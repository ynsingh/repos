package com.ehelpy.brihaspati4.router;
//23 May 2017 1240
/* This class contains:
 	1. Method Router() that takes a Routing Table object and queryID and
	 returns the destination ID.
	2. Algorithm "resolveColumn()"- finds the range in which
	 the mismatched nibble lie in the first mismatched column.
	3. Algorithm "findSmallestNodeGreaterThanQuery()"- finds the smallest node 
	greater than the queryID in the columns ahead of the mismatched column towards LSB. 
 */
import java.math.BigInteger;
import java.util.Arrays;

import com.ehelpy.brihaspati4.routingtable.*;

public class P2pRouter  {
	XmlParsing XPP = new XmlParsing();
	static String selfID = XmlParsing.getSelfID();// Accessing static method in XmlParsing class	
	int nib_mismatch;

	// P2pRouter: This function takes a Routing Table object and queryID and
	// returns the destination ID.

	public String Router(RoutingTable RT , String queryID) {
		System.out.println("SelfID is"+ selfID);
		System.out.println("QueryId is"+ queryID);
		String destinationID = null;

		// Check if query exactly matches self
		if (selfID.equals(queryID)) {
			System.out.println(" Query matches selfID :  I am the root node");
			System.out.println("Query will be forwarded to Index Manager");
			destinationID = selfID;
			return destinationID;
		}

		// Finding first mismatch from MSB side
		for (int m = 0; m < 40; m++) {
			int s_nib = Character.getNumericValue(selfID.charAt(m));
			int q_nib = Character.getNumericValue(queryID.charAt(m));

			if (s_nib == q_nib) {
				// m++
				// System.out.println("Nibble match: at "+m+" Moving to next
				// nibble ");
			} else {
				nib_mismatch = m;
				System.out.println("Nibble mismatch at   " + (nib_mismatch));
				break;
			}
		}
		BigInteger selfIDInB = new BigInteger(selfID, 16);// Converting selfId
															// into BigInteger
															// type for
															// calculations
		BigInteger predIDInB = new BigInteger(RoutingTable.Pred[0], 16);
		BigInteger queryIDInB = new BigInteger(queryID, 16);
		// ================================================================================================================

		// Check best column from mismatch onwards towards LSB considering few
		// empty nodes
		for (int i = nib_mismatch; i < 40; i++) {
			// Check whether query lies between selfID and Pred[0]
			// This step to see whether queryID lies between Pred and selfId has
			// been
			// kept inside this for loop so that in case if queryID lies between
			// Pred and selfId
			// "i am the root" and we could use break statement to stop any
			// further calculations
			// For eg if Pred[0]= "30123456789ABCDEF0123456789ABCDEF0123451" and
			// selfID = "30123456789ABCDEF0123456789ABCDEF0123456" and
			// queryID = "30123456789ABCDEF0123456789ABCDEF0123452"
			// Look at LSB, clearly query lies between self and predecessor so i
			// don't want
			// to go ahead with nibble mismatch etc.
			int res1 = queryIDInB.compareTo(predIDInB);
			int res2 = queryIDInB.compareTo(selfIDInB);
			if ((res1 == 1) && (res2 == -1)) {
				// System.out.println(" The query lies between selfID and
				// predecessor: I am the root");
				// System.out.println("Query will be forwarded to Index
				// Manager");
				destinationID = selfID;
				return destinationID;
			}
			String fwdNode;
			int filled = 0;
			if (RoutingTable.P[i].equals(" ") && RoutingTable.S[i].equals(" ") && RoutingTable.S[i].equals(" ")) { // If the mismatched
																						// column is empty
				// Switch to Algo 2
				System.out.println("The column  " + i + "   does not have any node entries");

				// Finding first non-empty column towards LSB
				for (int f = i + 1; f < 40; f++) {
					if ((RoutingTable.P[f].equals(" ") && RoutingTable.S[f].equals(" ") && RoutingTable.S[f].equals(" ")) && f != 39) {
						System.out.println("The column  " + f + "  also  does not have any node entries");
					} else
						filled = f; // Stores the first column number having valid entries and is non empty
				}
				if (filled == 39) {
					System.out.println("Since column 39 is also empty I am the root");
					destinationID = selfID;
					return destinationID;					
				} else // If non empty column is found before j==39.
				{
					fwdNode = findSmallestNodeGreaterThanQuery(filled); // Find the smallest node in the first
																			//non empty col which is greater tha queryID																		
					destinationID = fwdNode;
					return destinationID;
				}
			} else // If the very first mismatched column is non-empty
			{
				String result = resolveColumn(i, queryID); // resolveColumn() returns a string depending on															
															// where the query nibble lies on the nibble circle of self nibble.															
				if (result == "S") { // qNib ---->(selfNib,S[j] ]
					System.out.println("The query lies between self nibble and successor  nibble ");
					System.out.println("The query will be fwd to the S[" + i + "]  " + RoutingTable.S[i]);
					destinationID = RoutingTable.S[i];
					return destinationID;
				} else if (result == "P") { // qNib ---->(M[j], P[j]]
					System.out.println("The query lies between middle nibble and pred nibble ");
					System.out.println("The query will be fwd to the P[" + i + "]  " + RoutingTable.P[i]);
					destinationID = RoutingTable.P[i];
					return destinationID;
				} else if (result == "M") { // qNib ---->(S[j], M[j])
					System.out.println("The query lies between successor  nibble and middle  nibble ");
					System.out.println("The query will be fwd to the M[" + i + "]  " + RoutingTable.M[i]);
					destinationID = RoutingTable.M[i];
					return destinationID;
				} else if (result == "SELF") { // qNib ---->(P[j], self]
					System.out.println("The query lies between pred  nibble and self  nibble ");
					System.out.println("Switching over to the distance Algorithm");
					
					//****************************************************************************************************
					if (RoutingTable.P[i+1].equals(" ") && RoutingTable.S[i+1].equals(" ") && RoutingTable.S[i+1].equals(" ")) { // If the mismatched
						// column is empty
							// Switch to Algo 2
						//System.out.println("The column  " + (i+1) + "   does not have any node entries");

						// Finding first non-empty column towards LSB
						for (int f = i + 1; f < 40; f++) {
							if ((RoutingTable.P[f].equals(" ") && RoutingTable.S[f].equals(" ") && RoutingTable.S[f].equals(" ")) && f != 39) {
								System.out.println("The column  " + f + "  also  does not have any node entries");
							} else
								filled = f; // Stores the first column number having valid entries and is non empty
						}
						if (filled == 39) {
							System.out.println("Since column 39 is also empty I am the root");
							destinationID = selfID;
							return destinationID;					
						} else // If non empty column is found before j==39.
						{
							fwdNode = findSmallestNodeGreaterThanQuery(filled); // Find the smallest node in the first
							//non empty col which is greater tha queryID																		
							destinationID = fwdNode;
							return destinationID;
							}
					}
					//*********************************************************************************************************
					else{
					
					String xx = findSmallestNodeGreaterThanQuery(i+1);
					destinationID = xx;
					return destinationID;
					}
				}

			}

		}  return destinationID;
	}
	// =========================================================================================================
	// This function does the following
	// 1. Take mismatched column number and queryId as parameter.
	// 2. finds in which of the four "ranges" the query nibble lies: P,S,M or
	// Self Range
	// 3. Returns the string result of the range in which it lies.

