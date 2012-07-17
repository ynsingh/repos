/**
 * @(#) ProgramTermDetailValidation.as
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
import common.commonFunction;
import common.validations.CommonValidations;

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.ComboBox;
import mx.events.CloseEvent;
import mx.events.ListEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.NumberValidator;
import mx.validators.StringValidator;
import mx.validators.Validator;

import programMaster.ProgramMaster;

[Embed(source="/images/success.png")]private var successIcon:Class;	
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/warning.png")]private var warningIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var programCBValid:StringValidator = new StringValidator();
public var semesterCBValid:StringValidator = new StringValidator();
public var semesterStartDateValid:Validator =new Validator();
public var semesterEndDateValid:Validator =new Validator();
public var semesterSequenceValid:NumberValidator = new NumberValidator();
public var minSCPAValid:NumberValidator = new NumberValidator();
public var noOfTeachingDayValid:NumberValidator = new NumberValidator();
public var minCGPAValid:NumberValidator=new NumberValidator();
public var semesterGroupCBValid:StringValidator=new StringValidator();

//public var duration_in_weeksValid:NumberValidator = new NumberValidator();
public var maxCreditsValid:NumberValidator = new NumberValidator();
public var minCreditsValid:NumberValidator = new NumberValidator();
public var maxLectureCreditsValid:NumberValidator = new NumberValidator();
public var minLectureCreditsValid:NumberValidator = new NumberValidator();
public var maxCreditsSpecialCaseValid:NumberValidator = new NumberValidator();
public var maxSpecialLectureCourseValid:NumberValidator = new NumberValidator();					
					
public var validate:CommonValidations=new CommonValidations();
public var pm:ProgramMaster=new ProgramMaster();



/**
 * @protected
 * this method is called to validate the form fields.
 */
