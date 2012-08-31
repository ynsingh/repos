package in.ac.dei.edrp.client.SystemSetup;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.SetupTRDataService;
import in.ac.dei.edrp.client.RPCFiles.SetupTRDataServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
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

import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
/**
 * this class is for Tie Rule Setup and Management For a Program
 * 
 * @version 1.0 8 May 2012
 * @author ARJUN SINGH
 */
public class SetupTieRule 
{
private String userID;
public VerticalPanel vPanel=new VerticalPanel();
public VerticalPanel vPanel2=new VerticalPanel();
SetupTRDataServiceAsync serviceProxy=GWT.create(SetupTRDataService.class);
messages msgs = GWT.create(messages.class);
constants constants = GWT.create(constants.class);
public SetupTieRule(String userID) {
	
	this.userID = userID;
}

public void setTieRule()
{
	vPanel.clear();
	vPanel2.clear();
	showGrid();
	Button submitButton=new Button(constants.TieRuleButton());
	Button showAllButton=new Button(constants.ShowAll());
	Button closeButton=new Button(constants.Close());
  	final ComboBox programNameBox=new ComboBox();
	programNameBox.setFieldLabel("Select Program Name");   
    programNameBox.setDisplayField("program_name");   
    programNameBox.setMode(ComboBox.LOCAL);   
    programNameBox.setTriggerAction(ComboBox.ALL);   
    programNameBox.setForceSelection(true);   
    programNameBox.setValueField("program_id");   
    programNameBox.setReadOnly(true);
    
    
    final ComboBox componentBox=new ComboBox();
	componentBox.setFieldLabel("Select Component Name");   
    componentBox.setDisplayField("component_name");   
    componentBox.setMode(ComboBox.LOCAL);   
    componentBox.setTriggerAction(ComboBox.ALL);   
    componentBox.setForceSelection(true);   
    componentBox.setValueField("component_id");   
    componentBox.setReadOnly(true);
    
    final ComboBox subComponentBox=new ComboBox();
    subComponentBox.setFieldLabel("Select Sub Component");
   	subComponentBox.setDisplayField("subComp_name");   
   	subComponentBox.setMode(ComboBox.LOCAL);   
   	subComponentBox.setTriggerAction(ComboBox.ALL);   
   	subComponentBox.setForceSelection(true);   
   	subComponentBox.setValueField("subComp_id");   
   	subComponentBox.setReadOnly(true);
   	subComponentBox.disable();
   	
   	
    final NumberField sequenceField=new NumberField();
    sequenceField.setAllowBlank(false);
    sequenceField.setAllowDecimals(false);
    sequenceField.setAllowNegative(false);
    
    final ComboBox calBasisBox=new ComboBox();
   	calBasisBox.setFieldLabel("Select Calculation Basic");   
    calBasisBox.setDisplayField("calBase_name");   
    calBasisBox.setMode(ComboBox.LOCAL);   
    calBasisBox.setTriggerAction(ComboBox.ALL);   
    calBasisBox.setForceSelection(true);   
    calBasisBox.setValueField("calBase_id");   
    calBasisBox.setReadOnly(true);
    
    final ComboBox logicBox=new ComboBox();
   	logicBox.setFieldLabel("Select Logic");   
    logicBox.setDisplayField("logic_name");   
    logicBox.setMode(ComboBox.LOCAL);   
    logicBox.setTriggerAction(ComboBox.ALL);   
    logicBox.setForceSelection(true);   
    logicBox.setValueField("logic_id");   
    logicBox.setReadOnly(true);
    
    serviceProxy.getPrograms(userID, new AsyncCallback<CM_ProgramInfoGetter[]>()
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
								object[i][j]=result[i].getProgram_id();
							}
							else if(j==1)
							{
								object[i][j]=result[i].getProgram_name();
							}
						}
					}
					Store programNameBoxStore=new SimpleStore(new String[]{"program_id","program_name"},object);
					programNameBoxStore.load();
					programNameBox.setStore(programNameBoxStore); 
					}
    	
    		});
    serviceProxy.getCalculationBasis(userID,new AsyncCallback<CM_ProgramInfoGetter[]>()
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
					Store calBasisBoxStore=new SimpleStore(new String[]{"calBase_id","calBase_name"},object);
					calBasisBoxStore.load();
					calBasisBox.setStore(calBasisBoxStore); 
					}
    	
    		});
    serviceProxy.getLogic(userID, new AsyncCallback<CM_ProgramInfoGetter[]>()
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
					
					Store logicBoxStore=new SimpleStore(new String[]{"logic_id","logic_name"},object);
					logicBoxStore.load();
					logicBox.setStore(logicBoxStore); 
					}
    	
    		});
    
    programNameBox.addListener(new ComboBoxListenerAdapter()
    {
    	public void onSelect(ComboBox comboBox, Record record, int index)
    	{
    		
    		componentBox.clearValue();
    		serviceProxy.getComponents(userID, programNameBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
    				{

						
						public void onFailure(Throwable arg0) 
						{
							
							
						}

						
						public void onSuccess(CM_ProgramInfoGetter[] result)
						{

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
							Store componentBoxStore=new SimpleStore(new String[]{"component_id","component_name"},object);
							componentBoxStore.load();
							componentBox.setStore(componentBoxStore); 
						
						}
    			
    				});
    	}
    }
    		);
    
    componentBox.addListener(new ComboBoxListenerAdapter()
    {
    	public void onSelect(ComboBox comboBox, Record record, int index)
    	{
    		subComponentBox.clearValue();
    		subComponentBox.setDisabled(true);
    		
    		serviceProxy.getSubComponents(userID, programNameBox.getValue(), componentBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
    				{

						
						public void onFailure(Throwable arg0) {
							
							
						}

						
						public void onSuccess(CM_ProgramInfoGetter[] result) {
							if(result!=null)
							{

								subComponentBox.enable();
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
								Store subComponentBoxStore=new SimpleStore(new String[]{"subComp_id","subComp_name"},object);
								subComponentBoxStore.load();
								subComponentBox.setStore(subComponentBoxStore); 
							
							
							}
							
							
						}
    			
    				});
    	}
    });
    
    submitButton.addListener(new ButtonListenerAdapter()
    {
    	 public void onClick(Button button, EventObject e)
    	 {
    		 
    		 if(programNameBox.getValue()==null||componentBox.getValue()==null||sequenceField.getValue()==null||logicBox.getValue()==null||calBasisBox.getValue()==null||((!subComponentBox.isDisabled())&&subComponentBox.getValue()==null))
    		 {
    			MessageBox.alert(msgs.ErrorTitle(),msgs.SelectAllField()); 
    		 }
    		 else
    		 {
    			 serviceProxy.checkTR(userID, programNameBox.getValue(), componentBox.getValue(),  subComponentBox.getValue(),calBasisBox.getValue(), new AsyncCallback<Integer>()
    					 {

							
							public void onFailure(Throwable arg0) {
								
								
							}

							
							public void onSuccess(Integer result) {
								if(result>0)
								{
									MessageBox.alert(msgs.ErrorTitle(), msgs.AlreadyExists());
								}
								else
								{
									serviceProxy.checkSequenceNumber(userID, programNameBox.getValue(), componentBox.getValue(),  subComponentBox.getValue(), sequenceField.getValueAsString(),calBasisBox.getValue(),new AsyncCallback<Integer>()
											{

												
												public void onFailure(
														Throwable arg0) {
													
													
												}

												
												public void onSuccess(
														Integer result) {
													
													if(result>0)
													{
														MessageBox.alert(msgs.ErrorTitle(),msgs.SequenceAlreadyUsed());
														
													}
													else
													{

										    			MessageBox.confirm(msgs.ConfirmationTitle(), msgs.Confirmation(),new MessageBox.ConfirmCallback() {
															
															
															public void execute(String btnID) {
																if(btnID.matches("yes"))
																{
																	serviceProxy.insertTRDetails(userID, programNameBox.getValue(),componentBox.getValue(),subComponentBox.getValue(), calBasisBox.getValue(), logicBox.getValue(), sequenceField.getValueAsString(), new AsyncCallback()
																	{

																	
																		public void onFailure(Throwable arg0) {
																			
																			
																		}

																		
																		public void onSuccess(Object result) {
																			
																			MessageBox.alert(msgs.SuccessTitle(), msgs.SuccessInsertMessage());
																			
																			setTieRule();
																		}
																		
																	});
																}
																
															}
														});
										    		 	
													}
													}
										
											}
									);
								}
							}
    				 
    					 }
    			 );
    		 }
    	 }
    });
    
    
    final ComboBox programBox=new ComboBox();
   	programBox.setFieldLabel("Select Program Name");   
       programBox.setDisplayField("program_name");   
       programBox.setMode(ComboBox.LOCAL);   
       programBox.setTriggerAction(ComboBox.ALL);   
       programBox.setForceSelection(true);   
       programBox.setValueField("program_id");   
       programBox.setReadOnly(true);
       
       serviceProxy.getPrograms_FromTR(userID, new AsyncCallback<CM_ProgramInfoGetter[]>()
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
   								object[i][j]=result[i].getProgram_id();
   							}
   							else if(j==1)
   							{
   								object[i][j]=result[i].getProgram_name();
   							}
   						}
   					}
   					Store programBoxStore=new SimpleStore(new String[]{"program_id","program_name"},object);
   					programBoxStore.load();
   					programBox.setStore(programBoxStore); 
   					}
       	
       		});
       
   programBox.addListener(new ComboBoxListenerAdapter()
   {
	   public void onSelect(ComboBox comboBox, Record record, int index)
	   {
		   showGridWithProgram(programBox.getValue());
	   }
   });
    
    
   showAllButton.addListener(new ButtonListenerAdapter()
   {
	   public void onClick(Button button, EventObject e)
	   {
		   showGrid();
	   }
   });
    
   closeButton.addListener(new ButtonListenerAdapter()
   {
	   public void onClick(Button button, EventObject e)
	   {
		   vPanel.clear();
	   }
   });
    FlexTable table=new FlexTable();
    table.setCellPadding(6);
    table.setCellSpacing(6);
    table.setWidget(0, 0, new Label(constants.ProgramName()));
    table.setWidget(1, 0, new Label(constants.PaperName()));
    table.setWidget(2, 0, new Label(constants.Logic()));
    table.setWidget(0, 2, new Label(constants.ComponentName()));
    table.setWidget(1, 2, new Label(constants.CalculationBasis()));
    table.setWidget(2, 2, new Label(constants.SequenceNumber()));
    table.setWidget(0, 1, programNameBox);
    table.setWidget(1, 1, subComponentBox);
    table.setWidget(2, 1, logicBox);
    table.setWidget(0, 3, componentBox);
    table.setWidget(1, 3, calBasisBox);
    table.setWidget(2, 3, sequenceField);
    table.setWidget(3, 0, submitButton);
    FlexTable table2=new FlexTable();
    table2.setWidget(0, 0,  new Label(constants.SearchByProgram()));
    table2.setWidget(0, 1,  programBox);
    table2.setWidget(0, 2,showAllButton);
    table2.setWidget(0, 3,closeButton);
    VerticalPanel vp=new VerticalPanel();
    FormPanel fp=new FormPanel();
    fp.setFrame(true);
    fp.setTitle(constants.TieRuleSetup());
    fp.setStyleName("trForm");
    table.setStyleName("trTable1");
    table2.setStyleName("trTable2");
    vp.add(table);
    vp.add(table2);
    fp.add(vp);
   // vPanel.add(table);
    vPanel.add(fp);
    vPanel.add(vPanel2);
}
public void showGrid()
{


    vPanel2.clear();
    
   
    
    
    	serviceProxy.getTR_DetailsWithoutProgram(userID,new AsyncCallback<CM_ProgramInfoGetter[]>()
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
    						                   new StringFieldDef("sequence") ,
    						                   new StringFieldDef("component_id"),
    						                   new StringFieldDef("component_name") ,
    						                   new StringFieldDef("paper_code"),
    						                   new StringFieldDef("paper_name"),
    						                   new StringFieldDef("cal_base_code") ,
    						                   new StringFieldDef("cal_base_name") ,
    						        		   new StringFieldDef("logic_code") ,
    						                   new StringFieldDef("logic_name")
    						           }   
    						   );   
    						  GridPanel grid = new GridPanel();  
    						   Object[][] object=new Object[result.length][11];
    							for(int i=0;i<result.length;i++)
    							{
    								for(int j=0;j<11;j++)
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
    										object[i][j]=result[i].getSequence();
    									}
    									else if(j==3)
    									{
    										object[i][j]=result[i].getComponent_id();
    									}
    									else if(j==4)
    									{
    										object[i][j]=result[i].getComponentDescription();
    									}
    									else if(j==5)
    									{
    											object[i][j]=result[i].getPaper_code();
    											
    									}
    									else if(j==6)
    									{
    										object[i][j]=result[i].getPaper_description();
    									}
    									else if(j==7)
    									{
    										object[i][j]=result[i].getCal_basis();
    									}
    									else if(j==8)
    									{
    										object[i][j]=result[i].getCal_basisDescription();
    									}
    									else if(j==9)
    									{
    										object[i][j]=result[i].getLogic();
    									}
    									else if(j==10)
    									{
    										object[i][j]=result[i].getLogicDescription();
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
    						               
    						               
    						                new ColumnConfig("Program Name", "programName", 110, true, null,"programName"),   
    						                new ColumnConfig("Sequence Number", "sequence", 110),
    						                new ColumnConfig("Component Name", "component_name", 110),
    						                new ColumnConfig("Paper Code", "paper_name", 110),
    						                new ColumnConfig("Calculation Basis", "cal_base_name", 110),
    						                new ColumnConfig("Logic", "logic_name", 110)
    						                
    						               
    						        };   
    						  
    						        ColumnModel columnModel = new ColumnModel(columns);   
    						        grid.setColumnModel(columnModel);   
    						  
    						          
    						        grid.setStripeRows(true);   
    						        grid.setAutoExpandColumn("programName");   
    						  
    						        grid.setSelectionModel(cbSelectionModel);   
    						          grid.setFrame(true);
    						        grid.setWidth(900);   
    						        grid.setHeight(300); 
    						        grid.setTitle(msgs.ManageTieRule());   
    						        
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
    														final String[] param=new String[5];
    														param[0]=records[i].getAsString("programID");
    														param[1]=records[i].getAsString("component_id");
    														param[2]=records[i].getAsString("paper_code");
    														param[3]=records[i].getAsString("cal_base_code");
    														param[4]=records[i].getAsString("logic_code");
    														serviceProxy.deleteTR(userID,param, new AsyncCallback<Integer>()
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
    																			MessageBox.alert(msgs.ErrorTitle(), msgs.DeleteFailed())	;
    																			
    																		}
    																		setTieRule();
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


