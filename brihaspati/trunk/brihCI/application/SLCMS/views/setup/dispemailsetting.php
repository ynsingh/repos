<!--@name dispemailsetting.php
  @author Manorama Pal(palseema30@gmail.com)
 -->
<html>
 <title>View Email Setting</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">    
    </head>    
    <body>

        <!--?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            //echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo anchor('setup/emailsetting/', "Add Email Setting", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "View Email Setting";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?-->
        <center>            
        <table width="70%"> 
     
            <tr colspan="2"><td>  
            <?php
           
                    echo anchor('setup/emailsetting/', "Add Email Setting" ,array('title' => ' Add Email Configuration Detail ' , 'class' => 'top_parent'));
                    $help_uri = site_url()."/help/helpdoc#ViewEmailSetting";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:54%\">Click for Help</b></a>";
          
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
        </table></center>   
        <table cellpadding="16" class="TFtable" >
            <thead>
                <tr align="center">
                    <th>Email Protocol</th>
                    <th>Email Host</th>
                    <th>Email Port</th>
                    <th>User Name</th>
                    <th>Password</th>
                    <th>Sender Name</th>
                    <th>Sender Email</th>
                    <th>Module Name</th>
                    <!--<th>Creator Name</th>
                    <th>Creation Date</th>
                    <th>Modifier Name</th>
                    <th>Modify Date</th>-->
                    <th>Action</th>
                    <!-- <th></th>-->
                </tr>
            </thead>
            <tbody>
              <?php if( count($this->result) ): ?>
                    <?php foreach($this->result as $row){ ?>
                        <tr align="center">
                            <td><?php echo $row->emailprotocol; ?></td>
                            <td><?php echo $row->emailhost; ?></td>
                            <td><?php echo $row->emailport; ?></td>
                            <td><?php echo $row->username; ?></td>
                            <td><?php echo "xxxxxxxxx"; ?></td>
                         
                            <td><?php echo $row->sendername ; ?></td>
                            <td><?php echo $row->senderemail ; ?></td>
                            <td><?php echo $row->modulename ; ?></td>
                            <!-- <td><//?php echo $row->creatorid; ?></td>
                            <td><//?php echo $row->createdate; ?></td>
                            <td><//?php echo $row->modifierid; ?></td>
                            <td><//?php echo $row->modifidate; ?></td> -->
                            <td> <?php // echo anchor("setup/delete_eset/{$row->id}.{$row->emailprotocol}","Delete",array('title' => 'Delete' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")); ?>&nbsp;
                            &nbsp;<?php  echo anchor("setup/editemailsetting/{$row->id}","Edit",array('title' => 'Edit Details' , 'class' => 'red-link')); ?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "7" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
    </body>   
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

 
     
        
        





