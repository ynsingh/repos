/**
 * @(#) conformWindowAction.as
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
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
import common.Mask;

import mx.collections.*;
import mx.controls.*;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

public var entity:String="";
public var entityName:String="";

public var program:String="";
public var branch:String="";
public var specialization:String="";

public var process:String="";
public var semester:String="";
public var semesterName:String="";

public var programCourseKey:String="";
public var ssd:String="";
public var sed:String="";
public var activityCode:String="";
public var activityName:String="";
public var actvitySeq:String="";
public var activityStatus:String="";
[Bindable] public var buttonFunction:Function;
[Bindable] public var gridFunction:Function;
[Bindable] public var rejArray:ArrayCollection;
[Bindable] private var xmldata_activityList:XML;

public var url_DNS:String ="";
    	[Bindable] public var urlStartActivityList:String = "";


//This function Gives alert on confirmation 

public function confirmFunction():void 
{   
					url_DNS = commonFunction.getConstants('url');
					//Check CourseMaster Controller: Amir Code
		    		urlStartActivityList = url_DNS+"/startActivity/startProcessActivity.htm";
		    		
	var param:Object=new Object();
	param["entityId"]=entity;
	
	param["entityName"]=entityName;
	
	param["programId"]=program;
	param["branchId"]=branch;
	param["specializationId"]=specialization;
	
	param["programName"]=programforConfirmWindow.text;
	param["branchName"]=branchforConfirmWindow.text;
	param["specializationName"]=specilizationforConfirmWindow.text;
	
	
	
	param["processId"]=process;
	param["semesterId"]=semester;
	param["semesterName"]=semesterName;
	
	param["programCourseKey"]=programCourseKey;
	param["semesterStartDate"]=ssd;
	param["semesterEndDate"]=sed;
	
	param["activityCode"]=activityCode;
	param["activityName"]=activityName;
	param["activitySequence"]=actvitySeq;
	param["activityStatus"]=activityStatus;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	httpXmlStartActivityList.send(param);
	//Alert.show((resourceManager.getString('Messages','activitySubmittedSuccessfully')),(resourceManager.getString('Messages','success')),0,null,null,successIcon);
	
	closeConfirmWindow();
}

 private function startActivityResultHandler(event:ResultEvent):void{
        xmldata_activityList=event.result as XML;
        Mask.close();
     //  Alert.show(xmldata_activityList);
      // Alert.show(xmldata_activityList.error);
       // Alert.show(xmldata_activityList.error.errorRecord);  
        var errorList:XMLList=new XMLList(xmldata_activityList.error.errorRecord);
//        Alert.show(errorList+"");
//        Alert.show(errorList.length());
				var total:String="";
				var correct:String="";
        		var reject:String="";
        		var error:String="";
        		var isComplete:String="";
     //  Alert.show('coming in result activity start'+xmldata_activityList);
        var coutResult:ArrayCollection=new ArrayCollection();
        for each(var o:Object in xmldata_activityList.count){
        	coutResult.addItem({total:o.total,correct:o.correct,error:o.error,activityCompleted:o.activityCompleted});
        }
        rejArray=new ArrayCollection;
        for each(var objs:Object in errorList){
        	
        	var ss:String=objs.processed;
        	if(ss.length>30){
        	ss=ss.substring(0,30);
        	}
        	
        	rejArray.addItem({rollno:objs.rollNumber,reason:ss,detail:objs.processed});
        }
        		
       for each(var o:Object in coutResult){
        		total=o.total;
        		correct=o.correct;
        		error=o.error;
        		isComplete=o.activityCompleted;
        	}
        	
       if(total==""){
       	
        Alert.show("Database Inconsistency:System Failure","Error",0,null,null,errorIcon); 	
       		 
       }else{
       			var sts:String="";
       		 if(isComplete=="true")	{
       		 	sts="Activity Completed";
       		 }else if(isComplete=="false"){
       		 	sts="Activity Not Completed";
       		 }
           Alert.show("Total Records ="+" "+total+"\n"+"Records Processed Correctly ="+" "+correct+"\n"+"Records Gives Error ="+" "+error+"\n"+sts,"Result",4,null,onStart,successIcon);
           //Alert.show("Dheeraj : " + activityCode);
           if(activityCode=='MST'){
           	emailService.send([new Date]);
           }
       
        }
        
        //specializationCombo.dataProvider=xmldata_activityList.activity.value;
    }
    
    public function onStart(event:CloseEvent):void{
    	
    //	Alert.show("rohit");
        if(rejArray.length>0){
        	//Alert.show("1st go");
        	gridFunction(rejArray);
        }else{
        	buttonFunction.call();
        }
    	
    }
    
 private function faultHandler(event:FaultEvent):void{
        Alert.show(commonFunction.getMessages('requestFailed'),
	commonFunction.getMessages('failure'),0,null,null,errorIcon);
	Mask.close();
    }
    
//This function Close popup window on click of cancel button on window
public function cancelConfirmWindow():void 
{
	closeConfirmWindow();
}

//This function Close popup window on click of close sign at corner on window
public function closeConfirmWindow():void
{
	PopUpManager.removePopUp(this);			
}
  
   private function initializeForEsc(evt:KeyboardEvent):void
   {
    if (evt.keyCode == Keyboard.ESCAPE)
    {
    	closeConfirmWindow();
    }
  }
  