package org.bss.brihaspatisync.tools.presentation;

/**
 * ImageFilter.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */


public class ImageFilter extends FileFilter {
	
	public boolean accept(File f) {
        	if (f.isDirectory()) {
            		return true;
        	}
		String extension = Util.getExtension(f);
        	if (extension != null) {
                	if(extension.equals(Util.jpeg) ||extension.equals(Util.jpg) ||extension.equals(Util.png)) {
				return true;
            		} else {
                		return false;
            		}
        	}
		return false;
    	}
	public String getDescription() {
        	return "Images";
    	}
}
