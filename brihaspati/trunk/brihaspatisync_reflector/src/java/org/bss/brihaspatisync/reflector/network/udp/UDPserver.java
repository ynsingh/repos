package org.bss.brihaspatisync.reflector.network.udp;
// WAP for UDP server which can exchange message in text mode //
import java.util.*;
import java.net.*;
import java.io.*;
import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;
 
class UDPserver {
  
        
        private byte[] recbuffer=new byte[1024];
        private byte[] sendbuffer=new byte[256];
        private DatagramSocket s;
	private static UDPserver udps=null;
	private MaintainLog log=MaintainLog.getController();
		
	String Datafile,str1_port,str_ip,str0_port;

public static UDPserver getController(){
		if(udps==null)
			udps=new UDPserver();
			return udps;
		}	


public UDPserver(){
		try{
			
			ResourceBundle rb=ResourceBundle.getBundle("UDP");
			String t1=rb.getString("str1_port");
			log.setString("port from file "+t1);
			int port=Integer.parseInt(t1);
			log.setString("server is connected to port:"+port);
               		s=new DatagramSocket(port);
			
			while(true){
				log.setString("port from file ");
				String t2=rb.getString("str_ip");
				log.setString("server ip is:"+t2);
				DatagramPacket rp=new DatagramPacket(recbuffer,recbuffer.length);
                        	s.receive(rp);
				String str=new String(rp.getData());
				log.setString("Data Received = :"+str);
				BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
	               		String ss=input.readLine();
                		System.out.println("sending: " +ss);
                		byte [] sendBuffer=ss.getBytes();
				System.out.println("test");
				
	         		String s_t=rb.getString("str0_port");
				int send_port=Integer.parseInt(s_t);
				//InetAddress adr=InetAddress.getByName("172.28.44.44");
				//DatagramPacket pack=new DatagramPacket(sendBuffer,sendBuffer.length,adr,send_port);
				DatagramPacket pack=new DatagramPacket(sendBuffer,sendBuffer.length,(InetAddress.getByName(t2)),send_port);
				log.setString("send data successful");
				s.send(pack);
			}               		
            	}catch(Exception e){
			log.setString("UDPserver is not connected:");
         	}
	}

	public static void main(String args[]){
     		 UDPserver.getController();
     	}
}
         
