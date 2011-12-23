package org.bss.brihaspatisync.monitor.util;

/**
* ClientObject.java
* See LICENCE file for usage and redistribution terms
* Copyright (c) 2011
*/

import java.util.Vector;

/**
* @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
*/

public class ClientObject {

        private Vector brihaspati= new  Vector();
        private static ClientObject obj=null;

	/**
 	 * Controller for this class
 	 */  
        public static ClientObject getController(){
                if(obj==null) {
                        obj=new ClientObject();
                }
                return obj;
        }

        public void setbrihaspatiServerAddr(Vector value) {
		brihaspati.clear();	
		brihaspati=value;
        }
	
	public Vector getbrihaspatiServerAddr() {
		return brihaspati;
        }
}
