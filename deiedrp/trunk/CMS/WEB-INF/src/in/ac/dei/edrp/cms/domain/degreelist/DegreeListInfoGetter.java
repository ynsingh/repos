/*
 * @(#) DegreeListInfoGetter.java
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
package in.ac.dei.edrp.cms.domain.degreelist;

/**
 * This bean file is associated with
 * Degree list reports.
 * @author Ashish
 * @date 21 Aug 2011
 * @version 1.0
 */
public class DegreeListInfoGetter implements Comparable<DegreeListInfoGetter> {

	String studentName;
	String fatherName;
	String studentNameInHindi;
	String fatherNameInHindi;
	String motherNameInHindi;
	String dob;
	String cumPercentage;
	String cgpa;
	String divisionIdInTheory;
	String divisionInTheory;
	String divisionIdInPractical;
	String divisionInPractical;
	String programCode;
	String programId;
	String programName;
	String branchId;
	String branchName;
	String specializationId;
	String specializationName;
	String rollNumber;
	String courseCode;
	String courseName;
	String passedFromSession;
	String passedToSession;
	String universityCode;
	String componentId;
	String componentDescription;
	String entityId;
	String gender;
	String advanceProgramId;
	String advanceProgramName;
	String programPrintType;
	String theoryCGPA;
	String practicalCGPA;
	String theoryCWP;
	String practialCWP;
	String division;
	String resultSystem;
	int programResultSystem;
	String universityName;
	String reportType;
	String semesterCode;
	String semesterName;
	String programCourseKey;
	String semesterStartDate;
	String semesterEndDate;
	String finalSemesterCode;
	String studentStatus;
	String entityName;
	String SGPA;
	String theorySGPA;
	String practicalSGPA;
	String studentRollNumber;
	String avgsgpa;
	String entityCode;
	
	/**
	 * @return the avgsgpa
	 */
	public String getAvgsgpa() {
		return avgsgpa;
	}

	/**
	 * @param avgsgpa the avgsgpa to set
	 */
	public void setAvgsgpa(String avgsgpa) {
		this.avgsgpa = avgsgpa;
	}

	/**
	 * @return the studentRollNumber
	 */
	public String getStudentRollNumber() {
		return studentRollNumber;
	}

	/**
	 * @param studentRollNumber the studentRollNumber to set
	 */
	public void setStudentRollNumber(String studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}

	/**
	 * @return the sGPA
	 */
	public String getSGPA() {
		return SGPA;
	}

	/**
	 * @param sGPA the sGPA to set
	 */
	public void setSGPA(String sGPA) {
		SGPA = sGPA;
	}

	/**
	 * @return the theorySGPA
	 */
	public String getTheorySGPA() {
		return theorySGPA;
	}

	/**
	 * @param theorySGPA the theorySGPA to set
	 */
	public void setTheorySGPA(String theorySGPA) {
		this.theorySGPA = theorySGPA;
	}

	/**
	 * @return the practicalSGPA
	 */
	public String getPracticalSGPA() {
		return practicalSGPA;
	}

	/**
	 * @param practicalSGPA the practicalSGPA to set
	 */
	public void setPracticalSGPA(String practicalSGPA) {
		this.practicalSGPA = practicalSGPA;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * @return the studentStatus
	 */
	public String getStudentStatus() {
		return studentStatus;
	}

	/**
	 * @param studentStatus the studentStatus to set
	 */
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	/**
	 * @return the programCourseKey
	 */
	public String getProgramCourseKey() {
		return programCourseKey;
	}

	/**
	 * @param programCourseKey the programCourseKey to set
	 */
	public void setProgramCourseKey(String programCourseKey) {
		this.programCourseKey = programCourseKey;
	}

	/**
	 * @return the semesterStartDate
	 */
	public String getSemesterStartDate() {
		return semesterStartDate;
	}

	/**
	 * @param semesterStartDate the semesterStartDate to set
	 */
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}

	/**
	 * @return the semesterEndDate
	 */
	public String getSemesterEndDate() {
		return semesterEndDate;
	}

