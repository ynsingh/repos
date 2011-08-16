<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID 
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@page  import="java.util.List" %>
 <jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%

String msg=(String)request.getAttribute("msg");
List list1=(List)session.getAttribute("list1");
String back=(String)session.getAttribute("page");
String msg2=(String)request.getAttribute("msg1");

System.out.println(msg2+"...........");
%>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Faculty </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">

 function Add()
{
    var buttonvalue="Add";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

function View()
{
    var buttonvalue="View";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
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

function check1()
{
     if(document.getElementById('emptype_id').value=="Select")
    {
        alert("<%=resource.getString("systemsetup.managesubmember.selectmemtype")%>");

        document.getElementById('emptype_id').focus();

        return false;
    }

    if(document.getElementById('sub_emptype_id').value=="")
    {
        alert("<%=resource.getString("systemsetup.managesubmember.entersubmemid")%>...");

        document.getElementById('sub_emptype_id').focus();

        return false;
    }

  }





  function quit()
  {

      <%if(back!=null){%>
         window.location="<%=request.getContextPath()%>/admin/setup.jsp";
        <%}else{%>
      window.location="<%=request.getContextPath()%>/admin/main.jsp";
     <%}%>
      return true;
  }



    </script>
</head>
<body>
 
    <html:form method="post" onsubmit="return check1()" action="/submemberRegistration">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" dir="<%=rtl%>"  class="table" width="500px" height="200px" align="center">

  
        <tr><td dir="<%=rtl%>" align="center" colspan="2" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_submember.managesubmemtype")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="middle" align="center"> <br/>
                <table dir="<%=rtl%>" cellspacing="10px">

                    <tr><td dir="<%=rtl%>" align="<%=align%>" width="150px">&nbsp;<%=resource.getString("systemsetup.member_view_update.memname")%> </td><td dir="<%=rtl%>" align="<%=align%>">

                            <html:select   property="emptype_id" styleId="emptype_id" value="Select" >
                          <html:option value="Select">Select</html:option>
                          <html:options collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>

                     </html:select>


               </td></tr>
                    <tr><td dir="<%=rtl%>" class="txt2"><%=resource.getString("systemsetup.manage_submember.submemtypeid")%></td><td>
                        <input type="text" id="sub_emptype_id" name="sub_emptype_id" value=""/></td>
                    </tr>

                </table>
                    </td><td>
                        <table dir="<%=rtl%>">

                    
                            <tr><td dir="<%=rtl%>" width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button1" value="<%=resource.getString("systemsetup.manage_notice.add")%>" onclick="return Add();"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button1" value="<%=resource.getString("circulation.cir_member_reg.update")%>" onclick="return Update();"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button3" name="button1" value="<%=resource.getString("circulation.cir_member_reg.view")%>" onclick="return View();"  class="btn"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button4" name="button1" value="<%=resource.getString("circulation.cir_member_reg.delete")%>" onclick="return Delete();" class="btn" /></td></tr>
                         <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="Button5" name="button1" value="<%=resource.getString("circulation.cir_member_reg.back")%>" class="btn" onclick="return quit()"/></td></tr>
 

                </table>
       

    
   <input type="hidden" id="button" name="button" />








</td></tr>
                <tr><td class="mess">

                                      <%
          if (msg!=null && back!=null)
          {
              
        %>

        <p class="mess" dir="<%=rtl%>" align="<%=align%>">  <%=msg%></p>
        <script>
            var check = confirm("<%=resource.getString("systemsetup.manage_member.doyouwanttoaddmoresubmem")%>");
            if(check==false){
                <%if(session.getAttribute("location")==null&& session.getAttribute("document")==null&& session.getAttribute("member")==null)
                        {session.removeAttribute("submember");%>window.location="<%=request.getContextPath()%>/admin/main.jsp";<%}else{session.removeAttribute("submember");%>
                window.location="<%=request.getContextPath()%>/admin/setup.jsp";<%}%>}</script>

        <%
        }else if (msg!=null)
          {
        %>

        <p class="mess">  <%=msg%></p>


        <%
        }
        %>
         <%if (msg2!=null)
          {
        %>


        <p class="err" dir="<%=rtl%>" align="<%=align%>">  <%=msg2%></p>

        <%
        }
        %>

                    </td></tr>
                <tr><td align="justify" colspan="2"><font color="blue" size="-1"><b><%=resource.getString("systemsetup.manage_notice.example")%>:</b> ug <%=resource.getString("systemsetup.manage_sumember.forug")%>, pg <%=resource.getString("systemsetup.manage_sumember.forpg")%>, prof <%=resource.getString("systemsetup.manage_sumember.forprofesor")%>, c <%=resource.getString("systemsetup.manage_sumember.forclerk")%>, <%=resource.getString("systemsetup.manage_sumember.ifsubmemtypeexist")%>.</font></td></tr>

    </table>
        </div>
  
</html:form>

</body>

     
</html>
