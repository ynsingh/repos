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
package in.ac.dei.edrp.client.ProgramSetup;

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

import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.Date;


public class CM_cutoffcall {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    Label label3 = new Label("Dayalbagh Educational Institute");
    Label label = new Label(constants.label_programName());
    Label branchLabel = new Label(constants.label_branchname());
    Label label1 = new Label(constants.category());
    Label label2 = new Label(constants.label_xfactor());
    Label label6 = new Label(constants.label_categoryseats());
    Label startLabel = new Label(constants.label_sessionstartdate());
    Label endLabel = new Label(constants.label_sessionenddate());
    Label program_name = new Label(constants.label_programname());
    Label genderLabel = new Label(constants.gender());
    Label specLabel = new Label(constants.label_coscode());
    Label program = new Label();
    Label branch = new Label();
    Label selectLabel = new Label(constants.select());
    Hyperlink[] proHyperlink = new Hyperlink[550];
    Label[] branchname = new Label[550];
    CheckBox[] checkBox = new CheckBox[550];
    String[] offeredby = new String[550];
    String[] branches = new String[550];
    String cosvalue = "";
    int j;
    int k = 0;
    String speccode;
    String[] programid = new String[550];
    Validator valid = new Validator();
    String cos_code;
    String university_id;

    // final DateField startField = new DateField();
    // final DateField endField = new DateField();
    final DateItem startField = new DateItem();
    final DateItem endField = new DateItem();

    public CM_cutoffcall(String uniid) {
        this.university_id = uniid;
    }

