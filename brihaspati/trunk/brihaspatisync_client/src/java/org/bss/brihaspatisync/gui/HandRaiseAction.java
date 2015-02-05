package org.bss.brihaspatisync.gui;

/**
 * HandRaiseAction.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,2013,2015 ETRG,Kanpur.
 */

import java.net.URLEncoder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HandRaiseAction implements MouseListener {

	private UtilObject utilObject=UtilObject.getController();

	private String selectedUsername="";

	public void mouseClicked(MouseEvent e) { 
		try {
			selectedUsername=ClientObject.getSelectedListUsername();
			String cmd=e.getComponent().getName();
			if(cmd.equals("Get-Permission")) {
                                selectedUsername=ClientObject.getUserName();
                                ClientObject.setSelectedUserListname(selectedUsername);
                                actionONRequest("Get-Permission",selectedUsername);
                                StatusPanel.getController().setStatus(java.net.URLDecoder.decode(selectedUsername, "UTF-8")+" "+Language.getController().getLangValue("HandRaiseAction.MessageDialog2"));
                        }
			if(selectedUsername.equals("")) {
                       		StatusPanel.getController().setStatus(Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
                                javax.swing.JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));	
				return;
                       	}	

                        if(cmd.equals("Allow-Permission")) {
                        	actionONRequest("Allow-Permission",selectedUsername);
                                StatusPanel.getController().setStatus(java.net.URLDecoder.decode(selectedUsername, "UTF-8")+" "+Language.getController().getLangValue("HandRaiseAction.MessageDialog3"));
                        } else if(cmd.equals("Denie-Permission")) {
                        	actionONRequest("available",selectedUsername);
                                StatusPanel.getController().setStatus(java.net.URLDecoder.decode(selectedUsername, "UTF-8")+" "+Language.getController().getLangValue("HandRaiseAction.MessageDialog4"));
                        }
		} catch(Exception ex){}
	}
	public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e)  {}

	/**
 	 * Action for make signal for appropriate Handraise option (e.g request for whiteboard, request for audio mic, request for screen sharing etc.)
 	 */
	private void actionONRequest(String Request,String selectedUsername){
                try {
			String lectid=ClientObject.getLectureID();
			if(!(lectid.equals(""))) {
	                        String user="loginName="+URLEncoder.encode(selectedUsername,"UTF-8");
				String indexServer1=ClientObject.getIndexServerName();
				if(!(indexServer1.equals(""))) {
					String sb ="HandRaiseAction";
                                	sb=sb+lectid+","+user+","+Request+"reqnull";
					java.util.LinkedList sendqueue=utilObject.getSendQueue("UserList_Data");
        	                        sendqueue.addLast(sb.getBytes());
				} else
					System.out.println("Insufficient indexServer name in HandRaiseAction :");
			} else
				System.out.println("Insufficient lecture id in HandRaiseAction class"+lectid);
                } catch(Exception ex){System.out.println("Error on actionPerformed in UserListPanel."+ex.getMessage());}
        }
}
