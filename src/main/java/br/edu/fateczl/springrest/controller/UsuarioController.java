package br.edu.fateczl.springrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateczl.springrest.model.dto.DeptoDTO;
import br.edu.fateczl.springrest.model.dto.UsuarioDTO;
import br.edu.fateczl.springrest.model.entity.Depto;
import br.edu.fateczl.springrest.model.entity.Usuario;
import br.edu.fateczl.springrest.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UsuarioController implements ICRUDController<UsuarioDTO>, IUsuarioController {

	@Autowired
	UsuarioRepository uRep;
	

	@Override
	@GetMapping("/usuario")
	public List<UsuarioDTO> lista() {
		List<Usuario> lista = uRep.findAll();
		List<UsuarioDTO> listaDTO = converteListaUsuarioListaUsuarioDTO(lista);
		return listaDTO;
	}
	
	@Override
	@GetMapping("/usuario/validaLogin")
	public ResponseEntity<Integer> validaLogin(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Integer id = 0;
		id = uRep.spValidaLogin(usuarioDTO.getLogin(), usuarioDTO.getSenha());
		return ResponseEntity.ok().body(id);
	}

	@Override
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<UsuarioDTO> busca(@PathVariable(name = "usuarioId") int usuarioId) {
		Usuario usuario = uRep.findById(usuarioId).orElseThrow(() -> new ResourceNotFoundException("ID invalido"));
		UsuarioDTO usuarioDTO = converteUsuarioUsuarioDTO(usuario);
		return ResponseEntity.ok().body(usuarioDTO);
	}

	@Override
	@PostMapping("/usuario")
	public ResponseEntity<String> adiciona(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = converteUsuarioDTOUsuario(usuarioDTO);
		uRep.save(usuario);
		String saida = "Usuario adicionado com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@Override
	@PutMapping("/usuario")
	public ResponseEntity<String> atualiza(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = converteUsuarioDTOUsuario(usuarioDTO);
		uRep.save(usuario);
		String saida = "Usuario atualizado com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@Override
	@DeleteMapping("/usuario")
	public ResponseEntity<String> exclui(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = converteUsuarioDTOUsuario(usuarioDTO);
		uRep.delete(usuario);
		String saida = "Usuario excluido com sucesso";
		return ResponseEntity.ok().body(saida);
	}
	
	private List<UsuarioDTO> converteListaUsuarioListaUsuarioDTO(List<Usuario> listaUsuario) {
		List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();
		for (Usuario usuario : listaUsuario) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			DeptoDTO deptoDTO = new DeptoDTO();
			
			deptoDTO.setCodigo(usuario.getDepto().getCodigo());
			deptoDTO.setNome(usuario.getDepto().getNome());
			
			usuarioDTO.setDepto(deptoDTO);
			usuarioDTO.setId(usuario.getId());
			usuarioDTO.setNome(usuario.getNome());
			
			listaUsuarioDTO.add(usuarioDTO);
		}
		return listaUsuarioDTO;
	}
	
	private UsuarioDTO converteUsuarioUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		DeptoDTO deptoDTO = new DeptoDTO();
		
		deptoDTO.setCodigo(usuario.getDepto().getCodigo());
		deptoDTO.setNome(usuario.getDepto().getNome());
		
		usuarioDTO.setDepto(deptoDTO);
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNome(usuario.getNome());
		
		return usuarioDTO;
	}
	
	private Usuario converteUsuarioDTOUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		Depto depto = new Depto();
		
		depto.setCodigo(usuarioDTO.getDepto().getCodigo());
		depto.setNome(usuarioDTO.getDepto().getNome());
		
		usuario.setDepto(depto);
		usuario.setId(usuarioDTO.getId());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setLogin(usuarioDTO.getLogin());
		usuario.setSenha(usuarioDTO.getSenha());
		
		return usuario;
	}


}
