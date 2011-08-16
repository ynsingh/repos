<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<html:html lang="true">
    <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">

            <!-- This script and many more are available free online at -->
            <!-- The JavaScript Source!! http://javascript.internet.com -->
            <!-- Created by: www.jdstiles.com -->
            <!-- Begin
            function startCalc(){
                interval = setInterval("calc()",1);
            }
            function calc(){
                one = document.getElementById(1).value;
                two = document.getElementById(2).value;
                
                document.getElementById(3).value = (one * 1) * (two * 1);


            }
            function stopCalc(){
                clearInterval(interval);
            }

 function back()
  {

   window.location="<%=request.getContextPath()%>/currency.jsp";
      return false;
  }
        </script>

        <title>Budget</title>
    </head>
    <body style="background-color: white">

        <div
            style="  top:200px;
            left:5px;
            right:5px;
            position: absolute;

            visibility: show;">
            <html:form action="/currency2">

                <table border="1" class="table" width="400px" height="200px" align="center">
                    <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Currency Conversion</td></tr>
                    <tr><td valign="top" align="center"> <br/>
                            <table cellspacing="10px">
                                <tr><td>ID</td><td><input type="text" name="conid" value="<%=request.getAttribute("ConId")%>" readonly ></td></tr>
                                <tr><td align="right">Source Currency</td><td style="width: 100px">
                                        <select name="sCountry" style="width: 200px;">
                                            <option value="" selected="selected">Please select</option>
                                            <option value="ALL">ALL Albania Leke</option>
                                            <option value="BGN">BGN Bulgaria Leva</option>
                                            <option value="BOB">BOB Bolivia Bolivianos</option>
                                            <option value="BRL">BRL Brazil Reals</option>
                                            <option value="BWP">BWP Botswana Pulas</option>

                                            <option value="BZD">BZD Belize Dollars</option>
                                            <option value="CHF">CHF Switzerland Francs</option>
                                            <option value="CNY">CNY China Yuan Renminbi</option>
                                            <option value="CRC">CRC Costa Rica Col&oacute;n</option>
                                            <option value="CUP">CUP Cuba Pesos</option>
                                            <option value="CZK">CZK Czech Republic Koruny</option>
                                            <option value="DKK">DKK Denmark Kroner</option>
                                            <option value="DOP">DOP Dominican Republic Pesos</option>

                                            <option value="EEK">EEK Estonia Krooni</option>
                                            <option value="EGP">EGP Egypt Pounds</option>
                                            <option value="EUR">EUR Euro</option>
                                            <option value="GBP">GBP United Kingdom Pounds</option>
                                            <option value="GTQ">GTQ Guatemala Quetzales</option>
                                            <option value="SDD">SDD Sudanese Dinar</option>
                                            <option value="AUD">AUD Australia Dollars</option>
                                            <option value="ARS">ARS Argentina Pesos</option>
                                            <option value="FJD">FJD Fiji Dollars</option>

                                            <option value="COP">COP Colombia Pesos</option>
                                            <option value="CLP">CLP Chile Pesos</option>
                                            <option value="CAD">CAD Canada Dollars</option>
                                            <option value="CVE">CVE Cape Verde Escudos</option>
                                            <option value="BHD">BHD Bahrain Dinars</option>
                                            <option value="BIF">BIF Burundi Francs</option>
                                            <option value="DJF">DJF Djibouti Francs</option>
                                            <option value="DZD">DZD Algeria Algeria Dinars</option>
                                            <option value="ETB">ETB Ethiopia Birr</option>

                                            <option value="GEL">GEL Georgia Lari</option>
                                            <option value="GHS">GHS Ghana Cedis</option>
                                            <option value="GMD">GMD Gambia Dalasi</option>
                                            <option value="GNF">GNF Guinea Francs</option>
                                            <option value="HTG">HTG Haiti Gourdes</option>
                                            <option value="IQD">IQD Iraq Dinars</option>
                                            <option value="JOD">JOD Jordan Dinars</option>
                                            <option value="KES">KES Kenya Shillings</option>
                                            <option value="KMF">KMF Comoros Francs</option>

                                            <option value="KWD">KWD Kuwait Dinars</option>
                                            <option value="LSL">LSL Lesotho Maloti</option>
                                            <option value="LYD">LYD Libya Dinars</option>
                                            <option value="MAD">MAD Morocco Dirhams</option>
                                            <option value="MDL">MDL Moldova Lei</option>
                                            <option value="MGA">MGA Madagascar Ariary</option>
                                            <option value="MMK">MMK Myanmar (Burma) Kyats</option>
                                            <option value="MOP">MOP Macau Patacas</option>
                                            <option value="MRO">MRO Mauritania Ouguiyas</option>

                                            <option value="MVR">MVR Maldives (Maldive Islands) Rufiyaa</option>
                                            <option value="MWK">MWK Malawi Kwachas</option>
                                            <option value="PGK">PGK Papua New Guinea Kina</option>
                                            <option value="RWF">RWF Rwanda Rwanda Francs</option>
                                            <option value="SDG">SDG Sudan Pounds</option>
                                            <option value="SKK">SKK Slovakia Koruny</option>
                                            <option value="STD">STD Sao Tome and Principe Dobras</option>
                                            <option value="SZL">SZL Swaziland Emalangeni</option>
                                            <option value="TND">TND Tunisia Dinars</option>

                                            <option value="TZS">TZS Tanzania Shillings</option>
                                            <option value="UGX">UGX Uganda Shillings</option>
                                            <option value="XAF">XAF Communaute Financiere Africaine BEAC Francs</option>
                                            <option value="XOF">XOF Communaute Financiere Africaine BCEAO Francs</option>
                                            <option value="XPF">XPF Comptoirs Francais du Pacifique Francs</option>
                                            <option value="ZMK">ZMK Zambia Kwacha</option>
                                            <option value="AED">AED United Arab Emirates Dirhams</option>
                                            <option value="BDT">BDT Bangladesh Taka</option>
                                            <option value="HKD">HKD Hong Kong Dollars</option>

                                            <option value="HNL">HNL Honduras Lempiras</option>
                                            <option value="HRK">HRK Croatia Kuna</option>
                                            <option value="HUF">HUF Hungary Forint</option>
                                            <option value="IDR">IDR Indonesia Rupiahs</option>
                                            <option value="ILS">ILS Israel New Shekels</option>
                                            <option value="INR">INR India Rupees</option>
                                            <option value="IRR">IRR Iran Rials</option>
                                            <option value="ISK">ISK Iceland Kronur</option>
                                            <option value="JPY">JPY Japan Yen</option>

                                            <option value="KHR">KHR Cambodia Riels</option>
                                            <option value="KRW">KRW Korea (South) Won</option>
                                            <option value="KZT">KZT Kazakhstan Tenge</option>
                                            <option value="LAK">LAK Laos Kips</option>
                                            <option value="LBP">LBP Lebanon Pounds</option>
                                            <option value="BND">BND Brunei Darussalam Dollars</option>
                                            <option value="BMD">BMD Bermuda Dollars</option>
                                            <option value="LKR">LKR Sri Lanka Rupees</option>
                                            <option value="LTL">LTL Lithuania Litai</option>

                                            <option value="LVL">LVL Latvia Lati</option>
                                            <option value="MKD">MKD Macedonia Denars</option>
                                            <option value="MUR">MUR Mauritius Rupees</option>
                                            <option value="MYR">MYR Malaysia Ringgits</option>
                                            <option value="NGN">NGN Nigeria Nairas</option>
                                            <option value="NIO">NIO Nicaragua Cordobas</option>
                                            <option value="NOK">NOK Norway Krone</option>
                                            <option value="NPR">NPR Nepal Rupees</option>
                                            <option value="OMR">OMR Oman Rials</option>

                                            <option value="PAB">PAB Panama Balboa</option>
                                            <option value="PEN">PEN Peru Nuevos Soles</option>
                                            <option value="PHP">PHP Philippines Pesos</option>
                                            <option value="PKR">PKR Pakistan Rupees</option>
                                            <option value="PLN">PLN Poland Zlotych</option>
                                            <option value="PYG">PYG Paraguay Guarani</option>
                                            <option value="QAR">QAR Qatar Rials</option>
                                            <option value="RON">RON Romania New Lei</option>
                                            <option value="RSD">RSD Serbia Dinars</option>

                                            <option value="RUB">RUB Russia Rubles</option>
                                            <option value="SAR">SAR Saudi Arabia Riyals</option>
                                            <option value="SCR">SCR Seychelles Rupees</option>
                                            <option value="SEK">SEK Sweden Kronor</option>
                                            <option value="SOS">SOS Somalia Shillings</option>
                                            <option value="SYP">SYP Syria Pounds</option>
                                            <option value="THB">THB Thailand Baht</option>
                                            <option value="TRY">TRY Turkey New Lira</option>
                                            <option value="TWD">TWD Taiwan New Dollars</option>

                                            <option value="UAH">UAH Ukraine Hryvnia</option>
                                            <option value="UYU">UYU Uruguay Pesos</option>
                                            <option value="UZS">UZS Uzbekistan Sums</option>
                                            <option value="VEF">VEF Venezuela Bolivares Fuertes</option>
                                            <option value="VND">VND Vietnam Dong</option>
                                            <option value="YER">YER Yemen Rials</option>
                                            <option value="ZAR">ZAR South Africa Rand</option>
                                            <option value="USD">USD United States of America Dollars</option>
                                            <option value="SVC">SVC El Salvador Colones</option>

                                            <option value="SGD">SGD Singapore Dollars</option>
                                            <option value="NZD">NZD New Zealand Dollars</option>
                                            <option value="NAD">NAD Namibia Dollars</option>
                                            <option value="MXN">MXN Mexico Pesos</option>


                                        </select></td>

                                        <td><input type=text name="firstBox" id="1" value="1" onFocus="startCalc();" readonly onBlur="stopCalc();"></td></tr><br>
                                <tr><td>Rate</td><td><input type=text name="secondBox" value="" id="2" onFocus="startCalc();" onBlur="stopCalc();"></td></tr><br>

                                <tr><td align="right">Target Currency</td><td>
                                        <select name="dCountry" style="width: 200px;">
                                            <option value="" selected="selected">Please select</option>
                                            <option value="ALL">ALL Albania Leke</option>
                                            <option value="BGN">BGN Bulgaria Leva</option>
                                            <option value="BOB">BOB Bolivia Bolivianos</option>
                                            <option value="BRL">BRL Brazil Reals</option>
                                            <option value="BWP">BWP Botswana Pulas</option>

                                            <option value="BZD">BZD Belize Dollars</option>
                                            <option value="CHF">CHF Switzerland Francs</option>
                                            <option value="CNY">CNY China Yuan Renminbi</option>
                                            <option value="CRC">CRC Costa Rica Col&oacute;n</option>
                                            <option value="CUP">CUP Cuba Pesos</option>
                                            <option value="CZK">CZK Czech Republic Koruny</option>
                                            <option value="DKK">DKK Denmark Kroner</option>
                                            <option value="DOP">DOP Dominican Republic Pesos</option>

                                            <option value="EEK">EEK Estonia Krooni</option>
                                            <option value="EGP">EGP Egypt Pounds</option>
                                            <option value="EUR">EUR Euro</option>
                                            <option value="GBP">GBP United Kingdom Pounds</option>
                                            <option value="GTQ">GTQ Guatemala Quetzales</option>
                                            <option value="SDD">SDD Sudanese Dinar</option>
                                            <option value="AUD">AUD Australia Dollars</option>
                                            <option value="ARS">ARS Argentina Pesos</option>
                                            <option value="FJD">FJD Fiji Dollars</option>

                                            <option value="COP">COP Colombia Pesos</option>
                                            <option value="CLP">CLP Chile Pesos</option>
                                            <option value="CAD">CAD Canada Dollars</option>
                                            <option value="CVE">CVE Cape Verde Escudos</option>
                                            <option value="BHD">BHD Bahrain Dinars</option>
                                            <option value="BIF">BIF Burundi Francs</option>
                                            <option value="DJF">DJF Djibouti Francs</option>
                                            <option value="DZD">DZD Algeria Algeria Dinars</option>
                                            <option value="ETB">ETB Ethiopia Birr</option>

                                            <option value="GEL">GEL Georgia Lari</option>
                                            <option value="GHS">GHS Ghana Cedis</option>
                                            <option value="GMD">GMD Gambia Dalasi</option>
                                            <option value="GNF">GNF Guinea Francs</option>
                                            <option value="HTG">HTG Haiti Gourdes</option>
                                            <option value="IQD">IQD Iraq Dinars</option>
                                            <option value="JOD">JOD Jordan Dinars</option>
                                            <option value="KES">KES Kenya Shillings</option>
                                            <option value="KMF">KMF Comoros Francs</option>

                                            <option value="KWD">KWD Kuwait Dinars</option>
                                            <option value="LSL">LSL Lesotho Maloti</option>
                                            <option value="LYD">LYD Libya Dinars</option>
                                            <option value="MAD">MAD Morocco Dirhams</option>
                                            <option value="MDL">MDL Moldova Lei</option>
                                            <option value="MGA">MGA Madagascar Ariary</option>
                                            <option value="MMK">MMK Myanmar (Burma) Kyats</option>
                                            <option value="MOP">MOP Macau Patacas</option>
                                            <option value="MRO">MRO Mauritania Ouguiyas</option>

                                            <option value="MVR">MVR Maldives (Maldive Islands) Rufiyaa</option>
                                            <option value="MWK">MWK Malawi Kwachas</option>
                                            <option value="PGK">PGK Papua New Guinea Kina</option>
                                            <option value="RWF">RWF Rwanda Rwanda Francs</option>
                                            <option value="SDG">SDG Sudan Pounds</option>
                                            <option value="SKK">SKK Slovakia Koruny</option>
                                            <option value="STD">STD Sao Tome and Principe Dobras</option>
                                            <option value="SZL">SZL Swaziland Emalangeni</option>
                                            <option value="TND">TND Tunisia Dinars</option>

                                            <option value="TZS">TZS Tanzania Shillings</option>
                                            <option value="UGX">UGX Uganda Shillings</option>
                                            <option value="XAF">XAF Communaute Financiere Africaine BEAC Francs</option>
                                            <option value="XOF">XOF Communaute Financiere Africaine BCEAO Francs</option>
                                            <option value="XPF">XPF Comptoirs Francais du Pacifique Francs</option>
                                            <option value="ZMK">ZMK Zambia Kwacha</option>
                                            <option value="AED">AED United Arab Emirates Dirhams</option>
                                            <option value="BDT">BDT Bangladesh Taka</option>
                                            <option value="HKD">HKD Hong Kong Dollars</option>

                                            <option value="HNL">HNL Honduras Lempiras</option>
                                            <option value="HRK">HRK Croatia Kuna</option>
                                            <option value="HUF">HUF Hungary Forint</option>
                                            <option value="IDR">IDR Indonesia Rupiahs</option>
                                            <option value="ILS">ILS Israel New Shekels</option>
                                            <option value="INR">INR India Rupees</option>
                                            <option value="IRR">IRR Iran Rials</option>
                                            <option value="ISK">ISK Iceland Kronur</option>
                                            <option value="JPY">JPY Japan Yen</option>

                                            <option value="KHR">KHR Cambodia Riels</option>
                                            <option value="KRW">KRW Korea (South) Won</option>
                                            <option value="KZT">KZT Kazakhstan Tenge</option>
                                            <option value="LAK">LAK Laos Kips</option>
                                            <option value="LBP">LBP Lebanon Pounds</option>
                                            <option value="BND">BND Brunei Darussalam Dollars</option>
                                            <option value="BMD">BMD Bermuda Dollars</option>
                                            <option value="LKR">LKR Sri Lanka Rupees</option>
                                            <option value="LTL">LTL Lithuania Litai</option>

                                            <option value="LVL">LVL Latvia Lati</option>
                                            <option value="MKD">MKD Macedonia Denars</option>
                                            <option value="MUR">MUR Mauritius Rupees</option>
                                            <option value="MYR">MYR Malaysia Ringgits</option>
                                            <option value="NGN">NGN Nigeria Nairas</option>
                                            <option value="NIO">NIO Nicaragua Cordobas</option>
                                            <option value="NOK">NOK Norway Krone</option>
                                            <option value="NPR">NPR Nepal Rupees</option>
                                            <option value="OMR">OMR Oman Rials</option>

                                            <option value="PAB">PAB Panama Balboa</option>
                                            <option value="PEN">PEN Peru Nuevos Soles</option>
                                            <option value="PHP">PHP Philippines Pesos</option>
                                            <option value="PKR">PKR Pakistan Rupees</option>
                                            <option value="PLN">PLN Poland Zlotych</option>
                                            <option value="PYG">PYG Paraguay Guarani</option>
                                            <option value="QAR">QAR Qatar Rials</option>
                                            <option value="RON">RON Romania New Lei</option>
                                            <option value="RSD">RSD Serbia Dinars</option>

                                            <option value="RUB">RUB Russia Rubles</option>
                                            <option value="SAR">SAR Saudi Arabia Riyals</option>
                                            <option value="SCR">SCR Seychelles Rupees</option>
                                            <option value="SEK">SEK Sweden Kronor</option>
                                            <option value="SOS">SOS Somalia Shillings</option>
                                            <option value="SYP">SYP Syria Pounds</option>
                                            <option value="THB">THB Thailand Baht</option>
                                            <option value="TRY">TRY Turkey New Lira</option>
                                            <option value="TWD">TWD Taiwan New Dollars</option>

                                            <option value="UAH">UAH Ukraine Hryvnia</option>
                                            <option value="UYU">UYU Uruguay Pesos</option>
                                            <option value="UZS">UZS Uzbekistan Sums</option>
                                            <option value="VEF">VEF Venezuela Bolivares Fuertes</option>
                                            <option value="VND">VND Vietnam Dong</option>
                                            <option value="YER">YER Yemen Rials</option>
                                            <option value="ZAR">ZAR South Africa Rand</option>
                                            <option value="USD">USD United States of America Dollars</option>
                                            <option value="SVC">SVC El Salvador Colones</option>

                                            <option value="SGD">SGD Singapore Dollars</option>
                                            <option value="NZD">NZD New Zealand Dollars</option>
                                            <option value="NAD">NAD Namibia Dollars</option>
                                            <option value="MXN">MXN Mexico Pesos</option>


                                        </select></td>
                                    <td><input type=text id="3" name="thirdBox" readonly ></td>
                                <tr> <td></td><td colspan="2"><html:submit property="button" value="Submit"></html:submit></td><td></td>
                                    <td><input type="submit" name="button" value="Back"  onclick="return back()" ></td></tr>
                            </html:form>
                        </table>
                    </td></tr></table>
        </div>
    </body>
</html:html>
