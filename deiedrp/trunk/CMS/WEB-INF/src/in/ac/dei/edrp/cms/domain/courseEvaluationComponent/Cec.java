/**
 * @(#) Cec.java
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
package in.ac.dei.edrp.cms.domain.courseEvaluationComponent;

/**
 * this is bean class for Course Evaluation Component
 * 
 * @version 1.0 7 MAY 2011
 * @author MOHD AMIR
 */
public class Cec {

	/** declaring private variables **/
	private String programid;
	private String semestercode;
	private String componentdescription;
	private String coursecode;
	private String coursename;
	private String programname;
	private String programcode;
	private String inserttime;
	private String creatorid;
	private String sessionstartdate;
	private String sessionenddate;
	private String idtype;
	private String iddescription;
	private String idtypedescription;
	private String evalgroupcode;
	private String examdate;
	private String evaluationid;
	private String groupid;
	private String rule;
	private String orderinmarksheet;
	private String maximummark;
//	private String daystodisplay;
	private String datetodisplay;
	private String datefromdisplay;
	
	public String getDatetodisplay() {
		return datetodisplay;
	}

	public void setDatetodisplay(String datetodisplay) {
		this.datetodisplay = datetodisplay;
	}

	public String getDatefromdisplay() {
		return datefromdisplay;
	}

	public void setDatefromdisplay(String datefromdisplay) {
		this.datefromdisplay = datefromdisplay;
	}

	
	private String universityId;
	private String universityname;
	private String creatorId;
	private String groupName;
	private String ruleName;
	private Integer count;

	/** defining getter and setter method for private variables **/
	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public String getSemestercode() {
		return semestercode;
	}

	public void setSemestercode(String semestercode) {
		this.semestercode = semestercode;
	}

	public String getComponentdescription() {
		return componentdescription;
	}

	public void setComponentdescription(String componentdescription) {
		this.componentdescription = componentdescription;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getProgramname() {
		return programname;
	}

	public void setProgramname(String programname) {
		this.programname = programname;
	}

	public String getProgramcode() {
		return programcode;
	}

	public void setProgramcode(String programcode) {
		this.programcode = programcode;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getSessionstartdate() {
		return sessionstartdate;
	}

	public void setSessionstartdate(String sessionstartdate) {
		this.sessionstartdate = sessionstartdate;
	}

	public String getSessionenddate() {
		return sessionenddate;
	}

	public void setSessionenddate(String sessionenddate) {
		this.sessionenddate = sessionenddate;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	public String getIddescription() {
		return iddescription;
	}

	public void setIddescription(String iddescription) {
		this.iddescription = iddescription;
	}

	public String getIdtypedescription() {
		return idtypedescription;
	}

	public void setIdtypedescription(String idtypedescription) {
		this.idtypedescription = idtypedescription;
	}

	public String getEvalgroupcode() {
		return evalgroupcode;
	}

	public void setEvalgroupcode(String evalgroupcode) {
		this.evalgroupcode = evalgroupcode;
	}

	public String getExamdate() {
		return examdate;
	}

	public void setExamdate(String examdate) {
		this.examdate = examdate;
	}

	public String getEvaluationid() {
		return evaluationid;
	}

	public void setEvaluationid(String evaluationid) {
		this.evaluationid = evaluationid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getOrderinmarksheet() {
		return orderinmarksheet;
	}

	public void setOrderinmarksheet(String orderinmarksheet) {
		this.orderinmarksheet = orderinmarksheet;
	}

	public String getMaximummark() {
		return maximummark;
	}

	public void setMaximummark(String maximummark) {
		this.maximummark = maximummark;
	}

//	public String getDaystodisplay() {
//		return daystodisplay;
//	}

//	public void setDaystodisplay(String daystodisplay) {
//		this.daystodisplay = daystodisplay;
//	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getUniversityname() {
		return universityname;
	}

	public void setUniversityname(String universityname) {
		this.universityname = universityname;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}