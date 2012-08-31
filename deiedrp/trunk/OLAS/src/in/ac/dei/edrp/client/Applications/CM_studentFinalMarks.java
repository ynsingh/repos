package in.ac.dei.edrp.client.Applications;

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
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListener;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.CM_progOfferedBy;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.GridDataBean;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.RPCFiles.CM_manageMarks;
import in.ac.dei.edrp.client.RPCFiles.CM_manageMarksAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.Iterator;
import java.util.List;


@SuppressWarnings("deprecation")
public class CM_studentFinalMarks {
    Validator valid;
    public String userid;
    String[][] object2;
    CM_progOfferedBy pob = new CM_progOfferedBy(userid);
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    private final CM_manageMarksAsync marksService = GWT.create(CM_manageMarks.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);

    // First Outer Vertical Panel contains inner vertical panel with (all
    // comboBox and buttons)
    final VerticalPanel vInPanel = new VerticalPanel();

    // Inner Vertical panel which contains all comboBox and button
    public final VerticalPanel vPanel = new VerticalPanel();

    // Panel for containing grid
    HorizontalPanel gridPanel = new HorizontalPanel();
    String pagename = "Enter Admission Test Marks";
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
    String consBranchName = labels.label_branchname();
    String consAddMarks = labels.labeladdMarks();
    String consEditMarks = labels.labeleditMarks();
    String msgAtleastOneRec = msg.atleastOneRecord();
    String msgOnlyOneRec = msg.onlyOneRecord();
    String button_update = labels.button_update();
    String loading = labels.loading();
    String searching = labels.searching();
    ToolbarButton editButton = new ToolbarButton(consEditMarks);

    /**
     * Constructor for setting the Value of User ID
     *
     * @param Uid
     */
    public CM_studentFinalMarks(String Uid) {
        this.userid = Uid;
    }

