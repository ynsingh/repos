/**
 * @(#) prestagingActivityAction.as
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
import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question1.png")]private var questionIcon:Class;
public var forActivities:String="";
public var forConfirmActivty:String="";
protected var processGridList:ArrayCollection;
protected var activityGridList:ArrayCollection; 
import mx.flash.*;
import flash.display.Sprite;
import mx.validators.Validator;
import common.Mask;

[Bindable] private var xmldata_entityName:XML;
[Bindable] private var xmldata_programName:XML;
[Bindable] private var xmldata_branchName:XML;
[Bindable] private var xmldata_specializationName:XML;
[Bindable] private var xmldata_semesterName:XML;
[Bindable] private var xmldata_activityName:XML;
[Bindable] private var xmldata_semesterDate:XML;
[Bindable] private var xmldata_countRecord:XML;

[Bindable] public var url_DNS:String ="";
[Bindable] public var urlActivityList:String="";


public function getString(pass_key:String):String{
		
		return resourceManager.getString('ApplicationResource', pass_key);
		
		}

public function setDefaultValues():void
		{
			url_DNS = commonFunction.getConstants('url');
 	    		//urlActivityList=url_DNS+"/prestaging/getActivity.htm"
 	    		//Add entity into entity combo box
 	    			
		    		get_Entity_data();
		    	//Add entity into semester combo box	
		    	//	get_Semester_data();
		    	//Add entity into activity combo box	
		    		get_Activity_data();
}

public function setFirstFocus(object:UIComponent):void
            {
                object.setFocus();
                object.drawFocus(true);
                setDefaultValues();
            }
            

            
public function get_Entity_data(){
	Mask.show(commonFunction.getMessages('pleaseWait'));
	httpXmlEntityList.send();

}

public function get_Activity_data(){
	//Alert.show('inside set act'+urlActivityList);
	httpXmlActivityList.send();
}

public function get_Semester_data(){
	
	 httpXmlSemesterList.send();
}



public function get_Program_data(){
	 var param:Object=new Object();
	 param["entityId"]=xmldata_entityName.entity.(entityName==entityCombo.selectedLabel).entityId;
	 Mask.show(commonFunction.getMessages('pleaseWait'));
	 httpXmlProgramList.send(param);
	 
}

public function setDates(){
	var param:Object=new Object();
	param["semesterCode"]=xmldata_semesterName.Details.(name==semesterCombo.selectedLabel).id;
	param["programId"]=xmldata_programName.program.(programName==programCombo.selectedLabel).programId;
	param["entityId"]=xmldata_entityName.entity.(entityName==entityCombo.selectedLabel).entityId;
	//Alert.show(xmldata_entityName.entity.(entityName==entityCombo.selectedLabel).entityId);
	Mask.show(commonFunction.getMessages('pleaseWait'));
	httpXmlSetDate.send(param);
	 
}

private function entityResultHandler(event:ResultEvent):void{
        xmldata_entityName=event.result as XML;
        //Alert.show(xmldata_entityName);
       entityCombo.dataProvider=xmldata_entityName.entity.entityName;
        //get_program_data();
}

private function semesterResultHandler(event:ResultEvent):void{
	
      xmldata_semesterName=event.result as XML;        
         
         var semesterXml:XMLList = new XMLList(xmldata_semesterName.Details);
         
//         Alert.show(semesterXml.length());
         
         if(semesterXml.length()==0){
         	
         Alert.show(commonFunction.getMessages('nosemesters'),
         		commonFunction.getMessages('error'),0,null,null,errorIcon);
         	
         }else{
     
        semesterCombo.dataProvider=xmldata_semesterName.Details.name;
         }
       Mask.close();
}

private function programResultHandler(event:ResultEvent):void{
    	
        xmldata_programName=event.result as XML;
       
//        Alert.show(xmldata_programName+"dedd");
       
       var programList:XMLList = new XMLList(xmldata_programName.program);
       
       //programList = xmldata_programName as XMLList;
       
//       Alert.show("errl"+programList.length());
       
       if(programList.length()==0){
       	
//       	Alert.show("No Program Exists for seleceted entity");
       	
       	Alert.show((commonFunction.getMessages('noprogram')),
				(commonFunction.getMessages('error')),0,null,null,errorIcon);
       	
       }else{
       
        programCombo.dataProvider=xmldata_programName.program.programName;
        //get_branch_data();
        get_Semester_data();
       }
       
       Mask.close();
}

private function activityResultHandler(event:ResultEvent):void{
        xmldata_activityName=event.result as XML;
        activityCombo.dataProvider=xmldata_activityName.activity.activityName;
        Mask.close();
}

private function semesterDateResultHandler(event:ResultEvent):void{
    	
        xmldata_semesterDate=event.result as XML;
    
        semesterStartDateField.text=xmldata_semesterDate.semesterStartDate;
       // Alert.show("Dheeraj Check--- "+xmldata_semesterDate);
       
        semesterEndDateField.text=xmldata_semesterDate.semesterEndDate;
        if((semesterStartDateField.text!="")&&(semesterEndDateField.text!="")){
        	startButton.enabled=true;	
        }else{
        	startButton.enabled=false;
        	
        	Alert.show((commonFunction.getMessages('nosemesterdates')),
				(commonFunction.getMessages('error')),0,null,null,errorIcon);
//        	Alert.show("Program Registration not build for the selected semester");
        }
        
        //get_branch_data();
        Mask.close();
}

private function faultHandler(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,getString('errorInResult'));
         Mask.close();
    }


public function entityChange():void
{
	programCombo.enabled=true;
	programCombo.selectedIndex=-1;
	semesterCombo.selectedIndex=-1;
	semesterStartDateField.text="";
	semesterEndDateField.text="";
	get_Program_data();
}


public function programChange():void
{
	semesterCombo.enabled=true;
	semesterCombo.selectedIndex=-1;
	semesterStartDateField.text="";
	semesterEndDateField.text="";
}




/**This function set semester start date and end date for selected semester on semester change
 and enable start Button on 1st time selection semester selection */
