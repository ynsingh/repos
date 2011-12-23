package org.bss.brihaspatisync.monitor.gui;

/**
 * MainWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */


import java.util.Vector;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.JToolBar;
import javax.swing.Box;
import javax.swing.UIManager;
import java.io.File;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import org.bss.brihaspatisync.monitor.RegisterToIndexServer;
import org.bss.brihaspatisync.monitor.ReflectorManager;
import org.bss.brihaspatisync.monitor.graphlayout.GLPanel;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */


public class MainWindow  extends JFrame implements ActionListener, MouseListener {

	private static JDesktopPane desktop=null;
	private JPanel main_Panel=null;
	private JPanel left_Panel =null;
	private JPanel north_Panel =null;
	private JPanel status_Panel =null;
	private JPanel right_Panel =null;
	private JMenuBar menuBar=null;
	private JMenuItem login_menu=null;
        private JPanel monitorPanel=null;
	private JPanel top_panel=null;
   	boolean flag; 
	
	private JSplitPane jSplitPane=null;
    	private ClassLoader clr= this.getClass().getClassLoader();;
	private Container content=null;
	private JMenu help_menu=null;
	private JMenu seprate_menu=null;

	private JLabel audioBttn=null;;
	private JLabel vedioBttn=null;;
        private JLabel wbBttn=null;;
        private JLabel pptBttn=null;
        private JLabel desktopsharingBttn=null;
        private JLabel clientBttn=null;

	private Vector ReflectorsRunning=null;

	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	private static MainWindow fw=null;

  	public static MainWindow  getController(){
    		if (fw==null){
        		fw=new MainWindow();
      		}
      		return fw;
  	}
	
