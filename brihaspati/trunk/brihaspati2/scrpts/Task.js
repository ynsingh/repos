
/**
*This script is used for Task Management
* @author <a href="mailto:gaurav.soni992@gmail.com">Gaurav Verma</a>
*/






function addtask(frm,field)
        {
                if((frm.title.value!="") && (frm.et.value!=""))
                {
                        if(frm.et.value>=1 && frm.et.value<=30)
                        {
                                frm.actionName.value=field.name;
                                frm.submit();
                        }
                        else
                        {
                                alert("The Expiry time Should be between 1 to 30 days");
                        }
                }
                else
                {
                        alert("Please Fill All The mandatory (*) Fields");
                }
        }
	 var timeout     = 500;
        var closetimer  = 0;
        var ddmenuitem  = 0;

        // open hidden layer

    function mopen(id)
        {
                // cancel close timer
                mcancelclosetime();

                // close old layer
                if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

                // get new layer and show it
                ddmenuitem = document.getElementById(id);
                ddmenuitem.style.visibility = 'visible';
        }

        // close showed layer
  function mclose()
        {
                if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
        }

        // go close timer
 function mclosetime()
        {
                closetimer = window.setTimeout(mclose, timeout);
        }

        // cancel close timer
 function mcancelclosetime()
        {
                if(closetimer)
                {
                        window.clearTimeout(closetimer);
                        closetimer = null;
                }
        }
 // close layer when click-out
        document.onclick = mclose;

function checktask(frm,field)
        {
          if((frm.title.value!="") && (frm.et.value!=""))
                {
                        if(frm.et.value<31 && frm.et.value>=1)
                        {
                                if(frm.dday.value>=1 && frm.dday.value<=7)
                                {
                                        frm.actionName.value=field.name;
                                        frm.submit();
                                }
                                else
                                {
                                        alert("Task Conf days should not be more than 7 days");
                                }
                        }
                        else
                        {
                                alert("The Expiry time Should be between 1 to 30 days");
                        }
                }
                else
                {
                        alert("Please Fill All The mandatory (*) Fields");
                }
        }
//    code for clear the data
        function clearData(frm,field)
       {
               frm.title.value="";
       }



function showToolTip(e,text){
	if(document.all)e = event;
	
	var obj = document.getElementById('bubble_tooltip');
	var obj2 = document.getElementById('bubble_tooltip_content');
	obj2.innerHTML = text;
	obj.style.display = 'block';
	var st = Math.max(document.body.scrollTop,document.documentElement.scrollTop);
	if(navigator.userAgent.toLowerCase().indexOf('safari')>=0)st=0; 
	var leftPos = e.clientX - 100;
	if(leftPos<0)leftPos = 0;
	obj.style.left = leftPos + 'px';
	obj.style.top = e.clientY - obj.offsetHeight -1 + st + 'px';
}	

function hideToolTip()
{
	document.getElementById('bubble_tooltip').style.display = 'none';
	
}

