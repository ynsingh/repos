/*
 * @(#) ProgramDocumentSetUp.java
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

import in.ac.dei.edrp.client.CM_progMasterInfoGetter;
import in.ac.dei.edrp.client.ProgramDocumentInfoGetter;
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
import com.google.gwt.user.client.ui.HorizontalPanel;
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
import com.gwtext.client.widgets.grid.event.RowSelectionListener;
/**
 * This class is for Program Document Setup
 * 
 * @version 1.0 10 February 2012
 * @author UPASANA KULSHRESTHA
 */
public class ProgramDocumentSetUp{

	CMconnectRAsync connectService = (CMconnectRAsync) GWT.create(CMconnectR.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	private String userId;
	String confirm;
	String error;
	
	public VerticalPanel vPanel=new VerticalPanel();
	
	/**This constructor set the userId
	 * 
	 * @param userID
	 */
	public ProgramDocumentSetUp(String userID) {
		this.userId = userID;
	}
	
	/**
	 * This function add program document panel in the vertical panel
	 */
	public void programDocument() {  
			
			vPanel.clear();
		  	Panel panel = new Panel();
			panel.setTitle(constants.heading_programDocument());
	        panel.setWidth(500);
	        panel.setHeight(520);
	        
	        Label ProgramLabel =new Label(constants.selectProgram());
		  	final ListBox selectProgram=new ListBox();
		  	
	        HorizontalPanel hPanel=new HorizontalPanel();
	        hPanel.add(ProgramLabel);
	        hPanel.add(selectProgram);
	        hPanel.setSpacing(5);
	        
	        panel.add(hPanel);
	        
	        final VerticalPanel vp=new VerticalPanel();
	    	final VerticalPanel vp1=new VerticalPanel();
	    	
	    	error= msgs.error();
			confirm=msgs.confirm();
	        
			/**
			 * This method get Program list from database 
			 * @param userId
			 * @return Program list
			 */
	    	connectService.getUniversityProgramDetails(userId, new AsyncCallback<List<CM_progMasterInfoGetter>>() 
		    		 {
	        			public void onFailure(Throwable caught) {
	    					MessageBox.alert(constants.dbError(),caught.getMessage());
	                    }
						public void onSuccess(List<CM_progMasterInfoGetter> result) {
							selectProgram.clear();
	                        selectProgram.addItem(constants.select());
	                        if(result.size()<1){
								MessageBox.alert(error, msgs.noProgram());
							}
	                        for (int i = 0; i < result.size(); i++) {
	                            selectProgram.addItem(result.get(i).getProgram_name(), result.get(i).getProgram_id());
	                        }
	                        selectProgram.setItemSelected(0,true);
						}
		    });
	    	
	        selectProgram.addChangeHandler(new ChangeHandler() {
	             public void onChange(ChangeEvent arg0) {
	                 if (selectProgram.getSelectedIndex()==0) {
	                	 vp.clear();
	                	 vp1.clear();
	                 } 
	                 else {
	                	 refreshGrid(vp,vp1,selectProgram.getValue(selectProgram.getSelectedIndex()));
	                 }
	             }   
	         }); 
	        
	        panel.add(vp);
	        panel.add(vp1);
	        vPanel.add(panel);
	}

	/**This function add program document grids
	 * 
	 * @param vp 
	 * @param vp1
	 * 			,vertical panel to add second grid
	 * @param pId
	 * 			, contain program Id
	 */
	protected void refreshGrid(final VerticalPanel vp,final VerticalPanel vp1, final String pId) {
		
   	 	vp.clear();
   	 	vp1.clear();
   	 
   	 	/**
		 * This method get Document list from database 
		 * @param userId
		 * @return document list
		 */
   	 	connectService.getDocumentList(userId,pId, new AsyncCallback<List<ProgramDocumentInfoGetter>>() 
	    		 {
   		 			public void onFailure(Throwable caught) 
   		 			{
   		 				MessageBox.alert(constants.dbError(),caught.getMessage());
   		 			}
			
					public void onSuccess(List<ProgramDocumentInfoGetter> result) {
		
                       if(result.size()<1){
		            	   MessageBox.alert(error, msgs.error_record());
                       }

                       Object data[][]=new Object[result.size()][2];
                       for (int i = 0; i < result.size(); i++) 
                       {
                           data[i][0]=  result.get(i).getDocDesc();
                           data[i][1]= result.get(i).getDocId();
                       }
                       
                       final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
                       RecordDef recordDef = new RecordDef(  
	         	                new FieldDef[]{  
	         	                        new StringFieldDef("docDescription"),  
	         	                        new StringFieldDef("docId")  
	         	                }  
	         	        );  
	         	  
	         	       GridPanel grid = new GridPanel();
	         	       MemoryProxy proxy = new MemoryProxy(data);  
	         	       ArrayReader reader = new ArrayReader(recordDef);  
	         	       Store store = new Store(proxy, reader);  
	         	       store.load();  
	         	       grid.setStore(store);  
	         	  
	         	       BaseColumnConfig[] columns = new BaseColumnConfig[]{  
	         	                new CheckboxColumnConfig(cbSelectionModel),  
	         	                new ColumnConfig(constants.gridDocumentDescription(), "docDescription", 160, true, null, "docDescription")  
	         	       };  
	         	  
	         	       ColumnModel columnModel = new ColumnModel(columns);  
	         	       grid.setColumnModel(columnModel);  
	         	       grid.setStripeRows(true);  
	         	       grid.setAutoExpandColumn("docDescription");  
	         	  
	         	       grid.setSelectionModel(cbSelectionModel);  
	         	       grid.setWidth(400);  
	         	       grid.setHeight(200);  
	         	       grid.setFrame(true);  
	         	       grid.setTitle(constants.grid_listOfDocument());  
	         	       
	         	       Toolbar toolbar = new Toolbar();
	         	       toolbar.addFill();
	         	       grid.setTopToolbar(toolbar);
					
	         	       final ToolbarButton submitButton = new ToolbarButton(constants.addButton());
	         	       toolbar.addButton(submitButton);
	         	        
	         	       submitButton.setDisabled(true);
	         	       cbSelectionModel.addListener(new RowSelectionListener() {
							
							public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
								if(cbSelectionModel.hasSelection()){
									submitButton.setDisabled(false);
								}
								else{
									submitButton.setDisabled(true);
								}
							}
							
							public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
								if(cbSelectionModel.hasSelection()){
									submitButton.setDisabled(false);
								}
								else{
									submitButton.setDisabled(true);
								}
							}

							public boolean doBeforeRowSelect(
									RowSelectionModel sm, int rowIndex,
									boolean keepExisting, Record record) {
								return true;
							}

							public void onSelectionChange(RowSelectionModel sm) {
							}
						});
						
	         	        submitButton.addListener(new ButtonListenerAdapter() {
							public void onClick(Button button, EventObject e) {
								final Record[] records = cbSelectionModel.getSelections();
								if (records.length < 1) {
									MessageBox.alert(error, msgs.errorNoRecordSelected());
								}
								else 
								{
									MessageBox.confirm(confirm,	msgs.alert_confirmentries(),new MessageBox.ConfirmCallback() {
												public void execute(String btnID) {
													if (btnID.matches(constants.yesButton())) 
													{
														
														List<String> documentList=new ArrayList<String>();
														for(int j=0;j<records.length;j++){
															documentList.add(records[j].getAsString("docId"));
														}
														
														/**
														 * This method insert Document list into database for selected program
														 * @param documentList
														 * @param userId 
														 * @param userId
														 * @return document list
														 */
														connectService.insertProgramDocument(documentList,pId,userId,new AsyncCallback<Boolean>() 
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
																						refreshGrid(vp,vp1,pId);
																						cbSelectionModel.clearSelections();
																					}
																				});
																			}
																		}
																	}
														);
													}
												}
										});
									}
								}
						});
	         	        vp.add(grid);
	         	        vp.setSpacing(10);
					}
		});
   	 
   	 	/**
		 * This method get the added Document list from database for selected program 
		 * @param userId
		 * @param pId, 
		 * 				programId	
		 * @return document list
		 */
        connectService.getAddedDocument(userId,pId,new AsyncCallback<List<ProgramDocumentInfoGetter>>(){
			public void onFailure(Throwable caught) {
				MessageBox.alert(constants.dbError(),caught.getMessage());
			}
			public void onSuccess(List<ProgramDocumentInfoGetter> values) {
				
				if(!(values.size()<1)){
					vp1.clear();
					Object data1[][]=new Object[values.size()][2];
					for(int i = 0; i < values.size(); i++) {
               			data1[i][0]=  values.get(i).getDocDesc();
						data1[i][1]= values.get(i).getDocId();
                	}
					final CheckboxSelectionModel cbSelectionModel1 = new CheckboxSelectionModel();
					RecordDef recordDef1 = new RecordDef(  
    	                new FieldDef[]{  
    	                        new StringFieldDef("docdescription"),  
    	                        new StringFieldDef("docId")  
    	                }  
					);  
    	  
					GridPanel grid1 = new GridPanel();
    	            MemoryProxy proxy1 = new MemoryProxy(data1);  
    	            ArrayReader reader1 = new ArrayReader(recordDef1);  
    	            Store store1 = new Store(proxy1, reader1);  
    	            store1.load();  
    	            grid1.setStore(store1);  
    	  
    	            BaseColumnConfig[] columns1 = new BaseColumnConfig[]{  
    	                new CheckboxColumnConfig(cbSelectionModel1),  
    	                new ColumnConfig(constants.gridDocumentDescription(), "docdescription", 160, true, null, "docdescription")  
    	                
    	            };  
    	  
    	            ColumnModel columnModel1 = new ColumnModel(columns1);  
    	            grid1.setColumnModel(columnModel1);  
    	            grid1.setStripeRows(true);  
    	            grid1.setAutoExpandColumn("docdescription");  
    	            grid1.setSelectionModel(cbSelectionModel1);  
    	            grid1.setWidth(400);  
    	            grid1.setHeight(200);  
    	            grid1.setFrame(true);  
    	            grid1.setTitle(constants.grid_addedDocumentInProgram());  
    	            Toolbar toolbar = new Toolbar();
    	            toolbar.addFill();
    	            grid1.setTopToolbar(toolbar);
    	            final ToolbarButton delbutton = new ToolbarButton(constants.delete());
    	            toolbar.addButton(delbutton);
     	        
    	            delbutton.setDisabled(true);
    	            cbSelectionModel1.addListener(new RowSelectionListener() {
    	            	
    	            	public void onRowSelect(RowSelectionModel sm, int rowIndex, Record record) {
								if(cbSelectionModel1.hasSelection()){
									delbutton.setDisabled(false);
							
								}
								else{
									delbutton.setDisabled(true);
								}
						
    	            	}
					
						public void onRowDeselect(RowSelectionModel sm, int rowIndex, Record record) {
								if(cbSelectionModel1.hasSelection()){
									delbutton.setDisabled(false);
								}
								else{
									delbutton.setDisabled(true);
								}
						
						}

						public boolean doBeforeRowSelect(
							RowSelectionModel sm, int rowIndex,
							boolean keepExisting, Record record) {
							return true;
						}

						public void onSelectionChange(RowSelectionModel sm) {
				
						}
					
				});
     	        
     	        delbutton.addListener(new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						final Record[] records = cbSelectionModel1.getSelections();
		
						if (records.length < 1) {
							MessageBox.alert(error, msgs.errorNoRecordSelected());
						}
						else {
							MessageBox.confirm(confirm,	msgs.alert_confirmentries(),new MessageBox.ConfirmCallback() {
										public void execute(String btnID) {
											if (btnID.matches(constants.yesButton())) 
											{
												
												List<String> documents=new ArrayList<String>();
												for(int j=0;j<records.length;j++){
													documents.add(records[j].getAsString("docId"));
												}
												
												/**
												 * This method delete Document from database for selected program
												 * @param documents,
												 * 					list of doc id
												 * @param pId,
												 * 				programId 
												 * @param userId
												 * 
												 */
												connectService.deleteProgramDocument(documents,pId,userId,new AsyncCallback<Integer>() 
																{
																public void onFailure(Throwable caught) 
																{
																	MessageBox.alert(constants.dbError(),caught.getMessage());
																}
																public void onSuccess(Integer arg0) 
																{
																	if(arg0>0){
																		MessageBox.show(new MessageBoxConfig() {
																			{
																			setMsg(msgs.alert_ondeletesuccess());
																			setButtons(MessageBox.OK);
																			cbSelectionModel1.clearSelections();
																			refreshGrid(vp,vp1,pId);
																			}
																		});
																	}
																}
																	
												});
											}
										}
									});
								}
							}
				});
    	        vp1.add(grid1);
    	        vp1.setSpacing(10);
			}
		}
       });
	}  
}
