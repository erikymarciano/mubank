/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Conta;
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
public class ContaDAO {
    
    private Connection conexao;
    
    public ContaDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = ConnectionFactory.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Conta> getLista() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Conta> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from contas");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Contato para armazenar os dados
                //que vieram do BD
                Conta conta = new Conta();
                
                conta.setId(rs.getInt("id") );
                conta.setSaldo( rs.getDouble("saldo") );
                conta.setIdUsuario( rs.getInt("id_usuario") );
                //Adiciona o objeto criado na ArrayList resultado
                resultado.add(conta);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Contatos encontrados no banco de dados.
        return resultado;
    }
    
    public Conta getContaPorID( int id ) {
        Conta conta = new Conta();
        try {
            String sql = "SELECT * FROM contas WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                conta.setId(rs.getInt("id"));
                conta.setSaldo( rs.getDouble("saldo") );
                conta.setIdUsuario( rs.getInt("id_usuario") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return conta;
    }
    
    public Conta getContaPorUsuarioId( int usuario_id ) {
        Conta conta = new Conta();
        try {
            String sql = "SELECT * FROM contas WHERE id_usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, usuario_id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                conta.setId(rs.getInt("id"));
                conta.setSaldo( rs.getDouble("saldo") );
                conta.setIdUsuario( rs.getInt("id_usuario") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return conta;
    }
    
    public boolean gravar( Conta conta )    {
        try {
            String sql;
            if ( conta.getId() == 0 ) {
                // Realizar uma inclusão
                sql = "INSERT INTO contas (saldo, id_usuario) VALUES (?, ?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE contas SET saldo=?, id_usuario=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setDouble(1, conta.getSaldo());
            ps.setInt(2, conta.getIdUsuario());
            
            if ( conta.getId()> 0 )
                ps.setInt(3, conta.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir( int id ) {
        try {
            String sql = "DELETE FROM contas WHERE id = ?";
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
