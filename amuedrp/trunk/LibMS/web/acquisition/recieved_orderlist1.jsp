<%--
    Document   : approvallist
    Created on : May 12, 2011, 4:00:20 PM
    Author     : faraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.Acquisition.ApprovalList" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript">

    function get_check_value(index)
    {

  //  alert("hello check value");
var c_value;
var textvalue=index;
var check_index=index+1;
if(document.getElementById("f1").check.length!=undefined){
if(document.getElementById("f1").check.disabled==false)
    {
    document.getElementById("f1").check.disabled=true;
    document.getElementById("f1").check1.disabled=false;
    }
else
    {document.getElementById("f1").check[index].disabled=false;
        //document.getElementById("div"+check_index).innerHTML ="";
       //  top.document.getElementById("list1").value ="";
       // return false;
    }
//alert("in cc_value1");
//if(textvalue>0)

//for (var i=0; i < document.getElementById("f").check.length; i++)
 var cc_value = false;
 if(document.getElementById("f1").check1.length>1)
{
    cc_value = document.getElementById("f1").check1[index].checked;
}
else
    cc_value = document.getElementById("f1").check1.checked;

       if (cc_value)
            {
            c_value = top.document.getElementById("f").list.value;

            if(c_value==null || c_value=="undefined")
                {
                if(document.getElementById("f1").check1.length>1)
                {
            //        alert("in cvalue is null");
                   c_value =document.getElementById("f1").check1[index].value+";";
                }
                else
                    {
                        c_value =document.getElementById("f1").check1.value+";";
                    }
                }
           else
           {
               if(document.getElementById("f1").check1.length>1)
                {
              c_value = c_value + document.getElementById("f1").check1[index].value +";";
                }
                else
                    {
                        c_value = c_value + document.getElementById("f1").check1.value +";";
                    }
           }

           document.getElementById("div"+check_index).innerHTML ="<input type='text' id="+index+" onchange='getValue1("+ index +")'/>";
           //textvalue=index;
 if(document.getElementById("f1").check1.length>1)
{
    cc_value = document.getElementById("f1").check1[index].unchecked;
}
else
    cc_value = document.getElementById("f1").check1.unchecked;

  // document.getElementById("f1").check1[index].unchecked;
          }
       else
           {
          //   alert("cValue="+index);
           // document.getElementById("div"+i).innerHTML ="";
            var checkval = document.getElementById(index).value + ";";
         //alert(checkval);
        var listval = top.document.getElementById("f").list1.value;
        //alert(listval);
        var ccc_value = listval.replace(checkval, "");
        checkval = document.getElementById("f1").check1[index].value + ";";
        listval = top.document.getElementById("f").list.value;
        c_value = listval.replace(checkval,"");
        top.document.getElementById("f").list1.value = ccc_value;
       // alert("Changing list1 value as "+ ccc_value);
        document.getElementById("div"+check_index).innerHTML ="";
//getValue1();

           }

}else{
    //alert("length="+document.getElementById("f").check.length);
    if(document.getElementById("f1").check.disabled==false){
    document.getElementById("f1").check.disabled=true;

}
else
    {
         //getValue1();
        document.getElementById("f1").check.disabled=false;

        var checkval = document.getElementById(index).value + ";";
        var listval = top.document.getElementById("f").list1.value;
        listval = listval.replace(checkval, "");
    //  alert("jj"+listval);
      var checkvalue = document.getElementById("f1").check.value + ";";
      top.document.getElementById("f").list.value= top.document.getElementById("f").list.value.replace(checkvalue, "");
        top.document.getElementById("f").list3.value=top.document.getElementById("f").list.value;
        top.document.getElementById("f").list1.value=listval;
       document.getElementById("div1").innerHTML ="";
        return false;
    }

//if(textvalue>0)

//for (var i=0; i < document.getElementById("f").check.length; i++)
 var cc_value = document.getElementById("f1").check1.checked;

       if (cc_value)
            {
//alert("in cc_value");
            var cval = top.document.getElementById("f").list.value;
            if(cval!="" || cval!="undefined"||cval!=" ")
                c_value = cval + document.getElementById("f1").check1.value + ";";
            else
                c_value =document.getElementById("f1").check1.value + ";";


           document.getElementById("div"+check_index).innerHTML ="<input type='text' id="+index+" onchange='getValue1("+ index +")'/>";
           //textvalue=index;
   document.getElementById("f1").check1.unchecked;
}
else{
    var checkval = document.getElementById("f1").check.value + ";";
         //alert(checkval);
        var listval = top.document.getElementById("f").list.value;
        //alert(listval);
        c_value = listval.replace(checkval, "");

}
}
   top.document.getElementById("f").list.value=c_value;
   top.document.getElementById("f").list3.value=top.document.getElementById("f").list.value;

}

