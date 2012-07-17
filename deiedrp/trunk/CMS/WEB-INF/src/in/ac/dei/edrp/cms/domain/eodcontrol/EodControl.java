/*
 * @(#) EodControl.java
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
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

package in.ac.dei.edrp.cms.domain.eodcontrol;

/**
 * This is the getter setter class 
 * @author Ankit Jain
 * @date 25 Jan 2011
 * @version 1.0
 */
public class EodControl {
	
	private String phase;
	private String dependentPhase;
	private String step;
	private String stepFrequencyCode;
	private String stepFrequencyDescription; 
	private String periodActiveFrom;
	private String periodActiveTo;
	private String methodToRunCode;
	private String methodToRunDescription;
	private String methodBeforeRun;
	private String status;
	private String universityId;
	private String userId;
	private String buildTime;
	private String day;
	private String mmdd;
	private String eodDate;
	private String methodStatus;
	private String eodStartTime;
	private String lastEodDate;
	private String flag;
	private String remark;
	
	
	
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

		public String getLastEodDate() {
		return lastEodDate;
	}

	public void setLastEodDate(String lastEodDate) {
		this.lastEodDate = lastEodDate;
	}

	public String getEodStartTime() {
		return eodStartTime;
	}

	public void setEodStartTime(String eodStartTime) {
		this.eodStartTime = eodStartTime;
	}

	public String getMethodStatus() {
		return methodStatus;
	}

	public void setMethodStatus(String methodStatus) {
		this.methodStatus = methodStatus;
	}

	public String getEodDate() {
		return eodDate;
	}

	public void setEodDate(String eodDate) {
		this.eodDate = eodDate;
	}

	public String getMmdd() {
		return mmdd;
	}

	public void setMmdd(String mmdd) {
		this.mmdd = mmdd;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	public String getMethodToRunCode() {
		return methodToRunCode;
	}

	public void setMethodToRunCode(String methodToRunCode) {
		this.methodToRunCode = methodToRunCode;
	}

	public String getMethodToRunDescription() {
		return methodToRunDescription;
	}

	public void setMethodToRunDescription(String methodToRunDescription) {
		this.methodToRunDescription = methodToRunDescription;
	}

	public String getMethodBeforeRun() {
		return methodBeforeRun;
	}

	public void setMethodBeforeRun(String methodBeforeRun) {
		this.methodBeforeRun = methodBeforeRun;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getStepFrequencyCode() {
		return stepFrequencyCode;
	}

	public void setStepFrequencyCode(String stepFrequencyCode) {
		this.stepFrequencyCode = stepFrequencyCode;
	}

	public String getStepFrequencyDescription() {
		return stepFrequencyDescription;
	}

	public void setStepFrequencyDescription(String stepFrequencyDescription) {
		this.stepFrequencyDescription = stepFrequencyDescription;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getDependentPhase() {
		return dependentPhase;
	}

	public void setDependentPhase(String dependentPhase) {
		this.dependentPhase = dependentPhase;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getPeriodActiveFrom() {
		return periodActiveFrom;
	}

	public void setPeriodActiveFrom(String periodActiveFrom) {
		this.periodActiveFrom = periodActiveFrom;
	}

	public String getPeriodActiveTo() {
		return periodActiveTo;
	}

	public void setPeriodActiveTo(String periodActiveTo) {
		this.periodActiveTo = periodActiveTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
