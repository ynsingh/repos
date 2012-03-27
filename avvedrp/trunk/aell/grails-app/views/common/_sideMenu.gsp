
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script type="text/javascript">  

 var url_param =getUrlPara('subjectId'); 
 var rootUrl=getRootUrl();

  jQuery.ajax({
type:'POST',async:false,
data: {},
dataType: "json",
url:root_url+"home/backButton",
success:function(data,textStatus){
if(data.sessionoutstatus=='yes')
{
 window.location.href =rootUrl+'logout';
}
},
error:function(XMLHttpRequest,textStatus,errorThrown){}
});

  jQuery.ajax({
type:'POST',async:false,
data: {id:url_param,host:rootUrl},
dataType: "json",
url:""+rootUrl+'home/sidemenudata',
success:function(data,textStatus){

	document.write(data.menuData);

	},
error:function(XMLHttpRequest,textStatus,errorThrown){}
    });
  var contentId=getUrlPara('cnt');
	var experimentId=getUrlPara('exp');
  var links=document.getElementsByName("a");
  var expId=getUrlPara('exp');
  for (var i = 0; i < links.length; i++) {
	  var expUrl=links[i].getAttribute("href").substring(0,document.location.href.lastIndexOf("exp="));
	 var expUrl1=links[i].getAttribute("href").substring(expUrl.length,document.location.href.length);
	  var expUrl2=expUrl1.substring(0,expUrl1.lastIndexOf("&cnt="));
	var expid=expUrl2.substring(4,expUrl2.lenth)
      if((expId==expid)){
    		if(contentId!=false && experimentId!=false)    	          
               links[i].style.background='#7ab4fc' ;
    		else
    			links[i].style.background='#c4defe' ;
    } else{
            links[i].style.background='#c4defe' ;
    }
  }  
  
</script>