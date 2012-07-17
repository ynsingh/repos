import common.Mask;
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.collections.IViewCursor;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import studentRegistration.GroupList;

import studentRegistration1.LoginForm1;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

[Bindable]
public var studentDetailsResult:XML=new XML();
[Bindable]
public var deadlinesResult:XML=new XML();
[Bindable]
public var compulsaryCourseXml:XML=new XML();
[Bindable]
public var electiveCourseXml:XML=new XML();
[Bindable]
public var user_name:String=new String();
[Bindable]
public var parameter:Object=new Object();
[Bindable]
public var courseTypeXml:XML=new XML();
[Bindable]
public var termSummaryXml:XML=new XML();
[Bindable]
public var forCancel:String=new String();
[Bindable]
public var dep:XML=new XML();
[Bindable]
public var groupCat:XML=new XML();
[Bindable]
public var regData:XML=new XML();
 [Bindable]
 public  var oldGroupXml:XML=new XML();
[Bindable]
 public var inputData:Object=new Object();
[Bindable]
 public var finalData:Object=new Object();
		
/**
 * setting common parameters                                         
 */
public function setData():void{
	inputData["program_id"]=studentDetailsResult.student.program_id;
	inputData["branch_code"]=studentDetailsResult.student.branch_code;
	inputData["new_specialization"]=studentDetailsResult.student.new_specialization;
	inputData["semester_code"]=studentDetailsResult.student.semester_code;
	inputData["entity_id"]=studentDetailsResult.student.entity_id;
	inputData["admissionMode"]=studentDetailsResult.student.admission_mode;
	
inputData["old_entity"]=studentDetailsResult.student.old_entity_id;
inputData["old_program"]=studentDetailsResult.student.old_program_id;
inputData["old_branch"]=studentDetailsResult.student.old_branch_code;
inputData["old_specialization"]=studentDetailsResult.student.old_specialization;
inputData["old_semester"]=studentDetailsResult.student.old_semester_code;
inputData["roll"]=user_name;

}


/**
 *  Submitting the form
 */
public function submitForm():void
{
	var a:Array=getSelectedCourseGroupRowData(compulsoryCourseGrid);
	var b:Array=getSelectedCourseGroupRowData(electiveCourseGrid);
	//	 Alert.show("a"+a.length+"b"+b.length);	
	var compulsaryFlag:Boolean=validateCourseTypeSummary(a,b);
	///Alert.show("rohit1");
//	var electiveFlag:Boolean=validateCourseTypeSummary(b);
	var termFlag:Boolean=validateTermDetailSummary(a,b);
//Alert.show(compulsaryFlag+"and"+termFlag);
//	if(electiveAC.length<1){
//		electiveFlag=true;		
//	}		
		
//	if(compulsaryFlag && electiveFlag && termFlag){

		if(compulsaryFlag && termFlag){
		
		var course_codes:String=new String();
		var course_group:String=new String();
		
// Variable created by Dheeraj for adding course name in temp_student_courses
		var course_name:String=new String();		
	
finalData["student_id"]=studentDetailsResult.student.student_id;
finalData["roll_number"]=studentDetailsResult.student.roll_number;
finalData["enrollment_number"]=studentDetailsResult.student.enrollment_number;
finalData["date_of_birth"]=studentDetailsResult.student.date_of_birth;
finalData["category"]=studentDetailsResult.student.category;
finalData["gender"]=studentDetailsResult.student.gender;
finalData["student_name"]=studentDetailsResult.student.student_name;
finalData["father_name"]=studentDetailsResult.student.father_name;
finalData["mother_name"]=studentDetailsResult.student.mother_name;
finalData["entity_id"]=studentDetailsResult.student.entity_id;
finalData["program_id"]=studentDetailsResult.student.program_id;
finalData["branch_code"]=studentDetailsResult.student.branch_code;
finalData["old_entity_id"]=studentDetailsResult.student.old_entity_id;
finalData["old_program_id"]=studentDetailsResult.student.old_program_id;
finalData["old_branch_code"]=studentDetailsResult.student.old_branch_code;
finalData["old_specialization"]=studentDetailsResult.student.old_specialization;
finalData["new_specialization"]=studentDetailsResult.student.new_specialization;
finalData["semester_code"]=studentDetailsResult.student.semester_code;
finalData["old_semester_code"]=studentDetailsResult.student.old_semester_code;
finalData["admission_mode"]=studentDetailsResult.student.admission_mode;
finalData["session_start_date"]=studentDetailsResult.student.session_start_date;
finalData["session_end_date"]=studentDetailsResult.student.session_end_date;
finalData["attempt"]=studentDetailsResult.student.attempt;
finalData["first_name"]=studentDetailsResult.student.first_name;
finalData["middle_name"]=studentDetailsResult.student.middle_name;
finalData["last_name"]=studentDetailsResult.student.last_name;
finalData["primary_email_id"]=studentDetailsResult.student.primary_email_id;
finalData["sequence_number"]=studentDetailsResult.student.sequence_number;
finalData["probable_semester"]=studentDetailsResult.student.probable_semester;
if(studentDetailsResult.student.probable_semester_start_date==""){
finalData["probable_semester_start_date"]=null;	
}else{
	finalData["probable_semester_start_date"]=studentDetailsResult.student.probable_semester_start_date;
}

if(studentDetailsResult.student.probable_semester_end_date==""){
finalData["probable_semester_end_date"]=null;	
}else{
	finalData["probable_semester_end_date"]=studentDetailsResult.student.probable_semester_end_date;
}

finalData["probable_attempt_number"]=studentDetailsResult.student.probable_attempt_number;

if(studentDetailsResult.student.probable_register_due_date==""){
finalData["probable_register_due_date"]=null;	
}else{
	finalData["probable_register_due_date"]=studentDetailsResult.student.probable_register_due_date;
}
finalData["status_in_semester"]=studentDetailsResult.student.status_in_semester;
finalData["reg_roll_number"]=user_name;
finalData["fatherFirstName"]=studentDetailsResult.student.fatherFirstName;
finalData["fatherMiddleName"]=studentDetailsResult.student.fatherMiddleName;
finalData["fatherLastName"]=studentDetailsResult.student.fatherLastName;
finalData["motherFirstName"]=studentDetailsResult.student.motherFirstName;
finalData["motherMiddleName"]=studentDetailsResult.student.motherMiddleName;
finalData["motherLastName"]=studentDetailsResult.student.motherLastName;

var totalCredit:Number=totalCredits(a,b);
var theoryCredit:Number=theoryCount(a,b);
var pracCredit:Number=pracCount(a,b);
var auditCredit:Number=0;
var creditExcludeAudit:Number=(theoryCredit+pracCredit)-auditCredit;


finalData["regCredit"]=totalCredit;
finalData["theoryCredit"]=theoryCredit;
finalData["pracCredit"]=pracCredit;
finalData["creditExcludeAudit"]=creditExcludeAudit;

 	
if((studentDetailsResult.student.admission_mode).toString().toUpperCase()=="NEW"){
finalData["registrationNumber"]=user_name;
}else{
	finalData["registrationNumber"]=user_name;
}
//Alert.show("a length"+a.length+"\nb"+b.length);


	var i:int;

	for (i = 0; i < a.length-b.length; i++)
	{
   
    course_codes+=a[i].courseCode+"|";
    course_group+=a[i].course_group_code+"|";
    course_name+=a[i].courseName+"|";		// Added By Dheeraj For Getting Course Names From CourseXML
	}	
	//Alert.show("comp"+course_codes);
	
	for (i = 0; i < b.length; i++)
	{
   
    course_codes+=b[i].course_code+"|";
    course_group+=b[i].course_group_code+"|"
    course_name+=b[i].course_name+"|";		// Added By Dheeraj For Getting Course Names From CourseXML
	}
//	Alert.show("total"+course_codes);
	
 finalData["course_list"]=course_codes;
 
 finalData["course_group_list"]=course_group;
 
 finalData["course_name_list"]=course_name;		// Added By Dheeraj For Sending Course Name List
	
 Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,submitOrNot,questionIcon);
	}
