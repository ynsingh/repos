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
    </head>
    <body>
        <style>
             .navbar{
                 margin-bottom: 0px;
             }
            </style>
        <?php
        include 'header_login.php';
        if (!isset($_SESSION['email']))
            header("Location:/eguru/");
        ?>
        <div style="height: 475px;background-image: url('/static/img/it1.jpg');background-size: 100% 100%;background-repeat: no-repeat">
            <form style="margin-top: 138px;margin-left: 314px" class="well control-group span8" action="/student/mode_change" method="POST" style="margin-left:12%;">
                <center><table style="font-size:16px">
                        <tr>
                            <td>
                                Change your level of hardness-</td><td style='padding-left: 15px;padding-right: 41px'>
                                <?php
                                if ($data['hardness'] >= 2) {
                                    echo "<input  type='radio' name=hardness value='1'>Easier</td><td>";
                                    echo "<input type='radio' name=hardness value='2'>Harder</td>";
                                } else {
                                    echo "<input type='radio' name=hardness value='2'>Harder </td><td>&nbsp";
                                    echo "<input  type='radio' name=hardness value='1' disabled='true'><text style='color:burlywood'>Easier</text></td>";
                                }                                 
                                ?></td>
                        </tr>
                        <br>
                        <tr>
                            <td></td>
                            </tr>
                        <tr>
                            <td>
                                Switch to- 
                            </td><td style='padding-left: 15px'>
                                <input type="radio" value="video" name="type">Video </td><td>
                                <input type="radio" value="ppt" name="type">PPT </td><td style="padding-right: 41px">
                                <input type="radio" value="descriptive" name="type">Descriptive</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" class="btn btn-danger" value="submit" name="Submit">
                            </td></tr>
                    </table> </center>
            </form>
        </div>
<?php include 'footer.php'; ?>
    </body>
