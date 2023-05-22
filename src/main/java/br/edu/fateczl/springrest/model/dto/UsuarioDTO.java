package br.edu.fateczl.springrest.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsuarioDTO {

	private int id;
	private String login;
	private String nome;
	private String senha;
	private DeptoDTO depto;
	
}
