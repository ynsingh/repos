<!--@name dispemailsetting.php
  @author Manorama Pal(palseema30@gmail.com)
 -->
<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/message.css">
    </head>    
    <body>
        <?php
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
        ?>
   
        </br>    
        <table> 
     
            <tr colspan="2"><td>    

                <div  style="margin-left:30px;width:1700px;">
  
                <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                <?php
                };
                ?>
    
            </div>
        </td></tr>   
        <br/>
        <tr>
            <div align="left" style="margin-left:30px;">
  
                <?php
       
                    echo "<table style=\"padding: 8px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
        
                    echo "<table border=0 cellpadding=13 style=\"padding: 8px 8px 8px 20px;\">";
                    echo "<thead><tr align=\"left\"><th>Email Protocol</th><th>Email Host</th><th>Email Port</th><th>Username</th><th>Password</th><th>Sendername</th><th>Creator Id</th><th>Creation Date</th><th>Modifier Id</th><th>Modified Date</th><th>Action</th><th></th></tr></thead>";
                    //  echo "<thead><tr><th>Id</th><th>EmailProtocol</th><th>EmailHost</th><th>EmailPort</th><th>UserName</th><th>Password</th><th>SenderName</th><th>CreatorId</th><th>CreationDate</th><th>ModifierId</th><th>ModifiedDate</th><th>Action</th><th></th></tr></thead>";
                    foreach ($query->result() as $row)
                    {
                        echo "<tr>";
                       // echo "<td>" . $row->id   . "</td>";
                        echo "<td>" . $row->emailprotocol . "</td>";
                        echo "<td>" . $row->emailhost . "</td>";
                        echo "<td>" . $row->emailport . "</td>";
                        echo "<td>" . $row->username . "</td>";
                        echo "<td>" . $row->password . "</td>";
                        echo "<td>" . $row->sendername . "</td>";
                        echo "<td>" . $row->creatorid . "</td>";
                        echo "<td>" . $row->createdate . "</td>";
                        echo "<td>" . $row->modifierid . "</td>";
                        echo "<td>" . $row->modifidate . "</td>";
                        echo "<td>" . anchor('setup/delete_eset/' . $row->id , "Delete", array('title' => 'Details' , 'class' => 'red-link' ,'onclick' => "return confirm('Are you sure you want to delete this record')")) . " ";
                        echo "<td>" . anchor('setup/editemailsetting/' . $row->id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')) . " ";
                        echo "</br>";
                        echo "</tr>";
                    }
                    echo "</table>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
        
                ?>
            </div>
        </tr>
    </table>    
    </body>   
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

 
     
        
        