function getValue1(index)
{
 // alert("hello check value1");
  // setValues();
  var v = document.getElementById(index).value;
  //alert(v);
   var vlength = document.getElementById("f1").check.length;
   // alert("value of text box="+v);

    var valcheck="";

    if(vlength!=undefined)
        {//alert("Its not here");
        valcheck = document.getElementById("f1").check[index].value;}
    else{

//alert("Its here");
            valcheck = document.getElementById("f1").check.value;
}
   var valcheck1 = valcheck.split(",");

   //alert(Math.max(valcheck1[1],v));

        if(Math.max(valcheck1[1],v)!=valcheck1[1])
{
    alert("Entered Value is greater than actual value");
    document.getElementById(index).value = 0;
return false;
}
    var val = top.document.getElementById("f").list1.value;
//alert("before"+ val);
    if(val!="undefined"||val!=""||val!=" ")
         val+=v+";";
     else
       val=v;
   //  alert(val);
     var checkedvalue = document.getElementById("f1").check1.checked
     if(vlength!=undefined)
         {
     if(document.getElementById("f1").check1[index].checked==true)
        top.document.getElementById("f").list1.value = val;
    else
        {
            var val1 = document.getElementById(index).value+";";
        var listval = top.document.getElementById("f").list1.value;
        listval = listval.replace(val1,"");
        top.document.getElementById("f").list1.value = listval;
        }
         }
         else{
             if(document.getElementById("f1").check1.checked==true)
        {
          var val1 = val;
       // var listval = top.document.getElementById("f").list1.value;
        //listval = listval.replace(val1,"");

        top.document.getElementById("f").list1.value = val1;

        //top.document.getElementById("f").list1.value = val;
        }
    else
        {
            top.document.getElementById("f").list1.value = "";
        }
         }

}

function get_check_value3()
{
  //  alert(document.getElementById("f1").check1.length);
var c  ;

if(document.getElementById("f1").check1.length>1){
        for (var i=0; i < document.getElementById("f1").check1.length; i++)
         {


                if (document.getElementById("f1").check1[i].checked)
      {

           if(c==null)
            c =document.getElementById("f1").check1[i].value;
           else
           {
              c = c +";"+ document.getElementById("f1").check1[i].value;
           }
       }
   }
}else{
    if(document.getElementById("check1").checked)
        c =document.getElementById("check1").value
    else
        c="";
}

   top.document.getElementById("f").list3.value=c;

}

function set_cookie(t){
x=t;
//alert("Cookie"+x);
        if (document.cookie && document.cookie != ""){
        var old_cookie = document.cookie;
        var product = ("c="+x+";");
        document.cookie = old_cookie + escape(product);
        }//ends IF

        else{
        var product = ("c=")+x+";";
        document.cookie = "products=" + escape(product);}



    }//ends function

    function kill_set_cookie(){
    var kill_date = new Date("June 18, 2011");
    var kill_string = "products=<br />;expires=" + kill_date;
    document.cookie = kill_string;
    }//ends function




var haystackText = "";
function findMyText(str,needle, replacement) {
     if (haystackText.length == 0) {
          haystackText = str;
     }

     var match = new RegExp(needle, "ig");
     var replaced = "";
    // if (replacement.length > 0) {
          replaced = haystackText.replace(match, replacement);
     //}
    // else {
          //var boldText = "<div style=\"background-color: yellow; display: inline; font-weight: bold;\">" + needle + "</div>";
       //   var boldText=needle;
       //   replaced = haystackText.replace(match, boldText);
     //}
return replaced;
}

