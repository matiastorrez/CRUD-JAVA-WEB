<%@include file="./helpers/ifYouAreLoggedIn.jsp" %>
<%@include file="./partials/header.jsp" %>

<main  class="container d-flex flex-column align-items-center gap-2 justify-content-center" >
    <h1 class="text-center mb-3">Online banking</h1>
    <h2 class="text-center mb-5">Ingresá a tu cuenta a continuación:</h2>
    <%        String message = (String) session.getAttribute("createMessage");

        if (message != null) {
    %>
    <h1 class="m-5" ><%=message%></h1>
    <%
            session.setAttribute("createMessage", null);
        }
    %>   


    <div id="login-main" class=" d-flex justify-content-center align-items-center">
        <section class="left-hand">
            <img src="/img/user_login.svg" />
        </section>
        <form method="POST" action="/user/login" class="bg-light p-5 rounded">
            <div class="mb-3">
                <label for="user" class="form-label">Usuario</label>
                <input type="text" class="form-control" id="user" name="username" >
                <div class="form-text">Nunca compartiremos sus datos con alguien más.</div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" name="password" id="password">
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
            <div class="row align-items-center justify-content-between">
                <div class="col-auto">
                    <button type="submit" class="btn btn-dark">Ingresar</button>
                </div>
                <div class="col-auto">
                    <a href="/view/register" class="link-primary">No tengo cuenta</a>
                </div>
            </div>
        </form>
    </div>
</main>
<%@include file="./partials/footer.jsp" %>

