package com.bem.autopart.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.bem.autopart.model.Autoparte;
import com.bem.autopart.model.Marca;
import com.bem.autopart.model.Modelo;
import com.bem.autopart.service.AutoparteService;
import com.bem.autopart.service.MarcaService;
import com.bem.autopart.service.ModeloService;
import com.google.common.collect.Lists;

@ManagedBean
@ViewScoped
public class AutoparteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Marca> marcas = new ArrayList<Marca>();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private List<Autoparte> autopartes = new ArrayList<Autoparte>();
	private Autoparte deletedAutoparte = new Autoparte();
	private Autoparte modifiedAutoparte = new Autoparte();
	private String selectedMarca = null;
	private String selectedModelo = null;

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

	public void modificar() {
		
		if (modifiedAutoparte.getId() > 0
				&& !modifiedAutoparte.getNombre().equals("")) {
			autoparteService.getAutoparteRepository().save(modifiedAutoparte);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "La autoparte fue modificada exitosamente");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} else {
			modifiedAutoparte.getModelo().setId(
					(new Integer(selectedModelo)).longValue());
			Autoparte savedAutoparte = autoparteService
					.getAutoparteRepository().save(modifiedAutoparte);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "Se creo autoparte con exito");
			FacesContext.getCurrentInstance().addMessage(null, message);
			autopartes.add(savedAutoparte);
		}

		modifiedAutoparte = new Autoparte();
	}

	public void eliminar() {
		try {
			autoparteService.getAutoparteRepository().delete(deletedAutoparte);
			autopartes.remove(deletedAutoparte);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "La autoparte fue eliminada");
			FacesContext.getCurrentInstance().addMessage(null, message);
			deletedAutoparte = new Autoparte();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error",
					"No se puede eliminar autoparte por que tiene compras registradas");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void loadDeletedAutoparte(Autoparte c) {
		deletedAutoparte = c;
	}

	public void loadModifiedAutoparte(Autoparte c) {
		modifiedAutoparte = c;
	}

	public void loadCrearAutoparte() {
		modifiedAutoparte = new Autoparte();
		if (selectedMarca == null || selectedMarca.equals("")) {
			RequestContext.getCurrentInstance().addCallbackParam("notValid",
					true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia", "Seleccione una marca");
			FacesContext.getCurrentInstance().addMessage(null, message);

		}
		if (selectedModelo == null || selectedModelo.equals("")) {
			RequestContext.getCurrentInstance().addCallbackParam("notValid",
					true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia", "Seleccione un modelo");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void cargarModelos() {
		System.out.println(selectedMarca);
		if (selectedMarca != null && !selectedMarca.equals("")) {
			modelos = modeloService.getModeloRepository()
					.obtenerModeloPorMarca(Long.parseLong(selectedMarca));
			if (modelos.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron modelos");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			modelos = new ArrayList<Modelo>();
			autopartes = new ArrayList<Autoparte>();
		}
	}

	public void cargarAutopartes() {
		if (selectedModelo != null && !selectedModelo.equals("")) {
			autopartes = autoparteService.getAutoparteRepository()
					.obtenerAutopartePorModelo(Long.parseLong(selectedModelo));
			if (autopartes.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron autopartes");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			autopartes = new ArrayList<Autoparte>();
		}
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

	public Autoparte getDeletedAutoparte() {
		return deletedAutoparte;
	}

	public void setDeletedAutoparte(Autoparte deletedAutoparte) {
		this.deletedAutoparte = deletedAutoparte;
	}

	public Autoparte getModifiedAutoparte() {
		return modifiedAutoparte;
	}

	public void setModifiedAutoparte(Autoparte modifiedAutoparte) {
		this.modifiedAutoparte = modifiedAutoparte;
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

}
