package com.ehelpy.brihaspati4.indexmanager;

import java.net.Inet4Address;
//import java.net.UnknownHostException;
//import java.net.Inet4Address;
import java.util.LinkedHashMap;
import java.util.Map;
//import java.util.Scanner;

public class Routing extends IndexManagement {
	
	public String get_node_id(String query)
	{
		String my_node_id = "aaaa";											// put query in routing algo to reach to Vikhyat // this is vikhyat's node id
																			// then search query in vikhyat index and return corresponding node_id;
		Map<String, String> index1 = new LinkedHashMap<String, String>();
		index1.put("aaaa", "4321");
		if(my_node_id.equals(query))										// Here vikhyat is comparing whether the received query matches its node id, if true
																			//it will start search in its index table and return node id corresponding to query 
			System.out.println("query reached to responsible root node, started searching corresponding node id in its index");
		return (index1.get(query));		
	}
	
	
	public String getmynodeid()
	{
		String my_node_id = "cdef2";		// actually here there should be a code which will extract node_id from the routing table. i.e. first entry in the table		
		return (my_node_id);		
	}
	
	public String[] getmypredecessor()
	{
		String[] my_predecessor = {"cdef0","cdeef","cdeee","cdeed","cdeec"};		// actually here there should be a code which will extract predecessor from the routing table.		
		return (my_predecessor);		
	}
	
	public String[] getmysuccessors()
	{
		String[] my_successor_list = {"cdefa","cdefb","cdefc","cdefd","cdefe"};		// actually here there should be a code which will extract successor from the routing table.		
		return (my_successor_list);		
	}
	
	public String get_ip_address(String receive_node_id, String receive_ip_address) throws Exception
	{
		//String apte_ip_address = Inet4Address.getLocalHost().getHostAddress();
		
		String my_node_id = "4321";											// put query in routing algo to reach to Vikhyat 
																			// then search query in vikhyat index and return corresponding node_id;
		if(my_node_id.equals(receive_node_id))										// Here vikhyat is comparing whether the received query matches its node id, if true
			System.out.println("query finally reached its destination");																	//it will start search in its index table and return node id corresponding to query 
		return (Inet4Address.getLocalHost().getHostAddress());		
	}
	
	public String get_ip_address(String receive_node_id) throws Exception			/// here the routing table is searchng in routing tabke for ip address corresponding to this node id and will send its ip address
	{
		return (Inet4Address.getLocalHost().getHostAddress());		
	}
	
	public String Get_newly_joined_node()
	{
		String newly_joined_node = "cdef1";
		return newly_joined_node;
	}

}