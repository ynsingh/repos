package org.bss.brihaspatisync.gui;

/**
 * HandRaiseAction.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,Kanpur.
 */

import java.net.URLEncoder;

import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class HandRaiseAction {

	private static HandRaiseAction hraction=null;
	private String sb ="";
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
	public void actionONRequest(String Request,String selectedUsername){
		String lectid=null;
		this.selectedUsername=selectedUsername;
                try{
			String id=client_obj.getLectureID();
			if(!(id.equals("")))
                        	lectid ="lectID="+URLEncoder.encode(id,"UTF-8");
			else
				System.out.println("Insufficient lecture id in HandRaiseAction class"+id);
                        String user="loginName="+URLEncoder.encode(selectedUsername,"UTF-8");
                       	//JOptionPane.showMessageDialog(null,Language.getController().getLangValue("HandRaiseAction.MessageDialog1"));
			String indexServer1=client_obj.getIndexServerName();
			if(!(indexServer1.equals(""))){
				sb="HandRaiseAction";
                                sb=sb+id+","+user+","+Request;
                                utilObject.setSendQueue(sb);
				System.out.println(" sb "+sb+"\n\n\n\n");
                                
			}else{
				System.out.println("Insufficient indexServer name in HandRaiseAction :");
			}
                }catch(Exception ex){System.out.println("Error on actionPerformed in UserListPanel."+ex.getMessage());}
        }
}
