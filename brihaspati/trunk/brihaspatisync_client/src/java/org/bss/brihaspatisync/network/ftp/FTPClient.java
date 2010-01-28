package org.bss.brihaspatisync.network.ftp;

/**
 * FTPClient.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */

import java.net.Socket;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;

import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.gui.JoinSession;


import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.usermodel.PictureData;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.awt.geom.Rectangle2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;



/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class FTPClient {

	private static FTPClient ftpclient=null;
	private Socket sock;
        private DataInputStream din;
        private DataOutputStream dout;
	private boolean flag=false;
	private Log log=Log.getController();
	private int port=RuntimeDataObject.getController().getClientFTPPort();
	private ClientObject client_obj=ClientObject.getController();

	public static FTPClient getController(){
		if(ftpclient==null){
			ftpclient=new FTPClient();
		}
		return ftpclient;
	}
	
	private FTPClient(){ }

	public void startFTPClient(String fileformate){
		try{
         		if(!flag ){
                                sock=new Socket(ClientObject.getController().getReflectorIP(),port);
                                din=new DataInputStream(sock.getInputStream());
                                dout=new DataOutputStream(sock.getOutputStream());
				String lect_id=client_obj.getLectureID();
				/** if POST command receive 
				 * means Send file to reflector 
				 */
				if(fileformate.equals("POST")){
                                        dout.writeUTF("POST,"+lect_id);
                                        SendFile();
                                }
				/** if GET command receive
                                 * means Receive File from reflector
                                 */
				else if(fileformate.equals("GET")){
                                        dout.writeUTF("GET,"+lect_id);
                                        ReceiveFile();
                                }
				Thread.yield();
                               	Thread.sleep(10000);
                        }
                }catch(Exception e){
                        flag=false;
                        log.setLog("Error on FTPClient=>"+e.getMessage());
                }
	}
	/**
         * This method is used to Send ppt File to reflector
         */
	private void SendFile() throws Exception
        {
                System.out.println("POST Command Received ...");
                String filename="temp/presentation.ppt";
                File f=new File(filename);
                if(!f.exists())
                {
                        dout.writeUTF("File Not Found");
			System.out.println(".ppt File not send ");
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
				flag=true;
                }
        }
	/**
	 * This method is used to Receive ppt File from reflector 
	 */
	private void ReceiveFile() throws Exception {
                String fileName="presentation.ppt";
                String msgFromServer=din.readUTF();
                if(msgFromServer.compareTo("File Not Found")==0)
                {
			flag=false;
			System.out.println(".ppt File not found on Server ...");
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
				createppt_TO_Images();
				flag=true;
			}else{
				flag=false;	
			}
                }
        }

	/**
	 * This method is used to create ppt to image
	 */	
        public void createppt_TO_Images() 
        {
		try {
			File pptfile=new File("temp/presentation.ppt");
                        pptfile=new File(pptfile.getAbsolutePath().toString());
			if(pptfile.exists()){	
				SlideShow ppt1 = new SlideShow(new HSLFSlideShow("temp/presentation.ppt"));
                        	PictureData[] pdata = ppt1.getPictureData();
                        	FileInputStream is = new FileInputStream("temp/presentation.ppt");
                        	SlideShow ppt = new SlideShow(is);
                        	is.close();
                        	Dimension pgsize = ppt.getPageSize();
                        	Slide[] slide = ppt.getSlides();
                        	for (int i = 0; i < slide.length; i++) {
                                	BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
					//TYPE_INT_RGB);
                                	Graphics2D graphics = img.createGraphics();
                                	//clear the drawing area
                                	graphics.setPaint(Color.white);
                                	graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width+500, pgsize.height+500));
                                	//render
                                	slide[i].draw(graphics);
                                	//save the output
                                	FileOutputStream out = new FileOutputStream("temp/presentation/"+"image"+(i)+".png");
                                	javax.imageio.ImageIO.write(img, "png", out);
                                	out.close();
                        	}
			}else {
				System.out.println(".ppt file is not found !! ");
			}
		}catch(Exception e){log.setLog("Error in createppt_TO_Images() method ----->"+e.getMessage());}
		
        }
	/** checkDirectory is available or not */
		
	public void checkDirectory(){
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
                                temp2.delete();
                        }
                }
		File f=new File("temp"+"/"+"presentation.ppt");
                if(f.exists())
                        f.delete();
	}
}
