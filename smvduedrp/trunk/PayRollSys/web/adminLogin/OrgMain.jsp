<%--
    Document        : OrgMain.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
*  Copyright (c) 2010 - 2011.2014 SMVDU, Katra.
*  Copyright (c) 2014 - 2016 ETRG, IITK.
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
*   Author          : Saurabh Kumar
*   GUI Modification : 20 June 2015, Om Prakash (omprakashkgp@gmail.com), IITK
*   Modified   : 13:00 PM Saturday, August 13, 2016
*   Last Modified : November, 2016, Om
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="../css/RegistrationPage.css" /> 
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Add New Company / Institute</title>
        </head>
        <body>
            <div class="container">
            
            <div class="wrapper">
            
                <div class="header">

                    <h:graphicImage alt="payroll" url="/img/payrollheader.png" style="width:100%;"/>

                </div>
                    
                    
                <div class="pageBody">
                    
                    <div class="menu-wrapper">

                        <div id='cssmenu' class="align-center">
                            <ul>
                                <li><a href="../Login.jsf"> Login Page </a></li>
                                <li><a href='http://brihaspati.nmeict.in/brihaspati/servlet/brihaspati' target="_blank">Brihaspati</a></li>
                                <li><a href='http://brihaspati.nmeict.in/~brihaspati/BGAS/index.php/user/login' target="_blank">BGAS</a></li>
                              <%-- <li><a href='http://202.141.40.218:8081/pico/Administration/Index.action'>PICO</a></li>
                                 <li><a href='#'>Student Fee Management</a></li>--%>
                            </ul>
                        </div>
                    </div>
                    
                <div class="registrationForm">    
                
                <h:form id="frmTest">

                    <%--</rich:panel>--%>
                    <div id="msg">
                        <rich:messages>
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                                <h:outputText value="kk"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                                <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                     </div>    
                    <div class="form_lables">
                        <label>
                                    <span>Institute Name :</span>
                                    <h:inputText label=" instName" requiredMessage="Institute Name can not be empty" required="true" value="#{OrgProfileBean.name}" />                     
                        </label>
                                    <%--            <h:column><h:outputText value="Institute Name"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column><h:inputText id="instName" requiredMessage="Name cannot be empty" required="true" value="#{OrgProfileBean.name}"/></h:column>
                        <h:column><h:message for="instName" /></h:column>
                                    --%>


                        <%--           <h:outputText value="Institute Tag Line" />
                                   <h:inputText id="insttag" value="#{OrgProfileBean.tagLine}"/>
                                   <h:message for="insttag" />    --%>
                        
                        <label>
                                    <span>Institute Address :</span>
                                    <h:inputText id="insta1" requiredMessage="Institute Address can not be empty " required="true"  value="#{OrgProfileBean.address1}"/>
                        </label>
                                    <%--        <h:column>   <h:outputText value="Institute Address"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>   <h:inputText id="insta1" value="#{OrgProfileBean.address1}"/></h:column>
                        <h:column>   <h:message for="insta1" /></h:column>
                                    --%>

                        <%--               <h:outputText value="Institute Address(Line 2)"/>
                                      <h:inputText id="insta2" value="#{OrgProfileBean.address2}"/>
                                      <h:message for="insta2" />    --%>
                        
                        
                        <label>
                                    <span>City :</span>
                                    <h:inputText id="instc" requiredMessage="City can not be empty " required="true" value="#{OrgProfileBean.city}"/>
                        </label>
                                    <%--        <h:column>    <h:outputText value="City"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>    <h:inputText id="instc" value="#{OrgProfileBean.city}"/></h:column>
                        <h:column>    <h:message for="instac" /></h:column>
                        
                                    --%>
                        <label>
                                    <span>Pincode :</span>
                                    <h:inputText id="instpc" requiredMessage="Pincode can not be empty " required="true" value="#{OrgProfileBean.pincode}"/>  <h:outputText value="e.g. For IIT Kanpur (208016)" />
                        </label>
                                    <%--            <h:column>    <h:outputText value="Pincode"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>    <h:inputText id="instpc" value="#{OrgProfileBean.pincode}"/></h:column>
                        <h:column>    <h:message for="instpc" /></h:column>
                                    --%>
                        
                        <label>
                                    <span>State / Territory  :</span>
                                    <%--  <h:inputText id="insts" requiredMessage="State cannot be empty" required="true"  value="#{OrgProfileBean.state}"/>--%>
                                <h:selectOneMenu requiredMessage="State can not be empty " required="true" value="#{OrgProfileBean.state}">
                                <f:selectItem itemLabel="--Select State--" itemValue="" />        
                                <f:selectItem itemLabel="Andhra Pradesh" itemValue="Andhra Pradesh" />
                                <f:selectItem itemLabel="Andaman and Nicobar Islands" itemValue="Andaman and Nicobar Islands" />
                                <f:selectItem itemLabel="Assam" itemValue="Assam" />
                                <f:selectItem itemLabel="Arunachal Pradesh" itemValue="Arunachal Pradesh" />
                                <f:selectItem itemLabel="Bihar " itemValue="Bihar " />
                                <f:selectItem itemLabel="Chandigarh" itemValue="Chandigarh" />
                                <f:selectItem itemLabel="Chhattisgarh" itemValue="Chhattisgarh" />
                                <f:selectItem itemLabel="Delhi" itemValue="Delhi"/>
                                <f:selectItem itemLabel="Dadra and Nagar Haveli" itemValue="Dadra and Nagar Haveli" />
                                <f:selectItem itemLabel="Daman and Diu" itemValue="Daman and Diu" />
                                <f:selectItem itemLabel="Gujarat" itemValue="Gujarat" />
                                <f:selectItem itemLabel="Goa" itemValue="Goa" />
                                <f:selectItem itemLabel="Himachal Pradesh" itemValue="Himachal Pradesh" />
                                <f:selectItem itemLabel="Haryana" itemValue="Haryana" />
                                <f:selectItem itemLabel="Jammu and Kashmir" itemValue="Jammu and Kashmir" />
                                <f:selectItem itemLabel="Jharkhand" itemValue="Jharkhand" />
                                <f:selectItem itemLabel="Karnataka" itemValue="Karnataka" /> 
                                <f:selectItem itemLabel="Kerala" itemValue="Kerala" />
                                <f:selectItem itemLabel="Lakshadweep (Islands)" itemValue="Lakshadweep (Islands)" />
                                <f:selectItem itemLabel="Maharastra" itemValue="Maharastra" />
                                <f:selectItem itemLabel="Madhya Pradesh" itemValue="Madhya Pradesh" />
                                <f:selectItem itemLabel="Meghalaya" itemValue="Meghalaya" />
                                <f:selectItem itemLabel="Manipur" itemValue="Manipur" />
                                <f:selectItem itemLabel="Mizoram" itemValue="Mizoram" />
                                <f:selectItem itemLabel="Nagaland" itemValue="Nagaland" />
                                <f:selectItem itemLabel="Odisha" itemValue="Odisha" />
                                <f:selectItem itemLabel="Punjab" itemValue="Punjab" />
                                <f:selectItem itemLabel="Puducherry (Pondicherry)" itemValue="Puducherry (Pondicherry)" />
                                <f:selectItem itemLabel="Rajasthan" itemValue="Rajasthan" />
                                <f:selectItem itemLabel="Sikkim" itemValue="Sikkim" />
                                <f:selectItem itemLabel="Telangana" itemValue="Telangana" />
                                <f:selectItem itemLabel="Tamil Nadu" itemValue="Tamil Nadu" />
                                <f:selectItem itemLabel="Tripura" itemValue="Tripura" />
                                <f:selectItem itemLabel="Uttarakhand" itemValue="Uttarakhand" />
                                <f:selectItem itemLabel="Uttar Pradesh" itemValue="Uttar Pradesh" />
                                <f:selectItem itemLabel="West Bengal" itemValue="West Bengal" />
                         </h:selectOneMenu> 

                        </label>
                                    <%--            <h:column>     <h:outputText value="State"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>     <h:inputText id="insts" requiredMessage="State cannot be empty" required="true"  value="#{OrgProfileBean.state}"/></h:column>
                        <h:column>     <h:message for="insts" /></h:column>
                                    --%>
                                    <%--
                        <label>
                                    <span>Landline No.:</span>
                                    <h:inputText id="instln" value="#{OrgProfileBean.ll}"/>
                        </label>--%>
                                    <%--            <h:column>    <h:outputText value="Landline No."/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>    <h:inputText id="instln" value="#{OrgProfileBean.ll}"/></h:column>
                        <h:column>    <h:message for="instln" /></h:column>
                                    --%>
                        
                        <label>
                                    <span>Country Code :</span>
                                    <h:inputText id="instcc" requiredMessage="Country Code can not be empty " required="true" value="#{OrgProfileBean.countryCode}"/>
                                   
                        </label>
                                    <%--            <h:outputText value="Country Code"/>
                        <h:inputText id="instcc" value="#{OrgProfileBean.countryCode}"/>
                        <h:message for="instcc" />
                                    --%>
                                    <%--
                        <label>
                                    <span>Region Code :</span>
                                    <h:inputText id="instrc" value="#{OrgProfileBean.regionCode}"/>
                        </label>--%>
                                    <%--            <h:column>                <h:outputText value="Region Code"/><h:outputText value="*    Ex. For Jammu(0191)" style="color:red;"/></h:column>
                        <h:column>                <h:inputText id="instrc" value="#{OrgProfileBean.regionCode}"/></h:column>
                        <h:column>                 <h:message for="instrc" /></h:column>
                                    --%>
                                     
                        <label>
                                    <span>Phone Number :</span>
                                    <h:inputText id="instph" requiredMessage="Phone Number can not be empty" required="true"   value="#{OrgProfileBean.phone}"/>
                                    <h:outputText value="( Area Code + Landline / mobile ) e.g. 512 2597944 / 7000002422  " />
                        </label>
                                    
                                    <%--            <h:column>     <h:outputText value="Number"/><h:outputText value="*    Ex. For SMVDU(2470067)" style="color:red;"/></h:column>
                        <h:column>     <h:inputText id="instph" requiredMessage="Number cannot be empty" required="true"   value="#{OrgProfileBean.phone}"/></h:column>
                        <h:column>     <h:message for="instph" /></h:column>
                                    --%>
                        
                        <label>
                                    <span>Institute Domain :</span>
                                    <h:inputText id="instidn" requiredMessage="Institute Domain can not be empty" required="true"  value= "#{OrgProfileBean.instDomain}"/>
                                    <h:outputText value="( For IIT Kanpur ) e.g.  iitk.ac.in "/>
                        </label>
                                    <%--            <h:column>     <h:outputText value="Institute Domain"/><h:outputText value="*  @" style="color:red;"/></h:column>
                        <h:column>     <h:inputText id="instidn" requiredMessage="Domain cannot be empty" required="true"  value= "#{OrgProfileBean.instDomain}"/></h:column>
                        <h:column>     <h:message for="instidn" /></h:column>
                                    --%>
                        
                        <label>
                                    <span>Type of Institute :</span>
                                    <h:selectOneMenu value="#{OrgProfileBean.toi}">
                                        <f:selectItem itemLabel="Govt." itemValue="Govt."/>
                                        <f:selectItem itemLabel="Semi-Govt" itemValue="Semi-Govt"/>
                                        <f:selectItem itemLabel="Self-financed" itemValue="Self-financed"/>
                                        <f:selectItem itemLabel="Other" itemValue="Other"/>
                                    </h:selectOneMenu>
                        </label>
                        <%--            <h:outputText value="Type of Institute"/>
                        <h:selectOneMenu value="#{OrgProfileBean.toi}">
                            <f:selectItem itemLabel="Govt." itemValue="Govt."/>
                            <f:selectItem itemLabel="Semi-Govt" itemValue="Semi-Govt"/>
                            <f:selectItem itemLabel="Self-financed" itemValue="Self-financed"/>
                            <f:selectItem itemLabel="Other" itemValue="Other"/>
                        </h:selectOneMenu>
                        <h:message for="insttoi" />
                                    --%>
                        
                        <label>
                                    <span>Institute Website :</span>
                                    <h:inputText id="instweb" requiredMessage="Institute Website can not be empty" required="true"  value="#{OrgProfileBean.web}"/>
                                    <h:outputText value="e.g.  www.iitk.ac.in " />
                        </label>
                                    <%--            <h:column>                <h:outputText value="Institute Website"/><h:outputText value="* http://" style="color:red;"/></h:column>
                        <h:column>               <h:inputText id="instweb" requiredMessage="Website cannot be empty" required="true"  value="#{OrgProfileBean.web}"/></h:column>
                        <h:column>               <h:message for="instweb" /></h:column>
                                    --%>
                        
                        <label>
                                    <span>Affiliation :</span>
                                    <h:inputText id="instan" value="#{OrgProfileBean.affiliation}"/> 
                                    <h:outputText value="( University Name ) e.g. UPTU, CSJMU , If no affiliation leave it blank. " />
                        </label>
                       
                        <label> Please fill the Institute Administrator Details </label> 
                                    <%--            <h:column>       <h:outputText value="Affiliation"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>       <h:inputText id="instan" value="#{OrgProfileBean.affiliation}"/></h:column>
                        <h:column>       <h:message for="instan" /></h:column>
                                    --%> 
                        
                        <label>
                                    <span> First Name :</span>
                                    <h:inputText id="instafn" requiredMessage="First name can not be empty " required="true" value="#{OrgProfileBean.adminfn}"/>
                                    
                        </label>
                                    <%--            <h:column>       <h:outputText value="Admin's First Name"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>       <h:inputText id="instafn" value="#{OrgProfileBean.adminfn}"/></h:column>
                        <h:column>       <h:message for="instafn" /></h:column>
                                    --%>
                        
                         <label>
                                    <span> Last Name :</span>
                                    <h:inputText id="instaln" requiredMessage="Last name can not be empty" required="true" value="#{OrgProfileBean.adminln}"/>
                        </label>
                                    <%--            <h:column>      <h:outputText value="Admin's Last Name"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputText id="instaln" value="#{OrgProfileBean.adminln}"/></h:column>
                        <h:column>      <h:message for="instaln" /></h:column>
                                    --%>
                        <label>
                                    <span> Designation :</span>
                                    <h:inputText id="instd" requiredMessage="Designation can not be empty" required="true" value="#{OrgProfileBean.adminDesig}"/>
                        </label>
                                    <%--            <h:column>      <h:outputText value="Admin's Designation"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputText id="instd" value="#{OrgProfileBean.adminDesig}"/></h:column>
                        <h:column>      <h:message for="instd" /></h:column>
                                    --%>
                        
                        <label>
                                    <span> E-Mail ID :</span>
                                    <h:inputText id="instrec" requiredMessage="Email can not be empty" required="true" value="#{OrgProfileBean.email}"/>
                                    <h:outputText value="e.g. mprakash@iitk.ac.in " />
                        </label>
                                    <%--            <h:column>      <h:outputText value="Admin's E-Mail ID"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputText id="instrec" requiredMessage="E mail cannot be empty" required="true" value="#{OrgProfileBean.email}"/></h:column>
                        <h:column>      <h:message for="instrec" /></h:column>
                                    --%>
                        <label>
                                    <span> Password :</span>
                                    <h:inputSecret id="instpass" requiredMessage="Password can not be empty" required="true" value="#{OrgProfileBean.masterPassword}"/>
                        </label>
                                    <%--            <h:column>      <h:outputText value="Admin's Password"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputSecret id="instpass" requiredMessage="Password Cant be Empty" required="true" value="#{OrgProfileBean.masterPassword}"/></h:column>
                        <h:column>      <h:message for="instpass" /></h:column>
                                    --%>
                        <label>
                                    <span> Re type Password :</span>
                                    <h:inputSecret id="instpass2" requiredMessage="Re type password can not empty " required="true" value="#{OrgProfileBean.vpass}"/>
                        </label>
                                    <%--            <h:column>      <h:outputText value="Admin's Re type Password"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputSecret id="instpass2" requiredMessage="Retype Password Cant be Empty" required="true" value="#{OrgProfileBean.vpass}"/></h:column>
                        <h:column>      <h:message for="instpass2" /></h:column>  
                                    --%>


                        <%--                         <h:outputText value="Recovery E-Mail"/>
                                               <h:inputText id="instmail" value="#{OrgProfileBean.email}"/>
                                               <h:message for="instmail" />     --%>

                        <%--             <h:column>        <h:outputText value="Tan No."/><h:outputText value="*" style="color:red;"/></h:column>
                               <h:column>        <h:inputText id="tanno" value="#{OrgProfileBean.tanno}"/></h:column>
                               <h:column>        <h:message for="tanno" /></h:column>                                      --%>


                    
                    <label>
                                <span>&nbsp;</span>
                                <a4j:commandButton value="Save" reRender="up" action="#{OrgProfileBean.save}" styleClass="button"/>&nbsp;
                                <a4j:commandButton value="Reset" onclick="this.form.reset()" styleClass="button"/>
                    </label>    
                        
                        <%--            <h:outputText value=""/>
                    <span>&nbsp;</span>


                    <a4j:commandButton value="Save" reRender="up" action="#{OrgProfileBean.save}" styleClass="button"/>&nbsp;
                    <a4j:commandButton value="Reset" onclick="this.form.reset()" styleClass="button"/>
                        --%>
                    </div>    
                        
                        
                    </h:form>
                    
                    </div>
                </div>
                    
                    <div class="footer">
                    <div class="footer-content">
                
                        <p> Opensource component developed by IIT Kanpur, Initially was supported by MHRD</p>
                        <p> For problems at this site send email to ETRG, IIT Kanpur
                            For reporting bugs, suggestion, feature request, and maintainence support
                            post at brihaspati_ERP_mission@yahoogroups.com</p>
                 <!--       
                        <div class="secondary-links">
                            <a href="#" >Brihaspati</a>
                            <a href="#" >BGAS</a>
                            <a href="#" >PICO</a>
                            <a href="#" >Student Fee Management</a>
                        </div>      -->
                        <p class="copyright"> © 2015 PayrollSys IITK.</p>
                   </div>   
                </div> <!-- end footer--> 
                
            </div> <!-- end Wrapper -->                
            </div> <!-- end Container -->
        </body>
    </html>
</f:view>
