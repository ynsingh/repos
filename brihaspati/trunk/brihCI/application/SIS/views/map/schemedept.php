<!--@name userroletype.php  @author Rekha Pal(rekha20july@gmail.com)  -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
         <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
<script>
        $(document).ready(function(){
        
       $('#camp').on('change',function(){
           var sc_code = $(this).val();
           if(sc_code == ''){
               $('#scid').prop('disabled',true);
          
           }
           else{
             
               $('#scid').prop('disabled',false);
               $.ajax({
                   url: "<?php echo base_url();?>sisindex.php/map/getdeptlist",
                   type: "POST",
                   data: {"campusname" : sc_code},
                   dataType:"html",
                   success:function(data){
                      $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                   },
                   error:function(data){
                       alert("error occur..!!");
                 
                   }
               });
           }
       }); 
    });
</script>  
    </head>    
    <body>
       <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?> 
        </br>    
        <table width="100%"> 
       
            <tr><td>  
                <div style="margin-left:2%;width:90%;">    
                <?php echo anchor('map/viewschemedept/', "Map Scheme with Department List ", array('title' => 'View Detail' , 'class' => 'top_parent'));?>
               <?php
            //     $help_uri = site_url()."/help/helpdoc#EmailSetting";
              //   echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:70%\">Click for Help</b></a>";
                 ?>

                </div>
                <div align="left" style="margin-left:2%;width:90%;">
                 <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning">','</div>');?>
                  <?php echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:2%;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div style="margin-left:2%;"  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
         
            </div>
        </td></tr>  
        </table>  
        <br/>
        <table style="margin-left:2%;">
            
           <form action="<?php echo site_url('map/schemedept');?>" method="POST">
                         <tr><td> Choose your Campus: </td><td>
                         <select name="campus" id="camp" style="width:100%;">
                         <option value="">-------------Select Campus---------------</option>
                         <?php foreach($this->scresult as $datas): ?>
 		         <option value="<?php echo $datas->sc_id; ?>"><?php echo $datas->sc_name; ?></option>
                         <?php endforeach; ?>
                         </select>
                        </td></tr>
                       <tr><td>Choose your Department: </td><td>
                        <select name="dept_name" id="scid" disabled="" style="width:100%;">
                        <option value="">select department</option>
                        </select>
                        </td></tr>



                 <tr><td> Choose your Scheme: </td><td>
                         <select name="scheme" id="sdid" style="width:100%;">
                         <option value="">-------------Select Scheme---------------</option>
                         <?php foreach($this->schresult as $datas): ?>
                         <option value="<?php echo $datas->sd_id; ?>"><?php echo $datas->sd_name; ?></option>
                         <?php endforeach; ?>
                         </select>
                        </td></tr>
                    <tr>
                <td></td>
                <td colspan="2">   
                <button name="schemedept">Submit</button>
                <button name="reset">Clear</button>
                </td>
             </tr>
        </form>    
        </table>
    </body> 
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
