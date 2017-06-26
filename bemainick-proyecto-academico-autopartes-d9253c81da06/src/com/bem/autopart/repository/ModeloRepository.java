package com.bem.autopart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.Modelo;

@Repository
public interface ModeloRepository extends CrudRepository<Modelo, Long> {

	@Query("select m from Modelo m"
			+ " where m.nombre like :nombre and m.marca.id = :idMarca")
	public List<Modelo> obtenerModeloPorNombrePorMarca(
			@Param("nombre") String nombre, @Param("idMarca") Long idMarca);
	
	@Query("select m from Modelo m"
			+ " where m.marca.id = :idMarca")
	public List<Modelo> obtenerModeloPorMarca(
			@Param("idMarca") Long idMarca);

}
