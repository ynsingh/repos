    <?php
    $mysql_hostname = "localhost";
    $mysql_user = "";
    $mysql_password = "";
    $mysql_database = "edrp_portal";
    $prefix = "";
    $bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database, write your mysql user_name and password in connection.php");
    mysql_select_db($mysql_database, $bd) or die("Could not select database");
    ?>
