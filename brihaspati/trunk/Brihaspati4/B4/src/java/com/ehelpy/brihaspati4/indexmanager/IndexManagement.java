package com.ehelpy.brihaspati4.indexmanager;

import java.util.Collection;
//import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.Exception;
import java.net.Inet4Address;

public class IndexManagement extends MyMain {

	public static int decrement_counter = 5;   // counter used to transfer xml file to 5 successors.

	final static Map<String, String> myindex = new LinkedHashMap<String, String>();
	final static Map<String, String> fresh_index = new LinkedHashMap<String, String>();

	public void Add_in_Index(String[] xml_info) throws Exception			// xml_info[1]=hashid from readxmlfile
	{
		if(xml_info[0].equals( "0001"))				/// advertisement   to enter it into index table
		{												// Here, the received query with tag value = 0001 means advertisement
														// then this node will put its value in its index table, if
														// (a) it is exact match, root_node_id.equals(xml_info[1]) or
														// (b) it is between self(incl) and first predecessor (excl)
		
														// How to get self node id;
		String self_node_id = rout.getmynodeid();		// here calling a function getmynodeid() from the routing table who will return node's node_id.
		String[] predecessor = rout.getmypredecessor();	// here calling a function getmypredecessor() from the routing table who will return node's predecessor.
		try
			{         
			if(xml_info[1].equals(self_node_id))					// it returns boolean value// Here, if it is a exact match  i.e. queried hash_id matching exactly with root node id
				{
					myIndexTables(xml_info[1], xml_info[2]);
				}
			else if ((xml_info[1].compareToIgnoreCase(self_node_id)<0) && (xml_info[1].compareToIgnoreCase(predecessor[0])>0))		
																				// Here, if the queried hash_id is not exactly matching with root node id 
					{															// then we need to compare it as follows :-
																				//	(a) first it should be smaller than root node means before it (xmldetail1[1].compareToIgnoreCase(root_node_id)<0)
																				//	(b) and should be greater than its first predecessor          (xmldetail1[1].compareToIgnoreCase(predecessor)>0)
																				// if it satisfies these two conditions then it lies between them then it need to append that entry in index of root else discard.
																				// compareToIgnoreCase Returns:a negative integer, zero, or a positive integer as the specified String is greater than, equal to, or less than this String, ignoring case considerations.
					myIndexTables(xml_info[1], xml_info[2]);
					}
					
					else
					{
						System.out.println("Discard");
					}
			}
			catch(Exception e)
			{
			e.printStackTrace();
			}
		}
			
	}
		
	public String Search_in_Index(String hash_id) throws Exception			// hashid from readxmlfile
	{
		String self_node_id = rout.getmynodeid();							// here calling a function getmynodeid() from the routing table who will return node's node_id.
		System.out.println("Self_node_id is : " + self_node_id);
		String value = "";
		try
			{         
			if(hash_id.equals(self_node_id))								// it returns boolean value// Here, if it is a exact match  i.e. queried hash_id matching exactly with root node id
				{
					value = self_node_id; 
				}
			else if(myindex.containsKey(hash_id))							// Returns true if this map contains a mapping for the specified key.
			    {		
					value = myindex.get(hash_id);				
					//System.out.println(value);
			    }	
					
			}
			catch(Exception e)
			{
			e.printStackTrace();
			}
		return value;
	}
		
	final static void myIndexTables(String hash_id, String node_id)						// code 17Apr17
		{
			System.out.println("index table before entry");
			System.out.println(myindex);
			myindex.put(hash_id, node_id);				// here appending xml details i.e. hash_id and node_id in index
			System.out.println("index table after this entry is ");
			System.out.println(myindex);
		}
		
	void transferIndexTabletoSuccessors(String node_id, String successor, String filename) throws Exception
		{
			String self_node_id = node_id;		// here calling a function getmynodeid() from the routing table who will return node's node_id.
			String mysuccessor = successor;
			comm.sendxml_tosuccessor(self_node_id,mysuccessor, filename, decrement_counter);    // here i am  providing this info to communication manager class so that it can fetch the file to pass and 
																								//	also identify the ip address of its successor and can set up a socket to transfer xml file.
		}
	