//	else{
//		Alert.show(commonFunction.getMessages('creditCountMismatch'));
//	}
}

public function submitOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		httpSubmitForm.send(finalData);
	}
}



private function faultHandler_SubmitForm(event:FaultEvent):void{
	
	
Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
      
    }
    
    
private function resultHandler_SubmitForm(event:ResultEvent):void{

   	var resultXml:XML=event.result as XML
  
   	if(resultXml.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas2.removeAllChildren();
            } 
            
            
   	if(resultXml.children().exceptionstring.length() > 0){
   		   			Alert.show(resultXml.exception.exceptionstring,"Information",4,this,goToLogin,errorIcon);

   	}else{
   		   			Alert.show(resourceManager.getString('Messages','submittedSuccessfully'),commonFunction.getMessages('success'),4,this,goToLogin,successIcon);

   	}
   }

/**
 * Getting course type summary
 */
private function getCourseTypeSummary():void{
//	Alert.show(inputData['program_id']+""+inputData["branch_code"]+""+inputData["new_specialization"]+""+inputData["semester_code"]+
//	"");

   Mask.show(commonFunction.getMessages('pleaseWait'));
	httpCourseTypeSummary.send(inputData);
}

private function faultHandler_CourseTypeSummary(event:FaultEvent):void{
	//Alert.show("come inn fail");
	Alert.show(commonFunction.getMessages('courseFailureError'),commonFunction.getMessages('error'),0,null,null,errorIcon);
        
    }
   private function resultHandler_CourseTypeSummary(event:ResultEvent):void{
   
   Mask.close();
  // Alert.show("suces");
   courseTypeXml=event.result as XML;
  
   
   }



/**
 * Checking validity for credits in program_course_type_summary
 */
public function validateCourseTypeSummary(comp:Array,elec:Array):Boolean{
	var courseTypeAC:ArrayCollection=new ArrayCollection();
	var flag:Boolean=true;
   	for each (var object:Object in courseTypeXml.summary){
   		
   		courseTypeAC.addItem({course_type:object.course_type,minimum_credits:object.minimum_credit,
   		maximum_credits:object.maximum_credit});
   	}
  
   	if(elec != null){
	   	for each(var o:Object in elec)
	   	{
	   		comp.push(o);
	   	}
   	}
   	 	
   	var credit:Number=0;
   	if(comp != null){
   		for each(var obj:Object in courseTypeAC){
   		try{
			for (var j:int=0;j<comp.length;j++)
			{
				//Alert.show("rohit1"+obj.course_type.toString()+"rohit2"+comp[j].courseType.toString());
  				
  					
  				if(obj.course_type.toString() == comp[j].courseType.toString()){
  					    
  					//Alert.show(comp[j].credits.toString()+"in if");
  						credit=credit+Number('0'+comp[j].credits);
  					}
  				}
  				
			}
			catch(e:Error){
  					//Alert.show(e+"ff");
  				}
		//	Alert.show(credit+"credit");
			
		 	
			 if(credit<obj.minimum_credit)
	        	    {
	        		    flag=false;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectTotalCreditsAtleast')+" "+obj.minimum_credit+" "+commonFunction.getMessages('credits')+commonFunction.getMessages('for')+obj.course_type.toString()+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
  						 return flag;	        	   
	        	    }
	        	    else if(credit>obj.maximum_credit)
	        	    {   
	        		    flag=false;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectTotalCreditsLessThanorEqualto')+" "+obj.maximum_credit+" "+commonFunction.getMessages('credits')+commonFunction.getMessages('for')+obj.course_type.toString()+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        	    	 return flag;
	        	    }
   		}
	}else{
		flag=false;
		}

   	//Alert.show("goingrrrrr"+flag);
   	 	return flag;
   	}




