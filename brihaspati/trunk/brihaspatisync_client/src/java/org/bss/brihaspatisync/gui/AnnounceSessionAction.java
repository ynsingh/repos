package org.bss.brihaspatisync.gui;

/**
 * AnnounceSessionAction.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009-2010 ETRG, IIT Kanpur.
 */

import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.DateUtil;
import java.net.URLEncoder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
import java.util.Vector;
import org.bss.brihaspatisync.network.Log;


/**
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha Yadav </a>Created on 20Dec2008
 */

public class AnnounceSessionAction extends JPanel implements ActionListener{

	private Vector course_Name=null;
	private JPanel Ins_mainPanel;
	private JButton annBttn;	
	private static AnnounceSessionAction ann_action=null;
	private ClientObject client_obj=ClientObject.getController();
	private InstructorCSPanel insCSPanel=InstructorCSPanel.getController();
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	private AnnounceSessionPanel ann_sessionPanel=AnnounceSessionPanel.getController();
	private Log log=Log.getController();
	
	/**
 	 * This is a controller for this class.
 	 */
	public static AnnounceSessionAction getController(){
		if(ann_action==null){
			ann_action=new AnnounceSessionAction();
		}
			return ann_action;
		
	}
	
	/**
	 * If user press the Announce button then this method will announce a new Session.
     	 */
    	public void actionPerformed(ActionEvent e){
    		if(e.getSource()==(AnnounceSessionPanel.getController().getannBttn())){
			try{ 
				AnnounceSessionPanel.getController().getannBttn().setCursor(busyCursor);
				try{
					Thread.sleep(1000);
				}catch(InterruptedException ie){
					AnnounceSessionPanel.getController().getannBttn().setCursor(defaultCursor);
				}finally{
					AnnounceSessionPanel.getController().getannBttn().setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				if(!(AnnounceSessionPanel.getController().getLectureValues().equals(""))){
					String lectValue = "lectValue="+URLEncoder.encode(AnnounceSessionPanel.getController().getLectureValues(),"UTF-8");
					String indexServerName=client_obj.getIndexServerName();

					if(!(indexServerName.equals(""))){
						String 	indexServer=indexServerName+"/ProcessRequest?req=putLecture&"+lectValue;
						if(HttpsUtil.getController().getIndexingMessage(indexServer)){
					
							insCSPanel.getmainPanel().remove(1);
					 		course_Name=client_obj.getInstCourseList();
					 		insCSPanel.getmainPanel().add(insCSPanel.showLecture(client_obj.getSessionList(course_Name,client_obj.getIndexServerName())),BorderLayout.CENTER);
							insCSPanel.getmainPanel().revalidate();
							JOptionPane.showMessageDialog(null,"Lecture Announced successfully !!");
							insCSPanel.getinstCourseCombo().setSelectedItem("--Show All--");
						}else
							JOptionPane.showMessageDialog(null,"There is some problem in Announced Lecture  !!");
					}else{
						log.setLog("insufficient indexServer name in AnnounceSession :" + indexServerName);
					}//else
				}//if
        		}catch(Exception ex){
        			log.setLog("Error at actionPerformed()in AnnounceSessionPanel"+ex.getMessage());
        		}
		}
     	}
}
