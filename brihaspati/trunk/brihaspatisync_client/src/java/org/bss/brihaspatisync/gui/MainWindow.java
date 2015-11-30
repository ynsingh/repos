package org.bss.brihaspatisync.gui;
	
/**
 * MainWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012,2013,2015 ETRG, IIT Kanpur.
 */

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.Box;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.KeyboardFocusManager;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import org.bss.brihaspatisync.gui.Language;

import java.awt.Cursor;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 * @author <a href="mailto:Pradeepmca30@gmail.com">Pradeep Kumar Pal </a>
 */

public class MainWindow  extends JFrame implements ActionListener, MouseListener {

	private JMenu menu1;
	private JMenu menu2;
	private JMenu menu3;
        private JMenu menu4;
	private JMenu menu5;
	private JMenu menu6;

	private JLabel exit_icon;
	private JLabel logout_icon;
	private JLabel sessionOut_icon;

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
				int choice = JOptionPane.showOptionDialog(null,"Do you really want to exit the system", "Exit", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                                        if(choice == JOptionPane.YES_OPTION){
						new Logout().sendLogoutRequest();
	        	                	System.exit(0);
					}
                        }
                });
	}

	//Create JmenuBar for Mainwindow frame.
	private JMenuBar createJMenuBar(){
	
	        JMenuBar menuBar = new JMenuBar();

                menu1 = new JMenu(Language.getController().getLangValue("MainWindow.menu1"));
	
		menuItem7=new JMenuItem(Language.getController().getLangValue("InstructorCSPanel.LectureInfo"));
                menuItem7.setActionCommand("LectureInfo");
                menuItem7.setEnabled(false);
                menuItem7.addActionListener(this);
                menu1.add(menuItem7);
	
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
		
		menu6 = new JMenu();
		exit_icon = new JLabel();
                exit_icon.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/exit.png")));
		exit_icon.setToolTipText(Language.getController().getLangValue("MainWindow.menu6"));
		exit_icon.setName("Exit");
		exit_icon.setEnabled(true);
		exit_icon.addMouseListener(this);
		menu6.add(exit_icon);
		
		menu5 = new JMenu();
                logout_icon = new JLabel();
                logout_icon.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/logout.png")));
        	logout_icon.setToolTipText(Language.getController().getLangValue("MainWindow.menu5"));
                logout_icon.setName("Logout");
		logout_icon.setEnabled(false);
                logout_icon.addMouseListener(this);
                menu5.add(logout_icon);
		
		menu4 = new JMenu();
                sessionOut_icon = new JLabel();
                sessionOut_icon.setIcon(new javax.swing.ImageIcon(this.getClass().getClassLoader().getResource("resources/images/session_out.png")));
		sessionOut_icon.setToolTipText(Language.getController().getLangValue("MainWindow.menu4"));
                sessionOut_icon.setName("Sessionout");
		sessionOut_icon.setEnabled(false);
                sessionOut_icon.addMouseListener(this);
                menu4.add(sessionOut_icon);

                menuBar.add(menu1);
                menuBar.add(menu2);
                menuBar.add(menu3);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(menu4);
		menuBar.add(sessionOut_icon);
		menuBar.add(menu5);
		menuBar.add(logout_icon);
		menuBar.add(menu6);
		menuBar.add(exit_icon);
		
		return menuBar;	
	}
  

	public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Connection")) {
				new PreferenceWindow();
                	}else if(e.getActionCommand().equals("VideoServer")) {
                        	new VideoServerConfigure();
			}else if(e.getActionCommand().equals("Start-Recorder")) {
				 // Action code for start recorder.
                	} else if(e.getActionCommand().equals("LectureInfo")) {
			  	LectureInfo info= new LectureInfo(org.bss.brihaspatisync.util.ClientObject.getLectureInfoIndex(),org.bss.brihaspatisync.util.ClientObject.getLectureInfo()); 
                }else {
			StatusPanel.getController().setStatus(Language.getController().getLangValue("MainWindow.MessageDialog1"));
		}
        }
	

	 public void mouseClicked(MouseEvent e) {
		if(e.getComponent().getName().equals("Sessionout")) {
                        if(org.bss.brihaspatisync.util.ThreadController.getThreadFlag())
                        	new Logout().sendLogoutRequest();
                       		desktop.removeAll();
                        	desktop.setBackground(new Color(220,220,220));
                        	desktop.add(new CourseSessionWindow());
                        	content.add(desktop,BorderLayout.CENTER);
                        	menuItem7.setEnabled(false);
				sessionOut_icon.setEnabled(false);
                        	org.bss.brihaspatisync.util.ThreadController.setThreadFlag(false);
                        	content.validate();
                        	content.repaint();
                        	StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog4"));
		}
		if(e.getComponent().getName().equals("Logout")) {
                 	if(org.bss.brihaspatisync.util.ThreadController.getThreadFlag())
                        	new Logout().sendLogoutRequest();
                        	desktop.removeAll();
                        	StatusPanel.getController().setStatus(Language.getController().getLangValue("LoginWindow.MessageDialog6"));
                        	desktop.setBackground(new Color(220,220,220));
                        	//menu5.setEnabled(false);
                        	//menu4.setEnabled(false);
				logout_icon.setEnabled(false);
				sessionOut_icon.setEnabled(false);
                        	desktop.add(new LoginWindow());
                        	content.add(desktop,BorderLayout.CENTER);
                        	org.bss.brihaspatisync.util.ThreadController.setThreadFlag(false);
                        	content.validate();
                        	content.repaint();
		}
		if(e.getComponent().getName().equals("Exit")) {
                        int choice = JOptionPane.showOptionDialog(null,"Do you really want to exit the system", "Exit", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                              if(choice == JOptionPane.YES_OPTION){
                              	if(org.bss.brihaspatisync.util.ThreadController.getThreadFlag())
                                	new Logout().sendLogoutRequest();
                                        System.exit(0);
                 	}
		}     
	}
	
	public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e)  {}

	
	//set text for menuItem according to language choosen.
	public void setMenuItemText() {
		menu1.setText(Language.getController().getLangValue("MainWindow.menu1"));
		menu2.setText(Language.getController().getLangValue("MainWindow.menu2"));
		menu3.setText(Language.getController().getLangValue("MainWindow.menu3"));	

        	menuItem4.setText(Language.getController().getLangValue("MainWindow.menuItem4"));
        	menuItem5.setText(Language.getController().getLangValue("MainWindow.menuItem5"));
        	menuItem6.setText(Language.getController().getLangValue("MainWindow.menuItem6"));
		logout_icon.setEnabled(true);
		menuItem5.setEnabled(true);
		menuItem6.setEnabled(true);
		setTitle(Language.getController().getLangValue("MainWindow.MainWindowTitle"));
	}
               
	public void setMenuText() {
        	menuItem7.setEnabled(true);
        }
	
	public void setMenuText1(){
		sessionOut_icon.setEnabled(true);
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

