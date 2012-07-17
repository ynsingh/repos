/**
 * @(#) associateMouAction.as
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

import mx.collections.*;
import mx.controls.*;
import mx.core.UIComponent;
import mx.events.*;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;

[Bindable]public var universityList:XML;
[Bindable]public var mouList:XML;
[Bindable]public var resultXml:XML;
[Bindable]public var defaultXml:XML;
[Bindable]public var obj :Object ;
[Bindable] public var universitydata:ArrayCollection=new ArrayCollection();


[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
private var courseDetailGridDataProvider:ArrayCollection = new ArrayCollection();
public var codes:String="";
[Bindable]public var urlPrefix:String="";
[Bindable]var parms :Object={};

/** on load of page **/
public function showOnLoad():void
{
	urlPrefix=commonFunction.getConstants('url')+"/AssociateMOU/";
	parms["date"]=new Date();
	
	getMouDetails.send(parms);
	getDefault.send(parms);
	getUniversityList.send(parms);
}

/** get mou list success handler **/
public function getUniversityListSuccess(event:ResultEvent):void{
	
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    universityList = event.result as XML;
	universitydata.removeAll();
	
	for each( obj in universityList.rec)
	{
		universitydata.addItem({university:obj.university.universityName});
	}
}

/** get university name success handler **/
public function getDefaultSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    defaultXml=event.result as XML;
	universityIdLabel.text=defaultXml.university.universityName;
}

/** get mou list success handler **/
public function getMouDetailsSuccess(event:ResultEvent):void
{	
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    mouList=event.result as XML;
		
	var moudata:ArrayCollection=new ArrayCollection();
	moudata.removeAll();
	
	for each(var obj:Object in mouList.rec )
	{
		moudata.addItem({select:false,idname:obj.idname, mouidname:obj.mouidname,id:obj.id, mouid:obj.mouid});
	}
	associatedMouGrid.dataProvider=moudata;
}

/** add mou success handler **/
public function setMouSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    resultXml = event.result as XML;
	
	if (resultXml.msg =="P")
	{
		Alert.show((resourceManager.getString('Messages','successOnInsert')),(resourceManager.getString('Messages','success')),0,null,null,successIcon);
	}
	else
	{
		Alert.show((resourceManager.getString('Messages','requestFailed')),(resourceManager.getString('Messages','failure')),0,null,null,errorIcon);
	}
	
	showOnLoad();
}

/** delete mou success handler **/
public function deletMouSuccess(event:ResultEvent):void
{
	Mask.close();
	try
	{
		if(event.result.sessionConfirm == true)
		{
	 		Alert.show(resourceManager.getString('Messages','sessionInactive'),commonFunction.getMessages('error'),0,null,null,errorIcon);
	    	this.parentDocument.vStack.selectedIndex=0;
	    	this.parentDocument.loaderCanvas.removeAllChildren();
		}
	}
 	catch(e:Error){}
    resultXml = event.result as XML;
	
	if (resultXml.msg =="P")
	{
		Alert.show((resourceManager.getString('Messages','recordsDeletedSuccessfully')),(resourceManager.getString('Messages','success')),0,null,null,successIcon);
	}
	else
	{
		Alert.show((resourceManager.getString('Messages','recordsnotDeleted')),(resourceManager.getString('Messages','failure')),0,null,null,successIcon);
	}
	
	showOnLoad();
}
	
/** fault handler **/
public function onFailure(event:FaultEvent):void{
	
	Alert.show("This is getmou failure");
	Mask.close();	
}

/** setting first focus **/
public function setFirstFocous(object:UIComponent):void
{
	object.setFocus();
	object.drawFocus(true);
}

/** on change of university **/
public function mouChange():void
{
	submitButton.enabled=true;	
}



/** on click of submit **/
public function addMou():void
{
	if(mouCombo.selectedIndex!=-1)
	{
		Alert.show(commonFunction.getMessages('conformForContinue'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertOrNot,questionIcon);
	}
	else
	{
		mouCombo.errorString=commonFunction.getMessages('firstSelectMouUniversity');
		Alert.show((resourceManager.getString('Messages','firstSelectMouUniversity')),(resourceManager.getString('Messages','error')),0,null,null,errorIcon);
	}
}

/** insert confirmation handler **/
public function insertOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		parms["mouid"] = universityList.rec.university.(universityName==mouCombo.selectedLabel).@id;
		parms["id"] = defaultXml.university.(universityName=universityIdLabel.text).@id ;
		setMouDetails.send(parms);
		Mask.show(commonFunction.getMessages('pleaseWait'));
	}
}

/** on click of delete button **/
public function deleteRecords():void
{
	Alert.show((resourceManager.getString('Messages','conformForContinue')),(resourceManager.getString('Messages','confirm')),(Alert.YES|Alert.NO),null,deleteOrNot,questionIcon);	
}

/** delete record confirmation handler **/
public function deleteOrNot(event:CloseEvent):void
{
	if(event.detail==Alert.YES)
	{
		var griddata:ArrayCollection=commonFunction.getSelectedRowData(associatedMouGrid);
		var selectedRecordArrColl:ArrayCollection=new ArrayCollection();
		var s:String=" ";
		var recordtobedeleted:Object=new Object();
			   
		for each(var o:Object in griddata)
		{
			selectedRecordArrColl.addItem([o.id, o.mouid]);
		}
			   
		recordtobedeleted["delrec"]=selectedRecordArrColl;
		deletMouDetails.send(recordtobedeleted);
		Mask.show(commonFunction.getMessages('pleaseWait'));
		deletebutton.enabled=false;
	}
		 
	if(event.detail==2)
	{
		var gridDataItems:ArrayCollection=associatedMouGrid.dataProvider as ArrayCollection;
		
		for(var e:int=0;e<gridDataItems.length;e++)
		{
			var gridItem:Object=gridDataItems.getItemAt(e);
			if(gridItem.select==true)
			{
				gridItem.select=false;
				gridDataItems.setItemAt(gridItem,e);
				associatedMouGrid.dataProvider=gridDataItems;
			}
		}
		deletebutton.enabled=false;
	}
}  
	
/** on click of cancel button **/
public function cancelFunction():void
{
	this.parentDocument.loaderCanvas.removeAllChildren();
}							