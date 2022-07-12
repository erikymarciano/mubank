/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import aplicacao.Conta;
import aplicacao.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ContaDAO;
import model.UsuarioDAO;

/**
 *
 * @author Matth
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        Usuario usuario = new Usuario();
        switch (acao) {
            case "incluir":
                usuario.setId(0);
                usuario.setNome("");
                usuario.setEmail("");
                usuario.setSenha("");
                usuario.setAceitouTermo("");
 
                request.setAttribute("usuario", usuario);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/views/usuarios/FormUsuario.jsp");
                incluir.forward(request, response);
                break;
            
            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                usuario = usuarioDAO.getUsuarioPorID(id);

                if (usuario.getId() > 0) {
                    request.setAttribute("usuario", usuario);
                    RequestDispatcher rs = request.getRequestDispatcher("/views/usuarios/FormUsuario.jsp");
                    rs.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar usuario!";
                    request.setAttribute("mensagem", mensagem);
                    request.setAttribute("url_retorno", "UsuarioController?acao=mostrar");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem;
        try {
            Usuario usuario = new Usuario();
            Conta conta = new Conta();
            
            conta.setSaldo(0);

            usuario.setId(Integer.parseInt(request.getParameter("id")));
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setAceitouTermo(request.getParameter("aceitou_termo") != null ? "S" : "N");

            UsuarioDAO dao = new UsuarioDAO();
            ContaDAO conta_dao = new ContaDAO();

            if (dao.gravar(usuario)) {
                usuario = dao.getUsuarioPorEmailESenha(usuario.getEmail(), usuario.getSenha());
                conta.setIdUsuario(usuario.getId());
                if(conta_dao.gravar(conta)){
                    mensagem = "Usuario gravado com sucesso!";
                }else{
                    mensagem = "Erro ao criar conta do usuário!";
                }
            } else {
                mensagem = "Erro ao gravar usuario!";
            }

        } catch (Exception e) {
            mensagem = "Erro ao gravar usuario!";
        }
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("url_retorno", "index.jsp");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller de Usuarios";
    }// </editor-fold>

}
