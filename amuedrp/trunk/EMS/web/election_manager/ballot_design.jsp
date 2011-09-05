<%--
    Document   : ballot_design
    Created on : Jun 13, 2011, 12:31:44 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/election_manager/login.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.util.*,java.io.*,java.net.*" %>
<link type="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type"  content="text/html; charset=UTF-8">
        <title>Ballot Design</title>
        <style type="stylesheet">
             #position0{
            border-left-top-radius: "15px";
}

        </style>
        <script type="text/javascript" language="javascript">
            function clearme()
{


    document.getElementById("position_id").value = "";
    document.getElementById("position").value = "";
    document.getElementById("candidate").value = "";

    return true;
}
function changeValue(value,index)
{
/*
    var len = document.getElementById("form1").candidate.length;
    var text;
    var indexval = index.parentNode.id;
   // alert(len);
    if(len!=undefined){

    len = len-1;
   // alert(len);
    //text = document.getElementById("candidate2").candidate[len-1].value;
    //alert("Hmmm:"+document.getElementsById("form1").indexval.innerHTML);
    index.parentNode.innerHTML = "<input id=candidate"+ indexval +" style='border: transparent; background-color: gray'  type='text' value='"+value+ "' onblur='changeValue(this.value,this)' />";

   // alert("its working");
    }else
        {
           // alert(value);
          //text = candidate2.firstChild.value;
    //alert(text);
   index.parentNode.innerHTML = "<input id=candidate"+ indexval +" style='border: transparent; background-color: gray'  type='text' value='"+value+ "' onblur='changeValue(this.value,this)' />";
        }*/
}
function changeProperty(id)
{
    id.removeAttribute("readonly");

}

        </script>
         <script type="text/javascript" language="javascript">
           var i=0;
function create(ii,current,index)
{
    var textval = index.value;
    var id1 = "position_name0"+current;
    var id2 = "numberofchoice0"+current;
    if(document.getElementById(id1).value !=undefined && document.getElementById(id1).value != "" && document.getElementById(id2).value !=undefined && document.getElementById(id2).value != "")
    {


    if(textval=="Add Candidate")
        {


            index.value = "Add more Candidate";
            //var nod = "candi" + i;
           // alert(index);
           var id = "candidate0"+current;
            var el = document.getElementById(id);
            el.style.display = "block";
            
            search(ii,current);

        }




        i=ii+1;
     var divtag = document.createElement("div");
     divtag.id = current+''+ii;
     divtag.style.position = "relative";
     divtag.style.backgroundColor = "#D8D8D8";
     divtag.style.border = "3px solid cyan";
     divtag.style.display = "block";
     divtag.style.marginLeft = "30px";
     divtag.style.width = "475px";
     divtag.style.marginTop = "5px";
     divtag.style.marginBottom = "5px";
     divtag.style.marginLeft = "55px";
     divtag.style.overflow = "hidden";

     divtag.innerHTML = ii+'&nbsp;Candidate Name * <input type="text"   value="Candidate Name" Id="candidate_name'+ current +''+ii +'" size="40px"/>&nbsp;&nbsp;<input type="button" value="Save" onclick="search1('+current+','+ii+');"/>' ;
     //divtagparent.innerHTML = divtag;
    alert("yes its working");
    //alert();
    //var mytext = document.createElement('<div id="'+ i +'"><input name="BallotActionForm" property="candidate" style="border: transparent; background-color: brown" onblur="changeValue(this.value,this);" value="" styleId="candidate" size="40px"/></div>');
    var id = "candidate0"+current;
    document.getElementById(id).appendChild(divtag);
    //document.getElementById("candidate").innerHTML = document.getElementById("candidate").innerHTML + '<div id="'+ i +'"><i name="BallotActionForm" property="candidate" style="border: transparent; background-color: brown" onblur="changeValue(this.value,this);" value="Candidate Name" styleId="candidate" size="40px"/></div>';
   //alert("yes its working");
   //document.getElementById("form1").candidate[i].value = "Candidate Name";
    alert("yes its working");
    var idadd = "add0" + current;
    var iii = ii +1;
    document.getElementById(idadd).attributes.onclick.value = "create("+iii+","+current+",this);";

    }else{
        if(document.getElementById(id1).value != undefined && document.getElementById(id1).value !="")
            {
              alert("please Fill No of Choice First!");
        document.getElementById(id2).focus();
        return false;
            }
        alert("please Fill Position Name First!");
        document.getElementById(id1).focus();
        return false;
    }
}

 var j=0;
