/**
 * @(#) employeeMasterAction.java
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

//This function Set Focus on 1st Field Entity combobox
public function setFirstFocous(object:UIComponent):void
            {
	
	infoObject["userId"] = new Date;	
	
	getInputDetails.send(infoObject);
	
	getDesignations.send(infoObject);
	
	getgenderDetails.send(infoObject);
	
	getCategoryDetails.send(infoObject);
	
	getMinorityDetails.send(infoObject);
	
	getPensionDetails.send(infoObject);
	
                object.setFocus();
                object.drawFocus(true);
				

	var endMonth:Date=new Date();
	dateofBirthField.selectableRange={rangeEnd :endMonth};
	dateOfJoiningField.selectableRange={rangeEnd :endMonth};

     }

/**
 * The function retrives the list of programs for the 
 * concerned university idSS
 * */
[Bindable]
public var details: XML;
public var detailslist:ArrayCollection;
public function onSuccess(event:ResultEvent):void{
	
	details=event.result as XML;
	
	
	detailslist =new ArrayCollection();
	
	for each (var o:Object in details.role){
		
		detailslist.addItem({id:o.id,description:o.description});
		
	}		
		parententityCombo.dataProvider = detailslist;
		parententityCombo.labelField = "description";
		
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	
	Mask.close();
	
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);	
	
}

/**
 * The function retrives the list of programs for the 
 * concerned university id
 * */
[Bindable]
public var designationsDetails: XML;
public var designationlist:ArrayCollection;
public function onDesignationsSuccess(event:ResultEvent):void{
	
	designationsDetails=event.result as XML;
	
	designationlist =new ArrayCollection();
	
	for each (var o:Object in designationsDetails.role){
		
		designationlist.addItem({id:o.id,description:o.description});
		
	}		
		designationCombo.dataProvider = designationsDetails.role.description;		
}

/**
 * The function retrives the list of genders for the 
 * concerned university id
 * */
[Bindable]
public var otherDetails: XML;
public var otherlist:ArrayCollection;
public function onGenderDetailsSuccess(event:ResultEvent):void{
	
	otherDetails=event.result as XML;
	
	otherlist =new ArrayCollection();
	
	for each (var o:Object in otherDetails.role){
		
		otherlist.addItem({id:o.id,description:o.description});
		
	}		
		genderCombo.dataProvider = otherDetails.role.description;		
}

/**
 * The function retrives the list of categories for the 
 * concerned university id
 * */
[Bindable]
public var categoryDetails: XML;
public var categorylist:ArrayCollection;
public function onCategoryDetailsSuccess(event:ResultEvent):void{
	
	categoryDetails=event.result as XML;
	
	categorylist =new ArrayCollection();
	
	for each (var o:Object in categoryDetails.role){
		
		categorylist.addItem({id:o.id,description:o.description});
		
	}		
		categoryCombo.dataProvider = categoryDetails.role.description;
		
}

/**
 * The function retrives the list of minorities groups for the 
 * concerned university id
 * */
[Bindable]
public var minorityDetails: XML;
public var minoritylist:ArrayCollection;
public function onMinorityDetailsSuccess(event:ResultEvent):void{
	
	minorityDetails=event.result as XML;
	
	minoritylist =new ArrayCollection();
	
	for each (var o:Object in minorityDetails.role){
		
		minoritylist.addItem({id:o.id,description:o.description});
		
	}		
		minorityCombo.dataProvider = minorityDetails.role.description;
		
}

/**
 * The function retrives the list of pension Descriptions for the 
 * concerned university id
 * */
[Bindable]
public var pensionDetails: XML;
public var pensionlist:ArrayCollection;
public function onPensionDetailsSuccess(event:ResultEvent):void{
	
	pensionDetails=event.result as XML;
	
	pensionlist =new ArrayCollection();
	
	for each (var o:Object in pensionDetails.role){
		
		pensionlist.addItem({id:o.id,description:o.description});
		
	}
		pensionCombo.dataProvider = pensionDetails.role.description;
		
}


public function saveForm():void
{	
	if(validateForm())
	{
		if((checkEmployeeMiddleName())&&(checkQualification()))
		{			
			Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);		
			
			}
	    }
	else
	{
		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
}

