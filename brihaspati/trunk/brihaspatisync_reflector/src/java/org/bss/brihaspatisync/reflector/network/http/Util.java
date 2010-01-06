package org.bss.brihaspatisync.reflector.network.http;

/**
 * @(#)Util.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009 ETRG, IIT Kanpur.
 */

import java.util.Vector;

/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 */

public class Util {
	
	private static Util util=null;
	
	private Vector ipVectorforClient=new Vector();

	public static Util getController(){
              	if(util==null)
                       	util=new Util();
                return util;
        }
         
        protected void setIp(String ip){
		ipVectorforClient.add(ip);
        }
	
	protected boolean getStatus(String ip){
             	return (ipVectorforClient.contains(ip)) ? false : true ;
        }
}
