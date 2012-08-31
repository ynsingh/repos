package in.ac.dei.edrp.client.Applications;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.Shared.CM_ComboBoxes;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;


public class ManageSummarySheet {
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    String progID;
    String Program;
    String RegNo;
    String facultyRegno;
    String StudId;
    String FormNo;
   // String branchName;
   // String branchId;
    String entity_id;
   // String specialization_id;
    //String specialization_name;
    String[] address;
    String[][] callData = null;
    String entity;
    int x = 0;
    String ug;
    String pg;
    boolean flag = true;
    String modifierid;
    Date d1 = new Date();
    Validator valid = new Validator();
    VerticalPanel mainVerticalPanel = new VerticalPanel();
    messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    String dbException = cons.dbError();
    String errorMsg = msg.error();
    String confirm = msg.confirm();
    String alertSave = msg.alert_confirmentries();
    String correctEntriesMsg = msg.checkFields();
    String xCOS = cons.xCOS();
    String universityName;
    boolean flag1 = true;
    CM_ComboBoxes programCB;
    String pagename = "Apply for Admission";
    String[] values;
    Label label = new Label();
//    Button searchButton = new Button("Edit");
//    Button deleteButton = new Button("Delete");
    
    public ManageSummarySheet(String userid) {
        this.modifierid = userid;
    }

    public VerticalPanel onModuleLoad1() {
    	
        final SummarySheet summaryObj = new SummarySheet(modifierid);

        mainVerticalPanel.clear();

        mainVerticalPanel.setWidth("900px");

        //mainVerticalPanel.setHeight("500px");
        HorizontalPanel hp = new HorizontalPanel();

        final VerticalPanel vp = new VerticalPanel();

        final FlexTable flex3 = new FlexTable();
        Label selectEntityName = new Label(cons.entityName());
        Label uniLabel = new Label("University Name");
        final CM_ComboBoxes basic = new CM_ComboBoxes(modifierid);
        final ComboBox entityCombo = new ComboBox();
        final TextField entityTF = new TextField();
        final TextField uniTextField = new TextField();
        final TextField programTF = new TextField();
       // final TextField branchTF = new TextField();
        //final TextField textField = new TextField();
        //final ComboBox branchCombo = new ComboBox();
        final Button editButton = new Button(cons.button_update());
        final Button deleteButton = new Button("Delete");
        final Button delButton = new Button("Delete");
        final Button searchButton = new Button(cons.edit());
        final Button resetButton = new Button(cons.resetButton());

        final FormPanel mainForm = new FormPanel();
        final TextField appNo = new TextField();
        final TextField regNo = new TextField();
        
        final Label programLabel = new Label("Program Name*");
        programCB = new CM_ComboBoxes(modifierid);
        
        searchButton.disable();
        deleteButton.disable();
        
        loginconnect.getAuthorityOnPage(pagename, modifierid,
                new AsyncCallback<CM_userInfoGetter[]>() {
                    public void onFailure(Throwable arg0) {
                    }

                    public void onSuccess(CM_userInfoGetter[] result) {
                        if (result[0].getAuthority().equalsIgnoreCase("1")) {
                            loginconnect.getsecondaryauthorities(modifierid,
                                pagename,
                                new AsyncCallback<List<String>>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(List<String> result) {
                                        if (result.size() == 0) {
                                        	
                                        	deleteButton.enable();
                                        	searchButton.enable();
                                        	
                                        } else {
                                            values = new String[result.size()];

                                            for (int i = 0; i < result.size();
                                                    i++) {
                                                values[i] = result.get(i);

                                                if (values[i].equalsIgnoreCase(
                                                            "view")) {
                                                	
                                                }

                                                if (values[i].equalsIgnoreCase(
                                                            "update")) {
                                                	
                                                	searchButton.enable();
                                                	
                                                }

                                                if (values[i].equalsIgnoreCase(
                                                            "delete")) {
                                                	
                                                	deleteButton.enable();
                                                	
                                                }
                                            }
                                        }
                                    }
                                });
                        }
                    }
                });
        
        
        programCB.onModuleLoad();

        regNo.setAllowBlank(false);
        
        basic.onModuleLoad();
        entityCombo.disable();
        appNo.setWidth(130);
        regNo.setWidth(130);

        entityTF.setReadOnly(true);
        programTF.setReadOnly(true);
       // branchTF.setReadOnly(true);
        uniTextField.setReadOnly(true);
        //textField.setReadOnly(true);
        
        label.setVisible(false);

        hp.setSpacing(20);
        hp.add(programLabel);
        hp.add(programCB.progCombo);
        hp.add(new Label("Faculty " +cons.regNo()));
        hp.add(regNo);
        hp.add(searchButton);
        hp.add(deleteButton);
        hp.add(resetButton);
        
        VerticalPanel verticalPanel = new VerticalPanel();
        VerticalPanel verticalPanel2 = new VerticalPanel();
        

        verticalPanel.add(label);
        
        verticalPanel2.add(verticalPanel);
        verticalPanel2.add(hp);
        
        flex3.setCellSpacing(5);

