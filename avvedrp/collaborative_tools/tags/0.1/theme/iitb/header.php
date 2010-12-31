<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><?php echo TITLE; ?></title>
<link rel="shortcut icon" type="image/x-icon" href="<?php echo getThemeImage('favicon.ico'); ?>">
<link href="<?php echo getThemeCss('main.css'); ?>" rel="stylesheet" type="text/css"/>
<!--<script type="text/javascript" src="http://www.google.com/jsapi"></script>--> 
 <style type="text/css">
.slideshow { margin: auto; }

</style>
<!-- include jQuery library -->
<script type="text/javascript" src="js/jquery.min.js"></script>

<!-- include Cycle plugin -->
<script type="text/javascript" src="js/jquery.cycle.lite.min.js"></script>
<!--  initialize the slideshow when the DOM is ready -->
<script type="text/javascript">
//  addept iframe height to content height
/*function getDocHeight(doc) {
  alert("getDocHeight called!");
  var docHt = 0, sh, oh;
  if (doc.height) docHt = doc.height;
  else if (doc.body) {
    if (doc.body.scrollHeight) docHt = sh = doc.body.scrollHeight;
    if (doc.body.offsetHeight) docHt = oh = doc.body.offsetHeight;
    if (sh && oh) docHt = Math.max(sh, oh);
  }
  return docHt;
}

function setIframeHeight(ifrm) {
  alert("setIframeHeight called!");
  var iframeWin = window.frames[ifrm];
  alert("setIframeHeight iframe win!"+iframeWin);
  var iframeEl = document.getElementById ? document.getElementById(ifrm): document.all ? document.all[ifrm]: null;
  if ( iframeEl && iframeWin ) {
  alert(iframeWin.document.scrollHeight);
    iframeEl.style.height = "auto"; // helps resize (for some browsers) if new doc is shorter than previous
    var docHt = getDocHeight(iframeWin.document);
    if (docHt) iframeEl.style.height = docHt + 30 + "px"; // add to height to be sure it will all show
  }
   iframeEl.style.height = "auto"; // helps resize (for some browsers) if new doc is shorter than previous
   var docHt = getDocHeight(iframeWin.document);
    if (docHt) iframeEl.style.height = docHt + 30 + "px";
}*/
</script>
</head>
<body bgcolor="#FFFFFF">
<div id="header_main" align="center" style="width:1024px"><img src="<?php echo getThemeImage('logo.png');?>" align="right" style="padding-right:20px"></div>
 <div id="header" ><?php displayTopMenu(array('Home','Project','Vision','People','FAQ','Contact us','Video','Log In'),array('index.php','?pg=project','','','','','','javascript: authenticate()'));
 displaySearchBox();
 ?>
 </div>
