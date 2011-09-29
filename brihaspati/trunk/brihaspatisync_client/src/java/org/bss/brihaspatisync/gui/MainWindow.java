package org.bss.brihaspatisync.gui;
	
/**
 * MainWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
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
import org.bss.brihaspatisync.util.Language;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class MainWindow  extends JFrame implements ActionListener{

	private static JDesktopPane desktop=null;
	private JMenu menu1;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JMenu menu2;
	private JMenuItem menuItem4;
	private JMenu menu3;
	private JMenuItem menuItem5;
	private JPanel north_Panel =null;
	private JPanel south_Panel =null;
	private JPanel east_Panel =null;
	private JPanel west_Panel =null;
	private Container content=null;
	private JMenuBar menuBar;	

	private static MainWindow fw=null;
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
	public void createGUI(){
                
		content = getContentPane();
                content.setBackground(new Color(24,116,205));
                setTitle(Language.getController().getLangValue("MainWindow.MainWindowTitle"));
		setIconImage(new ImageIcon(clr.getResource("resources/images/mainwindow.gif")).getImage());
                setJMenuBar(createJMenuBar());

		desktop = new JDesktopPane();
                desktop.setBackground(new Color(220,220,220));

                north_Panel=new JPanel();
                north_Panel.setBackground(new Color(24,116,205));
                content.add(north_Panel,BorderLayout.NORTH);

                south_Panel=StatusPanel.getController();
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

	//Create JmenuBar for Mainwindow frame.
	private JMenuBar createJMenuBar(){
	        menuBar = new JMenuBar();

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

                menuItem3=new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem3"));
                menuItem3.setActionCommand("Exit");
                menuItem3.addActionListener(this);
                menu1.add(menuItem3);


                menu2 = new JMenu(Language.getController().getLangValue("MainWindow.menu2"));

                menuItem4=new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem4"));
                menuItem4.setActionCommand("Start-Recorder");
                menuItem4.addActionListener(this);
                menu2.add(menuItem4);

                menu3 = new JMenu(Language.getController().getLangValue("MainWindow.menu3"));

                menuItem5 = new JMenuItem(Language.getController().getLangValue("MainWindow.menuItem5"));
                menuItem5.setActionCommand("Connection");
                menuItem5.addActionListener(this);
                menu3.add(menuItem5);

                menuBar.add(menu1);
                menuBar.add(menu2);
                menuBar.add(menu3);

		return menuBar;	
	}
  

	public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Connection")){
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
                	south_Panel1.setBackground(new Color(0,0,0));
                	//south_Panel1.setBackground(new Color(24,116,205));
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
		}else if(e.getActionCommand().equals("Start-Recorder")){
			// Action code for start recorder.
                }else{
			StatusPanel.getController().setStatus(Language.getController().getLangValue("MainWindow.MessageDialog1"));

		}
        }

	//set text for menu according to language choosen.
	public void setMenuText(){
		menu1.setText(Language.getController().getLangValue("MainWindow.menu1"));
		menu2.setText(Language.getController().getLangValue("MainWindow.menu2"));
		menu3.setText(Language.getController().getLangValue("MainWindow.menu3"));
	}
	
	//set text for menuItem according to language choosen.
	public void setMenuItemText(){
 		menuItem1.setText(Language.getController().getLangValue("MainWindow.menuItem1"));
        	menuItem2.setText(Language.getController().getLangValue("MainWindow.menuItem2"));
        	menuItem3.setText(Language.getController().getLangValue("MainWindow.menuItem3"));
        	menuItem4.setText(Language.getController().getLangValue("MainWindow.menuItem4"));
        	menuItem5.setText(Language.getController().getLangValue("MainWindow.menuItem5"));
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

