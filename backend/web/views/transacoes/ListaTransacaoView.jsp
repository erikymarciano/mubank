<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.*" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/header.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="/Menu.jsp" />

                   
            <h1>Lista de Transacaos</h1>     
            <p></p>
            <a href="TransacaoController?acao=incluir" class="btn btn-outline-primary">Incluir</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome Transacao</th>
                            <th scope="col">Tipo</th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Transacao> ListaTransacao = (ArrayList<Transacao>) request.getAttribute("minhasTransacoes");
                            for (int i = 0; i < ListaTransacao.size(); i++) {
                                Transacao aux = ListaTransacao.get(i);
          
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getValor()%></td>
                            <td><%=aux.getTipo()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="/basic_scripts.html" %>
    </body>
</html>
