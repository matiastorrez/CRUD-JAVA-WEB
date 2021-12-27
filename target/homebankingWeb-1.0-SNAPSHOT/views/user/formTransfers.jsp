

<%@page import="helpers.TypeMessage"%>
<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@include file="../partials/header.jsp"%>
<main  class="container d-flex flex-column align-items-center gap-2 justify-content-center" >

    <%        String message = (String) session.getAttribute("messageDB");

        if (message != null) {
    %>
    <h2 class="my-3 alert alert-danger text-center text-break"><%=message%></h2>
    <%
            session.setAttribute("messageDB", null);
        }
    %>   

    <%
        TypeMessage messageTransfer = (TypeMessage) session.getAttribute("messageTransfer");

        if (messageTransfer != null) {
    %>
    <h2 class="my-3  container  alert alert-<%= messageTransfer.getType().equalsIgnoreCase("error") ? "danger" : "success"%>  text-center text-break" ><%=messageTransfer.getMessage()%></h2>
    <%
            session.setAttribute("messageTransfer", null);
        }
    %>   

    <h1 class="my-5">Transferir</h1>

    <form method="POST" action="/user/transfers" class="bg-light py-5 px-3 px-sm-5 rounded">

        <div class="form-group row mt-3" id="user-group">
            <label for="idAccountUserOrigin" class="form-label">ID de la cuenta que va a usar para transferir</label>
            <div class="col-2 p-0 text-center" >
                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-book" style="width: 44; height: 44;"viewBox="0 0 24 24" stroke-width="1.5" stroke="blue" fill="none" stroke-linecap="round" stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <path d="M3 19a9 9 0 0 1 9 0a9 9 0 0 1 9 0" />
                    <path d="M3 6a9 9 0 0 1 9 0a9 9 0 0 1 9 0" />
                    <line x1="3" y1="6" x2="3" y2="19" />
                    <line x1="12" y1="6" x2="12" y2="19" />
                    <line x1="21" y1="6" x2="21" y2="19" />
                </svg>
            </div>
            <div class="col-10 ">
                <input type="text" class="form-control" name="idAccountUserOrigin" id="amountTrasfer">
            </div>
        </div>

        <div class="form-group row mt-3" id="user-group">
            <label for="idUserDestination" class="form-label">ID del usuario a transferir</label>
            <div class="col-2 p-0 text-center" >
                <svg  xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user" style="width: 44; height: 44;"viewBox="0 0 24 24" stroke-width="1.5" stroke="red" fill="none" stroke-linecap="round" stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <circle cx="12" cy="7" r="4" />
                    <path d="M6 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" />
                </svg>
            </div>
            <div class="col-10 ">
                <input type="text" class="form-control" name="idUserDestination" id="amountTrasfer">
            </div>
        </div>

        <div class="form-group row mt-3" id="user-group">
            <label for="idAccountUserDestination" class="form-label">ID de la cuenta de destino</label>
            <div class="col-2 p-0 text-center" >
                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-book" style="width: 44; height: 44;" viewBox="0 0 24 24" stroke-width="1.5" stroke="red" fill="none" stroke-linecap="round" stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <path d="M3 19a9 9 0 0 1 9 0a9 9 0 0 1 9 0" />
                    <path d="M3 6a9 9 0 0 1 9 0a9 9 0 0 1 9 0" />
                    <line x1="3" y1="6" x2="3" y2="19" />
                    <line x1="12" y1="6" x2="12" y2="19" />
                    <line x1="21" y1="6" x2="21" y2="19" />
                </svg>
            </div>
            <div class="col-10 ">
                <input type="text" class="form-control" name="idAccountUserDestination" id="amountTrasfer">
            </div>
        </div>

        <div class="form-group row mt-3" id="user-group">
            <label for="amountTrasfer" class="form-label">Monto a transferir</label>
            <div class="col-2 p-0 text-center" >
                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-coin" style="width: 44; height: 44;"  viewBox="0 0 24 24" stroke-width="1.5" stroke="#2c3e50" fill="none" stroke-linecap="round" stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <circle cx="12" cy="12" r="9" />
                    <path d="M14.8 9a2 2 0 0 0 -1.8 -1h-2a2 2 0 0 0 0 4h2a2 2 0 0 1 0 4h-2a2 2 0 0 1 -1.8 -1" />
                    <path d="M12 6v2m0 8v2" />
                </svg>
            </div>
            <div class="col-10 ">
                <input type="text" class="form-control" name="amountTrasfer" id="amountTrasfer">
            </div>
        </div>

        <div class="d-flex align-items-center justify-content-center mt-4">
            <button type="submit" class="btn btn-dark col-12">Transferir</button>
        </div>
    </form>
</main>

<%@include file="../partials/footer.jsp"%>