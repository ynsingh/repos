/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/
package in.ac.dei.edrp.client;

import in.ac.dei.edrp.client.RPCFiles.CM_connectD;
import in.ac.dei.edrp.client.RPCFiles.CM_connectDAsync;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.BooleanFieldDef;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.IntegerFieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextArea;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.event.GridCellListener;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * @author Dayal Sharan Sukhdhami
 */

/**
 * This class creates University tab for Super administrator
 */
public class CM_SuperUniversity {

	int minLength=0;
	SuggestBox suggestbox;
    public String userid;
    private final CM_connectDAsync connectDService = GWT.create(CM_connectD.class);
    public final HorizontalPanel universityTreePanelRight = new HorizontalPanel();

    Object[][] object1;
    
    final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
    final ListBox universityColumn = new ListBox();

    Label heading = new Label("Add University");
  
    String universityCode="";
    String universityName = "";
    Date sessionStartDate ;
    Date sessionEndDate ;
    String universityAddress = "";
    String universityCity = "";
    String universityState = "";
    String universityPincode = "";
    String universityPhoneNumber = "";
    String universityOtherPhoneNumber = "";
    String universityFaxNumber = "";    
       
//    String date = (new java.sql.Timestamp(new java.util.Date().getTime()).toString().substring(0,19));
    
    boolean flagd = false;
    
    final ListBox universityNameListBox = new ListBox();
    

    //Constructor for setting the Value of User ID 
    public CM_SuperUniversity(String Uid) {
        this.userid = Uid;
    }

