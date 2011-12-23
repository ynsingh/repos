package org.bss.brihaspatisync.monitor.graphlayout;

/**
 * TGPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

import org.bss.brihaspatisync.monitor.*;
import  org.bss.brihaspatisync.monitor.graphlayout.interaction.*;
import  org.bss.brihaspatisync.monitor.graphlayout.graphelements.*;

import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */


public class TGPanel extends Panel {

  // static variables for use within the package

    public static Color BACK_COLOR = Color.white;

  // ....

    private GraphEltSet completeEltSet;
    private VisibleLocality visibleLocality;
    private LocalityUtils localityUtils;
    public TGLayout tgLayout;
    protected BasicMouseMotionListener basicMML;

    protected Edge mouseOverE;  //mouseOverE is the edge the mouse is over
    protected Node mouseOverN;  //mouseOverN is the node the mouse is over
    protected boolean maintainMouseOver = false; //If true, then don't change mouseOverN or mouseOverE

    protected Node select;

    Node dragNode; //Node currently being dragged

    protected Point mousePos; //Mouse location, updated in the mouseMotionListener
    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;

    private Vector graphListeners;
    private Vector paintListeners;
    int count=0;
    public Vector ip=new Vector();
    static String refIP;

    public Vector load;

    TGLensSet tgLensSet;  // Converts between a nodes visual position (drawx, drawy),
                          // and its absolute position (x,y).
    AdjustOriginLens adjustOriginLens;
    SwitchSelectUI switchSelectUI;


   /** Default constructor.
     */
    public TGPanel()
    {
        setLayout(null);

        setGraphEltSet(new GraphEltSet());
        addMouseListener(new BasicMouseListener());
        basicMML = new BasicMouseMotionListener();
        addMouseMotionListener(basicMML);

        graphListeners=new Vector();
        paintListeners=new Vector();

        adjustOriginLens = new AdjustOriginLens();
        switchSelectUI = new SwitchSelectUI();

        TGLayout tgLayout = new TGLayout(this);
        setTGLayout(tgLayout);
        tgLayout.start();
        setGraphEltSet(new GraphEltSet());

    }

    public void setLensSet( TGLensSet lensSet ) {
        tgLensSet = lensSet;
    }

  	public void setTGLayout( TGLayout tgl ) {
    	  tgLayout = tgl;
  	}

    public void setGraphEltSet( GraphEltSet ges ) {
        completeEltSet = ges;
        visibleLocality = new VisibleLocality(completeEltSet);
        localityUtils = new LocalityUtils(visibleLocality, this);
    }

    public AdjustOriginLens getAdjustOriginLens() {
        return adjustOriginLens;
    }

    public SwitchSelectUI getSwitchSelectUI() {
        return switchSelectUI;
    }

  // color and font setters ......................

    public void setBackColor( Color color ) { BACK_COLOR = color; }

  // Node manipulation ...........................

    /** Returns an Iterator over all nodes in the complete graph. */
/*
    public Iterator getAllNodes() {
        return completeEltSet.getNodes();
    }
*/

    /** Return the current visible locality. */
    public ImmutableGraphEltSet getGES() {
        return visibleLocality;
    }

    /** Returns the current node count. */
    public int getNodeCount() {
        return completeEltSet.nodeCount();
    }

    /** Returns the current node count within the VisibleLocality.
      * @deprecated        this method has been replaced by the <tt>visibleNodeCount()</tt> method.
      */
    public int nodeNum() {
        return visibleLocality.nodeCount();
    }

    /** Returns the current node count within the VisibleLocality. */
    public int visibleNodeCount() {
        return visibleLocality.nodeCount();
    }

  /** Return the Node whose ID matches the String <tt>id</tt>, null if no match is found.
    *
    * @param id The ID identifier used as a query.
    * @return The Node whose ID matches the provided 'id', null if no match is found.
    */
  public Node findNode( String id ) {
      if ( id == null ) return null; // ignore
      return completeEltSet.findNode(id);
  }