    public void callcutoff() {
        vPanel.clear();

        HorizontalPanel hPanel3 = new HorizontalPanel();
        ScrollPanel scPanel = new ScrollPanel();
        ScrollPanel scPanel1 = new ScrollPanel();
        ScrollPanel scpanel2 = new ScrollPanel();
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
        HorizontalPanel hPanel4 = new HorizontalPanel();

        final Button saveButton = new Button(constants.saveButton());
        final Button submitButton = new Button(constants.submit());

        final Hyperlink defaultHyperlink = new Hyperlink(constants.heading_defaultprogramcossetup(),
                null);
        final Hyperlink indiHyperlink = new Hyperlink(constants.heading_indivisualprogramcossetup(),
                null);

        startField.setValue(new Date());
        endField.setValue(new Date());

        // startField.setAllowBlank(false);
        //endField.setAllowBlank(false);
        final DynamicForm startdateFieldContainer = new DynamicForm();
        startField.setTitle("");
        startField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
        startField.setUseTextField(true);
        startField.setValidateOnChange(true);
        startField.setRequired(true);
        startField.setEnforceDate(true);
        startField.setShowErrorIcon(true);
        startdateFieldContainer.setItems(startField);

        final DynamicForm enddateFieldContainer = new DynamicForm();
        endField.setTitle("");
        endField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
        endField.setUseTextField(true);
        endField.setValidateOnChange(true);
        endField.setRequired(true);
        endField.setEnforceDate(true);
        endField.setShowErrorIcon(true);
        enddateFieldContainer.setItems(endField);

        Label label5 = new Label(constants.label_offeredby());

        final NumberField box2 = new NumberField();
        final NumberField box3 = new NumberField();
        final ListBox categoryBox = new ListBox();
        final ListBox genderBox = new ListBox();
        final Label specField = new Label();

        categoryBox.addItem("Select");
        categoryBox.addItem("General", "GN");
        categoryBox.addItem("Other Backward Caste", "BC");
        categoryBox.addItem("Schedule Caste", "SC");
        categoryBox.addItem("Schedule Tribe", "ST");

        genderBox.addItem("Select");
        genderBox.addItem("Male", "M");
        genderBox.addItem("Female", "F");
        genderBox.addItem("X-for no division", "X");

        box2.setAllowDecimals(false);
        box2.setAllowNegative(false);
        box2.setAllowBlank(false);
        box2.setMaxLength(2);
        box2.setValue(1);
        box2.setMinValue(1);

        box3.setAllowDecimals(false);
        box3.setAllowNegative(false);
        box3.setAllowBlank(false);
        box3.setValue(1);
        box3.setMinValue(1);
        box3.setMaxLength(3);

        scPanel.setSize("100%", "160px");

        scPanel1.setSize("100%", "160px");

        scpanel2.setSize("100%", "210px");

        selectLabel.setStyleName("jack");
        branchLabel.setStyleName("jack");
        label.setStyleName("jack");

        formPanel3.setFrame(true);
        formPanel3.setSize("1100px", "400px");
        formPanel3.setTitle(constants.heading_cutofflist());

        formPanel.setFrame(true);
        formPanel.setSize("300px", "150px");
        formPanel.setTitle(constants.heading_sessiondate());

        fPanel.setFrame(true);
        fPanel.setSize("400px", "170px");
        fPanel.setTitle(constants.heading_listforindividualcos());

        fPanel1.setFrame(true);
        fPanel1.setSize("400px", "170px");
        fPanel1.setTitle(constants.heading_defaultcos());

        fPanel.setVisible(false);

        fPanel1.setVisible(false);

        fPanel2.setFrame(true);
        fPanel2.setSize("600px", "220px");
        fPanel2.setTitle(constants.heading_setdetails());
        fPanel2.setClosable(true);

        fPanel2.setVisible(false);

        defaultHyperlink.setVisible(false);
        indiHyperlink.setVisible(false);

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

        listBox.addChangeHandler(new ChangeHandler() {
                public void onChange(ChangeEvent arg0) {
                    final String entity = listBox.getItemText(listBox.getSelectedIndex());
                    fPanel.setVisible(false);
                    fPanel1.setVisible(false);
                    fPanel2.setVisible(false);
                    box2.setValue(1);
                    box3.setValue(1);
                    defaultHyperlink.setVisible(true);
                    indiHyperlink.setVisible(true);

                    defaultHyperlink.addClickHandler(new ClickHandler() {
                            public void onClick(ClickEvent arg0) {
                                connectService.getdistinctprograms(entity,
                                    university_id,
                                    new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                        public void onFailure(Throwable arg0) {
                                        }

                                        public void onSuccess(
                                            final CM_ProgramInfoGetter[] result) {
                                            defaultTable.clear();
                                            fPanel1.setVisible(true);
                                            fPanel.setVisible(false);
                                            fPanel2.setVisible(false);

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
                                                                            callcutoff();
                                                                        }
                                                                    }
                                                                });
                                                        }
                                                    });
                                            } else {
                                                for (int i = 0;
                                                        i < result.length;
                                                        i++) {
                                                    k = i;

                                                    checkBox[i] = new CheckBox(
                                                            " " +
                                                            result[i].getProgram_name());

                                                    programid[k] = result[i].getprogram_id();
                                                    offeredby[k] = result[i].getEntity_id();
                                                    branchname[k] = new Label();
                                                    branchname[k].setText(result[i].getBranchName());
                                                    branches[k] = result[i].getBranch();

                                                    defaultTable.setBorderWidth(5);

                                                    defaultTable.setWidget(0,
                                                        0, selectLabel);
                                                    defaultTable.setWidget(i +
                                                        1, 0, checkBox[i]);
                                                    defaultTable.setWidget(0,
                                                        1, branchLabel);
                                                    defaultTable.setWidget(i +
                                                        1, 1, branchname[i]);

                                                    sessionTable.setWidget(2,
                                                        0, startLabel);
                                                    sessionTable.setWidget(2,
                                                        1,
                                                        startdateFieldContainer);
                                                    sessionTable.setWidget(4,
                                                        0, endLabel);
                                                    sessionTable.setWidget(4,
                                                        1, enddateFieldContainer);
                                                }

                                                saveButton.addClickHandler(new ClickHandler() {
                                                        @SuppressWarnings("deprecation")
                                                        public void onClick(
                                                            ClickEvent arg0) {
                                                            int d = 0;
                                                            int count = 0;
                                                            int flag = 0;
                                                            int a = 0;
                                                            int b = 0;

                                                            try {
                                                                for (int i = 0;
                                                                        i < result.length;
                                                                        i++) {
                                                                    if (checkBox[i].isChecked()) {
                                                                        flag = 1;

                                                                        count++;
                                                                    }
                                                                }
                                                            } catch (Exception e) {
                                                                System.out.println(
                                                                    "Exception " +
                                                                    e);
                                                            }

                                                            final String[][] arr =
                                                                new String[count][3];

                                                            try {
                                                                for (int i = 0;
                                                                        i < result.length;
                                                                        i++) {
                                                                    if (checkBox[i].isChecked()) {
                                                                        arr[d][0] = programid[i];
                                                                        arr[d][1] = branches[i];
                                                                        arr[d][2] = offeredby[i];

                                                                        d++;
                                                                    }
                                                                }
                                                            } catch (Exception e1) {
                                                                System.out.println(
                                                                    "Exception e1 " +
                                                                    e1);
                                                            }

                                                            try {
                                                                if (checkDate() > 0) {
                                                                    a = 1;
                                                                }
                                                            } catch (Exception e1) {
                                                                a = 1;
                                                            }

                                                            try {
                                                                if (checkDate() > 0) {
                                                                    b = 1;
                                                                }
                                                            } catch (Exception e1) {
                                                                b = 1;

                                                                //MessageBox.alert("Enter a valid Date");
                                                            }

                                                            if (flag == 1) {
                                                                final String dateSelected =
                                                                    startField.getDisplayValue();
                                                                final String dateSelected1 =
                                                                    endField.getDisplayValue();

                                                                if ((a == 1) ||
                                                                        (b == 1)) {
                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(msgs.error());
                                                                                setMsg(msgs.checkFields());
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
                                                                } else if (valid.datechecker1(
                                                                            (Date) startField.getValue(),
                                                                            (Date) endField.getValue())) {
                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                            {
                                                                                setTitle(msgs.error());
                                                                                setMsg(msgs.error_sessiondate());
                                                                                setIconCls(MessageBox.ERROR);
                                                                                setButtons(MessageBox.OK);
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
                                                                                                connectService.getdefaultdetails(arr,
                                                                                                    new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                                        public void onFailure(
                                                                                                            Throwable result) {
                                                                                                            MessageBox.alert(
                                                                                                                "in failure" +
                                                                                                                result.getMessage());
                                                                                                        }

                                                                                                        public void onSuccess(
                                                                                                            CM_ProgramInfoGetter[] result) {
                                                                                                            try {
                                                                                                                if (result.length == 0) {
                                                                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                                                                            {
                                                                                                                                setTitle(msgs.error());
                                                                                                                                setMsg(msgs.error_record());
                                                                                                                                setIconCls(MessageBox.ERROR);
                                                                                                                                setButtons(MessageBox.OK);

                                                                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                                                                        public void execute(
                                                                                                                                            String btnID,
                                                                                                                                            String text) {
                                                                                                                                            if (btnID.equals(
                                                                                                                                                        "ok")) {
                                                                                                                                                fPanel1.setVisible(false);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });
                                                                                                                            }
                                                                                                                        });
                                                                                                                } else {
                                                                                                                    for (int i =
                                                                                                                            0;
                                                                                                                            i < arr.length;
                                                                                                                            i++) {
                                                                                                                        for (int j =
                                                                                                                                0;
                                                                                                                                j < result.length;
                                                                                                                                j++) {
                                                                                                                            int seats =
                                                                                                                                result[j].getDefaultseats();
                                                                                                                            String category =
                                                                                                                                result[j].getcategory();
                                                                                                                            Float percentseats =
                                                                                                                                result[j].getPercentage_seats();
                                                                                                                            int actualseats =
                                                                                                                                (int) ((percentseats / 100) * seats);

                                                                                                                            connectService.methodinsertdefaultsettings(arr,
                                                                                                                                category,
                                                                                                                                actualseats,
                                                                                                                                dateSelected,
                                                                                                                                dateSelected1,
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
                                                                                                                                                                fPanel1.setVisible(false);
                                                                                                                                                            }
                                                                                                                                                        });
                                                                                                                                                }
                                                                                                                                            });
                                                                                                                                    }
                                                                                                                                });
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            } catch (Exception e) {
                                                                                                                System.out.println(
                                                                                                                    "client side ex " +
                                                                                                                    e);
                                                                                                            }
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
                                                            } else if (flag == 0) {
                                                                MessageBox.show(new MessageBoxConfig() {

                                                                        {
                                                                            setTitle(msgs.error());
                                                                            setMsg(msgs.error_noprogram());
                                                                            setIconCls(MessageBox.ERROR);
                                                                            setButtons(MessageBox.OK);
                                                                        }
                                                                    });
                                                            }
                                                        }
                                                    });
                                            }
                                        }
                                    });
                            }
                        });

                    indiHyperlink.addClickHandler(new ClickHandler() {
                            public void onClick(ClickEvent arg0) {
                                String settings = "D";
                                connectService.getindivisualprograms(entity,
                                    settings, university_id,
                                    new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                        public void onFailure(Throwable arg0) {
                                        }

                                        public void onSuccess(
                                            final CM_ProgramInfoGetter[] result) {
                                            regisTable.clear();
                                            fPanel.setVisible(true);

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
                                                                            callcutoff();
                                                                        }
                                                                    }
                                                                });
                                                        }
                                                    });
                                            } else {
                                                indiHyperlink.setVisible(true);
                                                defaultHyperlink.setVisible(true);
                                                fPanel.setVisible(true);
                                                fPanel1.setVisible(false);

                                                for (int i = 0;
                                                        i < result.length;
                                                        i++) {
                                                    final int k = i;

                                                    proHyperlink[i] = new Hyperlink(result[i].getProgram_name(),
                                                            null);
                                                    branchname[i] = new Label();
                                                    branchname[i].setText(result[i].getBranchName());

                                                    regisTable.setVisible(true);
                                                    regisTable.setBorderWidth(5);

                                                    regisTable.setWidget(0, 0,
                                                        label);
                                                    regisTable.setWidget(0, 1,
                                                        branchLabel);
                                                    regisTable.setWidget(i + 1,
                                                        0, proHyperlink[i]);
                                                    regisTable.setWidget(i + 1,
                                                        1, branchname[i]);

                                                    proHyperlink[i].addClickHandler(new ClickHandler() {
                                                            public void onClick(
                                                                ClickEvent arg0) {
                                                                fPanel2.setVisible(true);

                                                                program.setText(proHyperlink[k].getText());
                                                                branch.setText(branchname[k].getText());

                                                                final String programid =
                                                                    result[k].getProgram_id();

                                                                branches[k] = result[k].getBranch();

                                                                final String branch_code =
                                                                    branches[k];

                                                                connectService.getcos_cosdes(programid,
                                                                    branch_code,
                                                                    new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                        public void onFailure(
                                                                            Throwable arg0) {
                                                                        }

                                                                        public void onSuccess(
                                                                            CM_ProgramInfoGetter[] cos) {
                                                                            for (int i =
                                                                                    0;
                                                                                    i < cos.length;
                                                                                    i++) {
                                                                                cos_code = cos[i].getCos_value();

                                                                                specField.setText(cos_code);
                                                                            }
                                                                        }
                                                                    });

                                                                flexTable.setWidget(0,
                                                                    0,
                                                                    program_name);
                                                                flexTable.setWidget(0,
                                                                    2, program);
                                                                flexTable.setWidget(0,
                                                                    4, branch);
                                                                detailsTable.setWidget(2,
                                                                    0, label1);
                                                                detailsTable.setWidget(2,
                                                                    2,
                                                                    categoryBox);
                                                                detailsTable.setWidget(2,
                                                                    4,
                                                                    genderLabel);
                                                                detailsTable.setWidget(2,
                                                                    6, genderBox);
                                                                detailsTable.setWidget(4,
                                                                    0, specLabel);
                                                                detailsTable.setWidget(4,
                                                                    2, specField);

                                                                detailsTable.setWidget(4,
                                                                    4, label2);
                                                                detailsTable.setWidget(4,
                                                                    6, box2);
                                                                detailsTable.setWidget(6,
                                                                    0, label6);
                                                                detailsTable.setWidget(6,
                                                                    2, box3);
                                                                detailsTable.setWidget(8,
                                                                    0,
                                                                    startLabel);
                                                                detailsTable.setWidget(8,
                                                                    2,
                                                                    startdateFieldContainer);
                                                                detailsTable.setWidget(8,
                                                                    4, endLabel);
                                                                detailsTable.setWidget(8,
                                                                    6,
                                                                    enddateFieldContainer);
                                                                detailsTable.setWidget(12,
                                                                    0,
                                                                    submitButton);

                                                                submitButton.addClickHandler(new ClickHandler() {
                                                                        public void onClick(
                                                                            ClickEvent arg0) {
                                                                            final String category =
                                                                                categoryBox.getValue(categoryBox.getSelectedIndex());
                                                                            String gender =
                                                                                genderBox.getValue(genderBox.getSelectedIndex());
                                                                            speccode = cos_code;
                                                                            cosvalue = category +
                                                                                gender +
                                                                                speccode;

                                                                            int x =
                                                                                0;
                                                                            int y =
                                                                                0;

                                                                            final String dateSelected =
                                                                                startField.getDisplayValue();
                                                                            final String dateSelected1 =
                                                                                endField.getDisplayValue();
                                                                            offeredby[k] = result[k].getEntity_id();

                                                                            final String factor =
                                                                                box2.getText();
                                                                            final String seats =
                                                                                box3.getText();
                                                                            final String offered =
                                                                                offeredby[k];

                                                                            connectService.getcosvalue(programid,
                                                                                branch_code,
                                                                                offeredby[k],
                                                                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                    public void onFailure(
                                                                                        Throwable arg0) {
                                                                                    }

                                                                                    public void onSuccess(
                                                                                        CM_ProgramInfoGetter[] result) {
                                                                                        String[] cos_values =
                                                                                            new String[result.length];
                                                                                        boolean flag =
                                                                                            true;

                                                                                        for (int i =
                                                                                                0;
                                                                                                i < result.length;
                                                                                                i++) {
                                                                                            cos_values[i] = result[i].getCos_value();

                                                                                            if (cos_values[i].equals(
                                                                                                        cosvalue)) {
                                                                                                flag = false;

                                                                                                break;
                                                                                            }

                                                                                            //cos value k liye condition yahan aani hai..
                                                                                            if (((cos_values[i].equalsIgnoreCase(category +
                                                                                                        'M' +
                                                                                                        speccode)) ||
                                                                                                    (cos_values[i].equalsIgnoreCase(category +
                                                                                                        'F' +
                                                                                                        speccode))) &&
                                                                                                    (cosvalue.equalsIgnoreCase(category +
                                                                                                        'X' +
                                                                                                        speccode))) {
                                                                                                MessageBox.show(new MessageBoxConfig() {

                                                                                                        {
                                                                                                            setTitle(msgs.error());
                                                                                                            setMsg(msgs.error_nodivision());
                                                                                                            setIconCls(MessageBox.ERROR);
                                                                                                            setButtons(MessageBox.OK);
                                                                                                            setCallback(new MessageBox.PromptCallback() {
                                                                                                                    public void execute(
                                                                                                                        String btnID,
                                                                                                                        String text) {
                                                                                                                        if (btnID.equals(
                                                                                                                                    "ok")) {
                                                                                                                            categoryBox.setSelectedIndex(0);
                                                                                                                            genderBox.setSelectedIndex(0);
                                                                                                                            startField.setValue(new Date());
                                                                                                                            endField.setValue(new Date());
                                                                                                                            box2.setValue(1);
                                                                                                                            box3.setValue(1);
                                                                                                                        }
                                                                                                                    }
                                                                                                                });
                                                                                                        }
                                                                                                    });
                                                                                            }

                                                                                            if ((cos_values[i].equalsIgnoreCase(category +
                                                                                                        'X' +
                                                                                                        speccode)) &&
                                                                                                    ((cosvalue.equalsIgnoreCase(category +
                                                                                                        'M' +
                                                                                                        speccode)) ||
                                                                                                    (cosvalue.equalsIgnoreCase(category +
                                                                                                        'F' +
                                                                                                        speccode)))) {
                                                                                                MessageBox.show(new MessageBoxConfig() {

                                                                                                        {
                                                                                                            setTitle(msgs.error());
                                                                                                            setMsg(msgs.error_gendercategory());
                                                                                                            setIconCls(MessageBox.ERROR);
                                                                                                            setButtons(MessageBox.OK);
                                                                                                            setCallback(new MessageBox.PromptCallback() {
                                                                                                                    public void execute(
                                                                                                                        String btnID,
                                                                                                                        String text) {
                                                                                                                        if (btnID.equals(
                                                                                                                                    "ok")) {
                                                                                                                            categoryBox.setSelectedIndex(0);
                                                                                                                            genderBox.setSelectedIndex(0);
                                                                                                                            startField.setValue(new Date());
                                                                                                                            endField.setValue(new Date());
                                                                                                                            box2.setValue(1);
                                                                                                                            box3.setValue(1);
                                                                                                                        }
                                                                                                                    }
                                                                                                                });
                                                                                                        }
                                                                                                    });
                                                                                            }
                                                                                        }

                                                                                        if (flag == false) {
                                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                                    {
                                                                                                        setMinWidth(180);
                                                                                                        setMaxWidth(400);
                                                                                                        setTitle(msgs.error());
                                                                                                        setMsg(program +
                                                                                                            " with " +
                                                                                                            cosvalue +
                                                                                                            " category value " +
                                                                                                            " already exist ");
                                                                                                        setIconCls(MessageBox.ERROR);
                                                                                                        setButtons(MessageBox.OK);

                                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                                public void execute(
                                                                                                                    String btnID,
                                                                                                                    String text) {
                                                                                                                    if (btnID.equals(
                                                                                                                                "ok")) {
                                                                                                                        categoryBox.setSelectedIndex(0);
                                                                                                                        genderBox.setSelectedIndex(0);
                                                                                                                        startField.setValue(new Date());
                                                                                                                        endField.setValue(new Date());
                                                                                                                        box2.setValue(1);
                                                                                                                        box3.setValue(1);
                                                                                                                    }
                                                                                                                }
                                                                                                            });
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                    }
                                                                                });

                                                                            connectService.getCosSeats(programid,
                                                                                branch_code,
                                                                                offeredby[k],
                                                                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                    public void onFailure(
                                                                                        Throwable arg0) {
                                                                                    }

                                                                                    public void onSuccess(
                                                                                        CM_ProgramInfoGetter[] arg0) {
                                                                                        String cosseats =
                                                                                            null;

                                                                                        final int total_seats;

                                                                                        for (int i =
                                                                                                0;
                                                                                                i < arg0.length;
                                                                                                i++) {
                                                                                            if (arg0[i].getNo_of_seats() == (null)) {
                                                                                                cosseats = "0";
                                                                                            } else {
                                                                                                cosseats = arg0[i].getNo_of_seats();
                                                                                            }
                                                                                        }

                                                                                        total_seats = Integer.parseInt(cosseats) +
                                                                                            Integer.parseInt(seats);

                                                                                        connectService.gettotalseats(programid,
                                                                                            branch_code,
                                                                                            offeredby[k],
                                                                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                                public void onFailure(
                                                                                                    Throwable results) {
                                                                                                }

                                                                                                public void onSuccess(
                                                                                                    CM_ProgramInfoGetter[] results) {
                                                                                                    int totalseats =
                                                                                                        0;

                                                                                                    for (int i =
                                                                                                            0;
                                                                                                            i < results.length;
                                                                                                            i++) {
                                                                                                        totalseats = Integer.parseInt(results[i].getNo_of_seats());
                                                                                                    }

                                                                                                    if (total_seats > totalseats) {
                                                                                                        MessageBox.show(new MessageBoxConfig() {

                                                                                                                {
                                                                                                                    setTitle(msgs.error());
                                                                                                                    setMsg(msgs.error_seatsexceed());
                                                                                                                    setIconCls(MessageBox.ERROR);
                                                                                                                    setButtons(MessageBox.OK);
                                                                                                                    setCallback(new MessageBox.PromptCallback() {
                                                                                                                            public void execute(
                                                                                                                                String btnID,
                                                                                                                                String text) {
                                                                                                                                if (btnID.equals(
                                                                                                                                            "ok")) {
                                                                                                                                    box3.setValue(1);
                                                                                                                                }
                                                                                                                            }
                                                                                                                        });
                                                                                                                }
                                                                                                            });
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });

                                                                            int a =
                                                                                0;
                                                                            int b =
                                                                                0;

                                                                            try {
                                                                                if (checkDate() > 0) {
                                                                                    x = 1;
                                                                                }
                                                                            } catch (Exception e1) {
                                                                                x = 1;
                                                                                
                                                                            }

                                                                            try {
                                                                                if (checkDate() > 0) {
                                                                                    y = 1;
                                                                                }
                                                                            } catch (Exception e1) {
                                                                                y = 1;
                                                                            }

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

                                                                            if ((a == 1) ||
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
                                                                                        factor)) ||
                                                                                    (valid.nullValidator(
                                                                                        seats)) ||
                                                                                    (valid.nullValidator(
                                                                                        speccode)) ||
                                                                                    (categoryBox.getSelectedIndex() == 0) ||
                                                                                    (genderBox.getSelectedIndex() == 0)) {
                                                                                MessageBox.show(new MessageBoxConfig() {

                                                                                        {
                                                                                            setTitle(msgs.error());
                                                                                            setMsg(msgs.error_mandatoryfields());
                                                                                            setIconCls(MessageBox.ERROR);
                                                                                            setButtons(MessageBox.OK);
                                                                                        }
                                                                                    });
                                                                            } else if ((x == 1) ||
                                                                                    (y == 1)) {
                                                                                MessageBox.show(new MessageBoxConfig() {

                                                                                        {
                                                                                            setTitle(msgs.error());
                                                                                            setMsg(msgs.checkFields());
                                                                                            setIconCls(MessageBox.ERROR);
                                                                                            setButtons(MessageBox.OK);
                                                                                        }
                                                                                    });
                                                                            } else if (valid.datechecker1(
                                                                                        (Date) startField.getValue(),
                                                                                        (Date) endField.getValue())) {
                                                                                MessageBox.show(new MessageBoxConfig() {

                                                                                        {
                                                                                            setTitle(msgs.error());
                                                                                            setMsg(msgs.error_sessiondate());
                                                                                            setIconCls(MessageBox.ERROR);
                                                                                            setButtons(MessageBox.OK);
                                                                                            setCallback(new MessageBox.PromptCallback() {
                                                                                                    public void execute(
                                                                                                        String btnID,
                                                                                                        String text) {
                                                                                                        startField.setValue(new Date());
                                                                                                        endField.setValue(new Date());
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
                                                                                                            connectService.methodinsertcutoffdetails(cosvalue,
                                                                                                                factor,
                                                                                                                seats,
                                                                                                                programid,
                                                                                                                offered,
                                                                                                                branch_code,
                                                                                                                dateSelected,
                                                                                                                dateSelected1,
                                                                                                                university_id,
                                                                                                                new AsyncCallback<String>() {
                                                                                                                    public void onFailure(
                                                                                                                        Throwable arg0) {
                                                                                                                    }

                                                                                                                    public void onSuccess(
                                                                                                                        String result) {
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
                                                                                                                                                                            categoryBox.setSelectedIndex(0);
                                                                                                                                                                            genderBox.setSelectedIndex(0);

                                                                                                                                                                            startField.setValue(new Date());
                                                                                                                                                                            endField.setValue(new Date());
                                                                                                                                                                            box2.setValue(1);
                                                                                                                                                                            box3.setValue(1);
                                                                                                                                                                        } else if (btnID.equals(
                                                                                                                                                                                    "no")) {
                                                                                                                                                                            fPanel2.setVisible(false);

                                                                                                                                                                            box2.setValue(1);
                                                                                                                                                                            box3.setValue(1);
                                                                                                                                                                            categoryBox.setSelectedIndex(0);
                                                                                                                                                                            genderBox.setSelectedIndex(0);
                                                                                                                                                                            startField.setValue(new Date());
                                                                                                                                                                            endField.setValue(new Date());
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
                                                                                                            fPanel2.setVisible(false);

                                                                                                            box2.setValue(1);
                                                                                                            box3.setValue(1);
                                                                                                            categoryBox.setSelectedIndex(0);
                                                                                                            genderBox.setSelectedIndex(0);
                                                                                                            startField.setValue(new Date());
                                                                                                            endField.setValue(new Date());
                                                                                                        }
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                    });
                                                                            }
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
                }
            });

        panel3.add(flexTable);
        panel3.add(detailsTable);

        scpanel2.add(panel3);

        fPanel2.add(scpanel2);
        hPanel.add(label5);
        hPanel.setSpacing(20);
        hPanel.add(listBox);

        hPanel4.add(defaultHyperlink);
        hPanel4.setSpacing(30);
        hPanel4.add(indiHyperlink);

        formPanel.add(sessionTable);

        panel2.add(defaultTable);
        panel2.setSpacing(10);
        panel2.add(formPanel);
        panel2.add(saveButton);
        scPanel1.add(panel2);

        fPanel1.add(scPanel1);

        hPanel2.add(regisTable);
        scPanel.add(hPanel2);
        fPanel.add(scPanel);

        panel.add(hPanel);
        panel.add(hPanel4);
        panel.add(fPanel);
        panel.add(fPanel1);

        hPanel3.add(panel);
        hPanel3.add(fPanel2);

        formPanel3.add(hPanel3);

        //        label3.setStyleName("panelHeading");

        //        vPanel.add(label3);
        vPanel.add(formPanel3);
    }

    public int checkDate() throws Exception {
        Date startDate = null;
        Date endDate = null;
        int check = 0;

        try {
            startDate = (Date) startField.getValue();
            endDate = (Date) endField.getValue();
            if(startField.getDisplayValue().length()>10){
            	check++;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        
        
        if (valid.nullValidator2(startField.getDisplayValue()) ||
                valid.nullValidator2(endField.getDisplayValue())) {
            try {
                check++;
                //dateofbirthDateField.markInvalid("");
                startField.getInvalidDateStringMessage();
            } catch (Exception e) {
            }
        }
        System.out.println("check value: "+check);
        return check;
    }
}
