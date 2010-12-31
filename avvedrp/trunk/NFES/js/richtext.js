// Cross-Browser Rich Text Editor
// http://www.kevinroth.com/rte/demo.htm
// Written by Kevin Roth (kevin@NOSPAMkevinroth.com - remove NOSPAM)

//init variables

var isRichText = false;
var rng;
var currentRTE;
var allRTEs = "";

var isIE;
var isGecko;

var imagesPath;
var includesPath;
var cssFile;

function initRTE(imgPath, incPath, css) {
	//check to see if designMode mode is available
	if (document.getElementById && document.designMode) isRichText = true;
	
	//set browser vars
	var ua = navigator.userAgent.toLowerCase();
	isIE = ((ua.indexOf("msie") != -1) && (ua.indexOf("opera") == -1) && (ua.indexOf("webtv") == -1)); 
	isGecko = (ua.indexOf("gecko") != -1 && ua.indexOf("safari") == -1);
	
	//set paths vars
	imagesPath = imgPath;
	includesPath = incPath;
	cssFile = css;
	
	//for testing standard textarea, uncomment the following line
	//isRichText = false;
}


function setCdocPageAction ( actionstring ) {

	document.forms[0].action.value = actionstring;
	
}

function writeRichText(rte, html, width, height, buttons, readOnly) {
	if (isRichText) {
		if (allRTEs.length > 0) allRTEs += ";";
		allRTEs += rte;
		writeRTE(rte, html, width, height, buttons, readOnly);
	} else {
		writeDefault(rte, html, width, height, buttons, readOnly);
	}
}

function writeDefault(rte, html, width, height, buttons, readOnly) {
	if (!readOnly) {
		document.writeln('<textarea name="' + rte + '" id="' + rte + '" style="width: ' + width + 'px; height: ' + height + 'px;">' + html + '</textarea>');
	} else {
		document.writeln('<textarea name="' + rte + '" id="' + rte + '" style="width: ' + width + 'px; height: ' + height + 'px;" readonly>' + html + '</textarea>');
	}
}

