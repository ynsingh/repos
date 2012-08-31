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
package in.ac.dei.edrp.client.SystemSetup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
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
import com.gwtext.client.widgets.form.Checkbox;
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

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritDataService;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritDataServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.List;


public class CM_managefinalmeritcomponents {
	FinalMeritDataServiceAsync serviceProxy=GWT.create(FinalMeritDataService.class);
    CM_LoginConnectSAsync loginconnect = GWT.create(CM_LoginConnectS.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    
      
    public VerticalPanel vPanel=new VerticalPanel();
    VerticalPanel vPanel2=new VerticalPanel();
   
    String userID;
    
   

    public CM_managefinalmeritcomponents(String user_id) {
        this.userID = user_id;
    }

    public void methodmanagefinalmerit() 
    {
    	vPanel.clear();
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
        Button showFMCButton=new Button("Show Final Merit Components");	
    	
        serviceProxy.getEntityTypes(userID, new AsyncCallback<CM_ProgramInfoGetter[]>()
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
    								object[i][j]=result[i].getComponentId();
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
     		   
     		   
     		  serviceProxy.getEntityNames(userID, entityTypeBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
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
 								object[i][j]=result[i].getEntity_id();
 							}
 							else if(j==1)
 							{
 								object[i][j]=result[i].getEntity_name();
 							}
 						}
 					}
 					Store entityNameStore=new SimpleStore(new String[]{"entityID","entityName"},object);
 					entityNameStore.load();
 					entityNameBox.setStore(entityNameStore); 
 					}
     			  
     		  		});
     	   			}
        			});
        showFMCButton.addListener(new ButtonListenerAdapter()
        {
        	 public void onClick(Button button, EventObject e)
        	 {
        		
        		 if((entityTypeBox.getValue()==null) && (entityNameBox.getValue()==null))
        		 {
        			 showGridWithoutEntityType();
        		 }
        		 else if((entityTypeBox.getValue()!=null) && (entityNameBox.getValue()==null))
        		 {
        			 showGridWithEntityType(entityTypeBox.getValue());
        		 }
        		 else
        		 {
        			 showGrid(entityNameBox.getValue()); 
        		 }
        	 }
        });
        FlexTable table=new FlexTable();
        table.setWidget(0, 0, new Label("Entity Type"));
        table.setWidget(1, 0, new Label("Entity Name"));
        table.setWidget(0, 1, entityTypeBox);
        table.setWidget(1, 1, entityNameBox);
        table.setWidget(2, 0, showFMCButton);
        FormPanel fp=new FormPanel();
        fp.setTitle("Manage Final Merit Components");
        fp.setStyleName("mfmcStyle");
        table.setStyleName("mfmcTableStyle");
        fp.setFrame(true);
        fp.add(table);
       vPanel.add(fp);
        vPanel.add(vPanel2);
     
    }
 
    public void showGrid(final String entityID)
    {

        vPanel2.clear();

        	serviceProxy.getFMCDetails(userID,entityID,new AsyncCallback<CM_ProgramInfoGetter[]>()
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
        						                   new StringFieldDef("componentID") ,
        						                   new StringFieldDef("componentName"),
        						                   new StringFieldDef("attdFlag"),
        						                   new StringFieldDef("totalMarks") ,
        						                   new StringFieldDef("weightagePercentage") ,
        						                   new StringFieldDef("academicImpact")
        						           }   
        						   );   
        						  GridPanel grid = new GridPanel();  
        						   Object[][] object=new Object[result.length][10];
        							for(int i=0;i<result.length;i++)
        							{
        								for(int j=0;j<10;j++)
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
        										object[i][j]=result[i].getComponent_id();
        									}
        									else if(j==5)
        									{
        										object[i][j]=result[i].getComponentDescription();
        									}
        									else if(j==6)
        									{
        										object[i][j]=result[i].getAttendanceImpact();
        									}
        									else if(j==7)
        									{
        										object[i][j]=result[i].getTotal_marks();
        									}
        									else if(j==8)
        									{
        										object[i][j]=result[i].getWeightagePercentage();
        									}
        									else if(j==9)
        									{
        										object[i][j]=result[i].getAcademicImpact();
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
        						                new ColumnConfig("Component Name", "componentName", 110),
        						                new ColumnConfig("Attendance Impact", "attdFlag", 110),
        						                new ColumnConfig("Total Marks", "totalMarks", 110),
        						                new ColumnConfig("Weightage Percentage", "weightagePercentage", 110),
        						                new ColumnConfig("Academic Record Impact", "academicImpact", 110)
        						                
        						               
        						        };   
        						  
        						        ColumnModel columnModel = new ColumnModel(columns);   
        						        grid.setColumnModel(columnModel);   
        						  
        						          
        						        grid.setStripeRows(true);   
        						        grid.setAutoExpandColumn("entityName");   
        						  
        						        grid.setSelectionModel(cbSelectionModel);   
        						          grid.setFrame(true);
        						        grid.setWidth(900);   
        						        grid.setHeight(300); 
        						        grid.setTitle("Manage Final Merit Components");   
        						        
        						        Toolbar toolbar=new Toolbar();
        						        toolbar.addFill();
        						       
        						        final ToolbarButton deleteButton = new ToolbarButton("Delete");
        						        final ToolbarButton editButton=new ToolbarButton(constants.edit());
        						        toolbar.addButton(editButton);
        						        toolbar.addButton(deleteButton);
        						        grid.setTopToolbar(toolbar);

        						        editButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		String knowCaller="EI";
        						        		final Record[] record=cbSelectionModel.getSelections();
        						        		showEditWindow(record,knowCaller);
        						        	}
        						        });
        						        
        						        deleteButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		final Record[] records = cbSelectionModel.getSelections();
        						        		if(records.length<1)
        						        		{
        						        			MessageBox.alert("Alert","Please Select A Record");	
        						        		}
        						        		else if(records.length>=1)
        						        		{
        						        		MessageBox.confirm("Confirm","Do you want to delete the selected record ?", new MessageBox.ConfirmCallback() {
        											
        											
        											public void execute(String btnID) {
        												if(btnID.matches("yes"))
        												{
        													for (int i = 0; i < records.length; i++)
        													{
        														final String[] param=new String[3];
        														param[0]=records[i].getAsString("entityID");
        														param[1]=records[i].getAsString("programID");
        														param[2]=records[i].getAsString("componentID");
        														serviceProxy.deleteFMCDetails(param, new AsyncCallback<Integer>()
        																{

        																	
        																	public void onFailure(
        																			Throwable arg0) {
        																		
        																		
        																	}

        																	
        																	public void onSuccess(
        																			Integer result) {
        																		if(result>=1)
        																		{
        																		MessageBox.alert("Success", "The Records Deleted Successfully.")	;
        																		}
        																		else if((result==null)||(result!=1)||(result<1))
        																		{
        																			MessageBox.alert("Error", "The Records Could Not Be Deleted.")	;
        																			
        																		}
        																		showGrid(entityID);
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
        						MessageBox.alert("Error", "There are no records to display.");
        					}//Success Ends
        		   
        				}
        				});	

        	
        
    }
    
    public void showGridWithoutEntityType()
    {

        vPanel2.clear();

        	serviceProxy.getFMCDetailsWithoutEntityType(userID,new AsyncCallback<CM_ProgramInfoGetter[]>()
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
        						                   new StringFieldDef("componentID") ,
        						                   new StringFieldDef("componentName"),
        						                   new StringFieldDef("attdFlag"),
        						                   new StringFieldDef("totalMarks") ,
        						                   new StringFieldDef("weightagePercentage"),
        						                   new StringFieldDef("academicImpact") 
        						           }   
        						   );   
        						  GridPanel grid = new GridPanel();  
        						   Object[][] object=new Object[result.length][10];
        							for(int i=0;i<result.length;i++)
        							{
        								for(int j=0;j<10;j++)
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
        										object[i][j]=result[i].getComponent_id();
        									}
        									else if(j==5)
        									{
        										object[i][j]=result[i].getComponentDescription();
        									}
        									else if(j==6)
        									{
        										object[i][j]=result[i].getAttendanceImpact();
        									}
        									else if(j==7)
        									{
        										object[i][j]=result[i].getTotal_marks();
        									}
        									else if(j==8)
        									{
        										object[i][j]=result[i].getWeightagePercentage();
        									}
        									else if(j==9)
        									{
        										object[i][j]=result[i].getAcademicImpact();
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
        						                new ColumnConfig("Component Name", "componentName", 110),
        						                new ColumnConfig("Attendance Impact", "attdFlag", 110),
        						                new ColumnConfig("Total Marks", "totalMarks", 110),
        						                new ColumnConfig("Weightage Percentage", "weightagePercentage", 110),
        						                new ColumnConfig("Academic Record Impact", "academicImpact", 110)
        						                
        						               
        						        };   
        						  
        						        ColumnModel columnModel = new ColumnModel(columns);   
        						        grid.setColumnModel(columnModel);   
        						  
        						          
        						        grid.setStripeRows(true);   
        						        grid.setAutoExpandColumn("entityName");   
        						  
        						        grid.setSelectionModel(cbSelectionModel);   
        						          grid.setFrame(true);
        						        grid.setWidth(900);   
        						        grid.setHeight(300); 
        						        grid.setTitle("Manage Final Merit Components");   
        						        
        						        Toolbar toolbar=new Toolbar();
        						        toolbar.addFill();
        						       
        						        final ToolbarButton deleteButton = new ToolbarButton("Delete");
        						        final ToolbarButton editButton=new ToolbarButton(constants.edit());
        						        toolbar.addButton(editButton);
        						        toolbar.addButton(deleteButton);
        						        grid.setTopToolbar(toolbar);
        						        
        						        editButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		String knowCaller="None";
        						        		final Record[] record=cbSelectionModel.getSelections();
        						        		showEditWindow(record,knowCaller);
        						        	}
        						        });
        						        deleteButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		final Record[] records = cbSelectionModel.getSelections();
        						        		if(records.length<1)
        						        		{
        						        			MessageBox.alert("Alert","Please Select A Record");	
        						        		}
        						        		else if(records.length>=1)
        						        		{
        						        		MessageBox.confirm("Confirm","Do you want to delete the selected record ?", new MessageBox.ConfirmCallback() {
        											
        											
        											public void execute(String btnID) {
        												if(btnID.matches("yes"))
        												{
        													for (int i = 0; i < records.length; i++)
        													{
        														final String[] param=new String[3];
        														param[0]=records[i].getAsString("entityID");
        														param[1]=records[i].getAsString("programID");
        														param[2]=records[i].getAsString("componentID");
        														serviceProxy.deleteFMCDetails(param, new AsyncCallback<Integer>()
        																{

        																	
        																	public void onFailure(
        																			Throwable arg0) {
        																		
        																		
        																	}

        																	
        																	public void onSuccess(
        																			Integer result) {
        																		if(result>=1)
        																		{
        																		MessageBox.alert("Success", "The Records Deleted Successfully.")	;
        																		}
        																		else if((result==null)||(result!=1)||(result<1))
        																		{
        																			MessageBox.alert("Error", "The Records Could Not Be Deleted.")	;
        																			
        																		}
        																		showGridWithoutEntityType();
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
        						MessageBox.alert("Error", "There are no records to display.");
        					}//Success Ends
        		   
        				}
        				});	

        	
        
    }
    public void showGridWithEntityType(final String entityType)
    {

        vPanel2.clear();

        	serviceProxy.getFMCDetailsWithEntityType(userID,entityType,new AsyncCallback<CM_ProgramInfoGetter[]>()
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
        						                   new StringFieldDef("componentID") ,
        						                   new StringFieldDef("componentName"),
        						                   new StringFieldDef("attdFlag"),
        						                   new StringFieldDef("totalMarks") ,
        						                   new StringFieldDef("weightagePercentage"),
        						                   new StringFieldDef("academicImpact") 
        						           }   
        						   );   
        						  GridPanel grid = new GridPanel();  
        						   Object[][] object=new Object[result.length][10];
        							for(int i=0;i<result.length;i++)
        							{
        								for(int j=0;j<10;j++)
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
        										object[i][j]=result[i].getComponent_id();
        									}
        									else if(j==5)
        									{
        										object[i][j]=result[i].getComponentDescription();
        									}
        									else if(j==6)
        									{
        										object[i][j]=result[i].getAttendanceImpact();
        									}
        									else if(j==7)
        									{
        										object[i][j]=result[i].getTotal_marks();
        									}
        									else if(j==8)
        									{
        										object[i][j]=result[i].getWeightagePercentage();
        									}
        									else if(j==9)
        									{
        										object[i][j]=result[i].getAcademicImpact();
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
        						                new ColumnConfig("Component Name", "componentName", 110),
        						                new ColumnConfig("Attendance Impact", "attdFlag", 110),
        						                new ColumnConfig("Total Marks", "totalMarks", 110),
        						                new ColumnConfig("Weightage Percentage", "weightagePercentage", 110),
        						                new ColumnConfig("Academic Record Impact","academicImpact", 110)
        						                
        						               
        						        };   
        						  
        						        ColumnModel columnModel = new ColumnModel(columns);   
        						        grid.setColumnModel(columnModel);   
        						  
        						          
        						        grid.setStripeRows(true);   
        						        grid.setAutoExpandColumn("entityName");   
        						  
        						        grid.setSelectionModel(cbSelectionModel);   
        						          grid.setFrame(true);
        						        grid.setWidth(900);   
        						        grid.setHeight(300); 
        						        grid.setTitle("Manage Final Merit Components");   
        						        
        						        Toolbar toolbar=new Toolbar();
        						        toolbar.addFill();
        						       
        						        final ToolbarButton deleteButton = new ToolbarButton("Delete");
        						        final ToolbarButton editButton=new ToolbarButton(constants.edit());
        						        toolbar.addButton(editButton);
        						        toolbar.addButton(deleteButton);
        						        grid.setTopToolbar(toolbar);
        						        
        						        editButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		String knowCaller="ET";
        						        		final Record[] record=cbSelectionModel.getSelections();
        						        		showEditWindow(record,knowCaller);
        						        	}
        						        });
        						        deleteButton.addListener(new ButtonListenerAdapter()
        						        {
        						        	public void onClick(Button button, EventObject e)
        						        	{
        						        		final Record[] records = cbSelectionModel.getSelections();
        						        		if(records.length<1)
        						        		{
        						        			MessageBox.alert("Alert","Please Select A Record");	
        						        		}
        						        		else if(records.length>=1)
        						        		{
        						        		MessageBox.confirm("Confirm","Do you want to delete the selected record ?", new MessageBox.ConfirmCallback() {
        											
        											
        											public void execute(String btnID) {
        												if(btnID.matches("yes"))
        												{
        													for (int i = 0; i < records.length; i++)
        													{
        														final String[] param=new String[3];
        														param[0]=records[i].getAsString("entityID");
        														param[1]=records[i].getAsString("programID");
        														param[2]=records[i].getAsString("componentID");
        														serviceProxy.deleteFMCDetails(param, new AsyncCallback<Integer>()
        																{

        																	
        																	public void onFailure(
        																			Throwable arg0) {
        																		
        																		
        																	}

        																	
        																	public void onSuccess(
        																			Integer result) {
        																		if(result>=1)
        																		{
        																		MessageBox.alert("Success", "The Records Deleted Successfully.")	;
        																		}
        																		else if((result==null)||(result!=1)||(result<1))
        																		{
        																			MessageBox.alert("Error", "The Records Could Not Be Deleted.")	;
        																			
        																		}
        																		showGridWithEntityType(entityType);
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
        						MessageBox.alert("Error", "There are no records to display.");
        					}//Success Ends
        		   
        				}
        				});	

        	
        
    }
    final void showEditWindow(final Record[] record, final String knowCaller)
    {
    	if(record.length>1)
		{
			MessageBox.alert("Alert","Please Select Only One Record");
		}
		else if(record.length<1)
		{
			MessageBox.alert("Alert","Please Select A Record");
		}
		else if(record.length==1)
		{
			final String[] param=new String[9];
			param[0]=record[0].getAsString("entityID");
			param[1]=record[0].getAsString("entityName");
			param[2]=record[0].getAsString("programID");
			param[3]=record[0].getAsString("programName");
			param[4]=record[0].getAsString("componentID");
			param[5]=record[0].getAsString("componentName");
			param[6]=record[0].getAsString("attdFlag");
			param[7]=record[0].getAsString("totalMarks");
			param[8]=record[0].getAsString("weightagePercentage");
			
			final Checkbox check=new Checkbox();
			if(param[6].equalsIgnoreCase("Y"))
			{
			check.setChecked(true);	
			}
			else
			{
			check.setChecked(false);	
			}
			
			
			final NumberField marksField=new NumberField();
			final NumberField weightageField=new NumberField();
			marksField.setAllowBlank(false);
			marksField.setAllowDecimals(false);
			marksField.setAllowNegative(false);
			marksField.setValue(param[7]);
			weightageField.setAllowBlank(false);
			weightageField.setAllowDecimals(true);
			weightageField.setAllowNegative(false);
			weightageField.setValue(param[8]);
			
			Button submitButton=new Button(constants.edit());
			Button closeButton=new Button(constants.Close());
			
			if(param[4].trim().equalsIgnoreCase("AS"))
			{
				weightageField.setDisabled(true);
			}
			else
			{
				weightageField.setDisabled(false);
			}
			final Window window = new Window();
			FlexTable table=new FlexTable();
			table.setWidget(0, 0, new Label(constants.EntityName()));
			table.setWidget(1, 0, new Label(constants.ProgramName()));
			table.setWidget(2, 0, new Label(constants.ComponentName()));
			table.setWidget(3, 0, new Label(constants.AttendanceImpact()));
			table.setWidget(4, 0, new Label(constants.TotalMarks()));
			table.setWidget(5, 0, new Label(constants.WeightagePercentage()));
			table.setWidget(0, 1, new Label(param[1]));
			table.setWidget(1, 1, new Label(param[3]));
			table.setWidget(2, 1, new Label(param[5]));
			table.setWidget(3, 1, check);
			table.setWidget(4, 1, marksField);
			table.setWidget(5, 1, weightageField);
			table.setWidget(6, 0, submitButton);
			table.setWidget(6, 1, closeButton);
			Panel editPanel=new Panel();
			editPanel.setStyleName("mfmcPanel");
			editPanel.add(table);
			ScrollPanel sPanel = new ScrollPanel();
			//sPanel.setHeight("10em");
			sPanel.add(editPanel);
			sPanel.setStyleName("mfmcSPanel");
			 window.setTitle(constants.ManageFMC()); 
			 //window.setWidth(500);
			 //window.setHeight(300);
		        window.setClosable(true);
		        window.setPlain(true);   
		        window.setLayout(new BorderLayout());  
		        window.add(sPanel, new BorderLayoutData(RegionPosition.CENTER));
		        window.setCloseAction(Window.HIDE); 
		        window.setStyleName("editFMCWindow");
		        window.setModal(true);
		        window.show();
		      
			closeButton.addListener(new ButtonListenerAdapter()
			{
			public void onClick(Button button, EventObject e)
			{
				window.close();
			}
			});
			submitButton.addListener(new ButtonListenerAdapter()
			{
			public void onClick(Button button, EventObject e)
			{
				if((weightageField.getValue().intValue()>100)||(weightageField.getValue().intValue()<0))
				{
					MessageBox.alert(msgs.ErrorTitle(), msgs.WeightagePerLimit());
				}
				else
				{

					MessageBox.confirm(msgs.ConfirmationTitle(), msgs.UpdateConfirm(), new MessageBox.ConfirmCallback() {
						
						
						public void execute(String btnID) {
							if(btnID.matches("yes"))
							{

								String attendanceImpact;
								if(check.getValue()==true)
								{
									attendanceImpact="Y";	
								}
								else
								{
									attendanceImpact="N";
								}
								serviceProxy.updateFMC(param[0], param[2], param[4], attendanceImpact, marksField.getValueAsString(), weightageField.getValueAsString(), new AsyncCallback<Integer>()
										{

											
											public void onFailure(Throwable arg0) {
											
												
											}

											
											public void onSuccess(Integer result) {
												
											
											MessageBox.alert(msgs.SuccessTitle(), msgs.UpdateSuccess())	;
											if(knowCaller.trim().equalsIgnoreCase("EI"))
											{
											showGrid(param[0]);	
											}
											else
											{
												showGridWithoutEntityType();
											}
											
											}
									
										});
							
							}
							
						}
					});
				
				}
			}
			});
	       
		}	
    }
}
