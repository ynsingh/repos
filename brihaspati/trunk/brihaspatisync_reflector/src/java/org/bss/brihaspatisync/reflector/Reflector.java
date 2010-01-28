package org.bss.brihaspatisync.reflector;

/**
 * Reflector.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> created on 28Jan2009.
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> modify on 10Feb2009.
 */

public class Reflector {
	
	private static Reflector reflector=null;
        private JButton button=null;
        private boolean flag=false;
        private JLabel label1 = new JLabel();
    	
	/**
	 * Controller for this calss
	 */

	public static Reflector getController(){
		if(reflector==null)
			reflector=new Reflector();
		return reflector;
	}
	
	/**
         * Constructor, call RegisterToIndesServer for registering this reflector 
	 * to an index server from index server list (index server list would be 
         * found from master server )with it's IP Address and status.On successfull 
	 * registration it will return index serverlist 
	 */
	private void startReflector(){
		System.out.println("Starting Reflector !!!!!!!!");
          	try{
                	JFrame frame = new JFrame("Reflector Popup");
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        button = new JButton("Ask !!");
                        button.addActionListener(actionListener);
                        Container contentPane = frame.getContentPane();
                        contentPane.add(label1,BorderLayout.CENTER);
                        contentPane.add(button, BorderLayout.SOUTH);
                        frame.setSize(250, 120);
                        frame.setLocation(400, 500);
                        frame.setVisible(true);
              	}catch(Exception e){
                	JOptionPane.showMessageDialog(null,"Error on starting reflector :"+e.getCause());
              	}
	}      	
	 
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
                        Component source = (Component) actionEvent.getSource();
			Object response = JOptionPane.showInputDialog(source,"Chose options !!","Select a Destination", JOptionPane.QUESTION_MESSAGE,null, new String[] {"Start Reflector", "Stop Reflector and Exit ","Restart Reflector"},"Start Reflector");
                        String response1=response.toString();
                        if(response1.startsWith("Start Reflector")) {
                                if(!flag) {
					boolean indexServerList=RegisterToIndexServer.getController().connectToMasterServer();
                                        if(indexServerList){
                                                flag=true;
                                                RegisterToIndexServer.getController().connectToIndexServer();
                                                label1.setText("Reflector started successfully !! ");
                                        }else{
                                                label1.setText("There is some problem in starting Reflector");
                                        }
                                }else {
                                      	JOptionPane.showMessageDialog(null,"Reflector is already start successfully !!  :");

                                }
                        }else if(response1.startsWith("Stop Reflector")) {
                                LogoutReflector.getController().stopReflector();
                                label1.setText("Reflector stoped successfully !! ");
                                System.exit(0);
                        } else if(response1.startsWith("Restart Reflector")) {
                                LogoutReflector.getController().restartReflector();
				RegisterToIndexServer.getController().startThreads();
                                label1.setText("Restart Reflector successfully !! ");
                        	flag=false;        
                        }

                }
	};
	
	/**
	 * Main Method to start reflector.
	 */
	
	public static void main(String args[]){
		Reflector.getController().startReflector();
	}
}
