package com.uff.mubank.service;

import com.uff.mubank.models.Perfil;
import com.uff.mubank.models.Usuario;
import com.uff.mubank.repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilService perfilService;
    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
        this.perfilService = new PerfilService();
    }

    public Usuario criar(String username, String senha, String nome){
        Perfil perfil = perfilService.criar(nome);
        return usuarioRepository.salvar(username, senha, perfil);
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario buscarPorUsername(String username){
        return usuarioRepository.buscarPorUsername(username);
    }
}
