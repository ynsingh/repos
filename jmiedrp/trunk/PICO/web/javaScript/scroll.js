window.onscroll = function () {
	if(pageYOffset < 10) {
        document.getElementById('bottom').style.visibility = "visible";
	document.getElementById('backToTop').style.visibility = "hidden";
	document.getElementById('top').style.visibility = "hidden";
        }
	else if(pageYOffset >= 200 && pageYOffset < 44770) {
	document.getElementById('top').style.visibility = "visible";
	document.getElementById('bottom').style.visibility = "visible";
        }
	else if(pageYOffset >= 44770){
	document.getElementById('top').style.visibility = "visible";
	document.getElementById('bottom').style.visibility = "hidden";
	document.getElementById('backToTop').style.visibility = "hidden";
	}
};

function scrollToTop()
{
	window.scrollTo(0,0);
}

function scrollToBottom()
{
	window.scrollTo(0,document.body.scrollHeight);
}
