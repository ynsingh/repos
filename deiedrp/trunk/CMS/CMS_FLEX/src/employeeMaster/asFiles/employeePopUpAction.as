/**
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
import mx.controls.Alert;
import mx.validators.Validator; 
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

import mx.managers.PopUpManager;
import common.commonFunction;
public var resetFunction:Function;
public var parentObject:Object;

		private function resetThis():void
		{
			resetFunction(parentObject,this);
		}

   		private function initializeForEsc(evt:KeyboardEvent):void
   		{
    	 if (evt.keyCode == Keyboard.ESCAPE)
        {
    	   closePopup();
         }
        }
        
      public function closePopup():void
      {
	          PopUpManager.removePopUp(this);
      }
      
      






public function saveForm():void
{
	if(validateForm())
	{
		if((checkEmployeeMiddleName())&&(checkQualification()))
		{
		Alert.show((commonFunction.getMessages('submittedSuccessfully')),(resourceManager.getString('messages','saved')),0,null,null,successIcon);}
	    }
	else
	{
		Alert.show((resourceManager.getString('messages','pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
}

protected var flag:Boolean=false;
public function validateForm():Boolean
{
	addressValidator.source=permanentAddressField.addressLine1;
	stateValidator.source=permanentAddressField.stateField;
	cityValidator.source=permanentAddressField.cityField;
	coraddressValidator.source=corspondenceAddressField.addressLine1;
	corstateValidator.source=corspondenceAddressField.stateField;
	corcityValidator.source=corspondenceAddressField.cityField;
	pensionValidator.source=pensionCombo;
	minorValidator.source=minorityCombo;
	
	var corrRequired:Boolean=false;
	if((corspondenceAddressField.addressLine1.text!="")||(corspondenceAddressField.cityField.selectedIndex>-1)||(corspondenceAddressField.stateField.selectedIndex>-1)||(corspondenceAddressField.pinField.text!=""))
	{
		corrRequired=true;
	}
	
	if(minorGroup.selected==true){
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
	birthdate,gendervalid,addressValidator,stateValidator,faxphonevalid,
	cityValidator,permanentAddressField.pinValidator,coraddressValidator,corstateValidator,corcityValidator,
	corspondenceAddressField.pinValidator,
	qualificationvalid,categoryvalid,oficephonevalid,homephonevalid,otherphonavalid,pensionValidator,minorValidator]).length!=0)
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
	closePopup();
}

public function setDesignation():void
{
	designationCombo.dataProvider=parententityCombo.selectedItem.designation;
}	
		
public function copyaddress():void
{
	if(checkboxField.selected==true)
	{
	corspondenceAddressField.addressLine1.text=permanentAddressField.addressLine1.text;
	corspondenceAddressField.city=permanentAddressField.cityField.selectedLabel;
	corspondenceAddressField.stateField.selectedIndex=permanentAddressField.stateField.selectedIndex
	;
	
	corspondenceAddressField.pinField.text=permanentAddressField.pinField.text;
	
//	corspondenceAddressField.addressLine1.editable=false;
//	permanentAddressField.addressLine1.editable=false;
//	
//	permanentAddressField.pinField.editable=false;
//	corspondenceAddressField.pinField.editable=false;
//	permanentAddressField.stateField.enabled=false;
//	permanentAddressField.cityField.enabled=false;
//	corspondenceAddressField.stateField.enabled=false;
//	corspondenceAddressField.cityField.enabled=false;
permanentAddressField.enabled=false;
corspondenceAddressField.enabled=false;

	}
	else
	{
//     corspondenceAddressField.addressLine1.editable=true;
//	permanentAddressField.addressLine1.editable=true;
//	
//	permanentAddressField.pinField.editable=true;
//	corspondenceAddressField.pinField.editable=true;
//	permanentAddressField.stateField.enabled=true;
//	permanentAddressField.cityField.enabled=true;
//	corspondenceAddressField.stateField.enabled=true;
//	corspondenceAddressField.cityField.enabled=true;
corspondenceAddressField.enabled=true;
permanentAddressField.enabled=true;
		
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

//public function checkEmployeeFirstName():Boolean
//{
//    var sFname:String=studentfirstnametext.text;
//    for (var i:int=0;i<sFname.length;i++)
//    {
//    	if ((sFname.charCodeAt(i) == 32))
//    	{
//    		
//    		studentfirstnametext.errorString=commonFunction.getMessages('dontuseSpaceinFirstname');
//    		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
//    		return false;
//    	}
//    }
//    	return true;
//}
//
//public function checkEmployeeLastName():Boolean
//{
//    var sLname:String=studentlastnametext.text;
//    for (var i:int=0;i<sLname.length;i++)
//    {
//    	if ((sLname.charCodeAt(i) == 32))
//    	{
//    		
//    		studentlastnametext.errorString=commonFunction.getMessages('dontuseSpaceinLastname');
//    		Alert.show((commonFunction.getMessages('pleasecheckEntriesinRed')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
//    		return false;
//    	}
//    }
//    	return true;
//}

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
   
   