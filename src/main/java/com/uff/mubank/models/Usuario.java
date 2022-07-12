package com.uff.mubank.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private long id;
    private String username;
    private String senha;
    private Perfil perfil;
    private double saldo;
    private final List<Transacao> listaTransacao;

    public Usuario(long id, String username, String senha, Perfil perfil) {
        this.id = id;
        this.username = username;
        this.senha = senha;
        this.perfil = perfil;
        this.saldo = 0.0;
        this.listaTransacao = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getListaTransacao() {
        return listaTransacao;
    }

    public void adicionaTransacao(Transacao transacao){
        listaTransacao.add(transacao);
    }
}