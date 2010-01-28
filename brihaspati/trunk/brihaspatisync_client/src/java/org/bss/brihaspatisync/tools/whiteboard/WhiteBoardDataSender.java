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
	private Log log=Log.getController();
	private static WhiteBoardDataSender wb_draw=null;
	private UtilObject utilObject=UtilObject.getController();


        public static WhiteBoardDataSender getController(){
                if(wb_draw==null){
                        wb_draw=new WhiteBoardDataSender();
                }
                return wb_draw;
        }

	/*Constructor to initialise the value */
	private WhiteBoardDataSender(){}


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
                  	message=msg.toString();
			utilObject.setSendQueue(message);
              	} catch(Exception e) { log.setLog("Error On WhiteBoardDataSender"+e.getMessage()); }
     	}
}




