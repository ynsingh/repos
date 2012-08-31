/*
 * @(#) UserAccountEDEI.java
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
package in.ac.dei.edrp.client.applicantAccountEDEI;

import in.ac.dei.edrp.client.EdeiAdmission.EDEIAdmission;
import in.ac.dei.edrp.client.EdeiAdmission.EDEIStudentBean;
import in.ac.dei.edrp.client.EdeiAdmission.EDEISummarySheetForNEWProgram;
import in.ac.dei.edrp.client.EdeiAdmission.EDeiSummaryService;
import in.ac.dei.edrp.client.EdeiAdmission.EDeiSummaryServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.applicantAccount.UserProgramSelection;

import java.util.List;

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
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Node;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.event.TreeNodeListenerAdapter;

/**
 * 
 * @version 1.0 9 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 */

public class UserAccountEDEI {

	AccountLoginEDEIAsync connectService = (AccountLoginEDEIAsync) GWT.create(AccountLoginEDEI.class);
	EDeiSummaryServiceAsync summaryServiceAsync = GWT.create(EDeiSummaryService.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	public final VerticalPanel mainPanel = new VerticalPanel();
	public VerticalPanel BodyVerticalPanel = new VerticalPanel();
	public VerticalPanel sidePanel=new VerticalPanel();
	final VerticalPanel headerPanel = new VerticalPanel();
	final HorizontalPanel bodyPanel = new HorizontalPanel();
	final VerticalPanel footerPanel = new VerticalPanel();
	final HorizontalPanel bodyMainPanel=new HorizontalPanel();
	final HorizontalPanel HypMainPanelTop = new HorizontalPanel();
	final HorizontalPanel HypMainPanelBottom = new HorizontalPanel();
	final HorizontalPanel linkPanelBottom = new HorizontalPanel();
	final HorizontalPanel linkPanelTop = new HorizontalPanel();
	String userEmail;
	String usrId;
	String universityId;
	String uniNickName;
	String sessionStartDate;
	String sessionEndDate;
	String enrollmentNumber;
	static String pgId;
	Label welcomeLabel = new Label(constants.welcome()+" ");
	final Label headerLabel = new Label(constants.title());
	final Label headerLabel1 = new Label(constants.heading1());
	final Label headerLabel2 = new Label(constants.heading2()); 
	final Label footerLabel = new Label(constants.footer()+constants.footer1());

	final Hyperlink blank = new Hyperlink("", "");

	Hyperlink signOut=new Hyperlink(constants.signOut(),"");
	HorizontalPanel bodyDesPanel=new HorizontalPanel();

	Button onlineProg=new Button();
	FlexTable f=new FlexTable();

	public UserAccountEDEI(String userId,String userEmail,String uniNickName){
		this.userEmail=userEmail;
		this.usrId=userId;
		this.uniNickName=uniNickName;
	}
	
	public UserAccountEDEI(String userId,String userEmail,String uniNickName,String enrollment){
		this.userEmail=userEmail;
		this.usrId=userId;
		this.uniNickName=uniNickName;
		this.enrollmentNumber=enrollment;
	}
	
	public void onModuleLoad() {
		init();
	}

	VerticalPanel init() {

		//Property Set***********************************

		BodyVerticalPanel.setHeight("450");
		BodyVerticalPanel.setWidth("90%");

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

		sidePanel.add(sideTable);

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

		text.setStyleName("HeadingMessage");

		linkPanelTop.add(welcomeLabel);

		connectService.getUserNameForAccount(userEmail,new AsyncCallback<String>() {
			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.getMessage());
			}
			public void onSuccess(String name) {
				linkPanelTop.add(new Label(name));
			}
		});

		bodyPanel.setHeight("465");

		bodyDesPanel.setHeight("465");
		bodyDesPanel.setSpacing(0);
		bodyMainPanel.setHeight("465");
		bodyMainPanel.setSpacing(5);

		onlineProg.setText(constants.applyProgram());

		final Panel accordionPanel =new Panel();
		accordionPanel.setTitle(constants.onlineProgram());
		accordionPanel.setWidth(230);
		accordionPanel.setHeight(450);
		accordionPanel.setLayout(new AccordionLayout(true));

		summaryServiceAsync.getUniversityOnlineDomains(uniNickName,new AsyncCallback<List<EDEIStudentBean>>() {
			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.getMessage());
			}

