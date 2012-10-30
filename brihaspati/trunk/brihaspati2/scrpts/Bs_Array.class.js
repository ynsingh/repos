/**
* this code extends javascripts built in array class.
* if you include that file, all your arrays immediatly have that 
* functionality. 
* @author     andrej arn <andrej-at-blueshoes-dot-org>
* @package    javascript_core
* @subpackage lang
* @since      bs4.2
*/


/*
function bs_array_clone(deep) {
  var objectClone = new this.constructor();
  for (var property in this)
    if (!deep)
      objectClone[property] = this[property];
    else if (typeof this[property] == 'object')
      objectClone[property] = this[property].bs_array_clone(deep);
    else
      objectClone[property] = this[property];
  return objectClone;
}
Array.prototype.clone = bs_array_clone;
*/


/**
* moves the index specified one up in the vector.
* if the given key is out-of-bounds, the original array is returned.
* @access public
* @param  int key (index)
* @return array (the new array)
* @since  bs4.4
* @see    moveDown()
*/
Array.prototype.moveUp = function(key) {
	if (key == 0) return this; //noting to do, already on top
	if (key >= (this.length)) return this; //out of bounds. that would be a user-bug. 
	if (key > 1) {
		var newArr = this.slice(0, key -1);
	} else {
		var newArr = new Array;
	}
	newArr[newArr.length] = this[key];
	newArr[newArr.length] = this[key -1];
	var endArr = this.slice(key +1, this.length);
	return newArr.concat(endArr);
	
	/*
	//method tested using this code:
	var aa = new Array('a', 'b', 'c', 'd', 'e');
	bb = aa.moveUp(0);
	dump(bb);
	bb = aa.moveUp(1);
	dump(bb);
	bb = aa.moveUp(2);
	dump(bb);
	bb = aa.moveUp(4);
	dump(bb);
	bb = aa.moveUp(17);
	dump(bb);
	*/
}


/**
* moves the index specified one down in the vector.
* if the given key is out-of-bounds, the original array is returned.
* @access public
* @param  int key (index)
* @return array (the new array)
* @since  bs4.4
* @see    moveUp()
*/
Array.prototype.moveDown = function(key) {
	if (key >= (this.length -1)) return this; //noting to do, already at bottom (or out of bounds)
	if (key > 0) {
		var newArr = this.slice(0, key);
	} else {
		var newArr = new Array;
	}
	newArr[newArr.length] = this[key +1];
	newArr[newArr.length] = this[key];
	if (this.length > (key +2)) {
		var endArr = this.slice(key +2, this.length);
		return newArr.concat(endArr);
	}
	return newArr;
	/*
	//method tested using this code:
	var aa = new Array('a', 'b', 'c', 'd', 'e');
	bb = aa.moveDown(0);
	dump(bb);
	bb = aa.moveDown(1);
	dump(bb);
	bb = aa.moveDown(2);
	dump(bb);
	bb = aa.moveDown(3);
	dump(bb);
	bb = aa.moveDown(4);
	dump(bb);
	bb = aa.moveDown(5);
	dump(bb);
	bb = aa.moveDown(6);
	dump(bb);
	*/
}


/**
* moves the index specified to the beginning of the vector.
* if the given key is out-of-bounds, the original array is returned.
* @access public
* @param  int key (index)
* @return array (the new array)
* @since  bs4.4
* @see    moveToBottom()
*/
Array.prototype.moveToTop = function(key) {
	if (key == 0) return this; //noting to do, already on top
	if (key >= (this.length)) return this; //out of bounds. that would be a user-bug. 
	var startArr  = new Array(this[key]);
	var middleArr = this.slice(0, key);
	var endArr    = this.slice(key +1, this.length);
	return startArr.concat(middleArr, endArr);
	/*
	//method tested using this code:
	var aa = new Array('a', 'b', 'c', 'd', 'e');
	bb = aa.moveToTop(0);
	dump(bb);
	bb = aa.moveToTop(1);
	dump(bb);
	bb = aa.moveToTop(2);
	dump(bb);
	bb = aa.moveToTop(4);
	dump(bb);
	bb = aa.moveToTop(17);
	dump(bb);
	*/
}


