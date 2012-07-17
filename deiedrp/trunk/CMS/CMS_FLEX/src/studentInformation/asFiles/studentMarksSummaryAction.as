/**
 * @(#) studentMarksSummaryAction.as
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

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.containers.HBox;
import mx.controls.Alert;
import mx.controls.Button;
import mx.controls.DateField;
import mx.controls.Label;
import mx.controls.LinkButton;
import mx.events.*;
import mx.formatters.DateFormatter;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

import studentInformation.studentMarkCorrectionRequest;

[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;


[Bindable]public var urlPrefix:String;
[Bindable]public var appliedSemesterId:String;
[Bindable]public var appliedStartDate:String;
[Bindable]public var appliedEndDate:String;


/** Method fires on load of screen and set url to variable
 * and send request to fetch details  
 * */
public function init():void{
	urlPrefix=commonFunction.getConstants('url')+"/studentMarksSummary/";
	var infoObject:Object = new Object();
	infoObject["rollNumber"]=rollNoLbl.text;
	infoObject["courseCode"]=courseLbl.text;
	infoObject["time"]=new Date();
	infoObject["programCourseKey"]=programCourseKey;
	infoObject["entityCode"]=rollNoLbl.text.slice(2,-3);
	infoObject["semesterStartDate"]=semesterStartDateLbl.text;
	infoObject["semesterEndDate"]=semesterEndDateLbl.text;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	getMarksDetail.send(infoObject);
}


public var marksDetail: XML;


/** Method fires on success of fetching values for roll number
 * and sends another request for fetching courses details
 * */ 
