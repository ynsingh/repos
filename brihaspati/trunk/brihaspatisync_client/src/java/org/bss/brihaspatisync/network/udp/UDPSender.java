package org.bss.brihaspatisync.network.udp;

/*
 * UDPSender.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import java.util.Vector;
import java.io.IOException;

import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

import org.bss.brihaspatisync.network.util.Queue;
import org.bss.brihaspatisync.util.ClientObject;
//import org.bss.brihaspatisync.network.tcp.TCPReceiver;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>	
 */

public class UDPSender implements Runnable{

	private Queue udpsendQueue=null;
	
	private Thread runner=null;
	
	private static UDPSender udps=null;

	private final String parentIp=ClientObject.getController().getReflectorIP();

	/**
         * Controller for class.
         */
        public static UDPSender getController(){
                if(udps==null){
                        udps=new UDPSender();
                }
                return udps;
        }
	
	/**
         * Start TCPSender Thread.
         */
        public void start() throws IOException {
                if (runner == null) {
			udpsendQueue=new Queue();
                        runner = new Thread(this);
                        runner.start();
			System.out.println("UDP Sender Thread Start");
                }
        }
	
	/**
        * Stop TCPSender Thread.
        */
	public void stop() {
                if (runner != null) {
                        runner.interrupt();
                        runner = null;
                        runner.stop();
			System.out.println("UDP Sender Thread Start");
		}
        }
	
	/**
        * Check Queue for all new entry availble for broadcast.
        */
	public void run(){
		try {
			while(true){
				while(udpsendQueue.size()!=0 ){
					try{
						String data =udpsendQueue.getString();
						broadcast(data);
                                        	udpsendQueue.remove();
					}catch(Exception e){}
				}
				this.runner.yield();
                        	this.runner.sleep(200);
			}
		}catch(Exception ex){}	
        }

	/**
        * Broadcast tcp data to all neighbours
	*/
	public void broadcast(String broadcastdata){
		/*
        	Vector nodeList=TCPReceiver.getController().getRouter();
		try {
			DatagramSocket socket = new DatagramSocket();
               		for(int i=0;i<nodeList.size();i++) {
				if(!parentIp.equals((String)nodeList.elementAt(i))){
					InetAddress IPAddress = InetAddress.getByName((String)nodeList.elementAt(i));
        	       			byte[] sendData = new byte[1024];
                			sendData = broadcastdata.getBytes();
       	        			DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress,8181);
               				socket.send(sendPacket);
				}
                       	}
			socket.close();
              	}catch(Exception e) {}
		nodeList=null;
		*/
        }

	public Queue getSendQueue(){
                return udpsendQueue;
        }

        public Thread getRunner(){
                return runner;
        }

        public void setRunner(){
                runner=null;
        }

}