public function onOK(event:CloseEvent):void{
			
			if(event.detail==Alert.YES){				
				
			infoObject["parentEntityId"] = parententityCombo.selectedItem.id;
			infoObject["designationId"] = designationsDetails.role.(description==designationCombo.selectedLabel).id;			
			infoObject["dateOfJoining"] = dateFormatter.format(dateOfJoiningField.text);
			infoObject["empFirstName"] = studentfirstnametext.text;
			infoObject["empMiddleName"] = studentmiddlenametext.text;
			infoObject["empLastName"] = studentlastnametext.text;
			infoObject["primaryEmailId"] = primarymailtext.text;
			infoObject["secondaryEmailId"] = secondarymailtext.text;
			infoObject["dateOfBirth"] = dateFormatter.format(dateofBirthField.text);
			infoObject["qualification"] = qualificationtext.text;
			infoObject["gender"] = otherDetails.role.(description==genderCombo.selectedLabel).id;
			infoObject["category"] = categoryDetails.role.(description==categoryCombo.selectedLabel).id;
			infoObject["permanentAddress"] = permanentAddressField.addressLine1.text;
			infoObject["state"] = permanentAddressField.stateField.selectedLabel;
			infoObject["city"] = permanentAddressField.cityField.selectedLabel;
			infoObject["pinCode"] = permanentAddressField.pinField.text;
			infoObject["physical"] =  commonFunction.returnSelection(handicapped);
			infoObject["net"] = commonFunction.returnSelection(netQualified);
			infoObject["postGraduate"] = commonFunction.returnSelection(grad);
			infoObject["minorityFlag"] = commonFunction.returnSelection(minorGroup);
			infoObject["pension"] = pensionDetails.role.(description==pensionCombo.selectedLabel).id;
			infoObject["updateEmployeeCode"] = employeeCode.text;
			
			if(minorityCombo.selectedIndex==-1)
			{				
				infoObject["minorityCode"] ="";				
			}else
			{
			infoObject["minorityCode"] = minorityDetails.role.(description==minorityCombo.selectedLabel).id;
			}
			
			if(corspondenceAddressField.addressLine1.text==null)
			{				
				infoObject["coresspondenceAddress"] ="";				
			}else
			{
			infoObject["coresspondenceAddress"] = corspondenceAddressField.addressLine1.text;
			}
			if(corspondenceAddressField.stateField.selectedIndex==-1)
			{
				infoObject["coresState"] ="";
			}else
			{
				infoObject["coresState"] = corspondenceAddressField.stateField.selectedLabel;	
			}
			if(corspondenceAddressField.cityField.selectedIndex==-1)
			{
				infoObject["coresCity"] ="";
			}else
			{
				infoObject["coresCity"] = corspondenceAddressField.cityField.selectedLabel;	
			}
			if(corspondenceAddressField.pinField.text==null)
			{
				infoObject["coresPinCode"] =null;	
			}else
			{
				infoObject["coresPinCode"] = corspondenceAddressField.pinField.text;	
			}
			infoObject["officePhone"] = phones.officePhoneField.text;
			if(phones.homePhoneField.text==null)
			{
				infoObject["homePhone"] =null;
			}else
			{
				infoObject["homePhone"] = phones.homePhoneField.text;
			}
			if(phones.otherPhoneField.text==null)
			{
				infoObject["otherPhone"] =null;
			}else
			{
				infoObject["otherPhone"] = phones.otherPhoneField.text;
			}
			if(phones.faxPhoneField.text==null)
			{
				infoObject["faxPhone"] =null;	
			}else
			{
				infoObject["faxPhone"] = phones.faxPhoneField.text;	
			}
			infoObject["activity"] = "insert";
			setEmployeeProfile.send(infoObject);
				
				Mask.show(commonFunction.getMessages('pleaseWait'));
				
			}
			
		}

/**
 * method executed on processing of the request
 * */
[Bindable]
public var successDetails: XML;
private function oninsertSuccess(event:ResultEvent):void
{
	successDetails=event.result as XML;
	
	Mask.close();
	
	if(successDetails.Details.list_values=="duplicate"){
		
		Alert.show(commonFunction.getMessages('duplicateEmployee'),
               commonFunction.getMessages('failure'),0,null,null,errorIcon); 
		
	}else if(String(successDetails.Details.list_values).search("false")>-1){
               
               Alert.show(commonFunction.getMessages('errorInsert')+"\n"+String(successDetails.Details.list_values),
               commonFunction.getMessages('error'),0,null,null,errorIcon);                        
               
       }else if(successDetails.Details.list_values=="duplicateemployeecode"){
       
       			 Alert.show("Employee already exists with the same employee code",
               commonFunction.getMessages('failure'),0,null,null,errorIcon);  
       
       }else{
               	addressValidator.source=null;
    			coraddressValidator.source=null;
                Alert.show("Employee Created successfully with "+successDetails.Details.list_values+" as Employee Code",
               (commonFunction.getMessages('saved')),0,null,OnSuccess,successIcon);
       
       }	
}

