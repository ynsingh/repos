
<!-------------------------------------------------------
    -- @name stickerpdf.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?><!DOCTYPE html>
<html moznomarginboxes mozdisallowselectionprint>
<head>

</head>

<body>
<img src="uploads/logo/logo2.jpg" alt="logo" style="width:100%;height:70px;">
</br>
<center>
</br></br></br>
<table style="width:90%;margin-top:30px;text-align:center;" border=0>
	
	<tr>
	<?php 
		$i=0;
		if(!empty($getsticker)){
			foreach($getsticker as $row){
		?>
				<td style="border:1px solid black;width:20%"><?php echo $row->ca_centername;?><br>
				<?php $prgid=$this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$row->ca_asmid)->asm_coursename;
                       		echo $progname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';?>
				<br>				
				<?php echo $row->ca_rollno;?></td>
				<td width=2%>&nbsp;</td>	
			<?php $i++;
			if($i%5 == 0){?>
			</tr>
			<tr height=2%><td colspan=5>&nbsp;</td></tr>
			<tr>
			<?php } 
		} 	}
?>
	</tr>
	</table>

</center>

   
</body>
</html>
