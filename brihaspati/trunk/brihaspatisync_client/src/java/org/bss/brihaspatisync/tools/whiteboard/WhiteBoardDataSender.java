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

    	private String message="";
	private UtilObject utilObject=UtilObject.getController();

	/*Constructor to initialise the value */
	protected WhiteBoardDataSender(){}


	/**
	 * Send the whiteboard payload to the HTTP sender queue
	 */

        protected void sendUnicastPacket(int col,int m,int p,int q,int r,int s,String textname1,int size1,String msgdata1) {
            	try {
                   	StringBuffer msg=new StringBuffer(100);
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
                  	message=java.net.URLEncoder.encode(msg.toString());
			java.util.LinkedList sendqueue=utilObject.getSendQueue("Chat_Wb_Data");
                        sendqueue.addLast(message.getBytes());
              	} catch(Exception e) { System.out.println("Error On WhiteBoardDataSender"+e.getMessage()); }
     	}
}




