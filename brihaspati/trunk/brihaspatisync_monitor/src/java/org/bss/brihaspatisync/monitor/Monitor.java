package org.bss.brihaspatisync.monitor;

/**
 * Monitor.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *@author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *@author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 *@date 05/12/2011
 */


public class Monitor {

        private boolean flag=false;
	private static Monitor reflector=null;
	
	/**
	 * Controller for this calss
	 */

	public static Monitor getController(){
		if(reflector==null)
			reflector=new Monitor();
		return reflector;
	}

	private void startMonitor(){
		org.bss.brihaspatisync.monitor.ReflectorManager.getController().add();
		org.bss.brihaspatisync.monitor.gui.MainWindow.getController();
		org.bss.brihaspatisync.monitor.gui.CheckStatusServer.getController().start();
	}
	
	/**
	 * Main Method to start reflector.
	 */

	public static void main(String args[]) {
		Monitor.getController().startMonitor();
	}
}
