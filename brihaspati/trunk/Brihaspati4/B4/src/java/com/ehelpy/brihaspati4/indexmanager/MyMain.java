package com.ehelpy.brihaspati4.indexmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyMain {
	
	static ReadXmlFile rxf = new ReadXmlFile();
	static Routing rout = new Routing();
	static BuildXml build = new BuildXml();
	static TableBuffer tblbuf = new TableBuffer();	
	static CommMgr comm = new CommMgr();	
	public static HttpsServer htserver = new HttpsServer();
	public static HttpsClient htclient = new HttpsClient();
	static IndexManagement indmgt = new IndexManagement();
		
	final static Map<String, String> myindex = new LinkedHashMap<String, String>();

	public static void main(String[] args) throws Exception {
		
		ArrayList <String> arraylist = new ArrayList<String>(5);	
		arraylist = comm.filename;									
		comm.Add_Index(arraylist);								// Here, invoking Add_Index method of comm mgr class which will invoke Add_in_Index method in IndexManagement Class
		
		
		String filename = indmgt.GetFile_toPass();		
		String self_node_id = rout.getmynodeid();			// here calling a function getmynodeid() from the routing table who will return node's node_id.
		String[] mysuccessors = rout.getmysuccessors();
		System.out.println("Now, transferring the index table to successors");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		indmgt.transferIndexTabletoSuccessors(self_node_id, mysuccessors[0],filename);					// though here i am calling this method but their shoul also a logic to run when to call this method
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");

		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Search for the presence of key (cdef1) in index_table and return its corresponding value.");
		String value = comm.Search_Index("cdef1");				// Here, invoking Search_Index method of comm mgr class which will invoke Search_in_Index method in IndexManagement Class
		System.out.println("The corresponding node_id is : " + value);
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		
		System.out.println("Now Check which node joined the overlay");				// this could be done in two ways either it could be event based or periodic event
		if(rout.Get_newly_joined_node()!=null)
		{
		String newly_joined_node = rout.Get_newly_joined_node();
		System.out.println("Newly joined node is : " + newly_joined_node);
		System.out.println("Now " + newly_joined_node + " will send a request for indexing to its immediate successor ");
		indmgt.GetIndexing();									// invoke GetIndexing method which will generate request for indexing message and send it to immediate successor
																// this message will be an xml file with tag = "0003" which indicates to the successor that it is a request for indexing and thus needful to be done
		}
	}
	
}	
			







