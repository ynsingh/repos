/**
 * @(#) rollEnrollFormatAs.as
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
 import common.commonFunction;
 
 import mx.collections.ArrayCollection;
 import mx.controls.Alert;
 import mx.core.UIComponent;
 import mx.events.CloseEvent;
 import mx.managers.PopUpManager;
 import mx.rpc.events.FaultEvent;
 import mx.rpc.events.ResultEvent;
 
 import rollEnrollFormat.helpWindow;
 
 
 [Embed(source="/images/success.png")]private var successIcon:Class;			
 [Embed(source="/images/question.png")]private var questionIcon:Class;
 [Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
 [Embed(source="/images/error.png")]private var errorIcon:Class;
 
 var formatXml:XML;
 var gridArrCol:ArrayCollection;
/**
 * On Creation Complete of Main Panel
 **/
 private function onCreationComplete():void{
 	var typeCBArrCol:ArrayCollection=new ArrayCollection(); 
 	typeCBArrCol.addItemAt({type:"Roll Number",typeCode:"ROL"},typeCBArrCol.length);
 	typeCBArrCol.addItemAt({type:"Enrollment Number",typeCode:"ENROL"},typeCBArrCol.length);	
 	typeCB.dataProvider=typeCBArrCol;
 }
 /**
 * On Change of Type Combo Box
 **/
 private function onChangeTypeCB():void{
 	fromNS.enabled=true;
 	toNS.enabled=true;
 	submitBtn.enabled=true; 	 	 	
 	builtInRadio.enabled=true;
 	selfDefinedRadio.enabled=true; 	
 	
 	var inBuiltFormatArr:ArrayCollection=new ArrayCollection();
 	inBuiltFormatArr.addItemAt({formatName:"Sequence Number",formatCD:"SN"},inBuiltFormatArr.length);
 	inBuiltFormatArr.addItemAt({formatName:"Two digit year Code(YY)",formatCD:"YY"},inBuiltFormatArr.length);
 	inBuiltFormatArr.addItemAt({formatName:"Four digit year Code(YYYY)",formatCD:"YYYY"},inBuiltFormatArr.length);
 	inBuiltFormatArr.addItemAt({formatName:"Two digit Month Code(MM)",formatCD:"MM"},inBuiltFormatArr.length);
 	inBuiltFormatArr.addItemAt({formatName:"Entity Code(ENTCD)",formatCD:"ENTCD"},inBuiltFormatArr.length);
 	inBuiltCB.dataProvider=inBuiltFormatArr;
 	var obj:Object={};
 	obj["date"]=new Date();
 	obj["type"]=typeCB.selectedItem.typeCode;
 	getFormatService.send(obj);	
 }
 
 /**
 * Result handler of getDatabase service
 **/
public function getFormatResult(event:ResultEvent):void{
	formatXml=new XML();
	formatXml=event.result as XML;
	if(formatXml.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }
  	gridArrCol=new ArrayCollection();
  	var typeName:String="";
  	var tempMaxToRange:int=0;
  	for each(var obj:Object in formatXml.format){
  		if(obj.type=="ROL"){
  			typeName="Roll Number";
  		}
  		else if(obj.type=="ENROL"){
  			typeName="Enrollment Number";
  		}
  		if(obj.rangeTo>tempMaxToRange){
  			tempMaxToRange=obj.rangeTo;
  		}
  		gridArrCol.addItem({select:false,type:obj.type,typeName:typeName,rangeFrom:obj.rangeFrom,rangeTo:obj.rangeTo,format:obj.format,maxToRange:obj.maxToRange});  		
  	}
    var range:int=1;
    var temp:int=1;    
	 for(var i:int=0;i<gridArrCol.length;i++){	 
		if(int(gridArrCol.getItemAt(i)["rangeFrom"])==range){    			
			range=int(gridArrCol.getItemAt(i)["rangeTo"])+1;			
		}
		else{
			temp=int(gridArrCol.getItemAt(i)["rangeFrom"])-1;
			break;
		}		
	}
   	if(range-1==tempMaxToRange){
   		fromNS.value=tempMaxToRange+1;
  		fromNS.minimum=tempMaxToRange+1;
  		fromNS.maximum=tempMaxToRange+1;
  		toNS.value=1;
  		toNS.minimum=1;
  		toNS.maximum=99;
   	}
   	else{
   		fromNS.value=range;
  		fromNS.minimum=range;
  		fromNS.maximum=range;
  		toNS.value=temp;
  		toNS.minimum=range;
  		toNS.maximum=temp;
   	}
  	
    UIComponent(fromNS).errorString="";
    toNS.value=1;
    UIComponent(toNS).errorString="";
    formatValue.text="";   
    UIComponent(formatValue).errorString=""; 	
    inBuiltCB.selectedIndex=-1;
    UIComponent(inBuiltCB).errorString=""; 
    selfDefinedRadio.selected=false;
    builtInRadio.selected=false;
    formatValue.visible=false;
    inBuiltCB.visible=false;
    formatValue.enabled=false;
    inBuiltCB.enabled=false;
  	grid.dataProvider=gridArrCol;
}

/**
 * Failure Handler
 **/
