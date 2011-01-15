package in.ac.dei.edrp.client.ProgramSetup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.gwtext.client.core.EventObject;
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
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
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
import com.gwtext.client.widgets.grid.event.GridCellListener;
import com.gwtext.client.widgets.grid.event.RowSelectionListener;
import com.gwtext.client.widgets.layout.BorderLayoutData;

import in.ac.dei.edrp.client.CM_BranchSpecializationInfoGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CM_connectD;
import in.ac.dei.edrp.client.RPCFiles.CM_connectDAsync;
import in.ac.dei.edrp.client.Shared.Validator;

/**
 * @author Dayal Sharan Sukhdhami
 */
import java.util.List;


public class CM_ProgramPaperCode {
    private final CM_connectDAsync connectDService = GWT.create(CM_connectD.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    public VerticalPanel vPanel = new VerticalPanel();
    public String userid;
    Label label3 = new Label("Dayalbagh Educational Institute");
    Label label4 = new Label("2010-11");
    Label componentLabel = new Label("Component Name *");
    Label marksLabel = new Label("Paper Code");
    Button saveButton = new Button("Save");
    Button manageButton = new Button("Manage");
    String componentid;
    String uniid = "0001";
    Object[][] object1;
    final VerticalPanel fullpage1 = new VerticalPanel();
    Validator validator = new Validator();
    String description;
    String paperCode = null;

    //    boolean flag = false;
    String pagename = "Program Paper Codes";
    String[] values;
    ToolbarButton editButton = new ToolbarButton("Edit");
    ToolbarButton deletebutton = new ToolbarButton("Delete");
    String program = "";
    String groupText = "";

    //		  String paperCodeNumber = "";

    //Constructor for setting the Value of User ID 
    public CM_ProgramPaperCode(String Uid) {
        this.userid = Uid;
    }

    public void ProgramPaperCode() {
        final ComboBox programComboBox = new ComboBox();
        final ComboBox programOfferedComboBox = new ComboBox();
        final ComboBox groupComboBox = new ComboBox();
        final ComboBox paperComboBox = new ComboBox();

        final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();

        final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
        bd.setMargins(6, 6, 6, 6);

        programComboBox.setMinChars(1);
        programComboBox.setFieldLabel("Entity");
        programComboBox.setDisplayField("state");
        programComboBox.setMode(ComboBox.LOCAL);
        programComboBox.setTriggerAction(ComboBox.ALL);
        programComboBox.setEmptyText("Select Program");
        programComboBox.setLoadingText("Searching...");
        programComboBox.setTypeAhead(true);
        programComboBox.setSelectOnFocus(true);
        programComboBox.setWidth(190);
        programComboBox.setHideTrigger(false);
        programComboBox.setAllowBlank(false);
        programComboBox.setForceSelection(true);

        programOfferedComboBox.setMinChars(1);
        programOfferedComboBox.setFieldLabel("Entity");
        //       programOfferedComboBox.setValueField("ID");
        programOfferedComboBox.setDisplayField("state");
        programOfferedComboBox.setMode(ComboBox.LOCAL);
        programOfferedComboBox.setTriggerAction(ComboBox.ALL);
        programOfferedComboBox.setEmptyText("Select Entity");
        programOfferedComboBox.setLoadingText("Searching...");
        programOfferedComboBox.setTypeAhead(true);
        programOfferedComboBox.setSelectOnFocus(true);
        programOfferedComboBox.setWidth(190);
        programOfferedComboBox.setHideTrigger(false);
        programOfferedComboBox.setAllowBlank(false);
        programOfferedComboBox.setForceSelection(true);

        groupComboBox.setMinChars(1);
        groupComboBox.setFieldLabel("Entity");
        //       groupComboBox.setValueField("ID");
        groupComboBox.setDisplayField("state");
        groupComboBox.setMode(ComboBox.LOCAL);
        groupComboBox.setTriggerAction(ComboBox.ALL);
        groupComboBox.setEmptyText("Select Group");
        groupComboBox.setLoadingText("Searching...");
        groupComboBox.setTypeAhead(true);
        groupComboBox.setSelectOnFocus(true);
        groupComboBox.setWidth(190);
        groupComboBox.setHideTrigger(false);
        groupComboBox.setAllowBlank(false);
        groupComboBox.setForceSelection(true);

        paperComboBox.setMinChars(1);
        paperComboBox.setFieldLabel("Entity");
        paperComboBox.setValueField("ID");
        paperComboBox.setDisplayField("state");
        paperComboBox.setMode(ComboBox.LOCAL);
        paperComboBox.setTriggerAction(ComboBox.ALL);
        paperComboBox.setEmptyText("Select Paper");
        paperComboBox.setLoadingText("Searching...");
        paperComboBox.setTypeAhead(true);
        paperComboBox.setSelectOnFocus(true);
        paperComboBox.setWidth(190);
        paperComboBox.setHideTrigger(false);
        paperComboBox.setAllowBlank(false);
        paperComboBox.setBlankText("Field Required");
        paperComboBox.setMinLengthText("Required");
        paperComboBox.setForceSelection(true);

        vPanel.clear();
        fullpage1.clear();

        try {
            programOfferedComboBox.reset();
            programComboBox.reset();
            groupComboBox.reset();
            paperComboBox.reset();
        } catch (Exception e) {
        }

        FormPanel fPanel = new FormPanel();
        final TextField componentField = new TextField();
        final NumberField marksField = new NumberField();
        FlexTable componentTable = new FlexTable();

        final Label uniLabel = new Label("Program ");
        final Label descriptionLabel = new Label("Group");
        final Label criteriaLabel = new Label("Program Offering Entity");

        final NumberField toField = new NumberField();

        marksField.setAllowDecimals(false);
        marksField.setAllowNegative(false);
        marksField.setAllowBlank(false);
        marksField.setMaxLength(2);
        marksField.setMinLength(2);
        marksField.setValue(0);

        componentField.setEmptyText("Enter Component Name");

        toField.setAllowDecimals(false);
        toField.setAllowNegative(false);
        toField.setAllowBlank(false);
        toField.setMaxLength(3);

        fPanel.setFrame(true);
        fPanel.setSize("400px", "100%");
        fPanel.setTitle("Assign Program Paper Code with Entities & Programs");

        programOfferedComboBox.disable();
        programComboBox.disable();
        groupComboBox.disable();
        paperComboBox.disable();
        editButton.disable();
        deletebutton.disable();
        cbSelectionModel.lock();
        saveButton.disable();
        manageButton.disable();

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
                                        programOfferedComboBox.enable();
                                        programComboBox.enable();
                                        groupComboBox.enable();
                                        paperComboBox.enable();
                                        saveButton.enable();
                                        cbSelectionModel.unlock();
                                        editButton.setDisabled(false);
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

                                        for (int i = 0; i < result.size();
                                                i++) {
                                            values[i] = result.get(i);

                                            if (values[i].equalsIgnoreCase(
                                                        "create")) {
                                                programOfferedComboBox.enable();
                                                programComboBox.enable();
                                                groupComboBox.enable();
                                                paperComboBox.enable();
                                                manageButton.enable();
                                                saveButton.enable();
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "view")) {
                                                manageButton.fireEvent("Click");
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "update")) {
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "delete")) {
                                                manageButton.fireEvent("Click");
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

        connectDService.methodProgramOfferedByPopulate(new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert("Note", "Database Error: " + arg0);
                }

                public void onSuccess(
                    CM_BranchSpecializationInfoGetter[] result) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("state")
                            //		                         , new StringFieldDef("ID")
                        });

                    object1 = new Object[result.length][2];

                    Object[][] data = object1;

                    for (int i = 0; i < result.length; i++) {
                        object1[i][0] = result[i].getEntity_name();

                        //	             		object1[i][1]=result[i].getEntity_id(); 
                    }

                    MemoryProxy proxy = new MemoryProxy(data);
                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    programOfferedComboBox.setStore(store);
                }
            });

        connectDService.methodGroupsPopulate(new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert("Note", "Database Error: " + arg0);
                }

                public void onSuccess(
                    CM_BranchSpecializationInfoGetter[] result) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("state")
                            });

                    object1 = new Object[result.length][2];

                    Object[][] data = object1;

                    for (int i = 0; i < result.length; i++) {
                        object1[i][0] = result[i].getGrouping();
                    }

                    MemoryProxy proxy = new MemoryProxy(data);
                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    groupComboBox.setStore(store);
                }
            });

        connectDService.methodPapersPopulate(new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert("Note", "Database Error: " + arg0);
                }

                public void onSuccess(
                    CM_BranchSpecializationInfoGetter[] result) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("state"),
                                new StringFieldDef("ID")
                            });

                    object1 = new Object[result.length][2];

                    Object[][] data = object1;

                    for (int i = 0; i < result.length; i++) {
                        object1[i][0] = result[i].getPaper_description();
                        object1[i][1] = result[i].getPaper_code();
                    }

                    MemoryProxy proxy = new MemoryProxy(data);
                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    paperComboBox.setStore(store);
                }
            });

        programOfferedComboBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    final String programOfferedBy = programOfferedComboBox.getValue();

                    programComboBox.clearValue();

                    createCountriesOracle(programOfferedBy, programComboBox);

                    programComboBox.addListener(new ComboBoxListenerAdapter() {
                            public void onSelect(ComboBox comboBox,
                                Record record, int index) {
                                program = programComboBox.getValue();
                            }
                        });
                }
            });

        saveButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    Boolean flag = true;

                    try {
                        programOfferedComboBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        programComboBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        groupComboBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        paperComboBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    if (flag == true) {
                        try {
                            paperCode = paperComboBox.getText();
                            groupText = groupComboBox.getText();

                            MessageBox.show(new MessageBoxConfig() {

                                    {
                                        setTitle("Confirm");
                                        setMsg(
                                            "Are you sure you want to save the details ?");
                                        setIconCls(MessageBox.QUESTION);
                                        setButtons(MessageBox.YESNO);
                                        setCallback(new MessageBox.PromptCallback() {
                                                public void execute(
                                                    String btnID, String text) {
                                                    if (btnID.equals("yes")) {
                                                        connectDService.methodInsertProgramPaperCode(uniid,
                                                            program,
                                                            programOfferedComboBox.getValue(),
                                                            groupText,
                                                            paperCode,
                                                            new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
                                                                public void onFailure(
                                                                    Throwable arg0) {
                                                                    String[] exception =
                                                                        arg0.getMessage()
                                                                            .split(":");
                                                                    MessageBox.alert("Failure",
                                                                        exception[1]);
                                                                }

                                                                public void onSuccess(
                                                                    CM_BranchSpecializationInfoGetter[] arg0) {
                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(
                                                                                    "Confirm");
                                                                                setMsg(
                                                                                    "component added successfully");
                                                                                setIconCls(MessageBox.INFO);
                                                                                setButtons(MessageBox.OK);
                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                        public void execute(
                                                                                            String btnID,
                                                                                            String text) {
                                                                                            if (btnID.equals(
                                                                                                        "ok")) {
                                                                                                componentField.setValue(
                                                                                                    "");
                                                                                                marksField.setValue(0);
                                                                                                manageButton.fireEvent(
                                                                                                    "click");
                                                                                                paperComboBox.clearValue();
                                                                                                programComboBox.clearValue();
                                                                                                programOfferedComboBox.clearValue();
                                                                                                groupComboBox.clearValue();

                                                                                                try {
                                                                                                    paperComboBox.clearInvalid();
                                                                                                } catch (Exception e) {
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    });
                                                                            }
                                                                        });
                                                                }
                                                            });
                                                    } else if (btnID.equals(
                                                                "no")) {
                                                        componentField.setValue(
                                                            "");
                                                        marksField.setValue(0);
                                                    }
                                                }
                                            });
                                    }
                                });
                        } catch (Exception e1) {
                            System.out.println("Exception: " + e1);
                        }
                    } else {
                        MessageBox.alert("Error",
                            "Please check the entries in Red.");
                    }
                }
            });

        componentTable.setWidget(1, 0, criteriaLabel);
        componentTable.setWidget(1, 1, programOfferedComboBox);
        componentTable.setWidget(2, 0, uniLabel);
        componentTable.setWidget(2, 1, programComboBox);
        componentTable.setWidget(3, 0, descriptionLabel);
        componentTable.setWidget(3, 1, groupComboBox);
        componentTable.setWidget(4, 0, marksLabel);
        componentTable.setWidget(4, 1, paperComboBox);
        componentTable.setWidget(10, 0, saveButton);
        componentTable.setWidget(10, 1, manageButton);

        manageButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    connectDService.methodProgramPaperCodePopulate(new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
                            public void onFailure(Throwable arg0) {
                            }

                            public void onSuccess(
                                CM_BranchSpecializationInfoGetter[] result) {
                                final GridPanel grid = new GridPanel();

                                final RecordDef rDef = new RecordDef(new FieldDef[] {
                                            new StringFieldDef("entityName"),
                                            new StringFieldDef("programName"),
                                            new StringFieldDef("grouping"),
                                            new StringFieldDef("paperDescription"),
                                            
                                        new StringFieldDef("entityID"),
                                            new StringFieldDef("programID"),
                                            new StringFieldDef("paperCode")
                                        });

                                object1 = new Object[result.length][7];

                                String str = "";

                                for (int i = 0; i < result.length; i++) {
                                    for (int k = 0; k < 7; k++) {
                                        if (k == 0) {
                                            str = result[i].getEntity_name();
                                        } else if (k == 1) {
                                            str = result[i].getProgram_name();
                                        } else if (k == 2) {
                                            str = result[i].getGrouping();
                                        } else if (k == 3) {
                                            str = result[i].getPaper_description();
                                        } else if (k == 4) {
                                            str = result[i].getEntity_id();
                                        } else if (k == 5) {
                                            str = result[i].getProgram_id();
                                        } else if (k == 6) {
                                            str = result[i].getPaper_code();
                                        }

                                        try {
                                            object1[i][k] = str;
                                        } catch (Exception e) {
                                        }
                                    }
                                }

                                ;

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
                                        
                                        new ColumnConfig("Entity",
                                            "entityName", 250, true, null,
                                            "company"),
                                        new ColumnConfig("Program",
                                            "programName", 100, true, null,
                                            "company"),
                                        new ColumnConfig("Group", "grouping",
                                            50, true, null, "company"),
                                        new ColumnConfig("Paper Description",
                                            "paperDescription", 100, true,
                                            null, "company"),
                                        
                                        new ColumnConfig("agdam-bagdam",
                                            "entityID", 50, true, null,
                                            "company"),
                                        new ColumnConfig("agdam-bagdam",
                                            "programID", 50, true, null,
                                            "company"),
                                        new ColumnConfig("agdam-bagdam",
                                            "paperCode", 50, true, null,
                                            "company")
                                    };

                                ColumnModel columnModel = new ColumnModel(columns);
                                columnModel.setHidden(5, true);
                                columnModel.setHidden(6, true);
                                columnModel.setHidden(7, true);
                                grid.setColumnModel(columnModel);

                                grid.setFrame(true);
                                grid.setStripeRows(true);
                                grid.setSelectionModel(cbSelectionModel);
                                grid.setAutoWidth(true);
                                grid.setWidth(550);
                                grid.setHeight(280);

                                Toolbar topToolBar = new Toolbar();
                                topToolBar.addFill();

                                final ToolbarButton deletebutton1 = new ToolbarButton(
                                        "Delete");

                                deletebutton1.setDisabled(true);

                                deletebutton = deletebutton1;

                                deletebutton1.addListener(new ButtonListenerAdapter() {
                                        public void onClick(Button delButton,
                                            EventObject e) {
                                            Record[] records = cbSelectionModel.getSelections();
                                            Record record;

                                            if (records.length < 1) {
                                                MessageBox.alert("Error",
                                                    "Please select a record for deletion");
                                            } else if (records.length > 1) {
                                                MessageBox.alert("Error",
                                                    "Please select only one record for deletion");
                                            } else {
                                                for (int i = 0;
                                                        i < records.length;
                                                        i++) {
                                                    record = records[i];

                                                    String[] Univ = new String[6];

                                                    Univ[0] = record.getAsString(
                                                            "entityName");
                                                    Univ[1] = record.getAsString(
                                                            "programName");
                                                    Univ[2] = record.getAsString(
                                                            "grouping");
                                                    Univ[3] = record.getAsString(
                                                            "paperCode");
                                                    Univ[4] = record.getAsString(
                                                            "entityID");
                                                    Univ[5] = record.getAsString(
                                                            "programID");

                                                    final CM_BranchSpecializationInfoGetter deleteProgramPaperCode =
                                                        new CM_BranchSpecializationInfoGetter();
                                                    deleteProgramPaperCode.setEntity_id(Univ[4]);
                                                    deleteProgramPaperCode.setProgram_id(Univ[5]);
                                                    deleteProgramPaperCode.setGrouping(Univ[2]);
                                                    deleteProgramPaperCode.setPaper_code(Univ[3]);

                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setTitle(
                                                                    "Confirm");
                                                                setMsg(
                                                                    "Are you sure you want to delete these records ?");
                                                                setIconCls(MessageBox.QUESTION);
                                                                setButtons(MessageBox.YESNO);
                                                                setCallback(new MessageBox.PromptCallback() {
                                                                        public void execute(
                                                                            String btnID,
                                                                            String text) {
                                                                            if (btnID.equals(
                                                                                        "yes")) {
                                                                                connectDService.methodDeleteFromProgramPaperCode(deleteProgramPaperCode,
                                                                                    new AsyncCallback<String>() {
                                                                                        public void onFailure(
                                                                                            Throwable arg0) {
                                                                                            MessageBox.alert("Failure",
                                                                                                arg0.getMessage());
                                                                                        }

                                                                                        public void onSuccess(
                                                                                            String arg0) {
                                                                                            MessageBox.alert("Congratulation",
                                                                                                "Record Successfully deleted",
                                                                                                new AlertCallback() {
                                                                                                    public void execute() {
                                                                                                        cbSelectionModel.clearSelections();
                                                                                                        manageButton.fireEvent(
                                                                                                            "click");
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                    });
                                                                            } else if (btnID.equals(
                                                                                        "no")) {
                                                                                ProgramPaperCode();
                                                                            }
                                                                        }
                                                                    });
                                                            }
                                                        });
                                                }
                                            }
                                        }
                                    });

                                topToolBar.addButton(deletebutton);

                                grid.setTopToolbar(topToolBar);

                                grid.addGridCellListener(new GridCellListener() {
                                        public void onCellClick(
                                            GridPanel grid, int rowIndex,
                                            int colIndex, EventObject e) {
                                            //                                            Record[] records = cbSelectionModel.getSelections();

                                            //                                            Record record = records[0];
                                        }

                                        public void onCellContextMenu(
                                            GridPanel grid, int rowIndex,
                                            int cellIndex, EventObject e) {
                                        }

                                        public void onCellDblClick(
                                            GridPanel grid, int rowIndex,
                                            int colIndex, EventObject e) {
                                        }
                                    });

                                grid.setTitle(
                                    "Manage Program Paper Code Details");

                                fullpage1.clear();
                                fullpage1.add(grid);
                            }
                        });
                }
            });

        fPanel.add(componentTable);

        label3.setStyleName("panelHeading");
        label4.setStyleName("panelHeading");

        vPanel.add(label3);
        vPanel.add(label4);
        vPanel.add(fPanel);
        vPanel.add(fullpage1);
    }

    public void show() {
    }

    /**
     * Method to populate list of Programs of selected Program Offering Entity
     * @return
     */
    public String createCountriesOracle(String selectedProgramOfferedColumn,
        final ComboBox programComboBox) {
        connectDService.methodManageProgramList(selectedProgramOfferedColumn,
            new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert("DataBase Error : " + arg0.getMessage());
                }

                public void onSuccess(
                    final CM_BranchSpecializationInfoGetter[] result) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("state"),
                            });

                    object1 = new Object[result.length][2];

                    Object[][] data = object1;

                    for (int i = 0; i < result.length; i++) {
                        object1[i][0] = result[i].getProgram_name();
                    }

                    MemoryProxy proxy = new MemoryProxy(data);
                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    programComboBox.setStore(store);
                }
            });

        return program;
    }
}
