package org.bss.brihaspatisync.tools.chat;

/**
 * ChatPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG,IIT Kanpur
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JColorChooser;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import org.bss.brihaspatisync.gui.Language;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> View and modify for chat panel 
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a> Modify for multilingual implementation. 
 */

public class ChatPanel extends JPanel implements ActionListener,KeyListener,MouseListener {

 	private JPanel mainPanel;
        private JPanel south_mainPanel;
        private JPanel center_mainPanel;
	private JButton save;
	private BlinkLabel textLabel;
	private JTextField input_text;
	private JTextArea textArea;
	private JToolBar toolbar;
	private static ChatPanel chatPanel=null;
	private JScrollPane scrollpane=null;
	private UtilObject utilObject=UtilObject.getController();

	/**
	 * Controller for class
 	 */
	public static ChatPanel getController(){
                if (chatPanel==null){
                        chatPanel=new ChatPanel();
                }
                return chatPanel;
        }

	/** 
	 * Create GUI for Chat Panel
	 */
	public JPanel createGUI(){

		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());

		textArea = new JTextArea(8,60);
                textArea.setEditable(false);
		textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setBackground(new Color(240,248,255));
		scrollpane = new JScrollPane(textArea);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	     	scrollpane.setAutoscrolls(true);
		mainPanel.add(scrollpane,BorderLayout.CENTER);

		toolbar=new JToolBar("ChatTool"); 
		
		south_mainPanel=new JPanel();
		textLabel = new BlinkLabel(Language.getController().getLangValue("ChatPanel.Label1"));
                input_text=new JTextField(20);
		input_text.addMouseListener(this);
		input_text.addKeyListener(this);
                
		save=new JButton(Language.getController().getLangValue("ChatPanel.SaveBttn"));
		save.addActionListener(this);
		toolbar.add(save);
                toolbar.add(textLabel);
                toolbar.add(input_text);
		mainPanel.add(toolbar,BorderLayout.SOUTH);

		return mainPanel;
	}

	 public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==save){
                        FileOutputStream fileStream = null;
			JFileChooser c = new JFileChooser();
   			int rVal = c.showSaveDialog(this);
      			if (rVal == JFileChooser.APPROVE_OPTION) {
				try {
                                	File file = c.getSelectedFile();
                                	fileStream = new FileOutputStream(file);
                                	byte[] bytes = textArea.getText().getBytes();
                                	fileStream.write(bytes);
                        	} catch (IOException F) {System.out.println("Error on svae chat==>"+F.getMessage());}
				return;
      			}
      			if (rVal == JFileChooser.CANCEL_OPTION) {
        			return;	
      			}
		}


	}

	public void showChatMSG(String msg){
		
		Font f=new Font("Courier",Font.BOLD,14);
                textArea.setFont(f);
                textArea.append(msg+System.getProperty("line.separator"));
		textArea.setCaretPosition(textArea.getDocument().getLength());

	}

	// Adding this method for blinking thread 
	public void showMsg(String data){
		Font f=new Font("Courier",Font.BOLD,14);
                textArea.setFont(f);
                textArea.append(data+System.getProperty("line.separator"));
                textArea.setCaretPosition(textArea.getDocument().getLength());
		textLabel.setBlinking(true);
	} 
        // end of method  modification

	public void keyPressed(KeyEvent e){
		input_text.setFocusable(true);
		String send_msg="";
		String msg;
      		int   i, length;
      		JTextField tf;	
      		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			try {
	         		tf = (JTextField)e.getSource();
				msg = java.net.URLDecoder.decode(ClientObject.getController().getUserName(),"UTF-8")+" : "+tf.getText();
				if (tf.getText().length() == 0)  return;
         			else {
					showChatMSG(msg);
        	                	StringBuffer sb=new StringBuffer(100);
			                sb=sb.append("ch");
                			sb=sb.append("$");
		                	sb=sb.append(msg);
                		        send_msg=sb.toString();
		              		utilObject.setSendQueue(send_msg);
	               		}
                        }catch(Exception ex){}
             		input_text.setText("");
		}
      	}

   	public void keyTyped(KeyEvent e){
		textLabel.setBlinking(false);
	}
   	public void keyReleased(KeyEvent e){}
	public void mouseClicked(MouseEvent me) {
		textLabel.setBlinking(false);
	}
	public void mouseReleased(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
        // end of addition
}         

