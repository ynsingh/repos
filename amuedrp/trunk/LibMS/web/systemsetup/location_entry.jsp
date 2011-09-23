<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>


<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    // boolean read=true;
  boolean button_visibility=true;
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
<%! boolean read=false;%>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String button=(String)request.getAttribute("button");
//request.setAttribute("back", request.getAttribute("back"));

if (button.equals("View")||button.equals("Delete"))
read=true;

else
    read=false;
String msg1=(String) request.getAttribute("msg1");
%>
<%! String button1;%>
<%

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
<script type="text/javascript">

function Update()
{
    var buttonvalue="Update";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
function Delete()
{
    var buttonvalue="Delete";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
function Submit()
{
    var buttonvalue="Submit";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

function check1()
{
    if(document.getElementById('location_name').value=="")
    {
        alert("<%=resource.getString("systemsetup.location_entry.enterlocname")%>");

        document.getElementById('location_name').focus();

        return false;
    }

  }
  function confirm1()
{
document.getElementById('button').value="<%=button%>" ;
    var option=document.getElementById('button').value;
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
function send()
{
    window.location="<%=request.getContextPath()%>/systemsetup/add_location.jsp";
    return false;
}
function loadHelp()
    {
        window.status="Press F1 for Help";

    }

</script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
         <link rel="stylesheet" href="/LibMS-Struts/css/formstyle.css"/>
    </head>
   <body onload="loadHelp()" >


   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
   <html:form method="post" action="/add_location"  onsubmit="return check1()" >
       <table dir="<%=rtl%>" border="1" class="table" align="center">
                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b><%=resource.getString("systemsetup.location_entry.locdetailentry")%></b></td></tr>
                <tr><td dir="<%=rtl%>">
                        <table dir="<%=rtl%>" width="400px" border="0" cellspacing="4" cellpadding="1" align="<%=align%>">
                        <tr>
                        <html:hidden property="library_id" name="AcqBiblioActionForm" value="<%=library_id%>" />
                        <html:hidden property="sub_library_id" name="AcqBiblioActionForm" value="<%=sub_library_id%>" /><td></td>
                        </tr>
<tr><td dir="<%=rtl%>" colspan="5" height="10px"></td>
</tr>
<tr><td dir="<%=rtl%>" colspan="5" height="10px"></td>
</tr>
<tr>
    <td dir="<%=rtl%>" align="<%=align%>" class="txtStyle"><strong><%=resource.getString("systemsetup.manage_notice.locationid")%></strong></td>
    <td dir="<%=rtl%>"><html:text readonly="true" property="location_id" name="LocationActionForm" styleClass="textBoxWidth" /></td>
</tr>
  <tr><td dir="<%=rtl%>" colspan="5" height="5px"></td>
</tr>
<tr>
    <td dir="<%=rtl%>" width="150" align="<%=align%>" class="txtStyle"><strong><%=resource.getString("systemsetup.location_entry.locname")%><a class="star">*</a></strong> </td>
    <td dir="<%=rtl%>"><html:text readonly="<%=read%>"  onfocus="statwords('Please Enter Valid Location Name ');" onblur="return loadHelp();" property="location_name" name="LocationActionForm" styleClass="textBoxWidth" styleId="location_name" />
    </td>
  </tr>
  <tr><td dir="<%=rtl%>" colspan="5" height="5px"></td>
</tr>
  <tr>
    <td dir="<%=rtl%>" align="<%=align%>" class="txtStyle"><strong><%=resource.getString("systemsetup.location_entry.locdesc")%></strong></td>
  <td dir="<%=rtl%>"><html:textarea readonly="<%=read%>" property="location_description" name="LocationActionForm" styleClass="textBoxWidth" onfocus="statwords('Please Enter Location Description ');" onblur="return loadHelp();"
 />
  </td>
  </tr>
<tr><td colspan="5" height="5px"></td>
</tr>


<tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>

<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td dir="<%=rtl%>" align="center" colspan="5">
    <%if(button.equals("Update")){%>
    <input id="button1"  name="button1" type="submit" onclick="return Update();" value="<%=button1%>"/>
    &nbsp;&nbsp;&nbsp;<input name="button1" type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return send()" />
    <%}else if(button.equals("Delete")){%>
    <input id="button1"  name="button1" type="submit" onClick="return confirm1()" value="<%=button1%>"  />
    &nbsp;&nbsp;&nbsp;<input name="button1" type="button" onclick="return send()"  value="<%=resource.getString("circulation.cir_member_reg.back")%>" />
   <%}else if(button.equals("Add")){%>
    <input id="button1"  name="button1" type="submit" value="<%=resource.getString("circulation.cir_newmember.submit")%>" onclick="return Submit();" />
    &nbsp;&nbsp;&nbsp;<input name="button1" type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return send()" />
    <%}else if(button.equals("View")){%>
    <input name="button1" type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return send()" />
    <%}%>
	</td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
</table>
</td></tr> </table>
         <input type="hidden" id="button" name="button" />

  </html:form>
       </div>
    </body>
</html>
