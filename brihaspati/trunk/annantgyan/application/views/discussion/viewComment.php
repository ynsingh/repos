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
<div class="form-group col-md-10"><b>    <a href="<?php echo site_url('discussion/viewDiscussion');?>"><span ></span>View Discussion</a></b></div>
<div class="form-group col-md-10">
<!-- Discussion - initial comment -->
<?php foreach ($discussion_query as $discussion_result) : ?>
  <h2>
      <?php echo $discussion_result->ds_title; ?><br />
     <!-- <small><?php //echo $this->lang->line('comments_created_by') . $discussion_result->ds_usrid . $this->lang->line('comments_created_at') . $discussion_result->ds_created_at; ?></small> -->
      <small><?php echo 'Discussion created by ' . $this->commodel->get_listspfic1('sign_up','su_name','su_id',$discussion_result->ds_usrid)->su_name . ' Discussion created at ' . $discussion_result->ds_created_at; ?></small>
  </h2>
  <p class="lead"><?php echo $discussion_result->ds_body; ?></p>
<?php endforeach ; ?>

<!-- Comment - list of comments -->
<?php foreach ($comment_query as $comment_result) : ?>
  <li class="media">
    <a class="pull-left" href="#">
      <img class="media-object" src="<?php%20echo%20base_url()%20;%20?>img/profile.svg" />
    </a>
    <div class="media-body">
      <!--<h4 class="media-heading"><?php //echo $comment_result->cm_usrid . anchor('discussion/cflag/'.$comment_result->cm_dsid . '/' . $comment_result->cm_id,$this->lang->line('comments_flag')) ; ?></h4>-->
      <h4 class="media-heading"><?php echo $this->commodel->get_listspfic1('sign_up','su_name','su_id',$comment_result->cm_usrid)->su_name ." ". anchor('discussion/cflag/'.$comment_result->cm_dsid . '/' . $comment_result->cm_id,'CommentsFlag') ; ?></h4>
      <?php echo $comment_result->cm_body ; ?>
    </div>
  </li>
<?php endforeach ; ?>
</div>
<!-- Form - begin form section -->
<br /><br />
<p class="lead"><?php echo $this->lang->line('comments_form_instruction');?></p>

<?php //echo validation_errors(); ?>
<?php echo form_open('discussion/viewComment','role="form"') ; ?>
   <!-- <div class="form-group col-md-5">
      <label for="comment_name"><?php echo $this->lang->line('comments_comment_name');?></label>
      <input type="text" name="comment_name" class="form-control" id="comment_name" value="<?php echo set_value('comment_name'); ?>">
    </div>
    <div class="form-group col-md-5">
      <label for="comment_email"><?php echo $this->lang->line('comments_comment_email');?></label>
      <input type="email" name="comment_email" class="form-control" id="comment_email" value="<?php echo set_value('comment_email'); ?>">
    </div>-->
    <div class="form-group  col-md-10">
 <!--     <label for="comment_body"><?php echo $this->lang->line('comments_comment_body');?></label>-->
      <label for="comment_body"><?php echo 'Comment Body';?></label>
      <textarea class="form-control" rows="3" name="comment_body" id="comment_body"><?php echo set_value('comment_body'); ?></textarea>
    </div>
    <div class="form-group  col-md-11">
<!--      <button type="submit" class="btn btn-success"><?php echo $this->lang->line('common_form_elements_go');?></button>-->
      <button type="submit" class="btn btn-success"><?php echo 'Submit';?></button>
    </div>
  <?php echo form_hidden('ds_id',$ds_id) ; ?>
<?php echo form_close() ; ?>
</br>
</div>

