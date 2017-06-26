package com.bem.autopart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.TipoUsuario;

@Repository
public interface TipoUsuarioRepository extends
		CrudRepository<TipoUsuario, Long> {

	@Query("select t from TipoUsuario t")
	public List<TipoUsuario> obtenerTiposUsuario();

}
