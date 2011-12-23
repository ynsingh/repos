package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 * TGAbstractClickUI.java
 *   
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import  org.bss.brihaspatisync.monitor.graphlayout.*;
 
import  java.awt.*;
import  java.awt.event.*;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */

public abstract class TGAbstractClickUI extends TGSelfDeactivatingUI {

     private ACUIMouseListener ml;
     private TGPanel tgPanel;
     
     public TGAbstractClickUI() { // Instantiate this way if you want to finish after one click
         tgPanel=null;
         ml= null;
     }
     
     public TGAbstractClickUI(TGPanel tgp) { // Instantiate this way to keep listening for clicks until
                                             // deactivate is called
          tgPanel=tgp;
          ml = new ACUIMouseListener();
      }

     public final void activate() {
         if (tgPanel!=null && ml!=null) tgPanel.addMouseListener(ml);
     }
         
    public final void activate(MouseEvent e) {
        mouseClicked(e);
    }
        
    public final void deactivate() { 
        if (tgPanel!=null && ml!=null) tgPanel.removeMouseListener(ml);
        super.deactivate(); //To activate parentUI from TGUserInterface
    }
    
    public abstract void mouseClicked(MouseEvent e);
    
    private class ACUIMouseListener extends MouseAdapter {    
        public void mouseClicked(MouseEvent e) {
            TGAbstractClickUI.this.mouseClicked(e);
            if (selfDeactivate) deactivate();
        }
    }

} // end 
