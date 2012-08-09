

<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";

    String align="left";

   //  String webadmin=getServletContext().getInitParameter("webmail");
   //  String webpass=getServletContext().getInitParameter("webpass");
%>
<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
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
    <body style="margin: 0px 0px 0px 0px;">
        <jsp:include page="header.jsp"></jsp:include>
<table height="50px" style="border:solid 2px red;font-family: arial;font-size: 12px;" align="center" width="50%" >
<link rel="stylesheet" href="/LibMS/css/page.css"/>
               <tr ><td  valign="top">
        Release Notes<br>
        Product Name : LibMS Version: 1.0<br>



    </td></tr>
                 <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 19-July-2012 &nbsp;<i>(Recent Server Updation on : 19-July-2012)</i>


    </td></tr>
                <tr><td><hr>    <ul style="line-height: 20px;"><li> Additions <br>1. Add .mrc(MARC-21) file import facility in MARC repository.


                            </li>
                           <li> Removals&nbsp; -&nbsp;None.</li>
                           <li> Changes<br></li>
                           <li> Fixes<br>1.Fix Bug in Export Catalog Data in XLS & Flat Files Format.</li>
                       </ul>
</td></tr>
               <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 14-July-2012 &nbsp;<i>(Recent Server Updation on : 14-July-2012)</i>


    </td></tr>
                <tr><td><hr>    <ul style="line-height: 20px;"><li> Additions <br>1. Add Book Title Page Image Upload in Cataloguing Module.
                                <br>2. Add Book E-Content Upload in Cataloguing Module.
                                <br>3. View E-Content Uploaded from OPAC Simple & Browse Search.

                            </li>
                           <li> Removals&nbsp; -&nbsp;None.</li>
                           <li> Changes<br></li>
                           <li> Fixes<br>1.Fix Bug in Simple & Browse Search in Multilingual Data.</li>
                       </ul>
</td></tr>
               <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 13-July-2012 &nbsp;<i>(Recent Server Updation on : 13-July-2012)</i>


    </td></tr>
                <tr><td><hr>    <ul style="line-height: 20px;"><li> Additions <br>1. View Institute Logo in WebAdmin Module.

                            </li>
                           <li> Removals&nbsp; -&nbsp;None.</li>
                           <li> Changes<br></li>
                           <li> Fixes<br>1. Bug fixing from Member Registration Image Upload problem.<br>1.Bug Fixing in Image Upload problem in Circulation->Member Registration. </li>
                       </ul>
</td></tr>
                <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 12-July-2012 &nbsp;<i>(Recent Server Updation on : 12-July-2012)</i>


    </td></tr>
                <tr><td><hr>    <ul style="line-height: 20px;"><li> Additions<br>1. Add View All Institute List from home page of LibMS along with institute logo in jpg & png format only.
                                <br>2. Add OPAC help from opac section.<br> 3. Add link for Change Staff Password, Member Upload Activities.
                            </li>
                           <li> Removals&nbsp; -&nbsp;None.</li>
                           <li> Changes<br>1. Bug fixing from institute logo upload activity. </li>
                           <li> Fixes<br>1. Modify the css of SuperAdmin Module to view all header of grid.</li>
                       </ul>
</td></tr>
                <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 09-July-2012 &nbsp;<i>(Recent Server Updation on : 09-July-2012)</i>


    </td></tr>
                <tr><td><hr>    <ul style="line-height: 20px;"><li> Additions<br>1. Add View All Institute List from home page of LibMS.<br>2.Add Set Reservation Priority Link in System Setup.</li>
                           <li> Removals&nbsp; -&nbsp;None.</li>
                           <li> Changes<br>1. Modify home page of LibMS.<br>2.Add Expected arrival date of book in OPAC view Page. </li>
                           <li> Fixes<br>1. Modify the css of SuperAdmin Module to view all header of grid.<br>2. Resolved Bugs in Admin update staff activity.<br> 3. Remove Fixed Data of Courstey from Create Staff Activity.</li>
                       </ul>
</td></tr>
  <%--             <tr><td>
      <a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>
    </td>
     </tr>--%>
                </table>
    </body>
