/**
 * @(#) ManageCourseMasterAction.as
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

import courseMaster.CourseMaster;

import flash.events.Event;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;


[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
[Bindable]public var urlPrefix:String;
[Bindable]public var params:Object={};
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;
[Bindable]public var courseDetailsXml:XML;

/** creation of form **/
public function createForm():void
{
	urlPrefix=commonFunction.getConstants('url')+"/courseMaster/";
	params['time']=new Date();
	
	getEntityList.send(params);
}

/** get entity success handler **/
public function getEntitySuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    
    entityXml=event.result as XML;
	ownerEntityComboBox.dataProvider=entityXml.Details.name;
	ownerEntityComboBox.setFocus();
	Mask.close();
}

/** On change of entity **/
public function onEntityChange():void
{
	try{
	params['entityId']=entityXml.Details.(name==ownerEntityComboBox.selectedLabel).code;
	
	getProgramList.send(params);
	}catch(e:Error){}
}

/** get programs success handler **/
public function getProgramSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    
    programXml=event.result as XML;
	ownerProgramComboBox.dataProvider=programXml.Details.name;
	ownerEntityComboBox.setFocus();
	Mask.close();
	onEntityGet();
}

/** on change of program **/
public function onProgramChange():void
{
	try{
		params['programId']=programXml.Details.(name==ownerProgramComboBox.selectedLabel).id;
		params['time']=new Date();
	
		getBranchList.send(params);
	}
	catch(e:Error){}
}

/** get branches success handler **/
public function getBranchSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    
    branchXml=event.result as XML;
	ownerBranchComboBox.dataProvider=branchXml.Details.name;
	Mask.close();
}

/** on chnage of branch **/
public function onBranchChange():void
{
	try{
		params['branchId']=branchXml.Details.(name==ownerBranchComboBox.selectedLabel).id;
		params['time']=new Date();
		
		getSpecializationList.send(params);
	}
	catch(e:Error){}
}

/** get specialization success handler **/
public function getSpecializationSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}
    
    specializationXml=event.result as XML;
	ownerSpecializationComboBox.dataProvider=specializationXml.Details.name;
	Mask.close();
}

/** fault handler **/
public function onFailure(event:FaultEvent):void
{
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	Mask.close();
}

