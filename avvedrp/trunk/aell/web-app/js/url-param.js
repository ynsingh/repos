
 
     function getUrlPara( name )
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
     function getRootUrl(){
      var apphost='http://'+window.location.host+'/'

   var urlWithOutHost=document.location.href.substring(apphost.length,document.location.href.length);
   var approot= urlWithOutHost.substring(0,urlWithOutHost.indexOf("/"))+"/";

    var rootUrl= apphost+""+approot ;
             return rootUrl;
     
     }