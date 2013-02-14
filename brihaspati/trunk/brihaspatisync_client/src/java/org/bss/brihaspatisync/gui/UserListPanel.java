package org.bss.brihaspatisync.gui;
	
/**
 * UserListPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,Kanpur.
 */

import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;

import java.util.Vector;
import java.util.StringTokenizer;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListCellRenderer;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.network.ppt_sharing.GetPPTScreen;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class UserListPanel extends Thread {

	private JPanel mainPanel;
	private JScrollPane scrollPane=null;
	private JList jlist=null;
	private	Vector statusVector=new Vector();
	private Vector user_list=new Vector();
	private boolean flag=false;
	private boolean sharescreenFlag=false;		
	private String turnof_onFlag="";		
	private Vector userlist=new Vector();
	
	private static UserListPanel ul_panel=null;
	private ClientObject client_obj=ClientObject.getController();
	private String username=client_obj.getUserName();;	
	private String role=client_obj.getUserRole();	
	private String a_status=client_obj.getAudioStatus();
        private String v_status=client_obj.getVideoStatus();
	
	/**
	 * Controller for class.
	 */	
	protected static UserListPanel getController(){
		if(ul_panel==null){
			ul_panel=new UserListPanel();
		}
		return ul_panel;
	}
	
	/**
	* Creating GUI for UserListPanel.
	*/
	
	protected JPanel createGUI(){
		
		mainPanel=new JPanel();
                mainPanel.setLayout(new BorderLayout());
		
		ClassLoader clr= this.getClass().getClassLoader();
		Object elements[][]=new Object[1][5];
		elements[0][0] = new Font("Helvetica", Font.PLAIN, 14);
		elements[0][1] = Color.black;
		elements[0][2] = new ImageIcon(clr.getResource("resources/images/user/user.jpe"));
		elements[0][3] = username;
		elements[0][4] = username;
				
                jlist = new JList(elements);
                ListCellRenderer renderer = new UserListCellRendered();
                jlist.setCellRenderer(renderer);
                scrollPane = new JScrollPane(jlist);
	
              	mainPanel.add(ShareScreenAndPPT.getController().createGUI(),BorderLayout.NORTH);
		ShareScreenAndPPT.getController().setEnable_Decable();
		mainPanel.add(scrollPane,BorderLayout.CENTER);
		this.start();
		return mainPanel;
	
	}

	/**
 	 * This method is used to get all userlist from reflector. 
 	 */
	public  void run(){
		while(org.bss.brihaspatisync.util.ThreadController.getController().getThreadFlag()) {
			userlist.clear(); 
			try {
				String str=RuntimeDataObject.getController().getUserList();
                     		str=str.replaceAll(","," ");
	                        if(str.length()>0){
        	                	java.util.StringTokenizer Tok = new java.util.StringTokenizer(str);
                	                while(Tok.hasMoreElements()) {
                        	        	String str1=(String)Tok.nextElement();
                                	        userlist.add(str1);
	                             	}       
        	            	}
				display_UserList();this.sleep(500);this.yield();
			}catch(Exception e){}
		}		
	}	

	/**
	 * Getting username and it's status from userlist vector to change userlist view in gui
	 * according to status set flags for student mic controller, screen share controller, whiteboard controller etc.
	 */
	
	private void display_UserList(){
		ClassLoader clr= this.getClass().getClassLoader();
		Object elements[][]=new Object[userlist.size()][5];
		statusVector.clear();
		user_list.clear();
		for (int i=0;i<userlist.size();i++) {
			String str=(String)userlist.elementAt(i);
			if(( str.indexOf("Allow") > 0 ) || (str.indexOf("Get")>0) || (str.indexOf("Share")>0) ){
				user_list.add(0,str);
			}else{
				 user_list.add(str);
			}
		}	

                for (int i=0;i<user_list.size();i++){
                        String str=(String)user_list.elementAt(i);
                        StringTokenizer st=new StringTokenizer(str,"$");
                        while(st.hasMoreTokens()){
				String user = (String)st.nextToken();
				String status=st.nextToken().trim();	
				String fullname=java.net.URLDecoder.decode(st.nextToken().trim());	
				statusVector.add(status);
				if(role.equals("student")) {		
					if(statusVector.contains("Allow-Permission")){					
						try {
							if((user.equals(username)) && (!(turnof_onFlag.equals(status))) && (flag) ) {
								flag=false;
								HandRaiseThreadController.getController().stopAllPermission(true);		
							}	
							if((user.equals(username)) && (status.equals("Allow-Permission")) && (!flag)) {
								flag=true;
								turnof_onFlag="Allow-Permission";
                        	                                HandRaiseThreadController.getController().startPostPermission(true);
							} else if(!flag) {
								flag=true;
								turnof_onFlag="available";
	                                                        HandRaiseThreadController.getController().startGetPermission(true);
							}
						}catch(Exception e){}
					}else {
						if(flag) {
                         			       flag=false;
							HandRaiseThreadController.getController().stopAllPermission(true);
                        			}
					}
					
					
					if(statusVector.contains("Share-Screen")){
						try{
							if(!sharescreenFlag){
                                                                sharescreenFlag=true;
                                                                HandRaiseThreadController.getController().startGetShareScreen(true);
                                                        }
						}catch(Exception es){System.out.println("Error in Share-Screen ===========> ");}
					}else {
						if(sharescreenFlag) {
			                                sharescreenFlag=false;
							HandRaiseThreadController.getController().stopShareScreen(true);
                        			}
					}
					
				}else if(role.equals("instructor")) { // check only for controller according to username.
					if(statusVector.contains("Share-Screen")){
						if((user.equals(username)) &&  (!sharescreenFlag)){
                                                        sharescreenFlag=true;
                                                        HandRaiseThreadController.getController().startPostShareScreen(true);
                                                }
					}else {
                                                if(sharescreenFlag) {
                                                        sharescreenFlag=false;
							HandRaiseThreadController.getController().stopShareScreen(true);
                                                }
                                        }
					
					if(statusVector.contains("Allow-Permission")){
						if(!flag){
							flag=true;
							HandRaiseThreadController.getController().startGetPermission(true);
						}	
					}else {
						if(flag) {
                                                       	flag=false;
                   					HandRaiseThreadController.getController().stopAllPermission(true);
                                                }
					}
				}
				elements[i][0] = new Font("Helvetica", Font.PLAIN, 14);
        	        	elements[i][1] = Color.black;
				elements[i][2] = new ImageIcon(clr.getResource(getImageIcon(status)));
	                	elements[i][3] = user;
	                	elements[i][4] = fullname;
                        }
                }
		
		jlist.setListData(elements);
                ListCellRenderer renderer = new UserListCellRendered();
                jlist.setCellRenderer(renderer);
		scrollPane.revalidate();
                scrollPane.repaint();
		
        }

	/**
 	 * Controll images in Userlist according to it's status.
 	 */ 	 
	private String getImageIcon(String status){
		if(status.equals("available")){
			return "resources/images/user/user.jpe";
                }else if(status.equals("Get-Permission")){
                        return "resources/images/user/getscreen.jpeg";
		}else if(status.equals("Allow-Permission")){
                        return "resources/images/user/allowscreen.jpeg";
		}
		else if(status.equals("Share-Screen")){
                        return "resources/images/user/allowscreen.jpeg";
                }
		return "";
	}
}
