//<!--* @Date :30/12/2011
//* Version 1.0
//    Author :Arush 
//* Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
//* All Rights Reserved.
//*
//* Redistribution and use in source and binary forms, with or
//* without modification, are permitted provided that the following
//* conditions are met:
//*
//* Redistributions of source code must retain the above copyright
//* notice, this list of conditions and the following disclaimer.
//*
//* Redistribution in binary form must reproducuce the above copyright
//* notice, this list of conditions and the following disclaimer in
//* the documentation and/or other materials provided with the
//* distribution.
//*
//*
//* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
//* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
//* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
//* DISCLAIMED. IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
//* FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
//* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
//* OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
//* BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
//* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
//* OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
//* EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//*
//* Contributors: Members of EdRP, Dayalbagh Educational Institute
//*/
//// -->

// ActionScript file
import cancelRegistration.CancelRegistration;

import flash.events.Event;

import mx.collections.ArrayCollection;
import common.commonFunction;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.ResultEvent;

[Bindable] public  var urlPrefix:String;
public var labledata:ArrayCollection=new ArrayCollection;
public var parm:Object ;
public var semesterdateXML : XML ;
public var studentXML : XML ;
public var finalSemStatus: String;

[Bindable] public var studentcollection : ArrayCollection = new ArrayCollection ;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

public var parmstudent:Object = new Object ;

public  function processstudent(event:Event):void{
    parmstudent["rollNo"] = studentgrid.selectedItem.rollNo ;
    parmstudent["prvswitchstatus"] = studentgrid.selectedItem.swsts;
	           
	if (event.currentTarget.id == "swtact"){
		parmstudent["switchstatus"] = "SWT" ;
		if(finalSemStatus =="F"){
			if(studentgrid.selectedItem.swsts=="" && studentgrid.selectedItem.programOld=="" && 
			studentgrid.selectedItem.branchOld=="" && studentgrid.selectedItem.specializationOld==""
			&& studentgrid.selectedItem.semesterOld==""){
				insertStudentPST.send(parmstudent);
			}
			else Alert.show("Condition unmatched");
		}
		else{
			switchstudent.send(parmstudent);
  		}
    }
    if (event.currentTarget.id == "swtcan"){
    	parmstudent["switchstatus"] = "NOR" ;
    	if(finalSemStatus =="F"){
    		deleteStudentPST.send(parmstudent);		
    	}
    	else{
    		switchstudent.send(parmstudent);
    	}	
    }    
    if (event.currentTarget.id == "regcan"){
    	parmstudent["switchstatus"] = "" ;
    	var popupwindow:CancelRegistration = CancelRegistration(PopUpManager.createPopUp(this,CancelRegistration,true));
        popupwindow.regRollText.text=studentgrid.selectedItem.rollNo ;
        popupwindow.reregistrationCHBox.selected=true;
        popupwindow.cancelButton.visible=false;
        popupwindow.showCloseButton=true;
        popupwindow.okbutton.visible=true;
        PopUpManager.centerPopUp(popupwindow);
    }
}
protected function switchstudentSuccess(event:ResultEvent):void{
	
// 	var sts :String ;
// 	var progOld:String;
// 	var brnchOld:String;
// 	var spczOld:String;
// 	var semOld:String;
// 	var switchstatus :XML = event.result as  XML;
// 	for each(var obj:Object in switchstatus.student){
// 		sts = obj.switchstatus ;
// 		progOld=obj.soprogram;
// 		brnchOld=obj.sobranch;
// 		spczOld=obj.sospecialization;
// 		semOld=obj.sosemester;
// 	}
	
	if(parmstudent["switchstatus"]== "SWT" ){
		Alert.show(resourceManager.getString('Messages','studentSwitched',[fmpgm.text,topgm.text,studentgrid.selectedItem.rollNo]),commonFunction.getMessages('success'),0,null,null,successIcon);	
	}
	else if(parmstudent["switchstatus"]== "NOR" ){
		Alert.show(resourceManager.getString('Messages','studentUnSwitched',[fmpgm.text,topgm.text,studentgrid.selectedItem.rollNo]),commonFunction.getMessages('success'),0,null,null,successIcon);
	}
	getstudentlist.send(parm);
	
// 	if (studentcollection[studentgrid.selectedIndex].ss && sts == "SWT"){
//	 	studentcollection[studentgrid.selectedIndex].ss = false; 
//	 	studentcollection[studentgrid.selectedIndex].cs = true;	
//	 	studentcollection[studentgrid.selectedIndex].swsts = sts ;
// 	}
// 	if (studentcollection[studentgrid.selectedIndex].cs && sts == "NOR"){
//	 	studentcollection[studentgrid.selectedIndex].ss = true; 
//	 	studentcollection[studentgrid.selectedIndex].cs = false;	
//	 	studentcollection[studentgrid.selectedIndex].swsts = sts ;
// 	}
// 	if(studentcollection[studentgrid.selectedIndex].cs && sts == ""){
//	 	studentcollection[studentgrid.selectedIndex].ss = true; 
//	 	studentcollection[studentgrid.selectedIndex].cs = false;	
//	 	studentcollection[studentgrid.selectedIndex].swsts = sts ;
//	 	studentcollection[studentgrid.selectedIndex].programOld=progOld;
//	 	studentcollection[studentgrid.selectedIndex].branchOld=brnchOld;
//	 	studentcollection[studentgrid.selectedIndex].specializationOld=spczOld;
//	 	studentcollection[studentgrid.selectedIndex].semesterOld=semOld;
//	}
// 	studentcollection.refresh();
// 	studentgrid.dataProvider=studentcollection ;
}	 