function createposition()
{
i=0;
j=j+1;
var divtag = document.createElement("div");
     divtag.id = "position"+j;
     divtag.style.backgroundColor = "#E0F8F7";
     divtag.style.border = "solid 5px #0B3B24";
     divtag.style.borderTopLeftRadius = "10px";
     divtag.style.width = "600px";
     divtag.style.align = "center";
     divtag.style.marginLeft = "350px";
     divtag.innerHTML ='Position Name *<br><input type="text" Id="position_name'+i+''+j +'" size="40px"/><br>Number of choice *<br><input type="text" Id="numberofchoice'+i+''+ j +'" size="40px"/><br><br><div id="candidate'+i+''+j+'" style="position: relative;background-color: #E0F8F7; border: 3px solid #0B3B24;border-top-left-radius:10px;display: none;width: 595px"></div><input type="button" name="add candidate" style="" id="add'+i+''+j+'" onclick="create('+i+','+j+',this);" value="Add Candidate" size="50px"><br>';
    // var tagbr = document.createElement("html");

     //document.getElementById("position").appendChild(tagbr);
document.getElementById("position").appendChild(divtag);
       // current.parentNode.appendChild('<div id="position1" style="position: relative;">Position Id *<br><input name="BallotActionForm" property="position_id" styleId="position_id" size="40px"/><br><br>Position Name *<br><input name="BallotActionForm" property="position" styleId="position" size="40px"/><br><br><div id="candidate"><div id="0">Candidate Name *<br><input name="BallotActionForm" property="candidate" styleId="candidate" size="40px"/><br><br>Number of choice *<br><t name="BallotActionForm" property="numberofchoice" styleId="numberofchoice" size="40px"/><br><br></div><input type="button" name="add candidate" style="border: transparent" onclick="create(this);" value="Add More Candidate" size="50px"></div></div>');

}