			public void onSuccess(List<EDEIStudentBean> domainList) {
				for(int i=0;i<domainList.size();i++){
					final String domainCode=domainList.get(i).getDomainCode();
					final String universityId=domainList.get(i).getUniversityId();
					final String domainName=domainList.get(i).getDomainName();
					Panel panelOne = new Panel(domainList.get(i).getDomainName());

					TreePanel treePanel=new TreePanel();
					treePanel.setHeight(330);
					treePanel.setLines(false);

					TreeNode root = new TreeNode();  
					final TreeNode underGraduate = new TreeNode(constants.underGraduate());
					underGraduate.setExpanded(true);
					final TreeNode postGraduate = new TreeNode(constants.postGraduate());  
					postGraduate.setExpanded(true);
					root.appendChild(underGraduate);
					root.appendChild(postGraduate);

					treePanel.setRootVisible(false);  
					treePanel.setRootNode(root);  
					root.setExpanded(true);

					underGraduate.addListener(new TreeNodeListenerAdapter() {
						public void onDblClick(Node node, EventObject e) {
							onClick(node, e);
						}
						public void onClick(Node node, EventObject e) {
							f.clear();
							bodyDesPanel.clear();
							bodyDesPanel.add(onTradeClick(domainCode,"UG",universityId,domainName));
						}
					});

					postGraduate.addListener(new TreeNodeListenerAdapter() {
						public void onDblClick(Node node, EventObject e) {
							onClick(node, e);
						}
						public void onClick(Node node, EventObject e) {   
							f.clear();
							bodyDesPanel.clear();
							bodyDesPanel.add(onTradeClick(domainCode,"PG",universityId,domainName));						
						}
					});
					panelOne.add(treePanel);
					accordionPanel.add(panelOne);
				}

				FlexTable bodyAdd=new FlexTable();

				bodyAdd.setWidth("700");
				bodyAdd.setHeight("465");

				bodyPanel.add(accordionPanel);

				bodyAdd.setWidget(0, 0, bodyPanel);
				bodyAdd.setWidget(0, 1,bodyDesPanel);

				bodyMainPanel.add(bodyAdd);
				bodyMainPanel.add(BodyVerticalPanel);

				onlineProg.addListener(new ButtonListenerAdapter(){
					public void onClick(Button button, EventObject e) {
						connectService.getUniversityDetailsDEINew(uniNickName,new AsyncCallback<List<ApplicantAccountEDEIBean>>() {

							public void onFailure(Throwable arg0) {
								MessageBox.alert(arg0.getMessage());
							}

							public void onSuccess(List<ApplicantAccountEDEIBean> arg0) {
								bodyMainPanel.clear();
								bodyMainPanel.setWidth("100%");
								universityId=arg0.get(0).getUniversityId();
								sessionStartDate=arg0.get(0).getSessionStartDate();
								sessionEndDate=arg0.get(0).getSessionEndDate();
								if(enrollmentNumber==null || enrollmentNumber.equals(" ")){
									EDEIAdmission edei=new EDEIAdmission(uniNickName,userEmail,sessionStartDate,sessionEndDate,universityId,pgId,usrId);
									edei.onModuleLoad();
									bodyMainPanel.add(edei.formPanel);
								}
								else{
									EDEISummarySheetForNEWProgram edei=new EDEISummarySheetForNEWProgram(uniNickName,userEmail,sessionStartDate,sessionEndDate,universityId,pgId,usrId,enrollmentNumber);
									edei.onModuleLoad();
									bodyMainPanel.add(edei.formPanel);
								}
								
							}
						});
					}

				});

				//Add****************************************
				Hyperlink home=new Hyperlink(constants.home(),"");
				Hyperlink back=new Hyperlink(constants.backButton(),"");
				HorizontalPanel signoutPanel=new HorizontalPanel();
				signoutPanel.add(signOut);

				HorizontalPanel homePanel=new HorizontalPanel();
				homePanel.add(home);
				
				HorizontalPanel backPanel=new HorizontalPanel();			
				backPanel.add(back);
				
				HypMainPanelTop.add(homePanel);
				HypMainPanelTop.setCellHorizontalAlignment(homePanel,HasHorizontalAlignment.ALIGN_LEFT);
				HypMainPanelTop.setCellVerticalAlignment(homePanel,HasVerticalAlignment.ALIGN_MIDDLE);
				HypMainPanelTop.add(backPanel);
				HypMainPanelTop.setCellHorizontalAlignment(backPanel,HasHorizontalAlignment.ALIGN_CENTER);
				HypMainPanelTop.setCellVerticalAlignment(backPanel,HasVerticalAlignment.ALIGN_MIDDLE);
				HypMainPanelTop.add(signoutPanel);
				HypMainPanelTop.setCellHorizontalAlignment(signoutPanel, HasHorizontalAlignment.ALIGN_RIGHT);
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

				footerPanel.add(HypMainPanelBottom);
				footerPanel.add(footerLabel);

				mainPanel.add(headerPanel);
				mainPanel.add(bodyMainPanel);
				mainPanel.add(footerPanel);

				signOut.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent arg0) {
						RootPanel.get().clear();
						mainPanel.clear();
						UserLoginEDEI login = new UserLoginEDEI(usrId);
						login.onModuleLoad();
						RootPanel.get().add(login.mainPanel);
					}
				});

				home.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent arg0) {
						RootPanel.get().clear();
						UserProgramSelection usrAccount=new UserProgramSelection(usrId,userEmail,uniNickName);
						usrAccount.onModuleLoad();
						RootPanel.get().add(usrAccount.mainPanel);
					}
				});
				
				back.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent arg0) {
						RootPanel.get().clear();
						UserAccountEDEI usrAccount=null;
						if(enrollmentNumber==null || enrollmentNumber.equals(" ")){
							usrAccount=new UserAccountEDEI(usrId,userEmail,uniNickName);
						}
						else{
							usrAccount=new UserAccountEDEI(usrId,userEmail,uniNickName,enrollmentNumber);
						}
						usrAccount.onModuleLoad();
						RootPanel.get().add(usrAccount.mainPanel);
					}
				});
				RootPanel.get().add(mainPanel);
			}
		});

		return mainPanel;
	}

	public Panel onTradeClick(String trade, String tenCode, final String universityId,final String domainName){

		EDEIStudentBean input=new EDEIStudentBean();
		input.setUniversityId(universityId);
		input.setDomainCode(trade);
		input.setUgPg(tenCode);
		input.setProgramType("E");
		Panel p=new Panel();
		p.setStyleName("panelColorMy");
		p.setHeight(450);
		p.setWidth(440);
		p.setTitle(domainName);

		summaryServiceAsync.getUniversityOnlineProgram(input,new AsyncCallback<List<EDEIStudentBean>>() {
			public void onFailure(Throwable arg0) {
				System.out.println(arg0.getMessage());
			}
			public void onSuccess(List<EDEIStudentBean> progList) {
				if(progList.size()>0){
					final GridPanel progGrid = new GridPanel();
					Object[][] data = new Object[progList.size()][2];
					for (int i = 0; i < progList.size(); i++) {
						data[i][0] = progList.get(i).getProgramId();
						data[i][1]= progList.get(i).getProgramName();
					}
					final CheckboxSelectionModel cbSelectionModelProg=new CheckboxSelectionModel();
					RecordDef recordDef = new RecordDef(new FieldDef[] {
							new StringFieldDef("progId"),
							new StringFieldDef("progName")
					});
					MemoryProxy proxy = new MemoryProxy(data);
					ArrayReader reader = new ArrayReader(recordDef);
					Store store = new Store(proxy, reader);
					store.load();
					progGrid.setStore(store);
					BaseColumnConfig[] columns = new BaseColumnConfig[] {
							new ColumnConfig(constants.programme(),"progName", 80, true, null,"progName")		
					};

					ColumnModel columnModel = new ColumnModel(columns);
					progGrid.setColumnModel(columnModel);
					progGrid.setFrame(true);
					progGrid.setAutoExpandColumn("progName");
					progGrid.setSelectionModel(cbSelectionModelProg);
					progGrid.setWidth(430);
					progGrid.setHeight(200);
					progGrid.setTitle(constants.programme()+" ");
					progGrid.setStripeRows(true);

					f.setWidget(0, 0, progGrid);

					cbSelectionModelProg.addListener(new RowSelectionListenerAdapter() {
						public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
							if(cbSelectionModelProg.getSelections().length==1){
								final Record[] records = cbSelectionModelProg.getSelections();
								pgId=records[0].getAsString("progId");

								f.setWidget(1, 0, onlineProg);
								f.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
								f.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
							}
							else{
								MessageBox.alert(msgs.pleaseSelectOneRecord());
								f.removeCell(1, 0);	
							}
						}
						public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
							if(cbSelectionModelProg.getSelections().length==1){

							}
							else{
								f.removeCell(1, 0);
							}
						}
					});
				}
				else MessageBox.alert(msgs.noProgFound(domainName));
			}
		});
		p.add(f);
		return p;
	}
}
