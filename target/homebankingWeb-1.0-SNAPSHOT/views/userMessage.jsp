<%@include file="./partials/header.jsp" %>
        <% 
            boolean isCreated = (boolean) session.getAttribute("createMessage");
            
            if (isCreated) {            
        %>
        <h1 class="m-5" >El usuario se ha creado satisfacctoriamente</h1>
        <a class="nav-link" href="/view/login">Ir al Login</a>
        <% 
            } else {           
        %> 
        <h1 class="m-5" >Hubo un error intenta de nuevo</h1>
        <a class="nav-link" href="/view/register">Volver a intentar</a>
        <% 
            }          
        %>   
        
<%@include file="./partials/footer.jsp" %>