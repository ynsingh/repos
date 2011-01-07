<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>New Entry..</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>
<SCRIPT LANGUAGE="JAVASCRIPT">

function validate(){


if(document.Form1.TXTLIBNAME.value=="")
{
   alert("Please enter Library name");
   document.Form1.TXTLIBNAME.focus();
   return false;
}

if(document.Form1.TXTLIBID.value=="")
{
   alert("Please enter Library_id");
   document.Form1.TXTLIBID.focus();
   return false;
}

if(document.Form1.TXTFNAME.value=="")
{
   alert("Please enter first name");
   document.Form1.TXTFNAME.focus();
   return false;
}

if(document.Form1.TXTLNAME.value=="")
{
   alert("Please enter last name");
   document.Form1.TXTLNAME.focus();
   return false;
}

if(document.Form1.TXTCITY.value=="")
{
   alert("Please enter City");
   document.Form1.TXTCITY.focus();
   return false;
}
if(document.Form1.TXTSTATE.value=="")
{
   alert("Please enter state");
   document.Form1.TXTSTATE.focus();
   return false;
}

if(document.Form1.TXTPIN.value=="")
{
   alert("Please enter PinCode");
   document.Form1.TXTPIN.focus();
   return false;
}

if(document.Form1.TXTCOUNTRY.value=="")
{
   alert("Please enter country");
   document.Form1.TXTCOUNTRY.focus();
   return false;
}
if(document.Form1.TXTEMAIL.value=="")
{
   alert("Please enter email");
   document.Form1.TXTEMAIL.focus();
   return false;
}
if (echeck(document.Form1.TXTEMAIL.value)==false)
    {
		document.Form1.TXTEMAIL.value="";

              document.Form1.TXTEMAIL.focus();
		return false;
	}
return true;
}
var availableSelectList;
function echeck(str) {

		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("Invalid E-mail ID");
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		    alert("Invalid E-mail ID");
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		      alert("Invalid E-mail ID");
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		     alert("Invalid E-mail ID");
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    alert("Invalid E-mail ID");
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		     alert("Invalid E-mail ID");
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		    alert("Invalid E-mail ID");
		    return false
		 }

 		 return true
	}



function show()
{
   if(document.Form1.CMBCAT.value =="employee" || document.Form1.CMBCAT.value =="others")
   {
    document.Form1.TXTCOURSE.disabled=true;
    document.Form1.TXTROLL.disabled=true;
    
   }
 if(document.Form1.CMBCAT.value =="student")
   {
    document.Form1.TXTCOURSE.disabled=false;
    document.Form1.TXTROLL.disabled=false;
    
   }

}
</SCRIPT>


