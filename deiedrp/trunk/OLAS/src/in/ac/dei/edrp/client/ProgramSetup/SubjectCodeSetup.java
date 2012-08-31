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
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;

public class SubjectCodeSetup {	 
	private final CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
	
	private final constants constant = GWT.create(constants.class);
	private final messages message = GWT.create(messages.class);
	public VerticalPanel vPanel = new VerticalPanel();
	String userId = "";
	public SubjectCodeSetup(String userID) {
		this.userId = userID;
	}
	
	public void onModuleLoad(final String action){
		vPanel.clear();
		final FlexTable mainTable = new FlexTable();
		final FlexTable upperFlexTable = new FlexTable();	
		final FlexTable buttonTable=new FlexTable();
		final Panel vStudentPanel = new Panel();	
		if(action.equals("add")){
			vStudentPanel.setTitle(constant.subjectSetupTitle());
		}
		else{
			vStudentPanel.setTitle(constant.subjectManageTitle());
		}
		final ComboBox entityTypeCBox = new ComboBox();
		final ComboBox entityNameCBox = new ComboBox();
		final ComboBox programNameCBox = new ComboBox();
		final ComboBox subjectCBox = new ComboBox();
		
		final Button submitButton=new Button(constant.submit()); 
		final Button okButton=new Button(constant.okButton());
		final Button cancelButton=new Button(constant.cancelButton());			
		entityTypeCBox.setEmptyText(constant.emptyEntityType());
		entityNameCBox.setEmptyText(constant.emptyEntityName());
		programNameCBox.setEmptyText(constant.emptyProgramName());
		subjectCBox.setEmptyText(constant.selectSubject());
		
		final Label entityTypeLabel = new Label(constant.entityType());
		final Label entityNameLabel = new Label(constant.entityName());
		final Label programNameLabel = new Label(constant.programme());
		final Label subjectCodeLabel = new Label(constant.subjectName());
		
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
		
		subjectCBox.setForceSelection(true);
		subjectCBox.setMinChars(1);
		subjectCBox.setFieldLabel("Subject");
		subjectCBox.setDisplayField("subjectName");
		subjectCBox.setValueField("subjectCode");
		subjectCBox.setMode(ComboBox.LOCAL);
		subjectCBox.setTriggerAction(ComboBox.ALL);
		subjectCBox.setLoadingText("Loading...");
		subjectCBox.markInvalid(constant.marksInvalid());
		subjectCBox.setTypeAhead(true);
		subjectCBox.setSelectOnFocus(true);
		subjectCBox.setHideTrigger(false);
		
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
							System.out.println(object2[i][0] + " and "
									+ object2[i][1]);
						}

