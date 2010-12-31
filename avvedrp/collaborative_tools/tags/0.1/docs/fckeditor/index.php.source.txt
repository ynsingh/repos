<?php
include_once("fckeditor.php");
if($_POST['submit'])
{
	echo $sValue = stripslashes( $_POST['FCKeditor1'] );
	echo '<br>'. htmlspecialchars($sValue);
}
?>
<form method="post" action="">
<?php
$oFCKeditor = new FCKeditor('FCKeditor1') ;
$oFCKeditor->BasePath = '' ;

//$oFCKeditor->Value = '<p>This is some <strong>sample text</strong>. You are using <a href="http://www.fckeditor.net/">FCKeditor</a>.</p>' ;
$oFCKeditor->Create() ;
?>
<input name="submit" id="submit" type="submit"  />
</form>