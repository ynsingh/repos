
import common.Mask;
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.ComboBox;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;

import programMaster.BranchWithSpecialization;
import programMaster.EditBasicProgramDetails;
import programMaster.StartDate;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

public var bwsIdArray:Array=new Array();
public var startDateIdArray:Array=new Array();
public var branchSpecializationList:Array=new Array();
public var specializationExist:Boolean=false;
public var selectedType:String;
public var selectedMode:String;
public var c:int=0;
var editScreen:EditBasicProgramDetails=new EditBasicProgramDetails();
public var userId:String;
public var myUrl:String=commonFunction.getConstants('url')+ "/programmaster/methodUniversityProgramType.htm";

var progDetails:Object=new Object();



public function init_Url():String{
	
	return myUrl;
}
 
 public function init():void{
 	var branchWithSpec:BranchWithSpecialization=new BranchWithSpecialization();		
	    bwsIdArray.push(branchWithSpec);
	    addBranchWithSpecialization.addChild(branchWithSpec);
 	init_Url();
 	httpType();
 }
		

public function validateBasicProgramDetails():Boolean
{
	var i:int=Validator.validateAll([programCodeValidator,programModeValidator,programTypeValidator,programNameValidator,
	              printAggregateValidator,tenCodesValidator,ugOrPgValidator,creditsRequiredValidator,dgpaValidator,programDescriptionValidator]).length;
	    
	if(i==0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

public function goToDurationDetails():void
{
	if(validateBasicProgramDetails())
	{

/*
* code temporarily commented
* This code is for checking duplicate program code
*/

// 		var j:int=0;
//             var a:ArrayCollection=new ArrayCollection();
//        for each(var  s:XML in  codeXML.branch){
//        	a.addItem(s);
//        }
//        
//        for each(var o:Object in a)
//        {
//        	if(o.branchCode.toString().toUpperCase() == programCodeText.text.toString().toUpperCase())
//        	{
//        		j++;  		
//        	}
//        }
//        
//        if(j==0)
//        {
			//   programMasterStack.selectedIndex=1;
//        }
//        else
//        {
//	        programCodeText.setFocus();
//	   		Alert.show(resourceManager.getString('Messages','duplicateProgramCode'));
//        }



/*
* This code is for checking duplicate program name
*/

 		var j:int=0;
             var a:ArrayCollection=new ArrayCollection();
        for each(var  s:XML in  codeXML.branch){
        	a.addItem(s);
        }
        
        for each(var o:Object in a)
        {
        	if(o.branchName.toString().toUpperCase() == programNameText.text.toString().toUpperCase())
        	{
        		j++;  		
        	}
        }
        
        if(j==0)
        {
			programMasterStack.selectedIndex=1;
        }
        else
        {
	        programNameText.setFocus();
	   		Alert.show("Duplicate program Name");
        }



		
	}
	else
	{
		Alert.show(resourceManager.getString('Messages','pleasecheckEntriesinRed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	}
}
public function addStartDayMonth():void
{
	startDateIdArray.length=0;
	addStartDatePanel.removeAllChildren();
	var t:int=noOfTime.value;
	if(t>0)
	{
		addStartDatePanel.visible=true;
	}
	else
	{
		addStartDatePanel.visible=false;
	}
	for(var i:int=0;i<t;i++)
	{
	    var startDateSelecter:StartDate=new StartDate();
	    addStartDatePanel.addChild(startDateSelecter);
	    startDateIdArray.push(startDateSelecter);
	}
}
public function showDurationBox():void
{
	if(fixedDurationRadio.selectedValue=="Y")
	{
		maxDurationText.value=0;
		maxDurationText.enabled=false;
	}
	else
	{
		maxDurationText.enabled=true;
	}
}

public function goBackToBasicDetails():void
{
	programMasterStack.selectedIndex=0;
}

public function goToBranchDetails():void
{
	var validationError:Boolean=false;
	var minDuration:int=0;
	var maxDuration:int=0;
	if(fixedDurationRadio.selectedValue=="N")
	{
		if(minDurationText.value>=maxDurationText.value)
		{
			validationError=true;
			minDurationText.errorString=resourceManager.getString('Messages','invalidEntry');
		    maxDurationText.errorString=resourceManager.getString('Messages','invalidEntry');
		}
		else
		{
			minDuration=int(minDurationText.value);
			maxDuration=int(maxDurationText.value);
			minDurationText.errorString="";
		    maxDurationText.errorString="";
		}
	}
	else
	{
		minDuration=int(minDurationText.value);
		maxDuration=minDuration;
		minDurationText.errorString="";
		maxDurationText.errorString="";
	}
	
	if(noOfTime.value==0)
	{
		noOfTime.errorString=resourceManager.getString('Messages','programMustStartAtleastOneTimeInSession');
		validationError=true;
	}
	else
	{
		noOfTime.errorString="";
	}
	for each(var startDate:StartDate in startDateIdArray)
	{
		if(startDate.startDateField.selectedValue!="")
		{
			startDate.startDateField.errorString="";
		}
		else
		{
			startDate.startDateField.errorString=resourceManager.getString('Messages','selectADate');
			validationError=true;
		}
	}
	if(!validationError)
	{
		programMasterStack.selectedIndex=2;
	}
	else
	{
		Alert.show(resourceManager.getString('Messages','pleasecheckEntriesinRed'),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
	}
}


public function addABranchWithSpecialization():void
{

	var addRow:Boolean=true;
	for each(var temp:BranchWithSpecialization in bwsIdArray)
	{
		if(temp.checked.selected)
		{
		    temp.checked.errorString="";
	        addRow=true && addRow;
		}
		else
		{
			temp.checked.errorString=resourceManager.getString('Messages','notSelected');
			addRow=false;
		}
	}
	
	if(addRow)
	{
		var branchWithSpec:BranchWithSpecialization=new BranchWithSpecialization();
		
	    bwsIdArray.push(branchWithSpec);
	    addBranchWithSpecialization.addChild(branchWithSpec);
	}
}

public function goBackToDurationDetails():void
{
	programMasterStack.selectedIndex=1;
}

public function validateSelectedRow():void{
	
	var flag:Boolean=false;
	
	for each(var bws:BranchWithSpecialization in bwsIdArray){
		
		if(bws.checked.selected){
			flag=true;	
		}
	}
	
	if(flag){
		submitProgramDetailsForm();
	}
	else{
		Alert.show(commonFunction.getMessages('pleaseSelectRow'),commonFunction.getMessages('info'),4,null,null,infoIcon);
	}
}

public function submitProgramDetailsForm():void
{
	var validationError:Boolean=false;
	branchSpecializationList.length=0;
	c=0;
	var selectedBranches:Array=new Array();
	try
	{
	    for each(var bws:BranchWithSpecialization in bwsIdArray)
	    {
	    	if(bws.checked.selected)
	    	{
	    		
	    		if(bws.branchCombo.selectedIndex){
	    			
	    		}
	    		
	    		var branchCode:String=new String();
	    		var specializationCode:String=new String();
	    		
	    		if((bws.branchCombo.selectedIndex<0)||(bws.specializationCombo.selectedIndex<0)||(bws.ActiveSemCombo.selectedIndex<0))
	    		{
	    			bws.err.visible=true;
	    			bws.errorString=resourceManager.getString('Messages','invalidEntry');
	    	    	validationError=true;
	    	    	Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('warning'),4,null,null,errorIcon);
	    	    }
	    	    else
	    	    {
	    	    	
	    	    	 branchCode=bws.branchCombo.selectedItem.branchCode;
	    		 specializationCode=bws.specializationCombo.selectedItem.branchCode;
	    		 
	    		 bws.err.visible=false;
	    		bws.errorString="";
	    	    	
	    	    	if(selectedBranches.indexOf(branchCode+""+specializationCode)<0)
	    			{
	    				branchCode=bws.branchCombo.selectedItem.branchCode.toString();
	    				specializationCode=bws.specializationCombo.selectedItem.branchCode.toString();
	    				bws.branchCombo.errorString="";
	    			    bws.specializationCombo.errorString="";
	    			    selectedBranches.push(branchCode+""+specializationCode);
	    	            var specList:Array=new Array();
	    	            bws.specializationCombo.errorString="";
	    	    	    specList.push(branchCode);
	    	            specList.push(specializationCode);
	    	            if(specializationCode==commonFunction.getConstants('none'))
	    	            {
	    	            	specList.push(";");
	    	            }
	    	            else
	    	            {
	    	            	specList.push(bws.ActiveSemCombo.selectedItem.branchCode.toString());
	    	            }
	    	            branchSpecializationList[c++]=specList;
	    			}
	    			else
	    			{
	    				bws.branchCombo.errorString=resourceManager.getString('Messages','duplicateEntry');
	    	    	    bws.specializationCombo.errorString=resourceManager.getString('Messages','duplicateEntry');
	    	    	    validationError=true;
	    			}
	    	    }
	    	}
	    	else
	    	{
	    		bws.checked.errorString="";
	    		bws.branchCombo.errorString="";
	    		bws.specializationCombo.errorString="";
	    	}
	    }
	}
	catch(e1:Error){
	Alert.show(e1.message+"");
	}
	finally
	{
	    if(!validationError)
	    {
	    	 var branch:String="";
	    	 var spec:String="";
	    	 var activeSem="";
	    	var startDate="";
	    	
	    	for each(var startDateObj:StartDate in startDateIdArray)
	    {	    	
	    	startDate=startDate+startDateObj.startDateField.selectedValue+"|";	    	
	    }	
	    	
	    	
	    	// getting branch and specialization list with corresponding active semester 
	        for each (var obj:Object in branchSpecializationList)
	        {
	           
               branch=branch+obj[0].toString()+"|";
	            spec=spec+obj[1].toString()+"|";
	            activeSem=activeSem+obj[2].toString()+"|";
	        }        
                var branchLength:int=branch.length;
	    	 var specLength:int=spec.length;
              //adding program data to input object 
               
               
               switch(branchLength){
               	case 0:  progDetails["branch_exists"]=0;
               			branch=commonFunction.getConstants('none').toString()+"|";
               			spec=commonFunction.getConstants('none').toString()+"|";
               			break;
               	default: 	progDetails["branch_exists"]=1;
               			break;
               }
               
                switch(specLength){
               		case 0: progDetails["specialization_exists"]=0;
                             			break;
            		default: progDetails["specialization_exists"]=1;
            		               			break;
               }              
               
               
               progDetails["program_code"]=programCodeText.text;
               progDetails["program_name"]=programNameText.text;
               progDetails["program_description"]=programDescription.text;
               progDetails["program_type"]=typeXML.type.(typeName==programTypeCombo.selectedLabel).typeCode
               progDetails["program_mode"]=modeXML.mode.(modeName==programModeCombo.selectedLabel).modeCode
               progDetails["no_of_terms"]=noOfTermsText.value;               
			   progDetails["total_credits"]=totalCreditsText.text;
		       progDetails["number_of_attempt_allowed"]=maxAttemptAllowedText.value;
		       progDetails["max_number_of_fail_subjects"]=maxFailSubjectText.value;
		       progDetails["fixed_duration"]=fixedDurationRadio.selectedValue;
		       progDetails["ug_pg"]=ugpgXML.option.(name==ugOrPgCombo.selectedLabel).code;
		       progDetails["ten_codes"]=tenCodesXML.option.(name==tenCodesCombo.selectedLabel).code;
		       progDetails["print_aggregate"]=aggregateXML.option.(name==printAggregateCombo.selectedLabel).code;
               progDetails["max_reg_semester"]=maxRegSemText.value;
               progDetails["dgpa"]=dgpaText.text;
               progDetails["credit_required"]=creditsRequiredText.text;
               progDetails["fixed_or_variable_credit"]=fixOrVariableGroup.selectedValue;
               progDetails["startdate"]=startDate;
               progDetails["min_duration"]=minDurationText.value;
               progDetails["max_duration"]=maxDurationText.value;
               progDetails["branch_list"]=branch;
               progDetails["spec_list"]=spec;
               progDetails["active_sem_list"]=activeSem;
               confirmSubmit();

	    }
	   
	}	
}


public function confirmSubmit():void{
	
	
	Alert.show(commonFunction.getMessages('areyousure'),
				commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onOK,questionIcon);		
				
				
		  
}



public function onOK(event:CloseEvent):void{
			
			if(event.detail==Alert.YES){				
				
			    // calling http submit function
httpAddProgramDetails(progDetails);	
				
			}
			
		}



[Bindable]
public var typeXML:XML;
[Bindable]
public var modeXML:XML;
[Bindable]
public var codeXML:XML;
[Bindable]
public var codeEditXML:XML;

var programTypeCb:ComboBox=new ComboBox();
var programModeCb:ComboBox=new ComboBox();
/**
 * Function for getting program type list
 */
 public function httpType():void{
	var params:Object=new Object();
	params["userId"]=userId;
		params["time"]=new Date().getTime();
	httpProgramType.send(params);
}
public function faultHandler_ProgramType(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
public function resultHandler_ProgramType(event:ResultEvent):void{
	  
   typeXML=event.result as XML;
	if(typeXML.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          	var url:String="";
 			url=commonFunction.getConstants('navigateHome');
 			var urlRequest:URLRequest=new URLRequest(url);
 			urlRequest.method=URLRequestMethod.POST;
 			navigateToURL(urlRequest,"_self");
            }  
  var typeAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in typeXML.type){
   		typeAC.addItem({typeName:object.typeName,typeCode:object.typeCode});
   	}
  try{
  programTypeCombo.dataProvider=typeXML.type;
 programTypeCombo.labelField="typeName";
  }catch(e:Error){
  }
 
 programTypeCb.dataProvider=typeXML.type.typeName;

programTypeCb.selectedItem=selectedType;

httpMode();
   }

/**
 * Function for getting program mode list
 */
 public function httpMode():void{
	var params:Object=new Object();
	params["userId"]=userId;
		params["time"]=new Date().getTime();
	httpProgramMode.send(params);
}
public function faultHandler_ProgramMode(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
public function resultHandler_ProgramMode(event:ResultEvent):void{
	  
   modeXML=event.result as XML;
if(modeXML.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          var url:String="";
 			url=commonFunction.getConstants('navigateHome');
 			var urlRequest:URLRequest=new URLRequest(url);
 			urlRequest.method=URLRequestMethod.POST;
 			navigateToURL(urlRequest,"_self");
            }  
  var modeAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in modeXML.mode){
   		modeAC.addItem({modeName:object.modeName,modeCode:object.modeCode});
   	}
   try{
  programModeCombo.dataProvider=modeXML.mode;
 programModeCombo.labelField="modeName";
 }catch(e:Error){
  }
  programModeCb.dataProvider=modeXML.mode.modeName;
programModeCb.selectedItem=selectedMode;
 
 httpProgramCodeList();
   }


/**
 * Function for getting program mode list
 */
 private function httpAddProgramDetails(params:Object):void{
	params["userId"]=userId;
		params["time"]=new Date().getTime();
	httpAddProgram.send(params);
Mask.show(commonFunction.getMessages('pleaseWait'));
}
private function faultHandler_AddProgram(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_AddProgram(event:ResultEvent):void{
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          var url:String="";
 			url=commonFunction.getConstants('navigateHome');
 			var urlRequest:URLRequest=new URLRequest(url);
 			urlRequest.method=URLRequestMethod.POST;
 			navigateToURL(urlRequest,"_self");
            }             
            if(String(result.exception.exceptionstring).search("DBF")>-1){
   				mx.controls.Alert.show(resourceManager.getString('Messages','databasefailure')+
   				"\n"+String(result.exception.exceptionstring).substring(3),resourceManager.getString('Messages','error'),0,null,null,errorIcon);
		} 
	else if(String(result.exception.exceptionstring).search("F")>-1){
   mx.controls.Alert.show(resourceManager.getString('Messages','databasefailure')+
   				"\n"+String(result.exception.exceptionstring).substring(1),resourceManager.getString('Messages','error'));

	}else{
		Alert.show(commonFunction.getConstants('program')+" "+ resourceManager.getString('Messages','savedSuccessfully'),resourceManager.getString('Messages','success'),0,null,onInsertSuccess,successIcon);
		
	}
	Mask.close();
   }

public function onInsertSuccess(event:CloseEvent):void
{
	this.parentDocument.loaderCanvas.addChildAt(new ProgramMaster,1);
	this.parentDocument.loaderCanvas.removeChildAt(0);
}

public var codeListCb:ComboBox=new ComboBox();
/**
 * Function for getting program code list
 */
public function httpProgramCodeList():void{
	var params:Object=new Object();
	params["userId"]=userId;
		params["time"]=new Date().getTime();
	httpProgramCode.send(params);
}
private function faultHandler_ProgramCode(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,resourceManager.getString('Messages','error'));
    }
private function resultHandler_ProgramCode(event:ResultEvent):void{
	
   codeXML=event.result as XML;  
   if(codeXML.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
         var url:String="";
 			url=commonFunction.getConstants('navigateHome');
 			var urlRequest:URLRequest=new URLRequest(url);
 			urlRequest.method=URLRequestMethod.POST;
 			navigateToURL(urlRequest,"_self");
            }  
 var codeAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in codeXML.branch){
   		codeAC.addItem({branchName:object.branchName,branchCode:object.branchCode});
   	}

  codeListCb.dataProvider=codeXML.branch.branchName;

   }




