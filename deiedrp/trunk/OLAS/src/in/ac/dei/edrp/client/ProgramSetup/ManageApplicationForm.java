/*
 * @(#) ManageApplicationForm.java
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
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
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
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListener;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;

/**
 * this class is for Manage Application Form
 * 
 * @version 1.0 10 February 2012
 * @author UPASANA KULSHRESTHA
 */
public class ManageApplicationForm {
	
	CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	
    public VerticalPanel verPanel = new VerticalPanel();

    String errorMsg;
    String confirm;
	private String userId;
	public List<String> entityList;
	public List<String> resultList;

	/**
	 * This constructor set the userId
	 * @param uId
	 */
	public ManageApplicationForm(String uId) {
		this.userId = uId;
	}

	/**
	 * This function add the grid to manage program application form   
	 */
	public void manageapplicationform() {
		 verPanel.clear();	
		 final VerticalPanel panel = new VerticalPanel();
		 panel.clear();
		 
		 final VerticalPanel panel1 = new VerticalPanel();
		 panel1.clear();
		 
		 final VerticalPanel panel2 = new VerticalPanel();
		 panel2.clear();
		 
		 Panel mainPanel=new Panel();
		 mainPanel.setTitle(constants.heading_applicationformManage());
		 
		 final VerticalPanel gridPanel = new VerticalPanel();
		 gridPanel.clear();
		 
		 errorMsg= msgs.error();
		 confirm=msgs.confirm();
		 
		 connectService.getUniversityEntities(userId,new AsyncCallback<List<FormApplicationInfoGetter>>() {

				@Override
				public void onFailure(Throwable arg0) {
					MessageBox.alert(arg0.toString());
					 
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
			        panel1.add(entityFlex);
			        
			        entity.addChangeHandler(new ChangeHandler() {
			             
						@Override
						public void onChange(ChangeEvent arg0) {
							panel2.clear();
							if (entity.getSelectedIndex()!=0) {
								
								panel2.clear();
								gridPanel.clear();
								
								FlexTable flexTable = new FlexTable();
								final ListBox formname=new ListBox();
								formname.setVisible(true);
								
								flexTable.setWidget(0, 0, new Label(constants.label_form_name()));
								flexTable.setWidget(0, 1, formname);
								
								 
								panel2.add(flexTable);
								/**
								 * This method is used to get form name from the database
								 * @param userId
								 * @return List of form details
								 */
							 connectService.getFormList(userId,entity.getValue(entity.getSelectedIndex()),
						                new AsyncCallback<List<FormApplicationInfoGetter>>() {
						                    public void onFailure(Throwable arg0) {
						                    }
											public void onSuccess(List<FormApplicationInfoGetter> result) {
						
												formname.clear();
												formname.addItem(constants.select());
												formname.setItemSelected(0,true);
												
												if(result.size()<1){
													MessageBox.alert(errorMsg, msgs.error_norecord("forms"));
												}
						                        for (int i = 0; i < result.size(); i++) {
						                            formname.addItem(result.get(i).getFormName(), result.get(i).getFormNumber());
						                        }
						                        
						                        
											}
						     });
							 
							 formname.addChangeHandler(new ChangeHandler() {
					             public void onChange(ChangeEvent arg0) {
					                 if (formname.getSelectedIndex()==0) {
					                	 gridPanel.clear();
					                 } 
					                 else {
					                	 refreshGrid(gridPanel,formname,entity.getValue(entity.getSelectedIndex()));
					                 }
					             }   
					         });
						
							}
							else {
								
								panel2.clear();
								gridPanel.clear();
								
							}
							
						}   
			         });

			        
					
				}
			});


		 mainPanel.add(panel1);	
		 mainPanel.add(panel2);
		 panel.setSpacing(10);
		 panel.add(gridPanel);
		 mainPanel.setSize("600px", "450px");
	     mainPanel.add(panel);
	     verPanel.add(mainPanel);
	     
	}
	
