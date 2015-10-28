<!DOCTYPE html>
<html>
    <head>
        <title>EGURU | HOME</title>
        <link href="<?php echo base_url(); ?>/static/css/bootstrap.css" rel="stylesheet" type="text/css"> 
        <link href="<?php echo base_url(); ?>/static/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">        
        <link href="<?php echo base_url(); ?>/static/css/login.css" rel="stylesheet" type="text/css">
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <!-- <link href="/static/js/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">-->
        <script src="<?php echo base_url(); ?>/static/js/bootstrap.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.validate.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/login/valid.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/valid_user/user_valid.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/registration/register.js"></script>
    </head>
    <body style="background-image: url(<?php echo base_url(); ?>/static/img/back1.jpg)" >
        <?php include "header_login.php"; ?>

        <div class="bool-slider true">
            <div class="inset">
                <div class="control"></div>
            </div>
        </div>
        <div class="container-fluid" id="other_reg">
            <div class="row-fluid">                
                <div id="myCarousel " class="carousel slide span8" style="width:671px;height:439px;margin: 0px">
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- Carousel items -->
                    <div class="carousel-inner">
                        <div class="active item">
                            <img src="<?php echo base_url(); ?>/static/img/it1.jpg">
                        </div>
                        <div class="item">
                            <img src="<?php echo base_url(); ?>/static/img/it2.jpg">
                        </div>
                        <div class="item">
                            <img src="<?php echo base_url(); ?>/static/img/ee1.jpg">
                            <div class="carousel-caption">
                                <h1>thumbail</h1>
                            </div>
                        </div>
                    </div>
                    <!-- Carousel nav -->
                    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
                </div>
                <div class="span6" style="text-align: right;margin: 0px;width:567px;">
                    <div class="container-fluid">                                                
                        <form method="post" action="/registration/others_submit" class="well"  id="others_registration">
                            <div class="row-fluid">
                                <h2 style="margin-right:142px;" > Others Register</h2>
                                <hr>                                
                                <ul style="list-style-type: none">
                                    <li class="control-group">
                                        First Name-
                                        <input type="text" name="first_name"/>
                                        <br>
                                    </li>
                                    <li class="control-group">
                                        <br>
                                        Last Name-
                                        <input type="text" name="last_name" />
                                    </li>
                                    <li class="control-group">
                                        <br>
                                        Email-
                                        <input type="text" name="email"/>
                                    </li>
                                    <li class="control-group">
                                        <br>
                                        Password-
                                        <input type="password" name="pass" id="pass_others"/>
                                    </li>
                                    <li class="control-group">
                                        <br>
                                        Confirm Password-
                                        <input type="password" name="conf_pass"/>
                                    </li>
                                    <li class="control-group">
                                        <br>
                                        Designation-
                                        <input type="text" name="designation"/>
                                    </li>           
                                    <li class="control-group">
                                        <br>
                                        Apply for-
                                        <select name="apply_for" id="apply_for">
                                            <option value=""></option>
                                            <option value="dept_hod">Department Incharge</option>
                                            <option value="sub_hod">Subject Incharge</option>
                                        </select>
                                    </li>             
                                    <li id="department" class="control-group">
                                        Department -
                                        <select name="department" id="department_for">
                                            <option value=""></option>
                                            <?php foreach ($data->departments->result_object() as $row) { ?>
                                                <option id='<?= $row->abbr_name ?>' value='<?= $row->id ?>'><?= $row->name ?></option>
                                            <?php } ?>
                                        </select>
                                    </li>
                                    <li id="subject" class="control-group">
                                        <br>
                                        Subject-
                                        <select name="subject" id="subject_for">                                            
                                        </select>
                                    </li>
                                </ul>
                                <br>
                                <img  id="proceed" src="<?php echo base_url(); ?>/static/img/next.png">
                            </div>
                            <div id="according_apply_for">
                                <ul style="list-style-type: none;">
                                    <li class="control-group">
                                        <br>
                                        Research Papers Publication
                                        <input placeholder="No. of publications" type="text" name="research_papers_publication" id="research_papers_publication"/>
                                    </li>
                                    <li class="control-group">
                                        <br>
                                        Research Projects
                                        <input placeholder="No. of Research projects handled" type="text" name="research_projects" id="research_ptojects">
                                    </li>                                                                   
                                    <li class="control-group">     
                                        <input type="submit" class="btn btn-primary" name="submit" value="Register"/>
                                    </li>
                                </ul>
                            </div>                    
                        </form>                        
                    </div>
                </div>
            </div>
        </div>            

    </div>           
    <div class="container-fluid"  >            
        <form id="student_registration" method="POST"> 
            <div class="row-fluid">                          
                <div class="well span10 " style=" box-shadow: 5px 5px 5px 5px; font-size: 16px;font-family: inherit; ">        
                    <h3> PERSONAL DETAILS</h3>
                    <hr>
                    <div  class="span6" style="margin: 0px;text-align: right">                            
                        <ul style="list-style-type: none;">
                            <li class="control-group">
                                First Name-
                                <input type="text" name="f_name" />
                            </li>
                            <li class="control-group">
                                Last Name-
                                <input type="text" name="l_name"/>
                            </li>
                            <li class="control-group">
                                Mother's Name-
                                <input type="text" name="m_name" />
                            </li >
                            <li class="control-group"> 
                                Mobile*-
                                <div id="registration-phone-primary" class="input-prepend">
                                    <span class="add-on">+91</span>
                                    <input type="text" name="mobile" style="width: 174px"/>
                                </div>
                            </li>
                        </ul>                           
                    </div>
                    <div  class="span6" style="margin: 0px;">                            
                        <ul style="list-style-type: none;text-align: right;">
                            <li class="control-group">
                                Email-
                                <input type="email" name="email"/>
                            </li>
                            <li class="control-group">
                                Choose Your Password-
                                <input type="password" name="pass" id="pass"/>
                            </li>
                            <li class="control-group">
                                Confirm Password-
                                <input type="password" name="conf_pass"/>
                            </li>
                        </ul>                           
                    </div>                    
                </div>  
            </div>
            <br>  
            <div class="row-fluid">
                <div class="well span10 " style="box-shadow: 5px 5px 5px 5px; font-size: 16px;font-family: inherit; ">
                    <h3> EDUCATIONAL DETAILS</h3>
                    <hr>
                    <div  class="span6" style="text-align: right;margin: 0px">
                        <ul style="list-style-type: none">
                            <li class="control-group">
                                Institute-
                                <input type="text" name="institute"/>
                            </li>
                            <li class="control-group">
                                Highest Qualification-
                                <input type="text" name="high_qualify"/>
                            </li>
                            <li class="control-group">
                                Specialization -  
                                <input type="text" name="specialization"/>
                            </li>
                        </ul>
                    </div>
                    <div  class="span6" style="text-align: right; margin: 0px;">
                        <ul style="list-style-type: none">

                            <li class="control-group">
                                Stream- 
                                <input type="text" name="stream"/>
                            </li>
                            <li class="control-group">
                                Year of Passing- 
                                <input type="text" name="passing_year"/>
                            </li>
                        </ul>
                    </div>
                    <div style="float:right">
                        <br><br><br><br>
                        <input type="submit" class="btn btn-success" name="submit" value="submit"/>
                    </div>         
                </div>
        </form>
    </div>                
</div>
</body>
</html>









