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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
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
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
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


public class CM_manageprogramcomponent {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    final HorizontalPanel FacultyPanel = new HorizontalPanel();
    public final HorizontalPanel FacultyPanelRight = new HorizontalPanel();
    FlexTable facultyflexTable = new FlexTable();
    Label sessionText = new Label();
    Label instituteText = new Label();
    Label programname = new Label(constants.label_programname());
    Label termname = new Label(constants.label_compnentname());
    Label categoryseats = new Label(constants.label_componentweightage());
    Label startdate = new Label(constants.label_sequencenumber());
    Label ruleLabel = new Label(constants.label_rule());
    Label enddate = new Label();
    Label category = new Label(constants.label_categorizationtype());
    Label programBox = new Label();
    Label branch = new Label();
    Label termBox = new Label();
    final VerticalPanel fullpage1 = new VerticalPanel();
    Object[][] object1;
    Object[][] object;
    String university_id;
    String type = "";
    String special;
    String boardflag;
    String eligibleflag;
    String weightageflag;
    String weightage;
    CheckBox boardButtonA = new CheckBox(constants.checkbox_board());
    CheckBox weightButtonA = new CheckBox(constants.checknox_specialweightage());
    CheckBox weightBox = new CheckBox(constants.checkbox_weigthage());
    CheckBox eligibleBox = new CheckBox(constants.checkbox_eligibility());
    Validator valid = new Validator();
    HorizontalPanel gridPanel = new HorizontalPanel();
    String pagename = "Program Components";
    String[] values;
    ToolbarButton editButton = new ToolbarButton("Edit");
    ToolbarButton deletebutton = new ToolbarButton("Delete");
    
    CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();


    public CM_manageprogramcomponent(String user_id) {
        this.university_id = user_id;
    }

