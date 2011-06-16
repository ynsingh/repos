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
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.network.ppt_sharing.GetPPTScreen;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */

public class UserListPanel {

	private JPanel mainPanel;
	private JScrollPane scrollPane=null;
	private JList jlist=null;
	private	Vector statusVector=new Vector();
	
	private boolean flag=true;
	private boolean screenFlag=true;		
	private boolean sharescreenFlag=true;		
	private boolean pptFlag=true;		

	private static UserListPanel ul_panel=null;
	private ClientObject client_obj=ClientObject.getController();
	private String username=client_obj.getUserName();;	
	private String role=client_obj.getUserRole();	
	private String a_status=client_obj.getAudioStatus();
        private String v_status=client_obj.getVideoStatus();
	
	private HandRaisePanel handRaisePanel=HandRaisePanel.getController();

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
		Object elements[][]=new Object[1][4];
		elements[0][0] = new Font("Helvetica", Font.PLAIN, 14);
		elements[0][1] = Color.black;
		elements[0][2] = new ImageIcon(clr.getResource("resources/images/user/user.jpe"));
		elements[0][3] = username;
				
                jlist = new JList(elements);
                ListCellRenderer renderer = new UserListCellRendered();
                jlist.setCellRenderer(renderer);
                scrollPane = new JScrollPane(jlist);
	
		JPanel labelPane=new JPanel();
                JLabel welcome=new JLabel("Welcome!");
                welcome.setFont(new Font("Arial", Font.PLAIN, 20));
                JLabel userLogin=new JLabel(username);
                userLogin.setForeground(new Color(24,116,205));
                userLogin.setFont(new Font("Arial", Font.BOLD, 20));
                labelPane.add(welcome);
                labelPane.add(userLogin);
		
