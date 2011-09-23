<%-- 
    Document   : cat_modify_MARC
    Created on : May 22, 2011, 3:28:21 PM
    Author     : zeeshan
--%>


<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/admin/header.jsp"/>
<%@page import="com.myapp.struts.hbm.Editmarc"%>
<%@page import="com.myapp.struts.marc.NewMARCDAO"  %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Existing MARC TAGS </title>
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
            <link rel="stylesheet" href="<%=request.getContextPath()%>/LibMS-Struts/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/LibMS-Struts/css/formstyle.css"/>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/cataloguing/animatedcollapse.js">
         
           
 </script>
<script type="text/javascript" >
     function back()
            {
                location.href="<%=request.getContextPath()%>/cataloguing/cat_editmarc.jsp";
            }
</script>
            
  <%--
    NewMARCDAO nmd=new NewMARCDAO();
List<Editmarc> data2modify= nmd.getMarc(tag); --%>
          
    </head>
    <body>
       
        <% String sb[]={"","","","",""} ;
           String sd[]= {"","","","",""} ;
           Boolean r[]= {true,true,true,true,true};
           String tagn="";
        long tagnr=0;
            if(request.getAttribute("edm")!=null){
            List<Editmarc> edm=(List<Editmarc>) request.getAttribute("edm");
           tagnr=edm.get(0).getId().getTagnumber();
            tagn=edm.get(0).getTagname();
           for(int f=0;f<edm.size();f++)
            {
            char s1=edm.get(f).getId().getSubsymbol();
            sb[f]=String.valueOf(s1);
            sd[f]=edm.get(f).getSubdescription();
            r[f]=edm.get(f).getRepeatable1();
            }
        }
     %>
       
        <table border="0" cellspacing="4" cellpadding="1" bgcolor="lightskyblue" width="60%" style="position: absolute; top: 23%; left: 20%">
            <tr><td colspan="3" height="30px" bgcolor="#E0E8F5" align="center" ><b>ADD NEW MARC TAG</b></td></tr>
          <font color="green">  </font>
            <html:form action="/modifymarc" method="post" >
                <tr><td align="center" >Tag Number</td><td ><input type="text" value="<%=tagnr %>" name="tagnumber" /></td><td></td></tr>
            <tr><td align="center">Tag Name</td><td ><input type="text" value="<%=tagn %>" name="tagname" /></td><td></td></tr>
            <tr><td align="center"><b>Subfield</b>   Symbol &nbsp;$<input type="text" value="a" name="subsymbol" readonly size="2" maxlength="1" /></td><td> Description <input type="text" name="subdesc" value="<%=sd[0] %>" maxlength="100" /></td><td>
            R <select name="repeatable1" >
                <option value="<%=r[0]%>" ><%=r[0]%></option>
            <option value="<%=!r[0]%>" ><%=!r[0]%></option>
            </select></td></tr>

            <tr><td colspan="3"><a href="javascript:animatedcollapse.toggle('toggle')">+</a>
                    <div id="toggle" style="width: 100%; background: lightskyblue; display:none" >
                        <table border="0" align="center" width="80%">
                            <th><b>More Subfields</b></th>
                            <tr><td>
                                     Symbol $<input type="text" value="<%=sb[1] %>" name="subsymbol1" size="2" maxlength="1" /></td><td>   Description <input type="text" name="subdesc1" value="<%=sd[1] %>" maxlength="100" /></td>
                                <td>R <select name="repeatable11" >
              <option value="<%=r[1]%>" ><%=r[1]%></option>
            <option value="<%=!r[1]%>" ><%=!r[1]%></option>
            </select></td></tr>

                            <tr><td> Symbol $<input type="text" value="<%=sb[2] %>" name="subsymbol2" size="2" maxlength="1" /></td> <td>  Description <input type="text" name="subdesc2" value="<%=sd[2] %>" maxlength="100" /></td>
                                <td>R <select name="repeatable12" >
               <option value="<%=r[2]%>" ><%=r[2]%></option>
            <option value="<%=!r[2]%>" ><%=!r[2]%></option>
            </select></td></tr>
                            <tr><td> Symbol $<input type="text" value="<%=sb[3] %>" name="subsymbol3" size="2" maxlength="1" /></td> <td>  Description <input type="text" name="subdesc3" value="<%=sd[3] %>" maxlength="100" /></td>
                                <td>R <select name="repeatable13" >
                <option value="<%=r[3]%>" ><%=r[3]%></option>
            <option value="<%=!r[3]%>" ><%=!r[3]%></option>
            </select></td></tr>
                            <tr><td> Symbol $<input type="text" value="<%=sb[4] %>" name="subsymbol4" size="2" maxlength="1" /></td> <td>  Description <input type="text" name="subdesc4" value="<%=sd[4] %>" maxlength="100" /></td>
                                <td>R <select name="repeatable14" >
               <option value="<%=r[4]%>" ><%=r[4]%></option>
            <option value="<%=!r[4]%>" ><%=!r[4]%></option>
            </select></td></tr>
            </table></div></td></tr>

            <tr><td align="center"><input type="submit" value="Save" name="btn" style="width: 150px;" /></td><td><input type="button" value="Cancel" name="cancel" onclick="back();" style="width: 150px;" /></td></tr>
           </html:form>
        </table>

<script type="text/javascript">

animatedcollapse.addDiv('toggle', 'fade=1,height=200px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}


animatedcollapse.init();

</script>
        </body>
</html>