public function OnSuccess(event:CloseEvent):void{
	
resetForm();
	
}

protected var flag:Boolean=false;
public function validateForm():Boolean
{
	addressValidator.source=permanentAddressField.addressLine1;
	stateValidator.source=permanentAddressField.stateField;
	cityValidator.source=permanentAddressField.cityField;
	joindate.source=dateOfJoiningField;
	entityvalid.source=parententityCombo;
	designationvalid.source=designationCombo;
	categoryvalid.source=categoryCombo;
	gendervalid.source=genderCombo;
	coraddressValidator.source=corspondenceAddressField.addressLine1;
	corstateValidator.source=corspondenceAddressField.stateField;
	corcityValidator.source=corspondenceAddressField.cityField;
	pensionValidator.source=pensionCombo;
	minorValidator.source=minorityCombo;
	empcodevalid.source=employeeCode;
	
	var corrRequired:Boolean=false;
	if((corspondenceAddressField.addressLine1.text!="")||(corspondenceAddressField.cityField.selectedIndex>-1)||(corspondenceAddressField.stateField.selectedIndex>-1)||(corspondenceAddressField.pinField.text!=""))
	{
		corrRequired=true;
	}
	
	if(commonFunction.returnSelection(minorGroup)=="Y"){
		minorValidator.required=true;		
		}else{
			minorValidator.required=false;
		}
	
	coraddressValidator.required=corrRequired;
	corstateValidator.required=corrRequired;
	corcityValidator.required=corrRequired;
	corspondenceAddressField.pinValidator.required=corrRequired;
if(Validator.validateAll([joindate,
	entityvalid,designationvalid,employeeFirstname,employeeLastname,primaryemailVal,secondaryemailVal,
	birthdate,gendervalid,addressValidator,stateValidator,faxphonevalid,pensionValidator,
	cityValidator,permanentAddressField.pinValidator,coraddressValidator,corstateValidator,corcityValidator,corspondenceAddressField.pinValidator,
	qualificationvalid,categoryvalid,oficephonevalid,homephonevalid,otherphonavalid,minorValidator,empcodevalid]).length!=0)
		{
		flag=false;	
			
	    }
	
	else
		{
		flag=true;	
	    }
	return flag;
}




public function cancelButtonAction():void
{
this.parentDocument.loaderCanvas.removeAllChildren();
}

public function setDesignation():void
{
	designationCombo.dataProvider=parententityCombo.selectedItem.designation;
}	
		
public function resetForm():void
{
permanentAddressField.enabled=true;
corspondenceAddressField.enabled=true;

if(parententityCombo.selectedIndex>=0)
{
parententityCombo.selectedIndex=-1;
designationCombo.selectedIndex=-1;
}

dateOfJoiningField.text="";
studentfirstnametext.text="";
studentmiddlenametext.text="";
studentlastnametext.text="";
primarymailtext.text="";
secondarymailtext.text="";
dateofBirthField.text="";
genderCombo.selectedIndex=-1;
qualificationtext.text="";
categoryCombo.selectedIndex=-1;
corspondenceAddressField.addressLine1.text="";
permanentAddressField.addressLine1.text="";
pensionCombo.selectedIndex = -1;
minorityCombo.selectedIndex = -1;
//corspondenceAddressField.stateField.selectedIndex=-1;
if(permanentAddressField.stateField.selectedIndex>=0)
{
	permanentAddressField.cityField.selectedIndex=-1;
permanentAddressField.stateField.selectedIndex=-1;

}

if(corspondenceAddressField.stateField.selectedIndex>=0)
{
corspondenceAddressField.stateField.selectedIndex=-1;
corspondenceAddressField.cityField.selectedIndex=-1;
}
//corspondenceAddressField.cityField.selectedIndex=-1;
cityValidator.source=null;
corcityValidator.source=null;
corspondenceAddressField.pinField.text="";
permanentAddressField.pinField.text="";
phones.officePhoneField.text="";
phones.homePhoneField.text="";
phones.otherPhoneField.text="";
phones.faxPhoneField.text="";
checkboxField.selected=false;
parententityCombo.errorString="";
designationCombo.errorString="";
dateOfJoiningField.errorString="";
studentfirstnametext.errorString="";
studentmiddlenametext.errorString="";
studentlastnametext.errorString="";
primarymailtext.errorString="";
secondarymailtext.errorString="";
dateofBirthField.errorString="";
genderCombo.errorString="";
corspondenceAddressField.addressLine1.errorString="";
permanentAddressField.addressLine1.errorString="";
corspondenceAddressField.stateField.errorString="";
permanentAddressField.stateField.errorString="";
corspondenceAddressField.cityField.errorString="";
permanentAddressField.cityField.errorString="";
corspondenceAddressField.pinField.errorString="";
permanentAddressField.pinField.errorString="";	

qualificationtext.errorString="";
categoryCombo.errorString="";	
phones.officePhoneField.errorString="";
phones.homePhoneField.errorString="";
phones.otherPhoneField.errorString="";

minorGroup.selected = false;
handicapped.selected = false;
netQualified.selected = false;
grad.selected = false;

minorityCombo.errorString = "";
pensionCombo.errorString = "";
employeeCode.text="";
employeeCode.errorString="";


}			

