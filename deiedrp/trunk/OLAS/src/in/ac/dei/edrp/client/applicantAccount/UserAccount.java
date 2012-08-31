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

import in.ac.dei.edrp.client.ProgramSetup.ProgramSearchSetup;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Label;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class UserAccount {

	ApplicantAccountAsync connectService = (ApplicantAccountAsync) GWT.create(ApplicantAccount.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	public final VerticalPanel mainPanel = new VerticalPanel();
	public VerticalPanel BodyVerticalPanel = new VerticalPanel();
	public VerticalPanel sidePanel=new VerticalPanel();
    final VerticalPanel headerPanel = new VerticalPanel();
    final HorizontalPanel bodyPanel = new HorizontalPanel();
    final VerticalPanel footerPanel = new VerticalPanel();
    
    final HorizontalPanel HypMainPanelTop = new HorizontalPanel();
    final HorizontalPanel HypMainPanelBottom = new HorizontalPanel();
    final HorizontalPanel linkPanelBottom = new HorizontalPanel();
    final HorizontalPanel linkPanelTop = new HorizontalPanel();
    String userName;
    String usrId;
    Label welcomeLabel = new Label(constants.welcome()+" ");
    final Label headerLabel = new Label(constants.title());
    final Label headerLabel1 = new Label(constants.heading1());
    final Label headerLabel2 = new Label(constants.heading2()); 
    final Label footerLabel = new Label(constants.footer()+constants.footer1());
    
    final Hyperlink blank = new Hyperlink("", "");
    Hyperlink hyp=new Hyperlink(constants.applyProgram(),"");
    Hyperlink changePasswordHyp=new Hyperlink(constants.changePass(),"");
    Hyperlink appliedProgram=new Hyperlink(constants.registeredProg(),"");
    Hyperlink signOut=new Hyperlink(constants.signOut(),"");
    Hyperlink ManageAccount=new Hyperlink(constants.manageAccount(),"");
	
    public UserAccount() {
		// TODO Auto-generated constructor stub
	}
	
    public UserAccount(String user){
    	this.userName=user;
    }
    
    public UserAccount(String userName,String userId){
    	this.userName=userName;
    	this.usrId=userId;
    }
	
    public void onModuleLoad() {
    	init();
	}
    
    VerticalPanel init() {
        
		//Property Set***********************************
        
        BodyVerticalPanel.setHeight("450");
        BodyVerticalPanel.setWidth("90%");
        //BodyVerticalPanel.setSpacing(30);
        
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        headerPanel.setWidth("100%");
        bodyPanel.setWidth("100%");
        footerPanel.setWidth("100%");
        HypMainPanelTop.setWidth("100%");
        HypMainPanelBottom.setWidth("100%");
        
        sidePanel.setSize("150", "200");
        sidePanel.setStyleName("logOutPanel1");
        
        FlexTable sideTable=new FlexTable();
        //sideTable.setWidget(0, 0, hyp);
        sideTable.setWidget(1, 0, changePasswordHyp);
        sideTable.setWidget(2, 0, appliedProgram);
        sideTable.setWidget(3, 0, ManageAccount);
        sidePanel.add(sideTable);
        
        
        /*headerPanel.setStyleName("headerBlueBack");
        footerPanel.setStyleName("footerBlueBack");*/
        headerPanel.setStyleName("headerGrayBack");
        footerPanel.setStyleName("footerGrayBack");
        HypMainPanelTop.setStyleName("logOutPanel1");
        HypMainPanelBottom.setStyleName("logOutPanel1");
        
        headerLabel.setStyleName("MainHeading");
        headerLabel1.setStyleName("heading1");
        headerLabel2.setStyleName("heading1");
        footerLabel.setStyleName("footerLabel");
        
        headerPanel.setSpacing(5);
        headerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        footerPanel.setSpacing(5);
        footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        
                
        Label text=new Label(constants.accountLabel());
        Label text1=new Label(constants.accountLabel1());
        text.setStyleName("HeadingMessage");
        VerticalPanel messagePanel =new VerticalPanel();
        Panel msgPanel=new Panel();
        msgPanel.setFrame(true);
        
        messagePanel.add(text);
        messagePanel.add(text1);
        messagePanel.add(hyp);
        msgPanel.add(messagePanel);
        msgPanel.setSize("50%", "150px");
        BodyVerticalPanel.add(msgPanel);
        //BodyVerticalPanel.add(text);
        
        
        
        //linkPanelTop.setSpacing(15);
        linkPanelTop.add(welcomeLabel);
        
        connectService.getUserNameForAccount(userName,new AsyncCallback<String>() {
			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.getMessage());
			}
			public void onSuccess(String name) {
				linkPanelTop.add(new Label(name));
			}
		});
        
        
        //Add****************************************
        Hyperlink home=new Hyperlink(constants.home(),"");
        
        HorizontalPanel signoutPanel=new HorizontalPanel();
        signoutPanel.add(signOut);
        
        HorizontalPanel homePanel=new HorizontalPanel();
        homePanel.add(home);
        
        HypMainPanelTop.add(homePanel);
        HypMainPanelTop.setCellHorizontalAlignment(homePanel,HasHorizontalAlignment.ALIGN_LEFT);
        HypMainPanelTop.setCellVerticalAlignment(homePanel,HasVerticalAlignment.ALIGN_MIDDLE);
        HypMainPanelTop.add(signoutPanel);
        HypMainPanelTop.setCellHorizontalAlignment(signoutPanel, HasHorizontalAlignment.ALIGN_RIGHT);
        //HypMainPanelTop.setCellVerticalAlignment(signOut, HasVerticalAlignment.ALIGN_MIDDLE);
        HypMainPanelTop.setSpacing(5);

        headerPanel.add(headerLabel);
        headerPanel.add(headerLabel1);
        headerPanel.add(headerLabel2);
        headerPanel.add(linkPanelTop);
        headerPanel.setCellHorizontalAlignment(linkPanelTop, HasHorizontalAlignment.ALIGN_LEFT);
        headerPanel.add(HypMainPanelTop);

        HypMainPanelBottom.add(linkPanelBottom);
        HypMainPanelBottom.setCellHorizontalAlignment(linkPanelBottom,HasHorizontalAlignment.ALIGN_CENTER);
        HypMainPanelBottom.setCellVerticalAlignment(linkPanelBottom,HasVerticalAlignment.ALIGN_MIDDLE);

        
        bodyPanel.setHeight("465");
        bodyPanel.add(BodyVerticalPanel);
        bodyPanel.add(sidePanel);
        bodyPanel.setCellHorizontalAlignment(sidePanel, HasHorizontalAlignment.ALIGN_RIGHT);
        bodyPanel.setSpacing(5);
        
        //footerPanel.setHeight("70");

        footerPanel.add(HypMainPanelBottom);
        footerPanel.add(footerLabel);
       
        mainPanel.add(headerPanel);
        mainPanel.add(bodyPanel);
        mainPanel.add(footerPanel);
        
        signOut.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent arg0) {
            	
                RootPanel.get().clear();
                mainPanel.clear();
                UserLogin login = new UserLogin(usrId);
                login.onModuleLoad();
                RootPanel.get().add(login.mainPanel);
            }
        });
                	
        home.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				RootPanel.get().clear();
				UserAccount usrAccount=new UserAccount(userName,usrId);
				usrAccount.onModuleLoad();
				RootPanel.get().add(usrAccount.mainPanel);
			}
		});
        hyp.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				BodyVerticalPanel.clear();
				sidePanel.setVisible(false);
				System.out.println("uid,user"+usrId+" "+userName);
				ProgramSearchSetup progSearch=new ProgramSearchSetup(usrId,userName);
				progSearch.searchProgramSetup();
				BodyVerticalPanel.add(progSearch.vPanel);
				
				
			}
		});
        
        changePasswordHyp.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent arg0) {
				BodyVerticalPanel.clear();
				ApplicantChangePassword appPassword=new ApplicantChangePassword(userName,usrId);
				appPassword.onModuleLoad();
				BodyVerticalPanel.add(appPassword.mainVPanel);
				
			}
		});
        
        appliedProgram.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent arg0) {
			
				BodyVerticalPanel.clear();
				ApplicantPrograms appProgram=new ApplicantPrograms(userName);
				appProgram.onModuleLoad();
				BodyVerticalPanel.add(appProgram.mainVPanel);
			}
		});
        
        ManageAccount.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent arg0) {
				BodyVerticalPanel.clear();
				ManageApplicantAccount manageAccount=new ManageApplicantAccount(userName,usrId);
				manageAccount.onModuleLoad();
				BodyVerticalPanel.add(manageAccount.mainVPanel);
			}
		});
        
        RootPanel.get().add(mainPanel);

        return mainPanel;
	}
}
