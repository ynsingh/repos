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
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.DateField;
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
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.Date;
import java.util.List;


public class CM_managecutoffcall {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    final HorizontalPanel FacultyPanel = new HorizontalPanel();
    public final HorizontalPanel FacultyPanelRight = new HorizontalPanel();
    FlexTable facultyflexTable = new FlexTable();
    Label sessionText = new Label();
    Label instituteText = new Label();
    ListBox fnameText = new ListBox();
    Label programname = new Label(constants.label_programname());
    Label termname = new Label("Category Value");
    Label startdateLabel = new Label(constants.label_xfactor());
    Label categoryseats = new Label(constants.label_categoryseats());
    Label startdate = new Label(constants.label_sessionstartdate());
    Label enddate = new Label(constants.label_sessionenddate());
//    DateField startField = new DateField();
//    DateField endField = new DateField();
    final DateItem startField = new DateItem();
    final DateItem endField = new DateItem();
    
    Label programBox = new Label();
    Label branchLabel = new Label();
    Label termBox = new Label();
    final VerticalPanel fullpage1 = new VerticalPanel();
    Object[][] object1;
    String university_id;
    String type = "";
    final ListBox entityList = new ListBox();
    HorizontalPanel gridPanel = new HorizontalPanel();
    Validator valid = new Validator();
    String pagename = "Program COS";
    String[] values;
    ToolbarButton editButton = new ToolbarButton("Edit");
    ToolbarButton deletebutton = new ToolbarButton("Delete");

    public CM_managecutoffcall(String uniid) {
        this.university_id = uniid;
    }

