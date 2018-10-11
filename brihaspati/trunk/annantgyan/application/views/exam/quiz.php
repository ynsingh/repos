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
 <input type="hidden" name="starttime" value="10/9/2018 12:58:27 AM">
<!--
<?php //foreach($questions as $row) { ?>
    
    <?php //$ans_array = array($row->choice1, $row->choice2, $row->choice3, $row->answer);
	//shuffle($ans_array); ?>
    
    <p><?=$row->quizID?>.<?=$row->question?></p>
    
    <input type="radio" name="quizid<?=$row->quizID?>" value="<?=$ans_array[0]?>" required> <?=$ans_array[0]?><br>
    <input type="radio" name="quizid<?=$row->quizID?>" value="<?=$ans_array[1]?>"> <?=$ans_array[1]?><br>
    <input type="radio" name="quizid<?=$row->quizID?>" value="<?=$ans_array[2]?>"> <?=$ans_array[2]?><br>
    <input type="radio" name="quizid<?=$row->quizID?>" value="<?=$ans_array[3]?>"> <?=$ans_array[3]?><br>
    
    <?php //} ?>
    
<br><br>
-->
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
	<input type="hidden" name="sid" value="<?=$subid;?>" size="25">
	<input type="hidden" name="tid" value="<?=$testid;?>" size="25">
 	<input type="submit" value="Submit">
    </td></tr>
	</form>
  </tbody>
</table>
</div>
