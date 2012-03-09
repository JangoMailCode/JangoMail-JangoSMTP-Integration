/*
 * JangoSMTP.java
 * 
 * This example code shows how to send a transactional email through JangoSMTP from a Java program.
 * 
 * You need to make sure that you have the JavaMail package installed.
 * It can be obtained here: http://www.oracle.com/technetwork/java/javamail/index.html
 * In Eclipse, you can add it to your build path through Project > Properties > Libraries > Add External JARs.
 * 
 * For more help, see http://www.jangosmtp.com/How-It-Works.asp
 * 
 */

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class JangoSMTP {
 
    // these two variables store your login credentials for use below
    private static final String Username = "Your Username";
    private static final String Password  = "Your Password";
 
    public static void main(String[] args) throws Exception{
       new JangoSMTP().SendEmail("ToAddress@Domain.com", 
    		   						  "FromAddress@YourDomain.com",
    		   						  "This is the subject", 
    		   						  "This is the plain text message. Sent from a Java program!", 
    		   						  "<b>This is the message in HTML! Sent from a Java program!</b>");
    }
 
    public void SendEmail(String ToAddress, String FromAddress, String Subject, String PlainTextMessage, String HTMLMessage) throws Exception{
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "relay.jangosmtp.net");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.auth", "true");
 
        // set up a new authenticator
        Authenticator Authorization = new SMTPAuthenticator();
        Session JangoSMTPSession = Session.getDefaultInstance(props, Authorization);
        Transport transport = JangoSMTPSession.getTransport();
 
        // this object will hold the details of the email
        MimeMessage EmailMessage = new MimeMessage(JangoSMTPSession);
 
        // this object will store both the plain text and HTML portions of our email
        Multipart MultipartContent = new MimeMultipart("alternative");
 
        // first set the plain text message
        BodyPart part1 = new MimeBodyPart();
        part1.setText(PlainTextMessage);
        MultipartContent.addBodyPart(part1);
 
        // then set the HTML message
        BodyPart part2 = new MimeBodyPart();
        part2.setContent(HTMLMessage, "text/html");
        MultipartContent.addBodyPart(part2); 
 
        EmailMessage.setContent(MultipartContent);
        
        // Set the From Address.
        // This has to be a sender that is saved in your list of valid from-addresses.
        EmailMessage.setFrom(new InternetAddress(FromAddress));
        
        // Set the subject
        EmailMessage.setSubject(Subject);
        
        // set the to-address
        EmailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(ToAddress));
 
        // Connect, Send the Message, and Close the Connection
        transport.connect();
        transport.sendMessage(EmailMessage, EmailMessage.getRecipients(Message.RecipientType.TO));
        transport.close();
        
        // Then print a success message - this is only for testing
        System.out.println("Message sent!");
    }
 
    // this little class helps us to authenticate
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(Username, Password);
        }
    }
}