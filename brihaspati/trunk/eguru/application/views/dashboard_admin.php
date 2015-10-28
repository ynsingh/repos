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
        <script src="<?php echo base_url(); ?>/static/js/admin/admin_approve.js"></script>               
    </head>
    <body>
        <?php
        include'header_login.php';
        if (!isset($_SESSION['email']))
            header("Location:/eguru/");
        ?>
        <button data-toggle="modal" href="#edit" id="login" style="float:right;padding: 6px 12px" class="btn-large btn-success ">Edit Department &nbsp;<i class="icon icon-off icon-white icon-pencil"></i></button>
        <button data-toggle="modal" href="#add" id="login"style="float:right;padding: 6px 12px;position: relative;right:8px" class="btn-danger btn-large">Add Department &nbsp;<i class="icon icon-off icon-white icon-plus"></i></button>
        <form  id="add_department">
            <div id="add" class="modal hide fade in span6" style="display: none; ">
                <div class="modal-header">
                    <a class="close" data-dismiss="modal">x</a>
                    <h3>Add Department</h3>
                </div>                               
                <div class="modal-body span4">
                    <div class="control-group">                                                   
                        <div class="controls">
                            <input type="text" name="add_department" class="required" placeholder="Department Name" required/>
                        </div>
                    </div>                                                
                </div>
                <div class="modal-footer">
                    <input type="submit" name="Submit" value="ADD" class="btn btn-success"/> 
                    <a href="#" class="btn" data-dismiss="modal">Close</a>
                </div>

            </div>                                                                  
        </form>
        <form  id="edit_epartment">
            <div id="edit" class="modal hide fade in span6" style="display: none; ">
                <div class="modal-header">
                    <a class="close" data-dismiss="modal">x</a>
                    <h3>Edit Given Departments</h3>
                </div>                               
                <div class="modal-body span4">
                    <div class="control-group">                                                   
                        <div class="controls">
                            <?php
                            $count0 = 0;
                            foreach ($departments->result_array() as $row0) {
                                $count0++;
                                ?>
                            <div>
                            <input type="text" class="editting" name="add_department<?= $count0 ?>" class="required" readonly value="<?= $row0['name'] ?>" required/>
                            <i class="icon-black icon-edit change" ></i>
                                <button class="btn-mini btn-primary done" value="<?=$row0['id']?>">Done</button>
                                <i class="icon-black icon-remove-sign delete"></i>
                                </div>
<?php } ?>
                            <input type= "hidden" name="count" value="<?= $count0 ?>">                         
                        </div>
                    </div>                                                
                </div>                

            </div>            
        </div>
    </form>
    <div class="accordion" style="margin-top:65px">  
<?php
$count = 1;
foreach ($data->result_array() as $row) {
    ?> 
            <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse<?= $count ?>">
                    <h2><?php echo $row['first_name'] . " " . $row['last_name'] . "         ( Applied for department:" . ucwords($row['name']) . ")";
    ?></h2>
                </a>
            </div>
            <div id="collapse<?= $count ?>" class="accordion-body collapse ">
                    <?php echo " <div class='accordion-inner " . $row['id'] . "'>"; ?>
                <b><h2> Research_papers_publication-:</h2><?php
                    echo "<h3>" . $row['research_papers_publication'] . "</h3>";
                    echo"<br><h2>Research Projects-:</h2>";
                    echo "<h3>" . $row['research_projects'] . "</h3>";
                    if ($row['status'] === 'approved') {
                        echo "<button type='button' aid='".$row['name']."' id='" . $row['id'] . "' value='" . $row['status'] . "' class='btn btn-primary approve'> Disapprove</button>";
                    } else {
                        echo "<button type='button' aid='".$row['name']."' id='" . $row['id'] . "' value='" . $row['status'] . "' class='btn btn-primary approve'>Approve</button>";
                    }
                    ?> 
            </div>                          
    <?php $count++;
} ?>
    </div>

<center>
<div class="" style=" background-color:antiquewhite;height: 40px;border-radius: 18px;margin-top:354px">
    <div class="span3"></div>
    <div class="vertical-line" style="width:100%;margin: 0px" >
        <table class="table pos">
            <thead>
                <tr>
                    <td><a href="<?php echo base_url(); ?>index.php/eguru/">Eguru</a></td>
                    <td><a href="http://nitdgp.ac.in"></a>NIT Durgapur</td>
                    <td>Follow Us</td>
                    <td>Copyright&COPY;</td>
                </tr>
            </thead>                
        </table>
    </div>    
</div>    
</center>

</body>
</html>
