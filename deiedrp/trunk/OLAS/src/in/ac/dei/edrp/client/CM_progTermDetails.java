package in.ac.dei.edrp.client;

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


/**
 * @author Manpreet Kaur
 */

import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;
import in.ac.dei.edrp.client.Shared.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.FloatFieldDef;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.util.Format;
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
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

public class CM_progTermDetails {

	int termCount=0;
//	int numberOfTerms=0;
	HorizontalPanel HoriPanel=new HorizontalPanel();
	 final VerticalPanel VertiPanel = new VerticalPanel();
	   
	    final Button termSaveButton = new Button("Save");
	    final FlexTable tableFlexTable = new FlexTable();
	    
	    HorizontalPanel ProgTermHorizontalPanel = new HorizontalPanel();
	    public HorizontalPanel ProgTermHorizontalPanel1 = new HorizontalPanel();
	    FlexTable ProgTermflexTable = new FlexTable();
	    Validator validator=new Validator();
	
	    Object object1[][]=null;
	    
	    Store termStore=null;
	public String userid;

    /**
     * Constructor for setting the Value of User ID
     * @param Uid
     */
    public CM_progTermDetails(String Uid) {
        this.userid = Uid;
    }
    
    
    private final CM_connectAsync connectService = GWT.create(CM_connect.class);
    
    /**
     *Method to add details to the terms of program 
     */
	
