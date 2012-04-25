// The global array of objects that have been instanciated
if (!Bs_Objects) {var Bs_Objects = [];};

/**
* a button bar (aka toolbar).
* 
* @author     andrej arn <andrej-at-blueshoes-dot-org>
* @copyright  blueshoes.org
* @package    javascript_components
* @subpackage toolbar
* @license    developer extended
*/
function Bs_ButtonBar() {
  
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
  * the path to the buttons (images).
	* @access public
	* @var    string imgPath
  */
  this.imgPath = '/brihaspati2/images/SpreadsheetImages/';
	/**
	* if and how the helpbar should be used.
	* 
	*   not set or false means  =>  don't use it.
	*   bool true               =>  use it
	*   int 2                   =>  use it, and show the ? icon. looks nice.
	*   string element id       =>  use the element specified for the helpbar. if you 
	*                               want the ? icon, place it somewhere yourself.
	* 
	* @access public
	* @var    mixed useHelpBar (bool, int or string, see above.)
	* @see    var this.helpBarStyle
	*/
  this.useHelpBar;
	
	/**
	* how the toolbar shoud be aligned. 
	*   hor  => horizontally (default)
	*   vert => vertically
	*/
	this.alignment = 'hor';
	
	/**
	* all mouse events (mouse-over, out, click) are ignored. 
	* but the buttons are not set to inactive.
	* it is used when a children bar waits for input.
	* @access public
	* @var    bool ignoreEvents
	* @todo   all
	*/
	this.ignoreEvents = false;
  
	/**
	* css style (not class name) for the help bar.
	* @access public
	* @var    string helpBarStyle
	* @see    var this.useHelpBar
	*/
  this.helpBarStyle = "font-family:arial; font-size:11px; height:16px;";
  
	/**
	* the background color.
	* @access public
	* @var    string bgColor
	* @since  bs-4.6
	*/
	this.backgroundColor = 'menu';
	
  /**
  * references to the button objects.
	* 
	* data structure:
	*   vector, for buttons: holding vectors with 2 elements, the btn and the helpBarText.
	*           for html: holding vectors with 3 elements, the html, empty string and the string 'html'.
	*           for separators: string '|' (pipe)
	* 
  * @access protected
  * @var    array _buttons (see above)
  * @see    this.addButton()
  */
  this._buttons = new Array;
  
	/**
	* if this bar belongs to a button, then we have a reference to it here.
	* @access private
	* @var    object _parentButton (instance of Bs_Button)
	*/
	this._parentButton;
	
	
	/**
	* the pseudo constructor.
	* @access private
	* @return void
	*/
	this._constructor = function() {
  	// Put this instance into the global object instance list
    this._id = Bs_Objects.length;
    Bs_Objects[this._id] = this; 
    this._objectId = "Bs_ButtonBar_"+this._id;
	}

  /**
  * @access public
  * @param  object btn
  * @return void
  */
  this.addButton = function(btn, helpBarText) {
		btn._buttonBar = this;
    this._buttons[this._buttons.length] = new Array(btn, helpBarText);
  }
	
	/**
	* @access public
	* @param  string html
	* @return void
	*/
	this.addHtml = function(html) {
		this._buttons[this._buttons.length] = new Array(html, '', 'html');
	}
  
	/**
	* adds a group separator.
	* @access public
	* @return void
	*/
  this.newGroup = function() {
		this._buttons[this._buttons.length] = '|';
		//this._buttons[this._buttons.length] = '<div style="background-color:menu;">|</div>';
  }
  
  /**
  * renders the button bar and returns it.
  * @access public
  * @return string (html code)
  */
  this.render = function() {
    var out = new Array;
    if (this._isGecko()) {
      out[out.length] = '<div style="background-color:' + this.backgroundColor + '; padding: 2px">';
    } else {
      out[out.length] = '<div style="background-color:' + this.backgroundColor + ';">';
    }
    out[out.length] = '<div>';
    for (var i=0; i<this._buttons.length; i++) {
			if (this.alignment != 'hor') {
		    out[out.length] = '<div>';
			}

      if (this._buttons[i] == '|') {
				/*
        if (this._isGecko()) {
          out[out.length] = '<span class="separator"></span>';
        } else {
          //out[out.length] = '<div style="display:inline; vertical-align:top;">&nbsp;|&nbsp;</div>';
          out[out.length] = '<span class="separator"></span>';
        }*/
        out[out.length] = '<span class="' + ((this.alignment == 'hor') ? 'separatorForHorizontal' : 'separatorForVertical') + '"></span>';
			} else if ((typeof(this._buttons[i][2]) != 'undefined') && (this._buttons[i][2] == 'html')) {
				out[out.length] = '&nbsp;' + this._buttons[i][0] + '&nbsp;';
      } else {
        var btn = this._buttons[i][0];
				var helpBarDiv = false;
				if (typeof(this.useHelpBar) == 'string') {
          var helpBarDiv = this.useHelpBar;
        } else if (this.useHelpBar) {
          var helpBarDiv = this._objectId + '_helpBarDiv';
        }
				if (helpBarDiv != false) {
	        btn.attachEvent("document.getElementById('" + helpBarDiv + "').innerHTML = \"" + this._buttons[i][1] + "\";", 'over');
  	      btn.attachEvent("document.getElementById('" + helpBarDiv + "').innerHTML = \"\";", 'out');
				}
        out[out.length] = btn.render();
      }
			
			if (this.alignment != 'hor') {
		    out[out.length] = '</div>';
			}
    }
    out[out.length] = '</div>';
    if (this.useHelpBar) {
			if (this.useHelpBar == 2) {
	      //out[out.length] = '<div>--------------------</div>';
	      out[out.length] = '<div style="' + this.helpBarStyle + '">';
				out[out.length] = '<img align="middle" src="' + this.imgPath + 'bs_info.gif" border="0" onMouseOver="document.getElementById(\'' + helpBarDiv + '\').innerHTML = \'Move your mouse over the buttons to see the description here.\';" onMouseOut="document.getElementById(\'' + helpBarDiv + '\').innerHTML = \'\';"> ';
				out[out.length] = '<span id="' + helpBarDiv + '"></span></div>';
			} else if (this.useHelpBar == true) {
	      out[out.length] = '<div id="' + helpBarDiv + '" style="' + this.helpBarStyle + '"></div>';
			}
    }
    out[out.length] = '</div>';
    
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
    if (typeof(elm) == 'string') {
      elm = document.getElementById(elm);
    }
    if (elm) {
      elm.innerHTML = this.render();
    }
  }
  
  
  /**
	* tells if the client is a gecko browser (mozilla, netscape, ...).
	* rather poor implementation.
  * @access private
  * @return bool
  */
  this._isGecko = function() {
    if (navigator.appName == "Microsoft Internet Explorer") return false;
    var x = navigator.userAgent.match(/gecko/i);
    return (x);
    //if (preg_match('/gecko\/([0-9]{8})/i', $userAgent, $geckoMatch)) { //eg "Gecko/20020530"
    return false;
  }
    
	this._constructor(); //call the constructor. needs to be at the end.	  
}
