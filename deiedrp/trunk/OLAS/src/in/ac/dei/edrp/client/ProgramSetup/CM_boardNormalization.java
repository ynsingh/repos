package in.ac.dei.edrp.client.ProgramSetup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
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

import in.ac.dei.edrp.client.CM_boardNormalizationGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.CM_progTermDetails;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CM_boardConnect;
import in.ac.dei.edrp.client.RPCFiles.CM_boardConnectAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.summarysheet.CommonValidation;

import java.util.List;


/**
 * @author Manpreet Kaur
 */

/**
 * Class for setting normalization factor for different boards
 */
public class CM_boardNormalization {
    HorizontalPanel HoriPanel = new HorizontalPanel();
    final VerticalPanel VertiPanel = new VerticalPanel();
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
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
    String msgAtleastOneRec = msg.atleastOneRecord();
    String msgOnlyOneRec = msg.onlyOneRecord();
    String consBoardName = labels.labelBoardName();
    String loading = labels.loading();
    String searching = labels.searching();
    HorizontalPanel ProgTermHorizontalPanel = new HorizontalPanel();
    public HorizontalPanel ProgTermHorizontalPanel1 = new HorizontalPanel();
    FlexTable ProgTermflexTable = new FlexTable();
    Validator validator = new Validator();
    Store progNameStore;
    Store boardStore;
    Store compStore;
    String pagename = "Program Board";
    String[] values;
    ToolbarButton editButton = new ToolbarButton("Edit");
    ToolbarButton deletebutton = new ToolbarButton("Delete");
    private final CM_boardConnectAsync connectBoardService = GWT.create(CM_boardConnect.class);
    public String userid;

    /**
     * Constructor for setting the Value of User ID
     * @param Uid
     */
    public CM_boardNormalization(String Uid) {
        this.userid = Uid;
    }

