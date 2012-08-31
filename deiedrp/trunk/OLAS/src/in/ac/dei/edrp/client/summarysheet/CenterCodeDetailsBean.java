/*
 * @(#) CenterCodeDetailsBean.java
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
package in.ac.dei.edrp.client.summarysheet;

import com.gwtext.client.widgets.form.ComboBox;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class CenterCodeDetailsBean {

	private String programId;
	private String centerCode;
	private String centerDescription;
	private ComboBox centerComboBox;
	
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
	 * @return the centerCode
	 */
	public String getCenterCode() {
		return centerCode;
	}
	/**
	 * @param centerCode the centerCode to set
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	/**
	 * @return the centerDescription
	 */
	public String getCenterDescription() {
		return centerDescription;
	}
	/**
	 * @param centerDescription the centerDescription to set
	 */
	public void setCenterDescription(String centerDescription) {
		this.centerDescription = centerDescription;
	}
	/**
	 * @return the centerComboBox
	 */
	public ComboBox getCenterComboBox() {
		return centerComboBox;
	}
	/**
	 * @param centerComboBox the centerComboBox to set
	 */
	public void setCenterComboBox(ComboBox centerComboBox) {
		this.centerComboBox = centerComboBox;
	}
	
}
