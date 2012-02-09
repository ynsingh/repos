<%--
    Document   : approvallist
    Created on : May 12, 2011, 4:00:20 PM
    Author     : faraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.admin.StaffDoc;" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript">

  

var haystackText = "";
function findMyText(str,needle, replacement) {
     if (haystackText.length == 0) {
          haystackText = str;
     }

     var match = new RegExp(needle, "ig");
     alert(match+"...........");
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
var c="";



if(top.document.getElementById("f").list2.value !=null)
c = top.document.getElementById("f").list2.value ;

//alert("Fully Selection");



var t=document.getElementById("f1").check.length;

if(document.getElementById("f1").check.length>1){
alert(t);


           if(c=="" && document.getElementById("f1").check[ii].checked==true)
           {
           c =document.getElementById("f1").check[ii].value+";";

            alert("First Selction"+c);



            }
           else if(c!="" && document.getElementById("f1").check[ii].checked==true)
           {
               var t=document.getElementById("f1").check[ii].value;
               var myRegExp = "/"+t+"/";
            //  alert(myRegExp);
                var matchPos1 = c.search(myRegExp);

                if(matchPos1 == -1)
                    c = c+ document.getElementById("f1").check[ii].value+";";



            




           }
           alert("With Selection"+document.getElementById("f1").check[ii].value);
           if(document.getElementById("f1").check[ii].checked==false)
           {

           if(c!="")
           {
                var str = new String();
                str=c;
               
               var d=document.getElementById("f1").check[ii].value+";";
            
             c=  findMyText(str,d, "") ;
             
           }
           }

         
}








if(t==undefined)
    {
        alert("single selection");



      



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
             
               var d=";"+document.getElementById("check").value+";";
            
             c=  findMyText(str,d, "") ;
             alert("After Deselection Single"+c);
           }
           }

        

   }

   
   
 top.document.getElementById("f").list2.value=c;
 alert(top.document.getElementById("f").list2.value);
}






</script>
<script type="text/javascript">
 var obj1;
 

function setValues1()
{
    alert("setvalues1");
    
    
    var list2 = top.document.getElementById("f").list2.value;
    alert(list2);

    if(list2!="undefined"||list2!=""||list2!=" ")
        {

            var check = document.getElementById("f1").check.length;
           
            if(check!=undefined)
                {
         
                    var list22 = list2.split(";");
                    var index =0;
                    while(check>index)
                        {
                            var checkvalue=document.getElementById("f1").check[index].value;
                            var i=0;
    
                             if(list22.length==0)
                            {
                                if(list2==checkvalue)
                                {
                                    
                                    document.getElementById("f1").check[index].checked=true;
                                  
                                }
                            }
                            else
                                {
alert(list22.length+"......");
                                
                        while(list22.length>i)
                            {
                       alert("gsdgs"+list22[i]);
                        if(list22[i]==checkvalue)
                                {

                                    document.getElementById("f1").check[index].checked=true;
                                  
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
List<VoterRegistration> l11=(List<VoterRegistration>)session.getAttribute("VoterList");
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
<body onload="setValues1();">
<form id="f1" >
    <table border="0px" style="margin: 0px 0px 0px 0px;">
        <tr><td valign="top" >
                           <table border="0px" valign="top" >
             <tr bgcolor="#E0E8F5" ><td width="100" >Enrollment</td><td width="200">Name</td><td width="200">Select</td></tr>

             <logic:iterate id="VoterRegistration" name="VoterList" offset="<%=String.valueOf(fromIndex)%>" length="2">
                <tr>
                    <td><bean:write name="VoterRegistration" property="id.enrollment"/></td>
                   <td><bean:write name="VoterRegistration" property="voterName"/></td>
                    <td><input type="checkbox" id="check" name="check" value='<bean:write name="VoterRegistration" property="id.enrollment"/>'  onclick="get_check_value4(<%=i++%>)"/></td>
                 
                  
                </tr>

        </logic:iterate>
 </table>
                           </td></tr>

               </table>
</form>
</body>
