package com.uff.mubank.repository;

import com.uff.mubank.models.*;

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

    public Transferencia salvarTransferencia(Usuario usuarioOrigem, Usuario usuarioDestino, double valor) {
        Transferencia transferencia = new Transferencia(usuarioOrigem, usuarioDestino, valor);
        this.ultimoId++;
        this.listaTransacao.add(transferencia);
        return transferencia;
    }

    public Transacao buscarPorId(Long id) {
        for (Transacao transacao : this.listaTransacao) {
            if (transacao.getId() == id) {
                return transacao;
            }
        }
        return null;
    }

    public List<Transacao> listarTransferenciasDoUsuario(Usuario usuario) {
        List<Transacao> listaResultado = new ArrayList<>();
        for (Transacao transacao : this.listaTransacao) {
            if (transacao.ehDoUsuario(usuario)) {
                listaResultado.add(transacao);
            }
        }
        return listaResultado;
    }
}
