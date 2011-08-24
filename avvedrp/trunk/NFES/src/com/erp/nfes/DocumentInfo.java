package com.erp.nfes;


import java.io.Serializable;
import java.util.Date;


public class DocumentInfo implements Serializable {


	private String documentId;
	private String entityId;
	private String number;
	private String amended_yesno;
	private int amended_document_id;
	private String addendum_yesno;
	private int addendum_document_id;
	private String approved_yn;
	private String id;
	private String formName;
	private String version;
	private String description;
	private String title;
	private String document_type_id;
	private String document_type_description;
	private String document_type;
	private String service_center_id;
	private String form_type;
	private String package_id;
	private String package_name;
	private Date created_date_time;
	private String created_by;
	private int user_id;
	private String user;
	private int show_draft_copy_yesno;
	private String iso_document_number;
	private Date last_modified_date_time;
	private String entity_type;
	private String last_modified_by;
	private int statusId;
	private int childYesNo;
	private String customReportName;
	
	
	
	
	
	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Date getLast_modified_date_time() {
		return last_modified_date_time;
	}

	public void setLast_modified_date_time(Date last_modified_date_time) {
		this.last_modified_date_time = last_modified_date_time;
	}

	public String getIso_document_number() {
		return iso_document_number;
	}

	public void setIso_document_number(String iso_document_number) {
		this.iso_document_number = iso_document_number;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public String getDocument_type() {
		return document_type;
	}

	/**
	 * @return
	 */
	public String getDocument_type_id() {
		return document_type_id;
	}

	/**
	 * @return
	 */
	public String getForm_type() {
		return form_type;
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
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getService_center_id() {
		return service_center_id;
	}

	/**
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param string
	 */
	public void setDocument_type(String string) {
		document_type = string;
	}

	/**
	 * @param string
	 */
	public void setDocument_type_id(String string) {
		document_type_id = string;
	}

	/**
	 * @param string
	 */
	public void setForm_type(String string) {
		form_type = string;
	}

	/**
	 * @param string
	 */
	public void setFormName(String string) {
		formName = string;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	public void setService_center_id(String string) {
		service_center_id = string;
	}

	/**
	 * @param string
	 */
	public void setVersion(String string) {
		version = string;
	}

	/**
	 * @return
	 */
	public String getAddendum_yesno() {
		return addendum_yesno;
	}

	/**
	 * @return
	 */
	public String getAmended_yesno() {
		return amended_yesno;
	}

	/**
	 * @return
	 */
	public String getApproved_yn() {
		return approved_yn;
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
	public String getNumber() {
		return number;
	}

	public int getAddendum_document_id() {
		return addendum_document_id;
	}

	public void setAddendum_document_id(int addendum_document_id) {
		this.addendum_document_id = addendum_document_id;
	}

	/**
	 * @param string
	 */
	public void setAddendum_yesno(String string) {
		addendum_yesno = string;
	}

	/**
	 * @param string
	 */
	public void setAmended_yesno(String string) {
		amended_yesno = string;
	}

	/**
	 * @param string
	 */
	public void setApproved_yn(String string) {
		approved_yn = string;
	}

	/**
	 * @param string
	 */
	public void setDocumentId(String string) {
		documentId = string;
	}


	/**
	 * @param string
	 */
	public void setNumber(String string) {
		number = string;
	}


	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

	/**
	 * @return
	 */
	public String getPackage_id() {
		return package_id;
	}

	/**
	 * @return
	 */
	public String getPackage_name() {
		return package_name;
	}

	/**
	 * @param string
	 */
	public void setPackage_id(String string) {
		package_id = string;
	}

	/**
	 * @param string
	 */
	public void setPackage_name(String string) {
		package_name = string;
	}

	/**
	 * @return
	 */
	public Date getCreated_date_time() {
		return created_date_time;
	}

	/**
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param date
	 */
	public void setCreated_date_time(Date date) {
		created_date_time = date;
	}

	/**
	 * @param string
	 */
	public void setUser(String string) {
		user = string;
	}

	/**
	 * @param i
	 */
	public void setUser_id(int i) {
		user_id = i;
	}


	public int getShow_draft_copy_yesno() {
		return show_draft_copy_yesno;
	}

	public void setShow_draft_copy_yesno(int show_draft_copy_yesno) {
		this.show_draft_copy_yesno = show_draft_copy_yesno;
	}


	public String getDocument_type_description() {
		return document_type_description;
	}

	public void setDocument_type_description(String document_type_description) {
		this.document_type_description = document_type_description;
	}

	public int getAmended_document_id() {
		return amended_document_id;
	}

	public void setAmended_document_id(int amended_document_id) {
		this.amended_document_id = amended_document_id;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getLast_modified_by() {
		return last_modified_by;
	}

	public void setLast_modified_by(String last_modified_by) {
		this.last_modified_by = last_modified_by;
	}

	public int getChildYesNo() {
		return childYesNo;
	}

	public void setChildYesNo(int childYesNo) {
		this.childYesNo = childYesNo;
	}

	public String getCustomReportName() {
		return customReportName;
	}

	public void setCustomReportName(String customReportName) {
		this.customReportName = customReportName;
	}
	
}
