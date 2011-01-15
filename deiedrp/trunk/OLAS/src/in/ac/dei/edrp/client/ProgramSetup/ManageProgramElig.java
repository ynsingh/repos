package in.ac.dei.edrp.client.ProgramSetup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
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
import com.gwtext.client.widgets.grid.event.RowSelectionListener;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

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
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.List;


public class ManageProgramElig {
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    Validator validator = new Validator();
    Object[][] data;
    Object[][] data1;
    String str = null;
    String[] values;
    VerticalPanel vp = new VerticalPanel();
    Validator valid = new Validator();
    VerticalPanel panel = new VerticalPanel();
    String progID;
    String Program;
    String entity;
    String entity_id;
    String branchId = "";
    String branchName = "";
    String catCode;
    int branchCount = 0;
    String modifierid;
    String[] Progdetails = new String[5];
    String[] editProg = new String[10];
    Window window1 = new Window();
    messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    String consEntityName = cons.entityName();
    String consEntityType = cons.entityType();
    String errorMsg;
    String confirm;
    ToolbarButton editButton1 = new ToolbarButton("Edit");
    ToolbarButton deletebutton1 = new ToolbarButton("Delete");
    String pagename = "Program Age Eligibility";
    String university_id;
    CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();

    public ManageProgramElig(String userid) {
        this.modifierid = userid;
    }

    public VerticalPanel onModuleLoad() {
        final CM_ComboBoxes entityNameCB = new CM_ComboBoxes();
        final CM_ComboBoxes entityTypeCB = new CM_ComboBoxes();
        final Button submitButton = new Button("Submit");
        errorMsg = msg.error();
        confirm = msg.confirm();

        vp.clear();
        vp.setWidth("1200px");

        Label selectEntityName = new Label(consEntityName);
        Label selectEntity = new Label(consEntityType);

        final FormPanel mainForm = new FormPanel();
        FlexTable flex3 = new FlexTable();

        entityTypeCB.onModuleLoad();
        entityNameCB.onModuleLoad();

        entityNameCB.entityCombo.disable();
        //entityTypeCB.entityDescCB.disable();
        ///cbSelectionModel.lock();
        //editButton1.disable();
        //deletebutton1.disable();
        //submitButton.disable();
        flex3.setCellSpacing(10);
        flex3.setWidget(0, 0, selectEntity);
        flex3.setWidget(0, 1, entityTypeCB.entityDescCB);
        flex3.setWidget(0, 2, selectEntityName);
        flex3.setWidget(0, 3, entityNameCB.entityCombo);
        flex3.setWidget(1, 2, submitButton);

        mainForm.setLabelAlign(Position.TOP);
        mainForm.setTitle(cons.manageProgElig());
        mainForm.setPaddings(5);
        mainForm.setWidth("100%");

        vp.add(flex3);

        entityTypeCB.entityDescCB.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    entityNameCB.entityCombo.clearValue();
                    entityNameCB.entityCombo.disable();

                    String entity_type = entityTypeCB.entityDescCB.getValue();

                    connectTemp.Entity_Name(modifierid, entity_type,
                        new AsyncCallback<CM_entityInfoGetter[]>() {
                            public void onFailure(Throwable caught) {
                                MessageBox.alert(cons.dbError(),
                                    caught.getMessage());
                            }

                            public void onSuccess(CM_entityInfoGetter[] arg0) {
                                RecordDef recordDef = new RecordDef(new FieldDef[] {
                                            new StringFieldDef("EntityName"),
                                            new StringFieldDef("Entitycode")
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

                                entityNameCB.entityCombo.setStore(store);
                                entityNameCB.entityCombo.enable();
                            }
                        });
                }
            });