    public void methodManageindivisuallist() {
        gridPanel.clear();

        final String settings = "I";

        final NumberField xfactor = new NumberField();
        final NumberField seats = new NumberField();
        final VerticalPanel fullpage = new VerticalPanel();
        final FormPanel fPanel = new FormPanel();

        xfactor.setAllowDecimals(false);
        xfactor.setAllowNegative(false);
        xfactor.setAllowBlank(false);
        xfactor.setMinValue(1);
        xfactor.setMaxLength(2);

        seats.setAllowDecimals(false);
        seats.setAllowNegative(false);
        seats.setAllowBlank(false);
        seats.setMinValue(1);
        seats.setMaxLength(3);

       // startField.setAllowBlank(false);
       // endField.setAllowBlank(false);
        
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


        try {
            fullpage.setSize("100%", "100%");
            fullpage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

            fPanel.setFrame(true);
            fPanel.setSize("300px", "100%");
            fPanel.setTitle("Manage Individual COS setup");

            final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();

            final Panel p1 = new Panel();
            final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
            bd.setMargins(6, 6, 6, 6);

            FlexTable table = new FlexTable();

            Label label3 = new Label("Dayalbagh Educational Institute");
            //            Label label4 = new Label("2010-11");
            label3.setStyleName("panelHeading");

            //            label4.setStyleName("panelHeading");
            final Label entityCriteria = new Label(constants.entityName());
            final Label valueLabel = new Label(constants.label_programname());
            Label entityLabel = new Label(constants.label_entitytype());

            final ListBox entityCriteriaList = new ListBox();

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
                            oracle.clear();
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
                                                    entityCriteriaList.addItem(
                                                        "Select");

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

                            connectService.methodgetentitydetails(entitytype,
                                settings, systemvalue, university_id,
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

                        connectService.methodgetentitydetails(entitytype,
                            settings, systemvalue, university_id,
                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                public void onFailure(Throwable arg0) {
                                }

                                public void onSuccess(
                                    CM_ProgramInfoGetter[] result) {
                                    //                                    oracle.clear();
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
            //            fullpage.add(label3);
            //            fullpage.add(label4);
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

                        if (entityname.equalsIgnoreCase("select") &&
                                value.equals("")) {
                            connectService.callwithentitytype(entitytype,
                                settings, university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final GridPanel grid = new GridPanel();
                                        object1 = new Object[result.length][7];

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
                                                    new StringFieldDef("Category Value"),
                                                    new StringFieldDef("X-factor"),
                                                    new StringFieldDef("Category Seats"),
                                                    new StringFieldDef("Session Start Date"),
                                                    new StringFieldDef("Session End Date"),
                                                });

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 7; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getCos_value();
                                                    } else if (k == 3) {
                                                        str = result[i].getNo_of_times();
                                                    } else if (k == 4) {
                                                        str = result[i].getNo_of_seats();
                                                    } else if (k == 5) {
                                                        str = result[i].getSession_start_date();
                                                    } else if (k == 6) {
                                                        str = result[i].getSession_end_date();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("ex " +
                                                        e);
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
                                                    "Programme Name", 100,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 90, true,
                                                    null, "branchname"),
                                                new ColumnConfig("Category Value",
                                                    "Category Value", 90, true,
                                                    null, "entityname"),
                                                new ColumnConfig("X-factor",
                                                    "X-factor", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Category Seats",
                                                    "Category Seats", 90, true,
                                                    null, "entityparent"),
                                                new ColumnConfig("Session Start Date",
                                                    "Session Start Date", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Session End Date",
                                                    "Session End Date", 100,
                                                    true, null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("branchname");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(700);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
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

                                                            String[] Univ = new String[7];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Category Value");
                                                            Univ[2] = record.getAsString(
                                                                    "X-factor");
                                                            Univ[3] = record.getAsString(
                                                                    "Category Seats");
                                                            Univ[4] = record.getAsString(
                                                                    "Session Start Date");
                                                            Univ[5] = record.getAsString(
                                                                    "Session End Date");
                                                            Univ[6] = record.getAsString(
                                                                    "Branch Name");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            branchLabel.setText(Univ[6]);
                                                            termBox.setText(Univ[1]);
                                                            xfactor.setValue(Univ[2]);
                                                            seats.setValue(Univ[3]);
                                                            startField.setValue(Univ[4]);
                                                            endField.setValue(Univ[5]);

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branchLabel);
                                                            editInstiTable.setWidget(4,
                                                                0, termname);
                                                            editInstiTable.setWidget(4,
                                                                2, termBox);
                                                            editInstiTable.setWidget(8,
                                                                0,
                                                                startdateLabel);
                                                            editInstiTable.setWidget(8,
                                                                2, xfactor);
                                                            editInstiTable.setWidget(8,
                                                                4, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                6, seats);
                                                            editInstiTable.setWidget(12,
                                                                0, startdate);
                                                            editInstiTable.setWidget(12,
                                                                2, startdateFieldContainer);
                                                            editInstiTable.setWidget(12,
                                                                4, enddate);
                                                            editInstiTable.setWidget(12,
                                                                6, enddateFieldContainer);

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
                                                                        int x = 0;
                                                                        int y = 0;

                                                                        int a = 0;
                                                                        int b = 0;

                                                                        connectService.getSeatsCos(programBox.getText(),
                                                                            branchLabel.getText(),
                                                                            university_id,
                                                                            termBox.getText(),
                                                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                public void onFailure(
                                                                                    Throwable arg0) {
                                                                                }

                                                                                public void onSuccess(
                                                                                    CM_ProgramInfoGetter[] arg0) {
                                                                                    String cos_seats =
                                                                                        arg0[0].getNo_of_seats();
                                                                                    String seatsvalue =
                                                                                        seats.getValue()
                                                                                             .toString();

                                                                                    final int totalcsoseats =
                                                                                        Integer.parseInt(cos_seats) +
                                                                                        Integer.parseInt(seatsvalue);

                                                                                    System.out.println(
                                                                                        "seats" +
                                                                                        totalcsoseats);

                                                                                    connectService.getseats(programBox.getText(),
                                                                                        branchLabel.getText(),
                                                                                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                            public void onFailure(
                                                                                                Throwable arg0) {
                                                                                            }

                                                                                            public void onSuccess(
                                                                                                CM_ProgramInfoGetter[] arg0) {
                                                                                                int totatseats =
                                                                                                    Integer.parseInt(arg0[0].getNo_of_seats());

                                                                                                if (totalcsoseats > totatseats) {
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
                                                                                                                                seats.setValue(1);
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
                                                                            xfactor.validate();
                                                                        } catch (Exception e2) {
                                                                            a = 1;
                                                                        }

                                                                        try {
                                                                            seats.validate();
                                                                        } catch (Exception e1) {
                                                                            b = 1;
                                                                        }

                                                                        if ((x == 1) ||
                                                                                (y == 1) ||
                                                                                (a == 1) ||
                                                                                (b == 1)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(msgs.checkFields());
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (valid.datechecker1((Date)startField.getValue(), (Date) endField.getValue())) {
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
                                                                                        setMsg(msgs.alert_onedit());
                                                                                        setIconCls(MessageBox.QUESTION);
                                                                                        setButtons(MessageBox.YESNO);
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                    if (btnID.equals(
                                                                                                                "yes")) {
                                                                                                        connectService.methodupdatecutoffdetails(programBox.getText(),
                                                                                                            termBox.getText(),
                                                                                                            xfactor.getValue()
                                                                                                                   .toString(),
                                                                                                            seats.getValue()
                                                                                                                 .toString(),
                                                                                                                startField.getDisplayValue(),
                                                                                                                endField.getDisplayValue(),
                                                                                                            
                                                                                                            branchLabel.getText(),
                                                                                                            university_id,
                                                                                                            new AsyncCallback<String>() {
                                                                                                                public void onFailure(
                                                                                                                    Throwable arg0) {
                                                                                                                }

                                                                                                                public void onSuccess(
                                                                                                                    String result) {
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
                                                                                                "Category Value");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeleterecord(Univ,
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

                                        //                                        cbSelectionModel.addListener(new RowSelectionListener() {
                                        //                                                public boolean doBeforeRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex,
                                        //                                                    boolean keepExisting,
                                        //                                                    Record record) {
                                        //                                                    return true;
                                        //                                                }
                                        //
                                        //                                                public void onRowDeselect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onSelectionChange(
                                        //                                                    RowSelectionModel sm) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //                                            });
                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        } else if (value.equals("")) {
                            connectService.callwithentityname(entityname,
                                settings, university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final GridPanel grid = new GridPanel();

                                        try {
                                            object1 = new Object[result.length][7];
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
                                                    new StringFieldDef("Category Value"),
                                                    new StringFieldDef("X-factor"),
                                                    new StringFieldDef("Category Seats"),
                                                    new StringFieldDef("Session Start Date"),
                                                    new StringFieldDef("Session End Date"),
                                                });

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 7; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getCos_value();
                                                    } else if (k == 3) {
                                                        str = result[i].getNo_of_times();
                                                    } else if (k == 4) {
                                                        str = result[i].getNo_of_seats();
                                                    } else if (k == 5) {
                                                        str = result[i].getSession_start_date();
                                                    } else if (k == 6) {
                                                        str = result[i].getSession_end_date();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("ex " +
                                                        e);
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
                                                    "Programme Name", 100,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 90, true,
                                                    null, "branchname"),
                                                new ColumnConfig("Category Value",
                                                    "Category Value", 90, true,
                                                    null, "entityname"),
                                                new ColumnConfig("X-factor",
                                                    "X-factor", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Category Seats",
                                                    "Category Seats", 90, true,
                                                    null, "entityparent"),
                                                new ColumnConfig("Session Start Date",
                                                    "Session Start Date", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Session End Date",
                                                    "Session End Date", 100,
                                                    true, null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("branchname");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(700);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button Button, EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
                                                    Record record = records[0];
                                                    String msg = "";

                                                    if (records.length < 1) {
                                                        MessageBox.alert(msgs.error(),
                                                            msgs.error_selectrecord());
                                                    } else if (records.length == 1) {
                                                        try {
                                                            msg += record.getAsString(
                                                                "University Name");

                                                            String[] Univ = new String[7];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Category Value");
                                                            Univ[2] = record.getAsString(
                                                                    "X-factor");
                                                            Univ[3] = record.getAsString(
                                                                    "Category Seats");
                                                            Univ[4] = record.getAsString(
                                                                    "Session Start Date");
                                                            Univ[5] = record.getAsString(
                                                                    "Session End Date");
                                                            Univ[6] = record.getAsString(
                                                                    "Branch Name");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            branchLabel.setText(Univ[6]);
                                                            termBox.setText(Univ[1]);
                                                            xfactor.setValue(Univ[2]);
                                                            seats.setValue(Univ[3]);
                                                            startField.setValue(Univ[4]);
                                                            endField.setValue(Univ[5]);

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branchLabel);
                                                            editInstiTable.setWidget(4,
                                                                0, termname);
                                                            editInstiTable.setWidget(4,
                                                                2, termBox);
                                                            editInstiTable.setWidget(8,
                                                                0,
                                                                startdateLabel);
                                                            editInstiTable.setWidget(8,
                                                                2, xfactor);
                                                            editInstiTable.setWidget(8,
                                                                4, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                6, seats);
                                                            editInstiTable.setWidget(12,
                                                                0, startdate);
                                                            editInstiTable.setWidget(12,
                                                                2, startdateFieldContainer);
                                                            editInstiTable.setWidget(12,
                                                                4, enddate);
                                                            editInstiTable.setWidget(12,
                                                                6, enddateFieldContainer);

                                                            p1.clear();
                                                            p1.add(flexTable);
                                                            p1.add(editInstiTable);

                                                            Button b1 = new Button(
                                                                    "Update");

                                                            final Window window = new Window();
                                                            window.setTitle(
                                                                "Update Deadlines");
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
                                                                        int x = 0;
                                                                        int y = 0;

                                                                        int a = 0;
                                                                        int b = 0;

                                                                        connectService.getSeatsCos(programBox.getText(),
                                                                            branchLabel.getText(),
                                                                            university_id,
                                                                            termBox.getText(),
                                                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                public void onFailure(
                                                                                    Throwable arg0) {
                                                                                }

                                                                                public void onSuccess(
                                                                                    CM_ProgramInfoGetter[] arg0) {
                                                                                    String cos_seats =
                                                                                        arg0[0].getNo_of_seats();
                                                                                    String seatsvalue =
                                                                                        seats.getValue()
                                                                                             .toString();

                                                                                    final int totalcsoseats =
                                                                                        Integer.parseInt(cos_seats) +
                                                                                        Integer.parseInt(seatsvalue);

                                                                                    connectService.getseats(programBox.getText(),
                                                                                        branchLabel.getText(),
                                                                                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                            public void onFailure(
                                                                                                Throwable arg0) {
                                                                                            }

                                                                                            public void onSuccess(
                                                                                                CM_ProgramInfoGetter[] arg0) {
                                                                                                int totatseats =
                                                                                                    Integer.parseInt(arg0[0].getNo_of_seats());

                                                                                                if (totatseats > totalcsoseats) {
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
                                                                                                                                seats.setValue(1);
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
                                                                            xfactor.validate();
                                                                        } catch (Exception e2) {
                                                                            a = 1;
                                                                        }

                                                                        try {
                                                                            seats.validate();
                                                                        } catch (Exception e1) {
                                                                            b = 1;
                                                                        }

                                                                        if ((x == 1) ||
                                                                                (y == 1) ||
                                                                                (a == 1) ||
                                                                                (b == 1)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(msgs.checkFields());
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (valid.datechecker1((Date)startField.getValue(), (Date) endField.getValue())) {
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
                                                                                        setMsg(msgs.alert_onedit());
                                                                                        setIconCls(MessageBox.QUESTION);
                                                                                        setButtons(MessageBox.YESNO);
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                    if (btnID.equals(
                                                                                                                "yes")) {
                                                                                                        connectService.methodupdatecutoffdetails(programBox.getText(),
                                                                                                            termBox.getText(),
                                                                                                            xfactor.getValue()
                                                                                                                   .toString(),
                                                                                                            seats.getValue()
                                                                                                                 .toString(),
                                                                                                                startField.getDisplayValue(),
                                                                                                                endField.getDisplayValue(),
                                                                                                            branchLabel.getText(),
                                                                                                            university_id,
                                                                                                            new AsyncCallback<String>() {
                                                                                                                public void onFailure(
                                                                                                                    Throwable arg0) {
                                                                                                                }

                                                                                                                public void onSuccess(
                                                                                                                    String result) {
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
                                                                                                "Category Value");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeleterecord(Univ,
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

                                        //                                        cbSelectionModel.addListener(new RowSelectionListener() {
                                        //                                                public boolean doBeforeRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex,
                                        //                                                    boolean keepExisting,
                                        //                                                    Record record) {
                                        //                                                    return true;
                                        //                                                }
                                        //
                                        //                                                public void onRowDeselect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onSelectionChange(
                                        //                                                    RowSelectionModel sm) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //                                            });
                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        } else {
                            connectService.callwithprogramname(value, settings,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Programme Name"),
                                                    new StringFieldDef("Branch Name"),
                                                    new StringFieldDef("Category Value"),
                                                    new StringFieldDef("X-factor"),
                                                    new StringFieldDef("Category Seats"),
                                                    new StringFieldDef("Session Start Date"),
                                                    new StringFieldDef("Session End Date"),
                                                });

                                        final GridPanel grid = new GridPanel();

                                        try {
                                            object1 = new Object[result.length][7];
                                        } catch (Exception e) {
                                            System.out.println("ex1" + e);
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
                                            for (int k = 0; k < 7; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getCos_value();
                                                    } else if (k == 3) {
                                                        str = result[i].getNo_of_times();
                                                    } else if (k == 4) {
                                                        str = result[i].getNo_of_seats();
                                                    } else if (k == 5) {
                                                        str = result[i].getSession_start_date();
                                                    } else if (k == 6) {
                                                        str = result[i].getSession_end_date();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("ex " +
                                                        e);
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
                                                    "Programme Name", 100,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 90, true,
                                                    null, "branchname"),
                                                new ColumnConfig("Category Value",
                                                    "Category Value", 90, true,
                                                    null, "entityname"),
                                                new ColumnConfig("X-factor",
                                                    "X-factor", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Category Seats",
                                                    "Category Seats", 90, true,
                                                    null, "entityparent"),
                                                new ColumnConfig("Session Start Date",
                                                    "Session Start Date", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Session End Date",
                                                    "Session End Date", 100,
                                                    true, null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("branchname");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(700);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button Button, EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
                                                    Record record = records[0];
                                                    String msg = "";

                                                    if (records.length < 1) {
                                                        MessageBox.alert(msgs.error(),
                                                            msgs.error_selectrecord());
                                                    } else if (records.length == 1) {
                                                        try {
                                                            msg += record.getAsString(
                                                                "University Name");

                                                            String[] Univ = new String[7];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Category Value");
                                                            Univ[2] = record.getAsString(
                                                                    "X-factor");
                                                            Univ[3] = record.getAsString(
                                                                    "Category Seats");
                                                            Univ[4] = record.getAsString(
                                                                    "Session Start Date");
                                                            Univ[5] = record.getAsString(
                                                                    "Session End Date");
                                                            Univ[6] = record.getAsString(
                                                                    "Branch Name");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            branchLabel.setText(Univ[6]);
                                                            termBox.setText(Univ[1]);
                                                            xfactor.setValue(Univ[2]);
                                                            seats.setValue(Univ[3]);
                                                            startField.setValue(Univ[4]);
                                                            endField.setValue(Univ[5]);

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branchLabel);
                                                            editInstiTable.setWidget(4,
                                                                0, termname);
                                                            editInstiTable.setWidget(4,
                                                                2, termBox);
                                                            editInstiTable.setWidget(8,
                                                                0,
                                                                startdateLabel);
                                                            editInstiTable.setWidget(8,
                                                                2, xfactor);
                                                            editInstiTable.setWidget(8,
                                                                4, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                6, seats);
                                                            editInstiTable.setWidget(12,
                                                                0, startdate);
                                                            editInstiTable.setWidget(12,
                                                                2, startdateFieldContainer);
                                                            editInstiTable.setWidget(12,
                                                                4, enddate);
                                                            editInstiTable.setWidget(12,
                                                                6, enddateFieldContainer);

                                                            p1.clear();
                                                            p1.add(flexTable);
                                                            p1.add(editInstiTable);

                                                            Button b1 = new Button(
                                                                    "Update");

                                                            final Window window = new Window();
                                                            window.setTitle(
                                                                "Update Deadlines");
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
                                                                        int x = 0;
                                                                        int y = 0;

                                                                        int a = 0;
                                                                        int b = 0;

                                                                        connectService.getSeatsCos(programBox.getText(),
                                                                            branchLabel.getText(),
                                                                            university_id,
                                                                            termBox.getText(),
                                                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                public void onFailure(
                                                                                    Throwable arg0) {
                                                                                }

                                                                                public void onSuccess(
                                                                                    CM_ProgramInfoGetter[] arg0) {
                                                                                    String cos_seats =
                                                                                        arg0[0].getNo_of_seats();
                                                                                    String seatsvalue =
                                                                                        seats.getValue()
                                                                                             .toString();

                                                                                    final int totalcsoseats =
                                                                                        Integer.parseInt(cos_seats) +
                                                                                        Integer.parseInt(seatsvalue);

                                                                                    connectService.getseats(programBox.getText(),
                                                                                        branchLabel.getText(),
                                                                                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                            public void onFailure(
                                                                                                Throwable arg0) {
                                                                                            }

                                                                                            public void onSuccess(
                                                                                                CM_ProgramInfoGetter[] arg0) {
                                                                                                int totatseats =
                                                                                                    Integer.parseInt(arg0[0].getNo_of_seats());

                                                                                                if (totatseats > totalcsoseats) {
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
                                                                                                                                seats.setValue(1);
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
                                                                            xfactor.validate();
                                                                        } catch (Exception e2) {
                                                                            a = 1;
                                                                        }

                                                                        try {
                                                                            seats.validate();
                                                                        } catch (Exception e1) {
                                                                            b = 1;
                                                                        }

                                                                        if ((x == 1) ||
                                                                                (y == 1) ||
                                                                                (a == 1) ||
                                                                                (b == 1)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(msgs.checkFields());
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (valid.datechecker1((Date)startField.getValue(), (Date) endField.getValue())) {
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
                                                                                        setMsg(msgs.alert_onedit());
                                                                                        setIconCls(MessageBox.QUESTION);
                                                                                        setButtons(MessageBox.YESNO);
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                    if (btnID.equals(
                                                                                                                "yes")) {
                                                                                                        connectService.methodupdatecutoffdetails(programBox.getText(),
                                                                                                            termBox.getText(),
                                                                                                            xfactor.getValue()
                                                                                                                   .toString(),
                                                                                                            seats.getValue()
                                                                                                                 .toString(),
                                                                                                                startField.getDisplayValue(),
                                                                                                                endField.getDisplayValue(),
                                                                                                            branchLabel.getText(),
                                                                                                            university_id,
                                                                                                            new AsyncCallback<String>() {
                                                                                                                public void onFailure(
                                                                                                                    Throwable arg0) {
                                                                                                                }

                                                                                                                public void onSuccess(
                                                                                                                    String result) {
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
                                                                                                "Category Value");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeleterecord(Univ,
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

                                        //                                        cbSelectionModel.addListener(new RowSelectionListener() {
                                        //                                                public boolean doBeforeRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex,
                                        //                                                    boolean keepExisting,
                                        //                                                    Record record) {
                                        //                                                    return true;
                                        //                                                }
                                        //
                                        //                                                public void onRowDeselect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onSelectionChange(
                                        //                                                    RowSelectionModel sm) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //                                            });
                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        }
                    }
                });
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void methodmanagedefaultlist() {
        final String settings = "D";

        gridPanel.clear();

        final Label xfactor = new Label();
        final Label seats = new Label();
        final VerticalPanel fullpage = new VerticalPanel();
        final FormPanel fPanel = new FormPanel();

        //startField.setAllowBlank(false);
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


        try {
            fullpage.setSize("100%", "100%");
            fullpage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

            fPanel.setFrame(true);
            fPanel.setSize("300px", "100%");
            fPanel.setTitle("Manage Default COS setup");

            final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();

            final Panel p1 = new Panel();
            final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
            bd.setMargins(6, 6, 6, 6);

            FlexTable table = new FlexTable();

            Label label3 = new Label("Dayalbagh Educational Institute");

            label3.setStyleName("panelHeading");

            final Label entityCriteria = new Label("Entity Name ");
            final Label valueLabel = new Label("Program Name");
            Label entityLabel = new Label("Choose Entity Type *");

            final ListBox entityCriteriaList = new ListBox();

            final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
            final SuggestBox ValueSuggest = new SuggestBox(oracle);

            final Button okButton = new Button("OK");

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
                                                    entityCriteriaList.addItem(
                                                        "Select");

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

                            connectService.methodgetentitydetails(entitytype,
                                settings, systemvalue, university_id,
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

                        connectService.methodgetentitydetails(entitytype,
                            settings, systemvalue, university_id,
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
            //            fullpage.add(label3);
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

                        if (entityname.equalsIgnoreCase("select") &&
                                value.equals("")) {
                            connectService.callwithentitytype(entitytype,
                                settings, university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final GridPanel grid = new GridPanel();
                                        object1 = new Object[result.length][7];

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
                                                    new StringFieldDef("Category Value"),
                                                    new StringFieldDef("X-factor"),
                                                    new StringFieldDef("Category Seats"),
                                                    new StringFieldDef("Session Start Date"),
                                                    new StringFieldDef("Session End Date"),
                                                });

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 7; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getCos_value();
                                                    } else if (k == 3) {
                                                        str = result[i].getNo_of_times();
                                                    } else if (k == 4) {
                                                        str = result[i].getNo_of_seats();
                                                    } else if (k == 5) {
                                                        str = result[i].getSession_start_date();
                                                    } else if (k == 6) {
                                                        str = result[i].getSession_end_date();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("ex " +
                                                        e);
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
                                                    "Programme Name", 100,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 90, true,
                                                    null, "branchname"),
                                                new ColumnConfig("Category Value",
                                                    "Category Value", 90, true,
                                                    null, "entityname"),
                                                new ColumnConfig("X-factor",
                                                    "X-factor", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Category Seats",
                                                    "Category Seats", 90, true,
                                                    null, "entityparent"),
                                                new ColumnConfig("Session Start Date",
                                                    "Session Start Date", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Session End Date",
                                                    "Session End Date", 100,
                                                    true, null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("branchname");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(700);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
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

                                                            String[] Univ = new String[7];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Category Value");
                                                            Univ[2] = record.getAsString(
                                                                    "X-factor");
                                                            Univ[3] = record.getAsString(
                                                                    "Category Seats");
                                                            Univ[4] = record.getAsString(
                                                                    "Session Start Date");
                                                            Univ[5] = record.getAsString(
                                                                    "Session End Date");
                                                            Univ[6] = record.getAsString(
                                                                    "Branch Name");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            termBox.setText(Univ[1]);
                                                            xfactor.setText(Univ[2]);
                                                            seats.setText(Univ[3]);
                                                            startField.setValue(Univ[4]);
                                                            endField.setValue(Univ[5]);
                                                            branchLabel.setText(Univ[6]);

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branchLabel);
                                                            editInstiTable.setWidget(4,
                                                                0, termname);
                                                            editInstiTable.setWidget(4,
                                                                2, termBox);
                                                            editInstiTable.setWidget(8,
                                                                0,
                                                                startdateLabel);
                                                            editInstiTable.setWidget(8,
                                                                2, xfactor);
                                                            editInstiTable.setWidget(8,
                                                                4, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                6, seats);
                                                            editInstiTable.setWidget(12,
                                                                0, startdate);
                                                            editInstiTable.setWidget(12,
                                                                2, startdateFieldContainer);
                                                            editInstiTable.setWidget(12,
                                                                4, enddate);
                                                            editInstiTable.setWidget(12,
                                                                6, enddateFieldContainer);

                                                            p1.clear();
                                                            p1.add(flexTable);
                                                            p1.add(editInstiTable);

                                                            Button b1 = new Button(
                                                                    "Update");

                                                            final Window window = new Window();
                                                            window.setTitle(
                                                                "Update Deadlines");
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
                                                                        int x = 0;
                                                                        int y = 0;

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

                                                                        if ((x == 1) ||
                                                                                (y == 1)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(msgs.checkFields());
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (xfactor.getText()
                                                                                              .equals(null)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(
                                                                                            "Feilds cannot be Empty");
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (valid.datechecker1((Date)startField.getValue(), (Date) endField.getValue())) {
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
                                                                                        setMsg(msgs.alert_onedit());
                                                                                        setIconCls(MessageBox.QUESTION);
                                                                                        setButtons(MessageBox.YESNO);
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                    if (btnID.equals(
                                                                                                                "yes")) {
                                                                                                        connectService.methodupdatecutoffdetails(programBox.getText(),
                                                                                                            termBox.getText(),
                                                                                                            xfactor.getText()
                                                                                                                   .toString(),
                                                                                                            seats.getText()
                                                                                                                 .toString(),
                                                                                                                startField.getDisplayValue(),
                                                                                                                endField.getDisplayValue(),
                                                                                                            branchLabel.getText(),
                                                                                                            university_id,
                                                                                                            new AsyncCallback<String>() {
                                                                                                                public void onFailure(
                                                                                                                    Throwable arg0) {
                                                                                                                }

                                                                                                                public void onSuccess(
                                                                                                                    String result) {
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
                                                                                                "Category Value");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeleterecord(Univ,
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

                                        //                                        cbSelectionModel.addListener(new RowSelectionListener() {
                                        //                                                public boolean doBeforeRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex,
                                        //                                                    boolean keepExisting,
                                        //                                                    Record record) {
                                        //                                                    return true;
                                        //                                                }
                                        //
                                        //                                                public void onRowDeselect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onSelectionChange(
                                        //                                                    RowSelectionModel sm) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //                                            });
                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        } else if (value.equals("")) {
                            connectService.callwithentityname(entityname,
                                settings, university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final GridPanel grid = new GridPanel();

                                        try {
                                            object1 = new Object[result.length][7];
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
                                                    new StringFieldDef("Category Value"),
                                                    new StringFieldDef("X-factor"),
                                                    new StringFieldDef("Category Seats"),
                                                    new StringFieldDef("Session Start Date"),
                                                    new StringFieldDef("Session End Date"),
                                                });

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 7; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getCos_value();
                                                    } else if (k == 3) {
                                                        str = result[i].getNo_of_times();
                                                    } else if (k == 4) {
                                                        str = result[i].getNo_of_seats();
                                                    } else if (k == 5) {
                                                        str = result[i].getSession_start_date();
                                                    } else if (k == 6) {
                                                        str = result[i].getSession_end_date();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("ex " +
                                                        e);
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
                                                    "Programme Name", 100,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 90, true,
                                                    null, "branchname"),
                                                new ColumnConfig("Category Value",
                                                    "Category Value", 90, true,
                                                    null, "entityname"),
                                                new ColumnConfig("X-factor",
                                                    "X-factor", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Category Seats",
                                                    "Category Seats", 90, true,
                                                    null, "entityparent"),
                                                new ColumnConfig("Session Start Date",
                                                    "Session Start Date", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Session End Date",
                                                    "Session End Date", 100,
                                                    true, null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("branchname");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(700);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button Button, EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
                                                    Record record = records[0];
                                                    String msg = "";

                                                    if (records.length < 1) {
                                                        MessageBox.alert(msgs.error(),
                                                            msgs.error_selectrecord());
                                                    } else if (records.length == 1) {
                                                        try {
                                                            msg += record.getAsString(
                                                                "University Name");

                                                            String[] Univ = new String[7];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Category Value");
                                                            Univ[2] = record.getAsString(
                                                                    "X-factor");
                                                            Univ[3] = record.getAsString(
                                                                    "Category Seats");
                                                            Univ[4] = record.getAsString(
                                                                    "Session Start Date");
                                                            Univ[5] = record.getAsString(
                                                                    "Session End Date");
                                                            Univ[6] = record.getAsString(
                                                                    "Branch Name");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            termBox.setText(Univ[1]);
                                                            xfactor.setText(Univ[2]);
                                                            seats.setText(Univ[3]);
                                                            startField.setValue(Univ[4]);
                                                            endField.setValue(Univ[5]);
                                                            branchLabel.setText(Univ[6]);

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branchLabel);
                                                            editInstiTable.setWidget(4,
                                                                0, termname);
                                                            editInstiTable.setWidget(4,
                                                                2, termBox);
                                                            editInstiTable.setWidget(8,
                                                                0,
                                                                startdateLabel);
                                                            editInstiTable.setWidget(8,
                                                                2, xfactor);
                                                            editInstiTable.setWidget(8,
                                                                4, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                6, seats);
                                                            editInstiTable.setWidget(12,
                                                                0, startdate);
                                                            editInstiTable.setWidget(12,
                                                                2, startdateFieldContainer);
                                                            editInstiTable.setWidget(12,
                                                                4, enddate);
                                                            editInstiTable.setWidget(12,
                                                                6, enddateFieldContainer);

                                                            p1.clear();
                                                            p1.add(flexTable);
                                                            p1.add(editInstiTable);

                                                            Button b1 = new Button(
                                                                    "Update");

                                                            final Window window = new Window();
                                                            window.setTitle(
                                                                "Update Deadlines");
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
                                                                        int x = 0;
                                                                        int y = 0;

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

                                                                        if ((x == 1) ||
                                                                                (y == 1)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(msgs.checkFields());
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (xfactor.getText()
                                                                                              .equals(null)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(
                                                                                            "Feilds cannot be Empty");
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (valid.datechecker1((Date)startField.getValue(), (Date) endField.getValue())) {
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
                                                                                        setMsg(msgs.alert_onedit());
                                                                                        setIconCls(MessageBox.QUESTION);
                                                                                        setButtons(MessageBox.YESNO);
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                    if (btnID.equals(
                                                                                                                "yes")) {
                                                                                                        connectService.methodupdatecutoffdetails(programBox.getText(),
                                                                                                            termBox.getText(),
                                                                                                            xfactor.getText()
                                                                                                                   .toString(),
                                                                                                            seats.getText()
                                                                                                                 .toString(),
                                                                                                                startField.getDisplayValue(),
                                                                                                                endField.getDisplayValue(),

                                                                                                            branchLabel.getText(),
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
                                                                                                "Category Value");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeleterecord(Univ,
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

                                        //                                        cbSelectionModel.addListener(new RowSelectionListener() {
                                        //                                                public boolean doBeforeRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex,
                                        //                                                    boolean keepExisting,
                                        //                                                    Record record) {
                                        //                                                    return true;
                                        //                                                }
                                        //
                                        //                                                public void onRowDeselect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onSelectionChange(
                                        //                                                    RowSelectionModel sm) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //                                            });
                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        } else {
                            connectService.callwithprogramname(value, settings,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Programme Name"),
                                                    new StringFieldDef("Branch Name"),
                                                    new StringFieldDef("Category Value"),
                                                    new StringFieldDef("X-factor"),
                                                    new StringFieldDef("Category Seats"),
                                                    new StringFieldDef("Session Start Date"),
                                                    new StringFieldDef("Session End Date"),
                                                });

                                        final GridPanel grid = new GridPanel();

                                        try {
                                            object1 = new Object[result.length][7];
                                        } catch (Exception e) {
                                            System.out.println("ex1" + e);
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
                                            for (int k = 0; k < 7; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getBranch_name();
                                                    } else if (k == 2) {
                                                        str = result[i].getCos_value();
                                                    } else if (k == 3) {
                                                        str = result[i].getNo_of_times();
                                                    } else if (k == 4) {
                                                        str = result[i].getNo_of_seats();
                                                    } else if (k == 5) {
                                                        str = result[i].getSession_start_date();
                                                    } else if (k == 6) {
                                                        str = result[i].getSession_end_date();
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("ex " +
                                                        e);
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
                                                    "Programme Name", 100,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Branch Name",
                                                    "Branch Name", 90, true,
                                                    null, "branchname"),
                                                new ColumnConfig("Category Value",
                                                    "Category Value", 90, true,
                                                    null, "entityname"),
                                                new ColumnConfig("X-factor",
                                                    "X-factor", 60, true, null,
                                                    "entitycode"),
                                                new ColumnConfig("Category Seats",
                                                    "Category Seats", 90, true,
                                                    null, "entityparent"),
                                                new ColumnConfig("Session Start Date",
                                                    "Session Start Date", 100,
                                                    true, null, "startdate"),
                                                new ColumnConfig("Session End Date",
                                                    "Session End Date", 100,
                                                    true, null, "enddate"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("branchname");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entitycode");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("startdate");
                                        grid.setAutoExpandColumn("enddate");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(700);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        final ToolbarButton editButton1 = new ToolbarButton(
                                                "Edit");
                                        editButton1.setDisabled(true);

                                        editButton = editButton1;

                                        editButton1.addListener(new ButtonListenerAdapter() {
                                                public void onClick(
                                                    Button Button, EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
                                                    Record record = records[0];
                                                    String msg = "";

                                                    if (records.length < 1) {
                                                        MessageBox.alert(msgs.error(),
                                                            msgs.error_selectrecord());
                                                    } else if (records.length == 1) {
                                                        try {
                                                            msg += record.getAsString(
                                                                "University Name");

                                                            String[] Univ = new String[7];

                                                            Univ[0] = record.getAsString(
                                                                    "Programme Name");
                                                            Univ[1] = record.getAsString(
                                                                    "Category Value");
                                                            Univ[2] = record.getAsString(
                                                                    "X-factor");
                                                            Univ[3] = record.getAsString(
                                                                    "Category Seats");
                                                            Univ[4] = record.getAsString(
                                                                    "Session Start Date");
                                                            Univ[5] = record.getAsString(
                                                                    "Session End Date");
                                                            Univ[6] = record.getAsString(
                                                                    "Branch Name");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            FlexTable flexTable = new FlexTable();

                                                            editInstiTable.clear();

                                                            programBox.setText(Univ[0]);
                                                            termBox.setText(Univ[1]);
                                                            xfactor.setText(Univ[2]);
                                                            seats.setText(Univ[3]);
                                                            startField.setValue(Univ[4]);
                                                            endField.setValue(Univ[5]);
                                                            branchLabel.setText(Univ[6]);

                                                            flexTable.setWidget(0,
                                                                0, programname);
                                                            flexTable.setWidget(0,
                                                                2, programBox);
                                                            flexTable.setWidget(0,
                                                                4, branchLabel);
                                                            editInstiTable.setWidget(4,
                                                                0, termname);
                                                            editInstiTable.setWidget(4,
                                                                2, termBox);
                                                            editInstiTable.setWidget(8,
                                                                0,
                                                                startdateLabel);
                                                            editInstiTable.setWidget(8,
                                                                2, xfactor);
                                                            editInstiTable.setWidget(8,
                                                                4, categoryseats);
                                                            editInstiTable.setWidget(8,
                                                                6, seats);
                                                            editInstiTable.setWidget(12,
                                                                0, startdate);
                                                            editInstiTable.setWidget(12,
                                                                2, startdateFieldContainer);
                                                            editInstiTable.setWidget(12,
                                                                4, enddate);
                                                            editInstiTable.setWidget(12,
                                                                6, enddateFieldContainer);

                                                            p1.clear();
                                                            p1.add(flexTable);
                                                            p1.add(editInstiTable);

                                                            Button b1 = new Button(
                                                                    "Update");

                                                            final Window window = new Window();
                                                            window.setTitle(
                                                                "Update Deadlines");
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
                                                                        int x = 0;
                                                                        int y = 0;

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

                                                                        if ((x == 1) ||
                                                                                (y == 1)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(msgs.checkFields());
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (xfactor.getText()
                                                                                              .equals(null)) {
                                                                            MessageBox.show(new MessageBoxConfig() {

                                                                                    {
                                                                                        setTitle(msgs.error());
                                                                                        setMsg(
                                                                                            "Feilds cannot be Empty");
                                                                                        setIconCls(MessageBox.ERROR);
                                                                                        setButtons(MessageBox.OK);
                                                                                    }
                                                                                });
                                                                        } else if (valid.datechecker1((Date)startField.getValue(), (Date) endField.getValue())) {
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
                                                                                        setMsg(msgs.alert_onedit());
                                                                                        setIconCls(MessageBox.QUESTION);
                                                                                        setButtons(MessageBox.YESNO);
                                                                                        setCallback(new MessageBox.PromptCallback() {
                                                                                                public void execute(
                                                                                                    String btnID,
                                                                                                    String text) {
                                                                                                    if (btnID.equals(
                                                                                                                "yes")) {
                                                                                                        connectService.methodupdatecutoffdetails(programBox.getText(),
                                                                                                            termBox.getText(),
                                                                                                            xfactor.getText()
                                                                                                                   .toString(),
                                                                                                            seats.getText()
                                                                                                                 .toString(),
                                                                                                                startField.getDisplayValue(),
                                                                                                                endField.getDisplayValue(),
                                                                                                            branchLabel.getText(),
                                                                                                            university_id,
                                                                                                            new AsyncCallback<String>() {
                                                                                                                public void onFailure(
                                                                                                                    Throwable arg0) {
                                                                                                                }

                                                                                                                public void onSuccess(
                                                                                                                    String result) {
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
                                                                                                "Category Value");

                                                                                        Univ[2] = records[i].getAsString(
                                                                                                "Branch Name");

                                                                                        connectService.methodDeleterecord(Univ,
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

                                        //                                        cbSelectionModel.addListener(new RowSelectionListener() {
                                        //                                                public boolean doBeforeRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex,
                                        //                                                    boolean keepExisting,
                                        //                                                    Record record) {
                                        //                                                    return true;
                                        //                                                }
                                        //
                                        //                                                public void onRowDeselect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onRowSelect(
                                        //                                                    RowSelectionModel sm,
                                        //                                                    int rowIndex, Record record) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //
                                        //                                                public void onSelectionChange(
                                        //                                                    RowSelectionModel sm) {
                                        //                                                    if (sm.getCount() > 1) {
                                        //                                                        editButton.setDisabled(true);
                                        //                                                    } else {
                                        //                                                        editButton.setDisabled(false);
                                        //                                                    }
                                        //                                                }
                                        //                                            });
                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        }
                    }
                });
        } catch (Exception e) {
            System.out.println(e);
        }
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
