/**
 *  MainWindow.java
 *    
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *  Modified for showing courses running and reflector to reflector peering
 *  @date 15/04/12
 *  Modified 19/06/2012
 *  */

package org.bss.brihaspatisync.monitor.gui;

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

import org.bss.brihaspatisync.monitor.ReflectorManager;
import org.bss.brihaspatisync.monitor.RegisterToIndexServer;
import org.bss.brihaspatisync.monitor.graphlayout.GLPanel;
import org.bss.brihaspatisync.monitor.graphlayout.TGPanel;
import org.bss.brihaspatisync.monitor.gui.PacketRategraph;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;


public class MainWindow  extends JFrame implements ActionListener, MouseListener, ItemListener {

	private static JDesktopPane desktop=null;
	private JPanel main_Panel=null;
	private JPanel refp_Panel=null;
	private JPanel left_Panel =null;
	private JPanel north_Panel =null;
	private JPanel status_Panel =null;
	private JPanel right_Panel =null;
	public JPanel course_Panel =null;
	public JPanel display_Panel =null;
	public JPanel packetRate_Panel =null;


	private JMenuBar menuBar=null;
	private JMenuItem login_menu=null;
	private JMenuItem logout_menu=null;
        private JPanel monitorPanel=null;
	private JPanel top_panel=null;
   	boolean flag; 
	
	private JSplitPane jSplitPane=null;
	private JSplitPane jSplitPane1=null;
	private JSplitPane jSplitPane2=null;
  
 
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
	public  JLabel courseLabel2;
		
	public static int csize;	

	public JCheckBox[] courseLabel = new JCheckBox[5];
	
	private Vector ReflectorsRunning=null;
	public Vector courses=null;

	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	private static MainWindow fw=null;
	static String refIP;

  	public static MainWindow  getController(){
    		if (fw==null){
        		fw=new MainWindow();
      		}
      		return fw;
  	}
	
