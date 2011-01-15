package in.ac.dei.edrp.client;

import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;
import in.ac.dei.edrp.client.RPCFiles.CM_connectProgOfferedBy;
import in.ac.dei.edrp.client.RPCFiles.CM_connectProgOfferedByAsync;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.MessageBox.AlertCallback;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.layout.FitLayout;

public class CM_progOfferedBy {
	private final constants constant=GWT.create(constants.class);
	private final messages message=GWT.create(messages.class);

	   public VerticalPanel vPanel = new VerticalPanel();
	   HorizontalPanel hPanel = new HorizontalPanel();
	   Label heading = new Label(constant.labelAssignProgram());
	   VerticalPanel footerVerticalPanel = new VerticalPanel();
	   final Label footerLabel = new Label(constant.labelFooter());
	   CheckboxSelectionModel cbSelectionModel2 = new CheckboxSelectionModel();
	   
	   String[] entity = new String[3];
	   String programOfferedBy = "";
	   Validator validator = new Validator();

	   
	   final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
 
	 
	private final CM_connectProgOfferedByAsync connectProgOfferedByService = GWT.create(CM_connectProgOfferedBy.class);
	   private final CM_connectAsync connectService = GWT.create(CM_connect.class);
		  Object[][] object1;

		  String[][] object2;
	String user_id;
	
	Store progNameStore;
	 Store branchStore;
	 Store specStore;
	 public Store progListStore;
	
	 
	 
	
	 public CM_progOfferedBy(String Uid) {
	        this.user_id = Uid;
			 
	       }
	
	
	
