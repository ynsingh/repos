package org.bss.brihaspatisync.tools.audio_video;

/**
 * AudioVideoPanel.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

import java.awt.Component;
import javax.media.Manager;
import javax.media.Player;
import javax.media.protocol.DataSource;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;

import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.gui.JoinSessionPanel;

import org.bss.brihaspatisync.tools.audio_video.receiver.VideoReceive;
import org.bss.brihaspatisync.tools.audio_video.receiver.AudioReceive;
import org.bss.brihaspatisync.tools.audio_video.transmitter.AVTransmit3;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>	
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */


public class AudioVideoPanel extends JPanel{
	
	private Player player=null;
	private Component vc;
 	private Component cc;
	private JPanel panel=null;
	private DataSource[] dataSources=null;
	private DataSource ds=null;
	
	private static AudioVideoPanel avPanel=null;
	
	private Log log=Log.getController();


	public static AudioVideoPanel getController(){
		if(avPanel==null)
			avPanel=new AudioVideoPanel();
		return avPanel;
	}

	public AudioVideoPanel(){
		
		try {
		//	JFrame frame = new JFrame("BrihaspatiSync Audio Video");
		//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//	frame.setSize(400,300);//Bounds(400, 300, 400, 300);
 
		//	Container contentPanel = frame.getContentPane();
			//contentPanel.setLayout(new BorderLayout());
			
			panel=new JPanel();
			JToolBar toolBar = new JToolBar("Whiteboard Toolbar");
			panel.setLayout(new BorderLayout()); 
			String role=ClientObject.getController().getUserRole();
   		  	if(role.equals("student")){
				dataSources = new DataSource[2];
				try{
					dataSources[0]=AudioReceive.getAudioReceiveController().getAudioDataSource();
					dataSources[1]=VideoReceive.getVideoReceiveController().getVideoDataSource();
					ds = Manager.createMergingDataSource(dataSources);
					player = Manager.createRealizedPlayer(ds);
				}catch(Exception e){
					log.setLog("Error in creating audio video datasource"+e.getCause());
				}
   		  	}else{
   		  			player = Manager.createRealizedPlayer(AVTransmit3.getController().getDataSource());
   		  	}   		  				
			player.start();
			vc = player.getVisualComponent();
			cc = player.getControlPanelComponent();
			if (vc != null) {
				panel.add(vc, BorderLayout.CENTER);
				//toolBar.add(vc, BorderLayout.CENTER);
			}
			if (cc!= null){
			    panel.add(cc,BorderLayout.SOUTH);
			    //toolBar.add(cc,BorderLayout.SOUTH);
			}
			panel.setSize(600,600);
			toolBar.add(panel);
			
                
        //    contentPanel.add(panel);
		//	frame.setVisible(true);
		JoinSessionPanel.getController().getAV_Panel().add(toolBar);
		JoinSessionPanel.getController().getSplit_Panel().revalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
}
