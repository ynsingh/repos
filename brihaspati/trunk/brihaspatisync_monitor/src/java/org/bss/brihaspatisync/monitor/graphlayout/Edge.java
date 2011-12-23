package org.bss.brihaspatisync.monitor.graphlayout;

/**
 *Edge.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import  java.awt.Color;
import  java.awt.Graphics;
import  java.awt.Dimension;

/**
 * @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 * @date 05/12/2011
 *
 */


public class Edge {

    public static Color DEFAULT_COLOR = Color.decode("#006090");
    public static Color MOUSE_OVER_COLOR = Color.decode("#ccddff");
    public static int DEFAULT_LENGTH = 40;

    public Node from; //Should be private, changing from effects "from" Node
    public Node to;   //Should be private, changing from effects "to" Node
    protected Color col;
    protected int length;
    protected boolean visible;
    protected String id = null;

  // ............

    /** Constructor with two Nodes and a length.
      */
    public Edge(Node f, Node t, int len) {
        from = f;
        to = t;
        length = len;
        col = DEFAULT_COLOR;
        visible = false;
    }

    /** Constructor with two Nodes, which uses a default length.
      */
    public Edge(Node f, Node t) {
        this(f, t, DEFAULT_LENGTH);
    }

   // setters and getters ...............
   
    public static void setEdgeDefaultColor( Color color ) { DEFAULT_COLOR = color; }
    public static void setEdgeMouseOverColor( Color color ) { MOUSE_OVER_COLOR = color; }
    public static void setEdgeDefaultLength( int length ) { DEFAULT_LENGTH = length; }

   /** Returns the starting "from" node of this edge as Node. */
    public Node getFrom() { return from; }
    
   /** Returns the terminating "to" node of this edge as Node. */
    public Node getTo() { return to; }
    
   /** Returns the color of this edge as Color. */
    public Color getColor() {
        return col;
    }

   /** Set the color of this Edge to the Color <tt>color</tt>. */
    public void setColor( Color color ) {
        col = color;
    }

   /** Returns the ID of this Edge as a String. */
    public String getID()
    {
        return id;
    }

   /** Set the ID of this Edge to the String <tt>id</tt>. */
    public void setID( String id )
    {
        this.id=id;
    }

   /** Returns the length of this Edge as a double. */
    public int getLength() {
        return length;
    }

   /** Set the length of this Edge to the int <tt>len</tt>. */
    public void setLength(int len) {
        length=len;
    }

   /** Set the visibility of this Edge to the boolean <tt>v</tt>. */
    public void setVisible( boolean v) {
        visible = v;
    } 

   /** Return the visibility of this Edge as a boolean. */
    public boolean isVisible() {
        return visible;
    } 

    public Node getOtherEndpt(Node n) { //yields false results if Node n is not an endpoint
        if (to != n) return to;
        else return from;
    }

    /** Switches the endpoints of the edge */
    public void reverse() {
        Node temp = to;
        to = from;
        from = temp;
    }

    public boolean intersects(Dimension d) {
        int x1 = (int) from.drawx;
        int y1 = (int) from.drawy;
        int x2 = (int) to.drawx;
        int y2 = (int) to.drawy;

        return (((x1>0 || x2>0) && (x1<d.width  || x2<d.width)) &&
                  ((y1>0 || y2>0) && (y1<d.height || y2<d.height) ));

    }

    public double distFromPoint(double px, double py) {
        double x1= from.drawx;
        double y1= from.drawy;
        double x2= to.drawx;
        double y2= to.drawy;

        if (px<Math.min(x1, x2)-8 || px>Math.max(x1, x2)+8 ||
            py<Math.min(y1, y2)-8 || py>Math.max(y1, y2)+8)
            return 1000;

        double dist = 1000;
        if (x1-x2!=0) dist = Math.abs((y2-y1)/(x2-x1)*(px - x1) + (y1 - py));
        if (y1-y2!=0) dist = Math.min(dist, Math.abs((x2-x1)/(y2-y1)*(py - y1) + (x1 - px)));

        return dist;
    }

    public boolean containsPoint(double px, double py) {
        return distFromPoint(px,py)<10;
    }

    public static void paintArrow(Graphics g, int x1, int y1, int x2, int y2, Color c) {
        //Forget hyperbolic bending for now

        g.setColor(c);

        int x3=x1;
        int y3=y1;

        double dist=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        if (dist>10) {
            double adjustDistRatio = (dist-10)/dist;
            x3=(int) (x1+(x2-x1)*adjustDistRatio);
            y3=(int) (y1+(y2-y1)*adjustDistRatio);
        }

        x3=(int) ((x3*4+x1)/5.0);
        y3=(int) ((y3*4+y1)/5.0);

        g.drawLine(x3,   y3,   x2, y2);
        g.drawLine(x1,   y1,   x3, y3);
        g.drawLine(x1+1, y1,   x3, y3);
        g.drawLine(x1+2, y1,   x3, y3);
        g.drawLine(x1+3, y1,   x3, y3);
        g.drawLine(x1+4, y1,   x3, y3);
        g.drawLine(x1-1, y1,   x3, y3);
        g.drawLine(x1-2, y1,   x3, y3);
        g.drawLine(x1-3, y1,   x3, y3);
        g.drawLine(x1-4, y1,   x3, y3);
        g.drawLine(x1,   y1+1, x3, y3);
        g.drawLine(x1,   y1+2, x3, y3);
        g.drawLine(x1,   y1+3, x3, y3);
        g.drawLine(x1,   y1+4, x3, y3);
        g.drawLine(x1,   y1-1, x3, y3);
        g.drawLine(x1,   y1-2, x3, y3);
        g.drawLine(x1,   y1-3, x3, y3);
        g.drawLine(x1,   y1-4, x3, y3);
    }

    public void paint(Graphics g, TGPanel tgPanel) {
        Color c = (tgPanel.getMouseOverE()==this) ? MOUSE_OVER_COLOR : col;

        int x1=(int) from.drawx;
        int y1=(int) from.drawy;
        int x2=(int) to.drawx;
        int y2=(int) to.drawy;
        if (intersects(tgPanel.getSize())) paintArrow(g, x1, y1, x2, y2, c);
    }

} // end 
