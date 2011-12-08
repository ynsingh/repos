<%-- 
    Document   : PublicationInfoAdd
    Created on : Nov 2, 2011, 4:33:27 PM
Author     : Vinay
Version      : 1
Modified on 14 nov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>My Publication</title>
        <script type="text/javascript" src="<s:url value="/JS/jquery-latest.js"/>"></script>

        <script type="text/javascript">
            $(document).ready(function(){
                $("#accordion > li > div").click(function(){
 
                    if(false == $(this).next().is(':visible')) {
                        $('#accordion ul').slideUp(300);
                    }
                    $(this).next().slideToggle(300);
                });
 
                $('#accordion ul:eq(0)').show();

            });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('fieldset.jcalendar').jcalendar();
            });
        </script>
        <script src="<s:url value="/JS/jquery-1.6.4.min.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/JS/jcalendar-source.js"/>" type="text/javascript"></script>
        <link href="<s:url value="/JS/jcalendar.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/theme1/style.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../JS/jquery-ui.min.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="../JS/jquery-ui.css"/>


        <style>
            .ui-datepicker-calendar {
                display: none;
            }
        </style>
        <script type="text/javascript">
            $(function() {
                $('.date-picker').datepicker( {
                    changeMonth: true,
                    changeYear: true,
                    yearRange: '1950:2011',
                    showButtonPanel: true,
                    dateFormat: 'MM yy',
                    onClose: function(dateText, inst) { 
                        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                        $(this).datepicker('setDate', new Date(year, month, 1));
                    }
                });
            });
        </script>
    </head>
    <body>
        <jsp:include page="../Header.jsp"/>
        <div id="container">
            <div class="wrapper">

                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>Add Publication</h3>
                    <br/><br/>
                    <s:form action="AddPublication" method="post" namespace="/MyProfile" name="PublicationForm" onsubmit="return validatePlanForm()">

                        <table align="center" cellpadding="4" border="0" cellspacing="0" width="90%">
                            <s:textfield name="" label="Title" cssClass="width250"/>
                            <tr>
                                <td class="tdLabel">
                                    <label for="AddPublication_" class="label">Author:</label>
                                </td>
                                <td>
                                    <input type="text" name="" value="Surname" id="AddPublication_" class="width150 floatL"/>
                                    <input type="text" name="" value="First Name" id="AddPublication_" class="width150 floatL marl5"/>
                                    <input type="text" name="" value="Middle Name" id="AddPublication_" class="width150 floatL marl5"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdLabel">
                                    <label for="AddPublication_" class="label">Co-Author:</label>
                                </td>
                                <td>
                                    <input type="text" name="" value="Surname" id="AddPublication_" class="width150 floatL"/>
                                    <input type="text" name="" value="First Name" id="AddPublication_" class="width150 floatL marl5"/>
                                    <input type="text" name="" value="Middle Name" id="AddPublication_" class="width150 floatL marl5"/>
                                </td>
                            </tr>
                            <s:textfield name="" label="City" cssClass="width250"/>
                            <s:textfield name="" label="Country" cssClass="width250"/>                            
                            <s:textfield cssClass="date-picker width250" name="pubDate" label="Publication Date"/>
                            <tr><td>Identifiers</td><td>
                                    <select name="identifiersType" Class="width250">
                                        <option value="#">Select Identifiers</option>

                                        <option value="">ISSN</option>
                                        <option value="">ISMN</option>
                                        <option value="">ISBN</option>
                                        <option value="">Patent No</option>
                                        <option value="">Gov’t Doc#</option>
                                        <option value="">URI</option>
                                        <option value="">Others</option>
                                    </select>
                                </td>
                            </tr>
                            <s:textfield name="" label="Identifiers No." cssClass="width250"/>
                            <tr>
                                <td>Publication Type :</td>
                                <td>
                                    <select name="" Class="width250">
                                        <option value="#">Select Publication Type</option>
                                        <option value="">Animations</option>
                                        <option value="#">Article</option>
                                        <option value="#">Book</option>
                                        <option value="#">Book Chapter</option>
                                        <option value="#">Conference / Seminar / Workshop</option>
                                        <option value="#">Data set</option>
                                        <option value="#">Image</option>
                                        <option value="#">Image, 3D</option>
                                        <option value="#">Learning Objects</option>
                                        <option value="#">Map</option>
                                        <option value="#">Musical Score</option>
                                        <option value="#">Plan or Blueprints</option>
                                        <option value="#">Preprint</option>
                                        <option value="#">Presentation</option>
                                        <option value="#">Patent</option>
                                        <option value="#">Periodicals</option>
                                        <option value="#">Online Sources</option>
                                        <option value="#">Recording, Acoustical</option>
                                        <option value="#">Recording Musical</option>
                                        <option value="#">Recording Oral</option>
                                        <option value="#">References</option>
                                        <option value="#">Reports</option>
                                        <option value="#">Software</option>
                                        <option value="#">Standards</option>
                                        <option value="#">Technical Paper</option>
                                        <option value="#">Technical Report</option>
                                        <option value="#">Theses</option>
                                        <option value="#">Unpublished</option>
                                        <option value="#">Video</option>
                                        <option value="#">Working paper</option>
                                        <option value="#">Others</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Series/Report Type:</td>
                                <td>
                                    <select name="" Class="width250">
                                        <option value="#">Select Series, Report Type</option>
                                        <option value="#">Volume</option>
                                        <option value="#">Page No.</option>
                                        <option value="#">Report No.</option>                                   </select>
                                </td>
                            </tr>
                            <s:textfield name="" label="Series/Report No." cssClass="width250"/>
                            <s:textfield  name="" label="Sponsors/Publisher" cssClass="width250"/>
                            <s:textarea  name="" label="Abstract / Summary" cols="30" rows="6" cssClass="width250"/>
                            <s:textfield  name="" label="Subject Keywords" cssClass="width250"/>
                            <s:textfield name="" cssClass="width250" label="Publication URL"/>

                            <s:textfield  name="" label="Name"  value="Thesis / Dissertation Supervisor" cssClass="width250"/>
                            <s:textarea  name="" label="Designation, Department, University / Organization" cols="30" rows="6" cssClass="width250"/>

                        </table>
                        <br/>
                        <s:submit cssClass="floatL buttonsMiddle" value="Add Publication" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />
                    </s:form>
                    <br/><br/><br/><br/>
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>
