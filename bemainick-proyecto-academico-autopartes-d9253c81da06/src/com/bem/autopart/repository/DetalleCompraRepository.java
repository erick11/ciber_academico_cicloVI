package com.bem.autopart.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.DetalleCompra;

@Repository
public interface DetalleCompraRepository extends
		CrudRepository<DetalleCompra, Long> {

	@Query("select m, m.autoparteId, sum(m.cantidad) from DetalleCompra m"
			+ " where m.compra.fechaCompra between :fechaInicio and :fechaFin  GROUP BY m.autoparteId")
	public List<Object[]> obtenerMasVendidos(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin);

}
