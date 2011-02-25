package org.bss.brihaspatisync.gui;
	
/**
 * MainWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008
 */

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class MainWindow  extends JFrame implements ActionListener{

	private static JDesktopPane desktop=null;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenuItem menuItem4;
	private JMenuItem menuItem5;
	private JPanel north_Panel =null;
	private JPanel south_Panel =null;
	private JPanel east_Panel =null;
	private JPanel west_Panel =null;
	private Container content=null;

	private static MainWindow fw=null;

	/**
	 * Controller for class.
	 */
        public static MainWindow  getController(){
                if (fw==null){
                        fw=new MainWindow();
                }
                return fw;
        }

  
	/**
	 * Constructor details for MainWindow
	 */
 	private MainWindow(){
    		content = getContentPane();
    		content.setBackground(new Color(24,116,205));
		setTitle("Welcome !  BrihaspatiSync Live Classroom");

		JMenuBar menuBar = new JMenuBar();
	        
		JMenu menu1 = new JMenu("Classroom");

		menuItem4=new JMenuItem("Logout");
             	menuItem4.setActionCommand("Logout");
              	menuItem4.setEnabled(false);
              	menuItem4.addActionListener(this);
             	menu1.add(menuItem4);

            	menuItem5=new JMenuItem("Sessionout");
		menuItem4.setActionCommand("Sessionout");
              	menuItem5.setEnabled(false);
              	menuItem5.addActionListener(this);
              	menu1.add(menuItem5);

		menuItem1=new JMenuItem("Exit");
                menuItem1.setActionCommand("Exit");
                menuItem1.addActionListener(this);
                menu1.add(menuItem1);


		JMenu menu2 = new JMenu("Recorder");

		menuItem2=new JMenuItem("Start-Recorder");
		menuItem2.addActionListener(this);
                menu2.add(menuItem2);

		JMenu menu3 = new JMenu("Preference");

                menuItem3 = new JMenuItem("Connection");
                menuItem3.addActionListener(this);
                menu3.add(menuItem3);

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
              	setJMenuBar(menuBar);

    		desktop = new JDesktopPane();
    		desktop.setBackground(new Color(220,220,220));

		north_Panel=new JPanel();
                north_Panel.setBackground(new Color(24,116,205));
                content.add(north_Panel,BorderLayout.NORTH);
	
		south_Panel=new JPanel();
		south_Panel.setBackground(new Color(24,116,205));
		content.add(south_Panel,BorderLayout.SOUTH);

		east_Panel=new JPanel();
                east_Panel.setBackground(new Color(24,116,205));
                content.add(east_Panel,BorderLayout.EAST);

		west_Panel=new JPanel();
                west_Panel.setBackground(new Color(24,116,205));
                content.add(west_Panel,BorderLayout.WEST);

		
    		content.add(desktop,BorderLayout.CENTER);

		Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
    		setSize((int)dim.getWidth(),(int)dim.getHeight());
    	    	setVisible(true);
		addWindowListener( new WindowAdapter (){
                	public void windowClosing (WindowEvent ev ){
				Logout.getController().sendLogoutRequest();
				System.exit(0);
			}
		});
  	}

	public void actionPerformed(ActionEvent e) {
                if(e.getSource()==menuItem3){
			new PreferenceWindow();
                }
		else if(e.getActionCommand().equals("Logout")){

                        desktop.removeAll();
                        desktop.add(new LoginWindow());
                	desktop.revalidate();
                	desktop.validate();
                	desktop.repaint();
                	menuItem4.setEnabled(false);

            	}else if(e.getActionCommand().equals("Sessionout")){
			content.removeAll();//(1);
			JPanel p1=new JPanel();
			p1.setLayout(new BorderLayout());
			
			JPanel north_Panel1=new JPanel();
	                north_Panel1.setBackground(new Color(24,116,205));
        	       	p1.add(north_Panel1,BorderLayout.NORTH);

                	JPanel south_Panel1=new JPanel();
                	south_Panel1.setBackground(new Color(24,116,205));
                	p1.add(south_Panel1,BorderLayout.SOUTH);

                	JPanel east_Panel1=new JPanel();
                	east_Panel1.setBackground(new Color(24,116,205));
                	p1.add(east_Panel1,BorderLayout.EAST);

                	 JPanel west_Panel1=new JPanel();
                	west_Panel1.setBackground(new Color(24,116,205));
                	p1.add(west_Panel1,BorderLayout.WEST);

			JDesktopPane desktop1 = new JDesktopPane();
	                desktop1.setBackground(new Color(220,220,220));
			desktop1.add(new CourseSessionWindow());//.getController());
		
			p1.add(desktop1, BorderLayout.CENTER);
               	 	content.add(p1);
                	content.validate();
			content.repaint();
         	}else if(e.getActionCommand().equals("Exit")){
			Logout.getController().sendLogoutRequest();
                        System.exit(0);
                }else{
			JOptionPane.showMessageDialog(null,"wrong action please try again !!");

		}
        }

        public JDesktopPane getDesktop(){
                return desktop;
        }
	
        protected JMenuItem getMenuItem1(){
                return menuItem1;
        }

        protected JMenuItem getMenuItem2(){
                return menuItem2;
        }

        protected JMenuItem getMenuItem3(){
                return menuItem3;
        }
        protected JMenuItem getMenuItem4(){
                return menuItem4;
        }
        protected JMenuItem getMenuItem5(){
                return menuItem5;
        }

        public  Container getContainer(){
                return content;
        }
}

