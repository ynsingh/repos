package org.bss.brihaspatisync.reflector.network.desktop_sharing;

/**
 * DesktopPostSharing.java
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,ETRG, IIT Kanpur.
 **/


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal  </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class DestopSharingUtil {
	
	private static DestopSharingUtil httppostserver=null;
	private BufferImage bufferimage=new BufferImage();
	
	public static DestopSharingUtil getController() throws Exception {
                if(httppostserver==null)
                        httppostserver=new DestopSharingUtil();
                return httppostserver;
        }

	protected BufferImage getBuffer() throws Exception {
		return bufferimage;
  	}
}
