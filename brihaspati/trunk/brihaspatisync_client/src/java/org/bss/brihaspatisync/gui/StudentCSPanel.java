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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.util.Vector;
import java.util.Date;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Created on 2008, modified on 2011, modified on 2012 
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a> 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified for signalling.
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 */

public class StudentCSPanel extends JPanel implements ActionListener, MouseListener{

        private int cur_h=0;
        private int cur_m=0;
        private int curday=0;
	private int curyear=0;
        private int curmonth=0;
	
	private JLabel studLabel;
	private JPanel mainPanel;
	private JPanel nsPane[]=null;
	private JPanel north_mainPanel;
	private JPanel center_mainPanel;
        private JPanel reload_Panel=null;
        private JPanel buttonPanel[] =null;
	private JPanel studentCourseCombo_Panel=null;
		
	private String lect_id="";
	private JScrollPane scrollPane1;
        private JButton runButton[]=null;
	private JComboBox studCourseCombo;
	private ClassLoader clr= this.getClass().getClassLoader();
				
	
	private JLabel reloadLabel;
	private JLabel reloadLabel_1;
        private JLabel descLabel[]=null;
        private JLabel nameLabel[]=null;

	
        private Vector lectinfoVector=null;
	private Vector courseid=new Vector();	
        
	private Log log=Log.getController();
	
	private ClientObject client_obj=ClientObject.getController();
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	private static StudentCSPanel studcspanel=null;

	/**
	 * Creating GUI for StudentCSPanle
	 */
	protected JPanel createGUI(){
		
		mainPanel=new JPanel();
		mainPanel.setLayout(new BorderLayout());	
			
		north_mainPanel=new JPanel();
		north_mainPanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                Insets insets = new Insets(5,10,5,10);

		studentCourseCombo_Panel=new JPanel();
		reload_Panel=new JPanel();
		studentCourseCombo_Panel.setBackground(Color.LIGHT_GRAY);
		reload_Panel.setBackground(Color.LIGHT_GRAY);
		north_mainPanel.setBackground(Color.LIGHT_GRAY);
		studLabel=new JLabel("<html><b>"+Language.getController().getLangValue("StudentCSPanel.TitleLabel")+"</b></html>");
		gbc.gridx = 0;
                gbc.gridy = 0;
                north_mainPanel.add(studLabel,gbc);

		
		studCourseCombo=new JComboBox(reloadCourseList());
		studCourseCombo.addActionListener(this);	
		studentCourseCombo_Panel.add(studCourseCombo,BorderLayout.CENTER);
		gbc.gridx = 1;
                gbc.gridy = 0;
                north_mainPanel.add(studentCourseCombo_Panel,gbc);

		reloadLabel=new JLabel(new ImageIcon(clr.getResource("resources/images/reload.png")));
                reloadLabel.setEnabled(true);
                reloadLabel.addMouseListener(this);
		reloadLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                reloadLabel.setName("reloadLabel.Action");

		reloadLabel_1=new JLabel("<html><b><font color=blue>"+Language.getController().getLangValue("InstructorCSPanel.reloadLabel")+"</font></b></html>");
                reloadLabel_1.setEnabled(true);
                reloadLabel_1.addMouseListener(this);
                reloadLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                reloadLabel_1.setName("reloadLabel.Action");
		
                reload_Panel.add(reloadLabel,new FlowLayout());
                reload_Panel.add(reloadLabel_1,new FlowLayout());
		gbc.gridx = 2;
                gbc.gridy = 0;
                north_mainPanel.add(reload_Panel,gbc);
		
	   	mainPanel.add(north_mainPanel, BorderLayout.NORTH);
	   	
                mainPanel.add(showLecture(client_obj.getSessionList(reloadCourseList(),client_obj.getIndexServerName())),BorderLayout.CENTER);
    		return mainPanel;
	}
	
	private Vector reloadCourseList(){
		Vector courseVec=client_obj.getStudCourseList();
                String str=courseVec.get(0).toString();
                courseVec.clear();
                String str1[]=str.split(",");
                for(int i=0;i<str1.length;i++){
                        courseVec.add(str1[i]);
                }
                courseVec.add(0,"--Show All--");
                return courseVec;
        }
	
