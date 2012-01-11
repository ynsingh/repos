<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.hbm.*" %>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN "http://www.w3.org/TR/html4/strict.dtd">
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>LibMS : Application Section</title>
        <%!
        String privilege[]=new String[11];
        String acq_privilege[]=new String[100];
       String cat_privilege[]=new String[100];
       String cir_privilege[]=new String[100];
       String ser_privilege[]=new String[100];
        %>
           <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
          <%
          String staff_role=(String)request.getAttribute("staff_role");
           String username=(String)session.getAttribute("username");

       String library_name=(String)session.getAttribute("library_name");
       String sublibrary_name=(String)session.getAttribute("sublibrary_name");
        String login_role=(String)session.getAttribute("login_role");

String staff_name=(String)request.getAttribute("staff_name");
 String role_name="";
            if(login_role.equals("insti-admin"))
                          role_name="Institute Admin";
                          else if(login_role.equals("dept-admin"))
                             role_name="Departmental Admin";
                          else if(login_role.equals("admin"))
                              role_name="Main Library Admin";
                          else if(login_role.equals("dept-staff"))
                              role_name="Department Staff";
                          else if(login_role.equals("staff"))
                              role_name="Staff";







       Privilege rst1=(Privilege)request.getAttribute("privilege_resultset");
       AcqPrivilege acq=(AcqPrivilege)request.getAttribute("acq_privilege_resultset");
       CatPrivilege cat=(CatPrivilege)request.getAttribute("cat_privilege_resultset");
       CirPrivilege cir=(CirPrivilege)request.getAttribute("cir_privilege_resultset");
       SerPrivilege ser=(SerPrivilege)request.getAttribute("ser_privilege_resultset");



//Assign the Privileges of Privilege Table In Array

           privilege[1]=rst1.getAcquisition();
           privilege[2]=rst1.getCataloguing();
           privilege[3]=rst1.getCirculation();
           privilege[4]=rst1.getSerial();
           privilege[5]=rst1.getAdministrator();
           privilege[6]=rst1.getSystemSetup();
           privilege[7]=rst1.getUtilities();
           privilege[8]=rst1.getOpac();



                acq_privilege[1]=acq.getAcq101();
                acq_privilege[2]=acq.getAcq102();
                acq_privilege[3]=acq.getAcq103();
                acq_privilege[4]=acq.getAcq104();
                acq_privilege[5]=acq.getAcq105();
                acq_privilege[6]=acq.getAcq106();
                acq_privilege[7]=acq.getAcq107();
                acq_privilege[8]=acq.getAcq108();
                acq_privilege[9]=acq.getAcq109();
                acq_privilege[10]=acq.getAcq110();
                acq_privilege[11]=acq.getAcq111();
                acq_privilege[12]=acq.getAcq112();
                acq_privilege[13]=acq.getAcq113();
                acq_privilege[14]=acq.getAcq114();
                acq_privilege[15]=acq.getAcq115();
                acq_privilege[16]=acq.getAcq116();
                acq_privilege[17]=acq.getAcq117();
                acq_privilege[18]=acq.getAcq118();
                acq_privilege[19]=acq.getAcq119();
                acq_privilege[20]=acq.getAcq120();
                acq_privilege[21]=acq.getAcq121();
                acq_privilege[22]=acq.getAcq122();
                acq_privilege[23]=acq.getAcq123();
                acq_privilege[24]=acq.getAcq124();
                acq_privilege[25]=acq.getAcq125();
                acq_privilege[26]=acq.getAcq126();
                acq_privilege[27]=acq.getAcq127();
                acq_privilege[28]=acq.getAcq128();
                acq_privilege[29]=acq.getAcq129();
                acq_privilege[30]=acq.getAcq130();
                acq_privilege[31]=acq.getAcq131();
                acq_privilege[32]=acq.getAcq132();
                acq_privilege[33]=acq.getAcq133();
                acq_privilege[34]=acq.getAcq134();
                acq_privilege[35]=acq.getAcq135();
                acq_privilege[36]=acq.getAcq136();
                acq_privilege[37]=acq.getAcq137();
                acq_privilege[38]=acq.getAcq138();
                acq_privilege[39]=acq.getAcq139();
                acq_privilege[40]=acq.getAcq140();
                acq_privilege[41]=acq.getAcq141();
                acq_privilege[42]=acq.getAcq142();
                acq_privilege[43]=acq.getAcq143();
                acq_privilege[44]=acq.getAcq144();
                acq_privilege[45]=acq.getAcq145();
                acq_privilege[46]=acq.getAcq146();
                acq_privilege[47]=acq.getAcq147();
                acq_privilege[48]=acq.getAcq148();
                acq_privilege[49]=acq.getAcq149();
                acq_privilege[50]=acq.getAcq150();
                acq_privilege[51]=acq.getAcq151();
                acq_privilege[52]=acq.getAcq152();
                acq_privilege[53]=acq.getAcq153();
                acq_privilege[54]=acq.getAcq154();
                acq_privilege[55]=acq.getAcq155();
                acq_privilege[56]=acq.getAcq156();
                acq_privilege[57]=acq.getAcq157();
                acq_privilege[58]=acq.getAcq158();
                acq_privilege[59]=acq.getAcq159();
                acq_privilege[60]=acq.getAcq160();
                acq_privilege[61]=acq.getAcq161();
                acq_privilege[62]=acq.getAcq162();
                acq_privilege[63]=acq.getAcq163();
                acq_privilege[64]=acq.getAcq164();
                acq_privilege[65]=acq.getAcq165();
                acq_privilege[66]=acq.getAcq166();
                acq_privilege[67]=acq.getAcq167();
                acq_privilege[68]=acq.getAcq168();
                acq_privilege[69]=acq.getAcq169();
                acq_privilege[70]=acq.getAcq170();
                acq_privilege[71]=acq.getAcq171();
                acq_privilege[72]=acq.getAcq172();
                acq_privilege[73]=acq.getAcq173();
                acq_privilege[74]=acq.getAcq174();
                acq_privilege[75]=acq.getAcq175();
                acq_privilege[76]=acq.getAcq176();
                acq_privilege[77]=acq.getAcq177();
                acq_privilege[78]=acq.getAcq178();
                acq_privilege[79]=acq.getAcq179();
                acq_privilege[80]=acq.getAcq180();
                acq_privilege[81]=acq.getAcq181();
                acq_privilege[82]=acq.getAcq182();
                acq_privilege[83]=acq.getAcq183();
                acq_privilege[84]=acq.getAcq184();
                acq_privilege[85]=acq.getAcq185();
                acq_privilege[86]=acq.getAcq186();
                acq_privilege[87]=acq.getAcq187();
                acq_privilege[88]=acq.getAcq188();
                acq_privilege[89]=acq.getAcq189();
                acq_privilege[90]=acq.getAcq190();
                acq_privilege[91]=acq.getAcq191();
                acq_privilege[92]=acq.getAcq192();
                acq_privilege[93]=acq.getAcq193();
                acq_privilege[94]=acq.getAcq194();
                acq_privilege[95]=acq.getAcq195();
                acq_privilege[96]=acq.getAcq196();
                acq_privilege[97]=acq.getAcq197();
                acq_privilege[98]=acq.getAcq198();
                acq_privilege[99]=acq.getAcq199();




                 cat_privilege[1]=cat.getCat201();
                 cat_privilege[2]=cat.getCat202();
                 cat_privilege[3]=cat.getCat203();
                 cat_privilege[4]=cat.getCat204();
                 cat_privilege[5]=cat.getCat205();
                 cat_privilege[6]=cat.getCat206();
                 cat_privilege[7]=cat.getCat207();
                 cat_privilege[8]=cat.getCat208();
                 cat_privilege[9]=cat.getCat209();
                 cat_privilege[10]=cat.getCat210();
                 cat_privilege[11]=cat.getCat211();
                 cat_privilege[12]=cat.getCat212();
                 cat_privilege[13]=cat.getCat213();
                 cat_privilege[14]=cat.getCat214();
                 cat_privilege[15]=cat.getCat215();
                 cat_privilege[16]=cat.getCat216();
                 cat_privilege[17]=cat.getCat217();
                 cat_privilege[18]=cat.getCat218();
                 cat_privilege[19]=cat.getCat219();
                 cat_privilege[20]=cat.getCat220();
                 cat_privilege[21]=cat.getCat221();
                 cat_privilege[22]=cat.getCat222();
                 cat_privilege[23]=cat.getCat223();
                 cat_privilege[24]=cat.getCat224();
                 cat_privilege[25]=cat.getCat225();
                 cat_privilege[26]=cat.getCat226();
                 cat_privilege[27]=cat.getCat227();
                 cat_privilege[28]=cat.getCat228();
                 cat_privilege[29]=cat.getCat229();
                 cat_privilege[30]=cat.getCat230();
                 cat_privilege[31]=cat.getCat231();
                 cat_privilege[32]=cat.getCat232();
                 cat_privilege[33]=cat.getCat233();
                 cat_privilege[34]=cat.getCat234();
                 cat_privilege[35]=cat.getCat235();
                 cat_privilege[36]=cat.getCat236();
                 cat_privilege[37]=cat.getCat237();
                 cat_privilege[38]=cat.getCat238();
                 cat_privilege[39]=cat.getCat239();
                 cat_privilege[40]=cat.getCat240();
                 cat_privilege[41]=cat.getCat241();
                 cat_privilege[42]=cat.getCat242();
                 cat_privilege[43]=cat.getCat243();
                 cat_privilege[44]=cat.getCat244();
                 cat_privilege[45]=cat.getCat245();
                 cat_privilege[46]=cat.getCat246();
                 cat_privilege[47]=cat.getCat247();
                 cat_privilege[48]=cat.getCat248();
                 cat_privilege[49]=cat.getCat249();
                 cat_privilege[50]=cat.getCat250();
                 cat_privilege[51]=cat.getCat251();
                 cat_privilege[52]=cat.getCat252();
                 cat_privilege[53]=cat.getCat253();
                 cat_privilege[54]=cat.getCat254();
                 cat_privilege[55]=cat.getCat255();
                 cat_privilege[56]=cat.getCat256();
                 cat_privilege[57]=cat.getCat257();
                 cat_privilege[58]=cat.getCat258();
                 cat_privilege[59]=cat.getCat259();
                 cat_privilege[60]=cat.getCat260();
                 cat_privilege[61]=cat.getCat261();
                 cat_privilege[62]=cat.getCat262();
                 cat_privilege[63]=cat.getCat263();
                 cat_privilege[64]=cat.getCat264();
                 cat_privilege[65]=cat.getCat265();
                 cat_privilege[66]=cat.getCat266();
                 cat_privilege[67]=cat.getCat267();
                 cat_privilege[68]=cat.getCat268();
                 cat_privilege[69]=cat.getCat269();
                 cat_privilege[70]=cat.getCat270();
                 cat_privilege[71]=cat.getCat271();
                 cat_privilege[72]=cat.getCat272();
                 cat_privilege[73]=cat.getCat273();
                 cat_privilege[74]=cat.getCat274();
                 cat_privilege[75]=cat.getCat275();
                 cat_privilege[76]=cat.getCat276();
                 cat_privilege[77]=cat.getCat277();
                 cat_privilege[78]=cat.getCat278();
                 cat_privilege[79]=cat.getCat279();
                 cat_privilege[80]=cat.getCat280();
                 cat_privilege[81]=cat.getCat281();
                 cat_privilege[82]=cat.getCat282();
                 cat_privilege[83]=cat.getCat283();
                 cat_privilege[84]=cat.getCat284();
                 cat_privilege[85]=cat.getCat285();
                 cat_privilege[86]=cat.getCat286();
                 cat_privilege[87]=cat.getCat287();
                 cat_privilege[88]=cat.getCat288();
                 cat_privilege[89]=cat.getCat289();
                 cat_privilege[90]=cat.getCat290();
                 cat_privilege[91]=cat.getCat291();
                 cat_privilege[92]=cat.getCat292();
                 cat_privilege[93]=cat.getCat293();
                 cat_privilege[94]=cat.getCat294();
                 cat_privilege[95]=cat.getCat295();
                 cat_privilege[96]=cat.getCat296();
                 cat_privilege[97]=cat.getCat297();
                 cat_privilege[98]=cat.getCat298();
                 cat_privilege[99]=cat.getCat299();


                 cir_privilege[1]=cir.getCir301();
                 cir_privilege[2]=cir.getCir302();
                 cir_privilege[3]=cir.getCir303();
                 cir_privilege[4]=cir.getCir304();
                 cir_privilege[5]=cir.getCir305();
                 cir_privilege[6]=cir.getCir306();
                 cir_privilege[7]=cir.getCir307();
                 cir_privilege[8]=cir.getCir308();
                 cir_privilege[9]=cir.getCir309();
                 cir_privilege[10]=cir.getCir310();
                 cir_privilege[11]=cir.getCir311();
                 cir_privilege[12]=cir.getCir312();
                 cir_privilege[13]=cir.getCir313();
                 cir_privilege[14]=cir.getCir314();
                 cir_privilege[15]=cir.getCir315();
                 cir_privilege[16]=cir.getCir316();
                 cir_privilege[17]=cir.getCir317();
                 cir_privilege[18]=cir.getCir318();
                 cir_privilege[19]=cir.getCir319();
                 cir_privilege[20]=cir.getCir320();
                 cir_privilege[21]=cir.getCir321();
                 cir_privilege[22]=cir.getCir322();
                 cir_privilege[23]=cir.getCir323();
                 cir_privilege[24]=cir.getCir324();
                 cir_privilege[25]=cir.getCir325();
                 cir_privilege[26]=cir.getCir326();
                 cir_privilege[27]=cir.getCir327();
                 cir_privilege[28]=cir.getCir328();
                 cir_privilege[29]=cir.getCir329();
                 cir_privilege[30]=cir.getCir330();
                 cir_privilege[31]=cir.getCir331();
                 cir_privilege[32]=cir.getCir332();
                 cir_privilege[33]=cir.getCir333();
                 cir_privilege[34]=cir.getCir334();
                 cir_privilege[35]=cir.getCir335();
                 cir_privilege[36]=cir.getCir336();
                 cir_privilege[37]=cir.getCir337();
                 cir_privilege[38]=cir.getCir338();
                 cir_privilege[39]=cir.getCir339();
                 cir_privilege[40]=cir.getCir340();
                 cir_privilege[41]=cir.getCir341();
                 cir_privilege[42]=cir.getCir342();
                 cir_privilege[43]=cir.getCir343();
                 cir_privilege[44]=cir.getCir344();
                 cir_privilege[45]=cir.getCir345();
                 cir_privilege[46]=cir.getCir346();
                 cir_privilege[47]=cir.getCir347();
                 cir_privilege[48]=cir.getCir348();
                 cir_privilege[49]=cir.getCir349();
                 cir_privilege[50]=cir.getCir350();
                 cir_privilege[51]=cir.getCir351();
                 cir_privilege[52]=cir.getCir352();
                 cir_privilege[53]=cir.getCir353();
                 cir_privilege[54]=cir.getCir354();
                 cir_privilege[55]=cir.getCir355();
                 cir_privilege[56]=cir.getCir356();
                 cir_privilege[57]=cir.getCir357();
                 cir_privilege[58]=cir.getCir358();
                 cir_privilege[59]=cir.getCir359();
                 cir_privilege[60]=cir.getCir360();
                 cir_privilege[61]=cir.getCir361();
                 cir_privilege[62]=cir.getCir362();
                 cir_privilege[63]=cir.getCir363();
                 cir_privilege[64]=cir.getCir364();
                 cir_privilege[65]=cir.getCir365();
                 cir_privilege[66]=cir.getCir366();
                 cir_privilege[67]=cir.getCir367();
                 cir_privilege[68]=cir.getCir368();
                 cir_privilege[69]=cir.getCir369();
                 cir_privilege[70]=cir.getCir370();
                 cir_privilege[71]=cir.getCir371();
                 cir_privilege[72]=cir.getCir372();
                 cir_privilege[73]=cir.getCir373();
                 cir_privilege[74]=cir.getCir374();
                 cir_privilege[75]=cir.getCir375();
                 cir_privilege[76]=cir.getCir376();
                 cir_privilege[77]=cir.getCir377();
                 cir_privilege[78]=cir.getCir378();
                 cir_privilege[79]=cir.getCir379();
                 cir_privilege[80]=cir.getCir380();
                 cir_privilege[81]=cir.getCir381();
                 cir_privilege[82]=cir.getCir382();
                 cir_privilege[83]=cir.getCir383();
                 cir_privilege[84]=cir.getCir384();
                 cir_privilege[85]=cir.getCir385();
                 cir_privilege[86]=cir.getCir386();
                 cir_privilege[87]=cir.getCir387();
                 cir_privilege[88]=cir.getCir388();
                 cir_privilege[89]=cir.getCir389();
                 cir_privilege[90]=cir.getCir390();
                 cir_privilege[91]=cir.getCir391();
                 cir_privilege[92]=cir.getCir392();
                 cir_privilege[93]=cir.getCir393();
                 cir_privilege[94]=cir.getCir394();
                 cir_privilege[95]=cir.getCir395();
                 cir_privilege[96]=cir.getCir396();
                 cir_privilege[97]=cir.getCir397();
                 cir_privilege[98]=cir.getCir398();
                 cir_privilege[99]=cir.getCir399();



                 ser_privilege[1]=ser.getSer401();
                 ser_privilege[2]=ser.getSer402();
                 ser_privilege[3]=ser.getSer403();
                 ser_privilege[4]=ser.getSer404();
                 ser_privilege[5]=ser.getSer405();
                 ser_privilege[6]=ser.getSer406();
                 ser_privilege[7]=ser.getSer407();
                 ser_privilege[8]=ser.getSer408();
                 ser_privilege[9]=ser.getSer409();
                 ser_privilege[10]=ser.getSer410();
                 ser_privilege[11]=ser.getSer411();
                 ser_privilege[12]=ser.getSer412();
                 ser_privilege[13]=ser.getSer413();
                 ser_privilege[14]=ser.getSer414();
                 ser_privilege[15]=ser.getSer415();
                 ser_privilege[16]=ser.getSer416();
                 ser_privilege[17]=ser.getSer417();
                 ser_privilege[18]=ser.getSer418();
                 ser_privilege[19]=ser.getSer419();
                 ser_privilege[20]=ser.getSer420();
                 ser_privilege[21]=ser.getSer421();
                 ser_privilege[22]=ser.getSer422();
                 ser_privilege[23]=ser.getSer423();
                 ser_privilege[24]=ser.getSer424();
                 ser_privilege[25]=ser.getSer425();
                 ser_privilege[26]=ser.getSer426();
                 ser_privilege[27]=ser.getSer427();
                 ser_privilege[28]=ser.getSer428();
                 ser_privilege[29]=ser.getSer429();
                 ser_privilege[30]=ser.getSer430();
                 ser_privilege[31]=ser.getSer431();
                 ser_privilege[32]=ser.getSer432();
                 ser_privilege[33]=ser.getSer433();
                 ser_privilege[34]=ser.getSer434();
                 ser_privilege[35]=ser.getSer435();
                 ser_privilege[36]=ser.getSer436();
                 ser_privilege[37]=ser.getSer437();
                 ser_privilege[38]=ser.getSer438();
                 ser_privilege[39]=ser.getSer439();
                 ser_privilege[40]=ser.getSer440();
                 ser_privilege[41]=ser.getSer441();
                 ser_privilege[42]=ser.getSer442();
                 ser_privilege[43]=ser.getSer443();
                 ser_privilege[44]=ser.getSer444();
                 ser_privilege[45]=ser.getSer445();
                 ser_privilege[46]=ser.getSer446();
                 ser_privilege[47]=ser.getSer447();
                 ser_privilege[48]=ser.getSer448();
                 ser_privilege[49]=ser.getSer449();
                 ser_privilege[50]=ser.getSer450();
                 ser_privilege[51]=ser.getSer451();
                 ser_privilege[52]=ser.getSer452();
                 ser_privilege[53]=ser.getSer453();
                 ser_privilege[54]=ser.getSer454();
                 ser_privilege[55]=ser.getSer455();
                 ser_privilege[56]=ser.getSer456();
                 ser_privilege[57]=ser.getSer457();
                 ser_privilege[58]=ser.getSer458();
                 ser_privilege[59]=ser.getSer459();
                 ser_privilege[60]=ser.getSer460();
                 ser_privilege[61]=ser.getSer461();
                 ser_privilege[62]=ser.getSer462();
                 ser_privilege[63]=ser.getSer463();
                 ser_privilege[64]=ser.getSer464();
                 ser_privilege[65]=ser.getSer465();
                 ser_privilege[66]=ser.getSer466();
                 ser_privilege[67]=ser.getSer467();
                 ser_privilege[68]=ser.getSer468();
                 ser_privilege[69]=ser.getSer469();
                 ser_privilege[70]=ser.getSer470();
                 ser_privilege[71]=ser.getSer471();
                 ser_privilege[72]=ser.getSer472();
                 ser_privilege[73]=ser.getSer473();
                 ser_privilege[74]=ser.getSer474();
                 ser_privilege[75]=ser.getSer475();
                 ser_privilege[76]=ser.getSer476();
                 ser_privilege[77]=ser.getSer477();
                 ser_privilege[78]=ser.getSer478();
                 ser_privilege[79]=ser.getSer479();
                 ser_privilege[80]=ser.getSer480();
                 ser_privilege[81]=ser.getSer481();
                 ser_privilege[82]=ser.getSer482();
                 ser_privilege[83]=ser.getSer483();
                 ser_privilege[84]=ser.getSer484();
                 ser_privilege[85]=ser.getSer485();
                 ser_privilege[86]=ser.getSer486();
                 ser_privilege[87]=ser.getSer487();
                 ser_privilege[88]=ser.getSer488();
                 ser_privilege[89]=ser.getSer489();
                 ser_privilege[90]=ser.getSer490();
                 ser_privilege[91]=ser.getSer491();
                 ser_privilege[92]=ser.getSer492();
                 ser_privilege[93]=ser.getSer493();
                 ser_privilege[94]=ser.getSer494();
                 ser_privilege[95]=ser.getSer495();
                 ser_privilege[96]=ser.getSer496();
                 ser_privilege[97]=ser.getSer497();
                 ser_privilege[98]=ser.getSer498();
                 ser_privilege[99]=ser.getSer499();






