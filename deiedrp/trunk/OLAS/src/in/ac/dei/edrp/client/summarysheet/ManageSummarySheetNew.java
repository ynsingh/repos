/*
 * @(#) ManageSummarySheet.java
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
package in.ac.dei.edrp.client.summarysheet;

import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.RPCFiles.SummarySheetService;
import in.ac.dei.edrp.client.RPCFiles.SummarySheetServiceAsync;
import in.ac.dei.edrp.client.Shared.AddressField;
import in.ac.dei.edrp.client.Shared.DocumentUpload;
import in.ac.dei.edrp.client.Shared.OA_ComboBoxes;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.MessageBox.ConfirmCallback;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;

/**
 * 
 * @version 1.0 
 * @author DEVENDRA SINGHAL
 * @date May 19 2012
 */

public class ManageSummarySheetNew {
	SummarySheetServiceAsync summaryServiceAsync = GWT.create(SummarySheetService.class);
	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
	CM_connectTempAsync categoryServiceAsync = GWT.create(CM_connectTemp.class);
	private final constants constant = GWT.create(constants.class);
	private final messages message = GWT.create(messages.class);	
	public VerticalPanel vPanel = new VerticalPanel();
	String userId = "";
	public ManageSummarySheetNew(String userID) {
		this.userId = userID;
	}
	
