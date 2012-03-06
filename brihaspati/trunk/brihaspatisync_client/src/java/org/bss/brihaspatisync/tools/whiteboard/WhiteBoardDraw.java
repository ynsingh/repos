package org.bss.brihaspatisync.tools.whiteboard;

/**
 * WhiteBoardDraw.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2012 ETRG, IIT kanpur.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.ThreadController;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 */

public class WhiteBoardDraw extends JPanel implements ItemListener, MouseListener, MouseMotionListener, ActionListener, Runnable{

        private Thread runner = null;
        private int bcolr=0,figr=0,x1r=0,x2r=0,y1r=0,y2r=0,sizer=0,col=0;
        private String x51r="",x71r="",msgdata="",textname="";
        private int mouseX=0,mouseY=0,movX=0,movY=0;

        private final static int
                         BLACK = 0,
                         RED = 1,
                         GREEN = 2,
                         BLUE = 3,
                         CYAN = 4,
                         MAGENTA = 5,
                         YELLOW = 6,
                         WHITE =7;

        private JComboBox colorChoice=null,textChoice=null,sizeChoice=null;
        private JTextField data=null;
        private Image OSC=null;
        private Graphics OSG=null;
		
        private int  widthOfOSI=0, heightOfOSI=0;
        private int prevX=0, prevY=0;
        private int startX=0, startY=0;
        private boolean dragging;
        private boolean erasing;
        private  int figure=0;
	private Color currentColor = Color.BLACK;
        private int textdata=0,sizedata=0;
        private Graphics graphicsForDrawing=null;
        private Graphics offscreenGraphics=null;
        private String act_msg="";
	private boolean wb_Flag=false;
	private boolean wbSize_Flag=false;
        private static WhiteBoardDraw wbdraw=null;
        private final static int
			CURVE = 0,
                       	LINE = 1,
                       	RECT = 2,
                       	OVAL = 3,
                       	ROUNDRECT = 4,
                       	FILLED_RECT = 5,
                       	FILLED_OVAL = 6,
                       	FILLED_ROUNDRECT = 7;
	private  Vector draw_vector=new Vector();
	private static WhiteBoardDraw wb_draw=null;
	private boolean checkFlag=false;
//	private Log log=Log.getController();


	public static WhiteBoardDraw getController(){
		if(wb_draw==null){
			wb_draw=new WhiteBoardDraw();
		}
		return wb_draw;
	}


	public void allowDrawforStudent(){
		this.setCursor(new Cursor(1));
                this.addMouseListener(this);
                this.addMouseMotionListener(this);
                this.dragging=false;
        }

	public void denieDrawforStudent(){
		this.dragging=true;
                this.setCursor(new Cursor(0));
                this.removeMouseListener(this);
                this.removeMouseMotionListener(this);
                this.revalidate();
                this.checkFlag=false;
	}

	private WhiteBoardDraw(){
		createGUI();
	}

	public void createGUI(){
		if((ClientObject.getController().getUserRole()).equals("instructor")){
			setCursor(new Cursor(1));
			addMouseListener(this);
                        addMouseMotionListener(this);
		}
               	setBackground(Color.white);
               	setForeground(Color.black);
	}
	
	public void start() {
                if(!wb_Flag)
			wb_Flag=true;
		if (runner == null) {
                        runner = new Thread(this);
                       	runner.start();
			System.out.println("WB_Student_Thread started.");
		}
        }

	public synchronized void stop() {
		if(wb_Flag!=false){
			wb_Flag=false;
		}
                if (runner != null) {
                	runner.stop();
                       	runner = null;
			System.out.println("WB_Student_Thread stopped.");
                }
        }

