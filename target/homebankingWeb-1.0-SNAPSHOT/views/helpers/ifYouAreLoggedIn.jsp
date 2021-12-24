<%@page import="model.User"%>
<%
    if (session.isNew()) {
        session.setAttribute("userLogin", null);
    }

    User getUser = (User) session.getAttribute("userLogin");
    if (getUser != null) {
        response.sendRedirect("/user/profile");
    }
%>
