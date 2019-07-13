<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to IGNTU</title>
	<meta charset="utf-8">
	<title>IGNTU:offline payment</title>
<style>
body{font-family: "Helvetica Neue","Lucida Grande","Helvetica Neue",Arial,sans-serif;}

.light {
  background: #99B89D;
}
.light2{
	background: #FFFFFF;
}
</style>


    </head>
<body>
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
<?php 
	$this->load->view('template/header'); ?>
<table style="width:100%;" >
            <tr>
                <td>
                    <div align="left" style="">
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                   <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
                    <?php
                    if(!empty($_SESSION['success'])){
                        if(isset($_SESSION['success'])){?>
                    <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
                    <?php
                    } };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error" style="font-size:18px;"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                ?>
                </div>
            </td>
     </tr>
</table>
<?php

		 echo "<table width=\"100%\">";
		 echo "<tr>";
		 echo "<td align=\"left\" width=\"33%\"><b>";
		 echo anchor('profile/viewprofile', 'View Profile', array('title' => 'View Profile', 'class' => 'top_parent'));
                 echo "</b></td>";
                 echo "<td align=\"center\" width=\"34%\">";
                 echo "<b></b>";
                 echo "</td>";
                 echo "<td align=\"right\" width=\"33%\"><b>";
		 echo anchor('profile/changepasswd', 'Change Password', array('title' => 'Change Password', 'class' => 'top_parent'));
		 echo "</b></td>";
                 echo "</tr>";
		 echo "</table>";

                ?>

<div>

<div class="scroller">


