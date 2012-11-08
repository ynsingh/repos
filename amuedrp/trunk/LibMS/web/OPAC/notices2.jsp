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
 ArrayList<Notices> notices=(ArrayList<Notices>)session.getAttribute("notices");
 System.out.println(notices.size()+"kkkkkkkkkkkkkkkkkkkkkk");
/* index = request.getParameter("pageIndex");
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

*/

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
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
  
         <body onload="parent.setIframeHeight();" class="header1">

        <table dir="<%=rtl%>"  align="<%=align%>" class="datagrid" width="50%" >
        <%if(notices.size()==0){%> <%=resource.getString("global.norecordfound")%><%}else{%>

        <tr  dir="<%=rtl%>" class="header1"><td width="5px" >Sno</td><td   align="left" dir="<%=rtl%>"><b>Notices</b></td></tr>

         <% for(int i=0;i<notices.size();i++ ){%>

        <tr  dir="<%=rtl%>">
            <td valign="top"><%=i+1%>.</td>
                <td  align="<%=align%>" dir="<%=rtl%>" class="heading1">
                    <b>Subject</b>&nbsp;&nbsp:<i><%=notices.get(i).getSubject() %></i><br>
                    <ul><b><i>Details</i></b>&nbsp;&nbsp:<i><%=notices.get(i).getDetail() %></i><br>
                        <b><i>Date</i></b>&nbsp;&nbsp:<i><%=notices.get(i).getDate() %></i><br>
                        </ul>
                </td>
            </tr>
         <%}%>
         <%}%>
         </table>

    </body>
    </body>
</html>
