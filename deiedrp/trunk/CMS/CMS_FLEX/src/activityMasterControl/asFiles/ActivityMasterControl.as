/**
 * @(#) ActivityMasterControl.as
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
import activityMasterControl.ConfirmWindow;

import common.Mask;
import common.commonFunction;

import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.managers.PopUpManager;
import mx.printing.FlexPrintJob;
import mx.printing.FlexPrintJobScaleType;
import mx.printing.PrintDataGrid;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.validators.Validator;
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/reset.png")]private var resetIcon:Class;
[Embed(source="/images/question1.png")]private var questionIcon:Class;
public var forActivities:String="";
public var forConfirmActivty:String="";


protected var processGridList:ArrayCollection;
protected var activityGridList:ArrayCollection; 

/** This function calls on creation complete and It set's the selectable Renge of 
 * Date field for process start date as from 1 month before to 1 month sfter from
 * current date,and also calls a function drawFocousTo */
 
 
[Bindable] private var xmldata_entityName:XML;
[Bindable] private var xmldata_processName:XML;
[Bindable] private var xmldata_semesterName:XML;
[Bindable] private var xmldata_programName:XML;
[Bindable] private var xmldata_branchName:XML;
[Bindable] private var xmldata_specializationName:XML;
[Bindable] private var xmldata_processGridName:XML;
[Bindable] private var xmldata_activityGridName:XML;

		public var url_DNS:String ="";
    	[Bindable] public var urlEntityList:String = "";
    	[Bindable] public var urlProcessList:String = "";
    	[Bindable] public var urlSemesterList:String = "";
    	[Bindable] public var urlProgramList:String = "";
    	[Bindable] public var urlBranchList:String = "";
    	[Bindable] public var urlSpecializationList:String = "";
    	[Bindable] public var urlProcessGridList:String="";
    	[Bindable] public var urlActivityGridList:String="";
    	[Bindable] public var d:int=0;
    	
    	
