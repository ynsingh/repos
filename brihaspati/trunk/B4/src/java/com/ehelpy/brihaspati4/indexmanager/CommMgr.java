package com.ehelpy.brihaspati4.indexmanager;

import java.io.IOException;
import java.util.ArrayList;

// This part of code is also for simulation.
// Here also i am assuming that there is a CommMgr Class which will fetch xml files from source to receiving buffer of table buffer class
public class CommMgr extends MyMain
{
	public 	ArrayList<String> filename = new ArrayList<String>(5);				//		System.out.println("Xml files reached to CommMgr Class are : ");				// newline 090417
	  																	//		System.out.println("\n");	// new line 090417
	{
		for(int j =0;j<10;j++)
		{
			String fetchxml = "Query"+j+".xml";	
			tblbuf.addMessage(fetchxml);					// passing the info to addmessage method of tablebuffer class
			filename.add(tblbuf.getMessage());   			// tblbuf.getMessage() will return the file name and add method method will append it to filename arraylist
		}
	
		//	System.out.println("\nPassing these Xml files to TableBuffer class to store inside Receiving buffer ");
		//	System.out.println("\n");
		//	System.out.println("Xml files inside Receiving buffer of TableBuffer Class are : \n");
		//	System.out.println(tblbuf.ReceivingBuffer);	
		
		//	File file = build.returnxmlFile();					// method in BuildXml class which returns xml file name
		//	tblbuf.sendMessage(file);							// passing this xml file to sendMessage method of table buffer class
		//System.out.println(file);
	}
	
	public void Add_Index(ArrayList<String> filename) throws Exception
	{
		//  This is a method which will receive arraylist (having queries) from the mymain class which is actually fetched from comm mgr class only
		// actually here it should be only receiving hashid (key only) and once it is matched with the self ode or lies between itself and predecessor 
		//	then it will ask for node id (value) to append in the index table.
		
		String[] info_from_xml = {""};
		for(int j=0;j<10;j++)
		{
		//	System.out.println("Passing Xmlfile "+comm.filename.get(j)+" to ParseXml method of ReadXmlFile Class");		// new line 090417
			info_from_xml = rxf.ParseXml(filename.get(j));		//	System.out.println("Parsing returning hash id : " +xmldetail);// calling parsexml method of readxmlfile and storing its return value in a string array
			rxf.k++;														// to increment counter in ReadXmlfile class
			for(int x=0;x<3;x++)
				{
					if(x==0)System.out.println("Tag_Value  : " + info_from_xml[x]);
					if(x==1)System.out.println("Hash_Value : " + info_from_xml[x]);
					if(x==2)System.out.println("Node_Value : " + info_from_xml[x]);
				} 
					indmgt.Add_in_Index(info_from_xml);
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}
	
	public String Search_Index(String key) throws Exception				// Here the query will come if it is responsible node otherwise it will be forwarded to next hop method in Commn Mgr only.
	{
		String hash_id = key;
		String node_id = indmgt.Search_in_Index(hash_id);
		String value = node_id;
		return value;
	}
	
	
	public void sendxml_tosuccessor(String self_nodeid, String successor_nodeid, String filename, int decrement_counter) throws Exception 
	{
		int counter = 0;
		counter = decrement_counter ;
		
		Thread t1 = new Thread(new Runnable() {				// here implementing threads to start http server, because server will send this file to the first successor
			
			@Override
			public void run() 
			{
						try { htserver.server(filename);} 
						catch (IOException e) { e.printStackTrace();}
			
		     } 
		});
		t1.start();
		htclient.client(self_nodeid,successor_nodeid);
		counter--;
		if(counter > 0)
		{
						/*Here i am using recursion to call the method within the same method */
		
	//	String[] mysuccessors = rout.getmysuccessors();			// here, i am  under assumption that routing class is updated and once we call get my successors method it will always pass the updated list of successors.
	//	sendxml_tosuccessor(self_nodeid,mysuccessors[5-counter],counter);	// here i am calling the same method and passing the next successor at every iteration so that it can identify the ip address of this successor and can set up a socket to transfer xml file.
// will execute above line once i will be able to resolve problem of how to reuse a socket
		}
		else
		{
			System.out.println("Copy reached to all 5 successors");
		}
	}
	/*	Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				
				try {
					htclient.client(first_successor_nodeid, file_to_pass1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		});
		//System.out.println(tblbuf.getxml_topass());
		//System.out.println(rout.get_ip_address(first_successor_nodeid));
		// now we use network class to set up FTP connection between self and the first successor and then we will call it 
		t1.start();
		t2.start();*/
		
	/*	try{
		t1.join();
		t2.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}*/
	
	public void request_for_indexing(String successor_nodeid) throws Exception 
	{
		
		String transmit_request = tblbuf.getMessage();   	// tblbuf.getMessage() will return the request file name 	
		Thread t1 = new Thread(new Runnable() {				// here implementing threads to start http server, because server will send this file to the first successor
			
			@Override
			public void run() 
			{
						try { htserver.server_handle_request(transmit_request);} 
						catch (IOException e) { e.printStackTrace();}
			
		     } 
		});
		t1.start();
		htclient.client_handle_request(successor_nodeid);
	}
}

