/********************************************/
//for FULL SCREEN DETAILCARD ..for Increasing and decreasing Font Size*/
baseFont=12;
function fontResize(offset)
{
	var temp=baseFont+parseInt(offset);
	if(temp>5 && temp<30)
		{
			baseFont=temp;
			for(var i=0;i< document.getElementsByTagName("table").length;i++)
				document.getElementsByTagName("table")[i].style.fontSize=baseFont;
			for(var i=0;i< document.getElementsByTagName("a").length;i++)
				document.getElementsByTagName("a")[i].style.fontSize=baseFont;
		}
}
/*****************************************/