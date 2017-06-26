package com.bem.autopart.util;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bem.autopart.model.Compra;
import com.bem.autopart.model.DetalleCompra;
import com.sun.mail.smtp.SMTPTransport;

public class SendEmail {

	private final String username = "xxxxxxxxx";
	private final String password = "xxxxxxxxx";
	private String destinatario;
	private String titulo;
	private Compra compra = new Compra();

	public SendEmail(Compra compra, String destinatario, String titulo) {
		this.compra = compra;
		this.destinatario = destinatario;
		this.titulo = titulo;
	}

	public void send() throws AddressException, MessagingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");

		props.put("mail.smtps.quitwait", "false");

		Session session = Session.getInstance(props, null);

		final MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(username));
		msg.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(destinatario, false));
		msg.setSubject(titulo);
		msg.setText(getMensaje(), "utf-8", "html");
		msg.setSentDate(new Date());

		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", username, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}

	public String getMensaje() {
		StringBuilder msg = new StringBuilder();
		msg.append("<h2 style='color:#47a3da;'>Hola "
				+ compra.getCliente().getNombre() + "! </h2>");
		msg.append("<h3 style='color:#47a3da;'>Ha realizado la siguiente compra</h3>");
		msg.append("<div style='width:700px;'>");
		msg.append("<table style='color:#47a3da;'>");
		msg.append("<tr>");
		msg.append("<td>Nro. de Comprobante</td>");
		msg.append("<td>" + compra.getNumeroComprobante() + " </td>");
		msg.append("</tr>");
		msg.append("<tr>");
		msg.append("<td>Fecha de Compra:</td>");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		msg.append("<td>" + dateFormat.format(compra.getFechaCompra())
				+ " </td>");
		msg.append("</tr>");
		msg.append("</table>");
		msg.append("<br/>");
		msg.append("<table style='color:#47a3da; border: 1px solid #10689a;'>");
		msg.append("<tr>");
		msg.append("<th style='background-color: #47a3da; color: white; border: 1px solid #10689a;'>Marca</th>");
		msg.append("<th style='background-color: #47a3da; color: white; border: 1px solid #10689a;'>Modelo</th>");
		msg.append("<th style='background-color: #47a3da; color: white; border: 1px solid #10689a;'>Autoparte</th>");
		msg.append("<th style='background-color: #47a3da; color: white; border: 1px solid #10689a;'>Cantidad</th>");
		msg.append("<th style='background-color: #47a3da; color: white; border: 1px solid #10689a;'>SubTotal</th>");
		msg.append("</tr>");
		for (DetalleCompra d : compra.getDetallesCompra()) {
			msg.append("<tr>");
			msg.append("<td style='padding:5px; border: 1px solid #10689a;'>"
					+ d.getAutoparte().getModelo().getMarca().getNombre()
					+ "</td>");
			msg.append("<td style='padding:5px; border: 1px solid #10689a;'>"
					+ d.getAutoparte().getModelo().getNombre() + "</td>");
			msg.append("<td style='padding:5px; border: 1px solid #10689a;'>"
					+ d.getAutoparte().getNombre() + "</td>");
			msg.append("<td style='padding:5px; border: 1px solid #10689a;'>"
					+ d.getCantidad() + "</td>");
			msg.append("<td align='right' style='padding:5px; border: 1px solid #10689a;'>S/."
					+ round(d.getSubtotal(), 2) + "</td>");
			msg.append("</tr>");
		}
		msg.append("</table>");
		msg.append("<br/>");
		msg.append("<table style='color:#47a3da;'>");
		msg.append("<tr>");
		msg.append("<td>SubTotal:</td>");
		msg.append("<td align='right'>S/. " + round(compra.getSubtotal(), 2)
				+ " </td>");
		msg.append("</tr>");
		msg.append("<tr>");
		msg.append("<td>I.G.V.:</td>");
		msg.append("<td align='right'>S/. " + round(compra.getIgv(), 2)
				+ " </td>");
		msg.append("</tr>");
		msg.append("<tr>");
		msg.append("<td>Total:</td>");
		msg.append("<td align='right'>S/. " + round(compra.getTotal(), 2)
				+ " </td>");
		msg.append("</tr>");
		msg.append("</table>");
		msg.append("<h4 style='color:#47a3da;'>Gracias por su compra!</h4>");
		msg.append("</div>");

		return msg.toString();
	}

	public double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}