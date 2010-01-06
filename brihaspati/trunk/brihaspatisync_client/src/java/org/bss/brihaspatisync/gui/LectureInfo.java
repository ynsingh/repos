package org.bss.brihaspatisync.gui;

/**
 * LectureInfo.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG,IIT Kanpur.
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.util.Vector;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */


/**
 * This class is used to show the description of Lecture
 */
public class LectureInfo extends JFrame implements MouseListener
{
	/**Labels to show the description of fields of lectures*/

	private JLabel descLabel[]=null;

	/**Label for showing information*/

  	private JLabel infoLabel=null;

	/**Button for closing the window*/

  	private JLabel closeLabel=null;

	/**Frame for showing the information*/

  	private JFrame frame=null;

	/**main panel to hold all the panels*/

  	private JPanel mainPan=null;

	/**Panel to hold the label*/

  	private JPanel labelPan=null;

	/**Panel to hold the close Button*/

  	private JPanel buttonPan=null;

	/**Panel to hold the description label*/

	private JPanel descPanel=null;
       
	private String[] sessionInfo={"Lecture Id","Lecture Name","Lecture Info","Author Url","Phone No","Transmit Video","Transmit Audio","WhiteBoard-chat","Session Date","Session Time","Duration","Repeat","For Times" };
	
	/**
	 * Constructor of class
	 */
        protected LectureInfo(int p, Vector infoVector){
		ClassLoader clr= this.getClass().getClassLoader();
		descLabel=new JLabel[20];
             	p=p*13;                       
		for(int i=0;i<13;i++){
			if(i==10)
				descLabel[i]=new JLabel("<html><font color=Black>"+sessionInfo[i]+"</font><font color=Black><b>=</font><font color=blue><I>"+(String)infoVector.elementAt(p)+" Hour"+"</font></html>");
			else
    				descLabel[i]=new JLabel("<html><font color=Black>"+sessionInfo[i]+"</font><font color=Black><b>=</font><font color=blue><I>"+(String)infoVector.elementAt(p)+"</font></html>");                    
                        p++;
             	}

		descPanel=new JPanel();
		descPanel.setLayout(new GridLayout(0,2,5,0));
		descPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		for(int i=0;i<13;i++)
			descPanel.add(descLabel[i]);			
		infoLabel=new JLabel("<html><u><Font Color=Black>Information Of Lecture");
		closeLabel=new JLabel(new ImageIcon(clr.getResource("resources/images/close.jpg")));
       		closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		closeLabel.addMouseListener(this);
    		closeLabel.setName("closeLabel.Action");
    		closeLabel.addMouseListener(this);
		
		mainPan=new JPanel();
		mainPan.setLayout(new BorderLayout());
		
		labelPan=new JPanel();
		labelPan.setLayout(new FlowLayout(FlowLayout.CENTER));
		labelPan.setBackground(Color.LIGHT_GRAY);
		labelPan.setBorder(BorderFactory.createLineBorder(Color.black));

		buttonPan=new JPanel();
                buttonPan.setLayout(new FlowLayout(FlowLayout.CENTER));	             /**Setting the Layout of Panel*/
		buttonPan.setBorder(BorderFactory.createLineBorder(Color.black));    /**Set the border of Panel*/
		buttonPan.setBackground(Color.LIGHT_GRAY);
	
		labelPan.add(infoLabel);					/**Add label to this panel*/
		buttonPan.add(closeLabel);					/**Add close button to panel*/

		mainPan.add("North",labelPan);
		mainPan.add("South",buttonPan);
		mainPan.add("Center",descPanel);
	
		frame=new JFrame();
		frame.setTitle("Session Description");		/**Setting the title of the Frame*/
		frame.getContentPane().add(mainPan);		/**Adding panel to the frame*/
		frame.setSize(450,350);				/**Setting the size of the frame*/
		frame.setVisible(true);				/**Showing the frame*/
            	frame.setLocation(550,8);
	} 
	
	public void mouseClicked(MouseEvent e) {
		 if(e.getComponent().getName().equals("closeLabel.Action")){
		 	frame.dispose();
		 }
		 	
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
