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
          } };
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

<?php $current = 'discussion';?>

<!-- Form - begin form section -->
<!--<br /><br />
<p class="lead"><?php //echo $this->lang->line('discussion_form_instruction');?></p> -->
<div class="form-group col-md-10"><b>    <a href="<?php echo site_url('discussion/viewDiscussion');?>"><span ></span>View Discussion</a></b></div>
<?php //echo validation_errors(); ?>
<?php echo form_open('discussion/newDiscussion','role="form"') ; ?>
<!--	<div class="form-group col-md-5">
      <label for="usr_name"><?php echo $this->lang->line('discussion_usr_name');?></label>
      <input type="text" name="usr_name" class="form-control" id="usr_name" value="<?php echo set_value('usr_name'); ?>">
    </div>
    <div class="form-group col-md-5">
      <label for="usr_email"><?php echo $this->lang->line('discussion_usr_email');?></label>
      <input type="email" name="usr_email" class="form-control" id="usr_email" value="<?php echo set_value('usr_email'); ?>">
    </div> -->
    <div class="form-group col-md-10">
      <label for="ds_title"><?php echo "Discussion Title" ?></label>
      <input type="text" name="ds_title" class="form-control" id="ds_title" value="<?php echo set_value('ds_title'); ?>">
    </div>
    <div class="form-group  col-md-10">
      <label for="ds_body"><?php echo "Discussion Description" ?></label>
      <textarea class="form-control" rows="3" name="ds_body" id="ds_body"><?php echo set_value('ds_body'); ?></textarea>
    </div>
    <div class="form-group  col-md-11">
      <button type="submit" class="btn btn-success"><?php echo "Submit" ?></button>
    </div>
<!--
    <div class="form-group col-md-5">
      <label for="usr_name"><?php echo $this->lang->line('discussion_usr_name');?></label>
      <input type="text" name="usr_name" class="form-control" id="usr_name" value="<?php echo set_value('usr_name'); ?>">
    </div>
    <div class="form-group col-md-5">
      <label for="usr_email"><?php echo $this->lang->line('discussion_usr_email');?></label>
      <input type="email" name="usr_email" class="form-control" id="usr_email" value="<?php echo set_value('usr_email'); ?>">
    </div>
    <div class="form-group col-md-10">
      <label for="ds_title"><?php echo $this->lang->line('discussion_ds_title');?></label>
      <input type="text" name="ds_title" class="form-control" id="ds_title" value="<?php echo set_value('ds_title'); ?>">
    </div>
    <div class="form-group  col-md-10">
      <label for="ds_body"><?php echo $this->lang->line('discussion_ds_body');?></label>
      <textarea class="form-control" rows="3" name="ds_body" id="ds_body"><?php echo set_value('ds_body'); ?></textarea>
    </div>
    <div class="form-group  col-md-11">
      <button type="submit" class="btn btn-success"><?php echo $this->lang->line('common_form_elements_go');?></button>
    </div> -->
<?php echo form_close() ; ?>

</div>
