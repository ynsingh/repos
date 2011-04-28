<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="/admin/header.jsp" flush="true" />

<%
String  date="" ;

  Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=year+"-"+ (month+1) +"-"+day;
String library_id=(String)session.getAttribute("library_id");
String notice_id=(String)request.getAttribute("notice_id");

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
    if(document.getElementById('subject_type').value=="")
    {
        alert("Enter Subject");

        document.getElementById('subject_type').focus();

        return false;
    }
     if(document.getElementById('detail_type').value=="")
    {
        alert("Enter Detail");


        document.getElementById('detail_type').focus();

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

      window.location="<%=request.getContextPath()%>/systemsetup/manage_notices.jsp";
      return false;
  }



    </script>
</head>
<body>

    <html:form method="post" action="/notice" onsubmit="return check1();">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Add Notices</td></tr>
                <tr><td valign="top" align="center"> <br/>
                        <table cellspacing="10px">
                             <tr><td  class="txt2" colspan="2">Date

                        </td>
                        <td align="left"><html:text   styleId="date_type" property="date" readonly="true" value="<%=date%>"/><br>
                       </td>
                    </tr>
                 
                            <tr><td width="150px" class="txt2" colspan="2">Notice Id </td>
                                <td width="150px" align="left"> <html:text  styleId="noticetype" property="notice_id" value="<%=notice_id%>" readonly="true"/>
                                </td>
                            </tr>
                          
                           
                            <tr><td  class="txt2" colspan="2">Enter Subject<span class="star">*</span>

                        </td>
                        <td align="left"> <html:text styleId="subject_type" property="subject" value=""  /></td>
                    </tr>
                       <tr><td  class="txt2" colspan="2">Enter Detail<span class="star">*</span>

                        </td>
                        <td align="left"> <html:textarea  styleId="detail_type"  property="detail"/></td>
                    </tr>
                   

                    <tr><td  class="txt2" colspan="3" align="center">
                          <br/>   <html:submit  value="Submit"/>
                             <input type="button" onClick="quit()"  value="Back"/><br/><br/>
                        </td>

                    </tr>


                </table>


    <input type="hidden" name="library_id" value="<%=library_id%>">









</td></tr></table>
        </div>

</html:form>

</body>
 
    

</html>
