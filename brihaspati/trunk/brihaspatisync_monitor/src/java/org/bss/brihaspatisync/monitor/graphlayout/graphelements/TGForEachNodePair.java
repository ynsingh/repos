package org.bss.brihaspatisync.monitor.graphlayout.graphelements;
/**
 *  TGForEachNodePair.java
 *               
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */

import  org.bss.brihaspatisync.monitor.graphlayout.Node;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *                  
 *  */ 

public abstract class TGForEachNodePair {

    public void beforeInnerLoop( Node n1 ) {};

    public void afterInnerLoop( Node n1 ) {};

    public abstract void forEachNodePair( Node n1, Node n2 );

} // end com.touchgraph.graphlayout.graphelements.TGForEachNodePair
