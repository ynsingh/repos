/*
 * @(#) SummarySheet.java
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
import in.ac.dei.edrp.client.ProgramSetup.ProgramSearchSetup;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.RPCFiles.SummarySheetService;
import in.ac.dei.edrp.client.RPCFiles.SummarySheetServiceAsync;
import in.ac.dei.edrp.client.Shared.AddressField;
import in.ac.dei.edrp.client.Shared.CM_ComboBoxes;
import in.ac.dei.edrp.client.Shared.DocumentUpload;
import in.ac.dei.edrp.client.Shared.OA_ComboBoxes;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.applicantAccount.ApplicantAccountBean;
import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
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
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.MessageBox.ConfirmCallback;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class SummarySheet {
    SummarySheetServiceAsync summaryServiceAsync = GWT.create(SummarySheetService.class);
    CM_connectTempAsync categoryServiceAsync = GWT.create(CM_connectTemp.class);
    messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    String docFolder = "ApplicantDocuments";
    private String universityId;
    private String userId;
    private String formNumber;
    private String entityId;
    private String entityCode;
    private String entityName;
    private String programId;
    private String sessionStartDate;
    private String sessionEndDate;
    private String userEmail;
    private String uniNickName;
    private String formName;
    final Panel mainPanel = new Panel();
    
    public final VerticalPanel mainVPanel = new VerticalPanel();

    public SummarySheet(String universityId, String entityId,
        String entityCode, String entityName, String formName, String formNumber, String userId,
        String sessionStartDate, String sessionEndDate, String userEmailId,String uniNickName) {
        this.universityId = universityId;
        this.userId = userId;
        this.sessionStartDate = sessionStartDate;
        this.sessionEndDate = sessionEndDate;
        this.formNumber = formNumber;
        this.entityId = entityId;
        this.entityCode = entityCode;
        this.formName=formName;
        this.entityName = entityName;
        this.userEmail=userEmailId;
        this.uniNickName=uniNickName;
        //System.out.println(userEmail+" user");
    }

    public void onModuleLoad() {
        mainVPanel.clear();
        mainPanel.clear();

        final VerticalPanel vPanel = new VerticalPanel();
        final VerticalPanel vpPanel = new VerticalPanel();
        final FlexTable programTable = new FlexTable();

        mainPanel.setHeight("100%");
        mainPanel.setTitle(cons.summarySheet());
        vPanel.setHeight("100%");

        SummarySheetBean inputforProgram = new SummarySheetBean();
        inputforProgram.setUniversityId(universityId);
        inputforProgram.setDocId(formNumber);
        inputforProgram.setEntityId(entityId);

        
        programTable.setWidget(1, 0, new HTML("<b>" + entityName + "</b>"));
        programTable.setWidget(2, 0, new Label("     "));
        
        summaryServiceAsync.getFormProgramList(inputforProgram,
            new AsyncCallback<List<SummarySheetBean>>() {
                @Override
                public void onFailure(Throwable t) {
                    MessageBox.alert(msg.error(), t.getMessage());
                }

                @Override
                public void onSuccess(List<SummarySheetBean> programList) {
                    Object[][] programFormData = new Object[programList.size()][3];

                    for (int i = 0; i < programList.size(); i++) {
                        programFormData[i][0] = programList.get(i).getProgramId();
                        programFormData[i][1] = programList.get(i).getProgramName();
                        programFormData[i][2] = programList.get(i).getEntityId();
                    }

                    RecordDef programFormRecordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("programId"),
                                new StringFieldDef("programName"),
                                new StringFieldDef("entityId")
                            });
                    
                    MemoryProxy programFormProxy = new MemoryProxy(programFormData);
                    ArrayReader programFormReader = new ArrayReader(programFormRecordDef);
                    Store programFormStore = new Store(programFormProxy,
                            programFormReader);
                    final GridPanel programFormGrid = new GridPanel();
                    programFormStore.load();
                    programFormGrid.setStore(programFormStore);

                    final CheckboxSelectionModel cbSelectionModelProgram = new CheckboxSelectionModel();

                    BaseColumnConfig[] columnsProgram = new BaseColumnConfig[] {
                            new CheckboxColumnConfig(cbSelectionModelProgram),
                            new ColumnConfig("Program", "programName", 200,
                                false, null, "programName")
                        };
                    
                    ColumnModel programColumnModel = new ColumnModel(columnsProgram);             
                    programFormGrid.setColumnModel(programColumnModel);
                    programFormGrid.setFrame(true);
                    programFormGrid.setStripeRows(true);
                    programFormGrid.setAutoExpandColumn("programName");
                    programFormGrid.setSelectionModel(cbSelectionModelProgram);
                    programFormGrid.setWidth(400);
                    programFormGrid.setHeight(200);
                    programFormGrid.setTitle(cons.selectProgram());
                    
                    Toolbar toolBar = new Toolbar();
                    toolBar.addFill();
                    programFormGrid.setTopToolbar(toolBar);

                    final ToolbarButton okButton = new ToolbarButton(cons.okButton());
                    toolBar.addButton(okButton);

                    okButton.addListener(new ButtonListenerAdapter() {
                            public void onClick(Button button, EventObject e) {
                                final List<String> programList = new ArrayList<String>();
                                final List<String> programNameList = new ArrayList<String>();
                                final List<String> entityIdList = new ArrayList<String>();
                                
                                final Record[] records = cbSelectionModelProgram.getSelections();

                                for (int p = 0; p < records.length; p++) {
                                    cbSelectionModelProgram.lock();
                                    okButton.setDisabled(true);
                                    programList.add(records[p].getAsString("programId"));
                                    programNameList.add(records[p].getAsString("programName"));
                                    entityIdList.add(records[p].getAsString("entityId"));
                                }
                                if (programList.size() > 0) {
                                	summaryServiceAsync.getApplicantsPrograms(userEmail, new AsyncCallback<List<ApplicantAccountBean>>() {

										public void onFailure(Throwable arg0) {
											MessageBox.alert(""+arg0);
											
										}
										public void onSuccess(List<ApplicantAccountBean> progList) {
											String addedProg = "";
											//System.out.println("size"+addedProg.length());
											for(int i=0;i<progList.size();i++){
												if(programList.contains(progList.get(i).getProgramId())){
													addedProg=addedProg+" "+progList.get(i).getProgramName();
												}
											}
											
												if(addedProg.length()>0){
													cbSelectionModelProgram.unlock();
			                                        okButton.setDisabled(false);
													MessageBox.alert(cons.programsApplied()+" "+addedProg);
												}
												else createPage(vpPanel, programList, programNameList,entityIdList, records, cbSelectionModelProgram, okButton);
		                                	
										}
                                		
									});
                                	
                                } else {
                                	cbSelectionModelProgram.unlock();
                                	okButton.setDisabled(false);
                                    MessageBox.alert(msg.programNotSelected());
                                }
                            }
                        });

                    vPanel.add(programTable);
                    vPanel.add(programFormGrid);

                    vPanel.add(vpPanel);

                    mainPanel.add(vPanel);
                    mainVPanel.add(mainPanel);
                }
            });
    }

    private void createPage(final VerticalPanel vpPanel,
        final List<String> programList, final List<String> programNameList,
        final List<String> entityIdList, final Record[] records,
        final CheckboxSelectionModel cbSelectionModelProgram,
        final ToolbarButton okButton) {
        vpPanel.clear();
        mainPanel.getBody().mask(msg.waitMask());

        final Store banksStore = new SimpleStore(new String[] { "value", "" }, getBanks());

        OA_ComboBoxes comboData = new OA_ComboBoxes();
        comboData.onModuleLoad();

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
        
        final Grid buttonGrid = new Grid(1, 5);

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
        final DateField dob = new DateField();
        final ComboBox category = new ComboBox();
        final ComboBox gender = comboData.genderCB;
        final TextField nationality=new TextField();
        final TextField religion=new TextField();
        final TextField guardian=new TextField();
        final NumberField homePhone = new NumberField();
        final NumberField otherPhone = new NumberField();
        final ComboBox minority=new ComboBox();
        final ComboBox maritalStatus=new ComboBox();
        final TextField ddNo = new TextField();
        final TextField otherBank = new TextField();
        final NumberField amount = new NumberField();
        final ComboBox bankName = new ComboBox();
        final DateField ddDate = new DateField();
        final AddressField permanentAddress = new AddressField(cons.addressDetails());
        
        personalInfo.setTitle(cons.personal_details());
        addressInfo.setTitle(cons.addCorrespondence());
        academicInfo.setTitle(cons.acad_perform());
        staffInfo.setTitle(cons.if_staffWard());
        subjectCodeInfo.setTitle(cons.subjectCode());
        entranceInfo.setTitle(cons.test_options());
        examCenterInfo.setTitle(cons.examCenter());
        qualificationInfo.setTitle(cons.qualification());
        feeInfo.setTitle(cons.feeDetails());
        documentInfo.setTitle(cons.docDetails());
        
        summaryServiceAsync.getApplicantDetails(userEmail,universityId,new AsyncCallback<List<SummarySheetBean>>() {

			public void onFailure(Throwable arg0) {
				
				MessageBox.alert(arg0.toString());
			}

			public void onSuccess(List<SummarySheetBean> details) {
				if(details.size()>0){
					firstName.setValue(details.get(0).getFirstName());
					middleName.setValue(details.get(0).getMiddleName());
					lastName.setValue(details.get(0).getLastName());
					firstName.setReadOnly(true);
					middleName.setReadOnly(true);
					lastName.setReadOnly(true);
					
					if(details.get(0).getFatherFirstName().contains(" ")){
						fatherFirstName.setValue(details.get(0).getFatherFirstName().substring(0, details.get(0).getFatherFirstName().indexOf(" ")));
						if(details.get(0).getFatherFirstName().indexOf(" ")<details.get(0).getFatherFirstName().lastIndexOf(" ")){
							fatherMiddleName.setValue(details.get(0).getFatherFirstName().substring(details.get(0).getFatherFirstName().indexOf(" ")+1, details.get(0).getFatherFirstName().lastIndexOf(" ")));
						}
						fatherLastName.setValue(details.get(0).getFatherFirstName().substring(details.get(0).getFatherFirstName().lastIndexOf(" ")+1));
					}
					else fatherFirstName.setValue(details.get(0).getFatherFirstName());
					
					fatherFirstName.setReadOnly(true);
					fatherMiddleName.setReadOnly(true);
					fatherLastName.setReadOnly(true);
					
					if(details.get(0).getMotherFirstName().contains(" ")){
						motherFirstName.setValue(details.get(0).getMotherFirstName().substring(0, details.get(0).getMotherFirstName().indexOf(" ")));
						if(details.get(0).getMotherFirstName().indexOf(" ")<details.get(0).getMotherFirstName().lastIndexOf(" ")){
							motherMiddleName.setValue(details.get(0).getMotherFirstName().substring(details.get(0).getMotherFirstName().indexOf(" ")+1, details.get(0).getMotherFirstName().lastIndexOf(" ")));
						}
						motherLastName.setValue(details.get(0).getMotherFirstName().substring(details.get(0).getMotherFirstName().lastIndexOf(" ")+1));
					}
					else motherFirstName.setValue(details.get(0).getMotherFirstName());
					
					motherFirstName.setReadOnly(true);
					motherMiddleName.setReadOnly(true);
					motherLastName.setReadOnly(true);
					
					nationality.setValue(details.get(0).getNationality());
					nationality.setReadOnly(true);
					
					religion.setValue(details.get(0).getReligion());
					religion.setReadOnly(true);
					
					primaryMail.setValue(details.get(0).getPrimaryEmail());
					primaryMail.setReadOnly(true);
					
					dob.setValue(details.get(0).getDob());
					dob.setDisabled(true);
					
					category.setValue(details.get(0).getCategory());
					category.setDisabled(true);
					
					if(details.get(0).getGender().equalsIgnoreCase("F"))
					{
						gender.setValue(cons.female());
					}
					else gender.setValue(cons.male());
					gender.setDisabled(true);
					
					homePhone.setValue(details.get(0).getPhoneNumber());
					
					permanentAddress.state.setValue(details.get(0).getState());
					
					
				}
			}
		});

        firstName.setAllowBlank(false);
        firstName.setValidateOnBlur(true);

        // lastName.setAllowBlank(false);
        // lastName.setValidateOnBlur(true);
        fatherFirstName.setAllowBlank(false);
        fatherFirstName.setValidateOnBlur(true);

        // fatherLastName.setAllowBlank(false);
        // fatherLastName.setValidateOnBlur(true);
        motherFirstName.setAllowBlank(false);
        motherFirstName.setValidateOnBlur(true);

        // motherLastName.setAllowBlank(false);
        // motherLastName.setValidateOnBlur(true);
        primaryMail.setAllowBlank(false);
        primaryMail.setVtype(VType.EMAIL);
        primaryMail.setValidateOnBlur(true);

        secondaryMail.setVtype(VType.EMAIL);
        secondaryMail.setValidateOnBlur(true);

        category.setAllowBlank(false);
        category.setForceSelection(true);
        category.setValidateOnBlur(true);

        gender.setAllowBlank(false);
        gender.setForceSelection(true);
        gender.setValidateOnBlur(true);
        gender.setWidth(188);

        //dob.setReadOnly(true);
        dob.setAllowBlank(false);
        dob.setValidateOnBlur(true);
        dob.setWidth(188);
        //dob.setMaxValue(new Date());
        
        nationality.setAllowBlank(false);
        nationality.setValidateOnBlur(true);
        
        religion.setAllowBlank(false);
        religion.setValidateOnBlur(true);

        homePhone.setMinLength(10);
        homePhone.setMaxLength(15);
        homePhone.setDecimalPrecision(0);
        homePhone.setAllowBlank(false);
        homePhone.setValidateOnBlur(true);

        otherPhone.setMinLength(10);
        otherPhone.setMaxLength(15);
        otherPhone.setDecimalPrecision(0);
        otherPhone.setValidateOnBlur(true);

        categoryServiceAsync.Category(userId,
            new AsyncCallback<CM_StudentInfoGetter[]>() {
                @Override
                public void onFailure(Throwable t) {
                    MessageBox.alert(msg.error(), t.getMessage());
                }

                @Override
                public void onSuccess(CM_StudentInfoGetter[] categoryList) {
                    Object[][] categoryObj = new Object[categoryList.length][2];

                    for (int c = 0; c < categoryList.length; c++) {
                        categoryObj[c][0] = categoryList[c].getCategory_code();
                        categoryObj[c][1] = categoryList[c].getCategory();
                    }

                    final Store categoryStore = new SimpleStore(new String[] {
                                "catCode", "category"
                            }, categoryObj);

                    loadCombo(category, categoryStore, "catCode", "category",cons.category());
                    category.setWidth(188);
                    
                    
                    Object[][] minorityObj=new Object[2][2];
                    minorityObj[0][0]="Y";
                    minorityObj[0][1]="Yes";
                    minorityObj[1][0]="N";
                    minorityObj[1][1]="No";
                    
                    Store minorityStore=new SimpleStore(new String[]{
                    		"minorityCode","minority"
                    	}, minorityObj);
                    loadCombo(minority, minorityStore, "minorityCode","minority" , cons.selectMinority());
                    minority.setWidth(188);
                    
                    Object[][] maritalObj=new Object[2][2];
                    maritalObj[0][0]="M";
                    maritalObj[0][1]="Married";
                    maritalObj[1][0]="UM";
                    maritalObj[1][1]="Unmarried";
                    
                    Store maritalStore=new SimpleStore(new String[]{
                    		"maritalCode","marital"
                    	}, maritalObj);
                    loadCombo(maritalStatus, maritalStore, "maritalCode","marital" , cons.selectMarital());
                    maritalStatus.setWidth(188);
                    

                    personalTable.setWidget(2, 0, new Label(cons.selectPhoto()+cons.asterisk()));
                    personalTable.setWidget(3, 1, new Label(cons.firstName()));
                    personalTable.setWidget(3, 2, new Label(cons.middleName()));
                    personalTable.setWidget(3, 3, new Label(cons.lastName()));
                    personalTable.setWidget(4, 0, new Label(cons.stduentName()));
                    personalTable.setWidget(5, 0, new Label(cons.fatherName()));
                    personalTable.setWidget(6, 0, new Label(cons.motherName()));
                    personalTable.setWidget(7, 0, new Label(cons.dob()));
                    personalTable.setWidget(7, 2, new Label(cons.category()));
                    personalTable.setWidget(7, 4, new Label(cons.nationality()+cons.asterisk()));
                    personalTable.setWidget(8, 0, new Label(cons.gender()));
                    personalTable.setWidget(8, 2, new Label(cons.religion()+cons.asterisk()));
                    personalTable.setWidget(8, 4, new Label(cons.guardian()));
                    personalTable.setWidget(9, 0, new Label(cons.primaryMail()+cons.asterisk()));
                    personalTable.setWidget(9, 2,new Label(cons.secondaryMail()));
                    personalTable.setWidget(9, 4,new Label(cons.minority()));
                    personalTable.setWidget(10, 0,new Label(cons.labelPhone() + cons.asterisk()));
                    personalTable.setWidget(10, 2,new Label(cons.labelOtherPhone()));
                    personalTable.setWidget(10, 4,new Label(cons.marital()));

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
                    personalTable.setWidget(7, 1, dob);
                    personalTable.setWidget(7, 3, category);
                    personalTable.setWidget(7, 5, nationality);
                    personalTable.setWidget(8, 1, gender);
                    personalTable.setWidget(8, 3, religion);
                    personalTable.setWidget(8, 5, guardian);
                    personalTable.setWidget(9, 1, primaryMail);
                    personalTable.setWidget(9, 3, secondaryMail);
                    personalTable.setWidget(9, 5, minority);
                    personalTable.setWidget(10, 1, homePhone);
                    personalTable.setWidget(10, 3, otherPhone);
                    personalTable.setWidget(10, 5, maritalStatus);
                }
            });

        final SummarySheetBean inputBean = new SummarySheetBean();
        inputBean.setUniversityId(universityId);
        inputBean.setProgramId(programId);
        inputBean.setEntityIdList(entityIdList);
        inputBean.setProgramList(programList);
        inputBean.setProgramNameList(programNameList);
        academicTable.setWidget(0, 0, new HTML("<b>" + cons.component() + "</b>"));
        academicTable.setWidget(0, 5, new HTML("<b>" + cons.board() + "</b>"));
        //academicTable.setWidget(0, 6, new HTML("<b>" + cons.special_weightage()));
        academicTable.setWidget(0, 6, new HTML("<b>" + ""));
        final List<AcademicDetailsBean> componentList = new ArrayList<AcademicDetailsBean>();
        final List<PaperGroupBean> paperCodeList = new ArrayList<PaperGroupBean>();

        summaryServiceAsync.getCosCodeDescription(inputBean,new AsyncCallback<List<SummarySheetBean>>() {

			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.toString());
				
			}
			public void onSuccess(List<SummarySheetBean> cosCodeList) {

				final List<CosCodeDetailsBean> cosCodeDescriptionList = new ArrayList<CosCodeDetailsBean>();
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
                        loadCombo(cosCombo,store, "cosCode","cosDescription", "");
                        cosCodeComp.setCosComboBox(cosCombo);
                        cosCodeDescriptionList.add(cosCodeComp);
                        subjectCodeTable.setWidget(i,0,new Label(cons.labelfor()+" " +programNameList.get(i)));
                        subjectCodeTable.setWidget(i,1, cosCombo);
                    }
                }
			
        summaryServiceAsync.getProgramComponent(inputBean,
            new AsyncCallback<List<SummarySheetBean>>() {
                public void onFailure(Throwable t) {
                    MessageBox.alert(msg.error(), "in failure" +t.getMessage());
                }
                
                public void onSuccess(List<SummarySheetBean> outputList) {
                	CheckBox applicable=new CheckBox(cons.applicable());
                	staffTable.setWidget(0, 0, applicable);
                	
                	    for (int i = 0; i < outputList.size(); i++) {
		                	
			                    academicTable.setWidget(i + 1, 0,new Label(outputList.get(i).getComponentDescription()));
			                    academicTable.setWidget(i + 1, 1,new Label((outputList.get(i).getComponentType().equalsIgnoreCase("M")? cons.labelMarks() : cons.labelPercentage())));
			
			                    AcademicDetailsBean inputComponents = new AcademicDetailsBean();
			
			                    inputComponents.setComponentId(outputList.get(i).getComponentId());
			                    inputComponents.setComponentDescription(outputList.get(i).getComponentDescription());
			                    inputComponents.setComponentType(outputList.get(i).getComponentType());
			                    inputComponents.setComponentWeightage(outputList.get(i).getComponentWeightage());
			                    inputComponents.setWeightageFlag(outputList.get(i).getWeightageFlag());
			                    inputComponents.setBoardFlag(outputList.get(i).getBoardFlag());
			                    inputComponents.setSpecialWeightageFlag(outputList.get(i).getSpecialWeightageFlag());
			                    inputComponents.setComponentCriteriaFlag(outputList.get(i).getComponentCriteriaFlag());
			                    inputComponents.setUgPg(outputList.get(i).getUgPg());
			                    inputComponents.setRuleNumber(outputList.get(i).getRuleNumber());
			                    inputComponents.setSequenceNumber(outputList.get(i).getSequenceNumber());
			                    inputComponents.setProgramId(outputList.get(i).getProgramId());
			                    inputComponents.setEntityId(outputList.get(i).getEntityId());
			                    inputComponents.setStaffWeightage(applicable);
			                    
			                    if (outputList.get(i).getComponentType()
			                                      .equalsIgnoreCase("M")) {
			                        NumberField marks = new NumberField();
			                        NumberField total = new NumberField();
			
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
			                            new Label(cons.labelOutof()));
			                        academicTable.setWidget(i + 1, 4, total);
			                    } else {
			                        NumberField percentageScore = new NumberField();
			                        percentageScore.setMaxValue(100);
			                        percentageScore.setAllowBlank(false);
			                        percentageScore.setValidateOnBlur(true);
			                        inputComponents.setPercentageScore(percentageScore);
			                        academicTable.setWidget(i + 1, 2, percentageScore);
			                    }
			
			                    if (outputList.get(i).getBoardFlag()
			                                      .equalsIgnoreCase("Y")) {
			                        CM_ComboBoxes ocb = new CM_ComboBoxes(userId);
			                        ocb.BoardName();
			
			                        ComboBox boardCB = ocb.boardCB;
			                        boardCB.setAllowBlank(false);
			                        boardCB.setEditable(false);
			                        boardCB.setValidateOnBlur(true);
			                        boardCB.setWidth(150);
			
			                        inputComponents.setBoard(boardCB);
			
			                        academicTable.setWidget(i + 1, 5, boardCB);
			                    } else {
			                        Label lbl = new Label(" ");
			                        lbl.setWidth("150");
			                        academicTable.setWidget(i + 1, 5, lbl);
			                    }
			
			                    if (outputList.get(i).getSpecialWeightageFlag()
			                                      .equalsIgnoreCase("Y")) {
			                        CheckBox weightage = new CheckBox(cons.existingStudent()); //
			                        inputComponents.setWeightage(weightage);
			                        academicTable.setWidget(i + 1, 6, weightage);
			                    }
			
			                    componentList.add(inputComponents);
		               } 	

                	    
            	   summaryServiceAsync.getExaminationCenter(inputBean,new AsyncCallback<List<SummarySheetBean>>() {

					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						MessageBox.alert(msg.error(), arg0.getMessage());
					}

					public void onSuccess(List<SummarySheetBean> centerValue) {
						final List<CenterCodeDetailsBean> centerCodeDescriptionList = new ArrayList<CenterCodeDetailsBean>();
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
		                        loadCombo(centerCombo,store, "centerCode","centerDescription", "");
		                        centerCodeComp.setCenterComboBox(centerCombo);
		                        centerCodeDescriptionList.add(centerCodeComp);
		                        examCenterTable.setWidget(i,0,new Label(cons.labelfor()+" " +programNameList.get(i)));
		                        examCenterTable.setWidget(i,1, centerCombo);
		                    }
		                }

					   summaryServiceAsync.getGroupWisePaperCode(inputBean,
                        new AsyncCallback<List<SummarySheetBean>>() {
                            @Override
                            public void onFailure(Throwable t) {
                                MessageBox.alert(msg.error(), t.getMessage());
                            }

                            @Override
                            public void onSuccess(List<SummarySheetBean> paperCodes) {
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

                                    entranceTable.setWidget(0, 0, new HTML("<b>" + cons.labelForProgram() + "</b>"));
                                    
                                    entranceTable.setWidget(0, 1,new HTML("<b>" + cons.group() + "</b>"));
                                    
                                    entranceTable.setWidget(gs, 0,new Label( programName+ "  "));
                                    
                                    for (int g = 0; g < groupList.size();g++) {
                                        PaperGroupBean paperGroupBean = new PaperGroupBean();
                                        
                                        
                                        entranceTable.setWidget(gs, 1,new Label(cons.group()+" " +groupList.get(g) + "*"));

                                        paperGroupBean.setGrouping(groupList.get(g));
                                        paperGroupBean.setProgramId(program);
                                        paperGroupBean.setProgramName(programName);

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
                                        loadCombo(paperCombo, store,"paperCode", "paperDescription", "");
                                        entranceTable.setWidget(gs++, 2,paperCombo);
                                        paperGroupBean.setPaperCombo(paperCombo);
                                        paperCodeList.add(paperGroupBean);
                                    }
                                }

                                summaryServiceAsync.getProgramFirstDegreeList(inputBean,
                                    new AsyncCallback<List<SummarySheetBean>>() {
                                        @Override
                                        public void onFailure(Throwable t) {
                                            MessageBox.alert(msg.error(),
                                                t.getMessage());
                                        }

                                        @Override
                                        public void onSuccess(List<SummarySheetBean> firstDegreeList) {
                                            
                                        	final List<AcademicDetailsBean> firstDegreeCompList = new ArrayList<AcademicDetailsBean>();

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
                                                    loadCombo(degreeCombo,store, "degreeCode","degreeName", "");
                                                    firstDegreeComp.setBoard(degreeCombo);
                                                    firstDegreeCompList.add(firstDegreeComp);
                                                    qualificationTable.setWidget(i,0,new Label(cons.labelfor()+" " +programNameList.get(i)));
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

                                            loadCombo(bankName, banksStore,"value", "value",cons.bankName());

                                            bankName.addListener(new ComboBoxListenerAdapter() {
                                                    public void onSelect(ComboBox comboBox,Record record, int index) {
                                                    	
                                                        if (comboBox.getValue().equalsIgnoreCase("other")) {
                                                            otherBank.enable();
                                                            otherBank.setAllowBlank(false);
                                                            feeTable.setWidget(1, 9,new Label(cons.labelIfOther()));
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

                                            //ddDate.setReadOnly(true);
                                            ddDate.setAllowBlank(false);
                                            ddDate.setValidateOnBlur(true);
                                            ddDate.setWidth(100);
                                            //ddDate.setMaxValue(new Date());

                                            feeTable.setWidget(0, 0,new Label(cons.ddNumber()+cons.asterisk()));
                                            feeTable.setWidget(0, 2,new Label("     "));
                                            feeTable.setWidget(0, 3,new Label(cons.ddDate()+cons.asterisk()));
                                            feeTable.setWidget(0, 5,new Label("     "));
                                            feeTable.setWidget(0, 6,new Label(cons.amount()+cons.asterisk()));
                                            feeTable.setWidget(0, 8,new Label("     "));
                                            feeTable.setWidget(0, 9,new Label(cons.bankName()+cons.asterisk()));
                                            /*feeTable.setWidget(1, 9,new Label("If Others:"));*/

                                            feeTable.setWidget(0, 1, ddNo);
                                            feeTable.setWidget(0, 4, ddDate);
                                            feeTable.setWidget(0, 7, amount);
                                            feeTable.setWidget(0, 10, bankName);
                                            /*feeTable.setWidget(1, 10, otherBank);*/

                                            final List<DocumentUpload> uploadDocList = new ArrayList<DocumentUpload>();
                                            summaryServiceAsync.getProgramDocumentList(inputBean,
                                                new AsyncCallback<List<SummarySheetBean>>() {
                                                    @Override
                                                    public void onFailure(Throwable t) {
                                                        MessageBox.alert(msg.error(),t.getMessage());
                                                    }

                                                    @Override
                                                    public void onSuccess(List<SummarySheetBean> docList) {
                                                    	
                                                        for (int k = 0;k < docList.size();k++) {
                                                            DocumentUpload docProof =new DocumentUpload(docList.get(k).getDocName(),"200");
                                                            docProof.setDocId(docList.get(k).getDocId());
                                                            uploadDocList.add(docProof);
                                                            documentInfo.add(docProof);
                                                        }

                                                        final Button submitButton = new Button(cons.submit());
                                                        submitButton.addListener(
                                                                new ButtonListenerAdapter() {
                                                                    public void onClick(Button button,EventObject e) {
                                                                        final List<SummarySheetBean> paperGroupList =new ArrayList<SummarySheetBean>();
                                                                        final List<SummarySheetBean> academicList =new ArrayList<SummarySheetBean>();
                                                                        final List<SummarySheetBean> degreeList =new ArrayList<SummarySheetBean>();
                                                                        final List<SummarySheetBean> attachmentList =new ArrayList<SummarySheetBean>();
                                                                        final List<SummarySheetBean> subjectCodeList =new ArrayList<SummarySheetBean>();
                                                                        final List<SummarySheetBean> examCenterCodeList =new ArrayList<SummarySheetBean>();
                                                                        
                                                                        if(imgUpload.getFileName().length()>0){
                                                                        	if(validateCosCode(cosCodeDescriptionList,subjectCodeList)){
		                                                                        if (CommonValidation.validate(firstName)&&(firstName.getValueAsString().trim().length()>0)) {
		                                                                            if (CommonValidation.validate(fatherFirstName)&&(fatherFirstName.getValueAsString().trim().length()>0)) {
		                                                                                if (CommonValidation.validate(motherFirstName)) {
		                                                                                    if (CommonValidation.validate(dob)) {
		                                                                                        if (CommonValidation.validate(category)) {
		                                                                                        	if (CommonValidation.validate(nationality)) {
		                                                                                            if (CommonValidation.validate(gender)) {
		                                                                                            	if (CommonValidation.validate(religion)) {
		                                                                                                if (CommonValidation.validate(primaryMail)) {
		                                                                                                    if (CommonValidation.validate(secondaryMail)) {
		                                                                                                    	if (CommonValidation.validate(homePhone)) {
		                                                                                                    		if(CommonValidation.validate(otherPhone)){
			                                                                                                            if (CommonValidation.validate(permanentAddress.line1)) {
			                                                                                                                if (CommonValidation.validate(permanentAddress.line2)) {
			                                                                                                                    if (CommonValidation.validate(permanentAddress.city)) {
			                                                                                                                        if (CommonValidation.validate(permanentAddress.state)) {
			                                                                                                                            if (CommonValidation.validate(permanentAddress.pincode)) {
			                                                                                                                                if (validateAcademics(componentList,academicList)) {
			                                                                                                                                    if (validateQualification(firstDegreeCompList,degreeList)) {
			                                                                                                                                    	if(validateCenterCode(centerCodeDescriptionList,examCenterCodeList)){
			                                                                                                                                    	if (validatePaperCode(paperCodeList,paperGroupList)) {
			                                                                                                                                            if (CommonValidation.validate(ddNo)) {
			                                                                                                                                            	if (CommonValidation.validate(ddDate)) {
			                                                                                                                                            		if (CommonValidation.validate(amount)) {
			                                                                                                                                                        if (CommonValidation.validate(bankName)) {
			                                                                                                                                                            if (CommonValidation.validate(otherBank)) {
			                                                                                                                                                            	if(validateImgUpload(imgUpload)){
			                                                                                                                                                            		if(validateUpload(uploadDocList)){
				                                                                                                                                                            		
				                                                                                                                                                            		MessageBox.confirm(msg.confirm(),msg.alert_confirmentries(),new ConfirmCallback() {
				                                                                                                                                                                        public void execute(String btnID) {
				                                                                                                                                                                            if (btnID.equalsIgnoreCase("yes")) {
				                                                                                                                                                                            	
				                                                                                                                                                                            	mainPanel.getBody().mask(msg.waitMask());
				                                                                                                                                                            		        	
				                                                                                                                                                                                final SummarySheetBean finalData =new SummarySheetBean();
				                                                                                                                                                                                						
				                                                                                                                                                                                finalData.setUniversityId(universityId);
				                                                                                                                                                                                finalData.setUserId(userId);
				                                                                                                                                                                                finalData.setFormNumber(formNumber);
				                                                                                                                                                                                finalData.setEntityId(entityId);
				                                                                                                                                                                                finalData.setEntityCode(entityCode);
				                                                                                                                                                                                finalData.setUniversityNickName(uniNickName);
				                                                                                                                                                                                finalData.setEntityName(entityName);
				                                                                                                                                                                                /*finalData.setProgramId(programId);
				                                                                                                                                                                                finalData.setProgramName(programName);
				                                                                                                                                                                                */
				                                                                                                                                                                                finalData.setSessionStartDate(sessionStartDate);
				                                                                                                                                                                                finalData.setSessionEndDate(sessionEndDate);
				                                                                                                                                                                                
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
				                                                                                                                                                                                finalData.setCategory(category.getValueAsString());
				                                                                                                                                                                                finalData.setNationality(nationality.getValueAsString());
				                                                                                                                                                                                finalData.setGender(gender.getValueAsString().substring(0,1));
				                                                                                                                                                                                finalData.setReligion(religion.getValueAsString());
				                                                                                                                                                                                finalData.setGuardian(guardian.getValueAsString());
				                                                                                                                                                                                finalData.setPrimaryEmail(primaryMail.getValueAsString());
				                                                                                                                                                                                finalData.setSecondaryEmail(secondaryMail.getValueAsString());
				                                                                                                                                                                                finalData.setMinority(minority.getValueAsString());
				                                                                                                                                                                                finalData.setMaritalStatus(maritalStatus.getValueAsString());
				                                                                                                                                                                                finalData.setMinorityDesc(minority.getRawValue());
				                                                                                                                                                                                finalData.setMaritalStatusDesc(maritalStatus.getRawValue());
				                                                                                                                                                                                finalData.setPhoneNumber(homePhone.getValueAsString());
				                                                                                                                                                                                finalData.setOtherPhone(otherPhone.getValueAsString());
				                                                                                                                                                                                finalData.setAddressLine1(permanentAddress.line1.getValueAsString());
				                                                                                                                                                                                finalData.setAddressLine2(permanentAddress.line2.getValueAsString());
				                                                                                                                                                                                finalData.setCity(permanentAddress.city.getValueAsString());
				                                                                                                                                                                                finalData.setState(permanentAddress.state.getValueAsString());
				                                                                                                                                                                                finalData.setPincode(permanentAddress.pincode.getValueAsString());
				                                                                                                                                                                                finalData.setDdNo(ddNo.getValueAsString());
				                                                                                                                                                                                finalData.setDdDate(ddDate.getText());
				                                                                                                                                                                                finalData.setDdAmount(amount.getText());
				                                                                                                                                                                                finalData.setUserEmailId(userEmail);
				                                                                                                                                                                                
				                                                                                                                                                                                if (bankName.getValueAsString().equalsIgnoreCase("other")) {
				                                                                                                                                                                                    finalData.setBankName(otherBank.getValueAsString());
				                                                                                                                                                                                } else {
				                                                                                                                                                                                    finalData.setBankName(bankName.getValueAsString());
				                                                                                                                                                                                }
				                                                                                                                                                                                
				                                                                                                                                                                                finalData.setProgramList(programList);
				                                                                                                                                                                                finalData.setProgramNameList(programNameList);
				                                                                                                                                                                                finalData.setEntityIdList(entityIdList);
				                                                                                                                                                                                
				                                                                                                                                                                                for (int i = 0;i < uploadDocList.size();i++) {
				                                                                                                                                                                                	
				                                                                                                                                                                                    if (uploadDocList.get(i).hasFile() &&(uploadDocList.get(i).hasPdfFile() ||uploadDocList.get(i).hasJpgFile()||uploadDocList.get(i).hasDocFile()||uploadDocList.get(i).hasDocxFile())) {
				                                                                                                                                                                                        SummarySheetBean attachment =new SummarySheetBean();
				                                                                                                                                                                                        System.out.println(" doc idd "+uploadDocList.get(i).getDocId());
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
				                                                                                                                                                                                
				                                                                                                                                                                                finalData.setCosCodeList(subjectCodeList);
				                                                                                                                                                                                finalData.setCenterCodeList(examCenterCodeList);
				                                                                                                                                                                                finalData.setAcademicList(academicList);
				                                                                                                                                                                                finalData.setPaperGroupList(paperGroupList);
				                                                                                                                                                                                finalData.setAttachmentList(attachmentList);
				                                                                                                                                                                                finalData.setDegreeList(degreeList);
				                                                                                                                                                                                
				                                                                                                                                                                                summaryServiceAsync.insertSummarySheetDetails(finalData,
				                                                                                                                                                                                    new AsyncCallback<SummarySheetBean>() {
				                                                                                                                                                                                        @Override
				                                                                                                                                                                                        public void onFailure(Throwable t) {
				                                                                                                                                                                                            MessageBox.alert(msg.error(),t.getMessage());
				                                                                                                                                                                                        }
				
				                                                                                                                                                                                        @Override
				                                                                                                                                                                                        public void onSuccess(final SummarySheetBean outBean) {
				                                                                                                                                                                                        	//mainPanel.getBody().unmask();
				                                                                                                                                                                                            try {
				                                                                                                                                                                                                for (int i =0;i < uploadDocList.size();i++) {
				                                                                                                                                                                                                    if (uploadDocList.get(i).hasFile() &&(uploadDocList.get(i).hasPdfFile() ||uploadDocList.get(i).hasJpgFile()||uploadDocList.get(i).hasDocFile()||uploadDocList.get(i).hasDocxFile())) {
				                                                                                                                                                                                                        uploadDocList.get(i).uploadFile(docFolder+outBean.getFileSeparator()+outBean.getStudentId());
				                                                                                                                                                                                                    }
				                                                                                                                                                                                                    
				                                                                                                                                                                                                }
				                                                                                                                                                                                                imgUpload.uploadFile(docFolder +outBean.getFileSeparator()+outBean.getStudentId());
				                                                                                                                                                                                                
				                                                                                                                                                                                                summaryServiceAsync.generatePDF(outBean,docFolder,new AsyncCallback<SummarySheetBean>() {
				                                                                                                                                                                                                        public void onFailure(Throwable t) {
				                                                                                                                                                                                                            MessageBox.alert(msg.error(),
				                                                                                                                                                                                                                t.getMessage());
				                                                                                                                                                                                                        }
				
				                                                                                                                                                                                                        public void onSuccess(SummarySheetBean finalOutBean) {
				                                                                                                                                                                                                        	mainPanel.getBody().unmask();
				                                                                                                                                                                                                            Window.open(GWT.getHostPageBaseURL() +finalOutBean.getDocPath(),"","");
				                                                                                                                                                                                                            String regNum="";
				                                                                                                                                                                                                            for(int i=0;i<outBean.getRegistrationNumList().size();i++){
				                                                                                                                                                                                                            	regNum=regNum+outBean.getRegistrationNumList().get(i)+" ";
				                                                                                                                                                                                                            }
				                                                                                                                                                                                                            outBean.setRegistrationNumber(regNum);
				                                                                                                                                                                                                            finalOutBean.setRegistrationNumber(regNum);
	                                                                                                                                                                                                                        summaryServiceAsync.sendMailConfirmation(outBean,new AsyncCallback<SummarySheetBean>() {

	                                                                                                                                                                                                                        	@Override
																																																								public void onFailure(Throwable arg0) {
																																																									System.out.println("failure");
																																																								}

																																																								@Override
																																																								public void onSuccess(SummarySheetBean arg0) {
																																																									try{
																																																										System.out.println("mail send");
																																																									}
																																																									catch(Exception e){
																																																										System.out.println("error");
																																																									}
																																																								}
																																																							});	
				                                                                                                                                                                                                            
				                                                                                                                                                                                                            MessageBox.alert(msg.success(),msg.alert_successfulentry() +" " +msg.yourRegNo() +" " +
				                                                                                                                                                                                                                finalOutBean.getRegistrationNumber(),new AlertCallback() {
				                                                                                                                                                                                                                    @Override
				                                                                                                                                                                                                                    public void execute() {
				                                                                                                                                                                                                                        vpPanel.clear();
				                                                                                                                                                                                                                        //for (int p =0;p < records.length;p++) {
				                                                                                                                                                                                                                            mainVPanel.clear();
				                                                                                                                                                                                                                            ProgramSearchSetup progSearch =new ProgramSearchSetup(userId,userEmail);
				                                                                                                                                                                                                                            progSearch.searchProgramSetup();
				                                                                                                                                                                                                                            mainVPanel.add(progSearch.vPanel);
				                                                                                                                                                                                                                        //}
				                                                                                                                                                                                                                    }
				                                                                                                                                                                                                                });
				                                                                                                                                                                                                        }
				                                                                                                                                                                                                    });
				                                                                                                                                                                                            } catch (Exception e) {
				                                                                                                                                                                                                MessageBox.alert(msg.error(),e.getMessage());
				                                                                                                                                                                                            }
				                                                                                                                                                                                        }
				                                                                                                                                                                                    });
				                                                                                                                                                                                
				                                                                                                                                                                                
				                                                                                                                                                                                //mainPanel.getBody().unmask();
				                                                                                                                                                                            }
				                                                                                                                                                                        }
				                                                                                                                                                                    });
				                                                                                                                                                            	
				                                                                                                                                                            	 }
				                                                                                                                                                            	else{
				                                                                                                                                                            		MessageBox.alert(msg.fileFormatError());
				                                                                                                                                                            	}
				                                                                                                                                                            	
				                                                                                                                                                            }
			                                                                                                                                                            	else{
			                                                                                                                                                            		MessageBox.alert(msg.imageFormatError());
			                                                                                                                                                            	}
			                                                                                                                                                            }
			                                                                                                                                                        }else MessageBox.alert(msg.selectBankName());
			                                                                                                                                                    }else MessageBox.alert(msg.enterAmount());
			                                                                                                                                            	}else MessageBox.alert(msg.checkDDDate());	
			                                                                                                                                            }else MessageBox.alert(msg.enterDDNum());
			                                                                                                                                        }else MessageBox.alert(msg.testOption());
			                                                                                                                                    }else MessageBox.alert(msg.selectExamCenter());
			                                                                                                                                    } else MessageBox.alert(msg.selectQuali());
			                                                                                                                                }else MessageBox.alert(msg.selectAcademics());
			                                                                                                                                
			                                                                                                                            }else MessageBox.alert(msg.pincodeNum());
			                                                                                                                        }else MessageBox.alert(msg.selectState());
			                                                                                                                    }else MessageBox.alert(msg.enterCity());
			                                                                                                                }else MessageBox.alert(msg.checkAddress());
			                                                                                                            } else MessageBox.alert(msg.checkAddress());
		                                                                                                    		}else MessageBox.alert(msg.chkPhone());    
		                                                                                                    	}else MessageBox.alert(msg.chkPhone());
		                                                                                                    }else MessageBox.alert(msg.checkEmail());
		                                                                                                }else MessageBox.alert(msg.checkEmail());
		                                                                                            }else MessageBox.alert(msg.enterReligion());
		                                                                                            }else MessageBox.alert(msg.selectGender());
		                                                                                        }else MessageBox.alert(msg.selectNationality());
		                                                                                        }else MessageBox.alert(msg.selectCategory());
		                                                                                    }else MessageBox.alert(msg.checkDOB());
		                                                                                }else MessageBox.alert(msg.enterMotherName());
		                                                                            }else MessageBox.alert(msg.enterFatherName());
		                                                                        }else MessageBox.alert(msg.enterName());
                                                                        	}else MessageBox.alert(msg.selectSubCode());
                                                                    	} else MessageBox.alert(msg.uploadPhoto());
                                                                    }
                                                                });

                                                        Button resetButton = new Button(cons.reset(),
                                                                new ButtonListenerAdapter() {
                                                                    public void onClick(Button button,EventObject e) {
                                                                        createPage(vpPanel,programList,programNameList,entityIdList,records,cbSelectionModelProgram,okButton);
                                                                    }
                                                                });

                                                        Button cancelButton = new Button(cons.cancelButton(),
                                                        		new ButtonListenerAdapter() {
                                                                    public void onClick(Button button,EventObject e) {
                                                                        vpPanel.clear();

                                                                        for (int p =0;p < records.length;p++) {
                                                                            cbSelectionModelProgram.unlock();
                                                                            okButton.setDisabled(false);
                                                                            cbSelectionModelProgram.clearSelections();
                                                                        }
                                                                    	
                                                                    	mainVPanel.clear();
                                                                    	ProgramSearchSetup progSearch=new ProgramSearchSetup(userId,userEmail);
                                                        				progSearch.searchProgramSetup();
                                                        				mainVPanel.add(progSearch.vPanel);
                                                                    }
                                                                });

                                                        personalInfo.add(personalTable);
                                                        addressInfo.add(permanentAddress);
                                                        academicInfo.add(academicTable);
                                                        staffInfo.add(staffTable);
                                                        subjectCodeInfo.add(subjectCodeTable);
                                                        examCenterInfo.add(examCenterTable);
                                                        entranceInfo.add(entranceTable);
                                                        
                                                        qualificationInfo.add(qualificationTable);
                                                        feeInfo.add(feeTable);

                                                        buttonGrid.setWidget(0,0, submitButton);
                                                        buttonGrid.setWidget(0,2, resetButton);
                                                        buttonGrid.setWidget(0,4, cancelButton);

                                                        vpPanel.add(subjectCodeInfo);
                                                        vpPanel.add(personalInfo);
                                                        vpPanel.add(addressInfo);
                                                        /*vpPanel.add(subjectCodeInfo);*/
                                                        vpPanel.add(staffInfo);
                                                        vpPanel.add(academicInfo);
                                                        vpPanel.add(qualificationInfo);
                                                        vpPanel.add(examCenterInfo);
                                                        vpPanel.add(entranceInfo);
                                                        vpPanel.add(feeInfo);
                                                        if (uploadDocList.size()>0){
                                                        	vpPanel.add(documentInfo);
                                                        }
                                                        vpPanel.add(buttonGrid);
                                                        vpPanel.add(new Label(" "));
                                                        mainPanel.getBody().unmask();
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

    private Boolean validateCosCode(List<CosCodeDetailsBean> cosCodeDescriptionList,
            List<SummarySheetBean> subjectCodeList) {
            for (int pc = 0; pc < cosCodeDescriptionList.size(); pc++) {
                SummarySheetBean subjectCodeDescription = new SummarySheetBean();

                if (!CommonValidation.validate(cosCodeDescriptionList.get(pc).getCosComboBox())) {
                    return false;
                }
                subjectCodeDescription.setCosCode(cosCodeDescriptionList.get(pc).getCosComboBox().getValueAsString());
                subjectCodeDescription.setCosDescription(cosCodeDescriptionList.get(pc).getCosComboBox().getRawValue());
                subjectCodeDescription.setProgramId(cosCodeDescriptionList.get(pc).getProgramId());
                subjectCodeList.add(subjectCodeDescription);
                
            }

            return true;
        }
    private Boolean validateCenterCode(List<CenterCodeDetailsBean> centerCodeDescriptionList,
            List<SummarySheetBean> examCenterCodeList) {
            for (int pc = 0; pc < centerCodeDescriptionList.size(); pc++) {
                SummarySheetBean examCenterDescription = new SummarySheetBean();

                if (!CommonValidation.validate(centerCodeDescriptionList.get(pc).getCenterComboBox())) {
                    return false;
                }
                examCenterDescription.setCenterCode(centerCodeDescriptionList.get(pc).getCenterComboBox().getValueAsString());
                examCenterDescription.setCenterDescription(centerCodeDescriptionList.get(pc).getCenterComboBox().getRawValue());
                examCenterDescription.setProgramId(centerCodeDescriptionList.get(pc).getProgramId());
                examCenterCodeList.add(examCenterDescription);
                
            }

            return true;
        }
    
    private Boolean validatePaperCode(List<PaperGroupBean> paperCodeList,
        List<SummarySheetBean> paperGroupList) {
        for (int pc = 0; pc < paperCodeList.size(); pc++) {
            SummarySheetBean paperGroup = new SummarySheetBean();

            if (!CommonValidation.validate(paperCodeList.get(pc).getPaperCombo())) {
                return false;
            }

            paperGroup.setPaperCode(paperCodeList.get(pc).getPaperCombo()
                                                 .getValueAsString());
            paperGroup.setPaperDescription(paperCodeList.get(pc).getPaperCombo()
                                                        .getRawValue());
            paperGroup.setGrouping(paperCodeList.get(pc).getGrouping());
            paperGroup.setProgramId(paperCodeList.get(pc).getProgramId());
            paperGroup.setProgramName(paperCodeList.get(pc).getProgramName());
            paperGroupList.add(paperGroup);
        }

        return true;
    }

    private Boolean validateQualification(
        List<AcademicDetailsBean> firstDegreeCompList,
        List<SummarySheetBean> degreeList) {
        for (int pc = 0; pc < firstDegreeCompList.size(); pc++) {
        	
            SummarySheetBean degree = new SummarySheetBean();

            if (!CommonValidation.validate(firstDegreeCompList.get(pc).getBoard())) {
                return false;
            }

            degree.setProgramId(firstDegreeCompList.get(pc).getComponentId());
            degree.setComponentId(firstDegreeCompList.get(pc).getBoard().getValue());
            degree.setComponentDescription(firstDegreeCompList.get(pc).getBoard().getRawValue());
            degree.setProgramName(firstDegreeCompList.get(pc).getProgramName());
            
            degreeList.add(degree);
        }

        return true;
    }
    
    private Boolean validateUpload(List<DocumentUpload> uploadDocList) {
        boolean flag=false;
        try{
        	//System.out.println(uploadDocList.size());
	        for (int i = 0; i < uploadDocList.size(); i++) {
	    		if(uploadDocList.get(i).getFileName().length()>0){
		           	if (uploadDocList.get(i).hasFile() &&(uploadDocList.get(i).hasPdfFile() ||uploadDocList.get(i).hasJpgFile()||uploadDocList.get(i).hasDocFile()||uploadDocList.get(i).hasDocxFile())) {
		            		flag=true;
		           	}
		           	else flag=false;
	    		}
	    		else flag=true;
	        }
        }
        catch(Exception ex){
        	System.out.println("validateUpload"+ex.getStackTrace()+" "+ex);
        }
        return flag;
    }
    
    private Boolean validateImgUpload(DocumentUpload imgUpload) {
        boolean flag=false;    
    	   	if (imgUpload.hasFile() &&(imgUpload.hasJpgFile())) {
            		flag=true;
           	}
           	else flag=false;
        return flag;
    }

    private Boolean validateAcademics(List<AcademicDetailsBean> componentList,
        List<SummarySheetBean> academicList) {
        for (int cl = 0; cl < componentList.size(); cl++) {
            SummarySheetBean academic = new SummarySheetBean();
            academic.setComponentId(componentList.get(cl).getComponentId());
            academic.setComponentDescription(componentList.get(cl).getComponentDescription());
            academic.setComponentType(componentList.get(cl).getComponentType());
            /*academic.setProgramId(componentList.get(cl).getProgramId());
            academic.setEntityId(componentList.get(cl).getEntityId());*/
           // System.out.println("upasana "+componentList.get(cl).getWeightage().getValue());
           
            
            
            if (componentList.get(cl).getComponentType().equalsIgnoreCase("M")) {
                if (!CommonValidation.validate(componentList.get(cl).getMarksObtained())) {
                    return false;
                }

                if (!CommonValidation.validate(componentList.get(cl).getTotalMarks())) {
                    return false;
                }

                if (componentList.get(cl).getTotalMarks().getValue().longValue() < componentList.get(cl).getMarksObtained().getValue().longValue()) {
                    componentList.get(cl).getTotalMarks().focus(true);

                    return false;
                }

                academic.setTotalMarks(componentList.get(cl).getTotalMarks().getValueAsString());
                academic.setMarksObtained(componentList.get(cl).getMarksObtained().getValueAsString());
                
                double marksPercent=Double.parseDouble(componentList.get(cl).getMarksObtained().getValueAsString())/Double.parseDouble(componentList.get(cl).getTotalMarks().getValueAsString())*100;
                academic.setPercentage(marksPercent+"");
                
            } else if (componentList.get(cl).getComponentType().equalsIgnoreCase("P")) {
                if (!CommonValidation.validate(componentList.get(cl).getPercentageScore())) {
                    return false;
                }

                academic.setPercentage(componentList.get(cl).getPercentageScore().getValueAsString());
            } else {
                if (!CommonValidation.validate(componentList.get(cl).getPercentageScore())) {
                    return false;
                }

                academic.setScore(componentList.get(cl).getPercentageScore().getValueAsString());
            }

            academic.setBoardFlag(componentList.get(cl).getBoardFlag());

            if (componentList.get(cl).getBoardFlag().equalsIgnoreCase("y")) {
                if (!CommonValidation.validate(componentList.get(cl).getBoard())) {
                    return false;
                }

                academic.setBoard(componentList.get(cl).getBoard().getValueAsString());
                academic.setBankName(componentList.get(cl).getBoard().getRawValue());
            }

            academic.setSpecialWeightageFlag(componentList.get(cl).getSpecialWeightageFlag());
            //System.out.println("special weightage "+componentList.get(cl).getSpecialWeightageFlag());
            
            if (componentList.get(cl).getSpecialWeightageFlag().equalsIgnoreCase("y")) {
                academic.setWeightage(componentList.get(cl).getWeightage().getValue());
            }
            
            if(componentList.get(cl).getStaffWeightage().getValue()){
            	academic.setStaffWeightage(componentList.get(cl).getStaffWeightage().getValue());
            }

            academicList.add(academic);
        }

        return true;
    }
}
