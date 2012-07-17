/**
 * @(#) AwardBlankCorrection.as
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

public var roll_number:String=new String;
public var course_code:String=new String;
public var display_type:String=new String;
public var grade_limit:String=new String;

public var detailsXML:XML=new XML;
public var componentXml:XML=new XML;
public var marksXml:XML=new XML;
public var gradeLimitXml:XML=new XML;
public var GradeXML:XML=new XML;

private var componentAC:ArrayCollection=new ArrayCollection();
private var studentMarksListAC:ArrayCollection=new ArrayCollection();
private var detailsAC:ArrayCollection=new ArrayCollection();


public function reset():void{
	rollNumber.enabled=true;
	rollNumber.text='';
	courseCode.text='';
	awardBlankType.selectedIndex=-1;
	courseCode.enabled=false;
	awardBlankType.enabled=false;
	awardSheetCanvas.visible=false;
}

public function onLoad():void{
	getDisplayType();
	getGrades();
}

public function enableCourseInput():void{
	if(rollNumber.text.length>0){
	rollNumber.errorString='';
	courseCode.enabled=true;
	}else{
		courseCode.text='';
		courseCode.enabled=false;
		awardBlankType.enabled=false;
	}
	awardBlankType.selectedIndex=-1;
}

public function enableAwardTypeCombo():void{
	if(courseCode.text.length>0){
	courseCode.errorString='';
	awardBlankType.enabled=true;
	}else{
		awardBlankType.enabled=false;
	}
	awardBlankType.selectedIndex=-1;
}

public function getGrades():void{
	httpGetGrades.send([new Date]);
}

public function getDisplayType():void{
	httpGetDisplayType.send([new Date]);
}

public function resultGetGrades(event:ResultEvent):void{
	GradeXML=event.result as XML;
//	Alert.show(GradeXML);
}

public function resultGetDisplayType(event:ResultEvent):void{
	var Exam:XML=event.result as XML;
//	Alert.show(Exam);
	var start:String
	session=String(Exam.Details[3].startDate)+" --- "+String(Exam.Details[3].endDate);
	var examArr:ArrayCollection=new ArrayCollection();
	for each (var o:Object in Exam.Details)
	{		
		if(o.displayType!=''){
			examArr.addItem({displayType:o.displayType});
		}
	}
	awardBlankType.dataProvider=examArr;
}

public function checkDetails():void{
	if(rollNumber.text!=''&&courseCode.text!=''){
		awardSheetCanvas.visible=false;
		roll_number=rollNumber.text;
		course_code=courseCode.text;
		display_type=awardBlankType.text.charAt(0);
		var param:Object={};
		param['rollNumber']=roll_number;
		param['courseCode']=course_code;
		param['displayType']=display_type;
		httpGetCurrentDetails.send(param);
	}else{
		if(rollNumber.text==''){
			rollNumber.errorString=commonFunction.getMessages('fieldIsRequired');
		}
		if(courseCode.text==''){
			courseCode.errorString=commonFunction.getMessages('fieldIsRequired');
		}
		if(rollNumber.text==''&&courseCode.text==''){
			rollNumber.errorString=commonFunction.getMessages('fieldIsRequired');
			courseCode.errorString=commonFunction.getMessages('fieldIsRequired');
		}
		Alert.show(commonFunction.getMessages('redEntries'),"Error",null,null,null,errorIcon);
	}
}

public function resultGetCurrentDetails(event:ResultEvent):void{
	detailsXML=event.result as XML;
//	Alert.show(detailsXML);
	detailsAC.removeAll();
	for each(var o:Object in detailsXML.StudentDetail){
		detailsAC.addItem({enrollmentNumber:o.enrollmentNumber,studentName:o.studentName,entityId:o.entityId,programId:o.programId,
						branchId:o.branchId,specializationId:o.specializationId,semesterCode:o.semesterCode});
	}
		if(detailsAC.length==0){
			Alert.show(commonFunction.getMessages('noRecordFound'),"Error",null,null,null,errorIcon);
		}else{
			if(detailsXML.StudentDetail.reason!='APR'){
				Alert.show(commonFunction.getMessages('marksNotApproved'),"Error",null,null,null,errorIcon);
			}else if(detailsXML.StudentDetail.status!='REG'){
				Alert.show(commonFunction.getMessages('resultProcessed'),"Error",null,null,null,errorIcon);
			}else{
			grade_limit=detailsXML.StudentDetail.gradeLimitActive;
			var param:Object={};
			param['programId']=detailsXML.StudentDetail.programId;
			param['courseCode']=course_code;
			param['displayType']=display_type;
			httpGetComponentList.send(param);
			}
		}
	}

public function resultGetComponentList(event:ResultEvent):void{
	componentXml=event.result as XML;
//	Alert.show(componentXml);
	componentAC.removeAll();
	for each (var object:Object in componentXml.component){
		componentAC.addItem({evaluationId:object.evaluationId,group:object.group,
		rule:object.rule,idType:object.idType,displayType:object.displayType,maximumMarks:object.maximumMarks});
	}

	if(grade_limit=='1'){
		var param:Object={};
		param['courseCode']=course_code;
		param['displayType']=display_type;
		httpGetGradeLimit.send(param);
	}else{
		getMarks();
		}
}

public function resultGradeLimit(event:ResultEvent):void{
	gradeLimitXml=event.result as XML;
//	Alert.show(gradeLimitXml);
	gradeLimitAC.removeAll();
	for each(var obj:Object in gradeLimitXml.root){
		gradeLimitAC.addItem({coursecode:obj.courseCode,grades:obj.grades ,marksfrom:obj.marksfrom,marksto:obj.marksto});	
	}
	getMarks();
}

public function getMarks():void{
	var param:Object={};
	param['rollNumber']=roll_number;
	param['courseCode']=course_code;
	param['displayType']=display_type;
	param['entityId']=detailsXML.StudentDetail.entityId;
	param['programId']=detailsXML.StudentDetail.programId;
	param['semesterCode']=detailsXML.StudentDetail.semesterCode;
	param['programCourseKey']=detailsXML.StudentDetail.programCourseKey;
	param['semesterStartDate']=detailsXML.StudentDetail.semesterStartDate;
	param['semesterEndDate']=detailsXML.StudentDetail.semesterEndDate;
	httpStudentMarks.send(param);
}

public function resultGetStudentMarks(event:ResultEvent):void{
	marksXml=event.result as XML;
//	Alert.show(marksXml);
	for each(var m:Object in marksXml.marks){
		studentMarksListAC.addItem({marks:m.marks});
	}
	if(studentMarksListAC.length==0){
		Alert.show(commonFunction.getMessages('marksNotFound'),"Error",null,null,null,errorIcon);
	}else{
	studentMarksListAC.removeAll();
	var abc:ArrayCollection=new ArrayCollection();
	   		
   			var resultObj:Object=new Object();
   			resultObj["rollNumber"]=roll_number;
   			resultObj["studentName"]=detailsXML.StudentDetail.studentName;
   			
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
   				if(roll_number==obj2.rollNumber){
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
	   		createMarksGrid(abc);
	   		var MarksType:String=new String;
	   		if(display_type=='I'){
	   			MarksType=commonFunction.getConstants('internalMarks');
	   		}else if(display_type=='E'){
	   			MarksType=commonFunction.getConstants('externalMarks');
	   		}else{
	   			MarksType=commonFunction.getConstants('remedialMarks');
	   		}
	   		courseCodeLabel.text=course_code+' : ';
	   		marksTypeLable.text=MarksType;
		}
}

public function createMarksGrid(dataArray:ArrayCollection){
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
		
		if (grade_limit=='1'){
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
				if (grade_limit=='1'){
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
                         
					if (v=="grade" && grade_limit=='0'){     
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
				params["courseCode"]=course_code;
				params["semesterStartDate"]=detailsXML.StudentDetail.semesterStartDate;
				params["semesterEndDate"]=detailsXML.StudentDetail.semesterEndDate;
				params["programCourseKey"]=detailsXML.StudentDetail.programCourseKey; 
				params["displayType"]=display_type;
				params["data"]=s;
//				Alert.show(s);
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
	Alert.show(commonFunction.getMessages('problemInService'),"Error",null,null,null,errorIcon);
}

