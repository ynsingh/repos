 
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


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>

<body>
 
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
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex + perpage) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<table align="center" width="100%"   dir="<%=rtl%>" >
       <tr  ><td  valign="bottom" height="10%"   colspan="2" >





    <img src="<%=request.getContextPath()%>/images/bp.PNG" alt="banner space"  border="0" align="<%=align%>" dir="<%=rtl%>" id="Image1" style="height:50px;width:200px;">
                                <br>


                            </td>
                            <td align="right" valign="bottom" >
                                <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="">
                </td>

            </tr>
            <tr><td colspan="3" align="center">Registered Institute List</td></tr>
</table><hr>
 Total Institute [<%=rs.size()%>]
<br><br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>


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

<%
  String RegistrationID=resource.getString("admin.viewpending.registrationid");
  pageContext.setAttribute("RegistrationID", RegistrationID);
  String InstituteName=resource.getString("admin.viewpending.institutename");
  pageContext.setAttribute("InstituteName", InstituteName);
  String AdminEmail=resource.getString("admin.viewpending.adminemail");
  pageContext.setAttribute("AdminEmail", AdminEmail);
pageContext.setAttribute("rec",perpage);


%>
  <table class="datagrid" width="100%" style="border:dashed 1px cyan;">
            <tr class="header">
                <td>Logo</td>
              <td   >
                  <i>Institute Name</i></td>
              <td   >
                         <i>Library Name</i></td>
              <td   >
                         <i>Address</i></td>
              <td   >
                         <i>Institute Admin</i></td>
              <td   >
                         <i>Email Id</i></td>
              


                 </tr>
        <% for(int i=0;i<requestList.size();i++){
   if((i%2)==0){
    %>

        <tr>
            <%}else{%>
        <tr class="alternaterows">
            <%}%>
            <td><img src="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=requestList.get(i).getLogo() %>" alt="no image selected" width="120" height="120" border="1" /></td>
            <td    style="border-top:dashed 1px cyan;"><%=requestList.get(i).getInstitute_name() %></td>
            <td   style="border-top:dashed 1px cyan;"><%=requestList.get(i).getLibrary_name() %></td>
            <td    style="border-top:dashed 1px cyan;"><%=requestList.get(i).getCity() %></td>
            <td    style="border-top:dashed 1px cyan;"><%=requestList.get(i).getUser_name() %></td>
            <td    style="border-top:dashed 1px cyan;"><%=requestList.get(i).getAdmin_email() %></td>
            
        </tr>
        <%}%>
    </table>


<table align="center" width="100%"   dir="<%=rtl%>" ><tr><td align="center">
  </td></tr></table>


<%}}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>
 
    </body>



</html>


