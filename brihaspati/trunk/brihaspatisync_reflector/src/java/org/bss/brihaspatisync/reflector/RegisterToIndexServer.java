package org.bss.brihaspatisync.reflector;

/**
 * RegisterToIndexServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.util.Vector;
import java.util.Properties;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLEncoder;
import java.net.InetAddress;

import javax.swing.JOptionPane;

import javax.net.ssl.HttpsURLConnection;

import org.bss.brihaspatisync.reflector.util.HttpsUtil;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;

import org.bss.brihaspatisync.reflector.network.ppt.PPTGetAndPostServer;
import org.bss.brihaspatisync.reflector.network.tcp.TCPServer;

import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
import org.bss.brihaspatisync.reflector.network.http.HttpGetPost;

import org.bss.brihaspatisync.reflector.audio_video.TransmitHandlerThread;

import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;
import org.bss.brihaspatisync.reflector.network.desktop_sharing.DesktopPostServer;
import org.bss.brihaspatisync.reflector.network.desktop_sharing.DesktopGetServer;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListTimer;
import java.util.Timer;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a> 
 */


public class RegisterToIndexServer {

	private static RegisterToIndexServer registertoserver=null;
	private HttpsURLConnection connection=null;
	private Vector indexServerList=null;
	private Vector reg_result=null;
	private MaintainLog log=MaintainLog.getController();
	private Timer UL_Timer=null;
	private String ServerIP="";
		
	/**
	 * Controller for this class.
	 */
	public static RegisterToIndexServer getController(){
		if(registertoserver==null)
			registertoserver=new RegisterToIndexServer();
		return registertoserver;
	}
		
	protected String getIServerIP(){
        	String temp=ServerIP;
                ServerIP="";
                return temp;
	}

		
	public void getUserListToMasterServer(String m_url,String course_id){
		try {
                      	URL indexurl = new URL(m_url);
				
                        connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                        if(connection==null){
                        	JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
			}else{
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String temp="";
                                String str="";
				UserListUtil.getContriller().clearDataForVector(course_id);	
				try {
					while((str=in.readLine())!=null){
						temp=temp+","+str;	
					}
					UserListUtil.getContriller().addDataForVector(course_id,temp);	
				}finally {
					if(in != null) {
                                        	in.close();
                                        }
                               	}
			}
						
		} catch(Exception e){
			JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
              	}
	}
	/**
         * Instantiate connection to master server to retrieve the secondry indexing serveris' list
         * If there is secondary indexing serveris's list is find from master server, pass it to make connection 
	 * with indexserver otherwise it return from main method.
         */
        public boolean connectToMasterServer(){
		boolean flag=false;
        	try {
                	String m_url=RuntimeDataObject.getController().getMasterUrl().trim()+"req=getISList";;
                        if(!(m_url.equals(""))){
                                try {
                                        URL indexurl = new URL(m_url);
                                        connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                                        if(connection==null){
                                                JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
                                        }else{

                                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                                String str="";
                                                try{
                                                        while((str=in.readLine())!=null) {
                                                                if(!(str.equals(""))) {
                                                                        flag=true;
                                                                        ServerIP=str;
                                                                }
                                                        }
                                                }finally {
                                                        if(in != null) {
                                                                in.close();
                                                                return flag;
                                                        }
                                                }
                                        }
                                } catch(Exception e){
                                        log.setString("Error on getvectorMessage(connection) HttpsUtil.java "+e.getMessage());
                                }
			}else {
                                return flag;
                        }
                }catch (Exception ioe) {
                        log.setString("Error at connectToMasterServer()in HttpsConnection"+ioe.getMessage());
                }
                return flag;
	}
	

	
	/**
	 * Instantiate connection with index server with "inactive" status.
	 */
	public void connectToIndexServer(){
      		if(!ServerIP.equals("")){
                        try{
                                String indexServer=ServerIP;
                                String status= "status="+URLEncoder.encode("inactive", "UTF-8");
                                String req_url=indexServer+"/ProcessRequest?req=reflector_registration&"+status;
                                URL indexurl = new URL(req_url);
                                connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                                if(connection==null){
                                        JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
                                }else{
                                        if(bufferReader(connection.getInputStream())){
                                                if(setStatus(indexServer)){
                                                        RuntimeDataObject.getController().setindexServerAddr(indexServer);
                                                }
                                        } else{
                                                JOptionPane.showMessageDialog(null,"There is an error!! please try again");
                                        }
                                }
                        }catch(Exception e){
                                log.setString("Error on registeration "+e.getMessage());
                        }
                }else {
                        JOptionPane.showMessageDialog(null,"Reflector could not find iserver pls try again ");
                }
	}
	
	public void requestToChangeStatus(String str1){
                if(!ServerIP.equals("")){
                        try{
                                String str[]=str1.split(",");
                                String lectid ="lectID="+URLEncoder.encode(str[0].trim(),"UTF-8");
                                String user="loginName="+URLEncoder.encode(str[1].trim(),"UTF-8");
                                String action="userAction="+URLEncoder.encode(str[2].trim(),"UTF-8");
                                String indexServer =ServerIP+"/ProcessRequest?req=Permissions&"+lectid+"&"+user+"&"+action;
				//JOptionPane.showMessageDialog(null,indexServer);
                                URL indexurl = new URL(indexServer);
                                connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                                if(connection==null){
                                        JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
                                }else{
                                        if(!bufferReader(connection.getInputStream())){
                                                System.out.println("There is an error!! please try again");
                                        }
                                }
                        }catch(Exception e){
                                log.setString("Error on registeration "+e.getMessage());
                        }
                }else {
                        JOptionPane.showMessageDialog(null,"Reflector could not find iserver pls try again ");
                }
        }
	
	
	private boolean bufferReader(InputStream ins ){
		String str="";
		try{
		BufferedReader in=null;
		try {		
			in = new BufferedReader(new InputStreamReader(ins));
                        str=in.readLine();
		}finally {
                	if(in != null){
				in.close();
				if(str.equals("successfull")){
                                       return true;
                                }else{
                                        //JOptionPane.showMessageDialog(null,"There is an error!! please try again");
					return false;
                                }
			}
              	}
		}catch(Exception e){}
		return false;
	} 	

	private boolean  setStatus(String server){
		try{
			String status= "status="+URLEncoder.encode("active", "UTF-8");
			String req_url=server+"/ProcessRequest?req=reflector_status&"+status;
        	        URL indexurl = new URL(req_url);
                	connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                     	if(connection==null){
                     		JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
	                }else{
				if(bufferReader(connection.getInputStream())){
					startThreads();				
					return true;
                        	} else {
					return false;
				}
                  	}
		}catch(Exception ex){
			log.setString("There is an error to set status on indexing server");
		}
		return false;
	}

	protected String startThreads(){
		try {
			HttpGetPost.getController().start(); 	/** port 9999  */
			DesktopGetServer.getController().start();//DesktopSharing.getController().start();
			DesktopPostServer.getController().start();
			TCPServer.getController().start(); 	/** port 8888  */
			//log.start();
			TransmitHandlerThread.getControllerofHandler().start();
			PPTGetAndPostServer.getController().startThread();
			try{
				
        	                UL_Timer=new Timer(true);
                	        UL_Timer.schedule(UserListTimer.getController(),01,60*60*2);
				log.setString("timer start successfully");
	                }catch(Exception e){
				log.setString("Error in user list timer"+e.getMessage());
			}
			return "start_ok";
		} catch(Exception e){}
		return "";
	}
	
	protected Timer getTimer(){
                return UL_Timer;
        }

}	
	

