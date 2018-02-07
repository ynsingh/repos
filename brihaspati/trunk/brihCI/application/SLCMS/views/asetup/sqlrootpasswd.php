<!--@name set sql root password
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 -->
<html>
<title>Add MySQL Root Password</title>

    <head>    
       
</head>    
<body>
 <?php $this->load->view('template/header'); ?>
         
        <table width="100%"> 
                 <tr>  
            <?php
                    echo "<td align=\"left\" width=\"33%\">";        
                    echo anchor('asetup/dispmysqlpassword/', "View Setting" ,array('title' => ' Add MySQL Configuration Detail ' , 'class' => 'top_parent'));
                    echo "</td>";
                    
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add MySQL Setting Details</b>";
                    echo "</td>";
                                                
                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdoc#AddMySQLSetting";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
           ?>
		</tr>
           </table>
           <table width="100%">
           <tr><td>
            <div>
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
           </td></tr>
           </table>
            </div>
            </table>
                <form action="<?php echo site_url('asetup/sqlrootpasswd');?>" method="POST" class="form-inline"></div>
                    <table>
                        <tr>  
                            <td><label for="suname" class="control-label">User Name:</label></td>
                            <td><input type="text" name="suname" size="50"  class="form-control" value="Root" readonly /></td>
                        </tr>
                        <tr>
                            <td><label for="spasswd" class="control-label">Password:</label></td>
                            <td><input type="password" name="spasswd"size="50"  class="form-control"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>   
                            <button name="mysqlsetting" >Submit</button>
                            <button name="reset" >Clear</button>
                            </td>
                        </tr>
                    </table>
                </form>
	   </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
