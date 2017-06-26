package com.bem.autopart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query("select c from Cliente c"
			+ " where c.nombre like :nombre or c.apellido like :nombre")
	public List<Cliente> obtenerClientePorNombreApellido(
			@Param("nombre") String nombre);
	
	@Query("select c from Cliente c"
			+ " where c.usuario.id = :userId")
	public Cliente obtenerClientePorUsuarioId(
			@Param("userId") Long userId);
}