public function getString(pass_key:String):String{
		
		return resourceManager.getString('ApplicationResource', pass_key);
		
		}




		public function setDateRangeAndFocus():void
		{
			
			
		var startMonth:Date=new Date();
		startMonth.setMonth(startMonth.getMonth()-1);
		var endMonth:Date=new Date();
		endMonth.setMonth(endMonth.getMonth()+1);

		drawFocusTo(entityCombo);
		
					url_DNS = commonFunction.getConstants('url');
					//Check CourseMaster Controller: Amir Code
		    		urlEntityList = url_DNS+"/consolidatedChart/getEntityList.htm";
		    		
		    		urlProcessList = url_DNS+"/startActivity/getProcesses.htm";
		    		
		    		urlSemesterList = url_DNS+"/startActivity/getSemesterList.htm";
		    		
		    		urlProgramList = url_DNS+"/associatecoursewithinstructor/programList.htm";
		    		
		    		urlBranchList = url_DNS+"/associatecoursewithinstructor/branchList.htm";
		    		
		    		urlSpecializationList = url_DNS+"/associatecoursewithinstructor/specializationList.htm";
		    		
		    		urlProcessGridList = url_DNS+"/startActivity/processList.htm";
		    		
		    		urlActivityGridList = url_DNS+"/startActivity/activityList.htm";
		 	    	
		 	    	Mask.show(commonFunction.getMessages('pleaseWait'));	
		    		get_Entity_data();
		    		
		    		get_Process_data();
		    		
		    		get_Semester_data();
		}


	public function get_Entity_data():void
	{
		var params:Object={};
		params['time']=new Date;
		params['mode']="all";
		
	 httpXmlEntityList.send(params);
	}
	
	public function get_Process_data():void
	{
	  httpXmlProcessList.send(new Date);
	}

	public function get_Semester_data():void
	{
	  httpXmlSemesterList.send();
	}

 	private function resultHandler(event:ResultEvent):void{
        xmldata_entityName=event.result as XML;
        //Alert.show(xmldata_entityName);
       entityCombo.dataProvider=xmldata_entityName.Details.name;
        //get_program_data();
    }
    
    private function processResultHandler(event:ResultEvent):void{
        xmldata_processName=event.result as XML;
      //  Alert.show(xmldata_processName);
        processCombo.dataProvider=xmldata_processName.Details.name;
    }
    
    private function semesterResultHandler(event:ResultEvent):void{
        xmldata_semesterName=event.result as XML;
        semesterCombo.dataProvider=xmldata_semesterName.Details.name;
        Mask.close();
    }
    
    private function programResultHandler(event:ResultEvent):void{
    	
        xmldata_programName=event.result as XML;
       // Alert.show(xmldata_programName+"dedd");
        programCombo.dataProvider=xmldata_programName.program.programName;
        Mask.close();
        //get_branch_data();
    }
    
    private function branchResultHandler(event:ResultEvent):void{
        xmldata_branchName=event.result as XML;
        branchCombo.dataProvider=xmldata_branchName.branch.branchName;
         Mask.close();
       // get_specialization_data();
    }
    
    private function specializationResultHandler(event:ResultEvent):void{
        xmldata_specializationName=event.result as XML;
       // Alert.show(xmldata_specializationName+"");
        specializationCombo.dataProvider=xmldata_specializationName.specialization.specializationName;
         Mask.close();
    }
    
    private function faultHandler(event:FaultEvent):void{
         mx.controls.Alert.show(event.fault.message,getString('errorInResult'));
          Mask.close();
    }


	public function get_program_data():void
	{
	programCombo.enabled=true;
	
	 //makeNullOnChange();
	 var param:Object=new Object();
	 param["entityId"]=xmldata_entityName.Details.(name==entityCombo.selectedLabel).id;
	
	 Mask.show(commonFunction.getMessages('pleaseWait'));
	 httpXmlProgramList.send(param);
	 
	}
	
	public function get_branch_data():void
	{
		branchCombo.enabled=true;
     //makeNullOnChange();
     var param:Object=new Object();
	 param["programId"]=xmldata_programName.program.(programName==programCombo.selectedLabel).programId;
	 param["entityId"]=xmldata_entityName.Details.(name==entityCombo.selectedLabel).id;
    Mask.show(commonFunction.getMessages('pleaseWait'));
	  httpXmlBranchList.send(param);
	}

	public function get_specialization_data():void
	{
		specializationCombo.enabled=true;
    //makeNullOnChange();
    var param:Object=new Object();
	param["programId"]=xmldata_programName.program.(programName==programCombo.selectedLabel).programId;
	param["entityId"]=xmldata_entityName.Details.(name==entityCombo.selectedLabel).id;
	param["branchId"]=xmldata_branchName.branch.(branchName==branchCombo.selectedLabel).branchId;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	  httpXmlSpecializationList.send(param);
	}
	
//This function Set Focus on 1st Field Entity combobox
public function drawFocusTo(object:UIComponent):void
            {
                object.setFocus();
                object.drawFocus(true);
            }
            
/**This function Validate that all mandatory fields required has filled or not
 * and on validation pass calls a function showProcessGrid*/          
public function validationforOk():void
{
	if(Validator.validateAll([entityvalidator,semestervalidator,processvalidator,programComboValidator]).length!=0)
		{
		Alert.show((resourceManager.getString('Messages','firstSelectallMandatoryFields')),(resourceManager.getString('Messages','error')),0,null,null,errorIcon);		
	    }
	
	else{
		showProcessGrid();		
	    }
}

