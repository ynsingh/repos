/**
 * @(#) ManageprogramTermAction.as
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
// ActionScript file
import common.commonFunction;
import common.validations.CommonValidations;

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.NumberValidator;
import mx.validators.StringValidator;
import mx.validators.Validator;

import programTermDetails.ProgramTermDetails;

public var programCBValid:StringValidator = new StringValidator();
public var semesterCBValid:StringValidator = new StringValidator();
public var semesterStartDateValid:Validator =new Validator();
public var semesterEndDateValid:Validator =new Validator();
public var semesterSequenceValid:NumberValidator = new NumberValidator();
public var semesterGroupCBValid:StringValidator = new StringValidator();
//public var minSCPAValid:NumberValidator = new NumberValidator();
public var noOfTeachingDayValid:NumberValidator = new NumberValidator();

//public var duration_in_weeksValid:NumberValidator = new NumberValidator();
public var maxCreditsValid:NumberValidator = new NumberValidator();
public var minCreditsValid:NumberValidator = new NumberValidator();
public var maxLectureCreditsValid:NumberValidator = new NumberValidator();
public var minLectureCreditsValid:NumberValidator = new NumberValidator();
public var maxCreditsSpecialCaseValid:NumberValidator = new NumberValidator();
public var maxSpecialLectureCourseValid:NumberValidator = new NumberValidator();					
					
public var validate:CommonValidations=new CommonValidations();
var pt:ProgramTermDetails=new ProgramTermDetails();

[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/warning.png")]private var warningIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

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
	
	
	semesterGroupCBValid .source = semesterGroupCB ;
	semesterGroupCBValid.property = "text";
	semesterGroupCBValid.required=true;
	
	noOfTeachingDayValid .source = noOfTeachingDay ;
	noOfTeachingDayValid.property = "text";
	noOfTeachingDayValid.required=true;
	
	maxCreditsValid.source = maxCredits;
	maxCreditsValid.property = "text";
	maxCreditsValid.required=true;
	maxCreditsValid.allowNegative=false;
	maxCreditsValid.maxValue=999;
	maxCreditsValid.minValue=.50;
	maxCreditsValid.domain="real";
	maxCreditsValid.precision=2;
	
	minCreditsValid.source = minCredits;
	minCreditsValid.property = "text";
	minCreditsValid.required=true;
	minCreditsValid.allowNegative=false;
	minCreditsValid.maxValue=999
	minCreditsValid.minValue=.50;
	minCreditsValid.domain="real";
	minCreditsValid.precision=2;
	
	maxLectureCreditsValid.source = maxLectureCredits;
	maxLectureCreditsValid.property = "text";
	maxLectureCreditsValid.required=true;
	maxLectureCreditsValid.allowNegative=false;
	maxLectureCreditsValid.maxValue=999;
	maxLectureCreditsValid.minValue=.50;
	maxLectureCreditsValid.domain="real";
	maxLectureCreditsValid.precision=2;
	
	minLectureCreditsValid.source = minLectureCredits;
	minLectureCreditsValid.property = "text";
	minLectureCreditsValid.required=true;
	minLectureCreditsValid.allowNegative=false;
	minLectureCreditsValid.maxValue=999;
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
		semesterEndDateValid, semesterSequenceValid, semesterGroupCBValid, minCGPAValid,
		minSCPAValid, noOfTeachingDayValid, maxCreditsValid, minCreditsValid,	maxLectureCreditsValid,
		minLectureCreditsValid, maxCreditsSpecialCaseValid, maxSpecialLectureCourseValid]).length!=0){
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),4,null,null,errorIcon);
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
	if(semesterSequence.text != detailXML.term.semesterSequence){
	var i:String=pt.seqXML.root.(name==semesterSequence.text);
	if(i.length>0){
		semesterSequence.errorString=commonFunction.getMessages('duplicateEntry');
		flag=false;
	}
}
	
	return flag;
}

	/**
			 * @protected
			 * this method is called on update button click event.
			 * this method is used to update the data.
			 * @param event a mouse event object.
			 */
			protected function updateButtonClickHandler(event:MouseEvent):void
			{
				if(updateButton.label=="Update"){
					if(validateForm())
					{
						semesterStartDate.errorString="";
						maxCredits.errorString="";
						maxLectureCredits.errorString="";
						
						Alert.show(commonFunction.getMessages('areyousure'),
						commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);	
						
						
					}
				}
				else if(updateButton.label=="Delete"){
					if(semesterCB.selectedIndex>-1){
						semesterCB.errorString="";
						programCB.errorString="";
						
						Alert.show(commonFunction.getMessages('areyousure'),
						commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);					
						
					}else
					{
						semesterCB.errorString=commonFunction.getMessages('pleasecheckEntriesinRed');
						if(programCB.selectedIndex>-1){
							programCB.errorString=commonFunction.getMessages('pleasecheckEntriesinRed');
						}
					}
				}
			}
			
			
			public function onOK(event:CloseEvent):void{
			
			if(event.detail==Alert.YES){
				
				if(updateButton.label=="Update"){
				
						var termObj:Object=new Object();
						termObj["programId"]=progXML.root.(name==programCB.selectedLabel).code;
						termObj["semCode"]=semXML.root.(name==semesterCB.selectedLabel).code;
						termObj["startDate"]=semesterStartDate.selectedValue;
						termObj["endDate"]=semesterEndDate.selectedValue;
						termObj["semSequence"]=semesterSequence.text;
						termObj["semGroup"]=pt.semgrpXML.root.(name==semesterGroupCB.selectedLabel).code;
						if(finalSemesterCodeCB.text=="Yes"){
						termObj["finalSem"]="F";
						}else{termObj["finalSem"]="N";}
						termObj["teachingDays"]=noOfTeachingDay.text;
						termObj["duration"]=durationInWeeks.text;
						termObj["minSgpa"]=minSgpa.text;
						termObj["minCgpa"]=minCgpa.text;
						termObj["maxCredit"]=maxCredits.text;
						termObj["minCredit"]=minCredits.text;
						termObj["maxLCredit"]=maxLectureCredits.text;
						termObj["minLCredit"]=minLectureCredits.text;
						termObj["creditSplCase"]=maxCreditsSpecialCase.text;
						termObj["splLCourse"]=maxSpecialLectureCourse.text;
						termObj["userId"]=pt.pm.userId;
						updateTermDetails(termObj);
					
				}else if(updateButton.label=="Delete"){
					
						var InputObj:Object=new Object();
						InputObj["programId"]=progXML.root.(name==programCB.selectedLabel).code;
						InputObj["semCode"]=semXML.root.(name==semesterCB.selectedLabel).code.toString();
						InputObj["time"]=new Date().getTime();
						httpDeleteTerm.send(InputObj);
					
				}	
			}
			
		}
			
				/**
			 * @protected
			 * this method is called on semester comboBox item change event.
			 * @param event a ListEvent event object.
			 */
			protected function semesterCBChangeHandler(event:ListEvent):void
			{
				detailsPanel.visible=false;
				editButton.enabled=true;
				deleteButton.enabled=true;
			}
			
			/**
			 * @protected
			 * to close the form.
			 * @param event a mouse event object.
			 */
			protected function cancelButtonClickHandler(event:MouseEvent):void
			{
				this.parentDocument.loaderCanvas.removeAllChildren();
			}
			
			/**
			 * @protected
			 * this method is called on edit button click event.
			 * on method call a edit form will open.
			 * @param event a mouse event object.
			 */
			protected function editButtonClickHandler(event:MouseEvent):void
			{
				if(event.currentTarget.id=="deleteButton"){
					detailsPanel.title="Delete Mode";
					semesterStartDate.enabled=false;
					semesterEndDate.enabled=false;
					semesterSequence.editable=false;
					semesterGroupCB.enabled=false;
					finalSemesterCodeCB.enabled=false;
					noOfTeachingDay.editable=false;
					durationInWeeks.editable=false;
					minSgpa.editable=false;
					minCgpa.editable=false;
					maxCredits.editable=false;
					minCredits.editable=false;
					maxLectureCredits.editable=false;
					minLectureCredits.editable=false;
					maxCreditsSpecialCase.editable=false;
					maxSpecialLectureCourse.editable=false;
					updateButton.label="Delete";
				}
				else if(event.currentTarget.id=="editButton"){
					detailsPanel.title="Edit Mode";
					semesterStartDate.enabled=true;
					semesterEndDate.enabled=true;
					semesterSequence.editable=true;
					semesterGroupCB.enabled=true;
					finalSemesterCodeCB.enabled=true;
					noOfTeachingDay.editable=true;
					durationInWeeks.editable=true;
					minSgpa.editable=true;
					minCgpa.editable=true;
					maxCredits.editable=true;
					minCredits.editable=true;
					maxLectureCredits.editable=true;
					minLectureCredits.editable=true;
					maxCreditsSpecialCase.editable=true;
					maxSpecialLectureCourse.editable=true;
					updateButton.label="Update";
				}
				
				if(semesterCB.selectedIndex>-1){
					semesterCB.errorString="";
					programCB.errorString="";
					var InputObj:Object=new Object();
					InputObj["programId"]=progXML.root.(name==programCB.selectedLabel).code;
					InputObj["semCode"]=semXML.root.(name==semesterCB.selectedLabel).code;
					InputObj["time"]=new Date().getTime();
					httpTermDetails.send(InputObj);

					detailsPanel.visible=true;
				
					pt.httpSquenceList.url=commonFunction.getConstants('url')+ '/programtermdetails/getProgSequenceList.htm';
					pt.httpSquenceList.send(InputObj);
				}
				else{
					semesterCB.errorString=commonFunction.getMessages('pleasecheckEntriesinRed');
					if(programCB.selectedIndex>-1){
						programCB.errorString=commonFunction.getMessages('pleasecheckEntriesinRed');
					}
				}
				
			}		
			
	/**
	* @protected
    * this method is called on delete button click event.
	* this method is used to delete the records.
	* @param event a mouse event object.
	*/
	protected function deleteButtonClickHandler(event:MouseEvent):void
	{
		if(semesterCB.selectedIndex>-1){
			semesterCB.errorString="";
			programCB.errorString="";
			var InputObj:Object=new Object();
		InputObj["programId"]=progXML.root.(name==programCB.selectedLabel).code;
		InputObj["semCode"]=semXML.root.(name==semesterCB.selectedLabel).code.toString();
			InputObj["time"]=new Date().getTime();
		httpDeleteTerm.send(InputObj);
		}
	}
		