						for (int l = 0; l < object2.length; l++) {
							System.out.println(object2[l][0] + " and "
									+ object2[l][1]);
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
								programNameCBox.reset();
								entityNameCBox.reset();
								subjectCBox.reset();
								RecordDef recordDef = new RecordDef(
										new FieldDef[] {
												new StringFieldDef(
														"entity_name"),
												new StringFieldDef("entity_id") });

								object2 = new String[arg0.length][2];

								for (int i = 0; i < arg0.length; i++) {
									object2[i][0] = arg0[i].getEntity_name();
									object2[i][1] = arg0[i].getEntity_id();
									System.out.println(object2[i][0] + " and "
											+ object2[i][1]);
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
								programNameCBox.reset();	
								subjectCBox.reset();
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
				connectService.getSubject(userId,programNameCBox.getValue(),
						new AsyncCallback<List<CM_ProgramInfoGetter>>(){
						String[][] object2;
							@Override
							public void onFailure(Throwable arg0) {
								MessageBox.alert(message.failure() + ":", arg0.getMessage());
							}

							@Override
							public void onSuccess(
									List<CM_ProgramInfoGetter> arg0) {
								if(arg0.size()==0 && action.equals("add")){
									MessageBox.alert(message.info(),message.SubjectNotFound());
								}								
								RecordDef recordDef = new RecordDef(										
										new FieldDef[] {
												new StringFieldDef("subjectName"),new StringFieldDef("subjectCode") });

								object2 = new String[arg0.size()][2];
								for (int i = 0; i < arg0.size(); i++) {
									object2[i][0] = arg0.get(i).getSubject_description();
									object2[i][1] = arg0.get(i).getSubject_code();
								}
								MemoryProxy proxy = new MemoryProxy(object2);
								ArrayReader reader = new ArrayReader(recordDef);
								Store store = new Store(proxy, reader);
								store.load();
								subjectCBox.clearValue();
								subjectCBox.setStore(store);
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
		submitButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button,EventObject e){
				final String programId=programNameCBox.getValue();
				final String subjCode=subjectCBox.getValue();
				if(programId==null || subjCode==null){
					if (programId == null)
						MessageBox.alert(message.info(),message.selectProgramName());
					if (subjCode == null)
						MessageBox.alert(message.info(),message.selectSubjectCode());					
				}
				else{	
					MessageBox.confirm(message.confirm(), message.wantToContinue(),new MessageBox.ConfirmCallback(){
							@Override
							public void execute(String btnID) {
								if(btnID.equals(constant.yesButton())){
									connectService.setSubjectCode(userId, programId, subjCode, new AsyncCallback<String>(){
										@Override
										public void onFailure(Throwable arg0) {
											MessageBox.alert(message.failure()+ ":",arg0.getMessage());										
										}
										@Override
										public void onSuccess(String result) {
											if(result.equals("success")){
												String success=message.subjectCodeSuccess()+"\n"+message.continueWithSameProgram();
												MessageBox.confirm(message.success(),success, new MessageBox.ConfirmCallback(){
													@Override
													public void execute(String btnID) {
														if(btnID.equals(constant.yesButton())){														
															subjectCBox.reset();
															programNameCBox.reset();
														}
														else{
															entityTypeCBox.reset();
															entityNameCBox.reset();
															programNameCBox.reset();
															subjectCBox.reset();														
														}
													}
													
												});
											}
											else{
												MessageBox.alert(message.error(), message.problemInService());
											}												
										}
										
									});
								}
								else{
									
								}
							}
							
						});					
				}
			
			}
		});
		
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button,EventObject e){
				final GridPanel grid=new GridPanel();
				final ToolbarButton deletebutton = new ToolbarButton(constant.delete());
				final String programId=programNameCBox.getValue();				
				if(programId==null){
					if(mainTable.isCellPresent(1, 1)){
						mainTable.removeCell(1, 1);
						buttonTable.remove(cancelButton);
						upperFlexTable.setWidget(3, 2, cancelButton);
					}					
					if (programId == null)
						MessageBox.alert(message.info(),message.selectProgramName());													
				}
				else{					
					deletebutton.disable();								
					connectService.getSubjectCode(userId, programNameCBox.getValue(), new AsyncCallback<List<CM_ProgramInfoGetter>>(){
						@Override
						public void onFailure(Throwable arg0) {
							MessageBox.alert(message.failure()+ ":",arg0.getMessage());
						}
	
						@Override
						public void onSuccess(List<CM_ProgramInfoGetter> result) {												
							if(result.size()<1){
								MessageBox.alert(message.info(), message.noSubjectCodeAdded());
								if(mainTable.isCellPresent(1, 1)){
									mainTable.removeCell(1, 1);
									buttonTable.remove(cancelButton);
									upperFlexTable.setWidget(3, 2, cancelButton);
								}	
							}
							else{
								
								Object[][] data = new Object[result.size()][4];
								for(int i = 0; i < result.size(); i++){
									data[i][0] = programNameCBox.getRawValue();
									data[i][1] = programNameCBox.getValue();
									data[i][2]= result.get(i).getSubject_code();
									data[i][3]= result.get(i).getSubject_description();																
								}						
								RecordDef RecordDef = new RecordDef(new FieldDef[] {
										new StringFieldDef("programName"),
										new StringFieldDef("programId"),
										new StringFieldDef("subjectCode"),
										new StringFieldDef("subjectName"),								
								});
								MemoryProxy proxy = new MemoryProxy(data);
								ArrayReader reader = new ArrayReader(RecordDef);
								Store store = new Store(proxy, reader);
								store.load();
								grid.setStore(store);							
								final CheckboxSelectionModel cbSelectionModel=new CheckboxSelectionModel();
								BaseColumnConfig[] columns = new BaseColumnConfig[] {
										new CheckboxColumnConfig(cbSelectionModel),
										new ColumnConfig(constant.programme(),"programName", 100, true, null,"programName"),										
										new ColumnConfig(constant.subjectCode(), "subjectCode",80,true,null,"subjectCode"),
										new ColumnConfig(constant.subjectName(), "subjectName",80,true,null,"subjectName")
									};							
									ColumnModel columnModel = new ColumnModel(columns);
									grid.setColumnModel(columnModel);
									grid.setFrame(true);
									grid.setStripeRows(true);
									grid.setAutoExpandColumn("programName");
									grid.setAutoExpandColumn("subjectCode");
									grid.setAutoExpandColumn("subjectName");
									grid.setSelectionModel(cbSelectionModel);
									grid.setWidth(350);
									grid.setHeight(200);
									grid.setTitle(constant.selectSubject());
									Toolbar toolbar=new Toolbar();
									toolbar.addFill();
									grid.setTopToolbar(toolbar);
									toolbar.addButton(deletebutton);
															
								mainTable.setWidget(1, 1,  grid);	
								upperFlexTable.remove(cancelButton);
								buttonTable.setWidget(0, 1, cancelButton);	
								cbSelectionModel.addListener(new RowSelectionListenerAdapter() {
						   		  	public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
						   		  		if(cbSelectionModel.getSelections().length>0){
						   		  			deletebutton.enable();
						   		  		}
						   		  		else{
						   		  			deletebutton.disable();
						   		  		}
									}									
									public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
										if(cbSelectionModel.getSelections().length>0){
						   		  			deletebutton.enable();
						   		  		}
						   		  		else{
						   		  			deletebutton.disable();
						   		  		}
									}
						   		   
						   	   });
								deletebutton.addListener(new ButtonListenerAdapter(){
									public void onClick(Button button,EventObject e){
										MessageBox.confirm(message.confirm(), message.alert_ondelete(),new MessageBox.ConfirmCallback(){
											@Override
											public void execute(String btnID) {
												if(btnID.equals(constant.yesButton())){
													final Record record1[]=cbSelectionModel.getSelections();
													String subCod[]=new String[record1.length];
													for(int i=0;i<record1.length;i++){
														subCod[i]=record1[i].getAsString("subjectCode");										
													}
									   		  		connectService.deleteSubjectCode(userId, record1[0].getAsString("programId"), subCod, new AsyncCallback<String>(){
														@Override
														public void onFailure(Throwable arg0) {
															MessageBox.alert(message.failure()+ ":",arg0.getMessage());
														}
				
														@Override
														public void onSuccess(String arg0) {
															String result[]=arg0.split("-");
															if(result[0].equals("success")){												
																MessageBox.alert(message.success(), message.deleteSubjectCodeSuccess(String.valueOf(record1.length),result[1],String.valueOf(record1.length-Integer.parseInt(result[1]))));
															}
															else{
																MessageBox.alert(message.error(), message.problemInService());
															}
															mainTable.remove(grid);	
															buttonTable.remove(cancelButton);
															upperFlexTable.setWidget(3, 2, cancelButton);
														}
									   		  			
									   		  		});
												}
												else{
												}
											}
										});
									}
								});
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
		if(action.equals("add")){
			upperFlexTable.setWidget(3, 1, subjectCodeLabel);
			upperFlexTable.setWidget(3, 2, subjectCBox);	
			buttonTable.setWidget(0, 1, submitButton);
			buttonTable.setWidget(0, 2, cancelButton);
		}
		else{			
			upperFlexTable.setWidget(3, 1, okButton);
			upperFlexTable.setWidget(3, 2, cancelButton);
		}			
		buttonTable.setCellSpacing(10);
		upperFlexTable.setCellSpacing(10);
		mainTable.setWidget(0, 1, upperFlexTable);
		mainTable.setCellSpacing(10);
		vStudentPanel.add(mainTable);	
		vStudentPanel.add(buttonTable);
		vPanel.add(vStudentPanel);
	}
}
