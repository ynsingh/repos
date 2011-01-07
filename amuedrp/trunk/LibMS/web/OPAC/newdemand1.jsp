<%--
    Document   : NewDemand1.jsp
    Created on : Jun 11, 2010, 1:15:37 PM
    Author     : Mayank Saxena
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorPage.jsp" language="java"%>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>New Demand...</title>
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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>


</head>
<body>
<%! String id,name; %>
<%//Retrieving the values of NewMember form in variables.
id=(String)session.getAttribute("id");
name=(String)session.getAttribute("memname");
%>

 <%if(page.equals(true)){%>
      
<div id="wb_Form1" style="position:absolute;left:0px;top:0px;width:591px;height:529px;z-index:24">
<form name="Form1" method="post" action="NewDemand.do" id="Form1">
<div id="wb_Text3" style="position:absolute;left:11px;top:72px;width:80px;height:15px;z-index:0;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.libraryname")%></b></font></div>
<div id="wb_Text4" style="position:absolute;left:64px;top:108px;width:29px;height:15px;z-index:1;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.title")%></b></font></div>
<div id="wb_Text5" style="position:absolute;left:50px;top:142px;width:48px;height:15px;z-index:2;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.author")%></b></font></div>
<div id="wb_Text6" style="position:absolute;left:34px;top:182px;width:67px;height:15px;z-index:3;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.publisher")%></b></font></div>
<div id="wb_Text7" style="position:absolute;left:28px;top:219px;width:72px;height:30px;z-index:4;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.publishingyear")%></b></font></div>
<div id="wb_Text8" style="position:absolute;left:31px;top:262px;width:70px;height:15px;z-index:5;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.isbn")%></b></font></div>
<div id="wb_Text9" style="position:absolute;left:14px;top:294px;width:92px;height:15px;z-index:6;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.noofcopies")%></b></font></div>
<div id="wb_Text10" style="position:absolute;left:46px;top:329px;width:57px;height:15px;z-index:7;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.volume")%></b></font></div>
<div id="wb_Text11" style="position:absolute;left:41px;top:420px;width:64px;height:15px;z-index:8;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.remark")%></b></font></div>
<div style="position:absolute;left:449px;top:105px;width:110px;height:18px;border:1px #C0C0C0 solid;z-index:9">
<select name="CMBCAT" size="1" id="Combobox1" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="books">BOOKS</option>
<option value="journals">JOURNALS</option>
<option value="others">OTHERS</option>
</select>
</div>
<div id="wb_Text12" style="position:absolute;left:387px;top:107px;width:65px;height:15px;z-index:10;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.category")%></b></font></div>
<input type="text" id="Editbox1" style="position:absolute;left:102px;top:69px;width:198px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="TXTLIBNAME" >
<input type="text" id="Editbox2" style="position:absolute;left:102px;top:105px;width:193px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="TXTTITLE" >
<input type="text" id="Editbox3" style="position:absolute;left:102px;top:139px;width:281px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:13" name="TXTAUTHOR" >
<input type="text" id="Editbox4" style="position:absolute;left:102px;top:179px;width:246px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" name="TXTPUB" >
<input type="text" id="Editbox5" style="position:absolute;left:102px;top:222px;width:101px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:15" name="TXTPUBYR" >
<input type="text" id="Editbox6" style="position:absolute;left:102px;top:259px;width:181px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:16" name="TXTISBN" >
<input type="text" id="Editbox7" style="position:absolute;left:102px;top:291px;width:55px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:17" name="TXTCOPY" >
<input type="text" id="Editbox8" style="position:absolute;left:102px;top:326px;width:107px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:18" name="TXTVOL" >
<input type="text" id="Editbox9" style="position:absolute;left:103px;top:359px;width:107px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:19" name="TXTEDITION" >
<input type="hidden" id="id" value="<%=id%>"style="position:absolute;left:102px;top:69px;width:198px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="id" >
<textarea name="TXTREMARK" id="TextArea1" style="position:absolute;left:103px;top:398px;width:230px;height:69px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:20" rows="3" cols="24"></textarea>
<input type="submit" id="Button1" name="" value="<%=resource.getString("opac.myaccount.newdemand.send")%>" style="position:absolute;left:371px;top:431px;width:66px;height:25px;font-family:Arial;font-size:13px;z-index:21">
<div id="wb_Text13" style="position:absolute;left:52px;top:364px;width:46px;height:15px;z-index:22;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.edition")%></b></font></div>
<div id="wb_Text2" style="position:absolute;left:3px;top:4px;width:583px;height:19px;background-color:#FFFFE0;z-index:23;" align="left">
<font style="font-size:16px" color="#c0003b" face="Arial"><b>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<%=resource.getString("opac.myaccount.newdemand.newdemandtext")%> </b></font>
</div>
<div id="wb_Text2" style="position:absolute;left:3px;top:33px;width:583px;height:19px;background-color:#FFFFE0;z-index:23;" align="left">
<font style="font-size:12px" color="#c0003b" face="Arial"><b> &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<%=resource.getString("opac.myaccount.newdemand.welcome")%>&nbsp;  <%=name%>&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;ID&nbsp;:&nbsp;<%=id%> </b></font>
</div>
</form>
</div>

 <%}else{%>

 <div id="wb_Form1" style="position:absolute;right:0px;top:0px;width:591px;height:529px;z-index:24">
<form name="Form1" method="post" action="NewDemand.do" id="Form1">
<div id="wb_Text3" style="position:absolute;right:11px;top:72px;width:80px;height:15px;z-index:0;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.libraryname")%></b></font></div>
<div id="wb_Text4" style="position:absolute;right:64px;top:108px;width:29px;height:15px;z-index:1;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.title")%></b></font></div>
<div id="wb_Text5" style="position:absolute;right:50px;top:142px;width:48px;height:15px;z-index:2;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.author")%></b></font></div>
<div id="wb_Text6" style="position:absolute;right:34px;top:182px;width:67px;height:15px;z-index:3;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.publisher")%></b></font></div>
<div id="wb_Text7" style="position:absolute;right:28px;top:219px;width:72px;height:30px;z-index:4;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.publishingyear")%></b></font></div>
<div id="wb_Text8" style="position:absolute;right:31px;top:262px;width:70px;height:15px;z-index:5;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.isbn")%></b></font></div>
<div id="wb_Text9" style="position:absolute;right:14px;top:294px;width:92px;height:15px;z-index:6;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.noofcopies")%></b></font></div>
<div id="wb_Text10" style="position:absolute;right:46px;top:329px;width:57px;height:15px;z-index:7;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.volume")%></b></font></div>
<div id="wb_Text11" style="position:absolute;right:41px;top:420px;width:64px;height:15px;z-index:8;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.remark")%></b></font></div>
<div style="position:absolute;right:490px;top:105px;width:110px;height:18px;border:1px #C0C0C0 solid;z-index:9">
<select name="CMBCAT" size="1" id="Combobox1" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="books">BOOKS</option>
<option value="journals">JOURNALS</option>
<option value="others">OTHERS</option>
</select>
</div>
<div id="wb_Text12" style="position:absolute;right:387px;top:107px;width:65px;height:15px;z-index:10;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.category")%></b></font></div>
<input type="text" id="Editbox1" style="position:absolute;right:102px;top:69px;width:198px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="TXTLIBNAME" >
<input type="text" id="Editbox2" style="position:absolute;right:102px;top:105px;width:193px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="TXTTITLE" >
<input type="text" id="Editbox3" style="position:absolute;right:102px;top:139px;width:281px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:13" name="TXTAUTHOR" >
<input type="text" id="Editbox4" style="position:absolute;right:102px;top:179px;width:246px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" name="TXTPUB" >
<input type="text" id="Editbox5" style="position:absolute;right:102px;top:222px;width:101px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:15" name="TXTPUBYR" >
<input type="text" id="Editbox6" style="position:absolute;right:102px;top:259px;width:181px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:16" name="TXTISBN" >
<input type="text" id="Editbox7" style="position:absolute;right:102px;top:291px;width:55px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:17" name="TXTCOPY" >
<input type="text" id="Editbox8" style="position:absolute;right:102px;top:326px;width:107px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:18" name="TXTVOL" >
<input type="text" id="Editbox9" style="position:absolute;right:103px;top:359px;width:107px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:19" name="TXTEDITION" >
<input type="hidden" id="id" value="<%=id%>"style="position:absolute;right:102px;top:69px;width:198px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="id" >
<textarea name="TXTREMARK" id="TextArea1" style="position:absolute;right:103px;top:398px;width:230px;height:69px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:20" rows="3" cols="24"></textarea>
<input type="submit" id="Button1" name="" value="<%=resource.getString("opac.myaccount.newdemand.send")%>" style="position:absolute;right:371px;top:431px;width:66px;height:25px;font-family:Arial;font-size:13px;z-index:21">
<div id="wb_Text13" style="position:absolute;right:52px;top:364px;width:46px;height:15px;z-index:22;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.newdemand.edition")%></b></font></div>
<div id="wb_Text2" style="position:absolute;right:3px;top:4px;width:583px;height:19px;background-color:#FFFFE0;z-index:23;" align="right">
<font style="font-size:16px" color="#c0003b" face="Arial"><b>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; <%=resource.getString("opac.myaccount.newdemand.newdemandtext")%> </b></font>
</div>
<div id="wb_Text2" style="position:absolute;right:3px;top:33px;width:583px;height:19px;background-color:#FFFFE0;z-index:23;" align="right">
<font style="font-size:12px" color="#c0003b" face="Arial"><b> &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<%=resource.getString("opac.myaccount.newdemand.welcome")%>&nbsp;  <%=name%>&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;ID&nbsp;:&nbsp;<%=id%> </b></font>
</div>
</form>
</div>



  <% } %>
</body>
</html>