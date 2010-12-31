package com.erp.nfes;

/*
 * Copyright (c) 2002-2006 Amrita Technologies
 * Amritapuri, Kerala, India
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Amrita
 * Technologies. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Amrita Technologies.
 */

/** Common interface to Profile documentation related constants.
 *
 *	@author		Akhil
 *	@version	1.0
 */



public interface StaffProfileConstants {

	//DOCUMENT APPROVAED STATUS...
	static final String CDOC_DOCUMENT_APPROVED_YES = "1";
	static final String CDOC_DOCUMENT_APPROVED_NO = "0";
	
   //DOCUMENT PROVISIONAL STATUS...
	static final String CDOC_DOCUMENT_AMENDED_YES = "1";
	static final String CDOC_DOCUMENT_AMENDED_NO = "0";

	//DOCUMENT FORM TYPE, VELOCITY/HTML...
	static final String CDOC_VELOCITY_DOCUMENT = "1";
	static final String CDOC_HTML_DOCUMENT = "0";
	static final String CDOC_CPATH_DOCUMENT = "2"; // Added by Kannan to identify CPATH forms

    static final String CDOC_CLINICAL_NOTES = "3";//Added by SN for using ENursing module

	//DISCHARGE SUMMARY RELATED CONSTANTS;
	static final int CDOC_DISCHARGE_SUMMARY = 1;

	//SERVICE CENTER REPORTS RELATED CONSTANTS;
	static final int CDOC_SERVICE_CENTER_REPORTS = 2;

	static final String CDOC_DOCUMENT_MASTER = "document_master";
	static final String CDOC_ENTINTY_DOCUMENT_MASTER = "entity_document_master";
	static final String CDOC_DOCUMENT_TYPE_MASTER = "profile_type_master";
	static final String CDOC_PACKAGE_MASTER = "package_master";
	static final String CDOC_PACKAGE_FORM_MAP = "package_form_map";
	static final String CDOC_SERVICECENTER_FORM_MAP = "form_service_center_map";
	static final String CDOC_FORM_MASTER = "profile_master";
	static final String CDOC_FORM_MASTER_EXTENSION = "form_master_extension";
	static final String CDOC_FORM_SERVICE_CENTER_SERVICE_MAP="form_service_center_service_map";
	static final String GENERAL_MASTER = "general_master";
	static final String HEADER = "CDOC-HEADER_IMAGE";
	static final String FOOTER = "CDOC-FOOTER_IMAGE";
	//STATUS FOR OPENING A DOCUMENT.
	static final int CDOC_CREATE_NEW_FORM = 0;
	static final int CDOC_OPEN_SAVED_FORM = 1;

	//OTHER CLINICAL DOC REPORTS RELATED CONSTANTS;
	static final int CDOC_CLINICAL_REPORTS = 3;
	static final String CDOC_CLINICAL_REPORTS_TEBLE = "";

	//CRITICAL  INCIDENT REPORT RELATED QUERIES
	static final int CDOC_CRITICAL_INCIDENT_MONITOR_REPORT = 4;
	//Cancer Registry
	static final int CDOC_CANCER_REGISTRY = 5;

	//EMR related Constants;
	static final int CDOC_CONSULTATION_NOTE = 6;
	static final int CDOC_PRESCRIPTION_NOTE = 7;
	static final int CDOC_VITALS = 8;
	static final int CDOC_MLC = 9;
	static final int CDOC_PROGRESS_NOTE = 10;
	static final int CDOC_EMERGENCY_CASE_RECORD = 11;
	static final int CDOC_TEACHING_CASE = 12;
	static final int CDOC_GASTRO_NOTE = 13;
	static final int CDOC_VASCULAR_NOTE = 14;
	static final int CDOC_REFERRAL_NOTE = 15;

	static final String CDOC_FORM_TYPE = "CDOC_FORM_TYPE";

	//CONSTANTS FOR CLINICAL DOC CONTROLS
	static final int CDOC_PATIENT_AGE = 1;
	static final int CDOC_TODAY = 2;
	static final int CDOC_BED_NO = 3;
	static final int CDOC_CONSULTANT = 4;
	static final int CDOC_SPECIALITY = 5;
	static final int CDOC_DOA = 6;
	static final int CDOC_DOP = 7;
	static final int CDOC_DOD = 8;
	static final int CDOC_LABRESULTS = 9;
	static final int CDOC_USERLOGIN = 10;
	static final int CDOC_CURRENTTIME = 11;
	static final int CDOC_VISITCODE = 12;
	static final int CDOC_DISCHARGEPRESCRIPTION = 13;
	static final int CDOC_CURRENTDATETTIME = 14;
	static final int CDOC_TITLE = 15;
	

