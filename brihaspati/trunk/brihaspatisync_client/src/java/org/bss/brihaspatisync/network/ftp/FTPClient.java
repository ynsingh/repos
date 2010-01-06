package org.bss.brihaspatisync.network.ftp;

/**
 * FTPClient.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import java.net.Socket;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;

import java.util.zip.ZipFile;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.gui.JoinSession;
/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class FTPClient {

	private static FTPClient ftpclient=null;
	private Socket sock;
        private DataInputStream din;
        private DataOutputStream dout;
	private boolean flag=true;
	private ZipFile zipfile;
	private String destDir;
	private Log log=Log.getController();
	
	public static FTPClient getController(){
		if(ftpclient==null){
			ftpclient=new FTPClient();
		}
		return ftpclient;
	}
	
	public boolean getFleg(){
		return this.flag;
	}

	private FTPClient(){ }

	public void  startFTPClient(){
		try{
         		while(flag ){
                                sock=new Socket(ClientObject.getController().getReflectorIP(),5217);
                                din=new DataInputStream(sock.getInputStream());
                                dout=new DataOutputStream(sock.getOutputStream());
                                flag=false;
                                dout.writeUTF("GET");
                                log.setLog("GET command send ---->");
                                ReceiveFile();
				Thread.yield();
                               	Thread.sleep(10000);

                        }
                }catch(Exception e){
                        flag=true;
                        log.setLog("Error on FTPClient=>"+e.getMessage());
                }

	}

	private void ReceiveFile() throws Exception{

                String fileName="presentation.zip";
              	
                String msgFromServer=din.readUTF();
                if(msgFromServer.compareTo("File Not Found")==0)
                {
                        log.setLog("File not found on Server ...");
			flag=true;
                        //return;
                }
                else if(msgFromServer.compareTo("READY")==0){
			checkDirectory();
                        FileOutputStream fout=new FileOutputStream(new File("temp"+"/"+fileName));
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
			log.setLog(succ);
			if(succ.equals("File Receive Successfully")){
				GetUnzip();
				flag=false;
					
			}else{
				flag=true;	
			}
                }
        }

	private void GetUnzip(){
               try {
                        log.setLog("Start unzip File to this method GetUnzip()");
                        GetUnzip1("temp/presentation.zip","temp/presentation");
                }catch(Exception e){log.setLog("Error in GetUnzip() method ----->"+e.getMessage());}
        }

        private void GetUnzip1(String source,String destination) 
        {
		try {
			log.setLog("Start unzip File to this method GetUnzip1(String Path1,String Path2)");
        	        zipfile=new ZipFile( source );
                	if(destination==null)
	                {
        	                destDir="";
                	}
	                else
        	        {
                	        destDir=destination+"/";
			}
	                doUnZip();
        	        zipfile.close();
		}catch(Exception e){log.setLog("Error in GetUnzip1 method ----->"+e.getMessage());}
        }

        private void doUnZip() throws IOException
        {
		try {
	                Enumeration entries=zipfile.entries();
        	        while(entries.hasMoreElements())
                	{
                        	ZipEntry ze=(ZipEntry)entries.nextElement();
	                        File f=new File(destDir+ze.getName());
        	                if(ze.isDirectory())
                	        {
                        	        f.mkdirs();
                        	}
				else{
					FileOutputStream fos=new FileOutputStream(f);
                        	        InputStream in=zipfile.getInputStream(ze);
                                	int len;
	                                byte[] buf = new byte[1024];
        	                        while ((len = in.read(buf)) > 0)
                	                {
                        	                fos.write(buf, 0, len);
                                	}
	                                fos.close();
        	                }
                	}
		}catch(Exception e){log.setLog("Error in doUnzip method ----->"+e.getMessage());}
        }
		
	public void checkDirectory(){
		log.setLog("Check Directory Path Path name =====> ");
		File f=new File("temp"+"/"+"presentation.zip");
		if(f.exists())
			f.delete();
		File dest=new File("temp");
                if(!dest.exists())
                        dest.mkdir();
                dest=new File("temp/presentation");
                if(!dest.exists())
                        dest.mkdir();
                String str[]=dest.list();
                if(str.length != 0){
                        File temp1=new File("temp/presentation/");
                        for(int i=0;i<str.length;i++){
                                File temp2=new File(temp1.toString()+"/"+str[i]);
				log.setLog("deleting file from===========>"+temp2);
                                temp2.delete();
                        }
                }
	}
}
