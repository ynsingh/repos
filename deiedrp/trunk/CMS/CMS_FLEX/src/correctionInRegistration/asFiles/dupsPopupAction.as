/**
 * @(#) dupsPopupAction.as
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
import mx.collections.ArrayCollection;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Bindable]public var urlPrefix4:String;			
import mx.controls.Alert;
import mx.validators.Validator;
import common.commonFunction;
import mx.events.CloseEvent;			
public var buttonFunction:Function;
public var gender:String;
[Bindable]public var params:Object={};
[Bindable]public var genderr:String="";
[Bindable]public var categoryDetails: XML;
public var categorylist:ArrayCollection;
[Bindable]public var category:String="";
[Bindable]public var catId:String="";
[Bindable]public var rolNo:String="";
[Bindable]public var enrNo:String="";
[Bindable]public var sf:String="";
[Bindable]public var sm:String="";
[Bindable]public var sl:String="";
[Bindable]public var ff:String="";
[Bindable]public var fm:String="";
[Bindable]public var fl:String="";
[Bindable]public var gen:String="";
[Bindable]public var cateId:String="";
[Bindable]public var prgId:String="";
[Bindable]public var oldentId:String="";
[Bindable]public var oldprgId:String="";
[Bindable]public var oldbrnId:String="";
[Bindable]public var oldspcId:String="";
[Bindable]public var oldsemId:String="";
[Bindable]public var atmNo:String="";
[Bindable]public var admMode:String=""; 
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
public var param:Object={};

/**
 * The function calls on load of popup window
 * It sets Appropriate fields  
 * */
protected function titlewindowInitializeHandler():void{

	urlPrefix4=resourceManager.getString('Constants','url')+"/correctionInRegistration/";
	        
				    param["studentfname"]=sf;
	            	param["studentmname"]=sm;
	            	param["studentlname"]=sl;
	            	param["fatherfname"]=ff;
	            	param["fathermname"]=fm;
	            	param["fatherlname"]=fl;
	            	param["gender"]=gen;
	            	param["categoryId"]=cateId;
	            	param["dob"]=dobLabel.text;
	            	
	            	getEnrollNo.send(param);
				 if(reasoncodeLabel.text.toUpperCase()=="DUPS"){
	            	r1.visible=false;
	            	regrollNo.visible=false;
	            	}
	            }
			
			
/**
* method executed on request failure 
* */
public function onFailure(event:FaultEvent):void{
				
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
	}
			
			
/**
 * The function removes the popup
 */
public function closepopup():void{
				
	PopUpManager.removePopUp(this);
	}

/**
 * The function fires on success of getting enrollment number
 * */
public function ongetEnrollNoSuccess(event:ResultEvent):void{
		
		var enr:XML=event.result as XML;
		var s:String=enr.Details.enrollNo;
		enrollNo.text=s;
		if(reasoncodeLabel.text.toUpperCase()=="ENRR"){
			
			param["enrollNo"]=s;
			param["oldEntity"]=oldentId;
			param["oldProgram"]=oldprgId;
			param["oldBranch"]=oldbrnId;
			param["oldSpecialization"]=oldspcId;
			param["oldSemester"]=oldsemId;
			
			getRollNo.send(param);
	}
	}
	
/**
 * The function fires on success of getting roll number
 * */	
	public function ongetRollNoSuccess(event:ResultEvent):void{
		
		
		var rol:XML=event.result as XML;
		var r:String=rol.Details.enrollNo;
		regrollNo.text=r;
	}	
	
	
/**
 * The function fires on click of update button
 * it send request for updating values 
 * */		
	public function updateValues():void{
		
		param["rollNo"] =regrollNo.text;
		param["enrollNo"] =enrollNo.text;
		param["studentId"] =studentIdLabel.text;
		param["reasoncode"] =reasoncodeLabel.text;
		
	    setNumbers.send(param);			
	}
	
/**
 * The function fires on success of update 
 * and show aprropriate alert
 * */
public var updateDetails:XML;
public function onSetNumbersSuccess(event:ResultEvent):void{
		

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
 * The function fires on click of ok on Succes alert 
 * it closes popup and call appropriate function
 * */		
public function onUpdate(event:CloseEvent):void{
				
				PopUpManager.removePopUp(this);
				buttonFunction.call();
				
			}