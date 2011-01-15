package in.ac.dei.edrp.client.ProgramSetup;

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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;


public class CM_programcompenents {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    Label label3 = new Label("Dayalbagh Educational Institute");
    Label label = new Label(constants.label_programName());
    Label label1 = new Label(constants.label_selectcomponent());
    Label label2 = new Label(constants.label_componentweightage());
    Label label6 = new Label(constants.label_sequencenumber());
    Label program_name = new Label(constants.label_programname());
    Label genderLabel = new Label(constants.label_categorizationtype());
    Label specLabel = new Label(constants.label_rule());
    Label boardLabel = new Label("");
    Label speclLabel = new Label("");
    Label program = new Label();
    Label branch = new Label();
    Label branchLabel = new Label(constants.label_branchname());
    Hyperlink[] proHyperlink = new Hyperlink[550];
    Label[] branchname = new Label[550];
    String[] offeredby = new String[550];
    Hyperlink moreHyperlink = new Hyperlink(constants.label_newcomponent(), null);
    int j;
    int k = 0;
    String university_id;
    Object[][] object;
    String programid;
    String branchcode;
    String offered;
    Validator valid = new Validator();
    String component;
    String rule;
    String special;
    String boardflag;
    String eligibleflag;
    String weightageflag;
    String weightage;

    //Constructor for setting the Value of User ID 
    public CM_programcompenents(String Uid) {
        this.university_id = Uid;
    }

    @SuppressWarnings({"deprecation"
    })
    public void programcomponent() {
        vPanel.clear();

        VerticalPanel hPanel3 = new VerticalPanel();
        ScrollPanel scPanel3 = new ScrollPanel();
        ScrollPanel scPanel = new ScrollPanel();
        ScrollPanel scPanel1 = new ScrollPanel();
        ScrollPanel scPanel2 = new ScrollPanel();
        final FormPanel formPanel3 = new FormPanel();
        final FormPanel fPanel = new FormPanel();
        final FormPanel fPanel2 = new FormPanel();
        final FormPanel fPanel1 = new FormPanel();
        final FormPanel formPanel = new FormPanel();
        final FlexTable regisTable = new FlexTable();
        final FlexTable flexTable = new FlexTable();
        final FlexTable detailsTable = new FlexTable();
        final FlexTable defaultTable = new FlexTable();
        final FlexTable sessionTable = new FlexTable();
        final ListBox listBox = new ListBox();
        HorizontalPanel hPanel2 = new HorizontalPanel();
        HorizontalPanel hPanel = new HorizontalPanel();
        VerticalPanel panel = new VerticalPanel();
        VerticalPanel panel2 = new VerticalPanel();
        VerticalPanel panel3 = new VerticalPanel();

        final Button submitButton = new Button(constants.submit());

        final CheckBox boardButtonA = new CheckBox(constants.checkbox_board());
        final CheckBox weightButtonA = new CheckBox(constants.checknox_specialweightage());
        final CheckBox weightBox = new CheckBox(constants.checkbox_weigthage());
        final CheckBox eligibleBox = new CheckBox(constants.checkbox_eligibility());

        label.setStyleName("jack");
        branchLabel.setStyleName("jack");

        Label label5 = new Label(constants.label_offeredby());

        final NumberField box2 = new NumberField();
        final NumberField box3 = new NumberField();

        final ComboBox componentBox = new ComboBox();
        final ListBox typeBox = new ListBox();
        final ComboBox ruleBox = new ComboBox();

        componentBox.setForceSelection(true);
        componentBox.setMinChars(1);
        componentBox.setFieldLabel("State");
        componentBox.setDisplayField("state");
        componentBox.setValueField("abbr");
        componentBox.setMode(ComboBox.LOCAL);
        componentBox.setTriggerAction(ComboBox.ALL);
        componentBox.setEmptyText("Select Component");
        componentBox.setLoadingText("Searching...");
        componentBox.setTypeAhead(true);
        componentBox.setSelectOnFocus(true);
        componentBox.setWidth(198);
        componentBox.setHideTrigger(false);
        componentBox.setAllowBlank(false);

        typeBox.addItem("Select", null);
        typeBox.addItem("Marks", "M");
        typeBox.addItem("Percentage", "P");
        typeBox.addItem("Aptitude Score", "S");

        ruleBox.setForceSelection(true);
        ruleBox.setMinChars(1);
        ruleBox.setFieldLabel("State");
        ruleBox.setDisplayField("State");
        ruleBox.setValueField("rule_number");
        ruleBox.setMode(ComboBox.LOCAL);
        ruleBox.setTriggerAction(ComboBox.ALL);
        ruleBox.setEmptyText("Select Rule");
        ruleBox.setLoadingText("Searching...");
        ruleBox.setTypeAhead(true);
        ruleBox.setSelectOnFocus(true);
        ruleBox.setWidth(198);
        ruleBox.setHideTrigger(false);
        ruleBox.setAllowBlank(false);

        connectService.methodgetdescription(university_id,
            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                }

                public void onSuccess(CM_ProgramInfoGetter[] result) {
                    //                    if (result.length == 0) {
                    //                        MessageBox.show(new MessageBoxConfig() {
                    //
                    //                                {
                    //                                    setTitle(msgs.alert());
                    //                                    setMsg(msgs.alert_nocomponent());
                    //                                    setIconCls(MessageBox.INFO);
                    //                                    setButtons(MessageBox.OK);
                    //                                }
                    //                            });
                    //                    }
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("state"),
                                new StringFieldDef("abbr"),
                            });