	 public void methodAssignProg(){
				 
		final ComboBox programComboBox = new ComboBox(); 
		final ComboBox branchComboBox = new ComboBox();
		final ComboBox specializationComboBox = new ComboBox();
		
		methodProgramPopulate(programComboBox);
		 
	methodSetProgramProp(programComboBox);
	methodSetBranchProp(branchComboBox);
	methodSetSpecProp(specializationComboBox);
	      

	   	   heading.setStyleName("Heading1");
    	  
    	   footerVerticalPanel.add(footerLabel);
   		
   		final FlexTable programFlexTable =new FlexTable();
   		
//   		programComboBox.setStore(progNameStore);
		  
	    programComboBox.addListener(new ComboBoxListenerAdapter(){
	
			public void onSelect(ComboBox comboBox, Record record, int index) {


				 CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
				 object.setProgram_id(comboBox.getValue());
				 object.setBranchcode("000");
				methodBranchList(object,branchComboBox);
			
				   methodSpecList(object,specializationComboBox);
					branchComboBox.reset();
					specializationComboBox.reset();
   	        	           
				}	 		 		        	 
   	         });
	    
	    branchComboBox.addListener(new ComboBoxListenerAdapter(){
				  public void onSelect(ComboBox comboBox, Record record, int index) {

					  CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
						 object.setProgram_id(programComboBox.getValue());
						 object.setBranchcode(comboBox.getValue());
						   methodSpecList(object,specializationComboBox);
							  specializationComboBox.reset();
				  }	 		 		        	 
	            });		
	    
	
        
        programFlexTable.clear();
                    
    	programFlexTable.setWidget(10, 0, new Label(constant.programme()));
   		programFlexTable.setWidget(12, 0, new Label(constant.branch()));
   		programFlexTable.setWidget(14, 0, new Label(constant.specialization()));
   		
   		programFlexTable.setWidget(10, 1, programComboBox);
   		programFlexTable.setWidget(12, 1, branchComboBox);
   		programFlexTable.setWidget(14, 1, specializationComboBox);

   		   Button okButton = new Button(constant.okButton());
   		   		
           vPanel.add(programFlexTable); 
   		   vPanel.add(okButton);
   	     	   
    	   okButton.addListener(new ButtonListenerAdapter(){
		   public void onClick(Button button, EventObject e){
			   
			   CM_progMasterInfoGetter object=new CM_progMasterInfoGetter();
			  
			 	   
			   if(programComboBox.getRawValue().equalsIgnoreCase(""))
			   {
				   MessageBox.alert("Error",message.error_noprogram());
			   }else{
				   String spec="abc";
				   object.setProgram_id(programComboBox.getValue());
				   if(branchComboBox.getValueAsString().equalsIgnoreCase("")){
					   object.setBranchcode("%");
				   }else{
					   					   
					   object.setBranchcode(branchComboBox.getValue());
					   
					   if(specStore.getCount()==0){
						   spec="none";
					   }else{
						   spec="not null";
					   }
				   }
				   if(specializationComboBox.getValueAsString().equalsIgnoreCase("")){
					   
					   object.setSpecialization_code("%");
					   
				   }else{
					   object.setSpecialization_code(specializationComboBox.getValue());
				   } 
				   
				 
				   connectProgOfferedByService.methodBranchSpecList(object,spec,new AsyncCallback<CM_progMasterInfoGetter[]>(){
					public void onFailure(Throwable arg0) {
						MessageBox.alert(constant.failure(),arg0.getMessage());
						
					}

					public void onSuccess(final CM_progMasterInfoGetter[] arg0) {
												   
						 vPanel.clear();				   

				   
				         final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();		   
				         final RecordDef recordDef = new RecordDef(  
				                 new FieldDef[]{  
				                         new StringFieldDef("program"),  
				                         new StringFieldDef("branch"),  
				                         new StringFieldDef("specialization"),
				                         new StringFieldDef("branchcode"),  
				                         new StringFieldDef("specialization_code"),
  
				                 }  
				         );  
				   
				         final GridPanel grid1 = new GridPanel(); 		         
		    		   	 object1= new Object[arg0.length][5];
							 
				         String str = "";
				         
				   			for(int i=0 ; i < arg0.length ; i++){						
				            	for (int k = 0; k < 5; k++) {
				                    if (k == 0) {
				                        str = arg0[i].getProgram_name();
				                       
				                    } else if (k == 1) {
				                        str = arg0[i].getBranchname();
				                       
				                    } else if (k == 2) {
				                        str = arg0[i].getSpecname();
				                       
				                    } else if (k == 3) {
				                        str = arg0[i].getBranchcode();
				                       
				                    } else if (k == 4) {
				                        str = arg0[i].getSpecialization_code();
				                       
				                    } 
				                    
				            		 object1[i][k]=str;  

				                }
							};
								
						 Object[][] data = object1;	

				         MemoryProxy proxy = new MemoryProxy(data);  
				   
				         ArrayReader reader = new ArrayReader(recordDef);  
				         Store store = new Store(proxy, reader);  
				         store.load();  
				         grid1.setStore(store);  
				   
				         BaseColumnConfig[] columns = new BaseColumnConfig[]{  
				                 new CheckboxColumnConfig(cbSelectionModel),  
				               
				                 new ColumnConfig("Program", "program", 125, true, null, "program"),  
				                 new ColumnConfig("Branch", "branch", 150, true, null, "branch"),  
				                 new ColumnConfig("Specialization", "specialization", 188, true, null, "specialization"),  
 
				         };  
				         
				         			
				         ColumnModel columnModel = new ColumnModel(columns);		         
				         columnModel.setHidden(1, true);		         
				         grid1.setColumnModel(columnModel);  		         		   
				         grid1.setFrame(true);  
				         grid1.setStripeRows(true);
				   
				         grid1.setSelectionModel(cbSelectionModel);  
				         grid1.setWidth(350);  
				         grid1.setHeight(300);  
				         grid1.setFrame(true);  

				         grid1.setIconCls("grid-icon");
				        
				        
				         if(arg0.length==0){
				        	 grid1.setTitle(constant.titleProgramSpecification()); 
				         }else{
				         grid1.setTitle(constant.titleProgramBranchSpecification());		   
				         }
				         final HorizontalPanel buttonPanel = new HorizontalPanel();
				         
				         final Button okButton = new Button(constant.okButton());
				         
				   		   Button backButton = new Button(constant.backButton());
				    	   backButton.addListener(new ButtonListenerAdapter(){
				    		   public void onClick(Button button, EventObject e){
				    			   
//				    			   String progvalue=programComboBox.getValue();
//				    			   String branchvalue=branchComboBox.getValue();
//				    			   String specvalue=specializationComboBox.getValue();
				    			   
				    			   
				    			   methodAssignProg();

				    			   
				    		   }
				    	   });
				           	 
				    	   
				            vPanel.clear();
					        vPanel.add(heading);
					        vPanel.add(grid1);
					        buttonPanel.add(okButton);
					        buttonPanel.setSpacing(20);
					        buttonPanel.add(backButton);
					        vPanel.add(buttonPanel);
					        vPanel.add(footerLabel);
//					        vPanel.add(panel);
				         
				  okButton.addListener(new ButtonListenerAdapter() {  
				        public void onClick(Button addButton, EventObject e) {  
				        	if(cbSelectionModel.getSelections().length>0 || (arg0.length==0))	
				        	{
				                final VerticalPanel collegePanel = new VerticalPanel();
				                final FlexTable collegeCenterFlextable = new FlexTable();
				                final ComboBox entityTypeCombo=new ComboBox();
				                final ComboBox collegeCenterSelect = new ComboBox();
				                final ComboBox mentorComboBox = new ComboBox();
//				                	  mentorListBox.addItem("Select");
				   		
				   		methodSetEntityNameProp(collegeCenterSelect);
				   		methodSetEntityTypeProp(entityTypeCombo);
				        
				        methodEntityListPopulate(entityTypeCombo);
				        
				   		
				        entityTypeCombo.addListener(new ComboBoxListenerAdapter(){
				        	   public void onSelect(ComboBox cb, Record rd, int index) {

		                            final String criteria = "name";
		                            String entityType=cb.getValue();
		                            connectService.methodPopulateEntitySuggest(user_id,
		                                entityType, criteria,
		                                new AsyncCallback<CM_entityInfoGetter[]>() {
		                                    public void onFailure(Throwable arg0) {
		                                        MessageBox.alert(message.error(),
		                                            arg0.getMessage());
		                                    }

		                                    public void onSuccess(
		                                        CM_entityInfoGetter[] arg0) {
		                                    	RecordDef recordDef = new RecordDef(new FieldDef[] {
					                                    new StringFieldDef("entity_name"),
					                                    new StringFieldDef("entity_id")
					                                });

					                        object2 = new String[arg0.length][2];

					                        String[][] data = object2;

					                        for (int i = 0; i < arg0.length; i++) {
					                        	for(int k=0;k<2;k++){
					                        	if(k==0){
					                        		  object2[i][0] = arg0[i].getEntity_name();
					                        	}else
					                        	{
					                        		object2[i][1] = arg0[i].getEntity_id();
					                        		System.out
															.println(object2[i][1]+"   "+arg0[i].getEntity_id());
					                        	}
					                        	}
					                          
					                        }

					                        MemoryProxy proxy = new MemoryProxy(data);

					                        ArrayReader reader = new ArrayReader(recordDef);
					                        Store store = new Store(proxy, reader);
					                        store.load();

					                        collegeCenterSelect.setStore(store);
		                                    }
		                                });
				        	   }
				        });

				      
					    
					    mentorComboBox.setMinChars(1);  
					    mentorComboBox.setFieldLabel("Entity"); 
		         	    mentorComboBox.setDisplayField("name");
		          		mentorComboBox.setValueField("ID"); 
					    mentorComboBox.setMode(ComboBox.LOCAL);  
					    mentorComboBox.setTriggerAction(ComboBox.ALL);  
					    mentorComboBox.setEmptyText(constant.searchMentor());  
					    mentorComboBox.setLoadingText(constant.searching());  
					    mentorComboBox.setTypeAhead(true);  
					    mentorComboBox.setSelectOnFocus(true);  
					    mentorComboBox.setWidth(200);  
					    mentorComboBox.setHideTrigger(false);
					    mentorComboBox.setForceSelection(true);

					    
				    connectProgOfferedByService.methodMentorPopulate(user_id,new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
		  					public void onFailure(Throwable arg0) {
		  						MessageBox.alert(message.error(), constant.dbError() + arg0);
		  					}
		  					public void onSuccess(CM_BranchSpecializationInfoGetter[] result) {

		  				         @SuppressWarnings("unused")
								String fullName[] = new String[result.length];
		  				         String[] name = new String[3];
		  				          				         
		  				         RecordDef recordDef = new RecordDef(  
						                 new FieldDef[]{  
						                         new StringFieldDef("name"),  
						                         new StringFieldDef("ID"),
						                 }  
						         ); 
						         
						         object1= new Object[result.length][2];
						         Object[][] data = object1;			 
								 for(int i=0;i<result.length;i++){						
//					             		object1[i][0]=result[i].getEntity_name();  
					 					
			  			            	for (int k = 0; k < 4; k++){
			  			                    if (k == 0) {
			  			                    	name[0] = new String();
			  			                        name[0] = result[i].getFirst_name();
//			  			                        System.out.println("first name is: "+ name[0]);
			  			                    } else if (k == 1) {
			  			                    	name[1] = new String();
			  			                        name[1] = result[i].getMiddle_name();
//			  			                        System.out.println("mid name is: "+ name[1]);
			  			                    } else if (k == 2) {
			  			                    	name[2] = new String();
			  			                    	name[2] = result[i].getLast_name();
//			  			                        System.out.println("last name is: "+ name[2]);
			  			                    } 
			  			            	 }
			  			            		 
			  			            		 object1[i][0] = name[0]+" "+ name[1] +" "+ name[2];
			  			            		 object1[i][1] = result[i].getEntity_employee_id(); 
			  			            						 			             		
								 }
						         
								 MemoryProxy proxy = new MemoryProxy(data);  
				 		         ArrayReader reader = new ArrayReader(recordDef);  
				 		         Store store = new Store(proxy, reader);  
				 		         store.load();  
					
				 		         mentorComboBox.setStore(store);  

		  		 			}
		  		 	}); 
				    
				                final NumberField seatsNumberField = new NumberField();
				                seatsNumberField.setAllowDecimals(false);
				                seatsNumberField.setAllowBlank(false);
				                seatsNumberField.setAllowNegative(false);
				                seatsNumberField.setMaxLength(3);
				                final ComboBox locationComboBox = new ComboBox();
				     
				                
				                locationComboBox.setMinChars(1); 
				                locationComboBox.setDisplayField("state");
				                locationComboBox.setValueField("id");
				                locationComboBox.setMode(ComboBox.LOCAL);  
				                locationComboBox.setTriggerAction(ComboBox.ALL);  
				                locationComboBox.setEmptyText(constant.locationSearch());  
				                locationComboBox.setLoadingText(constant.searching());  
				                locationComboBox.setTypeAhead(true);  
				                locationComboBox.setSelectOnFocus(true);  
				                locationComboBox.setWidth(200);  
				                locationComboBox.setHideTrigger(false);
				                locationComboBox.setForceSelection(true);
						 		
				                
				                methodLocationPopulate(locationComboBox);
				                
					         
				                
                
				                		                
				                Button saveButton = new Button("SAVE");
				                
				                
				                collegeCenterFlextable.setWidget(1, 0, new Label(constant.selectEntityType()));
				                collegeCenterFlextable.setWidget(1, 1, entityTypeCombo);
				                collegeCenterFlextable.setWidget(2, 0, new Label(constant.selectEntity()));
				                collegeCenterFlextable.setWidget(2, 1, collegeCenterSelect);
				                collegeCenterFlextable.setWidget(3, 0, new Label(constant.selectMentor()));
				                collegeCenterFlextable.setWidget(3, 1, mentorComboBox);
				                collegeCenterFlextable.setWidget(4, 0, new Label(constant.selectSeats()));
				                collegeCenterFlextable.setWidget(4, 1, seatsNumberField);
				                collegeCenterFlextable.setWidget(5, 0, new Label(constant.selectLocation()));
				                collegeCenterFlextable.setWidget(5, 1, locationComboBox);		                
				                collegeCenterFlextable.setWidget(6, 1, saveButton);
				                	       
				       saveButton.addListener(new ButtonListenerAdapter(){ 
				            public void onClick(Button addButton, EventObject e){
				            	
				            	String collegeCenter = "";
				            	String location = "";
				            	String seats = "";
				            	String mentor = "";
				            	
					        	   Boolean flag = true;
					        	    
					        	    try{
					        	    	seatsNumberField.validate();
					        	    }catch (Exception e1) {
										flag = false;
									}

					    		    
					    		    if (validator.nullValidator(collegeCenterSelect.getRawValue())) {
					                     try {
					                    	 collegeCenterSelect.markInvalid(
					                             "");
					                     } catch (Exception e1) {
					                         flag = false;
					                     }

					                     flag = false;
					                 }
					    		    
					    		    if (validator.nullValidator(mentorComboBox.getRawValue())) {
					                     try {
					                    	 mentorComboBox.markInvalid(
					                             "");
					                     } catch (Exception e1) {
					                         flag = false;
					                     }

					                     flag = false;
					                 }
					    		    
					    		    if (validator.nullValidator(locationComboBox.getRawValue())) {
					                     try {
					                    	 locationComboBox.markInvalid(
					                             "");
					                     } catch (Exception e1) {
					                         flag = false;
					                     }

					                     flag = false;
					                 }
					    		    
//					    		    try{
//					    		    	locationComboBox.validate();
//					    		    }catch (Exception e4) {
//										flag = false;
//									}
					    		    
					        	    if(flag == true){
					        	    
					        	    try{
					        	    	
				            	Record[] records = cbSelectionModel.getSelections();  	
				            	
				            	 collegePanel.clear();
				            	 collegeCenterFlextable.clear();	    		     
			                     
			    		           if(records.length < 1 && arg0.length!=0){
			    		        	   MessageBox.alert(message.error(), message.alertOnAssign());
			    		           }
//			    		           else if(records.length > 1){
//			    		        	   MessageBox.alert("Note", "Please select only One Record at a time to assign it to entity*");
//			    		           }
			    		           else {	 
			                     
			                     
			                     /**
			                      * check here if grid is blank then set branch & spec as zero
			                      */
			                     
			                     String programCode=programComboBox.getValue();
			                     String[] branchCode=null;
			                     String[] specCode=null;
			                     
			                     if(arg0.length==0){
			                    	 branchCode=new String[1];
			                    	 specCode=new String[1];
			                    	 branchCode[0]="000";
				                     specCode[0]="000";
			                     }else{
			                    	 branchCode=new String[records.length];
			                    	 specCode=new String[records.length];
			                     }
			                     
			                    
			                     
			                    for(int i=0;i<records.length;i++) {
			    		        	   Record record = records[i];	    		        	   
			    		        	   		    		        	 
			    		        	   try{
			    		        		   branchCode[i] = new String();
			    		        		   branchCode[i] = record.getAsString("branchcode");
			    		        	   			    		        	   
			    		        		   specCode[i] = new String();
			    		        		   specCode[i] = record.getAsString("specialization_code");
			    		        	   // System.out.println("3");
			    		        	  
			    		        	   // System.out.println("prgrams etc are: " + entity[0] +entity[1]+ entity[2]);
			    		        	   }catch (Exception e1) {
											// System.out.println(e1);
										}	    		           
			    		           }           
				            	
				            	collegeCenter = collegeCenterSelect.getValue();
				            	location = locationComboBox.getValue();
				            	seats = seatsNumberField.getText();
				            	mentor = mentorComboBox.getValue();
				            	

				            	
				            	 System.out.println(collegeCenter);
				            	 System.out.println(location);
				            	 System.out.println(seats);
				            	 System.out.println(mentor);
				            	
				            	// System.out.println(entity[0]);
				            	// System.out.println(entity[1]);
				            	// System.out.println(entity[2]);
				            	
				            	
				            	
				 		     connectProgOfferedByService.methodAssignProgramsToEntity(programCode, branchCode, specCode, 
				 		    		 		collegeCenter, location, seats, mentor,user_id,
				 		    		 new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
				  					public void onFailure(Throwable arg0) {
				  						MessageBox.alert(message.error(), constant.dbError() + arg0);
				  					
				  					}
				  					public void onSuccess(CM_BranchSpecializationInfoGetter[] result) {
				  						MessageBox.alert("Success", message.alert_successfulentry(),new AlertCallback(){
											public void execute() {
												methodAssignProg();
												System.out
														.println("solved");
											}
				  							
				  						});			  		 		         
				  		 			}
				 		     });
				            	
					        	    }
			    		           
					        	    }catch (Exception e3) {
										System.out.println("Exception e3 "+e3);
								     }
				        	    }
			 				       else {
							        	   MessageBox.alert(message.error(), message.checkFields());
							       }     
				                vPanel.add(collegePanel); 				 		     		           	  
				            }
					        	    
					        	    
				            });
				       
				       
				                
				       			Label assignLabel = new Label(constant.assignEntityProgram());
				       			assignLabel.setStyleName("Heading1");
				       
				                vPanel.clear();
//				                collegePanel.clear();
//				                collegeCenterFlextable.clear();
				                vPanel.add(grid1);
				                vPanel.add(buttonPanel);
				                collegePanel.add(assignLabel);
				                collegePanel.add(collegeCenterFlextable);
				                footerVerticalPanel.clear();
				                vPanel.add(collegePanel);
				                footerVerticalPanel.add(footerLabel);
				                vPanel.add(footerVerticalPanel);		                
				            }  else{
				            	MessageBox.alert(message.error(),message.alertOnSelect());
				            }
				  }
				        });  
					  
						
						
					}
					   
				   });
				   
				   
				   
				   
				   
				   
				   
			   }
		
			   
		   }
	   });
    	   
    	   	  vPanel.clear();
    	      vPanel.add(heading);
    	   	  vPanel.add(programFlexTable);
	          vPanel.add(okButton);
	          vPanel.setSpacing(50);
	          vPanel.add(footerVerticalPanel);
	    
	      
	 }
	 
	 
	 
	 
	/*
	 * Method to populate Entity type list 
	 */
	 public void methodEntityListPopulate(final ComboBox entityTypeCombo)
	 {
		 connectService.methodEntityList(user_id,
	                new AsyncCallback<CM_entityInfoGetter[]>() {
	                    public void onFailure(Throwable arg0) {
	                        MessageBox.alert("Error", arg0.getMessage());
	                    }

	                    public void onSuccess(CM_entityInfoGetter[] arg0) {
	                        RecordDef recordDef = new RecordDef(new FieldDef[] {
	                                    new StringFieldDef("entity_type")
	                                });

	                        object2 = new String[arg0.length][1];

	                        String[][] data = object2;

	                        for (int i = 0; i < arg0.length; i++) {
	                        	
	                            object2[i][0] = arg0[i].getEntity_name();
	                        }

	                        MemoryProxy proxy = new MemoryProxy(data);

	                        ArrayReader reader = new ArrayReader(recordDef);
	                        Store store = new Store(proxy, reader);
	                        store.load();

	                        entityTypeCombo.setStore(store);
	                    }
	                });
	 }
	 
	/*
	 * Method to populate location list 
	 */
	 
	 public void methodLocationPopulate(final ComboBox locationComboBox){
		 
		    connectProgOfferedByService.methodLocationPopulate(new AsyncCallback<CM_employeeInfoGetter[]>() {
				public void onFailure(Throwable arg0) {
					MessageBox.alert(message.error(), constant.dbError() + arg0);
				}
				public void onSuccess(CM_employeeInfoGetter[] result) {		
			         RecordDef recordDef = new RecordDef(  
			                 new FieldDef[]{  
			                         new StringFieldDef("state"),
			                         new StringFieldDef("id"),
			                 }  
			         ); 
			         
			         object1 = new Object[result.length][25];
			         Object[][] data = object1;			 
					 for(int i=0; i < result.length; i++){	
							for(int k=0;k<2;k++){
	                        	if(k==0){
	                        		  object1[i][0] = result[i].getLocation_name();
	                        	}else
	                        	{
	                        		object1[i][1] = result[i].getLocation_id();
	                        		
	                        	}
	                        	}
		             		
					 }
			         
					 MemoryProxy proxy = new MemoryProxy(data);  
	 		         ArrayReader reader = new ArrayReader(recordDef);  
	 		         Store store = new Store(proxy, reader);  
	 		         store.load();  
		
	 		         locationComboBox.setStore(store);  
	 			}
    }); 
	 }
	 
