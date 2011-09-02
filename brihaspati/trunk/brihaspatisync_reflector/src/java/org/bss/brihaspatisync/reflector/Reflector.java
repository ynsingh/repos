package org.bss.brihaspatisync.reflector;

/**
 * Reflector.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009, 2011 ETRG, IIT Kanpur.
 */

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> created on 28Jan2009.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> modify on 08March2011.
 * @author <a href="mailto:ynsingh@iitk.ac.in">Y.N.Singh</a>.
 * History of changes.
 * 20110330 - YNS - GUI based message output for non-GUI system start replaced by simple print output on console. RegisterToIndexServer
 *    getcontroller is now called only once in startReflector() and the object reference is maintained for use later in same function,
 *    instead calling it twice.
 */

public class Reflector {
	
	private static Reflector reflector=null;
        private JButton button=null;
        private boolean flag=false;// Flag to check reflector running status.
        private boolean startflag=false;// Flag to check reflector running status.
        private JLabel label1 = new JLabel();
    	
	/**
	 * Controller for this class
	 */
	public static Reflector getController(){
		if(reflector==null)
			reflector=new Reflector();
		return reflector;
	}
	
	/**
         * init(String str) - This functions starts the reflector in either text mode or GUI mode
	 * depending on the whether str is "startjnlp" or "start" respectively.
	 */
	private void init(String str){
		if(str.equals("startjnlp")) {	
			System.out.println("Starting Reflector!");
          		try{
                		JFrame frame = new JFrame("Brihaspatisync Reflector");
	                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        	                button = new JButton("Ask!");
                	        button.addActionListener(actionListener);
                        	Container contentPane = frame.getContentPane();
	                        contentPane.add(label1,BorderLayout.CENTER);
        	                contentPane.add(button, BorderLayout.SOUTH);
                	        frame.setSize(250, 120);
                        	frame.setLocation(400, 500);
	                        frame.setVisible(true);
				frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);

        	      	}catch(Exception e){
                		System.out.println("Error on starting reflector :"+e.getCause());
              		}
		}else if(str.equals("start")){
			if(!flag) {
				if(startReflector()){
					RegisterToIndexServer.getController().connectToIndexServer();
                        		System.out.println("Reflector started successfully.");
                               	}else { 
                			System.out.println("There is problem in starting Reflector.");
                             	}
                     	}else {
                        	System.out.println("Reflector is already running.");
			}
		}
	}      	

	/**
         * startReflector() - It calls RegisterToIndexServer() for registering this reflector 
	 * to an index server from index server list (index server list would be 
         * found from master server) with its IP Address and status. On successful 
	 * registration it will return list of indexing servers. 
	 */
	private boolean startReflector() {
		Vector indexServerList=RegisterToIndexServer.getController().connectToMasterServer();
		String str1[]=new String[indexServerList.size()];
                for(int i=0;i<indexServerList.size();i++)
                	str1[i]=indexServerList.get(i).toString();
		JComboBox combo = new JComboBox(str1);
                Object[] message = new Object[] {"Select I_Server ",combo};
                int r = JOptionPane.showConfirmDialog(null, message, "I_Server", JOptionPane.OK_CANCEL_OPTION);
                if (r == JOptionPane.OK_OPTION) {
			RegisterToIndexServer.getController().setIServerIP((String)combo.getSelectedItem());
		}	
                if(indexServerList.size()>0){
                	flag=true;
              	}
		return flag;
	} 

	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
                        Component source = (Component) actionEvent.getSource();
			if(!startflag) {
				if(!startReflector()){
        	                	label1.setText("There is problem in starting Reflector.");
					return;
                       		}
			}
	
			Object response = JOptionPane.showInputDialog(source,"Choose options!","Select a Destination", JOptionPane.QUESTION_MESSAGE,null, new String[] {"Start Reflector", "Stop Reflector and Exit "},"Start Reflector");
                        String response1=response.toString();
                        if(response1.startsWith("Start Reflector")) {
                                if(!startflag) {
					startflag=true;
					RegisterToIndexServer.getController().connectToIndexServer();
					label1.setText("Reflector started successfully.");
                                }else {
                                      	System.out.println("Reflector is already running.");
                                }
                        }else if(response1.startsWith("Stop Reflector")) {
                                LogoutReflector.getController().stopReflector();
                                label1.setText("Reflector stoped successfully.");
                                System.exit(0);
			} 

                }
	};
	
	/**
	 * Main Method to start reflector.
	 */
	public static void main(String args[]) throws Exception {
		if(args[0].equals("startjnlp"))
			Reflector.getController().init(args[0]);
		if(args[0].equals("start"))	
			Reflector.getController().init(args[0]);
	}
}
