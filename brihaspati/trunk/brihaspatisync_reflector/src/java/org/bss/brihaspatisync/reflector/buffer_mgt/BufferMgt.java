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
import javax.sound.sampled.*;
/**
 *@author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 *@author <a href="mailto:meera.knit@gmail.com">Meera Pal </a>
 *@author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 *@date 19/06/2012
 */

public class  BufferMgt {
	
	private CreateHashTable createhashtable=new CreateHashTable();

	public BufferMgt() {}
        static int counter=0;	
        /**          
        *create removeBufferAndSetPointer method to remove the packets from a specific queue.
        */                   

        private void removeBufferAndSetPointer(String type){
		try {
        		Buffer buffer=createhashtable.set_getBuffer(type);
                       	int psize=0;                      
                       	Vector pointer=createhashtable.getPointer();
                        psize=pointer.size();
                        int maxpointer=(Integer)pointer.get(0);
                        int p1=(Integer)pointer.get((psize-1));
                        if(p1>0) {
                        	p1=p1-1;
				if(((maxpointer-p1) >10)|| (buffer.size()>10)) {
                                	createhashtable.resetPointer(5,type);
                                      	buffer.removeRange(0,5,type);
                                } else {
                                	createhashtable.resetPointer(p1,type);
					buffer.removeRange(0,p1,type);
                             	}
                     	}
            	}catch(Exception e){}
    	}

	
	private Object sendData_IncreasePointer(String ip,String type) throws Exception {
		try {	
             		int curpointer = createhashtable.getValue(ip,type);
			Buffer buffer=createhashtable.set_getBuffer(type);
			int size=buffer.size();
			if(curpointer<size){
				Object str=null;	
				while( curpointer != size) {
					String str1=(buffer.get(curpointer)).toString();
                                        str1=str1.trim();
                                        ip=ip.trim();
                                        if(!(str1.startsWith(ip))){
						str=buffer.getObject(curpointer);
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
		return null;
	 }


         /**
         * Create putByte method to insert the packets in appropriate queue after matching 
         * packet type and queue type.                
         */                  

	 public  synchronized void putByte(Object data,String current_ip,String type){
		try {
			counter++;
			if(counter==65535){
				counter=0;
			}
			if(type.startsWith("Audio_Post")){	
				Buffer buffer=createhashtable.set_getBuffer(type);
				java.util.Date now = new java.util.Date();
			        java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat("y-M-d'-'h:m:sa");
			        String str=dateFormatter.format(now);
				try {
					if(type.startsWith("Audio_Post")){
						String sessionid=type.replace("Audio_Post","");
						File f=new File(sessionid);
						if(!f.exists())
							f.mkdir();
						int bytesWritten = AudioSystem.write((AudioInputStream)data,AudioFileFormat.Type.WAVE,new File(sessionid+"/"+str+".wav"));
					}
				}catch(Exception ex){}
				buffer.putObject(str);
				buffer.put(current_ip);
			}else {
				Buffer buffer=createhashtable.set_getBuffer(type);
                                buffer.put(current_ip);
                                buffer.putObject(data);
			}
		}catch(Exception e){ System.out.println("Error in putByte method in BufferMgt class ----->"+e.getMessage()); }
	}
	
	 public int getBufferSize(){
                int cont=counter;
                return cont;
        }


 	public Object sendData(String newip,String type) {
                try {
			if(!newip.equals("")) {
				return sendData_IncreasePointer(newip,type);
			}
               	}catch(Exception s){ System.out.println("Error in sendData method ----->"+s.getMessage());}
		return null;
	}
	
	private synchronized void setPointer(String setip , int pointer,String type) {
		try {	
			createhashtable.setPointer(setip,pointer,type);
		}catch(Exception e){}
      	}  
}
