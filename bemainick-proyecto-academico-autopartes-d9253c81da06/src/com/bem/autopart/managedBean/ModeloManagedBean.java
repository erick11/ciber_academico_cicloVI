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

import com.bem.autopart.model.Marca;
import com.bem.autopart.model.Modelo;
import com.bem.autopart.service.MarcaService;
import com.bem.autopart.service.ModeloService;
import com.google.common.collect.Lists;

@ManagedBean
@ViewScoped
public class ModeloManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String modeloNombre = null;
	private List<Marca> marcas = new ArrayList<Marca>();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private Modelo deletedModelo = new Modelo();
	private Modelo modifiedModelo = new Modelo();
	private String selectedMarca = null;

	@ManagedProperty(value = "#{marcaService}")
	private MarcaService marcaService;

	@ManagedProperty(value = "#{modeloService}")
	private ModeloService modeloService;

	@PostConstruct
	public void load() {
		marcas = Lists.newArrayList(marcaService.getMarcaRepository()
				.obtenerMarcas());
	}

	public void modificar() {
		if (modifiedModelo.getId() > 0
				&& !modifiedModelo.getNombre().equals("")) {
			modeloService.getModeloRepository().save(modifiedModelo);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "El modelo fue modificado exitosamente");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} else {

			modifiedModelo.getMarca().setId(
					(new Integer(selectedMarca)).longValue());
			Modelo modeloSaved = modeloService.getModeloRepository().save(
					modifiedModelo);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "Se creo modelo con exito");
			FacesContext.getCurrentInstance().addMessage(null, message);
			modelos.add(modeloSaved);

		}

		modifiedModelo = new Modelo();
	}

	public void eliminar() {
		try {
			modeloService.getModeloRepository().delete(deletedModelo);
			modelos.remove(deletedModelo);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion",
					"El modelo fue eliminada con todas sus autopartes");
			FacesContext.getCurrentInstance().addMessage(null, message);
			deletedModelo = new Modelo();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error al eliminar",
					"Hay compras registradas relacionadas a este modelo");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void loadDeletedModelo(Modelo c) {
		deletedModelo = c;
	}

	public void loadModifiedModelo(Modelo c) {
		modifiedModelo = c;
	}

	public void loadCrearModelo() {
		modifiedModelo = new Modelo();
		if (selectedMarca == null || selectedMarca.equals("")) {
			RequestContext.getCurrentInstance().addCallbackParam("notValid",
					true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia", "Seleccione una marca");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void cargarModelos() {
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
		}
	}

	public String getModeloNombre() {
		return modeloNombre;
	}

	public void setModeloNombre(String modeloNombre) {
		this.modeloNombre = modeloNombre;
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

	public Modelo getDeletedModelo() {
		return deletedModelo;
	}

	public void setDeletedModelo(Modelo deletedModelo) {
		this.deletedModelo = deletedModelo;
	}

	public Modelo getModifiedModelo() {
		return modifiedModelo;
	}

	public void setModifiedModelo(Modelo modifiedModelo) {
		this.modifiedModelo = modifiedModelo;
	}

	public String getSelectedMarca() {
		return selectedMarca;
	}

	public void setSelectedMarca(String selectedMarca) {
		this.selectedMarca = selectedMarca;
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

}
