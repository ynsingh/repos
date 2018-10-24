package server;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import dao.DatabaseConnection;


public class servermain {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		GlobalObject global_object = GlobalObject.getGlobalObject();
		global_object.setRunStatus(true);
		
		// GlobalObject will keep status of various threads and run status. This will be used
		// for proper closure of threads when closing the application.

		Config conf=Config.getConfigObject();
			   
		// Config initialization from configuration file during by the constructor of Config.
		// Config_object will keep the data after reading from configuration file.
		// On each change, the data should be written back to config file also.
		// It implies, in each write api, write to config file on disk is to be implemented.

		boolean flagset = false;
    	boolean timeflg=dateTimeCheck.checkDate();
	 	    // If the time flag returns false then exit the user from the system 
    		// otherwise start the services.  
 	    if (!timeflg)
 	    {   
 	    	String msg = "Please reset your system time and try again." ; 
 	    	Gui.showMessageDialogBox(msg);
 			//Exit the system.
 			System.exit(0);
    	}
 	    
		
		// call boolean dateTimeCheck
			//if false - exit with msg
		
 	        //if true - call readVerifyCert
		
 	   else{
 		   
	      X509Certificate newcert = GenerateCertificate.createSelfSignedCert();
	      byte[] servercertbyte = newcert.getEncoded();
	      String servercert =new String( Base64.getEncoder().encode(servercertbyte));
			DatabaseConnection.toDb("null", "b4server@iitk.ac.in", servercert);
			 System.out.println("new certificate of node is " + newcert);
			// The above line is used for debugging.
	  }
	 	   
 	  //if(flagset){
 	    	// start user specific services
 	        // user specific DFS mount service,
 	    	// call objects and methods from classes of - (i)index management,(ii)routing and overlay mangement and (iii)communication
 	  //}
 	    
 	    // All generic services
 	    // storage service, routing service, indexing service
	}

}
