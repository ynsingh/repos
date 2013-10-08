// ActionScript file
/**
 * @(#) insertInPrestagingAction.as
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
 * Author:Ashish Mohan
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
 
import ProgramCourse.GridDataExporterService.*;

import common.Mask;
import common.commonFunction;

import flash.events.Event;
import flash.events.TimerEvent;

import insertStudentToPrestaging.successPopUp;

import mx.collections.ArrayCollection;
import mx.collections.IViewCursor;
import mx.controls.Alert;
import mx.controls.DataGrid;
import mx.controls.Label;
import mx.controls.TextInput;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.core.ClassFactory;
import mx.events.CloseEvent;
import mx.events.DataGridEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.utils.StringUtil;
public var columnNameArr:Array;
public var tableData:Array;
public var newStList:XMLList=new XMLList;
public var oldStList:XMLList=new XMLList;
public var gridData:Array=new Array;

private static const DEL_LAST_ROW:String = "Remove Last Student";
private static const ADD_ROW:String = "Add More Student";
private static const DEL_INDEX_ROW:String="Remove Selected Student";
[Bindable]public var urlPrefix:String;
[Bindable]public var params:Object={};
[Bindable]public var entityXml:XML;
[Bindable]public var programXml:XML;
[Bindable]public var branchXml:XML;
[Bindable]public var specializationXml:XML;
[Bindable]private var tasks:ArrayCollection;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;


  	
//used to give tooltip to the data grid
private function showHeaderTooltip(dg:DataGrid):void {
	var cols:Array = mainGrid.columns;
   	for each(var dataColumn:DataGridColumn in cols){
		dataColumn.showDataTips=true;
		dataColumn.dataTipField=dataColumn.dataField;
		dataColumn.headerRenderer=new ClassFactory(mx.controls.Label);
	}
	mainGrid.columns=cols;
}

//to set intial data grid row
private function makeRow():void
{
   tasks = new ArrayCollection();
   for (var i:int=0;i<30;i++)
   {	
   		tasks.addItem(new Task("","","","","","","","","",""));
   }
}
 
//to get serial no in data grid
private function myLabelFun(item:Object, col:DataGridColumn):String
{
	var itemIndex:int = tasks.getItemIndex(item);
	return (itemIndex+1).toString();
}
 
 
 
//it add 10 more empty row in datagrid
private function addRow():void
{
 	if(validateFullEmptyRow(mainGrid)){
			Alert.show(commonFunction.getMessages('studentFatherNameMandatory'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	else{
		  for (var i:int=0;i<10;i++)
   			{	
   				tasks.addItem(new Task("","","","","","","","","",""));
   			}
	}
}




//on uploading interface
public function onInterfaceLoad():void
{	urlPrefix=commonFunction.getConstants('url')+"/prestagingData/";
	params['time']=new Date;
	params['mode']="all";
	getEntityList.send(params);
	getCategoryDetails.send(params);
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	programCombo.enabled=false;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
    submitButton.enabled=false;
}



/**
 * The function retrives the list of categories for the 
 * concerned university id
 * */
[Bindable]public var categoryDetails: XML;
public var categoryCode:Array;
public function onCategoryDetailsSuccess(event:ResultEvent):void{
	
	categoryDetails=event.result as XML;
	categoryCode=new Array;
	for each (var o:Object in categoryDetails.role){
		categoryCode.push(o.id);
		//categorylist.addItem({id:o.id,description:o.description});
		
	}				
}


//get entity list success handler
public function getEntitySuccess(event:ResultEvent):void
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
	
	entityXml=event.result as XML;
	entityCombo.dataProvider=entityXml.Details.name;
	entityCombo.selectedIndex=-1;
	entityCombo.enabled=true;
}


//on change of entity
public function onEntityChange(event:Event):void
{
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
	getProgramList.send(params);
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
    submitButton.enabled=false;
    studentCanvas.visible=false;
}



//get program list success handler
public function getProgramSuccess(event:ResultEvent):void
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
	programXml=event.result as XML;
	
	programCombo.dataProvider=programXml.Details.name;
	programCombo.selectedIndex=-1;
	programCombo.enabled=true;
}



