package org.bss.brihaspatisync.network.util;

/**
 * UtilObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import org.bss.brihaspatisync.network.ReceiveQueueHandler;
import org.bss.brihaspatisync.network.util.Queue;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UtilObject {

	private Queue recQueue=null;	
	private Queue sendQueue=null;
	private static UtilObject nob=null;
	
	public static UtilObject getController(){
		if(nob==null)
			nob=new UtilObject();
		return nob;
	}
	
		
	public UtilObject(){
		this.recQueue  =new Queue();
		this.sendQueue =new Queue();
	}
	
	public void setRecQueue(String value){
                recQueue.putString(value);
        }
	public int getRecQueueSize(){
                return recQueue.size();
        }
	public String getRecQueue(){
		String value="";
		try {
                	value =recQueue.getString().toString();
			recQueue.remove();
		}catch (Exception e){}
		return value;
        }
	
	public void setSendQueue(String value){
                sendQueue.putString(value);
        }
	
        public String getSendQueue(){
		String value="";
                try {
			value= sendQueue.getString().toString();
			sendQueue.remove();
		}catch (Exception e){}
		return value;
        }
	
	public int getSendQueueSize(){
                return sendQueue.size();
        }
}

