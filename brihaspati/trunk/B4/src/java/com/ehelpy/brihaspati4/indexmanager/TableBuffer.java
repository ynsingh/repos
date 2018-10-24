package com.ehelpy.brihaspati4.indexmanager;

import java.io.File;
import java.util.ArrayList;

// This part of code is also simulation
//
public class TableBuffer extends IndexManagement {
		
	public 	ArrayList<String> ReceivingBuffer = new ArrayList<String>();
	public 	ArrayList<String> TransmittingBuffer = new ArrayList<String>();
	
	int length = 0;
	
	public void addMessage(String msg) 			// receive messages from other peers and save them in a queue of receivingBuffer
	{		
		length = ReceivingBuffer.size();						//System.out.println("size of buffer is ==" + ReceivingBuffer.size());	// this statement will generate the size of the receiving buffer
		ReceivingBuffer.add(length, msg);			// this statement will append the msg in the receiving buffer
	//	return ReceivingBuffer.get(length);			// this statement will return the element i.e. msg
		
	/*	System.out.println("Xml files inside Receiving buffer of TableBuffer Class  " + ReceivingBuffer);
	 * for(String x : ReceivingBuffer)	
			System.out.println("Xml files inside Receiving buffer of TableBuffer Class  " + x);					
		System.out.println("Xml files inside Receiving buffer of TableBuffer Class  " + ReceivingBuffer.get(length));  */
		
	}
	
	public String getMessage()				// get a message from the front of the queue of receiving buffer // returns an xml file
	{
		return ReceivingBuffer.get(length);
	}

	public void sendMessage(File file)	// send message to all peers  present in our routing table
	   {
		length = TransmittingBuffer.size();					//	System.out.println("size of buffer is ==" + TransmittingBuffer.size());	// this statement will generate the size of the transmitting buffer
		TransmittingBuffer.add(length, " "+file);			// this statement will append the file in the transmitting buffer
		//System.out.println(TransmittingBuffer);
	   }
	public String getxml_topass()				// get a message from the front of the queue of receiving buffer // returns an xml file
	{
		return TransmittingBuffer.get(length);
	}
	
}	


	//public 	String[] ReceivingBuffer = new String[3];
	//	int i = 0;
	//	System.out.println("size of buffer is ==" + length);
	/*	for(i=0;i<=length;i++)
		{
			//if(ReceivingBuffer[i]==null)
			//ReceivingBuffer[i] = msg;
			//break;
		}*/
		
	//	System.out.println(i);
	//	System.out.println(ReceivingBuffer[i]);
	//	return ReceivingBuffer[i];
		

	
	//public	String[] TransmittingBuffer = new String[3];
	//public 	String[] PingMsgReceivingBuffer;
	//public	String[] DHTRoutingtableBuffer;
		 
			
	/*public String getMessage(){
		if(ReceivingBuffer[0] !=null)							//get a message from the front of the queue of receiving buffer   //return message;
		{
			String firstentry = ReceivingBuffer[0];
			return firstentry;
		}
		else
		{
			return null;
		}
			
	}*/
	
	
	
	/*sendMessage(message) {
		send message to all peers  present in our routing table
	}

	size() {
		return receivingBuffer size
	}*/




