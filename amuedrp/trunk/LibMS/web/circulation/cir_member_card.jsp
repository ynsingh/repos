<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page  pageEncoding="UTF-8" contentType="text/html" import="java.util.*,java.io.*,com.myapp.struts.hbm.*,com.myapp.struts.circulation.CirCardProcessActionForm"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<jsp:include page="/admin/header.jsp"/>

<%String memid=(String)request.getAttribute("memid"); %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>
</head>
<div
   style="  top:130px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">




<style type="text/css">
body
{

}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>


<script type="text/javascript" language="javascript">



  function quit()
  {

      window.location="<%=request.getContextPath()%>/circulation/cir_generate_card.jsp";
      return false;
  }


  function validation()
    {

var library_id=document.getElementById('f1').b1.value;
var Add=document.getElementById('f1').b2.value;
var card_type=document.getElementById('f1').b3.value;
var name=document.getElementById('f1').b5.value;
var class1=document.getElementById('f1').b7.value;
var session=document.getElementById('f1').b8.value;
var sadd=document.getElementById('f1').b9.value;
var sign=document.getElementById('f1').b12.value;


if(library_id==""){
    alert("Please Enter Library Name");
    document.getElementById('f1').b1.focus();
    return false;


}
if(Add==""){
    alert("Please Enter Library Address");
    document.getElementById('f1').b2.focus();
    return false;


}

if(card_type==""){
    alert("Please Enter Card Type Name");
    document.getElementById('f1').b3.focus();
    return false;


}
if(name==""){
    alert("Please Enter Student Name Label");
    document.getElementById('f1').b5.focus();
    return false;


}
if(class1==""){
    alert("Please Enter Label for Class Name");
    document.getElementById('f1').b7.focus();
    return false;


}
if(session==""){
    alert("Please Enter Session ");
    document.getElementById('f1').b8.focus();
    return false;


}
if(sadd==""){
    alert("Please Enter Lable for Student Address");
    document.getElementById('f1').b9.focus();
    return false;


}

if(sign==""){
    alert("Please Enter Authorized Sign Label");
    document.getElementById('f1').b12.focus();
    return false;


}


   if(document.getElementById('f1').a1.checked && document.getElementById('f1').a2.checked && document.getElementById('f1').a3.checked && document.getElementById('f1').a5.checked &&  document.getElementById('f1').a7.checked &&  document.getElementById('f1').a8.checked && document.getElementById('f1').a9.checked && document.getElementById('f1').a12.checked)

{

    return true;

}
else{
    alert("All Fields are Mandatory to Select");
    return false;


}

    }


function set(x,y)
{
        document.getElementById(x).value=document.getElementById(y).value;
alert(document.getElementById(x).value);

}



</script>


