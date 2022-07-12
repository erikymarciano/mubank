package com.uff.mubank.repository;

import com.uff.mubank.models.Deposito;
import com.uff.mubank.models.Saque;
import com.uff.mubank.models.Transacao;
import com.uff.mubank.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TransacaoRepository {
    private double ultimoId;
    private final List<Transacao> listaTransacao;


    public TransacaoRepository() {
        this.listaTransacao = new ArrayList<>();
        this.ultimoId = 0;
    }

    public Transacao salvarDeposito(Usuario usuario, double valor) {
        Deposito deposito = new Deposito(valor, usuario);
        this.ultimoId++;
        this.listaTransacao.add(deposito);
        return deposito;
    }

    public Transacao salvarSaque(Usuario usuario, double valor) {
        Saque saque = new Saque(valor, usuario);
        this.ultimoId++;
        this.listaTransacao.add(saque);
        return saque;
    }

    public Transacao buscarPorId(Long id) {
        for (Transacao transacao : this.listaTransacao) {
            if (transacao.getId() == id) {
                return transacao;
            }
        }
        return null;
    }
}
