package br.edu.fateczl.springrest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.fateczl.springrest.model.entity.Depto;
import br.edu.fateczl.springrest.repository.DeptoRepository;

@SpringBootTest
class SpringrestApplicationTests {

	@Autowired
	DeptoRepository dRep;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void nomeCompleto() {
		String nome = "Departamento de Informática";
		Depto depto = dRep.fnNomeDepto(1);
		assertEquals(depto.getNome(), nome);
	}
	
	
}
