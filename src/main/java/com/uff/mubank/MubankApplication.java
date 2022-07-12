package com.uff.mubank;

import com.uff.mubank.controller.UsuarioController;

public class MubankApplication {

	public static void main(String[] args) {
		UsuarioController usuarioController = new UsuarioController();
		usuarioController.criar("andre", "123", "Andre Max");
		System.out.println(usuarioController.logar("andre", "123"));
	}

}