%>
        <!-- Standard reset and fonts -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/build/reset/reset.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/build/fonts/fonts.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/build/container/assets/container.css">

        <!-- CSS for Menu -->
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/build/menu/assets/menu.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/page.css">
        <!-- Page-specific styles -->
     <%--  <style type="text/css">


            body {

                background-color:white;

            }


            /* Define a new style for each menubar */

            div.yuimenubar {

                border-width:1px 0;
                border-color:#666;
                border-style:solid;
            background-color: #E0E8F5;


            }

            div.yuimenubar div.bd {

                border-width:1px 0;
                border-color:#ddd;
                border-style:solid;

            }

            div.yuimenubar li.yuimenubaritem {

                border-width:0;
                border-style:none;
                padding:4px 12px;
                font-family:arial;
                font-size:11px;
                 background-color: #E0E8F5;
                font-weight:bold;

            }

            div.yuimenubar li.yuimenubaritem img {

                margin:0;
                border:0;
                height:1px;
                width:1px;

            }


            /* Define a new style for each menu */

            div.yuimenu {

                border:solid 1px #666;
                 background-color: #E0E8F5;

            }

            div.yuimenu div.bd {

                border-width:0;
                border-style:none;

            }


            /* Define a new style for each menu item */

            div.yuimenu li.yuimenuitem {

                padding-top:4px;
                padding-bottom:4px;

            }

            div.yuimenu li.yuimenuitem img {

                height:8px;
                width:8px;
                margin:0 -16px 0 10px;
                border:0;

            }

            div.yuimenu ul {

                border:solid 1px #666;
                border-width:1px 0 0 0;

            }


            /* Define a new style for an item's "selected" state */

            div.yuimenu li.selected,
            div.yuimenubar li.selected {

                background-color:#039;

            }

            div.yuimenu li.selected a.selected,
            div.yuimenubar li.selected a.selected {

                text-decoration:none;

            }


            /* Define a new style for an item's "disabled" state */

            div.yuimenu li.disabled a.disabled,
            div.yuimenu li.disabled em.disabled,
            div.yuimenubar li.disabled a.disabled {

                color:#666;

            }

        </style>--%>
      <style type="text/css">


            body {

                background-color:white;

            }


            /* Define a new style for each menubar */

            div.yuimenubar {

                border-width:1px 0;
                border-color:#666;
                border-style:solid;
                background-color:pink;


            }

            div.yuimenubar div.bd {

                border-width:1px 0;
                border-color:#ddd;
                border-style:solid;

            }

            div.yuimenubar li.yuimenubaritem {

                border-width:0;
                border-style:none;
                padding:4px 12px;
                font-family:arial;
                font-size:11px;
                color:brown;
                font-weight:bold;

            }

            div.yuimenubar li.yuimenubaritem img {

                margin:0;
                border:0;
                height:1px;
                width:1px;

            }


            /* Define a new style for each menu */

            div.yuimenu {

                border:solid 1px #666;
                background-color:#ccc;

            }

            div.yuimenu div.bd {

                border-width:0;
                border-style:none;

            }


            /* Define a new style for each menu item */

            div.yuimenu li.yuimenuitem {

                padding-top:4px;
                padding-bottom:4px;

            }

            div.yuimenu li.yuimenuitem img {

                height:8px;
                width:8px;
                margin:0 -16px 0 10px;
                border:0;

            }

            div.yuimenu ul {

                border:solid 1px #666;
                border-width:1px 0 0 0;

            }


            /* Define a new style for an item's "selected" state */

            div.yuimenu li.selected,
            div.yuimenubar li.selected {

                background-color:#039;

            }

            div.yuimenu li.selected a.selected,
            div.yuimenubar li.selected a.selected {

                text-decoration:none;

            }


            /* Define a new style for an item's "disabled" state */

            div.yuimenu li.disabled a.disabled,
            div.yuimenu li.disabled em.disabled,
            div.yuimenubar li.disabled a.disabled {

                color:#666;

            }

        </style>



        <!-- Namespace source file -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/build/yahoo/yahoo.js"></script>

        <!-- Dependency source files -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/build/event/event.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/build/dom/dom.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/build/dragdrop/dragdrop.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/build/animation/animation.js"></script>

        <!-- Container source file -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/build/container/container.js"></script>

        <!-- Menu source file -->
        <script type="text/javascript" src="<%=request.getContextPath()%>/build/menu/menu.js"></script>


        <!-- Page-specific script -->
        <script type="text/javascript">

            /**
            * Constant representing the path to the image to be used for the
            * submenu arrow indicator.
            * @final
            * @type String
            */
            YAHOO.widget.MenuBarItem.prototype.SUBMENU_INDICATOR_IMAGE_PATH =
                "promo/m/irs/blank.gif";


            /**
            * Constant representing the path to the image to be used for the
            * submenu arrow indicator when a MenuBarItem instance is selected.
            * @final
            * @type String
            */
            YAHOO.widget.MenuBarItem.prototype.SELECTED_SUBMENU_INDICATOR_IMAGE_PATH =
                "promo/m/irs/blank.gif";


            /**
            * Constant representing the path to the image to be used for the
            * submenu arrow indicator when a MenuBarItem instance is
            * @final
            * @type String
            */
            YAHOO.widget.MenuBarItem.prototype.DISABLED_SUBMENU_INDICATOR_IMAGE_PATH =
                "promo/m/irs/blank.gif";


            // "load" event handler for the window

            YAHOO.example.onWindowLoad = function(p_oEvent) {


                // "click" event handler for each item in the root MenuBar instance

                function onMenuBarItemClick(p_sType, p_aArguments) {

                    this.parent.hasFocus = true;

                    var oActiveItem = this.parent.activeItem;


                    // Hide any other submenus that might be visible

                    if(oActiveItem && oActiveItem != this) {

                        this.parent.clearActiveItem();

                    }


                    // Select and focus the current MenuItem instance

                    this.cfg.setProperty("selected", true);
                    this.focus();


                    // Show the submenu for this instance

                    var oSubmenu = this.cfg.getProperty("submenu");

                    if(oSubmenu) {

                        if(oSubmenu.cfg.getProperty("visible")) {

                            oSubmenu.hide();

                        }
                        else {

                            oSubmenu.show();

                        }

                    }

                }


                // "mouseover" event handler for each item in the root MenuBar instance

                function onMenuBarItemMouseOver(p_sType, p_aArguments) {

                    var oActiveItem = this.parent.activeItem;


                    // Hide any other submenus that might be visible

                    if(oActiveItem && oActiveItem != this) {

                        this.parent.clearActiveItem();

                    }


                    // Select and focus the current MenuItem instance

                    this.cfg.setProperty("selected", true);
                    this.focus();

                    if(this.parent.hasFocus) {

                        // Show the submenu for this instance

                        var oSubmenu = this.cfg.getProperty("submenu");

                        if(oSubmenu) {

                            if(oSubmenu.cfg.getProperty("visible")) {

                                oSubmenu.hide();

                            }
                            else {

                                oSubmenu.show();

                            }

                        }

                    }

                }






//Start:Serial  Menu *********************************************************************************


//Start: Third Level Serial Menu 3333333333333333333333333333333333333333333333333333333333333333333333
 //var oSerRequestToVendorMenu = new YAHOO.widget.Menu("Request To Vendor");
 var oSerRequestToVendorMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial1") %>");

                //oSerRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("Direct Request", {  disabled: <%=ser_privilege[11]%>,  url: ""}));
                oSerRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial2") %>", {  disabled: <%=ser_privilege[11]%>,  url: ""}));
                //oSerRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("Approved Request", {  disabled: <%=ser_privilege[12]%>,  url: ""}));
                oSerRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial3") %>", {  disabled: <%=ser_privilege[12]%>,  url: ""}));

               // var oSerStatusUpdateMenu = new YAHOO.widget.Menu("Status Update");
                var oSerStatusUpdateMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial4") %>");
                //oSerStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("Vendor Request", {  disabled: <%=ser_privilege[14]%>,  url: ""}));
                oSerStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial5") %>", {  disabled: <%=ser_privilege[14]%>,  url: ""}));
               // oSerStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("Approval Status", {  disabled: <%=ser_privilege[15]%>,  url: ""}));
            oSerStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial6") %>", {  disabled: <%=ser_privilege[15]%>,  url: ""}));



              //  var oSerViewSubscriptionListMenu = new YAHOO.widget.Menu("View Subscription List");
              var oSerViewSubscriptionListMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial7") %>");

              //  oSerViewSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Direct Request", {  disabled: <%=ser_privilege[19]%>,  url: ""}));
                oSerViewSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial8") %>", {  disabled: <%=ser_privilege[19]%>,  url: ""}));
                //oSerViewSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Approved Request", {  disabled: <%=ser_privilege[20]%>,  url: ""}));
                oSerViewSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial3") %>", {  disabled: <%=ser_privilege[20]%>,  url: ""}));



               // var oSerBindingMenu = new YAHOO.widget.Menu("Binding");
                var oSerBindingMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial9") %>");

              //  oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Prepare Binding List", {  disabled: <%=ser_privilege[39]%>,  url: ""}));
               oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial10") %>", {  disabled: <%=ser_privilege[39]%>,  url: ""}));
              //  oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Binding Order", {  disabled: <%=ser_privilege[40]%>,  url: ""}));
              oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial11") %>", {  disabled: <%=ser_privilege[40]%>,  url: ""}));
              //  oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Binding Update", {  disabled: <%=ser_privilege[41]%>,  url: ""}));
               oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial12") %>", {  disabled: <%=ser_privilege[41]%>,  url: ""}));
                //oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Receive Binded Update", {  disabled: <%=ser_privilege[42]%>,  url: ""}));
                oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial13") %>", {  disabled: <%=ser_privilege[42]%>,  url: ""}));
              //  oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Prepare Binding Slip", {  disabled: <%=ser_privilege[43]%>,  url: ""}));
                oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial4") %>", {  disabled: <%=ser_privilege[43]%>,  url: ""}));
               // oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("Document Slip", {  disabled: <%=ser_privilege[44]%>,  url: ""}));
               oSerBindingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial15") %>", {  disabled: <%=ser_privilege[44]%>,  url: ""}));



              //  var oSerAccessioningMenu = new YAHOO.widget.Menu("Accessioning");
                var oSerAccessioningMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial16") %>");

              //  oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Generate Accession No", {  disabled: <%=ser_privilege[46]%>,  url: ""}));
                oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial17") %>", {  disabled: <%=ser_privilege[46]%>,  url: ""}));
                //oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Change Accession No", {  disabled: <%=ser_privilege[47]%>,  url: ""}));
                oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial18") %>", {  disabled: <%=ser_privilege[47]%>,  url: ""}));
               // oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", {  disabled: <%=ser_privilege[48]%>,  url: ""}));
                 oSerAccessioningMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial19") %>", {  disabled: <%=ser_privilege[48]%>,  url: ""}));


               // var oSerArticleRegisterMenu = new YAHOO.widget.Menu("Article Register");
                var oSerArticleRegisterMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial20") %>");

                //oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("Add", {  disabled: <%=ser_privilege[51]%>,  url: ""}));
                oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial21") %>", {  disabled: <%=ser_privilege[51]%>,  url: ""}));
              //  oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("Modify", {  disabled: <%=ser_privilege[52]%>,  url: ""}));
                oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial22") %>", {  disabled: <%=ser_privilege[52]%>,  url: ""}));
               // oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("Remove", {  disabled: <%=ser_privilege[53]%>,  url: ""}));
                oSerArticleRegisterMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial23") %>", {  disabled: <%=ser_privilege[53]%>,  url: ""}));


               // var oSerArticleRetrievalMenu = new YAHOO.widget.Menu("Article Retrieval");
                var oSerArticleRetrievalMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial24") %>");

              //  oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("Web Search", {  disabled: <%=ser_privilege[57]%>,  url: ""}));
               oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial25") %>", {  disabled: <%=ser_privilege[57]%>,  url: ""}));

               // oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("Article Document", {  disabled: <%=ser_privilege[58]%>,  url: ""}));
                oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial26") %>", {  disabled: <%=ser_privilege[58]%>,  url: ""}));
              //  oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("Article Bibliography", {  disabled: <%=ser_privilege[59]%>,  url: ""}));
                oSerArticleRetrievalMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial27") %>", {  disabled: <%=ser_privilege[59]%>,  url: ""}));


              //  var oSerMiscellaneousMenu = new YAHOO.widget.Menu("Miscellaneous");
            var oSerMiscellaneousMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial28") %>");
             //   oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Multimedia", {  disabled: <%=ser_privilege[61]%>,  url: ""}));
                oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial29") %>", {  disabled: <%=ser_privilege[61]%>,  url: ""}));
                //oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("List By Journal", {  disabled: <%=ser_privilege[62]%>,  url: ""}));
                oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial30") %>", {  disabled: <%=ser_privilege[62]%>,  url: ""}));
               // oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("Classified Subject", {  disabled: <%=ser_privilege[63]%>,  url: ""}));
             oSerMiscellaneousMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial31") %>", {  disabled: <%=ser_privilege[63]%>,  url: ""}));


