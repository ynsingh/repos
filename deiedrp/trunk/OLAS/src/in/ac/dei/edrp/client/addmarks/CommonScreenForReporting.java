package in.ac.dei.edrp.client.addmarks;

import java.util.List;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.ReportInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.GreetingService;
import in.ac.dei.edrp.client.RPCFiles.GreetingServiceAsync;
import in.ac.dei.edrp.client.ReportGeneration.StudentGridList;
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

public class CommonScreenForReporting {

	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);

private final constants constant=GWT.create(constants.class);
private final messages message=GWT.create(messages.class);

/**
* This is the entry point method.
*/

//public void onModuleLoad(){
//methodGenerateReport();
//}

final VerticalPanel vPanel=new VerticalPanel();
public final VerticalPanel outerPanel=new VerticalPanel();

final ComboBox entityTypeCBox = new ComboBox();
final ComboBox entityNameCBox = new ComboBox();
final ComboBox programNameCBox = new ComboBox();
final ComboBox branchNameCBox = new ComboBox();

public VerticalPanel methodGenerateReport(String buttonName) {
vPanel.clear();
outerPanel.clear();

final FlexTable upperFlexTable = new FlexTable();
final VerticalPanel vStudentPanel=new VerticalPanel();




final Label entityTypeLabel = new Label(constant.entityType());
final Label entityNameLabel = new Label(constant.entityName());
final Label programNameLabel = new Label(constant.programme());
final Label branchNameLabel = new Label(constant.branch());



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





//hPanel.add(updateButton);
//hPanel.add(getButton);
//hPanel.add(reportButton);
//hPanel.add(testNumberButton);
//hPanel.add(meritButton);
//hPanel.add(finalMeritButton);

//vPanel.add(hPanel);





	upperFlexTable.setWidget(0, 0, entityTypeLabel);
		upperFlexTable.setWidget(0, 1, entityTypeCBox);
		upperFlexTable.setWidget(1, 0, entityNameLabel);
		upperFlexTable.setWidget(1, 1, entityNameCBox);
		upperFlexTable.setWidget(2, 0, programNameLabel);
		upperFlexTable.setWidget(2, 1, programNameCBox);
		upperFlexTable.setWidget(3, 0, branchNameLabel);
		upperFlexTable.setWidget(3, 1, branchNameCBox);
		
	

//Button okButton = new Button("OK");

vPanel.setSpacing(20);
vPanel.setSpacing(10);
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




return outerPanel;



}//onmodule load



}

