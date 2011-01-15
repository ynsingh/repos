package in.ac.dei.edrp.client.addmarks;

import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.RPCFiles.GreetingServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;


//public class UploadMarksUsingExcel implements EntryPoint{
public class UploadMarksUsingExcel{
	private final messages message=GWT.create(messages.class);
	private final constants constant=GWT.create(constants.class);
	
	public VerticalPanel centerPanel = new VerticalPanel();
	public VerticalPanel vPanel=new VerticalPanel();
	public Hyperlink link;
	public FileUpload fileUpload;
	
	public final String userId="0001";
	//objects of other classes
	AddMarksExcelServiceAsync addMarksService = (AddMarksExcelServiceAsync)GWT.create(AddMarksExcelService.class );
	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);
	/*
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	public UploadMarksUsingExcel(){
		final CommonScreenForReporting commonScreen=new CommonScreenForReporting();
		
		final Button uploadButton = new Button(constant.uploadButton());
		
		VerticalPanel vPanel1=new VerticalPanel();
		
		FlexTable mainflexTable=new FlexTable();
		
		link = new Hyperlink(constant.downloadLink(),null);
	
		fileUpload = new FileUpload();
		
		Label linkname=new Label(constant.addMarksHeading());
//		Label instituteName=new Label(constant.footer());
//		Label sessionName=new Label("2010-11");
		linkname.setStyleName("heading");
//		instituteName.setStyleName("heading");
//		sessionName.setStyleName("heading");
		FlexTable flexLabel=new FlexTable();
		flexLabel.setWidget(0, 1, linkname);
//		flexLabel.setWidget(1, 1, instituteName);
//		flexLabel.setWidget(2, 1, sessionName);
		vPanel1.add(flexLabel);
//		vPanel1.add(linkname);
//		vPanel1.add(instituteName);
//		vPanel1.add(sessionName);
		
		
		mainflexTable.setWidget(0, 1, vPanel1);
		mainflexTable.setWidget(1, 1,new Label(constant.addMarksInstruction1()));
		
		mainflexTable.setWidget(2, 1,new Label(constant.addMarksInstruction2()));
		
		mainflexTable.setWidget(3, 1,link);
		
		
		VerticalPanel commonPanel=commonScreen.methodGenerateReport("upload");
		
		link.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				String entityType=commonScreen.entityTypeCBox.getValue();
				String entityId=commonScreen.entityNameCBox.getValue();
				String programId=commonScreen.programNameCBox.getValue();
				String branchCode=commonScreen.branchNameCBox.getValue();
			//System.out.println(entityType+" | "+entityId+" | "+programId+" | "+branchCode+" | ");
			//String branch_name="All";
			if(entityId==null||entityType==null||programId==null||branchCode==null){
				if(entityId==null)
						MessageBox.alert(message.selectEntityType());
					if(entityType==null)
						MessageBox.alert(message.selectEntityName());
					if(programId==null)
						MessageBox.alert(message.selectProgramName());
					if(branchCode==null)
						MessageBox.alert(message.selectBranchName());
				//MessageBox.alert(constant.marksInvalid());
			}
			else{
				
				Window.Location.replace(GWT.getModuleBaseURL()+"addmarks?param="+userId+"&param1="+entityType+"&param2="+entityId+"&param3="+programId+"&param4="+branchCode);
			}
			}
		});
		
		uploadButton.addListener(new ButtonListenerAdapter() {

			@Override
			public void onClick(Button button, EventObject e) {
				
			
				final String entityType=commonScreen.entityTypeCBox.getValue();
				final String entityId=commonScreen.entityNameCBox.getValue();
				final String programId=commonScreen.programNameCBox.getValue();
				final String branchCode=commonScreen.branchNameCBox.getValue();
				final String fileName=fileUpload.getFilename();
			//System.out.println(entityType+" | "+entityId+" | "+programId+" | "+branchCode+" | ");
			//String branch_name="All";
			if(entityId==null||entityType==null||programId==null||branchCode==null||fileName.equals("")||branchCode.equalsIgnoreCase("All")){
				if(entityId==null)
						MessageBox.alert(message.selectEntityType());
					if(entityType==null)
						MessageBox.alert(message.selectEntityName());
					if(programId==null)
						MessageBox.alert(message.selectProgramName());
					if(branchCode==null)
						MessageBox.alert(message.selectBranchName());
					if(fileName.equals(""))
						MessageBox.alert(message.selectFileName());
					if(branchCode.equalsIgnoreCase("All"))
						MessageBox.alert(message.allselectBranch());
				//MessageBox.alert(constant.marksInvalid());
			}
			else{
				
				greetingService.validateGenerate(userId,entityType,entityId,programId,branchCode,new AsyncCallback<String>(){

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						MessageBox.alert(message.failure()+":", arg0.getMessage());
					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						if(result.equalsIgnoreCase("E") || result.equalsIgnoreCase("F")){
							//String fileName=fileUpload.getFilename();
							if(fileUpload.getFilename().substring(fileName.indexOf("."),fileName.length()).equalsIgnoreCase(".xls")){
								//MessageBox.alert(fileUpload.getFilename());
								//System.out.println("FileName is "+fileUpload.getFilename());
								addMarksService.uploadFile(fileUpload.getFilename(),userId,entityType,entityId,programId,branchCode,new AsyncCallback<List<Integer>>() {
							 
								public void onFailure(Throwable arg0) {
									MessageBox.alert(message.failure()+":", arg0.getMessage());
								}

								public void onSuccess(List<Integer> list) {
									int value1=0;
									int value2=0;
										if(list.size()>0){
										Iterator<Integer> itr=list.iterator();
										while(itr.hasNext()){
											value1=itr.next();
											value2=itr.next();
										}
										MessageBox.alert(message.successfullyAddMarks(String.valueOf(value1),String.valueOf(value2),String.valueOf((value1-value2))));
										
										}
										else{
											MessageBox.alert(message.errorInAddMarks1());
										}
								}

								
									
								
							});//greetservice update marks
								}//if ends here
								else{
									MessageBox.alert(message.errorInAddMarks2());	
								}
								}
						else{
							MessageBox.alert(message.finalListFailure());
						}
						
					}
					
				});
				
			}//else ends here
		}//onclick ends
			
		});//button adaptor
		
		mainflexTable.setWidget(5, 1,commonPanel);
		FlexTable flexTable=new FlexTable();
		
		flexTable.setWidget(0, 1, new Label(constant.browseFile()));
		flexTable.setWidget(0, 2, fileUpload);
		
		mainflexTable.setWidget(6, 1,flexTable);
		
		mainflexTable.setWidget(8, 1,uploadButton);
		vPanel.add(mainflexTable);
		
		vPanel.setStyleName("background");
	}//end constructor

	
	public VerticalPanel getPanel() {
		// TODO Auto-generated method stub
				
		return vPanel;
	}

}
