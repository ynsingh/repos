/**
 * @(#) UserDataInterfaceAs.as
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
 import mx.collections.ArrayCollection;
 import mx.controls.Alert;
 import mx.effects.easing.*;
 import mx.managers.PopUpManager;
 import mx.rpc.events.FaultEvent;
 import mx.rpc.events.ResultEvent;
 
 import userDataInterface.GroupByClause;
 import userDataInterface.HavingClause;
 import userDataInterface.JoinClause;
 import userDataInterface.OrderByClaue;
 import userDataInterface.ResultPopup;
 import userDataInterface.WhereClause;
 
[Embed(source="/images/error.png")]private var errorIcon:Class;
[Embed(source="/images/success.png")]private var successIcon:Class;
[Embed(source="/images/question.png")]private var questionIcon:Class;
[Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
[Embed(source="/images/previous.png")]private var previousIcon:Class;
[Embed(source="/images/next.png")]private var nextIcon:Class;
[Embed(source="/images/refresh.png")]private var refreshIcon:Class;
[Embed(source="/images/doublePrevious.png")]private var doublePreviousIcon:Class;
[Embed(source="/images/doubleNext.png")]private var doubleNextIcon:Class;

var selectedTableIndex:Array;
var database:String="";
var selectedTableData:Array;//Contain Selected Tables 
var columnArrCol:ArrayCollection;//Contain all selected tables and their columns
var selectedTableColumns:ArrayCollection;
var selectedColumns:ArrayCollection;
var selectionDefaultTileIndex:Array;
var selectionDefaultTileData:Array;//Contain Selected Table's Columns for tile 
var selectionFinalTileIndex:Array;
var selectionFinalTileData:Array;//Contain Selected Columns 
var whereConditionColumnArray:ArrayCollection=new ArrayCollection();//Contain Where Conditions
var joinConditionArray:ArrayCollection=new ArrayCollection();//Contain Join Conditions
var groupByConditionArray:ArrayCollection=new ArrayCollection();//Contain Group by columns
var havingConditionArray:ArrayCollection=new ArrayCollection();//Contain Having Conditions
var orderByConditionArray:ArrayCollection=new ArrayCollection();//Contain Order By Columns
var result:XML;//Contain Result
[Bindable]var finalColumnArrTemp:ArrayCollection=new ArrayCollection();
/**
 * On Creation Complete of Main Panel
 * Method send request to get database names
 **/
public function onCreatrionComplete():void{
	getDatabaseService.send(new Date);
}
/**
 * Result handler of getDatabase service
 **/
public function getDatabaseServiceResult(event:ResultEvent):void{
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }
  	database=result.database[0].databaseName;
   	var param:Object={};
 	param["database"]=database
	param["date"]=new Date();
	refreshSelectionContainer();	
	getTableService.send(param);
}

/**
 * Failure Handler
 **/
public function onFailure(event:FaultEvent):void{
	Alert.show(commonFunction.getMessages('problemInService'),commonFunction.getMessages('error'),null,null,null,errorIcon);
}
/**
 * Result handler of getTable service
 **/
public function getTableServiceResult(event:ResultEvent):void{
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }
   var tableArrCol:ArrayCollection=new ArrayCollection();
   for each(var obj:Object in result.database){
   		tableArrCol.addItem({tableName:obj.tableName});
   }
   selectedTableIndex=null;
   selectedTableData=null;
   selectionColumnContainer.visible=false;
   conditionalContainer.visible=false;   
   tableTile.dataProvider=tableArrCol;
}

/**
 * Result handler of getColumn service
 **/
public function getColumnServiceResult(event:ResultEvent):void{
	var result:XML=event.result as XML;
	if(result.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }   
  	columnArrCol=new ArrayCollection();
  	for each(var obj:Object in result.database){
  		columnArrCol.addItem({tableName:obj.tableName,column:obj.columnName,key:obj.key,dataType:obj.dataType});
  	}
  	generateColumnSelectionContainer();  
}

/**
 * Method to generate container for selection columns
 **/
