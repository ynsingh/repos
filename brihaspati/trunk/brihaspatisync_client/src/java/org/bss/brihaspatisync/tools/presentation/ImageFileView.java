package org.bss.brihaspatisync.tools.presentation;

/**
 * ImageFileView.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.filechooser.FileView;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjass17@gmail.com">Arvind Pal </a>
 */


public class ImageFileView extends FileView {

	private ImageIcon jpgIcon = Util.createImageIcon("/images/jpgIcon.gif");
    	private ImageIcon pngIcon = Util.createImageIcon("/images/pngIcon.png");
	
	public String getName(File f) {
        	return null; //let the L&F FileView figure this out
    	}
	
    	public String getDescription(File f) {
        	return null; //let the L&F FileView figure this out
    	}

    	public Boolean isTraversable(File f) {
        	return null; //let the L&F FileView figure this out
    	}

    	public String getTypeDescription(File f) {
        	String extension = Util.getExtension(f);
        	String type = null;
		
		if (extension != null) {
            		if (extension.equals(Util.jpeg) ||extension.equals(Util.jpg)) {
				type = "JPEG Image";
            		} else if (extension.equals(Util.png)){
                		type = "PNG Image";
            		}
        	}
       		return type;
    	}

    	public Icon getIcon(File f) {
        	String extension = Util.getExtension(f);
       		Icon icon = null;
		if (extension != null) {
            		if (extension.equals(Util.jpeg) ||extension.equals(Util.jpg)) {
				icon = jpgIcon;
            		} else if (extension.equals(Util.png)) {
                		icon = pngIcon;
            		}
        	}
        	return icon;
    	}
}
