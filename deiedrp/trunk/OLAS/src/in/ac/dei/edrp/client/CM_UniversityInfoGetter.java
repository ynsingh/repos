package in.ac.dei.edrp.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CM_UniversityInfoGetter implements IsSerializable{
	
	private String universityCode;
	private String universityMaxCode;
	private Date sessionStartDate;
	private Date sessionEndDate;
	private String currentStatus;
	private String universityName;
	private String universityAddress;
	private String universityCity;
	private String universityState;
	private String universityPincode;
	private String universityPhoneNumber;
	private String universityOtherPhoneNumber;
	private String universityFaxNumber;
	private String universityInsertTime;
	private String modificationTime;
	private String universityCreatorID;
	private String modifierID;
	
	public CM_UniversityInfoGetter() {
    }

	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

	public String getUniversityCode() {
		return universityCode;
	}

	public void setSessionStartDate(Date sessionStartDate2) {
		this.sessionStartDate = sessionStartDate2;
	}

	public Date getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionEndDate(Date sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	public Date getSessionEndDate() {
		return sessionEndDate;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityAddress(String universityAddress) {
		this.universityAddress = universityAddress;
	}

	public String getUniversityAddress() {
		return universityAddress;
	}

	public void setUniversityCity(String universityCity) {
		this.universityCity = universityCity;
	}

	public String getUniversityCity() {
		return universityCity;
	}

	public void setUniversityState(String universityState) {
		this.universityState = universityState;
	}

	public String getUniversityState() {
		return universityState;
	}

	public void setUniversityPincode(String universityPincode) {
		this.universityPincode = universityPincode;
	}

	public String getUniversityPincode() {
		return universityPincode;
	}

	public void setUniversityPhoneNumber(String universityPhoneNumber) {
		this.universityPhoneNumber = universityPhoneNumber;
	}

	public String getUniversityPhoneNumber() {
		return universityPhoneNumber;
	}

	public void setUniversityOtherPhoneNumber(String universityOtherPhoneNumber) {
		this.universityOtherPhoneNumber = universityOtherPhoneNumber;
	}

	public String getUniversityOtherPhoneNumber() {
		return universityOtherPhoneNumber;
	}

	public void setUniversityFaxNumber(String universityFaxNumber) {
		this.universityFaxNumber = universityFaxNumber;
	}

	public String getUniversityFaxNumber() {
		return universityFaxNumber;
	}

	public void setUniversityInsertTime(String UniversityInsertTime) {
		this.universityInsertTime = UniversityInsertTime;
	}

	public String getUniversityInsertTime() {
		return universityInsertTime;
	}

	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}

	public String getModificationTime() {
		return modificationTime;
	}

	public void setUniversityCreatorID(String creatorID) {
		this.universityCreatorID = creatorID;
	}

	public String getUniversityCreatorID() {
		return universityCreatorID;
	}

	public void setModifierID(String modifierID) {
		this.modifierID = modifierID;
	}

	public String getModifierID() {
		return modifierID;
	}

	public void setUniversityMaxCode(String universityMaxCode) {
		this.universityMaxCode = universityMaxCode;
	}

	public String getUniversityMaxCode() {
		return universityMaxCode;
	}
	
	
}