	public void run(){
		while(wb_Flag && ThreadController.getController().getThreadFlag()){
			while(draw_vector.size() !=0 ){
				wbSize_Flag=true;
				for(int i=0;i<draw_vector.size();i++){
					StringTokenizer st = new StringTokenizer((String)draw_vector.get(i),"$");
                			while(st.hasMoreTokens()) {
                        			String type=st.nextToken();
	                        		bcolr = Integer.parseInt(st.nextToken());
		        	                figr=Integer.parseInt(st.nextToken());
        		        	        x1r = Integer.parseInt(st.nextToken());
                		        	y1r = Integer.parseInt(st.nextToken());
	                		        x2r = Integer.parseInt(st.nextToken());
        	                		y2r = Integer.parseInt(st.nextToken());
	                	        	x51r=st.nextToken();
		       	                	sizer=Integer.parseInt(st.nextToken());
			       	                x71r=st.nextToken();
        	        		}
	                		repaint();
		                	putFigure();
					draw_vector.remove(i);
				}
				if(draw_vector.size() == 0)
					wbSize_Flag=false;
			}
		}
	}
			
	private void putFigure(){
		Graphics g=null;
		if(OSC==null)
			setupOSC();
		g = OSC.getGraphics();
                g.setColor(getCurrentColor100());
                if(figr<=1)   
			g.drawLine(x1r,y1r,x2r,y2r); 
		if(figr==2 || figr==3 || figr==4 || figr==5 || figr==6 || figr==7 )  
			cal(g,figr,x1r,y1r,x2r,y2r);
                if(figr==8) {
                        Font myfont=new Font(x51r,Font.PLAIN,sizer);
                        setFont(myfont);
                        if(x71r!="") {
                                g.drawString(x71r,x1r,y1r);
                        }
                }
                if(figr==9) {  
			g.fillRect(x1r,y1r,40,40); 
			g.fillRect(x1r,y1r,40,40); 
			g.fillRect(x1r,y1r,40,40);  
		}
		repaint();
        }
	

	private Color getCurrentColor100() {
                switch (bcolr) {
                        case 0:           return Color.black;
                        case 1:           return Color.red;
                        case 2:           return Color.green;
                        case 3:           return Color.blue;
                        case 4:           return Color.cyan;
                        case 5:           return Color.magenta;
                        case 6:           return Color.yellow;
                        case 7:           return Color.orange;
                        case 8:           return Color.pink;
                        case 9:           return Color.gray;
                        default:          return Color.white;
                }
	}


        private void cal(Graphics g,int figr,int x1r,int y1r,int x2r,int y2r){
                int x=0,y=0,w=0,h=0;

                /* To get the x cordinate */
                if(x2r>=x1r)  {    x=x1r; w=x2r-x1r; }
                else       {    x=x2r; w=x1r-x2r;  }
		 /* To get the y cordinate*/
                if(y2r>=y1r) {   y=y1r; h=y2r-y1r;  }
                else      {   y=y2r; h=y1r-y2r;  }

                if(figr==2) { g.drawRect(x,y,w,h); }
                if(figr==3)   g.drawOval(x,y,w,h);
                if(figr==4)   g.drawRoundRect(x,y,w,h,20,20);
                if(figr==5)   g.fillRect(x,y,w,h);
                if(figr==6)   g.fillOval(x,y,w,h);
                if(figr==7)   g.fillRoundRect(x,y,w,h,20,20);
        }

        /*Implementing the Double buffering */

        private void setupOSC() {
                if(OSG==null ) {
                        OSC=createImage(getSize().width,getSize().height);
			OSG=OSC.getGraphics();
                        OSG.setColor(getBackground());
                        OSG.fillRect(0, 0, getSize().width-1,getSize().height-1);
			
                }
        }

        /* Getting the graphics of offScreen */

        private Graphics getOSG() {
		setupOSC();
                return OSC.getGraphics();
        }

        /* Clearing the offScrean and also the canvas screen */

        private void clearOSC() {
                OSG.setColor(Color.white);
                OSG.fillRect(0,0,900,900);
                OSG.dispose();
                OSG=null;
                OSC=null;
                repaint();
        }

