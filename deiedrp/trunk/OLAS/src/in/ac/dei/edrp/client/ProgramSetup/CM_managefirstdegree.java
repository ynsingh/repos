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

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.HashMap;
import java.util.List;


@SuppressWarnings("unused")
public class CM_managefirstdegree {
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
    final VerticalPanel fullpage1 = new VerticalPanel();
    Object[][] object1;
    String university_id;
    String type = "";
    final ListBox entityList = new ListBox();
    HorizontalPanel gridPanel = new HorizontalPanel();
    String pagename = "Program Pre Requiste Examinations(First Degree)";
    String[] values;
    ToolbarButton editButton = new ToolbarButton("Edit");
    ToolbarButton deletebutton = new ToolbarButton("Delete");

    public CM_managefirstdegree(String userid) {
        this.university_id = userid;
    }

    /**
     * Main method of class for creating tree for faculties that is to be added
     * in accordion panel with selection handlers. Selection handler call methods
     *  for displaying corresponding pages on right hand side with their functionality.
     */
    public void methodManagefirstdegree() {
        gridPanel.clear();

        final NumberField xfactor = new NumberField();
        final NumberField seats = new NumberField();
        final VerticalPanel fullpage = new VerticalPanel();
        final FormPanel fPanel = new FormPanel();

        xfactor.setAllowDecimals(false);
        xfactor.setAllowNegative(false);

        seats.setAllowDecimals(false);
        seats.setAllowNegative(false);

        try {
            fullpage.setSize("100%", "100%");
            fullpage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

            fPanel.setFrame(true);
            fPanel.setSize("300px", "100%");
            fPanel.setTitle(constants.heading_managefirstdegree());

            final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();

            final Panel p1 = new Panel();
            final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
            bd.setMargins(6, 6, 6, 6);

            FlexTable table = new FlexTable();

            Label label3 = new Label("Dayalbagh Educational Institute");
            label3.setStyleName("panelHeading");

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
//                                                MessageBox.alert("authorities" +
//                                                    values[i]);

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

            connectService.methodentitypopulate(university_id,
                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                    public void onFailure(Throwable arg0) {
                    }

                    public void onSuccess(CM_ProgramInfoGetter[] result) {
                        entityList.clear();
                        entityList.addItem("Select");

                        for (int i = 0; i < result.length; i++) {
                            String type = result[i].getComponentDescription();
                            String id = result[i].getComponentId();

                            entityList.addItem(type, id);
                        }
                    }
                });

            entityList.addChangeHandler(new ChangeHandler() {
                    public void onChange(ChangeEvent arg0) {
                        if (entityList.getItemText(
                                    entityList.getSelectedIndex())
                                          .equalsIgnoreCase("Select")) {
                            entityCriteriaList.clear();
                            okButton.setVisible(false);
                            ValueSuggest.setText("");
                            oracle.clear();
                            fullpage1.remove(gridPanel);
                        } else {
                            String entitytype = entityList.getValue(entityList.getSelectedIndex());
                            ValueSuggest.setText("");
                            okButton.setVisible(true);
                            fullpage1.remove(gridPanel);

                            int systemvalue = 1;

                            connectService.methodgetentitytype(entitytype,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        String type1 = "";
                                        String entity_name = "";

                                        entityCriteriaList.clear();
                                        entityCriteriaList.addItem("Select",
                                            null);

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            type1 = result[i].getEntity_id();
                                            entity_name = result[i].getEntity_name();

                                            entityCriteriaList.addItem(entity_name,
                                                type1);
                                        }
                                    }
                                });

                            connectService.methodgetentitytypeforfirstdegree(entitytype,
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
                        String entitytype = entityCriteriaList.getValue(entityCriteriaList.getSelectedIndex());
                        ValueSuggest.setText("");

                        int systemvalue = 0;

                        connectService.methodgetentitytypeforfirstdegree(entitytype,
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
                        final String entitytype = entityList.getValue(entityList.getSelectedIndex());
                        final String entityname = entityCriteriaList.getValue(entityCriteriaList.getSelectedIndex());

                        if (entityCriteriaList.getItemText(
                                    entityCriteriaList.getSelectedIndex())
                                                  .equalsIgnoreCase("Select") &&
                                value.equals("")) {
                            connectService.degreewithentitytype(entitytype,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final GridPanel grid = new GridPanel();
                                        object1 = new Object[result.length][4];

                                        if (result.length == 0) {
                                            MessageBox.show(new MessageBoxConfig() {

                                                    {
                                                        setTitle(msgs.alert());
                                                        setMsg(msgs.error_norecord(
                                                                entityList.getItemText(
                                                                    entityList.getSelectedIndex())));
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
                                                    new StringFieldDef("Component Name"),
                                                    new StringFieldDef("Programme Id"),
                                                    new StringFieldDef("Component Id"),
                                                });

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 4; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getComponent();
                                                    }

                                                    if (k == 2) {
                                                        str = result[i].getProgram_id();
                                                    } else if (k == 3) {
                                                        str = result[i].getComponent_id();
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
                                                    "Programme Name", 180,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Component Name",
                                                    "Component Name", 180,
                                                    true, null, "entityname"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("entityname");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(500);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

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
                                                                                            new String[2];

                                                                                        Univ[0] = records[i].getAsString(
                                                                                                "Programme Id");
                                                                                        Univ[1] = records[i].getAsString(
                                                                                                "Component Id");

                                                                                        connectService.methodDeletedegreerecord(Univ,
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
                                        topToolBar.addButton(deletebutton);

                                        grid.setTopToolbar(topToolBar);

                                     /*   grid.addGridCellListener(new GridCellListener() {
                                                public void onCellClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
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
                                            });*/

                                        grid.setTitle(constants.heading_programdetails());

                                        gridPanel.clear();
                                        gridPanel.add(grid);

                                        fullpage1.add(gridPanel);
                                    }
                                });
                        } else if (value.equals("")) {
                            connectService.degreewithentityname(entityname,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final GridPanel grid = new GridPanel();

                                        try {
                                            object1 = new Object[result.length][4];
                                        } catch (Exception e) {
                                            System.out.println("ex11" + e);
                                        }

                                        if (result.length == 0) {
                                            MessageBox.show(new MessageBoxConfig() {

                                                    {
                                                        setTitle(msgs.alert());
                                                        setMsg(msgs.error_norecord(
                                                                entityCriteriaList.getItemText(
                                                                    entityCriteriaList.getSelectedIndex())));
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
                                                    new StringFieldDef("Component Name"),
                                                    new StringFieldDef("Programme Id"),
                                                    new StringFieldDef("Component Id"),
                                                });

                                        Object str = null;

                                        for (int i = 0; i < result.length;
                                                i++) {
                                            for (int k = 0; k < 4; k++) {
                                                try {
                                                    if (k == 0) {
                                                        str = result[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = result[i].getComponent();
                                                    }

                                                    if (k == 2) {
                                                        str = result[i].getProgram_id();
                                                    } else if (k == 3) {
                                                        str = result[i].getComponent_id();
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
                                                    "Programme Name", 180,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Component Name",
                                                    "Component Name", 180,
                                                    true, null, "entityname"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("entityname");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(500);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

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
                                                                                            new String[2];

                                                                                        Univ[0] = records[i].getAsString(
                                                                                                "Programme Id");
                                                                                        Univ[1] = records[i].getAsString(
                                                                                                "Component Id");

                                                                                        connectService.methodDeletedegreerecord(Univ,
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
                                        topToolBar.addButton(deletebutton);

                                        grid.setTopToolbar(topToolBar);

                                        grid.addGridCellListener(new GridCellListener() {
                                                public void onCellClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
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
                            connectService.degreewithprogramname(value,
                                university_id,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] result) {
                                        final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Programme Name"),
                                                    new StringFieldDef("Component Name"),
                                                    new StringFieldDef("Programme Id"),
                                                    new StringFieldDef("Component Id"),
                                                });

                                        final GridPanel grid = new GridPanel();

                                        try {
                                            object1 = new Object[result.length][4];
                                        } catch (Exception e) {
                                            System.out.println("ex1 = " + e);
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
                                            for (int k = 0; k < 4; k++) {
                                                if (k == 0) {
                                                    str = result[i].getProgram_name();
                                                } else if (k == 1) {
                                                    str = result[i].getComponent();
                                                }

                                                if (k == 2) {
                                                    str = result[i].getProgram_id();
                                                } else if (k == 3) {
                                                    str = result[i].getComponent_id();
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
                                                    "Programme Name", 180,
                                                    true, null, "entityType"),
                                                new ColumnConfig("Component Name",
                                                    "Component Name", 180,
                                                    true, null, "entityname"),
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entityType");
                                        grid.setAutoExpandColumn("entityname");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(500);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

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
                                                                                            new String[2];

                                                                                        Univ[0] = records[i].getAsString(
                                                                                                "Programme Id");
                                                                                        Univ[1] = records[i].getAsString(
                                                                                                "Component Id");

                                                                                        connectService.methodDeletedegreerecord(Univ,
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
                                        topToolBar.addButton(deletebutton);

                                        grid.setTopToolbar(topToolBar);

                                        grid.addGridCellListener(new GridCellListener() {
                                                public void onCellClick(
                                                    GridPanel grid,
                                                    int rowIndex, int colIndex,
                                                    EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();
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
                        }
                    }
                });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
