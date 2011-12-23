package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  TGAbstractMouseMotionUI.java
 *          
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import  org.bss.brihaspatisync.monitor.graphlayout.*;

import  java.awt.*;
import  java.awt.event.*;


/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *          
 */

public abstract class TGAbstractMouseMotionUI extends TGUserInterface{
	TGPanel tgPanel;

	private AMMUIMouseMotionListener mml;

// ............

 /** Constructor with TGPanel <tt>tgp</tt>.
	 */
	public TGAbstractMouseMotionUI( TGPanel tgp ) {
			tgPanel=tgp;
			mml=new AMMUIMouseMotionListener();
	}

	 public final void activate() {
			tgPanel.addMouseMotionListener(mml);
	 }

	public final void deactivate() {
			tgPanel.removeMouseMotionListener(mml);
			super.deactivate(); //To activate parentUI from TGUserInterface
	}

	public abstract void mouseMoved(MouseEvent e);

	public abstract void mouseDragged(MouseEvent e);

	private class AMMUIMouseMotionListener extends MouseMotionAdapter {
			public void mouseMoved(MouseEvent e) {
						TGAbstractMouseMotionUI.this.mouseMoved(e);
			}

			public void mouseDragged(MouseEvent e) {
						TGAbstractMouseMotionUI.this.mouseMoved(e);
			}
	}
} // end 