//This function show all codes and calls function initApp to show grid data with pagination
public function showProcessGrid():void 
{
   	var show:String=" ";
	 var param:Object=new Object();
	param["programId"]=xmldata_programName.program.(programName==programCombo.selectedLabel).programId;
	param["entityId"]=xmldata_entityName.Details.(name==entityCombo.selectedLabel).id;
	param["semesterId"]=xmldata_semesterName.Details.(name==semesterCombo.selectedLabel).id;
	param["processId"]=xmldata_processName.Details.(name==processCombo.selectedLabel).id;
	
	if(branchCombo.selectedIndex==-1){
		param["branchId"]="";
	}
	else{
		param["branchId"]=xmldata_branchName.branch.(branchName==branchCombo.selectedLabel).branchId;
	
	}
	if(specializationCombo.selectedIndex==-1){
	param["specializationId"]="";
	}
	else{
		param["specializationId"]=xmldata_specializationName.specialization.(specializationName==specializationCombo.selectedLabel).specializationId;
		
	}
	processGridCanvas.visible=true;
	
	entityProcesSemesterforProcess.text="Entity:"+entityCombo.text+"Process:"+processCombo.text+" Semester: "+semesterCombo.text;
	Mask.show(commonFunction.getMessages('pleaseWait'));
	httpXmlProcessGridList.send(param);
	//showDataInProcessGrid();
}
//result handler for Process grid
 private function processGridResultHandler(event:ResultEvent):void{
        xmldata_processGridName=event.result as XML;
    
        
	processGridList= new ArrayCollection();
	for each (var o:Object in xmldata_processGridName.activityMaster)
	{
				var st:String="";
		if(o.status=="RDY"){
			st="Ready";
		}else if(o.status=="ERR"){
			st="Error";
		}else if(o.status=="COM"){
			st="Completed";
		}
		processGridList.addItem({select:false,programname:o.programName, branchname:o.branchName, specilizationname:o.specializationName,
		semesterstartdate:o.semesterStartDate, semesterenddate:o.semesterEndDate , status:st,programCourseKey:o.programCourseKey,
		programId:o.programId,branchId:o.branchId,specializationId:o.specializationId});
	}
	
        processGrid.dataProvider=processGridList;
        Mask.close();
       // get_specialization_data();
    }
//This function make changes on change of entity like make refresh grid,textboxes etc
protected function entityChangeHandler():void
			{
				get_program_data();
				branchCombo.enabled=false;
				specializationCombo.enabled=false;
				processGrid.dataProvider=null;
				entityProcesSemesterforProcess.text="";
				
				
			}

	
//This function make changes on change of semester like make refresh grid,textboxes etc
protected function semesterChangeHandler():void
			{
				programCombo.selectedIndex=-1;
				branchCombo.selectedIndex=-1;
				specializationCombo.selectedIndex=-1;
				branchCombo.enabled=false;
				specializationCombo.enabled=false;
				
				processGrid.dataProvider=null;
				entityProcesSemesterforProcess.text="";
			
			}	
			
//This function make changes on change of program like make refresh grid,textboxes etc					
protected function programChangeHandler():void
			{
				branchCombo.enabled=true;
				specializationCombo.enabled=true;
				branchCombo.selectedIndex=-1;
				specializationCombo.selectedIndex=-1;
				get_branch_data();
				processGrid.dataProvider=null;
			}	
			
//This function make changes on change of branch like make refresh grid etc			
protected function branchChangeHandler():void
			{
				specializationCombo.selectedIndex=-1;
				get_specialization_data();
			    processGrid.dataProvider=null;
			}			
			
//This function refresh processgrid on change of specilization 		
protected function specilizationChangeHandler():void
			{
				processGrid.dataProvider=null;
			
			}

//This function make changes on change of process like make refresh grid etc
protected function processChangeHandler():void
			{
			processGrid.dataProvider=null;
			entityProcesSemesterforProcess.text="";
			}

//This function make changes on change of process start date like make refresh grid etc
protected function processDateChangeHandler():void
			{
				processGrid.dataProvider=null;
			}

/** This function show codes for activitygrid ,make some text choosing disable
 * and calls a function initApp1 for activitygrid data with pagination*/
public function showActivityGrid():void
{
	StartButton.enabled=false;
	var processGridData:ArrayCollection=processGrid.dataProvider as ArrayCollection;
    var gridLength:int=processGridData.length;
    
	 var param:Object=new Object();
	param["entityId"]=xmldata_entityName.Details.(name==entityCombo.selectedLabel).id;
    
    for(var e:int=0;e<gridLength;e++)
    {
      var gridItem:Object=processGridData.getItemAt(e);
      if(gridItem.select==true)
      {

	
      	
	param["semesterStartDate"]=gridItem.semesterstartdate;
	param["semesterEndDate"]=gridItem.semesterenddate;
	param["processId"]=xmldata_processName.Details.(name==processCombo.selectedLabel).id;
	param["processStatus"]=	gridItem.statuscode;
    param["programCourseKey"]=gridItem.programCourseKey;	  	
		
	  }
   }
		processGridCanvas.visible=false;
    	activityGridCanvas.visible=true;
 		okButton.enabled=false;
    	entityCombo.enabled=false;
    	semesterCombo.enabled=false;
    	processCombo.enabled=false;
    	
    	programCombo.enabled=false;
    	branchCombo.enabled=false;
    	specializationCombo.enabled=false;
    	entityProcesSemesterforActivity.text=entityProcesSemesterforProcess.text;
    	Mask.show(commonFunction.getMessages('pleaseWait'));
    	httpXmlActivityGridList.send(param);
    	
    	
}   	

