/*
 * @(#) GroupForm.java
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

package in.ac.dei.mhrd.omr.group;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 10 Aug, 2011
 * @author Dheeraj
 * @version 1.0
 * XDoclet definition:
 * @struts.form name="selectGroup"
 */

public class GroupForm extends ActionForm{
	  
	  /**
	   * testName property
	   */
	  private String toDate;
	  
	  /**
	   * fromDate property
	   */
	  private String fromDate;
	  
	  /**
	   * toDate property
	   */
	  private String testName;

	  /** 
	   * Returns the toDate.
	   * @return String
	   */
	public String getToDate() {
		return toDate;
	}

	/** 
	 * Set the todate.
	 * @param toDate- The date to be set
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
	 * @param fromDate- The date to be set
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
	 * @param testName- The testName to be set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

}
