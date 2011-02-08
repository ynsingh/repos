<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>

<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11><TITLE>Staff Profile</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>


<script language="javascript" src="/js/clinicaldoc_common.js"></script>
<script language="javascript">
var abc="ss1";		
function refreshChild(entity_id,document_Id,formname,entity_type,tableData){			
	window.top.document.location.href = "./showdetailedform.jsp?entityId="+entity_id+"&documentId="+document_Id+"&formname="+formname+"&entitytype="+entity_type+"&Data="+tableData+"&edit_mode=1";
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
 	 opener.addRow(documentId,formname,entitytype,tabledata);
 	 javascript:window.close(); 
  	} 
}
	
</script>


</HEAD>
<BODY >

<form name="detailform" method="post" >

<table border ="1" width="100%" >
     <tr ><td align="center">    
     <input type="button" name="btclose" value="Close" onClick="closeChildwindow();" />                  
     </td></tr>
</table>


<%
String userId =request.getParameter("entityId");
//out.println(userId);

String documentId=request.getParameter("documentId");
//out.println(documentId);

String formname=request.getParameter("formname");
//out.println(formname);

String entitytype=request.getParameter("entitytype");
//out.println(entitytype);

String reload=request.getParameter("reload");
//out.println("reload"+reload);

String tabledata=request.getParameter("Data");
//out.println("Data:"+tabledata);

String edit_mode=request.getParameter("edit_mode");
//out.println("edit_mode:"+edit_mode);


%>


<script language="javascript">
   if(<%=reload%>=="1"){		
	var tabledataarray= new Array(<%=tabledata %>);		
	var tabledata="'"+tabledataarray[0]+"'";			
	//alert(tabledata);
	refreshChild(<%=userId%>,<%=documentId%>,<%=formname %>,<%=entitytype %>,tabledata);			
	}
</script>

<input type="HIDDEN" id="entityId" value=<%=userId%>></input>
<input type="HIDDEN"  id="documentId" value=<%=documentId%>></input>
<input type="HIDDEN" id="formname" value=<%=formname%>></input>
<input type="HIDDEN" id="entitytype" value=<%=entitytype%>></input>
<input type="HIDDEN" id="tabledata" value=<%=tabledata%>></input>

<% 
 String str1="../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT&entityId="+userId+"&documentId="+documentId+"&entitytype="+entitytype +"&formName="+formname+"&edit_mode="+edit_mode; 
%>

<iframe id='child' name='nchild' src =<%=str1%> width="100%" height="700px" frameborder="0">

</form>



</BODY></HTML>
