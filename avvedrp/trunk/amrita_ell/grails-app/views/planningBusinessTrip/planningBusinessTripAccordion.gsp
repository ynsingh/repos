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
    1.What does the GPS system or the Google do?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question1">&nbsp;It equips you with a map, gives direction and provides you with information about a place <br>
    <input type="radio" value="b" name="question1">&nbsp;Provides you with your tickets for travel.<br>
    <input type="radio" value="c" name="question1">&nbsp;	Teaches you foreign language.<br>
    <input type="radio" value="d" name="question1">&nbsp;	None of the above.<br>
    </div>

    <br>

      <div class="quizsubheading">
    2.What are the essentials you don’t forget while on a business trip,?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question2"> &nbsp;	All the above..<br>
    <input type="radio" value="b" name="question2">&nbsp; 	Your laptop, power supply, a foreign plug adapter and any files you’ll need for your meetings and presentations<br>
    <input type="radio" value="c" name="question2"> &nbsp;	A money belt as security for extra cash, credit cards and important documents<br>
    <input type="radio" value="d" name="question2"> &nbsp;	Do a research to see if you can use your mobile and how much it would cost to roam.<br>
    </div>

    <br>

    <div class="quizsubheading">
    3.	Can you put your laptop in your check-in baggage?
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	No, as rough handling can damage and cause repair with huge bills<br>
    <input type="radio" value="b" name="question3">&nbsp;	No, if you are going to use it during the flight<br>
    <input type="radio" value="c" name="question3">&nbsp;	Yes, if you have no other cabin baggage.<br>
    <input type="radio" value="d" name="question3">&nbsp;	All of the above.<br>
    </div>
     <br>

    <div class="quizsubheading">
    4.Why do you need an extra change of clothes in your cabin baggage?</div>  <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question4">&nbsp;	To avoid inconvenience and unnecessary expenditure, if checked-in-baggage is lost or delayed<br>
    <input type="radio" value="b" name="question4">&nbsp;	To reduce the weight in the checked-in-baggage.<br>
    <input type="radio" value="c" name="question4">&nbsp;	For stopover at airports.<br>
    <input type="radio" value="d" name="question4">&nbsp;	None of the above<br>
    </div>

    <br>

    <div class="quizsubheading">
    5.What are the clothes you need to take on a business trip?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question5">&nbsp;	Depending on the weather, a couple of business suits and one or two casual outfits, should you go sightseeing<br>
    <input type="radio" value="b" name="question5">&nbsp;	You can take clothes you are most comfortable in <br>
    <input type="radio" value="c" name="question5">&nbsp;	Warm clothes for cold weather and dresses you wear to a party <br>
    <input type="radio" value="d" name="question5">&nbsp;	None of the above.<br>
    </div>

    <div class="quizsubheading">
    6.What do you do to cope with unforeseen delays or if there is no healthy food available?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question5">&nbsp;	Take some snacks and a bottle of mineral water along<br>
    <input type="radio" value="b" name="question5">&nbsp;	Avoid taking food at any unknown place <br>
    <input type="radio" value="c" name="question5">&nbsp;	Drink a lot of water.  <br>
    <input type="radio" value="d" name="question5">&nbsp;	All of the above.<br>
    </div>

    <div class="quizsubheading">
    7.Why should you book your tickets in advance?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question5">&nbsp;	To ensure you get confirmed tickets of your choice.<br>
    <input type="radio" value="b" name="question5">&nbsp;	To avoid last minute hassles <br>
    <input type="radio" value="c" name="question5">&nbsp;	To get seats of your choice<br>
    <input type="radio" value="d" name="question5">&nbsp;	None of the above.<br>
    </div>

    <div class="quizsubheading">
    8.Why should you book your hotel in advance?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question5">&nbsp;	A convention in town can leave you out of luck as their will be no rooms vacant in the hotel<br>
    <input type="radio" value="b" name="question5">&nbsp;	To get the room of your choice.<br>
    <input type="radio" value="c" name="question5">&nbsp;	To have facilities like a gym and a swimming pool. <br>
    <input type="radio" value="d" name="question5">&nbsp;	All of the above. <br>
    </div>

    <div class="quizsubheading">
    9.How should you choose your hotel?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question5">&nbsp;	All  of the above.<br>
    <input type="radio" value="b" name="question5">&nbsp;	It should be close to your place of work <br>
    <input type="radio" value="c" name="question5">&nbsp;	It should fulfill your requirement of phone, fax, computers,  wi-fi etc.  <br>
    <input type="radio" value="d" name="question5">&nbsp;	There should be cleanliness, food and entertainment as well as extra facilities like gym, swimming pool<br>
    </div>

    <div class="quizsubheading">
    10.In case of e-tickets, why should you take printouts earlier?</div> <br>
    <div class="quiztextContent">
    <input type="radio" value="a" name="question5">&nbsp;	The Internet or power supply might be down and leave you stranded.<br>
    <input type="radio" value="b" name="question5">&nbsp;	For trips abroad, printouts are always taken earlier. <br>
    <input type="radio" value="c" name="question5">&nbsp;	A printout is to be taken as soon as ticket is booked online.<br>
    <input type="radio" value="d" name="question5">&nbsp;	None of the above.<br>
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

  </dd>
  <dt class="someClass">Modal Verbs</dt>
  <dd>
   
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