package in.ac.dei.edrp.client.Login;

import in.ac.dei.edrp.client.CM_passwordPolicyGetter;
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;
import in.ac.dei.edrp.client.Shared.CourseManagement;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.TextField;


public class Login implements EntryPoint {
	
    private final CM_LoginConnectSAsync connectService = GWT.create(CM_LoginConnectS.class);
    private final CM_connectAsync connect = GWT.create(CM_connect.class);
    messages msg = GWT.create(messages.class);
    constants labels=GWT.create(constants.class);
    
    String username;
    String password;
    String uID;
    String role;
    String Password;
    int check = 0;
    public final VerticalPanel mainPanel = new VerticalPanel();
    final VerticalPanel headerPanel = new VerticalPanel();
    final HorizontalPanel bodyPanel = new HorizontalPanel();
    final VerticalPanel footerPanel = new VerticalPanel();
    final HorizontalPanel detailsPanel = new HorizontalPanel();
    final HorizontalPanel HypMainPanelTop = new HorizontalPanel();
    final HorizontalPanel HypMainPanelBottom = new HorizontalPanel();
    final VerticalPanel labelPanel = new VerticalPanel();
    final VerticalPanel entryPanel = new VerticalPanel();
    final HorizontalPanel linkPanelBottom = new HorizontalPanel();
    final HorizontalPanel linkPanelTop = new HorizontalPanel();
    final DecoratorPanel BodyDecoratorPanel = new DecoratorPanel();
    FlexTable BodyFlexTable = new FlexTable();
    final Button login = new Button(labels.loginButton());
    final Label headerLabel = new Label(
            labels.title());
    final Label footerLabel = new Label(
            labels.footer());
    final Hyperlink home = new Hyperlink("Home", "Home");
    final Hyperlink blank = new Hyperlink("", "");
    final Hyperlink Feedback = new Hyperlink("Feedback", "Feedback");
    TextField useridTextField = new TextField();
    TextField passwordTextField = new TextField();
    Label passwordLabel = new Label(labels.password());
    Label useridLabel = new Label(labels.userid());
    Label uidexampleLabel = new Label(labels.egUserid());
    Label pwdexampleLabel = new Label();
    MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
    SuggestBox suggestBox = new SuggestBox(oracle);
    int minLength;
    int maxlength;
    int expiryPeriod;

    public void onModuleLoad() {
    	     init();
          }

