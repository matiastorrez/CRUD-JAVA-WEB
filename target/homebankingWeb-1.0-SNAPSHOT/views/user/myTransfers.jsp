

<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Transfer"%>
<%@include file="../partials/header.jsp"%>


<main>


    <h1 class="m-5" >Transfers</h1>

<%      
    String message = (String) session.getAttribute("messageDB");
    if (message != null) {
%>
    <h1 class="m-5" ><%=message%></h1>
<%
        session.setAttribute("messageDB", null);
    } else {
        String messageTransfers = (String) session.getAttribute("messageTransfer");
        if(messageTransfers != null){
%>
            <h1 class="m-5" ><%=messageTransfers%></h1>
<%
            session.setAttribute("messageTransfer", null);
        }
%>   
    <a href="/view/user/transfers">Realizar una transferencia</a>
<%    List<Transfer> transfers = (List<Transfer>) request.getAttribute("transfers");
        if (transfers.size() > 0) {
%>

    <table class="table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">usuario origen</th>
                <th scope="col">ID cuenta origen</th>
                <th scope="col">usuario destino</th>
                <th scope="col">ID cuenta destino</th>
                <th scope="col">monto</th>
            </tr>
        </thead>
<%
            for (Transfer transfer : transfers) {
%>
        <tbody>
            <tr>
                <th scope="row"><%= transfer.getId()%></th>
                <td><%= transfer.getOrigin().getUser().getUsername()%></td>
                <td><%= transfer.getOrigin().getId()%></td>
                <td><%= transfer.getDestination().getUser().getUsername()%></td>
                <td><%= transfer.getDestination().getId()%></td>
                <td><%= transfer.getAmount()%></td>
            </tr>
        </tbody>
<%
            }
%>

    </table>

<%
        } else {
%>

    <h2>No hay transferencias disponibles</h2>

<%
            }
    }

%>
</main>

<%@include file="../partials/footer.jsp"%>