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
package in.ac.dei.edrp.client.Shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.smartgwt.client.widgets.events.ValueChangedEvent;
import com.smartgwt.client.widgets.events.ValueChangedHandler;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuButton;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 *
 * @author Manpreet Kaur
 */
import in.ac.dei.edrp.client.CM_userInfoGetter;
import in.ac.dei.edrp.client.Login.CM_LoginConnectS;
import in.ac.dei.edrp.client.Login.CM_LoginConnectSAsync;
import in.ac.dei.edrp.client.Login.Login;


public class CourseManagement{
    private final CM_LoginConnectSAsync connectService = GWT.create(CM_LoginConnectS.class);
    constants cons = GWT.create(constants.class);
    public String type;
    public String uID;
    final VerticalPanel mainVerticalPanel = new VerticalPanel();
    final VerticalPanel headerVerticalPanel = new VerticalPanel();
    final HorizontalPanel bodyHorizontalPanel = new HorizontalPanel();
    final VerticalPanel footerVerticalPanel = new VerticalPanel();
    final HorizontalPanel detailsHorizontalPanel = new HorizontalPanel();
    final HorizontalPanel HypMainHorizontalPanelTop = new HorizontalPanel();
    final HorizontalPanel HypMainHorizontalPanelBottom = new HorizontalPanel();
    final VerticalPanel labelVerticalPanel = new VerticalPanel();
    final VerticalPanel entryVerticalPanel = new VerticalPanel();
    final VerticalPanel entryVerticalPanel1 = new VerticalPanel();
    Label welcomeLabel = new Label("Welcome :");
    final Label headerLabel = new Label("Online Admission System");
    final Label headerLabel1 = new Label(
            "An Open Source Initiative of the Ministry of Human Resource & Development");
    Label headerLabel2 = new Label(
            "(Developed under the National Mission of Education through Information & Communication Technology)");
    final Label footerLabel = new Label(
            "Dayalbagh Educational Institute,Agra-282110");
    Label footerLabel1 = new Label("Phone:+91-562-2801545,+91-562-2801226(fax)");
    final Hyperlink logOutTopHyperLink = new Hyperlink("Sign out", null);
    final Hyperlink HypBackTopHyperLink = new Hyperlink("Back to module selection ",
            null);
    final Hyperlink settings = new Hyperlink("Settings", null);
    CM_InstituteAdmin IA;
    public String user_name = "";
    public String urlHome;
    public String universityCode;
    public String application;
    public CM_userInfoGetter userInfo = new CM_userInfoGetter();
    
    public CourseManagement(String uID) {
        this.uID = uID;
    }

    public void onModuleLoad() {
    	
        init();
    }

