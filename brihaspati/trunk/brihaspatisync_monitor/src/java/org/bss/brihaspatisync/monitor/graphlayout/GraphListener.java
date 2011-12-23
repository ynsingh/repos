/**
 *   graphlayout.java
 *   See LICENCE file for usage and redistribution terms
 *   Copyright (c) 2011, ETRG, IIT Kanpur.
 **/

/**
 *@author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *@date 05/12/2011
 *
 **/


package org.bss.brihaspatisync.monitor.graphlayout;
import java.util.EventListener;
 
public interface GraphListener extends EventListener{

    void graphMoved();
    void graphReset();

} //end 