public function onFailure(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),null,null,null,errorIcon);
}
 
 /**
 * On Click of Submit Button
 **/
 private function onClickSubmit():void{
 	var flag:Boolean=true;
 	var existsFlag:Boolean=false;
 	for each(var obj:Object in formatXml.format){
		if(obj.format==formatValue.text){			
			flag=false;
			existsFlag=true;
		}	
  	}
 	if(toNS.value<fromNS.value){
 		toNS.errorString=commonFunction.getMessages('toRangeGreater');
 		Alert.show(commonFunction.getMessages('toRangeGreater'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 	}
 	else if(formatValue.text=="" && inBuiltCB.selectedIndex==-1){
 		if(selfDefinedRadio.selected==true){
 			formatValue.errorString=commonFunction.getMessages('enterFormat');
 			Alert.show(commonFunction.getMessages('enterFormat'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 		}
 		else{
 			inBuiltCB.errorString=commonFunction.getMessages('selectFormat');
 			Alert.show(commonFunction.getMessages('selectFormat'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 		}
 		
 	}
 	else if(existsFlag){
 		Alert.show(commonFunction.getMessages('fromNSValueExists'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 	}
 	else{ 	
 		var str:String="";
 		if(selfDefinedRadio.selected==true){
 			str=formatValue.text.toString();
 		}
 		else{
 			str=inBuiltCB.selectedItem.formatCD.toString();
 		} 		
 		if(str!="SN" && str!="ENTCD"){ 			 		 			
 			if(str.length!=(toNS.value-fromNS.value)+1){
 				flag=false;
 				if(str.length<(toNS.value-fromNS.value)+1){
 					Alert.show(commonFunction.getMessages('formatLengthLess'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 				}
 				else{
 					Alert.show(commonFunction.getMessages('formatLengthGreater'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 				} 				 			
 			} 			
 		}
 		else if(str=="ENTCD"){
 			if((toNS.value-fromNS.value)+1>1){
 				flag=false;
 				Alert.show(commonFunction.getMessages('entityCodeLengthShouldOne'),commonFunction.getMessages('info'),null,null,null,infoIcon);
 			}
 		}
 		if(flag){ 			
 			Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,insertConfirm,questionIcon);
 		}  			 	
 	}
 }
 
 /**
 * Method to insert records
 **/
private function insertConfirm(event:CloseEvent):void{
	if(event.detail==1){
		var obj:Object={};
 		obj["date"]=new Date();
 		obj["type"]=typeCB.selectedItem.typeCode;
 		obj["rangeFrom"]=fromNS.value.toString();
 		obj["rangeTo"]=toNS.value.toString();
 		if(selfDefinedRadio.selected==true){
 			obj["format"]=formatValue.text;
 		}
 		else{
 			obj["format"]=inBuiltCB.selectedItem.formatCD;
 		}
 		
 		insertFormatService.send(obj);
	}				
}
	
 /**
 * Result handler of insertFormatService service
 **/
public function insertFormatResult(event:ResultEvent):void{
	var xml:XML=new XML();
	xml=event.result as XML;
	if(xml.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }
    if(xml.info=="success"){
    	Alert.show(commonFunction.getMessages('successOnInsert'),commonFunction.getMessages('success'),null,null,null,successIcon);
    }
    else{
    	Alert.show(commonFunction.getMessages('formatInsertFail'),commonFunction.getMessages('error'),null,null,null,errorIcon);
    }
   	var obj:Object={};
 	obj["date"]=new Date();
 	obj["type"]=typeCB.selectedItem.typeCode;
 	getFormatService.send(obj);	   	
}

 /**
 * On Click of deleteRecord
 **/
public function deleteRecord():void{
	Alert.show(commonFunction.getMessages('areyousure'),commonFunction.getMessages('confirm'),(Alert.YES|Alert.NO),null,deleteConfirm,questionIcon);
}

 /**
 * Method to Delete records
 **/
private function deleteConfirm(event:CloseEvent):void{
	if(event.detail==1){
		var str:String="";
		for each(var obj:Object in grid.dataProvider){
			if(obj.select==true){
				str=str+obj.format+",";
			}
		}
		var obj:Object={};
 		obj["date"]=new Date();
 		obj["type"]=typeCB.selectedItem.typeCode; 		
 		obj["format"]=str;
 		deleteFormatService.send(obj);
	}				
}

/**
 * Result handler of insertFormatService service
 **/
public function deleteFormatResult(event:ResultEvent):void{
	var xml:XML=new XML();
	xml=event.result as XML;
	if(xml.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }
    if(xml.info=="success"){
    	Alert.show(commonFunction.getMessages('deleteSuccess'),commonFunction.getMessages('success'),null,null,null,successIcon);
    }
    else{
    	Alert.show(commonFunction.getMessages('formatDeleteFail'),commonFunction.getMessages('error'),null,null,null,errorIcon);
    }
    deleteLink.enabled=false;
   	var obj:Object={};
 	obj["date"]=new Date();
 	obj["type"]=typeCB.selectedItem.typeCode;
 	getFormatService.send(obj);	   	
}
 
 /**
 * On Change of Help Link
 **/
 private function onClickHelp():void{
 	var helpPopup:helpWindow=helpWindow(PopUpManager.createPopUp(this,helpWindow,true));
 }
