package org.bss.brihaspatisync.tools.presentation;
	
/**
 * PreferenceWindow.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2011 ETRG, IIT kanpur.
 */


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.bss.brihaspatisync.network.util.UtilObject;
import org.bss.brihaspatisync.network.ppt_sharing.PostPPTScreen;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a> 
 */
 
public class PresentationPanel extends JPanel implements ActionListener {
	private JLabel label=null;
	private JButton browse=null;
	private JPanel mainPanel=null;
	private JButton slideShow=null;
	private JFileChooser instcspanel=null;
	private static PresentationPanel prePanel=null;
	private UtilObject utilObject=UtilObject.getController();
	
	public static PresentationPanel getController(){
		if (prePanel==null){
			prePanel=new PresentationPanel();
		}
		return prePanel;
	}
	
  	public JPanel createGUI(){
  		mainPanel=new JPanel();
  		mainPanel.setLayout(new BorderLayout());

		TitledBorder title = BorderFactory.createTitledBorder("PPT Upload");
		mainPanel.setBorder(title);


  		JPanel labelPane=new JPanel();
		JPanel bttnPane=new JPanel();
		
	        browse=new JButton("Upload");
               	browse.addActionListener(this);
		
		label = new JLabel();//"Upload .ppt File ");
                labelPane.add(label);
		browse.setEnabled(false);
		slideShow=new JButton("Slide Show");
       	        slideShow.addActionListener(this);
		slideShow.setEnabled(false);
		bttnPane.add(browse);
               	bttnPane.add(slideShow);

		mainPanel.add(labelPane,BorderLayout.CENTER);
		mainPanel.add(bttnPane,BorderLayout.SOUTH);
		return mainPanel;
	}
	
	public void actionPerformed(ActionEvent ae){
                if(ae.getSource()==browse){	
			if (instcspanel == null) {
                                instcspanel = new JFileChooser();
                                org.bss.brihaspatisync.network.ppt_sharing.GetAndPostPPT.getController().checkDirectory();
                        }else {
				int returnVal = instcspanel.showDialog(PresentationPanel.this,"Attach");
				this.revalidate();
                                if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						ClassLoader clr= this.getClass().getClassLoader();
						label.setIcon(new ImageIcon(clr.getResource("resources/images/user/LoadingProgressBar.gif")));	
					}catch(Exception e){System.out.println("--------------------> "+e.getMessage());}
                                        
					File b=instcspanel.getSelectedFile();
					File src=new File(b.getAbsolutePath().toString());
                                        File dst=new File("temp/");
					if(dst.exists()) {
						dst=new File(dst.getAbsolutePath().toString()+"/presentation.ppt");
						try {
							browse.setEnabled(false);
							copy(src,dst);
						}catch(Exception e){System.out.println("Errorrrrrrrrrrrrrrr"+e.getCause());}
					}
                                } else {
                                        label.setText("Attachment cancelled by user.");
                                }
                        }
                        instcspanel.setSelectedFile(null);

                }
		
		if(ae.getSource() == slideShow){
			try {
				/***  button enable /decable *************/
				slideShow.setEnabled(false);
				//JsliderListener.getController().setEnable_Decable(true);
				PresentationViewPanel.getController().setEnable_Decable(true ,true);
				PresentationViewPanel.getController().setSclollEnable_Decable(true);
				
				/************   send frist slide ********************/
                	        PostPPTScreen.getController().start_to_sendppt(0);
			} catch(Exception e){}
			return ;
		}
	}
		
	public void setlabelText(){
		try {
                        ClassLoader clr= this.getClass().getClassLoader();
                        label.setIcon(new ImageIcon(clr.getResource("resources/images/user/accept.png")));
                        slideShow.setEnabled(true);
                }catch(Exception e){System.out.println("--------------------> "+e.getMessage());}
        }
	
	
	public void setEnable_Decable(boolean flag){
		browse.setEnabled(flag);                
        }
	
        private void copy(File src, File dst) throws IOException {
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dst);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                }
                in.close();
                out.close();
                System.out.println("PPT File  Upload Successfully !! ");
        }
}