function writeRTE(rte, html, width, height, buttons, readOnly) {
	if (readOnly) buttons = false;
	if (buttons == true) {
		document.writeln('<style type="text/css">');
		document.writeln('.btnImage {cursor: pointer; cursor: hand;}');
		document.writeln('</style>');
		document.writeln('<table id="Buttons1_' + rte + '">');
		document.writeln('	<tr>');
		document.writeln('		<td>');
		document.writeln('			<select id="formatblock_' + rte + '" onchange="Select(\'' + rte + '\', this.id);">');
		document.writeln('				<option value="<p>">Normal</option>');
		document.writeln('				<option value="<p>">Paragraph</option>');
		document.writeln('				<option value="<h1>">Heading 1 <h1></option>');
		document.writeln('				<option value="<h2>">Heading 2 <h2></option>');
		document.writeln('				<option value="<h3>">Heading 3 <h3></option>');
		document.writeln('				<option value="<h4>">Heading 4 <h4></option>');
		document.writeln('				<option value="<h5>">Heading 5 <h5></option>');
		document.writeln('				<option value="<h6>">Heading 6 <h6></option>');
		document.writeln('				<option value="<address>">Address <ADDR></option>');
		document.writeln('				<option value="<pre>">Formatted <pre></option>');
		document.writeln('			</select>');
		document.writeln('		</td>');
		document.writeln('		<td>');
		document.writeln('			<select id="fontname_' + rte + '" onchange="Select(\'' + rte + '\', this.id)">');
		document.writeln('				<option value="Font" selected>Font</option>');
		document.writeln('				<option value="Arial, Helvetica, sans-serif">Arial</option>');
		document.writeln('				<option value="Courier New, Courier, mono">Courier New</option>');
		document.writeln('				<option value="Times New Roman, Times, serif">Times New Roman</option>');
		document.writeln('				<option value="Verdana, Arial, Helvetica, sans-serif">Verdana</option>');
		document.writeln('			</select>');
		document.writeln('		</td>');
		document.writeln('		<td>');
		document.writeln('			<select unselectable="on" id="fontsize_' + rte + '" onchange="Select(\'' + rte + '\', this.id);">');
		document.writeln('				<option value="Size">Size</option>');
		document.writeln('				<option value="1">1</option>');
		document.writeln('				<option value="2">2</option>');
		document.writeln('				<option value="3">3</option>');
		document.writeln('				<option value="4">4</option>');
		document.writeln('				<option value="5">5</option>');
		document.writeln('				<option value="6">6</option>');
		document.writeln('				<option value="7">7</option>');
		document.writeln('			</select>');
		document.writeln('		</td>');
		document.writeln('	</tr>');
		document.writeln('</table>');
		document.writeln('<table id="Buttons2_' + rte + '" cellpadding="1" cellspacing="0">');
		document.writeln('	<tr>');
		document.writeln('		<td><button name="bold" type="button" onClick="FormatText(\'' + rte + '\', \'bold\',\'\')"><img class="btnImage" src="' + imagesPath + 'post_button_bold.gif" width="25" height="24" alt="Bold" title="Bold"></button></td>');
		document.writeln('		<td><button name="italic" type="button" onClick="FormatText(\'' + rte + '\', \'italic\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_italic.gif" width="25" height="24" alt="Italic" title="Italic"></button></td>');
		document.writeln('		<td><button name="underline" type="button" onClick="FormatText(\'' + rte + '\', \'underline\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_underline.gif" width="25" height="24" alt="Underline" title="Underline"></button></td>');
		document.writeln('		<td>&nbsp;</td>');
		document.writeln('		<td><button name="justleft" type="button" onClick="FormatText(\'' + rte + '\', \'justifyleft\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_left_just.gif" width="25" height="24" alt="Align Left" title="Align Left"></button></td>');
		document.writeln('		<td><button name="justcenter" type="button" onClick="FormatText(\'' + rte + '\', \'justifycenter\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_centre.gif" width="25" height="24" alt="Center" title="Center"></button></td>');
		document.writeln('		<td><button name="justright" type="button" onClick="FormatText(\'' + rte + '\', \'justifyright\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_right_just.gif" width="25" height="24" alt="Align Right" title="Align Right"></button></td>');
		document.writeln('		<td><button name="justfull" type="button" onclick="FormatText(\'' + rte + '\', \'justifyfull\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_justifyfull.gif" width="25" height="24" alt="Justify Full" title="Justify Full"></button></td>');
		document.writeln('		<td>&nbsp;</td>');
		document.writeln('		<td><button name="insertlist" type="button" onClick="FormatText(\'' + rte + '\', \'insertorderedlist\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_numbered_list.gif" width="25" height="24" alt="Ordered List" title="Ordered List"></button></td>');
		document.writeln('		<td><button name="insertunlist" type="button" onClick="FormatText(\'' + rte + '\', \'insertunorderedlist\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_list.gif" width="25" height="24" alt="Unordered List" title="Unordered List"></button></td>');
		document.writeln('		<td>&nbsp;</td>');
		
//		document.writeln('		<td><button name="pasteword" type="button" onClick="FormatText(\'' + rte + '\', \'pasteword\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_pst.gif" width="25" height="24" alt="Paste" title="Paste as in Word"></button></td>');
		document.writeln('		<td><button name="inserttable" type="button" onClick="FormatText(\'' + rte + '\', \'inserttable\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_table.gif" width="25" height="24" alt="Insert Table" title="Insert Table"></button></td>');
		document.writeln('		<td><button name="tabbing" type="button" onClick="FormatText(\'' + rte + '\', \'tabbing\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_tab.gif" width="25" height="24" alt="Tab" title="Tab Space"></button></td>');
		document.writeln('		<td><button name="subscript" type="button" onClick="FormatText(\'' + rte + '\', \'subscript\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_sub.gif" width="25" height="24" alt="Subscript" title="Subscript"></button></td>');
		document.writeln('		<td><button name="superscript" type="button" onClick="FormatText(\'' + rte + '\', \'superscript\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_sup.gif" width="25" height="24" alt="Superscript" title="Superscript"></button></td>');
		
		document.writeln('		<td>&nbsp;</td>');
		document.writeln('		<td><button name="outdent" type="button" onClick="FormatText(\'' + rte + '\', \'outdent\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_outdent.gif" width="25" height="24" alt="Outdent" title="Outdent"></button></td>');
		document.writeln('		<td><button name="indent" type="button" onClick="FormatText(\'' + rte + '\', \'indent\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_indent.gif" width="25" height="24" alt="Indent" title="Indent"></button></td>');
		document.writeln('		<td><div id="forecolor_' + rte + '"><button name="forecolor" type="button" onClick="FormatText(\'' + rte + '\', \'forecolor\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_textcolor.gif" width="25" height="24" alt="Text Color" title="Text Color"></button></div></td>');
		document.writeln('		<td><div id="hilitecolor_' + rte + '"><button name="hilitecolor" type="button" onClick="FormatText(\'' + rte + '\', \'hilitecolor\', \'\')"><img class="btnImage" src="' + imagesPath + 'post_button_bgcolor.gif" width="25" height="24" alt="Background Color" title="Background Color"></button></div></td>');
		document.writeln('		<td>&nbsp;</td>');
//		document.writeln('		<td><button name="createlink" type="button" onClick="FormatText(\'' + rte + '\', \'createlink\')"><img class="btnImage" src="' + imagesPath + 'post_button_hyperlink.gif" width="25" height="24" alt="Insert Link" title="Insert Link"></button></td>');
//		document.writeln('		<td><button name="addimage" type="button" onClick="AddImage(\'' + rte + '\')"><img class="btnImage" src="' + imagesPath + 'post_button_image.gif" width="25" height="24" alt="Add Image" title="Add Image"></button></td>');
//		if (isIE) document.writeln('		<td><button name="checkspell" type="button" onClick="checkspell()"><img class="btnImage" src="' + imagesPath + 'post_button_spellcheck.gif" width="25" height="24" alt="Spell Check" title="Spell Check"></button></td>');
//		document.writeln('		<td>&nbsp;</td>');
//		document.writeln('		<td><button name="cut" type="button" onClick="FormatText(\'' + rte + '\', \'cut\')"><img class="btnImage" src="' + imagesPath + 'post_button_cut.gif" width="25" height="24" alt="Cut" title="Cut"></button></td>');
//		document.writeln('		<td><button name="copy" type="button" onClick="FormatText(\'' + rte + '\', \'copy\')"><img class="btnImage" src="' + imagesPath + 'post_button_copy.gif" width="25" height="24" alt="Copy" title="Copy"></button></td>');
//		document.writeln('		<td><button name="paste" type="button" onClick="FormatText(\'' + rte + '\', \'paste\')"><img class="btnImage" src="' + imagesPath + 'post_button_paste.gif" width="25" height="24" alt="Paste" title="Paste"></button></td>');
//		document.writeln('		<td>&nbsp;</td>');
//		document.writeln('		<td><button name="undo" type="button" onClick="FormatText(\'' + rte + '\', \'undo\')"><img class="btnImage" src="' + imagesPath + 'post_button_undo.gif" width="25" height="24" alt="Undo" title="Undo"></button></td>');
//		document.writeln('		<td><button name="redo" type="button" onClick="FormatText(\'' + rte + '\', \'redo\')"><img class="btnImage" src="' + imagesPath + 'post_button_redo.gif" width="25" height="24" alt="Redo" title="Redo"></button></td>');
		document.writeln('		<td><div id="divTemp" style="VISIBILITY: hidden; OVERFLOW: hidden; POSITION: absolute; WIDTH: 1px; HEIGHT: 1px"></div></td>');
		document.writeln('	</tr>');
		document.writeln('</table>');
	}
	document.writeln('<iframe id="' + rte + '" name="' + rte + '" width="' + width + 'px" height="' + height + 'px"></iframe>');
//	if (!readOnly) document.writeln('<br /><input type="checkbox" id="chkSrc' + rte + '" onclick="toggleHTMLSrc(\'' + rte + '\');" />&nbsp;View Source');
	document.writeln('<iframe width="254" height="174" id="cp' + rte + '" src="' + includesPath + 'palette.htm" marginwidth="0" marginheight="0" scrolling="no" style="visibility:hidden; display: none; position: absolute;"></iframe>');
	document.writeln('<input type="hidden" id="hdn' + rte + '" name="' + rte + '" value="">');
	document.getElementById('hdn' + rte).value = html;
	enableDesignMode(rte, html, readOnly);
}