  /** Return the Node whose URL matches the String <tt>strURL</tt>, null if no match is found.
    *
    * @param strURL The URL identifier used as a query.
    * @return The Node whose URL matches the provided 'URL', null if no match is found.
    */
  public Node findNodeByURL( String strURL ) {
      if ( strURL == null ) return null; // ignore
      return completeEltSet.findNodeByURL(strURL);
  }


   /** Return the first Nodes whose label contains the String <tt>substring</tt>,
     * null if no match is found.
     * @param substring The Substring used as a query.
     */
    public Node findNodeLabelContaining( String substring ) {
        if ( substring == null ) return null; //ignore
        return completeEltSet.findNodeLabelContaining(substring);
    }

   /** Adds a Node, with its ID and label being the current node count plus 1.
     * @see com.touchgraph.graphlayout.Node
     */
    	public void getSelectedRef(String str){
		refIP=str;
        }

	public Vector getLoad(){
            load=ReflectorManager.getController().getLoadOnReflector(refIP);
	    return load;
	}

	public String getIP(){
      	    Vector peerIP=new Vector();
	    peerIP.clear();
	    Vector courses=new Vector();
	    courses.clear();
	    courses=ReflectorManager.getController().getReflectorCourses(refIP);
	    for(int i=0;i<courses.size();i++){
	     	RegisterToIndexServer.getController().connectGetLectureXML(courses.elementAt(i).toString());	
		peerIP=ReflectorManager.getController().getPeerValue(courses.elementAt(i).toString());
	    }
	    ip.clear();  
	    ip.add(refIP);
	    for(int i=0;i<peerIP.size();i++)
	    ip.add(peerIP.elementAt(i).toString());
	    return ip.elementAt(count).toString();
	}
    public Node addNode() throws TGException {
        String id = String.valueOf(getNodeCount()+1);
        String label=String.valueOf(getIP());
	count++;
        return addNode(id,label);
    }
   /** Adds a Node, provided its label.  The node is assigned a unique ID.
     * @see com.touchgraph.graphlayout.graphelements.GraphEltSet
     */
    public Node addNode( String label ) throws TGException {
        return addNode(null,label);
    }

   /** Adds a Node, provided its ID and label.
     * @see com.touchgraph.graphlayout.Node
     */
    public Node addNode( String id, String label ) throws TGException {
        Node node;
        if (label==null)
            node = new Node(id);
        else
            node = new Node(id, label);

        updateDrawPos(node); // The addNode() call should probably take a position, this just sets it at 0,0
        addNode(node);
        return node;
    }


    /** Add the Node <tt>node</tt> to the visibleLocality, checking for ID uniqueness. */
    public void addNode( final Node node ) throws TGException {
        synchronized (localityUtils) {
            visibleLocality.addNode(node);
            resetDamper();
        }
    }

    /** Remove the Node object matching the ID <code>id</code>, returning true if the
      * deletion occurred, false if a Node matching the ID does not exist (or if the ID
      * value was null).
      *
      * @param id The ID identifier used as a query.
      * @return true if the deletion occurred.
      */
    public boolean deleteNodeById( String id )
    {
        if ( id == null ) return false; // ignore
        Node node = findNode(id);
        if ( node == null ) return false;
        else return deleteNode(node);
    }

    public boolean deleteNode( Node node ) {
        synchronized (localityUtils) {
            if (visibleLocality.deleteNode(node)) { // delete from visibleLocality, *AND completeEltSet
                if ( node == select ) clearSelect();
                resetDamper();
                return true;
            }
            return false;
        }
    }

    public void clearAll() {
        synchronized (localityUtils) {
            visibleLocality.clearAll();
        }
    }

    public Node getSelect() {
        return select;
    }

    public Node getMouseOverN() {
        return mouseOverN;
    }

