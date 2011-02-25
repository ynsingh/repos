/*
 * @(#) UploadResponseSheetForm.java
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

package in.ac.dei.mhrd.omr.uploadSheet;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-18-2010
 * @version 1.0
 * @author Anshul Agarwal
 * XDoclet definition:
 * @struts.form name="uploadResponseSheet"
 *  
 */
public class UploadResponseSheetForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/** zippedFolder property */
	private FormFile zippedFolder;

	/** testName property */
	private String testName;
	
	private String fromDate;
	private String toDate;

	/*
	 * Generated Methods
	 */

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	/*public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}*/

	/** 
	 * Returns the zippedFolder.
	 * @return String
	 */
	public FormFile getZippedFolder() {
		return zippedFolder;
	}

	/** 
	 * Set the zippedFolder.
	 * @param zippedFolder The zippedFolder to set
	 */
	public void setZippedFolder(FormFile zippedFolder) {
		this.zippedFolder = zippedFolder;
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
	 * @param testname The testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}
}