/**
* moves the index specified to the end of the vector.
* if the given key is out-of-bounds, the original array is returned.
* @access public
* @param  int key (index)
* @return array (the new array)
* @since  bs4.4
* @see    moveToTop()
*/
Array.prototype.moveToBottom = function(key) {
	if (key >= (this.length -1)) return this; //noting to do, already at bottom (or out of bounds)
	
	if (key > 0) {
		var startArr = this.slice(0, key);
	} else {
		var startArr = new Array;
	}
	var middleArr = this.slice(key +1, this.length);
	var endArr    = new Array(this[key]);
	return startArr.concat(middleArr, endArr);
	/*
	//method tested using this code:
	var aa = new Array('a', 'b', 'c', 'd', 'e');
	bb = aa.moveToBottom(0);
	dump(bb);
	bb = aa.moveToBottom(1);
	dump(bb);
	bb = aa.moveToBottom(2);
	dump(bb);
	bb = aa.moveToBottom(4);
	dump(bb);
	bb = aa.moveToBottom(17);
	dump(bb);
	*/
}


/**
* returns the index position of element str in the vector.
* todo: js 1.6 already has this built in, we should check if it exists and then not define it anymore.
*       see http://developer.mozilla.org/en/docs/New_in_JavaScript_1.6
* @access public
* @param  string str
* @return int >= 0
* @throws -1
*/
Array.prototype.indexOf = function(str) {
  for(var i=0; i<this.length; i++) {
    if (this[i] == str) return i;
  }
  return -1;
};

/**
* tells if the given str is in the array.
* 
* we wanted to name this method "in" so you could do myArray.in() but 
* mozilla did not like it, thus renamed to .has()
* 
* @access public
* @param  string str
* @return bool
*/
Array.prototype.has = function(str) {
  return (this.indexOf(str) >= 0);
}

/**
* removes the item with the given index from the array.
* shrinks the array by one. 
* 
* all elements after the removed element will move one place down!
* if you don't want that, use 
*   yourArray[i] = null;
* 
* @access public
* @param  int i
* @return bool true
* @throws bool false
* @see    deleteItemHash
*/
Array.prototype.deleteItem = function(i) {
  if (i<0 || i>(this.length-1)) return false; //out of bounds
  if (i == (this.length-1)) {
    //optimized return
    this.length--;
    return true;
  }
  for (var i=(i+1); i<this.length; i++) {
    this[i-1] = this[i];
  }
  this.length--;
  return true;
};

/**
* same as deleteItem() but works with hashes.
* 
* @access public
* @return bool true
* @throws bool false
* @see    deleteItem
* @since  bs4.4
*/
Array.prototype.deleteItemHash = function(key) {
	var ret = new Array;
  for (var k in this) {
    if (k != key) ret[k] = this[k];
  }
	return ret;
}



/**
* these functions were in Bs_Array.lib.js and have been lost, by accident. put them in here now.
* bs_SpreadSheet uses them.
*/
function bs_array_maxSizeOfLevel(array, level) {
	if (!array) return 0;
		if (array.length == 0) return 0;
			if (level == 1) return array.length;
			var ret = 0;
				for (var i=0; i<array.length; i++) 
					{
						if (array[i].length > ret) ret = array[i].length;
					}
return ret;}

function bs_array_toCsv(array, separator) {
	if (typeof(separator) != 'string') separator = ',';var ret = '';
	for (var i=0; i<array.length; i++) {
		var lineA = new Array();
		for (var j=0; j<array[i].length; j++) {
			if ((array[i][j]) && (array[i][j]['value'])) {
				lineA[j] = array[i][j]['value'];
				//alert("lineA is"+lineA[j]);
				var item = lineA[j];
				item = item.replace("<br>","");
				item = item.replace("&nbsp;","");
				lineA[j] = item;
			}
		//	alert("lineA is"+lineA[j]);
		}
		//alert("----------return array value"+ lineA);
		ret += lineA.join(separator) + "\n";
		//alert("----------return array value"+ ret);
	}
	document.show.fieldValue.value = ret;
	return ret;
}

