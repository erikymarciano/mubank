<%@page import="aplicacao.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/header.html" %>
    </head>
    <body>
        <div class="container mt-2">
            
            <%
               Usuario aux = (Usuario)request.getAttribute("usuario");
            %>
            <div class="col-6 mt-5">
                  <h4>Cadastro</h4>
                  <form method="POST" action="UsuarioController" >
                      <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                      
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" class="form-control" name="nome" value="<%= aux.getNome() %>" required size="30" maxlength="100" placeholder="Nome">
                    </div>
                    <div class="form-group">
                        <label for="email">E-mail</label>
                        <input type="text" class="form-control" name="email" value="<%= aux.getEmail() %>" required size="30" maxlength="100" placeholder="E-mail">
                    </div>
                    <div class="form-group">
                        <label for="senha">Senha</label>
                        <input type="password" class="form-control" name="senha" value="<%= aux.getSenha() %>" required size="30" maxlength="100" placeholder="Senha">
                    </div>
                    <div class="form-group">
                        <input type="checkbox" name="aceitou_termo" required>   
                        <label for="aceitou_termo">Concorda com os termos de uso e política de privacidade?</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
        </div>

        <%@include file="/basic_scripts.html" %>
    </body>
</html>
