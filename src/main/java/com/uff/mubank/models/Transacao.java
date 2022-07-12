package com.uff.mubank.models;

import java.util.Date;

public abstract class Transacao {
    private long id;
    private Usuario usuario;
    private Date data;

    public Transacao(Usuario usuario) {
        this.id = 0;
        this.usuario = usuario;
        this.data = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean ehDoUsuario(Usuario usuario) {
        return this.usuario == usuario;
    }
}
