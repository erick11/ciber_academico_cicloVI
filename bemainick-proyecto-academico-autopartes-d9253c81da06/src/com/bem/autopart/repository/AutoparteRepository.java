package com.bem.autopart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.Autoparte;

@Repository
public interface AutoparteRepository extends CrudRepository<Autoparte, Long> {

	@Query("select m from Autoparte m where m.modelo.id = :idModelo")
	public List<Autoparte> obtenerAutopartePorModelo(
			@Param("idModelo") Long idModelo);

	@Query("select m from Autoparte m where m.modelo.id = :idModelo and m.stock > 5")
	public List<Autoparte> obtenerAutopartePorModeloConStock(
			@Param("idModelo") Long idModelo);
}
