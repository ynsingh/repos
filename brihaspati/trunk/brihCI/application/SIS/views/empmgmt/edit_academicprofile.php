
<!--@name edit_academicprofile.php  @author Manorama Pal(palseema30@gmail.com) -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Service Details</title>
    <head>
        <?php $this->load->view('template/header'); ?>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
       
    </head>
    
    <body>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    if($this->roleid == 4){
                        echo anchor('empmgmt/viewempprofile', 'View Profile ', array('class' => 'top_parent'));
                    }
                    else{
                        echo anchor('report/viewfull_profile/'.$this->emp_id, 'View Profile ', array('class' => 'top_parent'));
                    }
                    echo "</td>";
            
                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php  }; ?>
                <?php if  (isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php }; ?>
            </div>
            </td></tr>
        </table>
        <form id="myform" action="<?php echo site_url('empmgmt/edit_academicprofile/'.$this->emp_id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="empid" value="<?php echo  $this->emp_id ; ?>">
                <table style="width:100%; border:1px solid gray;" ><tr><td style="background-color:#0099CC; text-align:left; height:30px;" colspan=8">&nbsp;&nbsp; Edit Academic Qualification Details</td></tr></table>
                <table id="myTable1"  class="TFtable" align="center">
                    <thead>
                        <tr>
                            <th>Academic</th>
                            <th>Board/University</th>
                            <th>Result</th>
                            <th>Year of Passing</th>
                            <th>Discipline</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                    <?php $i=0;?>
                     <?php if( count($academicdata) ):  ?>      
                    <?php foreach($academicdata as $record){; ?>
                        <tr>
                           <?php // echo $record->saq_dgree;?>
                            <td>
                                <input type="text" name="stdclass<?php echo $i;?>" id="stdclass" value="<?php echo $record->saq_dgree;?>" readonly>    
                            </td>
                            <td>
                            <input type="text" name="board<?php echo $i;?>" id="buniv" value="<?php echo $record->saq_board_univ;?>">        
                            </td>
                            <td><select name="result<?php echo $i;?>" id="result">
                                <?php if(!empty($record->saq_result)):;?>
                                 <option value="<?php echo $record->saq_result;?>"><?php echo $record->saq_result;?></option>   
                                <?php else:?>   
                                <option value="">----- Select result ------</option>
                                <?php endif;?>
                                <option value="Pass">Pass</option>
                                <option value="Fail">Fail</option>
                                <option value="Discontinued">Discontinued</option>
                            </select>
                            </td>
                            <td>
                                <input type="text" name="yopass<?php echo $i;?>" value="<?php echo $record->saq_yopass;?>" id="yopass" />
                            </td>
                       
                            <td>
                            <input type="text" name="discipline<?php echo $i;?>" id="dpln" value="<?php echo $record->saq_discipline;?>">        
                            </td>
                            <td>
                               <a href='<?php echo site_url()."/empmgmt/delete_academicprofile/".$record->saq_id;?>' title="Delete" onclick="return confirm('Are you sure you want to delete this record?');"><img src="<?php echo base_url('assets/sis/images/delete3.jpg');?>"></a> 
                            </td>
                            <input type="hidden" name="entryid<?php echo $i;?>" value="<?php echo $record->saq_id;?>"> 
                        </tr>
                        <?php $i++; };?>
                        <input type="hidden" name="totalsize" value="<?php echo $i;?>">
                        <?php else : ?>
                        <td colspan= "13" align="center"> No Records found...!</td>
                        <?php endif;?> 
                        </tbody>
                                    
                    <tr style="color:white;background-color:#0099CC; text-align:left; height:30px;">
                    <td colspan="6">
                    <button name="updateacadrofile" id="update">Update</button>
		    <button type="button" onclick="history.back();">Back</button>
                    </tr>    
                </table> 
                
        </form>
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>    
    </html>    