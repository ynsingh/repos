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


/**
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha Yadav </a>Created on 20Dec2008
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Modify for GUI. 
 */

public class AnnounceSessionAction implements ActionListener{

	private ClientObject client_obj=ClientObject.getController();
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
					String indexServerName=client_obj.getIndexServerName();
                                        String value;
					if(!(indexServerName.equals(""))){
						String 	indexServer=indexServerName+"/ProcessRequest?req=putLecture&"+lectValue;
						if(HttpsUtil.getIndexingMessage(indexServer)){
							
							insCSPanel.getmainPanel().remove(1);
					 		Vector course_Name=client_obj.getInstCourseList();
					 		insCSPanel.getmainPanel().add(insCSPanel.showLecture(client_obj.getSessionList(course_Name,client_obj.getIndexServerName())),BorderLayout.CENTER);
							insCSPanel.getmainPanel().revalidate();
							value=Language.getController().getLangValue("AnnounceSessionAction.MessageDialog1");
                              				System.out.println(value);
							insCSPanel.getinstCourseCombo().setSelectedItem("--Show All--");
							
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
}
