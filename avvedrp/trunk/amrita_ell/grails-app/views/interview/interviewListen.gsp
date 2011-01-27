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
      <img src="${resource(dir:'images',file:'tmplistern.jpg')}"  alt="Header" usemap="#Map" border="0"/>
	  <map name="Map" id="Map">
     <area shape="rect" coords="3,3,113,57" href="interviewLearn.gsp?pg=careerLab1 " />
     <area shape="rect" coords="244,5,355,59" href="interviewSpeak.gsp?pg=careerLab1 " />
      <area shape="rect" coords="365,6,478,58" href="interviewQuiz.gsp?pg=careerLab1 " />
      <area shape="rect" coords="486,4,598,61" href="interviewActivity.gsp?pg=careerLab1 " />
  </map>
      </td>
        <tr>
      <td class="innerContentWithOutMenu">
           <p><strong class="heading">Candidate 1 Ms. Sheila Mathew</strong></p>

            <g:include controller="interview"  action="embeddedVideo"/>

             

  <p><strong class="subheading">Interviewer  &amp; Candidate </strong><br />
     <span class="textContent"><br>
    <b>Interviewer:</b>  Good  morning, Miss Mathew.; How are you?<br />
    <br />
  <b>Candidate:</b> Good morning. I’m  fine thank you, and you?<br />
  <br />
  <b>Interviewer:</b>  I’m fine. I  gather that you’d like to join our team.<br />
  <br />
  <b>Candidate:</b> Yes, I would.<br />
  <br />
  <b>Interviewer:</b>  That’s good. We’d  like to know something about you. Can you tell us about your education?<br />
  <br />
  <b>Candidate:</b> Yes, I left school  in 1999 and went on to join a graduate program in Media Studies. And then I  wanted to do a course in advertising, which my college didn’t offer at  specialization level.<br />
  <br />
  <b>Interviewer:</b>  That would be at  Christ College, right?<br />
  <br />
  <b>Candidate:</b> Yes, in Bangalore.   As they didn’t have advertising, I went  to New Age Media, which was giving a two-year diploma in advertising.  In fact, I finished the course this summer.<br />
  <br />
  <b>Interviewer:</b>  Did the diploma  course cover any computer animation programs?<br />
  <br />
  <b>Candidate:</b> Yes, I did a 2D  animation program, Adobe AfterEffects and a 3D animation program, 3D Studio  Max.<br />
  <br />
  <b>Interviewer:</b>  That’s  interesting. Tell me what did you enjoy most at school? Which course did  you like the most?<br />
  <br />
  <b>Candidate:</b> I really enjoyed  studying foreign languages.<br />
  <br />
  <b>Interviewer:</b>  Foreign languages?<br />
  <br />
  <b>Candidate:</b> Yes. We were  offered French and German.<br />
  <br />
  <b>Interviewer:</b>  And are you fluent  in both of these now?<br />
  <br />
  <b>Candidate:</b> I’m good at  French. My German is a <u>bit rusty</u>. You see, I used a lot of French in my  projects.<br />
  <br />
  <b>Interviewer:</b>  Did you like those  projects? I see quite a few in your CV?<br />
  <br />
  <b>Candidate:</b> Oh yes, it was  exciting. I loved working on the projects. For one thing,you get a <u>free  hand</u> to try out new things. I also liked the <u>brainstorming sessions</u> and the long hours at design and storyboarding. It was a fulfilling experience.<br />
  <br />
  <b>Interviewer:</b>  I see you like a  fast work pace. Tell me, do you think you get more creative ideas when you are  working alone or does a team help?<br />
  <br />
  <b>Candidate:</b> I think a team  helps. A small one though. Discussing and going over concepts is very useful.<br />
  <br />
  <b>Interviewer:</b>  That’s good to  hear.<br />
  <br />
  <b>Interviewer:</b>  Can you work under  pressure and meet deadlines?<br />
  <br />
  <b>Candidate:</b> I think I work the same if  there is pressure or no pressure. I try to avoid negative emotional factors and  like to work hard. I always prioritise and organise my work efficiently. If the  situation involves pressure due to a lack of time, then the only difference in  my work would be the extra time I would need to put in to meet the deadline on  time.  So I believe I work well under  pressure.<br />
  <br />
  <b>Interviewer:</b>  Now tell us, why do  you want to work for this organisation?<br />
  <br />
  <b>Candidate:</b> The job position  is not only an exciting opportunity, but ideally matches my  qualifications.  It contains the  challenges to <u>keep me on my toes</u> and provides growth opportunities.  I understand that this company is on the way  up and I want to be a part of this business as it grows. This is the kind of  job I like to anticipate every morning.<br />
  <br />
  <b>Interviewer:</b>  Would you be  willing to relocate, if required?<br />
  <br />
  <b>Candidate:</b> Yes, provided  there are growth opportunities and the remuneration  package is good. <br />
  <br />
  <b>Interviewer:</b>  Thank you, Miss  Mathew.  It was nice meeting you. You will be hearing from us  shortly.<br />
  <br />
  <b>Candidate:</b>  Thank you sir. It was nice meeting you. I  appreciate your giving me an interview. I would really like to join your  team and feel I can contribute to the company. I look forward to hearing  from you. Good-bye Sir.<br />
  <br />
  <b>Interviewer:</b>  Thank you for  coming. Good-bye.</span> </p>
     </td>
      </tr>
      </table>

  </body>
