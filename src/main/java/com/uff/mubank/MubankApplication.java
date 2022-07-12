package com.uff.mubank;

import com.uff.mubank.controller.PerfilController;
import com.uff.mubank.controller.TransacaoController;
import com.uff.mubank.controller.UsuarioController;
import com.uff.mubank.service.PerfilService;
import com.uff.mubank.service.TransacaoService;
import com.uff.mubank.service.UsuarioService;

public class MubankApplication {


	public static void main(String[] args) {
		PerfilService perfilService = new PerfilService();
		UsuarioService usuarioService = new UsuarioService(perfilService);
		TransacaoService transacaoService = new TransacaoService(usuarioService);

		UsuarioController usuarioController = new UsuarioController(usuarioService);
		PerfilController perfilController = new PerfilController(perfilService);
		TransacaoController transacaoController = new TransacaoController(transacaoService);

		usuarioController.criar("andre", "123", "Andre Max");
		perfilController.alterar(usuarioController.buscarPorUsername("andre").getPerfil().getId(), "Andre Barata");
		transacaoService.criarDeposito(usuarioController.buscarPorUsername("andre").getId(), 50);
		transacaoService.criarSaque(usuarioController.buscarPorUsername("andre").getId(), 15);
		transacaoService.criarTransferencia(usuarioController.buscarPorUsername("andre").getId(), usuarioController.buscarPorUsername("andre").getId(), 35);
		System.out.println(transacaoController.listarTransferenciasDoUsuario(usuarioController.buscarPorUsername("andre").getId()));
	}

}
