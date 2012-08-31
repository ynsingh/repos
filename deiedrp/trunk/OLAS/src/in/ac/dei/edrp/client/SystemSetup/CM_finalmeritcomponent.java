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

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritDataService;
import in.ac.dei.edrp.client.RPCFiles.FinalMeritDataServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventCallback;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;


public class CM_finalmeritcomponent {
   FinalMeritDataServiceAsync serviceProxy=GWT.create(FinalMeritDataService.class);
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
   
    String userID;
   

    public CM_finalmeritcomponent(String userid) {
        this.userID = userid;
    }

    public void fianlmeritcomponent() 
    {
    	vPanel.clear();
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
        final ComboBox programNameBox=new ComboBox();
        programNameBox.setFieldLabel("Select Program");   
          
        programNameBox.setDisplayField("program_name");   
        programNameBox.setMode(ComboBox.LOCAL);   
        programNameBox.setTriggerAction(ComboBox.ALL);   
        programNameBox.setForceSelection(true);   
        programNameBox.setValueField("program_id");   
        programNameBox.setReadOnly(true);	
        
        Button createComponentButton=new Button("Create Merit Component");
			Button closeButton=new Button("Close");
	    	final NumberField maxMarksField=new NumberField();
	    	final NumberField weightagePercentageField=new NumberField();
	    	maxMarksField.setAllowBlank(false);
	    	maxMarksField.setAllowDecimals(false);
	    	maxMarksField.setAllowNegative(false);
	    	weightagePercentageField.setAllowBlank(false);
	    	weightagePercentageField.setAllowDecimals(true);
	    	weightagePercentageField.setAllowNegative(false);
	    	weightagePercentageField.setMaxValue(100);
	    	weightagePercentageField.setMinValue(0);
	    	final Checkbox check=new Checkbox();
	    	final Checkbox academicImpactCheck=new Checkbox();
    	
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
     		   programNameBox.clearValue();
     		   
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
     
 entityNameBox.addListener(new ComboBoxListenerAdapter()
 {
    public void onSelect(ComboBox comboBox, Record record, int index)
    {
    	
 	   programNameBox.clearValue();
 	   
 	   serviceProxy.getProgramNames(userID,entityNameBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
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
 					Store programStore=new SimpleStore(new String[]{"program_id","program_name"},object);
 					programStore.load();
 					programNameBox.setStore(programStore); 
 					
 				}
 		   
 			   	});
 	   			
    			}
 				});

	final ComboBox componentBox=new ComboBox();   
  componentBox.setForceSelection(true);
  componentBox.setMinChars(1);
  componentBox.setDisplayField("comp_name");
  componentBox.setValueField("comp_id");
  componentBox.setMode(ComboBox.LOCAL);
  componentBox.setTriggerAction(ComboBox.ALL);
  componentBox.setEmptyText("Select Merit Component");
  componentBox.setLoadingText("Searching...");
  componentBox.setTypeAhead(true);
  componentBox.setSelectOnFocus(true);
  componentBox.setHideTrigger(false);
 
 			programNameBox.addListener(new ComboBoxListenerAdapter()
 			{
 				public void onSelect(ComboBox comboBox, Record record, int index)
 				{
 					 serviceProxy.getFinalMeritComponents(userID,programNameBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
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
 		 		 					Store componentBoxStore=new SimpleStore(new String[]{"comp_id","comp_name"},object);
 		 		 					componentBoxStore.load();
 		 		 					componentBox.setStore(componentBoxStore); 
 		 		 				
 		 		 				
 		 							
 		 						}
 		 	        	
 		 	        		});	
 					
 				}
 			});
 			
 			componentBox.addListener(new ComboBoxListenerAdapter()
 			{
 				public void onSelect(ComboBox comboBox, Record record, int index)
 				{
 					if(componentBox.getValue().toString().trim().equalsIgnoreCase("AS"))
 					{
 						weightagePercentageField.setValue(0.0);
 						weightagePercentageField.setDisabled(true);
 					}
 					else
 					{
 						weightagePercentageField.setDisabled(false);
 					}
 				}
 			});
 			
 	    	
 	    	
 	       
 	        
 	        createComponentButton.addListener(new ButtonListenerAdapter()
 	        {
 	        	public void onClick(Button button, EventObject e)
 	        	{
 	        		if(maxMarksField.getValue()==null||weightagePercentageField.getValue()==null||componentBox.getValue()==null)
 	        		{
 	        			MessageBox.alert(msgs.ErrorTitle(), msgs.InsertMandatory());
 	        		}
 	        		else if(weightagePercentageField.getValue().intValue()>100||weightagePercentageField.getValue().intValue()<0)
 	        		{
 	        			MessageBox.alert(msgs.ErrorTitle(), msgs.WeightagePerLimit());
 	        		}
 	        		else
 	        		{
 	        			
 	            		final String attendanceImpact;
 	            		final String academicImpact;
 	            		if(check.getValue()==true)
 	            		{
 	            			attendanceImpact="Y";	
 	            		}
 	            		else
 	            		{
 	            			attendanceImpact="N";
 	            		}
 	            		
 	            		if(academicImpactCheck.getValue()==true)
 	            		{
 	            			academicImpact="Y";	
 	            		}
 	            		else
 	            		{
 	            			academicImpact="N";
 	            		}
 	            		
 	            		final String total_marks=maxMarksField.getValueAsString();
 	            		final String weightagePercentage=weightagePercentageField.getValueAsString();
 	            		serviceProxy.checkFMCDetails(programNameBox.getValue(), entityNameBox.getValue(), componentBox.getValue(), new AsyncCallback<Boolean>()
 	            				{

 	    							
 	    							public void onFailure(Throwable arg0) {
 	    								
 	    								
 	    							}

 	    							
 	    							public void onSuccess(Boolean result) 
 	    							{
 	    								if(result==false)
 	    								{
 	    									MessageBox.confirm(msgs.ConfirmationTitle(), msgs.Confirmation(), new MessageBox.ConfirmCallback()
 	    					        		{

 	    										
 	    										public void execute(String btnID) {
 	    											if(btnID.matches("yes"))
 	    											{
 	    												serviceProxy.insertFinalMeritComponent(programNameBox.getValue(), entityNameBox.getValue(), componentBox.getValue(), attendanceImpact, total_marks, userID, weightagePercentage,academicImpact, new AsyncCallback()
 	    												{

 	    													
 	    													public void onFailure(Throwable arg0) {
 	    														
 	    														
 	    													}

 	    													
 	    													public void onSuccess(Object arg0) {
 	    														MessageBox.alert(msgs.SuccessTitle(),msgs.SuccessInsertMessage());
 	    														fianlmeritcomponent();
 	    													}
 	    													
 	    												});
 	    											}
 	    										}
 	    					        			
 	    					        		});	
 	    								}
 	    								else
 	    								{
 	    									MessageBox.alert(msgs.ErrorTitle(),msgs.AlreadyExists());
 	    								}
 	    							}
 	            			
 	            				});
 	            	
 	        		}
 	        	}// createComponentButton onClick Ends
 	        });
 	       
 	        
 	        closeButton.addListener(new ButtonListenerAdapter()
 	        {
 	        	public void onClick(Button button, EventObject e)
 	        	{
 	        		vPanel.clear();
 	        	}
 	        });
 	        
 			FlexTable table=new FlexTable();
 			table.setWidget(0, 0, new Label(constants.EntityType()));
 			table.setWidget(0, 2, new Label(constants.EntityName()));
 			table.setWidget(1, 0, new Label(constants.ProgramName()));
 			table.setWidget(0, 1, entityTypeBox);
 			table.setWidget(0, 3, entityNameBox);
 			table.setWidget(1, 1, programNameBox);
 			table.setWidget(1, 2, new Label(constants.ComponentName()));
 	        table.setWidget(2, 0, new Label(constants.MaximumMarks()));
 	        table.setWidget(2, 2, new Label(constants.WeightagePercentage()));
 	        table.setWidget(3, 0, new Label(constants.AttendanceImpact()));
 	       // table.setWidget(4,0,new Label("Academic Impact"));
 	       // table.setWidget(0, 1, new Label("Program Value"));
 	        table.setWidget(1, 3, componentBox);
 	        table.setWidget(2, 1, maxMarksField);
 	        table.setWidget(2, 3, weightagePercentageField);
 	        table.setWidget(3, 1,check );
 	       // table.setWidget(4, 1, academicImpactCheck);
 	       
 	        table.setWidget(5, 0, createComponentButton);
 	       table.setWidget(5, 1, closeButton);
 	      table.setCellPadding(6);
 	     table.setCellSpacing(6);
 	     FormPanel fp=new FormPanel();
 	     fp.setStyleName("fmcStyle");
 	     table.setStyleName("fmcTableStyle");
 	     fp.setFrame(true);
 	     fp.setTitle(constants.FMC());
 	     fp.add(table);
 			vPanel.add(fp);
 			
 			
    }
    
}
