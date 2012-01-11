<%@page contentType="text/html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page pageEncoding="UTF-8"%>
<html><head>
    </head>
    <body  >
        <script>
            function showdiv(){

        var ele = document.getElementById("image1");


	if(ele.style.display == "block") {
    		ele.style.display = "none";

  	}
	else {
		ele.style.display = "block";

	}


    }
        </script>
        <link rel="StyleSheet" href="<%=request.getContextPath()%>/css/page.css"/>
<jsp:include page="header.jsp" flush="true" />
<div
   style="
      top: 14%;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">


      



    <table width="100%" height="600px" valign="top" style="" >
        <tr><td valign="top" width="90%" style="">
                <br/>  <a href="#" onclick="javascript:showdiv();"> Add Insti Logo</a>
 <div id="image1"
   style="  top:130px;background: red;
   left:30%;
   overflow: hidden;
      position: absolute;
      display: none;"

      >


   <html:form action="/admin/instilogo" method="post" styleId="form1" enctype="multipart/form-data">


    <table class="table" style="border:5px solid cyan;" align="center" height="150px" width="500px">
           <tr><td width="250px" laign="center">Select Image:
 
<html:file  property="img" name="ImageUploadActionForm" styleId="img"    />
      <input type="hidden" name="filename" tabindex="16" id="filename" />
         <input type="submit" value="Upload Image"/>

</html:form>
               </td><%--<td align="center">
                   <%if (request.getAttribute("imagechange")!=null){%>
                        <html:img src="LibMS/admin/logo.jsp"  alt="no Image Selected" width="120" height="120"/>
                        <%}else{%>
                        <html:img src="LibMS/admin/viewimage.jsp" width="128" height="120" />
                        <%}%>

               </td>--%></tr></table>
         </div>

                     



            </td>               <td   valign="top">
                </td> </tr>

                </table>


                </div>


      </body>
</html>