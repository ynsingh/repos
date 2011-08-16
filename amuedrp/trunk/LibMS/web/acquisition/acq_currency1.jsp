<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.DateCalculation,com.myapp.struts.hbm.BaseCurrency,com.myapp.struts.AcquisitionDao.AcqCurrencyDao;"%>

 <jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");
String msg=(String)request.getAttribute("msg");


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

BaseCurrency base=AcqCurrencyDao.searchCurrency1(library_id);
//if(base!=null)
//{
//session.setAttribute("basecurrencyid", base.getId().getBaseCurrencySymbol());
//session.setAttribute("basecurrencyname", base.getFormalName());
//}

%>
function loadcurrency(){
    var curr=new Array();
    var currcode=new Array();
    <%if(base!=null){%>
    var base="<%=base.getId().getBaseCurrencySymbol()%>";
    <%}%>

                                            curr[0]="ALL Albania Leke";
                                            curr[1]="BGN Bulgaria Leva";
                                            curr[2]="BZD Belize Dollars";
                                            curr[3]="BOB Bolivia Bolivianos";
                                            curr[4]="BRL Brazil Reals";
                                            curr[5]="BWP Botswana Pulas";
                                            curr[6]="CHF Switzerland Francs";
                                            curr[7]="CNY China Yuan Renminbi";
                                            curr[8]="CRC Costa Rica Col";
                                            curr[9]="CUP Cuba Pesos";
                                            curr[10]="CZK Czech Republic Koruny";
                                            curr[11]="DKK Denmark Kroner";
                                            curr[12]="DOP Dominican Republic Pesos";
                                            curr[13]="EEK Estonia Krooni";
                                            curr[14]="EGP Egypt Pounds";
                                            curr[15]="EUR Euro";
                                            curr[16]="GBP United Kingdom Pounds";
                                            curr[17]="GTQ Guatemala Quetzales";
                                            curr[18]="SDD Sudanese Dinar";
                                            curr[19]="AUD Australia Dollars";
                                            curr[20]="ARS Argentina Pesos";
                                            curr[21]="FJD Fiji Dollars";
                                            curr[22]="COP Colombia Pesos";
                                            curr[23]="CLP Chile Pesos";
                                            curr[24]="CAD Canada Dollars";
                                            curr[25]="CVE Cape Verde Escudos";
                                            curr[26]="BHD Bahrain Dinars";
                                            curr[27]="BIF Burundi Francs";
                                            curr[28]="DJF Djibouti Francs";
                                            curr[29]="DZD Algeria Algeria Dinars";
                                            curr[30]="ETB Ethiopia Birr";
                                            curr[31]="GEL Georgia Lari";
                                            curr[32]="GHS Ghana Cedis";
                                            curr[33]="GMD Gambia Dalasi";
                                            curr[34]="GNF Guinea Francs";
                                            curr[35]="HTG Haiti Gourdes";
                                            curr[36]="IQD Iraq Dinars";
                                            curr[37]="JOD Jordan Dinars";
                                            curr[38]="KES Kenya Shillings";
                                            curr[39]="KMF Comoros Francs";
                                            curr[40]="KWD Kuwait Dinars";
                                            curr[41]="LSL Lesotho Maloti";
                                            curr[42]="LYD Libya Dinars";
                                            curr[43]="MAD Morocco Dirhams";
                                            curr[44]="MDL Moldova Lei";
                                            curr[45]="MGA Madagascar Ariary";
                                            curr[46]="MMK Myanmar (Burma) Kyats";
                                            curr[47]="MOP Macau Patacas";
                                            curr[48]="MRO Mauritania Ouguiyas";
                                            curr[49]="MVR Maldives (Maldive Islands) Rufiyaa";
                                            curr[50]="MWK Malawi Kwachas";
                                            curr[51]="PGK Papua New Guinea Kina";
                                            curr[52]="RWF Rwanda Rwanda Francs";
                                            curr[53]="SDG Sudan Pounds";
                                            curr[54]="SKK Slovakia Koruny";
                                            curr[55]="STD Sao Tome and Principe Dobras";
                                            curr[56]="SZL Swaziland Emalangeni";
                                            curr[57]="TND Tunisia Dinars";
                                            curr[58]="TZS Tanzania Shillings";
                                            curr[59]="UGX Uganda Shillings";
                                            curr[60]="XAF Communaute Financiere Africaine BEAC Francs";
                                            curr[61]="XOF Communaute Financiere Africaine BCEAO Francs";
                                            curr[62]="XPF Comptoirs Francais du Pacifique Francs";
                                            curr[63]="ZMK Zambia Kwacha";
                                            curr[64]="AED United Arab Emirates Dirhams";
                                            curr[65]="BDT Bangladesh Taka";
                                            curr[66]="HKD Hong Kong Dollars";
                                            curr[67]="HNL Honduras Lempiras";
                                            curr[68]="HRK Croatia Kuna";
                                            curr[69]="HUF Hungary Forint";
                                            curr[70]="IDR Indonesia Rupiahs";
                                            curr[71]="ILS Israel New Shekels";
                                            curr[72]="INR India Rupees";
                                            curr[73]="IRR Iran Rials";
                                            curr[74]="ISK Iceland Kronur";
                                            curr[75]="JPY Japan Yen";
                                            curr[76]="KHR Cambodia Riels";
                                            curr[77]="KRW Korea (South) Won";
                                            curr[78]="KZT Kazakhstan Tenge";
                                            curr[79]="LAK Laos Kips";
                                            curr[80]="LBP Lebanon Pounds";
                                            curr[81]="BND Brunei Darussalam Dollars";
                                            curr[82]="BMD Bermuda Dollars";
                                            curr[83]="LKR Sri Lanka Rupees";
                                            curr[84]="LTL Lithuania Litai";
                                            curr[85]="LVL Latvia Lati";
                                            curr[86]="MKD Macedonia Denars";
                                            curr[87]="MUR Mauritius Rupees";
                                            curr[88]="MYR Malaysia Ringgits";
                                            curr[89]="NGN Nigeria Nairas";
                                            curr[90]="NIO Nicaragua Cordobas";
                                            curr[91]="NOK Norway Krone";
                                            curr[92]="NPR Nepal Rupees";
                                            curr[93]="OMR Oman Rials";
                                            curr[94]="PAB Panama Balboa";
                                            curr[95]="PEN Peru Nuevos Soles";
                                            curr[96]="PHP Philippines Pesos";
                                            curr[97]="PKR Pakistan Rupees";
                                            curr[98]="PLN Poland Zlotych";
                                            curr[99]="PYG Paraguay Guarani";
                                            curr[100]="QAR Qatar Rials";
                                            curr[101]="RON Romania New Lei";
                                            curr[102]="RSD Serbia Dinars";
                                            curr[103]="RUB Russia Rubles";
                                            curr[104]="SAR Saudi Arabia Riyals";
                                            curr[105]="SCR Seychelles Rupees";
                                            curr[106]="SEK Sweden Kronor";
                                            curr[107]="SOS Somalia Shillings";
                                            curr[108]="SYP Syria Pounds";
                                            curr[109]="THB Thailand Baht";
                                            curr[110]="TRY Turkey New Lira";
                                            curr[111]="TWD Taiwan New Dollars";
                                            curr[112]="UAH Ukraine Hryvnia";
                                            curr[113]="UYU Uruguay Pesos";
                                            curr[114]="UZS Uzbekistan Sums";
                                            curr[115]="VEF Venezuela Bolivares Fuertes";
                                            curr[116]="VND Vietnam Dong";
                                            curr[117]="YER Yemen Rials";
                                            curr[118]="ZAR South Africa Rand";
                                            curr[119]="USD United States of America Dollars";
                                            curr[120]="SVC El Salvador Colones";
                                            curr[121]="SGD Singapore Dollars";
                                            curr[122]="NZD New Zealand Dollars";
                                            curr[123]="NAD Namibia Dollars";
                                           curr[124]="MXN Mexico Pesos";
 //code storage
                                            currcode[0]="ALL";
                                            currcode[1]="BGN";
                                            currcode[2]="BZD";
                                            currcode[3]="BOB";
                                            currcode[4]="BRL";
                                            currcode[5]="BWP";
                                            currcode[6]="CHF";
                                            currcode[7]="CNY";
                                            currcode[8]="CRC";
                                            currcode[9]="CUP";
                                            currcode[10]="CZK";
                                            currcode[11]="DKK";
                                            currcode[12]="DOP";
                                            currcode[13]="EEK";
                                            currcode[14]="EGP";
                                            currcode[15]="EUR";
                                            currcode[16]="GBP";
                                            currcode[17]="GTQ";
                                            currcode[18]="SDD";
                                            currcode[19]="AUD";
                                            currcode[20]="ARS";
                                            currcode[21]="FJD";
                                            currcode[22]="COP";
                                            currcode[23]="CLP";
                                            currcode[24]="CAD";
                                            currcode[25]="CVE";
                                            currcode[26]="BHD";
                                            currcode[27]="BIF";
                                            currcode[28]="DJF";
                                            currcode[29]="DZD";
                                            currcode[30]="ETB";
                                            currcode[31]="GEL";
                                            currcode[32]="GHS";
                                            currcode[33]="GMD";
                                            currcode[34]="GNF";
                                            currcode[35]="HTG";
                                            currcode[36]="IQD";
                                            currcode[37]="JOD";
                                            currcode[38]="KES";
                                            currcode[39]="KMF";
                                            currcode[40]="KWD";
                                            currcode[41]="LSL";
                                            currcode[42]="LYD";
                                            currcode[43]="MAD";
                                            currcode[44]="MDL";
                                            currcode[45]="MGA";
                                            currcode[46]="MMK";
                                            currcode[47]="MOP";
                                            currcode[48]="MRO";
                                            currcode[49]="MVR";
                                            currcode[50]="MWK";
                                            currcode[51]="PGK";
                                            currcode[52]="RWF";
                                            currcode[53]="SDG";
                                            currcode[54]="SKK";
                                            currcode[55]="STD";
                                            currcode[56]="SZL";
                                            currcode[57]="TND";
                                            currcode[58]="TZS";
                                            currcode[59]="UGX";
                                            currcode[60]="XAF";
                                            currcode[61]="XOF";
                                            currcode[62]="XPF";
                                            currcode[63]="ZMK";
                                            currcode[64]="AED";
                                            currcode[65]="BDT";
                                            currcode[66]="HKD";
                                            currcode[67]="HNL";
                                            currcode[68]="HRK";
                                            currcode[69]="HUF";
                                            currcode[70]="IDR";
                                            currcode[71]="ILS";
                                            currcode[72]="INR";
                                            currcode[73]="IRR";
                                            currcode[74]="ISK";
                                            currcode[75]="JPY";
                                            currcode[76]="KHR";
                                            currcode[77]="KRW";
                                            currcode[78]="KZT";
                                            currcode[79]="LAK";
                                            currcode[80]="LBP";
                                            currcode[81]="BND";
                                            currcode[82]="BMD";
                                            currcode[83]="LKR";
                                            currcode[84]="LTL";
                                            currcode[85]="LVL";
                                            currcode[86]="MKD";
                                            currcode[87]="MUR";
                                            currcode[88]="MYR";
                                            currcode[89]="NGN";
                                            currcode[90]="NIO";
                                            currcode[91]="NOK";
                                            currcode[92]="NPR";
                                            currcode[93]="OMR";
                                            currcode[94]="PAB";
                                            currcode[95]="PEN";
                                            currcode[96]="PHP";
                                            currcode[97]="PKR";
                                            currcode[98]="PLN";
                                            currcode[99]="PYG";
                                            currcode[100]="QAR";
                                            currcode[101]="RON";
                                            currcode[102]="RSD";
                                            currcode[103]="RUB";
                                            currcode[104]="SAR";
                                            currcode[105]="SCR";
                                            currcode[106]="SEK";
                                            currcode[107]="SOS";
                                            currcode[108]="SYP";
                                            currcode[109]="THB";
                                            currcode[110]="TRY";
                                            currcode[111]="TWD";
                                            currcode[112]="UAH";
                                            currcode[113]="UYU";
                                            currcode[114]="UZS";
                                            currcode[115]="VEF";
                                            currcode[116]="VND";
                                            currcode[117]="YER";
                                            currcode[118]="ZAR";
                                            currcode[119]="USD";
                                            currcode[120]="SVC";
                                            currcode[121]="SGD";
                                            currcode[122]="NZD";
                                            currcode[123]="NAD";
                                           currcode[124]="MXN";

<%if(base!=null){%>
for(i=0;i<124;i++){

if(base==currcode[i] && base!=null ){
    

}else{
 newOpt = document.getElementById('budgethead_id').appendChild(document.createElement('option'));
                newOpt.value = currcode[i];
                newOpt.text = curr[i];
}

}
<%}else{%>
    document.getElementById('result').innerHTML="You Need to Set Base Currency";
    <%}%>


}
function check1()
{
     var keyValue = document.getElementById('budgethead_id').options[document.getElementById('budgethead_id').selectedIndex].value;
   if (keyValue=="Select")
    {

        alert("Select Budget Head");

        document.getElementById('budgethead_id').focus();

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
<body onload="loadcurrency();">

    <html:form method="post" onsubmit="return check1()" action="/currency">

<div
   style="  top:200px;
   left:350px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table>
        <tr><td>

                 <table border="1" class="table" width="400px" height="200px" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Currency Conversion</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">
                    <tr><td rowspan="5" class="txt2">Select Source Currency:
                            <html:select  styleId="budgethead_id" property="sCountry" style="width: 200px;">
                                            <html:option value="Select">Please select</html:option>
                                           

                                        </html:select>
                                            <br>Current date
                                            <br>
                                             <input type="textbox" name="date" value="<%=date%>"/><br/>
                                             <div id="result" class="err"></div>



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
