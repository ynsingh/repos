package org.bss.brihaspatisync.tools.chat;

/**
 * ChatPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009-2010 ETRG,IIT Kanpur
 */
import java.awt.*;
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
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.util.UtilObject;
import org.bss.brihaspatisync.network.Log;



/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a>
 */

public class ChatPanel extends JPanel implements ActionListener,KeyListener,MouseListener {

 	private JPanel mainPanel;
        private JPanel south_mainPanel;
        private JPanel center_mainPanel;
	private JButton save;
	private JTextField input_text;
	private JTextArea textArea;
	private JToolBar toolbar;
	private UtilObject utilObject=UtilObject.getController();
	private static ChatPanel chatPanel=null;
	private Log log=Log.getController();
	private JScrollPane scrollpane=null;
	int i=0;
	Thread runner=null;
	boolean flag=false;

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
		JLabel textLabel = new JLabel("Enter Text : ",JLabel.LEFT);
                //south_mainPanel.add(textLabel);
                toolbar.add(textLabel);

                input_text=new JTextField(20);
		input_text.addMouseListener(this);
		input_text.addKeyListener(this);
		//south_mainPanel.add(input_text);
                toolbar.add(input_text);
                
		save=new JButton("Save");
		save.addActionListener(this);
		//south_mainPanel.add(save);
		toolbar.add(save);
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
                        	} catch (IOException F) {log.setLog("Error on svae chat==>"+F.getMessage());}
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

	//Modified by pratibha
	// Adding this method for blinking thread 
	public void showMsg(String data){
		Font f=new Font("Courier",Font.BOLD,14);
                textArea.setFont(f);
                textArea.append(data+System.getProperty("line.separator"));
                textArea.setCaretPosition(textArea.getDocument().getLength());
		input_text.setFocusable(false);
		runner=new Thread(new Runnable(){
				public void run(){
					if(flag==false)
						flag=true;
					while(flag){
						try{
							if((i%2)==0){
								toolbar.setBackground(new Color(255,228,225));//Color.red);
								repaint();
								runner.sleep(500);
								i++;
							}else{
								toolbar.setBackground(new Color(245,245,245));//.white);
								repaint();
								runner.sleep(500);
								i++;
							}
						}catch(InterruptedException ie){continue;}
					}
				}
			}, "BlinkThread");
		runner.start();
	} 
        // end of method  modification

	public void keyPressed(KeyEvent e){
		input_text.setFocusable(true);
		String send_msg="";
		String msg;
      		int   i, length;
      		JTextField tf;	
      		if (e.getKeyCode() == KeyEvent.VK_ENTER){
         		tf = (JTextField)e.getSource();
         		msg = ClientObject.getController().getUserName()+" : "+tf.getText();
			if (tf.getText().length() == 0)  return;
         		else {
				showChatMSG(msg);
				try{
                        		StringBuffer sb=new StringBuffer(100);
		                        sb=sb.append("ch");
                		        sb=sb.append("$");
		                        sb=sb.append(msg);
                		        send_msg=sb.toString();
                		        utilObject.setSendQueue(send_msg);
                        	}catch(Exception ex){}

	               	}
             		input_text.setText("");
		}
      	}

	// Added By pratibha
   	public void keyTyped(KeyEvent e){
		if(i>0){
			try{
				if(runner==null){
					System.out.println("thread is going to stop:");
					if(flag==true){

						flag=false;
						runner.interrupt();
						runner=null;
						toolbar.setBackground(new Color(245,245,245));//.white);
						repaint();
						i=0;
					}
				}
			}catch(Exception ex){System.out.println("---"+ex.getMessage());}
		}
	}
   	public void keyReleased(KeyEvent e){}
	public void mouseClicked(MouseEvent me){
		if(i>0){
                	try{
                        	if(runner!=null){
                                        System.out.println("thread is going to stop:");
                                        if(flag==true){
                                                flag=false;
                                                runner.interrupt();
                                                runner=null;
                                                toolbar.setBackground(new Color(245,245,245));
                                                repaint();
						i=0;
                                        }
                                }
                        }catch(Exception ex){System.out.println("---"+ex.getMessage());}
			input_text.setFocusable(true);
                }
	}
	public void mouseReleased(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
        // end of addition
}         

