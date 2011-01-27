
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>

       <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
      
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body >
    <div align="center">
       <table border="0"  align="center" width="1024px" cellpadding="0" cellspacing="0  ">
         <tr>
           <td>
           <g:render template="/common/header" />
           </td>
           </tr>
          <tr>
           <td height="10px" valign="top">
            <g:render template="/common/navibar" />
           </td>
           </tr>
         <tr>
           <td  class="outerContent" valign="top">
         <g:layoutBody />
            </td>
            </tr>
             <tr>
               <td  class='footer' valign="top" height="210px">
               <!--  <div style="width:200px;padding-left:310">
    Copyright @ 2009-2010
        </div> -->
             </td>
         </tr>
         </table>
      </div>

    </body>
</html>
