/**
 * @(#) externalExaminarDetailAction.java
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
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

public var param:Object={};

/**
 * This function calls on the click of the cancel button
 */
public function cancelButtonAction():void{
	this.parentDocument.loaderCanvas.removeAllChildren();
}
/**
 * This function calls on the tick of the Checkbox
 * Copies the Permanent Address Feilds to the Correspondance Address Fields
 */  
public function copyAddress():void{
	if(checkboxField.selected==true)
	{
	corspondenceAddressField.addressLine1.text=permanentAddressField.addressLine1.text;
	corspondenceAddressField.city=permanentAddressField.cityField.selectedLabel;
	corspondenceAddressField.stateField.selectedIndex=permanentAddressField.stateField.selectedIndex;
	corspondenceAddressField.pinField.text=permanentAddressField.pinField.text;
	permanentAddressField.enabled=false;
	corspondenceAddressField.enabled=false;

	}
	else
	{

	permanentAddressField.enabled=true;
	corspondenceAddressField.enabled=true;
	corspondenceAddressField.addressLine1.text="";
	corspondenceAddressField.stateField.selectedIndex=-1;
	corspondenceAddressField.cityField.selectedIndex=-1;
	corspondenceAddressField.pinField.text="";
	
	
	} 	
}
/**
 * This function reset all the field of the interface
 */
public function onReset():void{
	permanentAddressField.enabled=true;
	corspondenceAddressField.enabled=true;
	studentfirstnametext.text="";
	studentfirstnametext.errorString="";
	studentmiddlenametext.text="";
	studentmiddlenametext.errorString="";
	studentlastnametext.text="";
	studentlastnametext.errorString="";
	genderCombo.selectedIndex=-1;
	genderCombo.errorString="";
	designation.text="";
	designation.errorString="";
	colgName.text="";
	colgName.errorString="";
	colgAdd.text="";
	colgAdd.errorString="";
	department.text="";
	department.errorString="";
	if(permanentAddressField.stateField.selectedIndex>=0)
{
	permanentAddressField.cityField.selectedIndex=-1;
	permanentAddressField.cityField.errorString="";
permanentAddressField.stateField.selectedIndex=-1;
permanentAddressField.stateField.errorString="";

}

if(corspondenceAddressField.stateField.selectedIndex>=0)
{
corspondenceAddressField.stateField.selectedIndex=-1;
corspondenceAddressField.cityField.selectedIndex=-1;
corspondenceAddressField.stateField.errorString="";
corspondenceAddressField.cityField.errorString="";
}
//corspondenceAddressField.cityField.selectedIndex=-1;
cityValidator.source=null;
corcityValidator.source=null;
permanentAddressField.addressLine1.text="";
permanentAddressField.addressLine1.errorString="";
corspondenceAddressField.addressLine1.text="";
corspondenceAddressField.addressLine1.errorString="";
corspondenceAddressField.pinField.text="";
permanentAddressField.pinField.text="";
corspondenceAddressField.pinField.errorString="";
permanentAddressField.pinField.errorString="";
officePhoneField.text="";
mobilePhoneField.text="";
faxPhoneField.text="";
officePhoneField.errorString="";
mobilePhoneField.errorString="";
checkboxField.selected=false;
prefixCombo.selectedIndex=-1;
}
/**
 * This function calls on the click of the save button
 */
public function onSave():void{
	if(onValidate()){
		Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOk,questionIcon);		
	}
	else{
		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}


	
}
/**
 * This function validate all the fields of the interface
 */ 
public var flag:Boolean=false;
public function onValidate():Boolean{
	addressValidator.source=permanentAddressField.addressLine1;
	stateValidator.source=permanentAddressField.stateField;
	cityValidator.source=permanentAddressField.cityField;
	firstNameValid.source=studentfirstnametext;
	lastNameValid.source=studentlastnametext;
	genderValid.source=genderCombo;
	designationValid.source=designation;
	departmentValid.source=department;
	colgNameValid.source=colgName;
	colgAddValid.source=colgAdd;
	coraddressValidator.source=corspondenceAddressField.addressLine1;
	corstateValidator.source=corspondenceAddressField.stateField;
	corcityValidator.source=corspondenceAddressField.cityField;	
	
		var corrRequired:Boolean=false;
	if((corspondenceAddressField.addressLine1.text!="")||(corspondenceAddressField.cityField.selectedIndex>-1)||(corspondenceAddressField.stateField.selectedIndex>-1)||(corspondenceAddressField.pinField.text!=""))
	{
		corrRequired=true;
	}
	coraddressValidator.required=corrRequired;
	corstateValidator.required=corrRequired;
	corcityValidator.required=corrRequired;

   if(Validator.validateAll([addressValidator,stateValidator,cityValidator,firstNameValid,lastNameValid,genderValid,designationValid,
                             departmentValid,colgNameValid,colgAddValid,coraddressValidator,corstateValidator,corcityValidator,oficephonevalid,
                             mobilephonevalid,faxphonevalid]).length==0){
                             	flag=true;
                             }
   else{
     flag=false;                          	
     }
     return flag;
}
/**
 * This function send the field value for insertion of the examinar record
 */
