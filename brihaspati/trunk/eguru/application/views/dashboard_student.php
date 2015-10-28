
<!DOCTYPE html>
<html>
    <head>
        <title>EGURU | HOME</title>
        <link href="<?php echo base_url(); ?>/static/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="<?php echo base_url(); ?>/static/css/login.css" rel="stylesheet" type="text/css">
        <link href="<?php echo base_url(); ?>/static/css/jquery-ui.min.css" rel="stylesheet" type="text/css">
         <link href="<?php echo base_url(); ?>/static/css/autocomplete.css" rel="stylesheet" type="text/css">
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <!--<script src="/static/js/bootstrap.responsive.min.js" ></script>-->
        <script src="<?php echo base_url(); ?>/static/js/bootstrap.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-1.10.2.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery.validate.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/admin/admin_approve.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/jquery-ui.min.js"></script>
        <script src="<?php echo base_url(); ?>/static/js/student/dashboard.js"></script>
         <style>
             .navbar{
                 margin-bottom: 0px;
             }
             </style>
    </head>
    <body>
        <?php        
        include'header_login.php';
        if(!isset($_SESSION['email']))
            header("Location:/eguru/");
        if($data['stream']=='ee'){?>       
        <div style="background-image: url('/static/img/ee1.jpg');height: 564px">
        <?php } else { ?> 
        <div style="background-image: url('/static/img/it3.jpg');height: 564px"><?php }?>
         <form style="margin-top: 138px;margin-left: 314px" class="well span8" id="subject_selection" method="POST" action="/student/topic_material/">
            <center>
                <table>
                <tr>
                    <td>
                        Subject- <select id="subject" name="subject" >
                            <option value=""></option>
                        <?php                              
                                foreach ($data['subjects']->result_object() as $subjects){
                            ?>                         
                        <?php echo "<option value='$subjects->id'>$subjects->name</option>"; }?>                            
                        </select>                       
                        </td>
                        </tr>
                        <tr>
                            <td></td>
                            </tr>
                            <tr>
                                <td>
                                    Topic - <input type="text" id="topic" name="topic">
                            </td>
                            </tr>
                            <tr>
                            <td>
                                <input type ="submit" class="btn btn-primary" value="submit">    
                            </td>
                            </tr>
            </table>
                </center>
            </form>
            </div>
        <?php include 'footer.php'; ?>s