//on change of program
public function onProgramChange(event:Event):void
{
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
	params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
	getBranchList.send(params);
	specilizationCombo.selectedIndex=-1;
	specilizationCombo.enabled=false;
    submitButton.enabled=false;
    studentCanvas.visible=false;

}



//get branch list success handler
public function getBranchSuccess(event:ResultEvent):void
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
	branchXml=event.result as XML;
	branchCombo.dataProvider=branchXml.Details.name;
	branchCombo.selectedIndex=-1;
	branchCombo.enabled=true;
}



//on change of branch
public function onBranchChange(event:Event):void
{
	params['time']=new Date;
	params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
	params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
	params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
	getSpecializationList.send(params);
    submitButton.enabled=false;
    studentCanvas.visible=false;
}



//get specialization success handler
public function getSpecializationSuccess(event:ResultEvent):void
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
	specializationXml=event.result as XML;
	specilizationCombo.dataProvider=specializationXml.Details.name;
	specilizationCombo.selectedIndex=-1;
	specilizationCombo.enabled=true;
}



//On change of specialization
public function onSpecializationChange(event:Event):void
{
	submitButton.enabled=true;
	studentCanvas.visible=false;
}



//On clik of ok 
public function visibleStudentGrid(event:Event):void
{	startTimer();
	getStudentListMethod();
	studentCanvas.visible=true;
	headerCanvas.enabled=false;
}



public function startTimer():void{
    var timer:Timer = new Timer(10*60*1000); //time in milliseconds i.e for 10 min
	timer.addEventListener(TimerEvent.TIMER, onTimer);
	timer.start();
}

public function onTimer(e:TimerEvent):void
{
    saveData();
}


public function getStudentListMethod():void{
		params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
		params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
		params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
		params['specializationId']=specializationXml.Details.(name==specilizationCombo.selectedLabel).id;
		params['time']=new Date;
		getStudentList.send(params);
}

//setting already saved data
public function getStudentListSuccess(event:ResultEvent):void{
	var s:XML=event.result as XML;
	var i:int=0;
	makeRow();
	for each(var ob:Object in s.personalInfo){
		var enrollmentNumber:String=ob.enrollmentNumber;
		if(enrollmentNumber=="NA"){
			enrollmentNumber="";
		}
		var dob:String=ob.dateOfBirth;
		var yy:String=dob.substr(0,4);
		var mm:String=dob.substr(5,2);
		var dd:String=dob.substr(8,2);
		dob=dd+mm+yy;

		var categoryName:String=ob.categoryName;
		var hindiName:String=decodeURI(ob.hindiName);
		
		tasks.addItemAt(new Task(enrollmentNumber,ob.studentFirstName,ob.fatherFirstName,dob,
			ob.categoryName,ob.primaryEmailId,ob.gender,ob.addressLineOne,ob.path,hindiName),i);
			
		i++;
	}

	tasks.refresh();
	mainGrid.dataProvider=null;
	mainGrid.dataProvider=tasks;

}


public  var fullStRow:String="";
//On clik of save 
public function saveData():void
{	
		params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
		params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
		params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
		params['specializationId']=specializationXml.Details.(name==specilizationCombo.selectedLabel).id;
		params['time']=new Date;

		if(checkGridHalfFill(mainGrid)){
			Alert.show(commonFunction.getMessages('studentFatherNameMandatory'),commonFunction.getMessages('error'),0,null,null,errorIcon);
		}
		
		else{
			
			////////////remove full empty rows////////////////
			var rowIndices:String=getEmptyRowIndex(mainGrid);
			var rowIndicesArr:Array=StringUtil.trim(rowIndices).split(',');
			var rg:Array=rowIndicesArr.reverse();
			for(var i:int=1;i<rg.length;i++)
			{
				tasks.removeItemAt(Number(rg[i]));
			}			
			mainGrid.dataProvider=null;
			mainGrid.dataProvider=tasks;
			////////////////////////////////////////////////////
			
			//checking duplicate once again at the time of submit
			if(checkDuplicateStudent(mainGrid)){
			
				//grid data in array[row][col] format
				gridData=DataGridDataExporter.exportGridData(mainGrid);
				fullStRow="";
				for(var row:int=0;row<gridData.length;row++){
					var singleRow:String="";
					for(var col:int=0;col<gridData[row].length;col++){
						//Making all empty rows NA
						if(gridData[row][col].toString().length==0){
							gridData[row][col]="NA"	;
						}
						if(col==4 && gridData[row][col].toString()!="NA")
						{	var dd:String=String(gridData[row][col]).substr(0,2);
							var mm:String=String(gridData[row][col]).substr(2,2);
							var yy:String=String(gridData[row][col]).substr(4,4);
							gridData[row][col]=yy+"-"+mm+"-"+dd;
						}
						if(col==10)
						{	
							//encoding hindi name
							gridData[row][col]=encodeURI(gridData[row][col]);
						}
						singleRow+=gridData[row][col]+",";
	
	
					}
					var arr:Array=singleRow.split(',');
					fullStRow+=singleRow+",";
				}
				
				params['StudentData']=fullStRow;
				setTempData.send(params);
				
			}
			else{
				Alert.show(commonFunction.getMessages('duplicateEntry'),commonFunction.getMessages('error'),0,null,null,errorIcon);
			}//else of checkduplicate ends
		}//else of halffill ends
}


