<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.*,java.util.*,com.myapp.struts.hbm.AcqBudget"%>

 <jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");
String msg=(String)request.getAttribute("msg");

//session.setAttribute("backlocation", back);

//String back1=(String)session.getAttribute("backlocation");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Staff </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">
<%
String date=DateCalculation.now();
String year=date.substring(0,4);
String year1=year.substring(2, 4);
int year2=Integer.parseInt(year1);
year2=year2+1;
year=year;
%>

function check1()
{
     var keyValue = document.getElementById('budgethead_id').options[document.getElementById('budgethead_id').selectedIndex].value;
   if (keyValue=="Select")
    {

        alert("Select Budget Head");

        document.getElementById('budgethead_id').focus();

        return false;
    }
  
    return true;
  }

  function quit()
  {


         window.location="<%=request.getContextPath()%>/admin/main.jsp";

      return true;
  }



    </script>
</head>
<body>

    <html:form method="post" onsubmit="return check1()" action="/budget1">

<div
   style="  top:200px;
   left:350px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table>
        <tr><td>
             
                 <table border="1" class="table" width="400px" height="200px" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Budget Allocation</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">
                    <tr><td rowspan="5" class="txt2">Select Budget Head<br><br>
                            <html:select property="budgethead_id" styleId="budgethead_id" name="AcqBudgetActionForm" value="Select" styleClass="textBoxWidth" >
                           <html:option  value="Select">Select</html:option>


      <html:options collection="budgets" name="AcqBudget" labelProperty="budgetheadName" property="id.budgetheadId"/>
    
    </html:select>

                            <br> <strong>Financial Year:</strong>
                            <br><html:text styleId="fy" value="<%=year%>" readonly="true"  property="financial_yr" name="AcqBudgetActionForm" styleClass="textBoxWidth"/>
                        </td>

                        <td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Add" /></td></tr>
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
                


    </table>

            </td><td>
              
            </td></tr>
    </table>

   
        </div>

</html:form>

</body>


</html>
