package org.bss.brihaspatisync.reflector.network.ftp;


/*
 * MultiServerThread.java 
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.net.Socket;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class FTPHandlers implements Runnable {
	
	private Thread runner=null;
	
	private DataInputStream din=null;
	
	private DataOutputStream dout=null;
	
		
	private Socket socket = null;
	
	private static FTPHandlers mst=null;
	
	private FTPHandlers() {
       	}
	
	protected FTPHandlers(Socket socket) {
		this.socket=socket;
		System.out.println("start new thread MultiServerThread-");
	}
	
	/**
        * Start TCPSender Thread.
        */
        public void start(){
                if (runner == null) {
                        runner = new Thread(this);
                        runner.start();
                        System.out.println("TCP Sender Start");
                }
        }

        /**
         * Stop TCPSender Thread.
         */
        private void stop() {
                if (runner != null) {
                        runner.stop();
                        runner = null;
                        System.out.println("TCP Sender Stop");
                }
        }

	
	public void run(){
		try {		
			din=new DataInputStream(socket.getInputStream());
                        dout=new DataOutputStream(socket.getOutputStream());
                        System.out.println("FTP Client Connected ...");
                        String Command=din.readUTF();
                        String str[]=Command.split(",");
                        System.out.println("str[0]"+str[0]+" str[1]"+str[1]);
                        try{
                        	if(str[0].compareTo("GET")==0){
                                	SendFile(str[1]);
                               	}
                      	}catch(Exception ex){
                        	System.out.println("Error at handling FTP Request=>"+ex.getMessage());
                      	}
                        try{
                        	if(str[0].compareTo("POST")==0){
                                	ReceiveFile(str[1]);
                                }
                   	}catch(Exception ex){
                       		System.out.println("Error at handling FTP Request=>"+ex.getMessage());
                      	}
		}catch(Exception e){
                        System.out.println("Error in Catch FTP class line no 70 ");
                }
	}

	private void SendFile(String userid) throws Exception
        {
                System.out.println("GET Command Received userid ..."+userid);
                String filename="temp/"+userid+"/"+"presentation.ppt";
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
			stop();
                }

        }
	
	private void ReceiveFile(String userid) throws Exception{
                System.out.println("GET Command Received .. userid ."+userid);
                String fileName="presentation.ppt";
                String msgFromServer=din.readUTF();
                if(msgFromServer.compareTo("File Not Found")==0)
                {
                        System.out.println("File not found on Server ...");
                }
                else if(msgFromServer.compareTo("READY")==0){
                        checkDirectory(userid);
                        FileOutputStream fout=new FileOutputStream(new File("temp/"+userid+"/"+fileName));
                        int ch;
                        String temp;
                        do
                        {
                                temp=din.readUTF();
                                ch=Integer.parseInt(temp);
                                if(ch!=-1)
                                {
                                        fout.write(ch);
                                }
                        }while(ch!=-1);
                        fout.close();
                        String succ=din.readUTF();
                        System.out.println(succ);
			stop();
                }
        }

	public void checkDirectory(String userid){
                System.out.println("Check Directory Path Path name =====> ");
                File f=new File("temp/"+userid+"/"+"presentation.ppt");
                if(f.exists()) {
                        f.delete();
                        System.out.println("Delete presentation.ppt file successfuly !!");
                }
                File dest=new File("temp");
                if(!dest.exists()){
                        dest.mkdir();
                        System.out.println("make dir  ");
                }
                dest=new File("temp/"+userid);
                if(!dest.exists()){
                        dest.mkdir();
                        System.out.println("make dir  ");
                }
        }


}
