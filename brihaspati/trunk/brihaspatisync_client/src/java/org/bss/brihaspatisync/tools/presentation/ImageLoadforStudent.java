package org.bss.brihaspatisync.tools.presentation;

/**
 * ImageLoadforStudent.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2008-2009 ETRG, IIT Kanpur
 */

 
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.io.File;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
	
import org.bss.brihaspatisync.network.Log;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */


public class ImageLoadforStudent extends JPanel {

	private Image image=null;

	private String LogfilePath="";

	private Dimension area=new Dimension(0,0);

	private static ImageLoadforStudent img=null;	
	
	public static ImageLoadforStudent  getController() {
                if(img==null)
                        img=new ImageLoadforStudent();
                return img;
        }

	private ImageLoadforStudent() {
		setBackground(Color.white);
	}
	
	public void update(Graphics g) {
		paint(g);  
	}   

	public void paint(Graphics g) {
			if(image==null)
				g.setColor(Color.white);
				g.fillRect(0,0,image.getWidth(this),image.getHeight(this));
			if(image != null){			
				g.drawImage(image, 0, 0,image.getWidth(this),image.getHeight(this),this);
			}
	}
	
	public void runSlide(int temp){
		try {
			if(LogfilePath.equals("")) {
				String str="temp/";
	        	        File existingFile=new File(str);
        	        	LogfilePath = existingFile.getAbsolutePath();
			}
			String str=LogfilePath+"/image"+Integer.toString(temp)+".png";
                	str=str.trim();
			image = ImageIO.read(new File(str));
			area.width=image.getWidth(this);
		        area.height=image.getHeight(this);
			img.setPreferredSize(area);
                        img.revalidate();
			img.repaint();
		}catch(Exception e){}
	}
}
