package com.ehelpy.brihaspati4.authenticate ; 

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;

public class ClientMain {
	public static void main(String args[]) throws Exception {
		
		// call boolean dateTimeCheck
			//if false - exit with msg
			//if true - call readVerifyCert
		
		//  boolean readVerifyCert
			// if true - find root node, start comn
			// if false - call generate certificate
		
		// generate certificate
			// use GUI to acquire data from user
			// create selfSignedcert
			// send cert to server for signature
		
		// certsign
			// checks creation of secure channel
				// if false-
						//
				// if true -
					//send certf to server for signature
						// at server - call verify user
						// server extracts email id from certf
						// sends OTP on email id and verify user
						// server stores email id and otp combination
						// server recieves OTP enetered by user and compares
							// if not correct - resend otp
							// if correct - server signs client certf with own pvt key
								//- stores client details
								// sends signed certf back to client
		
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
 	    else{
 	    	try { 	    		
 	    		flagset = ReadVerifyCert.verifyCert();
    		} catch (CertificateException e) {					
				e.printStackTrace();
			}
 	    }
	 	   
 	    //if(flagset){
 	    	// start application
 	    	// call objects and methods from classes of - (i)index management,(ii)routing and overlay mangement and (iii)communication
 	    //}
    }	
}

