package org.bss.brihaspatisync.network.http;

/*
 * HTTPUserList.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import java.util.Vector;

import java.net.URL;
import java.net.HttpURLConnection;

import java.io.DataInputStream;
import java.io.BufferedInputStream;

import org.bss.brihaspatisync.Client;

import org.bss.brihaspatisync.network.Log;

import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto: ashish.knp@gmail.com"  >Ashish Yadav</a>
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 */

public class HTTPUserList {
	
	private  Vector vector=new Vector();
	
	private UtilObject utilObject=UtilObject.getController();

	private final String refHttpPort=Integer.toString(Client.getController().getRefHttpPort());
		
	private Log log=Log.getController();

	public HTTPUserList(){ }

	private static HTTPUserList httpUserList=null;
	
	public static HTTPUserList getController(){
		if(httpUserList==null )
			httpUserList=new HTTPUserList();
		return httpUserList;
	}
	public Vector getUserList(String reflectorIP,String lect_id) {
      		try {
			vector.clear();
			String url="http://"+reflectorIP+":"+refHttpPort+"/"+"lect_id="+lect_id+"req";
			URL serverAddress = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)serverAddress.openConnection();
                       	connection.setRequestMethod("GET");
                        connection.setDoOutput(true);
                        connection.connect();
			try{
                               	DataInputStream dis =new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                        	String str="";
				while((str=dis.readUTF())!=null){
                                	str=java.net.URLDecoder.decode(str);
                                        String str1[]=str.split(",");
                                        vector.clear();
                             	        for(int i=0;i<str1.length;i++)
                                		vector.add(str1[i]);
				}
                               	dis.close();
                	}catch(Exception ep){
                       		try {
                               		connection.disconnect();
                                       	connection=null;
                               	}catch(Exception ex){ log.setLog("Error in catch"+connection); }
                	}
			if(vector.size()>0)
				return vector;	
                }catch(Exception ex){ log.setLog(" HTTP UserList could not connect "+ex.getMessage());  }
		return null;
  	}
}
