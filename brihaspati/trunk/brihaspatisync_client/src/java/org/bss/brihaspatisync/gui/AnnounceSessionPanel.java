package org.bss.brihaspatisync.gui;

/**
 * AnnounceSessionPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.util.Vector;
import java.util.StringTokenizer;
import java.net.URLEncoder;
import org.bss.brihaspatisync.util.HttpsUtil;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.util.DateUtil;
import org.bss.brihaspatisync.network.Log;
import java.net.URLEncoder;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> created 
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a> Modified 
 * @author <a href="mailto:pratibhaayadav@gmail.com">Pratibha Yadav</a> Modified
 * Modify action for announce new session on 20 Dec 2008
 */

public class AnnounceSessionPanel extends JPanel implements MouseListener{

	private JPanel mainPanel;
	private JPanel north_Panel;
        private JPanel center_Panel;
        private JPanel south_Panel;
	private Container con=null;

	private JLabel lect_name;
        private JLabel lect_Info;
        private JLabel phone,date;
        private JLabel time;
        //private JLabel email;
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
	private String lectValue;
	private ClientObject client_obj=ClientObject.getController();
	private static AnnounceSessionPanel annPanel=null; 
	private Log log=Log.getController();
	private int h=0;
	private int m=0;
	private int year=2011;
	private int month=1;
        private int day=1;


	/**
         *The Controller for AnnounceSessionPanel Class.
         */

	protected static AnnounceSessionPanel getController(){
                if (annPanel==null){
                        annPanel=new AnnounceSessionPanel();
                }
                return annPanel;
        }

	/**
	 * Create announce session GUI.
	 */
	
	protected JPanel createGUI(){
                mainPanel=new JPanel();
                mainPanel.setLayout(new BorderLayout());
		mainPanel.add(createNorthPanel(),BorderLayout.NORTH);
                mainPanel.add(createCenterPanel(),BorderLayout.CENTER);
                mainPanel.add(createSouthPanel(),BorderLayout.SOUTH);
		
                setLocation(100,100);
        	setVisible(true);
        	setSize(550, 600);
	
		return mainPanel;
	}

	/**
         * This method is used to create North Panel for announce Panle.
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
                getTimeIndexingServer();
                center_Panel=new JPanel();
                center_Panel.setLayout(new GridLayout(0,4,5,2));

                lect_name=new JLabel("<html>&nbsp<font color=black>Lecture Name</font><font color=red>*</font>");
		lect_Info=new JLabel("<html>&nbsp<font color=black>Lecture Info</font><font color=red>*</font>");
                phone=new JLabel("<html>&nbsp<font color=black>Phone No.</font><font color=red>*</font>");
                date=new JLabel("<html>&nbsp<font color=black>Lecture Date</font><font color=red>*</font>");
                time=new JLabel("<html>&nbsp<font color=black>Lecture Time</font><font color=red>*</font>");
                //email=new JLabel("<html>&nbsp<font color=black>Email</font><font color=blue>*</font>");
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
		if(day<10)
			dayBox.addItem("0"+Integer.toString(day));
		else
			 dayBox.addItem(Integer.toString(day));
                for(int i=1;i<32;i++){
			if(day != i) {
				if(i<10)
                                	dayBox.addItem("0"+Integer.toString(i));
	                        else
        	                        dayBox.addItem(Integer.toString(i));
			}
                }
		if(month<10)
			monthBox.addItem("0"+Integer.toString(month));
		else
			monthBox.addItem(Integer.toString(month));
                for(int i=1;i<13;i++){
			if(month != i){
	                        if(i<10)
        	                        monthBox.addItem("0"+Integer.toString(i));
                	        else
                        	        monthBox.addItem(Integer.toString(i));
			}
                }
                for(int i=year;i<=2050;i++)
                        yearBox.addItem(Integer.toString(i));
                dateEntryPanel.add(yearBox);
                dateEntryPanel.add(monthBox);
                dateEntryPanel.add(dayBox);

                //creating a new panel for Time entry
                JPanel timeEntryPanel=new JPanel();
                timeEntryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                hourBox=new JComboBox();
                minBox=new JComboBox();
		
		hourBox.addItem(Integer.toString(h));
                for(int i=0;i<=23;i++){
                        if(i<10)
                                hourBox.addItem("0"+Integer.toString(i));
			else
                                hourBox.addItem(Integer.toString(i));
                }
		minBox.addItem(Integer.toString(m));
                for(int i=0;i<60;i++){
                        if(i<10)
                                minBox.addItem("0"+Integer.toString(i));
                        else
                                minBox.addItem(Integer.toString(i));
                }
                timeEntryPanel.add(hourBox);
                timeEntryPanel.add(minBox);

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
		return center_Panel;
	}
	
	/**
	 * Creating south panel which contains time duration for which lecture is announce and "Announce" button.
         */

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
                annBttn=new JButton("<html><u><b><center><font color=blue>Announce</font></center></b></u>");

