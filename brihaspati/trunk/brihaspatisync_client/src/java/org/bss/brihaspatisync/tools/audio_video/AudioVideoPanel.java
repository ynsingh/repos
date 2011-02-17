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
import org.bss.brihaspatisync.tools.audio_video.receiver.HandraiseAudioReceive;
import org.bss.brihaspatisync.tools.audio_video.receiver.PresentationAudioReceive;
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
	private DataSource[] dataSources_forstudent=null;
	private DataSource[] dataSources_forinstructor=null;
	private DataSource[] dataSources=null;

	private DataSource ds=null;
	private DataSource stud_ds=null;
	private DataSource pres_ds=null;

	private JToolBar toolBar=null;
	private static AudioVideoPanel avPanel=null;
	private HandraiseAudioReceive h_a_r=null;    //h_a_r (handraise_audio_receive).	
	private PresentationAudioReceive p_a_r=null; //p_a_r (presentation_audio_receive).	
	private Thread hr_thread=null;
	private Thread pr_thread=null;


	public static AudioVideoPanel getController(){
		if(avPanel==null)
			avPanel=new AudioVideoPanel();
		return avPanel;
	}

	public AudioVideoPanel(){

		panel=new JPanel();
		panel.setLayout(new BorderLayout());
	}

	//Start media player panel
	public void startPlayer(){	
		try{
			System.out.println("Starting player");

			panel=new JPanel();
                       	panel.setLayout(new BorderLayout());
                	toolBar = new JToolBar("Media Player Toolbar");
			player=Manager.createRealizedPlayer(getDataSource());

			player.start();

        	       	vc = player.getVisualComponent();
              		cc = player.getControlPanelComponent();
	              	if (vc != null) {
        	        	panel.add(vc, BorderLayout.CENTER);
			}
	              	if (cc!= null){
				panel.add(cc,BorderLayout.SOUTH);
			}

	              	panel.setSize(600,600);
        	      	toolBar.add(panel);

			JoinSessionPanel.getController().getAV_Panel().add(toolBar);
                	JoinSessionPanel.getController().getSplit_Panel().revalidate();
			
		}catch(Exception e){System.out.println("Error in create media player : "+e.getCause());}
	}
	
	// Stop media player
	public void stopPlayer(){
		try{
			player.stop();
			toolBar.remove(panel);
			JoinSessionPanel.getController().getAV_Panel().remove(toolBar);
			JoinSessionPanel.getController().getSplit_Panel().revalidate();
			toolBar=null;
			panel=null;
			System.out.println("stop media player successfull");
		}catch(Exception e){System.out.println("Error in stopping media player");}
	}
			
	// Manage Datasource, here we are merging datasource (hadraise audio, presentation audio, instructor audio/video).
	private DataSource getDataSource(){
		try{
			String role=ClientObject.getController().getUserRole();
			// Get student handraise audio datasource
			try {
				if(h_a_r==null)
                			h_a_r=new HandraiseAudioReceive();
				stud_ds=h_a_r.getAudioDataSource();
			}catch(Exception e){}

			// Get student presentation audio datasource
			try {
                                if(p_a_r==null)
                                        p_a_r=new PresentationAudioReceive();
                                pres_ds=p_a_r.getAudioDataSource();
                        }catch(Exception e){}

			// merging instructor audio/video datasource
			if((stud_ds==null)&&(pres_ds==null)){
   		  		if(role.equals("student")){
					dataSources = new DataSource[2];
					try{
						dataSources[0]=AudioReceive.getAudioReceiveController().getAudioDataSource();
						dataSources[1]=VideoReceive.getVideoReceiveController().getVideoDataSource();
						ds = Manager.createMergingDataSource(dataSources);
						return ds;
					}catch(Exception e){
						System.out.println("Error in creating audio video datasource"+e.getCause());
					}
   		  		}else{
   		  			return AVTransmit3.getController().getDataSource();
   		  		}
			// merging instructor audio/video and student handraise audio datasource.
			}else if((!(stud_ds==null)) && (pres_ds==null)){
				if(role.equals("student")){
                                        dataSources_forstudent = new DataSource[3];
                                        try{
						dataSources_forstudent[0]=AudioReceive.getAudioReceiveController().getAudioDataSource();
						dataSources_forstudent[1]=h_a_r.getAudioDataSource();
                                                dataSources_forstudent[2]=VideoReceive.getVideoReceiveController().getVideoDataSource();
                                                ds = Manager.createMergingDataSource(dataSources_forstudent);
						return ds;
                                        }catch(Exception e){
                                                System.out.println("Error in creating audio video datasource"+e.getCause());
                                        }
                                }else{
					dataSources_forinstructor = new DataSource[2];
					try{
						dataSources_forinstructor[0]=h_a_r.getAudioDataSource();
						dataSources_forinstructor[1]=AVTransmit3.getController().getDataSource();
						ds = Manager.createMergingDataSource(dataSources_forinstructor);
						System.out.println("ds --------------->  "+ds);
						return ds;
					}catch(Exception ex){System.out.println("Error in merging datasource for instructor");}
                                }
			// merging instructor audio/video and student presentation audio datasource
			}else if((stud_ds==null) && (!(pres_ds==null))){
                                if(role.equals("student")){
                                        dataSources_forstudent = new DataSource[3];
                                        try{
                                                dataSources_forstudent[0]=AudioReceive.getAudioReceiveController().getAudioDataSource();
                                                dataSources_forstudent[1]=p_a_r.getAudioDataSource();
                                                dataSources_forstudent[2]=VideoReceive.getVideoReceiveController().getVideoDataSource();
                                                ds = Manager.createMergingDataSource(dataSources_forstudent);
                                                return ds;
                                        }catch(Exception e){
                                                System.out.println("Error in creating audio video datasource"+e.getCause());
                                        }
                                }else{
                                        dataSources_forinstructor = new DataSource[2];
                                        try{
                                                dataSources_forinstructor[0]=p_a_r.getAudioDataSource();
                                                dataSources_forinstructor[1]=AVTransmit3.getController().getDataSource();
                                                ds = Manager.createMergingDataSource(dataSources_forinstructor);
                                                System.out.println("ds --------------->  "+ds);
                                                return ds;
                                        }catch(Exception ex){System.out.println("Error in merging datasource for instructor");}
                                }
			// merging instructor audio/video, student handraise audio and student presentation audio datasource
			}else if((!(stud_ds==null)) && (!(pres_ds==null))){

                                if(role.equals("student")){
                                        dataSources_forstudent = new DataSource[4];
                                        try{
                                                dataSources_forstudent[0]=AudioReceive.getAudioReceiveController().getAudioDataSource();
                                                dataSources_forstudent[1]=h_a_r.getAudioDataSource();
                                                dataSources_forstudent[2]=p_a_r.getAudioDataSource();
                                                dataSources_forstudent[3]=VideoReceive.getVideoReceiveController().getVideoDataSource();
                                                ds = Manager.createMergingDataSource(dataSources_forstudent);
                                                return ds;
                                        }catch(Exception e){
                                                System.out.println("Error in creating audio video datasource for conference"+e.getCause());
                                        }
                                }else{
                                        dataSources_forinstructor = new DataSource[3];
                                        try{
                                                dataSources_forinstructor[0]=h_a_r.getAudioDataSource();
                                                dataSources_forinstructor[1]=p_a_r.getAudioDataSource();
                                                dataSources_forinstructor[2]=AVTransmit3.getController().getDataSource();
                                                ds = Manager.createMergingDataSource(dataSources_forinstructor);
                                                return ds;
                                        }catch(Exception ex){System.out.println("Error in merging conference datasource for instructor");}
                                }
			}
			 
		}catch(Exception error){System.out.println("Error to return datasource to create player : "+error.getCause());}  		  				
		return null;
	}

	//Start handraise audio receiver 
	public void startReceiveHandraiseAudio(){
                if (!h_a_r.initialize()) {
                        System.out.println("Failed to initialize the sessions for Receive Handraise Audio.");
                }
                (hr_thread=new Thread(){
                        public void run(){
                                try {
                                        while (!h_a_r.isDone()) {
                                                Thread.sleep(1000);
                                        }
                                } catch (Exception e) { }
                        }
                } ).start();
        }

	//Stop handraise audio receiver thread.
        public void stopReceiveHandraiseAudio(){
                try {
                        h_a_r.close();
                        hr_thread.interrupt();
                        hr_thread=null;
			h_a_r=null;
			notifyAll();
                        System.out.println(" stop thread in method stopReceiveHandraiseAudio() ");
                } catch (Exception e) {
                        System.out.println("Error in catch stopReceiveHandraiseAudio() in method !!"+e.getMessage());
                }
        }
	
	//Start presentation audio receiver
	public void startReceivePresentationAudio(){
                if (!p_a_r.initialize()) {
                        System.out.println("Failed to initialize the sessions for Receive Presentation Audio.");
                }
                (pr_thread=new Thread(){
                        public void run(){
                                try {
                                        while (!p_a_r.isDone()) {
                                                Thread.sleep(1000);
                                        }
                                } catch (Exception e) { }
                        }
                } ).start();
        }

	// Stop Presentation audio receiver 
        public void stopReceivePresentationAudio(){
                try {
                        p_a_r.close();
                        pr_thread.interrupt();
                        pr_thread=null;
                        p_a_r=null;
                        notifyAll();
                        System.out.println(" stop thread in method stopReceivePresentationAudio() ");
                } catch (Exception e) {
                        System.out.println("Error in catch stopReceivePresentationAudio() in method !!"+e.getMessage());
                }
        }


}
