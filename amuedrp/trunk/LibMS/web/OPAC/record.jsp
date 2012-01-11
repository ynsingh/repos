<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.hbm.DocumentDetails"%>

<html><head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";

int fromIndex,toIndex;
int pagesize=10,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
List obj1;

%>
<%
 String lib_id = (String)session.getAttribute("library_id");
  String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
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
session.setAttribute("page_name", "accessionno");


List<DocumentDetails> l12=(List<DocumentDetails>)session.getAttribute("documentDetail");
System.out.println(l12.size()+"............");
index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }
if(l12!=null)
        size = l12.size();
 else
        size = 0;


 //for calculating no of pages required
 modvalue = size%pagesize;
 System.out.println("ModValue"+modvalue);
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;

 //to calculate the starting item and ending item index for the desired page
fromIndex = (pageIndex-1)*pagesize;


toIndex = fromIndex + pagesize;
if(toIndex>size)toIndex=size;
//fromIndex++;

System.out.println(size+"   "+pageIndex+" "+noofpages);
  %>



    </head><body style="margin:0px 0px 0px 0px;background-color:#e0e8f5;font: arial;font-size: 11px;text-align: center">
        



   
             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="<%=request.getContextPath()%>/OPAC/allitems.jsp" frameborder=0 height="260px" width="80%" scrolling="no"  id="f1"></IFRAME>
<br/>Pages&nbsp;&nbsp;
        <%for(int ii=1;ii<=noofpages;ii++){%>
        <a dir="" target="f1" href="<%=request.getContextPath()%>/OPAC/allitems.jsp?pageIndex=<%=ii%>"><%=ii%></a>&nbsp;&nbsp;

        <%}%>
          
  



    </body></html>