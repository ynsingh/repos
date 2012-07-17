/**
 * @(#) ProgramCourseHeader.as
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

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.managers.PopUpManager;
[Bindable] private var courseHeadergird:ArrayCollection = new ArrayCollection();

			[Bindable]
			private var initialGridData:ArrayCollection = new ArrayCollection([
			{course_code:"", description:"" ,course_type:"", category:"", group:"", availability:""}
			]);
			
			public var selectedRow:Number=0;
			public var selectedColumn:Number=0;
			public var programCourseKey:String=null;
							
			public function getString(pass_key:String):String{
				return commonFunction.getConstants(pass_key);
			}
					public function inTopParent():void{
						get_ProgramCourseDetail_List(programCourseKey);
					}										
			
			protected function editButton_ClickHandler(event:MouseEvent):void
			{	
				var myAC4:ArrayCollection = new ArrayCollection();
	        	var gridData:ArrayCollection=programCourseGrid.dataProvider as ArrayCollection;
            	var colc:int=gridData.length;
            	var x:int=0;
            	
            	var selecgtedRowData:Array=new Array();
            	
            	for(var i:int=0;i<colc;i++)
            	{
            	 	var gridItem:Object=gridData.getItemAt(i);
            		if(gridItem.Select==true)
            		{
            			x=x+1;
            			selecgtedRowData[0]=gridItem.programName;
            			selecgtedRowData[1]=gridItem.branchName;
            			selecgtedRowData[2]=gridItem.specializationName;
            			selecgtedRowData[3]=gridItem.semesterCode;
            			
            			programCourseKey=xmldata_ProgramCourseHeaderDetail.programCourseKey.
            			(programName==gridItem.programName).(branchName==gridItem.branchName).
            			(specializationName==gridItem.specializationName).(semesterCode==gridItem.semesterCode).@name;
               		}
            	}
            	
            	if(x==0)
            	{
            		Alert.show(commonFunction.getConstants('select_for_updation'),"error",0,null,null,errorIcon);
            	}
            	else if(x==1)
            	{
            		get_ProgramCourseDetail_List(programCourseKey);
		        	var proCouEditWindowObj:ProgramCourseEditWindow=ProgramCourseEditWindow(PopUpManager.createPopUp(this,ProgramCourseEditWindow,true));
	            	
	            	//proCouEditWindowObj.title="Edit to: "+programCourseKey;
		            proCouEditWindowObj.program_name_lab.text=selecgtedRowData[0];
					proCouEditWindowObj.branch_name_lab.text=selecgtedRowData[1];
					proCouEditWindowObj.specialization_lab.text=selecgtedRowData[2];
					proCouEditWindowObj.semester_lab.text=selecgtedRowData[3];
					proCouEditWindowObj.courseDetails=courseDetails;
					proCouEditWindowObj.programCourseKey= programCourseKey;
					proCouEditWindowObj.topParentFun = inTopParent;
					PopUpManager.centerPopUp(proCouEditWindowObj);
	           	}
            	else
            	{
            		Alert.show(commonFunction.getConstants('select_only_one')+" "+x+" "+commonFunction.getConstants('records'),
            		commonFunction.getConstants('error'),4,null,null,errorIcon);
            	}
       	}
			
			protected var recordArr:Array=null;
			private var programcoursekeys:ArrayCollection=null;
			public var inActivateProgramCourseKey:String=null;
			protected function inactiveButton_clickHandler(event:MouseEvent):void
			{	
				   recordArr=new Array();
				   programcoursekeys=new ArrayCollection;
					var gridData:ArrayCollection=programCourseGrid.dataProvider as ArrayCollection;
					var rows:int=gridData.length;
					var j:int=0;
					for(var i:int=0;i<rows;i++)
            		{
            	 		var gridItem:Object=gridData.getItemAt(i);
            			if(gridItem.Select==true)
            			{
            		inActivateProgramCourseKey=xmldata_ProgramCourseHeaderDetail.programCourseKey.
            			(programName==gridItem.programName).(branchName==gridItem.branchName).
            			(specializationName==gridItem.specializationName).(semesterCode==gridItem.semesterCode).@name;

            				recordArr[j]=inActivateProgramCourseKey;//replaces i from j in both lines
           					programcoursekeys.addItem(recordArr[j]);
           					j++;
            			}
               		}
				   
				   if(programcoursekeys.length==0){
				   		Alert.show(commonFunction.getConstants('select_tochange_status'),commonFunction.getConstants('confirm'),4,null,null,errorIcon);
				   }
				   else{
				  		Alert.show(commonFunction.getConstants('want_tochange_status'), commonFunction.getConstants('confirm'), 3, this, deleteForm, questionIcon);
					   }

			}
			
			public function deleteForm(event:CloseEvent):void
			{
				if(event.detail == Alert.YES)
				{
					inactivate_ProgramHeader(programcoursekeys);
				} 
			}
		
			private function resultProgramCourseHeaderHandler(event:ResultEvent):void{
        		xmldata_ProgramCourseHeaderDetail=event.result as XML;
        		Mask.close();
         		if(xmldata_ProgramCourseHeaderDetail.sessionConfirm == true){
        			Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
        			this.parentDocument.vStack.selectedIndex=0;
					this.parentDocument.loaderCanvas.removeAllChildren();
        		}
       			//  initApp();
         		courseHeadergird.removeAll();//Add by Devendra
         		for each(var s:Object in xmldata_ProgramCourseHeaderDetail.programCourseKey){
                	courseHeadergird.addItem({Select:false,programName:s.programName,branchName:s.branchName,
                  	specializationName:s.specializationName,semesterCode:s.semesterCode,
                  	semesterStatus:s.semesterStatus});
                }
                programCourseGrid.dataProvider=courseHeadergird;
      		}

	
			protected function resetButton_clickHandler(event:MouseEvent):void
			{
				var a:ArrayCollection=programCourseGrid.dataProvider as ArrayCollection;
				for each(var obj:Object in a)
				{
					obj.Select=false;
				}
				
				programCourseGrid.dataProvider=a;
			}
			
			protected function cancelButton_clickHandler(event:MouseEvent):void
			{
				document.visible=false;
			}
			