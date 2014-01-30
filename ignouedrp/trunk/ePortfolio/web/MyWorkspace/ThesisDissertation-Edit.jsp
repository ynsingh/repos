<%-- 
    Document   : ThesisDissertation-Edit
    Created on : Feb 21, 2012, 4:44:41 PM
    Author     : IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Edit Thesis / Dissertation</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/> 
        <link href="<s:url value="/css/MonthYearPicker.css"/>" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        <script type="text/javascript">
            if(window.history.forward(1) != null)
                window.history.forward(1);
        </script>
    </head>
    <body><%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include  value="/Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Edit Thesis / Dissertation</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum">
                                            <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="showTD">Thesis / Dissertation</a> &nbsp;> Edit Thesis / Dissertation
                                        </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w550p mar0a">
                                                <legend><strong>Edit Thesis / Dissertation</strong></legend>
                                                <s:form action="UpdateTD" method="post" namespace="/MyWorkspace" name="">
                                                    <table width="95%" border="0" class="mar0a" cellpadding="2" cellspacing="2">  
                                                        <s:iterator value="TDListList">
                                                            <s:hidden name="thesisDissertationId"/><s:hidden name="userId"/>
                                                            <s:radio name="reportType" label="Report Type" list="{'Thesis', 'Dissertation'}"/>
                                                            <s:select label="Programme" cssClass="width255" name="programme" headerKey="1" headerValue="-- Please Select Programme--" list="{'PhD', 'M.Phil', 'M.Tech', 'ME', 'MS', 'Others'}" />
                                                            <s:textfield name="other" label="If other, Enter name of the Programme"/>
                                                            <s:textfield name="department" label="Department"/>
                                                            <s:textfield name="nameUniversity" label="Name of the University / Institute"/>
                                                            <s:textfield name="cityState" label="City / State"/>
                                                            <tr>
                                                                <td width="35%">Country :</td>
                                                                <td width="60%">
                                                                    <select name="country" style="width: 200px;">
                                                                        <option value="Afghanistan"> Afghanistan </option>
                                                                        <option value="Albania"> Albania </option>
                                                                        <option value="Algeria"> Algeria </option>
                                                                        <option value="American Samoa"> American Samoa </option>
                                                                        <option value="Andorra"> Andorra </option>
                                                                        <option value="Angola"> Angola </option>
                                                                        <option value="Anguilla"> Anguilla </option>
                                                                        <option value="Antarctica"> Antarctica </option>
                                                                        <option value="Antigua and Barbuda"> Antigua and Barbuda </option>
                                                                        <option value="Argentina"> Argentina </option>
                                                                        <option value="Armenia"> Armenia </option>
                                                                        <option value="Aruba"> Aruba </option>
                                                                        <option value="Australia"> Australia </option>
                                                                        <option value="Austria"> Austria </option>
                                                                        <option value="Azerbaijan"> Azerbaijan </option>
                                                                        <option value="Bahamas"> Bahamas </option>
                                                                        <option value="Bahrain"> Bahrain </option>
                                                                        <option value="Bangladesh"> Bangladesh </option>
                                                                        <option value="Barbados"> Barbados </option>
                                                                        <option value="Belarus"> Belarus </option>
                                                                        <option value="Belgium"> Belgium </option>
                                                                        <option value="Belize"> Belize </option>
                                                                        <option value="Benin"> Benin </option>
                                                                        <option value="Bermuda"> Bermuda </option>
                                                                        <option value="Bhutan"> Bhutan </option>
                                                                        <option value="Bolivia"> Bolivia </option>
                                                                        <option value="Bosnia and Herzegovina"> Bosnia and Herzegovina </option>
                                                                        <option value="Botswana"> Botswana </option>
                                                                        <option value="Bouvet Island"> Bouvet Island </option>
                                                                        <option value="Brazil"> Brazil </option>
                                                                        <option value="British Indian Ocean Territory"> British Indian Ocean Territory </option>
                                                                        <option value="British Virgin Islands"> British Virgin Islands </option>
                                                                        <option value="Brunei Darussalam"> Brunei Darussalam </option>
                                                                        <option value="Bulgaria"> Bulgaria </option>
                                                                        <option value="Burkina Faso"> Burkina Faso </option>
                                                                        <option value="Burundi"> Burundi </option>
                                                                        <option value="Cambodia"> Cambodia </option>
                                                                        <option value="Cameroon"> Cameroon </option>
                                                                        <option value="Canada"> Canada </option>
                                                                        <option value="Cape Verde"> Cape Verde </option>
                                                                        <option value="Cayman Islands"> Cayman Islands </option>
                                                                        <option value="Central African Republic"> Central African Republic </option>
                                                                        <option value="Chad"> Chad </option>
                                                                        <option value="Chile"> Chile </option>
                                                                        <option value="China"> China </option>
                                                                        <option value="Christmas Island"> Christmas Island </option>
                                                                        <option value="Cocos"> Cocos </option>
                                                                        <option value="Colombia"> Colombia </option>
                                                                        <option value="Comoros"> Comoros </option>
                                                                        <option value="Congo"> Congo </option>
                                                                        <option value="Cook Islands"> Cook Islands </option>
                                                                        <option value="Costa Rica"> Costa Rica </option>
                                                                        <option value="Croatia"> Croatia </option>
                                                                        <option value="Cuba"> Cuba </option>
                                                                        <option value="Cyprus"> Cyprus </option>
                                                                        <option value="Czech Republic"> Czech Republic </option>
                                                                        <option value="Denmark"> Denmark </option>
                                                                        <option value="Djibouti"> Djibouti </option>
                                                                        <option value="Dominica"> Dominica </option>
                                                                        <option value="Dominican Republic"> Dominican Republic </option>
                                                                        <option value="East Timor"> East Timor </option>
                                                                        <option value="Ecuador"> Ecuador </option>
                                                                        <option value="Egypt"> Egypt </option>
                                                                        <option value="El Salvador"> El Salvador </option>
                                                                        <option value="Equatorial Guinea"> Equatorial Guinea </option>
                                                                        <option value="Eritrea"> Eritrea </option>
                                                                        <option value="Estonia"> Estonia </option>
                                                                        <option value="Ethiopia"> Ethiopia </option>
                                                                        <option value="Falkland Islands"> Falkland Islands </option>
                                                                        <option value="Faroe Islands"> Faroe Islands </option>
                                                                        <option value="Fiji"> Fiji </option>
                                                                        <option value="Finland"> Finland </option>
                                                                        <option value="France"> France </option>
                                                                        <option value="French Guiana"> French Guiana </option>
                                                                        <option value="French Polynesia"> French Polynesia </option>
                                                                        <option value="French Southern Territories"> French Southern Territories </option>
                                                                        <option value="Gabon"> Gabon </option>
                                                                        <option value="Gambia"> Gambia </option>
                                                                        <option value="Georgia"> Georgia </option>
                                                                        <option value="Germany"> Germany </option>
                                                                        <option value="Ghana"> Ghana </option>
                                                                        <option value="Gibraltar"> Gibraltar </option>
                                                                        <option value="Greece"> Greece </option>
                                                                        <option value="Greenland"> Greenland </option>
                                                                        <option value="Grenada"> Grenada </option>
                                                                        <option value="Guadeloupe"> Guadeloupe </option>
                                                                        <option value="Guam"> Guam </option>
                                                                        <option value="Guatemala"> Guatemala </option>
                                                                        <option value="Guinea"> Guinea </option>
                                                                        <option value="Guinea-Bissau"> Guinea-Bissau </option>
                                                                        <option value="Guyana"> Guyana </option>
                                                                        <option value="Haiti"> Haiti </option>
                                                                        <option value="Heard and McDonald Islands"> Heard and McDonald Islands </option>
                                                                        <option value="Honduras"> Honduras </option>
                                                                        <option value="Hong Kong"> Hong Kong </option>
                                                                        <option value="Hungary"> Hungary </option>
                                                                        <option value="Iceland"> Iceland </option>
                                                                        <option value="India" selected="true"> India </option>
                                                                        <option value="Indonesia"> Indonesia </option>
                                                                        <option value="Iran"> Iran </option>
                                                                        <option value="Iraq"> Iraq </option>
                                                                        <option value="Ireland"> Ireland </option>
                                                                        <option value="Israel"> Israel </option>
                                                                        <option value="Italy"> Italy </option>
                                                                        <option value="Ivory Coast"> Ivory Coast </option>
                                                                        <option value="Jamaica"> Jamaica </option>
                                                                        <option value="Japan"> Japan </option>
                                                                        <option value="Jordan"> Jordan </option>
                                                                        <option value="Kazakhstan"> Kazakhstan </option>
                                                                        <option value="Kenya"> Kenya </option>
                                                                        <option value="Kiribati"> Kiribati </option>
                                                                        <option value="Kuwait"> Kuwait </option>
                                                                        <option value="Kyrgyzstan"> Kyrgyzstan </option>
                                                                        <option value="Laos"> Laos </option>
                                                                        <option value="Latvia"> Latvia </option>
                                                                        <option value="Lebanon"> Lebanon </option>
                                                                        <option value="Lesotho"> Lesotho </option>
                                                                        <option value="Liberia"> Liberia </option>
                                                                        <option value="Libya"> Libya </option>
                                                                        <option value="Liechtenstein"> Liechtenstein </option>
                                                                        <option value="Lithuania"> Lithuania </option>
                                                                        <option value="Luxembourg"> Luxembourg </option>
                                                                        <option value="Macau"> Macau </option>
                                                                        <option value="Macedonia"> Macedonia </option>
                                                                        <option value="Madagascar"> Madagascar </option>
                                                                        <option value="Malawi"> Malawi </option>
                                                                        <option value="Malaysia"> Malaysia </option>
                                                                        <option value="Maldives"> Maldives </option>
                                                                        <option value="Mali"> Mali </option>
                                                                        <option value="Malta"> Malta </option>
                                                                        <option value="Marshall Islands"> Marshall Islands </option>
                                                                        <option value="Martinique"> Martinique </option>
                                                                        <option value="Mauritania"> Mauritania </option>
                                                                        <option value="Mauritius"> Mauritius </option>
                                                                        <option value="Mayotte"> Mayotte </option>
                                                                        <option value="Mexico"> Mexico </option>
                                                                        <option value="Micronesia"> Micronesia </option>
                                                                        <option value="Moldova"> Moldova </option>
                                                                        <option value="Monaco"> Monaco </option>
                                                                        <option value="Mongolia"> Mongolia </option>
                                                                        <option value="Montserrat"> Montserrat </option>
                                                                        <option value="Morocco"> Morocco </option>
                                                                        <option value="Mozambique"> Mozambique </option>
                                                                        <option value="Myanmar"> Myanmar </option>
                                                                        <option value="Namibia"> Namibia </option>
                                                                        <option value="Nauru"> Nauru </option>
                                                                        <option value="Nepal"> Nepal </option>
                                                                        <option value="Netherlands"> Netherlands </option>
                                                                        <option value="Netherlands Antilles"> Netherlands Antilles </option>
                                                                        <option value="New Caledonia"> New Caledonia </option>
                                                                        <option value="New Zealand"> New Zealand </option>
                                                                        <option value="Nicaragua"> Nicaragua </option>
                                                                        <option value="Niger"> Niger </option>
                                                                        <option value="Nigeria"> Nigeria </option>
                                                                        <option value="Niue"> Niue </option>
                                                                        <option value="Norfolk Island"> Norfolk Island </option>
                                                                        <option value="North Korea"> North Korea </option>
                                                                        <option value="Northern Mariana Islands"> Northern Mariana Islands </option>
                                                                        <option value="Norway"> Norway </option>
                                                                        <option value="Oman"> Oman </option>
                                                                        <option value="Pakistan"> Pakistan </option>
                                                                        <option value="Palau"> Palau </option>
                                                                        <option value="Panama"> Panama </option>
                                                                        <option value="Papua New Guinea"> Papua New Guinea </option>
                                                                        <option value="Paraguay"> Paraguay </option>
                                                                        <option value="Peru"> Peru </option>
                                                                        <option value="Philippines"> Philippines </option>
                                                                        <option value="Pitcairn"> Pitcairn </option>
                                                                        <option value="Poland"> Poland </option>
                                                                        <option value="Portugal"> Portugal </option>
                                                                        <option value="Puerto Rico"> Puerto Rico </option>
                                                                        <option value="Qatar"> Qatar </option>
                                                                        <option value="Reunion"> Reunion </option>
                                                                        <option value="Romania"> Romania </option>
                                                                        <option value="Russian Federation"> Russian Federation </option>
                                                                        <option value="Rwanda"> Rwanda </option>
                                                                        <option value="S. Georgia and S. Sandwich Islands"> S. Georgia and S. Sandwich Islands </option>
                                                                        <option value="Saint Kitts and Nevis"> Saint Kitts and Nevis </option>
                                                                        <option value="Saint Lucia"> Saint Lucia </option>
                                                                        <option value="Saint Vincent and The Grenadines"> Saint Vincent and The Grenadines </option>
                                                                        <option value="Samoa"> Samoa </option>
                                                                        <option value="San Marino"> San Marino </option>
                                                                        <option value="Sao Tome and Principe"> Sao Tome and Principe </option>
                                                                        <option value="Saudi Arabia"> Saudi Arabia </option>
                                                                        <option value="Senegal"> Senegal </option>
                                                                        <option value="Seychelles"> Seychelles </option>
                                                                        <option value="Sierra Leone"> Sierra Leone </option>
                                                                        <option value="Singapore"> Singapore </option>
                                                                        <option value="Slovakia"> Slovakia </option>
                                                                        <option value="Slovenia"> Slovenia </option>
                                                                        <option value="Solomon Islands"> Solomon Islands </option>
                                                                        <option value="Somalia"> Somalia </option>
                                                                        <option value="South Africa"> South Africa </option>
                                                                        <option value="South Korea"> South Korea </option>
                                                                        <option value="Soviet Union"> Soviet Union </option>
                                                                        <option value="Spain"> Spain </option>
                                                                        <option value="Sri Lanka"> Sri Lanka </option>
                                                                        <option value="St. Helena"> St. Helena </option>
                                                                        <option value="St. Pierre and Miquelon"> St. Pierre and Miquelon </option>
                                                                        <option value="Sudan"> Sudan </option>
                                                                        <option value="Suriname"> Suriname </option>
                                                                        <option value="Svalbard and Jan Mayen Islands"> Svalbard and Jan Mayen Islands </option>
                                                                        <option value="Swaziland"> Swaziland </option>
                                                                        <option value="Sweden"> Sweden </option>
                                                                        <option value="Switzerland"> Switzerland </option>
                                                                        <option value="Syria"> Syria </option>
                                                                        <option value="Taiwan"> Taiwan </option>
                                                                        <option value="Tajikistan"> Tajikistan </option>
                                                                        <option value="Tanzania"> Tanzania </option>
                                                                        <option value="Thailand"> Thailand </option>
                                                                        <option value="Togo"> Togo </option>
                                                                        <option value="Tokelau"> Tokelau </option>
                                                                        <option value="Tonga"> Tonga </option>
                                                                        <option value="Trinidad and Tobago"> Trinidad and Tobago </option>
                                                                        <option value="Tunisia"> Tunisia </option>
                                                                        <option value="Turkey"> Turkey </option>
                                                                        <option value="Turkmenistan"> Turkmenistan </option>
                                                                        <option value="Turks and Caicos Islands"> Turks and Caicos Islands </option>
                                                                        <option value="Tuvalu"> Tuvalu </option>
                                                                        <option value="Uganda"> Uganda </option>
                                                                        <option value="Ukraine"> Ukraine </option>
                                                                        <option value="United Arab Emirates"> United Arab Emirates </option>
                                                                        <option value="United Kingdom"> United Kingdom </option>
                                                                        <option value="United States"> United States </option>
                                                                        <option value="Uruguay"> Uruguay </option>
                                                                        <option value="US Minor Outlying Islands"> US Minor Outlying Islands </option>
                                                                        <option value="US Virgin Islands"> US Virgin Islands </option>
                                                                        <option value="Uzbekistan"> Uzbekistan </option>
                                                                        <option value="Vanuatu"> Vanuatu </option>
                                                                        <option value="Venezuela"> Venezuela </option>
                                                                        <option value="Viet Nam"> Viet Nam </option>
                                                                        <option value="Wallis and Futuna Islands"> Wallis and Futuna Islands </option>
                                                                        <option value="Western Sahara"> Western Sahara </option>
                                                                        <option value="Yemen"> Yemen </option>
                                                                        <option value="Yugoslavia"> Yugoslavia </option>
                                                                        <option value="Zaire"> Zaire </option>
                                                                        <option value="Zambia"> Zambia </option>
                                                                        <option value="Zimbabwe"> Zimbabwe </option>
                                                                    </select>
                                                                </td></tr>
                                                                <s:radio name="thesisType" label="Thesis /Dissertation Type" list="{'Academic','Industrial', 'Others'}"/>
                                                                <s:textfield name="thesisTitle" label="Thesis / Dissertation Title"/>
                                                                <sj:datepicker readonly="true"  id="date0" label="Month and Year of Start" 
                                                                               name="startDate" value="%{startDate}" 
                                                                               displayFormat="MM, yy"                                                            
                                                                               changeMonth="true" changeYear="true"
                                                                               onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                               />
                                                                <sj:datepicker readonly="true"  id="date1" label="Month and Year of Completion" 
                                                                               name="endDate" value="%{endDate}" 
                                                                               displayFormat="MM, yy"                                                            
                                                                               changeMonth="true" changeYear="true"
                                                                               onChangeMonthYearTopics="true" timepicker="true"  timepickerFormat=" "
                                                                               /><s:textfield name="outcome" label="Outcome"/>
                                                                <s:textfield name="url" label="URL if Any"/>
                                                                <s:textarea name="abstract_" label="Abstract / Objectives"/>
                                                            </s:iterator>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td>
                                                                <s:submit theme="simple" value="Save Changes" />
                                                                <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </fieldset>
                                            </s:form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--Right box End Here-->
                        </div>

                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>  
    </body>
</html>