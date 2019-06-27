<!--@name editschemedept.php  @author Rekha Pal(rekha20july@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
           
    </head>
    <body>
	<script>
        function goBack() {
        window.history.back();
        }
        </script>

        <table width="100%"> 
            <tr><td>    
                <div style="margin-left:2%;">
                    <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                       <div style="margin-left:2%;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                    ?>        
         </div> </br> 
         </td></tr>  
        </table> 
    <!--<table style="margin-left:50px;">
    <tr><td align="left"> Edit Campus Program seat</td></tr> 
    </table><br/>-->
     <table style="margin-left:2%;">
      <form action="<?php echo site_url('map/editschemedept/' .$msd_id);?>" method="POST" class="form-inline">
           <tr>
           <td>Campus Name</td>
                <td>
                   <?php echo form_input($msd_scid); ?>
                                          
               </td>
            </tr>
            <tr>
                <td>Department</td>
                <td>
                  <?php echo form_input($msd_deptid); ?>
                </td>
                <td><?php echo form_error('msd_deptid')?></td>
            </tr>

		<tr>
                <td>Scheme</td>
                 <td><select name="msd_schmid" class="my_dropdown" style="width:100%;">
                  <!--option value="<?php //echo $this->SIS_model->get_listspfic1('scheme_department','sd_name', 'sd_id',$msd_schmid['value'])->msd_schmid;?>" style="display:none"><?php echo $msd_schmid['value'];?></option-->

                  <option value= style="display:none"><?php echo $msd_schmid['value'];?></option>
                <?php foreach($this->schresult as $datas): ?>
                   <option value="<?php echo $datas->sd_id; ?>"><?php echo $datas->sd_name; ?></option>
                   <?php endforeach; ?>
                </td>
                <td><?php echo form_error('msd_schmid')?></td>
               </td>
            </tr>
               <td></td>
                <td colspan="2">
                <button name="editschemedept" >Update</button>
                 <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
                </td>
            </tr>
            <?php echo form_hidden('msd_id', $msd_id);?>
        </form>
        </table>
    </body>
    <div align="center"><?php $this->load->view('template/footer');?></div>
</html>
        
