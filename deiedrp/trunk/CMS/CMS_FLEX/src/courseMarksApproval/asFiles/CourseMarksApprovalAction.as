/**
 * @(#) CourseMarksApprovalAction.as
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
import common.*;

import flash.events.Event;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Bindable]public var urlPrefix2:String;
[Bindable]var courseList: XML;
[Bindable]var employeeList: XML;
[Bindable]var marksApprovalDetails: XML;
[Bindable]var programDetailsXml: XML;
[Bindable]var entityXml:XML;
[Bindable]var entityDataProvider:ArrayCollection;
[Bindable]var programDetails:ArrayCollection;
[Bindable]var employeeCol:ArrayCollection;
[Bindable]var selectedEmployeeCol:ArrayCollection;
[Bindable]var approvalData:ArrayCollection=new ArrayCollection();
[Bindable]var programArrcol:ArrayCollection;
[Bindable]var approverArrCol:ArrayCollection;
[Bindable]var displayArrCol:ArrayCollection;
var count:Number=0;
[Embed(source="/images/error.png")] private var errorIcon:Class;
[Embed(source="/images/success.png")] private var successIcon:Class;
[Embed(source="/images/reset.png")] private var resetIcon:Class;
[Embed(source="/images/question.png")] private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")] private var infoIcon:Class;

/** This function send request for get entity **/
public function setFirstFocous():void
{
	urlPrefix2=commonFunction.getConstants('url')+"/marksApproval/";
	getEntityList.send(new Date());
	Mask.show(commonFunction.getMessages('pleaseWait'));
}

/** Failure Handler **/
public function onFailure(event:FaultEvent):void
{
	Mask.close();
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
}

/** Method to refresh fields 
 * This method reset courseCode Combo and Employee Grid
**/
public function refresh():void
{
	courseCodeCombo.selectedIndex=-1
	courseCodeCombo.errorString="";
	courseNameField.text="";
	courseCodeCombo.enabled=false;
	employeeGrid.dataProvider=null;
	employeeGrid.dataProvider.refresh();	
	dispCombo.selectedIndex=-1;
	dispCombo.enabled=false;
	approvalOrderField.value=0;
}

/** Success handler of getCourseList service **/
public function getCourseSuccess(event:ResultEvent):void
{
	Mask.close();
	try
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
	}
 	catch(e:Error){}
    
    courseList=event.result as XML;
	
	courseCodeCombo.dataProvider=courseList.courseInfo;
	courseCodeCombo.labelField="courseCode";
	courseCodeCombo.enabled=true;
}

/** On Change of course code combo box 
 * This method send request for ger employee list
**/
protected function onCourseChange():void
{
	courseNameField.text=courseCodeCombo.selectedItem.courseName;
	getEmployeeList.send(new Date());
	refreshApprovalOrder();
	Mask.show(commonFunction.getMessages('pleaseWait'));
}

/** Success handler of getEmployeeList service **/
public function getEmployeeSuccess(event:ResultEvent):void
{
	Mask.close();
	try
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
	}
 	catch(e:Error){}
    
    employeeList=event.result as XML;
    trace(employeeList+"");
	employeeCol=new ArrayCollection;
	for each(var o:Object in employeeList.employeeInfo){
		employeeCol.addItem({select:false,entityName:o.employeeCode.@entityName,entityCode:o.employeeCode.@entityId,employeeCode:o.employeeCode,employeeName:o.employeeName});
	}
	employeeGrid.dataProvider=employeeCol;
}

