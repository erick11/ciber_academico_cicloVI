package com.bem.autopart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.Marca;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Long> {
	
	@Query("select m from Marca m"
			+ " where m.nombre like :nombre")
	public List<Marca> obtenerMarcaPorNombre(
			@Param("nombre") String nombre);
	
	@Query("select m from Marca m")
	public List<Marca> obtenerMarcas();

}
