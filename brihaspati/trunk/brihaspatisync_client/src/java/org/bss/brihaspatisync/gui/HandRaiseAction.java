package org.bss.brihaspatisync.gui;

/**
 * HandRaiseAction.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, 2013 ETRG,Kanpur.
 */

import java.net.URLEncoder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HandRaiseAction implements MouseListener {

	private UtilObject utilObject=UtilObject.getController();
	private ClientObject client_obj=ClientObject.getController();

	private String sb ="";
	private String selectedUsername="";
        private String ins_Username="";

	public void mouseClicked(MouseEvent e) { 
		try {
			selectedUsername=client_obj.getSelectedListUsername();
			String cmd=e.getComponent().getName();
			if(cmd.equals("Permission")) {
                                ins_Username=client_obj.getUserName();
                                actionONRequest("Allow-Permission",ins_Username);
                                StatusPanel.getController().setStatus(ins_Username +" "+" is Allowed to use all tools ");
                        }else if(cmd.equals("Allow-Permission")) {
                                if(selectedUsername.equals("")) {
                                        StatusPanel.getController().setStatus(Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
                                        javax.swing.JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
                                } else {
                                        actionONRequest("Allow-Permission",selectedUsername);
                                        StatusPanel.getController().setStatus(selectedUsername +" "+" is Allowed to use all tools ");
                                        selectedUsername="";
                                }
                        } else if(cmd.equals("Denie-Permission")) {
				if(!(client_obj.getUserRole()).equals("instructor"))
					selectedUsername=client_obj.getUserName();
                                if(selectedUsername.equals("") ) {
                                        StatusPanel.getController().setStatus(Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
					javax.swing.JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
				} else if (!ins_Username.equals("")) {
                                        actionONRequest("available",ins_Username);
                                        StatusPanel.getController().setStatus(ins_Username +" "+" is Denied to use all tools ");
                                        ins_Username="";
				} else {
                                        actionONRequest("available",selectedUsername);
                                        StatusPanel.getController().setStatus(selectedUsername +" "+" is Denied to use all tools ");
                                }
                                selectedUsername="";
                        } else if(cmd.equals("Get-Permission")) {
                        	selectedUsername=client_obj.getUserName();
                                actionONRequest("Get-Permission",selectedUsername);
                                StatusPanel.getController().setStatus(selectedUsername +" "+" is get permission to use all tools ");
                        }
			
		}catch(Exception ex){}
	}
	public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e)  {}

	/**
 	 * Action for make signal for appropriate Handraise option (e.g request for whiteboard, request for audio mic, request for screen sharing etc.)
 	 */
	public void actionONRequest(String Request,String selectedUsername){
		String lectid=null;
                try{
			String id=client_obj.getLectureID();
			if(!(id.equals("")))
                        	lectid ="lectID="+URLEncoder.encode(id,"UTF-8");
			else
				System.out.println("Insufficient lecture id in HandRaiseAction class"+id);
                        String user="loginName="+URLEncoder.encode(selectedUsername,"UTF-8");
			String indexServer1=client_obj.getIndexServerName();
			if(!(indexServer1.equals(""))){
				sb="HandRaiseAction";
                                sb=sb+id+","+user+","+Request+"reqnull";
				java.util.LinkedList sendqueue=utilObject.getSendQueue("UserList_Data");
                                sendqueue.addLast(sb.getBytes());
			}else{
				System.out.println("Insufficient indexServer name in HandRaiseAction :");
			}
                }catch(Exception ex){System.out.println("Error on actionPerformed in UserListPanel."+ex.getMessage());}
        }
}
