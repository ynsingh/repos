package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  TGUserInterface.java
 *            
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import  org.bss.brihaspatisync.monitor.graphlayout.*;
import  java.awt.*;
import  java.awt.event.*;
import  java.util.Vector;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *            
 */

public abstract class TGUserInterface {

    private TGUserInterface parentUI;

    public abstract void activate();

    /** Each user interface is responsible for properly setting this variable. */
    boolean active; 

    public boolean isActive() {
        return active; 
    }

    public void activate( TGUserInterface parent ) {
        parentUI = parent;
        parentUI.deactivate();
        activate();
    }

    public void deactivate() {
        if (parentUI!=null) parentUI.activate();
        parentUI = null;
    }

} // end com.touchgraph.graphlayout.interaction.TGUserInterface
