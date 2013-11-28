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
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.AudioUtilObject;
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
	private Vector user_id=new Vector();
	private Vector user_full_name=new Vector();
	
	private boolean flag=false;
	private String diffuser_list="";		
	private String turnof_onFlag="";

	private ClientObject client_obj=ClientObject.getController();
	private String role=client_obj.getUserRole();	
	private String username=client_obj.getUserName();;	
	private String a_status=AudioUtilObject.getAudioStatus();
        private String v_status=AudioUtilObject.getVideoStatus();
	private Allow_Deny_Permission all_deny_per=new Allow_Deny_Permission();	

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
	
              	mainPanel.add(all_deny_per.createGUI(),BorderLayout.NORTH);
		all_deny_per.setEnable_Decable_Allow_Get(true);
		mainPanel.add(scrollPane,BorderLayout.CENTER);
		this.start();
		return mainPanel;
	
	}

	/**
 	 * This method is used to get all userlist from reflector. 
 	 */
	public  void run() {
		while(org.bss.brihaspatisync.util.ThreadController.getThreadFlag()) {
			user_id.clear();	
			statusVector.clear();
			user_full_name.clear();
			try {
				String str=RuntimeDataObject.getController().getUserList();
				if(!diffuser_list.equals(str)) {
					diffuser_list=str;
                     			str=str.replaceAll(","," ");
		                        if(str.length()>0){
        		                	java.util.StringTokenizer Tok = new java.util.StringTokenizer(str);
                		                while(Tok.hasMoreElements()) {
                        		        	String str1=(String)Tok.nextElement();
                        				if(( str1.indexOf("Allow") > 0 ) || (str1.indexOf("Get")>0) || (str1.indexOf("Share")>0) ){
                                				user_Name_Id(0,str1);
                        				} else {
                                 				user_Name_Id(1,str1);
                        				}
	                             		}       
        	            		}
					display_UserList();
				}
				this.sleep(500);
				this.yield();
			}catch(Exception e){}
		}		
	}	

	private void user_Name_Id(int k,String str) {
		try {
                        StringTokenizer st=new StringTokenizer(str,"$");
                        while(st.hasMoreTokens()) {
				if(k== 0) {
					user_id.add(k,(String)st.nextToken());
					statusVector.add(k,st.nextToken().trim());
        	        	        user_full_name.add(k,java.net.URLDecoder.decode(st.nextToken().trim()));
				} else {
					user_id.add((String)st.nextToken());
                                        statusVector.add(st.nextToken().trim());
                                        user_full_name.add(java.net.URLDecoder.decode(st.nextToken().trim()));
				}
			}
		}catch(Exception e){ }
	}
	
	/**
	 * Getting username and it's status from userlist vector to change userlist view in gui
	 * according to status set flags for student mic controller, screen share controller, whiteboard controller etc.
	 */
	
	private void display_UserList(){
		ClassLoader clr= this.getClass().getClassLoader();
		Object elements[][]=new Object[user_id.size()][5];

                for (int i=0;i<statusVector.size();i++){
			String user = (String)user_id.get(i);
			String status=(String)statusVector.get(i);	
			String fullname=(String)user_full_name.get(i);	
			
			if(role.equals("student")) {
				if((user.equals(username)) && (status.equals("Allow-Permission") || (status.equals ("Get-Permission")))) {
					all_deny_per.setEnable_Decable_Deny(true);
                                        all_deny_per.setEnable_Decable_Allow_Get(false);	
				}
				if((user.equals(username)) && (!status.equals("Allow-Permission")) && (!status.equals ("Get-Permission"))) {
                                        all_deny_per.setEnable_Decable_Deny(false);
                                        all_deny_per.setEnable_Decable_Allow_Get(true);
                                }		
				if(statusVector.contains("Allow-Permission")){					
					try {
						if((user.equals(username)) && (!flag)) {
							flag=true; 
                                                        HandRaiseThreadController.getController().startPostPermission(true);
							
						} else if(!flag) {
							flag=true; 
	                                                HandRaiseThreadController.getController().startGetPermission(true);
						}
					}catch(Exception e) {   }
				} else {
					if(flag) {
						flag=false;
						HandRaiseThreadController.getController().stopAllPermission(true);
						all_deny_per.setEnable_Decable_Deny(false);
	                                        all_deny_per.setEnable_Decable_Allow_Get(true);
                        		}
				}
					
			} else if(role.equals("instructor")) { // check only for controller according to username.
				if(statusVector.contains("Allow-Permission")) {
					if((user.equals(username)) && (!flag)) {
						flag=true;
						HandRaiseThreadController.getController().startPostShareScreen(true);
					} else if(!flag){
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
			if(role.equals("instructor")) {	
				if(statusVector.contains("Allow-Permission")) {
					all_deny_per.setEnable_Decable_Deny(true);
	                		all_deny_per.setEnable_Decable_Allow_Get(false);
				} else {
					all_deny_per.setEnable_Decable_Deny(false);
		               		all_deny_per.setEnable_Decable_Allow_Get(true);
				}
			}else {
				
			}	
			elements[i][0] = new Font("Helvetica", Font.PLAIN, 14);
        	        elements[i][1] = Color.black;
			elements[i][2] = new ImageIcon(clr.getResource(getImageIcon(status)));
	                elements[i][3] = user;
	                elements[i][4] = fullname;
               		
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
			return "resources/images/login.png";
                }else if(status.equals("Get-Permission")){
                        return "resources/images/user/hr.jpeg";
		}else if(status.equals("Allow-Permission")){
                        return "resources/images/user/allowscreen.jpeg";
		}
		return "";
	}
}
