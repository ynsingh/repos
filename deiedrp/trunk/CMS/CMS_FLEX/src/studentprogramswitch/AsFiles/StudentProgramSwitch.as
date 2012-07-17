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
import cancelRegistration.CancelRegistration;

import flash.events.Event;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.managers.PopUpManager;
import mx.rpc.events.ResultEvent;
[Bindable] public  var urlPrefix:String;

[Bindable]
public var studentcollection : ArrayCollection = new ArrayCollection ;


public var parmstudent:Object = new Object ;

            
            public  function processstudent(event:Event):void{
            	
                   	
           	parmstudent["rollNo"] = studentgrid.selectedItem.rollNo ;
           	parmstudent["prvswitchstatus"] = studentgrid.selectedItem.swsts;
           
//            Alert.show(String(studentgrid.selectedIndex)) ;
//            
//            Alert.show(studentcollection[studentgrid.selectedIndex].swsts) ;
//            studentgrid.selectedIndex
		if (event.currentTarget.id == "swtact"){
			parmstudent["switchstatus"] = "SWT" ;
            switchstudent.send(parmstudent);
 	
            }
            
        	if (event.currentTarget.id == "swtcan"){
        	parmstudent["switchstatus"] = "NOR" ;	
            switchstudent.send(parmstudent);
 			
            }    
            
            	if (event.currentTarget.id == "regcan"){
        		
                   //addChild(CancelRegistration);
                   
                   var popupwindow:CancelRegistration = CancelRegistration(PopUpManager.createPopUp(this,CancelRegistration,true));
                   popupwindow.regRollText.text=studentgrid.selectedItem.rollNo ;
                   popupwindow.reregistrationCHBox.selected=true;
                   popupwindow.cancelButton.visible=false;
                   popupwindow.showCloseButton=true;
                   popupwindow.okbutton.visible=true;
                  
                   PopUpManager.centerPopUp(popupwindow);
            }

            }
 protected function switchstudentSuccess(event:ResultEvent):void{
 		Alert.show("request passed");
 		var sts :String ;
 		
 		var switchstatus :XML = event.result as  XML;
 		for each(var obj:Object in switchstatus.student){
 			sts = obj.switchstatus ;
 		}
 		
 		
 		if (studentcollection[studentgrid.selectedIndex].ss && sts == "SWT"){
 		studentcollection[studentgrid.selectedIndex].ss = false; 
 		studentcollection[studentgrid.selectedIndex].cs = true;	
 		studentcollection[studentgrid.selectedIndex].swsts = sts ;
 		}
 		
 		if (studentcollection[studentgrid.selectedIndex].cs && sts == "NOR"){
 		studentcollection[studentgrid.selectedIndex].ss = true; 
 		studentcollection[studentgrid.selectedIndex].cs = false;	
 		studentcollection[studentgrid.selectedIndex].swsts = sts ;
 		}
 		
 		studentcollection.refresh();
		
 }	 

protected function onFailure(event):void{
		Alert.show("request failed");
	
	
} 	 



















 
   
           
           
 
 
