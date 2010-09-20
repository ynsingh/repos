
/**
* init.
* 
*/

function bs_ss_pageLoaded() {
//alert("bs spreadsheet");
  mySpreadSheet = new Bs_SpreadSheet; //new public instance
  
  try {
    if (opener) parent = opener;
    //opener.bs_ss_init();
    parent.bs_ss_init();
  } catch (e) {
  }
}




/**
* website for this component:
* http://www.blueshoes.org/en/javascript/spreadsheet/
* 
* <b>Includes (+Dependences):</b>
* <code>
*    /_bsJavascript/core/html/Bs_HtmlUtil.lib.js
*    /_bsJavascript/core/lang/Bs_Array.class.js
*    /_bsJavascript/core/util/Bs_String.lib.js
*    /_bsJavascript/core/util/Bs_CsvUtil.class.js
*    /_bsJavascript/core/lang/Bs_Misc.lib.js
*    /_bsJavascript/components/spreadsheet/Bs_SpreadSheet.class.js
*    /_bsJavascript/components/spreadsheet/Bs_SpreadSheet.inc.js
*    /_bsJavascript/components/toolbar/Bs_ButtonBar.class.js
*    /_bsJavascript/components/toolbar/Bs_Button.class.js
* </code>
* 
* @version    3.1 (2003/11/04)
* @author     andrej arn <andrej-at-blueshoes-dot-org>
* @package    javascript_components
* @subpackage spreadsheet
* @copyright  blueshoes.org
*/
function Bs_SpreadSheet() {
  	  
  /**
  * the name of this object instance that is in the global scope. 
  * absolutely needed.
  * @access public
  * @var    string objectName
  */
  this.objectName;
  
  /**
  * are we in debug mode or not? default is false.
  * @access public
  * @var bool debug
  */
  this.debug = false;
  
  /**
  * if line numbers should be displayed or not.
  * default is true.
  * @access public
  * @var    bool showLineNumbers
  * @todo implement this option
  */
  this.showLineNumbers = true;
  
  /**
  * tells if the first row is a title row. this is important 
  * for sorting.
  * @access public
  * @var    bool firstRowTitle (default is false)
  * @see    firstColTitle
  */
  this.firstRowTitle = true //false;
  
  /**
  * tells if the first col is a title col. this is important 
  * for sorting.
  * @access public
  * @var    bool firstColTitle (default is false)
  * @see    firstRowTitle
  */
  this.firstColTitle = true //false;
  
  /**
  * if the button bar should be used. default is true.
  * @access public
  * @var    bool
  */
  this.useToolbar = true;
  
	/**
	* if cols may be added/removed.
	* if not then the column number is fixed.
	* @access public
	* @var    bool mayEditCols
	*/
	this.mayEditCols = true;
	
	/**
	* if rows may be added/removed.
	* if not then the row number is fixed.
	* @access public
	* @var    bool mayEditRows
	*/
	this.mayEditRows = true;
	
	/**
	* if the clipboard can be used, by buttons and shortcuts.
	* @access public
	* @var    bool mayUseClipboard
	*/
	this.mayUseClipboard = true;
	
  /**
  * if cell formatting may be used (bold, italic, underline). default is true.
  * @access public
  * @var    bool mayUseFormat
  */
  this.mayUseFormat = true;
  
  /**
  * if the cell alignments may be specified (left, center, right). default is true.
  * @access public
  * @var    bool mayUseWysiwyg
  */
  this.mayUseAlign = true;
  
  /**
  * if the cell wysiwyg editor may be used. default is true.
  * @access public
  * @var    bool mayUseWysiwyg
  */
  this.mayUseWysiwyg = true;
  
  /**
  * how the editor shoud look like. one of 
  * auto:     default
  * visible:  large/visible: expand cells as needed
  * hidden:   compact/hidden: don't show expanded cell content
  * 
  * @access private
  * @var string _drawStyle
  * @see setDrawStyle()
  * @todo re-implement this option
  */
  
	/**
	* the initial number of columns.
	* if the used data (in the constructor) has more, then there are more.
	* set to 0 if you don't want this feature.
	* @access public
	* @var    int numCols
	* @see    var numRows
	* @since  bs4.4
	*/
	this.numCols = 15;
	
	/**
	* the initial number of rows.
	* if the used data (in the constructor) has more, then there are more.
	* set to 0 if you don't want this feature.
	* @access public
	* @var    int numRows
	* @see    var numCols
	* @since  bs4.4
	*/
	this.numRows = 80;
	
	/**
	* captions for the columns, if not defined then it goes A-Z.
	* @access public
	* @var    array colCaptions
	* @see    _getColCaption()
	*/
	this.colCaptions;
	
  /**
  * array holding the table data and style information.
	* see the code of this._importData() for details.
	* 
  * @access private
  * @var    array _data
  */
  this._data;
	

  
  /**
  * name of the function in the parent (caller) window that has to be 
  * called once this window is ready to accept the data (after onLoad).
  * a good idea would be 'bs_ss_callback'. gets set in the init() function.
  * @access private
  * @var    string _callbackFunction
  */
  this._callbackFunction;
  
  /**
  * the return type. :)
  * 
  *   'array' => javascript array. that's the default.
  *   'csv'   => string, comma separated values à la excel
  * 
  * @access public
  * @var    string returnType
  */
  this.returnType = 'array';
  
  /**
  * the cell that is currently selected or in edit mode.
  * @access private 
  * @var    object _currentCell
  */
  this._currentCell;
  
  /**
  * if the cell specified in _currentCell is just 'active' or in 'edit' mode.
  * @access private
  * @var    string _currentState
  */
  this._currentState;
  
  /**
  * need in case the user cancels the edit mode to write it back.
  * @ccess private
  * @var   string _currentCellLastValue
  */
  this._currentCellLastValue;
  
  /**
  * default cell width in pixels. default is 80.
  * @access public
  * @var    int defaultCellWidth
  * @see    _cellWidth
  */
  this.defaultCellWidth = 80;
  
  /**
  * array with information about each col's width.
  * if not set then the defaultCellWidth will be used.
  * @access private
  * @var    array _cellWidth
  * @see    calculateCellWidth(), defaultCellWidth
  */
  this._cellWidth;
  
  /**
  * default cell height in pixels. default is 20.
  * @access public
  * @var    int defaultCellHeight
  * @see    _cellHeight
  */
  this.defaultCellHeight = 20;
  
  /**
  * array with information about each row's height.
  * if not set then the defaultCellHeight will be used.
  * @access private
  * @var    array _cellHeight
  * @see    calculateCellHeight(), defaultCellHeight
  */
  this._cellHeight;
  
	/**
	* not used anymore. calculated based on outer div dimensions.
	* the width of the sheet area in pixels.
	* @access public
	* @var    int sheetWidth
	* @see    var sheetHeight
	* @since  bs4.4
	*/
	//this.sheetWidth  = 450;
	this.sheetWidth  = 100;
  
	/**
	* the height of the sheet area in pixels.
	* @access public
	* @var    int sheetHeight
	* @see    var sheetWidth
	* @since  bs4.4
	*/
	//this.sheetHeight = 230;
	this.sheetHeight = 50;
  
  /**
  * don't recall what this was for, but it's needed.
  */
  this.editorI;
  /**
  * don't recall what this was for, but it's needed.
  */
  this.editorJ;
  
  
  /**
  * id of the tag we use to draw the whole spreadsheet. gets set in the init() function.
  * @access private
  * @var    string_drawTagId
  * @see    init()
  */
  this._drawTagId;
  
	/**
	* the image path for the button toolbar.
	* @access public
	* @var    string buttonsPath
	*/
	this.buttonsPath = '/brihaspati2/images/SpreadsheetImages/';
	
  
  /**
  * changes the cursor icon to something else.
  * i think this one did not work like i wanted it to, so it's not used.
  * (i wanted to set the sandbox while the computer is working...)
  * @access public
  * @param  bool work
  */
  this.setMousePointer = function(work) {
    var body = document.getElementById('body');
    if (body) {
      if (work) {
        body.style.cursor = 'hand';
      } else {
        body.style.cursor = 'hand';
      }
    }
  }
  
  
  /**
  * creates a new cell array and returns it.
  * @access private
  * @return array (hash)
  */
  this._getNewCell = function() {
    var ret = new Array();
    ret['value']     = '';
    ret['bold']      = false;
    ret['italic']    = false;
    ret['underline'] = false;
    ret['align']     = '';
    return ret;
  }
  
  
  /**
  * add a col into the data array
  * 
  * param pos:
  *   int position, value from 0-n (yes it may be 0, starts at 0 not 1.)
  *   or
  *   bool true  (left of current cell)
  *        false (right of current cell)
	*   or
	*   nothing/null/undefined => at the end
  * 
  * @access public
  * @param  mixed pos (see above)
	* @param  bool noRedraw (in case you want to do more stuff, and then redraw yourself.)
  */
  this.addCol = function(pos, noRedraw) {
    try {
      //if (isNaN(pos)) {
      if (typeof(pos) == 'boolean') {
        //we have to find out the position ourself.
        if (pos == false) {
          pos = this.getCol(this._currentCell.id) +1;
        } else {
          pos = this.getCol(this._currentCell.id);
        }
      } else if ((pos == null) || (typeof(pos) == 'undefined')) {
				pos = this._data[0].length +1;
      }
      	if (this.debug) alert("insert row at position " + pos);
      //bs_setMousePointer(true);
    
      this.updateDataFromFields();
      
			//update _cellWidth information
			var t = new Array();
      for (var i=0; i<this._cellWidth.length; i++) {
        if (pos == i) {
					t.push(this.defaultCellWidth);
				}
				t.push(this._cellWidth[i]);
			}
			this._cellWidth = t;
			
			//now update the real data
      for (var i=0; i<this._data.length; i++) {
        var numCols = this._data[i].length;
        var t = null;
        t = new Array();
        var doneAdd = false;
        for (var j=0; j<numCols; j++) {
          if (pos == j) {
            t.push(this._getNewCell());
            doneAdd = true;
          }
          t.push(this._data[i][j]);
        }
        if (!doneAdd) {
          t.push(this._getNewCell());
        }
        this._data[i] = t;
      }
      
      if (!noRedraw) this.draw();
    } catch (e) {
      //fuck it
      //alert(e);
      alert("First select cell before inserting");
    }
  }


  /**
  * add a row into the data array
  * 
  * param pos:
  *   int position, value from 0-n (yes it may be 0, starts at 0 not 1.)
  *   or
  *   bool true  (above current cell)
  *        false (below current cell)
	*   or
	*   nothing/null/undefined => at the end
  * 
  * @access public
  * @param  mixed pos (see above)
	* @param  bool noRedraw (in case you want to do more stuff, and then redraw yourself.)
  */
  this.addRow = function(pos, noRedraw) {
    try {
      if (typeof(pos) == 'boolean') {
        //we have to find out the position ourself.
        if (pos == false) {
          pos = this.getRow(this._currentCell.id) +1;
        } else {
          pos = this.getRow(this._currentCell.id);
        }
      } else if ((pos == null) || (typeof(pos) == 'undefined')) {
				pos = this._data.length +1;
			}
      if (this.debug) alert("insert row at position " + pos);
    
      this.updateDataFromFields();
      
			//update _cellHeight information
			var t = new Array();
      for (var i=0; i<this._cellHeight.length; i++) {
        if (pos == i) {
					t.push(this.defaultCellHeight);
				}
				t.push(this._cellHeight[i]);
			}
			this._cellHeight = t;
			
			//now update the real data
      var doneAdd = false;
      var t = new Array();
      for (var i=0; i<this._data.length; i++) {
        if (pos == i) {
          var tt = new Array;
          for (var j=0; j<this._data[i].length; j++) {
            tt[j] = this._getNewCell();
          }
          t.push(tt);
          doneAdd = true;
				}
        t.push(this._data[i]);
      }
      if (!doneAdd) {
				//need to add a line at the bottom, so not done yet.
				var maxCols = bs_array_maxSizeOfLevel(this._data, 2);
        var tt = new Array;
        for (var j=0; j<maxCols; j++) {
          tt[j] = this._getNewCell();
        }
        t.push(tt);
      }
      this._data = t;
      //alert(data);
      
      if (!noRedraw) this.draw();
    } catch (e) {
      //fuck it
      alert("First select cell before inserting");
    }
  }


  /**
  * remove a col from the data array
  * 
  * param pos:
  * if not given (not numeric) then the col of the currently 
  * selected cell will be removed.
  * 
  * @access public
  * @param int pos (position, value from 0-n (yes it may be 0))
  */
  this.removeCol = function(pos) {
    if (isNaN(pos)) {
      pos = this.getCol(this._currentCell.id);
    }
    
    if (this.debug) alert("remove col at position " + pos);
  
    this.updateDataFromFields();
    
		//update _cellWidth information
		var t = new Array();
    for (var i=0; i<this._cellWidth.length; i++) {
      if (pos == i) continue;
			t.push(this._cellWidth[i]);
		}
		this._cellWidth = t;
		
		//now update the real data
    for (var i=0; i<this._data.length; i++) {
      var t = new Array();
      for (var j=0; j<this._data[i].length; j++) {
        if (pos == j) continue;
        t.push(this._data[i][j]);
      }
      this._data[i] = t;
    }
    
    this.draw();
  }


  /**
  * remove a row from the data array
  * 
  * param pos:
  * if not given (not numeric) then the row of the currently 
  * selected cell will be removed.
  * 
  * @param int pos (position, value from 0-n (yes it may be 0))
  */
  this.removeRow = function(pos) {
    if (isNaN(pos)) {
      pos = this.getRow(this._currentCell.id);
    }
    
    if (this.debug) alert("remove row at position " + pos);
  
    this.updateDataFromFields();
    
		//update _cellHeight information
		var t = new Array();
    for (var i=0; i<this._cellHeight.length; i++) {
      if (pos == i) continue;
			t.push(this._cellHeight[i]);
		}
		this._cellHeight = t;
		
		//now update the real data
    var t = new Array();
    for (var i=0; i<this._data.length; i++) {
      if (pos == i) continue;
      t.push(this._data[i]);
    }
    this._data = t;
    
    this.draw();
  }
  
	
  /**
  * inits the editor with the given data. 
  * calls draw().
  * 
  * @access public
  * @param  array  data (the 2 dimensional data vector, see this._importData(), some documentation would be nice...)
  * @param  string drawTagId (id of the tag we use to draw the whole spreadsheet.)
  * @param  string callbackFunction (the js function to call when setting back the data to the caller)
  * @return void
  */
  this.init = function( data,drawTagId, callbackFunction) {
	if(this.debug) alert("data in init function"+data);
    this._importData(data);
    this._drawTagId = drawTagId;
	if(this.debug) alert("id"+ this._drawTagId);
		if (callbackFunction != null) this._callbackFunction = callbackFunction;
    this.draw();
  }
  
  
  /**
  * imports the data we got in the constructor. may have to modify the structure.
  * @access private
  * @param  array data
  * @see    _exportData()
  */
  this._importData = function(data) {
    var maxCols = bs_array_maxSizeOfLevel(data, 2);
		if (this.numCols > maxCols) maxCols = this.numCols;
    var numRows = data.length;
		if (this.numRows > numRows) numRows = this.numRows;
		
    //for (var i=0; i<data.length; i++) {
    for (var i=0; i<numRows; i++) {
      for (var j=0; j<maxCols; j++) {
				if (typeof(data[i]) == 'undefined') data[i] = new Array;
        try {
	        if (typeof(data[i][j]) != 'object') {
          	var tmpVal = data[i][j];
					} else {
	          var tmpVal = '';
					}
        } catch (e) {
          var tmpVal = '';
        }
        data[i][j] = new Object;
        data[i][j]['value']     = tmpVal;
	//alert("data value in import data"+data[i][j]['value']);
        data[i][j]["bold"]      = false;
        data[i][j]['italic']    = false;
        data[i][j]['underline'] = false;
        data[i][j]['align']     = '';
      }
    }
	//alert("data value outside loop in import data-------->"+data);
    this._data = data;
	//alert("data value-------->"+data[i][j]['value']);
  }
  
	/**
	* exports the data, and only the data (no style information) to cvs format.
	* @access public
	* @return string
	* @since  bs4.4
	*/
	this.exportDataToCsv = function() {
		var clean = new Array;
		for (var i=0; i<this._data.length; i++) {
			clean[i] = new Array;
			//alert('clean[' + i + ']'+clean[i]);
			for (var j=0; j<this._data[i].length; j++) {
				clean[i][j] = this._data[i][j]['value'];
				//alert('clean['+i+']['+j+']'+clean[i][j]);
			}
		}
		//return bs_array_toCsv(this._data, ';');
		return bs_array_toCsv(this._data, ',');
	}


	/**
	* @access private
	* @todo   all
	*/
	this._exportData = function() {
  }
  
  
  /**
  * [re]draws the editor.
  * @access public
  * @return void
  */
  this.draw = function() {
    /*
    please wait. loading...<br>
    bitte warten. am laden...<br>
    un instant svp. <br>
    */
    
		var containerElm    = document.getElementById(this._drawTagId);
		var containerWidth  = parseInt(containerElm.currentStyle.width);
		var containerHeight = parseInt(containerElm.currentStyle.height);
		var containerPos    = getAbsolutePos(containerElm, true);
		
		var layout = '';
		
		if (moz) { // && (document.getElementById('bs_ss_pasteLayerDiv') == null)
			layout += '<div id="bs_ss_pasteLayerDiv" style="z-index:15; display:none; position:absolute; background-color:#D6D3CE; padding:5px; border:1px solid black;">';
			layout += '<textarea name="bs_ss_pasteLayerTxt" id="bs_ss_pasteLayerTxt" cols="30" rows="4"></textarea><br>';
			layout += '<input type="button" name="bs_ss_pasteLayerOk" value="OK" onclick="' + this.objectName + '.pasteValue(document.getElementById(\'bs_ss_pasteLayerTxt\').value); document.getElementById(\'bs_ss_pasteLayerTxt\').value = \'\'; document.getElementById(\'bs_ss_pasteLayerDiv\').style.display = \'none\';"> ';
			layout += '<input type="button" name="bs_ss_pasteLayerCancel" value="Cancel" onclick="document.getElementById(\'bs_ss_pasteLayerTxt\').value = \'\'; document.getElementById(\'bs_ss_pasteLayerDiv\').style.display = \'none\';">';
			layout += '</div>';
		}
		
    layout    += '<div id="' + this.objectName + '_toolbarDiv" style="width:100%;"></div>';
    //layout    += '<div id="' + this.objectName + '_toolbarDiv" style=""></div>';
    layout    += '<div id="' + this.objectName + '_formulaDiv" style="width:100%; background-color:#D6D3CE; font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#404040;">';
    //layout    += '<div id="' + this.objectName + '_formulaDiv" style="background-color:#D6D3CE; font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#404040;">';
		layout    += '<div style="position:absolute; left:' + (containerPos.x + 8) + 'px;">Field: <span id="' + this.objectName + '_fieldSpan" style="color:#000000;"></span></div>';
		layout    += '<div style="position:relative; left:120px;">Value: ';
		//layout    += '<div id="' + this.objectName + '_valueSpan" style="position:absolute; width:' + (containerWidth -160) + 'px; color:#000000; background-color:#D6D3CE;"></div>';
		layout    += '<span id="' + this.objectName + '_valueSpan" style="color:#000000;"></span>';
		layout    += '</div>';
		layout    += '</div>';
		
		if ((containerElm.currentStyle.overflow == 'auto') || (containerElm.currentStyle.overflow == 'scroll')) { // (containerElm.currentStyle.overflow == 'visible') || 
			var sheetDivOverflow = 'visible';
			var sheetDivHeight   = '100%';
		} else {
			var sheetDivOverflow = 'auto';
			var sheetDivHeight   = (containerHeight -38) + 'px';
			//var sheetDivHeight   = '350px';
		}
		//layout    += '<div id="' + this.objectName + '_spreadSheetDiv" style="width:820px; height:' + sheetDivHeight + '; overflow:' + sheetDivOverflow + ';"></div>';
		layout    += '<div id="' + this.objectName + '_spreadSheetDiv" style="height:400px; overflow: scroll;"></div>';
		//sheetDivOverflow = 'scroll';
		//layout    += '<div id="' + this.objectName + '_spreadSheetDiv" style="height:' + sheetDivHeight + '; overflow:' + sheetDivOverflow + ';"></div>';
    document.getElementById(this._drawTagId).innerHTML = layout;
    
    if (this.useToolbar) this._loadToolbar();
    
    if ((this._currentCell) && (this._currentState == 'active')) {
      //there is currently an active cell. after the redraw we should 
      //activate it again.
      var reactivateCurrentCell = this._currentCell.id;
    }
    
		var fullTableWidth = 30;
    this.calculateCellWidth();
    this.calculateCellHeight();
    //this.calculateCellSizes();
    var numCols = this.getNumCols();
    var numRows = this.getNumRows();
		
    if (this.debug) alert("building table with " + numCols + " cols and " + this._data.length + " rows.");
    
    //building title row
    var titleRow = new Array;
    titleRow[titleRow.length] = '<tr>';
    titleRow[titleRow.length] = '<td width="25" bgcolor="#DEDBD6" style="border-bottom-color: Black; border-bottom-style: solid; border-bottom-width: 1px; border-right-color: Black; border-right-style: solid; border-right-width: 1px;">&nbsp;</td>';
    for (var i=0; i<numCols; i++) {
      var tdId = 'col_autonumber_' + (i+1);
      titleRow[titleRow.length] = '<td id="' + tdId + '" width="' + this._cellWidth[i] + '" bgcolor="#DEDBD6" align="middle" style="border-bottom-color: Black; border-bottom-style: solid; border-bottom-width: 1px; border-right-color: Black; border-right-style: solid; border-right-width: 1px;">';
      titleRow[titleRow.length] = '<table width="100%" height="100%" cellpadding="0" cellspacing="0"><tr><td align="middle" id="' + tdId + '_hack">';
      titleRow[titleRow.length] = this._getColCaption(i);
      titleRow[titleRow.length] = '</td><td align="right" width="3">';
      titleRow[titleRow.length] = '<div style="cursor:col-resize;" ondragstart="resizeColStart(\'' + tdId + '\')" ondrag="resizeCol(\'' + tdId + '\')" ondragend="resizeColEnd(\'' + tdId + '\')"><img src="/_bsImages/spacer.gif" width="3" height="10"></div>';
      titleRow[titleRow.length] = '</td></tr></table>';
      titleRow[titleRow.length] = '</td>';
    }
    titleRow[titleRow.length] = '</tr>';
    
    var out = new Array;
    var addRowLine = '';
    //for (var i=0; i<this._data.length; i++) {
    for (var i=0; i<numRows; i++) {
      var lineNumber = i+1;
      var outLine = new Array;
      outLine[outLine.length] = '<tr>\n';
      var outWhile = new Array;
      
      //add line number cell
      var tdId = 'row_autonumber_' + lineNumber;
      outWhile[outWhile.length] = '<td id="' + tdId + '" width="25" height="' + this._cellHeight[i] + '"valign="bottom" align="right" bgcolor="#DEDBD6" style="border-bottom-color: Black; border-bottom-style: solid; border-bottom-width: 1px;">';
      outWhile[outWhile.length] = '<table width="100%" height="100%" cellpadding="0" cellspacing="0"><tr><td valign="bottom" align="right" id="' + tdId + '_hack">';
      outWhile[outWhile.length] = lineNumber + '&nbsp;';
      outWhile[outWhile.length] = '</td></tr><tr height="3"><td align="middle" valign="bottom" height="3">';
      outWhile[outWhile.length] = '<span style="cursor:row-resize;" ondragstart="resizeRowStart(\'' + tdId + '\')" ondrag="resizeRow(\'' + tdId + '\')" ondragend="resizeRowEnd(\'' + tdId + '\')"><img src="/_bsImages/spacer.gif" width="10" height="3"></span>';
      outWhile[outWhile.length] = '</td></tr></table>';
      outWhile[outWhile.length] = '</td>';
			
      for (var j=0; j<numCols; j++) {
        var colNumber = j+1;
        //var cellValue = (this._data[i][j]) ? filterForHtml(this._data[i][j]) : '';
        //alert(i + " " + j);
        //try {
		//alert("this._data in draw method"+this._data[i][j]['value']);
          var cellValue   = (typeof(this._data[i][j]['value']) != 'undefined') ? this._data[i][j]['value'] : ''; // '&nbsp;'
			//alert("cell value in draw method"+cellValue);
					var formatStyle = this._getStyleStringForCell(i, j);
        //} catch (e) {
        //  var cellValue   = '';
				//	var formatStyle = '';
        //  //continue;
        //}
				if (i==0) fullTableWidth += this._cellWidth[j] +1; //+1 for the border
				
        //outWhile[outWhile.length] = '<td id="td_data[' + i + '][' + j + ']" width=' + this._cellWidth[j] + ' height=' + this._cellHeight[i] + ' style="width:' + this._cellWidth[j] + '; height:' + this._cellHeight[i] + ';" onclick="'+this.objectName+'.cellSelectTd(this);" ondblclick="'+this.objectName+'.editCellStartTd(this);" >';
        //outWhile[outWhile.length] = '<td id="td_data[' + i + '][' + j + ']" height=' + this._cellHeight[i] + ' style="height:' + this._cellHeight[i] + ';" onclick="'+this.objectName+'.cellSelectTd(this);" ondblclick="'+this.objectName+'.editCellStartTd(this);" >';
        outWhile[outWhile.length] = '<td id="td_data[' + i + '][' + j + ']" height=' + this._cellHeight[i] + ' style="height:' + this._cellHeight[i] + ';" onclick="'+this.objectName+'.editCellStartTd(this);" >'; //ondblclick="'+this.objectName+'.editCellStartTd(this);" >';
        outWhile[outWhile.length] = '<div name="data[' + i + '][' + j + ']" id="data[' + i + '][' + j + ']" ';
        outWhile[outWhile.length] = 'onkeydown="return ' + this.objectName + '.typing();" ';
        outWhile[outWhile.length] = 'onclick="'    + this.objectName + '.cellSelect(this);" ';
        outWhile[outWhile.length] = 'ondblclick="' + this.objectName + '.editCellStart(this);" ';
        outWhile[outWhile.length] = 'onpaste="'    + this.objectName + '.onPaste(this);" ';
        outWhile[outWhile.length] = 'style="' + formatStyle + ' position:relative; left:0; top:0; width:100%; height:100%; background-color:white; overflow:hidden; z-index:60;">';
        //outWhile[outWhile.length] = 'style="' + formatStyle + ' position:relative; left:0; top:0; background-color:white; overflow:hidden; z-index:60;">';
        outWhile[outWhile.length] = cellValue;
        outWhile[outWhile.length] = '</div>';
        outWhile[outWhile.length] = '</td>';
      }
      
      outLine[outLine.length] = outWhile.join('');
      outLine[outLine.length] = "</tr>\n";
      
      out[out.length] = outLine.join('');
      addRowLine = '';
      kSave = i;
    }
  	
    //var tableString = '<div style="z-index:50; width:' + this.sheetWidth + 'px; height:' + this.sheetHeight + 'px; overflow: visible;"><table width="' + fullTableWidth + '" id="dataTable" border="1" cellspacing="0" cellpadding="0" bordercolor="#DEDBD6" bgcolor="#FFFFFF">\n';
    //var tableString = '<div style="z-index:50; height:' + this.sheetHeight + 'px; overflow: scroll;"><table id="dataTable" border="1" cellspacing="0" cellpadding="0" bordercolor="#DEDBD6" bgcolor="#FFFFFF">\n';
    var tableString = '<div style="z-index:50; height:' + (sheetDivHeight -40) + 'px; overflow:scroll;"><table id="dataTable" border="1" cellspacing="0" cellpadding="0" bordercolor="#DEDBD6" bgcolor="#FFFFFF">\n';
    tableString += titleRow.join('');
    tableString += out.join('');
    tableString += '</table></div>\n';
    
    //alert(tableString);
    document.getElementById(this.objectName + '_spreadSheetDiv').innerHTML = tableString;
    
    //update context menu
    //attachMenuRow();
    //attachMenuCol();
    
    if (reactivateCurrentCell) {
	//alert("reactive cell");
      //let's reactivate that cell after the redraw.
      //it may not be exactly that cell, and it may fail completely. cause 
      //when we insert/remove rows/cols... you know.
      var cell = document.getElementById(reactivateCurrentCell);
	//alert("cell is"+cell);
      if (cell) {
        this.cellSelect(cell);
      }
    }
  }
  
  
  /**
  * loads the button bar.
  * @access private
  * @return void
  */
  this._loadToolbar = function() {
		bar = new Bs_ButtonBar();
		//bar.imgPath    = this.buttonsPath;
		bar.useHelpBar = false; //true;
		
		/*if (this.mayEditRows) {
			btnAddRowAbove = new Bs_Button();
			btnAddRowAbove.objectName = 'btnAddRowAbove';
			btnAddRowAbove.title = 'Insert row above';
			btnAddRowAbove.imgName = 'bs_tblInsertRowAbove';
			btnAddRowAbove.imgPath = this.buttonsPath;
			btnAddRowAbove.attachEvent(function(){mySpreadSheet.addRow(true)});
			bar.addButton(btnAddRowAbove, 'Insert row above');
			
			btnAddRowBelow = new Bs_Button();
			btnAddRowBelow.objectName = 'btnAddRowBelow';
			btnAddRowBelow.title = 'Insert row below';
			btnAddRowBelow.imgName = 'bs_tblInsertRowBelow';
			btnAddRowBelow.imgPath = this.buttonsPath;
			btnAddRowBelow.attachEvent(function(){mySpreadSheet.addRow(false)});
			bar.addButton(btnAddRowBelow, 'Insert row below');
			
			btnRemoveRow = new Bs_Button();
			btnRemoveRow.objectName = 'btnRemoveRow';
			btnRemoveRow.title = 'Remove row';
			btnRemoveRow.imgName = 'bs_tblRemoveRow';
			btnRemoveRow.imgPath = this.buttonsPath;
			btnRemoveRow.attachEvent(function(){mySpreadSheet.removeRow()});
			bar.addButton(btnRemoveRow, 'Remove row');
	    
			bar.newGroup();
		}
		
		if (this.mayEditCols) {
			btnAddColLeft = new Bs_Button();
			btnAddColLeft.objectName = 'btnAddColLeft';
			btnAddColLeft.title = 'Insert col left';
			btnAddColLeft.imgName = 'bs_tblInsertColLeft';
			btnAddColLeft.imgPath = this.buttonsPath;
			btnAddColLeft.attachEvent(function(){mySpreadSheet.addCol(true)});
			bar.addButton(btnAddColLeft, 'Insert col left');
			
			btnAddColRight = new Bs_Button();
			btnAddColRight.objectName = 'btnAddColRight';
			btnAddColRight.title = 'Insert col right';
			btnAddColRight.imgName = 'bs_tblInsertColRight';
			btnAddColRight.imgPath = this.buttonsPath;
			btnAddColRight.attachEvent(function(){mySpreadSheet.addCol(false)});
			bar.addButton(btnAddColRight, 'Insert col right');
			
			btnRemoveCol = new Bs_Button();
			btnRemoveCol.objectName = 'btnRemoveCol';
			btnRemoveCol.title = 'Remove col';
			btnRemoveCol.imgName = 'bs_tblRemoveCol';
			btnRemoveCol.imgPath = this.buttonsPath;
			btnRemoveCol.attachEvent(function(){mySpreadSheet.removeCol()});
			bar.addButton(btnRemoveCol, 'Remove col');
			
			bar.newGroup();
		}
		
		btnSortAsc = new Bs_Button();
		btnSortAsc.objectName = 'btnSortAsc';
		btnSortAsc.title = 'Sort Ascending';
		btnSortAsc.imgName = 'bs_sortAsc';
		btnSortAsc.imgPath = this.buttonsPath;
		btnSortAsc.attachEvent(function(){mySpreadSheet.sortAsc()});
		bar.addButton(btnSortAsc, 'Sort Ascending');
		
		btnSortDesc = new Bs_Button();
		btnSortDesc.objectName = 'btnSortDesc';
		btnSortDesc.title = 'Sort Descending';
		btnSortDesc.imgName = 'bs_sortDesc';
		btnSortDesc.imgPath = this.buttonsPath;
		btnSortDesc.attachEvent(function(){mySpreadSheet.sortDesc()});
		bar.addButton(btnSortDesc, 'Sort Descending');*/
		
    /*if (this.mayUseClipboard) {
			bar.newGroup();
			
			btnPaste = new Bs_Button();
			btnPaste.objectName = 'btnPaste';
			btnPaste.title = 'Paste';
			btnPaste.imgName = 'bs_paste';
			btnPaste.imgPath = this.buttonsPath;
			btnPaste.attachEvent(function(){mySpreadSheet.onGlobalPaste()});
			bar.addButton(btnPaste, 'Paste the clipboard at the current position.');
		}*/
		
    if (this.mayUseFormat) {
			bar.newGroup();
			
			btnBold = new Bs_Button();
			btnBold.objectName = 'btnBold';
			btnBold.title = 'Bold';
			btnBold.imgName = 'bs_formatBold_en'; //_de
			btnBold.imgPath = this.buttonsPath;
			btnBold.group   = 'bold';
			btnBold.attachEvent(function(){mySpreadSheet.formatBold(true)});
			bar.addButton(btnBold, 'Format the selected cell as bold.');
			
			btnItalic = new Bs_Button();
			btnItalic.objectName = 'btnItalic';
			btnItalic.title = 'Italic';
			btnItalic.imgName = 'bs_formatItalic_en'; //_de
			btnItalic.imgPath = this.buttonsPath;
			btnItalic.group   = 'italic';
			btnItalic.attachEvent(function(){mySpreadSheet.formatItalic(true)});
			bar.addButton(btnItalic, 'Format the selected cell as italic.');
			
			btnUnderline = new Bs_Button();
			btnUnderline.objectName = 'btnUnderline';
			btnUnderline.title = 'Underline';
			btnUnderline.imgName = 'bs_formatUnderline';
			btnUnderline.imgPath = this.buttonsPath;
			btnUnderline.group   = 'underline';
			btnUnderline.attachEvent(function(){mySpreadSheet.formatUnderline(true)});
			bar.addButton(btnUnderline, 'Format the selected cell as underlined.');
    }
    
    if (this.mayUseAlign) {
			bar.newGroup();
			
			btnAlignLeft = new Bs_Button();
			btnAlignLeft.objectName = 'btnAlignLeft';
			btnAlignLeft.title = 'Align left';
			btnAlignLeft.imgName = 'bs_alignLeft';
			btnAlignLeft.imgPath = this.buttonsPath;
			btnAlignLeft.group   = 'align';
			btnAlignLeft.attachEvent(formatAlign);
			bar.addButton(btnAlignLeft, 'Align the selected cell to the left.');
			
			btnAlignCenter = new Bs_Button();
			btnAlignCenter.objectName = 'btnAlignCenter';
			btnAlignCenter.title = 'Align center';
			btnAlignCenter.imgName = 'bs_alignCenter';
			btnAlignCenter.imgPath = this.buttonsPath;
			btnAlignCenter.group   = 'align';
			btnAlignCenter.attachEvent(formatAlign);
			bar.addButton(btnAlignCenter, 'Align the selected cell to the middle.');
			
			btnAlignRight = new Bs_Button();
			btnAlignRight.objectName = 'btnAlignRight';
			btnAlignRight.title = 'Align right';
			btnAlignRight.imgName = 'bs_alignRight';
			btnAlignRight.imgPath = this.buttonsPath;
			btnAlignRight.group   = 'align';
			btnAlignRight.attachEvent(formatAlign);
			bar.addButton(btnAlignRight, 'Align the selected cell to the right.');
    }
    
    if (this.mayUseWysiwyg) {
			bar.newGroup();
			
			btnWysiwyg = new Bs_Button();
			btnWysiwyg.objectName = 'btnWysiwyg';
			btnWysiwyg.title = 'Wysiwyg Editor';
			btnWysiwyg.imgName = 'bs_webEdit';
			btnWysiwyg.imgPath = this.buttonsPath;
			btnWysiwyg.attachEvent(function(){startWysiwyg()});
			bar.addButton(btnWysiwyg, 'Open the editor to visually edit this cells content.');
    }
		
		bar.drawInto(this.objectName + '_toolbarDiv');
  }
  
  
  /**
  * returns the style information in a string ready to use for the cell specified.
  * @access private
  * @param  int col
  * @param  int row
  * @return string
  */
  this._getStyleStringForCell = function(row, col) {
    var formatStyle = '';
    
    if (this._data[row][col]['bold']) {
      formatStyle += ' font-weight:bold;'
    }
    if (this._data[row][col]['italic']) {
      formatStyle += ' font-style:italic;'
    }
    if (this._data[row][col]['underline']) {
      formatStyle += ' text-decoration:underline;'
    }
    
    if ((this._data[row][col]['align']) && (this._data[row][col]['align'] != '')) {
      //formatStyle += ' text-align:' + this._data[row][col]['align'] + ';';
      formatStyle += ' text-align:' + this._data[row][col]['align'] + ',';
    }
    
    return formatStyle;
  }
  
  
  /**
  * walks through the structure of the data array and replaces all values 
  * with the new ones that are currently set in the page/form by the user.
  * @access public
  * @return void
  */
  this.updateDataFromFields = function() {
    //i don't think that method is needed anymore. every change to the data 
    //writes it itself automatically. so no need to collect.
    /*
    numCols = this.getNumCols();
    for (var i=0; i<this._data.length; i++) {
      for (var j=0; j<numCols; j++) {
        formField = document.getElementById('data[' + i + '][' + j + ']');
        if (formField) {
          //this._data[i][j] = formField.value;   //it's not a form field anymore, 
          this._data[i][j]['value'] = formField.innerHTML; //it's a div tag.
        }
      }
    }
    */
  }
  
  
  /**
  * sets the draw style. redraws the editor.
  * @param  string drawStyle
  * @return void
  */
  this.setDrawStyle = function(drawStyle) {
    this._drawStyle = drawStyle;
    this.updateDataFromFields();
    this.draw();
  }
    
  
  /**
  * set the data back to the caller.
  * @access public
  * @return void
  */
  this.save = function() {
    //not needed anymore.
    //this.updateDataFromFields();
		
    if (this.returnType == 'csv') {
	alert("return type"+this.returnType);
      //var ret = bs_array_toCsv(this._data, ';');
      var ret = bs_array_toCsv(this._data, ',');
	alert("return vlaue"+ret);
    } else {
      var ret = this._data;
    }
    //var evalStr = 'opener.' + this._callbackFunction + '(ret);';
		try {
	    var evalStr = 'parent.' + this._callbackFunction + '(ret);';
			eval(evalStr);
		} catch (e) {
			//maybe it's not in a frame, so we try it in the current scope.
			try {
		    var evalStr = this._callbackFunction + '(ret);';
				eval(evalStr);
			} catch (e) {
				alert("Could not call callback function.");
			}
		}
  }
  
	
	/**
	* @access private (used internally)
	* @param element td
	*/
	this.cellSelectTd = function(td) {
		var div = td.childNodes[0];
		this.cellSelect(div);
	}
	
  
  /**
  * selects a new cell. deselects the curently selected cell.
  * @access public
  * @param  object cell
  * @return void
  */
  this.cellSelect = function(cell) {
    if (cell != this._currentCell) {
      this._cellDeactivate(this._currentCell);
    }
    this._currentCell  = cell;
    this._currentState = 'active';
    this._cellActivate(cell);
  }
  
  /**
  * visually activates the given cell.
  * @access private
  * @param  object cell
  * @return void
  */
  this._cellActivate = function(cell) {
	this.ChkFormula(cell);

    var td = document.getElementById('td_' + cell.id);
    if (td) td.style.border = "2px solid Black";
    
    //document.getElementById('editCellInfo').innerText = 'Cell: ' + this.getCellCaption(cell.id);
    
    this._updateStyleButtons();
    
		var y = this.getCol(cell.id) +1;
		var x = this.getRow(cell.id) +1;

    var colTitle = document.getElementById('col_autonumber_' + y);
    if (colTitle) colTitle.bgColor = '#B5BED6';
    
    var rowTitle = document.getElementById('row_autonumber_' + x);
    if (rowTitle) rowTitle.bgColor = '#B5BED6';
		
		document.getElementById(this.objectName + '_fieldSpan').innerText = this._getColCaption(y-1) + '' + x;
	//	alert("field span is"+ document.getElementById(this.objectName + '_fieldSpan').innerText);
		document.getElementById(this.objectName + '_valueSpan').innerText = cell.innerText;
  }
  
  
  /**
  * visually deactivates the given cell.
  * also stops the edit mode if needed.
  * @access private
  * @param  object cell
  * @return void
  */
  this._cellDeactivate = function(cell) {
    if (cell) {
	//alert("cell deactivate is");
	if (this._currentState == 'edit') {
        this.editCellEnd(cell);
      }
      var td = document.getElementById('td_' + cell.id);
      if (td) td.style.border = "1px solid #DEDBD6";
      
      //document.getElementById('editCellInfo').innerText = '';
      
      var colTitle = document.getElementById('col_autonumber_' + (this.getCol(cell.id) +1));
      if (colTitle) colTitle.bgColor = '#DEDBD6';
      
      var rowTitle = document.getElementById('row_autonumber_' + (this.getRow(cell.id) +1));
      if (rowTitle) rowTitle.bgColor = '#DEDBD6';
      this.ChkValue(cell);
    }
  }
	/**
	* function for checking cell having formula
	* then replace formula with value
	* else call chkEdit function to edit formula for each cell
	*/
 	this.ChkValue = function(cell){
		/**
		 * getting selected cell & value of cell
		 */
		document.getElementById(this.objectName + '_valueSpan').innerText = cell.innerText;
                var y = this.getCol(cell.id) +1;
                var x = this.getRow(cell.id) +1;
                var Col = this._getColCaption(y-1) + '' + x;
		var chk = cell.innerText;
                //alert("cell.innertext"+chk);
		var val = chk.charAt(0);// getting first character of cell value
		//alert("char at "+val);
		/**
		 * check cell having formula or not
		 */
		if(val == "=")
			{//1 if
			var stemp = chk;
			stemp = stemp.toLowerCase();
			var chFrm = stemp.charAt(1);

			/**
			 * break formula and getting range of cell
			 */
			if(chFrm == "s")
				{//2 if	
					var temp = stemp.replace("=sum","");
				}//end of 2 if
			else
				{//2 else
					var temp = stemp.replace("=average","");
				}//end of 2 else
                        		//alert("temp.length========"+temp.length);
                        		var str1 = temp.replace("(","");
                        		//alert("first replace"+str1);             
                        		var str2 = str1.replace(")","");
                        		//alert("second replace"+str2);
                        		var msg = str2.split(':');
                        		//alert("msg length"+msg.length);
					var tmpArray = new Array;
					var Asval = new Array;
					var sum = 0;
					var avg = 0;
					var tmpRow;
					var ch2;
					var nitem = 0;
                        			for(var j=0; j< msg.length; j++)
                        				{//1 for
                                				var str3 = msg[j];//.trim();
                                				var ch;
                                				//alert("final value is "+str3);
                                				ch = str3.charAt(0);
                                				//alert("final character is "+ch);
								Asval[j] = this.ascii_value(ch);
								this.FindChar(ch);
								//alert("value of s"+s);
								ch2 = str3.charAt(1);
								//alert("chat at[1]"+ch2);
								tmpRow = ch2 -1;//gives row no.
								tmpArray[j] = re;
                        				}//end of 1 for
							//alert("First value"+tmpArray[0]+"second value"+tmpArray[1]);
					/**
					 * check if formula apply within column
					 */
					var t = tmpArray[0];
					var t1 = tmpArray[1];
					if(t == t1)
					{// 3 if
						alert("formula can't apply for same column");
					}// end of 3 if
					
					else
					{//3 else
                			var i=tmpRow;
                			//alert("value i in array\n\n"+i);
                			var clean = new Array;
					var j;
                        		clean[i] = new Array;
	
						/**
						 * loop for pick values from cell & sum it
						 */        
                        			for( j=tmpArray[0]; j<=tmpArray[1]; j++) 
							{//2 for
								//alert("data value for sum------>"+this._data[i][j]['value']);
								if(((this._data[i][j]['value'])== "")||(typeof(this._data[i][j]['value'])== 'undefined')||((this._data[i][j]['value'])== "-")) //this check if any cell does not have value
								{
								this._data[i][j]['value'] = 0;
								//alert("this._data[i][j]['value']"+this._data[i][j]['value']);
								}
                                				clean[i][j] = this._data[i][j]['value'];
								//}
                                				var item = clean[i][j];
								//alert("item for sum"+item);
								var x = parseInt(item);
						  		sum = sum + x;
							}//end of 2 for
					// put result value in cell
					if(chFrm == "s")
					{//4 if
						cell.innerHTML= sum;
					}// end of 4 if
					/**
					 * average formula
					 */
					else
					{// 4 else
						/**
						 * if any cell blank between formula then first check how many cells are blank
						 */
						var k = 0;
						var r = 0;
						//alert("tmpArray[0]"+tmpArray[0]+"\n\ntmpArray[1]"+tmpArray[1]);
						for(r=tmpArray[0]; r<=tmpArray[1]; r++)
						{//3 for
						//	alert("this._data[i][r]['value']"+this._data[i][r]['value']);
							if((this._data[i][r]['value'])== "0")
							{ //5 if 
							k = k + 1;	
							//alert("no of blank cells are"+k);		
							}// end of 5 if
						}// end of 3 for		
						/**
						 * getting range of cells having formula  
						 */
						//alert("Asval[0]\n"+Asval[0]+"Asval[1]\n"+Asval[1]);
						nitem = Asval[1]-Asval[0];
						/**
						 * subtract blank cell from range of cell
						 */
						nitem = nitem - k;
						/**
						 * get no. of cell having value then divide it from sum
						 */
						nitem = nitem + 1;
						avg = sum/nitem;
						/**
						 * method for getting float value
						 * with two decimal place
						 */
						avg = avg.toString();
						var pos = avg.indexOf('.');
						if(pos > 0)
						{// 6 if
						var n,a,b,c;
						n=avg.toString();
						var tempArray=new Array();
					    	tempArray=null;
	   					tempArray=n.split('.');
   						a=tempArray[0];
					        b=tempArray[1];
   						c=tempArray[1].substr(0,2);
						avg = parseFloat(a+'.'+c);
						cell.innerHTML = avg;
						}// end of 6 if
						else
						cell.innerHTML = avg;
					}// end of 4 else
					
						/**
						 * save formula along with value & cell position in array
						 * First get Cell position & then first character  
						 */
							var Colchar = Col.charAt(0);
							Colchar = Colchar.toLowerCase();
							//alert("col char "+ Colchar);
							/**
							 * then Find Character value
							 */
							this.FindChar(Colchar);
							var t = re;
							//alert("return value"+t);
							/**
							 * get cell value at that position
							 */
							var tem = this._data[i][t]['value'];
							//alert("value of cell having formula"+tem);
							var temind = tem.indexOf("$");
							if(temind!= '-1')
							{
								var tespl = tem.split('$');
								//alert("te"+tespl[1]);
								tem = tespl[1];
							}
							/**
							 * get first character of cell value
							 */
							var teind = tem.indexOf("s");
							//alert("index of s"+teind);
							if(teind!= '-1' )
								var temch = tem.charAt(teind);
							else
							{
								var teind = tem.indexOf("a");
								//alert("index of a is"+teind);
							}
							var temch = tem.charAt(teind);
							//alert("tem ch is"+temch);
							if(temch == "s")
							{
								this._data[i][t]['value']= sum + '#' + Col + '$'+ tem;
								//alert("this._data[i][t]['value']"+this._data[i][t]['value']);
							}
							if(temch == "a")
							{
								this._data[i][t]['value']= avg + '#' + Col + '$'+ tem;
								//alert("this._data[i][t]['value']"+this._data[i][t]['value']);
							}
					}//end of 3 else
			}//end of 1 if
			//else
				//this.ChkEdit(cell);
	}//end of function
		
		/**
		* method to check cell having formula or not at time of editing
		*/

		 this.ChkFormula = function(cell)
                        {
                        var cellval = cell.innerText;
                        //alert("cell value is---------->"+cellval);
                        document.getElementById(this.objectName + '_valueSpan').innerText = cell.innerText;
                        var y = this.getCol(cell.id) +1;
                        var x = this.getRow(cell.id) +1;
                        var Col = this._getColCaption(y-1) + '' + x;//getting field span value
                                if(document.uploadform.status.value == null)
                                {//1 if
                                        document.uploadform.status.value = 'edit';
                                }// end of 1 if
                                else
                                {// 1 else
                                var tmp = document.uploadform.formulaDetail.value;
                                //alert("formula value in check formula-------->"+tmp);
                                var frtmp = tmp.split("/");//split each line of formula
                                var flag;
                                for(var y = 0; y<frtmp.length; y++)
                                        {// 1 for
                                                var tmpval = frtmp[y];
                                                //alert("split value of formula is"+tmpval);
                                                var st = tmpval.split("$");//split cell position with formula
                                                for(var u = 0; u<st.length; u++)
                                                {//2 for
							var fr = st[u];
                                                        //alert("fr value is"+fr);
                                                        if(fr == Col)//match field value with formula cell position
                                                        {//2 if
                                                                //alert("value string match for formula");
                                                                cell.innerHTML = st[u+1];
                                                                flag=true;
                                                        }//end of 2 if
                                                        break;
                                                }// end of 2 for
                                        }//end of 1 for

				if(flag == true)
                                                {// 3 if
                                                        if(document.uploadform.status.value == 'edit')
                                                        document.uploadform.status.value = null;
                                                }// end of 3 if
                                                else
                                                this.ChkEdit(cell);

                                }//end of 1 else
                        }// end of function

		/**
		* This method for editing formula if any cell edit
		* Getting formula from template 
		*/

		this.ChkEdit = function(cell)
		{
			document.getElementById(this.objectName + '_valueSpan').innerText = cell.innerText;
                        var y = this.getCol(cell.id) +1;
                        var x = this.getRow(cell.id) +1;
                        var Col = this._getColCaption(y-1) + '' + x;//getting field span value
                                var tmp = document.uploadform.formulaDetail.value;
				if(tmp != "$formulaDetail")
				{// 1 if
                                //alert("formula value is-------->"+tmp);
                                var frtmp = tmp.split("/");//split each line of formula
                                //var flag;
                                for(var y = 0; y<frtmp.length; y++)
                                        {// 1 for
						//alert("come in first for loop");
                                                var tmpval = frtmp[y];
                                                //alert("split value of formula is"+tmpval);
                                                var st = tmpval.split("$");//split cell position with formula
                                                for(var u = 0; u<st.length; u++)
                                                {//2 for
							//alert("come in second for loop");
							/**
							* getting first cell position
							*/
                                                        var fr = st[u];
                                                        //alert("fr value is"+fr);
							/*
							* getting first character of cell position
							*/
							var frch = fr.charAt(0);
                                                        //alert("frch is"+frch);
                                                        frch = frch.toLowerCase();
                                                        //alert("col char "+ frch);
                                                        /**
                                                        * then Find Character value
                                                        */
                                                        this.FindChar(frch);
                                                        var r = re;// gives column no. for calling cell having formula
                                                        //alert("return value"+r);
                                                        var tmpchk = st[u+1];
							//alert("tmpchk in chkedit"+tmpchk);
							var val = tmpchk.charAt(0);
							if(val == "=")
				                        {//2 if
					                        var stemp = tmpchk;
					                        stemp = stemp.toLowerCase();
					                        var chFrm = stemp.charAt(1);

					                        /**
					                        * break formula and getting range of cell
					                        */
					                        if(chFrm == "s")
				                                {//3 if 
				                                        var temp = stemp.replace("=sum","");
				                                }//end of 3 if
					                        else
				                                {//1 else
				                                        var temp = stemp.replace("=average","");
				                                }//end of 1 else
			                                        var str1 = temp.replace("(","");
			                                        var str2 = str1.replace(")","");
			                                        var msg = str2.split(':');
			                                        var tmpArray = new Array;
			                                        var Asval = new Array;
			                                        var sum = 0;
			                                        var avg = 0;
			                                        var tmpRow;
			                                        var ch2;
			                                        var nitem = 0;
		                                                for(var j=0; j< msg.length; j++)
								 {//3 for
        	                                                        var str3 = msg[j];//.trim();
									var ch;
                        	                                        //alert("final value is "+str3);
                                	                                ch = str3.charAt(0);
                                        	                        //alert("final character is "+ch);
                                                	                Asval[j] = this.ascii_value(ch);
                                                        	        this.FindChar(ch);
                                                                	//alert("value of s"+s);
	                                                                ch2 = str3.charAt(1);
	                                                                //alert("chat at[1]"+ch2);
	                                                                tmpRow = ch2 -1;//gives row no.
	                                                                tmpArray[j] = re;
	                                                        }//end of 3 for
        	                                                //alert("First value"+tmpArray[0]+"second value"+tmpArray[1]);
			                                        var i=tmpRow;
			                                        //alert("value i in array\n\n"+i);
			                                        var clean = new Array;
			                                        var j;
	                		                        clean[i] = new Array;

                                        		        /**
		                                                *loop for pick values from cell & sum it
								 */ 
		                                                for( j=tmpArray[0]; j<=tmpArray[1]; j++)
	                                                        {//4 for
        	                                                        //alert("data value for sum------>"+this._data[i][j]['value']);
                	                                                if(((this._data[i][j]['value'])== "")||(typeof(this._data[i][j]['value'])== 'undefined')||((this._data[i][j]['value'])=="-")) //this check if any cell does not have value
                        	                                        {// 4 if
                                		                                this._data[i][j]['value'] = 0;
                                                                	}// end of 4 if
	                                                                clean[i][j] = this._data[i][j]['value'];
                                                                var item = clean[i][j];
                                                                var x = parseInt(item);
                                                                sum = sum + x;
								}//end of 4 for
                                                                var to = document.getElementById('data[' + i + '][' + r + ']');
                                                                //alert('value of sum for cell is data[' +i+ '][' +r+ ']');
								if(chFrm == "s")
								{//5 if
                                                                to.innerHTML = sum;
								this._data[i][r]['value']= sum + '#' + fr + '$'+ tmpchk;
								//alert(" this._data["+i+"]["+r+"] in edit"+ this._data[i][r]['value']);
								}// end of 5 if
								else
								{// 2 else
								 /** 
                                                		 * if any cell blank between formula then first check how 									many cells are blank
                                                		 */
                                                		var k = 0;
                                                		var l = 0;
                                                		//alert("tmpArray[0]"+tmpArray[0]+"\n\ntmpArray[1]"+tmpArray[1]);
                                                		for(l=tmpArray[0]; l<=tmpArray[1]; l++)
                                                		{//5 for
                                                		//alert("this._data[i][l]['value']"+this._data[i][l]['value']);
                                                        		if((this._data[i][l]['value'])== "0")
                                                        		{ //6 if 
                                                        			k = k + 1;      
                                                        			//alert("no of blank cells are"+k);             
                                                        		}// end of 6 if
                                                		}// end of 5 for                
                                                		/**
                                                		* getting range of cells having formula  
                                                		*/
                                                		//alert("Asval[0]\n"+Asval[0]+"Asval[1]\n"+Asval[1]);
                                                		nitem = Asval[1]-Asval[0];
                                                		/**
                                                		* subtract blank cell from range of cell
                                                		*/
                                                		nitem = nitem - k;
                                                		/**
                                                		* get no. of cell having value then divide it from sum
                                                		*/
                                                		nitem = nitem + 1;
                                                		avg = sum/nitem;
                                                		/**
                                                		* method for getting float value
                                                		* with two decimal place
                                                		*/
						 		avg = avg.toString();
                                               	 		var pos = avg.indexOf('.');
                                                		if(pos > 0)
                                                			{// 7 if
                                                			var n,a,b,c;
                                                			n=avg.toString();
                                                			var tempArray=new Array();
			                                                tempArray=null;
			                                                tempArray=n.split('.');
			                                                a=tempArray[0];
			                                                b=tempArray[1];
			                                                c=tempArray[1].substr(0,2);
			                                                avg = parseFloat(a+'.'+c);
			                                                to.innerHTML = avg;
									this._data[i][r]['value']= avg + '#' + fr + '$'+ tmpchk;
                                                                	//alert(" this._data["+i+"]["+r+"]in edit"+ this._data[i][r]['value']);

		                                                	}// end of 7 if
		                                                else
									{// 3 else
			                                        	to.innerHTML = avg;
									this._data[i][r]['value']= avg + '#' + fr + '$'+ tmpchk;
                                                                	//alert(" this._data["+i+"]["+r+"] in edit"+ this._data[i][r]['value']);
									}// end of 3 else
								}// end of 2 else
						
							}//end of 2 if
						break;
						}//end of 2 for
					}//end of 1 for
			}// end of 1 if
		}//end of function

				

		/**
		*this function return integer value based on ascii value
		*/
                  this.FindChar = function(val)
                        {
                                var aval=this.ascii_value(val);
                                //alert("\n\n\n val in another method\n"+aval);
                                switch(aval)
                                        {
                                        case 97: re='0';
                                                break;
                                        case 98: re='1';
                                                break;
                                        case 99: re='2';
                                                break;
                                        case 100: re='3';
                                                break;
                                        case 101: re='4';
                                                break;
                                        case 102: re='5';
                                                break;
                                        case 103: re='6';
                                                break;
                                        case 104: re='7';
                                                break;
                                        case 105: re='8';
                                                break;
                                        case 106: re='9';
                                                break;
                                        case 107: re='10';
                                                break;
                                        case 108: re='11';
                                                break;
                                        case 109: re='12';
                                                break;
                                        case 110: re='13';
                                                break;
                                        case 111: re='14';
                                                break;
					 case 112: re='15';
                                                break;
                                        case 113: re='16';
                                                break;
                                        case 114: re='17';
                                                break;
                                        case 115: re='18';
                                                break;
                                        case 116: re='19';
                                                break;
                                        case 117: re='20';
                                                break;
                                        case 118: re='21';
                                                break;
                                        case 119: re='22';
                                                break;
                                        case 120: re='23';
                                                break;
                                        case 121: re='24';
                                                break;
                                        case 122: re='25';
                                                break;
                                        default:
                                                alert("Invalid Character");
                            		}
			}

		/**
		*this method return ascii value
		*/
		this.ascii_value = function (c)
		{
			// restrict input to a single character
			c = c . charAt (0);

			// loop through all possible ASCII values
			var i;
			for (i = 0; i < 256; ++ i)
			{
				// convert i into a 2-digit hex string
				var h = i . toString (16);
				if (h . length == 1)
					h = "0" + h;

				// insert a % character into the string
					h = "%" + h;

				// determine the character represented by the escape code
					h = unescape (h);

				// if the characters match, we've found the ASCII value
			if (h == c)
			break;
			}
			return i;
		}

	/**
	* @access private (used internally)
	* @param element td
	*/
	this.editCellStartTd = function(td) {
		var div = td.childNodes[0];
		this.editCellStart(div);
	}
  
  /**
  * makes the given cell the active one, starts edit mode.
  * @access public
  * @param  object cell
  * @return void
  */
  this.editCellStart = function(cell) {
  if (cell != this._currentCell) {
      this.cellDeactivate(this._currentCell);
    }
 
    this._currentCell  = cell;
    this._currentState = 'edit';
    this._currentCellLastValue = cell.innerHTML; //filterForHtml()
	//alert("this._currentCellLastValue="+this._currentCellLastValue);
		if (ie) {
	    cell.style.left = cell.offsetLeft; //50;
 	if (this.debug)	alert(" left value is"+cell.style.left);
	    cell.style.top  = cell.offsetTop; //30;
 	if (this.debug)	alert("top value is"+cell.style.top);
	    cell.style.position = "absolute";
 	if (this.debug)	alert("position value is"+cell.style.position);
	    cell.style.overflow = "visible";
 	if (this.debug)	alert("overflow value is"+cell.style.overflow);
	    cell.style.width = 500;
 	if (this.debug)	alert("width value is"+cell.style.width);
	    cell.style.zIndex = 700;
 	if (this.debug)	alert("zindex value is"+cell.style.zIndex);
	    cell.contentEditable = true;
 	if (this.debug)	alert("editable value is"+cell.contentEditable);
	    cell.focus(); //that line is needed. strange things happen otherwise. --andrej
	if (this.debug) alert("cell focus is"+cell.focus);
		} else {
			var val=document.getElementById(this._currentCell.id);
			var left = cell.offsetLeft+'px';
			//val.style.top=val.style.pixelTop;
		if (this.debug)	alert("style left is"+left);
			var top = cell.offsetTop+'px';
		if (this.debug)	alert("style top is"+top);
			val.style.position = "relative";
		if (this.debug)	alert("style position is"+val.style.position);
			val.style.overflow = "visible";
		if (this.debug)	alert("style overflow is"+val.style.overflow);
			val.style.width = 500;//+'px';
		if (this.debug)	alert("width"+val.style.width);
			 val.style.zIndex = 700;
		if (this.debug) alert("zindex is" +val.style.zIndex);
			val.contentEditable = true;
		if (this.debug)	alert("editable is"+val.contentEditable);
			val.focus();
		//return startImmediate(val);
		/*if ((cell.innerText == false) || (cell.innerText == null))return;
		 cell.innerHTML = cell.innerText;*/
			/*var newVal = window.prompt('Type in the new value', this._currentCellLastValue);
			if ((newVal == false) || (newVal == null)) return;
			cell.innerHTML = newVal;
			alert("new val is"+cell.innerHTML);*/
			//this.editCellEnd(cell);
		}
  }
  
  
  /**
  * stops edit mode for the given cell, goes back into active mode.
  * @access public
  * @param  object cell
  * @return void
  */
  this.editCellEnd = function(cell) {
    /*
    var y = '';
    var i=0;
    for (x in cell) {
      y += x + ' ' + cell[x] + "       ";
      if (i > 3) {
        i=0;
        y += "\n";
      }
      i++;
    }
    alert(y);
    alert(cell.style.height);
    */
    
    /*
    //auto-resize the width/height?
    if (parseInt(cell.style.height) != cell.clientHeight) {
      //cell.style.height = cell.clientHeight;
      this._cellHeight[this.getRow(cell.id)] = cell.clientHeight;
      this.updateDataFromFields();
      this.draw();
    }
    */
    
    //write the value back to the data array
	//alert("edit cell end is");
    var row = this.getRow(cell.id);
    var col = this.getCol(cell.id);
    this._data[row][col]['value'] = cell.innerHTML;
		//alert("value inside array in edit cell end"+ this._data[row][col]['value']);
    //set the styles back.
	cell.style.position = "relative";
	cell.style.overflow = "hidden";
	cell.style.left = 0;
	cell.style.top = 0;
	cell.style.width = this._cellWidth[this.getCol(cell.id)];
	cell.style.zIndex = 50;
	cell.contentEditable = false;
	this._currentState = 'active';

		document.getElementById(this.objectName + '_valueSpan').innerText = cell.innerText;
		//alert("value of cell innertext in edit cell end"+ document.getElementById(this.objectName + '_valueSpan').innerText);

  }
  
  
  /**
  * handles the keydown event. 
  * @access public (you don't need that)
  * @return bool (true to continue handling the event, false to stop.)
  */
  this.typing = function() {
	 if(this.debug) alert("typing is");
    //esc 27
    //back-space 8
    //del 127
    
    if (this._currentState == 'edit') {
	 if(this.debug) alert("current satate in typing"+this._currentState);
      var goOn = false;
      switch (window.event.keyCode) {
	//if(this.debug) alert("switch case is"+window.event.keyCode);
        case 13: //enter
          //if (event.altKey) { //alt-key does not work here together with enter :(
          if (event.shiftKey) { //so we use shift to force a new-line.
            return true;
            //this._currentCell.innerHTML += "<br>\n";
            //return false; //alt-enter forces a newline
          }
	alert("edit cell end in typing");
          this.editCellEnd(this._currentCell);
          ///this.cellSelect(this._currentCell);
          return false;
        case 27: //esc
	 // if(this.debug) alert("for esc");
          this._currentCell.innerHTML = this._currentCellLastValue;
          this.editCellEnd(this._currentCell);
          this.cellSelect(this._currentCell);
          return false;
        case 40: //cursor-down
        case 9:  //horizontal tab
        case 39: //cursor-right
        case 38: //cursor-up
        case 37: //cursor-left
          this.editCellEnd(this._currentCell);
          goOn = true;
      }
      if (!goOn) {
	 if(this.debug) alert("not go on"+goOn);
        return true;
      }
    }
    
    //if ((this._currentState == 'active') || ) {
      if (event.ctrlKey) {
	//if(this.debug) alert("event ctrl is"event.ctrlKey);
        if (window.event.keyCode == 66) { //b    the german f[ett] is reserved for ctrl-f => find
          this.formatBold(true);
          this._updateStyleButtons();
          return false;
        } else if (window.event.keyCode == 73) { //i
          this.formatItalic(true);
          this._updateStyleButtons();
          return false;
        } else if (window.event.keyCode == 85) { //u
          this.formatUnderline(true);
          this._updateStyleButtons();
          return false;
        } else if (window.event.keyCode == 86) { //v
					this.onGlobalPaste(this._currentCell); //that is a paste.
					return false;
				}
        return true; //hitting ctrl and something? d'oh.
      }
      switch (window.event.keyCode) {
        case 13: //enter
        case 40: //cursor-down
          var row = this.getRow(this._currentCell.id);
          var col = this.getCol(this._currentCell.id);
          var t = document.getElementById('data[' + (row +1) + '][' + col + ']');
          if (t) {
            this.cellSelect(t);
          } else {
            //last row
            t = document.getElementById('data[0][' + col + ']');
            this.cellSelect(t);
          }
          return false;
        case 9:  //horizontal tab
        case 39: //cursor-right
          var row = this.getRow(this._currentCell.id);
          var col = this.getCol(this._currentCell.id);
          var t = document.getElementById('data[' + row + '][' + (col +1) + ']');
          if (t) {
            this.cellSelect(t);
          } else {
            if (window.event.keyCode == 9) {
              //end of lign, let's try the next row
              t = document.getElementById('data[' + (row +1) + '][0]');
            } else {
              //end of lign, let's jump back to the begin of the line
              t = document.getElementById('data[' + (row) + '][0]');
            }
            if (t) {
              this.cellSelect(t);
            } else {
              //end of sheet, let's jump back to the top
              t = document.getElementById('data[0][0]');
              this.cellSelect(t);
            }
          }
          return false;
        case 38: //cursor-up
        case 13: //shift-13 TODO
          var row = this.getRow(this._currentCell.id);
          var col = this.getCol(this._currentCell.id);
          var t = document.getElementById('data[' + (row -1) + '][' + col + ']');
          if (t) {
            this.cellSelect(t);
          } else {
            //last row
            t = document.getElementById('data[' + (this.getNumRows() -1) + '][' + col + ']');
            this.cellSelect(t);
          }
          return false;
        case 37: //cursor-left
          var row = this.getRow(this._currentCell.id);
          var col = this.getCol(this._currentCell.id);
          var t = document.getElementById('data[' + row + '][' + (col -1) + ']');
          if (t) {
            this.cellSelect(t);
          } else {
            //begin of lign, let's try the next row
            t = document.getElementById('data[' + (row) + '][' + (this.getNumCols() -1) + ']');
            if (t) {
              this.cellSelect(t);
            } else {
              //begin of sheet, let's jump to the end
              t = document.getElementById('data[' + (this.getNumRows() -1) + '][' + (this.getNumCols() -1) + ']');
              this.cellSelect(t);
            }
          }
          return false;
        case 113: //F2
          this.editCellStart(this._currentCell);
          return true;
        default:
          //alert(window.event.keyCode);
          if ((window.event.keyCode >= 32) && (window.event.keyCode <= 126)) {
            //assume the user wants to edit that cell...
            this.editCellStart(this._currentCell); //needs to be before removeContent() !!!
            this.removeContent(this._currentCell);
            return true;
          }
      }
    //}
    return false;
  }
  
	
	/**
	* 
	*/
	this.onPaste = function(cell) {
		//alert('onPaste()');
		/*
		//alert(cell);
		var clipValOrig = window.clipboardData.getData("Text");
		if (clipValOrig.indexOf("\t") >= 0) {
			//multiple lines
		} else {
			cell.innerHTML = clipValOrig;
		}*/
		event.returnValue = true;
	}
	
	/**
	* fires on a paste on a cell. by hitting ctrl-v or clicking the paste button.
	* @access public (you won't need it)
	* @param  element cell
	* @return void
	*/
	this.onGlobalPaste = function(cell) {
		if (typeof(cell) == 'undefined') cell = this._currentCell;
		if (bs_isNull(cell)) {
			alert('Select a cell first!');
			return;
		}
		
		if (ie) {
			//var clipValOrig = window.clipboardData.getData("Text");
			var clipValOrig = this._readFromClipboard("Text");
			this.pasteValue(clipValOrig, cell);
		} else { //moz
			/*
			var clipValOrig = window.prompt('Paste your clipboard', '');
			if ((clipValOrig == false) || (clipValOrig == null)) return;
			*/
			var layerDiv = document.getElementById('bs_ss_pasteLayerDiv');
			layerDiv.style.display = 'block';
			var pos = getAbsolutePos(document.getElementById(btnPaste._getId()), true);
			layerDiv.style.left = pos.x + 'px';
			layerDiv.style.top  = pos.y + 20 + 'px';
			document.getElementById('bs_ss_pasteLayerTxt').focus();
		}
	}
	
	
	/**
	* @access public
	* @param  string  value
	* @param  element cell
	* @return void
	*/
	this.pasteValue = function(value, cell) {
		if (typeof(cell) == 'undefined') cell = this._currentCell;
		
		if ((value.indexOf("\t") >= 0) || (value.indexOf("\n") >= 0)) {
			//alert(value);
			var cvsUtil = new Bs_CsvUtil();
			var clipArr = cvsUtil.csvStringToArray(value, 'auto', false, false, false, true);
			//dump(clipArr);
			//now let's insert that...
			var startRow = this.getRow(cell.id);
			var startCol = this.getCol(cell.id);
			for (var i=0; i<clipArr.length; i++) {
				for (var j=0; j<clipArr[i].length; j++) {
					this.setValue(clipArr[i][j], startRow +i, startCol +j, true);
				}
			}
			this.draw();
		} else {
			this.setValue(value, this.getRow(cell.id), this.getCol(cell.id));
		}
	}
	
  
  /**
  * clears the content of the given cell.
  * @access public static
  * @param  object cell
  * @return void
  * @todo i think this method exists twice with different names and code.
  */
  this.removeContent = function(cell) {
    cell.innerHTML = '';
		
  }
  
  
  /**
  * returns the row number of the cell with the given name.
	* starts at 0, not 1.
  * @access public static
  * @param  string cellName
  * @return int (0-n)
  */
  this.getRow = function(cellName) {
    //cell_1_1
    //var posTwo = cellName.lastIndexOf('_');
    //return parseInt(cellName.substring(5, posTwo));
    //data[' + i + '][' + j + ']" 
    var posTwo = cellName.indexOf(']');
    return parseInt(cellName.substring(5, posTwo));
  }
  
  
  /**
  * returns the col number of the cell with the given name.
	* starts at 0, not 1.
  * @access public static
  * @param  string cellName
  * @return int (0-n)
  */
  this.getCol = function(cellName) {
    //cell_1_1
    //var posTwo = cellName.lastIndexOf('_');
    //return parseInt(cellName.substr(posTwo +1));
    //data[' + i + '][' + j + ']" 
    var posTwo = cellName.lastIndexOf('[');
    return parseInt(cellName.substr(posTwo +1, cellName.length));
  }
  
  /**
  * returns a string like C5 or B7.
  * @access public
  * @param  string cellId
  * @return int
  */
  this.getCellCaption = function(cellId) {
    return this._getColCaption(cellId) + (this.getRow(cellId) +1);
  }
  
  
  /**
  * returns the number of rows.
  * @access public
  * @return int
  */
  this.getNumRows = function() {
    try {
      return this._data.length;
    } catch (e) {
      return 0;
    }
  }
  
  /**
  * returns the number of cols.
  * @access public
  * @return int
  */
  this.getNumCols = function() {
    return bs_array_maxSizeOfLevel(this._data, 2);
  }
  
  
  /**
  * calculates the width for each column.
  * @access public
  * @return void
  * @see this._cellWidth
  */
  this.calculateCellWidth = function() {
    var numCols = this.getNumCols();
		if (typeof(this._cellWidth) == 'undefined') {
	    this._cellWidth = new Array();
		}
    for (var i=0; i<numCols; i++) {
			if (typeof(this._cellWidth[i]) != 'undefined') continue;
      var t = document.getElementById('col_autonumber_' + (i+1));
      if (t != null) {
        this._cellWidth[i] = t.width;
      } else {
        this._cellWidth[i] = this.defaultCellWidth;
      }
    }
  }
  
  
  /**
  * calculates the height for each row.
  * @access public
  * @return void
  * @see this._cellHeight
  */
  this.calculateCellHeight = function() {
    /*
    var numCols = this.getNumCols();
    var numRows = this.getNumRows();
    for (var i=0; i<numCols; i++) {
      for (var j=0; j<numRows; j++) {
        var t = document.getElementById('row_autonumber_' + (i+1));
        if (t) {
          alert(t.clientHeight);
          alert(t.height);
          this._cellHeight[i] = t.clientHeight; //t.height;
        } else {
          this._cellHeight[i] = this.defaultCellHeight;
        }
      }
      
    }
    */
    var numRows = this.getNumRows();
		if (typeof(this._cellHeight) == 'undefined') {
	    this._cellHeight = new Array();
		}
    for (var i=0; i<numRows; i++) {
			if (typeof(this._cellHeight[i]) != 'undefined') continue;
      var t = document.getElementById('row_autonumber_' + (i+1));
      if (t != null) {
        this._cellHeight[i] = t.height;
      } else {
        this._cellHeight[i] = this.defaultCellHeight;
      }
      
      //this._cellHeight[i] = this.defaultCellHeight;
    }
  }
  
	/**
	* !!! not used [anymore?] !!!
	*/
  this.calculateCellSizes = function() {
    var numCols = this.getNumCols();
    var numRows = this.getNumRows();
    
    this._cellWidth  = new Array();
    this._cellHeight = new Array();
    
    var width  = this.defaultCellWidth;
    var height = this.defaultCellHeight;
    
    for (var i=0; i<numCols; i++) {
      for (var j=0; j<numRows; j++) {
        var cell = document.getElementById('data[' + i + '][' + j + ']');
        
        if (cell) {
          width  = cell.clientWidth;
          height = cell.clientHeight;
        }
        
        if ((!this._cellWidth[i]) || (width > this._cellWidth[i])) {
          this._cellWidth[i] = width;
        }
        
        if ((!this._cellHeight[j]) || (height > this._cellHeight[j])) {
          this._cellHeight[j] = height;
        }
      }
    }
  }
  
  
  /**
  * removes the content of a cell, col, row or the whole table.
  * if row is NaN then the whole row is emptied. same for col. 
  * currently only works if both row and col are given. 
  * @access public
  * @param  int row
  * @param  int col
  * @return void
  * @todo read above
  */
  this.emptyValue = function(row, col) {
    this.setValue('', row, col);
  }
  
  /**
  * sets the given value for the given cell.
  * @access public
  * @param  string val
  * @param  int row (0-n)
  * @param  int col (0-n)
	* @param  bool noRedraw (in case you want to do more stuff at once, and then redraw yourself.)
  * @return void
  */
  this.setValue = function(val, row, col, noRedraw) {
	alert("inside set value"+val);
		if (this._data.length <= row) {
			for (var i=this._data.length; i<=row; i++) {
				this.addRow(null, noRedraw);
			}
		}
		if (this._data[0].length <= col) {
			for (var i=this._data[0].length; i<=col; i++) {
				this.addCol(null, noRedraw);
			}
		}
    this._data[row][col]['value'] = val;

    var cell = document.getElementById('data[' + row + '][' + col + ']');
	alert("set value cell is"+cell);
    if (cell) {
			cell.innerHTML = val;
			
			//fuck, really don't see why that's needed here:
  	  this.editCellEnd(cell);
    	this.cellSelect(cell);
		}
		
    this.updateDataFromFields();
    if (!noRedraw) this.draw();
  }
  
  
  /**
  * formats the currently selected cell bold.
  * @access public
  * @param  bool val (true = bold, false = not bold)
  * @return void
  */
  this.formatBold = function(val) {
    try {
      if (!this._currentCell) throw 'noCellSelected';
      var row = this.getRow(this._currentCell.id);
      var col = this.getCol(this._currentCell.id);
      if (this._setStyle(row, col, 'bold', val)) {
        var cell = document.getElementById('data[' + row + '][' + col + ']');
        val = (val) ? 'bold' : 'normal';
        if (cell) cell.style.fontWeight = val;
      }
    } catch (e) {
      alert(e);
    }
  }
  
  /**
  * formats the currently selected cell italic.
  * @access public
  * @param  bool val (true = italic, false = not italic)
  * @return void
  */
  this.formatItalic = function(val) {
    try {
      if (!this._currentCell) throw 'noCellSelected';
      var row = this.getRow(this._currentCell.id);
      var col = this.getCol(this._currentCell.id);
      if (this._setStyle(row, col, 'italic', val)) {
        var cell = document.getElementById('data[' + row + '][' + col + ']');
        val = (val) ? 'italic' : 'normal';
        if (cell) cell.style.fontStyle = val;
      }
    } catch (e) {
      alert(e);
    }
  }
  
  /**
  * formats the currently selected cell underline.
  * @access public
  * @param  bool val (true = underline, false = not underline)
  * @return void
  */
  this.formatUnderline = function(val) {
    try {
      if (!this._currentCell) throw 'noCellSelected';
      var row = this.getRow(this._currentCell.id);
      var col = this.getCol(this._currentCell.id);
      if (this._setStyle(row, col, 'underline', val)) {
        var cell = document.getElementById('data[' + row + '][' + col + ']');
        val = (val) ? 'underline' : 'none';
        if (cell) cell.style.textDecoration = val;
      }
    } catch (e) {
      alert(e);
    }
  }
  
  
  /**
  * formats the alignment of the current cell.
  * one or none of the given params may be true.
  * @access public
  * @param  bool left
  * @param  bool center
  * @param  bool right
  * @return void
  */
  this.formatAlign = function(left, center, right) {
    var val = '';
    if (left) {
      val = 'left';
    } else if (center) {
      val = 'center';
    } else if (right) {
      val = 'right';
    }
    
    try {
      if (!this._currentCell) throw 'noCellSelected';
      var row = this.getRow(this._currentCell.id);
      var col = this.getCol(this._currentCell.id);
      if (this._setStyle(row, col, 'align', val)) {
        if (val != '') { //only do it if not empty.
          var cell = document.getElementById('data[' + row + '][' + col + ']');
          if (cell) cell.style.textAlign = val;
        }
      }
    } catch (e) {
      alert(e);
    }
  }
  
  
  /**
  * applies the given style to the cell specified.
  * @access private
  * @param  int    row
  * @param  int    col
  * @param  string style
  * @param  mixed  val
  * @return bool
  */
  this._setStyle = function(row, col, style, val) {
  if(this.debug) alert("apply style to cell\n"+val);
    this._data[row][col][style] = val;
    return true;
  }
  
  
  /**
  * updates the style buttons in the button bar to match the settings 
  * of the currently selected cell.
  * @access private
  * @return void
  */
  this._updateStyleButtons = function() {
    var row = this.getRow(this._currentCell.id);
    var col = this.getCol(this._currentCell.id);
    
    var valBold      = false;
    var valItalic    = false;
    var valUnderline = false;
    var valAlign     = '';
    
    try {
      if (this._data[row][col]) {
        valBold      = (this._data[row][col]['bold']);
        valItalic    = (this._data[row][col]['italic']);
        valUnderline = (this._data[row][col]['underline']);
        valAlign     = (this._data[row][col]['align']);
      }
    } catch (e) {
      //never mind
    }
    
		try {
			btnBold.setStatus(valBold ? 2 : 1);
			btnItalic.setStatus(valItalic ? 2 : 1);
			btnUnderline.setStatus(valUnderline ? 2 : 1);
		} catch (e) {
			//buttons not used.
		}
    
		try {
			btnAlignLeft.setStatus((valAlign   == 'left')   ? 2 : 1);
			btnAlignCenter.setStatus((valAlign == 'center') ? 2 : 1);
			btnAlignLeft.setStatus((valAlign   == 'right')  ? 2 : 1);
		} catch (e) {
			//buttons not used.
		}
  }
  
  
  /**
  * sorts the sheet in ascending order 
  * on the col of the currently selected cell.
  * @access public
  * @return void
  * @see    sortDesc()
  */
  this.sortAsc = function() {
    if (this._currentCell) {
      var colNumber = this.getCol(this._currentCell.id);
      this._sortData(colNumber, true);
      this.draw();
    }
  }
  
  /**
  * sorts the sheet in descending order 
  * on the col of the currently selected cell.
  * @access public
  * @return void
  * @see    sortAsc()
  */
  this.sortDesc = function() {
    if (this._currentCell) {
      var colNumber = this.getCol(this._currentCell.id);
      this._sortData(colNumber, false);
      this.draw();
    }
  }
  
  /**
  * sorts the data.
  * @access private
  * @param  int  colNumber
  * @param  bool asc
  * @return void
  */
  this._sortData = function(colNumber, asc) {
    //this._data[col][row]
    this.updateDataFromFields();
    
    var tmpArray = new Array();
    var dataLength = this.getNumRows();
    var subLength  = this.getNumCols();
    for (var i=0; i<dataLength; i++) {
      tmpArray[i] = this._data[i][colNumber]['value'] + '[?!*]' + i;
	alert("in sorting "+tmpArray[i]);
    }
    tmpArray.sort();
    var tmpArrayRank = new Array();
    for (var i=0; i<dataLength; i++) {
      pos = tmpArray[i].lastIndexOf('[?!*]') + 5;
      tmpArrayRank[i] = tmpArray[i].substr(pos);
    }
    
    if (!asc) tmpArrayRank.reverse();
    
    var tmpData = this._data;
    this._data = new Array();
    for (var i=0; i<dataLength; i++) {
      this._data[i] = Array();
      for (var j=0; j<subLength; j++) {
        this._data[i][j] = tmpData[tmpArrayRank[i]][j];
      }
    }
    
    /*
    this._data[0][0] = lala
    this._data[1][0] = gaga
    
    tmpArray[0] = lala
    tmpArray[1] = gaga
    */
  }
  
  
  /**
  * exports the data table as html output. 
  * @access public
  * @param  bool withStyle (default is false)
  * @return string (html)
  */
  this.toHtml = function(withStyle) {
    //if (this.debug) alert("exporting table to html");
    
    this.updateDataFromFields();
    var numCols = this.getNumCols();
  
    var out = "<table>\n";
    for (var i=0; i<this._data.length; i++) {
      out += "  <tr>\n";
      for (var j=0; j<numCols; j++) {
        //var cellValue = (this._data[i][j]) ? filterForHtml(this._data[i][j]) : '';
        var cellValue = this._data[i][j]['data'];
        var formatStyle = this._getStyleStringForCell(i, j);
        out += '    <td style="' + formatStyle + '">' + cellValue + '</td>\n';
      }
      out += "  </tr>\n";
    }    
    return out;
  }
  
	
	this._readFromClipboard = function(key) {
		if (window.clipboardData) {
			//ie
			return window.clipboardData.getData(key);
		} else {
			//moz
			
			// dit is belangrijk maar staat nergens duidelijk vermeld:
			netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
			
			// maak een interface naar het clipboard
			var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
			if (!clip) return;
			
			// maak een transferable
			var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
			if (!trans) return;
			
			// specificeer wat voor soort data we op willen halen; text in dit geval
			trans.addDataFlavor('text/unicode');
			
			// haal de data op
			clip.getData(trans,clip.kGlobalClipboard);
			
			// om de data uit de transferable te halen hebben we 2 nieuwe objecten nodig om het in op te slaan
			var str = new Object();
			var len = new Object();
			
			// haal de data en datalengte op in de nieuwe objecten; hier vang ik errors op als type-conversion niet lukt
			try { trans.getTransferData('text/unicode',str,len); }
			catch(error) { return; }
			
			// Als het data object iets bevat converteer het naar een string object
			
			if (str) {
				// deze werkte bij mij alleen in NS7
				if (Components.interfaces.nsISupportsWString) str=str.value.QueryInterface(Components.interfaces.nsISupportsWString);
				// en deze alleen in Mozilla 1.2
				else if (Components.interfaces.nsISupportsString) str=str.value.QueryInterface(Components.interfaces.nsISupportsString);
				else str = null;
			}
			
			// haal de text op uit het data segment; de lengte is de helft van de lengte zoals opgehaald uit de transferable
			if (str) return(str.data.substring(0,len.value / 2));
			
		}
	}
  
	
	/**
	* returns the caption (visible name) of a column. goes A-Z unless colCaptions is defined.
	* @access private
	* @param  int i
	* @return string
	* @see    var colCaptions
	*/
	this._getColCaption = function(i) {
		if (typeof(this.colCaptions) != 'undefined') {
			if (typeof(this.colCaptions[i]) != 'undefined') {
				return this.colCaptions[i];
			}
		}
		return String.fromCharCode(65 +i);
	}
	
}









function editorStart(i, j) {
  divEditor.style.display = 'block';
  formField = document.getElementById('data[' + i + '][' + j + ']');
  txtEditor = document.getElementById('txtEditor');
  txtEditor.value = formField.value;
  editorI = i;
  editorJ = j;
}
function editorSave() {
  formField = document.getElementById('data[' + editorI + '][' + editorJ + ']');
  txtEditor = document.getElementById('txtEditor');
  formField.value = txtEditor.value;
  txtEditor.value = '';
  divEditor.style.display = 'none';
}
function editorCancel() {
  txtEditor.value = '';
  divEditor.style.display = 'none';
}


