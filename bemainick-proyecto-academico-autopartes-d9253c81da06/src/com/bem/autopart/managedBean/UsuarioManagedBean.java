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

import com.bem.autopart.model.TipoUsuario;
import com.bem.autopart.model.Usuario;
import com.bem.autopart.service.TipoUsuarioService;
import com.bem.autopart.service.UsuarioService;
import com.google.common.collect.Lists;

@ManagedBean
@ViewScoped
public class UsuarioManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuarioNombre = null;
	private String selectedTipoUsuario = null;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private Usuario deletedUsuario = new Usuario();
	private Usuario modifiedUsuario = new Usuario();
	private List<TipoUsuario> tiposUsuario = new ArrayList<TipoUsuario>();

	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;

	@ManagedProperty(value = "#{tipoUsuarioService}")
	private TipoUsuarioService tipoUsuarioService;

	@PostConstruct
	public void load() {
		tiposUsuario = Lists.newArrayList(tipoUsuarioService
				.getTipoUsuarioRepository().obtenerTiposUsuario());

	}

	public void modificar() {
		System.out.println(modifiedUsuario.getId() + "");
		if (modifiedUsuario.getId() > 0
				&& !modifiedUsuario.getNombre().equals("")) {
			usuarioService.getUsuarioRepository().save(modifiedUsuario);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "El usuario fue modificado exitosamente");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} else {
			modifiedUsuario.getTipoUsuario().setId(
					(new Integer(selectedTipoUsuario)).longValue());
			Usuario usuarioSaved = usuarioService.getUsuarioRepository().save(
					modifiedUsuario);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "Se creo usuario con exito");
			FacesContext.getCurrentInstance().addMessage(null, message);
			usuarios.add(usuarioSaved);
		}

		modifiedUsuario = new Usuario();
	}

	public void eliminar() {
		usuarioService.getUsuarioRepository().delete(deletedUsuario);
		usuarios.remove(deletedUsuario);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Atencion", "El usuario fue eliminado");
		FacesContext.getCurrentInstance().addMessage(null, message);
		deletedUsuario = new Usuario();
	}

	public void loadDeletedUsuario(Usuario c) {
		deletedUsuario = c;
	}

	public void loadModifiedUsuario(Usuario c) {
		modifiedUsuario = c;
		modifiedUsuario.getTipoUsuario().setId(c.getTipoUsuario().getId());
		selectedTipoUsuario = c.getTipoUsuario().getId().toString();
	}

	public void loadCrearUsuario() {
		modifiedUsuario = new Usuario();
		tiposUsuario = Lists.newArrayList(tipoUsuarioService
				.getTipoUsuarioRepository().obtenerTiposUsuario());
	}

	public void buscarUsuarios() {
		if (usuarioNombre != null && !usuarioNombre.equals("")) {
			usuarios = usuarioService.getUsuarioRepository()
					.obtenerUsuarioPorNombre("%" + usuarioNombre + "%");
			if (usuarios.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron usuarios");
				FacesContext.getCurrentInstance().addMessage(null, message);
				usuarios = new ArrayList<Usuario>();
			}
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERROR",
					"Ingrese nombre de usuario");
			FacesContext.getCurrentInstance().addMessage(null, message);
			usuarios = new ArrayList<Usuario>();
		}
	}

	public String getUsuarioNombre() {
		return usuarioNombre;
	}

	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}

	public String getSelectedTipoUsuario() {
		return selectedTipoUsuario;
	}

	public void setSelectedTipoUsuario(String selectedTipoUsuario) {
		this.selectedTipoUsuario = selectedTipoUsuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getDeletedUsuario() {
		return deletedUsuario;
	}

	public void setDeletedUsuario(Usuario deletedUsuario) {
		this.deletedUsuario = deletedUsuario;
	}

	public Usuario getModifiedUsuario() {
		return modifiedUsuario;
	}

	public void setModifiedUsuario(Usuario modifiedUsuario) {
		this.modifiedUsuario = modifiedUsuario;
	}

	public List<TipoUsuario> getTiposUsuario() {
		return tiposUsuario;
	}

	public void setTiposUsuario(List<TipoUsuario> tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public TipoUsuarioService getTipoUsuarioService() {
		return tipoUsuarioService;
	}

	public void setTipoUsuarioService(TipoUsuarioService tipoUsuarioService) {
		this.tipoUsuarioService = tipoUsuarioService;
	}

}
