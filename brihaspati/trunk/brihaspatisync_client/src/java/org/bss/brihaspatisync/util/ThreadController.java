package org.bss.brihaspatisync.util;
/**
 * @(#)ThreadController.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur.
 */

/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a> // modified 2013
 */

public class ThreadController {

	private static boolean threadFlag=false;
	private static boolean reflector_status=false;

	public static void setThreadFlag(boolean value) {
		threadFlag=value;
	}
	
	public static boolean getThreadFlag(){
		return threadFlag;
	}

	public static void setReflectorStatusThreadFlag(boolean value){
                reflector_status=value;
        }

        public static boolean getReflectorStatusThreadFlag(){
                return reflector_status;
        }	
}