//result handler for Process grid
 private function activityGridResultHandler(event:ResultEvent):void{
        xmldata_activityGridName=event.result as XML;
      //  Alert.show(xmldata_activityGridName+"");
       var processGridData:ArrayCollection=processGrid.dataProvider as ArrayCollection;
        for(var e:int=0;e<processGridData.length;e++)
        {
          var gridItem:Object=processGridData.getItemAt(e);
          if(gridItem.select==true)
          {
	     var prog:String=gridItem.programname;
	     var bran:String=gridItem.branchname;
	var spcl:String=gridItem.specilizationname;
	var semSD:String=gridItem.semesterstartdate;
	var semED:String=gridItem.semesterenddate

	var pck:String=gridItem.programCourseKey;
	var program:String=gridItem.programId;
	var branch:String=gridItem.branchId;
	var specialization:String=gridItem.specializationId;
    	}
   }
     
        
	activityGridList= new ArrayCollection();
	for each (var o:Object in xmldata_activityGridName.activityMaster)
	{
		var sts:String="";
		if(o.status=="RDY"){
			sts="Ready";
		}else if(o.status=="ERR"){
			sts="Error";
		}else if(o.status=="COM"){
			sts="Completed";
		}
		activityGridList.addItem({select:false,activityprogramname:prog, activitybranchname:bran, activityspecilizationname:spcl,
		activitysemesterstartdate:semSD,activitysemesterenddate:semED ,activitystage:o.activityName,activitystagesequence:o.activitySequence, activitystatus:sts,programCourseKey:pck,
		activityCode:o.activityCode,programId:program,branchId:branch,specializationId:specialization
		});
	}
	
        activitiesGrid.dataProvider=activityGridList;
        Mask.close();
     // activitiesGrid.dataProvider=activitieslist;
       // get_specialization_data();
    }
   
   

    
/** This function Checks selected & allow only that activity to be start
 * 1-Which have stage sequence=1 and status != completed
 * 2-which have its previous avtivity(According to stage sequence) completed
 * and itself doesn't have status=completed
 * If any of above condition fulfills then calls a function openPopUpToStartActivity*/
public function forStartActivity():void
{
	     rejectedGridCanvas.visible=false;
         rejectedGrid.dataProvider=null;
		var activitiesGridData:ArrayCollection=activitiesGrid.dataProvider as ArrayCollection;
        
        var forStageSequence:int;
        var forPrevStageSequence:int;
        
        for(var d:int=0;d<activitiesGridData.length;d++){
        	
           var gridItem:Object=activitiesGridData.getItemAt(d);
           
           	if(gridItem.select==true)
           	{
           	   forStageSequence=gridItem.activitystagesequence;
           	   forPrevStageSequence=forStageSequence-1;
		   	}
        }
      
      if(forStageSequence==1)
      {
        for(var d:int=0;d<activitiesGridData.length;d++){
        	
           var gridItem:Object=activitiesGridData.getItemAt(d);
           
           	if(gridItem.activitystagesequence==1)
           	{
           	   if(gridItem.activitystatus.toString()=="Completed"){
           	   	Alert.show((resourceManager.getString('Messages','activityCompleted')),(resourceManager.getString('Messages','error')),0,null,null,errorIcon);
           	   	}
           	   else{
           	   	openPopUpToStartActivity();
           	   }
		   	}
        }
      }
      else 
      {
      	for(var d:int=0;d<activitiesGridData.length;d++){
        	var gridItem:Object=activitiesGridData.getItemAt(d);
        	if(gridItem.activitystagesequence==forStageSequence)
           	{
           	   if(gridItem.activitystatus.toString()=="Completed"){
      				Alert.show((resourceManager.getString('Messages','activityCompleted')),(resourceManager.getString('Messages','error')),0,null,null,errorIcon);
           		}
	            else{
	            	for(var d1:int=0;d1<activitiesGridData.length;d1++){
	        	         var gridItems:Object=activitiesGridData.getItemAt(d1);
	     				 if(gridItems.activitystagesequence==forPrevStageSequence){
	     					if(gridItems.activitystatus.toString()!="Completed"){
	     						Alert.show((resourceManager.getString('Messages','previousActivitynotCompleted')),(resourceManager.getString('Messages','error')),0,null,null,errorIcon);	
	     					}
	     					else{
	     						
	     						openPopUpToStartActivity();		
	     					}
	     				}
	     			}
	           	}
      		}
		}
  		}
}

