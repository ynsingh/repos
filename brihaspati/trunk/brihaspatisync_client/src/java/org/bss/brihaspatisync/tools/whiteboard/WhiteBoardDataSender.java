package org.bss.brihaspatisync.tools.whiteboard;

/**
 * WhiteBoardDataSender.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import org.bss.brihaspatisync.network.util.UtilObject;
import org.bss.brihaspatisync.network.Log;



/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */


public class WhiteBoardDataSender {

	private int m=0,p=0,q=0,r=0,s=0,col=0;
    	private String textname1="";
    	private int size1=0;
    	private String msgdata1="";
    	private static short sendSeq=0;
    	private int SSRC=0;
    	private String message="";
	private UtilObject utilObject=UtilObject.getController();
	private Log log=Log.getController();


	/*Constructor to initialise the value */

        protected WhiteBoardDataSender(int col,int m,int p,int q,int r,int s,String textname1,int size1,String msgdata1){
        	this.col=col;
                this.m=m;
                this.p=p;
                this.q=q;
                this.r=r;
                this.s=s;
                this.textname1=textname1;
                this.size1=size1;
                this.msgdata1=msgdata1;
    	}

        protected String sendpacket() {
        	String msg="";
                msg=sendUnicastPacket();
             	return msg;
         }

	/**
	 * Send the whiteboard payload to the TCP sender queue
	 */

        private String sendUnicastPacket() {
		int i,length;
            	try {
                   	StringBuffer msg=new StringBuffer(100);
			//msg=msg.append(InetAddress.getLocalHost().getHostAddress());
                        //msg=msg.append("#");
			msg=msg.append("wb");
                        msg=msg.append("$");
                   	msg=msg.append(Integer.toString(col));
                    	msg=msg.append("$");
                    	msg=msg.append(Integer.toString(m));
                    	msg=msg.append("$");
                    	msg=msg.append(Integer.toString(p));
                    	msg=msg.append("$");
                    	msg=msg.append(Integer.toString(q));
                    	msg=msg.append("$");
                    	msg=msg.append(Integer.toString(r));
                    	msg=msg.append("$");
                    	msg=msg.append(Integer.toString(s));
                    	msg=msg.append("$");
                    	msg=msg.append(textname1);
                    	msg=msg.append("$");
                    	msg=msg.append(Integer.toString(size1));
                    	msg=msg.append("$");
                    	msg=msg.append(msgdata1);
                  	message=msg.toString();
			utilObject.setSendQueue(message);
			//TCPSender.getController().getSendQueue().putString(message);			
              	} catch(Exception e) { log.setLog("Error On WhiteBoardDataSender"+e.getMessage()); }
      		return message;
     	}
}