        /* update the canvas screen */

        public void update(Graphics g) {
                paint(g);
        }

        /*Paint the graphics into the screen */

        public void paint(Graphics g) {
                if(OSC==null && OSG==null) {
                        g.setColor(Color.white);
                        g.fillRect(0,0,900,900);
                }
                setupOSC();
                g.drawImage(OSC,0,0,this);
        }

        public void itemStateChanged(ItemEvent ie) { }

        /* get Commands to perform the action */

        public void actionPerformed(ActionEvent evt) {
                String command = evt.getActionCommand();
                if (command.equals("Insert")) msgdata=data.getText();
                if (command.equals("-Clear-"))  clearOSC();
        }
	/* get the current value of the color */
	
	private Color getCurrentColor() {

		//Color newColor = JColorChooser.showDialog(null,"Select Drawing Color",null);
                //return value;//=newColor;

                int currentColor = (colorChoice.getSelectedIndex());
                switch (currentColor) {
                        case BLACK:    col=0;      return Color.black;
                        case RED:      col=1;      return Color.red;
                        case GREEN:    col=2;      return Color.green;
                        case BLUE:     col=3;      return Color.blue;
                        case CYAN:     col=4;      return Color.cyan;
                        case MAGENTA:  col=5;      return Color.magenta;
                        case YELLOW:   col=6;      return Color.yellow;
                        case WHITE:    col=7;      return Color.white;
                        default:                   return Color.white;
                }
        }

        /* Putting the figure into the screen */

        private void putFigure(Graphics g,int kind,int x1,int y1,int x2,int y2,boolean outlineOnly){
                int x=0, y=0, w=0, h=0;

                /* This is for the Text */

                if(kind==8 && msgdata!="") {
			
                        Font myfont=new Font(textname,Font.PLAIN,sizedata);
                        setFont(myfont);
                        System.out.println("String of msg==>"+msgdata);
                        g.drawString(msgdata,x1,y1);
                       	WhiteBoardDataSender.getController().sendUnicastPacket(col,figure,x1,y1,0,0,textname,sizedata,msgdata);
                        
                }
                if (kind == LINE)   g.drawLine(x1, y1, x2, y2);
                else {
                        if (x2 >= x1) {
                                x = x1;
                                w = x2 - x1;
                        }
                        else {
                                x = x2;
                                w = x1 - x2;
                        }
			if (y2 >= y1) {
                                y = y1;
                                h = y2 - y1;
                        }
                        else {
                                y = y2;
                                h = y1 - y2;
                        }

                        switch (kind) {
                                case RECT:                 g.drawRect(x, y, w, h);          break;
                                case OVAL:                 g.drawOval(x, y, w, h);          break;
                                case ROUNDRECT:            g.drawRoundRect(x, y, w, h, 20, 20);   break;
                                case FILLED_RECT:
                                        if (outlineOnly)   g.drawRect(x, y, w, h);
                                        else               g.fillRect(x, y, w, h);
                                break;

                                case FILLED_OVAL:
                                        if (outlineOnly)   g.drawOval(x, y, w, h);
                                        else               g.fillOval(x, y, w, h);
                                break;

                                case FILLED_ROUNDRECT:
                                        if (outlineOnly)   g.drawRoundRect(x, y, w, h,20, 20);
                                        else               g.fillRoundRect(x, y, w, h,20, 20);
                                 break;

                        }
                }
        }

        public void mousePressed(MouseEvent evt) {

                	if (dragging == true)   return;
                        prevX = startX = evt.getX();
                        prevY = startY = evt.getY();
                        figure=WhiteBoardPanel.getController().getButtonValue();//button_value;
			textname=(String)textChoice.getSelectedItem();
			sizedata=Integer.parseInt((String)sizeChoice.getSelectedItem());
	                graphicsForDrawing =getGraphics();
                        graphicsForDrawing.setColor(getCurrentColor());
                        offscreenGraphics = getOSG();
                        offscreenGraphics.setColor(getCurrentColor());
                        if (figure != CURVE && figure!=8) {
                                graphicsForDrawing.setXORMode(getBackground());
                                putFigure(graphicsForDrawing, figure, startX, startY,startX, startY, true);
                        }
                        dragging = true;
        } // end mousePressed()

