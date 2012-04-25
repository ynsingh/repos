var wysiwygWin;
function startWysiwyg() {
  if (!mySpreadSheet._currentCell) {
    //a bit hacky to access a private var from outside. 
    alert("Please select a cell first.");
    return;
  }
  wysiwygWin = window.open("/_admin/wysiwyg/popup.html", "spreadsheetWysiwyg", "width=610,height=500, left=50, top=50, resizable=yes, menubar=no, toolbar=no, dependent=yes");
}
function callbackWysiwygLoaded() {
  wysiwygWin.callbackSave = 'callbackWysiwygSave';
  wysiwygWin.document.frames.bs_wysiwygEditor.setValue(mySpreadSheet._currentCell.innerHTML);
}
function callbackWysiwygSave(value) {
  mySpreadSheet._currentCell.innerHTML = value;
  mySpreadSheet.editCellEnd(mySpreadSheet._currentCell); //again hacky with private var.
}

function formatAlign() {
  mySpreadSheet.formatAlign((btnAlignLeft.getStatus() == 2), (btnAlignCenter.getStatus() == 2), (btnAlignRight.getStatus() == 2));
}


function updateStatusBar(txt) {
  document.getElementById('statusBar').innerText = txt;
}


/**
* resizing the col of the data table.
* @param  string idName (id string of the <td> tag.)
* @return void
*/
function resizeColStart(idName) {
  obj = document.getElementById(idName);
  posH = window.event.clientX;
  widthOrig = obj.width;
  diffOrigWidthToPosH = widthOrig - posH;
}

/**
* resizing the col of the data table.
* @param  string idName (id string of the <td> tag.)
* @return void
*/
function resizeColEnd(idName) {
  //now we need to update the sizes of the cells.
  //mySpreadSheet.updateDataFromFields();
  //mySpreadSheet.draw();
  obj = document.getElementById(idName);
  var number = parseInt(idName.substr(15));
	mySpreadSheet._cellWidth[number -1] = parseInt(obj.width);
	
}

/**
* resizing the col of the data table.
* @param  string idName (id string of the <td> tag.)
* @return void
*/
function resizeCol(idName) {
  try {
    obj = document.getElementById(idName);
    var diff = window.event.clientX - posH;
    obj.width = posH + diff + diffOrigWidthToPosH;
  } catch (e) {
    //there may be problems with numbers <0.
  }
}


/**
* @see resizeColStart()
*/
function resizeRowStart(idName) {
  obj = document.getElementById(idName);
  posH = window.event.clientY;
  heightOrig = obj.height;
  diffOrigHeightToPosH = heightOrig - posH;
}

/**
* @see resizeColEnd()
*/
function resizeRowEnd(idName) {
  //now we need to update the sizes of the cells.
  //mySpreadSheet.updateDataFromFields();
  //mySpreadSheet.draw();
	//alert(idName);
  //row_autonumber_1
  obj = document.getElementById(idName);
  var number = parseInt(idName.substr(15));
	mySpreadSheet._cellHeight[number -1] = parseInt(obj.height);
}

/**
* @see resizeCol()
*/
function resizeRow(idName) {
  try {
    obj = document.getElementById(idName);
    var diff = window.event.clientY - posH;
    obj.height = posH + diff + diffOrigHeightToPosH;
  } catch (e) {
    //there may be problems with numbers <0.
  }
}




/**
* adds a row, uses the event object.
* @access public
* @param  bool above
* @return void
*/
function addRow(above) {
  //row_autonumber_1
  var number = parseInt(lastContextMenuObject.substr(15));
  if (above) number--;
  mySpreadSheet.addRow(number);
}

/**
* removes a row, uses the event object.
* @access public
* @return void
*/
function removeRow() {
  //row_autonumber_1
  var number = parseInt(lastContextMenuObject.substr(15)) -1;
  mySpreadSheet.removeRow(number);
}

/**
* adds a col, uses the event object.
* @access public
* @param  bool left
* @return void
*/
function addCol(left) {
  //col_autonumber_1
  var number = parseInt(lastContextMenuObject.substr(15));
  if (left) number--;
  mySpreadSheet.addCol(number);
}

/**
* removes a col, uses the event object.
* @access public
* @return void
*/
function removeCol() {
  //col_autonumber_1
  var number = parseInt(lastContextMenuObject.substr(15)) -1;
  mySpreadSheet.removeCol(number);
}


var lastContextMenuObject;
var menuRow;
var menuCol;

//core.loadUnit("menu");
//onload=function(){
function menubar() {
  menuRow=Menu(null);
  menuRow.moveTo(150,150);
  menuRow.addItem("Remove Row","removeRow()");
  menuRow.addItem("Insert Row");
  menuRow.childNodes[1].addItem("Above", "addRow(true)");
  menuRow.childNodes[1].addItem("Below", "addRow(false)");
  menuRow.addSeparator();
  attachMenuRow();
  
  menuCol=Menu(null);
  menuCol.moveTo(150,150);
  menuCol.addItem("Remove Col","removeCol()");
  menuCol.addItem("Insert Col");
  menuCol.childNodes[1].addItem("Left", "addCol(true)");
  menuCol.childNodes[1].addItem("Right", "addCol(false)");
  menuCol.addSeparator();
  attachMenuCol();
}

function attachMenuRow() {
  var numDataLines = mySpreadSheet.getNumRows();
  for (i=1; i<=numDataLines; i++) {
    var elm2=core.getElm('row_autonumber_' + i);
    if(core.isIE){
      elm2.oncontextmenu=popupMenuRow;
      elm2.onclick=function(){menuRow.hide()}
    } else {
      elm2.onclick=popupMenuRow;
    }
  }
}

function attachMenuCol() {
  var numCols = mySpreadSheet.getNumCols();
  for (i=1; i<=numCols; i++) {
    var elm2=core.getElm('col_autonumber_' + i);
    if(core.isIE){
      elm2.oncontextmenu=popupMenuCol;
      elm2.onclick=function(){menuCol.hide()}
    } else {
      elm2.onclick=popupMenuCol;
    }
  }
}

function popupMenu(e, obj) {
  core.preventBubble(e);
  if (core.isIE) {
    obj.moveTo(event.clientX,event.clientY);
    obj.show();
    lastContextMenuObject = event.srcElement.id;
    if (lastContextMenuObject.substr(lastContextMenuObject.length -5) == '_hack') {
      lastContextMenuObject = lastContextMenuObject.substr(0, lastContextMenuObject.length -5);
    }
    return false;
  } else {
    obj.hide();
    if(e.which==3){
       obj.moveTo(e.pageX,e.pageY);
       obj.show();
       return false;
    }
  }
}

function popupMenuRow(e) {
  return popupMenu(e, menuRow);
}

function popupMenuCol(e) {
  return popupMenu(e, menuCol);
}

function invokeSave() {
  mySpreadSheet.save();
}
