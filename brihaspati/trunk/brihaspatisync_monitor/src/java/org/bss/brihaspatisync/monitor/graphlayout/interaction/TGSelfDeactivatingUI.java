package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  TGSelfDeactivatingUI.java
 *   
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */


import  org.bss.brihaspatisync.monitor.graphlayout.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *      
 *  */


public abstract class TGSelfDeactivatingUI extends TGUserInterface {

    boolean selfDeactivate;

  // ............

    /** Default constructor.
      */
    public TGSelfDeactivatingUI() {
        selfDeactivate = true;
    }

    public void setSelfDeactivate( boolean sd ) {
        selfDeactivate = sd;
    }

} // end 
