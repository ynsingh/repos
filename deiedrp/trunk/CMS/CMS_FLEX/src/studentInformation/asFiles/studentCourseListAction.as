/**
 * @(#) studentCourseListAction.as
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
import mx.controls.Alert;
import mx.events.*;

import studentInformation.studentMarksSummary;

[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;


public function showMarks():void{
	var marksListWindow:studentMarksSummary = new studentMarksSummary();
	this.visible=false;
	this.parentDocument.loaderCanvas.addChild(marksListWindow);
	try{
	marksListWindow.programLbl.text=programLbl.text;
	marksListWindow.branchLbl.text=branchLbl.text;
	marksListWindow.specializationLbl.text=specializationLbl.text;
	marksListWindow.semesterLbl.text=semesterLbl.text;
	marksListWindow.semesterStartDateLbl.text=semesterStartDateLbl.text;
	marksListWindow.semesterEndDateLbl.text=semesterEndDateLbl.text;
	marksListWindow.courseLbl.text=courseDislpay.selectedItem.courseCode;
	marksListWindow.courseNameLbl.text=courseDislpay.selectedItem.courseName;
	marksListWindow.rollNoLbl.text=rollNumber;
	marksListWindow.programCourseKey=programCourseKey;
	}
	catch(e:Error){
		Alert.show(e+" nupur");
	}
	this.parentDocument.loaderCanvas.addChild(marksListWindow);
}

public function closePop():void{
	this.parent.getChildAt(0).visible=true;	
	this.parent.removeChild(this);
}
