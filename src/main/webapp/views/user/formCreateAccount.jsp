<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@include file="../partials/header.jsp" %>

<main  class="container d-flex flex-column align-items-center gap-2 justify-content-center" >

    <h1>Crear Cuenta</h1>

<%
    String message = (String) session.getAttribute("messageDB");

    if (message != null) {
%>
    <h1 class="m-5" ><%=message%></h1>
<%
        session.setAttribute("messageDB", null);
    }
%>   
    
<%        
    String messageCreate = (String) session.getAttribute("messageCreate");

    if (messageCreate != null) {
%>
        <h1 class="m-5" ><%=messageCreate%></h1>
<%
        session.setAttribute("messageCreate", null);
    }
%>   


    <form method="POST" action="/user/createaccount" class="bg-light p-5 rounded">


        <div class="form-group">
            <label for="account_type">Tipo de cuenta</label>
            <select  class="form-control" id="account_type" name="account_type" >
                <option value="CC">Cuenta Corriente</option>
                <option value="CA">Caja de ahorro</option>
            </select>
        </div>
        <br>
        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>

        <div class="row align-items-center justify-content-center">
            <button type="submit" class="btn btn-dark">Crear</button>
        </div>
    </form>
</main>
<%@include file="../partials/footer.jsp" %>