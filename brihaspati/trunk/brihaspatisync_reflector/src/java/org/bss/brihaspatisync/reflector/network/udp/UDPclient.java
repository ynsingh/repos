package org.bss.brihaspatisync.reflector.network.udp;
// WAP for UDPClient to print a message.
import java.util.*;
import java.net.*;
import java.io.*;
   
class UDPclient{
 
	
	private byte[] recBuffer=new byte[1024];
	private BufferedReader in=null;
	private static UDPclient udpc=null;
	String str0_port="";
	String str_ip="";
	String str1_port="";
	InetAddress add=null;
	int send_port=0;

public static UDPclient getController(){
		if(udpc==null)
			udpc=new UDPclient();
			return udpc;
		}
      
public UDPclient(){

	try{

		
		ResourceBundle rb=ResourceBundle.getBundle("UDP");
		//Properties prop=new Properties();
		String t3=rb.getString("str0_port");
		int port=Integer.parseInt(t3);
		DatagramSocket sock=new DatagramSocket(port);
		System.out.println("client is connected to Port:"+port);

		in=new BufferedReader(new InputStreamReader(System.in));
		String str=in.readLine();
		System.out.println("sending "+str);
		byte [] sendBuffer=str.getBytes();

		String t4=new String(rb.getString("str_ip"));
		System.out.println(rb.getString("str_ip"));
		add=InetAddress.getByName(t4);
		
		System.out.println("ip is:"+t4);

		String send_client=rb.getString("str1_port");
		System.out.println("client port is:"+send_client);
		send_port=Integer.parseInt(send_client);
		System.out.println("test for client"+send_port);

		DatagramPacket pack=new DatagramPacket(sendBuffer,sendBuffer.length,add,send_port);
		//System.out.println(add+"=-==============="+send_port);
		//pack.setAddress(add);
		//pack.setPort(send_port);
		System.out.println("control is here:");
		sock.send(pack);
		System.out.println("send Datagram packets");
		while(true){	
			DatagramPacket packet=new DatagramPacket(recBuffer,recBuffer.length);
			sock.receive(packet);
			String t= new String(packet.getData());
			System.out.println("Received data is : "+ t);
		}
	     	
	}catch(Exception e){
		System.out.println("Error :"+e.getCause());
		}
	
           
           
}   
       

public static void main(String args[]){
        UDPclient.getController();
       }
}
