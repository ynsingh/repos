package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  TGUIManager.java
 *    
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */


import  org.bss.brihaspatisync.monitor.graphlayout.*;

import  java.awt.*;
import  java.awt.event.*;
import  java.util.Vector;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *      
 *  */


public class TGUIManager {

    Vector userInterfaces;

  // ............

   /** Default constructor.
     */
    public TGUIManager() {
        userInterfaces = new Vector();
    }

    class NamedUI {
        TGUserInterface ui;
        String name;

        NamedUI( TGUserInterface ui, String n ) {
            this.ui = ui;
            name = n;
        }
    }

    public void addUI( TGUserInterface ui, String name ) {
        userInterfaces.addElement(new NamedUI(ui,name));
    }

    public void addUI( TGUserInterface ui ) {
        addUI(ui,null);
    }

    public void removeUI( String name ) {
        for (int i=0;i<userInterfaces.size();i++)
            if (((NamedUI) userInterfaces.elementAt(i)).name.equals(name)) userInterfaces.removeElementAt(i);

    }

    public void removeUI( TGUserInterface ui ) {
        for (int i=0;i<userInterfaces.size();i++)
            if (((NamedUI) userInterfaces.elementAt(i)).ui==ui) userInterfaces.removeElementAt(i);

    }

    public void activate( String name ) {
        for (int i=0;i<userInterfaces.size();i++) {
            NamedUI namedInterf = (NamedUI) userInterfaces.elementAt(i);
            TGUserInterface ui=namedInterf.ui;
            if (((NamedUI) userInterfaces.elementAt(i)).name.equals(name)) ui.activate();
            else ui.deactivate();
        }
    }

    public void activate( TGUserInterface ui ) {
        for (int i=0;i<userInterfaces.size();i++) {
            if (((NamedUI) userInterfaces.elementAt(i)).ui==ui) ui.activate();
            else ui.deactivate();
        }
    }

} // end 
