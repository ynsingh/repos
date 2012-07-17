/**
 * @(#) CancelCourseGroupAs.as
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
 
 var urlPrefix:String=commonFunction.getConstants('urlCms')+"/cancelcoursegroup/";
 var cancelGroupXml:XML;
 var cancelCodeXml:XML;
 var addGroupXml:XML;
 var addCodeXml:XML;
 
 var cancelGroupArrCol:ArrayCollection;
 var cancelCourseCodeArrCol:ArrayCollection;
 var addGroupArrCol:ArrayCollection;
 var addCourseCodeArrCol:ArrayCollection;
 
 var totalCancelCreditExcludingAudit:Number=0;
 var totalAddCreditExcludingAudit:Number=0;
 var totalCancelCredit:Number=0;
 var totalAddCredit:Number=0;
 var addTheroyCredit:Number=0;
 var cancelTheoryCredit:Number=0;
 var addPracticalCredit:Number=0;
 var cancelPracticalCredit:Number=0;
 var cancelCourseGroup:String="";
 var addCourseGroup:String="";
 var addCourse:String="";  
 var program:String="";
 var entity:String="";
 var startDate:String="";
 var endDate:String="";
 
 public var flag:int=0;
 public var flag1:int=0;
 
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

/**
  * This function calls on creation complete of ok panel
  * Method to initialize variables
 **/
