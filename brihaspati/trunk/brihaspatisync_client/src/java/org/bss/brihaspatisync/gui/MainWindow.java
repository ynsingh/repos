package org.bss.brihaspatisync.gui;
	
/**
 * MainWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012,2013 ETRG, IIT Kanpur.
 */

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class MainWindow  extends JFrame implements ActionListener {

	private JMenu menu1;
	private JMenu menu2;
	private JMenu menu3;

	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenuItem menuItem4;
	private JMenuItem menuItem5;
	private JMenuItem menuItem6;
	private JMenuItem menuItem7;
	
	private static Container content=null;
	private static MainWindow fw=null;
	private static JDesktopPane desktop=null;
	private ClassLoader clr= this.getClass().getClassLoader();

	/**
	 * Controller for class.
	 */
	
        public static MainWindow  getController(){
                if (fw==null){
                        fw=new MainWindow();
                }
                return fw;
        }
	
	//Create MainWindow frame GUI.
	public void createGUI() {
	
		content = getContentPane();
                content.setBackground(new Color(24,116,205));
                setTitle(Language.getController().getLangValue("MainWindow.MainWindowTitle"));
		setIconImage(new ImageIcon(clr.getResource("resources/images/mainwindow.gif")).getImage());
                setJMenuBar(createJMenuBar());
		
		desktop = new JDesktopPane();
		desktop.setBackground(new Color(220,220,220));
		desktop.add(new LoginWindow());
	
		JPanel northPanel=new JPanel();
                northPanel.setBackground(new Color(24,116,205));

                JPanel south_Panel=StatusPanel.getController();
                south_Panel.setBackground(new Color(24,116,205));

                JPanel east_Panel=new JPanel();
                east_Panel.setBackground(new Color(24,116,205));

                JPanel west_Panel=new JPanel();
                west_Panel.setBackground(new Color(24,116,205));

		content.add(northPanel,BorderLayout.NORTH);
		content.add(south_Panel,BorderLayout.SOUTH);
		content.add(east_Panel,BorderLayout.EAST);
		content.add(west_Panel,BorderLayout.WEST);
		content.add(desktop,BorderLayout.CENTER);

                Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                setSize((int)dim.getWidth(),(int)dim.getHeight());
                setVisible(true);
                addWindowListener( new WindowAdapter (){
                        public void windowClosing (WindowEvent ev ) {
				new Logout().sendLogoutRequest();
	                        System.exit(0);
                        }
                });
	}

	//Create JmenuBar for Mainwindow frame.
	private JMenuBar createJMenuBar(){
	        JMenuBar menuBar = new JMenuBar();
                menu1 = new JMenu(Language.getController().getLangValue("MainWindow.menu1"));
                menuItem1=new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem1"));
                menuItem1.setActionCommand("Logout");
                menuItem1.setEnabled(false);
                menuItem1.addActionListener(this);
                menu1.add(menuItem1);

                menuItem2=new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem2"));
                menuItem2.setActionCommand("Sessionout");
                menuItem2.setEnabled(false);
                menuItem2.addActionListener(this);
                menu1.add(menuItem2);

		menuItem7=new JMenuItem(Language.getController().getLangValue("InstructorCSPanel.LectureInfo"));
                menuItem7.setActionCommand("LectureInfo");
                menuItem7.setEnabled(false);
                menuItem7.addActionListener(this);
                menu1.add(menuItem7);

                menuItem3=new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem3"));
                menuItem3.setActionCommand("Exit");
                menuItem3.addActionListener(this);
                menu1.add(menuItem3);


                menu2 = new JMenu(Language.getController().getLangValue("MainWindow.menu2"));

                menuItem4=new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem4"));
                menuItem4.setActionCommand("Start-Recorder");
		menuItem4.setEnabled(false);
                menuItem4.addActionListener(this);
                menu2.add(menuItem4);

                menu3 = new JMenu(Language.getController().getLangValue("MainWindow.menu3"));

                menuItem5 = new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem5"));
                menuItem5.setActionCommand("Connection");
		menuItem5.setEnabled(true);
                menuItem5.addActionListener(this);
                menu3.add(menuItem5);
		
		menuItem6 = new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem6"));
                menuItem6.setActionCommand("VideoServer");
		menuItem6.setEnabled(false);
                menuItem6.addActionListener(this);
                menu3.add(menuItem6);

                menuBar.add(menu1);
                menuBar.add(menu2);
                menuBar.add(menu3);
		
		return menuBar;	
	}
  

	public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Connection")) {
			new PreferenceWindow();
                } else if(e.getActionCommand().equals("VideoServer")) {
                        new VideoServerConfigure();
                } else if(e.getActionCommand().equals("Logout")) {
			new Logout().sendLogoutRequest();	
                        desktop.removeAll();
                        StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog6"));

			desktop.setBackground(new Color(220,220,220));
			menuItem1.setEnabled(false);
	                menuItem2.setEnabled(false);	
			desktop.add(new LoginWindow());
			content.add(desktop,BorderLayout.CENTER);
                        org.bss.brihaspatisync.util.ThreadController.setThreadFlag(false);
                	content.validate();
                	content.repaint();
            	} else if(e.getActionCommand().equals("Sessionout")) {
			new Logout().sendLogoutRequest();
			desktop.removeAll();
                        desktop.setBackground(new Color(220,220,220));
                        desktop.add(new CourseSessionWindow());
                        content.add(desktop,BorderLayout.CENTER);
			org.bss.brihaspatisync.util.ThreadController.setThreadFlag(false);
                        content.validate();
                        content.repaint();
			
         	}else if(e.getActionCommand().equals("Exit")) {
			new Logout().sendLogoutRequest();
                        System.exit(0);
		}else if(e.getActionCommand().equals("Start-Recorder")) {
			 // Action code for start recorder.
                } else if(e.getActionCommand().equals("LectureInfo")) {
		  	LectureInfo info=new LectureInfo(org.bss.brihaspatisync.util.ClientObject.getLectureInfoIndex(),org.bss.brihaspatisync.util.ClientObject.getLectureInfo());                      
                }
		else {
			StatusPanel.getController().setStatus(Language.getController().getLangValue("MainWindow.MessageDialog1"));
		}
        }

	//set text for menuItem according to language choosen.
	public void setMenuItemText() {
		menu1.setText(Language.getController().getLangValue("MainWindow.menu1"));
		menu2.setText(Language.getController().getLangValue("MainWindow.menu2"));
		menu3.setText(Language.getController().getLangValue("MainWindow.menu3"));	

 		menuItem1.setText(Language.getController().getLangValue("MainWindow.menuItem1"));
        	menuItem2.setText(Language.getController().getLangValue("MainWindow.menuItem2"));
        	menuItem3.setText(Language.getController().getLangValue("MainWindow.menuItem3"));
        	menuItem4.setText(Language.getController().getLangValue("MainWindow.menuItem4"));
        	menuItem5.setText(Language.getController().getLangValue("MainWindow.menuItem5"));
        	menuItem6.setText(Language.getController().getLangValue("MainWindow.menuItem6"));
        	menuItem7.setText(Language.getController().getLangValue("InstructorCSPanel.LectureInfo"));
		menuItem1.setEnabled(true);
		menuItem2.setEnabled(true);
		menuItem5.setEnabled(true);
		menuItem6.setEnabled(true);
		//setTitle(Language.getController().getLangValue("MainWindow.MainWindowTitle"));
	}

        public JDesktopPane getDesktop(){
                return desktop;
        }
        public  Container getContainer(){
                return content;
        }
	
	public  void setCouseid(String courseid){
               setTitle(courseid);
        }
}

