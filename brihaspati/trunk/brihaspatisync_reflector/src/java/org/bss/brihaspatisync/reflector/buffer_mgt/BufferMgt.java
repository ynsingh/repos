package org.bss.brihaspatisync.reflector.buffer_mgt;

/***
 * BufferMgt.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,IIT Kanpur.
 */

import java.util.Vector;
import java.util.Enumeration;
import java.io.*;	
import java.util.Hashtable;

/**
 *@author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 *@author <a href="mailto:meera.knit@gmail.com">Meera Pal </a>
 */

public class  BufferMgt {
	
	private int curpointer = 0;
	private CreateHashTable createhashtable=new CreateHashTable();

	public BufferMgt() {}
	
        /**          
        *create removeBufferAndSetPointer method to remove the packets from a specific queue.
        */                   

        private void removeBufferAndSetPointer(String type){
		try {
        		Buffer buffer=createhashtable.setBuffer(type);
                       	int psize=0;                      
                       	Vector pointer=createhashtable.getPointer();
                        psize=pointer.size();
                        int maxpointer=(Integer)pointer.get(0); 
                        int p1=(Integer)pointer.get((psize-1));
                        if(p1>0) {
                        	p1=p1-1;
				if( (maxpointer-p1) >15) {
                                	createhashtable.resetPointer(5,type);
                                      	buffer.removeRange(0,5);
                                } else {
                                	createhashtable.resetPointer(p1,type);
					buffer.removeRange(0,p1);
                             	}
                     	}
			
                }catch(Exception e){}
    	 }
	 private String sendData_IncreasePointer(String ip,String type) throws Exception {
		try {	
             		curpointer = createhashtable.getValue(ip,type);
			Buffer buffer=createhashtable.setBuffer(type);
			int size=buffer.size();
			if(curpointer<size){
				String str="";	
				while( curpointer != size) {
					String str1=(buffer.get(curpointer)).toString();
                                        str1=str1.trim();
                                        ip=ip.trim();
                                        if(!(str1.startsWith(ip))){
						str=(buffer.getObject(curpointer)).toString();
						/*
                                                int temp=str1.indexOf("@$",0);
                                                temp=temp+2;
                                                str=str1.substring(temp,str1.length());
						*/
                                                curpointer++;
                                                setPointer(ip,curpointer,type);
                                                break;
                                        }
                                        curpointer++;
                                        setPointer(ip,curpointer,type);
				}
				removeBufferAndSetPointer(type);
				return str;
			}

		} catch(Exception e){System.out.println("Error in sendData_IncreasePointer method "+e.getMessage());}	
		return "";
	 }

         /**
         * Create putByte method to insert the packets in appropriate queue after matching 
         * packet type and queue type.                
         */                  

	 public  synchronized void putByte(String data,String current_ip,String type){
		try {
			if(type.equals("ch_wb")) {
				Buffer buffer=createhashtable.setBuffer(type);
				if(!(data.equals("nodata"))){
					buffer.put(current_ip+"@$");
					buffer.putObject(data);
	                	}
			}

		}catch(Exception e){ System.out.println("Error in putByte method in BufferMgt class ----->"+e.getMessage()); }
	}

 	public String sendData(String newip,String type) {
          	String senddata=null;
                try {
			if(!newip.equals("")) {
				String data=sendData_IncreasePointer(newip,type);
        	                if(!(data.equals(""))){
                	     		senddata=data;
				}
			}
               	}catch(Exception s){ System.out.println("Error in sendData method ----->"+s.getMessage());}
		return senddata;
	}
	
	private synchronized void setPointer(String setip , int pointer,String type) {
		try {	
			createhashtable.setPointer(setip,pointer,type);
		}catch(Exception e){}
      	}  
}

  
