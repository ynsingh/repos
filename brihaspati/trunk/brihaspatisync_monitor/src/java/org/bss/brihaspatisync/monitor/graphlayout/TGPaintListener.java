package org.bss.brihaspatisync.monitor.graphlayout;

/**
 *TGPaintListener.java
 *See LICENCE file for usage and redistribution terms
 *Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import  java.awt.*;
import  java.util.EventListener;

/**
 *@author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *@date 05/12/2011
 *
 */

public interface TGPaintListener extends EventListener{

    void paintFirst(Graphics g);
    void paintAfterEdges(Graphics g);
    void paintLast(Graphics g);

} // end 
