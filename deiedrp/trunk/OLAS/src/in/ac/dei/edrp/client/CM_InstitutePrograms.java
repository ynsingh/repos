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
package in.ac.dei.edrp.client;


import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;
import in.ac.dei.edrp.client.Shared.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.core.SortDir;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
import com.gwtext.client.data.GroupingStore;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.SortState;
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
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.GroupingView;
import com.gwtext.client.widgets.grid.RowNumberingColumnConfig;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;


/**
 * This class creates Programme Master menu for institute administrator containing methods for
 * adding, editing, deletion and view Programme details.
 */
@SuppressWarnings("deprecation")
public class CM_InstitutePrograms {
	
	CM_progMasterInfoGetter input=new CM_progMasterInfoGetter();
	constants cons=GWT.create(constants.class);
	messages msgs=GWT.create(messages.class);
	
    private final CM_connectAsync connectService = GWT.create(CM_connect.class);
    Validator validator=new Validator();
//    List<program_data> list=new ArrayList<program_data>();
    
    final FlexTable tableFlexTable = new FlexTable();
    final HorizontalPanel HoriPanel = new HorizontalPanel();
    final VerticalPanel VertiPanel = new VerticalPanel();
    final Button termOKButton = new Button(cons.okButton());
    final Button termSaveButton = new Button(cons.saveButton());
    
    Object programNameObject[][];
    
    Object[][] months=null;
    Object[][] days =null;
    Store monthStore;
    Store daysStore;
    Store branchstore;
    Store specstore;
    Store catstore;
    
    
	GridPanel g1 = null;
	GridPanel g2=null; 
	
	int count=0,branchcount=0;
    int next=0;
    int next1=0;
    int specCount=0;
//    String arr[]=null;
    String progCode[]=null;
//    ComboBox specText1[][]=null;
    
    
    
    GridPanel[] specgrid=new GridPanel[50];
    BaseColumnConfig[] speccolumns=null;  
    ColumnModel columnModel;
    final CheckboxSelectionModel cbSelectionModelspec = new CheckboxSelectionModel();
   CheckboxSelectionModel[] cbSelectionModelspec1 = new CheckboxSelectionModel[50];
      
   
   
Object[][] object1;
   

//    CM_InstituteFaculty CMIF;
    HorizontalPanel ProgrammeHorizontalPanel = new HorizontalPanel();
    public HorizontalPanel ProgrammeHorizontalPanel1 = new HorizontalPanel();
    FlexTable ProgrammeflexTable = new FlexTable();
    Label AddLabel = new Label(cons.label_addProgram());
    /*
     * code temporarily removed
     */
//    Label InstitutenameLabel = new Label("Institute Name :");
//    Label sessionLabel = new Label("Session :");
    Label entityLabel = new Label(cons.entityType());
    Label entityNameLabel = new Label(cons.entityName());
    Label programModeLabel=new Label(cons.label_programMode());
    Label programTypeLabel=new Label(cons.label_programType());
    Label ProgrammeCodeLabel = new Label(cons.label_programCode());
    Label ProgrammeNameLabel = new Label(cons.label_programName());
    Label semesterLabel = new Label(cons.label_numberOfTerms());
    Label creditsLabel = new Label(cons.label_totalCredits());
    Label seatsLabel = new Label(cons.label_noOfSeats());
    public ListBox userNameTextListBox = new ListBox();
    String select = "";
    String empty = "";
    String s1;
    String ss;
    
	int j=0,k=0,r=0;
	
 	int multipleStart = 0;
 	int termCount=0;
    final ListBox CodeTextListBox = new ListBox();
    final ListBox EditNameTextListBox = new ListBox();
    final TextField EditTextField = new TextField();
    final ListBox DeleteCodeListBox = new ListBox();
    final ListBox DeleteNameListBox = new ListBox();
    ComboBox entityCombo = new ComboBox();
    ComboBox entityNameCombo = new ComboBox();
    final Label nameText = new Label();
    final Label sessionText = new Label();
    Tree ProgrammesTree = new Tree();
    public String userid;
    Boolean nextFlag=true;

    /**
     * Constructor for setting the Value of User ID
     * @param Uid
     */
    public CM_InstitutePrograms(String Uid) {
        this.userid = Uid;
    }

    /**
     * Main method of class for creating tree for Programmes that is to be added
     * in accordion panel with selection handlers. Selection handler call methods
     *  for displaying corresponding pages on right hand side with their functionality.
     *  @return FlexTable.
     */
 /*
  * Code temporarily removed (kept for reference for using accordian panel tree)
  */
//    public FlexTable InstituteProgramme() {
//        TreeItem addProgrammeTreeItem = new TreeItem("Add Programme");
//        TreeItem editProgrammeTreeItem = new TreeItem("Edit Programme");
//        TreeItem deleteProgrammeTreeItem = new TreeItem("Delete Programme");
//        TreeItem viewProgrammeTreeItem = new TreeItem("View Programme List");
//        ProgrammesTree.addItem(addProgrammeTreeItem);
//        ProgrammesTree.addItem(editProgrammeTreeItem);
//        ProgrammesTree.addItem(deleteProgrammeTreeItem);
//        ProgrammesTree.addItem(viewProgrammeTreeItem);
//        ProgrammeHorizontalPanel.add(ProgrammesTree);
//
//        /*
//         * Adds Click handler to <code>ProgrammesTree</code>
//         */
//        ProgrammesTree.addSelectionHandler(new SelectionHandler() {
//                public void onSelection(SelectionEvent event) {
//                    String check = "Add Programme";
//                    String checkd = "Delete Programme";
//                    String checkv = "View Programme List";
//                    String checke = "Edit Programme";
//
//                    if (check.equals(
//                                ((TreeItem) (event.getSelectedItem())).getText())) {
//                        methodAddProgramme();
//                    } else if (checkd.equals(
//                                ((TreeItem) (event.getSelectedItem())).getText())) {
////                        methodDelete();
//                    } else if (checkv.equals(
//                                ((TreeItem) (event.getSelectedItem())).getText())) {
////                        methodProgrammeView();
//                    } else if (checke.equals(
//                                ((TreeItem) (event.getSelectedItem())).getText())) {
////                        methodEdit();
//                    }
//                }
//            });
//
//        return ProgrammeflexTable;
//    }

    /*
     * This method populates institute name and current session to which the user belongs.
     */
//    public void methodShowSession() {
//        connectService.methodShowOldSession(userid,
//            new AsyncCallback<String>() {
//                public void onFailure(Throwable caught) {
//                    MessageBox.show(new MessageBoxConfig() {
//
//                            {
//                                setTitle("Error");
//                                setMsg("Failure");
//                                setIconCls(MessageBox.ERROR);
//                                setButtons(MessageBox.OK);
//                            }
//                        });
//                }
//
//                public void onSuccess(String result) {
//                    int init = 0;
//                    int k = 0;
//                    String str = "";
//
//                    //putting institute name and session in respective TextFields.
//                    for (int i = 0; i < result.length(); i++) {
//                        if (result.charAt(i) == '|') {
//                            str = result.substring(init, i);
//                            init = i + 1;
//
//                            if (k == 0) {
//                                nameText.setText(str);
//                                nameText.setTitle(str);
//                                k = 1;
//                            }
//
//                            if (k == 1) {
//                                sessionText.setText("Session : "+str);
//                            }
//                        }
//                    }
//                }
//            });
//
//    }
    
    
    
    
    
   /************************************Method Add Program***********************************************/ 

    /**
     * Method provides an interface for adding Programmes in different departments.
     */
    public void methodAddProgramme() {
    	
    	nextFlag=true;

        final HorizontalPanel ProgrammeHorizontalPanel = new HorizontalPanel();

        
        ProgrammeHorizontalPanel.clear();

        AddLabel.setStyleName("Heading");
 

//        final ListBox departmentList = new ListBox();
        final TextField codeText = new TextField();
        final TextField cNameText = new TextField();
        final NumberField semesterText = new NumberField();
        final NumberField creditText = new NumberField();
        final NumberField seatsText = new NumberField();
        final ComboBox programModeComboBox=new ComboBox();
        final ComboBox programTypeCombo=new ComboBox();
        final Button next2Button=new Button(cons.nextButton());
        final Button nextBranchButton=new Button(cons.nextButton());
        final Button nextSpecButton=new Button(cons.nextButton());
        
        codeText.setAllowBlank(false);
        cNameText.setAllowBlank(false);
        semesterText.setAllowBlank(false);
        creditText.setAllowBlank(false);
        seatsText.setAllowBlank(false);
        next2Button.setDisabled(true);
        
        
/*
 * code to be uncommented
 */
//        codeText.setMaxLength(6);
        semesterText.setMaxLength(2);
//        codeText.setMinLength(6);
        semesterText.setAllowDecimals(false);
        seatsText.setAllowDecimals(false);
        creditText.setMaxLength(6);
        creditText.setDecimalPrecision(2);
        


//        Button ProgrammeAddButton = new Button("Add");
        Button ProgrammeNextButton = new Button(cons.nextButton());
        Button termNextButton = new Button(cons.nextButton());
        Button Clear = new Button(cons.resetButton());

        
        
        HoriPanel.clear();
       HoriPanel.add(termNextButton);
 /*
  * If we want to add term details within this flow
  *
  *       HoriPanel.add(ProgrammeNextButton);
  */
        HoriPanel.setSpacing(20);
        HoriPanel.add(Clear);
        
        entityCombo.setEmptyText(cons.select());
        entityNameCombo.setEmptyText(cons.select());

    
        final CheckBox branchCheckBox=new CheckBox(cons.ifBranches());
        final CheckBox specCheckBox=new CheckBox(cons.ifSpecialization());
        
        Label attemptsLabel=new Label(cons.label_noOfAttempts()); 
        final NumberField attemptField=new NumberField();
        Label maxFailCourseLabel=new Label(cons.label_maxFail()); 
        final NumberField maxFailCourseField=new NumberField();
        final RadioButton fixedRadio=new RadioButton("abc",cons.fixedDuration());
        final RadioButton floatRadio=new RadioButton("abc",cons.floatingDuration());
        final Label durationLabel=new Label(cons.duration());
        
        
        
        Label tencodesLabel=new Label(cons.tenCodes());
    	final ComboBox tencodescomboBox=new ComboBox();
        
        attemptField.setAllowBlank(false);
        maxFailCourseField.setAllowBlank(false);
        attemptField.setAllowDecimals(false);
        maxFailCourseField.setAllowDecimals(false);
        attemptField.setMaxLength(3);
        maxFailCourseField.setMaxLength(3);
        
        
        Label ugpgLabel=new Label(cons.ugPg()); 
        final ComboBox ugpgComboBox=new ComboBox();
        ugpgComboBox.setEmptyText(cons.select());

        HorizontalPanel HoriPanel2=new HorizontalPanel();
    	final DecoratorPanel dpanel=new DecoratorPanel();
    	HoriPanel2.setSpacing(20);
    	HoriPanel2.add(fixedRadio);
    	HoriPanel2.add(floatRadio);
    	dpanel.add(HoriPanel2);
    	
    	programModeComboBox.setForceSelection(true);
    	programModeComboBox.setMinChars(1);
    	programModeComboBox.setFieldLabel("Mode");
    	programModeComboBox.setDisplayField("mode_name");
    	programModeComboBox.setValueField("program_mode");
    	programModeComboBox.setMode(ComboBox.LOCAL);
    	programModeComboBox.setTriggerAction(ComboBox.ALL);
    	programModeComboBox.setEmptyText(cons.label_programMode());
    	programModeComboBox.setLoadingText(cons.loading());
    	programModeComboBox.setTypeAhead(true);
    	programModeComboBox.setSelectOnFocus(true);
    	programModeComboBox.setWidth(190);
    	programModeComboBox.setHideTrigger(false);
    	
    	
    	programTypeCombo.setForceSelection(true);
    	programTypeCombo.setMinChars(1);
    	programTypeCombo.setFieldLabel("Type");
    	programTypeCombo.setDisplayField("type_name");
    	programTypeCombo.setValueField("program_type");
    	programTypeCombo.setMode(ComboBox.LOCAL);
    	programTypeCombo.setTriggerAction(ComboBox.ALL);
    	programTypeCombo.setEmptyText(cons.label_programType());
    	programTypeCombo.setLoadingText(cons.loading());
    	programTypeCombo.setTypeAhead(true);
    	programTypeCombo.setSelectOnFocus(true);
    	programTypeCombo.setWidth(190);
    	programTypeCombo.setHideTrigger(false);
    	
    	ugpgComboBox.setForceSelection(true);
    	ugpgComboBox.setMinChars(1);
//    	ugpgComboBox.setFieldLabel("Type");
    	ugpgComboBox.setDisplayField("UGPG");
    	ugpgComboBox.setValueField("id");
    	ugpgComboBox.setMode(ComboBox.LOCAL);
    	ugpgComboBox.setTriggerAction(ComboBox.ALL);
    	ugpgComboBox.setEmptyText(cons.ugPg());
    	ugpgComboBox.setLoadingText(cons.loading());
    	ugpgComboBox.setTypeAhead(true);
    	ugpgComboBox.setSelectOnFocus(true);
    	ugpgComboBox.setWidth(190);
    	ugpgComboBox.setHideTrigger(false);
    	
    	RecordDef recordDef = new RecordDef(new FieldDef[] {
                new StringFieldDef("UGPG"),
                new StringFieldDef("id")
                
            });
	
    String[][] object2 = new String[2][2];

    String[][] data = object2;
    
        object2[0][0] = "Under Graduate";
        object2[0][1] = "U";
        object2[1][0] = "Post Graduate";
        object2[1][1] = "P";

    MemoryProxy proxy = new MemoryProxy(data);

    ArrayReader reader = new ArrayReader(recordDef);
    Store store = new Store(proxy, reader);
    store.load();

    ugpgComboBox.setStore(store);
    	
    	

    	
    	
    	RecordDef recordDef1 = new RecordDef(new FieldDef[] {
                new StringFieldDef("tencodes"),
            });
	
    String[][] object21 = new String[10][1];

    String[][] data1 = object21;

    
        object21[0][0] = "UG";
        object21[1][0] = "PG";
        object21[2][0] = "PD";
        object21[3][0] = "DH";
        object21[4][0] = "DI";
        object21[5][0] = "HA";
        object21[6][0] = "HS";
        object21[7][0] = "IA";
        object21[8][0] = "IS";
        object21[9][0] = "PH";
        
    MemoryProxy proxy1 = new MemoryProxy(data1);

    ArrayReader reader1 = new ArrayReader(recordDef1);
    Store store1 = new Store(proxy1, reader1);
    store1.load();

    tencodescomboBox.setStore(store1);
    	
    	
    	tencodescomboBox.setForceSelection(true);
    	tencodescomboBox.setMinChars(1);
    	tencodescomboBox.setFieldLabel("ten");
    	tencodescomboBox.setDisplayField("tencodes");
    	tencodescomboBox.setMode(ComboBox.LOCAL);
    	tencodescomboBox.setTriggerAction(ComboBox.ALL);
    	tencodescomboBox.setEmptyText(cons.tenCodes());
    	tencodescomboBox.setLoadingText(cons.loading());
    	tencodescomboBox.setTypeAhead(true);
    	tencodescomboBox.setSelectOnFocus(true);
    	tencodescomboBox.setWidth(190);
    	tencodescomboBox.setHideTrigger(false);
    	
    	
    	
    	
    	
    	connectService.methodUniversityProgramMode(userid, new AsyncCallback<CM_ProgramInfoGetter[]>(){
			public void onFailure(Throwable arg0) {
				MessageBox.alert(msgs.failure(),arg0.getMessage());
			}

			public void onSuccess(CM_ProgramInfoGetter[] mode) {

                RecordDef recordDef = new RecordDef(new FieldDef[] {
                            new StringFieldDef("mode_name"),
                            new StringFieldDef("program_mode")
                        });

                String[][] object2 = new String[mode.length][2];

                String[][] data = object2;

                for (int i = 0; i < mode.length; i++) {
                    object2[i][0] = mode[i].getMode_name();
                    object2[i][1] = mode[i].getProgram_mode();
                }

                MemoryProxy proxy = new MemoryProxy(data);

                ArrayReader reader = new ArrayReader(recordDef);
                Store store = new Store(proxy, reader);
                store.load();

                programModeComboBox.setStore(store);
				
			}
    		
    	});
    
    	connectService.methodUniversityProgramType(userid, new AsyncCallback<CM_ProgramInfoGetter[]>(){
			public void onFailure(Throwable arg0) {
				MessageBox.alert(msgs.failure(),arg0.getMessage());
			}

			public void onSuccess(CM_ProgramInfoGetter[] type) {
				RecordDef recordDef = new RecordDef(new FieldDef[] {
						 new StringFieldDef("type_name"),
						 new StringFieldDef("program_type")
                    });
			
            String[][] object2 = new String[type.length][2];

            String[][] data = object2;

            for (int i = 0; i < type.length; i++) {
                object2[i][0] = type[i].getType_name();
                object2[i][1] = type[i].getProgram_type();
            }

            MemoryProxy proxy = new MemoryProxy(data);

            ArrayReader reader = new ArrayReader(recordDef);
            Store store = new Store(proxy, reader);
            store.load();

            programTypeCombo.setStore(store);
				
			}
    		
    	});
        
        
    	tableFlexTable.clear();
        tableFlexTable.setWidget(4, 10, ProgrammeCodeLabel);
        tableFlexTable.setWidget(4, 11, codeText);
       
        tableFlexTable.setWidget(5,10, ProgrammeNameLabel);
        tableFlexTable.setWidget(5, 11, cNameText);
        tableFlexTable.setWidget(7, 10, programTypeLabel);
        tableFlexTable.setWidget(7, 11, programTypeCombo);
        tableFlexTable.setWidget(6, 10, programModeLabel);
        tableFlexTable.setWidget(6,11, programModeComboBox);
//        tableFlexTable.setWidget(8,10, branchCheckBox);
        tableFlexTable.setWidget(10, 10, semesterLabel);
        tableFlexTable.setWidget(10, 11, semesterText);
        tableFlexTable.setWidget(11, 10, creditsLabel);
        tableFlexTable.setWidget(11, 11, creditText);
      tableFlexTable.setWidget(12,10, attemptsLabel);
      tableFlexTable.setWidget(12, 11, attemptField);
      tableFlexTable.setWidget(13,10, maxFailCourseLabel);
      tableFlexTable.setWidget(13, 11, maxFailCourseField);
      
      tableFlexTable.setWidget(14,10, ugpgLabel);
      tableFlexTable.setWidget(14, 11, ugpgComboBox);
      tableFlexTable.setWidget(15,10, tencodesLabel);
      tableFlexTable.setWidget(15, 11, tencodescomboBox);
  	
 
      	VertiPanel.clear();
        VertiPanel.add(AddLabel);
        VertiPanel.setSpacing(10);
        VertiPanel.add(nameText);
        VertiPanel.add(sessionText);
        VertiPanel.setSpacing(20);
        VertiPanel.add(tableFlexTable);
        VertiPanel.setSpacing(20);
        VertiPanel.add(HoriPanel);

        /*
         * Code Temporarily removed
                 //
                 //                  CreditText.addKeyDownHandler(new KeyDownHandler()
                 //                  {
                 //
                 //                        public void onKeyDown(KeyDownEvent event) {
                 //                                //System.out.println(event.getNativeKeyCode());
                 //                        if((event.getNativeKeyCode()==8) || (event.getNativeKeyCode()==190) || (event.getNativeKeyCode()==46) || (event.getNativeKeyCode()==13)
                 //                                                || (event.getNativeKeyCode()==37) || (event.getNativeKeyCode()==38) || (event.getNativeKeyCode()==39) || (event.getNativeKeyCode()==40))
                 //                                        {}
                 //                                else if((event.getNativeKeyCode()<48) || (event.getNativeKeyCode()>57))
                 //                                        {
                 //                                                //MessageBox.alert("Only Numerics Allowed");
                 //                                        CreditText.cancelKey();
                 //
                 //
                 //                                        }
                 //
                 //                        }
                 //
                 //                  });
         */
       
//        methodShowSession();
       

        ProgrammeflexTable.setWidget(0, 0, VertiPanel);

        ProgrammeHorizontalPanel.add(ProgrammeflexTable);
        ProgrammeHorizontalPanel1.clear();
        ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);

        

        /*
         * Adds click handler to <code>Clear</code> button
         */
        Clear.addListener(new ButtonListenerAdapter() {
                public void onClick(Button Clear, EventObject e) {
                    methodAddProgramme();
                }
            });
        
        
        /*
         * Adds Click Handler to <code>ProgrammeNextButton</code>
         * 
         * 
         * This method is to be called to add each termwise details
         * 
         * code not in use yet (to be used later)
         * 
         */
        ProgrammeNextButton.addListener(new ButtonListenerAdapter() {
         	public void onClick(Button ProgrammeAddButton, EventObject e) {
            	
            	//Store all values of previous page
            	
         		final int numberOfTerms=Integer.parseInt(semesterText.getText());
         		termCount=1;
         		
            	FormPanel termDetailForm=new FormPanel();
            	HoriPanel.clear();
            	HoriPanel.add(termOKButton);
            	
            	//Store term details in arrays
            	termDetailForm.setFrame(true);
            	termDetailForm.setBorder(true);
            	
            	Label progNameLabel=new Label("program Name ");
            	Label termNumberLabel=new Label("Term Number ");            	
            	Label minSGPALabel=new Label("Minimum SGPA ");
            	Label termNameLabel=new Label("Term Name ");
            	Label teachingDaysLabel=new Label("Number of Teaching Days ");
            	Label durationLabel=new Label("Duration (in weeks) ");
            	Label termStartLabel=new Label("Term Start Date ");
            	Label termEndLabel=new Label("Term End Date ");
            	Label totalCreditsLabel=new Label("Total Credits ");
            
            	final Label termNumberText=new Label();
            	final NumberField minSGPAText=new NumberField();
            	final TextField termNameText=new TextField();
            	final NumberField teachingDaysText=new NumberField();
            	final NumberField durationText=new NumberField();
            	final DateField startDate=new DateField();
            	final DateField endDate=new DateField();
            	final NumberField totalCreditsText=new NumberField();
            	final ComboBox progcomboBox=new ComboBox();
            	
            	
            	
            	progcomboBox.setEmptyText("Choose Program");
            	
            	AddLabel.setText("Add Programme Details");
            	
            	 tableFlexTable.clear();
            	 tableFlexTable.setWidget(3,0, progNameLabel);
                 tableFlexTable.setWidget(3,1, progcomboBox);
            	 tableFlexTable.setWidget(4,0, termNumberLabel);
                 tableFlexTable.setWidget(4,1, termNumberText);
                 tableFlexTable.setWidget(5,0, minSGPALabel);
                 tableFlexTable.setWidget(5,1, minSGPAText);
                 tableFlexTable.setWidget(6,0, termNameLabel);
                 tableFlexTable.setWidget(6,1, termNameText);
                 tableFlexTable.setWidget(7,0, teachingDaysLabel);
                 tableFlexTable.setWidget(7,1, teachingDaysText);
                 tableFlexTable.setWidget(8,0, durationLabel);
                 tableFlexTable.setWidget(8,1, durationText);
                 tableFlexTable.setWidget(9,0, termStartLabel);
                 tableFlexTable.setWidget(9,1, startDate);
                 tableFlexTable.setWidget(10,0, termEndLabel);
                 tableFlexTable.setWidget(10,1, endDate);
                 tableFlexTable.setWidget(11,0, totalCreditsLabel);
                 tableFlexTable.setWidget(11,1, totalCreditsText);
                 
 
                 termNumberText.setText("1");
                 termOKButton.addListener(new ButtonListenerAdapter(){
                	 public void onClick(Button ProgrammeAddButton, EventObject e) {
                		 termCount++;
                		if(termCount<=numberOfTerms){                 		 
                		termNumberText.setText(""+termCount); 
                		}else{
                		 HoriPanel.clear();
                		 termSaveButton.setText("Next");
                 		HoriPanel.add(termSaveButton);
                		}
                	 }
                  });
                 
                 
                 
                 termDetailForm.add(tableFlexTable);
                 VertiPanel.clear();
            
            	   VertiPanel.add(AddLabel);
                   VertiPanel.setSpacing(30);
                   VertiPanel.add(nameText);
                   VertiPanel.add(sessionText);
                   VertiPanel.add(termDetailForm);
                   VertiPanel.setSpacing(20);
                   VertiPanel.add(HoriPanel);
            	   ProgrammeflexTable.setWidget(0, 0, VertiPanel);

                   ProgrammeHorizontalPanel.add(ProgrammeflexTable);
                   ProgrammeHorizontalPanel1.clear();
                   ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
            	
            }
            });
        
        
        connectService.methodprogList(userid, new AsyncCallback<CM_progMasterInfoGetter[]>(){
			public void onFailure(Throwable arg0) {
				MessageBox.alert(msgs.failure(),arg0.getMessage());
				
			}
			public void onSuccess(CM_progMasterInfoGetter[] result) {
				progCode=new String[result.length];
				
				for(int i=0;i<result.length;i++){
					
					progCode[i]=result[i].getProgram_code();
				}						
			}
 		});         		
        
        
       
    	final NumberField minDurationField=new NumberField();
    	final NumberField maxDurationField=new NumberField();
      	final NumberField multipleStartText=new NumberField(); 
        
    	final ComboBox dayField[]=new ComboBox[20];
    	final ComboBox monthComboBox[]=new ComboBox[20];
    	
    	final FormPanel branchForm=new FormPanel();
    	final FormPanel branchForm1=new FormPanel();
    	
    	final ComboBox branchName[] = new ComboBox[50];
    	final ComboBox branchName1[] = new ComboBox[50];
    	final CheckBox[] branchCheck=new CheckBox[50];
    	final CheckBox[] branchCheck1=new CheckBox[50];
    	final ComboBox specName[] = new ComboBox[50];
    	final CheckBox[] specCBoxs=new CheckBox[50];
    	final VerticalPanel decoVerti[]=new VerticalPanel[50];
    	final Hyperlink addMoreSpecHyper[]=new Hyperlink[50];
    	
//    	final TextField specText2[]=new TextField[100];
    	final Label specLabel[]=new Label[50];
        
