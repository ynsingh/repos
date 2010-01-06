package org.bss.brihaspatisync.gui;

/**
 * UpdateSessionPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.TitledBorder;
import java.util.StringTokenizer;
import java.net.URLEncoder;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.DateUtil;

import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a>
 */

public class UpdateSessionPanel extends JFrame implements ActionListener, MouseListener{

	private JFrame frame=null;

	/**Button for closing the window*/

        private JLabel closeLabel=null;
        private String courseId=null;
	
	/**Panel to hold the close Button*/

        private JPanel buttonPan=null;

	private JPanel mainPanel;
	private JPanel north_Panel;
        private JPanel center_Panel;
        private JPanel south_Panel;
	private Container con=null;

	private JLabel lect_name;
        private JLabel lect_Info;
        private JLabel phone,date;
        private JLabel time;
        private JLabel email;
        private JLabel duration;
        private JLabel repeat;
        private JLabel repeat_for_time;
        private JTextField lectName_Text;
        private JTextField phone_Text;
        private JComboBox hourBox;
        private JComboBox minBox;
        
	private JComboBox dayBox;
        private JComboBox monthBox;
        private JComboBox yearBox;
	
        private JComboBox durationBox;
        private JComboBox repeatBox;
        private JComboBox repeat_for_timeBox;
        private JTextField atRate;
        private JTextField urlText;
        private JTextField endText;
        private JScrollPane lectInfo_Pane;
        private JTextArea lecInfoArea;
        private JCheckBox audio;
        private JCheckBox video;
        private JCheckBox whiteboard;
        private JButton annBttn;

	private Vector returnVector=null;
	private StringBuffer lectValue;
	private Log log=Log.getController();	
	private ClientObject client_obj=ClientObject.getController();
	private InstructorCSPanel insCSPanel=InstructorCSPanel.getController();
	

	private static UpdateSessionPanel annPanel=null; 
	
	/**
         *The Controller for UpdateSessionPanel Class.
         */

	protected static UpdateSessionPanel getController(){
                if (annPanel==null){
                        annPanel=new UpdateSessionPanel();
                }
                return annPanel;
        }
	
	protected JPanel createGUI(int indexnumber,Vector updatevector){
        	mainPanel=new JPanel();
                mainPanel.setLayout(new BorderLayout());
		mainPanel.add(createNorthPanel(),BorderLayout.NORTH);
                mainPanel.add(createCenterPanel(),BorderLayout.CENTER);
                mainPanel.add(createSouthPanel(),BorderLayout.SOUTH);
		setLectureValues(indexnumber,updatevector);
		frame=new JFrame();
  		frame.setTitle("Session Description");                           /**Setting the title of the Frame*/
                frame.getContentPane().add(mainPanel);                           /**Adding panel to the frame*/
                frame.setSize(850,250);                                          /**Setting the size of the frame*/
                frame.setVisible(true);                                          /**Showing the frame*/
                frame.setLocation(100,100);
		return mainPanel;
	}

	private void setLectureValues(int indexnumber,Vector updatevector){
		
		indexnumber=(indexnumber*13)+1;
		courseId=((String)updatevector.get(indexnumber-1));
		log.setLog("c         "+courseId);
		lectName_Text.setText(((String)updatevector.get(indexnumber)).replaceAll("#",""));	
		phone_Text.setText(((String)updatevector.get(indexnumber+3)).replaceAll("#",""));	
		lecInfoArea.setText(((String)updatevector.get(indexnumber+1)).replaceAll("#",""));
		String updatemailid=((String)updatevector.get(indexnumber+2));
		String updatemailidarry[]=updatemailid.split("@");
		urlText.setText(updatemailidarry[0]);
		atRate.setText("@");	
		endText.setText(updatemailidarry[1]);
		if(((String)updatevector.get(indexnumber+4)).equals("1"))
			audio.setSelected(true);	
		else
			audio.setSelected(false);
		if(((String)updatevector.get(indexnumber+5)).equals("1"))
                        video.setSelected(true);
                else
                        video.setSelected(false);

		updatemailidarry=null;
		updatemailid=((String)updatevector.get(indexnumber+7)).substring(0,10);	
		updatemailidarry=updatemailid.split("-");
		yearBox.setSelectedItem(updatemailidarry[0]);	
		monthBox.setSelectedItem(updatemailidarry[1]);	
		dayBox.setSelectedItem(updatemailidarry[2]);	
		updatemailid=(String)updatevector.get(indexnumber+8);
		updatemailidarry=null;
		updatemailidarry=updatemailid.split(":");	
		hourBox.setSelectedItem(updatemailidarry[0]);
		minBox.setSelectedItem(updatemailidarry[1]);
		durationBox.setSelectedItem((String)updatevector.get(indexnumber+9)+":Hour");	
		
	}	

