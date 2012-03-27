$(document).ready(function(){
      var url_param =getUrlPara('pg');


//if(document.referrer.indexOf("index")>0){
	if(url_param=="main"){
    // second example

	$("#browser").treeview({
         /*collapsed: true,
           animated:"normal" ,
 persist: "location" */
        animated:"normal" ,
             control: "#treecontrol",
    persist: "cookie",
    cookieId: "filetree"

	}

            );
    }else// if(document.referrer.indexOf("index")<0){
    { //second example
    
	$("#browser").treeview({
        animated:"normal" ,
       	persist: "cookie"
        /* collapsed: true,
		animated:"normal",
        unique: true,
        control: "#treecontrol" */
		
	});
    }

 
   /* $("#browser li span.folder").click(function(){
        alert("folder");
         if(document.referrer.indexOf("index")>0){
        $("#browser").treeview({
               animated:"normal" ,
          	persist: "cookie"
        /* collapsed: true,
		animated:"normal",
        unique: true,
        control: "#treecontrol" 

	});
         }
    }); */
   $("#samplebutton").click(function(){
		
		$("#browser").treeview({
			add: branches
		});
	});


	// third example
	$("#red").treeview({
		animated: "fast",
		collapsed: true,
		control: "#treecontrol"
	});


});