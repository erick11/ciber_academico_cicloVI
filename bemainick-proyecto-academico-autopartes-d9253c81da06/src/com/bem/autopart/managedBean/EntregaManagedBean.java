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

import com.bem.autopart.model.Compra;
import com.bem.autopart.model.Usuario;
import com.bem.autopart.service.CompraService;

@ManagedBean
@ViewScoped
public class EntregaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fechaInicio;
	private Date fechaFin;
	private Compra selectedCompra = new Compra();
	private List<Compra> compras = new ArrayList<Compra>();
	private Usuario usuarioLogged = new Usuario();

	@ManagedProperty(value = "#{compraService}")
	private CompraService compraService;

	@PostConstruct
	public void load() {
		compras = compraService.getCompraRepository()
				.obtenerComprasPorEntregar();
		usuarioLogged = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
	}

	public void loadSelectedCompra(Compra c) {
		selectedCompra = c;
	}

	public void entregar() {
		selectedCompra.setEstadoEntrega(1);
		selectedCompra.setRepartidorId(usuarioLogged.getId());
		compraService.getCompraRepository().save(selectedCompra);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Exito", "Se entregó el pedido satisfactoriamente");
		FacesContext.getCurrentInstance().addMessage(null, message);
		compras.remove(selectedCompra);
		selectedCompra = new Compra();
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

	public Compra getSelectedCompra() {
		return selectedCompra;
	}

	public void setSelectedCompra(Compra selectedCompra) {
		this.selectedCompra = selectedCompra;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public CompraService getCompraService() {
		return compraService;
	}

	public void setCompraService(CompraService compraService) {
		this.compraService = compraService;
	}

}
