<%--
    Document   : pending_voter
    Created on : Jun 18, 2011, 4:48:20 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.VoterRegistration"%>

    <%@ page import="java.util.*,java.lang.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/election_manager/login.jsp"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Block_Managergrid</title>
         
    </head>
    <script>
        var olddoc="";
var olddoc1="";

function loadresult()
{

    var electVal = document.getElementById('e').options[document.getElementById('e').selectedIndex].value;
document.getElementById('st').style.visibility = 'hidden';
   //alert(electVal);
    var divtag = document.createElement("div");
                divtag.id = "overbody";
                divtag.style.width = "100%";
                divtag.style.height = "100%";
                window.scrollBy(0, 0);
                divtag.style.top = "0px";
                divtag.style.position = "absolute";
                divtag.style.overflow = "visible";
               // divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var h1 = "495";
                var htm = '<div id="electionResult1" style="display: block;opacity:1;background-color: white;border: 2px solid teal;height: 90%; width: 669px;margin-left: 25%; position: absolute;top:100px;">';
                htm+= '<div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>';
                htm+='<div style="position: relative">';
                  htm+='<iframe name="f1" id="f1"  src="<%=request.getContextPath()%>/election.do?election='+ electVal +'&amp;compute=true" width="665px" style="height: '+ h1 +'px" />';
                
                    htm+='</div></div>';
                divtag.innerHTML = htm;
               
                document.getElementById("bod").appendChild(divtag);
 //alert(h1);

}
function loadresult1()
{

     var electVal = document.getElementById('e').options[document.getElementById('e').selectedIndex].value;
    deleteBod();
    var divtag = document.createElement("div");
                divtag.id = "overbody";
                divtag.style.width = "100%";
                divtag.style.height = "100%";
                window.scrollBy(0, 0);
                divtag.style.top = "0px";
                divtag.style.position = "absolute";
                divtag.style.overflow = "visible";
                divtag.style.backgroundColor = "gray";
                divtag.style.opacity = 0.97;
                var h1 = "495";//alert(h1);
                var htm = '<div id="electionResult1" style="display: block;opacity:1;background-color: white;border: 2px solid teal;height: 90%; width: 669px;margin-left: 25%; position: absolute;top:100px;">';
                htm+= '<div style="background-color: teal;width: 100%;position: relative">&nbsp;<span style="float: right;"><a href="#" title="Close this window" onclick="deleteBod();">[X]</a></span></div>';
                htm+='<div style="position: relative">';
                htm+='<iframe name="f1" id="f1"  src="<%=request.getContextPath()%>/Voter/viewpostal.jsp?election='+ electVal +'&amp;compute=t" width="665px" style="height: '+ h1 +'px" />';
                    htm+='</div></div>';
                divtag.innerHTML = htm;
                document.getElementById("bod").appendChild(divtag);


}
function deleteBod()
            {
                var child = document.getElementById("overbody");
               if(child!=null)
               document.getElementById("bod").removeChild(child);

            }


        </script>
    <body>

        <%!


   StaffDoc Ob;
   VoterRegistration voter;
  // Election_Manager_StaffDetail ems;
  // AdminRegistration adminReg;
   //ElectionManager electionmanager;
   //StaffDetail staffdetail;

   ArrayList requestList;
   int fromIndex=0, toIndex;

%>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
String user=(String)session.getAttribute("username");
String instituteName=(String)session.getAttribute("institute_name");
 String contextPath = request.getContextPath();
 String role=(String)session.getAttribute("login_role");
    %>
    <body id="bod" >
        <div id="st" >
<%
  List<Election> lstclosedelection = (List<Election>)session.getAttribute("ClosedelectionList");
%>
          <%if(lstclosedelection!=null && !lstclosedelection.isEmpty()){%>

           
                    <html:form action="/electionResult" styleId="f2" target="f1">
                        Select an Election: <html:select property="election" styleId="e" >
              <html:options collection="ClosedelectionList" property="id.electionId" labelProperty="electionName" />
                        </html:select><input type="button"  value="Add Postal Vote" onclick="loadresult();" />
                        <input type="button"  value="View Postal Vote" onclick="loadresult1();"  />
                       <%-- <input type="button"  value="Update Postal Vote" onclick="loadresult2();"  />--%>
                    </html:form>
               <%}%>

        </div>

    </body>

</html>
