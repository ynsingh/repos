package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  TGAbstractDragUI.java
 *    
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */


import  org.bss.brihaspatisync.monitor.graphlayout.*;

import  java.awt.*;
import  java.awt.event.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *      
 *  */


public abstract class TGAbstractDragUI extends TGSelfDeactivatingUI {

    public TGPanel tgPanel;

    private ADUIMouseListener ml;
    private ADUIMouseMotionListener mml;
    public boolean mouseWasDragged; //To differentiate between mouse pressed+dragged, and mouseClicked

  // ............

   /** Constructor with TGPanel <tt>tgp</tt>.
     */
    public TGAbstractDragUI(TGPanel tgp) {
        tgPanel=tgp;
        ml =new ADUIMouseListener();
        mml=new ADUIMouseMotionListener();
    }

     public final void activate() {
        preActivate();
        tgPanel.addMouseListener(ml);
        tgPanel.addMouseMotionListener(mml);
			 mouseWasDragged=false;
     }

    public final void activate(MouseEvent e) {
        activate();
        mousePressed(e);
    }

    public final void deactivate() {
        preDeactivate();
        tgPanel.removeMouseListener(ml);
        tgPanel.removeMouseMotionListener(mml);
        super.deactivate(); //To activate parentUI from TGUserInterface
    }

    public abstract void preActivate();
    public abstract void preDeactivate();

    public abstract void mousePressed( MouseEvent e );
    public abstract void mouseDragged( MouseEvent e );
    public abstract void mouseReleased( MouseEvent e );


    private class ADUIMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            TGAbstractDragUI.this.mousePressed(e);
        }

        public void mouseReleased(MouseEvent e) {
            TGAbstractDragUI.this.mouseReleased(e);
            if (selfDeactivate) deactivate();
        }
    }

    private class ADUIMouseMotionListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            mouseWasDragged=true;
              TGAbstractDragUI.this.mouseDragged(e);
        }
    }

} // end com.touchgraph.graphlayout.interaction.TGAbstractDragUI
