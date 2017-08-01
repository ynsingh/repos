<!-------------------------------------------------------
    -- @name view_adver.php --	
    -- @author Sumit saxena(sumitsesaxena@gmail.com) --
--------------------------------------------------------->
<html>
 <title>View Email Setting</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">    
    </head>    
    <body>

        </br>    
        <table style="margin-left:2%;"> 
     
            <tr colspan="2"><td>  
            <?php
           
                    echo anchor('hrmgmt/add_advertisement/', "Add Advertisement" ,array('title' => 'Add job advertisement' , 'class' => 'top_parent'));
                  //  $help_uri = site_url()."/help/helpdoc#ViewEmailSetting";
                   // echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:74%\">Click for Help</b></a>";
          
            ?>
        
            <div  style="margin-left:2%;">
  
                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

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
  
            </div>
            </td></tr> 
        </table>   
        <table cellpadding="16" style="margin-left:2%;" class="TFtable" >
            <thead>
                <tr align="center">
                    <th>Sr. No.</th>
                    <th>Vacancy Code</th>
                    <th>Advertisement No.</th>
		    <th>Department</th>	
		    <th>Post Code</th>
                    <th>Name of the post</th>
	 	    <th>SC Vacancy</th>	
		    <th>ST Vacancy</th>	
		    <th>OBC Vacancy</th>	
		    <th>UR Vacancy</th>	
		    <th>PWD Vacancy</th>	 
                    <th>Total Vacancy</th>
                    <th>Grade Pay</th>
                    <th>Total Emoluments</th>
                    <th>Age Limit</th>
                    <th>Job Grouop</th>
                    <th>Essential</th>
                    <th>Experience</th>
                    <th>Desirable</th>
                    <th>Job Responsible</th>
		    <th>Online Form Start Date</th>	
                    <th>Online Form Last Date</th>
		    <th>Form Reached Last Date</th>	
                </tr>
            </thead>
            <tbody>
              <?php 
			$count = 0;
		if( count($this->job) ): ?>
                    <?php foreach($this->job as $row){ ?>
                        <tr align="center">
			    <td><?php echo ++$count; ?></td>	
                            <td><?php echo $row->job_vacnacycode; ?></td>
                            <td><?php echo $row->job_adverno; ?></td>
			    <td><?php echo $row->job_department; ?></td>	 
                            <td><?php echo $row->job_postcode; ?></td>
                            <td><?php echo $row->job_nameofpost; ?></td>
                            

                            <td><?php echo $row->job_vacancysc; ?></td>
                            <td><?php echo $row->job_vacancyst ; ?></td>
                            <td><?php echo $row->job_vacancyobc ; ?></td>
                            <td><?php echo $row->job_vacancyur; ?></td>
                            <td><?php echo $row->job_vacancypwd; ?></td>

                            <td><?php echo $row->job_vacancytotal; ?></td>
                            <td><?php echo $row->job_gradepay; ?></td> 
			    <td><?php echo $row->job_emoluments; ?></td>
			    <td><?php echo $row->job_agelimit; ?></td>
			    <td><?php echo $row->job_group; ?></td>	

			    <td><?php echo $row->job_essential; ?></td>
                            <td><?php echo $row->job_experience; ?></td> 
			    <td><?php echo $row->job_desirable; ?></td>
			    <td><?php echo $row->job_responsibles; ?></td>
				 <td><?php echo $row->job_startdateonlineform; ?></td>
			    <td><?php echo $row->job_lastdateonlineform; ?></td>
		
			     <td><?php echo $row->job_lastdateformreach; ?></td>
	
                            <td> <?php  //echo anchor("setup/delete_eset/{$row->id}.{$row->emailprotocol}","Delete",array('title' => 'Delete' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
                            &nbsp;<?php  //echo anchor("setup/editemailsetting/{$row->id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "16" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
    </body>   
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

 
     
        
        





