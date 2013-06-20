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
	
	public static void main(String ar[]) {
		ShutDownReflector post_screen=new ShutDownReflector();
	}

	public ShutDownReflector(){ 
		try {	
			java.util.Scanner in = new java.util.Scanner(System.in);
			System.out.println();
			System.out.println("Enter the ip address at which the Reflector is running !!  ");
			String ip = in.nextLine();
               		sendDataToReflector(ip);
			in.close();
		}catch(Exception ep){ System.out.println(" Exception  "+this.getClass()+ep.getMessage());}
	}
	
	/**
	 * This method is used to send the data client to reflector .
	 */
	private void sendDataToReflector(String ip){
		try {
			
			HttpClient client = new HttpClient();
                        PostMethod postMethod = new PostMethod("http://"+ip+":8081");
                        client.setConnectionTimeout(8000);
                        postMethod.setRequestHeader("session","stop"+","+"stop"+","+"stop");
			int statusCode = client.executeMethod(postMethod);
			if(statusCode != 200)
				System.out.println(" ip address is not correct ");
			byte[] receive_data_fromserver=postMethod.getResponseBody();
                        postMethod.releaseConnection();
			
		}catch(Exception e) { 
			System.out.println("Exception "+ this.getClass()+" to stop reflector "+e.getMessage());
		} 
	}
}
