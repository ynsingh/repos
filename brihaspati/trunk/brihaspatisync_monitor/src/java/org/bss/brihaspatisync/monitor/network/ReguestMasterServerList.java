package org.bss.brihaspatisync.monitor.network;

/**
 * ReguestMasterServerList.java
 *  
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import java.util.Vector;
import java.io.File;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import org.bss.brihaspatisync.monitor.*;
import org.bss.brihaspatisync.monitor.gui.MainWindow;
import javax.swing.JFrame;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */
	
public class ReguestMasterServerList extends Thread {
	
	private Vector ReflectorsRunning=null;
        private Vector CoursesRunning=null;
	
	public ReguestMasterServerList() {}
	
	public void run(){
		try {	
			while(true){
				try {	this.sleep(1000);this.yield();}catch(Exception e){}				                                     		
		                          MainWindow.getController().getLeftPanel().remove(1);
		                          MainWindow.getController().getLeftPanel().add(org.bss.brihaspatisync.monitor.gui.TreeMenu.getController().createGUI(),BorderLayout.CENTER);
					  MainWindow.getController().getLeftPanel().revalidate();
					  System.gc();
					  org.bss.brihaspatisync.monitor.RegisterToIndexServer.getController().connectGetReflectorList();
                                	  org.bss.brihaspatisync.monitor.RegisterToIndexServer.getController().connectGetIndexServerList();


                      	}
		}catch(Exception e){System.out.println("Request time out error in ReguestMasterServerList"+e.getMessage());}
	}
}