public function copyaddress():void
{
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


public function checkEmployeeMiddleName():Boolean
{
    var sMname:String=studentmiddlenametext.text;
  
   if(sMname.length>0){
   if(!((sMname.charCodeAt(0) > 64) && (sMname.charCodeAt(0) < 91)))
   {
   	 if (!((sMname.charCodeAt(0) > 96) && (sMname.charCodeAt(0) < 123)))
   	 {
   	 studentmiddlenametext.errorString=commonFunction.getMessages('eNTERFirstLettersCharinMiddlename');
   	 Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
   	 return false;
   	 }
   }
   } 
    
    for (var i:int=0;i<sMname.length;i++)
         {
            if (!((sMname.charCodeAt(i) > 64) && (sMname.charCodeAt(i) < 91))) //not between A to Z
             {
                if (!((sMname.charCodeAt(i) > 96) && (sMname.charCodeAt(i) < 123))) //not between a to z
                 {
                    if ((sMname.charCodeAt(i) != 32) && (sMname.charCodeAt(i) != 46)) //not space, not ., neither ( nor )
                     {
                        if (!((sMname.charCodeAt(i) <47) && (sMname.charCodeAt(i) >58)))
                        {
                        studentmiddlenametext.errorString=commonFunction.getMessages('enterValidCharinMiddlename');
                        Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
                        return false;
                        }
                     } 
                    
                   }
                }
                  
}
return true;
}

public function checkQualification():Boolean
{
   var qual:String=qualificationtext.text;
   if(qual.length>0){
   if(!((qual.charCodeAt(0) > 64) && (qual.charCodeAt(0) < 91)))
   {
   	 if (!((qual.charCodeAt(0) > 96) && (qual.charCodeAt(0) < 123)))
   	 {
   	 qualificationtext.errorString=commonFunction.getMessages('eNTERFirstLettersCharinQualification');
   	 Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
   	 return false;
   	 }
   }
   }
   for(var g:int=0;g<qual.length;g++)
   {
     if (!((qual.charCodeAt(g) > 64) && (qual.charCodeAt(g) < 91))) //not between A to Z
     {
      if (!((qual.charCodeAt(g) > 96) && (qual.charCodeAt(g) < 123))) //not between a to z
      {
       if ((qual.charCodeAt(g) != 32) && (qual.charCodeAt(g) != 46) &&
          (qual.charCodeAt(g) != 40) && (qual.charCodeAt(g) != 41)) //not space, not ., neither ( nor )
        {                
		  qualificationtext.errorString=commonFunction.getMessages('eNTERQualificationinValidLetters');
          Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
          return false;
        } 
      }
     }
   }
return true;
}

public function onChange():void{
	
	if(commonFunction.returnSelection(minorGroup) == "Y"){
		
		minorLabel.visible = true;
		minorityCombo.visible = true;	
	}else{
		
		minorLabel.visible = false;
		minorityCombo.visible = false;
		minorityCombo.selectedIndex=-1;		
	}
	
}

