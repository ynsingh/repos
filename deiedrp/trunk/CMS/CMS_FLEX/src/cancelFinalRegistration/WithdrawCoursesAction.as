// ActionScript file
/**
 * @(#) WithdrawCoursesAction.as
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
import common.Mask;
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

[Bindable]public var params:Object={};
[Bindable]public var urlP:String;
[Bindable]public var courseXML:XML;
[Bindable]public var studentDetailsResult:XML;
[Bindable]public var courseAvailableXML:XML;
[Bindable]public var termSummaryXML:XML;
[Bindable]public var typeSummaryXML:XML;
[Bindable]public var courseAvailableArray:ArrayCollection;
[Bindable]public var courseGroup:String;
[Bindable]public var selectedData:ArrayCollection;
[Bindable]public var totalGroupCredits:Number;
[Bindable]public var minimumGroupCredits:Number;
[Bindable]public var maximumGroupCredits:Number;
[Bindable]public var totalCredits:Number;
[Bindable]public var minimumCredits:Number;
[Bindable]public var maximumCredits:Number;
[Bindable]public var minLectureCredits:Number;
[Bindable]public var maxLectureCredits:Number;
[Bindable]public var regCredits:Number;
[Bindable]public var regTCreditsExAudit:Number;
[Bindable]public var regPCreditsExAudit:Number;
[Bindable]public var regCreditsExAudit:Number;
var ce:CloseEvent;
public var semCode:String="";
public var srshStatus:String="";


public function onCreationComplete():void
{
	regRollText.setFocus();
}

public function onRegSubmit():void
{	
	dropCredit.visible=false;
	addCourseCanvas.visible=false;
	addLabel.visible=false;
	if(regRollText.text!=""){
		params['t']=new Date;
		params["rollNumber"]=regRollText.text;
			httpStudentDetails.send(params);
			Mask.show(commonFunction.getMessages('pleaseWait'));			
		}else{
			regRollText.errorString=commonFunction.getMessages('fieldIsRequired');
		}
}


private function resultHandler_StudentDetails(event:ResultEvent):void
	{
		studentDetailsResult=event.result as XML;
		Mask.close();
		if(studentDetailsResult.student.program_id.toString() != ""){
	   		populateStudentDetails(studentDetailsResult);
	   		dropCourseCanvas.visible=true;
	   		params['entity_id']=studentDetailsResult.student.entity_id;
			params['program_id']=studentDetailsResult.student.program_id;
			params['branch_code']=studentDetailsResult.student.branch_code;
			params['new_specialization']=studentDetailsResult.student.new_specialization;
			params['semester_code']=studentDetailsResult.student.semester_code;
			params['t']=new Date;
			getCourseTypeSummary.send(params);
			Mask.show("Wait Courses Coming...");
	    }
	    else{
	    	dropCourseCanvas.visible=false;
	    	Alert.show(commonFunction.getMessages('noRecordFound'), commonFunction.getMessages('info'),4, null, null, infoIcon);
	    }
}
	
	

	private function populateStudentDetails(studentDetailsResult:XML):void{
		
		semCode=studentDetailsResult.student.semester_code;
		srshStatus=studentDetailsResult.student.sequence_number;
		studentName.text=studentDetailsResult.student.student_name;
	 	fatherName.text=studentDetailsResult.student.father_name;
	 	motherName.text=studentDetailsResult.student.mother_name;
	 	dob.text=studentDetailsResult.student.date_of_birth;
	 	gender.text=studentDetailsResult.student.gender_description;
	 	category.text=studentDetailsResult.student.category_description;
	 	belongsToText.text=studentDetailsResult.student.entity_name;
	 	programNameText.text=studentDetailsResult.student.program_name;
	 	branchNameText.text=studentDetailsResult.student.branch_name;
	 	specializationText.text=studentDetailsResult.student.new_specialization_description;
	 	semesterText.text=studentDetailsResult.student.semester;
	 	rollNoText.text=regRollText.text;
	 	enrollmentNoText.text=studentDetailsResult.student.enrollment_number;
	 	attemptNoText.text=studentDetailsResult.student.attempt;
	 	
	 	/* CHECK status_in_semester for checking registration_status of student */
 		if((studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="CAN") || (studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="ACT" && srshStatus.toUpperCase()=="CAN")){
			Alert.show(commonFunction.getMessages('registrationAlreadyCanceled'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
		}
		else if(studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="INA"){
			Alert.show(commonFunction.getMessages('studentInactive'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
		}
		else if(studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="PAS"){
			Alert.show(commonFunction.getMessages('studentCompleted'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
		}
		else if(studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="FAL"){
			Alert.show(commonFunction.getMessages('studentFailed'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
		}
		else if(studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="TRM"){
			Alert.show(commonFunction.getMessages('studentTrm'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
		}
		else if(srshStatus.toUpperCase()=="WTH"){
			Alert.show(commonFunction.getMessages('studentWth')+" "+semesterText.text,commonFunction.getMessages('info'), 4, null,null,infoIcon);
		}
		else if(studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="ACT"){
			dropCourseCombo.visible=true;
			dropComboLabel.visible=true;
		}
	 	else{
	 		Alert.show(commonFunction.getMessages('programStatusOfStudent')+studentDetailsResult.student.status_in_semester.toString().toUpperCase());
		}
	}


public function typeSummarySuccess(event:ResultEvent):void
{
	typeSummaryXML=event.result as XML;
	getTermDetailsSummary.send(params);
}

public function termSummarySuccess(event:ResultEvent):void
{	
	termSummaryXML=event.result as XML;
	
	minimumCredits=termSummaryXML.credits.minimum_credit;
	maximumCredits=termSummaryXML.credits.maximum_credit;
	minLectureCredits=termSummaryXML.credits.minimum_lecture_credit;
	maxLectureCredits=termSummaryXML.credits.maximum_lecture_credit;
	params["processFlag"]='MST';
	getCourseDetails.send(params);
}

public function getStudentCourseSuccess(event:ResultEvent):void
{	
	courseXML=event.result as XML;
	totalCredits=0;
	for each(var ob:Object in courseXML.Details)
	{
		totalCredits+=Number(ob.credits.toString());
	}
	dropCourseCombo.dataProvider=courseXML.Details.courseName;
	dropCourseCombo.selectedIndex=-1;
	dropCourseCanvas.visible=true;
	dropCourseCombo.setFocus();
	Mask.close();
}

public function fault(event:FaultEvent):void
{
	Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	Mask.close();
}

public function onDropCourseSelect():void
{
	var group:String=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseGroup+"";
	dropCredit.visible=true;
	dropCredit.text="Credit: "+courseXML.Details.(courseName==dropCourseCombo.selectedLabel).credits+"   Course Code: "+courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseCode;
	totalGroupCredits=0;
	for each(var ob:Object in courseXML.Details)
	{
		if(ob.courseGroup+""==group+"")
		{
			totalGroupCredits+=Number(ob.credits.toString());
		}
	}
	
	params['t']=new Date;
	params['regNumber']=regRollText.text;
	params['programId']=studentDetailsResult.student.program_id;
	params['branchId']=studentDetailsResult.student.branch_code;
	params['specializationId']=studentDetailsResult.student.new_specialization;
	params['semesterId']=studentDetailsResult.student.semester_code;
	params['courseCode']=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseCode+"";
	
	getAvailbaleCourse.send(params);
}

public function getAvailbaleCourseSuccess(event:ResultEvent):void
{
	courseAvailableXML=event.result as XML;
	courseAvailableArray=new ArrayCollection;
	
	for each(var obj:Object in courseAvailableXML.Details)
	{	
		minimumGroupCredits=Number(obj.minimumCredits);
		maximumGroupCredits=Number(obj.maximumCredits);
		courseGroup=obj.courseGroupName;
		courseAvailableArray.addItem({select:false,courseCode:obj.courseCode,courseName:obj.courseName,
						courseType:obj.courseTypeName,credits:obj.credits,courseClass:obj.courseClass,courseCategory:obj.courseCategory});
	}
	
	if(courseAvailableArray.length==0)
	{	
		Alert.show(commonFunction.getMessages('noCourseReplacement'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}	
		minCreditLabel.text=minimumGroupCredits+"";
		maxCreditLabel.text=maximumGroupCredits+"";
		courseGroupLabel.text=courseGroup;
		addCourseGrid.dataProvider=courseAvailableArray;
		addCourseCanvas.visible=true;
		addLabel.visible=true;	
}

public function onRegCancel(event:CloseEvent):void
{
	this.parentDocument.loaderCanvas.verticalScrollPosition=0;
	regRollText.editable=true;
	regSubmitButton.enabled=true;
	dropCourseCanvas.visible=false;
	regCancelButton.enabled=false;
	addCourseCanvas.visible=false;
	regRollText.text="";
	regRollText.setFocus();
}

public function onAddDropSubmit():void
{
	selectedData=new ArrayCollection;
	selectedData=commonFunction.getSelectedRowData(addCourseGrid);
	if(validateCourseSelection())
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,AddDropOrNot,questionIcon);
	}
}

public function validateCourseSelection():Boolean
{
	var bool:Boolean=false;
	var dropCredit:Number=Number(courseXML.Details.(courseName==dropCourseCombo.selectedLabel).credits);
	var totCreditsTheoryReg:Number=0;
	var totCreditsPratReg:Number=0;
	var totCreditsTheoryAudit:Number=0;
	var totCreditsPratAudit:Number=0;
	
	for each(var obj:Object in selectedData)
	{	if(String(obj.courseClass)=='T' && String(obj.courseCategory)!='REG'){
		totCreditsTheoryAudit+=Number(obj.credits+"");
		}
		else if(String(obj.courseClass)=='T' && String(obj.courseCategory)=='REG'){
		totCreditsTheoryReg+=Number(obj.credits+"");
		}
		else if(String(obj.courseClass)=='P' && String(obj.courseCategory)!='REG'){
		totCreditsPratAudit+=Number(obj.credits+"");
		}
		else if(String(obj.courseClass)=='P' && String(obj.courseCategory)=='REG'){
		totCreditsPratReg+=Number(obj.credits+"");
		}
	}
	var totCreditAdded:Number=totCreditsTheoryAudit+totCreditsTheoryReg+totCreditsPratAudit+totCreditsPratReg;
	var egc:Number=totalGroupCredits-dropCredit+totCreditAdded;
	var ec:Number=totalCredits-dropCredit+totCreditAdded;
	
	//for registered credit
	regCredits=ec;
	
	//for registered credit without audit
	if(courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseType=="REG" ){
	regCreditsExAudit=totalCredits-dropCredit+totCreditsPratReg+totCreditsTheoryReg;
	}
	else{
	regCreditsExAudit=totalCredits+totCreditsPratReg+totCreditsTheoryReg;
	}
	
	//for registered theory credit without audit
	if((courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseType=="REG") && (courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseClass=="T"))
	{
	regTCreditsExAudit=totalCredits-dropCredit+totCreditsTheoryReg;
	}
	else{
	regTCreditsExAudit=totalCredits+totCreditsTheoryReg;
	}
	
	//for registered pratical credit without audit
	if((courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseType=="REG") && (courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseClass=="P"))
	{
	regPCreditsExAudit=totalCredits-dropCredit+totCreditsPratReg;
	}
	else{
	regPCreditsExAudit=totalCredits+totCreditsPratReg;
	}
	

	if(egc<minimumGroupCredits)
	{
		Alert.show("Total credits of courses in group "+courseGroup+" must be greater than or equal to "+minimumGroupCredits,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(egc>maximumGroupCredits)
	{
		Alert.show("Total credits of courses in group "+courseGroup+" must be less than or equal to "+maximumGroupCredits,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(ec<minimumCredits)
	{
		Alert.show("Total credits of courses of all group must be greater than or equal to "+minimumGroupCredits,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(ec>maximumCredits)
	{
		Alert.show("Total credits of course of all group must be greater than or equal to "+minimumGroupCredits,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else
	{
		bool=true;
	}
	
	return bool;
}



public function AddDropOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		params['t']=new Date;
		params['dropcourseCode']=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseCode;
		params['courseGroup']=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseGroup;
		params['courseCategory']=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseType;
		params['registerCredit']=regCredits;
		params['regTCExAudit']=regTCreditsExAudit;
		params['regPCExAudit']=regPCreditsExAudit;
		params['registerCreditExAudit']=regCreditsExAudit;
		params['entityId']=studentDetailsResult.student.entity_id
		params['semesterStartDate']=studentDetailsResult.student.session_start_date;
		params['semesterEndDate']=studentDetailsResult.student.session_end_date;
		params['courseCodes']="";
		params['courseNames']="";
		
		for each(var obj:Object in selectedData)
		{
			params['courseCodes']+=obj.courseCode+"|";
			params['courseNames']+=obj.courseName+"|";
		}
		
		addDropCourse.send(params);
	}
}

public function addDropCourseSuccess(event:ResultEvent):void
{	
	if(event.result.sessionConfirm == true)
    {
        Alert.show(resourceManager.getString('Messages','sessionInactive'));
		var url:String="";
		url=commonFunction.getConstants('navigateHome');
		var urlRequest:URLRequest=new URLRequest(url);
		urlRequest.method=URLRequestMethod.POST;
		navigateToURL(urlRequest,"_self");
    }
            
    if(event.result.info.toString()=="true"){
		Alert.show(commonFunction.getMessages('success'),commonFunction.getMessages('success'),0,null,onRegCancel,successIcon);
	}
	else if(String(event.result.info).search("empty")>-1){
		Alert.show(commonFunction.getMessages('recordNotUpdate'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	else if(String(event.result.info).search("false")>-1)
	{
	Alert.show("Error occured \n Details are as follows: \n"+String(event.result.info)  ,resourceManager.getString('Messages','error'),4,null,null,errorIcon);
    }
}
