package in.ac.dei.edrp.client.ProgramSetup;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.SystemTableTwo;
import in.ac.dei.edrp.client.RPCFiles.COS_DataService;
import in.ac.dei.edrp.client.RPCFiles.COS_DataServiceAsync;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
/**
 * this class is for Examination Center Setup For a Program
 * 
 * @version 1.0 8 May 2012
 * @author ARJUN SINGH
 */

public class ProgramExaminationCenter 
{
	private String userID;
	public VerticalPanel vPanel=new VerticalPanel();
	COS_DataServiceAsync cos_service=(COS_DataServiceAsync)GWT.create(COS_DataService.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);
	
	/** The constructor below takes the userID and set in userID variable of the class	 
	 */
	public ProgramExaminationCenter(String userID)
	{
		this.userID=userID;
	}
	
	/**The following function is created to setup 
	 * Program Examination Center 
	 
	 */
public void createCenter()
{
	vPanel.clear();//This statement removes all the fields in vPanel vertical panel every time 'Set Up Examination Center' menu item is clicked.
	
	final ComboBox programNameBox=new ComboBox();
	final ComboBox entranceCenterBox=new ComboBox();
	
	final ComboBox entityTypeBox=new ComboBox();
    
    
    entityTypeBox.setDisplayField("comp_desc");   
    entityTypeBox.setMode(ComboBox.LOCAL);   
    entityTypeBox.setTriggerAction(ComboBox.ALL);   
    entityTypeBox.setForceSelection(true);   
    entityTypeBox.setValueField("comp_id");   
    entityTypeBox.setReadOnly(true);  
     
    final ComboBox entityNameBox=new ComboBox();   
    entityNameBox.setForceSelection(true);
    entityNameBox.setMinChars(1);
    entityNameBox.setDisplayField("entityName");
    entityNameBox.setValueField("entityID");
    entityNameBox.setMode(ComboBox.LOCAL);
    entityNameBox.setTriggerAction(ComboBox.ALL);
    entityNameBox.setEmptyText("Select Entity Name");
    entityNameBox.setLoadingText("Searching...");
    entityNameBox.setTypeAhead(true);
    entityNameBox.setSelectOnFocus(true);
    entityNameBox.setHideTrigger(false);
   
    entityNameBox.setReadOnly(true);

    programNameBox.setFieldLabel("Select Program");   
      
    programNameBox.setDisplayField("program_name");   
    programNameBox.setMode(ComboBox.LOCAL);   
    programNameBox.setTriggerAction(ComboBox.ALL);   
    programNameBox.setForceSelection(true);   
    programNameBox.setValueField("program_id");   
    programNameBox.setReadOnly(true);
    
    entranceCenterBox.setFieldLabel("Select Entrance Center");   
    
    entranceCenterBox.setDisplayField("center_name");   
    entranceCenterBox.setMode(ComboBox.LOCAL);   
    entranceCenterBox.setTriggerAction(ComboBox.ALL);   
    entranceCenterBox.setForceSelection(true);   
    entranceCenterBox.setValueField("center_id");   
    entranceCenterBox.setReadOnly(true);
    
    /**
     * The following RPC to getEntranceCenter fetches the examination center from system_table_two
     */
    cos_service.getEntranceCenter(userID,new AsyncCallback<SystemTableTwo[]>()
    		{

				
				public void onFailure(Throwable arg0) {
					
				}

				
				public void onSuccess(SystemTableTwo[] result) {
					

					Object[][] object=new Object[result.length][2];
					for(int i=0;i<result.length;i++)
					{
						for(int j=0;j<2;j++)
						{
							if(j==0)
							{
								object[i][j]=result[i].getComponent_id();
							}
							else if(j==1)
							{
								object[i][j]=result[i].getComponent_description();
							}
						}
					}
					Store entranceStore=new SimpleStore(new String[]{"center_id","center_name"},object);
					entranceStore.load();
					entranceCenterBox.setStore(entranceStore); 
					
				
				}
    	
    		});
    
    /**
     * The following call to getEntityTypes fetches the entity type from system_table_two
     */
    cos_service.getEntityTypes(userID, new AsyncCallback<CM_ProgramInfoGetter[]>()
    		{

				
				public void onFailure(Throwable arg0) {
					
					
				}

				
				public void onSuccess(CM_ProgramInfoGetter[] result) {
					Object[][] object=new Object[result.length][2];
					for(int i=0;i<result.length;i++)
					{
						for(int j=0;j<2;j++)
						{
							if(j==0)
							{
								object[i][j]=result[i].getComponent_id();
							}
							else if(j==1)
							{
								object[i][j]=result[i].getComponentDescription();
							}
						}
					}
					Store entityTypeStore=new SimpleStore(new String[]{"comp_id","comp_desc"},object);
					entityTypeStore.load();
					entityTypeBox.setStore(entityTypeStore); 
					
				}
    	
    		});
    entityTypeBox.addListener(new ComboBoxListenerAdapter()
       {
    	   public void onSelect(ComboBox comboBox, Record record, int index)
    	   {
    		 
    		   entityNameBox.clearValue();
    		   programNameBox.clearValue();
    		   entranceCenterBox.clearValue();
    		  cos_service.getEntityNames(userID, entityTypeBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
    		  {

				
				public void onFailure(Throwable arg0) {
					
					
				}

				
				public void onSuccess(CM_ProgramInfoGetter[] result) {
					Object[][] object=new Object[result.length][2];
					for(int i=0;i<result.length;i++)
					{
						for(int j=0;j<2;j++)
						{
							if(j==0)
							{
								object[i][j]=result[i].getEntity_name();
							}
							else if(j==1)
							{
								object[i][j]=result[i].getEntity_id();
							}
						}
					}
					Store entityNameStore=new SimpleStore(new String[]{"entityName","entityID"},object);
					entityNameStore.load();
					entityNameBox.setStore(entityNameStore); 
				}
    			  
    		  });
    	   }
       });
    
entityNameBox.addListener(new ComboBoxListenerAdapter()
{
   public void onSelect(ComboBox comboBox, Record record, int index)
   {
	  
	   programNameBox.clearValue();
	   entranceCenterBox.clearValue();
	   cos_service.getProgramNames(userID,entityNameBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
			   {

				
				public void onFailure(Throwable arg0) {
					
					
				}

				
				public void onSuccess(CM_ProgramInfoGetter[] result) {

					Object[][] object=new Object[result.length][2];
					for(int i=0;i<result.length;i++)
					{
						for(int j=0;j<2;j++)
						{
							if(j==0)
							{
								object[i][j]=result[i].getProgram_id();
							}
							else if(j==1)
							{
								object[i][j]=result[i].getProgram_name();
							}
						}
					}
					Store programStore=new SimpleStore(new String[]{"program_id","program_name"},object);
					programStore.load();
					programNameBox.setStore(programStore); 
				
				}
		   
			   });
   }
});

Button submitButton=new Button(constants.Submit());
submitButton.addListener(new ButtonListenerAdapter()
{
   public void onClick(Button button, EventObject e)
   {
	   if(entityNameBox.getValue()==null||programNameBox.getValue()==null||entranceCenterBox.getValue()==null)
	   {
		 MessageBox.alert(msgs.ErrorTitle(),msgs.SelectAllField());  
	   }
	   else
	   {
	   cos_service.getExaminationCenter(entityNameBox.getValue(), programNameBox.getValue(),entranceCenterBox.getValue(), new AsyncCallback<CM_ProgramInfoGetter[]>()
			   {

				
				public void onFailure(Throwable arg0) {
					
					
				}

				
				public void onSuccess(CM_ProgramInfoGetter[] result) {
					
				if(result.length>=1)
				{
					MessageBox.alert(msgs.ErrorTitle(), msgs.AlreadyExists());
				}
				else
				{
					   MessageBox.confirm(msgs.ConfirmationTitle(), msgs.Confirmation(), new MessageBox.ConfirmCallback() {
							
							
							public void execute(String btnID) {
							if(btnID.matches("yes"))
							{

								   cos_service.insertEntranceCenter(userID,entityNameBox.getValue(), programNameBox.getValue(), entranceCenterBox.getValue(),new AsyncCallback()
								   {

									
									public void onFailure(Throwable arg0) {
										
										
									}

									
									public void onSuccess(Object arg0) {
										MessageBox.alert(msgs.SuccessTitle(),msgs.SuccessInsertMessage());
										entityNameBox.reset();
										entityTypeBox.reset();
										programNameBox.reset();
										entranceCenterBox.reset();
										
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


Button closeButton=new Button(constants.Close());
closeButton.addListener(new ButtonListenerAdapter()
{
	public void onClick(Button button, EventObject e)
	{
		vPanel.clear();
	}
});

FlexTable table=new FlexTable();
table.setWidget(0, 0, new Label(constants.EntityType()));
table.setWidget(1, 0, new Label(constants.EntityName()));
table.setWidget(2, 0, new Label(constants.ProgramName()));
table.setWidget(3, 0, new Label(constants.examCenter()));

table.setWidget(0, 1,entityTypeBox);
table.setWidget(1, 1, entityNameBox);
table.setWidget(2, 1, programNameBox);
table.setWidget(3, 1,entranceCenterBox);

table.setWidget(4, 0, submitButton);
table.setWidget(4, 1, closeButton);
table.setStyleName("examinationCenterTable");
table.setCellPadding(6);
table.setCellSpacing(6);
FormPanel fp=new FormPanel();
fp.setTitle(constants.SetExaminationCenter());
fp.setFrame(true);
fp.setStyleName("ecForm");
fp.add(table);
final VerticalPanel verPan=new VerticalPanel();
vPanel.add(fp);
}


}
