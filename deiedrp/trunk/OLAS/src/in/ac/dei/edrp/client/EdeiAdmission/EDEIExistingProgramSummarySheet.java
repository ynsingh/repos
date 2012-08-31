/**
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
package in.ac.dei.edrp.client.EdeiAdmission;

import in.ac.dei.edrp.client.Shared.AddressField;
import in.ac.dei.edrp.client.Shared.OA_ComboBoxesEDEI;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.applicantAccount.UserProgramSelection;
import in.ac.dei.edrp.client.applicantAccountEDEI.AccountLoginEDEI;
import in.ac.dei.edrp.client.applicantAccountEDEI.AccountLoginEDEIAsync;
import in.ac.dei.edrp.client.applicantAccountEDEI.ApplicantAccountEDEIBean;
import in.ac.dei.edrp.client.applicantAccountEDEI.UserLoginEDEI;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.MessageBox.ConfirmCallback;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.Radio;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.form.event.CheckboxListenerAdapter;

/**
 * 
 * @version 1.0 9 
 * @date AUGUST 13 2012
 * @author DEVENDRA SINGHAL
 */
public class EDEIExistingProgramSummarySheet {	
	AccountLoginEDEIAsync connectService = (AccountLoginEDEIAsync) GWT.create(AccountLoginEDEI.class);
	EDeiSummaryServiceAsync summaryServiceAsync = GWT.create(EDeiSummaryService.class);
	
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	String userId;
	String userEmail;
	String uniNickName;
	String universityId;
	String sessionStartDate;
	String sessionEndDate;
	String enrollmentNumber;
	String rollNumber;
	String docFolder = "EDEIApplicantDocuments";
	String programId;
	String entityId;
	
	List<EDEIStudentBean>availableList;
	public VerticalPanel mainPanel;
	VerticalPanel headerPanel;
	Panel bodyMainPanel;
	VerticalPanel footerPanel;
	HorizontalPanel HypMainPanelTop;
	HorizontalPanel linkPanelTop;
	final Label headerLabel = new Label(constants.title());
	final Label headerLabel1 = new Label(constants.heading1());
	final Label headerLabel2 = new Label(constants.heading2());
	final Label footerLabel = new Label(constants.footer()+constants.footer1());
	public FormPanel formPanel = new FormPanel();
	Hyperlink signOut=new Hyperlink(constants.signOut(),"");
	Hyperlink home=new Hyperlink(constants.home(),"");
	Label welcomeLabel = new Label(constants.welcome()+" ");
	public EDEIExistingProgramSummarySheet(List<EDEIStudentBean>list,String enroll,String roll,String programId,String entityId,String uniNickName,String userName,String sessionStartDate,String sessionEndDate,String universityId,String uId){
		this.availableList=list;		
		this.enrollmentNumber=enroll;
		this.rollNumber=roll;
		this.programId=programId;
		this.entityId=entityId;
		this.uniNickName=uniNickName;	
		this.userEmail=userName;
		this.sessionStartDate=sessionStartDate;
		this.sessionEndDate=sessionEndDate;
		this.universityId=universityId;		
		this.userId=uId;
				
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
		mainPanel = new VerticalPanel();
		headerPanel = new VerticalPanel();
		bodyMainPanel=new Panel();
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
		headerLabel.setStyleName("MainHeading");
		headerLabel1.setStyleName("heading1");
		headerLabel2.setStyleName("heading1");
		footerLabel.setStyleName("footerLabel");		
		headerPanel.setSpacing(5);
		headerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		footerPanel.setSpacing(5);
		footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);				
		linkPanelTop.setSpacing(5);		
		
		HorizontalPanel signoutPanel=new HorizontalPanel();
		signoutPanel.add(signOut);

		HorizontalPanel homePanel=new HorizontalPanel();
		homePanel.add(home);
		
