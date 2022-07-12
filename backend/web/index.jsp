<%@ page import="java.util.*,helper.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="header.html" %>
    </head>
    <body>
        <div class="container mt-2">

            <%@include file="Menu.jsp" %>

            <h1>Trabalho de Projeto de Software</h1>
            <h2>Mubank</h2>
            
            <p>Bem vindo <%= SessaoHelper.getUsuarioLogado(session).getNome() %></p>
            <p>Seu saldo é <%= SessaoHelper.getUsuarioLogado(session).getConta().getSaldo() %></p>
            
            <a href="SessaoController?acao=logout">Deslogar</a>
            
            <p></p>
            <%@include file="basic_scripts.html" %>  

        </div>

    </body>
</html>