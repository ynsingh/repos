package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  GLEditUI.java
 *    
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 *  */


import  org.bss.brihaspatisync.monitor.graphlayout.*;

import java.awt.*;
import  java.awt.event.*;
import  java.applet.*;
import  java.io.*;
import  java.util.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *      
 *  */


public class GLEditUI extends TGUserInterface {

    /** True when the current UI is active. */
    
    TGPanel tgPanel;
    DragAddUI dragAddUI;
    DragNodeUI dragNodeUI;
    DragMultiselectUI dragMultiselectUI;
    TGAbstractClickUI switchSelectUI;
    TGAbstractDragUI hvDragUI;

    GLEditMouseListener ml;
    GLEditMouseMotionListener mml;

    PopupMenu nodePopup;
    PopupMenu edgePopup;
    PopupMenu backPopup;
    Node popupNode;
    Edge popupEdge;

//    AbstractAction deleteSelectAction;
//    final KeyStroke deleteKey = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);

  // ............

   /** Constructor with TGPanel <tt>tgp</tt>.
     */
    public GLEditUI( TGPanel tgp ) {
        active = false;
        tgPanel = tgp;

        ml = new GLEditMouseListener();
        mml = new GLEditMouseMotionListener();

        dragAddUI = new DragAddUI(tgPanel);
        dragNodeUI = new DragNodeUI(tgPanel);
        dragMultiselectUI = new DragMultiselectUI(tgPanel);
        switchSelectUI = tgPanel.getSwitchSelectUI();

        setUpNodePopup(tgp);
        setUpEdgePopup(tgp);
        setUpBackPopup(tgp);
    }

    public GLEditUI( GLPanel glPanel ) {
        this(glPanel.getTGPanel());
        hvDragUI = glPanel.hvScroll.getHVDragUI();
    }

    public void activate() {
        tgPanel.addMouseListener(ml);
        tgPanel.addMouseMotionListener(mml);
        active = true;
    }

    public void deactivate() {
        if (!active) dragMultiselectUI.deactivate();

        tgPanel.removeMouseListener(ml);
        tgPanel.removeMouseMotionListener(mml);
        active = false;
    }

    class GLEditMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            Node mouseOverN = tgPanel.getMouseOverN();
            Node select = tgPanel.getSelect();


            if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
                if (mouseOverN != null) {
                    if(mouseOverN!=select)
                        dragNodeUI.activate(e);
                    else
                        dragAddUI.activate(e);
                }
                else
                    if(hvDragUI!=null) hvDragUI.activate(e);
            }

        }

        public void mouseClicked(MouseEvent e) {
            if (e.getModifiers() == MouseEvent.BUTTON1_MASK)
                switchSelectUI.activate(e);

        }

        public void mouseReleased(MouseEvent e) {
              if (e.isPopupTrigger()) {
                   popupNode = tgPanel.getMouseOverN();
                   popupEdge = tgPanel.getMouseOverE();
                   if (popupNode!=null) {
                       tgPanel.setMaintainMouseOver(true);
                    nodePopup.show(e.getComponent(), e.getX(), e.getY());
                }
                else if (popupEdge!=null) {
                    tgPanel.setMaintainMouseOver(true);
                    edgePopup.show(e.getComponent(), e.getX(), e.getY());
                }
                else {
                    backPopup.show(e.getComponent(), e.getX(), e.getY());
                }
               }
         }
    }

    class GLEditMouseMotionListener extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e) {
        }
    }

    private void setUpNodePopup( TGPanel tgp ) {
        nodePopup = new PopupMenu();

				// For JDK1.1 Compatibility...
				tgp.add(nodePopup);

				MenuItem menuItem;
        Menu navigateMenu = new Menu("Navigate");

        menuItem = new MenuItem("Delete Node");
        ActionListener deleteNodeAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupNode!=null) {
                        tgPanel.deleteNode(popupNode);
                    }
                }
            };

        menuItem.addActionListener(deleteNodeAction);
        nodePopup.add(menuItem);

        menuItem = new MenuItem("Expand Node");
        ActionListener expandAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupNode!=null) {
                        tgPanel.expandNode(popupNode);
                    }
                }
            };

        menuItem.addActionListener(expandAction);
        navigateMenu.add(menuItem);

        menuItem = new MenuItem("Collapse Node");
        ActionListener collapseAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {                    
                    if(popupNode!=null) {
                        tgPanel.collapseNode(popupNode );
                    }
                }
            };

        menuItem.addActionListener(collapseAction);
        navigateMenu.add(menuItem);
            
        menuItem = new MenuItem("Hide Node");
        ActionListener hideAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Node select = tgPanel.getSelect();
                    if(popupNode!=null) {
                        tgPanel.hideNode(popupNode);
                    }
                }
            };
                               
        menuItem.addActionListener(hideAction);
        navigateMenu.add(menuItem);

        nodePopup.add(navigateMenu);

    }

    private void setUpEdgePopup( TGPanel tgp ) {
        edgePopup = new PopupMenu();

				// JDK1.1 Compatibility
				tgp.add(edgePopup);

        MenuItem menuItem;

        menuItem = new MenuItem("Relax Edge");
        ActionListener relaxEdgeAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupEdge!=null) {
                        popupEdge.setLength(popupEdge.getLength()*4);
                        tgPanel.resetDamper();
                    }
                }
            };
        menuItem.addActionListener(relaxEdgeAction);
        edgePopup.add(menuItem);

        menuItem = new MenuItem("Tighten Edge");
        ActionListener tightenEdgeAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupEdge!=null) {
                        popupEdge.setLength(popupEdge.getLength()/4);
                        tgPanel.resetDamper();
                    }
                }
            };
        menuItem.addActionListener(tightenEdgeAction);
        edgePopup.add(menuItem);

        menuItem = new MenuItem("Delete Edge");
        ActionListener deleteEdgeAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupEdge!=null) {
                        tgPanel.deleteEdge(popupEdge);
                    }
                }
            };
        menuItem.addActionListener(deleteEdgeAction);
        edgePopup.add(menuItem);
	}

    private void setUpBackPopup( TGPanel tgp ) {
        backPopup = new PopupMenu();

			// For JDK1.1 Compatibility...
				tgp.add(backPopup);

				MenuItem menuItem;

        menuItem = new MenuItem("Multi-Select");
        ActionListener multiselectAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dragMultiselectUI.activate(GLEditUI.this);
                }
            };
        menuItem.addActionListener(multiselectAction);
        backPopup.add(menuItem);

        menuItem = new MenuItem("Start Over");
        ActionListener startOverAction = new ActionListener() {
                public void actionPerformed( ActionEvent e ) {
                    tgPanel.clearAll();
                    tgPanel.clearSelect();
                    try {
                        tgPanel.addNode();
                    } catch ( TGException tge ) {
                        System.err.println(tge.getMessage());
                        tge.printStackTrace(System.err);
                    }
                    tgPanel.fireResetEvent();
                    tgPanel.repaint();
                }
            };
        menuItem.addActionListener(startOverAction);
        backPopup.add(menuItem);
    }

} // end 