function init():void{
	urlPrefix=commonFunction.getConstants('urlCms')+"/cancelcoursegroup/";
}
 /**
  * This function calls on click of ok button
 **/
 function onClickOk():void{
 	if(rollNumberInput.text==""){
 		Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
 	}
 	else{
 		flag=0;
 		flag1=0; 		
 		resetCancelCourseGrid();
 		resetAddCourseGrid();
 		addGroupCanvas.visible=false;
 		var obj:Object={};
 		obj["rollNumber"]=rollNumberInput.text;
 		obj["date"]=new Date();
 		getCancelCourseGroupService.send(obj);
 	}
 }

 /**
 * Failure handler
 **/
 function HttpServiceFaultHandler(event:FaultEvent):void{
 	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
 }
 /**
 * Success handler of getCancelCourseGroupService
 **/
 function cancelCourseHttpServiceResultHandler(event:ResultEvent):void{
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
 	
 	cancelGroupXml=event.result as XML; 	
 	cancelGroupArrCol=new ArrayCollection;
 	
 	for each(var obj:Object in cancelGroupXml.Details){	
			cancelGroupArrCol.addItem({select:false,group:obj.courseGroup,name:obj.groupName});	
			program=obj.programCourseKey;	
			entity=obj.entityId;		
 			startDate=obj.semesterStartDate;
 			endDate=obj.semesterEndDate;		
 	}
 	if(cancelGroupArrCol.length>0){
		if(cancelGroupXml.Details[0].branchName=="REG"){
			cancelGroupGrid.dataProvider=cancelGroupArrCol;
		}
		else{
			Alert.show(resourceManager.getString('Messages','cantCancelResultProcessed',[rollNumberInput.text]),commonFunction.getMessages('info'),0,null,null,infoIcon);
		}
  	}
 	else{
 		Alert.show(resourceManager.getString('Messages','noRecordFoundForCancelCourse',[rollNumberInput.text]),commonFunction.getMessages('info'),0,null,null,infoIcon);
 	}
 }
 	
 /**
 * Success handler of getCancelCourseCodeService
 **/
 function cancelCourseCodeHttpServiceResultHandler(event:ResultEvent):void{
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
 	cancelCodeXml=event.result as XML; 	 	
 	var str:String="";
 	var courseCategory:String="";
 	cancelCourseCodeArrCol=new ArrayCollection;
 	for each(var obj:Object in cancelCodeXml.Details){		 		
 		if(obj.courseClass.toString()=="T"){
 			str="Theory";
 		}
 		else{
 			str="Practical";
 		}
 		if(obj.courseCategory=="REG"){
 			courseCategory="Regular";
 		}
 		else{
 			courseCategory="Distance";
 		}
 		cancelCourseCodeArrCol.addItem({code:obj.courseCode,name:obj.courseName,credit:obj.credits,type:obj.courseClass,typeName:str,courseCategory:courseCategory,courseCategoryCode:obj.courseCategory});
 	}
 	if(cancelCourseCodeArrCol.length>0){
 		cancelCourseGrid.dataProvider=cancelCourseCodeArrCol;
 		var param:Object={};
 		param["rollNumber"]=rollNumberInput.text;
 		param["startDate"]=startDate;
 		param["endDate"]=endDate
 		param["program"]=program;
 		getAddCourseGroupService.send(param); 		
 	} 	
 }
 
 /**
 * Success handler of getAddCourseGroupService
 **/
 function AddCourseGroupHttpServiceResultHandler(event:ResultEvent):void{
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
 	addGroupArrCol=new ArrayCollection;
 	addGroupXml=event.result as XML;
 	for each(var obj:Object in addGroupXml.Details){ 		
 		addGroupArrCol.addItem({select:false,addGroup:obj.courseGroup,name:obj.groupName});
 	} 	
 	if(addGroupArrCol.length>0){
 		addGroupCanvas.visible=true;
 		addGroupGrid.dataProvider=addGroupArrCol;
 	}
 	else{
 		Alert.show(commonFunction.getMessages('NoCourseGroupForAdd'),commonFunction.getMessages('info'),0,null,null,infoIcon);
 	}
 }
 /**
 * Success handler of getAddCourseCodeService
 **/
 function AddCourseCodeHttpServiceResultHandler(event:ResultEvent):void{
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
 	var str:String="";
 	var courseCategory:String="";
 	addCodeXml=event.result as XML; 	
 	addCourseCodeArrCol=new ArrayCollection;
 	for each(var obj:Object in addCodeXml.Details){
 		if(obj.courseClass.toString()=="T"){
 			str="Theory";
 		}
 		else{
 			str="Practical";
 		}
 		if(obj.courseCategory=="REG"){
 			courseCategory="Regular";
 		}
 		else{
 			courseCategory="Distance";
 		}
 		addCourseCodeArrCol.addItem({select:false,code:obj.courseCode,name:obj.courseName,credit:obj.credits,type:obj.courseClass,typeName:str,courseCategory:courseCategory,courseCategoryCode:obj.courseCategory});
 	}
 	if(addCourseCodeArrCol.length>0){
 		addCourseGrid.dataProvider=addCourseCodeArrCol;
 	}
 	else{
 		Alert.show("There is no course for selected course group",commonFunction.getMessages('info'),0,null,null,infoIcon);
 	}
 }
 
 /**
 * Function is call when group grid check box is selected 
 * Method to get course Code for selected Course Group
 **/
 
 public function getCourseCode():void{
 	if(flag==1){
 		var param:Object={};
 		for each (var o:Object in cancelGroupGrid.dataProvider){
 			if(o.select){ 				
 				param["group"]=o.group; 				
 			}
 		}
 		
 		param["rollNumber"]=rollNumberInput.text;
 		param["startDate"]=startDate;
 		param["endDate"]=endDate
 		param["program"]=program;
 		getCancelCourseCodeService.send(param);
 	}
 }
 
 /**
 * Function is call when group grid check box is selected 
 * Method to get course Code for selected Course Group for Add
 **/
 
 public function getCourseCodeForAdd():void{
 	if(flag1==1){ 		
 		var param:Object={};
 		for each (var o:Object in addGroupGrid.dataProvider){
 			if(o.select){ 				 				
 				addCourseGroup=o.addGroup;
 			}
 		} 	
 		param["group"]=	addCourseGroup;
 		param["rollNumber"]=rollNumberInput.text;
 		param["startDate"]=startDate;
 		param["endDate"]=endDate
 		param["program"]=program; 		
 		getAddCourseCodeService.send(param);
 	}
 }
 
 /**Method to reset cancelGroupGrid*/
 
