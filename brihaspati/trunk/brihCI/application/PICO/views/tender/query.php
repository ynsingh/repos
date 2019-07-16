<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Tender|Question</title>

 <head>
  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
     <?php $this->load->view('template/header'); ?>
   
 </head>    
 <body> 



     <table width="100%">
            <tr><td>
                <?php echo anchor('', "View Detail ", array('title' => 'Add Detail' ,'class' =>'top_parent'));?>
                <?php
                 echo "<td align=\"right\">";
                 $help_uri = site_url()."/help/helpdoc#Role";
		 echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
		echo "</td>"
                 ?>
                <div  style="width:100%;">
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
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
             </td></tr>
        </table>




<form action="<?php echo site_url('tender/tender_question/');?>" method="POST" class="form-inline" enctype="multipart/form-data"> 




          <table class="TFtable">
          <tr>
                <td><label for="" class="control-label"><b>Email ID</b>:-</label></td>
                <td><input type="text" name="q_email" placeholder="E-mail Address Here" style="width:500 ;" /><br></td>
                
           </tr>
            <tr>
                <td><label for="" class="control-label"><b>Query Subject</b>:-</label></td>
                <td><input type="text" name="q_subject" placeholder="Query Subject Here" style="width:500 ;" /><br></td>
                
           </tr>
           <tr>
                <td><label for="" class="control-label"><b>Description</b>:-<br><br><br><br><br><br><br></label></td>
                <td><textarea style="width:500px; height:150px; " name="q_desc" placeholder="Give Details......" ></textarea><br></td>
                
           </tr>
        
          <tr>
               
              
                  <td colspan="2">
                   
                
                
            
                  
                  <button name="reset"  style="float:right; ">Clear</button>
						<button name="submit" style="float:right; " >Submit</button>                  
                  <!-- <input type="hidden" name="tid"value="<?php echo $tid ;?>" >  -->             
         
                  </td>
           
          </tr>
          </table>

</form>
  
  
  
  
  
 <p><br></p>
    </div>
    </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
    </html>
	
	

