package com.example.appincidencias;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender extends AsyncTask {

    private final Properties properties = new Properties();
    private String email;
    private Session session;
    private int codigo;

    /**
     * Puerto y cliente de correo a utilizar
     */
    private void init() {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.mail.sender","correoproyectosu@gmail.com");
        properties.put("mail.smtp.user", "correoproyectosu@gmail.com");
        properties.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);
    }

    public EmailSender(String email, int codigo){
        this.email = email;
        this.codigo = codigo;
    }

    public void sendMail(String correo, int codigo) {
        init();

        // Mensaje a enviar
        String body = "¡Bienvenido a la App de Incidencias!\n\nA continuacion se le presenta el" +
                "codigo de activacion de su nueva cuenta:\n\n" + codigo
                + "\n\nGracias!";
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setSubject("Activacion de Usuario App Incidencias");
            message.setText(body);
            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com", "correoproyectosu@gmail.com", "Ulacit2019.");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        }catch (Exception me){
            me.printStackTrace();
            return;
        }
    }

    /**
     * Se corre como un hilo en el fondo del proyecto
     * @param objects
     * @return
     */
    @Override
    protected Object doInBackground(Object[] objects) {
        sendMail(this.email, this.codigo);
        return null;
    }
}
