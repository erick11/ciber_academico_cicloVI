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
import com.bem.autopart.model.Marca;
import com.bem.autopart.model.Modelo;
import com.bem.autopart.model.Movimiento;
import com.bem.autopart.service.AutoparteService;
import com.bem.autopart.service.MarcaService;
import com.bem.autopart.service.ModeloService;
import com.bem.autopart.service.MovimientoService;
import com.google.common.collect.Lists;

@ManagedBean
@ViewScoped
public class ReporteMovimientoManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fechaInicio;
	private Date fechaFin;
	private List<Marca> marcas = new ArrayList<Marca>();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private List<Autoparte> autopartes = new ArrayList<Autoparte>();
	private String selectedMarca = null;
	private String selectedModelo = null;
	private String selectedAutoparte = null;
	private List<Movimiento> movimientos = new ArrayList<Movimiento>();
	private boolean checked;

	@ManagedProperty(value = "#{movimientoService}")
	private MovimientoService movimientoService;

	@ManagedProperty(value = "#{marcaService}")
	private MarcaService marcaService;

	@ManagedProperty(value = "#{modeloService}")
	private ModeloService modeloService;

	@ManagedProperty(value = "#{autoparteService}")
	private AutoparteService autoparteService;

	@PostConstruct
	public void load() {
		marcas = Lists.newArrayList(marcaService.getMarcaRepository()
				.obtenerMarcas());
	}

	public void buscar() {
		if (fechaFin == null || fechaInicio == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia",
					"Seleccione fecha de inicio y fecha de fin");
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
		if (checked) {
			if (selectedAutoparte == null || selectedAutoparte.equals("")) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"Seleccione autoparte");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				movimientos = movimientoService.getMovimientoRepository()
						.obtenerMovimientosRangoFechasAutoparte(fechaInicio,
								fechaFin, Long.parseLong(selectedAutoparte));
			}

		} else {
			movimientos = movimientoService.getMovimientoRepository()
					.obtenerMovimientosRangoFechas(fechaInicio, fechaFin);
		}

		if (movimientos.size() == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia",
					"No se efectuaron movimientos en ese periodo de tiempo.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void cargarModelos() {
		if (selectedMarca != null && !selectedMarca.equals("")) {
			modelos = modeloService.getModeloRepository()
					.obtenerModeloPorMarca(Long.parseLong(selectedMarca));
			selectedModelo = "";
			if (modelos.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron modelos");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			modelos = new ArrayList<Modelo>();

		}

	}

	public void cargarAutopartes() {
		if (selectedModelo != null && !selectedModelo.equals("")) {
			autopartes = autoparteService.getAutoparteRepository()
					.obtenerAutopartePorModeloConStock(
							Long.parseLong(selectedModelo));
			if (autopartes.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron autopartes");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			modelos = new ArrayList<Modelo>();

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

	public MovimientoService getMovimientoService() {
		return movimientoService;
	}

	public void setMovimientoService(MovimientoService movimientoService) {
		this.movimientoService = movimientoService;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public List<Autoparte> getAutopartes() {
		return autopartes;
	}

	public void setAutopartes(List<Autoparte> autopartes) {
		this.autopartes = autopartes;
	}

	public String getSelectedMarca() {
		return selectedMarca;
	}

	public void setSelectedMarca(String selectedMarca) {
		this.selectedMarca = selectedMarca;
	}

	public String getSelectedModelo() {
		return selectedModelo;
	}

	public void setSelectedModelo(String selectedModelo) {
		this.selectedModelo = selectedModelo;
	}

	public String getSelectedAutoparte() {
		return selectedAutoparte;
	}

	public void setSelectedAutoparte(String selectedAutoparte) {
		this.selectedAutoparte = selectedAutoparte;
	}

	public MarcaService getMarcaService() {
		return marcaService;
	}

	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}

	public ModeloService getModeloService() {
		return modeloService;
	}

	public void setModeloService(ModeloService modeloService) {
		this.modeloService = modeloService;
	}

	public AutoparteService getAutoparteService() {
		return autoparteService;
	}

	public void setAutoparteService(AutoparteService autoparteService) {
		this.autoparteService = autoparteService;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
