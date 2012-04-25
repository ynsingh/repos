package babylon;
/**
 * @(#)babylonServerStart_Stop.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.net.URL;
import java.net.InetAddress;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.BufferedInputStream;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">  Arvind Pal  </a>
 * @author <a href="mailto:shaistashekh@gmail.com"> Shaista Bano</a>
 */

public class babylonServerStop {
	
	public static void main(String args[]) throws IOException {
		HttpURLConnection connection=null;
                try{
			Thread.sleep(1000);
			String url="http://127.0.0.1:9991/"+args[0];
	                URL serverAddress = new URL(url);
        	        connection = (HttpURLConnection)serverAddress.openConnection();
                	connection.setRequestMethod("GET");
	                connection.setDoOutput(true);
        	        connection.connect();
			try{
			        DataInputStream dis =new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                                String str="";
                                while((str=dis.readUTF())!=null){ 	}
                                dis.close();
                     	}catch(Exception ep){
                        	try {
                                	connection.disconnect();
                                        connection=null;
                               	}catch(Exception ex){
                                	System.out.println("connection is not disconnect()"+connection);
                                }
                  	}
              	}catch(Exception ep){
                	try {
                        	connection.disconnect();
                                connection=null;
			}catch(Exception ex){
                  	}
        	}
	}
	
}

	
