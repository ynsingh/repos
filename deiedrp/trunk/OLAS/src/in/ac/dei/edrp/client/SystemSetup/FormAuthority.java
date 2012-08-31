package in.ac.dei.edrp.client.SystemSetup;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritDataService;
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
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;


/**
*
* @author Arjun Singh
*/
public class FormAuthority 
{
	SetupTRDataServiceAsync serviceProxy=GWT.create(SetupTRDataService.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    private VerticalPanel vPanel2 = new VerticalPanel();
   
    String userID;
    
    public FormAuthority(String userID)
    {
    	this.userID=userID;
    }
    public void setFormAuthority()
    {
    	vPanel.clear();
    	vPanel2.clear();
    	showRecords();
    	final ComboBox userNameBox=new ComboBox(); 
    	final ComboBox formNameBox=new ComboBox(); 
    	Label userNameLabel=new Label(constants.userName());
    	Label formNameLabel=new Label(constants.FormName());
    	Button submitButton=new Button(constants.Submit());
    	Button closeButton=new Button(constants.Close());
    	
    	FlexTable table=new FlexTable();
    	FormPanel form=new FormPanel();
    	form.setTitle(constants.FormAuthority());
    	form.setStyleName("frmaForm");
    	form.setFrame(true);
    	
        userNameBox.setForceSelection(true);
        userNameBox.setMinChars(1);
        userNameBox.setDisplayField("userName");
        userNameBox.setValueField("userID");
        userNameBox.setMode(ComboBox.LOCAL);
        userNameBox.setTriggerAction(ComboBox.ALL);
        userNameBox.setEmptyText(constants.SelectFormUser());
        userNameBox.setLoadingText("Searching...");
        userNameBox.setTypeAhead(true);
        userNameBox.setSelectOnFocus(true);
        userNameBox.setHideTrigger(false);
        
          
        formNameBox.setForceSelection(true);
        formNameBox.setMinChars(1);
        formNameBox.setDisplayField("formName");
        formNameBox.setValueField("formNumber");
        formNameBox.setMode(ComboBox.LOCAL);
        formNameBox.setTriggerAction(ComboBox.ALL);
        formNameBox.setEmptyText(constants.SelectFormName());
        formNameBox.setLoadingText("Searching...");
        formNameBox.setTypeAhead(true);
        formNameBox.setSelectOnFocus(true);
        formNameBox.setHideTrigger(false);
        
        serviceProxy.getUserDetails(userID, new AsyncCallback<CM_ProgramInfoGetter[]>()
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
    								object[i][j]=result[i].getUserID();
    							}
    							else if(j==1)
    							{
    								object[i][j]=result[i].getUserName();
    							}
    						}
    					}
    					Store userStore=new SimpleStore(new String[]{"userID","userName"},object);
    					userStore.load();
    					userNameBox.setStore(userStore); 
    					
    					
						
					}
        	
        		});
        
        userNameBox.addListener(new ComboBoxListenerAdapter()
			{
				public void onSelect(ComboBox comboBox, Record record, int index)
				{
					formNameBox.clearValue();
					serviceProxy.getFormDetails(userID,userNameBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
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
			    								object[i][j]=result[i].getFormID();
			    							}
			    							else if(j==1)
			    							{
			    								object[i][j]=result[i].getFormName();
			    							}
			    						}
			    					}
			    					Store formStore=new SimpleStore(new String[]{"formNumber","formName"},object);
			    					formStore.load();
			    					formNameBox.setStore(formStore); 
			    					
			    					
									
								}
			        	
			        		});	
				}
			});
			
        
        
        
        closeButton.addListener(new ButtonListenerAdapter()
        {
        	public void onClick(Button button, EventObject e)
        	{
        		vPanel.clear();
        	}
        });
        
        submitButton.addListener(new ButtonListenerAdapter()
        {
        	public void onClick(Button button, EventObject e)
        	{
        		if(userNameBox.getValue()==null||formNameBox.getValue()==null)
        		{
        			MessageBox.alert(msgs.AlertTitle(),msgs.InsertMandatory());
        		}
        		else
        		{
        			MessageBox.confirm(msgs.ConfirmationTitle(), msgs.Confirmation(), new MessageBox.ConfirmCallback()
        			{

						
						public void execute(String btnID) {
							if(btnID.matches("yes"))
							{
								serviceProxy.setFormAuthority(userNameBox.getValue(), formNameBox.getValue(), new AsyncCallback()
								{

									
									public void onFailure(Throwable arg0) {
										
									}

									
									public void onSuccess(Object result) {
									
									MessageBox.alert(msgs.SuccessTitle(), msgs.SuccessInsertMessage());	
									setFormAuthority();
									}
									
								});
							}
							
						}
        				
        			});
        		}
        	}
        });
        table.setWidget(0, 0, userNameLabel);
        table.setWidget(1, 0, formNameLabel);
        table.setWidget(0, 1, userNameBox);
        table.setWidget(1, 1, formNameBox);
        table.setWidget(2, 0, submitButton);
        table.setWidget(2, 1, closeButton);
        table.setCellPadding(6);
        table.setCellSpacing(6);
        form.add(table);
        vPanel.add(form);
        vPanel.add(vPanel2);
        
    }
    
    public void showRecords()
    {
    	serviceProxy.getFormAuthorityDetails(userID,new AsyncCallback<CM_ProgramInfoGetter[]>()
    			{

					
					public void onFailure(Throwable arg0) {
						
						
					}

					
					public void onSuccess(CM_ProgramInfoGetter[] result) {
						if(result.length>=1)
						{
							final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();   
 						   
 						   RecordDef recordDef = new RecordDef(   
 						           new FieldDef[]{   
 						        		   new StringFieldDef("userID"), 
 						                   new StringFieldDef("userName"),   
 						                   new StringFieldDef("formID") ,
 						                   new StringFieldDef("formName")
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
 									object[i][j]=result[i].getUserID();  
 								  }
 								  else if(j==1)
 								  {
 									 object[i][j]=result[i].getUserName();  
 								  }
 								 else if(j==2)
								  {
 									object[i][j]=result[i].getFormID();  
								  }
 								 else if(j==3)
								  {
 									object[i][j]=result[i].getFormName();  
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
					               
					               
					                new ColumnConfig("User Name", "userName", 110, true, null, "userName"),   
					                new ColumnConfig("Form Name", "formName", 110)
					               
					                
					               
					        };   
					  
					        ColumnModel columnModel = new ColumnModel(columns);   
					        grid.setColumnModel(columnModel);   
					  
					          
					        grid.setStripeRows(true);   
					        grid.setAutoExpandColumn("userName");   
					  
					        grid.setSelectionModel(cbSelectionModel);   
					        grid.setFrame(true);
					        grid.setWidth(900);   
					        grid.setHeight(300); 
					        grid.setTitle(constants.FARecords());   
					        grid.setStyleName("frmaGrid");
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
													param[0]=records[i].getAsString("userID");
													param[1]=records[i].getAsString("formID");
													
													serviceProxy.deleteFRMAuthority(param, new AsyncCallback<Integer>()
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
																		MessageBox.alert(msgs.error(), msgs.DeleteFailed())	;
																		
																	}
																	setFormAuthority();
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
							
						}
						
					}
    		
    			});
    }
}
