/*
*Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
* All Rights Reserved.
*
* Redistribution and use in source and binary forms, with or
* without modification, are permitted provided that the following
* conditions are met:
*
* Redistributions of source code must retain the above copyright
* notice, this  list of conditions and the following disclaimer.
*
* Redistribution in binary form must reproduce the above copyright
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
import mx.core.UIComponent;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var infoObject:Object = {};

public function saveForm():void
{	
	if(validateForm())
	{
		Alert.show(commonFunction.getMessages('areyousure'),
			commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);		
		}
	
}

protected var flag:Boolean=false;
public function validateForm():Boolean
{
	
	if(Validator.validateAll([institutenamevalid,
		institutenicknamevalid,institutetypevalid,institutedomainvalid,addressvalidator,
		statevalidator,cityvalidator,countryvalid,pincodevalid,adminnamevalid,adminlastnamevalid,matchpassword,
		admindesignationvalid,adminemailvalid,passwordvalid,instphonevalid,otherphonevalid]).length!=0)
	{
		flag=false;
		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);	
			
	}
	else if(confirmPass.text!=password.text){
		Alert.show("Password should Match",(commonFunction.getMessages('error')),0,null,null,errorIcon);
		confirmPass.errorString="Password should match";
		flag=false;	
	}
	else
		{
		flag=true;	
	    }
	    
	return flag;
}

public function onOK(event:CloseEvent):void{
	if(event.detail==Alert.YES){				
		
		infoObject["instituteName"] = instituteName.text;
		infoObject["instituteNickName"] = instituteNickName.text;
		infoObject["instituteType"] = instituteType.text;
		infoObject["instituteDomain"] = instituteDomain.text;
		infoObject["address"] = address.text;
		infoObject["city"] = cityField.text;
		infoObject["state"] = stateField.text;
		infoObject["country"] = country.text;
		infoObject["pinCode"] = pincode.text;
		infoObject["officePhoneNumber"] =  phoneNumber.text;
		infoObject["otherPhoneNumber"] = otherPhone.text;
		
		infoObject["employeeFirstName"]=adminName.text;
		infoObject["employeeLastName"]=adminLastName.text;
		infoObject["designation"]=adminDesignation.text;
		infoObject["primaryEmailId"]=adminEmail.text;
		infoObject["password"]=password.text;
		
		insertIntoInstituteAdmin.send(infoObject);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}
public var successDetails: XML;
public function onSuccess(event:ResultEvent):void{
	successDetails=event.result as XML;
	Mask.close();
	if(successDetails.Details.list_values=="duplicate"){
		
		Alert.show(commonFunction.getMessages('duplicateInstitute'),
               commonFunction.getMessages('failure'),0,null,null,errorIcon); 
		
	}else if(String(successDetails.Details.list_values).search("false")>-1){
               
        Alert.show(commonFunction.getMessages('errorInsert')+"\n"+String(successDetails.Details.list_values),
               commonFunction.getMessages('error'),0,null,null,errorIcon);                        
 	}     
	else if(successDetails.Details.list_values=="duplicateuser"){
	   
	   	Alert.show("Admin email id already exists ",
	           commonFunction.getMessages('failure'),0,null,null,errorIcon);  
	   
	}
	else{
	    Alert.show("Institute Created successfully ",
	           (commonFunction.getMessages('saved')),0,null,OnSuccess,successIcon);
	   
	}	
}
public function OnSuccess(event:CloseEvent):void{
	
	resetForm();
	
}
public function onFailure(event:FaultEvent):void{
	Mask.close();
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);	
}

public function cancelButtonAction():void
{
	//this.parentDocument.loaderCanvas.removeAllChildren();
	var strUrl:String=commonFunction.getConstants('homeUrl');
	var urlRequest:URLRequest=new URLRequest(strUrl);				
	urlRequest.method=URLRequestMethod.POST;
	navigateToURL(urlRequest,"_self");
}

public function resetForm():void
{	
	
	instituteName.text="";
	instituteNickName.text="";
	instituteType.text="";
	instituteDomain.text="";
	address.text="";
	
	country.selectedIndex=-1;
	cityField.selectedIndex=-1;
	stateField.selectedIndex=-1;
	pincode.text="";
	phoneNumber.text="";
	otherPhone.text="";
		
	adminName.text="";
	adminLastName.text="";
	adminDesignation.text="";
	adminEmail.text="";
	password.text="";
	confirmPass.text="";
		
	UIComponent(instituteName).errorString="";
	UIComponent(instituteNickName).errorString="";
	UIComponent(instituteType).errorString="";
	UIComponent(instituteDomain).errorString="";
	address.errorString="";
	country.errorString="";
	pincode.errorString="";
	phoneNumber.errorString="";
	otherPhone.errorString="";
	
	adminName.errorString="";
	adminLastName.errorString="";
	adminDesignation.errorString="";
	adminEmail.errorString="";
	password.errorString="";
	confirmPass.errorString="";
	
}			

