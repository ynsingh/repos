 
    <%@page import="com.myapp.struts.admin.RequestDoc,com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.AdminRegistrationDAO,com.myapp.struts.utility.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>View All Registered Institute List: LibMS</title>

    <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>

<body style="margin: 0px 0px 0px 0px" >
 
<%!
   
   
   RequestDoc Ob;
   List<RequestDoc> requestList;
   AdminRegistration adminReg;
   int fromIndex=0, toIndex;

%>
 <%
  AdminRegistrationDAO admindao = new AdminRegistrationDAO();
   

                    

 List rs = (List)admindao.getAdminDetailsByStatus("Registered");
if(rs!=null){
 Iterator it = rs.iterator();

   requestList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 
 if(request.getParameter("pageSize")!=null && request.getParameter("pageSize")!="")
    perpage = Integer.parseInt((String)request.getParameter("pageSize"));


  while (it.hasNext()) {
	
	Ob = new RequestDoc();
        adminReg = (AdminRegistration)rs.get(tcount);
	Ob.setRegistration_id(adminReg.getRegistrationId());
	Ob.setInstitute_name(adminReg.getInstituteName());
	Ob.setUser_name(adminReg.getCourtesy()+" "+adminReg.getAdminFname()+" "+adminReg.getAdminLname());
        Ob.setCity(adminReg.getInstituteAddress()+","+adminReg.getCity()+adminReg.getState());
	Ob.setAdmin_email(adminReg.getAdminEmail());
        Ob.setLibrary_name(adminReg.getLibraryName());
        Ob.setLogo(adminReg.getInstiLogo());
        adminReg=null;
   requestList.add(Ob);
   tcount++;
it.next();
  
		     }

System.out.println("tcount="+tcount);

%>
<script>
    function fun()
        {

            document.form1.action="<%=request.getContextPath()%>/admin/language.jsp";
            document.form1.submit();
        }



    function changerec(){
        var x=document.getElementById('rec').value;
    var loc = window.location;
    loc = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/admin/view_approved.jsp";
    
   // alert(loc);
        loc = loc + "?pageSize="+x;
    window.location = loc;

    }

      document.onkeyup = keyHit
function keyHit(event) {

  if (event.keyCode == 13) {
  changerec();

    event.stopPropagation()
    event.preventDefault()
  }
}

function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
    </script>


<%
  String RegistrationID=resource.getString("admin.viewpending.registrationid");
  pageContext.setAttribute("RegistrationID", RegistrationID);
  String InstituteName=resource.getString("admin.viewpending.institutename");
  pageContext.setAttribute("InstituteName", InstituteName);
  String AdminEmail=resource.getString("admin.viewpending.adminemail");
  pageContext.setAttribute("AdminEmail", AdminEmail);
pageContext.setAttribute("rec",perpage);


%>
       
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex + perpage) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
 <form name="form1" method="post">
<table align="center" width="100%" height="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;" dir="<%=rtl%>" >
      <tr><td class="homepage" style="background-color: black;color:white;height: 10px;" align="right" colspan="2">



         <a style="color:white" href="<%=request.getContextPath()%>">Home</a>&nbsp;|&nbsp;     <a style="color:white" href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>&nbsp;|&nbsp;<a  style="color:white" href="<%=request.getContextPath()%>/contactus.jsp">Contact Us</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/admin_registration.jsp"><%= resource.getString("login.href.institute.registration") %></a>
         &nbsp;|&nbsp;            <a style="color:white" href="<%=request.getContextPath()%>/relnotes.jsp">   Release Notes</a>&nbsp;|&nbsp;  <a style="color:white" href="instantUserManual_LibMS-2012.pdf">UserManual</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/view_instiapproved.jsp">View All Registered Institutes</a>&nbsp;|&nbsp;<%=resource.getString("login.message.selectlanguage")%><select name="locale" class="selecthome" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select>



                                         </td>

                                     </tr>
                                     <tr ><td height="25px;">
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
          
<tr><td colspan="2" height="20px"  align="center" valign="top">

Registered Institute List : Total Institute [<%=rs.size()%>]<br>




                </td></tr>
 
<tr><td colspan="2" valign="top" align="center">
  <table   width="50%" style="border:dashed 1px cyan;font-family: arial;">
      
            <tr >
                
                <td>Sno</td>

              <td   >
                  <i>Institute Name</i></td>
              
              <td   >
                         <i>Institute Admin</i></td>
              


                 </tr>
        <% for(int i=0;i<requestList.size();i++){
   if((i%2)==0){
    %>

        <tr>
            <%}else{%>
        <tr >
            <%}%>
            <td valign="middle" style="border-top:dashed 1px cyan;"><%=i+1%>.</td>
            
            <td valign="top"    style="border-top:dashed 1px cyan;">
                <table><tr><td>
                <img src="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=requestList.get(i).getLogo() %>" alt="no image selected" width="80" height="80" border="1" />
                        </td><td>
                <b><%=requestList.get(i).getInstitute_name() %></b><br>
            <%=requestList.get(i).getLibrary_name() %><br>
            <%=requestList.get(i).getCity() %>
                        </td></tr></table>
            </td>
            
            <td valign="middle"   style="border-top:dashed 1px cyan;"><%=requestList.get(i).getUser_name() %>
            <br><%=requestList.get(i).getAdmin_email() %></td>
            
        </tr>
        <%}%>
    </table>

    </td></tr></table>



<%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>

</form>
    </body>

<jsp:include page="/OPAC/opacfooter.jsp"/>

</html>


