package org.bss.brihaspatisync.network.ppt_sharing;

/**
 * GetAndPostPPT.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.usermodel.PictureData;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;

import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class GetAndPostPPT {

	private final int IMG_WIDTH = 800;
        private final int IMG_HEIGHT = 700;
	private static GetAndPostPPT client=null;
	private boolean flag=false;
	private int port =RuntimeDataObject.getController().getPPTPort();

	private ClientObject client_obj=ClientObject.getController();


	public static GetAndPostPPT getController(){
		if(client==null){
			client=new GetAndPostPPT();
		}
		return client;
	}
	
	private GetAndPostPPT(){ }

	public void startFTPClient(String fileformate) {
		try{
			String lect_id=client_obj.getLectureID();
			if(fileformate.equals("POST")){
                        	SendFile(lect_id);
                      	}
                }catch(Exception e){ System.out.println("Error on FTPClient=>"+e.getMessage()); }
	}
	
	/**
         * This method is used to Send ppt File to reflector
         */
	
	private void SendFile(String lect_id) throws Exception {
                System.out.println("POST Command Received ...");
                String filename="temp/presentation.ppt";
		File f=new File(filename);
		checkDirectory();
		while(true) {	
			if(f.exists()){
				createppt_TO_Images();
				break;
			}
			Thread.yield();
                        Thread.sleep(1000);
		}
        }
	/**
	 * This method is used to create ppt to image
	 */	
        private void createppt_TO_Images() {
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
                                	FileOutputStream out = new FileOutputStream("temp/"+"image"+(i)+".jpeg");
                                	javax.imageio.ImageIO.write(img, "jpeg", out);
                                	out.close();
					BufferedImage originalImage = ImageIO.read(new File("temp/"+"image"+(i)+".jpeg"));
	        	                int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        	        	        BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                        		ImageIO.write(resizeImageJpg, "jpeg", new File("temp/"+"image"+(i)+".jpeg"));
                        	}
			}else {
				System.out.println(".ppt file is not found !! ");
			}
			org.bss.brihaspatisync.tools.presentation.PresentationPanel.getController().setlabelText();			
			pptfile.delete();
		}catch(Exception e){System.out.println("Error in createppt_TO_Images() method ----->"+e.getMessage());}
		
        }
		
	private BufferedImage resizeImage(BufferedImage originalImage, int type) {
                BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
                g.dispose();
                return resizedImage;
        }
	
	/** checkDirectory is available or not */
		
	public void checkDirectory() {
		File dest=new File("temp");
                if(!dest.exists())
                        dest.mkdir();
                String str[]=dest.list();
                if(str.length != 0){
                        File temp1=new File("temp/");
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