<body>

    <html:form action="/card_process" styleId="f1" method="post">


   <table  align="center" width="70%"   class="table">



  <tr><td colspan="2" width="60%" height="25px"  class="headerStyle"  align="center"> Member Card Setup



        </td></tr>

  <tr><td  align="center" width="50%">

          <br/>
            <table  >



                <tr><td  class="txtStyle" >Library Name</td><td  width="25%" class="table_textbox">
                        <input type="text" name="library_id" id="b1"  style="width:160px" /></td>
                    <td>
                                              
                        <input type="checkbox" name="checkbox1" onclick="set('a1','b1')" id="a1" value=""/> </td><td class="txtStyle">Print</td>
                   
                </tr>
                <tr><td class="txtStyle">Library Address</td><td class="table_textbox"><input type="text"  name="address"  id="b2" style="width:160px" />

                       </td><td><input type="checkbox" name="checkbox2" onclick="set('a2','b2')" id="a2" value=""/> </td><td class="txtStyle">Print</td>

                </tr>
                <tr><td class="txtStyle">Card Type</td><td class="table_textbox"><input type="text"  name="card_type"  id="b3" style="width:160px" />

                       </td><td><input type="checkbox" name="checkbox3" onclick="set('a3','b3')" id="a3" value=""/> </td><td class="txtStyle">Print</td>

                </tr>

              <%--  <tr><td class="txtStyle">Group</td><td class="table_textbox"><input type="text"  name="group"  id="b4" style="width:160px" />

                       </td><td><input type="checkbox" name="checkbox4" onclick="set('a4','b4')" id="a4" value=""/> </td><td class="txtStyle">Print</td>

                </tr>--%>


                <tr><td class="txtStyle">Name</td><td class="table_textbox"><input type="text" name="name" id="b5" style="width:160px" /></td>
                    <td><input type="checkbox" name="checkbox5" onclick="set('a5','b5')" id="a5"/> </td><td class="txtStyle">Print</td>
                </tr>
               
               <%-- <tr><td class="txtStyle">ID</td><td class="table_textbox"><input type="text"  name="id" id="b6" style="width:160px" />
                    </td>
                <td><input type="checkbox" name="checkbox6" onclick="set('a6','b6')"  id="a6"/> </td><td class="txtStyle">Print</td>
                </tr>--%>
              
                <tr>  <td class="txtStyle">Class</td><td class="table_textbox"><input type="text" name="clas" id="b7" style="width:160px" />
                <br/><div align="left" class="err" id="searchResult" style="border:#000000; "></div>
                </td>
                <td><input type="checkbox" name="checkbox7" onclick="set('a7','b7')" id="a7"/> </td><td class="txtStyle">Print</td>
                </tr>
               
                <tr><td class="txtStyle">Session</td><td  class="table_textbox"> <input type="text" name="session" id="b8" style="width:160px"  style="width:160px" />
                </td>
                 <td><input type="checkbox" name="checkbox8" onclick="set('a8','b8')" id="a8"/> </td><td class="txtStyle">Print</td>
                </tr>
 
                <tr><td class="txtStyle">Address</td><td  class="table_textbox"> <input type="text" name="address2" id="b9" style="width:160px"  style="width:160px" />
                 </td>
                 <td><input type="checkbox" name="checkbox9" onclick="set('a9','b9')" id="a9"/> </td><td class="txtStyle">Print</td>
                </tr>
   
               <%-- <tr><td class="txtStyle">Holder sign</td><td  class="table_textbox"> <input type="text" name="holder_sign" id="b10" style="width:160px"  style="width:160px" />
                </td>
                 <td><input type="checkbox" name="checkbox10" onclick="set('a10','b10')" id="a10"/> </td><td class="txtStyle">Print</td>
                </tr>--%>
   
              <%--  <tr><td class="txtStyle">DOB</td><td  class="table_textbox"> <input type="text" name="dob" id="b11" style="width:160px"  style="width:160px" />
                 </td>
                 <td><input type="checkbox" name="checkbox11" onclick="set('a11','b11')" id="a11"/> </td><td class="txtStyle">Print</td>
                </tr>--%>
   
                <tr><td class="txtStyle">Auth. Sign</td><td  class="table_textbox"> <input type="text" name="auth_sign" id="b12" style="width:160px"  style="width:160px" />
                 </td>
                 <td><input type="checkbox" name="checkbox12" onclick="set('a12','b12')" id="a12"/> </td><td class="txtStyle">Print</td>
                </tr>

             <input type="hidden" name="memid" value="<%=memid%>"/>


     </table>
      </td><td style="border : solid 1px black">
          <img src="<%=request.getContextPath()%>/images/MemberCard1.jpg" height="200px" border="3px" width="450px"/><br/>
          <%--<img src="<%=request.getContextPath()%>/images/MemberCard.jpg" height="200px" width="450px"/>--%>

      </td></tr>
 <tr><td colspan="3" align="center" class="txt2"><br/>
         <html:submit value="Submit"  onclick="return validation()" />
         <html:button property="cancel" value="Cancel" onclick="quit();" />
        

         &nbsp;&nbsp;

         
         <br/>        </td>

          </tr>
     </table>

         <input type="hidden" id="btn" name="button" value="" />

 </html:form>


</body>
</div>

</html>
