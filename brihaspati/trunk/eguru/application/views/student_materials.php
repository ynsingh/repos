<!DOCTYPE html>
<html>
    <head>
        <title>EGURU | HOME</title>
        <link href="<?php echo base_url(); ?>/static/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="<?php echo base_url(); ?>/static/css/login.css" rel="stylesheet" type="text/css">
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <!--<script src="/static/js/bootstrap.responsive.min.js" ></script>-->
        <script src="<?php echo base_url(); ?>/static/js/bootstrap.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.validate.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/student/material_change.js"></script>
    </head>
    <body style="background-image: url(/static/img/it3.jpg)">
        <?php
        include 'header_login.php';
        if (!isset($_SESSION['email']))
            header("Location:/eguru/");
        $count = 1;
        ?>
        <div style="height: 28px">
            <div style="float: right;margin-right: 20px"><form  id="student_material_change">
                    <div id="material_change" class="modal hide fade in span8" style="display: none; margin-left: 0px">
                        <div class="modal-header">
                            <a class="close" data-dismiss="modal">x</a>
                            <h3>Change your type and hardness</h3>
                        </div>                               
                        <div class="modal-body span6">
                            <div class="control-group">
                                <label>Type :</label>
                                <div class="controls">
                                    <input type="radio" value="video" name="type">Video
                                    <input type="radio" value="ppt" name="type">PPT
                                    <input type="radio" value="descriptive" name="type">Descriptive
                                </div>
                            </div>
                            <div class="control-group">
                                <label>Hardness : </label>
                                <div class="controls">
                                    <?php
                                    if ($data['hardness'] == 2) {
                                        echo "<input  type='radio' name=hardness value='1'>Easier</td><td>";
                                        echo "<input type='radio' name=hardness value='3'>Harder</td>";
                                    } 
                                    else if ($data['hardness'] > 2) {
                                        echo "<input  type='radio' name=hardness value='2'>Easier</td><td>";
                                        echo "<input type='radio' name=hardness value='3' disabled='true'><text style='color:burlywood'>Harder</td>";
                                    } 
                                    else {
                                        echo "<input type='radio' name=hardness value='2'>Harder </td><td>&nbsp";
                                        echo "<input  type='radio' name=hardness value='1' disabled='true'><text style='color:burlywood'>Easier</text></td>";
                                    }
                                    ?>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="topic" value="<?=$data['topic']?>"/>
                            <input type="submit" name="material" value="Submit" class="btn btn-success"/> 
                            <a href="#" class="btn" data-dismiss="modal">Close</a>
                        </div>

                    </div>
                    <p><a data-toggle="modal" href="#material_change" id="login" class="btn btn-info adjust right"><i class="icon-remove-sign icon-white"></i> Dissatisfied</a></p>                            
                </form> </div> <a class="btn btn-danger" style="float:right;margin-right: 12px" href="/student/profile_student/"><i class="icon-ok-sign icon-white"></i>Satisfied</a></div>
            <div><h2 style="color:white">Your study material</h2>
            

            <div class="accordion">
                <?php
                $count = 1;
                //print_r ($material);
                ?>            
                <?php foreach ($material->result_array() as $row) {
                    ?><div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse<?= $count ?>" >
                                <h2 style="color:white">Link</h2>
                            </a>
                        </div>
                        <div id="collapse<?= $count ?>" class="accordion-body collapse ">
                            <div class="accordion-inner">
                                <h3  ><?php if ($row['link'] == "nothing") { ?><a  target="_blank" href="/uploads/<?= $data['subject_id'] ?>/<?= $row['file_name'] ?>" style="color:white" ><?php } else { ?><a target="_blank" href="<?= $row['link'] ?>" style="color:white"><?php } ?>Link</a></h3>
                            </div>
                        </div>
                    </div>                    
                    <?php
                    $count++;
                }
                ?>            
            </div>            
</div>        
<?php //include 'footer.php';   ?>                 

    </body>
</html>
