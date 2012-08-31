/*
 * @(#) UserAccount.java
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

import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.Shared.OA_ComboBoxes;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.summarysheet.CommonValidation;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

/**
 * @version 1.0 29 MAY 2012
 * @author UPASANA KULSHRESTHA
 */
public class ManageApplicantAccount {
	ApplicantAccountAsync connectService = (ApplicantAccountAsync) GWT.create(ApplicantAccount.class);
	CM_connectTempAsync categoryServiceAsync = GWT.create(CM_connectTemp.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	public VerticalPanel mainVPanel=new VerticalPanel();
	int counter;
	Image captchaImage;
	Panel mainPanel=new Panel();
	private String userEmailId;
	private String userId;
	public ManageApplicantAccount(String userName,String userId){
		this.userEmailId=userName;
		this.userId=userId;
	}
	
	public void onModuleLoad() {
    	init();
	}
	VerticalPanel init(){
		final OA_ComboBoxes comboData = new OA_ComboBoxes();
		comboData.onModuleLoad();
		
		final FieldSet personalInfo = new FieldSet();
		personalInfo.setTitle(constants.personal_details());
		
		final FlexTable flexTable = new FlexTable();
		flexTable.setSize("378px", "248px");
		
		final FlexTable confirmTable = new FlexTable();
		confirmTable.setSize("340px", "100px");
		
		Label emailIdLabel = new Label(constants.emailAddress()+constants.asterisk());
		flexTable.setWidget(0, 0, emailIdLabel);
		
		final TextField primaryMail = new TextField();
		primaryMail.setEmptyText(constants.enterEmail());
		primaryMail.setAllowBlank(false);
		primaryMail.setVtype(VType.EMAIL);
		primaryMail.setValidateOnBlur(true);
		flexTable.setWidget(0, 1, primaryMail);
		
		Label firstNameLbl = new Label(constants.firstName());
		flexTable.setWidget(1, 0, firstNameLbl);
		
		final TextField firstName = new TextField();
		firstName.setEmptyText(constants.enterFirstName());
		firstName.setAllowBlank(false);
		firstName.setValidateOnBlur(true);
		flexTable.setWidget(1, 1, firstName);
		
		Label middleNameLbl=new Label(constants.middleName());
		flexTable.setWidget(2, 0, middleNameLbl);
		
		final TextField middleName = new TextField();
		middleName.setEmptyText(constants.enterMiddleName());
		flexTable.setWidget(2, 1, middleName);
		
		Label lastNameLbl = new Label(constants.lastName());
		flexTable.setWidget(3, 0, lastNameLbl);
		
		final TextField lastName = new TextField();
		lastName.setEmptyText(constants.enterLastName());
		flexTable.setWidget(3, 1, lastName);
		
		
		Label fatherNameLbl = new Label(constants.fatherName()+constants.asterisk());
		flexTable.setWidget(4, 0, fatherNameLbl);
		
		final TextField fatherFirstName = new TextField();
		fatherFirstName.setEmptyText(constants.enterFatherName());
		fatherFirstName.setAllowBlank(false);
		fatherFirstName.setValidateOnBlur(true);
		flexTable.setWidget(4, 1, fatherFirstName);
		
		Label motherNameLbl = new Label(constants.motherName()+constants.asterisk());
		flexTable.setWidget(5, 0, motherNameLbl);
		
		final TextField motherFirstName = new TextField();
		motherFirstName.setEmptyText(constants.enterMotherName());
		motherFirstName.setAllowBlank(false);
		motherFirstName.setValidateOnBlur(true);
		flexTable.setWidget(5, 1, motherFirstName);
		
		Label dobLbl = new Label(constants.birthDay()+constants.asterisk());
		flexTable.setWidget(6, 0, dobLbl);
		
		final DateField dateBox = new DateField();
		dateBox.setEmptyText(constants.selectDob());
		dateBox.setReadOnly(true);
		dateBox.setAllowBlank(false);
		dateBox.setValidateOnBlur(true);
		dateBox.setWidth(188);
		//dateBox.setMaxValue(new Date());
		flexTable.setWidget(6, 1, dateBox);
		
		
		Label lblCategory = new Label(constants.category());
		flexTable.setWidget(7, 0, lblCategory);
		
		final ComboBox category = new ComboBox();
		category.setAllowBlank(false);
        category.setForceSelection(true);
        category.setValidateOnBlur(true);
        category.setWidth(188);
        
        categoryServiceAsync.Category(userId,new AsyncCallback<CM_StudentInfoGetter[]>() {
            public void onFailure(Throwable t) {
                MessageBox.alert(t.getMessage());
            }

            public void onSuccess(CM_StudentInfoGetter[] categoryList) {
                Object[][] categoryObj = new Object[categoryList.length][2];

                for (int c = 0; c < categoryList.length; c++) {
                    categoryObj[c][0] = categoryList[c].getCategory_code();
                    categoryObj[c][1] = categoryList[c].getCategory();
                }

                final Store categoryStore = new SimpleStore(new String[] {
                            "catCode", "category"
                        }, categoryObj);

                loadCombo(category, categoryStore, "catCode", "category",constants.category());
                category.setWidth(188);
                flexTable.setWidget(7, 1, category);
            }
        });           
        		
		Label lblGender = new Label(constants.gender());
		flexTable.setWidget(8, 0, lblGender);
		
		final ComboBox gender = comboData.genderCB;
		gender.setAllowBlank(false);
        gender.setForceSelection(true);
        gender.setValidateOnBlur(true);
        gender.setWidth(188);
		flexTable.setWidget(8, 1, gender);
		
		Label nationalityLbl = new Label(constants.nationality());
		flexTable.setWidget(9, 0, nationalityLbl);
		
		final TextField nationality = new TextField();
		nationality.setEmptyText(constants.enterNationality());
		nationality.setAllowBlank(false);
		nationality.setValidateOnBlur(true);
		flexTable.setWidget(9, 1, nationality);
		
		Label ReligionLbl = new Label(constants.religion());
		flexTable.setWidget(10, 0, ReligionLbl);
		
		final TextField religion = new TextField();
		religion.setEmptyText(constants.enterReligion());
		religion.setAllowBlank(false);
		religion.setValidateOnBlur(true);
		flexTable.setWidget(10, 1, religion);
		
		Label phoneLbl = new Label(constants.labelPhone()+constants.asterisk());
		flexTable.setWidget(11, 0, phoneLbl);
		
		final NumberField homePhone = new NumberField();
		homePhone.setEmptyText(constants.enterPhoneNo());
		homePhone.setMinLength(10);
		homePhone.setMaxLength(15);
		homePhone.setAllowBlank(false);
		homePhone.setDecimalPrecision(0);
		homePhone.setValidateOnBlur(true);
		flexTable.setWidget(11, 1, homePhone);
		
		Label lblLocation = new Label(constants.location()+constants.asterisk());
		flexTable.setWidget(12, 0, lblLocation);
		
		final ComboBox comboBoxState = comboData.statesCB;
		comboBoxState.setAllowBlank(false);
		comboBoxState.setEditable(false);
		comboBoxState.setForceSelection(true);
		comboBoxState.setValidateOnBlur(true);
		comboBoxState.setWidth(188);
		flexTable.setWidget(12, 1, comboBoxState);
		
		final ComboBox comboBoxCity = comboData.cityCB;
		comboBoxState.addListener(new ComboBoxListenerAdapter() {			
			@Override
			public void onSelect(ComboBox comboBox, Record record, int index) {					
				comboBoxCity.clearValue();
				comboData.onStateChange(comboBoxState.getRawValue());
								
			}
			
		});
		
		comboBoxCity.setAllowBlank(false);
		comboBoxCity.setValidateOnBlur(true);
		comboBoxCity.setEditable(false);
		comboBoxCity.setForceSelection(true);
		comboBoxCity.setWidth(188);
		
		Label lblCity = new Label(constants.city());
		flexTable.setWidget(13, 0, lblCity);
		
		flexTable.setWidget(13, 1, comboBoxCity);
		
		personalInfo.add(flexTable);
		
		Button cancelButton=new Button(constants.cancelButton());
		Button update=new Button(constants.updateButton());
		
		HorizontalPanel horizontalPanel=new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		horizontalPanel.add(update);
		horizontalPanel.setCellHorizontalAlignment(update, HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel.add(cancelButton);
		horizontalPanel.setCellHorizontalAlignment(cancelButton, HasHorizontalAlignment.ALIGN_CENTER);
		
		connectService.getAccountDetails(userEmailId,new AsyncCallback<List<ApplicantAccountBean>>() {

			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.toString());
			}

			public void onSuccess(List<ApplicantAccountBean> details) {
				if(details.size()>0){
					primaryMail.setValue(userEmailId);
					primaryMail.setDisabled(true);
					firstName.setValue(details.get(0).getFirstName());
					middleName.setValue(details.get(0).getMiddleName());
					lastName.setValue(details.get(0).getLastName());
					fatherFirstName.setValue(details.get(0).getFatherName());
					motherFirstName.setValue(details.get(0).getMotherName());
					dateBox.setValue(details.get(0).getDob());
					category.setValue(details.get(0).getCategory());
					if(details.get(0).getGender().equals("F")){
						gender.setValue(constants.female());
					}
					else gender.setValue(constants.male());
					nationality.setValue(details.get(0).getNationality());
					religion.setValue(details.get(0).getReligion());
					homePhone.setValue(details.get(0).getPhone());
					comboBoxState.setValue(details.get(0).getLocation());
					comboBoxCity.setValue(details.get(0).getCity());
				}
			}
		});
		
		update.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				if(CommonValidation.validate(firstName)){
					if(CommonValidation.validate(fatherFirstName)){
						if(CommonValidation.validate(motherFirstName)){
							if(CommonValidation.validate(dateBox)){
								if(CommonValidation.validate(category)){
									if(CommonValidation.validate(gender)){
										if(CommonValidation.validate(nationality)){
											if(CommonValidation.validate(religion)){
												if(CommonValidation.validate(homePhone)){
													if(CommonValidation.validate(comboBoxState)){
														if(CommonValidation.validate(comboBoxCity)){
							
															final ApplicantAccountBean accountInfo=new ApplicantAccountBean();
															
															accountInfo.setUserId(userId);
															accountInfo.setFirstName(firstName.getValueAsString().trim());
															accountInfo.setMiddleName(middleName.getValueAsString().trim());
															accountInfo.setLastName(lastName.getValueAsString().trim());
															accountInfo.setEmail(primaryMail.getValueAsString());
															accountInfo.setFatherName(fatherFirstName.getValueAsString().trim());
															accountInfo.setMotherName(motherFirstName.getValueAsString().trim());
															accountInfo.setDob(dateBox.getValueAsString());
															accountInfo.setCategory(category.getValueAsString());
															accountInfo.setGender(gender.getValueAsString().substring(0, 1));
															accountInfo.setPhone(homePhone.getValueAsString());
															accountInfo.setLocation(comboBoxState.getValueAsString());
															accountInfo.setNationality(nationality.getValueAsString().trim());
															accountInfo.setReligion(religion.getValueAsString().trim());
															accountInfo.setCity(comboBoxCity.getValueAsString());
															
															mainPanel.getBody().mask();
															connectService.updateAccountdetails(accountInfo, new AsyncCallback<String>() {
																public void onFailure(Throwable arg0) {
																	MessageBox.alert(arg0.toString());
																}
																public void onSuccess(String arg0) {
																	mainPanel.getBody().unmask();
																	MessageBox.alert(msgs.success(),msgs.detailsUpdated(),new AlertCallback() {
                                                                        public void execute() {
                                                                        	mainVPanel.clear();
																			RootPanel.get().clear();
																			UserAccount ua=new UserAccount(userEmailId,userId);
																			ua.onModuleLoad();
																			RootPanel.get().add(ua.mainPanel);
                                                                        }
                                                                    });
																}
															});
															
														}else MessageBox.alert(msgs.enterCity());
													}else MessageBox.alert(msgs.selectState());
												}else MessageBox.alert(msgs.chkPhone());
											}else MessageBox.alert(msgs.enterReligion());
										}else MessageBox.alert(msgs.selectNationality());
									}else MessageBox.alert(msgs.selectGender());
								}else MessageBox.alert(msgs.selectCategory());
							}else MessageBox.alert(msgs.checkDOB());
						}else MessageBox.alert(msgs.enterMotherName());
					}else MessageBox.alert(msgs.enterFatherName());
				}else MessageBox.alert(msgs.enterName());
			}
		});
		
		cancelButton.addListener(new ButtonListenerAdapter() {
			
			@Override
			public void onClick(Button button, EventObject e) {
				
				UserAccount user=new UserAccount(userEmailId,userId);
				RootPanel.get().clear();
				mainVPanel.clear();
				user.onModuleLoad();
				RootPanel.get().add(user.mainPanel);
				
			
			}
		});
		
		mainPanel.setTitle(constants.manageAccount());
		mainPanel.add(personalInfo);
		mainPanel.add(horizontalPanel);
		
		mainVPanel.add(mainPanel);
		
 
		return mainVPanel;
	}
	
	private void loadCombo(ComboBox combo, Store store, String valueField,
	        String displayField, String emptyText) {
	        store.load();
	        combo.setReadOnly(true);
	        combo.setAllowBlank(false);
	        combo.setValidateOnBlur(true);
	        combo.setWidth(250);
	        combo.setForceSelection(true);
	        combo.setStore(store);
	        combo.setDisplayField(displayField);
	        combo.setValueField(valueField);
	        combo.setMode(ComboBox.LOCAL);
	        combo.setTriggerAction(ComboBox.ALL);
	        combo.setEmptyText(emptyText);
	        combo.setLoadingText("Loading...");
	        combo.setTypeAhead(true);
	        combo.setSelectOnFocus(true);
	    }
}
