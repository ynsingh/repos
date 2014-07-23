package org.bss.brihaspatisync.gui;

/**
 * VideoSlider.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2013 ETRG, IIT Kanpur.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import org.bss.brihaspatisync.util.RuntimeDataObject;

/**
 * @author <a href="mailto: arvindjss17@gmail.com" > Arvind Pal </a>
 * @author <a href="mailto: pradeepmca30@gmail.com" > Pradeep Kumar Pal</a>
 */
public class VideoSlider extends JPanel implements ChangeListener {
	private int FPS_MIN = 0;
    	private int FPS_MAX = 100;
    	private int FPS_INIT = 20;    //initial frames per second
	private RuntimeDataObject runtime_object=RuntimeDataObject.getController();	

    	public VideoSlider() {
        	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	        //Create the label.
        	JLabel sliderLabel = new JLabel("Frames Per Second", JLabel.CENTER);
	        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        	//Create the slider.
	        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
        	framesPerSecond.addChangeListener(this);

	        //Turn on labels at major tick marks.

        	framesPerSecond.setMajorTickSpacing(20);
	        //framesPerSecond.setMinorTickSpacing(1);
        	//framesPerSecond.setPaintTicks(true);
	        framesPerSecond.setPaintLabels(true);
        	framesPerSecond.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
	        //Font font = new Font("Serif", Font.ITALIC, 5);
        	//framesPerSecond.setFont(font);

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

