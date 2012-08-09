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
 String h=(String)session.getAttribute("disbl");
 String noofcopies=(String)request.getAttribute("noofcopies");
 if(noofcopies==null)noofcopies="";
 String unitprice=(String)request.getAttribute("unitprice");
 if(unitprice==null)unitprice="";
 String title1=(String)request.getAttribute("title");
 if(title1==null)title1="";
 Integer tamount=(Integer)request.getAttribute("tamount");
 if(tamount==null)tamount= 0;
 String discount=(String)request.getAttribute("discount");
 if(discount==null)discount="";
 String net_amt=(String)request.getAttribute("net_amt");
 if(net_amt==null)net_amt="";
 String sizearray=(String)request.getAttribute("sizearray");
 String sizearrayindex=(String)request.getAttribute("sizearrayindex");
 String i=(String)request.getAttribute("i");
 System.out.println("aqeelllllllllllllllllllll"+discount);
%>
<body  style="background-color:  #e0e8f5;" onload="change();">

    <form action="invoice_item4.do" method="post" name="f" id="f">
      <table  align="center" width="90%" class="datagrid"    class="txt">

        <tr>
           <td align="center" width="1200px" height="15px" >
             <table class="datagrid" >
               <tr>
                   <td>Title</td>
                   <td width="100px">
                       <input type="text" name="title"  styleClass="selecthome"  id="title" value="<%=title1%>"/>
                   </td>
                   <td align="left">No. of copies</td>
                   <td align="left" width="200px">
                       <input type="text" name="no_of_copies"  styleClass="selecthome"  id="no_of_copies" value="<%=noofcopies%>"/>
                   </td>
                   <td align="left">Unit Price</td>
                   <td align="left" width="200px">
                    <input type="text" name="unit_price"  styleClass="selecthome"  id="unit_price" value="<%=unitprice%>"/>
                   </td>
                   <td align="left">Total Amount</td>
                   <td align="left" width="200px">
                   <input type="text" name="total_amount"  styleClass="selecthome"  id="total_amount" value="<%=tamount%>"/>
                  </td>
             </tr>
             <tr>
                 <td width="80px">Discount(in %)</td>
                 <td width="200px">
                     <input type="text" name="discount" styleClass="selecthome"  id="discount" value="<%=discount%>"   onchange="validate();parent.hello(this.form.discount.value);"/>
                 </td>
                 <td>Net Amount</td>
                 <td width="200px">
                  <input type="text" name="net_amt" styleClass="selecthome"  id="net_amt"  value="<%=net_amt%>" />
                 </td>
                 
             </tr>
            <%-- <input type="text" name="discnt" styleClass="selecthome"  id="discnt"  value="<%=discnt%>" />
             <input type="text" name="ntotal" styleClass="selecthome"  id="ntotal"  value="<%=ntotal%>" />--%>
             <input type="hidden" name="sizearray" styleClass="selecthome"  id="sizearray" value="<%=sizearray%>"/>
             <input type="hidden" name="sizearrayindex" styleClass="selecthome"  id="sizearrayindex" value="<%=sizearrayindex%>"/>
             <input type="hidden" name="i" styleClass="selecthome"  id="i" value="<%=i%>"/>
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

function validate()
{   var discount,t_amt,net_amt;
    discount =document.f.discount.value;
    t_amt=document.getElementById('total_amount').value;
    net_amt=t_amt-.01*discount*t_amt;
    document.f.net_amt.value=net_amt;
    document.f.action="<%=request.getContextPath()%>/acquisition/invoice_item4.do";
    document.f.method="post";
    document.f.target="f2";
    document.f.submit();
    return true;
}

</script>

<script language="javascript">

function change()
{
     var x=document.getElementById("discount");
     var hi="<%=discount%>";

     //alert("sss"+hi);
     if(hi=="")
     {
         x.disabled=false;
     }
     else{
        x.disabled=true;
     }

}

</script>
</html>