function get_check_value4(ii)
{
var c;
if(top.document.getElementById("f").list2.value !=null)
c = top.document.getElementById("f").list2.value ;


//alert(c+"  "+ii);

//alert("Fully Selection");


//alert("length="+document.getElementById("f1").check.length);
var t=document.getElementById("f1").check.length;
if(document.getElementById("f1").check.length>1){
  //  alert("Multiple="+document.getElementById("f1").check[ii].disabled);

if(document.getElementById("f1").check1[ii].disabled==false)
   {
       document.getElementById("f1").check1[ii].disabled=true;

   }
else {
    document.getElementById("f1").check1[ii].disabled=false;
     }
//alert(ii);
       // for (var i=0; i < document.getElementById("f1").check.length; i++)
       //  {



           if(c=="" && document.getElementById("f1").check[ii].checked==true)
           {
           c =document.getElementById("f1").check[ii].value+";";

           // alert("First Selction"+c);



            }
           else if(c!="" && document.getElementById("f1").check[ii].checked==true)
           {
               var t=document.getElementById("f1").check[ii].value;
               var myRegExp = "/"+t+"/";
            //  alert(myRegExp);
                var matchPos1 = c.search(myRegExp);

                if(matchPos1 == -1)
                    c = c+ document.getElementById("f1").check[ii].value+";";



             //   alert("After Selction"+c);
//



           }
         //  alert("With Selection"+document.getElementById("f1").check[ii].value);
           if(document.getElementById("f1").check[ii].checked==false)
           {

           if(c!="")
           {
                var str = new String();
                str=c;
            //   alert("Deselected"+str);
               var d=document.getElementById("f1").check[ii].value+";";
             // var t = d;
              //  alert(t);
              // str.replace(t, "");
             //  alert("After"+str);
             //  c=str;
             c=  findMyText(str,d, "") ;
           }
           }

         //  alert("Deselected"+c);







  // }




}





if(t==undefined)
    {
       // alert("single selection");



        if(document.getElementById("f1").check1.disabled==false)
   {
       document.getElementById("check1").disabled=true;
   }
else {
    document.getElementById("check1").disabled=false;
}
//alert("single  :"+document.getElementById("check").checked);



       if (document.getElementById("check").checked==true)
      {
         //  alert("value="+document.getElementById("check").value);
         //  alert("kk"+c);
           if(c=="")
           {
           c=document.getElementById("check").value+";";
           }
            else
           {
                 var t=document.getElementById("check").value+";";
               var myRegExp = "/"+t+"/";

                var matchPos1 = c.search(myRegExp);

                if(matchPos1 == -1)
                    c = c+document.getElementById("check").value+";";






            }

       }
        if(document.getElementById("check").checked==false)
           {

           if(c!="")
           {
                var str = new String();
                str=c;
             //  alert("Deselected"+str);
               var d=document.getElementById("check").value+";";
             // var t = d;
              //  alert(t);
              // str.replace(t, "");
             //  alert("After"+str);
             //  c=str;
             c=  findMyText(str,d, "") ;
           }
           }

        //   alert("Deselected"+c);

   }

   //alert("hey");
   //alert(top.document.getElementById("f").list2.value);
 top.document.getElementById("f").list2.value=c;
}

function GetCookie (name) {
  var arg = name + "=";
  var alen = arg.length;
  var clen = document.cookie.length;
  var i = 0;
  while (i < clen) {
    var j = i + alen;
    if (document.cookie.substring(i, j) == arg) {
      return getCookieVal (j);
      }
    i = document.cookie.indexOf(" ", i) + 1;
    if (i == 0) break;
    }
  return null;
  }
function get_check_value5()
{
//alert("hello 5");
var c  ;
if(document.getElementById("f1").check1.length>1){
        for (var i=0; i < document.getElementById("f1").check1.length; i++)
         {


      if (document.getElementById("f1").check1[i].checked)
      {

           if(c==null)
            c =document.getElementById("f1").check1[i].value;
           else
           {
              c = c +";"+ document.getElementById("f1").check1[i].value;
           }
       }
   }
}else{
     if (document.getElementById("check1").checked)
      {


            c =document.getElementById("check1").value;

       }
}
 top.document.getElementById("f").list3.value=c;
}



