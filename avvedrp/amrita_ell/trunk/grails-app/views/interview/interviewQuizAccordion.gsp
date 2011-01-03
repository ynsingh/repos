<head>
<!--<base href="http://www.ajaxdaddy.com/web20/jquery/interface-accordion/">-->
<style type="text/css">
body {
	
}
a {
	color: #000000;
}
</style>

<title>Accordion - Interface plugin for jQuery</title>
<!--<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript" src="interface.js"></script>
<link type="text/css" href="interface-accordion.css" rel="stylesheet">-->
   <link rel="stylesheet" type="text/css"  href="${createLinkTo(dir:'css',file:'interface-accordion.css')}">
     <script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.min.js')}"></script>
     <script type="text/javascript" src="${createLinkTo(dir:'js',file:'interface.js')}"></script>
   <script type="text/javascript" src="${createLinkTo(dir:'js',file:'quizconfig.js')}"></script>

</head>
<body>
<dl id="myAccordion">
  <dt class="someClass">Comprehension</dt>
  <dd>
    <p align="center">

    <form method="POST" name="myquiz">

 
    <div class="quizsubheading">
    1.What are Ms. Sheila Mathew’s qualifications?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question1">&nbsp;All the three are correct.<br>
    <input type="radio" value="b" name="question1">&nbsp;Graduate program in Media Studies from Christ College, Bangalore.<br>
    <input type="radio" value="c" name="question1">&nbsp;Two year Diploma in Advertising  from New Age Media.<br>
    <input type="radio" value="d" name="question1">&nbsp;The diploma covered 2D and 3D animation programs, Adobe After Effects and 3D Studio Max.<br>
    </div>

    <br>

      <div class="quizsubheading">
    2.Which are the two foreign languages studied by Ms. Mathew?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question2"> &nbsp;French and German.<br>
    <input type="radio" value="b" name="question2">&nbsp; German and Italian<br>
    <input type="radio" value="c" name="question2"> &nbsp;Chinese  and French<br>
    <input type="radio" value="d" name="question2"> &nbsp;None of the above.<br>
    </div>

    <br>

    <div class="quizsubheading">
    3.What did Ms. Mathew like about her projects?</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;All the three are correct.<br>
    <input type="radio" value="b" name="question3">&nbsp;The freedom to try out new things.<br>
    <input type="radio" value="c" name="question3">&nbsp;The brainstorming sessions.<br>
    <input type="radio" value="d" name="question3">&nbsp;The design and storyboarding.<br>
    </div>
     <br>

    <div class="quizsubheading">
    4.How did Ms. Mathew work under pressure and meet deadlines?</div>  <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question4">&nbsp;All the three are correct.<br>
    <input type="radio" value="b" name="question4">&nbsp;By removing negative emotional factors and working hard.<br>
    <input type="radio" value="c" name="question4">&nbsp;By prioritizing and organizing work effectively.<br>
    <input type="radio" value="d" name="question4">&nbsp;Work extra time in case of pressure due to lack of time, to meet deadlines.<br>
    </div>

    <br>

    <div class="quizsubheading">
    5.	Why does Ms. Mathew want to work for the organization?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question5">&nbsp;All the three are correct.<br>
    <input type="radio" value="b" name="question5">&nbsp;The job position is exciting and an ideal match for her qualifications.  <br>
    <input type="radio" value="c" name="question5">&nbsp;It has challenges and growth opportunities.  <br>
    <input type="radio" value="d" name="question5">&nbsp;This is the kind of job she’d anticipate each morning.<br>
    </div>

    <br>
   
    </form>

         <div id='PopUp' style='display: none; position: relative  ; left: 0%; top: 0%; border: solid black 0px; padding: 10px; background-color: #dcebf5;text-align: justify; font-size: 12px; width: 90%; height:33%'>

      <g:include controller="interview"  action="quizResult"/>
            	<br />
                <div style='text-align: right;'><a onmouseover='this.style.cursor="pointer" ' style='font-size: 12px;' onfocus='this.blur();' onclick="document.getElementById('PopUp').style.display = 'none' " ><span style="text-decoration: underline;">Close</span></a>
                </div>
            </div>
    <form>
    <div align="center">
    
      <input type="button" value="Grade Me!" name="B1" onClick="gradeit()"> <input type="button" value="Reset" name="B2" onClick="document.myquiz.reset()"></div>

    </form>


  </dd>
  <dt class="someClass">Vocabulary</dt>
  <dd>
    <div class="quizsubheading">
       1. Bit rusty
    </div>  <br>

        <div class="quiztextContent">
        <input type="radio" value="a" name="question3">&nbsp;	Less than good, due to lack of use or practicel.<br>
        <input type="radio" value="b" name="question3">&nbsp;	Covered with dust or corroded<br>
        <input type="radio" value="c" name="question3">&nbsp;	Discoloured by age.<br>
        <input type="radio" value="d" name="question3">&nbsp;	In a mess.<br>
       <p> Hint: Due to the long gap, her knowledge on the subject is a bit rusty.</p>
        </div>
         <br>
    <div class="quizsubheading">
       2. Free hand
    </div>  <br>

        <div class="quiztextContent">
        <input type="radio" value="a" name="question3">&nbsp;	Freedom to do or decide as one sees fit.<br>
        <input type="radio" value="b" name="question3">&nbsp;	The condition of being free.<br>
        <input type="radio" value="c" name="question3">&nbsp;	Arouse curiosity or interest..<br>
        <input type="radio" value="d" name="question3">&nbsp;	None of the above.<br>
        <p>Hint: The teacher gave her assistant a <u>free hand</u> with the class.</p>
        </div>
         <br>
         <div class="quizsubheading">
       3. Brainstorming sessions
    </div>  <br>

        <div class="quiztextContent">
        <input type="radio" value="a" name="question3">&nbsp;	Intensive discussions to solve problems or generate ideas.<br>
        <input type="radio" value="b" name="question3">&nbsp;	A group problem-solving technique in which members spontaneously share ideas and solutions.<br>
        <input type="radio" value="c" name="question3">&nbsp;	A method of shared problem solving in which all members of a group spontaneously contribute ideas.<br>
        <input type="radio" value="d" name="question3">&nbsp;	All of the above.<br>
        <p>Hint: We had an intense <u>brainstorming session</u> today.</p>
        </div>
         <br>
         <div class="quizsubheading">
       4. Keep on toes
    </div>  <br>

        <div class="quiztextContent">
        <input type="radio" value="a" name="question3">&nbsp;	To force someone to continue giving all their attention and energy to what they are doing.<br>
        <input type="radio" value="b" name="question3">&nbsp;	To stand on your toes.<br>
        <input type="radio" value="c" name="question3">&nbsp;	To pay no attention.<br>
        <input type="radio" value="d" name="question3">&nbsp;	None of the above.<br>
        <p>Hint: He gave me a couple of extra things to do just to <u>keep me on my toes.</u></p>
        </div>
         <br>
    <form>
    <div align="center">

      <input type="button" value="Grade Me!" name="B1" onClick="gradeit()"> <input type="button" value="Reset" name="B2" onClick="document.myquiz.reset()"></div>

    </form>

  </dd>
  <dt class="someClass">Modal Verbs</dt>
  <dd>
    <div class="quiztextContent">
    <p>Modals are auxiliary verbs which show the speaker’s attitude or mood. Modals do not take “–s” or “to” after them, nor do they take “do”. For e.g.it would be wrong (*) to say  </p>

      <p>
    *We should to phone later <br>
    *I do may follow you   <br>
    *I cans understand you <br>
    </p>
      <p>
    They are used in the following ways
     <ol>
      <ul  type="disc">
      <li>To express intentions – will, might  - I’ll put them away today.  </li>
     <li> To express permission – can, may - May I sit down?</li>

   <li>To express ability – can, could - We can do this.</li>
   <li>	To express obligation – must, should, ought to, have to- You must get ready, we’ll be late!</li>
  <li>	To express prohibition – mustn’t, can’t, shouldn’t - You shouldn’t party every night.</li>
    <li>	To make offers – will, shall, can - Will you join the club, please? </li>
    <li>	To make suggestions – should, shall, could - We could go out tomorrow, don’t you think? </li>
    <li>	To make requests – can, could, would, may - Would you do this for me?</li>
     </ul>
      </ol>
      </p>
       </div>
  </dd>
 
</dl>
</p>
<script type="text/javascript">
 var actualchoices=new Array()
document.cookie="ready=yes"
$(document).ready(
function()
{
$('#myAccordion').Accordion(
{
headerSelector : 'dt',
panelSelector : 'dd',
activeClass : 'myAccordionActive',
hoverClass : 'myAccordionHover',
panelHeight : 200,
speed : 300
}
);
}
);
</script>


<noscript>
<p></p>
</noscript>
</body>