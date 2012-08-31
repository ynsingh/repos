package in.ac.dei.edrp.client.Applications;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.Shared.CM_ComboBoxes;
import in.ac.dei.edrp.client.Shared.OA_ComboBoxes;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.util.DateUtil;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;
import com.gwtextux.client.data.PagingMemoryProxy;

public class CreateSummarySheet {
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    String progID;
    String Program;
    SummarySheet summaryObj;
    String NewRegNo;
    String ProgRegValue;
    String FormNo;
    String entity_id;
    String entity_name;
    String stu_value;
    boolean flag = false;
    boolean checkProgReg = false;
    String RegFlag;
    String catCode;
    String Stu_Cos;
    String entity_DefaultType = "";
    String entity_DefaultDescription = "";
    boolean entityViewIs = false;
    String[] spwt;
    CM_ComboBoxes entityTypeCB;
    CM_ComboBoxes entityNameCB;
    String universityId;
    ComboBox universityComboBox;
    VerticalPanel vp;
    ScrollPanel mainVerticalPanel = new ScrollPanel();
    Validator valid = new Validator();
    String creatorid;
    messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    String dbException = cons.dbError();
    String errorMsg = msg.error();
    String confirm = msg.confirm();
    String alertSave = msg.alert_confirmentries();
    String correctEntriesMsg = msg.checkFields();
    String msgCheckFormNo = msg.checkFormNo(8);
    String msgCheckRegNo = msg.checkRegNo(8);
    String duplicateForm = msg.duplicateFormNoAlert();
    String duplicateRegNo = msg.duplicateRegNoAlert();
    String xCOS = cons.xCOS();
    Button submitButton;
    Button cancelButton;
    Button okButton;
    FormPanel mainForm;
    String entityId;
    Label entityName = new Label(cons.entityName());
    
    List<String> programList;
    String[][] programObject=null;
    String[][] subjectObject=null;
    String subjectCode="";
    String subjectName;
    VerticalPanel panel;
    HorizontalPanel subjectPanel;
    HorizontalPanel programNamePanel;
    Label subjectComboLabel;
    ComboBox subjectCombo;
    FlexTable flex3;
    FlexTable flex4;
    FlexTable flex5;
    public CreateSummarySheet(String userid) {
        this.creatorid = userid;        
    }

