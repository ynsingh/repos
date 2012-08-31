package in.ac.dei.edrp.client.ProgramSetup;

import java.util.ArrayList;
import java.util.List;

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
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;


public class CM_programcompenents {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    
    Label label = new Label(constants.label_programName()+" ");
    Label label1 = new Label(constants.label_selectcomponent());
    Label label2 = new Label(constants.label_componentweightage());
    Label label6 = new Label(constants.label_sequencenumber());
    Label program_name = new Label(constants.label_programname());
    Label genderLabel = new Label(constants.label_categorizationtype());
    Label specLabel = new Label(constants.label_rule());
    Label boardLabel = new Label("");
    Label speclLabel = new Label("");
    Label program = new Label();
    Label branchLabel = new Label(constants.label_branchname());
    Hyperlink[] proHyperlink = new Hyperlink[5550];
    
    String[] offeredby = new String[5550];
    
    Label[] entityName = new Label[5550];
    Hyperlink moreHyperlink = new Hyperlink(constants.label_newcomponent(), null);
    int j;
    int k = 0;
    String university_id;
    Object[][] object;
    String programid;
    
    String offered;
    Validator valid = new Validator();
    String component;
    String rule;
    String special;
    String boardflag;
    String eligibleflag;
    String weightageflag;
    String weightage;
    List<CM_ProgramInfoGetter>typeList;

    //Constructor for setting the Value of User ID 
    public CM_programcompenents(String Uid) {
        this.university_id = Uid;
    }

