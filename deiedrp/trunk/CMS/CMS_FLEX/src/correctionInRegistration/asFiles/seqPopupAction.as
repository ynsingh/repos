/**
 * @(#) seqPopupAction.as
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
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Bindable]public var urlPrefix2:String;			
import mx.controls.Alert;
import mx.validators.Validator;
import common.commonFunction;
import mx.events.CloseEvent;			
public var buttonFunction:Function;
[Bindable]public var sequenceNum:String="";
[Bindable]public var reason:String=""; 
[Bindable]public var reg:String="";
[Bindable]public var roll:String="";
[Bindable]public var prgId:String="";
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
public var param:Object={};

/**
 * The function calls on load of popup window
 * It sets Appropriate fields  
 * */
protected function titlewindowInitializeHandler():void{
		
		urlPrefix2=resourceManager.getString('Constants','url')+"/correctionInRegistration/";
	            if(reason.toUpperCase()=="SNEW"){
	            	seqNo.text="1";
	            	seqNo.editable=false;
	            }else if(reason.toUpperCase()=="SSWT"){
	            	
	            	seqNo.text=sequenceNum;
	            	seqNo.editable=true;
	            }
	}
			

			
/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
				
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
	}
	
/**
 * The function close popup window
 * */		
public function closepopup():void{
	
		PopUpManager.removePopUp(this);
	}

	
/**
 * The function calls on click of update button 
 * It send appropriate request to update the values
 * */		
public function updateValues():void{
		
		if(reasoncodeLabel.text.toUpperCase()=="SSWT"){
			
			if(Number(seqNo.text)>0){
				param["sequenceNo"] =seqNo.text;
				param["reasoncode"] =reasoncodeLabel.text;
				param["programId"] =prgId;
				param["rollNo"] =roll;		
				updateSeqRecord.send(param);	
			}else{
				Alert.show("Sequnce number Cant be less than 0","error",4,null,null,errorIcon);
			}
		}else{
				param["sequenceNo"] =seqNo.text;
				param["reasoncode"] =reasoncodeLabel.text;
				param["regNo"] =reg;
				updateSeqRecord.send(param);	
		}
	}
	
/**
 * The function calls on success of update 
 * and it checks the result returned from server then show appropriate alert 
 * */
public var updateDetails:XML;
public function onSeqUpdateSuccess(event:ResultEvent):void{
		
	updateDetails=event.result as XML;
	
	if(updateDetails.Details.list_values=="success"){
	
	Alert.show((resourceManager.getString('Messages','recordUpdatedSuccessfully')),commonFunction.getMessages('success'),
		4,null,onUpdate,successIcon);
		
	}else{
		Alert.show(commonFunction.getMessages('failure'),
 	commonFunction.getMessages('error'),4,null,null,errorIcon);
	}				
}

/**
 * This functtion calls on click on update success alert,it closes popup and
 * call appropriate function.
 * */
public function onUpdate(event:CloseEvent):void{

	PopUpManager.removePopUp(this);
	buttonFunction.call();
}