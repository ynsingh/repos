/**
 * @(#) ProgList.as
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
import ProgramCourse.GridDataExporterService.DataGridDataExporter;

import common.Mask;
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.events.ListEvent;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Embed(source="/images/warning.png")]private var warningIcon:Class;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;

[Bindable]
public var course_availability:XML=<availability>
										<name value="Yes">
											<code>true</code>
										</name>
										<name value="No">
											<code>false</code>
										</name>
									</availability>;	

	public var selectedRow:Number=0;
	public var selectedColumn:Number=0;
	[Bindable]
	private var initialGridData:ArrayCollection = new ArrayCollection([
		{course_code:"Click Here",description:"", course_type:"", category:"", group:"",availability:""}]);
		
    [Bindable] private var xmldata_progName:XML;
    [Bindable] private var xmldata_branchName:XML;
    [Bindable] private var xmldata_semesterList:XML;
    [Bindable] private var xmldata_specialList:XML;
    [Bindable] public var xmldata_courseDetail:XML;
    [Bindable] public var xmldata_existingCourseDetail:XML;
    [Bindable] public var xmldata_insertProgramCourseDetail:XML;
 	[Bindable] public var course_details:ArrayCollection = null;
 
 	public var current_coursecode:String=null;
    //xml format DUMMY data	for Grid	
   
	public var url_DNS:String ="";
	[Bindable] public var urlProgramList:String = "";
   	[Bindable] public var urlBranchList:String = "";
	[Bindable] public var urlSemList:String = "";
 	[Bindable] public var urlSpecialList:String = "";
 	[Bindable] public var urlCourseDetail:String = "";
 	[Bindable] public var urlExistingCourseDetail:String="";
 	[Bindable] public var urlProgCourseDetail:String = "";
     
 		
 	public function getString(pass_key:String):String{
		return commonFunction.getConstants(pass_key);
	}
		
	public function init():void{
		url_DNS = commonFunction.getConstants('url');
		urlProgramList = url_DNS+"/progsetup/programList.htm";
		urlBranchList = url_DNS+"/progsetup/branchList.htm";
		urlSemList = url_DNS+"/progsetup/semesterList.htm";
		urlSpecialList = url_DNS+"/progsetup/specializationList.htm";
		urlCourseDetail = url_DNS+"/progsetup/courseDetails.htm";
		urlExistingCourseDetail = url_DNS+"/progsetup/existingCourseDetails.htm";
		urlProgCourseDetail = url_DNS+"/progsetup/insertCourseData.htm";
		get_Program_data();
	}
     
    public function get_Program_data():void {
    		var date:Object = {};
    		date = new Date();
            httpXmlProgList.send(date);
            Mask.show(commonFunction.getMessages('pleaseWait'));
     }
    
	private function get_branch():void {
    	var params:Object = {};
      	params["time"]=new Date();
      	params["progId"] = xmldata_progName.program.(@name==program_nameCB.selectedLabel).programId;
        httpXmlBranchList.send(params);
        Mask.show(commonFunction.getMessages('pleaseWait'));
    }
    
    private function get_semester():void {
    	var params:Object = {};
      	params["time"]=new Date();
      	params["progId"] = xmldata_progName.program.(@name==program_nameCB.selectedLabel).programId;      
        httpXmlSemList.send(params);
        Mask.show(commonFunction.getMessages('pleaseWait'));
    }
    
	private function get_specialization():void {
    	var params:Object = {};
      	params["time"]=new Date();
      	params["progId"] = xmldata_progName.program.(@name==program_nameCB.selectedLabel).programId;
      	params["branchId"] = xmldata_branchName.branch.(@name==branch_nameCB.selectedLabel).branchId;
      	params["semCode"] = semester_nameCB.selectedItem.semesterCode;
        httpXmlSpecialList.send(params);
        Mask.show(commonFunction.getMessages('pleaseWait'));
    }
     
	private function get_courseDetails():void {
    	var params:Object = {};
      	params["time"]=new Date();
      	params["progId"] = xmldata_progName.program.(@name==program_nameCB.selectedLabel).programId;
      	params["branchId"] = xmldata_branchName.branch.(@name==branch_nameCB.selectedLabel).branchId;
      	params["semCode"] = semester_nameCB.selectedItem.semesterCode;
      	params["specialId"] = xmldata_specialList.branch.(@name==specializationCB.selectedLabel).branchId;
        httpXmlCourseDetail.send(params);
        Mask.show(commonFunction.getMessages('pleaseWait'));
    }
     
	private function get_existingCourseDetails():void {
    	var params:Object = {};
      	params["time"]=new Date();
      	params["progId"] = xmldata_progName.program.(@name==program_nameCB.selectedLabel).programId;
      	params["branchId"] = xmldata_branchName.branch.(@name==branch_nameCB.selectedLabel).branchId;
      	params["semCode"] = semester_nameCB.selectedItem.semesterCode;
      	params["specialId"] = xmldata_specialList.branch.(@name==specializationCB.selectedLabel).branchId;
        httpXmlExistingCourseDetail.send(params);
        Mask.show(commonFunction.getMessages('pleaseWait'));
    }
     
     private function insert_courseDetails():void {
	      var params:Object = {};
	      params["time"]=new Date();
	      params["progId"] = xmldata_progName.program.(@name==program_nameCB.selectedLabel).programId;
	      params["branchId"] = xmldata_branchName.branch.(@name==branch_nameCB.selectedLabel).branchId;
	      params["semCode"] = semester_nameCB.selectedItem.semesterCode;
	      params["specialId"] = xmldata_specialList.branch.(@name==specializationCB.selectedLabel).branchId;
	      params["courseData"]=course_details;
	      Mask.show(commonFunction.getMessages('pleaseWait'));
	      httpInsertProgCourseDetail.send(params);
     }
    
    private function resultHandler(event:ResultEvent):void{
        xmldata_progName=event.result as XML;        
        Mask.close();
        if(xmldata_progName.sessionConfirm == true){
        	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        	this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
        }
    }
    
    private function resultHandler_BranchList(event:ResultEvent):void{
        xmldata_branchName=event.result as XML;
        Mask.close();
        if(xmldata_branchName.sessionConfirm == true){
        	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        	this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
        }
        program_nameCB_changeHandler();
    }
	private function resultHandler_SemList(event:ResultEvent):void{
        xmldata_semesterList=event.result as XML;
        Mask.close();                
        if(xmldata_semesterList.sessionConfirm == true){
        	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        	this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
        }
        
        var semesterList:ArrayCollection = new ArrayCollection();
        for each(var o:Object in xmldata_semesterList.sem){
		 	semesterList.addItem({semesterName:o.semesterName,semesterCode:o.semesterCode});
		}
        
        semester_nameCB.dataProvider = semesterList;
        semester_nameCB.labelField = "semesterName";
        branch_nameCB_changeHandler();
	}
	
	private function resultHandler_SpecialList(event:ResultEvent):void{
    	xmldata_specialList=event.result as XML;
    	Mask.close();
        if(xmldata_specialList.sessionConfirm == true){
        	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        	this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
        }
      // specializationCB_changeHandler();
      
      	semester_nameCB_changeHandler();
    }
    
	private function resultHandler_CourseDetail(event:ResultEvent):void{
    	xmldata_courseDetail=event.result as XML;
    	Mask.close();
        if(xmldata_courseDetail.sessionConfirm == true){
        	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        	this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
        }
        //Alert.show(xmldata_courseDetail);
        get_existingCourseDetails();
     }
     
     private function resultHandler_ExistingCourseDetail(event:ResultEvent):void{
        xmldata_existingCourseDetail=event.result as XML;
        Mask.close();
        if(xmldata_existingCourseDetail.sessionConfirm == true){
        	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        	this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
        }
	}
    
	private function resultHandler_insertProgCourseDetail(event:ResultEvent):void{
    	Mask.close();
       	xmldata_insertProgramCourseDetail=event.result as XML;
       	if(xmldata_insertProgramCourseDetail.sessionConfirm == true){
        	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        	this.parentDocument.vStack.selectedIndex=0;
			this.parentDocument.loaderCanvas.removeAllChildren();
        		}
       		if(xmldata_insertProgramCourseDetail.message=='exist'){
       		//checkExistingRecords();
       		Alert.show(commonFunction.getConstants('alreadyExist'),
       		commonFunction.getConstants('exist'),0,null,null,errorIcon);
       }
       else if(xmldata_insertProgramCourseDetail.message=='success'){
       		Alert.show(commonFunction.getConstants('insertSuccessful'),
       		commonFunction.getConstants('success'),0,null,null,successIcon);
       		get_courseDetails();
       		courseDetailGrid.dataProvider.removeAll();
       		common_toAll();
       		submitButton.enabled=true;
       }
       else if(xmldata_insertProgramCourseDetail.message=='fail'){
       		Alert.show(commonFunction.getConstants('insertFailed'),
       		commonFunction.getConstants('fail'),0,null,null,errorIcon);
       }
       //resetButton_clickHandler();
    }
 
 
    
	private function faultHandler(event:FaultEvent):void{
     	Mask.close();
        Alert.show(event.fault.message,commonFunction.getConstants('errorInResult'),4,null,null,errorIcon);
    }
   

	protected function new_row_Button_clickHandler(event:MouseEvent):void{	
		if(DataGridDataExporter.validateEmptyRow(courseDetailGrid)){
			Alert.show(commonFunction.getConstants('fill_courseDetails'),commonFunction.getConstants('warning'),0,null,null,warningIcon);
		}
		else{
			initialGridData.addItem({course_code:"Click Here",description:"", course_type:"", category:"", group:"",availability:""});
		}	
		courseDetailGrid.setFocus();		
	}
			
	protected function remove_row_Button_clickHandler(event:MouseEvent):void{	
		initialGridData.removeItemAt(initialGridData.length-1);
	}
		
	protected function program_nameCB_changeHandler():void{
		branch_nameCB.enabled=true;
		semester_nameCB.enabled=false;
		specializationCB.enabled=false;
				
		branch_nameCB.selectedIndex=-1;
		semester_nameCB.selectedIndex=-1;
		specializationCB.selectedIndex=-1;
				
		common_toAll();
		gridCanvas.visible=false;
		gridCanvas2.visible=false;
	}


	protected function branch_nameCB_changeHandler():void{
		semester_nameCB.enabled=true;
        specializationCB.enabled=false;
        	
		semester_nameCB.selectedIndex=-1;
		specializationCB.selectedIndex=-1;
				
		common_toAll();
		gridCanvas.visible=false;
		gridCanvas2.visible=false;
	}


	protected function semester_nameCB_changeHandler():void{
		specializationCB.enabled=true;
		specializationCB.selectedIndex=-1;	
		common_toAll();
		gridCanvas.visible=false;
		gridCanvas2.visible=false;
	}

	protected function specializationCB_changeHandler(event:ListEvent):void{
		get_courseDetails();
		gridCanvas.visible=true;
		//gridCanvas2.visible=true;	
		common_toAll();
		submitButton.enabled=true;
		displayExistingRecords();
	}
			
	protected function displayExistingRecords():void{
		this.height=730;
		gridCanvas2.y=400;
		gridCanvas2.visible=true;
		submitButton.y=350;
		resetButton.y=350;
		cancelButton.y=350;
	}
	
	public function common_toAll():void{
		submitButton.enabled=false;
		initialGridData.removeAll();
		initialGridData.refresh();
		initialGridData.addItem({course_code:"Click Here",description:"", course_type:"", category:"", group:"",availability:""});
	}
			
	public function check_CourseCode(code:String):void{
		if(DataGridDataExporter.checkDuplicateCourseCode(courseDetailGrid,code)==false)
		Alert.show(commonFunction.getConstants('duplicateCourseCode'),commonFunction.getConstants('duplicate_warning'),0,null,null,warningIcon);
	}
			
	protected function submitButton_clickHandler(code:String):void{
		if(DataGridDataExporter.validateEmptyRow(courseDetailGrid)){
			Alert.show(commonFunction.getConstants('fill_courseDetails'),commonFunction.getConstants('warning'),0,null,null,warningIcon);
		}
		else if(DataGridDataExporter.validateCourseCode(courseDetailGrid,code)==true){
			Alert.show(commonFunction.getMessages('saveConfirmMessage'), commonFunction.getMessages('confirm'), 3, this, goForInsertion,questionIcon);
		}
		else{
			Alert.show(commonFunction.getConstants('duplicateCourseCode'),commonFunction.getConstants('duplicate_warning'),0,null,null,warningIcon);
		}
	}
			
		protected function goForInsertion(event:CloseEvent):void
		{
			if(event.detail==Alert.YES){
				course_details=new ArrayCollection;
				
				var courseDetails:Array=new Array();
				
				var courseData:Array= DataGridDataExporter.exportGridData(courseDetailGrid);

				for(var i:int = 0; i < courseData.length; i++){
						courseDetails[i] = new Array();			
					for(var j:int = 0; j < 6; j++){
						var s:String="";
						var cl:int=0;
						
						courseDetails[i][cl] = courseData[i][0];
						cl++;
						j=3;
						s=xmldata_courseDetail.group.(@code=='CRSCTG').(componentDes==courseData[i][j]).componentCode;
						if(s.length>0){
							courseDetails[i][cl]=s;
							cl++;
							j++;
						}
						s=xmldata_courseDetail.group.(@code=='CRSGRP').(componentDes==courseData[i][j]).componentCode;
						if(s.length>0){
							courseDetails[i][cl]=s;
							cl++;
							j++;
						}
						s=xmldata_courseDetail.group.(@code=='CRSOPT').(componentDes==courseData[i][j]).componentCode;
						if(s.length>0){
							courseDetails[i][cl]=s;
							cl++;
							j++;
						}
						courseDetails[i][cl] = course_availability.name.(@value==courseData[i][j]).code;
						cl++;
						courseDetails[i][cl] = ";";
					}
				}
				for(var k:int = 0; k < courseDetails.length; k++){
					course_details.addItem(courseDetails[k]);
					trace("course details="+courseDetails[k]);
					
				}
				//trace("course_details=="+course_details);
				insert_courseDetails();
			}
		}
			
		protected function resetButton_clickHandler(/*event:MouseEvent*/):void
		{
			program_nameCB.selectedIndex=-1;
			branch_nameCB.selectedIndex=-1;
			semester_nameCB.selectedIndex=-1;
			specializationCB.selectedIndex=-1;
			
			branch_nameCB.enabled=false;
			semester_nameCB.enabled=false;
			specializationCB.enabled=false;
			submitButton.enabled=false;
			
			common_toAll();
			gridCanvas.visible=false;
			gridCanvas2.visible=false;
		}
			
		protected function cancelButton_clickHandler(event:MouseEvent):void
		{
			document.visible=false;
		}
		
		protected function cancelButton_FocusOutHandler(event:FocusEvent):void
		{
			program_nameCB.setFocus();	
		}
			
		protected function courseDetailGrid_ItemRollOver(event:ListEvent):void
		{
			selectedRow = event.rowIndex;
			selectedColumn = event.columnIndex;
			
			var c:Number = event.columnIndex;
		    var r:Number = event.rowIndex;
			if(!(c==1 || c==2)){
		    	courseDetailGrid.editedItemPosition = {columnIndex:c, rowIndex:r};
		    }
		    
		}