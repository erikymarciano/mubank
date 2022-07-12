package com.uff.mubank.service;

import com.uff.mubank.models.Perfil;
import com.uff.mubank.repository.PerfilRepository;

public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService() {
        this.perfilRepository = new PerfilRepository();
    }

    public Perfil criar(String nome) {
        return perfilRepository.salvar(nome);
    }

    public Perfil buscarPorId(Long id) {
        return perfilRepository.buscarPorId(id);
    }

    public Perfil alterar(Long id, String nome) {
        return perfilRepository.alterar(id, nome);
    }
}
