package org.bss.brihaspatisync.reflector.network.ftp;

/**
 * FTPServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

import org.bss.brihaspatisync.reflector.network.tcp.MaintainLog;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class FTPServer implements Runnable{
	
        private static FTPServer ftpserver=null;
        private Socket sock=null;
        private ServerSocket serv=null;
        private Thread runner=null;
	private boolean flag=false;
	private MaintainLog log=MaintainLog.getController();

        public static FTPServer getController(){
                if(ftpserver==null)
                        ftpserver=new FTPServer();
                return ftpserver;
        }

        private FTPServer(){
                try{
                        serv=new ServerSocket(5217);
                        log.setString("FTP Server Started on Port Number 5217");
                }catch(Exception e){log.setString("Error on Creating Socket for FTP==>"+e.getMessage());}

        }

        public void startThread(){
                if(runner==null){
			flag=true;
                        runner=new Thread(this);
                        runner.start();
                }

        }

        public void stopThread(){
                if(runner!=null){
			flag=false;
                        runner.interrupt();
                        runner.stop();
                }
        }
	
        public void run(){
                while(flag){
                        try{
                                sock=serv.accept();
                                synchronized(sock){
					new FTPHandlers(sock).start(); 
                                }
                        }catch(Exception ex){log.setString("Error at FTPServer Thread=>"+ex.getMessage());}
                }
        }

        public Thread getRunner(){
                return this.runner;
        }
}
