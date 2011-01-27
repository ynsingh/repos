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

</head>
<body>


    <table  border="0" align="top" class="innerContentWithMenu">
      <tr>
        <td >
      <img src="${resource(dir:'images',file:'tmpActivity.jpg')}"  alt="Header" usemap="#Map" border="0"/>
	  <map name="Map" id="Map">
      <area shape="rect" coords="3,3,113,57" href="interviewLearn.gsp?pg=careerLab1 " />
      <area shape="rect" coords="121,4,233,60" href="interviewListen.gsp?pg=careerLab1 " />
      <area shape="rect" coords="244,5,355,59" href="interviewSpeak.gsp?pg=careerLab1 " />
      <area shape="rect" coords="365,6,478,58" href="interviewQuiz.gsp?pg=careerLab1 " />

  </map>
      </td>
        <tr>
      <td class="innerContentWithOutMenu" valign="top">
        <g:include controller="interview"  action="interviewQuizActivityAccordion"/>
     </td>
      </tr>
      </table>

  </body>
