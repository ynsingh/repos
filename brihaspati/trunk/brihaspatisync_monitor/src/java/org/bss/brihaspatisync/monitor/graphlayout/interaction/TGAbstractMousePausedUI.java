package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 * TGAbstractMousePausedUI.java
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

public abstract class TGAbstractMousePausedUI extends TGUserInterface {

     private AMPUIMouseMotionListener mml;
     private AMPUIMouseListener ml;
     protected TGPanel tgPanel;
     Point mousePos=null;
     PauseThread pauseThread=null;

  // ............

   /** Constructor with TGPanel <tt>tgp</tt>.
     */
     public TGAbstractMousePausedUI( TGPanel tgp ) { // Instantiate this way to keep listening
                                                     // for clicks until deactivate is called
          tgPanel=tgp;
          ml = new AMPUIMouseListener();
          mml = new AMPUIMouseMotionListener();
      }

     public final void activate() {
        preActivate();
          tgPanel.addMouseMotionListener(mml);
          tgPanel.addMouseListener(ml);
     }

    public final void deactivate() {
        tgPanel.removeMouseMotionListener(mml);
        tgPanel.removeMouseListener(ml);
        postDeactivate();
        super.deactivate(); //To activate parentUI from TGUserInterface
    }

    public void preActivate() {}
    public void postDeactivate() {}
    public abstract void mousePaused(MouseEvent e);
    public abstract void mouseMoved(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);

    class PauseThread extends Thread{
        boolean resetSleep;
        boolean cancelled;
           PauseThread() { cancelled = false; start(); }

           void reset() { resetSleep = true; cancelled = false; }
           void cancel() { cancelled = true; }

           public void run() {
               try {
                   do {  resetSleep=false; sleep(250); } while (resetSleep);
                if (!cancelled) {
                    MouseEvent pausedEvent =
                        new MouseEvent(tgPanel,MouseEvent.MOUSE_ENTERED, 0,0, mousePos.x,mousePos.y,0,false);
                    mousePaused(pausedEvent);
                }
               }
               catch (Exception e) {e.printStackTrace();}
           }
    }

    public void resetPause() {
        if (pauseThread!=null && pauseThread.isAlive()) pauseThread.reset();
        else pauseThread = new PauseThread();
    }

    public void cancelPause() {
        if (pauseThread!=null && pauseThread.isAlive()) pauseThread.cancel();
    }

    private class AMPUIMouseMotionListener implements MouseMotionListener {
        public void mouseMoved(MouseEvent e) {
              mousePos=e.getPoint();
              resetPause();
              TGAbstractMousePausedUI.this.mouseMoved(e);
        }

        public void mouseDragged(MouseEvent e) {
              mousePos=e.getPoint();
              resetPause();
              TGAbstractMousePausedUI.this.mouseDragged(e);
        }
    }

    private class AMPUIMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
              cancelPause();
        }
        public void mouseReleased(MouseEvent e) {
              cancelPause();
        }
        public void mouseExited(MouseEvent e) {
              //cancelPause();
        }
    }

} // end 
