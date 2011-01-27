
var language='';   // specify your language if not English (en_en)
// Latex equation editor

var InsertEquationCommand=function(){};

InsertEquationCommand.prototype.Execute=function(){ }

InsertEquationCommand.GetState=function() {
        return FCK_TRISTATE_OFF; //we dont want the button to be toggled
}

var popupEqnwin = null;
InsertEquationCommand.Execute=function() 
{
	var url='http://latex.codecogs.com/editor_json.php';
  if(language!='') url+='?lang='+language;
	
  //open a popup window when the button is clicked
	if (popupEqnwin==null || popupEqnwin.closed || !popupEqnwin.location) 
	{
	  popupEqnwin=window.open('','LaTexEditor','width=700,height=450,status=1,scrollbars=yes,resizable=1');
	  if (!popupEqnwin.opener) popupEqnwin.opener = self;
	  popupEqnwin.document.write('<html><script src="'+url+'" type="text/javascript"></script><body></body></html>');
  }
	else
   if (window.focus) { popupEqnwin.focus() }
}

// Register the related command.
FCKCommands.RegisterCommand( 'Equation', InsertEquationCommand ) ;

// Create the "Placeholder" toolbar button.
var oEquationItem = new FCKToolbarButton( 'Equation', FCKLang.EquationBtn ) ;
oEquationItem.IconPath = FCKPlugins.Items['equation'].Path + 'equation.gif' ;

FCKToolbarItems.RegisterItem( 'Equation', oEquationItem ) ;


// The object used for all Placeholder operations.
var FCKEquation= new Object() ;

// Add a new placeholder at the actual selection.
// 
FCKEquation.Add = function( name )
{
//	var oImage = FCK.CreateElement( 'img' ) ;
	var oImage = FCK.InsertElement( 'img' ) ;
	this.SetupImage( oImage, name ) ;
// Then select the new placeholder
	FCKSelection.SelectNode(oImage);
}

FCKEquation.SetupImage = function( image, name )
{
	var sName = name.match( /(\\\[|\$)(.*?)(\\\]|\$)/ );
	var eq='';
	
	eq = escape(sName[2]);
	if(sName[1]=='$') 
	{	eq='\\inline&space;'+eq; }
	else
	{
	  image.style.display='block';
		image.style.margin='0 auto';
		image.style.textalign='center';
	}
	
	image.src = 'http://latex.codecogs.com/gif.latex?'+eq;
	image.style.backgroundColor = '#ffffdd' ;
	image.align='absmiddle';
  image.alt=name;
	image.title=name;

	if ( FCKBrowserInfo.IsGecko )
		image.style.cursor = 'default' ;

	image._fckequation = name;
	image.contentEditable = false ;

	// To avoid it to be resized.
	image.onresizestart = function()
	{
		FCK.EditorWindow.event.returnValue = false ;
		return false ;
	}
}

// On Gecko we must do this trick so the user select all the IMG when clicking on it.
FCKEquation._SetupClickListener = function()
{
	FCKEquation._ClickListener = function( e )
	{
		if ( e.target.tagName == 'IMG' && e.target._fckequation )
			FCKSelection.SelectNode( e.target ) ;
	}

	FCK.EditorDocument.addEventListener( 'click', FCKEquation._ClickListener, true ) ;
}

// Open the Placeholder dialog on double click.
FCKEquation.OnDoubleClick = function( image )
{
	if ( image.tagName == 'IMG' && image._fckequation )
		FCKCommands.GetCommand( 'Equation' ).Execute() ;
}

FCK.RegisterDoubleClickHandler( FCKEquation.OnDoubleClick, 'IMG' ) ;

// Check if a Placholder name is already in use.
FCKEquation.Exist = function( name )
{
	var aImages = FCK.EditorDocument.getElementsByTagName( 'IMG' )

	for ( var i = 0 ; i < aImages.length ; i++ )
	{
		if ( aIMGs[i]._fckequation == name )
			return true ;
	}
}

