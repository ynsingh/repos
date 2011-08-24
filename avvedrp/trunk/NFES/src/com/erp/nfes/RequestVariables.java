package com.erp.nfes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class RequestVariables implements Serializable {

	//The user request parameters ....
	private String entityId;
	private String documentId;
	private String documentType;
	private String formName;
	private String formId;
	private String submitButton;
	private String action;
	private String userlogin;
	private String ammendedYesNo;
	private String addendumYesNo;
	private String parentDocumentId;
	private String surgeryPlanId;
	private ArrayList userPrivilegeList = new ArrayList ();
	private HashMap questionAnswerMap = new HashMap ();
	private String specialityDesc;
	private String formTitle;
	private String saveFormat;
	private String formType; 
	private String userDepartmentId;
	private String anchor;
	private String entityType;
	private String accreditedAagency;
	private String headerImage;
	private String footerImage;
	private String ammendedDocumentId;
	private String approvedYesNo;
	private int statusId;
	private int addendumDocumentId;
	private String number;
	private String editwithNewDocID; 
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public int getAddendumDocumentId() {
		return addendumDocumentId;
	}

	public void setAddendumDocumentId(int addendumDocumentId) {
		this.addendumDocumentId = addendumDocumentId;
	}

	public String getApprovedYesNo() {
		return approvedYesNo;
	}

	public void setApprovedYesNo(String approvedYesNo) {
		this.approvedYesNo = approvedYesNo;
	}

	public String getAmmendedDocumentId() {
		return ammendedDocumentId;
	}

	public void setAmmendedDocumentId(String ammendedDocumentId) {
		this.ammendedDocumentId = ammendedDocumentId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return
	 */
	public String getDocumentId() {
		return documentId;
	}

	/**
	 * @return
	 */
	public String getDocumentType() {
		return documentType;
	}


	/**
	 * @return
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @return
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @return
	 */
	public String getSubmitButton() {
		return submitButton;
	}

	/**
	 * @return
	 */
	public String getUserlogin() {
		return userlogin;
	}


	/**
	 * @param string
	 */
	public void setAction(String string) {
		if ( string == null )
			action = "";
		else
			action = string;
	}

	/**
	 * @param string
	 */
	public void setDocumentId(String string) {
		if ( string == null )
			documentId = "";
		else
			documentId = string;
	}

	/**
	 * @param string
	 */
	public void setDocumentType(String string) {
		if ( string == null )
			documentType = "";
		else
			documentType = string;
	}


	/**
	 * @param string
	 */
	public void setFormName(String string) {
		if ( string == null )
			formName = "";
		else
			formName = string;
	}


	/**
	 * @param string
	 */
	public void setEntityId(String string) {
		if ( string == null )
			entityId = "";
		else
			entityId = string;
	}


	/**
	 * @param string
	 */
	public void setSubmitButton(String string) {
		if ( string == null )
			submitButton = "";
		else
			submitButton = string;
	}

	/**
	 * @param string
	 */
	public void setUserlogin(String string) {
		if ( string == null )
			userlogin = "";
		else
			userlogin = string;
	}


	/**
	 * @return
	 */
	public String getParentDocumentId() {
		return parentDocumentId;
	}

	/**
	 * @param string
	 */
	public void setParentDocumentId(String string) {
		if ( string == null )
			parentDocumentId = "";
		else
			parentDocumentId = string;
	}

	/**
	 * @return
	 */
	public String getAddendumYesNo() {
		return addendumYesNo;
	}

	/**
	 * @return
	 */
	public String getAmmendedYesNo() {
		return ammendedYesNo;
	}

	/**
	 * @param string
	 */
	public void setAddendumYesNo(String string) {
		if ( string == null )
			addendumYesNo = "";
		else
			addendumYesNo = string;
	}

	/**
	 * @param string
	 */
	public void setAmmendedYesNo(String string) {
		if ( string == null )
			ammendedYesNo = "";
		else
			ammendedYesNo = string;
	}

	/**
	 * @return
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * @param string
	 */
	public void setFormId(String string) {
		formId = string;
	}

	/**
	 * @return
	 */
	public HashMap getQuestionAnswerMap() {
		return questionAnswerMap;
	}

	/**
	 * @return
	 */
	public ArrayList getUserPrivilegeList() {
		return userPrivilegeList;
	}

	/**
	 * @param map
	 */
	public void setQuestionAnswerMap(HashMap map) {
		questionAnswerMap = map;
	}

	/**
	 * @param list
	 */
	public void setUserPrivilegeList(ArrayList list) {
		userPrivilegeList = list;
	}

	/**
	 * 
	 * @return
	 */
	public String getSaveFormat() {
		return saveFormat;
	}

	/**
	 * 
	 * @param saveFormat
	 */
	public void setSaveFormat(String saveFormat) {
		this.saveFormat = saveFormat;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getSurgeryPlanId() {
		return surgeryPlanId;
	}

	public void setSurgeryPlanId(String surgeryPlanId) {
		this.surgeryPlanId = surgeryPlanId;
	}

	public String getUserDepartmentId() {
		return userDepartmentId;
	}

	public void setUserDepartmentId(String userDepartmentId) {
		this.userDepartmentId = userDepartmentId;
	}

	public String getAnchor() {
		return anchor;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}


	public String getSpecialityDesc() {
		return specialityDesc;
	}

	public void setSpecialityDesc(String specialityDesc) {
		this.specialityDesc = specialityDesc;
	}

	public String getFormTitle() {
		return formTitle;
	}

	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}

	public String getAccreditedAagency() {
		return accreditedAagency;
	}

	public void setAccreditedAagency(String accreditedAagency) {
		this.accreditedAagency = accreditedAagency;
	}

	public String getFooterImage() {
		return footerImage;
	}

	public void setFooterImage(String footerImage) {
		this.footerImage = footerImage;
	}

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}

	/*public String geteditwithNewDocID() {
		return editwithNewDocID;
	}

	public void seteditwithNewDocID(String editwithNewDocID) {
		this.number = editwithNewDocID;
	}*/

	
}
