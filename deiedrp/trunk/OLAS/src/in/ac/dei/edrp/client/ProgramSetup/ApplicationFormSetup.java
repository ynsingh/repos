/*
 * @(#) ApplicationFormSetup.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.edrp.client.ProgramSetup;


import in.ac.dei.edrp.client.FormApplicationInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
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
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;

/**
 * this class is for Application Form Setup
 * 
 * @version 1.0 10 February 2012
 * @author UPASANA KULSHRESTHA
 */

public class ApplicationFormSetup {
	
	CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	Button submit=new Button(constants.submit());
    public VerticalPanel panel = new VerticalPanel();
    
    String errorMsg;
    String confirm;
	private String userId;
	public List<String> entityList;
	public List<String> resultList;

	/**
	 * This constructor set the userId
	 * @param Uid
	 */
	public ApplicationFormSetup(String Uid) {
        this.userId = Uid;
    }
    
	/**
	 * This function add the grid of the programs in the main vertical panel
	 */
	public void applicationForm() {
		panel.clear();
		
		Panel mainPanel=new Panel();
		mainPanel.clear();
		mainPanel.setTitle(constants.heading_applicationform());
		
	    final VerticalPanel detailPanel = new VerticalPanel();
	    final VerticalPanel programPanel = new VerticalPanel();
	    detailPanel.clear();
	    programPanel.clear();
	    
		errorMsg= msgs.error();
		confirm=msgs.confirm();
		final FlexTable flexTable = new FlexTable();

		final TextField formName=new TextField();
		formName.setWidth(188);
		
		flexTable.setWidget(0, 0, new Label(constants.label_form_name()));
		flexTable.setWidget(0, 1, formName);
		 
		detailPanel.setSpacing(10);
		detailPanel.add(flexTable);
		 
		connectService.getUniversityEntities(userId,new AsyncCallback<List<FormApplicationInfoGetter>>() {

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert(arg0.toString());
				 
			}

			public void onSuccess(List<FormApplicationInfoGetter> data) {
				final ListBox entity=new ListBox();
				entity.addItem(constants.select());
				entity.setItemSelected(0,true);
				
				if(data.size()<1){
					MessageBox.alert(errorMsg, msgs.noProgram());
				}
                for (int i = 0; i < data.size(); i++) {
                    entity.addItem(data.get(i).getEntityName(), data.get(i).getEntityId());
                }
				
				FlexTable entityFlex=new FlexTable();
		        
		        entityFlex.setWidget(0, 0, new Label(constants.selectEntity()));
		        entityFlex.setWidget(0, 1, entity);
		        detailPanel.add(entityFlex);
		        
		        entity.addChangeHandler(new ChangeHandler() {
		             
					@Override
					public void onChange(ChangeEvent arg0) {
						if (entity.getSelectedIndex()!=0) {
		                	 
							programPanel.clear();
							onChangeEvent(entity.getValue(entity.getSelectedIndex()),formName,programPanel,entity);
		            		 		                	 
		                 }
						else {
							
							programPanel.clear();
						}
						
					}   
		         });
			}
		});
		 
		 mainPanel.setSize("600px", "500px");

