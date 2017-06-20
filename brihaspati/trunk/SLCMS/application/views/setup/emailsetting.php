<!--@name emailsetting.php
  @author Manorama Pal(palseema30@gmail.com)
 -->
<html>
<title>Add Email Setting</title>

    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
<?php $this->load->view('template/menu');?>
</head>    
<body>
        <!--?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            echo anchor('setup/dispemailsetting/', "View Email Setting", array('title' => 'Add Detail' ,'class' =>'top_parent')) . " ";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo " Add Email Setting";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        
        ?-->
        <table style="margin-left:40px;"> 
            <tr colspan="2"><td>
                <?php echo anchor('setup/dispemailsetting/', "View Email Setting" ,array('title' => 'Email Configuration Detail ' , 'class' => 'top_parent'));?>
                 <?php
		 $help_uri = site_url()."/help/helpdoc#EmailSetting";
		 echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:75%\">Click for Help</b></a>";
                 ?>
                <div align="left" style=width:1700px;">
       
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

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
        </table><br/>
        <tr>  
            <div style="margin-left:30px;">
                <form action="<?php echo site_url('setup/emailsetting');?>" method="POST" class="form-inline">
                    <table style="margin-left:30px;">
                        <tr>  
                            <td><label for="emailprotocol" class="control-label">Email Protocol:</label></td>
                            <!--<td><input type="text" name="emailprotocol"  class="form-control" size="50" /><br></td>-->
                            <td>
                                <select name="emailprotocol" id="" class="my_dropdown" style="width:430px;">
                                    <option value="" disabled selected >------Select Email Protocol------</option>
                                    <option value="SMTP" class="dropdown-item">SMTP</option>
                                    <option value="IMAP" class="dropdown-item">IMAP</option>
                                    <option value="POP" class="dropdown-item">POP</option>
                                </select>

                            </td>
                            <td><?php echo form_error('emailporotcol')?></td>
                            <td>Example: smtp</td>
                
                        </tr>
                        <tr> 
                            <td><label for="emailhost" class="control-label">Email Host:</label></td>
                            <td><input type="text" name="emailhost" size="50"  class="form-control" /> <br></td>
                            <td><?php echo form_error('emailhost')?></td>
                            <td>Example : smtp.cc.iitk.ac.in  or  IP Address: 172.3.1.5</td>
                        </tr>
                        <tr>
                            <td><label for="emailport" class="control-label">Email Port:</label></td>
                            <td><input type="text" name="emailport"size="50"  class="form-control"/> <br></td>
                            <td><?php echo form_error('emailport')?></td>
                            <td>Example : 25</td>
                        </tr>
                        <tr>
                            <td><label for="username" class="control-label">Username:</label></td>
                            <td><input type="text" name="username"  size="50"  /> <br></td>
                            <td><?php echo form_error('username')?></td>
                            <td>Example : palseema30     or    palseema30@gmail.com</td>
                        </tr>
                        <tr>
                            <td><label for="password" class="control-label">Password:</label></td>
                            <td><input type="password" name="password" size="50"  /> <br></td>
                            <td><?php echo form_error('password')?></td>
                            <td> Example : ****** </td>
                        </tr>
                        <tr>
                            <td><label for="sendername" class="control-label">Sendername:</label></td>    
                            <td><input type="text" name="sendername" size="50"  /> <br></td>
                            <td><?php echo form_error('sendername')?></td>
                            <td> Example : Seema Pal    or    palseema30@gmail.com </td>
                        </tr>
                        <tr>
                            <td><label for="senderemail" class="control-label">Senderemail:</label></td>    
                            <td><input type="text" name="senderemail" size="50"  /> <br></td>
                            <td><?php echo form_error('senderemail')?></td>
                            <td> Example : Finance Officer    or    fo@igntu.ac.in </td>
                        </tr>
                        <tr>
                            <td><label for="modulename" class="control-label">Modulename:</label></td>    
                            <td><input type="text" name="modulename" size="50"  /> <br></td>
                            <td><?php echo form_error('modulename')?></td>
                            <td> Example : Account Section </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="2">   
                            <button name="emailsetting" >Submit</button>
                            <button name="reset" >Clear</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
        </tr>     
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
  
