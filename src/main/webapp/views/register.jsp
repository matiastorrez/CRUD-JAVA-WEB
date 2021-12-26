<%@include file="./helpers/ifYouAreLoggedIn.jsp" %>
<%@include file="./partials/header.jsp" %>
<main class=" col-12 container d-flex justify-content-center align-items-center " >

<%
    String message = (String) session.getAttribute("messageDB");
    if (message != null) {
%>
        <h1 class="m-5" ><%=message%></h1>
<%
        session.setAttribute("messageDB", null);
    }
  
    String messageRegister = (String) session.getAttribute("messageRegister");
    if (messageRegister != null) {
%>
    <h1 class="m-5" ><%=messageRegister%></h1>
<%
    session.setAttribute("messageRegister", null);
    }
%>   
    
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
            <label for="gender" class="form-label">G�nero</label>
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
        <div class="col-md-12 mt-3">
            <label for="password" class="form-label">Contrase�a</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="col-md-12 mt-3">
            <label for="repassword" class="form-label">Repita Contrase�a</label>
            <input type="password" class="form-control" id="repassword" name="repassword" required>
        </div>
        <div class="col-12 mt-3">
            <button class="btn btn-primary col-12" type="submit">Registrarme</button>
        </div>
    </form>
</main>
<%@include file="./partials/footer.jsp" %>