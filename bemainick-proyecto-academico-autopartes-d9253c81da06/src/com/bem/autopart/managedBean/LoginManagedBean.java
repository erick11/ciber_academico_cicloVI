package com.bem.autopart.managedBean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.bem.autopart.model.Cliente;
import com.bem.autopart.model.Usuario;
import com.bem.autopart.service.ClienteService;
import com.bem.autopart.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario;
	private String contrasena;
	private Usuario loggedUsuario = new Usuario();

	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;

	@ManagedProperty(value = "#{clienteService}")
	private ClienteService clienteService;

	@PostConstruct
	public void load() {

	}

	public String autenticar() {
		loggedUsuario = usuarioService.getUsuarioRepository()
				.obtenerUsuarioLogged(usuario, contrasena);
		if (loggedUsuario != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Ingreso", "Usuario autenticado correctamente");
			// Si es cliente, ponemos al cliente en session, si no se consulta el cliente por defecto (admin)
			Cliente clienteLogged = new Cliente();
			if (loggedUsuario.getTipoUsuario().getId() == 2) {
				clienteLogged = clienteService.getClienteRepository()
						.obtenerClientePorUsuarioId(loggedUsuario.getId());
			}else{
				clienteLogged = clienteService.getClienteRepository().findOne(
						(new Integer(1)).longValue());
			}
			FacesContext.getCurrentInstance().getExternalContext()
			.getSessionMap().put("cliente", clienteLogged);
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("usuario", loggedUsuario);

			usuario = "";
			contrasena = "";
			return "home?faces-redirect=true";
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error",
					"El usuario no existe o la contrasena no coincide con nuestra base de datos");
			FacesContext.getCurrentInstance().addMessage(null, message);
			System.out.println("no ingreso");
			return "";
		}
	}

	public void cerrarSesion() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.invalidateSession();
			ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Usuario getLoggedUsuario() {
		return loggedUsuario;
	}

	public void setLoggedUsuario(Usuario loggedUsuario) {
		this.loggedUsuario = loggedUsuario;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

}
