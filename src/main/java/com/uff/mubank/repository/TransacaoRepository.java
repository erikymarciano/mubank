package com.uff.mubank.repository;

import com.uff.mubank.models.Transacao;

import java.util.ArrayList;
import java.util.List;

public class TransacaoRepository {
    public Transacao salvar(Transacao transacao) {
        return transacao;
    }

    public Transacao buscarPorId(Long id){
        List<Transacao> listaTransacao = new ArrayList<>();
        for (Transacao transacao: listaTransacao){
            if (transacao.getId() == id){
                return transacao;
            }
        }
        return null;
    }
}