    /*
     * method to add normalization factor
     */
    public void methodBoardNormalization(final String action) {
        final FormPanel boardNormDetailsForm = new FormPanel();

        final ComboBox programComboBox = new ComboBox();
        final ComboBox entityComboBox = new ComboBox();
        final ComboBox compComboBox = new ComboBox();
        final ComboBox boardComboBox = new ComboBox();
        final NumberField factorField = new NumberField();
        Button addButton = new Button(labels.addButton());
        final Button okButton = new Button(labels.okButton());
        final FlexTable tableFlexTable = new FlexTable();
        final VerticalPanel gridVPanel = new VerticalPanel();

        final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
        final Panel p1 = new Panel();
        final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
        bd.setMargins(6, 6, 6, 6);

        entityComboBox.disable();
        compComboBox.disable();
        programComboBox.disable();
        okButton.disable();
        cbSelectionModel.lock();
        editButton.disable();
        deletebutton.disable();

        factorField.setAllowBlank(false);
        factorField.setMaxLength(3);
        factorField.setDecimalPrecision(1);
        factorField.setReadOnly(false);

        CM_progTermDetails ptd = new CM_progTermDetails(userid);
        /*
         * setting properties of program name list combobox
         */
        ptd.methodProgComboSettings(programComboBox);

        /*
         * setting properties of entity name list combobox
         */
        methodEntityComboSettings(entityComboBox);

        /*
         * setting properties of branch name list combobox
         */
        methodComponentComboSettings(compComboBox);

        /*
         * setting properties of branch name list combobox
         */
        methodBoardComboSettings(boardComboBox);

        methodEntityPopulate(entityComboBox);
        
        programComboBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                	 methodCompList(programComboBox.getValue(), entityComboBox.getValue(),compComboBox);                	 
                }
            });

        entityComboBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                	 if (action.equalsIgnoreCase("add")) {
                         boardNormDetailsForm.setSize("400px", "220px");
                         methodProgramPopulate(entityComboBox.getValue(),programComboBox);
                         
                     } else {
                         boardNormDetailsForm.setSize("350px", "200px");         
                         methodProgramPopulateManage(entityComboBox.getValue(),programComboBox);
                     }      
                }
            });
        compComboBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    methodboardList(boardComboBox);
                }
            });

        boardNormDetailsForm.setFrame(true);
        boardNormDetailsForm.setBorder(true);

        if (action.equalsIgnoreCase("add")) {
            boardNormDetailsForm.setTitle(labels.label_boardNormFactor());
        } else {
            boardNormDetailsForm.setTitle(labels.label_manageboardNormFactor());
        }

        tableFlexTable.clear();
        tableFlexTable.setWidget(3, 0, new Label(consEntityName));
        tableFlexTable.setWidget(3, 1, entityComboBox);
        tableFlexTable.setWidget(4, 0, new Label(consProgName));
        tableFlexTable.setWidget(4, 1, programComboBox);
        tableFlexTable.setWidget(5, 0,new Label(labels.select() + " " + labels.component()));
        tableFlexTable.setWidget(5, 1, compComboBox);
        tableFlexTable.setWidget(6, 0, new Label(labels.selectBoard()));
        tableFlexTable.setWidget(6, 1, boardComboBox);

        if (action.equalsIgnoreCase("add")) {
            tableFlexTable.setWidget(7, 0,
                new Label(labels.label_enterNormFactor()));
            tableFlexTable.setWidget(7, 1, factorField);
        }

        loginconnect.getAuthorityOnPage(pagename.trim(), userid,
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
                                        entityComboBox.enable();
                                        compComboBox.enable();
                                        programComboBox.enable();
                                        okButton.enable();
                                        cbSelectionModel.unlock();
                                        editButton.setDisabled(true);
                                        deletebutton.setDisabled(true);                                        
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
                                                        deletebutton.setDisabled(false);
                                                    }

                                                    if ((sm.getCount() == 0)) {
                                                        editButton.setDisabled(true);
                                                        deletebutton.setDisabled(true);
                                                    }

                                                    if ((sm.getCount() > 1)) {
                                                        editButton.setDisabled(true);
                                                        deletebutton.setDisabled(false);
                                                    }
                                                }

                                                public void onRowSelect(
                                                    RowSelectionModel sm,
                                                    int rowIndex, Record record) {
                                                    if ((sm.getCount() == 1)) {
                                                        editButton.setDisabled(false);
                                                        deletebutton.setDisabled(false);
                                                    }

                                                    if ((sm.getCount() == 0)) {
                                                        editButton.setDisabled(true);
                                                        deletebutton.setDisabled(true);
                                                    }

                                                    if ((sm.getCount() > 1)) {
                                                        editButton.setDisabled(true);
                                                        deletebutton.setDisabled(false);
                                                    }
                                                }

                                                public void onSelectionChange(
                                                    RowSelectionModel sm) {
                                                    if ((sm.getCount() == 1)) {
                                                        editButton.setDisabled(false);
                                                        deletebutton.setDisabled(false);
                                                    }

                                                    if ((sm.getCount() == 0)) {
                                                        editButton.setDisabled(true);
                                                        deletebutton.setDisabled(true);
                                                    }

                                                    if ((sm.getCount() > 1)) {
                                                        editButton.setDisabled(true);
                                                        deletebutton.setDisabled(false);
                                                    }
                                                }
                                            });
                                    } else {
                                        values = new String[result.size()];
                                        for (int i = 0; i < result.size();i++) {
                                            values[i] = result.get(i);
                                            if (values[i].equalsIgnoreCase("create")) {
                                                entityComboBox.enable();
                                                compComboBox.enable();
                                                programComboBox.enable();
                                                okButton.enable();
                                            }

                                            if (values[i].equalsIgnoreCase("view")) {
                                                entityComboBox.enable();
                                                compComboBox.enable();
                                                programComboBox.enable();
                                                okButton.enable();
                                            }

                                            if (values[i].equalsIgnoreCase("update")) {
                                                entityComboBox.enable();
                                                compComboBox.enable();
                                                programComboBox.enable();
                                                okButton.enable();
                                                cbSelectionModel.unlock();
                                                editButton.setDisabled(false);                                            
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

                                            if (values[i].equalsIgnoreCase("delete")) {
                                                entityComboBox.enable();
                                                compComboBox.enable();
                                                programComboBox.enable();
                                                okButton.enable();
                                                cbSelectionModel.unlock();
                                                deletebutton.setDisabled(false);                                               
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
                                                            if (sm.getCount() < 1) {
                                                                deletebutton.setDisabled(true);
                                                            } else {
                                                                deletebutton.setDisabled(false);
                                                            }
                                                        }

                                                        public void onRowSelect(
                                                            RowSelectionModel sm,
                                                            int rowIndex,
                                                            Record record) {
                                                            if ((sm.getCount() < 1)) {
                                                                deletebutton.setDisabled(true);
                                                            } else {
                                                                deletebutton.setDisabled(false);
                                                            }
                                                        }

                                                        public void onSelectionChange(
                                                            RowSelectionModel sm) {
                                                            if ((sm.getCount() < 1)) {
                                                                deletebutton.setDisabled(true);
                                                            } else {
                                                                deletebutton.setDisabled(false);
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
         * Adding handler to the save button
         */
        addButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    if(CommonValidation.validate(entityComboBox)){                    	
                    	if(CommonValidation.validate(programComboBox)){
                    		if(CommonValidation.validate(compComboBox)){
                    			if(CommonValidation.validate(boardComboBox)){
                    				if(factorField.getValue()!=null || !(String.valueOf(factorField.getValue()).equals(""))){                    				                                      
                    					if(CommonValidation.validate(factorField)){                    						
	                    			         	MessageBox.confirm(msg.confirm(),	msg.wantToContinue(),new MessageBox.ConfirmCallback() {
	                            					public void execute(String btnID) {
	                            						if (btnID.matches(labels.yesButton())) {
	                            							  CM_boardNormalizationGetter object = new CM_boardNormalizationGetter();
	                            		                        object.setProgram_id(programComboBox.getValue());
	                            		                        object.setEntity_id(entityComboBox.getValue());
	                            		                        object.setComponent_id(compComboBox.getValue());
	                            		                        object.setBoard_id(boardComboBox.getValue());
	                            		                        object.setNormalization_factor(factorField.getValueAsString());
	
	                            		                        connectBoardService.methodAddBoardNormalizationFactor(object,
	                            		                            new AsyncCallback<String>() {
	                            		                                public void onFailure(Throwable arg0) {
	                            		                                    MessageBox.alert(failureLabel,
	                            		                                        arg0.getMessage());
	                            		                                }
	
	                            		                                public void onSuccess(String arg0) {
	                            		                                    MessageBox.alert(congratsLabel,
	                            		                                        msg.alert_successfulentry(),
	                            		                                        new AlertCallback() {
	                            		                                            public void execute() {
	                            		                                                programComboBox.reset();
	                            		                                                entityComboBox.reset();
	                            		                                                compComboBox.reset();
	                            		                                                boardComboBox.reset();
	
	                            		                                                try {
	                            		                                                    factorField.setValue("");
	                            		                                                } catch (Exception e) {
	                            		                                                }
	
	                            		                                                factorField.clearInvalid();
	                            		                                            }
	                            		                                        });
	                            		                                }
	                            		                            });
	                            						}
	                            					}
	                            				});
                    					}else MessageBox.alert(msg.normalizationDigitExceed());
                    				}else MessageBox.alert(msg.selectNoarmalization());
                    			}else MessageBox.alert(msg.selectBoard());
                    		}else MessageBox.alert(msg.selectComponnt());
                    	}else MessageBox.alert(msg.selectProgramName());
                    }else MessageBox.alert(msg.selectEntityName());                                                                         
                }
            });

        /*
         * Adding handler to the ok button
         */
        okButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    final GridPanel grid = new GridPanel();
                    CM_boardNormalizationGetter object = new CM_boardNormalizationGetter();

                    if (!validator.nullValidator(programComboBox.getRawValue())) {
                        object.setProgram_id(programComboBox.getValue());

                        if (!validator.nullValidator(
                                    entityComboBox.getRawValue())) {
                            object.setEntity_id(entityComboBox.getValue());
                        } else {
                            object.setEntity_id("%");
                        }
                        if (!validator.nullValidator(compComboBox.getRawValue())) {
                            object.setComponent_id(compComboBox.getValue());
                        } else {
                            object.setComponent_id("%");
                        }

                        if (!validator.nullValidator(
                                    boardComboBox.getRawValue())) {
                            object.setBoard_id(boardComboBox.getValue());
                        } else {
                            object.setBoard_id("%");
                        }
                        connectBoardService.methodboardListForManage(object,
                            new AsyncCallback<CM_boardNormalizationGetter[]>() {
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(failureLabel,
                                        arg0.getMessage());
                                }

                                public void onSuccess(
                                    CM_boardNormalizationGetter[] arg0) {
                                    if (arg0.length == 0) {
                                        grid.setTitle(msg.error_record());
                                    } else {
                                        grid.setTitle(labels.label_boardNormFactor());
                                    }
                                    final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("program_name"),
                                                new StringFieldDef("entity_name"),                                              
                                                new StringFieldDef("description"),
                                                new StringFieldDef("board_name"),
                                                new StringFieldDef("normalization_factor"),
                                                new StringFieldDef("program_id"),
                                                new StringFieldDef("entity_id"),
                                                new StringFieldDef("component_id"),
                                                new StringFieldDef("board_id")
                                            });

                                    Object[][] object1 = new Object[arg0.length][9];

                                    String str = null;

                                    try {
                                        for (int i = 0; i < arg0.length; i++) {
                                            for (int k = 0; k < 9; k++) {
                                                if (k == 0) {
                                                    str = arg0[i].getProgram_name();                                                   
                                                } else if (k == 1) {
                                                    str = arg0[i].getEntity_name();
                                                } 
                                                else if (k == 2) {
                                                    str = arg0[i].getDescription();
                                                } else if (k == 3) {
                                                    str = arg0[i].getBoard_name();
                                                } else if (k == 4) {
                                                    str = arg0[i].getNormalization_factor();
                                                } else if (k == 5) {
                                                    str = arg0[i].getProgram_id();
                                                } else if (k == 6) {
                                                    str = arg0[i].getEntity_id();
                                                }
                                                else if (k == 7) {
                                                    str = arg0[i].getComponent_id();                                                  
                                                } else if (k == 8) {                                                	
                                                    str = arg0[i].getBoard_id();
                                                }

                                                object1[i][k] = str;
                                                
                                            }
                                        }
                                    } catch (Exception e2) {
                                        System.out.println("e2     " + e2);
                                    }

                                    Object[][] data = object1;

                                    MemoryProxy proxy = null;

                                    try {
                                        proxy = new MemoryProxy(data);
                                    } catch (Exception e3) {
                                        System.out.println("e3          " + e3);
                                    }

                                    ArrayReader reader = new ArrayReader(rDef);
                                    Store store = new Store(proxy, reader);

                                    store.load();
                                    grid.setStore(store);

                                    try {
                                        BaseColumnConfig[] columns = new BaseColumnConfig[] {
                                                new CheckboxColumnConfig(cbSelectionModel),
                                                new ColumnConfig(consProgName,
                                                    "program_name", 150, true,
                                                    null, "program_name"),
                                                new ColumnConfig(consEntityName,
                                                    "entity_name", 170, true,
                                                    null, "entity_name"),
                                                new ColumnConfig(labels.component(),
                                                    "description", 150, true,
                                                    null, "description"),
                                                new ColumnConfig(consBoardName,
                                                    "board_name", 150, true,
                                                    null, "board_name"),
                                                new ColumnConfig(labels.label_boardNormFactor(),
                                                    "normalization_factor",
                                                    150, true, null,
                                                    "normalization_factor")
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }

                                    grid.setFrame(true);
                                    grid.setStripeRows(true);

                                    grid.setSelectionModel(cbSelectionModel);
                                    grid.setAutoWidth(true);
                                    grid.setWidth(807);
                                    grid.setHeight(230);

                                    Toolbar topToolBar = new Toolbar();
                                    topToolBar.addFill();

                                    final ToolbarButton editButton1 = new ToolbarButton(labels.edit());
                                    editButton1.setDisabled(true);

                                    editButton = editButton1;

                                    editButton1.addListener(new ButtonListenerAdapter() {
                                            public void onClick(
                                                Button editButton, EventObject e) {
                                                try {
                                                    Record[] records = null;
                                                    records = cbSelectionModel.getSelections();

                                                    if (records.length < 1) {
                                                        MessageBox.alert(errorMsg,
                                                            msgAtleastOneRec);
                                                    } else if (records.length > 1) {
                                                        MessageBox.alert(errorMsg,
                                                            msgOnlyOneRec);
                                                    } else if (records.length == 1) {
                                                        final Record record = records[0];

                                                        FlexTable editTable = new FlexTable();

                                                        final NumberField factorField =
                                                            new NumberField();

                                                        /*
                                                         * setting validations
                                                         */
                                                        factorField.setAllowBlank(false);
                                                        factorField.setMaxLength(6);
                                                        factorField.setDecimalPrecision(4);
                                                        factorField.setReadOnly(false);

                                                        factorField.setValue(record.getAsFloat(
                                                                "normalization_factor"));

                                                        editTable.clear();
                                                        editTable.setWidget(3,0,  new Label(labels.label_programname()));
                                                        editTable.setWidget(3,1, new Label(record.getAsString( "program_name")));
                                                        editTable.setWidget(4, 0, new Label(labels.entityName()));
                                                        editTable.setWidget(4,1,new Label(record.getAsString("entity_name")));
                                                        editTable.setWidget(5,0,new Label(labels.component()));
                                                        editTable.setWidget(5,1,new Label(record.getAsString("description")));
                                                        editTable.setWidget(6,0,new Label(consBoardName +" : "));
                                                        editTable.setWidget(6,1,new Label(record.getAsString("board_name")));
                                                        editTable.setWidget(7,0,new Label(labels.label_boardNormFactor()));
                                                        editTable.setWidget(7,1, factorField);
                                                        p1.clear();
                                                        p1.add(editTable);

                                                        Button windowUpdateButton =
                                                            new Button(labels.edit());
                                                        Button windowResetButton =
                                                            new Button(labels.resetButton());
                                                        final Window window = new Window();
                                                        window.setTitle(labels.labelProgTermDetails());
                                                        window.setWidth(550);
                                                        window.setHeight(220);
                                                        window.setResizable(false);
                                                        window.setLayout(new BorderLayout());
                                                        window.setPaddings(5);
                                                        window.setButtonAlign(Position.CENTER);
                                                        window.addButton(windowUpdateButton);
                                                        window.addButton(windowResetButton);
                                                        window.setAutoScroll(true);
                                                        window.add(p1, bd);
                                                        window.setCloseAction(Window.CLOSE);
                                                        window.setPlain(true);
                                                        window.setFrame(true);
                                                        window.setClosable(true);
                                                        window.setModal(true);

                                                        window.show(editButton.getId());
                                                        //Adding handler to reset button of edit window 
                                                        windowResetButton.addListener(new ButtonListenerAdapter() {
                                                                public void onClick(
                                                                    Button button,
                                                                    EventObject e) {
                                                                    factorField.setValue(record.getAsFloat(
                                                                            "normalization_factor"));
                                                                }
                                                            });

                                                        //Adding handler to update button of edit window 
                                                        windowUpdateButton.addListener(new ButtonListenerAdapter() {
                                                                public void onClick(
                                                                    Button button,
                                                                    EventObject e) {
                                                                    Boolean flag =
                                                                        true;

                                                                    try {
                                                                        factorField.validate();
                                                                    } catch (Exception e1) {
                                                                        flag = false;
                                                                    }

                                                                    if (flag == true) {
                                                                        CM_boardNormalizationGetter object =
                                                                            new CM_boardNormalizationGetter();

                                                                        object.setNormalization_factor(factorField.getValueAsString());
                                                                        object.setProgram_id(record.getAsString(
                                                                                "program_id"));
                                                                        object.setEntity_id(record.getAsString(
                                                                                "entity_id"));
                                                                        object.setComponent_id(record.getAsString(
                                                                                "component_id"));
                                                                        object.setBoard_id(record.getAsString(
                                                                                "board_id"));

                                                                        connectBoardService.methodNormalizationFactorUpdate(object,
                                                                            new AsyncCallback<String>() {
                                                                                public void onFailure(
                                                                                    Throwable arg0) {
                                                                                    MessageBox.alert(failureLabel,
                                                                                        arg0.getMessage());
                                                                                }

                                                                                public void onSuccess(
                                                                                    String arg0) {
                                                                                    MessageBox.alert(congratsLabel,
                                                                                        msg.alert_oneditsuccess(),
                                                                                        new AlertCallback() {
                                                                                            public void execute() {
                                                                                                okButton.fireEvent(
                                                                                                    "click");
                                                                                                cbSelectionModel.clearSelections();
                                                                                                window.close();
                                                                                            }
                                                                                        });
                                                                                }
                                                                            });
                                                                    } else {
                                                                        MessageBox.alert(errorMsg,
                                                                            correctEntriesMsg);
                                                                    }
                                                                }
                                                            });
                                                    }
                                                } catch (Exception ex) {
                                                    System.out.println(
                                                        "here is the exception " +
                                                        ex);
                                                }
                                            }
                                        });

                                    final ToolbarButton deletebutton1 = new ToolbarButton(labels.delete());

                                    deletebutton1.setDisabled(true);

                                    deletebutton = deletebutton1;

                                    deletebutton1.addListener(new ButtonListenerAdapter() {
                                            public void onClick(
                                                Button delButton, EventObject e) {
                                                Record[] records = cbSelectionModel.getSelections();

                                                if (records.length < 1) {
                                                    MessageBox.alert(errorMsg,
                                                        msg.select_recordsdeletion());
                                                } else if (records.length > 1) {
                                                    MessageBox.alert(errorMsg,
                                                        msg.onlyOneDelete());
                                                } else {
                                                    final Record record = records[0];
                                                    final CM_boardNormalizationGetter object =
                                                        new CM_boardNormalizationGetter();

                                                    object.setProgram_id(record.getAsString(
                                                            "program_id"));
                                                    object.setEntity_id(record.getAsString(
                                                            "entity_id"));
                                                    object.setComponent_id(record.getAsString(
                                                            "component_id"));
                                                    object.setBoard_id(record.getAsString(
                                                            "board_id"));
                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setTitle(msg.confirm());
                                                                setMsg(msg.alert_ondelete());
                                                                setIconCls(MessageBox.QUESTION);
                                                                setButtons(MessageBox.YESNO);
                                                                setCallback(new MessageBox.PromptCallback() {
                                                                        public void execute(
                                                                            String btnID,
                                                                            String text) {
                                                                            if (btnID.equals(
                                                                                        "yes")) {
                                                                                connectBoardService.methodNormalizationFactorDelete(object,
                                                                                    new AsyncCallback<String>() {
                                                                                        public void onFailure(
                                                                                            Throwable arg0) {
                                                                                            MessageBox.alert(failureLabel,
                                                                                                arg0.getMessage());
                                                                                        }

                                                                                        public void onSuccess(
                                                                                            String arg0) {
                                                                                            MessageBox.alert(congratsLabel,
                                                                                                msg.alert_ondeletesuccess(),
                                                                                                new AlertCallback() {
                                                                                                    public void execute() {
                                                                                                        okButton.fireEvent(
                                                                                                            "click");
                                                                                                        cbSelectionModel.clearSelections();
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                    });
                                                                            } else if (btnID.equals(
                                                                                        "no")) {
                                                                            }
                                                                        }
                                                                    });
                                                            }
                                                        });
                                                }
                                            }
                                        });

                                    topToolBar.addButton(editButton);
                                    topToolBar.addButton(deletebutton);

                                    grid.setTopToolbar(topToolBar);

                                    gridVPanel.clear();
                                    gridVPanel.add(grid);
                                }
                            });
                    } else {
                        MessageBox.alert(errorMsg, msg.programNotSelected());
                    }
                }
            });

        boardNormDetailsForm.add(tableFlexTable);

        Label heading = new Label("");
        heading.setStyleName("heading");

        VertiPanel.clear();

        VertiPanel.add(heading);
        VertiPanel.setSpacing(10);
        VertiPanel.add(boardNormDetailsForm);
        VertiPanel.setSpacing(10);

        if (action.equalsIgnoreCase("add")) {
            heading.setText(labels.label_boardNormFactor());
            VertiPanel.add(addButton);
        } else {
            VertiPanel.add(okButton);
            VertiPanel.add(gridVPanel);
            heading.setText(labels.label_manageboardNormFactor());
        }

        ProgTermflexTable.clear();
        ProgTermflexTable.setWidget(0, 0, VertiPanel);

        ProgTermHorizontalPanel.clear();
        ProgTermHorizontalPanel.add(ProgTermflexTable);
        ProgTermHorizontalPanel1.clear();
        ProgTermHorizontalPanel1.add(ProgTermHorizontalPanel);
    }

    public void methodEntityComboSettings(ComboBox entityComboBox) {
        entityComboBox.setForceSelection(true);
        entityComboBox.setMinChars(1);
        entityComboBox.setDisplayField("entity_name");
        entityComboBox.setValueField("entity_id");
        entityComboBox.setMode(ComboBox.LOCAL);
        entityComboBox.setTriggerAction(ComboBox.ALL);
        entityComboBox.setEmptyText(consEntityName);
        entityComboBox.setLoadingText(loading);
        entityComboBox.setTypeAhead(true);
        entityComboBox.setSelectOnFocus(true);
        entityComboBox.setWidth(190);
        entityComboBox.setHideTrigger(false);
    }
    public void methodComponentComboSettings(ComboBox componentBox) {
        componentBox.setForceSelection(true);
        componentBox.setMinChars(1);
        componentBox.setDisplayField("description");
        componentBox.setValueField("component_id");
        componentBox.setMode(ComboBox.LOCAL);
        componentBox.setTriggerAction(ComboBox.ALL);
        componentBox.setEmptyText(labels.select() + " " + labels.component());
        componentBox.setLoadingText(searching);
        componentBox.setTypeAhead(true);
        componentBox.setSelectOnFocus(true);
        componentBox.setWidth(190);
        componentBox.setHideTrigger(false);
    }

    public void methodBoardComboSettings(ComboBox boardComboBox) {
        boardComboBox.setForceSelection(true);
        boardComboBox.setMinChars(1);
        boardComboBox.setDisplayField("board_name");
        boardComboBox.setValueField("board_id");
        boardComboBox.setMode(ComboBox.LOCAL);
        boardComboBox.setTriggerAction(ComboBox.ALL);
        boardComboBox.setEmptyText(labels.selectBoard());
        boardComboBox.setLoadingText(searching);
        boardComboBox.setTypeAhead(true);
        boardComboBox.setSelectOnFocus(true);
        boardComboBox.setWidth(190);
        boardComboBox.setHideTrigger(false);
    }

    /*
     * Method to populate entity list
     */
    public void methodEntityPopulate(final ComboBox entityComboBox) {
        connectBoardService.methodGetProgOfferingEntityList(new AsyncCallback<CM_entityInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(failureLabel, arg0.getMessage());
                }

                public void onSuccess(CM_entityInfoGetter[] arg0) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("entity_name"),
                                new StringFieldDef("entity_id")
                            });
                    String[][] object2 = new String[arg0.length][2];
                    String[][] data = object2;
                    for (int i = 0; i < arg0.length; i++) {
                        for (int k = 0; k < 2; k++) {
                            if (k == 0) {
                                object2[i][0] = arg0[i].getEntity_name();
                            } else {
                                object2[i][1] = arg0[i].getEntity_id();
                            }
                        }
                    }
                    MemoryProxy proxy = new MemoryProxy(data);
                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();
                    entityComboBox.setStore(store);
                }
            });
    }

    /*
     * method for populating program list
     */
    public void methodProgramPopulate(String entityId,final ComboBox programComboBox) {
        connectBoardService.methodprogList(userid,entityId,
            new AsyncCallback<CM_progMasterInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(failureLabel, arg0.getMessage());
                }
                public void onSuccess(CM_progMasterInfoGetter[] result) {
                    if (result.length > 0) {
                        try {
                            RecordDef recordDef = new RecordDef(new FieldDef[] {
                                        new StringFieldDef("program_name"),
                                        new StringFieldDef("program_id")
                                    });

                            String[][] object2 = new String[result.length][2];
                            String[][] data = object2;
                            for (int i = 0; i < result.length; i++) {
                                object2[i][0] = result[i].getProgram_name();
                                object2[i][1] = result[i].getProgram_id();
                            }
                            MemoryProxy proxy = new MemoryProxy(data);
                            ArrayReader reader = new ArrayReader(recordDef);
                            progNameStore = new Store(proxy, reader);
                            progNameStore.load();
                            programComboBox.setStore(progNameStore);
                        } catch (Exception e) {
                            System.out.println("Exception in prog list " + e);
                        }
                    } else {
                        MessageBox.alert(msg.failure(),
                            msg.errorNoProgForNormalize());
                    }
                }
            });
    }

    /*
     * method for populating program list for manage
     */
    public void methodProgramPopulateManage(String entityId,final ComboBox programComboBox) {
        connectBoardService.methodprogListForManage(userid,entityId,
            new AsyncCallback<CM_progMasterInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(failureLabel, arg0.getMessage());
                }

                public void onSuccess(CM_progMasterInfoGetter[] result) {
                    try {
                        RecordDef recordDef = new RecordDef(new FieldDef[] {
                                    new StringFieldDef("program_name"),
                                    new StringFieldDef("program_id")
                                });

                        String[][] object2 = new String[result.length][2];
                        String[][] data = object2;
                        for (int i = 0; i < result.length; i++) {
                            object2[i][0] = result[i].getProgram_name();
                            object2[i][1] = result[i].getProgram_id();
                        }

                        MemoryProxy proxy = new MemoryProxy(data);
                        ArrayReader reader = new ArrayReader(recordDef);
                        progNameStore = new Store(proxy, reader);
                        progNameStore.load();
                        programComboBox.setStore(progNameStore);
                    } catch (Exception e) {
                        System.out.println("Exception in prog list " + e);
                    }
                }
            });
    }

    /*
     * method populating component list
     */
    public void methodCompList(String program_id, String entity_id, final ComboBox compComboBox) {
        connectBoardService.methodComponentList(program_id, entity_id,
            new AsyncCallback<CM_boardNormalizationGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(failureLabel, arg0.getMessage());
                }

                public void onSuccess(CM_boardNormalizationGetter[] arg0) {
                    final RecordDef rDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("description"),
                                new StringFieldDef("component_id")
                            });

                    Object[][] object1 = new Object[arg0.length][2];
                    String str = null;
                    try {
                        for (int i = 0; i < arg0.length; i++) {
                            for (int k = 0; k < 2; k++) {
                                if (k == 0) {
                                    str = arg0[i].getDescription();
                                } else if (k == 1) {
                                    str = arg0[i].getComponent_id();
                                }

                                object1[i][k] = str;
                            }
                        }
                    } catch (Exception e2) {
                        System.out.println("e2     " + e2);
                    }
                    Object[][] data = object1;
                    MemoryProxy proxy = null;
                    proxy = new MemoryProxy(data);
                    ArrayReader reader = new ArrayReader(rDef);
                    compStore = new Store(proxy, reader);
                    compStore.load();
                    compComboBox.setStore(compStore);
                }
            });
    }

    /*
     * method populating component list
     */
    public void methodboardList(final ComboBox boardComboBox) {
        connectBoardService.methodBoardList(new AsyncCallback<CM_boardNormalizationGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(failureLabel, arg0.getMessage());
                }

                public void onSuccess(CM_boardNormalizationGetter[] arg0) {
                    final RecordDef rDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("board_name"),
                                new StringFieldDef("board_id")
                            });

                    Object[][] object1 = new Object[arg0.length][2];
                    String str = null;
                    try {
                        for (int i = 0; i < arg0.length; i++) {
                            for (int k = 0; k < 2; k++) {
                                if (k == 0) {
                                    str = arg0[i].getBoard_name();
                                } else if (k == 1) {
                                    str = arg0[i].getBoard_id();
                                }

                                object1[i][k] = str;
                            }
                        }
                    } catch (Exception e2) {
                        System.out.println("e2     " + e2);
                    }
                    Object[][] data = object1;
                    MemoryProxy proxy = null;
                    proxy = new MemoryProxy(data);
                    ArrayReader reader = new ArrayReader(rDef);
                    boardStore = new Store(proxy, reader);
                    boardStore.load();
                    boardComboBox.setStore(boardStore);
                }
            });
    }
}
