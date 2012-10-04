package org.bss.brihaspatisync.gui;

/**
 * InstructorCSPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011,2012 ETRG, IIT Kanpur
 */

import java.awt.Cursor;
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
import java.util.Date;
import java.util.Vector;
import java.net.URLEncoder;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.Language;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>Creadted on 2008, Modified on 2011, modified by 2012. 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha </a> Modified ActionListener and MouseListener for signalling
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>Modify for multilingual implementation. 
 * @author <a href="mailto:pradeepmca30@gmail.com">Pradeep kumar pal </a> testing for gui.
 */
 
public class InstructorCSPanel extends JPanel implements ActionListener, MouseListener{
	
        private int cur_h=0;
        private int cur_m=0;
        private int curday=0;
	private int curyear=0;
        private int curmonth=0;

	private JPanel mainPanel;
	private JPanel north_mainPanel;
	private JPanel center_mainPanel=null;
	private JPanel instCourseCombo_Panel=null;
	private JPanel reload_Panel=null;
	
	private JLabel instLabel;
	private JLabel announceLabel;
	private JLabel reloadLabel;
	private JLabel reloadLabel_1;
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
	private String status="available";
	private ClientObject client_obj=ClientObject.getController();
	private static InstructorCSPanel instcspanel=null;
	private Log log=Log.getController();
	private Cursor busyCursor =Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        private Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	private ClassLoader clr= this.getClass().getClassLoader();
	
	private String course_id="";

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
		instCourseCombo_Panel=new JPanel();
		instCourseCombo_Panel.setBackground(Color.LIGHT_GRAY);
		reload_Panel=new JPanel();
		reload_Panel.setBackground(Color.LIGHT_GRAY);

		north_mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Insets insets = new Insets(5,10,5,10);

		north_mainPanel.setBackground(Color.LIGHT_GRAY);
		instLabel=new JLabel("<html><b>"+Language.getController().getLangValue("InstructorCSPanel.Label1")+"</b></html>");
		gbc.gridx = 0;
                gbc.gridy = 0;
		north_mainPanel.add(instLabel,gbc);

		/**
		 * get Course list form clienmt object class.
		 */		
		instCourseCombo=new JComboBox(reloadCourseList());
		instCourseCombo.addActionListener(this);	
		instCourseCombo_Panel.add(instCourseCombo,BorderLayout.CENTER);
		gbc.gridx = 1;
                gbc.gridy = 0;
                north_mainPanel.add(instCourseCombo_Panel,gbc);

		
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


		announceLabel=new JLabel("<html><b><font color=black>"+Language.getController().getLangValue("InstructorCSPanel.AnnounceNewSession")+"</font></b></html>");
		announceLabel.setEnabled(false);		
		announceLabel.addMouseListener(this);
		announceLabel.removeMouseListener(this);
    		announceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		announceLabel.setName("announceLabel.Action");
		gbc.gridx = 3;
                gbc.gridy = 0;
                north_mainPanel.add(announceLabel,gbc);