<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String lib_name,lib_id;
%>
<%
try{
    lib_name = (String)session.getAttribute("library_name");
    lib_id = (String)session.getAttribute("library_id");
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

</head>
<body>
     <%if(page.equals(true)){%>

<div id="wb_Form1" style="position:absolute;left:0px;top:0px;width:741px;height:497px;z-index:46">
    <form name="Form1" method="post" action="NewMember.do" onsubmit="return validate();">
<div id="wb_Text1" style="position:absolute;left:6px;top:5px;width:705px;height:19px;background-color:#FFFFE0;z-index:0;" align="left">
<font style="FONT-SIZE: 16px" face="Arial" color="#000000"><b>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
        &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
        &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
        &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<%=resource.getString("opac.newmemberentry.text")%> </b></font></div>
<div id="wb_Text2" style="position:absolute;left:10px;top:35px;width:82px;height:15px;z-index:1;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.libraryname")%></b></font></div>
<div id="wb_Text3" style="position:absolute;left:304px;top:32px;width:59px;height:15px;z-index:2;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.libraryid")%></b></font></div>
<input type="text"  style="position:absolute;left:93px;top:30px;width:207px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:3" name="TXTLIBNAME" value="<%=lib_name%>" disabled="true">
<input type="text" style="position:absolute;left:363px;top:31px;width:79px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:4" name="TXTLIBID" value="<%=lib_id%>" disabled="true">
<div id="wb_Text4" style="position:absolute;left:29px;top:57px;width:59px;height:15px;z-index:5;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.category")%></b></font></div>
<div style="position:absolute;left:93px;top:57px;width:112px;height:18px;border:1px #C0C0C0 solid;z-index:6">
<select name="CMBCAT" size="1" onblur="show()"  style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="employee">EMPLOYEE</option>
<option value="student">STUDENT</option>
<option value="others">OTHERS</option>
</select>
</div>
<div id="wb_Text5" style="position:absolute;left:20px;top:81px;width:68px;height:15px;z-index:7;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.firstname")%></b></font></div>
<input type="text" tabindex="4" style="position:absolute;left:93px;top:81px;width:155px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:8" name="TXTFNAME">
<div id="wb_Text6" style="position:absolute;left:253px;top:81px;width:79px;height:15px;z-index:9;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.middlename")%></b></font></div>
<input type="text" tabindex="5" style="position:absolute;left:333px;top:79px;width:134px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:10" name="TXTMNAME">
<div id="wb_Text7" style="position:absolute;left:473px;top:81px;width:69px;height:15px;z-index:11;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.lastname")%></b></font></div>
<input type="text" tabindex="6" style="position:absolute;left:545px;top:79px;width:144px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="TXTLNAME">
<div id="wb_Text8" style="position:absolute;left:20px;top:124px;width:68px;height:15px;z-index:13;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.address1")%></b></font></div>
<textarea name="TXTADD1" tabindex="7"  style="position:absolute;left:93px;top:110px;width:220px;height:47px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" rows="1" cols="23"></textarea>
<div id="wb_Text9" style="position:absolute;left:371px;top:124px;width:67px;height:15px;z-index:15;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.address2")%></b></font></div>
<textarea name="TXTADD2" tabindex="8"  style="position:absolute;left:444px;top:109px;width:246px;height:46px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:16" rows="1" cols="26"></textarea>
<div id="wb_Text10" style="position:absolute;left:55px;top:163px;width:36px;height:15px;z-index:17;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.city")%></b></font></div>
<input type="text" tabindex="9" style="position:absolute;left:93px;top:161px;width:132px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:18" name="TXTCITY">
<div id="wb_Text11" style="position:absolute;left:292px;top:164px;width:38px;height:15px;z-index:19;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.state")%></b></font></div>
<input type="text" tabindex="10" style="position:absolute;left:333px;top:161px;width:118px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:20" name="TXTSTATE">
<div id="wb_Text12" style="position:absolute;left:510px;top:163px;width:53px;height:15px;z-index:21;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.pincode")%></b></font></div>
<input type="text" tabindex="11" style="position:absolute;left:567px;top:160px;width:121px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:22" name="TXTPIN">
<div id="wb_Text13" style="position:absolute;left:35px;top:192px;width:52px;height:15px;z-index:23;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.country")%></b></font></div>
<input type="text" tabindex="12" style="position:absolute;left:93px;top:191px;width:133px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:24" name="TXTCOUNTRY">
<div id="wb_Text14" style="position:absolute;left:33px;top:222px;width:60px;height:15px;z-index:25;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.emailid")%></b></font></div>
<input type="text" tabindex="13" style="position:absolute;left:93px;top:220px;width:238px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:26" name="TXTEMAIL">