function enableDesignMode(rte, html, readOnly) {
	var frameHtml = "<html id=\"" + rte + "\">\n";
	frameHtml += "<head>\n";
	//to reference your stylesheet, set href property below to your stylesheet path and uncomment
	if (cssFile.length > 0) {
		frameHtml += "<link media=\"all\" type=\"text/css\" href=\"" + cssFile + "\" rel=\"stylesheet\">\n";
	}
	frameHtml += "<style>\n";
	frameHtml += "body {\n";
	frameHtml += "	background: #FFFFFF;\n";
	frameHtml += "	margin: 0px;\n";
	frameHtml += "	padding: 0px;\n";
	frameHtml += "}\n";
	frameHtml += "</style>\n";
	frameHtml += "</head>\n";
	frameHtml += "<body>\n";
	frameHtml += html + "\n";
	frameHtml += "</body>\n";
	frameHtml += "</html>";
	
	if (document.all) {
		var oRTE = frames[rte].document;
		oRTE.open();
		oRTE.write(frameHtml);
		oRTE.close();
		if (!readOnly) oRTE.designMode = "On";
	} else {
		try {
			if (!readOnly) document.getElementById(rte).contentDocument.designMode = "on";
			try {
				var oRTE = document.getElementById(rte).contentWindow.document;
				oRTE.open();
				oRTE.write(frameHtml);
				oRTE.close();
				//oRTE.addEventListener("blur", updateRTE(rte), true);
				if (isGecko && !readOnly) {
					//attach a keyboard handler for gecko browsers to make keyboard shortcuts work
					oRTE.addEventListener("keypress", kb_handler, true);
				}
			} catch (e) {
				alert("Error preloading content.");
			}
		} catch (e) {
			//gecko may take some time to enable design mode.
			//Keep looping until able to set.
			if (isGecko) {
				setTimeout("enableDesignMode('" + rte + "', '" + html + "');", 10);
			} else {
				return false;
			}
		}
	}
}

