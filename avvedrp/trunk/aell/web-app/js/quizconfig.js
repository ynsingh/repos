var ns="0123456789"
var ls="abcd";
var flg=0;
var astr="";
var nr=0;
   var ctr=0;
var ad=0;
var ack=new Array();
 var rootUrl=getRootUrl();
function setNumberOfQuestions(num){

    nr=num;

    addToArray(nr);
}

function addToArray(numOfQuestions){
for (var i=0;i<numOfQuestions;i++){
 ack[i]=(numOfQuestions*4)+1;
 }
}
function lightIt(){

    document.images[flg+ad].src=rootUrl+"nr/rbl.gif";


 }
function dimIt(){
 if (ack[Math.floor(flg/4)]!=flg){
  document.images[flg+ad].src=rootUrl+"nr/rbd.gif";
  }
 }
function dumpIt(){

 if (ack[Math.floor(flg/4)]==flg){
  document.images[ack[Math.floor(flg/4)]+ad].src=rootUrl+"nr/rbd.gif";
  ack[Math.floor(flg/4)]=(nr*4)+1;
  }
 else{
  if (ack[Math.floor(flg/4)]!=flg&&ack[Math.floor(flg/4)]!=(nr*4)+1){
   document.images[ack[Math.floor(flg/4)]+ad].src=rootUrl+"nr/rbd.gif";
   }
  document.images[flg+ad].src=rootUrl+"nr/rbl.gif";
  ack[Math.floor(flg/4)]=flg;
  }

 }
function ckSco(){
// document.images[ad+(nr*4)].src="nr/cwf2.gif";
 //setTimeout('document.images[ad+(nr*4)].src="nr/cwf0.gif"',1000);

 for (var i=0;i<nr;i++){
  u=cor.charAt(i);
  v=ls.indexOf(u)+(i*4);
  document.images[ad+v].src=rootUrl+"nr/gbl.gif";
  }
 sco=psco;
 for (var i=0;i<nr;i++){
  if (ack[i]==(nr*4)+1){
   sco-=noans;
   }
  else{
   u=cor.charAt(i);
   v=ls.indexOf(u)+(i*4);
   sco=(v==ack[i]?sco:sco-wrans);
   }
  }
 sco=(sco<0?0:sco); // prevent minus score
 scos=""+sco;
 while (scos.length<3){
  scos="0"+scos;
  }
 document.images[ad+(nr*4)+1].src="nr/yts.gif";
 ctr=ad+(nr*4)+2;
 for (var i=0;i<3;i++){
  nj=ns.indexOf(scos.charAt(i));
 // document.images[ctr].src="nr/"+nj+".gif";
  ctr++;
  }
 document.getElementById("ans").value= "Your Score:"+scos;

}


function litbut(){
 document.images[ad+(nr*4)].src=rootUrl+"nr/cwf1.gif";
 }


function dimbut(){
 document.images[ad+(nr*4)].src=rootUrl+"nr/cwf0.gif";
 }
 ctr=0;

function srand() {
 today=new Date();
 rand=today.getTime();
 picker=""+rand
 picker=picker.charAt((picker.length - 4));
 rec=eval(picker);
 }
/* This little routine is simply used to count the
   number of images you may place on your web page
   prior to the radio buttons.  Just makes the routine
   independent of how you lay your page out. */
function getImgAdd(){
 for (var i=0;i<20;i++){
  if (document.images[i].src.indexOf("rbd.gif")>-1){
   ad=i;

   i=20;
   }
  }
 }






function  displayQuizGrammar(){

var a='<TABLE BORDER=0 CELLPADDING=5  ALIGN=CENTER' 
+' CELLSPACING=0 WIDTH=1000><TR><TD COLSPAN=2 '
+'ALIGN=CENTER><FONT SIZE=4 FACE="helvetica,a'
+'rial,geneva" COLOR=BLUE><B>'+ttl+'</B></FON'
+'T></TD></TR>';
for (var i=0;i<nr;i++){
 a+='<TR><TD COLSPAN=2 VALIGN=TOP  ALIGN=LEFT>'
 +' <FONT SIZE=4 FACE="helvetica,aria'
 +'l,geneva">'+ques[i]+'</FONT></TD></TR>'
 for (var j=0;j<4;j++){
 a+='<TR><TD><A HREF="mctest.htm'
  +'" onMouseOver=" flg='+((i*4)+j)+';lightIt('
  +');return true" onMouseOut="flg='+((i*4)+j)
  +';dimIt();return true;" onClick="flg='
  +((i*4)+j)+';dumpIt();return false;"><IMG S'
  +'RC=\"'+rootUrl+ 'nr/rbd.gif\"  WIDTH=15 HEIGHT=15 BORDER'
  +'=0></A></TD><TD ALIGN=LEFT><FONT SIZE=4 FACE='
  +'"helvetica,arial,geneva">'+ans[(i*4)+j]
  +'</FONT></TD></TR>';
  }
 }
 a+='</TABLE>';
 return(a) ;


// End Hiding -->
function yourScore(){
return scos;
}
}

function  displayActionElementsGrammar (){
 var elements='<TABLE BORDER=0><TR><TD WIDTH=500px ALIGN=CENTER>'
+'<A HREF="mctest.htm" onClick="ckSco(); return false;" onMouseOver="litbut(); return true;"  onMouseOut="dimbut();return true;">'
+'<img src=\"'+rootUrl+ 'nr/cwf0.gif\"  WIDTH=100 HEIGHT=30 BORDER=0 NAME="but" ALIGN="CENTER" ></A>'
+'&nbsp&nbsp&nbsp&nbsp<input  name="ans" id="ans" type="text"   style=" border:0  "  readonly="readonly"   size="20" maxlength="20"  />'
+'</TD></TR><TR><TD></TD></TR></TABLE>'
  return elements;
   //return  elements;
}


// End Hiding -->
function yourScore(){
return scos;
}
