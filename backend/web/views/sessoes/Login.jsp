<%-- 
    Document   : Login
    Created on : 10/07/2022, 15:15:10
    Author     : Matth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/header.html" %>
    </head>
    <body class="container text-center mt-5">
        <form class="form-signin" method="POST" action="SessaoController">
          <img class="mb-4" src="http://visual.ic.uff.br/dmi/img/icuff.jpg" alt="" width="227" height="155">
          
          <div class="mt-3">
            <label for="inputEmail" class="sr-only">E-mail</label>
            <input type="email" name="email" class="form-control" placeholder="Seu e-mail" required="" autofocus="">
          </div>
          
          <div class="mt-3">
            <label for="inputPassword" class="sr-only">Senha</label>
            <input type="password" name="senha" class="form-control" placeholder="Senha" required="">
          </div>

          <div class="mt-5">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
          </div
        </form>
        
        <div class="mt-4">
            <a href="UsuarioController?acao=incluir">Não tem uma conta? Cadastre-se</a>
        </div>
    </body>
</html>
