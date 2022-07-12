package com.uff.mubank.controller;

import com.uff.mubank.models.Transacao;
import com.uff.mubank.service.TransacaoService;

public class TransacaoController {
    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public Transacao criarDeposito(long idUsuario, double valor) {
        return transacaoService.criarDeposito(idUsuario, valor);
    }

    public Transacao criarSaque(long idUsuario, double valor) {
        return transacaoService.criarDeposito(idUsuario, valor);
    }

    public Transacao criarTransferencia(long idUsuarioOrigem, long idUsuarioDestino, double valor) {
        return transacaoService.criarTransferencia(idUsuarioOrigem, idUsuarioDestino, valor);
    }
}