function updateRTEs() {
	var vRTEs = allRTEs.split(";");
	for (var i = 0; i < vRTEs.length; i++) {
		updateRTE(vRTEs[i]);
	}
}

function updateRTE(rte) {
	//set message value
	var oHdnMessage = document.getElementById('hdn' + rte);
	var oRTE = document.getElementById(rte);
	var readOnly = false;

	//check for readOnly mode
//	if (document.all) {
//		if (frames[rte].document.designMode != "On") readOnly = true;
//	} else {
//		if (document.getElementById(rte).contentDocument.designMode != "on") readOnly = true;
//	}
//	
//	if (isRichText && !readOnly) {
//		//if viewing source, switch back to design view
//		if (document.getElementById("chkSrc" + rte).checked) {
//			document.getElementById("chkSrc" + rte).checked = false;
//			toggleHTMLSrc(rte);
//		}
//		
//		if (oHdnMessage.value == null) oHdnMessage.value = "";
//		if (document.all) {
//			oHdnMessage.value = frames[rte].document.body.innerHTML;
//		} else {
//			oHdnMessage.value = oRTE.contentWindow.document.body.innerHTML;
//		}
//		//if there is no content (other than formatting) set value to nothing
//		if (stripHTML(oHdnMessage.value.replace("&nbsp;", " ")) == "") oHdnMessage.value = "";
//	}	
}

function toggleHTMLSrc(rte) {
	//contributed by Bob Hutzel (thanks Bob!)
	var oRTE;
	if (document.all) {
		oRTE = frames[rte].document;
	} else {
		oRTE = document.getElementById(rte).contentWindow.document;
	}
	
	if (document.getElementById("chkSrc" + rte).checked) {
		document.getElementById("Buttons1_" + rte).style.visibility = "hidden";
		document.getElementById("Buttons2_" + rte).style.visibility = "hidden";
		if (document.all) {
			oRTE.body.innerText = oRTE.body.innerHTML;
		} else {
			var htmlSrc = oRTE.createTextNode(oRTE.body.innerHTML);
			oRTE.body.innerHTML = "";
			oRTE.body.appendChild(htmlSrc);
		}
	} else {
		document.getElementById("Buttons1_" + rte).style.visibility = "visible";
		document.getElementById("Buttons2_" + rte).style.visibility = "visible";
		if (document.all) {
			oRTE.body.innerHTML = oRTE.body.innerText;
		} else {
			var htmlSrc = oRTE.body.ownerDocument.createRange();
			htmlSrc.selectNodeContents(oRTE.body);
			oRTE.body.innerHTML = htmlSrc.toString();
		}
	}
}

