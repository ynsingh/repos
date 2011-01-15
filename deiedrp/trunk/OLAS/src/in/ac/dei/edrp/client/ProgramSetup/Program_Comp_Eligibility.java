package in.ac.dei.edrp.client.ProgramSetup;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.Shared.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

public class Program_Comp_Eligibility{
	
	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
	
	Validator valid= new Validator();
	
	VerticalPanel vp= new VerticalPanel();
	
	
	String progID;
	String Program;
    String entity_id;
    String branchId="";
    String branchName="";
    String catCode;
    int branchCount=0;
    String entity;
    NumberField cutoffNF1= new NumberField();

    String creatorid;
  
    messages msg = GWT.create(messages.class);
	constants cons = GWT.create(constants.class);
	
    String errorMsg;
    String confirm;
	
	 public Program_Comp_Eligibility(String userid) {
	    	
	    	this.creatorid=userid;
		}

   
    
	public VerticalPanel onModuleLoad() {
		
		errorMsg=msg.error();
		confirm=msg.confirm();
		
		vp.clear();
		vp.setWidth("1200px");
		
		Label selectEntityName= new Label(cons.entityName());
		Label selectEntity= new Label(cons.entityType());
		
		final CM_ComboBoxes entityTypeCB=new CM_ComboBoxes();
		final CM_ComboBoxes entityNameCB=new CM_ComboBoxes();
		final CM_ComboBoxes programCB= new CM_ComboBoxes();
		final CM_ComboBoxes branchCB= new CM_ComboBoxes();
		final ComboBox componentCombo= new ComboBox();
		final CM_ComboBoxes category_obj1 = new CM_ComboBoxes();
		
		
		final NumberField cutoffNF= new NumberField();
		final Button submitButton = new Button(cons.submit());
		
		final FormPanel mainForm = new FormPanel();
		FlexTable flex3 = new FlexTable();
	
		 componentCombo.setEmptyText("Select Component");  
		 componentCombo.setForceSelection(true);  
		 componentCombo.setMinChars(1);  
		 componentCombo.setDisplayField("Component");  
		 componentCombo.setMode(ComboBox.LOCAL);  
		 componentCombo.setTriggerAction(ComboBox.ALL);  
		 componentCombo.setLoadingText("Searching...");  
		 componentCombo.setTypeAhead(true);  
		 componentCombo.setSelectOnFocus(true);  
		 componentCombo.setHideTrigger(false);
		 componentCombo.setReadOnly(true);
		 
	
		entityTypeCB.onModuleLoad();
		entityNameCB.onModuleLoad();
		branchCB.onModuleLoad();
		category_obj1.onModuleLoad();
		programCB.onModuleLoad();
		
		
		programCB.progCombo.disable();
		branchCB.branchCombo.disable();
		entityNameCB.entityCombo.disable();
		componentCombo.disable();
		category_obj1.categoryCB.disable();
		submitButton.disable();
			
	    flex3.setCellSpacing(10);   
	    componentCombo.setWidth(130);
	    
	   
	    cutoffNF.setWidth(130);
	    cutoffNF.setAllowBlank(false);
	    cutoffNF.setAllowNegative(false);
	    cutoffNF.setMaxLength(5);
	    
	    cutoffNF1=cutoffNF;
	   
		flex3.setWidget(0, 0,selectEntity);
		flex3.setWidget(0, 1,entityTypeCB.entityDescCB);
		flex3.setWidget(0, 2,selectEntityName);
		flex3.setWidget(0, 3,entityNameCB.entityCombo);
		flex3.setWidget(1, 0,new Label(cons.label_programname()));
		flex3.setWidget(1, 1,programCB.progCombo);
		flex3.setWidget(1, 2,new Label(cons.label_branchname()));
		flex3.setWidget(1, 3,branchCB.branchCombo);
		flex3.setWidget(2, 0,new Label(cons.component()));
		flex3.setWidget(2, 1,componentCombo);
		flex3.setWidget(2, 2,new Label(cons.category()));
		flex3.setWidget(2, 3,category_obj1.categoryCB);
		flex3.setWidget(3, 0,new Label(cons.cutoffPercent()));
		flex3.setWidget(3, 1,cutoffNF);
		flex3.setWidget(4, 2,submitButton);

		
	  	mainForm.setLabelAlign(Position.TOP);
        mainForm.setTitle(cons.heading_programCompElig());
        mainForm.setPaddings(5);
        mainForm.setWidth("60%");
        mainForm.setFrame(true);
        
        mainForm.add(flex3);
        
        vp.add(mainForm);
       
        entityTypeCB.entityDescCB.addListener(new ComboBoxListenerAdapter() {
            public void onSelect(ComboBox comboBox, Record record, int index) {
            	
            	entityNameCB.entityCombo.clearValue();
            	entityNameCB.entityCombo.disable();
            	
            	programCB. progCombo.clearValue();
            	programCB. progCombo.disable();
            	
            	branchCB.branchCombo.clearValue();
            	branchCB.branchCombo.disable();
            	
            	componentCombo.clearValue();
    			componentCombo.disable();
    			
        		category_obj1.categoryCB.disable();
        		category_obj1.categoryCB.clearValue();
            	
        		String entity_type= entityTypeCB.entityDescCB.getValue();

						 connectTemp.Entity_Name(creatorid,entity_type,new AsyncCallback<CM_entityInfoGetter[]>(){

								public void onFailure(Throwable caught) {
									MessageBox.alert(cons.dbError(),caught.getMessage());
								}

								public void onSuccess(CM_entityInfoGetter[] arg0) {
									 
									RecordDef recordDef = new RecordDef(  
								                 new FieldDef[]{  
								                         new StringFieldDef("EntityName"),  
								                         new StringFieldDef("Entitycode")  

								                 }  
								         );  
									  final String[][] object2;
									  
									  
									  object2= new String[arg0.length][2];
									   String str = null;
										
					                   try {
					                       for (int i = 0; i < arg0.length;
					                               i++) {
					                      	
					                           for (int k = 0; k < 2; k++) {
					                               if (k == 0) {
					                              	 str = arg0[i].getEntity_name();
					                               } else if (k == 1) {
					                              	 str = arg0[i].getEntity_id();
					                               }

					                               object2[i][k] = str;
					                           }
					                       }
					                   } catch (Exception e2) {
					                       System.out.println("e2     " + e2);
					                   }

					                   Object[][] data = object2;
					       			
									
										 MemoryProxy proxy = new MemoryProxy(data);  
							    		   
							 		        
									         ArrayReader reader = new ArrayReader(recordDef);  
									         Store store = new Store(proxy, reader);  
									         store.load();  

									         entityNameCB.entityCombo.setStore(store);  
									         entityNameCB.entityCombo.enable();
									
								}
					        	
					        });
							
            }
            });
        
        
        entityNameCB.entityCombo.addListener(new ComboBoxListenerAdapter() {
            public void onSelect(ComboBox comboBox, Record record, int index) {
            	
            	programCB. progCombo.clearValue();
            	programCB. progCombo.disable();
            	
            	branchCB.branchCombo.clearValue();
            	branchCB.branchCombo.disable();
            	
            	componentCombo.reset();
    			componentCombo.disable();
        		
    			category_obj1.categoryCB.disable();
        		category_obj1.categoryCB.clearValue();
            	
            	if(index>=0){
            		
            	 entity=entityNameCB.entityCombo.getRawValue();
            	 entity_id=entityNameCB.entityCombo.getValue();
            	
            		connectTemp.getProgrammeOff(entity,new AsyncCallback<CM_ProgramInfoGetter[] >(){

           				public void onFailure(Throwable caught) {
           					MessageBox.alert(cons.dbError(),caught.getMessage());
           				}

           				public void onSuccess(CM_ProgramInfoGetter[]  arg0) {

           			         RecordDef recordDef = new RecordDef(  
           			                 new FieldDef[]{  
           			                         new StringFieldDef("Prog"),  
           			                         new StringFieldDef("ProgCode")  

					                 }  
					         );  
           			         
           			         if(arg0.length>0){
						  final String[][] object2;
						  object2= new String[arg0.length][2];
						  String str = null;
						  
						  try {
		                       for (int i = 0; i < arg0.length;
		                               i++) {
		                      	
		                           for (int k = 0; k < 2; k++) {
		                               if (k == 0) {
		                              	 str = arg0[i].getprogram_name();
		                               } else if (k == 1) {
		                              	 str = arg0[i].getprogram_id();
		                               }

		                               object2[i][k] = str;
		                           }
		                       }
		                   } catch (Exception e2) {
		                       System.out.println("e2     " + e2);
		                   }

		                   Object[][] data = object2;
           					 MemoryProxy proxy = new MemoryProxy(data);  
           		    		   
           		 		        
           				         ArrayReader reader = new ArrayReader(recordDef);  
           				         Store store = new Store(proxy, reader);  
           				         store.load();  

           				        programCB. progCombo.setStore(store); 
           				 	    programCB.progCombo.enable();
           				}
           			      else
      			        	MessageBox.alert(msg.emptyComboBox(cons.label_programname(),entity));
            		}
           				
           			});
            		
            	
            	}
            }
            });
        
        
        programCB.progCombo.addListener(new ComboBoxListenerAdapter() {
	        
        	public void onSelect(ComboBox comboBox, Record record, int index) {
        	
        		branchCB.branchCombo.clearValue();
        		branchCB.branchCombo.disable();
        		componentCombo.clearValue();
    			componentCombo.disable();
        		category_obj1.categoryCB.disable();
        		category_obj1.categoryCB.clearValue();
        	
        		Program=programCB.progCombo.getRawValue();
    					
    			progID=programCB.progCombo.getValue();
    					
						connectTemp.getBranch(progID,null, new AsyncCallback<CM_ProgramInfoGetter[]>(){

							public void onFailure(Throwable caught) {
								MessageBox.alert(cons.dbError(),caught.getMessage());	
							}

							public void onSuccess(CM_ProgramInfoGetter[] arg0) {
								 
								RecordDef recordDef = new RecordDef(new FieldDef[]{new StringFieldDef("BranchName"), 
										new StringFieldDef("BranchCode")  

				                 }  
				         );  
					  final String[][] object2;
					  
					  
					  object2= new String[arg0.length][2];
					   String str = null;
						
	                   try {
	                       for (int i = 0; i < arg0.length;
	                               i++) {
	                      	
	                           for (int k = 0; k < 2; k++) {
	                               if (k == 0) {
	                              	 str = arg0[i].getBranch_name();
	                               } else if (k == 1) {
	                              	 str = arg0[i].getBranch();
	                               }

	                               object2[i][k] = str;
	                           }
	                       }
	                   } catch (Exception e2) {
	                       System.out.println("e2     " + e2);
	                   }

	                   Object[][] data = object2;
									 
	                   MemoryProxy proxy = new MemoryProxy(data);  
						    		   
						 		        
								         ArrayReader reader = new ArrayReader(recordDef);  
								         Store store = new Store(proxy, reader);  
								         store.load();  

								         branchCB.branchCombo.setStore(store);  
								         branchCB.branchCombo.enable();
							
								
							}
							
						});
    			
						
            }
            });
        
       
   
    	
        branchCB.branchCombo.addListener(new ComboBoxListenerAdapter() {
	        
        	public void onSelect(ComboBox comboBox, Record record, int index) {
		
        		componentCombo.clearValue();
    			componentCombo.disable();
        		category_obj1.categoryCB.disable();
        		category_obj1.categoryCB.clearValue();
        		
			    branchName= branchCB.branchCombo.getRawValue();
	
			    branchId=branchCB.branchCombo.getValue();
			
					
					connectTemp.getProgramComponents(progID, branchId, new AsyncCallback<String[][]>(){

						public void onFailure(Throwable caught) {
							MessageBox.alert(cons.dbError(),caught.getMessage());
						}

						public void onSuccess(String[][] arg0) {
							
						
							RecordDef recordDef = new RecordDef(new FieldDef[]{new StringFieldDef("Component"), } );  
							 
							final String[][] object1;
							  
		
								branchCount=arg0.length;
								
								object1= new String[arg0.length][1];
								   
						         String[][] data = object1;			 

								 for(int i=0;i<arg0.length;i++){
								
						             		 object1[i][0]=arg0[i][0];  

								}
								 MemoryProxy proxy = new MemoryProxy(data);  
					    		   
					 		        
							         ArrayReader reader = new ArrayReader(recordDef);  
							         final Store store = new Store(proxy, reader);  
							         store.load();  

							      
							        
							        connectTemp.CheckComponents(entity_id,progID,branchId, new AsyncCallback<Boolean>(){

										public void onFailure(Throwable caught) {
											MessageBox.alert(cons.dbError(),caught.getMessage());
										}

										public void onSuccess(Boolean result) {
										if(result==false)
											MessageBox.alert(msg.noComponentSpecified(Program,branchName));
										else{
											  componentCombo.setStore(store);  
										      componentCombo.enable();
										      category_obj1.categoryCB.enable();
										      submitButton.enable();
										       
										}
											
											
										
										}
							        	
							        });
							
						}
						
					});
					
				
				
			
			
		}
        	
	});
    	
    	
        submitButton.addListener(new ButtonListenerAdapter() {
            
			public void onClick(Button button, EventObject e) {	
			
				int check=0;
			
				check=check+markIV(entityTypeCB.entityDescCB)+markIV(entityNameCB.entityCombo)+markIV(componentCombo)+
				markIV(programCB.progCombo)+markIV(branchCB.branchCombo)+markIV(category_obj1.categoryCB)+markIV(cutoffNF);
				 
				
				
				
				if(check==0){
					 if(Float.parseFloat(cutoffNF.getRawValue())>=100.00){
		          			try{
		          			MessageBox.alert(msg.checkFields());
		          			cutoffNF.markInvalid("");
		          			}
		          			catch(Exception ex){
		          				
		          			}
		          		 }
				else{
				
				final String[] details= new String[8];
				
				details[0]=entity_id;
				details[1]=progID;
				details[2]=branchId;
				details[4]=category_obj1.categoryCB.getValue();
				details[3]=componentCombo.getRawValue();
				details[5]=cutoffNF.getRawValue();
				details[7]=creatorid;
			
				sendAgeDetails(details);
					 }	
			}
				
				else
					MessageBox.alert(msg.checkFields());
        }
			});
		return vp;
        
        
   
        
        
	}
    