/** on click of submit button 
 * This method send request for get duplicate record count list
**/
public function submitForm():void
{
	if(!validateForm())
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(selectedEmployeeCol.length==0)
	{
		Alert.show(commonFunction.getMessages('pleaseSelectEmployee'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(approvalOrderField.value==0){
		Alert.show(commonFunction.getMessages('pleaseSelectApprovalOrder'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(dispCombo.selectedIndex==-1){
		Alert.show(commonFunction.getMessages('selectDisplayType'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(count==0){
		if(approverCB.selectedIndex==-1){
			Alert.show(commonFunction.getMessages('pleaseSelectApprover'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}		
		else{
			Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
		}
	}
	else if(count==1){
		if(approvalOrderField.value>1){
			Alert.show(resourceManager.getString('Messages','cantSelectApprovalOrder',[approvalOrderField.value," 1 "]),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
		else{
			Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
		}
	}
	else{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
	}
}

/** Confirmation method for insert record
 * This method send request for insert record
 **/
public function insertOrNot(event:CloseEvent):void{
	if(event.detail==Alert.YES)
	{
		var param:Object={};
		var data:String="";
		for(var i:int=0;i<selectedEmployeeCol.length;i++){
			//data=data+selectedEmployeeCol.getItemAt(i)["employeeCode"].toString()+","+selectedEmployeeCol.getItemAt(0)["entityCode"].toString()+"|";
			data=data+selectedEmployeeCol.getItemAt(i)["employeeCode"].toString()+","+commonFunction.getSelectedRowData(ProgramDetailGrid).getItemAt(0)["entityCode"].toString()+"|";
		}
		param["courseCode"]=courseCodeCombo.selectedLabel;
		param["approvalOrder"]=approvalOrderField.value;
		param["programCourseKey"]=courseList.courseInfo.(courseCode==courseCodeCombo.selectedLabel).programCourseKey;
		param["data"]=data;
		param["display"]=dispCombo.selectedItem.evaluationId;
		param["time"]=new Date();	
		if(count==0){			
				param["sequence"]=approverCB.selectedItem.seq.toString()	
		}	
		setApprovalDetails.send(param);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

/** Success handler of setApprovalDetails service **/
public function setApprovalDetailsSuccess(event:ResultEvent):void
{
	Mask.close();
	try
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
	}
 	catch(e:Error){}
    var result:String=event.result.info.toString();
    var msg:Array=new Array;
    msg=result.split("|");
    if(msg[0]=="success"){
		Alert.show(resourceManager.getString('Messages','successOnInsertOfCourseMarksApproval',[msg[1],msg[2],msg[3],msg[4]]),commonFunction.getMessages('success'),(Alert.YES|Alert.NO),null,continueWithSameProgramConfirmation,successIcon);
	}
	else if(msg[0]=="MoreRecord"){
		Alert.show(resourceManager.getString('Messages','moreRecordForApprovalOrder2',[approvalOrderField.value.toString()]),commonFunction.getMessages('info'),0,null,null,infoIcon);
	}
	else{
		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		ProgramDetailGrid.dataProvider=null;
	    ProgramDetailGrid.dataProvider.refresh();
	    entityCombo.selectedIndex=-1;
		refresh();
		refreshApprovalOrder();
	}
			
}

/**Method to confirmation for continue with same program and course code*/
protected function continueWithSameProgramConfirmation(event:CloseEvent):void{
 	if(event.detail==Alert.YES){
 		for each(var obj:Object in employeeGrid.dataProvider){
 			obj.select=false;
 		}
 		employeeGrid.dataProvider.refresh();
 		refreshApprovalOrder();
 		dispCombo.selectedIndex=-1;
 		dispCombo.enabled=false;
 		approvalOrderField.value=0;
 	}
 	else{
 		ProgramDetailGrid.dataProvider=null;
	    ProgramDetailGrid.dataProvider.refresh();
	    entityCombo.selectedIndex=-1;
		refresh();
 		refreshApprovalOrder();
 	}
}

/** Method to validate form fields 
 * @return Boolean(ture/false)\
**/
public function validateForm():Boolean
{
	var b:Boolean=true;
	courseCodeValidator.source=courseCodeCombo;
	if(Validator.validateAll([courseCodeValidator]).length>0)
	{
		b=false;
	}
	else if(courseCodeCombo.selectedLabel==""){
		b=false;
	}
	return b;
}

/** getProgramDetails success handler
 * method to set data provider of programDetailsGrid and entity Combo
 **/
protected function httpProgramDetailsSuccess(event:ResultEvent):void
{
	Mask.close();
	try
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
	}
 	catch(e:Error){}
    programDetailsXml=event.result as XML;
    programDetails=new ArrayCollection;
    programDetails.removeAll();
		for each(var obj:Object in programDetailsXml.Details){
			if(entityCombo.selectedLabel=='All'){
	   			programDetails.addItem({select:false,entityCode:obj.entityCode,entityName:obj.entityName,
	   			programCode:obj.programCode,programName:obj.programName,branchCode:obj.branchId,branchName:obj.branchName,
	   			specializationCode:obj.specialId,specializationName:obj.specialName,semesterCode:obj.semesterCode,semesterName:obj.semesterName});
	 		 }
	 		 else{
	 		 	if(obj.entityName.toString()==entityCombo.selectedLabel.toString()){
	 		 		programDetails.addItem({select:false,entityCode:obj.entityCode,entityName:obj.entityName,
	   				programCode:obj.programCode,programName:obj.programName,branchCode:obj.branchId,branchName:obj.branchName,
	   				specializationCode:obj.specialId,specializationName:obj.specialName,semesterCode:obj.semesterCode,semesterName:obj.semesterName});
	 		 	}
	 		 }
   		}
   		ProgramDetailGrid.dataProvider=programDetails;
   		refresh();
   		refreshApprovalOrder();
}

/** getEntityList service success handler
 * method to set data provider entity Combo
 **/
protected function httpEntitySuccess(event:ResultEvent):void
{
	Mask.close();
	try
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
	}
 	catch(e:Error){}
    entityXml=event.result as XML;
    entityDataProvider=new ArrayCollection;
   entityDataProvider.addItem({entityCode:"All",entityName:"All"});
   for each(var o:Object in entityXml.Details){
   	entityDataProvider.addItem({entityCode:o.entityCode,entityName:o.entityName});
   }
    entityCombo.dataProvider=entityDataProvider;   
}

/**
 * On Change of Entity Combo
 * This Method set dataProvider of program Details Grid on basis of selected entity
 */
protected function onEntityChange(){
	var param:Object={};
	param["entity"]=entityCombo.selectedItem.entityCode;	
	param["date"]=new Date;
	getProgramDetails.send(param);
    Mask.show(commonFunction.getMessages('pleaseWait'));
	
}

/** On Select program from program detail grid 
 * This method send request for get course 
*/
public function onSelectProgram(event:Event):void{
	programArrcol=new ArrayCollection;
	var flag:int=0;//flag that check how many check box in programDetail grid is selected
	
	//Code for get Slected row from programDetails Grid on click of check box in grid
	if(event.currentTarget.selected){
		     for each(var obj:Object in ProgramDetailGrid.dataProvider)
	            {
	                if(obj.select==true)
		            {
		            	flag++;
		                var temp:Array=new Array;
		                for(var i:String in obj)
		                {
		    	            temp[i]=obj[i];
		                }
		                programArrcol.addItem(temp);
		            }
	            }		
	}
	else{
		refresh();
		refreshApprovalOrder();
	}
	if(flag>1){
		Alert.show(commonFunction.getMessages("pleaseSelectOne"),commonFunction.getMessages("info"),0,null,null,infoIcon);
		//Code to reset programDetail Grid
		for each(var obj:Object in ProgramDetailGrid.dataProvider){
	            	obj.select=false;
	     }
	     ProgramDetailGrid.dataProvider.refresh();
	     refreshApprovalOrder();
		refresh();
	}
	else if(flag==1){
		 var params:Object={};
		params["program"] = programArrcol.getItemAt(0).programCode.toString();
		params["branch"] = programArrcol.getItemAt(0).branchCode.toString();
		params["specialization"] = programArrcol.getItemAt(0).specializationCode.toString();
		params["semester"]=programArrcol.getItemAt(0).semesterCode.toString();
		params["time"]=new Date();
		getCourseList.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

/** On Select of employee in employee grid
 * This method set get selected row data and set into arraycollection
*/
public function onSelectEmployee(event:Event):void{
	selectedEmployeeCol=new ArrayCollection;
	selectedEmployeeCol.removeAll();
	var flag:int=0;//flag that check how many check box in Employee grid is selected
	if(event.currentTarget.selected){
	     for each(var obj:Object in employeeGrid.dataProvider)
            {
                if(obj.select==true)
	            {
	            	flag++;
	                var temp:Array=new Array;
	                for(var i:String in obj)
	                {
	    	            temp[i]=obj[i];
	                }
	                selectedEmployeeCol.addItem(temp);
	            }
            }
 	}
 	else{
 		selectedEmployeeCol.removeAll();
 		refreshApprovalOrder();
 		dispCombo.enabled=false;
 		dispCombo.selectedIndex=-1;
 	}
 	if(flag>1){
		Alert.show(commonFunction.getMessages("pleaseSelectOne"),commonFunction.getMessages("info"),0,null,null,infoIcon);
		//Code to reset employee Grid
		for each(var obj:Object in employeeGrid.dataProvider){
	            	obj.select=false;
	     }
	     employeeGrid.dataProvider.refresh();
		flag=0;
		selectedEmployeeCol.removeAll();
		refreshApprovalOrder();
		dispCombo.enabled=false;
		dispCombo.selectedIndex=-1;
	}
	else if(flag==1){
		dispCombo.enabled=true;
		var params:Object={};	
		params["employeeCode"]=selectedEmployeeCol.getItemAt(0)["employeeCode"].toString();
		params["entity"]=commonFunction.getSelectedRowData(ProgramDetailGrid).getItemAt(0)["entityCode"].toString();
		params["courseCode"]=courseCodeCombo.selectedLabel;
		params["programCourseKey"]=courseList.courseInfo.(courseCode==courseCodeCombo.selectedLabel).programCourseKey;
		params["date"]=new Date();
		getCourseEmployee.send(params);
	}
}


/** getCourseEmployee service success handler
 **/
protected function httpEmployeeSuccessForCourse(event:ResultEvent):void
{
	try
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
	}
 	catch(e:Error){}
 	approverArrCol=new ArrayCollection;
 	approverArrCol.addItem({seq:1,approver:"Primary"});
 	approverArrCol.addItem({seq:2,approver:"Secondary"});
 	approverArrCol.addItem({seq:3,approver:"Tertiary"});
 	approverArrCol.addItem({seq:4,approver:"Other"});
 	approverCB.dataProvider=approverArrCol;
    var courseEmployeeXml:XML=event.result as XML;
    var str:String=courseEmployeeXml.info;
    if(str=="error"){
    	Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('info'),0,null,null,errorIcon);
    	refresh();
    	refreshApprovalOrder();
    }
    else{
    	count=Number(str);
	  	if(count==0){
	   		approverCB.enabled=true;	
	  	}
	    else{
	  	 	approverCB.enabled=false;
	  	}
	  	getDisplayType.send(new Date);
    }
    
}

/**
 * On change of ApproverCB Combo Box
 * Method to check employee with selected Approver is allready inserted or not
 **/
protected function onChangeApproverCB():void{	
		if(count==0){			
			if(approvalOrderField.value==1 && dispCombo.selectedItem.evaluationId=="I"){
				approverCB.selectedIndex=-1;
				Alert.show(resourceManager.getString('Messages','cantSelectApprovalOrder',[approvalOrderField.value," Greater Than 1 "]),commonFunction.getMessages('error'),0,null,null,errorIcon);			
			}
			else{
				var param:Object={};
				param["entity"]=commonFunction.getSelectedRowData(ProgramDetailGrid).getItemAt(0)["entityCode"].toString();
				param["courseCode"]=courseCodeCombo.selectedLabel;
				param["approvalOrder"]=approvalOrderField.value;
				param["programCourseKey"]=courseList.courseInfo.(courseCode==courseCodeCombo.selectedLabel).programCourseKey;
				param["displaay"]=dispCombo.selectedItem.evaluationId;
				param["time"]=new Date();			
				param["sequence"]=approverCB.selectedItem.seq.toString();
				checkSequence.send(param);	
			}
		}
}

/** getCourseEmployee service success handler
 **/
protected function httpCheckSeqence(event:ResultEvent):void
{
	try
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
	}
 	catch(e:Error){}
 	 var sequenceXml:XML=event.result as XML;
     var info:String=sequenceXml.info;
     if(info=="alreadyExist"){
     	Alert.show(resourceManager.getString('Messages','sequenceAllreadyExist',[approvalOrderField.value]),commonFunction.getMessages('info'),0,null,null,infoIcon);
     	approverCB.selectedIndex=-1;
     }
}


/**Method to refresh Approval order combo*/
protected function refreshApprovalOrder():void{
	approverCB.selectedIndex=-1;
   	approverCB.enabled=false;
}

/** getDisplayType service success handler
 **/
protected function httpGetDisplayTypeResult(event:ResultEvent):void
{
	try
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
	}
 	catch(e:Error){}
 	var dispXml:XML=event.result as XML;
 	displayArrCol=new ArrayCollection;
 	for each(var obj:Object in dispXml.component){
 		displayArrCol.addItem({displayType:obj.displayType,evaluationId:obj.evaluationId});
 	}
 	dispCombo.dataProvider=displayArrCol;
}
