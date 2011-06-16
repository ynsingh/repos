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

	private String tools="";
	private JPanel panel=null;	
	private JSlider width=null;
	private JSlider height=null;

	private static JsliderListener pptPanel =null;

        private static JsliderListener getController(){
                if (pptPanel==null){
                        pptPanel =new JsliderListener();
                }
                return pptPanel;
        }
	
	public void setTools(String temp){
		tools=temp;
	}		

	public JPanel createGUI(){  
     		panel = new JPanel();  
        	width = new JSlider(JSlider.VERTICAL, 50, 150, 80);  
		width.enable(false);
          	width.addChangeListener(new ChangeListener() {  
            		public void stateChanged(ChangeEvent evt) {  
                		JSlider width = (JSlider)evt.getSource();  
          			if (!width.getValueIsAdjusting()) {  
					int value=width.getValue();
					if(widthint<value) {
						if(tools.equals("presentation"))
							PresentationViewPanel.getController().revalidateImgWidth(value);
						else if(tools.equals("Desktop_Sharing"))
							org.bss.brihaspatisync.gui.Desktop_Sharing.getController().revalidateImgWidth(value);		
					} else {
						if(tools.equals("presentation"))
							PresentationViewPanel.getController().revalidateImgWidth(-(value));
						else if(tools.equals("Desktop_Sharing"))
                                                        org.bss.brihaspatisync.gui.Desktop_Sharing.getController().revalidateImgWidth(-(value));

					}		
					widthint=value;	
				}  
            		}  
        	});  
		
		height = new JSlider(JSlider.VERTICAL, 50, 150, 80);
		height.enable(false);
                height.addChangeListener(new ChangeListener() {        
                        public void stateChanged(ChangeEvent evt) {
                                JSlider height = (JSlider)evt.getSource();
                                if (!height.getValueIsAdjusting()) {                
					int value=height.getValue();
                                        if(heightint<value) {
						if(tools.equals("presentation"))
							PresentationViewPanel.getController().revalidateImgHeight(value);
						else if(tools.equals("Desktop_Sharing"))
							org.bss.brihaspatisync.gui.Desktop_Sharing.getController().revalidateImgHeight(value);
							
					} else {
						if(tools.equals("presentation"))
							PresentationViewPanel.getController().revalidateImgHeight(-value);
						else if(tools.equals("Desktop_Sharing"))
                                                        org.bss.brihaspatisync.gui.Desktop_Sharing.getController().revalidateImgHeight(-(value));
					}
					heightint=value;
                                }
                        }
                });
		
		panel.add(width);  
     		panel.add(height);  
		return panel;
	} 	
	
	public void setEnable_Decable(boolean flag){
		width.enable(flag);
		height.enable(flag);
		panel.revalidate();
        } 
}  
