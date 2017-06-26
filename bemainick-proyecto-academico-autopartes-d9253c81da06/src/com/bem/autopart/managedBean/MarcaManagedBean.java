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

import com.bem.autopart.model.Marca;
import com.bem.autopart.service.MarcaService;
import com.google.common.collect.Lists;

@ManagedBean
@ViewScoped
public class MarcaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String marcaNombre = null;
	private List<Marca> marcas = new ArrayList<Marca>();
	private Marca deletedMarca = new Marca();
	private Marca modifiedMarca = new Marca();

	@ManagedProperty(value = "#{marcaService}")
	private MarcaService marcaService;

	@PostConstruct
	public void load() {
		marcas = Lists.newArrayList(marcaService.getMarcaRepository()
				.obtenerMarcas());
	}

	public void modificar() {
		System.out.println(modifiedMarca.getId() + "");
		if (modifiedMarca.getId() > 0 && !modifiedMarca.getNombre().equals("")) {
			marcaService.getMarcaRepository().save(modifiedMarca);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "La marca fue modificado exitosamente");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} else {
			Marca marcaSaved = marcaService.getMarcaRepository().save(
					modifiedMarca);
			marcas.add(marcaSaved);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "Se creo marca con exito");
			FacesContext.getCurrentInstance().addMessage(null, message);

		}

		modifiedMarca = new Marca();
	}

	public void eliminar() {
		try {
			marcaService.getMarcaRepository().delete(deletedMarca);
			marcas.remove(deletedMarca);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion",
					"La marca fue eliminada con todas sus autopartes");
			FacesContext.getCurrentInstance().addMessage(null, message);
			deletedMarca = new Marca();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "Error al eliminar",
					"Hay compras registradas relacionadas a esta marca");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void loadDeletedMarca(Marca c) {
		deletedMarca = c;
	}

	public void loadModifiedMarca(Marca c) {
		modifiedMarca = c;
	}

	public void loadCrearMarca() {
		modifiedMarca = new Marca();
	}

	public void buscarMarcas() {
		if (marcaNombre != null && !marcaNombre.equals("")) {
			marcas = marcaService.getMarcaRepository().obtenerMarcaPorNombre(
					"%" + marcaNombre + "%");
			if (marcas.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron marcas");
				FacesContext.getCurrentInstance().addMessage(null, message);
				marcas = new ArrayList<Marca>();
			}
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERROR",
					"Ingrese nombre de la marca");
			FacesContext.getCurrentInstance().addMessage(null, message);
			marcas = new ArrayList<Marca>();
		}
	}

	public String getMarcaNombre() {
		return marcaNombre;
	}

	public void setMarcaNombre(String marcaNombre) {
		this.marcaNombre = marcaNombre;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public Marca getDeletedMarca() {
		return deletedMarca;
	}

	public void setDeletedMarca(Marca deletedMarca) {
		this.deletedMarca = deletedMarca;
	}

	public Marca getModifiedMarca() {
		return modifiedMarca;
	}

	public void setModifiedMarca(Marca modifiedMarca) {
		this.modifiedMarca = modifiedMarca;
	}

	public MarcaService getMarcaService() {
		return marcaService;
	}

	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}

}
