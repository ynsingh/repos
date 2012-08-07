/*
 * @(#) TestSetUpForm.java
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

package in.ac.dei.mhrd.omr.testSetUp;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 09-27-2010
 * @version 1.0
 * @author Anshul Agarwal
 * XDoclet definition:
 * @struts.form name="TestSetUpForm"
 */
public class TestSetUpForm extends ValidatorForm {
	/*
	 * Generated fields
	 */
	
	private String[] sectionDetail1 = new String[4];
	private String[] sectionDetail2 = new String[4];
	private String[] sectionDetail3 = new String[4];
	private String[] sectionDetail4 = new String[4];
	
	private String existTestName;

	/** totalQues property */
	private String totalQues;

	/** totalSec property */
	private String totalSec;

	/** testName property */
	private String testName;
	
	private String sdate;
	
	private String testNo;
	private String test;

	private String sheet;
	private String groupExists;
	/*
	 * Generated Methods
	 */

	public String getTest() {
	return test;
}

public void setTest(String test) {
	this.test = test;
}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	/*
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}
*/
	/** 
	 * Returns the totalQues.
	 * @return String
	 */
	public String getTotalQues() {
		return totalQues;
	}

	/** 
	 * Set the totalQues.
	 * @param totalQues The totalQues to set
	 */
	public void setTotalQues(String totalQues) {
		this.totalQues = totalQues;
	}

	/** 
	 * Returns the totalSec.
	 * @return String
	 */
	public String getTotalSec() {
		return totalSec;
	}

	/** 
	 * Set the totalSec.
	 * @param totalSec The totalSec to set
	 */
	public void setTotalSec(String totalSec) {
		this.totalSec = totalSec;
	}

	/** 
	 * Returns the testName.
	 * @return String
	 */
	public String getTestName() {
		return testName;
	}

	/** 
	 * Set the testName.
	 * @param testName The testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}


	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}



	public String[] getSectionDetail1() {
		return sectionDetail1;
	}

	public void setSectionDetail1(String[] sectionDetail1) {
		this.sectionDetail1 = sectionDetail1;
	}

	public String[] getSectionDetail2() {
		return sectionDetail2;
	}

	public void setSectionDetail2(String[] sectionDetail2) {
		this.sectionDetail2 = sectionDetail2;
	}

	public String[] getSectionDetail3() {
		return sectionDetail3;
	}

	public void setSectionDetail3(String[] sectionDetail3) {
		this.sectionDetail3 = sectionDetail3;
	}

	public String[] getSectionDetail4() {
		return sectionDetail4;
	}

	public void setSectionDetail4(String[] sectionDetail4) {
		this.sectionDetail4 = sectionDetail4;
	}

	public String getTestNo() {
		return testNo;
	}

	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}

	public String getExistTestName() {
		return existTestName;
	}

	public void setExistTestName(String existTestName) {
		this.existTestName = existTestName;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public String getSheet() {
		return sheet;
	}

	public void setGroupExists(String groupExists) {
		this.groupExists = groupExists;
	}

	public String getGroupExists() {
		return groupExists;
	}
}