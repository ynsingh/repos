<?php
defined('BASEPATH') OR exit('No direct script access allowed');

$this->load->view('template/topstyle.php');
?>
<body>

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

<?php $current = 'exam';?>
 <div class="container" style="margin-top: 10px;border:2px solid orange;border-radius: 15px 15px 15px 15px;" id="card">
<div class="form-group col-md-10">
<table  border=2 align="center">
  <thead>
    <tr>
    <th colspan=6>
	<?php 
		echo "Exam List - ".$this->commodel->get_listspfic1('courses','cou_name','cou_id',$subid)->cou_name." ( ".$this->commodel->get_listspfic1('courses','cou_code','cou_id',$subid)->cou_code ." )";	
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
                echo "Test Name";
                echo "</th>";

                echo "<th>";
                echo "Test Code";
                echo "</th>";
                echo "<th>";
                echo "Test Date";
                echo "</th>";
		echo "<th>";
		echo "Available Actions";
                echo "</th>";
                echo "</tr>";

		if(!empty($testdata)){
		$i=1;
		foreach ($testdata as $row) : 
	   	echo "<tr>";
	        echo "<td>";
		echo $i;
		echo "</td>";
		echo "<td>";
		echo $row->testname;
		echo "</td>";

		echo "<td>";
		echo $row->testcode;
		echo "</td>";
		echo "<td>";
		echo $row->testdate;
		echo "</td>";
		echo "<td>";

		//$enddate = $this->commodel->get_listspfic1('courseannouncement','crsann_crsend','crsann_crsid',$subid)->crsann_crsend;
		$enddate = $this->commodel->get_listspfic1('courseannouncement','crsann_feedbkdate','crsann_crsid',$subid)->crsann_feedbkdate;
		$cdate = date("Y-m-d");
		if($cdate <= $enddate){	
			$datadup = array('su_id' => $this->session->userdata('su_id'), 'testid' => $row->testid,'subid' =>$subid);
			$isexist=$this->commodel->isduplicatemore('studentquestion',$datadup);
			if(!$isexist){
				echo anchor('exam/quiz/' . $row->testid, "Start Exam") ;
			}else{
				echo "Submitted";
			}
		}
		else{
			echo "Timeover";
		}
		echo "</td>";
		echo "</tr>";
		$i++;
	?>
    <?php endforeach ; 
		}else{
			echo "<tr><td colspan=6 align=center>";
			echo "No Record Found";
			echo "</td></tr>";
	}
?>
	</form>
  </tbody>
</table>
</div>
</div>