//Function to format text in the text box
function FormatText(rte, command, option) {
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
		
		//get current selected range
		var selection = oRTE.document.selection; 
		if (selection != null) {
			rng = selection.createRange();
		}
	} else {
		oRTE = document.getElementById(rte).contentWindow;
		
		//get currently selected range
		var selection = oRTE.getSelection();
		rng = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
	}
	
	try {
		if ((command == "forecolor") || (command == "hilitecolor")) {
			//save current values
			parent.command = command;
			currentRTE = rte;
			
			//position and show color palette
			buttonElement = document.getElementById(command + '_' + rte);
			document.getElementById('cp' + rte).style.left = getOffsetLeft(buttonElement) + "px";
			document.getElementById('cp' + rte).style.top = (getOffsetTop(buttonElement) + buttonElement.offsetHeight) + "px";
			if (document.getElementById('cp' + rte).style.visibility == "hidden") {
				document.getElementById('cp' + rte).style.visibility = "visible";
				document.getElementById('cp' + rte).style.display = "inline";
			} else {
				document.getElementById('cp' + rte).style.visibility = "hidden";
				document.getElementById('cp' + rte).style.display = "none";
			}
		} else if (command == "tabbing"){
			AllowTabCharacter();
		} else if (command == "pasteword"){
			pasteFromWord(rte);
		} else if (command == "inserttable"){
			insertTable(rte);
		} else if (command == "createlink") {
			var szURL = prompt("Enter a URL:", "");
			try {
				//ignore error for blank urls
				oRTE.document.execCommand("Unlink", false, null);
				oRTE.document.execCommand("CreateLink", false, szURL);
			} catch (e) {
				//do nothing
			}
		} else {
			//oRTE.focus();
		  	oRTE.document.execCommand(command, false, option);
			//oRTE.focus();
		}
	} catch (e) {
		alert(e);
	}
}

//Function to set color
function setColor(color) {
	var rte = currentRTE;
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
	} else {
		oRTE = document.getElementById(rte).contentWindow;
	}
	
	var parentCommand = parent.command;
	if (document.all) {
		//retrieve selected range
		var sel = oRTE.document.selection; 
		if (parentCommand == "hilitecolor") parentCommand = "backcolor";
		if (sel != null) {
			var newRng = sel.createRange();
			newRng = rng;
			newRng.select();
		}
	} else {
		//oRTE.focus();
	}
	oRTE.document.execCommand(parentCommand, false, color);
	//oRTE.focus();
	document.getElementById('cp' + rte).style.visibility = "hidden";
	document.getElementById('cp' + rte).style.display = "none";
}

//Function to add image
function AddImage(rte) {
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
		
		//get current selected range
		var selection = oRTE.document.selection; 
		if (selection != null) {
			rng = selection.createRange();
		}
	} else {
		oRTE = document.getElementById(rte).contentWindow;
		
		//get currently selected range
		var selection = oRTE.getSelection();
		rng = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
	}
	
	imagePath = prompt('Enter Image URL:', 'http://');				
	if ((imagePath != null) && (imagePath != "")) {
		//oRTE.focus();
		oRTE.document.execCommand('InsertImage', false, imagePath);
	}
	//oRTE.focus();
}

//function to perform spell check
function checkspell() {
	try {
		var tmpis = new ActiveXObject("ieSpell.ieSpellExtension");
		tmpis.CheckAllLinkedDocuments(document);
	}
	catch(exception) {
		if(exception.number==-2146827859) {
			if (confirm("ieSpell not detected.  Click Ok to go to download page."))
				window.open("http://www.iespell.com/download.php","DownLoad");
		} else {
			alert("Error Loading ieSpell: Exception " + exception.number);
		}
	}
}