function generateColumnSelectionContainer():void{
	cancelBtn.y=563;
	executeBtn.y=563;
	showResultBtn.y=563;
	executeBtn.visible=true;
	showResultBtn.visible=true;
	showResultBtn.enabled=false;
	selectionColumnContainer.visible=true;
	conditionalContainer.visible=true;
	var selectedColumn:ArrayCollection=new ArrayCollection();
	selectedColumns=new ArrayCollection();
	if(finalSelectionColumnTile.dataProvider!=null){
		finalSelectionColumnTile.dataProvider.removeAll();
		finalSelectionColumnTile.dataProvider.refresh();
	}
	for(var i:int=0;i<selectedTableIndex.length;i++){
		selectedColumn.addItem({selectedColumn:selectedTableData[i].tableName});		
	}
	selectedTables.dataProvider=selectedColumn;
	if(selectedTableColumnsTile.dataProvider!=null){
		selectedTableColumnsTile.dataProvider=null;
		selectedTableColumnsTile.dataProvider.refresh();
	}
	whereConditionColumnArray.removeAll();
	joinConditionArray.removeAll();
	groupByConditionArray.removeAll();
	orderByConditionArray.removeAll();
	havingConditionArray.removeAll();
	whereBtn.setStyle("color",'#000000');
	whereBtn.setStyle("borderColor",'#b7babc');
	whereBtn.setStyle("themeColor",'#009dff');
	joinBtn.setStyle("color",'#000000');
	joinBtn.setStyle("borderColor",'#b7babc');
	joinBtn.setStyle("themeColor",'#009dff');
	groupByBtn.setStyle("color",'#000000');
	groupByBtn.setStyle("borderColor",'#b7babc');
	groupByBtn.setStyle("themeColor",'#009dff');	
	orderByBtn.setStyle("color",'#000000');
	orderByBtn.setStyle("borderColor",'#b7babc');
	orderByBtn.setStyle("themeColor",'#009dff');
	havingBtn.setStyle("color",'#000000');
	havingBtn.setStyle("borderColor",'#b7babc');
	havingBtn.setStyle("themeColor",'#009dff');
}
/**
 * On Change of Selected Table ComboBox
 **/
function onSelectedTableCBChange():void{
	selectedTableColumns=new ArrayCollection();
	for each(var obj:Object in columnArrCol){
		if(obj.tableName==selectedTables.selectedItem.selectedColumn){
			selectedTableColumns.addItem({column:obj.column,table:obj.tableName,key:obj.key,dataType:obj.dataType});
		}
	}
	selectedTableColumnsTile.dataProvider=selectedTableColumns;
}
/**
 * On click of Right Arrow
 **/