    /**
     * Method to add students' final test marks
     */
    public void methodAddMarks(final String function) {
        vPanel.clear();
        vInPanel.clear();
        gridPanel.clear();

        final ComboBox entityTypeCBox = new ComboBox();
        final ComboBox entityNameCBox = new ComboBox();
        final ComboBox programNameCBox = new ComboBox();
        final ComboBox criteriaTypeCBox = new ComboBox();
        final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
        final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
        bd.setMargins(6, 6, 6, 6);

        final SuggestBox textValue = new SuggestBox(oracle);

        // Add Button for adding student marks to studentfinalmarks table and
        // inserting these values to studentfinalmeritlist
        final Button addMarksButton = new Button(consAddMarks);

        // Edit Button, to edit records (marks for student) one by one. Table
        // changes: Studenfinalmarks and Studentfinalmeritlist
        final Button editMarksButton = new Button(consEditMarks);

        ComboBoxes(entityNameCBox);
        ComboBoxes(entityTypeCBox);
        ComboBoxes(programNameCBox);
        entityTypeCBox.setAllowBlank(false);
        programNameCBox.setAllowBlank(false);
        entityTypeCBox.disable();
        entityNameCBox.disable();
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
                                        entityTypeCBox.enable();
                                        entityNameCBox.enable();
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
                                                entityTypeCBox.enable();
                                                entityNameCBox.enable();
                                                programNameCBox.enable();
                                                editMarksButton.enable();
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "update")) {
                                                entityTypeCBox.enable();
                                                entityNameCBox.enable();
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

        connectTemp.Entity_Description(userid,
            new AsyncCallback<CM_entityInfoGetter[]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert("Sql Exception", caught.getMessage());
                }

                public void onSuccess(CM_entityInfoGetter[] arg0) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("Description"),
                                new StringFieldDef("code")
                            });

                    String[][] object1;

                    object1 = new String[arg0.length][2];

                    String str = null;

                    try {
                        for (int i = 0; i < arg0.length; i++) {
                            for (int k = 0; k < 2; k++) {
                                if (k == 0) {
                                    str = arg0[i].getEntity_description();
                                } else if (k == 1) {
                                    str = arg0[i].getEntity_type();
                                }

                                object1[i][k] = str;
                            }
                        }
                    } catch (Exception e2) {
                        System.out.println("e2     " + e2);
                    }

                    Object[][] data = object1;

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    entityTypeCBox.setStore(store);
                }
            });

        final FormPanel mainPanel = new FormPanel();
        Label heading;

        mainPanel.setFrame(true);

        if (function.equalsIgnoreCase("edit")) {
            mainPanel.setTitle(labels.labeleditTestMarks());
            heading = new Label(consEditMarks);
            heading.setStyleName("heading1");
            mainPanel.setSize("350px", "250px");
        } else {
            mainPanel.setTitle(labels.labeladdTestMarks());
            heading = new Label(consAddMarks);
            heading.setStyleName("heading1");
            mainPanel.setSize("350px", "150px");
        }

        // Label instiName = new Label("Dayalbagh Educational Institute");
        // Label session = new Label("Session: 2010-11");
        FlexTable upperFlexTable = new FlexTable();

        Label entityTypeLabel = new Label(consEntityType);
        Label entityNameLabel = new Label(consEntityName);
        Label programNameLabel = new Label(consProgName);

        entityTypeCBox.setEmptyText(consEntityType);
        entityNameCBox.setEmptyText(consEntityName);
        programNameCBox.setEmptyText(consProgName);

        upperFlexTable.setWidget(0, 0, entityTypeLabel);
        upperFlexTable.setWidget(0, 1, entityTypeCBox);
        upperFlexTable.setWidget(1, 0, entityNameLabel);
        upperFlexTable.setWidget(1, 1, entityNameCBox);
        upperFlexTable.setWidget(2, 0, programNameLabel);
        upperFlexTable.setWidget(2, 1, programNameCBox);
        if (function.equalsIgnoreCase("edit")) {
            methodCriteriaProp(criteriaTypeCBox);

            Label criteriaTypeLabel = new Label(labels.select() + " " +
                    labels.criteria());
            Label criteriaNameLabel = new Label(labels.search());

            RecordDef recordDef = new RecordDef(new FieldDef[] {
                        new StringFieldDef("crieteria_value"),
                    });

            object2 = new String[2][1];

            object2[0][0] = "Registration Number";
            object2[1][0] = "Test Number";

            String[][] data = object2;
            MemoryProxy proxy = new MemoryProxy(data);

            ArrayReader reader = new ArrayReader(recordDef);
            Store store = new Store(proxy, reader);
            store.load();
            criteriaTypeCBox.setStore(store);

            upperFlexTable.setWidget(10, 0, criteriaTypeLabel);
            upperFlexTable.setWidget(10, 1, criteriaTypeCBox);
            upperFlexTable.setWidget(11, 0, criteriaNameLabel);
            upperFlexTable.setWidget(11, 1, textValue);
        }
        mainPanel.add(upperFlexTable);

        if (function.equalsIgnoreCase("add")) {
            mainPanel.addButton(addMarksButton);
        } else {
            mainPanel.addButton(editMarksButton);
        }

        if (function.equalsIgnoreCase("add")) {
            entityTypeCBox.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox comboBox, Record record,
                        int index) {
                        String entityType = comboBox.getValue();
                        connectTemp.Entity_Name(userid, entityType,
                            new AsyncCallback<CM_entityInfoGetter[]>() {
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert(labels.dbError(),
                                        caught.getMessage());
                                }

                                public void onSuccess(
                                    CM_entityInfoGetter[] arg0) {
                                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("Description"),
                                                new StringFieldDef("code")
                                            });
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

                                    entityNameCBox.clearValue();
                                    programNameCBox.clearValue();
                                    entityNameCBox.setStore(store);
                                    vPanel.remove(gridPanel);
                                }
                            });
                    }
                });
        } else {
            entityTypeCBox.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox comboBox, Record record,
                        int index) {
                        String entityType = comboBox.getValue();

                        connectTemp.Entity_Name(userid, entityType,
                            new AsyncCallback<CM_entityInfoGetter[]>() {
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert(labels.dbError(),
                                        caught.getMessage());
                                }

                                public void onSuccess(
                                    CM_entityInfoGetter[] arg0) {
                                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("Description"),
                                                new StringFieldDef("code")
                                            });
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

                                    entityNameCBox.clearValue();
                                    programNameCBox.clearValue();
                                    entityNameCBox.setStore(store);
                                    vPanel.remove(gridPanel);
                                }
                            });
                    }
                });
        }

        entityNameCBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    final String programOfferedBy = comboBox.getValue();
                    programNameCBox.clearValue();

                    if (function.equalsIgnoreCase("add")) {
                        marksService.methodProgListAdd(programOfferedBy,
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
                    } else {
                        marksService.methodProgListEdit(programOfferedBy,
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
                    }

                    criteriaTypeCBox.clearValue();
                    vPanel.remove(gridPanel);
                }
            });

        programNameCBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    CM_progMasterInfoGetter object = new CM_progMasterInfoGetter();

                    if (function.equalsIgnoreCase("add")) {
                        object.setProgram_id(comboBox.getValue());
                    }
                    criteriaTypeCBox.clearValue();
                    vPanel.remove(gridPanel);
                }
            });
        criteriaTypeCBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    boolean flag = true;

                    try {
                        entityTypeCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        entityNameCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        programNameCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }                 

                    if (flag == true) {
                        textValue.setValue("");
                        methodLoadOracle(comboBox.getValue(),
                            programNameCBox.getValue(),
                            entityNameCBox.getValue());
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
                        entityTypeCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        entityNameCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

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
                        marksService.methodProgramList(userid,
                            entityTypeCBox.getValue(),
                            entityNameCBox.getValue(),
                            programNameCBox.getValue(),
                            new AsyncCallback<List<GridDataBean>>() {
                                public void onFailure(Throwable arg0) {
                                    String[] msg = arg0.getMessage().split(":");
                                    MessageBox.alert(errorMsg, msg[1]);
                                } // onFailure

                                public void onSuccess(List<GridDataBean> arg0) {
                                    if ((arg0.size() - 1) != 0) {
                                        // Hold data for reader object or list
                                        // data into double dimenssion array
                                        final String[][] object2;

                                        // Iterate list which returns from
                                        // server
                                        // Use of this iterator: To get Column
                                        // Names
                                        Iterator<GridDataBean> itr1 = arg0.iterator();
                                        itr1.hasNext();

                                        GridDataBean gd1 = (GridDataBean) itr1.next();
                                        final String[] sdf = new String[4 +
                                            (gd1.getComp().length)];
                                        sdf[0] = new String(gd1.getRegnum());
                                        sdf[1] = new String(gd1.getTestNumber());
                                        sdf[2] = new String(gd1.getCall());

                                        String[] s1 = gd1.getComp();
                                        Integer[] max_marks1 = new Integer[gd1.getComp().length];
                                        max_marks1 = gd1.getTotal_marks();

                                        final Integer[] max_marks = max_marks1;

                                        int count = 2;
                                        

                                        for (int k = 0; k < s1.length; k++) {
                                            sdf[k + 3] = new String(s1[k]);

                                            count++;
                                        } // For Loop ends

                                        sdf[count + 1] = new String(gd1.getTotal());

                                        // get Column name first,create your
                                        // bean with column name
                                        // FieldDefinition
                                        FieldDef[] def = new FieldDef[sdf.length];

                                        for (int defl = 0; defl < def.length;
                                                defl++) {
                                            def[defl] = new StringFieldDef(sdf[defl]);                                            
                                        }

                                        // RecordDefinition
                                        final RecordDef recordDef = new RecordDef(def); // RecordDef

                                        object2 = new String[arg0.size() - 1][4 +
                                            sdf.length];

                                        String[][] data = object2;

                                        Iterator<GridDataBean> itr = arg0.iterator();
                                        int i = 0;

                                        itr.next();

                                        while (itr.hasNext()) {
                                            GridDataBean gd = (GridDataBean) itr.next();
                                            data[i][0] = gd.getRegnum();

                                            data[i][1] = gd.getTestNumber();
                                            data[i][2] = gd.getCall();

                                            String[] s2 = gd.getComp();     
                                            
                                            count = 2;     
                                            final String[] ss = new String[(gd1.getComp().length)];
                                            //updated by devendra                                          
                                            for (int k = 0; k < max_marks.length;
                                                    k++) {
                                                data[i][k+3]=max_marks[k].toString();//s2[k];
                                                count++;
                                            } // For Loop ends

                                            data[i][count + 1] = gd.getTotal();
                                            i++;
                                        } // Iterator ends
                                          // Final data in data[] array

                                        MemoryProxy proxy = new MemoryProxy(data);
                                        ArrayReader reader = new ArrayReader(recordDef);
                                        final Store store = new Store(proxy,
                                                reader, true);
                                        store.load();

                                        // Here we have final data in store
                                        // creating column config
                                        ColumnConfig[] commonCol = new ColumnConfig[sdf.length];

                                        // commonCol[0]=new
                                        // CheckboxColumnConfig(cbSelectionModel);
                                        // setting editor for columns so that
                                        // user can edit
                                        for (int d = 0; d < commonCol.length;
                                                d++) {
                                            commonCol[d] = new ColumnConfig(sdf[d],
                                                    sdf[d], 80, true, null,
                                                    sdf[d]);
                                        }

                                        // creating BaseColumnConfig
                                        final BaseColumnConfig[] columns = commonCol;

                                        // creating ColumnModel
                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        columnModel.setDefaultSortable(true);

                                        // Creating object of editorgridpanel
                                        final GridPanel grid = new GridPanel(store,
                                                columnModel);
                                        // setting title, clicks to edit,frame,
                                        // size, StripeRows, TrackMouseOver and
                                        // visible
                                        grid.setTitle(consAddMarks);
                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setFrame(true);
                                        grid.setSize("1200px", "400px");
                                        grid.setAutoScroll(true);

                                        grid.setStripeRows(true);
                                        grid.setTrackMouseOver(true);
                                        grid.setVisible(true);

                                        // Adding toolbar with editorgridpanel
                                        // ToolbarButton addButton=new
                                        // ToolbarButton("Add");
                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        grid.setTopToolbar(topToolBar);

                                        for (int j = 0; j < sdf.length; j++) {
                                            grid.setAutoExpandColumn(sdf[j]);
                                        }

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        vPanel.add(gridPanel);

                                        topToolBar.addButton(new ToolbarButton(
                                                consAddMarks,
                                                new ButtonListenerAdapter() {
                                                public void onClick(Button Button,
                                                        EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();

                                                    if (records.length < 1) {
                                                        MessageBox.alert(
                                                                errorMsg,
                                                                msgAtleastOneRec);
                                                    } else if (records.length > 1) {
                                                        MessageBox.alert(
                                                                errorMsg,
                                                                msgOnlyOneRec);
                                                    } else if (records.length == 1) {
                                                        final Record record = records[0];

                                                        FlexTable editEntityProgramTable =
                                                                new FlexTable();
                                                        final NumberField[] numberField =
                                                                new NumberField[sdf.length -
                                                                4];
                                                        final ComboBox[] comboBox =
                                                                new ComboBox[sdf.length -
                                                                4];
                                                        final Label[] maxLabel = new Label[sdf.length -
                                                                4];

                                                        int numberCount = 0;

                                                        editEntityProgramTable.clear();

                                                        try {
                                                            for (int i = 0, j = 2;
                                                                        i < (sdf.length -
                                                                        1);
                                                                        i++, j++) {
                                                                editEntityProgramTable.setWidget(
                                                                        j, 0,
                                                                        new Label(
                                                                            sdf[i]));

                                                                if (j > 4) {
                                                                    numberField[numberCount] = new NumberField();
                                                                    comboBox[numberCount] = new ComboBox();
                                                                    maxLabel[numberCount] = new Label();
                                                                    // comboBox[numberCount].setId(""+numberCount);
                                                                    methodAttComboProp(
                                                                            comboBox[numberCount]);
                                                                    methodMarksFieldsProp(
                                                                            numberField[numberCount]);

                                                                    numberField[numberCount].setMaxValue(
                                                                            max_marks[j -
                                                                            5]);
                                                                    maxLabel[numberCount].setText(
                                                                            "(" +
                                                                            labels.label_maxmarks() +
                                                                            ":" +
                                                                            max_marks[j -
                                                                            5] +
                                                                            ")");

                                                                    editEntityProgramTable.setWidget(
                                                                            j,
                                                                            2,
                                                                            numberField[numberCount]);
                                                                    editEntityProgramTable.setWidget(
                                                                            j,
                                                                            1,
                                                                            maxLabel[numberCount]);

                                                                    editEntityProgramTable.setWidget(
                                                                            j,
                                                                            3,
                                                                            comboBox[numberCount]);
                                                               
                                                                    numberCount++;
                                                                } else {
                                                                    editEntityProgramTable.setWidget(
                                                                            j,
                                                                            1,
                                                                            new Label(
                                                                                record.getAsString(
                                                                                    sdf[i])));
                                                                }
                                                            }
                                                        } catch (Exception e1) {
                                                            System.out.println(
                                                                    "in editEntityProgramTable   " +
                                                                    e1);
                                                        }

                                                        final Panel p1 = new Panel();
                                                        p1.clear();
                                                        p1.add(editEntityProgramTable);

                                                        final Window window = new Window();
                                                        window.setTitle(
                                                                consAddMarks);
                                                        window.setWidth(650);
                                                        window.setHeight(300);
                                                        window.setMinWidth(350);
                                                        window.setMinHeight(150);
                                                        window.setLayout(
                                                                new FitLayout());
                                                        window.setPaddings(5);
                                                        window.setButtonAlign(
                                                                Position.CENTER);
                                                        window.setPlain(true);
                                                        window.setResizable(
                                                                false);
                                                        window.setAutoScroll(
                                                                true);
                                                        window.setCloseAction(
                                                                Window.CLOSE);
                                                        window.setFrame(true);
                                                        window.setClosable(true);
                                                        window.setModal(true);

                                                        Button updateButton = new Button(labels.saveButton());
                                                        window.addButton(
                                                                updateButton);

                                                        /*
                                                         * saving data
                                                         */
                                                        updateButton.addListener(
                                                                new ButtonListenerAdapter() {
                                                                public void onClick(Button updateButton,
                                                                        EventObject e) {
                                                                    Boolean flag =
                                                                            true;

                                                                    for (int i = 0;
                                                                                i < numberField.length;
                                                                                i++) {
                                                                        try {
                                                                            numberField[i].validate();
                                                                        } catch (Exception e1) {
                                                                            flag = false;
                                                                        }
                                                                    }

                                                                    for (int i = 0;
                                                                                i < comboBox.length;
                                                                                i++) {
                                                                        try {
                                                                            comboBox[i].validate();
                                                                        } catch (Exception e1) {
                                                                            flag = false;
                                                                        }
                                                                    }

                                                                    if (flag == true) {
                                                                        try {
                                                                            String entity_id =
                                                                                    entityNameCBox.getValue();
                                                                            String prog_id =
                                                                                    programNameCBox.getValue();
                                                                            String reg_no =
                                                                                    record.getAsString(
                                                                                        sdf[0]);
                                                                            String testnumber =
                                                                                    record.getAsString(
                                                                                        sdf[1]);
                                                                            String callMerit =
                                                                                    record.getAsString(
                                                                                        sdf[2]);
                                                                            String[] evalComp =
                                                                                    new String[numberField.length +
                                                                                    1];
                                                                            String[] markslist =
                                                                                    new String[numberField.length];
                                                                            String[] attList =
                                                                                    new String[numberField.length];

                                                                            for (int i =
                                                                                        0, j =
                                                                                        3;
                                                                                        i < evalComp.length;
                                                                                        i++, j++) {
                                                                                evalComp[i] = sdf[j];
                                                                            }

                                                                            for (int i =
                                                                                        0;
                                                                                        i < markslist.length;
                                                                                        i++) {
                                                                                attList[i] = comboBox[i].getValueAsString();

                                                                                if (attList[i].equalsIgnoreCase(
                                                                                                "P")) {
                                                                                    markslist[i] = numberField[i].getValueAsString();
                                                                                } else {
                                                                                    markslist[i] = "0";
                                                                                }
                                                                            }

                                                                            marksService.methodAddFinalMarks(
                                                                                    userid,
                                                                                    entity_id,
                                                                                    prog_id,
                                                                                    reg_no,
                                                                                    testnumber,
                                                                                    callMerit,
                                                                                    evalComp,
                                                                                    markslist,
                                                                                    attList,
                                                                                    new AsyncCallback<String>() {
                                                                                    public void onFailure(final Throwable arg0) {
                                                                                        MessageBox.show(
                                                                                                new MessageBoxConfig() {

                                                                                                {
                                                                                                    setIconCls(
                                                                                                            MessageBox.ERROR);
                                                                                                    setMsg(failureLabel +
                                                                                                            arg0.getMessage());
                                                                                                }
                                                                                            });
                                                                                    }

                                                                                    public void onSuccess(String arg0) {
                                                                                        MessageBox.show(
                                                                                                new MessageBoxConfig() {

                                                                                                {
                                                                                                    setIconCls(
                                                                                                            MessageBox.INFO);
                                                                                                    setMsg(msg.alert_successfulentry());
                                                                                                    setButtons(
                                                                                                            MessageBox.OK);
                                                                                                    setCallback(
                                                                                                            new MessageBox.PromptCallback() {
                                                                                                            public void execute(String btnID,
                                                                                                                    String text) {
                                                                                                                window.close();
                                                                                                                vPanel.remove(
                                                                                                                        gridPanel);
                                                                                                                System.out.println(
                                                                                                                        "clicking add button automatically");
                                                                                                                addMarksButton.fireEvent(
                                                                                                                        "click");
                                                                                                            }
                                                                                                        });
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                        } catch (Exception e1) {
                                                                            System.out.println(
                                                                                    "exception in retreiving data   " +
                                                                                    e1);
                                                                        }
                                                                    } else {
                                                                        MessageBox.alert(
                                                                                errorMsg,
                                                                                correctEntriesMsg);
                                                                    }
                                                                }
                                                            });

                                                        window.add(p1);
                                                        window.show();
                                                    }
                                                }
                                            }));
                                    } else if ((arg0.size() - 1) == 0) {
                                        MessageBox.alert(errorMsg,
                                            msg.error_record(),
                                            new AlertCallback() {
                                                public void execute() {
                                                    gridPanel.clear();
                                                    vPanel.remove(gridPanel);
                                                }
                                            });
                                    }
                                } // onsuccess ends
                            }); //
                    } else {                    	
                        MessageBox.alert(errorMsg, correctEntriesMsg);
                    }
                }
            });

        editMarksButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    boolean flag = true;
                    String crieteria_value = criteriaTypeCBox.getValue();
                    String search_value = textValue.getValue();

                    try {
                        entityTypeCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        entityNameCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        programNameCBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        if (crieteria_value == null) {
                            crieteria_value = "no";
                        }
                    } catch (Exception e1) {
                        System.out.println("here is the exception   " + e1);
                    }

                    if (flag == true) {
                        final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
                        final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
                        bd.setMargins(6, 6, 6, 6);

                        /**
                         * To get all student list which has been called for final
                         * merit
                         */
                        marksService.methodEditGridDataList(userid.substring(
                                1, 5), entityNameCBox.getValue(),
                            programNameCBox.getValue(), crieteria_value,
                            search_value,new AsyncCallback<List<GridDataBean>>() {
                                public void onFailure(Throwable arg0) {
                                    String[] msg = arg0.getMessage().split(":");
                                    MessageBox.alert(errorMsg, msg[1]);

                                    // MessageBox.alert("Failure",arg0.getMessage());
                                } // onFailure

                                public void onSuccess(List<GridDataBean> arg0) {
                                    if ((arg0.size() - 1) != 0) {
                                        // Hold data for reader object or list
                                        // data into double dimension array
                                        final String[][][] object;

                                        // Iterate list which returns from
                                        // server
                                        // Use of this iterator: To get Column
                                        // Names
                                        Iterator<GridDataBean> itr1 = arg0.iterator();
                                        itr1.hasNext();

                                        final GridDataBean gd1 = (GridDataBean) itr1.next();

                                        // sdf[]: to hold column names
                                        final String[] sdf = new String[4 +
                                            ((gd1.getComp().length) * 2)];
                                        sdf[0] = new String(gd1.getRegnum());
                                        sdf[1] = new String(gd1.getTestNumber());
                                        sdf[2] = new String(gd1.getCall());

                                        String[] s1 = gd1.getComp();
                                        Integer[] max_marks1 = new Integer[gd1.getComp().length];
                                        max_marks1 = gd1.getTotal_marks();

                                        final Integer[] max_marks = max_marks1;
                                        int count = 2;

                                        for (int k = 0; k < (s1.length * 2);
                                                k++) {
                                            if (k < s1.length) {
                                                sdf[k + 3] = new String(s1[k]);
                                            } else {
                                                sdf[k + 3] = new String(s1[k -
                                                        s1.length] +
                                                        labels.labelAttendance());
                                            }

                                            count++;
                                        } // For Loop ends

                                        sdf[count + 1] = new String(gd1.getTotal());

                                        // get Column name first,create your
                                        // bean with column name
                                        // FieldDefinition
                                        FieldDef[] def = new FieldDef[sdf.length];

                                        for (int defl = 0; defl < def.length;
                                                defl++) {
                                            def[defl] = new StringFieldDef(sdf[defl]);
                                        }

                                        // RecordDefinition
                                        final RecordDef recordDef = new RecordDef(def); // RecordDef

                                        object = new String[arg0.size() - 1][4 +
                                            sdf.length][sdf.length];

                                        String[][][] data = object;

                                        Iterator<GridDataBean> itr = arg0.iterator();
                                        int i = 0;

                                        itr.next();

                                        while (itr.hasNext()) {
                                            GridDataBean gd = (GridDataBean) itr.next();
                                            data[i][0][0] = gd.getRegnum();

                                            data[i][1][0] = gd.getTestNumber();
                                            data[i][2][0] = gd.getCall();

                                            String[][] s2 = gd.getComp1();
                                            count = 2;
                                            for (int k = 0;
                                                    k < (s2.length * 2); k++) {
                                                if (k < s2.length) {
                                                    data[i][k + 3][0] = s2[k][0];                                                    
                                                } else {
                                                    data[i][k + 3][0] = s2[k -
                                                        s2.length][1];                                                    
                                                }

                                                count++;
                                            } // For Loop ends

                                            data[i][count + 1][0] = gd.getTotal();
                                            System.out.println("total is " +
                                                gd.getTotal());
                                            i++;
                                        } // Iterator ends
                                          // Final data in data[] array

                                        MemoryProxy proxy = new MemoryProxy(data);
                                        ArrayReader reader = new ArrayReader(recordDef);
                                        final Store store = new Store(proxy,
                                                reader, true);
                                        store.load();

                                        // Here we have final data in store
                                        // creating column config
                                        ColumnConfig[] commonCol = new ColumnConfig[sdf.length];

                                        // commonCol[0]=new
                                        // CheckboxColumnConfig(cbSelectionModel);
                                        // setting editor for columns so that
                                        // user can edit
                                        for (int d = 0; d < commonCol.length;
                                                d++) {
                                            commonCol[d] = new ColumnConfig(sdf[d],
                                                    sdf[d], 120, true, null,
                                                    sdf[d]);
                                        }

                                        // creating BaseColumnConfig
                                        final BaseColumnConfig[] columns = commonCol;

                                        // creating ColumnModel
                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        columnModel.setDefaultSortable(true);

                                        // Creating object of editorgridpanel
                                        final GridPanel grid = new GridPanel(store,
                                                columnModel);
                                        // setting title, clicks to edit,frame,
                                        // size, StripeRows, TrackMouseOver and
                                        // visible
                                        grid.setTitle(consEditMarks);
                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setFrame(true);
                                        grid.setSize("1000px", "400px");
                                        grid.setAutoScroll(true);

                                        grid.setStripeRows(true);
                                        grid.setTrackMouseOver(true);
                                        grid.setVisible(true);

                                        // Adding toolbar with editorgridpanel
                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        ToolbarButton editButton1 = new ToolbarButton(consEditMarks);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button Button, EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();

                                                    if (records.length == 1) {
                                                        final Record record = records[0];

                                                        FlexTable editEntityProgramTable =
                                                            new FlexTable();
                                                        final NumberField[] numberField =
                                                            new NumberField[sdf.length -
                                                            4 -
                                                            (gd1.getComp().length)];
                                                        final ComboBox[] comboBox =
                                                            new ComboBox[sdf.length -
                                                            4 -
                                                            (gd1.getComp().length)];
                                                        final Label[] maxLabel = new Label[sdf.length -
                                                            4 -
                                                            (gd1.getComp().length)];

                                                        int numberCount = 0;

                                                        editEntityProgramTable.clear();

                                                        try {
                                                            for (int i = 0, j = 2;
                                                                    i < (sdf.length -
                                                                    1 -
                                                                    (gd1.getComp().length));
                                                                    i++, j++) {
                                                                editEntityProgramTable.setWidget(j,
                                                                    0,
                                                                    new Label(sdf[i]));

                                                                if (j > 4) {
                                                                    numberField[numberCount] = new NumberField();
                                                                    comboBox[numberCount] = new ComboBox();
                                                                    maxLabel[numberCount] = new Label();
                                                                    // comboBox[numberCount].setId(""+numberCount);
                                                                    methodAttComboProp(comboBox[numberCount]);
                                                                    methodMarksFieldsProp(numberField[numberCount]);

                                                                    comboBox[numberCount].setValue(record.getAsString(
                                                                            sdf[i +
                                                                            gd1.getComp().length]));
                                                                    numberField[numberCount].setMaxValue(max_marks[j -
                                                                        5]);
                                                                    maxLabel[numberCount].setText(
                                                                        "(" +
                                                                        labels.label_maxmarks() +
                                                                        ":" +
                                                                        max_marks[j -
                                                                        5] +
                                                                        ")");
                                                                    editEntityProgramTable.setWidget(j,
                                                                        1,
                                                                        maxLabel[numberCount]);

                                                                    editEntityProgramTable.setWidget(j,
                                                                        2,
                                                                        numberField[numberCount]);
                                                                    numberField[numberCount].setValue(record.getAsString(
                                                                            sdf[i]));
                                                                    editEntityProgramTable.setWidget(j,
                                                                        3,
                                                                        comboBox[numberCount]);

                                                                    numberCount++;
                                                                } else {
                                                                    editEntityProgramTable.setWidget(j,
                                                                        1,
                                                                        new Label(record.getAsString(
                                                                                sdf[i])));
                                                                }
                                                            }
                                                        } catch (Exception e1) {
                                                            System.out.println(
                                                                "in editEntityProgramTable   " +
                                                                e1);
                                                        }

                                                        final Panel p1 = new Panel();
                                                        p1.clear();
                                                        p1.add(editEntityProgramTable);

                                                        final Window window = new Window();
                                                        window.setTitle(consEditMarks);
                                                        window.setWidth(650);
                                                        window.setHeight(300);
                                                        window.setMinWidth(450);
                                                        window.setMinHeight(150);
                                                        window.setLayout(new FitLayout());
                                                        window.setPaddings(5);
                                                        window.setButtonAlign(Position.CENTER);
                                                        window.setPlain(true);
                                                        window.setResizable(false);
                                                        window.setAutoScroll(true);
                                                        window.setCloseAction(Window.CLOSE);
                                                        window.setFrame(true);
                                                        window.setClosable(true);
                                                        window.setModal(true);

                                                        Button updateButton = new Button(button_update);
                                                        window.addButton(updateButton);

                                                        updateButton.addListener(new ButtonListenerAdapter() {
                                                                public void onClick(
                                                                    Button updateButton,
                                                                    EventObject e) {
                                                                    Boolean flag =
                                                                        true;

                                                                    for (int i = 0;
                                                                            i < numberField.length;
                                                                            i++) {
                                                                        try {
                                                                            numberField[i].validate();
                                                                        } catch (Exception e1) {
                                                                            flag = false;
                                                                        }
                                                                    }

                                                                    for (int i = 0;
                                                                            i < comboBox.length;
                                                                            i++) {
                                                                        try {
                                                                            comboBox[i].validate();
                                                                        } catch (Exception e1) {
                                                                            flag = false;
                                                                        }
                                                                    }

                                                                    if (flag == true) {
                                                                        try {
                                                                            String entity_id =
                                                                                entityNameCBox.getValue();
                                                                            String prog_id =
                                                                                programNameCBox.getValue();
                                                                            String reg_no =
                                                                                record.getAsString(sdf[0]);
                                                                            String testnumber =
                                                                                record.getAsString(sdf[1]);
                                                                            String callMerit =
                                                                                record.getAsString(sdf[2]);
                                                                            String[] evalComp =
                                                                                new String[numberField.length +
                                                                                1];
                                                                            String[] markslist =
                                                                                new String[numberField.length];
                                                                            String[] attList =
                                                                                new String[numberField.length];

                                                                            for (int i =
                                                                                    0, j =
                                                                                    3;
                                                                                    i < evalComp.length;
                                                                                    i++, j++) {
                                                                                evalComp[i] = sdf[j];
                                                                            }

                                                                            for (int i =
                                                                                    0;
                                                                                    i < markslist.length;
                                                                                    i++) {
                                                                                attList[i] = comboBox[i].getValueAsString();

                                                                                if (attList[i].equalsIgnoreCase(
                                                                                            "P")) {
                                                                                    markslist[i] = numberField[i].getValueAsString();
                                                                                } else {
                                                                                    markslist[i] = "0";
                                                                                }
                                                                            }

                                                                            marksService.methodEditFinalMarks(userid,
                                                                                entity_id,
                                                                                prog_id,
                                                                                reg_no,
                                                                                testnumber,
                                                                                callMerit,
                                                                                evalComp,
                                                                                markslist,
                                                                                attList,
                                                                                new AsyncCallback<String>() {
                                                                                    public void onFailure(
                                                                                        Throwable caught) {
                                                                                        MessageBox.alert(failureLabel,
                                                                                            caught.getMessage());
                                                                                    }

                                                                                    public void onSuccess(
                                                                                        String result) {
                                                                                        MessageBox.show(new MessageBoxConfig() {

                                                                                                {
                                                                                                    setIconCls(MessageBox.INFO);
                                                                                                    setMsg(msg.alert_successfulentry());
                                                                                                    setButtons(MessageBox.OK);
                                                                                                    setCallback(new MessageBox.PromptCallback() {
                                                                                                            public void execute(
                                                                                                                String btnID,
                                                                                                                String text) {
                                                                                                                window.close();
                                                                                                                editMarksButton.fireEvent(
                                                                                                                    "click");
                                                                                                            }
                                                                                                        });
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                          
                                                                        } catch (Exception e1) {
                                                                            System.out.println(
                                                                                "exception in retreiving data   " +
                                                                                e1);
                                                                        }
                                                                    } else {
                                                                        MessageBox.alert(errorMsg,
                                                                            correctEntriesMsg);
                                                                    }
                                                                }
                                                            });

                                                        window.add(p1);
                                                        window.show();
                                                    }
                                                }
                                            });

                                        topToolBar.addButton(editButton);
                                        grid.setTopToolbar(topToolBar);
                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        vPanel.add(gridPanel);
                                    } else {
                                        MessageBox.alert(errorMsg,
                                            msg.error_record(),
                                            new AlertCallback() {
                                                public void execute() {
                                                    gridPanel.clear();
                                                    vPanel.remove(gridPanel);
                                                }
                                            });
                                    }
                                } // onsuccess ends
                            });
                    } else {
                        MessageBox.alert(errorMsg, correctEntriesMsg);
                    }
                }
            });

        vPanel.clear();
        vInPanel.clear();
        gridPanel.clear();
        vPanel.add(heading);
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
        marksService.methodPopulateSuggestion(criteria, program_id,entity_id,
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
