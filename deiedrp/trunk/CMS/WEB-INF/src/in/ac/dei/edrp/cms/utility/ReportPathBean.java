
/*   ReportPathBean.java
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
package in.ac.dei.edrp.cms.utility;
/**
 * This bean file is associated with
 * report path extraction
 * @author Nupur Dixit
 * @date 24 Jan 2011
 * @version 1.0
 */
public class ReportPathBean {
	private String universityId;
	private String entityId;
	private String programId;
	private String branchId;
	private String specializationId;
	private String semesterCode;
	private String semesterStartDate;
	private String semesterEndDate;
	private String enrollmentNumber;
	private String rollNumber;
	private String reportCode;
	private String reportType;
	private String reportDescription;
	private String reportPath;
	private String sessionStartDate;
	private String sessionEndDate;
	private String semesterType;
	
	public ReportPathBean() {
		System.out.println("inside default constructor of report path bean");
		this.universityId = null;
		this.entityId = null;
		this.programId = null;
		this.branchId = null;
		this.specializationId = null;
		this.semesterCode = null;
		this.semesterStartDate = null;
		this.semesterEndDate = null;
		this.enrollmentNumber = null;
		this.rollNumber = null;
		this.reportCode = null;
		this.reportType = null;	
		this.reportDescription=null;
		this.reportPath=null;
		this.sessionStartDate = null;
		this.sessionEndDate = null;	
		this.semesterType = null;
	}
	public ReportPathBean(String universityId,String entityId,String programId,String branchId,String specializationId,String semesterCode,
			String semesterStartDate,String semesterEndDate,
			String enrollmentNumber,String rollNumber,String reportCode,String reportType,
			String sessionStartDate,String sessionEndDate, String semesterType){
		this();
		this.universityId = universityId;
		this.entityId = entityId;
		this.programId = programId;
		this.branchId = branchId;
		this.specializationId = specializationId;
		this.semesterCode = semesterCode;
		this.semesterStartDate = semesterStartDate;
		this.semesterEndDate = semesterEndDate;
		this.enrollmentNumber = enrollmentNumber;
		this.rollNumber = rollNumber;
		this.reportCode = reportCode;
		this.reportType = reportType;		
		this.sessionStartDate = sessionStartDate;
		this.sessionEndDate = sessionEndDate;	
		this.semesterType = semesterType;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getSpecializationId() {
		return specializationId;
	}
	public void setSpecializationId(String specializationId) {
		this.specializationId = specializationId;
	}
	public String getSemesterCode() {
		return semesterCode;
	}
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}
	public String getSemesterStartDate() {
		return semesterStartDate;
	}
	public void setSemesterStartDate(String semesterStartDate) {
		this.semesterStartDate = semesterStartDate;
	}
	public String getSemesterEndDate() {
		return semesterEndDate;
	}
	public void setSemesterEndDate(String semesterEndDate) {
		this.semesterEndDate = semesterEndDate;
	}
	public String getEnrollmentNumber() {
		return enrollmentNumber;
	}
	public void setEnrollmentNumber(String enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	public String getSessionStartDate() {
		return sessionStartDate;
	}
	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}
	public String getSessionEndDate() {
		return sessionEndDate;
	}
	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}
	/**
	 * @param semesterType the semesterType to set
	 */
	public void setSemesterType(String semesterType) {
		this.semesterType = semesterType;
	}
	/**
	 * @return the semesterType
	 */
	public String getSemesterType() {
		return semesterType;
	}
	/**
	 * @param reportDescription the reportDescription to set
	 */
	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}
	/**
	 * @return the reportDescription
	 */
	public String getReportDescription() {
		return reportDescription;
	}	

}