public function getMarksDetailSuccess(event:ResultEvent):void{
	marksDetail=event.result as XML;
	
	var componentCollection:ArrayCollection=new ArrayCollection();
	var restCollection:ArrayCollection=new ArrayCollection(); 	
	for each(var obj:Object in marksDetail.marks){
		componentCollection.addItem({evaluationId:obj.evaluationId,mark:obj.mark,displayType:obj.displayType,studentLeft:obj.studentLeft,
									 markSavedDate:obj.markSavedDate,displayStartDate:obj.displayStartDate,displayEndDate:obj.displayEndDate});
		restCollection.addItem({totalInternal:obj.totalInternal,totalExternal:obj.totalExternal,totalMarks:obj.totalMarks,
								internalGrade:obj.internalGrade,externalGrade:obj.externalGrade,finalGradePoint:obj.finalGradePoint,
								semesterStartDate:obj.semesterStartDate,semesterEndDate:obj.semesterEndDate});
    }  
    var rem:String="n";  
    
    //added by ashish-------//
    var compMarkArr:Array=new Array;
    var i:int=0;
    //----------------------//
    	
    for each(var objj:Object in componentCollection){ 
    	
    	var horiBox:HBox=new HBox;
    	horiBox.width=500; 	
    	var lblEvaluation:Label=new Label; 
    	lblEvaluation.width=150;  
    	lblEvaluation.text=(objj.evaluationId).toString();
    	horiBox.addChild(lblEvaluation);  	
		var lblMarks:Label=new Label; 
		//lblMarks.width=60;
		
		//changes by ashish
   	 	if(String(objj.studentLeft)=="0"){
			if(checkVisibilityOfMarks(objj.markSavedDate,objj.displayStartDate)){
				compMarkArr[i]= (objj.mark).toString();
				lblMarks.text = (objj.mark).toString();  
			}
			else{
				compMarkArr[i]= "Not Available";
				lblMarks.text = "Not Available";
				lblMarks.toolTip="Marks Will be displayed after some days";
			}  	
    		horiBox.addChild(lblMarks);
    	
			//for request link visiblility
    		if(checkVisibility(objj.markSavedDate,objj.displayStartDate,objj.displayEndDate)){
    			var link:LinkButton=new LinkButton;
	    		link.label="Send Mark Correction Request";
	    		link.addEventListener(MouseEvent.CLICK,recordSelected);
	    		horiBox.addChild(link);
    		}
    	}
    	else{	
    			compMarkArr[i]= "Not Entered Yet";
    			lblMarks.text = "Not Entered Yet";
    			horiBox.addChild(lblMarks);
    		    var link:LinkButton=new LinkButton;
    			link.label=String(objj.studentLeft)+" Student Marks Left";
    			horiBox.addChild(link);
    	}
    	
    	i++;
    	//changes end
    	
    	var disType:String = objj.displayType; 
    	switch(disType){
    		case 'I':{vboxI.addChild(horiBox);
    				break;}
    		case 'E':{vboxE.addChild(horiBox);
    				break;}
    		case 'R':{//vboxR.addChild(horiBox);
    				vboxI.height=0;
    				vboxE.height=0;
    				rem="y";
    				break;}
    	} 	    	
    }
    
 
   	//changes to get if any component marks left to be entered--//
   	var arry:Array=new Array; 
    for each(var ob:Object in componentCollection){
    	arry.push(Number(String(ob.studentLeft)));
    }
    var studentLeftCount:Number = Math.max.apply(null, arry);
    //----------------------------------------------------------//
    
    
    var nameColl:Array;
    var ob:Object = restCollection.getItemAt(0);
	semesterStartDateLbl.text=ob.semesterStartDate;
    semesterEndDateLbl.text=ob.semesterEndDate;  
    nameColl=["Total Internal","Total External","Total Marks","Internal Grade","External Grade","Final Grade Point"];
    var valColl:Array=new Array;
    valColl.push(ob.totalInternal);
    valColl.push(ob.totalExternal);
    valColl.push(ob.totalMarks);
    valColl.push(ob.internalGrade);
    valColl.push(ob.externalGrade);
    valColl.push(ob.finalGradePoint);
    	
	if(rem=="y"){
		nameColl=["Marks Obtained","","","Grade","","Final Grade Point"];
		valColl[2]="";
	}
	else{
		vboxR.height=0;
	}
	
	//calling function to check any component marks is available or not--//
	var isAllMarksShown:Boolean=checkComponentMarks(compMarkArr);
	//-------------------------------------------------------------------//
	
    for(var i:int=0;i<6;i++){
    	var horiBox:HBox=new HBox;
    	horiBox.width=500;
    	var lblEvaluation:Label=new Label; 
    	lblEvaluation.width=150;
		var lblMarks:Label=new Label; 
		lblEvaluation.text=nameColl[i];
    	horiBox.addChildAt(lblEvaluation,0);
    	
    	//changes starts by ashish
   	 	if(studentLeftCount==0){
   	 		if(isAllMarksShown){
				lblMarks.text = valColl[i]; 
			}
   			else{
   				lblMarks.text = "Not Available";
				lblMarks.toolTip="Marks Will be displayed after some days";
			} 	 	
    	}
    	else{
    		lblMarks.text = "Not Entered Yet";
    	}
    	horiBox.addChildAt(lblMarks,1);
    	//changes end by ashish
    	
    	
    	switch(i){
    		case 0:
    		case 3:if(rem=="y"){
						vboxR.addChild(horiBox);
					}
					else{
						vboxI.addChild(horiBox)	;
					}    				
    				break;
    		
    		case 1:
    		case 4:vboxE.addChild(horiBox);
    				break;
    				
			default:vboxT.addChild(horiBox);
					break;
    	} 	    	
    }
    
    var btn:Button=new Button();
    btn.label="Back";
    btn.addEventListener(MouseEvent.CLICK,closePop);
    vbox.addChild(btn);
    //this.height = headerCanvas.height + vboxCanvas.height+this.height;
    Mask.close();
}

/**
 * method executed on request failure 
 * */
public function onFailure(event:FaultEvent):void{
	Mask.close();
	Alert.show(commonFunction.getMessages('requestFailed'),commonFunction.getMessages('failure'),0,null,null,errorIcon);	
}


//This function removes whole page
public function cancelFunction():void
{
   this.parentDocument.loaderCanvas.removeAllChildren();
}


public function closePop(e:MouseEvent):void{	
	this.parent.getChildAt(1).visible=true;	
	this.parent.removeChild(this);
}