    //after development change it's return type (ScrollPanel)
    public ScrollPanel onModuleLoad1() {
		subjectCode="";
        summaryObj = new SummarySheet(creatorid);        
        mainVerticalPanel.clear();
        mainVerticalPanel.setWidth("100%");
        mainVerticalPanel.setHeight("100%");

        vp = new VerticalPanel();
        panel=new VerticalPanel();
        okButton = new Button(cons.okButton());
        submitButton = new Button(cons.submit());
        cancelButton=new Button("Cancel");
        entityTypeCB = new CM_ComboBoxes(creatorid);
        entityNameCB = new CM_ComboBoxes(creatorid);      
        mainForm = new FormPanel();

        entityTypeCB.onModuleLoad();
        entityNameCB.onModuleLoad();
        entityNameCB.entityCombo.disable();
        programNamePanel=new HorizontalPanel();
        subjectPanel=new HorizontalPanel();
        subjectComboLabel=new Label("Select Subject  ");
        subjectCombo = new ComboBox();
        
        subjectCombo.setEmptyText("Select Subject");
        subjectCombo.setForceSelection(true);
        subjectCombo.setMinChars(1);
        subjectCombo.setDisplayField("subjectName");
        subjectCombo.setValueField("subjectCode");
        subjectCombo.setMode(ComboBox.LOCAL);
        subjectCombo.setTriggerAction(ComboBox.ALL);
        subjectCombo.setLoadingText("Searching...");
        subjectCombo.setTypeAhead(true);
        subjectCombo.setSelectOnFocus(true);
        subjectCombo.setHideTrigger(false);
        
        universityComboBox = new ComboBox();
        universityComboBox.setEmptyText("Select University");
        universityComboBox.setForceSelection(true);
        universityComboBox.setMinChars(1);
        universityComboBox.setDisplayField("UniversityName");
        universityComboBox.setValueField("UniversityCode");
        universityComboBox.setMode(ComboBox.LOCAL);
        universityComboBox.setTriggerAction(ComboBox.ALL);
        universityComboBox.setLoadingText("Searching...");
        universityComboBox.setTypeAhead(true);
        universityComboBox.setSelectOnFocus(true);
        universityComboBox.setHideTrigger(false);
         
        
        /**
         *  Checking for default entity view
         *  method updated
         */
        connectTemp.checkForDefaultView(new AsyncCallback<CM_entityInfoGetter[]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert(dbException, caught.getMessage());
                }

                public void onSuccess(CM_entityInfoGetter[] arg0) {
                    if (arg0.length == 1) {                    	
                        entityViewIs = true;

                        try {
                            furhterExecution(arg0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {                        
                    	
                        try {
                            furhterExecution(arg0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
       
        cancelButton.addListener(new ButtonListenerAdapter() {	
			@Override
			public void onClick(Button button, EventObject e) {
				mainVerticalPanel.clear();
				onModuleLoad1();
				
			}
		});
        submitButton.addListener(new ButtonListenerAdapter() {
                @SuppressWarnings({"deprecation", "static-access"})
                public void onClick(Button button, EventObject e) {
                    System.out.println("inside submit button listener program id is"+progID);

                    int check = 0;
                    if (summaryObj.checkDate() > 0) {
                        check++;
                    }

                    if (summaryObj.checkPersonal() > 0) {
                        summaryObj.markIV(summaryObj.fname);
                        summaryObj.markIV(summaryObj.dateofbirthDateField);
                        summaryObj.markIV(summaryObj.newCat);
                        summaryObj.markIV(summaryObj.fname1);
                        summaryObj.markIV(summaryObj.formNumber);
                        /*
                         * mother name
                         */
                        summaryObj.markIV(summaryObj.newGender);

                        check++;                        
                    }

                    if (summaryObj.checkAddress() > 0) {
                        summaryObj.markIV(summaryObj.cityCombo);
                        check++;
                        
                    }

                    if (summaryObj.checkAcad() > 0) {
                        check++;                        
                    }

                    if (summaryObj.checkAcad() == 0) {
                        if (summaryObj.marksCheck() > 0) {
                            check++;                            
                        }
                    }

                    if (summaryObj.hpPaper.isVisible()) {
                        if (summaryObj.checkpaperCode() > 0) {
                            check++;                            
                        }
                    }

                    if (summaryObj.checkUG() > 0) {
                        summaryObj.markIV(summaryObj.ug.firstDegCodeCB);
                        check++;                        
                    }

                    if (summaryObj.checkPG() > 0) {
                        summaryObj.markIV(summaryObj.pg.firstDegCodeCB);
                        check++;                      
                    }

                    if (summaryObj.CheckBoard(summaryObj.ocb1) > 0) {
                        check++;                        
                    }

                    if (check == 0) {
                    	
                    	final CM_StudentInfoGetter studentInfoGetter= new CM_StudentInfoGetter();
                        
                        /*
                         * program info
                         */
                        studentInfoGetter.setProgramId(progID);
                        studentInfoGetter.setEntity_id(entity_id);                        
                        studentInfoGetter.setCreator_id(creatorid);
                        
                        /*
                         * personal info
                         */
                        
                        studentInfoGetter.setFirst_name(summaryObj.fname.getRawValue().trim());
                        studentInfoGetter.setMiddle_name(summaryObj.mname.getRawValue().trim());
                        studentInfoGetter.setLast_name(summaryObj.lname.getRawValue().trim());
                        studentInfoGetter.setFather_Fname(summaryObj.fname1.getRawValue().trim());
                        studentInfoGetter.setFather_Mname(summaryObj.mname1.getRawValue().trim());
                        studentInfoGetter.setFather_Lname(summaryObj.lname1.getRawValue().trim());
                        studentInfoGetter.setMother_Fname(summaryObj.fname2.getRawValue().trim());
                        studentInfoGetter.setMother_Mname(summaryObj.mname2.getRawValue().trim());
                        studentInfoGetter.setMother_Lname(summaryObj.lname2.getRawValue().trim());                      
                        studentInfoGetter.setCategory(summaryObj.newCat.getValue());
                        studentInfoGetter.setGender(summaryObj.newGender.getValue());
                        studentInfoGetter.setForm_number(summaryObj.formNumber.getText());
                        
                        DateUtil d = new DateUtil();
                        studentInfoGetter.setDate_of_birth(d.format(summaryObj.dateofbirthDateField.getValue(), "Y-m-d"));
                        
                        /*
                         * address info
                         */                                                      
                        studentInfoGetter.setAddressLine1(summaryObj.street1Text.getRawValue().trim());
                        studentInfoGetter.setAddressLine2(summaryObj.street2Text.getRawValue().trim());
                        studentInfoGetter.setCity(summaryObj.cityCombo.getRawValue().trim());
                        System.out.println("after submit button state is "+summaryObj.newState.getRawValue());
                        studentInfoGetter.setState(summaryObj.newState.getRawValue());
                        studentInfoGetter.setPinCode(summaryObj.pinNumber1.getRawValue());
                    	
                    	/*
                    	 * add a method to check if the student with same basic info
                    	 * already exist for the same program combination
                    	 */
                    	connectTemp.checkStudentValidation(studentInfoGetter,new AsyncCallback<Boolean>(){

							public void onFailure(Throwable arg0) {
								
								MessageBox.alert("Failure"+arg0.getMessage());
								
							}

							public void onSuccess(Boolean arg0) {
								
								if(arg0==false){
									
			                    	/*
			                    	 * after checking the front end validations 
			                    	 */
			                    	MessageBox.confirm(confirm,
			                              alertSave,
			                              new MessageBox.ConfirmCallback() {
			                                  public void execute(
			                                      String btnID) {
			                                      if (btnID.matches(
			                                                  "yes")) {
			                                    	  
			                                    	  /**
			                                           * method updated
			                                           */
			                                  	connectTemp.getProgRegNumber(creatorid, entity_id,
			                                  			progID, new AsyncCallback<String[]>(){

			              									public void onFailure(Throwable arg0) {
			              										
			              										MessageBox.alert("Error",
			              										"Failure in getting registration number");
			              										
			              									}

			              									public void onSuccess(String[] result) {
			              										
			              										ProgRegValue = result[0];
			                                                      NewRegNo = result[1] +
			                                                          entity_id.substring(6,
			                                                              8) + "" +
			                                                          progID.substring(5,
			                                                              7) + "" 			                                                          
			                                                          /*
			                                                           * removed as branch code
			                                                           * does not have a fix length
			                                                           */
//			                                                          .substring(1,
//			                                                              3) 
			                                                              + "" +
			                                                          result[0];
			                                                      
			                                                      
			                                                      
			                                                      
			                                                      
			                                                      studentInfoGetter.setRegistrationNumber(NewRegNo);
			                                                      
			                                                      spwt = new String[summaryObj.cb1.length];
			                                                      
			                                                      /*
			                                                       * special weightage flag yes/no
			                                                       */
			                                                      for (int i =
			                                                        0;
			                                                        i < summaryObj.cb1.length;
			                                                        i++) {
			                                                    if (summaryObj.cb1[i].isChecked() == true) {
			                                                    	                                                    
			                                                    	
			                                                        spwt[i] =
			                                                            summaryObj.cb1[i].getName();

			                                                    }
			                                                }
			                                                      
			                                                      /*
			                                                       * staff ward flag yes/no
			                                                       */
			                                                      if (summaryObj.radioSW.isChecked() == true) {
			                                                    	  
			                                                    	  spwt = new String[1];
			                                                      	
			                                                          spwt[0] = "SW";                                                              
			                                                    	  
			                                                      }
			                                                      
			                                                      final String[][] hello =
			                                                        summaryObj.returnString();
			                                                      
			                                                      String[][] paperCodes=null;
			                                                      
			                                                      if (summaryObj.hpPaper.isVisible()) {
			                                                        if (summaryObj.checkpaperCode() == 0) {
			                                                            paperCodes =
			                                                                summaryObj.StudentpaperCode();

			                                                           
			                                                        }
			                                                      }
			                                                      
			                                                      String FirstDeg=null;
			                                                      
			                                                      if (summaryObj.ug.firstDegCodeCB.isVisible()) {
			                                           			   FirstDeg =summaryObj.StudentFD(summaryObj.ug.firstDegCodeCB);
			                                           			   
			                                                      }
			                                                      
			                                                      
			                                                      if (summaryObj.pg.firstDegCodeCB.isVisible()) {
			                                                    	  FirstDeg = summaryObj.StudentFD(summaryObj.pg.firstDegCodeCB);
			                           							   
			                                                      }
			                                                      studentInfoGetter.setSubject_code(subjectCode);
			                                                      connectTemp.setApplicantRecord(
			                                                    		  studentInfoGetter,spwt,hello,paperCodes,FirstDeg,
			                                                    		  new AsyncCallback<String>(){

																	public void onFailure(
																			Throwable arg0) {
																		
																	}

																	public void onSuccess(
																			String arg0) {
																		
																		if(arg0.equalsIgnoreCase("success")){
																			
																			MessageBox.show(new MessageBoxConfig() {
																				{
																				setMsg(msg.alert_successfulentry() +
																						 " " +
																						 msg.yourRegNo() +
																						 " " +
																						 NewRegNo);
																				setButtons(MessageBox.OK);
																				setCallback(new MessageBox.PromptCallback() {
																					public void execute(
																							String btnID,
																							String text) {
																						try {
//																							refresForm(summaryObj);//Add by Devendra
//																							mainVerticalPanel.clear();
																							onModuleLoad1();
																							} catch (Exception e) {
																								System.out
																										.println("exception inside create summary sheet "+e);
																								}
																							}
																					});
																				}
																				});
																			
																		}else{
																			
																			MessageBox.alert("Error","Try Again");
																			mainVerticalPanel.clear();
																			onModuleLoad1();
																			
																			
																		}
																		
																		
																		}
			                                                    	  
			                                                      });
			                                                      

			              									}
			              									});
			                                  	}
			                                      }
			                                  });
									
								}else{
									
									MessageBox.alert("Error","Applicant already exist with the same program Information");
									
								}
								
							}
                    		
                    	});


                    } else {
                        MessageBox.alert(errorMsg, correctEntriesMsg);
                    }
                }
            }); // end of submit button listener

        return mainVerticalPanel;

        //RootPanel.get().add(mainVerticalPanel);
    }
    
    

/**Method to create paging table for program */
void createProgramTable(final String[][]obj,final VerticalPanel panel){
	  RecordDef recordDef1=new RecordDef(  
              new FieldDef[]{  
                      new StringFieldDef("programName"),  
                      new StringFieldDef("programId")
              }  
      );
      PagingMemoryProxy proxy = new PagingMemoryProxy(obj);  
      
      
      ArrayReader reader = new ArrayReader(recordDef1);  
      final Store store1 = new Store(proxy, reader, true);  
      final CheckboxSelectionModel chkmodl= new CheckboxSelectionModel();
      BaseColumnConfig[] columns = new BaseColumnConfig[]{  
              //column ID is company which is later used in setAutoExpandColumn 
     		 new CheckboxColumnConfig(chkmodl),
              new ColumnConfig("Program", "programName", 160, true, null, "programName") 
      };  

      ColumnModel columnModel = new ColumnModel(columns);  
      
      GridPanel grid = new GridPanel();  
      grid.setStore(store1);  
      grid.setColumnModel(columnModel);  
      grid.setSelectionModel(chkmodl);
      grid.setFrame(true);  
      grid.setStripeRows(true);  
      grid.setAutoExpandColumn("programName");  
      grid.setWidth(400);  
      grid.setHeight(250);  
      grid.setTitle("Select Program");  
      grid.setAutoExpandColumn("programName");  
      programList=new ArrayList<String>();      
      chkmodl.addListener(new RowSelectionListenerAdapter() {
    	  @Override
			public void onRowSelect(RowSelectionModel sm, int rowIndex,
					Record record) {				
				final Record record1=sm.getSelected();
				programList.add(record1.getAsString("programId").toString());
					
				if(programList.size()>1){
					MessageBox.alert("Select Only one program");
					for(int i=0;i<programList.size();i++){
						programList.remove(i);
					}
					for(int j=0;j<obj.length;j++){
						sm.deselectRow(j);
					}
				}
				else if(programList.size()==1){
					progID=programList.get(0);
					connectTemp.getSubject(progID, new AsyncCallback<CM_ProgramInfoGetter[]>() {

						@Override
						public void onFailure(Throwable arg0) {
							MessageBox.alert("Error in getSuject "+arg0.getMessage());
						}

						@Override
						public void onSuccess(CM_ProgramInfoGetter[] arg0) {
							subjectObject=new String[arg0.length+1][2];								
							
							if(arg0.length==0){
								subjectObject[0][0]="X";
								subjectObject[0][1]="None";
							}
							else{
								for(int i=0;i<arg0.length;i++){
									subjectObject[i][0]=arg0[i].getSubject_code();
									subjectObject[i][1]=arg0[i].getSubject_description();
								}
							}
								RecordDef recordDef = new RecordDef(new FieldDef[] {
	                                    new StringFieldDef("subjectCode"),
	                                    new StringFieldDef("subjectName")
	                                });
								MemoryProxy proxy = new MemoryProxy(subjectObject);

		                          ArrayReader reader = new ArrayReader(recordDef);
		                          Store store = new Store(proxy, reader);
		                          store.load();
		                          subjectCombo.setStore(store);
		                          Label programLabel=new Label("Program Name  ");
		                          Label programName=new Label(record1.getAsString("programName").toString());
		                          programNamePanel.setSpacing(5);
		                          programNamePanel.add(programLabel);
		                          programNamePanel.add(programName);		                          
		                          subjectPanel.setSpacing(5);		                          
		                          subjectPanel.add(subjectComboLabel);
		                          subjectPanel.add(subjectCombo);
								panel.clear();
								panel.add(programNamePanel);
								panel.add(subjectPanel);
						}
					});
				}
				
			}
			@Override
			public void onRowDeselect(RowSelectionModel sm, int rowIndex,
					Record record) {
				for(int i=0;i<programList.size();i++){
					programList.remove(i);
				}
				for(int j=0;j<obj.length;j++){
					sm.deselectRow(j);
				}
			}

		});
      final PagingToolbar pagingToolbar = new PagingToolbar(store1);  
      pagingToolbar.setPageSize(10);  
      pagingToolbar.setDisplayInfo(true);  
      pagingToolbar.setDisplayMsg("Displaying Records {0} - {1} of {2}");  
      pagingToolbar.setEmptyMsg("No records to display");  

      NumberField pageSizeField = new NumberField();  
      pageSizeField.setWidth(20);  
      pageSizeField.setSelectOnFocus(true);  
      pageSizeField.addListener(new FieldListenerAdapter() {  
          public void onSpecialKey(Field field, EventObject e) {  
              if (e.getKey() == EventObject.ENTER) {  
            	  int pageSize =(int)Float.parseFloat(field.getValueAsString());
                  pagingToolbar.setPageSize(pageSize);  
              }  
          }  
      });  

      ToolTip toolTip = new ToolTip("Enter page size");  
      toolTip.applyTo(pageSizeField);  

      pagingToolbar.addField(pageSizeField);  
      grid.setBottomToolbar(pagingToolbar);  
      store1.load(0, 10);
      panel.add(grid);
}

void getProgramOffered(){
    connectTemp.getOfferedByDetails(progID,
    		universityId,
              new AsyncCallback<CM_entityInfoGetter[]>() {
                  public void onFailure(Throwable arg0) {
                  }

                  public void onSuccess(CM_entityInfoGetter[] result) {
                      if (result.length == 1) {
                          entity_id = result[0].getEntityId();

                          entityName.setVisible(false);
                          entityNameCB.entityCombo.setVisible(false);
                      } else {
                          RecordDef recordDef = new RecordDef(new FieldDef[] {
                                      new StringFieldDef("EntityName"),
                                      new StringFieldDef("EntityCode")
                                  });
                          final String[][] object2;

                          object2 = new String[result.length][2];

                          String str = null;

                          try {
                              for (int i = 0; i < result.length;
                                      i++) {
                                  for (int k = 0; k < 2; k++) {
                                      if (k == 0) {
                                          str = result[i].getEntityName();
                                      } else if (k == 1) {
                                          str = result[i].getEntityId();
                                      }

                                      object2[i][k] = str;
                                  }
                              }
                          } catch (Exception e2) {
                              System.out.println("e2     " + e2);
                          }

                          Object[][] data = object2;

                          MemoryProxy proxy = new MemoryProxy(data);

                          ArrayReader reader = new ArrayReader(recordDef);
                          Store store = new Store(proxy, reader);
                          store.load();
                          entityNameCB.entityCombo.setStore(store);

                          if (entityViewIs == true) {
                              entityNameCB.entityCombo.setVisible(true);
                              entityName.setVisible(true);

                              flex4.setWidget(1, 0, entityName);
                              flex4.setWidget(1, 1,
                                  entityNameCB.entityCombo);
                          } else {
                              entityNameCB.entityCombo.setVisible(true);
                              entityName.setVisible(true);

                              flex3.setWidget(1, 0, entityName);
                              flex3.setWidget(1, 1,
                                  entityNameCB.entityCombo);
                          }

                          entityNameCB.entityCombo.enable();
                          entityNameCB.entityCombo.setVisible(true);

                          entityNameCB.entityCombo.addListener(new ComboBoxListenerAdapter() {
                                  public void onSelect(
                                      ComboBox comboBox,
                                      Record record, int index) {
                                      entity_id = entityNameCB.entityCombo.getValue();

                                      MessageBox.alert(
                                          "entity id in else" +
                                          entity_id);
                                  }
                              });
                      }
                  }
              });
}

    @SuppressWarnings("deprecation")
    void furhterExecution(CM_entityInfoGetter[] arg0) throws Exception {
    	flex3 = new FlexTable();
    	flex4 = new FlexTable();
    	 flex5 = new FlexTable();
        
        Label selectEntityName = new Label("University Name");

        entityNameCB.entityCombo.setEmptyText("Select Entity");
        entityNameCB.entityCombo.setForceSelection(true);
        entityNameCB.entityCombo.setMinChars(1);
        entityNameCB.entityCombo.setDisplayField("EntityName");
        entityNameCB.entityCombo.setValueField("EntityCode");
        entityNameCB.entityCombo.setMode(ComboBox.LOCAL);
        entityNameCB.entityCombo.setTriggerAction(ComboBox.ALL);
        entityNameCB.entityCombo.setLoadingText("Searching...");
        entityNameCB.entityCombo.setTypeAhead(true);
        entityNameCB.entityCombo.setSelectOnFocus(true);
        entityNameCB.entityCombo.setHideTrigger(false);
        entityNameCB.entityCombo.setReadOnly(true);
        entityNameCB.entityCombo.setVisible(false);

      
        final Label entityNameLabel = new Label();
        final CheckBox enrollCheck = new CheckBox();
        enrollCheck.setChecked(false);

        final Label enrollmentNumberLabel = new Label(cons.enrollmentNumber());
        final TextBox enrollmentNumber = new TextBox();
        enrollmentNumberLabel.setVisible(false);
        enrollmentNumber.setMaxLength(10);
        enrollmentNumber.setVisible(false);
        //final Panel gridPanel=new Panel();
        enrollCheck.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent arg0) {
                    if (enrollCheck.isChecked()) {
                        enrollmentNumberLabel.setVisible(true);
                        enrollmentNumber.setEnabled(true);
                        enrollmentNumber.setVisible(true);
                    } else {
                        enrollmentNumberLabel.setVisible(false);
                        enrollmentNumber.setValue("");
                        enrollmentNumber.setVisible(false);
                    }
                }
            });

        if (entityViewIs == true) {
            //default view flextable
            entityNameLabel.setText(arg0[0].getUniversityName());
            System.out.println("inside entityView true");
            universityId = arg0[0].getUniversityCode();
            flex4.setCellSpacing(5);
            flex4.setWidget(0, 0, selectEntityName);
            flex4.setWidget(0, 1, entityNameLabel);
        } else {
        	System.out.println("inside entityView false");
            flex3.setCellSpacing(5);
            //            flex3.setWidget(0, 0, selectEntity);
            //            flex3.setWidget(0, 1, entityTypeCB.entityDescCB);            
            flex3.setWidget(0, 0, selectEntityName);
            flex3.setWidget(0, 1, universityComboBox);           
        }
        flex5.setCellSpacing(5);
        flex5.setWidget(0, 0, new Label(cons.enrolledStudent()));
        flex5.setWidget(0, 1, enrollCheck);
        flex5.setWidget(1, 0, enrollmentNumberLabel);
        flex5.setWidget(1, 1, enrollmentNumber);
        flex5.setWidget(2, 0, okButton);
        
        mainForm.setLabelAlign(Position.TOP);
        mainForm.setTitle(cons.summarySheet());
        mainForm.setPaddings(5);
        mainForm.setWidth("100%");
        mainForm.setFrame(true);
       
       
        if (entityViewIs == true) {            
            int counter = 0;

            /**
             * method unchanged
             */
            connectTemp.getProgrammeOff(universityId, counter,
                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                    public void onFailure(Throwable caught) {
                        MessageBox.alert(dbException, caught.getMessage());
                    }

                    public void onSuccess(CM_ProgramInfoGetter[] arg0) {                       

                        if (arg0.length > 0) {
                            final String[][] object2;

                            object2 = new String[arg0.length][2];
                            programObject=new String[arg0.length][2];

                            try {
                                for (int i = 0; i < arg0.length; i++) {
                                    for (int k = 0; k < 2; k++) {
                                        if (k == 0) {
                                        	object2[i][k] = arg0[i].getProgram_name();
                                        } else if (k == 1) {
                                        	object2[i][k] = arg0[i].getProgram_id();
                                        }
                                        
                                    }
                                }
                                programObject=object2;
                                createProgramTable(programObject, panel);
                            } catch (Exception e2) {
                                System.out.println("e2     " + e2);
                            }
                            
                        } else {
                            MessageBox.alert(msg.emptyComboBox("programmes",
                                    entity_name));
                        }
                    }
                });            
        } else { //if default view is false             
            
            /**
             *  Checking for default entity view
             *  method updated
             */
            connectTemp.checkForDefaultView(new AsyncCallback<CM_entityInfoGetter[]>() {
                    public void onFailure(Throwable caught) {
                        MessageBox.alert(dbException, caught.getMessage());
                    }

                    public void onSuccess(CM_entityInfoGetter[] arg0) {
                        
                        RecordDef recordDef = new RecordDef(new FieldDef[] {
                                    new StringFieldDef("UniversityName"),
                                    new StringFieldDef("UniversityCode")
                                });
                        final String[][] object2;

                        object2 = new String[arg0.length][2];

                        String str = null;

                        try {
                            for (int i = 0; i < arg0.length; i++) {
                                for (int k = 0; k < 2; k++) {
                                    if (k == 0) {
                                        str = arg0[i].getUniversityName();
                                    } else if (k == 1) {
                                        str = arg0[i].getUniversityCode();
                                        
                                    }

                                    object2[i][k] = str;
                                    System.out.println("university id is "+str);
                                }
                            }
                        } catch (Exception e2) {
                            System.out.println("e2     " + e2);
                        }

                        Object[][] data = object2;

                        MemoryProxy proxy = new MemoryProxy(data);

                        ArrayReader reader = new ArrayReader(recordDef);
                        Store store = new Store(proxy, reader);
                        store.load();
                        universityComboBox.setStore(store);
                        
                    }
                });

            /**
            * method unchanged
            */
            universityComboBox.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox comboBox, Record record,
                        int index) {
                        universityId = universityComboBox.getValue();


                        int counter = 0;
                        /**
                        * method unchanged
                        */
                        connectTemp.getProgrammeOff(universityId, counter,
                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert(dbException,
                                        caught.getMessage());
                                }

                                public void onSuccess(
                                    CM_ProgramInfoGetter[] arg0) {
                                    if (arg0.length > 0) {
                                        final String[][] object2;

                                        object2 = new String[arg0.length][2];
                                        programObject=new String[arg0.length][2];
                                        try {
                                            for (int i = 0; i < arg0.length;
                                                    i++) {
                                                for (int k = 0; k < 2; k++) {
                                                    if (k == 0) {
                                                    	object2[i][k] = arg0[i].getProgram_name();
                                                    } else if (k == 1) {
                                                    	object2[i][k] = arg0[i].getProgram_id();
                                                    }
                                                    
                                                }
                                            }
                                            programObject=object2;
                                            createProgramTable(programObject, panel);
                                        } catch (Exception e2) {
                                            System.out.println("e2     " + e2);
                                        }

                                    } else {                                        

                                        MessageBox.alert(msg.emptyComboBox(
                                                "programmes", entity_name));
                                    }
                                }
                            });
                    }
                });
            
        } //End of else of default view check
        
        	subjectCombo.addListener(new ComboBoxListenerAdapter(){
        		public void onSelect(ComboBox comboBox, Record record, int index) {   
        			subjectCode=subjectCombo.getValueAsString();
        			subjectName=subjectCombo.getRawValue();
        			
        			System.out.println("subject code is "+subjectCode+" sujectName is "+subjectName);
        			getProgramOffered();
            }
        	});
            
        if (entityViewIs == true) {            
            mainForm.add(flex4);            

        } else {            

            mainForm.add(flex3);
        }
        mainForm.add(panel);
        mainForm.add(flex5);
        mainForm.add(vp);
        mainVerticalPanel.add(mainForm);

        okButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    int check = 0;
                    

                    if (entityViewIs == false) {
                        if (entity_id == null) {
                            check++;
                            if (entityNameCB.entityCombo.isDisabled() == false) {
                                summaryObj.markIV(entityNameCB.entityCombo);
                            }
                        }
                    } //end of if
                    if(subjectCode.equals("") || subjectCode==null){
                    	 check++;
                    }

                    /*
                     * initial input checks before clicking ok
                     * button
                     */
                    System.out.println("check size is "+check+" creator id is "+creatorid);
                    if (check == 0) {
                        vp.clear();

                        SummarySheet d1 = new SummarySheet(creatorid);
                        summaryObj = d1;
                      

                        /**
                         * method updated
                         */
                        connectTemp.CheckComponents(entity_id, progID,
                            new AsyncCallback<Boolean>() {
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert(dbException,
                                        caught.getMessage());
                                }

                                public void onSuccess(Boolean result) {
                                    if (result == false) {
                                        MessageBox.alert(errorMsg,
                                            msg.noComponentSpecified(Program));
                                    } else {
                                    	/*
                                    	 * generate summary sheet window for adding data of the student
                                    	 */
                                    	subjectCombo.setDisabled(true);
                                    	enrollCheck.setEnabled(false);
                                    	okButton.setDisabled(true);
                                    summaryObj.generateSummarySheet(progID,subjectCode,entity_id);
                                    vp.add(summaryObj.mainScroll);
                                    HorizontalPanel buttonPanel=new HorizontalPanel();
                                    buttonPanel.add(submitButton);
                                    buttonPanel.setSpacing(10);
                                    buttonPanel.add(cancelButton);
                                    vp.add(buttonPanel);
                                        
                                    }
                                }
                            });
                    } else {
                        MessageBox.alert(errorMsg, msg.fieldsReqd());
                    }
                }
            }); // end of ok Button Listener      
    } //end of furtherFunctionality method
    

