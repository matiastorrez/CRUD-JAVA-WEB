/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.AccountDAO;
import database.TransferDAO;
import database.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

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

        } catch (SQLException | IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        UserDAO userDB = new UserDAO();
        String userName = request.getParameter("username");
        String pass = request.getParameter("password");

        User getUser = userDB.login(userName, pass);
        if (getUser != null) {
            session.setAttribute("userLogin", getUser);
            response.sendRedirect("/user/profile");
        } else {
            response.sendRedirect("/view/");
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        UserDAO userDB = new UserDAO();

        String userName = request.getParameter("username");
        String pass = request.getParameter("password");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String repass = request.getParameter("repassword");
        boolean isCreated = userDB.createUser(userName, pass, name, lastname, email, gender, repass);

        session.setAttribute("createMessage", isCreated);
        response.sendRedirect("/views/userMessage.jsp");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("userLogin", null);
        response.sendRedirect("/");
    }

    private void profile(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        UserDAO userDB = new UserDAO();
        List<User> users = userDB.getUsers();
        request.setAttribute("usuarios", users);
        RequestDispatcher miDispatcher = request.getRequestDispatcher("/views/user/profile.jsp");
        miDispatcher.forward(request, response);
    }

    private void transfers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");
        int idAccountUserOrigin = Integer.parseInt(request.getParameter("idAccountUserOrigin"));
        int idUserDestination = Integer.parseInt(request.getParameter("idUserDestination"));
        int idAccountUserDestination = Integer.parseInt(request.getParameter("idAccountUserDestination"));
        double amountTrasfer = Double.parseDouble(request.getParameter("amountTrasfer"));

        PrintWriter out = response.getWriter();
        
        if (getUser != null) {
            UserDAO userDB = new UserDAO();
            AccountDAO accountsDB = new AccountDAO();
            TransferDAO transferDB = new TransferDAO();

            
             out.println( "hola 1");

            Account accountOrigin = accountsDB.getUserAccount(getUser, idAccountUserOrigin);
            out.println(accountOrigin + " 1");

            if (accountOrigin != null) {
                User userDestination = userDB.getUserByID(idUserDestination);
                 out.println(userDestination + " 2");
                if (userDestination != null) {
                    Account accountDestination = accountsDB.getUserAccount(userDestination, idAccountUserDestination);
                    out.println(accountDestination + " 3");
                    if (accountDestination != null) {
                        Transfer transfer = new Transfer(accountOrigin, accountDestination, amountTrasfer);
                        transfer.transfer();
                        transferDB.createTransfer(transfer);
                        accountOrigin = transfer.getOrigin();
                        accountDestination = transfer.getDestination();
                        accountsDB.updateTotalAccount(accountOrigin);
                        accountsDB.updateTotalAccount(accountDestination);
                    }
                }
            }

            
            response.sendRedirect("/user/myaccounts");

        } else {
            response.sendRedirect("/view/login");
        }
    }

    private void myTransfers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");
        
        if (getUser != null) {
            TransferDAO transferDB = new TransferDAO();

            List<Transfer> transfers = transferDB.getAllTransfers(getUser);
            request.setAttribute("transfers", transfers);
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/views/user/myTransfers.jsp");

            miDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/view/login");
        }

    }

    private void myAccounts(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");

        if (getUser != null) {
            AccountDAO accountsDB = new AccountDAO();
            List<Account> accounts = accountsDB.getUserAccounts(getUser);

            request.setAttribute("accounts", accounts);
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/views/user/myAccounts.jsp");

            miDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/view/login");
        }

    }

    private void createAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        User getUser = (User) session.getAttribute("userLogin");

        if (getUser != null) {
            AccountDAO accountsDB = new AccountDAO();
            String account_type = request.getParameter("account_type");
            Account account = new Account(account_type);

            boolean isCreated = accountsDB.createAccout(getUser, account);

            if (isCreated) {
                response.sendRedirect("/user/myaccounts");
            } else {
                response.sendRedirect("/view/user/createaccount");
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
