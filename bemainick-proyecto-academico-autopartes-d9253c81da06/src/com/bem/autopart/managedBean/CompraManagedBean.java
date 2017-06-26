package com.bem.autopart.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.primefaces.context.RequestContext;

import com.bem.autopart.model.Autoparte;
import com.bem.autopart.model.Cliente;
import com.bem.autopart.model.Compra;
import com.bem.autopart.model.DetalleCompra;
import com.bem.autopart.model.Marca;
import com.bem.autopart.model.Modelo;
import com.bem.autopart.model.Movimiento;
import com.bem.autopart.model.Usuario;
import com.bem.autopart.service.AutoparteService;
import com.bem.autopart.service.ClienteService;
import com.bem.autopart.service.CompraService;
import com.bem.autopart.service.MarcaService;
import com.bem.autopart.service.ModeloService;
import com.bem.autopart.service.MovimientoService;
import com.bem.autopart.util.SendEmail;
import com.google.common.collect.Lists;

@ManagedBean
@SessionScoped
public class CompraManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Marca> marcas = new ArrayList<Marca>();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private DetalleCompra addedAutoparte = new DetalleCompra();
	private Compra compra = new Compra();
	private List<DetalleCompra> listedDetalle = new ArrayList<DetalleCompra>();
	private List<DetalleCompra> addedDetalle = new ArrayList<DetalleCompra>();
	private DetalleCompra removedDetalleCompra = new DetalleCompra();
	private Compra insertedCompra = new Compra();
	private Double total = 0.0;
	private Double subtotal = 0.0;
	private Double igv = 0.0;
	private Long idCompra;
	private String selectedMarca = null;
	private String selectedModelo = null;
	private String direccionentrega = null;
	private Cliente clienteLogged = new Cliente();
	private Usuario usuarioLogged = new Usuario();
	private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@ManagedProperty(value = "#{marcaService}")
	private MarcaService marcaService;

	@ManagedProperty(value = "#{modeloService}")
	private ModeloService modeloService;

	@ManagedProperty(value = "#{autoparteService}")
	private AutoparteService autoparteService;

	@ManagedProperty(value = "#{compraService}")
	private CompraService compraService;

	@ManagedProperty(value = "#{clienteService}")
	private ClienteService clienteService;

	@ManagedProperty(value = "#{movimientoService}")
	private MovimientoService movimientoService;

	@PostConstruct
	public void load() {
		marcas = Lists.newArrayList(marcaService.getMarcaRepository()
				.obtenerMarcas());
		Long lastId = new Long(1);
		lastId = compraService.getCompraRepository().obtenerIdUltimaCompra();
		if (lastId == null) {
			idCompra = (new Integer(1)).longValue();
		} else {
			idCompra = (new Integer(lastId.intValue() + 1).longValue());
		}
		usuarioLogged = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");

		clienteLogged = (Cliente) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("cliente");

		direccionentrega = clienteLogged.getDireccionEntrega();
	}

	public void agregarAutoparte() {
		Integer cantidad = addedAutoparte.getCantidad();
		Double subdtotal = cantidad * addedAutoparte.getAutoparte().getPrecio();
		DetalleCompra d = new DetalleCompra();
		d.setAutoparte(addedAutoparte.getAutoparte());
		d.setAutoparteId(addedAutoparte.getAutoparte().getId());
		d.setCompraId(idCompra);
		d.setCantidad(cantidad);
		d.setSubtotal(subdtotal);
		subtotal += subdtotal;
		igv = subtotal * 0.18;
		total = subtotal + igv;
		addedDetalle.add(d);
		listedDetalle.remove(addedAutoparte);
		addedAutoparte = new DetalleCompra();
	}

	public void removerAutoparte() {
		removedDetalleCompra.setCantidad(0);
		if (Long.parseLong(selectedModelo) == removedDetalleCompra
				.getAutoparte().getModelo().getId()) {
			listedDetalle.add(removedDetalleCompra);
			subtotal -= removedDetalleCompra.getSubtotal();
			igv = subtotal * 0.18;
			total = subtotal + igv;
		}
		addedDetalle.remove(removedDetalleCompra);

	}

	public void removeAddedDetalleCompra(DetalleCompra c) {
		removedDetalleCompra = c;
	}

	public void loadAddedDetalleCompra(DetalleCompra c) {
		addedAutoparte = c;
		if (addedAutoparte.getCantidad() > addedAutoparte.getAutoparte()
				.getStock()) {
			RequestContext.getCurrentInstance().addCallbackParam("notValid",
					true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia", "Contamos solo con stock de "
							+ addedAutoparte.getAutoparte().getStock()
							+ " unidades");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (addedAutoparte.getCantidad() == 0) {
			RequestContext.getCurrentInstance().addCallbackParam("notValid",
					true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia", "Ingrese una cantidad valida");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void procesarCompra() throws AddressException, MessagingException {
		compra.setId(idCompra);
		compra.setDetallesCompra(addedDetalle);
		compra.setEstado(1);
		compra.setNumeroComprobante("BV-00000" + idCompra);
		compra.setTotal(total);
		compra.setSubtotal(subtotal);
		compra.setIgv(igv);
		compra.setEstadoEntrega(0);
		compra.setFechaEntrega(new Date());
		compra.setFechaCompra(new Date());
		compra.setCliente(clienteLogged);
		if (checked) {
			compra.setDireccionEntrega(direccionentrega);
		}
		insertedCompra = compraService.getCompraRepository().save(compra);
		Movimiento movimiento;
		for (DetalleCompra det : addedDetalle) {
			movimiento = new Movimiento();
			movimiento.setCantidad(det.getCantidad());
			movimiento.setAutoparte(det.getAutoparte());
			// Salida
			movimiento.setTipo(1);
			movimiento.setFecha(new Date());
			det.getAutoparte().setStock(
					det.getAutoparte().getStock() - det.getCantidad());
			autoparteService.getAutoparteRepository().save(det.getAutoparte());
			movimientoService.getMovimientoRepository().save(movimiento);
		}
		idCompra = new Integer(idCompra.intValue() + 1).longValue();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Exito", "Se registro la compra con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		SendEmail sendEmail = new SendEmail(compra, clienteLogged.getCorreo(),
				"Compra realizada");
		sendEmail.send();
		total = 0.0;
		subtotal = 0.0;
		igv = 0.0;
		selectedMarca = "";
		selectedModelo = "";
		listedDetalle = new ArrayList<DetalleCompra>();
		addedDetalle = new ArrayList<DetalleCompra>();
	}

	public void cargarModelos() {
		if (selectedMarca != null && !selectedMarca.equals("")) {
			modelos = modeloService.getModeloRepository()
					.obtenerModeloPorMarca(Long.parseLong(selectedMarca));
			listedDetalle = new ArrayList<DetalleCompra>();
			addedDetalle = new ArrayList<DetalleCompra>();
			total = 0.0;
			subtotal = 0.0;
			igv = 0.0;
			selectedModelo = "";
			if (modelos.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron modelos");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			modelos = new ArrayList<Modelo>();
			listedDetalle = new ArrayList<DetalleCompra>();
			addedDetalle = new ArrayList<DetalleCompra>();

		}
	}

	public void cargarAutopartes() {
		if (selectedModelo != null && !selectedModelo.equals("")) {
			List<Autoparte> autopartes = new ArrayList<Autoparte>();
			autopartes = autoparteService.getAutoparteRepository()
					.obtenerAutopartePorModeloConStock(
							Long.parseLong(selectedModelo));
			listedDetalle = new ArrayList<DetalleCompra>();
			if (autopartes.size() == 0) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_WARN, "Advertencia",
						"No se encontraron autopartes");
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				for (Autoparte a : autopartes) {
					DetalleCompra b = new DetalleCompra();
					b.setCantidad(0);
					b.setAutoparte(a);

					if (addedDetalle.size() > 0) {
						boolean exist = false;
						for (DetalleCompra d : addedDetalle) {
							if (d.getAutoparte().getId() == a.getId()) {
								exist = true;
							}
						}
						if (!exist) {
							listedDetalle.add(b);
						}
					} else {
						listedDetalle.add(b);
					}

				}

			}

		} else {
			listedDetalle = new ArrayList<DetalleCompra>();
		}
	}

	public void validarProcesar() {
		if (addedDetalle == null || addedDetalle.size() == 0) {
			RequestContext.getCurrentInstance().addCallbackParam("notValid",
					true);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia", "No hay autopartes agregadas al carrito");
			FacesContext.getCurrentInstance().addMessage(null, message);
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

	public DetalleCompra getAddedAutoparte() {
		return addedAutoparte;
	}

	public void setAddedAutoparte(DetalleCompra addedAutoparte) {
		this.addedAutoparte = addedAutoparte;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<DetalleCompra> getListedDetalle() {
		return listedDetalle;
	}

	public void setListedDetalle(List<DetalleCompra> listedDetalle) {
		this.listedDetalle = listedDetalle;
	}

	public List<DetalleCompra> getAddedDetalle() {
		return addedDetalle;
	}

	public void setAddedDetalle(List<DetalleCompra> addedDetalle) {
		this.addedDetalle = addedDetalle;
	}

	public DetalleCompra getRemovedDetalleCompra() {
		return removedDetalleCompra;
	}

	public void setRemovedDetalleCompra(DetalleCompra removedDetalleCompra) {
		this.removedDetalleCompra = removedDetalleCompra;
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

	public CompraService getCompraService() {
		return compraService;
	}

	public void setCompraService(CompraService compraService) {
		this.compraService = compraService;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getIgv() {
		return igv;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public void setIgv(Double igv) {
		this.igv = igv;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}

	public Compra getInsertedCompra() {
		return insertedCompra;
	}

	public void setInsertedCompra(Compra insertedCompra) {
		this.insertedCompra = insertedCompra;
	}

	public String getDireccionentrega() {
		return direccionentrega;
	}

	public void setDireccionentrega(String direccionentrega) {
		this.direccionentrega = direccionentrega;
	}

	public Cliente getClienteLogged() {
		return clienteLogged;
	}

	public void setClienteLogged(Cliente clienteLogged) {
		this.clienteLogged = clienteLogged;
	}

	public Usuario getUsuarioLogged() {
		return usuarioLogged;
	}

	public void setUsuarioLogged(Usuario usuarioLogged) {
		this.usuarioLogged = usuarioLogged;
	}

	public MovimientoService getMovimientoService() {
		return movimientoService;
	}

	public void setMovimientoService(MovimientoService movimientoService) {
		this.movimientoService = movimientoService;
	}

}