</script>
<script type="text/javascript">
 var obj1;
 function setValues()
 {
     var recommended_by = top.document.getElementById("f").recommended_by.value;
     var approved_by = top.document.getElementById("f").approved_by.value;
     var approval_no = top.document.getElementById("f").approval_no.value;

     var approval_date = top.document.getElementById("f").approval_date.value;
     var list = top.document.getElementById("f").list.value;
     var list1 = document.getElementById("f").list1.value;
     var list2 = document.getElementById("f").list2.value;
     var list3 = document.getElementById("f").list3.value;

     obj1.add(recommended_by);
     obj1.add(approved_by);
     obj1.add(approval_no);

     obj1.add(approval_date);
     obj1.add(list);
     obj1.add(list1);
     obj1.add(list2);
     obj1.add(list3);

     return obj1;

 }

function setValues1()
{
   // alert("setvalues1");
    var list = top.document.getElementById("f").list.value;
    var list1 = top.document.getElementById("f").list1.value;
    var list2 = top.document.getElementById("f").list2.value;
    var list3 = top.document.getElementById("f").list3.value;
//alert(list2);
    if(list2!="undefined"||list2!=""||list2!=" ")
        {

            var check = document.getElementById("f1").check.length;
  //          alert("list2 check length="+check);
            if(check!=undefined)
                {
             //       alert("no");
                    var list22 = list2.split(";");
                    var index =0;
                    while(check>index)
                        {
                            var checkvalue=document.getElementById("f1").check[index].value;
                            var i=0;
    ///                        alert("list22 length="+list22.length);
                             if(list22.length==0)
                            {
                                if(list2==checkvalue)
                                {
                                //    alert("yes");
                                    document.getElementById("f1").check[index].checked=true;
                                    document.getElementById("f1").check1[index].disabled=true;
                                }
                            }
                            else
                                {
                        while(list22.length>i)
                            {
       //                 alert("List22 value in  i="+list22[i]);
                        if(list22[i]==checkvalue)
                                {

                                    document.getElementById("f1").check[index].checked=true;
                                    document.getElementById("f1").check1[index].disabled=true;
                                }
                                i++;
                            }
                                }


                                index++;
                        }
                }
                else
                    {
                    //    alert("in else");
                        var checkvalue=document.getElementById("f1").check.value;
                    //    alert(checkvalue);
                        //var list22 = list2.replace(";","");
                        var list22 = list2.split(";");
                        var i=0;
                      if(list22.length==0)
                           {
                                if(list22==checkvalue)
                                {
                             //       alert("yes");
                                    document.getElementById("f1").check.checked=true;
                                    document.getElementById("f1").check1.disabled=true;
                                }
                            }
                            else
                                {
                        while(list22.length>i)
                            {
                     //   alert(list22[i]);
                        if(list22[i]==checkvalue)
                                {

                                    document.getElementById("f1").check.checked=true;
                                }
                                i++;
                            }
                                }
                    }
        }

            if(list!="undefined"||list!="" || list!=" ")
        {

            var check = document.getElementById("f1").check1.length;
     //   alert(check);
            if(check!=undefined)
                {
              //     alert("no");
                    var list22 = list.split(";");
                    var list12 = list1.split(";");
                    var index =0;
                    while(check>index)
                        {
                            var checkvalue=document.getElementById("f1").check1[index].value;
                            var i=0;
                             if(list22.length==0)
                            {
                                if(list==checkvalue)
                                {
                           //         alert("yes");
                                    document.getElementById("f1").check1[index].checked=true;
          //                          alert("yes");
                                    var check_index=index+1;
                                    var listval = list12.replace(";", "");
                                    document.getElementById("div"+check_index).innerHTML ="<input type='text' value="+ listval +" id="+index+" onchange='getValue1("+ index +")'/>";
                                   // document.getElementById(index).value = list1;
            //                        alert("yes");
                                    document.getElementById("f1").check[index].disabled=true;
                                }
                            }
                            else
                                {i=0;
                        while(list22.length>i)
                            {
                     //   alert(list22[i]);
                        if(list22[i]==checkvalue)
                                {

                                    document.getElementById("f1").check1[index].checked=true;
                                    var check_index=index+1;
                                    var list42 = list22[i].split(",");
                                    var listval = list12[i].split(",");
                //                    alert("Value of list13 at "+i+" position="+listval);
                                    document.getElementById("div"+check_index).innerHTML ="<input type='text' value="+ listval +" id="+index+" onchange='getValue1("+ index +")'/>";
                                   // document.getElementById(index).value = list1;
                                    document.getElementById("f1").check[index].disabled=true;
                                }
                                i++;
                            }
                                }

                          /*  if(list2.contains(document.getElementById("f1").check[index].value))
                                {
                                    document.getElementById("f1").check[index].checked=true;
                                    document.getElementById("f1").check1[index].disabled=true;
                                }*/
                                index++;
                        }
                }
                else
                    {
                        var checkvalue=document.getElementById("f1").check1.value;
                        var list22 = list.split(";");
                        var list12 = list1.split(";");
                        var i=0;
                      //  alert(list22.length);
                        if(list22.length==1)
                            {
                                if(list==checkvalue)
                                {
                             //       alert("yes");
                                    document.getElementById("f1").check1.checked=true;
                                    var list11=list1.replace(";","");
                                    document.getElementById("div1").innerHTML ="<input type='text' value="+ list11 +" id=1 onchange='getValue1(1)'/>";
                                    document.getElementById("f1").check.disabled=true;
                                }
                            }
                            else
                                {

                                var check_index=i+1;
                        while(list22.length>i)
                            {
                  //    alert(list22[i]+" checkvalue="+checkvalue);
                        if(list22[i]==checkvalue)
                                {

                                    document.getElementById("f1").check1.checked=true;

                                    var div = "div"+check_index;
                              //      alert("div value="+div);
                              //      alert("Value of list12 at "+i+" position="+list12[i]);
                                    //var val = list1.split(";");
                                    document.getElementById(div).innerHTML ="<input type='text' value="+ list12[i] +" id="+i+" onchange='getValue1("+ i +")'/>";
                                    document.getElementById("f1").check.disabled=true;
                                    //document.getElementById("f1").check1.checked=true;
                                }
                                i++;
                            }
                                }
                    }
        }
        else
            {

            }
}






 </script>

