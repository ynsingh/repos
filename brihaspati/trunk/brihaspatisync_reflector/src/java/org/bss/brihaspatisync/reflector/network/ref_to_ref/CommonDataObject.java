package org.bss.brihaspatisync.reflector.network.ref_to_ref;

/**
 * PostSharedScreen.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 **/


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class CommonDataObject {

        private static CommonDataObject obj=null;
	
	private java.util.Hashtable store_reflectorToreflector_ip = new java.util.Hashtable();

	/**
 	 * Controller for this class
 	 */  
        public static CommonDataObject getController(){
                if(obj==null) {
                        obj=new CommonDataObject();
                }
                return obj;
        }
	
	public void get_setStatusCourseId(String sessionid,String ip) throws Exception{
		if(!(store_reflectorToreflector_ip.containsKey(sessionid))){
			ParentReflectorCommunication parentip=new ParentReflectorCommunication();
			store_reflectorToreflector_ip.put(sessionid ,parentip);		
			parentip.start(sessionid,ip);
		}
        }
	
}

