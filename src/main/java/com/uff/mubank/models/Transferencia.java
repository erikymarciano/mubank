package com.uff.mubank.models;

public class Transferencia extends Transacao {
    private final Usuario destino;
    private final double valor;


    public Transferencia(Usuario origem, Usuario destino, double valor) {
        super(origem);
        this.destino = destino;
        this.valor = valor;
    }

    public Usuario getDestino() {
        return destino;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public boolean ehDoUsuario(Usuario usuario) {
        return usuario == this.destino || usuario == this.getUsuario();
    }
}
