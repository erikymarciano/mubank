/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import aplicacao.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UsuarioDAO;

/**
 *
 * @author Matth
 */
@WebServlet(name = "SessaoController", urlPatterns = {"/SessaoController"})
public class SessaoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = (String) request.getParameter("acao");
        
        if("logout".equals(acao)){
            HttpSession session = request.getSession(false);
            session.invalidate();
        }
        
        RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/views/sessoes/Login.jsp");
        mostrar.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem;
        try {

            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            UsuarioDAO dao = new UsuarioDAO();
            
            Usuario usuario = dao.getUsuarioPorEmailESenha(email, senha);
            HttpSession session = request.getSession();

            if (usuario != null) {
                session.setAttribute("usuario_logado", usuario);
                mensagem = "Usuário logado com sucesso!";
            } else {
                response.sendRedirect("SessaoController");
                return;
            }

        } catch (Exception e) {
            mensagem = "Erro ao fazer login - 2!";
        }
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("url_retorno", "index.jsp");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller de Sessão";
    }// </editor-fold>

}