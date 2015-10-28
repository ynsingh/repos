
<!DOCTYPE html>
<script src="/static/js/login/student.js"></script>
<div class="navbar" >
    <div class="navbar-inner">
        <div class="container-fluid" >
            <div class="row-fluid">                
                <div class="span10">
                    <a href="<?php echo base_url();?>index.php/eguru/index">
                        <img src="<?php echo base_url();?>static/img/index_03.gif">                    
                    </a>
                </div>    
                <div class="span5" style="width:37%">
                    <a class="brand" href="<?php echo base_url();?>index.php/eguru/index">ELECTRONIC GURU</a>
                </div>              
                <div class="container-fluid">
                    <ul class="nav">
                        <li <?= echoActiveClassIfRequestMatches("index") ?>><a href="<?php echo base_url();?>index.php/eguru/index">HOME</a></li>
                        <li <?= echoActiveClassIfRequestMatches("about") ?>><a href="<?php echo base_url();?>index.php/eguru/about/">ABOUT</a></li>
                        <li <?= echoActiveClassIfRequestMatches("contact") ?>><a href="<?php echo base_url();?>index.php/eguru/contact/">CONTACT</a></li>
                        <li <?= echoActiveClassIfRequestMatches("admin") ?>><?php if (isset($_SESSION['email']) && $_SESSION['type'] == 'student') { ?>                                                   
                            <a class="disabled" style="color: bisque" href="<?php echo base_url();?>/admin/">
                                <?php } else { ?>
                                    <a href="<?php echo base_url();?>index.php/admin/">
                                    <?php } ?>ADMIN</a></li>
                    </ul>
                    <?php
                    if (!isset($_SESSION['email'])) {
                        ?>
                        <table>
                            <tr>
                                <td style="padding-left:89px">                                    
                                    <a class="btn btn-success" style="color:white;" href="<?php echo base_url();?>index.php/registration/form">REGISTER</a>                                    
                                </td>
                            </tr>                            
                            <tr>
                                <td style="padding-left:89px">
                                    <form  id="student_login">
                                        <div id="signin" class="modal hide fade in span6" style="display: none; ">
                                            <div class="modal-header">
                                                <a class="close" data-dismiss="modal">x</a>
                                                <h3>Sign-In</h3>
                                            </div>                               
                                            <div class="modal-body span8">
                                                <div class="control-group">
                                                    <label>Email :</label>
                                                    <div class="controls">
                                                        <input type="text" name="email" class="span12 required" placeholder="Email" required/>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label>Password : </label>
                                                    <div class="controls">
                                                        <input type="password" name="password" class="span12 required" placeholder="Password" required/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <input type="submit" name="signin" value="SignIn" class="btn btn-success"/> 
                                                <a href="#" class="btn" data-dismiss="modal">Close</a>
                                            </div>

                                        </div>
                                        <p><a data-toggle="modal" href="#signin" id="login" class="btn btn-info adjust right"><i class="icon-user icon-white"></i> Sign-In</a></p>                            
                                    </form> 
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left:77px">
                                    <a href="<?php echo base_url();?>index.php/eguru/forgot">Forgot Password</a>
                                </td>
                            </tr>
                        </table>
                    <?php } else {
                        ?>
                        <a class="btn btn-danger" style="color:white" href="<?php echo base_url();?>index.php/eguru/logout/"><i class="icon-off icon-white"></i>LOGOUT</a>
                        <h4> Welcome&nbsp
                            <?php if ($_SESSION['type'] == 'student') { ?><a  href="<?php echo base_url();?>index.php/student/profile_student"><?php
                                    echo ucfirst($data['first_name']);
                                } else if ($_SESSION['type'] == 'sub_hod') {
                                    ?>
                                    <a  href="<?php echo base_url();?>index.php/admin/profile_subject_hod/">
                                        <?php
                                        echo ucfirst($data['first_name']);
                                    } else if ($_SESSION['type'] == 'dept_hod') {
                                        ?>
                                        <a  href="<?php echo base_url();?>index.php/admin/profile_department_hod/">
                                            <?php
                                            echo ucfirst($data['first_name']);
                                        } else {
                                            ?>
                                            <a  href="<?php echo base_url();?>index.php/admin/profile_admin/">
                                                <?php
                                                echo "Admin";
                                            }
                                            ?></a></h4>
                                    <?php } ?></div>
                                    </div>
                                    </div>
                                    </div>
                                    </div>
                                    <?php

                                    function echoActiveClassIfRequestMatches($requestUri) {
                                        if ($_SERVER['REQUEST_URI'] != "/") {
                                            $current_file_name = explode("/", $_SERVER['REQUEST_URI']);
						echo $current_file_name[1] ."  ".$current_file_name[0]." ".$current_file_name[2];
                                            if ($current_file_name[2] == $requestUri || $current_file_name[1] == $requestUri) {
                                                echo 'class="active"';
                                            } else if ($current_file_name[1] == 'eguru' && $requestUri == "index")
                                                echo 'class="active"';
                                            else if ($current_file_name[1] == 'eguru' && $requestUri == "about")
                                                echo 'class="active"';
					    else
                                                echo 'class=""';
                                        }
                                    }
                                    ?>
