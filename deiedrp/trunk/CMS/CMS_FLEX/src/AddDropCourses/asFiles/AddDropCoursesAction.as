
import common.Mask;
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]public var params:Object={};
[Bindable]public var urlP:String;
[Bindable]public var courseXML:XML;
[Bindable]public var nameProgramXML:XML;
[Bindable]public var courseAvailableXML:XML;
[Bindable]public var termSummaryXML:XML;
[Bindable]public var typeSummaryXML:XML;
[Bindable]public var courseAvailableArray:ArrayCollection;
[Bindable]public var courseGroup:String;
[Bindable]public var selectedData:ArrayCollection;
[Bindable]public var totalGroupCredits:Number;
[Bindable]public var minimumGroupCredits:Number;
[Bindable]public var maximumGroupCredits:Number;
[Bindable]public var totalCredits:Number;
[Bindable]public var minimumCredits:Number;
[Bindable]public var maximumCredits:Number;
[Bindable]public var minLectureCredits:Number;
[Bindable]public var maxLectureCredits:Number;

var ce:CloseEvent;

public function onCreationComplete():void
{
	urlP=commonFunction.getConstants('url')+"/addDropCourse/";
	regNoText.setFocus();
}

public function onRegSubmit():void
{
	if(regNoText.text!=""){
		params['t']=new Date;
		params['regNumber']=regNoText.text;
		getStudentProgramDetails.send(params);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
	else
	{
		Alert.show("Please enter registration number.",commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

public function getStudentDetailsSuccess(event:ResultEvent):void
{
	nameProgramXML=event.result as XML;
	
	nameLabel.text=nameProgramXML.Details.name;
	entityLabel.text=nameProgramXML.Details.entityName;
	programLabel.text=nameProgramXML.Details.programName;
	branchLabel.text=nameProgramXML.Details.branchName;
	specializationLabel.text=nameProgramXML.Details.specializationName;
	semesterLabel.text=nameProgramXML.Details.semesterName;
	
	var i:int=0;
	
	for each(var o:Object in nameProgramXML.Details){
		i++;
	}
	
	if(i>0)
	{
		params['entity_id']=nameProgramXML.Details.entityId;
		params['program_id']=nameProgramXML.Details.programId;
		params['branch_code']=nameProgramXML.Details.branchId;
		params['new_specialization']=nameProgramXML.Details.specializationId;
		params['semester_code']=nameProgramXML.Details.semesterId;
		params['t']=new Date;
		getCourseTypeSummary.send(params);
	}
	else
	{
		Mask.close();
		Alert.show("Registration form not filled for this student.",commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

public function typeSummarySuccess(event:ResultEvent):void
{
	typeSummaryXML=event.result as XML;
	getTermDetailsSummary.send(params);
}

public function termSummarySuccess(event:ResultEvent):void
{
	termSummaryXML=event.result as XML;
	
	minimumCredits=termSummaryXML.credits.minimum_credit;
	maximumCredits=termSummaryXML.credits.maximum_credit;
	minLectureCredits=termSummaryXML.credits.minimum_lecture_credit;
	maxLectureCredits=termSummaryXML.credits.maximum_lecture_credit;

	getCourseDetails.send(params);
}

public function getStudentCourseSuccess(event:ResultEvent):void
{
	courseXML=event.result as XML;
	totalCredits=0;
	for each(var ob:Object in courseXML.Details)
	{
		totalCredits+=Number(ob.credits.toString());
	}
	dropCourseCombo.dataProvider=courseXML.Details.courseName;
	dropCourseCombo.selectedIndex=-1;
	regNoText.editable=false;
	regSubmitButton.enabled=false;
	regCancelButton.enabled=true;
	dropCourseCanvas.visible=true;
	dropCourseCombo.setFocus();
	Mask.close();
}

public function fault(event:FaultEvent):void
{
	Alert.show("error in service");
	Mask.close();
}

public function onDropCourseSelect():void
{
	var group:String=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseGroup+"";
	totalGroupCredits=0;
	for each(var ob:Object in courseXML.Details)
	{
		if(ob.courseGroup+""==group+"")
		{
			totalGroupCredits+=Number(ob.credits.toString());
		}
	}
	
	params['t']=new Date;
	params['regNo']=regNoText.text;
	params['programId']=nameProgramXML.Details.programId;
	params['branchId']=nameProgramXML.Details.branchId;
	params['specializationId']=nameProgramXML.Details.specializationId;
	params['semesterId']=nameProgramXML.Details.semesterId;
	params['courseCode']=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseCode+"";
	
	getAvailbaleCourse.send(params);
}

public function getAvailbaleCourseSuccess(event:ResultEvent):void
{
	courseAvailableXML=event.result as XML;
	courseAvailableArray=new ArrayCollection;
	
	for each(var obj:Object in courseAvailableXML.Details)
	{
		minimumGroupCredits=Number(obj.minimumCredits);
		maximumGroupCredits=Number(obj.maximumCredits);
		courseGroup=obj.courseGroupName;
		courseAvailableArray.addItem({select:false,courseCode:obj.courseCode,courseName:obj.courseName,
						courseType:obj.courseTypeName,credits:obj.credits});
	}
	
	if(courseAvailableArray.length>0)
	{
		minCreditLabel.text=minimumGroupCredits+"";
		maxCreditLabel.text=maximumGroupCredits+"";
		courseGroupLabel.text=courseGroup;
		addCourseGrid.dataProvider=courseAvailableArray;
		addCourseCanvas.visible=true;
	}
	else
	{
		Alert.show("Cannot drop selected course.",commonFunction.getMessages('error'),0,null,null,errorIcon);
		addCourseCanvas.visible=false;
	}
	
}

public function onRegCancel(event:CloseEvent):void
{
	regNoText.editable=true;
	regSubmitButton.enabled=true;
	dropCourseCanvas.visible=false;
	regCancelButton.enabled=false;
	addCourseCanvas.visible=false;
	regNoText.text="";
	regNoText.setFocus();
}

public function onAddDropSubmit():void
{
	selectedData=new ArrayCollection;
	selectedData=commonFunction.getSelectedRowData(addCourseGrid);
	if(validateCourseSelection())
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,AddDropOrNot,questionIcon);
	}
}

public function validateCourseSelection():Boolean
{
	var bool:Boolean=false;
	var dropCredit:Number=Number(courseXML.Details.(courseName==dropCourseCombo.selectedLabel).credits);
	var totCredits:Number=0;
	
	for each(var obj:Object in selectedData)
	{
		totCredits+=Number(obj.credits+"");
	}
	
	var egc:Number=totalGroupCredits-dropCredit+totCredits;
	var ec:Number=totalCredits-dropCredit+totCredits;
	
	if(egc<minimumGroupCredits)
	{
		Alert.show("Total credits of courses in group "+courseGroup+" must be greater than or equal to "+minimumGroupCredits,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(egc>maximumGroupCredits)
	{
		Alert.show("Total credits of courses in group "+courseGroup+" must be less than or equal to "+maximumGroupCredits,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(ec<minimumCredits)
	{
		Alert.show("Total credits of courses of all group must be greater than or equal to "+minimumGroupCredits,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else if(ec>maximumCredits)
	{
		Alert.show("Total credits of course of all group must be greater than or equal to "+minimumGroupCredits,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else
	{
		bool=true;
	}
	
	return bool;
}

public function addDropCourseSuccess(event:ResultEvent):void
{
	if(event.result.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas2.removeAllChildren();
            }
            
    if(event.result.info.toString()=="true"){
		Alert.show(commonFunction.getMessages('success'),commonFunction.getMessages('success'),0,null,onRegCancel,successIcon);
	}
	//added by ashish mohan
	else if(String(event.result.info).search("false")>-1)
	{
	Alert.show("Error occured \n Details are as follows: \n"+String(event.result.info)  ,resourceManager.getString('Messages','error'),4,null,null,errorIcon);
    }
}

public function AddDropOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		params['t']=new Date;
		params['dropcourseCode']=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseCode;
		params['courseGroup']=courseXML.Details.(courseName==dropCourseCombo.selectedLabel).courseGroup;
		params['regNumber']=regNoText.text;
		params['enrollNumber']=nameProgramXML.Details.enrollNumber;
		params['rollNumber']=nameProgramXML.Details.rollNumber;
		params['studentId']=nameProgramXML.Details.studentId;
		params['programId']=nameProgramXML.Details.programId;
		params['branchId']=nameProgramXML.Details.branchId;
		params['specializationId']=nameProgramXML.Details.specializationId;
		params['semesterId']=nameProgramXML.Details.semesterId;
		params['semesterStartDate']=nameProgramXML.Details.semesterStartDate;
		params['semesterEndDate']=nameProgramXML.Details.semesterEndDate;
		params['courseCodes']="";
		params['courseNames']="";
		
		for each(var obj:Object in selectedData)
		{
			params['courseCodes']+=obj.courseCode+"|";
			params['courseNames']+=obj.courseName+"|";
		}

		addDropCourse.send(params);
	}
}

