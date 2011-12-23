package org.bss.brihaspatisync.monitor.graphlayout.graphelements;
/**
 *  TGNodeQueue.java
 *         
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import  org.bss.brihaspatisync.monitor.graphlayout.Node;
 
import  java.util.Vector;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *            
 */


public class TGNodeQueue {

    Vector queue;

    public TGNodeQueue() {
        queue=new Vector();
    }
    
    public void push( Node n ) {
        queue.addElement(n);
    }
    
    public Node pop() {
        Node n = (Node)queue.elementAt(0);
        queue.removeElementAt(0);
        return n;
    }
    
    public boolean isEmpty() {
        return queue.size() == 0;
    }
    
    public boolean contains( Node n ) {
        return queue.contains(n);
    }

} // end 
