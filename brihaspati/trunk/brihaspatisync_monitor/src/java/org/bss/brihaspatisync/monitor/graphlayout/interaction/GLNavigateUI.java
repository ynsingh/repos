package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  GLNavigateUI.java
 *      
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import  org.bss.brihaspatisync.monitor.graphlayout.*;
import  java.awt.event.*;
import java.awt.*;


/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *      
 */

public class GLNavigateUI extends TGUserInterface {

    GLPanel glPanel;
    TGPanel tgPanel;

    GLNavigateMouseListener ml;

    TGAbstractDragUI hvDragUI;
    TGAbstractDragUI rotateDragUI;

    DragNodeUI dragNodeUI;
    LocalityScroll localityScroll;
    PopupMenu nodePopup;
    PopupMenu edgePopup;
    Node popupNode;
    Edge popupEdge;

    public GLNavigateUI( GLPanel glp ) {
        glPanel = glp;
        tgPanel = glPanel.getTGPanel();

        localityScroll = glPanel.getLocalityScroll();
        hvDragUI = glPanel.getHVScroll().getHVDragUI();
        rotateDragUI = glPanel.getRotateScroll().getRotateDragUI();
        dragNodeUI = new DragNodeUI(tgPanel);

        ml = new GLNavigateMouseListener();
        setUpNodePopup(glp);
        setUpEdgePopup(glp);
    }

    public void activate() {
        tgPanel.addMouseListener(ml);
    }

    public void deactivate() {
        tgPanel.removeMouseListener(ml);
    }

    class GLNavigateMouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            Node mouseOverN = tgPanel.getMouseOverN();

            if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
                if (mouseOverN == null)
                    hvDragUI.activate(e);
                else
                    dragNodeUI.activate(e);
            }
        }

        public void mouseClicked(MouseEvent e) {
            Node mouseOverN = tgPanel.getMouseOverN();
            if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
                if ( mouseOverN != null) {
                        tgPanel.setSelect(mouseOverN);						
			            glPanel.getHVScroll().slowScrollToCenter(mouseOverN);

                        try {
                            tgPanel.setLocale(mouseOverN, localityScroll.getLocalityRadius());
                        }
                        catch (TGException ex) {
                           System.out.println("Error setting locale");
                            ex.printStackTrace();
                        }                        
                }
            }
        }

        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popupNode = tgPanel.getMouseOverN();
                popupEdge = tgPanel.getMouseOverE();
                if (popupNode!=null) {
                    tgPanel.setMaintainMouseOver(true);
//									nodePopup.show(e.getComponent(), e.getX(), e.getY());
									nodePopup.show(tgPanel, e.getX(), e.getY());
                }
                else if (popupEdge!=null) {
                    tgPanel.setMaintainMouseOver(true);
//									edgePopup.show(e.getComponent(), e.getX(), e.getY());
										edgePopup.show(tgPanel, e.getX(), e.getY());
                  }
                else {
//									glPanel.glPopup.show(e.getComponent(), e.getX(), e.getY());
									glPanel.glPopup.show(tgPanel, e.getX(), e.getY());
                }
            }
					else tgPanel.setMaintainMouseOver(false);
        }

    }

    private void setUpNodePopup( GLPanel glp) {
        nodePopup = new PopupMenu();
				glp.add(nodePopup);
				MenuItem menuItem;

        menuItem = new MenuItem("Expand Node");
        ActionListener expandAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupNode!=null) {
                        tgPanel.expandNode(popupNode);
                    }
										tgPanel.setMaintainMouseOver(false);
										tgPanel.setMouseOverN(null);
										tgPanel.repaint();
                }
            };

        menuItem.addActionListener(expandAction);
        nodePopup.add(menuItem);

        menuItem = new MenuItem("Collapse Node");
        ActionListener collapseAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupNode!=null) {
                        tgPanel.collapseNode(popupNode );
                    }
									tgPanel.setMaintainMouseOver(false);
									tgPanel.setMouseOverN(null);
									tgPanel.repaint();
                }
            };

        menuItem.addActionListener(collapseAction);
        nodePopup.add(menuItem);

        menuItem = new MenuItem("Hide Node");
        ActionListener hideAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupNode!=null) {
                        tgPanel.hideNode(popupNode );
                    }
// JDK11 Change .. because of MenuBecomesInvisible
										tgPanel.setMaintainMouseOver(false);
										tgPanel.setMouseOverN(null);
										tgPanel.repaint();
// JDK11 Change .. because of MenuBecomesInvisible
                }
            };

        menuItem.addActionListener(hideAction);
        nodePopup.add(menuItem);

      menuItem = new MenuItem("Center Node");
      ActionListener centerAction = new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  if(popupNode!=null) {
                      glPanel.getHVScroll().slowScrollToCenter(popupNode);
                  }
// JDK11 Change .. because of MenuBecomesInvisible
                  tgPanel.setMaintainMouseOver(false);
                  tgPanel.setMouseOverN(null);
                  tgPanel.repaint();
// JDK11 Change .. because of MenuBecomesInvisible
              }
          };
      menuItem.addActionListener(centerAction);
      nodePopup.add(menuItem);

    }

    private void setUpEdgePopup( GLPanel glp) {
        edgePopup = new PopupMenu();
				glp.add(edgePopup);
        MenuItem menuItem;

        menuItem = new MenuItem("Hide Edge");
        ActionListener hideAction = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(popupEdge!=null) {
                        tgPanel.hideEdge(popupEdge);
                    }
// JDK11 Change .. because of MenuBecomesInvisible
									tgPanel.setMaintainMouseOver(false);
									tgPanel.setMouseOverN(null);
									tgPanel.repaint();
// JDK11 Change .. because of MenuBecomesInvisible
                }
            };

        menuItem.addActionListener(hideAction);
        edgePopup.add(menuItem);

    }

} // end 
