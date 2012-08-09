<!--
NEW ARRIVAL JSP PAGE OPAC
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="java.util.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<%
 String msg=(String)request.getAttribute("msg");
 String msg1=(String)request.getAttribute("msg1");

 String item1=(String)request.getAttribute("item1");
  if(item1==null)item1="";
 String item2=(String)request.getAttribute("item2");
  if(item2==null)item2="";
 String item3=(String)request.getAttribute("item3");
  if(item3==null)item3="";
 String item4=(String)request.getAttribute("item4");
  if(item4==null)item4="";
 String control_no=(String)request.getAttribute("item5");
 String order_no=(String)request.getAttribute("item6");
 String title_id=(String)request.getAttribute("item7");
 String unit_price=(String)request.getAttribute("item8");
 String acq_mode=(String)request.getAttribute("item9");
 String vendor_id=(String)request.getAttribute("item10");
%>
<body  style="background-color:  #e0e8f5;" >

    <form name="f" id="f">
      <table  align="center" width="90%" class="datagrid"    class="txt">
 
        <tr>
           <td align="center" width="1200px" height="15px" >
             <table class="datagrid" >
               <tr>
                   <td>Title</td>
                   <td width="200px">
                       <input type="text" name="title"  styleClass="selecthome"  id="title" value="<%=item1%>" />
                   </td>
                   <td>Ordered Copies</td>
                   <td>
                       <input type="text" name="no_of_copies" styleClass="selecthome"  id="no_of_copies" value="<%=item2%>" />
                   </td>
                   <td>Prev Received</td>
                   <td>
                       <input type="text" name="recieved_copies" styleClass="selecthome"  id="recieved_copies" value="<%=item3%>" />
                   </td>
                   <td>Received Now</td>
                   <td>
                       <input type="text" name="recieved_now" styleClass="selecthome"  id="recieved_now" onchange="cal()" />
                   </td>
                   <td>Pending</td>
                   <td>
                       <input type="text" name="pending_copies" styleClass="selecthome"  id="pending_copies"  value="<%=item4%>"/>
                   </td>
                      <td> <input type="hidden" name="control_no" value="<%=control_no%>" ></td>
                      <td> <input type="hidden" name="order_no" value="<%=order_no%>" ></td>
                      <td> <input type="hidden" name="title_id" value="<%=title_id%>" ></td>
                      <td> <input type="hidden" name="unit_price" value="<%=unit_price%>" ></td>
                      <td> <input type="hidden" name="acq_mode" value="<%=acq_mode%>" ></td>
                      <td> <input type="hidden" name="vendor_id" value="<%=vendor_id%>" ></td>
             </tr>
             </table>


           </td>
        </tr>
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
    </form>
</body>
<script language="javascript">
 function cal()
 {
    var a,b,c,d,p;
    a=document.f.no_of_copies.value;
    b=document.f.recieved_copies.value;
    c=document.f.recieved_now.value;
  //  p=document.f.pending_copies.value;
    if(c>a-b)alert("Enter correct Number");
    else
    d=a-b-c;
    document.f.pending_copies.value=d;
  //alert("n="+a+"aaaaaR="+b+"AAAAAARN="+c+"pending+"+d);
  
 }
</script>
</html>
