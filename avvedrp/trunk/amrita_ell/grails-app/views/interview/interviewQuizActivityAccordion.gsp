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
  <dt class="someClass">Positive and Negative Factors in an Interview</dt>
  <dd>
   <div class="quiztextContent">
    <p align="left">
      Given below are two boxes that highlight Positive and Negative factors in an interview.  Both these factors are divided into “Psychological and Behavioural Cues” and “Verbal Cues”.  These cues represent something that is said or done which leaves an impression on the interviewer.<br/><br/>
<b>From the below cues identify the category each one falls under and drag the cues to the right box:</b>
<br /> <br />
Early arrival &nbsp; &nbsp;Alert, expressive attitude  &nbsp;&nbsp;    Relaxed manner &nbsp;&nbsp;  Smiling  &nbsp;&nbsp; Clear voice &nbsp;&nbsp;Sticking to the main point  Relevant responses   &nbsp;&nbsp;       Organized information   &nbsp;&nbsp;       Spontaneous replies    &nbsp;&nbsp;      Candour   &nbsp;&nbsp;        Appropriate humour    &nbsp;&nbsp;      Late arrival  &nbsp;&nbsp; Inattentive    &nbsp;&nbsp;      Withdrawn or condescending    &nbsp;&nbsp;      Tense, fidgety  &nbsp;&nbsp;        Frowning   &nbsp;&nbsp;       Mumbling, timid &nbsp;&nbsp;         Changing the subject   &nbsp;&nbsp; Generalizing or excessive detail    &nbsp;&nbsp;      Disorganized    &nbsp;&nbsp;      Uncalled for humour   &nbsp;&nbsp;
       Long and frequent pauses  &nbsp;&nbsp;        Criticizing othersEvasive
   </div>
      <p align="center">
      <img src="${resource(dir:'images',file:'activity.jpg')}"  alt="Header"  border="0"/>
  </dd>
  <dt class="someClass">Activity for Interview Skills</dt>
  <dd> <div class="quiztextContent"><br>
    Do you know the meaning of the following terms? Here’s a small brainteaser. Try guessing the meaning from the context.  Don’t use the dictionary.
    </div>
       <img src="${resource(dir:'images',file:'activity-1.jpg')}"  alt="Header" usemap="#Map" border="0"/>
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