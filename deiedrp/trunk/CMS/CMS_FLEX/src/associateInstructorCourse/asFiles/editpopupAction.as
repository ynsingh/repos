/**
 * @(#) editpopupAction.as
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
			
[Embed(source="/images/success.png")]private var successIcon:Class;	
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
 public var sh:String;	
[Bindable]protected var urlPrefix:String; 
protected var employeeList:XML;
public var entityId:String; 
public var programCoursekey:String;
public var semesterStartDate:String;
public var semesterEndDate:String;
public var courseCode:String;
public var selectedGridData:ArrayCollection;
public var refreshGrid:Function;
public var employeeCode:String;
public var employeeName:String;
public var statusValue:String;
[Bindable]public var statusData:XML=<root>
							<element>
								<status name="Active" code="ACT"/>											
							</element>
							<element>
								<status name="Inactive" code="INA"/>											
							</element>
						  </root>; 

/**
 * @protected
 * function will called on the popup window creation 
 */
protected function popUpIntilize():void{
	urlPrefix=commonFunction.getConstants('url')+"/associatecoursewithinstructor/";
	statusCB.selectedItem=statusValue;
	this.title = this.title +": "+courseCode;	
	var param:Object=new Object();
	param["entityId"]=entityId;	
	employeeListHttpService.send(param);
}

/**
 * @protected
 * this method is called when a result come from the server. 
 * @param event a Result event object.
 */
protected function updateInstructorHttpServiceResultHandler(event:ResultEvent):void{
	var updateResult:XML= event.result as XML;
	if(updateResult.sessionConfirm == true){
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	if(updateResult.result.message=="success"){
		Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),(commonFunction.getMessages('success')),4,null,null,successIcon);	
	}
	else{
		Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
	}
	
	refreshGrid.call();
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function updateInstructorHttpServiceFaultHandler(event:FaultEvent):void{
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
		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
		this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
	}
	
	var employeeArrayColl:ArrayCollection = new ArrayCollection();
	
	for each(var obj:Object in employeeList.employee){
		employeeArrayColl.addItem({employeeCode:obj.employeeCode, employeeName:obj.employeeName,
		empNameCode:obj.employeeName+' ['+obj.employeeCode+']'});
	}
	
	assignEmployCombo.labelField="empNameCode";
	assignEmployCombo.dataProvider=employeeArrayColl;
	
	var index:int=0;
	for(var i:int=0;i<employeeArrayColl.length;i++)
	{
		if(employeeArrayColl.getItemAt(i).employeeCode.toString()==employeeCode)
		{
			index=i;
			break;
		}
	}
	
	assignEmployCombo.selectedIndex = index;
}

/**
 * @protected
 * this method is called when a fault occur from the server. 
 * @param event a Fault event object.
 */
protected function employeeListHttpServiceFaultHandler(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);
}

private function confirmUpdate(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		var param:Object=new Object();
  	
	  	var temp:ArrayCollection = assignEmployCombo.dataProvider as ArrayCollection;
		var employeeCode:String=temp.getItemAt(assignEmployCombo.selectedIndex).employeeCode;
		
	  	param["selectedEntityId"]=entityId;
	  	param["programCourseKey"]=programCoursekey;
	  	param["courseCode"]=courseCode;
	  	param["employeeCode"]=employeeCode;
	  	param["status"]=statusData.element.status.(@name==statusCB.selectedLabel).@code;
	  	param["semesterStartDate"]=semesterStartDate;
	  	param["semesterEndDate"]=semesterEndDate;
	  	updateInstructorHttpService.send(param);
	  	closeMe();	
	}	
}

/**
 * @protected
 * this method is called on the save button click
 */
protected function saveClickHandler():void{
	Alert.show(commonFunction.getMessages('updateConfirmMessage'), commonFunction.getMessages('confirm'), 3, this, confirmUpdate,questionIcon);
}

/**
 * @protected
 * this method is called on the cancel button click
 */
protected function cancelClickHandler():void {
	closeMe();	
}

/**
 * @protected
 * This function close popup   
 */
protected function closeMe():void{
	PopUpManager.removePopUp(this);			
}

/**
 * @protected
 * This function close popup on click of close button 
 * @param evt keyboard event object   
 */
protected function initializeForEsc(evt:KeyboardEvent):void{
    if (evt.keyCode == Keyboard.ESCAPE){
    	closeMe();
    }
}	