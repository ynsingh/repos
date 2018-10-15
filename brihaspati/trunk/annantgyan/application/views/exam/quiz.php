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
    <th>
	<?php 
		echo "Test Name : ".$this->commodel->get_listspfic1('test','testname','testid',$testid)->testname ." ( ".$this->commodel->get_listspfic1('test','testcode','testid',$testid)->testcode ." )";	
	?>
    </tr>
  </thead>
  <tbody>
	 <form method="post" action="<?php echo base_url();?>index.php/exam/quizsubmit">
	<?php 
		$i=1;
		foreach ($questions as $result) : 
	?>
      <tr>
        <td>
	<?php 
		echo "<p>";
		echo $i;
		echo "&nbsp;)&nbsp;";
		echo $result->question;
		echo "</p>";
		echo "<input type='radio' name='" .$result->qid.  "'value='optiona' required>". $result->optiona;
//		echo $result->optiona;
		echo "<br>";
		echo "<input type='radio' name='".$result->qid. "' value='optionb' required>". $result->optionb;
//		echo $result->optionb;
		echo "<br>";
		echo "<input type='radio' name='".$result->qid."'value='optionc' required>". $result->optionc;
//		echo $result->optionc;
		echo "<br>";
		echo "<input type='radio' name='".$result->qid."'value='optiond' required>". $result->optiond;
//		echo $result->optiond;
		echo "<br>";
		$i++;
	?>
        </td>
      </tr>
    <?php endforeach ; ?>
	<tr><td>
	<input type="hidden" name="sid" value="<?php echo $subid;?>" size="25">
	<input type="hidden" name="tid" value="<?php echo $testid;?>" size="25">
 	<input type="submit" name="submit" value="Submit">
    </td></tr>
	</form>
  </tbody>
</table>
</div>
</div>
