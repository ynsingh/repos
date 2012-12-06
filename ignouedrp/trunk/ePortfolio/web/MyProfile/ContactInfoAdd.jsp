<%-- 
    Document   : ContactInfoAdd
    Created on : Sep 16, 2011, 10:41:44 AM
    Author     : IGNOU Team
    Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add Contact</title>
<link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/gen_validatorv4.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/countries.js"/>"></script>
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
<body>
<%
            if (session.getAttribute("user_id") == null) {
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
          <div class="my_account_bg">Contact Information</div>
          <div class="v_gallery">
            <div class="w98 mar0a">
              <div class="w100 fl-l mart10">
                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > <s:a action="ShowContactInfo">Contact Information</s:a>&nbsp;>&nbsp;Add Contact Information </div>
              </div>
              <div class="w100 fl-l mart10">
                <div class="w100 fl-l tc fbld fcgreen">
                  <s:property value="msg"/>
                </div>
                <div class="w100 fl-l mart5">
                    <s:form action="AddContactInfo" method="post" namespace="MyProfile" name="myform" theme="simple">
                    <table width="50%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                      <tr>
                        <td>Country</td>
                        <td><select onchange="print_state('state',this.selectedIndex);" id="country" name ="country">
                            <option value="#">Choose Your Country Name</option>
                          </select></td>
                      </tr>
                      <tr>
                        <td>State/District</td>
                        <td><select id ="state" name ="state" >
                          </select></td>
                      </tr>
                      <script language="javascript">print_country("country");</script>
                      <tr>
                        <td>City/Place</td>
                        <td><s:textfield name="city" /></td>
                      </tr>
                      <tr>
                        <td>Address 1</td>
                        <td><s:textarea name="address1" /></td>
                      </tr>
                      <tr>
                        <td>Address 2</td>
                        <td><s:textarea name="address2" /></td>
                      </tr>
                      <tr>
                        <td>PIN/ZIP Code</td>
                        <td><s:textfield name="pin" /></td>
                      </tr>
                      <tr>
                        <td>Telephone(Home)</td>
                        <td><s:textfield name="HTelephone" /></td>
                      </tr>
                      <tr>
                        <td>Telephone(Office)</td>
                        <td><s:textfield name="OTelephone" /></td>
                      </tr>
                      <tr>
                        <td>Mobile No.</td>
                        <td><s:textfield name="mobileNo" /></td>
                      </tr>
                      <tr>
                        <td>FAX No.</td>
                        <td><s:textfield name="faxNo" /></td>
                      </tr>
                      <tr>
                        <td>Alternative Email 1</td>
                        <td><s:textfield name="email1" /></td>
                      </tr>
                      <tr>
                        <td>Alternative Email 2</td>
                        <td><s:textfield name="email2" /></td>
                      </tr>
                      <tr>
                        <td>Alternative Email 3</td>
                        <td><s:textfield name="email3" /></td>
                      </tr>
                      <tr>
                        <td>Website(Organization)</td>
                        <td><s:textfield name="owebsite" /></td>
                      </tr>
                      <tr>
                        <td>Website(Personal)</td>
                        <td><s:textfield name="pwebsite" /></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><s:submit value="Save" />
                          <s:reset value="Cancel" onClick="history.go(-1);" /></td>
                      </tr>
                    </table>
                  </s:form>
                </div>
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
<s:include value="/Footer.jsp"/>
<script type="text/javascript">
            var frmvalidator  = new Validator("myform");
          
            frmvalidator.addValidation("country","req","Please enter Country");
            
            frmvalidator.addValidation("state","req","Please enter State");
    
            frmvalidator.addValidation("address1","req","Please enter Address1");
            frmvalidator.addValidation("address1","maxlen=400",	"Max length for FirstName is 400");
            frmvalidator.addValidation("address1","alpha_num","Address 1 should be Alpha Numeric only");
    
            frmvalidator.addValidation("pin","req","Please enter your PIN/ZIP Code:");
            frmvalidator.addValidation("pin","maxlen=10", "Max length is 10");
            frmvalidator.addValidation("pin","numeric","numeric only");
  
            frmvalidator.addValidation("HTelephone","req","Please enter your TelephoneHome");
            frmvalidator.addValidation("HTelephone","maxlen=20","Max length is 20");
            frmvalidator.addValidation("HTelephone","numeric","Numeric only");

            frmvalidator.addValidation("mobileNo","req","Please enter your MobileNo");
            frmvalidator.addValidation("mobileNo","maxlen=20","Max length is 20");
            frmvalidator.addValidation("mobileNo","numeric","Numeric only");
              
            frmvalidator.addValidation("email1","maxlen=50");
            frmvalidator.addValidation("email1","req");
            frmvalidator.addValidation("email1","email");
  
        </script>
</body>
</html>
