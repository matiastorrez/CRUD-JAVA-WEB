<%@page import="helpers.TypeMessage"%>
<%@include file="./helpers/ifYouAreLoggedIn.jsp" %>
<%@include file="./partials/header.jsp" %>
<main class=" col-12 container  " >
    <h1 class="text-center my-5">Hacete cliente</h1>

    <%    String message = (String) session.getAttribute("messageDB");
        if (message != null) {
    %>
    <h2 class="my-3 alert alert-danger text-center text-break"><%=message%></h2>
    <%
            session.setAttribute("messageDB", null);
        }

        TypeMessage messageRegister = (TypeMessage) session.getAttribute("messageRegister");

        if (messageRegister != null) {
    %>
    <h2 class="my-3  container alert alert-<%= messageRegister.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageRegister.getMessage()%></h2>
    <%
            session.setAttribute("messageRegister", null);
        }
    %>       


    <div class="col-12 container d-flex justify-content-center align-items-center">


        <section class="d-none d-md-block col-md-6">
            <img style="width: 100%"  src="/img/user_login.svg" />
        </section>
        <form class="row bg-light py-5 px-3 px-sm-5 my-5" method="POST" action="/user/createuser" enctype="application/x-www-form-urlencoded">
            <div class="col-md-6 ">
                <label for="name" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="name" name="name"  required>
            </div>
            <div class="col-md-6 mt-3 mt-md-0">
                <label for="lastname" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="lastname" name="lastname"  required>
            </div>
            <div class="col-md-12 mt-3">
                <label for="username" class="form-label">Usuario</label>
                <div class="input-group has-validation">
                    <input type="text" class="form-control" id="username" name="username"  aria-describedby="inputGroupPrepend" required>
                </div>
            </div>
            <div class="col-md-12 mt-3">
                <label for="username" class="form-label">Email</label>
                <div class="input-group has-validation">
                    <input type="email" class="form-control" id="email" name="email"  aria-describedby="inputGroupPrepend" required>
                </div>
            </div>
            <div class="col-md-12 align-self-center mt-3">
                <label for="gender" class="form-label">Género</label>
                <div class="d-flex">
                    <div class="form-check pe-5">
                        <input class="form-check-input" type="radio" name="gender" id="gender" value="Femenino" checked>
                        <label class="form-check-label" for="female">
                            F
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="gender" value="Masculino">
                        <label class="form-check-label" for="male">
                            M
                        </label>
                    </div>
                </div>
            </div>
            <div class="col-md-12 mt-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="col-md-12 mt-3">
                <label for="repassword" class="form-label">Repita Contraseña</label>
                <input type="password" class="form-control" id="repassword" name="repassword" required>
            </div>
            <div class="col-12 mt-3">
                <button class="btn btn-primary col-12" type="submit">Registrarme</button>
            </div>
        </form>

    </div>

</main>
<%@include file="./partials/footer.jsp" %>