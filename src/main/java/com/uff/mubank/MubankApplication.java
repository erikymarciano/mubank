package com.uff.mubank;

import com.uff.mubank.controller.PerfilController;
import com.uff.mubank.controller.UsuarioController;
import com.uff.mubank.service.PerfilService;
import com.uff.mubank.service.UsuarioService;

public class MubankApplication {


	public static void main(String[] args) {
		PerfilService perfilService = new PerfilService();
		UsuarioService usuarioService = new UsuarioService(perfilService);


		UsuarioController usuarioController = new UsuarioController(usuarioService);
		PerfilController perfilController = new PerfilController(perfilService);

		usuarioController.criar("andre", "123", "Andre Max");
		perfilController.alterar(usuarioController.buscarPorUsername("andre").getPerfil().getId(), "Andre Barata");
		System.out.println(usuarioController.buscarPorUsername("andre").getPerfil().getName());
	}

}
