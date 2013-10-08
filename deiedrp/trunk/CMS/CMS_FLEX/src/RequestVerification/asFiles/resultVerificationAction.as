/**
 * @(#) requestVerificationAction.as
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

import mx.collections.*;
import mx.controls.*;
import mx.events.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
/** declaring public variables **/
[Bindable]public var urlPrefix:String="";
[Bindable]public var rollNoData:ArrayCollection=new ArrayCollection();
[Bindable]public var s:String;
[Bindable] public var updateFlag:String;
[Bindable] public var requestNumber:String;
[Bindable] public var requestTypeCode:String;
public var selectedValues:ArrayCollection = new ArrayCollection();

public var params:Object={};
public var requestTypeXml:XML;

/** on creation of form **/
public function onCreationComplete():void{
	urlPrefix=commonFunction.getConstants('url')+"/resultVerification/";	
	params["time"] = new Date;
	getRequestType.send(params);
	Mask.show();
}	

/** this method calls request completes**/
public var requestTypeCollection:ArrayCollection=new ArrayCollection();
public function getRequestTypeSuccess(event:ResultEvent):void{
    Mask.close();
	try{
		if(event.result.sessionConfirm == true){
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
	requestTypeXml=event.result as XML;
	for each(var obj:Object in requestTypeXml.requestType){
		requestTypeCollection.addItem({requestTypeCode:obj.requestTypeCode,requestTypeName:obj.requestTypeName});
	}
	requestTypeCombo.dataProvider = requestTypeCollection;
	
//	rollNumberCB.dataProvider = rollNumberDetail.rollNumber.rollNo;
if(requestTypeCode.length>0){
	for(var i:int=0;i<requestTypeCollection.length;i++){
    	var requestCode:String = requestTypeCollection.getItemAt(i).requestTypeCode;
    	if(requestTypeCode==requestCode){
    		requestTypeCombo.selectedIndex=i;
    		break;
    	}
    }
}
else if(requestTypeCollection.length==1){
		requestTypeCombo.selectedIndex=0;
//		onRollNoCombo();
	}
	 Mask.close();
//	requestNo.text=requestNoXml.request.requestNo;
}


/** this method calls if any fault occurs in request**/
public function faultHandler(event:FaultEvent):void{	
	Mask.close();
	 Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'));
}

/** onClick of OK button **/
public function ok():void{
	Mask.show();
	receiveDateValidator.source=receiveDate;
	companyValidator.source=compName;
	companyAddValidator.source=compAdd;
	if(Validator.validateAll([companyValidator,companyAddValidator,receiveDateValidator,requestTypeValidator]).length==0){
		detail.visible=true;
		vbox.visible=true;
		Mask.close();
//		submitButton.visible=true;
//		cancelButton.visible=true;
//		company.enabled=false;
//		receiveDate.enabled=false;
//		okButton.enabled=false;
//		start();
	}
	else{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		Mask.close();
	}
}	


/** this is to remove the interface**/
 public function cancel():void{
 	Mask.close();
  	parent.removeChild(this);  	
}
  	
  	
/** use to reset values to initials**/
public function reset():void{
	compName.errorString="";
	compAdd.errorString="";
	receiveDate.errorString="";
	requestTypeCombo.errorString="";
	companyValidator.source=null;
	receiveDateValidator.source=null;
	companyAddValidator.source=null;
	requestTypeValidator.source=null;
	receiveDate.enabled=true;
	receiveDate.text="";
	compName.text="";
	compAdd.text="";
	requester.text="";
	refNo.text="";
	refDate.text="";
	requestTypeCombo.selectedIndex=-1;
	detail.visible=false;
	vbox.visible=false;
//	cancelButton.visible=false;
	okButton.enabled=true;
}

public var rollNoStr:String;
public function chkRollNoAvailability(selArr:Array,flag:String):void{
	updateFlag = flag;
	params["time"] = new Date;
	params["rolNoArray"] = selArr;
	rollNoStr = selArr.join("|");
	getRollNo.send(params);		
}

/** this method calls roll no request completes**/
public var rollNoXml:XML;
public var noRollNo:ArrayCollection=new ArrayCollection;
public function getRollNoSuccess(event:ResultEvent):void{
	Mask.close();
	try{
		if(event.result.sessionConfirm == true){
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
	rollNoXml=event.result as XML;
	var arr:String="";
	for each(var obj:Object in rollNoXml.rollNo){
			noRollNo.addItem({rollNumber:obj.rollNumber});
//			Alert.show("roll number "+obj.rollNumber);
			if(arr.length==0){
				arr = obj.rollNumber;
			}
			else{
				arr = arr+" , "+obj.rollNumber;
			}
	}
	if(arr.length>0){
		Alert.show("Roll Number(s) "+arr+"are invalid one...either remove or correct them");	
	}
	else{
		params["requestType"]=requestTypeCollection.getItemAt(requestTypeCombo.selectedIndex).requestTypeCode;
		params["requester"]=requester.text;
		params["companyName"]=compName.text;
		params["companyAdd"]=compAdd.text;
		params["receiveDate"]=receiveDate.text;
		params["referenceNo"]=refNo.text;
		params["refDate"]=refDate.text;
		params["time"]=new Date();
		params["rollNo"]=rollNoStr;
		params["updateFlag"] = updateFlag;
		var index:int = updateFlag.indexOf("update");
		if(index != -1){
			var olderArray:Array=new Array;
			for each(var obj:Object in selRollNoArray){
				if (obj.rollNumber.length!=0) {
                	olderArray.push(obj.rollNumber);
        		}				
			}
			params["olderRollNo"] =olderArray;
			params["requestNo"] = requestNumber;
		}
		
		Mask.show();
		setResultVerRequest.send(params);
		//enter the detail in the database
	}
} 

public function setResultVerRequestSuccess(event:ResultEvent):void{
	Mask.close();
	try{
		if(event.result.sessionConfirm == true){
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
 	
 	var result:String=(event.result.info.message+"") 	
 	var lengthResult:Number = result.length;
 	if(result=="true"){
 		Alert.show(commonFunction.getMessages('fileUploadSuccess'),commonFunction.getMessages('success'),0,null,null,successIcon);
 		detail.visible=false;
 		vbox.visible=false;
 		reset();
 	}
 	else if(result.substring(0,6)=='update'){
 		if(result.substring(6,lengthResult)=='true'){
 			Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
 			parent.removeChild(this);
 		}
 		else{
 			Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
 		}
 	}
 	else{
 		Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
 	}
}