	/**
         * This method is used to create GUI for announce a new Session.
         */

        private JPanel createNorthPanel(){
                //Creating North Panel
                north_Panel=new JPanel();
                north_Panel.setLayout(new FlowLayout(FlowLayout.CENTER));
                north_Panel.setBackground(Color.LIGHT_GRAY);
                north_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
                video=new JCheckBox("<html><font color=green>Video");
                video.setBackground(Color.LIGHT_GRAY);
                audio=new JCheckBox("<html><font color=green>Audio");
                audio.setBackground(Color.LIGHT_GRAY);
                whiteboard=new JCheckBox("<html><font color=green>WhiteBoard");
                whiteboard.setBackground(Color.LIGHT_GRAY);
                north_Panel.add(new JLabel("                           "));
                north_Panel.add(audio);
                north_Panel.add(video);
                north_Panel.add(whiteboard);
                whiteboard.setSelected(true);
                north_Panel.add(new JLabel("                            "));
		
		return north_Panel;
	}
	
	private JPanel createCenterPanel(){
                //Creating Center Panel
                center_Panel=new JPanel();
                center_Panel.setLayout(new GridLayout(0,4,5,2));

                lect_name=new JLabel("<html>&nbsp<font color=black>Lecture Name</font><font color=blue>*</font>");
		lect_Info=new JLabel("<html>&nbsp<font color=black>Lecture Info</font><font color=blue>*</font>");
                phone=new JLabel("<html>&nbsp<font color=black>Phone No.</font>");
                date=new JLabel("<html>&nbsp<font color=black>Lecture Date</font><font color=blue>*</font>");
                time=new JLabel("<html>&nbsp<font color=black>Lecture Time</font><font color=blue>*</font>");
                email=new JLabel("<html>&nbsp<font color=black>Email</font><font color=blue>*</font>");
                lectName_Text=new JTextField();
                phone_Text=new JTextField();
                lecInfoArea=new JTextArea();
                lectInfo_Pane=new JScrollPane(lecInfoArea);

                //creating a new Panel for date choose
                JPanel dateEntryPanel=new JPanel();
                dateEntryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                dayBox=new JComboBox();
                monthBox=new JComboBox();
                yearBox=new JComboBox();
                for(int i=1;i<32;i++){
                        if(i<10)
                                dayBox.addItem("0"+Integer.toString(i));
                        else
                                dayBox.addItem(Integer.toString(i));
                }
                for(int i=1;i<13;i++){
                        if(i<10)
                                monthBox.addItem("0"+Integer.toString(i));
                        else
                                monthBox.addItem(Integer.toString(i));
                }
                for(int i=2005;i<=2050;i++)
                        yearBox.addItem(Integer.toString(i));
                dateEntryPanel.add(yearBox);
                dateEntryPanel.add(monthBox);
                dateEntryPanel.add(dayBox);

                //creating a new panel for Time entry
                JPanel timeEntryPanel=new JPanel();
                timeEntryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                hourBox=new JComboBox();
                minBox=new JComboBox();
                for(int i=0;i<=23;i++){
                        if(i<10)
                                hourBox.addItem("0"+Integer.toString(i));
			else
                                hourBox.addItem(Integer.toString(i));
                }
                for(int i=0;i<60;i++){
                        if(i<10)
                                minBox.addItem("0"+Integer.toString(i));
                        else
                                minBox.addItem(Integer.toString(i));
                }
                timeEntryPanel.add(hourBox);
                timeEntryPanel.add(minBox);

                //creating a new Panel for email entry
                JPanel mailPanel=new JPanel();
                mailPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                atRate=new JTextField("@");
                atRate.setEditable(false);
                urlText=new JTextField(5);
                endText=new JTextField(5);
                mailPanel.add(urlText);
                mailPanel.add(atRate);
                mailPanel.add(endText);

                center_Panel.add(lect_name);
                center_Panel.add(lectName_Text);
                center_Panel.add(lect_Info);
                center_Panel.add(lectInfo_Pane);
                center_Panel.add(phone);
                center_Panel.add(phone_Text);
                center_Panel.add(date);
                center_Panel.add(dateEntryPanel);
                center_Panel.add(time);
                center_Panel.add(timeEntryPanel);
                center_Panel.add(email);
                center_Panel.add(mailPanel);

		 return center_Panel;
	}

