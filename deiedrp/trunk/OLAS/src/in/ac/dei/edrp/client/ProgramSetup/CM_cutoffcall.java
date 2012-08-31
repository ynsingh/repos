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
   Author Name :Arjun Singh
 */
package in.ac.dei.edrp.client.ProgramSetup;



import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.google.gwt.core.client.GWT;



import com.google.gwt.user.client.rpc.AsyncCallback;


import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;

import com.google.gwt.user.client.ui.Label;



import com.google.gwt.user.client.ui.VerticalPanel;

import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;


import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;




import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.SubjectCode;
import in.ac.dei.edrp.client.SystemTableTwo;

import in.ac.dei.edrp.client.RPCFiles.COS_DataService;
import in.ac.dei.edrp.client.RPCFiles.COS_DataServiceAsync;

import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.ArrayList;
import java.util.Date;


public class CM_cutoffcall {
	  

	  
   
    COS_DataServiceAsync cos_service=(COS_DataServiceAsync)GWT.create(COS_DataService.class);
    
    messages msgs = GWT.create(messages.class);
    constants constants = GWT.create(constants.class);
    public VerticalPanel vPanel = new VerticalPanel();
    
    private int counter;
    private int originalCounter;
    private Label[] categoryName;
    private String[] categoryCode;
    private Label[] genderName;
    private String[] genderCode;
    private NumberField[] xFactorField;
    private NumberField[] catSeatsField;
    
    private String eName;
    private String pName;
    private String sName;
    private Date session_sdate;
    private Date session_edate;
    private Integer totalSeats;
    Label EntityName=new Label(constants.EntityName());
    Label ProgramName=new Label(constants.ProgramName());
    Label SubjectName=new Label("Subject Name");
    Label TotalSeats=new Label("Total Seats");
    Label CategorySeats=new Label("Category Seats");
    Label Gender=new Label("Gender");
    Label Category=new Label("Category");
    Label XFactor=new Label("X Factor");
    Label programNameLabel = new Label(constants.label_programName());
   
    
   
    
  
   
  
    String cosvalue = "";
    int j;
    int k = 0;
   
    
    Validator valid = new Validator();
    String cos_code;
    String university_id;
    Integer temp_cos_seats;
    Integer temp_total_seats;
    String entityID=null;
    String programID=null;
    String categoryID=null;
    String genderID=null;
    String subjectCode=null;
    String xFactor=null;
    String categorySeats=null;
    // final DateField startField = new DateField();
    // final DateField endField = new DateField();
    

    public CM_cutoffcall(String uniid) {
        this.university_id = uniid;
    }