	public String GetFile_toPass() throws Exception
	{
		build.convert_hashmap_toxml(myindex);				// passing hashtable myindex to convert_hashmap_toxml method of BuildXml class using its object
		File file = build.returnxmlFile();					// method in BuildXml class which returns xml file name
		tblbuf.sendMessage(file);							// passing this xml file to sendMessage method of table buffer class
		String file_to_pass = tblbuf.getxml_topass();						// getting the xml file to pass from tablebuffer method getxml_topass which returns the xml file stored in transmitting buffer
		String final_file_to_pass = file_to_pass.replaceAll("\\s","");			// in transmitting buffer file is stored as a string. for that a type conversion was done by adding a sting space at front, this single line of code will remove that space and again convert it into file type.
		return final_file_to_pass;
	}
	
	static void myPredecessorIndexTables(Map<String, String> receive_indextable)		// code 17Apr17  // storing for redundancy
	{
		//String[] mypredecessors = {"","","","",""};
					
	//	String[] mypredecessors = rout.getmypredecessor();
	}
	
	public void GetIndexing() throws Exception
	{
	//	String[] mysuccessor = rout.getmysuccessors();			// asking successor details from routing table to whom this request will be sent
		String mysuccessor = rout.getmynodeid();
		
	// now i hv to create a request message to send it to my successor that i need those entries from its index table to whom now i am repsonsible, bcz i recently joined the overlay.
		
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = f.newDocumentBuilder();
		Document doc = db.newDocument();
		
		Element rootele = doc.createElement("Request_for_Indexing");
		Element codeele = doc.createElement("Query_");
			
		String tag = "0002";			/// let us assume that this tag signifies request for indexing
	
		((Element)codeele).setAttribute("tag", tag);
		
		rootele.appendChild(codeele);
		doc.appendChild(rootele);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new  FileOutputStream("Request_for_Indexing.xml"));
		transformer.transform(source, result);
		
		String fetch_request = "../Request_for_Indexing.xml";	
			
