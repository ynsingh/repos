
<!--@filename facultyhome.php  @author Manorama Pal(palseema30@gmail.com) -->

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
            </style>
        </head>
       
    
        <body>
            <div>
                <?php $this->load->view('template/header'); ?>
                <h3>Welcome <?= $this->session->userdata('username') ?></h3>
                <?php $this->load->view('template/facultymenu');?>
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
                            <div class="panel-body"></div>
                        </div>
                    </td>    
              
                </tr>
            </table>
      
            </div><?php $this->load->view('template/footer'); ?></div>
        </body>
</html>
