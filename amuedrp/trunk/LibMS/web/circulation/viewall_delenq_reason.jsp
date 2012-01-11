<%-- 
    Document   : viewall_delenq_reason
    Created on : 30 Sep, 2011, 11:26:55 AM
    Author     : edrp-07
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.DeliquencyReason,java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
    </head>

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
List<DeliquencyReason> l11=(List<DeliquencyReason>)session.getAttribute("actlist");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }

 if(l11!=null)
        size = l11.size();
 else
        size = 0;

 //for calculating no of pages required
 modvalue = size%pagesize;
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;
 //to calculate the starting item and ending item index for the desired page
fromIndex = (pageIndex-1)*pagesize;
toIndex = fromIndex + pagesize;
if(toIndex>size)toIndex=size;
//fromIndex++;



%>

    <body>
     <table width="100%" class="table" valign="top">
           <tr class="headerStyle"><td >&nbsp;&nbsp; CancellationReason Id</td><td colspan="3">&nbsp;&nbsp;Description</td></tr>


          <logic:iterate id="DeliquencyReason" name="actlist" length="4" offset="<%=String.valueOf(fromIndex)%>">
                <html:form action="/DelUpdate" method="post">
            <tr>
            <td><input type="text" readonly="true" name="id"  value="<bean:write name="DeliquencyReason" property="id.id"/>"/></td>
            <td><input type="text" name="details" value="<bean:write name="DeliquencyReason" property="details"/>"/></td>
            <td><input type="submit" name="button" value="Update"/></td>
            <td><input type="submit" name="button" value="Delete"/></td>
            </tr>
            </html:form>
        </logic:iterate>
        </table>
    </body>
</html>
