<?php
$tickImg=getThemeImage('tick_mark.png');
if (!$tickImg)
{
	$tickImg='images/tick_mark.png';
}
?>
<script language="javascript">
function setSelTheme(valTheme, allTheme)
{
	var arrThemes=allTheme.split('|');
	for(var j=0;j<arrThemes.length; j++)
	{
		document['img'+arrThemes[j]].src = "images/blank.png";
	}
	document.getElementById('hdTheme').value =valTheme;
	document['img'+valTheme].src = "<?php echo $tickImg; ?>";
	
}
</script>
<?php
extract($_POST);
$arrTheme=getAllThemes();
//$strTheme=implode("|",$arrTheme);
//echo $strTheme;
//

/*if($btnPreview) //in case of preview
{
	echo "hdTheme:".$hdTheme;
	$themeImg="";
	$_SESSION['sess_original_theme']=$_SESSION['sess_current_theme'];
	$_SESSION['sess_current_theme']=$hdTheme;
}*/
echo "<table width=\"100%\" border=0 style=\"vertical-align:middle\"><tr valign=\"middle\"><td>";
echo "<table id=\"tblTheme\" border=0 style=\"display:inline\" ><tr>";
for($i=0;$i<count($arrTheme);$i++)
{
	$themeImg="images/blank.png";
	if($_SESSION['sess_current_theme']==$arrTheme[$i])
	{
		$themeImg=$tickImg;
	}
	//$_SESSION['sess_current_theme']=$arrTheme[$i];
	echo "<td><table border=0><tr><td  valign=\"bottom\" colspan=\"2\">";
	echo "<a style=\"border:none\" href=\"javascript:setSelTheme('".$arrTheme[$i]."','".implode("|",$arrTheme)."')\"><img src=\"".getThemeIcon($arrTheme[$i])."\" /></a>		</td></tr>";
	echo "<tr><td align=\"right\" width=\"50%\"><a style=\"text-align:right\" align=\"right\" href=\"javascript:setSelTheme('".$arrTheme[$i]."','".implode("|",$arrTheme)."')\"><b>".$arrTheme[$i]."</b></a></td>";
	echo "<td align=\"left\"><img align=\"left\" name=\"img".$arrTheme[$i]."\" src=\"".$themeImg."\" border=0 width=\"20px\" height=\"20px\"></td></tr></table></td>";
	if(($i+1)%3==0)
	{
		echo "</tr>";
		if(($i+1)<count($arrTheme))
		{
			echo "<tr>";
		}
	}	
}
if(count($arrTheme)%3!=0){echo "</tr>";}
echo "</table>";
echo "</td><td valign=\"top\">";

?>
<form id="form1" name="form1" method="post" action="" >
  <input type="hidden" id="hdTheme" name="hdTheme" value="<?php echo $hdTheme ?>" /> 
<table border="0" cellspacing="20px">
<tr valign="middle"><td><input style="background-image:url(<?php echo getThemeImage('preview.png')?>); width:155px; height:35px; background-color:Transparent; border:thin; color:Transparent; " type="submit" name="btnPreview" id="btnPreview" value="Preview" /></td></tr>

<tr><td><input style="background-image:url(<?php echo getThemeImage('applytheme.png')?>); width:155px; height:35px; background-color:Transparent; border:thin; color:Transparent; " type="submit" name="btnApply" id="btnApply" width="50px" value="Apply theme" /></td></tr>
<tr><td><img style="opacity: .4;" src="<?php echo getThemeImage('managetabs.png')?>" /></td></tr>
<tr><td><img style="opacity: .4;" src="<?php echo getThemeImage('addnewtabs.png')?>" /></td></tr>
<tr><td><img style="opacity: .4;" src="<?php echo getThemeImage('changelogo.png')?>" /></td></tr>

</table>
</form>
<?php
echo "</td></tr></table>"?>

