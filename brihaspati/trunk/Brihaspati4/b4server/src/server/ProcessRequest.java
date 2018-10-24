package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
//import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabaseConnection;
//import server.PeerManager;
import server.ServerUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/ProcessRequest")
public class ProcessRequest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ProcessRequest() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        System.out.println("welcome to I Server. you are in do get method");

        //doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

        System.out.println("welcome to I Server. you are in do post method");

        //Retrieving client sent request parameters
        String reqType=request.getParameter("req");

        //result=new Vector();
        PrintWriter out = response.getWriter();

        if(reqType.equals("securechannel")) {
            try {
                //get the hash and random string from the scss side.
                //byte hsccstring = request.getParameter("thedigest");  oldcode,errors resolved below
                String hsccstring = request.getParameter("thedigest");
                byte[] hscc = hsccstring.getBytes();

                //int rkey = request.getParameter("randomkey");  oldcode,errors resolved below
                String rkeystring = request.getParameter("randomkey");
                int rkey = Integer.parseInt(rkeystring);

                //master get the own ip .
                //how to get ip of ms and hardcoded ip getting from property file.

                //String msip = "http://202.141.40.218:8443/brihaspati4_mserver";//**existing msip for brashpati****
                String msip = "http://localhost:8080/b4server";//** updated msip**** confirm***
                // make the hash of (ip of ms + received random key).

                String hsh_strng = msip + rkey;
                byte[] bytesOfMessage = hsh_strng.getBytes("UTF-8");

                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] thedigestmsg  = md.digest(bytesOfMessage);

                // now make the comparision between received hash and own make hash.
                //if(matches) {
                if(Arrays.equals(thedigestmsg,hscc)) {
                    //get the ip of scss(client)
                    String scssip=InetAddress.getByName(request.getRemoteAddr()).toString();

                    //generate own random no.
                    int randomkey =ServerUtil.generateRandomKey();

                    //generate the hash of (ip of scss + own rndom key ) send it to the as a response.
                    String hash_ms_strng = scssip +randomkey;

                    byte[] bytesOfMsg = hash_ms_strng.getBytes("UTF-8");

                    MessageDigest md1 = MessageDigest.getInstance("MD5");
                    byte[] thedigestmsgscss  = md1.digest(bytesOfMsg);

                    String msg = "verify by server";
                    String message = msg + thedigestmsgscss + randomkey;
                    response.setContentLength(message.length());
                }
            } catch(Exception e)
            {
                // server log class to be created and then uncomment
                //ServerLog.log("Exception in login in ProcessRequest class"+e.getMessage()); }
            }
        }


        //get the certificate which is send from sccs server with request.
        //System.out.println("WELCOME TO I SERVER");
        if(reqType.equals("sscccertsign")) {
            try {
                System.out.println("WELCOME TO I SERVER");

                String recdClientCertString =request.getParameter("cert");// amended
                System.out.println("The recieved certificate at server IN STRING format is"+recdClientCertString);

                // this code extracts email id from client cert recieved at server. uses pattern matching.
                //the client cert used is in string format.// shud be converted to x.509 cert format
                String clientEmail = null;
                Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(recdClientCertString);
                while (m.find())
                    clientEmail = m.group().toString();
                System.out.println("CLIENT EMAIL ID EXTRACTED AT SERVER IS  "+clientEmail);



                // this code segment is used to convert client cert in string format to client cert in x.509 format.
                //  X509Certificate[] recdClientCertX509=ConvertStringCertToX509.convertToX509Certarray(recdClientCertString);
                //System.out.println("The recieved certificate at server IN X509 format is"+ recdClientCertX509.toString());

                // this code segment extracts client email id from cert in x.509 format. this email id will then be given to send otp method.
                //String clientEmail = X500Name.asX500Name(recdClientCertX509.getSubjectX500Principal()).getCommonName();
                //System.out.println("The extracted client emailid at server is"+clientEmail);


                //code segment to send OTP on email
                //for reference to pass values- sendEmail(String fromAddr, String toAddr)
                System.out.println("SENDING EMAIL...");
                String Otp = SendMailTLS.sendEmail("otpsender247@gmail.com",clientEmail);

                System.out.println("OTP SENT TO YOUR EMAIL ID PROVIDED IN CERTIFICATE.CHECK MAIL AND INPUT OTP FOR VERIFICATION");
                System.out.println("Sent Otp is "+ Otp);
                //	byte[] recdClientCertbyte = recdClientCertString.getEncoded();
                //	recdClientCertString = new String(Base64.getEncoder().encode(recdClientCertbyte));
                DatabaseConnection.toDb(Otp, clientEmail, null);
                //boolean status1 = otp(Otp);
                //System.out.println(status1);
                // ******new code to be implemented******
                //recieve  cert for signing from client -  implemented
                //get email id from cert at server side - implemented,store email id in server database - TBD
                //generate OTP - IMPLEMENTED,save OTP in serevr DB - TBD
                //send OTP to user email ,open input box for email - IMPLEMENTED
                //submit OTP by user -TBD
                // match OTP with stored OTP of specific email -TBD
                // if OTP does not match - go back to client
                // if OTP matches -
                //generate node id - hex 160 bit - at server, store in server DB
                //sign cert,add node id to cert
                //return back to client
                //***********

            } catch(Exception e) {
            }
        }
        //doGet(request, response);
    }

}