        entityNameCB.entityCombo.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    if (index >= 0) {
                        entity = entityNameCB.entityCombo.getRawValue();
                        entity_id = entityNameCB.entityCombo.getValue();
                    }
                }
            });

        submitButton.addListener(new ButtonListenerAdapter() {
                int checkNull() {
                    int check = 0;

                    check = markIV(entityTypeCB.entityDescCB) +
                        markIV(entityNameCB.entityCombo);

                    return (check);
                }

                public void onClick(Button button, EventObject e) {
                    panel.clear();

                    int flag = checkNull();

                    if (flag > 0) {
                        MessageBox.alert(msg.fieldsReqd());
                    } else {
                        Edit();
                    }
                }
            });

        return vp;
    }

    public void Edit() {
        panel.clear();

        cbSelectionModel.lock();
        //	editButton1.disable();
        //	deletebutton1.disable();
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

                                public void onSuccess(List<String> result) {
                                    if (result.size() == 0) {
                                    	cbSelectionModel.unlock();
                                        editButton1.setDisabled(false);
                                        deletebutton1.setDisabled(false);

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

                                                public void onRowSelect(
                                                    RowSelectionModel sm,
                                                    int rowIndex, Record record) {
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

                                                public void onSelectionChange(
                                                    RowSelectionModel sm) {
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

                                        for (int i = 0; i < result.size();
                                                i++) {
                                            values[i] = result.get(i);
                                            MessageBox.alert("authorities" +
                                                values[i]);

                                            if (values[i].equalsIgnoreCase(
                                                        "create")) {
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "view")) {
                                                editButton1.setDisabled(true);
                                                deletebutton1.setDisabled(true);
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "update")) {
                                                cbSelectionModel.unlock();
                                                //altered here after dayal's defect
                                                deletebutton1.setDisabled(true);

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
                                                                editButton1.setDisabled(true);
                                                            } else {
                                                                editButton1.setDisabled(false);
                                                            }
                                                        }

                                                        public void onRowSelect(
                                                            RowSelectionModel sm,
                                                            int rowIndex,
                                                            Record record) {
                                                            if ((sm.getCount() > 1) ||
                                                                    (sm.getCount() < 1)) {
                                                                editButton1.setDisabled(true);
                                                            } else {
                                                                editButton1.setDisabled(false);
                                                            }
                                                        }

                                                        public void onSelectionChange(
                                                            RowSelectionModel sm) {
                                                            if ((sm.getCount() > 1) ||
                                                                    (sm.getCount() < 1)) {
                                                                editButton1.setDisabled(true);
                                                            } else {
                                                                editButton1.setDisabled(false);
                                                            }
                                                        }
                                                    });
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "delete")) {
                                                cbSelectionModel.unlock();
                                                //altered here after dayal's defect
                                                editButton1.setDisabled(true);

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
                                                                deletebutton1.setDisabled(true);
                                                            } else {
                                                                deletebutton1.setDisabled(false);
                                                            }
                                                        }

                                                        public void onRowSelect(
                                                            RowSelectionModel sm,
                                                            int rowIndex,
                                                            Record record) {
                                                            if ((sm.getCount() < 1)) {
                                                                deletebutton1.setDisabled(true);
                                                            } else {
                                                                deletebutton1.setDisabled(false);
                                                            }
                                                        }

                                                        public void onSelectionChange(
                                                            RowSelectionModel sm) {
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
            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert(cons.dbError(), caught.getMessage());
                }

                public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("program"),
                                new StringFieldDef("branch"),
                                new StringFieldDef("category"),
                                new StringFieldDef("age")
                            });

                    GridPanel grid = new GridPanel();

                    data1 = new Object[arg0.length][4];

                    for (int i = 0; i < arg0.length; i++) {
                        for (int k = 0; k < 4; k++) {
                            if (k == 0) {
                                str = arg0[i].getProgram_name();
                            } else if (k == 1) {
                                str = arg0[i].getBranch_name();
                            } else if (k == 2) {
                                str = arg0[i].getcategory();
                            } else if (k == 3) {
                                str = arg0[i].getage_limit();
                            }

                            data1[i][k] = str;
                        }
                    }

                    data = data1;

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);

                    store.load();
                    grid.setStore(store);

                    BaseColumnConfig[] columns = new BaseColumnConfig[] {
                            new CheckboxColumnConfig(cbSelectionModel),
                            
                            new ColumnConfig(cons.label_programname(),
                                "program", 80, true, null, "program"),
                            new ColumnConfig(cons.label_branchname(), "branch",
                                120, true),
                            new ColumnConfig(cons.category(), "category", 70,
                                true),
                            new ColumnConfig(cons.agelimit(), "age", 70, true)
                        };

                    ColumnModel columnModel = new ColumnModel(columns);

                    grid.setColumnModel(columnModel);
                    grid.setFrame(true);

                    grid.setAutoExpandColumn("program");
                    grid.setSelectionModel(cbSelectionModel);
                    grid.setWidth(600);
                    grid.setHeight(300);
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
                                    MessageBox.alert(errorMsg,
                                        msg.atleastOneRecord());
                                } else if (records.length > 1) {
                                    MessageBox.alert(errorMsg,
                                        msg.onlyOneRecord());
                                } else if (records.length == 1) {
                                    Record record = records[0];

                                    final String[] Univ = new String[7];

                                    Univ[1] = record.getAsString("program");
                                    Univ[2] = record.getAsString("branch");
                                    Univ[3] = record.getAsString("category");
                                    Univ[4] = record.getAsString("age");

                                    final Label ProgramLabel = new Label();
                                    final Label branchLabel = new Label();
                                    final Label categoryLabel = new Label();
                                    final NumberField ageNF = new NumberField();

                                    ageNF.setWidth(130);
                                    ageNF.setAllowBlank(false);
                                    ageNF.setAllowDecimals(false);
                                    ageNF.setAllowNegative(false);
                                    ageNF.setMaxLength(2);

                                    ProgramLabel.setText(Univ[1]);
                                    branchLabel.setText(Univ[2]);
                                    categoryLabel.setText(Univ[3]);
                                    ageNF.setValue(Univ[4]);

                                    Button save = new Button(cons.button_update());

                                    save.addListener(new ButtonListenerAdapter() {
                                            int checkNull() {
                                                if (validator.nullValidator(
                                                            ageNF.getRawValue())) {
                                                    return 1;
                                                } else {
                                                    return 0;
                                                }
                                            }

                                            public void onClick(Button button,
                                                EventObject e) {
                                                int flag = checkNull();

                                                try {
                                                    ageNF.validate();
                                                } catch (Exception exception) {
                                                    flag++;
                                                }

                                                if (flag > 0) {
                                                    MessageBox.alert(msg.checkFields());
                                                } else {
                                                    editProg[0] = entity_id;
                                                    editProg[1] = Univ[1];
                                                    editProg[2] = Univ[2];
                                                    editProg[3] = Univ[3];
                                                    editProg[4] = ageNF.getRawValue();

                                                    MessageBox.confirm(confirm,
                                                        msg.alert_confirmentries(),
                                                        new MessageBox.ConfirmCallback() {
                                                            public void execute(
                                                                String btnID) {
                                                                if (btnID.matches(
                                                                            "yes")) {
                                                                    connectTemp.editProgAgeLimit(editProg,
                                                                        modifierid,
                                                                        new AsyncCallback<String>() {
                                                                            public void onFailure(
                                                                                Throwable caught) {
                                                                                MessageBox.alert(cons.dbError(),
                                                                                    caught.getMessage());
                                                                            }

                                                                            public void onSuccess(
                                                                                String arg0) {
                                                                                MessageBox.show(new MessageBoxConfig() {

                                                                                        {
                                                                                            setMsg(msg.alert_oneditsuccess());

                                                                                            setButtons(MessageBox.OK);
                                                                                            setCallback(new MessageBox.PromptCallback() {
                                                                                                    public void execute(
                                                                                                        String btnID,
                                                                                                        String text) {
                                                                                                        try {
                                                                                                            window1.close();

                                                                                                            Edit();
                                                                                                        } catch (Exception e) {
                                                                                                        }
                                                                                                    }
                                                                                                });
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

                                    final FlexTable editFlex = new FlexTable();

                                    editFlex.setCellSpacing(10);

                                    editFlex.setWidget(1, 0,
                                        new Label(cons.label_programname()));
                                    editFlex.setWidget(1, 3, ProgramLabel);
                                    editFlex.setWidget(2, 0,
                                        new Label(cons.label_branchname()));
                                    editFlex.setWidget(2, 3, branchLabel);
                                    editFlex.setWidget(3, 0,
                                        new Label(cons.category()));
                                    editFlex.setWidget(3, 3, categoryLabel);
                                    editFlex.setWidget(4, 0,
                                        new Label(cons.agelimit()));
                                    editFlex.setWidget(4, 3, ageNF);

                                    HorizontalPanel hp = new HorizontalPanel();
                                    hp.setSpacing(20);

                                    hp.add(save);

                                    final Panel center = new Panel();

                                    center.add(editFlex);
                                    center.add(hp);

                                    BorderLayoutData centerData = new BorderLayoutData(RegionPosition.CENTER);
                                    centerData.setMargins(6, 6, 6, 6);

                                    ScrollPanel sPanel = new ScrollPanel();
                                    sPanel.setHeight("10em");
                                    sPanel.add(center);

                                    final Window window = new Window();

                                    window.setTitle(cons.heading_manageprogramAgeElig());
                                    window.setClosable(true);
                                    window.setWidth(450);
                                    window.setHeight(250);
                                    window.setPlain(true);
                                    window.setLayout(new BorderLayout());
                                    window.add(sPanel, centerData);
                                    window.setCloseAction(Window.HIDE);
                                    window.setModal(true);
                                    //window.show();
                                    window1 = window;
                                    window1.show();
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
                                    MessageBox.alert(errorMsg,
                                        msg.select_recordsdeletion());
                                } else {
                                    MessageBox.confirm(confirm,
                                        msg.alert_ondelete(),
                                        new MessageBox.ConfirmCallback() {
                                            public void execute(String btnID) {
                                                if (btnID.matches("yes")) {
                                                    for (int i = 0;
                                                            i < records.length;
                                                            i++) {
                                                        final String[] Univ = new String[7];

                                                        Univ[0] = entity_id;
                                                        Univ[1] = records[i].getAsString(
                                                                "program");
                                                        Univ[2] = records[i].getAsString(
                                                                "branch");
                                                        Univ[3] = records[i].getAsString(
                                                                "category");
                                                        Univ[4] = records[i].getAsString(
                                                                "age");

                                                        connectTemp.deleteProgAge(Univ,
                                                            new AsyncCallback<String>() {
                                                                public void onFailure(
                                                                    Throwable caught) {
                                                                    MessageBox.alert(cons.dbError(),
                                                                        caught.getMessage());
                                                                }

                                                                public void onSuccess(
                                                                    String arg0) {
                                                                    window1.close();
                                                                }
                                                            });
                                                    }

                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setMsg(msg.alert_ondeletesuccess());

                                                                setButtons(MessageBox.OK);
                                                                setCallback(new MessageBox.PromptCallback() {
                                                                        public void execute(
                                                                            String btnID,
                                                                            String text) {
                                                                            try {
                                                                                Edit();
                                                                            } catch (Exception e) {
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

                    toolbar.addButton(disable);
                    panel.add(grid);
                    vp.add(panel);
                }
            });
    }

    public int markIV(TextField t) {
        int check = 0;

        if ((valid.nullValidator(t.getValueAsString()) == true)) {
            try {
                check++;
                t.markInvalid("");
            } catch (Exception e) {
            }
        }

        return check;
    }
}