/**Method to refresh Form
 * Added by Devendra
 */
void refresForm(SummarySheet s){
	s.formNumber.reset();
	s.fname.reset();
	s.mname.reset(); 
	s.lname.reset(); 
	s.fname1.reset(); 
	s.mname1.reset(); 
	s.lname1.reset(); 
	s.fname2.reset(); 
	s.mname2.reset(); 
	s.lname2.reset(); 
	s.street1Text.reset(); 
	s.street2Text.reset(); 
	s.cityCombo.reset(); 
	s.newCat.reset();
	s.gender_obj1.genderCB.reset();
	s.dateofbirthDateField.reset();
	s.newState.reset();
	s.radioNSW.setChecked(true);
	s.pinNumber1.reset();
	for(int i=0;i<s.groupCB.length;i++){
		s.groupCB[i].reset();
	}
	for(int k=0;k<s.ocb1.length;k++){
		s.ocb1[k].boardCB.reset();
	}
	for(int k=0;k<s.cb1.length;k++){
		s.cb1[k].setChecked(false);
	}
	for(int k=0;k<s.t1.length;k++){
		s.t1[k].setBlankText("");
		s.t1[k].reset();
	}
    s.count = 0;
    s.count1 = 0;
    s.count2 = 0;
    s.x1=0;
    s.b = 0;
    s.c = 0;
    s.boardCount = 0;
    s.spWtCount = 0;
    s.newc = 0;
    s.m1 = 0;
    s.papercodeGroupCount = 0;
	s.k1=0;	
}

}