public function startActivity():void
{
	if(Validator.validateAll([entityComboValidator,activityComboValidator,programComboValidator,semesterComboValidator]).length==0){	
		Alert.show(commonFunction.getMessages('areyousure'), commonFunction.getMessages('confirm'), 3, this, startConfirm,questionIcon);
	}
	else{
	 Alert.show(commonFunction.getMessages('pleasecheckEntriesinRed'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	
	}
}

protected function startConfirm(event:CloseEvent):void{
	if(event.detail==Alert.YES){
		var param:Object=new Object();	  
		param["entityId"]=xmldata_entityName.entity.(entityName==entityCombo.selectedLabel).entityId;
		param["semesterCode"]=xmldata_semesterName.Details.(name==semesterCombo.selectedLabel).id;
		param["programId"]=xmldata_programName.program.(programName==programCombo.selectedLabel).programId;
		param["activityId"]=xmldata_activityName.activity.(activityName==activityCombo.selectedLabel).activityId;
		param["semesterStartDate"]=semesterStartDateField.text;
		param["semesterEndDate"]=semesterEndDateField.text;
		Mask.show(commonFunction.getMessages('pleaseWait'));
		httpXmlStartActivity.send(param);	
	}
}


private function startActivityResultHandler(event:ResultEvent):void{
    	
        xmldata_countRecord=event.result as XML;
     if(xmldata_countRecord.Details.list_values=="failure"){
        	
        	Alert.show(commonFunction.getMessages('clearTempTables'),commonFunction.getMessages('error'),0,null,null,errorIcon);     
        	
        }else{   
				var total:String="";
				var correct:String="";
        		var reject:String="";
        		var error:String="";
        		var isComplete:String="";
        var coutResult:ArrayCollection=new ArrayCollection();
        for each(var o:Object in xmldata_countRecord.count){
        	coutResult.addItem({total:o.total,correct:o.correct,reject:o.rejected,error:o.error,activityCompleted:o.activityCompleted});
        }
         		
       for each(var o:Object in coutResult){
        		total=o.total;
        		correct=o.correct;
        		reject=o.reject;
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
           Alert.show("Total Records ="+" "+total+"\n"+"Records Processed Correctly ="+" "+correct+"\n"+"Records Rejected ="+" "+reject+"\n"+"Records Give Error ="+" "+error+"\n"+sts,"Result",0,null,reset,successIcon);
        }
		}
        
       
       Mask.close();
       // programCombo.dataProvider=xmldata_programName.program.programName;
        //get_branch_data();
}

public function reset(event:CloseEvent):void{
	
	semesterCombo.selectedIndex=-1;
	programCombo.selectedIndex=-1;
	activityCombo.selectedIndex=-1;
	entityCombo.selectedIndex=-1;
	semesterStartDateField.text="";
	semesterEndDateField.text="";
	semesterCombo.enabled=false;
	programCombo.enabled=false;
	
}

public function cancelFunction():void
{
	  this.parentDocument.loaderCanvas.removeAllChildren();
}