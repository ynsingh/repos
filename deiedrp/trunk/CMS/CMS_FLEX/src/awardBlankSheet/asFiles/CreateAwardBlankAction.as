/**
 * @(#) CreateAwardBlankAction.as
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

import awardBlankSheet.CustomLabel;
import awardBlankSheet.Customcombobox;
import awardBlankSheet.GRRenderer;
//import awardBlankSheet.LabelCustomEvent;
import awardBlankSheet.marksCorrection;

import common.Mask;
import common.commonFunction;

import coursegradelimit.ExternalGradeLimit;
import coursegradelimit.InternalGradeLimit;

import flash.net.FileReference;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.DateField;
import mx.controls.Label;
import mx.controls.advancedDataGridClasses.AdvancedDataGridColumn;
import mx.controls.advancedDataGridClasses.AdvancedDataGridColumnGroup;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.core.ClassFactory;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.utils.ObjectUtil;
//[Embed(source="/images/error.png")]private var errorIcon:Class;
//[Embed(source="/images/success.png")]private var successIcon:Class;
//[Embed(source="/images/reset.png")]private var resetIcon:Class;
//[Embed(source="/images/question.png")]private var questionIcon:Class;

   
	public var userId:String;
	public var _InternalGradeLimit:InternalGradeLimit;
	public var _marksCorrection:marksCorrection;
	public var _ExternalGradeLimit:ExternalGradeLimit;
	public var displayType:String;//="I";
	public var buttonPressed:String=null;
	public var employeeCode:String=null;
	public var programCourseKey:String=null;
	public var currentApprovalOrder:String;
	[Bindable]public var gradelimitdetail:Boolean  ; 
	[Bindable]public var courseapprstatus:Boolean  ; 
	
	[Bindable]public var gradelimit:String;
	
	[Bindable] private var entityTypeXml:XML;
	[Bindable] private var entityXml:XML;
	[Bindable] private var programXml:XML;
	[Bindable] private var branchXml:XML;
	[Bindable] private var specXml:XML;
	[Bindable] private var semXml:XML;
	[Bindable] private var courseXml:XML;
	[Bindable] private var componentXml:XML;
	[Bindable] private var studentXml:XML;
	[Bindable] private var marksXml:XML;
	[Bindable] private var ruleXml:XML;
	[Bindable] private var employeeXml:XML;
	[Bindable] private var keyXml:XML;
	public var gradeXML:XML
	private var componentAC:ArrayCollection=new ArrayCollection();
	private var studentListAC:ArrayCollection=new ArrayCollection();  
	private var studentMarksListAC:ArrayCollection=new ArrayCollection(); 
	
	[Bindable]public var param:Object={};
	public var employeeCourseArrCol:ArrayCollection;
	[Bindable] public var urlPrefix:String;
	
	[Bindable]private var columnNameList:Array; 
	[Bindable]private var columList:Array;
	[Bindable]private var columnNameListfordownload:Array; 
	[Bindable]private var columListfordownload:Array;
	[Bindable]private var dataProviderList:ArrayCollection;
	[Bindable]private var dataProviderListfordownload:ArrayCollection;
	[Bindable]private var selectedData:String;
	[Bindable]private var fileRef:FileReference;
	[Bindable]private var CTGroupName:String;
	[Bindable]private var gradelimitarraycoll :ArrayCollection = new ArrayCollection ;
	[Bindable]private var courseapprstatusarraycoll :ArrayCollection = new ArrayCollection ;
	
	[Bindable] public var abc:ArrayCollection ;
	[Bindable] public var abcbk:ArrayCollection= new ArrayCollection ;
	[Bindable] public var  markspublish:ArrayCollection = new ArrayCollection ;
	//public var resultarray:ArrayCollection;
	
	public var cid:String="";
	
	public var s:String ;
	

	
	private function moduleCreationCompleteHandler():void{
		
		urlPrefix=commonFunction.getConstants('url')+"/awardsheet/";
		var param:Object = new Object();
//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
//		param["time"]=new Date();
		param['displayType']=displayType;
		Mask.show(commonFunction.getMessages('pleaseWait')); 
		employeeCourseHttpService.send(param);
	}

	private function employeeCourseHttpServiceResultHandler(event:ResultEvent):void{
		var employeeCourse:XML = event.result as XML;
//		Alert.show(employeeCourse);
		if (displayType=="I"){
			courselabel.text = "Select Course to enter internal Marks " ;
			
		} else if (displayType=="E"){
			courselabel.text = "Select Course to enter External Marks " ;
		}else{
			courselabel.text = "Select Course to enter Remedial " ;
		}
		
		
		if(employeeCourse.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}
		
		var param:Object =new Object();
	 	param["time"]=new Date();
		gradeHttpService.send(param);
		
		if(String(employeeCourse+"").length==0){
			httpEmployeeCode.send(param);
		}
		else{
			employeeCourseArrCol=new ArrayCollection();
			for each(var obj:Object in employeeCourse.root){
				employeeCourseArrCol.addItem({select:false,entityId:obj.entityId, programId:obj.programId, programName:obj.programName, 
				branchId:obj.branchId, branchName:obj.branchName, specializationId:obj.specializationId, 
				specializationName:obj.specializationName, semesterCode:obj.semesterCode, semesterName:obj.semesterName, 
				semesterStartDate:obj.semesterStartDate, semesterEndDate:obj.semesterEndDate, courseCode:obj.courseCode, 
				courseName:obj.courseName, programCourseKey:obj.programCourseKey, resultSystem:obj.resultSystem, 
				employeeCode:obj.employeeCode, entityType:obj.entityType, entityName:obj.entityName, 
				startDate:obj.startDate, endDate:obj.endDate, employeeName:obj.employeeName, gradelimit:obj.gradelimit});
			}
			
			courseListGrid.dataProvider=null;
			courseListGrid.dataProvider=employeeCourseArrCol;
			employeeCode=employeeCourseArrCol.getItemAt(0).employeeCode;
			
			Mask.close();
			var params:Object=new Object();
			params["employeeCode"]=employeeCode;
			params["displayType"]=displayType;
			httpPendingRequests.send(params);
			// httpApprovedRequests & httpRejectedRequests service calling the same 
			// serverside method with different status 
			params["status"]="APR";
			httpApprovedRequests.send(params);  
			params["status"]="REJ";
			httpRejectedRequests.send(params);
		}
	}
	

	/** 
	 * This function fires on success of fetching employee code 
	 * it send requests for getting list of pending and approved requests
	 */	    	
	private function resultHandlerEmployeeCode(event:ResultEvent):void{
		employeeXml=event.result as XML;
		
		if(employeeXml.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}
		
		employeeCode=employeeXml.root.code;
		var params:Object=new Object();
	 	params["userId"]=userId;
	    params["employeeCode"]=employeeCode;
		params["displayType"]=displayType;
		httpPendingRequests.send(params);
		httpApprovedRequests.send(params);
		httpRejectedRequests.send(params);
	 }
 
	/**
	 * function for getting evaluation components
	 */
	public function getEvaluationComponents():void{
		var params:Object=new Object();
		params["programId"]=programId;
		params["courseCode"]=courseCode;
		params["displayType"]=displayType;
		httpComponentList.send(params);
			
	
	}

	/** 
	 * This function success of fetching evaluation components
	 * It sends requests for fetching studentList,programCourseKey 
	 */	    	
	private function resultHandlerComponent(event:ResultEvent):void{
			componentXml=event.result as XML;
		   	
		   	if(componentXml.sessionConfirm == true){
	    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
	    		this.parentDocument.vStack.selectedIndex=0;
				this.parentDocument.loaderCanvas.removeAllChildren();
    		}
		   	
		   	componentAC.removeAll();
			var flag:Boolean=true;
			
		   	for each (var object:Object in componentXml.component){
		   		componentAC.addItem({evaluationId:object.evaluationId,group:object.group,
		   		rule:object.rule,idType:object.idType,displayType:object.displayType,maximumMarks:object.maximumMarks});
		   	}
		   	if(componentAC.length==0){
		   		Alert.show(commonFunction.getMessages('componentMissing'),"Info",null,null,null,infoIcon);
				awardSheetCanvas.visible=false;
				Mask.close();
		   	}
		   	else{				
				param["entityId"]=entityId;
				param["programId"]=programId;
				param["branchCode"]=branchId;
				param["specCode"]=specializationId;
				param["semesterCode"]=semesterId;
				param["courseCode"]=courseCode;
				param["displayType"]=displayType;
				param["programCourseKey"]=programCourseKey;
				
				if(buttonPressed=='PL'){
					param["startDate"]=sessionStartDate;
					param["endDate"]=sessionEndDate;
				}else{
					param["startDate"]=semesterStartDate;
					param["endDate"]=semesterEndDate;
				}
				param["buttonPressed"]=buttonPressed;
				httpStudentList.send(param);
		}
	}

	/** 
	 * This function fires on success of getting student list and call function for getting student marks
	 */	    	
	private function resultHandlerStudent(event:ResultEvent):void{
		studentXml=event.result as XML;
		if(studentXml.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
	  	getStudentMarks();
	}

	/** 
	 * This function send request for fetching student saved marks
	 */	    	
	private function getStudentMarks():void{
		
	
			
		httpStudentMarksList.send(param);
			
		
		
	}

	/** 
	 * This function fires on success of getting student marks
	 * and call appropriate function for creating grid with data
	 */	    	
	private function resultHandlerStudentMarks(event:ResultEvent):void{
 		marksXml=event.result as XML;
 			

 		var resultObj:Object=new Object();
 		submitForApprovalButton.visible=true ;

		dataProviderList=new ArrayCollection();
		dataProviderListfordownload =new ArrayCollection();
		if(marksXml.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
		
		studentMarksListAC.removeAll();
		markspublish.removeAll();
		var abc:ArrayCollection=new ArrayCollection();
		
         abc.removeAll();
         abcbk.removeAll();
   		for each (var obj1:Object in studentXml.student){
   			var resultObj:Object=new Object();
   			
   			
   			var _s1:String=obj1.rollNumber;
   		//	resultObj["rollNumber"]=obj1.rollNumber;
   		    resultObj["rollNumber"]=_s1;
                		
   			resultObj["studentName"]=obj1.studentName;
   			
   			
   			//Alert.show("marks"+marksXml);
   			resultObj["totalInternal"]= "0";
			resultObj["totalExternal"]="0";
			resultObj["totalMarks"]="0";
			resultObj["grade"]="";
			resultObj["Correction"]="";

			for each(var obj3:Object in componentAC){
			var s4: String = obj3.evaluationId;
			resultObj[obj3.evaluationId]="Z";
			}


   			var cVal:String="";
   			var cVal1:String="";
   			var cVal2:String="";// for down load  arush 
   			var grade:String="";

   			var studentexist:Boolean = false;
   			var publishdate:Object=new Object();


   			for each(var obj2:Object in marksXml.marks){

   				
   				   	var _dateinsert:String =""; 
   					var _datemodified:String = "";
   					var _publishdate:String="";
   					var _dateins:Date = new Date();
   					var _datemod:Date=new Date() ;
   					var res:Number;
   				
   				publishdate = new Object();

   				if(obj1.rollNumber==obj2.rollNumber){
   					studentexist=true;
   					var cValue:String="";
   					
   					if(obj2.attendence == "ABS" ){
   						cValue="A" ;
   					}else{
   				   				   					
   					if(obj2.marks!="" ){
   						cValue=obj2.marks;
   					}else if(obj2.grades!=""){
   						cValue=obj2.grades;
   					}else{
   						cValue=obj2.passfail;
   					}
					if(cValue=="" || cValue==null){
						cVal1="0";
//						cVal="0";
						cValue = "Z";
					}
					else{
						cVal1=cValue;
					}
					}
   				
					//Alert.show("cValue is name is "+obj1.studentName+" :  "+cValue.toString()+" : "+obj2.evaluationId);
					
   					resultObj[obj2.evaluationId]=cValue;

   	   					   					
   					if (cValue!="Z"){
   					_dateinsert = obj2.inserttime;
   					_datemodified = obj2.modificationtime;
   				   						
   									
				   					if (_dateinsert == ""){
				   					_publishdate = _dateinsert ;
				   						
				   					}else if (_datemodified !=""){
									   					_datemod=DateField.stringToDate(_datemodified,"YYYY-MM-DD");
									   					_dateins=DateField.stringToDate(_dateinsert,"YYYY-MM-DD");	
									   					res=compare(_datemod,_dateins);
				   										if (res<0){
				   										_publishdate = _dateinsert;
				   										}else {
				   										_publishdate = _datemodified;
				   										}
				  		
				   										}
				   					else{
				   						_publishdate = _dateinsert ;
				   					}
				   					
   					}
   					publishdate[obj2.evaluationId]=_publishdate;
   					setpublishdate(markspublish,obj2.evaluationId,publishdate);



   					cVal=cVal1+"/"+obj2.evaluationId+"-"+cVal;
   					cVal2=cVal1+"/"+obj2.evaluationId+"#"+cVal2; // use # as sep. - is causing issue when student grades  are in negative eg. A-,B- arush
//   					Alert.show("cVal :" + cVal + "cVal1 :" + cVal1 + "cVal2 :" + cVal2 + "cValue :" + cValue);
				if (obj2.requestedmarks==null || obj2.requestedmarks==""){
				
				}else{
					resultObj["Correction"] += obj2.requestedmarks+"|"+obj2.evaluationId+"|"+ obj2.requesterremarks+"|"+ obj2.issuestatus+"|";
					
                  
					
				}
				

   					
   					if (obj2.totalExternal==null || obj2.totalExternal==""){
   						
   						resultObj["totalExternal"]="0";
   					}else{
   						resultObj["totalExternal"]=obj2.totalExternal;
   					}
   					
     					if (obj2.totalInternal==null || obj2.totalInternal==""){
   						
   						resultObj["totalInternal"]="0";
   					}else{
   						resultObj["totalInternal"]=obj2.totalInternal;
   					}
   					
     					if (obj2.totalMarks==null || obj2.totalMarks==""){
   						
   						resultObj["totalMarks"]="0";
   					}else{
   						resultObj["totalMarks"]=obj2.totalMarks;
   					} 					 					
   					
   					
//   					resultObj["totalExternal"]=obj2.totalExternal;
//   					resultObj["totalInternal"]=obj2.totalInternal;
//   					resultObj["totalMarks"]=obj2.totalMarks;
   					if(displayType.toUpperCase()=="I"){ 
   						resultObj["grade"]=obj2.internalGrade;	
						grade=obj2.internalGrade;
						if(obj2.internalGrade=="X"){
   						submitForApprovalButton.visible=false ;
   						}
						
   					}
   					else if(displayType.toUpperCase()=="E"){
   						resultObj["grade"]=obj2.externalGrade;
						//grade=obj2.internalGrade;  Arush
						grade = obj2.externalGrade ;
						if (obj2.externalGrade=="X"){
   						submitForApprovalButton.visible=false ;
   						}
   					}
   					else{
//   						resultObj["grade"]=obj2.internalGrade;
//						grade=obj2.internalGrade;
// Changed by Dheeraj for getting grades from student_marks table 
						resultObj["grade"]=obj2.grades;
						grade=obj2.grades;
						if (obj2.grades=="X"){
   						submitForApprovalButton.visible=false ;
   					}
						
   					}
   					


 //  				}
   			}

   			}
   			for  (var str:String in resultObj){
   				if(resultObj[str] =="Z"){
   					_publishdate = "";
   					publishdate[str]=_publishdate;
   					setpublishdate(markspublish,str,publishdate);
   				}
   			}
   			

   			abc.addItem(resultObj);
   			
   	      
   		  
			dataProviderList.addItem({data:obj1.rollNumber+","+obj1.studentName+","+cVal+","+grade});
			dataProviderListfordownload.addItem({data:obj1.rollNumber+","+obj1.studentName+","+cVal2+grade+"/Grade"});//arush if grades need to be downloaded
	//	dataProviderListfordownload.addItem({data:obj1.rollNumber+","+obj1.studentName+","+cVal});
		
   		}
        abcbk=ObjectUtil.copy(abc) as ArrayCollection ;
      
        //abcbk =ObjectUtil.copy(abc.source) as ArrayCollection;
      
		createGrid(abc);
	}
 
  /** This function fires after fetching the records for grid 
   * then according to requirement genrate the grid daynamically
   */
	 private function createGrid(dataArray:ArrayCollection):void {
	 	
	 	columnNameList=new Array();
	 	columnNameListfordownload = new Array();
	 	columList=new Array();
	 	
	 	//Customtextinput.maximumValue=new Array();
	 	var colmns: Array = new Array;
		var rollNoColmn:AdvancedDataGridColumn=new AdvancedDataGridColumn;
		rollNoColmn.headerText=commonFunction.getConstants('rollNo');
		rollNoColmn.width=100;
		rollNoColmn.editable=false;
		rollNoColmn.dataField='rollNumber';
		rollNoColmn.resizable=false;
		
		
		var nameColmn:AdvancedDataGridColumn=new AdvancedDataGridColumn;
		nameColmn.headerText=commonFunction.getConstants('studentName');
		nameColmn.width=150;
		nameColmn.editable=false;
		nameColmn.dataField='studentName';
		nameColmn.resizable=false;
		
		colmns.push(rollNoColmn);
		colmns.push(nameColmn);
		
		columnNameList.push(rollNoColmn);	
		columnNameList.push(nameColmn);  
		
		columnNameListfordownload.push(rollNoColmn);//  Arush	 for excel download
		columnNameListfordownload.push(nameColmn);  // Arush for excel download
		
			// new column  for correction   ....................
		var request:AdvancedDataGridColumn = new AdvancedDataGridColumn ;
	    request.headerText="request";
	    request.width=60;
	    request.dataField="Correction";
	    request.editable=false;
		request.resizable=false;
				
		
		    
		
		 
		 
	    colmns.push(request);
		request.visible = false;
	    
		
		// new column for correction
		
		var temp:Array=new Array();
		var w:int=320;
		CTGroupName="";
		var protect:Boolean = false;
			//	if (downloadButton.visible==false && uploadButton.visible==false){   as requested by examination department
			if (mkcorrectionButton.visible==false) {
			// if (downloadButton.visible==false){			
		protect = false ;
		
		}else{
			
			protect = true ;// Arush
			
		}
		for each(var obj1:Object in componentAC)
		{
			var groupName:String=obj1.group;
			if(temp.indexOf(groupName)<0)
			{
				temp.push(groupName);
				var colmnGroup:AdvancedDataGridColumnGroup=new AdvancedDataGridColumnGroup;
				colmnGroup.headerText=groupName;
				CTGroupName=colmnGroup.headerText.toString()+"-"+CTGroupName;
				for each(var obj2:Object in componentAC){
					if(obj2.group==groupName){
						var colmn:AdvancedDataGridColumn=new AdvancedDataGridColumn;
						colmn.headerText=obj2.evaluationId+"\n"+obj2.maximumMarks;
		                colmn.sortable=false;
		                colmn.dataField=obj2.evaluationId;

	            //    var color:markscolor = new markscolor; 
	            //var colfactory:IFactory = markscolor;
	         
	            var mkcolor:ClassFactory = new ClassFactory(CustomLabel);
//	          //  colmn.labelFunction=mylabel1;
//	               
               mkcolor.properties={myfield:colmn.dataField};
//	           
	           			colmn.itemRenderer=mkcolor	 ;
	           			
	           		
		                colmn.dataTipField=obj2.evaluationId;
		                colmn.showDataTips=true;
		                
		                
//		                if(protect == true ){
//		                	if(obj2.evaluationId=component.text){
//		                		colmn.editable = true;
//		                	}else{
//		                		colmn.editable = false;
//		                	}
//		                }else{
//		                	colmn.editable = protect;
//		                }
		                
						colmn.editable = protect;
						columnNameList.push(colmn);	
						columnNameListfordownload.push(colmn);//  Arush	 for excel download
		                columList.push(colmn);
						
		                switch(obj2.idType+"")
		                {
		                	case 'GR':
		                	    colmn.width=51;
		                        colmn.editorDataField='grValue';					                        
		                        colmn.itemEditor=new ClassFactory(GRRenderer);
		                        break;
		                	case 'MK':
							    colmn.width=65;
							    colmn.editorDataField="mkValue";
							    //GRRenderer.grid=awardSheetGrid;
							   // Customtextinput.maximumValue.push();
							    var mkFactory:ClassFactory=new ClassFactory(Customcombobox);
							    mkFactory.properties={datafield:colmn.dataField,maximumValue:Number(obj2.maximumMarks),datatooltip:"Arush"};							    
							    colmn.itemEditor=mkFactory;
							    
							  

							    colmnGroup.children.push(colmn);
								w+=int(colmn.width);
								if(displayType=='E'){
							    	var wColmn:AdvancedDataGridColumn=new AdvancedDataGridColumn;
									wColmn.width=-1;		
									wColmn.sortable=false;
									wColmn.editable=false;
									wColmn.headerText=obj2.evaluationId+"(In Words)";
							        wColmn.width=120;
							        wColmn.dataField=obj2.evaluationId+"iw";
							        wColmn.labelFunction=getInWords;
							        colmnGroup.children.push(wColmn);
									w+=int(wColmn.width);
							    }
								break;
		                   } //end of switch
		             }// end of if
		    	}// end of loop		    	
		    	colmns.push(colmnGroup);
	    	}
    	}
//		if(displayType=='I'){ // for total marks column
		
		
			var totalMarksColmn:AdvancedDataGridColumn=new AdvancedDataGridColumn;
			totalMarksColmn.headerText=commonFunction.getConstants('totalMarks');
			totalMarksColmn.width=65;
			totalMarksColmn.editable=false;
			totalMarksColmn.labelFunction=getTotal;
			 if(displayType=='I'){
		    	totalMarksColmn.dataField='totalInternal';
		    	}else if(displayType=='E'){
		    	totalMarksColmn.dataField='totalExternal';
		    }else if(displayType=='R'){
		    	totalMarksColmn.dataField='totalMarks';
		    }	
			
			
			colmns.push(totalMarksColmn);
			w=w+100;
//		}
		
	    var GradeColmn:AdvancedDataGridColumn = new AdvancedDataGridColumn ;
	    GradeColmn.headerText=commonFunction.getConstants('grade');
	    CTGroupName=GradeColmn.headerText.toString()+"-"+CTGroupName; // arush
	    
		GradeColmn.headerText=commonFunction.getConstants('grade')+"\n"+'AtoZ'  // arush for download GRADE;
		
		
		
//		if (downloadButton.visible==false && uploadButton.visible==false){
//			 			
//		GradeColmn.editable=false ;// Arush	
//		}else{
//			
//			GradeColmn.editable=true ;// Arush
//		}
		
		
		if (gradelimit=='1'){
			GradeColmn.editable=false ;// Arush	
		}else{
			GradeColmn.editable=protect ;
		}
	
		
		GradeColmn.width=60;
	    GradeColmn.editorDataField='grValue';	
	    GradeColmn.dataField="grade";	    
	    GRRenderer.gradeXMLData=gradeXML;
	    GRRenderer.grid=awardSheetGrid;
	    GradeColmn.itemEditor=new ClassFactory(GRRenderer);
		colmns.push(GradeColmn);
		columnNameList.push(GradeColmn);
		columnNameListfordownload.push(GradeColmn); // arush for down load grade
		 columList.push(GradeColmn);// arush for down load grade
	
		w=w+100;
			awardSheetGrid.horizontalScrollPolicy="on";
		awardSheetGrid.width=100;
		
	
		
			
		awardSheetGrid.groupedColumns = colmns;
		awardSheetGrid.width=w;
		awardSheetGrid.dataProvider=dataArray;
		//dataArray.refresh();
		
	
				
		awardSheetCanvas.visible=true;
		if(w<400){
			awardSheetCanvas.width=420;
		}
		else{
			awardSheetCanvas.width=w+20;
		}
		if(w>=1300){
			this.width=w+100;
		}
	    
		
    
	    		 
		awardSheetGrid.editable="true";
	
		////////////////////////////////////////////////////////////////////////

	    createlabel(); 

	/////////////////////////////////////////////////////////////////////////////////////	
		var params:Object=new Object();
		params["courseCode"]=courseCode;
		params["startDate"]=semesterStartDate;
		params["endDate"]=semesterEndDate;
	    params["programCourseKey"]=programCourseKey;
	    params["entityId"]=entityId;
	    params["employeeCode"]=employeeCode;
		params["displayType"]=displayType; 
	    httpStatus.send(params);
	}
	
		private function onClickok():void{
		//removeChild(awardSheetGrid);
		//createGrid(abc);
//		abcbk = abc ;
//		awardSheetCanvas.removeAllChildren();
//		abc = abcbk ;
//		
//		createGrid(abc);	
	}
	
	/*
	 * Function for saving the marks/grades of students
	 */	
	public function saveMarks():void{
		
		
	
		var gridData:ArrayCollection=commonFunction.getAdvancedDataGridRowData(awardSheetGrid);
		if(gridData.length>0){
			var s:String="";
			var totmrk :int;
			var st:String="";
			
			var nocomponent_exist:Boolean =true;
			for each(var o:Object in gridData){
				// If grade limit to be used  
				
				
				if (gradelimit=='1' ){
					if(displayType.toUpperCase()=="I"){
		    						totmrk=o["totalInternal"];
		    						}
		    						else if(displayType.toUpperCase()=="E"){
		    							totmrk=o["totalExternal"];
		    						} else{
		    							totmrk=o["totalMarks"];
		    						}
		    						
		    						var wgrade:String = calculategrade(totmrk);
		    						if (wgrade==null && gradelimitButton.visible==true){
		    						Alert.show(commonFunction.getMessages('gradeLimitDoesNotExist')+ totmrk,commonFunction.getMessages('info'),4,null,null,infoIcon);	
		    						return;
		    						}else if(wgrade!=null  && gradelimitButton.visible==true) {
		    							
		    						}else{
		    							wgrade = "X";
		    							
		    						}
				 }else {
				 	wgrade = o["grade"] ;
				 }
				
				for(var v:String in o){
					
					if (v==''){
			    		  					v='ZZZ' // dummy number
			    		  				}
	  				
			    		
			    		 				
			    		 				
//		    			if((v!="rollNumber")&&(v!="studentName")&&(v!="totalMarks")&&(v!=o[v]+"idType")&&    // commented out by Arush
//		    			(v!="externalGrade")&&(v!="internalGrade")&&(v!="totalInternal")&&(v!="totalExternal")&&(v!="grade")){    // commented out by Arush
		    			if((v!="rollNumber")&&(v!="studentName")&&(v!="totalMarks")&&(v!=o[v]+"idType")&&
			    				(v!="externalGrade")&&(v!="internalGrade")&&(v!="totalInternal")&&(v!="totalExternal")&&(v!="Correction") ){
			    	
		    		if (v!="grade"){
			    						    			
			    				var idmaxmarks:Number  = new Number(componentXml.component.(evaluationId==v).maximumMarks);
			    				var mrkchg:String="N";
			    				var componentMarks:Number ;
			    			
			    				if (isANumber(o[v])){
			    					 componentMarks  = new Number(o[v]);
			    				}else{
			    					componentMarks = 0;
			    				}
			    				
			    			  	var prvmarks:String=getpreviousmarks(o["rollNumber"],v);
			    			  	//var prvmarks:String = o[v+"b"];
			    			  	if(prvmarks!=o[v]) 
			    			  	{
			    			  		mrkchg = "C";
			    			  	}else{
			    			  			mrkchg = "N";
			    			  	}	
			    				if (componentMarks > idmaxmarks){
			    				
									Alert.show(commonFunction.getMessages('EnteredmarksmorethanMaximumMark')+ " for Roll Number: "+ 
									o["rollNumber"] + " and component: " + v
									,commonFunction.getMessages('error'),4,null,null,errorIcon);
			    					return;
			    		  				}
			    		  				if (v==''){
			    		  					v='ZZZ' // dummy number
			    		  				}
		    		
		    				
		    				
		    				
		    				nocomponent_exist = false ;
		    		// If grade limit to be used   		
		    //				if (gradelimit=='1'){
		    				 				
		    				
		    			
		    				
		    		
		    			if(displayType=='I'){
		    				
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalInternal"]+"|"+wgrade+"|"+prvmarks+"|"+mrkchg+";";
						}else if(displayType=='E'){
		    			//	s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o[v]+"|"+wgrade+";";
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalExternal"]+"|"+wgrade+"|"+prvmarks+"|"+mrkchg+";";
						}else {
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalMarks"]+"|"+wgrade+"|"+prvmarks+"|"+mrkchg+";";
						}
						
							}	


                         
                         
		if (v=="grade" ){     // loop v== Grade
			    		  		var invalidgrade:Boolean =true;	
			    		  		var s1:XML;
			    		  		var s2:String;
							  	for each (s1 in  gradeXML.gradeList.grade){
							  		
							  		s2=s1;
							  		
			    		  			if (o[v] ==s2){
			    		  			invalidgrade = 	false ;
			    		  			break;
			    		  			} 
			    		 }       // end  loop v== Grade
			    		  			
			    			if (invalidgrade==true){   // invalid grade true
//			    			Alert.show(commonFunction.getMessages('invalidgrade')+ " for Roll Number: "+ 
//									o["rollNumber"] + " and Grade : " + o[v]
//									,commonFunction.getMessages('error'),4,null,null,errorIcon);
									submitForApprovalButton.visible=false ;
				//					return;	
			    			}  // end of loop invalid grade true

					
				}
			    				}
			    					}
			    					
			    					
				
					if (nocomponent_exist){   // No component is set up in course evaluation component
			    			Alert.show("No course component exists.Please set up at least one  course components"
									,commonFunction.getMessages('error'),4,null,null,errorIcon);
									return;	
			    			}  // end of loop invalid grade true
				
//	not required now					if (nocomponent_exist){  // if there is no component on course ,save the grades for a student. Arush
//				
//						
//			   				if(displayType=='I'){
//		    				
//		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalInternal"]+"|"+wgrade+"|"+mrkchg+";";
//						}else if(displayType=='E'){
//		    			//	s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o[v]+"|"+wgrade+";";
//		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalExternal"]+"|"+wgrade+"|"+mrkchg+";";
//						}else {
//		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalMarks"]+"|"+wgrade+"|"+mrkchg+";";
//						}
				
//			}	
			    			
			    				}
			    				
	 
		 	var params:Object=new Object();
			params["entityId"]=entityId;
			params["courseCode"]=courseCode;
			params["startDate"]=semesterStartDate;
			params["endDate"]=semesterEndDate;
			params["programCourseKey"]=programCourseKey;
			params["employeeCode"]=employeeCode;  
			params["displayType"]=displayType;
			params["data"]=s;
//			Alert.show(s);
			Mask.show(commonFunction.getMessages('pleaseWait'));	
			    		
			httpSaveSheet.send(params);
		}
		else{    // outer loop gridData.length>0
			Alert.show(commonFunction.getMessages('noStudentFound'),commonFunction.getMessages('info'),4,null,null,infoIcon);	
		}		
	}
		/**
	 * calculate grade if grade limit exists
	 */
	public function calculategrade(marks:int):String{
	
	var wtotalmarks:int = new int(); 
	var lcut :int = new int();
	var ucut:int = new int();
	
	wtotalmarks =marks ;
			
	for each (var obj:Object in gradelimitarraycoll ){ 
		lcut = obj.marksfrom ;
		ucut = 	obj.marksto ;
	if (wtotalmarks>=lcut && wtotalmarks <= ucut ) {
	return (obj.grades);
	}
	}
	return null ;
	}
	
	/**
	 * request handler for save button
	 */
	private function resultHandlerSave(event:ResultEvent):void{
		var result:XML=event.result as XML;
		
		if(result.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
    	}
		
		if(result.exception.exceptionstring == 'E'){
	   		Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'),4,null,null,errorIcon);
		}
		else{
	   		Alert.show(commonFunction.getMessages('savedSuccessfully') ,commonFunction.getMessages('success'),4,null,null,successIcon);
	   		getStudentMarks();

		
		//awardSheetCanvas.removeChild(lab:Label);
		createlabel();
	   	}
	   	Mask.close();
//	   	 for each(var obj:Object in courseListGrid.dataProvider as ArrayCollection){
//			obj.select=false;
//		}

		
		

		//awardSheetCanvas.removeAllChildren();
		
	   	
	 }
 
	public function getInWords(row:Object,col:AdvancedDataGridColumn):String {
		var num:String=row[col.dataField.slice(0,col.dataField.length-2)];
		if(num==null){
			return "";
		}
		else{
			return commonFunction.convertNumberToWord(int(num));
		}
	}
	
	/** 
	 * this function fires on click of button for showing pending list
	 * calls appropirate function for fecthing data and open popup
	 */	
	public function showPendingList(event:MouseEvent):void
	{
		pendingList=PendingRequest(PopUpManager.createPopUp(this,PendingRequest,true));
		pendingList.refFunc1=setButtonPressed;
		pendingList.refFunc2=setVariables;
		pendingList.approveFunction = approveRequest;
		pendingList.rejectFunction = rejectFrame;
		pendingList.dataArray=pendingAC;
		PopUpManager.centerPopUp(pendingList);
	}

	/** 
	 * this function fires on click of button for showing approved list 
	 * calls appropirate function for fecthing data and open popup
	 */	
	public function showApprovedList(event:MouseEvent):void{
		approvedList=ApprovedRequest(PopUpManager.createPopUp(this,ApprovedRequest,true));
		approvedList.refFunc1=setButtonPressed;
		approvedList.refFunc2=setVariables;
		approvedList.dataArray=approvedAC;
		PopUpManager.centerPopUp(approvedList);
	}
	
	public function showRejectedList(event:MouseEvent):void
	{
		rejectedList=RejectedRequest(PopUpManager.createPopUp(this,RejectedRequest,true));
		rejectedList.refFunc1=setButtonPressed;
		rejectedList.refFunc2=setVariables;
		rejectedList.dataArray=rejectedAC;
		PopUpManager.centerPopUp(rejectedList);
	}
	
	/** 
	 * This function fires on failure of any request to give failure alert
	 */	    	
	private function faultHandler(event:FaultEvent):void{
		Mask.close();
		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	
	private function gradeHttpServiceResultHandler(event:ResultEvent):void{
		gradeXML = event.result as XML;
		
		  	

		  	
		Mask.close();
		if(gradeXML.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
	
	private function httpServiceFaultHandler(event: FaultEvent):void{
		Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}
	
	public function furtherExecution(selEntityId:String, selProgramId:String, selProgramName:String, selBranchId:String,
		selBranchName:String, selSpecializationId:String, selSpecializationName:String, selSemesterId:String, selSemesterName:String,
		selCourseCode:String, selCourseName:String, selSemesterStartDate:String, selSemesterEndDate:String, selProgramCourseKey:String,
		selResultSystem:String, selEmployeeCode:String, selEntityType:String, selEntityName:String, selStartDate:String, selEndDate:String,
		selEmployeeName:String,selgradelimit:String):void{
		
		entityId=selEntityId;
		programId=selProgramId;
		programName=selProgramName;
		branchId= selBranchId;
		branchName=selBranchName;
		specializationId=selSpecializationId;
		specializationName=selSpecializationName;
		semesterId=selSemesterId;
		semesterName=selSemesterName;
		courseCode=selCourseCode;
		courseName = selCourseName;
		semesterStartDate=selSemesterStartDate;
		semesterEndDate=selSemesterEndDate;
		programCourseKey=selProgramCourseKey;
		resultSystem=selResultSystem;
		employeeCode=selEmployeeCode;
		entityType=selEntityType;
		entityName=selEntityName;
		sessionStartDate = selStartDate;
		sessionEndDate = selEndDate;
		employeeName = selEmployeeName;
		gradelimit = selgradelimit;
//		Alert.show("Grade Limit Flag Initial : " + gradelimit); // Arush
		selectedData=entityId+","+programCourseKey+","+courseCode+","+semesterStartDate+","+semesterEndDate+","+programId;
		var param:Object =new Object();
		var gradeobject:Object=new Object();
		
		param["courseCode"]=courseCode;
		param["entityId"]=entityId;
		param["programCourseKey"]=programCourseKey;
//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
		param["displayType"]=displayType;
		
		gradeobject["courseCode"] = courseCode;
		gradeobject["displayType"] = displayType;
		
				 	if (gradelimit=='1'){
		 		
		 			httprequestgetgradelimit.send(gradeobject) ;
			
		}else{
			httpIsNextApprovalOrderExist.send(param);
		}
	//	httpIsNextApprovalOrderExist.send(param);
	
	
											
	}
	
	
	private function getgradelimitResultHandler(event:ResultEvent):void{
		var xmlgradelimit:XML = event.result as XML;
//		Alert.show(xmlgradelimit);
		var param:Object =new Object();
		
		//Alert.show("gradelimit"+xmlgradelimit);
		gradelimitarraycoll.removeAll();
		for each(var obj:Object in xmlgradelimit.root){
		gradelimitarraycoll.addItem({coursecode:obj.courseCode,grades:obj.grades ,marksfrom:obj.marksfrom,marksto:obj.marksto});	
		}
		if (gradelimitarraycoll.length== 0){
			//Alert.show(commonFunction.getMessages('gradeLimitNotExist'),commonFunction.getMessages("info"),4,null,null,infoIcon) ;
			gradelimitdetail = false ;
			
		}
		
		gradelimitdetail = true ;
		//else{
			
		param["courseCode"]=courseCode;
		param["entityId"]=entityId;
		param["programCourseKey"]=programCourseKey;
		param["displayType"]=displayType;
		param["startDate"]=semesterStartDate;
		param["endDate"]=semesterEndDate;
		httprequestgetcourseAprStatus.send(param);
		
			
		
			//httpIsNextApprovalOrderExist.send(param);
		//}
	
		
		
	}

	
	private function getcourseAprStatusResultHandler(event:ResultEvent):void{
		var xmlcourseapprstatus:XML = event.result as XML;
		var param:Object =new Object();
		courseapprstatusarraycoll.removeAll();
		for each(var obj:Object in xmlcourseapprstatus.root){
		courseapprstatusarraycoll.addItem({coursecode:obj.courseCode,entityId:obj.entityId ,requestSender:obj.requestSender,requestGetter:obj.requestGetter
		,requestdate:obj.requestdate,completiondate:obj.completiondate,approvalOrder:obj.approvalOrder,status:obj.status,
		requestSendername:obj.requestSendername,requestgettername:obj.requestgettername,requestSenderdesignation:obj.requestSenderdesignation
		,requestGetterdesignation:obj.requestGetterdesignation,
		submitdates:obj.submitdates}
		);	
		}
		courseapprstatus = true ;
		if (courseapprstatusarraycoll.length== 0){
			//Alert.show(commonFunction.getMessages('gradeLimitNotExist'),commonFunction.getMessages("info"),4,null,null,infoIcon) ;
			courseapprstatus = false;
			
		}
		
		param["courseCode"]=courseCode;
		param["entityId"]=entityId;
		param["programCourseKey"]=programCourseKey;
//Change Done By Dheeraj For Allowing Access To Examination Dept. For Entering External And Remedial Marks
		param["displayType"]=displayType;
		httpIsNextApprovalOrderExist.send(param);
	}
	

	private function httpIsNextAppOrderExistResultHandler(event:ResultEvent):void{
		var xmlData:XML = event.result as XML;
		
		if(xmlData.result.message=="approvalOrderNotExist"){
			Alert.show(commonFunction.getMessages('setAtleastTwoAppOrder'), commonFunction.getMessages("info"),4,null,null,infoIcon);
		}
		else{
			
		
			
		var params:Object =new Object();	
		params["courseCode"]=courseCode;
		params["entityId"]=entityId;
		params["programCourseKey"]=programCourseKey;
//		params["startDate"]=semesterStartDate;
//		params["endDate"]=semesterEndDate;	
		params["displayType"]=displayType;
//		params["date"]=new Date();	
		getApprovalOrder.send(params);
//
//		
//		//if ((gradelimitdetail == true) && (gradelimit == '1')||(gradelimit == '0')){
//			
//
//		
//		getAprStatus.send(params);
		//}
		
//			if(displayType=="E"){
//				var params:Object = new Object();
//				params["courseCode"]=courseCode;
//				httpIsExtAwardAllowed.send(params);	
//			}
//			else{
//				setButtonPressed('SW');
//				setVariables();
//			}
			
		}
	}
	
	private function httpIsExtAwardAllowedResultHandler(event:ResultEvent):void{
		var xmlData:XML = event.result as XML;
		if(xmlData.result.message=="notAllowed"){
			Alert.show(commonFunction.getMessages('extAwardBlankNotAllowed'), commonFunction.getMessages("info"),4,null,null,infoIcon);
		}
		else{
			setButtonPressed('SW');
			setVariables();	
		}
	}	
	
	
	/**
 	 * Method to fire the click event of the Download template link
     * This Method ask the confirmation to download template
    **/ 	 
	private function onClickDownloadTemplate():void{
		var param:Object={};
			var columnTokens:String="";
			var columnCT:String=""
			var dataProviderTokens:String="";
		//	for each(var obj in columnNameList){ arush:  Do not  clude grade column in  excel download.
		for each(var obj in columnNameListfordownload){
				columnTokens=obj.headerText+","+columnTokens;
			}
			for each(var ob in columList){
				columnCT=ob.headerText+","+columnCT;
			}
			for(var i:int=dataProviderListfordownload.length;i>0;i--){
				dataProviderTokens=dataProviderListfordownload.getItemAt(i-1).data+"|"+dataProviderTokens;
			}
			param["dataProviderList"]=dataProviderTokens;
			param["columnNameList"]=columnTokens;
			param["CTList"]=columnCT;
			param["CTGroupName"]=CTGroupName
			param["fileName"]=courseCode+"_"+entityId+"_"+programCourseKey+"_"+semesterStartDate+"_"+semesterEndDate;//ADd...
			param["date"]=new Date();
			httpDownloadTemplate.send(param);
	}
	/**
 	 * Result handler for httpUploadTemplate service
    **/ 
	private function httpDownloadTemplateResultHandler(event:ResultEvent):void{
		try
		{
			if(event.result.sessionConfirm == true)
			{
	 			Alert.show(resourceManager.getString('Messages','sessionInactive'));
				var url:String="";
				url=commonFunction.getConstants('navigateHome');
				var urlRequest:URLRequest=new URLRequest(url);
				urlRequest.method=URLRequestMethod.POST;
				navigateToURL(urlRequest,"_self");
			}
		}
 		catch(e:Error){}

		var xmlData:XML = event.result as XML;
		var str:String=xmlData.resultdata.toString();
		var arr:Array=str.split("|");
		if(arr[0].toString()=="success"){
			navigateToURL(new URLRequest(commonFunction.getConstants('url')+"/Excel/"+courseCode+"_"+entityId+"_"+programCourseKey+"_"+semesterStartDate+"_"+semesterEndDate+".xls"),"_self");
		}
		else if(arr[0].toString()=="FileError"){
			Alert.show(commonFunction.getMessages('cantDownloadFileError'),commonFunction.getMessages('error'),null,null,null,errorIcon);
		}
		else if(arr[0].toString()=="IOError"){
			Alert.show(commonFunction.getMessages('cantDownloadIOError'),commonFunction.getMessages('error'),null,null,null,errorIcon);
		}
		else if(arr[0].toString()=="NoCT"){
			Alert.show(commonFunction.getMessages('cantDownloadNoCT'),commonFunction.getMessages('info'),null,null,null,infoIcon);
		}
		else{
			Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),null,null,null,errorIcon);
		}
	}	
	/**
     *Method to fire the click event of upload template link
    **/
	private function onClickUploadTemplate():void{
		fileRef=new FileReference();
		var textFilter:FileFilter = new FileFilter("Xml Files(*.xls,)", "*.xls;");
		var allTypes:Array = new Array(textFilter);
		fileRef.addEventListener(Event.SELECT,selectHandler);
		fileRef.addEventListener(Event.COMPLETE,onLoadComplete);
		fileRef.addEventListener(DataEvent.UPLOAD_COMPLETE_DATA,uploadHandler);
		fileRef.browse(allTypes);
		i=0;
	}
	
	
	
	private function onClickMarksCorrection():void{
	if(displayType=="I"){
  	_marksCorrection=marksCorrection(PopUpManager.createPopUp(this,marksCorrection,true));
  	_marksCorrection.semesterStartDate=semesterStartDate;
  	_marksCorrection.semesterEndDate=semesterEndDate;
  	_marksCorrection.courseCode=courseCode;
  	_marksCorrection.programCourseKey=programCourseKey;
  	_marksCorrection.entityId=entityId;
  	_marksCorrection.method=getStudentMarks ;
  	_marksCorrection.CancelButton.visible=false ;
  	
  	PopUpManager.centerPopUp( _marksCorrection);
  
  	
 
		
	}
	}
	// Arush 
	private function onClickgradelimit():void{
		
	
  if(displayType=="I"){
  	_InternalGradeLimit=InternalGradeLimit(PopUpManager.createPopUp(this,InternalGradeLimit,true));
  	
    _InternalGradeLimit.courseCode.text=courseCode;
    _InternalGradeLimit.courseCode.editable=false;
    _InternalGradeLimit.displayType="I";
    _InternalGradeLimit.showGrid.visible=true;
    
     _InternalGradeLimit.cancelbutton.visible=false;
    _InternalGradeLimit.coursecodelabel.visible=true;
    _InternalGradeLimit.onClickButton();
    _InternalGradeLimit.showCloseButton=true ;
    _InternalGradeLimit.showGrid.visible=false;
    _InternalGradeLimit.method=getupdatedgrade;
    PopUpManager.centerPopUp( _InternalGradeLimit);
   
 	
	}
	
	 if(displayType=="E" || displayType=="R" ){
  	_ExternalGradeLimit=ExternalGradeLimit(PopUpManager.createPopUp(this,ExternalGradeLimit,true));
    _ExternalGradeLimit.courseCode.text=courseCode;
    _ExternalGradeLimit.showCloseButton=true ;
    _ExternalGradeLimit.cancelbutton.visible=false;
    _ExternalGradeLimit.courseCode.editable=false;
    _ExternalGradeLimit.displayType=displayType;
    _ExternalGradeLimit.courseGrid.editable=false;
   
   var mycolumn:DataGridColumn;
    for each(mycolumn in   _ExternalGradeLimit.courseGrid.columns){
    	if(mycolumn.headerText=="Action"){
    		mycolumn.visible=false;
    		
    	}
    }
    
    

    PopUpManager.centerPopUp( _ExternalGradeLimit);
   
 	
	}
	}
	
//	private function getSelectedIndex(item:String):int{
//				var i:int=0;
//				for each(var obj:Object in _gradelimit.examType.dataProvider as ArrayCollection){
//					if(item==obj.displayType+""){
//						return i;
//					}
//					i++;
//				} 
//				return -1;
//			}
	

	
	/**
 	 * Method to fire the select event of the FileReference
     * This Method add Event To FileReference
    **/
	public function selectHandler(event:Event):void{
		fileRef.load();
	}
	/**
	  * Method to fire the COMPLETE event of the FileReference
 	  * This Method upload browsed file to server
 	**/
 	var i:int=0;
	public function onLoadComplete(event:Event):void{
		
		if(i==0){
			fileRef.upload(new URLRequest(commonFunction.getConstants('url')+"/fileUpload/upload.jsp?fileName="+fileRef.name+"&folder=Excel"+"&time="+new Date()));
		}
		i++;
	}
	
	/**
	  * Result Handler of Upload request of fileReference
 	**/
	public function uploadHandler(event:Event):void{
		var columnCT:String=""
		for each(var ob in columList){
			columnCT=ob.headerText+","+columnCT;
		}
		var param:Object={};
		param["data"]=selectedData;
		param["CList"]=columnCT;
		param["display"]=displayType;
		param["fileName"]=fileRef.name;
		param["fileName1"]=courseCode+"_"+entityId+"_"+programCourseKey+"_"+semesterStartDate+"_"+semesterEndDate;//Add...
		param["date"]=new Date();
		
		////////////////////////
		param["entityId"]=entityId;
				param["programId"]=programId;
				param["branchCode"]=branchId;
				param["specCode"]=specializationId;
				param["semesterCode"]=semesterId;
				param["courseCode"]=courseCode;
				param["displayType"]=displayType;
				param["programCourseKey"]=programCourseKey;
				
				if(buttonPressed=='PL'){
					param["startDate"]=sessionStartDate;
					param["endDate"]=sessionEndDate;
				}else{
					param["startDate"]=semesterStartDate;
					param["endDate"]=semesterEndDate;
				}
				param["buttonPressed"]=buttonPressed;
				param["employeeCode"]=employeeCode;
			
		
		////////////////////////
		httpUploadTemplate.send(param);
	}

	/**
	  * Result Handler of Upload request of httpUploadTemplate service
 	**/
	public function httpUploadTemplateResultHandler(event:ResultEvent):void{
		var data:XML=event.result as XML;
		try
		{
			if(event.result.sessionConfirm == true)
			{
	 			Alert.show(resourceManager.getString('Messages','sessionInactive'));
				var url:String="";
				url=commonFunction.getConstants('navigateHome');
				var urlRequest:URLRequest=new URLRequest(url);
				urlRequest.method=URLRequestMethod.POST;
				navigateToURL(urlRequest,"_self");
			}
		}
 		catch(e:Error){}
		for each(var obj:Object in courseListGrid.dataProvider as ArrayCollection){
			obj.select=false;
		}
		courseListGrid.dataProvider.refresh();
		awardSheetGrid.dataProvider.refresh();
		awardSheetCanvas.visible=false;
		 var tempMsg:String=data.resultdata.toString();
		if(tempMsg=="success"){
        	Alert.show(commonFunction.getMessages('uploadSuccessForAwardBlank'), commonFunction.getMessages('success'),null,null, null, successIcon);
        }
        else if(tempMsg=="SqlError"){
        	Alert.show(commonFunction.getMessages('SqlErrorForUploadAwardBlank'), commonFunction.getMessages('error'),null,null, null, errorIcon);
        }
        else if(tempMsg=="NoCT"){
        	Alert.show(commonFunction.getMessages('NoCTForAwardBlank'), commonFunction.getMessages('info'),null,null, null, infoIcon);
        }
          else if(tempMsg=="FileError"){
        	Alert.show(commonFunction.getMessages('fileNoTFound'), commonFunction.getMessages('info'),null,null, null, infoIcon);
        }
          else if(tempMsg=="IOError"){
        	Alert.show(commonFunction.getMessages('IOErrorInAwardBlank'), commonFunction.getMessages('error'),null,null, null, errorIcon);
        }
          else if(tempMsg=="NotReadFile"){
        	Alert.show(commonFunction.getMessages('IOErrorInAwardBlank'), commonFunction.getMessages('info'),null,null, null, infoIcon);
        }
        else if(tempMsg=="fileNameDiff"){
        	Alert.show(commonFunction.getMessages('diffFileNameInAwardBlank'), commonFunction.getMessages('info'),null,null, null, infoIcon);
        }
        else if(tempMsg=="CTCountDiff"){
        	Alert.show(commonFunction.getMessages('diffCountAwardBlank'), commonFunction.getMessages('info'),null,null, null, infoIcon);
        }
        else if(tempMsg=="NoRecord"){
        	Alert.show(commonFunction.getMessages('noRecordFoundInUploadFile'), commonFunction.getMessages('info'),null,null, null, infoIcon);
        }
        else if(tempMsg=="ConstraintFail"){
        	Alert.show(commonFunction.getMessages('ConstraintFailInuploadAwardBlank'), commonFunction.getMessages('error'),null,null, null, errorIcon);
        }
        else {
        	Alert.show(commonFunction.getMessages('problemInService'), commonFunction.getMessages('error'),null,null, null, errorIcon);
        }
	}
	
	/**
 	 * Result handler for getAprStatus service
    **/ 
	private function httpAprStatusService(event:ResultEvent):void{
		var data:XML=event.result as XML;
		try
		{
			if(event.result.sessionConfirm == true)
			{
	 			Alert.show(resourceManager.getString('Messages','sessionInactive'));
				var url:String="";
				url=commonFunction.getConstants('navigateHome');
				var urlRequest:URLRequest=new URLRequest(url);
				urlRequest.method=URLRequestMethod.POST;
				navigateToURL(urlRequest,"_self");
			}
		}
 		catch(e:Error){}
 		downloadButton.visible=false;
 			uploadButton.visible=false;
 		if(data.result.message=="SUB" || data.result.message=="APR"){
 		
 			downloadButton.visible=false;
 			uploadButton.visible=false;
 			gradelimitButton.visible=false ;
 		}
 		else{
 		//	downloadButton.visible=true;  access removed  as requested by examination 
 			gradelimitButton.visible=true ;
 		//	uploadButton.visible=true;  access removed  as requested by examination
 		}
 		if(displayType=="E"){
			var params:Object = new Object();
			params["courseCode"]=courseCode;
			httpIsExtAwardAllowed.send(params);	
		}
		else{
			setButtonPressed('SW');
			setVariables();
		}
	}
	
	public function isANumber(__str:String):Boolean { 
		    return !isNaN(Number(__str)); }
		    
		    
		    private function getpreviousmarks(_rollnumber:String,_component:String):String{
		    	
		    		for each(var obj1:Object in abcbk){
		    			
		    			if (obj1.rollNumber==_rollnumber){
		    				var marks:String=obj1[_component];
		    				
		    					return marks
		    				
		    				
		    			}
		    		}
		    	return null;
		    }
	
	public function getupdatedgrade():void{
		
		 var param:Object =new Object();
		var gradeobject:Object=new Object();
		
		param["courseCode"]=courseCode;
		param["entityId"]=entityId;
		param["programCourseKey"]=programCourseKey;
		
		gradeobject["courseCode"] = courseCode;
		gradeobject["displayType"] = displayType;
		
				 	if (gradelimit=='1'){
		 		
		 			httprequestgetupdatedgradelimit.send(gradeobject) ;
				 	}	
		
	}
	
	
		private function getupdatedgradelimitResultHandler(event:ResultEvent):void{
		var xmlgradelimit:XML = event.result as XML;
//		Alert.show(xmlgradelimit);
		var param:Object =new Object();
		
		//Alert.show("gradelimit"+xmlgradelimit);
		gradelimitarraycoll.removeAll();
		for each(var obj:Object in xmlgradelimit.root){
		gradelimitarraycoll.addItem({coursecode:obj.courseCode,grades:obj.grades ,marksfrom:obj.marksfrom,marksto:obj.marksto});	
		}
		gradelimitarraycoll.refresh();
		if (gradelimitarraycoll.length== 0){
			//Alert.show(commonFunction.getMessages('gradeLimitNotExist'),commonFunction.getMessages("info"),4,null,null,infoIcon) ;
			gradelimitdetail = false ;
			
		}else{
			
				}
	
		
		

	}
	
	
	public function compare (date1 : Date, date2 : Date) : Number {
	var date1Timestamp : Number = date1.getTime ();
	var date2Timestamp : Number = date2.getTime ();
	var result : Number = -1;
	if (date1Timestamp == date2Timestamp){
	result = 0;
	} else if (date1Timestamp > date2Timestamp){
	result = 1;
		}
	return result;
	}
	
	
		public function setpublishdate (myac : ArrayCollection, str:String,publishdate:Object)  {
	var s1:String="";
	//var result : Number = -1;
	for each (var obj:Object in myac){
		var itemindex:int=myac.getItemIndex(obj);
		var d1:String;
		var d2:String;			
		//s1 =obj.evaluationId ;
   					for ( var idx:String in obj){
   						if (idx==str){  // if component found 
   							
   							if (obj[idx]!="" && publishdate[idx] !=""){  // if  publish dates  are not blank. 
   							
			   							if ((DateField.stringToDate(publishdate[idx],"YYYY-MM-DD"))
			   							>(DateField.stringToDate(myac[itemindex][idx],"YYYY-MM-DD"))){
			   							myac[itemindex][idx] =publishdate[idx];	
			   								}
			   									return;
			   													
			   					}else if (obj[idx]!="" && publishdate[idx]==""){  // if publish date is blank
			   								myac[itemindex][idx] =publishdate[idx];	
			   								return ;
			   							}else{  
			   								return;
	   									}					
   						
   									}
   							}	
   						
   					} 
   					
   					myac.addItem(publishdate);
   					return;
  }
  
  private function createlabel():void{
  	 var mycol:AdvancedDataGridColumn;
     var start:int=0;
     var stnextline:int=0;
     var startindex:int = 8;
     var line:int=273;
     var marksgrid_todisplay:Object = new Object();
     var lab:Label = new Label;
     var myar:Array =awardSheetCanvas.getChildren();
      for (startindex;startindex<myar.length; startindex++){
      awardSheetCanvas.removeChildAt(8);	
      }
      
     
 
     
     markspublish.refresh();
     for each( mycol in awardSheetGrid.groupedColumns){
     	if (mycol.dataField=="rollNumber" ||mycol.dataField=="studentName" ){
     	start = mycol.width + start
     	}
     	
     }		
      start += 10;
      stnextline = 10;
		 
          var st:int=1;
        			for each(var obj3:Object in componentAC){
        				if (st!=1){
        					start += 65;
        					
        				}	
        				st +=1;
        					
						var s5: String = obj3.evaluationId;
						marksgrid_todisplay[s5] = "N";
					
						
							for each (var obj4:Object in markspublish){
		
								for (var str:String in obj4){
										if(str==s5 && obj4[s5]!=""){
										lab=new Label();
										var s6:String=obj4[s5];
										s6=ddmmm.format(s6);

										
										lab.text=s6;
									
										
									    lab.x=start;
									    lab.y=line;
									    lab.toolTip="Date published"
									    awardSheetCanvas.addChild(lab);
									    marksgrid_todisplay[s5] = "Y"; 
									 
									 	break;				
							
										}

									}
									
								}
										
			
							}
		  
		  gradelimitButton.visible=true ;
		  
		  mkcorrectionButton.visible=true ;
		  
		  
		  for  each (var str in marksgrid_todisplay){
		  	if (str=="N"){
		  	 gradelimitButton.visible=false;
		  	 submitForApprovalButton.visible=false ;
		  }
		  
		  }
		 
	if ( courseapprstatusarraycoll.length>0 ){
		
			
			var count :int =0;
		for each (var obj5:Object in courseapprstatusarraycoll){
			count +=1;
			if (count==1){
				
		
		
				
			if (String(obj5.status)=="APR" || String(obj5.status)=="SUB"){

			line +=15;
			awardSheetCanvas.height=350;
			lab=new Label();
			lab.y = line ;
			lab.x=stnextline;
			lab.text =  "Dates of  Release by Course Teacher:"
			awardSheetCanvas.addChild(lab);	 
			var stridx:int=0;
			var releasedate:String =String(obj5.submitdates).substr(stridx,10);
			stnextline +=250; 
			while (releasedate!="") {
			lab=new Label();
			lab.y = line ;
			lab.x=stnextline;
			lab.text =  ddmmyy.format(releasedate);
			lab.setStyle("color", "Blue");
			lab.toolTip="Course Teacher:"+ String(obj5.requestSendername)  ;
			awardSheetCanvas.addChild(lab);
			stnextline +=100 
			stridx+=10;	
			releasedate=String(obj5.submitdates).substr(stridx,10);
			}
			if (String(obj5.status)=="APR"){
			stnextline = 10;
			
			line +=15;
			
			lab=new Label();
			lab.y = line ;
			lab.x=stnextline;
			lab.text =  "Date of Release by HOD:"
			awardSheetCanvas.addChild(lab);	
			stnextline +=155;
			lab=new Label();
			lab.y = line ;
			lab.x=stnextline;
			lab.text =  ddmmyy.format(String(obj5.completiondate));
			lab.setStyle("color", "Blue");
			lab.toolTip="HOD:"+ String(obj5.requestgettername)  ;
			awardSheetCanvas.addChild(lab);		
				
			}
			
			

			
				}
			}
			
			
			
			if (count==2){
				
		
			switch(String(obj5.status+"")){
			case 'APR': 
			stnextline +=100;		
			lab=new Label();
			lab.y = line ;
			lab.x=stnextline;
			lab.text =  "Date of Release by DEAN:"
			awardSheetCanvas.addChild(lab);	
			stnextline +=160;
			lab=new Label();
			lab.y = line ;
			lab.x=stnextline;
			lab.text =  ddmmyy.format(String(obj5.completiondate));
			lab.setStyle("color", "Blue");
			lab.toolTip="DEAN::"+ String(obj5.requestgettername)  ;
			awardSheetCanvas.addChild(lab);				
			break;
			

				}
			}
			
		}
	}
	

	 
  }
 	 
  public function httpGetApprovalOrder(event:ResultEvent){
  	var approvalOrderXML = event.result as XML;

  	currentApprovalOrder = approvalOrderXML.root.approvalOrder;

  	
  	var params:Object =new Object();	
		params["courseCode"]=courseCode;
		params["entityId"]=entityId;
		params["programCourseKey"]=programCourseKey;
		params["startDate"]=semesterStartDate;
		params["endDate"]=semesterEndDate;	
		params["display"]=displayType;
		params["displayType"]=displayType;
		params["employeeCode"]=employeeCode;
		params["approvalOrder"]=currentApprovalOrder;
		
		getAprStatus.send(params);
  	
  }
 