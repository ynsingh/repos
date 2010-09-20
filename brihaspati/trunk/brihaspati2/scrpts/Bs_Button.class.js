// The global array of objects that have been instanciated
if (!Bs_Objects) {var Bs_Objects = [];};

/**
* a button.
* 
* @package    javascript_components
* @subpackage toolbar
* @author     andrej arn <andrej-at-blueshoes-dot-org>
* @copyright  blueshoes.org
* @license    developer extended
*/
function Bs_Button() {
  
  /**
	* ID is initialized in the constuctor. Represents the possition of 'this' in the global array 'Bs_Objects'
	* Use: This is the only way we can reference ourselfs in an evaluation string 
  *  E.g. str = "var me = Bs_Objects["+this._id+"];";
  *       str += "me.foo();" 
  *       eval(str); 
  *
  * @access private
	* @var  integer 
  */
  this._id;
  
  /**
	* Unique Object ID is initialized in the constuctor.
	* Bassed on this._id. Can be used in genarate JS-code as ID. Is set together 
  * from the  classname + this._id (see constructor code at the bottom).
  *
  * @access private
	* @var  string 
  */
  this._objectId;
	
  /**
	* an optional id for the span/div tag of this rendered element.
	* if not specified then 'this.objectName + "_container"' will be used.
  * @access public
	* @var    string id
  */
  this.id;
	
	/**
	* this button can be added to a group. of all the buttons that are part of 
	* a group, only one can be active ("clicked down") at a time. 
	* it's also possible that none is "down". 
	* pushing another button down will release the other button.
	* 
	* this feature can also be used to make a "toggle" button. just 
	* give it a unique group identifier, and you're done.
	* 
	* @access public
	* @var    mixed group (anything, a string or int. all buttons with the same value belong to the same group.)
	* @since  bs4.4
	*/
	this.group;
  
	/**
	* status of the button.
	* 
	* 0 = button is inactive, not clickable. grayed out too.
	* 1 = button is up, not clicked. default initial mode of buttons.
	* 2 = button is down, clicked. 
	* 
	* a value of '2' is used for button groups, see var this.group.
	* 
	* @access private
	* @var    int _status
	* @see    setStatus(), getStatus()
	* @since  bs4.4
	*/
	this._status = 1;
	
	/**
	* if a button is set to inactive (status 0), how should that be shown?
	* i don't see a reason to change the default, but still it's possible.
	* 
	* 0 = not at all, just leave how it is.
	* 1 = make button gray
	* 2 = fade out button (opacity 0.3)
	* 3 = 1 + 2 (make button gray and fade out). this is the default, and it looks good.
	* 
	* note: not all browsers support the opacity feature. ie and newer mozillas do.
	* 
	* @access public
	* @var    int inactiveStyle
	* @see    this.setStatus(), var this._status
	* @since  bs4.4
	*/
	this.inactiveStyle = 3;
	
  /**
  * the default image path. used if this button is used as stand alone (not 
  * in a buttonbar) and there is no imgPath defined.
  * @access private
  * @var    string _imgPathDefault
  */
  this._imgPathDefault = '/brihaspati2/images/SpreadsheetImages/';
  
  /**
  * the image path. if not specified then the one from the buttonbar will 
  * be used. if this button is not a member of a buttonbar then 
  * this._imgPathDefault is the fallback.
  * 
	* @access public
	* @var    string imgPath
	* @see    var imgName
  */
  this.imgPath;
  
  /**
  * the image name.
	* @access public
	* @var    string imgName
	* @see    var imgPath
  */
  this.imgName;
  
	/**
	* you can overwrite the height that is specified in the css.
	* @access public
	* @var    int height (in pixel)
	* @see    var this.width
	*/
	this.height;
	
	/**
	* you can overwrite the width that is specified in the css.
	* @access public
	* @var    int width (in pixel)
	* @see    var this.height
	*/
	this.width;
	
	/**
	* you can overwrite the background color that is specified in the css.
	* @access public
	* @var    mixed backgroundColor (i recommend you use the hex code like '#ffffff', but you could also use a named color like 'white'.)
	* @see    var this.height
	*/
	this.backgroundColor;
	
  /**
	* the title (alt text) of your button.
	* 
	* note that this.setTitle() also works at runtime, after rendering.
	* 
  * @access public
	* @var    string title
	* @see    this.setTitle()
  */
  this.title;
	
	/**
	* if you want to place a text next to the image that is always visible, 
	* use this var.
	* @access public
	* @var    string caption
	*/
	this.caption;
  
	/**
	* the action that should be done once the button got pushed.
	* 
	* optional, but very useful in some circumstances.
	* for example you attach an 'on' event - your custom function 
	* named buttonClicked(btnObj). as param you receive a reference to 
	* the button object (btnObj). then you can ask the btnObj.action 
	* and based on that information do what you need to do.
	* 
	* @access public
	* @var    string action
	* @since  bs4.3
	*/
	this.action;
  
  /**
  * 
  */
  this.cssClassDefault   = 'bsBtnDefault';
  
  /**
  * 
  */
  this.cssClassMouseOver = 'bsBtnMouseOver';
  
  /**
  * 
  */
  this.cssClassMouseDown = 'bsBtnMouseDown';
  
	/**
	* reference to the "parent" button bar, the bar of which this button is a member.
	* @access private
	* @var    object _buttonBar (instance of Bs_ButtonBar.)
	*/
	this._buttonBar;
	
	/**
	* if this button has a children bar and a child element is clicked, 
	* should this button be updated? (the icon of the child button be used for this button)
	* 
	*   0 = no
	*   1 = only icon
	*   2 = only caption
	*   3 = both, icon and caption
	* 
	* @access public
	* @var    int actualizeFromChildren
	* @see    var _childrenButtonBar
	*/
	this.actualizeFromChildren = 0;
	
	/**
	* a button bar for children of this button.
	* instance of Bs_ButtonBar.
	* @access private
	* @var    object _childrenButtonBar
	* @see    setChildrenButtonBar(), var actualizeFromChildren
	*/
	this._childrenButtonBar;
	
	/**
	* if the child button bar should be rendered position:fixed. 
	* default is false.
	* @access private
	* @var    bool _childrenButtonBarFixed
	* @see    setChildrenButtonBar()
	*/
	this._childrenButtonBarFixed;
	
	/**
	* helper var to avoid to treat "drag-outs" of the button as button clicks.
	* @access private
	* @var    bool_isDragAction
	*/
	this._isDragAction;
	
	/**
	* @access private
	* @var    array _attachedEvents
	* @see    this.attachEvent()
	*/
	this._attachedEvents = new Array;
	
	
	/**
	* the pseudo constructor.
	* @access private
	* @return void
	*/
	this._constructor = function() {
  	// Put this instance into the global object instance list
    this._id = Bs_Objects.length;
    Bs_Objects[this._id] = this; 
    this._objectId = "Bs_Button_"+this._id;
	}

  /**
  * attach functions and code that fires on events.
  * 
  * these events are available:
  *   'on'   = when the button gets clicked
  *   'off'  = if it's a toggle-style button and it looses the "on" status
  *   'over' = mouse over
  *   'out'  = mouse out
  * 
	* registered functions receive one parameter: a reference to 'this', the button object.
	* 
	* //registered code string can use the constant __THIS__. it will then be replaced with a 
	* //reference to 'this', the button object. example: "doThis('foo', __THIS__);"
	* registered code string can use the word 'this'. it will mean this button object. 
	* example: "doThis('foo', this);"
	* 
  * @access public
  * @param  mixed  fire (function or string of code that gets evaluated)
  * @param  string e (event type, default is 'on')
  * @return void
  * @see    this.attachFireOff()
  */
  this.attachEvent = function(fire, e) {
    if (typeof(e) == 'undefined') e = 'on';
    if (typeof(this._attachedEvents[e]) == 'undefined') this._attachedEvents[e] = new Array;
    this._attachedEvents[e][this._attachedEvents[e].length] = fire;
  }
  
	/**
	* detaches all registered events of the event type specified.
	* @access public
	* @return void
	* @since  bs-4.6
	*/
	this.detachEvents = function(e) {
		this._attachedEvents[e] = new Array();
	}
	
	
  /**
  * @todo all
  */
  this.attachFireOff = function(param) {
  }
  
  /**
  * renders this and returns it.
  * @access public
  * @return string (html code)
  */
  this.render = function() {
    var isGecko        = this._isGecko();
    var out            = new Array;
		var containerStyle = new Array;
		
		out[out.length] = '<div style="display:inline; white-space:nowrap;">';
		
    var tagType = 'div'; //(isGecko) ? 'span' : 'div'; //not needed anymore since rick told me to use display:inline.
    out[out.length] = '<' + tagType; //'<div';
    out[out.length] = ' id="' + this._getId() + '"';
    if (typeof(this.title) != 'undefined') {
      out[out.length] = ' title="' + this.title + '"';
    }

//width:5px; height:5px; display:block;
		
    out[out.length] = ' unselectable="on"';
		
		captionType = typeof(this.caption);
    if (captionType != 'undefined') {
			containerStyle[containerStyle.length] = 'width:auto';
		} else {
			if (typeof(this.width)  != 'undefined') containerStyle[containerStyle.length] = 'width:'  + this.width  + 'px';
			if (typeof(this.height) != 'undefined') containerStyle[containerStyle.length] = 'height:' + this.height + 'px';
		}
		if (typeof(this.backgroundColor) != 'undefined') containerStyle[containerStyle.length] = 'background-color:' + this.backgroundColor;
		
		switch (this._status) {
			case 0: //inactive
				var filter = this._getInactiveStyleFilter();
				if (typeof(filter) == 'string') {
					containerStyle[containerStyle.length] = 'filter:' + filter;
				}
				//don't break!
			case 1: //default, up
		    out[out.length] = ' class="' + this.cssClassDefault + '"';
				break;
			case 2: //down
		    out[out.length] = ' class="' + this.cssClassMouseDown + '"';
				break;
		}
		
		out[out.length] = ' style="' + containerStyle.join(';') + '"';
		
    out[out.length] = ' onMouseOver="Bs_Objects['+this._id+'].mouseOver(this);"';
    out[out.length] = ' onMouseOut="Bs_Objects['+this._id+'].mouseOut(this);"';
    out[out.length] = ' onMouseDown="Bs_Objects['+this._id+'].mouseDown(this);"';
    out[out.length] = ' onMouseUp="Bs_Objects['+this._id+'].mouseUp(this);"';
		/*
		//have to deactivate this again. has its advantages, but quite a drawback. :(
    if (!isGecko) {
			out[out.length] = ' onDragStart="return Bs_Objects['+this._id+'].dragStart(this);"';
    }*/
    out[out.length] = '>';
    
		//out[out.length] = '<nobr>'; using white-space:nowrap now.
		
		//add the image
		if (typeof(this.imgName) != 'undefined') {
	    var imgFullPath = '';
      imgFullPath += this._getImgPath();
	    imgFullPath += this.imgName;
	    if (this.imgName.substr(this.imgName.length -4) != '.gif') imgFullPath += '.gif';
	    out[out.length] = '<img id="' + this._getId() + '_icon" src="' + imgFullPath + '"';
			//hacky: ie renders the button too large (height only) if we use that css setting. for small buttons.
			if ((typeof(this.height) == 'undefined') || (this.height > 18)) out[out.length] = ' style="vertical-align:top;"';
			out[out.length] = '>';
		}
		
		//add the caption
		captionType = typeof(this.caption);
    if (captionType != 'undefined') {
			if (captionType == 'string') {
				out[out.length] = this.caption;
			} else { //bool
				out[out.length] = this.title;
			}
			if (!isGecko) out[out.length] = '&nbsp;';
		}
		
		if ((typeof(this._childrenButtonBar) != 'undefined') && (this.numberOfAttachedEvents('on') == 0)) {
			this.group =  this._objectId + '_pseudoGroup'; //hacky. to keep the button down.
			
	    var imgFullPath = '';
	    //if (this.imgPath) 
			imgFullPath += this._getImgPath();
	    imgFullPath += 'small_black_arrow_down.gif';
	    out[out.length] = '&nbsp;<img src="' + imgFullPath + '" style="vertical-align:middle;">&nbsp;';
			var subBarString = this._childrenButtonBar.render();
			if (this._childrenButtonBarFixed) {
				var divPosition = 'fixed';
			} else {
				var divPosition = 'absolute';
			}
			subBarString = '<div id="' + this._objectId + '_childBar" class="bsBtnMouseOver" style="width:auto; height:auto; display:none; position:' + divPosition + '; left:50px; top:50px;">' + subBarString + '</div>';
			if (this._childrenButtonBarFixed) {
				/*
				//need to add this into document's head: (not working yet)
				
				<style type="text/css"><!--
				#fixme { position: absolute; left: 0px; top: 0px; }
				body > div#fixme { position: fixed; }
				--></style><!--[if gte IE 5.5]>
				<style type="text/css">
				div#fixme {
				left: expression( ( 0 + ( ignoreMe2 = ( document.documentElement && document.documentElement.scrollLeft ) ? document.documentElement.scrollLeft : document.body.scrollLeft ) ) + 'px' );
				top: expression( ( 0 + ( ignoreMe = ( document.documentElement && document.documentElement.scrollTop ) ? document.documentElement.scrollTop : document.body.scrollTop ) ) + 'px' );
				}
				</style>
				<![endif]-->
				*/
				
				document.body.insertAdjacentHTML('beforeEnd', subBarString);
			} else {
				out[out.length] = subBarString;
			}
		}
		
		//out[out.length] = '</nobr>'; white-space:nowrap
		
    out[out.length] = '</' + tagType + '>'; //'</div>'
		
		/*
		if ((typeof(this._childrenButtonBar) != 'undefined') && (this.numberOfAttachedEvents('on') > 0)) {
			out[out.length] = '<div>';

	    var imgFullPath = '';
	    if (this.imgPath) imgFullPath += this.imgPath;
	    imgFullPath += 'small_black_arrow_down.gif';
	    out[out.length] = '<img src="' + imgFullPath + '" style="vertical-align:top;">';
			out[out.length] = '</div>';
		}
    */
		
		out[out.length] = '</div>';
		
		//alert(out.join(''));
    return out.join('');
  }
  
  
  /**
  * renders this using writeln().
  * @access public
  * @return void
  */
  this.drawOut = function() {
    document.writeln(this.render());
  }
  
  
  /**
  * renders this into the element specified.
  * @access public
  * @param  mixed elm (string name of element, or object reference to element)
  * @return void
  */
  this.drawInto = function(elm) {
		//alert(document.body.innerHTML);
		//alert(elm);
    if (typeof(elm) == 'string') {
      elm = document.getElementById(elm);
    }
    if (elm != null) {
			var x = this.render();
			//alert(x);
			//wtf, in ie6 if there is a <nobr> tag in the string, ie stops with an 'unknown runtime error'. stupid stupid.
			//x = x.replace(/<nobr>/, '');
			//x = x.replace(/<\/nobr>/, '');
			x = x.replace(/<nobr>/, '<span style="white-space: nowrap">');
			x = x.replace(/<\/nobr>/, '<\/span>');
      elm.innerHTML = x;
    }
  }
  
  /**
  * 
  */
  this.mouseOver = function(div) {
		if (this._status == 2) return;
		if (this._status == 0) return; //inactive
    if (!this._isGecko()) {
      div.className = this.cssClassMouseOver;
    }
    this._fireEvent('over');
  }
  this.mouseOut = function(div) {
		if (this._status == 2) return;
		if (this._status == 0) return; //inactive
    if (!this._isGecko()) {
      div.className = this.cssClassDefault;
    }
    this._fireEvent('out');
  }
  this.mouseDown = function(div) {
		if (this._status == 0) return; //inactive
		this._isDragAction = false;
    div.className = this.cssClassMouseDown;
  }
  this.mouseUp = function(div) {
		if (this._status == 0) return; //inactive
		var doFireOn  = true;
		var doFireOff = false;
    if (this._isGecko()) {
      div.className = this.cssClassDefault;
    } else {
      div.className = this.cssClassMouseOver;
    }
		if (typeof(this.group) != 'undefined') {
			if (this._status == 2) {
				this._status = 1;
				doFireOn  = false;
				doFireOff = true;
			} else {
				div.className = this.cssClassMouseDown;
				this._status = 2;
				this._deactivateOtherGroupButtons();
			}
		}
		if (this._isDragAction) doFireOn = false; // we dont' wanna fire a click on a "drag-out".
		if (doFireOn) {
			this._fireEvent('on');
		} else if (doFireOff) {
			this._fireEvent('off');
		}
  }
  this.dragStart = function(div) {
		if (this._status == 0) return false; //inactive
		this._isDragAction = true;
    div.className = this.cssClassMouseOver;
		return false;
  }
  
	/**
	* used for button groups. deactivates another currently active button of the same group.
	* @access private
	* @return void
	* @since  bs4.4
	*/
	this._deactivateOtherGroupButtons = function() {
		if (typeof(this._buttonBar) == 'undefined') return;
		
		for (var i=0; i<this._buttonBar._buttons.length; i++) {
			var btnObj = this._buttonBar._buttons[i][0];
			if (typeof(btnObj) != 'object') continue;
			if ((btnObj.group == this.group)) {
				if (btnObj._objectId == this._objectId) continue; //don't fuck with this button.
				btnObj._status = 1;
				btnDiv = document.getElementById(btnObj._getId());
				btnDiv.className = btnObj.cssClassDefault;
			}
		}
	}
	
	
	/**
	* api function that works like a click on the button.
	* does nothing if the button is disabled.
	* @access public
	* @return bool (true on success, false on failure (disabled).)
	* @see    setStatus()
	* @since  bs-4.6
	*/
	this.pretendClick = function() {
		if (this._status == 0) return false;
		if (typeof(this.group) != 'undefined') this.setStatus(2);
		this._fireEvent('on');
	}
	
	/**
	* sets status of the button. like a click, but as api function.
	* does not fire registered functions, like 'on'!
	* 
	* @access public
	* @param  int status (0, 1 or 2. see var _status.)
	* @return void
	* @see    var _status, getStatus(), pretendClick()
	* @since  bs4.4
	*/
	this.setStatus = function(status) {
		if (this._status == status) return; //nothing to do
		
		var oldStatus = this._status; //make backup
		this._status = status;
		
		var btnDiv = document.getElementById(this._getId());
		if (btnDiv != null) { //already outrendered...
			switch (status) {
				case 0:
					var filter = this._getInactiveStyleFilter();
					if (typeof(filter) == 'string') {
						btnDiv.style.filter = filter;
					}
					break;
				case 1:
					btnDiv.className = this.cssClassDefault;
					break;
				case 2:
					if (this._isGecko()) {
					  btnDiv.className = this.cssClassDefault;
					} else {
					  btnDiv.className = this.cssClassMouseDown;
					}
					if (typeof(this.group) != 'undefined') {
						this._deactivateOtherGroupButtons();
					}
					break;
			}
			if ((oldStatus == 0) && (this.inactiveStyle != 0)) {
				//remove filter
				btnDiv.style.filter = "";
			}
		}
	}
	
	/**
	* returns the status of this button.
	* @access public
	* @return int
	* @see    var _status, setStatus()
	* @since  bs4.4
	*/
	this.getStatus = function() {
		return this._status;
	}
	
	/**
	* sets the title, also works at runtime.
	* @access public
	* @var    string title
	* @return void
	* @see    var this.title
	* @since  bs4.4
	*/
	this.setTitle = function(title) {
		var elm = document.getElementById(this._getId());
		if (elm != null) elm.title = title;
		this.title = title;
	}
	
	/**
	* sets a children button bar
	* @access public
	* @param  object bar (instance of Bs_ButtonBar)
	* @param  bool posFixed (if the children button par should be rendered position:fixed. default is false. see example 7.)
	* @return void
	* @see    var _childrenButtonBar
	*/
	this.setChildrenButtonBar = function(bar, posFixed) {
		bar._parentButton = this;
		this._childrenButtonBar = bar;
		this._childrenButtonBarFixed = (typeof(posFixed) != 'undefined') ? posFixed : false;
	}
	
	
  /**
  * @access private
  * @return bool
  */
  this._isGecko = function() {
    //rather poor implementation.
    if (navigator.appName == "Microsoft Internet Explorer") return false;
    var x = navigator.userAgent.match(/gecko/i);
    return (x);
    //if (preg_match('/gecko\/([0-9]{8})/i', $userAgent, $geckoMatch)) { //eg "Gecko/20020530"
    return false;
  }
  
  /**
  * fires the events that are registered.
  * @access private
  * @param string eventType ('on', 'off', 'over', 'out')
  * @return void
  * @see   this.attachEvent()
  */
  this._fireEvent = function(e) {
		//if (this._buttonBar.ignoreEvents) return; has to be done smarter.
		
		if ((e == 'on') && (typeof(this._buttonBar) != 'undefined') && (typeof(this._buttonBar._parentButton) != 'undefined')) {
			//special case, have to release that.
			this._buttonBar._parentButton._fireEvent('off');
			
			if ((this._buttonBar._parentButton.actualizeFromChildren == 1) || (this._buttonBar._parentButton.actualizeFromChildren == 3)) {
				var elm = document.getElementById(this._buttonBar._parentButton._getId() + '_icon');
	  	  var imgFullPath = '';
		    if (this.imgPath) imgFullPath += this.imgPath;
		    imgFullPath += this.imgName;
		    if (this.imgName.substr(this.imgName.length -4) != '.gif') imgFullPath += '.gif';
				elm.src = this._getImgPath() + imgFullPath;
			}
		}
		
		if (((e == 'on') || (e == 'off')) && (typeof(this._childrenButtonBar) != 'undefined') && (this.numberOfAttachedEvents('on') == 0)) {
			//special case, it's a button that activates a children button bar.
			var elm = document.getElementById(this._objectId + '_childBar');
			if (elm != null) {
				if (e == 'on') {
					this._buttonBar.ignoreEvents = true;
					var pos = getAbsolutePos(document.getElementById(this._getId()), true);
					var plusPixel = (typeof(this.height)  != 'undefined') ? parseInt(this.height) : 22;
					elm.style.top     = (pos.y + plusPixel) + 'px';
					elm.style.left    = pos.x + 'px';
					elm.style.display = 'block';
				} else {
					this._buttonBar.ignoreEvents = false;
					elm.style.display = 'none';
				}
			}
		} else {
	    if (!this._attachedEvents[e]) return;
	    for (var i=0; i<this._attachedEvents[e].length; i++) {
	      switch (typeof(this._attachedEvents[e][i])) {
	        case 'function':
	          this._attachedEvents[e][i](this);
	          break;
	        case 'string':
						//var ev = this._attachedEvents[e][i].replace(/__THIS__/, this);
						//if (e == 'on') alert(this._attachedEvents[e][i]); //4debug
	          eval(this._attachedEvents[e][i]);
	          break;
	        default:
	          //murphy
	      }
	    }
		}
  }
	
	/**
	* returns the number of attached events of the type specified.
	* @access public
	* @param  string eventType ('on', 'off', 'over', 'out')
	* @return int (0-n)
	* @see    attachEvent(), _fireEvent().
	*/
	this.numberOfAttachedEvents = function(e) {
		try {
			return this._attachedEvents[e].length;
		} catch (ex) {
			return 0;
		}
	}
	
	/**
	* returns the id of the container element (div or span) where the button is rendered into.
	* @access private
	* @return string
	*/
	this._getId = function() {
		if (typeof(this.id) != 'undefined') return this.id;
		return this._objectId + "_container";
	}
  
	
	/**
	* returns the filter to use if that button is inactive.
	* does not check if the button is inactive, do that yourself!
	* @access private
	* @return mixed (string for a filter, bool false for none.
	* @see    var this.inactiveStyle, this.setStatus()
	* @since  bs4.4
	*/
	this._getInactiveStyleFilter = function() {
		switch (this.inactiveStyle) {
			case 0: //do nothing
				return false;
				break;
			case 1: //make it gray
				return 'progid:DXImageTransform.Microsoft.BasicImage(grayScale=1)';
				break;
			case 2: //fade out
				return 'progid:DXImageTransform.Microsoft.BasicImage(opacity=.3)';
				break;
			default: //also case 3, do 2 + 1
				return 'progid:DXImageTransform.Microsoft.BasicImage(grayScale=1) progid:DXImageTransform.Microsoft.BasicImage(opacity=.3)';
		}
	}
	
  /**
  * different options for the path.
  * @access private
  * @return string
  * @see    vars this.imgPath, this._imgPathDefault, Bs_ButtonBar.imgPath
  */
  this._getImgPath = function() {
    if (typeof(this.imgPath) != 'undefined') {
      return this.imgPath;
    } else if (typeof(this._buttonBar) != 'undefined') {
      return this._buttonBar.imgPath;
    } else {
      return this._imgPathDefault;
    }
  }
	
  
	this._constructor(); //call the constructor. needs to be at the end.	
	
}