/**
 *  Getting credit details from program_term_details
 */
private function getTermSummary():void{
	httpTermSummary.send(inputData);
}

private function faultHandler_TermSummary(event:FaultEvent):void{
	
Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
    }
    
   
   private function resultHandler_TermSummary(event:ResultEvent):void{
   
   termSummaryXml=event.result as XML;
   
minimumCreditLabel.text=termSummaryXml.credits.minimum_credit;
maximumCreditLabel.text=termSummaryXml.credits.maximum_credit;
minimumLectureCreditLabel.text=termSummaryXml.credits.minimum_lecture_credit;
maximumLectureCreditLabel.text=termSummaryXml.credits.maximum_lecture_credit;
 
   }


/**
 * Checking validity for credits in program_term_details
 */
public function validateTermDetailSummary(comp:Array,elec:Array):Boolean{
	var flag:Boolean=true;
 var totalCredit:Number=totalCredits(comp,elec);

 var theoryCredit:Number=theoryCount(comp,elec);
 
 var minCredit:Number=Number(termSummaryXml.credits.minimum_credit);
 var maxCredit:Number=Number(termSummaryXml.credits.maximum_credit);
 var minLectCredit:Number=Number(termSummaryXml.credits.minimum_lecture_credit);
 var maxLectCredit:Number=Number(termSummaryXml.credits.maximum_lecture_credit);
  
					 if(totalCredit<minCredit)
	        	    {
	        		    flag=false;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectTotalCreditsAtleast')+" "+minCredit+" ",commonFunction.getMessages('error'),0,null,null,errorIcon);
  						 return flag;	        	   
	        	    }
	        	    else if(totalCredit>maxCredit)
	        	    {   
	        		    flag=false;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectTotalCreditsLessThanorEqualto')+" "+maxCredit+" "+commonFunction.getMessages('credits')+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        	    	 return flag;
	        	    }
 
 
 
 if(theoryCredit<minLectCredit)
	        	    {
	        		   flag=false;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectTotalTheoryCreditsAtleast')+" "+minLectCredit+" "+commonFunction.getMessages('credits')+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        	    	return flag;
	        	    }
	        	    else if(theoryCredit>maxLectCredit)
	        	    {
	        		   flag=false;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectTotalTheoryCreditsLessThanorEqualto')+" "+maxLectCredit+" "+commonFunction.getMessages('credits')+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        	   		return flag;
	        	    }
 	 
   	 	return flag;
   	}


/**
 * Counting all courses' total credits
 */
private function totalCredits(comp:Array,elec:Array):Number{
	
	var i:int;
	var total:Number=0;
	if(comp != null){
	for (i = 0; i < comp.length-elec.length; i++)
	{
   total+=Number(comp[i].credits);
	}	
	}
	
	var j:int;
	if(elec != null){
	for (j = 0; j < elec.length; j++)
	{
   total+=Number(elec[j].credits);
	}
	}	
//	Alert.show("elective+compul"+total);
	return total;	
}


/**
 * Counting credits of chosen theory courses
 */
private function theoryCount(comp:Array,elec:Array):Number{
	var i:int;
	var total:Number=0;
	if(comp != null){
	for (i = 0; i < comp.length-elec.length; i++)
	{			
		if((comp[i].courseClassification=="T")||(comp[i].courseClassification=="t")){
   total+=Number(comp[i].credits);
  }
	}	
	}
	
	var j:int;
	if(elec != null){
	for (j = 0; j < elec.length; j++)
	{
		if((elec[j].course_classification=="T")||(elec[j].course_classification=="t")){
   total+=Number(elec[j].credits);
  }
	}	
	}
     
	return total;
}

/**
 * Counting credits of chosen theory courses
 */
private function pracCount(comp:Array,elec:Array):Number{
	var i:int;
	var total:Number=0;
	if(comp != null){
	for (i = 0; i < comp.length-elec.length; i++)
	{			
		if((comp[i].courseClassification=="P")||(comp[i].courseClassification=="p")){
   total+=Number(comp[i].credits);
  }
	}	
	}
	
	var j:int;
	if(elec != null){
	for (j = 0; j < elec.length; j++)
	{
		if((elec[j].course_classification=="P")||(elec[j].course_classification=="p")){
   total+=Number(elec[j].credits);
  }
	}	
	}
     
	return total;
}

///**
// * Populating course classification
// */
//public function getCourseClassification(row:Object,col:AdvancedDataGridColumn):String
//{
//	if((row.course_classification=="T")||(row.course_classification=="t"))
//	{
//		return "Theory";
//	}
//	else if((row.course_classification=="P")||(row.course_classification=="p"))
//	{
//		return "Practicals";
//	}
//	else if((row.course_classification=="W")||(row.course_classification=="w"))
//	{
//		return "Work Experience";
//	}
//	else if((row.course_classification=="C")||(row.course_classification=="c"))
//	{
//		return "Core";
//	}
//	else
//	{
//	    return "";			
//	}
//}

