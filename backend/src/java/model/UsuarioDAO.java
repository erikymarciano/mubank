/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Matth
 */
public class UsuarioDAO {
    
    private Connection conexao;
    
    public UsuarioDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = ConnectionFactory.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Usuario> getLista() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Usuario> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from usuarios");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Usuário para armazenar os dados
                //que vieram do BD
                Usuario usuario = new Usuario();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                usuario.setId(rs.getInt("id") );
                usuario.setNome( rs.getString("nome") );
                usuario.setEmail( rs.getString("email") );
                usuario.setSenha( rs.getString("senha") );
                usuario.setAceitouTermo( rs.getString("aceitou_termo") );
                //Adiciona o objeto criado na ArrayList resultado
                resultado.add(usuario);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Usuários encontrados no banco de dados.
        return resultado;
    }

    public Usuario getUsuarioPorID( int id ) {
        Usuario usuario = new Usuario();
        try {
            String sql = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                usuario.setId(rs.getInt("id") );
                usuario.setNome( rs.getString("nome") );
                usuario.setEmail( rs.getString("email") );
                usuario.setSenha( rs.getString("senha") );
                usuario.setAceitouTermo( rs.getString("aceitou_termo") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }
    
    public Usuario getUsuarioPorEmailESenha( String email, String senha ) {
        Usuario usuario = new Usuario();
        try {
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                usuario.setId(rs.getInt("id") );
                usuario.setNome( rs.getString("nome") );
                usuario.setEmail( rs.getString("email") );
                usuario.setSenha( rs.getString("senha") );
                usuario.setAceitouTermo( rs.getString("aceitou_termo") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }
    
    public boolean gravar( Usuario usuario ) {
        try {
            String sql;
            if ( usuario.getId() == 0 ) {
                // Realizar uma inclusão
                sql = "INSERT INTO usuarios (nome, email, senha, aceitou_termo) VALUES (?, ?, ?, ?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE usuarios SET nome=?, email=?, senha=?, aceitou_termo=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getAceitouTermo());
            
            if ( usuario.getId()> 0 )
                ps.setInt(5, usuario.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir( int id ) {
        try {
            String sql = "DELETE FROM usuarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
}
