package com.ehelpy.brihaspati4.indexmanager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpsServer extends IndexManagement{
	
	public void server(String file_to_pass) throws IOException
	 {
		System.out.println("**********Server Program**************");
		System.out.println("file to pass  " + file_to_pass);
	    ServerSocket servsock = new ServerSocket(2315);
	    
	    if(!servsock.isBound())
            System.out.println("Sever Socket not Bounded...");
        else
            System.out.println("Server Socket bounded to Port : "+servsock.getLocalPort());
	    
	    File myFile = new File(file_to_pass);
	    while (true) 
	    {
	      Socket sock = servsock.accept();
	      
	      if(!sock.isConnected())
	            System.out.println("Client Socket not Connected...");
	        else
	            System.out.println("Client Socket Connected : "+ sock.getInetAddress());
	      
	      byte[] mybytearray = new byte[(int) myFile.length()];
	      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
	      bis.read(mybytearray, 0, mybytearray.length);			// no. of byte received
	      
	    //  System.out.println("No. of Bytes Received : "+bis.read(mybytearray, 0, mybytearray.length));
	      OutputStream os = sock.getOutputStream();
	      os.write(mybytearray, 0, mybytearray.length);
	      os.flush();
	      bis.close();
	      
	   
	      //sock.setPerformancePreferences(3, 2, 1);
	     // sock.setReuseAddress(true);
	      //sock.setSoTimeout(5);
	      sock.close();
	    //  servsock.close();
	      
	    }
	 }
		
	public void server_handle_request(String file_to_pass) throws IOException
	 {
		System.out.println("**********Server_Handle _Request Program**************");
		System.out.println("file to pass  " + file_to_pass);
	    ServerSocket servsock = new ServerSocket(2316);
	    
	    if(!servsock.isBound())
           System.out.println("Sever Socket not Bounded...");
       else
           System.out.println("Server Socket bounded to Port : "+servsock.getLocalPort());
	    
	    File myFile = new File(file_to_pass);
	    while (true) 
	    {
	      Socket sock = servsock.accept();
	      
	      if(!sock.isConnected())
	            System.out.println("Client Socket not Connected...");
	        else
	            System.out.println("Client Socket Connected : "+ sock.getInetAddress());
	      
	      byte[] mybytearray = new byte[(int) myFile.length()];
	      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
	      bis.read(mybytearray, 0, mybytearray.length);			// no. of byte received
	      
	    //  System.out.println("No. of Bytes Received : "+bis.read(mybytearray, 0, mybytearray.length));
	      OutputStream os = sock.getOutputStream();
	      os.write(mybytearray, 0, mybytearray.length);
	      os.flush();
	      bis.close();
	      
	      sock.close();
	    //  servsock.close();
	      
     
	    }
	   // servsock.close();
	 }
	
}

