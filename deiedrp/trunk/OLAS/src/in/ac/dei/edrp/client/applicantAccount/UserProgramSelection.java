/*
 * @(#) UserProgramSelection.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.client.applicantAccount;

import in.ac.dei.edrp.client.EdeiAdmission.EDEIExistingProgramSummarySheet;
import in.ac.dei.edrp.client.EdeiAdmission.EDEIStudentBean;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.applicantAccountEDEI.AccountLoginEDEI;
import in.ac.dei.edrp.client.applicantAccountEDEI.AccountLoginEDEIAsync;
import in.ac.dei.edrp.client.applicantAccountEDEI.ApplicantAccountEDEIBean;
import in.ac.dei.edrp.client.applicantAccountEDEI.UserAccountEDEI;
import in.ac.dei.edrp.client.applicantAccountEDEI.UserLoginEDEI;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.Label;

/**
 * 
 * @version 1.0 9 
 * @date AUGUST 13 2012
 * @author DEVENDRA SINGHAL
 */
public class UserProgramSelection {
	ApplicantAccountAsync serviceAsync = GWT.create(ApplicantAccount.class);
	AccountLoginEDEIAsync connectService = (AccountLoginEDEIAsync) GWT.create(AccountLoginEDEI.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	String uId;
	String userName;
	String uniNickName;
	String universityId;
	String sessionStartDate;
	String sessionEndDate;
	String programId;
	String entityId;
	public VerticalPanel mainPanel;
	VerticalPanel headerPanel;
	HorizontalPanel bodyPanel;
	HorizontalPanel bodyMainPanel;
	HorizontalPanel bodyDesPanel;
	VerticalPanel footerPanel;
	HorizontalPanel HypMainPanelTop;
	HorizontalPanel linkPanelTop;
	final Label headerLabel = new Label(constants.title());
	final Label headerLabel1 = new Label(constants.heading1());
	final Label headerLabel2 = new Label(constants.heading2());
	final Label footerLabel = new Label(constants.footer()+constants.footer1());
	final Hyperlink blank = new Hyperlink("", "");	
	Hyperlink signOut=new Hyperlink(constants.signOut(),"");
	
	public UserProgramSelection(String uId,String userName,String uniNickName){
		this.uId=uId;
		this.userName=userName;
		this.uniNickName=uniNickName;	
	}
	public void onModuleLoad() {
		init();
	}
	VerticalPanel init() {
		connectService.getUniversityDetailsDEINew(uniNickName,new AsyncCallback<List<ApplicantAccountEDEIBean>>() {
			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.getMessage());
			}
			public void onSuccess(List<ApplicantAccountEDEIBean> arg0) {				
				universityId=arg0.get(0).getUniversityId();
				sessionStartDate=arg0.get(0).getSessionStartDate();
				sessionEndDate=arg0.get(0).getSessionEndDate();				
			}
		});
		
		bodyPanel = new HorizontalPanel();
		mainPanel = new VerticalPanel();
		headerPanel = new VerticalPanel();
		bodyMainPanel=new HorizontalPanel();
		bodyDesPanel=new HorizontalPanel();
		footerPanel = new VerticalPanel();
		HypMainPanelTop = new HorizontalPanel();
		linkPanelTop = new HorizontalPanel();			
		
		mainPanel.setWidth("100%");
		mainPanel.setHeight("100%");
		headerPanel.setWidth("100%");
		bodyMainPanel.setWidth("100%");
		footerPanel.setWidth("100%");
		HypMainPanelTop.setWidth("100%");
		
		headerPanel.setStyleName("headerGrayBack");
		footerPanel.setStyleName("footerGrayBack");
		HypMainPanelTop.setStyleName("logOutPanel1");
		Label welcomeLabel = new Label(constants.welcome()+" ");
		headerLabel.setStyleName("MainHeading");
		headerLabel1.setStyleName("heading1");
		headerLabel2.setStyleName("heading1");
		footerLabel.setStyleName("footerLabel");
		linkPanelTop.add(welcomeLabel);