    public synchronized void setMouseOverN( Node node ) {
        if ( dragNode != null || maintainMouseOver ) return;  // So you don't accidentally switch nodes while dragging
        if ( mouseOverN != node ) {
            Node oldMouseOverN = mouseOverN;
            mouseOverN=node;
        }

        if (mouseOverN==null)
          setCursor(new Cursor(Cursor.MOVE_CURSOR));
        else
          setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

  // Edge manipulation ...........................

    /** Returns an Iterator over all edges in the complete graph. */
 /*   public Iterator getAllEdges() {
        return completeEltSet.getEdges();
    }
*/
    public void deleteEdge( Edge edge ) {
        synchronized (localityUtils) {
            visibleLocality.deleteEdge(edge);
            resetDamper();
        }
    }

    public void deleteEdge( Node from, Node to ) {
        synchronized (localityUtils) {
            visibleLocality.deleteEdge(from,to);
        }
    }

    /** Returns the current edge count in the complete graph.
      */
    public int getEdgeCount() {
        return completeEltSet.edgeCount();
    }

    /** Return the number of Edges in the Locality.
      * @deprecated        this method has been replaced by the <tt>visibleEdgeCount()</tt> method.
      */
    public int edgeNum() {
        return visibleLocality.edgeCount();
    }

    /** Return the number of Edges in the Locality.
      */
    public int visibleEdgeCount() {
        return visibleLocality.edgeCount();
    }

    public Edge findEdge( Node f, Node t ) {
        return visibleLocality.findEdge(f, t);
    }

    public void addEdge(Edge e) {
        synchronized (localityUtils) {
            visibleLocality.addEdge(e); resetDamper();
        }
    }

    public Edge addEdge( Node f, Node t, int tens ) {
        synchronized (localityUtils) {
            return visibleLocality.addEdge(f, t, tens);
        }
    }

    public Edge getMouseOverE() {
        return mouseOverE;
    }

    public synchronized void setMouseOverE( Edge edge ) {
        if ( dragNode != null || maintainMouseOver ) return; // No funny business while dragging
        if ( mouseOverE != edge ) {
            Edge oldMouseOverE = mouseOverE;
            mouseOverE = edge;
        }
    }


  // miscellany ..................................

    protected class AdjustOriginLens extends TGAbstractLens {
        protected void applyLens(TGPoint2D p) {
            p.x=p.x+TGPanel.this.getSize().width/2;
            p.y=p.y+TGPanel.this.getSize().height/2;
        }

        protected void undoLens(TGPoint2D p) {
            p.x=p.x-TGPanel.this.getSize().width/2;
            p.y=p.y-TGPanel.this.getSize().height/2;
        }
    }

    public class SwitchSelectUI extends TGAbstractClickUI {
        public void mouseClicked(MouseEvent e) {
            if ( mouseOverN != null ) {
                if ( mouseOverN != select )
                    setSelect(mouseOverN);
                else
                    clearSelect();
            }
        }
    }

    void fireMovedEvent() {
        Vector listeners;

        synchronized(this) {
              listeners = (Vector)graphListeners.clone();
           }

        for (int i=0;i<listeners.size();i++){
              GraphListener gl = (GraphListener) listeners.elementAt(i);
              gl.graphMoved();
        }
      }

    public void fireResetEvent() {
        Vector listeners;

        synchronized(this) {
              listeners = (Vector)graphListeners.clone();
        }

        for (int i=0;i<listeners.size();i++){
              GraphListener gl = (GraphListener) listeners.elementAt(i);
              gl.graphReset();
        }
    }

    public synchronized void addGraphListener(GraphListener gl){
        graphListeners.addElement(gl);
    }


    public synchronized void removeGraphListener(GraphListener gl){
        graphListeners.removeElement(gl);
    }


    public synchronized void addPaintListener(TGPaintListener pl){
        paintListeners.addElement(pl);
    }


    public synchronized void removePaintListener(TGPaintListener pl){
        paintListeners.removeElement(pl);
    }

    private void redraw() {
        resetDamper();
    }

    public void setMaintainMouseOver( boolean maintain ) {
        maintainMouseOver = maintain;
    }

    public void clearSelect() {
        if ( select != null ) {
            select = null;
            repaint();
        }
    }

   /** A convenience method that selects the first node of a graph, so that hiding works. Shikha
     */
    public void selectFirstNode() {
        setSelect(getGES().getFirstNode());
    }

    public void setSelect( Node node ) {
          if ( node != null ) {
              select = node;
              repaint();
          } else if ( node == null ) clearSelect();
    }

    public void multiSelect( TGPoint2D from, TGPoint2D to ) {
        final double minX,minY,maxX,maxY;

        if ( from.x > to.x ) { maxX = from.x; minX = to.x; }
        else                 { minX = from.x; maxX = to.x; }
        if ( from.y > to.y ) { maxY = from.y; minY = to.y; }
        else                 { minY = from.y; maxY = to.y; }

        final Vector selectedNodes = new Vector();

        TGForEachNode fen = new TGForEachNode() {
            public void forEachNode( Node node ) {
                double x = node.drawx;
                double y = node.drawy;
                if ( x > minX && x < maxX && y > minY && y < maxY ) {
                    selectedNodes.addElement(node);
                }
            }
        };

        visibleLocality.forAllNodes(fen);

        if ( selectedNodes.size() > 0 ) {
            int r = (int)( Math.random()*selectedNodes.size() );
            setSelect((Node)selectedNodes.elementAt(r));
        } else {
            clearSelect();
        }
    }


    public void updateLocalityFromVisibility() throws TGException {
        visibleLocality.updateLocalityFromVisibility();
    }

    public void setLocale( Node node, int radius, int maxAddEdgeCount, int maxExpandEdgeCount,
                           boolean unidirectional ) throws TGException {
        localityUtils.setLocale(node,radius, maxAddEdgeCount, maxExpandEdgeCount, unidirectional);
    }

    public void fastFinishAnimation() { //Quickly wraps up the add node animation
        localityUtils.fastFinishAnimation();
    }

    public void setLocale( Node node, int radius ) throws TGException {
        localityUtils.setLocale(node,radius);
    }

    public void expandNode( Node node ) {
        localityUtils.expandNode(node);
    }

    public void hideNode( Node hideNode ) {
        localityUtils.hideNode(hideNode );
    }

    public void collapseNode( Node collapseNode ) {
        localityUtils.collapseNode(collapseNode );
    }

    public void hideEdge( Edge hideEdge ) {
        visibleLocality.removeEdge(hideEdge);
        if ( mouseOverE == hideEdge ) setMouseOverE(null);
        resetDamper();
    }

    public void setDragNode( Node node ) {
        dragNode = node;
        tgLayout.setDragNode(node);
    }

    public Node getDragNode() {
        return dragNode;
    }

    void setMousePos( Point p ) {
        mousePos = p;
    }

    public Point getMousePos() {
        return mousePos;
    }

    /** Start and stop the damper.  Should be placed in the TGPanel too. */
    public void startDamper() {
        if (tgLayout!=null) tgLayout.resetDamper();        
    }

    public void stopDamper() {
        if (tgLayout!=null) tgLayout.stopDamper();
    }

    /** Makes the graph mobile, and slowly slows it down. */
    public void resetDamper() {
        if (tgLayout!=null) tgLayout.resetDamper();
    }

    /** Gently stops the graph from moving */
    public void stopMotion() {
        if (tgLayout!=null) tgLayout.stopMotion();
    }

    class BasicMouseListener extends MouseAdapter {

        public void mouseEntered(MouseEvent e) {
            addMouseMotionListener(basicMML);
        }

        public void mouseExited(MouseEvent e) {
            removeMouseMotionListener(basicMML);
            mousePos = null;
            setMouseOverN(null);
            setMouseOverE(null);
            repaint();
        }
    }

    class BasicMouseMotionListener implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {
            mousePos = e.getPoint();
            findMouseOver();
            try {
                Thread.currentThread().sleep(6); //An attempt to make the cursor flicker less
            } catch (InterruptedException ex) {
                //break;
            }
        }

        public void mouseMoved( MouseEvent e ) {
            mousePos = e.getPoint();
            synchronized (this) {
                Edge oldMouseOverE = mouseOverE;
                Node oldMouseOverN = mouseOverN;
                findMouseOver();
                if (oldMouseOverE!=mouseOverE || oldMouseOverN!=mouseOverN) {
                    repaint();
                }
            }
        }
    }