if ( FCKBrowserInfo.IsIE )
{
	FCKEquation.Redraw = function()
	{
		if ( FCK.EditMode != FCK_EDITMODE_WYSIWYG )
			return ;
	
		var aEquations = FCK.EditorDocument.body.innerText.match( /(\\\[|\$)(.*?)(\\\]|\$)/g ) ;
		if ( !aEquations )
			return ;

		var oRange = FCK.EditorDocument.body.createTextRange() ;

		for ( var i = 0 ; i < aEquations.length ; i++ )
		{
			if ( oRange.findText( aEquations[i] ) )
			{
				var sName = aEquations[i].match( /(\\\[|\$)(.*?)(\\\]|\$)/ );
				
				var eq=escape(sName[2])
				var extrastyle='';
      	if(sName[1]=='$') 
				  eq='\\inline&space;'+eq;
				else 
				  extrastyle=' display:block; margin:0 auto;'; 
					
        imgsrc = 'http://latex.codecogs.com/gif.latex?'+eq;
				
				oRange.pasteHTML( '<img src="'+imgsrc+'" style="background-color:#ffffdd;'+extrastyle+'" align="absmiddle" contenteditable="false" _fckequation="'+ escape(aEquations[i]) +'" />') ;
			}
		}
	}
}
else
{
	FCKEquation.Redraw = function()
	{
		if ( FCK.EditMode != FCK_EDITMODE_WYSIWYG )
			return ;
			
		var oInteractor = FCK.EditorDocument.createTreeWalker( FCK.EditorDocument.body, NodeFilter.SHOW_TEXT, FCKEquation._AcceptNode, true ) ;

		var	aNodes = new Array() ;

		while ( (oNode = oInteractor.nextNode()) )
		{
			aNodes[ aNodes.length ] = oNode ;
		}

		for ( var n = 0 ; n < aNodes.length ; n++ )
		{
			// For some season, we're not able to here to step through all the nodes.
			// It must have something to do with following code that somehow deletes something.
			var aPieces = aNodes[n].nodeValue.split( /(\\\[|\$)(.*?)(\\\]|\$)/g ) ;

      for ( var i = 0 ; i < aPieces.length ; i++ )
			{
			  if ( aPieces[i].length > 0 )
				{
					if ( aPieces[i]=='\\[' || aPieces[i]=='$')
					{

						var oImage = FCK.EditorDocument.createElement( 'img' ) ;
						FCKEquation.SetupImage( oImage, aPieces[i]+aPieces[i+1]+aPieces[i+2] ) ;

						aNodes[n].parentNode.insertBefore( oImage, aNodes[n] ) ;
						i+=2;
					}
					else
					  aNodes[n].parentNode.insertBefore( FCK.EditorDocument.createTextNode( aPieces[i] ) , aNodes[n] ) ;
				}
			}

			aNodes[n].parentNode.removeChild( aNodes[n] ) ;
		}
		
		FCKEquation._SetupClickListener() ;
	}
	
  FCKEquation._AcceptNode = function( node )
	{
	  if ( (/\\\[(.*?)\\\]/.test( node.nodeValue )) || ( /\$(.*?)\$/.test( node.nodeValue )) )
		{
			return NodeFilter.FILTER_ACCEPT ;
		}
		else
			return NodeFilter.FILTER_SKIP ;
	}
}

FCK.Events.AttachEvent( 'OnAfterSetHTML', FCKEquation.Redraw ) ;

// Must uncomment the following block or include the seperate plugin called TagProcessors; that contains
/*
FCKXHtml.TagProcessors['img'] = function( node, htmlNode )
{
	if ( htmlNode._fckequation )
	{
		node = FCKXHtml.XML.createTextNode( unescape(htmlNode._fckequation) ) ;
	}
	else
	{
		// The "ALT" attribute is required in XHTML.
		if ( ! node.attributes.getNamedItem( 'alt' ) )
			FCKXHtml._AppendAttribute( node, 'alt', '' ) ;
	
		var sSavedUrl = htmlNode.getAttribute( '_fcksavedurl' ) ;
		if ( sSavedUrl != null )
			FCKXHtml._AppendAttribute( node, 'src', sSavedUrl ) ;
	}

	return node ;
}

*/