        flex3.setWidget(1, 0, uniLabel);
        flex3.setWidget(1, 1, uniTextField);
        flex3.setWidget(1, 2, selectEntityName);
        flex3.setWidget(1, 3, entityTF);
        flex3.setWidget(2, 0, new Label(cons.label_programname()));
        flex3.setWidget(2, 1, programTF);
//        flex3.setWidget(2, 2, new Label(cons.label_branchname()));
//        flex3.setWidget(2, 3, branchTF);
//        flex3.setWidget(3, 0, new Label("Specialization Name"));
//        flex3.setWidget(3, 1, textField);

        mainForm.setLabelAlign(Position.TOP);
        mainForm.setTitle(cons.manageSumSheet());
        mainForm.setPaddings(5);
        mainForm.setWidth("100%");
        mainForm.setFrame(true);

        mainForm.add(verticalPanel2);

        mainForm.add(vp);

        mainVerticalPanel.add(mainForm);
        
        connectTemp.getProgrammeOff(modifierid, 2, new AsyncCallback<CM_ProgramInfoGetter[]>(){

			public void onFailure(Throwable arg0) {
				
			}

			public void onSuccess(CM_ProgramInfoGetter[] arg0) {
				
				 RecordDef recordDef = new RecordDef(new FieldDef[] {
                         new StringFieldDef("Prog"),
                         new StringFieldDef("ProgCode")
                     });

             if (arg0.length > 0) {
                 final String[][] object2;

                 object2 = new String[arg0.length][2];

                 String str = null;

                 try {
                     for (int i = 0; i < arg0.length;
                             i++) {
                         for (int k = 0; k < 2; k++) {
                             if (k == 0) {
                                 str = arg0[i].getProgram_name();
                             } else if (k == 1) {
                                 str = arg0[i].getProgram_id();
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

                 programCB.progCombo.setStore(store);
                 programCB.progCombo.enable();
             }
				
			}
        	
        });

        deleteButton.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	
            	flag1 = false;
            	
            	deleteButton.setDisabled(true);
            	searchButton.fireEvent("Click");
            	summaryObj.fname.setReadOnly(true);
            	summaryObj.fname1.setReadOnly(true);
            	summaryObj.fname2.setReadOnly(true);
            	summaryObj.mname.setReadOnly(true);
            	summaryObj.mname1.setReadOnly(true);
            	summaryObj.mname2.setReadOnly(true);
            	summaryObj.lname.setReadOnly(true);
            	summaryObj.lname1.setReadOnly(true);
            	summaryObj.lname2.setReadOnly(true);            	
            	summaryObj.dateofbirthDateField.disable();
            	summaryObj.street1Text.setReadOnly(true);
            	summaryObj.street2Text.setReadOnly(true);
            	//summaryObj.cityText.setReadOnly(true);
            	summaryObj.pinNumber1.setReadOnly(true);
            	summaryObj.gender_obj1.genderCB.disable();
            	summaryObj.newState.disable();
            	summaryObj.cityCombo.disable();
           	}
        });
        
        resetButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    onModuleLoad1();
                    flag1=true;
                }
            });

        searchButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                	
                	if(programCB.progCombo.getValue()==null){               		
                		
                		MessageBox.alert("Error","Enter the program name");
                		programCB.progCombo.markInvalid("error");
                		deleteButton.setDisabled(false);
                		
                	}else if(valid.nullValidator(regNo.getText())){
            			
            			MessageBox.alert("Error","Enter registration number");
            			regNo.markInvalid("error");
            			deleteButton.setDisabled(false);
            			
            		}else{
                    
                	deleteButton.setDisabled(true);
                	programCB.progCombo.disable();
                	regNo.disable();

                    facultyRegno = regNo.getRawValue();
                    
                    connectTemp.getRegistrationNumber(programCB.progCombo.getValue(),
                    		facultyRegno,new AsyncCallback<String>(){

						public void onFailure(Throwable arg0) {
							
						}

						public void onSuccess(String arg0) {
							

							if(arg0==null){
								MessageBox.alert(" No registration number found");
								resetButton.fireEvent("Click");
								
								deleteButton.setDisabled(false);
								
							}else
							{								
								searchButton.disable();
			                    regNo.setReadOnly(true);
			                    vp.clear();
			                    
//								MessageBox.alert("registration number"+arg0);
							
							RegNo = arg0;
							
							/**
                             * method updated
                             */
                            connectTemp.getStudentProgBranch(RegNo,
                                new AsyncCallback<CM_ProgramInfoGetter>() {
                                    public void onFailure(
                                        Throwable caught) {
                                        MessageBox.alert(dbException,
                                            caught.getMessage());
                                    }

                                    public void onSuccess(
                                        final CM_ProgramInfoGetter arg02) {
                                        progID = arg02.getProgram_id();
                                        //branchId = arg02.getBranch_code();
                                        Program = arg02.getProgram_name();
                                       // branchName = arg02.getBranch_name();
                                        entity = arg02.getEntity_name();
                                        entity_id = arg02.getEntity_id();
//                                        specialization_name = arg02.getSpecialization_name();
//                                        specialization_id = arg02.getSpecialization_id();
                                        universityName = arg02.getUniversity_name();

                                        vp.add(flex3);
                                        entityTF.setValue(entity);
                                        programTF.setValue(Program);
                                        //branchTF.setValue(branchName);
                                        uniTextField.setValue(universityName);
                                        //textField.setValue(specialization_name);

                                        summaryObj.generateSummarySheet(progID,"",
                                             entity_id);
                                        summaryObj.fieldSet7.setVisible(false);
                                        vp.add(summaryObj.mainScroll);
                                        if(flag1 == false){
                                        	
                                        	label.setText("Delete Mode");
                                        	label.setVisible(true);
                                        	
                                        	vp.add(delButton);                                        	
                                        }else{
                                        	
                                        	label.setText("Edit Mode");
                                        	label.setVisible(true);
                                        	
                                        	vp.add(editButton);
                                        }
                                        

                                        /**
                                         * method unchanged
                                         */
                                        connectTemp.getStudentPersonalInfo(RegNo,
                                            new AsyncCallback<CM_StudentInfoGetter[]>() {
                                                public void onFailure(
                                                    Throwable caught) {
                                                    MessageBox.alert(dbException,
                                                        caught.getMessage());
                                                }

                                                public void onSuccess(
                                                    CM_StudentInfoGetter[] arg0) {
                                                	try{
                                                	summaryObj.formNumber.setValue(facultyRegno);
                                                	summaryObj.formNumber.setReadOnly(true);
                                                    summaryObj.fname.setValue(arg0[0].getFirst_name());
                                                    summaryObj.mname.setValue(arg0[0].getMiddle_name());
                                                    summaryObj.lname.setValue(arg0[0].getLast_name());
                                                    summaryObj.fname1.setValue(arg0[0].getFather_Fname());
                                                    summaryObj.mname1.setValue(arg0[0].getFather_Mname());
                                                    summaryObj.lname1.setValue(arg0[0].getFather_Lname());
                                                    summaryObj.fname2.setValue(arg0[0].getMother_Fname());
                                                    summaryObj.mname2.setValue(arg0[0].getMother_Mname());
                                                    summaryObj.lname2.setValue(arg0[0].getMother_Lname());
                                                    summaryObj.newGender.setValue(arg0[0].getGender());                                                    
                                                    summaryObj.newCat.setValue(arg0[0].getCategory());
                                                    summaryObj.dateofbirthDateField.setValue(arg0[0].getDate_of_birth());
                                                	}catch (Exception e) {
                                                		System.out
																.println(e);
                                                		MessageBox.alert("Please try again!!!!");
                                                		onModuleLoad1();
													}
                                                    StudId = arg0[0].getUser_id();

                                                    address = summaryObj.addressInfo(StudId);

                                                    /**
                                                     * method unchanged
                                                     */
                                                    connectTemp.getStudentAddressInfo(StudId,
                                                        new AsyncCallback<CM_entityInfoGetter[]>() {
                                                            public void onFailure(
                                                                Throwable caught) {
                                                                MessageBox.alert(dbException,
                                                                    caught.getMessage());
                                                            }

                                                            public void onSuccess(
                                                                CM_entityInfoGetter[] arg0) {
                                                                summaryObj.street1Text.setValue(arg0[0].getAddress_line1());
                                                                summaryObj.street2Text.setValue(arg0[0].getAddress_line2());
                                                                summaryObj.cityCombo.setValue(arg0[0].getCity());
                                                                summaryObj.pinNumber1.setValue(arg0[0].getPincode());
                                                                summaryObj.newState.setValue(arg0[0].getState());
                                                                
                                                                if (summaryObj.hpPaper.isVisible()) {
                                                                    /**
                                                                         * method unchanged
                                                                         */
                                                                    connectTemp.getStudentPaperInfo(RegNo,
                                                                        progID,
                                                                        new AsyncCallback<CM_StudentInfoGetter[]>() {
                                                                            public void onFailure(
                                                                                Throwable caught) {
                                                                                MessageBox.alert(dbException,
                                                                                    caught.getMessage());
                                                                            }

                                                                            public void onSuccess(
                                                                                CM_StudentInfoGetter[] arg0) {
                                                                                if (arg0.length > 0) {
                                                                                    for (int k =
                                                                                            0;
                                                                                            k < summaryObj.groupCB.length;
                                                                                            k++)
                                                                                        for (int i =
                                                                                                0;
                                                                                                i < arg0.length;
                                                                                                i++) {
                                                                                            if (summaryObj.groupCB[k].getName()
                                                                                                                         .equals(arg0[i].getGrouping())) {
                                                                                                summaryObj.groupCB[k].setValue(arg0[i].getPapercode());
                                                                                            }
                                                                                            
                                                                                            if(flag1== false){
                                                                                            	
                                                                                            	summaryObj.groupCB[k].disable();
                                                                                            	
                                                                                            }
                                                                                            
                                                                                        }
                                                                                }
                                                                            }
                                                                        });
                                                                }
                                                                
                                                                /**
                                                                 * method unchanged
                                                                 */
                                                                connectTemp.getStudentFD(RegNo,
                                                                    progID, "U",
                                                                    new AsyncCallback<CM_StudentInfoGetter[]>() {
                                                                        public void onFailure(
                                                                            Throwable caught) {
                                                                            MessageBox.alert(dbException,
                                                                                caught.getMessage());
                                                                        }

                                                                        public void onSuccess(
                                                                            CM_StudentInfoGetter[] arg0) {
                                                                            if (arg0.length > 0) {
                                                                                ug = arg0[0].getProgramId();

                                                                                summaryObj.ug.firstDegCodeCB.setValue(ug);
                                                                            }
                                                                            
                                                                            if(flag1 == false){
                                                                            	
                                                                            	summaryObj.ug.firstDegCodeCB.disable();                                                                           	
                                                                            	
                                                                            }                                                                            
                                                                            
                                                                            /**
                                                                             * method unchanged
                                                                             */
                                                                            connectTemp.getStudentFD(RegNo,
                                                                                progID, "P",
                                                                                new AsyncCallback<CM_StudentInfoGetter[]>() {
                                                                                    public void onFailure(
                                                                                        Throwable caught) {
                                                                                        MessageBox.alert(dbException,
                                                                                            caught.getMessage());
                                                                                    }

                                                                                    public void onSuccess(
                                                                                        CM_StudentInfoGetter[] arg0) {
                                                                                        if (arg0.length > 0) {
                                                                                            pg = arg0[0].getProgramId();

                                                                                            summaryObj.pg.firstDegCodeCB.setValue(pg);
                                                                                        }
                                                                                        if(flag1 == false){
                                                                                        	
                                                                                        	summaryObj.pg.firstDegCodeCB.disable();
                                                                                        	
                                                                                        }
                                                                                        if (flag == true) {
                                                                                            /**
                                                                                                 * method unchanged
                                                                                                 */
                                                                                            connectTemp.getStudentCallListInfo(RegNo,
                                                                                                new AsyncCallback<CM_StudentInfoGetter[]>() {
                                                                                                    public void onFailure(
                                                                                                        Throwable caught) {
                                                                                                        MessageBox.alert(dbException,
                                                                                                            caught.getMessage());
                                                                                                    }

                                                                                                    public void onSuccess(
                                                                                                        final CM_StudentInfoGetter[] arg0) {
                                                                                                        if (arg0.length > 0) {
                                                                                                            final String[][] callData =
                                                                                                                new String[arg0.length][5];

                                                                                                            if (summaryObj.listTable.getRowCount() > 0) {
                                                                                                                for (int v =
                                                                                                                        1;
                                                                                                                        v < summaryObj.listTable.getRowCount();
                                                                                                                        v++) {
                                                                                                                    final int f =
                                                                                                                        v -
                                                                                                                        1;

                                                                                                                    final String Component1 =
                                                                                                                        summaryObj.listTable.getText(v,
                                                                                                                            1);
                                                                                                                    final String Id =
                                                                                                                        summaryObj.listTable.getText(v,
                                                                                                                            0);

                                                                                                                    for (int i =
                                                                                                                            0;
                                                                                                                            i < arg0.length;
                                                                                                                            i++) {
                                                                                                                        final int x =
                                                                                                                            i;

                                                                                                                        callData[x][0] = arg0[x].getComponentID();

                                                                                                                        final float per =
                                                                                                                            Float.parseFloat(arg0[x].getMarksPercentage());
                                                                                                                        final int marks =
                                                                                                                            Integer.parseInt(arg0[x].getMarksObtained());
                                                                                                                        final int total =
                                                                                                                            Integer.parseInt(arg0[x].getTotalMarks());
                                                                                                                        final int score =
                                                                                                                            Integer.parseInt(arg0[x].getScore());

                                                                                                                        final String board =
                                                                                                                            arg0[x].getBoard_id();

                                                                                                                        if (board != null) {
                                                                                                                            for (int x1 =
                                                                                                                                    0;
                                                                                                                                    x1 < summaryObj.ocb1.length;
                                                                                                                                    x1++) {
                                                                                                                                final int x11 =
                                                                                                                                    x1;

                                                                                                                                String ComponentName =
                                                                                                                                    summaryObj.ocb1[x11].boardCB.getName();

                                                                                                                                /**
                                                                                                                                 * method unchanged
                                                                                                                                 */
                                                                                                                                connectTemp.getComponentId(ComponentName,
                                                                                                                                    new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                                                                        public void onFailure(
                                                                                                                                            Throwable caught) {
                                                                                                                                            MessageBox.alert(dbException,
                                                                                                                                                caught.getMessage());
                                                                                                                                        }

                                                                                                                                        public void onSuccess(
                                                                                                                                            CM_ProgramInfoGetter[] result) {
                                                                                                                                            if (result[0].getComponent_id()
                                                                                                                                                             .equals(arg0[x].getComponentID())) {
                                                                                                                                                summaryObj.ocb1[x11].boardCB.setValue(board);
                                                                                                                                            }
                                                                                                                                            if(flag1==false){
                                                                                                                                            	
                                                                                                                                            	summaryObj.ocb1[x11].boardCB.
                                                                                                                                            	setReadOnly(true);
                                                                                                                                            	
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });
                                                                                                                            }
                                                                                                                        }

                                                                                                                        /**
                                                                                                                         * method unchanged
                                                                                                                         */
                                                                                                                        connectTemp.getComponentId(Id,
                                                                                                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                                                                public void onFailure(
                                                                                                                                    Throwable caught) {
                                                                                                                                    MessageBox.alert(dbException,
                                                                                                                                        caught.getMessage());
                                                                                                                                }

                                                                                                                                public void onSuccess(
                                                                                                                                    CM_ProgramInfoGetter[] arg01) {
                                                                                                                                    String WtId =
                                                                                                                                        arg01[0].getComponent_id();

                                                                                                                                    if (Component1.equals(
                                                                                                                                                "(" +
                                                                                                                                                summaryObj.percent +
                                                                                                                                                ")") &&
                                                                                                                                            WtId.equals(
                                                                                                                                                callData[x][0])) {
                                                                                                                                        if (per > 0) {
                                                                                                                                            summaryObj.t1[f].setValue(per);
                                                                                                                                        }
                                                                                                                                        
                                                                                                                                        if(flag1==false){
                                                                                                                                        	
                                                                                                                                        	summaryObj.t1[f].
                                                                                                                                        	setReadOnly(true);
                                                                                                                                        	
                                                                                                                                        }
                                                                                                                                        
                                                                                                                                    } else if (Component1.equals(
                                                                                                                                                "(" +
                                                                                                                                                summaryObj.marksObtd +
                                                                                                                                                ")") &&
                                                                                                                                            WtId.equals(
                                                                                                                                                callData[x][0])) {
                                                                                                                                        if (marks > 0) {
                                                                                                                                            summaryObj.t1[f].setValue(marks);
                                                                                                                                        }
                                                                                                                                        if(flag1==false){
                                                                                                                                        	
                                                                                                                                        	summaryObj.t1[f].
                                                                                                                                        	setReadOnly(true);
                                                                                                                                        	
                                                                                                                                        }
                                                                                                                                    } else if (Component1.equals(
                                                                                                                                                "(" +
                                                                                                                                                summaryObj.maxMarks +
                                                                                                                                                ")") &&
                                                                                                                                            WtId.equals(
                                                                                                                                                callData[x][0])) {
                                                                                                                                        if (total > 0) {
                                                                                                                                            summaryObj.t1[f].setValue(total);
                                                                                                                                        }
                                                                                                                                        
                                                                                                                                        if(flag1==false){
                                                                                                                                        	
                                                                                                                                        	summaryObj.t1[f].
                                                                                                                                        	setReadOnly(true);
                                                                                                                                        	
                                                                                                                                        }
                                                                                                                                    } else if (Component1.equals(
                                                                                                                                                "(" +
                                                                                                                                                summaryObj.score +
                                                                                                                                                ")") &&
                                                                                                                                            WtId.equals(
                                                                                                                                                callData[x][0])) {
                                                                                                                                        if (score > 0) {
                                                                                                                                            summaryObj.t1[f].setValue(score);
                                                                                                                                        }
                                                                                                                                        if(flag1==false){
                                                                                                                                        	
                                                                                                                                        	summaryObj.t1[f].
                                                                                                                                        	setReadOnly(true);
                                                                                                                                        	
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            });
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                        
                                                                                        
                                                                                        /**
                                                                                         * method unchanged
                                                                                         */
                                                                                        connectTemp.getStudentSpWt(RegNo,
                                                                                            progID,
                                                                                            new AsyncCallback<CM_StudentInfoGetter[]>() {
                                                                                                public void onFailure(
                                                                                                    Throwable caught) {
                                                                                                    MessageBox.alert(dbException,
                                                                                                        caught.getMessage());
                                                                                                }

                                                                                                @SuppressWarnings("deprecation")
                                                                                                public void onSuccess(
                                                                                                    final CM_StudentInfoGetter[] result) {
                                                                                                    x = result.length;

                                                                                                    for (int i =
                                                                                                            0;
                                                                                                            i < summaryObj.cb1.length;
                                                                                                            i++) {
                                                                                                        summaryObj.cb1[i].setChecked(false);
                                                                                                        
                                                                                                        if(flag1==false){
                                                                                                        	
                                                                                                        	summaryObj.cb1[i].setEnabled(false);
                                                                                                        	
                                                                                                        }
                                                                                                        
                                                                                                        
                                                                                                    }

                                                                                                    if (result.length > 0) {
                                                                                                        for (int i =
                                                                                                                0;
                                                                                                                i < result.length;
                                                                                                                i++) {
                                                                                                            if (result[i].getWeightageID()
                                                                                                                             .equals("SW") == false) {
                                                                                                                summaryObj.radioSW.setChecked(false);

                                                                                                                summaryObj.radioNSW.setChecked(true);
                                                                                                            }
                                                                                                        }

                                                                                                        for (int i =
                                                                                                                0;
                                                                                                                i < summaryObj.cb1.length;
                                                                                                                i++) {
                                                                                                            //													
                                                                                                            for (int j =
                                                                                                                    0;
                                                                                                                    j < result.length;
                                                                                                                    j++) {
                                                                                                                //															
                                                                                                                if (summaryObj.cb1[i].getName()
                                                                                                                                         .equals(result[j].getWeightageID())) {
                                                                                                                    summaryObj.cb1[i].setChecked(true);
                                                                                                                }

                                                                                                                if (result[j].getWeightageID()
                                                                                                                                 .equals("SW")) {
                                                                                                                    summaryObj.radioSW.setChecked(true);
                                                                                                                }     
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            });                                                                                
                                                                                    }
                                                                                });                                                                     
                                                                        }
                                                                    });                                                                
                                                            }
                                                        });                                       
                                                }
                                            });
                                    }
                                });
						}
						}
                    	
                    });
                }
                    
                    

//                    /**
//                     * method unchanged
//                     */
//                    connectTemp.checkReg(RegNo,
//                        new AsyncCallback<Integer>() {
//                            public void onFailure(Throwable caught) {
//                                MessageBox.alert(dbException,
//                                    caught.getMessage());
//                            }
//
//                            public void onSuccess(Integer result) {
//                                if (result > 0) {
//                                    
//                                } else {
//                                    MessageBox.alert(errorMsg, msg.regNotFound());
//                                    searchButton.enable();
//                                    regNo.setReadOnly(false);
//                                }
//                            }
//                        });
                }
            });

        editButton.addListener(new ButtonListenerAdapter() {
                @SuppressWarnings("deprecation")
                public void onClick(Button button, EventObject e) {
                    final String[] student = summaryObj.studentInfo();
System.out.println("inside edit button click");
                    student[12] = facultyRegno;
                    student[13] = RegNo;
                    //branchName = branchCombo.getValue();

//                    RegNo = student[13];
//                    FormNo = student[12];
//                    RegNo = student[13];
//                    FormNo = student[13];
                    

                    if (valid.nullValidator(RegNo)) {
                        Window.alert(msg.regNotFound());
                    } else {
                        int check = 0;

//                        if (summaryObj.checkChars() > 0) {
//                            summaryObj.markIVChars(summaryObj.fname);
//                            summaryObj.markIVChars(summaryObj.mname);
//                            summaryObj.markIVChars(summaryObj.lname);
//                            summaryObj.markIVChars(summaryObj.fname1);
//                            summaryObj.markIVChars(summaryObj.mname1);
//                            summaryObj.markIVChars(summaryObj.lname1);
//                            summaryObj.markIVChars(summaryObj.fname2);
//                            summaryObj.markIVChars(summaryObj.mname2);
//                            summaryObj.markIVChars(summaryObj.lname2);
//                            summaryObj.markIVChars(summaryObj.cityText);
//
//                            check++;
//                        }
                        System.out.println("inside edit button else check 0");
                        if (summaryObj.checkPersonal() > 0) {
                            summaryObj.markIV(summaryObj.fname);
                            summaryObj.markIV(summaryObj.dateofbirthDateField);
                            summaryObj.markIV(summaryObj.newCat);
                            summaryObj.markIV(summaryObj.fname1);
//                            summaryObj.markIV(summaryObj.fname2);
                            summaryObj.markIV(summaryObj.newGender);

                            check++;
                        }

                        if (summaryObj.checkDate() > 0) {
                            check++;
                        }

                        if (summaryObj.checkAddress() > 0) {
//                            summaryObj.markIV(summaryObj.street1Text);
                            summaryObj.markIV(summaryObj.cityCombo);
//                            summaryObj.markIV(summaryObj.newState);
//                            summaryObj.markIV(summaryObj.pinNumber1);
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

                        if (check == 0) {
                        	
                        	MessageBox.confirm(confirm,
		                              "Are you sure you want to edit " +
		                              "the information of the selected student?",
		                              new MessageBox.ConfirmCallback() {
		                                  public void execute(
		                                      String btnID) {
		                                      if (btnID.matches(
		                                                  "yes")) {
		                                    	  
		                                    	  /**
		                                           * method unchanged
		                                           */
		                                          connectTemp.UpdatePersonalInfo(student, modifierid,
		                                              new AsyncCallback<String>() {
		                                                  public void onFailure(Throwable caught) {
		                                                      MessageBox.alert(dbException,
		                                                          caught.getMessage());
		                                                  }

		                                                  public void onSuccess(String arg0) {
		                                                  }
		                                              });

		                                          if (summaryObj.checkAddress() == 0) {
		                                              String[] address1 = summaryObj.addressInfo(RegNo);

		                                              /**
		                                               * method unchanged
		                                               */
		                                              connectTemp.UpdateaddressInfo(address1,
		                                                  modifierid,
		                                                  new AsyncCallback<String>() {
		                                                      public void onFailure(Throwable caught) {
		                                                          MessageBox.alert(dbException,
		                                                              caught.getMessage());
		                                                      }

		                                                      public void onSuccess(String arg0) {
		                                                      }
		                                                  });
		                                          }

		                                          if (summaryObj.hpPaper.isVisible()) {
		                                              /**
		                                                   * method unchanged
		                                                   */
		                                              connectTemp.getStudentPaperInfo(RegNo, progID,
		                                                  new AsyncCallback<CM_StudentInfoGetter[]>() {
		                                                      public void onFailure(Throwable caught) {
		                                                          MessageBox.alert(dbException,
		                                                              caught.getMessage());
		                                                      }

		                                                      public void onSuccess(
		                                                          CM_StudentInfoGetter[] arg0) {
		                                                          if (arg0.length > 0) {
		                                                              final String[][] paperCodes = summaryObj.StudentpaperCode();

		                                                              for (int i = 0;
		                                                                      i < summaryObj.groupCB.length;
		                                                                      i++) {
		                                                                  final String PaperCode = paperCodes[i][0];

		                                                                  final String PaperGroup = paperCodes[i][1];

		                                                                  /**
		                                                                   * method unchanged
		                                                                   */
		                                                                  connectTemp.UpdatepaperCode(RegNo,
		                                                                      arg0[i].getPapercode(),
		                                                                      PaperCode, modifierid,
		                                                                      PaperGroup,
		                                                                      new AsyncCallback<String>() {
		                                                                          public void onFailure(
		                                                                              Throwable caught) {
		                                                                              MessageBox.alert(dbException,
		                                                                                  caught.getMessage());
		                                                                          }

		                                                                          public void onSuccess(
		                                                                              String result) {
		                                                                          }
		                                                                      });
		                                                              }
		                                                          }
		                                                      }
		                                                  });
		                                          }
		                                          
		                                          /**
		                                         * method unchanged
		                                         */
		                                        connectTemp.deleteStudentSpWt(
		                                            RegNo,
		                                            new AsyncCallback<String>() {
		                                                public void onFailure(
		                                                    Throwable caught) {
		                                                    MessageBox.alert(dbException,
		                                                        caught.getMessage());
		                                                }

		                                                public void onSuccess(String result) {
		                                                }
		                                            });

		                                          for (int i = 0; i < summaryObj.cb1.length; i++) {
		                                              if (summaryObj.cb1[i].isChecked() == true &&
		                                              		summaryObj.radioNSW.isChecked() == true) {
		                                              	
		                                              	
		                                              	
		                                                  /**
		                                                   * method unchanged
		                                                   */
		                                                  connectTemp.studentSpWt(progID, RegNo,
		                                                      summaryObj.cb1[i].getName(), entity_id,
		                                                      modifierid,
		                                                      new AsyncCallback<String>() {
		                                                          public void onFailure(
		                                                              Throwable caught) {
		                                                              MessageBox.alert(dbException,
		                                                                  caught.getMessage());
		                                                          }

		                                                          public void onSuccess(String arg0) {
		                                                          }
		                                                      });
		                                              }                  
		                                              
//		                                              else {
//		                                                  /**
//		                                                   * method unchanged
//		                                                   */
//		                                                  connectTemp.deleteStudentSpWt(
//		                                                      RegNo,
//		                                                      new AsyncCallback<String>() {
//		                                                          public void onFailure(
//		                                                              Throwable caught) {
//		                                                              MessageBox.alert(dbException,
//		                                                                  caught.getMessage());
//		                                                          }
		              //
//		                                                          public void onSuccess(String result) {
//		                                                          }
//		                                                      });
//		                                              }
		                                          }

		                                          if (summaryObj.radioSW.isChecked() == true) {           	
		                                          	
		                                          	
		                                          	/**
		                                               * method unchanged
		                                               */
		                                              connectTemp.deleteStudentSpWt(RegNo,
		                                                  new AsyncCallback<String>() {
		                                                      public void onFailure(
		                                                          Throwable caught) {
		                                                          MessageBox.alert(dbException,
		                                                              caught.getMessage());
		                                                      }

		                                                      public void onSuccess(String result) {
		                                                      }
		                                                  });
		                                          	
		                                              /**
		                                                   * method unchanged
		                                                   */
		                                              connectTemp.studentSpWt(progID, RegNo, "SW",
		                                                  entity_id, modifierid,
		                                                  new AsyncCallback<String>() {
		                                                      public void onFailure(Throwable caught) {
		                                                          MessageBox.alert(dbException,
		                                                              caught.getMessage());
		                                                      }

		                                                      public void onSuccess(String arg0) {
		                                                      }
		                                                  });
		                                          } 
		                                          
//		                                          else {
//		                                              /**
//		                                                * method unchanged
//		                                                */
//		                                              connectTemp.deleteStudentSpWt(RegNo,
//		                                                  new AsyncCallback<String>() {
//		                                                      public void onFailure(Throwable caught) {
//		                                                          MessageBox.alert(dbException,
//		                                                              caught.getMessage());
//		                                                      }
		              //
//		                                                      public void onSuccess(String result) {
//		                                                      }
//		                                                  });
//		                                          }

		                                          if (summaryObj.checkAcad() == 0) {
		                                              final String[][] hello = summaryObj.returnString();

		                                              for (int i = 0; i < hello.length; i++) {
		                                                  /**
		                                                   * method unchanged
		                                                   */
		                                                  connectTemp.UpdatecallListInfo(RegNo,
		                                                      hello[i], modifierid,
		                                                      new AsyncCallback<String>() {
		                                                          public void onFailure(
		                                                              Throwable caught) {
		                                                              MessageBox.alert(dbException,
		                                                                  caught.getMessage());
		                                                          }

		                                                          public void onSuccess(String arg0) {
		                                                          }
		                                                      });
		                                              }
		                                          }

		                                          if (summaryObj.ug.firstDegCodeCB.isVisible()) {
		                                              final String Firstdeg = summaryObj.StudentFD(summaryObj.ug.firstDegCodeCB);

		                                              /**
		                                               * method unchanged
		                                               */
		                                              connectTemp.UpdateStudentFD(RegNo, Firstdeg,
		                                                  "U", modifierid,
		                                                  new AsyncCallback<String>() {
		                                                      public void onFailure(Throwable caught) {
		                                                          MessageBox.alert(dbException,
		                                                              caught.getMessage());
		                                                      }

		                                                      public void onSuccess(String result) {
		                                                      }
		                                                  });
		                                          }

		                                          if (summaryObj.pg.firstDegCodeCB.isVisible()) {
		                                              final String PGdeg = summaryObj.StudentFD(summaryObj.pg.firstDegCodeCB);

		                                              /**
		                                               * method unchanged
		                                               */
		                                              connectTemp.UpdateStudentFD(RegNo, PGdeg, "P",
		                                                  modifierid,
		                                                  new AsyncCallback<String>() {
		                                                      public void onFailure(Throwable caught) {
		                                                          MessageBox.alert(dbException,
		                                                              caught.getMessage());
		                                                      }

		                                                      public void onSuccess(String result) {
		                                                      }
		                                                  });
		                                          }

		                                          String catCode = student[4] + "%";

		                                          /**
		                                           * method updated
		                                           */
		                                          connectTemp.getcos_value(progID,
			                                              entity_id, catCode,
			                                              new AsyncCallback<CM_ProgramInfoGetter[]>() {
			                                                  public void onFailure(Throwable caught) {
			                                                      MessageBox.alert(dbException,
			                                                          caught.getMessage());
			                                                  }

			                                                  public void onSuccess(
			                                                      CM_ProgramInfoGetter[] arg0) {
			                                                	  
			                                                      String Stu_Cos=null;
			                                                      
			                                                      if(arg0.length == 0){
			                                                      	
			                                                    	  Stu_Cos = xCOS;
			                                                      	
			                                                      }else if (arg0.length==1 && arg0[0].getCos_value().charAt(2)=='X') {
			                                                    	  Stu_Cos = arg0[0].getCos_value();
			                                                      }else{
			                                                      	for(CM_ProgramInfoGetter info : arg0){		                                                      		
			                                                      		
			                                                        if (info.getCos_value().charAt(
			                                                                    2) == summaryObj.newGender.getValue().charAt(0)) {
			                                                        	Stu_Cos = info.getCos_value();
			                                                      	  break;
			                                                        }            	
			                                                      }
			                                                      }
//			                                                      if (arg0.length == 0) {
//			                                                          Stu_Cos = xCOS;
//			                                                      } else {
//			                                                          String COS = arg0[0].getCos_value();
	//
//			                                                          if (COS.charAt(2) == 'X') {
//			                                                              Stu_Cos = COS;
//			                                                          } else if (COS.charAt(2) == student[3].charAt(
//			                                                                      0)) {
//			                                                              Stu_Cos = COS;
//			                                                          } else {
//			                                                              Stu_Cos = xCOS;
//			                                                          }
//			                                                      }

			                                                      /**
			                                                       * method unchanged
			                                                       */
			                                                      connectTemp.UpdateStudentReg(RegNo,
			                                                          Stu_Cos, modifierid,
			                                                          new AsyncCallback<String>() {
			                                                              public void onFailure(
			                                                                  Throwable caught) {
			                                                                  MessageBox.alert(dbException,
			                                                                      caught.getMessage());
			                                                              }

			                                                              public void onSuccess(
			                                                                  String result) {
			                                                              }
			                                                          });
			                                                  }
			                                              });

		                                          
		                                          MessageBox.alert("Success", msg.alert_oneditsuccess());
		                                          mainVerticalPanel.clear();
                                                  onModuleLoad1();		                                          
		                                      }
		                                  }
                          });                            
                        } else
                        {
                            MessageBox.alert(errorMsg, correctEntriesMsg);
                        }
                    }
                }
            });
        
        /*
         * button to delete the record of the concerned form number
         */
        delButton.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	
            	MessageBox.confirm(confirm,
                        "Are you sure you want to delete " +
                        "the record of the student?",
                        new MessageBox.ConfirmCallback() {
                            public void execute(
                                String btnID) {
                                if (btnID.matches(
                                            "yes")) {
                                	
                                	connectTemp.deleteStudentRecord(RegNo,new AsyncCallback<Boolean>(){

                    					public void onFailure(Throwable arg0) {
                    						
                    					}

                    					public void onSuccess(Boolean arg0) {                    						
                    						if(arg0==false){
                    							
                    							MessageBox.alert("Success",
                    									"Record deleted successfully");
                    							onModuleLoad1();
                    							
                    						}else{
                    							
                    							MessageBox.alert(errorMsg,
                    									"Error in deleting records");
                    							onModuleLoad1();
                    							
                    						}
                    					}
                                		
                                	});
                                	
                                }
                            }
            	});            	
            }
        });

        return mainVerticalPanel;
    }
}
