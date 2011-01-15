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


/**
 * @author Manpreet Kaur
 */
import in.ac.dei.edrp.client.CM_InstitutePrograms;
import in.ac.dei.edrp.client.CM_SuperUniversity;
import in.ac.dei.edrp.client.CM_createEntity;
import in.ac.dei.edrp.client.CM_progOfferedBy;
import in.ac.dei.edrp.client.CM_progTermDetails;
import in.ac.dei.edrp.client.Applications.CM_cutoffcomponents;
import in.ac.dei.edrp.client.Applications.CM_studentFinalMarks;
import in.ac.dei.edrp.client.Applications.CreateSummarySheet;
import in.ac.dei.edrp.client.Applications.ManageSummarySheet;
import in.ac.dei.edrp.client.ProgramSetup.CM_ProgramPaperCode;
import in.ac.dei.edrp.client.ProgramSetup.CM_boardNormalization;
import in.ac.dei.edrp.client.ProgramSetup.CM_cutoffcall;
import in.ac.dei.edrp.client.ProgramSetup.CM_finaldegree;
import in.ac.dei.edrp.client.ProgramSetup.CM_managecutoffcall;
import in.ac.dei.edrp.client.ProgramSetup.CM_managefirstdegree;
import in.ac.dei.edrp.client.ProgramSetup.CM_manageprogramcomponent;
import in.ac.dei.edrp.client.ProgramSetup.CM_programcompenents;
import in.ac.dei.edrp.client.ProgramSetup.ManageProgramElig;
import in.ac.dei.edrp.client.ProgramSetup.Manage_Comp_Elig;
import in.ac.dei.edrp.client.ProgramSetup.Program_Age_Eligibility;
import in.ac.dei.edrp.client.ProgramSetup.Program_Comp_Eligibility;
import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;
import in.ac.dei.edrp.client.ReportGeneration.GenrateReport;
import in.ac.dei.edrp.client.SystemSetup.CM_SpecialWeightage;
import in.ac.dei.edrp.client.SystemSetup.CM_finalmeritcomponent;
import in.ac.dei.edrp.client.SystemSetup.CM_managefinalmeritcomponents;
import in.ac.dei.edrp.client.addmarks.UploadMarksUsingExcel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Function;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("unused")
/**
 * This class creates interface for institute administrator.
 * All links for institute administrator appear in accordion panel on the left-hand side
 * and corresponding pages open on the right-hand side.
 */
public class CM_InstituteAdmin {
    private final CM_connectAsync connectService = GWT.create(CM_connect.class);
    public String userID;
    final HorizontalPanel hpanel= new HorizontalPanel();
    final DecoratorPanel RightDecoratorPanel = new DecoratorPanel();
    public FlexTable RightFlexTable = new FlexTable();
    public HorizontalPanel finalHorizontalPanel = new HorizontalPanel();

    /*
     * Creating objects of functional pages to be added on main page.
     */
    public CM_InstituteHome IH;
//    CM_InstituteSession IS;
    public CM_createEntity IF;
//    CM_InstituteDepartment ID;
//    CM_InstituteStaffMgmt IFM;
    public CM_InstitutePrograms IP;
    public CM_progTermDetails PTD;
    public CM_boardNormalization BN;
    
//    CM_InstituteSubjects ISB;
//    CM_InstituteRegistration IR;
//    CM_SuperAccSettings SAS;
//    CM_InstituteStructure CIS;
    public CM_progOfferedBy POB;
    
    
    
    public CM_SpecialWeightage SW;
//  ProgramPaperCode PPC;
    public CM_ProgramPaperCode PPC;
  
//  programRegistration PR;
//  manageprgregistration MPR;
  public CM_cutoffcall call;
  public CM_managecutoffcall mcall;
  public CM_programcompenents prgc;
  public CM_manageprogramcomponent mpgc;
  public CM_finalmeritcomponent cfmc;
  public CM_managefinalmeritcomponents mfmc;
  public CM_finaldegree cfd;
  public CM_managefirstdegree cmfd;
  public CM_cutoffcomponents cps;
  public CreateSummarySheet css; 
  public ManageSummarySheet mss;
  public Program_Age_Eligibility pae;
  public Program_Comp_Eligibility pce;
  public ManageProgramElig mpae;
  public Manage_Comp_Elig mpce;
  

    public GenrateReport gr=new GenrateReport();
   // ManageMarks mm=new ManageMarks();
    
    
    public CM_studentFinalMarks sfm;
    
    
    
    public CM_SuperUniversity SU;
    public UploadMarksUsingExcel upload=new UploadMarksUsingExcel();
    
    
    
    /**
     * Constructor for setting the Value of User ID
     * @param Uid
     */
    public CM_InstituteAdmin(String Uid) {
        this.userID = Uid;
    }

    /**
     * Main method of the class that
     * returns a horizontal panel with
     * Accordion panel on left hand-side
     * and items to be shown on right hand-side
     * on selecting items from accordion panel
     */
    public HorizontalPanel init() {
    	
        IH = new CM_InstituteHome(userID);
//        IS = new CM_InstituteSession(userID);
        IF = new CM_createEntity(userID);
//        ID = new CM_InstituteDepartment(userID);
//        IFM = new CM_InstituteStaffMgmt(userID);
        IP = new CM_InstitutePrograms(userID);
//        ISB = new CM_InstituteSubjects(userID);
//        IR = new CM_InstituteRegistration(userID);
//        SAS = new CM_SuperAccSettings(userID);
//        CIS = new CM_InstituteStructure(userID);
        
        SW = new CM_SpecialWeightage(userID);
        PPC = new CM_ProgramPaperCode(userID);
        BN=new CM_boardNormalization(userID);
        
        /* Ashish*/
//        PR = new programRegistration();
//        MPR = new manageprgregistration();
        call = new CM_cutoffcall(userID);
        mcall = new CM_managecutoffcall(userID);
        prgc = new CM_programcompenents(userID);
        mpgc = new CM_manageprogramcomponent(userID);
        cfmc = new CM_finalmeritcomponent(userID);
        cfd = new CM_finaldegree(userID);
        cmfd = new CM_managefirstdegree(userID);
        cps = new CM_cutoffcomponents(userID);
        mfmc = new CM_managefinalmeritcomponents(userID);
        
        
        css=new CreateSummarySheet(userID);
        mss=new ManageSummarySheet(userID);
        
        pae= new Program_Age_Eligibility(userID);
        pce= new Program_Comp_Eligibility(userID);
        mpae= new ManageProgramElig(userID);
        mpce= new Manage_Comp_Elig(userID);
        
        
        POB=new CM_progOfferedBy(userID);
        PTD=new CM_progTermDetails(userID);
        
        sfm=new CM_studentFinalMarks(userID);
        
        
        SU = new CM_SuperUniversity(userID);
        
        
        
        
        
        
        
        
        
        RightFlexTable.clear();
        RightFlexTable.setWidget(0, 0, IH.mainHorizontalPanel);
        IH.Home();
        
        finalHorizontalPanel.setSize("1200px", "500px");
//        finalHorizontalPanel.setStyleName("background");        
        
        hpanel.setSize("1200px", "500px");
        hpanel.setStyleName("background");
        hpanel.add(RightFlexTable);
        RightDecoratorPanel.clear();
        RightDecoratorPanel.add(hpanel);

        finalHorizontalPanel.setSpacing(10);
//        finalHorizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        finalHorizontalPanel.add(RightDecoratorPanel);

        return finalHorizontalPanel;
    }
}
