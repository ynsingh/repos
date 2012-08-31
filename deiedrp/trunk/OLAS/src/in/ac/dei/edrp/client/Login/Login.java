package in.ac.dei.edrp.client.Login;

import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;
import in.ac.dei.edrp.client.Shared.CourseManagement;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;
import in.ac.dei.edrp.client.applicantAccount.UserLogin;

//import org.apache.log4j.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.TextField;
import in.ac.dei.edrp.client.applicantAccount.MainLoginPage;

public class Login implements EntryPoint,ValueChangeHandler{
    private final CM_LoginConnectSAsync connectService = GWT.create(CM_LoginConnectS.class);
    private final CM_connectAsync connect = GWT.create(CM_connect.class);
//    static final Logger logger = Logger.getLogger(Login.class);
    messages msg = GWT.create(messages.class);
    constants labels = GWT.create(constants.class);
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
    final Label headerLabel = new Label(labels.title());
    final Label headerLabel1 = new Label(
            "An Open Source Initiative of the Ministry of Human Resource & Development");
    Label headerLabel2 = new Label(
            "(Developed under the National Mission of Education through Information & Communication Technology)");
    final Label footerLabel = new Label(labels.footer());
    Label footerLabel1 = new Label("Phone:+91-562-2801545,+91-562-2801226(fax)");
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
    final Button Apply = new Button("Apply for Admission");
    //final Hyperlink Apply = new Hyperlink("Apply for Admission", "");
    String userName,userId,userGroupId,userGroupName,application,urlHome,flag,universityId;
static String oldToken;

