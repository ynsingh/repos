
<!--@filename coehome.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
        <head>
          
            <style>
                .panel-primary{
                   // margin-left:20px;
                    //margin-right:5px; 
                    width:900px;
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
                
                   
            </div><br/>
            <table>
            <tr>
                <td>   
                    <div class="panel panel-primary" style="margin-left:20px;background-color: #D0D0D0; ">
                        <div class="panel-heading">User Profile </div>
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
                                        <td><?php  echo $this->orgname->org_name ;?></td>
                                    </tr>
                                    <tr>
                                        <td style="padding: 8px 8px 8px 20px;">Campus Name</td> 
                                        <td><?php  echo $this->campusname->sc_name ;?></td>
                                    </tr>
                                    <tr>
                                        <td style="padding: 8px 8px 8px 20px;">Department Name</td> 
                                        <td><?php  echo $this->deptname->dept_name ;?></td>
                                    </tr>
                       
                                </table>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="panel panel-primary" style="margin-left:10px; margin-right:20px;background-color: #D0D0D0;">
                            <div class="panel-heading">Course Detail</div>
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
                                        <?php foreach($this->cdetail as $row){ ?>
                                            <tr align="center">
                                                <td><?php echo $this->cmodel->get_listspfic1('program','prg_category ','prg_id',$row->pstp_prgid)->prg_category;?></td>
                                                <td><?php echo $this->cmodel->get_listspfic1('program','prg_name ','prg_id',$row->pstp_prgid)->prg_name ;?></td>
                                                <td><?php echo $row->pstp_acadyear;?></td>
                                                <td><?php echo $row->pstp_sem;?></td>
                                                <td><?php echo $this->cmodel->get_listspfic1('subject','sub_name','sub_id',$row->pstp_subid)->sub_name;?></td>
                                                <td><?php echo $this->cmodel->get_listspfic1('subject_paper','subp_name','subp_id',$row->pstp_papid)->subp_name;?></td>
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
