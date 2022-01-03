

<%@page import="helpers.TypeMessage"%>
<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Transfer"%>
<%@include file="../partials/header.jsp"%>


<main>


    <h1 class="text-center my-5 " >Transferencias</h1>
    <div class="my-5 mx-auto text-center">
        <a class='btn btn-primary' href="/view/user/form-transfers">Realizar una transferencia</a>
    </div>
<%      
    String message = (String) session.getAttribute("messageDB");
    if (message != null) {
%>
    <h2 class="my-3 container alert alert-danger text-center text-break"><%=message%></h2>
<%
        session.setAttribute("messageDB", null);
    } else {


TypeMessage messageTransfer = (TypeMessage) session.getAttribute("messageTransfer");

    if (messageTransfer != null) {
%>
<h2 class="my-3 container alert alert-<%= messageTransfer.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageTransfer.getMessage() %></h2>
<%
        session.setAttribute("messageTransfer", null);
    }
%>   


<%    List<Transfer> transfers = (List<Transfer>) request.getAttribute("transfers");
        if (transfers.size() > 0) {
%>
<div class="table-responsive">
    <table class="table  container ">
        <thead>
            <tr>
                <th scope="col" class="text-center text-sm-start">ID</th>
                <th scope="col" class="text-center text-sm-start">usuario origen</th>
                <th scope="col" class="text-center text-sm-start">ID cuenta origen</th>
                <th scope="col" class="text-center text-sm-start">usuario destino</th>
                <th scope="col" class="text-center text-sm-start">ID cuenta destino</th>
                <th scope="col" class="text-center text-sm-start">monto</th>
            </tr>
        </thead>
<%
            for (Transfer transfer : transfers) {
%>
        <tbody>
            <tr>
                <th scope="row"><%= transfer.getId()%></th>
                <td class="text-center text-sm-start"><%= transfer.getOrigin().getUser().getUsername()%></td>
                <td class="text-center text-sm-start"><%= transfer.getOrigin().getId()%></td>
                <td class="text-center text-sm-start"><%= transfer.getDestination().getUser().getUsername()%></td>
                <td class="text-center text-sm-start"><%= transfer.getDestination().getId()%></td>
                <td class="text-center text-sm-start">$<%= transfer.getAmount()%></td>
            </tr>
        </tbody>
<%
            }
%>

    </table>
</div>

<%
        } else {
%>

    <h2 class="text-center text-break">No hay transferencias realizadas</h2>

<%
            }
    }

%>
</main>

<%@include file="../partials/footer.jsp"%>