<%@page import="model.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/index.css"/>
    <link rel="shortcut icon" href="/img/icon.svg" type="image/x-icon">
    <title>bankApp | Tu App, Tu Banco</title>
  </head>
<body id="main-body">
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
            <div class="container">
              <a class="navbar-brand" href="/">
                  <img  src="/img/bankAppHorizontal.svg" />
              </a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse justify-content-end" id="navbarText">               
                <%
                    if (session.isNew()) {
                       session.setAttribute("userLogin", null);
                    } 
                    User userLogin = (User) session.getAttribute("userLogin");
                    
                     if (userLogin != null) {  
                       // String avatar = (String) session.getAttribute("avatar");
                %>
                <ul class="navbar-nav mb-2 mb-lg-0">
                     <li class="nav-item">
                        <a class="nav-link" href="/user/myfavourites">Favoritos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/myaccounts">Mis Cuentas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/mytransfers">Transferencias</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/profile">Mi Perfil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/logout">Cerrar Sesión</a>
                    </li>
                </ul>
                <% 
                  //  if (avatar == "female") {
                %>
                <!--<div class="avatar">
                    <img href="/female.jpg" />
                </div>-->
                    <% 
                      //  } else {
                    %>
                <!--<div class="avatar">
                    <img href="/male.jpg" />
                </div>-->
                <% 
                 //   }
                } else {
                %>
                <ul class="navbar-nav mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/view/register">Hacete cliente</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/view/login">Online Banking</a>
                    </li>
                </ul>
                <% 
                  }
                %>
              </div>
            </div>
        </nav>
    </header>
