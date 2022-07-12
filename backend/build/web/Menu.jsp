<%@ page import="java.util.*,aplicacao.*,helper.*" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="index.jsp">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="TransacaoController?acao=mostrar">Extrato</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="TransacaoController?acao=incluir">Nova Transação</a>
        </li>
    </ul>
  </div>
</nav>