    /**
     * Method for adding University
     */
    public void methodAddUniversity() {

        VerticalPanel vpanel = new VerticalPanel();
//        HorizontalPanel hpanel = new HorizontalPanel();
        HorizontalPanel buttonPanel = new HorizontalPanel();
        Button nextButton = new Button("Next");
        Button resetButton = new Button("Reset");

        universityTreePanelRight.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        
         final FormPanel addUniversityFormPanel = new FormPanel();
         final FormPanel addUniversityDetailsFormPanel = new FormPanel();
         
         addUniversityFormPanel.setFrame(true);  
         addUniversityFormPanel.setTitle("Add University");  
         
         addUniversityDetailsFormPanel.setFrame(true);  
         addUniversityDetailsFormPanel.setTitle("Add University Details");
              
         addUniversityFormPanel.setWidth(350);  
         addUniversityFormPanel.setLabelWidth(75);  
         addUniversityFormPanel.setUrl("save-form.php");  
         
          	  final TextField universityNameTextField = new TextField("University Name", "first", 230);  
//          	  addUniversityFormPanel.setAllowBlank(false);  
              addUniversityFormPanel.add(universityNameTextField);  
            
              final DateField sessionStartDateField = new DateField("Session Start Date", "last", 230);
              sessionStartDateField.setFormat("d-m-Y");
              sessionStartDateField.setAllowBlank(false);
           
              addUniversityFormPanel.add(sessionStartDateField);  
            
              final DateField sessionEndDateField = new DateField("Session End Date", "last", 230);
              sessionEndDateField.setFormat("d-m-Y");
              sessionEndDateField.setAllowBlank(false);
              addUniversityFormPanel.add(sessionEndDateField); 
              
              final TextArea  universityAddressTextArea = new TextArea("University Address", "company");  
              addUniversityDetailsFormPanel.add(universityAddressTextArea);
              
              final TextField  universityCityTextField = new TextField("University City", "company", 230);  
              addUniversityDetailsFormPanel.add(universityCityTextField); 
              
              final TextField  universityStateTextField = new TextField("University State", "company", 230);  
              addUniversityDetailsFormPanel.add(universityStateTextField); 
              
              final NumberField  universityPincodeNumberField = new NumberField("University Pincode", "company", 230);  
              addUniversityDetailsFormPanel.add(universityPincodeNumberField); 
              
              final NumberField  universityPhoneNumberField = new NumberField("Phone Number", "company", 230);  
              addUniversityDetailsFormPanel.add(universityPhoneNumberField); 
              
              final NumberField  universityOtherPhoneNumberField = new NumberField("Other Phone", "company", 230);  
              addUniversityDetailsFormPanel.add(universityOtherPhoneNumberField); 
              
              final NumberField  universityFaxNumberField = new NumberField("Fax Number", "company", 230);  
              addUniversityDetailsFormPanel.add(universityFaxNumberField); 
              

        universityAddressTextArea.setWidth(185);
        heading.setStyleName("Heading");
        
        universityNameTextField.setAllowBlank(false);
        sessionStartDateField.setAllowBlank(false);
        sessionEndDateField.setAllowBlank(false);    
        universityAddressTextArea.setAllowBlank(false);
        universityCityTextField.setAllowBlank(false);
        universityStateTextField.setAllowBlank(false);     
        universityPincodeNumberField.setAllowBlank(false);
        universityPincodeNumberField.setMaxLength(6);
        universityPincodeNumberField.setMinLength(6);
        universityPincodeNumberField.setAllowDecimals(false);
        universityPhoneNumberField.setAllowDecimals(false);
        universityOtherPhoneNumberField.setAllowDecimals(false);
        universityFaxNumberField.setAllowDecimals(false);
        universityPhoneNumberField.setMaxLength(20);
        universityOtherPhoneNumberField.setMaxLength(20);
        universityFaxNumberField.setMaxLength(20);

        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        buttonPanel.setSpacing(20);
        buttonPanel.add(nextButton);
//        buttonPanel.setSpacing(4);
        buttonPanel.add(resetButton);

        vpanel.setSpacing(4);
        vpanel.add(heading);
        
//        hpanel.add(addUniversityFormPanel);
//        hpanel.add(addUniversityDetailsFormPanel);
//        vpanel.add(hpanel);
        vpanel.add(addUniversityFormPanel);
        vpanel.add(addUniversityDetailsFormPanel);
        vpanel.add(buttonPanel);
        vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        resetButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button addButton, EventObject e) {
                    methodAddUniversity();
                }
            });
        
        nextButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button addButton, EventObject e) {

                    boolean valid = true;

                    try {
                    	universityNameTextField.validate();
                    	universityNameTextField.setValue(universityNameTextField.getText().trim());
                    } catch (Exception f) {
                        valid = false;
                    }

                    try {
                        universityAddressTextArea.validate();
                        universityAddressTextArea.setValue(universityAddressTextArea.getText().trim());
                    } catch (Exception f) {
                        valid = false;
                    }

                    try {
                    	universityCityTextField.validate();
                    	universityCityTextField.setValue(universityCityTextField.getText().trim());
                    } catch (Exception f) {
                        valid = false;
                    }

                    try {
                        universityStateTextField.validate();
                        universityStateTextField.setValue(universityStateTextField.getText().trim());
                    } catch (Exception f) {
                        valid = false;
                    }

                    try {
                        universityPincodeNumberField.validate();
                        universityPincodeNumberField.setValue(universityPincodeNumberField.getText().trim());
                    } catch (Exception f) {
                        valid = false;
                    }

                    try {
                        universityPhoneNumberField.validate();
                        universityPhoneNumberField.setValue(universityPhoneNumberField.getText().trim());
                    } catch (Exception f) {
                        valid = false;
                    }
                    
                    try {
                        universityOtherPhoneNumberField.validate();
                        universityOtherPhoneNumberField.setValue(universityOtherPhoneNumberField.getText().trim());
                    } catch (Exception f) {
                        valid = false;
                    }
                    
                    try {
                        universityFaxNumberField.validate();
                        universityFaxNumberField.setValue(universityFaxNumberField.getText().trim());
                    } catch (Exception f) {
                        valid = false;
                    }
                    try {
                    	sessionStartDateField.validate();
                        } catch (Exception f) {
                        valid = false;
                    }
                    	
                    try {
                    	sessionEndDateField.validate();
                    } catch (Exception f) {
                        valid = false;
                    }
                    int i= sessionStartDateField.getValue().compareTo(sessionEndDateField.getValue());
//                 	System.out.println("date compare value="+i); 

                 		if(i == -1){ 
                 			
                 		}else{
                 			sessionStartDateField.markInvalid("Invalid");
                 			sessionEndDateField.markInvalid("Invalid");
                            MessageBox.alert("Please insert Session End Date Greater than Session Start Date");
//                            System.out.println("Please insert Session End Date Greater than Session Start Date");
                            MessageBox.show(new MessageBoxConfig() {

                                {
                                    setTitle("Errr");
                                    setMsg("Please insert Session End Date Greater than Session Start Date");
//                                    setIconCls(MessageBox.ERROR);
                                    setButtons(MessageBox.OK);
                                }
                            });
                            valid = false;
                 		} 
                    
                    if (valid == false) {
                        MessageBox.show(new MessageBoxConfig() {

                                {
                                    setTitle("Error");
                                    setMsg("Kindly Fill All the mandatory fields");
                                    setIconCls(MessageBox.ERROR);
                                    setButtons(MessageBox.OK);
                                }
                            });
                    } else {

                    	
                        universityName = universityNameTextField.getText();
                    	sessionStartDate = sessionStartDateField.getValue();
                    	sessionEndDate = sessionEndDateField.getValue();
                        universityAddress = universityAddressTextArea.getText();
                        universityCity = universityCityTextField.getText();
                        universityState = universityStateTextField.getText();
                        universityPincode = universityPincodeNumberField.getText();
                        universityPhoneNumber = universityPhoneNumberField.getText();
                        universityOtherPhoneNumber = universityOtherPhoneNumberField.getText();
                        universityFaxNumber = universityFaxNumberField.getText(); 
                        
//                        System.out.println(sessionStartDateField.getValue());
//                        System.out.println(sessionStartDateField.getValueAsString());
//                                         
//                        System.out.println(universityName);
//                        System.out.println(sessionStartDate);
//                        System.out.println(sessionEndDate);
//                        System.out.println(universityAddress);
//                        System.out.println(universityCity);
//                        System.out.println(universityState);
//                        System.out.println(universityPincode);
//                        System.out.println(universityPhoneNumber);
//                        System.out.println(universityOtherPhoneNumber);
//                        System.out.println(universityFaxNumber);                        

                        flagd = true;
//                        System.out.println("hiiiiiiiii");
                        methodAddUniversityAdmin();

                    }
                }
            });