function resetCancelGroupGrid():void{
	for each (var o:Object in cancelGroupGrid.dataProvider){
 			o.select=false;
 	}
 		cancelGroupGrid.dataProvider.refresh();
 		totalCancelCredit=0;
 		totalCancelCreditExcludingAudit=0;
 		totalAddCreditExcludingAudit=0;
 		cancelTheoryCredit=0;
 		cancelPracticalCredit=0;
		totalAddCredit=0;
  		addTheroyCredit=0;
  		addPracticalCredit=0;
  		cancelCourseGroup="";
 		addCourseGroup="";
  		addCourse="";
}

/**Method to reset cancelCourseGrid*/
 
function resetCancelCourseGrid():void{
	if(cancelCourseGrid.dataProvider!=null){
		cancelCourseGrid.dataProvider.removeAll();
		cancelCourseGrid.dataProvider.refresh();
	}
		totalCancelCredit=0;
		totalCancelCreditExcludingAudit=0;
 		totalAddCreditExcludingAudit=0;
		cancelTheoryCredit=0;
 		cancelPracticalCredit=0;
		totalAddCredit=0;
  		addTheroyCredit=0;
  		addPracticalCredit=0;
  		cancelCourseGroup="";
 		addCourseGroup="";
  		addCourse="";
}

/**Method to reset addGroupGrid*/
 
function resetAddGroupGrid():void{
	for each (var o:Object in addGroupGrid.dataProvider){
 			o.select=false;
 	}
 		addGroupGrid.dataProvider.refresh();
 		totalCancelCredit=0;
 		totalCancelCreditExcludingAudit=0;
 		totalAddCreditExcludingAudit=0;
 		cancelTheoryCredit=0;
 		cancelPracticalCredit=0;
		totalAddCredit=0;
  		addTheroyCredit=0;
  		addPracticalCredit=0;
  		cancelCourseGroup="";
 		addCourseGroup="";
  		addCourse="";
}

/**Method to reset addCourseGrid*/
 
function resetAddCourseGrid():void{
	if(addCourseGrid.dataProvider!=null){
		addCourseGrid.dataProvider.removeAll();
		addCourseGrid.dataProvider.refresh();		
	}
		totalCancelCredit=0;
		totalCancelCreditExcludingAudit=0;
 		totalAddCreditExcludingAudit=0;
		cancelTheoryCredit=0;
 		cancelPracticalCredit=0;
		totalAddCredit=0;
  		addTheroyCredit=0;
  		addPracticalCredit=0;
  		cancelCourseGroup="";
 		addCourseGroup="";
  		addCourse="";
}

/**
 * Function to validate records for submit 
 **/
 
 function validate():Boolean{
 	var bool:Boolean=false;
 	var flg:int=0;
 	var flg1:int=0;
 	for each(var obj:Object in cancelGroupGrid.dataProvider){
 		if(obj.select==true){
 			cancelCourseGroup=obj.group;
 			flg=flg+1;
 		}
 	}
 	for each(var ob:Object in addCourseGrid.dataProvider){ 
 		if(ob.select==true){
 			flg1=flg1+1;
 		}
 	} 	
 	if(flg>0){
 		if(flg1>0){
 			bool=true;
 		}
 		else{
 			Alert.show(commonFunction.getMessages('selectCourseCode'),commonFunction.getMessages('error'),0,null,null,errorIcon);
 		}
 	}
 	else{
 		Alert.show(commonFunction.getMessages('selectCourseGroup'),commonFunction.getMessages('error'),0,null,null,errorIcon);
 	}
 	return bool;	
 }
 
/**
 * Method call when submit button is clicked
 * Method to ask confirmation for submit
 */
function onClickSubmit():void{
	if(validate()){
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),this,confirm,questionIcon);
	}
}