	public void onModuleLoad(final String action){		
		vPanel.clear();	
		
		final Panel vStudentPanel = new Panel();
		if(action.equals("edit")){
			vStudentPanel.setTitle(constant.edit()+" "+constant.summarySheet());
		}
		else if(action.equals("delete")){
			vStudentPanel.setTitle(constant.delete()+" "+constant.summarySheet());
		}		
		final FlexTable upperFlexTable = new FlexTable();
		final FlexTable buttonTable = new FlexTable();
		final FlexTable mainTable = new FlexTable();
		final FieldSet searchByReg=new FieldSet(constant.searchByReg());		
		Label registrationLabel=new Label(constant.enterRegistratonNo());		
		final TextField registrationNo=new TextField();
		registrationNo.setAllowBlank(false);		
		final Button okButton =new Button(constant.okButton());
		final Button cancelButton =new Button(constant.cancelButton());
		okButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				final GridPanel grid=new GridPanel();
				final ToolbarButton deletebutton = new ToolbarButton(constant.delete());
				final ToolbarButton editButton = new ToolbarButton(constant.edit());
				SummarySheetBean input=new SummarySheetBean();
				input.setRegistrationNumber(registrationNo.getText()+"%");
				input.setUniversityId(userId.substring(1, 5));
				summaryServiceAsync.getStudentsForManage(input, new AsyncCallback<List<SummarySheetBean>>(){
					@Override
					public void onFailure(Throwable arg0) {
						MessageBox.alert(message.failure(), arg0.getMessage());					
					}
					@Override
					public void onSuccess(List<SummarySheetBean> result) {
						if(result.size()<1){			
							if(action.equals("edit")){
								MessageBox.alert(message.info(), message.studentNotFoundFor("edit"));
							}
							else{
								MessageBox.alert(message.info(), message.studentNotFoundFor("delete"));
							}
							
							if(mainTable.isCellPresent(1, 1)){
								mainTable.removeCell(1, 1);
								mainTable.removeCell(2, 1);
								buttonTable.setWidget(0, 2, cancelButton);
								
							}	
						}
						else{
							Object[][] data = new Object[result.size()][12];							
							for(int i = 0; i < result.size(); i++){								
								data[i][0] = result.get(i).getRegistrationNumber();
								data[i][1] = result.get(i).getFirstName();
								data[i][2]= result.get(i).getFatherFirstName();
								data[i][3]= result.get(i).getDob();		
								data[i][4]= result.get(i).getProgramId();
								data[i][5]= result.get(i).getProgramName();
								data[i][6]= result.get(i).getSessionStartDate();
								data[i][7]= result.get(i).getSessionEndDate();
								//add Insert Time property
								data[i][8]= result.get(i).getDdDate();								
								data[i][9]= result.get(i).getStudentId();
								//add Session Date
								if(result.get(i).getSessionStartDate()!=null && result.get(i).getSessionEndDate()!=null){
									data[i][10]= result.get(i).getSessionStartDate().substring(0,4)+"-"+result.get(i).getSessionEndDate().substring(2,4);
								}								
								else{
									data[i][10]=null;
								}
								data[i][11]=result.get(i).getEntityId();
							}						
							RecordDef RecordDef = new RecordDef(new FieldDef[] {
									new StringFieldDef("registrationNumber"),
									new StringFieldDef("studentName"),
									new StringFieldDef("fatherName"),
									new StringFieldDef("dob"),	
									new StringFieldDef("programId"),
									new StringFieldDef("programName"),
									new StringFieldDef("sessionStartDate"),
									new StringFieldDef("sessionEndDate"),
									new StringFieldDef("insertTime"),
									new StringFieldDef("studentId"),
									new StringFieldDef("sessionDate"),
									new StringFieldDef("entityId"),
							});
							MemoryProxy proxy = new MemoryProxy(data);
							ArrayReader reader = new ArrayReader(RecordDef);
							Store store = new Store(proxy, reader);
							store.load();
							grid.setStore(store);							
							final CheckboxSelectionModel cbSelectionModel=new CheckboxSelectionModel();
							BaseColumnConfig[] columns = new BaseColumnConfig[] {
									new CheckboxColumnConfig(cbSelectionModel),
									new ColumnConfig(constant.registrationNumber(),"registrationNumber", 120, true, null,"registrationNumber"),										
									new ColumnConfig(constant.name(), "studentName",100,true,null,"studentName"),
									new ColumnConfig(constant.fatherName(), "fatherName",100,true,null,"fatherName"),
									new ColumnConfig(constant.birthDay(), "dob",100,true,null,"dob"),
									new ColumnConfig(constant.programme(), "programName",100,true,null,"programName"),
									new ColumnConfig(constant.sessionDate(), "sessionDate",100,true,null,"sessionDate"),
									new ColumnConfig(constant.submissionTime(), "insertTime",120,true,null,"insertTime")
								};							
								ColumnModel columnModel = new ColumnModel(columns);
								grid.setColumnModel(columnModel);
								grid.setFrame(true);
								grid.setStripeRows(true);
								grid.setAutoExpandColumn("registrationNumber");
								grid.setAutoExpandColumn("studentName");
								grid.setAutoExpandColumn("fatherName");
								grid.setAutoExpandColumn("dob");
								grid.setAutoExpandColumn("programName");
								grid.setAutoExpandColumn("sessionDate");
								grid.setAutoExpandColumn("insertTime");								
								grid.setSelectionModel(cbSelectionModel);
								grid.setWidth(800);
								grid.setHeight(200);
								grid.setTitle(constant.selectSubject());
								Toolbar toolbar=new Toolbar();
								toolbar.addFill();
								grid.setTopToolbar(toolbar);
								if(action.equals("edit")){
									toolbar.addButton(editButton);
								}
								else if(action.equals("delete")){
									toolbar.addButton(deletebutton);
								}		
								final FieldSet gridFieldSet=new FieldSet(constant.selectStudent());
								gridFieldSet.add(grid);
								mainTable.setWidget(1, 1, gridFieldSet);
								buttonTable.remove(cancelButton);
								mainTable.setWidget(2, 1, cancelButton);								
								cbSelectionModel.addListener(new RowSelectionListenerAdapter() {
						   		  	public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
						   		  		if(action.equals("edit")){
						   		  			if(cbSelectionModel.getSelections().length>1){
						   		  				MessageBox.alert(message.info(), message.selectOne());
						   		  				cbSelectionModel.clearSelections();						   		  				
						   		  			}						   		  			
						   		  		}
									}									
									public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
										
									}
						   		   
						   	   });	
								
								deletebutton.addListener(new ButtonListenerAdapter(){
									public void onClick(Button button,EventObject e){
										if(cbSelectionModel.getSelections().length<1){
											MessageBox.alert(message.info(), message.selectStudent());
										}
										else{
											MessageBox.confirm(message.confirm(), message.alert_ondelete(), new ConfirmCallback(){
												@Override
												public void execute(String btnID) {
													if(btnID.equals(constant.yesButton())){
														Record record[]=cbSelectionModel.getSelections();
														List<SummarySheetBean>list=new ArrayList<SummarySheetBean>();
														for(int i=0;i<record.length;i++){
															SummarySheetBean been=new SummarySheetBean();
															been.setRegistrationNumber(record[i].getAsString("registrationNumber"));
															been.setStudentId(record[i].getAsString("studentId"));
															been.setSessionStartDate(record[i].getAsString("sessionStartDate"));
															been.setSessionEndDate(record[i].getAsString("sessionEndDate"));
															been.setProgramId(record[i].getAsString("programId"));//Add by Devendra June 5
															been.setEntityId(record[i].getAsString("entityId"));//Add by Devendra June 5
															been.setUserId(userId);
															list.add(been);
														}	
														summaryServiceAsync.deleteStudents(list, new AsyncCallback<String>(){
															@Override
															public void onFailure(
																	Throwable arg0) {
																MessageBox.alert(message.failure(), arg0.getMessage());																
															}
															@Override
															public void onSuccess(
																	String arg0) {
																String result[]=arg0.split("-");
																if(result[0].equals("success")){
																	MessageBox.alert(message.success(), message.deleteStudentRegSuccess(result[1],result[2],result[3]));
																}
																else{
																	MessageBox.alert(message.error(),message.deleteStudentRegFail());
																}	
																cbSelectionModel.clearSelections();
																mainTable.removeCell(1, 1);
																mainTable.removeCell(2, 1);
																buttonTable.setWidget(0, 2, cancelButton);
															}
															
														});
													}
												}
												
											});
										}
									}
								});
								
								editButton.addListener(new ButtonListenerAdapter(){
									public void onClick(Button button,EventObject e){
										if(cbSelectionModel.getSelections().length<1){
											MessageBox.alert(message.info(), message.selectStudent());
										}							
										else{
											Record record[]=cbSelectionModel.getSelections();
											SummarySheetBean been=new SummarySheetBean();
											been.setRegistrationNumber(record[0].getAsString("registrationNumber"));
											been.setStudentId(record[0].getAsString("studentId"));
											been.setSessionStartDate(record[0].getAsString("sessionStartDate"));
											been.setSessionEndDate(record[0].getAsString("sessionEndDate"));
											been.setUserId(userId);
											been.setUniversityId(userId.substring(1, 5));
											
											Button edtButton=new Button(constant.button_update());
											Button exitButton=new Button(constant.cancelButton());
											final com.gwtext.client.widgets.Window window=new com.gwtext.client.widgets.Window();
											window.setTitle(constant.edit()+" "+constant.summarySheet());
											window.setWidth(1200);
                                            window.setHeight(500);                                            
                                            window.setResizable(false);
                                            window.setButtonAlign(Position.CENTER);
                                            window.addButton(edtButton);
                                            window.addButton(exitButton);                                            
                                            window.setAutoScroll(true); 
                                            vStudentPanel.getBody().mask(message.waitMask());
                                            VerticalPanel Editpanel=createPage(edtButton,userId.substring(1, 5),record[0].getAsString("programId"),record[0].getAsString("programName")
                                            		,record[0].getAsString("entityId"),record[0].getAsString("registrationNumber"),record[0].getAsString("studentId"),record[0].getAsString("sessionStartDate"),record[0].getAsString("sessionEndDate"),window,cbSelectionModel);	                                                    
                                            window.add(Editpanel);                                   
                                            window.setCloseAction(com.gwtext.client.widgets.Window.CLOSE);
                                            window.setPlain(true);
                                            window.setFrame(true);
                                            window.setClosable(true);
                                            window.setModal(true);
                                            window.show(button.getId()); 
                                            vStudentPanel.getBody().unmask();
                                            exitButton.addListener(new ButtonListenerAdapter(){
                                            	public void onClick(Button button,EventObject e){
                                            		window.close();
                                            		cbSelectionModel.clearSelections();
                                            	}
                                            });
										}
									}
								});
																	
						}
												
					}
					
				});
			}
		});
		cancelButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button,EventObject e){
				vPanel.clear();
			}
		});
		upperFlexTable.setWidget(0, 1, registrationLabel);
		upperFlexTable.setWidget(0, 2, registrationNo);
		buttonTable.setWidget(0, 1, okButton);
		buttonTable.setWidget(0, 2, cancelButton);
		buttonTable.setCellSpacing(5);
		upperFlexTable.setWidget(1, 1, buttonTable);
		upperFlexTable.setCellSpacing(10);
		searchByReg.add(upperFlexTable);
		mainTable.setWidget(0, 1, searchByReg);
		mainTable.setCellSpacing(10);
		vStudentPanel.add(mainTable);	
		vPanel.add(vStudentPanel);
	}	
	public VerticalPanel createPage(final Button editButton,final String universityId,final String programId,final String programName,
			final String entityId,final String registrationNumber,final String studentId,final String sessionStartDate,final String sessionEndDate,final com.gwtext.client.widgets.Window win,final CheckboxSelectionModel cbSelectionModel){				
		final VerticalPanel vpPanel = new VerticalPanel();	
		final SummarySheetBean inputDataBeen=new SummarySheetBean();
        inputDataBeen.setProgramId(programId);
        inputDataBeen.setProgramName(programName);
        inputDataBeen.setUniversityId(universityId);
        inputDataBeen.setRegistrationNumber(registrationNumber);
        inputDataBeen.setStudentId(studentId);
        inputDataBeen.setEntityId(entityId);
        inputDataBeen.setSessionStartDate(sessionStartDate);
        inputDataBeen.setSessionEndDate(sessionEndDate);
        inputDataBeen.setUserId(userId);
        
        summaryServiceAsync.getStudentDataForEdit(inputDataBeen, new AsyncCallback<List<SummarySheetBean>>(){
		@Override
		public void onFailure(Throwable arg0) {
			MessageBox.alert(message.error(), arg0.getMessage());
		}
		@Override
		public void onSuccess(final List<SummarySheetBean> result) {		
		     final DateField dob = new DateField(constant.dob(),"dob",188);	         
		     final Store banksStore = new SimpleStore(new String[] { "value", "" }, getBanks());	
		        OA_ComboBoxes comboData = new OA_ComboBoxes();
		        comboData.onModuleLoad();		        
		        final List<AcademicDetailsBean> academicDetailList = new ArrayList<AcademicDetailsBean>();
		        final List<AcademicDetailsBean> qualificationDetailList = new ArrayList<AcademicDetailsBean>();
		        final List<PaperGroupBean> entranceDetailList = new ArrayList<PaperGroupBean>();
		        final List<CenterCodeDetailsBean> examCenterDetailList = new ArrayList<CenterCodeDetailsBean>();
		        final List<DocumentUpload> uploadDocList = new ArrayList<DocumentUpload>();
		        final List<CosCodeDetailsBean> subjectList = new ArrayList<CosCodeDetailsBean>();
		        
		        final FieldSet personalInfo = new FieldSet();
		        final FieldSet addressInfo = new FieldSet();
		        final FieldSet academicInfo = new FieldSet();
		        final FieldSet staffInfo = new FieldSet();
		        final FieldSet subjectCodeInfo = new FieldSet();
		        final FieldSet examCenterInfo = new FieldSet();
		        final FieldSet entranceInfo = new FieldSet();
		        final FieldSet qualificationInfo = new FieldSet();
		        final FieldSet feeInfo = new FieldSet();
		        final FieldSet documentInfo = new FieldSet();
		        
		        final FlexTable personalTable = new FlexTable();
		        final FlexTable staffTable = new FlexTable();
		        final FlexTable academicTable = new FlexTable();
		        final FlexTable subjectCodeTable=new FlexTable();
		        final FlexTable examCenterTable = new FlexTable();
		        final FlexTable entranceTable = new FlexTable();
		        final FlexTable qualificationTable = new FlexTable();
		        final FlexTable feeTable = new FlexTable();
		        final DocumentUpload imgUpload = new DocumentUpload("", "0");
		        imgUpload.setDocId("1111");
		             		        		        
		        final TextField firstName = new TextField();
		        final TextField middleName = new TextField();
		        final TextField lastName = new TextField();
		        final TextField fatherFirstName = new TextField();
		        final TextField fatherMiddleName = new TextField();
		        final TextField fatherLastName = new TextField();
		        final TextField motherFirstName = new TextField();
		        final TextField motherMiddleName = new TextField();
		        final TextField motherLastName = new TextField();
		        final TextField primaryMail = new TextField();
		        final TextField secondaryMail = new TextField();
		        final ComboBox category = new ComboBox();
		        final ComboBox minorityCB = new ComboBox();
		        final ComboBox maritalCB = new ComboBox();
		        final ComboBox gender = comboData.genderCB;
		        final TextField nationality=new TextField();
		        final TextField religion=new TextField();
		        final TextField guardian=new TextField();
		        final NumberField homePhone = new NumberField();
		        final NumberField otherPhone = new NumberField();
		        final TextField ddNo = new TextField();
		        final TextField otherBank = new TextField();
		        final NumberField amount = new NumberField();
		        final ComboBox bankName = new ComboBox();
		        final DateField ddDate = new DateField();
		        final AddressField permanentAddress = new AddressField(constant.addCorrespondence());
		        
		        		       
		        if(result.get(0).getAddressLine1()!=null && !(result.get(0).getAddressLine1().equals(""))){
		        	permanentAddress.line1.setValue(result.get(0).getAddressLine1());
		        }	
		        if(result.get(0).getAddressLine2()!=null && !(result.get(0).getAddressLine2().equals(""))){
		        	 permanentAddress.line2.setValue(result.get(0).getAddressLine2());
		        }
		        if(result.get(0).getState()!=null && !(result.get(0).getState().equals(""))){
		        	 permanentAddress.state.setValue(result.get(0).getState());
                }	
		        if(result.get(0).getCity()!=null && !(result.get(0).getCity().equals(""))){
		        	 permanentAddress.city.setValue(result.get(0).getCity());
		        }
		        if(result.get(0).getPincode()!=null && !(result.get(0).getPincode().equals(""))){
		        	 permanentAddress.pincode.setValue(result.get(0).getPincode());		
		        }		               
		        if(result.get(0).getDdNo()!=null && !(result.get(0).getDdNo().equals(""))){
		        	ddNo.setValue(result.get(0).getDdNo());
                }
		        if(result.get(0).getDdAmount()!=null && !(result.get(0).getDdAmount().equals(""))){
		        	amount.setValue(result.get(0).getDdAmount());
                }
		        if(result.get(0).getDdDate()!=null && !(result.get(0).getDdDate().equals(""))){
		        	ddDate.setValue(result.get(0).getDdDate());
                }
		        		        		       
		        personalInfo.setTitle(constant.personal_details());
		        addressInfo.setTitle(constant.addCorrespondence());
		        academicInfo.setTitle(constant.acad_perform());
		        staffInfo.setTitle(constant.if_staffWard());
		        subjectCodeInfo.setTitle(constant.subjectCode());
		        entranceInfo.setTitle(constant.test_options());
		        examCenterInfo.setTitle(constant.examCenter());
		        qualificationInfo.setTitle(constant.qualification());
		        feeInfo.setTitle(constant.feeDetails());
		        documentInfo.setTitle(constant.docDetails());	        		        
	
		        categoryServiceAsync.Category(userId,
		            new AsyncCallback<CM_StudentInfoGetter[]>() {
		                @Override
		                public void onFailure(Throwable t) {
		                    MessageBox.alert(message.error(), t.getMessage());
		                }
		                @Override
		                public void onSuccess(CM_StudentInfoGetter[] categoryList) {
		                    Object[][] categoryObj = new Object[categoryList.length][2];
		                    Object[][] minorityObj = new Object[2][2];
		                    Object[][] maritalObj = new Object[2][2];
		                    minorityObj[0][0]="Y";
		                    minorityObj[0][1]="Yes";
		                    minorityObj[1][0]="N";
		                    minorityObj[1][1]="No";
		                    
		                    maritalObj[0][0]="M";
		                    maritalObj[0][1]="Married";
		                    maritalObj[1][0]="UM";
		                    maritalObj[1][1]="Unmarried";
		                    
		                    for (int c = 0; c < categoryList.length; c++) {
		                        categoryObj[c][0] = categoryList[c].getCategory_code();
		                        categoryObj[c][1] = categoryList[c].getCategory();
		                    }
	
		                    final Store categoryStore = new SimpleStore(new String[] {
		                                "catCode", "category"
		                            }, categoryObj);
		                    final Store minorityStore = new SimpleStore(new String[] {
	                                "minorityCode", "minorityDesc"
	                            }, minorityObj);
		                    final Store maritalStore = new SimpleStore(new String[] {
	                                "maritaCode", "maritalDesc"
	                            }, maritalObj);
	
		                    loadCombo(category, categoryStore, "catCode", "category",constant.category());
		                    loadCombo(minorityCB, minorityStore, "minorityCode", "minorityDesc",constant.selectMinority());
		                    loadCombo(maritalCB, maritalStore, "maritaCode", "maritalDesc",constant.selectMarital());
		                    category.setWidth(188);
		                    minorityCB.setWidth(188);
		                    maritalCB.setWidth(188);
	
		                    personalTable.setWidget(2, 0, new Label(constant.selectPhoto()));
		                    personalTable.setWidget(3, 1, new Label(constant.firstName()));
		                    personalTable.setWidget(3, 2, new Label(constant.middleName()));
		                    personalTable.setWidget(3, 3, new Label(constant.lastName()));
		                    personalTable.setWidget(4, 0, new Label(constant.stduentName()));
		                    personalTable.setWidget(5, 0, new Label(constant.fatherName()));
		                    personalTable.setWidget(6, 0, new Label(constant.motherName()));
		                    personalTable.setWidget(10, 0, new Label(constant.dob()));
		                    personalTable.setWidget(10, 2, new Label(constant.category()));
		                    personalTable.setWidget(10, 4, new Label(constant.nationality()+constant.asterisk()));
		                    personalTable.setWidget(11, 0, new Label(constant.gender()));
		                    personalTable.setWidget(11, 2, new Label(constant.religion()+constant.asterisk()));
		                    personalTable.setWidget(11, 4, new Label(constant.guardian()));
		                    personalTable.setWidget(12, 0, new Label(constant.primaryMail()+constant.asterisk()));
		                    personalTable.setWidget(12, 2,new Label(constant.secondaryMail()));
		                    personalTable.setWidget(13, 0,new Label(constant.labelPhone() + constant.asterisk()));
		                    personalTable.setWidget(13, 2,new Label(constant.labelOtherPhone()));
		                    personalTable.setWidget(12, 4,new Label(constant.minority()));
		                    personalTable.setWidget(13, 4,new Label(constant.marital()));
		                    
		                    firstName.setValue(result.get(0).getFirstName());
		                    middleName.setValue(result.get(0).getMiddleName());
		                    lastName.setValue(result.get(0).getLastName());
		                    fatherFirstName.setValue(result.get(0).getFatherFirstName());
		                    fatherMiddleName.setValue(result.get(0).getFatherMiddleName());
		                    fatherLastName.setValue(result.get(0).getFatherLastName());
		                    motherFirstName.setValue(result.get(0).getMotherFirstName());
		                    motherMiddleName.setValue(result.get(0).getMotherMiddleName());
		                    motherLastName.setValue(result.get(0).getMotherLastName());
		                    if(result.get(0).getDob()!=null && !(result.get(0).getDob().equals(""))){
		                    	dob.setValue(result.get(0).getDob());
		                    }		                    
		                    category.setValue(result.get(0).getCategory());
		                    nationality.setValue(result.get(0).getNationality());
		                    gender.setValue(result.get(0).getGender().equalsIgnoreCase("M")?"Male":"Female");
		                    religion.setValue(result.get(0).getReligion());
		                    guardian.setValue(result.get(0).getGuardian());
		                    primaryMail.setValue(result.get(0).getPrimaryEmail());
		                    secondaryMail.setValue(result.get(0).getSecondaryEmail());
		                    homePhone.setValue(result.get(0).getPhoneNumber());
		                    otherPhone.setValue(result.get(0).getOtherPhone());		                    
		                    if(result.get(0).getMinority()!=null && !(result.get(0).getMinority().equals(""))){
		                    	minorityCB.setValue(result.get(0).getMinority());
		                    }
		                    if(result.get(0).getMaritalStatus()!=null && !(result.get(0).getMaritalStatus().equals(""))){
		                    	maritalCB.setValue(result.get(0).getMaritalStatus());
		                    }
		                    homePhone.setDecimalPrecision(0);
		                    otherPhone.setDecimalPrecision(0);	
		                    
		                    firstName.setDisabled(true);
		                    middleName.setDisabled(true);
		                    lastName.setDisabled(true);
		                    fatherFirstName.setDisabled(true);
		                    fatherMiddleName.setDisabled(true);
		                    fatherLastName.setDisabled(true);
		                    motherFirstName.setDisabled(true);
		                    motherMiddleName.setDisabled(true);
		                    motherLastName.setDisabled(true);
		                    dob.setDisabled(true);
		                    category.setDisabled(true);
		                    nationality.setDisabled(true);
		                    gender.setDisabled(true);
		                    religion.setDisabled(true);		                   
		                    primaryMail.setDisabled(true);		                                
		                    
		                    personalTable.setWidget(2, 1, imgUpload);
		                    personalTable.setWidget(4, 1, firstName);
		                    personalTable.setWidget(4, 2, middleName);
		                    personalTable.setWidget(4, 3, lastName);
		                    personalTable.setWidget(5, 1, fatherFirstName);
		                    personalTable.setWidget(5, 2, fatherMiddleName);
		                    personalTable.setWidget(5, 3, fatherLastName);
		                    personalTable.setWidget(6, 1, motherFirstName);
		                    personalTable.setWidget(6, 2, motherMiddleName);
		                    personalTable.setWidget(6, 3, motherLastName);
		                    personalTable.setWidget(10, 1, dob);
		                    personalTable.setWidget(10, 3, category);
		                    personalTable.setWidget(10, 5, nationality);
		                    personalTable.setWidget(11, 1, gender);
		                    personalTable.setWidget(11, 3, religion);
		                    personalTable.setWidget(11, 5, guardian);
		                    personalTable.setWidget(12, 1, primaryMail);
		                    personalTable.setWidget(12, 3, secondaryMail);
		                    personalTable.setWidget(13, 1, homePhone);
		                    personalTable.setWidget(13, 3, otherPhone);
		                    personalTable.setWidget(12, 5, minorityCB);
		                    personalTable.setWidget(13, 5, maritalCB);
		                }
		            });
	
		        final List<String> programList=new ArrayList<String>();
		        final List<String> programNameList=new ArrayList<String>();
		        final List<String> entityIdList=new ArrayList<String>();
		        programList.add(programId);//Update By Devendra for Single Program
		        entityIdList.add(entityId);//Update By Devendra for Single Program
		        programNameList.add(programName);//Update By Devendra for Single Program
		        
		        inputDataBeen.setEntityIdList(entityIdList);
		        inputDataBeen.setProgramList(programList);
		        inputDataBeen.setProgramNameList(programNameList);
		        
		        academicTable.setWidget(0, 0, new HTML("<b>" + constant.component() + "</b>"));
		        academicTable.setWidget(0, 5, new HTML("<b>" + constant.board() + "</b>"));
		        academicTable.setWidget(0, 6, new HTML("<b>" + ""));		        		        
		        summaryServiceAsync.getCosCodeDescription(inputDataBeen,new AsyncCallback<List<SummarySheetBean>>() {
					public void onFailure(Throwable arg0) {
						MessageBox.alert(arg0.toString());
						
					}
					public void onSuccess(List<SummarySheetBean> cosCodeList) {
		                for (int i = 0;i < programList.size(); i++) {
		                	String thisProgram = programList.get(i);
		                    List<String> tempCos = new ArrayList<String>();
		                    
		                    for (int j = 0;j < cosCodeList.size();j++) {
		                        
		                        if (cosCodeList.get(j).getProgramId().equals(thisProgram)) {                        	
		                            tempCos.add(cosCodeList.get(j).getCosCode());
		                            tempCos.add(cosCodeList.get(j).getCosDescription());
		                        }
		                    }
		                    
		                    if(tempCos.size()==0){
		                    	tempCos.add("X");
		                        tempCos.add("None");
		                    }
		                   
		                   if (tempCos.size() > 0) {
		                        CosCodeDetailsBean cosCodeComp = new CosCodeDetailsBean();
		                        cosCodeComp.setProgramId(thisProgram);
		                        
		                        String[][] data = new String[tempCos.size() / 2][2];
		                        
		                        for (int t = 0;t < (tempCos.size() -1); t = t + 2) {
		                            data[t / 2][0] = tempCos.get(t);
		                            data[t / 2][1] = tempCos.get(t +1);
		                        }
	
		                        Store store = new SimpleStore(new String[] {
		                                    "cosCode",
		                                    "cosDescription"
		                                }, data);
		                        ComboBox cosCombo = new ComboBox();
		                        if(result.get(0).getComponentId().equalsIgnoreCase("x")){
		                        	cosCombo.setValue(result.get(0).getComponentDescription());
		                        }
		                        else{
		                        	cosCombo.setValue(result.get(0).getComponentId());
		                        }		                       
		                        loadCombo(cosCombo,store, "cosCode","cosDescription", "");
		                        cosCodeComp.setCosComboBox(cosCombo);
		                        subjectList.add(cosCodeComp);
		                        subjectCodeTable.setWidget(i,0,new Label(constant.labelfor()+" " +programNameList.get(i)));
		                        subjectCodeTable.setWidget(i,1, cosCombo);
		                    }
		                }
					
		        summaryServiceAsync.getProgramComponent(inputDataBeen,
		            new AsyncCallback<List<SummarySheetBean>>() {
		                public void onFailure(Throwable t) {
		                    MessageBox.alert(message.error(), "in failure" +t.getMessage());
		                }
		                
		                public void onSuccess(final List<SummarySheetBean> outputList) {
		                	final CheckBox applicable=new CheckBox(constant.applicable());
		                	staffTable.setWidget(0, 0, applicable);		                	
		                	summaryServiceAsync.getAcademicDetailForEdit(inputDataBeen, new AsyncCallback<List<SummarySheetBean>>(){
								@Override
								public void onFailure(Throwable arg0) {
									 MessageBox.alert(message.error(), arg0.getMessage());
								}

								@Override
								public void onSuccess(
										final List<SummarySheetBean> academicList) {								
									summaryServiceAsync.checkSpecialWeightage(inputDataBeen, new AsyncCallback<List<SummarySheetBean>>(){
										@Override
										public void onFailure(
												Throwable arg0) {
											 MessageBox.alert(message.error(), arg0.getMessage());
										}
										@Override
										public void onSuccess(
												List<SummarySheetBean> chekSpclWeightageList) {											
											for(int i=0;i<result.size();i++){
												SummarySheetBean been=new SummarySheetBean();
												been.setComponentId(result.get(i).getComponentId());												
												chekSpclWeightageList.add(been);
											}											
											   for (int i = 0; i < outputList.size(); i++) {				                	
								                    academicTable.setWidget(i + 1, 0,new Label(outputList.get(i).getComponentDescription()));
								                    academicTable.setWidget(i + 1, 1,new Label((outputList.get(i).getComponentType().equalsIgnoreCase("M")? constant.labelMarks() : constant.labelPercentage())));								
								                    final AcademicDetailsBean inputComponents = new AcademicDetailsBean();
								                    
								                    inputComponents.setComponentId(outputList.get(i).getComponentId());
								                    inputComponents.setComponentType(outputList.get(i).getComponentType());
								                    inputComponents.setComponentDescription(outputList.get(i).getComponentDescription());
								                    inputComponents.setComponentWeightage(outputList.get(i).getComponentWeightage());
								                    inputComponents.setWeightageFlag(outputList.get(i).getWeightageFlag());
								                    inputComponents.setBoardFlag(outputList.get(i).getBoardFlag());
								                    inputComponents.setSpecialWeightageFlag(outputList.get(i).getSpecialWeightageFlag());
								                    inputComponents.setStaffWeightage(applicable);
								                    
								                    if (outputList.get(i).getComponentType()
								                                      .equalsIgnoreCase("M")) {
								                        NumberField marks = new NumberField();
								                        NumberField total = new NumberField();								                       
								                        for(int kk=0;kk<academicList.size();kk++){								                        	
								                        	if(academicList.get(kk).getComponentId().equals(outputList.get(i).getComponentId())){								                        		
								                        		 marks.setValue(academicList.get(kk).getMarksObtained());
											                     total.setValue(academicList.get(kk).getTotalMarks());
								                        	}
								                        }					                       
								                        
								                        marks.setAllowBlank(false);
								                        marks.setValidateOnBlur(true);
								                        marks.setMinValue(1);
								                        
								                        total.setAllowBlank(false);
								                        total.setValidateOnBlur(true);
								                        marks.setMinValue(1);
								                        
								                        inputComponents.setMarksObtained(marks);
								                        inputComponents.setTotalMarks(total);
								
								                        academicTable.setWidget(i + 1, 2, marks);
								                        academicTable.setWidget(i + 1, 3,
								                            new Label(constant.labelOutof()));
								                        academicTable.setWidget(i + 1, 4, total);
								                    } else {
								                        NumberField percentageScore = new NumberField();								                       
								                        for(int kk=0;kk<academicList.size();kk++){								                        	
								                        	if(academicList.get(kk).getComponentId().equals(outputList.get(i).getComponentId())){
								                        		 percentageScore.setValue(academicList.get(kk).getPercentage());								                       
								                        	}
								                        }
								                       
								                        percentageScore.setMaxValue(100);
								                        percentageScore.setAllowBlank(false);
								                        percentageScore.setValidateOnBlur(true);
								                        inputComponents.setPercentageScore(percentageScore);
								                        academicTable.setWidget(i + 1, 2, percentageScore);
								                    }
								
								                    if (outputList.get(i).getBoardFlag()
								                                      .equalsIgnoreCase("Y")) {	
								                    	final SummarySheetBean ben=new SummarySheetBean();
								                    	ben.setComponentId(outputList.get(i).getComponentId());
								                    	ben.setCity(String.valueOf(i));
								                    	connectTemp.getBoard(new AsyncCallback<CM_StudentInfoGetter[]>(){
															@Override
															public void onFailure(
																	Throwable arg0) {
																MessageBox.alert(message.error(), arg0.getMessage());
															}

															@Override
															public void onSuccess(
																	CM_StudentInfoGetter[] arg0) {
																String[][] data=new String[arg0.length][2];
																for(int i=0;i<arg0.length;i++){
																	data[i][0]=arg0[i].getBoard_id();
																	data[i][1]=arg0[i].getBoard_name();
																}
																Store  store = new SimpleStore(new String[] {
										                                    "BoardCode",
										                                    "BoardName"
										                                }, data);
																	ComboBox cb=new ComboBox();	
																	 cb.setWidth(150);
																  for(int kk=0;kk<academicList.size();kk++){
											                        	if(academicList.get(kk).getComponentId().equals(ben.getComponentId())){
											                        		cb.setValue(academicList.get(kk).getBoard());								                       
											                        	}
											                        }
											                        loadCombo(cb,store, "BoardCode","BoardName", "");
											                        inputComponents.setBoard(cb);											                        
											                        academicTable.setWidget(Integer.parseInt(ben.getCity()) + 1, 5, cb);															              														 
															}
															
								                    		
								                    	});						                       								                    	

								                    } else {
								                        Label lbl = new Label(" ");
								                        lbl.setWidth("150");
								                        academicTable.setWidget(i + 1, 5, lbl);
								                    }
								
								                    if (outputList.get(i).getSpecialWeightageFlag()
								                                      .equalsIgnoreCase("Y")) {
								                    	 
								                    	CheckBox weightage = new CheckBox(constant.existingStudent()); //
								                    	for(int kk=0;kk<chekSpclWeightageList.size();kk++){
								                        	if(chekSpclWeightageList.get(kk).getComponentId().equals(outputList.get(i).getComponentId())){
								                        		weightage.setChecked(true);							                       
								                        	}
								                    	}								                       
								                        inputComponents.setWeightage(weightage);
								                        academicTable.setWidget(i + 1, 6, weightage);
								                    }								
								                    academicDetailList.add(inputComponents);
							               } 
										}
			                    		
			                    	});
								}
		                		
		                	});	                    			                	 		
		                	    
		            	   summaryServiceAsync.getExaminationCenter(inputDataBeen,new AsyncCallback<List<SummarySheetBean>>() {
	
							public void onFailure(Throwable arg0) {							
								MessageBox.alert(message.error(), arg0.getMessage());
							}
	
							public void onSuccess(List<SummarySheetBean> centerValue) {								
								for (int i = 0;i < programList.size(); i++) {
				                	String thisProgram = programList.get(i);
				                    List<String> tempCenter = new ArrayList<String>();				                    
				                    for (int j = 0;j < centerValue.size();j++) {				                        
				                        if (centerValue.get(j).getProgramId().equals(thisProgram)) {                        	
				                            tempCenter.add(centerValue.get(j).getCenterCode());
				                            tempCenter.add(centerValue.get(j).getCenterDescription());
				                        }
				                    }				                    
				                   if (tempCenter.size() > 0) {
				                	   CenterCodeDetailsBean centerCodeComp = new CenterCodeDetailsBean();
				                        centerCodeComp.setProgramId(thisProgram);
				                        
				                        String[][] data = new String[tempCenter.size() / 2][2];
				                        
				                        for (int t = 0;t < (tempCenter.size() -1); t = t + 2) {
				                            data[t / 2][0] = tempCenter.get(t);
				                            data[t / 2][1] = tempCenter.get(t +1);
				                        }
	
				                        Store store = new SimpleStore(new String[] {
				                                    "centerCode",
				                                    "centerDescription"
				                                }, data);
				                        ComboBox centerCombo = new ComboBox();
				                        centerCombo.setValue(result.get(0).getCenterCode());
				                        loadCombo(centerCombo,store, "centerCode","centerDescription", "");
				                        centerCodeComp.setCenterComboBox(centerCombo);
				                        examCenterDetailList.add(centerCodeComp);
				                        examCenterTable.setWidget(i,0,new Label(constant.labelfor()+" " +programNameList.get(i)));
				                        examCenterTable.setWidget(i,1, centerCombo);
				                    }
				                }
	
							   summaryServiceAsync.getGroupWisePaperCode(inputDataBeen,
		                        new AsyncCallback<List<SummarySheetBean>>() {
		                            @Override
		                            public void onFailure(Throwable t) {
		                                MessageBox.alert(message.error(), t.getMessage());
		                            }
	
		                            @Override
		                            public void onSuccess(final List<SummarySheetBean> paperCodes) {		                               
		                                summaryServiceAsync.getEnteranceTestData(inputDataBeen, new AsyncCallback<List<SummarySheetBean>>(){

											@Override
											public void onFailure(Throwable arg0) {
												MessageBox.alert(message.error(), arg0.getMessage());
											}

											@Override
											public void onSuccess(
													List<SummarySheetBean> enteranceResult) {
												 int gs = 1;
												    for (int pl = 0; pl < programList.size();pl++) {
					                                    String program = programList.get(pl);
					                                    String programName=programNameList.get(pl);
					                                    
					                                    List<String> groupList = new ArrayList<String>();
				
					                                    for (int p = 0; p < paperCodes.size();p++) {
					                                        if (paperCodes.get(p).getProgramId().equalsIgnoreCase(program) &&(groupList.indexOf(paperCodes.get(p).getGrouping()) < 0)) {
					                                    
					                                            groupList.add(paperCodes.get(p).getGrouping());
					                                            
					                                        }
					                                    }
				
					                                    entranceTable.setWidget(0, 0, new HTML("<b>" + constant.labelForProgram() + "</b>"));
					                                    
					                                    entranceTable.setWidget(0, 1,new HTML("<b>" + constant.group() + "</b>"));
					                                    
					                                    entranceTable.setWidget(gs, 0,new Label( programName+ "  "));
					                                    
					                                    for (int g = 0; g < groupList.size();g++) {
					                                        PaperGroupBean paperGroupBean = new PaperGroupBean();		                                        		                                       
					                                        entranceTable.setWidget(gs, 1,new Label(constant.group()+" " +groupList.get(g) + "*"));
				
					                                        paperGroupBean.setGrouping(groupList.get(g));
					                                        paperGroupBean.setProgramId(program);
					                                        
					                                        List<String> tempData = new ArrayList<String>();
				
					                                        for (int p = 0; p < paperCodes.size();p++) {
					                                            if (paperCodes.get(p).getProgramId().equalsIgnoreCase(program) &&groupList.get(g).equalsIgnoreCase(paperCodes.get(p).getGrouping())) {
					                                                tempData.add(paperCodes.get(p).getPaperCode());
					                                                tempData.add(paperCodes.get(p).getPaperDescription());
					                                            }
					                                        }
				
					                                        String[][] data = new String[tempData.size() / 2][2];
				
					                                        for (int t = 0;t < (tempData.size() - 1);t = t + 2) {
					                                            data[t / 2][0] = tempData.get(t);
					                                            data[t / 2][1] = tempData.get(t +1);
					                                        }
				
					                                        Store store = new SimpleStore(new String[] {
					                                                    "paperCode",
					                                                    "paperDescription"
					                                                }, data);					                                        
					                                        ComboBox paperCombo = new ComboBox();
					                                        for(int kk=0;kk<enteranceResult.size();kk++){
					                                        	if(enteranceResult.get(kk).getGrouping().equals(groupList.get(g))){					                                        		
					                                        		paperCombo.setValue(enteranceResult.get(kk).getPaperCode());
					                                        	}
					                                        }
					                                        loadCombo(paperCombo, store,"paperCode", "paperDescription", "");
					                                        entranceTable.setWidget(gs++, 2,paperCombo);
					                                        paperGroupBean.setPaperCombo(paperCombo);
					                                        entranceDetailList.add(paperGroupBean);
					                                    }
					                                }
											}
		                                	
		                                });
		                                
		                            
	
		                                summaryServiceAsync.getProgramFirstDegreeList(inputDataBeen,
		                                    new AsyncCallback<List<SummarySheetBean>>() {
		                                        @Override
		                                        public void onFailure(Throwable t) {
		                                            MessageBox.alert(message.error(),
		                                                t.getMessage());
		                                        }
		                                        @Override
		                                        public void onSuccess(List<SummarySheetBean> firstDegreeList) {
		                                            for (int i = 0;i < programList.size(); i++) {
		                                                
		                                            	String thisProgram = programList.get(i);
		                                                
		                                                List<String> tempDegree = new ArrayList<String>();
		                                                
		                                                for (int j = 0;j < firstDegreeList.size();j++) {
		                                                    
		                                                    if (firstDegreeList.get(j).getProgramId().equals(thisProgram)) {
		                                                    	
		                                                        tempDegree.add(firstDegreeList.get(j).getComponentId());
		                                                        tempDegree.add(firstDegreeList.get(j).getComponentDescription());
		                                                    }
		                                                }
		                                                
		                                                if (tempDegree.size() > 0) {
		                                                    AcademicDetailsBean firstDegreeComp = new AcademicDetailsBean();
		                                                    firstDegreeComp.setComponentId(thisProgram);
		                                                    firstDegreeComp.setProgramName(programNameList.get(i));
		                                                    
		                                                    String[][] data = new String[tempDegree.size() / 2][2];
		                                                    
		                                                    for (int t = 0;t < (tempDegree.size() -1); t = t + 2) {
		                                                        data[t / 2][0] = tempDegree.get(t);
		                                                        data[t / 2][1] = tempDegree.get(t +1);
		                                                    }
	
		                                                    Store store = new SimpleStore(new String[] {
		                                                                "degreeCode",
		                                                                "degreeName"
		                                                            }, data);
		                                                    ComboBox degreeCombo = new ComboBox();
		                                                    if(result.get(0).getDocId()!=null && !(result.get(0).getDocId().equals(""))){
		                                                    	degreeCombo.setValue(result.get(0).getDocId());
		                        		                    }
		                                                    loadCombo(degreeCombo,store, "degreeCode","degreeName", "");
		                                                    firstDegreeComp.setBoard(degreeCombo);
		                                                    qualificationDetailList.add(firstDegreeComp);
		                                                    qualificationTable.setWidget(i,0,new Label(constant.labelfor()+" " +programNameList.get(i)));
		                                                    qualificationTable.setWidget(i,1, degreeCombo);
		                                                }
		                                            }
	
		                                            ddNo.setAllowBlank(false);
		                                            ddNo.setValidateOnBlur(true);
		                                            ddNo.setMaxLength(15);
		                                            ddNo.setWidth(150);
	
		                                            amount.setAllowBlank(false);
		                                            amount.setValidateOnBlur(true);
		                                            amount.setMaxLength(10);
		                                            amount.setWidth(100);
	
		                                            loadCombo(bankName, banksStore,"value", "value",constant.bankName());
	
		                                            bankName.addListener(new ComboBoxListenerAdapter() {
		                                                    public void onSelect(ComboBox comboBox,Record record, int index) {
		                                                    	
		                                                        if (comboBox.getValue().equalsIgnoreCase("other")) {
		                                                            otherBank.enable();
		                                                            otherBank.setAllowBlank(false);
		                                                            feeTable.setWidget(1, 9,new Label(constant.labelIfOther()));
		                                                            feeTable.setWidget(1, 10, otherBank);
		                                                            
		                                                        } else {
		                                                            otherBank.setAllowBlank(true);
		                                                            otherBank.setValue("");
		                                                            otherBank.disable();
		                                                            feeTable.setWidget(1, 9,new Label(""));
		                                                            feeTable.setWidget(1, 10, new Label(""));
		                                                        }
		                                                    }
		                                                });
	
		                                            otherBank.disable();
		                                            otherBank.setMaxLength(100);
		                                            otherBank.setWidth(250);
		                                            ddDate.setAllowBlank(false);
		                                            ddDate.setValidateOnBlur(true);
		                                            ddDate.setWidth(100);
		                                      
		                                            feeTable.setWidget(0, 0,new Label(constant.ddNumber()+constant.asterisk()));
		                                            feeTable.setWidget(0, 2,new Label("     "));
		                                            feeTable.setWidget(0, 3,new Label(constant.ddDate()+constant.asterisk()));
		                                            feeTable.setWidget(0, 5,new Label("     "));
		                                            feeTable.setWidget(0, 6,new Label(constant.amount()+constant.asterisk()));
		                                            feeTable.setWidget(0, 8,new Label("     "));
		                                            feeTable.setWidget(0, 9,new Label(constant.bankName()+constant.asterisk()));
		                                            
		                                            String[][]bank=(String[][]) getBanks();
		                            		        boolean flag=false;
		                            		        for(int i=0;i<bank.length;i++){
		                            		        	if(bank[i][0].equalsIgnoreCase(result.get(0).getBankName())){		                            		        		
		                            		        		flag=true;
		                            		        		break;
		                            		        	}		        	
		                            		        }
		                            		        if(result.get(0).getBankName()!=null){
	                            		        	   if(flag){
			                            		        	bankName.setValue(result.get(0).getBankName());
			                            		        	otherBank.setAllowBlank(true);
			                                                otherBank.setValue("");
			                                                otherBank.disable();
			                                                feeTable.setWidget(1, 9,new Label(""));
			                                                feeTable.setWidget(1, 10, new Label(""));		        	
			                            		        }
			                            		        else{		                            		        
			                            		        	otherBank.enable();
			                            	                otherBank.setAllowBlank(false);
			                            	                feeTable.setWidget(1, 9,new Label(constant.labelIfOther()));
			                            	                feeTable.setWidget(1, 10, otherBank);
			                            		        	bankName.setValue("Other");
			                            		        	otherBank.setValue(result.get(0).getBankName());
			                            		        }
		                            		        }
		                            		     
		                                            feeTable.setWidget(0, 1, ddNo);
		                                            feeTable.setWidget(0, 4, ddDate);
		                                            feeTable.setWidget(0, 7, amount);
		                                            feeTable.setWidget(0, 10, bankName);
			                                            
		                                            summaryServiceAsync.getProgramDocumentList(inputDataBeen,
		                                                new AsyncCallback<List<SummarySheetBean>>() {
		                                                    @Override
		                                                    public void onFailure(Throwable t) {
		                                                        MessageBox.alert(message.error(),t.getMessage());
		                                                    }
	
		                                                    @Override
		                                                    public void onSuccess(List<SummarySheetBean> docList) {		                                                    	
		                                                        for (int k = 0;k < docList.size();k++) {
		                                                            DocumentUpload docProof =new DocumentUpload(docList.get(k).getDocName(),"200");
		                                                            docProof.setDocId(docList.get(k).getDocId());
		                                                            uploadDocList.add(docProof);
		                                                            documentInfo.add(docProof);
		                                                        }
		                                                        personalInfo.add(personalTable);
		                                                        addressInfo.add(permanentAddress);
		                                                        academicInfo.add(academicTable);
		                                                        staffInfo.add(staffTable);
		                                                        subjectCodeInfo.add(subjectCodeTable);
		                                                        examCenterInfo.add(examCenterTable);
		                                                        entranceInfo.add(entranceTable);
		                                                        
		                                                        qualificationInfo.add(qualificationTable);
		                                                        feeInfo.add(feeTable);
	
		                                                        vpPanel.add(subjectCodeInfo);
		                                                        vpPanel.add(personalInfo);
		                                                        vpPanel.add(addressInfo);
		                                                        vpPanel.add(staffInfo);
		                                                        vpPanel.add(academicInfo);
		                                                        vpPanel.add(qualificationInfo);
		                                                        vpPanel.add(examCenterInfo);
		                                                        vpPanel.add(entranceInfo);
		                                                        vpPanel.add(feeInfo);
		                                                        if (uploadDocList.size()>0){
		                                                        	vpPanel.add(documentInfo);
		                                                        }
		                                                        vpPanel.add(new Label(" "));
		                                                    }
		                                                });
		                                        }
		                                    });
		                            }
		                        });
		                	}
		                	}) ;   
		                }
		            });
				   }
				});

		        editButton.addListener(new ButtonListenerAdapter(){
		        	public void onClick(Button button,EventObject e){
		        		if (CommonValidation.validate(homePhone)) {
	                        if (CommonValidation.validate(permanentAddress.line1)) {
	                            if (CommonValidation.validate(permanentAddress.line2)) {
	                                if (CommonValidation.validate(permanentAddress.city)) {
	                                    if (CommonValidation.validate(permanentAddress.state)) {
	                                        if (CommonValidation.validate(permanentAddress.pincode)) {		                                                                                    
		                                        if (CommonValidation.validate(ddNo)) {
		                                        	if (CommonValidation.validate(ddDate)) {
		                                        		if (CommonValidation.validate(amount)) {
		                                                    if (CommonValidation.validate(bankName)) {
		                                                        if (CommonValidation.validate(otherBank)) {
		                                                        	MessageBox.confirm(message.confirm(), message.wantToContinue(), new ConfirmCallback(){
																		@Override
																		public void execute(
																				String btnID) {
																			if(btnID.equals(constant.yesButton())){
																				inputDataBeen.setFirstName(firstName.getValueAsString());
																				inputDataBeen.setMiddleName(middleName.getValueAsString());
																				inputDataBeen.setLastName(lastName.getValueAsString());
																				inputDataBeen.setFatherFirstName(fatherFirstName.getValueAsString());
																				inputDataBeen.setFatherMiddleName(fatherMiddleName.getValueAsString());
																				inputDataBeen.setFatherLastName(fatherLastName.getValueAsString());
																				inputDataBeen.setMotherFirstName(motherFirstName.getValueAsString());
																				inputDataBeen.setMotherMiddleName(motherMiddleName.getValueAsString());
																				inputDataBeen.setMotherLastName(motherLastName.getValueAsString());									
																				inputDataBeen.setDob(dob.getValueAsString());
																				inputDataBeen.setCategory(category.getRawValue());
																				inputDataBeen.setNationality(nationality.getValueAsString());
																				inputDataBeen.setGender(gender.getValueAsString());
																				inputDataBeen.setReligion(religion.getValueAsString());
																				inputDataBeen.setGuardian(guardian.getValueAsString());
																				inputDataBeen.setPrimaryEmail(primaryMail.getValueAsString());
																				inputDataBeen.setSecondaryEmail(secondaryMail.getValueAsString());
																				inputDataBeen.setPhoneNumber(homePhone.getValueAsString());
																				inputDataBeen.setOtherPhone(otherPhone.getValueAsString());
																				inputDataBeen.setMinority(minorityCB.getValueAsString());
																				inputDataBeen.setMaritalStatus(maritalCB.getValueAsString());
																				inputDataBeen.setMinorityDesc(minorityCB.getRawValue());
																				inputDataBeen.setMaritalStatusDesc(maritalCB.getRawValue());
																				
																				inputDataBeen.setAddressLine1(permanentAddress.line1.getValueAsString());
																				inputDataBeen.setAddressLine2(permanentAddress.line2.getValueAsString());
																				inputDataBeen.setCity(permanentAddress.city.getValueAsString());
																				inputDataBeen.setState(permanentAddress.state.getValueAsString());
																				if(permanentAddress.pincode.getValueAsString().equals("")){
																					inputDataBeen.setPincode(null);
																				}
																				else{
																					inputDataBeen.setPincode(permanentAddress.pincode.getValueAsString());
																				}																					
																				inputDataBeen.setDdNo(ddNo.getValueAsString());																			
																				inputDataBeen.setDdDate(ddDate.getValueAsString());
																				inputDataBeen.setDdAmount(amount.getValueAsString());										
																				if(bankName.getValueAsString().equalsIgnoreCase("Other")){
																					inputDataBeen.setBankName(otherBank.getValueAsString());
																				}
																				else{
																					inputDataBeen.setBankName(bankName.getValueAsString());
																				}
																				final List<SummarySheetBean> attachmentList =new ArrayList<SummarySheetBean>();
																				final List<SummarySheetBean> subjectCodeList =new ArrayList<SummarySheetBean>();
																				final List<SummarySheetBean> academicList =new ArrayList<SummarySheetBean>();
																				final List<SummarySheetBean> degreeList =new ArrayList<SummarySheetBean>();
																				final List<SummarySheetBean> examCenterCodeList =new ArrayList<SummarySheetBean>();
																				final List<SummarySheetBean> paperGroupList =new ArrayList<SummarySheetBean>();
																				
																				final String docFolder = "ApplicantDocuments";
																			     for (int i = 0;i < uploadDocList.size();i++) {                                                                             	
										                                             if (uploadDocList.get(i).hasFile() &&(uploadDocList.get(i).hasPdfFile() ||uploadDocList.get(i).hasJpgFile()||uploadDocList.get(i).hasDocFile()||uploadDocList.get(i).hasDocxFile())) {
										                                                 SummarySheetBean attachment =new SummarySheetBean();
										                                                 attachment.setDocId(uploadDocList.get(i).getDocId());
										                                                 attachment.setDocName(uploadDocList.get(i).getFileName());
										                                                 attachment.setComponentDescription(uploadDocList.get(i).getDocLabel());
										                                                 attachment.setDocPath(docFolder);
										                                                 attachmentList.add(attachment);
										                                             }
										                                         }
																			     SummarySheetBean picAttachment =new SummarySheetBean();
										                                         picAttachment.setDocName(imgUpload.getFileName());
										                                         picAttachment.setDocId(imgUpload.getDocId());
										                                         picAttachment.setDocPath(docFolder);
										                                         attachmentList.add(picAttachment);                                         
										                                         for(int i=0;i<subjectList.size();i++){
										                                        	 SummarySheetBean been=new SummarySheetBean();
										                                        	 been.setCosCode(subjectList.get(i).getCosComboBox().getValueAsString());
										                                        	 been.setCosDescription(subjectList.get(i).getCosComboBox().getRawValue());
										                                        	 been.setProgramId(subjectList.get(i).getProgramId());
										                                        	 subjectCodeList.add(been);
										                                         }
										                                         for(int i=0;i<examCenterDetailList.size();i++){
										                                        	 SummarySheetBean been=new SummarySheetBean();
										                                        	 been.setCenterCode(examCenterDetailList.get(i).getCenterComboBox().getValueAsString());
										                                        	 been.setCenterDescription(examCenterDetailList.get(i).getCenterComboBox().getRawValue());
										                                        	 been.setProgramId(examCenterDetailList.get(i).getProgramId());
										                                        	 examCenterCodeList.add(been);
										                                         }
										                                         for(int i=0;i<academicDetailList.size();i++){
										                                        	 SummarySheetBean been=new SummarySheetBean();
										                                        	 been.setComponentId(academicDetailList.get(i).getComponentId());
										                                        	 been.setComponentDescription(academicDetailList.get(i).getComponentDescription());
										                                        	 been.setComponentType(academicDetailList.get(i).getComponentType());
										                                        	 been.setComponentWeightage(academicDetailList.get(i).getComponentWeightage());
										                                        	 been.setWeightageFlag(academicDetailList.get(i).getWeightageFlag());
										                                        	 been.setBoardFlag(academicDetailList.get(i).getBoardFlag());
										                                        	 been.setSpecialWeightageFlag(academicDetailList.get(i).getSpecialWeightageFlag());
										                                        	 been.setStaffWeightage(academicDetailList.get(i).getStaffWeightage().isChecked());                                                                            	
										                                        	 if(academicDetailList.get(i).getComponentType().equalsIgnoreCase("M")){
										                                        		 been.setMarksObtained(academicDetailList.get(i).getMarksObtained().getValueAsString());
										                                            	 been.setTotalMarks(academicDetailList.get(i).getTotalMarks().getValueAsString());
										                                        	 }
										                                        	 else if(academicDetailList.get(i).getComponentType().equalsIgnoreCase("P")){
										                                        		 been.setPercentage(academicDetailList.get(i).getPercentageScore().getValueAsString());
										                                        	 }
										                                        	 else{
										                                        		 been.setScore(academicDetailList.get(i).getPercentageScore().getValueAsString());
										                                        	 }
										                                        	 if(academicDetailList.get(i).getBoardFlag().equalsIgnoreCase("Y")){
										                                        		 been.setBoard(academicDetailList.get(i).getBoard().getValueAsString());
										                                        		 been.setBankName(academicDetailList.get(i).getBoard().getRawValue());
										                                        	 }
										                                        	 if(academicDetailList.get(i).getSpecialWeightageFlag().equalsIgnoreCase("Y")){
										                                        		 been.setWeightage(academicDetailList.get(i).getWeightage().isChecked());
										                                        	 }                                                                            	                                                                            	                                                                             	                              								                 
										                                        	 academicList.add(been);                                                                            	                                                                             
										                                         }
										                                         for(int i=0;i<qualificationDetailList.size();i++){
										                                        	 SummarySheetBean been=new SummarySheetBean();
										                                        	 been.setProgramId(qualificationDetailList.get(i).getComponentId());
										                                        	 if(qualificationDetailList.get(i).getComponentId().equals(inputDataBeen.getProgramId())){
										                                        		 been.setProgramName(inputDataBeen.getProgramName());
										                                        	 }                                                                            	
										                                        	 been.setComponentId(qualificationDetailList.get(i).getBoard().getValueAsString());
										                                        	 been.setComponentDescription(qualificationDetailList.get(i).getBoard().getRawValue());
										                                        	 degreeList.add(been);
										                                         }
										                                         
										                                         for(int i=0;i<entranceDetailList.size();i++){
										                                        	 SummarySheetBean been=new SummarySheetBean();
										                                        	 been.setProgramId(entranceDetailList.get(i).getProgramId());  
										                                        	 if(entranceDetailList.get(i).getProgramId().equals(inputDataBeen.getProgramId())){
										                                        		 been.setProgramName(inputDataBeen.getProgramName());
										                                        	 }  
										                                        	 been.setGrouping(entranceDetailList.get(i).getGrouping());
										                                        	 been.setPaperCode(entranceDetailList.get(i).getPaperCombo().getValueAsString());
										                                        	 been.setPaperDescription(entranceDetailList.get(i).getPaperCombo().getRawValue());
										                                        	 paperGroupList.add(been);
										                                         }
										                                         inputDataBeen.setCosCodeList(subjectCodeList);
										                                         inputDataBeen.setAttachmentList(attachmentList);                                                                             
										                                         inputDataBeen.setCenterCodeList(examCenterCodeList);                                                                             
										                                         inputDataBeen.setAcademicList(academicList);
										                                         inputDataBeen.setDegreeList(degreeList);
										                                         inputDataBeen.setPaperGroupList(paperGroupList);                                                                                                                  
										                                         summaryServiceAsync.updateSummarySheet(inputDataBeen, new AsyncCallback<SummarySheetBean>(){
																					@Override
																					public void onFailure(
																							Throwable arg0) {
																						MessageBox.alert(message.error(), arg0.getMessage());
																					}
										
																					@Override
																					public void onSuccess(
																							final SummarySheetBean result) {																																							
																						try{
																							if(result.getComponentType().equals("success")){																						
																								 for (int i =0;i < uploadDocList.size();i++) {
										                                                             if (uploadDocList.get(i).hasFile() &&(uploadDocList.get(i).hasPdfFile() ||uploadDocList.get(i).hasJpgFile()||uploadDocList.get(i).hasDocFile()||uploadDocList.get(i).hasDocxFile())) {
										                                                                 uploadDocList.get(i).uploadFile(docFolder+result.getFileSeparator()+inputDataBeen.getStudentId());
										                                                             }                                                                                                 
										                                                         }
																								 if(imgUpload.hasFile() && imgUpload.hasJpgFile()){
																									 imgUpload.uploadFile(docFolder+result.getFileSeparator()+inputDataBeen.getStudentId());		
																								 }
																								 inputDataBeen.setDdDate(result.getDdDate());
																								 inputDataBeen.setDob(result.getDob());
																								 List<String> regList=new ArrayList<String>();
																								 regList.add(inputDataBeen.getRegistrationNumber());
																								 inputDataBeen.setRegistrationNumList(regList);
																								 summaryServiceAsync.generatePDF(inputDataBeen, docFolder, new AsyncCallback<SummarySheetBean>(){																								 
																									@Override
																									public void onFailure(
																											Throwable arg0) {
																										MessageBox.alert(message.error(), arg0.getMessage());
																									}
										
																									@Override
																									public void onSuccess(
																											SummarySheetBean arg0) {
																										win.close();
																										Window.open(GWT.getHostPageBaseURL() +arg0.getDocPath(),"","");																									
																										MessageBox.alert(message.success(),message.summarySheetUpdateSuccess());																						
																										cbSelectionModel.clearSelections();
																									}
																									 
																								 });
																								
										
																								}																																																																	
																							else{
																								MessageBox.alert(message.error(),message.summarySheetUpdateFail());
																							}
																						}
																						catch(Exception e){
																							MessageBox.alert(message.error(), e.getMessage());
																						}
																					}
										                                        	 
										                                         });
																			}
																		}		                                         		
		                                                        	});	                                                          			                                                                                                                
	                                                              }
	                                                            }else MessageBox.alert(message.selectBankName());
	                                                        }else MessageBox.alert(message.enterAmount());
	                                                	}else MessageBox.alert(message.checkDDDate());	
	                                                }else MessageBox.alert(message.enterDDNum());		                                                                                                                                           
	                                            }else MessageBox.alert(message.pincodeNum());
	                                        }else MessageBox.alert(message.selectState());
	                                    }else MessageBox.alert(message.enterCity());
	                                }else MessageBox.alert(message.checkAddress());
	                            } else MessageBox.alert(message.checkAddress());
			        		}else MessageBox.alert(message.chkPhone());					                        										                        	                        	                                                        				                                                                                                                    			                                                                                                                                                                                                                                		        					                                                    				          
		        	}
		        });        
			}    	  
		});       
        return vpPanel;	
	}
	
	 private Object[][] getBanks() {
	        return new String[][] {
	            new String[] { "Allahabad Bank", "" },
	            new String[] { "American Express Bank Ltd.", "" },
	            new String[] { "Andhra Bank", "" },
	            new String[] { "ANZ Grindlays Bank Plc.", "" },
	            new String[] { "Axis Bank", "" },
	            new String[] { "Bank of American NT & SA", "" },
	            new String[] { "Bank of Baroda", "" },
	            new String[] { "Bank of India", "" },
	            new String[] { "Bank of Maharashtra", "" },
	            new String[] { "Bank of Tokyo Ltd.", "" },
	            new String[] { "Banque Nationale de Paris", "" },
	            new String[] { "Barclays Bank Plc", "" },
	            new String[] { "Canara Bank", "" },
	            new String[] { "Central Bank of India", "" },
	            new String[] { "Citit Bank N.A.", "" },
	            new String[] { "Corporation Bank", "" },
	            new String[] { "Dena Bank", "" },
	            new String[] { "Deutsche Bank A.G.", "" },
	            new String[] { "Dresdner Bank AG", "" },
	            new String[] { "HDFC Bank Ltd.", "" },
	            new String[] { "Hongkong and Shanghai Banking Corporation", "" },
	            new String[] { "ICICI Bank Corp. Bank Ltd.", "" },
	            new String[] { "IDBI Bank Ltd.", "" },
	            new String[] { "Indian Bank", "" },
	            new String[] { "Indian Overseas Bank", "" },
	            new String[] { "Oriental Bank of Commerce", "" },
	            new String[] { "Punjab and Sind Bank", "" },
	            new String[] { "Punjab National Bank", "" },
	            new String[] { "SBI Commercial & Intl. Bank Ltd.", "" },
	            new String[] { "Standard Chartered Bank", "" },
	            new String[] { "State Bank of Bikaner and Jaipur", "" },
	            new String[] { "State Bank of Hyderabad", "" },
	            new String[] { "State Bank of India", "" },
	            new String[] { "State Bank of Indore", "" },
	            new String[] { "State Bank of Mysore", "" },
	            new String[] { "State Bank of Patiala", "" },
	            new String[] { "State Bank of Saurashtra", "" },
	            new String[] { "State Bank of Travancore", "" },
	            new String[] { "Syndicate Bank", "" },
	            new String[] { "The Chase Manhattan Bank Ltd.", "" },
	            new String[] { "UCO Bank", "" },
	            new String[] { "Union Bank of India", "" },
	            new String[] { "United Bank of India", "" },
	            new String[] { "UTI Bank Ltd.", "" },
	            new String[] { "Vijaya Bank", "" },
	            new String[] { "Vysya Bank", "" }, new String[] { "Other", "" },
	        };
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
