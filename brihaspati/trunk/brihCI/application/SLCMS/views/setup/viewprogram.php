<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Program</title>
<body>
<div>
<?php $this->load->view('template/header.php');?>
<h1>Welcome <?= $this->session->userdata('username')?> </h1>
<?php $this->load->view('template/menu.php');?>
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
</div>

<?php
/*
    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Setup";
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo anchor('setup/program/', "Add Program", array('title' => 'Add Detail' , 'class' => 'top_parent'));
    echo "<span  style='padding: 8px 8px 8px 20px;'> ";
    echo "|";
    echo "<span  style='padding: 8px 8px 8px 20px;'>";
    echo  "View Program";
    //echo "View Programs";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
*/
?>
<center>
<table width="70%">
	<tr><td>
		<?php echo anchor('setup/program/', "Add Program", array('title' => 'Add Program' , 'class' => 'top_parent'));
		$help_uri = site_url()."/help/helpdoc#ViewProgramandseatDetail";
		echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
		?>
</td></tr>
</table>

        <table width="70%"> 
            <tr><td>    
                <div>
                <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning>','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
            </div>
        </td></tr>   
        </table></center>   
    <div align="left" >
    <table  cellpadding="16" class="TFtable">
    <!--<table border=0 cellpadding=16 style="padding: 8px 8px 8px 25px;margin-left:30px;" class="TFtable">
    <table>-->

	<thead>
	    <tr align="center">
		<th>No</th>
                <th>Campus Name</th>
                <th>Deptt. Name</th>
		<th>Prog Category</th>
		<th>Prog Pattern</th>
		<th>Prog Name</th>
		<th>Prog Branch</th>
		<th>Prog Code</th>
		<th>Prog Short</th>
		<th>Prog Desc</th>
		<th>Prog Credit</th>
		<th>Seat Avail</th>
		<th>Prog Min Time (Years)</th>
		<th>Prog Max Time (Years)</th>
		<!--<th>Creator Name</th>
		<th>Creatoion Date</th>-->
		<th>Action</th>
    <!--<td><strong>No</strong></td><td><strong>Program Category</strong></td><td><strong>Program Name</strong></td><td><strong>Program Branch</strong></td> <td><strong>Seat Available</strong></td><td><strong>Program Code</strong></td><td><strong>Program Short</strong></td><td><strong>Program Description</strong></td><td><strong>Program Min Time</strong></td><td><strong>Program Max Time</strong></td><td><strong>Creator Name</strong></td><td><strong>Creatoion Date</strong></td><td><strong>Edit/Delete</strong></td> -->
    	</tr> 
	</thead>

    <?php  
        $count=0;
         foreach($prgres as $row)  
         {
            $count = $count + 1;
    ?>
            <tr align='center'>
            <td><?php echo $count;?></td>
            <td><?php echo $this->common_model->get_listspfic1('study_center','sc_name','sc_id',$row->prg_scid)->sc_name;?></td>
            <td><?php echo $this->common_model->get_listspfic1('Department','dept_name','dept_id',$row->prg_deptid)->dept_name;?></td>
            <td><?php echo $row->prg_category;?></td>
            <td><?php echo $row->prg_pattern;?></td>
            <td><?php echo $row->prg_name;?></td>
            <td><?php echo $row->prg_branch;?></td>
            <td><?php echo $row->prg_code;?></td>
            <td><?php echo $row->prg_short;?></td>
            <td><?php echo $row->prg_desc;?></td>
             <td><?php echo $row->prg_credit;?></td>
            <td><?php echo $row->prg_seat;?></td>
            <td><?php echo $row->prg_mintime;?></td>
            <td><?php echo $row->prg_maxtime;?></td>
           <!-- <td><?php //echo $row->creatorid;?></td>
            <td><?php //echo $row->createdate;?></td>-->
            <td><?php echo anchor('setup/editprogram/' . $row->prg_id , "Edit", array('title' => 'Edit Program', 'class' => 'red-link'));?>
             <?php //echo anchor('setup/deleteprogram/' . $row->prg_id , "Delete", array('title' => 'Delete Program', 'class' => 'red-link','onclick' => "return confirm('Are you sure you want to delete this record')"));?>
            </td>
    <?php        
         }?>  
    </tr>     
    </table>
    </div>    

<div>
<?php $this->load->view('template/footer.php');?>
</div>
</body>
</html>

