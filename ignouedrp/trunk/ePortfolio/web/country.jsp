

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib  prefix="sd" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head/>

    </head>
    <body>

        <s:form id="StudentReg" >
            <s:url id="CountId" action="CountryAction" namespace="/Dropdown"/> 
            <s:url id="CityId" action="CityAction" namespace="/Dropdown"/>                    
            <sj:select 
                href="%{CountId}" 
                id="CountryCode" 
                onChangeTopics="reloadcitylist" 
                name="Code" 
                list="countryMap" 
                emptyOption="false" 
                headerKey="-1" 
                headerValue="Please Select Country"
                label="Country"
                sortable="true"
                />
            <sj:select 
                href="%{CityId}" 
                id="CityName" 
                formIds="StudentReg" 
                reloadTopics="reloadcitylist" 
                name="city" 
                list="cityMap" 
                emptyOption="false" 
                headerKey="-1" 
                headerValue="Please Select City"
                label="City"
                sortable="true"
                />
        </s:form>


    </body>
</html>