public function validateForm():Boolean
{
	var flag:Boolean=true;
	
	programCBValid.source = programCB ;
	programCBValid.property = "text";
	programCBValid.required=true;
	
	semesterCBValid.source = semesterCB ;
	semesterCBValid.property = "text";
	semesterCBValid.required=true;
	
	semesterStartDateValid.source = semesterStartDate;
	semesterStartDateValid.property = "selectedValue";
	semesterStartDateValid.required=true;
	
	semesterEndDateValid.source = semesterEndDate;
	semesterEndDateValid.property = "selectedValue";
	semesterEndDateValid.required=true;
	
	semesterSequenceValid .source = semesterSequence ;
	semesterSequenceValid.property = "text";
	semesterSequenceValid.required=true;
	
	
	semesterGroupCBValid.source = semesterGroupCB ;
	semesterGroupCBValid.property = "selectedItem";
	semesterGroupCBValid.required = true;
	
	minSCPAValid.source = minSgpa ;
	minSCPAValid.property = "text";
	minSCPAValid.required=true;
	minSCPAValid.allowNegative=false;
	minSCPAValid.maxValue=10.00;
	minSCPAValid.minValue=.50;
	minSCPAValid.domain="real";
	minSCPAValid.precision=2;
	
	minCGPAValid.source = minCgpa ;
	minCGPAValid.property = "text";
	minCGPAValid.required=true;
	minCGPAValid.allowNegative=false;
	minCGPAValid.maxValue=10.00;
	minCGPAValid.minValue=.50;
	minCGPAValid.domain="real";
	minCGPAValid.precision=2;
	
	noOfTeachingDayValid .source = noOfTeachingDay ;
	noOfTeachingDayValid.property = "text";
	noOfTeachingDayValid.required=true;
		
	maxCreditsValid.source = maxCredits;
	maxCreditsValid.property = "text";
	maxCreditsValid.required=true;
	maxCreditsValid.allowNegative=false;
	maxCreditsValid.maxValue=999.00;
	maxCreditsValid.minValue=.50;
	maxCreditsValid.domain="real";
	maxCreditsValid.precision=2;
	
	minCreditsValid.source = minCredits;
	minCreditsValid.property = "text";
	minCreditsValid.required=true;
	minCreditsValid.allowNegative=false;
	minCreditsValid.maxValue=999.00;
	minCreditsValid.minValue=.50;
	minCreditsValid.domain="real";
	minCreditsValid.precision=2;
	
	maxLectureCreditsValid.source = maxLectureCredits;
	maxLectureCreditsValid.property = "text";
	maxLectureCreditsValid.required=true;
	maxLectureCreditsValid.allowNegative=false;
	maxLectureCreditsValid.maxValue=999.00;
	maxLectureCreditsValid.minValue=.50;
	maxLectureCreditsValid.domain="real";
	maxLectureCreditsValid.precision=2;
	
	minLectureCreditsValid.source = minLectureCredits;
	minLectureCreditsValid.property = "text";
	minLectureCreditsValid.required=true;
	minLectureCreditsValid.allowNegative=false;
	minLectureCreditsValid.maxValue=999.00;
	minLectureCreditsValid.minValue=.50;
	minLectureCreditsValid.domain="real";
	minLectureCreditsValid.precision=2;
	
	maxCreditsSpecialCaseValid.source = maxCreditsSpecialCase;
	maxCreditsSpecialCaseValid.property = "text";
	maxCreditsSpecialCaseValid.required=true;
	
	maxSpecialLectureCourseValid.source = maxSpecialLectureCourse;
	maxSpecialLectureCourseValid.property = "text";
	maxSpecialLectureCourseValid.required=true;


	if(Validator.validateAll([programCBValid, semesterCBValid, semesterStartDateValid,
		semesterEndDateValid, semesterSequenceValid, semesterGroupCBValid, minSCPAValid,minCGPAValid, 
		noOfTeachingDayValid, maxCreditsValid, minCreditsValid,	maxLectureCreditsValid,
		minLectureCreditsValid, maxCreditsSpecialCaseValid, maxSpecialLectureCourseValid]).length!=0){
//		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),4,null,null,warningIcon);
		flag=false;	
	}
	if(validate.isGreater( maxCredits.text,minCredits.text))
	{
		maxCredits.errorString=commonFunction.getMessages('maxCreditValidationMessage');
		flag=false;
	}
	if(validate.isGreater( maxLectureCredits.text,minLectureCredits.text))
	{
		maxLectureCredits.errorString=commonFunction.getMessages('maxLectureCreditsValidationMessage');
		flag=false;
	}
	var i:String=seqXML.root.(name==semesterSequence.text);
	if(i.length>0){
		semesterSequence.errorString=commonFunction.getMessages('duplicateEntry');
		flag=false;
	}
	
	return flag;
}



		
			
			/**
			 * @protected
			 * this method is called on program ComboBox item change event.
			 * @param event a ListEvent event object.
			 */
			protected function programCBChangeHandler(event:ListEvent):void
			{
				semesterCB.enabled=true;
				
			}
			
			/**
			 * @protected
			 * this method is called on semester ComboBox item change event.
			 * @param event a ListEvent event object.
			 */
			protected function semesterCBChangeHandler(event:ListEvent):void
			{
				saveButton.enabled=true;
			}
			
			/**
			 * @protected
			 * to close the form.
			 * @param event a mouse event object.
			 */
			protected function resetButtonClickHandler(event:MouseEvent):void
			{
				programCB.text="";
				semesterCB.text=""
				semesterStartDate.monthsCombo.selectedIndex=-1;
				semesterStartDate.dateCombo.selectedIndex=-1;
				semesterEndDate.monthsCombo.selectedIndex=-1;
				semesterEndDate.dateCombo.selectedIndex=-1;
				semesterSequence.text="";
				
				finalSemesterCodeCB.text="";
				noOfTeachingDay.text="";
				durationInWeeks.text="";
				minSgpa.text="";
				minCgpa.text="";
				maxCredits.text="";
				minCredits.text="";
				maxLectureCredits.text="";
				minLectureCredits.text="";
				maxCreditsSpecialCase.text=""
				maxSpecialLectureCourse.text="";
				
				programCB.errorString="";
				semesterCB.errorString=""
				semesterStartDate.errorString="";
				semesterEndDate.errorString="";
				semesterSequence.errorString="";
				semesterGroupCBValid.source=null;
				semesterGroupCB.errorString="";
				finalSemesterCodeCB.errorString="";
				noOfTeachingDay.errorString="";
				durationInWeeks.errorString="";
				minSgpa.errorString="";
				minCgpa.errorString="";
				maxCredits.errorString="";
				minCredits.errorString="";
				maxLectureCredits.errorString="";
				minLectureCredits.errorString="";
				maxCreditsSpecialCase.errorString=""
				maxSpecialLectureCourse.errorString="";
				saveButton.enabled=false;
				programCB.selectedIndex=-1;
				semesterCB.selectedIndex=-1;
				semesterGroupCB.selectedIndex=-1;
			}
			
			/**
			 * @protected
			 * this method send the Form's data to server side to save.
			 * @param event a mouse event object.
			 */
			protected function saveButtonClickHandler(event:MouseEvent):void
			{
				
				if(validateForm())
				{
					Alert.show(commonFunction.getMessages('areyousure'), commonFunction.getMessages('confirm'), 3, this, saveConfirm,questionIcon);
				}
				else{
					Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),4,null,null,errorIcon);
				}
			}
			
			private function saveConfirm(event:CloseEvent):void{
				if(event.detail==Alert.YES){
					semesterStartDate.errorString="";
					maxCredits.errorString="";
					maxLectureCredits.errorString="";
					semesterGroupCB.errorString="";
				
					var termObj:Object=new Object();
					termObj["programId"]=pm.codeXML.branch.(branchName==programCB.selectedLabel).programId;
					termObj["semCode"]=semXML.root.(name==semesterCB.selectedLabel).code;
					termObj["startDate"]=semesterStartDate.selectedValue;
					termObj["endDate"]=semesterEndDate.selectedValue;
					termObj["semSequence"]=semesterSequence.text;
					termObj["semGroup"]=semgrpXML.root.(name==semesterGroupCB.selectedLabel).code;
					if(finalSemesterCodeCB.text=="Yes"){
					termObj["finalSem"]="F";
					}else{ termObj["finalSem"]="N";}
					termObj["teachingDays"]=noOfTeachingDay.text;
					if(durationInWeeks.text==""){
						termObj["duration"]=null;
					}else{
						termObj["duration"]=durationInWeeks.text;
					}
					
					termObj["minSgpa"]=minSgpa.text;
					termObj["minCgpa"]=minCgpa.text;
					termObj["maxCredit"]=maxCredits.text;
					termObj["minCredit"]=minCredits.text;
					termObj["maxLCredit"]=maxLectureCredits.text;
					termObj["minLCredit"]=minLectureCredits.text;
					termObj["creditSplCase"]=maxCreditsSpecialCase.text;
					termObj["splLCourse"]=maxSpecialLectureCourse.text;
					termObj["userId"]=pm.userId;

					addTermDetails(termObj);
				}
			}

