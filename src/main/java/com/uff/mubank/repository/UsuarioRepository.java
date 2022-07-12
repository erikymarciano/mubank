package com.uff.mubank.repository;

import com.uff.mubank.models.Perfil;
import com.uff.mubank.models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioRepository {
    private long ultimoId;
    private final List<Usuario> listaUsuario;

    public UsuarioRepository(){
        this.ultimoId = 0;
        this.listaUsuario = new ArrayList<>();
    }

    public Usuario salvar(String username, String senha, Perfil perfil) {
        Usuario usuario = new Usuario(this.ultimoId, username, senha, perfil);
        this.ultimoId++;
        this.listaUsuario.add(usuario);
        return usuario;
    }

    public Usuario buscarPorId(Long id){
        for (Usuario usuario: this.listaUsuario){
            if (usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : this.listaUsuario) {
            if (Objects.equals(usuario.getUsername(), username)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario alterar(long id, String senha) {
        for (Usuario usuario : this.listaUsuario) {
            if (usuario.getId() == id) {
                usuario.setSenha(senha);
                return usuario;
            }
        }
        return null;
    }

    public Usuario somarSaldo(long id, double valor) {
        for (Usuario usuario : this.listaUsuario) {
            if (usuario.getId() == id) {
                usuario.somarSaldo(valor);
                return usuario;
            }
        }
        return null;
    }
}
