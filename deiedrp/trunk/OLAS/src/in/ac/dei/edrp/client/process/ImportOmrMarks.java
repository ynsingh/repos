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
package in.ac.dei.edrp.client.process;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritList;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritListAsync;
import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.RPCFiles.GreetingServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.List;

import com.google.gwt.core.client.GWT;
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
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;

public class ImportOmrMarks {
	private final CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
	private final FinalMeritListAsync meritListService=(FinalMeritListAsync)GWT.create(FinalMeritList.class);
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final constants constant = GWT.create(constants.class);
	private final messages message = GWT.create(messages.class);
	
	public VerticalPanel vPanel = new VerticalPanel();
	String userId = "";

	public ImportOmrMarks(String userID) {
		this.userId = userID;
	}
	
	String testId="";
	String testName="";
	String testConductDate="";
	String groupStatus="";
	 
	public void onModuleLoad(){
		vPanel.clear();							
		final FlexTable upperFlexTable = new FlexTable();
		final FlexTable mainFlexTable = new FlexTable();
		final FlexTable programTable= new FlexTable();
		final Panel vStudentPanel = new Panel();
		vStudentPanel.setTitle(constant.importOmrMarksTitle());
		
		final FieldSet programDetailPanel=new FieldSet(constant.programDetailTitle());
		final FieldSet searchPanel=new FieldSet(constant.searchTestByDate());
		final ComboBox entityTypeCBox = new ComboBox();
		final ComboBox entityNameCBox = new ComboBox();
		final ComboBox programNameCBox = new ComboBox();
		final Button cancelButton=new Button(constant.cancelButton()); 
		final Button importButton=new Button(constant.importMarksButton()); 
		final Button okButton=new Button(constant.okButton()); 
		final Label entityTypeLabel = new Label(constant.entityType());
		final Label entityNameLabel = new Label(constant.entityName());
		final Label programNameLabel = new Label(constant.programme());
		final Label conductLable = new Label(constant.testConductDate());
		final DateField conductDate=new DateField();
		
		entityTypeCBox.setEmptyText(constant.emptyEntityType());
		entityNameCBox.setEmptyText(constant.emptyEntityName());
		programNameCBox.setEmptyText(constant.emptyProgramName());
	
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
						MessageBox.alert(message.failure() + ":", arg0
								.getMessage());
					}
	