	//CONSTANTS FOR FOP AND TEMPLATES
	static final int SIMPLE_BLOCK = 1;
	static final int LINE_BLOCK = 2;
	static final int IMG_BLOCK =3;
	static final int PATIENT_INFO = 4;
	static final int FORM_INFO = 5;
	static final int CAPTION_ONLY = 6;
	static final int SIMPLE_LINE_BLOCK = 7;
	static final int LINE_SIMPLE_BLOCK = 8;
	static final int CHECK_BOX_BLOCK = 9;
	static final int COMBO_BOX_BLOCK = 10;
	static final int LABEL_BLOCK = 11;
	static final int IMAGE_BOX_BLOCK = 12;
	static final int DIAGNOSIS = 13;
	static final int MISSING = 14;
	static final int CDOCS_BOX_BLOCK = 15;
	static final int STATIC_IMAGE_BOX_BLOCK = 16;
	static final int HIS_SELECTOR_BLOCK=17;
	static final int BLOOD_GROUP_BOX_BLOCK = 18;
	static final int REFRRALS_BOX_BLOCK = 19;
	static final int DETAILFORM_BLOCK = 20;
	static final int SURGERYPLAN_BLOCK = 21;
	static final int ICD_CONTROLES_BLOCK = 22;
	static final int RICH_EDIT_BLOCK  =23;
	static final int DYNAMIC_TABLE = 24;
//Merging Starts -- Prajeesh
//Taken From 407 AIMS branch	
	static final int TECHNITION_BLOCK = 25;
//Merging Ends	
	static final int USER_DETAILES_BLOCK = 26;
	
	static final int CLINICAL_DIAGNOSIS =27;


	//Session Key for passing patient info from ClinicFormServlet to ClinicalReportServlet : Don't overwrite with another session key
	static final String PATIENT_DEMOGRAPHIC_MAP = "CDOC-PATIENT_DEMOGRAPHIC-MAP";
	//Session Key for passing Form Name  from ClinicFormServlet to ClinicalReportServlet : Don't overwrite with another session key
	static final String CDOC_FORM_NAME = "CDOC_SESSION_FORM_NAME";
	//Session Key for passing PDF as ByteArrayStream from  ClinicFormServlet to ClinicalReportServlet : Don't overwrite with another session key
	static final String CDOC_VIEW_AS_PDF = "CDOC_VIEW_AS_PDF";
	static final String CDOC_VIEW_AS_TEMPLTE_PDF = "CDOC_VIEW_AS_TEMPLTE_PDF";
	//Session key for geting the viewport values to the ClinicalREportFormServlet
	static final String CDOC_VIEW_REPORT = "CDOC_VIEW_REPORT";
	//Session key for getting the document id to ClinicalReportFormServlet
	static final String CDOC_DOCUMENT_ID = "CDOC_DOCUMENT_ID";
	//Session key for getting the approval status to ClinicalReportServlet
	static final String CDOC_APPROVED_YESNO = "CDOC_APPROVED_YESNO";
	
   //Session key for getting the provisional status to the ClinicalReportServlet
	static final String CDOC_AMENDED_YESNO = "CDOC_AMENDED_YESNO";

	//Session key for putting/getting the user privilege arraylist...
	static final String CDOC_SESSION_USER_PRIVILEGES = "userPrivileges";

	//The two types of saving the documents...
	//Added by Manoj S, on 11/11/2005...
	static final String CDOC_REGULAR_FORM = "CDOC_REGULAR_FORM"; //Old forms...

	static final String CDOC_CPATH_FORM = "CDOC_CPATH_FORM";	 //New CPath type forms . Also serves as the session key to determine CPATH for printing


	//CONSTANTS FOR CLINICAL DOC STATUS
	static final int CDOC_DOCUMENT_DICTATED = 1;
	static final int CDOC_DOCUMENT_OPEN_FOR_TRANSCRIPTION = 2;
	static final int CDOC_DOCUMENT_SIGNED_OFF = 3;
	static final int CDOC_DOCUMENT_CANCELLED = 4;
	static final int CDOC_DOCUMENT_AMENDED = 5;
	static final int CDOC_DOCUMENT_OPEN_FOR_APPROVAL = 6;
	static final int CDOC_DOCUMENT_BACK_TO_DICTATED = 7;
	static final int CDOC_DOCUMENT_PROVISIONAL = 8; //Added by vivek on 22/01/07
//Merging Starts -- Prajeesh
//Taken From 407 AIMS branch	
	static final int CDOC_DOCUMENT_INVALIDATED = 9; //Added by Saju on 11/04/2007
//Merging Ends	

	//CONSTANT FOR PRINT READY REPORT
	static final String CDOC_DOCUMENT_SHOW_PRINT_READY_REPORT = "Show Printable";

