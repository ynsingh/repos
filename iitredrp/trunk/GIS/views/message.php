<script>
/** 
	http://www.dmitri.me/misc/notify/ 
*/
//Message notification
//default = refresh page
function redirect(){
	window.location = <?=isset($redirect)?'"'.$redirect.'"':'window.location'?>;
}
$(function () {
  $.notifyBar({
    html: "<?=$msg?>",
    delay: 1000,
    animationSpeed: "normal",
    close: true,
    cls: "success"
  });
  setTimeout('redirect()', 1200);
});
</script>
