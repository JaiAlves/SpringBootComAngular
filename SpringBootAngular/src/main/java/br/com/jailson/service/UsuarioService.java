package br.com.jailson.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jailson.model.Usuario;
import br.com.jailson.repository.UsuarioRepository;

/**
 * 
 * @author jailson.alves
 *
 */
@RestController
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	/**
	 * Salva um registro
	 * @param usuario
	 * @return ResponseEntity
	 */
	@RequestMapping(value="/usuario", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
 		try {
 			return new ResponseEntity<Usuario>(this.usuarioRepository.save(usuario), HttpStatus.CREATED);
 		}catch(Exception e) {
 			return new ResponseEntity<String>("Não foi possivel adicionar."+e, HttpStatus.BAD_REQUEST);			
		}
	}
	
	/**
	 * Atualiza um usuário
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value="/usuario", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?>  atualizar(@RequestBody Usuario usuario) {
 		try {
 			return new ResponseEntity<Usuario>(this.usuarioRepository.save(usuario), HttpStatus.OK);
 		}catch(Exception e) {
 			return new ResponseEntity<String>("Não foi adicionar. Usuario não deserializado.", HttpStatus.BAD_REQUEST);			
		}
		
	}
	
	/**
	 * Consultar todos
	 * @return
	 */
	@RequestMapping(value="/usuarios", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Usuario>>  consultarTodos() {
 	    List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
	    
	    if (list==null || list.size()==0) {
	    	return new ResponseEntity<List<Usuario>>(list, HttpStatus.NOT_FOUND);
	    }
	    
	    return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
		
	}	
	
	/**
	 * Buscar pelo id
	 * @param id usuario
	 * @return
	 */
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Usuario buscar(@PathVariable("id") Integer id) {
 
		return this.usuarioRepository.findOne(id);
	}
	
	/***
	 * Excluir um usuario
	 * @param id usuario
	 * @return
	 */
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?>  excluir(@PathVariable("id") Integer id) {
 
		Usuario usuario = usuarioRepository.findOne(id);
		String msgErro = "excluir(): Não foi possivel apagar. Usuario com id "+id+" não encontrado.";
 
		if (usuario==null) {
			log.error(msgErro);
			return new ResponseEntity<String>(msgErro, HttpStatus.NOT_FOUND);
		}
		
		try {
			usuarioRepository.delete(usuario);
 
			return new ResponseEntity<Usuario>(HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(msgErro, HttpStatus.BAD_REQUEST);
		}
	}	
	
}
