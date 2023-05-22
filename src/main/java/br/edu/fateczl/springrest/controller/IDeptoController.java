package br.edu.fateczl.springrest.controller;

import org.springframework.http.ResponseEntity;

import br.edu.fateczl.springrest.model.dto.DeptoDTO;

public interface IDeptoController {

	public ResponseEntity<String> nomeDepto(DeptoDTO deptoDTO);
	
}
