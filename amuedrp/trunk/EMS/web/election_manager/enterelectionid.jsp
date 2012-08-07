<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<jsp:include page="/election_manager/login.jsp"/>
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


            function check1()
            {
                if(document.getElementById('electionid').value=="")
                {
                    alert("Enter Election Id...");
                    document.getElementById('electionid').focus();
                    return false;
                }
            }
            function quit()
            {

                //   window.location="<%=request.getContextPath()%>/admin/main.jsp";
                return false;
            }
        </script>
                <%String msg=(String)request.getAttribute("msg1"); %>
    </head>
    <body>
        <html:form method="post" onsubmit="return check1()" action="/electionview">
            <div
                style="  top:100px;
                left:5px;
                right:5px;
                position: absolute;

                visibility: show;">
                <table border="1" class="table" width="400px" height="200px" align="center">
                    <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Create Election </td></tr>
                    <tr><td valign="top" align="center"> <br/>
                            <table cellspacing="10px">
                                <tr><td rowspan="5" class="txt2">Enter Election ID<br><br>
                                        <html:text property="electionId" styleId="electionid" name="DepActionForm"/>
                                    </td><td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Add" /></td></tr>
                                <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>
                                <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Delete"  /></td></tr>
                                <!--  <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>-->
                                <tr><td width="150px" align="center"><input type="submit" id="Button4" name="button" value="View" class="btn" /></td></tr>
                                <%-- <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Viewall"  /></td></tr>--%>
                                <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td></tr>
                                <tr><td><%if(msg!=null){%><%=msg %><%}%></td></tr>

                            </table>
                        </td></tr>

                </table>
            </div>
</html:form>
        </body>
    </html>
