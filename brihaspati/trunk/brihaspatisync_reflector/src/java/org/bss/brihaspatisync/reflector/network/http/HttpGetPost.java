package org.bss.brihaspatisync.reflector.network.http;

/**
 * @(#)HttpServer.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011-2013 ETRG, IIT Kanpur.
 */

import org.bss.brihaspatisync.reflector.util.RuntimeObject;
import org.bss.brihaspatisync.reflector.buffer_mgt.BufferMgt;
import org.bss.brihaspatisync.reflector.network.serverdata.UserListUtil;
import org.bss.brihaspatisync.reflector.network.serverdata.HandraiseAction;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>Created a basic multithreaded HttpServer on 05Feb2011, 31Dec2012
 */

public class HttpGetPost {
	
	private static HttpGetPost httpserver=null;
	private HandraiseAction handraiseAction=new HandraiseAction();
	
	public static HttpGetPost getController() throws Exception {
              	if(httpserver==null)
                       	httpserver=new HttpGetPost();
                return httpserver;
        }
	
	public void putValue_CH_WB(byte[] bytes,String lecture_id,String username) {
		try {
			/**
 			 ** where data_value[0] contains contain data and data_value[1] contains parent ip .
                         */

			String req=new String(bytes);		
                        String data_value[]=req.split("req");
                        RuntimeObject runtimeObject=RuntimeObject.getController();
                        runtimeObject.setMastrerReflecterCourseid(lecture_id);
			if(data_value[0].startsWith("HandRaiseAction")){
                               	data_value[0]=java.net.URLDecoder.decode(data_value[0]).replaceAll("HandRaiseAction","");
	                        handraiseAction.setValue(data_value[0]);
			}
			
			/** set All course id */
                        //runtimeObject.setCourseID(lecture_id);
                        if(runtimeObject.getStatusCourseidIP(lecture_id+"#"+username)) {
                               	/** set course id and ip */
                                runtimeObject.setCourseid_IP(lecture_id+"#"+username);
                        }
                }catch(Exception ex){System.out.println("Error in in http post and get server "+ex.getMessage());}
        }
}