/** getting course details **/
public function showCourseDetails(flag:String):void
{
	if(Validator.validateAll([entityValidator]).length==0)
	{
		params['flag']=flag;
		params['entityId']=entityXml.Details.(name==ownerEntityComboBox.selectedLabel).code;
		//Alert.show(entityXml.Details.(name==ownerEntityComboBox.selectedLabel).code+"");
		if(ownerProgramComboBox.selectedIndex>=0)
		{
			params['programId']=programXml.Details.(name==ownerProgramComboBox.selectedLabel).id;
		}
		else
		{
			params["programId"]='%';
		}
		
		if(ownerBranchComboBox.selectedIndex>=0)
		{
			params['branchId']=branchXml.Details.(name==ownerBranchComboBox.selectedLabel).id;
		}
		else
		{
			params["branchId"]='%';
		}
		
		if(ownerSpecializationComboBox.selectedIndex>=0)
		{
			params['specializationId']=specializationXml.Details.(name==ownerSpecializationComboBox.selectedLabel).id;
		}
		else
		{
			params["specializationId"]='%';
		}
		
		params["courseCode"]=courseCodeTextInput.text+'%';
		
		params['time']=new Date();
		
		Mask.show(commonFunction.getMessages('pleaseWait'));
		getCourseDetails.send(params);
	}
	else
	{
		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

/** get course details success handler **/
public function getCourseDetailsSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
				    var url:String="";
				    url=commonFunction.getConstants('navigateHome');
				    var urlRequest:URLRequest=new URLRequest(url);
				    urlRequest.method=URLRequestMethod.POST;
				    navigateToURL(urlRequest,"_self");
		}
	}
 	catch(e:Error){}

    editButton.enabled=false;
	deleteButton.enabled=false;
	courseDetailsXml=event.result as XML;
	
	var courseData:ArrayCollection=new ArrayCollection();
	
	for each(var obj:Object in courseDetailsXml.Details)
	{
		courseData.addItem({select:false,ownerEntityId:obj.ownerEntityId,ownerEntityName:obj.ownerEntityName,ownerProgramId:obj.ownerProgramId,ownerProgramName:obj.ownerProgramName,
			ownerBranchId:obj.ownerBranchId,ownerBranchName:obj.ownerBranchName,ownerSpecializationId:obj.ownerSpecializationId,
			ownerSpecializationName:obj.ownerSpecializationName,courseGroupId:obj.courseGroupId,courseGroupName:obj.courseGroupName,courseTypeId:obj.courseTypeId,
			courseTypeName:obj.courseTypeName,courseClassificationId:obj.courseClassificationId,courseClassificationName:obj.courseClassificationName,courseCode:obj.courseCode,
			courseName:obj.courseName,marksContEval:obj.marksContEval,marksEndSemester:obj.marksEndSemester,marksTotal:obj.marksTotal,units:obj.units,credits:obj.credits,
			lectures:obj.lectures,tutorials:obj.tutorials,practicals:obj.practicals,sinceSession:obj.sinceSession,resultSystem:obj.resultSystem,dummyFlag:obj.dummyFlag,gradeLimit:obj.gradeLimit,edeiStatus:obj.edeiStatus});
	}
	courseDetailGrid.dataProvider=courseData;
	if(courseData.length>0)
	{
	    courseDetailPanel.visible=true;
	}
	else
	{
		courseDetailPanel.visible=false;
		if(params['flag']=="")
		{
			Alert.show(commonFunction.getMessages('noRecordFound'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
	}
	Mask.close();
}

/** this metthod show edit or delete screen **/
public function editOrDeleteCourseDetails(event:Event):void
{
	Mask.show(commonFunction.getMessages('pleaseWait'));
	var selectedValues:ArrayCollection=commonFunction.getSelectedRowData(courseDetailGrid);
	var editWindow:CourseMaster=CourseMaster(PopUpManager.createPopUp(this,CourseMaster,true));
			
			editWindow.ownerEntityComboBox.enabled=false;
			editWindow.ownerProgramComboBox.enabled=false;
			editWindow.ownerBranchComboBox.enabled=false;
			editWindow.ownerSpecializationComboBox.enabled=false;
			editWindow.courseCodeTextInput.editable=false;
			
			editWindow.ownerEntity=selectedValues[0].ownerEntityName;
			editWindow.ownerProgram=selectedValues[0].ownerProgramName;
			editWindow.ownerBranch=selectedValues[0].ownerBranchName;
			editWindow.ownerSpecialization=selectedValues[0].ownerSpecializationName;
			editWindow.courseCodeTextInput.text=selectedValues[0].courseCode;
			editWindow.courseNameTextInput.text=selectedValues[0].courseName;
			editWindow.courseClassification=selectedValues[0].courseClassificationName;
			editWindow.courseType=selectedValues[0].courseTypeName;
			editWindow.courseGroup=selectedValues[0].courseGroupName;
			editWindow.lecturesField.text=selectedValues[0].lectures;
			editWindow.tutorialsField.text=selectedValues[0].tutorials;
			editWindow.practicalsField.text=selectedValues[0].practicals;
			editWindow.creditsField.text=selectedValues[0].credits;
			editWindow.unitsField.text=selectedValues[0].units;
			editWindow.sinceSession=selectedValues[0].sinceSession.toString().substr(0,4);
			editWindow.internalMarksField.text=selectedValues[0].marksContEval;
			editWindow.externalMarksField.text=selectedValues[0].marksEndSemester;
			editWindow.resultSystem=selectedValues[0].resultSystem.toString();
			editWindow.totalMarksField.text=selectedValues[0].marksTotal;
			editWindow.InternalExternalActive.selectedValue=selectedValues[0].dummyFlag;
			/*gradeLimit Added by Mandeep*/
			editWindow.gradeLimitActive.selectedValue=selectedValues[0].gradeLimit;
			editWindow.edeiStatusGroup.selectedValue=selectedValues[0].edeiStatus;
			editWindow.referenceFunction=showCourseDetails;

					
			editWindow.submitButton.visible=false;
			editWindow.resetButton.visible=false;
			editWindow.showCloseButton=true;
			
			if(event.currentTarget.label=="Edit")
			{
				editWindow.updateButton.visible=true;
			    editWindow.deleteButton.visible=false;
			}
			else
			{
				editWindow.courseNameTextInput.editable=false;
		    	editWindow.courseClassificationComboBox.enabled=false;
				editWindow.courseTypeComboBox.enabled=false;
				editWindow.courseGroupComboBox.enabled=false;
				editWindow.lecturesField.editable=false;
		    	editWindow.tutorialsField.editable=false;
		    	editWindow.practicalsField.editable=false;
		    	editWindow.creditsField.editable=false;
		    	editWindow.unitsField.editable=false;
		    	editWindow.sinceSessionComboBox.enabled=false;
		    	editWindow.internalMarksField.editable=false;
		    	editWindow.externalMarksField.editable=false;
		    	editWindow.resultSystemCombo.enabled=false;
		    	editWindow.totalMarksField.editable=false;
		    	editWindow.InternalExternalActive.enabled=false;
		    	
		    	editWindow.updateButton.visible=false;
			    editWindow.deleteButton.visible=true;				
			}
			
			Mask.close();
			PopUpManager.centerPopUp(editWindow);
}

//on change of entity
public function onEntityGet():void
{
	courseDetailPanel.visible=false;
	
	ownerProgramComboBox.enabled=true;
	
	ownerProgramComboBox.selectedIndex=-1;
	ownerBranchComboBox.selectedIndex=-1;
	ownerSpecializationComboBox.selectedIndex=-1;
	courseCodeTextInput.text="";
	
	ownerBranchComboBox.enabled=false;
	ownerSpecializationComboBox.enabled=false;
	courseCodeTextInput.editable=true;
}