/*
 * method for populating program list	 
 */
	 
	 public void methodProgramPopulate(final ComboBox programComboBox){
		 connectService.methodprogList(user_id,new AsyncCallback<CM_progMasterInfoGetter[]>(){

				public void onFailure(Throwable arg0) {
					MessageBox.alert(constant.failure(),arg0.getMessage());
					
				}

				public void onSuccess(CM_progMasterInfoGetter[] result) {
					try{
					
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
	           
	            MemoryProxy proxy = new MemoryProxy(data);

	            ArrayReader reader = new ArrayReader(recordDef);
	            progNameStore = new Store(proxy, reader);
	            progNameStore.load();
	          
	            programComboBox.setStore(progNameStore);
	    		
				}catch (Exception e) {
					System.out.println("Exception in prog list "+e);
				}
				}
	    	});
	 }
		
	 
	 
	 
	 
	 
	 /*
	  * method populating specialization list
	  */
	 
	 public  void methodSpecList(CM_progMasterInfoGetter object,final ComboBox specializationComboBox){
		  connectProgOfferedByService.methodProgSpecList(object, new AsyncCallback<CM_progMasterInfoGetter[]>(){

				public void onFailure(Throwable arg0) {
					MessageBox.alert(message.failure(),arg0.getMessage());
					
				}

				public void onSuccess(CM_progMasterInfoGetter[] arg0) {
					
					 final RecordDef rDef = new RecordDef(new FieldDef[] {
                       new StringFieldDef("specname"),
                       new StringFieldDef("speccode")                        
                   });
						 
					 object1 = new Object[arg0.length][2];
					 
					                                         String str = null;
					
					                                         
					                                             for (int i = 0; i < arg0.length;
					                                                     i++) {
					                                            	
					                                                 for (int k = 0; k < 2; k++) {
					                                                     if (k == 0) {
					                                                    	 str = arg0[i].getSpecname();
					                                                     } else if (k == 1) {
					                                                    	 str = arg0[i].getSpecialization_code();
					                                                     }
					 
					                                                     object1[i][k] = str;
					                                                 }
					                                             }
					                                      
					 
					                                         Object[][] data = object1;
			
					                                         MemoryProxy proxy = null;
					 
					                                        
					                                             proxy = new MemoryProxy(data);
					                                         
			
					                                         ArrayReader reader = new ArrayReader(rDef);
					                                         
					                                          specStore = new Store(proxy,reader);  
					                                                 
					                                          specStore.load();
					                                          specializationComboBox.setStore(specStore);
				
				}
				 
			 });
		 
	 }
	 
	 
	 /*
	  * method populating branch list
	  */
	 
	 public void methodBranchList(CM_progMasterInfoGetter object,final ComboBox branchComboBox){
		 String criteria="id";
		 connectService.methodProgBranchDetailsForManage(user_id, object,criteria,new AsyncCallback<CM_progMasterInfoGetter[]>(){

				public void onFailure(Throwable arg0) {
					MessageBox.alert(message.failure(),arg0.getMessage());
					
				}

				public void onSuccess(CM_progMasterInfoGetter[] arg0) {
					
					 final RecordDef rDef = new RecordDef(new FieldDef[] {
                      new StringFieldDef("branchname"),
                      new StringFieldDef("branchcode")                        
                  });
						 
					 object1 = new Object[arg0.length][2];
					 
					                                         String str = null;
					
					                                         try {
					                                             for (int i = 0; i < arg0.length;
					                                                     i++) {
					                                            	
					                                                 for (int k = 0; k < 2; k++) {
					                                                     if (k == 0) {
					                                                    	 str = arg0[i].getBranchname();
					                                                     } else if (k == 1) {
					                                                    	 str = arg0[i].getBranchcode();
					                                                     }
					 
					                                                     object1[i][k] = str;
					                                                 }
					                                             }
					                                         } catch (Exception e2) {
					                                             System.out.println("e2     " + e2);
					                                         }
					 
					                                         Object[][] data = object1;
			
					                                         MemoryProxy proxy = null;
					 
					                                        
					                                             proxy = new MemoryProxy(data);
					                                         
			
					                                         ArrayReader reader = new ArrayReader(rDef);
					                                         
					                                          branchStore = new Store(proxy,reader);  
					                                                 
					                                           branchStore.load();
					                                       	branchComboBox.setStore(branchStore);
					                                         
		                                                       
					 
				
				}
		});
	 }

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 /**
	  * method to manage offeredby table
	  */
	 
	 public void manageOfferedBy(){

    	 
//		 	final VerticalPanel vPanel = new VerticalPanel();
		final ComboBox programOfferedComboBox = new ComboBox();
		final ComboBox suggestbox=new ComboBox();
		 	@SuppressWarnings("unused")
			HorizontalPanel hPanel = new HorizontalPanel();
	        final Button manageButton = new Button(constant.manageButton());
	      	final FlexTable searchtable = new FlexTable();
	      	final Label criteriaLabel = new Label(constant.labelProgramOfferedBy());
	      	final TextField valuetTextField = new TextField(constant.select());	
	      	valuetTextField.setPixelSize(183, 25);      	
		    final VerticalPanel gPanel = new VerticalPanel(); 		 
	 		final Label manageHeading = new Label(constant.manageEntityProgram());
	 		manageHeading.setStyleName("Heading1");
	 		VerticalPanel manageHeadingPanel = new VerticalPanel();
//	 		manageHeadingPanel.add(manageHeading);   

	 		
//	      programOfferedComboBox.setPixelSize(180, 5);
	 		 		
		      programOfferedComboBox.setMinChars(1);   
		      programOfferedComboBox.setDisplayField("entity_name");  
		      programOfferedComboBox.setValueField("entity_id");  
		      programOfferedComboBox.setMode(ComboBox.LOCAL);  
		      programOfferedComboBox.setTriggerAction(ComboBox.ALL);  
		      programOfferedComboBox.setEmptyText(constant.searchProgramOffer());  
		      programOfferedComboBox.setLoadingText(constant.searching());  
		      programOfferedComboBox.setTypeAhead(true);  
		      programOfferedComboBox.setSelectOnFocus(true);  
		      programOfferedComboBox.setWidth(190);  
		      programOfferedComboBox.setHideTrigger(false);
	 		
		      suggestbox.setMinChars(1);   
		      suggestbox.setDisplayField("program_name");  
		      suggestbox.setValueField("program_id");  
		      suggestbox.setMode(ComboBox.LOCAL);  
		      suggestbox.setTriggerAction(ComboBox.ALL);  
		      suggestbox.setEmptyText(constant.searchProgramOfferEntity());  
		      suggestbox.setLoadingText(constant.searching());  
		      suggestbox.setTypeAhead(true);  
		      suggestbox.setSelectOnFocus(true);  
		      suggestbox.setWidth(190);  
		      suggestbox.setHideTrigger(false);
		  	
	 		
		    connectProgOfferedByService.methodProgramOfferedByPopulate(new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
						public void onFailure(Throwable arg0) {
							MessageBox.alert(message.error(), constant.delete() + arg0);
						}
						public void onSuccess(CM_BranchSpecializationInfoGetter[] result) {		
					         RecordDef recordDef = new RecordDef(  
					                 new FieldDef[]{  
					                         new StringFieldDef("entity_name"),  
					                         new StringFieldDef("entity_id"),
					                 }  
					         ); 
					         
					         object1= new Object[result.length][2];
							   
					         Object[][] data = object1;			 

							 for(int i=0;i<result.length;i++){	
							for(int k=0;k<2;k++){
								if(k==0){
									object1[i][0]=result[i].getEntity_name(); 
								}else{
									object1[i][1]=result[i].getEntity_id(); 
								}
							}
							 }
					         
							 MemoryProxy proxy = new MemoryProxy(data);  
			 		         ArrayReader reader = new ArrayReader(recordDef);  
			 		         Store store = new Store(proxy, reader);  
			 		         store.load();  
				
			 		         programOfferedComboBox.setStore(store);  
			 			}
		    }); 
		    
			      
			    final Label uniLabel = new Label(constant.label_programName());		    			       		    		    
			    programOfferedComboBox.addListener(new ComboBoxListenerAdapter(){
					@Override
					public void onSelect(ComboBox comboBox, Record record, int index) {
						final String programOfferedBy = programOfferedComboBox.getValue(); 
		        		createProgramList(programOfferedBy,suggestbox);
					}
			     });

	        				        		  
	        manageButton.addListener(new ButtonListenerAdapter(){
	        		  public void onClick(final Button manageButton, EventObject e){
	        			  String value=null;
	        			  	        			 
	        			  if(!validator.nullValidator(programOfferedComboBox.getRawValue())){
	        				
	        				  if(!validator.nullValidator(suggestbox.getRawValue())){  
	        					
	        			     value = suggestbox.getValue();
	        			  }else
	        			  {
	        				 
	        				value="%";  
	        			  }
	        			 	        			  			        	 				 		
	        		connectProgOfferedByService.methodProgramOfferedByProgramList(programOfferedComboBox.getValue(), value,
	        					new AsyncCallback<CM_BranchSpecializationInfoGetter[]>(){
	        							@Override
	        							public void onFailure(Throwable arg0) {
	        								MessageBox.alert(constant.dbError() + arg0);
	        							}
	        							@Override
	        							public void onSuccess(final CM_BranchSpecializationInfoGetter[] arg0) {		        								

	        		              
	        		        	   final GridPanel grid2 = new GridPanel();
	        		        	   
	        		        	     grid2.setTitle(constant.labelProgramList());

	        				         String fullName[] = new String[arg0.length];
	          				         String[] name = new String[3];
	        			    		
	        			    		object1= new Object[arg0.length][15];
	        						 
	        		                String str = "";
	        			    			for(int i=0 ; i < arg0.length ; i++){
	        				             	for (int k = 0; k < 15; k++) {
	        				                     if (k == 0) {
	        				                         str = arg0[i].getProgram_name();
	        				                     } else if (k == 1) {
	        				                         str = arg0[i].getBranch_name();
	        				                     } else if (k == 2) {
	        				                         str = arg0[i].getSpecialization_name();
	        				                     } else if (k == 3) { 
	        				                         str = arg0[i].getEntity_name();
	        				                     } else if (k == 4) {
	        				                    	 			        			  		  					
		        			   			            	for (int j = 0; j < 3; j++) {
		        			   			                    if (j == 0) {
		        			   			                    	name[0] = new String();
		        			   			                        name[0] = arg0[i].getFirst_name();
		        			   			                    } else if (j == 1) {
		        			   			                    	name[1] = new String();
		        			   			                        name[1] = arg0[i].getMiddle_name();
		        			   			                    } else if (j == 2) {
		        			   			                    	name[2] = new String();
		        			   			                    	name[2] = arg0[i].getLast_name();
		        			   			                    } 
		        			   			            	}
		        			 			            		 fullName[i] = name[0] +" "+ name[1] +" "+ name[2];
		        			   			            		 str = fullName[i];
		        			   			            		 
//		        			   			            		
	        				                     } else if (k == 5) {
	        				                         str = arg0[i].getSeats();
	        				                     } else if (k == 6) {
	        				                         str = arg0[i].getLocation_name();
	        				                         
	        				                     } else if (k == 7) {
	        				                         str = arg0[i].getProgram_id();
	        				                     } else if (k == 8) {
	        				                         str = arg0[i].getBranch();
	        				                     } else if (k == 9) {
	        				                         str = arg0[i].getSpecialization();
	        				                     } else if (k == 10) {
	        				                         str = arg0[i].getEntity_id();
	        				                     } else if (k == 11) {
	        				                         str = arg0[i].getEntity_employee_id();
	        				                     } else if (k == 12) {
	        				                         str = arg0[i].getLocation_id();
	        				                     }		        				                     
	        				                     
	        				                     try{
	        				             		 object1[i][k]=str;  
	        				                     }catch (Exception e) {								
												}
	        		//		             		
	        				                 }
	        							};
	        							
	        						  Object[][] data2 = object1;			 
	        	 				  
	        		        	   final RecordDef recordDef2 = new RecordDef(  
	        		        			   new FieldDef[]{  	   		                		   
	        			                           new StringFieldDef("programName"),  
	        			                           new StringFieldDef("branchName"),  
	        			                           new StringFieldDef("specializationName"),  
	        			                           new StringFieldDef("offeredby"),  
	        			                           new StringFieldDef("mentor"),  
	        			                           new IntegerFieldDef("seats"),  
	        			                           new StringFieldDef("location"),
	        			                           new StringFieldDef("programID"),
	        			                           new StringFieldDef("branch"),
	        			                           new StringFieldDef("specialization"),
	        			                           new StringFieldDef("entityID"),
	        			                           new StringFieldDef("entityEmployeeID"),
	        			                           new StringFieldDef("locationID"),
	        			                   } 
	        			          );    

	        	    	           MemoryProxy proxy2 = new MemoryProxy(data2);  
	        	    	           ArrayReader reader2 = new ArrayReader(recordDef2);  
	        	    	           Store store2 = new Store(proxy2, reader2);
	        	    	           store2.load();
	        	    	           grid2.setStore(store2);
	        	    	              
	        	    	           BaseColumnConfig[] columns2 = new BaseColumnConfig[]{  
	        	    	                   new CheckboxColumnConfig(cbSelectionModel2),  
	        	    	                   //column ID is company which is later used in setAutoExpandColumn  
	        	    	                   new ColumnConfig("Program", "programName", 125, true, null, "company"),  
	        	    	                   new ColumnConfig("Branch", "branchName", 150, true, null, "company"),  
	        	    	                   new ColumnConfig("Specialization", "specializationName", 188, true, null, "company"),  
	        	    	                   new ColumnConfig("Program offering Entity*", "offeredby", 200, true, null, "company"),
	        	    	                   new ColumnConfig("Mentor", "mentor", 150, true, null, "company"),
	        	    	                   new ColumnConfig("Seats", "seats", 40, true, null, "company"),
	        	    	                   new ColumnConfig("Location", "location", 160, true, null, "company")

	        	    	           };
	        	    	             
	        	    	           ColumnModel columnModel2 = new ColumnModel(columns2);
	        	    	           columnModel2.setHidden(1, true);
	        	    	           
	        	    	           grid2.setColumnModel(columnModel2);   
	        	    	           grid2.setFrame(true);  
	        	    	           grid2.setStripeRows(true); 
	        	    	           grid2.setSelectionModel(cbSelectionModel2);  
	        	    	           grid2.setWidth(900);  
	        	    	           grid2.setHeight(300);  
	        	    	           grid2.setFrame(true);    
	        	    	           grid2.setIconCls("grid-icon");	  
	        	     	                   	  
	        	             	  Label heading2 = new Label(constant.manageEntityProgram());
	        	             	  heading2.setStyleName("Heading1");
	        	             	  
	        	             	  VerticalPanel heading2VerticalPanel = new VerticalPanel();
	        	             	  
	        		    		         Toolbar topToolBar = new Toolbar();
	        		    		         topToolBar.addFill();
	        	             	                 	  
	        		    		  topToolBar.addButton(new ToolbarButton(constant.edit(), new ButtonListenerAdapter(){
	        				    	       public void onClick(Button Button, EventObject e) {
	        				    	    	   
	        				    		           Record[] records = cbSelectionModel2.getSelections();
	        				    		                    
	        				    		           String msg = "";
	        				    		           if(records.length < 1){
	        				    		        	   MessageBox.alert(message.error(),message.atleastOneRecord());
	        				    		           }
	        				    		           else if(records.length > 1){
	        				    		        	   MessageBox.alert(message.error(), message.onlyOneRecord());
	        				    		           }
	        				    		           else if(records.length == 1){		        				   
	        				    		        	   final Record record = records[0]; 		        	   
	        				    		        	   msg += record.getAsString("program");  
	        				    		        	   // System.out.println("msg-2" + msg);
	        				    		        	   
	        				    		        	   				    		        	
 
	        				    		
	        				          final ComboBox mentorComboBox = new ComboBox();
	        				    		    
	        							    mentorComboBox.setMinChars(1);  
//	        							    mentorComboBox.setFieldLabel("Entity");  
//	        							    mentorComboBox.setDisplayField("state"); 
	        				         	    mentorComboBox.setDisplayField("name");
	        				          		mentorComboBox.setValueField("ID"); 
	        							    mentorComboBox.setMode(ComboBox.LOCAL);  
	        							    mentorComboBox.setTriggerAction(ComboBox.ALL);  
	        							    mentorComboBox.setEmptyText(constant.searchMentor());  
	        							    mentorComboBox.setLoadingText(constant.searching());  
	        							    mentorComboBox.setTypeAhead(true);  
	        							    mentorComboBox.setSelectOnFocus(true);  
	        							    mentorComboBox.setWidth(200);  
	        							    mentorComboBox.setHideTrigger(false);
	        							    mentorComboBox.setSelectOnFocus(true);
	        							    mentorComboBox.setForceSelection(true);

	        							   
	        						    connectProgOfferedByService.methodMentorPopulate(user_id,new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
	        				  					public void onFailure(Throwable arg0) {
	        				  						MessageBox.alert(message.error(),constant.dbError() + arg0);
	        				  					}
	        				  					public void onSuccess(CM_BranchSpecializationInfoGetter[] result) {

//	        				  				         String fullName[] = new String[result.length];
	        				  				         String[] name = new String[3];
	        				  				          				         
	        				  				         RecordDef recordDef = new RecordDef(  
	        								                 new FieldDef[]{  
	        								                         new StringFieldDef("name"),  
	        								                         new StringFieldDef("ID"),
	        								                 }  
	        								         ); 
	        								         
	        								         object1= new Object[result.length][2];
	        								         Object[][] data = object1;			 
	        										 for(int i=0;i<result.length;i++){						
	        					  			            	for (int k = 0; k < 4; k++){
	        					  			                    if (k == 0) {
	        					  			                    	name[0] = new String();
	        					  			                        name[0] = result[i].getFirst_name();
//	        					  			                        // System.out.println("first name is: "+ name[0]);
	        					  			                    } else if (k == 1) {
	        					  			                    	name[1] = new String();
	        					  			                        name[1] = result[i].getMiddle_name();
//	        					  			                        // System.out.println("mid name is: "+ name[1]);
	        					  			                    } else if (k == 2) {
	        					  			                    	name[2] = new String();
	        					  			                    	name[2] = result[i].getLast_name();
//	        					  			                        // System.out.println("last name is: "+ name[2]);
	        					  			                    } 
	        					  			            	 }
	        					  			            		 
	        					  			            		 object1[i][0] = name[0]+" "+ name[1] +" "+ name[2];
	        					  			            		 object1[i][1] = result[i].getEntity_employee_id(); 
	        					  			            		 // System.out.println("Full Name is: " + object1[i][0]);	
	        					  			            		 // System.out.println("Full Name's ID is: "+ object1[i][1]);						 			             		
	        										 }
	        								         
	        										 MemoryProxy proxy = new MemoryProxy(data);  
	        						 		         ArrayReader reader = new ArrayReader(recordDef);  
	        						 		         Store store = new Store(proxy, reader);  
	        						 		         store.load();  		        							
	        						 		         mentorComboBox.setStore(store);  
	        						 		        mentorComboBox.setRawValue(record.getAsString("mentor"));
	        				  		 			}
	        				  		 	});

	        				    		    
	        				    		    final ComboBox locationComboBox = new ComboBox();
	        				    		    
	        				                locationComboBox.setMinChars(1);  
//	        				                locationComboBox.setFieldLabel("Entity");  
	        				                locationComboBox.setDisplayField("state");
	        				                locationComboBox.setValueField("id");
	        				                locationComboBox.setMode(ComboBox.LOCAL);  
	        				                locationComboBox.setTriggerAction(ComboBox.ALL);  
	        				                locationComboBox.setEmptyText(constant.locationSearch());  
	        				                locationComboBox.setLoadingText(constant.searching());  
	        				                locationComboBox.setTypeAhead(true);
	        				                locationComboBox.setSelectOnFocus(true); 
	        				                locationComboBox.setWidth(200);  
	        				                locationComboBox.setHideTrigger(false);
	        				                locationComboBox.setForceSelection(true);
	        						 		
	        				                
	        				                methodLocationPopulate(locationComboBox);
	        				                

	        				                	
	        				    		        	   
	        				    		        	   final String[] entity = new String[17];
	        				    		        	   entity[0] = record.getAsString("programName");
	        				    		        	   entity[1] = record.getAsString("branchName");
	        				    		        	   entity[2] = record.getAsString("specializationName");
	        				    		        	   entity[3] = record.getAsString("offeredby");
	        				    		        	   entity[4] = record.getAsString("mentor");
	        				    		        	   entity[5] = record.getAsString("seats");
	        				    		        	   entity[6] = record.getAsString("location");
	        				    		        	   
	        				    		        	   entity[7] = record.getAsString("programID");
	        				    		        	   entity[8] = record.getAsString("branch");
	        				    		        	   entity[9] = record.getAsString("specialization");
	        				    		        	   entity[10] = record.getAsString("entityID");
	        				    		        	   entity[11] = record.getAsString("entityEmployeeID");
	        				    		        	   entity[12] = record.getAsString("locationID");
	        				    		        	   
	        				    		        	   FlexTable editEntityProgramTable = new FlexTable();
	        				    		        	   Label programLabel = new Label(constant.label_programName());
	        				    		        	   Label branchLabel = new Label(constant.label_branchname());
	        				    		        	   Label specializationLabel = new Label(constant.specialization());
	        				    		        	   Label programOfferingEntityLabel = new Label(constant.labelProgramOfferedBy());
	        				    		        	   Label mentorLabel = new Label(constant.mentor());
	        				    		        	   Label seatsLabel = new Label(constant.seat());
	        				    		        	   Label locationLabel = new Label(constant.location());
	        				    		        	   
//	           				    		        	   
	        				    		        	   Label programTextLabel = new Label(entity[0]);		        				    		        	   
	        				    		        	   Label branchTextLabel = new Label(entity[1]);
	        				    		        	   Label specializationTextLabel = new Label(entity[2]);
	        				    		        	   Label programOfferingEntityListLabel = new Label(entity[3]);

	        				    		        	   final NumberField seatsNumberField = new NumberField();
	        				    		        	   seatsNumberField.setAllowDecimals(false);
	        				    		        	   seatsNumberField.setMinLength(1);
	        				    		        	   seatsNumberField.setMaxLength(3);
	        				    		        	   seatsNumberField.setInvalidText(constant.seatMessage());
	        				    		        	   seatsNumberField.setValue(entity[5]);
	        				    		        	
		        				    		 	       
	        				    		        	 					    		        	   				    		        	        	         	   
	        				    		        	editEntityProgramTable.clear();		        				    		        	   
	        				    		        	editEntityProgramTable.setWidget(2, 0, programLabel);
	        				    		 	       	editEntityProgramTable.setWidget(2, 1, programTextLabel);
	        				    		 	       	editEntityProgramTable.setWidget(3, 0, branchLabel);
	        				    		 	       	editEntityProgramTable.setWidget(3, 1, branchTextLabel);
	        				    		 	       	editEntityProgramTable.setWidget(4, 0, specializationLabel);
	        				    		 	       	editEntityProgramTable.setWidget(4, 1, specializationTextLabel);	        
	        				    		 	       	editEntityProgramTable.setWidget(5, 0, programOfferingEntityLabel);
	        				    		 	       	editEntityProgramTable.setWidget(5, 1, programOfferingEntityListLabel);
	        				    		 	       	editEntityProgramTable.setWidget(6, 0, mentorLabel);
	        				    		 	       	editEntityProgramTable.setWidget(6, 1, mentorComboBox);
	        				    		 	       	editEntityProgramTable.setWidget(7, 0, seatsLabel);
	        				    		 	       	editEntityProgramTable.setWidget(7, 1, seatsNumberField);
	        				    		 	       	editEntityProgramTable.setWidget(8, 0, locationLabel);
	        				    		 	       	editEntityProgramTable.setWidget(8, 1, locationComboBox);
	        				    		 	      
	        				    		          final Panel p1 = new Panel();   
	        				    		 		  p1.clear();
	        				    		 		  p1.add(editEntityProgramTable); 
	        				    		 		  
	        				    		 		
	        				    		 		 
	        				    		       	  final Window window = new Window();
	        				    		 			 window.setTitle(constant.editPrograms());
	        				    		 	         window.setWidth(450);  
	        				    		 	         window.setHeight(300);  
	        				    		 	         window.setMinWidth(350);  
	        				    		 	         window.setMinHeight(150);  
	        				    		 	         window.setLayout(new FitLayout());  
	        				    		 	         window.setPaddings(5);  
	        				    		 	         window.setButtonAlign(Position.CENTER);  
	        				    		 	         window.setPlain(true);  
	        				    		 		     window.setResizable(false);
//	        				    		 		     window.setLayout(new BorderLayout());    
	        				    		 		     window.setAutoScroll(true);
	        				    		 		     window.setCloseAction(Window.CLOSE); 
	        				    		 		     window.setFrame(true);
	        				    		 		     window.setClosable(true);
	        				    		 		     window.setModal(true);
	        				    		           
	        				    		 		  Button updateButton = new Button(constant.updateButton());   
	        				    		 	      window.addButton(updateButton);   		        				    		 	    
	        				    		 	      
	        				    updateButton.addListener(new ButtonListenerAdapter(){ 
	        				           public void onClick(Button updateButton, EventObject e){
	        				        	   Boolean flag = true;
	        				        	    
	        				        	    try{
	        				        	    	seatsNumberField.validate();
	        				        	    }catch (Exception e1) {
												flag = false;
											}
	        				    		    if (validator.nullValidator(mentorComboBox.getRawValue())) {
	        				                     try {
	        				                    	 mentorComboBox.markInvalid(
	        				                             "");
	        				                     } catch (Exception e1) {
	        				                         flag = false;
	        				                     }

	        				                     flag = false;
	        				                 }
	        				    		    
	        				    		    if (validator.nullValidator(locationComboBox.getRawValue())) {
	        				                     try {
	        				                    	 locationComboBox.markInvalid(
	        				                             "");
	        				                     } catch (Exception e1) {
	        				                         flag = false;
	        				                     }

	        				                     flag = false;
	        				                 }
	        				        	    if(flag == true){
	        				        	    try{
	        				    		    entity[13] = entity[10];
	        				    		   
	        				    		           				    		    
	        				    		    
	        				    		    if(mentorComboBox.getRawValue().equalsIgnoreCase(record.getAsString("mentor"))){
	        				    		        entity[14] = record.getAsString("entityEmployeeID");
	        				    		     }else{
	        				    		    	 entity[14] = mentorComboBox.getValue();
	        				    		    }
	        				    		    
	        				    		    if(locationComboBox.getRawValue().equalsIgnoreCase(record.getAsString("location"))){
	        				    		    	entity[15] = record.getAsString("locationID");
		        				    		    }else{
		        				    		    	 entity[15] = locationComboBox.getValue();
		        				    		    }
	        				        	   entity[16] = seatsNumberField.getText();
	        				        	    
	        				        	    
	        				    		    CM_BranchSpecializationInfoGetter editProgramOfferedBy = new CM_BranchSpecializationInfoGetter();		    			
//	        				    		    	    
	        				    		    editProgramOfferedBy.setProgram_id(entity[7]);
	        				    		    editProgramOfferedBy.setBranch(entity[8]);
	        				    		    editProgramOfferedBy.setSpecialization(entity[9]);
	        				    		    editProgramOfferedBy.setOffered_by(entity[10]);
	        				    		    editProgramOfferedBy.setEntity_employee_id(entity[11]);
	        				    		    editProgramOfferedBy.setSeats(entity[5]);
	        				    		    editProgramOfferedBy.setLocation_id(entity[12]);
	        				    		    
	        				    		    editProgramOfferedBy.setNew_offered_by(entity[13]);
	        				    		    editProgramOfferedBy.setNew_entity_employee_id(entity[14]);
	        				    		    editProgramOfferedBy.setNew_location_id(entity[15]);
	        				    		    editProgramOfferedBy.setNew_seats(entity[16]);
	        				    		    
	        				    		    
	        			        connectProgOfferedByService.methodEditProgramOfferedBy(editProgramOfferedBy, new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
	 		        					public void onFailure(Throwable arg0) {
	 		        						MessageBox.alert(message.alertMentorandLocation());
	 		        					}
	 		        					public void onSuccess(CM_BranchSpecializationInfoGetter[] result) {			
 		        						 
						  						MessageBox.alert(message.success(), message.alert_oneditsuccess(), new AlertCallback(){
										
												public void execute() {
													cbSelectionModel2.clearSelections();
													manageButton.fireEvent("click");
												} 		        						  							
						  						});	
						  					window.close();
	 		        					}
	 		        			 }); 
	        			        						            	
	        				    			            }
	        				           catch (Exception e2) {
												System.out
														.println("Exception e2 "+e2);
											} }  
	        				        	    else {
			        				        	   MessageBox.alert(message.error(), message.checkFields());
			        				           }    
	        				           }

	        				    			      });

	        				    		           window.add(p1);
	        				    		           window.show();
	        				    		           locationComboBox.setRawValue(record.getAsString("location"));
//	        				    		           locationComboBox.setValue(record.getAsString("location")); 
	        				    		           }		        				    		           
	        				    		            	   
	        				    		       }   
	        		    		         	}				    		        	 
	        		    		         ));
		    		         
	        		    		         topToolBar.addButton(new ToolbarButton(constant.delete(), new ButtonListenerAdapter(){
	        				    			  public void onClick(Button delButton, EventObject e){
	        				    				  Record[] records = cbSelectionModel2.getSelections();
//	        				    				  String msg = "";
	        				    				  if(records.length<1){
	        				    					  MessageBox.alert(message.error(),message.select_recordsdeletion());
	        				    				  }
	        				    		          else if(records.length > 1){
	        				    		        	   MessageBox.alert(message.error(), message.atleastOneRecordDelete());
	        				    		          }
	        				    		          else if(records.length == 1){	
	        				    		        	  	Record record = records[0];
	        				    		        	  	
	        				    		        	  	String[] pEntity = new String[7];	    		        	  			        				    		        	  	
	        				    		        	  	pEntity[0] = record.getAsString("programID");
	        				    		        	  	pEntity[1] = record.getAsString("branch");
	        				    		        	  	pEntity[2] = record.getAsString("specialization");
	        				    		        	  	pEntity[3] = record.getAsString("entityID");
	        				    		        	  	pEntity[4] = record.getAsString("entityEmployeeID");
	        				    		        	  	pEntity[5] = record.getAsString("seats");
	        				    		        	  	pEntity[6] = record.getAsString("locationID");
	        				    		        	  
	        				    		        	  	
	       		        				    		    CM_BranchSpecializationInfoGetter deleteProgramOfferedBy = new CM_BranchSpecializationInfoGetter();
	    		        				    		    deleteProgramOfferedBy.setProgram_id(pEntity[0]);
	    		        				    		    deleteProgramOfferedBy.setBranch(pEntity[1]);
	    		        				    		    deleteProgramOfferedBy.setSpecialization(pEntity[2]);
	    		        				    		    deleteProgramOfferedBy.setOffered_by(pEntity[3]);
	    		        				    		    deleteProgramOfferedBy.setMentor(pEntity[4]);
	    		        				    		    deleteProgramOfferedBy.setSeats(pEntity[5]);
	    		        				    		    deleteProgramOfferedBy.setLocation_id(pEntity[6]);  
	    		        				    		    
	    		        				    		  connectProgOfferedByService.methodDeleteProgramOfferedBy(deleteProgramOfferedBy, new AsyncCallback<String>() {
 		        						  					public void onFailure(Throwable arg0) {
 		        						  						MessageBox.alert(message.error(), constant.dbError() + arg0);
 		        						  					}
 		        						  					public void onSuccess(String result) {			
 		        						  						MessageBox.alert("Success", message.detailsDelete(), new AlertCallback(){
																
																	public void execute() {
																		cbSelectionModel2.clearSelections();

																		manageButton.fireEvent("click");
																	} 		        						  							
 		        						  						});	 		        						  					 		        						  						
 		        						  		 			}
	    		        				    		  }); 		    		        				    		    		    		        				    		    
	        				    				  
	        				    		          }				    				  
	        				    			  } 
	        		    		         	}
	        		    		         ));
	        	             	
	        		    		  try{       
//	        		    		  cbSelectionModel2.clearSelections();
	        		    		  }catch (Exception e) {
									System.out.println("Exception in cbSelectionModel2  "+e);
								}   
	        		    		  heading2VerticalPanel.clear();       
//	        	             	  heading2VerticalPanel.add(heading2);
//	        	             	  heading2VerticalPanel.clear();
//	        	             	  vPanel.add(heading2VerticalPanel);
	        	             	  grid2.add(topToolBar);
	        	             	  gPanel.clear();
	        	             	  gPanel.add(grid2);
	        	             	  vPanel.add(gPanel);
	        	             	  vPanel.add(footerVerticalPanel);             	  	        	                	 	                				  
	        							
	        							}
	        				    	});  
	        		  }else{
	        			  System.out.println("Select entity");
	        			  MessageBox.alert(message.error(),constant.selectEntity());
	        	 			  }
	        	 		 }
	        });
			 
			    
			    
			    
			    searchtable.setWidget(3, 0, criteriaLabel);
			    searchtable.setWidget(3, 1, programOfferedComboBox);
			    searchtable.setWidget(4, 0, uniLabel);
			    searchtable.setWidget(4, 1, suggestbox);
			    searchtable.setWidget(5, 1, manageButton);
			    
