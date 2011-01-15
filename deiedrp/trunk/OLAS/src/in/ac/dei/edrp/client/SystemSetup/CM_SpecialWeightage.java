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
   Author Name :Ashish Yadav
 */
package in.ac.dei.edrp.client.SystemSetup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
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
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.List;


public class CM_SpecialWeightage {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    Label label3 = new Label("Dayalbagh Educational Institute");
    Label selectLabel = new Label(constants.label_selectcomponent());
    Label marksLabel = new Label(constants.label_weightagepercentage());
    Label liLabel = new Label(constants.label_selectgroup());
    String uniid;
    Object[][] object1;
    final VerticalPanel fullpage1 = new VerticalPanel();
    Validator validator = new Validator();
    Label program_name = new Label(constants.label_universityid());
    Label categoryLabel = new Label(constants.label_specialcategory());
    Label groupLabel = new Label(constants.label_selectgroup());
    Label weightLabel = new Label(constants.label_weightagepercentage());
    Label uni_id = new Label();
    Label branch = new Label();
    Object[][] object;
    String group;
    String weightage;
    String pagename = "Special Weightage";
    String[] values;
    ToolbarButton editButton = new ToolbarButton("Edit");
    ToolbarButton deletebutton = new ToolbarButton("Delete");

    public CM_SpecialWeightage(String user_id) {
        this.uniid = user_id;
    }

