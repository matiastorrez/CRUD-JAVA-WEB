

<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="model.Account"%>
<%@include file="../partials/header.jsp"%>
<main>

    <h1 class="m-5" >Accounts</h1>
    <a href="/view/user/createaccount">Crear Cuenta</a>
    <%    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
        if (accounts.size() > 0) {
    %>

    <table class="table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Account Type</th>
                <th scope="col">Total</th>
            </tr>
        </thead>
        <%
            for (Account account : accounts) {
        %>
        <tbody>
            <tr>
                <th scope="row"><%= account.getId()%></th>
                <td><%= account.getAccount_type()%></td>
                <td><%= account.getTotal()%></td>
            </tr>
        </tbody>
        <% 
            }
        %>

    </table>

    <%
    } else {
    %>

    <h2>No hay cuentas disponibles</h2>

    <%
        }
    %>
</main>
<%@include file="../partials/footer.jsp"%>