private function loadCombo():void{
	pm.httpProgramCode.url=commonFunction.getConstants('url')+ "/programmaster/methodprogList.htm";
	pm.httpProgramCodeList();
	pm.codeListCb=programCB;
	httpSemester();
	
}

[Bindable]
private var semXML:XML;
[Bindable]
public var semgrpXML:XML;
[Bindable]
public var seqXML:XML;
public var groupCB:ComboBox=new ComboBox();

/**
 * Function for getting branch list
 */
public function httpSemester():void{
	var params:Object=new Object();
	params["userId"]=pm.userId;
		params["time"]=new Date().getTime();
	httpSemList.send(params);
semGroupList(params);

}
private function faultHandler_SemList(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_SemList(event:ResultEvent):void{
	  
   semXML=event.result as XML;
  if(semXML.sessionConfirm == true)
             {
//            Alert.show("session is inactive");
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }
  var semAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in semXML.root){
   		semAC.addItem({name:object.name,code:object.code});
   	}

 semesterCB.dataProvider=semXML.root;

   }

public function semGroupList(param:Object):void{
	param["time"]=new Date().getTime();
	httpSemGroupList.send(param);
}

private function faultHandler_SemGroup(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_SemGroup(event:ResultEvent):void{
   semgrpXML=event.result as XML;
if(semgrpXML.sessionConfirm == true)
             {
            Alert.show(commonFunction.getMessages('sessionInactive')," ",4,null,null,errorIcon);
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
  var semgrpAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in semgrpXML.root){
   		semgrpAC.addItem({name:object.name,code:object.code});
   	}
try{
 semesterGroupCB.dataProvider=semgrpXML.root;
}catch(e:Error){}
groupCB.dataProvider=semgrpXML.root;
   }


/**
 *Function for adding term details 
 */
private function addTermDetails(params:Object):void{
		params["time"]=new Date().getTime();
	httpAddTerm.send(params);
}

private function faultHandler_AddTerm(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_AddTerm(event:ResultEvent):void{
	var event1:MouseEvent;
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true)
             {
            Alert.show(commonFunction.getMessages('sessionInactive')," ",4,null,null,errorIcon);
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
            
    	if(String(result.exception.exceptionstring).search("duplicateError")>-1){
   mx.controls.Alert.show(result.exception.exceptionstring,commonFunction.getMessages('duplicateError'),4,null,null,errorIcon);

	}        
	else if(String(result.exception.exceptionstring).search("insertError")>-1){
   mx.controls.Alert.show(result.exception.exceptionstring,commonFunction.getMessages('insertError'),4,null,null,errorIcon);

	}else{
		Alert.show(commonFunction.getMessages('savedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
		semesterGroupCBValid.source=null;
		resetButtonClickHandler(event1);
	}
		
   }



/* function for getting sequence list */
private function getSequence():void{
	var params:Object=new Object();
	params["programId"]=pm.codeXML.branch.(branchName==programCB.selectedLabel).programId;
	params["time"]=new Date().getTime();
	httpSquenceList.send(params);
}

private function faultHandler_Sequence(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_Sequence(event:ResultEvent):void{
	
	seqXML=event.result as XML;
		
	if(seqXML.sessionConfirm == true)
             {
            Alert.show(commonFunction.getMessages('sessionInactive')," ",4,null,null,errorIcon);
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            }  
   }