	public void methodAddTermDetails()
    {  	
    	
 		termCount=1;
 		
    	FormPanel termDetailForm=new FormPanel();
    	HoriPanel.clear();
    	HoriPanel.add(termSaveButton);
    	
    	termDetailForm.setFrame(true);
    	termDetailForm.setBorder(true);
    	termDetailForm.setTitle("Term Details");
    	
    	Label termNumberLabel=new Label("Term Number ");            	
    	Label minSGPALabel=new Label("Minimum SGPA ");
    	Label termNameLabel=new Label("Term Name ");
    	Label teachingDaysLabel=new Label("Number of Teaching Days ");
    	Label durationLabel=new Label("Duration (in weeks) ");
    	Label termStartLabel=new Label("Term Start Date ");
    	Label termEndLabel=new Label("Term End Date ");
    	Label totalCreditsLabel=new Label("Total Credits ");
    	
    	final NumberField minSGPAText=new NumberField();
    	final TextField termNameText=new TextField();
    	final NumberField teachingDaysText=new NumberField();
    	final NumberField durationText=new NumberField();
    	final DateField startDate=new DateField();
    	final DateField endDate=new DateField();
    	final NumberField totalCreditsText=new NumberField();
    	final ComboBox programComboBox=new ComboBox();
    	final ComboBox termComboBox=new ComboBox();
    	
    	/*
    	 * setting validations for fields
    	 */
    	minSGPAText.setMaxLength(5);
    	teachingDaysText.setMaxLength(5);
    	durationText.setMaxLength(5);
    	totalCreditsText.setMaxLength(10);
    	minSGPAText.setAllowBlank(false);
    	termNameText.setAllowBlank(false);
    	teachingDaysText.setAllowBlank(false);
    	durationText.setAllowBlank(false);
    	startDate.setAllowBlank(false);
    	endDate.setAllowBlank(false);
    	totalCreditsText.setAllowBlank(false);
    	startDate.setReadOnly(true);
    	endDate.setReadOnly(true);
    	teachingDaysText.setAllowDecimals(false);
    	durationText.setAllowDecimals(false);
    	
    	/*
    	 * setting properties of program name list combobox 
    	 */
    	methodProgComboSettings(programComboBox);
	      
	      /*
	       * setting properties of term list combobox 
	       */
    	methodtermComboSettings(termComboBox);
	      
    	/*
    	 * populating values in <code>programComboBox</code>
    	 */
	      CM_progOfferedBy pob=new CM_progOfferedBy(userid);
	      pob.methodProgramPopulate(programComboBox);
	            
	      Label AddLabel=new Label();
    	AddLabel.setText("Add Programme Details");
    	AddLabel.setStyleName("Heading");
    	
    	 tableFlexTable.clear();
    	 tableFlexTable.setWidget(3,0, new Label("Program Name"));
         tableFlexTable.setWidget(3,1, programComboBox);
    	 tableFlexTable.setWidget(4,0, termNumberLabel);
         tableFlexTable.setWidget(4,1, termComboBox);
         tableFlexTable.setWidget(6,0, minSGPALabel);
         tableFlexTable.setWidget(6,1, minSGPAText);
         tableFlexTable.setWidget(5,0, termNameLabel);
         tableFlexTable.setWidget(5,1, termNameText);
         tableFlexTable.setWidget(8,0, teachingDaysLabel);
         tableFlexTable.setWidget(8,1, teachingDaysText);
         tableFlexTable.setWidget(7,0, durationLabel);
         tableFlexTable.setWidget(7,1, durationText);
         tableFlexTable.setWidget(9,0, termStartLabel);
         tableFlexTable.setWidget(9,1, startDate);
         tableFlexTable.setWidget(10,0, termEndLabel);
         tableFlexTable.setWidget(10,1, endDate);
         tableFlexTable.setWidget(11,0, totalCreditsLabel);
         tableFlexTable.setWidget(11,1, totalCreditsText);
   
         
         programComboBox.addListener(new ComboBoxListenerAdapter(){
        	 public void onSelect(ComboBox comboBox, Record record, int index) {
        		 
        		methodNumberOfTerms(comboBox.getValue(),termComboBox);
        	 }
         });
         
         
         /*
          * saving values of form into database
          */
         termSaveButton.addListener(new ButtonListenerAdapter(){
        	 public void onClick(Button addButton, EventObject e){
        		  Boolean flag = true;
	        	    
	        	    try{
	        	    	minSGPAText.validate();
	        	    }catch (Exception e1) {
						flag = false;
					}
	        	    try{
	        	    	teachingDaysText.validate();
	        	    }catch (Exception e1) {
						flag = false;
					}
	        	    try{
	        	    	durationText.validate();
	        	    }catch (Exception e1) {
						flag = false;
					}
	        	    try{
	        	    	startDate.validate();
	        	    }catch (Exception e1) {
						flag = false;
					}
	        	    try{
	        	    	endDate.validate();
	        	    }catch (Exception e1) {
						flag = false;
					}
	        	    try{
	        	    	totalCreditsText.validate();
	        	    }catch (Exception e1) {
						flag = false;
					}
	        	    try{
	        	    	termNameText.validate();
	        	    }catch (Exception e1) {
						flag = false;
					}
	    		    
	    		    if (validator.nullValidator(programComboBox.getRawValue())) {
	                     try {
	                    	 programComboBox.markInvalid(
	                             "");
	                     } catch (Exception e1) {
	                         flag = false;
	                     }

	                     flag = false;
	                 }
	    		    
	    		    if (validator.nullValidator(termComboBox.getRawValue())) {
	                     try {
	                    	 termComboBox.markInvalid(
	                             "");
	                     } catch (Exception e1) {
	                         flag = false;
	                     }

	                     flag = false;
	                 }
	    		    
	    		    if(flag==true){
	    		    	 final String dateSelected =
                             Format.date(startDate.getText(),
                                 "Y-m-d");
                         final String dateSelected1 =
                             Format.date(endDate.getText(),
                                 "Y-m-d");
	    		    	if (dateSelected.compareTo(
                                dateSelected1) > 0) {
                        MessageBox.show(new MessageBoxConfig() {

                                {
                                    setTitle(
                                        "Error");
                                    setMsg(
                                        "Last Date of Registration shoud be higher");
                                    setIconCls(MessageBox.WARNING);
                                    setButtons(MessageBox.OK);
                                    setCallback(new MessageBox.PromptCallback() {
                                            public void execute(
                                                String btnID,
                                                String text) {
                                            	try{
                                                startDate.markInvalid("Invalid");
                                            	}catch (Exception e) {
												}
                                            	try{
                                                endDate.markInvalid("Invalid");
                                            	}catch (Exception e) {
												}
                                            }
                                        });
                                }
                            });
                    }else
                    {
                    	CM_progTermDetailGetter object=new CM_progTermDetailGetter();
                    	
                    	int value=Integer.parseInt(termComboBox.getValue());
                		String value1=termComboBox.getValue();	
                		
                    	if(value/10 == 0){
    	           			value1 = ("0"+value);
                    	}
                    	String term_id=programComboBox.getValue().substring(4,7);
                    	
                    	term_id=term_id+value1;
                    
                    	
                    	object.setEntity_program_term_id(term_id);
                    	object.setProgram_id(programComboBox.getValue());
                    	object.setTerm_name(termNameText.getText());
                    	object.setMinimum_sgpa(minSGPAText.getText());
                    	object.setDuration_in_weeks(durationText.getText());
                    	object.setNumber_of_teaching_days(teachingDaysText.getText());
                    	object.setTerm_start_date(dateSelected);
                    	object.setTerm_end_date(dateSelected1);
                    	object.setTotal_credits(totalCreditsText.getText());
                    
                    	
                    	
                    	connectService.methodAddTermDetails(userid, object,new AsyncCallback<String>(){

							public void onFailure(Throwable arg0) {
								
							MessageBox.alert("Failure",arg0.getMessage());
							}

							public void onSuccess(String arg0) {
								MessageBox.alert("Congratulation","Details for term successfully added",new AlertCallback(){
									public void execute() {
								termComboBox.reset();
								try{
									minSGPAText.setValue("");
								}catch (Exception e) {}
								try{
									teachingDaysText.setValue("");
									}catch (Exception e) {}
									try{
										startDate.setValue("");
										}catch (Exception e) {}
									try{
										endDate.setValue("");
										}catch (Exception e) {}
									try{
										totalCreditsText.setValue("");
										}catch (Exception e) {}
									try{
										termNameText.setValue("");
										}catch (Exception e) {}
									try{
										durationText.setValue("");
									    }catch (Exception e) {}
								minSGPAText.clearInvalid();
								teachingDaysText.clearInvalid();
								startDate.clearInvalid();
								endDate.clearInvalid();
								totalCreditsText.clearInvalid();
								termNameText.clearInvalid();
								durationText.clearInvalid();
								
									}
									
								});
							}
                    		
                    	});
                    	
                    	
                    }
	    		    }else{
	    		    	MessageBox.alert("Error","Kindly Fill All the mandatory fields");
	    		    }
        	 }
         });
         
         
        
         termDetailForm.add(tableFlexTable);

         VertiPanel.clear();
          
    	   VertiPanel.add(AddLabel);
           VertiPanel.setSpacing(30);
           VertiPanel.add(termDetailForm);
           VertiPanel.setSpacing(20);
           VertiPanel.add(HoriPanel);
           
           ProgTermflexTable.clear();
           ProgTermflexTable.setWidget(0, 0, VertiPanel);

    	   ProgTermHorizontalPanel.clear();
    	   ProgTermHorizontalPanel.add(ProgTermflexTable);
    	   ProgTermHorizontalPanel1.clear();
           ProgTermHorizontalPanel1.add(ProgTermHorizontalPanel);
    	
    }
	

