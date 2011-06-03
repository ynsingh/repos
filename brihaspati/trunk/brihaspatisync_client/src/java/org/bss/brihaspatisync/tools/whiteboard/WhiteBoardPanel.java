package org.bss.brihaspatisync.tools.whiteboard;

/**
 * WhiteBoardPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.gui.HandRaiseAction;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class WhiteBoardPanel extends JPanel implements ActionListener, MouseListener{

        private JPanel mainPanel;
        private static WhiteBoardPanel wbPanel=null;
	private TitledBorder titleBorder;
	private static int button_value=0;
	private  JButton curveButton;
	private  JButton lineButton;
	private  JButton rectButton;
	private  JButton ovalButton;
	private  JButton roundrectButton;
	private  JButton filledrectButton;
	private  JButton filledovalButton;
	private  JButton filledroundrectButton;
	private  JButton texter;
	private JButton desk_share=null;
	private JButton desk_ppt=null;
	private  JLabel bold;
	private  JLabel italic;
	private  JLabel unline;
	private  JButton eraser;
	private  JButton color=null;
	private JComboBox fontsize=null;
	private JComboBox f_Name=null;
	private String font_Name=null;	     // Font name from Style Combobox
	private int font_Size=10;       // Size of Font
	private int font_Style=Font.PLAIN;      // BOLD, ITALIC, UNDERLINE
	private ClassLoader clr;
	private String role=ClientObject.getController().getUserRole();



        public static WhiteBoardPanel getController(){
                if (wbPanel==null){
                        wbPanel=new WhiteBoardPanel();
                }
                return wbPanel;
        }

	protected WhiteBoardPanel(){
		clr= this.getClass().getClassLoader();

	}

       	public JPanel createGUI(){
                
                mainPanel=new JPanel();
                mainPanel.setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar("Whiteboard Toolbar");
		
		curveButton=new JButton(new ImageIcon(clr.getResource("resources/images/wb/freehand.png")));
                curveButton.setToolTipText("curve");
                curveButton.setActionCommand("0");
                curveButton.addActionListener(this);
                toolBar.add(curveButton);
		toolBar.addSeparator();

                lineButton=new JButton(new ImageIcon(clr.getResource("resources/images/wb/line.png")));
                lineButton.setToolTipText("line");
                lineButton.setActionCommand("1");
                lineButton.addActionListener(this);
                toolBar.add(lineButton);
		toolBar.addSeparator();

                rectButton=new JButton(new ImageIcon(clr.getResource("resources/images/wb/rectangle.gif")));
                rectButton.setToolTipText("rectangle");
                rectButton.setActionCommand("2");
                rectButton.addActionListener(this);
                toolBar.add(rectButton);
		toolBar.addSeparator();

		filledrectButton=new JButton(new ImageIcon(clr.getResource("resources/images/wb/rectanglefill.gif")));
                filledrectButton.setToolTipText("<html>filled<br>rectangle</html>");
                filledrectButton.setActionCommand("5");
                filledrectButton.addActionListener(this);
                toolBar.add(filledrectButton);
                toolBar.addSeparator();

                ovalButton=new JButton(new ImageIcon(clr.getResource("resources/images/wb/ellipse.gif")));
                ovalButton.setToolTipText("oval");
                ovalButton.setActionCommand("3");
                ovalButton.addActionListener(this);
                toolBar.add(ovalButton);
		toolBar.addSeparator();

		filledovalButton=new JButton(new ImageIcon(clr.getResource("resources/images/wb/ovalfill.gif")));
                filledovalButton.setToolTipText("<html>filled<br>oval</html>");
                filledovalButton.setActionCommand("6");
                filledovalButton.addActionListener(this);
                toolBar.add(filledovalButton);
                toolBar.addSeparator();

		
		roundrectButton=new JButton(new ImageIcon(clr.getResource("resources/images/wb/roundrect.gif")));
                roundrectButton.setToolTipText("<html>round<br>rectangle</html>");
                roundrectButton.setActionCommand("4");
                roundrectButton.addActionListener(this);
                toolBar.add(roundrectButton);
		toolBar.addSeparator();

                filledroundrectButton=new JButton(new ImageIcon(clr.getResource("resources/images/wb/fillroundrect.gif")));
                filledroundrectButton.setToolTipText("<html>filled<br>round<br>rectangle</html>");
                filledroundrectButton.setActionCommand("7");
                filledroundrectButton.addActionListener(this);
                toolBar.add(filledroundrectButton);
		toolBar.addSeparator();
		
		texter=new JButton(new ImageIcon(clr.getResource("resources/images/wb/text.png")));
                texter.setToolTipText("text");
                texter.setActionCommand("8");
                texter.addActionListener(this);
                toolBar.add(texter);
		toolBar.addSeparator();
	
		eraser=new JButton(new ImageIcon(clr.getResource("resources/images/wb/eraser.png")));
                eraser.setToolTipText("eraser");
                eraser.setActionCommand("9");
                eraser.addActionListener(this);
		if((ClientObject.getController().getUserRole()).equals("student"))
			eraser.setEnabled(false);		
                toolBar.add(eraser);
                toolBar.addSeparator();
		
		color=new JButton(new ImageIcon(clr.getResource("resources/images/wb/color.png")));
                color.setToolTipText("choose color");
                color.setActionCommand("10");
                color.addActionListener(this);
                toolBar.add(color);
                toolBar.addSeparator();

		JComboBox colChoice = new JComboBox();
                colChoice.setToolTipText("Color");
                colChoice.addItem(new ImageIcon(clr.getResource("resources/images/wb/color/black")));
                colChoice.addItem(new ImageIcon(clr.getResource("resources/images/wb/color/red")));
                colChoice.addItem(new ImageIcon(clr.getResource("resources/images/wb/color/green")));
                colChoice.addItem(new ImageIcon(clr.getResource("resources/images/wb/color/blue")));
                colChoice.addItem(new ImageIcon(clr.getResource("resources/images/wb/color/cyan")));
                colChoice.addItem(new ImageIcon(clr.getResource("resources/images/wb/color/magneta")));
                colChoice.addItem(new ImageIcon(clr.getResource("resources/images/wb/color/yellow")));
                colChoice.addItem(new ImageIcon(clr.getResource("resources/images/wb/color/white")));
		toolBar.add(colChoice);

		desk_share=new JButton("Share Screen",new ImageIcon(clr.getResource("resources/images/user/getscreen.jpeg")));
		desk_share.setToolTipText("stop desktop screen sharing");
		desk_share.setActionCommand("Share-Screen");
		desk_share.addActionListener(this);

		desk_ppt=new JButton("Share PPT",new ImageIcon(clr.getResource("resources/images/user/getscreen.jpeg")));
                desk_ppt.setToolTipText("stop desktop screen sharing");
                desk_ppt.setActionCommand("Instructor_Allow-PPT");
                desk_ppt.addActionListener(this);
		
		if(role.equals("instructor")) {
			toolBar.add(desk_share);
			toolBar.add(desk_ppt);
		}

		mainPanel.add(toolBar,BorderLayout.PAGE_START);
		mainPanel.add(WhiteBoardDraw.getController(),BorderLayout.CENTER);
		JToolBar texttool = new JToolBar("TextToolbar");
		JLabel textLabel = new JLabel("Text : ",JLabel.LEFT);
                texttool.add(textLabel);

                JTextField input_text=new JTextField(20);
                texttool.add(input_text);
		texttool.addSeparator();
		
		JButton Insert = new JButton("Insert");
		Insert.addActionListener(WhiteBoardDraw.getController());
                texttool.add(Insert);
		texttool.addSeparator();
		
                JLabel size = new JLabel("Font-size",JLabel.LEFT);

                fontsize=new JComboBox();
		for(int j=1;j<=50;j++) {
                        fontsize.addItem(Integer.toString(j));
                }
		fontsize.addActionListener(new ActionListener(){
                                                public void actionPerformed(ActionEvent event){
							if((String)fontsize.getSelectedItem()!="Size")
                                                        	font_Size =Integer.parseInt((String)fontsize.getSelectedItem());
                                                }
                                        });

                texttool.add(fontsize);
		texttool.addSeparator();
                JLabel style = new JLabel("Font-Style",JLabel.LEFT);

                f_Name=new JComboBox();
		f_Name.addItem("Style");
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
 		String fontNames[] = ge.getAvailableFontFamilyNames();
                for(int i=0;i< fontNames.length;i++) {
                        f_Name.addItem(fontNames[i]);
                }
		f_Name.addActionListener(new ActionListener(){
                				public void actionPerformed(ActionEvent event){
							if((String)fontsize.getSelectedItem()!="Font-Style")
	                    					font_Name = (String) f_Name.getSelectedItem();
                    				}
            				});
		
		texttool.add(f_Name);

		mainPanel.add(texttool,BorderLayout.SOUTH);

		WhiteBoardDraw.getController().setColorChoice(colChoice);
                WhiteBoardDraw.getController().setTextChoice(f_Name);
                WhiteBoardDraw.getController().setSizeChoice(fontsize);
                WhiteBoardDraw.getController().setData(input_text);;
		mainPanel.revalidate();
		return mainPanel;
	}
	
	public void actionPerformed(ActionEvent ae) {
		String cmd=ae.getActionCommand();
	 	if(cmd.equals("Share-Screen")) {
                        HandRaiseAction.getController().actionONRequest("Share-Screen");
			desk_share.setText("Stop Screen Share");
			desk_share.setToolTipText("Stop desktop screen sharing");
			desk_share.setIcon(new ImageIcon(clr.getResource("resources/images/user/allowscreen.jpeg")));
			desk_share.setActionCommand("Stop-Share-Screen");
                }

		if(cmd.equals("Stop-Share-Screen")){
                        HandRaiseAction.getController().actionONRequest("Instructor_Stop_Allow");
                        desk_share.setText("Screen Share");
			desk_share.setIcon(new ImageIcon(clr.getResource("resources/images/user/getscreen.jpeg")));
			desk_share.setToolTipText("start desktop screen sharing");
                        desk_share.setActionCommand("Share-Screen");

		}

		if(cmd.equals("Instructor_Allow-PPT")) {
                        HandRaiseAction.getController().actionONRequest("Instructor_Allow-PPT");
                        desk_ppt.setText("Stop PPT Share");
                        desk_ppt.setToolTipText("Stop PPT sharing");
                        desk_ppt.setIcon(new ImageIcon(clr.getResource("resources/images/user/allowscreen.jpeg")));
                        desk_ppt.setActionCommand("Stop-Allow-PPT");
                }

                if(cmd.equals("Stop-Allow-PPT")) {
                        HandRaiseAction.getController().actionONRequest("Instructor_Stop_Allow");//available");
                        desk_ppt.setText("PPT Share");
                        desk_ppt.setIcon(new ImageIcon(clr.getResource("resources/images/user/getscreen.jpeg")));
                        desk_ppt.setToolTipText("start desktop PPT sharing");
                        desk_ppt.setActionCommand("Instructor_Allow-PPT");
                }
		
		
		if( (!(cmd.equals("Stop-Allow-PPT"))) && (!(cmd.equals("Instructor_Allow-PPT"))) && (!(cmd.equals("Share-Screen"))) && (!(cmd.equals("Stop-Share-Screen")))){		
			button_value=Integer.parseInt(cmd);
                	WhiteBoardDraw.getController().setFigure(button_value);
		}

        }

	public void mouseClicked(MouseEvent e) { }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}

	public int getButtonValue(){
		return button_value;
	}
}

