<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>

<TITLE>Faculty Profile</TITLE>

<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>


<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
<style type="text/css">
/*.closebtn {
  position: relative;
  display: inline;  
  top: -3.55em;
  left:34.9em;  
}*/
#footer {position: fixed; bottom: 0; left: 0px; right:0px; width: 100%; } 
</style>




<script language="javascript" src="../js/clinicaldoc_common.js"></script>
<script language="javascript">


function refreshChild(entity_id,document_Id,formname,entity_type,tableData){			
	//alert(   tableData);	
	window.top.document.location.href = "./showdetailedform.jsp?entityId="+entity_id+"&documentId="+document_Id+"&formname="+formname+"&entitytype="+entity_type+"&Data="+tableData+"&edit_mode=1";
	//document.getElementById('tabledata').value=tableData;
	//document.getElementById('documentId').value=document_Id;
	
}


function closeChildwindow()
{
 
 var tabname="Tab_"+document.getElementById("formname").value;
 var tabledata=document.getElementById("tabledata").value; 
 
  if (tabledata=="null"){
  	javascript:window.close(); 
  	}	
  else{  	 	  
  	 var documentId=document.getElementById("documentId").value;	
	 var formname =document.getElementById("formname").value;
 	 var entitytype=document.getElementById("entitytype").value; 	   	 
 	 var tabledata=getValueFromcontrol(tabledata);
 	 //alert(tabledata); 	 
 	 opener.addRow(documentId,formname,entitytype,tabledata);
 	 javascript:window.close(); 
  	} 
}
	
function calcHeight(){ 
  var browserWindowSize = getBrowserWindowSize();
  //var the_height= document.getElementById('child').contentWindow.document.body.scrollHeight;    
  document.getElementById('child').height= (browserWindowSize.height-10); 
  document.getElementById('child').width= browserWindowSize.width;
 
}

function getBrowserWindowSize()
{
  var myWidth = 0, myHeight = 0;
  if( typeof( window.innerWidth ) == 'number' )
  {
    //Non-IE
    myWidth = window.innerWidth;
    myHeight = window.innerHeight;
  }
  else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) )
  {
    //IE 6+ in 'standards compliant mode'
    myWidth = document.documentElement.clientWidth;
    myHeight = document.documentElement.clientHeight;
  }
  else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) )
  {
    //IE 4 compatible
    myWidth = document.body.clientWidth;
    myHeight = document.body.clientHeight;
  }

  return {width:myWidth, height:myHeight};
}

function getValueFromcontrol()
{
var ifr = document.getElementById("child");
document.getElementById('documentId').value=ifr.contentWindow.document.getElementsByName('documentId')[0].value;
var tabledata=document.getElementById('tabledata').value.split(',');
var tableSplitdata="";

tableSplitdata=ifr.contentWindow.document.getElementsByName('documentId')[0].value;

for (i=0;i<tabledata.length;i++){		  		
	tableSplitdata=tableSplitdata+"||,";	
	if (ifr.contentWindow.document.getElementsByName(tabledata[i])[0].type=="hidden"){
	   if (ifr.contentWindow.document.getElementsByName(tabledata[i])[1].type=="radio"){
		var radioLength =ifr.contentWindow.document.getElementsByName(tabledata[i]).length;				
		for(var r = 1; r <radioLength; r++) {		 
		  if(ifr.contentWindow.document.getElementsByName(tabledata[i])[r].checked) {		    
		    tableSplitdata=tableSplitdata+ifr.contentWindow.document.getElementsByName(tabledata[i])[r].value;		    
				}
		}		
	  }
	}  
	else{		
		var urlFields=document.getElementById('URLFields').value;
		var srchField=tabledata[i]+"~";
		if((urlFields.indexOf(srchField)>=0) && ((ifr.contentWindow.document.getElementsByName(tabledata[i])[0].value)!="")){
			tableSplitdata=tableSplitdata+"<a href='"+ifr.contentWindow.document.getElementsByName(tabledata[i])[0].value+"' target='blank'>View</a>";		  		
		}else{
			tableSplitdata=tableSplitdata+ifr.contentWindow.document.getElementsByName(tabledata[i])[0].value;		  		
		}		
	}	
}
return(tableSplitdata);
}