	     mainPanel.add(detailPanel);
	     mainPanel.add(programPanel);
	     panel.add(mainPanel);
	}

	public void onChangeEvent(final String entityIdDesc, final TextField formName, final VerticalPanel programPanel, final ListBox entity){
		
		 programPanel.clear();
		 programPanel.setSpacing(10);
		 List<String> entityList1=new ArrayList<String>();
		 entityList1.add(entityIdDesc);
		 entityList= new ArrayList<String>();
		 resultList= new ArrayList<String>();
		 resultList.add(entityIdDesc);
		 resultList=getEntityTree(entityList1,entityIdDesc,formName,programPanel,entity);
		 
	}

	public void createGrid(List<String> li, final String entityIdDesc,
			final TextField formName, final VerticalPanel programPanel, final ListBox entity) {
		programPanel.clear();
		programPanel.setSpacing(10);
		connectService.getUniversityProgram(userId,li,entityIdDesc, new AsyncCallback<List<FormApplicationInfoGetter>>() 
	    		 {
					public void onFailure(Throwable caught) {
						MessageBox.alert(constants.dbError(), caught.getMessage());
					}
					public void onSuccess(List<FormApplicationInfoGetter> result) {
						if(result.size()<1){
							MessageBox.alert(errorMsg, msgs.noProgram());
						}
						
						programPanel.add( new Label(constants.selectProgram()));
						Object[][] data = new Object[result.size()][4];
						for (int i = 0; i < result.size(); i++) {
						
							data[i][0] = result.get(i).getProgramName();
							data[i][1]= result.get(i).getProgramId();
							data[i][2]=result.get(i).getOfferedBy();
							data[i][3]=result.get(i).getCity();
						}
						
						final CheckboxSelectionModel cbSelectionModel=new CheckboxSelectionModel();
						RecordDef recordDef = new RecordDef(new FieldDef[] {
								new StringFieldDef("program"),
								new StringFieldDef("programId"),
								new StringFieldDef("offeredBy"),
								new StringFieldDef("city")
								});

						GridPanel grid = new GridPanel();

						MemoryProxy proxy = new MemoryProxy(data);

						ArrayReader reader = new ArrayReader(recordDef);
						Store store = new Store(proxy, reader);
						

						store.load();
						grid.setStore(store);
						BaseColumnConfig[] columns = new BaseColumnConfig[] {
								
								new CheckboxColumnConfig(cbSelectionModel),

								new ColumnConfig(constants.label_programname(),
										"program", 80, true, null,
										"program") 
								};

						ColumnModel columnModel = new ColumnModel(columns);

						grid.setColumnModel(columnModel);
						grid.setFrame(true);

						grid.setAutoExpandColumn("program");
						grid.setSelectionModel(cbSelectionModel);
						grid.setWidth(500);
						grid.setHeight(300);
						grid.setTitle(constants.heading_programlist());
						grid.setStripeRows(true);
						
						programPanel.add(grid);
						
						programPanel.add(submit);
					     
						submit.addListener(new ButtonListenerAdapter() {
							public void onClick(Button button, EventObject e) {
								final Record[] records = cbSelectionModel.getSelections();
																
								if (formName.getText().length() < 1) {
									MessageBox.alert(errorMsg,msgs.errorPleaseEnterForm());
								}
								
								else if (records.length < 1) {
									MessageBox.alert(errorMsg, msgs.selectProgramName());
								}
								else {
									MessageBox.confirm(confirm,	msgs.alert_confirmentries(),new MessageBox.ConfirmCallback() {
													
												public void execute(String btnID) {
													if (btnID.matches(constants.yesButton())) 
													{
														
														String[] progId = new String[records.length];
														String[] progName=new String[records.length];
														String[] offeredEntity=new String[records.length];
														String[] cityLocation=new String[records.length];
														final String appFormName=formName.getText();
														
														Record record = null;
														for(int j=0;j<records.length;j++){
															record = records[j];
															
															progId[j]=record.getAsString("programId");
															progName[j]=record.getAsString("program");
															offeredEntity[j]=record.getAsString("offeredBy");
															cityLocation[j]=record.getAsString("city");
														}
														final List<String> programId= Arrays.asList(progId);
														final List<String> programName=Arrays.asList(progName);
														final List<String> offeredByEntity=Arrays.asList(offeredEntity);
														final List<String> city=Arrays.asList(cityLocation);
														/**
														 * This method is used for checking for duplicate formName 
														 * @param appFormName
														 * @param userId
														 * @return count of formName
														 */														
														connectService.checkFormName(appFormName,userId,new AsyncCallback<Integer>() {

															@Override
															public void onFailure(Throwable arg0) 
															{
																MessageBox.alert(constants.dbError(),arg0.getMessage());																
															}

															@Override
															public void onSuccess(Integer count) 
															{
																if(count>0){
																	MessageBox.alert(errorMsg, msgs.errorFormNameExists());
																}
																else{
																	
																	/**
																	 * This method is used for inserting form details to database
																	 * @param List of programId
																	 * @param List of programName
																	 * @param appFormName
																	 * @param userId
																	 * @return Boolean value
																	 */
																	connectService.setProgramForm(programId,programName,offeredByEntity,city,appFormName,userId,entityIdDesc,new AsyncCallback<Boolean>() 
																		{
																		public void onFailure(Throwable caught) 
																		{
																			MessageBox.alert(constants.dbError(),caught.getMessage());
																		}

																		public void onSuccess(Boolean flag) 
																		{
																			if(flag){
																				MessageBox.show(new MessageBoxConfig() {
																					{
																						setMsg(msgs.alert_successfulentry());
																						setButtons(MessageBox.OK);
																						cbSelectionModel.clearSelections();
																						programPanel.clear();
																						entity.setSelectedIndex(0);
																					}
																			
																				});
																			}
																			else {
																				MessageBox.show(new MessageBoxConfig() {
																					{
																						setMsg(msgs.requestFailed());
																						setButtons(MessageBox.OK);
																						cbSelectionModel.clearSelections();
																					}
																			
																				});
																				
																			} 
																			
																		}
																	});
																}
															}
															
														});
														cbSelectionModel.clearSelections();
														formName.reset();
														
														}
													}
												});
											}
											
									}
							
						});

					}
	             });
		
	}

	private List<String> getEntityTree(List<String>entityList1, final String entityIdDesc, final TextField formName, final VerticalPanel programPanel, final ListBox entity) {
		FormApplicationInfoGetter inputBean= new FormApplicationInfoGetter();
		inputBean.setEntityIdList(entityList1);
		if(entityList1.size()==0){
			createGrid(resultList,entityIdDesc,formName,programPanel,entity);
			return resultList;
		}
		else{
			try{
				connectService.getEntityRelate(entityList1,new AsyncCallback<List<FormApplicationInfoGetter>>() {
					public void onFailure(Throwable arg0) {
					}
	
					public void onSuccess(List<FormApplicationInfoGetter> result) {
						entityList= new ArrayList<String>();
						for(int k=0;k<result.size();k++) {
							entityList.add(result.get(k).getOwnerEntityName());
							resultList.add(result.get(k).getOwnerEntityName());
				        }
						getEntityTree(entityList,entityIdDesc,formName,programPanel,entity);
					}
				 });
			}
			catch (Exception e) {
				System.out.println(e+"Exception");
			}
		}
		return resultList;
	}
	
}