public void showGridWithProgram(final String programID)
{


    vPanel2.clear();
    
   
    
    
    
    	serviceProxy.getTR_DetailsWithProgram(userID,programID,new AsyncCallback<CM_ProgramInfoGetter[]>()
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
    						                   new StringFieldDef("sequence") ,
    						                   new StringFieldDef("component_id"),
    						                   new StringFieldDef("component_name") ,
    						                   new StringFieldDef("paper_code"),
    						                   new StringFieldDef("paper_name"),
    						                   new StringFieldDef("cal_base_code") ,
    						                   new StringFieldDef("cal_base_name") ,
    						        		   new StringFieldDef("logic_code") ,
    						                   new StringFieldDef("logic_name")
    						           }   
    						   );   
    						  GridPanel grid = new GridPanel();  
    						   Object[][] object=new Object[result.length][11];
    							for(int i=0;i<result.length;i++)
    							{
    								for(int j=0;j<11;j++)
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
    										object[i][j]=result[i].getSequence();
    									}
    									else if(j==3)
    									{
    										object[i][j]=result[i].getComponent_id();
    									}
    									else if(j==4)
    									{
    										object[i][j]=result[i].getComponentDescription();
    									}
    									else if(j==5)
    									{
    											object[i][j]=result[i].getPaper_code();
    											
    									}
    									else if(j==6)
    									{
    										object[i][j]=result[i].getPaper_description();
    									}
    									else if(j==7)
    									{
    										object[i][j]=result[i].getCal_basis();
    									}
    									else if(j==8)
    									{
    										object[i][j]=result[i].getCal_basisDescription();
    									}
    									else if(j==9)
    									{
    										object[i][j]=result[i].getLogic();
    									}
    									else if(j==10)
    									{
    										object[i][j]=result[i].getLogicDescription();
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
    						               
    						               
    						                new ColumnConfig("Program Name", "programName", 110, true, null,"programName"),   
    						                new ColumnConfig("Sequence Number", "sequence", 110),
    						                new ColumnConfig("Component Name", "component_name", 110),
    						                new ColumnConfig("Paper Code", "paper_name", 110),
    						                new ColumnConfig("Calculation Basis", "cal_base_name", 110),
    						                new ColumnConfig("Logic", "logic_name", 110)
    						                
    						               
    						        };   
    						  
    						        ColumnModel columnModel = new ColumnModel(columns);   
    						        grid.setColumnModel(columnModel);   
    						  
    						          
    						        grid.setStripeRows(true);   
    						        grid.setAutoExpandColumn("programName");   
    						  
    						        grid.setSelectionModel(cbSelectionModel);   
    						          grid.setFrame(true);
    						        grid.setWidth(900);   
    						        grid.setHeight(300); 
    						        grid.setTitle(msgs.ManageTieRule());   
    						        
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
    														final String[] param=new String[5];
    														param[0]=records[i].getAsString("programID");
    														param[1]=records[i].getAsString("component_id");
    														param[2]=records[i].getAsString("paper_code");
    														param[3]=records[i].getAsString("cal_base_code");
    														param[4]=records[i].getAsString("logic_code");
    														serviceProxy.deleteTR(userID,param, new AsyncCallback<Integer>()
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
    																			MessageBox.alert(msgs.ErrorTitle(), msgs.DeleteFailed())	;
    																			
    																		}
    																		setTieRule();
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
