/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import aplicacao.Transacao;
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
public class TransacaoDAO {
    
    private Connection conexao;
    
    public TransacaoDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = ConnectionFactory.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    
    public ArrayList<Transacao> getLista() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Transacao> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from transacoes");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Usuário para armazenar os dados
                //que vieram do BD
                Transacao transacao = new Transacao();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                transacao.setId(rs.getInt("id") );
                transacao.setTipo( rs.getString("tipo") );
                transacao.setValor( rs.getDouble("valor") );
                transacao.setIdConta( rs.getInt("id_conta") );
                //Adiciona o objeto criado na ArrayList resultado
                resultado.add(transacao);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Usuários encontrados no banco de dados.
        return resultado;
    }

    public ArrayList<Transacao> getListaPorConta(int id_conta) {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Transacao> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            String sql = "select * from transacoes where id_conta = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_conta);
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = ps.executeQuery();
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Usuário para armazenar os dados
                //que vieram do BD
                Transacao transacao = new Transacao();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                transacao.setId(rs.getInt("id") );
                transacao.setTipo( rs.getString("tipo") );
                transacao.setValor( rs.getDouble("valor") );
                transacao.setIdConta( rs.getInt("id_conta") );
                //Adiciona o objeto criado na ArrayList resultado
                resultado.add(transacao);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Usuários encontrados no banco de dados.
        return resultado;
    }

    
    public Transacao getTransacaoPorID( int id ) {
        Transacao transacao = new Transacao();
        try {
            String sql = "SELECT * FROM transacoes WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                transacao.setId(rs.getInt("id") );
                transacao.setTipo( rs.getString("tipo") );
                transacao.setValor( rs.getDouble("valor") );
                transacao.setIdConta( rs.getInt("id_conta") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return transacao;
    }
    
    public boolean gravar( Transacao transacao ) {
        try {
            String sql;
            if ( transacao.getId() == 0 ) {
                // Realizar uma inclusão
                sql = "INSERT INTO transacoes (tipo, valor, id_conta) VALUES (?, ?, ?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE transacoes SET tipo=?, valor=?, id_conta=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, transacao.getTipo());
            ps.setDouble(2, transacao.getValor());
            ps.setInt(3, transacao.getIdConta());
            
            if ( transacao.getId()> 0 )
                ps.setInt(4, transacao.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir( int id ) {
        try {
            String sql = "DELETE FROM transacoes WHERE id = ?";
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