		tblbuf.addMessage(fetch_request);				// passing the info to addmessage method of tablebuffer class
		System.out.println("Get indexing method sending request to successor " + mysuccessor);
		comm.request_for_indexing(mysuccessor);		// here i am  providing this info to request_for_indexing method of communication manager class because once a node joins it request for indexing to its immediate successor
		
	}
	
	void Generate_Indexing() throws Exception
	{
		// now the newly joined node have its immediate successors and predecessors
				// now a series of activities will happen
				//	(a)	this node will check its index table to find out those key value pairs for which the newly joined node is responsible
				//		--- these will be those key value pairs for which the newly joined node is the root node and 
				//		--- those key value pairs which lies between newly joined node and its immediate predecessor
				//  (b) these key value pairs need to be identified, placed in new index table and to be handed over to newly joined node
				//	(c) at same time these identified key value pairs need to be purged from the existing index table.
				//	(d) the index table after purging needs to be updated at successors
				//	(e)	the newly joined node has to send its index table to its 5 successors
				//	(f) the newly joined node has to take over the responsibility as successor of its immediate predecessor thus it has to take its data and maintain it
		
		System.out.println("--------------------- Generate Indexing Method Starts ------------------------------ ");
		
		
		// (a)	this node will check its index table to find out those key value pairs for which the newly joined node is responsible
		//		--- these will be those key value pairs for which the newly joined node is the root node and 

		
		String self_node_id = rout.Get_newly_joined_node();
		System.out.println("Self id : " + self_node_id);
		
		// here when a node joins an overlay it has nothing, therefore it will first ask from successor his predecessor list
		String[] mypredecessors = rout.getmypredecessor();
		// now it will decide its immediate predecessor
		String my_immediate_predecessor = mypredecessors[0];
		System.out.println("Predecessor : " + my_immediate_predecessor);
		// and it will make the successor its immediate successor 
		String my_immediate_successor = rout.getmynodeid();  // this will gv successor node id
		System.out.println("Successor : " + my_immediate_successor);
		
		if(myindex.containsKey(self_node_id))				// Returns true if this map contains a mapping for the specified key.
		{													// this is for a case when the newly joined node is root node for those entries which were in its successor index table due to its absence
		//	Map<String, String> fresh_index = new LinkedHashMap<String, String>();
		//	myindex.get(self_node_id);	   this returns the value corresponding to given key
			
			System.out.println("fresh index table before entry is " + fresh_index);
		//	System.out.println(fresh_index);
			
			fresh_index.put(self_node_id, myindex.get(self_node_id));   // second argument will extract value iro this key i.e. self_node_id
						
			System.out.println("fresh index table after entry " + fresh_index);
		//	System.out.println(fresh_index);
			
				
			
		//	(c) at same time these identified key value pairs need to be purged from the existing index table.
			
			System.out.println("Original index table changed from " + myindex );
			myindex.remove(self_node_id);				// remove the identified entries from original index table
			System.out.println("to " + myindex );
			

			
			
		//  (b) these key value pairs need to be identified, placed in new index table and to be handed over to newly joined node
		//		--- those key value pairs which lies between newly joined node and its immediate predecessor	
			
			//	now is the turn for those entries in the original index table which lies between newly joined node and its predecessor (earlier this was predecessor to its successor)	
			//	-- for this we need to identify those hash (key) which are lesser than newly joined node but greater than predecessor
			//	-- for this we will extract keyset and convert it to array
			
			
			Collection<String> key_extracted = myindex.keySet();			/// code to extract hash_id from array by first converting it into collection then to an array
			Object[] key_array = key_extracted.toArray();
			
			for(int i=0;i<key_array.length;i++)
			{
			//	System.out.println(key_array[i]);
			// now we will do the comparison
				if((((String) key_array[i]).compareToIgnoreCase(self_node_id)<0) && (((String) key_array[i]).compareToIgnoreCase(my_immediate_predecessor)>0))
				{
					fresh_index.put((String) key_array[i], myindex.get(key_array[i]));   // second argument will extract value iro this key i.e. self_node_id
					
//					(c) at same time these identified key value pairs need to be purged from the existing index table.	
					myindex.remove(key_array[i]);										// remove the entries from original index table
				}
			}
			System.out.println("fresh index table after finding other nodes " + fresh_index);
			System.out.println("Original index table changed to " + myindex );
			
		
		}
	
	// also it will tell its successor to update its predecessor list by purging the first entry i.e. predecessor[0] = predecessor[1] and so on.
	// by calling update predecessor method.
	// also it will tell its predecessor to update its successor list
		System.out.println("--------------------- Generate Indexing Method Close ------------------------------ ");

	}
	
	void Send_Indexing() throws Exception
	{
		String my_immediate_successor = rout.getmynodeid();  // this will gv successor node id
		String self_node_id = rout.Get_newly_joined_node();
		System.out.println("Inside Send_indexing");
//		(d) the original index table after purging needs to be updated by immediate successor node to its successors
				String[] mysuccessors = rout.getmysuccessors();
				build.convert_hashmap_toxml(myindex);			// passing hashtable myindex to convert_hashmap_toxml method of BuildXml class using its object
				File file = build.returnxmlFile();					// method in BuildXml class which returns xml file name
				tblbuf.sendMessage(file);
				String file_to_pass = tblbuf.getxml_topass();						// getting the xml file to pass from tablebuffer method getxml_topass which returns the xml file stored in transmitting buffer
				String file_to_pass1 = file_to_pass.replaceAll("\\s","");			// in transmitting buffer file is stored as a string. for that a type conversion was done by adding a sting space at front, this single line of code will remove that space and again convert it into file type.
//				System.out.println(file_to_pass1);

				transferIndexTabletoSuccessors(my_immediate_successor, mysuccessors[0],file_to_pass1);					// though here i am calling this method but their shoul also a logic to run when to call this method
				

			//	(e)	the newly joined node has to send its index table to its 5 successors
				build.convert_hashmap_toxml(myindex);			// passing hashtable myindex to convert_hashmap_toxml method of BuildXml class using its object
				File file1 = build.returnxmlFile();					// method in BuildXml class which returns xml file name
				tblbuf.sendMessage(file);
				String file_to_passs = tblbuf.getxml_topass();						// getting the xml file to pass from tablebuffer method getxml_topass which returns the xml file stored in transmitting buffer
				String file_to_pass2 = file_to_passs.replaceAll("\\s","");			// in transmitting buffer file is stored as a string. for that a type conversion was done by adding a sting space at front, this single line of code will remove that space and again convert it into file type.
//				System.out.println(file_to_pass1);

				transferIndexTabletoSuccessors(self_node_id, my_immediate_successor,file_to_pass2);
	}
	
	static void Update_Indexing()
	{
		
	}
	
}	
			
			