class SummarySheet {
    String dbException;
    String errorMsg;
    String confirm;
    String alertSave;
    String correctEntriesMsg;
    messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    RadioButton radioSW = new RadioButton("S", cons.applicable(), true);
    RadioButton radioNSW = new RadioButton("S", cons.not_Applicable(), true);
    String fName = cons.firstName();
    String mName = cons.middleName();
    String lName = cons.lastName();
    String maxMarks = cons.label_maxmarks();
    String marksObtd = cons.label_marksobtd();
    String percent = cons.percent();
    String chooseDeg = cons.chooseDegree();
    String chooseBoard = cons.chooseBoard();
    String score = cons.score();
    FieldSet fieldSet1 = new FieldSet(cons.personal_details());
    FieldSet fieldSet2 = new FieldSet(cons.addCorrespondence());
    FieldSet fieldSet3 = new FieldSet(cons.if_staffWard());
    FieldSet fieldSet4 = new FieldSet(cons.acad_perform());
    FieldSet fieldSet6 = new FieldSet(cons.test_options());
    FieldSet fieldSet7 = new FieldSet(cons.formNo_regNo());
    Validator valid = new Validator();
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    int count = 0;
    int count1 = 0;
    int count2 = 0;
    int x1;
    int b = 0;
    int c = 0;
    int boardCount = 0;
    int spWtCount = 0;
    int newc = 0;
    int m1 = 0;
    int papercodeGroupCount = 0;
    ComboBox[] groupCB;
    NumberField[] t1 = new NumberField[c];
    CM_ComboBoxes[] ocb1 = new CM_ComboBoxes[boardCount];
    CheckBox[] cb1 = new CheckBox[spWtCount];
    FlexTable listTable = new FlexTable();
    VerticalPanel listVPanel = new VerticalPanel();
    FormPanel mainForm = new FormPanel();
    String Course;
    String[] complabels;
    int k1 = 0;
    String[][] x;
    String[][] newcArr;
    String[][] newS;
    boolean flag = false;
    String creator_id;


