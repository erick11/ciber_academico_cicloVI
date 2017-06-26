package com.bem.autopart.test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.bem.autopart.model.Compra;
import com.bem.autopart.util.SendEmail;

public class TestEmail {

	public static void main(String[] args) {
		Compra compra = new Compra();
		compra.setTotal(300.5);
		compra.setSubtotal(200.10);
		compra.setIgv(100.4);
		compra.setDireccionEntrega("La calle");
		SendEmail send = new SendEmail(compra, "correoprueba@gmail.com",
				"Prueba 01");
		try {
			send.send();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
