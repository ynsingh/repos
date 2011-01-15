package in.ac.dei.edrp.client.ReportGeneration;


import java.util.List;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.RPCFiles.GreetingServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
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
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GenrateReport {
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	private final constants constant=GWT.create(constants.class);
	private final messages message=GWT.create(messages.class);

	/**
	 * This is the entry point method.
	 */
	
//	public void onModuleLoad(){
//		methodGenerateReport();
//	}
	
	final VerticalPanel vPanel=new VerticalPanel();
	public final VerticalPanel outerPanel=new VerticalPanel();

	public void methodGenerateReport(String buttonName) {
		vPanel.clear();
        outerPanel.clear();
        
		final FlexTable upperFlexTable = new FlexTable();
		final VerticalPanel vStudentPanel=new VerticalPanel();

		//update button computes marks and make candidate eligible or ineligible  with called
		//flag either '-' or 'n'
		final Button updateButton = new Button(constant.computeMarks());
		//report button generates call list according to cut_off_marks and make called flag 'y' or 'n'
		final Button reportButton = new Button(constant.internalCallList());
		//generated test number (status='eligible' and called='y')
		final Button testNumberButton = new Button(constant.testNumber());
		//Generated list includes test number
		final Button meritButton = new Button(constant.externalCallList());
		//Generates selected and waiting student and updates their status by selected/disqualify(if absent)/waiting 
		final Button finalMeritButton = new Button(constant.finalStudentList());
		//You can start it from compute marks.
		final Button resetButton = new Button(constant.reset());
		
		final Label entityTypeLabel = new Label(constant.entityType());
		final Label entityNameLabel = new Label(constant.entityName());
		final Label programNameLabel = new Label(constant.programme());
		final Label branchNameLabel = new Label(constant.branch());

		final ComboBox entityTypeCBox = new ComboBox();
		final ComboBox entityNameCBox = new ComboBox();
		final ComboBox programNameCBox = new ComboBox();
		final ComboBox branchNameCBox = new ComboBox();

		entityTypeCBox.setEmptyText(constant.emptyEntityType());
		entityNameCBox.setEmptyText(constant.emptyEntityName());
		programNameCBox.setEmptyText(constant.emptyProgramName());
		branchNameCBox.setEmptyText(constant.emptyBranchName());

		entityTypeCBox.setForceSelection(true);
		entityTypeCBox.setMinChars(1);
		entityTypeCBox.setFieldLabel("Entity Type");
		entityTypeCBox.setDisplayField("entity_description");
		entityTypeCBox.setValueField("entity_type");
		entityTypeCBox.setMode(ComboBox.LOCAL);
		entityTypeCBox.setTriggerAction(ComboBox.ALL);
		entityTypeCBox.setLoadingText("Loading...");
		entityTypeCBox.markInvalid(constant.marksInvalid());
		entityTypeCBox.setTypeAhead(true);
		entityTypeCBox.setSelectOnFocus(true);
		entityTypeCBox.setHideTrigger(false);
		

		entityNameCBox.setForceSelection(true);
		entityNameCBox.setMinChars(1);
		entityNameCBox.setFieldLabel("Entity Name");
		entityNameCBox.setDisplayField("entity_name");
		entityNameCBox.setValueField("entity_id");
		entityNameCBox.setMode(ComboBox.LOCAL);
		entityNameCBox.setTriggerAction(ComboBox.ALL);
		entityNameCBox.setLoadingText("Loading...");
		entityNameCBox.setTypeAhead(true);
		entityNameCBox.setSelectOnFocus(true);
		entityNameCBox.setHideTrigger(false);
		
		programNameCBox.setForceSelection(true);
		programNameCBox.setMinChars(1);
		programNameCBox.setFieldLabel("Program");
		programNameCBox.setDisplayField("program_name");
		programNameCBox.setValueField("program_id");
		programNameCBox.setMode(ComboBox.LOCAL);
		programNameCBox.setTriggerAction(ComboBox.ALL);
		programNameCBox.setLoadingText("Loading...");
		programNameCBox.markInvalid(constant.marksInvalid());
		programNameCBox.setTypeAhead(true);
		programNameCBox.setSelectOnFocus(true);
		programNameCBox.setHideTrigger(false);
		
		branchNameCBox.setForceSelection(true);
		branchNameCBox.setMinChars(1);
		branchNameCBox.setFieldLabel("Branch");
		branchNameCBox.setDisplayField("branch_name");
		branchNameCBox.setValueField("branch_code");
		branchNameCBox.setMode(ComboBox.LOCAL);
		branchNameCBox.setTriggerAction(ComboBox.ALL);
		branchNameCBox.setLoadingText("Loading...");
		branchNameCBox.markInvalid(constant.marksInvalid());
		branchNameCBox.setTypeAhead(true);
		branchNameCBox.setSelectOnFocus(true);
		branchNameCBox.setHideTrigger(false);

		
		Label heading = new Label("Report Generation");
		Label instiName = new Label("Dayalbagh Educational Institute");
		Label session = new Label("Session: 2010-11");
		
		
//		hPanel.add(updateButton);
//		hPanel.add(getButton);
//		hPanel.add(reportButton);
//		hPanel.add(testNumberButton);
//		hPanel.add(meritButton);
//		hPanel.add(finalMeritButton);
		
//		vPanel.add(hPanel);
		

		
		heading.setStyleName("heading");
		
        	upperFlexTable.setWidget(0, 0, entityTypeLabel);
	  		upperFlexTable.setWidget(0, 1, entityTypeCBox);
	  		upperFlexTable.setWidget(1, 0, entityNameLabel);
	  		upperFlexTable.setWidget(1, 1, entityNameCBox);
	  		upperFlexTable.setWidget(2, 0, programNameLabel);
	  		upperFlexTable.setWidget(2, 1, programNameCBox);
	  		upperFlexTable.setWidget(3, 0, branchNameLabel);
	  		upperFlexTable.setWidget(3, 1, branchNameCBox);
	  		
	  		if(buttonName.equalsIgnoreCase("internalcall")){
	  			heading.setText(constant.internalCallList());
	  			
	        	upperFlexTable.setWidget(6, 0, reportButton);
		  	}
	  		if(buttonName.equalsIgnoreCase("testnumber")){
	  			heading.setText(constant.testNumberHeading());
	        	upperFlexTable.setWidget(6, 0, testNumberButton);
		  	}
	  		if(buttonName.equalsIgnoreCase("externalcall")){
	  			heading.setText(constant.externalCallList());
	        	upperFlexTable.setWidget(6, 0, meritButton);
		  	}
	  		if(buttonName.equalsIgnoreCase("finalmerit")){
	  			heading.setText(constant.finalStudentList());
	        	upperFlexTable.setWidget(6, 0, finalMeritButton);
		  	}
	  		if(buttonName.equalsIgnoreCase("reset")){
	  			heading.setText(constant.reset());
	        	upperFlexTable.setWidget(6, 0, resetButton);
	  		}
	  		if(buttonName.equalsIgnoreCase("computemarks")){
	  			heading.setText(constant.computeMarksHeading());
	        	upperFlexTable.setWidget(6, 0, updateButton);
		  	}
     
		
		//Button okButton = new Button("OK");
		
		vPanel.setSpacing(20);
		vPanel.add(heading);
		vPanel.setSpacing(10);
		vPanel.add(instiName);
		vPanel.add(session);
		vPanel.add(upperFlexTable);
		vPanel.add(vStudentPanel);
		outerPanel.clear();
		outerPanel.setSize("100%", "100%");
		outerPanel.add(vPanel);
		outerPanel.setStyleName("background");

		

final String userid = "0001";
		

greetingService.methodEntityList(userid,
		new AsyncCallback<ReportInfoGetter[]>() {
			String[][] object2;
			public void onFailure(Throwable arg0) {
				MessageBox.alert(message.failure()+":", arg0.getMessage());
			}

			public void onSuccess(ReportInfoGetter[] arg0) {
				RecordDef recordDef = new RecordDef(
						new FieldDef[] { new StringFieldDef(
								"entity_type"),new StringFieldDef("entity_description")

						});
				
				object2 = new String[arg0.length][2];

				
				//String[][] data = object2;
				for (int i = 0; i < arg0.length; i++) {
					object2[i][0] = arg0[i].getEntity_type();
					object2[i][1] = arg0[i].getEntity_description();
					System.out.println(object2[i][0]+" and "+object2[i][1]);
				}
				
				for(int l=0;l<object2.length;l++){
					System.out.println(object2[l][0]+" and "+object2[l][1]);
				}
									
				MemoryProxy proxy = new MemoryProxy(object2);

				ArrayReader reader = new ArrayReader(recordDef);
				Store store = new Store(proxy, reader);
				// final Store store = new SimpleStore(new String[]{"entity_description","entity_type"}, object2);  
				        
				store.load();
				
				entityTypeCBox.setStore(store);
			}
		});

		
entityTypeCBox.addListener(new ComboBoxListenerAdapter() {

	public void onSelect(ComboBox comboBox, Record record, int index) {
		greetingService.methodEntityNameList(userid,
				comboBox.getValue(),
				new AsyncCallback<ReportInfoGetter[]>() {
					 String[][] object2;
					public void onFailure(Throwable arg0) {
						MessageBox.alert(message.failure()+":", arg0.getMessage());
					}

					public void onSuccess(ReportInfoGetter[] arg0) {
						RecordDef recordDef = new RecordDef(
								new FieldDef[] { new StringFieldDef(
										"entity_name"),new StringFieldDef("entity_id")

								});

						object2 = new String[arg0.length][2];

						//String[][] data = object2;

						for (int i = 0; i < arg0.length; i++) {
							object2[i][0] = arg0[i].getEntity_name();
							object2[i][1] = arg0[i].getEntity_id();
							System.out.println(object2[i][0]+" and "
									+ object2[i][1]);
						}

						MemoryProxy proxy = new MemoryProxy(object2);

						ArrayReader reader = new ArrayReader(recordDef);
						Store store = new Store(proxy, reader);
						store.load();
						
						entityNameCBox.clearValue();
						programNameCBox.clearValue();
						entityNameCBox.setStore(store);
					}
				});

	}
});

entityNameCBox.addListener(new ComboBoxListenerAdapter() {

	public void onSelect(ComboBox comboBox, final Record record, int index) {
		greetingService.methodProgramNameList(userid, comboBox.getValue(),
				new AsyncCallback<ReportInfoGetter[]>() {
					String[][] object2;
					String entity_id=record.getAsString("entity_id");
					public void onFailure(Throwable arg0) {
						MessageBox.alert(message.failure()+":", arg0.getMessage());
					}

					public void onSuccess(ReportInfoGetter[] arg0) {
						RecordDef recordDef = new RecordDef(
								new FieldDef[] { new StringFieldDef(
										"program_name"),new StringFieldDef("program_id")

								});

						object2 = new String[arg0.length][2];

						//String[][] data = object2;

						for (int i = 0; i < arg0.length; i++) {
							object2[i][0] = arg0[i].getProgram_name();
							object2[i][1] = arg0[i].getProgram_id();
							//object2[i][2] = arg0[i].getEntity_id();
							//System.out.println(object2[i][0]+" inside success "
								//	+ object2[i][1]);
						}

						MemoryProxy proxy = new MemoryProxy(object2);

						ArrayReader reader = new ArrayReader(recordDef);
						Store store = new Store(proxy, reader);
						store.load();
						programNameCBox.clearValue();
						programNameCBox.setStore(store);
					}
				});

	}
});

programNameCBox.addListener(new ComboBoxListenerAdapter() {

	public void onSelect(ComboBox comboBox, Record record, int index) {
		greetingService.methodBranchNameList(userid, comboBox.getValue(),entityNameCBox.getValue(),
				new AsyncCallback<ReportInfoGetter[]>() {
					String[][] object2;
					
					public void onFailure(Throwable arg0) {
						MessageBox.alert(message.failure()+":", arg0.getMessage());
					}

					public void onSuccess(ReportInfoGetter[] arg0) {
						RecordDef recordDef = new RecordDef(
								new FieldDef[] { new StringFieldDef(
										"branch_name"),new StringFieldDef("branch_code")

								});

						object2 = new String[arg0.length+1][2];

						String[][] data = object2;
						object2[0][0] = "All";
						object2[0][1] = "All";
						for (int i = 1; i < arg0.length+1; i++) {
							object2[i][0] = arg0[i-1].getBranch_name();
							object2[i][1] = arg0[i-1].getBranch_code();
							//System.out.println("inside success in branch"
									//+ object2[i][0]);
						}

						MemoryProxy proxy = new MemoryProxy(object2);

						ArrayReader reader = new ArrayReader(recordDef);
						Store store = new Store(proxy, reader);
						store.load();
						branchNameCBox.clearValue();
						branchNameCBox.setStore(store);
					}
				});

	}
});
		
		
		updateButton.addListener(new ButtonListenerAdapter() {

			@Override
			public void onClick(Button button, EventObject e) {
				
			
				vStudentPanel.clear();
			
			String entity_type=entityTypeCBox.getValue();
			String entity_id=entityNameCBox.getValue();
			String program_id=programNameCBox.getValue();
			String branch_id=branchNameCBox.getValue();
			System.out.println(entity_type+" | "+entity_id+" | "+program_id+" | "+branch_id+" | ");
			//String branch_name="All";
			if(entity_id==null||entity_type==null||program_id==null||branch_id==null){
				if(entity_id==null)
						MessageBox.alert(message.selectEntityType());
					if(entity_type==null)
						MessageBox.alert(message.selectEntityName());
					if(program_id==null)
						MessageBox.alert(message.selectProgramName());
					if(branch_id==null)
						MessageBox.alert(message.selectBranchName());
				//MessageBox.alert(constant.marksInvalid());
			}
			else{
			greetingService.updateMarks(userid,entity_type,entity_id,program_id,branch_id,
					new AsyncCallback<List<DataBean>>() {
			 
				public void onFailure(Throwable arg0) {
					MessageBox.alert(message.failure()+":", arg0.getMessage());
				}

				public void onSuccess(List<DataBean> list) {
					if(list.size()==0){
						MessageBox.alert(message.computeMarksFailure());	
					}
					else{
						MessageBox.alert(message.computeMarksSuccess());
						vStudentPanel.add(new StudentGridList(list).createStudentGridPanel());
												
					}
					
				}//onsuccess close
			});//greetservice update marks
			}//else ends here
		}//onclick ends
			
	});//button adaptor
		
		resetButton.addListener(new ButtonListenerAdapter() {

			@Override
			public void onClick(Button button, EventObject e) {
				
				String entity_type=entityTypeCBox.getValue();
				String entity_id=entityNameCBox.getValue();
				String program_id=programNameCBox.getValue();
				String branch_id=branchNameCBox.getValue();
				System.out.println(entity_type+" | "+entity_id+" | "+program_id+" | "+branch_id+" | ");
				//String branch_name="All";
				if(entity_id==null||entity_type==null||program_id==null||branch_id==null){
					
						if(entity_id==null)
							MessageBox.alert(message.selectEntityType());
						if(entity_type==null){
							MessageBox.alert(message.selectEntityName());
							try{
							entityNameCBox.markInvalid(constant.marksInvalid());
							}catch(Exception e1){
								System.out.println("Yes Exception :"+e1.getMessage());
							}
						}
						if(program_id==null)
							MessageBox.alert(message.selectProgramName());
						if(branch_id==null)
							MessageBox.alert(message.selectBranchName());
				}
				else{
			greetingService.getResetFlag(userid,entity_type,entity_id,program_id,branch_id,
					new AsyncCallback<String>() {
				public void onFailure(Throwable arg0) {
					MessageBox.alert(message.failure()+":", arg0.getMessage());
				}
				
				public void onSuccess(String arg) {
//					System.out.println("----------At client side-----------");
//					Iterator i=list.iterator();
//					while(i.hasNext()){
//						DataBean db=(DataBean)i.next();
//						String name=db.getRegistrationNumber();
//						System.out.print(name+" | ");
//						String[][] data=db.getCompData();
//						for(int i1=0;i1<data.length;i1++){
//							for(int j=0;j<3;j++){
//								System.out.print(data[i1][j]+" | ");
//							}
//						}
//						//double comp=db.getTotalMarks();
//						
//						//System.out.print(comp+" | ");
//						//System.out.print(db.getTestNumber()+" | ");
//						System.out.println(" | ");
//					}
					if(arg.length()>0){
						if(arg.equalsIgnoreCase("E")){
							MessageBox.alert(message.resetSecondSuccess());
						}
						if(arg.equalsIgnoreCase("R")){
							MessageBox.alert(message.resetFisrtSuccess());
						}
					}
					else{
						MessageBox.alert(message.resetFailure());
					}
				}//onsuccess close
			});//greetservice update marks
				}//else ends here
		}//onclick ends
			
	});//button adaptor
		
		//final double x=9.00;
		reportButton.addListener(new ButtonListenerAdapter() {

			@Override
			public void onClick(Button button, EventObject e) {
				String entity_type=entityTypeCBox.getValue();
				String entity_id=entityNameCBox.getValue();
				String program_id=programNameCBox.getValue();
				String branch_id=branchNameCBox.getValue();
				System.out.println(entity_type+" | "+entity_id+" | "+program_id+" | "+branch_id+" | ");
				//String branch_name="All";
				if(entity_id==null||entity_type==null||program_id==null||branch_id==null|| branch_id.equalsIgnoreCase("All")){

					if(entity_id==null)
						MessageBox.alert(message.selectEntityType());
					if(entity_type==null)
						MessageBox.alert(message.selectEntityName());
					if(program_id==null)
						MessageBox.alert(message.selectProgramName());
					if(branch_id==null||branch_id.equalsIgnoreCase("All"))
						MessageBox.alert(message.allselectBranch());
				}
			

			
				else{
				greetingService.validateGenerate(userid,entity_type,entity_id,program_id,branch_id,new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						MessageBox.alert(message.failure()+":", arg0.getMessage());
					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						if(result.equalsIgnoreCase("C") || result.equalsIgnoreCase("I")){
							
							//MessageBox.alert("Updated Success"+result);
							Window.Location.replace(GWT.getModuleBaseURL()+"export?param="+userid+"&param1="+entityTypeCBox.getValue()+"&param2="+entityNameCBox.getValue()+"&param3="+programNameCBox.getValue()+"&param4="+branchNameCBox.getValue());
								}
						else{
							MessageBox.alert(message.internalCallListFailure());
						}
						
					}
					
				});
				
			}
			

		}//onclick ends
			
	});//button adaptor
		
		
		testNumberButton.addListener(new ButtonListenerAdapter() {

			@Override
			public void onClick(Button button, EventObject e) {
				
				String entity_type=entityTypeCBox.getValue();
				String entity_id=entityNameCBox.getValue();
				String program_id=programNameCBox.getValue();
				String branch_id=branchNameCBox.getValue();
				System.out.println(entity_type+" | "+entity_id+" | "+program_id+" | "+branch_id+" | ");
				//String branch_name="All";
				if(entity_id==null||entity_type==null||program_id==null||branch_id==null|| branch_id.equalsIgnoreCase("All")){
					if(entity_id==null)
						MessageBox.alert(message.selectEntityType());
					if(entity_type==null)
						MessageBox.alert(message.selectEntityName());
					if(program_id==null)
						MessageBox.alert(message.selectProgramName());
					if(branch_id==null||branch_id.equalsIgnoreCase("All"))
						MessageBox.alert(message.allselectBranch());
				}
				
			greetingService.generateTestNumber(userid,entity_type,entity_id,program_id,branch_id,
					new AsyncCallback<Boolean>() {
				public void onFailure(Throwable arg0) {
					MessageBox.alert(message.failure()+":", arg0.getMessage());
				}

				public void onSuccess(Boolean arg) {
					if(arg==true){
						MessageBox.alert(message.generateTestNumberFailure());	
					}
					else{
					MessageBox.alert(message.generateTestNumberSuccess());
					//Window.Location.replace(GWT.getModuleBaseURL()+"exportcall?param="+userid+"&param1="+entityTypeCBox.getValue()+"&param2="+entityNameCBox.getValue()+"&param3="+programNameCBox.getValue()+"&param4="+branchNameCBox.getValue());
					}//else ends for flag
				}//onsuccess close
			});//greetservice update marks
		}//onclick ends
			
	});//test button adaptor
		
		
		meritButton.addListener(new ButtonListenerAdapter() {

			@Override
			public void onClick(Button button, EventObject e) {
				System.out.println("before servlet "+entityTypeCBox.getValue());
				String entity_type=entityTypeCBox.getValue();
				String entity_id=entityNameCBox.getValue();
				String program_id=programNameCBox.getValue();
				String branch_id=branchNameCBox.getValue();
				System.out.println(entity_type+" | "+entity_id+" | "+program_id+" | "+branch_id+" | ");
				//String branch_name="All";
				if(entity_id==null||entity_type==null||program_id==null||branch_id==null|| branch_id.equalsIgnoreCase("All")){
					if(entity_id==null)
						MessageBox.alert(message.selectEntityType());
					if(entity_type==null)
						MessageBox.alert(message.selectEntityName());
					if(program_id==null)
						MessageBox.alert(message.selectProgramName());
					if(branch_id==null||branch_id.equalsIgnoreCase("All"))
						MessageBox.alert(message.allselectBranch());
				}
				else{
				greetingService.validateGenerate(userid,entity_type,entity_id,program_id,branch_id,new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						MessageBox.alert(message.failure()+":", arg0.getMessage());
					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						if(result.equalsIgnoreCase("T")||result.equalsIgnoreCase("E")){
							//System.out.println("result is "+result);
							Window.Location.replace(GWT.getModuleBaseURL()+"exportcall?param="+userid+"&param1="+entityTypeCBox.getValue()+"&param2="+entityNameCBox.getValue()+"&param3="+programNameCBox.getValue()+"&param4="+branchNameCBox.getValue());
						}
						else{
							MessageBox.alert(message.externalCallListFailure());
						}
						
					}
					
				});
				}
				
				
			}//click ends here

				
		
	});//button adaptor1
		
		finalMeritButton.addListener(new ButtonListenerAdapter() {

			@Override
			public void onClick(Button button, EventObject e) {
				//MessageBox.alert("Right now this button is doing nothing");
				String entity_type=entityTypeCBox.getValue();
				String entity_id=entityNameCBox.getValue();
				String program_id=programNameCBox.getValue();
				String branch_id=branchNameCBox.getValue();
				System.out.println(entity_type+" | "+entity_id+" | "+program_id+" | "+branch_id+" | ");
				//String branch_name="All";
				if(entity_id==null||entity_type==null||program_id==null||branch_id==null|| branch_id.equalsIgnoreCase("All")){
					if(entity_id==null)
						MessageBox.alert(message.selectEntityType());
					if(entity_type==null)
						MessageBox.alert(message.selectEntityName());
					if(program_id==null)
						MessageBox.alert(message.selectProgramName());
					if(branch_id==null||branch_id.equalsIgnoreCase("All"))
						MessageBox.alert(message.allselectBranch());
				}
				else{
				greetingService.validateGenerate(userid,entity_type,entity_id,program_id,branch_id,new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						MessageBox.alert(message.failure()+":", arg0.getMessage());
					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						if(result.equalsIgnoreCase("E")||result.equalsIgnoreCase("F")){
							//System.out.println("result is "+result);
							Window.Location.replace(GWT.getModuleBaseURL()+"exportfinal?param="+userid+"&param1="+entityTypeCBox.getValue()+"&param2="+entityNameCBox.getValue()+"&param3="+programNameCBox.getValue()+"&param4="+branchNameCBox.getValue());
						}
						else{
							MessageBox.alert(message.finalCallListFailure());
						}
						
					}
					
				});
				}
				
			}	
		});
		
		
		
	}//onmodule load


	
}
