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
import in.ac.dei.edrp.client.Applications.ProgramApplicationDetails;
import in.ac.dei.edrp.client.InternalSummarySheet.FormSearch;
import in.ac.dei.edrp.client.ProgramSetup.CM_ProgramPaperCode;
import in.ac.dei.edrp.client.ProgramSetup.ApplicationFormSetup;
import in.ac.dei.edrp.client.ProgramSetup.CM_boardNormalization;
import in.ac.dei.edrp.client.ProgramSetup.CM_cutoffcall;
import in.ac.dei.edrp.client.ProgramSetup.CM_finaldegree;
import in.ac.dei.edrp.client.ProgramSetup.ManageApplicationForm;
import in.ac.dei.edrp.client.ProgramSetup.CM_managecutoffcall;
import in.ac.dei.edrp.client.ProgramSetup.CM_managefirstdegree;
import in.ac.dei.edrp.client.ProgramSetup.CM_manageprogramcomponent;
import in.ac.dei.edrp.client.ProgramSetup.CM_programcompenents;
import in.ac.dei.edrp.client.ProgramSetup.ProgramDocumentSetUp;
import in.ac.dei.edrp.client.ProgramSetup.ManageProgramElig;
import in.ac.dei.edrp.client.ProgramSetup.Manage_Comp_Elig;
import in.ac.dei.edrp.client.ProgramSetup.ProgramSearchSetup;
import in.ac.dei.edrp.client.ProgramSetup.Program_Age_Eligibility;
import in.ac.dei.edrp.client.ProgramSetup.Program_Comp_Eligibility;
import in.ac.dei.edrp.client.ProgramSetup.SubjectCodeSetup;
import in.ac.dei.edrp.client.ProgramSetup.UploadStudentComponentMarks;
import in.ac.dei.edrp.client.RPCFiles.CM_connect;
import in.ac.dei.edrp.client.RPCFiles.CM_connectAsync;
import in.ac.dei.edrp.client.ReportGeneration.GenrateReport;
import in.ac.dei.edrp.client.SystemSetup.CM_SpecialWeightage;
import in.ac.dei.edrp.client.SystemSetup.CM_finalmeritcomponent;
import in.ac.dei.edrp.client.SystemSetup.CM_managefinalmeritcomponents;
import in.ac.dei.edrp.client.SystemSetup.FormAuthority;
import in.ac.dei.edrp.client.addmarks.CommonScreenForReporting;
import in.ac.dei.edrp.client.addmarks.UploadMarksUsingExcel;
import in.ac.dei.edrp.client.process.FinalMeritListProcess; 
import in.ac.dei.edrp.client.process.ImportOmrMarks;
import in.ac.dei.edrp.client.summarysheet.ManageSummarySheetNew;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import in.ac.dei.edrp.client.ProgramSetup.ProgramExaminationCenter;
import in.ac.dei.edrp.client.ProgramSetup.ManageExaminationCenter;
import in.ac.dei.edrp.client.SystemSetup.SetupTieRule;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("unused")
/*
 * * This class creates interface for institute administrator. All links for
 * institute administrator appear in accordion panel on the left-hand side and
 * corresponding pages open on the right-hand side.
 */
public class CM_InstituteAdmin {
	private final CM_connectAsync connectService = GWT.create(CM_connect.class);
	public String userID;
	final HorizontalPanel hpanel = new HorizontalPanel();
	final DecoratorPanel RightDecoratorPanel = new DecoratorPanel();
	public FlexTable RightFlexTable = new FlexTable();
	public HorizontalPanel finalHorizontalPanel = new HorizontalPanel();

	/*
	 * Creating objects of functional pages to be added on main page.
	 */
	public CM_InstituteHome IH;
	// CM_InstituteSession IS;
	public CM_createEntity IF;
	// CM_InstituteDepartment ID;
	// CM_InstituteStaffMgmt IFM;
	public CM_InstitutePrograms IP;
	public CM_progTermDetails PTD;
	public CM_boardNormalization BN;
	public ProgramExaminationCenter pec;
	public ManageExaminationCenter mec;
	public SetupTieRule STR;
	public FormAuthority frmAuthority;
	// CM_InstituteSubjects ISB;
	// CM_InstituteRegistration IR;
	// CM_SuperAccSettings SAS;
	// CM_InstituteStructure CIS;
	public CM_progOfferedBy POB;

	public CM_SpecialWeightage SW;
	// ProgramPaperCode PPC;
	public CM_ProgramPaperCode PPC;
	public CM_ComboBoxes boxes;

	// programRegistration PR;
	// manageprgregistration MPR;
	public CM_cutoffcall call;
	public CM_managecutoffcall mcall;
	public CM_programcompenents prgc;
	public ApplicationFormSetup appform;		//uk
	public ManageApplicationForm manageappform;		//uk
	public ProgramDocumentSetUp progDocumnet;	//uk
	public ProgramSearchSetup progSearch;
	public FormSearch formSearch;				//uk

	
	/*public ManageApplicationForm manageappform;*/
	
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
	public CommonScreenForReporting reporting;
	public UploadMarksUsingExcel excel;
	public UploadStudentComponentMarks uploadStudentMarks; // add by Devendra
	public FinalMeritListProcess finalMerilListProcess;//Add by Devendra May 8th
	public ImportOmrMarks importOmrMarks;//Add by Devendra May 10	
	public GenrateReport gr;
	public SubjectCodeSetup subjectCodeSetup;//Add by Devendra 18 May
	public ManageSummarySheetNew manageSummarySheetNew;//Add by Devendra May 19
//	public ExcelComponents excels;
//	public ManageExcelComponents manageExcelComponents;
	// ManageMarks mm=new ManageMarks();

