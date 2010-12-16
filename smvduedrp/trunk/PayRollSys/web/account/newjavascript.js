
function loadFileIntoArray(filepath) {
        var xhttp = null;
	if (window.XMLHttpRequest)
	{
		xhttp = new XMLHttpRequest();
	}
	else // Internet Explorer 5/6
	{
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhttp.open("GET",filepath,false);
	xhttp.send("");
	var data = xhttp.responseText;
	var re = new RegExp("^.*$", "mg");
	var dataArray = data.match(re);
	return dataArray;
}


function compare(x)
{
	var src = p1[x];
	var a=0;
	var dest = p1[130];
	for(var i=0;i<600;i++)
	{
            if(src[i]!=dest[i])
            {
                    a++;
            }
	}
	//document.writeln("Comp : "+a);
        t
	return a;
}

function getBestMatchIndex(index,counter) {

try
{
	document.writeln("<b>"+index+"</b>");
	if(counter>10||index<0)
	{
		return;
	}
	counter++;
 	var mm=0;
	var bestmatch=600;
	var matchindex=-1;
	var source = p1[index];
	for(var i=0;i<p1.length;i++)
	{
		if(i==index)
		{
			continue; // skip comparing to itself
		}
		var target = p1[i];
		for(var j=0;j<source.length;j++)
		{
			if(source[j]!=target[j])
			{
				mm++;
			}

		}
		if(mm<bestmatch&&mm<=250) // This 300 is just to narrow the possibility of a parent corresponding to an element.
		{
				//var ll = (plength+mm)/2;
			  	if((excludes.indexOf(i)<0)&&(outexcludes.indexOf(i)<0))
				{
			  	matchindex=i; // this is the best matching sequence index
			  	bestmatch=mm;
				}
		}
		mm=0;
	}

	var k = compare(matchindex);
	document.writeln("Index "+index+"  :  Best Mismatch "+bestmatch+" [ "+matchindex+"], "+k+"<br>");

		parents[index] =matchindex;
		//excludes.push(index);
		mismatches[index] = bestmatch;
		excludes.push(matchindex);
		if(k<200)
		{
			document.writeln("Term");
			parents[index+1]=130;
			return;
		}
	    my2D.push(Array(index,matchindex,bestmatch));
	    getBestMatchIndex(matchindex,counter);
		optimize(index);
}
catch(ex)
{

}
       // document.writeln(line.length);
}


function optimize(index)
{
	excludes = Array();
	document.writeln("Opt..."+my2D.length+","+my2D[0][2]);
	var k = my2D[0][2];
	for(var t=0;t<my2D.length;t++)
	{
		var p = my2D[t][2];
		//document.writeln("P: "+p);
		if(p>k+20)
		{
			outexcludes.push(my2D[t][0]);
			document.writeln("<br><font color='red'> Ex "+my2D[t][0]+"</font>");
			my2D = Array();
			getBestMatchIndex(2,0);
		}
		else
		{
			k = my2D[t][2];
		}
		//document.writeln("K : "+k);
	}

}










var my2D = Array();
var p1 = loadFileIntoArray("tweens600.txt");
var px= loadFileIntoArray("solution600.txt");
var excludes =Array();
var outexcludes =Array();
var parents = Array(); // Store index of best suited parent
var mismatches = Array(); // even count the minimum mismatches with the so called parent

	getBestMatchIndex(2,0);
	document.writeln("Line : "+my2D.length);

