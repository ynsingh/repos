package org.bss.brihaspatisync.reflector.network.tcp;

/**
 * TCPClient.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.util.Vector;
import java.util.StringTokenizer;

import java.net.Socket;
import java.net.InetAddress;
import java.net.ServerSocket;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;

import org.bss.brihaspatisync.reflector.Reflector;
import org.bss.brihaspatisync.reflector.network.util.RuntimeObject;
import org.bss.brihaspatisync.reflector.network.serverdata.VectorClass;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class TCPClient implements Runnable{

	private int ipnumber= 0;
	
	private Thread runner=null;
	
	private String parentIp="";
	
	private String client_ip="";	
	
	private Socket pingskt=null;
	
	private PrintWriter out=null;
	
	private static int countint=0;
	
	private BufferedReader in=null;
	
	private Thread printWriterThread;
	
	private Thread bufferReaderThread;
	
	private boolean tcpsend_Flag=false;
	
	private static TCPClient tcps=null;
	
	private Vector course_id=new Vector();
	
	private MaintainLog log=MaintainLog.getController(); 
	
	private int TCP_Port=Reflector.getController().getTcpPort();
	
	/**
        * Controller for the class.
        */

	public static TCPClient getController(){
                if(tcps==null){
                        tcps=new TCPClient();
                }
                return tcps;
        }

	private TCPClient(){}

	/**
        * Start TCPSender Thread.
        */
        public void start(){
                if (runner == null) {
                        runner = new Thread(this);
                        runner.start();
			log.setString("TCP Sender Start");
		}
        }

	/**
         * Stop TCPSender Thread.
         */
        public void stop() {
                if (runner != null) {
                        runner.stop();
			runner = null;
			log.setString("TCP Sender Stop");
		}
        }

	/**
	 *Check Queue for all new entry availble for broadcast.
         */
	public void run(){
		try {	
			client_ip=parentIp;
			pingskt = new Socket(parentIp,TCP_Port);
			in = new BufferedReader(new InputStreamReader(pingskt.getInputStream()));
			out = new PrintWriter(pingskt.getOutputStream(),true);
		}catch(Exception e){ 
			System.out.println("Error in Catch TCPSender.java line no 96 ");	
		}
		(bufferReaderThread=new Thread(){
                	public void run(){
				synchronized(in){
                                	while(!tcpsend_Flag){
                                                try{
				                        String str="";
                                                        if((str=in.readLine())!=null) {
								if((str!=null) && (!str.equals(""))) {
									if(str.startsWith("BackupTreeCource_id")){
										str=str.replaceAll("BackupTreeCource_id","");		
										String str1[]=str.split("@");
										log.setString("str1[1] TCPClient "+str1[1]);
										TCPUtil.getController().getString(str1[0],str1[1]);					
									}else if(str.startsWith("SetUserList=")){
										String courseid=str.replaceAll("SetUserList=","");
										UserListUtil.getContriller().clearDataForVector(courseid);			
									}else if(str.startsWith("UserList=")){
										String courseid_data=str.replaceAll("UserList=","");
										String data[]=courseid_data.split("#");
										UserListUtil.getContriller().addDataForVector(data[0],data[1]);	
										
									}else {
									int temp=str.indexOf(",",0);
									String courseid=str.substring(0,temp);
									temp=temp+1;
                                                			str=str.substring(temp,str.length());
									MyHashTable temp_ht=RuntimeObject.getController().getMyHashTable();
									if(temp_ht.getStatus(courseid)) {
                                                                                BufferMgt te=temp_ht.getValues(courseid);
                                                                                te.putByte(str,client_ip);
                                                                        } else if(!temp_ht.getStatus(courseid)) {
                                                                                BufferMgt bm= new BufferMgt();
                                                                                temp_ht.setValues(courseid,bm);
                                                                                bm.putByte(str,client_ip);
                                                                        }
									}	
								}
							}
							bufferReaderThread.yield();
                                                	bufferReaderThread.sleep(100);
                                                }catch(Exception e){
							cleancourseID();
							for (int l=0;l<course_id.size();l++) {
								String courseid=course_id.get(l).toString();
								TCPClient.getController().setcourseID(courseid);
								MyHashTable tempht=RuntimeObject.getController().getUserListMyHashTable();
								VectorClass vectorclass=tempht.getCourseIdUserListVector(courseid);
								Vector v=vectorclass.getBackupTreeValue();
                                        			log.setString("Backup Tree value-------------"+v.toString())
;                                       			if((v.get(0).toString()).equals("#GParent")){
                                                   			String str=v.get(0).toString();
                                                                        str=str.replaceAll("#GParent","");
									setparentIp(str);
                                                                }

							}
							System.gc();			
                                                        TCPClient.getController().start();
			
							//}
							log.setString(" inputStream "+e.getMessage());
							log.setString(" TCPClient in inputStream "+e.getMessage());
							tcpsend_Flag=true;
							
							printWriterThread.interrupt();
                	                                printWriterThread.stop();
							bufferReaderThread.interrupt();
                                                        bufferReaderThread.stop();
	       	                                	try {        
								pingskt.close();
								pingskt=null;
	              					}catch(Exception ex ){
								log.setString("Error in to catch in TCPClient!! "+ex.getMessage());
							}       
							System.gc();         	                
							printWriterThread=null;
							bufferReaderThread=null;	
						}
					}
				}
			}
              	}).start();
		
		(printWriterThread =new Thread(){
                	public void run(){
				synchronized(out){
	                        	while(!tcpsend_Flag){
        	                        	try{
							if(ipnumber != course_id.size()){
								ipnumber=course_id.size();
								out.println("Cource_id"+course_id.get(ipnumber-1));	
								out.flush();
							}else{
								MyHashTable temp_ht=RuntimeObject.getController().getMyHashTable();
								for(int i=0;i<course_id.size();i++) {
									TCPUtil.getController().getString(course_id.get(i).toString(),"#Parent"+client_ip);
									if(temp_ht.getStatus(course_id.get(i).toString())) {
                                                                		BufferMgt buffer_mgt=temp_ht.getValues(course_id.get(i).toString());
        	                            					String str=buffer_mgt.sendData(client_ip);
                	                                        		if(str !=null ){
											str=course_id.get(i)+","+str;
				                                        		out.println(str);
                                        	                			out.flush();
                                	                        		}
									}
								}
							}
							countint++;
                                        		out.flush();	
							System.gc();
							printWriterThread.yield();
                                        	        printWriterThread.sleep(100);
						}catch(Exception ex){
							System.gc();	
							log.setString("Error in  TCPClient class in outputStream ");
						}
                             		}
				}
                    	}
		}).start();
	}
        
	

	public Thread getRunner(){
                return runner;
        }

        public void setRunner(){
                runner=null;
        }

	public void setcourseID(String str){
		course_id.add(str);
        }
	
	public void setparentIp(String str){
                parentIp=str;
        }
	
	private void cleancourseID(){
                course_id.clear();
        }
	

}