<?php
	//echo "</br>";
	echo "<table width=\"100%\"><tr ><td valign=\"top\">";
	echo "<table border=\"1\" style=\"text-align:center;color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:center; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; font-size:17px;\">";
	echo "University Profile";
        echo "</td>";
        echo "</tr>";
	echo "<tr>";
	echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; \">";
	echo "<table style=\"text-align:center;padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";


      	echo "<tr><td style=\"padding: 0px 8px 8px 0px;\"><b>University Code</b></td><td>";
	echo $this->result->org_code;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 0px 8px 8px 0px;\"><b>University Name</b></td><td>";
	echo $this->result->org_name;
	echo "</td></tr>";
     	echo "<tr><td style=\"padding: 0px 8px 8px 0px;\"><b>University Type</b></td><td>";
	echo $this->result->org_type;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 0px 8px 8px 0px;\"><b>University Address</b></td><td>";
	echo $this->result->org_address1;
	echo "\t";
	echo $this->result->org_address2;echo ",";echo $this->result->org_city;echo "";echo $this->result->org_state;echo ","; echo $this->contryname->name; echo ","; echo $this->result->org_pincode;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 0px;\"><b>University Email</b></td><td>";
	echo $this->result->org_email;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 0px;\"><b>University Fax</b></td><td>";
	echo $this->result->org_fax;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 0px;\"><b>University Phone</b></td><td>";
	echo $this->result->org_phone;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 0px;\"><b>University Affilation</b></td><td>";
	echo $this->result->org_affiliation;
	echo "</td></tr>";
        echo "<tr><td style=\"padding: 8px 8px 8px 0px;\"><b>University Administrator</b></td><td>";
	echo $this->result->org_adminfn;echo "\t";echo $this->result->org_adminln;echo ",";echo "\t";echo $this->result->org_admindesig;
	echo "</td></tr>";
     	echo "</tbody>";
	echo "</table>";
	echo "</td>";
	echo "</tr>";
        echo "</table>";

	echo "</td><td valign=\"top\" >";

	echo "<table border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;  \">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"text-align:center;padding: 8px 8px 8px 20px; text-align:center;font-size:17px;\">";
        echo "University Program and Seat";
        echo "</td>";
	echo "</tr>";
        echo "<tr>";
        echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
        echo "<table style=\"padding: 8px 8px 8px 20px;text-align:center;\">";
        echo "<tbody align=\"left\">";
	echo "<tr><td><b>Program Category</b></td><td><b>Program Name</b></td><td><b>Program Branch</b></td> <td><b>Seat</b></td></tr>";
	$flag=0;
	$pre="";
	
	foreach($this->prgseat as $row){
		$scname=$this->commodel->get_listspfic1('study_center','sc_name','sc_id',$row->prg_scid)->sc_name;
		if(!($scname==$pre))
		{ echo " <td class=\"light\" colspan=\"4\" style=\"padding: 8px 8px 8px 8px; text-align:center;\">$scname <br>";
	}
		$pre=$scname;
		echo "<tr><td style=\"padding: 8px 8px 8px 0px;\">$row->prg_category</td><td style=\"padding: 8px 8px 8px 0px;\">$row->prg_name</td> <td style=\"padding: 8px 8px 8px 0px;\">$row->prg_branch</td><td style=\"padding: 8px 8px 8px 0px;\">$row->prg_seat</td></tr>";
	}
        echo "</tbody>";
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";

	echo "</td><td valign=\"top\">";

        echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;font-size:17px;\">";
        echo "University Program and Fees";
        echo "</td>";
        echo "</tr>";
        echo "<tr>";
        echo "<td>";
        echo "<table width=\"100%\" border=\"0\" style=\"color: black; border-collapse:collapse;\">";
        echo "<table style=\"padding: 8px 8px 8px 20px;\">";
        echo "<tbody align=\"left\">";
	echo "<tr><td style=\"padding: 8px 8px 8px 0px;\"><b>Category</b></td> <td style=\"padding: 8px 8px 8px 0px;\"><b>Gender</b></td> <td style=\"padding: 8px 8px 8px 0px;\"><b> Fees</b></td></tr>";
	if(!empty($frecord)){
		$pre1="2";
		$pre2="z";
		$flag=0;
		foreach($frecord as $ta){
				//echo "<tr>";
			$flag=0;
			$h=$ta["prgname"];
			$z=$ta["prgsem"];
			if(!($ta["prgname"]==$pre1)){
				$flag=1;
				$h=$ta["prgname"];
	//			echo $h;
				if(!empty($h)){
	//				$hname=$this->commodel->get_listspfic1('program','prg_name','prg_id',$h)->prg_name;
			//		$hbranch=$this->commodel->get_listspfic1('program','prg_branch','prg_id',$h)->prg_branch;
					$hbranch=$this->commodel->get_listspfic1('program','prg_branch','prg_name',$h)->prg_branch;
				}else{
	//				$hname='';
					$hbranch =='';
				}
				echo "<tr>";
	//		echo "<td class=\"light\" colspan=\"4\" style=\"padding: 8px 8px 8px 8px;text-align:center;\">".$hname." ( ".$hbranch. " )  <br>";
			echo "<td class=\"light\" colspan=\"4\" style=\"padding: 8px 8px 8px 8px;text-align:center;\">".$h." ( ".$hbranch. " )  <br>";
			echo "</tr>";
			
			}
			
			if(!($ta["prgsem"]==$pre2)|| $flag==1){
				echo "<tr>";
				echo "<td class=\"light2\" colspan=\"4\" style=\"padding: 8px 8px 8px 8px; text-align:center;\"> Semester $z<br>";
				echo "</tr>";
				
			}
			$pre2=$ta["prgsem"];
			//echo "<br>";}
			//echo $pre1;
			$pre1=$h;
			echo "<tr>";
			//echo "<br>";
			$array = array_slice($ta,2,4);
			$pre3="l";
			foreach($array as $ta1){
				
				$y=$array["prgcat"];
				//echo $pre3;
				//if(!($y==$pre3)){
					echo "<td style=\"padding: 8px 8px 0px 0px;\">";
					echo $ta1;
					echo "</td>";
					
				//}
				//else{
			//for( $i =0;$i<=0;$i++){
				//$array1 = array_slice($array,3,4);
				//echo "hi";
				//echo "<td style=\"padding: 8px 8px 0px 0px;\">";
				//echo $array1["prgfee"];
			//echo "</td>";	//}
 			//}
			$pre3=$y;
			}
			echo "</tr>";
		}
	}
        echo "</tbody>";
        echo "</table>";
        echo "</td>";
        echo "</tr>";
        echo "</table>";

	echo "</td>";
	echo "</tr></table>";
echo "<br>";echo "<br>";echo "<br>";
?>
</div>

</div><!------scroller div------>

<?php $this->load->view('template/footer'); ?>
	
    </body>
</html>