    protected synchronized void findMouseOver() {

        if ( mousePos == null ) {
            setMouseOverN(null);
            setMouseOverE(null);
            return;
        }

        final int mpx=mousePos.x;
        final int mpy=mousePos.y;

        final Node[] monA = new Node[1];
        final Edge[] moeA = new Edge[1];

        TGForEachNode fen = new TGForEachNode() {

            double minoverdist = 100; //Kind of a hack (see second if statement)
                                        //Nodes can be as wide as 200 (=2*100)
            public void forEachNode( Node node ) {
                double x = node.drawx;
                double y = node.drawy;

                double dist = Math.sqrt((mpx-x)*(mpx-x)+(mpy-y)*(mpy-y));

                  if ( ( dist < minoverdist ) && node.containsPoint(mpx,mpy) ) {
                      minoverdist = dist;
                      monA[0] = node;
                  }
            }
        };
        visibleLocality.forAllNodes(fen);

        TGForEachEdge fee = new TGForEachEdge() {

            double minDist = 8; // Tangential distance to the edge
            double minFromDist = 1000; // Distance to the edge's "from" node

            public void forEachEdge( Edge edge ) {
                double x = edge.from.drawx;
                double y = edge.from.drawy;
                double dist = edge.distFromPoint(mpx,mpy);
                if ( dist < minDist ) { // Set the over edge to the edge with the minimun tangential distance
                    minDist = dist;
                    minFromDist = Math.sqrt((mpx-x)*(mpx-x)+(mpy-y)*(mpy-y));
                    moeA[0] = edge;
                } else if ( dist == minDist ) { // If tangential distances are identical, chose
                                                // the edge whose "from" node is closest.
                    double fromDist = Math.sqrt((mpx-x)*(mpx-x)+(mpy-y)*(mpy-y));
                    if ( fromDist < minFromDist ) {
                        minFromDist = fromDist;
                        moeA[0] = edge;
                    }
                }
            }
        };
        visibleLocality.forAllEdges(fee);

        setMouseOverN(monA[0]);
        if ( monA[0] == null )
            setMouseOverE(moeA[0]);
        else
            setMouseOverE(null);
    }

