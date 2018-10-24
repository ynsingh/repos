package com.ehelpy.brihaspati4.routingtable;
//23 May 2017 1240

import com.ehelpy.brihaspati4.routingtable.XmlParsing;

/* This class contains:
	1. Method getRoutingTable() that parses all entries from rTable.xml
		and stores it in 3 String Arrays P[],S[] and M[].
	2. Method getPredAndSucc() that parses 4 predecessors and 4 successors from
		PredAndSucc.xml and stores them in 2 String Arrays Pred[],Succ[].
		Pred[0] contains the nodeID of immediate neighbour on the ring.
	3. Method to extract the selfID from rTable.xml
	4. Method to update routing table in stabilisation cycles.
	5. Method to save routing table when logging off.
	6. Method to verify routing table when old user reenters the network.
*/
public class RoutingTable {	
	XmlParsing XPRT = new XmlParsing();
	public static String[] P = new String[40];// Array to store all Predecessors of routing table in one row
	public static String[] S = new String[40];// Array to store all Successors of routing table in one row
	public static String[] M = new String[40];// Array to store all Middles of routing table in one row
	public static String[] Pred = new String[4]; // Stores 4 predecessors
	public static String[] Succ = new String[4]; // Stores 4 successors
	
	public void getRoutingTable() {
		String[] rTable = XmlParsing.getRTableEntries();
		
		// Extracting P[40]
					for (int i = 0; i <40; i++) {
						int j =i*3;
						P[i] = rTable[j];
							}

					// Extracting S[40]
					for (int i = 0; i <40; i++) {
						int j =(i*3)+1;
						S[i] = rTable[j];
							}

					// Extracting M[40]
					for (int i = 0; i <40; i++) {
						int j =(i*3)+2;
						M[i] = rTable[j];
							}
	}
	
	public void getPredAndSucc(){
		String[] predSuccTable=XmlParsing.getPredAndSuccArray();	

		// Extracting Pred[4] from predSuccTable [8]
		for (int i = 0; i < 4; i++) {
			Pred[i] = predSuccTable[i];
		}
		System.out.println("Your immediate predecessor on the ring is :");
		System.out.println(Pred[0]);

		// Extracting Succ[4] from predSuccTable[8]
		for (int i = 0; i < 4; i++) {
			Succ[i] = predSuccTable[4 + i];
		}
	}

	public void getSelfID() {
		System.out.println("We are in getSelfID");
		XmlParsing.getSelfID();

	}

	public void saveRoutingTable() {
		// save the routing table when user is logging out
	}

	boolean verifyRoutingTable() {
		// ping and update entries when using saved routing table
		boolean result = false;
		return result;
	}

	void updateRoutingTable() {
		// part of stabilisation
		// To be done by Apte sir.
	}

}
