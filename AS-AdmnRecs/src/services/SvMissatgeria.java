package services;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import model.Usuari;

public class SvMissatgeria {

	private static final String serviceName = "SvMissatgeria";

	public static String getServicename() {
		return serviceName;
	}

	private final String username = "reservesarqsoft";
	private final String password = "13579qetuo";
	private String recipientEmail;
	private String title;
	private String message;

	public static void enviarDades(String nomR, Date data, int horaIni, int horaFi, List<String> username,
			String comentari, List<String> emails) {
		int i = 0;
		for (String email : emails) {
			SvMissatgeria newEmail = new SvMissatgeria();
			newEmail.setRecipientEmail(email);
			newEmail.setTitle("RESERVA");
			StringBuilder sb = new StringBuilder();
			sb.append("Recurso: " + nomR + "\n");
			sb.append("Username: " + username.get(i) + "\n");
			sb.append("Fecha: " + data + "\nHora Inicio: " + horaIni + ":00;\nHora Fin: " + horaFi + ":00;\n");
			if (!comentari.equals(""))
				sb.append("Comentaris: " + comentari + "\n");
			newEmail.setMessage(sb.toString());
			newEmail.send();
			i++;
		}

	}

	public SvMissatgeria() {

	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public void send() {

		try {
			send(this.username, this.password, this.recipientEmail, "", this.title, this.message);
		} catch (MessagingException ex) {
			Logger.getLogger(SvMissatgeria.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void send(String username, String password, String recipientEmail, String ccEmail, String title,
			String message) throws AddressException, MessagingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Get a Properties object
		Properties props = new Properties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");
		props.put("mail.smtps.starttls.enable", "true");
		props.put("mail.smtps.quitwait", "false");

		// Session session = Session.getInstance(props, null);
		/*
		 * Session session = Session.getInstance(props, new
		 * GMailAuthenticator(username,password ));
		 */
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// -- Create a new message --
		final MimeMessage msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		msg.setFrom(new InternetAddress(username + "@gmail.com"));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

		if (ccEmail.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
		}

		msg.setSubject(title);
		msg.setText(message, "utf-8");
		msg.setSentDate(new Date());

		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", username, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}


	public static void enviarDadesReserva(String nomR, String data, String hi, String hf, ArrayList<String> usus,
			String coment, ArrayList<String> mails, String userAutor) {
		// TODO:controlar que el servei funciona i si esta no disponible
		for (int i = 0; i < mails.size(); ++i) {
			StringBuilder sb = new StringBuilder();
			sb.append("Recurs: " + nomR + "\n");
			sb.append("Username: " + userAutor + "\n");
			sb.append("Data: " + data + "\nHorari: " + hi + ":00 - " + hf + ":00;\n");
			sb.append("Comentari: " + coment + "\n");
			sendSimpleMail(mails.get(i), "RESERVA", sb.toString());
		}
	}

	public static void enviarDadesReserva(String nomR, Date data, Integer hi, Integer hf, String userAutor,
			String coment, Set<String> mails) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// TODO:controlar que el servei funciona i si esta no disponible
		for (String mail : mails) {
			StringBuilder sb = new StringBuilder();
			sb.append("Recurs: " + nomR + "\n");
			sb.append("Username: " + userAutor + "\n");
			sb.append("Data: " + sdf.format(data) + "\nHorari: " + hi + ":00 - " + hf + ":00\n");
			sb.append("Comentari: " + coment + "\n");
			sendSimpleMail(mail, "RESERVA", sb.toString());
		}
	}
	
	
	private static void sendSimpleMail(String mail, String titol, String missatge) {
		// [START simple_example]
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Get a Properties object
		Properties props = new Properties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");
		props.put("mail.smtps.starttls.enable", "true");
		props.put("mail.smtps.quitwait", "false");

		try {
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("reservesarqsoft", "13579qetuo");
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("reservesarqsoft@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail, false));
			msg.setSubject(titol);
			msg.setText(missatge);
			msg.setSentDate(new Date());
			SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
			t.connect("smtp.gmail.com", "reservesarqsoft", "13579qetuo");
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
		} catch (AddressException e) {
			System.out.println("address exception");
		} catch (MessagingException e) {
			System.out.println("messaging exception");
		}
	}

	public void enviarDadesReserva(ArrayList<String> infoPerServei, Set<Usuari> usuaris) {

		ArrayList<String> usernames = new ArrayList<String>();

		ArrayList<String> emails = new ArrayList<String>();

		for (Usuari u : usuaris) {
			usernames.add(u.getUsername());
			emails.add(u.getMail());
		}

		enviarDadesReserva(infoPerServei.get(0), infoPerServei.get(1), infoPerServei.get(2), infoPerServei.get(3),
				usernames, infoPerServei.get(5), emails, infoPerServei.get(4));
		// TODO:controlar que el servei funciona i si esta no disponible

	}

}
