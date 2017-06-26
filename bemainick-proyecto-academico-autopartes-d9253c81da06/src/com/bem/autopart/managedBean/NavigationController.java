package com.bem.autopart.managedBean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;

	public void showPage() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			if (pageId.equals("view_portada"))
				ec.redirect(ec.getRequestContextPath() + "/faces/home.xhtml");
			if (pageId.equals("view_mantenimiento_autoparte"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/mantenimiento/autoparte.xhtml");
			if (pageId.equals("view_mantenimiento_usuario"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/mantenimiento/usuario.xhtml");
			if (pageId.equals("view_mantenimiento_cliente"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/mantenimiento/cliente.xhtml");
			if (pageId.equals("view_mantenimiento_marca"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/mantenimiento/marca.xhtml");
			if (pageId.equals("view_mantenimiento_modelo"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/mantenimiento/modelo.xhtml");
			if (pageId.equals("view_transaccional_compra"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/transaccional/compra.xhtml");
			if (pageId.equals("view_reporte_compras"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/reporte/reportecompras.xhtml");
			if (pageId.equals("view_reporte_movimiento"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/reporte/reportemovimientos.xhtml");
			if (pageId.equals("view_consulta_miscompras"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/consulta/miscompras.xhtml");
			if (pageId.equals("view_consulta_misentregas"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/consulta/misentregas.xhtml");
			if (pageId.equals("view_reporte_mas_vendidas"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/reporte/reportemasvendidas.xhtml");
			if (pageId.equals("view_transaccional_entregas"))
				ec.redirect(ec.getRequestContextPath()
						+ "/faces/transaccional/entregas.xhtml");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

}
