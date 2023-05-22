package br.edu.fateczl.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fateczl.springrest.model.entity.Depto;

public interface DeptoRepository extends JpaRepository<Depto, Integer>{
	Depto fnNomeDepto(int cod);
}
