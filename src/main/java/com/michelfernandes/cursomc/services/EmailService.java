package com.michelfernandes.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.michelfernandes.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

	//void sendNewPasswordEmail(Cliente cliente, String newPass);

}