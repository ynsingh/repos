package in.ac.dei.edrp.client.ProgramSetup;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.CM_ComboBoxes;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.summarysheet.CommonValidation;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListener;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class ManageProgramElig {
	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
	CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
	messages msgs = GWT.create(messages.class);
	constants cons = GWT.create(constants.class);
	
	Object[][] data;
	Object[][] data1;
	String[] values;
	VerticalPanel vp = new VerticalPanel();
	VerticalPanel panel = new VerticalPanel();
	String entity;
	String entity_id;
	String modifierid;
	Window window1 = new Window();	
	String consEntityName = cons.entityName();
	String consEntityType = cons.entityType();
	String errorMsg;
	String confirm;
	ToolbarButton editButton1 = new ToolbarButton(cons.edit());
	ToolbarButton deletebutton1 = new ToolbarButton(cons.delete());
	String pagename = "Program Age Eligibility";
	String university_id;	
	CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();	 
	public ManageProgramElig(String userid) {
		this.modifierid = userid;
	}
	public VerticalPanel onModuleLoad() {
		vp.clear();
		vp.setWidth("1200px");
		final CM_ComboBoxes entityNameCB = new CM_ComboBoxes(modifierid);
		final CM_ComboBoxes entityTypeCB = new CM_ComboBoxes(modifierid);
		final Button submitButton = new Button(cons.submit());
		final Button cancelButton = new Button(cons.cancelButton());
		final FormPanel mainForm = new FormPanel();
		errorMsg = msgs.error();
		confirm = msgs.confirm();		
		Label selectEntityName = new Label(consEntityName);
		Label selectEntity = new Label(consEntityType);		
		FlexTable flex3 = new FlexTable();
		entityTypeCB.onModuleLoad();
		entityNameCB.onModuleLoad();
		entityNameCB.entityCombo.disable();
		flex3.setCellSpacing(10);
		flex3.setWidget(0, 0, selectEntity);
		flex3.setWidget(0, 1, entityTypeCB.entityDescCB);
		flex3.setWidget(0, 2, selectEntityName);
		flex3.setWidget(0, 3, entityNameCB.entityCombo);
		FlexTable buttonTable=new FlexTable();		
		buttonTable.setWidget(0, 0, submitButton);
		buttonTable.setWidget(0, 1, cancelButton);
		flex3.setWidget(1, 1, buttonTable);
		
		mainForm.setLabelAlign(Position.TOP);
		mainForm.setTitle(cons.manageProgElig());
		mainForm.setPaddings(5);
		mainForm.setWidth("100%");
		mainForm.add(flex3);
		mainForm.setFrame(true);
		vp.add(mainForm);

		entityTypeCB.entityDescCB.addListener(new ComboBoxListenerAdapter() {
			public void onSelect(ComboBox comboBox, Record record, int index) {
				panel.clear();
				entityNameCB.entityCombo.clearValue();
				entityNameCB.entityCombo.disable();
				String entity_type = entityTypeCB.entityDescCB.getValue();
				connectTemp.Entity_Name(modifierid, entity_type,
						new AsyncCallback<CM_entityInfoGetter[]>() {
							public void onFailure(Throwable caught) {
								MessageBox.alert(cons.dbError(), caught
										.getMessage());
							}

							public void onSuccess(CM_entityInfoGetter[] arg0) {
								RecordDef recordDef = new RecordDef(
										new FieldDef[] {
												new StringFieldDef("EntityName"),
												new StringFieldDef("Entitycode") });
								final String[][] object2;

								object2 = new String[arg0.length][2];

								String str = null;

								try {
									for (int i = 0; i < arg0.length; i++) {
										for (int k = 0; k < 2; k++) {
											if (k == 0) {
												str = arg0[i].getEntity_name();
											} else if (k == 1) {
												str = arg0[i].getEntity_id();
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
								entityNameCB.entityCombo.enable();
							}
						});
			}
		});

		entityNameCB.entityCombo.addListener(new ComboBoxListenerAdapter() {
			public void onSelect(ComboBox comboBox, Record record, int index) {
				panel.clear();
				if (index >= 0) {
					entity = entityNameCB.entityCombo.getRawValue();
					entity_id = entityNameCB.entityCombo.getValue();
				}
			}
		});

		submitButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				if(entityTypeCB.entityDescCB.getValue()==null || entityNameCB.entityCombo.getValue()==null ){
					if(entityTypeCB.entityDescCB.getValue()==null ){
						MessageBox.alert(msgs.info(),msgs.selectEntityType());
					}
					if(entityNameCB.entityCombo.getValue()==null){
						MessageBox.alert(msgs.info(),msgs.selectEntityName());
					}
				}
				else{
					panel.clear();
					Edit();
				}
			}
		});

		cancelButton.addListener(new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				vp.clear();
			}
		});
		
		return vp;
	}

	public void Edit() {
		cbSelectionModel.lock();
		editButton1.disable();
		deletebutton1.disable();
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

										public void onSuccess(
												List<String> result) {
											if (result.size() == 0) {
												cbSelectionModel.unlock();
												editButton1.setDisabled(true);
												deletebutton1.setDisabled(true);
												cbSelectionModel.addListener(new RowSelectionListener() {
															public boolean doBeforeRowSelect(RowSelectionModel sm,int rowIndex,boolean keepExisting,Record record) {
																return true;
															}
															public void onRowDeselect(RowSelectionModel sm,int rowIndex,Record record) {
																if ((sm.getCount() == 1)) {
																	editButton1.setDisabled(false);
																	deletebutton1.setDisabled(false);
																}

																if ((sm.getCount() == 0)) {
																	editButton1.setDisabled(true);
																	deletebutton1.setDisabled(true);
																}

																if ((sm.getCount() > 1)) {
																	editButton1.setDisabled(true);
																	deletebutton1.setDisabled(false);
																}
															}

															public void onRowSelect(RowSelectionModel sm,int rowIndex,Record record) {
																if ((sm.getCount() == 1)) {
																	editButton1.setDisabled(false);
																	deletebutton1.setDisabled(false);
																}

																if ((sm.getCount() == 0)) {
																	editButton1.setDisabled(true);
																	deletebutton1.setDisabled(true);
																}

																if ((sm.getCount() > 1)) {
																	editButton1.setDisabled(true);
																	deletebutton1.setDisabled(false);
																}
															}

															public void onSelectionChange(RowSelectionModel sm) {
																if ((sm.getCount() == 1)) {
																	editButton1.setDisabled(false);
																	deletebutton1.setDisabled(false);
																}

																if ((sm.getCount() == 0)) {
																	editButton1.setDisabled(true);
																	deletebutton1.setDisabled(true);
																}

																if ((sm.getCount() > 1)) {
																	editButton1.setDisabled(true);
																	deletebutton1.setDisabled(false);
																}
															}
														});
											} else {
												values = new String[result.size()];
												for (int i = 0; i < result.size(); i++) {
													values[i] = result.get(i);
													if (values[i].equalsIgnoreCase("create")) {
													}

													if (values[i].equalsIgnoreCase("view")) {
														editButton1.setDisabled(true);
														deletebutton1.setDisabled(true);
													}
													if (values[i].equalsIgnoreCase("update")) {
														cbSelectionModel.unlock();
														deletebutton1.setDisabled(true);

														cbSelectionModel.addListener(new RowSelectionListener() {
																	public boolean doBeforeRowSelect(RowSelectionModel sm,int rowIndex,boolean keepExisting,Record record) {
																		return true;
																	}

																	public void onRowDeselect(RowSelectionModel sm,int rowIndex,Record record) {
																		if ((sm.getCount() > 1) || (sm.getCount() < 1)) {
																			editButton1.setDisabled(true);
																		} else {
																			editButton1.setDisabled(false);
																		}
																	}

																	public void onRowSelect(RowSelectionModel sm,int rowIndex,Record record) {
																		if ((sm.getCount() > 1) || (sm.getCount() < 1)) {
																			editButton1.setDisabled(true);
																		} else {
																			editButton1.setDisabled(false);
																		}
																	}

																	public void onSelectionChange(RowSelectionModel sm) {
																		if ((sm.getCount() > 1) || (sm.getCount() < 1)) {
																			editButton1.setDisabled(true);
																		} else {
																			editButton1.setDisabled(false);
																		}
																	}
																});
													}

													if (values[i].equalsIgnoreCase("delete")) {
														cbSelectionModel.unlock();
														editButton1.setDisabled(true);
														cbSelectionModel.addListener(new RowSelectionListener() {
																	public boolean doBeforeRowSelect(RowSelectionModel sm,int rowIndex,boolean keepExisting,Record record) {
																		return true;
																	}

																	public void onRowDeselect(RowSelectionModel sm,int rowIndex,Record record) {
																		if (sm.getCount() < 1) {
																			deletebutton1.setDisabled(true);
																		} else {
																			deletebutton1.setDisabled(false);
																		}
																	}

																	public void onRowSelect(RowSelectionModel sm,int rowIndex,Record record) {
																		if ((sm.getCount() < 1)) {
																			deletebutton1.setDisabled(true);
																		} else {
																			deletebutton1.setDisabled(false);
																		}
																	}

																	public void onSelectionChange(RowSelectionModel sm) {
																		if ((sm.getCount() < 1)) {
																			deletebutton1.setDisabled(true);
																		} else {
																			deletebutton1.setDisabled(false);
																		}
																	}
																});
													}
												}
											}
										}
									});
						}
					}
				});

		connectTemp.getprogAgeElig(entity_id,
				new AsyncCallback<List<CM_ProgramInfoGetter>>() {
					public void onFailure(Throwable caught) {
						MessageBox.alert(cons.dbError(), caught.getMessage());
					}
					public void onSuccess(List<CM_ProgramInfoGetter> result) {
						if (result.size() == 0) {
							MessageBox.show(new MessageBoxConfig() {
								{
									setTitle(msgs.alert());
									setMsg(msgs.error_norecord(entity));
									setIconCls(MessageBox.INFO);
									setButtons(MessageBox.OK);
									setCallback(new MessageBox.PromptCallback() {
										public void execute(String btnID,
												String text) {
											if (btnID.equals("ok")) {
												onModuleLoad();
											}
										}
									});
								}
							});
						} else {
							RecordDef recordDef = new RecordDef(new FieldDef[] {
									new StringFieldDef("program"),										
									new StringFieldDef("category"),
									new StringFieldDef("age"),
									new StringFieldDef("programId")
									 });

							GridPanel grid = new GridPanel();
							data1 = new Object[result.size()][4];
							for (int i = 0; i < result.size(); i++) {
								data1[i][0]=result.get(i).getProgram_name();
								data1[i][1]=result.get(i).getCategory();
								data1[i][2]=result.get(i).getAge_limit();
								data1[i][3]=result.get(i).getProgram_id();						
							}
							data = data1;
							MemoryProxy proxy = new MemoryProxy(data);
							ArrayReader reader = new ArrayReader(recordDef);
							Store store = new Store(proxy, reader);
							store.load();
							grid.setStore(store);
							BaseColumnConfig[] columns = new BaseColumnConfig[] {
									new CheckboxColumnConfig(cbSelectionModel),
									new ColumnConfig(cons.label_programname(),"program", 80, true, null,"program"),																		
									new ColumnConfig(cons.category(),"category", 70, true),
									new ColumnConfig(cons.agelimit(), "age",70, true) };							
							ColumnModel columnModel = new ColumnModel(columns);
							grid.setColumnModel(columnModel);
							grid.setFrame(true);
							grid.setAutoExpandColumn("program");
							grid.setSelectionModel(cbSelectionModel);
							grid.setWidth(300);
							grid.setHeight(250);
							grid.setTitle(cons.heading_manageprogramAgeElig());
							Toolbar toolbar = new Toolbar();
							toolbar.addFill();
							grid.setTopToolbar(toolbar);
							final ToolbarButton editButton = new ToolbarButton(cons.edit());
							editButton1 = editButton;
							editButton.addListener(new ButtonListenerAdapter() {
								public void onClick(Button button, EventObject e) {
									Record[] records = cbSelectionModel.getSelections();
									if (records.length < 1) {
										MessageBox.alert(msgs.info(), msgs.atleastOneRecord());										
									} else if (records.length > 1) {
										MessageBox.alert(msgs.info(), msgs.onlyOneRecord());
										cbSelectionModel.clearSelections();
									} else if (records.length == 1) {
										Record record = records[0];
										final CM_ProgramInfoGetter input=new CM_ProgramInfoGetter();
										input.setProgram_id(record.getAsString("programId"));
										input.setProgram_name(record.getAsString("program"));
										input.setAge_limit(record.getAsString("age"));
										input.setCategory(record.getAsString("category"));
										input.setEntity_id(entity_id);
										final Label ProgramLabel = new Label();										
										final Label categoryLabel = new Label();
										final NumberField ageNF = new NumberField();
										
										ageNF.setWidth(80);
										ageNF.setAllowBlank(false);
										ageNF.setAllowDecimals(false);
										ageNF.setAllowNegative(false);
										ageNF.setMaxLength(2);
										
										ProgramLabel.setText(input.getProgram_name());										
										categoryLabel.setText(input.getCategory());
										ageNF.setValue(input.getAge_limit());	
										
										Button save = new Button(cons.button_update());	
										Button cancelBtn = new Button(cons.cancelButton());																			

										final FlexTable editFlex = new FlexTable();
										editFlex.setCellSpacing(10);
										editFlex.setWidget(1, 0, new Label(cons.label_programname()));
										editFlex.setWidget(1, 1, ProgramLabel);																				
										editFlex.setWidget(2, 0, new Label(cons.category()));
										editFlex.setWidget(2, 1, categoryLabel);
										editFlex.setWidget(3, 0, new Label(cons.agelimit()));
										editFlex.setWidget(3, 1, ageNF);
										final FlexTable btnTable = new FlexTable();
										btnTable.setWidget(1, 0, save);	
										btnTable.setWidget(1, 1, cancelBtn);	
										editFlex.setWidget(4, 1, btnTable);										
										BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);
										centerData.setMargins(6, 6, 6, 6);
										ScrollPanel sPanel = new ScrollPanel();
										sPanel.setHeight("10em");
										sPanel.add(editFlex);
										final Window window = new Window();
										window.setTitle(cons.heading_manageprogramAgeElig());
										window.setClosable(true);
										window.setWidth(400);
										window.setHeight(210);
										window.setPlain(true);
										window.setLayout(new BorderLayout());
										window.add(sPanel,centerData);
										window.setCloseAction(Window.HIDE);
										window.setModal(true);		
										window1 = window;
										window1.show();
										cancelBtn.addListener(new ButtonListenerAdapter(){
											public void onClick(Button button,EventObject e) {
												window1.close();
											}
										});
										save.addListener(new ButtonListenerAdapter() {
											public void onClick(Button button,EventObject e) {
												if(CommonValidation.validate(ageNF)){				
														MessageBox.alert(msgs.checkAgeField());												
															input.setAge_limit(String.valueOf(ageNF.getValue()));
															input.setModifier_id(modifierid);															
															MessageBox.confirm(confirm,msgs.alert_confirmentries(),new MessageBox.ConfirmCallback() {
																public void execute(String btnID) {
																	if (btnID.matches(cons.yesButton())) {
																		connectTemp.editProgAgeLimit(input,new AsyncCallback<String>() {
																			public void onFailure(Throwable caught) {
																				MessageBox.alert(cons.dbError(),caught.getMessage());
																				cbSelectionModel.clearSelections();
																			}
																			public void onSuccess(String result) {
																				if(result.equals("success")){
																					MessageBox.alert(msgs.success(), msgs.alert_oneditsuccess());
																				}
																				else{
																					MessageBox.alert(msgs.success(), msgs.problemInService());
																				}
																				window1.close();
																				cbSelectionModel.clearSelections();
																				Edit();																																								
																			}
																		});
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
							
							toolbar.addButton(editButton);
							ToolbarButton disable = new ToolbarButton(cons.delete());
							deletebutton1 = disable;
							disable.addListener(new ButtonListenerAdapter() {
								public void onClick(Button button, EventObject e) {
									final Record[] records = cbSelectionModel.getSelections();
									if (records.length < 1) {
										MessageBox.alert(errorMsg, msgs.select_recordsdeletion());
										cbSelectionModel.clearSelections();
									} else {
										MessageBox.confirm(confirm,msgs.alert_ondelete(),new MessageBox.ConfirmCallback() {
											public void execute(String btnID) {
												if (btnID.matches(cons.yesButton())) {
													List<CM_ProgramInfoGetter> deleteInput=new ArrayList<CM_ProgramInfoGetter>();
													for (int i = 0; i < records.length; i++) {	
														CM_ProgramInfoGetter been=new CM_ProgramInfoGetter();
														been.setEntity_id(entity_id);
														been.setProgram_id(records[i].getAsString("programId"));
														been.setCategory(records[i].getAsString("category"));
														been.setAge_limit(records[i].getAsString("age"));
														deleteInput.add(been);
													}
														connectTemp.deleteProgAge(deleteInput,new AsyncCallback<String>() {
															public void onFailure(Throwable caught) {
																MessageBox.alert(cons.dbError(),caught.getMessage());
																cbSelectionModel.clearSelections();
															}
															public void onSuccess(String result) {
																String[]splt=result.split("-");
																if(splt[0].equals("success")){
																	MessageBox.alert(msgs.success(), msgs.deleteSubjectCodeSuccess(splt[1],splt[2],splt[3]));
																}
																else{
																	MessageBox.alert(msgs.info(), msgs.problemInService());
																}
																window1.close();
																cbSelectionModel.clearSelections();
																Edit();																
															}
														});
													}							
												}											
										});
									}
								}
							});

							toolbar.addButton(disable);
							panel.clear();
							panel.add(grid);							
							vp.add(panel);
						}
					}
				});
	}
}
