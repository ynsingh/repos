package server;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignedObject;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabaseConnection;
import sun.misc.BASE64Encoder;
import sun.security.x509.BasicConstraintsExtension;
import sun.security.x509.CertificateExtensions;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSubjectName;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class otp_verification
 */
@WebServlet("/otp_verification")
public class otp_verification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public otp_verification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println(" you are in do get method for otp verification");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(" you are in do post method for otp verification");
		String reqType=request.getParameter("req");
		KeyStore keyStore = null;
		//Socket socket;
		try {
			keyStore = KeyStore.getInstance("JKS");
		} catch (KeyStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		try {
			keyStore.load(null );
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CertificateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//result=new Vector();
		
		if(reqType.equals("otpverify"))
		{
        	try{     		
        		System.out.println("OTP verification in Progress");
        		String recdClientOTP =request.getParameter("OTP");
        		String recdClientCertString =request.getParameter("cert");
        		String recdClientCertStringfmbyte =request.getParameter("certstringbyte");

        		X509Certificate[] chain =	ConvertStringCertToX509.convertToX509Certarray(recdClientCertStringfmbyte);
        		recdClientCertString = chain[0].toString();
        		System.out.println("the client cert " + recdClientCertString);
        		String clientEmail = null;
        		
        		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(recdClientCertString);
        	    while (m.find()) 
        			clientEmail = m.group().toString();
        	    System.out.println("the client email id is " + clientEmail);
        		
        		System.out.println("The recieved OTP at server is " + recdClientOTP);
        		String SentOtp = DatabaseConnection.fromDb(clientEmail);
        		System.out.println("the sent otp is" + SentOtp);
        		if (recdClientOTP.equals(SentOtp))
        		{
        			
        			String ServerCertbyte = DatabaseConnection.CertfromDb("b4server@iitk.ac.in");
        			X509Certificate[] servercert =	ConvertStringCertToX509.convertToX509Certarray(ServerCertbyte);
        			System.out.println("the server cert " + servercert[0].toString());
        			String path = "C:\\tmp";
        			keystore_save adam = new keystore_save();
        			PrivateKey ServerPrivate = DatabaseConnection.PrivkeyfromDb("b4server@iitk.ac.in");
        			//System.out.println("ServerPrivate"+ ServerPrivate);
        			//System.out.println("Otp successfully verified " );
        			//create signed client certificate
        			X509Certificate signedclientcertificate = createSignedCertificate(chain[0],servercert[0],ServerPrivate);
        			byte[] clientcertbyte = signedclientcertificate.getEncoded();
        		    String clientcert =new String( Base64.getEncoder().encode(clientcertbyte));
        			System.out.println("the signed client cert " + signedclientcertificate.toString());
        			//Signature Sign = Signature.getInstance("SHA1WithRSA");
        			//Sign.initSign(ServerPrivate);
        			//Sign.update(Base64.getDecoder().decode(recdClientCertStringfmbyte));
        			//byte [] signupdate = Sign.sign();
        			//String signature = new String(signupdate, "UTF-8");
        			//String clientsignedhash	=	new String( Base64.getEncoder().encode(signupdate));
        			//System.out.println("SignedObject:" + clientsignedhash);
        			//System.out.println("SignedObject: in string" +  (so.getSignature()));
        			PrintWriter out = response.getWriter();
        			out.println( ServerCertbyte  +"ClientCert" + clientcert);
        		}
        		}	
        	
        		catch(Exception e){ 
        			System.err.println("Got an exception!");
				    System.err.println(e.getMessage());
        	} 
        	
		//doGet(request, response);
		}

	}

	private X509Certificate createSignedCertificate(X509Certificate cetrificate, X509Certificate issuerCertificate,PrivateKey issuerPrivateKey) {
		// TODO Auto-generated method stub
		try{
            Principal issuer = issuerCertificate.getSubjectDN();
            System.out.println("issuer   "+  issuer.toString());
            String issuerSigAlg = issuerCertificate.getSigAlgName();
             
            byte[] inCertBytes = cetrificate.getTBSCertificate();
            System.out.println("a1   ");
            X509CertInfo info = new X509CertInfo(inCertBytes);
            System.out.println("a2   ");
            X500Name attr	= (X500Name) issuer;
            System.out.println("a4   " + attr);
           // info.delete(X509CertInfo.ISSUER);
            
            //info.set(X509CertInfo.SUBJECT, new CertificateSubjectName(attr));
           // info.set(X509CertInfo.ISSUER, new CertificateIssuerName(attr));
           // System.out.println("a3   ");
            //System.out.println("issuer   "+  X509CertInfo.ISSUER.toString());
            //No need to add the BasicContraint for leaf cert
            //if(!cetrificate.getSubjectDN().getName().equals("CN=b4server@iitk.ac.in, OU=IIT KANPUR,O=EE DEPT, L=KANPUR, ST=UTTAR PRADESH, C=IN"))
               /*CertificateExtensions exts=new CertificateExtensions();
               System.out.println("a1   ");
                BasicConstraintsExtension bce = new BasicConstraintsExtension(true, -1);
                System.out.println("a2 ");
                exts.set(BasicConstraintsExtension.NAME,new BasicConstraintsExtension(false, bce.getExtensionValue()));
                System.out.println("a3  ");

                info.set(X509CertInfo.EXTENSIONS, exts);
                System.out.println("a4   ");
				*/
             
            X509CertImpl outCert = new X509CertImpl(info);
            outCert.sign(issuerPrivateKey, issuerSigAlg);
            X509Certificate clientcert = outCert;
            return clientcert;
        }catch(Exception ex){
            ex.printStackTrace();
        }
		return null;
	}
}
