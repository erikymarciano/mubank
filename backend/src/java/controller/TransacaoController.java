/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import aplicacao.Conta;
import aplicacao.Transacao;
import helper.SessaoHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ContaDAO;
import model.TransacaoDAO;

/**
 *
 * @author Matth
 */
@WebServlet(name = "TransacaoController", urlPatterns = {"/TransacaoController"})
public class TransacaoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Transacao> minhasTransacoes;
        Transacao transacao = new Transacao();
        switch (acao) {
            case "mostrar":
                HttpSession session = request.getSession(false);
                minhasTransacoes = transacaoDAO.getListaPorConta(SessaoHelper.getUsuarioLogado(session).getConta().getId());
                request.setAttribute("minhasTransacoes", minhasTransacoes);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/views/transacoes/ListaTransacaoView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                transacao.setId(0);
                transacao.setTipo("");
                transacao.setValor(0.0);
                transacao.setIdConta(0);
 
                request.setAttribute("transacao", transacao);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/views/transacoes/FormTransacao.jsp");
                incluir.forward(request, response);
                break;
            
            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                transacao = transacaoDAO.getTransacaoPorID(id);

                if (transacao.getId() > 0) {
                    request.setAttribute("transacao", transacao);
                    RequestDispatcher rs = request.getRequestDispatcher("/views/transacaos/FormTransacao.jsp");
                    rs.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar transacao!";
                    request.setAttribute("mensagem", mensagem);
                    request.setAttribute("url_retorno", "TransacaoController?acao=mostrar");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                transacaoDAO.excluir(id);

                minhasTransacoes = transacaoDAO.getLista();
                request.setAttribute("minhasTransacoes", minhasTransacoes);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/views/transacoes/ListaTransacaoView.jsp");
                aposexcluir.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem;
        try {
            Transacao transacao = new Transacao();

            transacao.setId(Integer.parseInt(request.getParameter("id")));
            transacao.setTipo(request.getParameter("tipo"));
            transacao.setValor(Double.parseDouble(request.getParameter("valor")));
            transacao.setIdConta(Integer.parseInt(request.getParameter("id_conta")));

            TransacaoDAO dao = new TransacaoDAO();
            ContaDAO conta_dao = new ContaDAO();
            
            Conta conta = conta_dao.getContaPorID(transacao.getIdConta());
            
            if(transacao.getTipo().equals("saque") && conta.getSaldo() < transacao.getValor()){
                mensagem = "Sua transação não pode exceder o valor que você tem em conta!";
            }else{
                if (dao.gravar(transacao)) {
                    if(transacao.getTipo().equals("saque")){
                        conta.setSaldo(conta.getSaldo() - transacao.getValor());
                    }else{
                        conta.setSaldo(conta.getSaldo() + transacao.getValor());
                    }
                    if(conta_dao.gravar(conta)){
                        mensagem = "Transacao gravada com sucesso!";
                    }else{
                        mensagem = "Erro ao atualizar saldo da conta!";
                    }
                } else {
                    mensagem = "Erro ao gravar transacao!";
                }
            }
        } catch (Exception e) {
            mensagem = "Erro ao gravar transacao!";
        }
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("url_retorno", "TransacaoController?acao=mostrar");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller de Transacoes";
    }// </editor-fold>

}