    TGPoint2D topLeftDraw = null;
    TGPoint2D bottomRightDraw = null;

    public TGPoint2D getTopLeftDraw() {
        return new TGPoint2D(topLeftDraw);
    }

    public TGPoint2D getBottomRightDraw() {
        return new TGPoint2D(bottomRightDraw);
    }

    public TGPoint2D getCenter() {
        return tgLensSet.convDrawToReal(getSize().width/2,getSize().height/2);
    }

    public TGPoint2D getDrawCenter() {
        return new TGPoint2D(getSize().width/2,getSize().height/2);
    }

    public void updateGraphSize() {
        if ( topLeftDraw == null ) topLeftDraw=new TGPoint2D(0,0);
        if ( bottomRightDraw == null ) bottomRightDraw=new TGPoint2D(0,0);

        TGForEachNode fen = new TGForEachNode() {
            boolean firstNode=true;
            public void forEachNode( Node node ) {
                if ( firstNode ) { //initialize topRight + bottomLeft
                    topLeftDraw.setLocation(node.drawx,node.drawy);
                    bottomRightDraw.setLocation(node.drawx,node.drawy);
                    firstNode=false;
                } else {  //Standard max and min finding
                    topLeftDraw.setLocation(Math.min(node.drawx,topLeftDraw.x),
                                            Math.min(node.drawy,topLeftDraw.y));
                    bottomRightDraw.setLocation(Math.max(node.drawx, bottomRightDraw.x),
                                                Math.max(node.drawy, bottomRightDraw.y));
                }
            }
        };

        visibleLocality.forAllNodes(fen);
    }