    /**
     * Main method of class for creating tree for faculties that is to be added
     * in accordion panel with selection handlers. Selection handler call methods
     *  for displaying corresponding pages on right hand side with their functionality.
     */
    public void methodManageprgcomponents() {
        gridPanel.clear();

        final NumberField xfactor = new NumberField();
        final NumberField seats = new NumberField();
        final VerticalPanel fullpage = new VerticalPanel();
        final FormPanel fPanel = new FormPanel();
        final ListBox types = new ListBox();

        final ComboBox ruleBox = new ComboBox();

        xfactor.setAllowDecimals(false);
        xfactor.setAllowNegative(false);

        seats.setAllowNegative(false);
        seats.setMaxLength(6);
        seats.setMaxValue(100);
        seats.setMinValue(1);
        seats.setAllowBlank(false);

        types.addItem("Marks", "M");
        types.addItem("Score", "S");
        types.addItem("Percentage", "P");

        try {
            fullpage.setSize("100%", "100%");
            fullpage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

            fPanel.setFrame(true);
            fPanel.setSize("300px", "100%");
            fPanel.setTitle(constants.heading_manageprogramcomponents());


            final Panel p1 = new Panel();
            final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
            bd.setMargins(6, 6, 6, 6);

            FlexTable table = new FlexTable();

            Label label3 = new Label("Dayalbagh Educational Institute");

            label3.setStyleName("panelHeading");

            final Label entityCriteria = new Label(constants.entityType());
            final Label valueLabel = new Label(constants.label_programname());
            Label entityLabel = new Label(constants.label_entitytype());

            final ListBox entityCriteriaList = new ListBox();
            final ListBox entityList = new ListBox();

            final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
            final SuggestBox ValueSuggest = new SuggestBox(oracle);

            final Button okButton = new Button(constants.okButton());

            //all fields disabled initially
            okButton.setVisible(false);
            entityList.setEnabled(false);
            entityCriteriaList.setEnabled(false);
            cbSelectionModel.lock();
            editButton.setDisabled(true);
            deletebutton.setDisabled(true);

            loginconnect.getAuthorityOnPage(pagename, university_id,
                new AsyncCallback<CM_userInfoGetter[]>() {
                    public void onFailure(Throwable arg0) {
                    }

                    public void onSuccess(CM_userInfoGetter[] result) {
                        if (result[0].getAuthority().equalsIgnoreCase("1")) {
                            loginconnect.getsecondaryauthorities(university_id,
                                pagename,
                                new AsyncCallback<List<String>>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(List<String> result) {
                                        if (result.size() == 0) {
                                            entityList.setEnabled(true);
                                            entityCriteriaList.setEnabled(true);
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
                                                        int rowIndex,
                                                        Record record) {
                                                    	
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
                                                        int rowIndex,
                                                        Record record) {
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
                                                MessageBox.alert("authorities" +
                                                    values[i]);

                                                //                                                if (values[i].equalsIgnoreCase(
                                                //                                                            "create")) {
                                                //                                                }
                                                if (values[i].equalsIgnoreCase(
                                                            "view")) {
                                                    entityList.setEnabled(true);
                                                    entityCriteriaList.setEnabled(true);
                                                }

                                                if (values[i].equalsIgnoreCase(
                                                            "update")) {
                                                    entityList.setEnabled(true);
                                                    entityCriteriaList.setEnabled(true);
                                                    cbSelectionModel.unlock();
                                                    editButton.setDisabled(false);
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
                                                    entityList.setEnabled(true);
                                                    entityCriteriaList.setEnabled(true);
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

            entityList.setWidth("150px");
            entityCriteriaList.setWidth("150px");
            ValueSuggest.setWidth("150px");

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

            weightBox.addClickHandler(new ClickHandler() {
                    @SuppressWarnings("deprecation")
                    public void onClick(ClickEvent arg0) {
                        if (weightBox.isChecked()) {
                            categoryseats.setVisible(true);
                            seats.setVisible(true);
                        } else {
                            categoryseats.setVisible(false);
                            seats.setVisible(false);
                        }
                    }
                });

            connectService.methodentitypopulate(university_id,
                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                    public void onFailure(Throwable arg0) {
                    }

                    public void onSuccess(CM_ProgramInfoGetter[] result) {
                        entityList.clear();
                        entityList.addItem("Select");

                        for (int i = 0; i < result.length; i++) {
                            String type = result[i].getComponent();

                            entityList.addItem(type);
                        }
                    }
                });

            entityList.addChangeHandler(new ChangeHandler() {
                    public void onChange(ChangeEvent arg0) {
                        if (entityList.getValue(entityList.getSelectedIndex())
                                          .equalsIgnoreCase("select")) {
                            entityCriteriaList.clear();
                            okButton.setVisible(false);
                            ValueSuggest.setText("");
                            fullpage1.remove(gridPanel);
                        } else {
                            int systemvalue = 0;
                            String entitytype = entityList.getItemText(entityList.getSelectedIndex());
                            ValueSuggest.setText("");
                            okButton.setVisible(true);
                            fullpage1.remove(gridPanel);

                            connectService.methodgetentitytype(entitytype,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        String type1 = "";

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            type1 = result[i].getEntity_type();
                                        }

                                        connectService.methodgetentity(type1,
                                            university_id,
                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                public void onFailure(
                                                    Throwable arg0) {
                                                }

                                                public void onSuccess(
                                                    CM_ProgramInfoGetter[] result) {
                                                    entityCriteriaList.clear();
                                                    entityCriteriaList.addItem("Select",
                                                        null);

                                                    for (int i = 0;
                                                            i < result.length;
                                                            i++) {
                                                        String name = result[i].getEntity_name();

                                                        entityCriteriaList.addItem(name);
                                                    }
                                                }
                                            });
                                    }
                                });
                            connectService.methodgetentitytypeforprogramcomponent(entitytype,
                                systemvalue, university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        oracle.clear();

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            String type = result[i].getProgram_name();

                                            oracle.add(type);
                                        }
                                    }
                                });
                        }
                    }
                });

            entityCriteriaList.addChangeHandler(new ChangeHandler() {
                    public void onChange(ChangeEvent arg0) {
                        String entitytype = entityCriteriaList.getItemText(entityCriteriaList.getSelectedIndex());
                        ValueSuggest.setText("");

                        int systemvalue = 1;

                        connectService.methodgetentitytypeforprogramcomponent(entitytype,
                            systemvalue, university_id,
                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                public void onFailure(Throwable arg0) {
                                }

                                public void onSuccess(
                                    CM_ProgramInfoGetter[] result) {
                                    oracle.clear();

                                    for (int i = 0; i < result.length; i++) {
                                        String type = result[i].getProgram_name();

                                        oracle.add(type);
                                    }
                                }
                            });
                    }
                });

            table.setWidget(0, 0, entityLabel);
            table.setWidget(0, 1, entityList);
            table.setWidget(2, 0, entityCriteria);
            table.setWidget(2, 1, entityCriteriaList);
            table.setWidget(3, 0, valueLabel);
            table.setWidget(3, 1, ValueSuggest);
            table.setWidget(6, 0, okButton);

            fPanel.add(table);

            fullpage.clear();
            fullpage1.clear();

            fullpage.setSpacing(10);
            fullpage.add(instituteText);
            fullpage.add(sessionText);
            fullpage.add(fPanel);
            fullpage.add(fullpage1);

            facultyflexTable.clear();
            FacultyPanelRight.clear();
            facultyflexTable.setWidget(0, 0, fullpage);
            FacultyPanelRight.add(facultyflexTable);

            // method to be called on click of ok button typically in onSuccess
            okButton.addListener(new ButtonListenerAdapter() {
                    public void onClick(Button button, EventObject e) {
                        final String value = ValueSuggest.getText();
                        final String entitytype = entityList.getItemText(entityList.getSelectedIndex());
                        final String entityname = entityCriteriaList.getItemText(entityCriteriaList.getSelectedIndex());

                        final GridPanel grid = new GridPanel();

                        if (entityname.equalsIgnoreCase("select") &&
                                value.equals("")) {
                            connectService.componentwithentitytype(entitytype,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                        MessageBox.alert("in failure=" + arg0);
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        object1 = new Object[result.length][12];

                                        if (result.length == 0) {
                                            MessageBox.show(new MessageBoxConfig() {

                                                    {
                                                        setTitle(msgs.alert());
                                                        setMsg(msgs.error_norecord(
                                                                entitytype));
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
                                                                        entityList.setSelectedIndex(0);
                                                                        entityCriteriaList.clear();
                                                                        okButton.setVisible(false);
                                                                        ValueSuggest.setText(
                                                                            "");
                                                                    }
                                                                }
                                                            });
                                                    }
                                                });
                                        }

                                        final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Programme Name"),
                                                    new StringFieldDef("Branch Name"),
                                                    new StringFieldDef("Component Name"),
                                                    new StringFieldDef("Type"),
                                                    new StringFieldDef("Component Weightage"),
                                                    new StringFieldDef("Board Flag Status"),
                                                    new StringFieldDef("Weightage Flag Status"),
                                                    new StringFieldDef("Special Weightage Flag Status"),
                                                    new StringFieldDef("Eligibility Flag Status"),
                                                    new StringFieldDef("Sequence Number"),
                                                    new StringFieldDef("Rule used"),
                                                    new StringFieldDef("rule_no"),
                                                });

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 12; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getprogram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getComponent();
                                                    } else if (k == 3) {
                                                        str = result[i].getType();
                                                    } else if (k == 4) {
                                                        str = result[i].getentity_id();
                                                    } else if (k == 5) {
                                                        str = result[i].getBoard_flag();
                                                    } else if (k == 6) {
                                                        str = result[i].getWeightage_flag();
                                                    } else if (k == 7) {
                                                        str = result[i].getSpecial_flag();
                                                    } else if (k == 8) {
                                                        str = result[i].getEligibility_flag();
                                                    } else if (k == 9) {
                                                        str = result[i].getSequence();
                                                    } else if (k == 10) {
                                                        str = result[i].getDescription();
                                                    } else if (k == 11) {
                                                        str = result[i].getRule_no();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println(
                                                        "exception " + e);
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
                                                
                                                new ColumnConfig("Programme Name",
                                                    "Programme Name", 140,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 140, true,
                                                    null, "BranchName"),
                                                new ColumnConfig("Component Name",
                                                    "Component Name", 100,
                                                    true, null, "entityname"),
                                                new ColumnConfig("Type",
                                                    "Type", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Component Weightage",
                                                    "Component Weightage", 120,
                                                    true, null, "entityparent"),
                                                new ColumnConfig("Weightage Flag Status",
                                                    "Weightage Flag Status",
                                                    130, true, null, "criteria"),
                                                new ColumnConfig("Board Flag Status",
                                                    "Board Flag Status", 120,
                                                    true, null, "boardStatus"),
                                                new ColumnConfig("Special Weightage Flag Status",
                                                    "Special Weightage Flag Status",
                                                    120, true, null,
                                                    "specialWeightagetatus"),
                                                new ColumnConfig("Eligibility Flag Status",
                                                    "Eligibility Flag Status",
                                                    120, true, null,
                                                    "eligibilityWeightagetatus"),
                                                new ColumnConfig("Sequence Number",
                                                    "Sequence Number", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Rule",
                                                    "Rule used", 120, true,
                                                    null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");
                                        grid.setAutoExpandColumn("criteria");
                                        grid.setAutoExpandColumn("BranchName");
                                        grid.setAutoExpandColumn("boardStatus");
                                        grid.setAutoExpandColumn(
                                            "specialWeightagetatus");
                                        grid.setAutoExpandColumn(
                                            "eligibilityWeightagetatus");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(1200);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                @SuppressWarnings("deprecation")
                                                public void onClick(
                                                    Button Button, EventObject e) {
                                                    try {
                                                        Record[] records = cbSelectionModel.getSelections();
                                                        String msg = "";

                                                        if (records.length < 1) {
                                                            MessageBox.alert(msgs.error(),
                                                                msgs.error_selectrecord());
                                                        } else if (records.length == 1) {
                                                            Record record = records[0];
                                                            msg += record.getAsString(
                                                                "University Name");

                                                            String[] Univ = new String[12];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Branch Name");
                                                            Univ[2] = record.getAsString(
                                                                    "Component Name");
                                                            Univ[3] = record.getAsString(
                                                                    "Type");
                                                            Univ[4] = record.getAsString(
                                                                    "Component Weightage");
                                                            Univ[5] = record.getAsString(
                                                                    "Board Flag Status");
                                                            Univ[6] = record.getAsString(
                                                                    "Weightage Flag Status");
                                                            Univ[7] = record.getAsString(
                                                                    "Special Weightage Flag Status");
                                                            Univ[8] = record.getAsString(
                                                                    "Eligibility Flag Status");
                                                            Univ[9] = record.getAsString(
                                                                    "Sequence Number");
                                                            Univ[10] = record.getAsString(
                                                                    "Rule used");
                                                            Univ[11] = record.getAsString(
                                                                    "rule_no");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            branch.setText(Univ[1]);
                                                            termBox.setText(Univ[2]);
                                                            type = Univ[3];
                                                            seats.setValue(Univ[4]);
                                                            weightageflag = Univ[6];
                                                            boardflag = Univ[5];
                                                            special = Univ[7];
                                                            eligibleflag = Univ[8];
                                                            enddate.setText(Univ[9]);
                                                            ruleBox.setValue(Univ[11]);

                                                            if (type.equalsIgnoreCase(
                                                                        "M")) {
                                                                types.setSelectedIndex(0);
                                                            } else if (type.equalsIgnoreCase(
                                                                        "S")) {
                                                                types.setSelectedIndex(1);
                                                            } else {
                                                                types.setSelectedIndex(2);
                                                            }

                                                            if (boardflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                boardButtonA.setChecked(true);
                                                            } else {
                                                                boardButtonA.setChecked(false);
                                                            }

                                                            if (special.equalsIgnoreCase(
                                                                        "Y")) {
                                                                weightButtonA.setChecked(true);
                                                            } else {
                                                                weightButtonA.setChecked(false);
                                                            }

                                                            if (eligibleflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                eligibleBox.setChecked(true);
                                                            } else {
                                                                eligibleBox.setChecked(false);
                                                            }

                                                            if (weightageflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                weightBox.setChecked(true);
                                                                categoryseats.setVisible(true);
                                                                seats.setVisible(true);
                                                            } else {
                                                                weightBox.setChecked(false);
                                                                categoryseats.setVisible(false);
                                                                seats.setVisible(false);
                                                            }

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branch);
                                                            flexTable.setWidget(2,
                                                                0, termname);
                                                            flexTable.setWidget(2,
                                                                2, termBox);
                                                            flexTable.setWidget(4,
                                                                0, startdate);
                                                            flexTable.setWidget(4,
                                                                2, enddate);

                                                            editInstiTable.setWidget(0,
                                                                0, category);
                                                            editInstiTable.setWidget(0,
                                                                2, types);
                                                            editInstiTable.setWidget(2,
                                                                0, boardButtonA);
                                                            editInstiTable.setWidget(2,
                                                                2, eligibleBox);
                                                            editInstiTable.setWidget(4,
                                                                0, weightButtonA);
                                                            editInstiTable.setWidget(6,
                                                                0, weightBox);
                                                            editInstiTable.setWidget(8,
                                                                0, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                2, seats);
                                                            editInstiTable.setWidget(10,
                                                                0, ruleLabel);
                                                            editInstiTable.setWidget(10,
                                                                2, ruleBox);

                                                            p1.clear();
                                                            p1.add(flexTable);
                                                            p1.add(editInstiTable);

                                                            Button b1 = new Button(
                                                                    "Update");

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
                                                                        special = "N";
                                                                        boardflag = "N";
                                                                        eligibleflag = "N";
                                                                        weightageflag = "N";
                                                                        weightage = "0";

                                                                        final String tag =
                                                                            types.getValue(types.getSelectedIndex());
                                                                        final String rule =
                                                                            ruleBox.getValue();

                                                                        if (weightBox.isChecked()) {
                                                                            weightageflag = "Y";
                                                                            weightage = seats.getRawValue();
                                                                        }

                                                                        if (weightageflag.equalsIgnoreCase(
                                                                                    "n")) {
                                                                            seats.setValue(1);
                                                                            weightage = "1";
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

                                                                        int b = 0;

                                                                        try {
                                                                            seats.validate();
                                                                        } catch (Exception e1) {
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
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                        } else if (b == 1) {
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
                                                                                                        connectService.methodupdateprgcomponents(programBox.getText(),
                                                                                                            branch.getText(),
                                                                                                            termBox.getText(),
                                                                                                            enddate.getText(),
                                                                                                            tag,
                                                                                                            boardflag,
                                                                                                            eligibleflag,
                                                                                                            special,
                                                                                                            weightageflag,
                                                                                                            weightage,
                                                                                                            rule,
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
                                                                                                                                            if (btnID.equals(
                                                                                                                                                        "ok")) {
                                                                                                                                                window.close();
                                                                                                                                                cbSelectionModel.clearSelections();
                                                                                                                                                okButton.fireEvent(
                                                                                                                                                    "click");
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
                                                                                                        cbSelectionModel.clearSelections();
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                        }
                                                                    }
                                                                });
                                                        }
                                                    } catch (Exception e1) {
                                                    }
                                                }
                                            });

                                        final ToolbarButton deletebutton1 = new ToolbarButton(
                                                "Delete");

                                        deletebutton1.setDisabled(true);

                                        deletebutton = deletebutton1;

                                        deletebutton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button delButton,
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
                                                                                                "Programme Name");
                                                                                        Univ[1] = records[i].getAsString(
                                                                                                "Component Name");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeletecomponentrecord(Univ,
                                                                                            university_id,
                                                                                            new AsyncCallback<String>() {
                                                                                                public void onFailure(
                                                                                                    Throwable arg0) {
                                                                                                    MessageBox.alert("Failure",
                                                                                                        arg0.getMessage());
                                                                                                }

                                                                                                public void onSuccess(
                                                                                                    String arg0) {
                                                                                                    MessageBox.alert(msgs.alert(),
                                                                                                        msgs.alert_ondeletesuccess(),
                                                                                                        new AlertCallback() {
                                                                                                            public void execute() {
                                                                                                                okButton.fireEvent(
                                                                                                                    "click");
                                                                                                                cbSelectionModel.clearSelections();
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

                                        grid.addGridCellListener(new GridCellListener() {
                                                public void onCellClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
                                                    @SuppressWarnings("unused")
                                                    Record record = records[0];
                                                }

                                                public void onCellContextMenu(
                                                    GridPanel grid,
                                                    int rowIndex,
                                                    int cellIndex, EventObject e) {
                                                }

                                                public void onCellDblClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                }
                                            });

                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        } else if (value.equals("")) {
                            connectService.componentwithentityname(entityname,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        try {
                                            object1 = new Object[result.length][12];
                                        } catch (Exception e) {
                                            System.out.println("ex11" + e);
                                        }

                                        if (result.length == 0) {
                                            MessageBox.show(new MessageBoxConfig() {

                                                    {
                                                        setTitle(msgs.alert());
                                                        setMsg(msgs.error_norecord(
                                                                entityname));
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
                                                                        entityList.setSelectedIndex(0);
                                                                        entityCriteriaList.clear();
                                                                        okButton.setVisible(false);
                                                                        ValueSuggest.setText(
                                                                            "");
                                                                    }
                                                                }
                                                            });
                                                    }
                                                });
                                        }

                                        final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Programme Name"),
                                                    new StringFieldDef("Branch Name"),
                                                    new StringFieldDef("Component Name"),
                                                    new StringFieldDef("Type"),
                                                    new StringFieldDef("Component Weightage"),
                                                    new StringFieldDef("Board Flag Status"),
                                                    new StringFieldDef("Weightage Flag Status"),
                                                    new StringFieldDef("Special Weightage Flag Status"),
                                                    new StringFieldDef("Eligibility Flag Status"),
                                                    new StringFieldDef("Sequence Number"),
                                                    new StringFieldDef("Rule used"),
                                                    new StringFieldDef("rule_no"),
                                                });

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 12; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getprogram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getComponent();
                                                    } else if (k == 3) {
                                                        str = result[i].getType();
                                                    } else if (k == 4) {
                                                        str = result[i].getentity_id();
                                                    } else if (k == 5) {
                                                        str = result[i].getBoard_flag();
                                                    } else if (k == 6) {
                                                        str = result[i].getWeightage_flag();
                                                    } else if (k == 7) {
                                                        str = result[i].getSpecial_flag();
                                                    } else if (k == 8) {
                                                        str = result[i].getEligibility_flag();
                                                    } else if (k == 9) {
                                                        str = result[i].getSequence();
                                                    } else if (k == 10) {
                                                        str = result[i].getDescription();
                                                    } else if (k == 11) {
                                                        str = result[i].getRule_no();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println(
                                                        "exception " + e);
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
                                                
                                                new ColumnConfig("Programme Name",
                                                    "Programme Name", 140,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 140, true,
                                                    null, "BranchName"),
                                                new ColumnConfig("Component Name",
                                                    "Component Name", 100,
                                                    true, null, "entityname"),
                                                new ColumnConfig("Type",
                                                    "Type", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Component Weightage",
                                                    "Component Weightage", 120,
                                                    true, null, "entityparent"),
                                                new ColumnConfig("Weightage Flag Status",
                                                    "Weightage Flag Status",
                                                    130, true, null, "criteria"),
                                                new ColumnConfig("Board Flag Status",
                                                    "Board Flag Status", 120,
                                                    true, null, "boardStatus"),
                                                new ColumnConfig("Special Weightage Flag Status",
                                                    "Special Weightage Flag Status",
                                                    120, true, null,
                                                    "specialWeightagetatus"),
                                                new ColumnConfig("Eligibility Flag Status",
                                                    "Eligibility Flag Status",
                                                    120, true, null,
                                                    "eligibilityWeightagetatus"),
                                                new ColumnConfig("Sequence Number",
                                                    "Sequence Number", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Rule",
                                                    "Rule used", 120, true,
                                                    null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");
                                        grid.setAutoExpandColumn("criteria");
                                        grid.setAutoExpandColumn("BranchName");
                                        grid.setAutoExpandColumn("boardStatus");
                                        grid.setAutoExpandColumn(
                                            "specialWeightagetatus");
                                        grid.setAutoExpandColumn(
                                            "eligibilityWeightagetatus");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(1200);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                @SuppressWarnings("deprecation")
                                                public void onClick(
                                                    Button Button, EventObject e) {
                                                    try {
                                                        Record[] records = cbSelectionModel.getSelections();
                                                        String msg = "";

                                                        if (records.length < 1) {
                                                            MessageBox.alert(msgs.error(),
                                                                msgs.error_selectrecord());
                                                        } else if (records.length == 1) {
                                                            Record record = records[0];
                                                            msg += record.getAsString(
                                                                "University Name");

                                                            String[] Univ = new String[12];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Branch Name");
                                                            Univ[2] = record.getAsString(
                                                                    "Component Name");
                                                            Univ[3] = record.getAsString(
                                                                    "Type");
                                                            Univ[4] = record.getAsString(
                                                                    "Component Weightage");
                                                            Univ[5] = record.getAsString(
                                                                    "Board Flag Status");
                                                            Univ[6] = record.getAsString(
                                                                    "Weightage Flag Status");
                                                            Univ[7] = record.getAsString(
                                                                    "Special Weightage Flag Status");
                                                            Univ[8] = record.getAsString(
                                                                    "Eligibility Flag Status");
                                                            Univ[9] = record.getAsString(
                                                                    "Sequence Number");
                                                            Univ[10] = record.getAsString(
                                                                    "Rule used");
                                                            Univ[11] = record.getAsString(
                                                                    "rule_no");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            branch.setText(Univ[1]);
                                                            termBox.setText(Univ[2]);
                                                            type = Univ[3];
                                                            seats.setValue(Univ[4]);
                                                            weightageflag = Univ[6];
                                                            boardflag = Univ[5];
                                                            special = Univ[7];
                                                            eligibleflag = Univ[8];
                                                            enddate.setText(Univ[9]);
                                                            ruleBox.setValue(Univ[11]);

                                                            if (type.equalsIgnoreCase(
                                                                        "M")) {
                                                                types.setSelectedIndex(0);
                                                            } else if (type.equalsIgnoreCase(
                                                                        "S")) {
                                                                types.setSelectedIndex(1);
                                                            } else {
                                                                types.setSelectedIndex(2);
                                                            }

                                                            if (boardflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                boardButtonA.setChecked(true);
                                                            } else {
                                                                boardButtonA.setChecked(false);
                                                            }

                                                            if (special.equalsIgnoreCase(
                                                                        "Y")) {
                                                                weightButtonA.setChecked(true);
                                                            } else {
                                                                weightButtonA.setChecked(false);
                                                            }

                                                            if (eligibleflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                eligibleBox.setChecked(true);
                                                            } else {
                                                                eligibleBox.setChecked(false);
                                                            }

                                                            if (weightageflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                weightBox.setChecked(true);
                                                                categoryseats.setVisible(true);
                                                                seats.setVisible(true);
                                                            } else {
                                                                weightBox.setChecked(false);
                                                                categoryseats.setVisible(false);
                                                                seats.setVisible(false);
                                                            }

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branch);
                                                            flexTable.setWidget(2,
                                                                0, termname);
                                                            flexTable.setWidget(2,
                                                                2, termBox);
                                                            flexTable.setWidget(4,
                                                                0, startdate);
                                                            flexTable.setWidget(4,
                                                                2, enddate);

                                                            editInstiTable.setWidget(0,
                                                                0, category);
                                                            editInstiTable.setWidget(0,
                                                                2, types);
                                                            editInstiTable.setWidget(2,
                                                                0, boardButtonA);
                                                            editInstiTable.setWidget(2,
                                                                2, eligibleBox);
                                                            editInstiTable.setWidget(4,
                                                                0, weightButtonA);
                                                            editInstiTable.setWidget(6,
                                                                0, weightBox);
                                                            editInstiTable.setWidget(8,
                                                                0, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                2, seats);
                                                            editInstiTable.setWidget(10,
                                                                0, ruleLabel);
                                                            editInstiTable.setWidget(10,
                                                                2, ruleBox);

                                                            p1.clear();
                                                            p1.add(flexTable);
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
                                                                        special = "N";
                                                                        boardflag = "N";
                                                                        eligibleflag = "N";
                                                                        weightageflag = "N";
                                                                        weightage = "0";

                                                                        final String rule =
                                                                            ruleBox.getValue();

                                                                        final String tag =
                                                                            types.getValue(types.getSelectedIndex());

                                                                        if (weightBox.isChecked()) {
                                                                            weightageflag = "Y";
                                                                            weightage = seats.getRawValue();
                                                                        }

                                                                        if (weightageflag.equalsIgnoreCase(
                                                                                    "n")) {
                                                                            seats.setValue(1);
                                                                            weightage = "1";
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

                                                                        int b = 0;

                                                                        try {
                                                                            seats.validate();
                                                                        } catch (Exception e1) {
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
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                        } else if (b == 1) {
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
                                                                                                        connectService.methodupdateprgcomponents(programBox.getText(),
                                                                                                            branch.getText(),
                                                                                                            termBox.getText(),
                                                                                                            enddate.getText(),
                                                                                                            tag,
                                                                                                            boardflag,
                                                                                                            eligibleflag,
                                                                                                            special,
                                                                                                            weightageflag,
                                                                                                            weightage,
                                                                                                            rule,
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
                                                                                                                                                okButton.fireEvent(
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
                                                                                                        cbSelectionModel.clearSelections();
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                        }
                                                                    }
                                                                });
                                                        }
                                                    } catch (Exception e1) {
                                                    }
                                                }
                                            });

                                        final ToolbarButton deletebutton1 = new ToolbarButton(
                                                "Delete");

                                        deletebutton1.setDisabled(true);

                                        deletebutton = deletebutton1;

                                        deletebutton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button delButton,
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
                                                                                                "Programme Name");
                                                                                        Univ[1] = records[i].getAsString(
                                                                                                "Component Name");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeletecomponentrecord(Univ,
                                                                                            university_id,
                                                                                            new AsyncCallback<String>() {
                                                                                                public void onFailure(
                                                                                                    Throwable arg0) {
                                                                                                    MessageBox.alert("Failure",
                                                                                                        arg0.getMessage());
                                                                                                }

                                                                                                public void onSuccess(
                                                                                                    String arg0) {
                                                                                                    MessageBox.alert(msgs.alert(),
                                                                                                        msgs.alert_ondeletesuccess(),
                                                                                                        new AlertCallback() {
                                                                                                            public void execute() {
                                                                                                                okButton.fireEvent(
                                                                                                                    "click");
                                                                                                                cbSelectionModel.clearSelections();
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

                                        grid.addGridCellListener(new GridCellListener() {
                                                public void onCellClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
                                                    @SuppressWarnings("unused")
                                                    Record record = records[0];
                                                }

                                                public void onCellContextMenu(
                                                    GridPanel grid,
                                                    int rowIndex,
                                                    int cellIndex, EventObject e) {
                                                }

                                                public void onCellDblClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                }
                                            });

                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        } else if (value != ("")) {
                            connectService.componentwithprogramname(value,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Programme Name"),
                                                    new StringFieldDef("Branch Name"),
                                                    new StringFieldDef("Component Name"),
                                                    new StringFieldDef("Type"),
                                                    new StringFieldDef("Component Weightage"),
                                                    new StringFieldDef("Board Flag Status"),
                                                    new StringFieldDef("Weightage Flag Status"),
                                                    new StringFieldDef("Special Weightage Flag Status"),
                                                    new StringFieldDef("Eligibility Flag Status"),
                                                    new StringFieldDef("Sequence Number"),
                                                    new StringFieldDef("Rule used"),
                                                    new StringFieldDef("rule_no"),
                                                });

                                        final GridPanel grid = new GridPanel();

                                        try {
                                            object1 = new Object[result.length][12];
                                        } catch (Exception e) {
                                            System.out.println("ex1 =" + e);
                                        }

                                        if (result.length == 0) {
                                            MessageBox.show(new MessageBoxConfig() {

                                                    {
                                                        setTitle(msgs.alert());
                                                        setMsg(msgs.error_norecord(
                                                                value));
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
                                                                        entityList.setSelectedIndex(0);
                                                                        entityCriteriaList.clear();
                                                                        okButton.setVisible(false);
                                                                        ValueSuggest.setText(
                                                                            "");
                                                                    }
                                                                }
                                                            });
                                                    }
                                                });
                                        }

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 12; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getprogram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getComponent();
                                                    } else if (k == 3) {
                                                        str = result[i].getType();
                                                    } else if (k == 4) {
                                                        str = result[i].getentity_id();
                                                    } else if (k == 5) {
                                                        str = result[i].getBoard_flag();
                                                    } else if (k == 6) {
                                                        str = result[i].getWeightage_flag();
                                                    } else if (k == 7) {
                                                        str = result[i].getSpecial_flag();
                                                    } else if (k == 8) {
                                                        str = result[i].getEligibility_flag();
                                                    } else if (k == 9) {
                                                        str = result[i].getSequence();
                                                    } else if (k == 10) {
                                                        str = result[i].getDescription();
                                                    } else if (k == 11) {
                                                        str = result[i].getRule_no();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println(
                                                        "exception " + e);
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
                                                
                                                new ColumnConfig("Programme Name",
                                                    "Programme Name", 140,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 140, true,
                                                    null, "BranchName"),
                                                new ColumnConfig("Component Name",
                                                    "Component Name", 100,
                                                    true, null, "entityname"),
                                                new ColumnConfig("Type",
                                                    "Type", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Component Weightage",
                                                    "Component Weightage", 120,
                                                    true, null, "entityparent"),
                                                new ColumnConfig("Weightage Flag Status",
                                                    "Weightage Flag Status",
                                                    130, true, null, "criteria"),
                                                new ColumnConfig("Board Flag Status",
                                                    "Board Flag Status", 120,
                                                    true, null, "boardStatus"),
                                                new ColumnConfig("Special Weightage Flag Status",
                                                    "Special Weightage Flag Status",
                                                    120, true, null,
                                                    "specialWeightagetatus"),
                                                new ColumnConfig("Eligibility Flag Status",
                                                    "Eligibility Flag Status",
                                                    120, true, null,
                                                    "eligibilityWeightagetatus"),
                                                new ColumnConfig("Sequence Number",
                                                    "Sequence Number", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Rule",
                                                    "Rule used", 120, true,
                                                    null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");
                                        grid.setAutoExpandColumn("criteria");
                                        grid.setAutoExpandColumn("BranchName");
                                        grid.setAutoExpandColumn("boardStatus");
                                        grid.setAutoExpandColumn(
                                            "specialWeightagetatus");
                                        grid.setAutoExpandColumn(
                                            "eligibilityWeightagetatus");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(1200);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                @SuppressWarnings("deprecation")
                                                public void onClick(
                                                    Button Button, EventObject e) {
                                                    try {
                                                        Record[] records = cbSelectionModel.getSelections();
                                                        String msg = "";

                                                        if (records.length < 1) {
                                                            MessageBox.alert(msgs.error(),
                                                                msgs.error_selectrecord());
                                                        } else if (records.length == 1) {
                                                            Record record = records[0];
                                                            msg += record.getAsString(
                                                                "University Name");

                                                            String[] Univ = new String[12];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Branch Name");
                                                            Univ[2] = record.getAsString(
                                                                    "Component Name");
                                                            Univ[3] = record.getAsString(
                                                                    "Type");
                                                            Univ[4] = record.getAsString(
                                                                    "Component Weightage");
                                                            Univ[5] = record.getAsString(
                                                                    "Board Flag Status");
                                                            Univ[6] = record.getAsString(
                                                                    "Weightage Flag Status");
                                                            Univ[7] = record.getAsString(
                                                                    "Special Weightage Flag Status");
                                                            Univ[8] = record.getAsString(
                                                                    "Eligibility Flag Status");
                                                            Univ[9] = record.getAsString(
                                                                    "Sequence Number");
                                                            Univ[10] = record.getAsString(
                                                                    "Rule used");
                                                            Univ[11] = record.getAsString(
                                                                    "rule_no");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            branch.setText(Univ[1]);
                                                            termBox.setText(Univ[2]);
                                                            type = Univ[3];
                                                            seats.setValue(Univ[4]);
                                                            weightageflag = Univ[6];
                                                            boardflag = Univ[5];
                                                            special = Univ[7];
                                                            eligibleflag = Univ[8];
                                                            enddate.setText(Univ[9]);
                                                            ruleBox.setValue(Univ[11]);

                                                            if (type.equalsIgnoreCase(
                                                                        "M")) {
                                                                types.setSelectedIndex(0);
                                                            } else if (type.equalsIgnoreCase(
                                                                        "S")) {
                                                                types.setSelectedIndex(1);
                                                            } else {
                                                                types.setSelectedIndex(2);
                                                            }

                                                            if (boardflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                boardButtonA.setChecked(true);
                                                            } else {
                                                                boardButtonA.setChecked(false);
                                                            }

                                                            if (special.equalsIgnoreCase(
                                                                        "Y")) {
                                                                weightButtonA.setChecked(true);
                                                            } else {
                                                                weightButtonA.setChecked(false);
                                                            }

                                                            if (eligibleflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                eligibleBox.setChecked(true);
                                                            } else {
                                                                eligibleBox.setChecked(false);
                                                            }

                                                            if (weightageflag.equalsIgnoreCase(
                                                                        "Y")) {
                                                                weightBox.setChecked(true);
                                                                categoryseats.setVisible(true);
                                                                seats.setVisible(true);
                                                            } else {
                                                                weightBox.setChecked(false);
                                                                categoryseats.setVisible(false);
                                                                seats.setVisible(false);
                                                            }

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branch);
                                                            flexTable.setWidget(2,
                                                                0, termname);
                                                            flexTable.setWidget(2,
                                                                2, termBox);
                                                            flexTable.setWidget(4,
                                                                0, startdate);
                                                            flexTable.setWidget(4,
                                                                2, enddate);

                                                            editInstiTable.setWidget(0,
                                                                0, category);
                                                            editInstiTable.setWidget(0,
                                                                2, types);
                                                            editInstiTable.setWidget(2,
                                                                0, boardButtonA);
                                                            editInstiTable.setWidget(2,
                                                                2, eligibleBox);
                                                            editInstiTable.setWidget(4,
                                                                0, weightButtonA);
                                                            editInstiTable.setWidget(6,
                                                                0, weightBox);
                                                            editInstiTable.setWidget(8,
                                                                0, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                2, seats);
                                                            editInstiTable.setWidget(10,
                                                                0, ruleLabel);
                                                            editInstiTable.setWidget(10,
                                                                2, ruleBox);

                                                            p1.clear();
                                                            p1.add(flexTable);
                                                            p1.add(editInstiTable);

                                                            Button b1 = new Button(
                                                                    "Update");

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
                                                                        special = "N";
                                                                        boardflag = "N";
                                                                        eligibleflag = "N";
                                                                        weightageflag = "N";
                                                                        weightage = "0";

                                                                        final String tag =
                                                                            types.getValue(types.getSelectedIndex());

                                                                        final String rule =
                                                                            ruleBox.getValue();

                                                                        if (weightBox.isChecked()) {
                                                                            weightageflag = "Y";
                                                                            weightage = seats.getRawValue();
                                                                        }

                                                                        if (weightageflag.equalsIgnoreCase(
                                                                                    "n")) {
                                                                            seats.setValue(1);
                                                                            weightage = "1";
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

                                                                        int b = 0;

                                                                        try {
                                                                            seats.validate();
                                                                        } catch (Exception e1) {
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
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                        } else if (b == 1) {
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
                                                                                                        connectService.methodupdateprgcomponents(programBox.getText(),
                                                                                                            branch.getText(),
                                                                                                            termBox.getText(),
                                                                                                            enddate.getText(),
                                                                                                            tag,
                                                                                                            boardflag,
                                                                                                            eligibleflag,
                                                                                                            special,
                                                                                                            weightageflag,
                                                                                                            weightage,
                                                                                                            rule,
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
                                                                                                                                                okButton.fireEvent(
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
                                                                                                        cbSelectionModel.clearSelections();
                                                                                                    }
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                });
                                                                        }
                                                                    }
                                                                });
                                                        }
                                                    } catch (Exception e1) {
                                                    }
                                                }
                                            });

                                        final ToolbarButton deletebutton1 = new ToolbarButton(
                                                "Delete");

                                        deletebutton1.setDisabled(true);

                                        deletebutton = deletebutton1;

                                        deletebutton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button delButton,
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
                                                                                                "Programme Name");
                                                                                        Univ[1] = records[i].getAsString(
                                                                                                "Component Name");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeletecomponentrecord(Univ,
                                                                                            university_id,
                                                                                            new AsyncCallback<String>() {
                                                                                                public void onFailure(
                                                                                                    Throwable arg0) {
                                                                                                    MessageBox.alert("Failure",
                                                                                                        arg0.getMessage());
                                                                                                }

                                                                                                public void onSuccess(
                                                                                                    String arg0) {
                                                                                                    MessageBox.alert(msgs.alert(),
                                                                                                        msgs.alert_ondeletesuccess(),
                                                                                                        new AlertCallback() {
                                                                                                            public void execute() {
                                                                                                                okButton.fireEvent(
                                                                                                                    "click");
                                                                                                                cbSelectionModel.clearSelections();
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

                                        grid.addGridCellListener(new GridCellListener() {
                                                public void onCellClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
                                                    @SuppressWarnings("unused")
                                                    Record record = records[0];
                                                }

                                                public void onCellContextMenu(
                                                    GridPanel grid,
                                                    int rowIndex,
                                                    int cellIndex, EventObject e) {
                                                }

                                                public void onCellDblClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                }
                                            });

                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        } else {
                            MessageBox.alert("select an entity");
                        }
                    }
                });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
