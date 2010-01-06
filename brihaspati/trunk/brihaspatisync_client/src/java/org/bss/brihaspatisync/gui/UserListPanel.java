package org.bss.brihaspatisync.gui;
	
/**
 * UserListPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,Kanpur.
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

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */

public class UserListPanel {

	private JPanel mainPanel;
	private JScrollPane scrollPane=null;
	private JList jlist=null;
			
	private static UserListPanel ul_panel=null;
	private String username=ClientObject.getController().getUserName();;	
	private String role=ClientObject.getController().getUserRole();	
	private HandRaisePanel handRaisePanel =HandRaisePanel.getController();	

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
	
	protected void  userlistPanel(Vector userlist){
		ClassLoader clr= this.getClass().getClassLoader();
		Object elements[][]=new Object[userlist.size()][4];
		Vector statusVector=new Vector();
                for (int i=0;i<userlist.size();i++){
                        String str=(String)userlist.elementAt(i);
                        StringTokenizer st=new StringTokenizer(str,"$");
                        while(st.hasMoreTokens()){
				String user = (String)st.nextToken();
				String status=st.nextToken();	
				statusVector.add(status);
				if(role.equals("student")){		
					if(user.equals(username)){
						handRaisePanel.setEnableORDecable(status);
					}
				}
				elements[i][0] = new Font("Helvetica", Font.PLAIN, 14);
        	        	elements[i][1] = Color.black;
				elements[i][2] = new ImageIcon(clr.getResource(getImageIcon(status)));
	                	elements[i][3] = user;
				
                        }
                }
		if(role.equals("instructor")){
			if(!(statusVector.contains("Hand-Raise"))){
				if(!(statusVector.contains("Allow-WB"))){
					handRaisePanel.setEnableORDecable("available");
                		}else {
					handRaisePanel.setEnableORDecable("Allow-WB");
				}
			}else{
				handRaisePanel.setEnableORDecable("Hand-Raise");
			}
		}		
		statusVector.clear(); statusVector=null;
		jlist.setListData(elements);
                ListCellRenderer renderer = new UserListCellRendered();
                jlist.setCellRenderer(renderer);
		scrollPane.revalidate();
                scrollPane.repaint();
		
        }

	private String getImageIcon(String status){
		if(status.equals("available")){
			return "resources/images/user/user.jpe";
		}
		else if(status.equals("Hand-Raise")){
                        return "resources/images/user/hr.jpeg";
                }else {
			return "resources/images/user/allow-wb.gif";
		}
	}
}
