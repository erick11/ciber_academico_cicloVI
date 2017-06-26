package com.bem.autopart.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.bem.autopart.model.Cliente;
import com.bem.autopart.service.ClienteService;

@ManagedBean
@ViewScoped
public class ClienteManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String clienteNombre = null;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Cliente deletedCliente = new Cliente();
	private Cliente modifiedCliente = new Cliente();

	@ManagedProperty(value = "#{clienteService}")
	private ClienteService clienteService;

	public String guardar() {
		return null;
	}

	public void modificar() {
		System.out.println(modifiedCliente.getId() + "");
		if (modifiedCliente.getId() > 0
				&& !modifiedCliente.getNombre().equals("")) {
			clienteService.getClienteRepository().save(modifiedCliente);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "El cliente fue modificado exitosamente");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} else {
			modifiedCliente.getUsuario().getTipoUsuario()
					.setId((new Integer(2)).longValue());
			modifiedCliente.getUsuario().setNombre(
					modifiedCliente.getNombre() + " "
							+ modifiedCliente.getApellido());
			Cliente clienteSaved = clienteService.getClienteRepository().save(
					modifiedCliente);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Atencion", "Se creo cliente con exito");
			FacesContext.getCurrentInstance().addMessage(null, message);
			clientes.add(clienteSaved);
		}

		modifiedCliente = new Cliente();
	}

	public void eliminar() {
		clienteService.getClienteRepository().delete(deletedCliente);
		clientes.remove(deletedCliente);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Atencion", "El cliente fue eliminado");
		FacesContext.getCurrentInstance().addMessage(null, message);
		deletedCliente = new Cliente();
	}

	public void loadDeletedCliente(Cliente c) {
		deletedCliente = c;
	}

	public void loadModifiedCliente(Cliente c) {
		modifiedCliente = c;
	}

	public void loadCrearCliente() {
		modifiedCliente = new Cliente();
	}

	public void buscarClientes() {
		if (clienteNombre != null && !clienteNombre.equals("")) {
			clientes = clienteService.getClienteRepository()
					.obtenerClientePorNombreApellido("%" + clienteNombre + "%");
			if (clientes.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron clientes");
				FacesContext.getCurrentInstance().addMessage(null, message);
				clientes = new ArrayList<Cliente>();
			}
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "ERROR",
					"Ingrese nombre o apellido de cliente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			clientes = new ArrayList<Cliente>();
		}
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cliente getDeletedCliente() {
		return deletedCliente;
	}

	public void setDeletedCliente(Cliente deletedCliente) {
		this.deletedCliente = deletedCliente;
	}

	public Cliente getModifiedCliente() {
		return modifiedCliente;
	}

	public void setModifiedCliente(Cliente modifiedCliente) {
		this.modifiedCliente = modifiedCliente;
	}

}
