<!--@filename viewprofile.php  @author Manorama Pal(palseema30@gmail.com)
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
        <script>
/*            $(document).ready(function(){
                $("#letterid").on('change',function(){
                var alphaletter= $(this).val();
                alert("id===seema"+letterid);
               /* if(alphaletter !== ''){
                    return $('#worktypeid');
                }*
               if(alphaletter == ''){
                    $('#worktypeid').prop('disabled',true);
                    //return $('#worktypeid');
                }
                else{
                    $('#worktypeid').prop('disabled',false);
                }   
               
                });
             });*/
        </script>    
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <?php $this->load->view('template/menu');?>
        <!--<//?= $this->session->userdata('username') ?>-->
        
        <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
        <table width="100%;">
          <tr>
    <?php
	    echo "<td align=\"center\" width=\"100%\">";
	    echo "<b>Employee List Details</b>";
	    echo "</td>";
    ?>
            <!--help part -->
        </tr>
        </table> 
        <div align="left" style="margin-left:2%;">
            
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
                  
        </div>
        <table width="100%">
            <!--<form action="<//?php echo site_url('report/viewprofile'.$id);?>" method="POST">-->
            <?php $id=0;?>
            <?php echo form_open_multipart('report/viewprofile/' .$id);?>    
            <tr bgcolor="grey"><td width="50%">
                <b><?php echo anchor('report/viewprofile/A', "A",array('title' => 'A' , 'id'=>'letterid','class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/B","B",array('title' => 'B' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/C","C",array('title' => 'C' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/D","D",array('title' => 'D' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/E","E",array('title' => 'E' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/F","F",array('title' => 'F' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/G","G",array('title' => 'G' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/H","H",array('title' => 'H' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/I","I",array('title' => 'I' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/J","J",array('title' => 'J' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/K","K",array('title' => 'K' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/L","L",array('title' => 'L' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/M","M",array('title' => 'M' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/N","N",array('title' => 'N' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/O","O",array('title' => 'O' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/P","P",array('title' => 'P' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/Q","Q",array('title' => 'Q' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/R","R",array('title' => 'R' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/S","S",array('title' => 'S' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/T","T",array('title' => 'T' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/U","U",array('title' => 'U' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/V","V",array('title' => 'U' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/W","W",array('title' => 'V' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/X","X",array('title' => 'W' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/Y","Y",array('title' => 'X' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                <?php echo anchor("report/viewprofile/Z","Z",array('title' => 'Y' , 'class' => 'red-link')); ?>&nbsp;&nbsp;
                </b>
                 
                </td> 
                <?php if($filter!==0){;?>  
		<td width="50%"><div><label for="workingtype" style="font-size:15px;"> Select working Type</label>
                        <select id="worktypeid" name="workingtype" required style="width:300px;" onchange="this.form.submit();"> 
                        <?php if(!empty($wtype)):;?>
                        <option value="<?php $wtype;?>"><?php echo $wtype;?></option>
                        <?php else:?>
                        <option selected="selected" disabled selected>------------- Working Type -------------</option>
                        <?php endif?>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                    </select></div>
                </td> 
                <?php }; ?>
               
            </tr>
            <?php echo form_hidden('filter', $filter);?>
            </form>
              </table>
            <div class="scroller_sub_page">
        <table class="TFtable" >
            <?php if(!empty($wtype)):;?>  
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th>Employee Name</th>
                    <th>Campus Name</th>
                   <!-- <th>University Officer Control</th> -->
                    <th>Department Name</th>
                    <th>Designation</th>
                    <th>E-Mail ID</th>
                    <th>Contact No</th>
                    <th>Aadhaar No</th>
                    
                </tr>
            </thead>
            <tbody>
            <?php $serial_no = 1;?>
                <?php if( count($emprecord) ):  ?>
                    <?php foreach($emprecord as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <td><?php echo anchor("report/viewfull_profile/{$record->emp_id}",$record->emp_name." ( "."PF No:".$record->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link')); ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name; ?></td>
<!--                            <td><?php echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name; ?></td>-->
                            <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name; ?></td>
                            <td><?php echo $record->emp_email; ?></td>
                            <td><?php echo $record->emp_phone; ?></td>
                            <td><?php echo $record->emp_aadhaar_no; ?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    
                    <td colspan= "13" align="center"> No Records found...!</td>
                    
                <?php endif;?>
		</tbody>
                <?php endif;?>
        </table>
            </div>    
    </body>    
</html>    
