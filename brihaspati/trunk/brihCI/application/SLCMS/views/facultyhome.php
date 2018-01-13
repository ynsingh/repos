
<!--@filename facultyhome.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
        <head>
          
            <style>
                .panel-primary{
                   // margin-left:20px;
                    //margin-right:5px; 
                  
                    height:500px;
                    background-color: #D0D0D0;
                    
                }
                #Table{
                    
                }
                #Table td{
                    
                    padding: 8px 8px 8px 50px;
                    font-size:15px;
                }
               
                .table2 {
                    font-family: arial, sans-serif;
                    // border-collapse: collapse;
                    width: 100%;
                    //overflow: scroll;
                }
                .table2 td{
                    // border: 2px solid #dddddd;
                    text-align: left;
                    padding: 8px;
                    // padding: 8px 8px 8px 50px;
                }

                tr:nth-child(even) {
                    //background-color: #dddddd;
                // padding: 8px 8px 8px 50px;
                }
            </style>
       
        </head>
       
        <body>
            <div>
                <?php $this->load->view('template/header'); ?>
<!--                <h3>Welcome <? //$this->session->userdata('username') ?></h3>-->
                <?php //$this->load->view('template/facultymenu');?>
                   
            </div><br/>
            <table style="width:100%;">
            <tr>
                <td>   
                    <div class="panel panel-primary" style="background-color: #D0D0D0;border:2px solid #a9a9a9;">
                        <div class="panel-heading" style="background-color:#38B0DE;color:white;font-size:18px;text-align:center;"><b>User Profile</b></div>
                            <div class="panel-body">
                                <table id="Table">
                                    <tr>
                                        <td style="padding: 8px 8px 8px 20px;">User Name</td> 
                                        <td>
                                            <?php  echo $this->name->firstname ;?>&nbsp;&nbsp;<?php echo  $this->lastn->lastname ;?>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding: 8px 8px 8px 20px;">Email</td> 
                                        <td><?php  echo $this->email->email ;?>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding: 8px 8px 8px 20px;">University Name</td> 
					<?php foreach($this->scname as $row){?>
                                        	<td><?php   $orgcode=$this->cmodel->get_listspfic1('study_center','org_code','sc_id',$row->scid)->org_code;
							echo $this->cmodel->get_listspfic1('org_profile','org_name','org_code',$orgcode)->org_name;
							?></td>
					<?php }?>
                                    </tr>
                                    <tr>
                                        <td style="padding: 8px 8px 8px 20px;">Campus Name</td> 
					<?php foreach($this->scname as $row){?>
                                        	<td><?php  echo $this->campusname=$this->cmodel->get_listspfic1('study_center','sc_name','sc_id',$row->scid)->sc_name;?></td>
					<?php }?>
                                    </tr>
                                    <tr>
                                        <td style="padding: 8px 8px 8px 20px;">Department Name</td> 
                                        <td><?php  echo $this->deptname->dept_name ;?></td>
                                    </tr>
                       
                                </table>
                            </div>
                        </div>
                    </td>
                    <td align=right>
                        <div class="panel panel-primary" style="background-color: #D0D0D0;border:2px solid #a9a9a9">
                            <div class="panel-heading" style="text-align:center;background-color:#38B0DE;color:white;font-size:18px;"><b>Course Detail</b></div>
                            <div class="panel-body">
                                <table class="table2" >
                                    <thead >
                                        <tr align="center">
                                            <th>Category</th>
                                            <th>Program</th>
                                            <th>Academic Year</th>
                                            <th>Semester</th>
                                            <th>Subject</th>
                                            <th>Paper</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php if( count($this->cdetail) ): ?>
                                        <?php foreach($this->cdetail as $row){ 
						$prgid = $row->pstp_prgid;
					?>
                                            <tr align="center">
                                                <td><?php echo $this->cmodel->get_listspfic1('program','prg_category ','prg_id',$prgid)->prg_category;?></td>
                                                <td><?php echo $this->cmodel->get_listspfic1('program','prg_name ','prg_id',$prgid)->prg_name .'( '.$this->cmodel->get_listspfic1('program','prg_branch ','prg_id',$prgid)->prg_branch .' )';?></td>
                                                <td><?php echo $this->cmodel->get_listspfic1('program_subject_teacher','pstp_acadyear','pstp_prgid',$prgid)->pstp_acadyear;?></td>
                                                <td><?php echo $this->cmodel->get_listspfic1('program_subject_teacher','pstp_sem','pstp_prgid',$prgid)->pstp_sem;?></td>
                                                <td><?php echo $this->cmodel->get_listspfic1('subject','sub_name','sub_id',$prgid)->sub_name;?></td>
                                                <td><?php echo $this->cmodel->get_listspfic1('subject_paper','subp_name','subp_id',$prgid)->subp_name;?></td>
                                            </tr>
                                        <?php }; ?>
                                        <?php else : ?>
                                        <td colspan= "6" align="center"> No Records found...!</td>
                                        <?php endif;?>

                                    </tbody>    
                                </table>    
                            </div>
                        </div>
                    </td>    
              
                </tr>
            </table>
      
            </div><?php $this->load->view('template/footer'); ?></div>
        </body>
</html>
