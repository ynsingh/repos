<div id="no_print">
<?php
if($flg_lab=="olab")
{
	if ($sub!="" && $brch!="" && $sim!="" && $cnt!=""){
		echo "<div class=\"breadcum\">You are here -> <a href=\"olabs.php\">Home</a> -> <a href=\"olabs.php?sub=".$sub."\">".get_name_of_head($sub,$cur_path)."</a> -> <a href=\"olabs.php?sub=".$sub."&brch=".$brch."&sim=".$sim."\">".get_name_of_head($sim,$cur_path)."</a> -> <a href=\"olabs.php?sub=".$sub."&brch=".$brch."&sim=".$sim."&cnt=".$cnt."\">".get_name_of_head($cnt,$cur_path)."</a></div>";
	} else if ($sub!="" && $brch!="" && $sim!=""){
		echo "<div class=\"breadcum\">You are here -> <a href=\"olabs.php\">Home</a> -> <a href=\"olabs.php?sub=".$sub."\">".get_name_of_head($sub,$cur_path)."</a> -> <a href=\"olabs.php?sub=".$sub."&brch=".$brch."&sim=".$sim."\">".get_name_of_head($sim,$cur_path)."</a></div>";
	} else if ($sub!="" && $brch!=""){
		echo "<div class=\"breadcum\">You are here -> <a href=\"olabs.php\">Home</a> -> <a href=\"olabs.php?sub=".$sub."\">".get_name_of_head($sub,$cur_path)."</a> </div>";
		
	} else if ($sub!=""){
		echo "<div class=\"breadcum\">You are here -> <a href=\"olabs.php\">Home</a> -> <a href=\"olabs.php?sub=".$sub."\">".get_name_of_head($sub,$cur_path)."</a></div>";
	} else {	
	
		echo "<div class=\"breadcum\">You are here <a href=\"olabs.php\">Home</a> -> ".get_name_of_head($sub,$cur_path)."</div>";
	}
}
else
{
	if ($sub!="" && $brch!="" && $sim!="" && $cnt!=""){
		echo "<div class=\"breadcum\">You are here -> <a href=\"index.php\">Home</a> -> <a href=\"index.php?sub=".$sub."\">".get_name_of_head($sub,$cur_path)."</a> -> <a href=\"index.php?sub=".$sub."&brch=".$brch."&ln=".$_SESSION['sess_ln']."\">".get_name_of_head($brch,$cur_path)."</a> -> <a href=\"index.php?sub=".$sub."&brch=".$brch."&sim=".$sim."\">".get_name_of_head($sim,$cur_path)."</a> -> <a href=\"index.php?sub=".$sub."&brch=".$brch."&sim=".$sim."&cnt=".$cnt."\">".get_name_of_head($cnt,$cur_path)."</a></div>";
	} else if ($sub!="" && $brch!="" && $sim!=""){
		echo "<div class=\"breadcum\">You are here -> <a href=\"index.php\">Home</a> -> <a href=\"index.php?sub=".$sub."\">".get_name_of_head($sub,$cur_path)."</a> -> <a href=\"index.php?sub=".$sub."&brch=".$brch."&ln=".$_SESSION['sess_ln']."\">".get_name_of_head($brch,$cur_path)."</a> -> <a href=\"index.php?sub=".$sub."&brch=".$brch."&sim=".$sim."\">".get_name_of_head($sim,$cur_path)."</a></div>";
	} else if ($sub!="" && $brch!=""){
		echo "<div class=\"breadcum\">You are here -> <a href=\"index.php\">Home</a> -> <a href=\"index.php?sub=".$sub."\">".get_name_of_head($sub,$cur_path)."</a> -> <a href=\"index.php?sub=".$sub."&brch=".$brch."&ln=".$_SESSION['sess_ln']."\">".get_name_of_head($brch,$cur_path)."</a></div>";
		
	} else if ($sub!=""){
		echo "<div class=\"breadcum\">You are here -> <a href=\"index.php\">Home</a> -> <a href=\"index.php?sub=".$sub."\">".get_name_of_head($sub,$cur_path)."</a></div>";
	} else {	
	
		echo "<div class=\"breadcum\">You are here <a href=\"index.php\">Home</a> -> ".get_name_of_head($sub,$cur_path)."</div>";
	}
}
//
function get_name_of_head($abbr,$cur_path)
{
	if($abbr!="")
	{
		$ini_array = parse_ini_file($cur_path."bread_cum_txt.ini");
		if($ini_array[$abbr]!="")
		{
			return $ini_array[$abbr];
		}
		return $abbr;
	}
	else
	{
		return 0;
	}
}
?>
</div>
<div id="no_print" align="right" class="breadcum" style="padding-right:60px"> <a href="javascript:print();"><img src="images/print.gif" alt="Print this page" border="0" /></a></div>