package org.bss.brihaspatisync.tools.presentation;

/**
 * PresentationViewPanel.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import javax.swing.JPanel;  
import javax.swing.JSlider;  
import javax.swing.event.ChangeEvent;  
import javax.swing.event.ChangeListener;  
 
/**
  * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>Created on feb2011
  * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav</a>Modified on feb2011
  */
      
public class JsliderListener {  
	
	private int widthint=0;
	private int heightint=0;
	
	private JPanel panel=null;	
	
	private static JsliderListener pptPanel =null;

        public static JsliderListener getController(){
                if (pptPanel==null){
                        pptPanel =new JsliderListener();
                }
                return pptPanel;
        }
		

	public JPanel createGUI(){  
     		JPanel panel = new JPanel();  
        	JSlider width = new JSlider(JSlider.VERTICAL, 50, 150, 80);  
          	width.addChangeListener(new ChangeListener() {  
            		public void stateChanged(ChangeEvent evt) {  
                		JSlider width = (JSlider)evt.getSource();  
          			if (!width.getValueIsAdjusting()) {  
					int value=width.getValue();
					if(widthint<value)
						PresentationViewPanel.getController().revalidateImgWidth(value);
					else
						PresentationViewPanel.getController().revalidateImgWidth(-(value));
					widthint=value;	
                		}  
            		}  
        	});  
		
		JSlider height = new JSlider(JSlider.VERTICAL, 50, 150, 80);
                height.addChangeListener(new ChangeListener() {        
                        public void stateChanged(ChangeEvent evt) {
                                JSlider height = (JSlider)evt.getSource();
                                if (!height.getValueIsAdjusting()) {                
					int value=height.getValue();
                                        if(heightint<value)
						PresentationViewPanel.getController().revalidateImgHeight(value);
					else 
						PresentationViewPanel.getController().revalidateImgHeight(-value);
					heightint=value;
                                }
                        }
                });
		
		panel.add(width);  
     		panel.add(height);  
		return panel;
	}  
}  
