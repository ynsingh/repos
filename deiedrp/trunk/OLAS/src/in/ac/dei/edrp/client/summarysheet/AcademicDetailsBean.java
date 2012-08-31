/*
 * @(#) AcademicDetailsBean.java
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

import com.google.gwt.user.client.ui.CheckBox;

import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.NumberField;

/**
 * 
 * @version 1.0 8 MAY 2012
 * @author UPASANA KULSHRESTHA
 */

public class AcademicDetailsBean {
    private String componentId;
    private String componentDescription;
    private String componentType;
    private String componentWeightage;
    private String weightageFlag;
    private String boardFlag;
    private String specialWeightageFlag;
    private String componentCriteriaFlag;
    private String ugPg;
    private String ruleNumber;
    private String sequenceNumber;
    private NumberField totalMarks;
    private NumberField marksObtained;
    private NumberField percentageScore;
    private ComboBox board;
    private CheckBox weightage;
    private String programId;
    private String entityId;
    private String programName;
    private CheckBox staffWeightage;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentWeightage() {
        return componentWeightage;
    }

    public void setComponentWeightage(String componentWeightage) {
        this.componentWeightage = componentWeightage;
    }

    public String getWeightageFlag() {
        return weightageFlag;
    }

    public void setWeightageFlag(String weightageFlag) {
        this.weightageFlag = weightageFlag;
    }

    public String getBoardFlag() {
        return boardFlag;
    }

    public void setBoardFlag(String boardFlag) {
        this.boardFlag = boardFlag;
    }

    public String getSpecialWeightageFlag() {
        return specialWeightageFlag;
    }

    public void setSpecialWeightageFlag(String specialWeightageFlag) {
        this.specialWeightageFlag = specialWeightageFlag;
    }

    public String getComponentCriteriaFlag() {
        return componentCriteriaFlag;
    }

    public void setComponentCriteriaFlag(String componentCriteriaFlag) {
        this.componentCriteriaFlag = componentCriteriaFlag;
    }

    public String getUgPg() {
        return ugPg;
    }

    public void setUgPg(String ugPg) {
        this.ugPg = ugPg;
    }

    public String getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(String ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public NumberField getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(NumberField totalMarks) {
        this.totalMarks = totalMarks;
    }

    public NumberField getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(NumberField marksObtained) {
        this.marksObtained = marksObtained;
    }

    public NumberField getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(NumberField percentageScore) {
        this.percentageScore = percentageScore;
    }

    public ComboBox getBoard() {
        return board;
    }

    public void setBoard(ComboBox board) {
        this.board = board;
    }

    public CheckBox getWeightage() {
        return weightage;
    }

    public void setWeightage(CheckBox weightage) {
        this.weightage = weightage;
    }

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public CheckBox getStaffWeightage() {
		return staffWeightage;
	}

	public void setStaffWeightage(CheckBox applicable) {
		this.staffWeightage = applicable;
	}
}