public function saveSuccess(event:ResultEvent):void{
	getStudentListMethod();
}


// for making row protected
private function allowForEdit(event:DataGridEvent) : void
{
            if( event.dataField != "RollNumberGroupCode" ) {
                var item:Object = ((event.currentTarget as DataGrid).dataProvider as ArrayCollection)[event.rowIndex];
                if( String(item.EnrollmentNumber).length>0 ) {
                    event.preventDefault();
                }
             }
}




//grid item edit ends
private function editEnd(event:DataGridEvent):void{
	
	// Get the cell editor and cast it to TextInput.
    var myEditor:TextInput = TextInput(event.currentTarget.itemEditorInstance);
                
    // Get the new value from the editor.
    var newVal:String = myEditor.text;
    
    var studentname:String=StringUtil.trim(String(tasks[event.rowIndex].StudentName));
    
                
				if(event.columnIndex==1 && newVal.length>0 && newVal.length<6)
                {  
                   event.preventDefault();
                   myEditor.errorString=commonFunction.getMessages('validEnrollNo');
				}
                else if(event.columnIndex==1 && newVal.length>=6){
                	var param:Object={};
                	param['enrollmentNumber']=newVal;
                	param['time']=new Date();
                	checkEnrollmentInfo.send(param);
				}
				else if(event.columnIndex==3 && studentname.length>0 && newVal.length==0 ){
                   event.preventDefault();
                   myEditor.errorString=commonFunction.getMessages('studentFatherNameMandatory');
                }

                //for duplicacy//////////////////////////////////
                else if(event.columnIndex==3 && studentname.length>0 && newVal.length>0 )
                {
                	var bool:Boolean=false;
					var studentNameArray:Array = new Array();
					studentNameArray.push(studentname+" "+newVal);  
					for each(var obj:Object in mainGrid.dataProvider){
						var uniqueName:String=StringUtil.trim(String(obj.StudentName))+" "+StringUtil.trim(String(obj.FatherName));
						if(uniqueName.length>1 && StringUtil.trim(String(obj.FatherName)).length>0)
						{	
							studentNameArray.push(uniqueName);
						}
					}
											
					for(var i:int=0; i<studentNameArray.length;i++){
						for(var j:int=i+1;j<studentNameArray.length;j++){
							if(studentNameArray[i]==studentNameArray[j]){
								bool=true;
								break;
							}
						}
					}
					if(bool){
                   		event.preventDefault();
                   		myEditor.errorString=commonFunction.getMessages('duplicateEntry');
                   	}
                }
                /////////////////////////////////////////////////////////////
                
                
                else if(event.columnIndex==4 && newVal.length>0 && newVal.length!=8){
                   event.preventDefault();
                   myEditor.errorString=commonFunction.getMessages('validFormatDate');
                }
                else if(event.columnIndex==4 && newVal.length==8 && validateCorrectDate(newVal)){
 					event.preventDefault();
        			myEditor.errorString=commonFunction.getMessages('validDate');
                }
				else if(event.columnIndex==6 && newVal.length>0){
					var myRegEx:RegExp = /^[a-z][\w.-]+@\w[\w.-]+\.[\w.-]*[a-z][a-z]$/i; 
					var myResult:Object = myRegEx.exec(newVal); 
					     if (myResult == null) { 
					        event.preventDefault();
                   			myEditor.errorString=commonFunction.getMessages('validEmailId');
					     }  
				}
}




