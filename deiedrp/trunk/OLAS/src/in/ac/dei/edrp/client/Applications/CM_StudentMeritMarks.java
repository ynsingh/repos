package in.ac.dei.edrp.client.Applications;

import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
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
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.EditorGridPanel;
import com.gwtext.client.widgets.grid.GridEditor;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowNumberingColumnConfig;
import com.gwtext.client.widgets.grid.event.GridRowListener;



public class CM_StudentMeritMarks implements EntryPoint{
	
    private final CM_connectAsync connectService = GWT.create(CM_connect.class);
    
	VerticalPanel outerPanel=new VerticalPanel();
	final EditorGridPanel grid = new EditorGridPanel();	
	
    String[][] object2;
    String userid;
    
//    public CM_StudentMeritMarks(String Uid) {
//        this.userid = Uid;
//    }
	
	public void onModuleLoad(){
		methodAddMarks();
//		methodEditMarks();
		  RootPanel.get().add(outerPanel);
		
		}
	
	
	
	EditorGridPanel method1()
	
	{
		
//		  final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
		
		
		grid.setTitle("Marks");
		
		
		 final RecordDef rDef = new RecordDef(new FieldDef[] {
               new StringFieldDef("RegistrationNumber"),
               new StringFieldDef("Name"),
               new StringFieldDef("CallMerit"),
               new StringFieldDef("WrittenTest"),
               new StringFieldDef("Interview"),
               new StringFieldDef("TotalMarks"),
               
           });
		 
	        Object[][] object1 = new Object[10][5];
		
		String str="demo";
		
		for (int i = 0; i < 10;i++) {
  for (int k = 0; k < 5; k++) {
		
	        object1[i][k] = str;
		
  }
		}
		
		 Object[][] data = object1;
	

		  MemoryProxy proxy = new MemoryProxy(data);

  ArrayReader reader = new ArrayReader(rDef);
  Store store = new Store(proxy, reader);
      store.load();
      

	

      ColumnConfig commonCol = new ColumnConfig("Registration Number", "RegistrationNumber", 220, true, null, "RegistrationNumber"); 
      		ColumnConfig nameCol = new ColumnConfig("Name", "Name", 130,true,null,"Name"); 
              
      		ColumnConfig callMerit = new ColumnConfig("Call Merit", "CallMerit", 130,true,null,"CallMerit");  
//               callMerit.setEditor(new GridEditor(new NumberField()));  
              
               ColumnConfig writtenCol = new ColumnConfig("Written Test", "WrittenTest", 130,true,null,"WrittenTest");  
               writtenCol.setEditor(new GridEditor(new NumberField())); 
             
               ColumnConfig interviewCol = new ColumnConfig("Interview", "Interview", 130,true,null,"Interview");  
               interviewCol.setEditor(new GridEditor(new NumberField())); 
               
               ColumnConfig totalCol = new ColumnConfig("Total Marks", "TotalMarks", 130,true,null,"TotalMarks");  
               totalCol.setEditor(new GridEditor(new NumberField())); 

      BaseColumnConfig[] columns = new BaseColumnConfig[] {
              new RowNumberingColumnConfig(),
              commonCol,
  	          nameCol,
  	          callMerit,
  	          writtenCol,
  	          interviewCol,
  	          totalCol
  	         };  
		
		
      final ColumnModel columnModel = new ColumnModel(columns);
      columnModel.setDefaultSortable(true); 
		
		
		grid.setStore(store);	
		
    grid.setColumnModel(columnModel);
    grid.setClicksToEdit(1); 
    grid.setFrame(true);
    grid.setSize("900px","300px");
    grid.setStripeRows(true);
   	grid.setTrackMouseOver(true);
  
   	
   	grid.addGridRowListener(new GridRowListener(){

		@Override
		public void onRowClick(GridPanel grid1, int rowIndex, EventObject e) {
			grid.startEditing(rowIndex,3);
			
		}

		public void onRowContextMenu(GridPanel grid1, int rowIndex, EventObject e) {
		
			
		}

		public void onRowDblClick(GridPanel grid1, int rowIndex, EventObject e) {
		
			
		}
   		
   	});
   	
    ToolbarButton saveButton=new ToolbarButton("Save");
	Toolbar bottomToolBar = new Toolbar();
	bottomToolBar.addFill();
	bottomToolBar.addButton(saveButton);
	grid.setBottomToolbar(bottomToolBar);
    	
    	
   	
	   	
    
	grid.setAutoExpandColumn("Registration Number");
	grid.setAutoExpandColumn("Name");
	grid.setAutoExpandColumn("CallMerit");
	grid.setAutoExpandColumn("WrittenTest");
	grid.setAutoExpandColumn("Interview");
	grid.setAutoExpandColumn("TotalMarks");

	return grid;
}
	
	
	public void methodAddMarks(){

		final VerticalPanel mainPanel=new VerticalPanel();

		Label heading=new Label("Add Marks");
		Label instiName=new Label("Dayalbagh Educational Institute");
		Label session=new Label("Session: 2010-11");
		
		heading.setStyleName("heading");
		
		FlexTable upperFlexTable=new FlexTable();
		Button okButton=new Button("OK");
		
		Label entityTypeLabel=new Label("Entity Type ");
		Label entityNameLabel=new Label("Entity Name ");
		Label programNameLabel=new Label("Program ");
		
		final ComboBox entityTypeCBox=new ComboBox();
		ComboBox entityNameCBox=new ComboBox();
		ComboBox programNameCBox=new ComboBox();
		
		entityTypeCBox.setEmptyText("Choose Entity Type");
		entityNameCBox.setEmptyText("Choose Entity Name");
		programNameCBox.setEmptyText("Choose Program");
		
		entityTypeCBox.setForceSelection(true);
		entityTypeCBox.setMinChars(1);
		entityTypeCBox.setFieldLabel("Entity");
		entityTypeCBox.setDisplayField("entity_type");
		entityTypeCBox.setMode(ComboBox.LOCAL);
		entityTypeCBox.setTriggerAction(ComboBox.ALL);
		entityTypeCBox.setEmptyText("Choose Entity Type");
		entityTypeCBox.setLoadingText("Loading...");
		entityTypeCBox.setTypeAhead(true);
		entityTypeCBox.setSelectOnFocus(true);
		entityTypeCBox.setHideTrigger(false);
		
		
		upperFlexTable.setWidget(0,0,entityTypeLabel);
		upperFlexTable.setWidget(0,1,entityTypeCBox);
		upperFlexTable.setWidget(1,0,entityNameLabel);
		upperFlexTable.setWidget(1,1,entityNameCBox);
		upperFlexTable.setWidget(2,0,programNameLabel);
		upperFlexTable.setWidget(2,1,programNameCBox);
		upperFlexTable.setWidget(6,2, okButton);
		
		
		mainPanel.setSpacing(20);
		mainPanel.add(heading);
		mainPanel.setSpacing(10);
		mainPanel.add(instiName);
		mainPanel.add(session);
		mainPanel.add(upperFlexTable);
		outerPanel.clear();
		outerPanel.setSize("100%","100%");
		outerPanel.add(mainPanel);
		outerPanel.setStyleName("background");
		
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				
				if(mainPanel.getWidgetCount()==4){
					mainPanel.add(method1());	
				}
			}
		});
		
		
		userid="E000100001";
		
		   connectService.methodEntityList(userid,
		            new AsyncCallback<CM_entityInfoGetter[]>() {
		                public void onFailure(Throwable arg0) {
		                    MessageBox.alert("Error", arg0.getMessage());
		                }

		                public void onSuccess(CM_entityInfoGetter[] arg0) {
		                    RecordDef recordDef = new RecordDef(new FieldDef[] {
		                                new StringFieldDef("entity_type"),
		                            });

		                    object2 = new String[arg0.length][1];

		                    String[][] data = object2;

		                    for (int i = 0; i < arg0.length; i++) {
		                        object2[i][0] = arg0[i].getEntity_name();
		                        System.out.println(object2[i][0]);
		                    }

		                    MemoryProxy proxy = new MemoryProxy(data);

		                    ArrayReader reader = new ArrayReader(recordDef);
		                    Store store = new Store(proxy, reader);
		                    store.load();

		                    entityTypeCBox.setStore(store);
		                }
		            });
		
		
		
	}
	
	
	
	public void methodEditMarks(){


		final VerticalPanel mainPanel=new VerticalPanel();
		
		Label heading=new Label("Modify Marks");
		Label instiName=new Label("Dayalbagh Educational Institute");
		Label session=new Label("Session: 2010-11");
		
		heading.setStyleName("heading");
		
		FlexTable upperFlexTable=new FlexTable();
		Button okButton=new Button("OK");
		
		FormPanel searchPanel=new FormPanel();
		
		searchPanel.setFrame(true);
		searchPanel.setTitle("Search");
		searchPanel.setSize("275px","100px");
		
		
		FlexTable searchFlexTable=new FlexTable();
		Label searchCriteria=new Label("Criteria ");
		ComboBox criteriaCombo=new ComboBox();
		Label searchValue=new Label("Value ");
		TextField valueText=new TextField();
		criteriaCombo.setEmptyText("Choose Criteria");
		
		searchFlexTable.setWidget(0,0,searchCriteria);
		searchFlexTable.setWidget(0,1,criteriaCombo);
		searchFlexTable.setWidget(1,0,searchValue);
		searchFlexTable.setWidget(1,1,valueText);
		
		searchPanel.add(searchFlexTable);
		

		
		Label entityTypeLabel=new Label("Entity Type ");
		Label entityNameLabel=new Label("Entity Name ");
		Label programNameLabel=new Label("Program ");
		
		ComboBox entityTypeCBox=new ComboBox();
		ComboBox entityNameCBox=new ComboBox();
		ComboBox programNameCBox=new ComboBox();
		
		entityTypeCBox.setEmptyText("Choose Entity Type");
		entityNameCBox.setEmptyText("Choose Entity Name");
		programNameCBox.setEmptyText("Choose Program");
		
		upperFlexTable.setWidget(0,0,entityTypeLabel);
		upperFlexTable.setWidget(0,1,entityTypeCBox);
		upperFlexTable.setWidget(1,0,entityNameLabel);
		upperFlexTable.setWidget(1,1,entityNameCBox);
		upperFlexTable.setWidget(2,0,programNameLabel);
		upperFlexTable.setWidget(2,1,programNameCBox);
		upperFlexTable.setWidget(6,2, okButton);
		
		mainPanel.setSpacing(20);
		mainPanel.add(heading);
		mainPanel.setSpacing(10);
		mainPanel.add(instiName);
		mainPanel.add(session);
		mainPanel.add(upperFlexTable);
		mainPanel.add(searchPanel);
		outerPanel.clear();
		outerPanel.setSize("100%","100%");
		outerPanel.add(mainPanel);
		outerPanel.setStyleName("background");
		
		okButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				
				if(mainPanel.getWidgetCount()==5){
					mainPanel.add(method1());	
				}
				
			}
		});
		
		final CheckboxSelectionModel cbSelectionModel=new CheckboxSelectionModel();	
	
		
		grid.setSelectionModel(cbSelectionModel);
		
	
		

		
        Toolbar topToolBar = new Toolbar();
        topToolBar.addFill();

        ToolbarButton disableButton = new ToolbarButton("Disable",
                new ButtonListenerAdapter() {
                    public void onClick(Button editButton, EventObject e) {
                    	  Record[] records = cbSelectionModel.getSelections();
                    	  if(records.length<1){
                    		  MessageBox.alert("select a record");
                    	  }else
                    	  {
                    		  /*
                    		   * disable the record where regisration number=records[0].getAsString("RegistrationNumber")
                    		   */
                    	  System.out.println("testing "+records[0].getAsString("RegistrationNumber") );
                    }
                    }
        });
		
        topToolBar.addButton(disableButton);
        grid.add(topToolBar);
    
		
	}
	
	
	
	
	

}
