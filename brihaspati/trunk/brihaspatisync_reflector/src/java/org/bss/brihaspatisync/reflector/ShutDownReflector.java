package org.bss.brihaspatisync.reflector;

/**
 * ShutDownReflector.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013, ETRG, IIT Kanpur.
 */

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 */

public class ShutDownReflector {
	
	public ShutDownReflector(){}
	public static void main(String ar[]) {
		try {
			
			HttpClient client = new HttpClient();
                        PostMethod postMethod = new PostMethod("http://localhost:8081");	
			postMethod.setRequestBody(new java.io.ByteArrayInputStream("sdasdada".getBytes()));
                        postMethod.setRequestHeader("session","stop"+","+"stop"+","+"stop");
			int statusCode = client.executeMethod(postMethod);
			if(statusCode ==200) {
				byte[] receive_data_fromserver=postMethod.getResponseBody();
				System.out.println("Reflector is Successfully Loggedout");
				if(receive_data_fromserver !=null)
					System.out.println(new String(receive_data_fromserver));
			}
                        postMethod.releaseConnection();
			
		}catch(Exception e) { 	System.out.println("\n\n ###########    Reflector is already Loggedout !! ########### "+e.getMessage()); } 
	}
}
