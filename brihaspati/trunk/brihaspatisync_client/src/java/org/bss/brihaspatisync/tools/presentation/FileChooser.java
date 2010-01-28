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

import org.bss.brihaspatisync.network.Log;
import org.bss.brihaspatisync.network.ftp.FTPClient;
/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */

public class FileChooser implements Runnable {

	private Thread runner = null;

        private boolean rec_Flag = false;

	private static FileChooser fc=null;

	private Log log=Log.getController();
	private File src=null;
	private File dst=null;
	private PresentationPanel ftpc=PresentationPanel.getController();
	
	public static FileChooser getController(){
                if (fc==null){
                        fc=new FileChooser();
                }
                return fc;
        }
	
	 /**
         * Start ReceiveQueueHandler Thread.
         */

        public synchronized void start(File src,File dst) throws IOException {
                if(rec_Flag!=true){
                        rec_Flag=true;
                }
                if (runner == null) {
                        runner = new Thread(this);
                        runner.start();
			this.dst=dst;
			this.src=src;
                        log.setLog("FileChooser is Start");
                }
        }

        /**
         * Stop receiveQueueHandler Thread.
         */
        private synchronized void stop() {
                if(rec_Flag!=false){
                        rec_Flag=false;
                }
                if (runner != null) {
                        runner.stop();
                        runner = null;
			this.dst=null;
                        this.src=null;
                        log.setLog("FileChooser is Stop");
                }
        }
	
	public void run(){
                while(rec_Flag){
                        try{
				//label.setText("Uploading process ---");
				ftpc.setlabelText("Uploading process -----");	
                        	dst=new File("temp/presentation.ppt");
                               	dst=new File(dst.getAbsolutePath().toString());
                               	createcopy(src,dst);
			}catch(Exception e){}
                }
        }
		
		
	private void createcopy(File src, File desc){
                try {
			ftpc.setlabelText("Uploading process ---");
                        copy(src,desc);
			ftpc.setlabelText("Uploading process -----");
                }catch(Exception e) { 
			e.printStackTrace();
		}
        }

	private void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dst);
		// Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                	out.write(buf, 0, len);
                }
                in.close();
                out.close();	
		ftpc.setlabelText("Uploading process ---");
    		FTPClient.getController().startFTPClient("POST");
		ftpc.setlabelText("Uploading process -----");
		PresentationPanel.getController().stopTimer(10000);
		System.out.println("PPT File  Upload Successfully !! ");	
		FTPClient.getController().createppt_TO_Images();
		stop();
        }
}
