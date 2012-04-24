/* Original source which is modified resulting in this file, is copyrighted (c)
 * 2001-2002 by Alexander Shapiro in made available under the license as
 * reproduced below.
 *
 * TouchGraph LLC. Apache-Style Software License
 *
 *
 * Copyright (c) 2001-2002 Alexander Shapiro. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        TouchGraph LLC (http://www.touchgraph.com/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "TouchGraph" or "TouchGraph LLC" must not be used to endorse
 *    or promote products derived from this software without prior written
 *    permission.  For written permission, please contact
 *    alex@touchgraph.com
 *
 * 5. Products derived from this software may not be called "TouchGraph",
 *    nor may "TouchGraph" appear in their name, without prior written
 *    permission of alex@touchgraph.com.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL TOUCHGRAPH OR ITS CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 * 
 * The changes done in the original source to suit our requirement are released
 * under the license as given in LICENSE file distributed with this source.
 * All such changes are Copyrighted (c) 2011-2012 ETRG, IIT Kanpur.
 */

package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 * RotateScroll.java
 */

import  org.bss.brihaspatisync.monitor.graphlayout.*;

import  java.awt.event.*;
import java.awt.*;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */

public class RotateScroll implements GraphListener {

    RotateLens rotateLens;
    double rotateAngle;
    RotateDragUI rotateDragUI;
    private DScrollBar rotateSB;
    boolean adjustmentIsInternal;
    private TGPanel tgPanel;

  // ............

    /** Constructor with TGPanel <tt>tgp</tt>.
      */
    public RotateScroll(TGPanel tgp) {
        tgPanel=tgp;
        rotateAngle=0;
        rotateLens=new RotateLens();
         rotateDragUI = new RotateDragUI();
         rotateSB = new DScrollBar(Scrollbar.HORIZONTAL, 0, 80, -314, 318);
         rotateSB.addAdjustmentListener(new rotateAdjustmentListener());
         adjustmentIsInternal=false;
         tgPanel.addGraphListener(this);
    }

    public RotateLens getLens() { return rotateLens; }

    public Scrollbar getRotateSB() { return rotateSB; }

    public RotateDragUI getRotateDragUI() { return rotateDragUI; }

    public int getRotationAngle() {
        double orientedValue = rotateSB.getValue()-rotateSB.getMinimum();
        double range = rotateSB.getMaximum()-rotateSB.getMinimum()-rotateSB.getVisibleAmount();
        return (int) ((orientedValue/range)*359);
    }

    public void setRotationAngle(int angle) {
        double range = rotateSB.getMaximum()-rotateSB.getMinimum()-rotateSB.getVisibleAmount();
        rotateSB.setValue((int) (angle/359.0 * range+0.5)+rotateSB.getMinimum());
    }

    public void graphMoved() {} //From GraphListener interface

    public void graphReset() { rotateAngle=0; rotateSB.setValue(0); } //From GraphListener interface

    private class rotateAdjustmentListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
            if(!adjustmentIsInternal) {
                rotateAngle = rotateSB.getDValue()/100.0;
                tgPanel.repaintAfterMove();
            }
        }
    }

    class DScrollBar extends Scrollbar {
        private double doubleValue;

        DScrollBar(int orient, int val, int vis, int min, int max){
            super(orient, val, vis, min, max);
            doubleValue=val;
        }
        public void setValue(int v) { doubleValue = v; super.setValue(v); }
        public void setIValue(int v) { super.setValue(v); }
        public void setDValue(double v) {
            doubleValue = Math.max(getMinimum(),Math.min(getMaximum(),v));
            setIValue((int) v);
        }
        public double getDValue() { return doubleValue;}
    }


    double computeAngle( double x, double y ) {
        double angle=Math.atan(y/x);

        if (x==0)  //There is probably a better way of hangling boundary conditions, but whatever
            if(y>0) angle=Math.PI/2;
            else angle=-Math.PI/2;

        if (x<0) angle+=Math.PI;

        return angle;
    }

    class RotateLens extends TGAbstractLens {
        protected void applyLens(TGPoint2D p) {
            double currentAngle=computeAngle(p.x,p.y);
            double dist=Math.sqrt((p.x*p.x)+(p.y*p.y));

            p.x=dist*Math.cos(currentAngle+rotateAngle);
            p.y=dist*Math.sin(currentAngle+rotateAngle);
        }

        protected void undoLens(TGPoint2D p) {
            double currentAngle=computeAngle(p.x,p.y);
            double dist=Math.sqrt((p.x*p.x)+(p.y*p.y));

            p.x=dist*Math.cos(currentAngle-rotateAngle);
            p.y=dist*Math.sin(currentAngle-rotateAngle);
        }
    }

    public void incrementRotateAngle(double inc) {
        rotateAngle+=inc;
        if (rotateAngle>Math.PI) rotateAngle-=Math.PI*2;
        if (rotateAngle<-Math.PI) rotateAngle+=Math.PI*2;
        adjustmentIsInternal=true;
            rotateSB.setDValue(rotateAngle*100);
        adjustmentIsInternal=false;
    }

    class RotateDragUI extends TGAbstractDragUI { // Will work best if rotate lens is top lens

        RotateDragUI() {
            super(RotateScroll.this.tgPanel);
        }

        double lastAngle;

        double getMouseAngle(double x, double y) {
            return computeAngle(x-this.tgPanel.getDrawCenter().x,
                                y-this.tgPanel.getDrawCenter().y);
        }

        public void preActivate() {}
        public void preDeactivate() {}

        public void mousePressed( MouseEvent e ) {
            lastAngle = getMouseAngle(e.getX(), e.getY());
        }

        public void mouseReleased( MouseEvent e ) {}

        public void mouseDragged( MouseEvent e ) {
            double currentAngle = getMouseAngle(e.getX(), e.getY());
            incrementRotateAngle(currentAngle-lastAngle);
            lastAngle=currentAngle;
            this.tgPanel.repaintAfterMove();
        }
    }

} // end
