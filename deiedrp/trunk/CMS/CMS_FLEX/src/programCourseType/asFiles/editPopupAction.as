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
import common.validations.CommonValidations;

import mx.collections.*;
import mx.controls.Alert;
import mx.controls.Button;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
public var buttonFunction:Function;
public var validateAtPopUp:CommonValidations=new CommonValidations;
public var programId:String;
public var branchId:String;
public var specializationId:String;
public var semesterCode:String;
public var courseTypeCode:String;
public var editButton:Button;
public var deleteButton:Button;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

/** Executed on updating record 
 * validates max credit is greater than min or not?
 * and send request for updating record
 * */
 public function updateRecord():void {
 	
	 if(validateAtPopUp.isGreater(changemincombo.text,changemaxcombo.text)){
	 	
	 	Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onValidationUpdate,questionIcon);  	    
	 }
	 else{
	 Alert.show((resourceManager.getString('Messages','maximumCreditsCantBeLessThenMinimumCredits')),
	 (resourceManager.getString('Messages','error')),0,null,null,errorIcon);	
	 }
}

public function onValidationUpdate(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){
		
		var infoObject:Object = {};
		 
		 infoObject["userId"] = new Date;
		 infoObject["programId"] = programId;
		 infoObject["branchId"] = branchId;
		 infoObject["specializationId"] =  specializationId;
		 infoObject["semesterCode"] = semesterCode;
		 infoObject["courseType"] = courseTypeCode;
		 infoObject["minCredits"] = changemincombo.text;
		 infoObject["maxCredits"] = changemaxcombo.text;
		 infoObject["activityFlag"] = "update";
		 
		 Mask.show(commonFunction.getMessages('pleaseWait'));
		 
		 updateSelectedRecord.send(infoObject);
		
	}
	
}

/**
 *Method to fire the click event of the button
 * ie. to reflect the changes made in the record
 * onto the application 
 **/
public function onOK(event:CloseEvent):void{		
		
		buttonFunction(event);
		editButton.enabled=false;
		deleteButton.enabled=false;

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
public function cancelpopup():void
{
	closePopup();
}

/** executed on click of close button and closes pop up
 * */
public function closePopup():void
{
	PopUpManager.removePopUp(this);			
}

/** executed on pressing Esc key down and closes pop up
 * */
private function initializeForEsc(evt:KeyboardEvent):void
   {
    if (evt.keyCode == Keyboard.ESCAPE)
    {
    	closePopup();
    }
  }
  
  public var resultXml:XML;
  public function onUpdateSuccess(event:ResultEvent):void{
  	
  	resultXml=event.result as XML;
  	
  	if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas.removeAllChildren();
            } 
  	
  	Alert.show((resourceManager.getString('Messages','recordUpdatedSuccessfully')),
  	    (resourceManager.getString('Messages','saved'))
		,4,null,onOK,successIcon);
		
		Mask.close();
		
	    closePopup();
  	
  }