<%@page import="aplicacao.Transacao,helper.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/header.html" %>
    </head>
    <body>
        <div class="container mt-2">

            <jsp:include page="/Menu.jsp" />
            
            <%
               Transacao aux = (Transacao)request.getAttribute("transacao");
            %>
            <div class="col-6 mt-5">
                  <h4>Incluir Transacao</h4>
                  <form method="POST" action="TransacaoController" >
                      <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                      <input type="hidden" class="form-control" name="id_conta" value="<%= SessaoHelper.getUsuarioLogado(session).getId() %>">
                      
                    <div class="form-group">
                        <label for="valor">Valor</label>
                        <input type="number" class="form-control" name="valor" value="<%= aux.getValor() %>" required size="30" maxlength="100" placeholder="Nome da Transacao">
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <a href="TransacaoController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
                </form>
            </div>
        </div>

        <%@include file="/basic_scripts.html" %>
    </body>
</html>
