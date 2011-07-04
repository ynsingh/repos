package org.bss.brihaspatisync.gui;

/**
 * InstructorCSPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2009-2010 ETRG, IIT Kanpur
 */

import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.util.Date;
import java.util.Vector;
import java.net.URLEncoder;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.Log;


/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified ActionListener and MouseListener for signalling
 */
 
public class InstructorCSPanel extends JPanel implements ActionListener, MouseListener{

	private JPanel mainPanel;
	private JPanel north_mainPanel;
	private JPanel center_mainPanel=null;
	private JLabel instLabel;
	private JLabel announceLabel;
	private JComboBox instCourseCombo=null;
	private JScrollPane scrollPane;
	private Vector courseid=new Vector();
	private JLabel serialNo[]=null;
	private JLabel nameLabel[]=null;
	private JPanel buttonPanel[] =null;
	private JRadioButton rButton[] =null; 
	private JPanel nsPane[]=null;
	private ButtonGroup group;
	private JScrollPane lectInfo_Pane[]=null;
	private Vector lectinfoVector=null;
	private JButton join[]=null;
        private JLabel  inLabel[]=null;
        private JLabel upLabel[]=null;
        private JLabel cancleLabel[]=null;
	private String lect_id="";
	private String status="available";
	private ClientObject client_obj=ClientObject.getController();
	private static InstructorCSPanel instcspanel=null;
	private Log log=Log.getController();
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	/**
	 * Controller for the class.
	 */
	protected static InstructorCSPanel getController(){
		if (instcspanel==null){
			instcspanel=new InstructorCSPanel();
		}
		return instcspanel;
	}
	
	/**
	 * Creating gui for the InstructorCSPanle
	 */
	protected JPanel createGUI(){
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());
		north_mainPanel=new JPanel();
		north_mainPanel.setLayout(new FlowLayout());
		north_mainPanel.setBackground(Color.LIGHT_GRAY);
		instLabel=new JLabel("<html><b>Course in which you are resistered as a instructor</b></html>");
		/**
		 * get Course list form clienmt object class.
		 */		
		Vector courseVec=client_obj.getInstCourseList();
		String str=courseVec.get(0).toString();
                courseVec.clear();
                String str1[]=str.split(",");
                for(int i=0;i<str1.length;i++){
                        courseVec.add(str1[i]);
                }
		courseVec.add(0,"--Show All--");
		instCourseCombo=new JComboBox(courseVec);
		instCourseCombo.addActionListener(this);	
		announceLabel=new JLabel("<html><b><font color=black>Announce new Session</font></b></html>");
		announceLabel.setEnabled(false);		
		announceLabel.addMouseListener(this);
		announceLabel.removeMouseListener(this);
    		announceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		announceLabel.setName("announceLabel.Action");
		north_mainPanel.add(instLabel,BorderLayout.WEST);
		north_mainPanel.add(instCourseCombo,BorderLayout.CENTER);
		north_mainPanel.add(announceLabel,BorderLayout.SOUTH);
		
