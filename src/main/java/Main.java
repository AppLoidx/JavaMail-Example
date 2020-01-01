import mail.MailSender;

/**
 * @author Arthur Kupriyanov
 */
public class Main {
    public static void main(String[] args) {
        MailSender.send("toAddr@mail", "Hello!");
    }
}
