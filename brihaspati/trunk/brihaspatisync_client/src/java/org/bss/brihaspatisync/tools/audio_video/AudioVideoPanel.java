package org.bss.brihaspatisync.tools.audio_video;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.CannotRealizeException;
import javax.media.Player;
import javax.media.protocol.DataSource;
import javax.media.RealizeCompleteEvent;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.InetAddress;
import javax.media.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;
import javax.media.format.*;
import javax.media.control.TrackControl;
import javax.media.control.FormatControl;
import javax.media.control.QualityControl;
import javax.media.rtp.*;
import javax.media.rtp.rtcp.*;
import com.sun.media.rtp.*;
import java.util.Vector;
import org.bss.brihaspatisync.gui.JoinSessionPanel;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.Log;


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
