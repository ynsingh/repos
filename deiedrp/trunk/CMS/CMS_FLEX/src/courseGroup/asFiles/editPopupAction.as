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
import mx.validators.Validator; //added my ashish

public var buttonFunction:Function;

public var programCourseKey:String;
public var courseGroupCode:String;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
/** This function executed on click of update button 
 * validates credits values
 * */ 
public function updateRecord():void 
{ 
	minCreditsValidator1.source=minCreditField;
	maxCreditsValidator1.source=maxCreditField;
	
    if(Validator.validateAll([minCreditsValidator1,maxCreditsValidator1]).length!=0)
    {
      Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
    }
    else if(Number(minCreditField.text)>Number(maxCreditField.text))
      {
	    maxCreditField.errorString=commonFunction.getMessages('maximumCreditsCantBeLessThenMinimumCredits');
	  Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	  }
	  
	   else
	  { 

	 Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,updateOrNot,questionIcon);
        
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
		infoObject["programCourseKey"] = programCourseKey;
		infoObject["courseGroupCode"] = courseGroupCode;
		infoObject["elective"] = isElectiveGroup.selectedValue;
		infoObject["minCredits"] = minCreditField.text;
		infoObject["maxCredits"] = maxCreditField.text;
/** order In Marksheet Added By Mandeep**/		
		infoObject["orderInMarksheet"]=order.value;
		updateCourseGroupService.send(infoObject);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}


/**
 * This function fire on successfull updation of records
 * */
public function updateCourseGroupResultHandler(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
 	if(int(event.result.count)>=0)
 	{
    	Alert.show(event.result.count+" "+commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,closePopUp,successIcon);
	}
	if(String(event.result.exception.exceptionstring).search("ErrorInUpdate")>-1){
		Alert.show(resourceManager.getString('Messages','cannotUpdate')+"\n"+String(event.result.exception.exceptionstring)  ,resourceManager.getString('Messages','error'),4,null,null,errorIcon);
     }
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
	buttonFunction();
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