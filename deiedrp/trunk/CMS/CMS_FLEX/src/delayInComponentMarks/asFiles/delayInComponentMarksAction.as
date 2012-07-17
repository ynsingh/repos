// ActionScript file
/**
 * @(#) delayInComponentMarksAction.as
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
 
 import flash.events.Event;
 
 import mx.collections.ArrayCollection;
 import mx.controls.Alert;
 import mx.controls.DataGrid;
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

/**
 * This method runs on the creation compelete of This interface
 */  
 public function onCreationComplete():void{
	Mask.show(commonFunction.getMessages('pleaseWait'));	
 	getAllCourses.send(new Date);	
 	
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
	getCourseDetails.send(object);
	
	}
	else{
	Alert.show((commonFunction.getMessages('enterCourseCode')),(commonFunction.getMessages('error')),0,null,null,errorIcon);
	}
	searchCanvas.visible=false;
		
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
	Alert.show((commonFunction.getMessages('allAwardBlankRejected')),(commonFunction.getMessages('info')),4,null,null,infoIcon);	
	getAllCourses.send(new Date);


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
 * This function Run on the Click of the CheckBox
 * Send Detail of the courses To know the Track oF components
 */ 

public function onCheckBoxChange(event:Event,grid:DataGrid):void{	


	arry=commonFunction.getSelectedRowData(courseGridId);
			if(arry.length>1){
				Alert.show(commonFunction.getMessages('selectOneRecord'),commonFunction.getMessages('info'),4,null,null,infoIcon);
				
			}
			else{
				var startYear:String=arry.getItemAt(0).semStart;
				object["entityId"]=arry.getItemAt(0).entityId;
				object["courseCode"]=arry.getItemAt(0).courseCode;
				object["programCourseKey"]=arry.getItemAt(0).programCourseKey;
				object["semStart"]=arry.getItemAt(0).semStart;
				object["year"]=startYear.slice(0,4);
				object["semEnd"]=arry.getItemAt(0).semEnd;
			
 				getComponentDetails.send(object);
			}
			
	var gridRecordData:ArrayCollection=courseGridId.dataProvider as ArrayCollection;
										
            		for(var e:int=0;e<gridRecordData.length;e++)
            		{
	            		var gridItem:Object=gridRecordData.getItemAt(e);
	            	    if(gridItem.select==false)
	            	    {
	            	    searchCanvas.visible=false;	
	            	    }
	            	}			

}

/**
 * This is a success handler of Http Service
 * Send To get Details Of Components
 */ 
public function getComponentDetailsSuccess(event:ResultEvent):void{
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
     if(courseXml.Details.length()==0){
     	Alert.show(commonFunction.getMessages('componentNotSet'),commonFunction.getMessages('info'),4,null,null,infoIcon);
     }  
     else{     
       	searchCanvas.visible=true;      
            arry=new ArrayCollection();
          for each(var obj:Object in courseXml.Details){
          	
          	arry.addItem({componentId:obj.marksContEval,teacher:obj.userId,actualDate:obj.startDate,displayDate:obj.endDate,componentStatus:obj.limit,delayDays:obj.lowerA});
          }  
            searchGrid.dataProvider=arry;
     }
}

