/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.SendFailedException;
public class Email {
private String to;
private String subject;
private String password;
private String text;
String userid;
String host;
String path,path1;
String saluation,closing;
String buffer;
InternetAddress toAddress;




public Email(String path,String to,String password,String subject,String body,String saluation,String closing)
{
 this.path1=path;
    this.to = to; this.password = password;this.subject=subject;this.text=body;this.path=path;this.saluation=saluation;this.closing=closing;}

public int send(){
 host = "smtp.gmail.com";
 



try
{
path=System.getProperty("user.home");

//   Open the file that is the first
 //  command line parameter

   Properties libmspro = new Properties();

            // DriverClass & url
           
             libmspro.load(new FileInputStream(path+"/libms.properties"));

             
        String     userid = libmspro.getProperty("webadmin");
        buffer = libmspro.getProperty("webpass");
         
            



  System.out.println(buffer+"  "+userid);
  
 


Properties props = System.getProperties();
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", host);
props.setProperty("mail.transport.protocol", "smtp");
props.put("mail.smtp.user", userid);
props.put("mail.smtp.password",buffer);
props.put("mail.smtp.port", "587");
props.put("mail.smtp.auth", "true");
Session session = Session.getDefaultInstance(props, null);
MimeMessage message = new MimeMessage(session);
InternetAddress fromAddress = null;
 toAddress = null;


fromAddress = new InternetAddress(userid);
toAddress = new InternetAddress(to);




message.setFrom(fromAddress);
message.setRecipient(RecipientType.TO, toAddress);
message.setSubject(subject);



if(subject.startsWith("accept"))
{
message.setSubject("Create Account Successfully from LibMS");
 // create and fill the first message part
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText("Instant User Manual As Attachment "+text);

      // create the second message part
      MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
         FileDataSource fds = new FileDataSource(path+"/help/help.doc");
      mbp2.setDataHandler(new DataHandler(fds));
      mbp2.setFileName(fds.getName());

      // create the Multipart and add its parts to it
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);

      // add the Multipart to the message
      message.setContent(mp);

      // set the Date: header
   //   message.setSentDate(new Date());


}


 // create and fill the first message part
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(saluation);

      // create the second message part
      MimeBodyPart mbp2 = new MimeBodyPart();
      mbp2.setText(text);

      MimeBodyPart mbp3 = new MimeBodyPart();
      mbp3.setText(closing);


      // create the Multipart and add its parts to it
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);
       mp.addBodyPart(mbp3);

      // add the Multipart to the message
      message.setContent(mp);



//SMTPSSLTransport transport =(SMTPSSLTransport)session.getTransport("smtp");
String pass=buffer.toString();
Transport transport = session.getTransport("smtp");
transport.connect(host, userid, pass);

//check Email ID
//boolean res=isValidDomain("gmail.com");
//System.out.println(res+".............>>>>>>>>>>>>>>"+toAddress.toString());


try{
transport.sendMessage(message, message.getAllRecipients());
}
catch(MessagingException e){
  System.out.println("sdfsdfsd"+e);

}
transport.close();
return 0;
}
catch(SendFailedException e1){
  
try{
System.out.println("//write xml file to save pending mailer details in xml file");
    File f;
  f=new File(path1+"/logs/myfile.txt");
  if(!f.exists()){
  f.createNewFile();
  System.out.println("New file \"myfile.txt\" has been created  to the current directory");
  Writer output = null;
  output = new BufferedWriter(new FileWriter(f));
  output.write(toAddress.toString());
  output.close();


 }
   }catch(Exception e2){
System.out.println(e2);

  }


}
catch (Exception e) {
//write xml file to save pending mailer details in xml file


    System.out.println("Ex");

 try

     {

       /*
#
        * To append output to a file, use
#
        * FileOutputStream(String file, booean blnAppend) or
#
        * FileOutputStream(File file, booean blnAppend) constructor.
#
        *
#
        * If blnAppend is true, output will be appended to the existing content
#
        * of the file. If false, file will be overwritten.
#
        */

path1=path1.substring(0,path1.indexOf("/"));
path1=path1.substring(0,path1.indexOf("/"));
path1=path1.substring(0,path1.indexOf("/"));

      FileOutputStream fos = new FileOutputStream(path1+"/web/logs/email.txt", true);

      

     String data=toAddress.toString();

       fos.write(data.getBytes());



      /*
#
       * Close FileOutputStream using,
#
       * void close() method of Java FileOutputStream class.
#
       *
#
       */



       fos.close();



     }

     catch(FileNotFoundException ex)

     {

      System.out.println("FileNotFoundException : " + ex);

     }

     catch(IOException ioe)

     {

      System.out.println("IOException : " + ioe);

     }
return 1;

}
 return 0;
}
public boolean isValidDomain(String domainName)
{
    try
    {
        InetAddress.getByName(domainName);
        return true;
    }
    catch (UnknownHostException e)
    {
        return false;
    }
}


}
