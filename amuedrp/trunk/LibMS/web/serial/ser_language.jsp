<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>

<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");
String msg=(String)request.getAttribute("msg");

//session.setAttribute("backlocation", back);

//String back1=(String)session.getAttribute("backlocation");
%>

 <script language="javascript" type="text/javascript">


function check1()
{
    if(document.getElementById('lan_id').value=="")
    {
        alert("Enter Serial Id...");

        document.getElementById('lan_id').focus();

        return false;
    }

  }

  function quit()
  {
     
   
         window.location="<%=request.getContextPath()%>/admin/main.jsp";
       
      return true;
  }



    </script>

<body style="margin:0px 0px 0px 0px">

    <html:form method="post" onsubmit="return check1()" action="/ser_lang">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

<table class="table" border="1" width="400px" height="180px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Serial Language</td></tr>
                <tr><td valign="top" align="center"> 
                <table cellspacing="10px">

                    <tr><td rowspan="5" class="txt2" valign="center">Enter Serial Language ID<br><br>
                        <input type="text" id="lan_id" name="lan_id" value=""/>
                        </td><td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Add" /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button4" name="button" value="Delete" class="btn" /></td></tr>
                           <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td></tr>

 


                </table>


    <input type="hidden" name="library_id" value="<%=library_id%>">









</td></tr>
                <tr><td class="mess">

        <%
          if (msg!=null)
          {
              
        %>

        <p class="mess">  <%=msg%></p>
       
        <%
        }
        %>
         <%if (msg1!=null)
          {
        %>


        <p class="err">  <%=msg1%></p>

        <%
        }
        %>

                    </td></tr>
          <tr><td align="justify" colspan="2"><font color="blue" size="-1"><b>Example:</b> en for English, hi for Hindi , ur for Urdu & so on</font></td></tr>


    </table>
      </div>

</html:form>

</body>

      