	/**
	 * This function refresh the grid after deleting or adding any row 
	 * @param gridPanel
	 * @param formname
	 * @param entityId 
	 */
	public void refreshGrid(final VerticalPanel gridPanel,final ListBox formname, final String entityId){
		gridPanel.clear();
		String forms = formname.getValue(formname.getSelectedIndex());
	   	 
		/**
		 * This method is used to get program name from the database for the given form
		 * @param userId
		 * @return List of form details
		 */
   		 connectService.getFormDetails(forms,userId,entityId,
                new AsyncCallback<List<FormApplicationInfoGetter>>() {
					public void onFailure(Throwable arg0) {
						
					}
					public void onSuccess(List<FormApplicationInfoGetter> detail) {
						if(detail.size()<1){
							
							manageapplicationform();
						}
						else
						{
							Object[][] data1 = new Object[detail.size()][2];
							for (int i = 0; i < detail.size(); i++) {
								data1[i][0] = detail.get(i).getProgramName();
								data1[i][1]= detail.get(i).getProgramId();
							}
							RecordDef recordDef = new RecordDef(new FieldDef[] {
								new StringFieldDef("program"),
								new StringFieldDef("programId")
								});
							MemoryProxy proxy = new MemoryProxy(data1);
							ArrayReader reader = new ArrayReader(recordDef);
							Store store1 = new Store(proxy, reader);
							GridPanel gridp = new GridPanel();
							store1.load();
							gridp.setStore(store1);

							final CheckboxSelectionModel cbSelectionModel=new CheckboxSelectionModel();
							BaseColumnConfig[] columns = new BaseColumnConfig[] {
								new CheckboxColumnConfig(cbSelectionModel),
								new ColumnConfig(constants.label_programname(),
										"program", 80, true, null,
										"program")
								};
							ColumnModel columnModel = new ColumnModel(columns);
							gridp.setColumnModel(columnModel);
							gridp.setFrame(true);
							gridp.setStripeRows(true);
							gridp.setAutoExpandColumn("program");
							gridp.setSelectionModel(cbSelectionModel);
							gridp.setWidth(500);
							gridp.setHeight(350);
							gridp.setTitle(constants.heading_programlist());
						
							Toolbar toolbar = new Toolbar();
							toolbar.addFill();
							gridp.setTopToolbar(toolbar);
						
							final ToolbarButton addButton = new ToolbarButton(constants.addButton());
							final ToolbarButton delete = new ToolbarButton(constants.delete());
							
							addButton.setDisabled(false);
							delete.setDisabled(true);
						
							cbSelectionModel.addListener(new RowSelectionListener() {
								public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
									if(cbSelectionModel.hasSelection()){
										addButton.setDisabled(true);
										delete.setDisabled(false);
									}
									else{
										addButton.setDisabled(false);
										delete.setDisabled(true);
									}
								}
								
								public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
									if(cbSelectionModel.hasSelection()){
										addButton.setDisabled(true);
										delete.setDisabled(false);
									}
									else{
										addButton.setDisabled(false);
										delete.setDisabled(true);
									}
								}

								public boolean doBeforeRowSelect(RowSelectionModel sm, int rowIndex,boolean keepExisting, Record record) {
									return true;
								}

								public void onSelectionChange(RowSelectionModel sm) {
									
								}
							});
						
							addButton.addListener(new ButtonListenerAdapter(){
								public void onClick(Button button, EventObject e){
									List<String> entityList1=new ArrayList<String>();
									 entityList1.add(entityId);
									 entityList= new ArrayList<String>();
									 resultList= new ArrayList<String>();
									 resultList.add(entityId);
									 resultList=getEntityTree(entityList1,gridPanel,formname,entityId);
								}
							});
						
							toolbar.addButton(addButton);
							
							delete.addListener(new ButtonListenerAdapter() {
								public void onClick(Button button, EventObject e) {
									onDelete(cbSelectionModel.getSelections(),gridPanel,formname,entityId);
								}
							});

							toolbar.addButton(delete);
							
							gridPanel.add(gridp);
							
						}
					}
                });
	}
	
	/**
	 * This function delete the selected rows from the database
	 * @param records
	 * @param gridPanel
	 * @param formname
	 * @param entityId 
	 */
	public void onDelete(final Record[] records,final VerticalPanel gridPanel,final ListBox formname, final String entityId){
			gridPanel.clear();
			if (records.length < 1) {
				MessageBox.alert(errorMsg, msgs.select_recordsdeletion());
			} 
			else 
			{
				MessageBox.confirm(confirm,	msgs.alert_ondelete(),new MessageBox.ConfirmCallback() 
				{
					public void execute(String btnID) 
					{
						if (btnID.matches(constants.yesButton())) 
						{
							String selectedFormName=formname.getValue(formname.getSelectedIndex());
							List<String> prgId=new ArrayList<String>();
							for (int i = 0; i < records.length; i++) 
							{
								prgId.add(records[i].getAsString("programId"));
							}
							
							/**
							 * This method is used to delete selected program name from the program form table 
							 * @param userId
							 * @param selectedFormName
							 * @return count
							 */
							connectService.deleteProgFormDetail(prgId,selectedFormName,userId,entityId,new AsyncCallback<Integer>() 
							{
								public void onFailure(Throwable caught) 
								{
									MessageBox.alert(constants.dbError(),caught.getMessage());
								}
								public void onSuccess(Integer num) 
								{
									if(num>0)
									{
										refreshGrid(gridPanel,formname,entityId);
										MessageBox.show(new MessageBoxConfig() {
											{
												setMsg(msgs.alert_ondeletesuccess());
												setButtons(MessageBox.OK);
											}
										});
									}
								}
							});
						}
						else {
							refreshGrid(gridPanel,formname,entityId);
						}
					}
				});
			}
	}
	
	/**
	 * This function add the programs in the selected form 
	 * @param resultList 
	 * @param gridPanel
	 * @param formname
	 * @param entityId 
	 */
	public void onAdd(List<String> resultList, final VerticalPanel gridPanel,final ListBox formname, final String entityId){
		
		final Panel vPanel = new Panel();
		String selectedFormName=formname.getItemText(formname.getSelectedIndex());
		final Label formNameLabel = new Label();
		final Label formLabel = new Label();
		final Button submit=new Button(constants.submit());
		formNameLabel.setText(selectedFormName);
		formNameLabel.setStyleName(constants.jackStyle());
		formLabel.setText(constants.label_form_name());
		formLabel.setStyleName(constants.jackStyle());
		vPanel.add(formLabel);
		vPanel.add(formNameLabel);
		
		
		/**
		 * This method is used to get program name of university which are not present 
		 * in program form table for the selected form 
		 * @param userId
		 * @param selectedFormName
		 * @return List of programs
		 */
		connectService.getProgramsDetails(resultList,selectedFormName,entityId ,userId,
                new AsyncCallback<List<FormApplicationInfoGetter>>() {

					public void onFailure(Throwable arg0) {
					}

					public void onSuccess(List<FormApplicationInfoGetter> result) {
						if(result.size()<1){
							MessageBox.alert(errorMsg, msgs.error_record());
						}
						else{
							Object[][] dataAdd = new Object[result.size()][4];
							
							for (int i = 0; i < result.size(); i++) {
								dataAdd[i][0] = result.get(i).getProgramName();
								dataAdd[i][1]= result.get(i).getProgramId();
								dataAdd[i][2]=result.get(i).getOfferedBy();
								dataAdd[i][3]=result.get(i).getCity();
							}
							RecordDef recordDef1 = new RecordDef(new FieldDef[] {
								new StringFieldDef("program"),
								new StringFieldDef("programId"),
								new StringFieldDef("offeredBy"),
								new StringFieldDef("city")
								});
							MemoryProxy proxy1 = new MemoryProxy(dataAdd);
							ArrayReader reader1 = new ArrayReader(recordDef1);
							Store store = new Store(proxy1, reader1);
							GridPanel gridAdd=new GridPanel();
							store.load();
							gridAdd.setStore(store);
							final CheckboxSelectionModel cbSelectionModel1=new CheckboxSelectionModel();
							BaseColumnConfig[] columns = new BaseColumnConfig[] {
								new CheckboxColumnConfig(cbSelectionModel1),
								new ColumnConfig(constants.label_programname(),
										"program", 80, true, null,
										"program") 
								};
							ColumnModel columnModel = new ColumnModel(columns);
							gridAdd.setColumnModel(columnModel);
							gridAdd.setAutoExpandColumn("program");
							gridAdd.setSelectionModel(cbSelectionModel1);
							gridAdd.setWidth(500);
							gridAdd.setHeight(300);
							gridAdd.setTitle(constants.heading_programlist());
							vPanel.add(gridAdd);
							BorderLayoutData bld=new BorderLayoutData(RegionPosition.CENTER);
						
							bld.setMargins(6,6,6,6);
							final Window window = new Window(); 
							window.setTitle(constants.heading_updatedetails()); 
							window.setWidth(550);
							window.setHeight(400);
							window.setResizable(false); 
							window.setLayout(new BorderLayout()); 
							window.setPaddings(5);
							window.setButtonAlign(Position.CENTER); 
							window.addButton(submit);
							window.setAutoScroll(true); 

							window.add(vPanel,bld);
							window.setCloseAction(Window.CLOSE); 
							window.setPlain(true); 
							window.setFrame(true); 
							window.setClosable(true); 
							window.setModal(true); 
							window.show();
						
							submit.addListener(new ButtonListenerAdapter() {
								public void onClick(Button button, EventObject e) {
									final Record[] recordsAdd = cbSelectionModel1.getSelections();
									if (recordsAdd.length < 1) {
										MessageBox.alert(errorMsg, msgs.errorNoRecordSelected());
									}
									else
									{
										MessageBox.confirm(confirm,	msgs.alert_confirmentries(),new MessageBox.ConfirmCallback() 
										{
											public void execute(String btnID) 
											{
												if (btnID.matches(constants.yesButton())) 
												{
													String selectedForm=formname.getValue(formname.getSelectedIndex());
													String formName=formname.getItemText(formname.getSelectedIndex());
													
													List<String> progId = new ArrayList<String>();
													List<String> offeredEntity = new ArrayList<String>();
													List<String> city=new ArrayList<String>();
													
													for (int i = 0; i < recordsAdd.length; i++) 
													{
														progId.add(recordsAdd[i].getAsString("programId"));
														offeredEntity.add(recordsAdd[i].getAsString("offeredBy"));
														city.add(recordsAdd[i].getAsString("city"));
													}
													
													
													/**
													 * This method insert the programs in program form table
													 * @param progId
													 * @param selectedFormName
													 * @param userId
													 * @return count of inserted rows
													 */
													connectService.addProgFormDetail(progId,offeredEntity,city,selectedForm,formName,userId,entityId,new AsyncCallback<Integer>() 
														{
														public void onFailure(Throwable caught) 
														{
															MessageBox.alert(constants.dbError(),caught.getMessage());
														}
														public void onSuccess(Integer count) 
														{
															if(count>0){
																MessageBox.show(new MessageBoxConfig() {
																	{
																		setMsg(msgs.alert_successfulentry());
																		setButtons(MessageBox.OK);
																		refreshGrid(gridPanel,formname,entityId);
																		window.close();
																
																	}
																});
															}
														}
													});
												}
												else {
													refreshGrid(gridPanel,formname,entityId);
												
												}
											}
									});
								}
							}
						});
					}
				}
             });
			}
	private List<String> getEntityTree(List<String>entityList1, final VerticalPanel gridPanel, final ListBox formname, final String entityId) {
		
		FormApplicationInfoGetter inputBean= new FormApplicationInfoGetter();
		inputBean.setEntityIdList(entityList1);
		if(entityList1.size()==0){
			onAdd(resultList,gridPanel,formname,entityId);
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
						getEntityTree(entityList, gridPanel, formname, entityId);
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
