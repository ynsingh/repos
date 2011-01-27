<g:applyLayout name="outerLayout">
    <html>
    <head>
        <title><g:layoutTitle/></title>
        <g:layoutHead />
    </head>
     <body>
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
 

       var heading="Career Lab 1 - Entering the Work Place"

       if(URL_param=="careerLab1"){
           heading="Career Lab 1 - Entering the Work Place"
       }else  if(URL_param=="careerLab2"){
           heading="Carrier Lab 2 - In the Work Place"
       }else  if(URL_param=="conversationalEnglish"){
           heading="Conversational English"
       }

   </script>
    <!--  <div class="subcontent"> --->
  <table cellpadding="0" cellspacing="0" border="0">
    <tr>
    <td colspan="2" class="innerHeading" height="64px">

       <h1 style=" padding-top:0px; color:#3367b5; font-size:25;font-family:'Times New Roman', Times, serif" >  <script type="text/javascript">document.write(heading)</script></h1>
      <p style="font-family:'verdana'; font-size:8">You are here Home >> Conversation Skills >> Listen</p>
    </td>
    </tr>
    <tr>
     <!-- <td class="convsidemenu" valign="top" > -->
       <td valign="top"  class="innerMenu">
     
          <g:include controller="conversation"  action="index"/>
      </td>
    <!-- <td class="convSideContent">-->
          <td valign="top"   class="innerContent">
         <g:layoutBody />
       </td>
    </tr>
    </table>
     
        </body>
  </html>
</g:applyLayout>