/**check date entered is correct or not
 * @author Ashish Mohan
 * @param String of ddmmyyyy format
 * @return true if date is incorrect
 * */
private function validateCorrectDate(enteredDate:String):Boolean{
	var dd:Number=Number(enteredDate.substr(0,2));
	var mm:Number=Number(enteredDate.substr(2,2));
	var yy:Number=Number(enteredDate.substr(4,4));
	var incorrectDate:Boolean=false;
	if(dd>31 || mm>12 || dd==0 ||mm==0){
		incorrectDate=true;
    }
    else if(((mm==4)||(mm==6)||(mm==9)||(mm==11)) && dd>30)
	{
		incorrectDate=true;
	}
	else if(mm==2)
	{
		if(yy%4==0 && dd>29)
		{
			incorrectDate=true;
		}
		else if(dd>28 && yy%4!=0)
		{
			incorrectDate=true;
		}
	}
	else{
		incorrectDate=false;
	}
	return incorrectDate;
}




// check enrollment number result handler
public function checkEnrollmentResultHandler(event:ResultEvent):void
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

	var bool:Boolean=(event.result.personalInfo.studentId.toString()!="");
	
	if(bool)
	{	
		var studentName:String=event.result.personalInfo.studentFirstName+" ";
			event.result.personalInfo.studentMiddleName+" "+
			event.result.personalInfo.studentLastName;
			
		var fatherName:String=event.result.personalInfo.fatherFirstName+" "
			+event.result.personalInfo.fatherMiddleName+" "
			+event.result.personalInfo.fatherLastName;
			
		var studentHindiName:String=decodeURI(String(event.result.personalInfo.hindiName+""));
		
        var dob:String=event.result.personalInfo.dateOfBirth;
        var yy:String=dob.substr(0,4);
		var mm:String=dob.substr(5,2);
		var dd:String=dob.substr(8,2);
				     
		var gen:String=event.result.personalInfo.gender;
		var pEmail:String=event.result.personalInfo.primaryMail;
		var address:String=event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').address+" "+
		event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').state.toString()+" "+
		event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').city.toString()+" "+
		event.result.personalInfo.addressInfo.addresses.(addressKey=='PER').pinCode;
	
		//setting column of grid
		tasks[mainGrid.selectedIndex].StudentName=studentName;
		tasks[mainGrid.selectedIndex].FatherName=fatherName;
		tasks[mainGrid.selectedIndex].DateofBirth=dd+mm+yy;
		tasks[mainGrid.selectedIndex].Category=event.result.personalInfo.categoryName;
		tasks[mainGrid.selectedIndex].EmailId=pEmail;
		tasks[mainGrid.selectedIndex].Gender=gen;
		tasks[mainGrid.selectedIndex].Address=StringUtil.trim(address);
		tasks[mainGrid.selectedIndex].StudentHindiName=studentHindiName;
		mainGrid.dataProvider=null;
		tasks.refresh();
		mainGrid.dataProvider=tasks;

	}
	else
	{
		tasks[mainGrid.selectedIndex].EnrollmentNumber="";
		Alert.show(commonFunction.getMessages('enrollmentNoNotFound'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}

}




//On clik of submit 
public function submitData():void
{	
	Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
}


//Insert data confirmation handler
public	var newStRow:String="";
public  var oldStRow:String="";
public function insertOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		saveData();

		
		params['entityId']=entityXml.Details.(name==entityCombo.selectedLabel).id;
		params['programId']=programXml.Details.(name==programCombo.selectedLabel).id;
		params['branchId']=branchXml.Details.(name==branchCombo.selectedLabel).id;
		params['specializationId']=specializationXml.Details.(name==specilizationCombo.selectedLabel).id;
		params['time']=new Date;
			
				//grid data in array[row][col] format
				gridData=DataGridDataExporter.exportGridData(mainGrid);
				newStRow="";
				oldStRow="";
				for(var row:int=0;row<gridData.length;row++){
					var singleRow:String="";
					for(var col:int=0;col<gridData[row].length;col++){
						//Making all empty rows NA
						if(gridData[row][col].toString().length==0){
							gridData[row][col]="NA"	;
						}
						if(col==4 && gridData[row][col].toString()!="NA")
						{	var dd:String=String(gridData[row][col]).substr(0,2);
							var mm:String=String(gridData[row][col]).substr(2,2);
							var yy:String=String(gridData[row][col]).substr(4,4);
							gridData[row][col]=yy+"-"+mm+"-"+dd;
						}
						if(col==10)
						{	
							//encoding hindi name
							gridData[row][col]=encodeURI(gridData[row][col]);
						}
						singleRow+=gridData[row][col]+",";
	
	
					}
					var arr:Array=singleRow.split(',');
					if(arr[1]=="NA"){
						newStRow+=singleRow+",";
					}
					else{
						oldStRow+=singleRow+",";
					}
				}
				
				params['newStudentData']=newStRow;
				setEnrollmentData.send(params);
				Mask.show(commonFunction.getMessages('pleaseWait'));
	}//else of event yes ends
}




