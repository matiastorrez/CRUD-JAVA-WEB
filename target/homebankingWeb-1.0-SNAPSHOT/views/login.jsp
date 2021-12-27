<%@page import="helpers.TypeMessage"%>
<%@include file="./helpers/ifYouAreLoggedIn.jsp" %>
<%@include file="./partials/header.jsp" %>

<main  class="container d-flex  flex-column align-items-center gap-2 justify-content-center" >
    <h1 class="text-center my-5">Online banking</h1>
    <h2 class="text-center mb-5">Ingresá a tu cuenta a continuación:</h2>

    <%    String messageDB = (String) session.getAttribute("messageDB");

        if (messageDB != null) {
    %>
    <h2 class="my-3 alert alert-danger text-center text-break"><%=messageDB%></h2>
    <%
            session.setAttribute("messageDB", null);
        }

        TypeMessage messageLogin = (TypeMessage) session.getAttribute("messageLogin");

        if (messageLogin != null) {
    %>
    <h2 class="my-3  container  alert alert-<%= messageLogin.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageLogin.getMessage()%></h2>
    <%
            session.setAttribute("messageLogin", null);
        }

        TypeMessage messageRegister = (TypeMessage) session.getAttribute("messageRegister");

        if (messageRegister != null) {
    %>
    <h2 class="my-3 container  alert alert-<%= messageRegister.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageRegister.getMessage()%></h2>
    <%
            session.setAttribute("messageRegister", null);
        }
    %>   

    <div class="row d-flex justify-content-center align-items-center">
        <section  class="d-none d-md-block col-md-6">
            <img style="width: 100%" src="/img/user_login.svg" />
        </section>
        <form method="POST" action="/user/login" class="bg-light py-5 px-3 px-sm-5  rounded col-12 col-md-6">
            <div class="mb-3 col-12">
                <label for="user" class="form-label">Usuario</label>
                <input type="text" class="form-control " id="user" name="username" >
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" name="password" id="password">
            </div>

            <div class="row align-items-center justify-content-center justify-content-md-between ">
                <div class="col-auto ">
                    <button type="submit" class="btn btn-dark">Ingresar</button>
                </div>
                <div class="col-auto mt-2">
                    <a href="/view/register" class="link-primary">No tengo cuenta</a>
                </div>
            </div>
        </form>
    </div>
</main>
<%@include file="./partials/footer.jsp" %>

