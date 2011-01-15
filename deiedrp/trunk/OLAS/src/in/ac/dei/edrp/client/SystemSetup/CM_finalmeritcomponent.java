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

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.BorderLayoutData;


public class CM_finalmeritcomponent {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    Label label3 = new Label("Dayalbagh Educational Institute");

    //    Label label4 = new Label("2010-11");
    Label componentLabel = new Label(constants.label_compnentname());
    Label marksLabel = new Label(constants.label_maxmarks());
    String componentid;
    String uniid;
    Object[][] object1;
    final VerticalPanel fullpage1 = new VerticalPanel();
    Validator validator = new Validator();
    String marks = null;
    boolean flag = false;
    Label label = new Label(constants.label_programName());
    Label branchLabel = new Label(constants.label_branchname());
    Hyperlink[] proHyperlink = new Hyperlink[550];
    Label[] branchname = new Label[550];
    String[] offeredby = new String[550];
    Label program_name = new Label(constants.label_programname());
    Label program = new Label();
    Label branch = new Label();
    String programid;
    String branchcode;
    String offered;
    String attendflag;

    public CM_finalmeritcomponent(String userid) {
        this.uniid = userid;
    }

    public void fianlmeritcomponent() {
        vPanel.clear();
        fullpage1.clear();

        final FormPanel fPanel = new FormPanel();
        final TextField componentField = new TextField();
        final NumberField marksField = new NumberField();
        final FlexTable componentTable = new FlexTable();
        final FlexTable flexTable = new FlexTable();
        HorizontalPanel hPanel = new HorizontalPanel();
        HorizontalPanel hPanel2 = new HorizontalPanel();
        HorizontalPanel hPanel3 = new HorizontalPanel();
        VerticalPanel panel = new VerticalPanel();
        final ListBox listBox = new ListBox();
        final FlexTable regisTable = new FlexTable();
        final FormPanel formPanel3 = new FormPanel();
        final FormPanel fPanel1 = new FormPanel();
        ScrollPanel scPanel3 = new ScrollPanel();
        ScrollPanel scPanel = new ScrollPanel();

        final Button saveButton = new Button(constants.saveButton());

        final CheckBox attendBox = new CheckBox(constants.checkbox_attendance());

        Label label5 = new Label(constants.label_offeredby());

        final NumberField toField = new NumberField();

        marksField.setAllowDecimals(false);
        marksField.setAllowNegative(false);
        marksField.setAllowBlank(false);
        marksField.setMaxLength(3);
        marksField.setValue(1);
        marksField.setMinValue(1);
        marksField.setMaxValue(999);

        scPanel3.setSize("100%", "365px");
        scPanel.setSize("100%", "160px");

        label.setStyleName("jack");
        branchLabel.setStyleName("jack");

        //        componentField.setEmptyText("Enter Component Name");
        componentField.setAllowBlank(false);

        toField.setAllowDecimals(false);
        toField.setAllowNegative(false);
        toField.setAllowBlank(false);
        toField.setMaxLength(3);

        fPanel.setFrame(true);
        fPanel.setSize("400px", "170px");
        fPanel.setTitle(constants.heading_componentdetails());

        fPanel.setVisible(false);

        formPanel3.setFrame(true);
        formPanel3.setSize("1100px", "400px");
        formPanel3.setTitle(constants.heading_finalmeritsetup());

        fPanel1.setFrame(true);
        fPanel1.setSize("400px", "170px");
        fPanel1.setTitle(constants.heading_programlist());

        fPanel1.setVisible(false);

        final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
        bd.setMargins(6, 6, 6, 6);

        connectService.methodentitypopulate(uniid,
            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                }

                public void onSuccess(CM_ProgramInfoGetter[] result) {
                    listBox.addItem("Select");

                    for (int i = 0; i < result.length; i++) {
                        String type = result[i].getComponent();

                        listBox.addItem(type);
                    }
                }
            });

        listBox.addChangeHandler(new ChangeHandler() {
                public void onChange(ChangeEvent arg0) {
                    final String entity = listBox.getItemText(listBox.getSelectedIndex());

                    connectService.methodgetprograms(entity, uniid,
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable arg0) {
                            }

                            public void onSuccess(
                                final CM_ProgramInfoGetter[] result) {
                                if (result.length == 0) {
                                    MessageBox.show(new MessageBoxConfig() {

                                            {
                                                setTitle(msgs.alert());
                                                setMsg(msgs.error_norecord(
                                                        entity));
                                                setIconCls(MessageBox.INFO);
                                                setButtons(MessageBox.OK);

                                                setCallback(new MessageBox.PromptCallback() {
                                                        public void execute(
                                                            String btnID,
                                                            String text) {
                                                            if (btnID.equals(
                                                                        "ok")) {
                                                                fianlmeritcomponent();
                                                            }
                                                        }
                                                    });
                                            }
                                        });
                                } else {
                                    fPanel1.setVisible(true);
                                    regisTable.clear();

                                    for (int i = 0; i < result.length; i++) {
                                        final int k = i;

                                        proHyperlink[i] = new Hyperlink(result[i].getProgram_name(),
                                                null);

                                        branchname[i] = new Label();
                                        branchname[i].setText(result[i].getBranch_name());
                                        offeredby[i] = result[i].getEntity_id();
                                        regisTable.setVisible(true);
                                        regisTable.setBorderWidth(5);

                                        regisTable.setWidget(0, 0, label);
                                        regisTable.setWidget(0, 1, branchLabel);
                                        regisTable.setWidget(i + 1, 0,
                                            proHyperlink[i]);
                                        regisTable.setWidget(i + 1, 1,
                                            branchname[i]);

                                        proHyperlink[i].addClickHandler(new ClickHandler() {
                                                @SuppressWarnings("deprecation")
                                                public void onClick(
                                                    ClickEvent arg0) {
                                                    fPanel.setVisible(true);
                                                    marksField.setValue(1);
                                                    attendBox.setChecked(false);

                                                    program.setText(proHyperlink[k].getText());
                                                    branch.setText(branchname[k].getText());

                                                    programid = result[k].getProgram_id();

                                                    branchcode = result[k].getBranch();

                                                    offered = result[k].getEntity_id();

                                                    flexTable.setWidget(0, 0,
                                                        program_name);
                                                    flexTable.setWidget(0, 2,
                                                        program);
                                                    flexTable.setWidget(0, 4,
                                                        branch);

                                                    componentTable.setWidget(0,
                                                        0, componentLabel);
                                                    componentTable.setWidget(0,
                                                        2, componentField);
                                                    componentTable.setWidget(2,
                                                        0, marksLabel);
                                                    componentTable.setWidget(2,
                                                        2, marksField);
                                                    componentTable.setWidget(4,
                                                        0, attendBox);
                                                    componentTable.setWidget(20,
                                                        0, saveButton);
                                                }
                                            });
                                    }
                                }
                            }
                        });
                }
            });

        saveButton.addListener(new ButtonListenerAdapter() {
                @SuppressWarnings("deprecation")
                public void onClick(Button button, EventObject e) {
                    flag = false;
                    attendflag = "N";

                    boolean flag1 = true;

                    int a = 0;

                    final String description;

                    description = componentField.getText();
                    marks = marksField.getRawValue();

                    System.out.println("three values=" + programid +
                        branchcode + offered);

                    if (attendBox.isChecked()) {
                        attendflag = "Y";
                    }

                    if (description.equals("")) {
                        MessageBox.show(new MessageBoxConfig() {

                                {
                                    setTitle(msgs.error());
                                    setMsg(msgs.error_componentname());
                                    setIconCls(MessageBox.ERROR);
                                    setButtons(MessageBox.OK);

                                    setCallback(new MessageBox.PromptCallback() {
                                            public void execute(String btnID,
                                                String text) {
                                                componentField.validate();
                                            }
                                        });
                                }
                            });
                        flag1 = false;
                    }

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

                    if ((flag1 == true) && (a == 0)) {
                        flag = false;
                        connectService.getdescription(programid, branchcode,
                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(
                                        "in failure of description");
                                }

                                public void onSuccess(
                                    CM_ProgramInfoGetter[] arg0) {
                                    String[] desc = new String[arg0.length];

                                    for (int i = 0; i < arg0.length; i++) {
                                        desc[i] = arg0[i].getDescription();

                                        if (desc[i].equalsIgnoreCase(
                                                    description)) {
                                            flag = true;

                                            break;
                                        }
                                    }

                                    if (flag == true) {
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
                                                                if (btnID.equals(
                                                                            "ok")) {
                                                                    //                                                                    componentField.setValue(
                                                                    //                                                                        "");
                                                                    marksField.setValue(1);
                                                                    attendBox.setChecked(false);
                                                                }
                                                            }
                                                        });
                                                }
                                            });
                                    } else {
                                        connectService.getrecords(programid,
                                            branchcode,
                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                public void onFailure(
                                                    Throwable arg0) {
                                                    MessageBox.alert(
                                                        "failuere in getRecords");
                                                }

                                                public void onSuccess(
                                                    CM_ProgramInfoGetter[] arg0) {
                                                    if (arg0.length == 0) {
                                                        componentid = "01";
                                                        MessageBox.show(new MessageBoxConfig() {

                                                                {
                                                                    setTitle(msgs.confirm());
                                                                    setMsg(msgs.alert_confirmentries());
                                                                    setIconCls(MessageBox.QUESTION);
                                                                    setButtons(MessageBox.YESNO);
                                                                    setCallback(new MessageBox.PromptCallback() {
                                                                            public void execute(
                                                                                String btnID,
                                                                                String text) {
                                                                                if (btnID.equalsIgnoreCase(
                                                                                            "yes")) {
                                                                                    connectService.methodinsertfinalmeritdetails(programid,
                                                                                        branchcode,
                                                                                        offered,
                                                                                        componentid,
                                                                                        description,
                                                                                        marks,
                                                                                        attendflag,
                                                                                        uniid,
                                                                                        new AsyncCallback<String>() {
                                                                                            public void onFailure(
                                                                                                Throwable arg0) {
                                                                                                MessageBox.alert(
                                                                                                    "Failure in insertfinal");
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
                                                                                                                        if (btnID.equals(
                                                                                                                                    "ok")) {
                                                                                                                            fianlmeritcomponent();
                                                                                                                            fPanel.setVisible(false);
                                                                                                                            //                                                                                                                            componentField.setValue(
                                                                                                                            //                                                                                                                                "");
                                                                                                                            //                                                                                                                            componentField.setEmptyText(
                                                                                                                            //                                                                                                                                "Enter Component Name");
                                                                                                                            marksField.setValue(1);
                                                                                                                            attendBox.setChecked(false);
                                                                                                                        }
                                                                                                                    }
                                                                                                                });
                                                                                                        }
                                                                                                    });
                                                                                            }
                                                                                        });
                                                                                } else if (btnID.equalsIgnoreCase(
                                                                                            "no")) {
                                                                                    fianlmeritcomponent();
                                                                                    fPanel.setVisible(false);
                                                                                    //                                                                                    componentField.setValue(
                                                                                    //                                                                                        "");
                                                                                    //                                                                                    componentField.setEmptyText(
                                                                                    //                                                                                        "Enter Component Name");
                                                                                    marksField.setValue(1);
                                                                                    attendBox.setChecked(false);
                                                                                }
                                                                            }
                                                                        });
                                                                }
                                                            });
                                                    } else {
                                                        connectService.getmaxid(programid,
                                                            branchcode,
                                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                public void onFailure(
                                                                    Throwable arg0) {
                                                                    MessageBox.alert(
                                                                        "failuere in getmaxid");
                                                                }

                                                                public void onSuccess(
                                                                    CM_ProgramInfoGetter[] arg0) {
                                                                    int value = Integer.parseInt(arg0[0].getComponent()) +
                                                                        1;

                                                                    String value1 =
                                                                        "";

                                                                    if ((value / 100) == 0) {
                                                                        value1 = ("" +
                                                                            value);

                                                                        if ((value / 10) == 0) {
                                                                            value1 = ("0" +
                                                                                value);
                                                                        }
                                                                    }

                                                                    componentid = value1;

                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(msgs.confirm());
                                                                                setMsg(msgs.alert_confirmentries());
                                                                                setIconCls(MessageBox.QUESTION);
                                                                                setButtons(MessageBox.YESNO);
                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                        public void execute(
                                                                                            String btnID,
                                                                                            String text) {
                                                                                            if (btnID.equalsIgnoreCase(
                                                                                                        "yes")) {
                                                                                                connectService.methodinsertfinalmeritdetails(programid,
                                                                                                    branchcode,
                                                                                                    offered,
                                                                                                    componentid,
                                                                                                    description,
                                                                                                    marks,
                                                                                                    attendflag,
                                                                                                    uniid,
                                                                                                    new AsyncCallback<String>() {
                                                                                                        public void onFailure(
                                                                                                            Throwable arg0) {
                                                                                                            MessageBox.alert(
                                                                                                                "Failure in insertfinal");
                                                                                                        }

                                                                                                        public void onSuccess(
                                                                                                            String arg0) {
                                                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                                                    {
                                                                                                                        setTitle(msgs.confirm());
                                                                                                                        setMsg(msgs.alert_successfulentry());
                                                                                                                        setIconCls(MessageBox.INFO);
                                                                                                                        setButtons(MessageBox.OK);
                                                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                                                public void execute(
                                                                                                                                    String btnID,
                                                                                                                                    String text) {
                                                                                                                                    if (btnID.equals(
                                                                                                                                                "ok")) {
                                                                                                                                        fianlmeritcomponent();
                                                                                                                                        fPanel.setVisible(false);
                                                                                                                                        //                                                                                                                                        componentField.setValue(
                                                                                                                                        //                                                                                                                                            "");
                                                                                                                                        marksField.setValue(1);
                                                                                                                                        attendBox.setChecked(false);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            });
                                                                                                                    }
                                                                                                                });
                                                                                                        }
                                                                                                    });
                                                                                            } else if (btnID.equalsIgnoreCase(
                                                                                                        "no")) {
                                                                                                fianlmeritcomponent();
                                                                                                fPanel.setVisible(false);
                                                                                                //                                                                                                componentField.setValue(
                                                                                                //                                                                                                    "");
                                                                                                marksField.setValue(1);
                                                                                                attendBox.setChecked(false);
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
                    }
                }
            });

        //        manageButton.addListener(new ButtonListenerAdapter() {
        //                public void onClick(Button button, EventObject e) {
        //                    connectService.getallrecords(uniid,
        //                        new AsyncCallback<List>() {
        //                            public void onFailure(Throwable arg0) {
        //                            }
        //
        //                            public void onSuccess(List result) {
        //                                final GridPanel grid = new GridPanel();
        //                                object1 = new Object[result.size()][3];
        //
        //                                final RecordDef rDef = new RecordDef(new FieldDef[] {
        //                                            new StringFieldDef("Component_id"),
        //                                            new StringFieldDef("Component Name"),
        //                                            new StringFieldDef("Total Marks"),
        //                                        });
        //
        //                                Object str = null;
        //
        //                                for (int i = 0; i < result.size(); i++) {
        //                                    for (int k = 0; k < 3; k++) {
        //                                        final HashMap hmap = (HashMap) result.get(i);
        //
        //                                        if (k == 0) {
        //                                            str = (String) hmap.get(
        //                                                    "component_id");
        //                                        } else if (k == 1) {
        //                                            str = (hmap.get("description")
        //                                                       .toString());
        //                                        } else if (k == 2) {
        //                                            str = Integer.parseInt(hmap.get(
        //                                                        "total_marks").toString());
        //                                        }
        //
        //                                        object1[i][k] = str;
        //                                    }
        //                                }
        //
        //                                Object[][] data = object1;
        //
        //                                MemoryProxy proxy = null;
        //
        //                                try {
        //                                    proxy = new MemoryProxy(data);
        //                                } catch (Exception e3) {
        //                                    System.out.println("e3" + e3);
        //                                }
        //
        //                                ArrayReader reader = new ArrayReader(rDef);
        //                                Store store = new Store(proxy, reader);
        //
        //                                store.load();
        //
        //                                grid.setStore(store);
        //
        //                                BaseColumnConfig[] columns = new BaseColumnConfig[] {
        //                                        new CheckboxColumnConfig(cbSelectionModel),
        //                                        
        //                                        new ColumnConfig("Component Name",
        //                                            "Component Name", 170, true, null,
        //                                            "entityname"),
        //                                        new ColumnConfig("Total Marks",
        //                                            "Total Marks", 80, true, null,
        //                                            "entitycode"),
        //                                    };
        //
        //                                final ColumnModel columnModel = new ColumnModel(columns);
        //                                grid.setColumnModel(columnModel);
        //
        //                                grid.setFrame(true);
        //                                grid.setStripeRows(true);
        //                                grid.setAutoExpandColumn("entityType");
        //                                grid.setAutoExpandColumn("entityname");
        //                                grid.setAutoExpandColumn("entitycode");
        //                                grid.setSelectionModel(cbSelectionModel);
        //                                grid.setAutoWidth(true);
        //                                grid.setWidth(600);
        //                                grid.setHeight(280);
        //
        //                                Toolbar topToolBar = new Toolbar();
        //                                topToolBar.addFill();
        //
        //                                ToolbarButton editButton = new ToolbarButton("Edit",
        //                                        new ButtonListenerAdapter() {
        //                                            public void onClick(
        //                                                Button editButton, EventObject e) {
        //                                                Record[] records = cbSelectionModel.getSelections();
        //                                                Record record = records[0];
        //                                                String msg = "";
        //
        //                                                if (records.length < 1) {
        //                                                    MessageBox.alert("Note",
        //                                                        "Please select atleast one Record for Editing");
        //                                                } else if (records.length > 1) {
        //                                                    MessageBox.alert("Note",
        //                                                        "Please select one record at a time for Editing");
        //                                                } else if (records.length == 1) {
        //                                                    try {
        //                                                        msg += record.getAsString(
        //                                                            "University Name");
        //
        //                                                        String[] Univ = new String[3];
        //
        //                                                        Univ[0] = record.getAsString(
        //                                                                "Component_id");
        //                                                        Univ[1] = record.getAsString(
        //                                                                "Component Name");
        //                                                        Univ[2] = record.getAsString(
        //                                                                "Total Marks");
        //
        //                                                        FlexTable editInstiTable =
        //                                                            new FlexTable();
        //
        //                                                        editInstiTable.clear();
        //
        //                                                        componentLabels.setText(Univ[0]);
        //                                                        descLabel.setText(Univ[1]);
        //                                                        toField.setValue(Univ[2]);
        //
        //                                                        editInstiTable.setWidget(8,
        //                                                            0, desLabel);
        //                                                        editInstiTable.setWidget(8,
        //                                                            8, descLabel);
        //                                                        editInstiTable.setWidget(12,
        //                                                            0, marksLabels);
        //                                                        editInstiTable.setWidget(12,
        //                                                            8, toField);
        //                                                        p1.clear();
        //                                                        p1.add(editInstiTable);
        //
        //                                                        Button b1 = new Button(
        //                                                                "Update");
        //
        //                                                        final Window window = new Window();
        //                                                        window.setTitle(
        //                                                            "Update Details");
        //                                                        window.setWidth(550);
        //                                                        window.setHeight(300);
        //                                                        window.setResizable(false);
        //                                                        window.setLayout(new BorderLayout());
        //                                                        window.setPaddings(5);
        //                                                        window.setButtonAlign(Position.CENTER);
        //                                                        window.addButton(b1);
        //
        //                                                        window.setAutoScroll(true);
        //                                                        window.add(p1, bd);
        //                                                        window.setCloseAction(Window.CLOSE);
        //                                                        window.setPlain(true);
        //                                                        window.setFrame(true);
        //                                                        window.setClosable(true);
        //                                                        window.setModal(true);
        //                                                        window.show(editButton.getId());
        //
        //                                                        b1.addListener(new ButtonListenerAdapter() {
        //                                                                public void onClick(
        //                                                                    Button button,
        //                                                                    EventObject e) {
        //                                                                    connectService.methodupdatefinalmerit(uniid,
        //                                                                        componentLabels.getText(),
        //                                                                        descLabel.getText(),
        //                                                                        toField.getValue()
        //                                                                               .toString(),
        //                                                                        new AsyncCallback<String>() {
        //                                                                            public void onFailure(
        //                                                                                Throwable arg0) {
        //                                                                            }
        //
        //                                                                            public void onSuccess(
        //                                                                                String arg0) {
        //                                                                                if ((validator.nullValidator(
        //                                                                                            descLabel.getText())) ||
        //                                                                                        (toField.getRawValue()
        //                                                                                                    .equals(""))) {
        //                                                                                    MessageBox.show(new MessageBoxConfig() {
        //
        //                                                                                            {
        //                                                                                                setTitle(
        //                                                                                                    msgs.error());
        //                                                                                                setMsg(
        //                                                                                                    "Feilds cannot be Empty");
        //                                                                                                setIconCls(MessageBox.WARNING);
        //                                                                                                setButtons(MessageBox.OK);
        //                                                                                            }
        //                                                                                        });
        //                                                                                } else {
        //                                                                                    MessageBox.show(new MessageBoxConfig() {
        //
        //                                                                                            {
        //                                                                                                setTitle(
        //                                                                                                    "Confirm");
        //                                                                                                setMsg(
        //                                                                                                    "Are you sure want to save the details ?");
        //                                                                                                setIconCls(MessageBox.QUESTION);
        //                                                                                                setButtons(MessageBox.YESNO);
        //                                                                                                setCallback(new MessageBox.PromptCallback() {
        //                                                                                                        public void execute(
        //                                                                                                            String btnID,
        //                                                                                                            String text) {
        //                                                                                                            if (btnID.equals(
        //                                                                                                                        "yes")) {
        //                                                                                                                MessageBox.show(new MessageBoxConfig() {
        //
        //                                                                                                                        {
        //                                                                                                                            setTitle(
        //                                                                                                                                "Confirm");
        //                                                                                                                            setMsg(
        //                                                                                                                                "Details Edited Sucessfully");
        //                                                                                                                            setIconCls(MessageBox.INFO);
        //                                                                                                                            setButtons(MessageBox.OK);
        //                                                                                                                            setCallback(new MessageBox.PromptCallback() {
        //                                                                                                                                    public void execute(
        //                                                                                                                                        String btnID,
        //                                                                                                                                        String text) {
        //                                                                                                                                        if (btnID.equals(
        //                                                                                                                                                    "ok")) {
        //                                                                                                                                            window.close();
        //
        //                                                                                                                                            manageButton.fireEvent(
        //                                                                                                                                                "click");
        //                                                                                                                                        }
        //                                                                                                                                    }
        //                                                                                                                                });
        //                                                                                                                        }
        //                                                                                                                    });
        //                                                                                                            } else if (btnID.equals(
        //                                                                                                                        "no")) {
        //                                                                                                                window.close();
        //                                                                                                                saveButton.fireEvent(
        //                                                                                                                    "click");
        //                                                                                                            }
        //                                                                                                        }
        //                                                                                                    });
        //                                                                                            }
        //                                                                                        });
        //                                                                                }
        //                                                                            }
        //                                                                        });
        //                                                                }
        //                                                            });
        //                                                    } catch (Exception e1) {
        //                                                        System.out.println(e1);
        //                                                    }
        //                                                }
        //                                            }
        //                                        });
        //
        //                                ToolbarButton deletebutton = new ToolbarButton("Delete",
        //                                        new ButtonListenerAdapter() {
        //                                            public void onClick(
        //                                                Button delButton, EventObject e) {
        //                                                Record[] records = cbSelectionModel.getSelections();
        //                                                Record record;
        //
        //                                                if (records.length < 1) {
        //                                                    MessageBox.alert(msgs.error(),
        //                                                        "Please select a record for deletion");
        //                                                } else if (records.length > 1) {
        //                                                    MessageBox.alert(msgs.error(),
        //                                                        "Please select only one record for deletion");
        //                                                } else {
        //                                                    for (int i = 0;
        //                                                            i < records.length;
        //                                                            i++) {
        //                                                        record = records[i];
        //
        //                                                        final String program_name =
        //                                                            record.getAsString(
        //                                                                "Component_id");
        //                                                        final String category_value =
        //                                                            record.getAsString(
        //                                                                "Component Name");
        //
        //                                                        MessageBox.show(new MessageBoxConfig() {
        //
        //                                                                {
        //                                                                    setTitle(
        //                                                                        "Confirm");
        //                                                                    setMsg(
        //                                                                        "Are you sure you want to delete these records ?");
        //                                                                    setIconCls(MessageBox.QUESTION);
        //                                                                    setButtons(MessageBox.YESNO);
        //                                                                    setCallback(new MessageBox.PromptCallback() {
        //                                                                            public void execute(
        //                                                                                String btnID,
        //                                                                                String text) {
        //                                                                                if (btnID.equals(
        //                                                                                            "yes")) {
        //                                                                                    connectService.methodDeletefromfinalmerit(program_name,
        //                                                                                        category_value,
        //                                                                                        new AsyncCallback<String>() {
        //                                                                                            public void onFailure(
        //                                                                                                Throwable arg0) {
        //                                                                                                MessageBox.alert("Failure",
        //                                                                                                    arg0.getMessage());
        //                                                                                            }
        //
        //                                                                                            public void onSuccess(
        //                                                                                                String arg0) {
        //                                                                                                MessageBox.alert("Congratulation",
        //                                                                                                    "Record Successfully deleted",
        //                                                                                                    new AlertCallback() {
        //                                                                                                        public void execute() {
        //                                                                                                            manageButton.fireEvent(
        //                                                                                                                "click");
        //                                                                                                        }
        //                                                                                                    });
        //                                                                                            }
        //                                                                                        });
        //                                                                                } else if (btnID.equals(
        //                                                                                            "no")) {
        //                                                                                    fianlmeritcomponent();
        //                                                                                }
        //                                                                            }
        //                                                                        });
        //                                                                }
        //                                                            });
        //                                                    }
        //                                                }
        //                                            }
        //                                        });
        //                                topToolBar.addButton(editButton);
        //                                topToolBar.addButton(deletebutton);
        //
        //                                grid.setTopToolbar(topToolBar);
        //
        //                                grid.addGridCellListener(new GridCellListener() {
        //                                        public void onCellClick(
        //                                            GridPanel grid, int rowIndex,
        //                                            int colIndex, EventObject e) {
        //                                            Record[] records = cbSelectionModel.getSelections();
        //                                            @SuppressWarnings("unused")
        //                                            Record record = records[0];
        //                                        }
        //
        //                                        public void onCellContextMenu(
        //                                            GridPanel grid, int rowIndex,
        //                                            int cellIndex, EventObject e) {
        //                                        }
        //
        //                                        public void onCellDblClick(
        //                                            GridPanel grid, int rowIndex,
        //                                            int colIndex, EventObject e) {
        //                                        }
        //                                    });
        //
        //                                grid.setTitle(
        //                                    "Manage Final Merit Components Details");
        //
        //                                fullpage1.clear();
        //                                fullpage1.add(grid);
        //                            }
        //                        });
        //                }
        //            });
        hPanel.add(label5);
        hPanel.setSpacing(20);
        hPanel.add(listBox);

        fPanel.add(flexTable);
        fPanel.add(componentTable);

        hPanel2.add(regisTable);
        scPanel.add(hPanel2);
        fPanel1.add(scPanel);

        panel.setSpacing(8);
        panel.add(hPanel);
        hPanel3.add(fPanel1);
        hPanel3.setSpacing(8);
        hPanel3.add(fPanel);

        formPanel3.add(panel);
        formPanel3.add(hPanel3);

        label3.setStyleName("panelHeading");
        //        label4.setStyleName("panelHeading");
        //        vPanel.add(label3);
        //        vPanel.add(label4);
        vPanel.add(formPanel3);
        vPanel.add(fullpage1);
    }
}
