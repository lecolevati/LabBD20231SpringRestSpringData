package br.edu.fateczl.springrest.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "depto")
@NamedNativeQuery(
		name = "Depto.fnNomeDepto",
		query = "SELECT codigo, nome FROM fn_nome_depto(?1)",
		resultClass = Depto.class
		)
public class Depto {

	@Id
	@Column(name = "codigo", nullable = false)
	private int codigo;
	@Column(name = "nome", nullable = false, length = 30)
	private String nome;
	
}