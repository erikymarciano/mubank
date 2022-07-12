package com.uff.mubank.service;

import com.uff.mubank.models.Transacao;
import com.uff.mubank.models.Usuario;
import com.uff.mubank.repository.TransacaoRepository;

import java.util.List;

public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final UsuarioService usuarioService;

    public TransacaoService(UsuarioService usuarioService) {
        this.transacaoRepository = new TransacaoRepository();
        this.usuarioService = usuarioService;
    }

    public Transacao criarDeposito(Long idUsuario, double valor) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        usuarioService.somarSaldo(idUsuario, valor);
        return transacaoRepository.salvarDeposito(usuario, valor);
    }

    public Transacao criarSaque(Long idUsuario, double valor) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        if (usuario.getSaldo() >= valor) {
            usuarioService.somarSaldo(idUsuario, -1 * valor);
            return transacaoRepository.salvarSaque(usuario, valor);
        }
        return null;
    }

    public Transacao criarTransferencia(Long idUsuarioOrigem, Long idUsuarioDestino, double valor) {
        Usuario usuarioOrigem = usuarioService.buscarPorId(idUsuarioOrigem);
        Usuario usuarioDestino = usuarioService.buscarPorId(idUsuarioDestino);
        if (usuarioOrigem.getSaldo() >= valor && usuarioDestino != null) {
            usuarioService.somarSaldo(idUsuarioOrigem, -1 * valor);
            usuarioService.somarSaldo(idUsuarioDestino, valor);
            return transacaoRepository.salvarTransferencia(usuarioOrigem, usuarioOrigem, valor);
        }
        return null;
    }

    public List<Transacao> listarTransferenciasDoUsuario(Long idUsuario) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        return transacaoRepository.listarTransferenciasDoUsuario(usuario);
    }
}