//			    // System.out.println("First Value is: " + value + programOfferedBy);	
			    
	    		vPanel.clear();
	    		manageHeadingPanel.clear();
	     		manageHeadingPanel.add(manageHeading); 
	    		vPanel.add(manageHeadingPanel);
	     		vPanel.add(searchtable);
	     		vPanel.add(footerVerticalPanel);
	     		
	      
	 }
	 
	 
	 
	 
	 /**
	  * Method to populate list of Programs of selected Program Offering Entity
	 * @return 
	  */ 
	 
	 MultiWordSuggestOracle createProgramList(String selectedProgramOfferedColumn,final ComboBox suggestbox){    

			connectProgOfferedByService.methodManageProgramList(selectedProgramOfferedColumn, new AsyncCallback<CM_BranchSpecializationInfoGetter[]>() {
		        public void onFailure(Throwable arg0) {
		            MessageBox.alert(constant.dbError() + arg0.getMessage());
		        }
		        public void onSuccess(final CM_BranchSpecializationInfoGetter[] result) {
					try{
				
					RecordDef recordDef = new RecordDef(new FieldDef[] {
	                        new StringFieldDef("program_name"),
	                        new StringFieldDef("program_id")
	                    });

	            String[][] object2 = new String[result.length][2];

	            String[][] data = object2;

	            for (int i = 0; i < result.length; i++) {
	                object2[i][0] = result[i].getProgram_name();
	                object2[i][1] = result[i].getProgram_id();
	             
	            }
	           
	            MemoryProxy proxy = new MemoryProxy(data);

	            ArrayReader reader = new ArrayReader(recordDef);
	            progListStore = new Store(proxy, reader);
	            progListStore.load();
	          
	        	suggestbox.setStore(progListStore);
	    		
				}catch (Exception e) {
					System.out.println("Exception in prog list "+e);
				}
		            
		        }
		        
		    });
		  return oracle;

		}
	 
	 public void methodSetEntityNameProp(ComboBox collegeCenterSelect){
	 collegeCenterSelect.setForceSelection(true);
		collegeCenterSelect.setMinChars(1);
		collegeCenterSelect.setDisplayField("entity_name");
//		collegeCenterSelect.setValueField("entity_id");
		collegeCenterSelect.setMode(ComboBox.LOCAL);
		collegeCenterSelect.setTriggerAction(ComboBox.ALL);
		collegeCenterSelect.setEmptyText(constant.selectEntityType());
		collegeCenterSelect.setLoadingText(constant.loading());
		collegeCenterSelect.setTypeAhead(true);
		collegeCenterSelect.setSelectOnFocus(true);
		collegeCenterSelect.setHideTrigger(false);
		
	 }
		
	 
	 
	 
	 public void methodSetEntityTypeProp(ComboBox entityTypeCombo){
		entityTypeCombo.setForceSelection(true);
     entityTypeCombo.setMinChars(1);
     entityTypeCombo.setDisplayField("entity_type");
     entityTypeCombo.setValueField("entity_type");
     entityTypeCombo.setMode(ComboBox.LOCAL);
     entityTypeCombo.setTriggerAction(ComboBox.ALL);
     entityTypeCombo.setEmptyText(constant.selectEntityType());
     entityTypeCombo.setLoadingText(constant.loading());
     entityTypeCombo.setTypeAhead(true);
     entityTypeCombo.setSelectOnFocus(true);
     entityTypeCombo.setHideTrigger(false);
	 
	 }
	 
	 public void methodSetProgramProp(ComboBox programComboBox){
		 programComboBox.setForceSelection(true); 
     programComboBox.setMinChars(1);  
     programComboBox.setDisplayField("program_name"); 
     programComboBox.setValueField("program_id");
     programComboBox.setMode(ComboBox.LOCAL);  
     programComboBox.setTriggerAction(ComboBox.ALL);  
     programComboBox.setEmptyText(constant.emptyProgramName());  
     programComboBox.setLoadingText(constant.loading());  
     programComboBox.setTypeAhead(true);  
     programComboBox.setSelectOnFocus(true);  
     programComboBox.setWidth(190);  
     programComboBox.setHideTrigger(false);
	 }

	 
	 public void methodSetBranchProp(ComboBox branchComboBox){
		 branchComboBox.setForceSelection(true); 
    branchComboBox.setDisplayField("branchname");  
    branchComboBox.setValueField("branchcode"); 
    branchComboBox.setMode(ComboBox.LOCAL);  
    branchComboBox.setTriggerAction(ComboBox.ALL);  
    branchComboBox.setEmptyText(constant.emptyBranchName());  
    branchComboBox.setLoadingText(constant.searching());  
    branchComboBox.setTypeAhead(true);  
    branchComboBox.setSelectOnFocus(true);  
    branchComboBox.setWidth(190);  
    branchComboBox.setHideTrigger(false);
	 }
	 
	 public void methodSetSpecProp(ComboBox specializationComboBox){  
    specializationComboBox.setDisplayField("specname");
    specializationComboBox.setValueField("speccode");
    specializationComboBox.setMode(ComboBox.LOCAL);  
    specializationComboBox.setTriggerAction(ComboBox.ALL);  
    specializationComboBox.setEmptyText(constant.selectSpecialization());  
    specializationComboBox.setLoadingText(constant.loading());  
    specializationComboBox.setTypeAhead(true);  
    specializationComboBox.setSelectOnFocus(true);  
    specializationComboBox.setWidth(190);  
    specializationComboBox.setHideTrigger(false);
	 }
	 
	 
}
