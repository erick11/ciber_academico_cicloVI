package com.bem.autopart.repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long> {

	@Query("select MAX(m.id) from Compra m")
	public Long obtenerIdUltimaCompra();

	@Query("select m from Compra m where m.fechaCompra between :fechaInicio and :fechaFin")
	public ArrayList<Compra> obtenerComprasRangoFechas(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin);

	@Query("select m from Compra m where m.fechaCompra between :fechaInicio and :fechaFin and"
			+ " (m.cliente.nombre like :clienteNombre or m.cliente.apellido like :clienteNombre)")
	public ArrayList<Compra> obtenerComprasRangoFechasCliente(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin,
			@Param("clienteNombre") String clienteNombre);

	@Query("select m from Compra m where m.direccionEntrega != null and m.estadoEntrega = 0")
	public ArrayList<Compra> obtenerComprasPorEntregar();

	@Query("select m from Compra m where m.cliente.id = :clienteId")
	public ArrayList<Compra> obtenerComprasClienteId( @Param("clienteId") Long clienteId);
	
	@Query("select m from Compra m where m.cliente.id = :clienteId and m.fechaCompra between :fechaInicio and :fechaFin")
	public ArrayList<Compra> obtenerComprasRangoFechasClienteId(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin, @Param("clienteId") Long clienteId);
	
	@Query("select m from Compra m where m.repartidorId = :usuarioId")
	public ArrayList<Compra> obtenerComprasEntregadasUsuarioId( @Param("usuarioId") Long usuarioId);
	
	@Query("select m from Compra m where m.repartidorId = :usuarioId and m.fechaCompra between :fechaInicio and :fechaFin")
	public ArrayList<Compra> obtenerComprasEntregadasRangoFechasUsuarioId(
			@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin, @Param("usuarioId") Long usuarioId);

}