  	public MainWindow(){
  		content = getContentPane();
    		content.setBackground(new Color(24,116,205));
		setTitle("    LED    ");
		content.add(createMainPanel());
		setJMenuBar(createMenu());
		java.awt.Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)dim.getWidth(),(int)dim.getHeight());
		int w=(int)dim.getWidth();
		int h=(int)dim.getHeight();
		setVisible(true);
		addWindowListener( new WindowAdapter (){
                        public void windowClosing (WindowEvent ev ){
				    File f=new File("Monitor.xml");
				    f.delete();
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
                left_Panel.add(imageLabel);
		left_Panel.add(TreeMenu.getController().createGUI());
                Dimension dim=new Dimension(570,840);
                left_Panel.setMinimumSize(dim);
		return left_Panel;
        }

	
	  public JPanel createdisplayCoursePanel(){
                display_Panel=new JPanel();
		display_Panel.setLayout(new GridLayout(5,1));
		JLabel courseLabel1=new JLabel("Please Select the Course");
		courseLabel2=new JLabel("No COurse is Running");
		Dimension dim=new Dimension(70,40);
                display_Panel.setMinimumSize(dim);
		display_Panel.setBackground(Color.white);
		display_Panel.add(courseLabel1,BorderLayout.PAGE_START);
                return display_Panel;
        }

	
	 public void getSelectedRef(String str){
                refIP=str;
        }

	 public JPanel createCoursePanel(Vector courses){
		csize=courses.size();
		
		course_Panel=new JPanel();
		course_Panel.setLayout(new GridLayout(1,3));
		for(int i=0;i<courses.size();i++)
                course_Panel.setBackground(Color.white);
		Dimension dim=new Dimension(20,10);
                course_Panel.setMinimumSize(dim);
			for(int i=0;i<courses.size();i++){
                		courseLabel[i]=new JCheckBox(courses.elementAt(i).toString());
				courseLabel[i].setSelected(false);
				courseLabel[i].setName(courses.elementAt(i).toString());
                		course_Panel.add(courseLabel[i]);
				courseLabel[i].addItemListener(this);
				courseLabel[i].setBackground(Color.white);
			}


                return course_Panel;
        }

	public JPanel createrefPPanel(){
                refp_Panel=new JPanel();
                refp_Panel.setLayout(new BorderLayout());
		refp_Panel.setBackground(Color.white);
		Dimension dim=new Dimension(70,40);
                refp_Panel.setMinimumSize(dim);
   
                return refp_Panel;
        }

	public JPanel createpacketRatePanel(){
                packetRate_Panel=new JPanel();
                packetRate_Panel.setLayout(new BorderLayout());
                packetRate_Panel.setBackground(Color.white);
                Dimension dim=new Dimension(70,40);
                packetRate_Panel.setMinimumSize(dim);

                return  packetRate_Panel;
        }
	

	public JPanel createRightPanel(){
      		right_Panel=new JPanel();
                right_Panel.setLayout(new BorderLayout());
                right_Panel.setBackground(Color.white);

                JLabel imageLabel2=new JLabel(new ImageIcon(clr.getResource("Icons&Logos/englishbrihaspati3.png")));
                right_Panel.add(imageLabel2,BorderLayout.CENTER);
		audioBttn=new JLabel("Audio");
                vedioBttn=new JLabel("Video");
                wbBttn=new JLabel("Whiteboard");
                pptBttn=new JLabel("PPT");
                desktopsharingBttn=new JLabel("DesktopSharing");
                clientBttn=new JLabel("Client");
                Dimension dim1=new Dimension(880,640);       
                right_Panel.setMinimumSize(dim1);
                return right_Panel;
        }

	
          public JSplitPane createSplitPane2(){
                jSplitPane2 =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,createrefPPanel(),createdisplayCoursePanel());
		jSplitPane2.setContinuousLayout(false);
		jSplitPane2.setDividerLocation(260);		
                return jSplitPane2;
        }

	
        public JSplitPane createSplitPane1(){
                jSplitPane1 =new JSplitPane(JSplitPane.VERTICAL_SPLIT,createSplitPane2(), createSplitPane3());
                jSplitPane1.setContinuousLayout(false);
		jSplitPane1.setDividerLocation(160);

                return jSplitPane1;
        }

	
        public JSplitPane createSplitPane(){
                jSplitPane =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createLeftPanel(),createSplitPane1());
                jSplitPane.setContinuousLayout(false);  
                jSplitPane.setDividerLocation(260);
                jSplitPane.setDividerSize(1);
                return jSplitPane;
        }

	public JSplitPane createSplitPane3(){
                jSplitPane =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createRightPanel(),createpacketRatePanel());
                jSplitPane.setContinuousLayout(false);
                jSplitPane.setDividerLocation(860);
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
		Dimension dim1=new Dimension(880,30);
                monitorPanel.setPreferredSize(dim1);
		Font font = new Font("Verdana", Font.BOLD,16);
	        audioBttn=new JLabel("Audio");
		audioBttn.setFont(font);
                vedioBttn=new JLabel("Vedio");
		vedioBttn.setFont(font);
                wbBttn=new JLabel("Whiteboard");
		wbBttn.setFont(font);
                pptBttn=new JLabel("PPT");
		pptBttn.setFont(font);
                desktopsharingBttn=new JLabel("DesktopSharing");
    		desktopsharingBttn.setFont(font);
                clientBttn=new JLabel("Client");
		clientBttn.setFont(font);
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
		
		logout_menu=new JMenuItem("Exit");
                logout_menu.setActionCommand("exit");
                logout_menu.addActionListener(this);
                logout_menu.setEnabled(true);


                var_brihaspati.add(login_menu);
	        var_brihaspati.add(logout_menu);

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

	  public JPanel getCoursePanel(){
                return course_Panel;
        }

	  public JPanel getrefPPanel(){
                return refp_Panel;
        }

        public JPanel getdisplayPanel(){
                return display_Panel;
        }

	public JPanel getpacketRatePanel(){
		return packetRate_Panel;
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
 
		if(e.getActionCommand().equals("exit"))
			 System.exit(0);
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
				GLPanel glPanel = new GLPanel();
                        	clientBttn.setCursor(busyCursor);
				getRightPanel().remove(1);
                                getRightPanel().add(glPanel,BorderLayout.CENTER);
                                getRightPanel().revalidate();
				org.bss.brihaspatisync.monitor.network.GLPanelThread st=new org.bss.brihaspatisync.monitor.network.GLPanelThread();
                		st.start();
		
			
		}
	}
	

	public void itemStateChanged(ItemEvent e) {
		getRightPanel().add(createMonitorPanel(),BorderLayout.PAGE_START);
                getRightPanel().revalidate();
		String s=null;
    		for(int i=0;i<csize;i++){
    			if(courseLabel[i].isSelected()){
				s=courseLabel[i].getText();
				TGPanel tgp=new TGPanel();
				tgp.getSelectedCourse(s);
			}else{
				s=courseLabel[i].getText();
				TGPanel tgp=new TGPanel();
                                tgp.removeCourse(s);
			}
		}
	}
		
   	public void mousePressed(MouseEvent e) {}
  	public void mouseReleased(MouseEvent e) {}
  	public void mouseEntered(MouseEvent e) {}
  	public void mouseExited(MouseEvent e) {}
}

