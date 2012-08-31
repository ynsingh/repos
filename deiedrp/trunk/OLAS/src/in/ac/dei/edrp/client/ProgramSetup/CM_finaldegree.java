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
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;


public class CM_finaldegree {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    Label label3 = new Label("Dayalbagh Educational Institute");
    Label label = new Label(constants.label_programName());
    Label label1 = new Label(constants.label_selectcomponent());
    Label program_name = new Label(constants.label_programname());
    Label entityLabel = new Label("Offering Entity");
    Label program = new Label();
    Hyperlink[] proHyperlink = new Hyperlink[550];
    String[] offeredby = new String[550];
    Button submitButton = new Button(constants.submit());
    Button saveButton = new Button(constants.saveButton());
    int j;
    int k = 0;
    String university_id;
    Object[][] object;
    String programid;
    Validator valid = new Validator();

    public CM_finaldegree(String userid) {
        this.university_id = userid;
    }

    public void finaldegree() {
        vPanel.clear();

        HorizontalPanel hPanel3 = new HorizontalPanel();
        ScrollPanel scPanel = new ScrollPanel();
        ScrollPanel scPanel1 = new ScrollPanel();
        final FormPanel formPanel3 = new FormPanel();
        final FormPanel fPanel = new FormPanel();
        final FormPanel fPanel2 = new FormPanel();
        final FormPanel formPanel = new FormPanel();
        final FlexTable regisTable = new FlexTable();
        final FlexTable detailsTable = new FlexTable();
        final FlexTable defaultTable = new FlexTable();
        final FlexTable sessionTable = new FlexTable();
        FlexTable flexTable2 = new FlexTable();
        final ListBox listBox = new ListBox();
        final ListBox listBox2 = new ListBox();
        HorizontalPanel hPanel2 = new HorizontalPanel();
        VerticalPanel panel = new VerticalPanel();
        VerticalPanel panel2 = new VerticalPanel();
        VerticalPanel hPanel4 = new VerticalPanel();
        final VerticalPanel verticalPanel2 = new VerticalPanel();
        final Label label10 = new Label(constants.entityName() + "*");

        Label label5 = new Label(constants.label_offeredby());

        final ComboBox componentBox = new ComboBox();

        final Button button = new Button(constants.okButton());

        componentBox.setForceSelection(true);
        componentBox.setMinChars(1);
        componentBox.setFieldLabel("State");
        componentBox.setDisplayField("State");
        componentBox.setValueField("number");
        componentBox.setMode(ComboBox.LOCAL);
        componentBox.setTriggerAction(ComboBox.ALL);
        componentBox.setEmptyText("Select Component");
        componentBox.setLoadingText("Searching...");
        componentBox.setTypeAhead(true);
        componentBox.setSelectOnFocus(true);
        componentBox.setWidth(198);
        componentBox.setHideTrigger(false);

        scPanel.setSize("100%", "150px");

        scPanel1.setSize("100%", "150px");
        label.setStyleName("jack");
        entityLabel.setStyleName("jack");

        button.setVisible(false);

        formPanel3.setFrame(true);
        formPanel3.setSize("1000px", "400px");
        formPanel3.setTitle(constants.heading_firstdegreecomponent());

        fPanel.setFrame(true);
        fPanel.setSize("300px", "150px");
        fPanel.setTitle(constants.heading_programlist());

        fPanel.setVisible(false);

        fPanel2.setFrame(true);
        fPanel2.setSize("350px", "150px");
        fPanel2.setTitle(constants.heading_firstdegreedetails());
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
                        finaldegree();
                    }

                    button.setVisible(false);

                    fPanel.setVisible(false);
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
                    if (listBox2.getSelectedIndex() == 0) {
                        button.setVisible(false);
                    } else {
                        button.setVisible(true);
                    }