	private JPanel createSouthPanel(){
                //creating South Panel
                south_Panel=new JPanel();
                south_Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		south_Panel.setBackground(Color.LIGHT_GRAY);
                south_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
                JLabel duration=new JLabel("<html>&nbsp<font color=black>Duration</font><font color=blue>*</font>");
                durationBox=new JComboBox();
                for(int i=1;i<=24;i++)
                        durationBox.addItem(Integer.toString(i)+":Hour");

                JLabel repeat=new JLabel("<html>&nbsp<font color=black>Repeat</font>");
                repeatBox=new JComboBox();
                repeat_for_timeBox=new JComboBox();
                repeatBox.addActionListener(new ActionListener(){
                	public void actionPerformed(ActionEvent er){
                        	if(((String)repeatBox.getSelectedItem()).equals("No")){
                                	repeat_for_timeBox.setEnabled(false);
				}
                                else{
                                	repeat_for_timeBox.setEnabled(true);
				}
			}
		});
	
                JLabel repeat_for_time=new JLabel("<html>&nbsp<font color=black>Repeat For Time</font>");
                try{
                        repeatBox.removeAllItems();
                }catch(Exception e){}
                repeatBox.addItem("No");
                repeatBox.addItem("Daily");
                repeatBox.addItem("Every 7 Days");
                repeatBox.addItem("Every 15 Days");
                try{
                        repeat_for_timeBox.removeAllItems();
                }catch(Exception e){}
                repeat_for_timeBox.addItem("7 Days");
                repeat_for_timeBox.addItem("15 Days");
                repeat_for_timeBox.addItem("30 Days");
                repeat_for_timeBox.addItem("60 Days");
                repeat_for_timeBox.setEnabled(false);
		
		ClassLoader clr= this.getClass().getClassLoader();	
		closeLabel=new JLabel(new ImageIcon(clr.getResource("resources/images/close.jpg")));
		closeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                closeLabel.addMouseListener(this);
                closeLabel.setName("closeLabel.Action");
                closeLabel.addMouseListener(this);


                annBttn=new JButton("<html><u><b><center><font color=blue>Update</font></center></b></u>");
		annBttn.addActionListener(this);
                south_Panel.add(duration);
                south_Panel.add(durationBox);
                south_Panel.add(new JLabel("      "));
                south_Panel.add(annBttn);
                south_Panel.add(new JLabel("      "));
                south_Panel.add(closeLabel);
                return south_Panel;
	}

	/**
	 * Get Lecture Values from  Announce Session Panel.
	 */
			