	/**
	 * @param semesterEndDate the semesterEndDate to set
	 */
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}

	/**
	 * @return the finalSemesterCode
	 */
	public String getFinalSemesterCode() {
		return finalSemesterCode;
	}

	/**
	 * @param finalSemesterCode the finalSemesterCode to set
	 */
	public void setFinalSemesterCode(String finalSemesterCode) {
		this.finalSemesterCode = finalSemesterCode;
	}

	/**
	 * @return the semesterCode
	 */
	public String getSemesterCode() {
		return semesterCode;
	}

	/**
	 * @param semesterCode the semesterCode to set
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	/**
	 * @return the semesterName
	 */
	public String getSemesterName() {
		return semesterName;
	}

	/**
	 * @param semesterName the semesterName to set
	 */
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the universityName
	 */
	public String getUniversityName() {
		return universityName;
	}

	/**
	 * @param universityName the universityName to set
	 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	/**
	 * @return the programResultSystem
	 */
	public int getProgramResultSystem() {
		return programResultSystem;
	}

	/**
	 * @param programResultSystem the programResultSystem to set
	 */
	public void setProgramResultSystem(int programResultSystem) {
		this.programResultSystem = programResultSystem;
	}

	/**
	 * @return the resultSystem
	 */
	public String getResultSystem() {
		return resultSystem;
	}

	/**
	 * @param resultSystem the resultSystem to set
	 */
	public void setResultSystem(String resultSystem) {
		this.resultSystem = resultSystem;
	}

	/**
	 * @return the theoryCGPA
	 */
	public String getTheoryCGPA() {
		return theoryCGPA;
	}

	/**
	 * @param theoryCGPA the theoryCGPA to set
	 */
	public void setTheoryCGPA(String theoryCGPA) {
		this.theoryCGPA = theoryCGPA;
	}

	/**
	 * @return the practicalCGPA
	 */
	public String getPracticalCGPA() {
		return practicalCGPA;
	}

	/**
	 * @param practicalCGPA the practicalCGPA to set
	 */
	public void setPracticalCGPA(String practicalCGPA) {
		this.practicalCGPA = practicalCGPA;
	}

	/**
	 * @return the theoryCWP
	 */
	public String getTheoryCWP() {
		return theoryCWP;
	}

	/**
	 * @param theoryCWP the theoryCWP to set
	 */
	public void setTheoryCWP(String theoryCWP) {
		this.theoryCWP = theoryCWP;
	}

	/**
	 * @return the practialCWP
	 */
	public String getPractialCWP() {
		return practialCWP;
	}

	/**
	 * @param practialCWP the practialCWP to set
	 */
	public void setPractialCWP(String practialCWP) {
		this.practialCWP = practialCWP;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * @return the programPrintType
	 */
	public String getProgramPrintType() {
		return programPrintType;
	}

	/**
	 * @param programPrintType the programPrintType to set
	 */
	public void setProgramPrintType(String programPrintType) {
		this.programPrintType = programPrintType;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName
	 *            the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @param fatherName
	 *            the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @return the studentNameInHindi
	 */
	public String getStudentNameInHindi() {
		return studentNameInHindi;
	}

	/**
	 * @param studentNameInHindi
	 *            the studentNameInHindi to set
	 */
	public void setStudentNameInHindi(String studentNameInHindi) {
		this.studentNameInHindi = studentNameInHindi;
	}

	/**
	 * @return the fatherNameInHindi
	 */
	public String getFatherNameInHindi() {
		return fatherNameInHindi;
	}

	/**
	 * @param fatherNameInHindi
	 *            the fatherNameInHindi to set
	 */
	public void setFatherNameInHindi(String fatherNameInHindi) {
		this.fatherNameInHindi = fatherNameInHindi;
	}

	/**
	 * @return the motherNameInHindi
	 */
	public String getMotherNameInHindi() {
		return motherNameInHindi;
	}

	/**
	 * @param motherNameInHindi
	 *            the motherNameInHindi to set
	 */
	public void setMotherNameInHindi(String motherNameInHindi) {
		this.motherNameInHindi = motherNameInHindi;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the cumPercentage
	 */
	public String getCumPercentage() {
		return cumPercentage;
	}

	/**
	 * @param cumPercentage
	 *            the cumPercentage to set
	 */
	public void setCumPercentage(String cumPercentage) {
		this.cumPercentage = cumPercentage;
	}

	/**
	 * @return the cgpa
	 */
	public String getCgpa() {
		return cgpa;
	}

	/**
	 * @param cgpa
	 *            the cgpa to set
	 */
	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}

	/**
	 * @return the divisionIdInTheory
	 */
	public String getDivisionIdInTheory() {
		return divisionIdInTheory;
	}

	/**
	 * @param divisionIdInTheory
	 *            the divisionIdInTheory to set
	 */
	public void setDivisionIdInTheory(String divisionIdInTheory) {
		this.divisionIdInTheory = divisionIdInTheory;
	}

	/**
	 * @return the divisionInTheory
	 */
	public String getDivisionInTheory() {
		return divisionInTheory;
	}

	/**
	 * @param divisionInTheory
	 *            the divisionInTheory to set
	 */
	public void setDivisionInTheory(String divisionInTheory) {
		this.divisionInTheory = divisionInTheory;
	}

	/**
	 * @return the divisionIdInPractical
	 */
	public String getDivisionIdInPractical() {
		return divisionIdInPractical;
	}

	/**
	 * @param divisionIdInPractical
	 *            the divisionIdInPractical to set
	 */
	public void setDivisionIdInPractical(String divisionIdInPractical) {
		this.divisionIdInPractical = divisionIdInPractical;
	}

	/**
	 * @return the divisionInPractical
	 */
	public String getDivisionInPractical() {
		return divisionInPractical;
	}

	/**
	 * @param divisionInPractical
	 *            the divisionInPractical to set
	 */
	public void setDivisionInPractical(String divisionInPractical) {
		this.divisionInPractical = divisionInPractical;
	}

	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * @param programCode
	 *            the programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param programName
	 *            the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}

	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId
	 *            the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName
	 *            the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the specializationId
	 */
	public String getSpecializationId() {
		return specializationId;
	}

	/**
	 * @param specializationId
	 *            the specializationId to set
	 */
	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}

	/**
	 * @return the specializationName
	 */
	public String getSpecializationName() {
		return specializationName;
	}

	/**
	 * @param specializationName
	 *            the specializationName to set
	 */
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	/**
	 * @return the rollNumber
	 */
	public String getRollNumber() {
		return rollNumber;
	}

	/**
	 * @param rollNumber
	 *            the rollNumber to set
	 */
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * @param courseCode
	 *            the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName
	 *            the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the passedFromSession
	 */
	public String getPassedFromSession() {
		return passedFromSession;
	}

	/**
	 * @param passedFromSession
	 *            the passedFromSession to set
	 */
	public void setPassedFromSession(String passedFromSession) {
		this.passedFromSession = passedFromSession;
	}

	/**
	 * @return the passedToSession
	 */
	public String getPassedToSession() {
		return passedToSession;
	}

	/**
	 * @param passedToSession
	 *            the passedToSession to set
	 */
	public void setPassedToSession(String passedToSession) {
		this.passedToSession = passedToSession;
	}

	/**
	 * @return the universityCode
	 */
	public String getUniversityCode() {
		return universityCode;
	}

	/**
	 * @param universityCode
	 *            the universityCode to set
	 */
	public void setUniversityCode(String universityCode) {
		this.universityCode = universityCode;
	}

	/**
	 * @return the componentId
	 */
	public String getComponentId() {
		return componentId;
	}

	/**
	 * @param componentId
	 *            the componentId to set
	 */
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	/**
	 * @return the componentDescription
	 */
	public String getComponentDescription() {
		return componentDescription;
	}

	/**
	 * @param componentDescription
	 *            the componentDescription to set
	 */
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId
	 *            the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the advanceProgramId
	 */
	public String getAdvanceProgramId() {
		return advanceProgramId;
	}

	/**
	 * @param advanceProgramId
	 *            the advanceProgramId to set
	 */
	public void setAdvanceProgramId(String advanceProgramId) {
		this.advanceProgramId = advanceProgramId;
	}

	/**
	 * @return the advanceProgramName
	 */
	public String getAdvanceProgramName() {
		return advanceProgramName;
	}

	/**
	 * @param advanceProgramName
	 *            the advanceProgramName to set
	 */
	public void setAdvanceProgramName(String advanceProgramName) {
		this.advanceProgramName = advanceProgramName;
	}
	
	public String getEntityCode(){
		return entityCode;
	}
	public void setEntityCode(String entityCode){
		this.entityCode = entityCode;
	}

	/**
	 * Compare a given roll number with this object. If roll number of this
	 * object is greater than the received object, then this object is greater
	 * than the other.
	 */
	public int compareTo(DegreeListInfoGetter o) {		

		return Integer.parseInt(this.rollNumber)
				- Integer.parseInt(o.rollNumber);

	}

}
