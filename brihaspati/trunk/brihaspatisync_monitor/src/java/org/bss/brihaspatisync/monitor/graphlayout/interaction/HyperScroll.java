package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  HyperScroll.java
 *    
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */


import  org.bss.brihaspatisync.monitor.graphlayout.*;
 
import  java.awt.event.*;
import java.awt.*;
//import  javax.swing.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *      
 *  */


public class HyperScroll implements GraphListener {

    private Scrollbar hyperSB;
    private TGPanel tgPanel;
    HyperLens hyperLens;
    double inverseArray[]=new double[200]; //Helps calculate the inverse of the Hyperbolic function
    double width; //Initially was intended to change the function of the lens depending on screen size,
                  //but now functions as a constant.

    public HyperScroll(TGPanel tgp) {    
        tgPanel=tgp;
        hyperSB = new Scrollbar(Scrollbar.HORIZONTAL, 100, 8, 0, 108);
        hyperSB.addAdjustmentListener(new hyperAdjustmentListener());
        
        hyperLens = new HyperLens();
        width= 2000;//tgPanel.getSize().width/2;
        updateInverseArray();
        
        tgPanel.addGraphListener(this);
    }
    
    public Scrollbar getHyperSB() { return hyperSB; }

    public HyperLens getLens() { return hyperLens; }
    
    public void graphMoved() {} //From GraphListener interface
    public void graphReset() { hyperSB.setValue(0); } //From GraphListener interface
    
    private class hyperAdjustmentListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            updateInverseArray();
            tgPanel.repaintAfterMove();
        }
    }
    
    double rawHyperDist (double dist) {  //The hyperbolic transform
        if(hyperSB.getValue()==0) return dist;
        double hyperV=hyperSB.getValue();
        return Math.log(dist/(Math.pow(1.5,(70-hyperV)/40)*80) +1);
        /*
        double hyperD = Math.sqrt(dist+(10.1-Math.sqrt(hyperV)))-Math.sqrt(10.1-Math.sqrt(hyperV));
        */
        
    }    
    
    double hyperDist (double dist) { 
        
        double hyperV=hyperSB.getValue();
        //Points that are 250 away from the center stay fixed.
        double hyperD= rawHyperDist(dist)/rawHyperDist(250)*250;
        double fade=hyperV;
        double fadeAdjust=100;
        hyperD=hyperD*fade/fadeAdjust+dist*(fadeAdjust-fade)/fadeAdjust;
        return hyperD;

    }    
    
    void updateInverseArray(){
        double x;
        for(int i=0;i<200;i++) { 
            x=width*i/200; //Points within a radius of 'width' will have exact inverses.
            inverseArray[i]=hyperDist(x);
        }    
    };
    
    int findInd(int min, int max, double dist) {
        int mid=(min+max)/2;
        if (inverseArray[mid]<dist)
            if (max-mid==1) return max;
            else return findInd(mid,max,dist);
        else
            if (mid-min==1) return mid;
            else return findInd(min,mid,dist);
    }
    
    double invHyperDist (double dist) { //The inverse of hyperDist
        
        if (dist==0) return 0;
        int i;
        if (inverseArray[199]<dist) i=199;
        else i=findInd(0,199,dist);        
        double x2=inverseArray[i];
        double x1=inverseArray[i-1];
        double j= (dist-x1)/(x2-x1);
        return(((double) i+j-1)/200.0*width);
    }


     class HyperLens extends TGAbstractLens {
        protected void applyLens(TGPoint2D p) {
            double dist=Math.sqrt(p.x*p.x+p.y*p.y);
            if(dist>0) {
                p.x=p.x/dist*hyperDist(dist);
                p.y=p.y/dist*hyperDist(dist);
            }
            else { p.x =0; p.y=0;}
        }
        
        protected void undoLens(TGPoint2D p) {
            double dist=Math.sqrt(p.x*p.x+p.y*p.y);
            if(dist>0) {
                p.x=p.x/dist*invHyperDist(dist);
                p.y=p.y/dist*invHyperDist(dist);
            }
            else { p.x =0; p.y=0;}
        }
    }


} // end 
