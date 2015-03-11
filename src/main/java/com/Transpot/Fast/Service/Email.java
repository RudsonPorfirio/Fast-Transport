package com.Transpot.Fast.Service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public abstract class Email {
	
	
	
	
	@SuppressWarnings("deprecation")
	public static void sendEmail(String emailDestino, String senha) throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   System.out.println("alterando hostname...");
		   email.setHostName("smtp.gmail.com");
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinatários
		   email.addTo(emailDestino, "Jose");
		   //Configure o seu email do qual enviará
		   email.setFrom("rudsonporfirio@gmail.com", "Rudson");
		   //Adicione um assunto
		   email.setSubject("Envio de senha");
		   //Adicione a mensagem do email
		   email.setMsg("sua senha e : "+senha);
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("rudsonporfirio", "porfirio2516");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}

}
