

<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@include file="../partials/header.jsp"%>

<main>
    <h1 class="m-5" >Bienvenido a tu perfil</h1>

<%
    out.print(session.getAttribute("userLogin"));
%>
</main>


<%@include file="../partials/footer.jsp"%>