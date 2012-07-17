// ActionScript file
import common.commonFunction;

import mx.validators.NumberValidator;
import mx.validators.StringValidator;
import mx.validators.Validator;


public var componentCodeValid:StringValidator = new StringValidator();
public var componentDescValid:StringValidator = new StringValidator();
public var firstFlagValid:NumberValidator = new NumberValidator();
public var secondFlagValid:StringValidator = new StringValidator();
public var thirdFlagValid:StringValidator = new StringValidator();

public function validateForm(groupDB:XML,group:String):Boolean
{
	var flag:Boolean=true;
	
	var min:int=int(groupDB.group.(@group_description==group).@min.toString());
	var max:int=int(groupDB.group.(@group_description==group).@max.toString());
	
	firstFlagValid.source = firstFlagTxtInput;
	firstFlagValid.property = "text";
	
	if(groupDB.group.(@group_description==group).@group_code.toString()=='ENTTYP')
	{
		firstFlagValid.maxValue=50;
		firstFlagValid.minValue=1;
	}
	else
	{
		firstFlagValid.maxValue=1;
		firstFlagValid.minValue=0;
	}
	firstFlagValid.required=true;
	
	secondFlagValid.source = secondFlagTxtInput;
	secondFlagValid.property = "text";
	secondFlagValid.maxLength=1;
	secondFlagValid.minLength=1;
	secondFlagValid.required=true;
	
	thirdFlagValid.source = thirdFlagTxtInput;
	thirdFlagValid.property = "text";
	thirdFlagValid.maxLength=1;
	thirdFlagValid.minLength=1;
	thirdFlagValid.required=true;
	
	componentCodeValid.source = componentCodeTxtInput;
	componentCodeValid.property = "text";
	componentCodeValid.maxLength=max;
	componentCodeValid.minLength=min;
	componentCodeValid.required=true;
	componentCodeValid.tooLongError=commonFunction.getMessages('lengthTooLong');
	componentCodeValid.requiredFieldError="Please enter the Component Code";
	
	
	componentDescValid.source = componentDescriptionTxtArea;
	componentDescValid.property = "text";
	componentDescValid.maxLength=45;
	componentDescValid.minLength=1;
	componentDescValid.required=true;
	componentDescValid.tooLongError=commonFunction.getMessages('lengthTooLong');
	componentDescValid.requiredFieldError="please enter Component Description";
	
	if(Validator.validateAll([componentCodeValid, componentDescValid]).length!=0){
		flag=false;		
	}
	
	if(!commonFunction.checkForComma(componentCodeTxtInput.text))
	{
		flag=false;
		componentCodeTxtInput.errorString=commonFunction.getMessages('invalidCharInData');
	}
	
	if(!commonFunction.checkForComma(componentDescriptionTxtArea.text))
	{
		flag=false;
		componentDescriptionTxtArea.errorString=commonFunction.getMessages('invalidCharInData');
	}
	
	return flag;
}