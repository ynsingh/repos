/**
 * @(#) Quiz_Action.java
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
import mx.controls.Button;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

public var buttonFunction:Function;

public var programCourseKey:String;
public var courseGroupCode:String;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var edit:Button;
public var delet:Button;
public var courseId:String;
public var mouUniversityId:String;
public var courseStatus:String;
public var infoObject:Object={};

/** This function ask for conformation for continue to update records
 * and calls appropriate function for handling choice
 * */
public function updateRecord():void{	
	
    Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,updateOrNot,questionIcon);
	   
}

/** This function sets value of status on load of pop up window
 * */
public function onCreationComplete():void{	

	changeStatusCombo.selectedItem = statusXmlId.status.(@code==courseStatus).@name.toString();
	
}

/**
 * method executed on successfull processing of the uodation request
 * */
[Bindable]
public var successDetails: XML;
public function onSetSuccess(event:ResultEvent):void{
	
	successDetails=event.result as XML;
	
	if(successDetails.Details.list_values=="success"){
		
		Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),
		commonFunction.getMessages('success'),0,null,onOk,successIcon);
		
		
	}else{
		Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	

	}			
}

/**
 *Method to fire the click event of the button
 * ie. to reflect the changes made in the record
 * onto the application 
 **/
public function updateOrNot(event:CloseEvent):void
{		
	if(event.detail==1)
	{		
		infoObject["userId"]=new Date;
		infoObject["mouUniversityId"]=mouUniversityId;
		infoObject["mouCourse"]=courseId;
		infoObject["courseStatus"]=statusXmlId.status.(@name==changeStatusCombo.selectedLabel).@code;
		infoObject["activity"]="update";
		
		setDetails.send(infoObject);	

	}
	if(event.detail==2)
	{
		edit.enabled=false;
		delet.enabled=false;
		closePopUp();
		
	}
}


/** method fire on click of ok button of alert box of succesfull updation
 * and closes popup
 * */
public function onOk(event:CloseEvent):void{
	closePopUp();
	buttonFunction();
	edit.enabled=false;
	delet.enabled=false;
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
}

/** executed on click of close button and closes pop up
 * */
public function closePopUp():void
{
	PopUpManager.removePopUp(this);
	
}

/** executed on click of cancel button and closes pop up
 * */
public function cancelPopUp():void
{
	closePopUp();

}

/** executed on pressing Esc key down and closes pop up
 * */
private function initializeForEsc(event:KeyboardEvent):void
{
    if (event.keyCode == Keyboard.ESCAPE)
    {
		closePopUp();
    }
}