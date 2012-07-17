//<!--* @Date :30/12/2011
//* Version 1.0
//    Author :Arush 
//* Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
//* All Rights Reserved.
//*
//* Redistribution and use in source and binary forms, with or
//* without modification, are permitted provided that the following
//* conditions are met:
//*
//* Redistributions of source code must retain the above copyright
//* notice, this list of conditions and the following disclaimer.
//*
//* Redistribution in binary form must reproducuce the above copyright
//* notice, this list of conditions and the following disclaimer in
//* the documentation and/or other materials provided with the
//* distribution.
//*
//*
//* THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
//* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
//* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
//* DISCLAIMED. IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
//* FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
//* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
//* OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
//* BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
//* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
//* OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
//* EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//*
//* Contributors: Members of EdRP, Dayalbagh Educational Institute
//*/
//// -->
// ActionScript file
import common.commonFunction;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.managers.PopUpManager;
import mx.rpc.events.ResultEvent;

import studentprogramswitch.StudentProgramSwitch;

[Bindable] protected var urlPrefix:String;

public var switchXML : XML ;
public var entitycheck:Boolean = true;
 	 public var programcheck:Boolean = true;
[Bindable] public var searchString:String='' ;

public var labledata:ArrayCollection;
public var obj : Object ;
public var semesterdateXML : XML ;
public var studentXML : XML ;
public var semesterdata:Object = new Object;
public var parm:Object ;



public var addWindow:StudentProgramSwitch ;

[Bindable]
		public var switchcollection :ArrayCollection ;

 	 public var firstTime:Boolean = true;

protected function LoadSwitches(switchXML:XML){



 switchcollection= new ArrayCollection ;

for each (var obj:Object in switchXML.switchinfo){
	
	
switchcollection.addItem({
	
fmentid:obj.FmentityId ,	
fment:obj.FmentityIdName,

fmprogramId:obj.FmprogramId,
fmpgm:obj.FmprogramIdName,

fmbranchId:obj.FmbranchId,
fmbrn:obj.FmbranchIdName,

fmspecializationId:obj.FmspecializationId, 
fmspc:obj.FmspecializationIdName,

fmsemesterCode:obj.FmsemesterCode,
fmsem:obj.FmsemesterCodeName,

toentityId:obj.ToentityId,
toent:obj.ToentityIdName,

toprogramId:obj.ToprogramId,
topgm:obj.ToprogramIdName,

tobranchId:obj.TobranchId,
tobrn:obj.TobranchIdName,
 
tospecializationId:obj.TospecializationId,  
tospc:obj.TospecializationIdName,

tosemesterCode:obj.TosemesterCode,
tosem:obj.TosemesterCodeName
})
;


programSwitchGrid.dataProvider =switchcollection;	
	


//for each(var obj:Object in progXml.Details){
//	prog.addItem({name:obj.programName,id:obj.programId});
//}
//programCombo.dataProvider=prog;
//programCombo.labelField="name";	
}	
}

protected function applicationCreationHandler():void
{
	urlPrefix=commonFunction.getConstants('url')+"/programswitches/";

	getSwitches.send();
	
	
	
}

protected function OngetSwitchesSuccess(event:ResultEvent):void
{
	//switchXML = new XML;
	
	//switchXML = event.result as XML; 
	switchXML = event.result as XML;
	
	
	LoadSwitches(switchXML);
	
	 
		
}

protected function onFailure(event)
{

	Alert.show("request failed");
}



// private function filterFunc(item:Object):Boolean {
//              return item.text().match(new RegExp("^" +entity.text , "i"));
// }
public function LoadStudentforSwitch():void{
	
	labledata = new ArrayCollection;
	labledata=commonFunction.getSelectedRowData(programSwitchGrid);
	 parm = new Object;
	
		
	for each(var obj:Object in labledata){
		 
	 parm["fmbranchId"]= obj.fmbranchId ;
	 parm["fmspecializationId"]=obj.fmspecializationId ;
	 parm["fmprogramId"] = obj.fmprogramId;
	 parm["fmsemesterCode"] = obj.fmsemesterCode ;
	 parm["fmentityId"]= obj.fmentid ;
	 
	 parm["tobranchId"]= obj.tobranchId ;
	 parm["tospecializationId"]=obj.tospecializationId ;
	 parm["toprogramId"] = obj.toprogramId;
	 parm["tosemesterCode"] = obj.tosemesterCode ;
	 parm["toentityId"]= obj.toentityId;
	 semesterdata = obj ; // store semester data  
	 
		
	}
	
	getsemesterdate.send(parm);
	
	//addWindow.fment =labeldata.
	

}

protected function getsemesterdateSuccess(event:ResultEvent):void

