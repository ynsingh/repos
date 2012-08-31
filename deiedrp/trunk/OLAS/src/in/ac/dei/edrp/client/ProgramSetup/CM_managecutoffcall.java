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
import com.gwtext.client.data.SimpleStore;
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
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
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
import in.ac.dei.edrp.client.RPCFiles.COS_DataService;
import in.ac.dei.edrp.client.RPCFiles.COS_DataServiceAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.Date;
import java.util.List;


public class CM_managecutoffcall {
    CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
    COS_DataServiceAsync cos_service=(COS_DataServiceAsync)GWT.create(COS_DataService.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    final HorizontalPanel FacultyPanel = new HorizontalPanel();
    public final VerticalPanel FacultyPanelRight = new VerticalPanel();
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
    final VerticalPanel vPanel2 = new VerticalPanel();
    Object[][] object1;
    String userID;
    String type = "";
    final ListBox entityList = new ListBox();
    HorizontalPanel gridPanel = new HorizontalPanel();
    Validator valid = new Validator();
    String pagename = "Program COS";
    String[] values;
    ToolbarButton editButton = new ToolbarButton("Edit");
    ToolbarButton deletebutton = new ToolbarButton("Delete");
    String program_id;
    String branch_id;
    String specialization_id;
    Label specializationName = new Label();

    public CM_managecutoffcall(String uniid) {
        this.userID = uniid;
    }

    public void methodManageindivisuallist() 
    {
    	FacultyPanelRight.clear();
    	vPanel2.clear();
    	final ComboBox entityTypeBox=new ComboBox();
        
        
        entityTypeBox.setDisplayField("comp_desc");   
        entityTypeBox.setMode(ComboBox.LOCAL);   
        entityTypeBox.setTriggerAction(ComboBox.ALL);   
        entityTypeBox.setForceSelection(true);   
        entityTypeBox.setValueField("comp_id");   
        entityTypeBox.setReadOnly(true);  
	     
        final ComboBox entityNameBox=new ComboBox();   
        entityNameBox.setForceSelection(true);
        entityNameBox.setMinChars(1);
        entityNameBox.setDisplayField("entityName");
        entityNameBox.setValueField("entityID");
        entityNameBox.setMode(ComboBox.LOCAL);
        entityNameBox.setTriggerAction(ComboBox.ALL);
        entityNameBox.setEmptyText("Select Entity Name");
        entityNameBox.setLoadingText("Searching...");
        entityNameBox.setTypeAhead(true);
        entityNameBox.setSelectOnFocus(true);
        entityNameBox.setHideTrigger(false);
       
        entityNameBox.setReadOnly(true);
        cos_service.getEntityTypes(userID, new AsyncCallback<CM_ProgramInfoGetter[]>()
        		{

					
					public void onFailure(Throwable arg0) {
						
						
					}

					
					public void onSuccess(CM_ProgramInfoGetter[] result) {
						Object[][] object=new Object[result.length][2];
						for(int i=0;i<result.length;i++)
						{
							for(int j=0;j<2;j++)
							{
								if(j==0)
								{
									object[i][j]=result[i].getComponent_id();
								}
								else if(j==1)
								{
									object[i][j]=result[i].getComponentDescription();
								}
							}
						}
						Store entityTypeStore=new SimpleStore(new String[]{"comp_id","comp_desc"},object);
						entityTypeStore.load();
						entityTypeBox.setStore(entityTypeStore); 
						
					}
        	
        		});
        	
        entityTypeBox.addListener(new ComboBoxListenerAdapter()
	       {
	    	   public void onSelect(ComboBox comboBox, Record record, int index)
	    	   {
	    		 
	    		   entityNameBox.clearValue();
	    		   showGridWithET(entityTypeBox.getValue()); 
	    		   
	    		  cos_service.getEntityNames(userID, entityTypeBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
	    		  {

					
					public void onFailure(Throwable arg0) {
						
						
					}

					
					public void onSuccess(CM_ProgramInfoGetter[] result) {
						Object[][] object=new Object[result.length][2];
						for(int i=0;i<result.length;i++)
						{
							for(int j=0;j<2;j++)
							{
								if(j==0)
								{
									object[i][j]=result[i].getEntity_name();
								}
								else if(j==1)
								{
									object[i][j]=result[i].getEntity_id();
								}
							}
						}
						Store entityNameStore=new SimpleStore(new String[]{"entityName","entityID"},object);
						entityNameStore.load();
						entityNameBox.setStore(entityNameStore); 
					}
	    			  
	    		  });
	    	   }
	       });
        
       entityNameBox.addListener(new ComboBoxListenerAdapter()
       {
    	   public void onSelect(ComboBox comboBox, Record record, int index)
    	   {
    		   showGridWithEN(entityNameBox.getValue());    
    	   }
       });
       Button showCOS=new Button(constants.ShowCOS());
       Button showALL=new Button(constants.ShowAll());
       showALL.addListener(new ButtonListenerAdapter()
       {
    	   public void onClick(Button button,EventObject e)
    	   {
    		   showGrid();
    	   }
       });
       
       FlexTable table=new FlexTable();
       table.setWidget(0, 0, new Label(constants.EntityType()));
       table.setWidget(1, 0, new Label(constants.EntityName()));
       table.setWidget(0, 1, entityTypeBox);
       table.setWidget(1, 1, entityNameBox);
     //  table.setWidget(2, 0, showCOS);
       table.setWidget(2, 0, showALL);
       table.setStyleName("mcosTable");
       FormPanel fp=new FormPanel();
       fp.setTitle("Manage Program COS");
       fp.setFrame(true);
       fp.setStyleName("mcosForm");
       fp.add(table);
       FacultyPanelRight.add(fp);
       FacultyPanelRight.add(vPanel2);
        
    }

    public void showGrid()
    {

    	vPanel2.clear();

        

        	cos_service.getCOS_Details(userID,new AsyncCallback<CM_ProgramInfoGetter[]>()
        			   {

        				
        				public void onFailure(Throwable arg0) {
        					
        					
        				}

        				
        				public void onSuccess(CM_ProgramInfoGetter[] result) {
        					if(result.length>=1)
        					{

        						final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();   
        						   
        						   RecordDef recordDef = new RecordDef(   
        						           new FieldDef[]{   
        						        		   new StringFieldDef("entityID"), 
        						                   new StringFieldDef("entityName"),   
        						                   new StringFieldDef("programID") ,
        						                   new StringFieldDef("programName"),
        						                   new StringFieldDef("cos_value") ,
        						                   new StringFieldDef("x_factor"),
        						                   new StringFieldDef("cos_seats"),
        						                   new StringFieldDef("start_date") ,
        						                   new StringFieldDef("end_date")
        						                  
        						           }   
        						   );   
        						  GridPanel grid = new GridPanel();  
        						   Object[][] object=new Object[result.length][9];
        							for(int i=0;i<result.length;i++)
        							{
        								for(int j=0;j<9;j++)
        								{
        									 if(j==0)
        									{
        										object[i][j]=result[i].getEntity_id();
        									}
        									else if(j==1)
        									{
        										object[i][j]=result[i].getEntity_name();
        									}
        									else if(j==2)
        									{
        										object[i][j]=result[i].getProgram_id();
        									}
        									else if(j==3)
        									{
        										object[i][j]=result[i].getProgram_name();
        									}
        									else if(j==4)
        									{
        										object[i][j]=result[i].getCos_value();
        									}
        									else if(j==5)
        									{
        										object[i][j]=result[i].getX_Factor();
        									}
        									else if(j==6)
        									{
        										object[i][j]=result[i].getCos_seats();
        									}
        									else if(j==7)
        									{
        										object[i][j]=result[i].getSession_start_date();
        									}
        									else if(j==8)
        									{
        										object[i][j]=result[i].getSession_end_date();
        									}
        									
        								}
        							}
        							
        							 MemoryProxy proxy = new MemoryProxy(object);   
        							  
        						        ArrayReader reader = new ArrayReader(recordDef);   
        						        Store store = new Store(proxy, reader);   
        						        store.load();   
        						        grid.setStore(store);  
        						        
        						        BaseColumnConfig[] columns = new BaseColumnConfig[]{   
        						                new CheckboxColumnConfig(cbSelectionModel),   
        						               
        						               
        						                new ColumnConfig("Entity Name", "entityName", 110, true, null, "entityName"),   
        						                new ColumnConfig("Program Name", "programName", 110),
        						                new ColumnConfig("COS Value", "cos_value", 110),
        						                new ColumnConfig("X Factor", "x_factor", 110),
        						                new ColumnConfig("COS Seats", "cos_seats", 110),
        						                new ColumnConfig("Session Start Date", "start_date", 110),
        						                new ColumnConfig("Session End Date", "end_date", 110)
        						                
        						               
        						        };   
        						  
        						        ColumnModel columnModel = new ColumnModel(columns);   
        						        grid.setColumnModel(columnModel);   
        						  
        						          
        						        grid.setStripeRows(true);   
        						        grid.setAutoExpandColumn("entityName");   
        						  
        						        grid.setSelectionModel(cbSelectionModel);   
        						          grid.setFrame(true);
        						        grid.setWidth(900);   
        						        grid.setHeight(300); 
        						        grid.setTitle(msgs.ManageCOS());   
        						        
        						        Toolbar toolbar=new Toolbar();
        						        toolbar.addFill();
        						       
        						        final ToolbarButton deleteButton = new ToolbarButton(msgs.DeleteButton());
        						        
        						        toolbar.addButton(deleteButton);
        						        grid.setTopToolbar(toolbar);

        						        deleteButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		final Record[] records = cbSelectionModel.getSelections();
        						        		if(records.length<1)
        						        		{
        						        			MessageBox.alert(msgs.AlertTitle(),msgs.SelectARecord());	
        						        		}
        						        		else if(records.length>=1)
        						        		{
        						        		MessageBox.confirm(msgs.ConfirmationTitle(),msgs.DeleteConfirm(), new MessageBox.ConfirmCallback() {
        											
        											
        											public void execute(String btnID) {
        												if(btnID.matches("yes"))
        												{
        													for (int i = 0; i < records.length; i++)
        													{
        														final String[] param=new String[3];
        														param[0]=records[i].getAsString("entityID");
        														param[1]=records[i].getAsString("programID");
        														param[2]=records[i].getAsString("cos_value");
        														cos_service.deleteCOSDetails(param, new AsyncCallback<Integer>()
        																{

        																	
        																	public void onFailure(
        																			Throwable arg0) {
        																		
        																		
        																	}

        																	
        																	public void onSuccess(
        																			Integer result) {
        																		if(result>=1)
        																		{
        																		MessageBox.alert(msgs.SuccessTitle() ,msgs.SuccessDelete())	;
        																		}
        																		else if((result==null)||(result!=1)||(result<1))
        																		{
        																			MessageBox.alert(msgs.ErrorTitle(), msgs.DeleteFailed())	;
        																			
        																		}
        																		showGrid();
        																	}
        															
        																});
        														
        													}
        												}
        												
        											}
        										})	;
        						        		}
        						        	}
        						        });
        						       
        								
        							
        										

        						        vPanel2.add(grid);
        						
        					}
        					else
        					{
        						MessageBox.alert(msgs.ErrorTitle(), msgs.NoRecordDisplay());
        					}//Success Ends
        		   
        				}
        				});	

        	
        
    
    }
    public void showGridWithET(String entityType)
    {


    	vPanel2.clear();

        

        	cos_service.getCOS_DetailsWithET(userID,entityType,new AsyncCallback<CM_ProgramInfoGetter[]>()
        			   {

        				
        				public void onFailure(Throwable arg0) {
        					
        					
        				}

        				
        				public void onSuccess(CM_ProgramInfoGetter[] result) {
        					if(result.length>=1)
        					{

        						final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();   
        						   
        						   RecordDef recordDef = new RecordDef(   
        						           new FieldDef[]{   
        						        		   new StringFieldDef("entityID"), 
        						                   new StringFieldDef("entityName"),   
        						                   new StringFieldDef("programID") ,
        						                   new StringFieldDef("programName"),
        						                   new StringFieldDef("cos_value") ,
        						                   new StringFieldDef("x_factor"),
        						                   new StringFieldDef("cos_seats"),
        						                   new StringFieldDef("start_date") ,
        						                   new StringFieldDef("end_date")
        						                  
        						           }   
        						   );   
        						  GridPanel grid = new GridPanel();  
        						   Object[][] object=new Object[result.length][9];
        							for(int i=0;i<result.length;i++)
        							{
        								for(int j=0;j<9;j++)
        								{
        									 if(j==0)
        									{
        										object[i][j]=result[i].getEntity_id();
        									}
        									else if(j==1)
        									{
        										object[i][j]=result[i].getEntity_name();
        									}
        									else if(j==2)
        									{
        										object[i][j]=result[i].getProgram_id();
        									}
        									else if(j==3)
        									{
        										object[i][j]=result[i].getProgram_name();
        									}
        									else if(j==4)
        									{
        										object[i][j]=result[i].getCos_value();
        									}
        									else if(j==5)
        									{
        										object[i][j]=result[i].getX_Factor();
        									}
        									else if(j==6)
        									{
        										object[i][j]=result[i].getCos_seats();
        									}
        									else if(j==7)
        									{
        										object[i][j]=result[i].getSession_start_date();
        									}
        									else if(j==8)
        									{
        										object[i][j]=result[i].getSession_end_date();
        									}
        									
        								}
        							}
        							
        							 MemoryProxy proxy = new MemoryProxy(object);   
        							  
        						        ArrayReader reader = new ArrayReader(recordDef);   
        						        Store store = new Store(proxy, reader);   
        						        store.load();   
        						        grid.setStore(store);  
        						        
        						        BaseColumnConfig[] columns = new BaseColumnConfig[]{   
        						                new CheckboxColumnConfig(cbSelectionModel),   
        						               
        						               
        						                new ColumnConfig("Entity Name", "entityName", 110, true, null, "entityName"),   
        						                new ColumnConfig("Program Name", "programName", 110),
        						                new ColumnConfig("COS Value", "cos_value", 110),
        						                new ColumnConfig("X Factor", "x_factor", 110),
        						                new ColumnConfig("COS Seats", "cos_seats", 110),
        						                new ColumnConfig("Session Start Date", "start_date", 110),
        						                new ColumnConfig("Session End Date", "end_date", 110)
        						                
        						               
        						        };   
        						  
        						        ColumnModel columnModel = new ColumnModel(columns);   
        						        grid.setColumnModel(columnModel);   
        						  
        						          
        						        grid.setStripeRows(true);   
        						        grid.setAutoExpandColumn("entityName");   
        						  
        						        grid.setSelectionModel(cbSelectionModel);   
        						          grid.setFrame(true);
        						        grid.setWidth(900);   
        						        grid.setHeight(300); 
        						        grid.setTitle(msgs.ManageCOS());   
        						        
        						        Toolbar toolbar=new Toolbar();
        						        toolbar.addFill();
        						       
        						        final ToolbarButton deleteButton = new ToolbarButton(msgs.DeleteButton());
        						        
        						        toolbar.addButton(deleteButton);
        						        grid.setTopToolbar(toolbar);

        						        deleteButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		final Record[] records = cbSelectionModel.getSelections();
        						        		if(records.length<1)
        						        		{
        						        			MessageBox.alert(msgs.AlertTitle(),msgs.SelectARecord());	
        						        		}
        						        		else if(records.length>=1)
        						        		{
        						        		MessageBox.confirm(msgs.ConfirmationTitle(),msgs.DeleteConfirm(), new MessageBox.ConfirmCallback() {
        											
        											
        											public void execute(String btnID) {
        												if(btnID.matches("yes"))
        												{
        													for (int i = 0; i < records.length; i++)
        													{
        														final String[] param=new String[3];
        														param[0]=records[i].getAsString("entityID");
        														param[1]=records[i].getAsString("programID");
        														param[2]=records[i].getAsString("cos_value");
        														cos_service.deleteCOSDetails(param, new AsyncCallback<Integer>()
        																{

        																	
        																	public void onFailure(
        																			Throwable arg0) {
        																		
        																		
        																	}

        																	
        																	public void onSuccess(
        																			Integer result) {
        																		if(result>=1)
        																		{
        																		MessageBox.alert(msgs.SuccessTitle() ,msgs.SuccessDelete())	;
        																		}
        																		else if((result==null)||(result!=1)||(result<1))
        																		{
        																			MessageBox.alert(msgs.ErrorTitle(), msgs.DeleteFailed())	;
        																			
        																		}
        																		showGrid();
        																	}
        															
        																});
        														
        													}
        												}
        												
        											}
        										})	;
        						        		}
        						        	}
        						        });
        						       
        								
        							
        										

        						        vPanel2.add(grid);
        						
        					}
        					else
        					{
        						MessageBox.alert(msgs.ErrorTitle(), msgs.NoRecordDisplay());
        					}//Success Ends
        		   
        				}
        				});	

        	
        
    
    
    }
    public void showGridWithEN(String entityID)
    {



    	vPanel2.clear();

        

        	cos_service.getCOS_DetailsWithEN(userID,entityID,new AsyncCallback<CM_ProgramInfoGetter[]>()
        			   {

        				
        				public void onFailure(Throwable arg0) {
        					
        					
        				}

        				
        				public void onSuccess(CM_ProgramInfoGetter[] result) {
        					if(result.length>=1)
        					{

        						final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();   
        						   
        						   RecordDef recordDef = new RecordDef(   
        						           new FieldDef[]{   
        						        		   new StringFieldDef("entityID"), 
        						                   new StringFieldDef("entityName"),   
        						                   new StringFieldDef("programID") ,
        						                   new StringFieldDef("programName"),
        						                   new StringFieldDef("cos_value") ,
        						                   new StringFieldDef("x_factor"),
        						                   new StringFieldDef("cos_seats"),
        						                   new StringFieldDef("start_date") ,
        						                   new StringFieldDef("end_date")
        						                  
        						           }   
        						   );   
        						  GridPanel grid = new GridPanel();  
        						   Object[][] object=new Object[result.length][9];
        							for(int i=0;i<result.length;i++)
        							{
        								for(int j=0;j<9;j++)
        								{
        									 if(j==0)
        									{
        										object[i][j]=result[i].getEntity_id();
        									}
        									else if(j==1)
        									{
        										object[i][j]=result[i].getEntity_name();
        									}
        									else if(j==2)
        									{
        										object[i][j]=result[i].getProgram_id();
        									}
        									else if(j==3)
        									{
        										object[i][j]=result[i].getProgram_name();
        									}
        									else if(j==4)
        									{
        										object[i][j]=result[i].getCos_value();
        									}
        									else if(j==5)
        									{
        										object[i][j]=result[i].getX_Factor();
        									}
        									else if(j==6)
        									{
        										object[i][j]=result[i].getCos_seats();
        									}
        									else if(j==7)
        									{
        										object[i][j]=result[i].getSession_start_date();
        									}
        									else if(j==8)
        									{
        										object[i][j]=result[i].getSession_end_date();
        									}
        									
        								}
        							}
        							
        							 MemoryProxy proxy = new MemoryProxy(object);   
        							  
        						        ArrayReader reader = new ArrayReader(recordDef);   
        						        Store store = new Store(proxy, reader);   
        						        store.load();   
        						        grid.setStore(store);  
        						        
        						        BaseColumnConfig[] columns = new BaseColumnConfig[]{   
        						                new CheckboxColumnConfig(cbSelectionModel),   
        						               
        						               
        						                new ColumnConfig("Entity Name", "entityName", 110, true, null, "entityName"),   
        						                new ColumnConfig("Program Name", "programName", 110),
        						                new ColumnConfig("COS Value", "cos_value", 110),
        						                new ColumnConfig("X Factor", "x_factor", 110),
        						                new ColumnConfig("COS Seats", "cos_seats", 110),
        						                new ColumnConfig("Session Start Date", "start_date", 110),
        						                new ColumnConfig("Session End Date", "end_date", 110)
        						                
        						               
        						        };   
        						  
        						        ColumnModel columnModel = new ColumnModel(columns);   
        						        grid.setColumnModel(columnModel);   
        						  
        						          
        						        grid.setStripeRows(true);   
        						        grid.setAutoExpandColumn("entityName");   
        						  
        						        grid.setSelectionModel(cbSelectionModel);   
        						          grid.setFrame(true);
        						        grid.setWidth(900);   
        						        grid.setHeight(300); 
        						        grid.setTitle(msgs.ManageCOS());   
        						        
        						        Toolbar toolbar=new Toolbar();
        						        toolbar.addFill();
        						       
        						        final ToolbarButton deleteButton = new ToolbarButton(msgs.DeleteButton());
        						        
        						        toolbar.addButton(deleteButton);
        						        grid.setTopToolbar(toolbar);

        						        deleteButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		final Record[] records = cbSelectionModel.getSelections();
        						        		if(records.length<1)
        						        		{
        						        			MessageBox.alert(msgs.AlertTitle(),msgs.SelectARecord());	
        						        		}
        						        		else if(records.length>=1)
        						        		{
        						        		MessageBox.confirm(msgs.ConfirmationTitle(),msgs.DeleteConfirm(), new MessageBox.ConfirmCallback() {
        											
        											
        											public void execute(String btnID) {
        												if(btnID.matches("yes"))
        												{
        													for (int i = 0; i < records.length; i++)
        													{
        														final String[] param=new String[3];
        														param[0]=records[i].getAsString("entityID");
        														param[1]=records[i].getAsString("programID");
        														param[2]=records[i].getAsString("cos_value");
        														cos_service.deleteCOSDetails(param, new AsyncCallback<Integer>()
        																{

        																	
        																	public void onFailure(
        																			Throwable arg0) {
        																		
        																		
        																	}

        																	
        																	public void onSuccess(
        																			Integer result) {
        																		if(result>=1)
        																		{
        																		MessageBox.alert(msgs.SuccessTitle() ,msgs.SuccessDelete())	;
        																		}
        																		else if((result==null)||(result!=1)||(result<1))
        																		{
        																			MessageBox.alert(msgs.ErrorTitle(), msgs.DeleteFailed())	;
        																			
        																		}
        																		showGrid();
        																	}
        															
        																});
        														
        													}
        												}
        												
        											}
        										})	;
        						        		}
        						        	}
        						        });
        						       
        								
        							
        										

        						        vPanel2.add(grid);
        						
        					}
        					else
        					{
        						MessageBox.alert(msgs.ErrorTitle(), msgs.NoRecordDisplay());
        					}//Success Ends
        		   
        				}
        				});	

        	
        
    
    
    	
    }
    public void methodmanagedefaultlist() {}

    public int checkDate() throws Exception {
        @SuppressWarnings("unused")
		Date startDate = null;
        @SuppressWarnings("unused")
		Date endDate = null;
        int check = 0;

        try {
            startDate = (Date) startField.getValue();
            endDate = (Date) endField.getValue();

            if (startField.getDisplayValue().length() > 10) {
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


        return check;
    }
}
