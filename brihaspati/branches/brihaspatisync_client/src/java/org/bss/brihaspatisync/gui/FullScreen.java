package org.bss.brihaspatisync.gui;

/**
 *  FullScreen.java
 *  
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2012 ETRG,IIT Kanpur.
 **/

import java.awt.Window;
import java.awt.Toolkit;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
/**
 *@author <a href="mailto:arvindjss17@gmail.com">Arvind pal </a>
 **/

public class FullScreen  
{
	private Window f=null;
	private Dimension screensize =null;	
	private static FullScreen fullscreen=null;
		
	public static FullScreen getController(){
                if (fullscreen==null){
                        fullscreen=new FullScreen();
                }
                return fullscreen;
        }

	public FullScreen() {
		f =new Window(new JFrame());
		screensize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setBounds(0,0,screensize.width,screensize.height);
                f.setVisible(true);	
	}
	public void screenshare(JLayeredPane mainpanel) 
  	{
    		f.add(mainpanel);
		
  	}
	
	public void disposescreenshare()
        {
                f.dispose();
                fullscreen=null;

        }
}