/**This functions open popup for confirmation to start selected activity
 * and pass all codes to popup in a variable forConfirmActivty*/
private function openPopUpToStartActivity():void 
{
	rejectedGridCanvas.visible=false;
	rejectedGrid.dataProvider=null;
	var activitiesGridData:ArrayCollection=activitiesGrid.dataProvider as ArrayCollection;
    var gridLength:int=activitiesGridData.length;
    for(var d:int=0;d<gridLength;d++)
    {
       var gridItem:Object=activitiesGridData.getItemAt(d);
       if(gridItem.select==true)
       {
    	var popUpWindow:ConfirmWindow=ConfirmWindow(PopUpManager.createPopUp(this,ConfirmWindow,true));
    	
		popUpWindow.programforConfirmWindow.text=gridItem.activityprogramname;
		popUpWindow.branchforConfirmWindow.text=gridItem.activitybranchname;
		popUpWindow.specilizationforConfirmWindow.text=gridItem.activityspecilizationname;
		popUpWindow.semesterEndDateforConfirmWindow.text=gridItem.activitysemesterenddate;
		popUpWindow.semesterStartDateforConfirmWindow.text=gridItem.activitysemesterstartdate;
    	popUpWindow.stageforConfirmWindow.text=gridItem.activitystage;
    	popUpWindow.stageSequenceforConfirmWindow.text=gridItem.activitystagesequence;
    	popUpWindow.statusforConfirmWindow.text=gridItem.activitystatus;  	
	    
	    popUpWindow.entity=xmldata_entityName.Details.(name==entityCombo.selectedLabel).id;
	    popUpWindow.entityName=entityCombo.selectedLabel;
	    
		popUpWindow.process=xmldata_processName.Details.(name==processCombo.selectedLabel).id;
		
		popUpWindow.semester=xmldata_semesterName.Details.(name==semesterCombo.selectedLabel).id;
		popUpWindow.semesterName=xmldata_semesterName.Details.(name==semesterCombo.selectedLabel).name;
		
		popUpWindow.programCourseKey=gridItem.programCourseKey;
    	popUpWindow.ssd=gridItem.activitysemesterstartdate;
    	popUpWindow.sed=gridItem.activitysemesterenddate;
    	popUpWindow.activityCode=gridItem.activityCode;
    	popUpWindow.activityName=gridItem.activitystage;
    	popUpWindow.actvitySeq=gridItem.activitystagesequence;
		popUpWindow.program=gridItem.programId;
		popUpWindow.branch=gridItem.branchId;
		popUpWindow.specialization=gridItem.specializationId;
		var sts:String="";
		if(gridItem.activitystatus=="Ready"){
			sts="RDY";
		}else if(gridItem.activitystatus=="Error"){
			sts="ERR";
		}else if(gridItem.activitystatus=="Completed"){
			sts="COM";
		}
		popUpWindow.activityStatus=sts;
		popUpWindow.buttonFunction=showActivityGrid;
		popUpWindow.gridFunction=showRGrid;
		PopUpManager.centerPopUp(popUpWindow);	    
	    }
    }
}

public function showRGrid(record:ArrayCollection):void{
	
	//Alert.show(record.length+"rohit");
	rejectedGridCanvas.visible=true;
	rejectedGrid.dataProvider=record;
	d=1;
	openPDF();
	
	
	
}