    public void MethodAddSpecialWeightage() {
        vPanel.clear();
        fullpage1.clear();

        final FormPanel fPanel = new FormPanel();
        final NumberField marksField = new NumberField();
        final NumberField marksField1 = new NumberField();
        final FlexTable flexTable = new FlexTable();
        HorizontalPanel hPanel2 = new HorizontalPanel();
        HorizontalPanel hPanel3 = new HorizontalPanel();
        final FlexTable regisTable = new FlexTable();
        final ListBox listBox = new ListBox();
        final ListBox listBox1 = new ListBox();
        final ComboBox comboBox = new ComboBox();
        ScrollPanel scPanel3 = new ScrollPanel();
        ScrollPanel scPanel = new ScrollPanel();
        final Button saveButton = new Button(constants.saveButton());
        final Button manageButton = new Button(constants.button_manage());

        final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();

        final Panel p1 = new Panel();
        final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
        bd.setMargins(6, 6, 6, 6);

        marksField.setAllowNegative(false);
        marksField.setAllowBlank(false);
        marksField.setMaxLength(6);
        marksField.setValue(1);
        marksField.setMinValue(1);
        marksField.setMaxValue(100);

        marksField1.setAllowNegative(false);
        marksField1.setAllowBlank(false);
        marksField1.setMaxLength(6);
        marksField1.setMinValue(1);
        marksField1.setMaxValue(100);

        scPanel3.setSize("100%", "365px");
        scPanel.setSize("100%", "160px");

        fPanel.setFrame(true);
        fPanel.setSize("400px", "170px");
        fPanel.setTitle(constants.heading_specialweightagesetup());

        listBox.addItem("Select", null);
        listBox.addItem("Group 1", "G1");
        listBox.addItem("Group 2", "G2");
        listBox.addItem("Group 3", "G3");
        listBox.addItem("Group 4", "G4");
        listBox.addItem("Group 5", "G5");

        listBox1.addItem("Group 1", "G1");
        listBox1.addItem("Group 2", "G2");
        listBox1.addItem("Group 3", "G3");
        listBox1.addItem("Group 4", "G4");
        listBox1.addItem("Group 5", "G5");

        comboBox.setForceSelection(true);
        comboBox.setMinChars(1);
        comboBox.setFieldLabel("State");
        comboBox.setDisplayField("State");
        comboBox.setValueField("number");
        comboBox.setMode(ComboBox.LOCAL);
        comboBox.setTriggerAction(ComboBox.ALL);
        comboBox.setEmptyText("Select");
        comboBox.setLoadingText("Searching...");
        comboBox.setTypeAhead(true);
        comboBox.setSelectOnFocus(true);
        comboBox.setWidth(182);
        comboBox.setHideTrigger(false);
        comboBox.setAllowBlank(false);

        //all fields disabled initially
        comboBox.disable();
        listBox.setEnabled(false);
        marksField.disable();
        saveButton.disable();
        manageButton.disable();
        cbSelectionModel.lock();
        editButton.disable();
        deletebutton.disable();

        loginconnect.getAuthorityOnPage(pagename, uniid,
            new AsyncCallback<CM_userInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                }

                public void onSuccess(CM_userInfoGetter[] result) {
                    if (result[0].getAuthority().equalsIgnoreCase("1")) {
                        loginconnect.getsecondaryauthorities(uniid, pagename,
                            new AsyncCallback<List<String>>() {
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert("Failure" +
                                        arg0.getMessage());
                                }

                                public void onSuccess(List<String> result) {
                                    if (result.size() == 0) {
                                        comboBox.enable();
                                        listBox.setEnabled(true);
                                        marksField.enable();
                                        saveButton.enable();
                                        manageButton.enable();
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
                                                comboBox.enable();
                                                listBox.setEnabled(true);
                                                marksField.enable();
                                                saveButton.enable();
                                                manageButton.enable();
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "view")) {
                                                manageButton.fireEvent("click");
                                            }

                                            if (values[i].equalsIgnoreCase(
                                                        "update")) {
                                                manageButton.fireEvent("click");
                                                cbSelectionModel.unlock();
                                                editButton.setDisabled(false);
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

                                            if (values[i].equalsIgnoreCase(
                                                        "delete")) {
                                                manageButton.fireEvent("click");
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

        connectService.methodgetdescriptions(uniid,
            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                }

                public void onSuccess(CM_ProgramInfoGetter[] result) {
                    if (result.length == 0) {
                        MessageBox.show(new MessageBoxConfig() {

                                {
                                    setTitle(msgs.alert());
                                    setMsg(msgs.error_record());
                                    setIconCls(MessageBox.INFO);
                                    setButtons(MessageBox.OK);
                                }
                            });
                    }

                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("State"),
                                new StringFieldDef("number"),
                            });

                    object = new String[result.length][2];

                    Object[][] data = object;

                    for (int i = 0; i < result.length; i++) {
                        object[i][0] = result[i].getProgram_name();
                        object[i][1] = result[i].getProgram_id();
                    }

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    comboBox.setStore(store);
                }
            });

        comboBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox1, Record record,
                    int index) {
                    connectService.methodgetvalue(uniid,
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable arg0) {
                            }

                            public void onSuccess(CM_ProgramInfoGetter[] result) {
                                final String component = comboBox.getValue();

                                for (int i = 0; i < result.length; i++) {
                                    String code = result[i].getprogram_id();

                                    if (code.equals(component)) {
                                        MessageBox.show(new MessageBoxConfig() {

                                                {
                                                    setTitle(msgs.error());
                                                    setMsg(msgs.error_duplicatecomponentname());
                                                    setIconCls(MessageBox.ERROR);
                                                    setButtons(MessageBox.OK);
                                                    setCallback(new MessageBox.PromptCallback() {
                                                            public void execute(
                                                                String btnID,
                                                                String text) {
                                                                comboBox.clearValue();
                                                                manageButton.fireEvent(
                                                                    "click");
                                                            }
                                                        });
                                                }
                                            });
                                    }
                                }
                            }
                        });
                }
            });

        flexTable.setWidget(0, 0, selectLabel);
        flexTable.setWidget(0, 1, comboBox);
        flexTable.setWidget(2, 0, liLabel);
        flexTable.setWidget(2, 1, listBox);
        flexTable.setWidget(4, 0, marksLabel);
        flexTable.setWidget(4, 1, marksField);
        flexTable.setWidget(15, 0, saveButton);
        flexTable.setWidget(15, 1, manageButton);

        saveButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    final String component = comboBox.getValue();
                    final String group = listBox.getValue(listBox.getSelectedIndex());
                    final String marks = marksField.getRawValue();

                    boolean flag = true;

                    try {
                        comboBox.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    if ((validator.nullValidator(group))) {
                        MessageBox.show(new MessageBoxConfig() {

                                {
                                    setTitle(msgs.error());
                                    setMsg(msgs.error_componentorgroup());
                                    setIconCls(MessageBox.ERROR);
                                    setButtons(MessageBox.OK);
                                    setCallback(new MessageBox.PromptCallback() {
                                            public void execute(String btnID,
                                                String text) {
                                            }
                                        });
                                }
                            });
                    } else {
                        int a = 0;

                        try {
                            marksField.validate();
                        } catch (Exception e2) {
                            a = 1;
                        }

                        if ((a == 1)) {
                            MessageBox.show(new MessageBoxConfig() {

                                    {
                                        setTitle(msgs.error());
                                        setMsg(msgs.checkFields());
                                        setIconCls(MessageBox.ERROR);
                                        setButtons(MessageBox.OK);
                                    }
                                });
                        }

                        if ((flag == true) && (a == 0)) {
                            MessageBox.show(new MessageBoxConfig() {

                                    {
                                        setTitle(msgs.confirm());
                                        setMsg(msgs.alert_confirmentries());
                                        setIconCls(MessageBox.QUESTION);
                                        setButtons(MessageBox.YESNO);
                                        setCallback(new MessageBox.PromptCallback() {
                                                public void execute(
                                                    String btnID, String text) {
                                                    if (btnID.equalsIgnoreCase(
                                                                "yes")) {
                                                        connectService.insertspecialweightagedetails(uniid,
                                                            component, group,
                                                            marks,
                                                            new AsyncCallback<String>() {
                                                                public void onFailure(
                                                                    Throwable arg0) {
                                                                }

                                                                public void onSuccess(
                                                                    String arg0) {
                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(msgs.alert());
                                                                                setMsg(msgs.alert_successfulentry());
                                                                                setIconCls(MessageBox.INFO);
                                                                                setButtons(MessageBox.OK);
                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                        public void execute(
                                                                                            String btnID,
                                                                                            String text) {
                                                                                            comboBox.clearValue();
                                                                                            listBox.setSelectedIndex(0);
                                                                                            marksField.setValue(1);
                                                                                            manageButton.fireEvent(
                                                                                                "click");
                                                                                        }
                                                                                    });
                                                                            }
                                                                        });
                                                                }
                                                            });
                                                    } else if (btnID.equalsIgnoreCase(
                                                                "no")) {
                                                        comboBox.clearValue();
                                                        listBox.setSelectedIndex(0);
                                                        marksField.setValue(1);
                                                        manageButton.fireEvent(
                                                            "click");
                                                    }
                                                }
                                            });
                                    }
                                });
                        } else {
                            MessageBox.show(new MessageBoxConfig() {

                                    {
                                        setTitle(msgs.error());
                                        setMsg(msgs.checkFields());
                                        setIconCls(MessageBox.ERROR);
                                        setButtons(MessageBox.OK);
                                        setCallback(new MessageBox.PromptCallback() {
                                                public void execute(
                                                    String btnID, String text) {
                                                }
                                            });
                                    }
                                });
                        }
                    }
                }
            });

        manageButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    connectService.getspecialrecords(uniid,
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable arg0) {
                            }

                            public void onSuccess(CM_ProgramInfoGetter[] result) {
                                final GridPanel grid = new GridPanel();
                                object1 = new Object[result.length][4];

                                if (result.length == 0) {
                                    MessageBox.show(new MessageBoxConfig() {

                                            {
                                                setTitle(msgs.alert());
                                                setMsg(msgs.error_record());
                                                setIconCls(MessageBox.INFO);
                                                setButtons(MessageBox.OK);
                                                grid.setVisible(false);
                                                setCallback(new MessageBox.PromptCallback() {
                                                        public void execute(
                                                            String btnID,
                                                            String text) {
                                                            if (btnID.equals(
                                                                        "ok")) {
                                                                grid.setVisible(false);
                                                            }
                                                        }
                                                    });
                                            }
                                        });
                                }

                                final RecordDef rDef = new RecordDef(new FieldDef[] {
                                            new StringFieldDef("university_id"),
                                            new StringFieldDef("Special Category"),
                                            new StringFieldDef("Group Name"),
                                            new StringFieldDef("Weightage Percentage"),
                                        });

                                Object str = null;

                                for (int i = 0; i < result.length; i++) {
                                    for (int k = 0; k < 4; k++) {
                                        try {
                                            if (k == 0) {
                                                str = result[i].getUniversity_id();
                                            }

                                            if (k == 1) {
                                                str = result[i].getprogram_id();
                                            } else if (k == 2) {
                                                str = result[i].getprogram_name();
                                            } else if (k == 3) {
                                                str = result[i].getentity_id();
                                            }
                                        } catch (Exception e) {
                                            System.out.println("ex " + e);
                                        }

                                        object1[i][k] = str;
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
                                        
                                        new ColumnConfig("Special Category",
                                            "Special Category", 170, true,
                                            null, "entityType"),
                                        new ColumnConfig("Group Name",
                                            "Group Name", 120, true, null,
                                            "entityname"),
                                        new ColumnConfig("Weightage Percentage",
                                            "Weightage Percentage", 80, true,
                                            null, "entitycode"),
                                    };

                                final ColumnModel columnModel = new ColumnModel(columns);
                                grid.setColumnModel(columnModel);

                                grid.setFrame(true);
                                grid.setStripeRows(true);
                                grid.setAutoExpandColumn("entityType");
                                grid.setAutoExpandColumn("entityname");
                                grid.setAutoExpandColumn("entitycode");
                                grid.setSelectionModel(cbSelectionModel);
                                grid.setAutoWidth(true);
                                grid.setWidth(500);
                                grid.setHeight(280);

                                Toolbar topToolBar = new Toolbar();
                                topToolBar.addFill();

                                final ToolbarButton editButton1 = new ToolbarButton(constants.edit());

                                editButton1.disable();

                                editButton = editButton1;

                                editButton1.addListener(new ButtonListenerAdapter() {
                                        public void onClick(Button Button,
                                            EventObject e) {
                                            Record[] records = cbSelectionModel.getSelections();
                                            String msg = "";

                                            if (records.length < 1) {
                                                MessageBox.alert(msgs.alert(),
                                                    msgs.error_selectrecord());
                                            } else if (records.length == 1) {
                                                Record record = records[0];

                                                try {
                                                    msg += record.getAsString(
                                                        "University Name");

                                                    String[] Univ = new String[4];

                                                    Univ[0] = record.getAsString(
                                                            "Special Category");
                                                    Univ[1] = record.getAsString(
                                                            "Group Name");
                                                    Univ[2] = record.getAsString(
                                                            "Weightage Percentage");
                                                    Univ[3] = record.getAsString(
                                                            "university_id");

                                                    FlexTable editInstiTable = new FlexTable();

                                                    editInstiTable.clear();

                                                    uni_id.setText(Univ[3]);
                                                    branch.setText(Univ[0]);
                                                    marksField1.setValue(Univ[2]);
                                                    group = Univ[1];

                                                    if (group.equalsIgnoreCase(
                                                                "G1")) {
                                                        listBox1.setSelectedIndex(0);
                                                    } else if (group.equalsIgnoreCase(
                                                                "G2")) {
                                                        listBox1.setSelectedIndex(1);
                                                    } else if (group.equalsIgnoreCase(
                                                                "G3")) {
                                                        listBox1.setSelectedIndex(2);
                                                    } else if (group.equalsIgnoreCase(
                                                                "G4")) {
                                                        listBox1.setSelectedIndex(3);
                                                    } else {
                                                        listBox1.setSelectedIndex(4);
                                                    }

                                                    editInstiTable.setWidget(0,
                                                        0, program_name);
                                                    editInstiTable.setWidget(0,
                                                        1, uni_id);
                                                    editInstiTable.setWidget(2,
                                                        0, categoryLabel);
                                                    editInstiTable.setWidget(2,
                                                        1, branch);
                                                    editInstiTable.setWidget(4,
                                                        0, groupLabel);
                                                    editInstiTable.setWidget(4,
                                                        1, listBox1);
                                                    editInstiTable.setWidget(6,
                                                        0, weightLabel);
                                                    editInstiTable.setWidget(6,
                                                        1, marksField1);

                                                    p1.clear();
                                                    p1.add(editInstiTable);

                                                    Button b1 = new Button(constants.button_update());

                                                    final Window window = new Window();
                                                    window.setTitle(constants.heading_updatedetails());
                                                    window.setWidth(550);
                                                    window.setHeight(300);
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
                                                                final String group1 =
                                                                    listBox1.getValue(listBox1.getSelectedIndex());
                                                                weightage = marksField1.getRawValue();

                                                                int a = 0;

                                                                try {
                                                                    marksField1.validate();
                                                                } catch (Exception e2) {
                                                                    a = 1;
                                                                }

                                                                if ((a == 1)) {
                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(msgs.error());
                                                                                setMsg(msgs.checkFields());
                                                                                setIconCls(MessageBox.ERROR);
                                                                                setButtons(MessageBox.OK);
                                                                            }
                                                                        });
                                                                } else {
                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(msgs.confirm());
                                                                                setMsg(msgs.alert_onedit());
                                                                                setIconCls(MessageBox.QUESTION);
                                                                                setButtons(MessageBox.YESNO);
                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                        public void execute(
                                                                                            String btnID,
                                                                                            String text) {
                                                                                            if (btnID.equals(
                                                                                                        "yes")) {
                                                                                                connectService.methodupdatespecialweightage(uni_id.getText(),
                                                                                                    branch.getText(),
                                                                                                    group1,
                                                                                                    weightage,
                                                                                                    uniid,
                                                                                                    new AsyncCallback<String>() {
                                                                                                        public void onFailure(
                                                                                                            Throwable arg0) {
                                                                                                        }

                                                                                                        public void onSuccess(
                                                                                                            String arg0) {
                                                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                                                    {
                                                                                                                        setTitle(msgs.alert());
                                                                                                                        setMsg(msgs.alert_oneditsuccess());
                                                                                                                        setIconCls(MessageBox.INFO);
                                                                                                                        setButtons(MessageBox.OK);
                                                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                                                public void execute(
                                                                                                                                    String btnID,
                                                                                                                                    String text) {
                                                                                                                                    if (btnID.equals(
                                                                                                                                                "ok")) {
                                                                                                                                        window.close();
                                                                                                                                        manageButton.fireEvent(
                                                                                                                                            "click");
                                                                                                                                        cbSelectionModel.clearSelections();
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            });
                                                                                                                    }
                                                                                                                });
                                                                                                        }
                                                                                                    });
                                                                                            } else if (btnID.equals(
                                                                                                        "no")) {
                                                                                                window.close();
                                                                                                manageButton.fireEvent(
                                                                                                    "click");
                                                                                                cbSelectionModel.clearSelections();
                                                                                            }
                                                                                        }
                                                                                    });
                                                                            }
                                                                        });
                                                                }
                                                            }
                                                        });
                                                } catch (Exception e1) {
                                                    System.out.println(e1);
                                                }
                                            }
                                        }
                                    });

                                final ToolbarButton deletebutton1 = new ToolbarButton(
                                        "Delete");

                                deletebutton1.setDisabled(true);

                                deletebutton = deletebutton1;

                                deletebutton1.addListener(new ButtonListenerAdapter() {
                                        public void onClick(Button delButton,
                                            EventObject e) {
                                            final Record[] records = cbSelectionModel.getSelections();

                                            if (records.length < 1) {
                                                MessageBox.alert(msgs.error(),
                                                    msgs.error_selectrecord());
                                            } else {
                                                MessageBox.show(new MessageBoxConfig() {

                                                        {
                                                            setTitle(msgs.confirm());
                                                            setMsg(msgs.alert_ondelete());
                                                            setIconCls(MessageBox.QUESTION);
                                                            setButtons(MessageBox.YESNO);
                                                            setCallback(new MessageBox.PromptCallback() {
                                                                    public void execute(
                                                                        String btnID,
                                                                        String text) {
                                                                        if (btnID.equals(
                                                                                    "yes")) {
                                                                            for (int i =
                                                                                    0;
                                                                                    i < records.length;
                                                                                    i++) {
                                                                                final String[] Univ =
                                                                                    new String[3];

                                                                                Univ[0] = records[i].getAsString(
                                                                                        "university_id");
                                                                                Univ[1] = records[i].getAsString(
                                                                                        "Special Category");

                                                                                connectService.deletefromspecialweightage(Univ,
                                                                                    new AsyncCallback<String>() {
                                                                                        public void onFailure(
                                                                                            Throwable arg0) {
                                                                                        }

                                                                                        public void onSuccess(
                                                                                            String arg0) {
                                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                                    {
                                                                                                        setTitle(msgs.alert());
                                                                                                        setMsg(msgs.alert_ondeletesuccess());
                                                                                                        setIconCls(MessageBox.INFO);
                                                                                                        setButtons(MessageBox.OK);
                                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                                public void execute(
                                                                                                                    String btnID,
                                                                                                                    String text) {
                                                                                                                    if (btnID.equals(
                                                                                                                                "ok")) {
                                                                                                                        manageButton.fireEvent(
                                                                                                                            "click");
                                                                                                                        cbSelectionModel.clearSelections();
                                                                                                                    }
                                                                                                                }
                                                                                                            });
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                    });
                                                                            }
                                                                        } else if (btnID.equals(
                                                                                    "no")) {
                                                                            cbSelectionModel.clearSelections();
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

                                //                                cbSelectionModel.addListener(new RowSelectionListener() {
                                //                                        public boolean doBeforeRowSelect(
                                //                                            RowSelectionModel sm, int rowIndex,
                                //                                            boolean keepExisting, Record record) {
                                //                                            return true;
                                //                                        }
                                //
                                //                                        public void onRowDeselect(
                                //                                            RowSelectionModel sm, int rowIndex,
                                //                                            Record record) {
                                //                                            if (sm.getCount() > 1) {
                                //                                                editButton1.setDisabled(true);
                                //                                            } else {
                                //                                                editButton1.setDisabled(false);
                                //                                            }
                                //                                        }
                                //
                                //                                        public void onRowSelect(
                                //                                            RowSelectionModel sm, int rowIndex,
                                //                                            Record record) {
                                //                                            if (sm.getCount() > 1) {
                                //                                                editButton1.setDisabled(true);
                                //                                            } else {
                                //                                                editButton1.setDisabled(false);
                                //                                            }
                                //                                        }
                                //
                                //                                        public void onSelectionChange(
                                //                                            RowSelectionModel sm) {
                                //                                            if (sm.getCount() > 1) {
                                //                                                editButton.setDisabled(true);
                                //                                            } else {
                                //                                                editButton.setDisabled(false);
                                //                                            }
                                //                                        }
                                //                                    });
                                grid.addGridCellListener(new GridCellListener() {
                                        public void onCellClick(
                                            GridPanel grid, int rowIndex,
                                            int colIndex, EventObject e) {
                                            Record[] records = cbSelectionModel.getSelections();
                                            @SuppressWarnings("unused")
                                            Record record = records[0];
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

                                grid.setTitle(constants.heading_managespecilaweightagesetup());

                                fullpage1.clear();
                                fullpage1.add(grid);
                            }
                        });
                }
            });

        fPanel.add(flexTable);

        hPanel2.add(regisTable);
        scPanel.add(hPanel2);

        hPanel3.setSpacing(8);
        hPanel3.add(fPanel);

        //        label3.setStyleName("panelHeading");
        //        vPanel.add(label3);
        vPanel.setSpacing(10);
        vPanel.add(fPanel);
        vPanel.add(fullpage1);
    }
}
