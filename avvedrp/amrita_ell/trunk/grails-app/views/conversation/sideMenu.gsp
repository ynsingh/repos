<%--
  Created by IntelliJ IDEA.
  User: sreejeshknair
  Date: Dec 8, 2010
  Time: 3:54:45 PM
  To change this template use File | Settings | File Templates.
--%>
  <!doctype html public "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
        <title>Home</title>
                <meta name="layout" content="withcols" />
     <link rel="stylesheet" type="text/css"  href="${createLinkTo(dir:'css',file:'tree.css')}">
     <script type="text/javascript" src="${createLinkTo(dir:'js',file:'yahoo.js')}"></script>
    <script type="text/javascript" src="${createLinkTo(dir:'js',file:'event.js')}"></script>
    <script type="text/javascript" src="${createLinkTo(dir:'js',file:'treeview.js')}"></script>
           <script type="text/javascript" src="${createLinkTo(dir:'js',file:'jktreeview.js')}"></script>
	   <script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery-1.4.2.js')}"></script>


   <!-- Below is Style sheet for demos. Removed if desired -->
   <style type="text/css">

   body {margin-top: 0px;}
   a { text-decoration: underline; color: #ffff33; }
   a:hover { text-decoration: underline; color: #4d77c3; }
   
  
   </style>

    </head>
  <body>
  <table border=0 >
    <tr >
      
    <td  id="tree1"  >

      </td>
    </tr>
      <tr >
    <td id="tree2" >

      </td>
    </tr>

     <tr >
    <td id="tree3" >

      </td>
    </tr>
     <tr >
    <td id="tree4" >

      </td>
    </tr>
     <tr >
    <td id="tree5" >

      </td>
    </tr>
     <tr >
    <td id="tree6" >

      </td>
    </tr>
     <tr >
    <td id="tree7" >

      </td>
    </tr>
     <tr >
    <td id="tree8" >

      </td>
    </tr>
     <tr >
    <td id="tree9" >

      </td>
    </tr>
     <tr >
    <td id="tree10" >

      </td>
    </tr>
     <tr >
    <td id="tree11" >

      </td>
    </tr>
  <!--  <td id="tree3" >

      </td>
    </tr>
    <tr >
    <td id="tree4" >

      </td>
    </tr>
    <tr >
    <td id="tree5" >

      </td>
    </tr>
     <tr >
    <td id="tree6" >

      </td>
    </tr>
     <tr >
    <td id="tree7" >

      </td>
    </tr>
     <tr >
    <td id="tree8" >

      </td>
    </tr> -->
    
    </table>

 
     
<script type="text/javascript">


     
     function gup( name )
      {
    name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
     var regexS = "[\\?&]"+name+"=([^&#]*)";
    var regex = new RegExp( regexS );
    var results = regex.exec( window.location.href );
    if( results == null )
     return false;
    else
      return results[1];
    }
    var URL_param = gup( 'pg' );
 
  if(URL_param=="careerLab1") {
var pinetree1=new jktreeview("tree1")

var branch1=pinetree1.addItem("	Exploring the Job Market ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree1.addItem("Profiling Oneself for a Job          ", branch1) //Add this item to branch3
	pinetree1.addItem("	Searching for a Job      ", branch1 ) //Add this item to branch3
	pinetree1.addItem("	Setting up the interview ", branch1) //Add this item to branch3
pinetree1.treetop.draw();


var pinetree2=new jktreeview("tree2")
var branch2=pinetree2.addItem("	Preparing for the Interview ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree2.addItem("test         ", branch2 ) //Add this item to branch3
	pinetree2.treetop.draw();

var pinetree3=new jktreeview("tree3")
var branch3=pinetree3.addItem("	Preparing the Resume") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree3.addItem("Test         ", branch3 ) //Add this item to branch3
	pinetree3.treetop.draw();

var pinetree4=new jktreeview("tree4")
var branch4=pinetree4.addItem("	Preparing the Portfolio ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree4.addItem("Test         ", branch4) //Add this item to branch3
	pinetree4.treetop.draw();

var pinetree5=new jktreeview("tree5")
var branch5=pinetree5.addItem("	Types of Interviews  ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree5.addItem("	Face to Face Interview          ", branch5, "../interview/interviewLearn.gsp?pg=careerLab1") //Add this item to branch3
     pinetree5.addItem("	Phone Interview          ", branch5) //Add this item to branch3
     pinetree5.addItem("	Campus Interview         ", branch5) //Add this item to branch3
     pinetree5.addItem("	Technical Interview         ", branch5) //Add this item to branch3
      pinetree5.addItem("	HR Interview       ", branch5) //Add this item to branch3
	pinetree5.treetop.draw();

  var pinetree6=new jktreeview("tree6")
var branch6=pinetree6.addItem("Group Discussion ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree6.addItem("	Test         ", branch6 ) //Add this item to branch3
	pinetree6.treetop.draw();
     }
     else if(URL_param=="careerLab2"){
     var pinetree1=new jktreeview("tree1")

var branch1=pinetree1.addItem("	What your Company Wants from You") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree1.addItem("test         ", branch1, "../interview/interviewLearn.gsp?pg=careerLab1") //Add this item to branch3
	pinetree1.treetop.draw();


var pinetree2=new jktreeview("tree2")
var branch2=pinetree2.addItem("	Business Ethics ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree2.addItem("	Company Ethics        ", branch2) //Add this item to branch3
       pinetree2.addItem("	Personal Ethics        ", branch2) //Add this item to branch3
	pinetree2.treetop.draw();

var pinetree3=new jktreeview("tree3")
var branch3=pinetree3.addItem("Social Skills in the Workplace") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree3.addItem("	Introductions and Greetings         ", branch3) //Add this item to branch3
	   pinetree3.addItem("	Small Talk in Business         ", branch3) //Add this item to branch3
       pinetree3.addItem("	Business Etiquette         ", branch3) //Add this item to branch3
       pinetree3.treetop.draw();

var pinetree4=new jktreeview("tree4")
var branch4=pinetree4.addItem("o	Telephone Communication in the Workplace ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree4.addItem("	Appointments         ", branch4) //Add this item to branch3
	pinetree4.addItem("Taking Messages         ", branch4) //Add this item to branch3
    pinetree4.addItem("	Voice Mail        ", branch4) //Add this item to branch3
	pinetree4.addItem("	Video Conferencing         ", branch4) //Add this item to branch3
    pinetree4.addItem("	Conference Calls         ", branch4) //Add this item to branch3
       pinetree4.treetop.draw();

var pinetree5=new jktreeview("tree5")
var branch5=pinetree5.addItem("	Business Correspondence  ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree5.addItem("	Memos         ", branch5) //Add this item to branch3
     pinetree5.addItem("	Office Documents           ", branch5) //Add this item to branch3
     pinetree5.addItem("	Formal  Letters        ", branch5) //Add this item to branch3
     pinetree5.addItem("	Emails         ", branch5) //Add this item to branch3
     pinetree5.treetop.draw();

  var pinetree6=new jktreeview("tree6")
var branch6=pinetree6.addItem("	Customer Service ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree6.addItem("		Essentials         ", branch6) //Add this item to branch3
       pinetree6.addItem("		Complaints         ", branch6) //Add this item to branch3
       pinetree6.addItem("		Rapport         ", branch6) //Add this item to branch3
	pinetree6.treetop.draw();

   var pinetree7=new jktreeview("tree7")
var branch7=pinetree7.addItem("	Meetings ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree7.addItem("Agenda Planning       ", branch7) //Add this item to branch3
         pinetree7.addItem("	Types of Meetings        ", branch7) //Add this item to branch3
       pinetree7.addItem("	Successful meetings      ", branch7) //Add this item to branch3
       pinetree7.addItem("	Conducting meetings        ", branch7) //Add this item to branch3
	  pinetree7.addItem("	Participating in meetings      ", branch7) //Add this item to branch3
    pinetree7.treetop.draw();

   var pinetree8=new jktreeview("tree8")
var branch8=pinetree8.addItem("Presentation Skills ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree8.addItem("	test          ", branch8) //Add this item to branch3
	pinetree8.treetop.draw();

     var pinetree9=new jktreeview("tree9")
var branch9=pinetree9.addItem("	Leading Discussions ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree9.addItem("	Starting and ending          ", branch9) //Add this item to branch3
    pinetree9.addItem("	Consensus          ", branch9) //Add this item to branch3
    pinetree9.addItem("	Interrupting         ", branch9) //Add this item to branch3
    pinetree9.addItem("	Moving Discussion           ", branch9 ) //Add this item to branch3
    pinetree9.addItem("	Agree/Disagree         ", branch9) //Add this item to branch3
	pinetree9.treetop.draw();

      var pinetree10=new jktreeview("tree10")
var branch10=pinetree10.addItem("Business Travel ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree10.addItem("		Planning a Business Trip        ", branch10, "../planningBusinessTrip/planningBusinessTripLearn.gsp?careerLab2") //Add this item to branch3
  pinetree10.treetop.draw();

      var pinetree11=new jktreeview("tree11")
var branch11=pinetree11.addItem("	Cross Cultural Communication   ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree11.addItem("	test      ", branch11) //Add this item to branch3
  pinetree11.treetop.draw();   

  }  else if(URL_param=="conversationalEnglish"){

      var pinetree1=new jktreeview("tree1")

var branch1=pinetree1.addItem("	Life Skills") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree1.addItem("	Healthy Eating          ", branch1, "../healthyEating/healthyEatingLearn.gsp?pg=conversationalEnglish") //Add this item to branch3
	pinetree1.treetop.draw();


var pinetree2=new jktreeview("tree2")
var branch2=pinetree2.addItem("	Social Issues  ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree2.addItem("	test      ", branch2) //Add this item to branch3
      	pinetree2.treetop.draw();

var pinetree3=new jktreeview("tree3")
var branch3=pinetree3.addItem("	Workplace Conversations") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree3.addItem("	test        ", branch3) //Add this item to branch3
	     pinetree3.treetop.draw();

var pinetree4=new jktreeview("tree4")
var branch4=pinetree4.addItem("Our Environment ") //A TREE BRANCH WITH NO URL FOR ITSELF
	pinetree4.addItem("	Compare your Pronunciation         ", branch4) //Add this item to branch3
	pinetree4.addItem("	Pronunciation Tips         ", branch4) //Add this item to branch3
     pinetree4.treetop.draw();

 }
</script>

</body>
 </html>
