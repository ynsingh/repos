package org.bss.brihaspatisync.gui;

/**
 * HandRaiseAction.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,Kanpur.
 */

import java.net.URLEncoder;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;
import org.bss.brihaspatisync.util.RuntimeDataObject;
import org.bss.brihaspatisync.tools.whiteboard.WhiteBoardPanel;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HandRaiseAction implements ActionListener {

	private static HandRaiseAction hraction=null;
	private StringBuffer sb =new StringBuffer(100);
	private ClientObject client_obj=ClientObject.getController();
	private UtilObject utilObject=UtilObject.getController();
	private String selectedUsername="";

	/**
 	 * Controller for the class
 	 */
	public static HandRaiseAction getController(){
		if(hraction==null)
			hraction=new HandRaiseAction();
		return hraction;	
	}

	/**
 	 * Action for make signal for appropriate Handraise option (e.g request for whiteboard, request for audio mic, request for screen sharing etc.)
 	 */
	public void actionONRequest(String Request){
                String user="";
		String lectid=null;
                try{
			String id=client_obj.getLectureID();
			if(!(id.equals("")))
                        	lectid ="lectID="+URLEncoder.encode(id,"UTF-8");
			else
				System.out.println("Insufficient lecture id in HandRaiseAction class"+id);

                        if((client_obj.getUserRole()).equals("student")){
                                user="loginName="+URLEncoder.encode(client_obj.getUserName(),"UTF-8");
                        }else if((client_obj.getUserRole()).equals("instructor")){
				if((!Request.equals("Share-Screen")) && ((!Request.equals("Instructor_Allow-PPT"))) && ((!Request.equals("Instructor_Stop_Allow"))) ){
                                	if(!selectedUsername.equals("")){
                                                user="loginName="+URLEncoder.encode(selectedUsername,"UTF-8");
                                                selectedUsername="";
                                        }else{
                                                JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
                                                return;
                                        }
				}else {
					if(Request.equals("Instructor_Allow-PPT"))
						Request="Allow-PPT";
					if(Request.equals("Instructor_Stop_Allow"))
                                                Request="available";
					user="loginName="+URLEncoder.encode(client_obj.getUserName(),"UTF-8");
				}
	              	}else
				System.out.println("Insufficient User Role :"+client_obj.getUserRole());
			String indexServer1=client_obj.getIndexServerName();
			if(!(indexServer1.equals(""))){
				sb=sb.append("HandRaiseAction");
                                sb=sb.append(id+","+user+","+Request);
                                utilObject.setSendQueue(sb.toString());
                                sb.delete(0, sb.length());
			}else{
				System.out.println("Insufficient indexServer name in HandRaiseAction :");
			}

                }catch(Exception ex){System.out.println("Error on actionPerformed in UserListPanel."+ex.getMessage());}
        }

	public void actionPerformed( ActionEvent event ){

                String cmd=event.getActionCommand();
                if(cmd.equals("Allow-WB")) {
                        actionONRequest("Allow-WB");
                }
                if((cmd.equals("Denie-WB"))|| (cmd.equals("Denie-Mic")) || (cmd.equals("Denie-Screen")) || (cmd.equals("Denie-PPT"))) {
                        actionONRequest("available");
                }
                if(cmd.equals("Allow-Mic")) {
                        actionONRequest("Allow-Mic");
                }
		if(cmd.equals("Allow-Screen")) {
                        actionONRequest("Allow-Screen");
                }
		if(cmd.equals("Allow-PPT")) {
                        actionONRequest("Allow-PPT");
                }		

		if(cmd.equals("Get-WB")) {
                        actionONRequest("Get-WB");
                }
		if(cmd.equals("Get-Mic")) {
                        actionONRequest("Get-Mic");
                }
		if(cmd.equals("Get-Screen")) {
                        actionONRequest("Get-Screen");
                }
		if(cmd.equals("Get-PPT")) {
                        actionONRequest("Get-PPT");
                }
		if(cmd.equals("Cancel-Allow-Screen")) {
                        actionONRequest("Cancel-Allow-Screen");
                }
        }
	
	protected void setSelectedUsername(String str){	
		this.selectedUsername=str;
	}
		
}
