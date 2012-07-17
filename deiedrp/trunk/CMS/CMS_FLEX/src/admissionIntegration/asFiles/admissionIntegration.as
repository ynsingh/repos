/**
 * @(#) admissionIntegration.as
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
 
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/warning.png")]private var warningIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

var entityAC:ArrayCollection = new ArrayCollection;
var programAC:ArrayCollection = new ArrayCollection;
var studentAC:ArrayCollection = new ArrayCollection;
var branchAC:ArrayCollection = new ArrayCollection;
var entityXML:XML = new XML;
var programXML:XML = new XML;
var studentXML:XML = new XML;
var branchXML:XML = new XML;
var param:Object = new Object;
var studentCount:int = new int;

public function onLoad():void{
	httpGetEntities.send([new Date]);
}

public function resultGetEntities(event:ResultEvent):void{
	entityXML = event.result as XML;
	entityAC.removeAll();
	for each (var object:Object in entityXML.entity){
		entityAC.addItem({entityName:object.entityName});
	}
	if(entityAC.length==0){
		Alert.show(commonFunction.getMessages('entityNotExists'),commonFunction.getMessages('info'),4,null,null,infoIcon);
	}else{
		entityCombo.dataProvider=entityAC;
	}
}

public function onEntityChange():void{
	refreshData();
	param['entityId']=entityXML.entity.(entityName==entityCombo.selectedLabel).entityId;
	httpGetPrograms.send(param);
}

public function resultGetPrograms(event:ResultEvent):void{
	programXML = event.result as XML;
	programAC.removeAll();
	for each (var object:Object in programXML.entity){
		programAC.addItem({entityName:object.entityName});
	}
	if(programAC.length==0){
		Alert.show(commonFunction.getMessages('programsNotExists'),commonFunction.getMessages('info'),4,null,null,infoIcon);
	}else{
		programCombo.enabled=true;
		programCombo.dataProvider=programAC;
	}
}

public function onProgramChange():void{
	param['entityId']=entityXML.entity.(entityName==entityCombo.selectedLabel).entityId;
	param['programId']=programXML.entity.(entityName==programCombo.selectedLabel).entityId;
	studentsGrid.dataProvider=null;
	branchGrid.dataProvider=null;
	httpGetStudents.send(param);
}

public function resultGetStudents(event:ResultEvent):void{
	studentXML = event.result as XML;
	studentAC.removeAll();
	for each (var o:Object in studentXML.detail){
		studentAC.addItem({select:false,entityId:o.entityId,programId:o.programId,registrationNo:o.registrationNo,studentId:o.studentId,studentName:o.studentFirstName+' '+o.studentMiddleName+' '+o.studentLastName,
							studentFirstName:o.studentFirstName,enrollmentNo:o.enrollmentNo,
							studentMiddleName:o.studentMiddleName,studentLastName:o.studentLastName,fatherName:o.fatherFirstName+' '+o.fatherMiddleName+' '+o.fatherLastName,fatherFirstName:o.fatherFirstName,
							fatherMiddleName:o.fatherMiddleName,
							fatherLastName:o.fatherLastName,motherFirstName:o.motherFirstName,motherMiddleName:o.motherMiddleName,motherLastName:o.motherLastName,
							dob:o.dateOfBirth,category:o.category,primaryMail:o.primaryMail,secondaryMail:o.secondaryMail,nationality:o.nationality,religion:o.religion,
							guardian:o.guardian,gender:o.gender,addressKey:o.addressKey,perAddress:o.perAddress,perCity:o.perCity,perState:o.perState,perPincode:o.perPincode,phone:o.phone});
	}
	if(studentAC.length==0){
		Alert.show(commonFunction.getMessages('studentNotExist'),commonFunction.getMessages('info'),4,null,null,infoIcon);
		reset();
	}else{
		studentsGrid.dataProvider=studentAC;
		studentCount=studentAC.length;
		param['programId']=programXML.entity.(entityName==programCombo.selectedLabel).entityId;
		httpBranchDetails.send(param);
	}
}

public function resultGetBranchDetails(event:ResultEvent):void{
	branchXML = event.result as XML;
	branchAC.removeAll();
	for each (var o:Object in branchXML.entity){
		branchAC.addItem({select:false,branchId:o.branchId,branchName:o.branchName,specializationId:o.specializationId,specializationName:o.specializationName});
	}
	if(branchAC.length==0){
		Alert.show(commonFunction.getMessages('branchSpecMissing'),commonFunction.getMessages('info'),4,null,null,infoIcon);
		reset();
	}else{
		branchGrid.dataProvider=branchAC;
		studentCanvas.visible=true;
		branchCanvas.visible=true;
		submitButton.visible=true;
	}
}

private function submitConfirm():void{
		Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),3,null,onSubmit,questionIcon); 	
}

protected function onSubmit(event:CloseEvent):void{
	if(event.detail==Alert.YES){
	var branchArr:ArrayCollection=new ArrayCollection;
	var dataArr:ArrayCollection=new ArrayCollection;
	
			branchArr=commonFunction.getSelectedRowData(branchGrid);
			dataArr=commonFunction.getSelectedRowData(studentsGrid);
			
			if(dataArr.length==0 || branchArr.length==0){
				Alert.show(commonFunction.getMessages('selectionRequired'),commonFunction.getMessages('info'),4,null,null,infoIcon);
			}else{
			var data:String="";
			for each(var obj:Object in dataArr){
				if(String(obj.enrollmentNo).length==0){
					obj.enrollmentNo='undefined';
				}if(String(obj.studentMiddleName).length==0){
					obj.studentMiddleName='undefined';
				}if(String(obj.studentLastName).length==0){
					obj.studentLastName='undefined';
				}if(String(obj.fatherMiddleName).length==0){
					obj.fatherMiddleName='undefined';
				}if(String(obj.fatherLastName).length==0){
					obj.fatherLastName='undefined';
				}if(String(obj.motherMiddleName).length==0){
					obj.motherMiddleName='undefined';
				}if(String(obj.motherLastName).length==0){
					obj.motherLastName='undefined';
				}if(String(obj.secondaryMail).length==0){
					obj.secondaryMail='undefined';
				}if(String(obj.guardian).length==0){
					obj.guardian='undefined';
				}
				data=data+obj.entityId+"$"+obj.programId+"$"+obj.registrationNo+"$"+obj.enrollmentNo+"$"+obj.studentId+"$"+obj.studentFirstName+"$"+
									obj.studentMiddleName+"$"+obj.studentLastName+"$"+obj.fatherFirstName+"$"+
									obj.fatherMiddleName+"$"+obj.fatherLastName+"$"+obj.motherFirstName+"$"+obj.motherMiddleName+"$"+obj.motherLastName+"$"+
									obj.dob+"$"+obj.category+"$"+obj.primaryMail+"$"+obj.secondaryMail+"$"+obj.nationality+"$"+obj.religion+"$"+
									obj.guardian+"$"+obj.gender+"$"+obj.addressKey+"$"+obj.perAddress+"$"+obj.perCity+"$"+obj.perState+"$"+obj.perPincode+"$"+obj.phone+";";
			}
			var params:Object={};
			params["data"]=data;
			params["branchId"]=branchArr.getItemAt(0)["branchId"];
			params["specializationId"]=branchArr.getItemAt(0)["specializationId"];
			httpSubmit.send(params);
		}
	}
}

public function resultSubmit(event:ResultEvent):void{
	var successXML:XML = new XML;
	successXML = event.result as XML;
		if(successXML.exception.exceptionstring == 'E'){
	   		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else{
	   		Alert.show(commonFunction.getMessages('successRetrieve'),commonFunction.getMessages('success'),4,null,null,successIcon);
	   			param['entityId']=entityXML.entity.(entityName==entityCombo.selectedLabel).entityId;
				param['programId']=programXML.entity.(entityName==programCombo.selectedLabel).entityId;
				if(studentCount!=successXML.exception.exceptionstring){
					httpGetStudents.send(param);
				}else{
					reset();
				}
		}
}

public function reset():void{
	entityCombo.selectedIndex=-1;
	programCombo.selectedIndex=-1;
	programCombo.enabled=false;
	studentCanvas.visible=false;
	branchCanvas.visible=false;
	submitButton.visible=false;
}

public function refreshData():void{
	programCombo.enabled=false;
	studentsGrid.dataProvider=null;
	branchGrid.dataProvider=null;
	studentCanvas.visible=false;
	branchCanvas.visible=false;
	submitButton.visible=false;
}

public function onFailure(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
}
