/*
 * @(#) ApplicantChangePassword.java
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

import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.summarysheet.CommonValidation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextField;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class ApplicantChangePassword {
	
	ApplicantAccountAsync connectService = (ApplicantAccountAsync) GWT.create(ApplicantAccount.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	public VerticalPanel mainVPanel=new VerticalPanel();
	String userName;
	String userId;
	public ApplicantChangePassword(){
		
	}
	public ApplicantChangePassword(String userName, String usrId) {
		this.userName=userName;
		this.userId=usrId;
	}
	public void onModuleLoad() {
    	init();
	}
	VerticalPanel init(){
		
		FieldSet changePasswordFieldSet=new FieldSet(constants.changePass());
		
		Label oldpasswordLabel=new Label(constants.enterOldPass());
		final TextField oldPass=new TextField();
		oldPass.setPassword(true);
		oldPass.setAllowBlank(false);
		oldPass.setValidateOnBlur(true);
		
		Label newPassworldLabel=new Label(constants.enterNewPass());
		final TextField newPass=new TextField();
		newPass.setPassword(true);
		newPass.setAllowBlank(false);
		newPass.setValidateOnBlur(true);
		
		Label retypePassLabel=new Label(constants.retypePass());
		final TextField retypePass=new TextField();
		retypePass.setAllowBlank(false);
		retypePass.setPassword(true);
		retypePass.setValidateOnBlur(true);
		
		Button submit=new Button(constants.submit());
		Button cancelButton=new Button(constants.cancelButton());
		
		FlexTable flexTabel=new FlexTable();
		flexTabel.setWidget(0, 0, oldpasswordLabel);
		flexTabel.setWidget(0, 1, oldPass);
		flexTabel.setWidget(1, 0, newPassworldLabel);
		flexTabel.setWidget(1, 1, newPass);
		flexTabel.setWidget(2, 0, retypePassLabel);
		flexTabel.setWidget(2, 1, retypePass);
		flexTabel.setWidget(3, 0, submit);
		flexTabel.setWidget(3, 1, cancelButton);
		
		changePasswordFieldSet.add(flexTabel);
		
		cancelButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				UserAccount user=new UserAccount(userName,userId);
				RootPanel.get().clear();
				mainVPanel.clear();
				user.onModuleLoad();
				RootPanel.get().add(user.mainPanel);
			}
		});
		
		submit.addListener(new ButtonListenerAdapter() {
			
			public void onClick(Button button, EventObject e) {
				
				if(CommonValidation.validate(oldPass)){
					if(CommonValidation.validate(newPass)){
						if(CommonValidation.validate(retypePass)){
							if(!(newPass.getValueAsString().equals(oldPass.getValueAsString()))){
								if(newPass.getValueAsString().equals(retypePass.getValueAsString())){
									
									connectService.changeApplicantPassword(userName,userId,oldPass.getValueAsString(),newPass.getValueAsString(),new AsyncCallback<Integer>() {
						
											public void onFailure(Throwable arg0) {
												
											}
						
											public void onSuccess(Integer count) {
												if(count>0){
													MessageBox.alert(msgs.passChanged());
													oldPass.reset();
													newPass.reset();
													retypePass.reset();
												}
												else MessageBox.alert(msgs.oldPassIncorrect());
											}
									});
								}
								else {
									MessageBox.alert(msgs.passNotMatched());
									newPass.reset();
									retypePass.reset();
								}
							}
							else MessageBox.alert(msgs.passAreSame());
						}
					}
				}
			}
		});
		
		mainVPanel.add(changePasswordFieldSet);
		
		return mainVPanel;
	}
}
