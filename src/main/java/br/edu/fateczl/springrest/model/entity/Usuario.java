package br.edu.fateczl.springrest.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "usuario")
@NamedStoredProcedureQuery(
		name = "Usuario.spValidaLogin",
		procedureName = "sp_valida_login",
		parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "login", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "senha", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
		}
	)
public class Usuario {

	@Id
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "login", nullable = false, length = 8)
	private String login;
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	@Column(name = "senha", nullable = false, length = 8)
	private String senha;
	@ManyToOne(targetEntity = Depto.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "depto", nullable = false)
	private Depto depto;
	
}
