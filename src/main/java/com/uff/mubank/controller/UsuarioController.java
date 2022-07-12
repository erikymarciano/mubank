package com.uff.mubank.controller;

import com.uff.mubank.models.Usuario;
import com.uff.mubank.service.UsuarioService;

import java.util.Objects;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public boolean logar(String username, String senha){
        return Objects.equals(usuarioService.buscarPorUsername(username).getSenha(), senha);
    }

    public Usuario criar(String username, String senha, String nome){
        return usuarioService.criar(username, senha, nome);
    }
}