//this method added by ashish mohan 
//to fire request of marks correction
public function recordSelected(e:MouseEvent):void{
	var popUpMarksCorrection:studentMarkCorrectionRequest=studentMarkCorrectionRequest(PopUpManager.createPopUp(this,studentMarkCorrectionRequest,true));
	popUpMarksCorrection.component.text=e.currentTarget.parent.getChildAt(0).text;
	popUpMarksCorrection.param['courseCode']=courseLbl.text;
	popUpMarksCorrection.param['programCourseKey']=programCourseKey;
	popUpMarksCorrection.param['rollNumber']=rollNoLbl.text;
	popUpMarksCorrection.param['semStartDate']=semesterStartDateLbl.text;
	popUpMarksCorrection.param['semEndDate']=semesterEndDateLbl.text;
	popUpMarksCorrection.param['entityCode']=rollNoLbl.text.slice(2,-3);
	PopUpManager.centerPopUp(popUpMarksCorrection);
}



/** 
 * @author Ashish Mohan
 * @param 3 object
 * @return boolean
 * method to check mark correction link should visible or not
 **/
public function checkVisibility(save:Object,start:Object,end:Object):Boolean{

	var currentDF:DateFormatter = new DateFormatter();
	currentDF.formatString = "YYYY-MM-DD";
	var savedDate:Date=DateField.stringToDate(save.toString(),"YYYY-MM-DD");
    var startDate:Date=DateField.stringToDate(start.toString(),"YYYY-MM-DD");
	var endDate:Date=DateField.stringToDate(end.toString(),"YYYY-MM-DD");
	var visibleStartDate:Date=new Date;
	var visibleEndDate:Date=new Date;
	var daysInMilliseconds:int = 1000*60*60*24 ;
	var currentDate:Date=DateField.stringToDate(currentDF.format(new Date()),"YYYY-MM-DD");
	
	//for setting visiblity start date
	if(commonFunction.calculateDays(savedDate,startDate)>0){
		visibleStartDate=startDate;
	}
	else{
		visibleStartDate=savedDate;
	}
	
	//for setting visiblity end date
	if(commonFunction.calculateDays(savedDate,endDate)>0){
		visibleEndDate.setTime(endDate.time+(daysInMilliseconds*3));
	}
	else{
		visibleEndDate.setTime(savedDate.time+(daysInMilliseconds*3));
	}
	
	//checking current date with range
	if(currentDate>=visibleStartDate && currentDate<=visibleEndDate){
		return true;
	}
	else{
		return false;
	}
	
}


/** 
 * @author Ashish Mohan
 * @param 2 object
 * @return boolean
 * method to check mark should show or not
 **/
public function checkVisibilityOfMarks(save:Object,start:Object):Boolean{

	var currentDF:DateFormatter = new DateFormatter();
	currentDF.formatString = "YYYY-MM-DD";
	var savedDate:Date=DateField.stringToDate(save.toString(),"YYYY-MM-DD");
    var startDate:Date=DateField.stringToDate(start.toString(),"YYYY-MM-DD");
	var visibleStartDate:Date=new Date;
	var daysInMilliseconds:int = 1000*60*60*24 ;
	var currentDate:Date=DateField.stringToDate(currentDF.format(new Date()),"YYYY-MM-DD");
	
	//for setting visiblity start date
	if(commonFunction.calculateDays(savedDate,startDate)>0){
		visibleStartDate=startDate;
	}
	else{
		visibleStartDate=savedDate;
	}
	
	//checking current date with visiblity start date
	if(currentDate>=visibleStartDate){
		return true;
	}
	else{
		return false;
	}
	
}
	

	
/** 
 * @author Ashish Mohan
 * @param string array
 * @return boolean
 * method to check all mark is showed or not
 **/
public function checkComponentMarks(ar:Array):Boolean{
	for(var j:int=0;j<ar.length;j++){
		if(ar[j]=="Not Available"){
			return false;
		} 
	}
	return true;	
}
	
	
	
	
	
	
	
