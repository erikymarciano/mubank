package com.uff.mubank.repository;

import com.uff.mubank.models.Perfil;

import java.util.ArrayList;
import java.util.List;

public class PerfilRepository {
    private long ultimoId;
    private List<Perfil> listaPerfil;

    public PerfilRepository() {
        this.ultimoId = 0;
        this.listaPerfil = new ArrayList<>();
    }

    public Perfil salvar(String nome) {
        Perfil perfil = new Perfil(this.ultimoId, nome);
        this.ultimoId++;
        listaPerfil.add(perfil);
        return perfil;
    }

    public Perfil buscarPorId(Long id) {
        for (Perfil perfil : this.listaPerfil) {
            if (perfil.getId() == id) {
                return perfil;
            }
        }
        return null;
    }

    public Perfil alterar(Long id, String nome) {
        for (Perfil perfil : this.listaPerfil) {
            if (perfil.getId() == id) {
                perfil.setName(nome);
                return perfil;
            }
        }
        return null;
    }
}