//End: Third Level Serial Menu 3333333333333333333333333333333333333333333333333333333333333333333333
//Start: Second Level Serial Menu 2222222222222222222222222222222222222222222222222222222222222222222222

               // var oSerSubscriptionListMenu = new YAHOO.widget.Menu("Prepare Subscription List");
                 var oSerSubscriptionListMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial32") %>");

               // oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Refer From Serial Catalogue", {  disabled: <%=ser_privilege[2]%>,  url:""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial33") %>", {  disabled: <%=ser_privilege[2]%>,  url:""}));


               // oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Refer From Demand List", {  disabled: <%=ser_privilege[3]%>,  url: ""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial34") %>", {  disabled: <%=ser_privilege[3]%>,  url: ""}));

               // oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("New Entry", {  disabled: <%=ser_privilege[4]%>,  url: ""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial35") %>", {  disabled: <%=ser_privilege[4]%>,  url: ""}));
                //oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("Request For Specimen Copy", {  disabled: <%=ser_privilege[5]%>,  url: ""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial36") %>", {  disabled: <%=ser_privilege[5]%>,  url: ""}));
               // oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("New Serial Status", {  disabled: <%=ser_privilege[6]%>,  url: ""}));
                oSerSubscriptionListMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial37") %>", {  disabled: <%=ser_privilege[6]%>,  url: ""}));

                //var oSerApprovalProcessMenu = new YAHOO.widget.Menu("Approval Process");
                var oSerApprovalProcessMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial38") %>");

                //oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("Add Serial Into Approval List", {  disabled: <%=ser_privilege[8]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial39") %>", {  disabled: <%=ser_privilege[8]%>,  url: ""}));
                //oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("Approve/Reject Serial", {  disabled: <%=ser_privilege[9]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial40") %>", {  disabled: <%=ser_privilege[9]%>,  url: ""}));
                //oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("Request To Vendor", {    submenu:oSerRequestToVendorMenu  ,  disabled: <%=ser_privilege[10]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial41") %>", {    submenu:oSerRequestToVendorMenu  ,  disabled: <%=ser_privilege[10]%>,  url: ""}));
                //oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("Status Update", {    submenu:oSerStatusUpdateMenu ,  disabled: <%=ser_privilege[13]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial42") %>", {    submenu:oSerStatusUpdateMenu ,  disabled: <%=ser_privilege[13]%>,  url: ""}));
                //oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("List Of Serials", {  disabled: <%=ser_privilege[16]%>,  url: ""}));
                oSerApprovalProcessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial43") %>", {  disabled: <%=ser_privilege[16]%>,  url: ""}));

                //var oSerOrderSubscriptionMenu = new YAHOO.widget.Menu("Order Subscription");
                var oSerOrderSubscriptionMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial44") %>");

                //oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("View Subscription List", {submenu:oSerViewSubscriptionListMenu ,  disabled: <%=ser_privilege[18]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial45") %>", {submenu:oSerViewSubscriptionListMenu ,  disabled: <%=ser_privilege[18]%>,  url: ""}));
                //oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Initiate Subscription/Ordering", {  disabled: <%=ser_privilege[21]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial46") %>", {  disabled: <%=ser_privilege[21]%>,  url: ""}));
                //oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Duplicate Subscription", {  disabled: <%=ser_privilege[22]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial47") %>", {  disabled: <%=ser_privilege[22]%>,  url: ""}));
                //oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Update Subscription Detail", {  disabled: <%=ser_privilege[23]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial48") %>", {  disabled: <%=ser_privilege[23]%>,  url: ""}));
                //oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Initial Old Subscription", {  disabled: <%=ser_privilege[24]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial49") %>", {  disabled: <%=ser_privilege[24]%>,  url: ""}));
                //oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Renewal Subscription", {  disabled: <%=ser_privilege[25]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial50") %>", {  disabled: <%=ser_privilege[25]%>,  url: ""}));
                //oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("Cancel/Reorder Subscription", {  disabled: <%=ser_privilege[26]%>,  url: ""}));
                oSerOrderSubscriptionMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial51") %>", {  disabled: <%=ser_privilege[26]%>,  url: ""}));



                //var oSerPaymentProcessMenu = new YAHOO.widget.Menu("Payment Process");

                var oSerPaymentProcessMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial52") %>");

                //oSerPaymentProcessMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", {  disabled: <%=ser_privilege[29]%>,  url: ""}));
                oSerPaymentProcessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial53") %>", {  disabled: <%=ser_privilege[29]%>,  url: ""}));
              //  oSerPaymentProcessMenu.addItem(new YAHOO.widget.MenuItem("Payment update", {  disabled: <%=ser_privilege[30]%>,  url: ""}));
               oSerPaymentProcessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial54") %>", {  disabled: <%=ser_privilege[30]%>,  url: ""}));



               // var oSerIssueManagementMenu = new YAHOO.widget.Menu("Issues Management");
                var oSerIssueManagementMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial55") %>");
                //oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("Registering Issue", {  disabled: <%=ser_privilege[33]%>,  url: ""}));
                oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial56") %>", {  disabled: <%=ser_privilege[33]%>,  url: ""}));
                //oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("Additional Issue", {  disabled: <%=ser_privilege[34]%>,  url: ""}));
                oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial57") %>", {  disabled: <%=ser_privilege[34]%>,  url: ""}));
                //oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("Annual Issue", {  disabled: <%=ser_privilege[35]%>,  url: ""}));
                oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial58") %>", {  disabled: <%=ser_privilege[35]%>,  url: ""}));
                //oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("Claim Monitoring", {  disabled: <%=ser_privilege[36]%>,  url: ""}));
                oSerIssueManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial59") %>", {  disabled: <%=ser_privilege[36]%>,  url: ""}));


               // var oSerBindingManagementMenu = new YAHOO.widget.Menu("Binding Management");
                var oSerBindingManagementMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial60") %>");

             //   oSerBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Binding", {submenu:oSerBindingMenu ,  disabled: <%=ser_privilege[38]%>,  url: ""}));
                oSerBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial61") %>", {submenu:oSerBindingMenu ,  disabled: <%=ser_privilege[38]%>,  url: ""}));
                //oSerBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Accessioning", {submenu:oSerAccessioningMenu ,  disabled: <%=ser_privilege[45]%>,  url: ""}));
                    oSerBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial62") %>", {submenu:oSerAccessioningMenu ,  disabled: <%=ser_privilege[45]%>,  url: ""}));


               // var oSerArticleIndexingMenu = new YAHOO.widget.Menu("Article Indexing");
                            var oSerArticleIndexingMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial63") %>");
               // oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Article Register", {submenu:oSerArticleRegisterMenu ,  disabled: <%=ser_privilege[50]%>,  url: ""}));
                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial64") %>", {submenu:oSerArticleRegisterMenu ,  disabled: <%=ser_privilege[50]%>,  url: ""}));
              //  oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Article Detail", {  disabled: <%=ser_privilege[54]%>,  url: ""}));
                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial65") %>", {  disabled: <%=ser_privilege[54]%>,  url: ""}));
               // oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Article List", {  disabled: <%=ser_privilege[55]%>,   url: ""}));
                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial66") %>", {  disabled: <%=ser_privilege[55]%>,   url: ""}));
               // oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Article Retrieval", {submenu:oSerArticleRetrievalMenu ,  disabled: <%=ser_privilege[56]%>,  url: ""}));
                oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial67") %>", {submenu:oSerArticleRetrievalMenu ,  disabled: <%=ser_privilege[56]%>,  url: ""}));
               // oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("Miscellaneous", {submenu:oSerMiscellaneousMenu ,  disabled: <%=ser_privilege[60]%>,  url: ""}));
                 oSerArticleIndexingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial68") %>", {submenu:oSerMiscellaneousMenu ,  disabled: <%=ser_privilege[60]%>,  url: ""}));




//End: Secondt Level Serial Menu 2222222222222222222222222222222222222222222222222222222222222222222222
//Start: First Level Serial Menu 11111111111111111111111111111111111111111111111111111111111111111111111
//submenu of serial
                //var oSerMenu = new YAHOO.widget.Menu("Serial", { zIndex:2 });
                var oSerMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.serial69") %>", { zIndex:2 });

                //oSerMenu.addItem(new YAHOO.widget.MenuItem(" Prepare Subscription List", {submenu:oSerSubscriptionListMenu ,  disabled: <%=ser_privilege[1]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem(" <%=resource.getString("admin.header.serial70") %>", {submenu:oSerSubscriptionListMenu ,  disabled: <%=ser_privilege[1]%>,  url:"" } ));
                //oSerMenu.addItem(new YAHOO.widget.MenuItem("Approval Process", {submenu:oSerApprovalProcessMenu ,  disabled: <%=ser_privilege[7]%>,  url:"" }));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial71") %>", {submenu:oSerApprovalProcessMenu ,  disabled: <%=ser_privilege[7]%>,  url:"" }));
                //oSerMenu.addItem(new YAHOO.widget.MenuItem("Order Subscription", {submenu:oSerOrderSubscriptionMenu ,  disabled: <%=ser_privilege[17]%>,  url:"" }));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial72") %>", {submenu:oSerOrderSubscriptionMenu ,  disabled: <%=ser_privilege[17]%>,  url:"" }));
                //oSerMenu.addItem(new YAHOO.widget.MenuItem("Receive Subscription", {   disabled: <%=ser_privilege[27]%>,  url:""}));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial73") %>", {   disabled: <%=ser_privilege[27]%>,  url:""}));
               // oSerMenu.addItem(new YAHOO.widget.MenuItem("Payment Process", {submenu:oSerPaymentProcessMenu ,  disabled: <%=ser_privilege[28]%>,  url:""} ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial74") %>", {submenu:oSerPaymentProcessMenu ,  disabled: <%=ser_privilege[28]%>,  url:""} ));
                //oSerMenu.addItem(new YAHOO.widget.MenuItem("Invoice Process", {  disabled: <%=ser_privilege[31]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial75") %>", {  disabled: <%=ser_privilege[31]%>,  url:"" } ));
                //oSerMenu.addItem(new YAHOO.widget.MenuItem("Issues Management", {submenu:oSerIssueManagementMenu ,  disabled: <%=ser_privilege[32]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial76") %>", {submenu:oSerIssueManagementMenu ,  disabled: <%=ser_privilege[32]%>,  url:"" } ));
                //oSerMenu.addItem(new YAHOO.widget.MenuItem("Binding Management", {submenu:oSerBindingManagementMenu ,  disabled: <%=ser_privilege[37]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial77") %>", {submenu:oSerBindingManagementMenu ,  disabled: <%=ser_privilege[37]%>,  url:"" } ));
                //oSerMenu.addItem(new YAHOO.widget.MenuItem("Article Indexing", {submenu:oSerArticleIndexingMenu ,   disabled: <%=ser_privilege[49]%>,  url:"" } ));
                oSerMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.serial78") %>", {submenu:oSerArticleIndexingMenu ,   disabled: <%=ser_privilege[49]%>,  url:"" } ));



//End: First Level Serial Menu 11111111111111111111111111111111111111111111111111111111111111111111111
//End:Serial  Menu *********************************************************************************


//Start:Circulation  Menu *********************************************************************************
//submenu of circulation
//Start: Third Level Circulation Menu 3333333333333333333333333333333333333333333333333333333333333333333333
var oCirInstituteMemberDetailsMenu = new YAHOO.widget.Menu("Member Details");

         /*        oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("Register", {  disabled: <%=cir_privilege[3]%>,  url: "<%=request.getContextPath()%>/circulation/cir_member_reg.jsp"}));
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("Update", {  disabled: <%=cir_privilege[4]%>,  url: "<%=request.getContextPath()%>/circulation/cir_member_reg.jsp"}));
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("Delete", {  disabled: <%=cir_privilege[5]%>,  url: "<%=request.getContextPath()%>/circulation/cir_member_reg.jsp"}));
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("View", {  disabled: <%=cir_privilege[6]%>,  url: "<%=request.getContextPath()%>/circulation/cir_member_reg.jsp"}));
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("View All", {  disabled: <%=cir_privilege[7]%>,  url: "<%=request.getContextPath()%>/circulation/cir_viewallload.do"}));
            */
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation1") %>", {  disabled: <%=cir_privilege[3]%>,  url: ""}));
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation2") %>", {  disabled: <%=cir_privilege[4]%>,  url: ""}));
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation3") %>", {  disabled: <%=cir_privilege[5]%>,  url: ""}));
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation4") %>", {  disabled: <%=cir_privilege[6]%>,  url: ""}));
                 oCirInstituteMemberDetailsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation5") %>", {  disabled: <%=cir_privilege[7]%>,  url: ""}));

