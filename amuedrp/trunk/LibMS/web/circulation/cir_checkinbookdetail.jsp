<%-- 
    Document   : checkinbookdetail
    Created on : Mar 22, 2011, 4:15:51 AM
    Author     : edrp01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/admin/header.jsp" flush="true" /> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%



%>
    </head>
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    <body>
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <html:form   action="/circheckinbookdetail" method="post">

         <table width="600px"   valign="top" align="center" class="table">

  <tr><td   class="headerstyle" bgcolor="#E0E8F5" height="25px"  align="center">



          <b>Book Return Checkin Process</b>





        </td></tr>
 
        <tr><td   width="600px" height="200px" valign="top" style="" align="center">
                <br>
                <table cellspacing="10px">

                    <tr>
                        <td class="txt2">Member ID</td>
                        <td>
                            <html:text    property="TXTMEMID" readonly="true" style="width:160px" />
                        </td>
                        <td class="txt2">Member Name</td>
                        <td>
                            <html:text    property="TXTMEMNAME"  readonly="true" style="width:160px" />
                        </td>
                    </tr>
                    <tr>
                        <td class="txt2">Accession No</td>
                        <td>
                            <html:text    property="TXTACCESSION"  readonly="true" style="width:160px" />
                        </td>
                        <td class="txt2">Title</td>
                        <td>
                           <html:text    property="TXTTITLE" style="width:160px" readonly="true"/>
                        </td>
                        
                    </tr>
                    <tr>
                        <td class="txt2">Author</td>
                        <td>
                           <html:text    property="TXTAUTHOR" style="width:160px" readonly="true"/>
                        </td>
                        <td class="txt2">Call No</td>
                        <td>
                           <html:text    property="TXTCALL" style="width:160px" readonly="true" />
                        </td>

                    </tr>
                    <tr>
                        <td class="txt2">Due Date</td>
                        <td>
                           <html:text    property="TXTDUEDATE" style="width:160px" readonly="true"/>
                        </td>
                        <td class="txt2">Returning Date</td>
                        <td>
                           <html:text    property="TXTRETURNINGDATE" style="width:160px" readonly="true" />
                        </td>

                    </tr>
                    <tr>
                        <td class="txt2">Fine</td>
                        <td>
                           <html:text    property="TXTFINE" style="width:160px" readonly="true"/>
                        </td>
                        <td class="txt2">Delayed</td>
                        <td>
                           <html:text    property="TXTDELAYED" style="width:160px" readonly="true"/>
                        </td>

                    </tr>
                    
 <tr>
                        <td class="txt2">Damaged</td>
                        <td>
                            <html:select property="TXTDAMAGEDSTATUS" styleId="damaged" style="width:160px" onchange="return showReason();">
                                <html:option value="Yes">Yes</html:option>
                                <html:option value="No">No</html:option>


                            </html:select>
                        </td>
                        <td class="txt2">Loss Status</td>
                        <td>
                           <html:select property="TXTLOSSSTATUS" style="width:160px">
                                <html:option value="Yes">Yes</html:option>
                                <html:option value="No">No</html:option>


                            </html:select>
                        </td>

                    </tr>
            
                    <tr>
                        <td class=txt2>
                         <div id="txtreason" style="visibility: hidden;">
                 </div></td>
                        <td>
                            <div id="reasonEnabled" style="visibility: hidden;">
                                <html:text    property="TXTREASON" style="width:160px" />
                            </div>
                        </td>
                       
                    </tr>
                    


                </table>












</td></tr>
        <tr><td align="center">


                            <input type="submit" class="btn" id="Button1" name="button" value="Check In" />
                            <input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return back()"/>
                


            </td></tr>
         </table>

                        <br><br>


 </html:form>

</div>
</body>
 <script language="javascript" type="text/javascript">

   function back()
    {

        location.href="<%=request.getContextPath()%>/circulation/cir_checkin.jsp";
    }

function showReason()
{
    
    var divreason = document.getElementById("reasonEnabled");
    if(document.getElementById("damaged").value == "Yes")
        {

        document.getElementById("txtreason").style.visibility= "visible";
        document.getElementById("txtreason").innerHTML = "Reason";
        document.getElementById("reasonEnabled").style.visibility= "visible";
        

        }
        else
            {
             document.getElementById("txtreason").innerHTML = "";
            document.getElementById("reasonEnabled").style.visibility= "hidden";
            document.getElementById("txtreason").style.visibility= "hidden";
            }
            return true;
}
   


</script>

</html>
