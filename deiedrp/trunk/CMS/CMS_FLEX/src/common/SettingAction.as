/**
 * @(#) SettingAction.as
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
 
 import mx.collections.ArrayCollection;
 import mx.controls.Alert;
 import mx.events.CloseEvent;
 import mx.rpc.events.FaultEvent;
 import mx.rpc.events.ResultEvent;
 import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]public var params:Object={};
[Bindable]public var urlPrefix:String="";
[Bindable]public var userType:String="";

//on page load
public function onPageLoad():void
{
	urlPrefix=commonFunction.getConstants('url')+"/settings/";
	params['time']=new Date;
	getAddressInfo.send(params);
	Mask.show(commonFunction.getMessages('pleaseWait'));
}

//copy permanent address to correspondence address
public function copyToCorrespondenceAddress():void
{
	var b:Boolean=checkBoxField.selected;
	if(b)
	{
		correspondenceAddress.addressLine1.text=permanentAddress.addressLine1.text;
		correspondenceAddress.city=permanentAddress.cityField.selectedLabel;
		correspondenceAddress.stateField.selectedIndex=permanentAddress.stateField.selectedIndex;
		correspondenceAddress.pinField.text=permanentAddress.pinField.text;
	}
	else
	{
	    correspondenceAddress.addressLine1.text="";
		correspondenceAddress.stateField.selectedIndex=-1;
		correspondenceAddress.cityField.selectedIndex=-1;
		correspondenceAddress.pinField.text="";	
	}  
	correspondenceAddress.enabled=(!b);
	permanentAddress.enabled=(!b);
}

// get address details Result Handler handler
public function getAddressesResultHandler(event:ResultEvent):void
{
	checkBoxField.selected=false;
	permanentAddress.enabled=true;
	correspondenceAddress.enabled=true;
	Mask.close();
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	  	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	userType=event.result.addressInfo.addresses.(addressKey=='PER').userType;
	permanentAddress.addressLine1.text=event.result.addressInfo.addresses.(addressKey=='PER').address;
	permanentAddress.stateField.selectedItem=event.result.addressInfo.addresses.(addressKey=='PER').state.toString();
	permanentAddress.city=event.result.addressInfo.addresses.(addressKey=='PER').city.toString();
	permanentAddress.pinField.text=event.result.addressInfo.addresses.(addressKey=='PER').pinCode;
	
	phone.homePhoneField.text=event.result.addressInfo.addresses.(addressKey=='PER').homePhone;
	phone.officePhoneField.text=event.result.addressInfo.addresses.(addressKey=='PER').officePhone;
	phone.otherPhoneField.text=event.result.addressInfo.addresses.(addressKey=='PER').otherPhone;
	phone.faxPhoneField.text=event.result.addressInfo.addresses.(addressKey=='PER').fax;
		
	correspondenceAddress.addressLine1.text=event.result.addressInfo.addresses.(addressKey=='COR').address;
	correspondenceAddress.stateField.selectedItem=event.result.addressInfo.addresses.(addressKey=='COR').state.toString();
	correspondenceAddress.city=event.result.addressInfo.addresses.(addressKey=='COR').city.toString();
	correspondenceAddress.pinField.text=event.result.addressInfo.addresses.(addressKey=='COR').pinCode;
}

//validating address field
public function validateAddress():Boolean
{
	var bool:Boolean=false;
	
	addressValidator1.source=permanentAddress.addressLine1;
	stateValidator1.source=permanentAddress.stateField;
	cityValidator1.source=permanentAddress.cityField;
	
	addressValidator2.source=correspondenceAddress.addressLine1;
	stateValidator2.source=correspondenceAddress.cityField;
	cityValidator2.source=correspondenceAddress.cityField;
	
	var corrRequired:Boolean=false;
	if((correspondenceAddress.addressLine1.text!="")||(correspondenceAddress.cityField.selectedIndex>-1)||(correspondenceAddress.stateField.selectedIndex>-1)||(correspondenceAddress.pinField.text!=""))
	{
		corrRequired=true;
	}
	
	addressValidator2.required=corrRequired;
	stateValidator2.required=corrRequired;
	cityValidator2.required=corrRequired;
	correspondenceAddress.pinValidator.required=corrRequired;
	
	if(Validator.validateAll([addressValidator1,addressValidator2,cityValidator1,cityValidator2,stateValidator1,
			stateValidator2,permanentAddress.pinValidator,correspondenceAddress.pinValidator]).length==0)
	{
		bool=true;
	}
	
	return bool;
}

//reset all address field
public function resetAddress():void
{
	getAddressInfo.send(params);
	
	permanentAddress.addressLine1.errorString="";
	permanentAddress.cityField.errorString="";
	permanentAddress.stateField.errorString="";
	permanentAddress.pinField.errorString="";
	correspondenceAddress.addressLine1.errorString="";
	correspondenceAddress.cityField.errorString="";
	correspondenceAddress.stateField.errorString="";
	correspondenceAddress.pinField.errorString="";	
	
	phone.officePhoneField.errorString="";
	phone.homePhoneField.errorString="";
	phone.otherPhoneField.errorString="";
	phone.faxPhoneField.errorString="";
}

//on click of upodate address button
public function changeAddress():void
{
	if(validateAddress())
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,updateAddressOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

//Update address confirmation handler
public function updateAddressOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		params["address"]=permanentAddress.addressLine1.text+"|"+correspondenceAddress.addressLine1.text+"|";
		params["city"]=permanentAddress.cityField.selectedLabel+"|"+correspondenceAddress.cityField.selectedLabel;
		params["state"]=permanentAddress.stateField.selectedLabel+"|"+correspondenceAddress.stateField.selectedLabel;
		params["pin"]=permanentAddress.pinField.text+"|"+correspondenceAddress.pinField.text+"|";
		params["officePhone"]=phone.officePhoneField.text;
		params["homePhone"]=phone.homePhoneField.text;
		params["otherPhone"]=phone.otherPhoneField.text;
		params["fax"]=phone.faxPhoneField.text;
		params["userType"]=userType;
		
		updateAddressInfo.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

// get address details Result Handler handler
public function updateAddressesResultHandler(event:ResultEvent):void
{
	Mask.close();
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	  	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	if(event.result.info.toString()=="true")
	{
		Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
		checkBoxField.selected=false;
		permanentAddress.enabled=true;
		correspondenceAddress.enabled=true;
	}
	else
	{
		Alert.show(commonFunction.getMessages('requestFailed'));
	}
}

// fault handler
public function faultHandler(event:FaultEvent):void
{
	Mask.close();
	Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'),0,null,null,errorIcon);
}

//on click of update password button
public function changePassword():void
{
	if(validatePassword())
	{
		params['userName']=this.parentDocument.userName;
		params['password']=oldPassword.text;
		params['application']=this.parentDocument.application;//Add by Devendra
		getLoginInfoService.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

//verify password result handler
public function loginInfoResultHandler(event:ResultEvent):void
{
	Mask.close();
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	  	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	var values:ArrayCollection=new ArrayCollection();
	for each(var obj:Object in event.result.loginInfo)
	{
		values.addItem({userId:obj.userId,universityId:obj.universityId,userGroupId:obj.userGroupId,
		userGroupName:obj.userGroupName,userName:obj.userName, universityName:obj.universityName});
	}
	
	if(values.length>0)
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,updatePasswordOrNot,questionIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('invalidPassword'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		oldPassword.errorString=commonFunction.getMessages('invalidPassword');
	}
}

//validate password fields
public function validatePassword():Boolean
{
	var bool:Boolean=false;
	if(Validator.validateAll([oldPasswordValid,newPasswordValid,confirmPasswordValid]).length==0)
	{
		if(newPassword.text==confirmPassword.text)
		{
			newPassword.errorString="";
			confirmPassword.errorString="";
			bool=true;
		}
		else
		{
			newPassword.errorString=commonFunction.getMessages('passwordMismatch');
			confirmPassword.errorString=commonFunction.getMessages('passwordMismatch');
		}
	}
	return bool;
}

//reset all password fields
public function resetPassword():void
{
	oldPassword.text="";
	newPassword.text="";
	confirmPassword.text="";
	oldPassword.errorString="";
	newPassword.errorString="";
	confirmPassword.errorString="";
}

//update password confirmation handler
public function updatePasswordOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES){
		params['password']=oldPassword.text;
		params['newPassword']=newPassword.text;
		updatePassword.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

// get address details Result Handler handler
public function updatePasswordResultHandler(event:ResultEvent):void
{
	Mask.close();
	if(event.result.sessionConfirm == true)
	{
		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	  	this.parentDocument.vStack.selectedIndex=0;
	   	this.parentDocument.loaderCanvas.removeAllChildren();
	}
	if(event.result.info.toString()=="true")
	{
		Alert.show(commonFunction.getMessages('recordUpdatedSuccessfully'),commonFunction.getMessages('success'),0,null,null,successIcon);
	}
	else
	{
		Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	resetPassword();
}