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
        <?php include 'header_login.php';
        if(!isset($_SESSION['email']))
            header("Location:/eguru/");
        ?>                
        <div style="height: 634px;background-image: url('/static/img/it1.jpg');background-size: 100% 100%;background-repeat: no-repeat">
            <table>
                <tr>
                    <td style="padding-left:70px">
                        <h2 style="color:red"> Select your stream </h2>
                        </td>
                    <td style="padding-left: 184px">
                        <a href="<?php echo base_url(); ?>/student/dashboard/2">
                            <img src="<?php echo base_url(); ?>/static/img/electrical.gif" style="width:241px">
                        </a>
                        </td>
                        <td style="padding-left: 205px;padding-top: 70px">
                        <a href="<?php echo base_url(); ?>/student/dashboard/1">
                            <img src="<?php echo base_url(); ?>/static/img/it.gif" style="width:104px">
                        </a>
                    </td>
                </tr>
            </table>
        </div>
<?php include 'footer.php';?>
    </body>
