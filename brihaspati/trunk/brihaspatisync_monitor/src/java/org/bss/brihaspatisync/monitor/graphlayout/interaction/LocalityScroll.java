package  org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  LocalityScroll.java
 *        
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import   org.bss.brihaspatisync.monitor.graphlayout.*;
import   org.bss.brihaspatisync.monitor.graphlayout.graphelements.*;

import  java.awt.event.*;
import java.awt.*;


/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *        
 */


public class LocalityScroll implements GraphListener {

    private Scrollbar localitySB;

    private TGPanel tgPanel;

    public LocalityScroll(TGPanel tgp) {
        tgPanel=tgp;
        localitySB = new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 0, 7);
        localitySB.setBlockIncrement(1);
        localitySB.setUnitIncrement(1);
        localitySB.addAdjustmentListener(new localityAdjustmentListener());
        tgPanel.addGraphListener(this);
    }

    public Scrollbar getLocalitySB() {
        return localitySB;
    }

    public int getLocalityRadius() {
        int locVal = localitySB.getValue();
        if(locVal>=6) return LocalityUtils.INFINITE_LOCALITY_RADIUS;
        else return locVal;
    }

    public void setLocalityRadius(int radius) {
        if (radius <= 0 ) 
            localitySB.setValue(0);
        else if (radius <= 5) //and > 0
            localitySB.setValue(radius);
        else // radius > 5
            localitySB.setValue(6);        
    }

    public void graphMoved() {} //From GraphListener interface
    public void graphReset() { localitySB.setValue(1); } //From GraphListener interface

    private class localityAdjustmentListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            Node select = tgPanel.getSelect();                        
            if (select!=null || getLocalityRadius() == LocalityUtils.INFINITE_LOCALITY_RADIUS)
                try {
                    tgPanel.setLocale(select, getLocalityRadius());
                }
                catch (TGException ex) {
                    System.out.println("Error setting locale");
                    ex.printStackTrace();
                }
        }
    }

} // end 
