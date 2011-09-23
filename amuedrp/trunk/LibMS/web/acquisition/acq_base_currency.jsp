<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.DateCalculation"%>

 <jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");
String msg=(String)request.getAttribute("msg");

//session.setAttribute("backlocation", back);

//String back1=(String)session.getAttribute("backlocation");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Staff </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">
<%
String date=DateCalculation.now();
String year=date.substring(0,4);
%>

function check1()
{
     var keyValue = document.getElementById('sourcecurency').options[document.getElementById('sourcecurency').selectedIndex].value;
   if (keyValue=="Select")
    {

        alert("Select Source Currency");

        document.getElementById('sourcecurency').focus();

        return false;
    }
    return true;
  }

  function quit()
  {


         window.location="<%=request.getContextPath()%>/admin/main.jsp";

      return true;
  }



    </script>
</head>
<body>

    <html:form method="post" onsubmit="return check1()" action="/acq_setbasecurrency">

<div
   style="  top:200px;
   left:350px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table>
        <tr><td>

                 <table border="1" class="table" width="400px" height="200px" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Set Base Currency</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">
                    <tr><td rowspan="5" class="txt2">Select Source Currency:
                            <html:select property="base_currency_symbol" name="AcqCurrencyActionForm" styleId="sourcecurency" style="width: 200px;">
                                            <html:option value="Select">Select</html:option>
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
                        

                        <td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Add" /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button4" name="button" value="Delete" class="btn" /></td></tr>
                         <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td></tr>


                </table>


    <input type="hidden" name="library_id" value="<%=library_id%>">









</td></tr>
                <tr><td class="mess">

        <%
          if (msg!=null)
          {

        %>

        <p class="mess">  <%=msg%></p>

        <%
        }
        %>
         <%if (msg1!=null)
          {
        %>


        <p class="err">  <%=msg1%></p>

        <%
        }
        %>

                    </td></tr>
         </table>
</td><td>

            </td></tr>
    </table>


        </div>

</html:form>

</body>


</html>
