package com.ehelpy.brihaspati4.authenticate ; 

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Vector;
import javax.net.ssl.HttpsURLConnection;
import server.ServerUtil;


public class CertificateSignature {
	
	/*public static boolean secure_channel() throws UnsupportedEncodingException, NoSuchAlgorithmException{
	    
    	boolean flagsecure=false;

   		//String msurl =RuntimeDataObject.getMasterUrl();//msurl through Config object
   		String msurl ="http://localhost:8080/IS";//given hard coded server url

    	//generate the random No.
   		int  randomkey = ServerUtil.generateRandomKey();
    	//String  randomkey = (String)ServerUtil.generateRandomKey();//********convert t string by typecasting

    	//generate the md5hash of (ip of ms + randomkey)
    	String randomstring = msurl+randomkey;

    	byte[] bytesOfMessage = randomstring.getBytes("UTF-8");

    	MessageDigest md = MessageDigest.getInstance("MD5");
    	byte[] thedigest = md.digest(bytesOfMessage); 
    	
    	//example to convert -String s = new String(bytes);
    	String s = new String(thedigest);
    	//byte[] hashdigest ="thedigest="+URLEncoder.encode(thedigest,"UTF-8");
    	String hs ="thedigest="+URLEncoder.encode(s,"UTF-8");
    	//example to convert - byte[] bytes = example.getBytes();
    	byte[] hashdigest = hs.getBytes();
    	
    	
    	//String rdmkey = "randomkey="+URLEncoder.encode(randomkey,"UTF-8");  	
    	String rdmkey = "randomkey="+URLEncoder.encode(""+randomkey,"UTF-8");
    	
         //String MSrequrl = mserverurl +"/ProcessRequest?req=sscccertsign&cert=" + URLEncoder.encode(certstring, "UTF-8");
   		 String  MSrequrl = msurl +"/ProcessRequest?req=securechannel&"+ hashdigest+"&" +randomkey;
   		 
   		 Vector result1 =HttpsUtil.getschannel(MSrequrl);
   		 //The Vector class implements a growable array of objects. 
   		 //Like an array, it contains components that can be accessed using an integer index.
   		 //However, the size of a Vector can grow or shrink as needed to accommodate adding and removing items
   		 //after the Vector has been created
   		 
         byte[] mshash = (byte[]) result1.get(1);
         int msrndmstrng = (int) result1.get(2);         
         //get(int index)-Returns the element at the specified position in this list
         
                 
         //now get the scssip(***client ip***).
         String scssip ="           ";
                 
         //generate the mhash of (scssip + msrndmstrng);
         String rndstr = scssip +msrndmstrng;
         byte[] bytes = rndstr.getBytes("UTF-8");

         MessageDigest md1 = MessageDigest.getInstance("MD5");
         byte[] thedigestscss = md1.digest(bytes);

         //compare mhash with mshash
         //if(matched){
         if(Arrays.equals(thedigestscss,thedigest)) {
        	 flagsecure =true;
             return flagsecure;
             //send true 
         }
		return flagsecure;
    }*/
	
	//public static boolean certsign(Certificate cert) throws UnsupportedEncodingException, NoSuchAlgorithmException{
	public static  void certsign(X509Certificate cert) throws Exception{
			
		
	   // boolean flag_s_value =secure_channel();
	   // if(flag_s_value){
	    // send cert for signing to server
	    				//inside cert sign function- get email id from cert at server side
	    				//send OTP to user email ,ask for OTP from user ,verify OTP
	    				//generate node id - hex 160 bit - at server
	    				//sign cert,add node id
	    	    	//return back to client
	    	
			//boolean certsign = false;    	
	        //String certificate = null;
			System.out.println("Welcome to CertificateSignature .Sending new cert to Iden Server for sign");
			//System.out.println("recieved certificate for signatue is     :"+ cert);
			String certstring=cert.toString();
			
						
			//System.out.println("String format of recieved certificate for signatue is     :"+certstring);
		
			String mserverurl ="http://localhost:8080/IS";						
								
			//example-String url = "http://example.com/query?q=" + URLEncoder.encode(q, "UTF-8");			
			String MSrequrl = mserverurl +"/ProcessRequest?req=sscccertsign&cert=" + URLEncoder.encode(certstring, "UTF-8");
				
			createConnection http = new createConnection();

			//System.out.println("Testing 1 - Send Http GET request");
			http.sendGet(MSrequrl);
			
			//System.out.println("\nTesting 2 - Send Http POST request");			
			http.sendPost(MSrequrl);
	
			//return cert;
			
	        //Vector result2 = HttpsUtil.getsigncert(MSrequrl);
	       // X509Certificate scsscert = (X509Certificate) result2.get(2);
	        //System.out.println("signed certificate is  =" + scsscert);
			//return scsscert;
			
	        //save this signed certificate in keystore- to be done.
			  	    	
	    }
	    //else{
	    //	System.out.println("secure channel not created properly.try again and then send certificate for signature");
	    //}	    
	//} 	
    }

