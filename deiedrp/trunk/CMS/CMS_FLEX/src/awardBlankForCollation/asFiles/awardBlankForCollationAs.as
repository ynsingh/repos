// ActionScript file
/**
 * @(#) AwardBlankForCollationAs.as
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
 
import awardBlankSheet.Customcombobox;
import awardBlankSheet.GRRenderer;

import common.Mask;
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.advancedDataGridClasses.AdvancedDataGridColumn;
import mx.controls.advancedDataGridClasses.AdvancedDataGridColumnGroup;
import mx.core.ClassFactory;
import mx.events.CloseEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/warning.png")]private var warningIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;

[Bindable]public var session:String="";
[Bindable]private var columnNameList:Array; 
[Bindable]private var columList:Array;
[Bindable]private var CTGroupName:String;
[Bindable]private var gradeLimitAC :ArrayCollection = new ArrayCollection ;


	public var entityId:String=null;
	public var programId:String=null;
	public var entityType:String=null;
	public var branchId:String=null;
	public var specializationId:String=null;
	public var semesterId:String=null;
	public var courseCode:String=null;
	public var courseName:String=null;
	public var sessionStartDate:String=null;
	public var sessionEndDate:String=null;
	public var resultSystem:String=null;
	public var entityName:String=null;
	public var programName:String=null;
	public var branchName:String=null;
	public var specializationName:String=null;
	public var semesterName:String=null;
	public var entityTypeName:String=null;
	public var semesterStartDate:String;
	public var semesterEndDate:String;
	public var prevousEmployeeId:String;
	public var employeeName:String;
	public var employeeCode:String;
	public var programCourseKey:String;
	public var gradelimit:String;
	public var display_type:String=null;

public var detailsXML:XML=new XML;
public var componentXml:XML=new XML;
public var marksXml:XML=new XML;
public var gradeLimitXml:XML=new XML;
public var GradeXML:XML=new XML;
[Bindable] private var studentXml:XML;
private var componentAC:ArrayCollection=new ArrayCollection();
private var studentMarksListAC:ArrayCollection=new ArrayCollection();
private var detailsAC:ArrayCollection=new ArrayCollection();
public var dataProviderList:ArrayCollection=new ArrayCollection();




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

			var param:Object={};
			param['programId']=programId;
			param['courseCode']=courseCode;
			param['displayType']=examType.text.charAt(0);
			httpGetComponentList.send(param);
}

public function onLoad():void{
	getDisplayType();
	getGrades();
}


public function getGrades():void{
	httpGetGrades.send([new Date]);
}

public function getDisplayType():void{
	httpGetDisplayType.send([new Date]);
}

public function resultGetGrades(event:ResultEvent):void{
	GradeXML=event.result as XML;
}

public function resultGetDisplayType(event:ResultEvent):void{
	var Exam:XML=event.result as XML;
	session=String(Exam.Details[3].startDate)+" --- "+String(Exam.Details[3].endDate);
	var examArr:ArrayCollection=new ArrayCollection();
	for each (var o:Object in Exam.Details)
	{		
		if(o.displayType!=''){
			if(String(o.displayType).toUpperCase().charAt(0)!='I'){
				examArr.addItem({displayType:o.displayType});
			}
		}
	}
	examType.dataProvider=examArr;
	

}


public function getCourses():void{
	awardSheetCanvas.visible=false;
	if(examType.selectedIndex>-1 && courseText.text.length>=6)
	{	
		display_type=examType.text.charAt(0);
		var param:Object={};
		param['displayType']=examType.text.charAt(0);
		param['courseCode']=courseText.text;
		httpGetCoursesDetails.send(param)
	}
	else{
		Alert.show("Please Enter Correct Course Code",commonFunction.getMessages('error'), 4, null,null,errorIcon);
	}
}

public function resultGetCourseDetails(event:ResultEvent):void{
	var employeeCourse:XML = event.result as XML;
	if (examType.text.charAt(0)=="E"){
		courselabel.text = "Select Course to enter External Marks " ;
	}
	else{
		courselabel.text = "Select Course to enter Remedial " ;
	}
		
	detailsAC=new ArrayCollection();
	for each(var obj:Object in employeeCourse.root){
				detailsAC.addItem({select:false,entityId:obj.entityId, programId:obj.programId, programName:obj.programName, 
				branchId:obj.branchId, branchName:obj.branchName, specializationId:obj.specializationId, 
				specializationName:obj.specializationName, semesterCode:obj.semesterCode, semesterName:obj.semesterName, 
				semesterStartDate:obj.semesterStartDate, semesterEndDate:obj.semesterEndDate, courseCode:obj.courseCode, 
				courseName:obj.courseName, programCourseKey:obj.programCourseKey, resultSystem:obj.resultSystem, 
				employeeCode:obj.employeeCode, entityType:obj.entityType, entityName:obj.entityName, 
				startDate:obj.startDate, endDate:obj.endDate, employeeName:obj.employeeName, gradelimit:obj.gradelimit});
	}
			if(detailsAC.length==0){
				Alert.show(commonFunction.getMessages('E101'),commonFunction.getMessages('info'),4,null,null,infoIcon);	
			}
			courseListGrid.dataProvider=null;
			courseListGrid.dataProvider=detailsAC;
			//employeeCode=detailsAC.getItemAt(0).employeeCode;
			
			Mask.close();
	}

public function resultGetComponentList(event:ResultEvent):void{
	componentXml=event.result as XML;

	componentAC.removeAll();
	for each (var object:Object in componentXml.component){
		componentAC.addItem({evaluationId:object.evaluationId,group:object.group,
		rule:object.rule,idType:object.idType,displayType:object.displayType,maximumMarks:object.maximumMarks});
	}

	if(gradelimit=='1'){
		var param:Object={};
		param['courseCode']=courseCode;
		param['displayType']=examType.text.charAt(0);
		httpGetGradeLimit.send(param);
	}else{
		getStudents();
		}
}

public function resultGradeLimit(event:ResultEvent):void{
	gradeLimitXml=event.result as XML;

	gradeLimitAC.removeAll();
	for each(var obj:Object in gradeLimitXml.root){
		gradeLimitAC.addItem({coursecode:obj.courseCode,grades:obj.grades ,marksfrom:obj.marksfrom,marksto:obj.marksto});	
	}
	getStudents();
}

public function getStudents():void{
	var param:Object={};
	param['entityId']=entityId;
	param['programId']=programId;
	param['branchCode']=branchId;
	param['specCode']=specializationId;
	param['semesterCode']=semesterId;
	param['courseCode']=courseCode;
	param['displayType']=examType.text.charAt(0);
	param['startDate']=semesterStartDate;
	param['endDate']=semesterEndDate;
	httpStudentList.send(param)
	
}

private function resultHandlerStudent(event:ResultEvent):void{
		studentXml=event.result as XML;
		if(studentXml.sessionConfirm == true){
    		Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    		this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
		}
		else if(studentXml.student.length()==0){
			Alert.show(commonFunction.getMessages('noStudentFound'),commonFunction.getMessages('info'),4,null,null,infoIcon);	
		}
		else{
	  		getMarks();
	 	}
	}

public function getMarks():void{
	var param:Object={};
	param['courseCode']=courseCode;
	param['displayType']=examType.text.charAt(0);
	param['entityId']=entityId;
	param['programId']=programId;
	param['semesterCode']=semesterId;
	param['courseCode']=courseCode;
	param['programCourseKey']=programCourseKey;
	param['startDate']=semesterStartDate;
	param['endDate']=semesterEndDate;

	httpStudentMarks.send(param);
}

public function resultGetStudentMarks(event:ResultEvent):void{
	marksXml=event.result as XML;

	for each(var m:Object in marksXml.marks){
		studentMarksListAC.addItem({marks:m.marks});
	}

	studentMarksListAC.removeAll();
	var abc:ArrayCollection=new ArrayCollection();
	   	for each (var obj1:Object in studentXml.student){
   			var resultObj:Object=new Object();
   			
   			var _s1:String=obj1.rollNumber;
   		
   		    resultObj["rollNumber"]=_s1;
            resultObj["studentName"]=obj1.studentName;
   			
   			resultObj["totalInternal"]= "0";
			resultObj["totalExternal"]="0";
			resultObj["totalMarks"]="0";
			resultObj["grade"]="";

			for each(var obj3:Object in componentAC){
			var s4: String = obj3.evaluationId;
			resultObj[obj3.evaluationId]="0";
			}
			var cVal:String="";
   			var cVal1:String="";
   			var cVal2:String="";
   			var grade:String="";
   			for each(var obj2:Object in marksXml.marks){
   				if(_s1==obj2.rollNumber){
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
							cValue = "ZZZ";
						}
						else{
							cVal1=cValue;
						}
   					}
					
   					resultObj[obj2.evaluationId]=cValue;
   					cVal=cVal1+"/"+obj2.evaluationId+"-"+cVal;
   					cVal2=cVal1+"/"+obj2.evaluationId+"#"+cVal2;	
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
   					
   					if(display_type.toUpperCase()=="I"){ 
   						resultObj["grade"]=obj2.internalGrade;	
						grade=obj2.internalGrade;
   					}
   					else if(display_type.toUpperCase()=="E"){
   						resultObj["grade"]=obj2.externalGrade;
						grade = obj2.externalGrade ;
   					}
   					else{
						resultObj["grade"]=obj2.grades;
						grade=obj2.grades;
   					}

   				}
   			}
   			
   			abc.addItem(resultObj);
   			dataProviderList.addItem({data:obj1.rollNumber+","+obj1.studentName+","+cVal+","+grade});
   		}
	   		
	   		
	   		createMarksGrid(abc);
	   		var MarksType:String=new String;

	   		if(display_type=='I'){
	   			MarksType=commonFunction.getConstants('internalMarks');
	   		}else if(display_type=='E'){
	   			MarksType=commonFunction.getConstants('externalMarks');
	   		}else{
	   			MarksType=commonFunction.getConstants('remedialMarks');
	   		}
	   		courseCodeLabel.text=courseCode+' : ';
	   		marksTypeLable.text=MarksType;
	
}

public function createMarksGrid(dataArray:ArrayCollection):void{
	 	columnNameList=new Array();
	 	columList=new Array();
	 	var colmns: Array = new Array;
		var rollNoColmn:AdvancedDataGridColumn=new AdvancedDataGridColumn;
		rollNoColmn.headerText=commonFunction.getConstants('rollNo');
		rollNoColmn.width=100;
		rollNoColmn.editable=false;
		rollNoColmn.dataField='rollNumber';
		
		var nameColmn:AdvancedDataGridColumn=new AdvancedDataGridColumn;
		nameColmn.headerText=commonFunction.getConstants('studentName');
		nameColmn.width=150;
		nameColmn.editable=false;
		nameColmn.dataField='studentName';
		
		colmns.push(rollNoColmn);
		colmns.push(nameColmn);
		
		columnNameList.push(rollNoColmn);	
		columnNameList.push(nameColmn);  
		
		var temp:Array=new Array();
		var w:int=320;
		CTGroupName="";
		var protect:Boolean = true;
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
		                colmn.dataTipField=obj2.evaluationId;
		                colmn.editable = protect;
						
						columnNameList.push(colmn);	
		                columList.push(colmn);
						
		                switch(obj2.idType+"")
		                {
		                	case 'GR':
		                	    colmn.width=51;
		                        colmn.editorDataField='grValue';					                        
		                        colmn.itemEditor=new ClassFactory(GRRenderer);
		                        break;
		                	case 'MK':
							    colmn.width=56;
							    colmn.editorDataField="mkValue";
							    var mkFactory:ClassFactory=new ClassFactory(Customcombobox);
							    mkFactory.properties={datafield:colmn.dataField,maximumValue:Number(obj2.maximumMarks)};							    
							    colmn.itemEditor=mkFactory;

							    colmnGroup.children.push(colmn);
								w+=int(colmn.width);
								if(display_type=='E'){
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
		
			var totalMarksColmn:AdvancedDataGridColumn=new AdvancedDataGridColumn;
			totalMarksColmn.headerText=commonFunction.getConstants('totalMarks');
			totalMarksColmn.width=100;
			totalMarksColmn.editable=false;
			totalMarksColmn.labelFunction=getTotal;
			 if(display_type=='I'){
		    	totalMarksColmn.dataField='totalInternal';
		    	}else if(display_type=='E'){
		    	totalMarksColmn.dataField='totalExternal';
		    }else if(display_type=='R'){
		    	totalMarksColmn.dataField='totalMarks';
		    }	
			
			colmns.push(totalMarksColmn);
			w=w+100;
		
	    var GradeColmn:AdvancedDataGridColumn = new AdvancedDataGridColumn ;
	    GradeColmn.headerText=commonFunction.getConstants('grade');
	    CTGroupName=GradeColmn.headerText.toString()+"-"+CTGroupName; 
	    
		GradeColmn.headerText=commonFunction.getConstants('grade')+"\n"+'AtoF'  
		GradeColmn.visible=false;
		if (gradelimit=='1'){
			GradeColmn.editable=false ;
		}else{
			GradeColmn.editable=protect ;
		}
	
		GradeColmn.width=70;
	    GradeColmn.editorDataField='grValue';	
	    GradeColmn.dataField="grade";	    
		GRRenderer.gradeXMLData=GradeXML;
	    GRRenderer.grid=awardSheetGrid;
	    GradeColmn.itemEditor=new ClassFactory(GRRenderer);
		colmns.push(GradeColmn);
		columnNameList.push(GradeColmn);

		columList.push(GradeColmn);
	
		w=w+100;
			
		awardSheetGrid.groupedColumns = colmns;
		awardSheetGrid.width=w;
		awardSheetGrid.dataProvider=dataArray;
		
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
}

private function saveConfirm():void{
		Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),3,null,saveMarks,questionIcon); 	
}

public function saveMarks(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		var gridData:ArrayCollection=commonFunction.getAdvancedDataGridRowData(awardSheetGrid);
		if(gridData.length>0){
			var s:String="";
			var totmrk :int;
			var st:String="";
			var nocomponent_exist:Boolean =true;
			for each(var o:Object in gridData){ 
				if (gradelimit=='1'){
					if(display_type.toUpperCase()=="I"){
		    						totmrk=o["totalInternal"];
		    						}
		    						else if(display_type.toUpperCase()=="E"){
		    							totmrk=o["totalExternal"];
		    						} else{
		    							totmrk=o["totalMarks"];
		    						}
		    						
		    						var wgrade:String = calculategrade(totmrk);
		    						if (wgrade==null){
		    						Alert.show(commonFunction.getMessages('gradeLimitDoesNotExist')+ totmrk,commonFunction.getMessages('info'),4,null,null,infoIcon);	
		    						return;
		    						}
				 }else {
				 	wgrade = o["grade"] ;
				 }
				
				for(var v:String in o){
					if (v==''){
			    		v='ZZZ'
			    	}
		    		if((v!="rollNumber")&&(v!="studentName")&&(v!="totalMarks")&&(v!=o[v]+"idType")&&
			    	(v!="externalGrade")&&(v!="internalGrade")&&(v!="totalInternal")&&(v!="totalExternal")){
			    	if (v!="grade"){
			    		var idmaxmarks:Number  = new Number(componentXml.component.(evaluationId==v).maximumMarks);
			    		var componentMarks:Number ;
			    		if (isANumber(o[v])){
			    			componentMarks  = new Number(o[v]);
			    		}else{
			    			componentMarks = 0;
			    		}
			    		if (componentMarks > idmaxmarks){
			    			Alert.show(commonFunction.getMessages('EnteredmarksmorethanMaximumMark')+ " for Roll Number: "+ 
							o["rollNumber"] + " and component: " + v
							,commonFunction.getMessages('error'),4,null,null,errorIcon);
			    			return;
			    		}
			    		if (v==''){
			    		  	v='ZZZ' 
			    		}
		    			nocomponent_exist = false ;
		    			if(display_type=='I'){
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalInternal"]+"|"+wgrade+";";
						}else if(display_type=='E'){
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalExternal"]+"|"+wgrade+";";
						}else {
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalMarks"]+"|"+wgrade+";";
						}
                    }
                         
					if (v=="grade" && gradelimit=='0'){     
			    		var invalidgrade:Boolean =true;	
			    		var s1:XML;
			    		var s2:String;
						for each (s1 in  GradeXML.gradeList.grade){
							s2=s1;
							if (o[v] ==s2){
			    		  	invalidgrade = 	false ;
			    		  	break;
			   			}
			    	}
			    	if (invalidgrade==true){
			    		Alert.show(commonFunction.getMessages('invalidgrade')+ " for Roll Number: "+ 
									o["rollNumber"] + " and Grade : " + o[v]
									,commonFunction.getMessages('error'),4,null,null,errorIcon);
									return;	
			    	}
				}
			}
		}
				
					if (nocomponent_exist){
						if(display_type=='I'){
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalInternal"]+"|"+wgrade+";";
						}else if(display_type=='E'){
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalExternal"]+"|"+wgrade+";";
						}else {
		    				s+=o["rollNumber"]+"|"+v+"|"+componentXml.component.(evaluationId==v).idType+"|"+o[v]+"|"+o["totalMarks"]+"|"+wgrade+";";
						}
					}	
			    }
			   	var params:Object=new Object();
			   	params['entityId']=entityId;
			   	params['programCourseKey']=programCourseKey;
				params['courseCode']=courseCode;
				params['startDate']=semesterStartDate;
				params['endDate']=semesterEndDate;
				params['displayType']=display_type;
				params["data"]=s;

				Mask.show(commonFunction.getMessages('pleaseWait'));	    		
				httpSaveSheet.send(params);
			}
			else{    
				Alert.show(commonFunction.getMessages('noStudentFound'),commonFunction.getMessages('info'),4,null,null,infoIcon);	
			}		
		}
	}
		
public function resultSaveSheet(event:ResultEvent):void{
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }
	if(result.exception.exceptionstring == 'E'){
	   	Alert.show(commonFunction.getMessages('error'),commonFunction.getMessages('error'),4,null,null,errorIcon);
	}else{
	   	Alert.show(commonFunction.getMessages('savedSuccessfully') ,commonFunction.getMessages('success'),4,null,null,successIcon);
	   	getMarks();
	}
	Mask.close();
}

public function onFailure(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService')+event.fault,"Error",null,null,null,errorIcon);
}


public function getbest(myArray:Array,rule:String):int{
		myArray.sort(Array.NUMERIC);
		
		var maxValue1:int =myArray.pop();
		var maxValue2:int =myArray.pop();
		var maxValue3:int =myArray.pop();
		var maxValue4:int =myArray.pop();
		var maxValue5:int =myArray.pop();
		
		var max:int =0;
		if(rule == "BT1" ){
			 max=maxValue1 ;
		} else if(rule == "BT2" ){
		   max=maxValue1+maxValue2;	
		} else if(rule == "BT3" ){
		   max=maxValue1+maxValue2+maxValue3;	
		} else if(rule == "BT4" ){
		   max=maxValue1+maxValue2+maxValue3+maxValue4;	
		} else if(rule == "BT5" ){
		   max=maxValue1+maxValue2+maxValue3+maxValue4+maxValue5;	
		} else{
			max=maxValue1+maxValue2;
		}
		
	
		return	max;
	}


	public function getTotal(row:Object,col:AdvancedDataGridColumn):String
	{
		var myArray:Array=new Array();
		var tempArray:Array=new Array();
		var rule:String = "";
		var myArray:Array=new Array();
				var total:int=0;
		
	    for each(var obj2:Object in componentAC){ //loop 03
	       
			var groupName:String=obj2.group;
			if(tempArray.indexOf(groupName)<0){	 //loop 02
		
		
			   	var arr:Object=new Object();
			   	
			   	var i:int=0;
			   	rule = obj2.rule;
			   	for each(var obj3:Object in componentAC){ // start loop 01
					if(groupName==obj3.group){
						if(obj3.idType=='MK'){
							arr[i++]=obj3.evaluationId;
			   			}
			   		}
			   	}// end for loop 01
			   	tempArray.splice(0);
			   	myArray.splice(0) ;
				tempArray.push(groupName);
				myArray.push(arr);
	//		}  	
	//	}  
			
		//var total:int=0;
		for each(var ac:Object in myArray){			
			var arr1:Array=new Array();	
			for each(var o:String in ac){
				arr1.push(int(row[o]));
			}
			
			
			
			total=total+getbest(arr1,rule);
		}
		}//loop 02
	    }//loop 03
		if(display_type=='I'){
		row["totalInternal"]=total;
		}else if(display_type=='E'){
			row["totalExternal"]=total;
		}else{
			row["totalMarks"]=total;
		}
		return	total+"";
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

	public function calculategrade(marks:int):String{
	
	var wtotalmarks:int = new int(); 
	var lcut :int = new int();
	var ucut:int = new int();
	
	wtotalmarks =marks ;
			
	for each (var obj:Object in gradeLimitAC ){ 
		lcut = obj.marksfrom ;
		ucut = 	obj.marksto ;
	if (wtotalmarks>=lcut && wtotalmarks <= ucut ) {
	return (obj.grades);
	}
	}
	return null ;
	}
	
public function isANumber(__str:String):Boolean { 
		    return !isNaN(Number(__str)); 
}