    public VerticalPanel init() {
        final HorizontalPanel canvas = new HorizontalPanel();

        // Sub Menus
        Menu prgcompsubmenu = new Menu();
        Menu prgdegreesubmenu = new Menu();
        Menu prgcossubmenu = new Menu();
        Menu managecossubmenu = new Menu();
        Menu prgbrdsubmenu = new Menu();
        Menu meritsubmenu = new Menu();
        Menu appsubmenu = new Menu();
        Menu testmarkssubmenu = new Menu();
        Menu entitysubmenu = new Menu();
        Menu prgmastersubmenu = new Menu();
        Menu createprgsubmenu = new Menu();
        Menu manageprgsubmenu = new Menu();
        Menu entityprgsubmenu = new Menu();
        Menu prgtermsubmenu = new Menu();
        Menu prgagesubmenu = new Menu();
        Menu prgcomposubmenu = new Menu();
        Menu excelsubmenu = new Menu();
        Menu formsubmenu=new Menu();	//uk
        

        // Main Menus
        Menu formsetup=new Menu();	//uk
        formsetup.setWidth(100);	//uk
        
        Menu prgdoc=new Menu();	//uk
        prgdoc.setWidth(100);	//uk
        
        Menu prgSer=new Menu();	//uk
        prgSer.setWidth(100);	//uk
        
        
        Menu prgsetup = new Menu();
        prgsetup.setWidth(100);

        Menu systemsetup = new Menu();
        systemsetup.setWidth(100);

        Menu appsetup = new Menu();
        appsetup.setWidth(100);

        Menu repsetup = new Menu();
        repsetup.setWidth(100);

        Menu univmenu = new Menu();
        univmenu.setWidth(100);

        Menu prgmenu = new Menu();
        prgmenu.setWidth(100);
	
	  Menu omrMenu=new Menu();		//omr menu
        omrMenu.setWidth(100);
		
        Menu programCenterSubMenu=new Menu();//Arjun Code
                		Menu tieRuleSubMenu=new Menu();//Arjun Code
                 
              
                		final MenuItem createCenter=new MenuItem("Setup Examination Center");
               		final MenuItem manageCenter=new MenuItem("Modify Examination Center");
                		programCenterSubMenu.setItems(createCenter,manageCenter);
               
               		final MenuItem centerMenuItem=new MenuItem("Program Examination Center");//Arjun Code
                      	centerMenuItem.setSubmenu(programCenterSubMenu);//Arjun Code
               
                     	final MenuItem tieRuleMenuItem=new MenuItem("Tie Rule Setup");//Arjun Code
        
		//Devendra May 18
        Menu SubjectSubMenu=new Menu();
        SubjectSubMenu.setWidth(110);     
        //For set up Subject Menus By Devendra May 18
        final MenuItem subjectMenu=new MenuItem("Program Subject");
        final MenuItem setupSubject=new MenuItem("Subject Setup");
        final MenuItem manageSubject=new MenuItem("Manage Subject");
        SubjectSubMenu.setItems(setupSubject,manageSubject);
        subjectMenu.setSubmenu(SubjectSubMenu);
        
		//Add by Devendra May 19
        Menu manageSummarySheet=new Menu();
        manageSummarySheet.setWidth(110);       
        //For Manage Summary sheet menus By Devendra May 18
        final MenuItem manageSummarySheetMenu=new MenuItem("Manage Summary Sheet");
        final MenuItem editSummarySheetMenu=new MenuItem("Edit Summary Sheet");
        final MenuItem deleteSummarySheetMenu=new MenuItem("Delete Summary Sheet");
        manageSummarySheet.setItems(editSummarySheetMenu,deleteSummarySheetMenu);
        manageSummarySheetMenu.setSubmenu(manageSummarySheet);
		
        // form Setup Menu Items
        final MenuItem createform = new MenuItem("Set up Application Form"); //uk
        final MenuItem manageform = new MenuItem("Modify Application Form");	//uk
        formsubmenu.setItems(createform, manageform);		//uk
        
        // Program Setup Menu Items
        MenuItem managedefcos = new MenuItem("Modify COS setup (Default)");
        MenuItem manageindicos = new MenuItem("Modify COS setup (Individual)");
        managecossubmenu.setItems(managedefcos, manageindicos);

        final MenuItem createprg = new MenuItem("Set up Program Components");
        final MenuItem manageprg = new MenuItem("Modify Program Components");
        prgcompsubmenu.setItems(createprg, manageprg);

        final MenuItem createprgbrd = new MenuItem("Set up Program Board");
        final MenuItem manageprgbrd = new MenuItem("Modify Program Board");
        prgbrdsubmenu.setItems(createprgbrd, manageprgbrd);
        
        final MenuItem createexcelmenu = new MenuItem("Define Excel Components");
        final MenuItem manageexcelmenu = new MenuItem("Delete Excel Components");
        excelsubmenu.setItems(createexcelmenu, manageexcelmenu);

        final MenuItem createprgage = new MenuItem("Set up Program Age Eligibility");
        final MenuItem manageprgage = new MenuItem("Modify Program Age Eligibility");
        prgagesubmenu.setItems(createprgage, manageprgage);

        final MenuItem createprgelig = new MenuItem("Set up Program Component Eligibility");
        final MenuItem manageprgelig = new MenuItem("Modify Program Component Eligibility");
		prgcomposubmenu.setItems(createprgelig, manageprgelig);

		final MenuItem createprgdegree = new MenuItem("Program Eligibility(UG&PG)");
		final MenuItem manageprgdegree = new MenuItem("Modify Program Eligibility(UG&PG)");
        prgdegreesubmenu.setItems(createprgdegree, manageprgdegree);

        final MenuItem createprgcos = new MenuItem("Set up Program COS");
        final MenuItem manageprgcos = new MenuItem("Modify Program COS");
        prgcossubmenu.setItems(createprgcos, manageprgcos);
      //Commented By Arjun  manageprgcos.setSubmenu(managecossubmenu);

        final MenuItem prgcomp = new MenuItem("Program Components");
        prgcomp.setSubmenu(prgcompsubmenu);
                
        final MenuItem formcomp = new MenuItem("Application Form");	//uk
        formcomp.setSubmenu(formsubmenu);								//uk
        
        final MenuItem prgDocList= new MenuItem("Program Document"); //uk
        final MenuItem prgSerList= new MenuItem("Program Search"); //uk
        final MenuItem internalSummarySheetList= new MenuItem("SummarySheet Internal"); //uk
        
		 final MenuItem omrList= new MenuItem("OMR"); 		//OMR MenuItem  
        final MenuItem prgdegree = new MenuItem("Program Pre Requiste Examinations(First Degree)");
        prgdegree.setSubmenu(prgdegreesubmenu);

        final MenuItem prgcos = new MenuItem("Program COS");
        prgcos.setSubmenu(prgcossubmenu);

        final MenuItem prgcodes = new MenuItem("Program Paper Codes");

        final MenuItem prgboard = new MenuItem("Program Board");
        prgboard.setSubmenu(prgbrdsubmenu);

        final MenuItem prgage = new MenuItem("Program Age Eligibility");
        prgage.setSubmenu(prgagesubmenu);

        final MenuItem prgcomponentelig = new MenuItem("Program Component Eligibility");
        prgcomponentelig.setSubmenu(prgcomposubmenu);

        // System Setup Menu Items
        final MenuItem createmerit = new MenuItem("Set up Final Merit Components");
        final MenuItem managemerit = new MenuItem("Modify Final Merit Components");
        meritsubmenu.setItems(createmerit, managemerit);

        final MenuItem splwt = new MenuItem("Special Weightage");

        final MenuItem resetcycle = new MenuItem("Reset Admission Cycle");
        final MenuItem frmAuthority = new MenuItem("Form Authority Setup");
        final MenuItem meritmenu = new MenuItem("Final Merit Components");
        meritmenu.setSubmenu(meritsubmenu);

        // Application Menu Items
        final MenuItem appmenu = new MenuItem("Apply for Admission");
        final MenuItem createapp = new MenuItem("Set up Summary Sheet");
        final MenuItem manageapp = new MenuItem("Manage Summary Sheet");
        appsubmenu.setItems(createapp, manageapp);
        appmenu.setSubmenu(appsubmenu);
        
        final MenuItem progregmenu = new MenuItem("Program Application Details");

        final MenuItem computemenu = new MenuItem("Compute Application Marks");

        final MenuItem cutoffmenu = new MenuItem("Cut Off");

        final MenuItem testmarks = new MenuItem("Enter Admission Test Marks");
        final MenuItem createmarks = new MenuItem("Set up Admission Test Marks");
        final MenuItem managemarks = new MenuItem("Modify Admission Test Marks");
        final MenuItem uploadfile = new MenuItem("upload/download file for adding Test Marks");
		
		//Add by Devendra for upload component marks
        final MenuItem uploadFileForAddMarksInFinalMarks = new MenuItem("Upload File for adding Component's Marks");
		//Add by Devendra for upload component marks May 8th
        final MenuItem finalMeritListProcess = new MenuItem("Final Merit List Process");
		//Add by Devendra for upload component marks May 8th
        final MenuItem importStudentMarks = new MenuItem("Import Students Marks");
		
		 //updated by debvendra
        testmarkssubmenu.setItems(createmarks, managemarks, uploadfile,uploadFileForAddMarksInFinalMarks,finalMeritListProcess,importStudentMarks);
        testmarks.setSubmenu(testmarkssubmenu);

        final MenuItem testnumber = new MenuItem("Generate Test Number");

        // Reports Menu Items
        final MenuItem excelcomponents = new MenuItem("Define Excel Components");
        excelcomponents.setSubmenu(excelsubmenu);
        
        final MenuItem calloutmenu = new MenuItem("Call List without Test Number");

        final MenuItem callwithmenu = new MenuItem("Call List with Test Number");

        final MenuItem finalmenu = new MenuItem("Final Merit List");

        // University(Course Registration) Menu Items
        final MenuItem entitymaster = new MenuItem("Entity Master");
        final MenuItem createentity = new MenuItem("Set up Entity");
        final MenuItem manageentity = new MenuItem("Modify Entity");
        entitysubmenu.setItems(createentity, manageentity);
        entitymaster.setSubmenu(entitysubmenu);

        // Programs(Course Registration) Menu Items
        final MenuItem prgmaster = new MenuItem("Program Master");
        final MenuItem createprgmaster = new MenuItem("Set up Program");
        final MenuItem manageprgmaster = new MenuItem("Modify Program");
        prgmastersubmenu.setItems(createprgmaster, manageprgmaster);
        prgmaster.setSubmenu(prgmastersubmenu);

        MenuItem newprg = new MenuItem("New Program Set up");
        MenuItem addstdate = new MenuItem("Add another Start Date");
        MenuItem addbranch = new MenuItem("Add another Branch");
        MenuItem addspec = new MenuItem("Add another Specialization");
        MenuItem addreser = new MenuItem("Add Reservation Category");
        createprgsubmenu.setItems(newprg, addstdate, addbranch, addspec,addreser);
        createprgmaster.setSubmenu(createprgsubmenu);

        MenuItem basicinfo = new MenuItem("Basic Information");
        MenuItem duration = new MenuItem("Duration");
        MenuItem branchinfo = new MenuItem("Branch Information");
        MenuItem specinfo = new MenuItem("Specialization Information");
        MenuItem reserinfo = new MenuItem("Seat Reservation");
        manageprgsubmenu.setItems(basicinfo, duration, branchinfo, specinfo,reserinfo);
        manageprgmaster.setSubmenu(manageprgsubmenu);

        final MenuItem entityprg = new MenuItem("Entity Programs");
        final MenuItem createentityprg = new MenuItem("Assign Programs to Entities");
        final MenuItem manageentityprg = new MenuItem("Modify Programs in Entities");
        entityprgsubmenu.setItems(createentityprg, manageentityprg);
        entityprg.setSubmenu(entityprgsubmenu);

        final MenuItem prgterm = new MenuItem("Program Term Details");
        final MenuItem createprgterm = new MenuItem("Setup Program Term Details");
        final MenuItem manageprgterm = new MenuItem("Modify Program Term Details");
        prgtermsubmenu.setItems(createprgterm, manageprgterm);
        prgterm.setSubmenu(prgtermsubmenu);

        // Main Menu Items
        final MenuButton home = new MenuButton("Home");
        home.setWidth(80);

        final MenuButton admission = new MenuButton("Admission");
        admission.setWidth(80);

//        final MenuButton coursemenu = new MenuButton("Course Registration");
//        coursemenu.setWidth(140);

        // Admission Menu Items
        final MenuButton programmenu = new MenuButton("Program Setup", prgsetup);
        programmenu.setWidth(110);
        
        final MenuButton omrMenuButton = new MenuButton("OMR Setup", omrMenu);		//OMR MenuButton
        omrMenuButton.setWidth(110);
        
        
        
        final MenuButton formmenu = new MenuButton("Application Form Setup", formsetup);		//uk
        formmenu.setWidth(110);														//uk	
              
        final MenuButton systemmenu = new MenuButton("System Setup", systemsetup);
        systemmenu.setWidth(100);

        final MenuButton applicationmenu = new MenuButton("Application",appsetup);

        final MenuButton reportsmenu = new MenuButton("Reports", repsetup);

        // Course Registration Menu Items
        final MenuButton universitymenu = new MenuButton("University Setup",univmenu);
        universitymenu.setWidth(110);

        final MenuButton uniprgmenu = new MenuButton("Programs", prgmenu);
        uniprgmenu.setWidth(100);

        prgsetup.setItems(prgcomp, prgdegree, prgcodes, prgcos, prgage,centerMenuItem, formcomp ,prgDocList,internalSummarySheetList,prgcomponentelig, prgboard,subjectMenu); // added uk
        
        formsetup.setItems(formcomp);		//uk

        systemsetup.setItems(meritmenu,frmAuthority,tieRuleMenuItem, splwt, resetcycle);

        appsetup.setItems(appmenu,manageSummarySheetMenu, progregmenu,computemenu, cutoffmenu, testmarks,testnumber);
		 omrMenu.setItems(omrList);	//OMR

        repsetup.setItems(excelcomponents,calloutmenu, callwithmenu, finalmenu);

        univmenu.setItems(entitymaster);

        prgmenu.setItems(prgmaster, entityprg, prgterm);

        canvas.add(home);
        canvas.add(admission);
        //        canvas.add(coursemenu);

        // menu items disabled initially
        admission.setDisabled(true);
//        coursemenu.setDisabled(true);
        programmenu.setDisabled(true);
        formmenu.setDisabled(true);			//uk
        
        applicationmenu.setDisabled(true);
        systemmenu.setDisabled(true);
        reportsmenu.setDisabled(true);
        universitymenu.setDisabled(true);
        uniprgmenu.setDisabled(true);
        prgcomp.setEnabled(false);
        
        formcomp.setEnabled(false);			//uk
        
        prgdegree.setEnabled(false);
        prgcos.setEnabled(false);
        prgboard.setEnabled(false);
        prgage.setEnabled(false);
        prgcomponentelig.setEnabled(false);
        prgcodes.setEnabled(false);
        meritmenu.setEnabled(false);
        splwt.setEnabled(false);
        resetcycle.setEnabled(false);
        appmenu.setEnabled(false);
        progregmenu.setEnabled(false);
        computemenu.setEnabled(false);
        cutoffmenu.setEnabled(false);
        testmarks.setEnabled(false);
        testnumber.setEnabled(false);
        excelcomponents.setEnabled(false);
        calloutmenu.setEnabled(false);
        callwithmenu.setEnabled(false);
        finalmenu.setEnabled(false);
        entitymaster.setEnabled(false);
        prgmaster.setEnabled(false);
        entityprg.setEnabled(false);
        prgterm.setEnabled(false);

        // sub menu items disabled initially
        createform.setEnabled(false);	//uk
        manageform.setEnabled(false);	//uk
        
        createprg.setEnabled(false);
        manageprg.setEnabled(false);
        createprgdegree.setEnabled(false);
        manageprgdegree.setEnabled(false);
        createprgcos.setEnabled(false);
        manageprgcos.setEnabled(false);
        createprgage.setEnabled(false);
        manageprgage.setEnabled(false);
        createprgelig.setEnabled(false);
        manageprgelig.setEnabled(false);
        createprgbrd.setEnabled(false);
        manageprgbrd.setEnabled(false);
        createmerit.setEnabled(false);
        managemerit.setEnabled(false);
        createapp.setEnabled(false);
        manageapp.setEnabled(false);
        createmarks.setEnabled(false);
        managemarks.setEnabled(false);
        uploadfile.setEnabled(false);
		uploadFileForAddMarksInFinalMarks.setEnabled(false);//Add by Devendra
		finalMeritListProcess.setEnabled(false);//Add by Devendra May 8th
		importStudentMarks.setEnabled(false);//Add by Devendra May 10
        createentity.setEnabled(false);
        manageentity.setEnabled(false);
        createentityprg.setEnabled(false);
        manageentityprg.setEnabled(false);
        createprgmaster.setEnabled(false);
        manageprgmaster.setEnabled(false);
        createprgterm.setEnabled(false);
        manageprgterm.setEnabled(false);
		subjectMenu.setEnabled(false);//Add by Devendra June 11 
        manageSummarySheetMenu.setEnabled(false);//Add by Devendra June 11
        centerMenuItem.setEnabled(false);//Added by Arjun
        tieRuleMenuItem.setEnabled(false);//Added by Arjun
        createCenter.setEnabled(false);//Added by Arjun
        manageCenter.setEnabled(false);//Added by Arjun
        frmAuthority.setEnabled(false);//Added by Arjun
		prgDocList.setEnabled(false);//Added by Upasana
		internalSummarySheetList.setEnabled(false);//Added by Upasana
		omrList.setEnabled(false);//Added by Upasana
        // Menu Selections on the basis of authorities
        /*
         * This method call has been commented by Nupur
         * since queries have been changed
         */
        /*
        connectService.getPageAuthority(user_name,new AsyncCallback<CM_userInfoGetter[]>() {
                public void onFailure(Throwable arg0) 
                {
                }

                public void onSuccess(CM_userInfoGetter[] result) {
                	System.out.println("hi "+user_name+result.length);
                    for (int i = 0; i < result.length; i++) {
                    	
                        if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Components")) && (result[i].getAuthority().equalsIgnoreCase("1"))) 
                        {
                            admission.setDisabled(false);
                            programmenu.setDisabled(false);
                            prgcomp.setEnabled(true);
                            createprg.setEnabled(true);
                            manageprg.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Components")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createprg.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Components")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageprg.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }
                        
                        if ((result[i].getMenu_item_name().equalsIgnoreCase("Application Form")) && (result[i].getAuthority().equalsIgnoreCase("1"))) 
                        {
                            admission.setDisabled(false);
                            programmenu.setDisabled(false);
                            formcomp.setEnabled(true);
                            createform.setEnabled(true);
                            manageform.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Components")) &&
                                                    (result[i].getAuthority().charAt(0) == '0')) {
                                                createprg.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Components")) && 
                                            		((result[i].getAuthority().charAt(1) == '0') &&
                                            				(result[i].getAuthority().charAt(2) == '0') &&
                                            					(result[i].getAuthority().charAt(3) == '0'))) {
                                                manageprg.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Program Pre Requiste Examinations(First Degree)")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            programmenu.setDisabled(false);
                            prgdegree.setEnabled(true);
                            createprgdegree.setEnabled(true);
                            manageprgdegree.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Pre Requiste Examinations(First Degree)")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createprgdegree.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Pre Requiste Examinations(First Degree)")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageprgdegree.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Program Paper Codes")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            programmenu.setDisabled(false);
                            prgcodes.setEnabled(true);
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Program COS")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            programmenu.setDisabled(false);
                            prgcos.setEnabled(true);
                            createprgcos.setEnabled(true);
                            manageprgcos.setEnabled(true);

                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program COS")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createprgcos.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program COS")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageprgcos.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Program Age Eligibility")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            programmenu.setDisabled(false);
                            prgage.setEnabled(true);
                            createprgage.setEnabled(true);
                            manageprgage.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Age Eligibility")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createprgage.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Age Eligibility")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageprgage.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Program Component Eligibility")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            programmenu.setDisabled(false);
                            prgcomponentelig.setEnabled(true);
                            createprgelig.setEnabled(true);
                            manageprgelig.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Component Eligibility")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createprgelig.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Component Eligibility")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageprgelig.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Program Board")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            programmenu.setDisabled(false);
                            prgboard.setEnabled(true);
                            createprgbrd.setEnabled(true);
                            manageprgbrd.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Board")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createprgbrd.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Board")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageprgbrd.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Final Merit Components")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            systemmenu.setDisabled(false);
                            meritmenu.setEnabled(true);
                            createmerit.setEnabled(true);
                            managemerit.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Final Merit Components")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createmerit.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Final Merit Components")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                managemerit.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Special Weightage")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            systemmenu.setDisabled(false);
                            splwt.setEnabled(true);
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Reset Admission Cycle")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            systemmenu.setDisabled(false);
                            resetcycle.setEnabled(true);
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Apply for Admission")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            applicationmenu.setDisabled(false);
                            appmenu.setEnabled(true);
                            createapp.setEnabled(true);
                            manageapp.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Apply for Admission")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createapp.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Apply for Admission")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageapp.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }
                        
                        if ((result[i].getMenu_item_name()
                                .equalsIgnoreCase("Program Application Details")) &&
                      (result[i].getAuthority().equalsIgnoreCase("1"))) {
                  admission.setDisabled(false);
                  applicationmenu.setDisabled(false);
                  progregmenu.setEnabled(true);
              }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Compute Application Marks")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            applicationmenu.setDisabled(false);
                            computemenu.setEnabled(true);
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Cut off")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            applicationmenu.setDisabled(false);
                            cutoffmenu.setEnabled(true);
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Enter Admission Test Marks")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            applicationmenu.setDisabled(false);
                            testmarks.setEnabled(true);
                            createmarks.setEnabled(true);
                            managemarks.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Enter Admission Test Marks")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createmarks.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Enter Admission Test Marks")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                managemarks.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }
                        
                        if ((result[i].getMenu_item_name()
                                .equalsIgnoreCase("Define Excel Components")) &&
                      (result[i].getAuthority().equalsIgnoreCase("1"))) {
                  admission.setDisabled(false);
                  reportsmenu.setDisabled(false);
                  excelcomponents.setEnabled(true);
                  createexcelmenu.setEnabled(true);
                  manageexcelmenu.setEnabled(true);
                  connectService.getPrimaryAuthorities(uID,
                      new AsyncCallback<CM_userInfoGetter[]>() {
                          public void onFailure(Throwable arg0) {
                          }

                          public void onSuccess(
                              CM_userInfoGetter[] result) {
                              for (int i = 0; i < result.length;
                                      i++) {
                                  if ((result[i].getMenu_item_name()
                                                    .equalsIgnoreCase("Define Excel Components")) &&
                                          (result[i].getAuthority()
                                                        .charAt(0) == '0')) {
                                	  createexcelmenu.setEnabled(false);
                                  }

                                  if ((result[i].getMenu_item_name()
                                                    .equalsIgnoreCase("Define Excel Components")) &&
                                          ((result[i].getAuthority()
                                                         .charAt(1) == '0') &&
                                          (result[i].getAuthority()
                                                        .charAt(2) == '0') &&
                                          (result[i].getAuthority()
                                                        .charAt(3) == '0'))) {
                                	  manageexcelmenu.setEnabled(false);
                                  }
                              }
                          }
                      });
              }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Generate Test Numbers")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            applicationmenu.setDisabled(false);
                            testnumber.setEnabled(true);
                        }
                        
                        
                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Call List without Test Number")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            reportsmenu.setDisabled(false);
                            calloutmenu.setEnabled(true);
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("upload/download file for adding Test Marks")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            applicationmenu.setDisabled(false);
                            testmarks.setEnabled(true);
                            uploadfile.setEnabled(true);
                        }
						 //Add by Devendra
                        if ((result[i].getMenu_item_name()
                                .equalsIgnoreCase("Upload File for adding Component's Marks")) &&
		                      (result[i].getAuthority().equalsIgnoreCase("1"))) {
		                  admission.setDisabled(false);
		                  applicationmenu.setDisabled(false);
		                  testmarks.setEnabled(true);
		                  uploadFileForAddMarksInFinalMarks.setEnabled(true);
		              }
					   //Add by Devendra May 8th
                       if ((result[i].getMenu_item_name()
                                .equalsIgnoreCase("Final Merit List Process")) &&
		                      (result[i].getAuthority().equalsIgnoreCase("1"))) {
		                  admission.setDisabled(false);
		                  applicationmenu.setDisabled(false);
		                  testmarks.setEnabled(true);
		                  finalMeritListProcess.setEnabled(true);
		              }
					   //Add by Devendra May 10th
                       if ((result[i].getMenu_item_name()
                                .equalsIgnoreCase("Import Students Marks")) &&
		                      (result[i].getAuthority().equalsIgnoreCase("1"))) {
		                  admission.setDisabled(false);
		                  applicationmenu.setDisabled(false);
		                  testmarks.setEnabled(true);
		                  importStudentMarks.setEnabled(true);
		              }
                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Call List with Test Number")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            reportsmenu.setDisabled(false);
                            callwithmenu.setEnabled(true);
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Final Merit List")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
                            admission.setDisabled(false);
                            reportsmenu.setDisabled(false);
                            finalmenu.setEnabled(true);
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Entity Master")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
//                            coursemenu.setDisabled(false);
                            universitymenu.setDisabled(false);
                            entitymaster.setEnabled(true);
                            createentity.setEnabled(true);
                            manageentity.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Entity Master")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createentity.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Entity Master")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageentity.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Program Master")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
//                            coursemenu.setDisabled(false);
                            uniprgmenu.setDisabled(false);
                            prgmaster.setEnabled(true);
                            createprgmaster.setEnabled(true);
                            manageprgmaster.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Master")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createprgmaster.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Master")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageprgmaster.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Entity Programs")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
//                            coursemenu.setDisabled(false);
                            uniprgmenu.setDisabled(false);
                            entityprg.setEnabled(true);
                            createentityprg.setEnabled(true);
                            manageentityprg.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Entity Programs")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createentityprg.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Entity Programs")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageentityprg.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }

                        if ((result[i].getMenu_item_name()
                                          .equalsIgnoreCase("Program Term Details")) &&
                                (result[i].getAuthority().equalsIgnoreCase("1"))) {
//                            coursemenu.setDisabled(false);
                            uniprgmenu.setDisabled(false);
                            prgterm.setEnabled(true);
                            createprgterm.setEnabled(true);
                            manageprgterm.setEnabled(true);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Term Details")) &&
                                                    (result[i].getAuthority()
                                                                  .charAt(0) == '0')) {
                                                createprgterm.setEnabled(false);
                                            }

                                            if ((result[i].getMenu_item_name()
                                                              .equalsIgnoreCase("Program Term Details")) &&
                                                    ((result[i].getAuthority()
                                                                   .charAt(1) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(2) == '0') &&
                                                    (result[i].getAuthority()
                                                                  .charAt(3) == '0'))) {
                                                manageprgterm.setEnabled(false);
                                            }
                                        }
                                    }
                                });
                        }
                    }
                }
            });*/
        
        /**
         * This new method written by Nupur Dixit
         * to avoid code clatch from other classes
         * older method is kept untouched and new method is 
         * appended with the new word
         * @author NUPUR
         * @parameter variable of CM_userInfoGetter having userid,application and instituteId to do 
         * validation in the user_info table
         */
        System.out.println("user info "+userInfo);
        System.out.println(userInfo.getUser_id()+userInfo.getUser_name()+
        		userInfo.getInstituteID()+userInfo.getApplication());
        connectService.getPageAuthorityNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>(){
        	public void onFailure(Throwable arg0) {
        	}

        	public void onSuccess(CM_userInfoGetter[] result) {
        		System.out.println("hi "+result.length);
        		for (int i = 0; i < result.length; i++) {                    	
        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Components")) && (result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				prgcomp.setEnabled(true);
        				createprg.setEnabled(true);
        				manageprg.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Components")) &&(result[i].getAuthority().charAt(0) == '0')) {
        								createprg.setEnabled(false);
        							}
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Components")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageprg.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Application Form")) && (result[i].getAuthority().equalsIgnoreCase("1"))) 
        			{
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
                            connectService.getPrimaryAuthorities(uID,
                                new AsyncCallback<CM_userInfoGetter[]>() {
                                    public void onFailure(Throwable arg0) {
                                    }

                                    public void onSuccess(
                                        CM_userInfoGetter[] result) {
                                        for (int i = 0; i < result.length;
                                                i++) {
                                            if ((result[i].getMenu_item_name().equalsIgnoreCase("Application Form")) &&
                                                    (result[i].getAuthority().charAt(0) == '1')) {
                                                /*createprg.setEnabled(false);*/
        										formcomp.setEnabled(true);
						        				createform.setEnabled(true);
											}

                                            if ((result[i].getMenu_item_name().equalsIgnoreCase("Application Form")) && 
                                            		((result[i].getAuthority().charAt(1) == '1') &&
                                            				(result[i].getAuthority().charAt(2) == '1') &&
                                            					(result[i].getAuthority().charAt(3) == '1'))) {
                                            	formcomp.setEnabled(true);
						        				manageform.setEnabled(true);
        									}
										 }
                                    }
                                });
                        }

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Pre Requiste Examinations(First Degree)")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				prgdegree.setEnabled(true);
        				createprgdegree.setEnabled(true);
        				manageprgdegree.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Pre Requiste Examinations(First Degree)")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createprgdegree.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Pre Requiste Examinations(First Degree)")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageprgdegree.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Paper Codes")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				prgcodes.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program COS")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				prgcos.setEnabled(true);
        				createprgcos.setEnabled(true);
        				manageprgcos.setEnabled(true);

        				connectService.getPrimaryAuthoritiesNew(userInfo,
        						new AsyncCallback<CM_userInfoGetter[]>() {
        							public void onFailure(Throwable arg0) {
        							}

        							public void onSuccess(CM_userInfoGetter[] result) {
        								for (int i = 0; i < result.length;i++) {
        									if ((result[i].getMenu_item_name().equalsIgnoreCase("Program COS")) &&
        											(result[i].getAuthority().charAt(0) == '0')) {
        										createprgcos.setEnabled(false);
        									}

        									if ((result[i].getMenu_item_name().equalsIgnoreCase("Program COS")) &&
        											((result[i].getAuthority().charAt(1) == '0') &&
        													(result[i].getAuthority().charAt(2) == '0') &&
        													(result[i].getAuthority().charAt(3) == '0'))) {
        										manageprgcos.setEnabled(false);
        									}
        								}
        							}
        						});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Age Eligibility")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				prgage.setEnabled(true);
        				createprgage.setEnabled(true);
        				manageprgage.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Age Eligibility")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createprgage.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Age Eligibility")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageprgage.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Component Eligibility")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				prgcomponentelig.setEnabled(true);
        				createprgelig.setEnabled(true);
        				manageprgelig.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Component Eligibility")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createprgelig.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Component Eligibility")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageprgelig.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Board")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				prgboard.setEnabled(true);
        				createprgbrd.setEnabled(true);
        				manageprgbrd.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Board")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createprgbrd.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Board")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageprgbrd.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Final Merit Components")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				systemmenu.setDisabled(false);
        				meritmenu.setEnabled(true);
        				createmerit.setEnabled(true);
        				managemerit.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Final Merit Components")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createmerit.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Final Merit Components")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								managemerit.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Special Weightage")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				systemmenu.setDisabled(false);
        				splwt.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Reset Admission Cycle")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				systemmenu.setDisabled(false);
        				resetcycle.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Apply for Admission")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				appmenu.setEnabled(true);
        				createapp.setEnabled(true);
        				manageapp.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Apply for Admission")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createapp.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Apply for Admission")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageapp.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Application Details")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				progregmenu.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Compute Application Marks")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				computemenu.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Cut off")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				cutoffmenu.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Enter Admission Test Marks")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				testmarks.setEnabled(true);
        				createmarks.setEnabled(true);
        				managemarks.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Enter Admission Test Marks")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createmarks.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Enter Admission Test Marks")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								managemarks.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Define Excel Components")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				reportsmenu.setDisabled(false);
        				excelcomponents.setEnabled(true);
        				createexcelmenu.setEnabled(true);
        				manageexcelmenu.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Define Excel Components")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createexcelmenu.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Define Excel Components")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageexcelmenu.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Generate Test Numbers")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				testnumber.setEnabled(true);
        			}


        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Call List without Test Number")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				reportsmenu.setDisabled(false);
        				calloutmenu.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("upload/download file for adding Test Marks")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				testmarks.setEnabled(true);
        				uploadfile.setEnabled(true);
        			}
        			//Add by Devendra
        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Upload File for adding Component's Marks")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				testmarks.setEnabled(true);
        				uploadFileForAddMarksInFinalMarks.setEnabled(true);
        			}
        			//Add by Devendra May 8th
        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Final Merit List Process")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				applicationmenu.setDisabled(false);
        				testmarks.setEnabled(true);
        				finalMeritListProcess.setEnabled(true);
        			}
					//Add by Devendra JUNE 11
                       if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Subject")) && (result[i].getAuthority().equalsIgnoreCase("1"))) {                    	   
                    	   admission.setDisabled(false);
                    	   applicationmenu.setDisabled(false);
                    	   subjectMenu.setEnabled(true);
                    	   setupSubject.setEnabled(false);
                    	   manageSubject.setEnabled(false);
                    	   connectService.getPrimaryAuthorities(uID,
		                         new AsyncCallback<CM_userInfoGetter[]>() {
		                             public void onFailure(Throwable arg0) {
		                             }
		                             public void onSuccess(
		                                 CM_userInfoGetter[] result) {
		                                 for (int i = 0; i < result.length; i++) {
		                                     if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Subject")) && (result[i].getAuthority().charAt(0) == '1')) {
		                		                 setupSubject.setEnabled(true);
		                                     }

		                                      if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Subject")) &&		                                             
		                                             (result[i].getAuthority().charAt(2) == '1') &&
		                                             (result[i].getAuthority().charAt(3) == '1')) {
													manageSubject.setEnabled(true);
		                                     }
		                                 }
		                             }
		                         });
		              }  
                       
                       //Add by Devendra JUNE 11
                       if ((result[i].getMenu_item_name().equalsIgnoreCase("Manage Summary Sheet")) && (result[i].getAuthority().equalsIgnoreCase("1"))) {                    	  
                    	   admission.setDisabled(false);
                    	   applicationmenu.setDisabled(false);
                    	   manageSummarySheetMenu.setEnabled(true);
                    	   editSummarySheetMenu.setEnabled(false);
                    	   deleteSummarySheetMenu.setEnabled(false);
                    	   connectService.getPrimaryAuthorities(uID,
		                         new AsyncCallback<CM_userInfoGetter[]>() {
		                             public void onFailure(Throwable arg0) {
		                             }
		                             public void onSuccess(
		                                 CM_userInfoGetter[] result) {
		                                 for (int i = 0; i < result.length; i++) {
		                                     if ((result[i].getMenu_item_name().equalsIgnoreCase("Manage Summary Sheet")) && (result[i].getAuthority().charAt(2) == '1')) {
		                                    	 editSummarySheetMenu.setEnabled(true);
		                                     }

		                                     if ((result[i].getMenu_item_name().equalsIgnoreCase("Manage Summary Sheet")) && ((result[i].getAuthority().charAt(3) == '1'))) {
		                                    	 deleteSummarySheetMenu.setEnabled(true);
		                                     }
		                                 }
		                             }
		                         });
		              }  
					  
					 //Add by Devendra May 10th
                       if ((result[i].getMenu_item_name()
                                .equalsIgnoreCase("Import Students Marks")) &&
		                      (result[i].getAuthority().equalsIgnoreCase("1"))) {
		                  admission.setDisabled(false);
		                  applicationmenu.setDisabled(false);
		                  testmarks.setEnabled(true);
		                  importStudentMarks.setEnabled(true);
		              }
					
                       if((result[i].getMenu_item_name().trim().equalsIgnoreCase("Tie Rule Setup"))&&(result[i].getAuthority().equalsIgnoreCase("1")))
                       {
                    	   admission.setDisabled(false);
                    	   systemmenu.setDisabled(false);
                    	   tieRuleMenuItem.setEnabled(true);
                       }
                       if((result[i].getMenu_item_name().trim().equalsIgnoreCase("Program Examination Center"))&&(result[i].getAuthority().equalsIgnoreCase("1")))
                       {
                    	   admission.setDisabled(false);
                    	   programmenu.setDisabled(false);
                    	   centerMenuItem.setEnabled(true);
                       }
                       if((result[i].getMenu_item_name().trim().equalsIgnoreCase("Setup Examination Center"))&&(result[i].getAuthority().equalsIgnoreCase("1")))
                       {
                    	   admission.setDisabled(false);
                    	   programmenu.setDisabled(false);
                    	   createCenter.setEnabled(true);
                       }
                       if((result[i].getMenu_item_name().trim().equalsIgnoreCase("Modify Examination Center"))&&(result[i].getAuthority().equalsIgnoreCase("1")))
                       {
                    	   admission.setDisabled(false);
                    	   programmenu.setDisabled(false);
                    	   manageCenter.setEnabled(true);
                       }
                       if((result[i].getMenu_item_name().trim().equalsIgnoreCase("Form Authority Setup"))&&(result[i].getAuthority().equalsIgnoreCase("1")))
                       {
                    	   admission.setDisabled(false);
                    	   systemmenu.setDisabled(false);
                    	   frmAuthority.setEnabled(true);
                       }
					   //Added by Upasana
					   if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Document Setup")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				prgDocList.setEnabled(true);
        				}
						   //Added by Upasana
					   if ((result[i].getMenu_item_name().equalsIgnoreCase("SummarySheet Internal")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				programmenu.setDisabled(false);
        				internalSummarySheetList.setEnabled(true);
        				}
						//Added by Upasana
					   if ((result[i].getMenu_item_name().equalsIgnoreCase("OMR Setup")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				omrMenuButton.setDisabled(false);
        				omrList.setEnabled(true);
        				}
        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Call List with Test Number")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				reportsmenu.setDisabled(false);
        				callwithmenu.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Final Merit List")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				admission.setDisabled(false);
        				reportsmenu.setDisabled(false);
        				finalmenu.setEnabled(true);
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Entity Master")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				//                            coursemenu.setDisabled(false);
        				universitymenu.setDisabled(false);
        				entitymaster.setEnabled(true);
        				createentity.setEnabled(true);
        				manageentity.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,
        						new AsyncCallback<CM_userInfoGetter[]>() {
        							public void onFailure(Throwable arg0) {
        							}

        							public void onSuccess(CM_userInfoGetter[] result) {
        								for (int i = 0; i < result.length;i++) {
        									if ((result[i].getMenu_item_name().equalsIgnoreCase("Entity Master")) &&
        											(result[i].getAuthority().charAt(0) == '0')) {
        										createentity.setEnabled(false);
        									}

        									if ((result[i].getMenu_item_name().equalsIgnoreCase("Entity Master")) &&
        											((result[i].getAuthority().charAt(1) == '0') &&
        													(result[i].getAuthority().charAt(2) == '0') &&
        													(result[i].getAuthority().charAt(3) == '0'))) {
        										manageentity.setEnabled(false);
        									}
        								}
        							}
        						});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Master")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				//                            coursemenu.setDisabled(false);
        				uniprgmenu.setDisabled(false);
        				prgmaster.setEnabled(true);
        				createprgmaster.setEnabled(true);
        				manageprgmaster.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Master")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createprgmaster.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Master")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageprgmaster.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Entity Programs")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				//                            coursemenu.setDisabled(false);
        				uniprgmenu.setDisabled(false);
        				entityprg.setEnabled(true);
        				createentityprg.setEnabled(true);
        				manageentityprg.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Entity Programs")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createentityprg.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Entity Programs")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageentityprg.setEnabled(false);
        							}
        						}
        					}
        				});
        			}

        			if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Term Details")) &&
        					(result[i].getAuthority().equalsIgnoreCase("1"))) {
        				//                            coursemenu.setDisabled(false);
        				uniprgmenu.setDisabled(false);
        				prgterm.setEnabled(true);
        				createprgterm.setEnabled(true);
        				manageprgterm.setEnabled(true);
        				connectService.getPrimaryAuthoritiesNew(userInfo,new AsyncCallback<CM_userInfoGetter[]>() {
        					public void onFailure(Throwable arg0) {
        					}

        					public void onSuccess(CM_userInfoGetter[] result) {
        						for (int i = 0; i < result.length;i++) {
        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Term Details")) &&
        									(result[i].getAuthority().charAt(0) == '0')) {
        								createprgterm.setEnabled(false);
        							}

        							if ((result[i].getMenu_item_name().equalsIgnoreCase("Program Term Details")) &&
        									((result[i].getAuthority().charAt(1) == '0') &&
        											(result[i].getAuthority().charAt(2) == '0') &&
        											(result[i].getAuthority().charAt(3) == '0'))) {
        								manageprgterm.setEnabled(false);
        							}
        						}
        					}
        				});
        			}
        		}
        	}
        });

        // click handlers of menu items
        admission.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                public void onClick(
                    com.smartgwt.client.widgets.events.ClickEvent event) {
                    canvas.remove(admission);
//                    canvas.remove(coursemenu);

                    canvas.add(programmenu);
                    canvas.add(systemmenu);
                    canvas.add(applicationmenu);
                    canvas.add(reportsmenu);
                    canvas.add(omrMenuButton);
                }
            });

        home.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                public void onClick(
                    com.smartgwt.client.widgets.events.ClickEvent event) {
                    canvas.clear();

                    canvas.add(home);
                    canvas.add(admission);
                    //                    canvas.add(coursemenu);
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                    IA.init();
                }
            });

