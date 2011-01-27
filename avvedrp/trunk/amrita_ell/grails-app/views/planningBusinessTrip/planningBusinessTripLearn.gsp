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
      <img src="${resource(dir:'images',file:'tmplearn.jpg')}"  alt="Header" usemap="#Map" border="0"/>
	  <map name="Map" id="Map">
      <area shape="rect" coords="121,4,233,60" href="planningBusinessTripListen.gsp?pg=careerLab2 " />
      <area shape="rect" coords="244,5,355,59" href="planningBusinessTripSpeak.gsp?pg=careerLab2 " />
      <area shape="rect" coords="365,6,478,58" href="planningBusinessTripQuiz.gsp?pg=careerLab2 " />
      <area shape="rect" coords="486,4,598,61" href="planningBusinessTripActivity.gsp?pg=careerLab2 " />
  </map>
      </td>
        <tr>
      <td class="innerContentWithOutMenu" valign="top">
          <p><strong class="heading">Introduction to Planning Business Trip</strong></p><br>
   <p><span class="textContent">
    <div align="right">


      <img src="${resource(dir:'images',file:'planningBT.jpg')}"  alt="Header" align="right" border="0"/>

    </div>
      Business travel is the  practice of people traveling for purposes related to their work.
        Business Travel to and from  India has increased as a direct outcome of economic liberalisation.
      Foreign  investments have increased in Indian companies. This has led to a steady growth  of business or corporate travels abroad.</span> </p> <br>

<p><span class="textContent">India  has caught the eye of the foreign investors who are interested
in investing in  India. The recent rise of the BPO phenomenon has contributed a lot to the  growing number of business travelers.</span></p><br> 

        <p><span class="textContent">For most people, traveling for business is the  answer to a
lifelong dream: the opportunity to see new places, meet new people  and learn more about the whole world.
 Whether  you enjoy business travel or feel more like an accidental tourist, it would be  useful for you to learn some tips that could
make your trips more enjoyable and  less stressful.Let us look at a conversation between John from the USA and  Akash from India, who is going to the
USA for the first time.</span> </p>
        <div align="center">
        <g:include controller="planningBusinessTrip"  action="embeddedAudio"/>
          </div>
     </td>
      </tr>
      </table>

  </body>
