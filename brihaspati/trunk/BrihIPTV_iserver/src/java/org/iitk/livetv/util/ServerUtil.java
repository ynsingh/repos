package org.iitk.livetv.util;

/*@(#)ServerUtil.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2012-2013.All Rights Reserved.
 */

import java.util.Random;
import java.util.Vector;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Calendar;

import java.util.List;
import org.apache.torque.util.Criteria;

/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */


public class ServerUtil{
	
	private static ServerUtil obj=null;

   	public static ServerUtil getController() {
        	if(obj==null)
           		obj=new ServerUtil();
        	return obj;
   	}

	/** Generate a session Key */

    	public int generateSessionKey() {
       		int key=new Random().nextInt();
         		if(key<=0) {
                     		do{
                       			key=new Random().nextInt();
                       		} while(key<=0);
                    	}
        	return key;
    	}
}//end of class	