<%!
int fromIndex,toIndex;
int pagesize=2,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
List obj1;

%>
<%
int i=0;
 int j=0;
List<ApprovalList> l11=(List<ApprovalList>)session.getAttribute("opacList2");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }

 if(l11!=null)
        size = l11.size();
 else
        size = 0;

 //for calculating no of pages required
 modvalue = size%pagesize;
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;
 //to calculate the starting item and ending item index for the desired page
fromIndex = (pageIndex-1)*pagesize;
toIndex = fromIndex + pagesize;
if(toIndex>size)toIndex=size;
//fromIndex++;



%>
<body onload="setValues1()">
<form id="f1" >
    <table border="0px" style="margin: 0px 0px 0px 0px;">
        <tr><td valign="top" >
                           <table border="0px" valign="top" >
                               <tr bgcolor="#E0E8F5" ><td width="100" >Control No</td><td width="200">Title</td><td width="200">Author</td><td width="100">ISBN</td><td width="100">No of Copies</td><td width="100"></td></tr>

             <logic:iterate id="ApprovalList" name="opacList2" offset="<%=String.valueOf(fromIndex)%>" length="2">
                <tr>
                    <td><bean:write name="ApprovalList" property="control_no"/></td>
                    <td><bean:write name="ApprovalList" property="title"/></td>
                    <td><bean:write name="ApprovalList" property="author"/></td>
                    <td><bean:write name="ApprovalList" property="isbn"/></td>
                    <td><bean:write name="ApprovalList" property="recieved_copies"/></td>
                    
                    </tr>

        </logic:iterate>
 </table>
                           </td></tr>

               </table>
</form>
</body>