	static String resolveColumn(int j, String queryID) {
		String result = null;
		int self = Character.getNumericValue(selfID.charAt(j));
		int query = Character.getNumericValue(queryID.charAt(j));
		int SR[] = new int[4];
		int PR[] = new int[4];
		int MR[] = new int[4];
		int selfR[] = new int[4];
		int sResult = 0, pResult = 0, mResult = 0, selfResult = 0;
		for (int i = 0; i < 4; i++) {
			sResult = (self + (i + 1)) % 16;
			SR[i] = sResult;
			mResult = (self + 4 + (i + 1)) % 16;
			MR[i] = mResult;
			pResult = (self + 8 + (i + 1)) % 16;
			PR[i] = pResult;
			selfResult = (self + 12 + (i + 1)) % 16;
			selfR[i] = selfResult;
		}
		System.out.println(" Mismatched selfID nibble is "+selfID.charAt(j));
		System.out.println("Successor range SR for the mismatched nibble has ");
		for (int i = 0; i < 4; i++) {
			// String hex = Integer.toHexString(val);
			String SRange = Integer.toHexString(SR[i]);
			System.out.print(" " + SRange);
		}
		System.out.println("");
		System.out.print("Middle range MR for the mismatched nibble has  ");
		for (int i = 0; i < 4; i++) {
			String MRange = Integer.toHexString(MR[i]);
			System.out.print(" " + MRange);
		}
		System.out.println("");
		System.out.println("Predecessor Range  PR for the mismatched nibble has ");
		for (int i = 0; i < 4; i++) {
			String PRange = Integer.toHexString(PR[i]);
			System.out.print(" " + PRange);
		}
		System.out.println("");
		System.out.print("Self range selfR for the mismatched nibble has  ");
		for (int i = 0; i < 4; i++) {
			String selfRange = Integer.toHexString(selfR[i]);
			System.out.print(" " + selfRange);
		}
		// To see if query matches any of the four arrays
		System.out.println("");
		for (int i = 0; i < 4; i++) {
			if (query == SR[i]) {
				result = "S";
			}
			if (query == PR[i]) {
				result = "P";
			}
			if (query == MR[i]) {
				result = "M";
			}
			if (query == selfR[i]) {
				result = "SELF";
			}
		}
		return result;
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Function to find the least node which is greater than the queryID in first filled column 
	//ahead of the mismatched column.

	public  String findSmallestNodeGreaterThanQuery(int c) {
		String nodeID = null;
		BigInteger[] store = new BigInteger[3];		
		store[0] = new BigInteger(RoutingTable.P[c], 16);
		store[1] = new BigInteger(RoutingTable.S[c], 16);
		store[2] = new BigInteger(RoutingTable.M[c], 16);	
		Arrays.sort(store); // Sorting the store[] array in ascending order
							// This will give us the smallest node greater than
							// the query at store[0]		
		nodeID = store[0].toString(16); // Converts Biginteger to Hexadecimal
										// and assigns it to nodeID string
		return nodeID;
	}
}
