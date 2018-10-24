package com.ehelpy.brihaspati4.router;
//23 May 2017 1240

import com.ehelpy.brihaspati4.comnmgr.*;
import com.ehelpy.brihaspati4.routingtable.*;
/*This class contains the main method to test the P2pRouter.
 1. It gets queryID from Comn Manager and 
 2. Calls Router method in the P2pRouter class and passes routing table object
  and queryID as parameters.
 3. Gets destinationID where the queryID must be forwarded next.
 */
public class RouterTestMain {	
	public static void main(String[] args) {
		ComnMgr CM = new ComnMgr();		
		String queryID = CM.getSimpleQuery();// CM.findSuccQuery(); CM.getAdvtQuery();
		P2PRouter PR= new P2PRouter();
		RoutingTable RT = new RoutingTable();
		RT.getRoutingTable();// Method invoked to fill P[],S[],M[] in RoutingTable Class
		RT.getPredAndSucc();	// Method invoked to fill Pred[4],Succ[4]  in RoutingTable Class
		String destinationID= PR.Router(new RoutingTable() , queryID);
		if(destinationID.equals(XmlParsing.getSelfID())){
			
			System.out.println("selfID is the root node for query");
			System.out.println("Forwarding the query to Index Manager");
			//IndexManager.resolveQuery(destinationID);
		}
		else
		System.out.println(" The destinationID is"+ destinationID);		
	}
}