                mainPanel.add(labelPane,BorderLayout.NORTH);
                mainPanel.add(handRaisePanel.createGUI(),BorderLayout.EAST);
		mainPanel.add(scrollPane,BorderLayout.CENTER);
		return mainPanel;
	
	}

	/**
 	 * Getting username and it's status from userlist vector to change userlist view in gui
 	 * according to status set flags for student mic controller, screen share controller, whiteboard controller etc.
 	 */  	
	protected void  userlistPanel(Vector userlist){
		ClassLoader clr= this.getClass().getClassLoader();
		Object elements[][]=new Object[userlist.size()][4];
		statusVector.clear();
                for (int i=0;i<userlist.size();i++){
                        String str=(String)userlist.elementAt(i);
                        StringTokenizer st=new StringTokenizer(str,"$");
                        while(st.hasMoreTokens()){
				String user = (String)st.nextToken();
				String status=st.nextToken().trim();	
				statusVector.add(status);
				if(role.equals("student")) {		
					if(user.equals(username)){
						handRaisePanel.setEnableORDisable(status);
					}
					// Student mic controller. 
					if(statusVector.contains("Allow-Mic")){
						try {
							if((user.equals(username)) && (status.equals("Allow-Mic") && (flag))){
                                                                flag=false;
								HandRaiseThreadController.getController().starthandraiseraudioflag(true);
                                                        }
							if(flag) {
								flag=false;
								HandRaiseThreadController.getController().starthraudioflag(true);
							}
							
						}catch(Exception sp){System.out.println("  Error in catch Allow-Mic ==========> ");}
					}
					if(statusVector.contains("Share-Screen")){
						try{
							if(sharescreenFlag){
                                                                sharescreenFlag=false;
                                                                HandRaiseThreadController.getController().startGetScreenFlag(true);
                                                        }
						}catch(Exception es){System.out.println("Error in Share-Screen ===========> ");}
					}
					// Student screen share controller.
					if(statusVector.contains("Allow-Screen")){
						try {
							if((user.equals(username)) && (status.equals("Allow-Screen") && (screenFlag))){
                                                                screenFlag=false;
                                                                HandRaiseThreadController.getController().startPostScreenFlag(true);
                        			        	/*if((a_status.equals("1"))&&(v_status.equals("1"))){
									HandRaiseThreadController.getController().startpresaudioflag(true);
								}
								*/
                                                        }
					
                                                        if(screenFlag){
								screenFlag=false;
								HandRaiseThreadController.getController().startGetScreenFlag(true);
                        			          	/*if((a_status.equals("1"))&&(v_status.equals("1"))){
									HandRaiseThreadController.getController().startPresAudioRec(true);
								}
								*/
                                                        }
                                                }catch(Exception sp){System.out.println("  Error in catch Allow-Mic ==========> ");}
					}
					// Student ppt presentation controller.
					if(statusVector.contains("Allow-PPT")){
                                                try {
                                                        if((user.equals(username)) && (status.equals("Allow-PPT") && (pptFlag))){
                                                                pptFlag=false;
                                                                HandRaiseThreadController.getController().startpostpptFlag(true);
                                                        }

                                                        if(pptFlag){
                                                                pptFlag=false;
                                                                HandRaiseThreadController.getController().startgetpptFlag(true);
                                                        }
                                                }catch(Exception sp){System.out.println("  Error in catch Allow-Mic ==========> ");}
                                        }
				}else if(role.equals("instructor")) { // check only for controller according to username.

					if(statusVector.contains("Share-Screen")){
						if((user.equals(username)) && (status.equals("Share-Screen") && (sharescreenFlag))){
                                                        sharescreenFlag=false;
                                                        HandRaiseThreadController.getController().startPostScreenFlag(true);
                                                }
					}

					// Instructor Screen share controller.
					if(statusVector.contains("Allow-Screen")){
						//if((user.equals(username)) && (status.equals("Allow-Screen") && (screenFlag))){
						//	screenFlag=false;
						//	HandRaiseThreadController.getController().startPostScreenFlag(true);
						//}
						if((screenFlag)){
							screenFlag=false;
							HandRaiseThreadController.getController().startGetScreenFlag(true);
                                                        /*if((a_status.equals("1"))&&(v_status.equals("1"))){
								HandRaiseThreadController.getController().startPresAudioRec(true);
							}*/
						}	
					}

					// Instructor ppt presentation controller.
					if(statusVector.contains("Allow-PPT")){
                                                try {
                                                        if((user.equals(username)) && (status.equals("Allow-PPT") && (pptFlag))) {
                                                                pptFlag=false;
                                                                HandRaiseThreadController.getController().startpostpptFlag(true);
                                                        }
                                                        if(pptFlag){
                                                                pptFlag=false;
                                                                HandRaiseThreadController.getController().startgetpptFlag(true);
                                                        }
                                                }catch(Exception sp){System.out.println("  Error in catch Allow-Mic ==========> ");}
                                        }


				}
				elements[i][0] = new Font("Helvetica", Font.PLAIN, 14);
        	        	elements[i][1] = Color.black;
				elements[i][2] = new ImageIcon(clr.getResource(getImageIcon(status)));
	                	elements[i][3] = user;
                        }
                }

		if(!(statusVector.contains("Share-Screen"))) {
			if(!sharescreenFlag) {
				sharescreenFlag=true;
				try {
                                        HandRaiseThreadController.getController().stopGetScreenFlag(true);
                                        HandRaiseThreadController.getController().stopPostScreenFlag(true);
                                } catch(Exception ex){}
			}
		}

		//Contoller for Screen share for both student and instructor. 
		if(!(statusVector.contains("Allow-Screen"))) {
			if(!screenFlag) {
				screenFlag=true;
				try {
                       			HandRaiseThreadController.getController().stopGetScreenFlag(true);
					HandRaiseThreadController.getController().stopPostScreenFlag(true);
                        		/*if((a_status.equals("1"))&&(v_status.equals("1"))){
                     				HandRaiseThreadController.getController().stopPresAudioRec(true);
					}*/
				} catch(Exception ex){}
			}
		}
		
		//Student mic controller for both student and instructor. 
		if(!(statusVector.contains("Allow-Mic"))) {
                	if(!flag) {
                          	flag=true;
                               	try {
                                	HandRaiseThreadController.getController().stophraudioflag(true);
					/******  arvind ***********/
					HandRaiseThreadController.getController().stophandraiseraudioflag(true);
                                } catch(Exception ex){}
                     	}
               	}
		
		//PPT presentation contoller for both student and instructor. 
		if(!(statusVector.contains("Allow-PPT"))) {
                        if(!pptFlag) {
                                pptFlag=true;
				GetPPTScreen.getController().stop();
				org.bss.brihaspatisync.tools.presentation.PresentationViewPanel.getController().setEnable_Decable(false ,false);
				org.bss.brihaspatisync.tools.presentation.PresentationViewPanel.getController().setSclollEnable_Decable(false);
				//org.bss.brihaspatisync.tools.presentation.JsliderListener.getController().setEnable_Decable(false);
                        }
               }
	 		
		/**
 		 *  Controller for instructor to handle action on student specific request 	
 		 */
		if(role.equals("instructor")){
			if((!(statusVector.contains("Get-WB"))) && (!(statusVector.contains("Get-Mic"))) && (!(statusVector.contains("Allow-WB"))) && (!(statusVector.contains("Allow-Mic")))&&(!(statusVector.contains("Share-Screen")))) {
                     		handRaisePanel.setEnableORDisable("available");
			}		
			if(!(statusVector.contains("Get-Mic"))){
                                if(statusVector.contains("Allow-Mic") && (flag)){
					flag=false;	
                                        handRaisePanel.setEnableORDisable("Allow-Mic");
					HandRaiseThreadController.getController().starthraudioflag(true);
				}
                        }else{
				if(statusVector.contains("Get-Mic"))
	                                handRaisePanel.setEnableORDisable("Get-Mic");
                        }
		
			if(!(statusVector.contains("Get-WB"))){
				if(statusVector.contains("Allow-WB")){
					handRaisePanel.setEnableORDisable("Allow-WB");
				}
					
			}else{
				if(statusVector.contains("Get-WB"))
					handRaisePanel.setEnableORDisable("Get-WB");
			}
			
			if(!(statusVector.contains("Get-Screen"))){
				if(statusVector.contains("Allow-Screen"))
                                        handRaisePanel.setEnableORDisable("Allow-Screen");

			}else{
                                if(statusVector.contains("Get-Screen"))
                                        handRaisePanel.setEnableORDisable("Get-Screen");
                        }
			
			if(!(statusVector.contains("Get-PPT"))){
                                if(statusVector.contains("Allow-PPT"))
                                        handRaisePanel.setEnableORDisable("Allow-PPT");

                        }else{
                                if(statusVector.contains("Get-PPT"))
                                        handRaisePanel.setEnableORDisable("Get-PPT");
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
		}else if(status.equals("Get-WB")){
                        return "resources/images/user/allow-wb.gif";
                }else if(status.equals("Get-Mic")){
                        return "resources/images/user/hr.jpeg";
                }else if(status.equals("Get-Screen")){
                        return "resources/images/user/getscreen.jpeg";
                }else if(status.equals("Allow-WB")){
                        return "resources/images/user/draw.jpeg";
                }else if(status.equals("Allow-Mic")){
                        return "resources/images/user/mic1.jpeg";
		}else if(status.equals("Get-Screen")){
                        return "resources/images/user/getscreen.jpeg";
                }else if(status.equals("Allow-Screen")){
                        return "resources/images/user/allowscreen.jpeg";
                } else if(status.equals("Get-PPT")){
                        return "resources/images/user/getscreen.jpeg";
                }else if(status.equals("Allow-PPT")){
                        return "resources/images/user/allowppt.png";
                }else if(status.equals("Share-Screen")){
                        return "resources/images/user/allowscreen.jpeg";
		}
		return "";
	}
}