function newXMLHttpRequest() {
var xmlreq = false;
// Create XMLHttpRequest object in non-Microsoft browsers
if (window.XMLHttpRequest) {
xmlreq = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
// Try to create XMLHttpRequest in later versions
// of Internet Explorer
xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e1) {
// Failed to create required ActiveXObject
try {
// Try version supported by older versions
// of Internet Explorer
xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
} catch (e2) {
// Unable to create an XMLHttpRequest by any means
xmlreq = false;
}
}
}
return xmlreq;
}
/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
// Return an anonymous function that listens to the XMLHttpRequest instance
return function () {
// If the request's status is "complete"
if (req.readyState == 4) {
// Check that we received a successful response from the server
if (req.status == 200) {
// Pass the XML payload of the response to the handler function.
responseXmlHandler(req.responseXML);
} else {
// An HTTP problem has occurred
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}
function search(index,current) {
    //alert("index="+index+" current="+current);
    var position = "position_name0"+current;
    var numberofchoice = "numberofchoice0"+current;
    var position_name = document.getElementById(position).value;
    var noofchoice = document.getElementById(numberofchoice).value;
    position_name = position_name.replace(/^\s*|\s*$/g,"");
    noofchoice = noofchoice.replace(/^\s*|\s*$/g,"");
if (position_name.length >= 1)
{
if(position_name!="" && position_name!=null && noofchoice!="" && noofchoice!=null)
{
    var req = newXMLHttpRequest();


req.onreadystatechange = getReadyStateHandler(req, update1);

req.open("POST","<%=request.getContextPath()%>/AddPosition.do?setPosition="+position_name+"&setChoice="+noofchoice, true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
var idPos = "position"+current;

document.getElementById(idPos).style.backgroundColor = "#D8CEF6";
 document.getElementById(idPos).style.border = "solid 5px #F2F5A9";

return true;
}
return false;
}

}

function search1(index,current) {

    var position = "position_name0"+index;
    var numberofchoice = "candidate_name"+index+""+current;
    var position_name = document.getElementById(position).value;
    var noofchoice = document.getElementById(numberofchoice).value;
    position_name = position_name.replace(/^\s*|\s*$/g,"");
    noofchoice = noofchoice.replace(/^\s*|\s*$/g,"");
    //alert("in Search 1 position="+position_name+" Candidate="+noofchoice);
if (position_name.length >= 1)
{
if(position_name!="" && position_name!=null && noofchoice!="" && noofchoice!=null){
var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update1);

req.open("POST","<%=request.getContextPath()%>/AddCandidate.do?setPosition="+position_name+"&setCandidate="+noofchoice+"&setId="+current, true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

req.send();
var idcan = index+""+current;
document.getElementById(idcan).style.border = "3px solid #F2F5A9";
document.getElementById(idcan).style.backgroundColor = "#F2F2F2";
var cand = 'candidate'+current+''+index;
document.getElementById(cand).style.backgroundColor = "#D8CEF6";
 document.getElementById(cand).style.border = "solid 5px #F2F5A9";
}
return true;
}

}
function update1(cartXML)
{
alert("call");

var em = cartXML.getElementsByTagName("email_ids")[0];
var em1 = em.getElementsByTagName("message");
//var em = cartXML.firstChild.value;
for(i=0;i<em1.length;i++)
    {
alert(em1[i].firstChild.nodeValue);

    }
}
function searchPositions() {
    //alert("index="+index+" current="+current);
    var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, updatePositions);

req.open("POST","<%=request.getContextPath()%>/getPosition.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}

function updatePositions(cartXML)
{


var em = cartXML.getElementsByTagName("positions")[0];
var em1 = em.getElementsByTagName("position");
//var em = cartXML.firstChild.value;
for(iii=0;iii<em1.length;iii++)
    {
        //position block creation
                var divtag = document.createElement("div");
                divtag.id = "position"+iii;
                divtag.style.backgroundColor = "#D8CEF6";
                divtag.style.border = "solid 5px #F2F5A9";
                divtag.style.borderTopLeftRadius = "10px";
                divtag.style.width = "600px";
                divtag.style.align = "center";
                
                divtag.style.marginTop = "5px";
                divtag.style.marginLeft = "350px";
                divtag.innerHTML ='Position Name *<br><input type="text" Id="position_name0'+iii +'" size="40px"/><br>Number of choice *<br><input type="text" Id="numberofchoice0'+ iii +'" size="40px"/><br><br><div id="candidate0'+iii+'" style="position: relative;border-top-left-radius: 15px;border-top-right-radius: 15px;background-color: #D8CEF6; border: 3px solid #F2F5A9;display: block;width: 595px"></div><input type="button" name="add candidate" style="" id="add0'+iii+'" onclick="create(0,'+iii+',this);" value="Add Candidate" size="50px"><br>';
                document.getElementById("position").appendChild(divtag);
        //end of block
var positionname1 = em1[iii].getElementsByTagName("positionname");
var positionname = positionname1[0].firstChild.nodeValue;
var noofchoice1 = em1[iii].getElementsByTagName("noofchoice");
var noofchoice = noofchoice1[0].firstChild.nodeValue;
var instruct = em1[iii].getElementsByTagName("instruction");
var instrucT = instruct[0].firstChild.nodeValue;
var instruction = "instruct0"+iii;
document.getElementById(instruction).value = instrucT;
var posiId = "position_name0"+iii;
var nochoice = "numberofchoice0"+iii;

document.getElementById(posiId).value = positionname;
document.getElementById(nochoice).value = noofchoice;

var ca = em1[iii].getElementsByTagName("candidate");
for(jj=0;jj<ca.length;jj++)
    {
        var candidatename1 = ca[jj].getElementsByTagName("candidatename");
        var candidatename;
            if(candidatename1[0].firstChild!=null) candidatename = candidatename1[0].firstChild.nodeValue;
            else candidatename = "";
        var candidateid1 = ca[jj].getElementsByTagName("candidateid");
        var candidateid = candidateid1[0].firstChild.nodeValue;
        //creating candidate block
            var divtag = document.createElement("div");
            divtag.id = iii+''+jj;
            divtag.style.position = "relative";
            divtag.style.backgroundColor = "#F2F2F2";
            divtag.style.border = "3px solid #F2F5A9";
            divtag.style.display = "block";
            divtag.style.width = "475px";
            divtag.style.marginTop = "5px";
            divtag.style.marginLeft = "55px";
            divtag.style.overflow = "hidden";
            if(jj+1==ca.length) divtag.style.marginBottom = "5px";
            var id = "candidate0"+iii;
            divtag.innerHTML = candidateid + '    Candidate Name * <input type="text"   value="'+ candidatename +'" Id="candidate_name'+ iii +''+jj +'" size="40px"/>&nbsp;&nbsp;<input type="button" value="Save" onclick="search1('+iii+','+jj+');"/>' ;
            document.getElementById(id).appendChild(divtag);
         //end of creation
i=jj;
    }
   var idadd = "add0"+iii;
document.getElementById(idadd).attributes.onclick.value = "create("+jj+","+iii+",this)";
//alert("create("+jj+","+iii+",this);");
//alert(document.getElementById(idadd).attributes.onclick.value);
}

j=iii-1;
if(j<0)j=0;

alert(i+" "+j);
}

function previewBallot() {
   //alert("index="+index+" current="+current);
   // alert(document.getElementById("ballot").style.display);
  if(document.getElementById("ballot").style.display=="inline")
        {
            <%--document.getElementById("ballot").style.display="none";
            //document.getElementById("position").style.display="block";
           // document.getElementById("add Position").style.display = "block";
            document.getElementById("previewtext").textContent = "Preview Ballot";
            return false;--%>
                        window.location = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/manageElection.do";
        }
    var req = newXMLHttpRequest();
document.getElementById("ballot").style.display="block";
req.onreadystatechange = getReadyStateHandler(req, designBallot);

                    req.open("POST","<%=request.getContextPath()%>/getPosition.do?getElectionId=<%=request.getParameter("electionId")%>", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}
function designBallot(cartXML)
{
var htm="";
document.getElementById("ballot").innerHTML="";
var em = cartXML.getElementsByTagName("positions")[0];
var em1 = em.getElementsByTagName("position");
//var em = cartXML.firstChild.value;
for(iii=0;iii<em1.length;iii++)
    {
        //position block creation
                var divtag = document.createElement("div");
                divtag.id = "position"+iii;
                divtag.style.backgroundColor = "#D8CEF6";
                divtag.style.border = "solid 5px #F2F5A9";
                divtag.style.borderTopLeftRadius = "15px";
                divtag.style.borderTopRightRadius = "15px";
                divtag.style.borderBottomLeftRadius = "15px";
                divtag.style.borderBottomRightRadius = "15px";
                divtag.style.width = "590px";
                divtag.style.align = "center";
                divtag.style.marginTop = "5px";
                divtag.style.height = document.height;
                //divtag.innerHTML ='Position Name *<br><input type="text" Id="position_name0'+iii +'" size="40px"/><br>Number of choice *<br><input type="text" Id="numberofchoice0'+ iii +'" size="40px"/><br><br><div id="candidate0'+iii+'" style="position: relative;background-color: #D8CEF6; border: 3px solid #F2F5A9;display: block;width: 595px"></div><input type="button" name="add candidate" style="" id="add0'+iii+'" onclick="create(0,'+iii+',this);" value="Add Candidate" size="50px"><br>';
                //document.getElementById("position").appendChild(divtag);
        //end of block
var positionname1 = em1[iii].getElementsByTagName("positionname");
var positionname = positionname1[0].firstChild.nodeValue;
var noofchoice1 = em1[iii].getElementsByTagName("noofchoice");
var noofchoice = noofchoice1[0].firstChild.nodeValue;
var instruct = em1[iii].getElementsByTagName("instruction");
var instrucT = instruct[0].firstChild.nodeValue;
var instruct1;
var instruct2;
if(instrucT!=undefined)
    if(instrucT.lastIndexOf(noofchoice)!=-1)
        {instruct1 = instrucT.substr(0, instrucT.lastIndexOf(noofchoice)-1);
        instruct2 = instrucT.substr(instrucT.lastIndexOf(noofchoice)+1,instrucT.length);}
htm = '<div class="building_block" >Position: <strong>'+positionname+'</strong><br><strong >'+ instruct1 +' <span style="color: red">'+noofchoice+'</span>'+ instruct2 +'</strong>';
<%--if(noofchoice>1) htm=htm + ' candidates';
else htm=htm + ' candidate';--%>
htm = htm +'<table class="ballot"><tbody><tr><th style="text-align: left;">Candidate Name</th><th>Selection</th></tr>';

var ca = em1[iii].getElementsByTagName("candidate");
for(jj=0;jj<ca.length;jj++)
    {
        var candidatename1 = ca[jj].getElementsByTagName("candidatename");
        var candidatename;
            if(candidatename1[0].firstChild!=null) candidatename = candidatename1[0].firstChild.nodeValue;
            else candidatename = "";
       htm = htm +'<tr><td style="text-align: left;"><label for="entry'+iii+'">'+candidatename+'</label></td>';
if(noofchoice>1)
       {
               htm = htm +'<td><input type="checkbox" value="'+jj+'" onclick="checkCandidateLimit('+iii+')" name="entry'+iii+'" id="entry'+iii+'" ></td></tr>';
       }
       else
           {
               htm = htm +'<td><input type="radio" value="'+jj+'"  name="entry'+iii+'" id="entry'+iii+'" ></td></tr>';
           }

    }
  htm = htm + '</tbody></table></div>';
//alert("create("+jj+","+iii+",this);");
//alert(document.getElementById(idadd).attributes.onclick.value);
divtag.innerHTML =htm;
document.getElementById("ballot").appendChild(divtag);
//document.getElementById("position").style.display = "none";
//document.getElementById("add Position").style.display = "none";
document.getElementById("ballot").style.display="inline";
document.getElementById("previewtext").textContent = "Close Preview";
}


}

function block()
            {

                         <%if(request.getParameter("electionId")==null){%>
                   var main12 = document.getElementById("electionblock");
                main12.style.display = "block";
                return false;
                <%}else{%>
                    document.getElementById("ballot").style.display="block";
                    document.getElementById("electionblock").style.display="none";
                previewBallot();<%}%>
            }
            </script>
    </head>
    <body onload="previewBallot();">

        
            
<%--<div id="electionblock" style="margin-left: 300px; display: none; border: solid aqua 5px;background-color: cyan;width:500px;height: 50px" >
<html:form method="post" action="/ballotdesign" styleId="form1">
              Choose Election<br>

              <html:select property="election" name="BallotActionForm" styleId="election" >
              <html:select property="election" styleId="election">
              <html:options collection="electionList" property="id.electionId" labelProperty="electionName" />
              </html:select>
              </html:select>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="submit" class="btn" id="Button1" name="go" value="Go---" />
</html:form>
  </div>--%>
              <div id="ballotpreview" style="display: block;overflow: hidden; margin-left: 350px; text-align: center; background-color: gray; width: 600px"><label onclick="previewBallot()" id="previewtext">Preview Ballot</label><br/><div id="ballot" style="display: block"></div>
              </div>
<!--<div id="position" align="left" class="position" style="left: 250px;">

                <div id="position" style="position: relative;background-color: yellow; border: 5px solid;"><br>
                    Position Name *
                    <br><input type="text" Id="position_name" size="40px"/>
                    <br>
                   Number of choice *
                   <br><input type="text"  Id="numberofchoice" size="40px"/>
                        <br>
                    <br><br>
                    <div id="candidate" style="position: relative;background-color: blue; border: 3px solid;display: none;width: 450px">
                        <div id="0" style="width: 550px">
                            Candidate Name *  <input type="text" Id="candidate_name" value="Candidate Name" size="40px"/>&nbsp;&nbsp;<input type="button" value="Save" onclick="search1(0,0);"/>
                        <br></div>
                    </div>
                    <input type="button" name="add candidate"  style="border: transparent" onclick="create(0,0,this);" value="Add Candidate" size="50px">
                </div>
                        <br/>
        </div>
              <input type="button" id="add Position" name="add Position" style="margin-left: 350px;" value="Add New Position" size="50px" onclick="createposition();">

                        <br>
-->

        
    </body>
</html>