function getOffsetTop(elm) {
	var mOffsetTop = elm.offsetTop;
	var mOffsetParent = elm.offsetParent;
	
	while(mOffsetParent){
		mOffsetTop += mOffsetParent.offsetTop;
		mOffsetParent = mOffsetParent.offsetParent;
	}
	
	return mOffsetTop;
}

function getOffsetLeft(elm) {
	var mOffsetLeft = elm.offsetLeft;
	var mOffsetParent = elm.offsetParent;
	
	while(mOffsetParent) {
		mOffsetLeft += mOffsetParent.offsetLeft;
		mOffsetParent = mOffsetParent.offsetParent;
	}
	
	return mOffsetLeft;
}

function Select(rte, selectname) {
	var oRTE;
	if (document.all) {
		oRTE = frames[rte];
		
		//get current selected range
		var selection = oRTE.document.selection; 
		if (selection != null) {
			rng = selection.createRange();
		}
	} else {
		oRTE = document.getElementById(rte).contentWindow;
		
		//get currently selected range
		var selection = oRTE.getSelection();
		rng = selection.getRangeAt(selection.rangeCount - 1).cloneRange();
	}
	
	var idx = document.getElementById(selectname).selectedIndex;
	// First one is always a label
	if (idx != 0) {
		var selected = document.getElementById(selectname).options[idx].value;
		var cmd = selectname.replace('_' + rte, '');
		oRTE.document.execCommand(cmd, false, selected);
		document.getElementById(selectname).selectedIndex = 0;
	}
	//oRTE.focus();
}

function kb_handler(evt) {
	var rte = evt.target.id;
	
	//contributed by Anti Veeranna (thanks Anti!)
	if (evt.ctrlKey) {
		var key = String.fromCharCode(evt.charCode).toLowerCase();
		var cmd = '';
		switch (key) {
			case 'b': cmd = "bold"; break;
			case 'i': cmd = "italic"; break;
			case 'u': cmd = "underline"; break;
		};

		if (cmd) {
			FormatText(rte, cmd, true);
			//evt.target.ownerDocument.execCommand(cmd, false, true);
			// stop the event bubble
			evt.preventDefault();
			evt.stopPropagation();
		}
 	}
}

function docChanged (evt) {
	alert('changed');
}

function stripHTML(oldString) {
	var newString = oldString.replace(/(<([^>]+)>)/ig,"");
	
	//replace carriage returns and line feeds
	newString = escape(newString);
	newString = newString.replace("%0D%0A"," ");
	newString = newString.replace("%0A"," ");
	newString = newString.replace("%0D"," ");
	newString = unescape(newString);
	
	//trim string
	newString = trim(newString);
	
	return newString;
}

function trim(inputString) {
   // Removes leading and trailing spaces from the passed string. Also removes
   // consecutive spaces and replaces it with one space. If something besides
   // a string is passed in (null, custom object, etc.) then return the input.
   if (typeof inputString != "string") return inputString;
   var retValue = inputString;
   var ch = retValue.substring(0, 1);
	
   while (ch == " ") { // Check for spaces at the beginning of the string
      retValue = retValue.substring(1, retValue.length);
      ch = retValue.substring(0, 1);
   }
   ch = retValue.substring(retValue.length-1, retValue.length);
	
   while (ch == " ") { // Check for spaces at the end of the string
      retValue = retValue.substring(0, retValue.length-1);
      ch = retValue.substring(retValue.length-1, retValue.length);
   }
	
	// Note that there are two spaces in the string - look for multiple spaces within the string
   while (retValue.indexOf("  ") != -1) {
		// Again, there are two spaces in each of the strings
      retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length);
   }
   return retValue; // Return the trimmed string back to the user
}

function AllowTabCharacter() {
//	if (event != null) {
//		if (event.srcElement) {
//			if (event.srcElement.value) {
//				if (event.keyCode == 9) {  // tab character
					if (document.selection != null) {
						document.selection.createRange().text = '       ';
						event.returnValue = false;
					}
					else {
						event.srcElement.value += '       ';
						return false;
					}
//				}
//			}
//		}
//	}
}

