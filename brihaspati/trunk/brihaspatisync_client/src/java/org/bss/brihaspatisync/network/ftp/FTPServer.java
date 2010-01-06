package org.bss.brihaspatisync.network.ftp;

/**
 * FTPServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import java.net.Socket;
import java.net.ServerSocket;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class FTPServer implements Runnable{

	private static FTPServer ftpserver=null;
	private Socket sock=null;
	private ServerSocket serv=null;
        private DataInputStream din;
        private DataOutputStream dout;
	private Thread runner=null;
	private Log log=Log.getController();


	public static FTPServer getController(){
		if(ftpserver==null)
			ftpserver=new FTPServer();
		return ftpserver;
	}

	private FTPServer(){
		try{
			serv=new ServerSocket(5217);
            		log.setLog("FTP Server Started on Port Number 5217");
		}catch(Exception e){log.setLog("Error on Creating Socket for FTP==>"+e.getMessage());}

	}

	public void startThread(){
		if(runner==null){
			runner=new Thread(this);
			runner.start();
		}
	
	}

	public void stopThread(){
		if(runner!=null){
			runner.interrupt();
			runner.stop();
		}
	}

	public void run(){
		while(true){
			try{	
				sock=serv.accept();
				synchronized(sock){
					din=new DataInputStream(sock.getInputStream());
                	        	dout=new DataOutputStream(sock.getOutputStream());
                        		log.setLog("FTP Client Connected ...");
                        		String Command=din.readUTF();
					try{
        	                		if(Command.compareTo("GET")==0){
                        	        		SendFile();
	                        		}
	 				}catch(Exception e){log.setLog("Error at handling FTP Request=>"+e.getMessage());}
				}
			}catch(Exception ex){log.setLog("Error at FTPServer Thread=>"+ex.getMessage());}
		}
	}

	private void SendFile() throws Exception
        {
                log.setLog("GET Command Received ...");
                String filename="temp/presentation.zip";
                File f=new File(filename);
                if(!f.exists())
                {
                        dout.writeUTF("File Not Found");
                        return;
                }
                else
                {
                        dout.writeUTF("READY");
                        FileInputStream fin=new FileInputStream(f);
                        int ch;
                        do
                        {
                                ch=fin.read();
                                dout.writeUTF(String.valueOf(ch));
                        }
                        while(ch!=-1);
                        fin.close();
                        dout.writeUTF("File Receive Successfully");
                }

        }


	public Thread getRunner(){
		return this.runner;
	}
}
