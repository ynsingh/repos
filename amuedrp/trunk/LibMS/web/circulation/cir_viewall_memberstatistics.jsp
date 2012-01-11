
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.circulation.CheckInDocumentDetails,com.myapp.struts.systemsetupDAO.BookCategoryDAO,com.myapp.struts.utility.DateCalculation,com.myapp.struts.hbm.*,com.myapp.struts.opacDAO.*"%>
     <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
 <jsp:include page="/admin/header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>

<title>View All Member Details </title>
<%!
    static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
<%
HashMap demo = (HashMap)session.getAttribute("statistics");
Iterator it = demo.entrySet().iterator();

try{

locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>



    </head>

<script>



$(document).ready(function()
{
   var jQueryDatePicker1Opts =
   {
      dateFormat: 'yy-mm-dd',
      changeMonth: false,
      changeYear: false,
      showButtonPanel: false,
      showAnim: 'show'
   };
   $("#txtdate").datepicker(jQueryDatePicker1Opts);

});
function checkAll()
{
    field=document.getElementsByName("list");

for (i = 0; i < field.length; i++)
{
if(field[i].checked == true)
    field[i].checked = false;
else
    field[i].checked = true;
}
}



</script>
    <body>

        <form id="myform">

        <div
   style="  top:100px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;"
      >
   <table  align="center" dir="<%=rtl%>" width="70%" class="table" style="border:solid 1px black;">



  <tr  class="headerStyle"><td  width="50%" dir="<%=rtl%>"  height="28px" align="center" >


	Member Statistics




        </td>
  <td  width="50%" dir="<%=rtl%>"  height="28px" align="center" style="border:solid 1px black" >


	Transaction Log




        </td>
  </tr>
  <tr><td width="50%" valign="top">

          <table width="100%" class="table">
              <tr class="headerStyle"><td>Year</td><td>Month</td><td>Total Registration</td></tr>
              <%
              while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        String str=pairs.getKey().toString();
String str1[]=str.split(",");


        %><tr><td>
        <%=str1[0]%></td><td><%=str1[1]%></td><td><%=pairs.getValue()%></td></tr>

        <%}%>
          </table>

      </td><td style="padding-left: 10px;border:solid 1px black" width="50%" valign="top">


          <%--<input type="checkbox" onClick="checkAll()">Check All<br/>
<input type="checkbox" name="list" value="Member">Member<br>
<input type="checkbox" name="list" value="CheckIn">CheckIn<br>
<input type="checkbox" name="list" value="CheckOut">CheckOut<br>
--%>

<a href="<%=request.getContextPath()%>/circulation/cir_viewallload.do">Member Details Log</a><br><a href="<%=request.getContextPath()%>/circulation/cir_checkin_report.jsp">CheckIn (Return Book) Log</a><br>
<a href="<%=request.getContextPath()%>/circulation/cir_checkout_report.jsp">Check Out (Book Issue) Log</a>
             
          
            
      </td></tr>
    
  

 
 




       </table>




        </div>



        </form>

 
</body>
</html>