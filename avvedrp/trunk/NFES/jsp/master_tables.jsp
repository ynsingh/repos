
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String lc="";
String tab = "";String universityId="";String institutionId="";
try{
     lc=(String) session.getAttribute("language");
     ml.init(lc);  
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
          
     tab=request.getParameter("tab"); 
     universityId=request.getParameter("university_id");
     institutionId=request.getParameter("institution_id");
     
}catch(Exception e){
     e.printStackTrace();
}

%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Master Tables</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

<link type="text/css" href="../css/jquery-ui-1.8.4.custom.css" rel="stylesheet" />
<link type="text/css" href="../css/oiostyles.css" rel="stylesheet" />
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript">
 var g="0";var pi="0";var ct="0";var u="0";var i="0";var dm="0";
$(function() {
$("#tabs").tabs();
});


function load()
{  
     var tab = <%=tab%>;
     if(tab == "5"){
         if(i=="0"){
     	 document.getElementById("if05").src="../MasterTables?tab_name=institution_master&action=show_institution_master&university_id="+'<%=universityId%>';
     	 i="1";
     	}
     }
     else if(tab == "6"){
     if(dm=="0"){
     	 document.getElementById("if06").src="../MasterTables?tab_name=institution_department_master&action=show_institution_department_master&institution_id="+'<%=institutionId%>';
     	 dm="1";
     	 tab="";
     	}
     }
     else{
     gm();
     }
}
function gm(){
     if(g=="0"){
     	  document.getElementById("if01").src="../MasterTables?tab_name=general_master&action=show_general_master&category=";
         g="1";
     }
}
function pim(){
     if(pi=="0"){
          document.getElementById("if02").src="../MasterTables?tab_name=principal_investigator_master&action=show_principal_investigator_master";
          pi="1";
     }
}

function ctm(){
     if(ct=="0"){
     	 document.getElementById("if03").src="../MasterTables?tab_name=courses_taught_master&action=show_courses_taught_master&faculty_name=";
         ct="1";
     }
}

function um(){
     if(u=="0"){
	 document.getElementById("if04").src="../MasterTables?tab_name=university_master&action=show_university_master";
	 u="1";
     }
}

function im(){
     if(i=="0"){
     	 document.getElementById("if05").src="../MasterTables?tab_name=institution_master&action=show_institution_master";
     	 i="1";
     }
}
function idm(){
     if(dm=="0"){
     	 document.getElementById("if06").src="../MasterTables?tab_name=institution_department_master&action=show_institution_department_master";
     	 dm="1";
     }
}

</script>

</HEAD>
<BODY class="bodystyle" onload="load();">

<div class="demo">

 <div id="tabs">
     <ul>
     <li class="horizontalTabli"><a href="#tabs-1" onclick="gm();"><%=ml.getValue("general_master")%></a></li>
     <li class="horizontalTabli"><a href="#tabs-2" onclick="pim();" ><%=ml.getValue("principal_investigator_master")%></a></li>
     <li class="horizontalTabli"><a href="#tabs-3"onclick="ctm();" ><%=ml.getValue("courses_taught_master")%></a></li>
     <li class="horizontalTabli"><a href="#tabs-4"onclick="um();" ><%=ml.getValue("university_master")%></a></li>
     <li class="horizontalTabli"><a href="#tabs-5"onclick="im();" ><%=ml.getValue("intitution_master")%></a></li>
     <li class="horizontalTabli"><a href="#tabs-6"onclick="idm();" ><%=ml.getValue("intitution_department_master")%></a></li>
     </ul>
     <div id="tabs-1" align="center">
     <iframe name="General_Master" id="if01" src="" width="99%" height="330px" frameborder="0" >
       <p>Your browser does not support iframes.</p>
     </iframe>
     </div>
     <div id="tabs-2">
     <iframe name="Principal_Investigator_Master" id="if02" src="" width="99%" height="330px" frameborder="0" >
       <p>Your browser does not support iframes.</p>
     </iframe>	 
     </div>
     <div id="tabs-3">
     <iframe name="Courses_Taught_Master" id="if03" src="" width="99%" height="330px" frameborder="0" >
            <p>Your browser does not support iframes.</p>
     </iframe>
     </div>
     <div id="tabs-4">
     <iframe name="University_Master" id="if04" src="" width="99%" height="330px" frameborder="0" >
                 <p>Your browser does not support iframes.</p>
     </iframe>
     </div>
     <div id="tabs-5">
     <iframe name="Institution_Master" id="if05" src="" width="99%" height="330px" frameborder="0" >
                 <p>Your browser does not support iframes.</p>
     </iframe>
     </div>
     <div id="tabs-6">
          <iframe name="Institution_department_Master" id="if06" src="" width="99%" height="330px" frameborder="0" >
                      <p>Your browser does not support iframes.</p>
          </iframe>
     </div>
 </div>

</div>

</BODY>
</HTML>






	