    TextField fname = new TextField();
    TextField mname = new TextField();
    TextField lname = new TextField();
    TextField fname1 = new TextField();
    TextField mname1 = new TextField();
    TextField lname1 = new TextField();
    TextField fname2 = new TextField();
    TextField mname2 = new TextField();
    TextField lname2 = new TextField();
    TextField street1Text = new TextField();
    TextField street2Text = new TextField();
    
    TextField formNumber = new TextField(); 
    NumberField pinNumber1 = new NumberField();
    ComboBox newGender = new ComboBox();
    ComboBox newCat = new ComboBox();
    ComboBox newState = new ComboBox();
    ComboBox cityCombo=new ComboBox();
    DateField dateofbirthDateField = new DateField("", "", 190);
    VerticalPanel mainScroll = new VerticalPanel();
    final VerticalPanel vmain = new VerticalPanel();
    final VerticalPanel vpRadioSW = new VerticalPanel();
    HorizontalPanel hpCall = new HorizontalPanel();
    HorizontalPanel hpPaper = new HorizontalPanel();
    final CM_ComboBoxes ug = new CM_ComboBoxes(creator_id);
    final CM_ComboBoxes pg = new CM_ComboBoxes(creator_id);
    String[][] object2;
    int checkCos = 0;
    
    OA_ComboBoxes gender_obj1 = new OA_ComboBoxes();    
    SummarySheet(String userId) {
        this.creator_id = userId;
    }

    @SuppressWarnings("deprecation")
	void generateSummarySheet(final String progID, final String subjectCode, final String entity_id) {
    	System.out.println("inside generate summaysheet subjectcode is "+progID+" : "+subjectCode+" : "+entity_id);
        dbException = cons.dbError();
        errorMsg = msg.error();
        confirm = msg.confirm();
        alertSave = msg.alert_confirmentries();
        correctEntriesMsg = msg.checkFields();
        mainScroll.clear();
        hpPaper.setVisible(false);                                                     
        
        /**
         * method unchanged
         */
        connectTemp.papercodeGroupCount(progID,
            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert(dbException, caught.getMessage());
                }

                public void onSuccess(CM_ProgramInfoGetter[] result) {
                    papercodeGroupCount = result.length;

                    if (papercodeGroupCount == 0) {
                        hpPaper.setVisible(false);
                    } else {
                        groupCB = new ComboBox[papercodeGroupCount];

                        for (int i = 0; i < papercodeGroupCount; i++) {
                            String group = result[i].getGrouping();

                            final ComboBox paperCodeCB = new ComboBox();

                            paperCodeCB.setName(result[i].getGrouping());
                            paperCodeCB.setForceSelection(true);
                            paperCodeCB.setMinChars(1);
                            paperCodeCB.setDisplayField("paperDescription");
                            paperCodeCB.setValueField("paperCode");
                            paperCodeCB.setMode(ComboBox.LOCAL);
                            paperCodeCB.setTriggerAction(ComboBox.ALL);
                            paperCodeCB.setEmptyText("Select");
                            paperCodeCB.setLoadingText("Searching...");
                            paperCodeCB.setTypeAhead(true);
                            paperCodeCB.setSelectOnFocus(true);
                            paperCodeCB.setWidth(130);
                            paperCodeCB.setHideTrigger(false);
                            paperCodeCB.setReadOnly(true);

                            /**
                             * method unchanged
                             */
                            connectTemp.DistinctPaperGroupingCount(progID,
                                group,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable caught) {
                                        MessageBox.alert(dbException,
                                            caught.getMessage());
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] arg0) {
                                        if (arg0.length > 0) {
                                            RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                        new StringFieldDef("paperDescription"),
                                                        new StringFieldDef("paperCode")
                                                    });

                                            object2 = new String[arg0.length][2];

                                            String str = null;

                                            try {
                                                for (int i = 0;
                                                        i < arg0.length; i++) {
                                                    for (int k = 0; k < 2;
                                                            k++) {
                                                        if (k == 0) {
                                                            str = arg0[i].getPaper_description();
                                                        } else if (k == 1) {
                                                            str = arg0[i].getPaper_code();
                                                        }

                                                        object2[i][k] = str;
                                                    }
                                                }
                                            } catch (Exception e2) {
                                                System.out.println("e2     " +
                                                    e2);
                                            }

                                            Object[][] data = object2;

                                            MemoryProxy proxy = new MemoryProxy(data);

                                            ArrayReader reader = new ArrayReader(recordDef);
                                            Store store = new Store(proxy,
                                                    reader);
                                            store.load();

                                            paperCodeCB.setStore(store);
                                        } else {
                                            paperCodeCB.disable();
                                        }
                                    }
                                });
                            groupCB[i] = paperCodeCB;

                            hpPaper.setVisible(true);
                            hpPaper.add(new Label(cons.group() + " " +
                                    groupCB[i].getName() + "*"));
                            hpPaper.add(groupCB[i]);
                        }
                    }
                }
            });

        
        CM_ComboBoxes category_obj1 = new CM_ComboBoxes(creator_id);      

        NumberField pinNumber = new NumberField();

        Label nameLabel = new Label(cons.name());
        Label fnameLabel = new Label(fName+"*");
        Label mnameLabel = new Label(mName);
        Label lnameLabel = new Label(lName);

        Label fathernameLabel = new Label(cons.fatherName());
        Label fnameLabel1 = new Label(fName+"*");
        Label mnameLabel1 = new Label(mName);
        Label lnameLabel1 = new Label(lName);

        Label mothernameLabel = new Label(cons.motherName());
        Label fnameLabel2 = new Label(fName);
        Label mnameLabel2 = new Label(mName);
        Label lnameLabel2 = new Label(lName);

        Label streetlabel1 = new Label(cons.line1());
        Label streetlabel2 = new Label(cons.line2());
        Label citylabel = new Label(cons.city());
        Label statelabel = new Label(cons.state());
        Label pinlabel = new Label(cons.pincode());

        FlexTable flex1 = new FlexTable();
        FlexTable flex2 = new FlexTable();

        gender_obj1.onModuleLoad();
        category_obj1.onModuleLoad();        
        newCat=category_obj1.categoryCB;
        newState=gender_obj1.statesCB; 
        newState.addListener(new ComboBoxListenerAdapter() {			
			@Override
			public void onSelect(ComboBox comboBox, Record record, int index) {					
				cityCombo.clearValue();
				gender_obj1.onStateChange(newState.getRawValue());
								
			}
			
		});
        cityCombo=gender_obj1.cityCB;
        newGender = gender_obj1.genderCB;   

        fieldSet1.setFrame(true);

        fieldSet2.setFrame(true);

        fieldSet3.setFrame(true);

        fieldSet4.setFrame(true);

        fieldSet6.setFrame(true);

        fieldSet7.setFrame(true);

        hpPaper.setSpacing(10);

        fname.setWidth(190);
        mname.setWidth(190);
        lname.setWidth(190);
        // appNo.setWidth(130);
        // regNo.setWidth(130);
        fname.setAllowBlank(false);
        fname.setMaxLength(100);
        fname1.setAllowBlank(false);
        fname1.setMaxLength(100);
