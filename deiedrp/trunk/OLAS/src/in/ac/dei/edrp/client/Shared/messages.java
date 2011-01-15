package in.ac.dei.edrp.client.Shared;

import com.google.gwt.i18n.client.Messages;


/**
 * @author Manpreet
 */
public interface messages extends Messages {
    
	//------------------Ankit-----------------//
		String enterEnrollNumber();
	//------------------Ankit-----------------//
	
	//------------------Manpreet-----------------//
    String emptyUsernamePassword();

    String emptyUsername();

    String emptyPassword();

    String failure();

    String wrongLogin();

    String success();

    String passwordLength(int minLength);

    String error_programCode();

    String duplicateProgCode(String progCode);

    String equalMinMaxDuration();

    String greaterMaxDuration();

    String startAtleastOne();

    String branchWithSpec(String arg);

    String branchWithoutSpec(String arg);

    String chooseOneSpec(int i);

    String duplicateBranch();

    String chooseSpec();

    String duplicateSpec();

    String duplicateCategory();

    String reserveCantExceed();

    String nonZero(String arg);

    String noProgram();

    String onlyOneDelete();

    String eitherProgNameOrCode();

    String requiredCatPercent();

    String errorNoProgForNormalize();

    //------------------Manpreet-----------------//

    //------------------------Ashish
    String error();

    String alert();

    String confirm();

    String alert_confirmentries();

    String alert_successfulentry();

    String alert_sameprogram();

    String alert_onedit();

    String alert_oneditsuccess();

    String alert_ondelete();

    String alert_ondeletesuccess();

    String error_norecord(String name);

    String error_category();

    String error_sequencenumber();

    String error_duplicatesequencenumber();

    String error_duplicatecomponent();

    String error_mandatoryfields();

    String error_noprogram();

    String error_sessiondate();

    String error_gendercategory();

    String error_nodivision();

    String error_componentname();

    String error_componentorgroup();

    String error_duplicatecomponentname();

    String error_nocos();

    String error_activecos();

    String error_record();

    String error_selectrecord();

    String error_weightagevalue();

    //    String alert_nocomponent();
    String error_seatsexceed();

    //------------------------Ashish

    //**************************Added deepak
    String selectEntityName();

    String selectEntityType();

    String selectProgramName();

    String selectBranchName();

    String allselectBranch();

    String computeMarksSuccess();

    String computeMarksFailure();

    String generateTestNumberSuccess();

    String generateTestNumberFailure();

    String internalCallListXLSName(String entity_id, String program_id,
        String branch_id);

    String internalCallListFailure();

    String externalCalllistXLSName(String entity_id, String program_id,
        String branch_id);

    String externalCallListFailure();

    String finalCalllistXLSName(String entity_id, String program_id,
        String branch_id);

    String finalCallListFailure();

    String resetFisrtSuccess();

    String resetSecondSuccess();

    String resetFailure();

    //*********************************deepak ends

    /*****************************Anshika*********************************/
    String duplicateEntry();

    String checkFields();

    String fieldsReqd();

    String emptyComboBox(String s1, String s2);

    String successfullySet(String s);

    String noComponentSpecified(String s1, String s2);

    String atleastOneRecord();

    String onlyOneRecord();

    String userExistsAlert();

    String checkFormNo(int l);

    String checkRegNo(int l);

    String alreadyRegAlert(String s1, String s2);

    String duplicateRegNoAlert();

    String duplicateFormNoAlert();

    String regNotFound();

    String noCOSspecified();

    String programNotSelected();

    String select_recordsdeletion();

    String initializeRegNo();

    String yourRegNo();

    /***************************Anshika*****************************/
    
    /**********************deepak************************************/
    /****deep****/
	  String alertOnAssign();
	  String alertOnSelect();
	  String alertMentorandLocation();
	  String atleastOneRecordDelete();
	  String detailsDelete();
	  //deepak
	  String addFirstLevel();
	  String noGridCrieteria();
/**deepak add marks*****/
	  String selectFileName();
	  String successfullyAddMarks(String value1,String value2,String value3);
	  String errorInAddMarks1();
	  String errorInAddMarks2();
	  String finalListFailure();
	  /******************************deepak ends***************************/

	
    
    
    
    
    
}
