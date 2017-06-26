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
public class MisEntregasManagedBean implements Serializable {

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
		usuarioLogged = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
		compras = compraService.getCompraRepository()
				.obtenerComprasEntregadasUsuarioId(usuarioLogged.getId());
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
		compras = compraService.getCompraRepository()
				.obtenerComprasEntregadasRangoFechasUsuarioId(fechaInicio,
						fechaFin, usuarioLogged.getId());

		if (compras.size() == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia",
					"No se efectuaron compras en ese periodo de tiempo.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void loadSelectedCompra(Compra c) {
		selectedCompra = c;
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

	public Usuario getUsuarioLogged() {
		return usuarioLogged;
	}

	public void setUsuarioLogged(Usuario usuarioLogged) {
		this.usuarioLogged = usuarioLogged;
	}

}
