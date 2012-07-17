/**
 * @(#) correctionWindowAction.as
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
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
import common.Mask;

import mx.collections.*;
import mx.controls.*;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;
[Bindable]public var stuId:String="";
[Bindable] public var urlUpdate:String = "";
[Bindable] public var buttonFunction:Function;
[Bindable] public var ed:Button;

/**
 *Method firs on load of popup window
 * it set visibilty of appropriate labels 
 **/
public function setLabels():void{
	
	if(branchLabel.text==""){
		br.visible=false;
		branchLabel.visible=false;
	}
	if(specializationLabel.text==""){
		specializationLabel.visible=false;
		sp.visible=false;
	}
}
    
/**
 *Method fires on click of update button
 *validate required fields
 * send request to update values 
 **/
public function updateName():void{

	if(Validator.validateAll([sfNameValidator,ffNameValidator,mfNameValidator]).length!=0){
	
	 	Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);     	
	}else{
	urlUpdate = commonFunction.getConstants('url')+"/namecorrection/updateNames.htm";
	
	var param:Object=new Object();
	
	param["registrationNo"]=registrationLabel.text;
	param["studentId"]=stuId;
	param["studentfname"]=studentFirstName.text;
	param["studentmname"]=studentMiddleName.text;
	param["studentlname"]=studentLastName.text;
	param["fatherfname"]=fatherFirstName.text;
	param["fathermname"]=fatherMiddleName.text;
	param["fatherlname"]=fatherLastName.text;
	param["motherfname"]=motherFirstName.text;
	param["mothermname"]=motherMiddleName.text;
	param["motherlname"]=motherLastName.text;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	updateNames.send(param);
	}
}

/**
 * method executed on success of update request 
 * */
[Bindable] private var xmldata_activityList:XML;
private function updateResultHandler(event:ResultEvent):void{
	Mask.close();	
        xmldata_activityList=event.result as XML;
       
       if(xmldata_activityList.Details.list_values=="success"){
	
	Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),
		4,null,onUpdate,successIcon);
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}	
	        
}

/**
 * method executed on click of ok on update success alert
 * */
public function onUpdate(event:CloseEvent):void{
	ed.enabled=false;
	closeConfirmWindow();
	buttonFunction();
}
 
 /**
 * method executed on request failure 
 * */   
 private function faultHandler(event:FaultEvent):void{
 	Mask.close();
      Alert.show(commonFunction.getMessages('requestFailed'),
				commonFunction.getMessages('failure'),0,null,null,errorIcon);	
    }
    
//This function Close popup window on click of cancel button on window
public function cancelConfirmWindow():void 
{
	closeConfirmWindow();
	
}

//This function Close popup window on click of close sign at corner on window
public function closeConfirmWindow():void
{
	PopUpManager.removePopUp(this);			
}

//This function Close popup window on click ESC button  
private function initializeForEsc(evt:KeyboardEvent):void
   {
    if (evt.keyCode == Keyboard.ESCAPE)
    {
    	closeConfirmWindow();
    }
  }