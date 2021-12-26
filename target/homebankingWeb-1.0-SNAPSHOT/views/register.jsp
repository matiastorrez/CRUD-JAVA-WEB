<%@include file="./helpers/ifYouAreLoggedIn.jsp" %>
<%@include file="./partials/header.jsp" %>
<main class="container d-flex justify-content-center align-items-center gap-5" >

    <%        String message = (String) session.getAttribute("createMessage");

        if (message != null) {
    %>
    <h1 class="m-5" ><%=message%></h1>
    <%
        session.setAttribute("createMessage", null);
        }
    %>   
    <section class="left-hand">
        <img src="/img/user_login.svg" />
    </section>
    <form class="row g-3" method="POST" action="/user/createuser" enctype="application/x-www-form-urlencoded">
        <div class="col-md-6">
            <label for="name" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Ingresa tu nombre" required>
        </div>
        <div class="col-md-6">
            <label for="lastname" class="form-label">Apellido</label>
            <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Ingresa tu apellido" required>
        </div>
        <div class="col-md-8">
            <label for="username" class="form-label">Usuario</label>
            <div class="input-group has-validation">
                <input type="text" class="form-control" id="username" name="username" placeholder="Ingresa un nombre de usuario" aria-describedby="inputGroupPrepend" required>
            </div>
        </div>
        <div class="col-md-8">
            <label for="username" class="form-label">Email</label>
            <div class="input-group has-validation">
                <input type="email" class="form-control" id="email" name="email" placeholder="Ingresa un correo" aria-describedby="inputGroupPrepend" required>
            </div>
        </div>
        <div class="col-md-3 align-self-center">
            <label for="gender" class="form-label">Género</label>
            <div class="d-flex">
                <div class="form-check pe-5">
                    <input class="form-check-input" type="radio" name="gender" id="gender" value="female" checked>
                    <label class="form-check-label" for="female">
                        F
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="gender" id="gender" value="male">
                    <label class="form-check-label" for="male">
                        M
                    </label>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="col-md-3">
            <label for="repassword" class="form-label">Repita Contraseña</label>
            <input type="password" class="form-control" id="repassword" name="repassword" required>
        </div>
        <div class="col-12">
            <button class="btn btn-primary" type="submit">Registrarme</button>
        </div>
    </form>
</main>
<%@include file="./partials/footer.jsp" %>