/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matth
 */

@WebFilter("/*")
public class FiltroAcesso implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        
        String url = request.getRequestURI();
        Object usuario_logado = session.getAttribute("usuario_logado");
        
        String acao = (String) request.getParameter("acao");
        
        if(usuario_logado != null){
            if(url.contains("SessaoController") && !acao.equals("logout")){
                response.sendRedirect("index.jsp");
            }else{
                chain.doFilter(req,res); 
            }
        }else{
            if(url.contains("SessaoController") || url.contains("UsuarioController")){
                chain.doFilter(req,res);
            }else{
                response.sendRedirect("SessaoController");
            }
        }
    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}