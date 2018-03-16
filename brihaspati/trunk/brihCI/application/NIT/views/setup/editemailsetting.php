<!--@name editemailsetting.php
  @author Manorama Pal(palseema30@gmail.com)
 -->
<html>
 <title> Edit Email Setting</title>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
    <head>    
        <?php $this->load->view('template/header'); ?>
            <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php //$this->load->view('template/menu');?>
       
    </head>
    <body>
<script>
        function goBack() {
        window.history.back();
        }
    </script>

        <!--?php
            echo "<table width=\"100%\" border=\5"\" style=\"color: yellow;  border-collapse:collapse; border:2px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:center; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            //echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo anchor('setup/dispemailsetting/', "View Email Setting", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Edit Email Setting";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?-->
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
        <table width="100%"> 
            <tr>
                <?php //echo anchor('setup/dispemailsetting/', "View Email Configuration" ,array('title' => ' Email Configuration Detail ' , 'class' => 'top_parent'));
		echo "<td align=\"center\" width=\"100%\">";
                echo "<b>Update Email Setting Details</b>";
                echo "</td>";
		?> 
	</tr>
</table>   
<table width="100%">
            <tr><td>        
       		 <div>
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

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
        <table>  
 
        <?php

            echo form_open('setup/editemailsetting/' . $id);

       
            echo "<tr>";
                echo "<td>";
                echo form_label('Email Protocol', 'emailprotocol');
                echo "</td>";
                echo "<td>";
                   // echo form_input($emailprotocol);
                echo "<select name='emailprotocol' style=\"width:100%;\">;
                <option value=\"$emailprotocol[value]\">$emailprotocol[value]</option>;  
                <option value=\"smtp\">SMTP</option>;
                <option value=\"imap\">IMAP</option>;
                <option value=\"pop\">POP</option>;
                </select>";
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
                    echo form_label('User Name', 'username');
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
                    echo form_label('Sender Name', 'sendername');
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
                    echo form_label('Sender Email', 'senderemail');
                echo "</td>";
                echo "<td>";
                // echo "<br />";
                    echo form_input($senderemail);
                echo "</td>";
                echo "<td>";
                echo " Example : fo@igntu.ac.in";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
                echo "<td>";
                    echo form_label('Module Name', 'modulename');
                echo "</td>";
                echo "<td>";
                // echo "<br />";
                    echo form_input($modulename);
                echo "</td>";
                echo "<td>";
                echo " Example : Account Section";
                echo "</td>";
            echo "</tr>";
            echo "<tr>";
            echo "<td>";
            echo "</td>";
                echo "<td>";
                   echo form_hidden('id', $id);
                   echo form_submit('submit', 'Update');
             echo " ";
             echo form_close();
             echo "<button onclick=\"goBack()\" >Back</button>";
             echo "</td>";
             echo "</tr>";
             echo"</td>";         
        ?>
 
        </table>   
 <!--   </div>
</tr>
</table>-->
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



