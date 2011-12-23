package org.bss.brihaspatisync.monitor.graphlayout.graphelements;
/**
 *  Locality.java
 *             
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 */


import  org.bss.brihaspatisync.monitor.graphlayout.Node;
import  org.bss.brihaspatisync.monitor.graphlayout.Edge;
import  org.bss.brihaspatisync.monitor.graphlayout.TGException;

import  java.util.Vector;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *                
 */


public class Locality extends GraphEltSet {

    protected GraphEltSet completeEltSet;

  // ............

  /** Constructor with GraphEltSet <tt>ges</tt>.
    */
    public Locality(GraphEltSet ges) {
        super();
        completeEltSet = ges;
    }

    public GraphEltSet getCompleteEltSet() {
        return completeEltSet;
    }

    public synchronized void addNode( Node n ) throws TGException {
        if (!contains(n)) {
            super.addNode(n);
            //If a new Node is created, and then added to Locality, then add the new edge
            //to completeEltSet as well.
            if (!completeEltSet.contains(n)) completeEltSet.addNode(n);
        }
    }

    public void addEdge( Edge e ) {
        if(!contains(e)) {
            edges.addElement(e);
            //If a new Edge is created, and then added to Locality, then add the new edge
            //to completeEltSet as well.
            if (!completeEltSet.contains(e)) completeEltSet.addEdge(e);
        }
    }

    public synchronized void addNodeWithEdges( Node n ) throws TGException {
        addNode(n);
        for (int i = 0 ; i < n.edgeCount(); i++) {
            Edge e=n.edgeAt(i);
            if(contains(e.getOtherEndpt(n))) addEdge(e);
        }

    }
    
    public synchronized void addAll() throws TGException {
        synchronized (completeEltSet) {
            for (int i = 0 ; i<completeEltSet.nodeCount(); i++) {
                addNode(completeEltSet.nodeAt(i));            
            }
            for (int i = 0 ; i<completeEltSet.edgeCount(); i++) {
                addEdge(completeEltSet.edgeAt(i));            
            }
        }
    }

    public Edge findEdge( Node from, Node to ) {
        Edge foundEdge=super.findEdge(from,to);
        if (foundEdge!=null && edges.contains(foundEdge)) return foundEdge;
        else return null;
    }

    public boolean deleteEdge( Edge e ) {
        if (e == null) return false;
        else {
            removeEdge(e);
            return completeEltSet.deleteEdge(e);
        }
    }

    public synchronized void deleteEdges( Vector edgesToDelete ) {
        removeEdges(edgesToDelete);
        completeEltSet.deleteEdges(edgesToDelete);
    }

    public boolean removeEdge( Edge e ) {
        if (e == null) return false;
            else {
                if(edges.removeElement(e)) {
                    return true;
                }
            return false;
        }
    }

    public synchronized void removeEdges( Vector edgesToRemove ) {
        for (int i=0;i<edgesToRemove.size();i++) {
            removeEdge((Edge) edgesToRemove.elementAt(i));
        }
    }

    public boolean deleteNode( Node node ) {
        if ( node == null ) return false;
        else {
            removeNode(node);
            return completeEltSet.deleteNode(node);
        }
    }

    public synchronized void deleteNodes( Vector nodesToDelete ) {
        removeNodes(nodesToDelete);
        completeEltSet.deleteNodes(nodesToDelete);
    }

    public boolean removeNode( Node node ) {
          if (node == null) return false;
          if (!nodes.removeElement(node)) return false;
		  
		  String id = node.getID();
          if ( id != null ) nodeIDRegistry.remove(id); // remove from registry
	
          for (int i = 0 ; i < node.edgeCount(); i++) {
             removeEdge(node.edgeAt(i));
         }

         return true;
    }

    public synchronized void removeNodes( Vector nodesToRemove ) {
        for (int i=0;i<nodesToRemove.size();i++) {
            removeNode((Node) nodesToRemove.elementAt(i));
        }
    }

    public synchronized void removeAll() {
        super.clearAll();        
    }

    public synchronized void clearAll() {
        removeAll();
        completeEltSet.clearAll();
    }

} // end 
