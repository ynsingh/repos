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
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

public class FinalMeritListProcess {
	private final CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final FinalMeritListAsync meritListService=(FinalMeritListAsync)GWT.create(FinalMeritList.class);
	
	private final constants constant = GWT.create(constants.class);
	private final messages message = GWT.create(messages.class);
	
	public VerticalPanel vPanel = new VerticalPanel();
	String userId = "";

	public FinalMeritListProcess(String userID) {
		this.userId = userID;
	}
	public void onModuleLoad(){
		vPanel.clear();	
		final FlexTable upperFlexTable = new FlexTable();
		final Panel vStudentPanel = new Panel();
		vStudentPanel.setTitle(constant.finalMeritListTitle());	
		
		final ComboBox entityTypeCBox = new ComboBox();
		final ComboBox entityNameCBox = new ComboBox();
		final ComboBox programNameCBox = new ComboBox();
		final Button MeritListButton=new Button(constant.generateFinalMeritListButton()); 
		final Button cancelButton=new Button(constant.cancelButton()); 
		final Label entityTypeLabel = new Label(constant.entityType());
		final Label entityNameLabel = new Label(constant.entityName());
		final Label programNameLabel = new Label(constant.programme());
	
	
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
		
		cancelButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				vPanel.remove(vStudentPanel);
				
			}
			
		});
		MeritListButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				vStudentPanel.getBody().mask(message.waitMask());
				final String programId=programNameCBox.getValue();
				final String entityId=entityNameCBox.getValue();								
				final String entityType=entityTypeCBox.getValue();
				if (entityId == null || entityType == null || programId == null){
					vStudentPanel.getBody().unmask();
					if (entityId == null)
						MessageBox.alert(message.selectEntityType());
					if (entityType == null)
						MessageBox.alert(message.selectEntityName());
					if (programId == null)
						MessageBox.alert(message.selectProgramName());
				}
				else{
					greetingService.validateGenerate(userId, entityType,
							entityId, programId, "",
							"", new AsyncCallback<String>() {
	
								@Override
								public void onFailure(Throwable arg0) {
									vStudentPanel.getBody().unmask();
									MessageBox.alert(message.failure() + ":",
											arg0.getMessage());
								}
	
								@Override
								public void onSuccess(String result) {									
									if (result.equalsIgnoreCase("E")|| result.equalsIgnoreCase("F")) {										
										meritListService.generateFinalMeritList(userId, entityId, programId, new AsyncCallback<String>(){
											@Override
											public void onFailure(Throwable arg0) {
												vStudentPanel.getBody().unmask();
												MessageBox.alert(message.failure() + ":", arg0
														.getMessage()+" : "+"inside final success");												
											}

											@Override
											public void onSuccess(
													String msg) {
												vStudentPanel.getBody().unmask();
												String[]str=msg.split("-");
												if(str[0].equals("success")){																									
													MessageBox.alert(message.successfullyAddMarks(str[1],str[2],str[3]));
												}											
												else if(str[0].equals("noRegisteredStudent")){
													MessageBox.alert(message.studentNotFound());
												}
												else if(str[0].equals("error")){
													MessageBox.alert(message.sqlErrorInFinalListProcess());
												}
												else{
													MessageBox.alert(message.problemInService());
												}
												entityTypeCBox.reset();
												entityNameCBox.reset();
												programNameCBox.reset();
											}
											
										});
									}
									else{
										vStudentPanel.getBody().unmask();
										MessageBox.alert(message
												.validateFailure());
									}
								}
	
					});
				}
			}
	
		});
		upperFlexTable.setWidget(0, 1, entityTypeLabel);
		upperFlexTable.setWidget(0, 2, entityTypeCBox);
		upperFlexTable.setWidget(1, 1, entityNameLabel);
		upperFlexTable.setWidget(1, 2, entityNameCBox);
		upperFlexTable.setWidget(2, 1, programNameLabel);
		upperFlexTable.setWidget(2, 2, programNameCBox);
		upperFlexTable.setWidget(3, 1, MeritListButton);
		upperFlexTable.setWidget(3, 2, cancelButton);
		upperFlexTable.setCellSpacing(10);
		vStudentPanel.add(upperFlexTable);
		vPanel.add(vStudentPanel);
	}

}
