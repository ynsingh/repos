/*
 * @(#) FormSearch.java
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

package in.ac.dei.edrp.client.InternalSummarySheet;

import in.ac.dei.edrp.client.ProgramSearchInfoGetter;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.summarysheet.SummarySheet;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
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
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;

/**
 * This class is to Search University forms
 * 
 * @version 1.0 28 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class FormSearch {
	FormSearchServiceAsync connectService=GWT.create(FormSearchService.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	
	public VerticalPanel vPanel=new VerticalPanel();
	String errorMsg;
    String confirm;
	private String userId;
	
	/**This constructor set the user Id 
	 *@param uId
	 */
	public FormSearch(String uId) {
		this.userId = uId;
	}
	
	/**This function add the first panel in main vertical panel
	 * 
	 */
	public void searchFormSetup() {

		errorMsg=msgs.error();
		confirm=msgs.confirm();
		vPanel.clear();
		final Panel mainPanel=new Panel();
		
		mainPanel.setSize(450, 350);
		mainPanel.setPaddings(10);
		final VerticalPanel firstPanel=new VerticalPanel();
		final VerticalPanel secondPanel=new VerticalPanel();
		
		//widgets for first panel
		mainPanel.setTitle(constants.internalSummarySheet());
		
		final ListBox entityListBox=new ListBox();
		FlexTable table=new FlexTable();
		table.setWidget(0, 0, new Label(constants.selectEntity()));
		table.setWidget(0,1,entityListBox);
		
		firstPanel.add(table);
		/**
		 * This method get the list of entities of the university
		 * 
		 * @param userId
		 * @return List of type FormSearchInfoGetter
		 */
		connectService.getUnivEntity(userId,new AsyncCallback<List<FormSearchInfoGetter>>(){
			public void onFailure(Throwable caught) {
				MessageBox.alert(constants.dbError(),caught.getMessage());
			}
			public void onSuccess(List<FormSearchInfoGetter> entityList) {
				entityListBox.addItem(constants.select());
				entityListBox.setItemSelected(0,true);
				if(entityList.size()<1){
					MessageBox.alert(errorMsg, msgs.noProgram());
				}
                for (int i = 0; i < entityList.size(); i++) {
                    entityListBox.addItem(entityList.get(i).getEntityName(),entityList.get(i).getEntityId());
                }
				               
            }
		});
		entityListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent arg0) {
				if(entityListBox.getSelectedIndex()!=0){
					secondPanel.clear();
					final String entityValue=entityListBox.getValue(entityListBox.getSelectedIndex());
					final String entityName=entityListBox.getItemText(entityListBox.getSelectedIndex());
					addEntityGrid(entityValue,entityName,secondPanel);	
				}
				else secondPanel.clear();				
			}
		});
		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		vPanel.add(mainPanel);
	}
	/**
	 * To add grid in second Panel
	 * @param entityValue
	 * @param entityName
	 * @param secondPanel
	 */
	protected void addEntityGrid(final String entityValue, final String entityName,  final VerticalPanel secondPanel) {
		/**
		 * This method get the list of programs 
		 * 
		 * @param userId
		 * @return List of type FormSearchInfoGetter
		 */
		connectService.getEntityPrograms(userId,entityValue,new AsyncCallback<List<FormSearchInfoGetter>>(){
			public void onFailure(Throwable caught) {
				MessageBox.alert(constants.dbError(),caught.getMessage());
			}
			public void onSuccess(List<FormSearchInfoGetter> detail) {
				Object[][] programData = new Object[detail.size()][5];
				for(int i = 0; i < detail.size(); i++){
					programData[i][0]=detail.get(i).getFormNumber();
					programData[i][1]=detail.get(i).getFormName();
					programData[i][2]=detail.get(i).getOfferedBy();
					programData[i][3]=detail.get(i).getEntityOfferedBy();
					programData[i][4]=detail.get(i).getEntityCode();
				}
				RecordDef programRecordDef = new RecordDef(new FieldDef[] {
						new StringFieldDef("formNumber"),
						new StringFieldDef("formName"),
						new StringFieldDef("offeredBy"),
						new StringFieldDef("entityOfferedBy"),
						new StringFieldDef("entityCode")
				});
				GridPanel programGrid = new GridPanel();
				MemoryProxy programProxy = new MemoryProxy(programData);
				ArrayReader programReader = new ArrayReader(programRecordDef);
				Store programStore = new Store(programProxy, programReader);
				programStore.load();
				programGrid.setStore(programStore);
				final CheckboxSelectionModel cbSelectionModelProgram=new CheckboxSelectionModel();	
				BaseColumnConfig[] columnsProgram = new BaseColumnConfig[] {
						new CheckboxColumnConfig(cbSelectionModelProgram),
						new ColumnConfig("form Name","formName", 200, false, null,"formName"),
						new ColumnConfig("Offered By", "entityOfferedBy",200,true,null)		
				};
				ColumnModel programColumnModel = new ColumnModel(columnsProgram);
				programGrid.setColumnModel(programColumnModel);
				programGrid.setFrame(true);
				programGrid.setStripeRows(true);
				programGrid.setAutoExpandColumn("formName");
				programGrid.setSelectionModel(cbSelectionModelProgram);
				programGrid.setWidth(400);
				programGrid.setHeight(200);
				
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
						final String entityCode=recordsProgram[0].getAsString("entityCode");
						final String universityId=userId.substring(1,5);
						/**
						 * This method get the session details of the university
						 * 
						 * @param universityId
						 * @return List of type FormSearchInfoGetter
						 */
						connectService.getUniversitySessionDate(universityId,new AsyncCallback<List<FormSearchInfoGetter>>() {
							public void onFailure(Throwable caught) {
								MessageBox.alert(constants.dbError(),caught.getMessage());
							}
							public void onSuccess(List<FormSearchInfoGetter> details) {
								if(details.size()<1){
									MessageBox.alert(msgs.noSessionFound());
								}
								final String sessionStartDate=details.get(0).getSessionStartDate();
								final String sessionEndDate=details.get(0).getSessionEndDate();
								final String uniNick=details.get(0).getUniNickName();
								vPanel.clear();
								InternalSummarySheet summarySheet=new InternalSummarySheet(universityId,entityValue,
										entityCode, entityName,  formNumber,  userId,
										sessionStartDate,  sessionEndDate, uniNick);
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
				
			  	secondPanel.add(programGrid);
			}
		});
	}
}