//Set data success handler
public function setEnrollDataSuccess(event:ResultEvent):void
{
	Mask.close();
	newStList=event.result.student;
	transferInPrestaging.send([new Date]);
}


public function transferInPrestagingResult(event:ResultEvent):void{
		entityCombo.selectedIndex=-1;
		programCombo.selectedIndex=-1;
		branchCombo.selectedIndex=-1;
		specilizationCombo.selectedIndex=-1;
		programCombo.enabled=false;
		branchCombo.enabled=false;
		specilizationCombo.enabled=false;
	    submitButton.enabled=false;
	    tasks=new ArrayCollection;
	    studentCanvas.visible=false;
	    this.parentDocument.loaderCanvas.verticalScrollPosition=0;
	    
	    ////////param also contain entity,program,branch,spec details////////
		params['time']=new Date;
		params['oldStudentData']=oldStRow;
	    setPrestagingDataForExisting.send(params);
}


//Set data success handler
public function setPrestagingDataForOldSuccess(event:ResultEvent):void{	
	
	Mask.close();
	oldStList=event.result.student;
	var total:int=oldStList.length()+newStList.length();
	entityCombo.selectedIndex=-1;
	programCombo.selectedIndex=-1;
	branchCombo.selectedIndex=-1;
	specilizationCombo.selectedIndex=-1;
	programCombo.enabled=false;
	branchCombo.enabled=false;
	specilizationCombo.enabled=false;
    submitButton.enabled=false;
    this.parentDocument.loaderCanvas.verticalScrollPosition=0;
    headerCanvas.enabled=true;
	

	if(total>0){
		var resultArr:ArrayCollection=new ArrayCollection;
		for each(var og:Object in newStList){
			resultArr.addItem({studentId:og.studentId,regNo:og.regNo,name:og.name});
		}
		for each(var ob:Object in oldStList){
			resultArr.addItem({studentId:ob.studentId,regNo:ob.regNo,name:ob.name});
		}

		var successList:successPopUp=successPopUp(PopUpManager.createPopUp(this,successPopUp,true));
		successList.dataArray=resultArr;
		successList.title=total+" "+commonFunction.getConstants('newStudentsSendForRegistration');
		PopUpManager.centerPopUp(successList);
	}
	else{
		Alert.show(commonFunction.getMessages('errorInsert'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	}
	
}




//fault handler
public function onFailure(event:FaultEvent):void
{
	Alert.show(event.fault+"",commonFunction.getMessages('error'),0,null,null,errorIcon);  	
}



//for validating single row
public function validateFullEmptyRow(dg:DataGrid):Boolean
{
	var data:Array = new Array();
	var flag:Boolean=false;
	var columns:Array = dg.columns;
	var columnCount:int = columns.length;
	var column:DataGridColumn;
	var dataProvider:Object = dg.dataProvider;
	var rowCount:int = dataProvider.length;
	var dp:Object = null;
	var cursor:IViewCursor = dataProvider.createCursor();
			
	//loop through rows
	while (!cursor.afterLast)
	{
		cursor.moveNext();
	}	
	cursor.movePrevious();
	var obj:Object = null;
	obj = cursor.current;
				
	//loop through all columns for the row
	for(var k:int = 0; k < columnCount; k++)
	{
		column = columns[k];
		data[k]= column.itemToLabel(obj);
	}
			
	// third or fourth col cannot be empty
	if((data[2]=="" || data[3]=="") && !(data[2]=="" && data[3]==""))
	{
		flag=true;
	}
	else{
		flag=false;
	}	
		
	//set references to null:
	dataProvider = null;
	columns = null;
	column = null;
		
	return flag;
}
		
		

// return index of fully empty Row
public static function getEmptyRowIndex(dg:DataGrid, csvSeparator:String="\t", lineSeparator:String="\n"):String
{
			var data:String = "";
			var header:String = "";
			var headerGenerated:Boolean = false;
			var columns:Array = dg.columns;
			var columnCount:int = columns.length;
			var column:DataGridColumn;
			var dataProvider:Object = dg.dataProvider;
			var rowCount:int = dataProvider.length;
			var dp:Object = null;
			var courseData:Array=new Array();
			var cursor:IViewCursor = dataProvider.createCursor();
			var row:int = 0;
			var rowToBeDeleted:String="";
			//loop through rows
			while (!cursor.afterLast)
			{
				var obj:Object = null;
				obj = cursor.current;
				courseData[row] = new Array();

				//loop through all columns for the row
				for(var col:int = 0; col < columnCount; col++)
				{
					column = columns[col];
					courseData[row][col]=column.itemToLabel(obj);
				}
				headerGenerated = true;
				if(String(courseData[row][2]).length==0 && String(courseData[row][3]).length==0){
					rowToBeDeleted+=row+',';
				}
				row++;
				cursor.moveNext ();
			}//end of while loop
			
			//set references to null:
			dataProvider = null;
			columns = null;
			column = null;
			return rowToBeDeleted;
		}
		
		
//check full grid for half fill row		
public static function checkGridHalfFill(dg:DataGrid, csvSeparator:String="\t", lineSeparator:String="\n"):Boolean
{
			var data:String = "";
			var header:String = "";
			var headerGenerated:Boolean = false;
			var columns:Array = dg.columns;
			var columnCount:int = columns.length;
			var column:DataGridColumn;
			var dataProvider:Object = dg.dataProvider;
			var rowCount:int = dataProvider.length;
			var dp:Object = null;
			var courseData:Array=new Array();
			var cursor:IViewCursor = dataProvider.createCursor();
			var row:int = 0;
			var flag:Boolean=false;
			//loop through rows
			while (!cursor.afterLast)
			{
				var obj:Object = null;
				obj = cursor.current;
				courseData[row] = new Array();

				//loop through all columns for the row
				for(var col:int = 0; col < columnCount; col++)
				{
					column = columns[col];
					courseData[row][col]=column.itemToLabel(obj);
				}
				headerGenerated = true;
				if((String(courseData[row][2]).length==0 || String(courseData[row][3]).length==0) &&
				!(String(courseData[row][2]).length==0 && String(courseData[row][3]).length==0))
				{
					flag=true;
					break;
				}
				row++;
				cursor.moveNext ();
			}//end of while loop
			
			//set references to null:
			dataProvider = null;
			columns = null;
			column = null;
			return flag;
}

//check dulplicate entries of student
public static function checkDuplicateStudent(dg:DataGrid):Boolean{
			var bool:Boolean=true;
			var studentNameArray:Array = new Array();  
			
			for each(var obj:Object in dg.dataProvider){
				
				var uniqueName:String=StringUtil.trim(String(obj.StudentName))+" "+StringUtil.trim(String(obj.FatherName));
				if(uniqueName.length>1)
				{	
					studentNameArray.push(uniqueName);
				}
			}
			
			for(var i:int=0; i<studentNameArray.length;i++){
				for(var j:int=i+1;j<studentNameArray.length;j++){
					if(studentNameArray[i]==studentNameArray[j]){
						bool=false;
						break;
					}
				}
			}
			return bool;
}

