

<%@page import="helpers.TypeMessage"%>
<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="model.Account"%>
<%@include file="../partials/header.jsp"%>
<main>

    <h1 class="text-center my-5" >Cuentas</h1>
    <div class="my-5 mx-auto text-center">
        <a class="btn btn-primary " href="/view/user/form-accounts">Crear Cuenta</a>
    </div>

    <%        String message = (String) session.getAttribute("messageDB");
        if (message != null) {
    %>
    <h2 class="my-3 alert alert-danger text-center text-break"><%=message%></h2>
    <%
        session.setAttribute("messageDB", null);

    } else {

    TypeMessage messageCreate = (TypeMessage) session.getAttribute("messageCreate");

    if (messageCreate != null) {
%>
<h2 class="my-3 container alert alert-<%= messageCreate.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageCreate.getMessage() %></h2>
<%
        session.setAttribute("messageCreate", null);
    }

        List<Account> accounts = (List<Account>) request.getAttribute("accounts");
        if (accounts.size() > 0) {
    %>

    <table class="table container">
        <thead>
            <tr>
                <th scope="col" class="text-center text-sm-start">ID</th>
                <th scope="col" class="text-center text-sm-start">Account Type</th>
                <th scope="col" class="text-center text-sm-start">Total</th>
            </tr>
        </thead>
        <%
            for (Account account : accounts) {
        %>
        <tbody>
            <tr>
                <th scope="row" class="text-center text-sm-start"><%= account.getId()%></th>
                <td class="text-center text-sm-start"><%= account.getAccount_type()%></td>
                <td class="text-center text-sm-start">$<%= account.getTotal()%></td>
            </tr>
        </tbody>
        <%
            }
        %>

    </table>

    <%
    } else {
    %>

    <h2 class="text-center text-break">No hay cuentas creadas</h2>

    <%
            }
        }
    %>
</main>
<%@include file="../partials/footer.jsp"%>