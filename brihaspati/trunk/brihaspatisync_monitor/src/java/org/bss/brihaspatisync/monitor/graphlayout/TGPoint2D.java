package org.bss.brihaspatisync.monitor.graphlayout;

/**
 * TGPoint2D.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */

public class TGPoint2D {

    public double x,y;
    
    public TGPoint2D( double xpos, double ypos ) {
        x=xpos;
        y=ypos;
    }

    public TGPoint2D( TGPoint2D p ) {
        x=p.x;
        y=p.y;
    }
    
    public void setLocation( double xpos,double ypos ) {
        x=xpos;
        y=ypos;
    }
    
    public void setX( double xpos ) { x=xpos; }
    public void setY( double ypos ) { y=ypos; }

} // end 
