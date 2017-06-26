package com.bem.autopart.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.Movimiento;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

	@Query("select m from Movimiento m where m.fecha between :fechaInicio and :fechaFin")
	public ArrayList<Movimiento> obtenerMovimientosRangoFechas(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin);

	@Query("select m from Movimiento m where m.fecha between :fechaInicio and :fechaFin"
			+ " and m.autoparte.id = :autoparteId")
	public ArrayList<Movimiento> obtenerMovimientosRangoFechasAutoparte(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin,
			@Param("autoparteId") Long autoparteId);

}
