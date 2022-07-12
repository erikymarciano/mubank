package com.uff.mubank.controller;

import com.uff.mubank.models.Perfil;
import com.uff.mubank.service.PerfilService;

public class PerfilController {
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    public Perfil alterar(long id, String nome) {
        return perfilService.alterar(id, nome);
    }
}
