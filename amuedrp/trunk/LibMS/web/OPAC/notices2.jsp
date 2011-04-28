<%-- 
    Document   : notices2
    Created on : Apr 1, 2011, 6:01:44 AM
    Author     : edrp02
--%>
<%@ page import="java.util.*,com.myapp.struts.hbm.*,java.text.*,java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%!
int fromIndex,toIndex;
int pagesize=10,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
%>
<%
 List<Notices> notices=(List<Notices>)session.getAttribute("notices");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }

 if(notices!=null)
        size = notices.size();
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
fromIndex++;



%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%

try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
    </head>
    <body>
     <table style="width:500px" align="center" dir="<%=rtl%>" >
<%if(size==0){%>
<p class="err">no record found</p>
<%}else{%>

     <% for(;fromIndex<=toIndex;fromIndex++){%>
     

     <tr dir="<%=rtl%>" align="center">
     <table dir="<%=rtl%>" align="center" width="50%">

      <tr dir="<%=rtl%>" align="<%=align%>">
          <td  align="<%=align%>" dir="<%=rtl%>" class="heading"><p dir="<%=rtl%>" align="justify" align="<%=align%>"><a dir="<%=rtl%>" href="<%=request.getContextPath()%>/viewnotices.do?name=<%=notices.get(fromIndex-1).getId().getNoticeId()%>" target="f5"><%=notices.get(fromIndex-1).getSubject()%></a></p></td></tr>

      <tr dir="<%=rtl%>" align="<%=align%>"><td dir="<%=rtl%>" align="<%=align%>">
              <%
       String str_date=notices.get(fromIndex-1).getDate();
       String  year=str_date.substring(0,str_date.indexOf("-"));


        String    month=str_date.substring(str_date.indexOf("-")+1,str_date.indexOf("-", str_date.indexOf("-")+1));


        String    day=str_date.substring(str_date.lastIndexOf("-")+1,str_date.length());

String txtmonth="";
int month1=Integer.parseInt(month);
switch(month1)
        {

case 1:
        txtmonth="January";
        break;
case 2:
        txtmonth="Feb";
        break;

case 3:
        txtmonth="March";
        break;

case 4:
        txtmonth="April";
        break;

case 5:
        txtmonth="May";
        break;

case 6:
        txtmonth="June";
        break;

case 7:
        txtmonth="July";
        break;

case 8:
        txtmonth="August";
        break;

case 9:
        txtmonth="Sept";
        break;

case 10:
        txtmonth="October";
        break;

case 11:
        txtmonth="November";
        break;

case 12:
        txtmonth="December";
        break;

}





                    %><%=day%>&nbsp;<%=txtmonth%>&nbsp;,&nbsp;<%=year%></td></tr>
      <tr dir="<%=rtl%>" align="<%=align%>"><td dir="<%=rtl%>" align="<%=align%>"><a dir="<%=rtl%>" href="<%=request.getContextPath()%>/viewnotices.do?name=<%=notices.get(fromIndex-1).getId().getNoticeId()%>" target="f5"><font size="3" color="blue">More</font></a></td></tr>
     <tr dir="<%=rtl%>" align="<%=align%>"><td dir="<%=rtl%>" align="<%=align%>"></td></tr>
     </table>
     
 </tr>
     
    <% }%>
     
    <tr align="center">
        <td align="center" dir="<%=rtl%>" colspan="4"><p align="center" dir="<%=rtl%>">Pages&nbsp;&nbsp;
        <%for(int i=1;i<=noofpages;i++){%>
        <a dir="<%=rtl%>" href="<%=request.getContextPath()%>/OPAC/notices2.jsp?pageIndex=<%=i%>"><%=i%></a>&nbsp;&nbsp;
        <%}%>
            </p>
        </td>
    </tr>
    <%}%>
</table>
    </body>
</html>