		mainPanel.add(north_mainPanel, BorderLayout.NORTH);
		mainPanel.add(showLecture(client_obj.getInstSessionList()),BorderLayout.CENTER);
		return mainPanel;
	}

	protected JScrollPane showLecture(Vector lectVector){
		lectinfoVector=lectVector;
        	int y=lectVector.size();
		serialNo=new JLabel[y];
                nameLabel=new JLabel[y];
		buttonPanel=new JPanel[y];
		nsPane=new JPanel[y];
		lectInfo_Pane=new JScrollPane[y];
                int curdate=Integer.parseInt(client_obj.getServerDate());
		join=new JButton[y];
                inLabel=new JLabel[y];
                upLabel=new JLabel[y];
                cancleLabel=new JLabel[y];		

                center_mainPanel=new JPanel();
		center_mainPanel.setLayout(new GridLayout(0,2,5,3));

                center_mainPanel.setBorder(BorderFactory.createTitledBorder("Announced Sessions "));
                center_mainPanel.add(new JLabel("<html><b><U><font color=green>LECTURE NAME</font></U></b>",0));
                center_mainPanel.add(new JLabel("<html><b><U><font color=green>ACTIONS</font</U></b>",0));
		for(int i=0;i<y;i++){
                        java.util.StringTokenizer str1 = new java.util.StringTokenizer(lectVector.get(i).toString(),",");
                        String lectid=str1.nextElement().toString();
			courseid.add(lectid);
                        String lectCouseName=str1.nextElement().toString();
                        String lectUserName=str1.nextElement().toString();
                        String lectName=str1.nextElement().toString();
                        String lectInfo=str1.nextElement().toString();
                        String lectNo=str1.nextElement().toString();
                        String lectVedio=str1.nextElement().toString();
                        String lectAudio=str1.nextElement().toString();
                        String lectWhiteBoard=str1.nextElement().toString();
                        String lectDate=str1.nextElement().toString();
                        String lectTime=str1.nextElement().toString();
                        String lectDuration=str1.nextElement().toString();
                        String repeattime=str1.nextElement().toString();
                        String fortime=str1.nextElement().toString();
	
			buttonPanel[i]=new JPanel();
                        buttonPanel[i].setLayout(new FlowLayout());
                        buttonPanel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			
			nsPane[i]=new JPanel();
			nsPane[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			
			nameLabel[i]=new JLabel(lectName);
			nsPane[i].add(nameLabel[i]);
			lectDate=lectDate.substring(0,10);
			lectDate=lectDate.replaceAll("-","");
			int checkintdate=Integer.parseInt(lectDate);
                        lectInfo_Pane[i]=new JScrollPane(nsPane[i]);
			System.out.println(checkintdate+" == "+curdate);
                        if((checkintdate == curdate )) {
                       		join[i]=new JButton("Join");
				join[i].addActionListener(this);
                     	}else {
                       		join[i]=new JButton("");
                    	}
			
			buttonPanel[i].add(join[i]);
			ClassLoader clr= this.getClass().getClassLoader();
                        inLabel[i]=new JLabel("<html><Font color=blue><u>Lecture Info</u></font></html>",new ImageIcon(clr.getResource("resources/images/info.gif")),0);
                        inLabel[i].addMouseListener(this);
                        inLabel[i].setName("lectureInfo.Action");
                        inLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			buttonPanel[i].add(inLabel[i]);

			/********************************MODIFIED BY PRATIBHA*********************/

                        if((instCourseCombo.getSelectedItem()).equals("--Show All--")){
                                upLabel[i]=new JLabel("<html><Font color=black>Update</font></html>");
                                upLabel[i].setEnabled(false);
                                upLabel[i].removeMouseListener(this);
                        }
			else{
                                upLabel[i]=new JLabel("<html><Font color=blue><u>Update</u></font></html>");
                                upLabel[i].addMouseListener(this);
                		upLabel[i].setName("Update.Action");
                                upLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                        }
                        /******************************************************************************/
			buttonPanel[i].add(upLabel[i]);
			cancleLabel[i]=new JLabel("<html><Font color=blue><u>Cancle</u></font></html>");
                        cancleLabel[i].addMouseListener(this);
                        cancleLabel[i].setName("Cancle.Action");
                        cancleLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			buttonPanel[i].add(cancleLabel[i]);

                        center_mainPanel.add(nsPane[i]);
                        center_mainPanel.add(buttonPanel[i]);
                }
                if(y==0){
                        //JOptionPane.showMessageDialog(null,"No Lecture in this course");
                        return new JScrollPane();
                }else{
                        scrollPane=new JScrollPane(center_mainPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        return scrollPane;
                }
        }

        public void actionPerformed(ActionEvent e) {
		
		// Action for Combobox on selecting particular Course
                if(e.getSource()==instCourseCombo){
			
                       	JComboBox combo = (JComboBox)e.getSource();
                  	mainPanel.remove(1);
			if(((String)combo.getSelectedItem()).equals("--Show All--")){
				announceLabel.setEnabled(false);
                                announceLabel.setText("<html><b><font color=black>Announce new Session</font></b></html>");
				announceLabel.removeMouseListener(this);
				Vector courseName=client_obj.getInstCourseList();
				mainPanel.add(showLecture(client_obj.getSessionList(courseName,client_obj.getIndexServerName())),BorderLayout.CENTER);
			}else{
				announceLabel.setEnabled(true);
                                announceLabel.setText("<html><b><font color=blue>Announce new Session</font></b></html>");
				announceLabel.addMouseListener(this);
				Vector courseName=new Vector();
				client_obj.setCourseForAnnounce((String)combo.getSelectedItem());
				courseName.addElement((String)combo.getSelectedItem());
				mainPanel.add(showLecture(client_obj.getSessionList(courseName,client_obj.getIndexServerName())),BorderLayout.CENTER);
			}
                	center_mainPanel.validate();
                       	mainPanel.revalidate();
               	}
		// Action for Join button 
		// Modified for signalling
		try{
			for(int i=0;i<join.length;i++){
				if(e.getSource()==join[i]){
					lect_id=courseid.get(i).toString();
					// store this lect_id in client objects for later use by this client.
					client_obj.setLectureID(lect_id);	
					// store role in client objects for later use by this client.
					if(!((client_obj.getUserRole()).equals("instructor")))
                                		client_obj.setUserRole("instructor");
					JoinSession.getController().goToLecture(lect_id);
					MainWindow.getController().getMenuItem5().setEnabled(true);
				}
			}
		}catch(Exception exc){log.setLog("Can't open GUI");}
        }

	// Modified for signalling
	public void mouseClicked(MouseEvent ev) {
		
		// Action for Announce Button
		if(ev.getComponent().getName().equals("announceLabel.Action")){
			announceLabel.setCursor(busyCursor);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException ie){
				announceLabel.setCursor(defaultCursor);
			}finally{
				announceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		 	announceNewSession();
		}
		// Action for Lecture Info button
		if(ev.getComponent().getName().equals("lectureInfo.Action")){
			try{
				for(int i=0;i<inLabel.length;i++) {
                			if(ev.getSource()==inLabel[i]){
						LectureInfo info=new LectureInfo(i,lectinfoVector);
                    			}
        			}
			}catch(Exception e){}		 	
		}
		// Action for Update button
                if(ev.getComponent().getName().equals("Update.Action")){
			try{
                        	for(int i=0;i<upLabel.length;i++) {
                                	if(ev.getSource()==upLabel[i]){
						UpdateSessionPanel.getController().createGUI(i,lectinfoVector);
                                        }
                                }

                	}catch(Exception e){}
                }
		//action for update button
		if(ev.getComponent().getName().equals("Cancle.Action")){
                        try{
					
                                for(int i=0;i<cancleLabel.length;i++) {
                                        if(ev.getSource()==cancleLabel[i]){
						cancleLecture(i);
					}//if
				} //for
				mainPanel.remove(1);
                                Vector courseName=client_obj.getInstCourseList();
				mainPanel.add(showLecture(client_obj.getSessionList(courseName,client_obj.getIndexServerName())),BorderLayout.CENTER);
	     			mainPanel.revalidate();
					
                        }catch(Exception e){ log.setLog("  Error in Cancle action !!!!   ");  }
                }
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {	
		if(e.getComponent().getName().equals("announceLabel.Action"))
		 announceLabel.setText("<html><b><font color=blue><u>Announce new Session</u></font></b></html>");

	}

	public void mouseExited(MouseEvent e){
		if(e.getComponent().getName().equals("announceLabel.Action"))
                announceLabel.setText("<html><b><font color=blue>Announce new Session</font></b></html>");
	}
	
	//Modified By pratibha
	private void announceNewSession(){
			mainPanel.remove(1);
			announceLabel.setEnabled(false);
			announceLabel.setText("<html><b><font color=black>Announce new Session</font></b></html>");
                        announceLabel.removeMouseListener(this);
			mainPanel.add(AnnounceSessionPanel.getController().createGUI(),BorderLayout.CENTER);
			mainPanel.revalidate();
	}

	private void cancleLecture(int indexnumber){	
		try {
        	        String idCourse = "lectValue="+URLEncoder.encode(courseid.get(indexnumber).toString(),"UTF-8");
			String indexServerName=client_obj.getIndexServerName();
			String indexServer=indexServerName+"/ProcessRequest?req=cancleLecture&"+idCourse;
			if(!(indexServerName.equals(""))) {
				if(!(HttpsUtil.getController().getIndexingMessage(indexServer))){
                                	JOptionPane.showMessageDialog(null,"There are some problem in InstructorCSPanel !!");
				}
			}  else
				log.setLog("Insufficient indexServer name in cancleLecture() in InstructorCSPanel :"+indexServer);
		}catch(Exception e){/**System.out.**/}
	}
	
	/****************Modofied by pratibha*************************/
        protected JPanel getmainPanel(){
                return mainPanel;
        }

	protected JComboBox getinstCourseCombo(){
                return instCourseCombo;
        }

        /*************************************************************/

}	