	private String getLectureValues(){
	if((lectName_Text.getText().equals(""))|| (urlText.getText().equals(""))||(lecInfoArea.getText().equals(""))||(endText.getText().equals(""))||(phone_Text.getText().equals(""))){
			JOptionPane.showMessageDialog(null,"Please enter (*) mandatory fields");
                }
                else{
			DateUtil date=DateUtil.getController();
                        String st_year=(String)yearBox.getSelectedItem();
                        String st_month=(String)monthBox.getSelectedItem();
                        String st_day=(String)dayBox.getSelectedItem();
                        int curdate=Integer.parseInt(client_obj.getServerDate());
			int intforduedate=Integer.parseInt(st_year+st_month+st_day);
                        boolean check=date.checkDateInput(st_year,st_month,st_day);
                        if(intforduedate < curdate)
                        {
                                JOptionPane.showMessageDialog(null,"Please checked the Duration date !!");
                                lectValue=null;
                                return lectValue.toString();
                        }
                        else if((intforduedate >= curdate)&& check){
                                String st_hour=(String)hourBox.getSelectedItem();
                                String st_minutes=(String)minBox.getSelectedItem();
                                if(intforduedate == curdate){
                                        int totaltime=Integer.parseInt(st_hour)*60;
                                        totaltime=totaltime+Integer.parseInt(st_minutes);
                                        if(totaltime< (date.checkTimeInput())) {
                                                JOptionPane.showMessageDialog(null,"Please checked the Duration Time !!");
                                                lectValue=null;
                                                return lectValue.toString();
                                        }
                                }
				lectValue=new StringBuffer(500);
				String courseName="";
				 if(!((client_obj.getCourseForAnnounce()).equals("")))
                                        courseName=client_obj.getCourseForAnnounce();
				if(courseName.equals("--Show All--")){
                                        JOptionPane.showMessageDialog(null,"Please select the Course except Show All !!");
                                        lectValue=null;
                                        return lectValue.toString();
                                }
				
				String st_duration=(String)durationBox.getSelectedItem();
				StringTokenizer st=new StringTokenizer(st_duration,":");
				
				lectValue.append("GetUpdateLectValues");
				lectValue.append("$"+courseId);
				lectValue.append("$");
				lectValue.append(courseName);
				lectValue.append("$");
				lectValue.append((String)lectName_Text.getText());
				lectValue.append("$");
				lectValue.append((String)lecInfoArea.getText());
				lectValue.append("$");
				lectValue.append((String)(urlText.getText()+atRate.getText()+endText.getText()));
				lectValue.append("$");
				lectValue.append((String)phone_Text.getText());
				lectValue.append("$");
					
                       	       	if(video.isSelected()==true){
					lectValue.append("1");
					lectValue.append("$");
                       		}else{	
					lectValue.append("0");
					lectValue.append("$");
				}
	                      	if(audio.isSelected()==true){
					lectValue.append("1");
					lectValue.append("$");
                            	}else{
					lectValue.append("0");
					lectValue.append("$");

                     		}
				if(whiteboard.isSelected()==true){
					lectValue.append("1");
					lectValue.append("$");
        	               	}else{
					lectValue.append("0");
					lectValue.append("$");
				}
				lectValue.append(st_year+"-"+st_month+"-"+st_day);
				lectValue.append("$");
				lectValue.append(st_hour+":"+st_minutes);
				lectValue.append("$");

				lectValue.append(st.nextToken());
				lectValue.append("$");

                	        if(((String)repeatBox.getSelectedItem()).equals("No")){
					lectValue.append("No");
					lectValue.append("$");
					lectValue.append("No");
					lectValue.append("$");
                             	}	
				else{
					lectValue.append((String)repeatBox.getSelectedItem());
					lectValue.append("$");
					lectValue.append((String)repeat_for_timeBox.getSelectedItem());
					lectValue.append("$");
                        	}
			}//if
            	}//else
		return lectValue.toString();
     	}

	/**
	 * If user press the Announce button then announce a new Session.
      	 */
	public void actionPerformed(ActionEvent e){
        	if(e.getSource()==annBttn){
			//BufferedReader in=null;
			//URL indexurl=null;
			try{
				String lectValue = "lectValue="+URLEncoder.encode(getLectureValues(),"UTF-8");
                                String indexServerName=client_obj.getIndexServerName();

                                if(!(indexServerName.equals(""))){
                                        String  indexServer=indexServerName+"/ProcessRequest?req=putLecture&"+lectValue;
                                        if(HttpsUtil.getController().getIndexingMessage(indexServer)){
						/********************* modified ******************************/
                                                JOptionPane.showMessageDialog(null," Update lecture successfully !!");
						frame.dispose();
						insCSPanel.getmainPanel().remove(1);
                                                        Vector course_Name=client_obj.getInstCourseList();
                                                        insCSPanel.getmainPanel().add(insCSPanel.showLecture(client_obj.getSessionList(course_Name,client_obj.getIndexServerName())),BorderLayout.CENTER);
                                                        insCSPanel.getmainPanel().revalidate();
                                                        insCSPanel.getinstCourseCombo().setSelectedItem("--Show All--");
						/*************************************************************/
                                        }else
                                                JOptionPane.showMessageDialog(null,"There are some problem in Update lecture !!");
                                }else{
                                        log.setLog("insufficient indexServer name in UpdateSession :" + indexServerName);
                                }
			}catch(Exception ex){log.setLog("Error at actionPerformed()in UpdateSessionPanel"+ex.getMessage());}
		}//if
     	}

 	/**
         * Action for mouse click in Announce Session Panel.
         */

	public void mouseClicked(MouseEvent ev) {
        	if(ev.getComponent().getName().equals("")){
		}
		/*******  Modified *******/
		if(ev.getComponent().getName().equals("closeLabel.Action")){
                  frame.dispose();
                }
		/************************/
	}
	
	public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
	
}//end of class
