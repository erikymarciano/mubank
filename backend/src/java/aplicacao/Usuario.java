/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import model.ContaDAO;

/**
 *
 * @author Matth
 */
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String aceitou_termo;
    
    public Usuario(){
        
    }

    public Usuario(int id, String nome, String email, String senha, String aceitou_termo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.aceitou_termo = aceitou_termo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getAceitouTermo() {
        return aceitou_termo;
    }

    public void setAceitouTermo(String aceitou_termo) {
        this.aceitou_termo = aceitou_termo;
    }
    
    public Conta getConta() {
        return new ContaDAO().getContaPorUsuarioId(this.id);
    }
}
