package com.bem.autopart.security;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import com.bem.autopart.model.Usuario;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {

		FacesContext facesContext = event.getFacesContext();
		ExternalContext rootContext = facesContext.getExternalContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);

		if (session == null) {
			if (!isLoginPage) {
				try {
					rootContext.redirect(rootContext.getRequestContextPath()
							+ "/faces/index.xhtml");
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		}

		else {
			Usuario currentUser = (Usuario) session.getAttribute("usuario");
			if (!isLoginPage && (currentUser == null)) {
				try {
					rootContext.redirect(rootContext.getRequestContextPath()
							+ "/faces/index.xhtml");
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}

	public void beforePhase(PhaseEvent event) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
