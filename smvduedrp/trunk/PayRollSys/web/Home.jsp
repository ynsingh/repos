<%-- 
    Document   : MyUpdates
    Created on : Dec 9, 2010, 3:59:55 PM
    Author     : Algox
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <f:view>
        <rich:panel style="height:400px;width:100%;" header="Payroll System | General Instructions">
            <rich:panel  style="width:100%;">
            <h:panelGrid columns="2">
            <h:outputText value="Developed At : SMVDU"/>
           
            <h:outputText value="Last Updated On : 12/01/11 10:40 AM "/>
           
            <h:outputText value="Last Updated By  : Developer Team At Sri Mata Vaishno Devi University  "/>
            
            <h:outputText value="Contact Details  : Sushant Kumar ,sushant001@gmail.com "/>
            </h:panelGrid>
        </rich:panel>
            <h:panelGrid columns="2">
                <rich:panel style="width:100%;height:700px;" header="User Guide">
                <ul type="none">
                     <li> <h3>Setup</h3> </li>
                        <ul>
                            <li>Setups provide Creating / Updating various elements that is required throughout the application.</li>
                            <li>All Setup elements have Add New Button at the Top left to add new element.</li>
                            <li>All Setup elements have ---- marked elements, which turns editable once clicked.
                                Click Update button when all such editing is done</li>
                            <li>Setups provide Creating / Updating various elements that is required throughout the application.</li>
                        </ul>
                     <li><h3>Employee profile</h3></li>
                        <ul>
                            <li>Once setup is done, Add employee profiles one by one, configuring all the required details</li>
                            <li>To edit profile, Enter Employee's code and press load. Update details and finally click Update</li>
                            <li>Searching employee can be done based on various fields.</li>
                        </ul>
                     <li><h3>Salary</h3></li>
                        <ul>
                            <li>Once setup is done, Add employee profiles one by one, configuring all the required details</li>
                            <li>To edit profile, Enter Employee's code and press load. Update details and finally click Update</li>
                            <li>Searching employee can be done based on various fields.</li>
                        </ul>
                 </ul>
                       
            </rich:panel>
            <rich:panel style="width:100%;height:700px;" header="Known Bugs">
                <ul type="none">
                     <li> <h3>Salary Formula</h3> </li>
                       Define Salary Heads in order , and accuracy of calculation of Formula based Heads
                       depends on the order of definition. Meaning, if Head B depends on Head A, then B must
                       come first in Salary Head / Salary Formula than A.
                    
                 </ul>

            </rich:panel>
         </h:panelGrid>
        </rich:panel>
        

        
    </f:view>
</html>
