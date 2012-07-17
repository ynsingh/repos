/**
 * @(#) editPopupAction.as
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

import mx.collections.*;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent; 
public var refreshFunction:Function;
public var bl:Boolean=true;
public var bool:Boolean=true;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

/** function executed on pop up creation complete
 
 * */ 
public function checkDateForTimeVisibility():void 
{ 
	if(secondDate.text.length!=0){
	secondHourLabel.visible=true;
	secondMinLabel.visible=true;
	secondTimeInHour.visible=true;
	secondTimeInMinute.visible=true;}
	if(thirdDate.text.length!=0){
	thirdHourLabel.visible=true;
	thirdMinLabel.visible=true;
	thirdTimeInHour.visible=true;
	thirdTimeInMinute.visible=true;}
	
}

//to check both second time and date is selected 
public function checkSecondDate():Boolean{
	if(secondDate.text.length!=0 && (secondTimeInHour.value==0 && secondTimeInMinute.value==0)){
 		Alert.show("Select Second Time",commonFunction.getMessages('info'),4,null,null,infoIcon);
 		bool=false;
 	}
 
 	else if(secondDate.text.length!=0 && (secondTimeInHour.value>0 || secondTimeInMinute.value>0)){
		bool=true;
 	}
	return bool;
}

//to check both third time and date is selected
public function checkThirdDate():Boolean{
	if(thirdDate.text.length!=0 && (thirdTimeInHour.value==0 && thirdTimeInMinute.value==0)){
 		Alert.show("Select Third Time",commonFunction.getMessages('info'),4,null,null,infoIcon);
 		bl=false;
 	}
 	else if(thirdDate.text.length!=0 && (thirdTimeInHour.value>0 || thirdTimeInMinute.value>0)){
 		bl=true;
 	}
	return bl;
}


/** This function executed on click of update button 
 
 * */ 
public function updateRecord():void 
{  
	if((firstDate.text.length!=0)&&(timeInHour.value>0 || timeInMinute.value>0))
    {
    	checkSecondDate();	
        checkThirdDate();
        if(bl==true && bool==true)
        {
        	Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,updateOrNot,questionIcon);	
		}		  
	}
	else
	{
		Alert.show(commonFunction.getMessages('firstSelectallMandatoryFields'),
		commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/**
 * The function fires on second date change
 * enabled save button
 * */
public function visibleSecondTime():void{
	if(firstDate.text.length!=0){
	secondHourLabel.visible=true;
	secondMinLabel.visible=true;
	secondTimeInHour.visible=true;
	secondTimeInMinute.visible=true;}
	else{
		secondDate.text="";
		Alert.show(commonFunction.getMessages('selectFirstDate'),commonFunction.getMessages('info'),4,null,null,infoIcon);
	}
}

/**
 * The function fires on third date change
 * enabled save button
 * */
public function visibleThirdTime():void{
	if(secondDate.text.length!=0){
	thirdHourLabel.visible=true;
	thirdMinLabel.visible=true;
	thirdTimeInHour.visible=true;
	thirdTimeInMinute.visible=true;}
	else{
	thirdDate.text="";
	Alert.show(commonFunction.getMessages('selectSecondDate'),commonFunction.getMessages('info'),4,null,null,infoIcon);
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
		var infoObject:Object = {};
		infoObject["courseCode"] = courseCode.text;
		infoObject["externalExaminerId"] = externalExaminerId.text;
		infoObject["firstDate"] = dateFormatter.format(firstDate.text);
		infoObject["secondDate"] =dateFormatter.format(secondDate.text);
		infoObject["thirdDate"] =dateFormatter.format(thirdDate.text);
		infoObject["time"]=timeInHour.value+":"+timeInMinute.value;
		infoObject["secondTime"]=secondTimeInHour.value+":"+secondTimeInMinute.value;
		infoObject["thirdTime"]=thirdTimeInHour.value+":"+thirdTimeInMinute.value;
		updateCourseExaminerService.send(infoObject);
		Mask.show(commonFunction.getMessages('pleaseWait'));
		
	
	}
}


/**
 * This function fire on successfull updation of records
 * */
public function updateCourseExaminerResultHandler(event:ResultEvent):void
{	
	Mask.close();
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
 	if(event.result.Details.list_values=="success")
 	{
    	Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,closePopUp,successIcon);
	}
	else{
		Alert.show((resourceManager.getString('Messages','requestFailed')),
		(resourceManager.getString('Messages','failure')),0,null,null,errorIcon);	
	}	
	refreshFunction();
}

/** this fire on faliure of processing a request
 * */
public function onFailure(event:FaultEvent):void{
	
	Mask.close();
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
}

/** this method fires on click of close button and closes pop up
 * */
public function closePopUp(event:CloseEvent):void
{
	PopUpManager.removePopUp(this);
	refreshFunction();
	
}

/**
 * This fire on pressing down ESC key 
 * and closes popup
 * */
private function initializeForEsc(event:KeyboardEvent):void
{
    if (event.keyCode == Keyboard.ESCAPE)
    {
		var evt:CloseEvent;
		closePopUp(evt);
    }
}