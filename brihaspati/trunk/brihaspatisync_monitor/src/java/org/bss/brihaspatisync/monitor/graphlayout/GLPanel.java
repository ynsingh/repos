package org.bss.brihaspatisync.monitor.graphlayout;
/**
 *  GLPanel.java
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2011, ETRG, IIT Kanpur.
 */

import  java.awt.*;
import  java.awt.event.*;
import  java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Enumeration;
import java.net.URL;
import java.io.InputStreamReader;

import org.bss.brihaspatisync.monitor.graphlayout.interaction.*;
import org.bss.brihaspatisync.monitor.graphlayout.graphelements.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 */

public class GLPanel extends Panel {


    public HVScroll hvScroll;
    public ZoomScroll zoomScroll;
    public HyperScroll hyperScroll; // unused
    public RotateScroll rotateScroll;
    public LocalityScroll localityScroll;
    public PopupMenu glPopup;
    public Hashtable scrollBarHash; //= new Hashtable();

    protected TGPanel tgPanel;
    protected TGLensSet tgLensSet;
    protected TGUIManager tgUIManager;

    private Scrollbar currentSB =null;


  private Color defaultBackColor = new Color(0x01,0x11,0x44);
  private Color defaultBorderBackColor = new Color(0x02,0x35,0x81);
  private Color defaultForeColor = new Color((float)0.95,(float)0.85,(float)0.55);

   /** Default constructor. */
    public GLPanel() {
        this.setBackground(defaultBorderBackColor);
        this.setForeground(defaultForeColor);
        scrollBarHash = new Hashtable();
        tgLensSet = new TGLensSet();
        tgPanel = new TGPanel();
        tgPanel.setBackColor(defaultBackColor);
        hvScroll = new HVScroll(tgPanel, tgLensSet);
        zoomScroll = new ZoomScroll(tgPanel);
		hyperScroll = new HyperScroll(tgPanel);
        rotateScroll = new RotateScroll(tgPanel);
        localityScroll = new LocalityScroll(tgPanel);
        initialize();
    }

   /** Initialize panel, lens, and establish a random graph as a demonstration.
     */
    public void initialize() {
        buildPanel();
        buildLens();
        tgPanel.setLensSet(tgLensSet);
        addUIs();
        try {
		randomGraph();
         }catch ( TGException tge ) {
            System.err.println(tge.getMessage());
            tge.printStackTrace(System.err);
        }
        setVisible(true);
    }

    /** Return the TGPanel used with this GLPanel. */
    public TGPanel getTGPanel() {
        return tgPanel;
    }

  // navigation .................

    /** Return the HVScroll used with this GLPanel. */
    public HVScroll getHVScroll()
    {
        return hvScroll;
    }

    /** Return the HyperScroll used with this GLPanel. */
    public HyperScroll getHyperScroll()
    {
        return hyperScroll;
    }

    /** Sets the horizontal offset to p.x, and the vertical offset to p.y
      * given a Point <tt>p<tt>.
      */
    public void setOffset( Point p ) {
        hvScroll.setOffset(p);
    };

    /** Return the horizontal and vertical offset position as a Point. */
    public Point getOffset() {
        return hvScroll.getOffset();
    };

  // rotation ...................

    /** Return the RotateScroll used with this GLPanel. */
    public RotateScroll getRotateScroll()
    {
        return rotateScroll;
    }

    /** Set the rotation angle of this GLPanel (allowable values between 0 to 359). */
     public void setRotationAngle( int angle ) {
        rotateScroll.setRotationAngle(angle);
    }

    /** Return the rotation angle of this GLPanel. */
    public int getRotationAngle() {
        return rotateScroll.getRotationAngle();
    }

  // locality ...................

    /** Return the LocalityScroll used with this GLPanel. */
    public LocalityScroll getLocalityScroll()
    {
        return localityScroll;
    }

    /** Set the locality radius of this TGScrollPane
      * (allowable values between 0 to 4, or LocalityUtils.INFINITE_LOCALITY_RADIUS).
      */
    public void setLocalityRadius( int radius ) {
        localityScroll.setLocalityRadius(radius);
    }

    /** Return the locality radius of this GLPanel. */
    public int getLocalityRadius() {
        return localityScroll.getLocalityRadius();
    }

  // zoom .......................

    /** Return the ZoomScroll used with this GLPanel. */
    public ZoomScroll getZoomScroll()
    {
        return zoomScroll;
    }

    /** Set the zoom value of this GLPanel (allowable values between -100 to 100). */
    public void setZoomValue( int zoomValue ) {
        zoomScroll.setZoomValue(zoomValue);
    }

    /** Return the zoom value of this GLPanel. */
    public int getZoomValue() {
        return zoomScroll.getZoomValue();
    }

  // ....

    public PopupMenu getGLPopup()
    {
        return glPopup;
    }

    public void buildLens() {
        tgLensSet.addLens(hvScroll.getLens());
        tgLensSet.addLens(zoomScroll.getLens());
		tgLensSet.addLens(hyperScroll.getLens());
        tgLensSet.addLens(rotateScroll.getLens());
        tgLensSet.addLens(tgPanel.getAdjustOriginLens());
    }

