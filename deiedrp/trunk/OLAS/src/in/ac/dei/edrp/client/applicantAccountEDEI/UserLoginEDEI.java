/*
 * @(#) UserLoginEDEI.java
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

import in.ac.dei.edrp.client.EdeiAdmission.EDEIStudentBean;
import in.ac.dei.edrp.client.EdeiAdmission.EDeiSummaryService;
import in.ac.dei.edrp.client.EdeiAdmission.EDeiSummaryServiceAsync;
import in.ac.dei.edrp.client.Shared.CommonValidation;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.applicantAccount.ApplicantAccountSetup;
import in.ac.dei.edrp.client.applicantAccount.MainLoginPage;
import in.ac.dei.edrp.client.applicantAccount.UserProgramSelection;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
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
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.form.event.TextFieldListenerAdapter;
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

public class UserLoginEDEI implements EntryPoint {

	AccountLoginEDEIAsync connectService = (AccountLoginEDEIAsync) GWT.create(AccountLoginEDEI.class);
	EDeiSummaryServiceAsync summaryServiceAsync = GWT.create(EDeiSummaryService.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	String uId;
	public final VerticalPanel mainPanel = new VerticalPanel();
	final VerticalPanel headerPanel = new VerticalPanel();
	final HorizontalPanel bodyPanel = new HorizontalPanel();
	final HorizontalPanel bodyMainPanel=new HorizontalPanel();
	HorizontalPanel bodyDesPanel=new HorizontalPanel();
	final VerticalPanel footerPanel = new VerticalPanel();

	final HorizontalPanel HypMainPanelTop = new HorizontalPanel();
	final HorizontalPanel HypMainPanelBottom = new HorizontalPanel();
	final HorizontalPanel linkPanelBottom = new HorizontalPanel();
	final HorizontalPanel linkPanelTop = new HorizontalPanel();

	final Label headerLabel = new Label(constants.title());
	final Label headerLabel1 = new Label(constants.heading1());
	final Label headerLabel2 = new Label(constants.heading2());
	final Label footerLabel = new Label(constants.footer()+constants.footer1());
	FlexTable f=new FlexTable();
	String pgId;

	final Hyperlink blank = new Hyperlink("", "");
	String uniNickName=constants.uniNickName();
	public UserLoginEDEI(String uId){
		this.uId=uId;
	}

	public void onModuleLoad() {
		init();
	}
	VerticalPanel init() {

		//Property Set***********************************
		final VerticalPanel BodyVerticalPanel = new VerticalPanel();
		BodyVerticalPanel.setHeight("450");
		BodyVerticalPanel.setWidth("100%");

		mainPanel.setWidth("100%");
		mainPanel.setHeight("100%");
		headerPanel.setWidth("100%");

		bodyMainPanel.setWidth("100%");
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
		loginPanel.setTitle("Login");
		loginPanel.setSize("300px", "181px");

		FlexTable flexTable = new FlexTable();
		flexTable.setSize("280px", "166px");

		Label userNameLabel = new Label(constants.userName());
		flexTable.setWidget(0, 0, userNameLabel);

		final TextField userName = new TextField();
		userName.setEmptyText(constants.enterUserName());
		userName.setAllowBlank(false);
		userName.setVtype(VType.EMAIL);
		userName.setValidateOnBlur(true);
		flexTable.setWidget(0, 1, userName);

		Label passwordLabel = new Label(constants.password());
		flexTable.setWidget(1, 0, passwordLabel);

		final TextField password = new TextField();
		password.setEmptyText(constants.enterPassword());
		password.setAllowBlank(false);
		password.setPassword(true);
		password.setValidateOnBlur(true);
		flexTable.setWidget(1, 1, password);

		final Button signIn = new Button(constants.signIn());
		flexTable.setWidget(2, 0, signIn);
		signIn.setWidth("65px");

		Label newUserLabel = new Label(constants.newUser());
		flexTable.setWidget(3, 0, newUserLabel);

		final Button createAccount = new Button(constants.createAccount());
		flexTable.setWidget(3, 1, createAccount);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_CENTER);

		loginPanel.add(flexTable);
		BodyVerticalPanel.add(loginPanel);
		BodyVerticalPanel.setCellVerticalAlignment(loginPanel, HasVerticalAlignment.ALIGN_MIDDLE);
		BodyVerticalPanel.setCellHorizontalAlignment(loginPanel,HasHorizontalAlignment.ALIGN_RIGHT);
		BodyVerticalPanel.setSpacing(30);

		linkPanelTop.setSpacing(15);
		linkPanelTop.add(blank);

		//Add****************************************

		Hyperlink back=new Hyperlink("Back","");
		back.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				RootPanel.get().clear();
				mainPanel.clear();
				MainLoginPage accountUser=new MainLoginPage(uId);
				try{
					accountUser.onModuleLoad();
					RootPanel.get().add(accountUser.mainPanel);

				}catch(Exception exx){
					System.out.println(exx.getStackTrace());
					System.out.println(exx);
				}

			}
		});

		linkPanelTop.add(back);

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

		bodyDesPanel.setHeight("465");
		bodyDesPanel.setSpacing(0);

		bodyMainPanel.setHeight("465");
		bodyMainPanel.setSpacing(5);

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
							bodyDesPanel.add(onTradeClick(domainCode,"UG",universityId,domainName,false));
						}
					});

					postGraduate.addListener(new TreeNodeListenerAdapter() {
						public void onDblClick(Node node, EventObject e) {
							onClick(node, e);
						}
						public void onClick(Node node, EventObject e) {   
							f.clear();
							bodyDesPanel.clear();
							bodyDesPanel.add(onTradeClick(domainCode,"PG",universityId,domainName,false));						
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
				bodyAdd.setWidget(0,1, bodyDesPanel);

				bodyMainPanel.add(bodyAdd);
				bodyMainPanel.add(BodyVerticalPanel);

				footerPanel.add(HypMainPanelBottom);
				footerPanel.add(footerLabel);

				mainPanel.add(headerPanel);
				mainPanel.add(bodyMainPanel);
				mainPanel.add(footerPanel);

				createAccount.addListener(new ButtonListenerAdapter(){
					public void onClick(Button button, EventObject e) {
						bodyMainPanel.clear();
						bodyMainPanel.setSpacing(10);
						ApplicantAccountSetup accountApplicant=new ApplicantAccountSetup(uId,3);
						try{
							accountApplicant.applicantAccountSetup();
							bodyMainPanel.add(accountApplicant.verticalPanel);

						}catch(Exception exx){
							System.out.println(exx.getStackTrace());
						}
					}
				});

				signIn.addListener(new ButtonListenerAdapter(){
					public void onClick(Button button, EventObject e) {
						if((userName.getValueAsString().length()>0)&&(password.getValueAsString().length()>0)){
							if(CommonValidation.validate(userName)){
								connectService.getLogInConfirmation(userName.getValueAsString(),password.getValueAsString(),new AsyncCallback<Integer>() {
									public void onFailure(Throwable arg0) {
										MessageBox.alert(arg0.getMessage());
									}
									public void onSuccess(Integer count) {
										if(count>0){
											RootPanel.get().clear();
											mainPanel.clear();
											UserProgramSelection accountUser=new UserProgramSelection(uId,userName.getValueAsString(),uniNickName);
											try{
												accountUser.onModuleLoad();
												RootPanel.get().add(accountUser.mainPanel);

											}catch(Exception exx){
												System.out.println(exx.getStackTrace());
												System.out.println(exx);
											}
										}
										else MessageBox.alert(msgs.invalidUserPass());
									}
								});
							}
							else MessageBox.alert(msgs.invalidUser());
						}
						else MessageBox.alert(msgs.enterEmailPass());
					}
				});
				RootPanel.get().add(mainPanel);
			}
		});
		return mainPanel;

	}

	public Panel onTradeClick(String trade, String tenCode, final String universityId,final String domainName,final boolean flag){
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
								if(!flag){
									EDEIStudentBean inputBean=new EDEIStudentBean();
									inputBean.setProgramId(pgId);
									inputBean.setUniversityId(universityId);
									final TextField searchText=new TextField();
									FlexTable addSearch=new FlexTable();
									addSearch.setWidget(0, 0, new HTML("<B>"+constants.search()+"</B>"));
									addSearch.setWidget(0, 1, new Label(" "));
									addSearch.setWidget(0, 2, searchText);
									f.setWidget(1, 0, addSearch);
									summaryServiceAsync.getUniversityOnlineProgramModule(inputBean,new AsyncCallback<List<EDEIStudentBean>>(){
										public void onFailure(Throwable arg0) {
											MessageBox.alert(arg0.getMessage());
										}
										public void onSuccess(final List<EDEIStudentBean> resultDb) {
											createGrid(resultDb);
											searchText.addListener(new TextFieldListenerAdapter() {
												public void onChange(Field field, Object newVal, Object oldVal) {
													List<EDEIStudentBean> filtered=filterList(resultDb, searchText.getValueAsString());
													createGrid(filtered);
												}
												public void onFocus(Field field) {
													onChange(field, "", "");
												}
												@SuppressWarnings("static-access")
												public void onSpecialKey(Field field, EventObject e) {
													if(e.getKey()==e.ENTER){
														List<EDEIStudentBean> filtered=filterList(resultDb, searchText.getValueAsString());
														createGrid(filtered);
													}
												}
											});
										}
										private void createGrid(List<EDEIStudentBean> resultDb) {
											final GridPanel moduleGrid = new GridPanel();
											Object[][] data = new Object[resultDb.size()][11];
											for (int i = 0; i < resultDb.size(); i++) {
												data[i][0] = resultDb.get(i).getModuleName();
												data[i][1]= resultDb.get(i).getModuleId();
												data[i][2]=resultDb.get(i).getModuleStartDate();
												data[i][3]=resultDb.get(i).getModuleEndDate();
												data[i][4]=resultDb.get(i).getProgCourseKey();
												data[i][5]=resultDb.get(i).getEntityId();
												data[i][6]=resultDb.get(i).getCoursesId();
												data[i][7]=resultDb.get(i).getCoursesName();
												data[i][8]=resultDb.get(i).getCredit();
												data[i][9]=resultDb.get(i).getSemesterStartDate();
												data[i][10]=resultDb.get(i).getSemesterEndDate();
											}
											final CheckboxSelectionModel cbSelectionModelModule=new CheckboxSelectionModel();
											RecordDef recordDef = new RecordDef(new FieldDef[] {
													new StringFieldDef("moduleName"),
													new StringFieldDef("moduleId"),
													new StringFieldDef("startDate"),
													new StringFieldDef("endDate"),
													new StringFieldDef("progCourseKey"),
													new StringFieldDef("entity"),
													new StringFieldDef("courseId"),
													new StringFieldDef("courseName"),
													new StringFieldDef("credit"),
													new StringFieldDef("semesterStartDate"),
													new StringFieldDef("semesterEndDate")
											});
											MemoryProxy proxy = new MemoryProxy(data);
											ArrayReader reader = new ArrayReader(recordDef);
											Store store = new Store(proxy, reader);
											store.load();
											moduleGrid.setStore(store);
											BaseColumnConfig[] columns = new BaseColumnConfig[] {
													new ColumnConfig(constants.moduleCode(),"moduleId", 80, true, null,"moduleId"),		
													new ColumnConfig(constants.module(),"moduleName", 80, true, null,"moduleName"),
													new ColumnConfig(constants.courseCode(),"courseId", 80, true, null,"courseId"),		
													new ColumnConfig(constants.course(),"courseName", 80, true, null,"courseName"),
													new ColumnConfig(constants.startDate(),"startDate", 80, true, null,"startDate"),		
													new ColumnConfig(constants.endDate(),"endDate", 80, true, null,"endDate"),
													new ColumnConfig(constants.credits(),"credit", 80, true, null,"credit"),
											};
											ColumnModel columnModel = new ColumnModel(columns);
											moduleGrid.setColumnModel(columnModel);
											moduleGrid.setFrame(true);
											moduleGrid.setAutoExpandColumn("moduleName");
											moduleGrid.setSelectionModel(cbSelectionModelModule);
											moduleGrid.setWidth(430);
											moduleGrid.setHeight(200);
											moduleGrid.setTitle(constants.selectModuleSession()+" ");
											moduleGrid.setStripeRows(true);
											f.setWidget(2, 0, moduleGrid);
										}
									});
								}
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

	List<EDEIStudentBean> filterList(List<EDEIStudentBean> resultDb, String filterTxt) {  
		List<EDEIStudentBean> filteredList = new ArrayList<EDEIStudentBean>();
		for(EDEIStudentBean course: resultDb) {  
			if((course.getModuleId().toLowerCase().contains(filterTxt.toLowerCase()))||
					(course.getModuleName().toLowerCase().contains(filterTxt.toLowerCase()))||
					(course.getCoursesId().toLowerCase().contains(filterTxt.toLowerCase()))||
					(course.getCoursesName().toLowerCase().contains(filterTxt.toLowerCase()))
					){
				filteredList.add(course);
			}
		}
		if(filteredList.size()<1){
			return resultDb;
		}
		return filteredList;  
	}
}

