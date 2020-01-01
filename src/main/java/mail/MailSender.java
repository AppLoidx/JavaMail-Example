package mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * @author Arthur Kupriyanov
 */
public class MailSender {



    public static boolean send(String toAdr, String userPassword) {
        final String email = "your@email";
        final String password = "your-password";

        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.socketFactory.port", 465);
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.required", "true");
        p.put("mail.smtp.port", 465);

        // 465 - default SMTP gmail port

        Session s = Session.getDefaultInstance(p,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(email, password);
                    }
                });

        try {
            Message mess = new MimeMessage(s);
            mess.setFrom(new InternetAddress(email));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toAdr));
            mess.setSubject("user password");
            mess.setText(String.valueOf(userPassword));

            // sending mail

            Transport.send(mess);

            // mail sent

            return true;
        }catch (Exception ex){

            // You can get bad credentials exception, when you try auth to gmail SMTP
            // without turn on the "Less secure app access"
            // read more info here: https://support.google.com/accounts/answer/6010255?hl=en

            // And additional you can turn off the IMAP on gmail settings
            // read more info here: https://www.lifewire.com/how-to-enable-gmail-via-imap-1170856

            ex.printStackTrace();
            return false;
        }

    }
}