    public void callcutoff() {
        vPanel.clear();
        
        final FormPanel firstForm=new FormPanel();
        
        EntityName.setStyleName("myLabelStyle");
        ProgramName.setStyleName("myLabelStyle");
        SubjectName.setStyleName("myLabelStyle");
        TotalSeats.setStyleName("myLabelStyle");
        Category.setStyleName("myLabelStyle");
        Gender.setStyleName("myLabelStyle");
        CategorySeats.setStyleName("myLabelStyle");
        XFactor.setStyleName("myLabelStyle");
     
       
        
        
        FlexTable flexTable2 = new FlexTable();
     
     
        final DateField startField=new DateField();
        startField.setAllowBlank(false);
        final DateField endField=new DateField();
        endField.setAllowBlank(false);
        
      
        final Button submitButton = new Button(constants.submit());
        final Button closeButton = new Button("Close");
        
        final Button okButton = new Button(constants.okButton());
        final Button closeButton2 = new Button("Close");

       

       
       
        Label entityTypeLabel = new Label(constants.EntityType());
        final Label entityNameLabel = new Label(constants.EntityName());

        
        
      
       final FlexTable indiCOSTable=new FlexTable();
       final FlexTable defCOSTable=new FlexTable();
        
       final FormPanel setDefaultCOSPanel=new FormPanel();
       setDefaultCOSPanel.setTitle("Set Default COS Details");
       setDefaultCOSPanel.setVisible(false);
       setDefaultCOSPanel.setFrame(true);
       
        final FormPanel setIndividualCOSPanel=new FormPanel();
        setIndividualCOSPanel.setTitle("Set Individual COS Details");
        setIndividualCOSPanel.setVisible(false);
        setIndividualCOSPanel.setFrame(true);

       
        
        
        
	       
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
        
        cos_service.getEntityTypes(university_id, new AsyncCallback<CM_ProgramInfoGetter[]>()
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
        	
        
	      
	       
	       
	        final ComboBox programBox=new ComboBox();
	        programBox.setForceSelection(true);
	        programBox.setMinChars(1);
	        programBox.setDisplayField("programName");
	        programBox.setValueField("programID");
	        programBox.setMode(ComboBox.LOCAL);
	        programBox.setTriggerAction(ComboBox.ALL);
	        programBox.setEmptyText("Select Program Name");
	        programBox.setLoadingText("Searching...");
	        programBox.setTypeAhead(true);
	        programBox.setSelectOnFocus(true);
	        programBox.setHideTrigger(false);
	        programBox.setReadOnly(true);
	     
			       
			       final ComboBox subjectCodeBox=new ComboBox();
		           subjectCodeBox.setForceSelection(true);
			       subjectCodeBox.setMinChars(1);
			       subjectCodeBox.setDisplayField("subjectCodeDescription");
			       subjectCodeBox.setValueField("subjectCode");
			       subjectCodeBox.setMode(ComboBox.LOCAL);
			       subjectCodeBox.setTriggerAction(ComboBox.ALL);
			       subjectCodeBox.setEmptyText("Select Subject Code");
			       subjectCodeBox.setLoadingText("Searching...");
			       subjectCodeBox.setTypeAhead(true);
			       subjectCodeBox.setSelectOnFocus(true);
			       subjectCodeBox.setHideTrigger(false);
			       subjectCodeBox.setReadOnly(true);
			       
			       final Checkbox genderImpactBox=new Checkbox();
			       
			       
			       final NumberField xField=new NumberField();
			        xField.setAllowBlank(false);
			        xField.setAllowDecimals(false);
			        xField.setAllowNegative(false);
			       
			        
			        final NumberField seatsField=new NumberField();
			        seatsField.setAllowBlank(false);
			        seatsField.setAllowDecimals(false);
			        seatsField.setAllowNegative(false);
			       
		
			        entityTypeBox.addListener(new ComboBoxListenerAdapter()
				       {
				    	   public void onSelect(ComboBox comboBox, Record record, int index)
				    	   {
				    		 
				    		   entityNameBox.clearValue();
				    		   programBox.clearValue();
				    		   subjectCodeBox.clearValue();
				    		   
				    		   
				    		  cos_service.getEntityNames(university_id, entityTypeBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
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
			        
			       
			       
			        
			        closeButton.addListener(new ButtonListenerAdapter()
			        {
			        	public void onClick(Button button, EventObject e)
			        	{
			        		vPanel.clear();
			        	}
			        });
			        
	  entityNameBox.addListener(new ComboBoxListenerAdapter()
	  {
		  public void onSelect(ComboBox comboBox, Record record, int index)
		  {
			  programBox.clearValue();
   		   subjectCodeBox.clearValue();
   		   
			  String settings="D";
			  cos_service.getPrograms(entityNameBox.getValue(), settings, university_id, new AsyncCallback<CM_ProgramInfoGetter[]>()
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
					Store programStore=new SimpleStore(new String[]{"programID","programName"},object);
					programStore.load();
					programBox.setStore(programStore);
					

					
					
					
				}
				  
			  });
		  }
	  });
       
        
        
       
      

        programBox.addListener(new ComboBoxListenerAdapter()
        {
        	 public void onSelect(ComboBox comboBox, Record record, int index)
        	 {
        		 subjectCodeBox.clearValue();
         		   
        		 cos_service.getSubjectCodes(university_id,programBox.getValue(),
			               new AsyncCallback<SubjectCode[]>(){

							
							public void onFailure(Throwable caught) {
								MessageBox.alert("Sql Exception", caught.getMessage());
							}

							
							public void onSuccess(SubjectCode[] arg0) {
							
								  RecordDef recordDef = new RecordDef(new FieldDef[] {
										  new StringFieldDef("subjectCodeDescription"),
										  new StringFieldDef("subjectCode")
			                          });

								   Object[][] object1 = new String[arg0.length][2];

			                  String str = null;

			                  try {
			                      for (int i = 0; i < arg0.length; i++) {
			                          for (int k = 0; k < 2; k++) {
			                              if (k == 0) {
			                            	  str = arg0[i].getSubject_code_description();
			                              } else if (k == 1) {
			                                  str = arg0[i].getSubject_code();
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

			                 subjectCodeBox.setStore(store);
								
							}
			            	   
			               });
		        
        		cos_service.getTotalSeats(programBox.getValue(), entityNameBox.getValue(), new AsyncCallback<Integer>()
        		{

					
					public void onFailure(Throwable arg0) {
						
						
					}

				
					public void onSuccess(Integer result) {
						totalSeats=result;
						
					}
        			
        		}); 
        		 
        		 
        	 }
        });
        
        subjectCodeBox.addListener(new ComboBoxListenerAdapter()
        {
        	 public void onSelect(ComboBox comboBox, Record record, int index)
        	 {
        		 cos_service.getEntityName(university_id, entityNameBox.getValue(), new AsyncCallback<String>()
        	  			  {

        	  				
        	  				public void onFailure(Throwable arg0) {
        	  					// TODO Auto-generated method stub
        	  					
        	  				}

        	  				
        	  				public void onSuccess(String result) {
        	  					eName=result;
        	  					
        	  				}
        	  		  
        	  			  });
        		 cos_service.getProgramName(programBox.getValue(), new AsyncCallback<String>()
        	  			  {

        	  				
        	  				public void onFailure(Throwable arg0) {
        	  					// TODO Auto-generated method stub
        	  					
        	  				}

        	  				
        	  				public void onSuccess(String result) {
        	  					pName=result;
        	  					
        	  				}
        	  		  
        	  			  });
        		 
        		 cos_service.getSubjectName(university_id,subjectCodeBox.getValue(), new AsyncCallback<String>()
        	  			  {

        	  				
        	  				public void onFailure(Throwable arg0) {
        	  					// TODO Auto-generated method stub
        	  					
        	  				}

        	  				
        	  				public void onSuccess(String result) {
        	  					sName=result;
        	  					
        	  				}
        	  		  
        	  			  });
        		 
        	 }
        });
        
        

      okButton.addListener(new ButtonListenerAdapter()
      {
    	  public void onClick(Button button, EventObject e)
    	  {
    		  if(entityTypeBox.getValue()==null||entityNameBox.getValue()==null||programBox.getValue()==null||subjectCodeBox.getValue()==null)
    		  {
    			MessageBox.alert("Alert", "Please Insert Mandatory Fields");  
    		  }
    		  else
    		  {
    		  createInsertPanel(programBox.getValue(),entityNameBox.getValue(),subjectCodeBox.getValue(),genderImpactBox.getValue());
    		  }
    		  
    		  
    	  }
      });  
        
      closeButton2.addListener(new ButtonListenerAdapter()
      {
    	  public void onClick(Button button, EventObject e)
    	  {
    		 vPanel.clear();
    		 
    		  
    	  }
      }); 
       
      	FlexTable table=new FlexTable();
      	table.setCellPadding(6);
      	table.setCellSpacing(6);
        table.setWidget(0, 0, entityTypeLabel);
        table.setWidget(0, 2, entityNameLabel);
        table.setWidget(1, 0, programNameLabel);
        table.setWidget(1, 2, new Label("Subject Name"));
        table.setWidget(2, 0, new Label("Gender Impact"));
        table.setWidget(0, 1, entityTypeBox);
        table.setWidget(0, 3, entityNameBox);
        table.setWidget(1, 1, programBox);
        table.setWidget(1, 3, subjectCodeBox);
        table.setWidget(2, 1, genderImpactBox);
        table.setWidget(3, 0, okButton);
        table.setWidget(3, 1, closeButton2);
        
        firstForm.setTitle("Program COS");
        firstForm.setFrame(true);
        firstForm.add(table);
        vPanel.add(firstForm);
        
        
    }
/*
    public String getEntityName(String entityID)
    {
  	  cos_service.getEntityName(university_id, entityID, new AsyncCallback<String>()
  			  {

  				
  				public void onFailure(Throwable arg0) {
  					// TODO Auto-generated method stub
  					
  				}

  				
  				public void onSuccess(String result) {
  					eName=result;
  					
  				}
  		  
  			  });
  	  return eName;
    }
    
    public String getProgramName(String programID)
    {
  	  cos_service.getProgramName(programID, new AsyncCallback<String>()
  			  {

  				
  				public void onFailure(Throwable arg0) {
  					// TODO Auto-generated method stub
  					
  				}

  				
  				public void onSuccess(String result) {
  					pName=result;
  					
  				}
  		  
  			  });
  	  return pName;  
    }
     
    public String getSubjectName(String subjectCode)
    {
  	  cos_service.getSubjectName(university_id,subjectCode, new AsyncCallback<String>()
  			  {

  				
  				public void onFailure(Throwable arg0) {
  					// TODO Auto-generated method stub
  					
  				}

  				
  				public void onSuccess(String result) {
  					sName=result;
  					
  				}
  		  
  			  });
  	  return sName;   
    }
  */
   
   public void createInsertPanel(final String programID,final String entityID,final String subjectCode,boolean genderImpact)
   {	//vPanel.clear();
	   
	   final Button submitButton=new Button("Submit");
	   final Button submitButton2=new Button("Submit");
	   final Button closeButton=new Button("Close");
	   closeButton.addListener(new ButtonListenerAdapter()
	   {
		   public void onClick(Button button, EventObject e)
		   {
			callcutoff();   
		   }
	   });
	   submitButton.addListener(new ButtonListenerAdapter()
		{
			public void onClick(Button button, EventObject e)
			{
				ArrayList<String> seatList=new ArrayList<String>();
				ArrayList<String> xfList=new ArrayList<String>();
				ArrayList<String> catCodeList=new ArrayList<String>();
				ArrayList<String> genCodeList=new ArrayList<String>();
				int seatsEntered=0;
				int nullCounter=0;
			for(int i=0;i<counter;i++)
			{
				
			
				 if(((xFactorField[i].getValue()==null)&&(catSeatsField[i].getValue()!=null))||((xFactorField[i].getValue()!=null)&&(catSeatsField[i].getValue()==null)))
				{
					MessageBox.alert("Alert", msgs.InsertFullDetailsCG(categoryName[i].getText(), genderName[i].getText()));
					return;
					
				}
				else if(((xFactorField[i].getValue()!=null)&&(catSeatsField[i].getValue()!=null)))
				{
					seatList.add(catSeatsField[i].getValue().toString());
					xfList.add(xFactorField[i].getValue().toString());
					catCodeList.add(categoryCode[i]);
					genCodeList.add(genderCode[i]);
				}
				else
				{ 
				if(nullCounter==(counter-1))
				{
					MessageBox.alert(msgs.AlertTitle(),msgs.OneRecordToInsert());
					return;
				}
				else
				{
					nullCounter++;
				}
				}
			}

			originalCounter=seatList.size();
			final String[] xf=new String[originalCounter];
			final String[] seat=new String[originalCounter];
			final String[] category=new String[originalCounter];
			final String[] gender=new String[originalCounter];
			for(int i=0;i<originalCounter;i++)
			{
			seat[i]=seatList.get(i);
			xf[i]=xfList.get(i);
			category[i]=catCodeList.get(i);
			gender[i]=genCodeList.get(i);
			seatsEntered=Integer.parseInt(seat[i])+seatsEntered;
			}
		
			if(seatsEntered>totalSeats)
			{
				MessageBox.alert("Message", "Entered Number of Seats exceed the Maximum Seats.");
			}
			else
			{
				MessageBox.confirm("Confirmation", "Do You want to insert the Record", new MessageBox.ConfirmCallback()
				{

					
					public void execute(String btnID) {
						if(btnID.matches("yes"))
						{
							cos_service.getSessionDates(university_id, new AsyncCallback<CM_ProgramInfoGetter[]>()
									{

										
										public void onFailure(
												Throwable arg0) {
											
											
										}

										
										public void onSuccess(
												CM_ProgramInfoGetter[] result) {
											
											session_sdate=result[0].getSession_sdate();
											session_edate=result[0].getSession_edate();
									cos_service.setCOS(category, gender, subjectCode, seat, xf, entityID, programID, university_id,session_sdate, session_edate,counter, new AsyncCallback()
									{

										
										public void onFailure(Throwable arg0) {
											
											
										}

										
										public void onSuccess(Object result)
										{
											vPanel.clear();
											MessageBox.alert("Success", "Record Inserted Successfully");
											callcutoff();
											
										}
										
									});
												
											
											
											
										}
								
									});
						}
						
					}
					
				});
			}
						
							
							
												
				
		
			
	
			}
		});
	   submitButton2.addListener(new ButtonListenerAdapter()
		{
			public void onClick(Button button, EventObject e)
			{
				
				
				ArrayList<String> seatList=new ArrayList<String>();
				ArrayList<String> xfList=new ArrayList<String>();
				ArrayList<String> catCodeList=new ArrayList<String>();
				int seatsEntered=0;
				int nullCounter=0;
			for(int i=0;i<counter;i++)
			{
				if((i==counter)&&((xFactorField[i].getValue()==null)&&(catSeatsField[i].getValue()==null)))
				{
					MessageBox.alert("Alert", msgs.InsertMandatory());
					return;
				}
				else if(((xFactorField[i].getValue()==null)&&(catSeatsField[i].getValue()!=null))||((xFactorField[i].getValue()!=null)&&(catSeatsField[i].getValue()==null)))
				{
					MessageBox.alert("Alert", msgs.InsertFullDetailsC(categoryName[i].getText()));
					return;
					
				}
				else if(((xFactorField[i].getValue()!=null)&&(catSeatsField[i].getValue()!=null)))
				{
					seatList.add(catSeatsField[i].getValue().toString());
					xfList.add(xFactorField[i].getValue().toString());
					catCodeList.add(categoryCode[i]);
				}
				else
				{ 
				if(nullCounter==(counter-1))
				{
					MessageBox.alert(msgs.AlertTitle(),msgs.OneRecordToInsert());
					return;
				}
				else
				{
					nullCounter++;
				}
				}
			}

			originalCounter=seatList.size();
			final String[] xf=new String[originalCounter];
			final String[] seat=new String[originalCounter];
			final String[] category=new String[originalCounter];
			
			for(int i=0;i<originalCounter;i++)
			{
			seat[i]=seatList.get(i);
			xf[i]=xfList.get(i);
			category[i]=catCodeList.get(i);
			seatsEntered=Integer.parseInt(seat[i])+seatsEntered;
			}
					
			if(seatsEntered>totalSeats)
			{
				MessageBox.alert("Message", "Entered Number of Seats exceed the Maximum Seats.");
			}
			else
			{
				MessageBox.confirm("Confirmation", "Do You want to insert the Record", new MessageBox.ConfirmCallback()
				{

					
					public void execute(String btnID) {
						if(btnID.matches("yes"))
						{
							cos_service.getSessionDates(university_id, new AsyncCallback<CM_ProgramInfoGetter[]>()
									{

										
										public void onFailure(
												Throwable arg0) {
											
											
										}

										
										public void onSuccess(
												CM_ProgramInfoGetter[] result) {
											
											session_sdate=result[0].getSession_sdate();
											session_edate=result[0].getSession_edate();
									cos_service.setCOS_WithoutGender(category,  subjectCode, seat, xf, entityID, programID, university_id,session_sdate, session_edate,originalCounter, new AsyncCallback()
									{

										
										public void onFailure(Throwable arg0) {
											
											
										}

										
										public void onSuccess(Object result) {
											vPanel.clear();
											MessageBox.alert("Success", "Record Inserted Successfully");
											callcutoff();
											
										}
										
									});
												
											
											
											
										}
								
									});
						}
						
					}
					
				});	
			}
							
							
												
				
		
			
	
			}
		});
	   if(genderImpact==true)
	   {	
		 cos_service.checkCOSWithoutGender(entityID, programID,subjectCode, new AsyncCallback<Integer>()
				 {

					
					public void onFailure(Throwable caught) 
					{
						
						
					}

					
					public void onSuccess(Integer result) 
					{
						
					if(result>0)
					{
						MessageBox.alert(msgs.AlertTitle(), "COS already set OR It is set using without Gender Impact");
					}
					else
					{
						
						   cos_service.getCAT_GEN(university_id, programID, entityID, subjectCode, new AsyncCallback<CM_ProgramInfoGetter[]>()
								   {

									
									public void onFailure(Throwable arg0) {
										
										
									}

									
									public void onSuccess(CM_ProgramInfoGetter[] result) {
										if(result.length>0)
										{
											 vPanel.clear();
											counter=result.length;
											categoryName=new Label[counter];
											categoryCode=new String[counter];
											genderName=new Label[counter];
											genderCode=new String[counter];
											xFactorField=new NumberField[counter];
											catSeatsField=new NumberField[counter];
											try {
							                      for (int i = 0; i < result.length; i++) 
							                      {
							                    	  categoryCode[i]=new String(result[i].getComponentId());
							                    	  categoryName[i] = new Label(result[i].getComponent());
							                    	  genderCode[i]=new String(result[i].getComponent_id());
							                    	  genderName[i]=new Label(result[i].getComponentDescription());
							                    	  xFactorField[i]=new NumberField();
							                    	  catSeatsField[i]=new NumberField();
							                      }
							                  } catch (Exception e2) {
							                      System.out.println("e2     " + e2);
							                  }
											
											 FlexTable table=new FlexTable();
											   FlexTable table2=new FlexTable();
											   table2.setCellPadding(6);
											   table2.setCellSpacing(6);
											   table.setCellPadding(6);
											   table.setCellSpacing(6);
											   table2.setStyleName("detailsTable");
											   table2.setWidget(0, 0, EntityName);
											   table2.setWidget(0, 2,ProgramName);
											   table2.setWidget(1, 0, SubjectName);
											   table2.setWidget(1, 2, TotalSeats);
											   table2.setWidget(0, 1, new Label(eName));
											   table2.setWidget(0, 3, new Label(pName));
											   table2.setWidget(1, 1, new Label(sName));
											   table2.setWidget(1, 3, new Label(totalSeats.toString()));
											   table.setWidget(0, 0, Category);
											   table.setWidget(0, 1, Gender);
											   table.setWidget(0,2, CategorySeats);
											   table.setWidget(0, 3, XFactor);
											   for(int i=0;i<counter;i++)
											   {
												   if((i!=0)&&(!categoryCode[i].trim().equalsIgnoreCase(categoryCode[i-1])))
												   {
													   table.setWidget(i+1, 0, categoryName[i]);
												   }
												   else if(i==0)
												   {
													   table.setWidget(i+1, 0, categoryName[i]);  
												   }
												table.setWidget(i+1, 1, genderName[i]);   
												table.setWidget(i+1, 2, catSeatsField[i]);   
												table.setWidget(i+1, 3, xFactorField[i]);   
											   }
											   table.setWidget(counter+1, 0,submitButton);
											   table.setWidget(counter+1, 1,closeButton);
											   
											   FormPanel fp=new FormPanel();
											   fp.setTitle("Set Individual Program COS");
											   fp.setStyleName("indiForm");
											   fp.add(table2);
											   fp.add(table);
											   fp.setFrame(true);
											   vPanel.add(fp);
											
										}
										else
										{
											MessageBox.alert("Message", "COS already set for this Program");	
										}

										
										
									
									}

									
							   
								   });	
					}
					}
			 
				 });
		  
	   
	  
	   }
	   else if(genderImpact==false)
	   {
		   cos_service.checkCOSWithGender(entityID, programID,subjectCode, new AsyncCallback<Integer>()
				   {

					
					public void onFailure(Throwable caught)
					{
						
						
					}

					
					public void onSuccess(Integer result)
					{
						if(result>0)
						{
							MessageBox.alert(msgs.AlertTitle(), "COS already set OR It is set using with Gender Impact");
						}	
						else
						{
							  
								cos_service.getCAT(university_id, programID, entityID, subjectCode, new AsyncCallback<CM_ProgramInfoGetter[]>()
										{
												
											
											public void onFailure(Throwable arg0) {
												
											}

											
											public void onSuccess(CM_ProgramInfoGetter[] result) {

												if(result.length>0)
												{
													vPanel.clear();
													counter=result.length;
													categoryName=new Label[counter];
													categoryCode=new String[counter];
													xFactorField=new NumberField[counter];
													catSeatsField=new NumberField[counter];
													try {
									                      for (int i = 0; i < result.length; i++) 
									                      {
									                    	  categoryCode[i]=new String(result[i].getComponent_id());
									                    	  categoryName[i] = new Label(result[i].getComponentDescription());
									                    	  xFactorField[i]=new NumberField();
									                    	  catSeatsField[i]=new NumberField();
									                      }
									                  } catch (Exception e2) {
									                      System.out.println("e2     " + e2);
									                  }
													
													 FlexTable table=new FlexTable();
													   FlexTable table2=new FlexTable();
													   table2.setCellPadding(6);
													   table2.setCellSpacing(6);
													   table.setCellPadding(6);
													   table.setCellSpacing(6);
													   table2.setStyleName("detailsTable");
													   table2.setWidget(0, 0, EntityName);
													   table2.setWidget(0, 2,ProgramName);
													   table2.setWidget(1, 0, SubjectName);
													   table2.setWidget(1, 2, TotalSeats);
													   table2.setWidget(0, 1, new Label(eName));
													   table2.setWidget(0, 3, new Label(pName));
													   table2.setWidget(1, 1, new Label(sName));
													   table2.setWidget(1, 3, new Label(totalSeats.toString()));
													   table.setWidget(0, 0, Category);
													   table.setWidget(0,1, CategorySeats);
													   table.setWidget(0, 2, XFactor);
													   for(int i=0;i<counter;i++)
													   {
														   if((i!=0)&&(!categoryCode[i].trim().equalsIgnoreCase(categoryCode[i-1])))
														   {
															   table.setWidget(i+1, 0, categoryName[i]);
														   }
														   else if(i==0)
														   {
															   table.setWidget(i+1, 0, categoryName[i]);  
														   }
														  
														table.setWidget(i+1, 1, catSeatsField[i]);   
														table.setWidget(i+1, 2, xFactorField[i]);   
													   }
													   table.setWidget(counter+1, 0,submitButton2);
													   table.setWidget(counter+1, 1,closeButton);
													   
													   FormPanel fp=new FormPanel();
													   fp.setTitle("Set Individual Program COS");
													   fp.setStyleName("indiForm");
													   fp.add(table2);
													   fp.add(table);
													   fp.setFrame(true);
													   vPanel.add(fp);	
												}
												else
												{
													MessageBox.alert("Message", "COS already set for this Program");	
												}
												
											
											}
									
										});
						}
					}
			   
				   });
		    
	   }
   }
    
}