	//CONSTANTS FOR CLINICAL DOCUMENT TYPES
	static final String CDOC_DOCUMENT_DISCHARGESUMMARY = "DS";
	static final String CDOC_DOCUMENT_SERVICEREPORTS = "SCR";
	static final String CDOC_DOCUMENT_OTNOTES = "OTN";
	static final String CDOC_DOCUMENT_GENERAL = "GENERAL";
	static final String CDOC_DOCUMENT_OPCON = "OPR";
	static final String CDOC_DOCUMENT_PN = "PN";
	static final String CDOC_DOCUMENT_ORDER_REQUEST = "ORD";
	static final String CDOC_DOCUMENT_ENTITY_REPORTS = "ETR";
	//new document types for EMR implementation
	static final String CDOC_DOCUMENT_VITALS = "VL";
	static final String CDOC_DOCUMENT_GENERAL_CONSULTATION_NOTE = "CN";
	static final String CDOC_DOCUMENT_PRESCRIPTION_NOTE = "PN";
	static final String CDOC_DOCUMENT_PROGRESS_NOTE = "PGN";
	static final String CDOC_DOCUMENT_MLC = "MLC";
	static final String CDOC_DOCUMENT_EMERGENCY_CASE_RECORD = "ECR";
	static final String CDOC_DOCUMENT_TEACHING_CASE = "TC";
	static final String CDOC_DOCUMENT_GASTRO_NOTE = "GN";
	static final String CDOC_DOCUMENT_VASCULAR_NOTE = "VN";
	static final String CDOC_DOCUMENT_REFERRAL_NOTE = "RN";
	static final String CDOC_DOCUMENT_ADVANCE_DIRECTIVE = "ADV_DIR";

	static final String CDOC_DOCUMENT_CASE_RECORDS = "C";
	
	
	static final String CDOC_DOCUMENT_ADMINISTRATIVE_FORMS = "AF";
	static final String CDOC_DOCUMENT_IP_PROGRESS_NOTE = "IPR";

	//Constant for emr package privilege head...
	static final String CDOC_EMR_PACKAGE_PRIVILEGE_HEAD = "EMR-PACKAGE_";

	//Constant for giving privilege for clinical query over the forms of a package ...
	static final String CDOC_EMR_PACKAGE_QUERY_PRIVILEGE_HEAD = "CQRY-PACKAGE_";
	
	static final String CQRY_GLOBAL_SEARCH_TYPE = "GLOBALTAG";

	static final String CDOC_EMR_PRINT_PRIVILEGE_HEAD ="EMR-PRINT_";

	//Path where the annotation file stored
	//static final String CDOC_ANNOTATION_PATH = "/var/www/html/audio/annotation/xml/";

	//Constant for sound applet global configaration...
	static final String EMR_CDOC_SOUNDAPPLET_GLB_CONFIG = "EMR-DS_SHOW_RECORDING_APPLET";

	//Added SN
	static final String CDOC_ADDENDUM_OIO_REPORT = "Addendum_oio_report_v0";

	//Constants for Referral status
	//static final int EMR_REFERRAL_CREATED = 1;
	static final int EMR_REFERRAL_ACTIVE = 1;
	static final int EMR_REFERRAL_ENCOUNTERCREATED = 2;
	static final int EMR_REFERRAL_REPLIED = 3;
	static final int EMR_REFERRAL_CANCELLED = 4;

	//added by vivek
	static final int EMR_REFERRAL_FINALAPPROVE = 5;

	//Constants for GeneralMaster  category
	static final String CATEGORY_OT_SURGERY_TYPE = "OT-SURGERY_TYPE";
	static final String CATEGORY_OT_ANAESTHESIA_TYPE = "OT-ANAESTHESIA_TYPE";
	static final String CATEGORY_OT_OPTHAL_PROPOSED_TIME = "OT-OPTHAL_PROPOSED_TIME";
	static final String CATEGORY_OT_OPTHAL_TYPE_OF_OPERATION = "OT-OPTHAL_TYPE_OF_OPERATION";
	static final String CATEGORY_OT_OPTHAL_IOL_MAKE = "OT-OPTHAL_IOL_MAKE";
	static final String CATEGORY_OT_DIAGNOSIS = "OT-DIAGNOSIS";

	//Constants for Operative Note Clinical forms
	static final String OT_NOTE_GENERAL_OT_NOTES = "General_OT_Notes";
	static final String OT_NOTE_OPTHALMIC_NEW_OPERATIVE_NOTES = "Opthalmic_New_Operative_Notes";
	static final String OT_NOTE_OPTHALMIC_PREOPERATIVE_ENTRYFORMS = "Opthalmic_PreOperative_EntryForms_v0";
	static final String PRESCRIPTION_NOTE = "Prescription_Note";
	static final String CIRSAFORM = "Cirsaform_v0";
	static final String IPPROGRESSNOTEMAIN = "IP_Progress_Note_Main";