                    fPanel.setVisible(false);
                    fPanel2.setVisible(false);
                }
            });

        button.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    final String entitytype = listBox.getValue(listBox.getSelectedIndex());
                    final String entityname = listBox2.getValue(listBox2.getSelectedIndex());
                    fPanel.setVisible(false);
                    fPanel2.setVisible(false);

                    connectService.methodgetprogramses(entitytype,
                        university_id, entityname,
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
                                                                finaldegree();
                                                            }
                                                        }
                                                    });
                                            }
                                        });
                                } else {
                                    fPanel.setVisible(true);
                                    regisTable.clear();

                                    for (int i = 0; i < result.length; i++) {
                                        final int k = i;

                                        proHyperlink[i] = new Hyperlink(result[i].getProgram_name(),
                                                null);

                                        regisTable.setVisible(true);
                                        regisTable.setBorderWidth(5);

                                        regisTable.setWidget(0, 0, label);
                                        regisTable.setWidget(i + 1, 0,
                                            proHyperlink[i]);

                                        proHyperlink[i].addClickHandler(new ClickHandler() {
                                                public void onClick(
                                                    ClickEvent arg0) {
                                                    fPanel2.setVisible(true);

                                                    try {
                                                        componentBox.clearValue();
                                                    } catch (Exception e) {
                                                        System.out.println("e" +
                                                            e);
                                                    }

                                                    program.setText(proHyperlink[k].getText());

                                                    programid = result[k].getProgram_id();

                                                    detailsTable.setWidget(2,
                                                        0, program_name);
                                                    detailsTable.setWidget(2,
                                                        2, program);
                                                    detailsTable.setWidget(6,
                                                        0, label1);
                                                    detailsTable.setWidget(6,
                                                        2, componentBox);

                                                    detailsTable.setWidget(10,
                                                        0, submitButton);

                                                    connectService.methodprogrammaster(programid,
                                                        university_id,
                                                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                            public void onFailure(
                                                                Throwable arg0) {
                                                                MessageBox.alert(
                                                                    "failure");
                                                            }

                                                            public void onSuccess(
                                                                CM_ProgramInfoGetter[] result) {
                                                                RecordDef recordDef =
                                                                    new RecordDef(new FieldDef[] {
                                                                            new StringFieldDef(
                                                                                "State"),
                                                                            new StringFieldDef(
                                                                                "number"),
                                                                        });

                                                                object = new String[result.length][2];

                                                                Object[][] data = object;

                                                                for (int i = 0;
                                                                        i < result.length;
                                                                        i++) {
                                                                    object[i][0] = result[i].getProgram_name();
                                                                    object[i][1] = result[i].getProgram_id();
                                                                }

                                                                MemoryProxy proxy =
                                                                    new MemoryProxy(data);

                                                                ArrayReader reader =
                                                                    new ArrayReader(recordDef);
                                                                Store store = new Store(proxy,
                                                                        reader);
                                                                store.load();

                                                                componentBox.setStore(store);
                                                            }
                                                        });
                                                }
                                            });
                                    }
                                }
                            }
                        });

                    componentBox.addListener(new ComboBoxListenerAdapter() {
                            public void onSelect(ComboBox comboBox,
                                Record record, int index) {
                                int x = 1;
                                connectService.getcomponent(programid, x,
                                    new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                        public void onFailure(Throwable arg0) {
                                        }

                                        public void onSuccess(
                                            CM_ProgramInfoGetter[] result) {
                                            final String component = componentBox.getValue();

                                            for (int i = 0; i < result.length;
                                                    i++) {
                                                String code = result[i].getBranch_code();

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
                                                                            componentBox.reset();
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
                                final String component = componentBox.getValue();


                                if (component == null) {
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
                                                            componentBox.markInvalid(
                                                                "Noo..!!");
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
                                                                connectService.methodinsertfirstdegreecomponents(programid,
                                                                    component,
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
                                                                                                                                componentBox.reset();
                                                                                                                            } else if (btnID.equals(
                                                                                                                                        "no")) {
                                                                                                                                fPanel2.setVisible(false);
                                                                                                                                componentBox.reset();
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

        fPanel2.add(detailsTable);

        flexTable2.setWidget(0, 0, label5);
        flexTable2.setWidget(0, 2, listBox);
        flexTable2.setWidget(2, 0, label10);
        flexTable2.setWidget(2, 2, listBox2);

        //        hPanel.add(label5);
        //        hPanel.setSpacing(20);
        //        hPanel.add(listBox);
        verticalPanel2.add(button);

        formPanel.add(sessionTable);

        panel2.add(defaultTable);
        panel2.setSpacing(10);
        panel2.add(formPanel);
        panel2.add(saveButton);
        scPanel1.add(panel2);

        hPanel2.add(regisTable);
        scPanel.add(hPanel2);
        fPanel.add(scPanel);

        panel.add(flexTable2);

        hPanel3.add(fPanel);
        hPanel3.setSpacing(8);
        hPanel3.add(fPanel2);

        verticalPanel2.add(hPanel3);

        hPanel4.add(panel);

        hPanel4.add(verticalPanel2);

        formPanel3.add(hPanel4);

        label3.setStyleName("panelHeading");
        //        vPanel.add(label3);
        vPanel.add(formPanel3);
    }
}
