/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import aplicacao.Usuario;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matth
 */
public final class SessaoHelper {
    
    public static Usuario getUsuarioLogado(HttpSession session){
        Object usuario_logado = session.getAttribute("usuario_logado");
        Usuario usuario = (Usuario) usuario_logado; 
        return usuario;
    }
    
}
