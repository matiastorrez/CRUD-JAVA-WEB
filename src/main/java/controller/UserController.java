/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.AccountDAO;
import database.TransferDAO;
import database.UserDAO;
import helpers.OtherData;
import helpers.TypeMessage;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Transfer;
import model.User;

/**
 *
 * @author Matias
 */
@WebServlet(name = "UserController", urlPatterns = {"/user/*"})
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getPathInfo();

            switch (action) {
                case "/login":
                    login(request, response);
                    break;
                case "/createuser":
                    createUser(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/profile":
                    profile(request, response);
                    break;
                case "/transfers":
                    transfers(request, response);
                    break;
                case "/mytransfers":
                    myTransfers(request, response);
                    break;
                case "/myaccounts":
                    myAccounts(request, response);
                    break;
                case "/createaccount":
                    createAccount(request, response);
                    break;
                default:

                    break;
            }

        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserDAO userDB = new UserDAO();
        String userName = request.getParameter("username");
        String pass = request.getParameter("password");

        try {
            User getUser = userDB.login(userName, pass);
            if (getUser != null) {
                session.setAttribute("userLogin", getUser);
                response.sendRedirect("/user/profile");
            } else {
                String message = "Alguna de sus credenciales es incorrecta";

                session.setAttribute("messageLogin", new TypeMessage("error", message));
                response.sendRedirect("/view/login");
            }
        } catch (Exception e) {
            session.setAttribute("messageDB", e.getMessage());
            response.sendRedirect("/view/login");
        }

    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        if (!repass.equalsIgnoreCase(pass)) {
            String message = "Las passwords no coinciden";
            session.setAttribute("messageRegister", new TypeMessage("error", message));
            response.sendRedirect("/view/register");
        } else {
            String userName = request.getParameter("username");
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            UserDAO userDB = new UserDAO();
            try {
                boolean isCreated = userDB.createUser(userName, pass, name, lastname, email, gender, repass);
                if (isCreated) {
                    String message = "Se creo la cuenta con exito";
                    session.setAttribute("messageRegister", new TypeMessage("success", message));
                    response.sendRedirect("/view/login");
                } else {
                    String message = "Hubo un error";
                    session.setAttribute("messageRegister", new TypeMessage("error", message));
                    response.sendRedirect("/view/register");
                }
            } catch (Exception e) {
                session.setAttribute("messageDB", e.getMessage());
                response.sendRedirect("/view/register");
            }
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("userLogin", null);
        response.sendRedirect("/");
    }

    private void profile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");
        AccountDAO accountDB = new AccountDAO();
        TransferDAO transferDB = new TransferDAO();
        if (getUser != null) {
            try {
                int totalAccounts = accountDB.totalNumberOfAccounts(getUser);
                int totalAccountsCA = accountDB.totalNumberOfAccountsCA(getUser);
                int totalAccountsCC = accountDB.totalNumberOfAccountsCC(getUser);
                int totalTransfers = transferDB.totalNumberTransfers(getUser);
                OtherData od = new OtherData(totalAccounts, totalAccountsCA, totalAccountsCC, totalTransfers);
                request.setAttribute("otherData", od.mapOtherData());
                request.setAttribute("userData", getUser.mapUserData());

            } catch (Exception e) {
                session.setAttribute("messageDB", e.getMessage());
            } finally {
                RequestDispatcher miDispatcher = request.getRequestDispatcher("/views/user/profile.jsp");
                miDispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("/view/login");
        }

    }

    private void transfers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");
        int idAccountUserOrigin = Integer.parseInt(request.getParameter("idAccountUserOrigin"));
        int idUserDestination = Integer.parseInt(request.getParameter("idUserDestination"));
        int idAccountUserDestination = Integer.parseInt(request.getParameter("idAccountUserDestination"));
        double amountTrasfer = Double.parseDouble(request.getParameter("amountTrasfer"));

        if (getUser != null) {
            UserDAO userDB = new UserDAO();
            AccountDAO accountsDB = new AccountDAO();
            TransferDAO transferDB = new TransferDAO();
            try {
                Account accountOrigin = accountsDB.getUserAccount(getUser, idAccountUserOrigin);
                if (accountOrigin != null) {
                    User userDestination = userDB.getUserByID(idUserDestination);
                    if (userDestination != null) {
                        Account accountDestination = accountsDB.getUserAccount(userDestination, idAccountUserDestination);
                        if (accountDestination != null) {
                            if (accountDestination.getId() != accountOrigin.getId()) {
                                Transfer transfer = new Transfer(accountOrigin, accountDestination, amountTrasfer);
                                if (transfer.transfer()) {
                                    transferDB.createTransfer(transfer);
                                    accountOrigin = transfer.getOrigin();
                                    accountDestination = transfer.getDestination();
                                    accountsDB.updateTotalAccount(accountOrigin);
                                    accountsDB.updateTotalAccount(accountDestination);
                                    String message = "La transaccion de $" + amountTrasfer + " fue un exito";
                                    session.setAttribute("messageTransfer", new TypeMessage("success", message));
                                    response.sendRedirect("/user/mytransfers");

                                } else {
                                    String message = "La transaccion de $" + amountTrasfer + " no se pudo realizar debido a que la cuenta que quizo utilizar para transferir tiene $" + accountOrigin.getTotal();
                                    session.setAttribute("messageTransfer", new TypeMessage("error", message));
                                    response.sendRedirect("/view/user/form-transfers");
                                }
                            } else {
                                String message = "No se puede realizar una transferencia a la misma cuenta";
                                session.setAttribute("messageTransfer", new TypeMessage("error", message));
                                response.sendRedirect("/view/user/form-transfers");
                            }
                        } else {
                            String message = "La cuenta n°" + idAccountUserDestination + " que colocó no existe en el usuario destinatario";
                            session.setAttribute("messageTransfer", new TypeMessage("error", message));
                            response.sendRedirect("/view/user/form-transfers");
                        }
                    } else {
                        String message = "El usuario destinatario con ID " + idUserDestination + " no existe";
                        session.setAttribute("messageTransfer", new TypeMessage("error", message));
                        response.sendRedirect("/view/user/form-transfers");
                    }
                } else {
                    String message = "La cuenta n°" + idAccountUserOrigin + " que selecciono no existe en su usuario";
                    session.setAttribute("messageTransfer", new TypeMessage("error", message));
                    response.sendRedirect("/view/user/form-transfers");
                }
            } catch (Exception e) {
                session.setAttribute("messageDB", e.getMessage());
                response.sendRedirect("/view/user/form-transfers");
            }
        } else {
            response.sendRedirect("/view/login");
        }
    }

    private void myTransfers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");

        if (getUser != null) {
            TransferDAO transferDB = new TransferDAO();
            try {
                List<Transfer> transfers = transferDB.getAllTransfers(getUser);
                request.setAttribute("transfers", transfers);
            } catch (Exception e) {
                session.setAttribute("messageDB", e.getMessage());
            } finally {
                RequestDispatcher miDispatcher = request.getRequestDispatcher("/views/user/myTransfers.jsp");
                miDispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("/view/login");
        }

    }

    private void myAccounts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");

        if (getUser != null) {
            AccountDAO accountsDB = new AccountDAO();
            try {
                List<Account> accounts = accountsDB.getUserAccounts(getUser);
                request.setAttribute("accounts", accounts);
            } catch (Exception e) {
                session.setAttribute("messageDB", e.getMessage());
            } finally {
                RequestDispatcher miDispatcher = request.getRequestDispatcher("/views/user/myAccounts.jsp");
                miDispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("/view/login");
        }
    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");
        if (getUser != null) {
            String account_type = request.getParameter("account_type");
            String password = request.getParameter("password");
            Account account = new Account(account_type);
            try {
                AccountDAO accountsDB = new AccountDAO();
                UserDAO userDB = new UserDAO();
                String message;
                boolean isPassword = userDB.isUserPassword(getUser, password);
                if (isPassword) {
                    boolean isCreated = accountsDB.createAccout(getUser, account);
                    if (isCreated) {
                        message = "Cuenta creada";
                        session.setAttribute("messageCreate", new TypeMessage("success", message));
                        response.sendRedirect("/user/myaccounts");
                    } else {
                        message = "No se pudo crear la cuenta";
                        session.setAttribute("messageCreate", new TypeMessage("error", message));
                        response.sendRedirect("/view/user/form-accounts");
                    }
                } else {
                    message = "Su password no es correcta";
                    session.setAttribute("messageCreate", new TypeMessage("error", message));
                    response.sendRedirect("/view/user/form-accounts");

                }

            } catch (Exception e) {
                session.setAttribute("messageDB", e.getMessage());

            }
        } else {
            response.sendRedirect("/view/login");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
