/*
 * @(#) MainLoginPage.java
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
import in.ac.dei.edrp.client.applicantAccountEDEI.UserLoginEDEI;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.Label;

/**
 * 
 * @version 1.0 9 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 */

public class MainLoginPage implements EntryPoint {

	ApplicantAccountAsync connectService = (ApplicantAccountAsync) GWT.create(ApplicantAccount.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	String uId="E0001";
	
	public final VerticalPanel mainPanel = new VerticalPanel();
    final VerticalPanel headerPanel = new VerticalPanel();
    final HorizontalPanel bodyPanel = new HorizontalPanel();
    final VerticalPanel footerPanel = new VerticalPanel();
    
    final HorizontalPanel HypMainPanelTop = new HorizontalPanel();
    final HorizontalPanel HypMainPanelBottom = new HorizontalPanel();
    final HorizontalPanel linkPanelBottom = new HorizontalPanel();
    final HorizontalPanel linkPanelTop = new HorizontalPanel();
    final DecoratorPanel BodyDecoratorPanel = new DecoratorPanel();
    
    final Label headerLabel = new Label(constants.title());
    final Label headerLabel1 = new Label(constants.heading1());
    final Label headerLabel2 = new Label(constants.heading2());
    final Label footerLabel = new Label(constants.footer()+constants.footer1());
    
    final Hyperlink blank = new Hyperlink("", "");
    final Hyperlink backHyperLink = new Hyperlink("Back to Main", null);
    public String urlHome;
    
    public MainLoginPage(){
    	
    }
    
    public MainLoginPage(String uId){
    	this.uId=uId;
    }
    
 	public void onModuleLoad() {
 		History.newItem("apply");
		init();
	}
	VerticalPanel init() {
        
		//Property Set***********************************
        HorizontalPanel BodyVerticalPanel = new HorizontalPanel();
        BodyVerticalPanel.setHeight("450");
        BodyVerticalPanel.setWidth("100%");
        BodyDecoratorPanel.setWidth("100%");

        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        headerPanel.setWidth("100%");
        bodyPanel.setWidth("100%");
        footerPanel.setWidth("100%");
        HypMainPanelTop.setWidth("100%");
        HypMainPanelBottom.setWidth("100%");
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
        
        FieldSet loginPanel = new FieldSet();
    	loginPanel.setTitle("Create Account");
    	loginPanel.setSize("300px", "120px");

    	FlexTable flexTable = new FlexTable();
    	flexTable.setSize("280px", "100px");
        
 		Label newUserLabel = new Label(constants.newUser());
		flexTable.setWidget(1, 0, newUserLabel);
		
		Button createAccount = new Button(constants.createAccount());
		flexTable.setWidget(1, 1, createAccount);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_CENTER);
		
		
		Hyperlink regularProgram=new Hyperlink(constants.regularApplication(),"");
			regularProgram.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {

				RootPanel.get().clear();
				UserLogin ul=new UserLogin(uId);
				ul.onModuleLoad();
				RootPanel.get().add(ul.mainPanel);
			}
		});
		
		Hyperlink onlineProgram=new Hyperlink(constants.onlineApplication(),"");
        onlineProgram.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {

				RootPanel.get().clear();
				UserLoginEDEI ul=new UserLoginEDEI(uId);
				ul.onModuleLoad();
				RootPanel.get().add(ul.mainPanel);
			}
		});
        
        FlexTable program=new FlexTable();
        program.setWidget(0, 0, regularProgram);
        program.setWidget(2, 0, onlineProgram);
        
        
        BodyVerticalPanel.add(program);
        BodyVerticalPanel.setCellVerticalAlignment(program, HasVerticalAlignment.ALIGN_MIDDLE);
        BodyVerticalPanel.setCellHorizontalAlignment(program,HasHorizontalAlignment.ALIGN_LEFT);

        loginPanel.add(flexTable);
        BodyVerticalPanel.add(loginPanel);
        BodyVerticalPanel.setCellVerticalAlignment(loginPanel, HasVerticalAlignment.ALIGN_MIDDLE);
        BodyVerticalPanel.setCellHorizontalAlignment(loginPanel,HasHorizontalAlignment.ALIGN_RIGHT);
        BodyVerticalPanel.setSpacing(30);
        
        BodyDecoratorPanel.add(BodyVerticalPanel);
        
        linkPanelTop.setSpacing(15);
        linkPanelTop.add(backHyperLink);
        linkPanelTop.add(blank);

        HypMainPanelTop.add(linkPanelTop);
        HypMainPanelTop.setCellHorizontalAlignment(linkPanelTop,HasHorizontalAlignment.ALIGN_CENTER);
        HypMainPanelTop.setCellVerticalAlignment(linkPanelTop,HasVerticalAlignment.ALIGN_MIDDLE);

        headerPanel.add(headerLabel);
        headerPanel.add(headerLabel1);
        headerPanel.add(headerLabel2);
        headerPanel.add(HypMainPanelTop);

        HypMainPanelBottom.add(linkPanelBottom);
        HypMainPanelBottom.setCellHorizontalAlignment(linkPanelBottom,HasHorizontalAlignment.ALIGN_CENTER);
        HypMainPanelBottom.setCellVerticalAlignment(linkPanelBottom,HasVerticalAlignment.ALIGN_MIDDLE);

        bodyPanel.setHeight("465");
        bodyPanel.add(BodyDecoratorPanel);
 
        footerPanel.add(HypMainPanelBottom);
        footerPanel.add(footerLabel);
       
        mainPanel.add(headerPanel);
        mainPanel.add(bodyPanel);
        
        mainPanel.add(footerPanel);
        
        createAccount.addListener(new ButtonListenerAdapter(){
        	
        	public void onClick(Button button, EventObject e) {
        		bodyPanel.clear();
        		bodyPanel.setSpacing(10);
        		int flag=1;
        		ApplicantAccountSetup accountApplicant=new ApplicantAccountSetup(uId,flag);
        		try{
					accountApplicant.applicantAccountSetup();
					bodyPanel.add(accountApplicant.verticalPanel);
				
				}catch(Exception exx){
					System.out.println(exx.getStackTrace());
				}
        	}
        });
        
        backHyperLink.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent arg0) {
            	redirect(urlHome);
            }
        });

        
        RootPanel.get().add(mainPanel);

        return mainPanel;
	}
	
	//redirect the browser to the given url
    public static native void redirect(String url)/*-{
          $wnd.location = url;
      }-*/;
}
