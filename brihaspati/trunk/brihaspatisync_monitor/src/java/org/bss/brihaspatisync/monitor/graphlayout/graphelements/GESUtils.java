package org.bss.brihaspatisync.monitor.graphlayout.graphelements;

/**
 *  GESUtils.java
 *  
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 **/


import  org.bss.brihaspatisync.monitor.graphlayout.Node;
import  org.bss.brihaspatisync.monitor.graphlayout.Edge;
import  org.bss.brihaspatisync.monitor.graphlayout.TGException;

import  java.util.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *    
 *  */


public class GESUtils {

   /** Returns a hashtable of Node-Integer pairs, where integers are the minimum
     * distance from the focusNode to any given Node, and the Nodes in the Hashtable
     * are all within radius distance from the focusNode.
     *
     * Nodes with edge degrees of more then maxAddEdgeCount are not added to the hashtable, and those
     * with edge degrees of more then maxExpandEdgeCount are added but not expanded.
     *
     * If unidirectional is set to true, then edges are only follewed in the forward direction.
     *
     */

    public static Hashtable calculateDistances(GraphEltSet ges, Node focusNode, int radius,
                                               int maxAddEdgeCount, int maxExpandEdgeCount,
                                               boolean unidirectional ) {
        Hashtable distHash = new Hashtable();
        distHash.put(focusNode,new Integer(0));

        TGNodeQueue nodeQ = new TGNodeQueue();
        nodeQ.push(focusNode);

        while (!nodeQ.isEmpty()) {
            Node n = nodeQ.pop();
            int currDist = ((Integer) distHash.get(n)).intValue();

            if (currDist>=radius) break;

            for (int i = 0 ; i < n.edgeCount(); i++) {
                Edge e=n.edgeAt(i);
                if(n!=n.edgeAt(i).getFrom() && unidirectional) continue;
                Node adjNode = e.getOtherEndpt(n);
                if(ges.contains(e) && !distHash.containsKey(adjNode) && adjNode.edgeCount()<=maxAddEdgeCount) {
                    if (adjNode.edgeCount()<=maxExpandEdgeCount) nodeQ.push(adjNode);
                    distHash.put(adjNode,new Integer(currDist+1));
                }
            }
        }
        return distHash;
    }

    public static Hashtable calculateDistances(GraphEltSet ges, Node focusNode, int radius ) {
        return calculateDistances(ges, focusNode, radius, 1000, 1000, false);
    }

   /** A temporary function that returns the largest connected subgraph in a GraphEltSet.
     */

//	 public static Collection getLargestConnectedSubgraph(GraphEltSet ges) {
		 public static Hashtable getLargestConnectedSubgraph(GraphEltSet ges) {
        int nodeCount = ges.nodeCount();
        if(nodeCount==0) return null;

        Vector subgraphVector = new Vector();
        for(int i=0; i<nodeCount; i++) {
            Node n = ges.nodeAt(i);
            boolean skipNode=false;
            for (int j = 0; j<subgraphVector.size();j++) {
                if(((Hashtable) subgraphVector.elementAt(j)).contains(n)) {
                    skipNode=true;
                }
            }
//					Collection subgraph = calculateDistances(ges,n,1000).keySet();
						Hashtable subgraph = calculateDistances(ges,n,1000);
            if(subgraph.size()>nodeCount/2) return subgraph; //We are done
            if (!skipNode) subgraphVector.addElement(subgraph);

        }

        int maxSize=0;
        int maxIndex=0;
        for (int j = 0; j<subgraphVector.size();j++) {
            if(((Hashtable) subgraphVector.elementAt(j)).size()>maxSize) {
                maxSize = ((Hashtable) subgraphVector.elementAt(j)).size();
                maxIndex = j;
            }
        }

        return (Hashtable) subgraphVector.elementAt(maxIndex);
    }

} // end 
