package org.bss.brihaspatisync.network.udp;
/*
 * UDPReceiver.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import org.bss.brihaspatisync.network.util.Queue;
//import org.bss.brihaspatisync.network.tcp.TCPReceiver;
import org.bss.brihaspatisync.tools.presentation.SlideShowWindow;
import org.bss.brihaspatisync.tools.presentation.ImageLoadforStudent;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class UDPReceiver implements Runnable{

        private String clientIP=null;
        private DatagramSocket server=null;
	private byte[] receiveData = new byte[1024];
        private Thread runner = null;
        private final int UDP_Port=8181;
	
	private int tempInt=0;
	private int tempInt1=0;
        private static UDPReceiver udpr=null;

	/**
         * Create Class Controller.
         */
        public static UDPReceiver getController(){
                if(udpr==null){
                        udpr=new UDPReceiver();
                }
                return udpr;
        }
	
	/**
         * Start UDPReceive Thread.
         */
        public void start() throws IOException {
                if (runner == null) {
                        runner = new Thread(this);
                        runner.start();
			System.out.println("UDP Receiver Thread Start");
                }
        }
	
	/**
         * Stop UDPReceive Thread.
         */
        public void stop() {
                if (server != null) {
                        runner.interrupt();
                        runner = null;
                        server.close();
                        server = null;
			System.out.println("UDP Sender Thread Stop");

		 }
        }

	/**
         * This Accept all new incomming udp connection.Check Router entry for this node,
         * Receive Stream from this node and put it into receiveQueue for local action
         * and sendQueue for Broadcast this to all neighbours.
         */

        public void run(){
        	try {
			server = new DatagramSocket(UDP_Port);
			while(true){
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				server.receive(receivePacket);
				String data=new String(receivePacket.getData());
				data=data.trim();
                                if(data.equals("cancleppt")){
                                        System.out.println("Receive UDP data------->"+data);
                                        SlideShowWindow.getController().getCanclePPT();
                                }else{

					int slide_index=Integer.parseInt(data);
					if(tempInt==tempInt1){
						tempInt++;
						showPPT();
					}else{
						slideShow(slide_index);
					}
				}
				UDPSender.getController().getSendQueue().putString(data);
				this.runner.yield();
	                        this.runner.sleep(200);

			}
		}catch(Exception e) {}
        }

	private void slideShow(int slide_index){
		ImageLoadforStudent.getController().runSlide(slide_index);
	}

	private void showPPT(){
			SlideShowWindow.getController().setUPGUI();	
	}

	public Thread getRunner(){
                return runner;
        }

        public void setRunner(){
                runner=null;
        }

}
