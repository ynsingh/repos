package in.ac.dei.edrp.client.addmarks;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.RPCFiles.CMconnectR;
import in.ac.dei.edrp.client.RPCFiles.CMconnectRAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

public class CommonScreenForReporting {

	CMconnectRAsync connectService = (CMconnectRAsync) GWT
			.create(CMconnectR.class);

	private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT
			.create(CM_connectTemp.class);

	private final constants constant = GWT.create(constants.class);
	private final messages message = GWT.create(messages.class);

	/**
	 * This is the entry point method.
	 */
	final VerticalPanel vPanel = new VerticalPanel();
	public final VerticalPanel outerPanel = new VerticalPanel();

	final ComboBox entityTypeCBox = new ComboBox();
	final ComboBox entityNameCBox = new ComboBox();
	final ComboBox programNameCBox = new ComboBox();

	String userId = "";

	public CommonScreenForReporting(String userID) {
		this.userId = userID;
	}

	public VerticalPanel methodGenerateReport(String buttonName) {
		vPanel.clear();
		outerPanel.clear();

		final FlexTable upperFlexTable = new FlexTable();
		final VerticalPanel vStudentPanel = new VerticalPanel();

		final Label entityTypeLabel = new Label(constant.entityType());
		final Label entityNameLabel = new Label(constant.entityName());
		final Label programNameLabel = new Label(constant.programme());

		entityTypeCBox.setEmptyText(constant.emptyEntityType());
		entityNameCBox.setEmptyText(constant.emptyEntityName());
		programNameCBox.setEmptyText(constant.emptyProgramName());

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

		upperFlexTable.setWidget(0, 0, entityTypeLabel);
		upperFlexTable.setWidget(0, 1, entityTypeCBox);
		upperFlexTable.setWidget(1, 0, entityNameLabel);
		upperFlexTable.setWidget(1, 1, entityNameCBox);
		upperFlexTable.setWidget(2, 0, programNameLabel);
		upperFlexTable.setWidget(2, 1, programNameCBox);
		
		vPanel.setSpacing(20);
		vPanel.setSpacing(10);
		vPanel.add(upperFlexTable);
		vPanel.add(vStudentPanel);
		outerPanel.clear();
		outerPanel.setSize("100%", "100%");
		outerPanel.add(vPanel);
		outerPanel.setStyleName("background");

		connectService.methodentitypopulate(userId,
				new AsyncCallback<CM_ProgramInfoGetter[]>() {
					String[][] object2;

					public void onFailure(Throwable arg0) {
						MessageBox.alert(message.failure() + ":", arg0
								.getMessage());
					}

					public void onSuccess(CM_ProgramInfoGetter[] arg0) {
						RecordDef recordDef = new RecordDef(new FieldDef[] {
								new StringFieldDef("entity_type"),
								new StringFieldDef("entity_description") });

						object2 = new String[arg0.length][2];
						for (int i = 0; i < arg0.length; i++) {
							object2[i][0] = arg0[i].getComponentId();
							object2[i][1] = arg0[i].getComponentDescription();
							System.out.println(object2[i][0] + " and "
									+ object2[i][1]);
						}

						for (int l = 0; l < object2.length; l++) {
							System.out.println(object2[l][0] + " and "
									+ object2[l][1]);
						}

						MemoryProxy proxy = new MemoryProxy(object2);

						ArrayReader reader = new ArrayReader(recordDef);
						Store store = new Store(proxy, reader);
						store.load();

						entityTypeCBox.setStore(store);
					}
				});


		entityTypeCBox.addListener(new ComboBoxListenerAdapter() {

			public void onSelect(ComboBox comboBox, Record record, int index) {

				connectService.methodgetentitytype(comboBox.getValue(), userId,
						new AsyncCallback<CM_ProgramInfoGetter[]>() {
							String[][] object2;

							public void onFailure(Throwable arg0) {
								MessageBox.alert(message.failure() + ":", arg0
										.getMessage());
							}

							public void onSuccess(CM_ProgramInfoGetter[] arg0) {
								RecordDef recordDef = new RecordDef(
										new FieldDef[] {
												new StringFieldDef(
														"entity_name"),
												new StringFieldDef("entity_id") });

								object2 = new String[arg0.length][2];

								for (int i = 0; i < arg0.length; i++) {
									object2[i][0] = arg0[i].getEntity_name();
									object2[i][1] = arg0[i].getEntity_id();
									System.out.println(object2[i][0] + " and "
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

			public void onSelect(ComboBox comboBox, final Record record,
					int index) {

				int counter=1;
				connectTemp.getProgrammeOff(comboBox.getValue(),counter,
						new AsyncCallback<CM_ProgramInfoGetter[]>() {
							String[][] object2;
							public void onFailure(Throwable arg0) {
								MessageBox.alert(message.failure() + ":", arg0
										.getMessage());
							}

							public void onSuccess(CM_ProgramInfoGetter[] arg0) {
								RecordDef recordDef = new RecordDef(
										new FieldDef[] {
												new StringFieldDef(
														"program_name"),
												new StringFieldDef("program_id") });

								object2 = new String[arg0.length][2];
								for (int i = 0; i < arg0.length; i++) {
									object2[i][0] = arg0[i].getProgram_name();
									object2[i][1] = arg0[i].getProgram_id();
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

			}
		});

		return outerPanel;

	}// onmodule load

}
