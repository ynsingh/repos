package org.bss.brihaspatisync.reflector.network.ppt;

/**
 * DesktopPostSharing.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,ETRG, IIT Kanpur.
 **/


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class PPTUtil {
	
	private static PPTUtil httppostserver=null;
	private org.bss.brihaspatisync.reflector.network.desktop_sharing.BufferImage bufferimage=new org.bss.brihaspatisync.reflector.network.desktop_sharing.BufferImage();
	
	public static PPTUtil getController() throws Exception {
                if(httppostserver==null)
                        httppostserver=new PPTUtil();
                return httppostserver;
        }

	protected org.bss.brihaspatisync.reflector.network.desktop_sharing.BufferImage getBuffer() throws Exception {
		return bufferimage;
  	}
}
