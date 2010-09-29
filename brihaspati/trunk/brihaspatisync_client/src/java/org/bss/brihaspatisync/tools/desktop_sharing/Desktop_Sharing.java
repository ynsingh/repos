package org.bss.brihaspatisync.tools.desktop_sharing;

/**
 * DesktopSharing.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2010 ETRG, IIT Kanpur.
 */

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class Desktop_Sharing extends javax.swing.JPanel {
	
	private String LogfilePath="";
	
	private java.awt.Image image=null;
		
	private java.awt.Dimension area=new java.awt.Dimension(200,300);
	
        private static Desktop_Sharing desktopSharing=null;
	
	public static Desktop_Sharing getController(){
                if (desktopSharing==null){
                        desktopSharing=new Desktop_Sharing();
                }
                return desktopSharing;
        }
	
	protected Desktop_Sharing(){ 
                java.io.File existingFile=new java.io.File("");
		LogfilePath = existingFile.getAbsolutePath();
		setBackground(java.awt.Color.white); 
	}
		
	public void update(java.awt.Graphics g) {
                paint(g);
        }

        public void paint(java.awt.Graphics g) {
		if(image==null) {
                	g.setColor(java.awt.Color.white);
                        g.fillRect(0,0,image.getWidth(this),image.getHeight(this));
             	} if(image != null){
                	g.drawImage(image, 0, 0,image.getWidth(this),image.getHeight(this),this);
           	}
        }
	
	public void runDesktopSharing(){
                try {
			String str=LogfilePath+"/"+"saved.png";
			str=str.trim();
                        image = javax.imageio.ImageIO.read(new java.io.File(str));
                        area.width=image.getWidth(this);
                        area.height=image.getHeight(this);
                        desktopSharing.setPreferredSize(area);
                        desktopSharing.revalidate();
                        desktopSharing.repaint();
	
                } catch(Exception e){
			org.bss.brihaspatisync.network.Log.getController().setLog(" Error in  Desktop_Sharing  method in runDesktopSharing() in line 85 !!"+e.getMessage() );
		}
        }
}