    VerticalPanel init() {
    	
    	  final ComboBox comboBox = new ComboBox();
    	  
    	  comboBox.setForceSelection(true);
          comboBox.setMinChars(1);
          comboBox.setDisplayField("language");
          comboBox.setValueField("code");
          comboBox.setMode(ComboBox.LOCAL);
          comboBox.setTriggerAction(ComboBox.ALL);
          comboBox.setEmptyText("Choose Language");
          comboBox.setLoadingText("Searching...");
          comboBox.setTypeAhead(true);
          comboBox.setSelectOnFocus(true);
          comboBox.setWidth(150);
          comboBox.setHideTrigger(false);
          comboBox.setAllowBlank(false);
    	  
          RecordDef recordDef = new RecordDef(new FieldDef[] {
                  new StringFieldDef("language"),
                  new StringFieldDef("code"),
              });

          Object[][] object = new String[2][2];

      Object[][] data = object;

          object[0][0] = "Original";
          object[0][1] = "?locale=";
          object[1][0] = "Dummy";
          object[1][1] = "?locale=dummy";

      MemoryProxy proxy = new MemoryProxy(data);

      ArrayReader reader = new ArrayReader(recordDef);
      Store store = new Store(proxy, reader);
      store.load();

      comboBox.setStore(store);
    	
    	//Property Set***********************************
        VerticalPanel BodyVerticalPanel = new VerticalPanel();

//        String[] words = { "a", "ab", "c", "cd", "abc", "abcd", "abcde", "bcd" };
//
//        for (int i = 0; i < words.length; ++i) {
//            oracle.add(words[i]);
//        }

        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        headerPanel.setWidth("100%");
        footerPanel.setWidth("100%");
        HypMainPanelTop.setWidth("100%");
        HypMainPanelBottom.setWidth("100%");
        headerPanel.setStyleName("headerBlueBack");
        footerPanel.setStyleName("footerBlueBack");
        HypMainPanelTop.setStyleName("logOutPanel");
        HypMainPanelBottom.setStyleName("logOutPanel");
        headerLabel.setStyleName("MainHeading");
        footerLabel.setStyleName("heading1");

        labelPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        entryPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        bodyPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        labelPanel.setSpacing(5);
        entryPanel.setSpacing(5);
        headerPanel.setSpacing(5);
        footerPanel.setSpacing(5);

        useridLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        
        useridLabel.setStyleName("Heading1");
        passwordLabel.setStyleName("Heading1");
        passwordTextField.setInputType("password");
        //        passwordTextField.setMinLength(6);
        BodyFlexTable.setWidget(60, 230, useridLabel);
        BodyFlexTable.setWidget(60, 232, useridTextField);
        //        BodyFlexTable.setWidget(60, 232, suggestBox);
        BodyFlexTable.setWidget(61, 232, uidexampleLabel);
        BodyFlexTable.setWidget(65, 230, passwordLabel);
        BodyFlexTable.setWidget(65, 232, passwordTextField);
        BodyFlexTable.setWidget(66, 232, pwdexampleLabel);
        BodyFlexTable.setWidget(85, 231, login);

     VerticalPanel languagePanel=new VerticalPanel();
     
//     languagePanel.add(comboBox);
     BodyVerticalPanel.add(languagePanel);
       BodyVerticalPanel.setCellHorizontalAlignment(languagePanel,
                HasHorizontalAlignment.ALIGN_RIGHT);
        BodyVerticalPanel.add(BodyFlexTable);
        BodyVerticalPanel.setHeight("520px");
        BodyVerticalPanel.setWidth("1260px");

        BodyVerticalPanel.setCellHorizontalAlignment(BodyFlexTable,
            HasHorizontalAlignment.ALIGN_LEFT);
        
        BodyDecoratorPanel.add(BodyVerticalPanel);

        linkPanelBottom.setSpacing(10);
        //        linkPanelBottom.add(home);
        //        linkPanelBottom.add(Feedback);
        linkPanelTop.setSpacing(15);
        linkPanelTop.add(blank);

        detailsPanel.add(labelPanel);
        detailsPanel.add(entryPanel);

        //Add****************************************
        HypMainPanelTop.add(linkPanelTop);
        HypMainPanelTop.setCellHorizontalAlignment(linkPanelTop,
            HasHorizontalAlignment.ALIGN_CENTER);
        HypMainPanelTop.setCellVerticalAlignment(linkPanelTop,
            HasVerticalAlignment.ALIGN_MIDDLE);

        headerPanel.add(headerLabel);
        headerPanel.add(detailsPanel);
        headerPanel.add(HypMainPanelTop);

        HypMainPanelBottom.add(linkPanelBottom);
        HypMainPanelBottom.setCellHorizontalAlignment(linkPanelBottom,
            HasHorizontalAlignment.ALIGN_CENTER);
        HypMainPanelBottom.setCellVerticalAlignment(linkPanelBottom,
            HasVerticalAlignment.ALIGN_MIDDLE);

        bodyPanel.setHeight("475px");
      
    
        bodyPanel.add(BodyDecoratorPanel);
        footerPanel.setHeight("130px");

        footerPanel.add(HypMainPanelBottom);
        footerPanel.add(footerLabel);

       
        mainPanel.add(headerPanel);
        mainPanel.add(bodyPanel);
        mainPanel.add(footerPanel);

        // calling method to get password policy of the system    
        connect.methodPasswordPolicy(new AsyncCallback<CM_passwordPolicyGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(labels.dbError(),
                        arg0.getMessage());
                }

                public void onSuccess(CM_passwordPolicyGetter[] result) {
                	                	
                    minLength = result[0].getMinimum_length();
                    maxlength = result[0].getMaximum_length();
                    expiryPeriod = result[0].getExpiry_period();
                    //setting up informatory text
                    pwdexampleLabel.setText(msg.passwordLength(minLength));
                  
                  }
            });

        login.addListener(new ButtonListenerAdapter() {
                public void onClick(Button login, EventObject e) {
                    boolean flag = false;
                    username = useridTextField.getText();
                    password = passwordTextField.getText();
                    username = username.trim(); //removing trailing spaces
                    password = password.trim();

                    //validating username and password 
                    if ((username.length() == 0) && (password.length() == 0)) {
                        flag = true;
                        MessageBox.alert(msg.failure(),
                        		msg.emptyUsernamePassword());
                    } else if (username.length() == 0) {
                        flag = true;
                        MessageBox.alert(msg.failure(), msg.emptyUsername());
                    } else if (password.length() == 0) {
                        flag = true;
                        MessageBox.alert(msg.failure(), msg.emptyPassword());
                    } else if (password.length() < minLength) {
                        flag = true;
                        MessageBox.alert(msg.failure(),
                            msg.passwordLength(minLength),
                            new MessageBox.AlertCallback() {
                                public void execute() {
                                    passwordTextField.setValue("");
                                    passwordTextField.focus();
                                }
                            });
                    }
                    
                    /*
                     * Temporarily removed code
                     * code to be used if maximum length of password needs to implement
                     */

                    //                    else if (password.length() > maxlength) {
                    //                        flag = true;
                    //                        MessageBox.alert("Alert",
                    //                            "Password exceeds maximum length of "+maxlength+" Characters",
                    //                            new MessageBox.AlertCallback() {
                    //                                public void execute() {
                    //                                    passwordTextField.setValue("");
                    //                                    passwordTextField.focus();
                    //                                }
                    //                            });
                    //                    }
                    if (flag == false) { //if no error exist
                        connectService.methodLogin(username, password,
                            new AsyncCallback<CM_userInfoGetter[]>() {
                                
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(labels.loginError(),
                                        arg0.getMessage());
                                }

                                public void onSuccess(
                                    CM_userInfoGetter[] result) {
                                	
//                                	System.out.println(result[0].getUser_name());
                                
                                    if (result[0].getUser_name()
                                                     .equals("Invalid")) {
                                        MessageBox.alert(msg.failure(),
                                            msg.wrongLogin(),
                                            new MessageBox.AlertCallback() {
                                                public void execute() {
                                                    useridTextField.setValue("");
                                                    passwordTextField.setValue(
                                                        "");
                                                }
                                            });
                                    } else if(result[0].getUser_name()
                                            .equalsIgnoreCase("Inactive")){
                                    	MessageBox.alert(msg.failure(),
                                                labels.userDeactive(),
                                                new MessageBox.AlertCallback() {
                                                    public void execute() {
                                                        useridTextField.setValue("");
                                                        passwordTextField.setValue(
                                                            "");
                                                    }
                                                });
                                    }
                                    	else {                             
                                        //Loop Starts For Getting the User Name And Password and role From The String
                                        for (int i = 0; i < result.length;
                                                i++) {
//                                            uID = result[0].getUser_id();
                                            String username=result[0].getUser_name();
                                            String user_id = result[0].getUser_id();
                                            Password = result[0].getpassword();
                                            role = result[0].getUser_group_name();
//                                            if((result[0].getUser_group_name()).equalsIgnoreCase("Super Admin")){
//                                            role = 1;
//                                            }else
//                                            {
//                                            	role=2;
//                                            }
                                            CourseManagement CM = new CourseManagement(user_id);
                                            CM.type = role;
                                            CM.user_name = username;
                                            mainPanel.setVisible(false);
                                            RootPanel.get().add(CM.init());
                                        }
                                    }
                                }
                            });

 
                    }
                }
            });

        RootPanel.get().add(mainPanel);

        return mainPanel;
    }
}
