package util;

import javax.mail.*;
import javax.mail.internet.*;


import java.util.Properties;
public class Correo  {

	private Session session;
	private static final String USER_NAME="sistemadedietasinfo@gmail.com";
	private static final String PASSSWORD="dietas123";
	private static final String FROM_ADDRESS="sistemadedietasinfo@gmail.com";

	public Correo() {
	}

	public static void main(String[] args){
		new Correo().sendEmailMessage("palaciocamilor@gmail.com", "<html>Eooooo</html>");
	}
	public boolean logInEmail() {
        Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
        try {
            Session sessionTest = Session.getInstance(props, null);
            Store store = sessionTest.getStore();
            store.connect("imap.gmail.com", USER_NAME, PASSSWORD);
            return true;
        } catch (Exception mex) {
        	mex.printStackTrace();
        	return false;
        }
	}

	public boolean sendEmailMessage(String emailAddressTo,String msgText) {

		try {
			
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(USER_NAME,
									PASSSWORD);
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_ADDRESS)); // Set from
																// address of
																// the email
			message.setContent(msgText, "text/html"); // set content type of the
														// email

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailAddressTo)); // Set email
															// recipient

			message.setSubject("Sistema de Dietas|Cuenta creada exitosamente!"); 
			Transport.send(message); 

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
}
