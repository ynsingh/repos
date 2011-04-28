<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>

<%
String library_id=(String)session.getAttribute("library_id");
String book_type=(String)request.getAttribute("book_type");
String emptype_id=(String)request.getAttribute("emptype_id");
String emptype_name=(String)request.getAttribute("emptype_name");
String subemptype_name=(String)request.getAttribute("subemptype_name");
String subemptype_id=(String)request.getAttribute("subemptype_id");
String book_typefullname=(String)request.getAttribute("book_typefullname");



%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Book Category </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">


function check1()
{
    
     if(document.getElementById('permitday').value=="")
    {
        alert("Enter Issue Days Limit");


        document.getElementById('permitday').focus();

        return false;
    }
     if(document.getElementById('fineperdayRs').value=="")
    {
        alert("Enter Fine in Rupee or 0 for None");

        document.getElementById('fineperdayRs').focus();

        return false;
    }
     if(document.getElementById('fineperdayPs').value=="")
    {
        alert("Enter Fine in Paise or 0 for None");

        document.getElementById('fineperdayPs').focus();

        return false;
    }
return true;
  }

function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }




  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_book.jsp";
      return false;
  }



    </script>
</head>
<body>

    <html:form method="post" action="/addbook" onsubmit="return check1()">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Configure Fine Detail</td></tr>
                <tr><td valign="top" align="center"> <br/>
                        <table cellspacing="10px" width="80%">
                            <tr><td  colspan="2">Member Type </td>
                                <td align="center"> <html:hidden  styleId="emptype" property="emptype_id" value="<%=emptype_id%>"/>
                                    <html:text  styleId="emptype" readonly="true" property="emptype_id" value="<%=emptype_name%>"/>
                                </td>
                            </tr>
                            <tr><td colspan="2">SubMember Type </td><td width="150px" align="center"><html:hidden property="sub_emptype_id" name="BookCategoryDecideActionForm" styleId="subemptype" value="<%=subemptype_id%>" />
                                <html:text property="sub_emptype_id" name="BookCategoryDecideActionForm" styleId="subemptype_name" value="<%=subemptype_name%>"  readonly="true" />

                                </td>
                           
                            <tr><td   colspan="2">Document Category

                        </td>
                        <td align="left"> <html:hidden styleId="book_type" property="book_type" value="<%=book_type%>"/>
                        <html:text styleId="book_type" property="book_type" value="<%=book_typefullname%>"  readonly="true"/>

                        </td>
                    </tr>
                      

                      
                       <html:hidden styleId="fullname" name="BookCategoryDecideActionForm"  property="full_name"/>
                    
                    <tr><td   colspan="2">Number of Days Permitted<br><br>

                        </td>
                        <td align="left"><html:text styleId="permitday" value="" onkeypress="return isNumberKey(event)" property="permitday"/><br>
                            <font size="-2" color="blue">(Only numberic value permitted)</font></td>
                    </tr>
                    <tr><td   colspan="3" height="5px"> </td>
                    </tr>

                  <tr><td   colspan="2">Fine Per Day

                        </td>
                        <td align="left"><font size="-1"> Rs:<html:text styleId="fineperdayRs"  onkeypress="return isNumberKey(event)" property="fineRs" value="" size="2"/>Paise:<input type="text" id="fineperdayPs" onkeypress="return isNumberKey(event)" name="finePs" size="2" value=""/>
                            </font>  </td>
                    </tr>

                     <tr><td  class="txt2" colspan="3" align="center"><br/>
                             <html:submit  value="Submit"/>
                             <input type="button" onClick="quit()"  value="Back"/><br/>
                        </td>

                    </tr>


                </table>


    <input type="hidden" name="library_id" value="<%=library_id%>">









</td></tr></table>
        </div>

</html:form>

</body>

    

</html>