public function onOk(event:CloseEvent):void{
	
	if(event.detail==Alert.YES){

		param["firstName"]=studentfirstnametext.text;
		
			
			
		if(studentmiddlenametext.text==""){
			param["middleName"]=null;
			
		}
		else{
        param["middleName"]=studentmiddlenametext.text;
          }
       
        param["lastName"]=studentlastnametext.text;
        
        param["gender"]=genderCombo.selectedItem;
        param["designation"]=designation.text;
        param["department"]=department.text;
		param["collegeName"]=colgName.text;
		param["collegeAddress"]=colgAdd.text;
		param["permanentAddress"]=permanentAddressField.addressLine1.text;
		param["permState"]=permanentAddressField.stateField.selectedLabel;
		param["permCity"]=permanentAddressField.cityField.selectedLabel;
		if(permanentAddressField.pinField.text==""){
			param["pinCode"]="";
		}
		else{
			param["pinCode"]=permanentAddressField.pinField.text;
		}
		if(corspondenceAddressField.addressLine1.text==""){
			param["corsAddress"]=null;
		}
		else{
		param["corsAddress"]=corspondenceAddressField.addressLine1.text;	
		}
		if(corspondenceAddressField.stateField.selectedIndex==-1){
		  param["corsState"]=null;	
		}
		else{
			param["corsState"]=corspondenceAddressField.stateField.selectedLabel;
		}
		if(corspondenceAddressField.cityField.selectedIndex==-1){
			param["corsCity"]=null;
			
		}
		else{
			param["corsCity"]=corspondenceAddressField.cityField.selectedLabel;
		}
		if(corspondenceAddressField.pinField.text==""){
			param["corsPinCode"]=null;
			
		}
		else{
			param["corsPinCode"]=corspondenceAddressField.pinField.text;
		}
		param["officePhoneNumber"]=officePhoneField.text;
		param["mobilePhoneNumber"]=mobilePhoneField.text;
		if(faxPhoneField.text==""){
			param["faxNumber"]=null;
		}
		else{
			param["faxNumber"]=faxPhoneField.text;
		}
		

   param["examinarId"]=examinarId;
   param["prefix"]=prefixCombo.selectedLabel;
 
	insertExaminarDetail.send(param);
		
	}


}
public var examinarId;
public var empId:String="";
public function onGenderChange():void{
/**
 * This function called on the change of the gender Combo
 * It creates the Examinar Id
 */
getExaminarId.send(new Date);
		if(studentmiddlenametext.text==""){
			param["middleName"]=null;
			
		}
		else{
        param["middleName"]=studentmiddlenametext.text;
          }
         
		var str:String=studentlastnametext.text;
		var str1:String=studentfirstnametext.text;
		var str2:String=studentmiddlenametext.text;
		
//		ExaminarId when Last Name >=6
		if(studentlastnametext.text.length>=6){
			var empLastName:String=str.substring(0,6);
//			If  Middle Name text input is empty then firstname is substring to 1st two character
			if(studentmiddlenametext.text==""){
				var empFirstName:String=str1.substring(0,2);
			    empId=empLastName+empFirstName;
			}
//			If  Middle Name text input is not empty then firstname and middlename is substring to  one character
			else{
			 var empMiddleName:String=str2.substring(0,1);
			var empFirstName:String=str1.substring(0,1);
			empId=empLastName+empFirstName+empMiddleName;
			}
		}
//	  ExaminarId when Last Name <6	
		else{
			var suffix:String="";
			for(var i:int=0;i<(6-studentlastnametext.text.length);i++){
				suffix +="x";
			}
			
//			Suffix ("xx") is appended to Examinar Id
			var empLastName:String=studentlastnametext.text+suffix;
//			If  Middle Name text input is empty then firstname is substring to 1st two character
			if(studentmiddlenametext.text==""){
				var empFirstName:String=str1.substring(0,2);
			    empId=empLastName+empFirstName;
			}
//			If  Middle Name text input is not empty then firstname and middlename is substring to  one character
			else{
			 var empMiddleName:String=str2.substring(0,1);
			var empFirstName:String=str1.substring(0,1);
			empId=empLastName+empFirstName+empMiddleName;
			}
			
		}
// Appending Integer in the end of Examinar Id
   examinarId=empId+1;

}
/**
 * This function called on the success of the Insert Examinar Detail Service
 */
public function onInsertSuccess(event:ResultEvent):void{

	var detailList:XML=event.result as XML;
	if(detailList.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
           	    var url:String="";
           	    url=commonFunction.getConstants('navigateHome');
	   var urlRequest:URLRequest=new URLRequest(url);
	   urlRequest.method=URLRequestMethod.POST;
	    navigateToURL(urlRequest,"_self");
            }	
	
	if(String(detailList.Details.list_values).search("success")>-1){
		var exmId:String=String(detailList.Details.list_values).substring(7,String(detailList.Details.list_values).length);
		Alert.show(commonFunction.getMessages('submittedSuccessfully')+'\nExaminerId is - '+exmId,commonFunction.getMessages('confirm'),0,null,null,successIcon);
	    onReset();
	}
	else if(detailList.Details.list_values=="duplicate"){
		Alert.show(commonFunction.getMessages('duplicateEntry'),
	commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else
	{
	Alert.show(commonFunction.getMessages('failure'),commonFunction.getMessages('error'),0,null,null,errorIcon);

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
 * The function retrives the list of Examinar Names  
 * 
 * */
public var arry:ArrayCollection=new ArrayCollection();
public var extExaminarId:String;
public function onExaminarIdSuccess(event:ResultEvent):void{	
		var examinarIdList:XML=event.result as XML;
	if(examinarIdList.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
           	    var url:String="";
           	    url=commonFunction.getConstants('navigateHome');
	   var urlRequest:URLRequest=new URLRequest(url);
	   urlRequest.method=URLRequestMethod.POST;
	    navigateToURL(urlRequest,"_self");
            }			
    var flag:Boolean=false;
    var value:int=null;
		for each(var o:Object in examinarIdList.show){
			if(o.ExaminarId==examinarId){	
				var temp:String=examinarId;
				value=int(temp.substring(8,9));
				flag=true;
			
			}
			else{
				flag=false;
			}

if(flag==true){
	var tempValue:int=value+1;
	examinarId=empId+tempValue;

}

}


}