<div id="wb_Text15" style="position:absolute;left:408px;top:222px;width:31px;height:15px;z-index:27;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.fax")%></b></font></div>
<input type="text" tabindex="14" style="position:absolute;left:446px;top:219px;width:241px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:28" name="TXTFAX">
<div id="wb_Text16" style="position:absolute;left:12px;top:281px;width:82px;height:15px;z-index:29;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.facultyname")%></b></font></div>
<input type="text" tabindex="17" id="TXTFACULTY" style="position:absolute;left:93px;top:280px;width:211px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:30" name="TXTFACULTY">
<div id="wb_Text17" style="position:absolute;left:331px;top:280px;width:110px;height:15px;z-index:31;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.departmentname")%></b></font></div>
<input type="text" tabindex="18" style="position:absolute;left:447px;top:278px;width:239px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:32" name="TXTDEPT">
<div id="wb_Text18" style="position:absolute;left:60px;top:312px;width:27px;height:19px;z-index:33;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.newmemberentry.id")%></b></font><font style="font-size:16px" color="#FF0000" face="Arial"><b>*</b></font></div>
<input type="text" tabindex="19" style="position:absolute;left:93px;top:311px;width:239px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:34" name="TXTID">
<input type="text" tabindex="20" style="position:absolute;left:93px;top:341px;width:166px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:35" name="TXTROLL" >
<input type="submit" id="Button1" tabindex="23" value="<%=resource.getString("opac.newmemberentry.submit")%>" style="position:absolute;left:322px;top:431px;width:60px;height:25px;font-family:Arial;font-size:13px;z-index:36">
<input type="text" tabindex="15" style="position:absolute;left:93px;top:251px;width:172px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:37" name="TXTPH1">
<input type="text" tabindex="16" style="position:absolute;left:446px;top:249px;width:172px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:38" name="TXTPH2">
<div id="wb_Text20" style="position:absolute;left:36px;top:255px;width:51px;height:15px;z-index:39;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.phone1")%></b></font></div>
<div id="wb_Text21" style="position:absolute;left:388px;top:252px;width:50px;height:15px;z-index:40;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.phone2")%></b></font></div>
<div id="wb_Text22" style="position:absolute;left:5px;top:339px;width:93px;height:15px;z-index:41;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.facultyrollno")%></b></font></div>
<input type="text" tabindex="21" style="position:absolute;left:93px;top:370px;width:166px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:42" name="TXTCOURSE">
<div id="wb_Text23" style="position:absolute;left:46px;top:372px;width:48px;height:15px;z-index:43;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.course")%></b></font></div>
<div id="wb_Text19" style="position:absolute;left:405px;top:314px;width:250px;height:14px;z-index:44;" align="left">
&nbsp;</div>
<div id="wb_Text24" style="position:absolute;left:388px;top:323px;width:308px;height:78px;background-color:#FFFFE0;z-index:45;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b> </b></font><font style="font-size:15px" color="#CC3300" face="Arial"><b>* </b></font><font style="font-size:12px" color="#CC3300" face="Arial"><b> <u><%=resource.getString("opac.newmemberentry.note")%> </u><br>
<%=resource.getString("opac.newmemberentry.enterenrollment")%><br>
<%=resource.getString("opac.newmemberentry.enterenrollmentid")%><br>
<%=resource.getString("opac.newmemberentry.enteremailidincaseofothers")%></b></font></div>
</form>
</div>

     <%}else{%>

     <div id="wb_Form1" style="position:absolute;right:0px;top:0px;width:741px;height:497px;z-index:46">
<form name="Form1" method="post" action="NewMember.do">
<div id="wb_Text1" style="position:absolute;right:60px;top:5px;width:705px;height:19px;background-color:#FFFFE0;z-index:0;" align="right">
<font style="FONT-SIZE: 16px" face="Arial" color="#000000"><b>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
        &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
        &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
        &nbsp;<%=resource.getString("opac.newmemberentry.text")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font></div>
<div id="wb_Text2" style="position:absolute;right:10px;top:35px;width:82px;height:15px;z-index:1;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.libraryname")%></b></font></div>
<div id="wb_Text3" style="position:absolute;right:304px;top:32px;width:59px;height:15px;z-index:2;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.libraryid")%></b></font></div>
<input type="text"  style="position:absolute;right:93px;top:30px;width:207px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:3" name="TXTLIBNAME">
<input type="text" style="position:absolute;right:363px;top:31px;width:79px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:4" name="TXTLIBID">
<div id="wb_Text4" style="position:absolute;right:29px;top:57px;width:59px;height:15px;z-index:5;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.category")%></b></font></div>
<div style="position:absolute;right:93px;top:57px;width:112px;height:18px;border:1px #C0C0C0 solid;z-index:6">
<select name="CMBCAT" size="1" onblur="show()"  style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="employee">EMPLOYEE</option>
<option value="student">STUDENT</option>
<option value="others">OTHERS</option>
</select>
</div>
<div id="wb_Text5" style="position:absolute;right:20px;top:81px;width:68px;height:15px;z-index:7;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.firstname")%></b></font></div>
<input type="text" tabindex="4" style="position:absolute;right:93px;top:81px;width:155px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:8" name="TXTFNAME">
<div id="wb_Text6" style="position:absolute;right:253px;top:81px;width:79px;height:15px;z-index:9;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.middlename")%></b></font></div>
<input type="text" tabindex="5" style="position:absolute;right:333px;top:79px;width:134px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:10" name="TXTMNAME">
<div id="wb_Text7" style="position:absolute;right:473px;top:81px;width:69px;height:15px;z-index:11;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.lastname")%></b></font></div>
<input type="text" tabindex="6" style="position:absolute;right:545px;top:79px;width:144px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="TXTLNAME">
<div id="wb_Text8" style="position:absolute;right:20px;top:124px;width:68px;height:15px;z-index:13;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.address1")%></b></font></div>
<textarea name="TXTADD1" tabindex="7"  style="position:absolute;right:93px;top:110px;width:220px;height:47px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" rows="1" cols="23"></textarea>
<div id="wb_Text9" style="position:absolute;right:371px;top:124px;width:67px;height:15px;z-index:15;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.address2")%></b></font></div>
<textarea name="TXTADD2" tabindex="8"  style="position:absolute;right:444px;top:109px;width:246px;height:46px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:16" rows="1" cols="26"></textarea>
<div id="wb_Text10" style="position:absolute;right:55px;top:163px;width:36px;height:15px;z-index:17;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.city")%></b></font></div>
<input type="text" tabindex="9" style="position:absolute;right:93px;top:161px;width:132px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:18" name="TXTCITY">
<div id="wb_Text11" style="position:absolute;right:292px;top:164px;width:38px;height:15px;z-index:19;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.state")%></b></font></div>
<input type="text" tabindex="10" style="position:absolute;right:333px;top:161px;width:118px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:20" name="TXTSTATE">
<div id="wb_Text12" style="position:absolute;right:510px;top:163px;width:53px;height:15px;z-index:21;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.pincode")%></b></font></div>
<input type="text" tabindex="11" style="position:absolute;right:567px;top:160px;width:121px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:22" name="TXTPIN">
<div id="wb_Text13" style="position:absolute;right:35px;top:192px;width:52px;height:15px;z-index:23;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.country")%></b></font></div>
<input type="text" tabindex="12" style="position:absolute;right:93px;top:191px;width:133px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:24" name="TXTCOUNTRY">
<div id="wb_Text14" style="position:absolute;right:33px;top:222px;width:60px;height:15px;z-index:25;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.emailid")%></b></font></div>
<input type="text" tabindex="13" style="position:absolute;right:93px;top:220px;width:238px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:26" name="TXTEMAIL">
<div id="wb_Text15" style="position:absolute;right:408px;top:222px;width:31px;height:15px;z-index:27;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.fax")%></b></font></div>
<input type="text" tabindex="14" style="position:absolute;right:446px;top:219px;width:241px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:28" name="TXTFAX">
<div id="wb_Text16" style="position:absolute;right:12px;top:281px;width:82px;height:15px;z-index:29;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.facultyname")%></b></font></div>
<input type="text" tabindex="17" id="TXTFACULTY" style="position:absolute;right:93px;top:280px;width:211px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:30" name="TXTFACULTY">
<div id="wb_Text17" style="position:absolute;right:331px;top:280px;width:110px;height:15px;z-index:31;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.departmentname")%></b></font></div>
<input type="text" tabindex="18" style="position:absolute;right:447px;top:278px;width:239px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:32" name="TXTDEPT">
<div id="wb_Text18" style="position:absolute;right:60px;top:312px;width:27px;height:19px;z-index:33;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.newmemberentry.id")%></b></font><font style="font-size:16px" color="#FF0000" face="Arial"><b>*</b></font></div>
<input type="text" tabindex="19" style="position:absolute;right:93px;top:311px;width:239px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:34" name="TXTID">
<input type="text" tabindex="20" style="position:absolute;right:93px;top:341px;width:166px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:35" name="TXTROLL" >
<input type="submit" id="Button1" tabindex="23" value="<%=resource.getString("opac.newmemberentry.submit")%>" style="position:absolute;right:322px;top:431px;width:60px;height:25px;font-family:Arial;font-size:13px;z-index:36">
<input type="text" tabindex="15" style="position:absolute;right:93px;top:251px;width:172px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:37" name="TXTPH1">
<input type="text" tabindex="16" style="position:absolute;right:446px;top:249px;width:172px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:38" name="TXTPH2">
<div id="wb_Text20" style="position:absolute;right:36px;top:255px;width:51px;height:15px;z-index:39;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.phone1")%></b></font></div>
<div id="wb_Text21" style="position:absolute;right:388px;top:252px;width:50px;height:15px;z-index:40;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.phone2")%></b></font></div>
<div id="wb_Text22" style="position:absolute;right:5px;top:339px;width:93px;height:15px;z-index:41;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.facultyrollno")%></b></font></div>
<input type="text" tabindex="21" style="position:absolute;right:93px;top:370px;width:166px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:42" name="TXTCOURSE">
<div id="wb_Text23" style="position:absolute;right:46px;top:372px;width:48px;height:15px;z-index:43;" align="right">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.newmemberentry.course")%></b></font></div>
<div id="wb_Text19" style="position:absolute;right:405px;top:314px;width:250px;height:14px;z-index:44;" align="right">
&nbsp;</div>
<div id="wb_Text24" style="position:absolute;right:388px;top:323px;width:308px;height:78px;background-color:#FFFFE0;z-index:45;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b> </b></font><font style="font-size:15px" color="#CC3300" face="Arial"><b>* </b></font><font style="font-size:12px" color="#CC3300" face="Arial"><b> <u><%=resource.getString("opac.newmemberentry.note")%> </u><br>
<%=resource.getString("opac.newmemberentry.enterenrollment")%><br>
<%=resource.getString("opac.newmemberentry.enterenrollmentid")%><br>
<%=resource.getString("opac.newmemberentry.enteremailidincaseofothers")%></b></font></div>
</form>
</div>





     <%}%>

</body>
</html>