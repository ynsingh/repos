<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Pay matrix</title>
<body>
<div>
<?php $this->load->view('template/header.php');?>

<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</div>

<div align="left">
<table style="margin-left:0%;">
<tr><td>
<?php echo anchor('setup4/paymatrix/', "Add Pay Matrix", array('title' => 'Add Pay Matrix' , 'class' => 'top_parent'));
//$help_uri = site_url()."/help/helpdoc#ViewProgramandseatDetail";
//echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
?>
</td></tr>
</table>
</div>

        <table> 
            <tr><td>    
                <div>
		<?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                <?php
                };
                ?>

            </div>
        </td></tr>   
        </table>   
    <div align="left" style="margin-left:0%;">
    <table  cellpadding="16" class="TFtable">

	<thead>
	    <tr align="center">
		<th>Level </th>
		<?php 
			for($i=1;$i<=40;$i++){
				//echo "<th> Sub Level".$i."</th>";
				echo "<th> ".$i."</th>";
			}
		?>
		<th>Action</th>
    	</tr> 
	</thead>

    <?php  
        $count=0;$wt='';$pcom='';
	if(!empty($paymat)){
         foreach($paymat as $row)  
         {
		$sgmpc= $row->pm_pc;
                $sgmwt = $row->pm_wt;
                if((strcmp($pcom,$sgmpc))||(strcmp($wt,$sgmwt))){
                	echo "<tr><td colspan=42><b>". $sgmpc ." Pay commission  - ".$sgmwt."</b></td></tr>";
                        $pcom = $sgmpc;
                        $wt = $sgmwt;
                }

//		echo "<tr>";
//		echo "<td colspan=42><b>";
//		echo $row->pm_pc;
//		echo " - ";
//		echo $row->pm_wt;
//		echo "</b></td>";
//		echo "</tr>";
    ?>
            <tr align='center'>
            <td><b><?php echo $row->pm_level;?></b></td>
	<?php
                        for($i=1;$i<=40;$i++){
				$nme='pm_sublevel'.$i;
                        	echo "<td> ".$row->$nme."</td>";
                        	//echo "<td> ".$row->pm_sublevel.$i."</td>";
		//		echo $row->sublevel1;
			}
                ?>
            <td>
		<?php echo anchor('setup4/editpaymatrix/' . $row->pm_id , "Edit", array('title' => 'Edit ', 'class' => 'red-link'));?>
                <?php //echo anchor('setup4/deletem/' . $row->pm_id , "Delete", array('title' => 'Delete P', 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')"));?>
            </td>
    </tr>    
	<tr><td colspan=42></td></tr> 
	<tr><td colspan=42></td></tr> 
    <?php        
         }
	 }
                                                else{ ?>
                                                        <tr>
                                                        <td colspan=42 align=center> No Records found</td>
                                                        </tr>
                                        <?php
                                                } ?>

    </table>
    </div>    

<div>
<?php $this->load->view('template/footer.php');?>
</div>
</body>
</html>