{
	semesterdateXML = event.result as XML;
	
	parm["fmsemstdt"]=String(semesterdateXML.semesterdate.fmsemesterstartdate);
	parm["fmsemeddt"]=String(semesterdateXML.semesterdate.fmsemesterenddate);
	parm["tosemstdt"]=String(semesterdateXML.semesterdate.tosemesterstartdate);
	parm["tosemeddt"]=String(semesterdateXML.semesterdate.tosemesterenddate);
	parm["fmprogramcoursekey"]=String(semesterdateXML.semesterdate.fmprogramcoursekey);
	
	getstudentlist.send(parm);
	 
}
 
 
 
 protected function getstudentlistSuccess(event:ResultEvent):void{
 	studentXML = event.result as XML;
 	
 	//Alert.show("studentXML"+studentXML);
 	var addWindow:StudentProgramSwitch=StudentProgramSwitch(PopUpManager.createPopUp(this,StudentProgramSwitch,true));
 	// addWindow.ection = new ArrayCollection ;
 	
 	for each(var obj:Object in studentXML.student){
 	var ss:Boolean=false;
 	var cs:Boolean=false;
 	var ctr:Boolean=false;
 	
 	if (String(obj.switchstatus) =="NOR" && String(obj.processstatus)==""  ){
				ss = true ;	}else{
					 ss = false ;
				
						}

	if (String(obj.switchstatus) =="SWT" && String(obj.processstatus)==""  ){
				cs = true ;	}else{
					 cs = false ;
					}

 	if ( String(obj.processstatus)=="PRC"  ){
				ctr = true ;	}else{
					 ctr = false ;
					}
 	
 	
 	
 		addWindow.studentcollection.addItem({select:false,rollNo:obj.rollNumber ,
 		name:obj.Studentname, swsts:obj.switchstatus, processstatus:obj.processstatus,
 		registrationduedate:obj.registrationduedate,ss:ss,cs:cs,ctr:ctr})
 	}
 	
 	 	
 	
      
    addWindow.parmstudent = parm;
        
 	addWindow.studentgrid.dataProvider=addWindow.studentcollection ;
 	
 	addWindow.urlPrefix = urlPrefix ;
 	
 	 addWindow.fmbrn.text=semesterdata.fmbrn;
 	 //addWindow.fmbranchId = semesterdata.fmbranchId ;
 	 
	 addWindow.fment.text = semesterdata.fment ;
	 //addWindow.fmentid = semesterdata.fmentid ;
	 
	 addWindow.fmpgm.text = semesterdata.fmpgm ;
	 //addWindow.fmprogramId =semesterdata.fmprogramId ;
	 
	 addWindow.fmsem.text = semesterdata.fmsem;
	 //addWindow.fmsemesterCode =semesterdata.fmsemesterCode ;
	 
	 
	 addWindow.fmspc.text = semesterdata.fmspc ;
	 //addWindow.fmspecializationId = semesterdata.fmspecializationId ;
	
	 
	 addWindow.tobrn.text=semesterdata.tobrn;
	 addWindow.toent.text = semesterdata.toent ;
	 addWindow.topgm.text = semesterdata.topgm ;
	 addWindow.tosem.text = semesterdata.tosem;
	 addWindow.tospc.text = semesterdata.tospc ;
	 
	 // populate  semester dates and convert to format DD.MM.YY
	 
	 
	addWindow.fmsemstdt.text = addWindow.fmtdate.format(String(semesterdateXML.semesterdate.fmsemesterstartdate)) ;
	addWindow.fmsemeddt.text = addWindow.fmtdate.format(String(semesterdateXML.semesterdate.fmsemesterenddate)) ;
	addWindow.tosemstdt.text = addWindow.fmtdate.format(String(semesterdateXML.semesterdate.tosemesterstartdate)) ; 
	addWindow.tosemeddt.text = addWindow.fmtdate.format(String(semesterdateXML.semesterdate.tosemesterenddate)) ; 
	
	
	// addWindow.fmsemstdt = semesterdateXML.
	PopUpManager.centerPopUp(addWindow);	
 	
 }
  
  

   private function filter():void {
                switchcollection.filterFunction = filterswitchCollection;
                
                
                switchcollection.refresh();
            }
           
            private function filterswitchCollection(item:Object):Boolean {
                var entityString:String = entity.text.toLowerCase();
                var pgmString:String = program.text.toLowerCase();
                
                if (entityString.length>0){
                	searchString =entityString ;
                	      var itemName:String = item.fment.toLocaleLowerCase()  ;
                	         
                } else {
                	searchString =pgmString ;
             		itemName = item.fmpgm.toLocaleLowerCase()  ;
                }
                 
    			  return itemName.indexOf(searchString) > -1;
      
             
                //itemName.toLocaleLowerCase();
                //= (item.fment as String).toLowerCase();
                
                
             
            }
           
 
  private function clearEntityInput():void {
               
                if (entitycheck== true )
                {
                    entity.text = "";
                   
                    entitycheck = false;
                }
            }
            
            private function clearProgramInput():void {
               
                if (programcheck == true )
                {
                    program.text = "";
                   
                    programcheck = false;
                }
            }
            
            
       	
 