		HypMainPanelTop.add(homePanel);
		HypMainPanelTop.setCellHorizontalAlignment(homePanel,HasHorizontalAlignment.ALIGN_LEFT);
		HypMainPanelTop.setCellVerticalAlignment(homePanel,HasVerticalAlignment.ALIGN_MIDDLE);
		HypMainPanelTop.add(signoutPanel);
		HypMainPanelTop.setCellHorizontalAlignment(signoutPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		HypMainPanelTop.setSpacing(5);
		linkPanelTop.add(welcomeLabel);
		connectService.getUserNameForAccount(userEmail,new AsyncCallback<String>() {
			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.getMessage());
			}
			public void onSuccess(String name) {
				linkPanelTop.add(new Label(name));
			}
		});
		headerPanel.add(headerLabel);
		headerPanel.add(headerLabel1);
		headerPanel.add(headerLabel2);
		headerPanel.add(linkPanelTop);
		headerPanel.setCellHorizontalAlignment(linkPanelTop, HasHorizontalAlignment.ALIGN_LEFT);
		headerPanel.add(HypMainPanelTop);
		mainPanel.setCellVerticalAlignment(footerPanel, HasVerticalAlignment.ALIGN_BOTTOM);						
		footerPanel.add(footerLabel);	
		bodyMainPanel.setHeight("465");
		
		bodyMainPanel.add(generateSummarySheet());
		mainPanel.add(headerPanel);
		mainPanel.add(bodyMainPanel);
		mainPanel.add(footerPanel);			
		return mainPanel;
	}
	
	public FormPanel generateSummarySheet(){		
		formPanel.setTitle(constants.summarySheet());  
	      formPanel.setFrame(true);  
	      formPanel.setPaddings(5, 5, 5, 0);  
	      formPanel.setLabelWidth(75);  
	             
	      //Create FieldSet and set their Properties
	      
	      final FieldSet fldstPersonalInformation = new FieldSet();
	      fldstPersonalInformation.setTitle(constants.personal_details());
	      fldstPersonalInformation.setCollapsible(true);
	      
	      final FieldSet addressInformationFS = new FieldSet();
	      addressInformationFS.setTitle(constants.addressDetails());
	      addressInformationFS.setCollapsible(true);
	      
	      final FieldSet courseModuleFS = new FieldSet(constants.coursesModules());  
	      courseModuleFS.setCollapsible(true);  
	      courseModuleFS.setAutoHeight(true);	      
	      
	      OA_ComboBoxesEDEI comboData = new OA_ComboBoxesEDEI();
	      comboData.onModuleLoad();
	      
	      //create widgets for first fieldset
	      
	      final FlexTable flexTablePersonal = new FlexTable();
	      flexTablePersonal.setCellPadding(10);
	   
	      
	      final Label lblFirstName = new Label(constants.firstName());
	      final Label lblMiddleName = new Label(constants.middleName());
	      final Label lblLastName = new Label(constants.lastName());
	      final Label lblName = new Label(constants.name());
	      final Label lblFatherName = new Label(constants.fatherName());
	      final Label lblMotherName = new Label(constants.motherName());
	      final Label lblDateofBirth=new Label(constants.dateOfBirth());
	      final Label lblCategory=new Label(constants.category());
	      final Label lblNationality=new Label(constants.nationality());
	      final Label lblGender=new Label(constants.gender());
	      final Label lblReligion=new Label(constants.religion());
	      final Label lblGaurdian=new Label(constants.guardian());
	      final Label lblPrimaryEmail=new Label(constants.primaryMail());
	      final Label lblSecondaryEmail=new Label(constants.secondaryMail());
	      final Label lblMinority=new Label(constants.minority());
	      final Label lblPhone=new Label(constants.labelPhone());
	      final Label lblOther=new Label(constants.labelOtherPhone());
	      final Label lblMaritalStatus=new Label(constants.maritalStatus());
	      
	      final TextField firstName = new TextField();
	      final TextField middleName = new TextField();
	      final TextField lastName = new TextField();
	      final TextField fatherFirstName = new TextField();
	      final TextField fatherMiddleName = new TextField();
	      final TextField fatherLastName = new TextField();
	      final TextField motherFirstName = new TextField();
	      final TextField motherMiddleName = new TextField();
	      final TextField motherLastName = new TextField();
	      final DateField dob=new DateField();
	      final ComboBox category=new ComboBox();
	      final TextField nationality=new TextField();
	      final ComboBox gender=comboData.genderCB;
	      final TextField religion=new TextField();
	      final TextField gaurdian=new TextField();
	      final TextField primaryEmail=new TextField();
	      final TextField secondEmail=new TextField();
	      final ComboBox minority=comboData.phCB;
	      final NumberField phone=new NumberField();
	      final NumberField otherPhone=new NumberField();
	      final ComboBox maritalStatus=comboData.maritalCB;
	      final AddressField permanentAddress = new AddressField(constants.addressDetails());
	      
	      //set widget properties
	      firstName.setWidth(188);
	      firstName.setVtype(VType.ALPHA);	      
	      middleName.setWidth(188);	      
	      lastName.setWidth(188);	     
	      lastName.setVtype(VType.ALPHA);	      
	      fatherFirstName.setWidth(188);
	      fatherFirstName.setVtype(VType.ALPHA);	      
	      fatherMiddleName.setWidth(188);	      
	      fatherLastName.setWidth(188);
	      fatherLastName.setVtype(VType.ALPHA);	      
	      motherFirstName.setWidth(188);
	      motherFirstName.setVtype(VType.ALPHA);	      
	      motherMiddleName.setWidth(188);	      
	      motherLastName.setWidth(188);
	      motherLastName.setVtype(VType.ALPHA);	      
	      dob.setWidth(188);	      
	      category.setWidth(188);	      
	      nationality.setWidth(188);
	      nationality.setVtype(VType.ALPHA);	      
	      gender.setWidth(188);	      
	      religion.setWidth(188);
	      religion.setVtype(VType.ALPHA);	      
	      primaryEmail.setWidth(188);
	      primaryEmail.setVtype(VType.EMAIL);
	      secondEmail.setWidth(188);
	      secondEmail.setVtype(VType.EMAIL);	      
	      minority.setWidth(188);	      
	      phone.setMaxLength(15);
	      phone.setMinLength(10);
	      phone.setDecimalPrecision(0);	      
	      otherPhone.setWidth(188);
	      otherPhone.setMaxLength(15);
	      otherPhone.setMinLength(10);
	      otherPhone.setDecimalPrecision(0);	     
	      maritalStatus.setWidth(188);
	      
	      final EDEIStudentBean inputBean=new EDEIStudentBean();
	      inputBean.setUniversityId(universityId);
	      inputBean.setStudentId(userEmail);
	      inputBean.setUniversityNickName(uniNickName);
	      inputBean.setSessionStartDate(sessionStartDate);
	      inputBean.setSessionEndDate(sessionEndDate);
	      inputBean.setEnrollmentNumber(enrollmentNumber);
	      System.out.println(userEmail);
	      
	      summaryServiceAsync.getPersonalDetails(inputBean,new AsyncCallback<List<EDEIStudentBean>>() {
	    	  	public void onFailure(Throwable arg0) {
					MessageBox.alert(arg0.toString());
				}

				public void onSuccess(List<EDEIStudentBean> details) {
					if(details.size()>0){
						firstName.setValue(details.get(0).getFirstName());
						middleName.setValue(details.get(0).getMiddleName());
						lastName.setValue(details.get(0).getLastName());
						fatherFirstName.setValue(details.get(0).getFatherFirstName());
						fatherMiddleName.setValue(details.get(0).getFatherMiddleName());
						fatherLastName.setValue(details.get(0).getFatherLastName());
						motherFirstName.setValue(details.get(0).getMotherFirstName());
						motherMiddleName.setValue(details.get(0).getMotherMiddleName());
						motherLastName.setValue(details.get(0).getMotherLastName());
						dob.setValue(details.get(0).getDob());
						category.setValue(details.get(0).getCategory());
						if(details.get(0).getGender().equals("F")){
							gender.setValue("Female");
						}
						else{
							gender.setValue("Male");
						}
						
						religion.setValue(details.get(0).getReligion());
						nationality.setValue(details.get(0).getNationality());
						gaurdian.setValue(details.get(0).getGuardian());
						primaryEmail.setValue(details.get(0).getPrimaryEmail());
						secondEmail.setValue(details.get(0).getSecondaryEmail());
						if(details.get(0).getPhoneNumber()!=null || !(details.get(0).getPhoneNumber().equals(" "))){
							phone.setValue(details.get(0).getPhoneNumber());
						}
						if(details.get(0).getOtherPhone()!=null || !(details.get(0).getOtherPhone().equals(" "))){
							otherPhone.setValue(details.get(0).getOtherPhone());
						}												
						permanentAddress.line1.setValue(details.get(0).getAddressLine1());
						permanentAddress.city.setValue(details.get(0).getCity());
						permanentAddress.state.setValue(details.get(0).getState());
						permanentAddress.pincode.setValue(details.get(0).getPincode());
						
						
						firstName.setReadOnly(true);
						middleName.setReadOnly(true);
						lastName.setReadOnly(true);
						fatherFirstName.setReadOnly(true);
						fatherMiddleName.setReadOnly(true);
						fatherLastName.setReadOnly(true);
						motherFirstName.setReadOnly(true);
						motherMiddleName.setReadOnly(true);
						motherLastName.setReadOnly(true);
						dob.disable();
						category.disable();
						nationality.setReadOnly(true);
						gender.disable();
						religion.setReadOnly(true);
						gaurdian.setReadOnly(true);
						primaryEmail.setReadOnly(true);
						secondEmail.setReadOnly(true);
						minority.disable();
						phone.setReadOnly(true);
						otherPhone.setReadOnly(true);
						maritalStatus.disable();
						permanentAddress.line1.setReadOnly(true);
						permanentAddress.line2.setReadOnly(true);
						permanentAddress.city.disable();
						permanentAddress.state.disable();
						permanentAddress.pincode.setReadOnly(true);
					}
				}
		  });
	      
	      summaryServiceAsync.Category(universityId,new AsyncCallback<List<EDEIStudentBean>>() {
				public void onFailure(Throwable arg0) {
					MessageBox.alert(arg0.getMessage());
				}

				public void onSuccess(List<EDEIStudentBean> categoryDetails) {
	              Object[][] categoryObj = new Object[categoryDetails.size()][2];

	              for (int c = 0; c < categoryDetails.size(); c++) {
	                  categoryObj[c][0] = categoryDetails.get(c).getCategoryCode();
	                  categoryObj[c][1] = categoryDetails.get(c).getCategory();
	              }

	              final Store categoryStore = new SimpleStore(new String[] {
	                          "catCode", "category"
	                      }, categoryObj);

	              loadCombo(category, categoryStore, "catCode", "category",constants.category());
	              category.setWidth(188);
	              category.setAllowBlank(false);
	              minority.setWidth(188);
	              minority.setAllowBlank(true);	              
	              maritalStatus.setWidth(188);
	              maritalStatus.setAllowBlank(true);	              
	              gender.setWidth(188);	              
	              flexTablePersonal.setWidget(1, 1, lblFirstName);
	              flexTablePersonal.setWidget(1, 2, lblMiddleName);
	              flexTablePersonal.setWidget(1, 3, lblLastName);
	              flexTablePersonal.setWidget(2, 0, lblName);
	              flexTablePersonal.setWidget(3, 0, lblFatherName);
	              flexTablePersonal.setWidget(4, 0, lblMotherName);
	              flexTablePersonal.setWidget(5, 0, lblDateofBirth);
	              flexTablePersonal.setWidget(5, 2, lblCategory);
	              flexTablePersonal.setWidget(5, 4, lblNationality);
	              flexTablePersonal.setWidget(6, 0, lblGender);
	              flexTablePersonal.setWidget(6, 2, lblReligion);
	              flexTablePersonal.setWidget(6,4, lblGaurdian);
	              flexTablePersonal.setWidget(7, 0, lblPrimaryEmail);
	              flexTablePersonal.setWidget(7, 2, lblSecondaryEmail);
	              flexTablePersonal.setWidget(7,4, lblMinority);
	              flexTablePersonal.setWidget(8, 0, lblPhone);
	              flexTablePersonal.setWidget(8, 2, lblOther);
	              flexTablePersonal.setWidget(8,4, lblMaritalStatus);
	              
	              flexTablePersonal.setWidget(2, 1, firstName);
	              flexTablePersonal.setWidget(2, 2, middleName);
	              flexTablePersonal.setWidget(2, 3, lastName);
	              flexTablePersonal.setWidget(3, 1, fatherFirstName);
	              flexTablePersonal.setWidget(3, 2, fatherMiddleName);
	              flexTablePersonal.setWidget(3, 3, fatherLastName);
	              flexTablePersonal.setWidget(4, 1, motherFirstName);
	              flexTablePersonal.setWidget(4, 2, motherMiddleName);
	              flexTablePersonal.setWidget(4, 3, motherLastName);
	              flexTablePersonal.setWidget(5, 1, dob);
	              flexTablePersonal.setWidget(5, 3, category);
	              flexTablePersonal.setWidget(5, 5, nationality);
	              flexTablePersonal.setWidget(6, 1, gender);
	              flexTablePersonal.setWidget(6, 3, religion);
	              flexTablePersonal.setWidget(6, 5, gaurdian);
	              flexTablePersonal.setWidget(7, 1, primaryEmail);
	              flexTablePersonal.setWidget(7, 3, secondEmail);
	              flexTablePersonal.setWidget(7, 5, minority);
	              flexTablePersonal.setWidget(8, 1, phone);
	              flexTablePersonal.setWidget(8, 3, otherPhone);
	              flexTablePersonal.setWidget(8, 5, maritalStatus);
				}
			});	      	     	      	    
	      
	      /*--------------course Module-------*/
	      
	      FieldSet fieldSet = new FieldSet(constants.registerFor());  
	      fieldSet.setWidth(420);  
	      final Radio courseRadio = new Radio();  
	      courseRadio.setName("dropstyle");  
	      courseRadio.setBoxLabel(constants.course());
	      courseRadio.setChecked(true);
	      fieldSet.add(courseRadio);  

	      final Radio moduleRadio = new Radio();  
	      moduleRadio.setName("dropstyle");  
	      moduleRadio.setBoxLabel(constants.module());  
	      fieldSet.add(moduleRadio); 	      
	      final VerticalPanel main=new VerticalPanel();
	      main.setSpacing(5);	      
	      
	      final ModuleForExistingProgram newModule=new ModuleForExistingProgram(availableList,universityId,sessionStartDate,sessionEndDate,userEmail,programId);	      
	      courseRadio.addListener(new CheckboxListenerAdapter() {
				public void onCheck(Checkbox field, boolean checked) {
					if(main.isAttached()){
		  				main.clear();
		  			}
					if(courseRadio.getValue()){
						main.add(newModule.onModule("CRS"));
					}
					else main.add(newModule.onModule("MOD"));
				}
	      }); 
	      
	       
	      /*------------cousre Module end---------*/
	      
	      Button submit=new Button(constants.submit());
		    submit.addListener(new ButtonListenerAdapter(){
		    	public void onClick(Button button, EventObject e) {
		    		 if(validateModule()){
                 		MessageBox.confirm(msgs.confirm(), msgs.alert_confirmentries(), new ConfirmCallback(){
                 			public void execute(String btnID) {
                 				if (btnID.equalsIgnoreCase("yes")) {
                 					final EDEIStudentBean finalData =new EDEIStudentBean();
                 					
                 					finalData.setUniversityId(universityId);
                 					finalData.setUniversityNickName(uniNickName);
                 					finalData.setSessionStartDate(sessionStartDate);
                 					finalData.setSessionEndDate(sessionEndDate);
                 					finalData.setStudentId(userEmail);
                 					finalData.setUserId(userId); 
                 					
                 					finalData.setFirstName(firstName.getValueAsString());
                					finalData.setMiddleName(middleName.getValueAsString());
                					finalData.setLastName(lastName.getValueAsString());
                					finalData.setFatherFirstName(fatherFirstName.getValueAsString());
                					finalData.setFatherMiddleName(fatherMiddleName.getValueAsString());
                					finalData.setFatherLastName(fatherLastName.getValueAsString());
                					finalData.setMotherFirstName(motherFirstName.getValueAsString());
                					finalData.setMotherMiddleName(motherMiddleName.getValueAsString());
                					finalData.setMotherLastName(motherLastName.getValueAsString());
                					finalData.setDob(dob.getText());
                					finalData.setCategory(category.getRawValue());
                					finalData.setCategoryCode(category.getValueAsString());
                					finalData.setNationality(nationality.getValueAsString());
                					finalData.setGender(gender.getValueAsString().substring(0,1));
                					finalData.setReligion(religion.getValueAsString());
                					finalData.setGuardian(gaurdian.getValueAsString());
                					finalData.setPrimaryEmail(primaryEmail.getValueAsString());
                					finalData.setSecondaryEmail(secondEmail.getValueAsString());
                					finalData.setMinority(minority.getValueAsString());
                					finalData.setMaritalStatus(maritalStatus.getValueAsString());
                					finalData.setMinorityDesc(minority.getRawValue());
                					finalData.setMaritalStatusDesc(maritalStatus.getRawValue());
                					finalData.setPhoneNumber(phone.getValueAsString());
                					finalData.setOtherPhone(otherPhone.getValueAsString());
                					finalData.setAddressLine1(permanentAddress.line1.getValueAsString());
                					finalData.setAddressLine2(permanentAddress.line2.getValueAsString());
                					finalData.setCity(permanentAddress.city.getValueAsString());
                					finalData.setState(permanentAddress.state.getValueAsString());
                					finalData.setPincode(permanentAddress.pincode.getValueAsString());             					
                 					finalData.setProgramId(programId);
                 					finalData.setEnrollmentNumber(enrollmentNumber);
                 					finalData.setRollNumber(rollNumber);
                 					finalData.setStudentStatus("OLDEXP");
                 					if(courseRadio.getValue()){
                 						finalData.setCourseModuleList(newModule.courseModule);
                 						
                 					}
                 					else {
                 						finalData.setCourseModuleList(newModule.selectedModuleDetails);                 						
                 					}
                                         						       						                                                                        					
                                     summaryServiceAsync.insertEDEINEWSummarySheetDetailsForExistingProgram(finalData,new AsyncCallback<EDEIStudentBean>() {
                                         public void onFailure(Throwable arg0) {
                                         	MessageBox.alert(""+arg0);
											}
                                         public void onSuccess(final EDEIStudentBean outBean) {
                                         	try {
                                         		 
                                         		summaryServiceAsync.generatePDFForExistingProgram(outBean,docFolder,new AsyncCallback<EDEIStudentBean>() {
                                                      public void onFailure(Throwable t) {
                                                     	 MessageBox.alert(msgs.error(),t.getMessage());
                                                      }
                                                      public void onSuccess(EDEIStudentBean finalOutBean) {
                                                     	 //mainPanel.getBody().unmask();
                                                     	 Window.open(GWT.getHostPageBaseURL() +finalOutBean.getDocPath(),"","");
                                                     	 summaryServiceAsync.sendMailConfirmation(outBean,new AsyncCallback<EDEIStudentBean>() {
                                                     		 public void onFailure(Throwable arg0) {
                                                              }
                                                     		 public void onSuccess(EDEIStudentBean arg0) {
																			
                                                     		 }
                                                     	 });	
                                                                 
                                                     	MessageBox.alert(msgs.success(),msgs.alert_successfulentry() +" " +msgs.yourRegNo() +" " +
                                                 				outBean.getRegistrationNumber(),new AlertCallback() {                                              		
                                                      		 public void execute() {
                                                      			 RootPanel.get().clear();
                                                      			UserProgramSelection usrAccount=new UserProgramSelection(userId,userEmail,uniNickName);
                                                      			 usrAccount.onModuleLoad();
                                                      			 RootPanel.get().add(usrAccount.mainPanel);
                                                      		 }
                                                      	 });          
                                                      }
                                         		});
                                         	} catch (Exception e) {
                                         		MessageBox.alert(msgs.error(),e.getMessage());
                                         	}
                                         }
                                     });
                 				}
                 			}
                 		});				
                 }else MessageBox.alert(msgs.pleaseSelectCourseModule());
	            }

				private boolean validateModule() {					
					if(courseRadio.getValue()){
		    			if(!newModule.addButton.isDisabled()){
		    				return false;
			    		}
			    	}
		    		else{
		    			if(!newModule.addButtonModule.isDisabled()){
			    			return false;
			    		}
			    	}
					return true;
				}

		    });
		    
		    Button cancel=new Button(constants.cancelButton());
		    cancel.addListener(new ButtonListenerAdapter(){
		    	public void onClick(Button button, EventObject e) {
		        	RootPanel.get().clear();
					UserProgramSelection usrAccount=new UserProgramSelection(userId,userEmail,uniNickName);
					usrAccount.onModuleLoad();
					RootPanel.get().add(usrAccount.mainPanel);
		    	}
		    });
		    
		    signOut.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent arg0) {
					RootPanel.get().clear();
					mainPanel.clear();
					UserLoginEDEI login = new UserLoginEDEI(userId);
					login.onModuleLoad();
					RootPanel.get().add(login.mainPanel);
				}
			});

			home.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent arg0) {
					RootPanel.get().clear();
					UserProgramSelection usrAccount=new UserProgramSelection(userId,userEmail,uniNickName);
					usrAccount.onModuleLoad();
					RootPanel.get().add(usrAccount.mainPanel);
				}
			});
			
		    fldstPersonalInformation.add(flexTablePersonal);
		    addressInformationFS.add(permanentAddress);
		    courseModuleFS.add(fieldSet);
		    courseModuleFS.add(main);
		    
		    //Add FieldSet in FormPanel
		    formPanel.add(fldstPersonalInformation);
		    formPanel.add(addressInformationFS);
		    formPanel.add(courseModuleFS);
		    formPanel.addButton(submit);  
		    formPanel.addButton(cancel);  
		    		    
		return formPanel;
	}
	
	private void loadCombo(ComboBox combo, Store store, String valueField,
	        String displayField, String emptyText) {
	        store.load();
	        combo.setValidateOnBlur(true);
	        combo.setWidth(188);
	        combo.setForceSelection(true);
	        combo.setStore(store);
	        combo.setDisplayField(displayField);
	        combo.setValueField(valueField);
	        combo.setMode(ComboBox.LOCAL);
	        combo.setTriggerAction(ComboBox.ALL);
	        combo.setEmptyText(emptyText);
	        combo.setLoadingText(constants.loading());
	        combo.setTypeAhead(true);
	        combo.setSelectOnFocus(true);
  }
  	
}