  	public MainWindow(){
  		content = getContentPane();
    		content.setBackground(new Color(5,106,167));
		setTitle("    LED    ");
		content.add(createMainPanel());
		setJMenuBar(createMenu());
		java.awt.Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1200,500);                                 
		int w=(int)dim.getWidth();
		int h=(int)dim.getHeight();
		setLocation((w-650),(h-530));
		setResizable(false);
		setVisible(true);
		addWindowListener( new WindowAdapter (){
                        public void windowClosing (WindowEvent ev ){
                                    System.exit(0);
                        }
                });
  	}
		
	
  	/**
	 * Constructor details for MainWindow
	 */
 	private JPanel createMainPanel(){
		main_Panel=new JPanel();
        	main_Panel.setLayout(new BorderLayout());
                main_Panel.setBackground(Color.white);
	        main_Panel.add(createStatusPanel(),BorderLayout.SOUTH);
                return main_Panel;
  	}
	
	public JPanel createLeftPanel(){
		left_Panel=new JPanel();
                left_Panel.setLayout(new BorderLayout());
                JLabel imageLabel=new JLabel(new ImageIcon(clr.getResource("Icons&Logos/left.png")));
                left_Panel.add(imageLabel,BorderLayout.NORTH);
                left_Panel.add(TreeMenu.getController().createGUI(),BorderLayout.CENTER);
                Dimension dim=new Dimension(570,840);
                left_Panel.setMinimumSize(dim);
		return left_Panel;
        }
	
	public JPanel createRightPanel(){
      		right_Panel=new JPanel();
                right_Panel.setLayout(new BorderLayout());
                right_Panel.setBackground(Color.white);
                JLabel imageLabel1=new JLabel(new ImageIcon(clr.getResource("Icons&Logos/right.png")));
                right_Panel.add(imageLabel1,BorderLayout.NORTH);

                JLabel imageLabel2=new JLabel(new ImageIcon(clr.getResource("Icons&Logos/englishbrihaspati3.png")));
                right_Panel.add(imageLabel2,BorderLayout.CENTER);
		audioBttn=new JLabel("Audio");
                vedioBttn=new JLabel("Vedio");
                wbBttn=new JLabel("Whiteboard");
                pptBttn=new JLabel("PPT");
                desktopsharingBttn=new JLabel("DesktopSharing");
                clientBttn=new JLabel("Client");
                Dimension dim1=new Dimension(880,640);       
                right_Panel.setMinimumSize(dim1);
                return right_Panel;
        }
	
        public JSplitPane createSplitPane(){
                jSplitPane =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createLeftPanel(), createRightPanel());
                jSplitPane.setContinuousLayout(false);  
                jSplitPane.setDividerLocation(260);
                jSplitPane.setDividerSize(1);
                return jSplitPane;
        }

        public JPanel createStatusPanel(){

                status_Panel=new JPanel();
                status_Panel.setLayout(new BorderLayout());
                try {
                        status_Panel.add(StatusLabel.getController(),BorderLayout.WEST);
                        status_Panel.setBackground(new Color(5,106,167));
                }catch(Exception e){}
                return status_Panel;
        }

	 public JPanel createMonitorPanel(){

                monitorPanel=new JPanel();
                monitorPanel.setBackground(Color.gray);
                monitorPanel.setLayout(new GridLayout(1,6));
	
	        audioBttn=new JLabel("Audio");
                vedioBttn=new JLabel("Vedio");
                wbBttn=new JLabel("Whiteboard");
                pptBttn=new JLabel("PPT");
                desktopsharingBttn=new JLabel("DesktopSharing");
                clientBttn=new JLabel("Client");
		
		monitorPanel.add(audioBttn);
                monitorPanel.add(vedioBttn);
                monitorPanel.add(wbBttn);
                monitorPanel.add(pptBttn);
                monitorPanel.add(desktopsharingBttn);
                monitorPanel.add(clientBttn);
                clientBttn.addMouseListener(this);
                clientBttn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                clientBttn.setName("clientBttn.Action");
                return monitorPanel;
        }

	private JMenu var_brihaspati=null;
	public JMenuBar createMenu(){
                menuBar = new JMenuBar();
                menuBar.setBackground(new Color(5,106,167));

                var_brihaspati = new JMenu("Brihaspati");
                var_brihaspati.setBackground(new Color(5,106,167));
                var_brihaspati.setForeground(new Color(255,255,255));
		
		login_menu=new JMenuItem("Login");
                login_menu.setIcon(new ImageIcon(clr.getResource("Icons&Logos/Login-16x16.png")));
                login_menu.setActionCommand("login");
                login_menu.addActionListener(this);
                login_menu.setEnabled(true);
		
                var_brihaspati.add(login_menu);
		menuBar.add(var_brihaspati);

		seprate_menu = new JMenu(" | ");
                seprate_menu.setBackground(new Color(5,106,167));
                seprate_menu.setForeground(new Color(255,255,255));
                menuBar.add(seprate_menu);
		
                return menuBar;
	}	
	public  Container getContainer(){
	        return content;
        }
		
	public JPanel getMainPanel(){
                return main_Panel;
        }
	public JPanel getLeftPanel(){
        	return left_Panel;
        }		
	
	public JSplitPane getJSplitPanePanel(){
        	return jSplitPane;
        }

	 public JPanel getRightPanel(){
                return right_Panel;
        }

	 public boolean startReflector() {
                Vector indexServerList=RegisterToIndexServer.getController().connectToMasterServer1();
                String str1[]=new String[indexServerList.size()];
                for(int i=0;i<indexServerList.size();i++)
                        str1[i]=indexServerList.get(i).toString();
                JComboBox combo = new JComboBox(str1);
                Object[] message = new Object[] {"Select I_Server ",combo};
                int r = JOptionPane.showConfirmDialog(null, message, "I_Server", JOptionPane.OK_CANCEL_OPTION);
                if (r == JOptionPane.OK_OPTION) {
                        RegisterToIndexServer.getController().setIServerIP((String)combo.getSelectedItem());
                }
                if(indexServerList.size()>0){
                        flag=true;
                }
                return flag;
        }



	public void actionPerformed(ActionEvent e) {
 
		if(e.getActionCommand().equals("login")){
			 org.bss.brihaspatisync.monitor.gui.MainWindow.getController().startReflector();
			 
			login_menu.setEnabled(false);
			boolean indexServerList=org.bss.brihaspatisync.monitor.RegisterToIndexServer.getController().connectToMasterServer();
			if(indexServerList) {
                        	getMainPanel().add(createSplitPane(),BorderLayout.CENTER);
                        	getMainPanel().revalidate();
		        	StatusLabel.getController().setStatus(" Logged In Successfully");
			} else { 
				StatusLabel.getController().setStatus("<BLINK> not Logged in Successfully </BLINK> ");
			}


			org.bss.brihaspatisync.monitor.network.ReguestMasterServerList st=new org.bss.brihaspatisync.monitor.network.ReguestMasterServerList();
                        st.start();

                }
		
	}

	 public void mouseClicked(MouseEvent me) {
		if(me.getComponent().getName().equals("clientBttn.Action")){
				ReflectorsRunning=ReflectorManager.getController().getReflectorIP();
				GLPanel glPanel = new GLPanel();
                        	clientBttn.setCursor(busyCursor);
                             	getRightPanel().remove(1);
	                        getRightPanel().add(glPanel,BorderLayout.CENTER);
        	                getRightPanel().revalidate();
				org.bss.brihaspatisync.monitor.network.GLPanelThread st=new org.bss.brihaspatisync.monitor.network.GLPanelThread();
                		st.start();
		
			
   		}
	}
	
		
   	public void mousePressed(MouseEvent e) {}
  	public void mouseReleased(MouseEvent e) {}
  	public void mouseEntered(MouseEvent e) {}
  	public void mouseExited(MouseEvent e) {}

}

