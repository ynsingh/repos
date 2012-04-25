/**
* 
* @package    javascript_core
* @subpackage html
*/

/*
will disappear, use toString(16) and toString(10)! --andrej
*/
function GiveDec(Hex) {
   if (Hex == "A") Value = 10;
   else if(Hex == "B") Value = 11;
   else if(Hex == "C") Value = 12;
   else if(Hex == "D") Value = 13;
   else if(Hex == "E") Value = 14;
   else if(Hex == "F") Value = 15;
   else Value = eval(Hex);
   return Value;
}
function GiveHex(Dec) {
   if(Dec == 10) Value = "A";
   else if(Dec == 11) Value = "B";
   else if(Dec == 12) Value = "C";
   else if(Dec == 13) Value = "D";
   else if(Dec == 14) Value = "E";
   else if(Dec == 15) Value = "F";
   else Value = "" + Dec;
   return Value;
}
function HexToDec(Input) {
   Input = Input.toUpperCase();
   a = GiveDec(Input.substring(0, 1));
   b = GiveDec(Input.substring(1, 2));
   c = GiveDec(Input.substring(2, 3));
   d = GiveDec(Input.substring(3, 4));
   e = GiveDec(Input.substring(4, 5));
   f = GiveDec(Input.substring(5, 6));
   outRed   = (a * 16) + b;
   outGreen = (c * 16) + d;
   outBlue  = (e * 16) + f;
   out = new Array(outRed, outGreen, outBlue);
   return out;
}
function DecToHex(Red, Green, Blue) {
   a = GiveHex(Math.floor(Red / 16));
   b = GiveHex(Red % 16);
   c = GiveHex(Math.floor(Green / 16));
   d = GiveHex(Green % 16);
   e = GiveHex(Math.floor(Blue / 16));
   f = GiveHex(Blue % 16);
   out = a + b + c + d + e + f;
   return out;
}


/**
* @deprecated see bs_filterForHtml (same code, different name.)
*/
function filterForHtml(str) {
  return bs_filterForHtml(str);
}