//        System.out.println("added VPANEL");
        universityTreePanelRight.clear();
        universityTreePanelRight.add(vpanel);
    }

    /**
     * Method to add University Administrator for the University.
     */
    public void methodAddUniversityAdmin() {
    	
//    	System.out.println("addinsti me aaya");
    	universityTreePanelRight.clear();
//    	System.out.println(universityInsertTime);
//    	System.out.println(universityCreatorID);
        VerticalPanel vpanel = new VerticalPanel();
        
        vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        
        FlexTable addInstiAdminTable = new FlexTable();
        HorizontalPanel buttonPanel = new HorizontalPanel();
        Button addButton = new Button("Add");
        Button resetButton = new Button("Reset");
        Label pageHeading = new Label("Add University Admin");
        Label instiNameLabel = new Label("University Name :");
        Label userIDLabel = new Label("User Name :");
        Label passwordLabel = new Label("Password :");
        Label confirmPasswordLabel = new Label("Confirm Password :");
        Label passMinLengthLabel = new Label(
                "(Password must be equal to or more than "+ minLength +" characters)");

        final TextField userIDTextField = new TextField();
        final TextField passwordTextField = new TextField();
        final TextField confirmPasswordTextField = new TextField();
        final TextField universityTextField = new TextField();	
        
        pageHeading.setStyleName("Heading");

//        vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        
        universityTextField.setReadOnly(true);
        passwordTextField.setInputType("password");
        confirmPasswordTextField.setInputType("password");
        userIDTextField.setAllowBlank(false);
        passwordTextField.setAllowBlank(false);
        confirmPasswordTextField.setAllowBlank(false);
        passwordTextField.setMinLength(minLength);
        confirmPasswordTextField.setMinLength(minLength);
        userIDTextField.setValidationEvent(false);
        passwordTextField.setValidationEvent(false);
        confirmPasswordTextField.setValidationEvent(false);

//        methodShowUniversity(1);
        
        universityTextField.setValue(universityName);
        
        addInstiAdminTable.setWidget(0, 1, pageHeading);
        addInstiAdminTable.setWidget(19, 0, instiNameLabel);
        
//        if(flagd == true){
        	addInstiAdminTable.setWidget(19, 1, universityTextField);

//        }else if(flagd == false){
//        	addInstiAdminTable.setWidget(19, 1, universityNameListBox);
//    	}
        addInstiAdminTable.setWidget(21, 0, userIDLabel);
        addInstiAdminTable.setWidget(21, 1, userIDTextField);
        addInstiAdminTable.setWidget(22, 0, passwordLabel);
        addInstiAdminTable.setWidget(22, 1, passwordTextField);
        addInstiAdminTable.setWidget(23, 1, passMinLengthLabel);
        addInstiAdminTable.setWidget(24, 0, confirmPasswordLabel);
        addInstiAdminTable.setWidget(24, 1, confirmPasswordTextField);
        addInstiAdminTable.setWidget(25, 1, buttonPanel);

        buttonPanel.setSpacing(10);
        buttonPanel.add(addButton);
        buttonPanel.add(resetButton);

//        methodinstituteList();

          
//        universityNameListBox.addClickListener(new ClickListener() {
//                public void onClick(Widget arg0) {
//                    if (universityNameListBox.getItemCount() == 0) {
//                        MessageBox.show(new MessageBoxConfig() {
//
//                                {
//                                    setTitle("No Institute");
//                                    setMsg(
//                                        "No University left without University Administrator");
//                                    setIconCls(MessageBox.ERROR);
//                                    setButtons(MessageBox.OK);
//                                    methodAddUniversity();
//                                }
//                            }
//                        );
//                    }
//                }
//            });

        vpanel.setSpacing(20);
        // vpanel.add(pageHeading);
        vpanel.add(addInstiAdminTable);
         vpanel.add(buttonPanel);
        resetButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button addButton, EventObject e) {
                	methodAddUniversityAdmin();
                }
            });

        addButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button addButton, EventObject e) {
                    Boolean flag = true;

//                    System.out.println("add mein to aaya.");
                    try {
                    	userIDTextField.validate();
                    	userIDTextField.setValue(userIDTextField.getText().trim());
                    } catch (Exception e1) {
                        flag = false;
                    }

                    try {
                    	passwordTextField.validate();
                    	passwordTextField.setValue(passwordTextField.getText().trim());
                    } catch (Exception e2) {
                        flag = false;
                    }

                    try {
                    	confirmPasswordTextField.validate();
                    	confirmPasswordTextField.setValue(confirmPasswordTextField.getText()
                                                                        .trim());
                    } catch (Exception e3) {
                        flag = false;
                    }

                    if (flag == false) {
                        MessageBox.show(new MessageBoxConfig() {

                                {
                                    setTitle("Error");
                                    setMsg("Kindly Fill All the mandatory fields");
                                    setIconCls(MessageBox.ERROR);
                                    setButtons(MessageBox.OK);
                                }
                            });
                    } else {
                        if (passwordTextField.getText()
                                            .equals(confirmPasswordTextField.getText())) {
                        	
                        	
                        	
                        	if(flagd == true){
//                            	System.out.println("ander kaise aaya.");
//                            	methodShowUniversity(1); 
                                
                            	String universityInsertTime= (new java.sql.Timestamp(new java.util.Date().getTime()).toString().substring(0,19));
                            	String universityCreatorID =userid;
                            	
//                            	Date sessionSDate.setValue(sessionStartDate);
//                            	String.valueOf(arg0[i].getSessionEndDate());
                            	
//                            	System.out.println(universityInsertTime);
//                            	System.out.println(universityCreatorID);
//                            	System.out.println("Start date-> "+sessionStartDate);
//                            	System.out.println("End date-> "+sessionEndDate);
                            	
                            	connectDService.methodAddUniversity(universityCode,
                                sessionStartDate, sessionEndDate, universityName, universityAddress,
                                universityCity, universityState, universityPincode,
                                universityPhoneNumber, universityOtherPhoneNumber,
                                universityFaxNumber, universityInsertTime, universityCreatorID, 
                                        new AsyncCallback<String>(){
                                            public void onFailure(final Throwable arg0) {
                                                MessageBox.show(new MessageBoxConfig() {

                                                        {
                                                            String s = arg0.getMessage();
                                                            String[] a = s.split(":");

                                                            setTitle("Error");
                                                            setMsg("Database Error: " + a[1] + a[2]);
                                                            setIconCls(MessageBox.WARNING);
                                                            setButtons(MessageBox.OK);
                                                        }
                                                    });
                                            }

                                            
                                            
                                            public void onSuccess(String arg0) {
                                               	String userName = userIDTextField.getText();
                                            	String password = passwordTextField.getText();
                                            	String userGroupName = "Institute Admin";
                                            	String regTimeStamp = (new java.sql.Timestamp(new java.util.Date().getTime()).toString().substring(0,19));
                                            	boolean activated = true;
//                                            	System.out.println(universityName+ ", "+userGroupName +", "+userName+", "+ password+", "+ regTimeStamp+", "+ activated);
                                            	
                                                connectDService.methodAddUniversityAdmin(flagd, universityName, userGroupName, userName, password,
                                                		regTimeStamp, activated, 
                                                    new AsyncCallback<String>() {
                                                
                                                	public void onFailure(Throwable arg0) {
                                                            MessageBox.alert("error in methodAddUniversityAdmin method....DataBase Error : " +
                                                                arg0.getMessage());
                                                        }

                                                    public void onSuccess(String arg0) {
                                                            MessageBox.show(new MessageBoxConfig() {

                                                                    {
                                                                        setTitle("Congratulations");
                                                                        
                                                                        setMsg(
                                                                            "University and University Admin for " +
                                                                              universityName +
                                                                               " successfully Added");
                                                                         setIconCls(MessageBox.INFO);
                                                                         setButtons(MessageBox.OK);   
                                                                         setCallback(new MessageBox.PromptCallback() {
                                                                             public void execute(
                                                                                 String btnID,
                                                                                 String text) {
                                                                                 if (btnID.equals(
                                                                                             "ok")) {
                                                                                	    methodAddUniversity();
                                                                                 }
                                                                             }
                                                                         });                         
                                                                    
                                                                        
                                                                        
                                                                    }  
                                                                });
                                                            
                                                            
                                                            
                                                        } 
                                                    }
                                                		
                                                );
////                                                MessageBox.confirm("Confirm",
////                                                    "Do you want to Add Institute Administrator Now?",
////                                                    new MessageBox.ConfirmCallback() 
////                                                {
////                                                        public void execute(String btnID) {
////                                                            if (btnID.equals("yes")) {
////                                                                methodAddInstituteAdmin();
////                                                            } else 
////                                                            {
//                                                        MessageBox.show(new MessageBoxConfig() {
//
//                                                                        {
//                                                                            setTitle(
//                                                                                "Congratulations");
//                                                                            setMsg(
//                                                                                "University Information Successfully Added");
//                                                                            setIconCls(MessageBox.INFO);
//                                                                            setButtons(MessageBox.OK);
////                                                                            methodAddInstituteAdmin();
//                                                                            
//                                                                        }
//                                                                    });
////                                                            }
////                                                        }
////                                                    });
                                            }

                                        }); 
                                } 
                        	
                        	
                        	
//                        	if(flagd == false){
//                        		universityName = universityNameListBox.getValue(universityNameListBox.getSelectedIndex());
////                        		System.out.println("selected University: "+ universityName);
//                        	}else if(flagd == true){
//                        		
                        		universityTextField.setValue(universityName);
//                        	
//                        	}

                     
                        } else {
                            MessageBox.show(new MessageBoxConfig() {

                                    {
                                        setTitle("Password Error");
                                        setMsg("Password Strings do not match");
                                        setIconCls(MessageBox.ERROR);
                                        setButtons(MessageBox.OK);
                                    }
                                });
                        }
                    }
                }
            });
        
        
