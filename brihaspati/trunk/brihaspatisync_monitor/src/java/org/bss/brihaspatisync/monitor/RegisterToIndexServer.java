package org.bss.brihaspatisync.monitor;

/**
 * RegisterToIndexServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import java.util.Vector;
import java.util.Properties;
import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLEncoder;
import java.net.InetAddress;

import javax.swing.JOptionPane;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.*;

import org.bss.brihaspatisync.monitor.util.HttpsUtil;
import org.bss.brihaspatisync.monitor.util.RuntimeDataObject;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */

public class RegisterToIndexServer {

	private static RegisterToIndexServer registertoserver=null;
	private HttpsURLConnection connection=null;
	private Vector indexServerList=null;
	private Vector reg_result=null;
	private String ServerIP="";
		
	/**
	 * Controller for this class.
	 */
	
	public static RegisterToIndexServer getController(){
		if(registertoserver==null)
			registertoserver=new RegisterToIndexServer();
		return registertoserver;
	}

       	public void setIServerIP(String temp){
                ServerIP=temp;
       	}
	
	public boolean connectToMasterServer() {
		Vector brihaspati=new Vector();
                try {
			String m_url=RuntimeDataObject.getController().getMasterUrl().trim()+"req=getLEDList";
			if(!(m_url.equals(""))){
                        	try {
                                        URL indexurl = new URL(m_url);
                                        connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                                        if(connection!=null) {
                                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                                try{
                					String str="";
                                          		if((str=in.readLine())!=null) {
								String str_1[]=str.split(",");
								for(int i=0;i<str_1.length;i++) {
									brihaspati.add(str_1[i]);	
								}
                                                        }
                                                } finally { if(in != null) { in.close(); } }
                                        } else{ JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");}
                                } catch(Exception e){ System.out.println("Error on getvectorMessage(connection) HttpsUtil.java "+e.getMessage()); }
                       	} 
		}catch(Exception e){}	
		if(brihaspati.size()>0 ) {
			org.bss.brihaspatisync.monitor.util.ClientObject.getController().setbrihaspatiServerAddr(brihaspati);
			return true;	
		}else {
			return false;	
		}
	}

	public Vector connectToMasterServer1(){
		Vector server_ip_vector=new Vector();
                try {
                        String m_url=RuntimeDataObject.getController().getMasterUrl().trim()+"req=getISList";;
                        if(!(m_url.equals(""))){
                                try {
                                        URL indexurl = new URL(m_url);
                                        connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                                        if(connection==null){
                                                System.out.println("Check your Network Connection or try again");
                                        }else{

                                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                                String str="";
                                                server_ip_vector.clear();
                                                try{
                                                        while((str=in.readLine())!=null) {
                                                                if(!(str.equals(""))) {
                                                                        server_ip_vector.add(str);
                                                                }
                                                        }
                                                }finally {
                                                        if(in != null) {
                                                                in.close();
                                                        }
                                                }
                                        }
                                } catch(Exception e){
                                        System.out.println("Error on getvectorMessage(connection) HttpsUtil.java "+e.getMessage());
                                }
                        }
                }catch (Exception ioe) {
                        System.out.println("Error at connectToMasterServer()in HttpsConnection"+ioe.getMessage());
                }
                return server_ip_vector;
        }

	        
	public void connectGetReflectorList(){
                String str="";
                try {
			String indexServer=ServerIP;
                        String req_url=indexServer+"/ProcessRequest?req=GetReflectorXML";
                        URL indexurl = new URL(req_url);
                        connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                        if(connection==null){
                                JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
                        }else{
                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                String str1="";
                                try {
                                        FileWriter fw=new FileWriter("Monitor.xml");
                                        while((str1=in.readLine())!=null) {
                                                  fw.write(str1+"\n");
                                        }
                                        fw.close();
                                }finally {
                                 	if(in != null)
                                                in.close();
                                }

                        }
                }catch(Exception er){System.out.print("Here also error in connection");}
        }

	public void connectGetIndexServerList(){
                String str="";
                try {
			String indexServer=ServerIP;
                        String req_url=indexServer+"/ProcessRequest?req=GetReflectorStatusXML";
                        URL indexurl = new URL(req_url);
                        connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                        if(connection==null){
                                JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
                        }else{
                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                String str1="";
                                try {
                                        FileWriter fw=new FileWriter("MonitorStatus.xml");
                                        while((str1=in.readLine())!=null) {
                                                  fw.write(str1+"\n");
                                        }
                                        fw.close();
                                }finally {
                                        if(in != null)
                                                in.close();
                                }
                        }
                }catch(Exception er){System.out.print("Here also error in connection");}
        }

	public void connectGetLectureXML(String lectID){ 
                String str="";
                try {
			String indexServer=ServerIP; 
			String lectid ="lectID="+URLEncoder.encode(lectID,"UTF-8");
                        String req_url=indexServer+"/ProcessRequest?req=GetLectureXml&"+lectid;
                        URL indexurl = new URL(req_url);
                        connection=HttpsUtil.getController().createHTTPConnection(indexurl);
                        if(connection==null){
                                JOptionPane.showMessageDialog(null,"Check your Network Connection or try again");
                        }else{
                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                String str1="";
                                try {
                                        FileWriter fw=new FileWriter(lectID+".xml");
                                           while((str1=in.readLine())!=null) {
                                                  fw.write(str1+"\n");

                                        }
                                        fw.close();
                                }finally {
                                        if(in != null)
                                                in.close();
                                }
                        }
                }catch(Exception er){System.out.print("Here also error in connection");}
        }
}	