    public void sendAgeDetails(final String[] details){
    	  
    	
    	if(checkTf(cutoffNF1)==0)
    
    	
		connectTemp.componentEligibility(details, new AsyncCallback<Integer>() {

			public void onFailure(Throwable arg0) {
			
				MessageBox.alert(cons.dbError(),arg0.getMessage());
			}

		
			public void onSuccess(Integer arg0) {
				for(int i=0;i< 6;i++)
			
			
				if(arg0>0)
					MessageBox.alert(errorMsg,msg.duplicateEntry());
				
				else{
					 MessageBox.show(new MessageBoxConfig() { 

                         {
                             setMsg(msg.successfullySet(cons.weight()));
                     
                             setButtons(MessageBox.OK);
                             setCallback(new MessageBox.PromptCallback() {
                                     public void execute(
                                         String btnID,
                                         String text) {
                                     	try{
                                     		onModuleLoad();
                                     	}catch (Exception e) {
											}
                                     	
                                     }
                                 });
                         }
                     });
			
				}
			}
			
			
		});
    	else
			MessageBox.alert(msg.fieldsReqd());
    }
    public int checkTf( NumberField nf ){
    	int check=0;
    	try{
    		nf.validate();
    	}
    	catch(Exception e){
    		check++;
    	}
    	
    	return check;
    	
    }
    public int markIV(TextField t){
		int check=0;
		
		if((valid.nullValidator(t.getValueAsString())==true))
			try{
			
				check++;
				t.markInvalid("");
				
			}
		catch (Exception e) {
		
		}
		return check;
		
	}
}