	//Key constants for setting the values to user sessions
	static final String EMR_MRDV_DOCUMENT_ID_CREATED_IN_NOTES_TAB = "EMR_MRDV_DOCUMENT_ID_CREATED_IN_NOTES_TAB";
	static final String EMR_MRDV_ANCHOR_VALUE_CREATED_IN_NOTES_TAB = "EMR_MRDV_ANCHOR_VALUE_CREATED_IN_NOTES_TAB";
	static final String EMR_MRDV_PATIENT_ID_OF_THIS_CREATED_DOCUMENT = "EMR_MRDV_PATIENT_ID_OF_THIS_CREATED_DOCUMENT";// Added by susha on 21/07/2009 ,Bug No:18154 

	//Constants for BARC Death Summary form
	static final String BARC_DEATH_SUMMARY_FORM = "BARC_Death_Summary";
	
	//privilege for telephonic encounter
	static final String EMR_TELEPHONIC_ENCOUNTER = "EMR-TELEPHONIC_ENCOUNTER";
//Merging Starts -- Prajeesh
//Taken From 407 AIMS branch	
	static final String STAFF_FUNCTION_NAME_NABL = "NABL Certified Professional";
//Merging Ends	
	
	//Constants for EMR-CLINICAL_FORM_PRIVACY_LEVEL
	static final String CLINICAL_FORM_PRIVACY_LEVEL_PUBLIC = "100";
	static final String CLINICAL_FORM_PRIVACY_LEVEL_PUBLIC_STRING = "Public";
	static final String CLINICAL_FORM_PRIVACY_LEVEL_ALL_PRIVILEGED_USERS = "50";
	static final String CLINICAL_FORM_PRIVACY_LEVEL_ALL_PRIVILEGED_USERS_STRING  = "Privileged";
	static final String CLINICAL_FORM_PRIVACY_LEVEL_OWN_PATIENTS = "10";
	static final String CLINICAL_FORM_PRIVACY_LEVEL_OWN_PATIENTS_STRING = "Private";
	
	//Constants for avoiding privacy checking 
	static final String CLINICAL_FORM_PRIVACY_WITHOUT_CHECKING = "1";
	
	//Constants for checking EMR_PATIENT_RECORD_PRIVACY_in different location
	static final String EMR_PATIENT_RECORD_PRIVACY_BY_DEPARTMENT = "Department";
	static final String EMR_PATIENT_RECORD_PRIVACY_BY_SPECIALITY = "Speciality";
	static final String EMR_PATIENT_RECORD_PRIVACY_BY_UNIT = "Unit";
	
	static final int CDOC_CHILD_OR_EMBEDEDD_FORM_YES = 1;
	
   //FROZEN DOCUMENT STATUS...
	static final int CDOC_DOCUMENT_FROZEN = 10;
	
	
   //Added by Sankaranarayanan on 10 th July 2008
   //For RIS-HIS integration
    static final int RISHIS_NOT_LINKED = 0;
    static final int RISHIS_LINKED_BY_AUTO_PROCESS = 1;
    static final int RISHIS_LINKED_BY_MANUAL= 2;
    
	//EMR Help system URL
	public static final String AHMIS_HELP_SYSTEM_URL = "/AHISHelp/ahishelp.htm";
	
	//PMT#5839: To identify whether the query specified in the clinical form control refer to clinicalDocs db. 
	static final String CLINICALDOCS_DB = "CDOC_DB.";
	//PMT#6159: To refer the his database
	static final String HIS_DB = "HIS_DB";

	static final String CUSTOM_CLINICAL_REPORT="custom-clinical-reports";


	static final int DRUG_CLASSIFICATION_MOLECULE = 4 ;
	static final int DRUG_CLASSIFICATION_GENERIC = 5 ;
	static final int DRUG_CLASSIFICATION_BRAND = 6 ;

	static final int ADVANCE_DIRECTIVE_OPEN = 1 ;
	static final int ADVANCE_DIRECTIVE_COMPLETED = 2 ; 
	static final int ADVANCE_DIRECTIVE_REVOKED = 3 ;
	static final int ADVANCE_DIRECTIVE_IMPLEMENTED = 4 ;

	static final String ALLERGY_REACTION_STATUS_ACTIVE = "ACTIVE";
	static final String ALLERGY_REACTION_STATUS_UNKNOWN = "UNKNOWN";
	static final String ALLERGY_REACTION_STATUS_CANCELLED = "CANCELLED";
	
}
