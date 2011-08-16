<%--
    Document   : approvallist
    Created on : May 12, 2011, 4:00:20 PM
    Author     : faraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.Acquisition.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


        <%!
int fromIndex,toIndex;
int pagesize=2,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
List obj1;

%>
<%
int i=0;
 int j=0;

List<ApprovalList> app=(List<ApprovalList>)session.getAttribute("approval1");

 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }

 if(app!=null)
        size = app.size();
 else
        size = 0;

 //for calculating no of pages required
 modvalue = size%pagesize;
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;


 //to calculate the starting item and ending item index for the desired page

 fromIndex =(pageIndex-1)*pagesize;
 
toIndex = fromIndex + pagesize;

 System.out.println(size+"     "+pageIndex+" " +noofpages+"   "+fromIndex+"   "+toIndex+" "+pagesize);
if(toIndex>size)toIndex=size;




%>
<body>
<form id="f1">
        <table border="0px" style="margin: 0px 0px 0px 0px;">
                   <tr><td valign="top" >
                           <table  border="0px" valign="top">
             <tr bgcolor="#E0E8F5" ><td width="100" >Control No</td><td width="200">Title</td><td width="200">Author</td><td width="200">ISBN</td><td width="100">Unit Price</td><td width="100">No of Copies</td></tr>

             <logic:iterate id="ApprovalList" name="approval1" offset="<%=String.valueOf(fromIndex)%>" length="2">
                <tr>
                    <td><bean:write name="ApprovalList" property="control_no"/></td>
                    <td><bean:write name="ApprovalList" property="title"/></td>
                    <td><bean:write name="ApprovalList" property="author"/></td>
                    <td><bean:write name="ApprovalList" property="isbn"/></td>
                    <td><bean:write name="ApprovalList" property="unit_price"/></td>
                    <td><bean:write name="ApprovalList" property="no_of_copies"/></td>

                   
                </tr>

        </logic:iterate>
 </table>
                           </td></tr>

               </table>
</form>
</body>