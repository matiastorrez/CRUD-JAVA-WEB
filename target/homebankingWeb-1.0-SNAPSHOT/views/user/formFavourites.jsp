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
    TypeMessage messageFavourite = (TypeMessage) session.getAttribute("messageFavourite");

    if (messageFavourite != null) {
%>
<h2 class="my-3  container  alert alert-<%= messageFavourite.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageFavourite.getMessage() %></h2>
<%
        session.setAttribute("messageFavourite", null);
    }
%>   

    <h1 class="my-5 ">Agregar un Favorito</h1>


    <form method="POST" action="/user/createfavourite" class="bg-light row py-5 px-1 px-sm-5 rounded">
        <div class="form-group col-12 ">
            <label for="idFavourite" class="form-label">ID del usuario que quiere agregar como favorito</label>
            <input type="text" class="form-control" name="idFavourite" id="idFavourite">
        </div>
        <br>
        <div class=" col-12 mt-3">
            <button type="submit" class="btn btn-dark col-12">Agregar</button>
        </div>
    </form>
</main>
<%@include file="../partials/footer.jsp" %>