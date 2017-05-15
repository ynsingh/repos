<!--@name emailsetting.php
  @author Manorama Pal(palseema30@gmail.com)
 -->
<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
    </head>    
    <body>

        <?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            //echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo anchor('setup/dispemailsetting/', "View Email Setting", array('title' => 'Add Detail' ,'class' =>'top_parent')) . " ";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo " Add Email Setting";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        
        ?>
        <table> 
     
            <tr colspan="2"><td>    
                <div align="left" style="margin-left:30px;width:1700px;">
       
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
               
                </div>
            </td></tr>  
    
            <tr>  
                <div style="margin-left:30px;">
                <br/>    
      
                    <form action="<?php echo site_url('setup/emailsetting');?>" method="POST" class="form-inline">
                        <table style="margin-left:30px;">
                            <tr>  
                                <td><label for="emailprotocol" class="control-label">Email Protocol:</label></td>
                                <td><input type="text" name="emailprotocol"  class="form-control" size="50" /><br></td> 
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
                                <td><input type="text" name="password" size="50"  /> <br></td>
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
                                <td colspan="2">   
                                <button name="emailsetting" >Add Email Setting</button>
                                <button name="reset" >Reset</button>
                                </td>
                            </tr>
                        </table>
                    </form>
       
                </div> 
            </tr>     
        </table>     
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
  