var oCirInstituteMemberAccountMenu = new YAHOO.widget.Menu("Member Account");

            /*   oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("Create", {  disabled: <%=cir_privilege[9]%>,  url: "<%=request.getContextPath()%>/circulation/manage_member_account.jsp"}));
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("Update", {  disabled: <%=cir_privilege[10]%>,  url: "<%=request.getContextPath()%>/circulation/manage_member_account.jsp"}));
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("Delete", {  disabled: <%=cir_privilege[11]%>,  url: "<%=request.getContextPath()%>/circulation/manage_member_account.jsp"}));
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("View", {  disabled: <%=cir_privilege[12]%>,  url: "<%=request.getContextPath()%>/circulation/manage_member_account.jsp"}));
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("View All", {  disabled: <%=cir_privilege[13]%>,  url: "<%=request.getContextPath()%>/circulation/cir_viewallaccount.do"}));
            */
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation6") %>", {  disabled: <%=cir_privilege[9]%>,  url: ""}));
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation7") %>", {  disabled: <%=cir_privilege[10]%>,  url: ""}));
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation8") %>", {  disabled: <%=cir_privilege[11]%>,  url: ""}));
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation9") %>", {  disabled: <%=cir_privilege[12]%>,  url: ""}));
                 oCirInstituteMemberAccountMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation10") %>", {  disabled: <%=cir_privilege[13]%>,  url: ""}));


 var oCirInstituteMemberMenu = new YAHOO.widget.Menu("Institute Member");

              /*  oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("Member Set", {  disabled: <%=cir_privilege[22]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("Daily", {  disabled: <%=cir_privilege[23]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("By Group", {  disabled: <%=cir_privilege[24]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("General", {  disabled: <%=cir_privilege[25]%>,  url: ""}));

             */
                oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation11") %>", {  disabled: <%=cir_privilege[22]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation12") %>", {  disabled: <%=cir_privilege[23]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation13") %>", {  disabled: <%=cir_privilege[24]%>,  url: ""}));
                 oCirInstituteMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation14") %>", {  disabled: <%=cir_privilege[25]%>,  url: ""}));

                 var oCirReminderMenu = new YAHOO.widget.Menu("Reminder");

              /*   oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("Overdue Reminder", {  disabled: <%=cir_privilege[65]%>,  url: ""}));
                 oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("Collect Notice", {  disabled: <%=cir_privilege[66]%>,  url: ""}));
                 oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("Bindery Order", {  disabled: <%=cir_privilege[67]%>,  url: ""}));
                    */
                oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation15") %>", {  disabled: <%=cir_privilege[65]%>,  url: ""}));
                 oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation16") %>", {  disabled: <%=cir_privilege[66]%>,  url: ""}));
                 oCirReminderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation17") %>", {  disabled: <%=cir_privilege[67]%>,  url: ""}));


                 var oCirMemberRelatedParameterMenu = new YAHOO.widget.Menu("Member Related Parameter");

              /*   oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("Member Id Sequence", {  disabled: <%=cir_privilege[72]%>,  url: ""}));
                 oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("Member Type", {  disabled: <%=cir_privilege[73]%>,  url: ""}));
                 oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("SubMember Type", {  disabled: <%=cir_privilege[74]%>,  url: ""}));

                    */
                 oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation18") %>", {  disabled: <%=cir_privilege[72]%>,  url: ""}));
                 oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("opac.newmemberentry.typemem") %>", {  disabled: <%=cir_privilege[73]%>,  url: ""}));
                 oCirMemberRelatedParameterMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("opac.newmemberentry.memsubcat") %>", {  disabled: <%=cir_privilege[74]%>,  url: ""}));

                 var oCirRemoveExpiredRecordsMenu = new YAHOO.widget.Menu("Remove Expired record");

               /*  oCirRemoveExpiredRecordsMenu.addItem(new YAHOO.widget.MenuItem("Related To Members", {  disabled: <%=cir_privilege[83]%>,  url: ""}));
                 oCirRemoveExpiredRecordsMenu.addItem(new YAHOO.widget.MenuItem("Related To Reservation", {  disabled: <%=cir_privilege[84]%>,  url: ""}));
                */
                   oCirRemoveExpiredRecordsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation21") %>", {  disabled: <%=cir_privilege[83]%>,  url: ""}));
                 oCirRemoveExpiredRecordsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation22") %>", {  disabled: <%=cir_privilege[84]%>,  url: ""}));

                 var oLibraryLoanRecordKeepingMenu = new YAHOO.widget.Menu("Record Keeping");

                /*  oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Document Details", {  disabled: <%=cir_privilege[87]%>,  url: ""}));
                  oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Modify Recieved Documents", {  disabled: <%=cir_privilege[88]%>,  url: ""}));
                  oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("Return Documents", {  disabled: <%=cir_privilege[89]%>,  url: ""}));
                */
                oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation23") %>", {  disabled: <%=cir_privilege[87]%>,  url: ""}));
                  oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation24") %>", {  disabled: <%=cir_privilege[88]%>,  url: ""}));
                  oLibraryLoanRecordKeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation25") %>", {  disabled: <%=cir_privilege[89]%>,  url: ""}));


                 var oLibraryLoanCirculationMenu = new YAHOO.widget.Menu("Circulation");

                /* oLibraryLoanCirculationMenu.addItem(new YAHOO.widget.MenuItem("Check-Out", {  disabled: <%=cir_privilege[91]%>,  url: ""}));
                 oLibraryLoanCirculationMenu.addItem(new YAHOO.widget.MenuItem("Check-In", {  disabled: <%=cir_privilege[92]%>,  url: ""}));
                        */
                 oLibraryLoanCirculationMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation26") %>", {  disabled: <%=cir_privilege[91]%>,  url: ""}));
                 oLibraryLoanCirculationMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation27") %>", {  disabled: <%=cir_privilege[92]%>,  url: ""}));


                  var oLibraryLoanEnquiriesMenu  = new YAHOO.widget.Menu("Enquiries.");

                /*  oLibraryLoanEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("ILL Documents", {  disabled: <%=cir_privilege[94]%>,  url: ""}));
                  oLibraryLoanEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Documents Checked-Out", {  disabled: <%=cir_privilege[95]%>,  url: ""}));
                  */
                  oLibraryLoanEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation28") %>", {  disabled: <%=cir_privilege[94]%>,  url: ""}));
                  oLibraryLoanEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation29") %>", {  disabled: <%=cir_privilege[95]%>,  url: ""}));


                var oLibraryLoanReportMenu = new YAHOO.widget.Menu("Reports");

        	/*oLibraryLoanReportMenu.addItem(new YAHOO.widget.MenuItem("ILL Documents", {  disabled: <%=cir_privilege[97]%>,  url: ""}));
                oLibraryLoanReportMenu.addItem(new YAHOO.widget.MenuItem("Documents Checked-Out", {  disabled: <%=cir_privilege[98]%>,  url: ""}));
                        */
                oLibraryLoanReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation30") %>", {  disabled: <%=cir_privilege[97]%>,  url: ""}));
                oLibraryLoanReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation31") %>", {  disabled: <%=cir_privilege[98]%>,  url: ""}));





//End: Third Level Circulation Menu 3333333333333333333333333333333333333333333333333333333333333333333333
//Start: Second Level Circulation Menu 2222222222222222222222222222222222222222222222222222222222222222222222

                  var oCirMemberManagementMenu = new YAHOO.widget.Menu("Member Management");

              /**    oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Member Details", { submenu:oCirInstituteMemberDetailsMenu ,  disabled: <%=cir_privilege[2]%>,  url:"" }));
                oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Member Account", { submenu:oCirInstituteMemberAccountMenu ,  disabled: <%=cir_privilege[8]%>,  url:"" }));





                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Registration Request From Opac", {   disabled: <%=cir_privilege[14]%>,  url:"<%=request.getContextPath()%>/circulation/requestfromopac.do" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Temporary Registration", {   disabled: <%=cir_privilege[15]%>,  url:"<%=request.getContextPath()%>/circulation/cir_member_reg.jsp" }));

                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Member Statistics", {   disabled: <%=cir_privilege[16]%>,  url:"<%=request.getContextPath()%>/circulation/statistics.do" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Cancellation", {   disabled: <%=cir_privilege[17]%>,  url:"<%=request.getContextPath()%>/circulation/cir_viewallaccount1.do" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Renewal", {   disabled: <%=cir_privilege[18]%>,  url:"<%=request.getContextPath()%>/circulation/cir_viewallaccount1.do" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Delinquent Member", {   disabled: <%=cir_privilege[19]%>,  url:"<%=request.getContextPath()%>/circulation/cir_viewallaccount1.do" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("UnBlock Member", {   disabled: <%=cir_privilege[20]%>,  url:"<%=request.getContextPath()%>/circulation/cir_viewallaccount1.do" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Institute Member", {submenu:oCirInstituteMemberMenu ,   disabled: <%=cir_privilege[21]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("Scheme Allocation", {   disabled: <%=cir_privilege[26]%>,  url:"" }));
*/

                oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation32") %>", { submenu:oCirInstituteMemberDetailsMenu ,  disabled: <%=cir_privilege[2]%>,  url:"" }));
                oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation33") %>", { submenu:oCirInstituteMemberAccountMenu ,  disabled: <%=cir_privilege[8]%>,  url:"" }));
                 oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation34") %>", {   disabled: <%=cir_privilege[14]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation35") %>", {   disabled: <%=cir_privilege[15]%>,  url:"" }));

                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation36") %>", {   disabled: <%=cir_privilege[16]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation37") %>", {   disabled: <%=cir_privilege[17]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation38") %>", {   disabled: <%=cir_privilege[18]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation39") %>", {   disabled: <%=cir_privilege[19]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation40") %>", {   disabled: <%=cir_privilege[20]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation41") %>", {submenu:oCirInstituteMemberMenu ,   disabled: <%=cir_privilege[21]%>,  url:"" }));
                  oCirMemberManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation42") %>", {   disabled: <%=cir_privilege[26]%>,  url:"" }));


                  var oCirMembershipCardManagementMenu = new YAHOO.widget.Menu("Membership Card Management");

              /*    oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("Generate Card", {  disabled: <%=cir_privilege[29]%>,  url: "<%=request.getContextPath()%>/circulation/cir_generate_card.jsp"}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("Lost Card/Found Card", {  disabled: <%=cir_privilege[30]%>,  url: "<%=request.getContextPath()%>/circulation/cir_generate_card.jsp"}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("Duplicate Card", {  disabled: <%=cir_privilege[31]%>,  url: "<%=request.getContextPath()%>/circulation/cir_generate_card.jsp"}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("Remove Card", {  disabled: <%=cir_privilege[32]%>,  url: "<%=request.getContextPath()%>/circulation/cir_viewallaccount1.do"}));
*/
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation43") %>", {  disabled: <%=cir_privilege[29]%>,  url: ""}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation44") %>", {  disabled: <%=cir_privilege[30]%>,  url: ""}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation45") %>", {  disabled: <%=cir_privilege[31]%>,  url: ""}));
                  oCirMembershipCardManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation46") %>", {  disabled: <%=cir_privilege[32]%>,  url: ""}));


                 var oCirFineManagementMenu = new YAHOO.widget.Menu("Fine Management");

               /*  oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("Fine Collection", {  disabled: <%=cir_privilege[36]%>,  url: ""}));
                 oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("Update Fine Detail", {  disabled: <%=cir_privilege[37]%>,  url: ""}));
                 oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("Fine Notices", {  disabled: <%=cir_privilege[38]%>,  url: ""}));

                    */
                 oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation47") %>", {  disabled: <%=cir_privilege[36]%>,  url: ""}));
                 oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation48") %>", {  disabled: <%=cir_privilege[37]%>,  url: ""}));
                 oCirFineManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation49") %>", {  disabled: <%=cir_privilege[38]%>,  url: ""}));


                 var oCirCollectionManagementMenu = new YAHOO.widget.Menu("Collection Management");

               /*  oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Titles On Showcase/Display", {  disabled: <%=cir_privilege[40]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Missing Document", {  disabled: <%=cir_privilege[41]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Damaged Document", {  disabled: <%=cir_privilege[42]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Withdrawn Document", {  disabled: <%=cir_privilege[43]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("Written Off Document", {  disabled: <%=cir_privilege[44]%>,  url: ""}));
                                */
                    oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation50") %>", {  disabled: <%=cir_privilege[40]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation51") %>", {  disabled: <%=cir_privilege[41]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation52") %>", {  disabled: <%=cir_privilege[42]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation53") %>", {  disabled: <%=cir_privilege[43]%>,  url: ""}));
                 oCirCollectionManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation54") %>", {  disabled: <%=cir_privilege[44]%>,  url: ""}));



                 var oCirBindingManagementMenu = new YAHOO.widget.Menu("Binding Management");

                /* oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Prepare List For Binding", {  disabled: <%=cir_privilege[46]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Sent to Binder", {  disabled: <%=cir_privilege[47]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Update Binding Status", {  disabled: <%=cir_privilege[48]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Receive Binded Titles", {  disabled: <%=cir_privilege[49]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("Written Off Document", {  disabled: <%=cir_privilege[50]%>,  url: ""}));
*/
                   oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation55") %>", {  disabled: <%=cir_privilege[46]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation56") %>", {  disabled: <%=cir_privilege[47]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation57") %>", {  disabled: <%=cir_privilege[48]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation58") %>", {  disabled: <%=cir_privilege[49]%>,  url: ""}));
                 oCirBindingManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation59") %>", {  disabled: <%=cir_privilege[50]%>,  url: ""}));



                 var oCirDocumentReservationMenu = new YAHOO.widget.Menu("Document Reservation");

               /** oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("Reserve Document", {  disabled: <%=cir_privilege[52]%>,  url: ""}));
                 oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("Cancel Reservation", {  disabled: <%=cir_privilege[53]%>,  url: ""}));
                 oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("Change Reserve Sequence", {  disabled: <%=cir_privilege[54]%>,  url: ""}));
                    **/
                     oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation60") %>", {  disabled: <%=cir_privilege[52]%>,  url: ""}));
                 oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation61") %>", {  disabled: <%=cir_privilege[53]%>,  url: ""}));
                 oCirDocumentReservationMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation62") %>", {  disabled: <%=cir_privilege[54]%>,  url: ""}));


                 var oCirCirculationEnquiriesMenu = new YAHOO.widget.Menu("Circulation Enquiry");

              /*   oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Enquiry on Members", {  disabled: <%=cir_privilege[56]%>,  url: "<%=request.getContextPath()%>/circulation/cir_enquirymember.jsp"}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Title Based Enquiry", {  disabled: <%=cir_privilege[57]%>,  url: "<%=request.getContextPath()%>/circulation/cir_enquirymember.jsp"}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Transaction Log", {  disabled: <%=cir_privilege[58]%>,  url: "<%=request.getContextPath()%>/circulation/cir_transactionlog.jsp"}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Serial Based", {  disabled: <%=cir_privilege[59]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("Statistics", {  disabled: <%=cir_privilege[60]%>,  url: "<%=request.getContextPath()%>/circulation/statistics.do"}));
*/
                  oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation63") %>", {  disabled: <%=cir_privilege[56]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation64") %>", {  disabled: <%=cir_privilege[57]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation65") %>", {  disabled: <%=cir_privilege[58]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation66") %>", {  disabled: <%=cir_privilege[59]%>,  url: ""}));
                 oCirCirculationEnquiriesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation67") %>", {  disabled: <%=cir_privilege[60]%>,  url: ""}));


                var oCirCirculationReport = new YAHOO.widget.Menu("Circulation Report");

             /*   oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("CheckOut Report", {  disabled: <%=cir_privilege[62]%>,  url: "<%=request.getContextPath()%>/circulation/cir_checkout_report.jsp"}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("CheckIn Report", {  disabled: <%=cir_privilege[63]%>,  url: "<%=request.getContextPath()%>/circulation/cir_checkin_report.jsp"}));
                 oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("Reminder", {   submenu:oCirReminderMenu ,  disabled: <%=cir_privilege[64]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("Management Reporting", {  disabled: <%=cir_privilege[68]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("Written off Document", {  disabled: <%=cir_privilege[69]%>,  url: ""}));

*/
                   oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation68") %>", {  disabled: <%=cir_privilege[62]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation69") %>", {  disabled: <%=cir_privilege[63]%>,  url: ""}));
                 oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation70") %>", {   submenu:oCirReminderMenu ,  disabled: <%=cir_privilege[64]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation71") %>", {  disabled: <%=cir_privilege[68]%>,  url: ""}));
                oCirCirculationReport.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation72") %>", {  disabled: <%=cir_privilege[69]%>,  url: ""}));

                var oCirSystemSetupMenu = new YAHOO.widget.Menu("System Setup");

              /*  oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Member Related Parameter", {submenu:oCirMemberRelatedParameterMenu ,  disabled: <%=cir_privilege[71]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Delinquency Reasons", {  disabled: <%=cir_privilege[75]%>,  url: "<%=request.getContextPath()%>/circulation/add_activity.jsp"}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Cancellation Reasons", {  disabled: <%=cir_privilege[76]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Configure Fine Parameters", {  disabled: <%=cir_privilege[77]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Text of Notices For Member", {  disabled: <%=cir_privilege[78]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("Parameter For Slips", {  disabled: <%=cir_privilege[79]%>,  url: ""}));
*/
            oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation73") %>", {submenu:oCirMemberRelatedParameterMenu ,  disabled: <%=cir_privilege[71]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation74") %>", {  disabled: <%=cir_privilege[75]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation75") %>", {  disabled: <%=cir_privilege[76]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup6") %>", {  disabled: <%=cir_privilege[77]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation77") %>", {  disabled: <%=cir_privilege[78]%>,  url: ""}));
                oCirSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation78") %>", {  disabled: <%=cir_privilege[79]%>,  url: ""}));


                var oCirHouseKeepingMenu = new YAHOO.widget.Menu("House Keeping");

             /*   oCirHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Remove Transaction Log", {  disabled: <%=cir_privilege[81]%>,  url: ""}));
                oCirHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Remove Expired Records", {submenu:oCirRemoveExpiredRecordsMenu ,  disabled: <%=cir_privilege[82]%>,  url: ""}));
*/
                        oCirHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation79") %>", {  disabled: <%=cir_privilege[81]%>,  url: ""}));
                oCirHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation80") %>", {submenu:oCirRemoveExpiredRecordsMenu ,  disabled: <%=cir_privilege[82]%>,  url: ""}));



                 var oInterLibraryLoanMenu = new YAHOO.widget.Menu("Inter Library Loan");

            /*   oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Record keeping", {submenu:oLibraryLoanRecordKeepingMenu ,disabled: <%=cir_privilege[86]%>,  url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Circulation", {submenu :oLibraryLoanCirculationMenu ,  disabled: <%=cir_privilege[90]%>,   url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Enquiries.", {submenu:oLibraryLoanEnquiriesMenu ,  disabled: <%=cir_privilege[93]%>,   url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("Reports", {submenu:oLibraryLoanReportMenu ,  disabled: <%=cir_privilege[96]%>,   url: ""}));
*/

                oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation81") %>", {submenu:oLibraryLoanRecordKeepingMenu ,disabled: <%=cir_privilege[86]%>,  url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation82") %>", {submenu :oLibraryLoanCirculationMenu ,  disabled: <%=cir_privilege[90]%>,   url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation83") %>", {submenu:oLibraryLoanEnquiriesMenu ,  disabled: <%=cir_privilege[93]%>,   url: ""}));
               oInterLibraryLoanMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation84") %>", {submenu:oLibraryLoanReportMenu ,  disabled: <%=cir_privilege[96]%>,   url: ""}));

