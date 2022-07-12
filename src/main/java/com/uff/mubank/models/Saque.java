package com.uff.mubank.models;

public class Saque extends Transacao{
    private double valor;

    public Saque(double valor, Usuario usuario) {
        super(usuario);
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
