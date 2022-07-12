package com.uff.mubank.service;

import com.uff.mubank.models.Perfil;
import com.uff.mubank.models.Usuario;
import com.uff.mubank.repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilService perfilService;
    public UsuarioService(PerfilService perfilService) {
        this.usuarioRepository = new UsuarioRepository();
        this.perfilService = perfilService;
    }

    public Usuario criar(String username, String senha, String nome){
        Perfil perfil = perfilService.criar(nome);
        return usuarioRepository.salvar(username, senha, perfil);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.buscarPorUsername(username);
    }

    public Usuario alterar(long id, String senha) {
        return usuarioRepository.alterar(id, senha);
    }
}