    public void buildPanel() {
        final Scrollbar horizontalSB = hvScroll.getHorizontalSB();
        final Scrollbar verticalSB = hvScroll.getVerticalSB();
        final Scrollbar zoomSB = zoomScroll.getZoomSB();
        final Scrollbar rotateSB = rotateScroll.getRotateSB();
		final Scrollbar localitySB = localityScroll.getLocalitySB();
		final Scrollbar hyperSB = hyperScroll.getHyperSB();

        setLayout(new BorderLayout());

        Panel scrollPanel = new Panel();
        scrollPanel.setBackground(defaultBackColor);
        scrollPanel.setForeground(defaultForeColor);
        scrollPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        Panel modeSelectPanel = new Panel();
        modeSelectPanel.setBackground(defaultBackColor);
        modeSelectPanel.setForeground(defaultForeColor);
        modeSelectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));

        final Panel topPanel = new Panel();
        topPanel.setBackground(defaultBorderBackColor);  //defaultBorderBackColor
        topPanel.setForeground(defaultForeColor);        //defaultForeColo
        topPanel.setLayout(new GridBagLayout());
        c.gridy=0; c.fill=GridBagConstraints.HORIZONTAL;

        c.gridx=0;c.weightx=0;

        c.insets=new Insets(0,0,0,0);
        c.gridy=0;c.weightx=1;

        add(topPanel, BorderLayout.SOUTH);

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridx = 0; c.gridy = 1; c.weightx = 1; c.weighty = 1;
        scrollPanel.add(tgPanel,c);

        c.gridx = 1; c.gridy = 1; c.weightx = 0; c.weighty = 0;

        c.gridx = 0; c.gridy = 2;

        add(scrollPanel,BorderLayout.CENTER);

        glPopup = new PopupMenu();
	      add(glPopup);	// needed by JDK11 Popupmenu..

        MenuItem menuItem = new MenuItem("Toggle Controls");
        ActionListener toggleControlsAction = new ActionListener() {
                boolean controlsVisible = true;
                public void actionPerformed(ActionEvent e) {
                    controlsVisible = !controlsVisible;
                    horizontalSB.setVisible(controlsVisible);
                    verticalSB.setVisible(controlsVisible);
                    topPanel.setVisible(controlsVisible);
                    GLPanel.this.doLayout();
                }
            };
        menuItem.addActionListener(toggleControlsAction);
        glPopup.add(menuItem);
    }

    protected Panel scrollSelectPanel(final String[] scrollBarNames) {
      final Panel sbp = new Panel(new GridBagLayout());
        setSize(800,600);
        setVisible(true);

//    UI: Scrollbarselector via Radiobuttons.................................

      sbp.setBackground(defaultBorderBackColor);
      sbp.setForeground(defaultForeColor);

      Panel firstRow=new Panel(new GridBagLayout());

      final CheckboxGroup bg = new CheckboxGroup();

      int cbNumber = scrollBarNames.length;
      Checkbox checkboxes[] = new Checkbox[cbNumber];

      GridBagConstraints c = new GridBagConstraints();
      c.anchor=GridBagConstraints.WEST;
      c.gridy = 0; c.weightx= 0; c.fill = GridBagConstraints.HORIZONTAL;

      for (int i=0;i<cbNumber;i++) {
      	checkboxes[i] = new Checkbox(scrollBarNames[i],true,bg);
        c.gridx = i;
        firstRow.add(checkboxes[i],c);
      }
      checkboxes[0].setState(true);

      c.gridx=cbNumber;c.weightx=1;
      Label lbl = new Label("     Right-click nodes and background for more options");
      firstRow.add(lbl,c);

      c.anchor = GridBagConstraints.NORTHWEST;
      c.insets=new Insets(1,5,1,5);
      c.gridx = 0; c.gridy = 0; c.weightx = 10;
      c.gridwidth=3;   //Radiobutton UI
      c.gridheight=1;
      c.fill=GridBagConstraints.NONE;
      c.anchor=GridBagConstraints.WEST;
      sbp.add(firstRow,c);

      c.gridy=1;
      c.fill=GridBagConstraints.HORIZONTAL;

      return sbp;
    }

    public void addUIs() {
        tgUIManager = new TGUIManager();
        GLEditUI editUI = new GLEditUI(this);
        GLNavigateUI navigateUI = new GLNavigateUI(this);
        tgUIManager.addUI(editUI,"Edit");
        tgUIManager.addUI(navigateUI,"Navigate");
        tgUIManager.activate("Navigate");
    }

	public void randomGraph() throws TGException {
        Node n1= tgPanel.addNode();
        n1.setType(0);
	int size=tgPanel.ip.size();
        for (int i=1;i<size; i++ ) {  //249
        	 tgPanel.addNode();
    	}

        TGForEachNode fen = new TGForEachNode() {
            public void forEachNode(Node n) {
				for(int i=0;i<5;i++) {
				    Node r = tgPanel.getGES().getFirstNode();   
				    if(r!=n && tgPanel.findEdge(r,n)==null)
					    tgPanel.addEdge(r,n,Edge.DEFAULT_LENGTH);
			    }
			}
		};
		tgPanel.getGES().forAllNodes(fen);
		
        tgPanel.setLocale(n1,1);
        tgPanel.setSelect(n1);
        try {
       	    Thread.currentThread().sleep(2000);
	    System.gc();
        } catch (InterruptedException ex) {}

    }



} // end 
