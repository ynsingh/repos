package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 * ZoomScroll.java
 *   
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import  org.bss.brihaspatisync.monitor.graphlayout.*;

import  java.awt.event.*;
import java.awt.*;


/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */

public class ZoomScroll implements GraphListener {

    protected ZoomLens zoomLens;
    private Scrollbar zoomSB;
    private TGPanel tgPanel;

  // ............

   /** Constructor with TGPanel <tt>tgp</tt>.
     */
    public ZoomScroll( TGPanel tgp ) {
        tgPanel=tgp;
        zoomSB = new Scrollbar(Scrollbar.HORIZONTAL, -4, 7, -31, 19);
        zoomSB.addAdjustmentListener(new zoomAdjustmentListener());
        zoomLens=new ZoomLens();
        tgPanel.addGraphListener(this);
    }

    public Scrollbar getZoomSB() { return zoomSB; }

    public ZoomLens getLens() { return zoomLens; }

    public void graphMoved() {} //From GraphListener interface
    public void graphReset() { zoomSB.setValue(-10); } //From GraphListener interface

    public int getZoomValue() {
        double orientedValue = zoomSB.getValue()-zoomSB.getMinimum();
        double range = zoomSB.getMaximum()-zoomSB.getMinimum()-zoomSB.getVisibleAmount();
        return (int) ((orientedValue/range)*200-100);
    }

    public void setZoomValue(int value) {
        double range = zoomSB.getMaximum()-zoomSB.getMinimum()-zoomSB.getVisibleAmount();
        zoomSB.setValue((int) ((value+100)/200.0 * range+0.5)+zoomSB.getMinimum());
    }
    
    private class zoomAdjustmentListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
        tgPanel.repaintAfterMove();
        }
    }

    class ZoomLens extends TGAbstractLens {
        protected void applyLens(TGPoint2D p) {
            p.x=p.x*Math.pow(2,zoomSB.getValue()/10.0);
            p.y=p.y*Math.pow(2,zoomSB.getValue()/10.0);

        }

        protected void undoLens(TGPoint2D p) {
            p.x=p.x/Math.pow(2,zoomSB.getValue()/10.0);
            p.y=p.y/Math.pow(2,zoomSB.getValue()/10.0);
        }
    }

} // end 
