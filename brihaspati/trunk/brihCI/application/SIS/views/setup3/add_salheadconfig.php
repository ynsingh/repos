<!--@name add_salheadconfig.php  @author Manorama Pal(palseema30@gmail.com) -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Salary Head</title>
    <head>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script>
            function goBack() {
                // window.location="<?php echo site_url('setup3/salaryhead_list/');?>";
                window.history.back();
            }
        </script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/salaryformula_list/', "View Salary Head Formula" ,array('title' => 'View salary head formula' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Salary Head Configuration</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['err_message'])) && (($_SESSION['err_message'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['err_message'];
                    echo "</div>";
                }
                ;?>
            </div>
            </td></tr>
        </table>
        <form id="myform" action="<?php echo site_url('setup3/salhead_config');?>" method="POST" enctype="multipart/form-data">
        <div class="scroller_sub_page">
            <table width="100%" border="0">
                <tr>
                    <?php  // echo"seloption====".$seloption;?>
                    <td>Employee Type <font color='Red'>*</font>
                        <select id="emptype" style="width:350px;" name="emptype" required onchange="this.form.submit();"> 
                        <?php if (!empty($seloption)) :?>
                        <option value="<?php echo $seloption;?>"><?php 
                        echo $this->sismodel->get_listspfic1('employee_type','empt_name ','empt_id',$seloption)->empt_name;?></option>
                        <?php else: ?>
                        <option selected="selected" disabled selected>--------Select Employee Type-----</option>
                        <?php endif ;?>
                            <?php foreach($this->emptype as $emptdata): ?>	
   				<option value="<?php echo $emptdata->empt_id; ?>"><?php echo $emptdata->empt_name."( ".$emptdata->empt_code." )"; ?></option> 
                            <?php endforeach; ?>
                        </select>
                    </td> 
	     	</tr>
            </table>                  
            <table class="TFtable">
                <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Head Code</th>
                    <th>Head Name</th>
                    <th>Short Name</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                    <?php $serial_no = 1;  $no=0; // $shconf=0; ;?>
                        <?php if( count($this->salhead ) ):  ?>
                            <?php foreach($this->salhead as $record){ 
					$flag=false;
				?>
                            
                            <tr>
                                <td><?php echo $serial_no++; ?></td>
                                <td><?php echo $record->sh_code; ?></td>
                                <td><?php echo $record->sh_name; ?></td>
                                <td><?php echo $record->sh_shortname; ?></td>
                                 <td>  
                                <?php  // $j = $shconf;
                                if(!empty($shconf)):?> 
                               <?php $array  = explode(",", $shconf);
                                //print_r("seema===".$array);
                                foreach($array as $line) { 
                               
                                if($record->sh_id == $line): 
					$flag = true;
                                endif;
                                 }; 
                                if($flag): ?>
                                <input type="checkbox" name="check_list[]" checked value="<?php echo $record->sh_id; ?>" />
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record->sh_id; ?>" />
                               <?php endif;?>
                             
                               
                                <?php else : ?>
                                <input type="checkbox" name="check_list[]"  value="<?php echo $record->sh_id; ?>" />
                                 <?php endif;?>
				</td>
                            </tr> 
                               
                        <?php }; ?>
                    <?php else : ?>
                        <td colspan= "13" align="center"> No Records found...!</td>
                    <?php endif;?>
                     <tr><td colspan="6"> <button name="update">Update</button></td></tr>    
                        
                </tbody>    
            </table>    
         
             </div><!------scroller div------>    
            </form>    
      
        <p> &nbsp; </p>
      
        <div align="center"> <?php $this->load->view('template/footer');?></div>  
    </body>   
    