	public CM_studentFinalMarks sfm;

	public CM_SuperUniversity SU;
	
	public ProgramApplicationDetails pad;

	/**
	 * Constructor for setting the Value of User ID
	 * 
	 * @param Uid
	 */
	public CM_InstituteAdmin(String Uid) {
		this.userID = Uid;
	}

	/**
	 * Main method of the class that returns a horizontal panel with Accordion
	 * panel on left hand-side and items to be shown on right hand-side on
	 * selecting items from accordion panel
	 */
	public HorizontalPanel init() {
		pec=new ProgramExaminationCenter(userID);//Arjun Code
		mec=new ManageExaminationCenter(userID);//Arjun Code
		frmAuthority=new FormAuthority(userID);
		STR=new SetupTieRule(userID);
		IH = new CM_InstituteHome(userID);
		// IS = new CM_InstituteSession(userID);
		IF = new CM_createEntity(userID);
		// ID = new CM_InstituteDepartment(userID);
		// IFM = new CM_InstituteStaffMgmt(userID);
		IP = new CM_InstitutePrograms(userID);
		// ISB = new CM_InstituteSubjects(userID);
		// IR = new CM_InstituteRegistration(userID);
		// SAS = new CM_SuperAccSettings(userID);
		// CIS = new CM_InstituteStructure(userID);

		SW = new CM_SpecialWeightage(userID);
		PPC = new CM_ProgramPaperCode(userID);
		BN = new CM_boardNormalization(userID);

		/* Ashish */
		// PR = new programRegistration();
		// MPR = new manageprgregistration();
		call = new CM_cutoffcall(userID);
		mcall = new CM_managecutoffcall(userID);
		prgc = new CM_programcompenents(userID);
		appform=new ApplicationFormSetup(userID);		//Upasana
		manageappform=new ManageApplicationForm(userID);		//upasana
		progDocumnet=new ProgramDocumentSetUp(userID); //upasana
		progSearch=new ProgramSearchSetup(userID);		//upasana
		formSearch=new FormSearch(userID);					//Upasana
		//manageappform=new ManageApplicationForm(userID);		//uk
		//summary=new SummarySheet();
		//accountSetup=new ApplicantAccountSetup(userID);
		
		mpgc = new CM_manageprogramcomponent(userID);
		cfmc = new CM_finalmeritcomponent(userID);
		cfd = new CM_finaldegree(userID);
		cmfd = new CM_managefirstdegree(userID);
		cps = new CM_cutoffcomponents(userID);
		mfmc = new CM_managefinalmeritcomponents(userID);
		uploadStudentMarks=new UploadStudentComponentMarks(userID);//Add by Devendra
		finalMerilListProcess=new FinalMeritListProcess(userID);//Add by Devendra May 8th
		importOmrMarks=new ImportOmrMarks(userID);//Add by Devendra May 10th
		css = new CreateSummarySheet(userID);
		mss = new ManageSummarySheet(userID);

		pae = new Program_Age_Eligibility(userID);
		pce = new Program_Comp_Eligibility(userID);
		mpae = new ManageProgramElig(userID);
		mpce = new Manage_Comp_Elig(userID);

		POB = new CM_progOfferedBy(userID);
		PTD = new CM_progTermDetails(userID);

		sfm = new CM_studentFinalMarks(userID);

		SU = new CM_SuperUniversity(userID);

		boxes = new CM_ComboBoxes(userID);

		gr = new GenrateReport(userID);
		subjectCodeSetup=new SubjectCodeSetup(userID);//Add by Devendra May 18
		manageSummarySheetNew=new ManageSummarySheetNew(userID);//Add by Devendra May 19
//		excels = new ExcelComponents(userID);
//		
//		manageExcelComponents = new ManageExcelComponents(userID);

		reporting = new CommonScreenForReporting(userID);

		excel = new UploadMarksUsingExcel(userID);
		
		pad = new ProgramApplicationDetails(userID);

		RightFlexTable.clear();
		RightFlexTable.setWidget(0, 0, IH.mainHorizontalPanel);
		IH.Home();

		finalHorizontalPanel.setSize("1200px", "500px");
		// finalHorizontalPanel.setStyleName("background");

		hpanel.setSize("1200px", "500px");
		hpanel.setStyleName("background");
		hpanel.add(RightFlexTable);
		RightDecoratorPanel.clear();
		RightDecoratorPanel.add(hpanel);

		finalHorizontalPanel.setSpacing(10);
		// finalHorizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		finalHorizontalPanel.add(RightDecoratorPanel);

		return finalHorizontalPanel;
	}
}
