package babylon;

/**
 * @(#)babylonServerObject.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 *

import babylon.*;
import java.util.Vector;

/**
 * @author <a href="mailto:arvindjss17@gmail.com">  Arvind Pal  </a>
 * @author <a href="mailto:shaistashekh@gmail.com"> Shaista Bano</a>
 */

class babylonServerObject {
	private static boolean enable=false;	
    	private static Vector server=new Vector();
    	private static babylonServerObject bs = null;
	
	
	protected static babylonServerObject getController(){
    		if(bs==null) {
        		bs = new babylonServerObject();
        	}
		return bs;
        }

	protected void babylonStoreObject(babylonServer object){
		if(enable)
			System.out.println("obj"+object);
		server.add(object);
		if(enable)
			System.out.println("server.length->  "+server.size());
        }

    	protected static void babylonServerObject(){
		try {
			if(enable)
				System.out.println("babylonServerObject()  "+server.size());
			if(server.size()>0) {
				babylonServer bs=(babylonServer)server.get(0);
				bs.shutdown();
			}
		}catch(Exception e){
			System.out.println("Exception in Server Object"+e.getMessage());
		}
	}
}


