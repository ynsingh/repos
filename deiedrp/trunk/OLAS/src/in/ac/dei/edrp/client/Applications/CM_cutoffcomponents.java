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
package in.ac.dei.edrp.client.Applications;

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

import com.gwtext.client.core.EventCallback;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;


public class CM_cutoffcomponents {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    Label label3 = new Label("Dayalbagh Educational Institute");
    Label label = new Label(constants.label_programName()+"*");
    Label program_name = new Label(constants.label_programname()); 
    Label program = new Label();
    int j;
    int k = 0;
    String university_id;
    Object[][] object;
    String programid;
    Validator valid = new Validator();

    public CM_cutoffcomponents(String uniid) {
        this.university_id = uniid;
    }

    public void cutoffcomponentdetails() {
        vPanel.clear();        
        ScrollPanel scPanel = new ScrollPanel();
        ScrollPanel scPanel1 = new ScrollPanel();
        ScrollPanel scPanel2 = new ScrollPanel();
        final FormPanel formPanel3 = new FormPanel();
        final FormPanel fPanel2 = new FormPanel();
        final FlexTable componentTable = new FlexTable();
        final ListBox listBox = new ListBox();
        final ListBox listBox2 = new ListBox();
        final ListBox programListBox = new ListBox();
        FlexTable flexTable2 = new FlexTable();
        VerticalPanel panel = new VerticalPanel();
        Label label5 = new Label(constants.label_offeredby());
        final Label selectLabel = new Label(constants.select());
        final Label xLabel = new Label(constants.label_xfactor());
        final Label xstateLabel = new Label(constants.label_xstatus());
        final Label numberLabel = new Label(constants.label_cutoffnumber());
        final Label numberstateLabel = new Label(constants.label_numberstatus());
        final Label percentLabel = new Label(constants.label_cutoffpercent());
        final Label percentstateLabel = new Label(constants.label_percentstatus());
        final Button saveButton = new Button(constants.saveButton());
        final Label label10 = new Label(constants.entityName() + "*");

        saveButton.setVisible(false);

        scPanel.setSize("100%", "150px");
        scPanel1.setSize("100%", "565px");
        scPanel2.setSize("100%", "170px");       
        formPanel3.setFrame(true);
        formPanel3.setSize("1200px", "600px");
        formPanel3.setTitle(constants.heading_callcomponentsetup());

        fPanel2.setFrame(true);
        fPanel2.setSize("1100px", "200px");
        fPanel2.setTitle(constants.heading_setcomponentdetails());
        fPanel2.setVisible(false);

        connectService.methodentitypopulate(university_id,
            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                }

                public void onSuccess(CM_ProgramInfoGetter[] result) {
                    listBox.addItem("Select");

                    for (int i = 0; i < result.length; i++) {
                        String type = result[i].getComponentDescription();
                        String id = result[i].getComponentId();

                        listBox.addItem(type, id);
                    }
                }
            });

        listBox.addChangeHandler(new ChangeHandler() {
                public void onChange(ChangeEvent arg0) {
                    final String entity = listBox.getValue(listBox.getSelectedIndex());

                    if (listBox.getValue(listBox.getSelectedIndex())
                                   .equalsIgnoreCase("select")) {
                        cutoffcomponentdetails();
                    }
                    fPanel2.setVisible(false);
                    connectService.methodgetentitytype(entity, university_id,
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable arg0) {
                            }

                            public void onSuccess(CM_ProgramInfoGetter[] result) {
                                String type1 = "";
                                String entity_name = "";                                
                                listBox2.clear();
                                listBox2.addItem("Select", null);                    
                                for (int i = 0; i < result.length; i++) {
                                    type1 = result[i].getEntity_id();
                                    entity_name = result[i].getEntity_name();
                                    listBox2.addItem(entity_name, type1);
                                }
                            }
                        });
                }
            });

        listBox2.addChangeHandler(new ChangeHandler() {
                public void onChange(ChangeEvent arg0) {
                    final String entity = listBox2.getValue(listBox2.getSelectedIndex());
                    fPanel2.setVisible(false);

                    connectService.getcalledprograms(entity, university_id,
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
                                                        listBox2.getItemText(
                                                            listBox2.getSelectedIndex())));
                                                setIconCls(MessageBox.INFO);
                                                setButtons(MessageBox.OK);

                                                setCallback(new MessageBox.PromptCallback() {
                                                        public void execute(
                                                            String btnID,
                                                            String text) {
                                                            if (btnID.equals(
                                                                        "ok")) {
                                                                cutoffcomponentdetails();
                                                            }
                                                        }
                                                    });
                                            }
                                        });
                                } else {
                                    programListBox.clear();
                                    programListBox.addItem("Select", null);
                                    String program_id = "";
                                    String program_name = "";
                                    for (int i = 0; i < result.length; i++) {
                                        final int k = i;
                                        program_name=(String) result[i].getProgram_name();
                                        program_id=(String) result[i].getProgram_id();
                                        programListBox.addItem(program_name, program_id);
                                                                            
                                    }
                                }
                            }
                        });
                }
            });

        programListBox.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent arg0) {            	
                if (programListBox.getValue(programListBox.getSelectedIndex())
                               .equalsIgnoreCase("select")) {
                    cutoffcomponentdetails();
                }                
                fPanel2.setVisible(true);
                saveButton.setVisible(true);
                saveButton.setEnabled(false);        
                System.out.println("selected index is "+listBox.getSelectedIndex());
                program.setText(programListBox.getItemText(programListBox.getSelectedIndex()));
                programid = programListBox.getValue(programListBox.getSelectedIndex());        
                connectService.methodgetdetails(programid,listBox2.getValue(listBox2.getSelectedIndex()),
                    new AsyncCallback<CM_ProgramInfoGetter[]>() {
                        public void onFailure(
                            Throwable arg0) {
                            MessageBox.alert(
                                "failure" +
                                arg0.getMessage());
                        }

                        public void onSuccess(
                            final CM_ProgramInfoGetter[] result) {                        	
                            componentTable.clear();
                         int count=result.length;
                       
                       	 final CheckBox[] checkBox = new CheckBox[count];
                         final Label[] factorLabel = new Label[count];
                         final ListBox[] xBox = new ListBox[count];
                         final ListBox[] numberBox = new ListBox[count];
                         final ListBox[] percentBox = new ListBox[count];
                         final String[] factoractive = new String[count];
                         final String[] numberactive = new String[count];
                         final String[] percentactive = new String[count];

                         final NumberField[] numberField = new NumberField[count];
                         final NumberField[] percentField = new NumberField[count];
                         
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
                                                            fPanel2.setVisible(false);
                                                            componentTable.setVisible(false);
                                                        }
                                                    }
                                                });
                                        }
                                    });
                            } else {     
                            	
                                fPanel2.setVisible(true);
                                componentTable.setVisible(true);
                                componentTable.setBorderWidth(3);                                
                                componentTable.addClickHandler(new ClickHandler() {
                                        public void onClick(
                                            ClickEvent arg0) {
                                        }
                                    });                                
                                for (int i = 0;
                                        i < result.length;
                                        i++) {
                                    final int z =
                                        i;
                                    componentTable.setWidget(0,
                                        0,
                                        selectLabel);
                                    componentTable.setWidget(0,
                                        1,
                                        xLabel);
                                    componentTable.setWidget(0,
                                        2,
                                        xstateLabel);
                                    componentTable.setWidget(0,
                                        3,
                                        numberLabel);
                                    componentTable.setWidget(0,
                                        4,
                                        numberstateLabel);
                                    componentTable.setWidget(0,
                                        5,
                                        percentLabel);
                                    componentTable.setWidget(0,
                                        6,
                                        percentstateLabel);

                                    checkBox[i] = new CheckBox(
                                            " " +
                                            result[i].getCos_value());
                                    factorLabel[i] = new Label();
                                    numberField[i] = new NumberField();
                                    percentField[i] = new NumberField();
                                    xBox[i] = new ListBox();
                                    numberBox[i] = new ListBox();
                                    percentBox[i] = new ListBox();
                                    factoractive[i] = new String();
                                    numberactive[i] = new String();
                                    percentactive[i] = new String();

                                    xBox[i].addItem("Inactive",
                                        "I");
                                    xBox[i].addItem("Active",
                                        "A");

                                    percentBox[i].addItem("Inactive",
                                        "I");
                                    percentBox[i].addItem("Active",
                                        "A");

                                    numberBox[i].addItem("Active",
                                        "A");
                                    numberBox[i].addItem("Inactive",
                                        "I");

                                    xBox[i].addChangeHandler(new ChangeHandler() {
                                            public void onChange(
                                                ChangeEvent arg0) {
                                                saveButton.setEnabled(true);
                                            }
                                        });

                                    numberBox[i].addChangeHandler(new ChangeHandler() {
                                            public void onChange(
                                                ChangeEvent arg0) {
                                                saveButton.setEnabled(true);

                                                if (numberBox[z].getSelectedIndex() == 0) {
                                                    numberField[z].setDisabled(false);
                                                } else {
                                                    numberField[z].setDisabled(true);
                                                }
                                            }
                                        });

                                    percentBox[i].addChangeHandler(new ChangeHandler() {
                                            public void onChange(
                                                ChangeEvent arg0) {
                                                saveButton.setEnabled(true);

                                                if (percentBox[z].getSelectedIndex() == 0) {
                                                    percentField[z].setDisabled(true);
                                                } else {
                                                    percentField[z].setDisabled(false);
                                                }
                                            }
                                        });

                                    numberField[i].addKeyPressListener(new EventCallback() {
                                            public void execute(
                                                EventObject e) {
                                                saveButton.setEnabled(true);
                                            }
                                        });

                                    percentField[i].addKeyPressListener(new EventCallback() {
                                            public void execute(
                                                EventObject e) {
                                                saveButton.setEnabled(true);
                                            }
                                        });

                                    numberField[i].setAllowBlank(false);
                                    numberField[i].setMaxLength(6);
                                    numberField[i].setAllowNegative(false);
                                    numberField[i].setMaxValue(100);
                                    percentField[i].setAllowBlank(false);
                                    percentField[i].setMaxLength(6);
                                    percentField[i].setAllowNegative(false);
                                    factorLabel[i].setText(result[i].getNo_of_times());
                                    factoractive[i] = result[i].getNo_of_times_active();
                                    numberField[i].setValue(result[i].getCut_off_number());
                                    numberactive[i] = result[i].getCut_off_number_active();
                                    percentField[i].setValue(result[i].getCut_off_percentage());
                                    percentactive[i] = result[i].getCut_off_percentage_active();

                                    if (numberactive[i].equalsIgnoreCase(
                                                "A")) {
                                        numberField[z].setDisabled(false);
                                    } else {
                                        numberField[z].setDisabled(true);
                                    }

                                    if (percentactive[i].equalsIgnoreCase(
                                                "A")) {
                                        percentField[z].setDisabled(false);
                                    } else {
                                        percentField[z].setDisabled(true);
                                    }

                                    if (factoractive[i].equalsIgnoreCase(
                                                "A")) {
                                        xBox[i].setSelectedIndex(1);
                                    } else {
                                        xBox[i].setSelectedIndex(0);
                                    }

                                    if (numberactive[i].equalsIgnoreCase(
                                                "A")) {
                                        numberBox[i].setSelectedIndex(0);
                                    } else {
                                        numberBox[i].setSelectedIndex(1);
                                    }

                                    if (percentactive[i].equalsIgnoreCase(
                                                "A")) {
                                        percentBox[i].setSelectedIndex(1);
                                    } else {
                                        percentBox[i].setSelectedIndex(0);
                                    }

                                    componentTable.setWidget(i +
                                        1,
                                        0,
                                        checkBox[i]);
                                    componentTable.setWidget(i +
                                        1,
                                        1,
                                        factorLabel[i]);
                                    componentTable.setWidget(i +
                                        1,
                                        2,
                                        xBox[i]);
                                    componentTable.setWidget(i +
                                        1,
                                        3,
                                        numberField[i]);
                                    componentTable.setWidget(i +
                                        1,
                                        4,
                                        numberBox[i]);
                                    componentTable.setWidget(i +
                                        1,
                                        5,
                                        percentField[i]);
                                    componentTable.setWidget(i +
                                        1,
                                        6,
                                        percentBox[i]);
                                }
                            }

                            saveButton.addClickHandler(new ClickHandler() {
                                    @SuppressWarnings({"deprecation"
                                    })
                                    public void onClick(
                                        ClickEvent arg0) {
                                        int a =
                                            0;
                                        int b =
                                            0;

                                        int d =
                                            0;
                                        int count =
                                            0;
                                        int flag =
                                            0;

                                        try {
                                            for (int i =
                                                    0;
                                                    i < result.length;
                                                    i++) {
                                                if (checkBox[i].isChecked()) {
                                                    flag = 1;
                                                    System.out.println(
                                                        "result =" +
                                                        result.length);
                                                    count++;

                                                    System.out.println(
                                                        "count ki value =" +
                                                        count);
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.out.println(
                                                "Exception " +
                                                e);
                                        }

                                        final String[][] arr =
                                            new String[count][7];

                                        int flag1 =
                                            0;
                                        int flag2 =
                                            0;

                                        try {
                                            for (int i =
                                                    0;
                                                    i < result.length;
                                                    i++) {
                                                try {
                                                    numberField[i].validate();
                                                } catch (Exception e) {
                                                    a = 1;
                                                }

                                                try {
                                                    percentField[i].validate();
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
                                                }

                                                if (checkBox[i].isChecked()) {
                                                    arr[d][0] = checkBox[i].getHTML();
                                                    arr[d][1] = factorLabel[i].getText();
                                                    arr[d][2] = xBox[i].getValue(xBox[i].getSelectedIndex());
                                                    arr[d][3] = numberField[i].getValue()
                                                                              .toString();
                                                    arr[d][4] = numberBox[i].getValue(numberBox[i].getSelectedIndex());
                                                    arr[d][5] = percentField[i].getValue()
                                                                               .toString();
                                                    arr[d][6] = percentBox[i].getValue(percentBox[i].getSelectedIndex());

                                                    if (arr[d][4].equalsIgnoreCase(
                                                                "A")) {
                                                        flag1 = 1;
                                                    } else {
                                                        flag1 = 0;
                                                    }

                                                    if (arr[d][6].equalsIgnoreCase(
                                                                "A")) {
                                                        flag2 = 1;
                                                    } else {
                                                        flag2 = 0;
                                                    }

                                                    d++;
                                                }
                                            }
                                        } catch (Exception e1) {
                                            System.out.println(
                                                "Exception e1 " +
                                                e1);
                                        }

                                        int x =
                                            flag1 +
                                            flag2;

                                        if (flag == 1) {
                                            if (x > 1) {
                                                MessageBox.show(new MessageBoxConfig() {

                                                        {
                                                            setTitle(msgs.error());
                                                            setMsg(msgs.error_activecos());
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
                                            }

                                            System.out.println(
                                                "value of x" +
                                                x);

                                            if ((flag == 1) &&
                                                    (a == 0) &&
                                                    (b == 0) &&
                                                    (x < 2)) {
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
                                                                            connectService.methodinsertcalldetails(programid,
                                                                                arr,
                                                                                university_id,
                                                                                listBox2.getValue(listBox2.getSelectedIndex()),
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
                                                                                                                if (btnID.equals(
                                                                                                                            "ok")) {
                                                                                                                    fPanel2.setVisible(false);
                                                                                                                    saveButton.setVisible(false);
                                                                                                                    listBox.setSelectedIndex(0);
                                                                                                                    listBox2.setSelectedIndex(-1);
                                                                                                                    programListBox.setSelectedIndex(-1);
                                                                                                                }
                                                                                                            }
                                                                                                        });
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
                                        } else if (flag == 0) {
                                            MessageBox.show(new MessageBoxConfig() {

                                                    {
                                                        setTitle(msgs.error());
                                                        setMsg(msgs.error_selectrecord());
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
        scPanel2.add(componentTable);
        fPanel2.add(scPanel2);
        flexTable2.setWidget(0, 0, label5);
        flexTable2.setWidget(0, 1, listBox);
        flexTable2.setWidget(2, 0, label10);
        flexTable2.setWidget(2, 1, listBox2);
        flexTable2.setWidget(4, 0, label);
        flexTable2.setWidget(4, 1, programListBox);
        panel.setSpacing(8);
        panel.add(flexTable2);
        panel.add(fPanel2);
        panel.add(saveButton);
        scPanel1.add(panel);

        formPanel3.add(scPanel1);        
        label3.setStyleName("panelHeading");
        vPanel.add(formPanel3);
    }
}
