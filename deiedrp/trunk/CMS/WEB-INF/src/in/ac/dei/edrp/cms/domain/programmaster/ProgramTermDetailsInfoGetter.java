/*
 * @(#) ProgramTermDetailsInfoGetter.java
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
 * Redistribution in binary form must reproducuce the above copyright
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
package in.ac.dei.edrp.cms.domain.programmaster;

/**
 * Bean class for Program terms related data
 * @author Manpreet Kaur
 * @date 24-02-2011
 * @version 1.0
 */
public class ProgramTermDetailsInfoGetter {
    private String semesterCode;
    private String programId;
    private String minimumSgpa;
    private String minimumCgpa;
    private String numberOfTeachingDays;
    private String durationInWeeks;
    private String semesterStartDate;
    private String semesterEndDate;
    private String insertTime;
    private String creatorId;
    private String modifierId;
    private String semesterSequence;
    private String semesterGroup;
    private String finalSemesterCode;
    private String minimumCredit;
    private String maximumCredit;
    private String minimumLectureCredit;
    private String maximumLectureCredit;
    private String maximumCreditSpecialcase;
    private String maxSpecLectureCourse;
    private String code;
    private String name;
    private String sessionStartDate;
    private String sessionEndDate;
    private String oldSemesterStartDate;
    private String oldSemesterEndDate;
    
    
    
	/**
	 * @return the oldSemesterStartDate
	 */
	public String getOldSemesterStartDate() {
		return oldSemesterStartDate;
	}

	/**
	 * @param oldSemesterStartDate the oldSemesterStartDate to set
	 */
	public void setOldSemesterStartDate(String oldSemesterStartDate) {
		this.oldSemesterStartDate = oldSemesterStartDate;
	}

	/**
	 * @return the oldSemesterEndDate
	 */
	public String getOldSemesterEndDate() {
		return oldSemesterEndDate;
	}

	/**
	 * @param oldSemesterEndDate the oldSemesterEndDate to set
	 */
	public void setOldSemesterEndDate(String oldSemesterEndDate) {
		this.oldSemesterEndDate = oldSemesterEndDate;
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

	public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getMinimumSgpa() {
        return minimumSgpa;
    }

    public void setMinimumSgpa(String minimumSgpa) {
        this.minimumSgpa = minimumSgpa;
    }

    public String getNumberOfTeachingDays() {
        return numberOfTeachingDays;
    }

    public void setNumberOfTeachingDays(String numberOfTeachingDays) {
        this.numberOfTeachingDays = numberOfTeachingDays;
    }

    public String getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(String durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
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

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifierId() {
        return modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getSemesterSequence() {
        return semesterSequence;
    }

    public void setSemesterSequence(String semesterSequence) {
        this.semesterSequence = semesterSequence;
    }

    public String getSemesterGroup() {
        return semesterGroup;
    }

    public void setSemesterGroup(String semesterGroup) {
        this.semesterGroup = semesterGroup;
    }

    public String getFinalSemesterCode() {
        return finalSemesterCode;
    }

    public void setFinalSemesterCode(String finalSemesterCode) {
        this.finalSemesterCode = finalSemesterCode;
    }

    public String getMinimumCredit() {
        return minimumCredit;
    }

    public void setMinimumCredit(String minimumCredit) {
        this.minimumCredit = minimumCredit;
    }

    public String getMaximumCredit() {
        return maximumCredit;
    }

    public void setMaximumCredit(String maximumCredit) {
        this.maximumCredit = maximumCredit;
    }

    public String getMinimumLectureCredit() {
        return minimumLectureCredit;
    }

    public void setMinimumLectureCredit(String minimumLectureCredit) {
        this.minimumLectureCredit = minimumLectureCredit;
    }

    public String getMaximumLectureCredit() {
        return maximumLectureCredit;
    }

    public void setMaximumLectureCredit(String maximumLectureCredit) {
        this.maximumLectureCredit = maximumLectureCredit;
    }

    public String getMaximumCreditSpecialcase() {
        return maximumCreditSpecialcase;
    }

    public void setMaximumCreditSpecialcase(String maximumCreditSpecialcase) {
        this.maximumCreditSpecialcase = maximumCreditSpecialcase;
    }

    public String getMaxSpecLectureCourse() {
        return maxSpecLectureCourse;
    }

    public void setMaxSpecLectureCourse(String maxSpecLectureCourse) {
        this.maxSpecLectureCourse = maxSpecLectureCourse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinimumCgpa() {
        return minimumCgpa;
    }

    public void setMinimumCgpa(String minimumCgpa) {
        this.minimumCgpa = minimumCgpa;
    }
}
