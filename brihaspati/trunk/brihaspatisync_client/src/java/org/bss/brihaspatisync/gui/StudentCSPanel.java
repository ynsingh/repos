package org.bss.brihaspatisync.gui;

/**
 * StudentCSPanel.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2007-2008 ETRG, IIT Kanpur.
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
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.util.Vector;
import java.util.Date;
import org.bss.brihaspatisync.util.ClientObject;
import org.bss.brihaspatisync.network.Log;

/**
 * @author <a href="mailto:ashish.knp@gmail.com">Ashish Yadav </a> 
 */

public class StudentCSPanel extends JPanel implements ActionListener, MouseListener{

	private JPanel mainPanel;
	private JPanel north_mainPanel;
	private JPanel center_mainPanel;
	private JLabel studLabel;
	private JComboBox studCourseCombo;
	private JScrollPane scrollPane1;
	private Vector courseName=null;
	private JButton runButton[]=null;
	private JLabel serialNo[]=null;
	private JLabel descLabel[]=null; 
	private JLabel nameLabel[]=null;
	private JPanel buttonPanel[] =null;
        private JPanel nsPane[]=null;
	private String lect_id="";
	private Vector courseVec=null;
	private Vector lectinfoVector=null;
	private ClientObject client_obj=ClientObject.getController();
	private static StudentCSPanel studcspanel=null;
	private Log log=Log.getController();

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
		studLabel=new JLabel("<html><b>Course in which you are resistered as a student</b></html>");
		courseVec=client_obj.getStudCourseList();
		courseVec.add(0,"--Show All--");
		studCourseCombo=new JComboBox(courseVec);
		studCourseCombo.addActionListener(this);	
		
		north_mainPanel.add(studLabel,BorderLayout.WEST);
		north_mainPanel.add(studCourseCombo,BorderLayout.CENTER);
	   	mainPanel.add(north_mainPanel, BorderLayout.NORTH);
	   	
	       	mainPanel.add(showLecture(client_obj.getStudSessionList()), BorderLayout.CENTER);
    		return mainPanel;
	}
	
	/**
	 * Create Grid View for lectures detail.
	 */
	private JScrollPane showLecture(Vector lectureVector){
		lectinfoVector=lectureVector;
		int y=lectureVector.size();
		int x=y/13;
		int p=1;
	
		runButton=new JButton[x];			
		nameLabel=new JLabel[x];
		descLabel=new JLabel[x];
		serialNo=new JLabel[x];
		buttonPanel=new JPanel[x];
	        nsPane=new JPanel[x];
		center_mainPanel=new JPanel();
    	
 		center_mainPanel.setLayout(new GridLayout(0,2,5,3));
    		center_mainPanel.setBorder(BorderFactory.createTitledBorder("Announced Sessions "));
		center_mainPanel.add(new JLabel("<html><b><U><font color=green>LECTURE NAME</font></U></b>",0));
		center_mainPanel.add(new JLabel("<html><b><U><font color=green>ACTIONS</font></U></b>",0));
		
		int curdate=Integer.parseInt(client_obj.getServerDate());
                int check=8;
                String checkdatevector[]=new String[x];
                int tempint=0;
                for(int i=0;i<y;i++)
                {
                        if(check==i) {
                     checkdatevector[tempint]=((String)lectureVector.elementAt(i)).substring(0,10).replaceAll("-","");


                        tempint++;
                        check=check+13;
                        }
                }
	       	for(int i=0;i<x;i++){
			nsPane[i]=new JPanel();
                        nsPane[i].setBorder(BorderFactory.createLineBorder(Color.gray));
		        String textLabel=(String)lectureVector.elementAt(p);
			nameLabel[i]=new JLabel(textLabel);
			nsPane[i].add(nameLabel[i]);
			int checkintdate=Integer.parseInt(checkdatevector[i]);
                        if(textLabel.endsWith("#") && (checkintdate == curdate)) {
                    		runButton[i]=new JButton("Join");
				runButton[i].addActionListener(this);

               		}
			else {
				runButton[i]=new JButton("");
                    	
			}
			buttonPanel[i]=new JPanel();
                       	buttonPanel[i].setLayout(new FlowLayout());
                       	buttonPanel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			ClassLoader clr= this.getClass().getClassLoader();
                       	descLabel[i]=new JLabel("<html><Font color=blue><u>Lecture Info</u></font></html>",new ImageIcon(clr.getResource("resources/images/info.gif")),0);
			descLabel[i].addMouseListener(this);
			descLabel[i].setName("lectureInfo.Action");
			descLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			buttonPanel[i].add(runButton[i]);
			buttonPanel[i].add(descLabel[i]);
			center_mainPanel.add(nsPane[i]);
			center_mainPanel.add(buttonPanel[i]);
               		p=p+13;
		}
       		if(p==1){
			JOptionPane.showMessageDialog(null,"No Lecture in this course");
			return new JScrollPane();
		}else{
			scrollPane1=new JScrollPane(center_mainPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			return scrollPane1;
		}  		
	}

	/**
	 * getLectureID(lectVector) is used to get Lecture ID from particular Lecture Details.
	 */
	private Vector getLectureID(Vector lectVector){
                int p=0;
                Vector lect_id=new Vector();
                try{
                        for(int i=0;i<lectVector.size();i++){
                                lect_id.addElement(lectVector.elementAt(p));
                                p=p+13;
                                if((lectVector.size()-1)<p)
                                        break;
                                log.setLog("Lecture Id Vector"+lect_id);
                        }
                }catch(Exception e){log.setLog(e.getMessage());}
                return lect_id;
        }

	public void actionPerformed(ActionEvent e) {
		// Action for Combobox
  		if(e.getSource()==studCourseCombo){
      			JComboBox combo = (JComboBox)e.getSource();
        		mainPanel.remove(1);
			if(((String)combo.getSelectedItem()).equals("--Show All--")){
				courseName=client_obj.getStudCourseList();
                                mainPanel.add(showLecture(client_obj.getSessionList(courseName,client_obj.getIndexServerName())),BorderLayout.CENTER);
                        }else{
                                courseName=new Vector();
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
					lect_id=(String)(getLectureID(lectinfoVector)).elementAt(i);
					// store this lect_id in client objects for later use by this client.
                                        client_obj.setLectureID(lect_id);
                                       	log.setLog("lectid====="+lect_id);
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
                     				int p=i;
                       	  			LectureInfo info=new LectureInfo(p,lectinfoVector);
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
	
