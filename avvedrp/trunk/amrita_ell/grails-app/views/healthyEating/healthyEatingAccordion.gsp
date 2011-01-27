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
    1. Why is it important to have a good breakfast in the morning?
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	It gives you the energy to begin the day.s<br>
    <input type="radio" value="b" name="question3">&nbsp;	It helps you maintain a balanced diet.<br>
    <input type="radio" value="c" name="question3">&nbsp;	It helps you lose your weight.<br>
    <input type="radio" value="d" name="question3">&nbsp;	All of the above.<br>
    </div>
     <br>
<div class="quizsubheading">
    2. Is food eaten late at night fattening?
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	No, it depends on what, how much you eat and how much physical activity you do during the day.<br>
    <input type="radio" value="b" name="question3">&nbsp;	Not if the food is low on calorie.<br>
    <input type="radio" value="c" name="question3">&nbsp;	Yes, as your body is idle at night.<br>
    <input type="radio" value="d" name="question3">&nbsp;	None of the above.<br>
    </div>
     <br>
     <div class="quizsubheading">
    3. How can low fat be high calorie?
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	By adding sugar, flour or starch thickeners.<br>
    <input type="radio" value="b" name="question3">&nbsp;	By eating large portions of food low on fat.<br>
    <input type="radio" value="c" name="question3">&nbsp;	Low fat or free free does not always mean it is low on calories.<br>
    <input type="radio" value="d" name="question3">&nbsp;	All of the above.<br>
    </div>
     <br>
     <div class="quizsubheading">
    4. Along with exercise, what do you need to do to lose weight?
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	You will have to be active every day and eat less.<br>
    <input type="radio" value="b" name="question3">&nbsp;	You will have to consume food with fewer calories.<br>
    <input type="radio" value="c" name="question3">&nbsp;	Have food high in starch.<br>
    <input type="radio" value="d" name="question3">&nbsp;	Skip your breakfast.<br>
    </div>
     <br>
     <div class="quizsubheading">
    5. Why does it take a lot of exercise to lose 1 pound of fat?
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	You need to walk 80 kilometers (50 miles) to lose 1 pound of fat.<br>
    <input type="radio" value="b" name="question3">&nbsp;	You build muscle that weighs more than fat.<br>
    <input type="radio" value="c" name="question3">&nbsp;	You tire easily and consume more food.<br>
    <input type="radio" value="d" name="question3">&nbsp;	None of the above.<br>
    </div>
     <br>
     <div class="quizsubheading">
    6. Why are bananas a good food?
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	All the three are correct.<br>
    <input type="radio" value="b" name="question3">&nbsp;	They are high in potassium.<br>
    <input type="radio" value="c" name="question3">&nbsp;	They come in their own packaging, are clean and very handy as a snack!<br>
    <input type="radio" value="d" name="question3">&nbsp;	There is only half a gram of fat and 95 calories in a banana<br>
    </div>
     <br>
    <p> <strong> Fill in the appropriate phrase to complete the sentence.</strong></p>
       <div class="quizsubheading">
   1. Bread and potatoes are not fattening unless they are  ____________.
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	eaten in large portions or covered with high-fat toppings like butter, sour cream, or mayonnaise.<br>
    <input type="radio" value="b" name="question3">&nbsp;	eaten twice a day.<br>
    <input type="radio" value="c" name="question3">&nbsp;	high on starch and calories.<br>
    <input type="radio" value="d" name="question3">&nbsp;	None of the above.<br>
    </div>
     <br>
       <div class="quizsubheading">
   2. Starch is a high source of energy for the body, ____________.
</div>  <br>

    <div class="quiztextContent">
    <input type="radio" value="a" name="question3">&nbsp;	as it has complex carbohydrates that are an important source of energy to the body.<br>
    <input type="radio" value="b" name="question3">&nbsp;	as it is low in fat and calorie.<br>
    <input type="radio" value="c" name="question3">&nbsp;	as it gives one a balanced diet.<br>
    <input type="radio" value="d" name="question3">&nbsp;	None of the above<br>
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