//        instituteTreePanelRight.clear();
        universityTreePanelRight.add(vpanel);
        
    }

    
/*****************************************************************************************************************/
 
    
    public void methodShowUniversity(final int i) {
    	
    	connectDService.methodUniversityList(new AsyncCallback<CM_UniversityInfoGetter[]>() {
            public void onFailure(Throwable arg0) {
                MessageBox.alert("DataBase Error : " + arg0.getMessage());
            }

            public void onSuccess(CM_UniversityInfoGetter[] result) {

                String str = "";

                universityNameListBox.clear();
      
                for (int i = 0; i < result.length; i++) {
                		str = result[i].getUniversityName();
                		universityNameListBox.addItem(str);
                }

            }

		
        });
    }
    
    /**
     * Method to generate list of Universities.
     */
    public void methodUniversityList()  {

		
    		universityTreePanelRight.clear();
    		
    		final CheckboxSelectionModel cbSelectionModel = new CheckboxSelectionModel();
//    		final RowSelectionModel rSelectionModel = new RowSelectionModel(true);
    	
	    	final VerticalPanel vpanel1 = new VerticalPanel();
	    	
//	    	vpanel1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	    	
	    	final VerticalPanel vpanel2 = new VerticalPanel();
//	    	vpanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	    	
		    final SuggestBox suggestbox = new SuggestBox(createCountriesOracle());
		    FlexTable searchtable = new FlexTable();
	    
		    searchtable.getFlexCellFormatter(); 
		    
		    Label search = new Label("Manage Universities:");
		    search.setStyleName("Heading1");
		    vpanel1.setSpacing(2);
		    Label searchBy = new Label("Criteria: ");
		    
		    universityColumn.setPixelSize(155, 5);
		    universityColumn.clear();
		    universityColumn.addItem("          Select");
		    universityColumn.addItem("University ID");
		    universityColumn.addItem("University Name");
		    universityColumn.addItem("University City");
		    universityColumn.addItem("University State");
		      
		    Label uniLabel = new Label("Value: ");
		   
//		    panel.addStyleName("demo-panel-padded");
		    Button okButton = new Button("ok");
		    
		    searchtable.setWidget(3, 0, searchBy);
		    searchtable.setWidget(3, 1, universityColumn);
		    searchtable.setWidget(4, 0, uniLabel);
		    searchtable.setWidget(4, 1, suggestbox);
		    searchtable.setWidget(5, 1, okButton);
		        
		    vpanel1.add(search);
		    vpanel1.add(searchtable);
		       

		    okButton.addListener(new ButtonListenerAdapter(){

				@Override
				public void onClick(Button button, EventObject e) {
										
					final String criteria = universityColumn.getItemText(universityColumn.getSelectedIndex());
					String value = suggestbox.getText();
//					MessageBox.alert(value);
					
					connectDService.methodManageUniversityList(criteria, value, 
							new AsyncCallback<CM_UniversityInfoGetter[]>(){

						@Override
						public void onFailure(Throwable arg0) {
							MessageBox.alert("Database Error:" + arg0);
						}

						@Override
						public void onSuccess(final CM_UniversityInfoGetter[] arg0) {

							
							final RecordDef rDef = new RecordDef(
				    				new FieldDef[]{
				    						new StringFieldDef("University Name"),
				    						new StringFieldDef("University ID"),
				    						new StringFieldDef("Session Start Date"),
				    						new StringFieldDef("Session End Date"),
				    						new BooleanFieldDef("Current Status"),
				    						new StringFieldDef("University Address"),
				    						new StringFieldDef("University City"),
				    						new StringFieldDef("University State"),
				    						new IntegerFieldDef("Pincode"),
				    						new IntegerFieldDef("Phone Number"),
				    						new IntegerFieldDef("Other Phone"),
				    						new IntegerFieldDef("Fax Number")
				    						
				    						 						 
				    				}
				    		);
				    		
							
				    		final GridPanel grid = new GridPanel();
				    		
				    		object1= new Object[arg0.length][12];
							 
			                String str = null;
				    			for(int i=0 ; i < arg0.length ; i++){
								
				             	for (int k = 0; k < 12; k++) {
				                     if (k == 0) {
				                         str = arg0[i].getUniversityName();
				                         System.out.println(k+"1"+str);
				                     } else if (k == 1) {
				                         str = arg0[i].getUniversityCode();
				                     } else if (k == 2) {
				                         str = String.valueOf(arg0[i].getSessionStartDate());
//				                         System.out.println(String.valueOf(arg0[i].getSessionStartDate().getDate()));
//				                         System.out.println(String.valueOf(arg0[i].getSessionStartDate().getMonth()));
//				                         System.out.println(String.valueOf(arg0[i].getSessionStartDate().getYear()));
				                     } else if (k == 3) {
				                    	 arg0[i].getSessionEndDate();
				                         str = String.valueOf(arg0[i].getSessionEndDate());
				                         System.out.println("tiwari"+str);
				                     } else if (k == 4) {
				                         str = arg0[i].getCurrentStatus();
				                     } else if (k == 5) {
				                         str = arg0[i].getUniversityAddress();
				                     } else if (k == 6) {
				                         str = arg0[i].getUniversityCity();
				                     } else if (k == 7) {
				                         str = arg0[i].getUniversityState();
				                     } else if (k == 8) {
				                    	 str = arg0[i].getUniversityPincode();
				                     } else if (k == 9) {
				                    	 str = arg0[i].getUniversityPhoneNumber();
				                     } else if (k == 10) {
				                    	 str = arg0[i].getUniversityOtherPhoneNumber();
				                     } else if (k == 11) {
				                    	 str = arg0[i].getUniversityFaxNumber();
				                     }
				                     	
				             		 object1[i][k]=str;  
//				             		 System.out.println("result "+object1[i][k]);

				                 }

								};
								
							  Object[][] data = object1;			 

				    		  MemoryProxy proxy = new MemoryProxy(data);  
				    		   
				    		         ArrayReader reader = new ArrayReader(rDef);  
				    		         Store store = new Store(proxy, reader);  
				    		         store.load();  
				    		         grid.setStore(store);  

				    		         BaseColumnConfig[] columns = new BaseColumnConfig[]{  
				    		                 new CheckboxColumnConfig(cbSelectionModel),  
				    		                 //column ID is company which is later used in setAutoExpandColumn  
				    		                 new ColumnConfig("University Name", "University Name", 200, true, null, "uniname"),  
				    		                 new ColumnConfig("University ID", "University ID", 50, true,null, "unicode"),
				    		                 new ColumnConfig("Session Start Date", "Session Start Date", 50, true,null, "startdate"),
				    		                 new ColumnConfig("Session End Date", "Session End Date", 50, true,null, "enddate"),
				    		                 new ColumnConfig("Current Status", "Current Status", 50, true,null, "currentstatus"),
				    		                 new ColumnConfig("University Address", "University Address", 100, true, null, "uniadd"),  
				    		                 new ColumnConfig("University City", "University City", 80, true, null, "unicity"),  
				    		                 new ColumnConfig("University State", "University State", 80, true, null, "unistate"),  
				    		                 new ColumnConfig("Pincode", "Pincode", 55, true, null),
				    		                 new ColumnConfig("Phone Number", "Phone Number", 55, true, null),
				    		                 new ColumnConfig("Other Phone", "Other Phone", 55, true, null),
				    		                 new ColumnConfig("Fax Number", "Fax Number", 55, true, null),
				    		                
				    		         };  
				    		   
				    		         final ColumnModel columnModel = new ColumnModel(columns);  
				    		         grid.setColumnModel(columnModel);  
				    		   
				    		         grid.setFrame(true);  
				    		         grid.setStripeRows(true);  
				    		         grid.setAutoExpandColumn("uniname");
				    		         grid.setAutoExpandColumn("unicode");
				    		         grid.setAutoExpandColumn("uniadd");
				    		         grid.setAutoExpandColumn("unicity");
				    		         grid.setAutoExpandColumn("unistate");
				    		         
				    		        
				    		         grid.setSelectionModel(cbSelectionModel);  
				    		        
				    		         grid.setWidth(800);  
				    		         grid.setHeight(300); 
				    		  
				    		         Toolbar topToolBar = new Toolbar();
				    		         topToolBar.addFill();
				    	       
				    		         
				    		         topToolBar.addButton(new ToolbarButton("Edit", new ButtonListenerAdapter(){
						    		       public void onClick(Button editButton, EventObject e) {  
						    		           Record[] records = cbSelectionModel.getSelections();
						    		           cbSelectionModel.clearSelections();
						    		           
						    		           String msg = "";
						    		           if(records.length < 1){
						    		        	   MessageBox.alert("Note", "Please select a Record for Editing");
						    		           }
						    		           else if(records.length > 1){
						    		        	   MessageBox.alert("Note", "Please select only One Record at a time for editing");
						    		           }
						    		           else if(records.length == 1){
						    		        	   
						    		        	   Record record = records[0];

						    		        	   msg += record.getAsString("University Name");  

						    		        	   String[]Univ = new String[12];

						    		        	   Univ[0] = record.getAsString("University Name");
						    		        	   Univ[1] = record.getAsString("University ID");
						    		        	   Univ[2] = record.getAsString("Session Start Date");
						    		        	   Univ[3] = record.getAsString("Session End Date");
						    		        	   Univ[4] = record.getAsString("Current Status");
						    		        	   Univ[5] = record.getAsString("University Address");
						    		        	   Univ[6] = record.getAsString("University City");
						    		        	   Univ[7] = record.getAsString("University State");
						    		        	   Univ[8] = record.getAsString("Pincode");
						    		        	   Univ[9] = record.getAsString("Phone Number");
						    		        	   Univ[10] = record.getAsString("Other Phone");
						    		        	   Univ[11] = record.getAsString("Fax Number");
						    		        	   
						    		        	   System.out.println(Univ[1] +Univ[0]+ Univ[8]);
						    		        	   
						    		        	   FlexTable editUniversityTable = new FlexTable();
						    		        	   
						    		        	   Label uniNameLabel = new Label("University Name");
						    		        	   Label uniIDLabel = new Label("University ID");
						    		        	   Label SSDLabel = new Label("Session Start Date");
						    		        	   Label SEDLabel = new Label("Session End Date");
						    		        	   Label cStatusLabel = new Label("Current Status");
						    		        	   Label uniAddressLabel = new Label("University Address");
						    		        	   Label uniCityLabel = new Label("University City");
						    		        	   Label uniStateLabel = new Label("University State");
						    		        	   Label pinLabel = new Label("Pincode");
						    		        	   Label phoneLabel = new Label("Phone Number");
						    		        	   Label otherPhoneLabel = new Label("Other Phone");
						    		        	   Label faxLabel = new Label("Fax Number");
						    		        	   
						    		        	   TextField universityNameTextField = new TextField();
						    		        	   universityNameTextField.setValue(Univ[0]);
						    		        	   
						    		        	   TextField universityIDTextField = new TextField();
						    		        	   universityIDTextField.setValue(Univ[1]);
						    		        	   universityIDTextField.setReadOnly(true);
						    		        	   
						    		        	   
						    		        	   final DateField sessionStartDateField = new DateField();
						    		        	   sessionStartDateField.setFormat("d-m-Y");
						    		        	   try{
						    		        		System.out
															.println(Format.date(Univ[2],
					                                "Y-m-d"));
						    		        	   sessionStartDateField.setValue(Format.date(Univ[2],
					                                "Y-m-d"));
						    		        	   }catch (Exception e1) {
													System.out
															.println(e1);
												}
						    		        	   
						    		        	   final DateField sessionEndDateField = new DateField();
						    		        	   sessionEndDateField.setFormat("d-m-Y");
						    		        	   sessionEndDateField.setValue(Univ[3]);
						    		        	   
						    		        	   TextField currentStatus = new TextField();
						    		        	   currentStatus.setValue(Univ[4]);
						    		        	   
						    		        	   TextField universityAddressTextField = new TextField();
						    		        	   universityAddressTextField.setValue(Univ[5]);
						    		        	   
						    		        	   TextField universityCityTextField = new TextField();
						    		        	   universityCityTextField.setValue(Univ[6]);
						    		        	   
						    		        	   TextField universityStateTextField = new TextField();
						    		        	   universityStateTextField.setValue(Univ[7]);
						    		        	   
						    		        	   NumberField PinNumberField = new NumberField();
						    		        	   PinNumberField.setValue(Univ[8]);
						    		        	   
						    		        	   NumberField phoneNumberField = new NumberField();
						    		        	   phoneNumberField.setValue(Univ[9]);
						    		        	   
						    		        	   NumberField otherPhoneNumberField = new NumberField();
						    		        	   otherPhoneNumberField.setValue(Univ[10]);
						    		        	   
						    		        	   NumberField faxNumberField = new NumberField();
						    		        	   faxNumberField.setValue(Univ[11]);
						    		        	   
						    		        	   
						    		        	   editUniversityTable.clear();
						    		        	   
						    		        	   editUniversityTable.setWidget(2, 0, uniIDLabel);
						    		        	   editUniversityTable.setWidget(2, 1, universityIDTextField);
						    		        	   editUniversityTable.setWidget(3, 0, uniNameLabel);
						    		        	   editUniversityTable.setWidget(3, 1, universityNameTextField);
						    		        	   editUniversityTable.setWidget(4, 0, SSDLabel);
						    		        	   editUniversityTable.setWidget(4, 1, sessionStartDateField);	        
						    		        	   editUniversityTable.setWidget(5, 0, SEDLabel);
						    		        	   editUniversityTable.setWidget(5, 1, sessionEndDateField);
						    		        	   editUniversityTable.setWidget(6, 0, cStatusLabel);
						    		        	   editUniversityTable.setWidget(6, 1, currentStatus);
						    		        	   editUniversityTable.setWidget(7, 0, uniAddressLabel);
						    		        	   editUniversityTable.setWidget(7, 1, universityAddressTextField);
						    		        	   editUniversityTable.setWidget(8, 0, uniCityLabel);
						    		        	   editUniversityTable.setWidget(8, 1, universityCityTextField);
						    		        	   editUniversityTable.setWidget(9, 0, uniStateLabel);
						    		        	   editUniversityTable.setWidget(9, 1, universityStateTextField);    	   
						    		        	   editUniversityTable.setWidget(10, 0, pinLabel);
						    		        	   editUniversityTable.setWidget(10, 1, PinNumberField);
						    		        	   editUniversityTable.setWidget(11, 0, phoneLabel);
						    		        	   editUniversityTable.setWidget(11, 1, phoneNumberField);
						    		        	   editUniversityTable.setWidget(12, 0, otherPhoneLabel);
						    		        	   editUniversityTable.setWidget(12, 1, otherPhoneNumberField);
						    		        	   editUniversityTable.setWidget(13, 0, faxLabel);
						    		        	   editUniversityTable.setWidget(13, 1, faxNumberField);

						    		          final Panel p1 = new Panel();   
								    		  p1.clear();
								    		  p1.add(editUniversityTable); 	   
						    		        	   
						    		       	 final Window window = new Window();
						       				 window.setTitle("University Details");
						       		         window.setWidth(400);  
						       		         window.setHeight(400);  
						       		         window.setMinWidth(300);  
						       		         window.setMinHeight(200);  
						       		         window.setLayout(new FitLayout());  
						       		         window.setPaddings(5);  
						       		         window.setButtonAlign(Position.CENTER);  
						       		         window.addButton(new Button("Edit"));   

						       		         window.setPlain(true);  
						   	    		     window.setResizable(false);
//						   	    		     window.setLayout(new BorderLayout());    
						   	    		     window.setAutoScroll(true);
						   	    		     window.setCloseAction(Window.CLOSE); 
						   	    		     window.setFrame(true);
						   	    		     window.setClosable(true);
						   	    		     window.setModal(true);


						    		           window.add(p1);
						    		           window.show();
						    		               
						    		           grid.clear();
						    		           
						    		           }
						    		           
						    		            System.out.println("Records Selected :" + msg);  
						    		       }   
				    		         	}				    		        	 
				    		         ));
				    		         
				    		         topToolBar.addButton(new ToolbarButton("Delete", new ButtonListenerAdapter(){
						    			  public void onClick(Button delButton, EventObject e){
						    				  Record[] records = cbSelectionModel.getSelections();
						    				  String msg = "";
						    				  if(records.length<1){
						    					  MessageBox.alert("No", "Please select a record for deletion");
						    				  }
						    				  for(int i=0; i < records.length; i++){				    					   
							    		               Record record = records[i];  
							    		               msg += record.getAsString("University Name") + " ";  
		 
						    					  
						    					 MessageBox.alert("Yes", msg);  
						    				  }				    				  
						    			  } 
				    		         	}
				    		         ));
				    		         
				    		         grid.setTopToolbar(topToolBar);
				    		    

				    		grid.addGridCellListener(new GridCellListener(){

								@Override
								public void onCellClick(GridPanel grid,
										int rowIndex, int colIndex,
										EventObject e) {
//									Record[] records = cbSelectionModel.getSelections();
//									 Record record = records[0];
//									
								}

								@Override
								public void onCellContextMenu(GridPanel grid,
										int rowIndex, int cellIndex,
										EventObject e) {
									
									
								}

								@Override
								public void onCellDblClick(GridPanel grid,
										int rowIndex, int colIndex,
										EventObject e) {
									
									
								}
				    			
				    		});      
				        	
				        	grid.setTitle("University Details");
		    		          
		    		         grid.setIconCls("staff_icon");  
		    					    		          
				    	  vpanel2.clear();
				          vpanel2.add(grid);

						}								
						
					});  					
				}				
		    }		    				
		    );

		    vpanel1.add(okButton);
		    vpanel1.add(vpanel2);
//		    instituteTreePanelRight.clear();
		    universityTreePanelRight.add(vpanel1);
	    
	}
    
    MultiWordSuggestOracle createCountriesOracle(){    
		System.out.println("in oracle");
		connectDService.methodManageUniversityList(null,null, new AsyncCallback<CM_UniversityInfoGetter[]>() {
	        public void onFailure(Throwable arg0) {
	            MessageBox.alert("DataBase Error : " + arg0.getMessage());
	        }

	        public void onSuccess(final CM_UniversityInfoGetter[] result) {          
			    
	        	universityColumn.addChangeHandler(new ChangeHandler (){

					public void onChange(ChangeEvent arg0) {
						
						String str = "";
						
						int selectedUniversityColumn = universityColumn.getSelectedIndex();
//						System.out.println("selected Index is: "+ selectedInstiCol);
						
						if(selectedUniversityColumn==1){
							oracle.clear();
			            	for (int i = 0; i < result.length; i++){
			                        str = result[i].getUniversityCode();
			                        oracle.add(str);                        
			            	}   
			            }
						else if(selectedUniversityColumn==2){
							oracle.clear();
			            	for (int i = 0; i < result.length; i++){    
			                        str = result[i].getUniversityName();
			                        oracle.add(str);                         
			            	}   
			            } 
						else if(selectedUniversityColumn==3){
							oracle.clear();
							for (int i = 0; i < result.length; i++){
			                        str = result[i].getUniversityCity();
			                        oracle.add(str);                        
			            	}   
			            }
						else if(selectedUniversityColumn==4){
							oracle.clear();
			            	for (int i = 0; i < result.length; i++){
			                        str = result[i].getUniversityState();
			                        oracle.add(str);                        
			            	}   
			            }
										
					}
			    	
			    });    
	            
	        }
	        
	    });
	  return oracle;

	}
        
    
