

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
        
<table height="50px" style="border:solid 2px red;font-family: arial;font-size: 12px;" align="center" width="50%" >
<link rel="stylesheet" href="/LibMS/css/page.css"/>
               <tr ><td  valign="top">
        Release Notes<br>
        Product Name : LibMS Version: 1.0<br>



    </td></tr>
                <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 14-July-2012 &nbsp;<i>(Recent Database Updation on : 14-July-2012)</i>


    </td></tr>
                <tr><td><hr>    <ul style="line-height: 20px;"><li> Additions<br>

                            </li>
                           <li> Removals&nbsp; -&nbsp;None.</li>
                           <li> Changes<br>
                           1. In bibliograhic_details table (
 alter table bibliograhic_details add column image varchar(200),digital_data varchar(200),digital_content varchar(300));
 2. In bibliograhic_details_lang table (
 alter table bibliograhic_details_lang add column image varchar(200),digital_data varchar(200),digital_content varchar(300));
                           </li>

                       </ul>
</td></tr>
                  <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 13-July-2012 &nbsp;<i>(Recent Database Updation on : 13-July-2012)</i>


    </td></tr>
                <tr><td><hr>    <ul style="line-height: 20px;"><li> Additions<br>

                            </li>
                           <li> Removals&nbsp; -&nbsp;None.</li>
                           <li> Changes<br>
                           1. In Cir_requestfrom_opac table (ALTER TABLE Cir_requestfrom_opac drop column image;
 alter table Cir_requestfrom_opac add image varchar(200));
                           </li>

                       </ul>
</td></tr>
                <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 12-July-2012 &nbsp;<i>(Recent Database Updation on : 12-July-2012)</i>


    </td></tr>
                <tr><td><hr>    <ul style="line-height: 20px;"><li> Additions<br>

                            </li>
                           <li> Removals&nbsp; -&nbsp;None.</li>
                           <li> Changes<br>
                           1. alter table library modify library_name varchar(100);<br>
2. ALTER TABLE admin_registration drop column insti_logo;<br>
3. alter table admin_registration add insti_logo varchar(200);<br>
                           </li>
                           
                       </ul>
</td></tr>
  <%--             <tr><td>
      <a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>
    </td>
     </tr>--%>
                </table>
    </body>
