package in.ac.dei.edrp.client.Applications;

import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.CM_progOfferedBy;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CM_manageMarks;
import in.ac.dei.edrp.client.RPCFiles.CM_manageMarksAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
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
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
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
import com.gwtext.client.widgets.grid.event.RowSelectionListener;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class ProgramApplicationDetails {
	
	

    Validator valid;
    public String userid;
    String[][] object2;
    CM_progOfferedBy pob = new CM_progOfferedBy(userid);
    private final CM_manageMarksAsync marksService = GWT.create(CM_manageMarks.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    
    // First Outer Vertical Panel contains inner vertical panel with (all comboBox and buttons)
    final VerticalPanel vInPanel = new VerticalPanel();

    // Inner Vertical panel which contains all comboBox and button
    public final VerticalPanel vPanel = new VerticalPanel();

    // Panel for containing grid
    HorizontalPanel gridPanel = new HorizontalPanel();
    String pagename = "Program Application Details";
    String[] values;

    // oracle for suggestion box on edit screen
    final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
    constants labels = GWT.create(constants.class);
    messages msg = GWT.create(messages.class);
    String errorMsg = msg.error();
    String correctEntriesMsg = msg.checkFields();
    String failureLabel = msg.failure();
    String congratsLabel = labels.congratulation();
    String dbException = labels.dbError();
    String consEntityName = labels.entityName();
    String consEntityType = labels.entityType();
    String consProgName = labels.label_programname();
    String consAddMarks = labels.labeladdMarks();
    String consEditMarks = labels.labeleditMarks();
    String msgAtleastOneRec = msg.atleastOneRecord();
    String msgOnlyOneRec = msg.onlyOneRecord();
    String button_update = labels.button_update();
    String loading = labels.loading();
    String searching = labels.searching();
    ToolbarButton editButton = new ToolbarButton(consEditMarks);
    Object[][] object1;

    /**
     * Constructor for setting the Value of User ID
     *
     * @param Uid
     */
    public ProgramApplicationDetails(String Uid) {
        this.userid = Uid;
    }

    /**
     * Method to add students' final test marks
     */
    @SuppressWarnings("deprecation")
	public void methodAddMarks(final String function) {
        vPanel.clear();
        vInPanel.clear();
        gridPanel.clear();
        final ComboBox programNameCBox = new ComboBox();
        final ComboBox criteriaTypeCBox = new ComboBox();
        final TextField nameField = new TextField();
        final TextField registrationField = new TextField();
        final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
        final Panel p1 = new Panel();
        final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
        bd.setMargins(6, 6, 6, 6);

        final SuggestBox textValue = new SuggestBox(oracle);

        // Add Button for adding student marks to studentfinalmarks table and
        // inserting these values to studentfinalmeritlist
        final Button addMarksButton = new Button("OK");

        // Edit Button, to edit records (marks for student) one by one. Table
        // changes: Studenfinalmarks and Studentfinalmeritlist
        final Button editMarksButton = new Button(consEditMarks);
        ComboBoxes(programNameCBox);
        programNameCBox.setAllowBlank(false);
        programNameCBox.disable();
        editMarksButton.disable();
        cbSelectionModel.lock();
        editButton.disable();

        loginconnect.getAuthorityOnPage(pagename, userid,
            new AsyncCallback<CM_userInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                }

                public void onSuccess(CM_userInfoGetter[] result) {
                    if (result[0].getAuthority().equalsIgnoreCase("1")) {
                        loginconnect.getsecondaryauthorities(userid, pagename,
                            new AsyncCallback<List<String>>() {
                                public void onFailure(Throwable arg0) {
                                }

                                public void onSuccess(List<String> result) {
                                    if (result.size() == 0) {
//                                        entityTypeCBox.enable();
//                                        entityNameCBox.enable();
                                        programNameCBox.enable();
                                        editMarksButton.enable();
                                        cbSelectionModel.unlock();
                                        editButton.enable();
                                        cbSelectionModel.addListener(new RowSelectionListener() {
                                                public boolean doBeforeRowSelect(
                                                    RowSelectionModel sm,
                                                    int rowIndex,
                                                    boolean keepExisting,
                                                    Record record) {
                                                    return true;
                                                }

                                                public void onRowDeselect(
                                                    RowSelectionModel sm,
                                                    int rowIndex, Record record) {
                                                    if ((sm.getCount() == 1)) {
                                                        editButton.setDisabled(false);
                                                    }

                                                    if ((sm.getCount() == 0)) {
                                                        editButton.setDisabled(true);
                                                    }

                                                    if ((sm.getCount() > 1)) {
                                                        editButton.setDisabled(true);
                                                    }
                                                }

                                                public void onRowSelect(
                                                    RowSelectionModel sm,
                                                    int rowIndex, Record record) {
                                                    if ((sm.getCount() == 1)) {
                                                        editButton.setDisabled(false);
                                                    }

                                                    if ((sm.getCount() == 0)) {
                                                        editButton.setDisabled(true);
                                                    }

                                                    if ((sm.getCount() > 1)) {
                                                        editButton.setDisabled(true);
                                                    }
                                                }

                                                public void onSelectionChange(
                                                    RowSelectionModel sm) {
                                                    if ((sm.getCount() == 1)) {
                                                        editButton.setDisabled(false);
                                                    }

                                                    if ((sm.getCount() == 0)) {
                                                        editButton.setDisabled(true);
                                                    }

                                                    if ((sm.getCount() > 1)) {
                                                        editButton.setDisabled(true);
                                                    }
                                                }
                                            });
                                    } else {
                                        values = new String[result.size()];

                                        for (int i = 0; i < result.size();
                                                i++) {
                                            values[i] = result.get(i);

                                            if ((values[i].equalsIgnoreCase(
                                                        "view")) ||
                                                    (values[i].equalsIgnoreCase(
                                                        "create"))) {
                                                programNameCBox.enable();
                                                editMarksButton.enable();
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "update")) {
                                                programNameCBox.enable();
                                                editMarksButton.enable();
                                                cbSelectionModel.unlock();
                                                editButton.enable();
                                                cbSelectionModel.addListener(new RowSelectionListener() {
                                                        public boolean doBeforeRowSelect(
                                                            RowSelectionModel sm,
                                                            int rowIndex,
                                                            boolean keepExisting,
                                                            Record record) {
                                                            return true;
                                                        }

                                                        public void onRowDeselect(
                                                            RowSelectionModel sm,
                                                            int rowIndex,
                                                            Record record) {
                                                            if ((sm.getCount() > 1) ||
                                                                    (sm.getCount() < 1)) {
                                                                editButton.setDisabled(true);
                                                            } else {
                                                                editButton.setDisabled(false);
                                                            }
                                                        }

                                                        public void onRowSelect(
                                                            RowSelectionModel sm,
                                                            int rowIndex,
                                                            Record record) {
                                                            if ((sm.getCount() > 1) ||
                                                                    (sm.getCount() < 1)) {
                                                                editButton.setDisabled(true);
                                                            } else {
                                                                editButton.setDisabled(false);
                                                            }
                                                        }

                                                        public void onSelectionChange(
                                                            RowSelectionModel sm) {
                                                            if ((sm.getCount() > 1) ||
                                                                    (sm.getCount() < 1)) {
                                                                editButton.setDisabled(true);
                                                            } else {
                                                                editButton.setDisabled(false);
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


        
        /*
         * check the method
         */
        marksService.methodGetPrograms(userid,
                new AsyncCallback<CM_progMasterInfoGetter[]>() {
                    public void onFailure(Throwable arg0) {
                        MessageBox.alert(dbException,
                            arg0.getMessage());
                    }

                    public void onSuccess(
                        final CM_progMasterInfoGetter[] result) {
                        try {
                            RecordDef recordDef = new RecordDef(new FieldDef[] {
                                        new StringFieldDef("Description"),
                                        new StringFieldDef("code")
                                    });

                            String[][] object2 = new String[result.length][2];                            

                            String[][] data = object2;

                            for (int i = 0; i < result.length;
                                    i++) {
                                object2[i][0] = result[i].getProgram_name();
                                object2[i][1] = result[i].getProgram_id();
                            }

                            MemoryProxy proxy = new MemoryProxy(data);

                            ArrayReader reader = new ArrayReader(recordDef);
                            pob.progListStore = new Store(proxy,
                                    reader);
                            pob.progListStore.load();

                            programNameCBox.setStore(pob.progListStore);
                        } catch (Exception e) {
                            System.out.println(
                                "Exception in prog list " + e);
                        }
                    }
                });

        final FormPanel mainPanel = new FormPanel();

        mainPanel.setFrame(true);

        if (function.equalsIgnoreCase("add")) {
            mainPanel.setTitle("Program's Applicant Details");
            mainPanel.setSize("450px", "230px");
        } 
        FlexTable upperFlexTable = new FlexTable();
        
        FlexTable lowerFlexTable = new FlexTable();

        Label entityTypeLabel = new Label("Name");
        Label entityNameLabel = new Label("Faculty Registration Number");
        Label programNameLabel = new Label(consProgName+"*");
        programNameCBox.setEmptyText(consProgName);
        upperFlexTable.setWidget(2, 0, programNameLabel);
        upperFlexTable.setWidget(2, 1, programNameCBox);
        
        lowerFlexTable.setWidget(0, 0, new Label("Search By:"));
        lowerFlexTable.setWidget(2, 0, entityTypeLabel);
        lowerFlexTable.setWidget(2, 1, nameField);
        lowerFlexTable.setWidget(3, 0, entityNameLabel);
        lowerFlexTable.setWidget(3, 1, registrationField);
        
        VerticalPanel verticalPanel = new VerticalPanel();
        
        verticalPanel.add(upperFlexTable);
        
        verticalPanel.setSpacing(20);
        
        verticalPanel.add(lowerFlexTable);
        
        
        mainPanel.add(verticalPanel);

        if (function.equalsIgnoreCase("add")) {
            mainPanel.addButton(addMarksButton);
        } 


        programNameCBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {                                     
                    criteriaTypeCBox.clearValue();
                    vPanel.remove(gridPanel);
                }
            });
      
        criteriaTypeCBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    boolean flag = true;

                    try {
                        programNameCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }                    

                    if (flag == true) {
                        textValue.setValue("");
                    } else {
                        MessageBox.alert(errorMsg, correctEntriesMsg);
                    }

                    vPanel.remove(gridPanel);
                }
            });
        textValue.addFocusListener(new FocusListener() {
                public void onFocus(Widget arg0) {
                    textValue.setText("");
                    vPanel.remove(gridPanel);
                }

                public void onLostFocus(Widget arg0) {
                }
            });

        addMarksButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    boolean flag = true;

                    try {
                        programNameCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }                  
                    if (flag == true) {
                        /**
                         * To get all student list which has been called for final
                         * merit
                         */
                    	marksService.getProgramComponents(userid,programNameCBox.getValue(),
                    			nameField.getText(),registrationField.getText(),
                    			new AsyncCallback<CM_progMasterInfoGetter[]>(){

									public void onFailure(Throwable arg0) {										
									}

									public void onSuccess(
											CM_progMasterInfoGetter[] arg0) {										
										 object1 = new Object[arg0.length][7];
										
										if(arg0.length==0){
											
											MessageBox.alert("Error","No Record Found");
											
											vPanel.remove(gridPanel);
											
										}else{
											
											final GridPanel grid = new GridPanel();											
											final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Registration Number"),
                                                    new StringFieldDef("Faculty Registration Number"),
                                                    new StringFieldDef("Applicant Name"),
                                                    new StringFieldDef("Father Name"),
                                                    new StringFieldDef("Date Of Birth"),
                                                    new StringFieldDef("COS Value"),
                                                    new StringFieldDef("Gender"),
											});
											
											Object str = null;											
											if((nameField.getText().equalsIgnoreCase(""))
													&&(registrationField.getText().equalsIgnoreCase(""))){
												
												for(int i=0;i<arg0.length;i++){											
													 
													for(int j=0;j<7;j++)
													{
														if (j == 0) {
															str = arg0[i].getRegistration_number();
															
	                                                    } else if (j == 1) {
	                                                    	str = arg0[i].getUserDetails()[0];
	                                                        
	                                                    } else if (j == 2) {
	                                                    	str = arg0[i].getUserDetails()[1];
	                                                        
	                                                    } else if (j == 3) {
	                                                    	str = arg0[i].getUserDetails()[2];
	                                                        
	                                                    } else if (j == 4) {
	                                                    	str = arg0[i].getUserDetails()[3];
	                                                        
	                                                    } else if (j == 5) {
	                                                    	str = arg0[i].getUserDetails()[4];
	                                                        
	                                                    } else if(j==6){
	                                                    	
	                                                    	str = arg0[i].getUserDetails()[5];
	                                                    	
	                                                    }
														
														try{
															
														object1[i][j] = str;
														
														}catch (Exception e) {
															System.out
																	.println("error"+e);
														}
														
													}												
													
												}			
												
											}else {
												
												for(int i=0;i<arg0.length;i++){											
													 
													for(int j=0;j<7;j++)
													{
														if (j == 0) {
															str = arg0[i].getRegistration_number();
															
	                                                    } else if (j == 1) {
	                                                    	str = arg0[i].getForm_number();
	                                                        
	                                                    } else if (j == 2) {
	                                                    	str = arg0[i].getAplicant_name();
	                                                        
	                                                    } else if (j == 3) {
	                                                    	str = arg0[i].getFather_name();
	                                                        
	                                                    } else if (j == 4) {
	                                                    	str = arg0[i].getDateofbirth();
	                                                        
	                                                    } else if (j == 5) {
	                                                    	str = arg0[i].getCategory();
	                                                        
	                                                    } else if(j==6){
	                                                    	
	                                                    	str = arg0[i].getGender();
	                                                    	
	                                                    }
														
														try{
															
														object1[i][j] = str;
														
														}catch (Exception e) {
															System.out
																	.println("error"+e);
														}
														
													}												
													
												}
												
											}
											
											
																			
											Object[][] data = object1;

	                                        MemoryProxy proxy = null;

	                                        try {
	                                            proxy = new MemoryProxy(data);
	                                        } catch (Exception e3) {
	                                            System.out.println("e3" + e3);
	                                        }

	                                        ArrayReader reader = new ArrayReader(rDef);
	                                        Store store = new Store(proxy, reader);

	                                        store.load();

	                                        grid.setStore(store);
	                                        
	                                        BaseColumnConfig[] columns = new BaseColumnConfig[] {
	                                                new CheckboxColumnConfig(cbSelectionModel),
	                                                
	                                                new ColumnConfig("Registration Number",
	                                                    "Registration Number", 120,
	                                                    true, null, "entityType"),
	                                                new ColumnConfig("Faculty Registration Number",
	                                                    "Faculty Registration Number", 120, true,
	                                                    null, "BranchName"),
	                                                new ColumnConfig("Applicant Name",
	                                                    "Applicant Name", 120,
	                                                    true, null,
	                                                    "SpecializationName"),
	                                                new ColumnConfig("Father Name",
	                                                    "Father Name", 120,
	                                                    true, null, "entityname"),
	                                                new ColumnConfig("Date Of Birth",
	                                                    "Date Of Birth", 100, true, null,
	                                                    "entitycode"),
	                                                new ColumnConfig("COS Value",
	                                                    "COS Value", 80,
	                                                    true, null, "entityparent"),
	                                                new ColumnConfig("Gender",
	                                                    "Gender",
	                                                    80, true, null, "criteria"),
	                                                
	                                            };

	                                        final ColumnModel columnModel = new ColumnModel(columns);
	                                        grid.setColumnModel(columnModel);

	                                        grid.setFrame(true);
	                                        grid.setStripeRows(true);
	                                        grid.setAutoExpandColumn("entityType");
	                                        grid.setAutoExpandColumn("entityname");
	                                        grid.setAutoExpandColumn("entitycode");
	                                        grid.setAutoExpandColumn("entityparent");
	                                        grid.setAutoExpandColumn("criteria");
	                                        grid.setAutoExpandColumn("BranchName");
	                                        grid.setAutoExpandColumn(
	                                            "SpecializationName");


	                                        grid.setSelectionModel(cbSelectionModel);
	                                        grid.setAutoWidth(true);
	                                        grid.setWidth(850);
	                                        grid.setHeight(280);

	                                        Toolbar topToolBar = new Toolbar();
	                                        topToolBar.addFill();
	                                        
	                                        final ToolbarButton editButton1 = new ToolbarButton(
                                            "View");
	                                        editButton1.setDisabled(true);

	                                        editButton = editButton1;
	                                        
	                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button Button, EventObject e) {                                                	
                                                	
                                                	Record[] records = cbSelectionModel.getSelections();
                                                	
                                                	 Record record = records[0];
                                                	
                                                	final String[] Univ = new String[7];

                                                    Univ[0] = record.getAsString(
                                                            "Registration Number");
                                                    Univ[1] = record.getAsString(
                                                            "Faculty Registration Number");                                                    
                                                    Univ[2] = record.getAsString(
                                                    "Applicant Name");
                                                    Univ[3] = record.getAsString(
                                                    "Father Name");
                                                    Univ[4] = record.getAsString(
                                                    "Date Of Birth");
                                                    Univ[5] = record.getAsString(
                                                    "COS Value");
                                                    Univ[6] = record.getAsString(
                                                    "Gender");
                                                    
                                                    VerticalPanel panel2 = new VerticalPanel();

                                                    FlexTable editInstiTable =
                                                        new FlexTable();
                                                    
                                                    final FlexTable marksInfo =
                                                        new FlexTable();
                                                    
                                                    final FlexTable paperInfo =
                                                        new FlexTable();
                                                    
                                                    marksInfo.setBorderWidth(1);
                                                    
                                                    paperInfo.setBorderWidth(1);
                                                    
                                                    paperInfo.setWidget(0, 0, new Label("Papers Opted"));
                                                    
                                                    marksInfo.setWidget(0, 0, new Label("Component Name"));
                                                    marksInfo.setWidget(0, 1, new Label("Marks Obtained"));
                                                    marksInfo.setWidget(0, 2, new Label("Max. Marks"));
                                                    marksInfo.setWidget(0, 3, new Label("Special Weightage"));
                                                    
                                                    editInstiTable.setWidget(0, 0, new Label("Registration Number"));
                                                    editInstiTable.setWidget(0, 1, new Label(Univ[0]));
                                                    editInstiTable.setWidget(0, 2, new Label("Faculty Registration Number"));
                                                    editInstiTable.setWidget(0, 3, new Label(Univ[1]));
                                                    editInstiTable.setWidget(1, 0, new Label("Applicant Name"));
                                                    editInstiTable.setWidget(1, 1, new Label(Univ[2]));
                                                    editInstiTable.setWidget(1, 2, new Label("Father Name"));
                                                    editInstiTable.setWidget(1, 3, new Label(Univ[3]));
                                                    editInstiTable.setWidget(2, 0, new Label("Date of Birth"));
                                                    editInstiTable.setWidget(2, 1, new Label(Univ[4]));
                                                    editInstiTable.setWidget(2, 2, new Label("COS Value"));
                                                    editInstiTable.setWidget(2, 3, new Label(Univ[5]));
                                                    editInstiTable.setWidget(3, 0, new Label("Gender"));
                                                    editInstiTable.setWidget(3, 1, new Label(Univ[6]));
                                                    editInstiTable.setBorderWidth(1);
                                                    
                                                    marksService.getApplicantsDetails(Univ[0], 
                                                    		new AsyncCallback<CM_progMasterInfoGetter[]>(){

														public void onFailure(
																Throwable arg0) {
															
														}

														public void onSuccess(
																final CM_progMasterInfoGetter[] result) {
															
															
															for(int i=0;i<result.length;i++){
																
																final int k=i;
																
																marksInfo.setWidget(i+1, 0, 
																		new Label(result[i].getDescription()));
																marksInfo.setWidget(i+1, 1, 
																		new Label(result[i].getMarksObtained()));
																marksInfo.setWidget(i+1, 2, 
																		new Label(result[i].getTotalMarks()));
																
																marksService.getcomponentWeightage(Univ[0],
																		result[i].getComponent_id(),
																		new AsyncCallback<CM_progMasterInfoGetter[]>(){

																			public void onFailure(
																					Throwable arg0) {
																				
																			}

																			public void onSuccess(
																					CM_progMasterInfoGetter[] arg0) {
																				
																				if (arg0.length==0) {
																					
																					marksInfo.setWidget(k+1, 3, 
																							new Label("No"));
																					
																				}else{
																					
																					marksInfo.setWidget(k+1, 3, 
																							new Label("Yes"));
																					
																				}
																				
																			}
																			
																		});
																
																
															}
														}
                                                    	
                                                    });
                                                    
                                                    marksService.getPaperCodes(Univ[0],
                                                    		new AsyncCallback<CM_progMasterInfoGetter[]>(){

														public void onFailure(
																Throwable arg0) {
															
														}

														public void onSuccess(
																CM_progMasterInfoGetter[] arg0) {
															for(int i=0;i<arg0.length;i++){																
																paperInfo.setWidget(i+1, 0, 
																		new Label(arg0[i].getPaper_description()));																
															}										
															
														}
                                                    	
                                                    }); 
                                                    
                                                    
                                                    panel2.add(new Label("Personal Details"));
                                                    panel2.add(editInstiTable);
                                                    panel2.add(new Label("Academic Details"));
                                                    panel2.add(marksInfo);
                                                    panel2.add(new Label("Paper Options"));
                                                    panel2.add(paperInfo);
                                                    panel2.setSpacing(5);
                                                    
                                                    
                                                    p1.clear();
                                                    p1.add(panel2);
//                                                    p1.add(marksInfo);
//                                                    p1.add(paperInfo);
                                                    
                                                    Button b1 = new Button(
                                                    "OK");
                                                    
                                                    final Window window = new Window();
                                                    window.setTitle("Applicant Details");
                                                    window.setWidth(900);
                                                    window.setHeight(500);
                                                    window.setResizable(false);
                                                    window.setLayout(new BorderLayout());
                                                    window.setPaddings(5);
                                                    window.setButtonAlign(Position.CENTER);
                                                    window.addButton(b1);

                                                    window.setAutoScroll(true);
                                                    window.add(p1, bd);
                                                    window.setCloseAction(Window.CLOSE);
                                                    window.setPlain(true);
                                                    window.setFrame(true);
                                                    window.setClosable(true);
                                                    window.setModal(true);
                                                    window.show(Button.getId());
                                                    
                                                    b1.addListener(new ButtonListenerAdapter() {
                                                        public void onClick(
                                                            Button button,
                                                            EventObject e) {                                                        	
                                                        	window.close();
                                                        	cbSelectionModel.clearSelections();
                                                        	
                                                        }
        	                                        });
                                                	
                                                }
	                                        });
	                                        topToolBar.addButton(editButton);                                 

	                                        grid.setTopToolbar(topToolBar);	                                      
	                                        grid.setTitle("Details");
	                                        gridPanel.clear();
	                                        gridPanel.add(grid);
	                                        vPanel.add(gridPanel);									
										}
											
									}
                    				
                    			});

                    } else {
                        MessageBox.alert(errorMsg, correctEntriesMsg);
                    }
                }
            });

        vPanel.clear();
        vInPanel.clear();
        gridPanel.clear();
        vPanel.setSpacing(20);
        vInPanel.add(mainPanel);
        vPanel.add(vInPanel);

    }

    public void methodSetEntityNameProp(ComboBox collegeCenterSelect) {
        collegeCenterSelect.setForceSelection(true);
        collegeCenterSelect.setMinChars(1);
        collegeCenterSelect.setDisplayField("entity_name");
        collegeCenterSelect.setValueField("entity_id");
        collegeCenterSelect.setMode(ComboBox.LOCAL);
        collegeCenterSelect.setTriggerAction(ComboBox.ALL);
        collegeCenterSelect.setEmptyText(consEntityType);
        collegeCenterSelect.setLoadingText(loading);
        collegeCenterSelect.setTypeAhead(true);
        collegeCenterSelect.setSelectOnFocus(true);
        collegeCenterSelect.setHideTrigger(false);
        collegeCenterSelect.setAllowBlank(false);
    }

    public void methodAttComboProp(ComboBox attComboBox) {
        attComboBox.setAllowBlank(false);
        attComboBox.setForceSelection(true);
        attComboBox.setMinChars(1);
        attComboBox.setDisplayField("Att");
        attComboBox.setValueField("id");
        attComboBox.setMode(ComboBox.LOCAL);
        attComboBox.setTriggerAction(ComboBox.ALL);
        attComboBox.setEmptyText(labels.labelAttendance());
        attComboBox.setLoadingText(loading);
        attComboBox.setTypeAhead(true);
        attComboBox.setSelectOnFocus(true);
        attComboBox.setWidth(190);
        attComboBox.setHideTrigger(false);

        RecordDef recordDef = new RecordDef(new FieldDef[] {
                    new StringFieldDef("Att"), new StringFieldDef("id")
                });

        String[][] object2 = new String[2][2];

        String[][] data = object2;

        object2[0][0] = "Present";
        object2[0][1] = "P";
        object2[1][0] = "Absent";
        object2[1][1] = "A";

        MemoryProxy proxy = new MemoryProxy(data);

        ArrayReader reader = new ArrayReader(recordDef);
        Store store = new Store(proxy, reader);
        store.load();

        attComboBox.setStore(store);
    }

    public void methodMarksFieldsProp(NumberField num) {
        num.setAllowNegative(false);
        num.setDecimalPrecision(2);
        num.setAllowBlank(false);
        num.setMaxLength(5);
    }

    public void methodCriteriaProp(ComboBox criteriaTypeCBox) {
        criteriaTypeCBox.setForceSelection(true);

        criteriaTypeCBox.setMinChars(1);
        criteriaTypeCBox.setFieldLabel("Entity");
        criteriaTypeCBox.setDisplayField("crieteria_value");
        criteriaTypeCBox.setMode(ComboBox.LOCAL);
        criteriaTypeCBox.setTriggerAction(ComboBox.ALL);
        criteriaTypeCBox.setEmptyText(consEntityType);
        criteriaTypeCBox.setLoadingText(loading);
        criteriaTypeCBox.setTypeAhead(true);
        criteriaTypeCBox.setSelectOnFocus(true);
        criteriaTypeCBox.setHideTrigger(false);
    }

    public void methodLoadOracle(String criteria, String program_id, String entity_id) {
        marksService.methodPopulateSuggestion(criteria, program_id,
            entity_id,
            new AsyncCallback<String[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(failureLabel, arg0.getMessage());
                }

                public void onSuccess(String[] arg0) {
                    oracle.clear();

                    for (int i = 0; i < arg0.length; i++) {
                        oracle.add(arg0[i]);
                    }
                }
            });
    }

    public void ComboBoxes(ComboBox comboBox) {
        comboBox.setForceSelection(true);
        comboBox.setMinChars(1);
        comboBox.setDisplayField("Description");
        comboBox.setValueField("code");
        comboBox.setMode(ComboBox.LOCAL);
        comboBox.setTriggerAction(ComboBox.ALL);
        comboBox.setLoadingText("Searching...");
        comboBox.setTypeAhead(true);
        comboBox.setSelectOnFocus(true);
        comboBox.setWidth(180);
        comboBox.setHideTrigger(false);
        comboBox.setReadOnly(true);
    }

	
	

}
