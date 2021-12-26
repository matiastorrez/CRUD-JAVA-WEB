

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@include file="../partials/header.jsp"%>

<%    User user = (User) session.getAttribute("userLogin");
%>

<main class="container">
    <h1 class="my-5 text-center" >Bienvenido a tu perfil <%= user.getUsername()%> </h1>

    <%
        String message = (String) session.getAttribute("messageDB");

        if (message != null) {
    %>
    <h1 class="m-5" ><%=message%></h1>
    <%
        session.setAttribute("messageDB", null);
    } else {

        HashMap<String, Integer> otherData = (HashMap<String, Integer>) request.getAttribute("otherData");
        HashMap<String, Object> userData = (HashMap<String, Object>) request.getAttribute("userData");

    %>   

    <div class="card mb-3 mx-auto " >
        <div class="row g-0">
            <div class="col-sm-4 align-self-center">
                <img src="/img/<%= user.getGender().equals("male") ? "male" : "female"%>.jpg" class="img-fluid col-12 rounded-start" alt="...">
            </div>
            <div class="col-sm-8 border border-1 d-flex align-items-center">
                <div class="card-body  ">
                    <table class="table table-borderless ">

                        <%
                            Iterator<String> userDataIt = userData.keySet().iterator();
                            while (userDataIt.hasNext()) {
                                String clave = userDataIt.next();
                                Object valor = userData.get(clave);
                        %>
                        <tr>
                            <td class="card-text fw-bolder">
                                <%=clave%>
                            </td>
                            <td class="card-text text-center">
                                <%=valor%>
                            </td>
                        </tr>
                        <%
                            }
                        %>

                    </table>
                </div>
            </div>
        </div>
    </div>

    <h2 class="text-center my-5">Otros datos</h2>


    <table class="table border border-1">

        <%
            Iterator<String> otherDataIt = otherData.keySet().iterator();
            while (otherDataIt.hasNext()) {
                String clave = otherDataIt.next();
                int valor = otherData.get(clave);
        %>
        <tr>
            <td class="card-text fw-bolder">
                <%=clave%>
            </td>
            <td class="card-text text-end">
                <%=valor%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</main>
<%
    }
%>

<%@include file="../partials/footer.jsp"%>