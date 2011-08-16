<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--
     Design by Iqubal Ahmad
     Modified on 2011-02-02
     This jsp page is meant for Dynamically change button value as View,Update,Deleate this is only page from where
     View, update, Deleate is done also used for   Ajax for
     Dept,Fac,course & image upload can be done.
     This jsp page is third page  for one Complete Process  of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp"/>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>



<script language="javascript" type="text/javascript">

function newXMLHttpRequest() {
var xmlreq = false;
// Create XMLHttpRequest object in non-Microsoft browsers
if (window.XMLHttpRequest) {
xmlreq = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
// Try to create XMLHttpRequest in later versions
// of Internet Explorer
xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e1) {
// Failed to create required ActiveXObject
try {
// Try version supported by older versions
// of Internet Explorer
xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
} catch (e2) {
// Unable to create an XMLHttpRequest by any means
xmlreq = false;
}
}
}
return xmlreq;
}
/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
// Return an anonymous function that listens to the XMLHttpRequest instance
return function () {
// If the request's status is "complete"
if (req.readyState == 4) {
// Check that we received a successful response from the server
if (req.status == 200) {
// Pass the XML payload of the response to the handler function.
responseXmlHandler(req.responseXML);
} else {
// An HTTP problem has occurred
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}

function copy(){

  var i=document.getElementById("lname1");
  var j=document.getElementById("lname2");
  i.value=j.value;
  var fname1=document.getElementById("fname1");
  var fname2=document.getElementById("fname2");
  fname1.value=fname2.value;
   var mname1=document.getElementById("mname1");
  var mname2=document.getElementById("mname2");
  mname1.value=mname2.value;
   var city1=document.getElementById("city1");
  var city11=document.getElementById("city11");
  city1.value=city11.value;
  var city2=document.getElementById("city2");
  var city22=document.getElementById("city22");
  city2.value=city22.value;
   var state1=document.getElementById("state1");
  var state11=document.getElementById("state11");
  state1.value=state11.value;
   var state2=document.getElementById("state2");
  var state22=document.getElementById("state22");
  state2.value=state22.value;
  var country1=document.getElementById("country1");
  var country11=document.getElementById("country11");
  country1.value=country11.value;
   var country2=document.getElementById("country2");
  var country22=document.getElementById("country22");
  country2.value=country22.value;
   var ph1=document.getElementById("ph1");
  var ph11=document.getElementById("ph11");
  ph1.value=ph11.value;
   var ph2=document.getElementById("ph2");
  var ph22=document.getElementById("ph22");
  ph2.value=ph22.value;
   var add1=document.getElementById("add1");
  var add11=document.getElementById("add11");
  add1.value=add11.value;
   var add2=document.getElementById("add2");
  var add22=document.getElementById("add22");
  add2.value=add22.value;
   var mail1=document.getElementById("mail1");
  var mail2=document.getElementById("mail2");
  mail1.value=mail2.value;
   var fax1=document.getElementById("fax1");
  var fax2=document.getElementById("fax2");
  fax1.value=fax2.value;
  var mem_id1=document.getElementById("mem_id1");
   var mem_id2=document.getElementById("mem_id2");
   mem_id1.value=mem_id2.value;
   
}
 function showdiv(){

        var ele = document.getElementById("image1");


	if(ele.style.display == "block") {
    		ele.style.display = "none";

  	}
	else {
		ele.style.display = "block";

	}


    }
    </script>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    String button1;
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

 <%

     CirMemberDetail     cirmemdetail  =(CirMemberDetail)session.getAttribute("cirmemdetail");
  

  String lname=(String)request.getAttribute("lname");
if(lname==null)
    lname=cirmemdetail.getLname();
String fname=(String)request.getAttribute("fname");
if(fname==null)
    fname=cirmemdetail.getFname();
String mname=(String)request.getAttribute("mname");
if(mname==null)
    mname=cirmemdetail.getMname();
String add1=(String)request.getAttribute("add1");
if(add1==null)
    add1=cirmemdetail.getAddress1();
String add2=(String)request.getAttribute("add2");
if(add2==null)
    add2=cirmemdetail.getAddress2();
String city1=(String)request.getAttribute("city1");
if(city1==null)
    city1=cirmemdetail.getCity1();
String city2=(String)request.getAttribute("city2");
if(city2==null)
    city2=cirmemdetail.getCity2();
String state1=(String)request.getAttribute("state1");
if(state1==null)
    state1=cirmemdetail.getState1();
String state2=(String)request.getAttribute("state2");
if(state2==null)
    state2=cirmemdetail.getState2();
String country1=(String)request.getAttribute("country1");
if(country1==null)
    country1=cirmemdetail.getCountry1();
String country2=(String)request.getAttribute("country2");
if(country2==null)
    country2=cirmemdetail.getCountry2();
String pin1=(String)request.getAttribute("pin1");
if(pin1==null)
    pin1=cirmemdetail.getPin1();
String pin2=(String)request.getAttribute("pin2");
if(pin2==null)
    pin2= cirmemdetail.getPin2();
String ph1=(String)request.getAttribute("ph1");
if(ph1==null)
    ph1=cirmemdetail.getPhone1();
String ph2=(String)request.getAttribute("ph2");
if(ph2==null)
    ph2=cirmemdetail.getPhone2();

String mail_id=(String)request.getAttribute("mail_id");
if(mail_id==null)
    mail_id=cirmemdetail.getEmail();


String fax=(String)request.getAttribute("fax");
if(fax==null)
    fax=cirmemdetail.getFax();


String mem_id=(String)request.getAttribute("mem_id");
if(mem_id==null)
    mem_id=cirmemdetail.getId().getMemId();




    

        String button=(String)request.getAttribute("button");
        if (button==null) button=(String)session.getAttribute("page");
        session.setAttribute("page", button);
        boolean read=true;
         boolean button_visibility=true;
     

    %>

    <%
 if(button.equals("View"))
 {
     button1=resource.getString("circulation.cir_member_reg.view");
   read=true;
   button_visibility=false;
 }
 if(button.equals("Update"))
 {
     button1=resource.getString("circulation.cir_member_reg.update");
   read=false;
   button_visibility=true;
 }
 if(button.equals("Delete"))
 {
     button1=resource.getString("circulation.cir_member_reg.delete");
   read=true;
   button_visibility=true;
 }
%>




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






</head>
<body>


   <div id="image1"
   style="  top:130px;background: red;
   left:30%;
   overflow: hidden;
      position: absolute;
      display: none;"

      >


   <html:form action="/cirimageupload1" method="post" styleId="form1" enctype="multipart/form-data">


    <table class="table" style="border:5px solid blue;" align="center" height="100px" width="400px">
           <tr><td>Select Image:
       <html:file  property="img" name="CirculationMemberActionForm" styleId="img" onchange="submit()"   onclick="copy()" />
      <input type="hidden" name="filename" tabindex="16" id="filename" />
         <input type="button" value="Cancel" onclick="showdiv();"/>      </td></tr></table>
      

           <html:hidden property="TXTLNAME" name="CirculationMemberActionForm" styleId="lname1"/>
          <html:hidden property="TXTFNAME" name="CirculationMemberActionForm" styleId="fname1"/>
          <html:hidden property="TXTMNAME" name="CirculationMemberActionForm" styleId="mname1"/>
          <html:hidden property="TXTADD1" name="CirculationMemberActionForm" styleId="add1"/>
          <html:hidden property="TXTADD2" name="CirculationMemberActionForm" styleId="add2"/>
          <html:hidden property="TXTCITY1" name="CirculationMemberActionForm" styleId="city1"/>
          <html:hidden property="TXTCITY2" name="CirculationMemberActionForm" styleId="city2"/>
          <html:hidden property="TXTSTATE1" name="CirculationMemberActionForm" styleId="state1"/>
          <html:hidden property="TXTSTATE2" name="CirculationMemberActionForm" styleId="state2"/>
          <html:hidden property="TXTCOUNTRY1" name="CirculationMemberActionForm" styleId="country1"/>
          <html:hidden property="TXTCOUNTRY2" name="CirculationMemberActionForm" styleId="country2"/>
          <html:hidden property="TXTPH1" name="CirculationMemberActionForm" styleId="ph1"/>
          <html:hidden property="TXTPH2" name="CirculationMemberActionForm" styleId="ph2"/>
          <html:hidden property="TXTEMAILID" name="CirculationMemberActionForm" styleId="mail1"/>
          <html:hidden property="TXTFAX" name="CirculationMemberActionForm" styleId="fax1"/>
         <html:hidden property="TXTMEMID" name="CirculationMemberActionForm" styleId="mem_id1"/>

    </html:form>

    </div>



    <html:form action="/cirviewmember1" method="post" onsubmit="return validation();">

 

   <table dir="<%=rtl%>" align="center" width="50%"  class="table">



  <tr><td    class="headerStyle"  align="center">


		 <%=resource.getString("circulation.cir_view_update.memberregistration")%>



        </td></tr>

  <tr><td valign="top" align="center" widt="50%" >

       <br/>
            <table dir="<%=rtl%>" width="80%" >



                <tr><td dir="<%=rtl%>" class="txtStyle" width="25%"><%=resource.getString("circulation.cir_newmember.memberid")%></td><td dir="<%=rtl%>" width="25%" class="table_textbox">
                        <html:text    property="TXTMEMID" readonly="true" value="<%=mem_id %>" styleId="mem_id2" name="CirculationMemberActionForm" style="width:160px" /></td>
                   <td dir="<%=rtl%>" width="25%" rowspan="4" width="200px" class="table_textbox" valign="bottom">

                        <%if (request.getAttribute("imagechange")!=null){%>
                        <html:img src="./circulation/upload.jsp"  alt="no Image Selected" width="120" height="120"/>
                        <%}else{%>
                        <html:img src="./circulation/viewimage.jsp" width="128" height="120" />
                        <%}%><br/> <%if(button.equals("Update")==true)
                        {%>
                        <a href="#" onclick="javascript:showdiv();"> <%=resource.getString("circulation.cir_newmember.imageupload")%></a>
                        <%}%>

                     
                    </td>   </tr>
                   <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.fname")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>"  property="TXTFNAME" styleId="fname2" value="<%=fname %>" name="CirculationMemberActionForm" style="width:160px" /><br/>
                 <html:messages id="err_name" property="TXTFNAME">
				<bean:write name="err_name" />

			</html:messages>

                </td>

                </tr>
            <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.mname")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTMNAME" readonly="<%=read%>" styleId="mname2"  value="<%=mname%>" name="CirculationMemberActionForm" style="width:160px" /></td></tr>
            <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.lname")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>" property="TXTLNAME" styleId="lname2"  value="<%=lname%>" name="CirculationMemberActionForm" style="width:160px" />
                <br/>
                 <html:messages id="err_name" property="TXTLNAME">
				<bean:write name="err_name" />

			</html:messages>
                </td>



                </tr>
            <tr>  <td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.email")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>" property="TXTEMAILID" onblur="return echeck(mail2.value)" styleId="mail2" value="<%=mail_id%>" name="CirculationMemberActionForm" style="width:160px" />
                <br/><div align="left" class="err" id="searchResult" style="border:#000000; "></div>
                 <html:messages id="err_name" property="TXTEMAILID">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                 

            </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.localadd")%>*</td><td dir="<%=rtl%>" class="table_textbox"> <html:text property="TXTADD1" styleId="add11" style="width:160px" value="<%=add1%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" />
                 <br/>
                 <html:messages id="err_name" property="TXTADD1">
		<bean:write name="err_name" />

		</html:messages>

                 </td>
              
             </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.city")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCITY1" value="<%=city1%>" styleId="city11" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>
             </tr>


                 
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.state")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSTATE1" styleId="state11" value="<%=state1%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>



                 </td></tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.country")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCOUNTRY1" styleId="country11" value="<%=country1 %>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>
                 <br/>


                 </td>
                 </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.mobile")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTPH1" styleId="ph11" value="<%=ph1%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>
                 <br/>


                 </td> </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.landlineno")%>.</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTPH2"  styleId="ph22" value="<%=ph2%>"  readonly="<%=read%>"  name="CirculationMemberActionForm" style="width:160px" /></td>
                 
                 </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.fax")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTFAX" styleId="fax2" value="<%=fax%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td>



         </tr>

            <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.permadd")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text property="TXTADD2" style="width:160px" styleId="add22" value="<%=add2%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td>
                <%--   <td> Request Date
            </td><td class="table_textbox"><html:text  property="TXTREQ_DATE"   value="" styleClass="textBoxWidth" value="<%=cirmemdetail.getRequestdate()%>" disabled="true"   style="width:160px" /> --%>
            </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.city")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCITY2" styleId="city22" value="<%=city2%>" style="width:160px" readonly="<%=read%>"/></td>

                 </tr>
             <tr><td dir="<%=rtl%>"  class="txtStyle"><%=resource.getString("circulation.cir_newmember.state")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSTATE2" styleId="state22" value="<%=state2%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td>

                 </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.country")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCOUNTRY2" styleId="country22" value="<%=country2%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td></tr>


             <tr><td></td><td>
         <%if(button_visibility){%>
         <input type="submit" value="<%=button1%>"  onclick="return del()" /> 
         <%}%>
         &nbsp;&nbsp;

         <input type="button" value="<%=resource.getString("circulation.cir_newmember.back")%>" property="button" onclick="return quit()"/>
         <br/>

                 </td></tr>
     </table>
      </td></tr>

 <input type="hidden" id="btn" name="button" value="" />

   </table>

 </html:form>


</body>


</div>
<script language="javascript" type="text/javascript">
    function quit()
    {
        location.href="<%=request.getContextPath()%>/circulation/cir_member_reg.jsp";
    }

    function del()
    {
      document.getElementById('btn').value="<%=button%>" ;
    var option=document.getElementById('btn').value;
    if(option=="Delete"){
        var a=confirm("<%=resource.getString("circulation.cir_newmember.douwanttodelrec")%>");
       // alert(a);
        if(a!=true)
            {
                document.getElementById('button').focus();
               return false;

        }
        else
            return true;
    }
    }

</script>
 <script language="javascript" type="text/javascript">

function echeck(str) {


availableSelectList = document.getElementById("searchResult");
availableSelectList.innerHTML="";
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   availableSelectList.innerHTML="Invalid E-mail ID";
                   document.getElementById('mail2').value="";
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		    availableSelectList.innerHTML="Invalid E-mail ID";
                    document.getElementById('mail2').value="";
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		 }

 		 return true
	}





     function validation()
    {
     
    
   var email_id=document.getElementById('mail2');
    var first_name=document.getElementById('fname2');

    var last_name=document.getElementById('lname2');
    var local=document.getElementById('add11');
    var phone=document.getElementById('ph11');
   

    var city1=document.getElementById('city11');
    var state1=document.getElementById('state11');
    var country1=document.getElementById('country11');




var str= "<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";


   if(email_id.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enteremailid")%> ";
            alert(str);
            document.getElementById('mail2').focus();
            return false;
        }



   if(first_name.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterfname")%>";
            alert(str);
            document.getElementById('fname2').focus();
            return false;
        }
if(last_name.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterlname")%>";
            alert(str);
            document.getElementById('lname2').focus();
            return false;
        }

  if(local.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterlocaladd")%>";
            alert(str);
            document.getElementById('add11').focus();
            return false;
        }

 if(phone.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterphno")%> ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;
        }


  if(city1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entercity")%> ";
            alert(str);
            document.getElementById('city11').focus();
            return false;
        }
if(state1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterstate")%> ";
            alert(str);
            document.getElementById('state11').focus();
            return false;
        }

        if(country1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entercountry")%> ";
            alert(str);
            document.getElementById('country11').focus();
            return false;
        }

   




var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";




   
    if(echeck(email_id.value)==false){

     str+="\n Invalid E-mail ID ";
            alert(str);
              document.getElementById('mail2').value="";
            document.getElementById('mail2').focus();
            return false;

 }
 if(isNaN(phone.value)){

 str+="\n Invalid Mobile No ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;

 }


if(str=="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('mail2').focus();
        return false;
    }




    }




</script>






</html>
