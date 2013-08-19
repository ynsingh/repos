<%-- 
    Document   : Admin
    Created on : Dec 12, 2012, 10:23:06 AM
    Author     : KESU
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
                    <rich:panel style="height:170px; width:1340px;">
                        <div  align="left">
                            <h:graphicImage url="/img/pls1.png"/>
                        </div>
                        <div  align="right">
                            <h:graphicImage url="/img/3_1.PNG" style="margin-top:-100px;"/>
                        </div>
                    </rich:panel>
                    <rich:panel style="height:430px;font-size:19px;text-align:center;" id="list" header="Wellcome To Administrator Control Panel">
                        
                        <h:panelGrid columns="1">
                            <rich:panel style="style=height:430px;font-size:17px;font-weight:bold;width:1200px;">
                                <h:panelGrid columns="22">
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