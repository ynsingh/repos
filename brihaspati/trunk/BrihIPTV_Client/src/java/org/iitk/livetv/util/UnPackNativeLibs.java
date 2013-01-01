package org.iitk.livetv.util;

/**
 * UnPackNativeLibs.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012, ETRG, IIT Kanpur.
 */

import java.io.File;
import java.io.IOException;

import java.net.*;

import java.util.*;
import java.util.jar.*;

import java.io.IOException;
import java.util.jar.Pack200;
import java.util.jar.Pack200.*;
import java.io.File;
import java.io.FileOutputStream;

import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.Enumeration;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 10Dec2012
 */


public class UnPackNativeLibs {

	private static UnPackNativeLibs nativelib=null;

	public static UnPackNativeLibs getController(){
		if(nativelib==null){
			nativelib=new UnPackNativeLibs();
		}
		return nativelib;
	}
	
	public void unpackJarFile(String jarFilePath , String destPath){
		try{
			java.util.jar.JarFile jar = new java.util.jar.JarFile(jarFilePath);
			java.util.Enumeration enume = jar.entries();
        	      	while (enume.hasMoreElements()) {
              			java.util.jar.JarEntry file = (java.util.jar.JarEntry) enume.nextElement();
              			java.io.File f = new java.io.File(destPath + java.io.File.separator + file.getName());
	              		if (file.isDirectory()) { // if its a directory, create it
        	       			f.mkdir();
                			continue;
            			}
	              		java.io.InputStream is = jar.getInputStream(file); // get the input stream
        	      		java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
              			while (is.available() > 0) {  // write contents of 'is' to 'fos'
                			fos.write(is.read());
	               		}
        	       		fos.close();
                		is.close();
	               }
		}catch(Exception e){System.out.println("Error in unpack native library : "+e.getMessage());}                 
	}
}

