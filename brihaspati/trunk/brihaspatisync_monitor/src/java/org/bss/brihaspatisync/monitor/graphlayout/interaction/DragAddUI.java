package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  DragAddUI.java
 *    
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */


import  org.bss.brihaspatisync.monitor.graphlayout.*;

import  java.io.*;
import  java.util.*;
import  java.awt.*;
import  java.applet.*;

import  java.awt.event.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *      
 *  */


public class DragAddUI extends TGAbstractDragUI implements TGPaintListener {

    Point mousePos = null;
    Node dragAddNode = null;

  // ............

   /** Constructor with provided TGPanel <tt>tgp</tt>.
     */ 
    public DragAddUI( TGPanel tgp ) {
        super(tgp); 
    }

    public void preActivate() {
        mousePos=null;
        tgPanel.addPaintListener(this);
    }

    public void preDeactivate() {
        tgPanel.removePaintListener(this);
    };

    public void mousePressed( MouseEvent e ) {
        dragAddNode = tgPanel.getMouseOverN();
    }    

    public void mouseReleased( MouseEvent e ) {
        Node mouseOverN = tgPanel.getMouseOverN();

        if (mouseOverN!=null && dragAddNode!=null && mouseOverN!=dragAddNode) {
            Edge ed=tgPanel.findEdge(dragAddNode,mouseOverN);
            if (ed==null) tgPanel.addEdge(dragAddNode,mouseOverN, Edge.DEFAULT_LENGTH); 
            else tgPanel.deleteEdge(ed);

        } else if ( mouseOverN == null && dragAddNode != null ) {
            try {
                Node n =tgPanel.addNode(); 
                tgPanel.addEdge(dragAddNode,n, Edge.DEFAULT_LENGTH); 
                n.drawx = tgPanel.getMousePos().x; 
                n.drawy = tgPanel.getMousePos().y;
                tgPanel.updatePosFromDraw(n); 
            } catch ( TGException tge ) {
                System.err.println(tge.getMessage());
                tge.printStackTrace(System.err);
            }
        }

        if (mouseWasDragged) { //Don't reset the damper on a mouseClicked
            tgPanel.resetDamper();
            tgPanel.startDamper();
        }   

        dragAddNode=null;
    }

    public void mouseDragged(MouseEvent e) {    
        mousePos=e.getPoint();
       tgPanel.repaint();
    }

    public void paintFirst(Graphics g) {};
    public void paintLast(Graphics g) {};

    public void paintAfterEdges(Graphics g) {

        if(mousePos==null) return;

        Node mouseOverN = tgPanel.getMouseOverN();

        if (mouseOverN==null) {
//            g.setColor(Node.BACK_DEFAULT_COLOR);
            g.drawRect(mousePos.x-7, mousePos.y-7, 14, 14);
        }

        Color c;
        if (mouseOverN==dragAddNode)
            c = Color.darkGray;
        else
            c = Color.blue;

        Edge.paintArrow(g, (int) dragAddNode.drawx, (int) dragAddNode.drawy,
               mousePos.x, mousePos.y, c);
    }

} // end 
