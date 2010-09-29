package org.bss.brihaspatisync.network.desktop_sharing;

/**
 * LD.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010
 */


public class LD {
	
   	private java.awt.Robot rt = null;
	private java.awt.Toolkit tk = null;
   	private java.awt.Rectangle screenRect;
		
   	protected LD(){
		this.tk = java.awt.Toolkit.getDefaultToolkit();
                this.screenRect = new java.awt.Rectangle(this.tk.getScreenSize());
		try {
			this.rt = new java.awt.Robot();
		}catch(Exception e){}
	}
   	
	protected void CaptureScreenByteArray() {
		try {
                        java.io.File out = new java.io.File("saved.jpeg");
                        javax.imageio.ImageIO.write(this.rt.createScreenCapture(this.screenRect), "jpeg",out);
                } catch (Exception e) {
                        org.bss.brihaspatisync.network.Log.getController().setLog("Exception in catch CaptureScreenByteArray() "+e.getMessage());
                }
   	}
}