function pasteFromWord(rte)
{
	var BrowserInfo = new Object() ;
	BrowserInfo.MajorVer = navigator.appVersion.match(/MSIE (.)/)[1] ;
	BrowserInfo.MinorVer = navigator.appVersion.match(/MSIE .\.(.)/)[1] ;
	BrowserInfo.IsIE55OrMore = BrowserInfo.MajorVer >= 6 || ( BrowserInfo.MajorVer >= 5 && BrowserInfo.MinorVer >= 5 ) ;

	alert('pasteFromWord');
	if (BrowserInfo.IsIE55OrMore){
		cleanAndPaste( GetClipboardHTML(),rte ) ;
	}
	else if ( confirm( lang["NotCompatiblePaste"] ) ){
		decCommand(DECMD_PASTE) ;
	}
}

function cleanAndPaste( html,rte )
{
	alert('inside clean');
	alert(html);
	// Remove all SPAN tags
	html = html.replace(/<\/?SPAN[^>]*>/gi, "" );
	// Remove Class attributes
	html = html.replace(/<(\w[^>]*) class=([^ |>]*)([^>]*)/gi, "<$1$3") ;
	// Remove Style attributes
	html = html.replace(/<(\w[^>]*) style="([^"]*)"([^>]*)/gi, "<$1$3") ;
	// Remove Lang attributes
	html = html.replace(/<(\w[^>]*) lang=([^ |>]*)([^>]*)/gi, "<$1$3") ;
	// Remove XML elements and declarations
	html = html.replace(/<\\?\?xml[^>]*>/gi, "") ;
	// Remove Tags with XML namespace declarations: <o:p></o:p>
	html = html.replace(/<\/?\w+:[^>]*>/gi, "") ;
	// Replace the &nbsp;
	html = html.replace(/&nbsp;/, " " );
	// Transform <P> to <DIV>
	var re = new RegExp("(<P)([^>]*>.*?)(<\/P>)","gi") ;	// Different because of a IE 5.0 error
	html = html.replace( re, "<div$2</div>" ) ;
	
	insertHtml( html,rte ) ;
}

function GetClipboardHTML()
{
	alert('GetClipboardHTML');
	var oDiv = document.getElementById("divTemp");
	oDiv.innerHTML = "" ;
	
	var oTextRange = document.body.createTextRange() ;
	oTextRange.moveToElementText(oDiv) ;
	oTextRange.execCommand("Paste") ;
	
	var sData = oDiv.innerHTML ;
	oDiv.innerHTML = "" ;
	
	return sData ;
}

// insertHtml(): Insert HTML at the current document position.
function insertHtml(html,rte)
{
	alert('inside insert in :' + rte);
	document.getElementById(rte).contentWindow.document.body.innerHTML = html;
	
//	if (objContent.DOM.selection.type.toLowerCase() != "none")
//		objContent.DOM.selection.clear() ;
//	objContent.DOM.selection.createRange().pasteHTML(html) ; 
}

function returnControlStr(name){
        
        var controlName = "document.myForm." + name;
        alert(controlName);
        
//        var obj = getFormObj().elements[name]
//        return obj;

	return controlName;
 }

function insertTable(rte)
{
	var rows = window.prompt("Enter number of rows you need:","2");
	var cols = window.prompt("Enter number of columns you need:","2");
	
	if( rows != '' && cols != '' && rows != '0' && cols != '0' )
	{
		
		tblStr = '<TABLE ID="myTable" BORDER="1" CELLPADDING="0" CELLSPACING="0">\n';
		
		for(rcnt = 0; rcnt < rows; rcnt++){
			
			tblStr = tblStr + '<TR>\n';
			for(ccnt = 0; ccnt < cols; ccnt++){
				tblStr = tblStr + '<TD>&nbsp;&nbsp;</TD>\n';
			}
			tblStr = tblStr + '</TR>\n';
		}

		tblStr = tblStr + '</TABLE>\n';
		
		valStr = document.getElementById(rte).contentWindow.document.body.innerHTML;
		
		newStr = valStr + tblStr;
		
		document.getElementById(rte).contentWindow.document.body.innerHTML = newStr;
	}
}

