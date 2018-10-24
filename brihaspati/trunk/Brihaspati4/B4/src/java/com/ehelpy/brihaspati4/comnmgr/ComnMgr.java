package com.ehelpy.brihaspati4.comnmgr;
// 19 May 2017 1240
/* This class contains:
	1. Method getSimpleQuery() which returns the "simple"  queryID from FIFO heap.
		"Simple" query - It is a type of query which any node generates when trying to 
		find another node in the network.
	2. Method findSuccQuery() which returns the "find Successor"  queryID from FIFO heap.
		"find Successor" query - It is a type of query which a bootstrap node generates when helping 
		another node trying to enter the network.
	3. Method getAdvtQuery() which returns the "Advertisement"  queryID from FIFO heap.
		"Advertisement" query - It is a type of query which any node generates when trying to 
		store its indexing information in its root node.
*/
import java.util.LinkedList;
import java.util.Queue;

public class ComnMgr {
	String Query;

	// This method returns simple query in FIFO to QueryRouting class
	public String getSimpleQuery() {		
		// Change entries in col ="13" in routing table xml file to see cahnges
		String[] simpleQ = { "30123456789ABADEF0123456789ABCDEF0123456", "b", "3", "f", "0" };// Array to store simple queries
		Queue<String> fifo = new LinkedList<String>();
		for (int i = 0; i < simpleQ.length; i++) {
			fifo.add(new String(simpleQ[i]));
		}
		Query = fifo.remove();// Removes the query from FIFO heap
		return Query;
	}

	// This method returns Successor query in FIFO to QueryRouting class
	public String findSuccQuery() {		
		String[] succQ = { "30123456789ABCDEF0123456789ABCFEF0123456", "5", "4", "f", "1" };// Array to store
																							// simple queries
		Queue<String> fifo = new LinkedList<String>();
		for (int i = 0; i < succQ.length; i++) {
			fifo.add(new String(succQ[i]));
		}
		Query = fifo.remove();// Removes the query from FIFO heap
		return Query;
	}

	// This method returns advertisement query in FIFO to QueryRouting class
	public String getAdvtQuery() {		
		String[] ad = { "30126456789ABCDEF0123456789ABCDEF0123452", "4", "3", "2", "1" };// Array to store
																							// advertisement queries
		Queue<String> fifo = new LinkedList<String>();
		for (int i = 0; i < ad.length; i++) {
			fifo.add(new String(ad[i]));
		}
		Query = fifo.remove();// Removes the query from FIFO heap
		return Query;
	}
}



