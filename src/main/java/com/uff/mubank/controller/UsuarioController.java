package com.uff.mubank.controller;

import com.uff.mubank.models.Usuario;
import com.uff.mubank.service.UsuarioService;

import java.util.Objects;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public boolean logar(String username, String senha) {
        return Objects.equals(usuarioService.buscarPorUsername(username).getSenha(), senha);
    }

    public Usuario criar(String username, String senha, String nome) {
        return usuarioService.criar(username, senha, nome);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioService.buscarPorUsername(username);
    }

    public Usuario alterar(long id, String senha) {
        return usuarioService.alterar(id, senha);
    }
}
