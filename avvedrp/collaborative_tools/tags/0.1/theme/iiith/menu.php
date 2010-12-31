<?php 
if($flg_lab=="olab")
{ 
	$tab_img="";
	if($cnt=='manual')
	{
		$tab_img="tabs_olab_theory.jpg";
	}
	else if($cnt=='simulator')
	{
		$tab_img="tabs_olab_simulator.jpg";
	}
	else if($cnt=='assignment')
	{
		$tab_img="tabs_olab_assignment.jpg";
	}
	else if($cnt=='info')
	{
		$tab_img="tabs_olab_reference.jpg";
	}
	$link="?sub=$sub&brch=$brch&sim=$sim&cnt=";	
?>
<div id="no_print">
<div style="position: relative; margin:auto; width: 1024px;"><img src="<?php echo 'images/'.$tab_img ?>" alt="" width="1024" border="0" usemap="#Map2">
<map name="Map2" id="Map2">
<area shape="rect" coords="28,17,98,80" href="<?php echo $link.'manual'?>" />
<area shape="rect" coords="113,16,186,78" href="<?php echo $link.'simulator'?>" />
<area shape="rect" coords="201,15,277,81" href="<?php echo $link.'assignment'?>" />
<area shape="rect" coords="290,15,365,80" href="<?php echo $link.'info'?>" />
</map>
</div>
<!--div id="tabs">
          <ul>
            <li></li>
            <li><a href="?sub=<?php echo $sub;?>&brch=<?php echo $brch;?>&sim=<?php echo $sim;?>&cnt=manual"><span>Theory</span></a></li>
            <li><a href="?sub=<?php echo $sub;?>&brch=<?php echo $brch;?>&sim=<?php echo $sim;?>&cnt=simulator"><span>Simulator</span></a></li>
            <li><a href="?sub=<?php echo $sub;?>&brch=<?php echo $brch;?>&sim=<?php echo $sim;?>&cnt=assignment"><span>Assignment</span></a></li>
            <li><a href="?sub=<?php echo $sub;?>&brch=<?php echo $brch;?>&sim=<?php echo $sim;?>&cnt=info"><span>Reference</span></a></li>
          
          </ul>
        </div --></div>
<?php }
else
{
	$tab_img="";
	if($cnt=='theory')
	{
		$tab_img="tabs_theory.jpg";
	}
	else if($cnt=='manual')
	{
		$tab_img="tabs_procedure.jpg";
	}
	else if($cnt=='evaluation')
	{
		$tab_img="tabs_Selfevaluation.jpg";
	}
	else if($cnt=='simulator')
	{
		$tab_img="tabs_simulator.jpg";
	}
	else if($cnt=='assignment')
	{
		$tab_img="tabs_assignment.jpg";
	}
	else if($cnt=='info')
	{
		$tab_img="tabs_reference.jpg";
	}
	else if($cnt=='feedback')
	{
		$tab_img="tabs_feedback.jpg";
	}
	$link="?sub=$sub&brch=$brch&sim=$sim&cnt=";		
?>
<div id="no_print">
<div style="position: relative; margin:auto; width: 1024px;"><img src="<?php echo 'images/'.$tab_img ?>" width="1024" alt="" border="0" usemap="#Map3"">
<map name="Map3" id="Map3">
<area shape="rect" coords="29,16,96,80" href="<?php echo $link.'theory'?>" />
<area shape="rect" coords="114,14,188,78" href="<?php echo $link.'manual'?>" />
<area shape="rect" coords="205,15,298,77" href="<?php echo $link.'evaluation'?>" />
<area shape="rect" coords="315,15,389,77" href="<?php echo $link.'simulator'?>" />
<area shape="rect" coords="403,14,478,81" href="<?php echo $link.'assignment'?>" />
<area shape="rect" coords="491,16,568,78" href="<?php echo $link.'info'?>" />
<area shape="rect" coords="582,13,657,80" href="<?php echo $link.'feedback'?>" />
</map>
</div>
</div>
<?php
	
 } 
 
 ?>