    public synchronized void processGraphMove() {
        updateDrawPositions();
        updateGraphSize();
    }

    public synchronized void repaintAfterMove() { // Called by TGLayout + others to indicate that graph has moved
        processGraphMove();
        findMouseOver();
        fireMovedEvent();
        repaint();
    }

    public void updateDrawPos( Node node ) { //sets the visual position from the real position
        TGPoint2D p = tgLensSet.convRealToDraw(node.x,node.y);
        node.drawx = p.x;
        node.drawy = p.y;
       }

    public void updatePosFromDraw( Node node ) { //sets the real position from the visual position
        TGPoint2D p = tgLensSet.convDrawToReal(node.drawx,node.drawy);
        node.x = p.x;
        node.y = p.y;
    }

    public void updateDrawPositions() {
        TGForEachNode fen = new TGForEachNode() {
            public void forEachNode( Node node ) {
                updateDrawPos(node);
            }
        };
        visibleLocality.forAllNodes(fen);
    }

    Color myBrighter( Color c ) {
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        r=Math.min(r+96, 255);
        g=Math.min(g+96, 255);
        b=Math.min(b+96, 255);

        return new Color(r,g,b);
    }

    public synchronized void paint( Graphics g ) {
        update(g);
    }

    public synchronized void update( Graphics g ) {
        Dimension d = getSize();
        if ( (offscreen == null)
                || ( d.width != offscreensize.width )
                || ( d.height != offscreensize.height )) {
            offscreen = createImage(d.width, d.height);
            offscreensize = d;
            offgraphics = offscreen.getGraphics();

            processGraphMove();
            findMouseOver();
            fireMovedEvent();
        }

        offgraphics.setColor(BACK_COLOR);
        offgraphics.fillRect(0, 0, d.width, d.height);


        synchronized(this) {
            paintListeners = (Vector)paintListeners.clone();
        }

        for ( int i = 0; i < paintListeners.size(); i++ ) {
            TGPaintListener pl = (TGPaintListener) paintListeners.elementAt(i);
            pl.paintFirst(offgraphics);
        }

        TGForEachEdge fee = new TGForEachEdge() {
            public void forEachEdge( Edge edge ) {
                edge.paint(offgraphics, TGPanel.this);
            }
        };

        visibleLocality.forAllEdges(fee);

        for ( int i = 0; i < paintListeners.size() ; i++ ) {
             TGPaintListener pl = (TGPaintListener) paintListeners.elementAt(i);
             pl.paintAfterEdges(offgraphics);
        }

        TGForEachNode fen = new TGForEachNode() {
            public void forEachNode( Node node ) {
                node.paint(offgraphics,TGPanel.this);
            }
        };

        visibleLocality.forAllNodes(fen);

        if ( mouseOverE != null ) { //Make the edge the mouse is over appear on top.
            mouseOverE.paint(offgraphics, this);
            mouseOverE.from.paint(offgraphics, this);
            mouseOverE.to.paint(offgraphics, this);
        }

        if ( select != null ) { //Make the selected node appear on top.
            select.paint(offgraphics, this);
        }

        if ( mouseOverN != null ) { //Make the node the mouse is over appear on top.
            mouseOverN.paint(offgraphics, this);
        }

        for ( int i = 0; i < paintListeners.size(); i++ ) {
            TGPaintListener pl = (TGPaintListener)paintListeners.elementAt(i);
            pl.paintLast(offgraphics);
        }

        paintComponents(offgraphics); //Paint any components that have been added to this panel
        g.drawImage(offscreen, 0, 0, null);

    }

}// end 