    @SuppressWarnings({"deprecation"
    })
    public void programcomponent() {
        vPanel.clear();

        final VerticalPanel hPanel3 = new VerticalPanel();
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
        final ListBox listBox2 = new ListBox();
        HorizontalPanel hPanel2 = new HorizontalPanel();
        FlexTable flexTable2 = new FlexTable();
        final VerticalPanel panel = new VerticalPanel();
        final VerticalPanel verticalPanel2 = new VerticalPanel();
        VerticalPanel panel2 = new VerticalPanel();
        VerticalPanel panel3 = new VerticalPanel();

        final Button button = new Button(constants.okButton());

        final Button submitButton = new Button(constants.submit());

        final CheckBox boardButtonA = new CheckBox(constants.checkbox_board());
        final CheckBox weightButtonA = new CheckBox(constants.checknox_specialweightage());
        final CheckBox weightBox = new CheckBox(constants.checkbox_weigthage());
        final CheckBox eligibleBox = new CheckBox(constants.checkbox_eligibility());

        label.setStyleName("jack");
    
        Label label5 = new Label(constants.label_entitytype());
        final Label label10 = new Label(constants.entityName()+"*");

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

      //Added By Upasana
        /**
         * university_id
         * return result List of CM_ProgramInfoGetter
         */
        connectService.getCategorizationType(university_id,new AsyncCallback<List<CM_ProgramInfoGetter>>() {
			public void onFailure(Throwable arg0) {
				MessageBox.alert(arg0.toString());
			}
			public void onSuccess(List<CM_ProgramInfoGetter> result) {
				typeBox.clear();
				typeList=new ArrayList<CM_ProgramInfoGetter>();
				typeList=result;
				for (int i = 0; i < result.size(); i++) {
                    typeBox.addItem(result.get(i).getCatTypeDescription(), result.get(i).getCatTypeId());                    
                }
			}
		});

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
        label2.setVisible(false);
        box2.setVisible(false);

        button.setVisible(false);

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
        fPanel.setSize("700px", "170px");
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
                        String type = result[i].getComponentDescription();
                        String id=result[i].getComponentId();

                        listBox.addItem(type,id);
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
                    final String entity = listBox.getValue(listBox.getSelectedIndex());

                    if (listBox.getValue(listBox.getSelectedIndex())
                                   .equalsIgnoreCase("select")) {
                        programcomponent();
                    }

                    button.setVisible(false);

                    fPanel.setVisible(false);
                    fPanel1.setVisible(false);
                    fPanel2.setVisible(false);

                    connectService.methodgetentitytype(entity, university_id,
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable arg0) {
                            }

                            public void onSuccess(CM_ProgramInfoGetter[] result) {
                                String type1 = "";
                                String entity_name="";
                                
                                listBox2.clear();
                                listBox2.addItem("Select", null);

                                for (int i = 0; i < result.length; i++) {
                                    type1 = result[i].getEntity_id();
                                    entity_name = result[i].getEntity_name();
                                    listBox2.addItem(entity_name,type1);
                                }
                            }
                        });
                }
            });

        listBox2.addChangeHandler(new ChangeHandler() {
                public void onChange(ChangeEvent arg0) {
                    if (listBox2.getSelectedIndex()==0) {
                        button.setVisible(false);
                    } else {
                        button.setVisible(true);
                    }
                        fPanel.setVisible(false);
                        fPanel1.setVisible(false);
                        fPanel2.setVisible(false);
                }
            });

        button.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    final String entitytype = listBox.getValue(listBox.getSelectedIndex());
                    final String entityname = listBox2.getValue(listBox2.getSelectedIndex());

                    connectService.methodgetprograms(entitytype, university_id,
                        entityname,
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
                                                        entityname));
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

                                        offeredby[i] = result[i].getEntity_id();
                                        regisTable.setVisible(true);
                                        regisTable.setBorderWidth(5);
              
                                        regisTable.setWidget(0, 1, label);
                                        regisTable.setWidget(i + 1, 1,
                                            proHyperlink[i]);
              
                                        proHyperlink[i].addClickHandler(new ClickHandler() {
                                                public void onClick(
                                                    ClickEvent arg0) {
                                                    fPanel2.setVisible(true);

                                                    try {
                                                        componentBox.clearValue();
                                                    } catch (Exception e) {
                                                        System.out.println("exception in componentbox =" +e);
                                                    }

                                                    try {
                                                        ruleBox.clearValue();
                                                    } catch (Exception e1) {
                                                        System.out.println("e1 =" + e1);
                                                    }

                                                    typeBox.setSelectedIndex(0);

                                                    eligibleBox.setChecked(false);
                                                    weightBox.setChecked(false);
                                                    weightButtonA.setChecked(false);
                                                    boardButtonA.setChecked(false);
                                                    label2.setVisible(false);
                                                    box2.setVisible(false);

                                                    //                                                    box4.setValue("");
                                                    program.setText(proHyperlink[k].getText());
                                                    /*branch.setText(branchname[k].getText());
                                                    specialName.setText("(" +
                                                        specializationName[k].getText() +
                                                        ")");*/

                                                    programid = result[k].getProgram_id();

                                                    //branchcode = result[k].getBranch_id();

                                                    offered = result[k].getEntity_id();

                                                   // specializationCode = result[k].getSpecialization_id();

                                                    flexTable.setWidget(0, 0,
                                                        program_name);
                                                    flexTable.setWidget(0, 2,
                                                        program);
                                                    /*flexTable.setWidget(0, 4,
                                                        branch);
                                                    flexTable.setWidget(0, 6,
                                                        specialName);*/

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
                }
            });

        componentBox.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    int x = 0;
                    connectService.getcomponent(programid, x,
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable arg0) {
                            }

                            public void onSuccess(CM_ProgramInfoGetter[] result) {
                                component = componentBox.getValue();

                                for (int i = 0; i < result.length; i++) {
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

        submitButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
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
                    } catch (Exception e1) {
                        action = false;
                    }

                    try {
                        ruleBox.validate();
                    } catch (Exception e1) {
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
                        } catch (Exception e1) {
                            a = 1;
                        }

                        try {
                            box2.validate();
                        } catch (Exception e1) {
                            b = 1;
                        }

                        if ((a == 1) || (b == 1) || (action == false)) {
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
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        String[] sequenceno = new String[result.length];

                                        boolean flag = true;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            sequenceno[i] = result[i].getSequence();

                                            if (sequenceno[i].equals(sequence)) {
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
                                                                        connectService.insertcomponentdetails(programid,offered,compo,type,weightage,
                                                                            sequence,rule,rawvalue,special,boardflag,weightageflag,eligibleflag,university_id,
                                                                            new AsyncCallback<String>() {
                                                                                public void onFailure(
                                                                                    Throwable arg0) {
                                                                                }

                                                                                public void onSuccess(String arg0) {
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
                                                                                                                                    if (btnID.equals("yes")) {
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
                                                                                                                                    } else if (btnID.equals("no")) {
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
                                                                    } else if (btnID.equals("no")) {
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

        panel3.add(flexTable);
        panel3.add(detailsTable);

        scPanel2.add(panel3);

        fPanel2.add(scPanel2);

        flexTable2.setWidget(0, 0, label5);
        flexTable2.setWidget(0, 2, listBox);
        flexTable2.setWidget(2, 0, label10);
        flexTable2.setWidget(2, 2, listBox2);

        formPanel.add(sessionTable);

        panel2.add(defaultTable);
        panel2.setSpacing(10);
        panel2.add(formPanel);
        scPanel1.add(panel2);

        fPanel1.add(scPanel1);

        hPanel2.add(regisTable);
        scPanel.add(hPanel2);
        fPanel.add(scPanel);

        panel.add(flexTable2);

        verticalPanel2.add(button);

        //        panel.add(hPanel4);
        verticalPanel2.add(fPanel);
        verticalPanel2.add(fPanel1);

        hPanel3.add(panel);
        hPanel3.setSpacing(8);
        hPanel3.add(verticalPanel2);
        hPanel3.add(fPanel2);
        scPanel3.add(hPanel3);

        formPanel3.add(scPanel3);

        
        //        vPanel.add(label3);
        vPanel.add(formPanel3);
    }
}
