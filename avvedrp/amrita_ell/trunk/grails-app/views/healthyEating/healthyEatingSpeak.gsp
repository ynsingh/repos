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
        <td colspan="2">
      <img src="${resource(dir:'images',file:'tmpSpeak.jpg')}"  alt="Header" usemap="#Map" border="0"/>
	  <map name="Map" id="Map">
     <area shape="rect" coords="3,3,113,57" href="healthyEatingLearn.gsp?pg=conversationalEnglish" />
        <area shape="rect" coords="121,4,233,60" href="healthyEatingListen.gsp?pg=conversationalEnglish" />
      <area shape="rect" coords="365,6,478,58" href="healthyEatingQuiz.gsp?pg=conversationalEnglish" />
      <area shape="rect" coords="486,4,598,61" href="healthyEatingActivity.gsp?pg=conversationalEnglish" />
  </map>
      </td>
      </tr>
        <tr>
      <td class="innerContentWithOutMenu">

       <g:include controller="interview"  action="embeddedVideo"/>



     </td>
      <td valign="top" width=2000px>
        <br />
         <br />
         <br />
         <input type="radio" >&nbsp;	Mute interviewer voice and record<br>  <br />
          <input type="radio">&nbsp;	Mute candidate voice and record<br>  <br />
           <input type="button" value="Record" name="B1" onClick="">  <br />  <br />
            <input type="button" value="Play" name="B2" onClick="">  <br />
      </td>
      </tr>
      </table>

  </body>