		connectService.getUserNameForAccount(userName,new AsyncCallback<String>() {
			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.getMessage());
			}
			public void onSuccess(String name) {
				linkPanelTop.add(new Label(name));
			}
		});
		
		headerPanel.setSpacing(5);
		headerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		footerPanel.setSpacing(5);
		footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);		
		
		HorizontalPanel signoutPanel=new HorizontalPanel();
		signoutPanel.add(signOut);
		
		HypMainPanelTop.add(signoutPanel);
		HypMainPanelTop.setCellHorizontalAlignment(signoutPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		HypMainPanelTop.setSpacing(5);
		
//		HypMainPanelTop.add(linkPanelTop);
//		HypMainPanelTop.setCellHorizontalAlignment(linkPanelTop,HasHorizontalAlignment.ALIGN_CENTER);
//		HypMainPanelTop.setCellVerticalAlignment(linkPanelTop,HasVerticalAlignment.ALIGN_MIDDLE);
		headerPanel.add(headerLabel);
		headerPanel.add(headerLabel1);
		headerPanel.add(headerLabel2);
		headerPanel.add(linkPanelTop);
		headerPanel.setCellHorizontalAlignment(linkPanelTop, HasHorizontalAlignment.ALIGN_LEFT);
		headerPanel.add(HypMainPanelTop);
		bodyPanel.setHeight("465");
		bodyDesPanel.setHeight("465");
		bodyDesPanel.setWidth("500");
		bodyDesPanel.setSpacing(5);
		bodyMainPanel.setHeight("465");
		mainPanel.setCellVerticalAlignment(footerPanel, HasVerticalAlignment.ALIGN_BOTTOM);
		
		Panel btnPanel=new Panel(constants.studentType());
		btnPanel.setWidth("180");
		Button newButton=new Button(constants.newStudent());
		Button oldButton=new Button(constants.oldStudent());
		Button repButton=new Button(constants.repeater());
		Button remButton=new Button(constants.remedial());	
		
		btnPanel.add(newButton);
		btnPanel.add(oldButton);
		btnPanel.add(repButton);
		btnPanel.add(remButton);			
		bodyPanel.add(btnPanel);
		
		newButton.setSize("180", "20");
		oldButton.setSize("180", "20");
		repButton.setSize("180", "20");
		remButton.setSize("180", "20");
	
		
		signOut.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				RootPanel.get().clear();
				mainPanel.clear();
				UserLoginEDEI login = new UserLoginEDEI(uId);
				login.onModuleLoad();
				RootPanel.get().add(login.mainPanel);
			}
		});
		
		newButton.addListener(new ButtonListenerAdapter(){

			@Override
			public void onClick(Button button, EventObject e) {
				bodyDesPanel.clear();
				RootPanel.get().clear();
				UserAccountEDEI ul=new UserAccountEDEI(uId,userName,uniNickName);
				ul.onModuleLoad();
				RootPanel.get().add(ul.mainPanel);
			}
			
		});
		oldButton.addListener(new ButtonListenerAdapter(){

			@Override
			public void onClick(Button button, EventObject e) {
				bodyDesPanel.clear();
				bodyDesPanel.add(oldStudents());
				
			}
			
		});
		repButton.addListener(new ButtonListenerAdapter(){

			@Override
			public void onClick(Button button, EventObject e) {
				bodyDesPanel.clear();
				// TODO Auto-generated method stub
				
			}
			
		});
		remButton.addListener(new ButtonListenerAdapter(){

			@Override
			public void onClick(Button button, EventObject e) {
				bodyDesPanel.clear();
				// TODO Auto-generated method stub
				
			}
			
		});
		
		bodyPanel.setBorderWidth(1);
		bodyDesPanel.setBorderWidth(1);
		FlexTable bodyTable=new FlexTable();
		bodyTable.setCellSpacing(5);
		bodyTable.setWidget(0, 0, bodyPanel);
		bodyTable.setWidget(0, 1, bodyDesPanel);
		bodyMainPanel.add(bodyTable);
		footerPanel.add(footerLabel);		
		mainPanel.add(headerPanel);
		mainPanel.add(bodyMainPanel);
		mainPanel.add(footerPanel);
		
		return mainPanel;

	}
	
	Panel oldStudents(){
		final Panel oldPanel=new Panel(constants.userInfo());
		oldPanel.setPaddings(5);
		FieldSet fieldSetOld=new FieldSet(constants.selectProgramType());
		FlexTable programTable=new FlexTable();
		programTable.setCellSpacing(3);
		HTML programLabel=new HTML("<h9><b>Want to go for Existing Program or New Program ?</b><h9>");
		Label existingLabel=new Label(constants.existingProgram());
		Label newProgramLabel=new Label(constants.newProgram());
		final RadioButton existingRadio=new RadioButton("programType");
		existingRadio.setChecked(true);
		RadioButton newRadio=new RadioButton("programType");
		Button okButton=new Button(constants.okButton());
		programTable.setWidget(1, 0, existingLabel);
		programTable.setWidget(1, 1, existingRadio);
		programTable.setWidget(2, 0, newProgramLabel);
		programTable.setWidget(2, 1, newRadio);
		programTable.setWidget(3, 0, okButton);
		fieldSetOld.add(programLabel);
		fieldSetOld.add(programTable);
		fieldSetOld.setWidth("490");
		
		oldPanel.add(fieldSetOld);
		
		final FlexTable userInfoTable=new FlexTable();
		userInfoTable.setCellSpacing(4);
		final FieldSet personalFieldSet=new FieldSet(constants.personal_details());
		personalFieldSet.setWidth("490");
		personalFieldSet.add(userInfoTable);
		oldPanel.add(personalFieldSet);
		personalFieldSet.setVisible(false);						
		okButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {	
				if(existingRadio.isChecked()){				
					userInfoTable.clear();
					personalFieldSet.setVisible(true);
					Label enrollLabel=new Label(constants.enrollmentNumber());
					Label rollLabel=new Label(constants.rollNumber());
					final TextBox enrollmentNo=new TextBox();
					final TextBox rollNo=new TextBox();
					Button okButton1=new Button(constants.okButton());
					userInfoTable.setWidget(0, 0, enrollLabel);
					userInfoTable.setWidget(0, 1, enrollmentNo);
					userInfoTable.setWidget(2, 0, rollLabel);
					userInfoTable.setWidget(2, 1, rollNo);
					userInfoTable.setWidget(3, 0, okButton1);	
					okButton1.addListener(new ButtonListenerAdapter(){
						@Override
						public void onClick(Button button,EventObject e){	
							final String enrollment=enrollmentNo.getText();
							final String rollNumber=rollNo.getText();												
							if(enrollment==null || enrollment.trim().length()==0){							
								MessageBox.alert(msgs.selectEnroll());							
							}
							else if(rollNumber==null || rollNumber.trim().length()==0){
								MessageBox.alert(msgs.selectRoll());						
							}
							else{								
								final EDEIStudentBean bean=new EDEIStudentBean();
								bean.setEnrollmentNumber(enrollment);
								bean.setRollNumber(rollNumber);
								
								serviceAsync.getExistingProgramDetail(bean, new AsyncCallback<List<EDEIStudentBean>>(){
									@Override
									public void onFailure(Throwable arg0) {
										MessageBox.alert(arg0.getMessage());
									}
	
									@Override
									public void onSuccess(List<EDEIStudentBean> arg0) {
										bean.setProgCourseKey(arg0.get(0).getProgCourseKey());			
										programId=arg0.get(0).getProgramId();
										entityId=arg0.get(0).getEntityId();
										serviceAsync.getAvailableModule(bean, new AsyncCallback<List<EDEIStudentBean>>(){
											@Override
											public void onFailure(Throwable arg0) {
												MessageBox.alert(arg0.getMessage());
											}
	
											@Override
											public void onSuccess(List<EDEIStudentBean> result) {	
												RootPanel.get().clear();
												EDEIExistingProgramSummarySheet summarySheet=new EDEIExistingProgramSummarySheet(result,enrollment,rollNumber,programId,entityId,uniNickName,userName,sessionStartDate,sessionEndDate,universityId,uId);
												summarySheet.onModuleLoad();
												RootPanel.get().add(summarySheet.mainPanel);
											}																		
										});
									}
									
								});						
							}
						}
					});
				}
				else{					
					userInfoTable.clear();
					personalFieldSet.setVisible(true);
					Label enrollLabel=new Label(constants.enrollmentNumber());					
					final TextBox enrollmentNo=new TextBox();					
					Button okButton1=new Button(constants.okButton());
					userInfoTable.setWidget(0, 0, enrollLabel);
					userInfoTable.setWidget(0, 1, enrollmentNo);
					userInfoTable.setWidget(2, 0, okButton1);
					okButton1.addListener(new ButtonListenerAdapter(){
						@Override
						public void onClick(Button button,EventObject e){	
							final String enrollment=enrollmentNo.getText();																		
							if(enrollment==null || enrollment.trim().length()==0){							
								MessageBox.alert(msgs.selectEnroll());							
							}							
							else{								
								final EDEIStudentBean bean=new EDEIStudentBean();
								bean.setEnrollmentNumber(enrollment);				
								bodyDesPanel.clear();
								RootPanel.get().clear();
								UserAccountEDEI ul=new UserAccountEDEI(uId,userName,uniNickName,enrollment);
								ul.onModuleLoad();
								RootPanel.get().add(ul.mainPanel);																	
							}
						}
					});
				}
			}
		});				
		return oldPanel;		
	}
}



