package org.bss.brihaspatisync.tools.presentation;

/**
 * FileChooser.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.util.Vector;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;


import java.awt.Insets;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;


import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.bss.brihaspatisync.network.ftp.FTPClient;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */

public class FileChooser extends JFrame implements ActionListener {

    	private String newline = "\n";
	
	private static FileChooser instcspanel=null;

	private String zipFile="";

        /**
         * Controller for the class.
         */
	/**
        protected  static FileChooser getController(){
                if (instcspanel==null){
                        instcspanel=new FileChooser();
                }
                return instcspanel;
        }
	**/
	private Vector v=new Vector();
	private JFrame frame=null;;
	private JTextArea textarea;
    	private Container con=null;
	private JFileChooser fc;
	private JButton attach=null;
	private JButton upload=null;

	private JScrollPane logScrollPane=null;	
	
	protected FileChooser() {
		super("Upload Presentation");
		con=getContentPane();
		JPanel mainPane=new JPanel();
		mainPane.setLayout(new BorderLayout());

        	textarea = new JTextArea(5,20);
        	textarea.setMargin(new Insets(5,5,5,5));
        	textarea.setEditable(false);
		
        	logScrollPane = new JScrollPane(textarea);
		JPanel bttnPane=new JPanel();
        	attach = new JButton("Attach File");
		upload = new JButton("Create Presentation");
		upload.addActionListener(this);
        	attach.addActionListener(this);
		
		bttnPane.add(attach);
		bttnPane.add(upload);

		mainPane.add(logScrollPane, BorderLayout.CENTER);
        	mainPane.add(bttnPane, BorderLayout.SOUTH);
		con.add(mainPane);
		setVisible(true);
		setSize(300,300);
		setLocation(400,400);	      
    	}

	/**
         * Create the GUI and show it.
         */
	
    	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==attach){
	        	if (fc == null) {
        	    		fc = new JFileChooser();
            			fc.addChoosableFileFilter(new ImageFilter());
            			fc.setAcceptAllFileFilterUsed(false);
	            		fc.setFileView(new ImageFileView());
        		   	fc.setAccessory(new ImagePreview(fc));
				fc.setMultiSelectionEnabled(true);
        		}
	        	int returnVal = fc.showDialog(FileChooser.this,"Attach");
        		if (returnVal == JFileChooser.APPROVE_OPTION) {
            			File file1[] = fc.getSelectedFiles();// getSelectedFile();
				for(int i=0;i<file1.length;i++){
					File file=file1[i];
					if(!file.getAbsolutePath().equals(""))
						v.add(file.getAbsolutePath().toString());	
	            			textarea.append("Attaching file - "+v.size() +" : " +file.getName() + "." + newline);
				}
        		} else {
            			textarea.append("Attachment cancelled by user." + newline);
        		}
	        	textarea.setCaretPosition(textarea.getDocument().getLength());
			fc.setSelectedFile(null);
		}
		if(e.getSource()==upload){
			if(v.size() != 0){
				FTPClient.getController().checkDirectory();	
				File file=new File("temp/presentation");				
				File f=null;	
				for(int i=0;i<v.size();i++){
					f=new File((String)v.get(i));
					try{
	        	       	                copy(f,file);
					}catch(Exception ex1){}
				}
				f=null;
				file=null;
                        	createZip();//sourceFile.toString());//,file.toString());
				setVisible(false);
			}
			else{
			 	JOptionPane.showMessageDialog(null,"Please Attach atleast one image file to console !!");
	
			}	
		}	
    	}
	
	private void copy(File src, File dst) throws IOException {

                String str[]=dst.list();
                File descfile=new File(dst.toString()+"/image"+str.length+".png");
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(descfile);
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                }
                in.close();
                out.close();
        }
																	
	private void createZip(){
		try
                {
                        File inFolder=new File("temp/presentation/");
                        File outFolder=new File("temp/presentation.zip");
                        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
                        BufferedInputStream in = null;
                        byte[] data    = new byte[1000];
                        String files[] = inFolder.list();
                        for (int i=0; i<files.length; i++)
                        {
                                in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);
                                out.putNextEntry(new ZipEntry(files[i]));
                                int count;
                                while((count = in.read(data,0,1000)) != -1){
                                        out.write(data, 0, count);
                                }
                                out.closeEntry();
                        }
                        out.flush();
                        out.close();
                }catch(Exception e) { e.printStackTrace();}

	}
}
