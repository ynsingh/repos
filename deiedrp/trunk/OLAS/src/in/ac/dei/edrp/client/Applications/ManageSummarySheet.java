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
package in.ac.dei.edrp.client.Applications;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.Shared.CM_ComboBoxes;
import in.ac.dei.edrp.client.Shared.Validator;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;

/**
 * <code> ManageSummarySheet </code>
 * ManageSummarySheet is used to Update the Student Information.
 * @author Ankit
 */
public class ManageSummarySheet  {
	
	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);

	String progID;
	String Program;
	
	String RegNo;
	String StudId;
	String FormNo;
	String branchName;
	String branchId;
	String entity_id;
	String[] address ;
	String[][] callData=null;
	String entity;
	int x=0;
	String ug;
	String pg;
	boolean flag=true;
	String modifierid;  
	Date d1 = new Date();
	Validator valid= new Validator();
	VerticalPanel mainVerticalPanel= new VerticalPanel();

	 messages msg = GWT.create(messages.class);
	 constants cons = GWT.create(constants.class);
	 

	 String	dbException=cons.dbError();
	 String errorMsg=msg.error();
	 String	confirm=msg.confirm();
	 String	alertSave=msg.alert_confirmentries();
	 String	correctEntriesMsg=msg.checkFields();
	 String	xCOS=cons.xCOS();
	 
	 /**
      * Constructor Definition
      * @param userid
      */
	 public ManageSummarySheet(String userid) {
	    	
	    	this.modifierid=userid;
		}

	
	/*public void onModuleLoad() {
		
		RootPanel.get().add(onModuleLoad1());
		
	}
	*/
	 /**
      * method to generate Search panel
      * this panel is used to search the Student by Registration number.
      * @return VerticalPanel
      */
	 public VerticalPanel onModuleLoad1() {
		
		final SummarySheet summaryObj = new SummarySheet();
	
		
		mainVerticalPanel.clear();
		
		mainVerticalPanel.setWidth("900px");
		
		//mainVerticalPanel.setHeight("500px");
		
		HorizontalPanel hp =new HorizontalPanel(); 
		
		final VerticalPanel vp= new VerticalPanel();

		
		
		final FlexTable flex3 = new FlexTable();
		Label selectEntityName= new Label(cons.entityName());
		final CM_ComboBoxes basic=new CM_ComboBoxes();
		final ComboBox entityCombo= new ComboBox();
		final TextField entityTF= new TextField();
		final TextField programTF = new TextField();
		final TextField branchTF = new TextField();
		final ComboBox branchCombo= new ComboBox();
		final Button editButton = new Button(cons.button_update());
		final Button searchButton = new Button(cons.search());
		final Button resetButton = new Button(cons.resetButton());
		
		final FormPanel mainForm = new FormPanel();
		final TextField appNo = new TextField();
		final TextField regNo = new TextField();
		
		basic.onModuleLoad();
		entityCombo.disable();
		appNo.setWidth(130);
		regNo.setWidth(130);
	
		entityTF.setReadOnly(true);
		programTF.setReadOnly(true);
		branchTF.setReadOnly(true);
		
		    hp.setSpacing(20);
			hp.add(new Label(cons.regNo()));
			hp.add(regNo);
			hp.add(searchButton);
			hp.add(resetButton);
			
		    flex3.setCellSpacing(5);   
	
			flex3.setWidget(1, 0,selectEntityName);
			flex3.setWidget(1, 1,entityTF);
			flex3.setWidget(2, 0,new Label(cons.label_programname()));
			flex3.setWidget(2, 1,programTF);
			flex3.setWidget(2, 2,new Label(cons.label_branchname()));
			flex3.setWidget(2, 3,branchTF);
			

			mainForm.setLabelAlign(Position.TOP);
		    mainForm.setTitle(cons.manageSumSheet());
		    mainForm.setPaddings(5);
		    mainForm.setWidth("100%");
		    mainForm.setFrame(true);

		       
		        mainForm.add(hp);
	
		        mainForm.add(vp);
		        
		        mainVerticalPanel.add(mainForm);
		        
		        /**
	             * button to reset the Search panel.
	             */
		        resetButton.addListener(new ButtonListenerAdapter() {
		            
					public void onClick(Button button, EventObject e) {
						onModuleLoad1();
					 }
		           });
		        
		        /**
	             * button to generate summary sheet if registered student found.
	             */
		        searchButton.addListener(new ButtonListenerAdapter() {
		            
					public void onClick(Button button, EventObject e) {
						
						searchButton.disable();
						regNo.setReadOnly(true);
						vp.clear();
						
		            	RegNo=regNo.getRawValue();
		            	
		            	/**
                         * Method to check the Registration Number.
                         */
		            	connectTemp.checkReg(RegNo,new AsyncCallback<Integer>(){

							public void onFailure(Throwable caught) {
								MessageBox.alert(dbException,caught.getMessage());
								
							}

							public void onSuccess(Integer result) {
							
								if(result>0){
								
						
		            	connectTemp.getStudentProgBranch(RegNo,new AsyncCallback<CM_ProgramInfoGetter>(){

							public void onFailure(Throwable  caught) {
								MessageBox.alert(dbException,caught.getMessage());
							}

							public void onSuccess(final CM_ProgramInfoGetter arg02) {
								
								
								progID=arg02.getprogram_id();
								branchId=arg02.getBranch_code();
								Program=arg02.getprogram_name();
								branchName=arg02.getBranch_name();
							    entity= arg02.getentity_name();
							    entity_id=arg02.getentity_id();
							  
							    
								vp.add(flex3);
								entityTF.setValue(entity);
								programTF.setValue(Program);
								branchTF.setValue(branchName);
							
								   
							  
								summaryObj.generateSummarySheet(progID,branchId,entity_id);
								summaryObj.fieldSet7.setVisible(false);
								vp.add(summaryObj.mainScroll);
								vp.add(editButton);
							
								/**
                                 * Method to get the student personal information
                                 */
								connectTemp.getStudentPersonalInfo(RegNo, new AsyncCallback<CM_StudentInfoGetter[]>(){

									public void onFailure(Throwable  caught) {
										MessageBox.alert(dbException,caught.getMessage());
									}

									public void onSuccess(CM_StudentInfoGetter[] arg0) {
								
										summaryObj.fname.setValue(arg0[0].getfirst_name());
										summaryObj.mname.setValue(arg0[0].getmiddle_name());
										summaryObj.lname.setValue(arg0[0].getlast_name());
										summaryObj.fname1.setValue(arg0[0].getfather_Fname());
										summaryObj.mname1.setValue(arg0[0].getfather_Mname());
										summaryObj.lname1.setValue(arg0[0].getfather_Lname());
										summaryObj.fname2.setValue(arg0[0].getmother_Fname());
										summaryObj.mname2.setValue(arg0[0].getmother_Mname());
										summaryObj.lname2.setValue(arg0[0].getmother_Lname());
										summaryObj.newGender.setValue(arg0[0].getgender());
										summaryObj.newCat.setValue(arg0[0].getcategory());
										summaryObj.dateofbirthDateField.setValue(arg0[0].getdate_of_birth());
										
										StudId=arg0[0].getUser_id();
										
								
										
										address =summaryObj.addressInfo(StudId);
									
										/**
                                         * Method to get the student address information.
                                         */
										connectTemp.getStudentAddressInfo(StudId, new AsyncCallback<CM_entityInfoGetter[]>(){

									
										  public void onFailure(Throwable  caught) {
												MessageBox.alert(dbException,caught.getMessage());
									}
										  public void onSuccess(CM_entityInfoGetter[] arg0) {
										
											  summaryObj.street1Text.setValue(arg0[0].getAddress_line1());
											  summaryObj.street2Text.setValue(arg0[0].getAddress_line2());
											  summaryObj.cityText.setValue(arg0[0].getcity());
											  summaryObj.pinNumber1.setValue(arg0[0].getpincode());
											  summaryObj.newState.setValue(arg0[0].getstate());
										
										
									}
				            		
				            	});
										
										/**
                                         * Method to get the student paper Information
                                         */
										 if(summaryObj.hpPaper.isVisible())	
										connectTemp.getStudentPaperInfo(RegNo,progID,new AsyncCallback<CM_StudentInfoGetter[]>(){

											public void onFailure(Throwable caught) {
												MessageBox.alert(dbException,caught.getMessage());
											}

											public void onSuccess(CM_StudentInfoGetter[] arg0) {
												
												if(arg0.length>0){
													
													for(int k=0;k<summaryObj.groupCB.length;k++)
													for(int i=0;i<arg0.length;i++){
												
													if(summaryObj.groupCB[k].getName().equals(arg0[i].getGrouping())){
														summaryObj.groupCB[k].setValue(arg0[i].getPapercode());
													}
												
													}
												
												}
											}
						            		
						            	});
										

										 /**
                                          * Method to get the student First Degree Information
                                          */
										 connectTemp.getStudentFD(RegNo,progID,"U",new AsyncCallback<CM_StudentInfoGetter[]>(){

											public void onFailure(Throwable caught) {
												MessageBox.alert(dbException,caught.getMessage());
											}

											public void onSuccess(CM_StudentInfoGetter[] arg0) {
												
												if(arg0.length>0){
												
												ug=arg0[0].getProgramId();
												
												summaryObj.ug.firstDegCodeCB.setValue(ug);
												}
											}
						            		
						            	});

										 /**
                                          * Method to get the student First Degree Information
                                          */
										 connectTemp.getStudentFD(RegNo,progID,"P",new AsyncCallback<CM_StudentInfoGetter[]>(){

											public void onFailure(Throwable caught) {
												MessageBox.alert(dbException,caught.getMessage());
											}

											public void onSuccess(CM_StudentInfoGetter[] arg0) {
											
												if(arg0.length>0){
												
												pg=arg0[0].getProgramId();
												
												summaryObj.pg.firstDegCodeCB.setValue(pg);
												}
											}
						            		
						            	});
										
									if(flag==true){
										/**
                                         * To get the Student CallList Information
                                         */
						            	connectTemp.getStudentCallListInfo(RegNo,
						            			new AsyncCallback<CM_StudentInfoGetter[]>(){

											public void onFailure(Throwable caught) {
												MessageBox.alert(dbException,caught.getMessage());
											}

											public void onSuccess(final CM_StudentInfoGetter[] arg0) {
											
												if(arg0.length>0){
												
													final String[][] callData=new String[arg0.length][5];
											
													if(summaryObj.listTable.getRowCount()>0)
													{
											
													for( int v=1;v< summaryObj.listTable.getRowCount();v++){
													
													final int f=v-1;
													
											
													final String Component1=summaryObj.listTable.getText(v, 1);
													final String Id= summaryObj.listTable.getText(v, 0);
												
												
													
													for( int i=0;i<arg0.length;i++){
												
												
													final int x=i;
													
													callData[x][0]=arg0[x].getComponentID();
											
													final float per=Float.parseFloat(arg0[x].getMarksPercentage());
													final int marks=Integer.parseInt(arg0[x].getMarksObtained());
													final int total=Integer.parseInt(arg0[x].getTotalMarks());
													final int score=Integer.parseInt(arg0[x].getScore());
												

										
												
													final String board= arg0[x].getboard_id();

											
													if(board!=null){
													
															
												   for(int x1=0;x1<summaryObj.ocb1.length;x1++){
															
												   final int x11= x1;
											
														String ComponentName=summaryObj.ocb1[x11].boardCB.getName();
														
														/**
                                                         * Method to get component Name
                                                         */
														connectTemp.getComponentId(ComponentName, new AsyncCallback<CM_ProgramInfoGetter[]>(){

															public void onFailure(
																	Throwable caught) {
																MessageBox.alert(dbException,caught.getMessage());
																
															}

															public void onSuccess(
																	CM_ProgramInfoGetter[] result) {
																if(result[0].getComponent_id().equals(arg0[x].getComponentID()) ){
																	
																	summaryObj.ocb1[x11].boardCB.setValue(board);
																
																	}
															}
															
														});
										
												}
														
											}		
													/**
                                                     * Method to get the Component ID
                                                     */
													connectTemp.getComponentId(Id,new AsyncCallback<CM_ProgramInfoGetter[]>(){

														
														public void onFailure(Throwable caught) {
															MessageBox.alert(dbException,caught.getMessage());
														}

														public void onSuccess(CM_ProgramInfoGetter[] arg01) {
													
															String WtId=arg01[0].getComponent_id();
														
															
													
															if(Component1.equals("("+summaryObj.percent+")") && WtId.equals(callData[x][0])){
														
																if(per>0){
												           		
																summaryObj.t1[f].setValue(per);
															
																}
													          
															}
															else   
																if(Component1.equals("("+summaryObj.marksObtd+ ")")&& WtId.equals(callData[x][0])){
													           
													           		if(marks>0){
													           	
													           		summaryObj.t1[f].setValue(marks);
													           	
																	}
																		
													           	 }
														
															else if(Component1.equals("("+summaryObj.maxMarks+")")&& WtId.equals(callData[x][0])){
													           	
													           		if(total>0){
													           		
													           		summaryObj.t1[f].setValue(total);
												           	
																	}
														
													           	 }    
															
															else if(Component1.equals("("+summaryObj.score+")")&& WtId.equals(callData[x][0])){
													           	
												           		if(score>0){
												           		
												           		summaryObj.t1[f].setValue(score);
												          
												           	
																}
												           	 }    
															
															
														
															
														}
													});
											
										}
											
												}
											}
											}
												
											}
									
						            		
						            	});
									}            		
						
									
									/**
                                     * Method to get the student special weightage Information
                                     */	
									connectTemp.getStudentSpWt(RegNo,progID, new AsyncCallback<CM_StudentInfoGetter[]>(){

											public void onFailure(Throwable caught) {
												MessageBox.alert(dbException,caught.getMessage());
											}

											@SuppressWarnings("deprecation")
											public void onSuccess(final CM_StudentInfoGetter[] result) {
												x=result.length;
											
											
												 for(int i=0;i<summaryObj.cb1.length;i++){
													 summaryObj.cb1[i].setChecked(false);
												 }
											
											if(result.length>0){
										
												
												 for(int i=0;i<result.length;i++){
								
												
														if(result[i].getWeightageID().equals("SW")==false){
															
															summaryObj.radioSW.setChecked(false);
															
															summaryObj.radioNSW.setChecked(true);
														
													
														
												 }
												 }
												 
											
												 for(int i=0;i<summaryObj.cb1.length;i++){
//													
														 for(int j=0;j<result.length;j++){
//															
																	
																	if(summaryObj.cb1[i].getName().equals(result[j].getWeightageID()))
																	{
																		
															
																		summaryObj.cb1[i].setChecked(true);
																	
																	
																	}
																	if(result[j].getWeightageID().equals("SW")){

																		summaryObj.radioSW.setChecked(true);
																	
																		}
																
																	 }
															
														
											}
												
												
												
											}
											}
								
						            		
						            	});		
						            					
									
								}
				            		
				            	});
				           
							}
		            		
		            	});
							}
							
								 	else{
					            		MessageBox.alert(errorMsg,msg.regNotFound());
					            		searchButton.enable();
					            		regNo.setReadOnly(false);
								 	}
					}
		        });
		            	
					}
  });
   
		       
			
		        editButton.addListener(new ButtonListenerAdapter() {
		            
					@SuppressWarnings("deprecation")
					public void onClick(Button button, EventObject e) {
						
							final String[] student =summaryObj.studentInfo();
							
							student[12]=appNo.getRawValue();
							student[13]=regNo.getRawValue();
							branchName= branchCombo.getValue();
						
							RegNo=student[13];
							FormNo=student[12];
					    
						if(valid.nullValidator(RegNo))
							
							Window.alert(msg.regNotFound());
						
						else{
					
							int check=0;
						
							if(summaryObj.checkChars()>0){
							
							summaryObj.markIVChars(summaryObj.fname);
							summaryObj.markIVChars(summaryObj.mname);
							summaryObj.markIVChars(summaryObj.lname);
							summaryObj.markIVChars(summaryObj.fname1);
							summaryObj.markIVChars(summaryObj.mname1);
							summaryObj.markIVChars(summaryObj.lname1);
							summaryObj.markIVChars(summaryObj.fname2);
							summaryObj.markIVChars(summaryObj.mname2);
							summaryObj.markIVChars(summaryObj.lname2);
							summaryObj.markIVChars(summaryObj.cityText);
							
							check++;
						
							
							}
							  if(summaryObj.checkPersonal()>0){
								  
							    	summaryObj.markIV(summaryObj.fname);
							    	//summaryObj.markIV(summaryObj.dateofbirthDateField);
							    	summaryObj.markIV(summaryObj.newCat);
							    	summaryObj.markIV(summaryObj.fname1);
							    	summaryObj.markIV(summaryObj.fname2);
							    	summaryObj.markIV(summaryObj.newGender);
							  
							    	check++;
							  }
							  
							  
							  try {
									if (summaryObj.checkDate() > 0) {
									    check++;
									}
								} catch (Exception e1) {
									MessageBox.alert("Enter a valid date");
								}
								

							  if(summaryObj.checkAddress()>0){
							
							    	summaryObj.markIV(summaryObj.street1Text);
							    	summaryObj.markIV(summaryObj.cityText);
							    	summaryObj.markIV(summaryObj.newState);
							    	summaryObj.markIV(summaryObj.pinNumber1);
							  check++;
							  }
							  
							  
							
								if(summaryObj.checkAcad()>0){
									
									check++;
						  }
							
							
								 if(summaryObj.checkAcad()==0 ){
									 if(summaryObj.marksCheck()>0){
										
										  check++;
									 }
									  }
								 
								 if(summaryObj.hpPaper.isVisible()){	
								   if(summaryObj.checkpaperCode()>0){
				
								 check++;
						  }
								 }
								if(check==0){
								
									/**
			                            * Method to Update the Personal Information.
			                            */			
							connectTemp.UpdatePersonalInfo(student,modifierid, new AsyncCallback<String>(){

							
							public void onFailure(Throwable caught) {
								MessageBox.alert(dbException,caught.getMessage());
								
							}

							public void onSuccess(String arg0) {
								
							}
							 
						 });
							
							
							
							if(summaryObj.checkAddress()==0) {
							 
						    String[]   address1 =summaryObj.addressInfo(RegNo);
							 
						    /**
                             * Method to update the student Address Information
                             */ 
						    connectTemp.UpdateaddressInfo(address1,modifierid, new AsyncCallback<String>(){

								public void onFailure(Throwable caught) {
									MessageBox.alert(dbException,caught.getMessage());
								}

								public void onSuccess(String arg0) {
									
								}
								 
							 });
						    }
							
							
							
							 if(summaryObj.hpPaper.isVisible()){
										
								 /**
	                                * Method to get the student paper Information
	                                */	
								 connectTemp.getStudentPaperInfo(RegNo,progID,new AsyncCallback<CM_StudentInfoGetter[]>(){

										public void onFailure(Throwable caught) {
											MessageBox.alert(dbException,caught.getMessage());
											
										}

										public void onSuccess(CM_StudentInfoGetter[] arg0) {
											
											if(arg0.length>0){
											
											 final String[][] paperCodes = summaryObj.StudentpaperCode();
									
											
											 for(int i=0;i<summaryObj.groupCB.length;i++){
													final String PaperCode=paperCodes[i][0];
												
													final String PaperGroup=paperCodes[i][1];
												 
											
													/**
                                                     * Method to update the Paper Code
                                                     */			
													connectTemp.UpdatepaperCode(RegNo, arg0[i].getPapercode(), PaperCode,modifierid,PaperGroup,new AsyncCallback<String>(){

																	public void onFailure(
																			Throwable caught) {
																		MessageBox.alert(dbException,caught.getMessage());
																	}

																	public void onSuccess(
																			String result) {
																	
																	}
																	
																});
																	
																
													
												 }
											}
										}
					            		
					            	});
							 }
							
										 for(int i=0;i<summaryObj.cb1.length;i++){
											
												if(summaryObj.cb1[i].isChecked()==true){
													
											
													/**
				                                     * Method to get the student Special Weightage Information
				                                     */
																	connectTemp.studentSpWt(progID, RegNo,  summaryObj.cb1[i].getName(),entity_id,modifierid,  new AsyncCallback<String>(){

																	public void onFailure(Throwable caught) {
																		MessageBox.alert(dbException,caught.getMessage());
																		
																	}

																	public void onSuccess(String arg0) {
																	
																		
																	}
																	
																});
																	
													}	
												else{
													/**
				                                     * Method to delete the student special weightage
				                                     */
													connectTemp.deleteStudentSpWt(summaryObj.cb1[i].getName(), RegNo, new AsyncCallback<String>(){

														public void onFailure(
																Throwable caught) {
															MessageBox.alert(dbException,caught.getMessage());
														}

														public void onSuccess(
																String result) {
														
															
														}
														
													});
												}
											
												
										 }
										 
										 if(summaryObj.radioSW.isChecked()==true){
											
											 /**
				                                * Method to get the special weightage
				                                */
											
												connectTemp.studentSpWt(progID, RegNo, "SW",entity_id,modifierid,  new AsyncCallback<String>(){

													public void onFailure(Throwable caught) {
													
														MessageBox.alert(dbException,caught.getMessage());
													}

													public void onSuccess(String arg0) {
							
														
													}
													
												});
										 }
										 else{
											 /**
				                                * Method to delete the student special weightage
				                                */
											 connectTemp.deleteStudentSpWt("SW", RegNo, new AsyncCallback<String>(){

													public void onFailure(
															Throwable caught) {
														MessageBox.alert(dbException,caught.getMessage());
													}

													public void onSuccess(
															String result) {
													
														
													}
													
												});
										 }

								
						
								if(summaryObj.checkAcad()==0){
									
									final String[][] hello= summaryObj.returnString();
									
										for(int i=0;i<hello.length;i++){
											
											 /**
		                                     * Method to update the callList information
		                                     */
												connectTemp.UpdatecallListInfo(RegNo, hello[i],modifierid, new AsyncCallback<String>(){

													public void onFailure(Throwable caught) {
														MessageBox.alert(dbException,caught.getMessage());
													}

													public void onSuccess(String arg0) {
													
													}
													
												});
											
											}

									
								}
					
								if(summaryObj.ug.firstDegCodeCB.isVisible()){
				
						 final String Firstdeg= summaryObj.StudentFD(summaryObj.ug.firstDegCodeCB);

						 /**
                          * Method to update the student First Degree Information
                          */
						 connectTemp.UpdateStudentFD(RegNo, Firstdeg, "U",modifierid, new AsyncCallback<String>(){

							public void onFailure(Throwable caught) {
								MessageBox.alert(dbException,caught.getMessage());
							}

							public void onSuccess(String result) {
							
							}
							 
							 
						 });
							 
								}
									
								if(summaryObj.pg.firstDegCodeCB.isVisible()){
									
									 final String PGdeg= summaryObj.StudentFD(summaryObj.pg.firstDegCodeCB);
									 
									 connectTemp.UpdateStudentFD( RegNo, PGdeg, "P",modifierid, new AsyncCallback<String>(){

									
										public void onFailure(Throwable caught) {
											MessageBox.alert(dbException,caught.getMessage());
										}

										public void onSuccess(String result) {
									
											
										}
										 
										 
									 });
								}
							 
				
											
											String catCode=student[4]+"%";
											
											connectTemp.getcos_value(progID, branchId, entity_id,catCode, new AsyncCallback<CM_ProgramInfoGetter[]>(){

												public void onFailure(Throwable caught) {
													MessageBox.alert(dbException,caught.getMessage());
													
												}

												public void onSuccess(CM_ProgramInfoGetter[] arg0) {
													String Stu_Cos;
												
													
													if(arg0.length==0)
														Stu_Cos=xCOS;
												
													else{
													
												
													String COS=arg0[0].getcos_value();
													if(COS.charAt(2)=='X'){
														Stu_Cos=COS;
													
													}
													else
														if(COS.charAt(2)==student[3].charAt(0)){
															Stu_Cos=COS;
														
														}
														else
															Stu_Cos=xCOS;
													}
													
													
													 connectTemp.UpdateStudentReg(RegNo, Stu_Cos, modifierid, new AsyncCallback<String>(){

														public void onFailure(Throwable caught) {
															MessageBox.alert(dbException,caught.getMessage());
														}

														public void onSuccess(String result) {
															
														}
														 
													 });
												}
												
											});
								
						    MessageBox.show(new MessageBoxConfig() {

                                {
                                    setMsg(
                                        msg.alert_oneditsuccess());
                             
                                    setButtons(MessageBox.OK);
                                    setCallback(new MessageBox.PromptCallback() {
                                            public void execute(
                                                String btnID,
                                                String text) {
                                            	try{
                                            		 mainVerticalPanel.clear();
                    								 onModuleLoad1();
                                            	}catch (Exception e) {
												}
                                            	
                                            }
                                        });
                                }
                            });
						
							
							
							 
						}
						
								else
									MessageBox.alert(errorMsg, correctEntriesMsg);
						
						}
						
		            }
		            });
				return mainVerticalPanel;
		
	}

}

