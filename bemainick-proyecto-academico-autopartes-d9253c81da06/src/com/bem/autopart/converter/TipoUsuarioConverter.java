package com.bem.autopart.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.bem.autopart.model.TipoUsuario;
import com.bem.autopart.service.TipoUsuarioService;

@FacesConverter(forClass = TipoUsuario.class)
public class TipoUsuarioConverter {

	@ManagedProperty(value = "#{tipoUsuarioService}")
	private TipoUsuarioService tipoUsuarioService;

	public String getAsString(FacesContext context, UIComponent component,
			Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof TipoUsuario) {
			return String.valueOf(((TipoUsuario) modelValue).getId());
		} else {
			throw new ConverterException(new FacesMessage(modelValue
					+ " is not a valid Warehouse"));
		}
	}

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		try {
			return tipoUsuarioService.getTipoUsuarioRepository().findOne(
					Long.valueOf(value));
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(value
					+ " is not a valid tipo de usuario ID"), e);
		}
	}
}
