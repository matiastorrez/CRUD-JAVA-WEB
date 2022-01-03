<%@page import="helpers.TypeMessage"%>
<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@include file="../partials/header.jsp" %>

<main  class="container d-flex flex-column align-items-center  justify-content-center" >
    <%    String message = (String) session.getAttribute("messageDB");

        if (message != null) {
    %>
    <h2 class="my-3 alert alert-danger text-center text-break"><%=message%></h2>
    <%
            session.setAttribute("messageDB", null);
        }
    %>   

    <%
        TypeMessage messageChangePassword = (TypeMessage) session.getAttribute("messageChangePassword");

        if (messageChangePassword != null) {
    %>
    <h2 class="my-3  container  alert alert-<%= messageChangePassword.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageChangePassword.getMessage()%></h2>
    <%
            session.setAttribute("messageChangePassword", null);
        }
    %>   

    <h1 class="my-5 ">Cambiar Password</h1>


    <form method="POST" action="/user/changepassword" class="bg-light row py-5 px-1 px-sm-5 rounded">
        <div class="mt-3 col-12">
            <label for="currentPassword" class="form-label">Coloque contraseña actual</label>
            <input type="password" class="form-control" name="currentPassword" id="currentPassword">
        </div>
        <br>
        <div class="mt-3 col-12">
            <label for="newPassword" class="form-label">Coloque nueva contraseña</label>
            <input type="password" class="form-control" name="newPassword" id="newPassword">
        </div>
        <br>
        <div class="mt-3 col-12">
            <label for="repeatNewPassword" class="form-label">Repita nueva contraseña</label>
            <input type="password" class="form-control" name="repeatNewPassword" id="repeatNewPassword">
        </div>
        <br> 
        <div class=" col-12 mt-3">
            <button type="submit" class="btn btn-dark col-12">Cambiar</button>
        </div>
    </form>
</main>
<%@include file="../partials/footer.jsp" %>