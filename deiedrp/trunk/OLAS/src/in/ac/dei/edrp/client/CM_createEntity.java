package in.ac.dei.edrp.client;

import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
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
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextArea;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
/**
 * @author Manpreet
 */

/**
 * This class creates Faculty tab for institute administrator containing
 * methods for adding, editing, deletion and view faculty details.
 */
public class CM_createEntity {
    private final CM_connectAsync connectService = GWT.create(CM_connect.class);

    private final constants constant=GWT.create(constants.class);
	private final messages message=GWT.create(messages.class);
	
    //	    private final CM_connectDAsync connectDService = GWT.create(CM_connectD.class);
    Validator validator = new Validator();
    String entityType = null;
    final HorizontalPanel FacultyPanel = new HorizontalPanel();
    Tree mainTree = new Tree();
    TreeItem facultytree = new TreeItem(constant.titleFacultyTree());
    TreeItem headTree = new TreeItem(constant.titleHeadTree());
    TreeItem adminTree = new TreeItem(constant.titleAdminTree());
    public final HorizontalPanel FacultyPanelRight = new HorizontalPanel();
    FlexTable facultyflexTable = new FlexTable();
    String empty = "";
    String oldFAUserID = "";
    int headEditIndex = -1;
    int adminEditIndex = -1;
    Label sessionText = new Label();
    Label instituteText = new Label();
    Label instituteName = new Label(constant.labelInstituteName());
    Label sessionLabel = new Label(constant.labelSessionName());
    
    Label fTypeLabel = new Label(constant.emptyEntityName());
   
    Label fIDLabel = new Label(constant.labelFaculty());
    Label fOtherPhoneLabel = new Label(constant.labelOtherPhone());
    Label headIDLabel = new Label(constant.labelHead());
    
 
    Label parentLabel1 = new Label(constant.labelChangeParent());
    Object[][] object1;
    String[][] object2;
    String[][] parentObject;
    String str1 = null;
    CM_entityInfoGetter entityInfo = new CM_entityInfoGetter();
    final ListBox entityTypeList = new ListBox();
    final ComboBox entityParentList = new ComboBox();
    public String userid;

    //Constructor for setting the Value of User ID 
    public CM_createEntity(String Uid) {
        this.userid = Uid;
    }

    /**
     * Main method of class for creating tree for faculties that is to be added
     * in accordion panel with selection handlers. Selection handler call methods
     *  for displaying corresponding pages on right hand side with their functionality.
     */
    public void faculty() {
        instituteText.setStyleName("heading1");
        sessionText.setStyleName("heading1");

        TreeItem FacultyAdd = new TreeItem(constant.labelFacultyAdd());
        TreeItem FacultyEdit = new TreeItem(constant.labelFacultyEdit());
        TreeItem FacultyDelete = new TreeItem(constant.labelFacultyDelete());
        TreeItem FacultyView = new TreeItem(constant.labelFacultyView());

        TreeItem headFacultyAdd = new TreeItem(constant.labelHeadFacultyAdd());
        TreeItem headFacultyEdit = new TreeItem(constant.labelHeadFacultyEdit());

        TreeItem adminFacultyAdd = new TreeItem(constant.labelAdminFacultyAdd());
        TreeItem adminFacultyEdit = new TreeItem(constant.labelAdminFacultyEdit());

        facultytree.addItem(FacultyAdd);
        facultytree.addItem(FacultyEdit);
        facultytree.addItem(FacultyDelete);
        facultytree.addItem(FacultyView);

        headTree.addItem(headFacultyAdd);
        headTree.addItem(headFacultyEdit);

        adminTree.addItem(adminFacultyAdd);
        adminTree.addItem(adminFacultyEdit);

        mainTree.addItem(facultytree);
        mainTree.addItem(headTree);
        mainTree.addItem(adminTree);

        FacultyPanel.add(mainTree);

        //	        mainTree.addSelectionHandler(new SelectionHandler() {
        //	                public void onSelection(SelectionEvent event) {
        //	                    String check = "Add Entity";
        //	                    String checkd = "Delete Faculty";
        //	                    String checkv = "View Faculty List";
        //	                    String checke = "Edit Faculty";
        //	                    String acheck = "Add Faculty Admin";
        //	                    String achecke = "Edit Faculty Admin";
        //	                    String hcheck = "Add Faculty Head";
        //	                    String hchecke = "Edit Faculty Head";
        //
        //	                    if (check.equals(
        //	                                ((TreeItem) (event.getSelectedItem())).getText())) {
        //	                        methodAddEntity();
        //	                    } else if (checkd.equals(
        //	                                ((TreeItem) (event.getSelectedItem())).getText())) {
        ////	                        methodDeleteFaculty();
        //	                    } else if (checkv.equals(
        //	                                ((TreeItem) (event.getSelectedItem())).getText())) {
        ////	                        methodViewFaculty();
        //	                    } else if (checke.equals(
        //	                                ((TreeItem) (event.getSelectedItem())).getText())) {
        ////	                        methodEditFaculty();
        //	                    } else if (acheck.equals(
        //	                                ((TreeItem) (event.getSelectedItem())).getText())) {
        ////	                        addFacultyAdmin();
        //	                    } else if (achecke.equals(
        //	                                ((TreeItem) (event.getSelectedItem())).getText())) {
        ////	                        editFacultyAdmin();
        //	                    } else if (hcheck.equals(
        //	                                ((TreeItem) (event.getSelectedItem())).getText())) {
        ////	                        methodAddHead();
        //	                    } else if (hchecke.equals(
        ////	                                ((TreeItem) (event.getSelectedItem())).getText())) {
        //	                        editFacultyHead();
        //	                    }
        //	                }
        //	            });
    }