    public void onModuleLoad() {
    	 urlHome = com.google.gwt.user.client.Window.Location.getParameter("urlHome");
    	// If the application starts with no history token, redirect to a new
        // 'login' state.
        String initToken = History.getToken();
       System.out.println("token in history "+initToken);        
       if (initToken.length() == 0) {
           History.newItem("login");
           oldToken="loginDone";
         }
       
       /* if(initToken.equalsIgnoreCase("logout")) {
//        	urlHome="http://www.google.com";
        	redirect(urlHome);
        }*/
        /*else if (initToken.length() == 0) {
        	System.out.println("first time entry to login");
            History.newItem("login");
            counter++;
            System.out.println("counter "+counter);
            init();
            System.out.println("flag is "+flag);
            if(flag.equalsIgnoreCase("apply")){
//            	Apply.fireEvent(Apply.get)
            	Apply.fireEvent(Apply.getClickEvent());
            }
            else{
            	login.fireEvent(login.getClickEvent());
            }      
          }*/
        // Add history listener
        History.addValueChangeHandler(this);
        // Now that we've setup our listener, fire the initial history state.
        History.fireCurrentHistoryState();         
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

        RecordDef recordDef = new RecordDef(new FieldDef[] {new StringFieldDef("language"), new StringFieldDef("code"),});

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
        headerLabel1.setStyleName("heading1");
        headerLabel2.setStyleName("heading1");
        footerLabel.setStyleName("heading1");
        footerLabel1.setStyleName("heading1");

        labelPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        entryPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        bodyPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        labelPanel.setSpacing(5);
        entryPanel.setSpacing(5);
        headerPanel.setSpacing(5);
        footerPanel.setSpacing(5);

        userName = com.google.gwt.user.client.Window.Location.getParameter("userName");
        userId = com.google.gwt.user.client.Window.Location.getParameter("userId");
        userGroupId = com.google.gwt.user.client.Window.Location.getParameter("userGroupId");
        userGroupName = com.google.gwt.user.client.Window.Location.getParameter("userGroupName");
       urlHome = com.google.gwt.user.client.Window.Location.getParameter("urlHome");
        application = com.google.gwt.user.client.Window.Location.getParameter("application");
        flag = com.google.gwt.user.client.Window.Location.getParameter("flag");
        universityId = com.google.gwt.user.client.Window.Location.getParameter("universityId");
        universityId = universityId==null?"0001":universityId;
        application = application==null?"ADM":application;
        userId = userId==null?"E00010002000000001":userId;
        
        if(userName==null){
        	userName="admin";
        }
        if(flag==null){
        	flag="noApply";
        }
        useridTextField.setValue(userName);
        useridLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        useridLabel.setStyleName("Heading1");
        passwordLabel.setStyleName("Heading1");
        passwordTextField.setInputType("password");
       passwordTextField.setValue("dayalbagh");
       
        BodyFlexTable.setWidget(60, 230, useridLabel);
        BodyFlexTable.setWidget(60, 232, useridTextField);
        BodyFlexTable.setWidget(61, 232, uidexampleLabel);
        BodyFlexTable.setWidget(65, 230, passwordLabel);
        BodyFlexTable.setWidget(65, 232, passwordTextField);
        BodyFlexTable.setWidget(66, 232, pwdexampleLabel);
        BodyFlexTable.setWidget(85, 231, login);
        BodyFlexTable.setWidget(0, 0, Apply);

        VerticalPanel languagePanel = new VerticalPanel();

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
        headerPanel.add(headerLabel1);
        headerPanel.add(headerLabel2);
        headerPanel.add(detailsPanel);
        headerPanel.add(HypMainPanelTop);

        HypMainPanelBottom.add(linkPanelBottom);
        HypMainPanelBottom.setCellHorizontalAlignment(linkPanelBottom,HasHorizontalAlignment.ALIGN_CENTER);
        HypMainPanelBottom.setCellVerticalAlignment(linkPanelBottom,HasVerticalAlignment.ALIGN_MIDDLE);

        bodyPanel.setHeight("475px");
        bodyPanel.add(BodyDecoratorPanel);
        footerPanel.setHeight("130px");
        footerPanel.add(HypMainPanelBottom);
        footerPanel.add(footerLabel);
        footerPanel.add(footerLabel1);
        mainPanel.add(headerPanel);
        mainPanel.add(bodyPanel);
        mainPanel.add(footerPanel);

        // calling method to get password policy of the system    
       /* connect.methodPasswordPolicy(new AsyncCallback<CM_passwordPolicyGetter[]>() {
                public void onFailure(Throwable arg0) {
                    MessageBox.alert(labels.dbError(), arg0.getMessage());
                }

                public void onSuccess(CM_passwordPolicyGetter[] result) {
                    minLength = result[0].getMinimum_length();
                    maxlength = result[0].getMaximum_length();
                    expiryPeriod = result[0].getExpiry_period();
                    //setting up informatory text
                    pwdexampleLabel.setText(msg.passwordLength(minLength));
                }
            });
            
            */
        Apply.addListener(new ButtonListenerAdapter() {
            public void onClick(Button Apply, EventObject e) {
            	System.out.println("inside ckick event");
            	History.newItem("apply");
            	oldToken="apply";
            	RootPanel.get().clear();
				MainLoginPage ul=new MainLoginPage("E000100001");
				ul.urlHome=urlHome;
				ul.onModuleLoad();
				RootPanel.get().add(ul.mainPanel);	
            }
        });
            	
            	
        /*Apply.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent arg0) {
				RootPanel.get().clear();
				UserLogin ul=new UserLogin("E000100001");
				ul.onModuleLoad();
				RootPanel.get().add(ul.mainPanel);	
			}
		});
*/        
        login.addListener(new ButtonListenerAdapter() {
            public void onClick(Button login, EventObject e) {
            	System.out.println("inside ckick event");
                boolean flag = false;
                String username = userName;
                String user_id = userId;
//                Password = result[0].getpassword();
                role = userGroupName;                                        
                CourseManagement CM = new CourseManagement(user_id);
                CM.type = role;
                CM.user_name = username;
                CM.urlHome = urlHome;
                CM.userInfo.setUser_name(username);
                CM.userInfo.setInstituteID(universityId);
                CM.userInfo.setApplication(application);
                CM.universityCode = universityId;
                CM.application = application;
                CM.userInfo.setUser_id(userId);
                System.out.println("url home "+urlHome);
                mainPanel.setVisible(false);
                RootPanel.get().add(CM.init());

                    //validating username and password 
                    //temporarily removed by nupur to do integration
                    
                   /* 
                    username = useridTextField.getText();
                    password = passwordTextField.getText();
                    username = username.trim(); //removing trailing spaces
                    password = password.trim();
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
                    }*/

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
                    
                    //commented by Nupur to perform Integration
/*                    if (flag == false) { //if no error exist
                        connectService.methodLogin(username, password,
                            new AsyncCallback<CM_userInfoGetter[]>() {
                                public void onFailure(Throwable arg0) {
                                    MessageBox.alert(labels.loginError(),
                                        arg0.getMessage());
                                }

                                public void onSuccess(CM_userInfoGetter[] result) {
                                    //                                	System.out.println(result[0].getUser_name());
                                    if (result[0].getUser_name().equals("Invalid")) {
                                        MessageBox.alert(msg.failure(),msg.wrongLogin(),
                                            new MessageBox.AlertCallback() {
                                                public void execute() {
                                                    useridTextField.setValue("");
                                                    passwordTextField.setValue("");
                                                }
                                            });
                                    } else if (result[0].getUser_name().equalsIgnoreCase("Inactive")) {
                                        MessageBox.alert(msg.failure(),labels.userDeactive(),
                                            new MessageBox.AlertCallback() {
                                                public void execute() {
                                                    useridTextField.setValue("");
                                                    passwordTextField.setValue("");
                                                }
                                            });
                                    } else {
                                        //Loop Starts For Getting the User Name And Password and role From The String
                                        for (int i = 0; i < result.length;i++) {                                           
                                            String username = result[0].getUser_name();
                                            String user_id = result[0].getUser_id();
                                            Password = result[0].getpassword();
                                            role = result[0].getUser_group_name();                                        
                                            CourseManagement CM = new CourseManagement(user_id);
                                            CM.type = role;
                                            CM.user_name = username;
                                            mainPanel.setVisible(false);
                                            RootPanel.get().add(CM.init());
                                        }
                                    }
                                }
                            });
                    }*/
                }
            });
        RootPanel.get().add(mainPanel);
        return mainPanel;
    }
    
    public void onValueChange(ValueChangeEvent event) {
    	GWT.log("in GWT log The current history token is: " + event.getValue(), null);
      if(event.getValue().toString().length()==0 && oldToken.equalsIgnoreCase("loginDone")){
    	   this.onModuleLoad();      
       }
        if (event.getValue().equals("login")){
        	 init();
             System.out.println("flag is "+flag);
             if(flag.equalsIgnoreCase("apply")){
             	Apply.fireEvent(Apply.getClickEvent());
             }
             else{
             	login.fireEvent(login.getClickEvent());
             }  
        }        
        if (event.getValue().equals("logout")){
        	oldToken="logoutDone";
        	redirect(urlHome);
        }    
        System.out.println("The current history token in value change is: " + event.getValue()+"old value "+oldToken);
    }
  //redirect the browser to the given url
    public static native void redirect(String url)/*-{
      	$wnd.location.href = url;
      }-*/;
    
}

