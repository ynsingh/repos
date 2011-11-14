package org.bss.brihaspatisync.reflector.network.tcp;


/*
 * MultiServerThread.java 
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009
 */

import java.util.Vector;

import java.net.Socket;
import java.net.InetAddress;
import java.net.ServerSocket;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.buffer_mgt.MyHashTable;
import org.bss.brihaspatisync.reflector.util.RuntimeDataObject;
import org.bss.brihaspatisync.reflector.network.serverdata.VectorClass;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;


/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class MultiServerThread extends Thread {
	
	private static MultiServerThread mst=null;
	
	private Socket socket = null;

	private Thread bufferReaderThread;

	private Thread printWriterThread;

	private	BufferedReader in=null;

	private PrintWriter out=null;

	private boolean flag=true;
	
	private String client_ip="";	

	private static int countint=0;	

	private Vector course_id=new Vector();	

	private MaintainLog log=MaintainLog.getController();
	
	private MultiServerThread() {
       	}
	
	protected MultiServerThread(Socket socket,String clientIP) {
		this.socket=socket;
		this.client_ip=clientIP;
		log.setString("start new thread MultiServerThread-"+clientIP);
	}

	public void run(){
		//Buffered Reader Thread is responsible for handle all inputStream for this socket.	
		(bufferReaderThread=new Thread(){
                	public void run(){
                        	try{
                                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                }catch(Exception e){}	

				synchronized(in){
                                	while(flag){
						try {
                                                	String str="";
                                               		if((str=in.readLine()) !=null ){
								if(str.startsWith("Cource_id")) {
									str=str.replaceAll("Cource_id","");
									course_id.add(str);	
									str="nodata";
								} else {
									if(course_id.size() > 0){
									if((str!=null) && (!str.equals(""))){
										int temp=str.indexOf(",",0);
	                                                                        String courseid=str.substring(0,temp);
        	                                                                temp=temp+1;
                	                                                        str=str.substring(temp,str.length());
										MyHashTable temp_ht=RuntimeDataObject.getController().getMyHashTable();
	                                                                        if(temp_ht.getStatus(courseid)) {
											BufferMgt te=temp_ht.getValues(courseid);
                        	                                                        te.putByte(str,client_ip,"ch_wb");
                                	                                        } else if(!temp_ht.getStatus(courseid)) {
                                        	                                        BufferMgt bm= new BufferMgt();
                                                	                                temp_ht.setValues(courseid,bm);
                                                        	                        bm.putByte(str,client_ip,"ch_wb");
                                                                	        }
									}}
								}
											
							}
							System.gc();
							bufferReaderThread.yield();
                                                	bufferReaderThread.sleep(500);
                                        	} catch (Exception e) { 
							try{
								System.gc();
								flag=false;
	                	                        	bufferReaderThread.interrupt();
        	                	                	socket.close();
                	                	        	bufferReaderThread.stop();
								socket=null;
								bufferReaderThread=null;
								
							}catch(Exception ex){
								log.setString("Error in MultiServerThread class in outputStream");
							}
							log.setString(" catch in MultiServerThread.java    BufferReader !!");
						}
					}
				}
                        }
                }).start();
		
		//printwriterThread is responsible for handle all output Stream from this socket.
                (printWriterThread =new Thread(){
			public void run(){
                                try{
                                        out = new PrintWriter(socket.getOutputStream(), true);
					synchronized(out){
					while(flag){
						if((countint/7) == 0){
							/**
							 * send userlist to client
							 */
							for(int i=0 ; i<course_id.size();i++){
 								String courseid=course_id.get(i).toString();
								MyHashTable tempht=RuntimeDataObject.getController().getUserListMyHashTable();
								VectorClass vectorclass=tempht.getCourseIdUserListVector(courseid);
							        Vector v=vectorclass.getBackupTreeValue();
								log.setString("Backup Tree value-------------"+v.toString());
								if(!(v.get(1).toString()).equals("#Parent")){	
									String str=v.get(1).toString();
									str=str.replaceAll("#Parent","");
									out.println("BackupTreeCource_id"+courseid+"@#GParent"+str);	
									out.flush();
								}
								TCPUtil.getController().getString(courseid,"#Client"+client_ip);
								out.println("SetUserList="+courseid);
								String data=UserListUtil.getContriller().getDataForVector(courseid);
								out.println("UserList="+courseid+"#"+data);
								out.flush();
							
							}
						}
						else if(course_id.size() > 0) {
							MyHashTable temp_ht=RuntimeDataObject.getController().getMyHashTable();
							for(int i=0 ; i<course_id.size();i++) {
								if(temp_ht.getStatus(course_id.get(i).toString())){
									BufferMgt buffer_mgt=temp_ht.getValues(course_id.get(i).toString());
        	                               				String str=buffer_mgt.sendData(client_ip,"ch_wb");
							        	if(str !=null ){
										str=course_id.get(i).toString()+","+str;
                                	                       	        	out.println(str);
										out.flush();
                                                        		}
								}
							}
							countint++;
						}
						System.gc();
						printWriterThread.yield();
                                                printWriterThread.sleep(500);
                                        }
					}
                                }catch(Exception e){	
					try{
						System.gc();	
						flag=false;
                        	               	printWriterThread.interrupt();
                                        	socket.close();
                                       		printWriterThread.stop();
                                       		socket=null;
                                       		printWriterThread=null;
						
                                	}catch(Exception ex){
						log.setString("Error in MultiServerThread class in outputStream");

					}
					log.setString(" catch in MultiServerThread.java print reader line 72 !!"); 
				}
                        }
                }).start();
	}
}
