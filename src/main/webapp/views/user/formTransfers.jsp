

<%@include file="../helpers/ifYouAreNotLoggedIn.jsp" %>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@include file="../partials/header.jsp"%>
<main  class="container d-flex flex-column align-items-center gap-2 justify-content-center" >

    <h1>Transferir</h1>

    <form method="POST" action="/user/transfers" class="bg-light p-5 rounded">
        <div class="mb-3">
            <label for="idAccountUserOrigin" class="form-label">ID de la cuenta que va a usar para transferir</label>
            <input type="text" class="form-control" name="idAccountUserOrigin" id="text">
        </div>
        <div class="mb-3">
            <label for="idUserDestination" class="form-label">ID del usuario a transferir</label>
            <input type="text" class="form-control" name="idUserDestination" id="text">
        </div>
        <div class="mb-3">
            <label for="idAccountUserDestination" class="form-label">ID de la cuenta de destino</label>
            <input type="text" class="form-control" name="idAccountUserDestination" id="accountID">
        </div>
         <div class="mb-3">
            <label for="amountTrasfer" class="form-label">Monto a transferir</label>
            <input type="text" class="form-control" name="amountTrasfer" id="amountTrasfer">
        </div>

        <div class="row align-items-center justify-content-center">
            <button type="submit" class="btn btn-dark">Transferir</button>
        </div>
    </form>
</main>

<%@include file="../partials/footer.jsp"%>