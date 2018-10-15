<?php
defined('BASEPATH') OR exit('No direct script access allowed');

$this->load->view('template/topstyle.php');
?>
<body>
<!--
 <script type="text/javascript">
  $(document).ready(function(){
  $('#back').on('click', function(){
        <?php $send = $_SERVER['HTTP_REFERER'];?> 
        var redirect_to="<?php echo $send;?>";             
        window.location.href = redirect_to;
      });
   });
  </script>
-->
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
                echo "Question";
                echo "</th>";

                echo "<th>";
                echo "Your Answer";
                echo "</th>";
                echo "<th>";
                echo "Correct Answer";
                echo "</th>";
//		echo "<th>";
//		echo "Available Actions";
  //              echo "</th>";
                echo "</tr>";

		if(!empty($sansdata)){
		$i=1;
		foreach ($sansdata as $row) : 
	   	echo "<tr>";
	        echo "<td>";
		echo $i;
		echo "</td>";
		echo "<td>";
		echo $this->commodel->get_listspfic1('question','question','qid',$row->quid)->question;
		echo "</td>";

		echo "<td>";
		$stans=$row->stdanswer;
		echo $stans;
		echo "<br>";
		echo $this->commodel->get_listspfic1('question',$stans,'qid',$row->quid)->$stans;
		echo "</td>";
		echo "<td>";
		$actans=$this->commodel->get_listspfic1('question','correctanswer','qid',$row->quid)->correctanswer;
		echo $actans;
		echo "<br>";
		echo $this->commodel->get_listspfic1('question',$actans,'qid',$row->quid)->$actans;
		echo "</td>";
//		echo "<td>";
//		$datadup = array('su_id' => $this->session->userdata('su_id'), 'testid' => $row->testid,'subid' =>$subid);
//		$isexist=$this->commodel->isduplicatemore('studentquestion',$datadup);
//		if(!$isexist){
//			echo anchor('progress/answercopy/' . $row->st_testid, "View Answer Copy") ;
//		}else{
//			echo "Submitted";
//		}
//		echo "</td>";
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
		<tr><td colspan=6>
		<a href="javascript:window.history.go(-1);"> <button type="button" class="btn btn-primary btn-sm" >Back</button></a>
<!--		 <input type="button" name="verifyans" class="btn btn-success submit" value="Back">
		<button type="button" class="btn btn-primary btn-sm"  id= "back">Back</button> -->
		</td></tr>
  </tbody>
</table>
</div>
</div>
