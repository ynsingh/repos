package com.ehelpy.brihaspati4.authenticate ; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import sun.security.x509.X500Name;

public class ReadVerifyCert {

   public static KeyStore loadKeyStore(String keystorelocation, String password){
	   
      //to request a KeyStore object of default type:KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
      //Before a key store can be accessed, it must be loaded.
	  
	   KeyStore keystore = null;
	   try {		   
	      keystore = KeyStore.getInstance(KeyStore.getDefaultType());
	   } catch (KeyStoreException e1) {		   
			e1.printStackTrace();
	   }
	    
	   FileInputStream is = null;
	   try {
		   //keystore.load(null, password.toCharArray());
		   //FileOutputStream fos = new FileOutputStream("KeyStoreNew.JKS");
		//	 keystore.store(fos, password.toCharArray());
			// fos.close();
		   
	      //FileInputStream(String name)-creates a FileInputStream by opening a connection to an actual file
		  //the file named by the path name in the file system.
		  is = new FileInputStream(keystorelocation);
      			
		  //Loads this KeyStore from the given input stream location.
		  // toCharArray - Converts string to new char array & returns a newly allocated char array
		  keystore.load(is, password.toCharArray()); 
		  
		// Store away the keystore.
			 
	     			     		
      	} catch (Exception e) {      		
 		   //Logger add to log with date, time, IP address, timezone - TBD}
      	} finally {       		
  		   if (null != is) {  			   
  			   try {  				   
	 			   is.close();
     		 	} catch (IOException e) {    		 		
     		 		e.printStackTrace();
     		 	}
  		   }
      	} 
        return keystore;  
	}	
	
	static boolean verifyCert() throws Exception{
   
       // This function loads keystore and checks the validity of the certificate   	
       boolean status=true;// flag for certificate status		
       boolean flag=false;
       do {
          
    	  // keystore password input from user using Gui class.this will be taken from config finally
    	  String keyStorePass = Gui.getkeystorepass();
        		
    	  if(keyStorePass == null) {    		  
    	     status=false;
    	  } 
    	  else {
    		  //option of getting keystorelocation and password from config object.-TBD
	          	//Config conf=Config.getConfigObject();
        	  	//String keystorelocation = conf.keystorelocation;
			  	//String keystorepass = conf.keystorepass;
    		  
    		  // keystore location input presently being taken from user through a GUI
    		  Certificate cert=null;
    		  String keystorelocation = Gui.getkeystorelocation();
    		  try{
			     
    			 KeyStore keystore = loadKeyStore(keystorelocation,keyStorePass);
    			 
				 System.out.println("KEYSTORE IS = "+ keystore);
				 //GenerateCertificate.createSelfSignedCert();
				 			 
				 /*
				  * KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

char[] password = "some password".toCharArray();
ks.load(null, password);

// Store away the keystore.
FileOutputStream fos = new FileOutputStream("newKeyStoreFileName");
ks.store(fos, password);
fos.close();
				  */
				  

				 //Get the alias from key store. 
				 //Enumeration<String>	aliases() - Lists all the alias names of this keystore.
				 
				 //X509Certificate testcert= GenerateCertificate.createSelfSignedCert();
				 //System.out.println("testcert is "+ testcert);
				 Enumeration<String> aliases1 = keystore.aliases();
				 System.out.println("aliases are "+ aliases1);		
				 while(aliases1.hasMoreElements()){ 
				 
				    // hasMoreElements - method call returns 'true' if there are more tokens; false otherwise.
					String alias = (String)aliases1.nextElement();// nextElement -method call returns the next token in the string.
					System.out.println("ALIAS is = "+ alias);
					
					//getCertificate(String alias) - Returns the certificate associated with the given alias
					cert = keystore.getCertificate(alias);
					System.out.println("Certificate type =  "+ cert.getType());
					System.out.println("Existing certificate of ALIAS  "+ alias+ "  is =  " +cert);
			     }
    		  }
    		  catch (KeyStoreException e) {    			  
			     e.printStackTrace();
    		  }
					
    		  
    		  if (cert.getType() == "X.5091") {
			  // String getType() -Returns the type of this keystore.
			    
			     try {   
				    
			    	 //X509Certificate x509cert = X509Certificate.getInstance(cert.getEncoded());
					 X509Certificate x509cert = (X509Certificate) cert;
					 x509cert.checkValidity();
					 System.out.println("Checked certificate validity.Certificate is VALID.");
					 // check certificate is already signed by identity server private key. -TBD
					 //if yes- move to comn appln,if no- go to server for cert sign -TBD
					 String clientemailid = X500Name.asX500Name(((X509Certificate) cert).getSubjectX500Principal()).getCommonName();
					 System.out.println("email id of client is:  " + clientemailid);
					 //CertificateSignature.certsign(x509cert);
					 status = true;
			     } catch(Exception e) {
			    	 
			    	 System.out.println("Checked certificate validity.Certificate is EXPIRED(not valid).Move on to new Cert Acquisition");
			    	 //Logger.add to log with date, time, IP address, timezone - "The Certificate has been found to be expired before the hash verification."				
			    	 status = false;
			    	 flag=false; 
			    	 
			    	 // Generating and printing new self signed certificate using GenerateCertificate Class
			    	 X509Certificate newcert = GenerateCertificate.createSelfSignedCert();
			    	 System.out.println("new certificate of node is " + newcert);
					 
					 // extracting the email id from the new certificate. this will be done at server side.	
			    	 String clientemailid = X500Name.asX500Name(((X509Certificate) cert).getSubjectX500Principal()).getCommonName();
					 //System.out.println("email id of client is:  " + clientemailid);
	               	 //System.out.println("email id of client is " +X500Name.asX500Name(newcert.getSubjectX500Principal()).getCommonName());
	               	 
	               	 // call Certificate Signature class and forward new certificate for signature to Identity server
       	     		 System.out.println("new certificate of node will now be sent for signature to Identity Server ");
       	     		 CertificateSignature.certsign(newcert);
			     }
    		  }        								
     		  else {               	     
     		  
 			     System.out.println("Checked certificate validity.Certificate is not X509 Type.Move on to new Cert Acquisition");
     			 
 			     // Generating and printing new self signed certificate using GenerateCertificate Class
 			     X509Certificate newcert = GenerateCertificate.createSelfSignedCert();
           	     System.out.println("new certificate of node is " + newcert);
           	     
           	     // extracting the email id from the new certificate.this will be done at server side.
           	     String clientemailid = X500Name.asX500Name(((X509Certificate) cert).getSubjectX500Principal()).getCommonName();
				 //System.out.println("email id of client is:  " + clientemailid);
           	     //System.out.println("email id of client is " +X500Name.asX500Name(newcert.getSubjectX500Principal()).getCommonName());
           	     
           	     // call Certificate Signature class and forward new certificate for signature to Identity server
     			 System.out.println("new certificate of node will now be sent for signature to Identity Server ");
       	     	 CertificateSignature.certsign(newcert);
               	     	               	     
   	     	  }
       	    } // first else loop closure  -when keystore password is not equal to null    		
    	 }// do loop closure
      
        while(flag);
        return status;// status- returns status of checkValidity method
    }
}