package in.ac.dei.edrp.client.ProgramSetup;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.COS_DataService;
import in.ac.dei.edrp.client.RPCFiles.COS_DataServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
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
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
/**
 * this class is for Modifying Examination Center Setup For a Program
 * 
 * @version 1.0 8 May 2012
 * @author ARJUN SINGH
 */
public class ManageExaminationCenter 
{
private String userID;
public VerticalPanel vPanel=new VerticalPanel();
VerticalPanel vPanel2=new VerticalPanel();
COS_DataServiceAsync cos_service=(COS_DataServiceAsync)GWT.create(COS_DataService.class);
messages msgs = GWT.create(messages.class);
constants constants = GWT.create(constants.class);
public ManageExaminationCenter(String userID) {
	
	this.userID = userID;
}
public void manageCenter()
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
	    Button okButton=new Button("Show Examination Center");
	    

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
	    		   
	    		 vPanel2.clear();
	    		 entityNameBox.clearValue(); 
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
	    		vPanel2.clear();
	    	}
	    });
	    
	    FlexTable table=new FlexTable();
	    table.setWidget(0,0, new Label(constants.EntityType()));
	    table.setWidget(1,0, new Label(constants.EntityName()));
	    table.setWidget(0,1, entityTypeBox);
	    table.setWidget(1,1, entityNameBox);
	    table.setWidget(2,0, okButton);
	    table.setCellPadding(6);
	    table.setCellSpacing(6);
	    FormPanel fp=new FormPanel();
	    fp.setTitle(constants.ManageCOS());
	    fp.setFrame(true);
	    fp.setStyleName("mecForm");
	    table.setStyleName("mecTable");
	    fp.add(table);
	    vPanel.add(fp);
	    vPanel.add(vPanel2);
	   okButton.addListener(new ButtonListenerAdapter()
	   {
		   public void onClick(Button button, EventObject e)
		   {
			   
			   showGrid(entityNameBox.getValue());
		   }
	   });
	   
	  

	  
	  //Code For the Grid
}

public void showGrid(final String entityID)
{
vPanel2.clear();

	cos_service.getEntranceDetails(userID,entityID,new AsyncCallback<CM_ProgramInfoGetter[]>()
			   {

				
				public void onFailure(Throwable arg0) {
					
					
				}

				
				public void onSuccess(CM_ProgramInfoGetter[] result) {
					if(result.length>=1)
					{

						final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();   
						   
						   RecordDef recordDef = new RecordDef(   
						           new FieldDef[]{   
						                   new StringFieldDef("programID"), 
						                   new StringFieldDef("programName"), 
						                   new StringFieldDef("centerCode"),
						                   new StringFieldDef("entranceCenter")   
						           }   
						   );   
						  GridPanel grid = new GridPanel();  
						   Object[][] object=new Object[result.length][4];
							for(int i=0;i<result.length;i++)
							{
								for(int j=0;j<4;j++)
								{
									 if(j==0)
									{
										object[i][j]=result[i].getProgram_id();
									}
									else if(j==1)
									{
										object[i][j]=result[i].getProgram_name();
									}
									else if(j==2)
									{
										object[i][j]=result[i].getComponent_id();
									}
									else if(j==3)
									{
										object[i][j]=result[i].getComponentDescription();
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
						                 
						               
						                new ColumnConfig("Program Name", "programName", 110, true, null, "programName"),   
						                new ColumnConfig("Examination Center", "entranceCenter", 110),   
						               
						        };   
						  
						        ColumnModel columnModel = new ColumnModel(columns);   
						        grid.setColumnModel(columnModel);   
						  
						          
						        grid.setStripeRows(true);   
						        grid.setAutoExpandColumn("programName");   
						  
						        grid.setSelectionModel(cbSelectionModel);   
						          grid.setFrame(true);
						        grid.setWidth(600);   
						        grid.setHeight(300); 
						        grid.setTitle(constants.ManageExaminationCenter());   
						        grid.setIconCls("grid-icon"); 
						        Toolbar toolbar=new Toolbar();
						        toolbar.addFill();
						       
						        final ToolbarButton deleteButton = new ToolbarButton(constants.delete());
						        
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
														final String[] param=new String[2];
														
														param[0]=records[i].getAsString("programID");
														param[1]=records[i].getAsString("centerCode");
														cos_service.deleteEntranceCenter(param,entityID,userID, new AsyncCallback<Integer>()
																{

																	
																	public void onFailure(
																			Throwable arg0) {
																		
																		
																	}

																	
																	public void onSuccess(
																			Integer result) {
																		if(result>=1)
																		{
																		MessageBox.alert(msgs.SuccessTitle(), msgs.SuccessDelete())	;
																		}
																		else if((result==null)||(result!=1)||(result<1))
																		{
																			MessageBox.alert(msgs.ErrorTitle(),msgs.DeleteFailed())	;
																			
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
						MessageBox.alert(msgs.ErrorTitle(), msgs.NoRecordDisplay());
					}//Success Ends
		   
				}
				});	

	
}
}