		mainPanel.add(north_mainPanel, BorderLayout.NORTH);
		mainPanel.add(showLecture(client_obj.getSessionList(reloadCourseList(),client_obj.getIndexServerName())),BorderLayout.CENTER);
		return mainPanel;
	}
	
	private Vector reloadCourseList(){
		Vector courseVec=client_obj.getInstCourseList();
                String str=courseVec.get(0).toString();
                courseVec.clear();
                String str1[]=str.split(",");
                for(int i=0;i<str1.length;i++){
                        courseVec.add(str1[i]);
                }
                courseVec.add(0,"--Show All--");
		return courseVec;
	}	


	protected JScrollPane showLecture(Vector lectVector){
		getTimeIndexingServer();
		lectinfoVector=lectVector;
        	int y=lectVector.size();
		serialNo=new JLabel[y];
                nameLabel=new JLabel[y];
		buttonPanel=new JPanel[y];
		nsPane=new JPanel[y];
		lectInfo_Pane=new JScrollPane[y];
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

		join=new JButton[y];
                inLabel=new JLabel[y];
                upLabel=new JLabel[y];
                cancleLabel=new JLabel[y];		

                center_mainPanel=new JPanel();
		center_mainPanel.setLayout(new GridLayout(0,2,5,3));

                center_mainPanel.setBorder(BorderFactory.createTitledBorder(Language.getController().getLangValue("InstructorCSPanel.AnnouncedSession")));
                center_mainPanel.add(new JLabel("<html><b><U><font color=green >"+Language.getController().getLangValue("InstructorCSPanel.Actions")+"</font></U></b>"),0);
                center_mainPanel.add(new JLabel("<html><b><U><font color=green > "+Language.getController().getLangValue("InstructorCSPanel.LectureName")+"</font></U></b>"),0);
		courseid.clear();
		for(int i=0;i<y;i++){
			try {

                        java.util.StringTokenizer str1 = new java.util.StringTokenizer(lectVector.get(i).toString(),",");
                        String lectid=decrypt(str1.nextElement().toString());
		        String lectCouseName=decrypt(str1.nextElement().toString());
			String str=lectid+"-"+lectCouseName;
			courseid.add(str);//lectid);
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
			int anausetime=	(Integer.parseInt(time[0])*60)+Integer.parseInt(time[1]);

			
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
			/************  time *********************/	
                        int cue_finaltime =(cur_h*60)+cur_m;
			/***********  set time *************/	
			time=lectDuration.split(":");
                        int durationtime=Integer.parseInt(time[0])*60;
                        durationtime=anausetime+durationtime;
				
                        if((checkintdate == curdate )) {
				if((anausetime <= cue_finaltime) && (durationtime > cue_finaltime) ) {
                       			join[i]=new JButton(Language.getController().getLangValue("InstructorCSPanel.Join"));
					join[i].addActionListener(this);
				}else
					join[i]=new JButton("");
                     	}else {
                       		join[i]=new JButton("");
                    	}
			
			buttonPanel[i].add(join[i]);
                        inLabel[i]=new JLabel("<html><Font color=blue><u>"+Language.getController().getLangValue("InstructorCSPanel.LectureInfo")+"</u></font></html>");
                        inLabel[i].addMouseListener(this);
                        inLabel[i].setName("lectureInfo.Action");
                        inLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			buttonPanel[i].add(inLabel[i]);

			/********************************MODIFIED BY PRATIBHA*********************/

                        if((instCourseCombo.getSelectedItem()).equals("--Show All--")){
                                upLabel[i]=new JLabel("<html><Font color=black><u>"+Language.getController().getLangValue("InstructorCSPanel.Update")+"</u></font></html>");
                                upLabel[i].setEnabled(false);
                                upLabel[i].removeMouseListener(this);
                        }
			else{
                                upLabel[i]=new JLabel("<html><Font color=blue><u>"+Language.getController().getLangValue("InstructorCSPanel.Update")+"</u></font></html>");
                                upLabel[i].addMouseListener(this);
                		upLabel[i].setName("Update.Action");
                                upLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                        }
                        /******************************************************************************/
			buttonPanel[i].add(upLabel[i]);
			cancleLabel[i]=new JLabel("<html><Font color=blue><u>"+Language.getController().getLangValue("InstructorCSPanel.Cancel")+"</u></font></html>");
                        cancleLabel[i].addMouseListener(this);
                        cancleLabel[i].setName("Cancle.Action");
                        cancleLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			buttonPanel[i].add(cancleLabel[i]);

                        center_mainPanel.add(nsPane[i]);
                        center_mainPanel.add(buttonPanel[i]);
			}catch(Exception e){System.out.println("Error in get session "+e.getMessage());}
                }
                if(y==0){
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
                                announceLabel.setText("<html><b><font color=black>"+Language.getController().getLangValue("InstructorCSPanel.AnnounceLabel")+"</font></b></html>");
				announceLabel.removeMouseListener(this);
				Vector courseVec=client_obj.getInstCourseList();
                		String str=courseVec.get(0).toString();
		                courseVec.clear();
                		String str1[]=str.split(",");
		                for(int i=0;i<str1.length;i++){
                	        	courseVec.add(str1[i]);
                		}
				mainPanel.add(showLecture(client_obj.getSessionList(courseVec,client_obj.getIndexServerName())),BorderLayout.CENTER);
			}else if(((String)combo.getSelectedItem()).equals("noCourse")){
				announceLabel.setEnabled(false);
				StatusPanel.getController().setStatus(Language.getController().getLangValue("InstructorCSPanel.msg"));	
			}else {	
				announceLabel.setEnabled(true);
                                announceLabel.setText("<html><b><font color=blue><u>"+Language.getController().getLangValue("InstructorCSPanel.AnnounceLabel")+"</u></font></b></html>");
				announceLabel.addMouseListener(this);
				Vector courseName=new Vector();
				client_obj.setCourseForAnnounce((String)combo.getSelectedItem());
				course_id=(String)combo.getSelectedItem();
				courseName.addElement(course_id);
				mainPanel.add(showLecture(client_obj.getSessionList(courseName,client_obj.getIndexServerName())),BorderLayout.CENTER);
				MainWindow.getController().setCouseid(course_id);
			}
                	center_mainPanel.validate();
                       	mainPanel.revalidate();
               	}
		// Action for Join button 
		// Modified for signalling
		try{
			/*********   arvind ************/
			for(int i=0;i<join.length;i++){
				if(e.getSource()==join[i]){
					String lect_id=courseid.get(i).toString();
					System.out.println(lect_id);
					String str[]=lect_id.split("-");
					lect_id=str[0];
					System.out.println(lect_id);
					MainWindow.getController().setCouseid(str[1]);
					// store this lect_id in client objects for later use by this client.
					client_obj.setLectureID(lect_id);	
					// store role in client objects for later use by this client.
					if(!((client_obj.getUserRole()).equals("instructor")))
                                		client_obj.setUserRole("instructor");
					JoinSession.getController().goToLecture(lect_id);
					MainWindow.getController().getMenuItem5().setEnabled(true);
					MainWindow.getController().getMenuItem6().setEnabled(true);
					client_obj.setLectureInfo(lectinfoVector);
					client_obj.setLectureInfoIndex(i);
				}
			}
		}catch(Exception exc){System.out.println("Can't open GUI"+exc.getMessage());}
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
		
		if(ev.getComponent().getName().equals("reloadLabel.Action")) {
                	try {
				instCourseCombo_Panel.remove(instCourseCombo);
		                instCourseCombo=new JComboBox(reloadCourseList());
			        instCourseCombo.addActionListener(this);
			        instCourseCombo_Panel.add(instCourseCombo,BorderLayout.CENTER);
               	        	instCourseCombo_Panel.revalidate();
				mainPanel.remove(1);
				mainPanel.add(showLecture(client_obj.getSessionList(reloadCourseList(),client_obj.getIndexServerName())),BorderLayout.CENTER);
				StatusPanel.getController().setStatus(Language.getController().getLangValue("InstructorCSPanel.msg1"));
				reloadLabel.setCursor(defaultCursor);
				reloadLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                	} catch(Exception ex){ System.out.println("==================="+ex.getMessage());  }
			center_mainPanel.validate();
                        mainPanel.revalidate();
                }
	
		// Action for Lecture Info button
		if(ev.getComponent().getName().equals("lectureInfo.Action")) {
			try{
				for(int i=0;i<inLabel.length;i++) {
                			if(ev.getSource()==inLabel[i]) {
						LectureInfo info=new LectureInfo(i,lectinfoVector);
                    			}
        			}
			}catch(Exception e){	}		 	
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
				mainPanel.add(showLecture(client_obj.getSessionList(reloadCourseList(),client_obj.getIndexServerName())),BorderLayout.CENTER);
	     			mainPanel.revalidate();
                        }catch(Exception e){ log.setLog("  Error in Cancle action !!!!   ");  }
                }
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {	
		if(e.getComponent().getName().equals("announceLabel.Action"))
		 announceLabel.setText("<html><b><font color=blue><u>"+Language.getController().getLangValue("InstructorCSPanel.AnnounceLabel")+"</u></font></b></html>");

	}

	public void mouseExited(MouseEvent e){
		if(e.getComponent().getName().equals("announceLabel.Action"))
                announceLabel.setText("<html><b><font color=blue>"+Language.getController().getLangValue("InstructorCSPanel.AnnounceLabel")+"</font></b></html>");
	}
	
	//Modified By pratibha
	private void announceNewSession(){
			mainPanel.remove(1);
			announceLabel.setEnabled(false);
			announceLabel.setText("<html><b><font color=blue>"+Language.getController().getLangValue("InstructorCSPanel.AnnounceLabel")+"</font></b></html>");
                        announceLabel.removeMouseListener(this);
			mainPanel.add(AnnounceSessionPanel.getController().createGUI(),BorderLayout.CENTER);
			mainPanel.revalidate();
	}

	private void cancleLecture(int indexnumber){	
		try {
			String lect_id=courseid.get(indexnumber).toString();
                        String str[]=lect_id.split("-");
			lect_id=str[0];
        	        String idCourse = "lectValue="+URLEncoder.encode(lect_id,"UTF-8");
			String indexServerName=client_obj.getIndexServerName();
			String indexServer=indexServerName+"/ProcessRequest?req=cancleLecture&"+idCourse;
			if(!(indexServerName.equals(""))) {
				if(!(HttpsUtil.getController().getIndexingMessage(indexServer))){
                                	System.out.println(Language.getController().getLangValue("InstructorCSPanel.Messageialog1"));
				}
			}  else
				log.setLog("Insufficient indexServer name in cancleLecture() in InstructorCSPanel :"+indexServer);
		}catch(Exception e){/**System.out.**/}
	}
	
	/**
 	*Modofied by pratibha
 	**/
        protected JPanel getmainPanel(){
                return mainPanel;
        }

	protected JComboBox getinstCourseCombo(){
                return instCourseCombo;
        }

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
                byte[] decordedValue = new sun.misc.BASE64Decoder().decodeBuffer(encryptedData);
                String decryptedValue = new String(decordedValue);
                return decryptedValue;
        }
}	