//End: Secondt Level Circulation Menu 2222222222222222222222222222222222222222222222222222222222222222222222

//Start: First Level Circulation Menu 11111111111111111111111111111111111111111111111111111111111111111111111

               var oCirMenu = new YAHOO.widget.Menu("Circulation", { zIndex:2 });

             /*   oCirMenu.addItem(new YAHOO.widget.MenuItem("Member Management", {Submenu:oCirMemberManagementMenu ,   disabled: <%=cir_privilege[1]%>,  url:"" } ));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Member Directory", {   disabled: <%=cir_privilege[27]%>,  url:"<%=request.getContextPath()%>/circulation/cir_viewallload.do"  } ));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Membership Card Management", {submenu:oCirMembershipCardManagementMenu ,   disabled: <%=cir_privilege[28]%>,  url:""  }));

                 oCirMenu.addItem(new YAHOO.widget.MenuItem("Check-Out (Issue)", {  disabled: <%=cir_privilege[33]%>,  url: "<%=request.getContextPath()%>/circulation/cir_checkout.jsp"}));
                  oCirMenu.addItem(new YAHOO.widget.MenuItem("Check-In (Return)", {  disabled: <%=cir_privilege[34]%>,  url: "<%=request.getContextPath()%>/circulation/cir_checkin.jsp"}));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Fine Management", {submenu:oCirFineManagementMenu ,   disabled: <%=cir_privilege[35]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Collection Management", {submenu: oCirCollectionManagementMenu ,   disabled: <%=cir_privilege[39]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Binding Management", {submenu:oCirBindingManagementMenu ,   disabled: <%=cir_privilege[45]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Document Reservation", {submenu:oCirDocumentReservationMenu ,   disabled: <%=cir_privilege[51]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Circulation Enquiries", {submenu:oCirCirculationEnquiriesMenu ,   disabled: <%=cir_privilege[55]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Circulation Report", {submenu:oCirCirculationReport ,   disabled: <%=cir_privilege[61]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {submenu:oCirSystemSetupMenu ,   disabled: <%=cir_privilege[70]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("House Keeping", {submenu:oCirHouseKeepingMenu ,    disabled: <%=cir_privilege[80]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Inter Library Loan", {submenu:oInterLibraryLoanMenu ,    disabled: <%=cir_privilege[85]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("Serial Circulation", {   disabled: <%=cir_privilege[99]%>,  url:""  }));

*/


  oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation85") %>", {Submenu:oCirMemberManagementMenu ,   disabled: <%=cir_privilege[1]%>,  url:"" } ));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation86") %>", {   disabled: <%=cir_privilege[27]%>,  url:""  } ));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation87") %>", {submenu:oCirMembershipCardManagementMenu ,   disabled: <%=cir_privilege[28]%>,  url:""  }));

                 oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation88") %>", {  disabled: <%=cir_privilege[33]%>,  url: ""}));
                  oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation89") %>", {  disabled: <%=cir_privilege[34]%>,  url: ""}));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation90") %>", {submenu:oCirFineManagementMenu ,   disabled: <%=cir_privilege[35]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation91") %>", {submenu: oCirCollectionManagementMenu ,   disabled: <%=cir_privilege[39]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation92") %>", {submenu:oCirBindingManagementMenu ,   disabled: <%=cir_privilege[45]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation93") %>", {submenu:oCirDocumentReservationMenu ,   disabled: <%=cir_privilege[51]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation94") %>", {submenu:oCirCirculationEnquiriesMenu ,   disabled: <%=cir_privilege[55]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation95") %>", {submenu:oCirCirculationReport ,   disabled: <%=cir_privilege[61]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation96") %>", {submenu:oCirSystemSetupMenu ,   disabled: <%=cir_privilege[70]%>,  url:""  }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation97") %>", {submenu:oCirHouseKeepingMenu ,    disabled: <%=cir_privilege[80]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation98") %>", {submenu:oInterLibraryLoanMenu ,    disabled: <%=cir_privilege[85]%>,  url:"" }));
                oCirMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.circulation99") %>", {   disabled: <%=cir_privilege[99]%>,  url:""  }));


//End: First Level Circulation Menu 11111111111111111111111111111111111111111111111111111111111111111111111
//End:Circulation  Menu *********************************************************************************



//Start:Cataloguing Menu *********************************************************************************
//submenu of cataloguing


//Start: Third Level Catalogue Menu 333333333333333333333333333333333333333333333333333333333333333333333333


                var oCatTitleHoldingmanagementMenu =  new YAHOO.widget.Menu("Title Holding Management");

             /*   oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("Merge Titles", {  disabled: <%=cat_privilege[7]%>,  url: ""}));
                oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("Update Holding", {  disabled: <%=cat_privilege[8]%>,  url: ""}));
                oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("Remove Title", {  disabled: <%=cat_privilege[9]%>,  url: ""}));
*/
 oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing1") %>", {  disabled: <%=cat_privilege[7]%>,  url: ""}));
                oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing2") %>", {  disabled: <%=cat_privilege[8]%>,  url: ""}));
                oCatTitleHoldingmanagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing3") %>", {  disabled: <%=cat_privilege[9]%>,  url: ""}));

                var oCatOldTitleMenu = new YAHOO.widget.Menu("Old Title Entry");
               /* oCatOldTitleMenu.addItem(new YAHOO.widget.MenuItem("Manage Biblography", {  disabled: <%=cat_privilege[4]%>,  url: ""}));
                oCatOldTitleMenu.addItem(new YAHOO.widget.MenuItem("View All", {  disabled: <%=cat_privilege[5]%>,  url: ""}));
*/
 oCatOldTitleMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing4") %>", {  disabled: <%=cat_privilege[4]%>,  url: ""}));
                oCatOldTitleMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing5") %>", {  disabled: <%=cat_privilege[5]%>,  url: ""}));

                var oCatAccessionManagementMenu =  new YAHOO.widget.Menu("Accession Management");

              /*  oCatAccessionManagementMenu.addItem(new YAHOO.widget.MenuItem("Change Accession No", {  disabled: <%=cat_privilege[12]%>,  url: "<%=request.getContextPath()%>/cataloguing/cat_accession.jsp"}));
                oCatAccessionManagementMenu.addItem(new YAHOO.widget.MenuItem("Assign Accession No", {  disabled: <%=cat_privilege[13]%>,  url: "<%=request.getContextPath()%>/cataloguing/cat_accession.jsp"}));
*/
 oCatAccessionManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing6") %>", {  disabled: <%=cat_privilege[12]%>,  url: ""}));
                oCatAccessionManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing7") %>", {  disabled: <%=cat_privilege[13]%>,  url: ""}));


                var oCatTitleManagementMenu =  new YAHOO.widget.Menu("Cataloguing of New Titles");

               /* oCatTitleManagementMenu.addItem(new YAHOO.widget.MenuItem("Entry using Simple Template", {  disabled: <%=cat_privilege[15]%>,  url: ""}));
                oCatTitleManagementMenu.addItem(new YAHOO.widget.MenuItem("Entry using MARC21 Template", {  disabled: <%=cat_privilege[16]%>,  url: ""}));
                 oCatTitleManagementMenu.addItem(new YAHOO.widget.MenuItem("Entry using Customized Template", {  disabled: <%=cat_privilege[17]%>,  url: ""}));
*/
                    oCatTitleManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing8") %>", {  disabled: <%=cat_privilege[15]%>,  url: ""}));
               oCatTitleManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing9") %>", {  disabled: <%=cat_privilege[16]%>,  url: ""}));
                oCatTitleManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing37") %>", {  disabled: <%=cat_privilege[17]%>,  url: ""}));


//End: Third Level Catalogue Menu 33333333333333333333333333333333333333333333333333333333333333333333333333
//Start: Second Level Catalogue Menu 2222222222222222222222222222222222222222222222222222222222222222222222
                var oCatInitiateCataloguingMenu = new YAHOO.widget.Menu("Initiate Cataloguing");

              /*  oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Titles ready for Cataloguing", {  disabled: <%=cat_privilege[2]%>,  url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Old Title Entry", { submenu:oCatOldTitleMenu, disabled: <%=cat_privilege[3]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Title Holding management", {submenu:oCatTitleHoldingmanagementMenu,  disabled: <%=cat_privilege[6]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Subject Updates", {  disabled: <%=cat_privilege[10]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Accession Management", {submenu:oCatAccessionManagementMenu,  disabled: <%=cat_privilege[11]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Cataloguing of New Titles", {submenu:oCatTitleManagementMenu,  disabled: <%=cat_privilege[14]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("Multimedia Management", {  disabled: <%=cat_privilege[18]%>, url: ""}));
*/
                  oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing10") %>", {  disabled: <%=cat_privilege[2]%>,  url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing11") %>", { submenu:oCatOldTitleMenu, disabled: <%=cat_privilege[3]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing12") %>", {submenu:oCatTitleHoldingmanagementMenu,  disabled: <%=cat_privilege[6]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing13") %>", {  disabled: <%=cat_privilege[10]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing14") %>", {submenu:oCatAccessionManagementMenu,  disabled: <%=cat_privilege[11]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing15") %>", {submenu:oCatTitleManagementMenu,  disabled: <%=cat_privilege[14]%>, url: ""}));
                oCatInitiateCataloguingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing16") %>", {  disabled: <%=cat_privilege[18]%>, url: ""}));


                var oCatLatestCognizanceMenu= new YAHOO.widget.Menu("Latest Cognizance");

              /*  oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("Generate Latest Addition List", {  disabled: <%=cat_privilege[20]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("Update Latest Addition List", {  disabled: <%=cat_privilege[21]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("View Latest Addition List", {  disabled: <%=cat_privilege[22]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("Develop Bibliography", {  disabled: <%=cat_privilege[23]%>, url: ""}));
*/
 oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing17") %>", {  disabled: <%=cat_privilege[20]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing18") %>", {  disabled: <%=cat_privilege[21]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing19") %>", {  disabled: <%=cat_privilege[22]%>, url: ""}));
                oCatLatestCognizanceMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing20") %>", {  disabled: <%=cat_privilege[23]%>, url: ""}));



               var oCatReportsMenu = new YAHOO.widget.Menu("Catalogue Reports");

             /*  oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Document Slip", {  disabled: <%=cat_privilege[30]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("List Titles", {  disabled: <%=cat_privilege[31]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Subjects/Keywords", {  disabled: <%=cat_privilege[32]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Arrival Notice", {  disabled: <%=cat_privilege[33]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Classified Subjects", {  disabled: <%=cat_privilege[34]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Highly Priced titles", {  disabled: <%=cat_privilege[35]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("Withdrawn Register", {  disabled: <%=cat_privilege[36]%>, url: ""}));
*/
 oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing21") %>", {  disabled: <%=cat_privilege[30]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing22") %>", {  disabled: <%=cat_privilege[31]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing23") %>", {  disabled: <%=cat_privilege[32]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing24") %>", {  disabled: <%=cat_privilege[33]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing25") %>", {  disabled: <%=cat_privilege[34]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing26") %>", {  disabled: <%=cat_privilege[35]%>, url: ""}));
               oCatReportsMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing27") %>", {  disabled: <%=cat_privilege[36]%>, url: ""}));


 var oDataImportExport = new YAHOO.widget.Menu("Data Import/Export");

         /*      oDataImportExport.addItem(new YAHOO.widget.MenuItem("Data Import", {  disabled: <%=cat_privilege[27]%>, url: "<%=request.getContextPath()%>/cataloguing/cat_data_import_read.jsp" }));
               oDataImportExport.addItem(new YAHOO.widget.MenuItem("Data Export", {  disabled: <%=cat_privilege[28]%>, url: "<%=request.getContextPath()%>/cataloguing/cat_exportDatabaseToExcell.jsp" }));
*/

               oDataImportExport.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing38") %>", {  disabled: <%=cat_privilege[27]%>, url: "" }));
               oDataImportExport.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing39") %>", {  disabled: <%=cat_privilege[28]%>, url: "" }));