	/**
	 * @param program_id
	 * @param termComboBox
	 */
	public void methodNumberOfTerms(String program_id,final ComboBox termComboBox)
	{
		 connectService.methodNumberOfTerms(program_id, new AsyncCallback<Integer>(){

				public void onFailure(Throwable arg0) {
					MessageBox.alert("Failure",arg0.getMessage());
					
				}

				public void onSuccess(Integer arg0) {

					
				 final RecordDef rDef = new RecordDef(new FieldDef[] {
                   new StringFieldDef("no_of_terms")                    
               });
					 
				 object1 = new Object[arg0][1];
				 
				    for(int i=0;i<arg0;i++){
				    	object1[i][0]=i+1;
				    }
				  Object[][] data = object1;
				  MemoryProxy proxy = null;
				    proxy = new MemoryProxy(data);
			      ArrayReader reader = new ArrayReader(rDef);
			   termStore = new Store(proxy,reader);  
			termStore.load();
			termComboBox.setStore(termStore);
					 
				}
				
			});
	}
	
	
	public void methodProgComboSettings(ComboBox programComboBox){
		programComboBox.setForceSelection(true);
		 programComboBox.setMinChars(1);  
	      programComboBox.setDisplayField("program_name"); 
	      programComboBox.setValueField("program_id");
	      programComboBox.setMode(ComboBox.LOCAL);  
	      programComboBox.setTriggerAction(ComboBox.ALL);  
	      programComboBox.setEmptyText("Select Program");  
	      programComboBox.setLoadingText("Searching...");  
	      programComboBox.setTypeAhead(true);  
	      programComboBox.setSelectOnFocus(true);  
	      programComboBox.setWidth(190);  
	      programComboBox.setHideTrigger(false);
	}
	
