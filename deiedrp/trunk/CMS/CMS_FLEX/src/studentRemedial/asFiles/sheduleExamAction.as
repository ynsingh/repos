/**
 * @(#) sheduleExamAction.as
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
import mx.controls.Button;
import mx.rpc.events.ResultEvent;
import mx.rpc.events.FaultEvent;
import mx.managers.PopUpManager;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;

public var infoObject=Object;
[Bindable]public var urlPrefix1:String;
public var programCourseKey:String;
public var appliedSemesterId:String;
[Bindable] public var buttonFunction:Function;
[Bindable] public var btn:Button;

/** This function set value to variable urlPrefix1 on load on confirm window
 * */
public function setUrl():void{
urlPrefix1=commonFunction.getConstants('url')+"/studentRemedial/";	
}

/** this method fires on conforming apply for remedial 
 * this send all request with all parameters to insert
 * values in database
 *  **/
public function submitDetails():void{

 infoObject["rollNo"]=currentRollNoLabel.text;   
 infoObject["programCourseKey"]=programCourseKey;    
 infoObject["courseCode"]=remedialCourseLabel.text;   
 infoObject["semesterStartDate"]=remedialStartDateLabel.text;    
 infoObject["semesterEndDate"]=remedialEndDateLabel.text;    
 infoObject["attemptNumber"]=attemptNumberLabel.text;    
 infoObject["courseStatus"]=remedialCourseStatusLabel.text;
 infoObject["applied"]="YES";
 infoObject["appliedSemester"]=appliedSemesterId;
 infoObject["appliedStartDate"]=applyingSemesterStartLabel.text;
 infoObject["appliedEndDate"]=applyingSemesterEndLabel.text;   
 Mask.show(commonFunction.getMessages('pleaseWait'));
 insertRemedialDetails.send(infoObject);
 }
    
/** This method fires on successfull insertion of record 
 * and show success alert
 * */    
public function onInsertSuccess(event:ResultEvent):void{
	Mask.close();
	Alert.show(commonFunction.getMessages('successfullyAppliedToTakeRemedialExam'),commonFunction.getMessages('success'),0,null,null,successIcon);
	buttonFunction();
	btn.enabled=false;
	closeScreen();
  }

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	Mask.close();
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
}

/** this method close popup screen on click of cross button**/
public function closeScreen():void{
	
	PopUpManager.removePopUp(this);
}

/**
 * @protected
 * This function close popup on click of cancel button  
 */
public function cancelPopup():void{
	closeScreen();
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
    	closeScreen();
    }
}