					public void onSuccess(CM_ProgramInfoGetter[] arg0) {
						RecordDef recordDef = new RecordDef(new FieldDef[] {
								new StringFieldDef("entity_type"),
								new StringFieldDef("entity_description") });
	
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
								MessageBox.alert(message.failure() + ":", arg0
										.getMessage());
							}	
							public void onSuccess(CM_ProgramInfoGetter[] arg0) {
								RecordDef recordDef = new RecordDef(
										new FieldDef[] {
												new StringFieldDef(
														"entity_name"),
												new StringFieldDef("entity_id") });	
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
	
				int counter=1;
				connectTemp.getProgrammeOff(comboBox.getValue(),counter,
						new AsyncCallback<CM_ProgramInfoGetter[]>() {
							String[][] object2;
							public void onFailure(Throwable arg0) {
								MessageBox.alert(message.failure() + ":", arg0
										.getMessage());
							}
	
							public void onSuccess(CM_ProgramInfoGetter[] arg0) {
								RecordDef recordDef = new RecordDef(										
										new FieldDef[] {
												new StringFieldDef(
														"program_name"),
												new StringFieldDef("program_id") });
	
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
			public void onSelect(ComboBox comboBox, final Record record,
					int index) {	
			}
		});					
		 
		okButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				vStudentPanel.getBody().mask(message.waitMask());
				final GridPanel grid = new GridPanel();
				String date=conductDate.getRawValue();	
				String date1=null;				
				if(date==null || date.equals("")){
					
				}	
				else{
					String[]str=date.split("/");
					date1=str[2]+"-"+str[0]+"-"+str[1];	
				}			
				connectTemp.getOmrTestId(date1, new AsyncCallback<List<ReportInfoGetter>>(){
					@Override
					public void onFailure(Throwable arg0) {
						MessageBox.alert(message.failure() + ":", arg0
								.getMessage());
						vStudentPanel.getBody().unmask();
					}

					@Override
					public void onSuccess(List<ReportInfoGetter> result) {
						vStudentPanel.getBody().unmask();
						if(result.size()<1){							
							MessageBox.alert(message.notTestInOmr());
							upperFlexTable.removeCell(0, 1);
						}
						else{
							Object[][] testData = new Object[result.size()][4];
							for(int i = 0; i < result.size(); i++){
								testData[i][0] = result.get(i).getTest_number();
								testData[i][1]= result.get(i).getDescription();
								testData[i][2]= result.get(i).getStart_date();
								testData[i][3]= result.get(i).getStatus();
								
							}
							RecordDef RecordDef = new RecordDef(new FieldDef[] {
									new StringFieldDef("testId"),
									new StringFieldDef("testName"),
									new StringFieldDef("conductDate"),
									new StringFieldDef("groupStatus"),
							});
							MemoryProxy proxy = new MemoryProxy(testData);
							ArrayReader reader = new ArrayReader(RecordDef);
							Store store = new Store(proxy, reader);
							store.load();
							grid.setStore(store);
							
							final CheckboxSelectionModel cbSelectionModel=new CheckboxSelectionModel();
							BaseColumnConfig[] columns = new BaseColumnConfig[] {
									new CheckboxColumnConfig(cbSelectionModel),
									new ColumnConfig(constant.testId(),"testId", 90, true, null,"testId"),
									new ColumnConfig(constant.testName(), "testName",100,true,null,"testName"),	
									new ColumnConfig(constant.conductDate(), "conductDate",80,true,null,"conductDate"),
									new ColumnConfig(constant.groupExists(), "groupStatus",80,true,null,"groupStatus")
								};
								ColumnModel columnModel = new ColumnModel(columns);
								grid.setColumnModel(columnModel);
								grid.setFrame(true);
								grid.setStripeRows(true);
								grid.setAutoExpandColumn("testId");
								grid.setAutoExpandColumn("testName");
								grid.setAutoExpandColumn("conductDate");
								grid.setAutoExpandColumn("groupStatus");
								grid.setSelectionModel(cbSelectionModel);
								grid.setWidth(400);
								grid.setHeight(200);
								grid.setTitle(constant.selectTest());
								upperFlexTable.setWidget(0, 1, grid);
								
								cbSelectionModel.addListener(new RowSelectionListenerAdapter() {
						   		  	public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
											if(cbSelectionModel.getSelections().length==1){
												Record[] data=cbSelectionModel.getSelections();
												testId=data[0].getAsString("testId");
												testName=data[0].getAsString("testName");
												testConductDate=data[0].getAsString("conductDate");
												groupStatus=data[0].getAsString("groupStatus");
											}
											else{												
												testId="";
												testConductDate="";
												groupStatus="";
												cbSelectionModel.clearSelections();
												MessageBox.alert(message.info(), message.selectOne());
											}
										}
										
										public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
											if(cbSelectionModel.getSelections().length==1){
												testId="";
												testConductDate="";
												groupStatus="";
											}
											else{
												testId="";
												testConductDate="";
												groupStatus="";
												cbSelectionModel.clearSelections();
												
											}
										}
						   		   
						   	   });
						
						}
						
					}
					
				});
				
			}
			
		});
		cancelButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				vPanel.remove(vStudentPanel);
				
			}
			
		});
		importButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				vStudentPanel.getBody().mask(message.waitMask());
				String entityType=entityTypeCBox.getValue();
				String entityId=entityNameCBox.getValue();
				String program=programNameCBox.getValue();
				if(entityType==null || entityId==null || program==null || testId.equals("")){
					vStudentPanel.getBody().unmask();
					if (entityId == null)
						MessageBox.alert(message.selectEntityType());
					if (entityType == null)
						MessageBox.alert(message.selectEntityName());
					if (program == null)
						MessageBox.alert(message.selectProgramName());
					if(testId.equals(""))
						MessageBox.alert(message.selectTestId());
				}
				else{
					greetingService.validateGenerate(userId, entityTypeCBox.getValue(),
							entityNameCBox.getValue(), programNameCBox.getValue(), "",
							"", new AsyncCallback<String>() {
	
								@Override
								public void onFailure(Throwable arg0) {
									vStudentPanel.getBody().unmask();
									MessageBox.alert(message.failure() + ":",
											arg0.getMessage());
								}
								@Override
								public void onSuccess(String result) {
									if (result.equalsIgnoreCase("E") || result.equalsIgnoreCase("F")) {										
										meritListService.importOmrMarks(userId,programNameCBox.getValue(), entityNameCBox.getValue(), testId, testConductDate, groupStatus, new AsyncCallback<String>(){
											@Override
											public void onFailure(
													Throwable arg0) {
												vStudentPanel.getBody().unmask();
												MessageBox.alert(message.failure() + ":",
														arg0.getMessage());														
											}

											@Override
											public void onSuccess(String result) {
												vStudentPanel.getBody().unmask();
												String msg[]=result.split("-");
												if(msg[0].equals("success")){
													MessageBox.alert(message.success(),message.importOmrMarksSuccess(msg[1],msg[2],msg[3]));	
												}
												else if(msg[0].equals("NoETComponent")){
													MessageBox.alert(message.info(),message.componentNotAdded());
												}
												else if(msg[0].equals("NoRecordFound")){
													MessageBox.alert(message.info(),message.studentNotFound());
												}
												else if(msg[0].equals("NoMarksInOMR")){
													MessageBox.alert(message.info(),message.testNotConducted(testName));
												}
												else if(msg[0].equals("error")){
													MessageBox.alert(message.error(),message.marksNotImported());
												}
												else{
													MessageBox.alert(message.error(),message.problemInService());
												}
											}
											
										});
									}
									else{
										vStudentPanel.getBody().unmask();
										upperFlexTable.removeCell(0, 1);
										MessageBox.alert(message.info(),message.validateFailure());										
									}
								}
	
					});
							
				}
			}
		});
		programTable.setWidget(0, 1, entityTypeLabel);
		programTable.setWidget(0, 2, entityTypeCBox);
		programTable.setWidget(1, 1, entityNameLabel);
		programTable.setWidget(1, 2, entityNameCBox);
		programTable.setWidget(2, 1, programNameLabel);
		programTable.setWidget(2, 2, programNameCBox);
		programTable.setCellSpacing(10);
		programDetailPanel.add(programTable);
		
		FlexTable searchTable1=new FlexTable();
		searchTable1.setWidget(0, 1, conductLable);
		searchTable1.setWidget(0, 2, conductDate);
		searchTable1.setWidget(1, 1, okButton);
		searchTable1.setCellSpacing(10);
		searchPanel.add(searchTable1);
		searchPanel.setPaddings(5);
		searchPanel.add(upperFlexTable);
		mainFlexTable.setWidget(0, 1, programDetailPanel);	
		mainFlexTable.setWidget(1, 1, searchPanel);
		mainFlexTable.setCellSpacing(10);
		vStudentPanel.add(mainFlexTable);
		FlexTable buttonTable=new FlexTable();
		buttonTable.setWidget(0, 1, importButton);
		buttonTable.setWidget(0, 2, cancelButton);
		buttonTable.setCellSpacing(10);
		vStudentPanel.add(buttonTable);
		vPanel.add(vStudentPanel);		
	}
}