function getURLFields(){
	var tablename=document.getElementById("formname").value;
	var url = '../GetAjaxResponse?action=GET_URL_FIELDS&tablename='+tablename;
    if (window.ActiveXObject){
        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }else if (window.XMLHttpRequest){
        httpRequest = new XMLHttpRequest();
    }        
    httpRequest.open("GET", url, true);
    httpRequest.onreadystatechange = function() {getURLFieldName(); } ;
    httpRequest.send(null); 	
}

function getURLFieldName(){
		if (httpRequest.readyState == 4){
            if(httpRequest.status == 200) {
                var URLFieldXML = httpRequest.responseXML.getElementsByTagName("reponseXML")[0];
                var URLFieldDets="";
                if(URLFieldXML.childNodes[0]){
	               URLFieldDets = URLFieldXML.childNodes[0].nodeValue;             
					if(URLFieldDets=="null"){
   						document.getElementById('URLFields').value="";
					}else{
						document.getElementById('URLFields').value=URLFieldDets;
					}
                }                                            	
           }else{
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText);
            }         		
          }  
}
</script>


</HEAD>
<BODY class="bodystyle" style="overflow: hidden" onresize="calcHeight()" onload="calcHeight()" >

<form name="detailform" method="post" >


<%
String userId =request.getParameter("entityId");
String documentId=request.getParameter("documentId");
String formname=request.getParameter("formname");
String entitytype=request.getParameter("entitytype");
String reload=request.getParameter("reload");
String tabledata=request.getParameter("Data");
String edit_mode=request.getParameter("edit_mode");
String message=request.getParameter("message");
%>


<script language="javascript">
   if(<%=reload%>=="1"){	   	
	var tabledataarray= new Array(<%=tabledata %>);		
	var tabledata="'"+tabledataarray[0]+"'";				
	var document_id='';
	document_id=<%=documentId%>;	
	var msg=<%=message%>;
	alert(msg);
	refreshChild(<%=userId%>,<%=documentId%>,<%=formname %>,<%=entitytype %>,tabledata);			
	}
</script>

<input type="HIDDEN" id="entityId" value=<%=userId%>></input>
<input type="HIDDEN"  id="documentId" value=<%=documentId%>></input>
<input type="HIDDEN" id="formname" value=<%=formname%>></input>
<input type="HIDDEN" id="entitytype" value=<%=entitytype%>></input>
<input type="HIDDEN" id="tabledata" name="tabledata" value=<%=tabledata%>></input>
<input type="HIDDEN" id="URLFields" name="URLFields" value=""></input>
<!--<table width=101% style="background-color: #FFFFFF; border=1;margin: 0px -5px">
	<tr>
	<td width=5% rowspan="2"><img src="../images/loginheader_logo.PNG" ></td>
	</tr>	
	<tr>
	<td colspan=2 width=95%  align="right" valign="bottom"><img src="../images/loginheader_NFES.PNG" ></td>	
	</tr>
</table>	
<div style="background-image: url('../images/innerpageheaderhr.jpg');repeat-x;scroll 0 0 #ef9e00;margin-left: -8px; margin-right: -8px;">&nbsp;</div>
-->

<% 
 String str1="../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT&entityId="+userId+"&documentId="+documentId+"&entitytype="+entitytype +"&formName="+formname+"&edit_mode="+edit_mode; 
%>
<!-- -->
<table class="bodystyle1" width="100%" style="margin: 0px 0px 0px -10px;" ><tr><td>
<iframe  id='child' name='child' src =<%=str1%>  width="100%"  scrolling="auto" frameborder="0"></iframe>
</td></tr></table> 
</form>




<script language="javascript">
getURLFields();
window.onunload = function(){
 var tabledata=document.getElementById('tabledata').value;    
  if(tabledata!="null"){  	
	closeChildwindow();
  }	
} 
</script>

<!--<div id="footer">
<center>
<div class="footerdiv" > 
Developed by Amrita University under ERP, NME ICT, MHRD
</div>
</center>
</div> -->
</BODY>
</HTML>


