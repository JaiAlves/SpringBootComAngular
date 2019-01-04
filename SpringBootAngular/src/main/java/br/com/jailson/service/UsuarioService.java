package br.com.jailson.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jailson.model.MenssagemRetorno;
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
	@RequestMapping(value="/usuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
		log.info("Criar usuario ");
		
 		try {
 			usuario.setIdUsuario(null);
 			
 			Usuario usu = this.usuarioRepository.save(usuario);
 			
 			MenssagemRetorno retorno = new MenssagemRetorno();
			retorno.setCodigoRetorno(MenssagemRetorno.OK);
			retorno.setMenssagemRetorno("Usuário criado com sucesso!");
			retorno.setObjetoRetorno(usu);
		
 			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.CREATED);
 		}catch(Exception e) {
 			MenssagemRetorno retorno = new MenssagemRetorno();
			retorno.setCodigoRetorno(MenssagemRetorno.ERRO_CHAMADA);
			retorno.setMenssagemRetorno("Não foi possivel adicionar."+e.getMessage());
 			
 			log.error("Erro ao tentar add usuário",e);
 			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.BAD_REQUEST);			
		}
	}
	
	/**
	 * Atualiza um usuário
	 * @param usuario
	 * @return
	 */
	@PutMapping(value="/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
		log.info("Atualizar usuario ");
 		try {
 			MenssagemRetorno retorno = new MenssagemRetorno();

			if (usuario == null) {
				retorno.setCodigoRetorno(MenssagemRetorno.ERRO_CHAMADA);
		    	retorno.setMenssagemRetorno("Usuário não informado!");
		    	
		    	return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.BAD_REQUEST);
			}
 			
 			Usuario usu = this.usuarioRepository.save(usuario);
 			
 			retorno.setCodigoRetorno(MenssagemRetorno.OK);
 			retorno.setMenssagemRetorno("Usuário atualizado com sucesso!");
 			retorno.setObjetoRetorno(usu);
 			
 			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.OK);
 		}catch(Exception e) {
 			MenssagemRetorno retorno = new MenssagemRetorno();
			retorno.setCodigoRetorno(MenssagemRetorno.ERRO_CHAMADA);
			retorno.setMenssagemRetorno("Não foi possivel alterar usuario."+e.getMessage());
			
 			log.error("Erro ao tentar add usuário",e);
 			e.printStackTrace();
 			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.BAD_REQUEST);			
		}
		
	}
	
	/**
	 * Consultar todos
	 * @return
	 */
	@RequestMapping(value="/usuarios", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MenssagemRetorno> consultarTodos() {
		List<Usuario> list = null;
		try {
 	    	list = (List<Usuario>) usuarioRepository.findAll();
 	    	
 		    if (list==null || list.size()==0) {
 		    	MenssagemRetorno retorno = new MenssagemRetorno();
 		    	retorno.setCodigoRetorno(MenssagemRetorno.OK);
 		    	retorno.setMenssagemRetorno("Nenhum registro encontrado!");
 		    	
 		    	return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.NOT_FOUND);
 		    }
 		    
 			MenssagemRetorno retorno = new MenssagemRetorno();
 			retorno.setCodigoRetorno(MenssagemRetorno.OK);
 			retorno.setMenssagemRetorno("lista obtida com sucesso!");
 			retorno.setObjetoRetorno(list);
 		    
 		    return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.OK); 	    	
		} catch (Exception e) {
 			MenssagemRetorno retorno = new MenssagemRetorno();
			retorno.setCodigoRetorno(MenssagemRetorno.ERRO_CHAMADA);
			retorno.setMenssagemRetorno("Não foi possivel obter a lista de usuarios."+e.getMessage());
			
 			log.error("Erro ao tentar obter lista de usuarios",e);
 			e.printStackTrace();
 			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.BAD_REQUEST);			
		}
	    

	}	
	
	/**
	 * Buscar pelo id
	 * @param id usuario
	 * @return
	 */
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MenssagemRetorno> buscar(@PathVariable("id") Integer id) {
		
		try {
	    	MenssagemRetorno retorno = new MenssagemRetorno();
	    	retorno.setCodigoRetorno(MenssagemRetorno.OK);
	    	retorno.setMenssagemRetorno("Usuário encontrado com sucesso!");
		
			Usuario usu = this.usuarioRepository.findOne(id);
			
			if (usu == null) {
		    	retorno.setMenssagemRetorno("Nenhum registro encontrado!");
		    	
		    	return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.NOT_FOUND);
			}
			retorno.setObjetoRetorno(usu);
			
			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.OK);
		} catch(Exception e) {
 			MenssagemRetorno retorno = new MenssagemRetorno();
			retorno.setCodigoRetorno(MenssagemRetorno.ERRO_CHAMADA);
			retorno.setMenssagemRetorno("Não foi possivel obter o usuario parao id "+id+e.getMessage());
			
 			log.error("Erro ao tentar obter o usuario para o id "+id,e);
 			e.printStackTrace();
 			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.BAD_REQUEST);			
			
		}
	}
	
	/***
	 * Excluir um usuario
	 * @param id usuario
	 * @return
	 */
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?>  excluir(@PathVariable("id") Integer id) {
 
		MenssagemRetorno retorno = new MenssagemRetorno();
		
		Usuario usuario = usuarioRepository.findOne(id);
		String msgErro = "Não foi possivel apagar. Usuario com id "+id+" não encontrado.";
 
		if (usuario==null) {
			retorno.setCodigoRetorno(MenssagemRetorno.ERRO_CHAMADA);
			retorno.setMenssagemRetorno(msgErro);
			
			log.error(msgErro);
			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.NOT_FOUND);
		}
		
		try {
			usuarioRepository.delete(usuario);

			retorno.setCodigoRetorno(MenssagemRetorno.OK);
			retorno.setMenssagemRetorno("Usuario excluido com sucesso!");
			
			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.OK);
		} catch(Exception e) {
			retorno.setCodigoRetorno(MenssagemRetorno.ERRO_SERVER);
			retorno.setMenssagemRetorno(msgErro+e.getMessage());			
			
			e.printStackTrace();
			return new ResponseEntity<MenssagemRetorno>(retorno, HttpStatus.NOT_FOUND);
		}
	}	
	
}
