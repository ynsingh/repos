package server;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {
	public static String sendEmail(String fromAddr, String toAddr) throws IOException {
		
		final String username = "otpsender247@gmail.com";
		final String password = "OTPSender@247";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("otpsender247@gmail.com"));
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("vikhyat@iitk.ac.in"));
			
			message.setFrom(new InternetAddress(fromAddr));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toAddr));
			
			message.setSubject("OTP EMAIL FOR USER VERIFICATION");
			
			char[] OTP = generateOTP(8);// GENERATE 8 DIG OTP FROM METHOD BELOW
            final String stringValueOfOTP = String.valueOf(OTP);// CONVERT CHAR OTP TO STRING
            System.out.println(stringValueOfOTP);
			message.setText("your authentication OTP is:   " + stringValueOfOTP);
			

			Transport.send(message);

			System.out.println("Email with OTP sent");
			
			return stringValueOfOTP;
			
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	static char[] generateOTP(int len)
    {
        //Generating OTP using numeric values
		
        String numbers = "0123456789";
 
        // Using random method
        Random rndm_method = new Random();
 
        char[] otp = new char[len];
	 
        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
             numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }
	
}