protected function onFailure(event):void{
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);
} 	 

public function onSubmit():void{	
	if(combofm.selectedItem.toString().substr(0,4)<=comboto.selectedItem.toString().substr(0,4)){
		gridCanvas.visible=true;
		comboCanvas.visible=false;
		parm = new Object;
		for each(var obj:Object in labledata){
			parm["fmbranchId"]= obj.fmbranchId ;
			parm["fmspecializationId"]=obj.fmspecializationId ;
			parm["fmprogramId"] = obj.fmprogramId;
			parm["fmsemesterCode"] = obj.fmsemesterCode ;
			parm["fmentityId"]= obj.fmentid ;
			parm["tobranchId"]= obj.tobranchId ;
			parm["tospecializationId"]=obj.tospecializationId ;
			parm["toprogramId"] = obj.toprogramId;
			parm["tosemesterCode"] = obj.tosemesterCode ;
			parm["toentityId"]= obj.toentityId;
			parm["switchType"]=obj.switchType;
			parm["ruleId"]=obj.ruleId;
			parm["sessionStartDate"]=combofm.selectedItem.toString();
			parm["sessionEndDate"]=comboto.selectedItem.toString();
			//semesterdata = obj ; // store semester data  
		}
		getsemesterdate.send(parm);
	}
	else{
		gridCanvas.visible=false;
		comboCanvas.visible=true;
		Alert.show(resourceManager.getString('Messages','invalidSession'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	} 
}

protected function getsemesterdateSuccess(event:ResultEvent):void
{
	semesterdateXML = event.result as XML;
	if(semesterdateXML.semesterdate.fmsemesterstartdate<=semesterdateXML.semesterdate.tosemesterstartdate){
		fmsemstdt.text = fmtdate.format(String(semesterdateXML.semesterdate.fmsemesterstartdate)) ;
		fmsemeddt.text = fmtdate.format(String(semesterdateXML.semesterdate.fmsemesterenddate)) ;
		tosemstdt.text = fmtdate.format(String(semesterdateXML.semesterdate.tosemesterstartdate)) ; 
		tosemeddt.text = fmtdate.format(String(semesterdateXML.semesterdate.tosemesterenddate)) ;
		parm["fmsemstdt"]=String(semesterdateXML.semesterdate.fmsemesterstartdate);
		parm["fmsemeddt"]=String(semesterdateXML.semesterdate.fmsemesterenddate);
		parm["tosemstdt"]=String(semesterdateXML.semesterdate.tosemesterstartdate);
		parm["tosemeddt"]=String(semesterdateXML.semesterdate.tosemesterenddate);
		parm["fmprogramcoursekey"]=String(semesterdateXML.semesterdate.fmprogramcoursekey);	
		parm["finalSemStatus"]=String(semesterdateXML.semesterdate.finalSemStatus);
		finalSemStatus=String(semesterdateXML.semesterdate.finalSemStatus);
		getstudentlist.send(parm);
	}
	else {
		gridCanvas.visible=false;
		comboCanvas.visible=true;
		Alert.show(resourceManager.getString('Messages','invalidSemesterDate'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
}

protected function getstudentlistSuccess(event:ResultEvent):void{
 	studentXML = event.result as XML;
 	studentcollection=new ArrayCollection;
 	for each(var obj:Object in studentXML.student){
	 	var ss:Boolean=false;
	 	var cs:Boolean=false;
	 	var ctr:Boolean=false;
	 	if (String(obj.switchstatus) =="NOR" && String(obj.processstatus)==""  ){
			ss = true ;	
		}
		else{
			ss = false ;
		}
		if (String(obj.switchstatus) =="SWT" && String(obj.processstatus)==""  ){
			cs = true ;	
		}else{
			cs = false ;
		}
	 	if ( String(obj.processstatus)=="PRC"  ){
			ctr = true ;	
		}else{
			ctr = false ;
		}
		if (String(obj.switchstatus) =="" && String(obj.processstatus)==""  ){
			ss = true ;	
		}
		studentcollection.addItem({select:false,rollNo:obj.rollNumber ,
 		name:obj.Studentname, swsts:obj.switchstatus, processstatus:obj.processstatus,
 		registrationduedate:obj.registrationduedate,ss:ss,cs:cs,ctr:ctr,programOld:obj.soprogram,
 		branchOld:obj.sobranch,specializationOld:obj.sospecialization,semesterOld:obj.sosemester})
 	}
 	parmstudent = parm;
    studentgrid.dataProvider=studentcollection ;
 	urlPrefix = urlPrefix ;
}

public function switchAll():void{
	var count:int=0;
	for each(var obj:Object in studentcollection){
		if(obj.swsts=="SWT"){
		}
		else count++;
	}
	if(count>0)
	{
		Alert.show(resourceManager.getString('Messages','confirmSwitch',[count]),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,onConfirm,questionIcon);
	}
	else Alert.show(resourceManager.getString('Messages','noRecordforSwitch'),resourceManager.getString('Messages','info'),0,null,null,infoIcon);
}

function onConfirm(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		setSwitchAll.send(parm);	
	}
} 