    /**
     * Method to provide interface for addition of Entities
     */
    public void methodAddEntity() {
        final TextArea fAddressText = new TextArea();
        final TextField fCityText = new TextField();
        final TextField fStateText = new TextField();

        final NumberField fPhoneText = new NumberField();
        final NumberField faxText = new NumberField();

        final ComboBox entityTypeCombo = new ComboBox();
        final ComboBox entityParentList = new ComboBox();

        FlexTable table = new FlexTable();
        VerticalPanel fullpage = new VerticalPanel();
        HorizontalPanel buttonPanel = new HorizontalPanel();

        Label heading = new Label(constant.labelFacultyAdd());
        heading.setStyleName("Heading");

        fullpage.setHeight("492px");
        fullpage.setWidth("600px");

        Label fnameLabel = new Label(constant.entityName());
        Label parentLabel = new Label(constant.emptyParentEntity());
        Label fAdressLabel = new Label(constant.labelAddress());
        Label fCityLabel = new Label(constant.city());
        Label fStateLabel = new Label(constant.state());
        Label fPhoneLabel = new Label(constant.labelPhone());
        Label faxLabel = new Label(constant.labelFax());    
        
        
        
        final TextField fnameText = new TextField();

        entityTypeList.addItem("Select");

        Button addButton = new Button(constant.addButton());
        Button resetButton = new Button(constant.resetButton());
        Button addAdminButton = new Button(constant.addAdminButton());

        fnameText.setAllowBlank(false);

        fAddressText.setAllowBlank(false);
        fCityText.setAllowBlank(false);
        fStateText.setAllowBlank(false);

        table.setCellSpacing(10);
        fAddressText.setWidth("185px");
        fPhoneText.setWidth("185px");
        fPhoneText.setAllowDecimals(false);
        faxText.setAllowDecimals(false);

        entityTypeCombo.setForceSelection(true);
        entityTypeCombo.setMinChars(1);
        entityTypeCombo.setFieldLabel("Entity");
        entityTypeCombo.setDisplayField("entity_type");
        entityTypeCombo.setMode(ComboBox.LOCAL);
        entityTypeCombo.setTriggerAction(ComboBox.ALL);
        entityTypeCombo.setEmptyText(constant.emptyEntityType());
        entityTypeCombo.setLoadingText(constant.loading());
        entityTypeCombo.setTypeAhead(true);
        entityTypeCombo.setSelectOnFocus(true);
        entityTypeCombo.setWidth(190);
        entityTypeCombo.setHideTrigger(false);

        entityParentList.setForceSelection(true);
        entityParentList.setMinChars(1);
        entityParentList.setFieldLabel("Entity");
        entityParentList.setDisplayField("parententity");
        entityParentList.setMode(ComboBox.LOCAL);
        entityParentList.setTriggerAction(ComboBox.ALL);
        entityParentList.setEmptyText(constant.emptyParentEntity());
        entityParentList.setLoadingText(constant.loading());
        entityParentList.setTypeAhead(true);
        entityParentList.setSelectOnFocus(true);
        entityParentList.setWidth(190);
        entityParentList.setHideTrigger(false);

        fAddressText.setAllowBlank(false);
        fCityText.setAllowBlank(false);
        fStateText.setAllowBlank(false);
        fPhoneText.setAllowBlank(false);
        faxText.setAllowBlank(false);

        table.clear();
        table.setWidget(3, 0, fnameLabel);
        table.setWidget(3, 1, fnameText);
        table.setWidget(2, 0, fTypeLabel);
        table.setWidget(2, 1, entityTypeCombo);
        table.setWidget(2, 6, parentLabel);
        table.setWidget(2, 7, entityParentList);
        table.setWidget(4, 0, fAdressLabel);
        table.setWidget(4, 1, fAddressText);
        table.setWidget(5, 0, fCityLabel);
        table.setWidget(5, 1, fCityText);
        table.setWidget(6, 0, fStateLabel);
        table.setWidget(6, 1, fStateText);
        table.setWidget(7, 0, fPhoneLabel);
        table.setWidget(7, 1, fPhoneText);
        table.setWidget(9, 0, faxLabel);
        table.setWidget(9, 1, faxText);

        buttonPanel.setSpacing(20);
        buttonPanel.add(addButton);

        //	buttonPanel.add(addAdminButton);
        buttonPanel.add(resetButton);

        fullpage.clear();
        //     fullpage.setSpacing(10);
        fullpage.add(heading);
        fullpage.add(instituteText);
        fullpage.add(sessionText);
        fullpage.add(table);
        fullpage.add(buttonPanel);
        facultyflexTable.clear();
        FacultyPanelRight.clear();
        facultyflexTable.setWidget(0, 0, fullpage);
        FacultyPanelRight.add(facultyflexTable);

        connectService.methodEntityList(userid,
            new AsyncCallback<CM_entityInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(message.error(), arg0.getMessage());
                }

                public void onSuccess(CM_entityInfoGetter[] arg0) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("entity_type"),
                            });

                    object2 = new String[arg0.length][1];

                    String[][] data = object2;

                    for (int i = 0; i < arg0.length; i++) {
                        object2[i][0] = arg0[i].getEntity_name();
                    }

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    entityTypeCombo.setStore(store);
                }
            });

        entityTypeCombo.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox cb, Record rd, int index) {
                    entityParentList.reset();

                    if (entityTypeCombo.getValue().equalsIgnoreCase("institute")) {
                        String uni_id = userid.substring(1, 5);
                        connectService.methodUniversityName(uni_id,
                            new AsyncCallback<String>() {
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(message.failure(),
                                        arg0.getMessage());
                                }

                                public void onSuccess(String arg0) {
                                    Object[][] pobject = new String[1][1];

                                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("parententity"),
                                            });

                                    Object[][] data = pobject;
                                    pobject[0][0] = arg0;

                                    MemoryProxy proxy = new MemoryProxy(data);

                                    ArrayReader reader = new ArrayReader(recordDef);
                                    Store store = new Store(proxy, reader);
                                    store.load();

                                    entityParentList.setStore(store);
                                }
                            });
                    } else {
                        entityInfo.setEntity_type(entityTypeCombo.getValue());

                        connectService.methodParentEntityList(userid,
                            entityInfo,
                            new AsyncCallback<CM_entityInfoGetter[]>() {
                                @Override
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(arg0.getMessage());
                                }

                                @Override
                                public void onSuccess(
                                    CM_entityInfoGetter[] arg0) {
                                	if(arg0.length==0){
                                		MessageBox.alert(message.failure(),message.addFirstLevel());
                                	}else 
                                	{
                                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("parententity"),
                                            });

                                    parentObject = new String[arg0.length][1];

                                    String[][] data = parentObject;

                                    for (int i = 0; i < arg0.length; i++) {
                                        parentObject[i][0] = arg0[i].getEntity_name();
                                    }

                                    MemoryProxy proxy = new MemoryProxy(data);

                                    ArrayReader reader = new ArrayReader(recordDef);
                                    Store store = new Store(proxy, reader);
                                    store.load();

                                    entityParentList.setStore(store);
                                	}
                                }
                            });
                    }
                }
            });

        addButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button addButton, EventObject e) {
                    Boolean flag = true;

                    try {
                        fnameText.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        fAddressText.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        fCityText.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                        fStateText.validate();
                    } catch (Exception e1) {
                        flag = false;
                    }

                    //To check if no item selected
                    if (validator.nullValidator(entityTypeCombo.getRawValue())) {
                        try {
                            entityTypeCombo.markInvalid(
                                constant.emptyEntityType());
                        } catch (Exception e1) {
                            flag = false;
                        }

                        flag = false;
                    }

                    if (validator.nullValidator(entityParentList.getRawValue())) {
                        try {
                            entityParentList.markInvalid(
                                constant.emptyParentEntity());
                        } catch (Exception e1) {
                            flag = false;
                        }

                        flag = false;
                    }

                    if (flag == true) {
                        entityInfo.setParent_entity_name(entityParentList.getValue());
                        entityInfo.setEntity_type(entityTypeCombo.getValue());
                        entityInfo.setEntity_name(fnameText.getText());
                        entityInfo.setEntity_address(fAddressText.getText());
                        entityInfo.setEntity_city(fCityText.getText());
                        entityInfo.setEntity_state(fStateText.getText());
                        entityInfo.setEntity_phone(fPhoneText.getText());
                        entityInfo.setFax(faxText.getText());

                        connectService.methodAddEntity(userid, entityInfo,
                            new AsyncCallback<String>() {
                                @Override
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(arg0.getMessage());
                                }

                                @Override
                                public void onSuccess(String arg0) {
                                    MessageBox.alert(message.success(),
                                    		message.alert_successfulentry(),
                                        new MessageBox.AlertCallback() {
                                            public void execute() {
                                                methodAddEntity();
                                            }
                                        });
                                }
                            });
                    } else {
                        MessageBox.alert(message.error(),message.checkFields());
                    }
                }
            });

        addAdminButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button addAdminButton, EventObject e) {
                    //	                    addFacultyAdmin();
                }
            });

        resetButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button resetButton, EventObject e) {
                    methodAddEntity();
                }
            });

//        methodShowSession();
    }

    //Method fetches institute name and current session from database and put it on screen
