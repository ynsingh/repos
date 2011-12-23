package org.bss.brihaspatisync.monitor.gui;

/**
 * CheckStatusServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import java.net.Socket;
import java.util.Vector;
import java.net.Socket;
import java.util.Hashtable;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */

public class CheckStatusServer implements Runnable {
	
	private Vector v=null;
	private Thread runner=null;
	private Hashtable ht=new Hashtable();
	private static CheckStatusServer fw=null;
	
        public static CheckStatusServer  getController(){
                if (fw==null){
                        fw=new CheckStatusServer();
                }
                return fw;
	}
	
	public void start(){
                if (runner == null) {
                        runner = new Thread(this);
                        runner.start();
                }
        }

	public void stop() {
                if (runner != null) {
                        runner.stop();
                        runner = null;
                }
        }
		
        protected void checkServer(Vector brihaspatisync_v) {
		if(v==null)
			this.v=brihaspatisync_v;
	}
	
	public void run() {
		while(true) {
			try {
			if(v!=null) {
				for(int i=0; i<v.size();i++) { 
        	        		try {
		 				String tempstr=v.get(i).toString();
						if((!tempstr.equals("brihaspatisync")) && (!tempstr.equals("reflector")) && (!tempstr.equals("brihaspati"))) {
                        			String temp="";
                	      		        if(tempstr.startsWith("https://"))
        	                       			temp=tempstr.replace("https://","");
		                                if(tempstr.startsWith("http://"))
        	                        		temp=tempstr.replace("http://","");
						temp=temp.substring(0,temp.indexOf("/"));
                			        temp=temp.trim();
		                	        String str[]=temp.split(":");
						try {
							boolean flag=false;
						        try {
				                        	Socket Skt = new Socket(str[0].trim(), Integer.parseInt(str[1].trim()));
                        					flag=true;
			        		                Skt.close();
				        	                Skt=null;
				                	} catch (Exception ep) { 
							 	flag=false;
							} 
							String s = new Boolean(flag).toString();
							ht.put(str[0].trim()+":"+str[1].trim(),s);
						}catch(Exception ex){System.out.println(" Error in catch in CheckStatusServer.java !! ");}
						}
					} catch(Exception e){}
         			}
				try {
					this.runner.sleep(500);
					System.gc();
					this.runner.yield();
				} catch(Exception e){}
			}
			try {
                                        this.runner.sleep(500);
                                        this.runner.yield();
                                } catch(Exception e){}
			}catch(Exception ert){}
		}
        }
	
	public boolean flagServer_Running(String ip_port){
		if (ht.containsKey(ip_port)) {
			return Boolean.parseBoolean((ht.get(ip_port)).toString());
		} else
			return false;      	 
	}
}
