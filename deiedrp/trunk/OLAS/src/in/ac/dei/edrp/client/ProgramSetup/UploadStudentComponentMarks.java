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
package in.ac.dei.edrp.client.ProgramSetup;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.RPCFiles.GreetingServiceAsync;
import in.ac.dei.edrp.client.Shared.DocumentUpload;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.addmarks.AddMarksExcelService;
import in.ac.dei.edrp.client.addmarks.AddMarksExcelServiceAsync;

import java.util.Iterator;
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
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

public class UploadStudentComponentMarks {
	CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	AddMarksExcelServiceAsync addMarksService = (AddMarksExcelServiceAsync) GWT.create(AddMarksExcelService.class);
	
	private final constants constant = GWT.create(constants.class);
	private final messages message = GWT.create(messages.class);
	
	public VerticalPanel vPanel = new VerticalPanel();
	String userId = "";

	public UploadStudentComponentMarks(String userID) {
		this.userId = userID;
	}
	
	public void onModuleLoad(){
		vPanel.clear();		
		final FlexTable upperFlexTable = new FlexTable();
		final FlexTable buttonTable = new FlexTable();
		final Panel vStudentPanel = new Panel();
		vStudentPanel.setTitle(constant.importStudentMarksTitle());	
		
		final ComboBox entityTypeCBox = new ComboBox();
		final ComboBox entityNameCBox = new ComboBox();
		final ComboBox programNameCBox = new ComboBox();
		final ComboBox componentCBox = new ComboBox();
		
		final DocumentUpload browsButton=new DocumentUpload("","0");
		
		final Button uploadButton=new Button(constant.uploadButton()); 
		final Button cancelButton=new Button(constant.cancelButton()); 
		final Button processButton=new Button(constant.importMarksButton());
		final Label entityTypeLabel = new Label(constant.entityType());
		final Label entityNameLabel = new Label(constant.entityName());
		final Label programNameLabel = new Label(constant.programme());
		final Label componentLabel = new Label(constant.componentName());
		final Label uploadLabel = new Label(constant.browseFile());
		
		
		entityTypeCBox.setEmptyText(constant.emptyEntityType());
		entityNameCBox.setEmptyText(constant.emptyEntityName());
		programNameCBox.setEmptyText(constant.emptyProgramName());
		componentCBox.setEmptyText(constant.emptyComponentName());
		
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

		componentCBox.setForceSelection(true);
		componentCBox.setMinChars(1);
		componentCBox.setFieldLabel("Component");
		componentCBox.setDisplayField("component_name");
		componentCBox.setValueField("component_id");
		componentCBox.setMode(ComboBox.LOCAL);
		componentCBox.setTriggerAction(ComboBox.ALL);
		componentCBox.setLoadingText("Loading...");
		componentCBox.markInvalid(constant.marksInvalid());
		componentCBox.setTypeAhead(true);
		componentCBox.setSelectOnFocus(true);
		componentCBox.setHideTrigger(false);
		
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
				connectTemp.getComponentList(programNameCBox.getValue(), entityNameCBox.getValue(),
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
														"component_name"),
												new StringFieldDef("component_id") });

								object2 = new String[arg0.length+1][2];
								object2[0][0] = "Academic Score";
								object2[0][1] = "AS";
								for (int i = 1; i < arg0.length+1; i++) {
									object2[i][0] = arg0[i-1].getComponentDescription();
									object2[i][1] = arg0[i-1].getComponentId();
								}
								MemoryProxy proxy = new MemoryProxy(object2);
								ArrayReader reader = new ArrayReader(recordDef);
								Store store = new Store(proxy, reader);
								store.load();
								componentCBox.clearValue();
								componentCBox.setStore(store);
							}
						});		

			}
		});			
		
		componentCBox.addListener(new ComboBoxListenerAdapter(){
			public void onSelect(ComboBox comboBox, Record record, int index) {
				if(componentCBox.getValue().equalsIgnoreCase("AS")){
					vStudentPanel.setTitle(constant.importStudentMarksTitle());
					upperFlexTable.remove(uploadLabel);
					upperFlexTable.remove(browsButton);
					buttonTable.remove(uploadButton);
					buttonTable.setWidget(0, 1, processButton);								
					
				}
				else{	
					vStudentPanel.setTitle(constant.uploadStudentMarksTitle());
					upperFlexTable.setWidget(4, 1, uploadLabel);
					upperFlexTable.setWidget(4, 2, browsButton);
					buttonTable.remove(processButton);
					buttonTable.setWidget(0, 1, uploadButton);
					
				}
			}
		});
		
		cancelButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				vPanel.remove(vStudentPanel);
				
			}
			
		});
		
		uploadButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				vStudentPanel.getBody().mask(message.waitMask());
				final String programId=programNameCBox.getValue();
				final String entityId=entityNameCBox.getValue();
				final String componentId=componentCBox.getValue();
				final String ComponentDescription=componentCBox.getRawValue();
				final String entityType=entityTypeCBox.getValue();
				final String fileName=browsButton.getFileName();
				if (entityId == null || entityType == null || programId == null || componentId==null || fileName.equals("")){
					vStudentPanel.getBody().unmask();
					if (entityId == null)
						MessageBox.alert(message.info(),message.selectEntityType());
					if (entityType == null)
						MessageBox.alert(message.info(),message.selectEntityName());
					if (programId == null)
						MessageBox.alert(message.info(),message.selectProgramName());
					if (componentId == null)
						MessageBox.alert(message.info(),message.selectComponnt());
					if (fileName.equals(""))
						MessageBox.alert(message.info(),message.selectFileName());
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
									if (result.equalsIgnoreCase("E")|| result.equalsIgnoreCase("F") || result.equalsIgnoreCase("T")) {
										if (browsButton.getFileName().substring(
												fileName.indexOf("."),
												fileName.length())
												.equalsIgnoreCase(".xls")) {
											browsButton.setDocId("1");
											try {
												browsButton.uploadFile("Excel/");
											} catch (Exception e) {
												MessageBox.alert(message.error(), message.errorUploadExcel());
											}
											addMarksService.uploadFileForStudentComponentMarks(browsButton.getFileName(), userId, entityType, entityId, 
													programId, componentId, ComponentDescription, new AsyncCallback<List<Integer>>(){
														@Override
														public void onFailure(
																Throwable arg0) {
															vStudentPanel.getBody().unmask();
															MessageBox.alert(message.failure()+ ":",arg0.getMessage());
															
														}
														@Override
														public void onSuccess(
																List<Integer> list) {
															vStudentPanel.getBody().unmask();
															int value1 = 0;
															int value2 = 0;
															if (list.size() > 0) {
																Iterator<Integer> itr = list.iterator();
																while (itr.hasNext()) {
																	value1 = itr.next();
																	value2 = itr.next();
																}
																MessageBox.alert(message.successfullyAddMarks(String.valueOf(value1),String.valueOf(value2),String.valueOf((value1 - value2))));
																			
															} else {
																MessageBox.alert(message.errorInAddMarks1());
															}															
															entityTypeCBox.reset();
															entityNameCBox.reset();
															programNameCBox.reset();
															componentCBox.reset();																	
														}
												
											});
										}
										else {
											vStudentPanel.getBody().unmask();
											MessageBox.alert(message
													.errorInAddMarks2());
										}
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
		
		processButton.addListener(new ButtonListenerAdapter(){
			@Override
			public void onClick(Button button, EventObject e) {
				vStudentPanel.getBody().mask(message.waitMask());
				final String programId=programNameCBox.getValue();
				final String entityId=entityNameCBox.getValue();
				final String componentId=componentCBox.getValue();
				final String ComponentDescription=componentCBox.getRawValue();
				final String entityType=entityTypeCBox.getValue();
				if (entityId == null || entityType == null || programId == null || componentId==null){
					vStudentPanel.getBody().unmask();
					if (entityId == null)
						MessageBox.alert(message.info(),message.selectEntityType());
					if (entityType == null)
						MessageBox.alert(message.info(),message.selectEntityName());
					if (programId == null)
						MessageBox.alert(message.info(),message.selectProgramName());
					if (componentId == null)
						MessageBox.alert(message.info(),message.selectComponnt());
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
									if (result.equalsIgnoreCase("E")|| result.equalsIgnoreCase("F") || result.equalsIgnoreCase("T")) {
											addMarksService.uploadFileForStudentComponentMarks("", userId, entityType, entityId, 
													programId, componentId, ComponentDescription, new AsyncCallback<List<Integer>>(){

														@Override
														public void onFailure(
																Throwable arg0) {
															vStudentPanel.getBody().unmask();
															MessageBox.alert(message.failure()+ ":",arg0.getMessage());
															
														}

														@Override
														public void onSuccess(
																List<Integer> list) {
															vStudentPanel.getBody().unmask();
															if(list.get(0)==1){
																MessageBox.alert(message.success(),message.academicScoreSuccess());
															}
															else{
																MessageBox.alert(message.success(),message.academicScorFail());
															}															
															entityTypeCBox.reset();
															entityNameCBox.reset();
															programNameCBox.reset();
															componentCBox.reset();																	
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
		upperFlexTable.setWidget(3, 1, componentLabel);
		upperFlexTable.setWidget(3, 2, componentCBox);
		buttonTable.setWidget(0, 1, processButton);
		buttonTable.setWidget(0, 2, cancelButton);
		buttonTable.setCellSpacing(10);
		upperFlexTable.setWidget(5, 1, buttonTable);
		upperFlexTable.setCellSpacing(10);
		vStudentPanel.add(upperFlexTable);	
		vPanel.add(vStudentPanel);
	
	}
}
