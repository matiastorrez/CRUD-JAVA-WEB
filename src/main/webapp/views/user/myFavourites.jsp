


<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="helpers.TypeMessage"%>
<%@include file="../partials/header.jsp"%>
<main>

    <h1 class="text-center my-5" >Favoritos</h1>
    <div class="my-5 mx-auto text-center">
        <a class="btn btn-primary " href="/view/user/form-favourites">Agregar un favorito</a>
    </div>

    <%        String message = (String) session.getAttribute("messageDB");
        if (message != null) {
    %>
    <h2 class="my-3 alert alert-danger text-center text-break"><%=message%></h2>
    <%
        session.setAttribute("messageDB", null);

    } else {

    TypeMessage messageFavourite = (TypeMessage) session.getAttribute("messageFavourite");

    if (messageFavourite != null) {
%>
<h2 class="my-3 container alert alert-<%= messageFavourite.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageFavourite.getMessage() %></h2>
<%
        session.setAttribute("messageFavourite", null);
    }

        List<User> favourites = (List<User>) request.getAttribute("favourites");
        if (favourites.size() > 0) {
    %>

    <table class="table container">
        <thead>
            <tr>
                <th scope="col" class="text-center text-sm-start">ID del usuario</th>
                <th scope="col" class="text-center text-sm-start">Usuario</th>
                <th scope="col" class="text-center text-sm-start">Email</th>
                <th scope="col" class="text-center text-sm-start">Eliminar</th>
            </tr>
        </thead>
        <%
            for (User favourite : favourites) {
        %>
        <tbody>
            <tr>
                <th scope="row" class="text-center text-sm-start"><%= favourite.getId()%></th>
                <td class="text-center text-sm-start"><%= favourite.getUsername()%></td>
                <td class="text-center text-sm-start"><%= favourite.getEmail()%></td>
                <td class="text-center text-sm-start "><form action="/user/deletefavourite" method="POST"><input class="d-none" name="idFavourite" value=<%=favourite.getId()%> /> <button class="btn btn-danger">Eliminar</button></form></td>
            </tr>
        </tbody>
        <%
            }
        %>

    </table>

    <%
    } else {
    %>

    <h2 class="text-center text-break">No hay favoritos agregados</h2>

    <%
            }
        }
    %>
</main>
<%@include file="../partials/footer.jsp"%>