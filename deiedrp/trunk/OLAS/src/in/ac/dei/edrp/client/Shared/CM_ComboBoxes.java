package in.ac.dei.edrp.client.Shared;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;


public class CM_ComboBoxes {
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    String[][] object1;
    String[][] object2;
    String[][] object3;
    String[][] object4;
    String userId;
    public final ComboBox entityDescCB = new ComboBox();
    final ComboBox paperCodeCB = new ComboBox();
    public final ComboBox firstDegCodeCB = new ComboBox();
    public final ComboBox boardCB = new ComboBox();
    public ComboBox progCombo = new ComboBox();
    public final ComboBox entityCombo = new ComboBox();
    public final ComboBox branchCombo = new ComboBox();
    public final ComboBox SpecialziationCombo = new ComboBox();
    public final ComboBox categoryCB = new ComboBox();
    int count = 0;

    public CM_ComboBoxes(String userID) {
        this.userId = userID;
    }
    

    public void onModuleLoad() {
        entityCombo.setEmptyText("Select Entity Name");
        entityCombo.setForceSelection(true);
        entityCombo.setMinChars(1);
        entityCombo.setDisplayField("EntityName");
        entityCombo.setValueField("Entitycode");
        entityCombo.setMode(ComboBox.LOCAL);
        entityCombo.setTriggerAction(ComboBox.ALL);
        entityCombo.setLoadingText("Searching...");
        entityCombo.setTypeAhead(true);
        entityCombo.setSelectOnFocus(true);
        entityCombo.setHideTrigger(false);
        entityCombo.setReadOnly(true);

        branchCombo.setEmptyText("Select Branch");
        branchCombo.setForceSelection(true);
        branchCombo.setMinChars(1);
        branchCombo.setDisplayField("BranchName");
        branchCombo.setValueField("BranchCode");
        branchCombo.setMode(ComboBox.LOCAL);
        branchCombo.setTriggerAction(ComboBox.ALL);
        branchCombo.setLoadingText("Searching...");
        branchCombo.setTypeAhead(true);
        branchCombo.setSelectOnFocus(true);
        branchCombo.setHideTrigger(false);
        branchCombo.setReadOnly(true);

        SpecialziationCombo.setEmptyText("Select Branch");
        SpecialziationCombo.setForceSelection(true);
        SpecialziationCombo.setMinChars(1);
        SpecialziationCombo.setDisplayField("SpecialziationName");
        SpecialziationCombo.setValueField("SpecialziationCode");
        SpecialziationCombo.setMode(ComboBox.LOCAL);
        SpecialziationCombo.setTriggerAction(ComboBox.ALL);
        SpecialziationCombo.setLoadingText("Searching...");
        SpecialziationCombo.setTypeAhead(true);
        SpecialziationCombo.setSelectOnFocus(true);
        SpecialziationCombo.setHideTrigger(false);
        SpecialziationCombo.setReadOnly(true);

        progCombo.setForceSelection(true);
        progCombo.setMinChars(1);
        progCombo.setDisplayField("Prog");
        progCombo.setValueField("ProgCode");
        progCombo.setMode(ComboBox.LOCAL);
        progCombo.setTriggerAction(ComboBox.ALL);
        progCombo.setEmptyText("Select Programme");
        progCombo.setLoadingText("Searching...");
        progCombo.setTypeAhead(true);
        progCombo.setSelectOnFocus(true);
        progCombo.setHideTrigger(false);
        progCombo.setReadOnly(true);

        categoryCB.setForceSelection(true);
        categoryCB.setMinChars(1);
        categoryCB.setFieldLabel("Category");
        categoryCB.setValueField("Categorycode");
        categoryCB.setDisplayField("Categoryname");
        categoryCB.setMode(ComboBox.LOCAL);
        categoryCB.setTriggerAction(ComboBox.ALL);
        categoryCB.setEmptyText("Select Category");
        categoryCB.setLoadingText("Searching...");
        categoryCB.setTypeAhead(true);
        categoryCB.setSelectOnFocus(true);
        categoryCB.setHideTrigger(false);
        categoryCB.setReadOnly(true);        

        final Panel panel = new Panel();
        panel.setBorder(false);
        panel.setPaddings(15);

        final FormPanel form = new FormPanel();
        form.setLabelWidth(35);
        form.setBorder(false);

        entityDescCB.setForceSelection(true);
        entityDescCB.setMinChars(1);
        entityDescCB.setDisplayField("entityDescription");
        entityDescCB.setValueField("entityType");
        entityDescCB.setMode(ComboBox.LOCAL);
        entityDescCB.setTriggerAction(ComboBox.ALL);
        entityDescCB.setEmptyText("Select Entity Type");
        entityDescCB.setLoadingText("Searching...");
        entityDescCB.setTypeAhead(true);
        entityDescCB.setSelectOnFocus(true);
        entityDescCB.setHideTrigger(false);
        entityDescCB.setReadOnly(true);

        /**
         * method updated
         */
        connectTemp.Entity_Description(userId,
            new AsyncCallback<CM_entityInfoGetter[]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert("Sql Exception", caught.getMessage());
                }

                public void onSuccess(CM_entityInfoGetter[] arg0) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("entityDescription"),
                                new StringFieldDef("entityType")
                            });

                    object1 = new String[arg0.length][2];

                    String str = null;

                    try {
                        for (int i = 0; i < arg0.length; i++) {
                            for (int k = 0; k < 2; k++) {
                                if (k == 0) {
                                    str = arg0[i].getEntity_description();
                                } else if (k == 1) {
                                    str = arg0[i].getEntity_type();
                                }

                                object1[i][k] = str;
                            }
                        }
                    } catch (Exception e2) {
                        System.out.println("e2     " + e2);
                    }

                    Object[][] data = object1;

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    entityDescCB.setStore(store);
                }
            });
                
        
        /**
         * method updated
         */
        connectTemp.Category(userId,
            new AsyncCallback<CM_StudentInfoGetter[]>() {
                public void onFailure(Throwable arg0) {
                }

                public void onSuccess(CM_StudentInfoGetter[] arg0) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("Categoryname"),
                                new StringFieldDef("Categorycode")
                            });
                    object2 = new String[arg0.length][2];

                    String str = null;

                    try {
                        for (int i = 0; i < arg0.length; i++) {
                            for (int k = 0; k < 2; k++) {
                                if (k == 0) {
                                    str = arg0[i].getCategory();
                                } else if (k == 1) {
                                    str = arg0[i].getCategory_code();
                                }

                                object2[i][k] = str;
                            }
                        }
                    } catch (Exception e2) {
                        System.out.println("e2     " + e2);
                    }

                    Object[][] data = object2;

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    categoryCB.setStore(store);

                    // RootPanel.get().add(panel);  
                }
            });        
    }

    public void PaperCode(String Program) {
        paperCodeCB.setForceSelection(true);
        paperCodeCB.setMinChars(1);
        paperCodeCB.setDisplayField("paperDescription");
        paperCodeCB.setValueField("paperCode");
        paperCodeCB.setMode(ComboBox.LOCAL);
        paperCodeCB.setTriggerAction(ComboBox.ALL);
        paperCodeCB.setEmptyText("Select");
        paperCodeCB.setLoadingText("Searching...");
        paperCodeCB.setTypeAhead(true);
        paperCodeCB.setSelectOnFocus(true);
        paperCodeCB.setWidth(130);
        paperCodeCB.setHideTrigger(false);
        paperCodeCB.setReadOnly(true);
    }

    public void FirstDegree(final String ProgID, final String Type) {
        firstDegCodeCB.setForceSelection(true);
        firstDegCodeCB.setMinChars(1);
        firstDegCodeCB.setDisplayField("DegreeDescription");
        firstDegCodeCB.setValueField("DegreeCode");
        firstDegCodeCB.setMode(ComboBox.LOCAL);
        firstDegCodeCB.setTriggerAction(ComboBox.ALL);
        firstDegCodeCB.setEmptyText("Select");
        firstDegCodeCB.setLoadingText("Searching...");
        firstDegCodeCB.setTypeAhead(true);
        firstDegCodeCB.setSelectOnFocus(true);
        firstDegCodeCB.setWidth(130);
        firstDegCodeCB.setHideTrigger(false);
        firstDegCodeCB.setReadOnly(true);

        connectTemp.FirstDegreeCode(ProgID, Type,
            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert("Sql Exception", caught.getMessage());
                }

                public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                new StringFieldDef("DegreeDescription"),
                                new StringFieldDef("DegreeCode")
                            });

                    object3 = new String[arg0.length][2];

                    String str = null;

                    try {
                        for (int i = 0; i < arg0.length; i++) {
                            for (int k = 0; k < 2; k++) {
                                if (k == 0) {
                                    str = arg0[i].getProgram_name();
                                } else if (k == 1) {
                                    str = arg0[i].getProgram_id();
                                }

                                object3[i][k] = str;
                            }
                        }
                    } catch (Exception e2) {
                        System.out.println("e2     " + e2);
                    }

                    Object[][] data = object3;

                    MemoryProxy proxy = new MemoryProxy(data);

                    ArrayReader reader = new ArrayReader(recordDef);
                    Store store = new Store(proxy, reader);
                    store.load();

                    firstDegCodeCB.setStore(store);
                }
            });
    }

    public void BoardName() {
        boardCB.setForceSelection(true);
        boardCB.setMinChars(1);
        boardCB.setDisplayField("BoardName");
        boardCB.setValueField("BoardCode");
        boardCB.setMode(ComboBox.LOCAL);
        boardCB.setTriggerAction(ComboBox.ALL);
        boardCB.setEmptyText("Select");
        boardCB.setLoadingText("Searching...");
        boardCB.setTypeAhead(true);
        boardCB.setSelectOnFocus(true);
        boardCB.setWidth(130);
        boardCB.setHideTrigger(false);

        connectTemp.getBoard(new AsyncCallback<CM_StudentInfoGetter[]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert("Sql Exception", caught.getMessage());
                }

                public void onSuccess(CM_StudentInfoGetter[] arg0) {
                    if (arg0.length > 0) {
                        RecordDef recordDef = new RecordDef(new FieldDef[] {
                                    new StringFieldDef("BoardName"),
                                    new StringFieldDef("BoardCode"),
                                });

                        object4 = new String[arg0.length][2];

                        String str = null;

                        try {
                            for (int i = 0; i < arg0.length; i++) {
                                for (int k = 0; k < 2; k++) {
                                    if (k == 0) {
                                        str = arg0[i].getBoard_name();
                                    } else if (k == 1) {
                                        str = arg0[i].getBoard_id();
                                    }

                                    object4[i][k] = str;
                                }
                            }
                        } catch (Exception e2) {
                            System.out.println("e2     " + e2);
                        }

                        Object[][] data = object4;

                        MemoryProxy proxy = new MemoryProxy(data);

                        ArrayReader reader = new ArrayReader(recordDef);
                        Store store = new Store(proxy, reader);
                        store.load();

                        boardCB.setStore(store);
                    }
                }
            });
    }
}
