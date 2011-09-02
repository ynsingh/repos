package org.bss.brihaspatisync.reflector.buffer_mgt;

/**
 * BufferMgt.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.util.Vector;
import java.util.Enumeration;
//import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
	

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal  </a> 
 */

public class  BufferMgt extends Thread {
	private String str="";	
	private int curpointer = 0;
	private Buffer buffer=null;
	private MyHashTable hashTable=null;
	//private MaintainLog log=MaintainLog.getController();
	
	public BufferMgt() {}
		
	private void startBuffer(){
                try {
			Vector pointer=new Vector();
                        int psize=0;
                        pointer=hashTable.getPointer();
                        psize=pointer.size();
                	if((pointer.size()) >0){
        	        	psize=pointer.size();
	                        int maxpointer=(Integer)pointer.get(0);
                                int p1=(Integer)pointer.get((psize-1));
				if(p1>0) {
					p1=p1-1;
	                        	if( (maxpointer-p1) >15) {
        	        	        	hashTable.resetPointer(5);
						buffer.removeRange(0,5);
        	        	      	} else {
						hashTable.resetPointer(p1);
                                        	buffer.removeRange(0,p1);
                                	}
				}
                        }
		}catch(Exception e){
			System.out.println("Error startBuffer() in BufferMng  "+e.getMessage());	
                }
        }
	
	private String run1(String ip) throws Exception {
		try {
			if(buffer==null){
                	        buffer=new Buffer();
			}if(hashTable == null) {
				hashTable = new MyHashTable();
			}
		}catch(Exception e){
			System.out.println("Error in buffer in BufferMgt");
		}	
		try {	
			curpointer = hashTable.getValue(ip);
			int size=buffer.size();
			if(curpointer<size){
				while( curpointer != size){
					
					String str1=buffer.getString(curpointer);
					str1=str1.trim();
					ip=ip.trim();
					if(!(str1.startsWith(ip))){
						int temp=str1.indexOf("@$",0);
						temp=temp+2;
			                        str=str1.substring(temp,str1.length());
						curpointer++;
        	                                setPointer(ip,curpointer);
						break;
                                	}
					curpointer++;
					setPointer(ip,curpointer);
					
				}
				startBuffer();	
			}
			return str;
			
		}catch(Exception e){
			System.out.println("Error in BufferMng  "+e.getMessage());
		}	
		return null;
	}
	
	public  synchronized void putByte(String data,String current_ip){ 
		try {
			if(buffer==null)
				buffer=new Buffer();
			if(!(data.equals("nodata"))){
				buffer.putString(current_ip+"@$"+data);
			}
		}catch(Exception e){ 
			System.out.println("Error in putByte method "+e.getMessage());
		}
	}
	public String sendData(String newip) {
               	String senddata=null;
                try {
			if(!newip.equals("")) {
				String abc=run1(newip);
        	                if(!(abc.equals(""))){
                	                senddata=abc;
                        	        this.str="";
				}
			}
                }catch(Exception s){
			System.out.println(" Error in sendData in  BufferMgt.java"+s.getMessage());
                }
		return senddata;
        }
	
		
		

	private synchronized void setPointer(String setip , int pointer) {
		try {	
			if(hashTable == null){
        			hashTable = new MyHashTable();
                	}
			hashTable.setPointer(setip,pointer);
		}catch(Exception e){
			System.out.println("Error in BufferMng  "+e.getMessage());
		}
        }
	
}

