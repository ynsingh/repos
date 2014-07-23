package org.bss.brihaspatisync.tools.chat;

/**
 * BlinkLabel.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur
 **/

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep </a>
 */
 
public class BlinkLabel extends JLabel {
  	private static final int BLINKING_RATE = 1000; // in ms
	public boolean blinkingOn = false;
	public BlinkLabel(){}
  	public BlinkLabel(String text) {
    		super(text);
		Timer timer = new Timer( BLINKING_RATE , new TimerListener(this));
		timer.setInitialDelay(0);
		timer.start();
	}
  
	public void setBlinking(boolean flag) {
    		this.blinkingOn = flag;
  	}
  	public boolean getBlinking(boolean flag) {
    		return this.blinkingOn;
  	}
	
	private class TimerListener implements ActionListener {
		private BlinkLabel bl;
		private boolean isForeground = true;
    		public TimerListener(BlinkLabel bl) {
			this.bl = bl;
    		}
 
		public void actionPerformed(ActionEvent e) {
			if (bl.blinkingOn) {
        			if (isForeground) {
          				bl.setForeground(Color.blue);
        			} else {
          				bl.setForeground(Color.lightGray);
        			}
        			isForeground = !isForeground;
      			} else {
       				if (isForeground) {
          				isForeground = false;
          				bl.setForeground(Color.black);
        			}else
					bl.setForeground(Color.black);
				
      			}
    		}
    	}
}

