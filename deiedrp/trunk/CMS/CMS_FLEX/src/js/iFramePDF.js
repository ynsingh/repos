var pdfObject;

function loadIFrame(url){
    try{
    	/* 
    	 * Reason I do 'object' for IE is, it can handle multiple pdfs as objects and by creating it
    	 * as an object, we will get access to all of the Acrobat JS methods, which is very useful
    	 * I will try to show the power of AcroJS in my Part 3 and 4 of this tutorial
    	 */
    	 
    	if (navigator.appName.indexOf ("Microsoft") !=-1) //For IE (7)
    	{
    		document.clear();
    		document.getElementById("pdfFrame").innerHTML = "<object id='pdfIFrame' data='"+url+"' type='application/pdf'>  </object>";	
    	}
    	else //for non IE browser (FF, Safari)
    	{
    		document.clear();
    		document.getElementById("pdfFrame").innerHTML = "<iframe id='pdfIFrame' name='pdfIFrame' src='"+url+"' frameborder='0' >  </iframe>";	
    	}
    	pdfObject = document.getElementById("pdfIFrame");
    }
    catch(e)
     {
    	alert("Error in loadIFrame \name:"+e.name+"\nmessage:"+e.message);
      }
}

function moveIFrame(x,y,w,h) {
    var frameRef=document.getElementById("pdfFrame");
    frameRef.style.left=x;
    frameRef.style.top=y;
    var iFrameRef=document.getElementById("pdfIFrame");    
    iFrameRef.width=w;
    iFrameRef.height=h;
}

function hideIFrame(){
    document.getElementById("pdfFrame").style.visibility="hidden";
}
    
function showIFrame(){
    document.getElementById("pdfFrame").style.visibility="visible";
}

/*
 * If you want to hide the PDF in your Flex interface and still print it, on click of a button,
 * This function will allow you to do that.
 */
function printIFramePDF(srcUrl)
	{
		try{
			if (navigator.appName.indexOf ("Microsoft") !=-1) //For IE (7) 	
				{ 
				 	if(document.getElementById("pdfFrame").style.visibility == "visible")
				  		{pdfObject.print();}
				  		else
				  		{
				  		 showIFrame();
				  		 pdfObject.print();
				  		 setTimeout("hideIFrame()", 800);
				  		 }
				}
			else //for non-IE browser (FF)   
			 {
			 	frames["pdfIFrame"].focus();
				frames["pdfIFrame"].print();
			  }
			   
			}
		catch(e){
			alert("Error in printIFramePDF:\n name = "+ e.name + "\n message = " + e.message);
		}
}