//        coursemenu.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
//                public void onClick(
//                    com.smartgwt.client.widgets.events.ClickEvent event) {
//                    canvas.remove(admission);
//                    canvas.remove(coursemenu);
//
//                    canvas.add(universitymenu);
//                    canvas.add(uniprgmenu);
//                }
//            });

        createprg.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.prgc.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.prgc.programcomponent();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
        
        /*--------uk--------*/
        createform.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.appform.panel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.appform.applicationForm();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
        
        manageform.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.manageappform.verPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.manageappform.manageapplicationform();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
        
        prgDocList.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.progDocumnet.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.progDocumnet.programDocument();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
        
        prgSerList.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.progSearch.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.progSearch.searchProgramSetup();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
        
        //OMR 
        omrList.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler(){

			@Override
			public void onClick(MenuItemClickEvent event) {
				//Window.Location.assign("http://localhost:8080/OMR/");
				//Window.Location.assign(cons.omrUrl());
				Window.open(cons.omrUrl(), "_blank", "");
				
			}
        	
        });
        
        /*accountApplicant.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.accountSetup.verticalPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.accountSetup.onModuleLoad();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });*/
        
        internalSummarySheetList.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.formSearch.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.formSearch.searchFormSetup();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
        
        
        /*--------uk--------*/

        manageprg.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.mpgc.FacultyPanelRight);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.mpgc.methodManageprgcomponents();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createprgdegree.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.cfd.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.cfd.finaldegree();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        manageprgdegree.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.cmfd.FacultyPanelRight);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.cmfd.methodManagefirstdegree();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createprgcos.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.call.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.call.callcutoff();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        managedefcos.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.mcall.FacultyPanelRight);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.mcall.methodmanagedefaultlist();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        manageprgcos.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.mcall.FacultyPanelRight);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.mcall.methodManageindivisuallist();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        prgcodes.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.PPC.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.PPC.uniid=userInfo.getInstituteID();
                    IA.PPC.ProgramPaperCode();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createprgbrd.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.BN.ProgTermHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.BN.methodBoardNormalization("add");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        manageprgbrd.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.BN.ProgTermHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.BN.methodBoardNormalization("manage");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createmerit.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.cfmc.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.cfmc.fianlmeritcomponent();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        managemerit.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                	
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.mfmc.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.mfmc.methodmanagefinalmerit();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        splwt.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.SW.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.SW.MethodAddSpecialWeightage();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        resetcycle.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.gr.outerPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.gr.methodGenerateReport("reset");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
        createapp.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.css.onModuleLoad1());
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
        manageapp.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.mss.onModuleLoad1());
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
        
        /*
         * added today
         */
        progregmenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler(){

			public void onClick(MenuItemClickEvent event) {
				
				bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();

                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.pad.methodAddMarks("add");
                IA.RightFlexTable.setWidget(0, 0, IA.pad.vPanel);
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
				
				
			}
        	
        });
        
        computemenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();

                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.gr.methodGenerateReport("computemarks");
                    IA.RightFlexTable.setWidget(0, 0, IA.gr.outerPanel);
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
        cutoffmenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.cps.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.cps.cutoffcomponentdetails();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
        createmarks.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.sfm.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.sfm.methodAddMarks("add");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        managemarks.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.sfm.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.sfm.methodAddMarks("edit");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        uploadfile.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.excel.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.excel.getPanel();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
		  //Add by Devendra
        uploadFileForAddMarksInFinalMarks.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.uploadStudentMarks.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.uploadStudentMarks.onModuleLoad();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
		//Add by Devendra May 8th
        finalMeritListProcess.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.finalMerilListProcess.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.finalMerilListProcess.onModuleLoad();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
		 //Add by Devendra May 10th
        importStudentMarks.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.importOmrMarks.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.importOmrMarks.onModuleLoad();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
		//Add by Devendra 18 May
        setupSubject.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.subjectCodeSetup.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.subjectCodeSetup.onModuleLoad("add");
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
      //Add by Devendra 18 May
        manageSubject.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.subjectCodeSetup.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.subjectCodeSetup.onModuleLoad("manage");
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
		//Add by Devendra 19 May
        editSummarySheetMenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.manageSummarySheetNew.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.manageSummarySheetNew.onModuleLoad("edit");
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
      //Add by Devendra 19 May
        deleteSummarySheetMenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.manageSummarySheetNew.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.manageSummarySheetNew.onModuleLoad("delete");
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
        testnumber.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.gr.outerPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.gr.methodGenerateReport("testnumber");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
        
