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


<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg");
String back=(String)session.getAttribute("page");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Document Category </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">


function check1()
{
    if(document.getElementById('location_id').value=="")
    {
        alert("Enter Location Id...");

        document.getElementById('location_id').focus();

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

    <html:form method="post" onsubmit="return check1()" action="/document">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Document Category</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">

                    <tr><td rowspan="5" class="txt2">Enter Document Category ID<br><br>
                        <input type="text" id="location_id" name="document_category_id" value=""/>
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
          if (msg2!=null && back!=null)
          {
              
        %>

        <p class="mess">  <%=msg2%></p>
        <script>
          
          var check = confirm("Do You Want To Add More Document Category..");
            if(check==false){
                <%if(session.getAttribute("location")==null&& session.getAttribute("member")==null&& session.getAttribute("submember")==null)
                        {session.removeAttribute("document");%>window.location="<%=request.getContextPath()%>/admin/main.jsp";<%}else{session.removeAttribute("document");%>
                window.location="<%=request.getContextPath()%>/admin/setup.jsp";<%}%>}</script>
           
       

        <%
        }else  if (msg2!=null)
          {
        %>

        <p class="mess">  <%=msg2%></p>


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
                 <tr><td align="justify"><font color="blue" size="-1"><b>Example:</b> txt for Text-Book, ref for Reference-Book, gen for General-Reading & so on, if DocumentCategory exists.</font></td></tr>

    </table>
        </div>

</html:form>

</body>

     
</html>
