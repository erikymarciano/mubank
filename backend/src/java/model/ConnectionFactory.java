/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matth
 */
public class ConnectionFactory {
    private static Connection conexao = null;
    
    public static Connection criaConexao() throws SQLException {
        //Verifica se já exite uma conexão com o banco de dados
        if ( conexao == null ) {
            try {
                //Carrega o Driver JDBC na memória
                Class.forName("com.mysql.jdbc.Driver"); //load driver                       
                System.out.println("Driver foi carregado!");
                //Abre a conexão com o banco de dados via JDBC
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalhops", "root", "amancio1");
                System.out.println("Conexão realizada com sucesso!");
            }
            catch( ClassNotFoundException e ) {
                System.out.println("Driver não foi localizado!");
            }
        }
        // Retorna um objeto Connection, contendo a conexão aberta com o BD
        return conexao;
    }
}
