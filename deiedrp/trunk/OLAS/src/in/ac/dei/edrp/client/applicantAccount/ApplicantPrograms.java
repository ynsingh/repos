/*
 * @(#) ApplicantProgram.java
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

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.Label;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class ApplicantPrograms {

	ApplicantAccountAsync connectService = (ApplicantAccountAsync) GWT.create(ApplicantAccount.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	public VerticalPanel mainVPanel=new VerticalPanel();
	String userEmailId;
	int size;
	public ApplicantPrograms(){
		
	}
	public ApplicantPrograms(String userId){
		this.userEmailId=userId;
	}
	
	public void onModuleLoad() {
    	init();
	}
	VerticalPanel init(){
		
		final FieldSet programFieldSet=new FieldSet(constants.progRegistered());
		final FlexTable flexTabel=new FlexTable();
		connectService.getAppliedProgramList(userEmailId,new AsyncCallback<List<ApplicantAccountBean>>() {

			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.getMessage());
				
			}
			public void onSuccess(List<ApplicantAccountBean> prog) {
				size=prog.size();
				if(prog.size()>0){
					for(int i=0;i<prog.size();i++){
						
						String programName=prog.get(i).getProgramName();
						//System.out.println(programName+" ");
						flexTabel.setWidget(i, 0,new Label( programName+ ""));
					}
					
				}
				else {
					MessageBox.alert(constants.noProgApplied());
					Label noProg=new Label(constants.noProgApplied());
					flexTabel.setWidget(0, 0,noProg);
				}
			}
		});
		
		programFieldSet.add(flexTabel);
		mainVPanel.add(programFieldSet); 
		return mainVPanel;
	}
}
