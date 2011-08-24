<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>

<TITLE>NFES - Faculty Profile Approval Page</TITLE>

<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>


<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
<style type="text/css">
#footer {position: fixed; bottom: 0; left: 0px; right:0px; width: 100%; } 
</style>


<script language="javascript" src="../js/clinicaldoc_common.js"></script>
<script language="javascript">
	
function calcHeight(){  	
  var browserWindowSize = getBrowserWindowSize();    
  document.getElementById('child').height= browserWindowSize.height-100;  
  document.getElementById('child').width= browserWindowSize.width-15;
  hidemenusinprofilepage();
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

function hidemenusinprofilepage(){
if(document.getElementById("formname").value=="staff_profile_staff_v0.xml"){
	var ifr = document.getElementById("child");
	ifr.contentWindow.document.getElementById('userdetailstab').className="hidetd";
	ifr.contentWindow.document.getElementById('menutd').className="hidetd";
	ifr.contentWindow.document.getElementById('uploadresumeblank').className="hidetd";
	ifr.contentWindow.document.getElementById('btclose').style.visibility="visible";
}	
}

</script>


</HEAD>
<BODY class="bodystyle1" style="overflow: hidden" onresize="calcHeight()" onload="calcHeight()" >

<form name="detailform" method="post" >


<%
String userId =request.getParameter("entityId");
String documentId=request.getParameter("documentId");
String formname=request.getParameter("formname");
String entitytype=request.getParameter("entitytype");
String reload=request.getParameter("reload");
String tabledata=request.getParameter("Data");
String edit_mode=request.getParameter("edit_mode");
%>


<input type="HIDDEN" id="entityId" value=<%=userId%>></input>
<input type="HIDDEN"  id="documentId" value=<%=documentId%>></input>
<input type="HIDDEN" id="formname" value=<%=formname%>></input>
<input type="HIDDEN" id="entitytype" value=<%=entitytype%>></input>
<input type="HIDDEN" id="tabledata" name="tabledata" value=<%=tabledata%>></input>

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
Connection conn=null;
Connection conn1=null;
Statement theStatement=null;
ResultSet theResult=null;
String imageName="";
try{

    /* =================== To display Staff Image 04-07-11 Rajitha ================ */
    conn1 = db.getMysqlConnection();
    PreparedStatement pst=conn1.prepareStatement("SELECT upload_photo FROM staff_profile_report_v0_values WHERE idf="+userId);	
    ResultSet rs=pst.executeQuery();
    while(rs.next())
    {
      imageName=userId+"/photo/"+rs.getString(1);					
    }	
    conn1.close();
     /* ================================ End ======================================= */

     conn = db.getMysqlConnection();
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select * from  staff_profile_masterdetails_v0_values where userid="+ userId);
     while(theResult.next()){    %>     
     <div name="userdetails" id="userdetails"  >
     <table class="thead" width="100%" >
     <tr >
     <td rowspan="2" width="100" height="100"><img src= "../GetImageServlet?filename=<%=imageName%>" height="100%" width="100%"></td>		
     <td align="center">
     <h2><%=theResult.getString("user_full_name")%>&nbsp;&nbsp;&nbsp;</h2>
     <b><%=theResult.getString("designation")%>&nbsp;&nbsp;&nbsp;
     <%=theResult.getString("department")%>&nbsp;&nbsp;&nbsp;
     <%=theResult.getString("institution")%>&nbsp;&nbsp;&nbsp;
     <%=theResult.getString("university")%>
     </b>
     </td>     
     </tr>
     <!--<tr>
     <td><b>University:</b><%=theResult.getString("university")%> </td>
     <td><b>Institution:</b><%=theResult.getString("institution")%> </td>
     <td><b>Department:</b><%=theResult.getString("department")%> </td>
     <td><b>Designation:</b><%=theResult.getString("designation")%> </td>
     </tr>-->
     
     </table>
     </div>
     <%
     }
}
catch(Exception e){
     e.printStackTrace();
}
conn.close();
 
String str1="../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT_FOR_APPROVE&entityId="+userId+"&documentId="+documentId+"&entitytype="+entitytype +"&formName="+formname+"&edit_mode="+edit_mode; 
%>

<table class="bodystyle1" width="100%" align="center" ><tr><td>
<iframe  id='child' name='child' src =<%=str1%>  width="100%"  scrolling="auto" frameborder="0" onLoad="calcHeight();" >
</iframe>
</td></tr><tr><td></td></tr></table>
</form>

</BODY>
</HTML>

<!--<html>
<body>
<div id="footer">
<center>
<div class="footerdiv" > 
Developed by Amrita University under ERP, NME ICT, MHRD
</div>
</center>
</div> 
</BODY>
</HTML>-->


