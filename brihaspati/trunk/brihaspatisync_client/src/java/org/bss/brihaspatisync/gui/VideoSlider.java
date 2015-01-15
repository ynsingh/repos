package org.bss.brihaspatisync.gui;

/**
 * VideoSlider.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2015, IIT Kanpur.
 */

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto: pradeepmca30@gmail.com" > Pradeep Kumar Pal</a>
 */
public class VideoSlider extends JPanel implements ChangeListener {
	private int FPS_MIN = 0;
    	private int FPS_MAX = 100;
    	private int FPS_INIT = 20;    
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();	

    	public VideoSlider() {
        	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        	JLabel sliderLabel = new JLabel("Frames Per Second", JLabel.CENTER);
	        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
        	framesPerSecond.addChangeListener(this);
        	framesPerSecond.setMajorTickSpacing(20);
	        framesPerSecond.setPaintLabels(true);
        	framesPerSecond.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        	add(framesPerSecond);
    	}

    	public void stateChanged(ChangeEvent e) {
        	JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
            		int fps = (int)source.getValue();
			fps =1030-(fps*10); 
			runtime_object.setTimeDelayFrame(fps);
        	}
    	}
}

