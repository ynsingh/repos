<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name displayfees.php 
  @author Vijay (vijay.pal428@gmail.com)
 -->
<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
    </head>
    <body>

		   <!--?php
                    echo "<table>";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#LogDetails";
                    echo "<a target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?-->

        <table width="100%">
            <tr colspan="2">
            <div>
	<?php
		    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Log Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
		    $help_uri = site_url()."/help/helpdoc#LogDetails";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
?>
                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

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
	<div class="scroller_sub_page">
	<table>
	<div align="center" >
	<?php
	echo "<table><tr><td valign=\"top\">";

        echo "<table style=\"color: black; width:100%; border-collapse:collapse; border:1px solid #BBBBBB;\" >";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\" colspan=4>";
        echo "View Audit Trails";
        echo "</td>";
        echo "</tr>";
	echo "<tr style=\"background-color:#A9A9A9;font-weight:bold;\"><td style=\"padding: 8px 8px 8px 20px;\"> Date</td><td style=\"padding: 8px 8px 8px 20px;\">Who</td><td style=\"padding: 8px 8px 8px 20px;\">IP Address</td> <td style=\"padding: 8px 8px 8px 20px;\">Description</td></tr>";
	echo "</table>";
	echo "<div style=\"overflow:scroll; height:550px;\">";			
	echo "<table>";
	if(!empty($logdresult3)){
		foreach($logdresult3 as $row){
			$hip =trim($row->host_ip);
			echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> $row->date </td><td style=\"padding: 8px 8px 8px 20px;\">$row->user</td><td style=\"padding: 8px 8px 8px 20px;\">$hip</td> <td style=\"padding: 8px 8px 8px 20px;\">$row->message_title $row->message_desc</td></tr>";
		}
	}
	echo "</table>";
	echo "</div>";

	echo "</td><td valign=\"top\" >";

        echo "<table style=\"color: black; width:100%; border-collapse:collapse; border:1px solid #BBBBBB;\" >";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\" colspan=4>";
        echo "Insert Audit Trails";
        echo "</td>";
        echo "</tr>";
	echo "<tr style=\"background-color:#A9A9A9;font-weight:bold;\"><td style=\"padding: 8px 8px 8px 20px;\"> Date</td><td style=\"padding: 8px 8px 8px 20px;\">Who</td><td style=\"padding: 8px 8px 8px 20px;\">IP Address</td> <td style=\"padding: 8px 8px 8px 20px;\">Description</td></tr>";
	echo "</table>";
	echo "<div style=\"overflow:scroll; height:550px;\">";			
	echo "<table>";
	if(!empty($logdresult1)){
		foreach($logdresult1 as $row){
			echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> $row->date </td><td style=\"padding: 8px 8px 8px 20px;\">$row->user</td><td style=\"padding: 8px 8px 8px 20px;\">$row->host_ip</td> <td style=\"padding: 8px 8px 8px 20px;\">$row->message_title $row->message_desc</td></tr>";
		}
	}
	echo "</table>";
	echo "</div>";

	echo "</td><td valign=\"top\" >";

        echo "<table style=\"color: black;  width:100%; border-collapse:collapse; border:1px solid #BBBBBB;\">";
        echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
        echo "<td style=\"padding: 8px 8px 8px 20px; text-align:center;\" colspan=4>";
        echo "Update Audit Trails";
        echo "</td>";
        echo "</tr>";
	echo "<tr style=\"background-color:#A9A9A9;font-weight:bold;\"><td style=\"padding: 8px 8px 8px 20px;\"> Date</td><td style=\"padding: 8px 8px 8px 20px;\">Who</td><td style=\"padding: 8px 8px 8px 20px;\">IP Address</td> <td style=\"padding: 8px 8px 8px 20px;\">Description</td></tr>";
	echo "</table>";
	echo "<div style=\"overflow:scroll; height:550px;\">";			
	echo "<table>";
	if(!empty($logdresult2)){
		foreach($logdresult2 as $row){
			echo "<tr><td style=\"padding: 8px 8px 8px 20px;\"> $row->date </td><td style=\"padding: 8px 8px 8px 20px;\">$row->user</td><td style=\"padding: 8px 8px 8px 20px;\">$row->host_ip</td> <td style=\"padding: 8px 8px 8px 20px;\">$row->message_title $row->message_desc</td></tr>";
		}
	}
	echo "</table>";
	echo "</div>";

	echo "</td></tr></table>";
	?>
	</div>
</table>
</div>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

