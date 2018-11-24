<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<head>
    <script type="text/javascript" src="<?php print base_url(); ?>/assets/js/1.12.4jquery.min.js"></script>
</head>
<body>

<?php
$this->load->view('template/topstyle.php');
?>
<div class="fluid-container">
        <div class="row">
                <div class="col-md-12">
                        <img src="<?php echo base_url('images');?>/logo.png" class="img-responsive center-block">
                </div>
        </div>

        <div class="row">
	<?php 
	  	if($this->session->userdata('su_name') === 'admin'){
                        $this->load->view('template/admin_header.php');
                }else{
                        $this->load->view('template/login_header.php');
                }
         ?>
        </div>

</div>

<div class="container">
<?php echo validation_errors('<div class="alert-warning"  style="font-size: 18px;" align=left>','</div>');?>
        <?php echo form_error('<div class="">','</div>');?>
        <?php
        if(!empty($_SESSION['success'])){
        	if(isset($_SESSION['success'])){?>
        		<div class="alert alert-success" style="font-size: 18px;"><?php echo $_SESSION['success'];?></div>
         <?php
		} 
	};
        ?>

        <?php
        if(!empty($_SESSION['error'])){
        	if(isset($_SESSION['error'])){?>
             		<div class="alert alert-danger" style="font-size: 18px;"><?php echo $_SESSION['error'];?></div>
        <?php
        	};
    	}
    ?>
</div>

<?php $current = 'progress';?>
 <div class="container" style="margin-top: 10px;border:2px solid orange;border-radius: 15px 15px 15px 15px;" id="card">
<div class="form-group col-md-12">
<table  border=2 align="center">
  <thead>
    <tr>
    <th colspan=6>
	<?php 
		echo "".
		$this->commodel->get_listspfic1('sign_up','su_name','su_id',$suid)->su_name
		."&nbsp;&nbsp;&nbsp;&nbsp; ( ".
		$this->commodel->get_listspfic1('sign_up','su_emailid','su_id',$suid)->su_emailid 
		." )&nbsp;&nbsp;&nbsp;&nbsp;".	
		$this->commodel->get_listspfic1('courses','cou_name','cou_id',$subid)->cou_name
		."&nbsp;&nbsp;&nbsp;&nbsp; ( ".
		$this->commodel->get_listspfic1('courses','cou_code','cou_id',$subid)->cou_code 
		." )";	
?>
	</th>
    </tr>
  </thead>
  <tbody>
<?php 
		echo "<tr>";
                echo "<th>";
                echo "Sr. No.";
                echo "</th>";
                echo "<th>";
                echo "Test Name (Test Code)";
                echo "</th>";
                echo "<th>";
                echo "Max Marks";
                echo "</th>";
                echo "<th>";
                echo "Marks Obtained";
                echo "</th>";
		echo "<th>";
		echo "Exam Status";
                echo "</th>";
                echo "</tr>";
                $i=1; $totm=0;$per =0;$tmm=0;
                if(!empty($studata)){
			foreach($studata as $row){
				echo "<tr>";
                                echo "<td>". $i++."</td>";
                                echo "<td>". $row['testname'] ." ( ". $row['testcode'] ." )</td>";
                                echo "<td>". $row['maxmarks']."</td>";
                                echo "<td>". $row['stmarks']."</td>";
                                echo "<td>". $row['stattemp']."</td>";
                                $totm = $totm + $row['stmarks'];
                                $tmm = $tmm + $row['maxmarks'];
                                echo "</tr>";
	           	}
                        //get the max marks
                        // get the  percentage
                        $per = ($totm * 100)/$tmm;
                        echo    "<tr><td>Total Marks :</td><td>".$totm."</td><td> Percentage : </td><td>".$per ."%</td></tr>";
		}
                else{ 
                        echo "<tr>";
                        echo "<td colspan=10 align=center> No Records found</td>";
                        echo "</tr>";
                                        
                } ?>
  </tbody>
</table>
</div>
</div>
	<div id="wrapper">
 
<div id="divForGraph"></div>
 
    </div>
</body>
