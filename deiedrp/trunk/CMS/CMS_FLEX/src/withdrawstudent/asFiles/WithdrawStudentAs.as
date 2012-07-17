/**
 * @(#) WithdrawStudentAs.as
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
 
 import flash.net.URLRequest;
 
 import mx.collections.ArrayCollection;
 import mx.controls.Alert;
 import mx.events.CloseEvent;
 import mx.rpc.events.FaultEvent;
 import mx.rpc.events.ResultEvent;
 import mx.validators.Validator;
 
[Bindable] var urlPrefix:String=commonFunction.getConstants('urlCms')+"/withdrawstudent/";
[Bindable] var programDetailCol:ArrayCollection;
[Bindable] var recordArrayCol:ArrayCollection;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

 /**
 * On click of Submit button
 * Method to show grid for student
 */
protected function onClickSubmit():void{
	if(Validator.validateAll([rollVal]).length>0){
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
	}
	else{
		var param:Object={};
		param["rollNumber"]=rollnumberInput.text;
		param["date"]=new Date();
		getProgramDetailsService.send(param);
	}
}

 /**
 * On Change Of InputTextn
 */
protected function onChangeOfInputText():void{
	rollnumberInput.errorString="";
}

 /**
 * Failure handler
 **/
 protected function HttpServiceFaultHandler(event:FaultEvent):void{
 	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
 }
 
/**
 * Success handler of cancelCourseService
 **/
 protected function getProgramDetailResultHandler(event:ResultEvent):void{
 	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
		}
		
	}
 	catch(e:Error){}
 	var programXml:XML=event.result as XML;
 	programDetailCol=new ArrayCollection;
 	for each(var obj:Object in programXml.Details){
 		programDetailCol.addItem({select:false,entityId:obj.entityId,entityName:obj.entityName,programId:obj.programId,programName:obj.programName
 		,branchId:obj.branchId,branchName:obj.branchName,spclId:obj.specializationId,spclName:obj.specializationName,semesterId:obj.semesterId
 		,semesterName:obj.semesterName,semesterStartDate:obj.appliedStartDate,semesterEndDate:obj.appliedEndDate,enrollment:obj.enrollNo
 		,programCourseKey:obj.programCourseKey});
 	}
 	
 	if(programDetailCol.length>0){ 		
 		programDetailGrid.dataProvider=programDetailCol;
 		gridCanvas.visible=true;
 	}
 	else{
 		resetProgramGrid();
 		Alert.show(resourceManager.getString('Messages','noStudentFound',[rollnumberInput.text]),commonFunction.getMessages('info'),0,null,null,infoIcon);
 	}
 }
 
 /**
  * Method to reset Program Details Grid
 **/

 protected function resetProgramGrid():void{
 	programDetailGrid.dataProvider=null;
 	gridCanvas.visible=false;
 }
 
 /**
  * On click of Withraw Link
  * Method get selected row data and ask confirmation for withraw student
 **/

 protected function onClickWithrawLink():void{
 	recordArrayCol=new ArrayCollection;
 	recordArrayCol=commonFunction.getSelectedRowData(programDetailGrid);
 	if(recordArrayCol.length>0){
 		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),this,withdrawConfirm,questionIcon);
 	}
 	else{
 		Alert.show(commonFunction.getMessages('selectStudentDetail'),commonFunction.getMessages('info'),0,null,null,infoIcon);
 	}
 }
 
 /**
  * Method send request to withdraw student
 **/
 
  protected function withdrawConfirm(event:CloseEvent):void{
  	if(event.detail==Alert.YES){
  		var param:Object={};
  		param["entity"]=recordArrayCol.getItemAt(0).entityId;
  		param["programId"]=recordArrayCol.getItemAt(0).programId;
  		param["branchId"]=recordArrayCol.getItemAt(0).branchId;
  		param["spclId"]=recordArrayCol.getItemAt(0).spclId;
  		param["enrollment"]=recordArrayCol.getItemAt(0).enrollment;
  		param["programCourseKey"]=recordArrayCol.getItemAt(0).programCourseKey;
  		param["semesterStartDate"]=recordArrayCol.getItemAt(0).semesterStartDate;
  		param["semesterEndDate"]=recordArrayCol.getItemAt(0).semesterEndDate;
  		param["semesterId"]=recordArrayCol.getItemAt(0).semesterId;
  		param["rollNumber"]=rollnumberInput.text;
  		param["date"]=new Date();
  		withdrawStudentService.send(param);
  	}
  }
  
  /**
 * Success handler of withdrawStudentService
 **/
 protected function withdrawStudentResultHandler(event:ResultEvent):void{
 	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	var url:String="";
			url=commonFunction.getConstants('navigateHome');
			var urlRequest:URLRequest=new URLRequest(url);
			urlRequest.method=URLRequestMethod.POST;
			navigateToURL(urlRequest,"_self");
		}
		
	}
 	catch(e:Error){}
 	var resultXml:XML=event.result as XML;
 	var result:String=resultXml.info;
 	var resultArr:Array=result.split("-");
 	if(resultArr[0]=="success"){
 		Alert.show(commonFunction.getMessages('studentWithdrawSuccess'),commonFunction.getMessages('success'),0,null,null,successIcon);
 	}
 	else if(resultArr[0]=="cantWithDrawSrshStatusREG"){ 	
 		if(resultArr[1]=="WTH"){
 			Alert.show(commonFunction.getMessages('studentAlreadyWth'),commonFunction.getMessages('info'),0,null,null,infoIcon);
 		}
 		else{
 			Alert.show(commonFunction.getMessages('studentNotRegResultProcess'),commonFunction.getMessages('info'),0,null,null,infoIcon);
 		} 	
 	}
 	else if(resultArr[0]=="CancelRegistrationError"){
 		Alert.show(commonFunction.getMessages('errorInCancelReg'),commonFunction.getMessages('error'),0,null,null,errorIcon);
 	}
 	else if(resultArr[0]=="NoNextSemesterDateInProgramRegistrationNoYTREntrySRSH"){
 		Alert.show(commonFunction.getMessages('noNextSemDates'),commonFunction.getMessages('info'),0,null,null,infoIcon);
 	}
 	else{
 		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),0,null,null,errorIcon);
 	}
 	
 	rollnumberInput.text="";
 	programDetailGrid.dataProvider=null;
 	programDetailGrid.dataProvider.refresh();
 	gridCanvas.visible=false;
 	withrawLink.enabled=false;
 }
