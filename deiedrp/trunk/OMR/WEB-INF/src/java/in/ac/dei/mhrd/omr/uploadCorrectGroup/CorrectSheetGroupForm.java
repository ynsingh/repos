/*
 * @(#) CorrectSheetGroupForm.java
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

package in.ac.dei.mhrd.omr.uploadCorrectGroup;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;


/**
 * Bean class for getting and setting correct Answers related data
 * @author UPASANA KULSHRESTHA
 * @date 8-12-2011
 * @version 1.0
 */

public class CorrectSheetGroupForm extends ValidatorForm {
	/*
	 * Generated fields
	 */
	/** toDate property */
	private String toDate;

	/** fromDate property */
	private String fromDate;

	/** testName property */
	private String testName;

	/** correctPath property */
	private FormFile correctPath;

	/** 
	 * Returns the toDate.
	 * @return String
	 */
	public String getToDate() {
		return toDate;
	}

	/** 
	 * Set the toDate.
	 * @param toDate The toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/** 
	 * Returns the fromDate.
	 * @return String
	 */
	public String getFromDate() {
		return fromDate;
	}

	/** 
	 * Set the fromDate.
	 * @param fromDate The fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
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

	/** 
	 * Returns the correctPath.
	 * @return FormFile
	 */
	public FormFile getCorrectPath() {
		return correctPath;
	}
	
	/** 
	 * Set the correctPath.
	 * @param correctPath The correctPath to set
	 */
	public void setCorrectPath(FormFile correctPath) {
		this.correctPath = correctPath;
	}
	
		
}
