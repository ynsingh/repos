  var actualchoices=new Array()
   document.cookie="ready=yes"
   $(document).ready(
   function()
   {
     
   $('#myAccordion').Accordion(
   {

   headerSelector : 'dt',
   panelSelector : 'dd',
   activeClass : 'myAccordionActive',
   hoverClass : 'myAccordionHover',
   panelHeight : 200,
   speed : 300
   }
   );
   }
   );

   
   //////////////collapsing other by click
   
 $(document).ready(function(){  
   var url_param =getUrlPara('pg');
   if(url_param=="main")
   {
   $("#sideMenu").treeview({
   prerendered:false
   });
   }
   else
   {
   $("#sideMenu").treeview({
   prerendered:true
   });
   }
	$("#sideMenu").treeview({	
		unique: true,
		collapsed: false,
		//persist: "location",
		prerendered:true
		
	});	
		
	});
   