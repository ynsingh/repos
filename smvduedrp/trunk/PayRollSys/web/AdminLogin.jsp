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
            <h:form>
                <h:panelGrid columns="1">
                    <rich:panel style="height:150px; width:1340px;">
                        <div  align="left">
                            <%--<h:graphicImage url="/img/pls1.png"/>--%>
                            <h:graphicImage url="/img/payrollheader.png"/>
                        </div>
                        <div  align="right">
                            <%--<h:graphicImage url="/img/3_1.PNG" style="margin-top:-100px;"/>--%>
                        </div>
                    </rich:panel>
                    <rich:panel style="height:730px;font-size:19px;text-align:center;" id="list" header="Wellcome To Administrator Control Panel">
                        
                        <h:panelGrid columns="1">
                            <rich:panel style="style=height:430px;font-size:17px;font-weight:bold;width:1200px;">
                                <h:panelGrid columns="23">
                                   <h:column>
                                        <h:commandLink value="Setup" onclick="return loadIframe('ifrm', 'adminLogin/LeaveTypes.jsf')"/>
                                    </h:column>
                                   <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:commandLink value="Add New Administrator" onclick="return loadIframe('ifrm', 'adminLogin/adminList.jsf')"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:commandLink value="Add New Admin EmailID Config" onclick="return loadIframe('ifrm', 'adminLogin/AdminEmailConfig.jsf')"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:commandLink value="Check Registered College/Institute's" onclick="return loadIframe('ifrm', 'adminLogin/CollegeList.jsf')"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:commandLink value="Check Pending College/Institute's" onclick="return loadIframe('ifrm', 'adminLogin/PendingCollegeList.jsf')"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:commandLink value="Change Admin Password" onclick="return loadIframe('ifrm', 'adminLogin/ChangePassword.jsf')"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value=""/>
                                    </h:column>
                                    <h:column>
                                        <h:commandLink value="Server Configuration" onclick="return loadIframe('ifrm', 'adminLogin/ServerList.jsf')"/>
                                    </h:column>
                                    <h:column>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </h:column>
                                    <h:graphicImage id="im" rendered="#{adminManagedBean.imgerDraw}" value="/img/new.gif"/>
                                    <rich:toolTip for="im" value="Some College Are Wating For Your Approval"/>
                                    <h:form>
                                        <h:commandButton id="lout"  image="/img/lout.gif" action="#{adminManagedBean.logout}" />
                                        <rich:toolTip for="lout" value="Click Here For Log Out"/>
                                    </h:form>
                                        
                                </h:panelGrid>
                            </rich:panel>
                            <rich:separator/>
                            <iframe id="ifrm" style="border:none;" height="400" width="1100">
                            <rich:panel>
                                 
                            </rich:panel>
                        </h:panelGrid>
                    </rich:panel>
                </h:panelGrid>
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
            </h:form>
        </body>
    </html>
</f:view>