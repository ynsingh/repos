
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Master Tables</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

<link type="text/css" href="../css/jquery-ui-1.8.4.custom.css" rel="stylesheet" />
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript">
 var pi="0";var ct="0";var u="0";var i="0";
$(function() {
$("#tabs").tabs();
});



function load()
{       
     document.getElementById("if01").src="../master_tables?tab_name=general_master&action=show_general_master&category=";
   
}

function pim(){
     if(pi=="0"){
          document.getElementById("if02").src="../master_tables?tab_name=principal_investigator_master&action=show_principal_investigator_master";
          pi="1";
     }
}

function ctm(){
     if(ct=="0"){
     	 document.getElementById("if03").src="../master_tables?tab_name=courses_taught_master&action=show_courses_taught_master&faculty_name=";
         ct="1";
     }
}

function um(){
     if(u=="0"){
	 document.getElementById("if04").src="../master_tables?tab_name=university_master&action=show_university_master";
	 u="1";
     }
}

function im(){
     if(i=="0"){
     	 document.getElementById("if05").src="../master_tables?tab_name=institution_master&action=show_institution_master";
     	 i="1";
     }
}

</script>

</HEAD>
<BODY class="bodystyle" onload="load();">

<div class="demo">

 <div id="tabs">
     <ul>
     <li><a href="#tabs-1">General Master</a></li>
     <li><a href="#tabs-2" onclick="pim();" >Principal Investigator Master</a></li>
     <li><a href="#tabs-3"onclick="ctm();" >Courses Taught Master</a></li>
     <li><a href="#tabs-4"onclick="um();" >University Master</a></li>
     <li><a href="#tabs-5"onclick="im();" >Institution Master</a></li>
     </ul>
     <div id="tabs-1" align="center">
     <iframe name="General_Master" id="if01" src="" width="99%" height="350px" frameborder="0" >
       <p>Your browser does not support iframes.</p>
     </iframe>
     </div>
     <div id="tabs-2">
     <iframe name="Principal_Investigator_Master" id="if02" src="" width="99%" height="350px" frameborder="0" >
       <p>Your browser does not support iframes.</p>
     </iframe>	 
     </div>
     <div id="tabs-3">
     <iframe name="Courses_Taught_Master" id="if03" src="" width="99%" height="350px" frameborder="0" >
            <p>Your browser does not support iframes.</p>
     </iframe>
     </div>
     <div id="tabs-4">
     <iframe name="University_Master" id="if04" src="" width="99%" height="350px" frameborder="0" >
                 <p>Your browser does not support iframes.</p>
     </iframe>
     </div>
     <div id="tabs-5">
     <iframe name="Institution_Master" id="if05" src="" width="99%" height="350px" frameborder="0" >
                 <p>Your browser does not support iframes.</p>
     </iframe>
     </div>
 </div>

</div>

</BODY>
</HTML>






	
