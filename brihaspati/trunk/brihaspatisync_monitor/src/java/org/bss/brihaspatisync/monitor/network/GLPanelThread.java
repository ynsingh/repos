package org.bss.brihaspatisync.monitor.network;

/**
 * GLPanelThread.java
 * 
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import java.util.Vector;
import java.io.File;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import org.bss.brihaspatisync.monitor.gui.MainWindow;
import org.bss.brihaspatisync.monitor.graphlayout.GLPanel;
import org.bss.brihaspatisync.monitor.graphlayout.TGPanel;
import javax.swing.JFrame;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */    
	
public class GLPanelThread extends Thread {
	
	private Vector ReflectorsRunning=null;
        private Vector CoursesRunning=null;
	private  Vector load=null;
	static String refIP;	
	public GLPanelThread() {}
	
	public void run(){
		try {	
			while(true){
				 try {   this.sleep(1000);this.yield();}catch(Exception e){}
				      
				      		TGPanel tgPanel=new TGPanel();
						Vector load=new Vector();
				      	        load=tgPanel.getLoad();
						int size=load.size();
						String a= load.lastElement().toString();
						String b= load.elementAt(size-2).toString();

						if(!a.equals(b)){
							GLPanel glPanel=new GLPanel();
                                                	MainWindow.getController().getRightPanel().remove(1);
							MainWindow.getController().getRightPanel().add(MainWindow.getController().createMonitorPanel(),BorderLayout.PAGE_START);
                                                	MainWindow.getController().getRightPanel().add(glPanel,BorderLayout.CENTER);
                                                	MainWindow.getController().getRightPanel().revalidate();
						}
                                        
			 }
						
		}catch(Exception e){System.out.println("Request time out error in GLPanel Thread"+e.getMessage());}
	}
}



