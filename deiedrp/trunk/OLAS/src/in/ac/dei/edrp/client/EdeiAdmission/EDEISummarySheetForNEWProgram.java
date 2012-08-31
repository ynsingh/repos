/*
 * @(#) EDEISummarySheetForNEWProgram.java
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
import in.ac.dei.edrp.client.Shared.CommonValidation;
import in.ac.dei.edrp.client.Shared.DocumentUpload;
import in.ac.dei.edrp.client.Shared.OA_ComboBoxesEDEI;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.applicantAccountEDEI.UserAccountEDEI;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
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
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

/**
 * @version 1.0 
 * date AUGUST 22 2012
 * @author 	DEVENDRA SINGHAL
 */

public class EDEISummarySheetForNEWProgram {
    /**
   * Create a remote service proxy to talk to the server-side EDEISummarySheet service.
   */
  EDeiSummaryServiceAsync summaryServiceAsync = GWT.create(EDeiSummaryService.class);
  
  public FormPanel formPanel = new FormPanel();
  
  messages msgs = GWT.create(messages.class);
  constants constants = GWT.create(constants.class);
  
  private String universityId;
  private String userEmail;
  private String uniNickName;
  private String sessionStartDate;
  private String sessionEndDate;
  String docFolder = "EDEIApplicantDocuments";
  String userId;
  String pgId;
  String enrollment;

