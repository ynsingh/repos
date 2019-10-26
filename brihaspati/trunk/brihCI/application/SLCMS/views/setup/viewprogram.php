<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>View Program</title>
<body>
<div>
<?php $this->load->view('template/header.php');?>
<?php //$this->load->view('template/menu.php');?>
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
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
<table width="100%">
	<tr>
		<?php 
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup/program/', "Add Program", array('title' => 'Add Program' , 'class' => 'top_parent'));
                echo "</td>";
                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Program Details</b>";
                echo "</td>";
                echo "<td align=\"right\" width=\"33%\">";
		$help_uri = site_url()."/help/helpdoc#ViewProgramandseatDetail";
		echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
		?>
</tr>
</table>
            <table width="100%"> 
               <tr><td>
                <div>
                <?php echo validation_errors('<div class="isa_warning>','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
            </div>
	</td>
       </tr>
     </table>
    <div class="scroller_sub_page">   
    <table  class="TFtable">
    <!--<table border=0 cellpadding=16 style="padding: 8px 8px 8px 25px;margin-left:30px;" class="TFtable">
    <table>-->

	   <thead>
	        <th>No</th>
             <!--   <th>Campus Name</th>-->
                <th>Deptt. Name</th>
		<!--<th>Prog Category</th>-->
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
    
	</thead>

    <?php 
    $count=0; 
    $orid=0;
    $prgcatid='';
       // $count=1;
         foreach($prgres as $row)  
         {
//   print_r($prgres); 
    //$orgid = $this->common_model->get_listspfic1('org_profile','org_id','org_id',$row->prg_scid)->org_id;
    if($orid != $row->prg_scid){
     ?>
       <tr>

                <td colspan=15 style="font-size:18px;text-align:center;"><b>Institute Name :</b>
        <?php if(!empty($row->prg_scid)){
                                echo $this->common_model->get_listspfic1('study_center','sc_name','sc_id',$row->prg_scid)->sc_name;
                }?></td>
        </tr>   
     <?php $orid = $row->prg_scid; 
     $count= 0;
     $prgcatid='';
    }
    
    if($prgcatid != $row->prg_category){
    
         ?> 
       <tr>

                <td colspan=15 style="font-size:18px;"><b>Programme Category :</b>
        <?php //echo $this->common_model->get_listspfic1('programcategory','prgcat_name','prgcat_id',$row->prg_category)->prgcat_name; 
	 //              echo  $this->common_model->get_listspfic1('programcategory','prgcat_name','prgcat_id',$row->prg_category)->prgcat_name;
              echo $row->prg_category;
        echo " &nbsp";"&nbsp";"&nbsp";"&nbsp";?>
   
     <?php 
    
     echo "</td>";
        echo "</tr>";
     $prgcatid =$row->prg_category; 
    $count= 0;
    
        
    } 
    
            $count = $count + 1;
    ?>
            <tr>
            <td><?php echo $count;?></td>
           <!-- <td><?php //echo $this->common_model->get_listspfic1('study_center','sc_name','sc_id',$row->prg_scid)->sc_name;?></td>-->
            <td><?php echo $this->common_model->get_listspfic1('Department','dept_name','dept_id',$row->prg_deptid)->dept_name;?></td>
            <!--<td><?php echo $row->prg_category;?></td> -->
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
      //   }
     }?>  
    </tr>     
    </table></div>
<div>
<?php $this->load->view('template/footer.php');?>
</div>
</body>
</html>