//End: Second Level Catalogue Menu 2222222222222222222222222222222222222222222222222222222222222222222222
//Start: First Level Catalogue Menu 11111111111111111111111111111111111111111111111111111111111111111111111
               var oCatMenu = new YAHOO.widget.Menu("Cataloguing", { zIndex:2 });

              /*  oCatMenu.addItem(new YAHOO.widget.MenuItem("Initiate Cataloguing", {submenu:oCatInitiateCataloguingMenu ,   disabled: <%=cat_privilege[1]%>, url:""} ));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Latest Cognizance", {submenu:oCatLatestCognizanceMenu ,  disabled: <%=cat_privilege[18]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Print Catalogue Cards", {  disabled: <%=cat_privilege[24]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Catalogue System Setup", {  disabled: <%=cat_privilege[25]%>,url:"" }));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Data Import/Export", {   disabled: <%=cat_privilege[26]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Catalogue Reports", {submenu:oCatReportsMenu ,  disabled: <%=cat_privilege[27]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Annual Stock", {  disabled: <%=cat_privilege[35]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Database Backup", {  disabled: <%=cat_privilege[36]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("Online Search", {  disabled: <%=cat_privilege[37]%>, url:""}));
*/

 oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing28") %>", {submenu:oCatInitiateCataloguingMenu ,   disabled: <%=cat_privilege[1]%>, url:""} ));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing29") %>", {submenu:oCatLatestCognizanceMenu ,  disabled: <%=cat_privilege[19]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing30") %>", {  disabled: <%=cat_privilege[24]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing31") %>", {  disabled: <%=cat_privilege[25]%>,url:"" }));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing32") %>", {  submenu:oDataImportExport , disabled: <%=cat_privilege[26]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing33") %>", {submenu:oCatReportsMenu ,  disabled: <%=cat_privilege[29]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing34") %>", {  disabled: <%=cat_privilege[37]%>,url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing35") %>", {  disabled: <%=cat_privilege[38]%>, url:""}));
                oCatMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.cataloguing36") %>", {  disabled: <%=cat_privilege[39]%>, url:""}));



//End: First Level Catalogue Menu 11111111111111111111111111111111111111111111111111111111111111111111111
//End:Cataloguing Menu *********************************************************************************
//************************************************Start acquisition Menu*********************************************



//Start:Third Level Acquisition Menu 333333333333333333333333333333333333333333333333333333333333333333333


                var oAcqRequestToVendorMenu = new YAHOO.widget.Menu("Request To Vendor");

            /*    oAcqRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("Direct Request", {  disabled: <%=acq_privilege[10]%>, url: ""}));
                oAcqRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("Approved Request", {  disabled: <%=acq_privilege[11]%>,url: ""}));
*/
                 oAcqRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition1") %>", {  disabled: <%=acq_privilege[10]%>, url: ""}));
                oAcqRequestToVendorMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition2") %>", {  disabled: <%=acq_privilege[11]%>,url: ""}));

                var oAcqStatusUpdateMenu =  new YAHOO.widget.Menu("Status Update");

              /*  oAcqStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("Vendor Request", {  disabled: <%=acq_privilege[13]%>, url: ""}));
                oAcqStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("Approval Status", {  disabled: <%=acq_privilege[14]%>,url: ""}));
*/
                     oAcqStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition3") %>", {  disabled: <%=acq_privilege[13]%>, url: ""}));
                oAcqStatusUpdateMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition4") %>", {  disabled: <%=acq_privilege[14]%>,url: ""}));


                var oAcqListTitlesMenu =  new YAHOO.widget.Menu("List Titles");

            /*    oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("On-Approval Titles", {  disabled: <%=acq_privilege[16]%>, url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("On Request To Vendor Titles", {  disabled: <%=acq_privilege[17]%>, url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("Pending Titles", {  disabled: <%=acq_privilege[18]%>,url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("Approved Titles", {  disabled: <%=acq_privilege[19]%>,url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("Rejected Titles", {  disabled: <%=acq_privilege[20]%>,url: ""}));
*/
                        oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition5") %>", {  disabled: <%=acq_privilege[16]%>, url:""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition6") %>", {  disabled: <%=acq_privilege[17]%>, url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition7") %>", {  disabled: <%=acq_privilege[18]%>,url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition8") %>", {  disabled: <%=acq_privilege[19]%>,url: ""}));
                oAcqListTitlesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition9") %>", {  disabled: <%=acq_privilege[20]%>,url: ""}));


                var oAcqViewListForOrderMenu = new YAHOO.widget.Menu("View List for Order");

              /*  oAcqViewListForOrderMenu.addItem(new YAHOO.widget.MenuItem("Direct Request", {  disabled: <%=acq_privilege[23]%>,url: ""}));
                oAcqViewListForOrderMenu.addItem(new YAHOO.widget.MenuItem("Approved Request ", {  disabled: <%=acq_privilege[24]%>,url: ""}));
*/

                    oAcqViewListForOrderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition10") %>", {  disabled: <%=acq_privilege[23]%>,url: ""}));
                oAcqViewListForOrderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition11") %> ", {  disabled: <%=acq_privilege[24]%>,url: ""}));

                var oAcqListInvoicesMenu = new YAHOO.widget.Menu("List Invoices");

             /*  oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("All Invoices", {  disabled: <%=acq_privilege[35]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("With Library", {  disabled: <%=acq_privilege[36]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("Unpaid Invoice", {  disabled: <%=acq_privilege[37]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("Invoice Register", {  disabled: <%=acq_privilege[38]%>,url: ""}))
*/
                    oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition12") %>", {  disabled: <%=acq_privilege[35]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition13") %>", {  disabled: <%=acq_privilege[36]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition14") %>", {  disabled: <%=acq_privilege[37]%>,url: ""}))
               oAcqListInvoicesMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition15") %>", {  disabled: <%=acq_privilege[38]%>,url: ""}))

//End:Third Level Acquisition Menu 333333333333333333333333333333333333333333333333333333333333333333333


//Start:Second Level Acquisition Menu 22222222222222222222222222222222222222222222222222222222222222222222

                var oAcqGenerateTitleMenu = new YAHOO.widget.Menu("Generate Title");

           /*    oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("Refer from Demend list/ Requests", { disabled: <%=acq_privilege[2]%>,url: ""}));
                oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("Refer From Catalogue", { disabled: <%=acq_privilege[3]%>,url: ""}));
                oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("New Entry of Title", {  disabled: <%=acq_privilege[4]%>,url: "<%=request.getContextPath()%>/acquisition/acq_new_entry.jsp"}));
               oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("Initiate Acquisition", { disabled: <%=acq_privilege[5]%>,url: "<%=request.getContextPath()%>/acquisition/acq_search_title.jsp"}));
              */
              oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition16") %>", { disabled: <%=acq_privilege[2]%>,url: ""}));
                oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition17") %>", { disabled: <%=acq_privilege[3]%>,url: ""}));
                oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition18") %>", {  disabled: <%=acq_privilege[4]%>,url: ""}));
               oAcqGenerateTitleMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition19") %>", { disabled: <%=acq_privilege[5]%>,url: ""}));

              var oAcqApprovalprocessMenu = new YAHOO.widget.Menu("Approval Process");

              /*   oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Add Title into Approval List", { disabled: <%=acq_privilege[7]%>, url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Approve/Rejects Title", {disabled: <%=acq_privilege[8]%> ,url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Request to Vendor", {submenu:oAcqRequestToVendorMenu  ,disabled: <%=acq_privilege[9]%> , url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("Status Update", {submenu:oAcqStatusUpdateMenu  ,disabled: <%=acq_privilege[12]%> ,url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("List Titles ", {submenu:oAcqListTitlesMenu ,disabled: <%=acq_privilege[15]%> ,url: ""}));
*/
                oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition20") %>", { disabled: <%=acq_privilege[7]%>, url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition21") %>", {disabled: <%=acq_privilege[8]%> ,url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition22") %>", {submenu:oAcqRequestToVendorMenu  ,disabled: <%=acq_privilege[9]%> , url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition23") %>", {submenu:oAcqStatusUpdateMenu  ,disabled: <%=acq_privilege[12]%> ,url: ""}));
                 oAcqApprovalprocessMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition24") %> ", {submenu:oAcqListTitlesMenu ,disabled: <%=acq_privilege[15]%> ,url: ""}));


              var oAcqPlaceorderMenu = new YAHOO.widget.Menu("Place Order");

              /*   oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("View List For Order", {submenu:oAcqViewListForOrderMenu , disabled: <%=acq_privilege[22]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Generate Order", {disabled: <%=acq_privilege[25]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("List of Ordered Titles", {disabled: <%=acq_privilege[26]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notices", {disabled: <%=acq_privilege[27]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("Cancel Order", {disabled: <%=acq_privilege[28]%> ,url: ""}));
*/
                oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition25") %>", {submenu:oAcqViewListForOrderMenu , disabled: <%=acq_privilege[22]%> ,url: ""}));





                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition26") %>", {disabled: <%=acq_privilege[25]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition27") %>", {disabled: <%=acq_privilege[26]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition28") %>", {disabled: <%=acq_privilege[27]%> ,url: ""}));
                 oAcqPlaceorderMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition29") %>", {disabled: <%=acq_privilege[28]%> ,url: ""}));


                var oInvoiceManagementMenu = new YAHOO.widget.Menu("Invoice Management");

              /*  oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("Process Invoice", {disabled: <%=acq_privilege[31]%> ,url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", {disabled: <%=acq_privilege[32]%>,url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("Payment Updates", {disabled: <%=acq_privilege[33]%> , url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("List Invoices", {submenu:oAcqListInvoicesMenu ,disabled: <%=acq_privilege[34]%> , url: ""}));
*/

 oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition30") %>", {disabled: <%=acq_privilege[31]%> ,url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition31") %>", {disabled: <%=acq_privilege[32]%>,url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition32") %>", {disabled: <%=acq_privilege[33]%> , url: ""}));
                oInvoiceManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition33") %>", {submenu:oAcqListInvoicesMenu ,disabled: <%=acq_privilege[34]%> , url: ""}));

               var oAccessioningMenu =new YAHOO.widget.Menu("Accessioning");

           /*    oAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Accession Process", {disabled: <%=acq_privilege[40]%> ,url: ""}));
               oAccessioningMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", {disabled: <%=acq_privilege[41]%> ,url: ""}));
*/

                  oAccessioningMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition34") %>", {disabled: <%=acq_privilege[40]%> ,url: ""}));
               oAccessioningMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition35") %>", {disabled: <%=acq_privilege[41]%> ,url: ""}));

                var oAcqRecordkeepingMenu = new YAHOO.widget.Menu("Record Keeping");

              /*  oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Vendors", {disabled: <%=acq_privilege[45]%> ,url:"<%=request.getContextPath()%>/acquisition/acq_initiate_vendor.jsp"}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Member's Set", {disabled: <%=acq_privilege[46]%> ,url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("Update Subjects/Class No", {disabled: <%=acq_privilege[47]%> ,url: ""}));
*/
                    oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition36") %>", {disabled: <%=acq_privilege[45]%> ,url:""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition37") %>", {disabled: <%=acq_privilege[46]%> ,url: ""}));
                oAcqRecordkeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition38") %>", {disabled: <%=acq_privilege[47]%> ,url: ""}));



              var oAcqReportMenu = new YAHOO.widget.Menu("Reports");{url: ""}

            /*  oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles in Acquisition", { disabled: <%=acq_privilege[49]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Requested Titles", { disabled: <%=acq_privilege[50]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles for Approval", { disabled: <%=acq_privilege[51]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Collect Notice", { disabled: <%=acq_privilege[52]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles For Ordering", { disabled: <%=acq_privilege[53]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order Form", { disabled: <%=acq_privilege[54]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Vendors Directory", { disabled: <%=acq_privilege[55]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Sanction Request", { disabled: <%=acq_privilege[56]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Request", { disabled: <%=acq_privilege[57]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Payment Status Request", { disabled: <%=acq_privilege[58]%>,url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Check Delivery Notice", { disabled: <%=acq_privilege[59]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notice(Selective)", { disabled: <%=acq_privilege[60]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Overdue Notice(All Titles)", { disabled: <%=acq_privilege[61]%> ,url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Accession Register", { disabled: <%=acq_privilege[62]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Invoice Register", { disabled: <%=acq_privilege[63]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Request to vendor", { disabled: <%=acq_privilege[64]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Titles by Control No", { disabled: <%=acq_privilege[65]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Acknowledge Letter", { disabled: <%=acq_privilege[66]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Supply Order", { disabled: <%=acq_privilege[67]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Approve Titles", { disabled: <%=acq_privilege[68]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Sanction Form", { disabled: <%=acq_privilege[69]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order Amendment", { disabled: <%=acq_privilege[70]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Expenditure Analysis", { disabled: <%=acq_privilege[71]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Order List", { disabled: <%=acq_privilege[72]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("Receipt Voucher", { disabled: <%=acq_privilege[73]%>, url: ""}));
*/
 oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition39") %>", { disabled: <%=acq_privilege[49]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition40") %>", { disabled: <%=acq_privilege[50]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition41") %>", { disabled: <%=acq_privilege[51]%>, url: ""}));








              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition42") %>", { disabled: <%=acq_privilege[52]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition43") %>", { disabled: <%=acq_privilege[53]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition44") %>", { disabled: <%=acq_privilege[54]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition45") %>", { disabled: <%=acq_privilege[55]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition46") %>", { disabled: <%=acq_privilege[56]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition47") %>", { disabled: <%=acq_privilege[57]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition48") %>", { disabled: <%=acq_privilege[58]%>,url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition49") %>", { disabled: <%=acq_privilege[59]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition50") %>", { disabled: <%=acq_privilege[60]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition51") %>", { disabled: <%=acq_privilege[61]%> ,url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition52") %>", { disabled: <%=acq_privilege[62]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition53") %>", { disabled: <%=acq_privilege[63]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition54") %>", { disabled: <%=acq_privilege[64]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition55") %>", { disabled: <%=acq_privilege[65]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition56") %>", { disabled: <%=acq_privilege[66]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition57") %>", { disabled: <%=acq_privilege[67]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition58") %>", { disabled: <%=acq_privilege[68]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition59") %>", { disabled: <%=acq_privilege[69]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition60") %>", { disabled: <%=acq_privilege[70]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition61") %>", { disabled: <%=acq_privilege[71]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition62") %>", { disabled: <%=acq_privilege[72]%>, url: ""}));
              oAcqReportMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition63") %>", { disabled: <%=acq_privilege[73]%>, url: ""}));


               var oAcqHouseKeepingMenu = new YAHOO.widget.Menu("House Keeping");

            /*    oAcqHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Remove Invoice Record", {  disabled: <%=acq_privilege[75]%>, url: ""}));
                oAcqHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("Flushout Order Form", {  disabled: <%=acq_privilege[76]%>,  url: ""}));
*/
 oAcqHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition64") %>", {  disabled: <%=acq_privilege[75]%>, url: ""}));
                oAcqHouseKeepingMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition65") %>", {  disabled: <%=acq_privilege[76]%>,  url: ""}));


                var oAcqBudgetManagementMenu=new YAHOO.widget.Menu("Budget Management")

          /*      oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("Budget Heads", {  disabled: <%=acq_privilege[78]%>,url: "<%=request.getContextPath()%>/acquisition/acq_add_budgethead.jsp"}));
                oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("Income Budget Heads", {  disabled: <%=acq_privilege[79]%>,  url: "<%=request.getContextPath()%>/acquisition/acq_budget_alloc.do"}));
               oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("Exchange Rates", {  disabled: <%=acq_privilege[80]%>, url: "<%=request.getContextPath()%>/acquisition/acq_currency1.jsp"}));
                 oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("Set Base Currencey", {  disabled: <%=acq_privilege[81]%>, url: "<%=request.getContextPath()%>/acquisition/acq_base_currency.jsp"}));
*/

  oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition66") %>", {  disabled: <%=acq_privilege[78]%>,url: ""}));
                oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition84")%>", {  disabled: <%=acq_privilege[79]%>,  url: ""}));
               oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition68") %>", {  disabled: <%=acq_privilege[80]%>, url: ""}));
                oAcqBudgetManagementMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition85")%>", {  disabled: <%=acq_privilege[81]%>, url: ""}));

                var oAcqSystemSetupMenu = new YAHOO.widget.Menu("System Setup");

  /*              oAcqSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {  disabled: <%=acq_privilege[83]%>, url: ""}));
                oAcqSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("System Option", {  disabled: <%=acq_privilege[84]%>, url: ""}));
*/
 oAcqSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition69") %>", {  disabled: <%=acq_privilege[83]%>, url: ""}));
                oAcqSystemSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition70") %>", {  disabled: <%=acq_privilege[84]%>, url: ""}));