  public EDEISummarySheetForNEWProgram(String uniNickName, String userEmail,
			String uniSessionStartDate, String uniSessionEndDate,
			String universityId, String pgId, String usrId,String enrollment) {
	  this.uniNickName=uniNickName;
	  this.universityId=universityId;
	  this.userEmail=userEmail;
	  this.sessionStartDate=uniSessionStartDate;
	  this.sessionEndDate=uniSessionEndDate;
	  this.pgId=pgId;
	  this.userId=usrId;
	  this.enrollment=enrollment;
  }

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    init();
    
  }
  
  public FormPanel init(){	    
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
      
      final FieldSet detailsFS = new FieldSet(constants.academicDetails());  
      detailsFS.setCollapsible(true);  
      detailsFS.setAutoHeight(true);
      
      final FieldSet courseModuleFS = new FieldSet(constants.coursesModules());  
      courseModuleFS.setCollapsible(true);  
      courseModuleFS.setAutoHeight(true);
      
      final FieldSet documentFS = new FieldSet(constants.uploadDoc());  
      documentFS.setCollapsible(true);  
      documentFS.setAutoHeight(true);
      
      OA_ComboBoxesEDEI comboData = new OA_ComboBoxesEDEI();
      comboData.onModuleLoad();
      
      //create widgets for first fieldset
      
      final FlexTable flexTablePersonal = new FlexTable();
      flexTablePersonal.setCellPadding(10);
      
      final FlexTable academicTable = new FlexTable();
      
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
      inputBean.setEnrollmentNumber(enrollment);
      
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
              minority.setWidth(188);              
              maritalStatus.setWidth(188);              
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
      
      /* --------------academic------------*/
      final List<AcademicDetailsNewBean> academicList=new ArrayList<AcademicDetailsNewBean>();
      AcademicDetailsNewBean a=new AcademicDetailsNewBean();
      
      academicTable.setWidget(0, 0, new HTML("<b>" + constants.component() + "</b>"));
      academicTable.setWidget(0, 1, new HTML("<b>" + constants.marksGrade()+"</b>"));
      academicTable.setWidget(0, 2, new HTML("<b>" + constants.obtainedMarksGrade()+"</b>"));
      academicTable.setWidget(0, 4, new HTML("<b>" + constants.totalMrksGrd()+"</b>"));
      academicTable.setWidget(0, 5, new HTML("<b>" + constants.degreeName()+"</b>"));      
      
      final NumberField marksHS = new NumberField();
      final NumberField totalHS = new NumberField();
      ComboBox marksGrdHS=comboData.markGradeCB;
      
      marksGrdHS.setAllowBlank(false);
      /*loadCombo(marksGrdHS, markGradeStore,"valueCode", "value","select");*/
      
      marksHS.setAllowBlank(false);
      marksHS.setValidateOnBlur(true);
      marksHS.setMinValue(1);
      
      totalHS.setAllowBlank(false);
      totalHS.setValidateOnBlur(true);
      
      academicTable.setWidget(1, 0, new Label(constants.highSchool()));
      academicTable.setWidget(1, 1, marksGrdHS);
      academicTable.setWidget(1, 2, marksHS);
      academicTable.setWidget(1, 3,new Label(constants.labelOutof()));
      academicTable.setWidget(1, 4, totalHS);
      
      marksGrdHS.addListener(new ComboBoxListenerAdapter() {
          public void onSelect(ComboBox comboBox,Record record, int index) {
          	
              if (comboBox.getValue().equalsIgnoreCase("GR")) {
            	  marksHS.setMaxValue(10);
            	  totalHS.setMaxValue(10);
            	  totalHS.setMinValue(4);
              }
              else {
            	  marksHS.setMaxValue(Integer.MAX_VALUE);
            	  totalHS.setMaxValue(Integer.MAX_VALUE);
              }
          }
      });
      
      a.setComponentId("HS");
	  a.setComponentDescription(constants.highSchool());
	  a.setMarksObtained(marksHS);
	  a.setTotalMarks(totalHS);
	  a.setMarkOrGrade(marksGrdHS);
	  academicList.add(a);
	  
	  final NumberField marksIN = new NumberField();
      final NumberField totalIN = new NumberField();
      OA_ComboBoxesEDEI comboIN=new OA_ComboBoxesEDEI();
      comboIN.onModuleLoad();
      ComboBox marksGrdIN=comboIN.markGradeCB;
      
      marksGrdIN.setAllowBlank(false);
      //loadCombo(marksGrdIN, markGradeStore,"valueCode", "value","select");
      marksIN.setAllowBlank(false);
      marksIN.setValidateOnBlur(true);
      marksIN.setMinValue(1);
      marksIN.setMinValue(1);
      totalIN.setAllowBlank(false);
      totalIN.setValidateOnBlur(true);
      
      academicTable.setWidget(2, 0, new Label(constants.intermediate()));
      academicTable.setWidget(2, 1, marksGrdIN);
      academicTable.setWidget(2, 2, marksIN);
      academicTable.setWidget(2, 3,new Label(constants.labelOutof()));
      academicTable.setWidget(2, 4, totalIN);
      
      marksGrdIN.addListener(new ComboBoxListenerAdapter() {
          public void onSelect(ComboBox comboBox,Record record, int index) {
          	
              if (comboBox.getValue().equalsIgnoreCase("GR")) {
            	  marksIN.setMaxValue(10);
            	  totalIN.setMaxValue(10);
            	  totalIN.setMinValue(4);
              }
              else {
            	  marksIN.setMaxValue(Integer.MAX_VALUE);
            	  totalIN.setMaxValue(Integer.MAX_VALUE);
              }
          }
      });
      
      a=new AcademicDetailsNewBean();
      a.setComponentId("IN");
      a.setComponentDescription(constants.intermediate());
      a.setMarksObtained(marksIN);
      a.setTotalMarks(totalIN);
      a.setMarkOrGrade(marksGrdIN);
      academicList.add(a);
      
      final NumberField marksUG = new NumberField();
      final NumberField totalUG = new NumberField();
      final TextField degreeUG=new TextField();
      ComboBox progNameUG = comboData.progNameUGCB;
      OA_ComboBoxesEDEI comboUG=new OA_ComboBoxesEDEI();
      comboUG.onModuleLoad();
      ComboBox marksGrdUG=comboUG.markGradeCB;
      
      marksUG.setValidateOnBlur(true);
      marksUG.setMinValue(1);
      marksUG.setMinValue(1);
      totalUG.setValidateOnBlur(true);
      progNameUG.setAllowBlank(true);
      
      academicTable.setWidget(3, 0, new Label(constants.underGraduate()));
      academicTable.setWidget(3, 1, marksGrdUG);
      academicTable.setWidget(3, 2, marksUG);
      academicTable.setWidget(3, 3,new Label(constants.labelOutof()));
      academicTable.setWidget(3, 4, totalUG);
      academicTable.setWidget(3, 5, progNameUG);
      
      marksGrdUG.addListener(new ComboBoxListenerAdapter() {
          public void onSelect(ComboBox comboBox,Record record, int index) {
          	
              if (comboBox.getValue().equalsIgnoreCase("GR")) {
            	  marksUG.setMaxValue(10);
            	  totalUG.setMaxValue(10);
            	  totalUG.setMinValue(4);
              }
              else {
            	  marksUG.setMaxValue(Integer.MAX_VALUE);
            	  totalUG.setMaxValue(Integer.MAX_VALUE);
              }
          }
      });
      
      progNameUG.addListener(new ComboBoxListenerAdapter() {
          public void onSelect(ComboBox comboBox,Record record, int index) {
          	
              if (comboBox.getValue().equalsIgnoreCase(constants.other())) {
                  degreeUG.enable();
                  degreeUG.setAllowBlank(false);
                  degreeUG.setWidth(188);
                  academicTable.setWidget(3, 6,degreeUG);
                  academicTable.setWidget(0, 6, new HTML("<b>" + constants.degreeName()+"</b>"));
              } else {
            	  degreeUG.setAllowBlank(true);
            	  degreeUG.setValue("");
            	  degreeUG.disable();
                  academicTable.setWidget(3, 6,new Label(""));
                  academicTable.setWidget(0, 6, new Label(" "));
              }
          }
      });
      /*degreeUG.disable();*/
      degreeUG.setMaxLength(100);
      
      a=new AcademicDetailsNewBean();
      a.setComponentId("UG");
      a.setComponentDescription(constants.underGraduate());
      a.setMarksObtained(marksUG);
      a.setTotalMarks(totalUG);
      a.setDegreeDescription(degreeUG);
      a.setDegreeValue(progNameUG);
      a.setMarkOrGrade(marksGrdUG);
      academicList.add(a);
      
      final NumberField marksPG = new NumberField();
      final NumberField totalPG = new NumberField();
      final TextField degreePG=new TextField();
      ComboBox progNamePG=comboData.progNamePGCB;
      /*loadCombo(progNamePG, progStorePG,"value", "value","select");*/
      OA_ComboBoxesEDEI comboPG=new OA_ComboBoxesEDEI();
      comboPG.onModuleLoad();
      ComboBox marksGrdPG=comboPG.markGradeCB;
      marksPG.setValidateOnBlur(true);
      marksPG.setMinValue(1);
      marksPG.setMinValue(1);
      totalPG.setValidateOnBlur(true);
      
      academicTable.setWidget(4, 0, new Label(constants.postGraduate()));
      academicTable.setWidget(4, 1, marksGrdPG);
      academicTable.setWidget(4, 2, marksPG);
      academicTable.setWidget(4, 3,new Label(constants.labelOutof()));
      academicTable.setWidget(4, 4, totalPG);
      academicTable.setWidget(4, 5, progNamePG);
      
      marksGrdPG.addListener(new ComboBoxListenerAdapter() {
          public void onSelect(ComboBox comboBox,Record record, int index) {
          	
              if (comboBox.getValue().equalsIgnoreCase("GR")) {
            	  marksPG.setMaxValue(10);
            	  totalPG.setMaxValue(10);
            	  totalPG.setMinValue(4);
              }
              else {
            	  marksPG.setMaxValue(Integer.MAX_VALUE);
            	  totalPG.setMaxValue(Integer.MAX_VALUE);
              }
          }
      });
      
      progNamePG.addListener(new ComboBoxListenerAdapter() {
          public void onSelect(ComboBox comboBox,Record record, int index) {
          	
              if (comboBox.getValue().equalsIgnoreCase(constants.other())) {
                  degreePG.enable();
                  degreePG.setAllowBlank(false);
                  degreePG.setWidth(188);
                  academicTable.setWidget(0, 6, new HTML("<b>" + constants.degreeName()+"</b>"));
                  academicTable.setWidget(4, 6,degreePG);
              } else {
            	  degreePG.setAllowBlank(true);
            	  degreePG.setValue("");
            	  degreePG.disable();
            	  academicTable.setWidget(0, 6, new Label(" "));
                  academicTable.setWidget(4, 6,new Label(""));
              }
          }
      });
      /*degreePG.disable();*/
      degreePG.setMaxLength(100);
      
      a=new AcademicDetailsNewBean();
      a.setComponentId("PG");
      a.setComponentDescription(constants.postGraduate());
      a.setMarksObtained(marksPG);
      a.setTotalMarks(totalPG);
      a.setDegreeDescription(degreePG);
      a.setDegreeValue(progNamePG);
      a.setMarkOrGrade(marksGrdPG);
      academicList.add(a);
      
      final NumberField marksOther = new NumberField();
      final NumberField totalOther = new NumberField();
      final TextField degreeOther=new TextField();
      OA_ComboBoxesEDEI comboOT=new OA_ComboBoxesEDEI();
      comboOT.onModuleLoad();
      ComboBox marksGrdOther=comboOT.markGradeCB;
      
      ComboBox progNameOT=comboData.progNameOTCB;
      
      marksOther.setValidateOnBlur(true);
      marksOther.setMinValue(1);
      marksOther.setMinValue(1);
      totalOther.setValidateOnBlur(true);
      progNameOT.setAllowBlank(true);
      
      academicTable.setWidget(5, 0, new Label(constants.other()));
      academicTable.setWidget(5, 1, marksGrdOther);
      academicTable.setWidget(5, 2, marksOther);
      academicTable.setWidget(5, 3,new Label(constants.labelOutof()));
      academicTable.setWidget(5, 4, totalOther);
      academicTable.setWidget(5, 5, progNameOT);
      
      marksGrdOther.addListener(new ComboBoxListenerAdapter() {
          public void onSelect(ComboBox comboBox,Record record, int index) {
          	
              if (comboBox.getValue().equalsIgnoreCase("GR")) {
            	  marksOther.setMaxValue(10);
            	  totalOther.setMaxValue(10);
            	  totalOther.setMinValue(4);
              }
              else {
            	  marksOther.setMaxValue(Integer.MAX_VALUE);
            	  totalOther.setMaxValue(Integer.MAX_VALUE);
              }
          }
      });
      progNameOT.addListener(new ComboBoxListenerAdapter() {
          public void onSelect(ComboBox comboBox,Record record, int index) {
          	
              if (comboBox.getValue().equalsIgnoreCase(constants.other())) {
                  degreeOther.enable();
                  degreeOther.setAllowBlank(false);
                  degreeOther.setWidth(188);
                  academicTable.setWidget(0, 6, new HTML("<b>" + constants.degreeName()+"</b>"));
                  academicTable.setWidget(5, 6,degreeOther);
                  
              } else {
            	  degreeOther.setAllowBlank(true);
            	  degreeOther.setValue("");
            	  degreeOther.disable();
            	  academicTable.setWidget(0, 6, new Label(""));
                  academicTable.setWidget(5, 6,new Label(""));
              }
          }
      });
      /*degreeOther.disable();*/
      degreeOther.setMaxLength(100);
      
      a=new AcademicDetailsNewBean();
      a.setComponentId("OT");
      a.setComponentDescription(constants.other());
      a.setMarksObtained(marksOther);
      a.setTotalMarks(totalOther);
      a.setDegreeDescription(degreeOther);
      a.setDegreeValue(progNameOT);
      a.setMarkOrGrade(marksGrdOther);
      academicList.add(a);
      
      /*--------------academic end------------*/
      
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
      final Module newModule=new Module(universityId,sessionStartDate,sessionEndDate,userEmail,pgId);
      
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
      final List<DocumentUpload> uploadDocList = new ArrayList<DocumentUpload>();
      DocumentUpload docProofHS =new DocumentUpload(constants.highSchool(),"200");
      DocumentUpload docProofIN =new DocumentUpload(constants.intermediate(),"200");
      DocumentUpload docProofUG =new DocumentUpload(constants.underGraduate(),"200");
      DocumentUpload docProofPG =new DocumentUpload(constants.postGraduate(),"200");
      DocumentUpload docProofOther =new DocumentUpload(constants.other(),"200");
      
      docProofHS.setDocId("HS");
      uploadDocList.add(docProofHS);
      
      docProofIN.setDocId("IN");
      uploadDocList.add(docProofIN);
      
      docProofUG.setDocId("UG");
      uploadDocList.add(docProofUG);
      
      docProofPG.setDocId("PG");
      uploadDocList.add(docProofPG);
      
      docProofOther.setDocId("OT");
      uploadDocList.add(docProofOther);
      
      documentFS.add(docProofHS);
      documentFS.add(docProofIN);
      documentFS.add(docProofUG);
      documentFS.add(docProofPG);
      documentFS.add(docProofOther);
      /*------------cousre Module end---------*/
      
      Button submit=new Button(constants.submit());
	    submit.addListener(new ButtonListenerAdapter(){
	    	public void onClick(Button button, EventObject e) {
	    		final List<EDEIStudentBean> academicDetailList =new ArrayList<EDEIStudentBean>();
	    		
	    		final List<EDEIStudentBean> attachmentList =new ArrayList<EDEIStudentBean>();	    		
                	if(validateAcademics(academicList,academicDetailList)){
                        if(validateModule()){
                        	if(validateUpload(uploadDocList,academicDetailList)){
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
                        					finalData.setCategoryCode(category.getValueAsString());
                        					finalData.setCategory(category.getRawValue());
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
                        					finalData.setProgramId(pgId);
                        					finalData.setEnrollmentNumber(enrollment);
                        					finalData.setStudentStatus("OLDNEW");
                        					if(courseRadio.getValue()){
                        						finalData.setCourseModuleList(newModule.courseModule);
                        						finalData.setEntityId(newModule.courseModule.get(0).getEntityId());
                        						
                        					}
                        					else {
                        						finalData.setCourseModuleList(newModule.selectedModuleDetails);
                        						finalData.setEntityId(newModule.selectedModuleDetails.get(0).getEntityId());
                        					}
                                                
                        					for (int i = 0;i < uploadDocList.size();i++) {
                        						if (uploadDocList.get(i).hasFile() &&(uploadDocList.get(i).hasPdfFile() ||uploadDocList.get(i).hasJpgFile())) {
                        							EDEIStudentBean attachment =new EDEIStudentBean();
                        							attachment.setDocId(uploadDocList.get(i).getDocId());
                        							attachment.setDocName(uploadDocList.get(i).getFileName());
                        							attachment.setComponentDescription(uploadDocList.get(i).getDocLabel());
                        							attachment.setDocPath(docFolder);
                        							attachmentList.add(attachment);
                                                 }
                                            }					                                                                        										                                             
                                                
                                            finalData.setAcademicDataList(academicDetailList);
                                            finalData.setAttachmentList(attachmentList);
                                            
                                            summaryServiceAsync.insertEDEINEWSummarySheetDetailsForNEWProgram(finalData,new AsyncCallback<EDEIStudentBean>() {
                                                public void onFailure(Throwable arg0) {
                                                	MessageBox.alert(""+arg0);
												}
                                                public void onSuccess(final EDEIStudentBean outBean) {
                                                	try {
                                                		for (int i =0;i < uploadDocList.size();i++) {
                                                			if (uploadDocList.get(i).hasFile() &&(uploadDocList.get(i).hasPdfFile() ||uploadDocList.get(i).hasJpgFile())) {
                                                				uploadDocList.get(i).uploadFile(docFolder+outBean.getFileSeparator()+outBean.getStudentId()+outBean.getFileSeparator()+outBean.getRegistrationNumber());
                                                            }
                                                		}					                                                                                               
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
                                                            			finalOutBean.getRegistrationNumber(),new AlertCallback() {
                                                            		
                                                            		 public void execute() {
                                                            			 RootPanel.get().clear();
                                                            			 UserAccountEDEI usrAccount=new UserAccountEDEI(userId,userEmail,uniNickName);
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
                        	}
                        }else MessageBox.alert(msgs.pleaseSelectCourseModule());
                	}
	                                                                                
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
				UserAccountEDEI usrAccount=new UserAccountEDEI(userId,userEmail,uniNickName);
				usrAccount.onModuleLoad();
				RootPanel.get().add(usrAccount.mainPanel);
	    	}
	    });
	    
	    fldstPersonalInformation.add(flexTablePersonal);
	    addressInformationFS.add(permanentAddress);
	    detailsFS.add(academicTable);
	    courseModuleFS.add(fieldSet);
	    courseModuleFS.add(main);
	    
	    //Add FieldSet in FormPanel
	    formPanel.add(fldstPersonalInformation);
	    formPanel.add(addressInformationFS);
	    formPanel.add(detailsFS);
	    formPanel.add(courseModuleFS);
	    formPanel.add(documentFS);
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
  
  private Boolean validateAcademics(List<AcademicDetailsNewBean> academicList, List<EDEIStudentBean> academicDetailList) {
	  
	  try{
	  
	  for(int i=0;i<academicList.size();i++){
    	  
		  EDEIStudentBean  edei=new EDEIStudentBean();
		  if((academicList.get(i).getComponentId().equalsIgnoreCase("HS"))||(academicList.get(i).getComponentId().equalsIgnoreCase("IN"))){
			  	
			  	if(!CommonValidation.validate(academicList.get(i).getMarkOrGrade())){
			  		MessageBox.alert(msgs.selectMarksGrade());
			  		return false;
			  	}
			  
			  	if (!CommonValidation.validate(academicList.get(i).getMarksObtained())) {
			  		if(academicList.get(i).getMarkOrGrade().getValue().equalsIgnoreCase("GR")){
			  			MessageBox.alert(msgs.enterGrade()+" "+academicList.get(i).getComponentDescription());
			  		}
			  		else MessageBox.alert(msgs.enterMarks()+" "+academicList.get(i).getComponentDescription());
		            return false;
			  	}
		
			    if (!CommonValidation.validate(academicList.get(i).getTotalMarks())) {
			    	if(academicList.get(i).getMarkOrGrade().getValue().equalsIgnoreCase("GR")){
			  			MessageBox.alert(msgs.totalGrade()+" "+academicList.get(i).getComponentDescription());
			  		}
			  		else MessageBox.alert(msgs.totalMarks()+" "+academicList.get(i).getComponentDescription());
			    	
		            return false;
		        }
		
		        if (academicList.get(i).getTotalMarks().getValue().longValue() < academicList.get(i).getMarksObtained().getValue().longValue()) {
		        	academicList.get(i).getTotalMarks().focus(true);
		        	if(academicList.get(i).getMarkOrGrade().getValue().equalsIgnoreCase("GR")){
		        		MessageBox.alert(msgs.errorGrade()+" "+academicList.get(i).getComponentDescription());
			  		}
			  		else MessageBox.alert(msgs.errorMarks()+" "+academicList.get(i).getComponentDescription());
		        	
		        	return false;
		        }
		         edei.setComponentId(academicList.get(i).getComponentId());
		   	  	 edei.setComponentDescription(academicList.get(i).getComponentDescription());
		   	  	 edei.setTotalMarks(academicList.get(i).getTotalMarks().getValueAsString());
		   	  	 edei.setMarksObtained(academicList.get(i).getMarksObtained().getValueAsString());
		   	  	 double marksPercent2=Double.parseDouble(academicList.get(i).getMarksObtained().getValueAsString())/Double.parseDouble(academicList.get(i).getTotalMarks().getValueAsString())*100;
		   	  	 edei.setPercentage(marksPercent2+"");
		   	  	 edei.setMarkOrGrade(academicList.get(i).getMarkOrGrade().getValueAsString());
		   	  	 academicDetailList.add(edei);
		  }
		  else{
			  if((academicList.get(i).getMarksObtained().getValueAsString().length()>0)||(academicList.get(i).getDegreeValue().getValueAsString().trim().length()>0)||
					  (academicList.get(i).getTotalMarks().getValueAsString().length()>0)||(academicList.get(i).getMarkOrGrade().getValueAsString().length()>0)){
				  academicList.get(i).getMarksObtained().setAllowBlank(false);
				  academicList.get(i).getTotalMarks().setAllowBlank(false);
				  academicList.get(i).getDegreeValue().setAllowBlank(false);
				  academicList.get(i).getMarkOrGrade().setAllowBlank(false);
				  
				  if(academicList.get(i).getDegreeValue().getValueAsString().equalsIgnoreCase(constants.other())){
					  academicList.get(i).getDegreeDescription().setAllowBlank(false);
				  }else academicList.get(i).getDegreeDescription().setAllowBlank(true);
				  
				  if(!CommonValidation.validate(academicList.get(i).getMarkOrGrade())){
					  MessageBox.alert(msgs.selectMarksGrade());
					  return false;
				  }
				  
				  if (!CommonValidation.validate(academicList.get(i).getMarksObtained())) {
					  if(academicList.get(i).getMarkOrGrade().getValue().equalsIgnoreCase("GR")){
				  			MessageBox.alert(msgs.enterGrade()+" "+academicList.get(i).getComponentDescription());
				  		}
				  		else MessageBox.alert(msgs.enterMarks()+" "+academicList.get(i).getComponentDescription());
			            return false;
			      }
			
				  if (!CommonValidation.validate(academicList.get(i).getTotalMarks())) {
					  if(academicList.get(i).getMarkOrGrade().getValue().equalsIgnoreCase("GR")){
				  			MessageBox.alert(msgs.totalGrade()+" "+academicList.get(i).getComponentDescription());
				  	  }
				  	  else MessageBox.alert(msgs.totalMarks()+" "+academicList.get(i).getComponentDescription());
					  return false;
			      }
				  if(!CommonValidation.validate(academicList.get(i).getDegreeValue())){
					  MessageBox.alert(msgs.enterDegree()+" "+academicList.get(i).getComponentDescription());
					  return false;
				  }
				  
				  if (!CommonValidation.validate(academicList.get(i).getDegreeDescription())) {
					  MessageBox.alert(msgs.selectDegree()+" "+academicList.get(i).getComponentDescription());
					  return false;
			      }
				  
				  if (academicList.get(i).getTotalMarks().getValue().longValue() < academicList.get(i).getMarksObtained().getValue().longValue()) {
					  academicList.get(i).getTotalMarks().focus(true);
					  if(academicList.get(i).getMarkOrGrade().getValue().equalsIgnoreCase("GR")){
			        		MessageBox.alert(msgs.errorGrade()+" "+academicList.get(i).getComponentDescription());
				  	  }
				  	  else MessageBox.alert(msgs.errorMarks()+" "+academicList.get(i).getComponentDescription());
					  return false;
			      }
				  
				  if(academicList.get(i).getDegreeValue().getValueAsString().equalsIgnoreCase(constants.other())){
					  if((academicList.get(i).getDegreeDescription()!=null)||(academicList.get(i).getDegreeDescription().getValueAsString().length()>0))
				   	  {
						  edei.setDegreeDescription(academicList.get(i).getDegreeDescription().getValueAsString());
				   	  }
					  else return false;
				  }
				  else{
					  edei.setDegreeDescription(academicList.get(i).getDegreeValue().getValueAsString());
				  }
				  
				  edei.setComponentId(academicList.get(i).getComponentId());
			   	  edei.setComponentDescription(academicList.get(i).getComponentDescription());
			   	  edei.setTotalMarks(academicList.get(i).getTotalMarks().getValueAsString());
			   	  edei.setMarksObtained(academicList.get(i).getMarksObtained().getValueAsString());
			   	  double marksPercent2=Double.parseDouble(academicList.get(i).getMarksObtained().getValueAsString())/Double.parseDouble(academicList.get(i).getTotalMarks().getValueAsString())*100;
			   	  edei.setPercentage(marksPercent2+"");
			   	  edei.setMarkOrGrade(academicList.get(i).getMarkOrGrade().getValueAsString());
			   	  academicDetailList.add(edei);
			   	
			  }else{
				  academicList.get(i).getMarksObtained().setAllowBlank(true);
				  academicList.get(i).getMarksObtained().isValid(true);
				  academicList.get(i).getTotalMarks().setAllowBlank(true);
				  academicList.get(i).getTotalMarks().isValid(true);
				  academicList.get(i).getDegreeValue().setAllowBlank(true);
				  academicList.get(i).getDegreeValue().isValid(true);
				  academicList.get(i).getMarkOrGrade().setAllowBlank(true);
				  academicList.get(i).getMarkOrGrade().isValid(true);
			  }
		  }
		  
      }
	  
    }catch (Exception e) {
		System.out.println(""+e);
	}
	  
	return true;
  }
  
  private Boolean validateUpload(List<DocumentUpload> uploadDocList, List<EDEIStudentBean> academicDetailList) {
      boolean flag=false;
      try{
      	for (int i = 0; i < uploadDocList.size(); i++) {
	    	for(int j=0;j<academicDetailList.size();j++){
	    		if(uploadDocList.get(i).getDocId().equalsIgnoreCase(academicDetailList.get(j).getComponentId())){
	        		if (uploadDocList.get(i).hasFile() &&(uploadDocList.get(i).hasPdfFile() ||uploadDocList.get(i).hasJpgFile()||uploadDocList.get(i).hasDocFile()||uploadDocList.get(i).hasDocxFile())) {
		           		flag=true;
	        		}
	        		else {
	        			MessageBox.alert(msgs.uploadDoc()+" "+academicDetailList.get(j).getComponentDescription());
	        			return false;
	        		}
	        	}
	        }   	
	    }
      }
      catch(Exception ex){
      	System.out.println("validateUpload"+ex.getStackTrace()+" "+ex);
      }
      return flag;
  }
  
}