//This function make back from activitygrid to processgrid
public function backFunction():void 
{
	processGridCanvas.visible=true;
    activityGridCanvas.visible=false;
    rejectedGridCanvas.visible=false;
    rejectedGrid.dataProvider=null;
    entityCombo.enabled=true;
    semesterCombo.enabled=true;
    processCombo.enabled=true;
    
    programCombo.enabled=true;
    okButton.enabled=true;
    getButton.enabled=false;
  //  StartButton.enabled=false;
    if(programCombo.selectedIndex==-1)
    {
        branchCombo.enabled=false;
        specializationCombo.enabled=false;
    }
    else
    {
        branchCombo.enabled=true;
        specializationCombo.enabled=true;
    } 
		var processGridData:ArrayCollection=processGrid.dataProvider as ArrayCollection;
        var gridLength:int=processGridData.length;
        for(var c:int=0;c<gridLength;c++)
        {
           var processGridItem:Object=processGridData.getItemAt(c);
           if(processGridItem.select==true)
           {
           	processGridItem.select=false;
           	processGridData.setItemAt(processGridItem,c);
           	processGrid.dataProvider=processGridData;
           }
 		}
        var activitiesGridData:ArrayCollection=activitiesGrid.dataProvider as ArrayCollection;
        var countgridLength:int=activitiesGridData.length;
        for(var e:int=0;e<countgridLength;e++)
        {
           var activityGridItem:Object=activitiesGridData.getItemAt(e);
           if(activityGridItem.select==true)
           {
              activityGridItem.select=false;
              activitiesGridData.setItemAt(activityGridItem,e);
              activitiesGrid.dataProvider=activitiesGridData;
           }
        }
}

private function printGridData():void{

const printJob:FlexPrintJob = new FlexPrintJob();

if ( printJob.start() )
{
const printDataGrid:PrintDataGrid = new PrintDataGrid();
this.addChild(printDataGrid);
printDataGrid.width = printJob.pageWidth-80;
printDataGrid.height = printJob.pageHeight;

printDataGrid.columns = [new DataGridColumn("rollno"),new DataGridColumn("reason")];
printDataGrid.dataProvider = rejectedGrid.dataProvider;
printDataGrid.visible = false;
while(printDataGrid.rowCount)
{
	printJob.addObject(printDataGrid,FlexPrintJobScaleType.SHOW_ALL);
	printDataGrid.nextPage();
}
printDataGrid.styleDeclaration.setStyle("align","center");
printJob.send();
this.removeChild(printDataGrid);
}
			}		


public function sessionDataResultHandler(event:ResultEvent):void{
	
	//Mask.close();
	var ss:XML=event.result as XML;
	
	var s1:String=ss.universityId;
	var s2:String=ss.SessionStartDate;
	var s3:String=ss.SessionEndDate;
	var s4:String=s2.substring(0,4)+"-"+s3.substring(0,4);
	var s5:String=xmldata_semesterName.Details.(name==semesterCombo.selectedLabel).id;
	var s6:String="";
	var s7:String="";
	
	var activitiesGridData:ArrayCollection=commonFunction.getSelectedRowData(activitiesGrid);
    
    for each(var gridItem:Object in activitiesGridData){
    	
		s6=gridItem.programCourseKey;
    
    	s7=gridItem.activityCode;
    	
    	
    }
       
    var pdfurl:String=commonFunction.getConstants('url')+"/"+"RejectedRecords"+"/"+s1+"/"+s4+"/"+s5+"/"+s7+"_"+s6+".pdf";
   // Alert.show(pdfurl);
	navigateToURL(new URLRequest(pdfurl));
	//Alert.show(result.substr(0,result.indexOf("|")),commonFunction.getMessages('success'),0,null,null,successIcon);
	if(d==1){
		showActivityGrid();
		pdfbutton.enabled=false;
		StartButton.enabled=false;
	}
}
  
        
	

public function openPDF():void{
	
	//Mask.show("Please Wait");
	sessionDataList.send(new Date);
	
}

//This function removes whole page
public function cancelFunction():void
{
   this.parentDocument.loaderCanvas.removeAllChildren();
}
