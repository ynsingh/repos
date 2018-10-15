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

<?php $current = 'discussion';?>
 <div class="container" style="margin-top: 10px;border:2px solid orange;border-radius: 15px 15px 15px 15px;" id="card">
<div class="form-group col-md-10">
SORT: <?php echo anchor('discussion/viewDiscussion/sort/age/' . (($dir == 'ASC') ? 'DESC' : 'ASC'),'Newest '. (($dir == 'ASC') ? 'DESC' : 'ASC'));?>
</div>

<div class="form-group col-md-10">
<table class="table table-hover">
  <thead>
    <tr>
      <th><?php echo "Discussions "; //this->lang->line('discussions_title') ; ?> &nbsp;
	<a href="<?php echo site_url('discussion/newDiscussion');?>"><span ></span>New Discussion</a></th>
    </tr>
  </thead>
  <tbody>

    <?php foreach ($query as $result) : ?>
      <tr>
        <td>
	<?php 
    		echo anchor('discussion/viewComment/'.$result->ds_id,$result->ds_title) . '    ' . "  Created by  " . $this->commodel->get_listspfic1('sign_up','su_name','su_id',$result->ds_usrid)->su_name ."  " ; 
		    //$this->lang->line('comments_created_by') 
	?>
          <?php echo anchor('discussion/dflag/'.$result->ds_id,'[Flag]');
   // $this->lang->line('discussion_flag')) ; 
	?>
          <br />
          <?php echo $result->ds_body ; ?>
        </td>
      </tr>
    <?php endforeach ; ?>

  </tbody>
</table>
</div>
</div>
