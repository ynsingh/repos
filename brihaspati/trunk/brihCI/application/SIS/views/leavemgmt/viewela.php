<!--@name earnedleave.php 
  @author Shobhit Tiwari(tshobhit206@gmail.com)
  @author Ankur Singh (ankursingh206@gmail.com)
-->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>earnedleave</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
  </head>

<body>

	<table width="100%">
			<tr>
		   <td width="15%"> <?php echo anchor('leavemgmt/displayleavetype/', "View Leave Type", array('title' => 'Display Leave Type' ,'class' =>'top_parent'));?> </td>
                <td align="right" width="100%">  <b>Select Employee Post</b>
                    <select name="etype" id="etype" style="width:250px;"> 								
                      <option value="" disabled selected>--------Select Post-------</option>                       
                      <option value="Assistant">Assistant</option>
                      <option value="Non Teaching"> Non Teaching</option>
                    </select>                                     
                </td>                
                <td align="right"><input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/></td>
            </tr>    
        </table>   
 
        <table width="100%">
				<tr>
            	<td width="100%" style=" background-color: graytext;">             
                	<div style="height:30px;margin-left:560px;"><b>Earned Leave Details</b></div>
               </td>        
               <div>
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
				</tr>
 		 </table>		
        <div class="scroller_sub_page">
         <table class="TFtable" >
            <thead>
						<tr>
                     <th>S.No</th>
                    	<th>User Name</th>
					   	<th>Leave Remaining</th>
                </tr>
				</thead>

				<tbody>
		  		<?php 
				   if(!empty($this->fldata)):
        			$count =0;
               foreach ($this->fldata as $row)
        			{
         		?>
            	<tr>
					 <td> <?php echo ++$count; ?> </td>
					 <td> <?php echo $row['fname'] ?>
							<?php echo $row['lname'] ?>
					 		( <?php echo anchor("leavemgmt/viewell/{$row['userid']}", $row['userid'], array('title' => 'Details' , 'class' => 'red-link')) . " ";?>)</td>
					 <td> <?php echo $row['ltremain'] ?></td>
            	</tr>

				<?php
		        }
			   ?>
				<?php else : ?>
              <td colspan= "8" align="center"> No Records found...!</td>
            <?php endif;?>
				</tbody>
         </table>
       </div>
</body>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
                                                                                                                                                                                          

