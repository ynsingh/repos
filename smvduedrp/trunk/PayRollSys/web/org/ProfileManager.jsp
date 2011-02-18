<%-- 
    Document   : ProfileManager
    Created on : Dec 25, 2010, 1:52:58 PM
    Author     : Algox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
       <h:panelGrid columns="2" columnClasses="top,top">
            <rich:fileUpload fileUploadListener="#{fileUploadBean.listener}"
                maxFilesQuantity="#{fileUploadBean.uploadsAvailable}"
                id="upload"
                immediateUpload="#{fileUploadBean.autoUpload}"
                acceptedTypes="jpg, gif, png, bmp" allowFlash="#{fileUploadBean.useFlash}">
                <a4j:support event="onuploadcomplete" reRender="info" />
            </rich:fileUpload>
            <h:panelGroup id="info">
                <rich:panel bodyClass="info">
                    <f:facet name="header">
                        <h:outputText value="Uploaded Files Info" />
                    </f:facet>
                    <h:outputText value="No files currently uploaded"
                        rendered="#{fileUploadBean.size==0}" />
                    <rich:dataGrid columns="1" value="#{fileUploadBean.files}"
                        var="file" rowKeyVar="row">
                        <rich:panel bodyClass="rich-laguna-panel-no-header">
                            <h:panelGrid columns="2">
                                <a4j:mediaOutput element="img" mimeType="#{file.mime}"
                                    createContent="#{fileUploadBean.paint}" value="#{row}"
                                    style="width:100px; height:100px;" cacheable="false">
                                    <f:param value="#{fileUploadBean.timeStamp}" name="time"/>
                                </a4j:mediaOutput>
                                <h:panelGrid columns="2">
                                    <h:outputText value="File Name:" />
                                    <h:outputText value="#{file.name}" />
                                    <h:outputText value="File Length(bytes):" />
                                    <h:outputText value="#{file.length}" />
                                </h:panelGrid>
                            </h:panelGrid>
                        </rich:panel>
                    </rich:dataGrid>
                </rich:panel>
                <rich:spacer height="3"/>
                <br />
                <a4j:commandButton action="#{fileUploadBean.clearUploadData}"
                    reRender="info, upload" value="Clear Uploaded Data"
                    rendered="#{fileUploadBean.size>0}" />
            </h:panelGroup>
        </h:panelGrid>
                </f:view>
    </body>
</html>
