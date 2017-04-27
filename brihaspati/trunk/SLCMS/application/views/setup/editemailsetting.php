<!--@name editemailsetting.php
  @author Manorama Pal(palseema30@gmail.com)
 -->
<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
      
        <style>
            .isa_info, .isa_success, .isa_warning, .isa_error {
                margin: 10px 0px;
                padding:12px;
 
            }
            .isa_info {
                color: #00529B;
                background-color: #BDE5F8;
            }
            .isa_success {
                color: #4F8A10;
                background-color: #DFF2BF;
            }
            .isa_warning {
                color: #9F6000;
                background-color: #FEEFB3;
            }
            .isa_error {
                color: #D8000C;
                background-color: #FFBABA;
            }
            .isa_info i, .isa_success i, .isa_warning i, .isa_error i {
                margin:10px 22px;
                font-size:2em;
                vertical-align:middle;
            }
            .top_parent{
                color:white;
                text-decoration: none;
                padding: 8px 8px 8px 20px;
            }
        </style>  
    </head>
    <body>
        <?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo " ";
            echo " | ";
            echo " ";
            echo anchor('setup/dispemailsetting/', "View Email Setting", array('title' => 'Add Detail' , 'class' => 'top_parent')) . " ";
            echo " ";
            echo "| ";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Edit Email Setting";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>
        <table style="margin-left:30px;"> 
            <tr colspan="2"><td>    
                <div style="margin-left:30px;width:1700px;">
                    <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div> </br> 
            </td></tr>  
        </table>    
        <table style="padding: 8px 8px 8px 30px;">  
 
        <?php

            echo form_open('setup/editemailsetting/' . $id);

       
            echo "<tr>";
                echo "<td>";
                    echo form_label('Email Protocol', 'emailprotocol');
                echo "</td>";
                echo "<td>";
                    echo form_input($emailprotocol);
                echo "</td>";
                echo "<td>";
                    echo "Example: smtp";
                echo "</td>";
            echo "</tr>";

            //echo "<p>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Email Host', 'emailhost');
                    //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($emailhost);
                echo "</td>";
                echo "<td>";
                    echo "Example : smtp.cc.iitk.ac.in  or  IP Address: 172.3.1.5";
                echo "</td>";
            echo "</tr>";
        
            echo "<tr>";
                echo "<td>";
                    echo form_label('Email Port', 'emailport');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($emailport);
                echo "</td>";
                echo "<td>";
                    echo " Example : 25";
                echo "</td>";
            echo "</tr>";
            
            echo "<tr>";
                echo "<td>";
                    echo form_label('UserName', 'username');
                echo "</td>";
                echo "<td>";
                    //echo "<br />";
                    echo form_input($username);
                echo "</td>";
                echo "<td>";
                    echo " Example : palseema30  or  palseema30@gmail.com";
                echo "</td>";
            echo "</tr>";
            //echo "</p>";
        
            // echo "<p>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Password', 'password');
                    // echo "<br />";
                echo "</td>";
                echo "<td>";
                    echo form_input($password);
                echo "</td>";
                echo "<td>";
                    echo " Example : *****";
                echo "</td>";
            echo "</tr>";
            //echo "</p>";
        
            //echo "<p>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Sendername', 'sendername');
                echo "</td>";
                echo "<td>";
                // echo "<br />";
                    echo form_input($sendername);
                echo "</td>";
                echo "<td>";
                echo " Example : Seema Pal  or  palseema30@gmail.com";
                echo "</td>";
            echo "</tr>";
        
            echo "<tr>";
                echo "<td>";
                    echo form_hidden('id', $id);
                    echo form_submit('submit', 'Update');
                    echo " ";
        
                    echo anchor('setup/dispemailsetting', 'Back', 'Back to setup emailsetting');
                    // echo "</p>";
                echo "</td>";
            echo "</tr>";

            echo form_close();
        ?>
 
        </table>   
 <!--   </div>
</tr>
</table>-->
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



