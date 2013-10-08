	/**
 * @(#) CancelRegistrationAction.as
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
	
	import mx.controls.Alert;
	import mx.events.CloseEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.validators.Validator;
	
	[Embed(source="/images/error.png")]private var errorIcon:Class;
	[Embed(source="/images/success.png")]private var successIcon:Class;
	[Embed(source="/images/warning.png")]private var warningIcon:Class;
	[Embed(source="/images/question.png")]private var questionIcon:Class;
	[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
	
	[Bindable]public var studentDetailsResult:XML;

	public function populateData():void{	
		if(Validator.validateAll([regRollValidator]).length==0){
			var param:Object=new Object();
			param["roll_number"]=regRollText.text;
			httpStudentDetails.send(param);
			Mask.show(commonFunction.getMessages('pleaseWait'));			
		}else{
			regRollText.errorString=commonFunction.getMessages('fieldIsRequired');
		}
	}

	private function populateStudentDetails(studentDetailsResult:XML):void{
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
	 	if(studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="D"){
			Alert.show(commonFunction.getMessages('registrationAlreadyCanceled'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
			cancelRegistrationButton.enabled=false;
			reasonText.errorString="";
			reasonText.editable=false;
		}
		else if(studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="M"){
			Alert.show(commonFunction.getMessages('studentTransferedToMaster'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
			cancelRegistrationButton.enabled=false;
			reasonText.errorString="";
			reasonText.editable=false;
		}
		else{
			cancelRegistrationButton.enabled=true;
			reasonText.editable=true;
		}
	 	
	}

	private function faultHandler_StudentDetails(event:FaultEvent):void
	{
		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}

	private function resultHandler_StudentDetails(event:ResultEvent):void
	{
		studentDetailsResult=event.result as XML;
		Mask.close();
		if(studentDetailsResult.student.program_id.toString() != ""){
	   		populateStudentDetails(studentDetailsResult);
	   		detailsPanel.visible=true;
	    }
	    else{
	    	detailsPanel.visible=false;
	    	Alert.show(commonFunction.getMessages('noRecordFound'), commonFunction.getMessages('info'),4, null, null, infoIcon);
	    }
	    
	}

	private function cancelRegistrationAlert():void
	{
		if(Validator.validateAll([reasonValidator]).length==0){
			reasonText.errorString="";
			Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),3,this,cancelRegistration,questionIcon);
		}else{
			reasonText.errorString=commonFunction.getMessages('fieldIsRequired');
		}
	}

	private function cancelRegistration(event:CloseEvent):void{	
		if(event.detail==Alert.YES)
		{
			/* CHECK status_in_semester for checking registration_status of student */
			var param:Object=new Object();
			param["regRollNumber"]=rollNoText.text;
			param["enrolmentNumber"]=enrollmentNoText.text;
			param["reason"]=reasonText.text;
			param["studentId"]=studentDetailsResult.student.student_id;
			param["entityId"]=studentDetailsResult.student.entity_id;
			param["programId"]=studentDetailsResult.student.program_id;
			param["branchId"]=studentDetailsResult.student.branch_code;
			param["specializationId"]=studentDetailsResult.student.new_specialization;
			param["admissionMode"]=studentDetailsResult.student.admissionMode;
			param["semesterCode"]=studentDetailsResult.student.semester_code;
			if(studentDetailsResult.student.status_in_semester.toString().toUpperCase()=="G"){
				Alert.show(commonFunction.getMessages('studentEnrollmentNoGenerated'),commonFunction.getMessages('confirm'),3,this,cancelRegistration1,questionIcon);
			}
			else{
				if(reregistrationCHBox.selected==true){
					param["processedFlag"]="yes";
				}
				else{
					param["processedFlag"]="no";
				}		
			httpCancelStudentRegistration.send(param);
			}
		}	
	}

	private function cancelRegistration1(event:CloseEvent):void{	
		if(event.detail==Alert.YES){
			var param:Object=new Object();
			param["regRollNumber"]=rollNoText.text;
			param["enrolmentNumber"]=enrollmentNoText.text;
			param["reason"]=reasonText.text;
			param["studentId"]=studentDetailsResult.student.student_id;
			param["entityId"]=studentDetailsResult.student.entity_id;
			param["programId"]=studentDetailsResult.student.program_id;
			param["branchId"]=studentDetailsResult.student.branch_code;
			param["specializationId"]=studentDetailsResult.student.new_specialization;
			if(reregistrationCHBox.selected==true){
				param["processedFlag"]="yes";
			}
			else{
				param["processedFlag"]="no";
			}	
			httpCancelStudentRegistration.send(param);			
		}
	}
  
  	private function faultHandler_Cancel(event:FaultEvent):void{
		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}

	private function resultHandler_Cancel(event:ResultEvent):void{
		var resultXml:XML=event.result as XML;
		if(resultXml.sessionConfirm == true){
			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('info'), 4, null,null,warningIcon);
	        this.parentDocument.vStack.selectedIndex=0;
	        this.parentDocument.loaderCanvas.removeAllChildren();
	    }       
	    
	    regRollText.text="";
	    regRollText.errorString="";				
		
		detailsPanel.visible=false;	
		reasonText.text="";
		reasonText.editable=true;
		reregistrationCHBox.selected=false;
		if(resultXml.exception.exceptionstring=="success"){
			Alert.show(commonFunction.getMessages('registrationCanceled'),commonFunction.getMessages('success'),4,null,null,successIcon);
		}
		else{
			Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}		
	} 	