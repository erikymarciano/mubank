package com.uff.mubank.models;

public class Deposito extends Transacao{
    private double valor;

    public Deposito(double valor, Usuario usuario) {
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