	public void methodtermComboSettings(ComboBox termComboBox){
		 termComboBox.setMinChars(1);  
	      termComboBox.setDisplayField("no_of_terms");
	      termComboBox.setMode(ComboBox.LOCAL);  
	      termComboBox.setTriggerAction(ComboBox.ALL);  
	      termComboBox.setEmptyText("Select Program Term");  
	      termComboBox.setLoadingText("Loading...");  
	      termComboBox.setTypeAhead(true);  
	      termComboBox.setSelectOnFocus(true);  
	      termComboBox.setWidth(190);  
	      termComboBox.setHideTrigger(false);
	}

	
	
	
	
	/**
	 * Method for managing term details
	 */
	
	public void methodManageTermDetails(){
		
	 	   final Panel p1 = new Panel();
           final BorderLayoutData bd = new BorderLayoutData(RegionPosition.CENTER);
           bd.setMargins(6, 6, 6, 6);

       	final VerticalPanel manageProgVpanel =new VerticalPanel();
     	VerticalPanel containerVerticalPanel=new VerticalPanel();
    	  final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
    	    	FlexTable upperFlexTable=new FlexTable();
    	final VerticalPanel gridVPanel=new VerticalPanel();
    	Label heading=new Label("Manage Programs Terms");
    	heading.setStyleName("heading");
    	final ComboBox progCbox=new ComboBox();
    	final ComboBox termCbox=new ComboBox();
    	
    	
    	
    	methodProgComboSettings(progCbox);
    	methodtermComboSettings(termCbox);
    	
    	
    	upperFlexTable.clear();
    	upperFlexTable.setWidget(0,0,new Label("Choose Program "));
    	upperFlexTable.setWidget(0,10,progCbox);
    	upperFlexTable.setWidget(1,0,new Label("Choose Term "));
    	upperFlexTable.setWidget(1,10,termCbox);
    	
    	
    	CM_progOfferedBy pob=new CM_progOfferedBy(userid);
	      pob.methodProgramPopulate(progCbox);
    	
	      progCbox.addListener(new ComboBoxListenerAdapter(){
	        	 public void onSelect(ComboBox comboBox, Record record, int index) {
	        		 
	        		methodNumberOfTerms(comboBox.getValue(),termCbox);
	        	 }
	         });
         

    final Button okButton=new Button("OK");	
    	
    	
    
  
    final CM_progTermDetailGetter object=new CM_progTermDetailGetter();
		
		
		
		okButton.addListener(new ButtonListenerAdapter(){
   		 
			public void onClick(Button button, EventObject e) {
    			
    			  	
    			  	
    				final GridPanel grid = new GridPanel();
			
    				if(!validator.nullValidator(progCbox.getRawValue())){
    					
    				object.setProgram_id(progCbox.getValue());
        				
      				  if(!validator.nullValidator(termCbox.getRawValue())){  
      					  
      					int value=Integer.parseInt(termCbox.getValue());
                		String value1=termCbox.getValue();	
                		
                    	if(value/10 == 0){
    	           			value1 = ("0"+value);
                    	}
                    	String term_id=progCbox.getValue().substring(4,7);
                    	
                    	System.out.println(progCbox.getValue().substring(4,7));
                    	
                    	term_id=term_id+value1;
                    	                    	
                    	object.setEntity_program_term_id(term_id);
      			     
      			  }else
      			  {
      				object.setEntity_program_term_id("%");
      			  }

   				
   				 connectService.methodGetTermDetails(object,new AsyncCallback<CM_progTermDetailGetter[]>(){

					public void onFailure(Throwable arg0) {
						MessageBox.alert("Failure in getting program details",arg0.getMessage());
						
					}

					public void onSuccess(CM_progTermDetailGetter[] arg0) {
//						VerticalPanel p1Panel=new VerticalPanel();			 
//						final GridPanel grid = new GridPanel();
						 if (arg0.length == 0) {
						 grid.setTitle("No record found");
						  } else {
						    grid.setTitle("Program Details");
						  }
						 
						 final RecordDef rDef = new RecordDef(new FieldDef[] {
	                            new StringFieldDef("programName"),
	                            new StringFieldDef("termName"),
	                            new FloatFieldDef("sgpa"),
	                            new IntegerFieldDef("teachingDays"),
	                            new IntegerFieldDef("duration"),
	                            new StringFieldDef("startDate"),
	                            new StringFieldDef("endDate"),
	                            new FloatFieldDef("credits"),
	                            new StringFieldDef("programid"),
	                            new StringFieldDef("termid")
	                        });
							 
						 object1 = new Object[arg0.length][10];
						 
						                                         String str = null;
						
						                                         try {
						                                             for (int i = 0; i < arg0.length;
						                                                     i++) {
						                                            	
						                                                 for (int k = 0; k < 10; k++) {
						                                                     if (k == 0) {
						                                                         str = arg0[i].getProgram_name();
						                                                     } else if (k == 1) {
						                                                    	 str = arg0[i].getTerm_name();
						                                                     } else if (k == 2) {
						                                                    	 str = arg0[i].getMinimum_sgpa();
						                                                     } else if (k == 3) {
						                                                    	 str = arg0[i].getNumber_of_teaching_days();
						                                                     } else if (k == 4) {
						                                                    	 str = arg0[i].getDuration_in_weeks();
						                                                     } else if (k == 5) {
						                                                    	 str = arg0[i].getTerm_start_date();
						                                                     } else if (k == 6) {
						                                                    	 str = arg0[i].getTerm_end_date();
						                                                     } else if (k == 7) {
						                                                    	 str = arg0[i].getTotal_credits();
						                                                     } else if (k == 8) {
						                                                    	 str = arg0[i].getProgram_id();
						                                                     } else if (k == 9) {
						                                                    	 str = arg0[i].getEntity_program_term_id();
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
						                                                 new ColumnConfig("Program Name",
						                                                     "programName", 200, true, null,
						                                                     "programName"),
						                                                 new ColumnConfig("Term Name",
						                                                     "termName", 200, true, null,
						                                                     "termName"),
						                                                 new ColumnConfig("Minimum SGPA",
						                                                     "sgpa", 100, true,
						                                                     null, "sgpa"),
						                                                 new ColumnConfig("Number of Teaching Days",
						                                                     "teachingDays", 200, true, null,
						                                                     "teachingDays"),
						                                                 new ColumnConfig("Duration",
						                                                     "duration", 80, true, null,
						                                                     "duration"),
						                                                 new ColumnConfig("Start Date",
						                                                     "startDate", 150, true, null,
						                                                     "startDate"),
						                                                 new ColumnConfig("End Date",
						                                                     "endDate", 150, true, null,
						                                                     "endDate"),
						                                                 new ColumnConfig("Total Credits", "credits",
						                                                     80, true, null, "credits")
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
						 
						                  						 
						 ToolbarButton editButton = new ToolbarButton("Edit",
				                                                new ButtonListenerAdapter() {
			                                                    public void onClick(
			                                                        Button editButton,
			                                                        EventObject e) {
			                                                      
			                                                    	try{
			                                                        Record[] records =null;
			                                                        records = cbSelectionModel.getSelections();
		
			
			                                                        if (records.length < 1) {
			                                                            MessageBox.alert("Note",
			                                                                "Please select atleast one Record for Editing");
			                                                        } else if (records.length > 1) {
			                                                            MessageBox.alert("Note",
			                                                                "Please select only One Record at a time for editing");
			                                                        } else if (records.length == 1) {
			                                                            final Record record = records[0];
			                                                            
			                         		                                                          
						
			                                                            FlexTable editprogTable = new FlexTable();
			                                                            
			                                                            final NumberField minSGPAText=new NumberField();
			                                                        	final TextField termNameText=new TextField();
			                                                        	final NumberField teachingDaysText=new NumberField();
			                                                        	final NumberField durationText=new NumberField();
			                                                        	final DateField startDate=new DateField();
			                                                        	final DateField endDate=new DateField();
			                                                        	final NumberField totalCreditsText=new NumberField();
			                                                        	
			                                                          
			                                                        	/*
			                                                        	 * setting validations for fields
			                                                        	 */
			                                                        	minSGPAText.setMaxLength(5);
			                                                        	teachingDaysText.setMaxLength(5);
			                                                        	durationText.setMaxLength(5);
			                                                        	totalCreditsText.setMaxLength(10);
			                                                        	minSGPAText.setAllowBlank(false);
			                                                        	termNameText.setAllowBlank(false);
			                                                        	teachingDaysText.setAllowBlank(false);
			                                                        	durationText.setAllowBlank(false);
			                                                        	startDate.setAllowBlank(false);
			                                                        	endDate.setAllowBlank(false);
			                                                        	totalCreditsText.setAllowBlank(false);
			                                                        	startDate.setReadOnly(true);
			                                                        	endDate.setReadOnly(true);
			                                                        	teachingDaysText.setAllowDecimals(false);
			                                                        	durationText.setAllowDecimals(false);
			                                                           
			                                                           
			                                                           
			                                                           termNameText.setValue(record.getAsString("termName"));
			                                                           minSGPAText.setValue(record.getAsFloat("sgpa"));
			                                                           teachingDaysText.setValue(record.getAsInteger("teachingDays"));
			                                                           durationText.setValue(record.getAsInteger("duration"));
			                                                           startDate.setValue(record.getAsString("startDate"));
			                                                           endDate.setValue(record.getAsString("endDate"));
			                                                           totalCreditsText.setValue(record.getAsFloat("credits"));
			                                                           

			                                                           
			                                                           
			                                                            editprogTable.clear();
			                                                            editprogTable.setWidget(3, 0, new Label("Program Name : "));
			                                                            editprogTable.setWidget(3, 1, new Label(record.getAsString("programName")));
			                                                            editprogTable.setWidget(4, 0, new Label("Term Name : "));
			                                                            editprogTable.setWidget(4, 1, termNameText);
			                                                            editprogTable.setWidget(5, 0, new Label("Minimum SGPA : "));
			                                                            editprogTable.setWidget(5, 1, minSGPAText);
			                                                            editprogTable.setWidget(6, 0, new Label("Duration: "));
			                                                            editprogTable.setWidget(6, 1, durationText);
			                                                            editprogTable.setWidget(7, 0, new Label("Number of Teaching Days "));
			                                                            editprogTable.setWidget(7, 1, teachingDaysText);
			                                                            editprogTable.setWidget(8, 0, new Label("Start Date "));
			                                                            editprogTable.setWidget(8, 1, startDate);
			                                                            editprogTable.setWidget(9, 0,new Label("End Date "));
			                                                            editprogTable.setWidget(9, 1, endDate);
			                                                            editprogTable.setWidget(10, 0, new Label("Total Credits "));
			                                                            editprogTable.setWidget(10, 1, totalCreditsText);
			                                                          		                                                           
			                                                        
			                                                            p1.clear();
			                                                            p1.add(editprogTable);
			
			                                                            Button windowUpdateButton =
			                                                                new Button(
			                                                                    "Update");
			                                                            Button windowResetButton =
			                                                                new Button(
			                                                                    "Reset");
			                                                            final Window window = new Window();
			                                                            window.setTitle(
			                                                                "Program Term Details");
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
			                                                                    	
			                                                                    	termNameText.setValue(record.getAsString("termName"));
			 			                                                           minSGPAText.setValue(record.getAsFloat("sgpa"));
						                                                           teachingDaysText.setValue(record.getAsInteger("teachingDays"));
						                                                           durationText.setValue(record.getAsInteger("duration"));
						                                                           startDate.setValue(record.getAsString("startDate"));
						                                                           endDate.setValue(record.getAsString("endDate"));
						                                                           totalCreditsText.setValue(record.getAsFloat("credits"));
						                                                           
			                                                                    }
			                                                                });
			
			                                                            //Adding handler to update button of edit window 
			                                                            windowUpdateButton.addListener(new ButtonListenerAdapter() {
			                                                                    public void onClick(
			                                                                        Button button,
			                                                                        EventObject e) {
			                                                                    	  Boolean flag = true;
			                                                      	        	    
			                                                      	        	    try{
			                                                      	        	    	minSGPAText.validate();
			                                                      	        	    }catch (Exception e1) {
			                                                      						flag = false;
			                                                      					}
			                                                      	        	    try{
			                                                      	        	    	teachingDaysText.validate();
			                                                      	        	    }catch (Exception e1) {
			                                                      						flag = false;
			                                                      					}
			                                                      	        	    try{
			                                                      	        	    	durationText.validate();
			                                                      	        	    }catch (Exception e1) {
			                                                      						flag = false;
			                                                      					}
			                                                      	        	    try{
			                                                      	        	    	startDate.validate();
			                                                      	        	    }catch (Exception e1) {
			                                                      						flag = false;
			                                                      					}
			                                                      	        	    try{
			                                                      	        	    	endDate.validate();
			                                                      	        	    }catch (Exception e1) {
			                                                      						flag = false;
			                                                      					}
			                                                      	        	    try{
			                                                      	        	    	totalCreditsText.validate();
			                                                      	        	    }catch (Exception e1) {
			                                                      						flag = false;
			                                                      					}
			                                                      	        	    try{
			                                                      	        	    	termNameText.validate();
			                                                      	        	    }catch (Exception e1) {
			                                                      						flag = false;
			                                                      					}
			                                    
			                                                      	    		    
			                                                      	    		    
			                                                                        if(flag==true){
			                                                   	    		    	 final String dateSelected =
			                                                                             Format.date(startDate.getText(),
			                                                                                 "Y-m-d");
			                                                                         final String dateSelected1 =
			                                                                             Format.date(endDate.getText(),
			                                                                                 "Y-m-d");
			                                                	    		    	if (dateSelected.compareTo(
			                                                                                dateSelected1) > 0) {
			                                                                        MessageBox.show(new MessageBoxConfig() {

			                                                                                {
			                                                                                    setTitle(
			                                                                                        "Error");
			                                                                                    setMsg(
			                                                                                        "Last Date of Registration shoud be higher");
			                                                                                    setIconCls(MessageBox.WARNING);
			                                                                                    setButtons(MessageBox.OK);
			                                                                                    setCallback(new MessageBox.PromptCallback() {
			                                                                                            public void execute(
			                                                                                                String btnID,
			                                                                                                String text) {
			                                                                                            	try{
			                                                                                                startDate.markInvalid("Invalid");
			                                                                                            	}catch (Exception e) {
			                                                												}
			                                                                                            	try{
			                                                                                                endDate.markInvalid("Invalid");
			                                                                                            	}catch (Exception e) {
			                                                												}
			                                                                                            }
			                                                                                        });
			                                                                                }
			                                                                            });
			                                                                    }else
			                                                                    {
			                                                                    	CM_progTermDetailGetter object=new CM_progTermDetailGetter();
                                            	
			                                                                    	object.setEntity_program_term_id(record.getAsString("termid"));
			                                                                    	object.setProgram_id(record.getAsString("programid"));
			                                                                    	object.setTerm_name(termNameText.getText());
			                                                                    	object.setMinimum_sgpa(minSGPAText.getText());
			                                                                    	object.setDuration_in_weeks(durationText.getText());
			                                                                    	object.setNumber_of_teaching_days(teachingDaysText.getText());
			                                                                    	object.setTerm_start_date(dateSelected);
			                                                                    	object.setTerm_end_date(dateSelected1);
			                                                                    	object.setTotal_credits(totalCreditsText.getText());
			                                                                   
			                                                                    	connectService.methodUpdateTermDetails(userid, object, new AsyncCallback<String>(){

																						
																						public void onFailure(
																								Throwable arg0) {
																							 MessageBox.alert(
                                                                                                     "Failure",
                                                                                                     arg0.getMessage());
																							
																						}

																						@Override
																						public void onSuccess(
																								String arg0) {
																							MessageBox.alert(
                                                                                                    "Congratulation",
                                                                                                    "Program Term Details Successfully Updated",
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
			                                                                        }else
			                                                	    		    {
			                                                	    		    	MessageBox.alert("Error","Kindly Fill All the mandatory fields");
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
			                                                                "Error",
			                                                                "Please select a record for deletion");
			                                                    } else if (records.length > 1) {
			                                                        MessageBox.alert(
			                                                                "Error",
			                                                                "Please select only one record for deletion");
			                                                    }else {
			                                                        
			                                                    	final Record record = records[0];
			                                                    	final CM_progTermDetailGetter object=new CM_progTermDetailGetter();
			                                                    	
                                                                	object.setEntity_program_term_id(record.getAsString("termid"));
                                                                	object.setProgram_id(record.getAsString("programid"));
			                                                            MessageBox.show(
			                                                                    new MessageBoxConfig() {
			
			                                                                    {
			                                                                        setTitle(
			                                                                                "Confirm");
			                                                                        setMsg("Are you sure you want to delete this term's details?");
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
			                                                                                        connectService.methodDeleteProgTermDetail(
			                                                                                                object,
			                                                                                                new AsyncCallback<String>() {
			                                                                                                public void onFailure(Throwable arg0) {
			                                                                                                    MessageBox.alert(
			                                                                                                            "Failure",
			                                                                                                            arg0.getMessage());
			                                                                                                }
			
			                                                                                                public void onSuccess(String arg0) {
			                                                                                                    MessageBox.alert(
			                                                                                                            "Congratulation",
			                                                                                                            "Term Details Successfully deleted",
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
			                                            }));
			
			                                        grid.setTopToolbar(topToolBar);
						 
						 
						 
						 
						 
			                                        gridVPanel.clear();
			                                         gridVPanel.add(grid);
						 
					}
   				
   			}); 
			
			}else
				{
				MessageBox.alert("Error","Please select program");
				}
			}
			});
	
		manageProgVpanel.clear();
	 	
	 	manageProgVpanel.add(heading);
	 	manageProgVpanel.setSpacing(10);
	 	manageProgVpanel.setSpacing(20);
	 	manageProgVpanel.add(upperFlexTable);
	 	manageProgVpanel.add(okButton);
		
	 	containerVerticalPanel.clear();
	 	containerVerticalPanel.add(manageProgVpanel);
	 	containerVerticalPanel.add(gridVPanel);
	 	
	 	
	 	ProgTermHorizontalPanel.clear();
	 	ProgTermHorizontalPanel.setSpacing(20);
	 	ProgTermHorizontalPanel.add(containerVerticalPanel);
	 	ProgTermHorizontalPanel1.clear();
	 	ProgTermHorizontalPanel1.add(ProgTermHorizontalPanel);
	 	 
	      
	}

	
	
}