        public void mouseReleased(MouseEvent evt) {
                if(dragging == false)  return;
                if(figure != CURVE && figure!=9) {
                        putFigure(graphicsForDrawing, figure, startX, startY, prevX, prevY,true);
                        if (startX != prevX || startY != prevY) {
                                graphicsForDrawing.setPaintMode();
                                putFigure(graphicsForDrawing, figure, startX, startY,prevX,prevY,false);
                                putFigure(offscreenGraphics, figure, startX, startY,prevX, prevY,false);
                                WhiteBoardDataSender.getController().sendUnicastPacket(col,figure,startX,startY,prevX,prevY,"blank",0,"blank");
                              	
                        }
                }
                graphicsForDrawing.dispose();
                offscreenGraphics.dispose();
                graphicsForDrawing = null;
                offscreenGraphics = null;
                dragging = false;

        }

        public void mouseDragged(MouseEvent evt) {
                if (dragging == false)          return;
                int x = evt.getX();
                int y = evt.getY();

                if (figure == CURVE && figure!=8) {
                        putFigure(graphicsForDrawing, LINE, prevX, prevY, x, y, false);
                        WhiteBoardDataSender.getController().sendUnicastPacket(col,0,prevX,prevY,x,y,"blank",0,"blank");
	                putFigure(offscreenGraphics, LINE, prevX, prevY, x, y, false);
                }
                else {
                        putFigure(graphicsForDrawing, figure, startX, startY, prevX, prevY,true);
                        putFigure(graphicsForDrawing, figure, startX, startY, x, y, true);
                }

                prevX = x;
                prevY = y;

                if(figure==9) DrawPoint(evt.getPoint());
        }

        public void  DrawPoint(Point pt) {
                OSG.setColor(Color.white);
                OSG.fillOval(pt.x, pt.y,40,40);
                repaint();
              	WhiteBoardDataSender.getController().sendUnicastPacket(10,9,pt.x,pt.y,0,0,"blank",0,"blank");
        }

        public void mouseEntered(MouseEvent evt) { }
        public void mouseExited(MouseEvent evt)  { }
        public void mouseClicked(MouseEvent evt) { }
        public void mouseMoved(MouseEvent evt)   { }
		
	protected int getFigure(){
                return figure;
        }

        protected void setFigure(int value){
                figure=value;
        }

        protected JComboBox getColorChoice(){
                return colorChoice;
        }

        protected JComboBox getSizeChoice(){
                return sizeChoice;
        }

        protected JComboBox getTextChoice(){
                return textChoice;
        }

        protected JTextField getData(){
                return data;
        }
	
	protected void setColorChoice(JComboBox val){
                colorChoice=val;
        }

        protected void setSizeChoice(JComboBox val){
                sizeChoice=val;
        }

        protected void setTextChoice(JComboBox val){
                textChoice=val;
        }

        protected void setData(JTextField val){
                data=val;
        }


        public boolean getWB_Flag(){
                return wb_Flag;
        }

        public void  setWB_Flag(boolean flag){
               wb_Flag=flag;
        }

        public void setRunner(){
                runner=null;
        }

        public Thread getRunner(){
                return runner;
        }

        public Vector getDraw_vector(){
                return draw_vector;
        }
	
	public boolean getWBSize_Flag(){
                return wbSize_Flag;
        }

        public void  setWBSize_Flag(boolean flag){
               wbSize_Flag=flag;
        }

	public void  setCurrentColor(Color val) {
                currentColor=val;
        }

	//public Color getCurrentColor() {
	//	return currentColor;
	//}

}

