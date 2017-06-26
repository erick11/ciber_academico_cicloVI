package com.bem.autopart.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.bem.autopart.model.Autoparte;
import com.bem.autopart.service.CompraService;

@ManagedBean
@ViewScoped
public class ReporteMasVendidasManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fechaInicio;
	private Date fechaFin;
	private List<Autoparte> autopartes = new ArrayList<Autoparte>();
	private List<Object[]> lista = new ArrayList<Object[]>();

	@ManagedProperty(value = "#{compraService}")
	private CompraService compraService;

	@PostConstruct
	public void load() {

	}

	public void buscar() {
		if (fechaFin == null || fechaInicio == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia", "Seleccione fecha de inicio y fecha de fin");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		if (!fechaInicio.before(fechaFin)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia",
					"Fecha de fin no puede ser previa a la fecha de inicio");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return;
		}
		lista = compraService.getDetalleCompraRepository().obtenerMasVendidos(
				fechaInicio, fechaFin);
		if (lista.size() == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia",
					"No hubo compras registradas en este periodo de tiempo");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Autoparte> getAutopartes() {
		return autopartes;
	}

	public void setAutopartes(List<Autoparte> autopartes) {
		this.autopartes = autopartes;
	}

	public CompraService getCompraService() {
		return compraService;
	}

	public void setCompraService(CompraService compraService) {
		this.compraService = compraService;
	}

	public List<Object[]> getLista() {
		return lista;
	}

	public void setLista(List<Object[]> lista) {
		this.lista = lista;
	}

}
