package org.bss.brihaspatisync.monitor.gui;

/**
 * StatusLabel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */


import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Icon;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */

public class StatusLabel extends JPanel {
	
	private JLabel label=null;
	private JLabel label1=null;
	private static StatusLabel labe =null;
	
	public StatusLabel() {
		setLayout(new BorderLayout());
		setBackground(Color.blue);
		try {
			label = new JLabel("<html><Font size=3 color=white><b>Status: </b></font></html>");
			add(label,BorderLayout.WEST);
			label1 = new JLabel("");
			add(label1,BorderLayout.CENTER);
		}catch(Exception e){}
	}

	public static StatusLabel getController(){
		if(labe==null)
			labe=new StatusLabel();
		return labe;
	}

	public void setStatus(String str){
		label1.setText("<html><blink><Font size=3 color=white><b>"+str+"</b></font></blink></html>");
		label1.updateUI();
	}
}

