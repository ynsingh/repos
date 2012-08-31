/*
 * @(#) AcademicDetailsNewBean.java
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

package in.ac.dei.edrp.client.EdeiAdmission;

import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;


/**
 * 
 * @version 1.0 1 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 */

public class AcademicDetailsNewBean {
	
	private String componentId;
    private String componentDescription;
    private NumberField totalMarks;
    private NumberField marksObtained;
    private NumberField percentageScore;
    private TextField degreeDescription;
    private ComboBox degreeValue;
    private ComboBox markOrGrade;
	
	/**
	 * @return the componentId
	 */
	public String getComponentId() {
		return componentId;
	}
	/**
	 * @param componentId the componentId to set
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
	 * @param componentDescription the componentDescription to set
	 */
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	/**
	 * @return the totalMarks
	 */
	public NumberField getTotalMarks() {
		return totalMarks;
	}
	/**
	 * @param totalMarks the totalMarks to set
	 */
	public void setTotalMarks(NumberField totalMarks) {
		this.totalMarks = totalMarks;
	}
	/**
	 * @return the marksObtained
	 */
	public NumberField getMarksObtained() {
		return marksObtained;
	}
	/**
	 * @param marksObtained the marksObtained to set
	 */
	public void setMarksObtained(NumberField marksObtained) {
		this.marksObtained = marksObtained;
	}
	/**
	 * @return the percentageScore
	 */
	public NumberField getPercentageScore() {
		return percentageScore;
	}
	/**
	 * @param percentageScore the percentageScore to set
	 */
	public void setPercentageScore(NumberField percentageScore) {
		this.percentageScore = percentageScore;
	}
	/**
	 * @return the degreeDescription
	 */
	public TextField getDegreeDescription() {
		return degreeDescription;
	}
	/**
	 * @param degreeDescription the degreeDescription to set
	 */
	public void setDegreeDescription(TextField degreeDescription) {
		this.degreeDescription = degreeDescription;
	}
	/**
	 * @return the degreeValue
	 */
	public ComboBox getDegreeValue() {
		return degreeValue;
	}
	/**
	 * @param degreeValue the degreeValue to set
	 */
	public void setDegreeValue(ComboBox degreeValue) {
		this.degreeValue = degreeValue;
	}
	/**
	 * @return the markOrGrade
	 */
	public ComboBox getMarkOrGrade() {
		return markOrGrade;
	}
	/**
	 * @param markOrGrade the markOrGrade to set
	 */
	public void setMarkOrGrade(ComboBox markOrGrade) {
		this.markOrGrade = markOrGrade;
	}
	

}
