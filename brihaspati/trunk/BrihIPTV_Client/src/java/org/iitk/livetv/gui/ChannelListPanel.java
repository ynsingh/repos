package org.iitk.livetv.gui;
/**
 * ChannelListPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013 ETRG,Kanpur.
 */

import java.awt.Font;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import java.util.Vector;
import java.util.StringTokenizer;

import org.iitk.livetv.util.ClientObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 10Feb2013 
 */


public class ChannelListPanel extends Thread {

	private static ChannelListPanel ch_Panel=null;

	private JScrollPane scrollPane=null;
	private JList ch_list=null;
	private Vector ch_name_vector=null;
	private Vector ch_id_vector=null;
	private Vector ch_status_vector=null;
	private DefaultListModel model=null;
	private ClientObject clientObj=ClientObject.getController();

	public static ChannelListPanel getController(){
		if(ch_Panel==null)
			ch_Panel=new ChannelListPanel();
		return ch_Panel;
	}

	public JScrollPane createGUI(){
		model = new DefaultListModel();
		ch_list = new JList(model);
		ch_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                ch_list.setLayoutOrientation(JList.VERTICAL);
                scrollPane = new JScrollPane(ch_list);
		this.start();
		return scrollPane;
	}

	public void run(){
		 while(true) {
                        try {				
                               	displayChannelList(clientObj.getChannels());
				this.sleep(3000);
				this.yield();
                        }catch(Exception e){System.out.println("Error in channel list thread "+ e.getMessage());}
                }
	}

	public void displayChannelList(Vector channel_list){
		
		ClassLoader clr= this.getClass().getClassLoader();
                Object elements[][]=new Object[channel_list.size()][4];
                
		for(int i=0;i<channel_list.size();i++){
                       	String str=(channel_list.get(i)).toString();
			if(!(str.equals("noChannel"))){
                               	StringTokenizer st=new StringTokenizer(str,"$");
	                      	while(st.hasMoreTokens()){
        	                       	String ch_name=st.nextToken().trim();
                                      	String ch_id=st.nextToken().trim();
                       	               	String ch_status=st.nextToken().trim();
					elements[i][0] =  new Font("Helvetica", Font.PLAIN, 15);
                               		elements[i][1] =  Color.black;
	                               	elements[i][2] =  new ImageIcon(clr.getResource(getImageIcon(ch_status)));
		                       	elements[i][3] = ch_name;
				}
                      	}
                }
		ch_list.setListData(elements);
                ListCellRenderer renderer = new ChannelListCellRendered();
                ch_list.setCellRenderer(renderer);
                scrollPane.revalidate();
                scrollPane.repaint();
		channel_list.clear();
	}

	public String getImageIcon(String status){
		if(status.equals("1")){
                        return "resources/images/active.png";
                }else if(status.equals("0")){
                        return "resources/images/disable.png";
		}
		return "";
	}
}
