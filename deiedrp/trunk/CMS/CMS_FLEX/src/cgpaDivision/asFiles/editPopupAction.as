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
import mx.controls.Button;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.*;

[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
public var infoObject:Object={};
public var divisionId:String;
public var buttonFunction:Function;
public var editB:Button;
public var deleteB:Button;

/** Executed on updating record 
 * validates max cgpa is greater than min cgpa or not?
 * and send request for updating record
 * */
public function updateRecord():void {
	
	     if(Validator.validateAll([changeMinCgpaFieldValidator,changeMinCgpaFieldValidator]).length==0)
        { 
	     	var v:Number= Number(changedmaxcgpa.text);
        	var z:Number=Number(changedmincgpa.text);
        	if(v>z)
	 	{
	 		
	 		Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,successValidation,questionIcon);					
	 }
	 else
	 {
	 Alert.show(commonFunction.getMessages('maximumCGPACanNotBeEqualorLessThanMininmumCGPA'),
	 commonFunction.getMessages('error'),0,null,null,errorIcon);	
	 }
        }
	 	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),
		commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

public function successValidation(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){	
		
        		infoObject["userId"]=new Date;
		        infoObject["divisionId"]=divisionId;
       			infoObject["minCGPA"]=changedmincgpa.text;
	      		infoObject["maxCGPA"]=changedmaxcgpa.text;
        		infoObject["activity"]="update";
        		
        		Mask.show(commonFunction.getMessages('pleaseWait'));
        		
        		saveDetail.send(infoObject);	    	
		 }
}


/** this methos executed on successfull updation of cerords
 * */
public function onSetSuccess(event:ResultEvent):void{
	Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),
	commonFunction.getMessages('saved'),4,null,null,successIcon);
	closePopup();
	
	Mask.close();
	
	buttonFunction();
	editB.enabled=false;
	deleteB.enabled=false;
	
	
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	
	Mask.close();	
}

/** executed on click of cancel button and closes pop up
 * */
public function cancelpopup():void{
	closePopup();
}

/** executed on click of close button and closes pop up
 * */
public function closePopup():void{
	PopUpManager.removePopUp(this);			
}

/** executed on pressing Esc key down and closes pop up
 * */
private function initializeForEsc(evt:KeyboardEvent):void{
    if (evt.keyCode == Keyboard.ESCAPE){
    	closePopup();
    }
  }