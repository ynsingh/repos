    <%-- 
    Document   : Admin
    Created on : Dec 12, 2012, 10:23:06 AM
    Author     : KESU
    
    *  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  Modified Date: 4 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
   GUI Modified date 21 July 2015, IITK , Om Prakash (omprakashkgp@gmail.com)

--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <%-- <link rel="stylesheet" type="text/css" href="css/LoginPage.css" /> --%>
               <link rel="stylesheet" type="text/css" href="css/AdministratorMainPage.css" />

           
        </head>
        <script type="text/javaScript">
                
                function loadIframe(iframeName, url) {
                    
                    parent.document.getElementById('ifrm').src = url;
                    if ( window.frames[iframeName] ) {
                        window.frames[iframeName].location = url;
                        return false;
                    }
                    return true;
                }
                // assign new url to iframe element's src property
            </script> 
        <body>

           <a4j:keepAlive beanName="UserBean" ajaxOnly="true"/> 
            
            <div class="container">
            
                <div class="wrapper">
            
                    <div class="header">

                        <h:graphicImage alt="payroll" url="/img/payrollheader.png" style="width:100%;"/>

                    </div>

              <div class="pageBody">
                        
                     
                   <div class="menu-wrapper">

                        <div id='cssmenu' class="align-center">
                            <ul>
                                <li><a href='http://202.141.40.215:8080/brihaspati/servlet/brihaspati' target="_blank">Brihaspati</a></li>
                                <li><a href='http://202.141.40.215/~brihaspati/BGAS/index.php/user/login' target="_blank">BGAS</a></li>
                               <li><a href='#'>PICO</a></li>
                              <%-- <li><a href='#'>Student Fee Management</a></li>--%>
                            </ul>
                        </div>
                        
                    </div>
    
                <h:form>
                <%-- <h:panelGrid > --%> 
                <%--   <h:graphicImage url="/img/payrollheader.png" width="100%" height="100%" /> --%>
             
                    <%--<rich:panel style="height:150px; width:1340px;">
                        <div  align="left">
                            <%--<h:graphicImage url="/img/pls1.png"/>--%>
                            <%--<h:graphicImage url="/img/payrollheader.png"/>
                        </div>
                        <div  align="right">
                            <%--<h:graphicImage url="/img/3_1.PNG" style="margin-top:-100px;"/>--%>
                            <%-- </div>
                    </rich:panel>--%>
                            <%--<rich:panel style="height:730px;font-size:19px;text-align:center;" id="list" header="Wellcome To Administrator Control Panel">
                        
                        <%-- <h:panelGrid columns="1"> --%>
                        <%--<rich:panel style="style=height:430px;font-size:17px;font-weight:bold;width:1300px;"> 
                                <%-- <h:panelGrid columns="20">--%>
                          <rich:toolBar width="100%;" height="25px" >
                                    <%-- <h:column> --%>
                                    <%-- <h:commandLink value="Setup" style="color : white " onclick="return loadIframe('ifrm', 'adminLogin/LeaveTypes.jsf')"/> --%>
                                    <rich:dropDownMenu id="set" value="Setup">
                                        <rich:menuItem value="Setup of Leave Types" onclick="return loadIframe('ifrm', 'adminLogin/LeaveTypes.jsf')" />
                                        <%-- <rich:menuItem value="Change Admin Password" onclick="return loadIframe('ifrm', 'adminLogin/ChangePassword.jsf')" /> --%>
                                    </rich:dropDownMenu>
                                       <%--   </h:column> --%>
                                    <%--  <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>--%>
                                    <%-- <h:column> --%>
                                <rich:dropDownMenu id="madmin" value="Manage Administrator " >
                                <rich:menuItem value="Add New Admin Email ID Config" onclick="return loadIframe('ifrm', 'adminLogin/AdminEmailConfig.jsf')"/>
                                <rich:menuItem   value="Add New Administrator" onclick="return loadIframe('ifrm', 'adminLogin/adminList.jsf')" />
                                <rich:menuItem value="SMTP Configuration" onclick="return loadIframe('ifrm', 'adminLogin/ServerList.jsf')" />
                                            <%-- <rich:menuItem value="Admin Profile " onclick="return loadIframe('ifrm', 'adminLogin/ServerList.jsf')" /> --%>
                                </rich:dropDownMenu>    
                                        <%-- <h:commandLink value="Add New Administrator" onclick="return loadIframe('ifrm', 'adminLogin/adminList.jsf')"/>--%>
                                        <%-- </h:column> --%>
                                    <%--<h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>--%>
                                    <%-- <h:column>
                                        <h:commandLink value="Add New Admin EmailID Config" onclick="return loadIframe('ifrm', 'adminLogin/AdminEmailConfig.jsf')"/>
                                    </h:column>--%>
                                    <%-- <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>--%>
                                    <%--    <h:column> --%>
                                <rich:dropDownMenu id="IMang" value="Institute Management" >
                                <rich:menuItem value="Check Registered College/Institute's" onclick="return loadIframe('ifrm', 'adminLogin/CollegeList.jsf')"/>
                                <rich:menuItem value="Check Pending College/Institute's" onclick="return loadIframe('ifrm', 'adminLogin/PendingCollegeList.jsf')"/> 
                                        <%-- <h:commandLink value="Check Registered College/Institute's" onclick="return loadIframe('ifrm', 'adminLogin/CollegeList.jsf')"/>--%>
                                </rich:dropDownMenu>
                                    <%--  </h:column> --%>
                                    <%-- <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>--%>
                                    <%--<h:column>
                                        <h:commandLink value="Check Pending College/Institute's" onclick="return loadIframe('ifrm', 'adminLogin/PendingCollegeList.jsf')"/>
                                    </h:column>--%>
                                    <%-- <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column> --%>
                                    <%--    <h:column> --%>
                                    <%--<h:commandLink style="color : white" value="Change Admin Password" onclick="return loadIframe('ifrm', 'adminLogin/ChangePassword.jsf')"/>--%>
                                    <rich:dropDownMenu id="cap" value="Change Password">
                                        <rich:menuItem value="Change Admin Password" onclick="return loadIframe('ifrm', 'adminLogin/ChangePassword.jsf')" />
                                    </rich:dropDownMenu>
                                        <%-- </h:column> --%>
                                    <%-- <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column> --%>
                                    <%-- <h:column>
                                        <h:commandLink value="Server Configuration" onclick="return loadIframe('ifrm', 'adminLogin/ServerList.jsf')"/>
                                    </h:column>--%>
                                    <%-- <h:column>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </h:column> --%>
                                    <%--<h:graphicImage id="im" rendered="#{adminManagedBean.imgerDraw}" value="/img/new.gif"/> --%>
                                <h:form>
                                <rich:toolTip for="im" value="Some College Are Wating For Your Approval"/>
                                <h:commandButton id="lout"  value="Logout"  action="#{adminManagedBean.logout}" />
                                        <%--  <h:commandButton id="lout"  image="/img/lout.gif" action="#{adminManagedBean.logout}" />--%>
                                <rich:toolTip for="lout" value="Click Here For Log Out"/>
                                </h:form>
                                </rich:toolBar> 
                                <%--  </h:form> --%>
                                <%-- </h:panelGrid> --%>
                                <%-- </rich:panel> --%>
                                <%--  <rich:separator/>--%>
                                <iframe id="ifrm" src="adminLogin/CollegeList.jsf" style="border:none;" height="400" width="1310"></iframe> 
                                <%--  <rich:panel>
                                </rich:panel>
                                <%-- </h:panelGrid> --%>
                                <%-- </rich:panel> --%>
                                <%-- </h:panelGrid> --%>
                    </h:form>
                <rich:modalPanel id="adm"> 
                            
                    <h:form>
                        <rich:panel header="Add New Administrator">
                            
                        </rich:panel>
                    </h:form>
                </rich:modalPanel>
                <rich:modalPanel id="actiAdm">
                    <h:form>
                        <rich:panel header="Active/Inactive Administrator">

                        </rich:panel>
                    </h:form>
                </rich:modalPanel> 
                                           
                 </div> <%-- end of pageBody --%>
              
                    <div class="footer">
                    <div class="footer-content">
                
                        <p> Opensource component developed by IIT Kanpur, Initially was supported by MHRD.</p>
                        <p> For problems at this site send email to ETRG, IIT Kanpur
                            For reporting bugs, suggestion, feature request, and maintainence support
                            post at brihaspati_ERP_mission@yahoogroups.com</p>
                       
                        <p class="copyright"> © 2015 PayrollSys IITK.</p>
                   </div>   
                   </div> <!-- end footer-->    
            
                </div> <!-- end Wrapper -->                
            </div> <!-- end Container -->                 
        </body>
    </html>
</f:view> 