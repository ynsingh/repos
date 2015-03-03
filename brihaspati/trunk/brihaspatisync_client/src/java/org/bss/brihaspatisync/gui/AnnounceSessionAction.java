package org.bss.brihaspatisync.gui;

/**
 * AnnounceSessionAction.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 , 2013 ETRG, IIT Kanpur.
 */

import javax.swing.JPanel;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import java.net.URLEncoder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;


/**
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha Yadav </a>Created on 20Dec2008
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modify for GUI. 
 */

public class AnnounceSessionAction implements ActionListener{

	//private ClientObject client_obj=ClientObject.getController();
	private InstructorCSPanel insCSPanel=null;
	private AnnounceSessionPanel ann_sess_panel=null;	
	protected AnnounceSessionAction(InstructorCSPanel insCSPanel,AnnounceSessionPanel ann_sess_panel) {
		this.insCSPanel=insCSPanel;
		this.ann_sess_panel=ann_sess_panel;
	}	
	/**
	 * If user press the Announce button then this method will announce a new Session.
     	 */
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource()==(ann_sess_panel.getannBttn())){
			try{ 
				if(!(ann_sess_panel.getLectureValues().equals(""))){
					String lectValue = ann_sess_panel.getLectureValues();
					String indexServerName=ClientObject.getIndexServerName();
                                       	String value;
					if(!(indexServerName.equals(""))){
						String 	indexServer=indexServerName+"/ProcessRequest?req=putLecture&"+lectValue;
						if(HttpsUtil.getIndexingMessage(indexServer)){
							guiworker task = new guiworker();
						task.execute();
							
							
						} else
						        value=Language.getController().getLangValue("AnnounceSessionAction.MessageDialog2");
					} else
						value=Language.getController().getLangValue("AnnounceSessionAction.MessageDialog3");
				}//if
        		}catch(Exception ex) {
        			System.out.println("Error at actionPerformed()in AnnounceSessionPanel"+ex.getMessage());
        		}
		}
     	}

	public class guiworker extends SwingWorker<Boolean,Void>{
			private ClassLoader clr= this.getClass().getClassLoader();
			
                	JFrame processframe = new JFrame("Please Wait....");
                        guiworker(){
                        	Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                        	ImageIcon loading = new ImageIcon(clr.getResource("resources/images/user/LoadingProgressBar.gif"));
                        	processframe.add(new JLabel("Loading .....",loading, JLabel.CENTER));
                        	processframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        	processframe.setSize(355,100);
                        	processframe.setVisible(true);
                        	processframe.setLocation((((int)dim.getWidth()/2)-102),((int)dim.getHeight()/2)+100);
                	}
     
		protected  Boolean doInBackground() throws Exception {
				String value;
				insCSPanel.getmainPanel().remove(1);
					 		Vector course_Name=ClientObject.getInstCourseList();
					 		insCSPanel.getmainPanel().add(insCSPanel.showLecture(ClientObject.getSessionList(course_Name,ClientObject.getIndexServerName())),BorderLayout.CENTER);
							insCSPanel.getmainPanel().revalidate();
							value=Language.getController().getLangValue("AnnounceSessionAction.MessageDialog1");
                              				System.out.println(value);
							insCSPanel.getinstCourseCombo().setSelectedItem("--Show All--");
				return true;
                	}

	 	protected void done(){
	 			boolean retval = false;
	 			try{
	 				 retval = get();
	 			}catch(Exception e) { System.out.println(e.getMessage());}
	 			if(retval)
                        	processframe.dispose();
                                
     			}
	}




}