public function getSelectedCourseGroupRowData(dg:AdvancedDataGrid):Array
{
	var selectedData:Array=new Array();
	var gridData:Object = dg.dataProvider;
	var courseGroup:String="";
	var totalCredits:Number=0;
	var minCredits:Number=0;
	var maxCredits:Number=0;
	var validError:Boolean=false;
	var cursor:IViewCursor = gridData.createCursor();
	while(!cursor.afterLast)
	{
		var obj:Object=cursor.current;
		var group:String=obj.course_group;
		var checked:Boolean=obj.select;
		if(group!=null)
		{
		    if(courseGroup=="")
	        {
	        	courseGroup=group;
	        	minCredits=obj.minimum_credits;
	        	maxCredits=obj.maximum_credits;
	        	if(checked)
	        	{
	        	    totalCredits=Number(obj.credits);
	        	}
	        }
	        else
	        {
	        	if(group==courseGroup)
	        	{
	        		minCredits=obj.minimum_credits;
	        	    maxCredits=obj.maximum_credits;
	        	    if(checked)
	        	    {
	        	        totalCredits+=Number(obj.credits);
	        	    }
	        	}
	        	else
	        	{
	        		
	        		if(totalCredits<minCredits)
	        	    {
	        		    validError=true;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectAtleast')+" "+minCredits+" "+commonFunction.getMessages('creditsFrom')+" "+courseGroup+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        	    }
	        	    else if(totalCredits>maxCredits)
	        	    {
	        		    validError=true;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectLessThanorEqualto')+" "+maxCredits+" "+commonFunction.getMessages('creditsFrom')+" "+courseGroup+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        	    }
	        		courseGroup=group;
	        		totalCredits=0;
	        	    minCredits=obj.minimum_credits;
	        	    maxCredits=obj.maximum_credits;
	        	    if(checked)
	        	    {
	        	        totalCredits=Number(obj.credits);
	        	    }
	        	}
	        }
	        if(checked)
	        {
	        	selectedData.push(obj);
	        }
		}
		cursor.moveNext();
		if(cursor.afterLast)
		{

	        if(totalCredits<minCredits)
	        {
	            validError=true;
	            Alert.show(commonFunction.getMessages('pleaseSelectAtleast')+" "+minCredits+" "+commonFunction.getMessages('creditsFrom')+" "+courseGroup+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        }
	        else if(totalCredits>maxCredits)
	        {
	            validError=true;
	            Alert.show(commonFunction.getMessages('pleaseSelectLessThanorEqualto')+" "+maxCredits+" "+commonFunction.getMessages('creditsFrom')+" "+courseGroup+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        }
		}
	}
	if(!validError)
	{
        return (selectedData);
    }
    else
    {
       	return null;
    }
}

var dependencyArray:ArrayCollection;
var groupCategoryArray:ArrayCollection;
var i:int=1;
var minSelection:int=0;
var maxSelection:int=0;
var groupListArray:Array=new Array;
var groupData:ArrayCollection=new ArrayCollection;
var regDataArray:ArrayCollection=new ArrayCollection;
var selectedGroup:Array=new Array;

		
/* Getting student's details from server */

public function getStudentDetails():void {
	httpStudentDetails.send(parameter);
}
 var oldGroupArray:Array=new Array;
//on creation complete of page
public function doComplete():void
{
	groupData=new ArrayCollection;
	dependencyArray=new ArrayCollection;
    groupCategoryArray=new ArrayCollection;
    
    for each(var obj:Object in dep.compulsory)
    {
    	dependencyArray.addItem({subGroup:obj.groupCode,subGroupName:obj.groupName,condition:obj.groupRule,
    					conditionSubGroup:obj.subGroupCode,conditionSubGroupName:obj.subGroupName});
    }

    for each(var oldobj:Object in oldGroupXml.compulsory)
    {
    	oldGroupArray.push(oldobj.groupCode.toString());
    }
         
    for each(var gobj:Object in groupCat.compulsory)
    {
    	var groupCode:String=gobj.subGroupCode.toString();
    	var bool:Boolean=false;
    	if(oldGroupArray.indexOf(groupCode)>=0)
    	{
    		bool=true;
    	}
    	else
    	{
    		bool=false;
    	}
    	groupCategoryArray.addItem({select:bool,order:gobj.groupOrder,group:gobj.groupCode,groupName:gobj.groupName,
    	subGroup:gobj.subGroupCode,subGroupName:gobj.subGroupName,
    		min:gobj.minimumSelection,max:gobj.maximumSelection,
    			isCond:gobj.conditionalGroup,linkedGroup:gobj.linkedGroup,
    			linkedMin:gobj.linkedMinimumSelection,linkedMax:gobj.linkedMaximumSelection});
    			    			
    }

    for each(var obje:Object in groupCategoryArray)
    {		
     	if((int(obje.order.toString())==i)&&((obje.isCond.toString().toUpperCase())=="N"))
    	{   
    		groupData.addItem(obje);
        	minSelection=int(obje.min.toString());
			maxSelection=int(obje.max.toString());
			major.groupColumn.headerText=obje.groupName;
        }
    }
    
    i++;
    groupListArray.push(major);
    
    major.majorGrid.dataProvider=groupData;
    major.majorGrid.dataProvider.refresh();
    major.nextFunction=addGroupData;
    major.previousFunction=removeGroupData;
    
    getCourseTypeSummary();
//   	// getting summary from program_term_details
   	getTermSummary();
  
}

//showing selected records
public function showSelected():void
{
	var n:String="";
	for each(var s:Object in selectedGroup)
	{
		n=n+"\n"+s;
	}
}

//validating group at each step	
public function validateGroupSelections():Boolean
{	
		
	var bool:Boolean=false;
	
	var oldGroupList:GroupList=GroupList(SubgroupWindow.getChildAt(groupListArray.length-1));
    var count:int=0;
    var errorStr="";
	    
    for each(var obj:Object in oldGroupList.majorGrid.dataProvider)
    {   
    	if(obj.select==true)
    	{
    		var isBrk1:Boolean=false;
    		for each(var obj1:Object in dependencyArray)
    		{
    			var isBrk2:Boolean=false;
    			for each(var obj2:Object in selectedGroup)
    			{
    				if((obj1.condition.toString().toUpperCase()=="X")&&(obj1.subGroup.toString()==obj2.toString())&&(obj1.conditionSubGroup.toString()==obj.subGroup.toString()))
    				{
    					count=-1;
    					errorStr=commonFunction.getMessages('pleaseSelectOnlyOneSubjectFrom')+" "+obj1.subGroupName+" & "+obj1.conditionSubGroupName+" ).";
    					isBrk1=true;
    					isBrk2=true;
    					
    					break;
    				}
    			}
    			
    			for each(var obj3:Object in oldGroupList.majorGrid.dataProvider)
    			{
    				if(obj3.select==true)
    				{
	    				if((obj1.condition.toString().toUpperCase()=="X")&&(obj1.subGroup.toString()==obj3.subGroup.toString())&&(obj1.conditionSubGroup.toString()==obj.subGroup.toString()))
	    				{
	    					count=-1;
	    					errorStr=commonFunction.getMessages('pleaseSelectOnlyOneSubjectFrom')+" "+obj1.subGroupName+" & "+obj1.conditionSubGroupName+" ).";
	    					isBrk1=true;
	    					isBrk2=true;
	    					
	    					break;
	    				}
    				}
    			}
    			if(isBrk2)
    			{
    				break;
    			}
    		}
    		if(isBrk1)
    		{
    			break;
    		}
    		count++;
    	}
    }
    
    if((count>=minSelection)&&(count<=maxSelection))
    {
    	for each(var mobj:Object in oldGroupList.majorGrid.dataProvider)
    	{
    		if(mobj.select==true)
    		{
    			selectedGroup.push(mobj.subGroup.toString());
    		}
    	}
    	
		bool=true;
	}
	else
	{
		if(count==-1)
		{
			Alert.show(errorStr,commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
		else
		{
			if(count<minSelection){  
			Alert.show(commonFunction.getMessages('pleaseSelectAtleast')+" "+commonFunction.convertNumberToWord(minSelection)+" "+obj.groupName+" "+commonFunction.getMessages('subject')+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
			
			}else if(count>maxSelection){
			Alert.show(commonFunction.getMessages('pleaseSelectLessThanorEqualto')+" "+commonFunction.convertNumberToWord(maxSelection)+" "+obj.groupName+" "+commonFunction.getMessages('subject')+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
			}
		}
	}
	
	return bool;
}

//validating conditional dependency
public function validateConditionalSelection():Boolean
{   
	var bool:Boolean=true;
	var c1:int=0;
	var c2:int=0;
	var c3:int=0;
	var cm:int=0;
	var cw:int=0;
	var linkedMin:int=0;
	var linkedMax:int=0;
	
	var ii:int=i;
	
	var ms:String="";
	var ws:String="";
	
	for each(var obj1:Object in dependencyArray)
	{
		if(obj1.condition.toString().toUpperCase()=="A")
		{    
			for each(var obj2:Object in selectedGroup)
			{   
				if(obj1.subGroup.toString()==obj2.toString())
				{
					cm++;
					ws+=obj1.conditionSubGroupName+",";
					
					for each(var obj3:Object in selectedGroup)
					{
						if(obj1.conditionSubGroup.toString()==obj3.toString())
						{
							cw++;
						}
					}
				}
			}		
		}
		
		if(obj1.condition.toString().toUpperCase()=="M")
		{
			for each(var obj4:Object in selectedGroup)
			{
				if(obj1.subGroup.toString()==obj4.toString())
				{
					c1++;
					var ci:int=0;
					for each(var obj5:Object in selectedGroup)
					{
						if(obj1.conditionSubGroup.toString()==obj5.toString())
						{
							ci++;
							c1--;
						}
					}
					
					if(ci==0)
					{
						//ms= "if you select "+obj1.subGroupName+" then you must select "+obj1.conditionSubGroupName+"\n";
						ms=commonFunction.getMessages('youMustSelect')+" "+obj1.conditionSubGroupName;
					}	
				}
			}		
		}		
	}
	                                                
	var groupArray:Array=new Array;
	
	var linkedGroupNames:String="";
				
	for each(var ob1:Object in groupCategoryArray)
	{
		if(groupArray.indexOf(ob1.linkedGroup.toString())<0)
		{
			if((ob1.linkedGroup!=null)&&(ob1.linkedGroup!=""))
			{
				groupArray.push(ob1.linkedGroup.toString());
				var count:int=0;
				linkedMax=int(ob1.linkedMax);
				linkedMin=int(ob1.linkedMin);
				linkedGroupNames=ob1.linkedGroup;
				var arr:Array=new Array();
				for each(var ob2:Object in groupCategoryArray)
				{
					if(ob1.linkedGroup.toString()==ob2.linkedGroup.toString())
					{
						for each(var subGroup:Object in selectedGroup)
						{
							if(subGroup.toString()==ob2.subGroup.toString())
							{
								count++;			
							}
							
						}
						
						if(arr.indexOf(ob2.groupName.toString())<0)
						{
							arr.push(ob2.groupName.toString());
							//arr.push(",");	
						}
					}
					
				}
					
			if(count<linkedMin)
			{
				//Alert.show("coming ");
				bool=false;
				Alert.show(commonFunction.getMessages('pleaseSelectAtleast')+" "+commonFunction.convertNumberToWord(linkedMin)+" "+arr+" "+commonFunction.getMessages('subject'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				break;
			}else if(count>linkedMax){
				bool=false;
				Alert.show(commonFunction.getMessages('pleaseSelectLessThanorEqualto')+" "+commonFunction.convertNumberToWord(linkedMax)+" "+arr+" "+commonFunction.getMessages('subject'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				break;
			}
				
				
				
				
				
			}
		}		
	}

	for each(var obj:Object in groupCategoryArray)
	{
		var gs:String="";
		c2=0;
		for each(var obj6:Object in groupCategoryArray)
		{
			if((int(obj6.order.toString())==ii)&&(obj6.isCond.toString()=="Y"))
			{
				minSelection=int(obj6.min.toString());
				maxSelection=int(obj6.max.toString());
				
				gs=gs+" "+obj6.subGroupName.toString()+",";
				
				if(selectedGroup.indexOf(obj6.subGroup.toString())>=0)
				{
					c2++;
				}
			}
		}
		
		
		if(gs!="")
		{
			if(c2<minSelection)
			{
				
				bool=false;
				Alert.show(commonFunction.getMessages('pleaseSelectAtleast')+" "+commonFunction.convertNumberToWord(minSelection)+" "+commonFunction.getMessages('subjectFrom')+gs.substring(0,gs.length-1)+").",commonFunction.getMessages('error'),0,null,null,errorIcon);
				break;
			}else if(c2>maxSelection){
				bool=false;
				Alert.show(commonFunction.getMessages('pleaseSelectLessThanorEqualto')+" "+commonFunction.convertNumberToWord(maxSelection)+" "+commonFunction.getMessages('subjectFrom')+gs.substring(0,gs.length-1)+").",commonFunction.getMessages('error'),0,null,null,errorIcon);
				break;
			}
		}
		ii++;
	}
	
	if(c1>0)
	{
		bool=false;
		Alert.show(ms,commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	
	if(cm>0){
		if(cw==0)
		{
			
			bool=false;
			Alert.show(commonFunction.getMessages('PleaseSelectAtleastOneSubjectFrom')+ws.substring(0,ws.length-1)+").",commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
	}
	
	
	if(!bool)
	{
		var oldGroupList:GroupList=GroupList(SubgroupWindow.getChildAt(groupListArray.length-1));
		for each(var ob:Object in oldGroupList.majorGrid.dataProvider)
		{
			if(ob.select==true)
			{
				selectedGroup.pop();
				minSelection=int(ob.min.toString());
				maxSelection=int(ob.max.toString());
			}
		}
	}
	return bool;
}

//go to next step	
public function addGroupData():void
{  
	//Alert.show("in next func");
	if(validateGroupSelections())
	{
		
		var groupList:GroupList=new GroupList;
	
		groupList.previousVisible=true;
		groupData=new ArrayCollection;
				
		for each(var obj:Object in groupCategoryArray)
		{
			if((int(obj.order.toString())==i)&&((obj.isCond.toString().toUpperCase())=="N"))
			{
				if(oldGroupArray.indexOf(obj.subGroup.toString())>=0)
				{
					obj.select=true;
				}
				else
				{
					obj.select=false;
				}
				groupData.addItem(obj);
				minSelection=int(obj.min.toString());
				maxSelection=int(obj.max.toString());		
			}
		}
		
		if(groupData.length>0)
		{
			i++;
			var oldGroupList:GroupList=GroupList(SubgroupWindow.getChildAt(groupListArray.length-1));
			oldGroupList.enabled=false;
			SubgroupWindow.addChildAt(groupList,groupListArray.length);
			groupList.majorGrid.dataProvider=groupData;
			groupList.groupColumn.headerText=groupData.getItemAt(0).groupName;
			groupList.nextFunction=addGroupData;
			groupList.previousFunction=removeGroupData;
			groupListArray.push(groupList);
		}
		else
		{    
			if(validateConditionalSelection())
			{
				var oldGroupList1:GroupList=GroupList(SubgroupWindow.getChildAt(groupListArray.length-1));
				submitButton.enabled=true;
				oldGroupList1.enabled=false;
				SubgroupWindow.visible=false;
				compulsoryCourseCanvas.visible=true;
				
				//selected data
				//Alert.show("on next req going");
				getCompulsaryCourse(selectedGroup);		
			}
		}
	}
}

//go back to previous step from course selection
public function removeCourseGrid():void
{
	var oldGroupList:GroupList=GroupList(SubgroupWindow.getChildAt(groupListArray.length-1));
	oldGroupList.enabled=true;
    compulsoryCourseCanvas.visible=false;
    SubgroupWindow.visible=true;
    submitButton.enabled=false;
    
    for each(var obj:Object in oldGroupList.majorGrid.dataProvider)
	{
		if(obj.select==true)
		{
			selectedGroup.pop();
		}
	}
	
	for each(var gobj:Object in groupCategoryArray)
	{
		if((int(gobj.order.toString())==(i-1))&&((gobj.isCond.toString().toUpperCase())=="N"))
       	{
       		minSelection=int(gobj.min.toString());
			maxSelection=int(gobj.max.toString());
       	}
	}
}

//go back to previous step from group selection
public function removeGroupData():void
{
	SubgroupWindow.removeChild(groupListArray.pop() as DisplayObject);
	i--;
		
	for each(var obj:Object in groupCategoryArray)
	{
		if((int(obj.order.toString())==(i-1))&&((obj.isCond.toString().toUpperCase())=="N"))
       	{
       		minSelection=int(obj.min.toString());
			maxSelection=int(obj.max.toString());
       	}
	}
		
	var pos:int=SubgroupWindow.getChildIndex(groupListArray[groupListArray.length-1] as DisplayObject);
    var oldGroupList:GroupList=GroupList(SubgroupWindow.getChildAt(pos));
    for each(var mobj:Object in oldGroupList.majorGrid.dataProvider)
	{
		if(mobj.select==true)
		{
			selectedGroup.pop();
		}
	}
	oldGroupList.enabled=true;
}
		
private function faultHandler_StudentDetails(event:FaultEvent):void
{
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	
}

private function resultHandler_StudentDetails(event:ResultEvent):void
{
   
   	studentDetailsResult=event.result as XML; 
   	
   		if(studentDetailsResult.sessionConfirm == true)
             {
            Alert.show(resourceManager.getString('Messages','sessionInactive'));
          this.parentDocument.vStack.selectedIndex=0;
          this.parentDocument.loaderCanvas2.removeAllChildren();
            } 
   	
   	
   	
	if(studentDetailsResult.children().exceptionstring.length() > 0){
   
   Alert.show(studentDetailsResult.exception.exceptionstring,commonFunction.getMessages('information'),4,this,goToLogin,infoIcon);

   	}else{
   	   		setData(); 
	populateStudentDetails(studentDetailsResult);
   	}


	}

/*
Populating student details in registration form
*/
private function populateStudentDetails(studentDetailsResult:XML):void{
	studentName.text=studentDetailsResult.student.student_name;
 	fatherName.text=studentDetailsResult.student.father_name;
 	motherName.text=studentDetailsResult.student.mother_name;
 	dob.text=studentDetailsResult.student.date_of_birth;
 	gender.text=studentDetailsResult.student.gender_description;
 	category.text=studentDetailsResult.student.category_description;
 	belongsToText.text=studentDetailsResult.student.entity_name;
 	programNameText.text=studentDetailsResult.student.program_name;
 	branchNameText.text=studentDetailsResult.student.branch_name;
	specializationNameText.text=studentDetailsResult.student.new_specialization_description;
 	semesterText.text=studentDetailsResult.student.semester;
 	rollNoText.text=user_name;
 	enrollmentNoText.text=studentDetailsResult.student.enrollment_number;
 	attemptNoText.text=studentDetailsResult.student.attempt;
 	getRegistrationDeadlines();
}

/* Getting registration deadlines for student's program*/

private function getRegistrationDeadlines():void{
	
	httpRegistrationDeadlines.send(inputData);
}

	private function faultHandler_RegistrationDeadlines(event:FaultEvent):void{
		
		Alert.show(commonFunction.getMessages('registrationNotAllowed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
        
    }
    
   private function resultHandler_RegistrationDeadlines(event:ResultEvent):void{
   	deadlinesResult=event.result as XML;
  	 	if(deadlinesResult.children().exceptionstring.length() > 0){
	   	  mx.controls.Alert.show(deadlinesResult.exception.exceptionstring,"Information",4,this,goToLogin,successIcon);

   }else{
if((deadlinesResult.deadlines.registration_status)=="Yes"){

		getElectiveCourse();
	}else if((deadlinesResult.deadlines.registration_status)=="No"){
	Alert.show(commonFunction.getMessages('registrationNotAllowed'),commonFunction.getMessages('error'),4,this,goToLogin,errorIcon);
      }
   }
      
   }
      
private function goToLogin(event:CloseEvent):void{
	
	if(event.detail==Alert.OK){	
		if(forCancel=="outer"){		
    this.parentDocument.loaderCanvas2.addChild(new LoginForm());
	this.parentDocument.loaderCanvas2.removeChildAt(0);
		}else if(forCancel=="inner"){
			this.parentDocument.loaderCanvas.addChild(new LoginForm1);
	        this.parentDocument.loaderCanvas.removeChildAt(0);
		}
			}
	
}

/*Getting Compulsary Courses*/
private function getCompulsaryCourse(selectedGroup):void{

	inputData["selectedGroup"]=selectedGroup.toString();
	inputData["time"]=new Date().getTime();
	//Alert.show("comp req going");
	httpCompulsaryCourses.send(inputData);
}

private function faultHandler_CompulsaryCourses(event:FaultEvent):void{
	
	Alert.show("rohit1");
	      Mask.close();
         Alert.show(commonFunction.getMessages('courseFailureError'),commonFunction.getMessages('error'),0,null,null,errorIcon);
    }
   private function resultHandler_CompulsaryCourses(event:ResultEvent):void{
   	compulsaryCourseXml=event.result as XML;
   	
   	var compulsaryAC:ArrayCollection=new ArrayCollection();
//   	for each (var object:Object in compulsaryCourseXml.elective ){
//   		compulsaryAC.addItem({course_code:object.course_code,course_name:object.course_name,
//   		credits:object.credits,course_type:object.course_type,minimum_credits:object.minimum_credits,
//   		maximum_credits:object.maximum_credits,course_group:object.course_group,course_classification:object.course_classification});
//  
//   	}
//   	groupByCourseType.source=compulsaryAC;
//   	groupByCourseType.refresh();
		regDataArray=new ArrayCollection;
			    
			    for each(var obj:Object in compulsaryCourseXml.elective)
			    {
			    	regDataArray.addItem({select:false,courseName:obj.course_name,courseCode:obj.course_code,
			    		courseType:obj.course_type,course_group:obj.course_group,courseClassification:obj.course_classification,courseClassificationName:obj.course_classification_name,
			    			credits:obj.credits,minimum_credits:obj.minimum_credits,maximum_credits:obj.maximum_credits,course_group_code:obj.course_group_code});
			    }
			    
				var cg:String;
				var temp:Array=new Array();
				var minCredits:Number;
				var maxCredits:Number;
				var totalCredits:Number;
				
				var finalData:ArrayCollection=new ArrayCollection;
				    
				for each(var obj1:Object in regDataArray)
				{
					cg=obj1.course_group;
					minCredits=Number(obj1.minimum_credits);
				    maxCredits=Number(obj1.maximum_credits);
				    totalCredits=0;
				    if(temp.indexOf(cg)<0)
				    {
				    	temp.push(cg);
				    	for each(var obj11:Object in regDataArray)
					    {
						    var cg1:String=obj11.course_group;
						    if(cg==cg1)
						    {
							    totalCredits+=Number(obj11.credits);
						    }
					    }
					    if((totalCredits==minCredits)&&(totalCredits<=maxCredits))
				        {
				        	for each(var obj12:Object in regDataArray)
				            {
				            	if(obj12.course_group+""==cg)
				                {
				                    obj12.select=true;
				                }
				                finalData.addItem(obj12);
				            }  
				        }
				    }
				}
 
 
 //altered from finalData to regDataArray temporarily
				groupByCourseType.source=regDataArray;
				groupByCourseType.refresh();
   	
   }
   	
   	
   	/*Getting Elective Courses*/
private function getElectiveCourse():void{
	httpElectiveCourses.send(inputData);
	

	
	
}

[Bindable] public var electiveAC:ArrayCollection;

private function faultHandler_ElectiveCourses(event:FaultEvent):void{
	
         Alert.show(commonFunction.getMessages('courseFailureError'),commonFunction.getMessages('error'),0,null,null,errorIcon);
    }
    
    
private function resultHandler_ElectiveCourses(event:ResultEvent):void{
   	
   	electiveCourseXml=event.result as XML;
   //	Alert.show(electiveCourseXml);
  
  
	
   	
   	
   	  	electiveAC=new ArrayCollection();
   	for each (var object:Object in electiveCourseXml.elective){
   		electiveAC.addItem({course_code:object.course_code,course_name:object.course_name,
   		credits:object.credits,course_type:object.course_type,minimum_credits:object.minimum_credits,
   		maximum_credits:object.maximum_credits,course_group:object.course_group,courseClassificationName:object.course_classification_name,
   		course_classification:object.course_classification,course_group_code:object.course_group_code});
   	}
   	
   	if(electiveAC.length<1){
   		electivePanel.visible=false;
   	}
   	else
   	{
   		electivePanel.visible=true;
   	}
   	
   	
   	
   	            var cgg:String;
				var temp1:Array=new Array();
				var minCredit:Number;
				var maxCredit:Number;
				var totalCredit:Number;
				
   	for each(var obj2:Object in electiveAC)
				{
					cgg=obj2.course_group;
					minCredit=Number(obj2.minimum_credits);
				    maxCredit=Number(obj2.maximum_credits);
				    totalCredit=0;
				    if(temp1.indexOf(cgg)<0)
				    {
				    	temp1.push(cgg);
				    	for each(var obj11:Object in electiveAC)
					    {
						    var cg11:String=obj11.course_group;
						    if(cgg==cg11)
						    {
							    totalCredit+=Number(obj11.credits);
						    }
					    }
					    if((totalCredit==minCredit)&&(totalCredit<=maxCredit))
				        {
				        	for each(var obj122:Object in electiveAC)
				            {
				            	if(obj122.course_group+""==cgg)
				                {
				                    obj122.select=true;
				                }
				            }  
				        }        
				    }            
				}                
				                
				                	
   	groupByCourseGroup.source=electiveAC;
   	groupByCourseGroup.refresh(); 	
   	
   	Mask.show(commonFunction.getMessages('pleaseWait'));
   	httpCoreCourseGroup.send(inputData);
   	// getting course type summary
  // 	Alert.show("rohit pct query going");
   	
   	
//   	getCourseTypeSummary();
//   	// getting summary from program_term_details
//   	getTermSummary();
   }
   
   
   
   
   
    private function resultHandler_CoreGroup(event:ResultEvent):void{
    	
    Mask.close();
   	groupCat=event.result as XML; 
   	Mask.show(commonFunction.getMessages('pleaseWait')); 	
	httpCoreCourseGroupRule.send(inputData);
   }
   
   
    private function resultHandler_CoreGroupRule(event:ResultEvent):void{
    	Mask.close();
   	dep=event.result as XML;
   	if((studentDetailsResult.student.admission_mode.toString().toUpperCase()=='NOR') || (studentDetailsResult.student.admission_mode.toString().toUpperCase()=='SWT')){
		Mask.show("Go2"); 
		httpOldCourseGroup.send(inputData);
	}else{
		 doComplete();
	}
	
    
   }

public function validateNext():void{
	
	if(electiveAC.length<1){
		registratinStack.selectedIndex=1;
	}else{
	var selectedData:Array=new Array();
	var gridData:Object = electiveCourseGrid.dataProvider;
	var courseGroup:String="";
	var totalCredits:Number=0;
	var minCredits:Number=0;
	var maxCredits:Number=0;
	var validError:Boolean=false;
	var cursor:IViewCursor = gridData.createCursor();
	//row
	while(!cursor.afterLast)
	{
		var obj:Object=cursor.current;
		var group:String=obj.course_group;
		var checked:Boolean=obj.select;
		if(group!=null)
		{
		    if(courseGroup=="")
	        {
	        	courseGroup=group;
	        	minCredits=obj.minimum_credits;
	        	maxCredits=obj.maximum_credits;
	        	if(checked)
	        	{
	        	    totalCredits=Number(obj.credits);
	        	}
	        }
	        else
	        {
	        	if(group==courseGroup)
	        	{
	        		minCredits=obj.minimum_credits;
	        	    maxCredits=obj.maximum_credits;
	        	    if(checked)
	        	    {
	        	        totalCredits+=Number(obj.credits);
	        	    }
	        	}
	        	else
	        	{
	        		
	        		if(totalCredits<minCredits)
	        	    {
	        		    validError=true;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectAtleast')+" "+minCredits+" "+commonFunction.getMessages('creditsFrom')+" "+courseGroup+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        	    }
	        	    else if(totalCredits>maxCredits)
	        	    {
	        		    validError=true;
	        		    Alert.show(commonFunction.getMessages('pleaseSelectLessThanorEqualto')+" "+maxCredits+" "+commonFunction.getMessages('creditsFrom')+" "+courseGroup+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        	    }
	        		courseGroup=group;
	        		totalCredits=0;
	        	    minCredits=obj.minimum_credits;
	        	    maxCredits=obj.maximum_credits;
	        	    if(checked)
	        	    {
	        	        totalCredits=Number(obj.credits);
	        	    }
	        	}
	        }
	        if(checked)
	        {
	        	selectedData.push(obj);
	        }
		}
		cursor.moveNext();
		if(cursor.afterLast)
		{

	        if(totalCredits<minCredits)
	        {
	            validError=true;
	            Alert.show(commonFunction.getMessages('pleaseSelectAtleast')+" "+minCredits+" "+commonFunction.getMessages('creditsFrom')+" "+courseGroup+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        }
	        else if(totalCredits>maxCredits)
	        {
	            validError=true;
	            Alert.show(commonFunction.getMessages('pleaseSelectLessThanorEqualto')+" "+maxCredits+" "+commonFunction.getMessages('creditsFrom')+" "+courseGroup+".",commonFunction.getMessages('error'),0,null,null,errorIcon);
	        }else{
	        	registratinStack.selectedIndex=1;
	        }
		}
	}
	}
}   

private function resultHandler_OldCoreGroup(event:ResultEvent):void
{
	 Mask.close();
	oldGroupXml=event.result as XML;
	doComplete();
}