function onAddSelectonClick():void{
	if(selectionDefaultTileIndex==null || selectionDefaultTileIndex.length==0){
		Alert.show(commonFunction.getMessages('selectColumn'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
	}
	else{
		var flag:Boolean=false;
		for(var i:int=0;i<selectionDefaultTileIndex.length;i++){
			for each(var o:Object in selectedColumns){
				if((o.column==selectionDefaultTileData[i].column) && (o.table==selectionDefaultTileData[i].table)){
					flag=true;
					break;
				}
			}
			if(flag){
				Alert.show(commonFunction.getMessages('columnExist'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
				break;
			}
			else{
				selectedColumns.addItem({column:selectionDefaultTileData[i].column,table:selectionDefaultTileData[i].table,key:selectionDefaultTileData[i].key,dataType:selectionDefaultTileData[i].dataType,tolTip:selectionDefaultTileData[i].table+"."+selectionDefaultTileData[i].column});
			}				
		}
		if(!flag){
			finalSelectionColumnTile.dataProvider=selectedColumns;
			finalSelectionColumnTile.dataProvider.refresh();
			selectedTableColumnsTile.dataProvider.removeItemAt(selectionDefaultTileIndex);
			selectedTableColumnsTile.dataProvider.refresh();
		}		
		selectionDefaultTileIndex=null;
	}	

}
/**
 * On click of Double Right Arrow
 **/
function onAllAddSelectonClick():void{
	if(selectedTableColumnsTile.dataProvider.length==0){
		Alert.show(commonFunction.getMessages('noColumnToAdd'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
	}
	else{
		var flag:Boolean=false;
		for(var i:int=0;i<selectedTableColumnsTile.dataProvider.length;i++){
			for each(var o:Object in selectedColumns){
				if((o.column==selectedTableColumnsTile.dataProvider[i].column) && (o.table==selectedTableColumnsTile.dataProvider[i].table)){
					flag=true;
					break;
				}
			}
			if(!flag){
				selectedColumns.addItem({column:selectedTableColumnsTile.dataProvider[i].column,table:selectedTableColumnsTile.dataProvider[i].table,key:selectedTableColumnsTile.dataProvider[i].key,dataType:selectedTableColumnsTile.dataProvider[i].dataType,tolTip:selectedTableColumnsTile.dataProvider[i].table+"."+selectedTableColumnsTile.dataProvider[i].column});
			}						
		}		
		finalSelectionColumnTile.dataProvider=selectedColumns;
		finalSelectionColumnTile.dataProvider.refresh();
		selectedTableColumnsTile.dataProvider.removeAll();
		selectedTableColumnsTile.dataProvider.refresh();	
		selectionDefaultTileIndex=null;
	}	

}
/**
 * On click of Left Arrow
 **/
 function onRemoveSelectionClick():void{
 	if(selectionFinalTileIndex==null || selectionFinalTileIndex.length==0){
		Alert.show(commonFunction.getMessages('selectColumn'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
	}
	else{
		var flag:Boolean=false;
		for(var i:int=0;i<selectionFinalTileIndex.length;i++){
			for each(var obj:Object in selectedTableColumns){				
				if(obj.column==selectionFinalTileData[i].column){
					flag=true;
					break;
				}
			}
			if(!flag){	
				selectedTableColumns.addItemAt({column:selectionFinalTileData[i].column,table:selectionFinalTileData[i].table,key:selectionFinalTileData[i].key,dataType:selectionFinalTileData[i].dataType},selectedTableColumns.length);			
			}							
		}
		if(!flag){	
			finalSelectionColumnTile.dataProvider.removeItemAt(selectionFinalTileIndex);
			finalSelectionColumnTile.dataProvider.refresh();		
			selectedTableColumnsTile.dataProvider=selectedTableColumns;
			selectedTableColumnsTile.dataProvider.refresh();
		}
		else{
			finalSelectionColumnTile.dataProvider.removeItemAt(selectionFinalTileIndex);
			finalSelectionColumnTile.dataProvider.refresh();
		}				
		selectionFinalTileIndex=null;
	}
 }
 /**
 * On click of Double Left Arrow
 **/
 function onAllRemoveSelectionClick():void{
 	if(finalSelectionColumnTile.dataProvider.length==0){
		Alert.show(commonFunction.getMessages('noColumntoRemove'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
	}
	else{
		var flag:Boolean=false;
		for(var i:int=0;i<finalSelectionColumnTile.dataProvider.length;i++){
			for each(var obj:Object in selectedTableColumns){				
				if(obj.column==finalSelectionColumnTile.dataProvider[i].column){
					flag=true;
					break;
				}
			}
			if(!flag){	
				selectedTableColumns.addItemAt({column:finalSelectionColumnTile.dataProvider[i].column,table:finalSelectionColumnTile.dataProvider[i].table,key:finalSelectionColumnTile.dataProvider[i].key,dataType:finalSelectionColumnTile.dataProvider[i].dataType},selectedTableColumns.length);			
			}							
		}
		if(!flag){	
			finalSelectionColumnTile.dataProvider.removeAll();
			finalSelectionColumnTile.dataProvider.refresh();		
			selectedTableColumnsTile.dataProvider=selectedTableColumns;
			selectedTableColumnsTile.dataProvider.refresh();
		}
		else{
			finalSelectionColumnTile.dataProvider.removeAll();
			finalSelectionColumnTile.dataProvider.refresh();
		}				
		selectionFinalTileIndex=null;
	}
 }
/**
 * On Refresh Click
**/
function onRefreshClick():void{
 	if(finalSelectionColumnTile.dataProvider!=null){
		finalSelectionColumnTile.dataProvider.removeAll();
		finalSelectionColumnTile.dataProvider.refresh();
	}	
	if(selectedTableColumnsTile.dataProvider!=null){
		selectedTableColumnsTile.dataProvider.removeAll();		
		for each(var obj:Object in columnArrCol){
			if(obj.tableName==selectedTables.selectedItem.selectedColumn){
				selectedTableColumns.addItem({column:obj.column,table:obj.tableName,key:obj.key,dataType:obj.dataType});
			}
		}		
		selectedTableColumnsTile.dataProvider=selectedTableColumns;
		selectedTableColumnsTile.dataProvider.refresh();
	}	
}
/**
 * Method to refresh Selection Container
 **/
function refreshSelectionContainer():void{
	if(selectedTables.dataProvider!=null){
		selectedTables.dataProvider.removeAll();
		selectedTables.dataProvider.refresh();
	}
	if(finalSelectionColumnTile.dataProvider!=null){
		finalSelectionColumnTile.dataProvider=null;
		finalSelectionColumnTile.dataProvider.refresh();
	}
	if(selectedTableColumnsTile.dataProvider!=null){
		selectedTableColumnsTile.dataProvider=null;		
		selectedTableColumnsTile.dataProvider.refresh();
	}		
}

/**
 * On Click of where condition button
 **/
private function onClickWhereCondition():void{		
	 var wherePopup:WhereClause=WhereClause(PopUpManager.createPopUp(this,WhereClause,true));	
	 wherePopup.selectedTablesArr=selectedTables.dataProvider as ArrayCollection;
	 wherePopup.columnArr=columnArrCol;	 
	 wherePopup.fun=whereConditionFunction;
	 wherePopup.whereConditionArr=whereConditionColumnArray;	
	 openEffect.play([wherePopup]);
	 wherePopup.grid.dataProvider=wherePopup.whereConditionArr;
	 wherePopup.selectedTables.dataProvider=wherePopup.selectedTablesArr;
	 PopUpManager.centerPopUp(wherePopup); 
}
/**
 * On Click of where condition button
 **/
private function onClickJoinCondition():void{
	var joinPopup:JoinClause=JoinClause(PopUpManager.createPopUp(this,JoinClause,true));
	joinPopup.selectedTablesArr=selectedTables.dataProvider as ArrayCollection;
	joinPopup.columnArr=columnArrCol;
	joinPopup.fun=joinConditionFunction;	 
	joinPopup.joinConditionArr=joinConditionArray;
	openEffect.play([joinPopup]);
	joinPopup.selectedTables.dataProvider=joinPopup.selectedTablesArr;
	joinPopup.selectedTables1.dataProvider=joinPopup.selectedTablesArr;
	joinPopup.grid.dataProvider=joinPopup.joinConditionArr;
	PopUpManager.centerPopUp(joinPopup); 
}
/**
 * On Click of where condition button
 **/
private function onClickOrderBy():void{
	var orderByPopup:OrderByClaue=OrderByClaue(PopUpManager.createPopUp(this,OrderByClaue,true));
	orderByPopup.columnArr=columnArrCol;
	orderByPopup.selectedTablesArr=selectedTables.dataProvider as ArrayCollection;
	orderByPopup.fun=orderByConditionFunction;
	orderByPopup.orderByArr=orderByConditionArray;
	openEffect.play([orderByPopup]);
	orderByPopup.selectedTables.dataProvider=orderByPopup.selectedTablesArr;				
	orderByPopup.grid.dataProvider=orderByPopup.orderByArr;	
	PopUpManager.centerPopUp(orderByPopup);
}
/**
 * On Click of where condition button
 **/
private function onClickGroupBy():void{
	var groupByPopup:GroupByClause=GroupByClause(PopUpManager.createPopUp(this,GroupByClause,true));
	groupByPopup.columnArr=columnArrCol;
	groupByPopup.selectedTablesArr=selectedTables.dataProvider as ArrayCollection;
	groupByPopup.fun=groupByConditionFunction;
	groupByPopup.groupByArr=groupByConditionArray;
	openEffect.play([groupByPopup]);	
	groupByPopup.selectedTables.dataProvider=groupByPopup.selectedTablesArr;
	groupByPopup.grid.dataProvider=groupByPopup.groupByArr;
	PopUpManager.centerPopUp(groupByPopup);
}
/**
 * On Click of where condition button
 **/
private function onClickHavingCondition():void{
	if(groupByConditionArray.length==0){
		Alert.show(commonFunction.getMessages('firstSelectGroupBy'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
	}
	else{
		var havingPopup:HavingClause=HavingClause(PopUpManager.createPopUp(this,HavingClause,true));
		havingPopup.selectedTablesArr=selectedTables.dataProvider as ArrayCollection;
		havingPopup.columnArr=columnArrCol;	 
		havingPopup.fun=havingConditionFunction;
		havingPopup.havingConditionArr=havingConditionArray;	
		openEffect.play([havingPopup]);
		havingPopup.grid.dataProvider=havingPopup.havingConditionArr;
		havingPopup.selectedTables.dataProvider=havingPopup.selectedTablesArr;
		PopUpManager.centerPopUp(havingPopup); 
	}
}

/**
 * On cancel event of Where condition popup
 * Method to set where conditions in whereConditionColumnArray arrayCollection
 **/
public function whereConditionFunction(arr:ArrayCollection):void{	
	whereConditionColumnArray=arr;
	if(whereConditionColumnArray.length>0){
		whereBtn.setStyle("color",'#6E64F1');
		whereBtn.setStyle("borderColor",'#3D3678');
		whereBtn.setStyle("themeColor",'#9C7FBF');
	}
	else{
		whereBtn.setStyle("color",'#000000');
		whereBtn.setStyle("borderColor",'#b7babc');
		whereBtn.setStyle("themeColor",'#009dff');
	}
}
/**
 * On cancel event of Join condition popup
 * Method to set join conditions in joinConditionArray arrayCollection
 **/
public function joinConditionFunction(arr:ArrayCollection):void{	
	joinConditionArray=arr;
	if(joinConditionArray.length>0){
		joinBtn.setStyle("color",'#6E64F1');
		joinBtn.setStyle("borderColor",'#3D3678');
		joinBtn.setStyle("themeColor",'#9C7FBF');
	}
	else{
		joinBtn.setStyle("color",'#000000');
		joinBtn.setStyle("borderColor",'#b7babc');
		joinBtn.setStyle("themeColor",'#009dff');
	}
}
/**
 * On cancel event of Group By popup
 * Method to set Group By conditions in groupByConditionArray arrayCollection
 **/
public function groupByConditionFunction(arr:ArrayCollection):void{	
	groupByConditionArray=arr;
	if(groupByConditionArray.length>0){
		groupByBtn.setStyle("color",'#6E64F1');
		groupByBtn.setStyle("borderColor",'#3D3678');
		groupByBtn.setStyle("themeColor",'#9C7FBF');
	}
	else{
		groupByBtn.setStyle("color",'#000000');
		groupByBtn.setStyle("borderColor",'#b7babc');
		groupByBtn.setStyle("themeColor",'#009dff');
	}
}
/**
 * On cancel event of Order By popup
 * Method to set Order By conditions in orderByConditionArray arrayCollection
 **/
public function orderByConditionFunction(arr:ArrayCollection):void{	
	orderByConditionArray=arr;
	if(orderByConditionArray.length>0){
		orderByBtn.setStyle("color",'#6E64F1');
		orderByBtn.setStyle("borderColor",'#3D3678');
		orderByBtn.setStyle("themeColor",'#9C7FBF');
	}
	else{
		orderByBtn.setStyle("color",'#000000');
		orderByBtn.setStyle("borderColor",'#b7babc');
		orderByBtn.setStyle("themeColor",'#009dff');
	}
}
/**
 * On cancel event of Having Condition popup
 * Method to set Having conditions in havingConditionArray arrayCollection
 **/
public function havingConditionFunction(arr:ArrayCollection):void{	
	havingConditionArray=arr;
	if(havingConditionArray.length>0){
		havingBtn.setStyle("color",'#6E64F1');
		havingBtn.setStyle("borderColor",'#3D3678');
		havingBtn.setStyle("themeColor",'#9C7FBF');
	}
	else{
		havingBtn.setStyle("color",'#000000');
		havingBtn.setStyle("borderColor",'#b7babc');
		havingBtn.setStyle("themeColor",'#009dff');
	}
}
/**
 * On cancel event of Result popup
 * Method to set Order By conditions in orderByConditionArray arrayCollection
 **/
public function resultCloseFunction():void{	
	showResultBtn.enabled=true;
}
/**
 * On Click of Execute Button
 **/
private function onExecuteClick():void{	
	if(finalSelectionColumnTile.dataProvider==null || finalSelectionColumnTile.dataProvider.length<=0){
		Alert.show(commonFunction.getMessages('selectColumnsToExecute'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
	}
	else{
		var selectedColumns:String="";
		var tables:String="";
		var whereCondition:String="";
		var joinCondition:String="";
		var groupByCondition:String="";
		var orderByCondition:String="";
		var havingCondition:String="";
		
		//To Add Selection Columns
		for each(var o:Object in finalSelectionColumnTile.dataProvider){
			selectedColumns=selectedColumns+"IF("+o.table+"."+o.column+" IS NULL,'',"+o.table+"."+o.column+"),'|',";
		}
		
		//To Add Selected Tables
		for each(var obj:Object in selectedTables.dataProvider){
			tables=tables+obj.selectedColumn+",";
		}
		
		//To Add Where Condition
		var i:int=1;		
		for each(var obj:Object in whereConditionColumnArray){
			var str:String="";
			var exp:String="";					
			if(i==whereConditionColumnArray.length){
				if(obj.expression=="LIKE"){
					if(obj.dataType=="datetime" || obj.dataType=="date"){
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"%' ";
					}
					else{
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"' ";
					}					
				}			
				else{
					if(obj.dataType=="datetime" && obj.expression=="="){
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"%' ";
					}	
					else{
						str=obj.table+"."+obj.column+obj.expression+" '"+obj.value+"' ";
					}
				}										
			}
			else{
				if(obj.expression=="LIKE"){
					if(obj.dataType=="datetime" || obj.dataType=="date"){
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"%' "+obj.condition+" ";
					}
					else{
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"' "+obj.condition+" ";
					}					
				}		
				else{
					if(obj.dataType=="datetime" && obj.expression=="="){
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"%' "+obj.condition+" ";
					}	
					else{
						str=obj.table+"."+obj.column+obj.expression+" '"+obj.value+"' "+obj.condition+" ";
					}	
				}							
			}
			whereCondition=whereCondition+str;
			i++;
		}	
		
		//To Add JoinCondition
		for(var j:int=0;j<joinConditionArray.length;j++){
			var str:String="";
			if(j+1==joinConditionArray.length){
				str=joinConditionArray.getItemAt(j)["table1"]+"."+joinConditionArray.getItemAt(j)["column1"]+joinConditionArray.getItemAt(j)["expression"]+joinConditionArray.getItemAt(j)["table2"]+"."+joinConditionArray.getItemAt(j)["column2"];
			}
			else{
				str=joinConditionArray.getItemAt(j)["table1"]+"."+joinConditionArray.getItemAt(j)["column1"]+joinConditionArray.getItemAt(j)["expression"]+joinConditionArray.getItemAt(j)["table2"]+"."+joinConditionArray.getItemAt(j)["column2"]+" "+joinConditionArray.getItemAt(j)["condition"]+" ";
			}
			joinCondition=joinCondition+str;
		}
		
		//To Add Group By Condition
		for(var j:int=0;j<groupByConditionArray.length;j++){
			var str:String="";
			if(j+1==groupByConditionArray.length){
				str=groupByConditionArray.getItemAt(j)["table"]+"."+groupByConditionArray.getItemAt(j)["column"];
			}
			else{
				str=groupByConditionArray.getItemAt(j)["table"]+"."+groupByConditionArray.getItemAt(j)["column"]+" , ";
			}
			groupByCondition=groupByCondition+str;
		}	
		
		//To Add Having Condition
		var i:int=1;		
		for each(var obj:Object in havingConditionArray){
			var str:String="";
			var exp:String="";					
			if(i==havingConditionArray.length){
				if(obj.expression=="LIKE"){
					if(obj.dataType=="datetime" || obj.dataType=="date"){
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"%' ";
					}
					else{
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"' ";
					}					
				}			
				else{
					if(obj.dataType=="datetime" && obj.expression=="="){
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"%' ";
					}	
					else{
						str=obj.table+"."+obj.column+obj.expression+" '"+obj.value+"' ";
					}
				}										
			}
			else{
				if(obj.expression=="LIKE"){
					if(obj.dataType=="datetime" || obj.dataType=="date"){
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"%' "+obj.condition+" ";
					}
					else{
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"' "+obj.condition+" ";
					}					
				}		
				else{
					if(obj.dataType=="datetime" && obj.expression=="="){
						str=obj.table+"."+obj.column+" LIKE"+" '"+obj.value+"%' "+obj.condition+" ";
					}	
					else{
						str=obj.table+"."+obj.column+obj.expression+" '"+obj.value+"' "+obj.condition+" ";
					}	
				}							
			}
			havingCondition=havingCondition+str;
			i++;
		}
		
		//To Add Order By Condition
		for(var j:int=0;j<orderByConditionArray.length;j++){
			var str:String="";
			if(j+1==orderByConditionArray.length){
				str=orderByConditionArray.getItemAt(j)["table"]+"."+orderByConditionArray.getItemAt(j)["column"]+" "+orderByConditionArray.getItemAt(j)["order"];
			}
			else{
				str=orderByConditionArray.getItemAt(j)["table"]+"."+orderByConditionArray.getItemAt(j)["column"]+" "+orderByConditionArray.getItemAt(j)["order"]+" , ";
			}
			orderByCondition=orderByCondition+str;
		}	
				
		var universityFlag:String="";
		for each(var obj:Object in columnArrCol){
			if(obj.column=="university_code"){
				universityFlag=" AND "+obj.tableName+".university_code";				
				break;
			}
			else if(obj.column=="university_id"){
				universityFlag=" AND "+obj.tableName+".university_id";				
				break;				
			}			
		}							
		var param:Object={};
		param["date"]=new Date();
		param["selectedColumn"]=selectedColumns;
		param["tables"]=tables;
		param["whereCondition"]=whereCondition;
		param["joinCondition"]=joinCondition;
		param["groupByCondition"]=groupByCondition;
		param["havingCondition"]=havingCondition;
		param["orderByCondition"]=orderByCondition;	
		param["universityFlag"]=universityFlag;		
		executeService.send(param);
	}
	
}
/**
 * Result handler of Execute service
 **/
function executeServiceResult(event:ResultEvent):void{
	result=event.result as XML;
	if(result.sessionConfirm == true){
    	Alert.show(commonFunction.getMessages('sessionInactive'),commonFunction.getMessages('error'), 4, null,null,errorIcon);
    	this.parentDocument.vStack.selectedIndex=0;
		this.parentDocument.loaderCanvas.removeAllChildren();
    }
    if(result.database.columnName!=null && result.database.columnName!=""){    	
    	var resultPopup:ResultPopup=ResultPopup(PopUpManager.createPopUp(this,ResultPopup,true));
		resultPopup.resultXml=result;	
		resultPopup.fun=resultCloseFunction;
		resultPopup.columnNameArr=finalSelectionColumnTile.dataProvider as ArrayCollection;	
		finalColumnArrTemp.removeAll();
		for each(var o:Object in finalSelectionColumnTile.dataProvider){
			finalColumnArrTemp.addItem({table:o.table,column:o.column,dataType:o.dataType,key:o.key});
		}			
		resultCreationCompleteEffect.play([resultPopup]);			
    }
    else{
    	showResultBtn.enabled=false;
    	Alert.show(commonFunction.getMessages('resultFailDataBaseInfo'),commonFunction.getMessages('info'), 4, null,null,infoIcon);
    }
    
}
/**
 * On Click of Show Result Button
 **/
private function onClickShowResult():void{	
 	showResultBtn.enabled=false;
 	var resultPopup:ResultPopup=ResultPopup(PopUpManager.createPopUp(this,ResultPopup,true));
	resultPopup.resultXml=result;	
	resultPopup.fun=resultCloseFunction;
	resultPopup.columnNameArr=finalColumnArrTemp;
	resultCreationCompleteEffect.play([resultPopup]);	
 }
 /**
 * On Click of Cancel Button
 **/
 private function onClickCancelButton():void{
 	 this.parentDocument.loaderCanvas.removeAllChildren();
 }
/**
 * On Mouse Down
 **/
private function onMouseDown(str:String):void{			
	if(str=="addSelectionColumn"){		
		addSelection.height=33;	
		addSelection.width=33;	
	}
	else if(str=="removeSelectionColumn"){
		removeSelection.height=33;	
		removeSelection.width=33;			
	}
	if(str=="allAddSelectionColumn"){		
		allAddSelection.height=33;	
		allAddSelection.width=33;	
	}
	else if(str=="allRemoveSelectionColumn"){
		allRemoveSelection.height=33;	
		allRemoveSelection.width=33;			
	}
	else if(str=="refreshSelection"){
		refreshSelection.height=33;	
		refreshSelection.width=33;			
	}
	else if(str=="WhereCondition"){
		whereBtn.height=24;	
		whereBtn.width=227;			
	}
	else if(str=="JoinCondition"){
		joinBtn.height=24;	
		joinBtn.width=227;			
	}
	else if(str=="orderByCondition"){
		orderByBtn.height=24;	
		orderByBtn.width=227;			
	}
	else if(str=="groupByCondition"){
		groupByBtn.height=24;	
		groupByBtn.width=227;			
	}
	else if(str=="havingCondition"){
		havingBtn.height=24;	
		havingBtn.width=227;			
	}
	else if(str=="Cancel"){
		cancelBtn.height=22;	
		cancelBtn.width=96;			
	}
	else if(str=="Execute"){
		executeBtn.height=22;	
		executeBtn.width=96;			
	}
	else if(str=="showResult"){
		showResultBtn.height=22;	
		showResultBtn.width=134;			
	}
}
/**
 * On Mouse UP
 **/
private function onMouseUp(str:String):void{			
	if(str=="addSelectionColumn"){
		addSelection.height=36;	
		addSelection.width=36;	
		onAddSelectonClick();	
	}
	else if(str=="removeSelectionColumn"){
		removeSelection.height=36;	
		removeSelection.width=36;
		onRemoveSelectionClick();		
	}
	if(str=="allAddSelectionColumn"){		
		allAddSelection.height=36;	
		allAddSelection.width=36;	
		onAllAddSelectonClick();
	}
	else if(str=="allRemoveSelectionColumn"){
		allRemoveSelection.height=36;	
		allRemoveSelection.width=36;
		onAllRemoveSelectionClick();			
	}
	else if(str=="refreshSelection"){
		refreshSelection.height=36;	
		refreshSelection.width=36;
		onRefreshClick();
	}
	else if(str=="WhereCondition"){
		whereBtn.height=27;	
		whereBtn.width=230;
		onClickWhereCondition();
	}
	else if(str=="JoinCondition"){
		joinBtn.height=27;	
		joinBtn.width=230;	
		onClickJoinCondition();		
	}
	else if(str=="orderByCondition"){
		orderByBtn.height=27;
		orderByBtn.width=230;
		onClickOrderBy();			
	}
	else if(str=="groupByCondition"){
		groupByBtn.height=27;
		groupByBtn.width=230;
		onClickGroupBy();			
	}
	else if(str=="havingCondition"){
		havingBtn.height=27;	
		havingBtn.width=230;	
		onClickHavingCondition();		
	}
	else if(str=="Cancel"){
		cancelBtn.height=25;	
		cancelBtn.width=99;	
		onClickCancelButton();		
	}
	else if(str=="Execute"){
		executeBtn.height=25;	
		executeBtn.width=99;	
		onExecuteClick();		
	}
	else if(str=="showResult"){		
		showResultBtn.height=25;	
		showResultBtn.width=137;
		if(showResultBtn.enabled==true){
			onClickShowResult();
		}				
	}
}
/**
 * On Mouse UP
 **/
private function omMouseOut(str:String):void{			
	if(str=="addSelectionColumn"){
		addSelection.height=36;	
		addSelection.width=36;		
	}
	else if(str=="removeSelectionColumn"){
		removeSelection.height=36;	
		removeSelection.width=36;		
	}
	if(str=="allAddSelectionColumn"){		
		allAddSelection.height=36;	
		allAddSelection.width=36;	
	}
	else if(str=="allRemoveSelectionColumn"){
		allRemoveSelection.height=36;	
		allRemoveSelection.width=36;			
	}
	else if(str=="refreshSelection"){
		refreshSelection.height=36;	
		refreshSelection.width=36;
	}
	else if(str=="WhereCondition"){
		whereBtn.height=27;	
		whereBtn.width=230;
	}
	else if(str=="JoinCondition"){
		joinBtn.height=27;	
		joinBtn.width=230;			
	}
	else if(str=="orderByCondition"){
		orderByBtn.height=27;
		orderByBtn.width=230;			
	}
	else if(str=="groupByCondition"){
		groupByBtn.height=27;
		groupByBtn.width=230;			
	}
	else if(str=="havingCondition"){
		havingBtn.height=27;	
		havingBtn.width=230;			
	}
	else if(str=="Cancel"){
		cancelBtn.height=25;	
		cancelBtn.width=99;			
	}
	else if(str=="Execute"){
		executeBtn.height=25;	
		executeBtn.width=99;			
	}
	else if(str=="showResult"){
		showResultBtn.height=25;	
		showResultBtn.width=137;			
	}
}
