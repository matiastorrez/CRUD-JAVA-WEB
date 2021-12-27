<%@page import="helpers.TypeMessage"%>
<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@include file="../partials/header.jsp" %>

<main  class="container d-flex flex-column align-items-center  justify-content-center" >
<%
    String message = (String) session.getAttribute("messageDB");

    if (message != null) {
%>
    <h2 class="my-3 alert alert-danger text-center text-break"><%=message%></h2>
<%
        session.setAttribute("messageDB", null);
    }
%>   
    
<%        
    TypeMessage messageCreate = (TypeMessage) session.getAttribute("messageCreate");

    if (messageCreate != null) {
%>
<h2 class="my-3  container  alert alert-<%= messageCreate.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageCreate.getMessage() %></h2>
<%
        session.setAttribute("messageCreate", null);
    }
%>   

    <h1 class="my-5 ">Crear Cuenta</h1>


    <form method="POST" action="/user/createaccount" class="bg-light row py-5 px-1 px-sm-5 rounded">
        <div class="form-group col-12 ">
            <label for="account_type">Tipo de cuenta</label>
            <select  class="form-control" id="account_type" name="account_type" >
                <option value="CC">Cuenta Corriente</option>
                <option value="CA">Caja de ahorro</option>
            </select>
        </div>
        <br>
        <div class="mt-3 col-12">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>

        <div class=" col-12 mt-3">
            <button type="submit" class="btn btn-dark col-12">Crear</button>
        </div>
    </form>
</main>
<%@include file="../partials/footer.jsp" %>