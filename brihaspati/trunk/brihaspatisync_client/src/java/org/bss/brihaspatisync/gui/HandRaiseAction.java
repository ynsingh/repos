package org.bss.brihaspatisync.gui;

/**
 * HandRaiseAction.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,Kanpur.
 */

import java.net.URLEncoder;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */

public class HandRaiseAction implements ActionListener {

	private static HandRaiseAction hraction=null;
	private StringBuffer sb =new StringBuffer(100);
	private ClientObject client_obj=ClientObject.getController();
	private UtilObject utilObject=UtilObject.getController();
	private String selectedUsername="";
	protected static HandRaiseAction getController(){
		if(hraction==null)
			hraction=new HandRaiseAction();
		return hraction;	
	}

	private void actionONRequest(String Request){
                String user="";
		String lectid=null;
                try{
			String id=client_obj.getLectureID();
			if(!(id.equals("")))
                        	lectid ="lectID="+URLEncoder.encode(id,"UTF-8");
			else
				System.out.println("Insufficient lecture id in HandRaiseAction class "+id);

                        if((client_obj.getUserRole()).equals("student")){
                                user="loginName="+URLEncoder.encode(client_obj.getUserName(),"UTF-8");
                        }else if((client_obj.getUserRole()).equals("instructor")){
                                if(!selectedUsername.equals("")){
                                	user="loginName="+URLEncoder.encode(selectedUsername,"UTF-8");
					selectedUsername="";
				}else{
					JOptionPane.showMessageDialog(null,"Please select username ");
                                       	return;
				}
	              	}else
				System.out.println("Insufficient User Role :"+client_obj.getUserRole());
                        //String action="userAction="+URLEncoder.encode(Request,"UTF-8");
			String indexServer1=client_obj.getIndexServerName();
			if(!(indexServer1.equals(""))){
				sb=sb.append("HandRaiseAction");
                                sb=sb.append(id+","+user+","+Request);
                                System.out.println(sb.toString());
                                utilObject.setSendQueue(sb.toString());
                                sb.delete(0, sb.length());
				/*
                        	String indexServer =indexServer1+"/ProcessRequest?req=Permissions&"+lectid+"&"+user+"&"+action;
                        	if(!(HttpsUtil.getController().getIndexingMessage(indexServer)))
					JOptionPane.showMessageDialog(null,"There is some problem to Please try again");
				*/
			}else{
				System.out.println("Insufficient indexServer name in HandRaiseAction :");
			}

                }catch(Exception ex){System.out.println("Error on actionPerformed in UserListPanel."+ex.getMessage());}
        }

	public void actionPerformed( ActionEvent event )
        {
                String cmd=event.getActionCommand();
                if(cmd.equals("Allow-WB")) {
                        System.out.println("Allow WB");
                        actionONRequest("Allow-WB");
                }
                if(cmd.equals("Denie-WB")) {
                        System.out.println("Denie WB");
                        actionONRequest("available");
                }
                if(cmd.equals("Hand-Raise")) {
                        System.out.println("Handraise");
                        actionONRequest("Hand-Raise");
                }
        }
	
	protected void setSelectedUsername(String str){	
		this.selectedUsername=str;
	}
		
}