//End:Second Level Acquisition Menu 22222222222222222222222222222222222222222222222222222222222222222222

//Start: First Level Acquisition Menu1111111111111111111111111111111111111111111111111111111111111111111

                var oAcqMenu = new YAHOO.widget.Menu("Acquisition", { zIndex:2 });
            /*    oAcqMenu.addItem(new YAHOO.widget.MenuItem("Generate Title", {submenu:oAcqGenerateTitleMenu , disabled: <%=acq_privilege[1]%> , url:"" } ));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Approval Process" , {submenu:oAcqApprovalprocessMenu , disabled: <%=acq_privilege[6]%> , url:""}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Place Order", {submenu:oAcqPlaceorderMenu , disabled:<%=acq_privilege[21]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Receive Order", { disabled:<%=acq_privilege[29]%> , url:"<%=request.getContextPath()%>/acquisition/acq_budget_alloc.do" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Invoice Management", {submenu:oInvoiceManagementMenu , disabled:<%=acq_privilege[30]%> , url:"" }));

                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Accessioning", {submenu:oAccessioningMenu , disabled: <%=acq_privilege[39]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Manage Demand List", { disabled: <%=acq_privilege[42]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Free/Gifted Documents", { disabled:<%=acq_privilege[43]%> , url:"<%=request.getContextPath()%>/acquisition/acq_gift_entry.jsp" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Record Keeping", {submenu:oAcqRecordkeepingMenu , disabled:<%=acq_privilege[44]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem(" Acquisition Reports", {submenu:oAcqReportMenu ,  disabled:<%=acq_privilege[48]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("House Keping", {submenu:oAcqHouseKeepingMenu ,  disabled:<%=acq_privilege[74]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("Budget Management", {submenu:oAcqBudgetManagementMenu , disabled:<%=acq_privilege[77]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("System Setup", {submenu:oAcqSystemSetupMenu ,  disabled:<%=acq_privilege[82]%> , url:"" }));
*/
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition71") %>", {submenu:oAcqGenerateTitleMenu , disabled: <%=acq_privilege[1]%> , url:"" } ));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition72") %>" , {submenu:oAcqApprovalprocessMenu , disabled: <%=acq_privilege[6]%> , url:""}));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition73") %>", {submenu:oAcqPlaceorderMenu , disabled:<%=acq_privilege[21]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition74") %>", { disabled:<%=acq_privilege[29]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition75") %>", {submenu:oInvoiceManagementMenu , disabled:<%=acq_privilege[30]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition76") %>", {submenu:oAccessioningMenu , disabled: <%=acq_privilege[39]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition77") %>", { disabled: <%=acq_privilege[42]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition78") %>", { disabled:<%=acq_privilege[43]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition79") %>", {submenu:oAcqRecordkeepingMenu , disabled:<%=acq_privilege[44]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition80") %>", {submenu:oAcqReportMenu ,  disabled:<%=acq_privilege[48]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition81") %>", {submenu:oAcqHouseKeepingMenu ,  disabled:<%=acq_privilege[74]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition82") %>", {submenu:oAcqBudgetManagementMenu , disabled:<%=acq_privilege[77]%> , url:"" }));
                oAcqMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.acquisition83") %>", {submenu:oAcqSystemSetupMenu ,  disabled:<%=acq_privilege[82]%> , url:"" }));

 //End First Level Acquisition Menu1111111111111111111111111111111111111111111111111111111111111111111111111111
//************************************End Acquisition Menu******************************************************


//sum menu of admin-staffAcc

               // var oStaffAccMenu = new YAHOO.widget.Menu("StaffAcc");
                var oStaffAccMenu = new YAHOO.widget.Menu("StaffAcc");


            //    oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Create Staff Account", {url: "<%=request.getContextPath()%>/admin/account.jsp"}));
            oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin4")%>", {url: ""}));
                //oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("View Staff Account", {url: "<%=request.getContextPath()%>/admin/account.jsp"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin5")%>", {url: ""}));
                //oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Update Account", {url: "<%=request.getContextPath()%>/admin/account.jsp"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin6")%>", {url: ""}));
                //oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("Delete Staff Account", {url: "<%=request.getContextPath()%>/admin/account.jsp"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin7")%>", {url: ""}));
                //oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("View All Staff Account", {url: "<%=request.getContextPath()%>/admin/viewallaccount.do"}));
                oStaffAccMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin8")%>", {url: ""}));

//submenu of admin Staff
               var oStaffMenu = new YAHOO.widget.Menu("Staff");

             //   oStaffMenu.addItem(new YAHOO.widget.MenuItem("Register Staff", {url: "<%=request.getContextPath()%>/admin/acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin9")%>", {url: ""}));
                //oStaffMenu.addItem(new YAHOO.widget.MenuItem("Change Staff Details", {url: "<%=request.getContextPath()%>/admin/acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin10")%>", {url: ""}));
                //oStaffMenu.addItem(new YAHOO.widget.MenuItem("Delete Staff", {url: "<%=request.getContextPath()%>/admin/acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin11")%>", {url: ""}));
                //oStaffMenu.addItem(new YAHOO.widget.MenuItem("View Staff Record", {url: "<%=request.getContextPath()%>/admin/acq_register.jsp"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin12")%>", {url: ""}));

                //oStaffMenu.addItem(new YAHOO.widget.MenuItem("View All Staff Record", {url: "<%=request.getContextPath()%>/admin/viewall.do"}));
                oStaffMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin13")%>", {url: ""}));


//submenu of admin-privilege

               var oPrivilegeMenu = new YAHOO.widget.Menu("Privilege");

             //   oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("Assign Privileges", {url: "<%=request.getContextPath()%>/admin/assign_privilege.jsp"}));
                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin14")%>", {url: ""}));
               // oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("Change Privileges", {url: "<%=request.getContextPath()%>/admin/assign_privilege.jsp"}));

                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin15")%>", {url: ""}));
               // oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("View Privileges", {url: "<%=request.getContextPath()%>/admin/assign_privilege.jsp"}));
                oPrivilegeMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin16")%>", {url: ""}));


//submenu of Manage Library

<%
        if(login_role.equalsIgnoreCase("insti-admin") || login_role.equalsIgnoreCase("admin")){%>
                var oManDepMenu =new YAHOO.widget.Menu("Manage SubLibrary");

               // oManDepMenu.addItem(new YAHOO.widget.MenuItem("Add SubLibrary", {url: "<%=request.getContextPath()%>/admin/manage_library.jsp"}))
                 oManDepMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.sublibrary1")%>", {url: ""}))
               // oManDepMenu.addItem(new YAHOO.widget.MenuItem("Update SubLibrary", {url: "<%=request.getContextPath()%>/admin/manage_library.jsp"}))
                oManDepMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.sublibrary2")%>", {url: ""}))
               // oManDepMenu.addItem(new YAHOO.widget.MenuItem("Delete SubLibrary", {url: "<%=request.getContextPath()%>/admin/manage_library.jsp"}))
               oManDepMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.sublibrary3")%>", {url: ""}))
               // oManDepMenu.addItem(new YAHOO.widget.MenuItem("View SubLibrary", {url: "<%=request.getContextPath()%>/admin/manage_library.jsp"}))
                oManDepMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.sublibrary4")%>", {url: ""}))
               // oManDepMenu.addItem(new YAHOO.widget.MenuItem("ViewAll SubLibrary", {url: "<%=request.getContextPath()%>/admin/sub_lib_viewall.do"}))
               oManDepMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.sublibrary5")%>", {url: ""}))
<%}%>
//submenu of Manage Faculty


                var oFacultyMenu =new YAHOO.widget.Menu("Manage Faculty");

               // oFacultyMenu.addItem(new YAHOO.widget.MenuItem("Add Faculty", {url: "<%=request.getContextPath()%>/systemsetup/manage_faculty.jsp"}))
                oFacultyMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.faculty1")%>", {url: ""}))
                //oFacultyMenu.addItem(new YAHOO.widget.MenuItem("Update Faculty", {url: "<%=request.getContextPath()%>/systemsetup/manage_faculty.jsp"}))
                oFacultyMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.faculty2")%>", {url: ""}))
                //oFacultyMenu.addItem(new YAHOO.widget.MenuItem("Delete Faculty", {url: "<%=request.getContextPath()%>/systemsetup/manage_faculty.jsp"}))
                oFacultyMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.faculty3")%>", {url: ""}))
                //oFacultyMenu.addItem(new YAHOO.widget.MenuItem("View Faculty", {url: "<%=request.getContextPath()%>/systemsetup/manage_faculty.jsp"}))
                oFacultyMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.faculty4")%>", {url: ""}))
                //oFacultyMenu.addItem(new YAHOO.widget.MenuItem("ViewAll Faculty", {url: "<%=request.getContextPath()%>/systemsetup/view_allfaculty.do"}))
                oFacultyMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.faculty5")%>", {url: ""}))



//submenu of Manage Department


                var oDepartmentMenu =new YAHOO.widget.Menu("Manage Department");

              //  oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("Add Department", {url: "<%=request.getContextPath()%>/systemsetup/managedept1.do"}))
                oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.dept1")%>", {url: ""}))
                //oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("Update Department", {url: "<%=request.getContextPath()%>/systemsetup/managedept1.do"}))
                oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.dept2")%>", {url: ""}))
                //oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("Delete Department", {url: "<%=request.getContextPath()%>/systemsetup/managedept1.do"}))
                oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.dept3")%>", {url: ""}))
                //oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("View Department", {url: "<%=request.getContextPath()%>/systemsetup/managedept1.do"}))
                oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.dept4")%>", {url: ""}))
                //oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("ViewAll Department", {url: "<%=request.getContextPath()%>/systemsetup/view_alldept.do"}))
                oDepartmentMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.dept5")%>", {url: ""}))

//submenu of Manage Course


                var oCourseMenu =new YAHOO.widget.Menu("Manage Course");

               // oCourseMenu.addItem(new YAHOO.widget.MenuItem("Add Course", {url: "<%=request.getContextPath()%>/systemsetup/managecourse1.do"}))
                oCourseMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.course1")%>", {url: ""}))
                //oCourseMenu.addItem(new YAHOO.widget.MenuItem("Update Course", {url: "<%=request.getContextPath()%>/systemsetup/managecourse1.do"}))
                oCourseMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.course2")%>", {url: ""}))
                //oCourseMenu.addItem(new YAHOO.widget.MenuItem("Delete Course", {url: "<%=request.getContextPath()%>/systemsetup/managecourse1.do"}))
                oCourseMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.course3")%>", {url: ""}))
                //oCourseMenu.addItem(new YAHOO.widget.MenuItem("View Course", {url: "<%=request.getContextPath()%>/systemsetup/managecourse1.do"}))
                oCourseMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.course4")%>", {url: ""}))
                //oCourseMenu.addItem(new YAHOO.widget.MenuItem("ViewAll Course", {url: "<%=request.getContextPath()%>/systemsetup/view_allcourse.do"}))
                oCourseMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.course5")%>", {url: ""}))


//submenu of Member


                var oMemberMenu =new YAHOO.widget.Menu("Manage Member Types");

              //  oMemberMenu.addItem(new YAHOO.widget.MenuItem("Add Member", {url: "<%=request.getContextPath()%>/systemsetup/manage_member.jsp"}))
                oMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.member1")%>", {url: ""}))
              //  oMemberMenu.addItem(new YAHOO.widget.MenuItem("Update Member", {url: "<%=request.getContextPath()%>/systemsetup/manage_member.jsp"}))
                oMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.member2")%>", {url: ""}))
                //oMemberMenu.addItem(new YAHOO.widget.MenuItem("Delete Member", {url: "<%=request.getContextPath()%>/systemsetup/manage_member.jsp"}))
                oMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.member3")%>", {url: ""}))
                //oMemberMenu.addItem(new YAHOO.widget.MenuItem("View Member", {url: "<%=request.getContextPath()%>/systemsetup/manage_member.jsp"}))
                oMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.member4")%>", {url: ""}))
                //oMemberMenu.addItem(new YAHOO.widget.MenuItem("ViewAll AllMember", {url: "<%=request.getContextPath()%>/systemsetup/viewall_member.do"}))
                oMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.member5")%>", {url: ""}))



//submenu of SubMember


                var oSubMemberMenu =new YAHOO.widget.Menu("Manage SubMember Types");

                //oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("Add SubMember", {url: "<%=request.getContextPath()%>/systemsetup/submember1.do"}))
                oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.submember1")%>", {url: ""}))
                //oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("Update SubMember", {url: "<%=request.getContextPath()%>/systemsetup/submember1.do"}))
                oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.submember2")%>", {url: ""}))
                //oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("Delete SubMember", {url: "<%=request.getContextPath()%>/systemsetup/submember1.do"}))
                oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.submember3")%>", {url: ""}))
                //oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("View SubMember", {url: "<%=request.getContextPath()%>/systemsetup/submember1.do"}))
                oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.submember4")%>", {url: ""}))
                //oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("ViewAll AllSubMember", {url: "<%=request.getContextPath()%>/systemsetup/viewall_submember.do"}))
                oSubMemberMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.submember5")%>", {url: ""}))






//submenu of Manage Book Type
                var oBookMenu =new YAHOO.widget.Menu("Manage Document Category");

                //oBookMenu.addItem(new YAHOO.widget.MenuItem("Add Document Category", {url: "<%=request.getContextPath()%>/systemsetup/document_category.jsp"}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.doccat1")%>", {url: ""}))
                //oBookMenu.addItem(new YAHOO.widget.MenuItem("Update Document Category", {url: "<%=request.getContextPath()%>/systemsetup/document_category.jsp"}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.doccat2")%>", {url: ""}))
               // oBookMenu.addItem(new YAHOO.widget.MenuItem("Delete Document Category", {url: "<%=request.getContextPath()%>/systemsetup/document_category.jsp"}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.doccat3")%>", {url: ""}))
                //oBookMenu.addItem(new YAHOO.widget.MenuItem("View Document Category", {url: "<%=request.getContextPath()%>/systemsetup/document_category.jsp"}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.doccat4")%>", {url: ""}))
                //oBookMenu.addItem(new YAHOO.widget.MenuItem("ViewAll Document Category", {url: "<%=request.getContextPath()%>/systemsetup/viewallcat.do"}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.doccat5")%>", {url: ""}))

//submenu of Manage Fine
                var oFineMenu =new YAHOO.widget.Menu("Configure Fine Parameters");

                //oFineMenu.addItem(new YAHOO.widget.MenuItem("Set FineDetail ", {url: "<%=request.getContextPath()%>/systemsetup/managebooktype.do"}))
                oFineMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.fine1")%>", {url: ""}))
                //oFineMenu.addItem(new YAHOO.widget.MenuItem("Update FineDetail", {url: "<%=request.getContextPath()%>/systemsetup/managebooktype.do"}))
                oFineMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.fine2")%>", {url: ""}))
                //oFineMenu.addItem(new YAHOO.widget.MenuItem("Delete FineDetail", {url: "<%=request.getContextPath()%>/systemsetup/managebooktype.do"}))
                oFineMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.fine3")%>", {url: ""}))
                //oFineMenu.addItem(new YAHOO.widget.MenuItem("View FineDetail", {url: "<%=request.getContextPath()%>/systemsetup/managebooktype.do"}))
                oFineMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.fine4")%>", {url: ""}))
                //oFineMenu.addItem(new YAHOO.widget.MenuItem("ViewAll FineDetail", {url: "<%=request.getContextPath()%>/systemsetup/view_allbook.do"}))
                oFineMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.fine5")%>", {url: ""}))


