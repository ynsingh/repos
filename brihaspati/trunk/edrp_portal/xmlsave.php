
<?php
$a= $_POST["UserAddress1"];
$b= $_POST["UserAddress2"];
$c= $_POST["UserAddress3"];
$filename= $_POST["filenm"];
$redirect= $_POST["redirect"];

$dom = new DOMDocument();
$dom->encoding = 'utf-8';
$dom->xmlVersion = '1.0';
$dom->formatOutput = true;
$dom->xmlStandalone = true;
$xml_file_name=$filename;
$root = $dom->createElement('CATALOG');

$user_node=$dom->createElement('CD');

$child_node_AEM=$dom->createElement('AEM',$a);
$user_node->appendChild($child_node_AEM);
$child_node_CP=$dom->createElement('CP',$c);
$user_node->appendChild($child_node_CP);

$child_node_RES=$dom->createElement('RES',$b);
$user_node->appendChild($child_node_RES);

$root->appendChild($user_node);
$dom->appendChild($root);
$dom->save($xml_file_name);
//$m= realpath("index.php");
echo $redirect;
echo"hsdhsdhd";
header("Location: $redirect");
?>
