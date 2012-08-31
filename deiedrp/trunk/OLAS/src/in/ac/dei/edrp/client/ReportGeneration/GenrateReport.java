/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

/*
   Author Name :Devendra Singhal
 */
package in.ac.dei.edrp.client.ReportGeneration;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.RPCFiles.GreetingServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

public class GenrateReport {
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
	
	private final constants constant = GWT.create(constants.class);
	private final messages message = GWT.create(messages.class);
	
	public VerticalPanel outerPanel = new VerticalPanel();
	String userId = "";

	public GenrateReport(String userID) {
		this.userId = userID;
	}
	public void methodGenerateReport(String buttonName){
		outerPanel.clear();			
		final FlexTable upperFlexTable = new FlexTable();
		final VerticalPanel vStudentPanel = new VerticalPanel();
		final Panel panel = new Panel();
		
		//update button computes marks and make candidate eligible or ineligible  with called
        //flag either '-' or 'n'
        final Button updateButton = new Button(constant.computeMarks());

        //report button generates call list according to cut_off_marks and make called flag 'y' or 'n'
        final Button reportButton = new Button(constant.internalCallList());

        //generated test number (status='eligible' and called='y')
        final Button testNumberButton = new Button(constant.testNumber());

        //Generated list includes test number
        final Button meritButton = new Button(constant.externalCallList());

        //Generates selected and waiting student and updates their status by selected/disqualify(if absent)/waiting 
        final Button finalMeritButton = new Button(constant.finalStudentList());

        //You can start it from compute marks.
        final Button resetButton = new Button(constant.reset());
        
		final ComboBox entityTypeCBox = new ComboBox();
		final ComboBox entityNameCBox = new ComboBox();
		final ComboBox programNameCBox = new ComboBox();
		final ComboBox subjectCodeCBox = new ComboBox();
	    final ComboBox genderCBox = new ComboBox();
	         
		final Button cancelButton=new Button(constant.cancelButton()); 
		final Label entityTypeLabel = new Label(constant.entityType());
		final Label entityNameLabel = new Label(constant.entityName());
		final Label programNameLabel = new Label(constant.programme());
		final Label subjectLabel = new Label(constant.subjectCode());
        final Label genderDependencyLabel = new Label(constant.showGender());
	
		entityTypeCBox.setEmptyText(constant.emptyEntityType());
		entityNameCBox.setEmptyText(constant.emptyEntityName());
		programNameCBox.setEmptyText(constant.emptyProgramName());
		subjectCodeCBox.setEmptyText(constant.selectSubject());
		genderCBox.setEmptyText(constant.selectGender());
		
		subjectCodeCBox.setForceSelection(true);
        subjectCodeCBox.setMinChars(1);
        subjectCodeCBox.setFieldLabel("Subject Code");
        subjectCodeCBox.setDisplayField("subject_description");
        subjectCodeCBox.setValueField("subject_code");
        subjectCodeCBox.setMode(ComboBox.LOCAL);
        subjectCodeCBox.setTriggerAction(ComboBox.ALL);
        subjectCodeCBox.setLoadingText("Loading...");
        subjectCodeCBox.markInvalid(constant.marksInvalid());
        subjectCodeCBox.setTypeAhead(true);
        
        genderCBox.setForceSelection(true);
        genderCBox.setMinChars(1);
        genderCBox.setFieldLabel("Gender Dependency");
        genderCBox.setDisplayField("description");
        genderCBox.setValueField("value");
        genderCBox.setMode(ComboBox.LOCAL);
        genderCBox.setTriggerAction(ComboBox.ALL);
        genderCBox.setLoadingText("Loading...");
        genderCBox.markInvalid(constant.marksInvalid());
        genderCBox.setTypeAhead(true);
        
        entityTypeCBox.setForceSelection(true);
        entityTypeCBox.setMinChars(1);
        entityTypeCBox.setFieldLabel("Entity Type");
        entityTypeCBox.setDisplayField("entity_description");
        entityTypeCBox.setValueField("entity_type");
        entityTypeCBox.setMode(ComboBox.LOCAL);
        entityTypeCBox.setTriggerAction(ComboBox.ALL);
        entityTypeCBox.setLoadingText("Loading...");
        entityTypeCBox.markInvalid(constant.marksInvalid());
        entityTypeCBox.setTypeAhead(true);
        entityTypeCBox.setSelectOnFocus(true);
        entityTypeCBox.setHideTrigger(false);

        entityNameCBox.setForceSelection(true);
        entityNameCBox.setMinChars(1);
        entityNameCBox.setFieldLabel("Entity Name");
        entityNameCBox.setDisplayField("entity_name");
        entityNameCBox.setValueField("entity_id");
        entityNameCBox.setMode(ComboBox.LOCAL);
        entityNameCBox.setTriggerAction(ComboBox.ALL);
        entityNameCBox.setLoadingText("Loading...");
        entityNameCBox.setTypeAhead(true);
        entityNameCBox.setSelectOnFocus(true);
        entityNameCBox.setHideTrigger(false);
        
        programNameCBox.setForceSelection(true);
        programNameCBox.setMinChars(1);
        programNameCBox.setFieldLabel("Program");
        programNameCBox.setDisplayField("program_name");
        programNameCBox.setValueField("program_id");
        programNameCBox.setMode(ComboBox.LOCAL);
        programNameCBox.setTriggerAction(ComboBox.ALL);
        programNameCBox.setLoadingText("Loading...");
        programNameCBox.markInvalid(constant.marksInvalid());
        programNameCBox.setTypeAhead(true);
        programNameCBox.setSelectOnFocus(true);
        programNameCBox.setHideTrigger(false);
	
        connectService.methodentitypopulate(userId,
                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                    String[][] object2;
                    public void onFailure(Throwable arg0) {
                        MessageBox.alert(message.failure() + ":", arg0.getMessage());
                    }
                    public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                        RecordDef recordDef = new RecordDef(new FieldDef[] {
                                    new StringFieldDef("entity_type"),
                                    new StringFieldDef("entity_description")
                                });
                        object2 = new String[arg0.length][2];
                        for (int i = 0; i < arg0.length; i++) {
                            object2[i][0] = arg0[i].getComponentId();
                            object2[i][1] = arg0[i].getComponentDescription();
                        }
                        MemoryProxy proxy = new MemoryProxy(object2);
                        ArrayReader reader = new ArrayReader(recordDef);
                        Store store = new Store(proxy, reader);
                        store.load();
                        entityTypeCBox.setStore(store);
                    }
                });

            entityTypeCBox.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox comboBox, Record record, int index) {
                        connectService.methodgetentitytype(comboBox.getValue(), userId,
                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                String[][] object2;
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(message.failure() + ":",
                                        arg0.getMessage());
                                }
                                public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("entity_name"),
                                                new StringFieldDef("entity_id")
                                            });

                                    object2 = new String[arg0.length][2];
                                    for (int i = 0; i < arg0.length; i++) {
                                        object2[i][0] = arg0[i].getEntity_name();
                                        object2[i][1] = arg0[i].getEntity_id();
                                    }
                                    MemoryProxy proxy = new MemoryProxy(object2);
                                    ArrayReader reader = new ArrayReader(recordDef);
                                    Store store = new Store(proxy, reader);
                                    store.load();
                                    entityNameCBox.clearValue();
                                    programNameCBox.clearValue();
                                    entityNameCBox.setStore(store);
                                }
                            });
                    }
                });

            entityNameCBox.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox comboBox, final Record record,
                        int index) {
                        int counter = 1;
                        connectTemp.getProgrammeOff(comboBox.getValue(), counter,
                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                String[][] object2;
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(message.failure() + ":",
                                        arg0.getMessage());
                                }
                                public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("program_name"),
                                                new StringFieldDef("program_id")
                                            });
                                    object2 = new String[arg0.length][2];
                                    for (int i = 0; i < arg0.length; i++) {
                                        object2[i][0] = arg0[i].getProgram_name();
                                        object2[i][1] = arg0[i].getProgram_id();
                                    }
                                    MemoryProxy proxy = new MemoryProxy(object2);
                                    ArrayReader reader = new ArrayReader(recordDef);
                                    Store store = new Store(proxy, reader);
                                    store.load();
                                    programNameCBox.clearValue();
                                    programNameCBox.setStore(store);
                                }
                            });
                    }
                });
            programNameCBox.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox comboBox, Record record, int index) {
                        connectTemp.getSubject(comboBox.getValue(),         
                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                String[][] object2;
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(message.failure() + ":",
                                        arg0.getMessage());
                                }
                                public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                    		  new StringFieldDef("subject_description"),
                                              new StringFieldDef("subject_code")
                                            });
                                    object2 = new String[arg0.length + 1][2];
                                    if(arg0.length==0){
                                    	 object2[0][0] = "None";
                                         object2[0][1] = "X";
                                    }
                                    else{
                                    	 object2[0][0] = "All";
                                         object2[0][1] = "All";
                                         for (int i = 1; i < (arg0.length + 1); i++) {
                                         	System.out.println("result Size is "+arg0[i - 1].getSubject_code() +" : "+arg0[i - 1].getSubject_description());
                                         	object2[i][0] = arg0[i - 1].getSubject_description();//Add by Devendra
                                             object2[i][1] = arg0[i - 1].getSubject_code();//Add by Devendra
                                         }
                                    }                                                              
                                    MemoryProxy proxy = new MemoryProxy(object2);
                                    ArrayReader reader = new ArrayReader(recordDef);
                                    Store store = new Store(proxy, reader);
                                    store.load();
                                    subjectCodeCBox.clearValue();//Add by Devendra
                                    subjectCodeCBox.setStore(store);//Add by Devendra
                                }
                            });
                    }
                });
            subjectCodeCBox.addListener(new ComboBoxListenerAdapter(){
            	public void onSelect(ComboBox comboBox, Record record, int index){
            		 String[][] object2;
            		 RecordDef recordDef = new RecordDef(new FieldDef[] {
                             new StringFieldDef("description"),
                             new StringFieldDef("value")
                         });
            		 object2 = new String[2][2];
            		 object2[0][0] = "YES";
                     object2[0][1] = "Y";
                     object2[1][0] = "No";
                     object2[1][1] = "N";
                     MemoryProxy proxy = new MemoryProxy(object2);

                     ArrayReader reader = new ArrayReader(recordDef);
                     Store store = new Store(proxy, reader);
                     store.load();
                     genderCBox.clearValue();
                     genderCBox.setStore(store);
            	}
            });
            
		
		cancelButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				outerPanel.clear();			
			}
			
		});
		
		upperFlexTable.setWidget(0, 0, entityTypeLabel);
        upperFlexTable.setWidget(0, 1, entityTypeCBox);
        upperFlexTable.setWidget(1, 0, entityNameLabel);
        upperFlexTable.setWidget(1, 1, entityNameCBox);
        upperFlexTable.setWidget(2, 0, programNameLabel);
        upperFlexTable.setWidget(2, 1, programNameCBox); 
        upperFlexTable.setWidget(3, 0, subjectLabel);
        upperFlexTable.setWidget(3, 1, subjectCodeCBox);
        upperFlexTable.setWidget(4, 0, genderDependencyLabel);
        upperFlexTable.setWidget(4, 1, genderCBox);
        
        if (buttonName.equalsIgnoreCase("internalcall")) {
        	panel.setTitle(constant.internalCallList());
            upperFlexTable.setWidget(6, 0, reportButton);
        }
        else if (buttonName.equalsIgnoreCase("testnumber")) {
        	genderCBox.setVisible(false);
        	genderDependencyLabel.setText("");
        	panel.setTitle(constant.testNumberHeading());
            upperFlexTable.setWidget(6, 0, testNumberButton);
        }
        else if (buttonName.equalsIgnoreCase("externalcall")) {
        	panel.setTitle(constant.externalCallList());
            upperFlexTable.setWidget(6, 0, meritButton);
        }
        else if (buttonName.equalsIgnoreCase("finalmerit")) {
        	panel.setTitle(constant.finalStudentList());
            upperFlexTable.setWidget(6, 0, finalMeritButton);
        }
        else if (buttonName.equalsIgnoreCase("reset")) {
        	panel.setTitle(constant.reset());
            upperFlexTable.setWidget(6, 0, resetButton);
        }
        else if (buttonName.equalsIgnoreCase("computemarks")) {
        	genderCBox.setVisible(false);
        	genderDependencyLabel.setText("");
        	panel.setTitle(constant.computeMarksHeading());
            upperFlexTable.setWidget(6, 0, updateButton);
        }        
        upperFlexTable.setWidget(6, 1, cancelButton);
        panel.add(upperFlexTable);
        panel.add(vStudentPanel);
        panel.setPaddings(10);
		outerPanel.add(panel);
		
		updateButton.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {                 	
            	vStudentPanel.clear();
                String entity_type = entityTypeCBox.getValue();
                String entity_id = entityNameCBox.getValue();
                String program_id = programNameCBox.getValue();
                String subject_code = subjectCodeCBox.getValue();
                String gender = genderCBox.getValue();
                if ((entity_id == null) || (entity_type == null) ||
                        (program_id == null) || (subject_code == null)) {
                    if (entity_id == null) {
                        MessageBox.alert(message.selectEntityType());
                    }

                    if (entity_type == null) {
                        MessageBox.alert(message.selectEntityName());
                    }

                   if ((subject_code == null)) {
                   	 MessageBox.alert("Please select Subject from Subject.");
                   }                      
                } else {     
                	panel.getBody().mask(message.waitMask());
                	greetingService.validateGenerate(userId, entity_type,entity_id, program_id, subject_code,gender,new AsyncCallback<String>(){
                                public void onFailure(Throwable arg0) {
                                	panel.getBody().unmask();
                                    MessageBox.alert(message.failure() + ":",arg0.getMessage());
                                }
                                public void onSuccess(String result) {                                 	
                                	vStudentPanel.clear();                                    	
                                    if (result.equalsIgnoreCase("C") ||result.equalsIgnoreCase("T")||result.equalsIgnoreCase("N")||result.equalsIgnoreCase("R")||result.equalsIgnoreCase("I")) {                    	
                                    	greetingService.updateMarks(userId, entityTypeCBox.getValue(),entityNameCBox.getValue(),  programNameCBox.getValue(), subjectCodeCBox.getValue(),genderCBox.getValue(),new AsyncCallback<List<DataBean>>(){
				                            public void onFailure(Throwable arg0) {
				                                MessageBox.alert(message.failure() + ":",arg0.getMessage());
				                            }				
				                            public void onSuccess(List<DataBean> list) {
				                                if (list.size() == 0) {
				                                    MessageBox.alert(message.computeMarksFailure());
				                                }
				                                else {				                                	
				                                	vStudentPanel.clear();
				                                    MessageBox.alert(message.computeMarksSuccess());
				                                    vStudentPanel.add(new StudentGridList(list).createStudentGridPanel());
				                                }
				                                panel.getBody().unmask();
				                            }
				                        });
                                    }
                                    else{
                                    	panel.getBody().unmask();
                                    	MessageBox.alert(message.computeMarksFailure());
                                    }
                                }                                
                                });
                }
            }
        });

   /* resetButton.addListener(new ButtonListenerAdapter() {
            @Override
            public void onClick(Button button, EventObject e) {
                String entity_type = entityTypeCBox.getValue();
                String entity_id = entityNameCBox.getValue();
                String program_id = programNameCBox.getValue();
                String branch_id = branchNameCBox.getValue();
                String specialization_id = specializationNameCBox.getValue();

                System.out.println(entity_type + " | " + entity_id + " | " +
                    program_id + " | " + branch_id + " | " + " | " +
                    specialization_id);

                //String branch_name="All";
                if ((entity_id == null) || (entity_type == null) ||
                        (program_id == null) || (branch_id == null)||
                        (branch_id.equalsIgnoreCase("All"))) {
                    if (entity_id == null) {
                        MessageBox.alert(message.selectEntityType());
                    }

                    if (entity_type == null) {
                        MessageBox.alert(message.selectEntityName());

                        try {
                            entityNameCBox.markInvalid(constant.marksInvalid());
                        } catch (Exception e1) {
                            System.out.println("Yes Exception :" +
                                e1.getMessage());
                        }
                    }

                    if (program_id == null) {
                        MessageBox.alert(message.selectProgramName());
                    }

                    if (branch_id == null) {
                        MessageBox.alert(message.selectBranchName());
                    }
                } else {
                    greetingService.getResetFlag(userId, entity_type,
                        entity_id, program_id, branch_id,
                        specialization_id,
                        new AsyncCallback<String>() {
                            public void onFailure(Throwable arg0) {
                                MessageBox.alert(message.failure() + ":",
                                    arg0.getMessage());
                            }

                            public void onSuccess(String arg) {
                                if (arg.length() > 0) {
                                    if (arg.equalsIgnoreCase("E")) {
                                        MessageBox.alert(message.resetSecondSuccess());
                                    }

                                    if (arg.equalsIgnoreCase("R")) {
                                        MessageBox.alert(message.resetFisrtSuccess());
                                    }
                                } else {
                                    MessageBox.alert(message.resetFailure());
                                }
                            } //onsuccess close
                        }); //greetservice update marks
                } //else ends here
            } //onclick ends
        }); //button adaptor
*/

    reportButton.addListener(new ButtonListenerAdapter() {
            @Override
            public void onClick(Button button, EventObject e) {
            	panel.getBody().mask(message.waitMask());
                String entity_type = entityTypeCBox.getValue();
                String entity_id = entityNameCBox.getValue();
                String program_id = programNameCBox.getValue();                
                String subject_code=subjectCodeCBox.getValue();
                String gender=genderCBox.getValue();
                if ((entity_id == null) || (entity_type == null) ||
                        (program_id == null) || (subject_code == null) || gender==null) {
                    if (entity_id == null) {
                        MessageBox.alert(message.selectEntityType());
                    }

                    if (entity_type == null) {
                        MessageBox.alert(message.selectEntityName());
                    }

                    if (program_id == null) {
                        MessageBox.alert(message.selectProgramName());
                    }

                    if ((subject_code == null)) {
                    	 MessageBox.alert("Please select Subject from Subject.");
                    }
                    if ((gender == null)) {
                    	 MessageBox.alert("Please select Gender Dependency.");
                    }
                    panel.getBody().unmask();
                } else {
                    greetingService.validateGenerate(userId, entity_type,entity_id, program_id, subject_code,gender,new AsyncCallback<String>(){
                            public void onFailure(Throwable arg0) {
                            	panel.getBody().unmask();
                                MessageBox.alert(message.failure() + ":",arg0.getMessage());
                            }
                            public void onSuccess(String result) {
                                if (result.equalsIgnoreCase("C") ||
                                        result.equalsIgnoreCase("I")) {                                        
                                    Window.Location.replace(GWT.getModuleBaseURL() +
                                        "export?param=" + userId +
                                        "&param1=" +
                                        entityTypeCBox.getValue() +
                                        "&param2=" +
                                        entityNameCBox.getValue() +
                                        "&param3=" +
                                        programNameCBox.getValue() +
                                        "&param4=" +
                                        subjectCodeCBox.getValue() +
                                        "&param5=" +
                                        genderCBox.getValue() +
                                        "&param6=" +
                                        subjectCodeCBox.getRawValue());
                                } else {
                                    MessageBox.alert(message.internalCallListFailure());
                                }
                                panel.getBody().unmask();
                            }
                        });
                }
            }
        });

    testNumberButton.addListener(new ButtonListenerAdapter() {
            @Override
            public void onClick(Button button, EventObject e) {
            	panel.getBody().mask(message.waitMask());
                String entity_type = entityTypeCBox.getValue();
                String entity_id = entityNameCBox.getValue();
                String program_id = programNameCBox.getValue();
                String subject_code=subjectCodeCBox.getValue();
                String gender=genderCBox.getValue();
                if ((entity_id == null) || (entity_type == null) ||
                        (program_id == null) || (subject_code == null)) {
                    if (entity_id == null) {
                        MessageBox.alert(message.selectEntityType());
                    }

                    if (entity_type == null) {
                        MessageBox.alert(message.selectEntityName());
                    }

                    if (program_id == null) {
                        MessageBox.alert(message.selectProgramName());
                    }

                   if ((subject_code == null)) {
                   	 MessageBox.alert(message.selectSubCode());
                   }
                   panel.getBody().unmask();
                }
                else{
	                greetingService.generateTestNumber(userId, entity_type,entity_id, program_id, subject_code, gender,new AsyncCallback<Boolean>(){
	                        public void onFailure(Throwable arg0) {
	                        	panel.getBody().unmask();
	                            MessageBox.alert(message.failure() + ":", arg0.getMessage());
	                        }
	                        public void onSuccess(Boolean arg) {
	                            if (arg == true) {
	                                MessageBox.alert(message.generateTestNumberFailure());
	                            } 
	                            else {
	                                MessageBox.alert(message.generateTestNumberSuccess());                                
	                            }
	                            panel.getBody().unmask();
	                        } 
	                    }); 
            }
            } 
        }); 

    meritButton.addListener(new ButtonListenerAdapter() {
            @Override
            public void onClick(Button button, EventObject e) {   
            	panel.getBody().mask(message.waitMask());
                String entity_type = entityTypeCBox.getValue();
                String entity_id = entityNameCBox.getValue();
                String program_id = programNameCBox.getValue();
                String subject_code=subjectCodeCBox.getValue();
                String genderDep=genderCBox.getValue();            
                if ((entity_id == null) || (entity_type == null) ||
                        (program_id == null) || (subject_code == null) || genderDep==null) {
                    if (entity_id == null) {
                        MessageBox.alert(message.selectEntityType());
                    }

                    if (entity_type == null) {
                        MessageBox.alert(message.selectEntityName());
                    }

                    if (program_id == null) {
                        MessageBox.alert(message.selectProgramName());
                    }

                    if ((subject_code == null)) {
                   	 	MessageBox.alert("Please select Subject from Subject.");
                    }
                    if ((genderDep == null)) {
                    	MessageBox.alert("Please select Gender Dependency.");
                    }
                    panel.getBody().unmask();
                } else {
                    greetingService.validateGenerate(userId, entity_type,entity_id, program_id, subject_code,genderDep,new AsyncCallback<String>(){
                            public void onFailure(Throwable arg0) {
                            	panel.getBody().unmask();
                                MessageBox.alert(message.failure() + ":",arg0.getMessage());
                            }
                            public void onSuccess(String result) {
                                if (result.equalsIgnoreCase("T") ||result.equalsIgnoreCase("E") ||  result.equalsIgnoreCase("C")) {                                       
                                    Window.Location.replace(GWT.getModuleBaseURL() +
                                        "exportcall?param=" + userId +
                                        "&param1=" +
                                        entityTypeCBox.getValue() +
                                        "&param2=" +
                                        entityNameCBox.getValue() +
                                        "&param3=" +
                                        programNameCBox.getValue() +
                                        "&param4=" +
                                        subjectCodeCBox.getValue() +
                                        "&param5=" +
                                        genderCBox.getValue() +
                                        "&param6=" +
                                        subjectCodeCBox.getRawValue());
                                } else {
                                    MessageBox.alert(message.externalCallListFailure());
                                }
                                panel.getBody().unmask();
                            }
                        });
                }
            } 
        }); 

    finalMeritButton.addListener(new ButtonListenerAdapter() {
            @Override
            public void onClick(Button button, EventObject e) {
            	panel.getBody().mask(message.waitMask());
                String entity_type = entityTypeCBox.getValue();
                String entity_id = entityNameCBox.getValue();
                String program_id = programNameCBox.getValue();
                String subject_code=subjectCodeCBox.getValue();
                String genderDep=genderCBox.getValue(); 
                if ((entity_id == null) || (entity_type == null) ||
                        (program_id == null) || (subject_code == null) || genderDep==null) {
                    if (entity_id == null) {
                        MessageBox.alert(message.selectEntityType());
                    }

                    if (entity_type == null) {
                        MessageBox.alert(message.selectEntityName());
                    }

                    if (program_id == null) {
                        MessageBox.alert(message.selectProgramName());
                    }

                    if ((subject_code == null)) {
                   	 	MessageBox.alert("Please select Subject from Subject.");
                    }
                    if ((genderDep == null)) {
                    	MessageBox.alert("Please select Gender Dependency.");
                    }
                    panel.getBody().unmask();
                } 
                else {
                    greetingService.validateGenerate(userId,entity_type,entity_id, program_id, subject_code, genderDep,new AsyncCallback<String>(){
                            public void onFailure(Throwable arg0) {
                            	panel.getBody().unmask();
                                MessageBox.alert(message.failure() + ":",arg0.getMessage());
                            }
                            public void onSuccess(String result) {
                                if (result.equalsIgnoreCase("E") ||result.equalsIgnoreCase("F") || result.equalsIgnoreCase("C")) {                                    
                                    Window.Location.replace(GWT.getModuleBaseURL() +
                                        "exportfinal?param=" + userId +
                                        "&param1=" +
                                        entityTypeCBox.getValue() +
                                        "&param2=" +
                                        entityNameCBox.getValue() +
                                        "&param3=" +
                                        programNameCBox.getValue() +
                                        "&param4=" +
                                        subjectCodeCBox.getValue() +
                                        "&param5=" +
                                        genderCBox.getValue() +
                                        "&param6=" +
                                        subjectCodeCBox.getRawValue());
                                } else {
                                    MessageBox.alert(message.finalCallListFailure());
                                }
                                panel.getBody().unmask();
                            }
                        });
                }
            }
        });
	}

}
