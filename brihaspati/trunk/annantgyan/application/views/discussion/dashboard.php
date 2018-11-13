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
		echo $this->session->userdata('su_name');
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
 <div class="container" style="margin-top: 10px;border:2px solid orange;border-radius: 15px 15px 15px 15px;" id="card">
<h1 id="tables" class="page-header">Dashboard</h1>

<table class="table">
    <thead>
		<tr><th>Discussion List </th></tr>
        <tr>
          <th>Id</th>
          <th>Name (Email)</th>
          <th>Course Name</th>
          <th>Title</th>
          <th>Description</th>
          <th>Actions</th>
        </tr>
    </thead>
    <tbody>
<?php 
	if(!empty($discussion_query)):
		if(count($discussion_query) > 0) { ?>
            <?php foreach ($discussion_query as $row) : ?>
                <tr>
                  <td><?php echo $row->ds_id ; ?></td>
                  <td><?php echo $this->commodel->get_listspfic1('sign_up','su_name','su_id',$row->ds_usrid)->su_name ." ( ".$this->commodel->get_listspfic1('sign_up','su_emailid','su_id',$row->ds_usrid)->su_emailid ." )" ?></td>
                  <td><?php echo $this->commodel->get_listspfic1('courses','cou_name','cou_id',$row->ds_crsid)->cou_name ." ( ".$this->commodel->get_listspfic1('courses','cou_code','cou_id',$row->ds_crsid)->cou_code ." )" ; ?></td>
                  <td><?php echo $row->ds_title; ?></td>
                  <td><?php echo $row->ds_body; ?></td>
		  <td><?php 
//			echo anchor('admin/update_item/ds/allow/'.$row->ds_id,"Allow") .' ' . anchor('admin/update_item/ds/disallow/'.$row->ds_id,"Disallow") ; ?>
                  </td>
                </tr>
	    <?php endforeach ; 
		}?>
        <?php else : ?>
            <tr>
              <td colspan="4">No  Discussion here </td>
            </tr>
        <?php endif; ?>
    </tbody>
</table>

<table class="table">
    <thead>
		<tr><th>Comment List </th></tr>
        <tr>
          <th>Id</th>
          <th>Name(Email)</th>
<!--          <th>Course name</th>-->
          <th>Discussion Title</th>
          <th>Comment</th>
         <th>Actions</th>
        </tr>
    </thead>
    <tbody>
<?php 
		if(!empty($comment_query)):
			if(count($comment_query) > 0) {
				foreach ($comment_query as $row) :
?>
                <tr>
                  <td><?php echo $row->cm_id ; ?></td>
                  <td><?php echo $this->commodel->get_listspfic1('sign_up','su_name','su_id',$row->cm_usrid)->su_name ." ( ".$this->commodel->get_listspfic1('sign_up','su_emailid','su_id',$row->cm_usrid)->su_emailid ." )" ?></td>
                  <td><?php echo $this->commodel->get_listspfic1('discussions','ds_title','ds_id',$row->cm_dsid)->ds_title ." ( ".$this->commodel->get_listspfic1('discussions','ds_body','ds_id',$row->cm_dsid)->ds_body ." )" ; ?></td>
                  <td><?php echo $row->cm_body; ?></td>
		  <td><?php 
					//	echo anchor('admin/update_item/cm/allow/'.$row->cm_id,"Allow") .' ' . anchor('admin/update_item/cm/disallow/'.$row->cm_id,"Disallow") ; 
					

//					echo anchor('admin/update_item/cm/allow/'.
  //                  $row->cm_id,$this->lang->line('admin_dash_allow')) . 
    //                ' ' . anchor('admin/update_item/cm/disallow/'.
//		    $row->cm_id,$this->lang->line('admin_dash_disallow')) ; 
?>
                  </td>
                </tr>
	    <?php endforeach ; 
		}?>
        <?php else : ?>
                <tr>
                  <td colspan="4">No comments here</td>
                </tr>
        <?php endif; ?>
    </tbody>
</table>
 </div>
</div>
<br><br>
<br><br>
