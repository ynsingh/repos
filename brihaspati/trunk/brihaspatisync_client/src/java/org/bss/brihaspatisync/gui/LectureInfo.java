package org.bss.brihaspatisync.gui;

/**
 * LectureInfo.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG,IIT Kanpur.
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
import org.bss.brihaspatisync.util.Language;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified for signalling 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
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
       
	private String[] sessionInfo={"Lecture Id","Course Name","Lecture Name","Lecture Info","Author Url","Phone No","Transmit Video","Transmit Audio","WhiteBoard-chat","Session Date","Session Time","Duration","Repeat","For Times" };
	
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);	
	
	/**
	 * Constructor of class
	 */
        protected LectureInfo(int p, Vector infoVector){
		ClassLoader clr= this.getClass().getClassLoader();
		java.util.StringTokenizer str1 = new java.util.StringTokenizer(infoVector.get(p).toString(),",");
		int k=0;
		descLabel=new JLabel[20];
		while(str1.hasMoreTokens()){
			descLabel[k]=new JLabel("<html><font color=Black>"+sessionInfo[k]+"</font><font color=Black><b>=</font><font color=blue><I>"+str1.nextElement().toString()+"</font></html>");  
			k++;
		}	
		descPanel=new JPanel();
		descPanel.setLayout(new GridLayout(0,2,5,0));
		descPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		for(int i=0;i<=13;i++)
			descPanel.add(descLabel[i]);			
		infoLabel=new JLabel("<html><u><Font Color=Black>"+Language.getController().getLangValue("LectureInfo.InformationOfLecture")+"</Font Color=Black></u></html>");
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
		frame.setTitle(Language.getController().getLangValue("LectureInfo.Title"));		/**Setting the title of the Frame*/
		frame.getContentPane().add(mainPan);		/**Adding panel to the frame*/
		java.awt.Dimension dim=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(450,350);				/**Setting the size of the frame*/
		frame.setVisible(true);				/**Showing the frame*/
            	frame.setLocation((((int)dim.getWidth()/2)-225),(((int)dim.getHeight()/2)-175));
	} 
	
	//Modified by pratibha
	public void mouseClicked(MouseEvent e) {
		 if(e.getComponent().getName().equals("closeLabel.Action")){
			closeLabel.setCursor(busyCursor);
			try{
				Thread.sleep(500);
			}catch(InterruptedException ie){
				closeLabel.setCursor(defaultCursor);
			}
			finally{
				closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		 	frame.dispose();
		 }
		 	
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
