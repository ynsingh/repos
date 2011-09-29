package org.bss.brihaspatisync.gui;

/**
 * StudentCSPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
 */

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
//import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.util.Vector;
import java.util.Date;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a> 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified for signalling.
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class StudentCSPanel extends JPanel implements ActionListener, MouseListener{

	private JLabel studLabel;
	private JPanel mainPanel;
	private String lect_id="";

	private JPanel nsPane[]=null;
        private JButton runButton[]=null;
        private JLabel descLabel[]=null;
        private JLabel nameLabel[]=null;
        private JPanel buttonPanel[] =null;
        private Vector lectinfoVector=null;

	private JPanel north_mainPanel;
	private JPanel center_mainPanel;
	private JComboBox studCourseCombo;
	private JScrollPane scrollPane1;
	private Vector courseid=new Vector();	
        
	private Log log=Log.getController();
	private static StudentCSPanel studcspanel=null;
	private ClientObject client_obj=ClientObject.getController();
	
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	/**
	 * Controller for class.
	 */
	protected static StudentCSPanel getController(){
		if (studcspanel==null){
			studcspanel=new StudentCSPanel();
		}
		return studcspanel;
	}
	
	/**
	 * Creating GUI for StudentCSPanle
	 */
	protected JPanel createGUI(){
		
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());	
			
		north_mainPanel=new JPanel();
		north_mainPanel.setLayout(new FlowLayout());
		north_mainPanel.setBackground(Color.LIGHT_GRAY);
		studLabel=new JLabel("<html><b>"+Language.getController().getLangValue("StudentCSPanel.TitleLabel")+"</b></html>");
		Vector courseVec=client_obj.getStudCourseList();
		String str=courseVec.get(0).toString();
                courseVec.clear();
                String str1[]=str.split(",");
                for(int i=0;i<str1.length;i++){
                        courseVec.add(str1[i]);
                }
		courseVec.add(0,"--Show All--");
		studCourseCombo=new JComboBox(courseVec);
		studCourseCombo.addActionListener(this);	
		
		north_mainPanel.add(studLabel,BorderLayout.WEST);
		north_mainPanel.add(studCourseCombo,BorderLayout.CENTER);
	   	mainPanel.add(north_mainPanel, BorderLayout.NORTH);
	   	
		Vector courseName=client_obj.getStudCourseList();
                mainPanel.add(showLecture(client_obj.getSessionList(courseName,client_obj.getIndexServerName())),BorderLayout.CENTER);
		//mainPanel.add(showLecture(client_obj.getStudSessionList()), BorderLayout.CENTER);
    		return mainPanel;
	}
	
	/**
	 * Create Grid View for lectures detail.
	 */
	private JScrollPane showLecture(Vector lectureVector) {
		
		lectinfoVector=lectureVector;
		int y=lectureVector.size();
		runButton=new JButton[y];			
		nameLabel=new JLabel[y];
		descLabel=new JLabel[y];
		buttonPanel=new JPanel[y];
	        nsPane=new JPanel[y];
		center_mainPanel=new JPanel();
    	
 		center_mainPanel.setLayout(new GridLayout(0,2,5,3));
    		center_mainPanel.setBorder(BorderFactory.createTitledBorder(Language.getController().getLangValue("StudentCSPanel.BorderText")));
		center_mainPanel.add(new JLabel("<html><b><U><font color=green>"+Language.getController().getLangValue("UpdateSessionPanel.LectureLabel")+"</font></U></b>",0));
		center_mainPanel.add(new JLabel("<html><b><U><font color=green>"+Language.getController().getLangValue("UpdateSessionPanel.ActionLabel")+"</font></U></b>",0));
		
		int curdate=Integer.parseInt(client_obj.getServerDate());
		
		for(int i=0;i<y;i++){
                        java.util.StringTokenizer str1 = new java.util.StringTokenizer(lectureVector.get(i).toString(),",");
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
			
			nsPane[i]=new JPanel();
                        nsPane[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			nameLabel[i]=new JLabel(lectName);
			nsPane[i].add(nameLabel[i]);
			lectDate=lectDate.substring(0,10);
                        lectDate=lectDate.replaceAll("-","");
			int checkintdate=Integer.parseInt(lectDate);
                        if(checkintdate == curdate) {
                    		runButton[i]=new JButton(Language.getController().getLangValue("StudentCSPanel.JoinBttn"));
				runButton[i].addActionListener(this);

               		}
			else {
				runButton[i]=new JButton("");
                    	
			}
			buttonPanel[i]=new JPanel();
                       	buttonPanel[i].setLayout(new FlowLayout());
                       	buttonPanel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			ClassLoader clr= this.getClass().getClassLoader();
                       	descLabel[i]=new JLabel("<html><Font color=blue><u>"+Language.getController().getLangValue("StudentCSPanel.LectureInfo")+"</u></font></html>",new ImageIcon(clr.getResource("resources/images/info.gif")),0);
			descLabel[i].addMouseListener(this);
			descLabel[i].setName("lectureInfo.Action");
			descLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			buttonPanel[i].add(runButton[i]);
			buttonPanel[i].add(descLabel[i]);
			center_mainPanel.add(nsPane[i]);
			center_mainPanel.add(buttonPanel[i]);
		}
       		if(y==0){
			return new JScrollPane();
		}else{
			scrollPane1=new JScrollPane(center_mainPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			return scrollPane1;
		}  		
	}

	public void actionPerformed(ActionEvent e) {
		// Action for Combobox
  		if(e.getSource()==studCourseCombo){
      			JComboBox combo = (JComboBox)e.getSource();
        		mainPanel.remove(1);
			if(((String)combo.getSelectedItem()).equals("--Show All--")){
				Vector courseName=client_obj.getStudCourseList();
                                mainPanel.add(showLecture(client_obj.getSessionList(courseName,client_obj.getIndexServerName())),BorderLayout.CENTER);
                        }else{
                                Vector courseName=new Vector();
                                courseName.addElement((String)combo.getSelectedItem());
                                mainPanel.add(showLecture(client_obj.getSessionList(courseName,client_obj.getIndexServerName())),BorderLayout.CENTER);
                        }

			center_mainPanel.validate();
                        mainPanel.revalidate();
    		}
		// Action for Join button
		try{
                       	for(int i=0;i<runButton.length;i++){
                               	if(e.getSource()==runButton[i]){
					lect_id=courseid.get(i).toString();
					// store this lect_id in client objects for later use by this client.
                                        client_obj.setLectureID(lect_id);
					// store role in client objects for later use by this client.
					if(!(client_obj.getUserRole()).equals("student"))
                                                client_obj.setUserRole("student");
                                       	JoinSession.getController().goToLecture(lect_id);
                               	}
                       	}
                }catch(Exception exc){log.setLog("Can't open GUI");}
	}

	public void mouseClicked(MouseEvent ev) {
		 	 	
		 if(ev.getComponent().getName().equals("lectureInfo.Action")){
			try{
				for(int i=0;i<descLabel.length;i++) {
					if(ev.getSource()==descLabel[i]){
                       	  			LectureInfo info=new LectureInfo(i,lectinfoVector);
                    			}
        			}
			}catch(Exception e){}	 	
		 }
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
	
