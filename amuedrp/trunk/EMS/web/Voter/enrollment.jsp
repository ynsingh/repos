<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ELECTION MANAGER </title>
        <script language="javascript" type="text/javascript">


            function check2()
            {
                if(document.getElementById('Enrollment').value=="")
                {
                    alert("Enter Enrollment...");
                    document.getElementById('Enrollment').focus();
                    return false;
                }
                if(document.getElementById('ins').options[document.getElementById('ins').selectedIndex].value=="Select")
    {
        alert("Select Institute Name");

        document.getElementById('ins').focus();

        return false;
    }
    }
            function quit()
            {

                 window.location="<%=request.getContextPath()%>/login.jsp";
                return false;
            }

  function send()
{
    window.location="<%=request.getContextPath()%>/login.jsp";
    return false;
}


        </script>
                <%String msg=(String)request.getAttribute("msg1"); %>
    </head>
    <body>
        <html:form method="post" onsubmit="return check2()" action="/newregistration">
            <div
                style="  top:100px;
                left:5px;
                right:5px;
                position: absolute;

                visibility: show;">
                <table  class="table" width="600px" height="300px" align="center">
                    <tr><td align="center" class="headerStyle1" bgcolor="#E0E8F5" height="25px;">Voter Registration </td></tr>
                    <tr><td valign="top" align="center"> <br><br>
                            <table cellspacing="10px">

<tr><td align="left">Enter Enrollment No</td><td>  <html:text property="enrollment" styleId="Enrollment" name="VoterRegActionForm"/></td></tr>



<br><tr><td align="left">Institute Name</td><td> <html:select property="institute_id" styleId="ins"  name="VoterRegActionForm"  tabindex="10">

            <html:option  value="Select"> Select </html:option>
            <html:options collection="Institute"  labelProperty="instituteName" property="instituteId"  name="Institute" ></html:options>
                                <%--<html:option  value="amu">Aligarh muslim university</html:option>
                                <html:option value="jmi">Jamia Millia islamia</html:option>
                               <html:option value="du">Delhi University</html:option>
                               <html:option value="jnu">JNU</html:option>--%>
</html:select>
    </td>
      </tr>

      <tr><td align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Add" /><input type="button" class="btn" id="Button11" name="button" value="Cancel" onclick="return send();"/></td>
          </tr>
                                <%-- <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>--%>
                                <%-- <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Delete"  /></td></tr>--%>
                                <!--  <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>-->
                                     <tr><td><%if(msg!=null){%><%=msg %><%}%></td></tr>
                            </table>
                        </td></tr>

                </table>
            </div>
</html:form>
        </body>
    </html>