/**
 * Confirmatilon method to submit records
 * This method send request to cancel student course group and to add student course group
 **/
 
 function confirm(event:CloseEvent):void{
 	if(event.detail==Alert.YES){	
 		 for each(var obj:Object in cancelCourseGrid.dataProvider){
	 			totalCancelCredit=totalCancelCredit+Number(obj.credit);	
	 			if(obj.courseCategoryCode=="REG"){
	 				totalCancelCreditExcludingAudit=totalCancelCreditExcludingAudit+Number(obj.credit);	
					if(obj.type=="T"){
						cancelTheoryCredit=cancelTheoryCredit+Number(obj.credit);	
					}	
					else{
						cancelPracticalCredit=cancelPracticalCredit+Number(obj.credit);	
					}	
	 			}
			}
		
		for each(var obj:Object in addCourseGrid.dataProvider){
			if(obj.select==true){
				addCourse=addCourse+obj.code+","+obj.name+"|";
				totalAddCredit=totalAddCredit+Number(obj.credit);			
				if(obj.courseCategoryCode=="REG"){
					totalAddCreditExcludingAudit=totalAddCreditExcludingAudit+Number(obj.credit);					
					if(obj.type=="T"){
						addTheroyCredit=addTheroyCredit+Number(obj.credit);
					}
					else{
						addPracticalCredit=addPracticalCredit+Number(obj.credit);
					}
				}	
			}			
		}
		var param:Object={};
		param["addCourseGroup"]=addCourseGroup;
		param["cancelCourseGroup"]=cancelCourseGroup;
		param["addCourse"]=addCourse;
		param["totalAddCreditExcludingAudit"]=totalAddCreditExcludingAudit;
		param["totalCancelCreditExcludingAudit"]=totalCancelCreditExcludingAudit;
		param["totalAddCredit"]=totalAddCredit;
		param["totaoAddTheoryCredit"]=addTheroyCredit;
		param["totalAddPracticalCredit"]=addPracticalCredit;
		param["totalCancelCredit"]=totalCancelCredit;
		param["cancelTheoryCredit"]=cancelTheoryCredit;
		param["cancelPracticalCredit"]=cancelPracticalCredit;
		param["rollNum"]=rollNumberInput.text;
		param["program"]=program;
		param["entity"]=entity;
		param["startDate"]=startDate;
		param["endDate"]=endDate;
		
		if(totalAddCredit == totalCancelCredit){			
			if(addTheroyCredit==cancelTheoryCredit && addPracticalCredit==cancelPracticalCredit){
				param["flag"]="EQUAL";
			}
			else{
				param["flag"]="GREATER";
			}
			cancelCourseService.send(param);
		}
		else if(totalAddCredit > totalCancelCredit){
			param["flag"]="GREATER";
			cancelCourseService.send(param);			
		}
		else{
			Alert.show(resourceManager.getString('Messages','addCourseCreditGreater',[totalAddCredit,totalCancelCredit]),commonFunction.getMessages('info'),0,null,null,infoIcon);
			totalAddCreditExcludingAudit=0;
			totalCancelCreditExcludingAudit=0;
			totalAddCredit=0;
			addPracticalCredit=0;
			addTheroyCredit=0;
			totalCancelCredit=0;
			cancelTheoryCredit=0;
			cancelPracticalCredit=0;
		}
	}
 }
 
 /**
 * Success handler of cancelCourseService
 **/
 function cancelCourseResultHandler(event:ResultEvent):void{
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
 	var result:XML=event.result as XML;
 	var str:String=result.resultdata;
 	var message:Array=str.split("-");
 	if(message[0]=="success"){ 		
 		Alert.show(commonFunction.getMessages('cancelCourseSuccess'),commonFunction.getMessages('success'),0,null,null,successIcon);
 		onClickOk();
 	}
 	else if(message[0]=="sqlError"){
 		Alert.show(commonFunction.getMessages('sqlErrorInCancelCourse'),commonFunction.getMessages('error'),0,null,null,errorIcon);
 	}
 	else if(message[0]=="greaterThanMaxCredit"){
 		Alert.show(resourceManager.getString('Messages','addTotalCreditGreater',[message[1],message[2]]),commonFunction.getMessages('info'),0,null,null,infoIcon);
 	}
 	else{
 		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),0,null,null,errorIcon);
 	}
 		resetCancelGroupGrid();
 		resetCancelCourseGrid();
 		resetAddGroupGrid();
 		resetAddCourseGrid();
 		flag=0;
 		flag1=0;
 		addGroupCanvas.visible=false;
 }
 
 /**
 * Method call on click of cancel button
 **/
 
 function onClickCancel():void{
 	flag=0;
 	flag1=0;
 	parent.removeChild(this);
 }