		//Modified by pratibha
		annBttn.setCursor(new Cursor(Cursor.HAND_CURSOR));
               	annBttn.addActionListener(AnnounceSessionAction.getController());
              	//End of modification
		//annBttn.addActionListener(this);
                south_Panel.add(duration);
                south_Panel.add(durationBox);
                south_Panel.add(new JLabel("      "));
                south_Panel.add(annBttn);
                return south_Panel;
	}

	/**
	 * Get Lecture Values from Announce Session Panel GUI.
	 */
	
	protected String getLectureValues(){
		String courseName="";
       		if((lectName_Text.getText().equals("")) ||(lecInfoArea.getText().equals(""))||(phone_Text.getText().equals(""))){
                	JOptionPane.showMessageDialog(null,"Please enter (*) mandatory fields");
              	}
		else{
	       		String st_year=(String)yearBox.getSelectedItem();
	               	String st_month=(String)monthBox.getSelectedItem();
                       	String st_day=(String)dayBox.getSelectedItem();
			int curdate=Integer.parseInt(client_obj.getServerDate());
	                int intforduedate=Integer.parseInt(st_year+st_month+st_day);
			boolean check=DateUtil.getController().checkDateInput(st_year,st_month,st_day);
			System.out.println(intforduedate+" < "+curdate);
			if(intforduedate < curdate)
			{
				JOptionPane.showMessageDialog(null,"Please checked the Lecture date !!");
				lectValue=null;
				return lectValue;
			}
			else if((intforduedate >= curdate)&& check){
				String st_hour=(String)hourBox.getSelectedItem();
               			String st_minutes=(String)minBox.getSelectedItem();
				String st_hour_st_minutes=st_hour+":"+st_minutes;	
				
				if(intforduedate == curdate){
					int totaltime=Integer.parseInt(st_hour)*60;
					totaltime=totaltime+Integer.parseInt(st_minutes);	
					if(totaltime< (DateUtil.getController().checkTimeInput())) {
						JOptionPane.showMessageDialog(null,"Please checked the Lecture Time !!");
						lectValue=null;
						return lectValue;
					}
				}
				
				System.out.println("1 "+st_hour_st_minutes);
				if(((String)lectName_Text.getText()).length()<6)
				{
					JOptionPane.showMessageDialog(null,"Please checked the Lecture Name atleast 5 !!");
                                        lectValue=null;  
					return lectValue;  
				}
	              		String st_duration=(String)durationBox.getSelectedItem();
				System.out.println("2 "+st_duration);
				
				if(!((client_obj.getCourseForAnnounce()).equals("")))
					courseName=client_obj.getCourseForAnnounce();
				if(courseName.equals("--Show All--")){
					JOptionPane.showMessageDialog(null,"Please select the Course except Show All !!");
					lectValue=null;
                                        return lectValue;
				}
				String vedeo="";	
                       	       	if(video.isSelected()==true){
					vedeo="1";
                       		}else{	
					vedeo="0";
				}
				
				String audio1="";
	                      	if(audio.isSelected()==true){
					audio1="1";
                            	}else{
					audio1="0";
                     		}
				System.out.println("9 "+audio1);
				String whiteboard1="1";
				if(whiteboard.isSelected()){
					whiteboard1="1";
        	               	}else{
					whiteboard1="0";
				}
				try {
					lectValue = "&"+"lectGetParameter="+URLEncoder.encode("GetAnnounceValues","UTF-8");
					lectValue =lectValue+"&"+ "lectUserName="+URLEncoder.encode(client_obj.getUserName(),"UTF-8");
					lectValue =lectValue+"&"+"lectCouseName="+URLEncoder.encode(courseName,"UTF-8");
					lectValue =lectValue+"&"+"lectName="+URLEncoder.encode((String)lectName_Text.getText(),"UTF-8");
					lectValue =lectValue+"&"+"lectInfo="+URLEncoder.encode((String)lecInfoArea.getText(),"UTF-8");
					lectValue =lectValue+"&"+"lectNo="+URLEncoder.encode((String)phone_Text.getText(),"UTF-8");
					lectValue =lectValue+"&"+"lectDate="+URLEncoder.encode(st_year+"-"+st_month+"-"+st_day,"UTF-8");
					lectValue =lectValue+"&"+"lectTime="+URLEncoder.encode(st_hour_st_minutes,"UTF-8");
					lectValue =lectValue+"&"+"lectDuration="+URLEncoder.encode(st_duration,"UTF-8");
					lectValue =lectValue+"&"+"lectAudio="+URLEncoder.encode(audio1,"UTF-8");
					lectValue =lectValue+"&"+"lectVedio="+URLEncoder.encode(vedeo,"UTF-8");
					lectValue =lectValue+"&"+"lectWhiteBoard="+URLEncoder.encode(whiteboard1,"UTF-8");
					System.out.println(lectValue);
				} catch(Exception es){}
			}
            	}//else
		
		return lectValue;
     	}

	/**
	 * If user press the Announce button then announce a new Session,
	 * this method is moved to AnnounceSessionAction.java
      	 */
/*	public void actionPerformed(ActionEvent e){
	}
*/	
	private void announceNewSession(){
		mainPanel.remove(1);
               	mainPanel.add(createGUI(),BorderLayout.CENTER);
              	mainPanel.revalidate();
        }

	protected JButton getannBttn(){
                return annBttn;
        }


 	/**
         * Action for mouse click in Announce Session Panel.
         */

	public void mouseClicked(MouseEvent ev) {
        	if(ev.getComponent().getName().equals("")){
		}
	}
	
	public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
	
	private void getTimeIndexingServer(){
		String indexServerName=client_obj.getIndexServerName();
		if(!(indexServerName.equals(""))){
			String  indexServer=indexServerName+"/ProcessRequest?req=getTimeforLecture&";
			indexServer=HttpsUtil.getController().getReflectorAddress(indexServer);
			String str[]=indexServer.split(" ");
			String str1[]=str[0].split("/");
			
			year=Integer.parseInt(str1[0]);
			month=Integer.parseInt(str1[1]);
			day=Integer.parseInt(str1[2]);
			
			
			String str2[]=str[1].split(":");
			h=Integer.parseInt(str2[0]);
			m=Integer.parseInt(str2[1]);
           	}else{
                	System.out.println("insufficient indexServer name in AnnounceSession :" + indexServerName);
            	}
	}
}//end of class