//        fname2.setAllowBlank(false);
        fname2.setMaxLength(100);
        // appNo.setAllowBlank(false);
        //regNo.setAllowBlank(false);
        newGender.setAllowBlank(false);
        newCat.setAllowBlank(false);
        dateofbirthDateField.setAllowBlank(false);
        formNumber.setAllowBlank(false);
        formNumber.setMaxLength(5);

        street1Text.setWidth(190);
        street2Text.setWidth(190);
        pinNumber.setWidth(190);
        pinNumber.setAllowDecimals(false);
        pinNumber.setAllowNegative(false);
        pinNumber.setMaxLength(6);
        pinNumber.setMinLength(6);

//        street1Text.setAllowBlank(false);
        street2Text.setWidth(190);
//        pinNumber.setAllowBlank(false);
//        pinNumber.setRegex("{[1-9][0-9][0-9][0-9][0-9][0-9]}");
//        newState.setAllowBlank(false);

        pinNumber1 = pinNumber;

        flex1.setCellSpacing(5);
        
        flex1.setWidget(0, 0, new Label("Faculty Reg. No."+"*"));
        flex1.setWidget(0, 2, formNumber);
        
        flex1.setWidget(2, 0, nameLabel);
        flex1.setWidget(2, 2, fnameLabel);
        flex1.setWidget(2, 4, mnameLabel);
        flex1.setWidget(2, 6, lnameLabel);
        flex1.setWidget(3, 2, fname);
        flex1.setWidget(3, 4, mname);
        flex1.setWidget(3, 6, lname);
        flex1.setWidget(4, 2, new Label(cons.gender()));
        flex1.setWidget(5, 2, newGender);
        flex1.setWidget(4, 4, new Label(cons.dob()));
        flex1.setWidget(5, 4, dateofbirthDateField);
        flex1.setWidget(4, 6, new Label(cons.category()));
        flex1.setWidget(5, 6, newCat);
        flex1.setWidget(6, 0, fathernameLabel);
        flex1.setWidget(6, 2, fnameLabel1);
        flex1.setWidget(6, 4, mnameLabel1);
        flex1.setWidget(6, 6, lnameLabel1);
        flex1.setWidget(7, 2, fname1);
        flex1.setWidget(7, 4, mname1);
        flex1.setWidget(7, 6, lname1);
        flex1.setWidget(8, 0, mothernameLabel);
        flex1.setWidget(8, 2, fnameLabel2);
        flex1.setWidget(8, 4, mnameLabel2);
        flex1.setWidget(8, 6, lnameLabel2);
        flex1.setWidget(9, 2, fname2);
        flex1.setWidget(9, 4, mname2);
        flex1.setWidget(9, 6, lname2);
        fieldSet1.add(flex1);

        flex2.setCellSpacing(5);
        flex2.setWidget(0, 0, streetlabel1);
        flex2.setWidget(0, 1, street1Text);
        flex2.setWidget(0, 2, streetlabel2);
        flex2.setWidget(0, 3, street2Text);        
        flex2.setWidget(1, 0, statelabel);
        flex2.setWidget(1, 1, newState);        
        flex2.setWidget(1, 2, citylabel);
        flex2.setWidget(1, 3, cityCombo);        
        flex2.setWidget(2, 0, pinlabel);
        flex2.setWidget(2, 1, pinNumber1);
        fieldSet2.add(flex2);

        HorizontalPanel hp1 = new HorizontalPanel();
        hp1.setSpacing(20);

        HorizontalPanel hp2 = new HorizontalPanel();
        hp2.setSpacing(10);

        radioNSW.setChecked(true);
        hp2.add(radioSW);
        hp2.add(radioNSW);

        vpRadioSW.add(hp2);
        vpRadioSW.setHeight("40px");

        fieldSet3.add(vpRadioSW);

        fieldSet6.add(hpPaper);

        //fieldSet7.add(flex3);
        final VerticalPanel vp = new VerticalPanel();
        vp.setHeight("100%");
        fieldSet4.add(vp);

        FlexTable newFlex = display(progID, subjectCode,entity_id);

        vp.add(newFlex);

        vmain.setSpacing(10);
        //vmain.add(fieldSet7);
        vmain.add(fieldSet1);
        vmain.add(fieldSet2);
        vmain.add(fieldSet3);
        vmain.add(fieldSet4);
        vmain.add(fieldSet6);

        mainScroll.add(vmain);

        newCat.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    /**
                     * method updated
                     */           
                    connectTemp.getcos_value(progID, entity_id,
                    		newCat.getValue() + "%"+subjectCode,
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable caught) {
                                MessageBox.alert(dbException,
                                    caught.getMessage());
                            }

                            public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                                checkCos = arg0.length;

                                if (arg0.length == 0) {
                                    MessageBox.show(new MessageBoxConfig() {

                                            {
                                                setMsg(msg.noCOSspecified());

                                                setButtons(MessageBox.OK);
                                                setCallback(new MessageBox.PromptCallback() {
                                                        public void execute(
                                                            String btnID,
                                                            String text) {
                                                            try {
                                                                newCat.clearValue();
                                                            } catch (Exception e) {
                                                            }
                                                        }
                                                    });
                                            }
                                        });
                                }
                            }
                        });
                }
            });
               
    }

    public String[] returnBoard() {
        final String[] BoardList = new String[c];

        for (int k = 0; k < boardCount; k++) {
            for (int i = 0; i < c; i++) {
                if (listTable.getText(i + 1, 3).equals(cons.chooseBoard())) {
                    BoardList[i] = ocb1[k].boardCB.getValue();

                    k++;
                }
            }
        }

        return BoardList;
    }

    @SuppressWarnings("deprecation")
    public String[] returnSpwtCB() {
        final String[] checklist = new String[c];

        for (int k = 0; k < boardCount; k++) {
            for (int i = 0; i < c; i++) {
                if (cb1[k].isChecked() == true) {
                    checklist[i] = cb1[k].getName();

                    k++;
                }
            }
        }

        return checklist;
    }

    public String[][] returnString() {
        final String[][] data1 = new String[c][5];

        final String[] AppCoaList = new String[c];

        for (int k = 1; k < (c + 1); k++) {
            AppCoaList[k - 1] = t1[k - 1].getRawValue();

            final int s1 = k - 1;

            final String[] data = new String[5];

            String Component = listTable.getText(k, 0);

            for (int i = 0; i < newS.length; i++) {
                if (newS[i][0].equals(Component)) {
                    Component = newS[i][5];
                }
            }

            final String Component1 = listTable.getText(k, 1);

            data[0] = Component;

            if (Component1.equals("(" + percent + ")")) {
                data[1] = AppCoaList[s1];
            } else {
                data[1] = "0";
            }

            if (Component1.equals("(" + marksObtd + ")") ||
                    Component1.equals("(Marks)")) {
                data[2] = AppCoaList[s1];
            } else {
                data[2] = "0";
            }

            if (Component1.equals("(" + maxMarks + ")")) {
                data[3] = AppCoaList[s1];
            } else {
                data[3] = "0";
            }

            if (Component1.equals("(" + score + ")")) {
                data[4] = AppCoaList[s1];
            } else {
                data[4] = "0";
            }

            data1[s1] = data;
        }

        return newFunc(data1);
    }

    public String[][] newFunc(String[][] data1) {
        String[][] data11 = new String[c][5];

        data11 = data1;

        String[] newBoard = returnBoard();

        for (int i = 0; i < (c - 1); i++) {
            if (data11[i][0].equalsIgnoreCase(data11[i + 1][0])) {
                newc++;
            }
        }

        newcArr = new String[c - newc][6];

        for (int i = 0; i < (c - 1); i++) {
            if (data11[i][0].equalsIgnoreCase(data11[i + 1][0])) {
                k1++;

                newcArr[k1 - 1][0] = data1[i][0];

                newcArr[k1 - 1][5] = newBoard[i];

                for (int j1 = 1; j1 < 5; j1++) {
                    float val = Float.parseFloat(data11[i][j1]) +
                        Float.parseFloat(data11[i + 1][j1]);
                    newcArr[k1 - 1][j1] = "" + val;
                }
            }
        }

        int newk = k1 * 2;

        int k2 = k1;

        for (int i = newk; i < c; i++) {
            k2++;

            newcArr[k2 - 1][0] = data1[i][0];
            newcArr[k2 - 1][5] = newBoard[i];

            for (int j1 = 1; j1 < 5; j1++) {
                newcArr[k2 - 1][j1] = data11[i][j1];
            }
        }

        for (int i = 0; i < (c - newc); i++)
            for (int j1 = 0; j1 < 6; j1++) {
                float x = Float.parseFloat(newcArr[i][2]);
                float y = Float.parseFloat(newcArr[i][3]);

                if ((x > 0) && (y > 0)) {
                    float z = (x / y) * 100;
                    newcArr[i][1] = "" + z;
                }
            }

        return newcArr;
    }

    public FlexTable display(final String progID, final String subjectCode,final String entity_id) {
        Label[] headings = new Label[6];

        headings[0] = new Label(cons.component());
        headings[1] = new Label(cons.criteria());
        headings[2] = new Label(cons.value());
        headings[3] = new Label(cons.board_degree());
        headings[4] = new Label(cons.value());
        headings[5] = new Label(cons.special_weightage());

        listTable.clear();
        listTable.setBorderWidth(0);
        listTable.setCellSpacing(10);
        listTable.setWidget(0, 0, headings[0]);
        listTable.setWidget(0, 1, headings[1]);
        listTable.setWidget(0, 2, headings[2]);
        listTable.setWidget(0, 3, headings[3]);
        listTable.setWidget(0, 4, headings[4]);
        listTable.setWidget(0, 5, headings[5]);

        /**
         * method updated
         */
        connectTemp.getProgramComponents(progID, entity_id,
            new AsyncCallback<String[][]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert(dbException, caught.getMessage());
                }

                public void onSuccess(String[][] result) {
                    if (result.length == 0) {
                    } 
                    else {
                        count = result.length;
                        ug.FirstDegree(progID, "U");
                        pg.FirstDegree(progID, "P");

                        for (int i = 0; i < count; i++) {
                            if (result[i][3].equals("Y")) {
                                result[i][3] = chooseBoard;
                                boardCount = boardCount + 1;
                            } else {
                                result[i][3] = "";
                            }
                        }

                        for (int i = 0; i < count; i++) {
                            if (result[i][1].equals("M")) {
                                b = b + 1;
                            }
                        }

                        for (int i = 0; i < count; i++)
                            if (result[i][6].equals("Y")) {
                                result[i][6] = result[i][5];
                                spWtCount = spWtCount + 1;
                            } else {
                                result[i][6] = "";
                            }

                        x = new String[2 * b][7];

                        int bt = 0;

                        for (int i = 0; i < count; i++) {
                            if (result[i][1].equals("M")) {
                                x[bt][0] = result[i][0];
                                x[bt][1] = marksObtd;
                                x[bt + 1][0] = result[i][0];
                                x[bt + 1][1] = maxMarks;
                                x[bt][2] = result[i][2];
                                x[bt][3] = result[i][3];
                                x[bt][4] = result[i][4];
                                x[bt][5] = result[i][5];
                                x[bt][6] = result[i][6];
                                bt = bt + 2;
                            }
                        }

                        c = count + b;

                        newS = new String[c][7];

                        for (int b1 = 0; b1 < bt; b1++) {
                            newS[b1][0] = x[b1][0];
                            newS[b1][1] = x[b1][1];
                            newS[b1][2] = x[b1][2];
                            newS[b1][3] = x[b1][3];
                            newS[b1][4] = x[b1][4];
                            newS[b1][5] = x[b1][5];
                            newS[b1][6] = x[b1][6];
                        }

                        for (int j = bt; j < c; j++) {
                            for (int i = 0; i < count; i++) {
                                if (result[i][1].equals("P")) {
                                    newS[j][0] = result[i][0];
                                    newS[j][1] = percent;
                                    newS[j][2] = result[i][2];
                                    newS[j][3] = result[i][3];
                                    newS[j][4] = result[i][4];
                                    newS[j][5] = result[i][5];
                                    newS[j][6] = result[i][6];
                                    j++;
                                }

                                if (result[i][1].equals("S")) {
                                    newS[j][0] = result[i][0];
                                    newS[j][1] = score;
                                    newS[j][2] = result[i][2];
                                    newS[j][3] = result[i][3];
                                    newS[j][4] = result[i][4];
                                    newS[j][5] = result[i][5];
                                    newS[j][6] = result[i][6];
                                    j++;
                                }
                            }
                        }

                        for (int i = 0; i < c; i++) {
                            listTable.setWidget(i + 1, 0, new Label(newS[i][0]));

                            listTable.setWidget(i + 1, 1,
                                new Label("(" + newS[i][1] + ")"));

                            listTable.setWidget(i + 1, 3, new Label(newS[i][3]));

                            listTable.setWidget(i + 1, 5, new Label(newS[i][6]));
                        }

                        CM_ComboBoxes[] ocb = new CM_ComboBoxes[boardCount];

                        for (int k = 0; k < boardCount; k++) {
                            for (int i = 0; i < c; i++)
                                if (listTable.getText(i + 1, 3)
                                                 .equals(chooseBoard)) {
                                    ocb[k] = new CM_ComboBoxes(creator_id);
                                    ocb[k].BoardName();

                                    ocb[k].boardCB.setName(listTable.getText(i +
                                            1, 0));

                                    listTable.setWidget(i + 1, 4, ocb[k].boardCB);
                                    k++;
                                }
                        }

                        ocb1 = ocb;

                        CheckBox[] cb2 = new CheckBox[spWtCount];

                        for (int k = 0; k < spWtCount; k++) {
                            for (int i = 0; i < c; i++)

                                if (listTable.getText(i + 1, 5).equals("") == false) {
                                    cb2[k] = new CheckBox();
                                    cb2[k].setName(listTable.getText(i + 1, 5));
                                    listTable.getCellFormatter()
                                             .setHorizontalAlignment(i + 1, 5,
                                        HasAlignment.ALIGN_CENTER);
                                    listTable.setWidget(i + 1, 5, cb2[k]);

                                    k++;
                                }
                        }

                        cb1 = cb2;

                        NumberField[] t = new NumberField[c];

                        t1 = t;

                        for (int k = 0; k < c; k++) {
                            t[k] = new NumberField();

                            t1[k] = new NumberField();
                            t[k].setAllowNegative(false);

                            t[k].setMaxLength(7);

                            t1[k] = t[k];
                            t[k].setWidth(130);
                            t[k].setAllowBlank(false);

                            listTable.setWidget(k + 1, 2, t[k]);
                        }

                        for (int i = 0; i < (c - 1); i++) {
                            if (listTable.getText(i + 1, 0)
                                             .contains("Under Graduate")) {
                                if (listTable.getText(i + 2, 0)
                                                 .contains("Under Graduate")) {
                                    listTable.setWidget(i + 1, 3,
                                        (new Label(chooseDeg)));
                                    listTable.setWidget(i + 1, 4,
                                        (ug.firstDegCodeCB));
                                }
                            }

                            if (listTable.getText(i + 1, 0)
                                             .contains("Post Graduate")) {
                                if (listTable.getText(i + 2, 0)
                                                 .contains("Post Graduate")) {
                                    listTable.setWidget(i + 1, 3,
                                        (new Label(chooseDeg)));
                                    listTable.setWidget(i + 1, 4,
                                        (pg.firstDegCodeCB));
                                }
                            }
                        }
                    }
                }
            });

        return listTable;
    }

    public int CheckBoard(CM_ComboBoxes[] basicComboBoxSamples) {
        int check = 0;

        for (int i = 0; i < basicComboBoxSamples.length; i++)
            if (valid.nullValidator(
                        basicComboBoxSamples[i].boardCB.getRawValue())) {
                try {
                    check++;

                    basicComboBoxSamples[i].boardCB.markInvalid("");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        return check;
    }

    public int checkAcad() {
        int check = 0;

        for (int i = 1; i < listTable.getRowCount(); i++) {
            try {
                t1[i - 1].validate();
            } catch (Exception e) {
                check++;
                System.out.println(check);
            }

            try {
                if (Float.parseFloat(t1[i - 1].getRawValue()) <= 0.0) {
                    check++;
                    t1[i - 1].markInvalid("");
                }
            } catch (Exception e) {
            }
        }

        return check;
    }

    @SuppressWarnings("static-access")
    public String[] studentInfo() {
        final String[] student = new String[17];

        student[0] = fname.getRawValue();
        student[1] = mname.getRawValue();
        student[2] = lname.getRawValue();
        student[3] = newGender.getRawValue();
        student[4] = newCat.getValue();        

        DateUtil d = new DateUtil();
        student[5] = d.format(dateofbirthDateField.getValue(), "Y-m-d");
        student[6] = fname1.getRawValue();
        student[7] = mname1.getRawValue();
        student[8] = lname1.getRawValue();
        student[9] = fname2.getRawValue();
        student[10] = mname2.getRawValue();
        student[11] = lname2.getRawValue();
        //student[12]= appNo.getRawValue();
        student[12] = null;
        //	student[13]= regNo.getRawValue();
        student[13] = null;
        student[16] = newCat.getRawValue();
        System.out.println("category is "+newCat.getRawValue()+" "+newCat.getValue());
        return student;
    }

    public int checkAddress() {
        int check = 0;

        if (valid.nullValidator(cityCombo.getRawValue())){ 
        		
        	/*
        	 * validation for address details
        	 */
//        		||
//                valid.nullValidator(newState.getRawValue()) ||
//                valid.nullValidator(pinNumber1.getRawValue())) {
            check = 1;
        } else {
            check = 0;
        }

        try {
            pinNumber1.validate();
        } catch (Exception e) {
            check = 1;
        }

        return check;
    }

    public String[] addressInfo(String StuId) {
        String[] address = new String[8];

        address[0] = StuId;
        address[1] = street1Text.getRawValue();
        address[5] = street2Text.getRawValue();
        address[2] = cityCombo.getRawValue();
        address[3] = newState.getRawValue();
        address[4] = pinNumber1.getRawValue();

        return address;
    }

    public int checkpaperCode() {
        int check = 0;

        for (int i = 0; i < groupCB.length; i++) {
            if (valid.nullValidator(groupCB[i].getRawValue())) {
                try {
                    check++;
                    groupCB[i].markInvalid("");
                } catch (Exception e) {
                }
            }
        }

        return check;
    }

    public String[][] StudentpaperCode() {
        String[][] paperCode = null;

        paperCode = new String[groupCB.length][2];

        for (int i = 0; i < groupCB.length; i++) {
            paperCode[i][0] = groupCB[i].getValue();
            paperCode[i][1] = groupCB[i].getName();
        }

        return paperCode;
    }

    public String StudentFD(ComboBox c) {
        return c.getValue();
    }

    public int checkUG() {
        int check = 0;

        if (ug.firstDegCodeCB.isVisible() &&
                (valid.nullValidator(ug.firstDegCodeCB.getRawValue()))) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    public int checkPG() {
        int check = 0;

        if (pg.firstDegCodeCB.isVisible() &&
                (valid.nullValidator(pg.firstDegCodeCB.getRawValue()))) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    public int checkPersonal() {
        int check = 0;

        if (valid.nullValidator(fname.getText()) ||
                valid.nullValidator(DateUtil.format(
                        dateofbirthDateField.getValue(), "Y-m-d")) ||
                valid.nullValidator(newCat.getRawValue()) ||
                valid.nullValidator(fname1.getText())
                ||valid.nullValidator(formNumber.getText()) || valid.nullValidator(newGender.getRawValue())){
        	/*
        	 * mother name
        	 */
//                ||
//                valid.nullValidator(fname2.getText())) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    public int checkDate() {
        Date currentDate = new Date();

        int check = 0;

        Date dateofbirth = dateofbirthDateField.getValue();

        if (valid.nullValidator(dateofbirthDateField.getRawValue())) {
            try {
                check++;
                dateofbirthDateField.markInvalid("");
            } catch (Exception e) {
            }
        } else {
            if (valid.datechecker1(dateofbirth, currentDate) == true) {
                try {
                    check++;
                    dateofbirthDateField.markInvalid("");
                } catch (Exception e) {
                }
            }
        }

        return check;
    }

    public String[] DuplicacyCheck(String Reg) {
        String[] checkData = new String[6];

        checkData[0] = Reg;
        checkData[1] = fname.getText();
        checkData[2] = lname.getText();
        checkData[3] = DateUtil.format(dateofbirthDateField.getValue(), "Y-m-d");
        checkData[4] = fname1.getText();
        checkData[5] = newCat.getRawValue();

        return checkData;
    }

    public int markIV(TextField t) {
        int check = 0;

        if ((valid.nullValidator(t.getValueAsString()) == true)) {
            try {
                check++;
                t.markInvalid("");
            } catch (Exception e) {
            }
        }

        return check;
    }

    public void markIVChars(TextField t) {
    	
        if (valid.checkText(t) == 1) {
            try {
                t.markInvalid("");
            } catch (Exception e) {
            }
        }
    }

    public int marksCheck() {
        int check = 0;

        for (int i = 1; i < listTable.getRowCount(); i++) {
            if (listTable.getText(i, 1).equals("(" + marksObtd + ")")) {
                float x = Float.parseFloat(t1[i - 1].getRawValue());
                float y = Float.parseFloat(t1[i].getRawValue());

                if ((x > 0) && (y > 0) && ((x / y) > 1)) {
                    try {
                        check++;
                        t1[i - 1].markInvalid("");
                        t1[i].markInvalid("");
                    } catch (Exception e) {
                    }
                }
            }

            if (listTable.getText(i, 1).equals("(" + percent + ")")) {
                float x = Float.parseFloat(t1[i - 1].getRawValue());

                if ((x > 0) && (x > 100.00)) {
                    try {
                        check++;
                        t1[i - 1].markInvalid("");
                    } catch (Exception e) {
                    }
                }
            }
        }

        return check;
    }
}
