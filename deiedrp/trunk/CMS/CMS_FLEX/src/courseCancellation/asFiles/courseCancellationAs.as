/**
 * @(#) courseCancellationAs.as
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 * Author :Mandeep Sodhi
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
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

public var object:Object={};
public var courseXml:XML; 
public var arry:ArrayCollection;
public var flag:String=new String();

/**
 * This method runs on the creation compelete of This interface
 */  
 public function onCreationComplete():void{
 	Mask.show(commonFunction.getMessages('pleaseWait'));
 	object["teacher"]=flag;
 	getApprovedCourseCode.send(object);	
 	
 }
 
 /**
 * The method is executed on failure of 
 * a request
 **/ 	
public function onFailure(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	Mask.close();	
}

/**
 * This is a Success method of getApprovedCourseCode service
 * It get All the Approved Courses
 */ 
public function onApprovedCourseCodeSuccess(event:ResultEvent):void{
	courseXml=event.result as XML;
			if(courseXml.sessionConfirm == true)
             {
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
            }
	
	arry=new ArrayCollection();
	for each(var obj:Object in courseXml.Details){
			arry.addItem({courseCode:obj.courseCode});
	}
	courseCombo.selectedIndex=-1;
	courseCombo.dataProvider=arry;
	courseCombo.labelField = "courseCode";
	Mask.close();
	
}

/**
 * This function run on the click of Ok button
 */ 
public function onOkClick():void{
	if(Validator.validateAll([courseComboValidate]).length==0){
	object["courseCode"]=courseCombo.text;
	object["status"]="OK";
	getApprovedCourseDetails.send(object);
	
	}
	else{
	Alert.show((commonFunction.getMessages('enterCourseCode')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
		
}

/**
 * This is a Success method of Http service
 * It get All the Approved Courses
 */
public function onApprovedCourseDetailsSuccess(event:ResultEvent):void{
	courseXml=event.result as XML;
				if(courseXml.sessionConfirm == true)
             {
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
            }
            
	arry=new ArrayCollection();
	
	if(courseXml.Details.length()==0){
	gridCanvas.visible=false;
	courseCombo.dataProvider.removeAll();
	courseCombo.dataProvider.refresh();
	object["status"]=null;	
	object["teacher"]=flag;
	Alert.show((commonFunction.getMessages('allAwardBlankRejected')),(commonFunction.getMessages('info')),4,this,onAllReject,infoIcon);	
	getApprovedCourseCode.send(object);


	}
	else{
		gridCanvas.visible=true;
	for each(var obj:Object in courseXml.Details){
		arry.addItem({select:false,courseCode:obj.courseCode,entityId:obj.ownerEntityId,entityName:obj.ownerEntityName,programId:obj.ownerProgramId,
					programName:obj.ownerProgramName,branchId:obj.ownerBranchId,branchName:obj.ownerBranchName,
		            SpecializationId:obj.ownerSpecializationId,SpecializationName:obj.ownerSpecializationName,semStart:obj.startDate,semEnd:obj.endDate,
		            displayType:obj.displayType,status:obj.grade,approvedBy:obj.userId,programCourseKey:obj.courseName,semester:obj.marksEndSemester}); 	
	}
	}
	
	courseGridId.dataProvider=arry;	
}
/**
 * On click of Ok Button Of the Alert
 * This function remove the typed value in th suggetion box
 */ 
public function onAllReject(event:CloseEvent):void{

		courseCombo.text="";
}
/**
 * This function runs on the click of Cancel Approval Button
 */ 
public function onCancelApproval():void{
	  Alert.show(commonFunction.getMessages('conformForContinue'),
  commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,cancelOrNot,questionIcon);
}

/**
 * This function ask the user to Cancel The Approval Or Not
 */ 
public function cancelOrNot(event:CloseEvent):void{
	if(event.detail==1){
 	 	var arry:ArrayCollection=new ArrayCollection();
 	arry=courseGridId.dataProvider as ArrayCollection;
		object["entityId"]="";
		object["courseCode"]="";
		object["programCourseKey"]="";
		object["semStart"]="";
		object["semEnd"]="";
		
		for each (var obj:Object in arry){
			if(obj.select==true){
				object["entityId"]+=obj.entityId+'|';
				object["courseCode"]+=obj.courseCode+'|';
				object["programCourseKey"]+=obj.programCourseKey+'|';
				object["semStart"]+=obj.semStart+'|';
				object["semEnd"]+=obj.semEnd+'|';
				
			}
			
		}
		updateCourseDetails.send(object);
		
	}
}
/**
 * This is the success method of the Http Service
 * This Function Change The Status To WDW of the Selected Course code
 */  
public function onChangingCourseStatusSuccess(event:ResultEvent):void{
		courseXml=event.result as XML;
			if(courseXml.sessionConfirm == true)
             {
   			Alert.show(resourceManager.getString('Messages','sessionInactive'));
  			var url:String="";
	 		url=commonFunction.getConstants('navigateHome');
	 		var urlRequest:URLRequest=new URLRequest(url);
	 		urlRequest.method=URLRequestMethod.POST;
	 		navigateToURL(urlRequest,"_self");
            }
            		
		if(courseXml.Details.list_values>0){
			 		Alert.show("Award Blank Of " +object["courseCode"].toString().replace('|',' ')+ "rejected Successfully",
	commonFunction.getMessages('alert'),4,this,onCancelSuccesfull,successIcon); 
		}
		else{
			Alert.show(commonFunction.getMessages('cantReject'),
				commonFunction.getMessages('error'),4,this,onCancelSuccesfull,errorIcon); 
		}
	
}
/**
 * This function refresh the grid
 */  
public function onCancelSuccesfull(event:CloseEvent):void{
		object["courseCode"]=courseCombo.text;
	object["status"]="OK";
	getApprovedCourseDetails.send(object);
	
}

