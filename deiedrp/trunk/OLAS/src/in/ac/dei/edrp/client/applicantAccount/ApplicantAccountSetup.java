/*
 * @(#) ApplicantAccountSetup.java
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
import in.ac.dei.edrp.client.applicantAccountEDEI.UserLoginEDEI;
import in.ac.dei.edrp.client.summarysheet.CommonValidation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
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
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.form.ValidationException;
import com.gwtext.client.widgets.form.Validator;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;


/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

/*public class ApplicantAccountSetup implements EntryPoint{*/
public class ApplicantAccountSetup{

	ApplicantAccountAsync connectService = (ApplicantAccountAsync) GWT.create(ApplicantAccount.class);
	CM_connectTempAsync categoryServiceAsync = GWT.create(CM_connectTemp.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	
	int counter;
	Image captchaImage;
	public VerticalPanel verticalPanel = new VerticalPanel();
	Panel mainPanel=new Panel();
	
	private String userId;
	private int flagValue;
	
	public ApplicantAccountSetup(String uId,int flag){
		System.out.println(uId);
		this.userId = uId;
		this.flagValue=flag;
	}
	
	
	public void applicantAccountSetup(){
		verticalPanel.clear();
		counter=(int) (Math.random()*10000);
		onModuleLoad();
	}
	
	public void onModuleLoad() {
		
		verticalPanel.clear();
		verticalPanel.setSize("381px", "361px");
		
		final OA_ComboBoxes comboData = new OA_ComboBoxes();
		comboData.onModuleLoad();
		
		final FieldSet personalInfo = new FieldSet();
		personalInfo.setTitle(constants.personal_details());
		
		final FieldSet captchaInfo = new FieldSet();
		captchaInfo.setTitle(constants.confirmation());
		
		final FlexTable flexTable = new FlexTable();
		flexTable.setSize("378px", "248px");
		
		final FlexTable confirmTable = new FlexTable();
		confirmTable.setSize("340px", "100px");
		
		Label firstNameLbl = new Label(constants.firstName());
		firstNameLbl.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setWidget(0, 0, firstNameLbl);
		
		final TextField firstName = new TextField();
		firstName.setEmptyText(constants.enterFirstName());
		firstName.setAllowBlank(false);
		firstName.setValidateOnBlur(true);
		flexTable.setWidget(0, 1, firstName);
		
		Label middleNameLbl=new Label(constants.middleName());
		flexTable.setWidget(1, 0, middleNameLbl);
		
		final TextField middleName = new TextField();
		middleName.setEmptyText(constants.enterMiddleName());
		flexTable.setWidget(1, 1, middleName);
		
		Label lastNameLbl = new Label(constants.lastName());
		flexTable.setWidget(2, 0, lastNameLbl);
		
		final TextField lastName = new TextField();
		lastName.setEmptyText(constants.enterLastName());
		flexTable.setWidget(2, 1, lastName);
		
		Label emailIdLabel = new Label(constants.emailAddress()+constants.asterisk());
		flexTable.setWidget(3, 0, emailIdLabel);
		
		final TextField primaryMail = new TextField();
		primaryMail.setEmptyText(constants.enterEmail());
		primaryMail.setAllowBlank(false);
		primaryMail.setVtype(VType.EMAIL);
		primaryMail.setValidateOnBlur(true);
		flexTable.setWidget(3, 1, primaryMail);
		
		Label passwordLabel=new Label(constants.password()+constants.asterisk());
		flexTable.setWidget(4, 0, passwordLabel);
		
		final TextField password = new TextField();
		password.setAllowBlank(false);
		password.setPassword(true);
		password.setValidateOnBlur(true);
		password.setMinLength(4);
		password.setMaxLength(20);
		flexTable.setWidget(4, 1, password);
		
		Label confirmPasswordLabel=new Label(constants.confirmPassword()+constants.asterisk());
		flexTable.setWidget(5, 0, confirmPasswordLabel);
		
		final TextField passwordConfirm = new TextField();
		passwordConfirm.setAllowBlank(false);
		passwordConfirm.setPassword(true);
		passwordConfirm.setValidateOnBlur(true);
		passwordConfirm.setValidator(new Validator() {
			public boolean validate(String value) throws ValidationException {
				return password.getValueAsString().equals(value);
			}
		});
		flexTable.setWidget(5, 1, passwordConfirm);
		
		Label fatherNameLbl = new Label(constants.fatherName()+constants.asterisk());
		flexTable.setWidget(6, 0, fatherNameLbl);
		
		final TextField fatherFirstName = new TextField();
		fatherFirstName.setEmptyText(constants.enterFatherName());
		fatherFirstName.setAllowBlank(false);
		fatherFirstName.setValidateOnBlur(true);
		flexTable.setWidget(6, 1, fatherFirstName);
		
		Label motherNameLbl = new Label(constants.motherName()+constants.asterisk());
		flexTable.setWidget(7, 0, motherNameLbl);
		
		final TextField motherFirstName = new TextField();
		motherFirstName.setEmptyText(constants.enterMotherName());
		motherFirstName.setAllowBlank(false);
		motherFirstName.setValidateOnBlur(true);
		flexTable.setWidget(7, 1, motherFirstName);
		
		Label dobLbl = new Label(constants.birthDay()+constants.asterisk());
		flexTable.setWidget(8, 0, dobLbl);
		
		final DateField dateBox = new DateField();
		dateBox.setEmptyText(constants.selectDob());
		dateBox.setReadOnly(true);
		dateBox.setAllowBlank(false);
		dateBox.setValidateOnBlur(true);
		dateBox.setWidth(188);
		//dateBox.setMaxValue(new Date());
		flexTable.setWidget(8, 1, dateBox);
		
		
		Label lblCategory = new Label(constants.category());
		flexTable.setWidget(9, 0, lblCategory);
		
		final ComboBox category = new ComboBox();
		category.setAllowBlank(false);
        category.setForceSelection(true);
        category.setValidateOnBlur(true);
        category.setWidth(188);
        
        categoryServiceAsync.Category(userId,
                new AsyncCallback<CM_StudentInfoGetter[]>() {
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
                        flexTable.setWidget(9, 1, category);
                    }
        });           
        
		
				
		Label lblGender = new Label(constants.gender());
		flexTable.setWidget(10, 0, lblGender);
		
		final ComboBox gender = comboData.genderCB;
		gender.setAllowBlank(false);
        gender.setForceSelection(true);
        gender.setValidateOnBlur(true);
        gender.setWidth(188);
		flexTable.setWidget(10, 1, gender);
		
		Label nationalityLbl = new Label(constants.nationality());
		flexTable.setWidget(11, 0, nationalityLbl);
		
		final TextField nationality = new TextField();
		nationality.setEmptyText(constants.enterNationality());
		nationality.setAllowBlank(false);
		nationality.setValidateOnBlur(true);
		flexTable.setWidget(11, 1, nationality);
		
		Label ReligionLbl = new Label(constants.religion());
		flexTable.setWidget(12, 0, ReligionLbl);
		
		final TextField religion = new TextField();
		religion.setEmptyText(constants.enterReligion());
		religion.setAllowBlank(false);
		religion.setValidateOnBlur(true);
		flexTable.setWidget(12, 1, religion);
		
		Label phoneLbl = new Label(constants.labelPhone()+constants.asterisk());
		flexTable.setWidget(13, 0, phoneLbl);
		
		final NumberField homePhone = new NumberField();
		homePhone.setEmptyText(constants.enterPhoneNo());
		homePhone.setMinLength(10);
		homePhone.setMaxLength(15);
		homePhone.setAllowBlank(false);
		homePhone.setDecimalPrecision(0);
		homePhone.setValidateOnBlur(true);
		flexTable.setWidget(13, 1, homePhone);
		
		Label lblLocation = new Label(constants.location()+constants.asterisk());
		flexTable.setWidget(14, 0, lblLocation);
		
		final ComboBox comboBoxState = comboData.statesCB;
		comboBoxState.setAllowBlank(false);
		comboBoxState.setEditable(false);
		comboBoxState.setForceSelection(true);
		comboBoxState.setValidateOnBlur(true);
		comboBoxState.setWidth(188);
		
		flexTable.setWidget(14, 1, comboBoxState);
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
		flexTable.setWidget(15, 0, lblCity);
		
		flexTable.setWidget(15, 1, comboBoxCity);
		
		personalInfo.add(flexTable);
		
		
		final TextField captchaField = new TextField();
		captchaField.setWidth("200");
		captchaField.setEmptyText(constants.enterText());
		counter++;		
		captchaImage = new Image("/SimpleCaptcha.jpg?counter="+counter);
		
		Button reloadButton=new Button();
		reloadButton.setIcon("images/refreshIcon.png");
		//reloadButton.setIcon("images/refresh1.png");
		
		ToolTip toolTip = new ToolTip(constants.tooltipCaptcha());  
        toolTip.applyTo(reloadButton.getElement());  
		
		Button cancelButton=new Button(constants.cancelButton());
		
		Label typeText=new Label(constants.typeText());
		
		Button submit=new Button(constants.submit());
		HorizontalPanel horizontalPanel=new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		horizontalPanel.add(submit);
		horizontalPanel.setCellHorizontalAlignment(submit, HasHorizontalAlignment.ALIGN_CENTER);
		
		horizontalPanel.add(cancelButton);
		horizontalPanel.setCellHorizontalAlignment(reloadButton, HasHorizontalAlignment.ALIGN_CENTER);
		
		confirmTable.setCellSpacing(5);
		confirmTable.setWidget(0, 0, captchaImage);
		confirmTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		confirmTable.setWidget(1, 0, typeText);
		confirmTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		confirmTable.setWidget(2, 0, captchaField);
		confirmTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		confirmTable.setWidget(2, 1, reloadButton);
		confirmTable.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
		confirmTable.setWidget(3, 0, horizontalPanel);
		confirmTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
				
		captchaInfo.add(confirmTable);
		
		submit.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				
				if(CommonValidation.validate(firstName)){
					if(CommonValidation.validate(primaryMail)){
						if(CommonValidation.validate(password)){
						if(password.getValueAsString().equals(passwordConfirm.getValueAsString())){
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
															String captcha = captchaField.getText();
															
															connectService.performSignup( captcha, new AsyncCallback<Boolean>() {		
																public void onSuccess(Boolean result) {
																	
																	if (result) {
																		
																		final ApplicantAccountBean accountInfo=new ApplicantAccountBean();
																		//accountInfo.setUniversityId(userId.substring(1,5));
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
																		accountInfo.setPassword(password.getValueAsString());
																		accountInfo.setNationality(nationality.getValueAsString().trim());
																		accountInfo.setReligion(religion.getValueAsString().trim());
																		accountInfo.setCity(comboBoxCity.getValueAsString());
																		
																		connectService.checkEmailDuplicacy(accountInfo,new AsyncCallback<Integer>() {
																			public void onFailure(Throwable arg0) {
																				MessageBox.alert(arg0.toString());
																			}
																			public void onSuccess(Integer flag) {
																				if(flag==0){
																					mainPanel.getBody().mask();
																					connectService.insertAccountdetails(accountInfo, new AsyncCallback<String>() {
																						public void onFailure(Throwable arg0) {
																							MessageBox.alert(arg0.toString());
																						}
																						public void onSuccess(String arg0) {
																							mainPanel.getBody().unmask();
																							
																							connectService.sendEmailAccount(accountInfo,new AsyncCallback<String>() {
																								public void onFailure(Throwable arg0) {
																									System.out.println("failure"+arg0.getMessage());
																								}
																								public void onSuccess(String arg0) {
																									System.out.println("Success");
																								}
																							});
																							
																							MessageBox.alert(msgs.success(),msgs.accountCreated(),new AlertCallback() {
                                                                                                public void execute() {
                                                                                                	verticalPanel.clear();
        																							RootPanel.get().clear();
        																							
        																							if(flagValue==1){
        																								MainLoginPage ua=new MainLoginPage(userId);
        																								ua.onModuleLoad();
            																							RootPanel.get().add(ua.mainPanel);
        																							}
        																							else if(flagValue==2){
        																								UserLogin ua=new UserLogin(userId);
        																								ua.onModuleLoad();
            																							RootPanel.get().add(ua.mainPanel);
        																							}
        																							else if(flagValue==3){
        																								UserLoginEDEI ua=new UserLoginEDEI(userId);
        																								ua.onModuleLoad();
            																							RootPanel.get().add(ua.mainPanel);
        																							}
        																							
                                                                                                }
                                                                                            });
																						}
																					});
																				}
																				else {
																					MessageBox.alert(msgs.emailInUse());
																					captchaField.reset();
																					reload(confirmTable);
																				}
																			}
																		});
																	}
																	else {
																		MessageBox.alert(constants.retypeCaptcha());
																		captchaField.reset();
																		reload(confirmTable);
																	}			
																}		
																public void onFailure(Throwable caught) {
																	MessageBox.alert(msgs.errorServer());
																}		
															});
														}else {
															MessageBox.alert(msgs.enterCity());
															captchaField.reset();
															reload(confirmTable);
														}
														
													}else {
														MessageBox.alert(msgs.selectState());
														captchaField.reset();
														reload(confirmTable);
													}		
												}else {
													MessageBox.alert(msgs.chkPhone());
													captchaField.reset();
													reload(confirmTable);
												}
											}else {
												MessageBox.alert(msgs.enterReligion());
												captchaField.reset();
												reload(confirmTable);
											}

									  }else {
											MessageBox.alert(msgs.selectNationality());
											captchaField.reset();
											reload(confirmTable);
										}	
									}else {
										MessageBox.alert(msgs.selectGender());
										captchaField.reset();
										reload(confirmTable);
									}
								  }else {
										MessageBox.alert(msgs.selectCategory());
										captchaField.reset();
										reload(confirmTable);
									}	
								}else {
									MessageBox.alert(msgs.checkDOB());
									captchaField.reset();
									reload(confirmTable);
								}
							}else{
								MessageBox.alert(msgs.enterMotherName());
								captchaField.reset();
								reload(confirmTable);
								}	
								
							}else {
								MessageBox.alert(msgs.enterFatherName());
								captchaField.reset();
								reload(confirmTable);
							}
						}else {
							MessageBox.alert(msgs.passNotMatched());
							captchaField.reset();
							reload(confirmTable);
						}
					  }else {
						  MessageBox.alert(msgs.checkPass());
						  captchaField.reset();
							reload(confirmTable);
					 }
					}else {
						MessageBox.alert(msgs.checkEmail());
						captchaField.reset();
						reload(confirmTable);
					}
				}else {
					MessageBox.alert(msgs.enterName());
					captchaField.reset();
					reload(confirmTable);
				}
			}
		});
		
		cancelButton.addListener(new ButtonListenerAdapter() {
			
			@Override
			public void onClick(Button button, EventObject e) {
				
				RootPanel.get().clear();
				verticalPanel.clear();
				
				if(flagValue==1){
					MainLoginPage ua=new MainLoginPage(userId);
					ua.onModuleLoad();
					RootPanel.get().add(ua.mainPanel);
				}
				else if(flagValue==2){
					UserLogin ua=new UserLogin(userId);
					ua.onModuleLoad();
					RootPanel.get().add(ua.mainPanel);
				}
				else if(flagValue==3){
					UserLoginEDEI ua=new UserLoginEDEI(userId);
					ua.onModuleLoad();
					RootPanel.get().add(ua.mainPanel);
				}
				
			}
		});
		reloadButton.addListener(new ButtonListenerAdapter(){

			public void onClick(Button button, EventObject e) {
				
				captchaField.reset();
				reload(confirmTable);
				
			}
			
		});
		mainPanel.setTitle("Account Setup");
		mainPanel.add(personalInfo);
		mainPanel.add(captchaInfo);
		verticalPanel.add(mainPanel);
		
		
		/*verticalPanel.add(personalInfo);
		verticalPanel.add(captchaInfo);*/
	}
	
	private void reload(FlexTable confirmTable) {
		counter++;
		confirmTable.remove(captchaImage);
		captchaImage = new Image("SimpleCaptcha.jpg?counter="+counter);
		confirmTable.setWidget(0, 0, captchaImage);
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
