package org.bss.brihaspatisync.tools.presentation;

/**
 * Util.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.io.File;
import javax.swing.ImageIcon;
import org.bss.brihaspatisync.network.Log;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */


public class Util {

	protected final  static String jpeg = "jpeg";
    	protected final  static String jpg = "jpg";
    	protected final  static String png = "png";
	private Log log=Log.getController();

    	/**
     	 * Get the extension of a file.
     	 */
	
    	protected static String getExtension(File f) {
        	String ext = null;
        	String s = f.getName();
        	int i = s.lastIndexOf('.');
		if (i > 0 &&  i < s.length() - 1) {
            		ext = s.substring(i+1).toLowerCase();
        	}
        	return ext;
    	}

    	protected static ImageIcon createImageIcon(String path) {
        	java.net.URL imgURL = Util.class.getResource(path);
        	if (imgURL != null) {
            		return new ImageIcon(imgURL);
        	} else {
            		//log.setLog("Couldn't find file: " + path);
            		return null;
        	}
    	}
}
