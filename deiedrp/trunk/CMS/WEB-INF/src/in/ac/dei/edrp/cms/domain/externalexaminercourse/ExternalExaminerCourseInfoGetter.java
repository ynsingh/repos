/*
 * @(#) ExternalExaminerCourseInfoGetter.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
package in.ac.dei.edrp.cms.domain.externalexaminercourse;


/**
 * This bean file is associated with
 * External Examiner Courses setup.
 * @author Ashish
 * @date 7 Mar 2011
 * @version 1.0
 */
public class ExternalExaminerCourseInfoGetter {
    private String userId;
    private String universityCode;
    private String componentId;
    private String componentDescription;
    private String externalExaminerId;
    private String externalExaminerName;
    private String courseCode;
    private String courseName;
    private String activity;
    private String parameter;
    private String creatorId;
    private String creationTime;
    private String modifierId;
    private String modificationTime;
    /**
     * Added by Mandeep
     */
    private String examinarId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String designation;
    private String department;
    private String collegeName;
    private String collegeAddress;
    private String permanentAddress;
    private String permState;
    private String permCity;
    private String pinCode;
    private String corsAddress;
    private String corsState;
    private String corsCity;
    private String corsPinCode;
    private String permanentAddressKey;
    private String coresAddresskey;
    private String officePhoneNumber;
    private String mobilePhoneNumber;
    private String faxNumber;
    private String extCode;
    private String extName;
    private String examinarCode;
    private String addressKey;
    private String address;
    private String state;
    private String city;
    private String prefix;
    private String recordArrayCol;
    /**
     * Added by Mandeep
     */   
    
    //added by ashish mohan
    private String firstDate;
    private String secondDate;
    private String thirdDate;
    private String time;
    private String secondTime;
    private String thirdTime;
    private String sessionStartDate;
    private String sessionEndDate;
    private String employeeId;
    /**
     * Added by Mandeep for pdf
     */ 
    
    private String cityPin;
    private String desgDept;
    private String Phone;
    private String fullName;
    private String programId;
    private String programCode;
    private String courseClass;


    /**
	 * @return the courseClass
	 */
	public String getCourseClass() {
		return courseClass;
	}

