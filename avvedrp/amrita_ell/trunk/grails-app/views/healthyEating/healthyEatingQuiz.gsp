<%--
  Created by IntelliJ IDEA.
  User: sreejeshknair
  Date: Dec 8, 2010
  Time: 4:45:42 PM
  To change this template use File | Settings | File Templates.
--%>

<head>

 <meta name='layout' content='innerLayout' />
<title></title>
  <!--Link the CSS style  sheet that styles the Collapsible Panel-->


</head>
<body>
 <table  border="0" align="top" class="innerContentWithMenu">
      <tr>
        <td >
      <img src="${resource(dir:'images',file:'tmpQuiz.jpg')}"  alt="Header" usemap="#Map" border="0"/>
	  <map name="Map" id="Map">
        <area shape="rect" coords="3,3,113,57" href="healthyEatingLearn.gsp?pg=conversationalEnglish" />
      <area shape="rect" coords="121,4,233,60" href="healthyEatingListen.gsp?pg=conversationalEnglish" />
      <area shape="rect" coords="244,5,355,59" href="healthyEatingSpeak.gsp?pg=conversationalEnglish" />
      <area shape="rect" coords="486,4,598,61" href="healthyEatingActivity.gsp?pg=conversationalEnglish" />
  </map>
      </td>
      </tr>
        <tr>
      <td class="innerContentWithOutMenu" valign="top">


                        <g:include controller="healthyEating"  action="healthyEatingAccordion"/>



     </td>
      </tr>
      </table>
</body>