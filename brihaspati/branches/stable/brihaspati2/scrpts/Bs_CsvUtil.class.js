/**
* csv util class. csv = comma separated value.
*   $c =& new Bs_CsvUtil;
*   dump($c->csvFileToArray('/some/path/file.csv'));
* 
* dependencies: Bs_String.lib.js
* 
* @author     andrej arn <andrej-at-blueshoes-dot-org>
* @copyright  blueshoes.org
* @version    4.4.$Revision: 1.2 $ $Date: 2004/06/24 17:50:46 $
* @package    javascript_core
* @subpackage util
* @access     pseudostatic
* @since      bs4.4
*/
function Bs_CsvUtil()  {
  
	/**
	* dummy var because we need at least one for phpdocumentor.
	* @access private
	*/
	this.foo = '';
  
  /**
  * Constructor. not called yet.
  */
  this.Bs_CsvUtil = function() {
  }
  
  
  /**
  * takes a csv-string and returns it as a 2-dim vector.
  * @param  string string
	* @param  string separator        (default is ';'. set to 'auto' if you don't know, it will then be guessed. see this.guessSeparator().)
	* @param  bool   removeHeader     (default is false, if the first line should be removed cause it's a header.)
	* @param  bool   removeEmptyLines (if empty lines should be removed. default is false... you might want to change this. especially empty lines at the eof are annoying.)
  * @param  bool   checkMultiline   (default is false, see _checkMultiline())
  * @return array
  * @see    csvArrayToArray()
  */
  this.csvStringToArray = function(string, separator, trim, removeHeader, removeEmptyLines, checkMultiline) {
		//if (typeof(separator)        == 'undefined') separator        = ';';
		if (typeof(separator)        == 'undefined') separator        = ',';
		if (typeof(trim)             == 'undefined') trim             = 'none';
		if (typeof(removeHeader)     == 'undefined') removeHeader     = false;
		if (typeof(removeEmptyLines) == 'undefined') removeEmptyLines = false;
		if (typeof(checkMultiline)   == 'undefined') checkMultiline   = false;
		
    if (string.length == 0) return new Array;
    var array = string.split("\n");
    
    //short hack: on windows we should explode by "\r\n". if not, the elements in array still end with \r.
    //so let's remove that ... --andrej
		for (var i=0; i<array.length; i++) {
			if (array[i].substr(array[i].length -1) == "\r") {
        array[i] = array[i].substr(0, array[i].length -1);
      }
    }
		
		if ((typeof(array) != 'object') || (array.length == 0)) return new Array;
		
    if (checkMultiline) array = this._checkMultiline(array);
		if (separator == 'auto') separator = this.guessSeparator(array);
    
    return this.csvArrayToArray(array, separator, trim, removeHeader, removeEmptyLines);
  }
  
  
  /**
  * 
  * reads in a cvs array and returns it as a 2-dim vector.
  * 
  * cvs = comma separated value. you can easily export that from 
  * an excel file for example. it looks like:
  * 
  * headerCellOne;headerCellTwo;headerCellThree
  * dataCellOne;dataCellTwo;dataCellThree
  * apple;peach;banana;grapefruit
  * linux;windows;mac
  * 1;2;3
  * 
  * note  I: all returned array elements are strings even if the values were numeric.
  * note II: it may be that one array has another array-length than another. in the example 
  *          above, the fruits have 4 elements while the others just have 3. this is not 
  *          catched. ideally every sub-array would have 4 elements. this would have to be 
  *          added when needed, maybe with another param in the function call.
  * 
  * @access public pseudostatic
  * @param  string fullPath (fullpath to the cvs file)
  * @param  array  array (hash or vector where the values are the csv lines)
  * @param  string separator (cell separator, default is ';')
  * @param  string trim (if we should trim the cells, default is 'none', can also be 'left', 'right' or 'both'. 'none' kinda makes it faster, omits many function calls, remember that.)
  * @param  bool   removeHeader (default is FALSE. would remove the first line which usually is the title line.)
  * @param  bool   removeEmptyLines (default is FALSE. would remove empty lines, that is, lines where the cells are empty. white spaces count as empty aswell.)
  * @return array (2-dim vector. it may be an empty array if there is no data.)
  * @throws bool FALSE on any error.
  * @see    csvStringToArray()
  */
  this.csvArrayToArray = function(array, separator, trim, removeHeader, removeEmptyLines) {
		//if (typeof(separator)        == 'undefined') separator        = ';';
		if (typeof(separator)        == 'undefined') separator        = ',';
		if (typeof(trim)             == 'undefined') trim             = 'none';
		if (typeof(removeHeader)     == 'undefined') removeHeader     = false;
		if (typeof(removeEmptyLines) == 'undefined') removeEmptyLines = false;
		
    switch (trim) {
      case 'none':
        var trimFunction = false;
        break;
      case 'left':
        var trimFunction = 'ltrim';
        break;
      case 'right':
        var trimFunction = 'rtrim';
        break;
      default: //'both':
        var trimFunction = 'trim';
        break;
    }
    
    var sepLength = separator.length;
    
    if (removeHeader) {
      array.shift();
    }
    
    var ret = new Array;
		for (var i=0; i<array.length; i++) {
			var line = array[i];
      var offset    = 0;
      var lastPos   = 0;
      var lineArray = new Array;
			for (var j=0; j<1; j--) { //endless
        //find the next separator
        var pos = line.indexOf(separator, offset);
        if (pos == -1) {
          //no more separators.
          lineArray[lineArray.length] = line.substr(lastPos);
          break;
        }
        //now let's see if it is inside a field value (text) or it is a real separator. 
        //it can only be a separator if the number of quotes (") since the last separator 
        //is straight (not odd).
        var currentSnippet = line.substr(lastPos, pos-lastPos);
        //var numQuotes = substr_count(currentSnippet, '"');
				var numQuotes = currentSnippet.split('"').length -1;
        if ((numQuotes % 2) == 0) {
          //that's good, we got the next field. the separator was a real one.
          lineArray[lineArray.length] = line.substr(lastPos, pos-lastPos);
          lastPos = pos + sepLength;
        } else {
          //have to go on, separator was inside a field value.
        }
        offset = pos + sepLength;
      }
      
      //trim if needed
      if (trimFunction != false) {
				try {
					for (var j=0; j<lineArray.length; j++) {
						if (trimFunction == 'trim') {
							lineArray[j] = bs_trim(lineArray[j]);
						} else if (trimFunction == 'ltrim') {
							lineArray[j] = bs_ltrim(lineArray[j]);
						} else if (trimFunction == 'rtrim') {
							lineArray[j] = bs_rtrim(lineArray[j]);
						}
					}
				} catch (e) {
					//Bs_String.lib.js is not included. thus the trim functions are missing. well fuck it, 
					//it's not trimmed.
				}
      }
      
      //remove quotes around cell values, and unescape other quotes.
			for (var j=0; j<lineArray.length; j++) {
        if ((lineArray[j].substr(0, 1) == '"') && (lineArray[j].substr(1, 1) != '"') && (lineArray[j].substr(lineArray[j].length -1) == '"')) {
          //string has to look like "hello world" and may not look like ""hello. 
          //if two quotes are together, it's an escaped one. csv uses ms-escape style.
          lineArray[j] = lineArray[j].substring(1, lineArray[j].length -1);
        }
        //now un-escape the other quotes
        lineArray[j] = lineArray[j].replace(/""/, '"');
      }
      
      //removeEmptyLines
      var addIt = true;
      if (removeEmptyLines) {
      	var addIt = false;
				for (var j=0; j<lineArray.length; j++) {
					try {
						var tmp = bs_trim(lineArray[j]);
					} catch (e) {
						//again, s_String.lib.js is not included
						var tmp = lineArray[j];
					}
          if (tmp != '') {
						addIt = true;
						break;
					}
        }
      }
      
      if (addIt) {
        ret[ret.length] = lineArray;
			}
    }
    
    return ret;
  }
  
	
	/**
	* @todo all
	*/
	this.guessSeparator = function(cvsArray) {
		//if (cvsArray[0].indexOf(';')  >= 0) return ';';
		if (cvsArray[0].indexOf(',')  >= 0) return ',';
		if (cvsArray[0].indexOf("\t") >= 0) return "\t";
		return false;
	}
  
  
  /**
	* @todo all
  */
  this._checkMultiline = function(input) {
		return input;
  }
  
  
} // end Class