	/**
	 * @param courseClass the courseClass to set
	 */
	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}

	/**
	 * @return the addressKey
	 */
	public String getAddressKey() {
		return addressKey;
	}

	/**
	 * @param addressKey the addressKey to set
	 */
	public void setAddressKey(String addressKey) {
		this.addressKey = addressKey;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the extCode
	 */
	public String getExtCode() {
		return extCode;
	}

	/**
	 * @param extCode the extCode to set
	 */
	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	/**
	 * @return the extName
	 */
	public String getExtName() {
		return extName;
	}

	/**
	 * @param extName the extName to set
	 */
	public void setExtName(String extName) {
		this.extName = extName;
	}

	/**
	 * @return the officePhoneNumber
	 */
	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	/**
	 * @param officePhoneNumber the officePhoneNumber to set
	 */
	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

	/**
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * @param mobilePhoneNumber the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return the permanentAddressKey
	 */
	public String getPermanentAddressKey() {
		return permanentAddressKey;
	}

	/**
	 * @param permanentAddressKey the permanentAddressKey to set
	 */
	public void setPermanentAddressKey(String permanentAddressKey) {
		this.permanentAddressKey = permanentAddressKey;
	}

	/**
	 * @return the coresAddresskey
	 */
	public String getCoresAddresskey() {
		return coresAddresskey;
	}

	/**
	 * @param coresAddresskey the coresAddresskey to set
	 */
	public void setCoresAddresskey(String coresAddresskey) {
		this.coresAddresskey = coresAddresskey;
	}

	/**
	 * @return the permanentAddress
	 */
	public String getPermanentAddress() {
		return permanentAddress;
	}

	/**
	 * @param permanentAddress the permanentAddress to set
	 */
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	/**
	 * @return the permState
	 */
	public String getPermState() {
		return permState;
	}

	/**
	 * @param permState the permState to set
	 */
	public void setPermState(String permState) {
		this.permState = permState;
	}

	/**
	 * @return the permCity
	 */
	public String getPermCity() {
		return permCity;
	}

	/**
	 * @param permCity the permCity to set
	 */
	public void setPermCity(String permCity) {
		this.permCity = permCity;
	}

	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	/**
	 * @return the corsAddress
	 */
	public String getCorsAddress() {
		return corsAddress;
	}

	/**
	 * @param corsAddress the corsAddress to set
	 */
	public void setCorsAddress(String corsAddress) {
		this.corsAddress = corsAddress;
	}

	/**
	 * @return the corsState
	 */
	public String getCorsState() {
		return corsState;
	}

	/**
	 * @param corsState the corsState to set
	 */
	public void setCorsState(String corsState) {
		this.corsState = corsState;
	}

	/**
	 * @return the corsCity
	 */
	public String getCorsCity() {
		return corsCity;
	}

	/**
	 * @param corsCity the corsCity to set
	 */
	public void setCorsCity(String corsCity) {
		this.corsCity = corsCity;
	}

	/**
	 * @return the corsPinCode
	 */
	public String getCorsPinCode() {
		return corsPinCode;
	}

	/**
	 * @param corsPinCode the corsPinCode to set
	 */
	public void setCorsPinCode(String corsPinCode) {
		this.corsPinCode = corsPinCode;
	}

	/**
	 * @return the examinarId
	 */
	public String getExaminarId() {
		return examinarId;
	}

	/**
	 * @param examinarId the examinarId to set
	 */
	public void setExaminarId(String examinarId) {
		this.examinarId = examinarId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the collegeName
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * @param collegeName the collegeName to set
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * @return the collegeAddress
	 */
	public String getCollegeAddress() {
		return collegeAddress;
	}

	/**
	 * @param collegeAddress the collegeAddress to set
	 */
	public void setCollegeAddress(String collegeAddress) {
		this.collegeAddress = collegeAddress;
	}

	public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public String getExternalExaminerId() {
        return externalExaminerId;
    }

    public void setExternalExaminerId(String externalExaminerId) {
        this.externalExaminerId = externalExaminerId;
    }

    public String getExternalExaminerName() {
        return externalExaminerName;
    }

    public void setExternalExaminerName(String externalExaminerName) {
        this.externalExaminerName = externalExaminerName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

	/**
	 * @param examinarCode the examinarCode to set
	 */
	public void setExaminarCode(String examinarCode) {
		this.examinarCode = examinarCode;
	}

	/**
	 * @return the examinarCode
	 */
	public String getExaminarCode() {
		return examinarCode;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param recordArrayCol the recordArrayCol to set
	 */
	public void setRecordArrayCol(String recordArrayCol) {
		this.recordArrayCol = recordArrayCol;
	}
	/**
	 * @return the recordArrayCol
	 */
	public String getRecordArrayCol() {
		return recordArrayCol;
	}
	/**
	 * @return the firstDate
	 */
	public String getFirstDate() {
		return firstDate;
	}

	/**
	 * @param firstDate the firstDate to set
	 */
	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	/**
	 * @return the secondDate
	 */
	public String getSecondDate() {
		return secondDate;
	}

	/**
	 * @param secondDate the secondDate to set
	 */
	public void setSecondDate(String secondDate) {
		this.secondDate = secondDate;
	}

	/**
	 * @return the thirdDate
	 */
	public String getThirdDate() {
		return thirdDate;
	}

	/**
	 * @param thirdDate the thirdDate to set
	 */
	public void setThirdDate(String thirdDate) {
		this.thirdDate = thirdDate;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the secondTime
	 */
	public String getSecondTime() {
		return secondTime;
	}

	/**
	 * @param secondTime the secondTime to set
	 */
	public void setSecondTime(String secondTime) {
		this.secondTime = secondTime;
	}

	/**
	 * @return the thirdTime
	 */
	public String getThirdTime() {
		return thirdTime;
	}

	/**
	 * @param thirdTime the thirdTime to set
	 */
	public void setThirdTime(String thirdTime) {
		this.thirdTime = thirdTime;
	}

	/**
	 * @return the sessionStartDate
	 */
	public String getSessionStartDate() {
		return sessionStartDate;
	}

	/**
	 * @param sessionStartDate the sessionStartDate to set
	 */
	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}

	/**
	 * @return the sessionEndDate
	 */
	public String getSessionEndDate() {
		return sessionEndDate;
	}

	/**
	 * @param sessionEndDate the sessionEndDate to set
	 */
	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the cityPin
	 */
	public String getCityPin() {
		return cityPin;
	}

	/**
	 * @param cityPin the cityPin to set
	 */
	public void setCityPin(String cityPin) {
		this.cityPin = cityPin;
	}

	/**
	 * @return the desgDept
	 */
	public String getDesgDept() {
		return desgDept;
	}

	/**
	 * @param desgDept the desgDept to set
	 */
	public void setDesgDept(String desgDept) {
		this.desgDept = desgDept;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return Phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		Phone = phone;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * @param programCode the programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}


}
