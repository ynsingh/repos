<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>

<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String) request.getAttribute("msg1");

%>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_initiate_vendor.jsp";
    return false;
}

function check1()
{
    if(document.getElementById('vendor_name').value=="")
    {
        alert("Enter Vendor Name");

        document.getElementById('vendor_name').focus();

        return false;
    }
    if(document.getElementById('contact_person').value=="")
    {
        alert("Enter Contact Person Name");

        document.getElementById('contact_person').focus();

        return false;
    }

  if(document.getElementById('ph').value=="")
    {
        alert("Enter Phone No");

        document.getElementById('ph').focus();

        return false;
    }

     var keyValue = document.getElementById('vendor_currency').options[document.getElementById('vendor_currency').selectedIndex].value;
   if (keyValue=="Select")
    {

        alert("Select Vendor Currency");

        document.getElementById('vendor_currency').focus();

        return false;
    }



 if(document.getElementById('email').value=="")
    {
        alert("Enter Email Id");

        document.getElementById('email').focus();

        return false;
    }
    return true;

  }

  function echeck(str) {
availableSelectList = document.getElementById("searchResult");
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   availableSelectList.innerHTML="Invalid E-mail ID";
                   document.getElementById('email').value="";
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		    availableSelectList.innerHTML="Invalid E-mail ID";
                    document.getElementById('email').value="";
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email').value="";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email').value="";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email').value="";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email').value="";
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email').value="";
		    return false
		 }

 		 return true
	}


</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>
   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>

   <html:form method="post" action="/acq_vendor_update"  onsubmit="return check1();" style="position:absolute; left:200px; top:150px;"  >
       <table border="1" class="table" width="720" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Vendor Detail Entry</b></td></tr>
                <tr><td>
                        <table width="700" border="0" cellspacing="4" cellpadding="1" align="left">
                        <tr>
                        <html:hidden property="library_id" name="AcqVendorActionForm" value="<%=library_id%>" />
                        <html:hidden property="sub_library_id" name="AcqVendorActionForm" value="<%=sub_library_id%>" /><td></td>
                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Vendor Id:</strong></td>
    <td><html:text readonly="true" property="vendor_id" name="AcqVendorActionForm" styleClass="textBoxWidth" /></td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td align="right"  class="txtStyle"><strong>Vendor Name:<a class="star">*</a></strong></td>
    <td><html:text readonly="true" property="vendor" name="AcqVendorActionForm" styleId="vendor_name" styleClass="textBoxWidth" /></td>
</tr>

<tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td align="right" class="txtStyle"><strong>Contact Person:<a class="star">*</a></strong></td>
    <td><html:text readonly="true" property="contact_person" name="AcqVendorActionForm" styleId="contact_person"  styleClass="textBoxWidth" /></td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr>
      <td  align="right" class="txtStyle"><strong>Phone No:<a class="star">*</a></strong></td>
      <td><html:text readonly="true" property="phone" name="AcqVendorActionForm" styleId="ph"  styleClass="textBoxWidth" /></td>
  </tr>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td width="150" align="right" class="txtStyle"><strong>Address:</strong> </td>
    <td><html:text readonly="true" property="address" name="AcqVendorActionForm" styleClass="textBoxWidth" />

    </td>
    <td width="150" align="right" class="txtStyle"><strong>Vendor Currency:</strong> <a class="star">*</a></td>
    <td><html:select disabled="true" property="vendor_currency" name="AcqVendorActionForm" styleId="vendor_currency" style="width: 200px;">
                                            <html:option value="Select">Please select</html:option>
                                            <html:option value="ALL">ALL Albania Leke</html:option>
                                            <html:option value="BGN">BGN Bulgaria Leva</html:option>
                                            <html:option value="BOB">BOB Bolivia Bolivianos</html:option>
                                            <html:option value="BRL">BRL Brazil Reals</html:option>
                                            <html:option value="BWP">BWP Botswana Pulas</html:option>

                                            <html:option value="BZD">BZD Belize Dollars</html:option>
                                            <html:option value="CHF">CHF Switzerland Francs</html:option>
                                            <html:option value="CNY">CNY China Yuan Renminbi</html:option>
                                            <html:option value="CRC">CRC Costa Rica Col&oacute;n</html:option>
                                            <html:option value="CUP">CUP Cuba Pesos</html:option>
                                            <html:option value="CZK">CZK Czech Republic Koruny</html:option>
                                            <html:option value="DKK">DKK Denmark Kroner</html:option>
                                            <html:option value="DOP">DOP Dominican Republic Pesos</html:option>

                                            <html:option value="EEK">EEK Estonia Krooni</html:option>
                                            <html:option value="EGP">EGP Egypt Pounds</html:option>
                                            <html:option value="EUR">EUR Euro</html:option>
                                            <html:option value="GBP">GBP United Kingdom Pounds</html:option>
                                            <html:option value="GTQ">GTQ Guatemala Quetzales</html:option>
                                            <html:option value="SDD">SDD Sudanese Dinar</html:option>
                                            <html:option value="AUD">AUD Australia Dollars</html:option>
                                            <html:option value="ARS">ARS Argentina Pesos</html:option>
                                            <html:option value="FJD">FJD Fiji Dollars</html:option>

                                            <html:option value="COP">COP Colombia Pesos</html:option>
                                            <html:option value="CLP">CLP Chile Pesos</html:option>
                                            <html:option value="CAD">CAD Canada Dollars</html:option>
                                            <html:option value="CVE">CVE Cape Verde Escudos</html:option>
                                            <html:option value="BHD">BHD Bahrain Dinars</html:option>
                                            <html:option value="BIF">BIF Burundi Francs</html:option>
                                            <html:option value="DJF">DJF Djibouti Francs</html:option>
                                            <html:option value="DZD">DZD Algeria Algeria Dinars</html:option>
                                            <html:option value="ETB">ETB Ethiopia Birr</html:option>

                                            <html:option value="GEL">GEL Georgia Lari</html:option>
                                            <html:option value="GHS">GHS Ghana Cedis</html:option>
                                            <html:option value="GMD">GMD Gambia Dalasi</html:option>
                                            <html:option value="GNF">GNF Guinea Francs</html:option>
                                            <html:option value="HTG">HTG Haiti Gourdes</html:option>
                                            <html:option value="IQD">IQD Iraq Dinars</html:option>
                                            <html:option value="JOD">JOD Jordan Dinars</html:option>
                                            <html:option value="KES">KES Kenya Shillings</html:option>
                                            <html:option value="KMF">KMF Comoros Francs</html:option>

                                            <html:option value="KWD">KWD Kuwait Dinars</html:option>
                                            <html:option value="LSL">LSL Lesotho Maloti</html:option>
                                            <html:option value="LYD">LYD Libya Dinars</html:option>
                                            <html:option value="MAD">MAD Morocco Dirhams</html:option>
                                            <html:option value="MDL">MDL Moldova Lei</html:option>
                                            <html:option value="MGA">MGA Madagascar Ariary</html:option>
                                            <html:option value="MMK">MMK Myanmar (Burma) Kyats</html:option>
                                            <html:option value="MOP">MOP Macau Patacas</html:option>
                                            <html:option value="MRO">MRO Mauritania Ouguiyas</html:option>

                                            <html:option value="MVR">MVR Maldives (Maldive Islands) Rufiyaa</html:option>
                                            <html:option value="MWK">MWK Malawi Kwachas</html:option>
                                            <html:option value="PGK">PGK Papua New Guinea Kina</html:option>
                                            <html:option value="RWF">RWF Rwanda Rwanda Francs</html:option>
                                            <html:option value="SDG">SDG Sudan Pounds</html:option>
                                            <html:option value="SKK">SKK Slovakia Koruny</html:option>
                                            <html:option value="STD">STD Sao Tome and Principe Dobras</html:option>
                                            <html:option value="SZL">SZL Swaziland Emalangeni</html:option>
                                            <html:option value="TND">TND Tunisia Dinars</html:option>

                                            <html:option value="TZS">TZS Tanzania Shillings</html:option>
                                            <html:option value="UGX">UGX Uganda Shillings</html:option>
                                            <html:option value="XAF">XAF Communaute Financiere Africaine BEAC Francs</html:option>
                                            <html:option value="XOF">XOF Communaute Financiere Africaine BCEAO Francs</html:option>
                                            <html:option value="XPF">XPF Comptoirs Francais du Pacifique Francs</html:option>
                                            <html:option value="ZMK">ZMK Zambia Kwacha</html:option>
                                            <html:option value="AED">AED United Arab Emirates Dirhams</html:option>
                                            <html:option value="BDT">BDT Bangladesh Taka</html:option>
                                            <html:option value="HKD">HKD Hong Kong Dollars</html:option>

                                            <html:option value="HNL">HNL Honduras Lempiras</html:option>
                                            <html:option value="HRK">HRK Croatia Kuna</html:option>
                                            <html:option value="HUF">HUF Hungary Forint</html:option>
                                            <html:option value="IDR">IDR Indonesia Rupiahs</html:option>
                                            <html:option value="ILS">ILS Israel New Shekels</html:option>
                                            <html:option value="INR">INR India Rupees</html:option>
                                            <html:option value="IRR">IRR Iran Rials</html:option>
                                            <html:option value="ISK">ISK Iceland Kronur</html:option>
                                            <html:option value="JPY">JPY Japan Yen</html:option>

                                            <html:option value="KHR">KHR Cambodia Riels</html:option>
                                            <html:option value="KRW">KRW Korea (South) Won</html:option>
                                            <html:option value="KZT">KZT Kazakhstan Tenge</html:option>
                                            <html:option value="LAK">LAK Laos Kips</html:option>
                                            <html:option value="LBP">LBP Lebanon Pounds</html:option>
                                            <html:option value="BND">BND Brunei Darussalam Dollars</html:option>
                                            <html:option value="BMD">BMD Bermuda Dollars</html:option>
                                            <html:option value="LKR">LKR Sri Lanka Rupees</html:option>
                                            <html:option value="LTL">LTL Lithuania Litai</html:option>

                                            <html:option value="LVL">LVL Latvia Lati</html:option>
                                            <html:option value="MKD">MKD Macedonia Denars</html:option>
                                            <html:option value="MUR">MUR Mauritius Rupees</html:option>
                                            <html:option value="MYR">MYR Malaysia Ringgits</html:option>
                                            <html:option value="NGN">NGN Nigeria Nairas</html:option>
                                            <html:option value="NIO">NIO Nicaragua Cordobas</html:option>
                                            <html:option value="NOK">NOK Norway Krone</html:option>
                                            <html:option value="NPR">NPR Nepal Rupees</html:option>
                                            <html:option value="OMR">OMR Oman Rials</html:option>

                                            <html:option value="PAB">PAB Panama Balboa</html:option>
                                            <html:option value="PEN">PEN Peru Nuevos Soles</html:option>
                                            <html:option value="PHP">PHP Philippines Pesos</html:option>
                                            <html:option value="PKR">PKR Pakistan Rupees</html:option>
                                            <html:option value="PLN">PLN Poland Zlotych</html:option>
                                            <html:option value="PYG">PYG Paraguay Guarani</html:option>
                                            <html:option value="QAR">QAR Qatar Rials</html:option>
                                            <html:option value="RON">RON Romania New Lei</html:option>
                                            <html:option value="RSD">RSD Serbia Dinars</html:option>

                                            <html:option value="RUB">RUB Russia Rubles</html:option>
                                            <html:option value="SAR">SAR Saudi Arabia Riyals</html:option>
                                            <html:option value="SCR">SCR Seychelles Rupees</html:option>
                                            <html:option value="SEK">SEK Sweden Kronor</html:option>
                                            <html:option value="SOS">SOS Somalia Shillings</html:option>
                                            <html:option value="SYP">SYP Syria Pounds</html:option>
                                            <html:option value="THB">THB Thailand Baht</html:option>
                                            <html:option value="TRY">TRY Turkey New Lira</html:option>
                                            <html:option value="TWD">TWD Taiwan New Dollars</html:option>

                                            <html:option value="UAH">UAH Ukraine Hryvnia</html:option>
                                            <html:option value="UYU">UYU Uruguay Pesos</html:option>
                                            <html:option value="UZS">UZS Uzbekistan Sums</html:option>
                                            <html:option value="VEF">VEF Venezuela Bolivares Fuertes</html:option>
                                            <html:option value="VND">VND Vietnam Dong</html:option>
                                            <html:option value="YER">YER Yemen Rials</html:option>
                                            <html:option value="ZAR">ZAR South Africa Rand</html:option>
                                            <html:option value="USD">USD United States of America Dollars</html:option>
                                            <html:option value="SVC">SVC El Salvador Colones</html:option>

                                            <html:option value="SGD">SGD Singapore Dollars</html:option>
                                            <html:option value="NZD">NZD New Zealand Dollars</html:option>
                                            <html:option value="NAD">NAD Namibia Dollars</html:option>
                                            <html:option value="MXN">MXN Mexico Pesos</html:option>


                                        </html:select>

    </td>

  </tr>

  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>City:</strong></td>
    <td><html:text readonly="true" property="city" name="AcqVendorActionForm" styleClass="textBoxWidth" />
      <br><span class="err">   <html:messages id="err_name" property="author">
        <bean:write name="err_name" />
    </html:messages></span>
  </td>

 <td align="right" class="txtStyle"><strong>State:</strong></td>
 <td><html:text readonly="true" property="state" name="AcqVendorActionForm" styleClass="textBoxWidth" /></td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>


  <tr>
    <td align="right" class="txtStyle"><strong>Country:</strong></td>
    <td><html:text readonly="true" property="country" name="AcqVendorActionForm" styleClass="textBoxWidth" /></td>
 <td align="right" class="txtStyle"><strong>Pin No:</strong></td>
 <td><html:text readonly="true" property="pin" name="AcqVendorActionForm" styleClass="textBoxWidth" /></td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>


<tr>
   <td align="right" class="txtStyle"><strong>Fax:</strong></td>
   <td><html:text readonly="true" property="fax" name="AcqVendorActionForm" styleClass="textBoxWidth" />
   </td>
 <td align="right" class="txtStyle"><strong>Email:<a class="star">*</a></strong></td>
 <td><html:text readonly="true" property="email" name="AcqVendorActionForm" onblur="return echeck(email.value)" styleId="email" styleClass="textBoxWidth" />
 <br/> <div align="left" class="err" id="searchResult" style="border:#000000; "></div>
 </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>

<tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="5px" class="mandatory" align="right"><a class="star">*</a>indicated fields are mandatory</td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td align="center" colspan="4">
    
   <input name="button" type="button" onclick="return send();" value="Back" class="txt1"/></td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
</table>
</td></tr> </table>
  </html:form>
    </body>
</html>