                    object = new String[result.length][2];

                    Object[][] data = object;

                    for (int i = 0; i < result.length; i++) {
                        object[i][0] = result[i].getDescription();
                        object[i][1] = result[i].getComponent();
                    }

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    componentBox.setStore(store);
                }
            });

        connectService.methodgetrules(new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                }

                public void onSuccess(CM_ProgramInfoGetter[] result) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("State"),
                                new StringFieldDef("rule_number"),
                            });

                    object = new String[result.length][2];

                    Object[][] data = object;

                    for (int i = 0; i < result.length; i++) {
                        object[i][0] = result[i].getDescription();
                        object[i][1] = result[i].getRule_no();
                    }

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    ruleBox.setStore(store);
                }
            });

        box2.setAllowNegative(false);
        box2.setAllowBlank(false);
        box2.setMaxLength(6);
        box2.setMaxValue(100);
        box2.setMinValue(1);
        box3.setAllowDecimals(false);
        box3.setAllowNegative(false);
        box3.setMaxLength(2);
        box3.setMinValue(1);
        box3.setAllowBlank(false);
        //        box4.setAllowNegative(false);
        //        box4.setMaxLength(5);
        label2.setVisible(false);
        box2.setVisible(false);

        //        criteriaLabel.setVisible(false);
        //        box4.setVisible(false);
        scPanel.setSize("100%", "160px");

        scPanel1.setSize("100%", "150px");

        scPanel2.setSize("100%", "210px");
        scPanel3.setSize("100%", "465px");

        formPanel3.setFrame(true);
        formPanel3.setSize("1100px", "500px");
        formPanel3.setTitle(constants.heading_programcomponentsetup());

        formPanel.setFrame(true);
        formPanel.setSize("300px", "100px");
        formPanel.setTitle(constants.heading_sessiondate());

        fPanel.setFrame(true);
        fPanel.setSize("400px", "170px");
        fPanel.setTitle(constants.heading_programlist());

        fPanel1.setFrame(true);
        fPanel1.setSize("400px", "150px");
        fPanel1.setTitle(constants.heading_programlist());

        fPanel.setVisible(false);

        fPanel1.setVisible(false);

        fPanel2.setFrame(true);
        fPanel2.setSize("850px", "220px");
        fPanel2.setTitle(constants.heading_programcomponentdetails());
        fPanel2.setClosable(true);

        fPanel2.setVisible(false);

        connectService.methodentitypopulate(university_id,
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

        weightBox.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent arg0) {
                    if (weightBox.isChecked()) {
                        label2.setVisible(true);
                        box2.setVisible(true);
                    } else {
                        label2.setVisible(false);
                        box2.setVisible(false);
                    }
                }
            });

        listBox.addChangeHandler(new ChangeHandler() {
                public void onChange(ChangeEvent arg0) {
                    final String entity = listBox.getItemText(listBox.getSelectedIndex());
                    fPanel.setVisible(false);
                    fPanel1.setVisible(false);
                    fPanel2.setVisible(false);
                    box2.setValue(1);
                    box3.setValue(1);

                    connectService.methodgetprograms(entity, university_id,
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
                                                                programcomponent();
                                                            }
                                                        }
                                                    });
                                            }
                                        });
                                } else {
                                    fPanel.setVisible(true);
                                    fPanel1.setVisible(false);
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
                                                public void onClick(
                                                    ClickEvent arg0) {
                                                    fPanel2.setVisible(true);

                                                    try {
                                                        componentBox.clearValue();
                                                    } catch (Exception e) {
                                                        System.out.println(
                                                            "exception in componentbox =" +
                                                            e);
                                                    }

                                                    try {
                                                        ruleBox.clearValue();
                                                    } catch (Exception e1) {
                                                        System.out.println(
                                                            "e1 =" + e1);
                                                    }

                                                    typeBox.setSelectedIndex(0);
                                                    //                                                    box2.setValue("");
                                                    //                                                    box3.setValue("");
                                                    eligibleBox.setChecked(false);
                                                    weightBox.setChecked(false);
                                                    weightButtonA.setChecked(false);
                                                    boardButtonA.setChecked(false);
                                                    label2.setVisible(false);
                                                    box2.setVisible(false);

                                                    //                                                    box4.setValue("");
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

                                                    detailsTable.setWidget(2,
                                                        0, label1);
                                                    detailsTable.setWidget(2,
                                                        2, componentBox);
                                                    detailsTable.setWidget(2,
                                                        4, genderLabel);
                                                    detailsTable.setWidget(2,
                                                        6, typeBox);
                                                    detailsTable.setWidget(4,
                                                        0, specLabel);
                                                    detailsTable.setWidget(4,
                                                        2, ruleBox);
                                                    detailsTable.setWidget(4,
                                                        4, label6);
                                                    detailsTable.setWidget(4,
                                                        6, box3);
                                                    detailsTable.setWidget(6,
                                                        0, weightButtonA);

                                                    detailsTable.setWidget(6,
                                                        4, boardButtonA);
                                                    detailsTable.setWidget(8,
                                                        0, weightBox);
                                                    detailsTable.setWidget(8,
                                                        4, eligibleBox);
                                                    detailsTable.setWidget(12,
                                                        0, label2);
                                                    detailsTable.setWidget(12,
                                                        2, box2);

                                                    detailsTable.setWidget(20,
                                                        0, submitButton);

                                                    //                                                    detailsTable.setWidget(20,
                                                    //                                                        2, moreHyperlink);
                                                }
                                            });
                                    }
                                }
                            }
                        });

                    componentBox.addListener(new ComboBoxListenerAdapter() {
                            public void onSelect(ComboBox comboBox,
                                Record record, int index) {

                                int x = 0;
                                connectService.getcomponent(programid, x,
                                    branchcode,
                                    new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                        public void onFailure(Throwable arg0) {
                                        }

                                        public void onSuccess(
                                            CM_ProgramInfoGetter[] result) {
                                            component = componentBox.getValue();

                                            for (int i = 0; i < result.length;
                                                    i++) {
                                                String code = result[i].getComponent_id();

                                                if (code.equals(component)) {
                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setTitle(msgs.error());
                                                                setMsg(msgs.error_duplicatecomponent());
                                                                setIconCls(MessageBox.ERROR);
                                                                setButtons(MessageBox.OK);
                                                                setCallback(new MessageBox.PromptCallback() {
                                                                        public void execute(
                                                                            String btnID,
                                                                            String text) {
                                                                            componentBox.clearValue();
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

                    submitButton.addClickHandler(new ClickHandler() {
                            public void onClick(ClickEvent arg0) {
                                final String rawvalue;
                                final String compo = componentBox.getValue();

                                special = "N";
                                boardflag = "N";
                                eligibleflag = "N";
                                weightageflag = "N";
                                weightage = "0";

                                rule = ruleBox.getValue();

                                final String type = typeBox.getValue(typeBox.getSelectedIndex());
                                final String sequence = box3.getRawValue();

                                boolean action = true;

                                //                                try {
                                if (weightBox.isChecked()) {
                                    weightageflag = "Y";
                                    weightage = box2.getRawValue();
                                }

                                if (weightageflag.equalsIgnoreCase("n")) {
                                    weightage = "1";
                                    box2.setValue(1);
                                }

                                if (boardButtonA.isChecked()) {
                                    boardflag = "Y";
                                }

                                if (weightButtonA.isChecked()) {
                                    special = "Y";
                                }

                                if (eligibleBox.isChecked()) {
                                    eligibleflag = "Y";
                                }

                                try {
                                    componentBox.validate();
                                } catch (Exception e) {
                                    action = false;
                                }

                                try {
                                    ruleBox.validate();
                                } catch (Exception e) {
                                    action = false;
                                }

                                if (action == false) {
                                    MessageBox.show(new MessageBoxConfig() {

                                            {
                                                setTitle(msgs.error());
                                                setMsg(msgs.checkFields());
                                                setIconCls(MessageBox.ERROR);
                                                setButtons(MessageBox.OK);
                                            }
                                        });
                                }

                                if ((compo.equalsIgnoreCase("PG")) ||
                                        (compo.equalsIgnoreCase("P1")) ||
                                        (compo.equalsIgnoreCase("P2")) ||
                                        (compo.equalsIgnoreCase("P3")) ||
                                        (compo.equalsIgnoreCase("P4")) ||
                                        (compo.equalsIgnoreCase("P5")) ||
                                        (compo.equalsIgnoreCase("P6")) ||
                                        (compo.equalsIgnoreCase("P7")) ||
                                        (compo.equalsIgnoreCase("P8")) ||
                                        (compo.equalsIgnoreCase("P9"))) {
                                    rawvalue = "PG";
                                } else if ((compo.equalsIgnoreCase("UG")) ||
                                        ((compo.equalsIgnoreCase("U1")) |
                                        (compo.equalsIgnoreCase("U2")) |
                                        (compo.equalsIgnoreCase("U3")) |
                                        (compo.equalsIgnoreCase("U4")) |
                                        (compo.equalsIgnoreCase("U5")) |
                                        (compo.equalsIgnoreCase("U6")) |
                                        (compo.equalsIgnoreCase("U7")) |
                                        (compo.equalsIgnoreCase("U8")) |
                                        (compo.equalsIgnoreCase("U9")))) {
                                    rawvalue = "UG";
                                } else {
                                    rawvalue = "XX";
                                }

                                if ((valid.nullValidator(compo)) ||
                                        (valid.nullValidator(rule)) ||
                                        (valid.nullValidator(type))) {
                                    MessageBox.show(new MessageBoxConfig() {

                                            {
                                                setTitle(msgs.error());
                                                setMsg(msgs.error_category());
                                                setIconCls(MessageBox.ERROR);
                                                setButtons(MessageBox.OK);
                                            }
                                        });
                                } else if ((weightBox.isChecked()) &&
                                        (weightage.isEmpty())) {
                                    MessageBox.show(new MessageBoxConfig() {

                                            {
                                                setTitle(msgs.error());
                                                setMsg(msgs.error_weightagevalue());
                                                setIconCls(MessageBox.ERROR);
                                                setButtons(MessageBox.OK);
                                            }
                                        });
                                } else {
                                    int a = 0;
                                    int b = 0;

                                    try {
                                        box3.validate();
                                    } catch (Exception e) {
                                        a = 1;
                                    }

                                    try {
                                        box2.validate();
                                    } catch (Exception e) {
                                        b = 1;
                                    }

                                    if ((a == 1) || (b == 1) ||
                                            (action == false)) {
                                        MessageBox.show(new MessageBoxConfig() {

                                                {
                                                    setTitle(msgs.error());
                                                    setMsg(msgs.checkFields());
                                                    setIconCls(MessageBox.ERROR);
                                                    setButtons(MessageBox.OK);
                                                }
                                            });
                                    } else {
                                        connectService.getsequencenumbers(programid,
                                            branchcode,
                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                public void onFailure(
                                                    Throwable arg0) {
                                                }

                                                public void onSuccess(
                                                    CM_ProgramInfoGetter[] result) {
                                                    String[] sequenceno = new String[result.length];

                                                    boolean flag = true;

                                                    for (int i = 0;
                                                            i < result.length;
                                                            i++) {
                                                        sequenceno[i] = result[i].getSequence();

                                                        if (sequenceno[i].equals(
                                                                    sequence)) {
                                                            flag = false;

                                                            break;
                                                        }
                                                    }

                                                    if (flag == false) {
                                                        MessageBox.show(new MessageBoxConfig() {

                                                                {
                                                                    setTitle(msgs.error());
                                                                    setMsg(msgs.error_duplicatesequencenumber());
                                                                    setIconCls(MessageBox.ERROR);
                                                                    setButtons(MessageBox.OK);
                                                                    setCallback(new MessageBox.PromptCallback() {
                                                                            public void execute(
                                                                                String btnID,
                                                                                String text) {
                                                                                box3.setValue(
                                                                                    "");
                                                                            }
                                                                        });
                                                                }
                                                            });
                                                    } else {
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
                                                                                if (btnID.equals(
                                                                                            "yes")) {
                                                                                    connectService.insertcomponentdetails(programid,
                                                                                        branchcode,
                                                                                        offered,
                                                                                        compo,
                                                                                        type,
                                                                                        weightage,
                                                                                        sequence,
                                                                                        rule,
                                                                                        rawvalue,
                                                                                        special,
                                                                                        boardflag,
                                                                                        weightageflag,
                                                                                        eligibleflag,
                                                                                        university_id,
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
                                                                                                                        MessageBox.show(new MessageBoxConfig() {

                                                                                                                                {
                                                                                                                                    setTitle(msgs.confirm());
                                                                                                                                    setMsg(msgs.alert_sameprogram());
                                                                                                                                    setIconCls(MessageBox.QUESTION);
                                                                                                                                    setButtons(MessageBox.YESNO);
                                                                                                                                    setCallback(new MessageBox.PromptCallback() {
                                                                                                                                            public void execute(
                                                                                                                                                String btnID,
                                                                                                                                                String text) {
                                                                                                                                                if (btnID.equals(
                                                                                                                                                            "yes")) {
                                                                                                                                                    componentBox.clearValue();
                                                                                                                                                    ruleBox.clearValue();
                                                                                                                                                    typeBox.setSelectedIndex(0);
                                                                                                                                                    box2.setValue(1);
                                                                                                                                                    box3.setValue(1);
                                                                                                                                                    eligibleBox.setChecked(false);
                                                                                                                                                    weightBox.setChecked(false);
                                                                                                                                                    weightButtonA.setChecked(false);
                                                                                                                                                    boardButtonA.setChecked(false);
                                                                                                                                                    label2.setVisible(false);
                                                                                                                                                    box2.setVisible(false);
                                                                                                                                                } else if (btnID.equals(
                                                                                                                                                            "no")) {
                                                                                                                                                    fPanel2.setVisible(false);
                                                                                                                                                    componentBox.clearValue();
                                                                                                                                                    ruleBox.clearValue();
                                                                                                                                                    typeBox.setSelectedIndex(0);
                                                                                                                                                    box2.setValue(1);
                                                                                                                                                    box3.setValue(1);
                                                                                                                                                    eligibleBox.setChecked(false);
                                                                                                                                                    weightBox.setChecked(false);
                                                                                                                                                    weightButtonA.setChecked(false);
                                                                                                                                                    boardButtonA.setChecked(false);
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        });
                                                                                                                                }
                                                                                                                            });
                                                                                                                    }
                                                                                                                });
                                                                                                        }
                                                                                                    });
                                                                                            }
                                                                                        });
                                                                                } else if (btnID.equals(
                                                                                            "no")) {
                                                                                    componentBox.clearValue();
                                                                                    ruleBox.clearValue();
                                                                                    typeBox.setSelectedIndex(0);
                                                                                    box2.setValue(1);
                                                                                    box3.setValue(1);
                                                                                    eligibleBox.setChecked(false);
                                                                                    weightBox.setChecked(false);
                                                                                    weightButtonA.setChecked(false);
                                                                                    boardButtonA.setChecked(false);
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

                                //                                } catch (Exception e) {
                                //                                    MessageBox.show(new MessageBoxConfig() {
                                //
                                //                                            {
                                //                                                setTitle(msgs.error());
                                //                                                setMsg(
                                //                                                    "Mandatory Fields are Empty");
                                //                                                setIconCls(MessageBox.ERROR);
                                //                                                setButtons(MessageBox.OK);
                                //                                            }
                                //                                        });
                                //                                }
                            }
                        });
                    moreHyperlink.addClickHandler(new ClickHandler() {
                            public void onClick(ClickEvent arg0) {
                                Label label = new Label("Component Name*");
                                Label typeLabel = new Label("Component Type*");
                                final TextField compoField = new TextField();
                                final ListBox ugpgBox = new ListBox();
                                Button saveButton = new Button("Submit");

                                ugpgBox.addItem("Select", null);
                                ugpgBox.addItem("under graduate", "UG");
                                ugpgBox.addItem("post graduate", "PG");

                                compoField.setEmptyText(
                                    "U_-UG,P_-PG 2 characters only");
                                compoField.setMinLength(2);
                                compoField.setMaxLength(2);

                                detailsTable.setWidget(2, 0, label);
                                detailsTable.setWidget(2, 2, compoField);
                                detailsTable.setWidget(2, 4, typeLabel);
                                detailsTable.setWidget(2, 6, ugpgBox);
                                detailsTable.setWidget(4, 0, genderLabel);
                                detailsTable.setWidget(4, 2, typeBox);
                                detailsTable.setWidget(4, 4, specLabel);
                                detailsTable.setWidget(4, 6, ruleBox);
                                detailsTable.setWidget(6, 0, label6);
                                detailsTable.setWidget(6, 2, box3);
                                detailsTable.setWidget(10, 0, boardButtonA);
                                detailsTable.setWidget(10, 4, weightButtonA);
                                detailsTable.setWidget(12, 0, eligibleBox);
                                detailsTable.setWidget(14, 0, weightBox);
                                detailsTable.setWidget(16, 0, label2);
                                detailsTable.setWidget(16, 2, box2);
                                detailsTable.setWidget(20, 0, saveButton);
                                //                                detailsTable.setWidget(20, 2, moreHyperlink);
                                saveButton.addClickHandler(new ClickHandler() {
                                        public void onClick(ClickEvent arg0) {
                                            int x = 0;

                                            final String newtext = compoField.getText();
                                            final String newtype = ugpgBox.getValue(ugpgBox.getSelectedIndex());
                                            final String rule = ruleBox.getValue();
                                            final String type = typeBox.getValue(typeBox.getSelectedIndex());
                                            final String sequence = box3.getRawValue();

                                            special = "N";
                                            boardflag = "N";
                                            eligibleflag = "N";
                                            weightageflag = "N";
                                            weightage = "0";

                                            if (weightBox.isChecked()) {
                                                weightageflag = "Y";
                                                weightage = box2.getRawValue();
                                            }

                                            if (boardButtonA.isChecked()) {
                                                boardflag = "Y";
                                            }

                                            if (weightButtonA.isChecked()) {
                                                special = "Y";
                                            }

                                            if (eligibleBox.isChecked()) {
                                                eligibleflag = "Y";
                                            }

                                            try {
                                                if ((!newtext.equals(null))) {
                                                    connectService.getcomponent(programid,
                                                        x, branchcode,
                                                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                            public void onFailure(
                                                                Throwable arg0) {
                                                            }

                                                            public void onSuccess(
                                                                CM_ProgramInfoGetter[] result) {
                                                                String[] code = new String[result.length];

                                                                boolean flag = true;

                                                                for (int i = 0;
                                                                        i < result.length;
                                                                        i++) {
                                                                    code[i] = result[i].getComponent_id();

                                                                    if (code[i].equals(
                                                                                newtext)) {
                                                                        flag = false;

                                                                        break;
                                                                    }
                                                                }

                                                                if (flag == false) {
                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(msgs.error());
                                                                                setMsg(msgs.error_duplicatecomponent());
                                                                                setIconCls(MessageBox.ERROR);
                                                                                setButtons(MessageBox.OK);
                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                        public void execute(
                                                                                            String btnID,
                                                                                            String text) {
                                                                                            compoField.setValue(
                                                                                                "");
                                                                                            compoField.setEmptyText(
                                                                                                "U_-UG,P_-PG 2 characters only");
                                                                                        }
                                                                                    });
                                                                            }
                                                                        });
                                                                }
                                                            }
                                                        });
                                                }

                                                int a = 0;
                                                int b = 0;

                                                try {
                                                    box3.validate();
                                                } catch (Exception e) {
                                                    a = 1;
                                                }

                                                try {
                                                    box2.validate();
                                                } catch (Exception e) {
                                                    b = 1;
                                                }

                                                if ((weightBox.isChecked()) &&
                                                        (weightage.isEmpty())) {
                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setTitle(msgs.error());
                                                                setMsg(msgs.error_weightagevalue());
                                                                setIconCls(MessageBox.ERROR);
                                                                setButtons(MessageBox.OK);
                                                            }
                                                        });
                                                } else if ((a == 1) ||
                                                        (b == 1)) {
                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setTitle(msgs.error());
                                                                setMsg(msgs.checkFields());
                                                                setIconCls(MessageBox.ERROR);
                                                                setButtons(MessageBox.OK);
                                                            }
                                                        });
                                                } else if ((valid.nullValidator(
                                                            newtext)) ||
                                                        (valid.nullValidator(
                                                            type)) ||
                                                        (valid.nullValidator(
                                                            sequence)) ||
                                                        (valid.nullValidator(
                                                            rule)) ||
                                                        (valid.nullValidator(
                                                            newtype))) {
                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setTitle(msgs.error());
                                                                setMsg(msgs.error_mandatoryfields());
                                                                setIconCls(MessageBox.ERROR);
                                                                setButtons(MessageBox.OK);
                                                                setCallback(new MessageBox.PromptCallback() {
                                                                        public void execute(
                                                                            String btnID,
                                                                            String text) {
                                                                        }
                                                                    });
                                                            }
                                                        });
                                                } else if ((newtype.equalsIgnoreCase(
                                                            "UG")) &&
                                                        (!newtext.startsWith(
                                                            "U"))) {
                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setTitle(msgs.error());
                                                                setMsg(
                                                                    "Component name should start with U");
                                                                setIconCls(MessageBox.WARNING);
                                                                setButtons(MessageBox.OK);
                                                                setCallback(new MessageBox.PromptCallback() {
                                                                        public void execute(
                                                                            String btnID,
                                                                            String text) {
                                                                            compoField.setValue(
                                                                                "");
                                                                            compoField.setEmptyText(
                                                                                "U_-UG,P_-PG 2 characters only");
                                                                        }
                                                                    });
                                                            }
                                                        });
                                                } else if ((newtype.equalsIgnoreCase(
                                                            "PG")) &&
                                                        (!newtext.startsWith(
                                                            "P"))) {
                                                    MessageBox.show(new MessageBoxConfig() {

                                                            {
                                                                setTitle(
                                                                    "Warning");
                                                                setMsg(
                                                                    "Component name should start with P");
                                                                setIconCls(MessageBox.WARNING);
                                                                setButtons(MessageBox.OK);
                                                                setCallback(new MessageBox.PromptCallback() {
                                                                        public void execute(
                                                                            String btnID,
                                                                            String text) {
                                                                            compoField.setValue(
                                                                                "");
                                                                            compoField.setEmptyText(
                                                                                "U_-UG,P_-PG 2 characters only");
                                                                        }
                                                                    });
                                                            }
                                                        });
                                                } else {
                                                    connectService.getsequencenumbers(programid,
                                                        branchcode,
                                                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                            public void onFailure(
                                                                Throwable arg0) {
                                                            }

                                                            public void onSuccess(
                                                                CM_ProgramInfoGetter[] result) {
                                                                String[] sequenceno =
                                                                    new String[result.length];

                                                                boolean flag = true;

                                                                for (int i = 0;
                                                                        i < result.length;
                                                                        i++) {
                                                                    sequenceno[i] = result[i].getSequence();
                                                                    

                                                                    if (sequenceno[i].equals(
                                                                                sequence)) {
                                                                        flag = false;

                                                                        break;
                                                                    }
                                                                }

                                                                if (flag == false) {
                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(
                                                                                    "Warning");
                                                                                setMsg(
                                                                                    "Sequence number exists for this program");
                                                                                setIconCls(MessageBox.WARNING);
                                                                                setButtons(MessageBox.OK);
                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                        public void execute(
                                                                                            String btnID,
                                                                                            String text) {
                                                                                            box3.setValue(1);
                                                                                        }
                                                                                    });
                                                                            }
                                                                        });
                                                                } else {
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
                                                                                            if (btnID.equals(
                                                                                                        "yes")) {
                                                                                                connectService.insertcomponentdetails(programid,
                                                                                                    branchcode,
                                                                                                    offered,
                                                                                                    newtext,
                                                                                                    type,
                                                                                                    weightage,
                                                                                                    sequence,
                                                                                                    rule,
                                                                                                    newtype,
                                                                                                    special,
                                                                                                    boardflag,
                                                                                                    weightageflag,
                                                                                                    eligibleflag,
                                                                                                    university_id,
                                                                                                    new AsyncCallback<String>() {
                                                                                                        public void onFailure(
                                                                                                            Throwable arg0) {
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
                                                                                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                                                                                            {
                                                                                                                                                setTitle(msgs.confirm());
                                                                                                                                                setMsg(msgs.alert_successfulentry());
                                                                                                                                                setIconCls(MessageBox.QUESTION);
                                                                                                                                                setButtons(MessageBox.YESNO);
                                                                                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                                                                                        public void execute(
                                                                                                                                                            String btnID,
                                                                                                                                                            String text) {
                                                                                                                                                            if (btnID.equals(
                                                                                                                                                                        "yes")) {
                                                                                                                                                                compoField.setValue(
                                                                                                                                                                    "");
                                                                                                                                                                compoField.setEmptyText(
                                                                                                                                                                    "U_-UG,P_-PG 2 characters only");
                                                                                                                                                                ugpgBox.setSelectedIndex(0);
                                                                                                                                                                componentBox.clearValue();
                                                                                                                                                                ruleBox.reset();
                                                                                                                                                                typeBox.setSelectedIndex(0);
                                                                                                                                                                box2.setValue(
                                                                                                                                                                    "");
                                                                                                                                                                box3.setValue(
                                                                                                                                                                    "");
                                                                                                                                                                eligibleBox.setChecked(false);
                                                                                                                                                                weightBox.setChecked(false);
                                                                                                                                                                weightButtonA.setChecked(false);
                                                                                                                                                                boardButtonA.setChecked(false);

                                                                                                                                                                label2.setVisible(false);
                                                                                                                                                                box2.setVisible(false);
                                                                                                                                                            } else if (btnID.equals(
                                                                                                                                                                        "no")) {
                                                                                                                                                                fPanel2.setVisible(false);

                                                                                                                                                                //                                                                                                                                                                componentBox.reset();
                                                                                                                                                                //                                                                                                                                                                ruleBox.reset();
                                                                                                                                                                //                                                                                                                                                                typeBox.setSelectedIndex(0);
                                                                                                                                                                //                                                                                                                                                                box2.setValue(
                                                                                                                                                                //                                                                                                                                                                    "");
                                                                                                                                                                //                                                                                                                                                                box3.setValue(
                                                                                                                                                                //                                                                                                                                                                    "");
                                                                                                                                                                //                                                                                                                                                                
                                                                                                                                                                //                                                                                                                                                                eligibleBox.setChecked(false);
                                                                                                                                                                //                                                                                                                                                                weightBox.setChecked(false);
                                                                                                                                                                //                                                                                                                                                                weightButtonA.setChecked(false);
                                                                                                                                                                //                                                                                                                                                                boardButtonA.setChecked(false);

                                                                                                                                                                //                                                                                                                                                                box4.setValue(
                                                                                                                                                                //                                                                                                                                                                    "");
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    });
                                                                                                                                            }
                                                                                                                                        });
                                                                                                                                }
                                                                                                                            });
                                                                                                                    }
                                                                                                                });
                                                                                                        }
                                                                                                    });
                                                                                            } else if (btnID.equals(
                                                                                                        "no")) {
                                                                                                compoField.setValue(
                                                                                                    "");
                                                                                                compoField.setEmptyText(
                                                                                                    "U_-UG,P_-PG 2 characters only");
                                                                                                ugpgBox.setSelectedIndex(0);
                                                                                                componentBox.clearValue();
                                                                                                ruleBox.clearValue();
                                                                                                typeBox.setSelectedIndex(0);
                                                                                                box2.setValue(1);
                                                                                                box3.setValue(1);
                                                                                                eligibleBox.setChecked(false);
                                                                                                weightBox.setChecked(false);
                                                                                                weightButtonA.setChecked(false);
                                                                                                boardButtonA.setChecked(false);

                                                                                                label2.setVisible(false);
                                                                                                box2.setVisible(false);

                                                                                                //                                                                                                box4.setValue(
                                                                                                //                                                                                                    "");
                                                                                            }
                                                                                        }
                                                                                    });
                                                                            }
                                                                        });
                                                                }
                                                            }
                                                        });
                                                }
                                            } catch (Exception e) {
                                                MessageBox.show(new MessageBoxConfig() {

                                                        {
                                                            setTitle(msgs.error());
                                                            setMsg(msgs.error_mandatoryfields());
                                                            setIconCls(MessageBox.ERROR);
                                                            setButtons(MessageBox.OK);
                                                        }
                                                    });
                                            }
                                        }
                                    });
                            }
                        });
                }
            });

        panel3.add(flexTable);
        panel3.add(detailsTable);

        scPanel2.add(panel3);

        fPanel2.add(scPanel2);

        hPanel.add(label5);
        hPanel.setSpacing(20);
        hPanel.add(listBox);

        formPanel.add(sessionTable);

        panel2.add(defaultTable);
        panel2.setSpacing(10);
        panel2.add(formPanel);
        scPanel1.add(panel2);

        fPanel1.add(scPanel1);

        hPanel2.add(regisTable);
        scPanel.add(hPanel2);
        fPanel.add(scPanel);

        panel.add(hPanel);
        //        panel.add(hPanel4);
        panel.add(fPanel);
        panel.add(fPanel1);

        hPanel3.add(panel);
        hPanel3.setSpacing(8);
        hPanel3.add(fPanel2);
        scPanel3.add(hPanel3);

        formPanel3.add(scPanel3);

        label3.setStyleName("panelHeading");

        //        vPanel.add(label3);
        vPanel.add(formPanel3);
    }
}
