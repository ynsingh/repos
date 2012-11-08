

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
    <link rel="stylesheet" href="/LibMS/css/page.css"/>
    <script>
        function fun()
        {

            document.form1.action="<%=request.getContextPath()%>/admin/language.jsp";
            document.form1.submit();
        }
        </script>
    <body style="margin: 0px 0px 0px 0px;">
        <form name="form1">
  <table align="center" width="100%" height="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;" dir="<%=rtl%>" >
      <tr><td class="homepage" style="background-color: black;color:white;" align="right" colspan="2">



         <a style="color:white" href="<%=request.getContextPath()%>">Home</a>&nbsp;|&nbsp;     <a style="color:white" href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>&nbsp;|&nbsp;<a  style="color:white" href="<%=request.getContextPath()%>/contactus.jsp">Contact Us</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/admin_registration.jsp"><%= resource.getString("login.href.institute.registration") %></a>
         &nbsp;|&nbsp;            <a style="color:white" href="<%=request.getContextPath()%>/relnotes.jsp">   Release Notes</a>&nbsp;|&nbsp;  <a style="color:white" href="instantUserManual_LibMS-2012.pdf">UserManual</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/view_instiapproved.jsp">View All Registered Institutes</a>&nbsp;|&nbsp;<%=resource.getString("login.message.selectlanguage")%><select name="locale" class="selecthome" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select>



                                         </td>

                                     </tr>
            <tr><td><table align="center"  width="100%"     dir="<%=rtl%>" >

   <tr ><td>
                                     <table width="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"><tr>
                                         <td align="left"  style="background-color: white;color:blue;height: 50px;  margin: 0px 0px 0px 0px;font-style: italic;font-size: 18px;valign:bottom" valign="bottom" align="center">

                                             &nbsp;&nbsp;<span style="font-style: italic;font-size: 18px;">LibMS....</span>        "<%=resource.getString("login.message.logo.under")%>"




                            </td>
                            <td align="right">
  <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="height:70px;width:160px;">

                            </td>


                                         </tr></table>
                                          <hr color="cyan">
                                         </td>

           </tr>
           <tr><td align="center"><table class="homepage" width="70%">
               <tr ><td  valign="top">
        RELEASE NOTES&nbsp;&nbsp;      Product Name : LibMS Version: 1.0<br>



    </td></tr>
                 <tr ><td  valign="top"><hr>
        Published By EdRP Team @AMU on 12- Sept -2012 &nbsp;<i>(Recent Server Updation on : 12-Sept-2012)</i>


    </td></tr>
                <tr><td>
<pre>
	Core Module of LibMS:

1.Web Administration
2.Administration & Security
3.Utility & System SetUp
4.OPAC
5.Acquisition
6.Cataloguing
7.Serial
8.Circulation
                              
Administration Module

    In Administration Module Following Activities are Integrated & functional.
    1.Register Library Staff's  (Add/Update/View/Delete)
    2.Upload Library Staff Data in XLS Sheet
    3.Activate/Deactivate Staff Account
    4.Upload Institute Logo
    5.Export Library Staff Data in XLS/XML/CSV Format.

System SetUp Module

    In System Setup Module Following Activities are Integrated & functional.
    1.Add Location
    2.Add General Notices
    3.Add Faculty/Courses/Dept
    4.Add Member/Sub Member Types
    5.Add Dept Libraries
    6.Configure Fine Details
    7.Add Document Types

Utility & Help Module

    1. Provide UserManual of LibMS in PDF Format

Acquisition Module :

    In Acquisition Module Following Activities are Integrated & functional.
    1.Enter Title Refer from Demand List & Direct Entry
    2.Initiate Acquisition
    3.Approval Process
    4.Order Process
    5.Invoicing Process
    6.Receiving Documents
    7.Payment Process
    8.Accessioning of Documents
    9.Reports ( Pending Documents, Approved Document List)
    10.Set Budget Head/Budget Head Allocation, Vendor Setting, Base Currency Setting

Cataloguing Module:

    In Cataloguing Module Following Activities are Integrated & functional.
    1.Cataloguing of retrospective documents.
    2.Cataloguing of new documents (multilingual data entry) of books in AACR2 Format.
    3.Accessioning of documents in AACR2 Format.
    4.Generate Catalog Card
    5.Generate Bar Code
    6.Import Bibliographic Details Entry in XLS File format.
    7.Export Bibliographic Details Entry in XLS File format.
    8.Import .mrc file from Library of Congress (Copy Catalog Activity)
    9.Upload Digital Data like PDF/Video File & Book Cover Page related to documents.
    10.Bibliographic Detail Entry in Dissertation Document Type
    11.Accessioning in Dissertation Document Type
    12.Bibliographic Detail Entry in CD Document Type (Insert Activity)
    13.Bibliographic Detail Entry in Journal Document Type
    14.Accessioning in Journal Document Type


Circulation Module

    In Circulation Module Following Activities are Integrated & functional.
    1.Member Registration ( Direct Data Entry/ Import from XLS File)
    2.Request Approval coming from OPAC.
    3.Delinquent Member
    4.Check In Activity
    5.Check Out Activity
    6.Report on CheckIn/CheckOut
    7.Fine Management Activity
    8.Report on Fine Management (View All OverDue Members/ Generate Payment Slip)
    9.Generate Library Card Activity.

OPAC Module:

    In OPAC Module Following Activities are Integrated & functional.
    1.	Narrow Down Searching from different Searching options i.e
	   a) Simple Search
	   b) Browse Search
	   c) Additional Search
	   d) Advanced Search
	   e) ISBN Search
	   f) Accession No Search
	   h) Call No Search
    2.View Digital Content in All Search Result if available
    3.Send Request for Check Out From OPAC.
    4.New Arrival Link to View/Search Book recently acquired in Library.
    5.View Notices
    6.View Location
    7.Send FeedBack
    8.Send Member Registration request
    9.My Account Section to View Registered Library Staff CheckIn/CheckOut Details.
    10.Rate the Book in Simple Search Link.

Serial Module

    1.Set Budget Head/Budget Head Allocation, Vendor Setting, Base Currency Setting
    2.Language Setting
    3.Serial New Entry for New Paper type periodicals.
    4.Report in Pending/Rejected/OnApproval & Firm Order Items

Web Administration

    Once Any User Download the System & have a local installation they need to login into the system & Use this module. It has following features
    1.Approval Process of Institute Request
    2.SMTP Mail Setting
    3.Block Institute
    4.Change Any User Password.
</pre>
</td></tr>
  
                   </table></td></tr></table></td></tr></table></form>
<jsp:include page="/OPAC/opacfooter.jsp"/>
    </body>