/*	Map<String, String> predecessor1_index = new LinkedHashMap<String, String>();
predecessor1_index.putAll(receive_indextable);									// puts all value of receive_indextable into predecessor1_index as a hash map.
System.out.println("Predecessor1 " + mypredecessors[0] + " index table is "+ predecessor1_index);  //predecessor1 is closest

Map<String, String> predecessor2_index = new LinkedHashMap<String, String>();
predecessor2_index.putAll(receive_indextable);									// puts all value of receive_indextable into predecessor1_index as a hash map.
System.out.println("Predecessor2 " + mypredecessors[1] + " index table is "+ predecessor2_index);

Map<String, String> predecessor3_index = new LinkedHashMap<String, String>();
predecessor3_index.putAll(receive_indextable);									// puts all value of receive_indextable into predecessor1_index as a hash map.
System.out.println("Predecessor3 " + mypredecessors[2] + " index table is "+ predecessor3_index);

Map<String, String> predecessor4_index = new LinkedHashMap<String, String>();
predecessor4_index.putAll(receive_indextable);									// puts all value of receive_indextable into predecessor1_index as a hash map.
System.out.println("Predecessor4 " + mypredecessors[3] + " index table is "+ predecessor4_index);

Map<String, String> predecessor5_index = new LinkedHashMap<String, String>();
predecessor5_index.putAll(receive_indextable);									// puts all value of receive_indextable into predecessor1_index as a hash map.
System.out.println("Predecessor5 " + mypredecessors[4] + " index table is "+ predecessor5_index);*/



			
			
		/*	Map<String, String> successor1_index = new LinkedHashMap<String, String>();
			successor1_index.putAll(myindex);
			System.out.println("Successor1 " + mysuccessors[0] + " additional index table "+ successor1_index);
			
			Map<String, String> successor2_index = new LinkedHashMap<String, String>();
			successor2_index.putAll(myindex);
			System.out.println("Successor2 " + mysuccessors[1] + " additional index table "+ successor2_index);
			
			Map<String, String> successor3_index = new LinkedHashMap<String, String>();
			successor3_index.putAll(myindex);
			System.out.println("Successor3 " + mysuccessors[2] + " additional index table "+ successor3_index);
			
			Map<String, String> successor4_index = new LinkedHashMap<String, String>();
			successor4_index.putAll(myindex);
			System.out.println("Successor4 " + mysuccessors[3] + " additional index table "+ successor4_index);
			
			Map<String, String> successor5_index = new LinkedHashMap<String, String>();
			successor5_index.putAll(myindex);
			System.out.println("Successor5 " + mysuccessors[4] + " additional index table "+ successor5_index);   */
			
		





/*	String teststring = "0000" + "1111";				// code to convert string to char and then taking required char values and again converting back to string

char[] Self_NodeIDarray = teststring.toCharArray();

String tag1 = String.valueOf(Self_NodeIDarray[0]);
String tag2 = String.valueOf(Self_NodeIDarray[1]);
String tag3 = String.valueOf(Self_NodeIDarray[2]);
String tag4 = String.valueOf(Self_NodeIDarray[3]);
String finaltag = tag1+tag2+tag3+tag4;
System.out.println(finaltag);  */

//String predecessor = "1234";



/*	Scanner scan1 = new Scanner(System.in);
System.out.println("Enter 1 if you want to communicate to someone (i.e. node already exist and email id od addressee is known) or \nEnter 2 for ADVERTISEMENT (i.e. to populate its entry in indextable of responsible node when it newly joins the overlay)\n");
int tag = scan1.nextInt();
scan1.close();  */


/*
* 		Map<String, String> index = new LinkedHashMap<String, String>();
		index.put("cdef", "2467");
		index.put("12fe", "379a");
		index.put("f23c", "969a"); */

