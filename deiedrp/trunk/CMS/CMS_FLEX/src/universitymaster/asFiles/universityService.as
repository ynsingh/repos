/**
 * @(#) universityService.as
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
import common.commonFunction;
import common.validations.CommonValidations;

import mx.controls.Alert;
import mx.validators.DateValidator;
import mx.validators.NumberValidator;
import mx.validators.PhoneNumberValidator;
import mx.validators.StringValidator;
import mx.validators.Validator;


public var universityNameValid:StringValidator = new StringValidator();
public var universityNickNameValid:StringValidator = new StringValidator();
public var startDateValid:DateValidator = new DateValidator();
public var endDateValid:DateValidator =new DateValidator();
public var addressValid:StringValidator = new StringValidator();
public var cityValid:StringValidator = new StringValidator();
public var stateValid:StringValidator = new StringValidator();
public var pinCodeValid:NumberValidator = new NumberValidator();
public var phoneValid:PhoneNumberValidator = new PhoneNumberValidator();
public var otherPhoneValid:PhoneNumberValidator =new PhoneNumberValidator();
public var faxValid:PhoneNumberValidator = new PhoneNumberValidator();
public var countryValid:StringValidator = new StringValidator();


public var validate:CommonValidations=new CommonValidations();



/**
 * @protected
 * this method is called to validate the form fields.
 */
protected function validateForm():Boolean
{
	sessionStartDate.errorString="";
	sessionEndDate.errorString="";
	 var flag:Boolean=false;
	 var count:int=0;
	
	universityNameValid.source = universityName ;
	universityNameValid.property = "text";
	universityNameValid.required=true;
	
	universityNickNameValid.source = universityNickName ;
	universityNickNameValid.property = "text";
	universityNickNameValid.required=true;
	
	startDateValid.source = sessionStartDate;
	startDateValid.property = "text";
	startDateValid.required=true;
	startDateValid.inputFormat="mm/dd/yyyy";
	startDateValid.allowedFormatChars="/";
	
	endDateValid.source = sessionEndDate;
	endDateValid.property = "text";
	endDateValid.required=true;
	endDateValid.inputFormat="mm/dd/yyyy";
	endDateValid.allowedFormatChars="/";
	
	addressValid .source = address ;
	addressValid.property = "text";
	addressValid.required=true;
	
	cityValid.source = city ;
	cityValid.property = "text";
	cityValid.required=true;
	
	stateValid.source = stateCB ;
	stateValid.property = "text";
	stateValid.required=true;
	
	countryValid.source = countryCB ;
	countryValid.property = "text";
	countryValid.required=true;
	
	pinCodeValid .source = pincode ;
	pinCodeValid.property = "text";
	pinCodeValid.required=true;
	pinCodeValid.minValue=100000;
	pinCodeValid.maxValue=999999;
	
	phoneValid.source=phoneNumber;
	phoneValid.property="text";
	phoneValid.required=true;	
		
	if(Validator.validateAll([universityNameValid, universityNickNameValid, startDateValid, endDateValid,
		addressValid, cityValid, stateValid,countryValid, pinCodeValid, phoneValid]).length!=0){
		
		count++;		
		
	}
	
	if(sessionStartDate.text!=""){
	if(validate.datechecker(sessionStartDate, sessionEndDate)){
		sessionStartDate.errorString=commonFunction.getMessages('startLessEndDate');
		count++;	
		
	}
	else{
		
		sessionStartDate.errorString="";
	}
	}
	
	if(validate.universitydatechecker(sessionStartDate, sessionEndDate)){
		sessionStartDate.errorString=commonFunction.getMessages('diffsession');
		sessionEndDate.errorString=commonFunction.getMessages('diffsession');
		count++;		
		
	}
	
	if(universityNickName.text.length<3 || universityNickName.text==""){
		universityNickName.errorString=commonFunction.getMessages('fieldValidation');
		count++;		
	}
	else{
		universityNickName.errorString="";
	}
	
	if(validate.PhoneNumberLength(phoneNumber.text)){
		phoneNumber.errorString=commonFunction.getMessages('tenDigitsOnly');
		count++;		
	}
	else{
		phoneNumber.errorString="";
	}
	
	 if(validate.PhoneNumberLength(otherPhone.text)==true){
	 	
	 	if(int(otherPhone.text.length)>0){
	 		
	 		otherPhone.errorString=commonFunction.getMessages('tenDigitsOnly');
			count++;
	 	}else{
		 otherPhone.errorString="";
	 } 
	}
	 
	
	if(validate.PhoneNumberLength(faxNumber.text)){
		if(int(faxNumber.text.length)>0){
	 		
	 		faxNumber.errorString=commonFunction.getMessages('tenDigitsOnly');
			count++;
	 	}else{
		faxNumber.errorString="";
	}
	}
	
	
	
	if(count>0){
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
		return false;
	}
	else{
		return true;
	}
	
}