//    public void methodShowSession() {
//        connectService.methodShowOldSession(userid,
//            new AsyncCallback<String>() {
//                public void onFailure(Throwable caught) {
//                    MessageBox.alert("Failure");
//                }
//
//                public void onSuccess(String result) {
//                    int init = 0;
//                    int k = 0;
//                    String str = "";
//
//                    for (int i = 0; i < result.length(); i++) {
//                        if (result.charAt(i) == '|') {
//                            str = result.substring(init, i);
//                            init = i + 1;
//
//                            if (k == 0) {
//                                instituteText.setText(str);
//                                instituteText.setTitle(str);
//                                k = 1;
//                            }
//
//                            if (k == 1) {
//                                sessionText.setText(str);
//                            }
//                        }
//                    }
//                }
//            });
//    }

    public void methodManageEntity() {
        try {
            final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();

            //    		final RowSelectionModel rSelectionModel = new RowSelectionModel(true);
            final Panel p1 = new Panel();
            final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
            bd.setMargins(6, 6, 6, 6);

            FlexTable table = new FlexTable();
            final VerticalPanel fullpage = new VerticalPanel();
            final VerticalPanel fullpage1 = new VerticalPanel();
            HorizontalPanel buttonPanel = new HorizontalPanel();

            //	        fullpage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
            Label heading = new Label(constant.manageEntity());
            heading.setStyleName("Heading");

            //	        fullpage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
            final TextArea fAddressText = new TextArea();
            final TextField fCityText = new TextField();
            final TextField fStateText = new TextField();

            final Label entityCriteria = new Label(constant.criteria());
            final Label valueLabel = new Label(constant.labelValueLabel());

            final ComboBox entityTypeList = new ComboBox();
            final TextField entityTypeList1 = new TextField();
            final ComboBox entityParentList = new ComboBox();

            final ComboBox entityCriteriaList = new ComboBox();
            final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
            final SuggestBox ValueSuggest = new SuggestBox(oracle);
            final Button okButton = new Button("ok");

            entityTypeList.setForceSelection(true);
            entityTypeList.setMinChars(1);
            entityTypeList.setDisplayField("entity_type");
            entityTypeList.setMode(ComboBox.LOCAL);
            entityTypeList.setTriggerAction(ComboBox.ALL);
            entityTypeList.setEmptyText("Choose Entity Type");
            entityTypeList.setLoadingText("Loading...");
            entityTypeList.setTypeAhead(true);
            entityTypeList.setSelectOnFocus(true);
            entityTypeList.setWidth(190);
            entityTypeList.setHideTrigger(false);

            entityCriteriaList.setForceSelection(true);
            entityCriteriaList.setMinChars(1);
            entityCriteriaList.setDisplayField("criteria");
            entityCriteriaList.setMode(ComboBox.LOCAL);
            entityCriteriaList.setTriggerAction(ComboBox.ALL);
            entityCriteriaList.setEmptyText(constant.emptyCrieteria());
            entityCriteriaList.setLoadingText(constant.loading());
            entityCriteriaList.setTypeAhead(true);
            entityCriteriaList.setSelectOnFocus(true);
            entityCriteriaList.setWidth(190);
            entityCriteriaList.setHideTrigger(false);

            entityParentList.setForceSelection(true);
            entityParentList.setMinChars(1);
            entityParentList.setFieldLabel("Entity");
            entityParentList.setDisplayField("parententitylist");
            entityParentList.setMode(ComboBox.LOCAL);
            entityParentList.setTriggerAction(ComboBox.ALL);
            entityParentList.setEmptyText(constant.emptyParentEntity());
            entityParentList.setLoadingText(constant.loading());
            entityParentList.setTypeAhead(true);
            entityParentList.setSelectOnFocus(true);
            entityParentList.setWidth(190);
            entityParentList.setHideTrigger(false);

            connectService.methodEntityList(userid,
                new AsyncCallback<CM_entityInfoGetter[]>() {
                    public void onFailure(Throwable arg0) {
                        MessageBox.alert(message.error(), arg0.getMessage());
                    }

                    public void onSuccess(CM_entityInfoGetter[] arg0) {
                        RecordDef recordDef = new RecordDef(new FieldDef[] {
                                    new StringFieldDef("entity_type"),
                                });

                        object2 = new String[arg0.length][1];

                        String[][] data = object2;

                        for (int i = 0; i < arg0.length; i++) {
                            object2[i][0] = arg0[i].getEntity_name();
                        }

                        MemoryProxy proxy = new MemoryProxy(data);

                        ArrayReader reader = new ArrayReader(recordDef);
                        Store store = new Store(proxy, reader);
                        store.load();

                        entityTypeList.setStore(store);
                    }
                });

            RecordDef criteriarecordDef = new RecordDef(new FieldDef[] {
                        new StringFieldDef("criteria"),
                    });

            Object[][] criteriadata = getCriteria();

            MemoryProxy criteriaproxy = new MemoryProxy(criteriadata);

            ArrayReader criteriareader = new ArrayReader(criteriarecordDef);
            Store criteriastore = new Store(criteriaproxy, criteriareader);
            criteriastore.load();

            entityCriteriaList.setStore(criteriastore);

            VerticalPanel searchVerticalPanel=new VerticalPanel();
            FormPanel searchEntityForm=new FormPanel();
            searchEntityForm.setTitle(constant.search());
            searchEntityForm.setBorder(true);
            searchEntityForm.setBodyBorder(true);
            searchEntityForm.setStyle("background");
//            searchEntityForm.setMargins(5);
            FlexTable searchFlexTable=new FlexTable();
            searchFlexTable.setWidth("100%");
            table.setWidget(1, 0, fTypeLabel);
            table.setWidget(1, 1, entityTypeList);
            searchFlexTable.setWidget(2, 0, entityCriteria);
            searchFlexTable.setWidget(2, 1, entityCriteriaList);
            searchFlexTable.setWidget(4, 0, valueLabel);
            searchFlexTable.setWidget(4, 1, ValueSuggest);
//            table.setWidget(6, 6, okButton);
            searchFlexTable.setStyleName("background");
            searchEntityForm.add(searchFlexTable);
            
            
            searchVerticalPanel.setSpacing(20);
            searchVerticalPanel.add(table);
            searchVerticalPanel.add(searchEntityForm);
            searchVerticalPanel.add(okButton);
            searchVerticalPanel.setCellHorizontalAlignment(okButton,HasHorizontalAlignment.ALIGN_RIGHT);
            entityCriteriaList.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox cb, Record rd, int index) {
                        ValueSuggest.setText("");

                        Boolean flag = true;

                        //To check if no item selected
                        if (validator.nullValidator(
                                    entityTypeList.getRawValue())) {
                            MessageBox.alert(message.error(),
                                message.selectEntityName());
                            flag = false;
                        } else {
                            entityType = entityTypeList.getValue();
                        }

                        if (flag == true) {
                            final String criteria = entityCriteriaList.getValue();
                            connectService.methodPopulateEntitySuggest(userid,
                                entityType, criteria,
                                new AsyncCallback<CM_entityInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                        MessageBox.alert("Error",
                                            arg0.getMessage());
                                    }

                                    public void onSuccess(
                                        CM_entityInfoGetter[] arg0) {
                                        if (criteria.equalsIgnoreCase("name")) {
                                            oracle.clear();

                                            for (int i = 0; i < arg0.length;
                                                    i++) {
                                                oracle.add(arg0[i].getEntity_name());
                                            }
                                        } else if (criteria.equalsIgnoreCase(
                                                    "city")) {
                                            oracle.clear();

                                            for (int i = 0; i < arg0.length;
                                                    i++) {
                                                oracle.add(arg0[i].getEntity_city());
                                            }
                                        } else {
                                            System.out.println("Wrong criteria");
                                        }
                                    }
                                });
                        }
                    }
                });

            entityTypeList.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox cb, Record rd, int index) {
                        entityCriteriaList.reset();
                        ValueSuggest.setText("");
                    }
                });

            fullpage.setSpacing(10);
            fullpage.add(heading);
            fullpage.add(instituteText);
            fullpage.add(sessionText);
            fullpage.add(searchVerticalPanel);
            fullpage.add(buttonPanel);
            fullpage.add(fullpage1);

            facultyflexTable.clear();
            FacultyPanelRight.clear();
            facultyflexTable.setWidget(0, 0, fullpage);
            FacultyPanelRight.add(facultyflexTable);

            // method to be called on click of ok button typically in onSuccess
            okButton.addListener(new ButtonListenerAdapter() {
                    public void onClick(Button button, EventObject e) {
                        try {
                            if (cbSelectionModel.getCount() > 0) {
                                cbSelectionModel.clearSelections();
                            }
                        } catch (Exception e1) {
                            System.out.println(e1);
                        }

                        Boolean flag = true;
                        String criteria = null;
                        String value="";
                        //To check if no item selected
                        if (validator.nullValidator(
                                    entityTypeList.getRawValue())) {
                            MessageBox.alert(message.failure(),
                                message.selectEntityType());
                            flag = false;
                        } else {
                            entityType = entityTypeList.getValue();
                        }

                        if (validator.nullValidator(entityCriteriaList.getRawValue())) {
//                            MessageBox.alert("Error", "Please select criteria");
//                            flag = false;
                        	criteria="name";
                        	 value ="%";
                        } else {
                            criteria = entityCriteriaList.getValue();
                           value = ValueSuggest.getText();
                        }

                        if (flag == true) {
                            
                            connectService.methodPopulateEntity(userid,
                                entityType, criteria, value,
                                new AsyncCallback<CM_entityInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                        MessageBox.alert(message.error(),
                                            arg0.getMessage());
                                    }

                                    @Override
                                    public void onSuccess(
                                        CM_entityInfoGetter[] arg0) {
                                        final GridPanel grid = new GridPanel();

                                        if (arg0.length == 0) {
                                            grid.setTitle(
                                                message.noGridCrieteria());
                                        } else {
                                            grid.setTitle("Entity Details");
                                        }

                                        final RecordDef rDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Type"),
                                                    new StringFieldDef("Name"),
                                                    new StringFieldDef("Parent Entity"),
                                                    new StringFieldDef("Address"),
                                                    new StringFieldDef("City"),
                                                    new StringFieldDef("State"),
                                                    new FloatFieldDef("Phone"),
                                                    new FloatFieldDef("Fax"),
                                                    new StringFieldDef("Entity id")
                                                });

                                        object1 = new Object[arg0.length][9];

                                        String str = null;

                                        try {
                                            for (int i = 0; i < arg0.length;
                                                    i++) {
                                                for (int k = 0; k < 9; k++) {
                                                    if (k == 0) {
                                                        str = entityType;
                                                    } else if (k == 1) {
                                                        str = arg0[i].getEntity_name();
                                                    } else if (k == 2) {
                                                        str = arg0[i].getParent_entity_id();
                                                    } else if (k == 3) {
                                                        str = arg0[i].getEntity_address();
                                                    } else if (k == 4) {
                                                        str = arg0[i].getEntity_city();
                                                    } else if (k == 5) {
                                                        str = arg0[i].getEntity_state();
                                                    } else if (k == 6) {
                                                        str = arg0[i].getEntity_phone();
                                                    } else if (k == 7) {
                                                        str = arg0[i].getFax();
                                                    } else if (k == 8) {
                                                        str = arg0[i].getEntity_id();
                                                    }

                                                    object1[i][k] = str;
                                                }
                                            }
                                        } catch (Exception e2) {
                                            System.out.println("e2     " + e2);
                                        }

                                        Object[][] data = object1;

                                        MemoryProxy proxy = null;

                                        try {
                                            proxy = new MemoryProxy(data);
                                        } catch (Exception e3) {
                                            System.out.println("e3          " +
                                                e3);
                                        }

                                        ArrayReader reader = new ArrayReader(rDef);
                                        Store store = new Store(proxy, reader);

                                        store.load();
                                        grid.setStore(store);

                                        BaseColumnConfig[] columns = new BaseColumnConfig[] {
                                                new CheckboxColumnConfig(cbSelectionModel),
                                                new ColumnConfig("Type",
                                                    "Type", 200, true, null,
                                                    "entitytype"),
                                                new ColumnConfig("Name",
                                                    "Name", 200, true, null,
                                                    "entityname"),
                                                new ColumnConfig("Parent Entity",
                                                    "Parent Entity", 200, true,
                                                    null, "entityparent"),
                                                new ColumnConfig("Address",
                                                    "Address", 100, true, null,
                                                    "entityadd"),
                                                new ColumnConfig("City",
                                                    "City", 80, true, null,
                                                    "entitycity"),
                                                new ColumnConfig("State",
                                                    "State", 80, true, null,
                                                    "entitystate"),
                                                new ColumnConfig("Phone",
                                                    "Phone", 55, true, null,
                                                    "entityPhone"),
                                                new ColumnConfig("Fax", "Fax",
                                                    55, true, null, "entityFax")
                                            };

                                        final ColumnModel columnModel = new ColumnModel(columns);
                                        grid.setColumnModel(columnModel);

                                        grid.setFrame(true);
                                        grid.setStripeRows(true);
                                        grid.setAutoExpandColumn("entitytype");
                                        grid.setAutoExpandColumn("entityname");
                                        grid.setAutoExpandColumn("entityadd");
                                        grid.setAutoExpandColumn("entitycity");
                                        grid.setAutoExpandColumn("entitystate");
                                        grid.setAutoExpandColumn("entityPhone");
                                        grid.setAutoExpandColumn("entityparent");
                                        grid.setAutoExpandColumn("entityFax");

                                        grid.setSelectionModel(cbSelectionModel);
                                        grid.setAutoWidth(true);
                                        grid.setWidth(1200);
                                        grid.setHeight(280);

                                        Toolbar topToolBar = new Toolbar();
                                        topToolBar.addFill();

                                        ToolbarButton editButton = new ToolbarButton(constant.edit(),
                                                new ButtonListenerAdapter() {
                                                    public void onClick(
                                                        Button editButton,
                                                        EventObject e) {
                                                        //	Record rec=cbSelectionModel.getSelected();
                                                        // System.out.println(rec.getAsString("Name"));
                                                    	try{
                                                        Record[] records = cbSelectionModel.getSelections();

                                                        String msg = "";

                                                        if (records.length < 1) {
                                                            MessageBox.alert(message.error(),
                                                            		message.atleastOneRecord());
                                                        } else if (records.length > 1) {
                                                            MessageBox.alert(message.error(),
                                                                message.onlyOneRecord());
                                                        } else if (records.length == 1) {
                                                            final Record record = records[0];

                                                            msg += record.getAsString(
                                                                "University Name");

                                                            FlexTable editInstiTable =
                                                                new FlexTable();
                                                            final NumberField fPhoneText =
                                                                new NumberField();
                                                            final NumberField faxText =
                                                                new NumberField();
                                                            fPhoneText.setAllowDecimals(false);
                                                            faxText.setAllowDecimals(false);

                                                            Label fnameLabel = new Label(constant.entityName());
                                                            final TextField fnameText = new TextField();
                                                            Label fTypeLabel1 = new Label(constant.entityType());
                                                            fnameText.setAllowBlank(false);
                                                            Label parentLabel = new Label(constant.emptyParentEntity());
                                                            Label fAdressLabel = new Label(constant.labelAddress());
                                                            Label fCityLabel = new Label(constant.city());
                                                            Label fStateLabel = new Label(constant.state());
                                                            Label fPhoneLabel = new Label(constant.labelPhone());
                                                            Label faxLabel = new Label(constant.labelFax());
                                                            
                                                            
                                                            
                                                            editInstiTable.clear();
                                                            editInstiTable.setWidget(3,
                                                                0, fnameLabel);
                                                            editInstiTable.setWidget(3,
                                                                1, fnameText);
                                                            editInstiTable.setWidget(2,
                                                                0, fTypeLabel1);
                                                            editInstiTable.setWidget(2,
                                                                1,
                                                                entityTypeList1);
                                                            editInstiTable.setWidget(2,
                                                                6, parentLabel);
                                                            editInstiTable.setWidget(2,
                                                                7,
                                                                entityParentList);
                                                            editInstiTable.setWidget(4,
                                                                0, fAdressLabel);
                                                            editInstiTable.setWidget(4,
                                                                1, fAddressText);
                                                            editInstiTable.setWidget(5,
                                                                0, fCityLabel);
                                                            editInstiTable.setWidget(5,
                                                                1, fCityText);
                                                            editInstiTable.setWidget(6,
                                                                0, fStateLabel);
                                                            editInstiTable.setWidget(6,
                                                                1, fStateText);
                                                            editInstiTable.setWidget(7,
                                                                0, fPhoneLabel);
                                                            editInstiTable.setWidget(7,
                                                                1, fPhoneText);
                                                            editInstiTable.setWidget(9,
                                                                0, faxLabel);
                                                            editInstiTable.setWidget(9,
                                                                1, faxText);

                                                            entityTypeList1.setReadOnly(true);
                                                            entityParentList.setEmptyText(
                                                                constant.emptyParentEntity());
                                                            entityParentList.clearValue();

                                                            entityTypeList1.setValue(record.getAsString(
                                                                    "Type"));
                                                            entityInfo.setEntity_type(entityTypeList1.getText());
                                                            connectService.methodParentEntityList(userid,
                                                                entityInfo,
                                                                new AsyncCallback<CM_entityInfoGetter[]>() {
                                                                    public void onFailure(
                                                                        Throwable arg0) {
                                                                        MessageBox.alert(arg0.getMessage());
                                                                    }

                                                                    public void onSuccess(
                                                                        CM_entityInfoGetter[] arg0) {
                                                                        RecordDef recordDef =
                                                                            new RecordDef(new FieldDef[] {
                                                                                    new StringFieldDef(
                                                                                        "parententitylist")
                                                                                });

                                                                        parentObject = new String[arg0.length][1];

                                                                        String[][] data =
                                                                            parentObject;

                                                                        for (int i =
                                                                                0;
                                                                                i < arg0.length;
                                                                                i++) {
                                                                            parentObject[i][0] = arg0[i].getEntity_name();
                                                                        }

                                                                        MemoryProxy proxy =
                                                                            new MemoryProxy(data);

                                                                        ArrayReader reader =
                                                                            new ArrayReader(recordDef);
                                                                        Store store =
                                                                            new Store(proxy,
                                                                                reader);
                                                                        store.load();

                                                                        entityParentList.setStore(store);
                                                                    }
                                                                });

                                                            entityParentList.setValue(record.getAsString(
                                                                    "Parent Entity"));

                                                            fnameText.setValue(record.getAsString(
                                                                    "Name"));
                                                            fAddressText.setValue(record.getAsString(
                                                                    "Address"));
                                                            fCityText.setValue(record.getAsString(
                                                                    "City"));
                                                            fStateText.setValue(record.getAsString(
                                                                    "State"));
                                                            fPhoneText.setValue(record.getAsString(
                                                                    "Phone"));
                                                            faxText.setValue(record.getAsString(
                                                                    "Fax"));

                                                            /*
                                                             * To get id of selected entity
                                                             * record.getAsString("Entity id")
                                                             */
                                                            p1.clear();
                                                            p1.add(editInstiTable);

                                                            Button windowUpdateButton =
                                                                new Button(
                                                                    constant.updateButton());
                                                            Button windowResetButton =
                                                                new Button(
                                                                    constant.resetButton());
                                                            final Window window = new Window();
                                                            window.setTitle(
                                                                constant.titleEntitydetails());
                                                            window.setWidth(700);
                                                            window.setHeight(300);
                                                            window.setResizable(false);
                                                            window.setLayout(new BorderLayout());
                                                            window.setPaddings(5);
                                                            window.setButtonAlign(Position.CENTER);
                                                            window.addButton(windowUpdateButton);
                                                            window.addButton(windowResetButton);
                                                            window.setAutoScroll(true);
                                                            window.add(p1, bd);
                                                            window.setCloseAction(Window.CLOSE);
                                                            window.setPlain(true);
                                                            window.setFrame(true);
                                                            window.setClosable(true);
                                                            window.setModal(true);

                                                            window.show(editButton.getId());
                                                            //Adding handler to reset button of edit window 
                                                            windowResetButton.addListener(new ButtonListenerAdapter() {
                                                                    public void onClick(
                                                                        Button button,
                                                                        EventObject e) {
                                                                        entityTypeList1.setValue(record.getAsString(
                                                                                "Type"));
                                                                        entityInfo.setEntity_type(entityTypeList1.getText());
                                                                        connectService.methodParentEntityList(userid,
                                                                            entityInfo,
                                                                            new AsyncCallback<CM_entityInfoGetter[]>() {
                                                                                public void onFailure(
                                                                                    Throwable arg0) {
                                                                                    MessageBox.alert(arg0.getMessage());
                                                                                }

                                                                                public void onSuccess(
                                                                                    CM_entityInfoGetter[] arg0) {
                                                                                    RecordDef recordDef =
                                                                                        new RecordDef(new FieldDef[] {
                                                                                                new StringFieldDef(
                                                                                                    "parententitylist")
                                                                                            });

                                                                                    parentObject = new String[arg0.length][1];

                                                                                    String[][] data =
                                                                                        parentObject;

                                                                                    for (int i =
                                                                                            0;
                                                                                            i < arg0.length;
                                                                                            i++) {
                                                                                        parentObject[i][0] = arg0[i].getEntity_name();
                                                                                    }

                                                                                    MemoryProxy proxy =
                                                                                        new MemoryProxy(data);

                                                                                    ArrayReader reader =
                                                                                        new ArrayReader(recordDef);
                                                                                    Store store =
                                                                                        new Store(proxy,
                                                                                            reader);
                                                                                    store.load();

                                                                                    entityParentList.setStore(store);
                                                                                }
                                                                            });

                                                                        entityParentList.setValue(record.getAsString(
                                                                                "Parent Entity"));

                                                                        fnameText.setValue(record.getAsString(
                                                                                "Name"));
                                                                        fAddressText.setValue(record.getAsString(
                                                                                "Address"));
                                                                        fCityText.setValue(record.getAsString(
                                                                                "City"));
                                                                        fStateText.setValue(record.getAsString(
                                                                                "State"));
                                                                        fPhoneText.setValue(record.getAsString(
                                                                                "Phone"));
                                                                        faxText.setValue(record.getAsString(
                                                                                "Fax"));
                                                                    }
                                                                });

                                                            //Adding handler to update button of edit window 
                                                            windowUpdateButton.addListener(new ButtonListenerAdapter() {
                                                                    public void onClick(
                                                                        Button button,
                                                                        EventObject e) {
                                                                        Boolean flag = true;

                                                                        try {
                                                                            fnameText.validate();
                                                                        } catch (Exception e1) {
                                                                            flag = false;
                                                                        }

                                                                        try {
                                                                            fAddressText.validate();
                                                                        } catch (Exception e1) {
                                                                            flag = false;
                                                                        }

                                                                        try {
                                                                            fCityText.validate();
                                                                        } catch (Exception e1) {
                                                                            flag = false;
                                                                        }

                                                                        try {
                                                                            fStateText.validate();
                                                                        } catch (Exception e1) {
                                                                            flag = false;
                                                                        }

                                                                        //To check if no item selected
                                                                       
                                                                        if (validator.nullValidator(entityParentList.getRawValue())) {
                                                                            try {
                                                                                entityParentList.markInvalid(
                                                                                    constant.emptyParentEntity());
                                                                            } catch (Exception e1) {
                                                                                flag = false;
                                                                            }

                                                                            flag = false;
                                                                        }
                                                                        if(flag==true){
                                                                        CM_entityInfoGetter entityInfoObject =
                                                                            new CM_entityInfoGetter();

                                                                        entityInfoObject.setEntity_id(record.getAsString(
                                                                                "Entity id"));
                                                                        entityInfoObject.setEntity_name(fnameText.getText());
                                                                        entityInfoObject.setParent_entity_name(entityParentList.getValue());
                                                                        entityInfoObject.setEntity_address(fAddressText.getText());
                                                                        entityInfoObject.setEntity_city(fCityText.getText());
                                                                        entityInfoObject.setEntity_state(fStateText.getText());
                                                                        entityInfoObject.setEntity_phone(fPhoneText.getText());
                                                                        entityInfoObject.setFax(faxText.getText());

                                                                        entityInfoObject.setEntity_type(record.getAsString(
                                                                        "Type"));
                                                                        connectService.methodUpdateEntity(userid,
                                                                            entityInfoObject,
                                                                            new AsyncCallback<String>() {
                                                                                public void onFailure(
                                                                                    Throwable arg0) {
                                                                                    MessageBox.alert(message.failure(),
                                                                                        arg0.getMessage());
                                                                                }

                                                                                public void onSuccess(
                                                                                    String arg0) {
                                                                                    MessageBox.alert(constant.congratulation(),
                                                                                       message.alert_oneditsuccess(),
                                                                                        new AlertCallback() {
                                                                                            public void execute() {
                                                                                            	  if (validator.nullValidator(entityCriteriaList.getRawValue())) {
                                                                                                      
                                                                                                  }else if (entityCriteriaList.getValue().equalsIgnoreCase("name")) {
                                                                                                    ValueSuggest.setText(fnameText.getText());
                                                                                                } else if (entityCriteriaList.getValue().equalsIgnoreCase("city")){
                                                                                                    ValueSuggest.setText(fCityText.getText());
                                                                                                }

                                                                                                okButton.fireEvent("click");
                                                                                                window.close();
                                                                                            }
                                                                                        });
                                                                                }
                                                                            });
                                                                    }
                                                                    }
                                                                    
                                                                });
                                                        }
                                                    }catch (Exception ex) {
													System.out
															.println("here is the exception "+ ex);
													}
                                        }
                                                });

                                        topToolBar.addButton(editButton);

                                        topToolBar.addButton(new ToolbarButton(
                                                "Delete",
                                                new ButtonListenerAdapter() {
                                                public void onClick(Button delButton,
                                                        EventObject e) {
                                                    Record[] records = cbSelectionModel.getSelections();

                                                    if (records.length < 1) {
                                                        MessageBox.alert(
                                                                message.error(),
                                                                message.atleastOneRecordDelete());
                                                    } else if (records.length > 1) {
                                                        MessageBox.alert(
                                                                message.error(),
                                                                message.select_recordsdeletion());
                                                    }else {
                                                        for (int i = 0;
                                                                    i < records.length;
                                                                    i++) {
                                                            Record record = records[i];
                                                            final String entity_id =
                                                                    record.getAsString(
                                                                        "Entity id");
                                                            MessageBox.show(
                                                                    new MessageBoxConfig() {

                                                                    {
                                                                        setTitle(
                                                                                message.confirm());
                                                                        setMsg(message.alert_ondelete());
                                                                        setIconCls(
                                                                                MessageBox.QUESTION);
                                                                        setButtons(
                                                                                MessageBox.YESNO);
                                                                        setCallback(
                                                                                new MessageBox.PromptCallback() {
                                                                                public void execute(String btnID,
                                                                                        String text) {
                                                                                    if (btnID.equals(
                                                                                                    "yes")) {
                                                                                        connectService.methodDeleteEntity(
                                                                                                entity_id,
                                                                                                new AsyncCallback<String>() {
                                                                                                public void onFailure(Throwable arg0) {
                                                                                                    MessageBox.alert(
                                                                                                            message.failure(),
                                                                                                            arg0.getMessage());
                                                                                                }

                                                                                                public void onSuccess(String arg0) {
                                                                                                    MessageBox.alert(
                                                                                                            constant.congratulation(),
                                                                                                            message.alert_ondeletesuccess(),
                                                                                                            new AlertCallback() {
                                                                                                            public void execute() {
                                                                                                                okButton.fireEvent(
                                                                                                                        "click");
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
                                                }
                                            }));

                                        grid.setTopToolbar(topToolBar);

                                        fullpage1.clear();
                                        fullpage1.add(grid);
                                    }
                                });
                        }
                    }
                });
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Object[][] getCriteria() {
        return new Object[][] { new Object[] { "Name" }, new Object[] { "City" } };
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    //
    //	    /**
    //	     * Method provides interface for adding faculty administration
    //	     */
    //	    public void addFacultyAdmin() {
    //	        final VerticalPanel Vpanel = new VerticalPanel();
    //	        HorizontalPanel Hpanel = new HorizontalPanel();
    //	        HorizontalPanel Hpanel1 = new HorizontalPanel();
    //	        Vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    //
    //	        FlexTable addadmin = new FlexTable();
    //
    //	        Label Heading = new Label("");
    //	        Heading.setStyleName("Heading");
    //	        Heading.setText("Add faculty Admin");
    //
    //	        Vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    //	        Vpanel.setHeight("492px");
    //	        Vpanel.setWidth("600px");
    //
    //	        Label fnameLabel = new Label("Faculty Name :");
    //	        Label Name = new Label("Employee Name :");
    //	        Label EmpID = new Label("Employee ID :");
    //
    //	        final ListBox fnameText = new ListBox();
    //	        final ListBox empNameList = new ListBox();
    //	        final TextField empIDText = new TextField();
    //	        final ListBox tempListBox = new ListBox();
    //
    //	        Button addbutton = new Button("Add");
    //	        Button Reset = new Button("Reset");
    //
    //	        empIDText.setReadOnly(true);
    //	        fnameText.setWidth("185px");
    //	        empNameList.setWidth("185px");
    //
    //	        Reset.addListener(new ButtonListenerAdapter() {
    //	                public void onClick(Button resetButton, EventObject e) {
    //	                    addFacultyAdmin();
    //	                }
    //	            });
    //
    //	        addadmin.setWidget(0, 0, fnameLabel);
    //	        addadmin.setWidget(0, 1, fnameText);
    //	        addadmin.setWidget(4, 0, Name);
    //	        addadmin.setWidget(4, 1, empNameList);
    //	        addadmin.setWidget(5, 0, EmpID);
    //	        addadmin.setWidget(5, 1, empIDText);
    //
    //	        Hpanel.add(addbutton);
    //	        Hpanel.setSpacing(20);
    //	        Hpanel.add(Reset);
    //
    //	        Vpanel.add(Heading);
    //	        Vpanel.setSpacing(20);
    //	        Vpanel.add(addadmin);
    //	        Vpanel.setSpacing(20);
    //	        Vpanel.add(Hpanel);
    //
    //	        // method for populating faculty list
    //	        connectService.methodFacultyPopulate(userid,
    //	            new AsyncCallback<CM_FacultyInfoGetter[]>() {
    //	                public void onFailure(Throwable arg0) {
    //	                    MessageBox.alert("Failure");
    //	                }
    //
    //	                public void onSuccess(CM_FacultyInfoGetter[] result) {
    //	                    if (result.length < 1) {
    //	                        MessageBox.alert("Alert",
    //	                            "No Faculty Exist in Institute",
    //	                            new MessageBox.AlertCallback() {
    //	                                public void execute() {
    //	                                    methodAddEntity();
    //	                                }
    //	                            });
    //	                    } else {
    //	                        fnameText.clear();
    //
    //	                        //                        int init = 0;
    //	                        //                        String str = "";
    //	                        for (int i = 0; i < result.length; i++) {
    //	                            //                            if (result.charAt(i) == '|') {
    //	                            //                                str = result.substring(init, i);
    //	                            fnameText.addItem(result[i].getfacultyName());
    //
    //	                            //
    //	                            //                                init = i + 1;
    //	                            //                            }
    //	                        }
    //	                    }
    //	                }
    //	            });
    //
    //	        fnameText.addClickHandler(new ClickHandler() {
    //	                public void onClick(ClickEvent arg0) {
    //	                    if (fnameText.getItemCount() != 0) {
    //	                        String facultyName = fnameText.getValue(fnameText.getSelectedIndex());
    //	                        //populating employee list
    //	                        connectService.methodEmployeePopulate(userid,
    //	                            facultyName,
    //	                            new AsyncCallback<String>() {
    //	                                public void onFailure(Throwable arg0) {
    //	                                    MessageBox.alert("Failure");
    //	                                }
    //
    //	                                public void onSuccess(String result) {
    //	                                    tempListBox.clear();
    //	                                    empNameList.clear();
    //	                                    empIDText.setValue("");
    //
    //	                                    int init = 0;
    //	                                    int k = 0;
    //	                                    String str = "";
    //
    //	                                    //putting employee's list in respective ListBoxes.
    //	                                    for (int i = 0; i < result.length(); i++) {
    //	                                        if (result.charAt(i) == '|') {
    //	                                            str = result.substring(init, i);
    //	                                            init = i + 1;
    //
    //	                                            if (k == 0) {
    //	                                                empNameList.addItem(str, str);
    //	                                                k = 1;
    //	                                            } else if (k == 1) {
    //	                                                tempListBox.addItem(str, str);
    //	                                                k = 0;
    //	                                            }
    //	                                        }
    //	                                    }
    //	                                }
    //	                            });
    //	                    }
    //	                }
    //	            });
    //
    //	        empNameList.addClickHandler(new ClickHandler() {
    //	                public void onClick(ClickEvent arg0) {
    //	                    if (empNameList.getItemCount() != 0) {
    //	                        empIDText.setValue(tempListBox.getValue(
    //	                                empNameList.getSelectedIndex()));
    //	                    }
    //	                }
    //	            });
    //
    //	        addbutton.addListener(new ButtonListenerAdapter() {
    //	                public void onClick(Button addButton, EventObject e) {
    //	                    if ((empNameList.getItemCount() != 0) ||
    //	                            (fnameText.getItemCount() != 0)) {
    //	                        final String facultyName = fnameText.getValue(fnameText.getSelectedIndex());
    //	                        String employeeID = empIDText.getText();
    //
    //	                        if ((employeeID.trim()).equals("")) {
    //	                            MessageBox.alert("Please select employee first");
    //	                        } else {
    //	                            connectService.methodAddFacultyAdmin(userid,
    //	                                facultyName, employeeID,
    //	                                new AsyncCallback<String>() {
    //	                                    public void onFailure(Throwable arg0) {
    //	                                        MessageBox.alert("Failure");
    //	                                    }
    //
    //	                                    public void onSuccess(String result) {
    //	                                        if (result.equals("Already exists")) {
    //	                                            MessageBox.alert(
    //	                                                "Faculty Admin for " +
    //	                                                facultyName +
    //	                                                " already exists");
    //	                                        } else {
    //	                                            MessageBox.alert(
    //	                                                "Faculty Admin successfully added");
    //	                                            addFacultyAdmin();
    //	                                        }
    //	                                    }
    //	                                });
    //	                        }
    //	                    }
    //	                }
    //	            });
    //
    //	        facultyflexTable.setWidget(0, 0, Vpanel);
    //	        Hpanel1.add(facultyflexTable);
    //	        FacultyPanelRight.add(Hpanel1);
    //	    }
    //
    //	    // method for populating faculty list
    //	    public void methodFacultyPopulate() {
    //	        connectService.methodFacultyPopulate(userid,
    //	            new AsyncCallback<CM_FacultyInfoGetter[]>() {
    //	                public void onFailure(Throwable arg0) {
    //	                    MessageBox.alert("Failure");
    //	                }
    //
    //	                public void onSuccess(CM_FacultyInfoGetter[] result) {
    //	                    if (result.length < 1) {
    //	                        MessageBox.alert("Alert",
    //	                            "No Faculty Exist in Institute",
    //	                            new MessageBox.AlertCallback() {
    //	                                public void execute() {
    //	                                    methodAddEntity();
    //	                                }
    //	                            });
    //	                    } else {
    ////	                        fnameText.clear();
    //
    //
    //	                        for (int i = 0; i < result.length; i++) {
    //	                        
    ////	                            fnameText.addItem(result[i].getfacultyName());
    //
    //	                            
    //	                        }
    //	                    }
    //	                }
    //	            });
    //	    }
    //
    //	    /**
    //	     * This method provides interface for editing faculty administrator information.
    //	     */
    //	    public void editFacultyAdmin() {
    //	        final VerticalPanel Vpanel = new VerticalPanel();
    //	        HorizontalPanel Hpanel = new HorizontalPanel();
    //	        HorizontalPanel Hpanel1 = new HorizontalPanel();
    //	        Vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    //
    //	        FlexTable editadmin = new FlexTable();
    //
    //	        Label Heading = new Label("");
    //	        Heading.setStyleName("Heading");
    //	        Heading.setText("Edit faculty Admin");
    //
    //	        Vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    //	        Vpanel.setHeight("492px");
    //	        Vpanel.setWidth("600px");
    //
    //	        Label empOldNameLabel = new Label("Faculty Administrator's Name :");
    //	        Label empOldIDLabel = new Label("Faculty Administrator's ID :");
    //	        Label empNamelabel = new Label("Employee Name :");
    //	        Label empIDLabel = new Label("Employee ID :");
    //
    //	        final ListBox fnameText = new ListBox();
    //	        final ListBox empNameList = new ListBox();
    //	        final TextField empIDText = new TextField();
    //	        final ListBox tempListBox = new ListBox();
    //
    //	        final TextField empOldNameText = new TextField();
    //	        final TextField empOldIDText = new TextField();
    //
    //	        Button editbutton = new Button("Edit");
    //	        Button Reset = new Button("Reset");
    //
    //	        Reset.addListener(new ButtonListenerAdapter() {
    //	                public void onClick(Button resetButton, EventObject e) {
    //	                    editFacultyAdmin();
    //	                }
    //	            });
    //
    //	        editadmin.setWidget(0, 0, fnameLabel);
    //	        editadmin.setWidget(0, 1, fnameText);
    //	        editadmin.setWidget(2, 0, empOldNameLabel);
    //	        editadmin.setWidget(2, 1, empOldNameText);
    //	        editadmin.setWidget(3, 0, empOldIDLabel);
    //	        editadmin.setWidget(3, 1, empOldIDText);
    //	        editadmin.setWidget(4, 0, empNamelabel);
    //	        editadmin.setWidget(4, 1, empNameList);
    //	        editadmin.setWidget(5, 0, empIDLabel);
    //	        editadmin.setWidget(5, 1, empIDText);
    //
    //	        // method for populating faculty list
    //	        connectService.methodFacultyPopulate(userid,
    //	            new AsyncCallback<CM_FacultyInfoGetter[]>() {
    //	                public void onFailure(Throwable arg0) {
    //	                    MessageBox.alert("Failure");
    //	                }
    //
    //	                public void onSuccess(CM_FacultyInfoGetter[] result) {
    //	                    if (result.length < 1) {
    //	                        MessageBox.alert("Alert",
    //	                            "No Faculty Exist in Institute",
    //	                            new MessageBox.AlertCallback() {
    //	                                public void execute() {
    //	                                    methodAddEntity();
    //	                                }
    //	                            });
    //	                    } else {
    //	                        fnameText.clear();
    //	                        adminEditIndex = -1;
    //
    //	                        //                        int init = 0;
    //	                        //                        String str = "";
    //	                        for (int i = 0; i < result.length; i++) {
    //	                            //                            if (result.charAt(i) == '|') {
    //	                            //                                str = result.substring(init, i);
    //	                            fnameText.addItem(result[i].getfacultyName());
    //
    //	                            //                                init = i + 1;
    //	                            //                            }
    //	                        }
    //	                    }
    //	                }
    //	            });
    //
    //	        fnameText.addClickHandler(new ClickHandler() {
    //	                public void onClick(ClickEvent arg0) {
    //	                    if (fnameText.getItemCount() != 0) {
    //	                        final String facultyName = fnameText.getValue(fnameText.getSelectedIndex());
    //	                        int index = fnameText.getSelectedIndex();
    //
    //	                        if (adminEditIndex != index) {
    //	                            adminEditIndex = index;
    //	                            connectService.methodFacultyAdminPopulate(userid,
    //	                                facultyName,
    //	                                new AsyncCallback<String>() {
    //	                                    public void onFailure(Throwable arg0) {
    //	                                        MessageBox.alert("Failure");
    //	                                    }
    //
    //	                                    public void onSuccess(String result) {
    //	                                        if (result.equals("FA dont exist")) {
    //	                                            MessageBox.alert(
    //	                                                "Faculty Admin for " +
    //	                                                facultyName + " donot exist");
    //	                                            empOldNameText.setValue("");
    //	                                            empOldIDText.setValue("");
    //	                                            tempListBox.clear();
    //	                                            empNameList.clear();
    //	                                            empIDText.setValue("");
    //	                                        } else {
    //	                                            int init = 0;
    //	                                            int k = 0;
    //	                                            String str = "";
    //
    //	                                            //putting faculty Admin's name and ID in respective TextFieldes.
    //	                                            for (int i = 0;
    //	                                                    i < result.length(); i++) {
    //	                                                if (result.charAt(i) == '|') {
    //	                                                    str = result.substring(init,
    //	                                                            i);
    //	                                                    init = i + 1;
    //
    //	                                                    if (k == 0) {
    //	                                                        empOldNameText.setValue(str);
    //	                                                        k = 1;
    //	                                                    } else if (k == 1) {
    //	                                                        empOldIDText.setValue(str);
    //	                                                        k = 2;
    //	                                                    } else if (k == 2) {
    //	                                                        oldFAUserID = str;
    //	                                                        k = 0;
    //	                                                    }
    //	                                                }
    //	                                            }
    //
    //	                                            connectService.methodEmployeePopulate(userid,
    //	                                                facultyName,
    //	                                                new AsyncCallback<String>() {
    //	                                                    public void onFailure(
    //	                                                        Throwable arg0) {
    //	                                                        MessageBox.alert(
    //	                                                            "Failure");
    //	                                                    }
    //
    //	                                                    public void onSuccess(
    //	                                                        String result) {
    //	                                                        tempListBox.clear();
    //	                                                        empNameList.clear();
    //
    //	                                                        int init = 0;
    //	                                                        int k = 0;
    //	                                                        String str = "";
    //
    //	                                                        for (int i = 0;
    //	                                                                i < result.length();
    //	                                                                i++) {
    //	                                                            if (result.charAt(i) == '|') {
    //	                                                                str = result.substring(init,
    //	                                                                        i);
    //	                                                                init = i + 1;
    //
    //	                                                                if (k == 0) {
    //	                                                                    empNameList.addItem(str,
    //	                                                                        str);
    //	                                                                    k = 1;
    //	                                                                } else if (k == 1) {
    //	                                                                    tempListBox.addItem(str,
    //	                                                                        str);
    //	                                                                    k = 0;
    //	                                                                }
    //	                                                            }
    //	                                                        }
    //	                                                    }
    //	                                                });
    //	                                        }
    //	                                    }
    //	                                });
    //	                        }
    //	                    }
    //	                }
    //	            });
    //	        //click Handler for list of employees
    //	        empNameList.addClickHandler(new ClickHandler() {
    //	                public void onClick(ClickEvent arg0) {
    //	                    if (empNameList.getItemCount() != 0) {
    //	                        empIDText.setValue(tempListBox.getValue(
    //	                                empNameList.getSelectedIndex()));
    //	                    }
    //	                }
    //	            });
    //	        editbutton.addListener(new ButtonListenerAdapter() {
    //	                public void onClick(Button edtButton, EventObject e) {
    //	                    if ((fnameText.getItemCount() != 0) ||
    //	                            (empNameList.getItemCount() != 0)) {
    //	                        String facultyName = fnameText.getValue(fnameText.getSelectedIndex());
    //	                        String employeeID = empIDText.getText();
    //	                        String OldFAuID = oldFAUserID;
    //
    //	                        if ((employeeID.trim()).equals("")) {
    //	                            MessageBox.alert("Please select employee first");
    //	                        } else {
    //	                            connectService.methodEditFacultyAdmin(userid,
    //	                                facultyName, employeeID, OldFAuID,
    //	                                new AsyncCallback<String>() {
    //	                                    public void onFailure(Throwable arg0) {
    //	                                        MessageBox.alert("Failure");
    //	                                    }
    //
    //	                                    public void onSuccess(String arg0) {
    //	                                        MessageBox.alert(
    //	                                            "Faculty Administrator successfully edited");
    //	                                        editFacultyAdmin();
    //	                                    }
    //	                                });
    //	                        }
    //	                    }
    //	                }
    //	            });
    //
    //	        Hpanel.add(editbutton);
    //	        Hpanel.setSpacing(20);
    //	        Hpanel.add(Reset);
    //
    //	        Vpanel.add(Heading);
    //	        Vpanel.setSpacing(20);
    //	        Vpanel.add(editadmin);
    //	        Vpanel.setSpacing(20);
    //	        Vpanel.add(Hpanel);
    //
    //	        methodFacultyPopulate();
    //
    //	        facultyflexTable.setWidget(0, 0, Vpanel);
    //	        Hpanel1.add(facultyflexTable);
    //	        FacultyPanelRight.add(Hpanel1);
    //	    }
    //
    //	    /**
    //	     * Method adds faculty head to the faculty
    //	     */
    //	    public void methodAddHead() {
    //	        final VerticalPanel Vpanel = new VerticalPanel();
    //	        HorizontalPanel Hpanel = new HorizontalPanel();
    //	        HorizontalPanel Hpanel1 = new HorizontalPanel();
    //	        Vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    //
    //	        FlexTable addHead = new FlexTable();
    //
    //	        Label Heading = new Label("");
    //	        Heading.setStyleName("Heading");
    //	        Heading.setText("Add faculty Head");
    //
    //	        Vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    //	        Vpanel.setHeight("492px");
    //	        Vpanel.setWidth("600px");
    //
    //	        Label fnameLabel = new Label("Faculty Name :");
    //	        Label Name = new Label("Employee Name :");
    //	        Label EmpID = new Label("Employee ID :");
    //
    //	        final ListBox fnameText = new ListBox();
    //	        final ListBox empNameList = new ListBox();
    //	        final TextField empIDText = new TextField();
    //	        final ListBox tempListBox = new ListBox();
    //
    //	        Button addbutton = new Button("Add");
    //	        Button Reset = new Button("Reset");
    //
    //	        empIDText.setReadOnly(true);
    //	        fnameText.setWidth("185px");
    //	        empNameList.setWidth("185px");
    //
    //	        Reset.addListener(new ButtonListenerAdapter() {
    //	                public void onClick(Button resetButton, EventObject e) {
    //	                    addFacultyAdmin();
    //	                }
    //	            });
    //
    //	        addHead.setWidget(0, 0, fnameLabel);
    //	        addHead.setWidget(0, 1, fnameText);
    //	        addHead.setWidget(4, 0, Name);
    //	        addHead.setWidget(4, 1, empNameList);
    //	        addHead.setWidget(5, 0, EmpID);
    //	        addHead.setWidget(5, 1, empIDText);
    //
    //	        Hpanel.add(addbutton);
    //	        Hpanel.setSpacing(20);
    //	        Hpanel.add(Reset);
    //
    //	        Vpanel.add(Heading);
    //	        Vpanel.setSpacing(20);
    //	        Vpanel.add(addHead);
    //	        Vpanel.setSpacing(20);
    //	        Vpanel.add(Hpanel);
    //
    //	        // method for populating faculty list
    //	        connectService.methodFacultyPopulate(userid,
    //	            new AsyncCallback<CM_FacultyInfoGetter[]>() {
    //	                public void onFailure(Throwable arg0) {
    //	                    MessageBox.alert("Failure");
    //	                }
    //
    //	                public void onSuccess(CM_FacultyInfoGetter[] result) {
    //	                    if (result.length < 1) {
    //	                        MessageBox.alert("Alert",
    //	                            "No Faculty Exist in Institute",
    //	                            new MessageBox.AlertCallback() {
    //	                                public void execute() {
    //	                                    methodAddEntity();
    //	                                }
    //	                            });
    //	                    } else {
    //	                        fnameText.clear();
    //
    //	                        for (int i = 0; i < result.length; i++) {
    //	                            fnameText.addItem(result[i].getfacultyName());
    //	                        }
    //	                    }
    //	                }
    //	            });
    //
    //	        fnameText.addClickHandler(new ClickHandler() {
    //	                public void onClick(ClickEvent arg0) {
    //	                    if (fnameText.getItemCount() != 0) {
    //	                        String facultyName = fnameText.getValue(fnameText.getSelectedIndex());
    //
    //	                        connectService.methodEmployeePopulate(userid,
    //	                            facultyName,
    //	                            new AsyncCallback<String>() {
    //	                                public void onFailure(Throwable arg0) {
    //	                                    MessageBox.alert("Failure");
    //	                                }
    //
    //	                                public void onSuccess(String result) {
    //	                                    tempListBox.clear();
    //	                                    empNameList.clear();
    //
    //	                                    int init = 0;
    //	                                    int k = 0;
    //	                                    String str = "";
    //
    //	                                    //putting employee's list in respective ListBoxes.
    //	                                    for (int i = 0; i < result.length(); i++) {
    //	                                        if (result.charAt(i) == '|') {
    //	                                            str = result.substring(init, i);
    //	                                            init = i + 1;
    //
    //	                                            if (k == 0) {
    //	                                                empNameList.addItem(str, str);
    //	                                                k = 1;
    //	                                            } else if (k == 1) {
    //	                                                tempListBox.addItem(str, str);
    //	                                                k = 0;
    //	                                            }
    //	                                        }
    //	                                    }
    //	                                }
    //	                            });
    //	                    }
    //	                }
    //	            });
    //
    //	        empNameList.addClickHandler(new ClickHandler() {
    //	                public void onClick(ClickEvent arg0) {
    //	                    if (empNameList.getItemCount() != 0) {
    //	                        empIDText.setValue(tempListBox.getValue(
    //	                                empNameList.getSelectedIndex()));
    //	                    }
    //	                }
    //	            });
    //
    //	        addbutton.addListener(new ButtonListenerAdapter() {
    //	                public void onClick(Button addButton, EventObject e) {
    //	                    if ((empNameList.getItemCount() != 0) ||
    //	                            (fnameText.getItemCount() != 0)) {
    //	                        final String facultyName = fnameText.getValue(fnameText.getSelectedIndex());
    //	                        String employeeID = empIDText.getText();
    //
    //	                        if ((employeeID.trim()).equals("")) {
    //	                            MessageBox.alert("Please select employee first");
    //	                        } else {
    //	                            connectService.methodAddFacultyHead(userid,
    //	                                facultyName, employeeID,
    //	                                new AsyncCallback<String>() {
    //	                                    public void onFailure(Throwable arg0) {
    //	                                        MessageBox.alert("Failure");
    //	                                    }
    //
    //	                                    public void onSuccess(String result) {
    //	                                        if (result.equals("Already exists")) {
    //	                                            MessageBox.alert(
    //	                                                "Faculty Head for " +
    //	                                                facultyName +
    //	                                                " already exists");
    //	                                        } else {
    //	                                            MessageBox.alert(
    //	                                                "Faculty Head successfully added");
    //	                                            addFacultyAdmin();
    //	                                        }
    //	                                    }
    //	                                });
    //	                        }
    //	                    }
    //	                }
    //	            });
    //
    //	        facultyflexTable.setWidget(0, 0, Vpanel);
    //	        Hpanel1.add(facultyflexTable);
    //	        FacultyPanelRight.add(Hpanel1);
    //	    }
    //
    //	    /**
    //	     * This method provides interface for editing faculty head.
    //	     */
    //	    public void editFacultyHead() {
    //	        final VerticalPanel Vpanel = new VerticalPanel();
    //	        HorizontalPanel Hpanel = new HorizontalPanel();
    //	        HorizontalPanel Hpanel1 = new HorizontalPanel();
    //	        Vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    //
    //	        FlexTable editadmin = new FlexTable();
    //
    //	        Label Heading = new Label("");
    //	        Heading.setStyleName("Heading");
    //	        Heading.setText("Edit faculty Head");
    //
    //	        Vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    //	        Vpanel.setHeight("492px");
    //	        Vpanel.setWidth("600px");
    //
    //	        Label empOldNameLabel = new Label("Faculty Head's Name :");
    //	        Label empOldIDLabel = new Label("Faculty Head's ID :");
    //	        Label empNamelabel = new Label("Employee Name :");
    //	        Label empIDLabel = new Label("Employee ID :");
    //
    //	        final ListBox fnameText = new ListBox();
    //	        final ListBox empNameList = new ListBox();
    //	        final TextField empIDText = new TextField();
    //	        final ListBox tempListBox = new ListBox();
    //
    //	        final TextField empOldNameText = new TextField();
    //	        final TextField empOldIDText = new TextField();
    //
    //	        Button editbutton = new Button("Edit");
    //	        Button Reset = new Button("Reset");
    //
    //	        Reset.addListener(new ButtonListenerAdapter() {
    //	                public void onClick(Button resetButton, EventObject e) {
    //	                    editFacultyHead();
    //	                }
    //	            });
    //
    //	        editadmin.setWidget(0, 0, fnameLabel);
    //	        editadmin.setWidget(0, 1, fnameText);
    //	        editadmin.setWidget(2, 0, empOldNameLabel);
    //	        editadmin.setWidget(2, 1, empOldNameText);
    //	        editadmin.setWidget(3, 0, empOldIDLabel);
    //	        editadmin.setWidget(3, 1, empOldIDText);
    //	        editadmin.setWidget(4, 0, empNamelabel);
    //	        editadmin.setWidget(4, 1, empNameList);
    //	        editadmin.setWidget(5, 0, empIDLabel);
    //	        editadmin.setWidget(5, 1, empIDText);
    //
    //	        // method for populating faculty list
    //	        connectService.methodFacultyPopulate(userid,
    //	            new AsyncCallback<CM_FacultyInfoGetter[]>() {
    //	                public void onFailure(Throwable arg0) {
    //	                    MessageBox.alert("Failure");
    //	                }
    //
    //	                public void onSuccess(CM_FacultyInfoGetter[] result) {
    //	                    if (result.length <= 1) {
    //	                        MessageBox.alert("Alert",
    //	                            "No Faculty Exist in Institute",
    //	                            new MessageBox.AlertCallback() {
    //	                                public void execute() {
    //	                                    methodAddEntity();
    //	                                }
    //	                            });
    //	                    } else {
    //	                        fnameText.clear();
    //	                        headEditIndex = -1;
    //
    //	                        //                        int init = 0;
    //	                        //                        String str = "";
    //	                        for (int i = 0; i < result.length; i++) {
    //	                            //                            if (result.charAt(i) == '|') {
    //	                            //                                str = result.substring(init, i);
    //	                            fnameText.addItem(result[i].getfacultyName());
    //
    //	                            //
    //	                            //                                init = i + 1;
    //	                            //                            }
    //	                        }
    //	                    }
    //	                }
    //	            });
    //
    //	        fnameText.addClickHandler(new ClickHandler() {
    //	                public void onClick(ClickEvent arg0) {
    //	                    if (fnameText.getItemCount() != 0) {
    //	                        final String facultyName = fnameText.getValue(fnameText.getSelectedIndex());
    //	                        int index = fnameText.getSelectedIndex();
    //
    //	                        if (headEditIndex != index) {
    //	                            headEditIndex = index;
    //	                            connectService.methodFacultyHeadPopulate(userid,
    //	                                facultyName,
    //	                                new AsyncCallback<String>() {
    //	                                    public void onFailure(Throwable arg0) {
    //	                                        MessageBox.alert("Failure");
    //	                                    }
    //
    //	                                    public void onSuccess(String result) {
    //	                                        if (result.equals("FH dont exist")) {
    //	                                            MessageBox.alert(
    //	                                                "Faculty Head for " +
    //	                                                facultyName + " donot exist");
    //	                                            empOldNameText.setValue("");
    //	                                            empOldIDText.setValue("");
    //	                                            tempListBox.clear();
    //	                                            empNameList.clear();
    //	                                            empIDText.setValue("");
    //	                                        } else {
    //	                                            int init = 0;
    //	                                            int k = 0;
    //	                                            String str = "";
    //
    //	                                            //putting faculty Head's name and ID in respective TextFieldes.
    //	                                            for (int i = 0;
    //	                                                    i < result.length(); i++) {
    //	                                                if (result.charAt(i) == '|') {
    //	                                                    str = result.substring(init,
    //	                                                            i);
    //	                                                    init = i + 1;
    //
    //	                                                    if (k == 0) {
    //	                                                        empOldNameText.setValue(str);
    //	                                                        k = 1;
    //	                                                    } else if (k == 1) {
    //	                                                        empOldIDText.setValue(str);
    //	                                                        k = 2;
    //	                                                    } else if (k == 2) {
    //	                                                        oldFAUserID = str;
    //	                                                        k = 0;
    //	                                                    }
    //	                                                }
    //	                                            }
    //
    //	                                            connectService.methodEmployeePopulate(userid,
    //	                                                facultyName,
    //	                                                new AsyncCallback<String>() {
    //	                                                    public void onFailure(
    //	                                                        Throwable arg0) {
    //	                                                        MessageBox.alert(
    //	                                                            "Failure");
    //	                                                    }
    //
    //	                                                    public void onSuccess(
    //	                                                        String result) {
    //	                                                        tempListBox.clear();
    //	                                                        empNameList.clear();
    //
    //	                                                        int init = 0;
    //	                                                        int k = 0;
    //	                                                        String str = "";
    //
    //	                                                        for (int i = 0;
    //	                                                                i < result.length();
    //	                                                                i++) {
    //	                                                            if (result.charAt(i) == '|') {
    //	                                                                str = result.substring(init,
    //	                                                                        i);
    //	                                                                init = i + 1;
    //
    //	                                                                if (k == 0) {
    //	                                                                    empNameList.addItem(str,
    //	                                                                        str);
    //	                                                                    k = 1;
    //	                                                                } else if (k == 1) {
    //	                                                                    tempListBox.addItem(str,
    //	                                                                        str);
    //	                                                                    k = 0;
    //	                                                                }
    //	                                                            }
    //	                                                        }
    //	                                                    }
    //	                                                });
    //	                                        }
    //	                                    }
    //	                                });
    //	                        }
    //	                    }
    //	                }
    //	            });
    //	        //click Handler for list of employees
    //	        empNameList.addClickHandler(new ClickHandler() {
    //	                public void onClick(ClickEvent arg0) {
    //	                    if (empNameList.getItemCount() != 0) {
    //	                        empIDText.setValue(tempListBox.getValue(
    //	                                empNameList.getSelectedIndex()));
    //	                    }
    //	                }
    //	            });
    //	        editbutton.addListener(new ButtonListenerAdapter() {
    //	                public void onClick(Button edtButton, EventObject e) {
    //	                    if ((fnameText.getItemCount() != 0) ||
    //	                            (empNameList.getItemCount() != 0)) {
    //	                        String facultyName = fnameText.getValue(fnameText.getSelectedIndex());
    //	                        String employeeID = empIDText.getText();
    //	                        String OldFAuID = oldFAUserID;
    //
    //	                        if ((employeeID.trim()).equals("")) {
    //	                            MessageBox.alert("Please select employee first");
    //	                        } else {
    //	                            connectService.methodEditFacultyHead(userid,
    //	                                facultyName, employeeID, OldFAuID,
    //	                                new AsyncCallback<String>() {
    //	                                    public void onFailure(Throwable arg0) {
    //	                                        MessageBox.alert("Failure");
    //	                                    }
    //
    //	                                    public void onSuccess(String arg0) {
    //	                                        MessageBox.alert(
    //	                                            "Faculty Head successfully edited");
    //	                                        editFacultyHead();
    //	                                    }
    //	                                });
    //	                        }
    //	                    }
    //	                }
    //	            });
    //
    //	        Hpanel.add(editbutton);
    //	        Hpanel.setSpacing(20);
    //	        Hpanel.add(Reset);
    //
    //	        Vpanel.add(Heading);
    //	        Vpanel.setSpacing(20);
    //	        Vpanel.add(editadmin);
    //	        Vpanel.setSpacing(20);
    //	        Vpanel.add(Hpanel);
    //
    //	        methodFacultyPopulate();
    //
    //	        facultyflexTable.setWidget(0, 0, Vpanel);
    //	        Hpanel1.add(facultyflexTable);
    //	        FacultyPanelRight.add(Hpanel1);
    //	    }
}
