/*
 * @(#) ProgramSearchSetup.java
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

import java.util.List;
import in.ac.dei.edrp.client.ProgramSearchInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.summarysheet.SummarySheet;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
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
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;

/**
 * This class is to Search University programs
 * 
 * @version 1.0 18 February 2012
 * @author UPASANA KULSHRESTHA
 */

public class ProgramSearchSetup {
	
	CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	public VerticalPanel vPanel=new VerticalPanel();
	
	String errorMsg;
    String confirm;
	private String userId;
	private String userEmailId;

	/**This constructor set the user Id 
	 *@param uId
	 */
	public ProgramSearchSetup(String uId) {
		this.userId = uId;
	}
	
	public ProgramSearchSetup(String uId, String userEmail) {
		this.userId = uId;
		this.userEmailId=userEmail;
		
	}

	/**This function add the first panel in main vertical panel
	 * 
	 */
	public void searchProgramSetup() {

		errorMsg=msgs.error();
		confirm=msgs.confirm();
		vPanel.clear();
		
		final Panel firstPanel=new Panel();
		firstPanel.setShadow(true);
		firstPanel.setPaddings(10,30,30,10);
		
		final Panel secondPanel=new Panel();
		secondPanel.setPaddings(10,30,30,10);
		secondPanel.setFrame(true);
		
		final VerticalPanel firstVerPanel=new VerticalPanel();
		final VerticalPanel secondVerPanel=new VerticalPanel();
		
		//widgets for first panel
		firstPanel.setTitle(constants.searchByEntity());
		firstPanel.setFrame(true);
		
		final TextBox entitySearch=new TextBox();
		entitySearch.setWidth("340");
		final Button entitySearchButton=new Button(constants.search());
		
		final FlexTable locationTable=new FlexTable();
		
	    final ListBox locationListBox=new ListBox();
		Label locationLabel=new Label(constants.selectLocation());
		locationTable.setWidget(0, 0, locationLabel);
		locationTable.setWidget(0, 1, locationListBox);
		
		final FlexTable entityTable=new FlexTable();
		entityTable.setWidget(0, 0, entitySearch);
		entityTable.setWidget(0, 1, entitySearchButton);
		
		firstPanel.add(locationTable);
		entitySearch.setVisible(false);
		entitySearchButton.setVisible(false);
		firstPanel.add(entityTable);
		
		/**
		 * This method get the List of locations of the entities offered by the university 
		 *
		 * @param userId
		 * @return List of type ProgramSearchInfoGetter 
		 */	
		connectService.getLocationDetails(userId,new AsyncCallback<List<ProgramSearchInfoGetter>>(){

			@Override
			public void onFailure(Throwable caught) {
				MessageBox.alert(constants.dbError(),caught.getMessage());
			}

			@Override
			public void onSuccess(List<ProgramSearchInfoGetter> locationList) {
				
				locationListBox.addItem(constants.select());
				locationListBox.setItemSelected(0,true);
				
				if(locationList.size()<1){
					MessageBox.alert(errorMsg, msgs.noProgram());
				}
                for (int i = 0; i < locationList.size(); i++) {
                    locationListBox.addItem(locationList.get(i).getEntityLocation());
                }
				               
            }
		});
		
		locationListBox.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent arg0) {
            	secondVerPanel.clear();
				firstVerPanel.clear();
				entitySearch.setText("");
				secondPanel.removeFromParent();
				if(locationListBox.getSelectedIndex()!=0){
					
					entitySearch.setVisible(true);
					entitySearchButton.setVisible(true);
					
					/*String locationValue="%"+locationListBox.getValue(locationListBox.getSelectedIndex())+"%";*/
					String locationValue=locationListBox.getValue(locationListBox.getSelectedIndex());
					String entity="";
					char searchKey[]=entity.toCharArray();
					addEntity(searchKey, locationValue, firstVerPanel, secondPanel, secondVerPanel,entitySearch);
				}
				else{
					entitySearch.setVisible(false);
					entitySearchButton.setVisible(false);
					
				}
			
            }   
        });
		
		entitySearchButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) 
			{
				secondVerPanel.clear();
				firstVerPanel.clear();
				secondPanel.removeFromParent();
				if(locationListBox.getSelectedIndex()==0){
					MessageBox.alert(errorMsg,msgs.errorPleaseSelectLocation());
				}
				else{
					String locationValue=locationListBox.getValue(locationListBox.getSelectedIndex());
					/*String locationValue="%"+locationListBox.getValue(locationListBox.getSelectedIndex())+"%";*/
					char searchKey[]=entitySearch.getText().trim().toCharArray();
					
					/*if(searchKey.length<1){
						MessageBox.alert(errorMsg,msgs.errorPleaseEnterValue());
					}
					else{*/
						addEntity(searchKey, locationValue, firstVerPanel, secondPanel, secondVerPanel,entitySearch);
					//}
				}
			}
		});
		
		firstPanel.add(firstVerPanel);
		vPanel.add(firstPanel);
	}
	
	/**
	 * This function add entity grid in the first panel
	 * 
	 * @param searchKey
	 * @param locationValue
	 * @param firstVerPanel
	 * @param secondPanel
	 * @param secondVerPanel
	 * @param entitySearch 
	 */
	protected void addEntity(char[] searchKey,String locationValue,final VerticalPanel firstVerPanel,final Panel secondPanel, final VerticalPanel secondVerPanel, final TextBox entitySearch) {
		
		firstVerPanel.clear();

		String searchEntityValue="%";
		
		for(int i=0;i<searchKey.length;i++){
			searchEntityValue=searchEntityValue+searchKey[i]+"%";
		}
		
		/**
		 * This method get the List of entities offered by the university
		 * @param searchEntityValue,
		 * @param userId
		 * @param locationValue
		 * @return List of type ProgramSearchInfoGetter 
		 */
		connectService.searchByEntity(searchEntityValue,userId,locationValue,new AsyncCallback<List<ProgramSearchInfoGetter>>(){

			public void onFailure(Throwable caught) {
				MessageBox.alert(constants.dbError(),caught.getMessage());
			}

			public void onSuccess(List<ProgramSearchInfoGetter> detail) {
			
				if(detail.size()<1){
					MessageBox.alert(errorMsg, msgs.noGridCrieteria());
					entitySearch.setText("");
				}
				else
				{
					Object[][] entityData = new Object[detail.size()][5];
					for(int i = 0; i < detail.size(); i++){
						entityData[i][0] = detail.get(i).getUniversityId();
						entityData[i][1]= detail.get(i).getEntityId();
						entityData[i][2]= detail.get(i).getEntityName();
						entityData[i][3]= detail.get(i).getEntityLocation();
						entityData[i][4]= detail.get(i).getEntityCode();
						
					}
					RecordDef entityRecordDef = new RecordDef(new FieldDef[] {
							new StringFieldDef("universityId"),
							new StringFieldDef("entityId"),
							new StringFieldDef("entityName"),
							new StringFieldDef("entityLocation"),
							new StringFieldDef("entityCode")
					});
					MemoryProxy entityProxy = new MemoryProxy(entityData);
					ArrayReader entityReader = new ArrayReader(entityRecordDef);
					Store entityStore = new Store(entityProxy, entityReader);
					final GridPanel entityGrid = new GridPanel();
					entityStore.load();
					entityGrid.setStore(entityStore);
	
					final CheckboxSelectionModel cbSelectionModel=new CheckboxSelectionModel();
						
					BaseColumnConfig[] columns = new BaseColumnConfig[] {
						new CheckboxColumnConfig(cbSelectionModel),
						new ColumnConfig(constants.entityInstituteName(),
								"entityName", 200, false, null,
								"entityName"),
						new ColumnConfig(constants.location(), "entityLocation",200,true,null)		
					};
					ColumnModel columnModel = new ColumnModel(columns);
					entityGrid.setColumnModel(columnModel);
					entityGrid.setFrame(true);
					entityGrid.setStripeRows(true);
					entityGrid.setAutoExpandColumn("entityName");
					entityGrid.setSelectionModel(cbSelectionModel);
					entityGrid.setWidth(400);
					entityGrid.setHeight(200);
					entityGrid.setTitle(constants.searchByEntity());
					
					/*Toolbar toolbar = new Toolbar();
	         	    toolbar.addFill();
	         	    entityGrid.setTopToolbar(toolbar);
					
	         	    final ToolbarButton submitButton = new ToolbarButton(constants.submit());
	         	    toolbar.addButton(submitButton);
	         	        
	         	    submitButton.setDisabled(true);*/
					
		         	cbSelectionModel.addListener(new RowSelectionListenerAdapter() {
		         		  public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
								if(cbSelectionModel.getSelections().length==1){
									
									final Record[] records = cbSelectionModel.getSelections();
									addProgram(records,secondPanel, secondVerPanel);
								}
								else{
									
									secondVerPanel.clear();
									secondPanel.removeFromParent();	
								}
							}
							public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
								if(cbSelectionModel.getSelections().length==1){
									
								}
								else{
									
									secondVerPanel.clear();
									secondPanel.removeFromParent();
								}
							}
		         	});
		         	   
		         	
					firstVerPanel.add(entityGrid);
				}
			}
		});
	}

	/**This function add program in the second panel 
	 * 
	 * @param records
	 * @param secondPanel
	 * @param secondVerPanel
	 */
	protected void addProgram(Record[] records, Panel secondPanel,final VerticalPanel secondVerPanel)
	{
		secondVerPanel.clear();
		secondPanel.setTitle(constants.searchByProgram());
		secondPanel.add(secondVerPanel);
		FlexTable programTable=new FlexTable();
		final TextBox programSearch=new TextBox();
		programSearch.setWidth("340");
		final Button programSearchButton=new Button(constants.search());
		programTable.setWidget(0, 0, programSearch);
		programTable.setWidget(0, 1, programSearchButton);
		
		final VerticalPanel programPanel=new VerticalPanel();
		
		secondVerPanel.add(programTable);
		if (records.length < 1) {
			MessageBox.alert(errorMsg, msgs.errorNoRecordSelected());
		}
		if(records.length >1){
			MessageBox.alert(errorMsg, msgs.errorSelectOneRecord());
		}
		else 
		{
			final String selectedEntity=records[0].getAsString("entityId");
			final String entityName=records[0].getAsString("entityName");
			final String entityCode=records[0].getAsString("entityCode");
			
			/**
			 * This method get the list of programs for selected Entity
			 * @param selectedEntity
			 * @param userId
			 * @return List of type ProgramSearchInfoGetter
			 */
			connectService.getEntityPrograms(selectedEntity,userId,new AsyncCallback<List<ProgramSearchInfoGetter>>(){

				public void onFailure(Throwable caught) {
				
					MessageBox.alert(constants.dbError(),caught.getMessage());
				}
				public void onSuccess(List<ProgramSearchInfoGetter> detail) {
					
					addProgramGrid(detail,programPanel,secondVerPanel,selectedEntity,entityName,entityCode);
				}
			});
			programSearchButton.addListener(new ButtonListenerAdapter(){
				public void onClick(Button button, EventObject e) {
					programPanel.clear();
					String searchProgramValue="%";
					char searchKey[]=programSearch.getText().trim().toCharArray();
					/*if(searchKey.length<1){
						MessageBox.alert(errorMsg,msgs.errorPleaseEnterValue());
					}else{	*/
						for(int i=0;i<searchKey.length;i++){
							searchProgramValue=searchProgramValue+searchKey[i]+"%";
						}
					
						/**
						 * This method get the List of program offered by the university for selected entity
						 * @param searchProgramValue,
						 * @param selectedEntity
						 * @return List of type ProgramSearchInfoGetter 
						 */
						connectService.searchByProgram(searchProgramValue,selectedEntity,new AsyncCallback<List<ProgramSearchInfoGetter>>(){

							@Override
							public void onFailure(Throwable caught) {
								MessageBox.alert(constants.dbError(),caught.getMessage());
							}

							@Override
							public void onSuccess(List<ProgramSearchInfoGetter> details) {
								if(details.size()<1){
									MessageBox.alert(errorMsg, msgs.noGridCrieteria());
									programSearch.setText("");
								}
								else
								{
									addProgramGrid(details, programPanel, secondVerPanel,selectedEntity,entityName,entityCode);
								}
							}
						});
					//}
				}
			});
			vPanel.add(secondPanel);
		}
	}
	
	/**This function create the grid for the program for the selected entities
	 * 
	 * @param detail
	 * @param programPanel
	 * @param secondVerPanel
	 * @param entityCode 
	 * @param entityName 
	 * @param selectedEntity 
	 * @param entityName 
	 * @param selectedEntity 
	 * @param entityCode 
	 */
	private void addProgramGrid(List<ProgramSearchInfoGetter> detail, VerticalPanel programPanel, VerticalPanel secondVerPanel, final String selectedEntity, final String entityName, final String entityCode){
		
		Object[][] programData = new Object[detail.size()][3];
		for(int i = 0; i < detail.size(); i++){
				/*programData[i][0] = detail.get(i).getProgramId();
				programData[i][1]= detail.get(i).getProgramName();
				programData[i][2]= detail.get(i).getProgramDescription();*/
				//programData[i][3]=detail.get(i).getFormNumber();
			
			programData[i][0]=detail.get(i).getFormNumber();
			programData[i][1]=detail.get(i).getFormName();
		}
		
		
		RecordDef programRecordDef = new RecordDef(new FieldDef[] {
		
				/*new StringFieldDef("programId"),
				new StringFieldDef("programName"),
				new StringFieldDef("programDescription"),*/
				//new StringFieldDef("formNumber")
				new StringFieldDef("formNumber"),
				new StringFieldDef("formName")
				
		});
		MemoryProxy programProxy = new MemoryProxy(programData);
		ArrayReader programReader = new ArrayReader(programRecordDef);
		Store programStore = new Store(programProxy, programReader);
		final GridPanel programGrid = new GridPanel();
		programStore.load();
		programGrid.setStore(programStore);
		
		final CheckboxSelectionModel cbSelectionModelProgram=new CheckboxSelectionModel();
		
		BaseColumnConfig[] columnsProgram = new BaseColumnConfig[] {
				new CheckboxColumnConfig(cbSelectionModelProgram),
				new ColumnConfig("form Name","formName", 200, false, null,"formName")/*,
				new ColumnConfig(constants.gridProgramDescription(), "programDescription",200,true,null)	*/	
		};
		ColumnModel programColumnModel = new ColumnModel(columnsProgram);
		programGrid.setColumnModel(programColumnModel);
		programGrid.setFrame(true);
		programGrid.setStripeRows(true);
		programGrid.setAutoExpandColumn("formName");
		programGrid.setSelectionModel(cbSelectionModelProgram);
		programGrid.setWidth(400);
		programGrid.setHeight(200);
		//programGrid.setTitle(constants.searchByProgram());
		
		Toolbar toolBar=new Toolbar();
		toolBar.addFill();
		programGrid.setTopToolbar(toolBar);
	
		final ToolbarButton programSubmitButton = new ToolbarButton(constants.submit());
		toolBar.addButton(programSubmitButton);
		programSubmitButton.setDisabled(true);
	    
		cbSelectionModelProgram.addListener(new RowSelectionListenerAdapter() {
   		  	public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
					if(cbSelectionModelProgram.getSelections().length==1){
						programSubmitButton.setDisabled(false);
					}
					else{
						programSubmitButton.setDisabled(true);
					}
				}
				public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
					if(cbSelectionModelProgram.getSelections().length==1){
						programSubmitButton.setDisabled(false);
					}
					else{
						programSubmitButton.setDisabled(true);
					}
				}
   	   });
	   programSubmitButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e) {
				final Record[] recordsProgram = cbSelectionModelProgram.getSelections();
				final String formNumber=recordsProgram[0].getAsString("formNumber");
				final String formName=recordsProgram[0].getAsString("formName");
				final String universityId=userId.substring(1,5);
								
						connectService.getUniversitySessionDate(universityId,new AsyncCallback<List<ProgramSearchInfoGetter>>() {
		
							public void onFailure(Throwable caught) {
								MessageBox.alert(constants.dbError(),caught.getMessage());
							}
		
							public void onSuccess(List<ProgramSearchInfoGetter> details) {
								if(details.size()<1){
									MessageBox.alert(msgs.noSessionFound());
								}
								final String sessionStartDate=details.get(0).getSessionStartDate();
								final String sessionEndDate=details.get(0).getSessionEndDate();
								final String uniNick=details.get(0).getUniNickName();
								
											vPanel.clear();
											SummarySheet summarySheet=new SummarySheet(universityId,selectedEntity,
													entityCode, entityName,  formName, formNumber,  userId,
													sessionStartDate,  sessionEndDate,userEmailId,uniNick);
											try{
												summarySheet.onModuleLoad();
												vPanel.add(summarySheet.mainVPanel);
											}catch(Exception exx){
												System.out.println("Exception"+exx.getStackTrace());
											}
										
							}
						});
				
			}
	    });
		programPanel.add(programGrid);
		secondVerPanel.add(programPanel);
	} 
}
