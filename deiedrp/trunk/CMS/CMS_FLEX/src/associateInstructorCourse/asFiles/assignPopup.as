/**
 * @(#) assignPopup.as
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

import mx.collections.*;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Embed(source="/images/question.png")]private var questionIcon:Class;	
[Embed(source="/images/success.png")]private var successIcon:Class;	
[Embed(source="/images/error.png")]private var errorIcon:Class;
public var showCodes:String;


[Bindable]protected var urlPrefix:String; 
protected var employeeList:XML;
public var entityId:String; 
public var semesterStartDate:String;
public var semesterEndDate:String;
public var courseCode:String;
public var selectedGridData:ArrayCollection;
public var refreshGrid:Function;
[Bindable]public var employeeArrayColl:ArrayCollection = new ArrayCollection();


/**
 * @protected
 * function will called on the popup window creation 
 */
protected function popUpIntilize():void{
	urlPrefix=commonFunction.getConstants('url')+"/associatecoursewithinstructor/";
	var param:Object=new Object();
	param["entityId"]=entityId;	
	employeeListHttpService.send(param);
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function assignEmployeeHttpServiceResultHandler(event:ResultEvent):void{
	var saveResult:XML= event.result as XML;		
	if(saveResult.sessionConfirm == true){
		Alert.show(resourceManager.getString('Messages','sessionInactive'));
		var url:String="";
		url=commonFunction.getConstants('navigateHome');
		var urlRequest:URLRequest=new URLRequest(url);
		urlRequest.method=URLRequestMethod.POST;
		navigateToURL(urlRequest,"_self");
	}
			
	refreshGrid.call();
	if(saveResult.result.message=="success"){
		Alert.show(commonFunction.getMessages('savedSuccessfully'),(commonFunction.getMessages('success')),4,null,null,successIcon);	
	}
	else{
		Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);	
	}
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function assignEmployeeHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function employeeListHttpServiceResultHandler(event:ResultEvent):void{
	employeeList= event.result as XML;
	if(employeeList.sessionConfirm == true){
		Alert.show(resourceManager.getString('Messages','sessionInactive'));
		var url:String="";
		url=commonFunction.getConstants('navigateHome');
		var urlRequest:URLRequest=new URLRequest(url);
		urlRequest.method=URLRequestMethod.POST;
		navigateToURL(urlRequest,"_self");
	}
	for each(var obj:Object in employeeList.employee){
		employeeArrayColl.addItem({select:false,employeeCode:obj.employeeCode, employeeName:obj.employeeName});
	}
	employeeGrid.dataProvider=employeeArrayColl;
}


/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function employeeListHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
}


/**
 * @protected
 * This function ask conformation alert  
 **/
protected function saveAssigned():void{
	employeeArrayColl.filterFunction=null;
	employeeArrayColl.refresh();
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);
}


/**
 * @protected
 * This function show codes on saving assined details,show success alert,close popup window  
 **/
protected function onOK(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		var selectedRecordArrColl:ArrayCollection=new ArrayCollection();
		var employeeCollection:ArrayCollection=commonFunction.getSelectedRowData(employeeGrid);
		for each(var o:Object in selectedGridData){
			for each(var ob:Object in employeeCollection){
				selectedRecordArrColl.addItem([o.programCourseKey, o.courseCode,ob.employeeCode, entityId,semesterStartDate, semesterEndDate]);
			}
		}
		var param:Object=new Object();
		param["selectedData"]=selectedRecordArrColl;
		//Alert.show(selectedRecordArrColl+"")
		assignEmployeeHttpService.send(param);
		closePopup();
	}
}


/**
 * @protected
 * This function close popup on click of cancel button  
 */
protected function cancelPopup():void {
	closePopup();	
}


/**
 * @protected
 * This function  close popup window on pressing ESC key 
 * @param evt a Keyboard Event object.
 */
protected function initializeForEsc(evt:KeyboardEvent):void
{
    if (evt.keyCode == Keyboard.ESCAPE)
    {
    	closePopup();
    }
}


/**
 * @protected
 * This function close popup on click of close button 
 * @param event a Fault event object.
 */
protected function closePopup():void
{
	PopUpManager.removePopUp(this);			
}


/**
 * @protected
 * This function refresh grid and filter grid on the basis of search items 
 * @author Ashish Mohan
 */
protected function onSearchChange():void{
	for each(var o:Object in employeeGrid.dataProvider as ArrayCollection){
		o.select=false;
	}
	saveLinkButton.enabled=false;
	commonFunction.searchForTwoFields(searchName.text,'employeeName',searchCode.text,'employeeCode',employeeGrid.dataProvider as ArrayCollection)
}	