//	MultiWordSuggestOracle createCountriesOracle(){    
//		System.out.println("in oracle");
//		connectService.methodInstituteList(null,null, new AsyncCallback<CM_instituteInfoGetter[]>() {
//	        public void onFailure(Throwable arg0) {
//	            MessageBox.alert("DataBase Error : " + arg0.getMessage());
//	        }
//
//	        public void onSuccess(final CM_instituteInfoGetter[] result) {          
//
//			    
//			    instiCol.addChangeHandler(new ChangeHandler (){
//
//					public void onChange(ChangeEvent arg0) {
//						
//						String str = "";
//						
//						int selectedInstiCol = instiCol.getSelectedIndex();
////						System.out.println("selected Index is: "+ selectedInstiCol);
//						
//						if(selectedInstiCol==1){
//							oracle.clear();
//			            	for (int i = 0; i < result.length; i++){
//			                        str = result[i].getId();
//			                        oracle.add(str);                        
//			            	}   
//			            }
//						else if(selectedInstiCol==2){
//							oracle.clear();
//			            	for (int i = 0; i < result.length; i++){    
//			                        str = result[i].getName();
//			                        oracle.add(str);                         
//			            	}   
//			            } 
//						else if(selectedInstiCol==3){
//							oracle.clear();
//							for (int i = 0; i < result.length; i++){
//			                        str = result[i].getcity();
//			                        oracle.add(str);                        
//			            	}   
//			            }
//						else if(selectedInstiCol==4){
//							oracle.clear();
//			            	for (int i = 0; i < result.length; i++){
//			                        str = result[i].getstate();
//			                        oracle.add(str);                        
//			            	}   
//			            }
//										
//					}
//			    	
//			    });    
//	            
//	        }
//	        
//	    });
//	  return oracle;
//
//	}
	
}