        /*
         * Adds Click Handler to <code>termNextButton</code>
         */
        termNextButton.addListener(new ButtonListenerAdapter() {
         	public void onClick(Button ProgrammeAddButton, EventObject e) {
         		int a=0,b=0;
         		try {
               	 codeText.validate();
                } catch (Exception e1) {
                   b++;
                   a++;
               	MessageBox.alert(msgs.error(),msgs.error_programCode());	
                }
                if(b==0){
                	for(int j=0;j<progCode.length;j++){
                		if(progCode[j].equalsIgnoreCase(codeText.getValueAsString())){
                			a++;
                            	MessageBox.alert(msgs.duplicateEntry(),msgs.duplicateProgCode(codeText.getValueAsString()));
                         	    	
                		}
                	}
                }
                
         		if(a==0){
            	//Store all values of previous page
         		 Boolean flag = true;

                 try {
                	 codeText.validate();
                 } catch (Exception e1) {
                     flag = false;
                 }

                 try {
                	 cNameText.validate();
                 } catch (Exception e1) {
                     flag = false;
                 }

                 try {
                	 semesterText.validate();
                 } catch (Exception e1) {
                     flag = false;
                 }

                 try {
                	 creditText.validate();
                 } catch (Exception e1) {
                     flag = false;
                 }
                 try {
                	 attemptField.validate();
                 } catch (Exception e1) {
                     flag = false;
                 }
                 try {
                	 maxFailCourseField.validate();
                 } catch (Exception e1) {
                     flag = false;
                 }

                 //To check if no item selected
                 if (validator.nullValidator(programModeComboBox.getRawValue())) {
                     try {
                    	 programModeComboBox.markInvalid(
                             "");
                     } catch (Exception e1) {
                         flag = false;
                     }

                     flag = false;
                 }

                 if (validator.nullValidator(programTypeCombo.getRawValue())) {
                     try {
                    	 programTypeCombo.markInvalid(
                             "");
                     } catch (Exception e1) {
                         flag = false;
                     }

                     flag = false;
                 }

                 if (validator.nullValidator(ugpgComboBox.getRawValue())) {
                     try {
                    	 ugpgComboBox.markInvalid(
                             "");
                     } catch (Exception e1) {
                         flag = false;
                     }

                     flag = false;
                 }
                 if (flag == true) {
              
                	 /*
                	  * Storing values of previous page
                	  */
                	 
                	 input.setProgram_code(codeText.getValueAsString());
                	 input.setProgram_name(cNameText.getValueAsString());
                	 input.setProgram_mode(programModeComboBox.getValue());
                	 input.setProgram_type(programTypeCombo.getValue());
                	 input.setNo_of_terms(semesterText.getValueAsString());
                	 input.setTotal_credits(creditText.getValueAsString());
                	 input.setNumber_of_attempt_allowed(attemptField.getValueAsString());
                	 input.setMax_number_of_fail_subjects(maxFailCourseField.getValueAsString());
                	 input.setUGorPG(ugpgComboBox.getValue());
                	 input.setTencodes(tencodescomboBox.getRawValue());

         		
            	final FlexTable tableFlexTable=new FlexTable();
            	FormPanel durationForm=new FormPanel();
            	
            	final HorizontalPanel dateHPanel[]=new HorizontalPanel[20];
            
            	HoriPanel.clear();
            	
            	HoriPanel.add(next2Button);
            	
            	Label tooltipLabel=new Label(cons.inMonths());
            	Label tooltipLabel1=new Label(cons.inMonths());
            	Label minDuration=new Label(cons.minDuration());
        
            	Label maxDuration=new Label(cons.maxDuration());
            	
            	final Label startDate[]=new Label[20];
            
            	final Label multipleStartLabel=new Label(cons.progMultiStart());
          
            	Button okButton=new Button(cons.okButton());
           
            	
            	durationForm.setFrame(true);
            	durationForm.setTitle(cons.progDurationDetail());
                tooltipLabel.setStyleName("GREY");
                tooltipLabel1.setStyleName("GREY");
                multipleStartText.setAllowDecimals(false);
                multipleStartText.setAllowBlank(false);
                fixedRadio.setChecked(true);
                minDurationField.setAllowDecimals(false);
                maxDurationField.setAllowDecimals(false);
                maxDurationField.setAllowBlank(false);
                minDurationField.setAllowBlank(false);
                minDurationField.setValue("1");
                maxDurationField.setValue("1");
                
            	tableFlexTable.clear();
            	tableFlexTable.setSize("100%","100%");
            	 tableFlexTable.setWidget(4,0, durationLabel);
                 tableFlexTable.setWidget(4,1, dpanel);
                 tableFlexTable.setWidget(5,0, minDuration);
                 tableFlexTable.setWidget(5,1, minDurationField);
                 tableFlexTable.setWidget(5,2, tooltipLabel);
                 tableFlexTable.setWidget(6,0, maxDuration);
                 tableFlexTable.setWidget(6,1, maxDurationField);
                 tableFlexTable.setWidget(6,2, tooltipLabel1);
                 tableFlexTable.setWidget(7,0, multipleStartLabel);
                 tableFlexTable.setWidget(7,1, multipleStartText);
                 tableFlexTable.setWidget(8,2, okButton);
                 
                   
                 okButton.addListener(new ButtonListenerAdapter(){
                	 public void onClick(Button ProgrammeAddButton, EventObject e) {
                		 Boolean flag=true;
                		 
                		 /*
                		  * To be varified on click of next button
                		  */
                		 try{
                		 minDurationField.validate();
                		 }catch (Exception e1) {
							flag=false;
						}
                		 try{
                    		 maxDurationField.validate();
                    		 }catch (Exception e1) {
    							flag=false;
    						}
                		 
                		 if((Integer.parseInt(maxDurationField.getValueAsString()))==0 || (Integer.parseInt(minDurationField.getValueAsString()))==0){
                			 flag=false;
                			
                			 MessageBox.alert(msgs.error(),msgs.nonZero("Duration"));
                		 } else
                		 {
                		 if (fixedRadio.isChecked()){
                			 if((Integer.parseInt(maxDurationField.getValueAsString()))!=(Integer.parseInt(minDurationField.getValueAsString()))){
                				 flag=false;
                				 MessageBox.alert(msgs.error(),msgs.equalMinMaxDuration());

                				 maxDurationField.markInvalid("Invalid"); 
                				 minDurationField.markInvalid("Invalid");
                			 }
//                			 else{
//                				 maxDurationField.setValue(minDurationField.getValue());
//                			 }
                			 
                		 }else{
                			 if((Integer.parseInt(maxDurationField.getValueAsString()))<(Integer.parseInt(minDurationField.getValueAsString()))){
                				 flag=false;            				 
                				 minDurationField.markInvalid("Invalid");
                		 MessageBox.alert(msgs.error(),msgs.greaterMaxDuration());
                			 }
                		 }
                		 try {
                			 multipleStartText.validate();
                         } catch (Exception e1) {
                             flag = false;
                         }
                         
                		 if(flag==true){	
                			 dates();
                        	 multipleStart= Integer.parseInt( multipleStartText.getText());
                        	 if(multipleStart!=0){
                		 try{
                			 next2Button.setDisabled(false);
            			 for(int x=9;x<tableFlexTable.getRowCount();x++){
            			 tableFlexTable.removeRow(x);
            			 }
                		 }catch (Exception e1) {
							System.out.println("error in removing row "+e1);
						}try{
                		 for(int z=0;z<multipleStart;z++)
                         {
                			 startDate[z]=new Label();
                        	 dateHPanel[z]=new HorizontalPanel();
                        	 monthComboBox[z]=new ComboBox();
                        	 dayField[z]=new ComboBox();
                        	 startDate[z].setText("Start Date ");
                        	 
                 	         monthComboBox[z].setFieldLabel(""+z);  
                 	         monthComboBox[z].setStore(monthStore);  
                 	         monthComboBox[z].setDisplayField("month");  
                 	         monthComboBox[z].setMode(ComboBox.LOCAL);  
                 	         monthComboBox[z].setTriggerAction(ComboBox.ALL);  
                 	         monthComboBox[z].setForceSelection(true);  
                 	         monthComboBox[z].setValueField("id");
                
                 	         monthComboBox[z].setReadOnly(true);  
                        	                	       
                	   
//                	        dayField[z].setFieldLabel("Select City");  
                 	        dayField[z].setDisplayField("day");  
                	        dayField[z].setValueField("did");  
                	        dayField[z].setMode(ComboBox.LOCAL);  
                	        dayField[z].setTriggerAction(ComboBox.ALL);  
                	        dayField[z].setLinked(true);  
                	        dayField[z].setForceSelection(true);  
                	        dayField[z].setReadOnly(true);  
                        	 
                        		dayField[z].setWidth(60);
                            	monthComboBox[z].setWidth(60);
                            	dateHPanel[z].add(monthComboBox[z]);
                            	dateHPanel[z].setSpacing(20);
                            	dateHPanel[z].add(dayField[z]);
                            
                            	         monthComboBox[z].addListener(new ComboBoxListenerAdapter() {  
                            	   
                            	             public void onSelect(ComboBox comboBox, Record record, int index) {
                            	            	 int zz=Integer.parseInt(comboBox.getFieldLabel());
                                     	        dayField[zz].setStore(daysStore);                                      	    
                            	               	 dayField[zz].setValue("");  
                            	                 daysStore.filter("id", comboBox.getValue().substring(2)); 
                            	                                          	                 
                            	             }  
                            	         });  
                            	   
                         tableFlexTable.setWidget(9+z,0, startDate[z]);
                         tableFlexTable.setWidget(9+z,1, dateHPanel[z]);
                         }
                	 }catch (Exception ex) {
						System.out.println("Exception "+ex);
					}
                		 tableFlexTable.setSize("100%","100%");
                	 }else{
                		 nextFlag=false;
                		 MessageBox.alert(msgs.error(),msgs.startAtleastOne());
                	 }
                		 }else{
                			 MessageBox.alert(msgs.error(),msgs.checkFields());
                		 }
                	 }
                 } 
                 });
                 
                 
                 
                 
                 durationForm.add(tableFlexTable);
            	VertiPanel.clear();
            
            	   VertiPanel.add(AddLabel);
                   VertiPanel.setSpacing(30);
                   VertiPanel.add(nameText);
                   VertiPanel.add(sessionText);
                   VertiPanel.add(durationForm);
                   VertiPanel.setSpacing(20);
                   VertiPanel.add(HoriPanel);
            	   ProgrammeflexTable.setWidget(0, 0, VertiPanel);

                   ProgrammeHorizontalPanel.add(ProgrammeflexTable);
                   ProgrammeHorizontalPanel1.clear();
                   ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
         	}else{
         		 MessageBox.alert(msgs.error(),msgs.checkFields());
         	}
            }
        }
            });
        
        
        /*
         * Adds Click Handler to <code>Next2Button</code>
         */
        next2Button.addListener(new ButtonListenerAdapter() {
      		public void onClick(Button ProgrammeAddButton, EventObject e) {
      		    final ScrollPanel branchScroll=new ScrollPanel();
      		    final ScrollPanel branchScroll1=new ScrollPanel();
      			
      			/*
      			 * Store values of duration page
      			 */
      			String fixed;
      			boolean flag=true;
      			
      			if(fixedRadio.isChecked()){
      				fixed="Y";
      			}else{
      				fixed="N";
      			}
      			
      			String minDuration=minDurationField.getValueAsString();
      			String maxDuration=maxDurationField.getValueAsString();	
      			
      		if(fixed.equals("Y")){
      			if(!(minDuration.equalsIgnoreCase(maxDuration))){
      			 flag=false;
      			}
      		}else
      		{
      			if(Integer.parseInt(minDuration)>=Integer.parseInt(maxDuration)){
      				flag=false;
      			}
      		}
		
  			int multiStart=Integer.parseInt(multipleStartText.getValueAsString());
  			String[] StartDate=new String[multiStart];
  		for(int k=0;k<multiStart;k++){
  	
  			StartDate[k]=monthComboBox[k].getValue().substring(0,2)+dayField[k].getRawValue();
  			
  		}
  		
  		
  		input.setFixed_duration(fixed);
		input.setMinimun_duration(minDuration);
		input.setMaximum_duration(maxDuration);
  		input.setStart_day_Month(StartDate);
  		
      			
      		if(flag==true){
      			branchStorePopulate();
      			tableFlexTable.clear();
            	HoriPanel.clear();
           	HorizontalPanel branchHorizontalPanel=new HorizontalPanel();
        	HorizontalPanel branchFormHorizontalPanel=new HorizontalPanel();
        	final VerticalPanel linksHorizontalPanel=new VerticalPanel();
        	final VerticalPanel linksHorizontalPanel1=new VerticalPanel();
        	final HorizontalPanel optionHorizontalPanel=new HorizontalPanel();
         	final FlexTable branchflex=new FlexTable();
         	final FlexTable branchflex1=new FlexTable();
//         	final FlexTable specListFlex[]=new FlexTable[50];
         	final DecoratorPanel decoSpec[]=new DecoratorPanel[50];
         
         	
            	
            	
            	
            	branchForm.setFrame(true);
            	branchForm.setTitle(msgs.branchWithSpec(""));
            	branchForm1.setFrame(true);
            	branchForm1.setTitle(msgs.branchWithoutSpec(""));
            	
            	branchForm.setClosable(true);
            	branchForm1.setClosable(true);
            	
            	branchHorizontalPanel.add(nextBranchButton);
//            	final Label numberOfBranchesLabel=new Label("Number of Branches ");
            	final NumberField numberOfBranchesText=new NumberField();
            	numberOfBranchesText.setWidth(25);
            	numberOfBranchesText.setAllowDecimals(false);
            	final Button okButton=new Button(cons.okButton());
            
            	final Label branchNameLabel[]=new Label[50];

            	
            	final Hyperlink otherBranchHyper=new Hyperlink(cons.anotherBranch(),null);
            	final Hyperlink otherBranchHyper1=new Hyperlink(cons.anotherBranch(),null);
            	final Hyperlink otherBranchHyperMain1=new Hyperlink(msgs.branchWithSpec("Add"),null);
            	final Hyperlink otherBranchHyperMain2=new Hyperlink(msgs.branchWithoutSpec("Add"),null);
            	
            	/*
            	 * <code>removeBranchHyper</code> and <code>removeBranchHyper1</code> Not yet in use
            	 */
            	final Hyperlink removeBranchHyper=new Hyperlink("Remove Last Branch Box",null);
            	final Hyperlink removeBranchHyper1=new Hyperlink("Remove Last Branch Box11",null);
            
            	
            	tableFlexTable.setWidget(4,0,branchCheckBox);

            	
//            	numberOfBranchesLabel.setVisible(false);
				numberOfBranchesText.setVisible(false);
				okButton.setVisible(false);
				optionHorizontalPanel.setVisible(false);
				

				branchForm.setSize("750px","250px");
				branchForm1.setSize("350px","250px");
				linksHorizontalPanel.setSpacing(5);
				linksHorizontalPanel.add(otherBranchHyper);
//				linksHorizontalPanel.add(removeBranchHyper);
				
				linksHorizontalPanel1.setSpacing(5);
				linksHorizontalPanel1.add(otherBranchHyper1);
//				linksHorizontalPanel1.add(removeBranchHyper1);
				
				
				optionHorizontalPanel.setSpacing(30);
				optionHorizontalPanel.add(otherBranchHyperMain1);
				optionHorizontalPanel.add(otherBranchHyperMain2);
				
				
	branchForm1.setWidth("350px");
	final VerticalPanel branchVPanel=new VerticalPanel();
	final VerticalPanel branchVPanel1=new VerticalPanel();
	branchScroll.setSize("725px","215px");
	branchScroll1.setSize("330px","215px");
	branchVPanel.add(branchflex);
	branchVPanel.add(linksHorizontalPanel);
	branchVPanel1.add(branchflex1);
	branchVPanel1.add(linksHorizontalPanel1);
	branchScroll.clear();
	branchScroll.add(branchVPanel);
	branchScroll1.clear();
	branchScroll1.add(branchVPanel1);
	branchcount++;

	
if(branchcount==1){
	branchForm.add(branchScroll);
	branchForm1.add(branchScroll1);
}

	
	branchForm.add(branchScroll);
	branchForm1.add(branchScroll1);
  
	
	otherBranchHyperMain2.addClickListener(new ClickListener(){
		
		public void onClick(Widget arg0) {
			
			if(branchForm1.isVisible()){
				otherBranchHyperMain2.setText(msgs.branchWithSpec("Add"));
				branchForm1.setVisible(false);
			}else{
				otherBranchHyperMain2.setText(msgs.branchWithoutSpec("Add"));				
				branchForm1.setVisible(true);
			}
			

			k=0;
			branchName1[k]=new ComboBox();
			
			branchName1[k].setStore(branchstore);
    		
    		branchName1[k].setForceSelection(true);
    		branchName1[k].setMinChars(1);
    		branchName1[k].setDisplayField("branch_name");
    		branchName1[k].setValueField("branch_code");
    		branchName1[k].setMode(ComboBox.LOCAL);
    		branchName1[k].setTriggerAction(ComboBox.ALL);
    		branchName1[k].setEmptyText(cons.label_branchname());
    		branchName1[k].setLoadingText(cons.loading());
    		branchName1[k].setTypeAhead(true);
    		branchName1[k].setSelectOnFocus(true);
    		branchName1[k].setWidth(190);
    		branchName1[k].setHideTrigger(false);
    	  
			
			
			branchCheck1[k]=new CheckBox();
			branchCheck1[k].setChecked(true);
          	branchNameLabel[k]=new Label();
          	branchNameLabel[k].setText("Branch Name ");
          	branchflex1.setWidget(k,0, branchNameLabel[k]);
          	  branchflex1.setWidget(k,1, branchName1[k]);
          	 branchflex1.setWidget(k,2, branchCheck1[k]);
			
			removeBranchHyper1.setVisible(false);
		}
		});
	
            	
	otherBranchHyper1.addClickHandler(new ClickHandler(){

					public void onClick(ClickEvent arg0) {
						
						k++;
                	  branchName1[k]=new ComboBox();
                	  
                	  branchName1[k].setStore(branchstore);
	            		
	            		branchName1[k].setForceSelection(true);
	            		branchName1[k].setMinChars(1);
	            		branchName1[k].setDisplayField("branch_name");
	            		branchName1[k].setValueField("branch_code");
	            		branchName1[k].setMode(ComboBox.LOCAL);
	            		branchName1[k].setTriggerAction(ComboBox.ALL);
	            		branchName1[k].setEmptyText(cons.label_branchname());
	            		branchName1[k].setLoadingText(cons.loading());
	            		branchName1[k].setTypeAhead(true);
	            		branchName1[k].setSelectOnFocus(true);
	            		branchName1[k].setWidth(190);
	            		branchName1[k].setHideTrigger(false);
                	  
                	  
                	  
                	  
                	  
                	  branchCheck1[k]=new CheckBox();
          			branchCheck1[k].setChecked(true);
                  	branchNameLabel[k]=new Label();
                  	branchNameLabel[k].setText(cons.label_branchname());
                  	branchflex1.setWidget(k,0, branchNameLabel[k]);
                  	  branchflex1.setWidget(k,1, branchName1[k]);
                    	 branchflex1.setWidget(k,2, branchCheck1[k]);
                  	branchScroll1.scrollToBottom();	
                  	if(k>0){
                  		removeBranchHyper1.setVisible(true);
                  	}
					}
            		
            	});
            	
        
            	otherBranchHyperMain1.addClickListener(new ClickListener(){
				
					public void onClick(Widget arg0) {
						try{
							
							specgrid=new GridPanel[50];
							}catch (Exception e) {
								System.out.println("error in declaring specText1 size "+e);
							}
						branchScroll.clear();
						branchVPanel.clear();
						branchflex.clear();
						
						branchVPanel.add(branchflex);
						
						branchVPanel.add(linksHorizontalPanel);		
						
						branchScroll.add(branchVPanel);
						
						j=0;
						if(branchForm.isVisible()){
							otherBranchHyperMain1.setText(msgs.branchWithSpec("Add"));
							branchForm.setVisible(false);
						}else{
							otherBranchHyperMain1.setText(msgs.branchWithSpec("Remove"));	
							
							branchForm.setVisible(true);
						}
				
	            		branchName[j]=new ComboBox();
	            		branchName[j].setStore(branchstore);
	            		
	            		branchName[j].setForceSelection(true);
	            		branchName[j].setMinChars(1);
	            		branchName[j].setDisplayField("branch_name");
	            		branchName[j].setValueField("branch_code");
	            		branchName[j].setMode(ComboBox.LOCAL);
	            		branchName[j].setTriggerAction(ComboBox.ALL);
	            		branchName[j].setEmptyText(cons.label_branchname());
	            		branchName[j].setLoadingText(cons.loading());
	            		branchName[j].setTypeAhead(true);
	            		branchName[j].setSelectOnFocus(true);
	            		branchName[j].setWidth(190);
	            		branchName[j].setHideTrigger(false);
	            		
	            		branchCheck[j]=new CheckBox();
	            		 branchCheck[j].setChecked(true);
                    	branchNameLabel[j]=new Label();	  
                    	
                    	
                    	specgrid[j]=new GridPanel();
                    	

                    	specLabel[j]=new Label();
                    
                    	addMoreSpecHyper[j]=new Hyperlink(cons.moreSpecs(),null);
                    	decoSpec[j]=new DecoratorPanel();
                    	decoVerti[j]=new VerticalPanel();
                    	
                    	
                       	specgrid[j].setStore(specstore);
                       	
                       	
                       	cbSelectionModelspec1[j]=new CheckboxSelectionModel();
//                       	cbSelectionModelspec1[j]=cbSelectionModelspec;
                       	
                       	speccolumns = new BaseColumnConfig[] {
                                new CheckboxColumnConfig(cbSelectionModelspec1[j]),
                                new ColumnConfig(cons.spec(),
                                    "spec_name", 200, true, null,
                                    "spec_name")
                            };

                       columnModel = new ColumnModel(speccolumns);
                    
                       	specgrid[j].setColumnModel(columnModel);
                       	specgrid[j].setFrame(true);
                       	specgrid[j].setStripeRows(true);
                       	
                       	
                       	specgrid[j].setSelectionModel(cbSelectionModelspec1[j]);
                       	specgrid[j].setAutoWidth(true);
                     	specgrid[j].setWidth(250);
                       	specgrid[j].setHeight(150);
                       	specgrid[j].setAutoScroll(true);
                     	
                    	decoVerti[j].add(specgrid[j]);
                    	
                 
                    	decoSpec[j].add(decoVerti[j]);
                    	specLabel[j].setText(cons.spec());
                    
                    	
                    	branchNameLabel[j].setText(cons.label_branchname());
                    	branchflex.clear();
                    	branchflex.setWidget(j,0, branchNameLabel[j]);
                    	  branchflex.setWidget(j,1, branchName[j]);
                    	  branchflex.setWidget(j,2, branchCheck[j]);
                    	  branchflex.setWidget(j,3,decoSpec[j]);
                    	 
                    	removeBranchHyper.setVisible(false);
                    
                    	
                    	
					}
            		
            	});
            	
             	branchCheckBox.addClickHandler(new ClickHandler(){

					public void onClick(ClickEvent arg0) {
					
						    
							if(branchCheckBox.isChecked()){
								j=0;
								
		            		optionHorizontalPanel.setVisible(true);
		            		
		            		branchStorePopulate();
		            		
	                       	specGrid();
							}else
							{
								branchForm.setVisible(false);
								branchForm1.setVisible(false);
								optionHorizontalPanel.setVisible(false);
							}
				                
								
					}
                	
                });
            	
            	
             	otherBranchHyper.addClickListener(new ClickListener(){

					public void onClick(Widget arg0) {
	            		  j++;
	            		  branchName[j]=new ComboBox();
	            		  
	            		  branchName[j].setStore(branchstore);
		            		
		            		branchName[j].setForceSelection(true);
		            		branchName[j].setMinChars(1);
		            		branchName[j].setDisplayField("branch_name");
		            		branchName[j].setValueField("branch_code");
		            		branchName[j].setMode(ComboBox.LOCAL);
		            		branchName[j].setTriggerAction(ComboBox.ALL);
		            		branchName[j].setEmptyText(cons.label_branchname());
		            		branchName[j].setLoadingText(cons.loading());
		            		branchName[j].setTypeAhead(true);
		            		branchName[j].setSelectOnFocus(true);
		            		branchName[j].setWidth(190);
		            		branchName[j].setHideTrigger(false);
	            		  
	            		  
	            		  
	            		  branchCheck[j]=new CheckBox();
	            		  branchCheck[j].setChecked(true);
	                    	branchNameLabel[j]=new Label();	  
	                    	
	                    	specLabel[j]=new Label();
	                    	specgrid[j]=new GridPanel();
	                    	
	                    	addMoreSpecHyper[j]=new Hyperlink(cons.moreSpecs(),null);
	                    	
	                    	decoSpec[j]=new DecoratorPanel();
	                    	decoVerti[j]=new VerticalPanel();
	                    	
	                    	specgrid[j].setStore(specstore);
	                    	
	                      	cbSelectionModelspec1[j]=new CheckboxSelectionModel();
//                       	cbSelectionModelspec1[j]=cbSelectionModelspec;
                       	
                       	speccolumns = new BaseColumnConfig[] {
                                new CheckboxColumnConfig(cbSelectionModelspec1[j]),
                                new ColumnConfig(cons.spec(),
                                    "spec_name", 200, true, null,
                                    "spec_name")
                            };

                       columnModel = new ColumnModel(speccolumns);
                    
	                    	
	                       	specgrid[j].setColumnModel(columnModel);
	                       	specgrid[j].setFrame(true);
	                       	specgrid[j].setStripeRows(true);
	              
	                       	specgrid[j].setSelectionModel(cbSelectionModelspec1[j]);
	                       	                       	
	                       	specgrid[j].setAutoWidth(true);
	                       	specgrid[j].setWidth(250);
	                       	specgrid[j].setHeight(150);
	                    	specgrid[j].setAutoScroll(true);
	                    	
	                    	decoVerti[j].add(specgrid[j]);
	                    	decoSpec[j].add(decoVerti[j]);
	                    	specLabel[j].setText(cons.spec());	                    	

	                    

	                    	branchNameLabel[j].setText(cons.label_branchname());
	                    	branchflex.setWidget(j,0, branchNameLabel[j]);
	                    	  branchflex.setWidget(j,1, branchName[j]);
	                    	  branchflex.setWidget(j,2,branchCheck[j]);
	                    	  branchflex.setWidget(j,3,decoSpec[j]);
	                    	
	            		  
                  	branchScroll.scrollToBottom();	
                  	if(j>0){
                  		removeBranchHyper.setVisible(true);
                  	}
                  	
                 	
                  	
					}
             		
             	});
             	
             	removeBranchHyper.addClickListener(new ClickListener(){

					public void onClick(Widget arg0) {
				  		removeBranchHyper.setVisible(true);
						branchflex.removeRow(j);
	                            	j--;
                  	branchScroll.scrollToBottom();	
                  	if(j<1){
                  		removeBranchHyper.setVisible(false);
                  	}
					}
             		
             	});
             	
             	
             	removeBranchHyper1.addClickListener(new ClickListener(){

					public void onClick(Widget arg0) {
				  		removeBranchHyper1.setVisible(true);
						branchflex1.removeRow(k);
	                            	k--;
                  	branchScroll1.scrollToBottom();	
                  	if(k<1){
                  		removeBranchHyper1.setVisible(false);
                  	}
					}
             		
             	});
             	
             	branchFormHorizontalPanel.clear();
             	branchFormHorizontalPanel.setSpacing(20);
             	branchFormHorizontalPanel.add(branchForm);
             	branchFormHorizontalPanel.add(branchForm1);
             	
            	VertiPanel.clear();
            	branchForm.setVisible(false);
            	branchForm1.setVisible(false);
            	VertiPanel.add(AddLabel);
                VertiPanel.setSpacing(30);
                VertiPanel.add(nameText);
                VertiPanel.add(sessionText);
                VertiPanel.setSpacing(10);
                VertiPanel.add(tableFlexTable);
                VertiPanel.add(optionHorizontalPanel);
                VertiPanel.add(branchFormHorizontalPanel);
                
                VertiPanel.add(branchHorizontalPanel);
            
                
         	   ProgrammeflexTable.setWidget(0, 0, VertiPanel);

                ProgrammeHorizontalPanel.add(ProgrammeflexTable);
                ProgrammeHorizontalPanel1.clear();
                ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
            	
      		}
            }
        });
        
