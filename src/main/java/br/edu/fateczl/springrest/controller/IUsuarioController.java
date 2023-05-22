package br.edu.fateczl.springrest.controller;

import org.springframework.http.ResponseEntity;

import br.edu.fateczl.springrest.model.dto.UsuarioDTO;

public interface IUsuarioController {
	
	public ResponseEntity<Integer> validaLogin(UsuarioDTO usuarioDTO);

}
