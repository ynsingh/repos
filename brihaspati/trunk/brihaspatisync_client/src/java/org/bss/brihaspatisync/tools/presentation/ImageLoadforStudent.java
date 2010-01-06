package org.bss.brihaspatisync.tools.presentation;

/**
 * ImageLoadforStudent.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2008-2009 ETRG, IIT Kanpur
 */

 
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.MediaTracker;

import java.io.File;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.Dimension;
import org.bss.brihaspatisync.network.Log;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */


public class ImageLoadforStudent extends JPanel {
	private MediaTracker tracker=null;
	private Image image=null;
	private static ImageLoadforStudent img=null;	
	
	private File f=new File("./temp/presentation");
        private String str[]=null;
	private Log log=Log.getController();

	
	public static ImageLoadforStudent  getController() {
                if(img==null)
                        img=new ImageLoadforStudent();
                return img;
        }
	
	public void runSlide(int temp) {
		try{
			image=Toolkit.getDefaultToolkit().createImage(f.toString()+"/"+"image"+Integer.toString(temp)+".png");	
                       	tracker = new MediaTracker(this);
       	              	tracker.addImage(image,0);
			tracker.waitForID(0);

			repaint();		
		}catch(Exception ex) {
			log.setLog("ERROR on load image===>"+ex.getMessage()); 
		}
	}

	private ImageLoadforStudent() {
		setBackground(Color.white);
	}
	
	public void update(Graphics g) {
		paint(g);  
	}   

	public void paint(Graphics g) {
		g.setColor(Color.white);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		if(image==null)
			g.fillRect(0,0,((int)screenSize.getWidth()),((int)screenSize.getHeight()));
		if(image != null){
			g.setColor(Color.white);  
			g.drawImage(image, 0, 0,((int)screenSize.getWidth()),((int)screenSize.getHeight()),this);
		} 
	}     
}