/**
* filters the given string to use in html.
* @param  string str
* @return string
*/
function bs_filterForHtml(str) {
  // NOTE: '&' first to replace! Then repace the rest
  str = str.replace(/&/g,'&amp;').replace(/'/g,'&#039;').replace(/"/g,'&quot;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
  // Then repace white spaces with the corresponding entity code 
  str = str.replace(/\r/g,'&#x0D;').replace(/\f/g,'&#x0C;').replace(/\n/g,'&#x0A;').replace(/\t/g,'&#x09;');
  return str;
}

function bs_unfilterForHtml(str) {
  // NOTE: '&' first to replace! Then repace the rest
  str = str.replace(/&amp;/g,'&').replace(/&#039;/g,'\'').replace(/&quot;/g,'"').replace(/&lt;/g,'<').replace(/&gt;/g,'>');
  // Then repace white spaces with the corresponding entity code 
  str = str.replace(/&#x0D;/g,'\r').replace(/&#x0C;/g,'\f').replace(/&#x0A;/g,'\n').replace(/&#x09;/g,'\t');
  return str;
}

function filterForHtml2(str) {
 //str = filter("&quot;", "\"", str)
 //str = filter("&#39;",  "'", str)
 //str = filter("&#10;",  "\n", str)
 //str = filter("&#13;",  "\r", str)
 str = filter("&lt;",   "<", str)
 str = filter("&gt;",   ">", str)
  /*
  str = str.replace("&quot;", "\"");
  str = str.replace("&#39;",  "'");
  str = str.replace("&#10;",  "\n");
  str = str.replace("&#13;",  "\r");
  str = str.replace("&lt;",   "<");
  str = str.replace("&gt;",   ">");
  */
  str = str.replace("<>", ""); //bug in filter function?
  return str;
}

function filter(inTag, outTag, inString) {
	split = inString.split(inTag)
	var outString = '';
	if (split.length > 0) {
		for(i=0; i<split.length; i++) {
			if (i==split.lenth) {
				outString += split[i];
			} else {
				outString += split[i] + outTag;
			} // end else
		} // end for	

		return outString;
	} else {
		return inString;
	} // end else
} // end filter


/**
* removes html code/tags/formatting from the given string. 
* another name would be html2text().
* 
* this is currently very, very hacky and dirty. we use 
* insertAdjacentHTML(), innerHTML and innerText.
* 
* it should be done better, i recommend to use string.replace() (regexp).
* the way it is done now is save cause we let ie do the work. but it is 
* dirty because we insert invisible stuff at the end of the document.
* 
* @param  string str
* @return string
* @since bs4.4
*/
function bs_stripTags(str) {
	//set up an invisible tag we can use for our work:
	var hackyTagName = 'bs_stripTags_helperTag';
	var hackyTag = document.getElementById(hackyTagName);
	if (hackyTag == null) {
		var tags    = document.getElementsByTagName('body');
		var bodyTag = tags[0];
		bodyTag.insertAdjacentHTML('BeforeEnd', '<div id="' + hackyTagName + '" style="display:none;"></div>');
		hackyTag = document.getElementById(hackyTagName);
	}
	hackyTag.innerHTML = str;
	return hackyTag.innerText;
	
}

/**
* this is currently very, very hacky and dirty. we use 
* insertAdjacentHTML(), innerHTML and innerText.
* 
* 
* a "simple tag" is a tag that closes itself, for example an image.
*   <img src="foo">
*   while a <td>foo</td> would not be a simple tag.
* 
* @param  string tagStr (eg "<img src='foo.gif' border='1'>")
* @return array (hash, may be empty)
*/
function bs_parseSimpleTagProps(tagStr) {
	//the hackyTag needs to be visible for a moment. if we only use display:none; then 
	//the width attribute of an img will become 0, no matter what it is set to. :/
	
	//set up an invisible tag we can use for our work:
	var hackyTagName = 'bs_parseSimpleTagProps_helperTag';
	var hackyTag = document.getElementById(hackyTagName);
	if (hackyTag == null) {
		var tags    = document.getElementsByTagName('body');
		var bodyTag = tags[0];
		bodyTag.insertAdjacentHTML('BeforeEnd', '<div id="' + hackyTagName + '" style="display:none;"></div>');
		hackyTag = document.getElementById(hackyTagName);
	}
	hackyTag.style.display = 'block';
	hackyTag.innerHTML     = tagStr;
	var myTag              = hackyTag.children[0];
	
	
	var ret = new Array;
	for (var prop in myTag.attributes) {
		try {
			var x = 'style';
			if (prop == x) alert('prop: ' + prop + ' ' + typeof(myTag.attributes[prop]));
			if (myTag.attributes[prop]['name'] == x) {
				alert(myTag.attributes[prop]['name']);
				alert(myTag.attributes[prop]['value']);
			}
		} catch (e) {}
		switch (typeof(myTag.attributes[prop])) {
			case 'object':
				if (myTag.attributes[prop]['specified']) {
					ret[myTag.attributes[prop]['name']] = myTag.attributes[prop]['value'];
				}
				break;
			case 'string':
				ret[prop] = myTag.attributes[prop];
				break;
			case 'undefined':
				break;
		}
	}
	hackyTag.style.display = 'none';
	return ret;
}


/**
* loops up the parents and finds the tag we're looking for. returns false if not found.
* 
* examples:
*   var tag = findWrappingElement('A', myElm);
*   var tag = findWrappingElement(new Array('BOLD', 'STRONG'), myElm);
*   var tag = findWrappingElement('A', myElm, new Array('BODY', 'TABLE'));
*   var tag = findWrappingElement(new Array('BOLD', 'STRONG'), new Array('BODY', 'TABLE'));
* 
* @param  mixed findTagName (what we look for, in uppercase, eg 'A' for a link. can be a string or an array.)
* @param  object obj     (an object we can use to do parentElement() etc.)
* @param  array  stopTags (when these tags are reached the function stops and returns false. default is 'BODY', you might want to include 'TABLE' too.)
* @return object
* @throws bool false if not found. this method loops recursively. if we reached the BODY, we'll give up.
*/
function findWrappingElement(findTagName, obj, stopTags) {
	if (typeof(stopTags)    == 'undefined') stopTags    = new Array('BODY');
	if (typeof(findTagName) == 'string')    findTagName = new Array(findTagName);
	
	if (typeof(obj.tagName) != 'undefined') {
		//alert(obj.parentElement.tagName);
		var tagName = obj.tagName.toUpperCase();
		
		//hit?
		//if (tagName == findTagName) return obj;
		//if (findTagName.contains(tagName)) return obj;
		for (var i=0; i<findTagName.length; i++) {
			if (findTagName[i] == tagName) return obj;
		}
		
		//stop tags
		//if (tagName == 'BODY') return false;
		//if (stopTags.contains(tagName)) return false;
		for (var i=0; i<stopTags.length; i++) {
			if (stopTags[i] == tagName) return false;
		}
	}
	
	try {
		if (obj.parentElement) {
			var newObj = obj.parentElement();
		} else {
			var newObj = obj.item(0).parentElement;
		}
	} catch (e) {
		return false;
	}
	return findWrappingElement(findTagName, newObj, stopTags);
}


/**
* if we're inside a tag of the type specified, then the selection is expanded to 
* the full tag. otherwise the original textRange object is returned.
* 
* a "simple tag" is a tag that closes itself, for example an image.
*   <img src="foo">
*   while a <td>foo</td> would not be a simple tag.
* it can still be used to parse the props of a <td> tag.
* 
* @param  object textRange (a textrange object, see http://msdn.microsoft.com/workshop/author/dhtml/reference/objects/obj_textrange.asp)
* @param  string (eg 'img')
* @return object (textRange)
*/
function expandSelectionToSimpleTag(textRange, tagName) {
	tagName        = tagName.toLowerCase();
	var r2         = textRange.duplicate();
	var weArInside = false;
	
	for (var i=0; i>-1; i++) { //endless
		if (r2.text.substr(0,1) == '<') {
			if (r2.text.length < (tagName.length +1)) {
				//it may look like "<im" now, but we check for "<img". have to expand on the right.
				r2.moveEnd('character', tagName.length + 1 - r2.text.length);
			}
			if (r2.text.substr(0, tagName.length +1).toLowerCase() == ('<' + tagName)) {
				for (var j=0; j>-1; j++) { //endless
					if (r2.text.substr(r2.text.length -1, 1) == '>') {
						weArInside = true;
						break;
					}
					var moved = r2.moveEnd('character', 1);
					if (moved != 1) break;
					if (j > 1000) break; //give up
				}
			}
			break;
		}
		var moved = r2.moveStart('character', -1);
		if (moved != -1) break;
		if (i > 10000) break; //give up
	}
	if (weArInside) return r2;
	return textRange;
}
