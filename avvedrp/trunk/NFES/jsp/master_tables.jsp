
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String gm="";String pim="";String ctm="";String um="";String im="";String lc="";String idm="";
try{
     lc=(String) session.getAttribute("language");
     conn = db.getMysqlConnection();
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and file_code=26 and language_code=\'"+lc+"\'");
     theResult.last();int len=theResult.getRow();String cn[]=new String[len];String ls[]=new String[len];
     int i=0;theResult.beforeFirst();
     while(theResult.next()){
          cn[i]=theResult.getString("control_name");
          ls[i]=theResult.getString("language_string");
          i++;
     }
     
     for(i=0;i<len;i++){
     	if(cn[i].equals("general_master")){
     		gm=ls[i];
     	}else if(cn[i].equals("principal_investigator_master")){
     		pim=ls[i];
     	}else if(cn[i].equals("courses_taught_master")){
     		ctm=ls[i];
     	}else if(cn[i].equals("university_master")){
     		um=ls[i];
     	}else if(cn[i].equals("intitution_master")){
     		im=ls[i];
     	}else if(cn[i].equals("intitution_department_master")){
     		idm=ls[i];
     	}
     	
     }
     
     
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}
theResult.close();theStatement.close();conn.close();	
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
 var pi="0";var ct="0";var u="0";var i="0";var dm="0";
$(function() {
$("#tabs").tabs();
});



function load()
{       
     document.getElementById("if01").src="../MasterTables?tab_name=general_master&action=show_general_master&category=";
   
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
     <li><a href="#tabs-1"><%=gm%></a></li>
     <li><a href="#tabs-2" onclick="pim();" ><%=pim%></a></li>
     <li><a href="#tabs-3"onclick="ctm();" ><%=ctm%></a></li>
     <li><a href="#tabs-4"onclick="um();" ><%=um%></a></li>
     <li><a href="#tabs-5"onclick="im();" ><%=im%></a></li>
     <li><a href="#tabs-6"onclick="idm();" ><%=idm%></a></li>
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






	