//submenu of Manage Locations
                var oLocations =new YAHOO.widget.Menu("Manage Locations");
              /*  oLocations.addItem(new YAHOO.widget.MenuItem("Add Location", {url: "<%=request.getContextPath()%>/systemsetup/add_location.jsp"}))
                oLocations.addItem(new YAHOO.widget.MenuItem("Update Location", {url: "<%=request.getContextPath()%>/systemsetup/add_location.jsp"}))
                oLocations.addItem(new YAHOO.widget.MenuItem("Delete Location", {url: "<%=request.getContextPath()%>/systemsetup/add_location.jsp"}))
                oLocations.addItem(new YAHOO.widget.MenuItem("View Locations", {url: "<%=request.getContextPath()%>/systemsetup/add_location.jsp"}))
                oLocations.addItem(new YAHOO.widget.MenuItem("ViewAll Locations", {url: "<%=request.getContextPath()%>/systemsetup/view_alllocations.do"}))
*/
                 oLocations.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.loc1")%>", {url: ""}))
                oLocations.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.loc2")%>", {url: ""}))
                oLocations.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.loc3")%>", {url: ""}))
                oLocations.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.loc4")%>", {url: ""}))
                oLocations.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.loc5")%>", {url: ""}))




//submenu of manage notices

                 var oManNotMenu =new YAHOO.widget.Menu("Manage Notices");

              /*  oManNotMenu.addItem(new YAHOO.widget.MenuItem("Add Notices", {url: "<%=request.getContextPath()%>/systemsetup/manage_notices.jsp"}))
                oManNotMenu.addItem(new YAHOO.widget.MenuItem("Update Notices", {url: "<%=request.getContextPath()%>/systemsetup/manage_notices.jsp"}))
                oManNotMenu.addItem(new YAHOO.widget.MenuItem("Delete Notices", {url: "<%=request.getContextPath()%>/systemsetup/manage_notices.jsp"}))
                oManNotMenu.addItem(new YAHOO.widget.MenuItem("View Notices", {url: "<%=request.getContextPath()%>/systemsetup/manage_notices.jsp"}))
                oManNotMenu.addItem(new YAHOO.widget.MenuItem("ViewAll Notices", {url: "<%=request.getContextPath()%>/systemsetup/view_allnotices.do"}))

                */

                oManNotMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.notice1")%>", {url: ""}))
                oManNotMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.notice2")%>", {url: ""}))
                oManNotMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.notice3")%>", {url: ""}))
                oManNotMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.notice4")%>", {url: ""}))
                oManNotMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup.notice5")%>", {url: ""}))



//submenu of Administrator
               // var oAdminMenu = new YAHOO.widget.Menu("Administrator", { zIndex:2 });
               var oAdminMenu = new YAHOO.widget.Menu("<%=resource.getString("admin.header.main2")%>", { zIndex:2 });

               // oAdminMenu.addItem(new YAHOO.widget.MenuItem("Manage Staff", { submenu: oStaffMenu }));
                oAdminMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin3")%>", { submenu: oStaffMenu }));
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Manage Staff Account", { submenu: oStaffAccMenu }));
                oAdminMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin1")%>", { submenu: oStaffAccMenu }));
                //oAdminMenu.addItem(new YAHOO.widget.MenuItem("Manage Staff Privilege", { submenu: oPrivilegeMenu }));
                oAdminMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.admin2")%>", { submenu: oPrivilegeMenu }));
		//oAdminMenu.addItem(new YAHOO.widget.MenuItem("Change Institute Logo", {url: "<%=request.getContextPath()%>/admin/changelogo.jsp"}));
		oAdminMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.changeimage")%>", {url: ""}));

//submenu of System Setup
                var oSetupMenu = new YAHOO.widget.Menu("System Setup", { zIndex:2 });

               /*  oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage Notices", {submenu:oManNotMenu}));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage Locations", { submenu: oLocations }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage Member Types", { submenu: oMemberMenu }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage SubMember Types", { submenu: oSubMemberMenu }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage Document Category", { submenu: oBookMenu }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("Configure Fine Parameters", { submenu: oFineMenu }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage Faculty", { submenu: oFacultyMenu }));
                oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage Department", { submenu: oDepartmentMenu }));
                oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage Courses", { submenu: oCourseMenu }));
                      */
                     oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup1")%>", {submenu:oManNotMenu}));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup2")%>", { submenu: oLocations }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup3")%>", { submenu: oMemberMenu }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup4")%>", { submenu: oSubMemberMenu }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup5")%>", { submenu: oBookMenu }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup6")%>", { submenu: oFineMenu }));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup7")%>", { submenu: oFacultyMenu }));
                oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup8")%>", { submenu: oDepartmentMenu }));
                oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup9")%>", { submenu: oCourseMenu }));




            <% if(login_role.equalsIgnoreCase("insti-admin") || login_role.equalsIgnoreCase("admin")){%>
               // oSetupMenu.addItem(new YAHOO.widget.MenuItem("Manage SubLibrary", {submenu:oManDepMenu}));
                 oSetupMenu.addItem(new YAHOO.widget.MenuItem("<%=resource.getString("admin.header.systemsetup10")%>", {submenu:oManDepMenu}));
  <%}%>














//submenu of Manage Book Type


                var oBookMenu =new YAHOO.widget.Menu("Manage Book Category");

                oBookMenu.addItem(new YAHOO.widget.MenuItem("Add Book Category", {url: ""}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("Update Book Category", {url: ""}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("Delete Book Category", {url: ""}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("View Book Category", {url: ""}))
                oBookMenu.addItem(new YAHOO.widget.MenuItem("ViewAll Book Category", {url: ""}))







//*****************************************Start main menu*******************************************************
                var oMenuBar = new YAHOO.widget.MenuBar("menubar");
<% if(locale1.equalsIgnoreCase("ar")|| locale1.equalsIgnoreCase("ur")){%>
     //oMenuBar.addItem("HELP");
               oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main10") %>", {url:""}));
                 //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("OPAC", {url:"<%=request.getContextPath()%>/OPAC/OPACmain.jsp"}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main9") %>", {url:""}));
         //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("UTILTIES", { disabled: <%=privilege[7]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main8") %>", { disabled: <%=privilege[7]%>}));
             //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("SYSTEM SETUP", { submenu: oSetupMenu, disabled:<%=privilege[6]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main7") %>", { submenu: oSetupMenu, disabled:<%=privilege[6]%>}));
               //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("SERIAL", { submenu: oSerMenu , disabled: <%=privilege[4]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main6") %>", { submenu: oSerMenu , disabled: <%=privilege[4]%>}));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("CIRCULATION", { submenu: oCirMenu , disabled: <%=privilege[3]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main5") %>", { submenu: oCirMenu , disabled: <%=privilege[3]%>}));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("CATALOGUING", { submenu: oCatMenu, disabled: <%=privilege[2]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main4") %>", { submenu: oCatMenu, disabled: <%=privilege[2]%> }));
               //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("ACQUISITION", { submenu: oAcqMenu, disabled:<%=privilege[1]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main3") %>", { submenu: oAcqMenu, disabled:<%=privilege[1]%> }));
               //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("ADMINISTRATOR", { submenu: oAdminMenu , disabled:<%=privilege[5]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main2") %>", { submenu: oAdminMenu , disabled:<%=privilege[5]%> }));





  //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("HOME", {url:"<%=request.getContextPath()%>/admin/main.jsp"}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main1") %>", {url:""}));



    <%}else{%>
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("HOME", {url:"<%=request.getContextPath()%>/admin/main.jsp"}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main1") %>", {url:""}));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("ADMINISTRATOR", { submenu: oAdminMenu , disabled:<%=privilege[5]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main2") %>", { submenu: oAdminMenu , disabled:<%=privilege[5]%> }));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("ACQUISITION", { submenu: oAcqMenu, disabled:<%=privilege[1]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main3") %>", { submenu: oAcqMenu, disabled:<%=privilege[1]%> }));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("CATALOGUING", { submenu: oCatMenu, disabled: <%=privilege[2]%> }));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main4") %>", { submenu: oCatMenu, disabled: <%=privilege[2]%> }));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("CIRCULATION", { submenu: oCirMenu , disabled: <%=privilege[3]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main5") %>", { submenu: oCirMenu , disabled: <%=privilege[3]%>}));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("SERIAL", { submenu: oSerMenu , disabled: <%=privilege[4]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main6") %>", { submenu: oSerMenu , disabled: <%=privilege[4]%>}));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("SYSTEM SETUP", { submenu: oSetupMenu, disabled:<%=privilege[6]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main7") %>", { submenu: oSetupMenu, disabled:<%=privilege[6]%>}));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("UTILTIES", { disabled: <%=privilege[7]%>}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main8") %>", { disabled: <%=privilege[7]%>}));
                //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("OPAC", {url:"<%=request.getContextPath()%>/OPAC/OPACmain.jsp"}));
                oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main9") %>", {url:""}));
                //oMenuBar.addItem("HELP");
                 oMenuBar.addItem(new YAHOO.widget.MenuBarItem("<%=resource.getString("admin.header.main10") %>", {url:""}));

                <%}%>
//*****************************************End main menu*******************************************************
                               //oMenuBar.addItem(new YAHOO.widget.MenuBarItem("Examples Home", { url: "index.html" }));


                // Render the MenuBar instance and corresponding submenus

                oMenuBar.render(document.body);


                /*
                    Add a "click" and "mouseover" event handler to each item
                    in the root MenuBar instnace
                */

                var i = oMenuBar.getItemGroups()[0].length - 1,
                    oMenuBarItem;

                do {

                    oMenuBarItem = oMenuBar.getItem(i);

                    if(oMenuBarItem) {

                        oMenuBarItem.clickEvent.subscribe(
                                onMenuBarItemClick,
                                oMenuBarItem,
                                true
                            );

                        oMenuBarItem.mouseOverEvent.subscribe(
                                onMenuBarItemMouseOver,
                                oMenuBarItem,
                                true
                            );

                    }

                }
                while(i--);


                // "click" event handler for the document

                function onDocumentClick(p_oEvent) {

                    var oTarget = YAHOO.util.Event.getTarget(p_oEvent);

                    if(
                        oTarget != oMenuBar.element &&
                        !YAHOO.util.Dom.isAncestor(oMenuBar.element, oTarget)
                    ) {

                        oMenuBar.hasFocus = false;

                        if(oMenuBar.activeItem) {

                            var oSubmenu = oMenuBar.activeItem.cfg.getProperty("submenu");

                            if(oSubmenu) {

                                oSubmenu.hide();

                            }

                            oMenuBar.clearActiveItem();
                            oMenuBar.activeItem.blur();

                        }

                    }

                }


            }


            // Add a "load" handler for the window

            YAHOO.util.Event.addListener(window, "load", YAHOO.example.onWindowLoad);


//Drop Down Menu Code

var urls1 = new buildArray("",
"<%=request.getContextPath()%>/admin/staffupdate.do",
"<%=request.getContextPath()%>/admin/changeuserpassword"


);


function buildArray()
{
  var a = buildArray.arguments;

  for ( var i=0; i<a.length; i++ )
  {
    this[i] = a[i];
  }

  this.length = a.length;
}


function go ( which, num, win )
{
  var n = which.selectedIndex;

  if ( n != 0 )
  {
    var url = eval ( "urls" + num + "[n]" )
    if ( win )
    {
      openWindow ( url );
    }
    else
    {
      location.href = url;
    }
  }
}

function openWindow ( url )
{
  popupWin = window.open ( url, 'remote', 'width=500,height=350' );
}


function additem(linkname,dest){
  document.write('<a  href="'+dest+'">'+linkname+'</a><br>')
}

function toggle_menu(state){
var theMenu=document.getElementById("ddmenu").style;
if (state==0) {
  theMenu.visibility="hidden" }
else {
  theMenu.visibility = (theMenu.visibility=="hidden") ? "visible" : "hidden";
}
}






        </script>
<style type="text/Stylesheet">
    #ddmenu a{ text-decoration:none; }
#ddmenu a:hover{ background-color:#FFFF95;

    </style>



    </head>

    <body style="margin:2px 2px 2px 2px;">

   <% if(locale1.equalsIgnoreCase("ar")|| locale1.equalsIgnoreCase("ur")){%>
<table width="100%"  border="0px" class="logout">

               <tr>
                    <td  width="300px"   valign="top">
                        <table border="0"><tr><td valign="top"><%=resource.getString("login.hello")%>,&nbsp;
                        <script>document.write('<span ');
document.write('height:10px;border:0px solid black;font:bold 10pt Verdana;');
document.write(' onClick="toggle_menu(1);');
document.write('event.cancelBubble=1" ><span style="cursor:hand;">');
document.write('<%=username%> <img width=10 height=10 src="<%=request.getContextPath()%>/images/down.gif"></span>')
document.write('<div id="ddmenu" style="');
document.write('height:45px;border:0px solid black;background-color:white;text-align: right;padding-right:2px');
document.write('overflow-y:scroll;visibility:hidden;">')
  additem("Change Profile","<%=request.getContextPath()%>/admin/staffupdate.do?id=admin");
additem("Change Password","<%=request.getContextPath()%>/admin/changeuserpassword.do");



document.onclick= function() {toggle_menu(0); }
document.write('</div></span>');


                        </script>
                                </td><td valign="top">
&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/logout.do"><%=resource.getString("login.signout")%></a>&nbsp;&nbsp;&nbsp;
                                </td></tr></table>



</td>
                    <td align="center" class=""><span style="font-size:20px;font-weight:bold;color:blue;" > <%=library_name%></span>&nbsp;&nbsp;<br/><span class="mess"><%=resource.getString("admin.header.role")%>:&nbsp;[<%=role_name%>]&nbsp;&nbsp;<%=resource.getString("opac.simplesearch.library")%>&nbsp;[<%=sublibrary_name%>]&nbsp;&nbsp;</span></td>


             <td width="150px"   align="right" >


                    <img src="<%=request.getContextPath()%>/images/opac_lib.PNG" alt="banner space"   align="top" style="padding:5px 5px 5px 5px;">

                   </td>
               </tr>




                </table>
                                <%}else{%>

<table width="100%"  border="0px"  class="logout">

               <tr><td width="150px"  align="left">


                    <img src="<%=request.getContextPath()%>/images/opac_lib.PNG" alt="banner space"   align="top" style="padding:5px 5px 5px 5px;">

                   </td>
                        <td align="center" class=""><span style="font-size:20px;font-weight:bold;color:blue;" > <%=library_name%></span>&nbsp;&nbsp;<br/><span class="mess"><%=resource.getString("opac.simplesearch.library")%>&nbsp;[<%=sublibrary_name%>]&nbsp;&nbsp;<%=resource.getString("admin.header.role")%>:&nbsp;[<%=role_name%>]</span></td>

                    <td  align="<%=align%>"  width="300px"  valign="top">
                        <table border="0"><tr><td valign="top"><%=resource.getString("login.hello")%>,&nbsp;
                        <script>document.write('<span ');
document.write('height:10px;border:0px solid black;font:bold 10pt Verdana;');
document.write(' onClick="toggle_menu(1);');
document.write('event.cancelBubble=1" ><span style="cursor:hand;">');
document.write('<%=username%> <img width=10 height=10 src="<%=request.getContextPath()%>/images/down.gif"></span>')
document.write('<div id="ddmenu" style="');
document.write('height:45px;border:0px solid black;background-color:white;text-align: right;padding-right:2px');
document.write('overflow-y:scroll;visibility:hidden;">')
  additem("Change Profile","<%=request.getContextPath()%>/admin/staffupdate.do");
additem("Change Password","<%=request.getContextPath()%>/admin/changeuserpassword.do");



document.onclick= function() {toggle_menu(0); }
document.write('</div></span>');


                        </script>
                                </td><td valign="top">
&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/logout.do"><%=resource.getString("login.signout")%></a>&nbsp;&nbsp;&nbsp;
                                </td></tr></table>



</td>
                </tr>





                </table>

<%}%>


        <html:form action="/assign_privilege2" method="post">

<div
   style="
      top: 105;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/><br><br><br>
   <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">
             
                    <tr><td align="center">



                
 <p align="center" class="mess">Privilege Assigned to:&nbsp;<b><%=staff_name%></b> with role &nbsp;<b><%=staff_role%></b> are shown in the above Menu </p>
 <input type="button" value="Back" onclick="javascript:location.href='<%=request.getContextPath()%>/admin/assign_privilege.jsp'"/>

                    </td></tr></table>
                    </td></tr></table>








                       

   

</div>



 </html:form>
        </body>
</html>
