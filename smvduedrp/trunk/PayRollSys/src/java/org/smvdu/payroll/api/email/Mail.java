package org.smvdu.payroll.api.email;

import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import org.smvdu.payroll.api.Administrator.CollegeList;


public class Mail {

    String d_email = "from",
            d_password = "secret",
            d_host = "smtp.gmail.com",
            d_port = "465",
            m_to = "to email",
            m_subject = "Testing",
            m_text = "Hey, this is the testing email using smtp.gmail.com.";

    public static void main(String[] args) {
        String[] to = {""};
        String[] cc = {""};
        String[] bcc = {""};
        //This is for google
       // Task task = new Task();
        //Mail.sendMail(task );
    }

    public synchronized static boolean sendMail(String sender) {
        Properties props = new Properties();
        //Properties props=System.getProperties();
        props.put("mail.smtp.user", " user email id");
        props.put("mail.smtp.host", "smtp.gmail.com");
        {
            props.put("mail.smtp.port", "465");
        }
        {
            props.put("mail.smtp.starttls.enable", "true");
        }
        props.put("mail.smtp.auth", "true");
         {
            props.put("mail.smtp.debug", "true");
        } 
         
        {
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        

        try {
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);
            
           return true;
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
    }
    
   /** Mail Message for change password
    * @param newpassword String new password of the user
    * @param serverUrl String URL of the server
    * @return String
    */
    
    public String getMailMessage(String newpassword,String serverUrl)
    {
        String s = "<HTML>";
        s+="<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>Your password has been changed on PayRoll System . <hr>";
        s+="<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>Your new password is <hr>";
        s+="<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>"+newpassword;
        s+="<font style='color:#4B4B4B;font-size:13px;font-weight:bold;'> You can change it later on as per your convenience.<hr>";
        s+="<br><br><font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>This is a system generated mail sent from <hr>";
        s+="<br><font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>"+serverUrl;
        s+="<br><br><font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>With Regards <hr>";
        s+="<br><br><font style='color:#4B4B4B;font-size:13px;font-weight:bold;'>PayRoll Administrator <hr>";
        s+="</html>";
        //System.err.println(s);
        return s;
    }
    
    /** This method is used to send the mails with suitable messages
    * @param toEMail String E-Mail of the user
    * @param subject String subject of Email 
    * @param info String url of the server 
    * @return boolean
    */

    public boolean sendMailMessage(String subject,String toEmail,String info ){
       try{
           String serverUrl=getserverUrl();
           String message=getMailMessage(info,serverUrl);
           final String[] maildata = new CollegeList().getSMTPAuthDetails().split("-");
           int port=Integer.parseInt(maildata[0]);
           //System.out.print("maildata======"+port+":=="+maildata[0]+"frommail==="+maildata[1]
           //+"smtpHostName=="+maildata[3]+"frompasswd=="+maildata[2]);
           Properties props = new Properties();
           props.put("mail.smtp.host", maildata[3]); //smtpHostName
           if(!maildata[2].equals("")){
                props.put("mail.smtp.auth", "true");
           }
           if(!String.valueOf(port).equals("25")){
               //props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.port", port);
                // Use the following if you need SSL
                props.put("mail.smtp.socketFactory.port", port);
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
           }
           
           Session session = Session.getDefaultInstance(props, null);
           session.setDebug(false);
           
           MimeMessage msg = new MimeMessage(session);
           try {
                msg.setFrom(new InternetAddress(maildata[1]));
                msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
                msg.setSubject(subject);
                //Create and fill the first message part
                MimeBodyPart l_mbp = new MimeBodyPart();
                Multipart l_mp = new MimeMultipart();
                l_mbp.setContent(message, "text/html");
                l_mp.addBodyPart(l_mbp);
                msg.setContent(l_mp);
                java.util.Date date=new java.util.Date();
                msg.setSentDate(date);
                Transport transport = session.getTransport("smtp");
                if(!maildata[2].equals("")){
                    if(!String.valueOf(port).equals("25")){
                        transport.connect(maildata[3].trim(), port, maildata[1].trim(), maildata[2].trim());
                    }
                    else{
                        transport.connect(maildata[3].trim(), maildata[1].trim(), maildata[2].trim());
                        //System.out.print("maildata======"+port+":=="+maildata[0]+"frommailelsepart==="+port);
                    }
               // Send the message
                }
                else{
                    transport.connect();
                }
                msg.saveChanges();     // don't forget this
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                
           }
           catch(Exception exc) 
           {
            System.out.println(exc);
           }
           return true;  
       }
       catch(Exception e)
              {
                  e.printStackTrace();
                  return false;
      }
   }
    /** This method is used to get the serverUrl for mail sending
     * @return String 
     */
   public String getserverUrl(){
     try{
         FacesContext facesContext = FacesContext.getCurrentInstance();
         HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
         String ipAddress = request.getRemoteAddr();
         String port = String.valueOf(request.getServerPort()); 
         String serverUrl="http://"+ipAddress+":"+port+"/index.jsp";
         //System.out.println("Ip : "+ipAddress+" : "+request.getRemoteHost());
         return serverUrl;
     }
     catch(Exception e)
     {
        e.printStackTrace();
        return null;
      }
   }  
}