	/**
	 * Create Grid View for lectures detail.
	 */
	private JScrollPane showLecture(Vector lectureVector) {
		getTimeIndexingServer();
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
				
		center_mainPanel.add(new JLabel("<html><b><U><font color=green >"+Language.getController().getLangValue("StudentCSPanel.ActionLabel")+"</font></U></b>"),0);
		center_mainPanel.add(new JLabel("<html><b><U><font color=green >"+Language.getController().getLangValue("StudentCSPanel.LectureLabel")+"</font></U></b>"),0);
	
		String str_curday="";
                String str_curmonth="";
		
		if(curday<10){
                        str_curday="0"+Integer.toString(curday);
                }else
			str_curday=Integer.toString(curday);
                if(curmonth<10){
                        str_curmonth="0"+Integer.toString(curmonth);
                }else
			str_curmonth=Integer.toString(curmonth);

                int curdate=Integer.parseInt(Integer.toString(curyear)+str_curmonth+str_curday);	
		
		for(int i=0;i<y;i++){
			try {
                        java.util.StringTokenizer str1 = new java.util.StringTokenizer(lectureVector.get(i).toString(),",");
                        String lectid=decrypt(str1.nextElement().toString());
                        courseid.add(lectid);
                        String lectCouseName=decrypt(str1.nextElement().toString());
                        String lectUserName=decrypt(str1.nextElement().toString());
                        String lectName=decrypt(str1.nextElement().toString());
                        String lectInfo=decrypt(str1.nextElement().toString());
                        String lectNo=decrypt(str1.nextElement().toString());
                        String lectVedio=decrypt(str1.nextElement().toString());
                        String lectAudio=decrypt(str1.nextElement().toString());
                        String lectWhiteBoard=decrypt(str1.nextElement().toString());
                        String lectDate=decrypt(str1.nextElement().toString());
                        String lectTime=decrypt(str1.nextElement().toString());
                        String lectDuration=decrypt(str1.nextElement().toString());
                        String repeattime=decrypt(str1.nextElement().toString());
                        String fortime=decrypt(str1.nextElement().toString());
			
			String time[]=lectTime.split(":");
			int anausetime= (Integer.parseInt(time[0])*60)+Integer.parseInt(time[1]);	
			nsPane[i]=new JPanel();
                        nsPane[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			nameLabel[i]=new JLabel(lectName);
			nsPane[i].add(nameLabel[i]);
			lectDate=lectDate.substring(0,10);
                        lectDate=lectDate.replaceAll("-","");
			int checkintdate=Integer.parseInt(lectDate);
			
			int cue_finaltime =(cur_h*60)+cur_m;
			time=lectDuration.split(":");
			int durationtime=Integer.parseInt(time[0])*60;
			durationtime=anausetime+durationtime;
			if(checkintdate == curdate) {
				if((anausetime <= cue_finaltime) && (durationtime >= cue_finaltime) ) {				
	                    		runButton[i]=new JButton(Language.getController().getLangValue("StudentCSPanel.JoinBttn"));
					runButton[i].addActionListener(this);
				}else
				runButton[i]=new JButton("");
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
			}catch(Exception e){System.out.println("Error in load session in StudentCSPanel class  "+e.getMessage());}
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
                                       	new JoinSession(lect_id);
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
		}else if(ev.getComponent().getName().equals("reloadLabel.Action")) {
			studentCourseCombo_Panel.remove(studCourseCombo);
			
			studCourseCombo=new JComboBox(reloadCourseList());
	                studCourseCombo.addActionListener(this);
        	        studentCourseCombo_Panel.add(studCourseCombo,BorderLayout.CENTER);
			studentCourseCombo_Panel.revalidate();
			mainPanel.remove(1);
                       	mainPanel.add(showLecture(client_obj.getSessionList(reloadCourseList(),client_obj.getIndexServerName())),BorderLayout.CENTER);
			StatusPanel.getController().setStatus("reload Successfully");
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
		
	private void getTimeIndexingServer() {
                try {
                        String indexServer=org.bss.brihaspatisync.http.HttpCommManager.getController().getTimeIndexingServer();
                        if(indexServer != null) {
				indexServer=java.net.URLDecoder.decode(indexServer.trim());
                                indexServer=indexServer.replace("date","");
                                String str[]=indexServer.split(" ");
                                String str1[]=str[0].split("/");

                                curyear=Integer.parseInt(str1[0]);
                                curmonth=Integer.parseInt(str1[1]);
                                curday=Integer.parseInt(str1[2]);
                                String str2[]=str[1].split(":");
                                cur_h=Integer.parseInt(str2[0]);
                                cur_m=Integer.parseInt(str2[1])+10;
                        }
                }catch(Exception e){ System.out.println("Error in getTimeIndexingServer() "+e.getMessage());}
        }
	
	private String decrypt(String encryptedData) throws Exception {
		String decryptedValue = new String(org.apache.commons.codec.binary.Base64.decodeBase64(encryptedData.getBytes()));
                return decryptedValue;
        }

}
	