//        createexcelmenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
//            public void onClick(MenuItemClickEvent event) {
//                bodyHorizontalPanel.clear();
//                IA.finalHorizontalPanel.clear();
//                IA.RightFlexTable.setWidget(0, 0, IA.excels.vPanel);
//                IA.finalHorizontalPanel.add(IA.RightFlexTable);
//                IA.excels.defineExcelComponents();
//                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
//            }
//        });
        
//        manageexcelmenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
//            public void onClick(MenuItemClickEvent event) {
//                bodyHorizontalPanel.clear();
//                IA.finalHorizontalPanel.clear();
//                IA.RightFlexTable.setWidget(0, 0, IA.manageExcelComponents.vPanel);
//                IA.finalHorizontalPanel.add(IA.RightFlexTable);
//                IA.manageExcelComponents.defineExcelComponents();
//                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
//            }
//        });

        calloutmenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.gr.outerPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.gr.methodGenerateReport("internalcall");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        callwithmenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.gr.outerPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.gr.methodGenerateReport("externalcall");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        finalmenu.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.gr.outerPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.gr.methodGenerateReport("finalmerit");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createentity.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.IF.FacultyPanelRight);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IF.methodAddEntity();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        manageentity.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.IF.FacultyPanelRight);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IF.methodManageEntity();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        newprg.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodAddProgramme();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        addstdate.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodNewDetails("duration");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        addbranch.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodNewDetails("branch");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        addspec.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodNewDetails("specialization");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        addreser.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodNewDetails("reservation");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        basicinfo.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodManageProg("Basic");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        duration.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodManageProg("duration");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        branchinfo.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodManageProg("branch");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        specinfo.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodManageProg("specialization");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        reserinfo.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.IP.ProgrammeHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.IP.methodManageProg("reservation");
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createentityprg.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.POB.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.POB.methodAssignProg();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        manageentityprg.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.POB.vPanel);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.POB.manageOfferedBy();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createprgterm.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.PTD.ProgTermHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.PTD.methodAddTermDetails();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        manageprgterm.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0,
                        IA.PTD.ProgTermHorizontalPanel1);
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    IA.PTD.methodManageTermDetails();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createprgage.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.pae.onModuleLoad());
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        manageprgage.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.mpae.onModuleLoad());
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        createprgelig.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.pce.onModuleLoad());
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });

        manageprgelig.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
                public void onClick(MenuItemClickEvent event) {
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    IA.RightFlexTable.setWidget(0, 0, IA.mpce.onModuleLoad());
                    IA.finalHorizontalPanel.add(IA.RightFlexTable);
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                }
            });
        //Arjun Code Starts
        createCenter.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.pec.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.pec.createCenter();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
        
        manageCenter.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
            	 bodyHorizontalPanel.clear();
                 IA.finalHorizontalPanel.clear();
                 IA.RightFlexTable.setWidget(0, 0, IA.mec.vPanel);
                 IA.finalHorizontalPanel.add(IA.RightFlexTable);
                 IA.mec.manageCenter();
                 bodyHorizontalPanel.add(IA.finalHorizontalPanel);
               
            }
        });
        
        tieRuleMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.STR.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.STR.setTieRule();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });
        frmAuthority.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                bodyHorizontalPanel.clear();
                IA.finalHorizontalPanel.clear();
                IA.RightFlexTable.setWidget(0, 0, IA.frmAuthority.vPanel);
                IA.finalHorizontalPanel.add(IA.RightFlexTable);
                IA.frmAuthority.setFormAuthority();
                bodyHorizontalPanel.add(IA.finalHorizontalPanel);
            }
        });

        final Label userLabel = new Label(getName());
        // final Label timeLabel = new Label(getLastLogin());

        // Property Set***********************************
        // CMS = new CM_Student(uID);
        // SA =new CM_Superadmin(uID);
        IA = new CM_InstituteAdmin(uID);

        // DA = new CM_DepartmentAdmin(uID, type);
        // FA = new CM_InstructorAdmin(uID, type);
        mainVerticalPanel.setWidth("100%");
        mainVerticalPanel.setHeight("100%");

        headerVerticalPanel.setWidth("100%");
        footerVerticalPanel.setWidth("100%");
        

        HypMainHorizontalPanelTop.setWidth("100%");

        HypMainHorizontalPanelBottom.setWidth("100%");

        headerVerticalPanel.setStyleName("headerBlueBack");
        footerVerticalPanel.setStyleName("footerBlueBack");

        HypMainHorizontalPanelTop.setStyleName("logOutPanel");
        HypMainHorizontalPanelBottom.setStyleName("logOutPanel");

        headerLabel.setStyleName("MainHeading");
        headerLabel1.setStyleName("heading1");
        headerLabel2.setStyleName("heading1");
        footerLabel.setStyleName("heading1");
        footerLabel1.setStyleName("heading1");

        labelVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        entryVerticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

        bodyHorizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        bodyHorizontalPanel.setStyleName("");

        labelVerticalPanel.setSpacing(5);
        entryVerticalPanel.setSpacing(5);
        headerVerticalPanel.setSpacing(5);
        

        HorizontalPanel linkPanel = new HorizontalPanel();

        // final Hyperlink home = new Hyperlink("Home", "Home");
        // final Hyperlink Feedback = new Hyperlink("Feedback", "Feedback");
        linkPanel.setSpacing(5);

        // linkPanel.add(home);
        // linkPanel.add(Feedback);
        HorizontalPanel panel = new HorizontalPanel();
        panel.setSpacing(5);
        // panel.add(settings);
        panel.add(logOutTopHyperLink);

        HorizontalPanel panel2 = new HorizontalPanel();
        panel2.setSpacing(5);
        panel2.add(HypBackTopHyperLink);

        // Add****************************************
        HypMainHorizontalPanelTop.add(panel2);
        HypMainHorizontalPanelTop.setCellHorizontalAlignment(panel2,
            HasHorizontalAlignment.ALIGN_LEFT);
        HypMainHorizontalPanelTop.setSpacing(5);

        HypMainHorizontalPanelTop.add(panel);
        // HypMainHorizontalPanelTop.add(logOutTopHyperLink);
        HypMainHorizontalPanelTop.setCellHorizontalAlignment(panel,
            HasHorizontalAlignment.ALIGN_RIGHT);
        headerVerticalPanel.add(headerLabel);
        headerVerticalPanel.add(headerLabel1);
        headerVerticalPanel.add(headerLabel2);
        headerVerticalPanel.add(detailsHorizontalPanel);
        headerVerticalPanel.add(HypMainHorizontalPanelTop);
        headerVerticalPanel.add(entryVerticalPanel1);

        labelVerticalPanel.add(welcomeLabel);

        entryVerticalPanel.add(userLabel);

        detailsHorizontalPanel.add(labelVerticalPanel);
        detailsHorizontalPanel.add(entryVerticalPanel);

        HypMainHorizontalPanelBottom.add(linkPanel);
        HypMainHorizontalPanelBottom.setCellHorizontalAlignment(linkPanel,
            HasHorizontalAlignment.ALIGN_CENTER);
        HypMainHorizontalPanelBottom.setCellVerticalAlignment(linkPanel,
            HasVerticalAlignment.ALIGN_MIDDLE);

        bodyHorizontalPanel.setHeight("475px");
        footerVerticalPanel.setHeight("80px");

        footerVerticalPanel.setSpacing(5);
        footerVerticalPanel.add(HypMainHorizontalPanelBottom);
        footerVerticalPanel.add(footerLabel);
        footerVerticalPanel.add(footerLabel1);        

        mainVerticalPanel.add(headerVerticalPanel);
        mainVerticalPanel.add(canvas);
        mainVerticalPanel.add(bodyHorizontalPanel);
        mainVerticalPanel.add(footerVerticalPanel);

        IA.init();
        bodyHorizontalPanel.add(IA.finalHorizontalPanel);

        logOutTopHyperLink.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent arg0) {
//                	Window.open(urlHome, "_self", ""); 
//                	urlHome="http://www.google.com";
                	History.newItem("logout");
//                	redirect(urlHome);
//                	Window.Location.replace(urlHome);

                    /*RootPanel.get().clear();
                    bodyHorizontalPanel.clear();

                    Login login = new Login();
                    login.onModuleLoad();
                    RootPanel.get().add(login.mainPanel);*/
                }
            });

        HypBackTopHyperLink.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent arg0) {
                    canvas.clear();

                    canvas.add(home);
                    canvas.add(admission);
                    //canvas.add(coursemenu);
                    bodyHorizontalPanel.clear();
                    IA.finalHorizontalPanel.clear();
                    bodyHorizontalPanel.add(IA.finalHorizontalPanel);
                    IA.init();
                }
            });
        
        /*Window.addWindowClosingHandler(new ClosingHandler() {
            @Override
             public void onWindowClosing(ClosingEvent event) {
             event.setMessage("My program");
             }
           }); */

        RootPanel.get().add(mainVerticalPanel);

        return mainVerticalPanel;
    }

    String getName() {
        return user_name;
    }
    
    /*
     *   var Backlen=$wnd.history.length;  
	    $wnd.alert(Backlen); 
     <!--$wnd.history.go(-Backlen);
     $wnd.history.forward(1);
     	$wnd.alert($wnd.history.length); -->    
     */
    
  //redirect the browser to the given url
    public static native void redirect(String url)/*-{
      	$wnd.location.href = url;
      }-*/;
      
       
}
