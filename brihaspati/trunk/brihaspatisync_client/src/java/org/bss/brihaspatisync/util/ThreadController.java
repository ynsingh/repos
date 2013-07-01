package org.bss.brihaspatisync.util;
/**
 * @(#)ThreadController.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT Kanpur.
 */

/**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class ThreadController {

	private static ThreadController tc=null;
	private boolean threadFlag=false;
	private boolean reflector_status=false;

	public static ThreadController getController(){
		if(tc==null){
			tc=new ThreadController();
		}
		return tc;
	}

	public void setThreadFlag(boolean value) {
		this.threadFlag=value;
	}
	
	public boolean getThreadFlag(){
		return this.threadFlag;
	}

	public void setReflectorStatusThreadFlag(boolean value){
                this.reflector_status=value;
        }

        public boolean getReflectorStatusThreadFlag(){
                return this.reflector_status;
        }	
}