//	/**
//	* @protected
//    * this method is called on delete button click event.
//	* this method is used to delete the records.
//	* @param event a mouse event object.
//	*/
//	protected function deleteButtonClickHandler(event:MouseEvent):void
//	{
//		if(semesterCB.selectedIndex>-1){
//			semesterCB.errorString="";
//			programCB.errorString="";
//			var InputObj:Object=new Object();
//		InputObj["programId"]=progXML.root.(name==programCB.selectedLabel).code;
//		InputObj["semCode"]=semXML.root.(name==semesterCB.selectedLabel).code;
//			InputObj["time"]=new Date().getTime();
//		httpDeleteTerm.send(InputObj);
//		
//			
//		}else
//		{
//			semesterCB.errorString=commonFunction.getMessages('pleasecheckEntriesinRed');
//			if(programCB.selectedIndex>-1){
//						programCB.errorString=commonFunction.getMessages('pleasecheckEntriesinRed');
//					}
//		}
//	}
	
			
			
			/*****************getting data from server*************************/
			
[Bindable]
var progXML:XML;			
[Bindable]
var semXML:XML;		
[Bindable]
var detailXML:XML;
			
/**
 * Function for getting program list
 */
public function httpProgramList():void{
	var params:Object=new Object();
	params["userId"]=pt.pm.userId;
	params["time"]=new Date().getTime();
	httpProgList.send(params);
	
	pt.httpSemGroupList.url=commonFunction.getConstants('url')+ '/programtermdetails/getSemesterGroupList.htm';
	pt.semGroupList(params);
	pt.groupCB=semesterGroupCB;
}
private function faultHandler_ProgList(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_ProgList(event:ResultEvent):void{
	  
   progXML=event.result as XML;
if(progXML.sessionConfirm == true)
             {
            Alert.show(commonFunction.getMessages('sessionInactive')," ",4,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
            }  
  var progAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in progXML.root){
   		progAC.addItem({name:object.name,code:object.code});
   	}

 programCB.dataProvider=progXML.root;
}


	/**
	 * @protected
	 * this method is called on program comboBox item change event.
	 * @param event a ListEvent event object.
	 */
			protected function programCBChangeHandler(event:ListEvent):void
			{
				detailsPanel.visible=false;
				semesterCB.enabled=true;
				editButton.enabled=false;
				deleteButton.enabled=false;
			semList();	
			}
			
private function semList(){
	var progIdObj:Object=new Object();
				progIdObj["programId"]=progXML.root.(name==programCB.selectedLabel).code;
				progIdObj["time"]=new Date().getTime();
			httpSemList.send(progIdObj);
}
private function faultHandler_SemList(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_SemList(event:ResultEvent):void{	  
   semXML=event.result as XML;  
   if(semXML.sessionConfirm == true)
             {
            Alert.show(commonFunction.getMessages('sessionInactive')," ",4,null,null,errorIcon);
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


/**
 * Handlers for request for program term related details
 */
private function faultHandler_Details(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_Details(event:ResultEvent):void{	  
   detailXML=event.result as XML; 
   
			if(detailXML.sessionConfirm == true)
             {
            Alert.show(commonFunction.getMessages('sessionInactive')," ",4,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
            }  	
				var startDate:String=detailXML.term.semesterStartDate.toString();
				var endDate:String= detailXML.term.semesterEndDate.toString();
				
				semesterStartDate.selectedDate=int(startDate.substr(3,2));
				semesterStartDate.monthsCombo.selectedIndex=int(startDate.substr(0,2))-1;
				
				semesterEndDate.selectedDate = int(endDate.substr(3,2));
				semesterEndDate.monthsCombo.selectedIndex = int(endDate.substr(0,2))-1;
				
				semesterSequence.text = detailXML.term.semesterSequence;

				var semgrpAC:ArrayCollection=new ArrayCollection();
					var codeText:String = detailXML.term.semesterGroup;
					var codeArray:String;
				   	for each (var object:Object in pt.semgrpXML.root){
				   		semgrpAC.addItem({name:object.name,code:object.code});
				   	}
				for(var i:int=0;i<semgrpAC.length;i++){
					codeArray =semgrpAC.getItemAt(i).code; 
					if(codeArray==codeText){
						semesterGroupCB.selectedIndex=i;
						break;
					}
				}		
				if(detailXML.term.finalSemesterCode=="F"){
				finalSemesterCodeCB.selectedItem = "Yes";
				}else{
				finalSemesterCodeCB.selectedItem= "No";	
				}
				noOfTeachingDay.text = detailXML.term.numberOfTeachingDays;
				durationInWeeks.text = detailXML.term.durationInWeeks;
				minSgpa.text =detailXML.term.minimumSgpa;
				minCgpa.text =detailXML.term.minimumCgpa;
				maxCredits.text = detailXML.term.maximumCredit;
				minCredits.text = detailXML.term.minimumCredit;
				maxLectureCredits.text = detailXML.term.maximumLectureCredit;
				minLectureCredits.text = detailXML.term.minimumLectureCredit;
				maxCreditsSpecialCase.text = detailXML.term.maximumCreditSpecialcase;
				maxSpecialLectureCourse.text = detailXML.term.maxSpecLectureCourse;  
   
}


/**
 *Function for updating term details 
 */
private function updateTermDetails(params:Object):void{
		params["time"]=new Date().getTime();
	httpUpdateTerm.send(params);
}

private function faultHandler_UpdateTerm(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_UpdateTerm(event:ResultEvent):void{
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true)
             {
            Alert.show(commonFunction.getMessages('sessionInactive')," ",4,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
            }  
	if(String(result.exception.exceptionstring).search("updateError")>-1){
   mx.controls.Alert.show(result.exception.exceptionstring,commonFunction.getMessages('updateError'),4,null,null,errorIcon);

	}else if(result.exception.exceptionstring=="E"){
		
		Alert.show(commonFunction.getMessages('validateSemester'),commonFunction.getMessages("error"),4,null,null,errorIcon);
	
	}else if(result.exception.exceptionstring=="1"){
		Alert.show(commonFunction.getMessages('success'),commonFunction.getMessages('success'),0,null,null,successIcon);
		detailsPanel.visible=false;
		semesterCB.selectedIndex=-1;
		semesterCB.enabled=false;
		programCB.selectedIndex=-1;
		editButton.enabled=false;
		deleteButton.enabled=false;
		
	}
	else if(result.exception.exceptionstring=="0"){
	Alert.show(commonFunction.getMessages('noRecordUpdated'),commonFunction.getMessages("error"),4,null,null,errorIcon);	
	}
		
   }
   
   
   private function faultHandler_DeleteTerm(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,commonFunction.getMessages('error'),4,null,null,errorIcon);
    }
private function resultHandler_DeleteTerm(event:ResultEvent):void{
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive')," ",4,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
            }  
	if(String(result.exception.exceptionstring).search("F")>-1){

   mx.controls.Alert.show(result.exception.exceptionstring,commonFunction.getMessages('error'),4,null,null,errorIcon);

	}
	else if(String(result.exception.exceptionstring).search("Error")>-1){
		mx.controls.Alert.buttonWidth=100;
   		mx.controls.Alert.show(result.exception.exceptionstring,commonFunction.getMessages('error'),4,null,null,errorIcon);

	}
	else if(result.exception.exceptionstring=="1"){
		Alert.show(commonFunction.getMessages('success'),commonFunction.getMessages('success'),0,null,null,successIcon);
		detailsPanel.visible=false;
		httpProgramList();
		semList();
		semesterCB.selectedIndex=-1
		semesterCB.enabled=false;
		programCB.selectedIndex=-1;
		editButton.enabled=false;
		deleteButton.enabled=false;
	}
	else if(result.exception.exceptionstring=="0"){
		Alert.show(commonFunction.getMessages('noRecordDeleted'),commonFunction.getMessages("error"),4,null,null,errorIcon);
	}
		
   }
   
   


   
   
   
   
