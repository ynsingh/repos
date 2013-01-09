package org.iitk.livetv.gui;

/**
 * LiveTVLeftPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane; 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;;
import java.util.Collections;
import java.util.Vector;
import org.iitk.livetv.util.ClientObject;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 02Sep2012 
 */
 
public class LiveTVLeftPanel extends JPanel implements ActionListener, MouseListener{

	private static LiveTVLeftPanel leftPanel=null;
	private JPanel mainPanel=null;
	private JPanel northPanel=null;
	private JComboBox categoryBox=null;
	private DefaultListModel model;
	private JList list=null;
	private JScrollPane pane=null;
	private ClientObject client_obj=ClientObject.getController();


	public static LiveTVLeftPanel getController(){
		if(leftPanel==null){
			leftPanel=new LiveTVLeftPanel();
		}
		return leftPanel;
	}

	public JPanel createGUI(){
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());

		northPanel=new JPanel();
		northPanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 1.0;
                gbc.weightx = 1.0;
                gbc.fill=GridBagConstraints.HORIZONTAL;
		
		JLabel selectCategory=new JLabel("Select category");
		gbc.gridx = 0;
                gbc.gridy = 0;
		gbc.insets = new Insets(10,5,0,5);
                northPanel.add(selectCategory,gbc);

		
		Vector channelCategory=ClientObject.getController().getCategory();
		Collections.sort(channelCategory);
		categoryBox=new JComboBox(channelCategory); 
		categoryBox.addActionListener(this);
                gbc.gridx = 0;
                gbc.gridy = 1;
		gbc.insets = new Insets(5,5,10,5);
		northPanel.add(categoryBox,gbc);
	
		mainPanel.add(northPanel,BorderLayout.NORTH);

		
		model = new DefaultListModel();
    		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    		pane = new JScrollPane(list);		
                mainPanel.add(pane,BorderLayout.CENTER);


		return mainPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

	public void mouseClicked(MouseEvent ev) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e){}
}