        /*
         * Adds Click Handler to <code>nextBranchButton</code>
         */
        nextBranchButton.addListener(new ButtonListenerAdapter(){
		
			public void onClick(Button ProgrammeAddButton, EventObject e) {
				final ScrollPanel specScroll=new ScrollPanel();
				boolean flag=true;
				
				boolean branchExist=true;
//				String[] branches = new String[k+1];
				String[] branches =null;
			String branch=new String();
			String[][] arr=null;
			
				if(branchCheckBox.isChecked()){
					branchExist=true;
//					String[][] arr=null;
					try{
						if(branchForm.isVisible()){
							int branchCount=0;
							int loop=0;
//							list.clear();
							branchExist=true;
							
							for(int i=0;i<=j;i++){
								if(branchCheck[i].isChecked())	{
									branchCount++;
								}
							}
							
							arr=new String[branchCount][];
							
							for(int i=0;i<=j;i++){
																
							if(branchCheck[i].isChecked())	
								{
								Record[] records = cbSelectionModelspec1[i].getSelections();

								
								if(branchName[i].getRawValue().equalsIgnoreCase("")){
									flag=false;
									}else{
							branch=branchName[i].getValue();
//							System.out.println("branch="+branch);
							
							if(records.length==0){
								flag=false;
								MessageBox.alert(msgs.error(),msgs.chooseOneSpec(i));
							}else
							{
							arr[loop]=new String[records.length+1];
							for(int q=0;q<records.length+1;q++)
							{
								if(q==0){
								
									arr[loop][q]=branch;
									
								}else{		
									Record record = records[q-1];
									
									arr[loop][q]=record.getAsString("specialization_code");
								}
							}	
							loop++;
									}	
								}
								}
							}
							
						}
						}catch (Exception ex) {
						System.out.println("exception in getting branches "+ ex);
						}
						
						try{
							
						if(branchForm1.isVisible()){
							int branch_count=0;
							for(int i=0;i<=k;i++){
								if(branchCheck1[i].isChecked())	
									{
									branch_count++;
									}
							}
							branches=new String[branch_count];
							System.out.println("branch_count="+branch_count);
							int b=0;
							branchExist=true;
							for(int i=0;i<=k;i++){
								if(branchCheck1[i].isChecked())	
									{
									if(branchName1[i].getRawValue().equalsIgnoreCase("")){										
										flag=false;
										
										}else{
								branch=branchName1[i].getValueAsString();
								branches[b]=branchName1[i].getValueAsString();
								b++;
										}
									}
								
								
						}
						}else {
							branches=new String[0];
						}
						}catch (Exception e1) {
							System.out.println("Exception in only branch "+e1);
						}
						boolean c=false;
						//Error in this try block (throwing null pointer exception)
						try{
						System.out.println("arr.length "+arr.length);
							String s=null;
							
							if(arr.length>0){
								System.out.println("inside arr>0");
							for(int i=0;i<arr.length;i++){
								if(c==false){
									s=arr[i][0];
								}
								for(int k=0;k<arr.length;k++){
									int j=0;
									if(i!=k){
										if(s.equalsIgnoreCase(arr[k][j])){
											c=true;
											System.out.println("inside kj true"+k+" and "+j+" and "+arr[k][j]);
											break;
										}
									}
								}
								System.out.println("branches.length "+branches.length);
								for(int z=0;z<branches.length;z++){
									if(s.equalsIgnoreCase(branches[z])){
										c=true;
										System.out.println("inside z true"+z+" and "+branches[z]);
										break;
									}
								}
								
							}
							}
							
							System.out.println("branches.length alone"+branches.length);
							for(int zz=0;zz<branches.length;zz++){
								if(c==false){
									s=branches[zz];
								}
								
								for(int z=0;z<branches.length;z++){
									if(s.equalsIgnoreCase(branches[z])){
										if(z!=zz){
										c=true;
//										System.out.println("inside zz second true"+z+" and "+branches[z]);
										break;
										}
									}
								}
								
							}
							
						}catch (Exception e1) {
							System.out.println("Exception in checking branch duplicacy "+e1);
						}
							
							if(c==true){
								flag=false;
								MessageBox.alert(msgs.error(),msgs.duplicateBranch());
							}else{
								
//							
//								for(int i=0;i<arr.length;i++){
//									System.out.println("arr[0]"+arr[i][0]);
//									for(int k=0;k<arr[i].length;k++){
//										System.out.println("arr["+i+"]"+"arr["+k+"]"+arr[i][k]);
//									}
//								}
								
								input.setBranchSpec(arr);
//								System.out.println("coming just before branch set ");
								input.setBranch_code(branches);
//								System.out.println("coming just after branch set ");
							}
							
				
				
						
				}
				else{
					branchExist=false;
				}
				
				input.setBranch(branchExist);
				input.setBranch_Name(branches);
				
				if (flag==true){
					      
		      			tableFlexTable.clear();
		            	HoriPanel.clear();
		           	HorizontalPanel specHorizontalPanel=new HorizontalPanel();
		        	HorizontalPanel speclinksHorizontalPanel=new HorizontalPanel();
		         	final FlexTable specflex=new FlexTable();
		            	final FormPanel specForm=new FormPanel();
		            	specForm.setFrame(true);
		            	specForm.setTitle(cons.spec()+cons.details());
		            	specHorizontalPanel.add(nextSpecButton);
		            	
   
		            	
		            	final Label specNameLabel[]=new Label[50];
		               	final Hyperlink otherSpecHyper=new Hyperlink(cons.anotherSpec(),null);
		            /*
		             * <code>removeSpecHyper</code> not in use yet
		             */
		               	final Hyperlink removeSpecHyper=new Hyperlink("Remove Last Specialization Box",null);
		            			            	
		            	
		            	tableFlexTable.setWidget(4,0,specCheckBox);
		            	
		            							
		            	specForm.setSize("500px","200px");
		            	speclinksHorizontalPanel.setSpacing(20);
		            	speclinksHorizontalPanel.add(otherSpecHyper);
//		            	speclinksHorizontalPanel.add(removeSpecHyper);			
						
						
						
		try{
			VerticalPanel specVPanel=new VerticalPanel();
			
			specScroll.setSize("435px","175px");
			
			specVPanel.add(specflex);
			specVPanel.add(speclinksHorizontalPanel);
			specScroll.clear();
			specScroll.add(specVPanel);
			specForm.add(specScroll);
			
		}catch (Exception e2) {
		System.out.println("e2 "+e2);
		}			

		connectService.methodSpecList(userid,new AsyncCallback<CM_progMasterInfoGetter[]>(){
			public void onFailure(Throwable arg0) {
			MessageBox.alert(msgs.failure(),arg0.getMessage());
				
			}

			public void onSuccess(CM_progMasterInfoGetter[] arg0) {
				RecordDef recordDef = new RecordDef(new FieldDef[] {
						 new StringFieldDef("spec_name"),
						 new StringFieldDef("specialization_code")
                    });
			
            String[][] object2 = new String[arg0.length][2];

            String[][] data = object2;

            for (int i = 0; i < arg0.length; i++) {
                object2[i][0] = arg0[i].getSpecname();
                object2[i][1] = arg0[i].getSpecialization_code();
            }

            MemoryProxy proxy = new MemoryProxy(data);

            ArrayReader reader = new ArrayReader(recordDef);
           specstore = new Store(proxy, reader);
           specstore.load();
				
			}
			
		});      
		            	
		specCheckBox.addClickHandler(new ClickHandler(){

							public void onClick(ClickEvent arg0) {
							
								try{    
									if(specCheckBox.isChecked()){
																		
										specCount=0;
								
									specForm.setVisible(true);
				            		specName[specCount]=new ComboBox();
				            		specName[specCount].setStore(specstore);
				            		
				            		specName[specCount].setForceSelection(true);
				            		specName[specCount].setMinChars(1);
				            		specName[specCount].setDisplayField("spec_name");
				            		specName[specCount].setValueField("specialization_code");
				            		specName[specCount].setMode(ComboBox.LOCAL);
				            		specName[specCount].setTriggerAction(ComboBox.ALL);
				            		specName[specCount].setEmptyText(msgs.chooseSpec());
				            		specName[specCount].setLoadingText(cons.loading());
				            		specName[specCount].setTypeAhead(true);
				            		specName[specCount].setSelectOnFocus(true);
				            		specName[specCount].setWidth(190);
				            		specName[specCount].setHideTrigger(false);
				            		
				            		
				            		
				            		specCBoxs[specCount]=new CheckBox();
				            		specCBoxs[specCount].setChecked(true);
			                    	specNameLabel[specCount]=new Label();
			                    	specNameLabel[specCount].setText(cons.spec());
			                    	specflex.clear();
			                    	specflex.setWidget(specCount,0, specNameLabel[specCount]);
			                    	  specflex.setWidget(specCount,1, specName[specCount]);
			                    	  specflex.setWidget(specCount,2, specCBoxs[specCount]);
			                    	    	removeSpecHyper.setVisible(false);
			                    	    	
				            		
									}else
									{
										System.out.println("spec form getting false");
										specForm.setVisible(false);
		  	  
									}
						                    
						                    
						                	}catch (Exception e1) {
												System.out.println(e1);
											}
							}
		                	
		                });
		            	
		            	
		             	otherSpecHyper.addClickListener(new ClickListener(){

							public void onClick(Widget arg0) {
								specCount++;
		                  	  specName[specCount]=new ComboBox();
		                  	specName[specCount].setStore(specstore);
		            		
		            		specName[specCount].setForceSelection(true);
		            		specName[specCount].setMinChars(1);
		            		specName[specCount].setDisplayField("spec_name");
		            		specName[specCount].setValueField("specialization_code");
		            		specName[specCount].setMode(ComboBox.LOCAL);
		            		specName[specCount].setTriggerAction(ComboBox.ALL);
		            		specName[specCount].setEmptyText(msgs.chooseSpec());
		            		specName[specCount].setLoadingText(cons.loading());
		            		specName[specCount].setTypeAhead(true);
		            		specName[specCount].setSelectOnFocus(true);
		            		specName[specCount].setWidth(190);
		            		specName[specCount].setHideTrigger(false);
		                  	
		                  	specCBoxs[specCount]=new CheckBox();
		                  	specCBoxs[specCount].setChecked(true);
		                  	specNameLabel[specCount]=new Label();
		                  		specNameLabel[specCount].setText(cons.spec());
		                  	specflex.setWidget(specCount,0, specNameLabel[specCount]);
		                  	  specflex.setWidget(specCount,1, specName[specCount]);
		                  	specflex.setWidget(specCount,2, specCBoxs[specCount]);
		                  	specScroll.scrollToBottom();	
		                  	if(specCount>0){
		                  		removeSpecHyper.setVisible(true);
		                  	}
		                  	
		                               	
		                  	
							}
		             		
		             	});
		             	
		             	removeSpecHyper.addClickListener(new ClickListener(){

							public void onClick(Widget arg0) {
						  		removeSpecHyper.setVisible(true);
								specflex.removeRow(specCount);
								specCount--;
		                  	specScroll.scrollToBottom();	
		                  	if(specCount<1){
		                  		removeSpecHyper.setVisible(false);
		                  	}
							}
		             		
		             	});
		             	
		             	
		            	VertiPanel.clear();
		            	specForm.setVisible(false);
		            	VertiPanel.add(AddLabel);
		                VertiPanel.setSpacing(30);
		                VertiPanel.add(nameText);
		                VertiPanel.add(sessionText);
		                VertiPanel.add(tableFlexTable);
		                VertiPanel.add(specForm);
		                VertiPanel.setSpacing(20);
		                VertiPanel.add(specHorizontalPanel);
		            
		                
		         	   ProgrammeflexTable.setWidget(0, 0, VertiPanel);

		                ProgrammeHorizontalPanel.add(ProgrammeflexTable);
		                ProgrammeHorizontalPanel1.clear();
		                ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
		            	
		       		
			}	
				
			}
    	});
        
        
        
        
        /*
         * Adds Click Handler to <code>nextSpecButton</code>
         */
        nextSpecButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button ProgrammeAddButton, EventObject e) {
			   final ScrollPanel reserveScroll=new ScrollPanel();
				boolean specExist=true;
				boolean specFlag=true;
				int number_of_specs=0;
				
				try{
					
					
//				String[] specs=new String[specCount+1];
					

				/*
				 * Store all program level specializations
				 */
				
				if(specCheckBox.isChecked()){
					for(int i=0;i<=specCount;i++){
						if(specCBoxs[i].isChecked()){
							number_of_specs++;	
						}
					}
					String[] specs=new String[number_of_specs];
					int j=0;
					specExist=true;
					for(int i=0;i<=specCount;i++){
						if(specCBoxs[i].isChecked()){
						if(specName[i].getRawValue().equalsIgnoreCase("")){
							specFlag=false;
							MessageBox.alert(msgs.error(),msgs.chooseSpec());
						}else{
					specs[j]=new String();
					specs[j]=specName[i].getValue();
					j++;
					}
					}	
				}
					boolean c=false;
					String s=null;
	
					/*
					 * loop for checking duplicacy of specialization
					 */
					for(int zz=0;zz<specs.length;zz++){
						if(c==false){
							s=specs[zz];							
						}
						for(int z=0;z<specs.length;z++){
							if(s.equalsIgnoreCase(specs[z])){
								if(z!=zz){
								c=true;
								break;
								}
							}
						}
						
					}
					
					
					if(c==true){
						specFlag=false;
						MessageBox.alert(msgs.error(),msgs.duplicateSpec());
					}else{
						input.setSpecialization_name(specs);	
					}

				}else{
					specExist=false;
				}	
				
				}catch (Exception e1) {
			System.out.println("exception in storing specs "+e1);
				}
				input.setSpecilization(specExist);
				
			if(specFlag==true)	{      
		      			tableFlexTable.clear();
		            	HoriPanel.clear();
		           	HorizontalPanel reserveHorizontalPanel=new HorizontalPanel();
		        	HorizontalPanel reservelinksHorizontalPanel=new HorizontalPanel();
		        	final Button nextSaveButton=new Button(cons.saveButton());
		         	final FlexTable reserveflex=new FlexTable();
		            	final FormPanel reserveForm=new FormPanel();
		            	reserveForm.setFrame(true);
		            	reserveForm.setTitle(cons.reserveSeats());
		            	reserveHorizontalPanel.add(nextSaveButton);
		            	final CheckBox reserveCheckBox=new CheckBox(cons.isReserve());
   
		            	final NumberField percentageText[] = new NumberField[50];
		            	final CheckBox reserveCheck[]=new CheckBox[50];
		            	final ComboBox categoryCombo[] = new ComboBox[50];
		            	final Label categoryLabel[]=new Label[50];
		            	final Label percentageLabel[]=new Label[50];
		               	final Hyperlink otherReserveHyper=new Hyperlink(cons.anotherCategory(),null);
		            	final Hyperlink removeReserveHyper=new Hyperlink("Remove Last Category",null);
		            			            	
		            	
		            	tableFlexTable.setWidget(4,0,reserveCheckBox);
		            	
		            							
		            	reserveForm.setSize("700px","200px");
		            	reservelinksHorizontalPanel.setSpacing(20);
		            	reservelinksHorizontalPanel.add(otherReserveHyper);
//		            	reservelinksHorizontalPanel.add(removeReserveHyper);			
						
		            	
		            	
		            	
						
						
		try{
			VerticalPanel reserveVPanel=new VerticalPanel();
			
			reserveScroll.setSize("635px","175px");
			
			reserveVPanel.add(reserveflex);
			reserveVPanel.add(reservelinksHorizontalPanel);
			reserveScroll.clear();
			reserveScroll.add(reserveVPanel);
			reserveForm.add(reserveScroll);
			
		}catch (Exception e2) {
		System.out.println("e2 "+e2);
		}			

    	

	categoryList();
		
	
		reserveCheckBox.addClickHandler(new ClickHandler(){

							public void onClick(ClickEvent arg0) {
							
								try{    
									if(reserveCheckBox.isChecked()){
										r=0;
								
									reserveForm.setVisible(true);
				            		percentageText[r]=new NumberField();
				            		reserveCheck[r]=new CheckBox();
				            		reserveCheck[r].setChecked(true);
			                    	categoryLabel[r]=new Label(cons.select()+cons.category());
			                    	categoryCombo[r]=new ComboBox();
			                    	percentageLabel[r]=new Label(cons.percent());
//			                    	categoryLabel[r].setText("Choose Category ");
			                    	categoryCombo[r].setEmptyText(cons.select()+cons.category());
			                    	percentageText[r].setEmptyText(cons.percent());
//			                    	percentageText[r].setMaxLength(3);
			                    	
			                    	categoryCombo[r].setStore(catstore);
			                    	
			                		categoryCombo[r].setForceSelection(true);
			                		categoryCombo[r].setMinChars(1);
			                		categoryCombo[r].setDisplayField("cat_name");
			                		categoryCombo[r].setValueField("cat_code");
			                		 		categoryCombo[r].setMode(ComboBox.LOCAL);
			                		categoryCombo[r].setTriggerAction(ComboBox.ALL);
			                		categoryCombo[r].setEmptyText(cons.select()+cons.category());
			                		categoryCombo[r].setLoadingText(cons.loading());
			                		categoryCombo[r].setTypeAhead(true);
			                		categoryCombo[r].setSelectOnFocus(true);
			                		categoryCombo[r].setWidth(190);
			                		categoryCombo[r].setHideTrigger(false);
			                    	

			                	   
			                    	
			                    	reserveflex.clear();
			                    	
			                    	reserveflex.setWidget(r,0, categoryLabel[r]);
			                    	reserveflex.setWidget(r,1, categoryCombo[r]);
			                    	reserveflex.setWidget(r,2, percentageLabel[r]);
			                    	  reserveflex.setWidget(r,3, percentageText[r]);
			                    	  reserveflex.setWidget(r,4, reserveCheck[r]);
			                    	    	removeReserveHyper.setVisible(false);
			                                    	    
				            		
									}else
									{
										
										reserveForm.setVisible(false);
		  	  
									}
						                    
						                    
						                	}catch (Exception e1) {
												System.out.println(e1);
											}
							}
		                	
		                });
		            	
		            	
		             	otherReserveHyper.addClickListener(new ClickListener(){

							public void onClick(Widget arg0) {
			            		  r++;
			            			percentageText[r]=new NumberField();
			            			reserveCheck[r]=new CheckBox();
				            		reserveCheck[r].setChecked(true);
			                    	categoryLabel[r]=new Label();
			                    	categoryCombo[r]=new ComboBox();
			                    	percentageLabel[r]=new Label(cons.percent());
			                    	categoryLabel[r].setText(cons.select()+cons.category());
//		                  		categoryLabel[r].setText("Choose Category ");
		                  		categoryCombo[r].setEmptyText(cons.select()+cons.category());
		                  		percentageText[r].setEmptyText(cons.percent());
//		                  		percentageText[r].setMaxLength(3);
		                  		
		                  		
		                  		
		                    	categoryCombo[r].setStore(catstore);
		                    	
		                		categoryCombo[r].setForceSelection(true);
		                		categoryCombo[r].setMinChars(1);
		                		categoryCombo[r].setDisplayField("cat_name");
		                		categoryCombo[r].setValueField("cat_code");
		                		categoryCombo[r].setMode(ComboBox.LOCAL);
		                		categoryCombo[r].setTriggerAction(ComboBox.ALL);
		                		categoryCombo[r].setEmptyText(cons.select()+cons.category());
		                		categoryCombo[r].setLoadingText(cons.loading());
		                		categoryCombo[r].setTypeAhead(true);
		                		categoryCombo[r].setSelectOnFocus(true);
		                		categoryCombo[r].setWidth(190);
		                		categoryCombo[r].setHideTrigger(false);
		                  		
		                	 
		                  		
		                  	       		reserveflex.setWidget(r,0, categoryLabel[r]);
		                    	reserveflex.setWidget(r,1, categoryCombo[r]);
		                    	reserveflex.setWidget(r,2, percentageLabel[r]);
		                  	  reserveflex.setWidget(r,3, percentageText[r]);
		                  	reserveflex.setWidget(r,4, reserveCheck[r]);
		                  	reserveScroll.scrollToBottom();	
		                  	if(r>0){
		                  		removeReserveHyper.setVisible(true);
		                  	}      	
		                  	
							}
		             		
		             	});
		             	
		             	removeReserveHyper.addClickListener(new ClickListener(){

							public void onClick(Widget arg0) {
						  		removeReserveHyper.setVisible(true);
								reserveflex.removeRow(r);
			                            	r--;
		                  	reserveScroll.scrollToBottom();	
		                  	if(r<1){
		                  		removeReserveHyper.setVisible(false);
		                  	}
							}
		             		
		             	});
		             	
		             	
		            	VertiPanel.clear();
		            	reserveForm.setVisible(false);
		            	VertiPanel.add(AddLabel);
		                VertiPanel.setSpacing(30);
		                VertiPanel.add(nameText);
		                VertiPanel.add(sessionText);
		                VertiPanel.add(tableFlexTable);
		                VertiPanel.add(reserveForm);
		                VertiPanel.setSpacing(20);
		                VertiPanel.add(reserveHorizontalPanel);
		            
		                
		         	   ProgrammeflexTable.setWidget(0, 0, VertiPanel);

		                ProgrammeHorizontalPanel.add(ProgrammeflexTable);
		                ProgrammeHorizontalPanel1.clear();
		                ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
		                
		                
		                
		                
		                
		                nextSaveButton.addListener(new ButtonListenerAdapter(){
		                    public void onClick(Button ProgrammeAddButton, EventObject e) {
		                    	
		        				String[] reservecat=new String[r+1];
		        				String[] reserveper=new String[r+1];
		        				boolean reserveExist=true;
		        				boolean reserveFlag=true;
		        				
		        				if(reserveCheckBox.isChecked()){
		        					int j=0;
		        					reserveExist=true;
		        					for(int i=0;i<=r;i++){
		        						
		        						/*
		        						 * Storing values of reservations
		        						 */
		        						
		        						if(reserveCheck[i].isChecked()){
		        							
		        						if((categoryCombo[i].getValueAsString().equalsIgnoreCase("")) || (percentageText[i].getText().equalsIgnoreCase(""))){
		        							
		        							reserveFlag=false;
		        							MessageBox.alert(msgs.error(),msgs.requiredCatPercent());
		        						}else{
		        					reservecat[j]=new String();
		        					reservecat[j]=categoryCombo[i].getValueAsString();
//		        					System.out.println("value of reserver "+reservecat[j]);
		        					reserveper[j]=new String();
		        					reserveper[j]=percentageText[i].getText();
//		        					System.out.println("value of percentage "+reservecat[j]);
		        					j++;
		        							
		        					}
		        					}
		        					}	
		        					
		        					boolean c=false;
		        					String s=null;
		        							
		        					for(int zz=0;zz<reservecat.length;zz++){
	        							s=reservecat[zz];

		        						for(int z=(zz+1);z<reservecat.length;z++){
		        							if(s.equalsIgnoreCase(reservecat[z])){
		        								c=true;
		        								break;
		        								
		        							}
		        						}
		        						
		        					}
		        					
		        					
		        					if(c==true){
		        						reserveFlag=false;
		        						MessageBox.alert(msgs.error(),msgs.duplicateCategory());
		        					}	else
		        					{

		        						double count=0;
//		        						System.out.println("NO duplicate category "+reservecat.length);
		        						
			        					for(int zz=0;zz<reservecat.length;zz++){
//			        						System.out.println("reserveper[zz]="+reserveper[zz] + " zz="+zz);
			        						count=count+Double.parseDouble(reserveper[zz]);
//			        						System.out.println("count "+count);
			        					}
			        					
			        					if(count>100.00){
			        						reserveFlag=false;
//			        						System.out.println("count exceeding"+count);
			        						MessageBox.alert(msgs.error(),msgs.reserveCantExceed());
			        					}else
			        					{
//			        						System.out.println("count is ok");
			        					}
		        					}
		        					
		        				}else{
		        					reserveExist=false;
		        				}
		                    	
		        				if(reserveFlag==true){
		        					input.setReservation(reserveExist);
		        					input.setCategory(reservecat);
		        					input.setPercentageSeats(reserveper);
		        					
		        					FlexTable resultFlex=new FlexTable();
		        					resultFlex.setWidget(0, 1,new Label(input.getProgram_name()));
		        					resultFlex.setWidget(1, 1,new Label(input.getProgram_code()));
		        					resultFlex.setWidget(2, 1,new Label(input.getProgram_mode()));
		        					resultFlex.setWidget(3, 1,new Label(input.getProgram_type()));
		        					resultFlex.setWidget(4, 1,new Label(input.getNo_of_terms()));
		        					resultFlex.setWidget(5, 1,new Label(input.getTotal_credits()));
		        					resultFlex.setWidget(6, 1,new Label(input.getNumber_of_attempt_allowed()));
		        					resultFlex.setWidget(7, 1,new Label(input.getMax_number_of_fail_subjects()));
		        					resultFlex.setWidget(8, 1,new Label(input.getUGorPG()));
		        					resultFlex.setWidget(0, 4,new Label(input.getFixed_duration()));
		        					resultFlex.setWidget(1, 4,new Label(input.getMinimun_duration()));
		        					resultFlex.setWidget(2, 4,new Label(input.getMaximum_duration()));
//		        					System.out.println("output of start date "+input.getStart_day_Month()[0]);
		        					for(int i=0;i<input.getStart_day_Month().length;i++){
		        					resultFlex.setWidget(i+3, 4,new Label(input.getStart_day_Month()[i]));
		        					}
		        					Window window = new Window();
		        					window.setTitle(cons.heading_programdetails());
		        					window.add(resultFlex);
//		        					window.show();
		                    	
		                        MessageBox.show(
                                        new MessageBoxConfig() {

                                        {
                                            setTitle(
                                                    msgs.confirm());
                                            setMsg(msgs.alert_confirmentries());
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
                                                        	/*
                                                        	 * code temporarily removed
                                                        	 */
//                                                        	Iterator<program_data> i=list.iterator();
//                                                    		
//                                                    		while(i.hasNext())
//                                                    		{
//                                                    			program_data d=(program_data)i.next();
//                                                    			System.out.println(d.getBranch_name());
//                                                    			String[] s=d.getSpec_name();
//                                                    			for(int i1=0;i1<s.length;i1++){
//                                                    				System.out.println("|||"+s[i1]);
//                                                    			}
//                                                    		}
              
                                                        	
                                                        	
                                                     
                                                        	
                                                        	
                                                        	connectService.methodAddProgDetails(userid, input,new AsyncCallback<String>(){

																public void onFailure(
																		Throwable arg0) {
																	MessageBox.alert(msgs.failure(),arg0.getMessage());
																	
																}

																public void onSuccess(
																		String arg0) {
																	
																	MessageBox.alert(msgs.alert(),msgs.alert_successfulentry(),new AlertCallback(){

																		public void execute() {
																	methodAddProgramme();
																	
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
		                        
		                        
		                    }else
		                    {
//		                    	MessageBox.alert("Error","Error in category");
		                    }
		                    }
		                });
		            	
		       		
				
			}
			}
    	});    
        
        

    }

    
    
   /****************************Add Term Details*****************************************/ 
    
    /*
     * code temporarily removed
     */
//    public void methodAddTermDetails()
//    {
//    	/*
//    	 * Number of terms to be accessed from program Name chosen
//    	 * 
//    	 * final int numberOfTerms=Integer.parseInt(semesterText.getText());
//    	 *
//    	 */
//    	final int numberOfTerms=4;
//    	
//    	
// 		termCount=1;
// 		
//    	FormPanel termDetailForm=new FormPanel();
//    	HoriPanel.clear();
//    	HoriPanel.add(termOKButton);
//    	
//    	//Store term details in arrays
//    	termDetailForm.setFrame(true);
//    	termDetailForm.setBorder(true);
//    	termDetailForm.setTitle("Term Details");
//    	
////    	Label progNameLabel=new Label("program Name ");
//    	Label termNumberLabel=new Label("Term Number ");            	
//    	Label minSGPALabel=new Label("Minimum SGPA ");
//    	Label termNameLabel=new Label("Term Name ");
//    	Label teachingDaysLabel=new Label("Number of Teaching Days ");
//    	Label durationLabel=new Label("Duration (in weeks) ");
//    	Label termStartLabel=new Label("Term Start Date ");
//    	Label termEndLabel=new Label("Term End Date ");
//    	Label totalCreditsLabel=new Label("Total Credits ");
//    	
//    	final Label termNumberText=new Label();
//    	final NumberField minSGPAText=new NumberField();
//    	final TextField termNameText=new TextField();
//    	final NumberField teachingDaysText=new NumberField();
//    	final NumberField durationText=new NumberField();
//    	final DateField startDate=new DateField();
//    	final DateField endDate=new DateField();
//    	final NumberField totalCreditsText=new NumberField();
//    	final ComboBox programComboBox=new ComboBox();
//    	
//    	programComboBox.setMinChars(1);  
//	      programComboBox.setDisplayField("program_name"); 
//	      programComboBox.setValueField("program_id");
//	      programComboBox.setMode(ComboBox.LOCAL);  
//	      programComboBox.setTriggerAction(ComboBox.ALL);  
//	      programComboBox.setEmptyText("Select Program");  
//	      programComboBox.setLoadingText("Searching...");  
//	      programComboBox.setTypeAhead(true);  
//	      programComboBox.setSelectOnFocus(true);  
//	      programComboBox.setWidth(190);  
//	      programComboBox.setHideTrigger(false);
//	      
//	      CM_progOfferedBy pob=new CM_progOfferedBy(userid);
//	      pob.methodProgramPopulate(programComboBox);
//	            
//	      
//    	AddLabel.setText("Add Programme Details");
//    	AddLabel.setStyleName("Heading");
//    	
//    	 tableFlexTable.clear();
//    	 tableFlexTable.setWidget(3,0, ProgrammeNameLabel);
//         tableFlexTable.setWidget(3,1, programComboBox);
//    	 tableFlexTable.setWidget(4,0, termNumberLabel);
//         tableFlexTable.setWidget(4,1, termNumberText);
//         tableFlexTable.setWidget(5,0, minSGPALabel);
//         tableFlexTable.setWidget(5,1, minSGPAText);
//         tableFlexTable.setWidget(6,0, termNameLabel);
//         tableFlexTable.setWidget(6,1, termNameText);
//         tableFlexTable.setWidget(7,0, teachingDaysLabel);
//         tableFlexTable.setWidget(7,1, teachingDaysText);
//         tableFlexTable.setWidget(8,0, durationLabel);
//         tableFlexTable.setWidget(8,1, durationText);
//         tableFlexTable.setWidget(9,0, termStartLabel);
//         tableFlexTable.setWidget(9,1, startDate);
//         tableFlexTable.setWidget(10,0, termEndLabel);
//         tableFlexTable.setWidget(10,1, endDate);
//         tableFlexTable.setWidget(11,0, totalCreditsLabel);
//         tableFlexTable.setWidget(11,1, totalCreditsText);
//
//         termNumberText.setText("1");
//         termOKButton.addListener(new ButtonListenerAdapter(){
//        	 public void onClick(Button ProgrammeAddButton, EventObject e) {
//        		 termCount++;
//        		if(termCount<=numberOfTerms){                 		 
//        		termNumberText.setText(""+termCount); 
//        		}else{
//        		 HoriPanel.clear();
//        		 termSaveButton.setText("Save");
//         		HoriPanel.add(termSaveButton);
//        		}
//        	 }
//          });
//   
//         termDetailForm.add(tableFlexTable);
//
//         VertiPanel.clear();
//    
//         methodShowSession();
//         
//    	   VertiPanel.add(AddLabel);
//           VertiPanel.setSpacing(30);
//           VertiPanel.add(nameText);
//           VertiPanel.add(sessionText);
//           VertiPanel.add(termDetailForm);
//           VertiPanel.setSpacing(20);
//           VertiPanel.add(HoriPanel);
//           
//           ProgrammeflexTable.clear();
//    	   ProgrammeflexTable.setWidget(0, 0, VertiPanel);
//
//    	   ProgrammeHorizontalPanel.clear();
//           ProgrammeHorizontalPanel.add(ProgrammeflexTable);
//           ProgrammeHorizontalPanel1.clear();
//           ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
//    	
//    }
    
   /********************************Integrated programs***************************************/ 
    
    /*
     * Method to add integrated program details
     * 
     * coding to be done later
     * 
     */
    
  public void methodIntegratedProgDetails()
  {
	  
	count=0;
	  
	   final FormPanel integratedDetailForm=new FormPanel();
	   final ScrollPanel stepScrollPanel=new ScrollPanel();
       VerticalPanel stepVerticalPanel=new VerticalPanel();
	   final Button backButton=new Button("Back");
	   
	   FormPanel previewPanel=new FormPanel();
       ScrollPanel previewScrollPanel=new ScrollPanel();
       final VerticalPanel previewVerticalPanel=new VerticalPanel();
       Label previewLabel=new Label("Preview");
       previewLabel.setStyleName("Heading");
       previewPanel.setBorder(true);
       previewPanel.setFrame(true);
       previewScrollPanel.setSize("480px","580");
       previewPanel.setSize("500px","600px");
       previewPanel.setTitle("Preview Details");
       previewVerticalPanel.setSpacing(20);
  
  	integratedDetailForm.setFrame(true);
  	integratedDetailForm.setBorder(true);
  	integratedDetailForm.setTitle("Integrated Program Details");
  	
  	final Button nextButton=new Button("Next");
  	
  	Label AddLabel=new Label();
  	Label progNameLabel=new Label("Integrated Program Name ");
  	final Label entryProgNameLabel=new Label("First Program Name ");
  	Label numberOfStepsLabel=new Label("How many Degrees are offered in program ");            	
  	
  	final Label stepNumberLabel=new Label("Step Number "); 
  	final Label stepProgLabel=new Label("Step Program "); 
  	final Label termNumberLabel=new Label("Number of Terms ");
  	final Label minSGPALabel=new Label("Minimum SGPA ");
  	final Label availableTermLabel=new Label("Availbale From Term");
   	final Label totalSeatsLabel=new Label("Available Seats ");
   
  	final NumberField stepNumberText=new NumberField();
	final ComboBox stepProgcomboBox=new ComboBox();
 	final NumberField termNumberText=new NumberField();
   	final NumberField minSGPAText=new NumberField();
  final ComboBox availableTermComboBox=new ComboBox();
  	final NumberField totalseatsText=new NumberField();
  	
  	final ComboBox progcomboBox=new ComboBox();
  	final ComboBox entryProgcomboBox=new ComboBox();
  	
  	progcomboBox.setEmptyText("Choose Integrated Program");  	
  	entryProgcomboBox.setEmptyText("Choose Entry Program");
  	stepProgcomboBox.setEmptyText("Choose Step Program"); 
  	stepNumberText.setAllowDecimals(false);
  	
  	
  	AddLabel.setText("Add Integrated Programme Details");
  	AddLabel.setStyleName("Heading");
  	
  	 tableFlexTable.clear();
  	 tableFlexTable.setWidget(3,0, progNameLabel);
     tableFlexTable.setWidget(3,1, progcomboBox);
     tableFlexTable.setWidget(4,0, numberOfStepsLabel);
     tableFlexTable.setWidget(4,1, stepNumberText);
//     tableFlexTable.setWidget(5,0, entryProgNameLabel);
//     tableFlexTable.setWidget(5,1, methodDrawGrid());

       
       nextButton.addListener(new ButtonListenerAdapter(){
    	   public void onClick(Button ProgrammeAddButton, EventObject e) {
    		 if(nextButton.getText().equalsIgnoreCase("Next")){
    		
    		g1=methodDrawGrid();
        	 g2=methodDrawGrid(); 
    		   		tableFlexTable.clear();
    		   		if(count==0)
    		   		{
    		   			stepNumberLabel.setVisible(false);
    		   			stepNumberText.setVisible(false);
    		   			count++;
    		   		}else
    		   		{
    		   			
    		   			stepNumberLabel.setVisible(true);
    		   			stepNumberText.setVisible(true);
    		   			entryProgNameLabel.setVisible(false);
    		   			g1.setVisible(false);
    		   			
    		   			switch (count){
    		   			case 1: {stepProgLabel.setText(count+1+"nd Degree Program");break;}
    		   			case 2: {stepProgLabel.setText(count+1+"rd Degree Program");break;}
    		   			default: {stepProgLabel.setText(count+1+"th Degree Program");break;}
    		   			}	
    		   		
    		   		 tableFlexTable.setWidget(5,0, stepProgLabel);
    		   		 tableFlexTable.setWidget(5,1, g2);
    		   		 tableFlexTable.setWidget(6,0, termNumberLabel);
    		   		 tableFlexTable.setWidget(6,1, termNumberText);
    		   		 tableFlexTable.setWidget(7,0, minSGPALabel);
    		   		 tableFlexTable.setWidget(7,1, minSGPAText);
    		   		 tableFlexTable.setWidget(8,0, availableTermLabel);
    		   		 tableFlexTable.setWidget(8,1, availableTermComboBox);
    		   		 tableFlexTable.setWidget(9,0, totalSeatsLabel);
    		   		 tableFlexTable.setWidget(9,1, totalseatsText);
    		   		integratedDetailForm.setTitle("Integrated Program Step"+ count +"Details");
    		   		count++;
    		   		
    		   		if(count<Integer.parseInt(stepNumberText.getText())){
    		   		 nextButton.setText("Next");    		   
    		   		}else
    		   		{
    		   		 nextButton.setText("Save");
    		   		}
    		   		
    		   		}
//    		  	 	tableFlexTable.setWidget(3,0, stepNumberLabel);
//    		  	 	tableFlexTable.setWidget(3,1, stepNumberText);
    		       tableFlexTable.setWidget(4,0, entryProgNameLabel);
    		       tableFlexTable.setWidget(4,1, g1);
    		      
    		       stepScrollPanel.setSize("530px","270");
    		       integratedDetailForm.setSize("550px","300px");
    		       
    		       
    		       
    		      
    		       backButton.setVisible(true);
    		       integratedDetailForm.setTitle("Integrated Program Step Details");
//    		       stepNumberText.setText("1");
//    		         termOKButton.addListener(new ButtonListenerAdapter(){
//    		        	 public void onClick(Button ProgrammeAddButton, EventObject e) {
//    		        		 termCount++;
//    		        		if(termCount<=totalSteps){                 		 
//    		        		stepNumberText.setText(""+termCount); 
//    		        		}else{
//    		        		 HoriPanel.clear();
//    		        		 termSaveButton.setText("Save");
//    		         		HoriPanel.add(termSaveButton);
//    		        		}
//    		        	 }
//    		          });
    		       
    		       
    	   }else
    	   {
    		   MessageBox.alert("Congratulations","Integrated Program Details Saved Successfully");
    	   }
    	   }
       });
       
       backButton.addListener(new ButtonListenerAdapter(){
    	   public void onClick(Button ProgrammeAddButton, EventObject e) {
    		   Record[] records=(g1.getSelectionModel()).getSelections();
    		   if(records.length>0){
    			   for(int l=0;l<records.length;l++){
    		   Record r1=records[l];
    		   String prog=r1.getAsString("Program Name");
    		   Label prevLabel=new Label("First Program : "+prog);
    		  previewVerticalPanel.add(prevLabel);
    			   }
    		   }
    	   }
    	   });
       
       integratedDetailForm.setSize("450px","150px");
 
     
       stepVerticalPanel.add(tableFlexTable);
       stepScrollPanel.add(stepVerticalPanel);
       integratedDetailForm.add(stepScrollPanel);

       backButton.setVisible(false);
       
   	HoriPanel.clear();
   	HoriPanel.setSpacing(20);
  	HoriPanel.add(nextButton);      
    HoriPanel.add(backButton);   
       
       VertiPanel.clear();
  
//       methodShowSession();
       
  	   VertiPanel.add(AddLabel);
         VertiPanel.setSpacing(30);
         VertiPanel.add(nameText);
         VertiPanel.add(sessionText);
         VertiPanel.add(integratedDetailForm);
         VertiPanel.setSpacing(20);
         VertiPanel.add(HoriPanel);
         
       
         
         previewVerticalPanel.add(previewLabel);
         previewScrollPanel.add(previewVerticalPanel);
         previewPanel.add(previewScrollPanel);
         
         
         ProgrammeflexTable.clear();
  	   ProgrammeflexTable.setWidget(0, 0, VertiPanel);
  	 ProgrammeflexTable.setWidget(0,10, previewPanel);

  	   ProgrammeHorizontalPanel.clear();
         ProgrammeHorizontalPanel.add(ProgrammeflexTable);
         ProgrammeHorizontalPanel1.clear();
         ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
  }
    
    
    
    
  
    
  public GridPanel methodDrawGrid()
  {
	  final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
//	  final Panel p1=new Panel();
	  
		final RecordDef rDef = new RecordDef(
				new FieldDef[]{
						new StringFieldDef("Program Name")
				}
		);
		
	
		final GridPanel grid = new GridPanel();
		
		programNameObject= new Object[10][9];
		 
        String str = null;
       
       try{
			for(int i=0 ; i <10; i++){
			
         	for (int k = 0; k < 9; k++) {         		
                 	str="program";
         		 programNameObject[i][k]=str+i;  
//         		 System.out.println("result "+object1[i][k]);

             }

			}
       }catch (Exception e2) {
		System.out.println("e2     "+e2);
	}
			
		
		  Object[][] data = programNameObject;	

		
		  MemoryProxy proxy = null;
		  try{
	        	 proxy = new MemoryProxy(data);  
		  }catch (Exception e3) {
				System.out.println("e3          "+e3);
			}
		         ArrayReader reader = new ArrayReader(rDef);  
		         Store store = new Store(proxy, reader); 
		  
		         store.load();  
		         grid.setStore(store);  
		  
		      
		         BaseColumnConfig[] columns = new BaseColumnConfig[]{  
		                 new CheckboxColumnConfig(cbSelectionModel), 
		                 new ColumnConfig(cons.label_programName(), "Program Name", 200, true, null, "Program"),  
		                   };  
		
		         final ColumnModel columnModel = new ColumnModel(columns);  
		         grid.setColumnModel(columnModel);  
		   
		         grid.setFrame(true);  
		         grid.setStripeRows(true);
		         grid.setAutoExpandColumn("Program");
		        		         
		         grid.setSelectionModel(cbSelectionModel);  
		        grid.setAutoWidth(true);
		         grid.setWidth("250px"); 
		         grid.setHeight("150px");
		  		    
    
    	
    	grid.setTitle("Choose Programs");
          
      
			    		          

  return grid;
  }		
 
    
 
    
    
    /***************************************Manage Program*****************************************************************/
    
    
    public void methodManageProg(final String method){
    	
    	   final Panel p1 = new Panel();
           final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
           bd.setMargins(6, 6, 6, 6);

           
    	  final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
    	  final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle(); 
    	final VerticalPanel manageProgVpanel =new VerticalPanel();
    	VerticalPanel containerVerticalPanel=new VerticalPanel();
    	FlexTable upperFlexTable=new FlexTable();
    	final VerticalPanel gridVPanel=new VerticalPanel();
    	Label heading=new Label("Manage Programs");
    	heading.setStyleName("heading");
    	final ComboBox progCbox=new ComboBox();
    	
    	progCbox.setForceSelection(true);
    	progCbox.setMinChars(1);
    	progCbox.setFieldLabel(cons.select()+cons.label_programName());
    	progCbox.setDisplayField("program_name");
    	progCbox.setValueField("program_id");
    	progCbox.setMode(ComboBox.LOCAL);
    	progCbox.setTriggerAction(ComboBox.ALL);
    	progCbox.setEmptyText(cons.select()+cons.label_programName());
    	progCbox.setLoadingText(cons.loading());
    	progCbox.setTypeAhead(true);
    	progCbox.setSelectOnFocus(true);
    	progCbox.setWidth(190);
    	progCbox.setHideTrigger(false);
    	
    	
    	upperFlexTable.clear();
    	upperFlexTable.setWidget(0,0,new Label(cons.select()+cons.label_programName()));
    	upperFlexTable.setWidget(0,10,progCbox);
    	
    	
    	
    	
    	connectService.methodprogList(userid,new AsyncCallback<CM_progMasterInfoGetter[]>(){

			public void onFailure(Throwable arg0) {
				MessageBox.alert(msgs.failure(),arg0.getMessage());
				
			}

			public void onSuccess(CM_progMasterInfoGetter[] result) {
				if(result.length==0)
				{
					MessageBox.alert(msgs.alert(),msgs.noProgram());				
				}
				{
				RecordDef recordDef = new RecordDef(new FieldDef[] {
                        new StringFieldDef("program_name"),
                        new StringFieldDef("program_id"),
                        new StringFieldDef("program_code")
                    });

            String[][] object2 = new String[result.length][3];

            String[][] data = object2;

            for (int i = 0; i < result.length; i++) {
                object2[i][0] = result[i].getProgram_name();
                object2[i][1] = result[i].getProgram_id();
                object2[i][2] = result[i].getProgram_code();
            }
            
            

            for (int i = 0; i < result.length;
                    i++) {
                oracle.add(result[i].getProgram_code());
            }
            

            MemoryProxy proxy = new MemoryProxy(data);

            ArrayReader reader = new ArrayReader(recordDef);
            Store store = new Store(proxy, reader);
            store.load();

            progCbox.setStore(store);
				
			}
			}
    		
    	});
    	
    	
    	FormPanel searchFormPanel=new FormPanel();
    	
    	searchFormPanel.setTitle(cons.search());
    	searchFormPanel.setBorder(true);
    	searchFormPanel.setBodyBorder(true);
//    	searchFormPanel.setStyle("background");
    	 
         
 final SuggestBox codeSuggestBox=new SuggestBox(oracle);
    final Button okButton=new Button("OK");	
    	
    	
      FlexTable searchFlexTable=new FlexTable();
      searchFlexTable.setWidth("100%");
      searchFlexTable.setWidget(1, 0, new Label(cons.label_programCode()));
      searchFlexTable.setWidget(1, 1, codeSuggestBox);


//      searchFlexTable.setStyleName("background");
      searchFormPanel.add(searchFlexTable);
  
    final CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
    	
    	
    	okButton.addListener(new ButtonListenerAdapter(){
    		 
			public void onClick(Button button, EventObject e) {
    			
    			  	String criteria=null;	
    			  	
    				final GridPanel grid = new GridPanel();
    			 if(method.equalsIgnoreCase("basic"))
 		        {
    				 if(codeSuggestBox.getText().equals("")){
       					 criteria="id";
       					 if(progCbox.getValue()==null){
        					 object.setProgram_id("%");
        				 }else{
        				 object.setProgram_id(progCbox.getValue());
        				  }
       					 }else{
       						 criteria="code"; 
       						 object.setProgram_code(codeSuggestBox.getText());
       					 }
    				
    				 connectService.methodProgMasterDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

					public void onFailure(Throwable arg0) {
						MessageBox.alert(msgs.failure(),arg0.getMessage());
						
					}

					public void onSuccess(CM_progMasterInfoGetter[] arg0) {
//						VerticalPanel p1Panel=new VerticalPanel();			 
//						final GridPanel grid = new GridPanel();
						 if (arg0.length == 0) {
						 grid.setTitle(msgs.error_record());
						  } else {
						    grid.setTitle(cons.heading_programdetails());
						  }
						 
						 final RecordDef rDef = new RecordDef(new FieldDef[] {
	                            new StringFieldDef("programName"),
	                            new StringFieldDef("programCode"),
	                            new StringFieldDef("mode"),
	                            new StringFieldDef("type"),
	                            new IntegerFieldDef("terms"),
	                            new FloatFieldDef("credits"),
	                            new FloatFieldDef("attempts"),
	                            new FloatFieldDef("failSubjects"),
	                            new StringFieldDef("ugpg"),
	                            new StringFieldDef("tencodes"),
	                            new StringFieldDef("program_id")
	                        });
							 
						 object1 = new Object[arg0.length][11];
						 
						                                         String str = null;
						
						                                         try {
						                                             for (int i = 0; i < arg0.length;
						                                                     i++) {
						                                            	
						                                                 for (int k = 0; k < 11; k++) {
						                                                     if (k == 0) {
						                                                         str = arg0[i].getProgram_name();
						                                                     } else if (k == 1) {
						                                                    	 str = arg0[i].getProgram_code();
						                                                     } else if (k == 2) {
						                                                    	 str = arg0[i].getProgram_mode();
						                                                     } else if (k == 3) {
						                                                    	 str = arg0[i].getProgram_type();
						                                                     } else if (k == 4) {
						                                                    	 str = arg0[i].getNo_of_terms();
						                                                     } else if (k == 5) {
						                                                    	 str = arg0[i].getTotal_credits();
						                                                     } else if (k == 6) {
						                                                    	 str = arg0[i].getNumber_of_attempt_allowed();
						                                                     } else if (k == 7) {
						                                                    	 str = arg0[i].getMax_number_of_fail_subjects();
						                                                     } else if (k == 8) {
						                                                    	 str = arg0[i].getUGorPG();
						                                                     }
						                                                     else if (k == 9) {
						                                                    	 str = arg0[i].getTencodes();
						                                                     }else if (k == 10) {
						                                                    	 str = arg0[i].getProgram_id();
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
				
						 try{
						                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
						                                                 new CheckboxColumnConfig(cbSelectionModel),
						                                                 new ColumnConfig(cons.label_programName(),
						                                                     "programName", 200, true, null,
						                                                     "programName"),
						                                                 new ColumnConfig(cons.label_programCode(),
						                                                     "programCode", 200, true, null,
						                                                     "programCode"),
						                                                 new ColumnConfig(cons.label_programMode(),
						                                                     "mode", 200, true,
						                                                     null, "mode"),
						                                                 new ColumnConfig(cons.label_programType(),
						                                                     "type", 100, true, null,
						                                                     "type"),
						                                                 new ColumnConfig(cons.label_numberOfTerms(),
						                                                     "terms", 80, true, null,
						                                                     "terms"),
						                                                 new ColumnConfig(cons.label_totalCredits(),
						                                                     "credits", 80, true, null,
						                                                     "credits"),
						                                                 new ColumnConfig(cons.label_noOfAttempts(),
						                                                     "attempts", 55, true, null,
						                                                     "attempts"),
						                                                 new ColumnConfig(cons.label_maxFail(), "failSubjects",
						                                                     55, true, null, "failSubjects"),
						                                                 new ColumnConfig(cons.ugPg(), "ugpg",
								                                               55, true, null, "ugpg")
						                                             };
						 
						                                         final ColumnModel columnModel = new ColumnModel(columns);
						                                         grid.setColumnModel(columnModel);
						 }catch (Exception e) {
								System.out.println(e+ "  hwgehghweg");
							}
						                                         grid.setFrame(true);
						                                         grid.setStripeRows(true);
						 
						                                         grid.setSelectionModel(cbSelectionModel);
						                                         grid.setAutoWidth(true);
						                                         grid.setWidth(1200);
						                                         grid.setHeight(280);
						 
						                                         Toolbar topToolBar = new Toolbar();
						                                         topToolBar.addFill();
						 
						                                     
						 
						 ToolbarButton editButton = new ToolbarButton(cons.edit(),
				                                                new ButtonListenerAdapter() {
			                                                    public void onClick(
			                                                        Button editButton,
			                                                        EventObject e) {
			                                                        //						    		    	   Record rec=cbSelectionModel.getSelected();
			                                                        //						    		    	 System.out.println(rec.getAsString("Name"));
			                                                    	try{
			                                                        Record[] records =null;
			                                                        records = cbSelectionModel.getSelections();
			System.out.println("record length "+records.length);
			
			                                                        if (records.length < 1) {
			                                                            MessageBox.alert(msgs.error(),
			                                                                msgs.atleastOneRecord());
			                                                        } else if (records.length > 1) {
			                                                            MessageBox.alert(msgs.error(),
			                                                                msgs.onlyOneRecord());
			                                                        } else if (records.length == 1) {
			                                                            final Record record = records[0];
			                                                            
			                                                            final ComboBox ugpgComboBox=new ComboBox();
			                                                            
			                                                        	ugpgComboBox.setForceSelection(true);
			                                                        	ugpgComboBox.setMinChars(1);
//			                                                        	ugpgComboBox.setFieldLabel("Type");
			                                                        	ugpgComboBox.setDisplayField("UGPG");
			                                                        	ugpgComboBox.setValueField("id");
			                                                        	ugpgComboBox.setMode(ComboBox.LOCAL);
			                                                        	ugpgComboBox.setTriggerAction(ComboBox.ALL);
			                                                        	ugpgComboBox.setEmptyText(cons.ugPg());
			                                                        	ugpgComboBox.setLoadingText(cons.loading());
			                                                        	ugpgComboBox.setTypeAhead(true);
			                                                        	ugpgComboBox.setSelectOnFocus(true);
			                                                        	ugpgComboBox.setWidth(190);
			                                                        	ugpgComboBox.setHideTrigger(false);
			                                                        	
			                                                        	RecordDef recordDef = new RecordDef(new FieldDef[] {
			                                                                    new StringFieldDef("UGPG"),
			                                                                    new StringFieldDef("id")
			                                                                    
			                                                                });
			                                                    	
			                                                        String[][] object2 = new String[2][2];

			                                                        String[][] data = object2;
			                                                        
			                                                            object2[0][0] = "Under Graduate";
			                                                            object2[0][1] = "U";
			                                                            object2[1][0] = "Post Graduate";
			                                                            object2[1][1] = "P";

			                                                        MemoryProxy proxy = new MemoryProxy(data);

			                                                        ArrayReader reader = new ArrayReader(recordDef);
			                                                        Store store = new Store(proxy, reader);
			                                                        store.load();

			                                                        ugpgComboBox.setStore(store);
			                                                          
						
			                                                            FlexTable editprogTable = new FlexTable();
			                                                           final TextField progName=new TextField();
			                                                           final NumberField semesterText = new NumberField();
			                                                           final NumberField creditText = new NumberField();
			                                                           Label attemptsLabel=new Label(cons.label_noOfAttempts()); 
			                                                           final NumberField attemptField=new NumberField();
			                                                           Label maxFailCourseLabel=new Label(cons.label_maxFail()); 
			                                                           final NumberField maxFailCourseField=new NumberField();
			                                                     
			                                                          
			                                                           progName.setAllowBlank(false);
			                                                           semesterText.setAllowBlank(false);
			                                                           creditText.setAllowBlank(false);
			                                                           attemptField.setAllowBlank(false);
			                                                           maxFailCourseField.setAllowBlank(false);
			                                                           
			                                                           semesterText.setAllowDecimals(false);
			                                                           attemptField.setAllowDecimals(false);
			                                                           maxFailCourseField.setAllowDecimals(false);
			                                                           
			                                                           
			                                                           
			                                                           progName.setValue(record.getAsString("programName"));
			                                                           semesterText.setValue(record.getAsFloat("terms"));
			                                                           creditText.setValue(record.getAsFloat("credits"));
			                                                           attemptField.setValue(record.getAsFloat("attempts"));
			                                                           maxFailCourseField.setValue(record.getAsFloat("failSubjects"));
			                                                           ugpgComboBox.setValue(record.getAsString("ugpg"));
			                                                           
			                                                           
			                                                           
			                                                            editprogTable.clear();
			                                                            editprogTable.setWidget(3, 0, new Label(cons.label_programCode()+" : "));
			                                                            editprogTable.setWidget(3, 1, new Label(record.getAsString("programCode")));
			                                                            editprogTable.setWidget(4, 0, new Label(cons.label_programName() +" : "));
			                                                            editprogTable.setWidget(4, 1, progName);
			                                                            editprogTable.setWidget(5, 0, new Label(cons.label_programMode()+" : "));
			                                                            editprogTable.setWidget(5, 1, new Label(record.getAsString("mode")));
			                                                            editprogTable.setWidget(6, 0, new Label(cons.label_programType()+" : "));
			                                                            editprogTable.setWidget(6, 1, new Label(record.getAsString("type")));
			                                                            editprogTable.setWidget(7, 0, new Label(cons.label_numberOfTerms()));
			                                                            editprogTable.setWidget(7, 1, semesterText);
			                                                            editprogTable.setWidget(8, 0, new Label(cons.label_totalCredits()));
			                                                            editprogTable.setWidget(8, 1, creditText);
			                                                            editprogTable.setWidget(9, 0, attemptsLabel);
			                                                            editprogTable.setWidget(9, 1, attemptField);
			                                                            editprogTable.setWidget(10, 0, maxFailCourseLabel);
			                                                            editprogTable.setWidget(10, 1, maxFailCourseField);
			                                                            
			                                                            editprogTable.setWidget(11, 0, new Label(cons.ugPg()));
			                                                            editprogTable.setWidget(11, 1, ugpgComboBox);
			                                                           
			                                                        
			                                                            p1.clear();
			                                                            p1.add(editprogTable);
			
			                                                            Button windowUpdateButton =
			                                                                new Button(
			                                                                    cons.button_update());
			                                                            Button windowResetButton =
			                                                                new Button(
			                                                                    cons.resetButton());
			                                                            final Window window = new Window();
			                                                            window.setTitle(
			                                                                cons.heading_programdetails());
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
			                                                                    	
			                                                                    	
			                                                                    	
			                                                                    }
			                                                                });
			
			                                                            //Adding handler to update button of edit window 
			                                                            windowUpdateButton.addListener(new ButtonListenerAdapter() {
			                                                                    public void onClick(
			                                                                        Button button,
			                                                                        EventObject e) {
			                                                                    	CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
			                                                                        Boolean flag = true;
			                                                                       			                                                                       
			 			                                                           try {
			                                                                            progName.validate();
			                                                                        } catch (Exception e1) {
			                                                                            flag = false;
			                                                                        }
			
			                                                                        try {
			                                                                            semesterText.validate();
			                                                                        } catch (Exception e1) {
			                                                                            flag = false;
			                                                                        }
			
			                                                                        try {
			                                                                            creditText.validate();
			                                                                        } catch (Exception e1) {
			                                                                            flag = false;
			                                                                        }
			
			                                                                        try {
			                                                                            attemptField.validate();
			                                                                        } catch (Exception e1) {
			                                                                            flag = false;
			                                                                        }
			
			                                                                        try {
			                                                                            maxFailCourseField.validate();
			                                                                        } catch (Exception e1) {
			                                                                            flag = false;
			                                                                        }
			                                                                        
			                                                                        
			                                                                        
//			                                                                        //To check if no item selected
//			                                                                       
			                                                                        if (validator.nullValidator(ugpgComboBox.getRawValue())) {
			                                                                            try {
			                                                                            	ugpgComboBox.markInvalid(
			                                                                                    "Choose UG/PG");
			                                                                            } catch (Exception e1) {
			                                                                                flag = false;
			                                                                            }
			
			                                                                            flag = false;
			                                                                        }
			                                                                        if(flag==true){
			                                      object.setProgram_code(record.getAsString("programCode"));
			                                      object.setProgram_name(progName.getValueAsString());
			                                      object.setNumber_of_attempt_allowed(attemptField.getValueAsString());
			                                      object.setNo_of_terms(semesterText.getValueAsString());
			                                      object.setMax_number_of_fail_subjects(maxFailCourseField.getValueAsString());
			                                      object.setUGorPG(ugpgComboBox.getValue());
			                                      object.setTotal_credits(creditText.getValueAsString()) ;
			                                     
			                                                                        	connectService.methodUpdateProgBasicDetails(userid, object, new AsyncCallback<String>(){

																							public void onFailure(
																									Throwable arg0) {
																						MessageBox.alert(msgs.failure(),arg0.getMessage());
																								
																							}
																							public void onSuccess(
																									String arg0) {
																							MessageBox.alert(msgs.success(),msgs.alert_oneditsuccess(),new AlertCallback(){

																								@Override
																								public void execute() {
																									okButton.fireEvent("click");
																									window.close();
																									cbSelectionModel.clearSelections();
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
			                                                                msgs.error(),
			                                                                msgs.select_recordsdeletion());
			                                                    } else if (records.length > 1) {
			                                                        MessageBox.alert(
			                                                        		msgs.error(),
			                                                                msgs.onlyOneDelete());
			                                                    }else {
			                                                        for (int i = 0;
			                                                                    i < records.length;
			                                                                    i++) {
			                                                            Record record = records[i];
			                                                            final String program_id =
			                                                                    record.getAsString(
			                                                                        "program_id");
			                                                            MessageBox.show(
			                                                                    new MessageBoxConfig() {
			
			                                                                    {
			                                                                        setTitle(
			                                                                               msgs.confirm());
			                                                                        setMsg(msgs.alert_ondelete());
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
			                                                                                        connectService.methodDeleteProg(
			                                                                                                program_id,
			                                                                                                new AsyncCallback<String>() {
			                                                                                                public void onFailure(Throwable arg0) {
			                                                                                                    MessageBox.alert(
			                                                                                                            msgs.failure(),
			                                                                                                            arg0.getMessage());
			                                                                                                }
			
			                                                                                                public void onSuccess(String arg0) {
			                                                                                                    MessageBox.alert(
			                                                                                                            msgs.alert(),
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
						 
						 
						 
						 
						 
			                                        gridVPanel.clear();
			                                         gridVPanel.add(grid);
						 
					}
    				
    			}); 
    			
					 
 		        }else if(method.equalsIgnoreCase("duration"))
 		        {

   				 if(codeSuggestBox.getText().equals("")){
      					 criteria="id";
      					 if(progCbox.getValue()==null){
       					 object.setProgram_id("%");
       				 }else{
       				 object.setProgram_id(progCbox.getValue());
       				  }
      					 }else{
      						 criteria="code"; 
      						 object.setProgram_code(codeSuggestBox.getText());
      					 }
   				
   				 connectService.methodProgDurationDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

					public void onFailure(Throwable arg0) {
						MessageBox.alert(msgs.failure(),arg0.getMessage());
						
					}

					public void onSuccess(CM_progMasterInfoGetter[] arg0) {
//						VerticalPanel p1Panel=new VerticalPanel();			 
						 
						 if (arg0.length == 0) {
						 grid.setTitle(msgs.error_record());
						  } else {
						    grid.setTitle(cons.heading_programdetails());
						  }
						 
						 final RecordDef rDef = new RecordDef(new FieldDef[] {
	                            new StringFieldDef("programName"),
	                            new StringFieldDef("programCode"),
	                            new StringFieldDef("minduration"),
	                            new StringFieldDef("maxduration"),
	                            new StringFieldDef("startdate"),
	                            new StringFieldDef("programid"),
	                            new StringFieldDef("actualstartdate"),
	                        });
							 
						 object1 = new Object[arg0.length][7];
						 
						                                         String str = null;
						
						                                         try {
						                                             for (int i = 0; i < arg0.length;
						                                                     i++) {
						                                            	
						                                                 for (int k = 0; k < 7; k++) {
						                                                     if (k == 0) {
						                                                         str = arg0[i].getProgram_name();
						                                                     } else if (k == 1) {
						                                                    	 str = arg0[i].getProgram_code();
						                                                     } else if (k == 2) {
						                                                    	 str = arg0[i].getMinimun_duration();
						                                                     } else if (k == 3) {
						                                                    	 str = arg0[i].getMaximum_duration();
						                                                     } else if (k == 4) {
						                                                    	 str = arg0[i].getStartdate();
						                                                    	 int a=Integer.parseInt(str.substring(0,2));
						                                                    	 
						                                                    	 switch (a)
						                                                    	 {
						                                                    	 case 1 :{
						                                                    		 str="Jan "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 2 :{
						                                                    		 str="feb "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 3 :{
						                                                    		 str="Mar "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 4:{
						                                                    		
						                                                    		 str="Apr "+str.substring(2);
						                                                    		 
						                                                    		 break;
						                                                    	 }
						                                                    	 case 5:{
						                                                    		
						                                                    		 str="May "+str.substring(2);
						                                                    		 
						                                                    		 break;
						                                                    	 }
						                                                    	 case 6:{
						                                                    		 str="Jun "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 7:{
						                                                    		 str="Jul "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 8:{
						                                                    		 str="Aug "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 9:{
						                                                    		 str="Sep "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 10:{
						                                                    		 str="Oct "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 11:{
						                                                    		 str="Nov "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 12:{
						                                                    		 ;
						                                                    		 str="Dec "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 
						                                                    	 }
						                                                    	 
						                                                    	 
						                                                     } else if (k == 5) {
						                                                    	 str = arg0[i].getProgram_id();
						                                                     }else if (k == 6) {
						                                                    	 str = arg0[i].getStartdate();
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
						                                         
						                                           GroupingStore store = new GroupingStore();  
						                                                  store.setReader(reader);  
						                                                  store.setDataProxy(proxy);  
						                                                  store.setSortInfo(new SortState("programCode", SortDir.ASC));  
						                                                  store.setGroupField("programCode");  
						                                                  store.load();
						                                         
						                                                    GroupingView gridView = new GroupingView();  
						                                                           gridView.setForceFit(true);  
						                                                           gridView.setGroupTextTpl("{text} ({[values.rs.length]} {[values.rs.length > 1 ? \"Items\" : \"Item\"]})");  
						                                                     
						                                                           grid.setView(gridView);  
//						                                         Store store = new Store(proxy, reader);
						 
						                                         store.load();
						                                         grid.setStore(store);
				
						 try{
						                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
						                                                 new CheckboxColumnConfig(cbSelectionModel),
						                                                 new ColumnConfig(cons.label_programName(),
						                                                     "programName", 200, true, null,
						                                                     "programName"),
						                                                 new ColumnConfig(cons.label_programCode(),
						                                                     "programCode", 200, true, null,
						                                                     "programCode"),
						                                                 new ColumnConfig(cons.minDuration(),
						                                                     "minduration", 100, true,
						                                                     null, "minduration"),
						                                                 new ColumnConfig(cons.maxDuration(),
						                                                     "maxduration", 100, true, null,
						                                                     "maxduration"),
						                                                 new ColumnConfig(cons.startDate(),
						                                                     "startdate", 100, true, null,
						                                                     "startdate")
						                                             };
						 
						                                         final ColumnModel columnModel = new ColumnModel(columns);
						                                         grid.setColumnModel(columnModel);
						 }catch (Exception e) {
								System.out.println(e+ "  hwgehghweg");
							}
						                                         grid.setFrame(true);
						                                         grid.setStripeRows(true);
						 
						                                         grid.setSelectionModel(cbSelectionModel);
						                                         grid.setAutoWidth(true);
						                                         grid.setWidth(750);
						                                         grid.setHeight(280);
						 
						                                         Toolbar topToolBar = new Toolbar();
						                                         topToolBar.addFill();
						 
						                                     
						 
						 ToolbarButton editButton = new ToolbarButton(cons.edit(),
				                                                new ButtonListenerAdapter() {
			                                                    public void onClick(
			                                                        Button editButton,
			                                                        EventObject e) {
			                                                        //						    		    	   Record rec=cbSelectionModel.getSelected();
			                                                        //						    		    	 System.out.println(rec.getAsString("Name"));
			                                                    	try{
			                                                        Record[] records = cbSelectionModel.getSelections();
			
			                                                       
			
			                                                        if (records.length < 1) {
			                                                            MessageBox.alert(msgs.error(),
			                                                                msgs.atleastOneRecord());
			                                                        } else if (records.length > 1) {
			                                                            MessageBox.alert(msgs.error(),
			                                                                 msgs.onlyOneRecord());
			                                                        } else if (records.length == 1) {
			                                                        	
			                                                            final Record record = records[0];
						//callinng method to populate month and days list
			                                                            
			                                                            
			                                                            FlexTable editprogTable = new FlexTable();
			                                                       
			                                                            TextField progName=new TextField();
			                                                        	Label tooltipLabel=new Label(cons.inMonths());
			                                                        	Label tooltipLabel1=new Label(cons.inMonths());
			                                                        	final NumberField minDurationField=new NumberField();
			                                                        	final NumberField maxDurationField=new NumberField();
			                                                        	
			                                                        	dates();
			                                                        	final ComboBox monthComboBox=new ComboBox();
			                                                        	monthComboBox.setStore(monthStore);
			                                                        	final ComboBox dayComboBox=new ComboBox();
			                                                        	dayComboBox.setStore(daysStore);                       
			                                                        	
			                                                      
			                                                    	HorizontalPanel dateHPanel=new HorizontalPanel();
			                                                    	
			                                             	         monthComboBox.setDisplayField("month");  
			                                             	         monthComboBox.setMode(ComboBox.LOCAL);  
			                                             	         monthComboBox.setTriggerAction(ComboBox.ALL);  
			                                             	         monthComboBox.setForceSelection(true);  
			                                             	         monthComboBox.setValueField("id");
			                                            
			                                             	         monthComboBox.setReadOnly(true);  
			                                                    	                	       
			                                            	   
//			                                            	        
			                                             	        dayComboBox.setDisplayField("day");  
			                                             	       dayComboBox.setValueField("did");  
			                                             	      dayComboBox.setMode(ComboBox.LOCAL);  
			                                             	     dayComboBox.setTriggerAction(ComboBox.ALL);  
			                                             	    dayComboBox.setLinked(true);  
			                                             	   dayComboBox.setForceSelection(true);  
			                                             	  dayComboBox.setReadOnly(true);  
			                                             	  
			                                                    	 
			                                             	 dayComboBox.setWidth(60);
			                                                        	monthComboBox.setWidth(60);
			                                                        	dateHPanel.add(monthComboBox);
			                                                        	dateHPanel.setSpacing(20);
			                                                        	dateHPanel.add(dayComboBox);
			                                                            monthComboBox.addListener(new ComboBoxListenerAdapter() {  
			                                                         	   
			                                               	             public void onSelect(ComboBox comboBox, Record record, int index) {
			                                               	            	dayComboBox.setStore(daysStore);                                      	    
			                                               	            	dayComboBox.setValue("");  
			                                               	                 daysStore.filter("id", comboBox.getValue().substring(2)); 
			                                               	                                         	                 
			                                               	             }  
			                                               	         });  
			                                               	   
			                                                            final RadioButton fixedRadio=new RadioButton("abc",cons.fixedDuration());
			                                                            final RadioButton floatRadio=new RadioButton("abc",cons.floatingDuration());
			                                                            final Label durationLabel=new Label("Duration ");
			                                                            
			                                                            
			                                                            maxDurationField.setAllowDecimals(false);
			                                                            minDurationField.setAllowDecimals(false);
			                                                            minDurationField.setAllowBlank(false);
			                                                            maxDurationField.setAllowBlank(false);
			                                                            
			                                                            progName.setValue(record.getAsString("programName"));
			                                                            			                                                            
			                                                            minDurationField.setValue(record.getAsString("minduration"));
			                                                            maxDurationField.setValue(record.getAsString("maxduration"));
			                                                            
			                                                            monthComboBox.setValue(record.getAsString("startdate").substring(0,3));
			                                                            
			                                                            
			                                                          
			                                                            dayComboBox.setValue(record.getAsString("startdate").substring(3));
			                                                        
			                                                            @SuppressWarnings("unused")
																		String duration=null;
			                                                            if(Integer.parseInt(minDurationField.getValueAsString())==Integer.parseInt(maxDurationField.getValueAsString())){
			                                                            	fixedRadio.setChecked(true);
			                                                            	duration="Fixed";
			                                                            }else
			                                                            {
			                                                            	floatRadio.setChecked(true);
			                                                            	duration="Floated";
			                                                            }
			                                                            
			                                                            
			                                                            
			                                                            editprogTable.clear();
			                                                            editprogTable.setWidget(3,0, new Label(cons.label_programCode()));
			                                                            editprogTable.setWidget(3,1, new Label(record.getAsString("programCode")));
			                                                            editprogTable.setWidget(4,0,new Label(cons.label_programName()));
			                                                            editprogTable.setWidget(4,1,new Label(record.getAsString("programName")));
			                                                            editprogTable.setWidget(5,0,durationLabel);
			                                                            editprogTable.setWidget(5,1,fixedRadio);
			                                                            editprogTable.setWidget(5,2,floatRadio);
			                                                            fixedRadio.setEnabled(false);
			                                                            floatRadio.setEnabled(false);
			                                                            editprogTable.setWidget(6,0,new Label(cons.minDuration()));
			                                                            editprogTable.setWidget(6,1,minDurationField);
			                                                            editprogTable.setWidget(6,2,tooltipLabel);
			                                                            editprogTable.setWidget(7,0,new Label(cons.maxDuration()));
			                                                            editprogTable.setWidget(7,1,maxDurationField);
			                                                            editprogTable.setWidget(7,2,tooltipLabel1);
			                                                            
			                                                            editprogTable.setWidget(8,0,new Label(cons.startDate()));
			                                                            editprogTable.setWidget(8,1,dateHPanel);
//			                                                            editprogTable.setWidget(8,1,new Label(duration));
			                                                        
			                                                            p1.clear();
			                                                            p1.add(editprogTable);
			
			                                                            Button windowUpdateButton =
			                                                                new Button(
			                                                                    cons.button_update());
			                                                            Button windowResetButton =
			                                                                new Button(
			                                                                    cons.resetButton());
			                                                            final Window window = new Window();
			                                                            window.setTitle(
			                                                                cons.heading_programdetails());
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
			                                                                    	
			                                                                    	
			                                                                    	
			                                                                    }
			                                                                });
			
			                                                            //Adding handler to update button of edit window 
			                                                            windowUpdateButton.addListener(new ButtonListenerAdapter() {
			                                                                    public void onClick(
			                                                                        Button button,
			                                                                        EventObject e) {
			                                                                        Boolean flag = true;
//			                                                            			
			                                                                        try {
			                                                                            minDurationField.validate();
			                                                                        } catch (Exception e1) {
			                                                                            flag = false;
			                                                                        }
			
			                                                                        try {
			                                                                            maxDurationField.validate();
			                                                                        } catch (Exception e1) {
			                                                                            flag = false;
			                                                                        }
			if(fixedRadio.isChecked()){
				if(Integer.parseInt(minDurationField.getValueAsString())!=Integer.parseInt(maxDurationField.getValueAsString())){
					minDurationField.markInvalid("Invalid");
					maxDurationField.markInvalid("invalid");
					flag=false;
				}
			}else{
				if(Integer.parseInt(minDurationField.getValueAsString())>Integer.parseInt(maxDurationField.getValueAsString())){
					minDurationField.markInvalid("Invalid");
					maxDurationField.markInvalid("invalid");
					flag=false;
				}
			}
			                                               
			                                                                        if(flag==true){
			                                                                        	
			                                                                        	CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
			                                                                        	object.setProgram_id(record.getAsString("programid"));
			                                                                        	object.setMinimun_duration(minDurationField.getValueAsString());
			                                                                        	object.setMaximum_duration(maxDurationField.getValueAsString());
			                                                                        	String startdate=null;
			                                                                        	if(monthComboBox.getValue().equalsIgnoreCase(record.getAsString("startdate").substring(0,3)))
			                                                                        	{
			                                                                        		startdate=record.getAsString("actualstartdate").substring(0,2)+dayComboBox.getRawValue();
			                                                                        	}else{
			                                                                        	startdate=monthComboBox.getValue().substring(0,2)+dayComboBox.getRawValue();
			                                                                        	}
			                                                                        	object.setStartdate(startdate);
			                                                                        	object.setOldstartdate(record.getAsString("actualstartdate"));
			                                                                        				                                                                        	
			                                                                        	
			                                                                        	System.out
																								.println("start date"+object.getStartdate());
			                                                                        	
			                                                                        	
			                                                                        	connectService.methodUpdateProgDurationDetails(userid, object,new AsyncCallback<String>(){

																							public void onFailure(
																									Throwable arg0) {
																								MessageBox.alert(msgs.failure(),arg0.getMessage());
																								
																							}

																							@Override
																							public void onSuccess(
																									String arg0) {
																								
																								MessageBox.alert(
                                                                                                        msgs.alert(),
                                                                                                        msgs.alert_oneditsuccess(),
                                                                                                        new AlertCallback() {
                                                                                                        public void execute() {
                                                                                                            okButton.fireEvent(
                                                                                                                    "click");
                                                                                                            cbSelectionModel.clearSelections();
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
			                                                cons.delete(),
			                                                new ButtonListenerAdapter() {
			                                                public void onClick(Button delButton,
			                                                        EventObject e) {
			                                                    Record[] records = cbSelectionModel.getSelections();
			
			                                                    if (records.length < 1) {
			                                                        MessageBox.alert(
			                                                                msgs.error(),
			                                                                msgs.select_recordsdeletion());
			                                                    } else if (records.length > 1) {
			                                                        MessageBox.alert(
			                                                                msgs.error(),
			                                                                msgs.onlyOneDelete());
			                                                    }else {
			                                                        for (int i = 0;
			                                                                    i < records.length;
			                                                                    i++) {
			                                                            Record record = records[i];
			                                                            final String program_id=record.getAsString("programid");
			                                                            final String startdate=record.getAsString("actualstartdate");
			                                                            
			                                                            
			                                                            MessageBox.show(
			                                                                    new MessageBoxConfig() {
			
			                                                                    {
			                                                                        setTitle(
			                                                                                msgs.confirm());
			                                                                        setMsg(msgs.alert_ondelete());
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
			                                                                                    	System.out
																											.println();
			                                                                                        connectService.methodProgDurationDelete(
			                                                                                                program_id,startdate,
			                                                                                                new AsyncCallback<String>() {
			                                                                                                public void onFailure(Throwable arg0) {
			                                                                                                    MessageBox.alert(
			                                                                                                            msgs.failure(),
			                                                                                                            arg0.getMessage());
			                                                                                                }
			
			                                                                                                public void onSuccess(String arg0) {
			                                                                                                    MessageBox.alert(
			                                                                                                            msgs.alert(),
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
						 
			                                        gridVPanel.clear();
			                                         gridVPanel.add(grid);
						 
					}
   				
   			}); 
   			
					 
		        
 		        	
 		        }
 		        else if(method.equalsIgnoreCase("branch"))
 		        {

 	   				 if(codeSuggestBox.getText().equals("")){
 	      					 criteria="id";
 	      					 if(progCbox.getValue()==null){
 	       					 object.setProgram_id("%");
 	       				 }else{
 	       				 object.setProgram_id(progCbox.getValue());
 	       				  }
 	      					 }else{
 	      						 criteria="code"; 
 	      						 object.setProgram_code(codeSuggestBox.getText());
 	      					 }
 	   				
 	   				 connectService.methodProgBranchDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

 						public void onFailure(Throwable arg0) {
 							MessageBox.alert(msgs.failure(),arg0.getMessage());
 							
 						}

 						public void onSuccess(CM_progMasterInfoGetter[] arg0) {
// 							VerticalPanel p1Panel=new VerticalPanel();			 
 							 /*
 							  * take program_id also for editing
 							  */
 							 if (arg0.length == 0) {
 							 grid.setTitle(msgs.error_record());
 							  } else {
 							    grid.setTitle(cons.programBranchDetails());
 							  }
 							 
 							 final RecordDef rDef = new RecordDef(new FieldDef[] {
 		                            new StringFieldDef("programName"),
 		                            new StringFieldDef("programCode"),
 		                            new StringFieldDef("branchname"),
 		                            new StringFieldDef("branchcode"),
 		                           new StringFieldDef("programid"), 
 		                           
 		                        });
 								 
 							 object1 = new Object[arg0.length][5];
 							 
 							                                         String str = null;
 							
 							                                         try {
 							                                             for (int i = 0; i < arg0.length;
 							                                                     i++) {
 							                                            	
 							                                                 for (int k = 0; k < 5; k++) {
 							                                                     if (k == 0) {
 							                                                         str = arg0[i].getProgram_name();
 							                                                     } else if (k == 1) {
 							                                                    	 str = arg0[i].getProgram_code();
 							                                                     } else if (k == 2) {
 							                                                    	 str = arg0[i].getBranchname();
 							                                                     } else if (k == 3) {
 							                                                    	 str = arg0[i].getBranchcode();
 							                                                     }else if (k == 4) {
 							                                                    	 str = arg0[i].getProgram_id();
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
 							                                         
 							                                           GroupingStore store = new GroupingStore();  
 							                                                  store.setReader(reader);  
 							                                                  store.setDataProxy(proxy);  
 							                                                  store.setSortInfo(new SortState("programCode", SortDir.ASC));  
 							                                                  store.setGroupField("programCode");  
 							                                                  store.load();
 							                                         
 							                                                    GroupingView gridView = new GroupingView();  
 							                                                           gridView.setForceFit(true);  
 							                                                           gridView.setGroupTextTpl("{text} ({[values.rs.length]} {[values.rs.length > 1 ? \"Items\" : \"Item\"]})");  
 							                                                     
 							                                                           grid.setView(gridView);  
// 							                                         Store store = new Store(proxy, reader);
 							 
 							                                         store.load();
 							                                         grid.setStore(store);
 					
 							 try{
 							                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
 							                                                 new CheckboxColumnConfig(cbSelectionModel),
 							                                                 new ColumnConfig(cons.label_programName(),
 							                                                     "programName", 200, true, null,
 							                                                     "programName"),
 							                                                 new ColumnConfig(cons.label_programCode(),
 							                                                     "programCode", 200, true, null,
 							                                                     "programCode"),
 							                                                 new ColumnConfig(cons.label_branchname(),
 							                                                     "branchname", 200, true,
 							                                                     null, "branchname")
 							                                             };
 							 
 							                                         final ColumnModel columnModel = new ColumnModel(columns);
 							                                         grid.setColumnModel(columnModel);
 							 }catch (Exception e) {
 									System.out.println(e+ "  hwgehghweg");
 								}
 							                                         grid.setFrame(true);
 							                                         grid.setStripeRows(true);
 							 
 							                                         grid.setSelectionModel(cbSelectionModel);
 							                                         grid.setAutoWidth(true);
 							                                         grid.setWidth(750);
 							                                         grid.setHeight(280);
 							 
 							                                         Toolbar topToolBar = new Toolbar();
 							                                         topToolBar.addFill();
 							 
 							                                     
 							 

 				
 				                                        topToolBar.addButton(new ToolbarButton(
 				                                                cons.delete(),
 				                                                new ButtonListenerAdapter() {
 				                                                public void onClick(Button delButton,
 				                                                        EventObject e) {
 				                                                    Record[] records = cbSelectionModel.getSelections();
 				
 				                                                    if (records.length < 1) {
 				                                                        MessageBox.alert(
 				                                                                msgs.error(),
 				                                                                msgs.select_recordsdeletion());
 				                                                    } else if (records.length > 1) {
 				                                                        MessageBox.alert(
 				                                                                msgs.error(),
 				                                                                msgs.onlyOneDelete());
 				                                                    }else {
 				                                                        for (int i = 0;
 				                                                                    i < records.length;
 				                                                                    i++) {
 				                                                            Record record = records[i];
 				                                                           final String program_id=record.getAsString("programid");
				                                                            final String branchcode=record.getAsString("branchcode");
 				                                                            MessageBox.show(
 				                                                                    new MessageBoxConfig() {
 				
 				                                                                    {
 				                                                                        setTitle(
 				                                                                                msgs.confirm());
 				                                                                        setMsg(msgs.alert_ondelete());
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
// 				                                                                                        
				                                                                                    	
				                                                                                        connectService.methodBranchDelete(
				                                                                                                program_id,branchcode,
				                                                                                                new AsyncCallback<String>() {
				                                                                                                public void onFailure(Throwable arg0) {
				                                                                                                    MessageBox.alert(
				                                                                                                            msgs.failure(),
				                                                                                                            arg0.getMessage());
				                                                                                                }
				
				                                                                                                public void onSuccess(String arg0) {
				                                                                                                    MessageBox.alert(
				                                                                                                            msgs.alert(),
				                                                                                                            "Branch Successfully deleted",
				                                                                                                            new AlertCallback() {
				                                                                                                            public void execute() {
				                                                                                                                okButton.fireEvent(
				                                                                                                                        "click");
				                                                                                    							cbSelectionModel.clearSelections();
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
 							 
 				                                        gridVPanel.clear();
 				                                         gridVPanel.add(grid);
 							 
 						}
 	   				
 	   			}); 
 	   			
 						 
 			        
 	 		        	
 	 		        }
 		        else if(method.equalsIgnoreCase("specialization"))
 		        {
 		        	 		        	

	   				 if(codeSuggestBox.getText().equals("")){
	      					 criteria="id";
	      					 if(progCbox.getValue()==null){
	       					 object.setProgram_id("%");
	       				 }else{
	       				 object.setProgram_id(progCbox.getValue());
	       				  }
	      					 }else{
	      						 criteria="code"; 
	      						 object.setProgram_code(codeSuggestBox.getText());
	      					 }
	   				
	   				 connectService.methodProgSpecDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

						public void onFailure(Throwable arg0) {
							MessageBox.alert("Failure in getting program Specialization details",arg0.getMessage());
							
						}

						public void onSuccess(CM_progMasterInfoGetter[] arg0) {
//							VerticalPanel p1Panel=new VerticalPanel();			 
							 /*
							  * take program_id also for editing
							  */
							 if (arg0.length == 0) {
							 grid.setTitle(msgs.error_record());
							  } else {
							    grid.setTitle("Program Specialization Details");
							  }
							 
							 final RecordDef rDef = new RecordDef(new FieldDef[] {
		                            new StringFieldDef("programName"),
		                            new StringFieldDef("programCode"),
		                            new StringFieldDef("branchname"),
		                            new StringFieldDef("specname"),
		                            new StringFieldDef("programid"),
		                            new StringFieldDef("specialization_code")
		                           
		                        });
								 
							 object1 = new Object[arg0.length][6];
							 
							                                         String str = null;
							
							                                         try {
							                                             for (int i = 0; i < arg0.length;
							                                                     i++) {
							                                            	
							                                                 for (int k = 0; k < 6; k++) {
							                                                     if (k == 0) {
							                                                         str = arg0[i].getProgram_name();
							                                                     } else if (k == 1) {
							                                                    	 str = arg0[i].getProgram_code();
							                                                     } else if (k == 3) {
							                                                    	 str = arg0[i].getSpecname();
							                                                     }else if (k == 2) {
							                                                    	 str = arg0[i].getBranchname();
							                                                     }else if (k == 4) {
							                                                    	 str = arg0[i].getProgram_id();
							                                                     }else if (k == 5) {
							                                                    	 str = arg0[i].getSpecialization_code();
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
							                                         
							                                           GroupingStore store = new GroupingStore();  
							                                                  store.setReader(reader);  
							                                                  store.setDataProxy(proxy);  
							                                                  store.setSortInfo(new SortState("programCode", SortDir.ASC));  
							                                                  store.setGroupField("programCode");  
							                                                  store.load();
							                                         
							                                                    GroupingView gridView = new GroupingView();  
							                                                           gridView.setForceFit(true);  
							                                                           gridView.setGroupTextTpl("{text} ({[values.rs.length]} {[values.rs.length > 1 ? \"Items\" : \"Item\"]})");  
							                                                     
							                                                           grid.setView(gridView);  
//							                                         Store store = new Store(proxy, reader);
							 
							                                         store.load();
							                                         grid.setStore(store);
					
							 try{
							                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
							                                                 new CheckboxColumnConfig(cbSelectionModel),
							                                                 new ColumnConfig("programName",
							                                                     "programName", 200, true, null,
							                                                     "programName"),
							                                                 new ColumnConfig("programCode",
							                                                     "programCode", 200, true, null,
							                                                     "programCode"),
							                                                 new ColumnConfig("Branch Name",
		 							                                              "branchname", 200, true,
		 							                                               null, "branchname"),
							                                                 new ColumnConfig("Specialization Name",
							                                                     "specname", 200, true, null,
							                                                     "specname")
							                                             };
							 
							                                         final ColumnModel columnModel = new ColumnModel(columns);
							                                         grid.setColumnModel(columnModel);
							 }catch (Exception e) {
									System.out.println(e+ "  hwgehghweg");
								}
							                                         grid.setFrame(true);
							                                         grid.setStripeRows(true);
							 
							                                         grid.setSelectionModel(cbSelectionModel);
							                                         grid.setAutoWidth(true);
							                                         grid.setWidth(750);
							                                         grid.setHeight(280);
							 
							                                         Toolbar topToolBar = new Toolbar();
							                                         topToolBar.addFill();
							 
							                                     
							 

				
				                                        topToolBar.addButton(new ToolbarButton(
				                                                cons.delete(),
				                                                new ButtonListenerAdapter() {
				                                                public void onClick(Button delButton,
				                                                        EventObject e) {
				                                                    Record[] records = cbSelectionModel.getSelections();
				
				                                                    if (records.length < 1) {
				                                                        MessageBox.alert(
				                                                                msgs.error(),
				                                                                msgs.select_recordsdeletion());
				                                                    } else if (records.length > 1) {
				                                                        MessageBox.alert(
				                                                                msgs.error(),
				                                                                msgs.onlyOneDelete());
				                                                    }else {
				                                                        for (int i = 0;
				                                                                    i < records.length;
				                                                                    i++) {
				                                                            Record record = records[i];
				                                                            final String program_id=record.getAsString("programid");
				                                                            final String specialization_code=record.getAsString("specialization_code");
				                                                            MessageBox.show(
				                                                                    new MessageBoxConfig() {
				
				                                                                    {
				                                                                        setTitle(
				                                                                                msgs.confirm());
				                                                                        setMsg(msgs.alert_ondelete());
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
				                                                                                    	
				                                                                                        connectService.methodSpecDelete(
				                                                                                                program_id,specialization_code,
				                                                                                                new AsyncCallback<String>() {
				                                                                                                public void onFailure(Throwable arg0) {
				                                                                                                    MessageBox.alert(
				                                                                                                            msgs.failure(),
				                                                                                                            arg0.getMessage());
				                                                                                                }
				
				                                                                                                public void onSuccess(String arg0) {
				                                                                                                    MessageBox.alert(
				                                                                                                            msgs.alert(),
				                                                                                                            "Specialization Successfully deleted",
				                                                                                                            new AlertCallback() {
				                                                                                                            public void execute() {
				                                                                                                                okButton.fireEvent(
				                                                                                                                        "click");
				                                                                                    							cbSelectionModel.clearSelections();
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
							 
				                                        gridVPanel.clear();
				                                         gridVPanel.add(grid);
							 
						}
	   				
	   			}); 
	   			
						 
			        
	 		        	
	 		        
					 
		        
 		        }
	   				 else if(method.equalsIgnoreCase("reservation"))
 		        {

	   				 if(codeSuggestBox.getText().equals("")){
	      					 criteria="id";
	      					 if(progCbox.getValue()==null){
	       					 object.setProgram_id("%");
	       				 }else{
	       				 object.setProgram_id(progCbox.getValue());
	       				  }
	      					 }else{
	      						 criteria="code"; 
	      						 object.setProgram_code(codeSuggestBox.getText());
	      					 }
	   				
	   				 connectService.methodProgReserveDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

						public void onFailure(Throwable arg0) {
							MessageBox.alert("Failure in getting program Branch details",arg0.getMessage());
							
						}

						public void onSuccess(CM_progMasterInfoGetter[] arg0) {
//							VerticalPanel p1Panel=new VerticalPanel();			 
							
							 if (arg0.length == 0) {
							 grid.setTitle(msgs.error_record());
							  } else {
							    grid.setTitle("Program Branch Details");
							  }
							 
							 final RecordDef rDef = new RecordDef(new FieldDef[] {
		                            new StringFieldDef("programName"),
		                            new StringFieldDef("programCode"),
		                            new StringFieldDef("category"),
		                            new StringFieldDef("percentage"),
		                            new StringFieldDef("category_code"),
		                            new StringFieldDef("programid"),
		                        });
								 
							 object1 = new Object[arg0.length][6];
							 
							                                         String str = null;
							
							                                         try {
							                                             for (int i = 0; i < arg0.length;
							                                                     i++) {
							                                            	
							                                                 for (int k = 0; k < 6; k++) {
							                                                     if (k == 0) {
							                                                         str = arg0[i].getProgram_name();
							                                                     } else if (k == 1) {
							                                                    	 str = arg0[i].getProgram_code();
							                                                     } else if (k == 2) {
							                                                    	 str = arg0[i].getCategory();
							                                                     } else if (k == 3) {
							                                                    	 str = arg0[i].getPercentage_seats();
							                                                     } else if (k == 4) {
							                                                    	 str = arg0[i].getCategory_code();
							                                                     }else if (k == 5) {
							                                                    	 str = arg0[i].getProgram_id();
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
							                                         
							                                         
							                                           GroupingStore store = new GroupingStore();  
							                                                  store.setReader(reader);  
							                                                  store.setDataProxy(proxy);  
							                                                  store.setSortInfo(new SortState("programCode", SortDir.ASC));  
							                                                  store.setGroupField("programCode");  
							                                                  store.load();
							                                         
							                                                    GroupingView gridView = new GroupingView();  
							                                                           gridView.setForceFit(true);  
							                                                           gridView.setGroupTextTpl("{text} ({[values.rs.length]} {[values.rs.length > 1 ? \"Items\" : \"Item\"]})");  
							                                                     
							                                                           grid.setView(gridView);  
//							                                        
							 
							                                         store.load();
							                                         grid.setStore(store);
					
							 try{
							                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
							                                                 new CheckboxColumnConfig(cbSelectionModel),
							                                                 new ColumnConfig("programName",
							                                                     "programName", 200, true, null,
							                                                     "programName"),
							                                                 new ColumnConfig("programCode",
							                                                     "programCode", 200, true, null,
							                                                     "programCode"),
							                                                 new ColumnConfig("Category",
							                                                     "category", 200, true,
							                                                     null, "category"),
							                                                 new ColumnConfig("Percentage of Seats",
							                                                     "percentage", 200, true, null,
							                                                     "percentage")
							                                             };
							 
							                                         final ColumnModel columnModel = new ColumnModel(columns);
							                                         grid.setColumnModel(columnModel);
							 }catch (Exception e) {
									System.out.println(e+ "  hwgehghweg");
								}
							                                         grid.setFrame(true);
							                                         grid.setStripeRows(true);
							 
							                                         grid.setSelectionModel(cbSelectionModel);
							                                         grid.setAutoWidth(true);
							                                         grid.setWidth(750);
							                                         grid.setHeight(280);
							 
							                                         Toolbar topToolBar = new Toolbar();
							                                         topToolBar.addFill();
							 
							                                     
							 
							 ToolbarButton editButton = new ToolbarButton("Edit",
					                                                new ButtonListenerAdapter() {
				                                                    public void onClick(
				                                                        final Button editButton,
				                                                        EventObject e) {
				                                                        //						    		    	   Record rec=cbSelectionModel.getSelected();
				                                                        //						    		    	 System.out.println(rec.getAsString("Name"));
				                                                    	try{
				                                                        Record[] records = cbSelectionModel.getSelections();
				
				                                                        if (records.length < 1) {
				                                                            MessageBox.alert("Note",
				                                                                msgs.atleastOneRecord());
				                                                        } else if (records.length > 1) {
				                                                            MessageBox.alert("Note",
				                                                                 msgs.onlyOneRecord());
				                                                        } else if (records.length == 1) {
				                                                            final Record record = records[0];
							
				                                                            FlexTable editprogTable =
				                                                                new FlexTable();
				                                                           final TextField seats=new TextField();
				                                                              seats.setAllowBlank(false); 
				                                                              
				                                                            seats.setValue(record.getAsString("percentage"));
				                                                            
				                                                            editprogTable.clear();
				                                                            editprogTable.setWidget(3,
				                                                                0, new Label("Program Code "));
				                                                            editprogTable.setWidget(3,
					                                                                1, new Label(record.getAsString("programCode")));
				                                                            editprogTable.setWidget(4, 0,new Label("Program Name "));
				                                                            editprogTable.setWidget(4, 1,new Label(record.getAsString("ProgramName")));
				                                                            editprogTable.setWidget(5, 0,new Label("Category "));
				                                                            editprogTable.setWidget(5, 1,new Label(record.getAsString("category")));
				                                                            editprogTable.setWidget(6, 0,new Label("Percentage of seats "));
				                                                            editprogTable.setWidget(6, 1,seats);
				                                                        
				                                                            			
				                                                       
				                                                           
				                                                            /*
				                                                             * To get id of selected entity
				                                                             * record.getAsString("Entity id")
				                                                             */
				                                                            p1.clear();
				                                                            p1.add(editprogTable);
				
				                                                            Button windowUpdateButton =
				                                                                new Button(
				                                                                    cons.button_update());
				                                                            Button windowResetButton =
				                                                                new Button(
				                                                                    cons.resetButton());
				                                                            final Window window = new Window();
				                                                            window.setTitle(
				                                                                cons.heading_programdetails());
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
				                                                                    	
				                                                                    	seats.setValue(record.getAsString("percentage"));
				                                                                    	
				                                                                    }
				                                                                });
				
				                                                            //Adding handler to update button of edit window 
				                                                            windowUpdateButton.addListener(new ButtonListenerAdapter() {
				                                                                    public void onClick(
				                                                                        Button button,
				                                                                        EventObject e) {
				                                                                        Boolean flag = true;
//				                                                            			
				                                                                        try {
				                                                                            seats.validate();
				                                                                        } catch (Exception e1) {
				                                                                            flag = false;
				                                                                        }
//				
//				                                                                        
				                                                                        if(flag==true){
				                                                                        	CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
				                                                                        	
				                                                                        	object.setProgram_id(record.getAsString("programid"));
				                                                                        	object.setCategory_code(record.getAsString("category_code"));
				                                                                        	object.setPercentage_seats(seats.getValueAsString());
				                                                                        	
				                                                                        	connectService.methodUpdateProgReserveDetails(userid, object, new AsyncCallback<String>(){

				                                                    							public void onFailure(
																										Throwable arg0) {
																									MessageBox.alert("failure in updating reservation",arg0.getMessage());
																									
																								}

																								@Override
																								public void onSuccess(
																										String arg0) {
																									
																									MessageBox.alert(
	                                                                                                        msgs.alert(),
	                                                                                                        "Program seat reservation details successfully updated",
	                                                                                                        new AlertCallback() {
	                                                                                                        public void execute() {
	                                                                                                            okButton.fireEvent(
	                                                                                                                    "click");
	                                                                                                            cbSelectionModel.clearSelections();
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
				                                                cons.delete(),
				                                                new ButtonListenerAdapter() {
				                                                public void onClick(Button delButton,
				                                                        EventObject e) {
				                                                    Record[] records = cbSelectionModel.getSelections();
				
				                                                    if (records.length < 1) {
				                                                        MessageBox.alert(
				                                                                msgs.error(),
				                                                                msgs.select_recordsdeletion());
				                                                    } else if (records.length > 1) {
				                                                        MessageBox.alert(
				                                                                msgs.error(),
				                                                                msgs.onlyOneDelete());
				                                                    }else {
				                                                        for (int i = 0;
				                                                                    i < records.length;
				                                                                    i++) {
				                                                            Record record = records[i];
				                                                          final  String program_id=record.getAsString("programid");
				                                                           final String category_code=record.getAsString("category_code");
				                                                           
				                                                            MessageBox.show(
				                                                                    new MessageBoxConfig() {
				
				                                                                    {
				                                                                        setTitle(
				                                                                                msgs.confirm());
				                                                                        setMsg(msgs.alert_ondelete());
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
				                                                                                        connectService.methodSeatReservationDelete(
				                                                                                                program_id,category_code,
				                                                                                                new AsyncCallback<String>() {
				                                                                                                public void onFailure(Throwable arg0) {
				                                                                                                    MessageBox.alert(
				                                                                                                            msgs.failure(),
				                                                                                                            arg0.getMessage());
				                                                                                                }
				
				                                                                                                public void onSuccess(String arg0) {
				                                                                                                    MessageBox.alert(
				                                                                                                            msgs.alert(),
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
							 
				                                        gridVPanel.clear();
				                                         gridVPanel.add(grid);
							 
						}
	   				
	   			}); 
	   			
						 
			        
	 		        	
	 		        	        }
				 
    			 
    			
    			 
    		 }
    	});
    	
    	manageProgVpanel.clear();
//    	methodShowSession();
    	manageProgVpanel.add(heading);
    	manageProgVpanel.setSpacing(10);
    	manageProgVpanel.add(nameText);
    	manageProgVpanel.add(sessionText);
    	manageProgVpanel.setSpacing(20);
    	manageProgVpanel.add(upperFlexTable);
    	manageProgVpanel.add(searchFormPanel);
    	manageProgVpanel.add(okButton);
   	
    	containerVerticalPanel.clear();
    	containerVerticalPanel.add(manageProgVpanel);
    	containerVerticalPanel.add(gridVPanel);
    	
    	
    	 ProgrammeHorizontalPanel.clear();
    	 ProgrammeHorizontalPanel.setSpacing(20);
         ProgrammeHorizontalPanel.add(containerVerticalPanel);
          ProgrammeHorizontalPanel1.clear();
         ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
    	
  
  
    }
    
    
    
    
    
    public void dates()
    {
    	 // months store  
	         months = new Object[][]{  
	                 new Object[]{"01","01odd", "Jan"},  
	                 new Object[]{"02","02feb", "Feb"},  
	                 new Object[]{"03","03odd", "Mar"},  
	                 new Object[]{"04","04even", "Apr"},  
	                 new Object[]{"05","05odd", "May"}, 
	                 new Object[]{"06","06even", "Jun"}, 
	                 new Object[]{"07","07odd", "Jul"}, 
	                 new Object[]{"08","08odd", "Aug"}, 
	                 new Object[]{"09","09even", "Sep"}, 
	                 new Object[]{"10","10odd", "Oct"}, 
	                 new Object[]{"11","11even", "Nov"}, 
	                 new Object[]{"12","12odd", "Dec"} 
	         }; 
	         
	         monthStore = new SimpleStore(new String[]{"mid","id", "month"}, months);  
	         monthStore.load();  
		 
	    //days store  
        days = new Object[][]{  
       		 new Object[]{new Integer(1), "even", "01"},
                new Object[]{new Integer(2), "even", "02"},  
                new Object[]{new Integer(3), "even", "03"},  
                new Object[]{new Integer(4), "even", "04"},  
                new Object[]{new Integer(5), "even", "05"},  
                new Object[]{new Integer(6), "even", "06"},  
                new Object[]{new Integer(7), "even", "07"},  
                new Object[]{new Integer(8), "even", "08"},  
                new Object[]{new Integer(9), "even", "09"},  
                new Object[]{new Integer(10), "even", "10"},  
                new Object[]{new Integer(11), "even", "11"},  
                new Object[]{new Integer(12), "even", "12"},  
                new Object[]{new Integer(13), "even", "13"},  
                new Object[]{new Integer(14), "even", "14"},  
                new Object[]{new Integer(15), "even", "15"},  
                new Object[]{new Integer(16), "even", "16"},  
                new Object[]{new Integer(17), "even", "17"},  
                new Object[]{new Integer(18), "even", "18"},  
                new Object[]{new Integer(19), "even", "19"},  
                new Object[]{new Integer(20), "even", "20"}, 
                new Object[]{new Integer(21), "even", "21"},  
                new Object[]{new Integer(22), "even", "22"},  
                new Object[]{new Integer(23), "even", "23"},  
                new Object[]{new Integer(24), "even", "24"},  
                new Object[]{new Integer(25), "even", "25"},                             	                 
                new Object[]{new Integer(26), "even", "26"},  
                new Object[]{new Integer(27), "even", "27"},  
                new Object[]{new Integer(28), "even", "28"},  
                new Object[]{new Integer(29), "even", "29"},  
                new Object[]{new Integer(30), "even", "30"},  
                new Object[]{new Integer(31), "odd", "01"},
                new Object[]{new Integer(32), "odd", "02"},  
                new Object[]{new Integer(33), "odd", "03"},  
                new Object[]{new Integer(34), "odd", "04"},  
                new Object[]{new Integer(35), "odd", "05"},  
                new Object[]{new Integer(36), "odd", "06"},  
                new Object[]{new Integer(37), "odd", "07"},  
                new Object[]{new Integer(38), "odd", "08"},  
                new Object[]{new Integer(39), "odd", "09"},  
                new Object[]{new Integer(40), "odd", "10"},  
                new Object[]{new Integer(41), "odd", "11"},  
                new Object[]{new Integer(42), "odd", "12"},  
                new Object[]{new Integer(43), "odd", "13"},  
                new Object[]{new Integer(44), "odd", "14"},  
                new Object[]{new Integer(45), "odd", "15"},  
                new Object[]{new Integer(46), "odd", "16"},  
                new Object[]{new Integer(47), "odd", "17"},  
                new Object[]{new Integer(48), "odd", "18"},  
                new Object[]{new Integer(49), "odd", "19"},  
                new Object[]{new Integer(50), "odd", "20"}, 
                new Object[]{new Integer(51), "odd", "21"},  
                new Object[]{new Integer(52), "odd", "22"},  
                new Object[]{new Integer(53), "odd", "23"},  
                new Object[]{new Integer(54), "odd", "24"},  
                new Object[]{new Integer(55), "odd", "25"},                             	                 
                new Object[]{new Integer(56), "odd", "26"},  
                new Object[]{new Integer(57), "odd", "27"},  
                new Object[]{new Integer(58), "odd", "28"},  
                new Object[]{new Integer(59), "odd", "29"},  
                new Object[]{new Integer(60), "odd", "30"}, 
                new Object[]{new Integer(61), "odd", "31"},
                new Object[]{new Integer(62), "feb", "01"},
                new Object[]{new Integer(63), "feb", "02"},  
                new Object[]{new Integer(64), "feb", "03"},  
                new Object[]{new Integer(65), "feb", "04"},  
                new Object[]{new Integer(90), "feb", "05"},  
                new Object[]{new Integer(66), "feb", "06"},  
                new Object[]{new Integer(67), "feb", "07"},  
                new Object[]{new Integer(68), "feb", "08"},  
                new Object[]{new Integer(69), "feb", "09"},  
                new Object[]{new Integer(70), "feb", "10"},  
                new Object[]{new Integer(71), "feb", "11"},  
                new Object[]{new Integer(72), "feb", "12"},  
                new Object[]{new Integer(73), "feb", "13"},  
                new Object[]{new Integer(74), "feb", "14"},  
                new Object[]{new Integer(75), "feb", "15"},  
                new Object[]{new Integer(76), "feb", "16"},  
                new Object[]{new Integer(77), "feb", "17"},  
                new Object[]{new Integer(78), "feb", "18"},  
                new Object[]{new Integer(79), "feb", "19"},  
                new Object[]{new Integer(80), "feb", "20"}, 
                new Object[]{new Integer(81), "feb", "21"},  
                new Object[]{new Integer(82), "feb", "22"},  
                new Object[]{new Integer(83), "feb", "23"},  
                new Object[]{new Integer(84), "feb", "24"},  
                new Object[]{new Integer(85), "feb", "25"},                             	                 
                new Object[]{new Integer(86), "feb", "26"},  
                new Object[]{new Integer(87), "feb", "27"},  
                new Object[]{new Integer(88), "feb", "28"},  
                new Object[]{new Integer(89), "feb", "29"}
        };  

	    daysStore = new SimpleStore(new String[]{"did", "id", "day"},days);  
     daysStore.load(); 
    }
    
    
    
    
    
    
    /***************grid****************/
    
    
    
    
   public void specGrid(){
		  
	  
	   
	   connectService.methodSpecList(userid,new AsyncCallback<CM_progMasterInfoGetter[]>(){
			public void onFailure(Throwable arg0) {
			MessageBox.alert(msgs.failure(),arg0.getMessage());
				
			}

			public void onSuccess(CM_progMasterInfoGetter[] arg0) {
				
				RecordDef recordDef = new RecordDef(new FieldDef[] {
						 new StringFieldDef("spec_name"),
						 new StringFieldDef("specialization_code")
                   });
			
           String[][] object2 = new String[arg0.length][2];

           String[][] data = object2;

           for (int i = 0; i < arg0.length; i++) {
               object2[i][0] = arg0[i].getSpecname();
               object2[i][1] = arg0[i].getSpecialization_code();
           }

           MemoryProxy proxy = new MemoryProxy(data);

           ArrayReader reader = new ArrayReader(recordDef);
           
          specstore = new Store(proxy, reader);
          specstore.load();

        
			}
			
		}); 
   }
    
 
    
   
   
   
   /*
    * method for adding details in existing program
    */
    
    
    public void methodNewDetails(final String method){
    	
 	   final Panel p1 = new Panel();
        final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
        bd.setMargins(6, 6, 6, 6);

        
 	  final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
 	  final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle(); 
 	final VerticalPanel manageProgVpanel =new VerticalPanel();
 	VerticalPanel containerVerticalPanel=new VerticalPanel();
 	FlexTable upperFlexTable=new FlexTable();
 	final VerticalPanel gridVPanel=new VerticalPanel();
 	Label heading=new Label(cons.additionalProgram());
 	heading.setStyleName("heading");
 	final ComboBox progCbox=new ComboBox();
 	
 	progCbox.setForceSelection(true);
 	progCbox.setMinChars(1);
 	progCbox.setFieldLabel(cons.select()+cons.label_programName());
 	progCbox.setDisplayField("program_name");
 	progCbox.setValueField("program_id");
 	progCbox.setMode(ComboBox.LOCAL);
 	progCbox.setTriggerAction(ComboBox.ALL);
 	progCbox.setEmptyText(cons.select()+cons.label_programName());
 	progCbox.setLoadingText(cons.loading());
 	progCbox.setTypeAhead(true);
 	progCbox.setSelectOnFocus(true);
 	progCbox.setWidth(190);
 	progCbox.setHideTrigger(false);
 	
 	
 	upperFlexTable.clear();
 	upperFlexTable.setWidget(0,0,new Label(cons.select()+cons.label_programName()));
 	upperFlexTable.setWidget(0,10,progCbox);
 	
 	
 	
 	
 	connectService.methodprogList(userid,new AsyncCallback<CM_progMasterInfoGetter[]>(){

			public void onFailure(Throwable arg0) {
				MessageBox.alert(msgs.failure(),arg0.getMessage());
				
			}

			public void onSuccess(CM_progMasterInfoGetter[] result) {
				
				RecordDef recordDef = new RecordDef(new FieldDef[] {
                     new StringFieldDef("program_name"),
                     new StringFieldDef("program_id"),
                     new StringFieldDef("program_code")
                 });

         String[][] object2 = new String[result.length][3];

         String[][] data = object2;

         for (int i = 0; i < result.length; i++) {
             object2[i][0] = result[i].getProgram_name();
             object2[i][1] = result[i].getProgram_id();
             object2[i][2] = result[i].getProgram_code();
         }
         
         

         for (int i = 0; i < result.length;
                 i++) {
             oracle.add(result[i].getProgram_code());
         }
         

         MemoryProxy proxy = new MemoryProxy(data);

         ArrayReader reader = new ArrayReader(recordDef);
         Store store = new Store(proxy, reader);
         store.load();

         progCbox.setStore(store);
				
			}
 		
 	});
 	
 	
 	FormPanel searchFormPanel=new FormPanel();
 	
 	searchFormPanel.setTitle(cons.search());
 	searchFormPanel.setBorder(true);
 	searchFormPanel.setBodyBorder(true);
// 	searchFormPanel.setStyle("background");
 	 
      
final SuggestBox codeSuggestBox=new SuggestBox(oracle);
 final Button okButton=new Button(cons.okButton());	
 	
 	
   FlexTable searchFlexTable=new FlexTable();
   searchFlexTable.setWidth("100%");
   searchFlexTable.setWidget(1, 0, new Label(cons.label_programCode()));
   searchFlexTable.setWidget(1, 1, codeSuggestBox);


//   searchFlexTable.setStyleName("background");
   searchFormPanel.add(searchFlexTable);

 final CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
 	
 	
 	okButton.addListener(new ButtonListenerAdapter(){
 		 
			public void onClick(Button button, EventObject e) {
 			boolean flag=true;
 			  	String criteria=null;	
 			  	
 				final GridPanel grid = new GridPanel();
 			  if(method.equalsIgnoreCase("duration"))
		        {

				 if(codeSuggestBox.getText().equals("")){
   					 criteria="id";
   					 if(progCbox.getValue()==null){
    					 MessageBox.alert(msgs.error(),msgs.eitherProgNameOrCode());
    					 flag=false;
    				 }else{
    				 object.setProgram_id(progCbox.getValue());
    				 flag=true;
    				  }
   					 }else{
   						 criteria="code"; 
   						 object.setProgram_code(codeSuggestBox.getText());
   						 flag=true;
   					 }
				if(flag==true) {
				 connectService.methodProgDurationDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

					public void onFailure(Throwable arg0) {
						MessageBox.alert(msgs.failure(),arg0.getMessage());
						
					}

					public void onSuccess(CM_progMasterInfoGetter[] arg0) {
//						VerticalPanel p1Panel=new VerticalPanel();			 
						 
						 if (arg0.length == 0) {
						 grid.setTitle(msgs.error_record());
						  } else {
						    grid.setTitle(cons.heading_programdetails());
						  }
						 
						 final RecordDef rDef = new RecordDef(new FieldDef[] {
	                            new StringFieldDef("programName"),
	                            new StringFieldDef("programCode"),
	                            new StringFieldDef("minduration"),
	                            new StringFieldDef("maxduration"),
	                            new StringFieldDef("startdate"),
	                            new StringFieldDef("programid"),
	                            new StringFieldDef("actualstartdate"),
	                        });
							 
						 object1 = new Object[arg0.length][7];
						 
						                                         String str = null;
						
						                                         try {
						                                             for (int i = 0; i < arg0.length;
						                                                     i++) {
						                                            	
						                                                 for (int k = 0; k < 7; k++) {
						                                                     if (k == 0) {
						                                                         str = arg0[i].getProgram_name();
						                                                     } else if (k == 1) {
						                                                    	 str = arg0[i].getProgram_code();
						                                                     } else if (k == 2) {
						                                                    	 str = arg0[i].getMinimun_duration();
						                                                     } else if (k == 3) {
						                                                    	 str = arg0[i].getMaximum_duration();
						                                                     } else if (k == 4) {
						                                                    	 str = arg0[i].getStartdate();
						                                                    	 int a=Integer.parseInt(str.substring(0,2));
						                                                    	 
						                                                    	 switch (a)
						                                                    	 {
						                                                    	 case 1 :{
						                                                    		 str="Jan "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 2 :{
						                                                    		 str="feb "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 3 :{
						                                                    		 str="Mar "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 4:{
						                                                    		
						                                                    		 str="Apr "+str.substring(2);
						                                                    		 
						                                                    		 break;
						                                                    	 }
						                                                    	 case 5:{
						                                                    		
						                                                    		 str="May "+str.substring(2);
						                                                    		 
						                                                    		 break;
						                                                    	 }
						                                                    	 case 6:{
						                                                    		 str="Jun "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 7:{
						                                                    		 str="Jul "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 8:{
						                                                    		 str="Aug "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 9:{
						                                                    		 str="Sep "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 10:{
						                                                    		 str="Oct "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 11:{
						                                                    		 str="Nov "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 case 12:{
						                                                    		 ;
						                                                    		 str="Dec "+str.substring(2);
						                                                    		 break;
						                                                    	 }
						                                                    	 
						                                                    	 }
						                                                    	 
						                                                    	 
						                                                     } else if (k == 5) {
						                                                    	 str = arg0[i].getProgram_id();
						                                                     }else if (k == 6) {
						                                                    	 str = arg0[i].getStartdate();
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
						                                         
						                                           Store store = new Store(proxy,reader);  
						                                                  store.setSortInfo(new SortState("programCode", SortDir.ASC)); 
						 
						                                         store.load();
						                                         grid.setStore(store);
				
						 try{
						                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
						                                                 new RowNumberingColumnConfig(),
						                                                 new ColumnConfig(cons.label_programName(),
						                                                     "programName", 200, true, null,
						                                                     "programName"),
						                                                 new ColumnConfig(cons.label_programCode(),
						                                                     "programCode", 200, true, null,
						                                                     "programCode"),
						                                                 new ColumnConfig(cons.minDuration(),
						                                                     "minduration", 100, true,
						                                                     null, "minduration"),
						                                                 new ColumnConfig(cons.maxDuration(),
						                                                     "maxduration", 100, true, null,
						                                                     "maxduration"),
						                                                 new ColumnConfig(cons.startDate(),
						                                                     "startdate", 100, true, null,
						                                                     "startdate")
						                                             };
						 
						                                         final ColumnModel columnModel = new ColumnModel(columns);
						                                         grid.setColumnModel(columnModel);
						 }catch (Exception e) {
								System.out.println(e+ "  hwgehghweg");
							}
						                                         grid.setFrame(true);
						                                         grid.setStripeRows(true);
						 
//						                                         grid.setSelectionModel(cbSelectionModel);
						                                         grid.setAutoWidth(true);
						                                         grid.setWidth(750);
						                                         grid.setHeight(200);
						 
						                                         Toolbar topToolBar = new Toolbar();
						                                         topToolBar.addFill();
						                                         
						 
			                                        gridVPanel.clear();
			                                         gridVPanel.add(grid);
			                                        
			                                     	dates();
                                                	final ComboBox monthComboBox=new ComboBox();
                                                	monthComboBox.setStore(monthStore);
                                                	final ComboBox dayComboBox=new ComboBox();
                                                	dayComboBox.setStore(daysStore);                       
                                                	
                                              
                                            	HorizontalPanel dateHPanel=new HorizontalPanel();
                                            	
                                     	         monthComboBox.setDisplayField("month");  
                                     	         monthComboBox.setMode(ComboBox.LOCAL);  
                                     	         monthComboBox.setTriggerAction(ComboBox.ALL);  
                                     	         monthComboBox.setForceSelection(true);  
                                     	         monthComboBox.setValueField("id");
                                    
                                     	         monthComboBox.setReadOnly(true);  
                                            	                	       
                                    	   
//                                    	        
                                     	        dayComboBox.setDisplayField("day");  
                                     	       dayComboBox.setValueField("day");  
                                     	      dayComboBox.setMode(ComboBox.LOCAL);  
                                     	     dayComboBox.setTriggerAction(ComboBox.ALL);  
                                     	    dayComboBox.setLinked(true);  
                                     	   dayComboBox.setForceSelection(true);  
                                     	  dayComboBox.setReadOnly(true);  
                                     		 
                                     	 dayComboBox.setWidth(60);
                                                	monthComboBox.setWidth(60);
                                                	dateHPanel.add(monthComboBox);
                                                	dateHPanel.setSpacing(20);
                                                	dateHPanel.add(dayComboBox);
                                                    monthComboBox.addListener(new ComboBoxListenerAdapter() {  
                                                 	   
                                       	             public void onSelect(ComboBox comboBox, Record record, int index) {
                                       	            	dayComboBox.setStore(daysStore);                                      	    
                                       	            	dayComboBox.setValue("");  
                                       	            
                                       	                 daysStore.filter("id", comboBox.getValue().substring(2)); 
                                       	                                         	                 
                                       	             }  
                                       	         });  
                                                    
                                                    
                                                    FlexTable dateMain=new FlexTable();
                                                    Label startdate=new Label(cons.startDate());
                                                    Button addDateButton=new Button(cons.addButton());
                                                    dateMain.setWidget(0,0,startdate);
                                                    dateMain.setWidget(0,1,dateHPanel);
                                                    dateMain.setWidget(2,10,addDateButton);
                                                    
                                             gridVPanel.add(dateMain);       
                                                    addDateButton.addListener(new ButtonListenerAdapter(){
                                                    	public void onClick(Button button, EventObject e) {
                                                    		if(monthComboBox.getValue()==null||dayComboBox.getValue()==null){
                                                    			try{
                                                    				monthComboBox.markInvalid("invalid");
                                                    			}catch (Exception e1) {
																}try{
																	dayComboBox.markInvalid("invalid");
                                                    			}catch (Exception e1) {
																}
                                                    			
                                                    		}else{
                                                    			CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
                                                    			
                                                    			String startdate=monthComboBox.getValue().substring(0,2)+dayComboBox.getValue();
                                                    			                                                 			
                                                    			object.setStartdate(startdate);
                                                    			object.setMinimun_duration(""+object1[0][2]);
                                                    			object.setMaximum_duration(""+object1[0][3]);
                                                    			object.setProgram_id(progCbox.getValue());                                                    			
                                                    			connectService.methodAddStartDate(userid, object,new AsyncCallback<String>(){

																	public void onFailure(
																			Throwable arg0) {
																		MessageBox.alert(msgs.failure(),arg0.getMessage());
																		
																	}
																	public void onSuccess(
																			String arg0) {
																		MessageBox.alert(msgs.alert(),msgs.alert_successfulentry());
																		okButton.fireEvent("click");
																		
																	}
                                                    				
                                                    			});
                                                    			
                                                    		}
                                                    	}
                                                    });
			                                         
			                                         
			                                         
						 
					}
				
			}); 
			
		        }	 
		        
		        	
		        }
		        else if(method.equalsIgnoreCase("branch"))
		        {

		        	if(codeSuggestBox.getText().equals("")){
	   					 criteria="id";
	   					 if(progCbox.getValue()==null){
	    					 MessageBox.alert(msgs.error(),msgs.eitherProgNameOrCode());
	    					 flag=false;
	    				 }else{
	    				 object.setProgram_id(progCbox.getValue());
	    				 flag=true;
	    				  }
	   					 }else{
	   						 criteria="code"; 
	   						 object.setProgram_code(codeSuggestBox.getText());
	   						 flag=true;
	   					 }
		        	if(flag==true){
	   				 connectService.methodProgBranchDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

						public void onFailure(Throwable arg0) {
							MessageBox.alert(msgs.failure(),arg0.getMessage());
							
						}

						public void onSuccess(CM_progMasterInfoGetter[] arg0) {
							final int resultLength=arg0.length;			
							
							
//							VerticalPanel p1Panel=new VerticalPanel();			 
							 
							 if (arg0.length == 0) {
							 grid.setTitle(msgs.error_record());
							  } else {
							    grid.setTitle(cons.programBranchDetails());
							  }
							 
							 final RecordDef rDef = new RecordDef(new FieldDef[] {
		                            new StringFieldDef("programName"),
		                            new StringFieldDef("programCode"),
		                            new StringFieldDef("branchname"),
		                            new StringFieldDef("branchcode"),
		                           new StringFieldDef("programid"), 
		                           
		                        });
								 
							 object1 = new Object[arg0.length][5];
							 
							                                         String str = null;
							
							                                         try {
							                                             for (int i = 0; i < arg0.length;
							                                                     i++) {
							                                            	
							                                                 for (int k = 0; k < 5; k++) {
							                                                     if (k == 0) {
							                                                         str = arg0[i].getProgram_name();
							                                                     } else if (k == 1) {
							                                                    	 str = arg0[i].getProgram_code();
							                                                     } else if (k == 2) {
							                                                    	 str = arg0[i].getBranchname();
							                                                     } else if (k == 3) {
							                                                    	 str = arg0[i].getBranchcode();
							                                                     }else if (k == 4) {
							                                                    	 str = arg0[i].getProgram_id();
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
							                                         
							                                           Store store = new Store(proxy,reader);  
							                                                 
							                                                  store.setSortInfo(new SortState("programCode", SortDir.ASC));  
							                                                 
							                                         store.load();
							                                         grid.setStore(store);
					
							 try{
							                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
							                                                 new RowNumberingColumnConfig(),
							                                                 new ColumnConfig(cons.label_programName(),
							                                                     "programName", 200, true, null,
							                                                     "programName"),
							                                                 new ColumnConfig(cons.label_programCode(),
							                                                     "programCode", 200, true, null,
							                                                     "programCode"),
							                                                 new ColumnConfig(cons.label_branchname(),
							                                                     "branchname", 200, true,
							                                                     null, "branchname")
							                                             };
							 
							                                         final ColumnModel columnModel = new ColumnModel(columns);
							                                         grid.setColumnModel(columnModel);
							 }catch (Exception e) {
									System.out.println(e+ "  hwgehghweg");
								}
							                                         grid.setFrame(true);
							                                         grid.setStripeRows(true);
							 
							                                         grid.setSelectionModel(cbSelectionModel);
							                                         grid.setAutoWidth(true);
							                                         grid.setWidth(750);
							                                         grid.setHeight(200);
							 
							 
				                                        gridVPanel.clear();
				                                         gridVPanel.add(grid);
				                                         
				                                         FlexTable branchflex=new FlexTable();
				                                         Hyperlink withspecHyperlink=new Hyperlink(msgs.branchWithSpec("Add"),null);
				                                         Hyperlink withoutspecHyperlink=new Hyperlink(msgs.branchWithoutSpec("Add"),null);
				                                         branchflex.setWidget(0,0,withspecHyperlink);
				                                         branchflex.setWidget(0,5,withoutspecHyperlink);
				                                         branchStorePopulate();
				                                     	specGrid();
				                                        
				                                         withspecHyperlink.addClickListener(new ClickListener(){

															public void onClick(
																	Widget arg0) {
			                                                        
			                                                    	try{
			                                                            FlexTable branchflex =
			                                                                new FlexTable();
			                                                            final ComboBox branchName=new ComboBox();	                                                            
			                                                            
			                                                            
			                                    	            		branchName.setStore(branchstore);
			                                    	            		
			                                    	            		branchName.setForceSelection(true);
			                                    	            		branchName.setMinChars(1);
			                                    	            		branchName.setDisplayField("branch_name");
			                                    	            		branchName.setValueField("branch_code");
			                                    	            		branchName.setMode(ComboBox.LOCAL);
			                                    	            		branchName.setTriggerAction(ComboBox.ALL);
			                                    	            		branchName.setEmptyText(cons.select()+cons.label_branchname());
			                                    	            		branchName.setLoadingText(cons.loading());
			                                    	            		branchName.setTypeAhead(true);
			                                    	            		branchName.setSelectOnFocus(true);
			                                    	            		branchName.setWidth(190);
			                                    	            		branchName.setHideTrigger(false);
			                                                        	
			                                                        	GridPanel specgrid=new GridPanel();
			                                                        	

			                                                        	Label specLabel=new Label(cons.spec());
			                                                        
			                                                        	
			                                                        	DecoratorPanel decoSpec=new DecoratorPanel();
			                                                        	VerticalPanel decoVerti=new VerticalPanel();
			                                                        	
			                                                        
			                                                        	
			                                                           	specgrid.setStore(specstore);
			                                                           	
			                                                           
			                                                           	final CheckboxSelectionModel cbSelectionModelspec=new CheckboxSelectionModel();
			                                                          	                                                           	
			                                                           	speccolumns = new BaseColumnConfig[] {
			                                                                    new CheckboxColumnConfig(cbSelectionModelspec),
			                                                                    new ColumnConfig(cons.spec(),
			                                                                        "spec_name", 200, true, null,
			                                                                        "spec_name")
			                                                                };

			                                                           columnModel = new ColumnModel(speccolumns);
			                                                        
			                                                           	specgrid.setColumnModel(columnModel);
			                                                           	specgrid.setFrame(true);
			                                                           	specgrid.setStripeRows(true);
			                                                           	
			                                                           	
			                                                           	specgrid.setSelectionModel(cbSelectionModelspec);
			                                                           	specgrid.setAutoWidth(true);
			                                                         	specgrid.setWidth(250);
			                                                           	specgrid.setHeight(150);
			                                                          
			                                                         	
			                                                        	decoVerti.add(specgrid);
			                                                        	
			                                                     
			                                                        	decoSpec.add(decoVerti);
			                                                        	specLabel.setText(cons.spec());
			                                                        
			                                                        	
			                                                        	
			                                                        	branchflex.clear();
			                                                        	branchflex.setWidget(0,0, new Label(cons.label_branchname()));
			                                                        	  branchflex.setWidget(0,1, branchName);
			                                                        	  branchflex.setWidget(0,13,decoSpec);
			                                                            
			                                                            
			                                                            p1.clear();
			                                                            p1.add(branchflex);
			
			                                                            Button windowAddButton =
			                                                                new Button(
			                                                                    cons.addButton());
			                                                            final Window window = new Window();
			                                                            window.setTitle(
			                                                                msgs.branchWithSpec("New"));
			                                                            window.setWidth(700);
			                                                            window.setHeight(300);
			                                                            window.setResizable(false);
			                                                            window.setLayout(new BorderLayout());
			                                                            window.setPaddings(5);
			                                                            window.setButtonAlign(Position.CENTER);
			                                                            window.addButton(windowAddButton);
			                                                            window.setAutoScroll(true);
			                                                            window.add(p1, bd);
			                                                            window.setCloseAction(Window.CLOSE);
			                                                            window.setPlain(true);
			                                                            window.setFrame(true);
			                                                            window.setClosable(true);
			                                                            window.setModal(true);
			                                                            
			
			                                                            window.show(arg0.getElement());
	
			                                                            //Adding handler to update button of edit window 
			                                                            windowAddButton.addListener(new ButtonListenerAdapter() {
			                                                                    public void onClick(
			                                                                        Button button,
			                                                                        EventObject e) {

			                                        								Record[] records = cbSelectionModelspec.getSelections();
			                                        								String[] arr=new String[records.length+1];
			                                        								CM_progMasterInfoGetter progInfo=new CM_progMasterInfoGetter();
			                                        								String branch1="with";
			                                        								
			                                        								
			                                        								if(branchName.getRawValue().equalsIgnoreCase("")|| records.length==0){
			                                        									
			                                        									}else{
			                                        							String branch=branchName.getValue();
			                                        							for(int q=0;q<records.length+1;q++)
			                                        							{
			                                        								if(q==0){
			                                        									arr[q]=branch;
//			                                        									
			                                        								}else{	
		                                        										int count=0;
		                                        										String branchCode=branchName.getValue();
		                                        										
		                                        										for(int i=0;i<resultLength;i++){
		                                           											String object=""+object1[i][3];
		                    																if(object.equalsIgnoreCase(branchCode)){
		                    																	count++;
		                    																}
		                    															}
		                                        										
		                                        										if(count==0){	
			                                        									Record record = records[q-1];
			                                        									
			                                        								arr[q]=record.getAsString("specialization_code");
			                                        								
			                                        								progInfo.setBranch_code(arr);
			                                        								progInfo.setProgram_id(progCbox.getValue());
			                                        								
			                                        								
			                                        								
			                                        								}else{
		                                        										MessageBox.alert(msgs.error(),msgs.duplicateBranch());
		                                        									}
			                                        								
			                                        								}
			                                        							}
			                                        							
			                                        							connectService.methodAddAnotherBranch(userid, progInfo,branch1,new AsyncCallback<String>(){

																					public void onFailure(
																							Throwable arg0) {
																						MessageBox.alert(msgs.failure()+arg0.getMessage());
																						
																					}
																					
																					public void onSuccess(
																							String arg0) {
																						MessageBox.alert(msgs.alert(),msgs.alert_successfulentry(),new AlertCallback(){

																							public void execute() {
																								okButton.fireEvent("click");
																								window.close();
																							}
																							
																						});
																					
																						
																					}
		                                        									
		                                        								});
			                                        							
			                                        							
			                                        							
			                                        							
			                                        									}
			                                                                    	
			                                                                    }
			                                                                    
			                                                                });
			                                                      
			                                                    }catch (Exception ex) {
																System.out
																		.println("here is the exception "+ ex);
																}
			                                        }
			                                                
																
				                                         });
				                                         withoutspecHyperlink.addClickListener(new ClickListener(){


																public void onClick(
																		Widget arg0) {
				                                                        
				                                                    	try{
				                                                            FlexTable branchflex =
				                                                                new FlexTable();
				                                                            final ComboBox branchName=new ComboBox();	                                                            
				                                                            
				                                                            
				                                    	            		branchName.setStore(branchstore);
				                                    	            		
				                                    	            		branchName.setForceSelection(true);
				                                    	            		branchName.setMinChars(1);
				                                    	            		branchName.setDisplayField("branch_name");
				                                    	            		branchName.setValueField("branch_code");
				                                    	            		branchName.setMode(ComboBox.LOCAL);
				                                    	            		branchName.setTriggerAction(ComboBox.ALL);
				                                    	            		branchName.setEmptyText(cons.select()+" "+cons.label_branchname());
				                                    	            		branchName.setLoadingText(cons.loading());
				                                    	            		branchName.setTypeAhead(true);
				                                    	            		branchName.setSelectOnFocus(true);
				                                    	            		branchName.setWidth(190);
				                                    	            		branchName.setHideTrigger(false);
				                                                        	
				                                                        	branchflex.clear();
				                                                        	branchflex.setWidget(0,0, new Label(cons.label_branchname()));
				                                                        	  branchflex.setWidget(0,1, branchName);
				                                                            p1.clear();
				                                                            p1.add(branchflex);
				
				                                                            Button windowAddButton =
				                                                                new Button(
				                                                                    cons.addButton());
				                                                            final Window window = new Window();
				                                                            window.setTitle(
				                                                                msgs.branchWithoutSpec("New"));
				                                                            window.setWidth(500);
				                                                            window.setHeight(300);
				                                                            window.setResizable(false);
				                                                            window.setLayout(new BorderLayout());
				                                                            window.setPaddings(5);
				                                                            window.setButtonAlign(Position.CENTER);
				                                                            window.addButton(windowAddButton);
				                                                            window.setAutoScroll(true);
				                                                            window.add(p1, bd);
				                                                            window.setCloseAction(Window.CLOSE);
				                                                            window.setPlain(true);
				                                                            window.setFrame(true);
				                                                            window.setClosable(true);
				                                                            window.setModal(true);
				                                                            
				
				                                                            window.show(arg0.getElement());
		
				                                                            //Adding handler to update button of edit window 
				                                                            windowAddButton.addListener(new ButtonListenerAdapter() {
				                                                                    public void onClick(
				                                                                        Button button,
				                                                                        EventObject e) {

				                                        							if(branchName.getRawValue().equalsIgnoreCase("")){
				                                        									
				                                        									}else{
				                                        										int count=0;
				                                        										String branchCode=branchName.getValue();
				                                        										
				                                        										for(int i=0;i<resultLength;i++){
				                                           											String object=""+object1[i][3];
				                    																if(object.equalsIgnoreCase(branchCode)){
				                    																	count++;
				                    																}
				                    															}
				                                        										
				                                        										if(count==0){
				                                        							String branch=branchName.getValue();
				                                        							
		                                        								CM_progMasterInfoGetter progInfo=new CM_progMasterInfoGetter();
		                                        								progInfo.setBranchcode(branch);
		                                        								progInfo.setProgram_id(progCbox.getValue());
		                                        								String branch1="without";
		                                        								
		                                        								connectService.methodAddAnotherBranch(userid, progInfo,branch1,new AsyncCallback<String>(){

																					public void onFailure(
																							Throwable arg0) {
																						MessageBox.alert(msgs.failure(),arg0.getMessage());
																						
																					}
																					
																					public void onSuccess(
																							String arg0) {
																						MessageBox.alert(msgs.alert(),msgs.alert_successfulentry(),new AlertCallback(){

																							public void execute() {
																								okButton.fireEvent("click");
																								window.close();
																							}
																							
																						});
																					
																						
																					}
		                                        									
		                                        								});
				                                        									}else{
				                                        										MessageBox.alert(msgs.error(),msgs.duplicateBranch());
				                                        									}
				                                        									}
				                                        								
				                                                                    	
				                                                                    	
				                                                                    }
				                                                                    
				                                                                });
				                                                      
				                                                    }catch (Exception ex) {
																	System.out
																			.println("here is the exception in only branch"+ ex);
																	}
				                                        }
				                                                
																	
					                                         
				                                         });
				                                        	 
				                                       
				                                         
				                                         
				                                         
				                                         
				                                         gridVPanel.add(branchflex);                         
							 
						}
	   				
	   			}); 
	   			
		        }	 
			        
	 		        	
	 		        }
		        else if(method.equalsIgnoreCase("specialization"))
		        {
		        	 		        	

		        	if(codeSuggestBox.getText().equals("")){
	   					 criteria="id";
	   					 if(progCbox.getValue()==null){
	    					 MessageBox.alert(msgs.error(),msgs.eitherProgNameOrCode());
	    					 flag=false;
	    				 }else{
	    				 object.setProgram_id(progCbox.getValue());
	    				 flag=true;
	    				  }
	   					 }else{
	   						 criteria="code"; 
	   						 object.setProgram_code(codeSuggestBox.getText());
	   						 flag=true;
	   					 }
		        	if(flag==true){
		        		specGrid();
	   				 connectService.methodProgSpecDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

						public void onFailure(Throwable arg0) {
							MessageBox.alert(msgs.failure(),arg0.getMessage());
							
						}

						public void onSuccess(CM_progMasterInfoGetter[] arg0) {
						final int resultLength=arg0.length;
							
							 if (arg0.length == 0) {
							 grid.setTitle(msgs.error_record());
							  } else {
							    grid.setTitle(cons.programSpecDetails());
							  }
							 
							 final RecordDef rDef = new RecordDef(new FieldDef[] {
		                            new StringFieldDef("programName"),
		                            new StringFieldDef("programCode"),
		                            new StringFieldDef("branchname"),
		                            new StringFieldDef("specname"),
		                            new StringFieldDef("programid"),
		                            new StringFieldDef("specialization_code")
		                           
		                        });
								 
							 object1 = new Object[arg0.length][6];
							 
							                                         String str = null;
							
							                                         try {
							                                             for (int i = 0; i < arg0.length;
							                                                     i++) {
							                                            	
							                                                 for (int k = 0; k < 6; k++) {
							                                                     if (k == 0) {
							                                                         str = arg0[i].getProgram_name();
							                                                     } else if (k == 1) {
							                                                    	 str = arg0[i].getProgram_code();
							                                                     } else if (k == 3) {
							                                                    	 str = arg0[i].getSpecname();
							                                                     }else if (k == 2) {
							                                                    	 str = arg0[i].getBranchname();
							                                                     }else if (k == 4) {
							                                                    	 str = arg0[i].getProgram_id();
							                                                     }else if (k == 5) {
							                                                    	 str = arg0[i].getSpecialization_code();
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
							                                         
							                                           GroupingStore store = new GroupingStore();  
							                                                  store.setReader(reader);  
							                                                  store.setDataProxy(proxy);  
							                                                  store.setSortInfo(new SortState("programCode", SortDir.ASC));  
							                                                  store.setGroupField("programCode");  
							                                                  store.load();
							                                         
							                                                    GroupingView gridView = new GroupingView();  
							                                                           gridView.setForceFit(true);  
							                                                           gridView.setGroupTextTpl("{text} ({[values.rs.length]} {[values.rs.length > 1 ? \"Items\" : \"Item\"]})");  
							                                                     
							                                                           grid.setView(gridView);  
//							                                         Store store = new Store(proxy, reader);
							 
							                                         store.load();
							                                         grid.setStore(store);
					
							 try{
							                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
							                                                 new RowNumberingColumnConfig(),
							                                                 new ColumnConfig(cons.label_programName(),
							                                                     "programName", 200, true, null,
							                                                     "programName"),
							                                                 new ColumnConfig(cons.label_programCode(),
							                                                     "programCode", 200, true, null,
							                                                     "programCode"),
							                                                 new ColumnConfig(cons.label_branchname(),
		 							                                              "branchname", 200, true,
		 							                                               null, "branchname"),
							                                                 new ColumnConfig(cons.spec(),
							                                                     "specname", 200, true, null,
							                                                     "specname")
							                                             };
							 
							                                         final ColumnModel columnModel = new ColumnModel(columns);
							                                         grid.setColumnModel(columnModel);
							 }catch (Exception e) {
									System.out.println(e+ "  hwgehghweg");
								}
							                                         grid.setFrame(true);
							                                         grid.setStripeRows(true);
							 
							                                     
							                                         grid.setAutoWidth(true);
							                                         grid.setWidth(750);
							                                         grid.setHeight(280);
							 
				                                        gridVPanel.clear();
				                                         gridVPanel.add(grid);
				                                         
	                                                    	try{
	                                                            FlexTable branchflex =
	                                                                new FlexTable();
	                                                            final ComboBox specName=new ComboBox();	                                                            
	                                                            
	                                                            
	                                    	            		specName.setStore(specstore);
	                                    	            		
	                                    	            		specName.setForceSelection(true);
	                                    	            		specName.setMinChars(1);
	                                    	            		specName.setDisplayField("spec_name");
	                                    	            		specName.setValueField("specialization_code");
	                                    	            		specName.setMode(ComboBox.LOCAL);
	                                    	            		specName.setTriggerAction(ComboBox.ALL);
	                                    	            		specName.setEmptyText(cons.select()+" "+cons.spec());
	                                    	            		specName.setLoadingText(cons.loading());
	                                    	            		specName.setTypeAhead(true);
	                                    	            		specName.setSelectOnFocus(true);
	                                    	            		specName.setWidth(190);
	                                    	            		specName.setHideTrigger(false);
	                                                        	
	                                                        	branchflex.clear();
	                                                        	branchflex.setWidget(0,0, new Label(cons.spec()));
	                                                        	  branchflex.setWidget(0,1, specName);
	                                                            
	                                                        	  Button addButton=new Button(cons.addButton());
	                                                        	  
	                                                            gridVPanel.add(branchflex);
	                                                            gridVPanel.add(addButton);
	                                                           
	                                                           
	                                                            //Adding handler to update button of edit window 
	                                                            addButton.addListener(new ButtonListenerAdapter() {
	                                                                    public void onClick(
	                                                                        Button button,
	                                                                        EventObject e) {
	                                        							if(specName.getRawValue().equalsIgnoreCase("")){
	                                        									
	                                        									}else{
	                                        										int count=0;
	                                        										String spec1=specName.getValue();            										
	                                           										
	                                           										for(int i=0;i<resultLength;i++){
	                                           											String object=""+object1[i][5];
	                                           											if(object.equalsIgnoreCase(spec1)){
	                    																	count++;
	                    																}
	                    															}
	                                           										
	                                           									if(count==0){
	                                        									CM_progMasterInfoGetter progInfo=new CM_progMasterInfoGetter();	
	                                        							String spec=specName.getValue();
	                                        							progInfo.setProgram_id(progCbox.getValue());
	                                        							progInfo.setSpecialization_code(spec);
	                                        							
	                                        							connectService.methodAddAnotherSpec(userid, progInfo,new AsyncCallback<String>(){

																			public void onFailure(
																					Throwable arg0) {
																				MessageBox.alert(msgs.failure()+arg0.getMessage());
																				
																			}
																			
																			public void onSuccess(
																					String arg0) {
																				MessageBox.alert(msgs.alert(),msgs.alert_successfulentry(),new AlertCallback(){

																					public void execute() {
																						okButton.fireEvent("click");
																						
																					}
																				});
																			}
                                        									
                                        								});
	                                        									}else{
	                                        										MessageBox.alert(msgs.error(),msgs.duplicateSpec());
	                                        									}
	                                                                    }
	                                                                    	
	                                                                    	
	                                                                    }
	                                                                    
	                                                                });
	                                                      
	                                                    }catch (Exception ex) {
														System.out
																.println("here is the exception in spec"+ ex);
														}
	                                        
				                                         						 
						}
	   				
	   			}); 
	   			
						 
			        
		        }   	
	 		        
					 
		        
		        }
	   				 else if(method.equalsIgnoreCase("reservation"))
		        {
	   					 categoryList();
	   					if(codeSuggestBox.getText().equals("")){
		   					 criteria="id";
		   					 if(progCbox.getValue()==null){
		    					 MessageBox.alert(msgs.error(),msgs.eitherProgNameOrCode());
		    					 flag=false;
		    				 }else{
		    				 object.setProgram_id(progCbox.getValue());
		    				 flag=true;
		    				  }
		   					 }else{
		   						 criteria="code"; 
		   						 object.setProgram_code(codeSuggestBox.getText());
		   						 flag=true;
		   					 }
	   					
	   				 if(flag==true){
	   					 
	   				 connectService.methodProgReserveDetailsForManage(userid, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

						public void onFailure(Throwable arg0) {
							MessageBox.alert(msgs.failure(),arg0.getMessage());
							
						}

						public void onSuccess(CM_progMasterInfoGetter[] arg0) {
							final int resultLength=arg0.length;
							
							 if (arg0.length == 0) {
							 grid.setTitle(msgs.error_record());
							  } else {
							    grid.setTitle(cons.heading_programdetails());
							  }
							 
							 final RecordDef rDef = new RecordDef(new FieldDef[] {
		                            new StringFieldDef("programName"),
		                            new StringFieldDef("programCode"),
		                            new StringFieldDef("category"),
		                            new StringFieldDef("percentage"),
		                            new StringFieldDef("category_code"),
		                            new StringFieldDef("programid"),
		                        });
								 
							 object1 = new Object[arg0.length][6];
							 
							                                         String str = null;
							
							                                         try {
							                                             for (int i = 0; i < arg0.length;
							                                                     i++) {
							                                            	
							                                                 for (int k = 0; k < 6; k++) {
							                                                     if (k == 0) {
							                                                         str = arg0[i].getProgram_name();
							                                                     } else if (k == 1) {
							                                                    	 str = arg0[i].getProgram_code();
							                                                     } else if (k == 2) {
							                                                    	 str = arg0[i].getCategory();
							                                                     } else if (k == 3) {
							                                                    	 str = arg0[i].getPercentage_seats();
							                                                     } else if (k == 4) {
							                                                    	 str = arg0[i].getCategory_code();
							                                                     }else if (k == 5) {
							                                                    	 str = arg0[i].getProgram_id();
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
							                                         
							                                         
							                                           GroupingStore store = new GroupingStore();  
							                                                  store.setReader(reader);  
							                                                  store.setDataProxy(proxy);  
							                                                  store.setSortInfo(new SortState("programCode", SortDir.ASC));  
							                                                  store.setGroupField("programCode");  
							                                                  store.load();
							                                         
							                                                    GroupingView gridView = new GroupingView();  
							                                                           gridView.setForceFit(true);  
							                                                           gridView.setGroupTextTpl("{text} ({[values.rs.length]} {[values.rs.length > 1 ? \"Items\" : \"Item\"]})");  
							                                                     
							                                                           grid.setView(gridView);  
//							                                         Store store = new Store(proxy, reader);
							 
							                                         store.load();
							                                         grid.setStore(store);
					
							 try{
							                                         BaseColumnConfig[] columns = new BaseColumnConfig[] {
							                                                 new CheckboxColumnConfig(cbSelectionModel),
							                                                 new ColumnConfig(cons.label_programName(),
							                                                     "programName", 200, true, null,
							                                                     "programName"),
							                                                 new ColumnConfig(cons.label_programCode(),
							                                                     "programCode", 200, true, null,
							                                                     "programCode"),
							                                                 new ColumnConfig(cons.category(),
							                                                     "category", 200, true,
							                                                     null, "category"),
							                                                 new ColumnConfig(cons.percent(),
							                                                     "percentage", 200, true, null,
							                                                     "percentage")
							                                             };
							 
							                                         final ColumnModel columnModel = new ColumnModel(columns);
							                                         grid.setColumnModel(columnModel);
							 }catch (Exception e) {
									System.out.println(e+ "  hwgehghweg");
								}
							                                         grid.setFrame(true);
							                                         grid.setStripeRows(true);
							 
							                                         grid.setSelectionModel(cbSelectionModel);
							                                         grid.setAutoWidth(true);
							                                         grid.setWidth(750);
							                                         grid.setHeight(280);
							 
							 
				                                        gridVPanel.clear();
				                                         gridVPanel.add(grid);
				                                        FlexTable reserveflex=new FlexTable();
				                                         
				                                         
				                                         final NumberField percentageText=new NumberField();
				     			                    	Label categoryLabel=new Label(cons.select()+" "+cons.category());
				     			                    	final ComboBox categoryCombo=new ComboBox();
				     			                    	Label percentageLabel =new Label(cons.select()+" "+cons.percent());
//				     			                    	categoryLabel[r].setText("Choose Category ");
				     			                    	categoryCombo.setEmptyText(cons.select()+" "+cons.category());
				     			                    	percentageText.setEmptyText(cons.percent());
//				     			                    	percentageText[r].setMaxLength(3);
				     			                    	
				     			                    	categoryCombo.setStore(catstore);
				     			                    	
				     			                		categoryCombo.setForceSelection(true);
				     			                		categoryCombo.setMinChars(1);
				     			                		categoryCombo.setDisplayField("cat_name");
				     			                		categoryCombo.setValueField("cat_code");
				     			                		categoryCombo.setMode(ComboBox.LOCAL);
				     			                		categoryCombo.setTriggerAction(ComboBox.ALL);
				     			                		categoryCombo.setEmptyText(cons.select()+" "+cons.category());
				     			                		categoryCombo.setLoadingText(cons.loading());
				     			                		categoryCombo.setTypeAhead(true);
				     			                		categoryCombo.setSelectOnFocus(true);
				     			                		categoryCombo.setWidth(190);
				     			                		categoryCombo.setHideTrigger(false);
				     			                    	
				     			                    	reserveflex.clear();
				     			                    	
				     			                    	reserveflex.setWidget(r,0, categoryLabel);
				     			                    	reserveflex.setWidget(r,1, categoryCombo);
				     			                    	reserveflex.setWidget(r,2, percentageLabel);
				     			                    	reserveflex.setWidget(r,3, percentageText);
				     			                    	    	
				                                         
				                               
				                               
				                               Button addButton=new Button(cons.addButton());
				                               gridVPanel.add(reserveflex);
				                               gridVPanel.add(addButton);
				                               
				                               
				                               addButton.addListener(new ButtonListenerAdapter() {
                                                   public void onClick(
                                                       Button button,
                                                       EventObject e) {

                       							if(categoryCombo.getRawValue().equalsIgnoreCase("")||(percentageText.getValueAsString()==null)){
                       								MessageBox.alert(msgs.error(),msgs.requiredCatPercent());
                       									
                       									}else{
                       										int count=0;
                       										String category1=categoryCombo.getValue();            										
                       										
                       										for(int i=0;i<resultLength;i++){
                       											String object=""+object1[i][4];
																if(object.equalsIgnoreCase(category1)){
																	count++;
																}
															}
                       										
                       									if(count==0){
                       							String category=categoryCombo.getValue();
                       						String percentage=	percentageText.getValueAsString();
                       						CM_progMasterInfoGetter progInfo=new CM_progMasterInfoGetter();
                       						progInfo.setCategory(category);
                       						progInfo.setPercentage_seats(percentage);
                       						progInfo.setProgram_id(progCbox.getValue());
                       						
                							
                							connectService.methodAddAnotherCategory(userid, progInfo,new AsyncCallback<String>(){

												public void onFailure(
														Throwable arg0) {
													MessageBox.alert(msgs.failure(),arg0.getMessage());
													
												}
												
												public void onSuccess(
														String arg0) {
													MessageBox.alert(msgs.alert(),msgs.alert_successfulentry(),new AlertCallback(){

														public void execute() {
															okButton.fireEvent("click");
															
														}
													});
												}
            									
            								});
                       									}else{
                       										MessageBox.alert(msgs.error(),msgs.duplicateCategory());
                       									}
                       									}
                       								
                                                   	
                                                   	
                                                   }
                                                   
                                               });
				                                         
							 
						}
	   				
	   			}); 
	   			
						 
			        
		        }
	 		        	        }
				 
 			 
 			
 			 
 		 }
 	});
 	
 	manageProgVpanel.clear();
// 	methodShowSession();
 	manageProgVpanel.add(heading);
 	manageProgVpanel.setSpacing(10);
 	manageProgVpanel.add(nameText);
 	manageProgVpanel.add(sessionText);
 	manageProgVpanel.setSpacing(20);
 	manageProgVpanel.add(upperFlexTable);
 	manageProgVpanel.add(searchFormPanel);
 	manageProgVpanel.add(okButton);
	
 	containerVerticalPanel.clear();
 	containerVerticalPanel.add(manageProgVpanel);
 	containerVerticalPanel.add(gridVPanel);
 	
 	
 	 ProgrammeHorizontalPanel.clear();
 	 ProgrammeHorizontalPanel.setSpacing(20);
      ProgrammeHorizontalPanel.add(containerVerticalPanel);
       ProgrammeHorizontalPanel1.clear();
      ProgrammeHorizontalPanel1.add(ProgrammeHorizontalPanel);
 	


 
    	
    	
    	
    	
    	
    	
    }
    
    
  public void branchStorePopulate()
  {
	  
	  connectService.methodBranchList(userid,new AsyncCallback<CM_progMasterInfoGetter[]>(){
			public void onFailure(Throwable arg0) {
			MessageBox.alert(msgs.failure(),arg0.getMessage());
				
			}

			public void onSuccess(CM_progMasterInfoGetter[] arg0) {
				RecordDef recordDef = new RecordDef(new FieldDef[] {
						 new StringFieldDef("branch_name"),
						 new StringFieldDef("branch_code")
                  });
			
          String[][] object2 = new String[arg0.length][2];

          String[][] data = object2;

          for (int i = 0; i < arg0.length; i++) {
              object2[i][0] = arg0[i].getBranchname();
              object2[i][1] = arg0[i].getBranchcode();
          }

          MemoryProxy proxy = new MemoryProxy(data);

          ArrayReader reader = new ArrayReader(recordDef);
         branchstore = new Store(proxy, reader);
          branchstore.load();
				
			}
			
		});
  }
    
   
  public void categoryList(){
		
	    connectService.methodCategoryList(userid,new AsyncCallback<CM_progMasterInfoGetter[]>(){
			public void onFailure(Throwable arg0) {
			MessageBox.alert(msgs.failure(),arg0.getMessage());
				
			}

			public void onSuccess(CM_progMasterInfoGetter[] arg0) {
				RecordDef recordDef = new RecordDef(new FieldDef[] {
						 new StringFieldDef("cat_name"),
						 new StringFieldDef("cat_code")
	                });
			
	        String[][] object2 = new String[arg0.length][2];

	        String[][] data = object2;

	        for (int i = 0; i < arg0.length; i++) {
	            object2[i][0] = arg0[i].getCategory();
	            object2[i][1] = arg0[i].getCategory_code();
	        }

	        MemoryProxy proxy = new MemoryProxy(data);

	        ArrayReader reader = new ArrayReader(recordDef);
	       catstore = new Store(proxy, reader);
	       catstore.load();
				
			}
			
		});
  }
  
    
    
    
    
    
    
    
    
    
    
    
}
