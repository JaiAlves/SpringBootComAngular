package br.com.jailson.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.jailson.model.Usuario;

/**
 * 
 * @author jailson.alves
 *
 */
public interface UsuarioRepository extends Repository <Usuario, Integer> {

	Usuario save(Usuario usuario);
	 
	Usuario delete(Usuario usuario);
 
	List<Usuario> findAll();
 
	Usuario findOne(Integer id);
	
}
