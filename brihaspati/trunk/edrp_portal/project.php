<?php
	$a= $_POST["UserAddress1"];
	$b= $_POST["UserAddress2"];
	$c= $_POST["UserAddress3"];
	$d= $_POST["UserAddress4"];
	$e= $_POST["UserAddress5"];
	$Element= $_POST["element"];
	$filename= $_POST["filenm"];
	$redirect= $_POST["redirect"];
	$dom = new DOMDocument();
	$dom->encoding = 'utf-8';
	$dom->xmlVersion = '1.0';
	$dom->formatOutput = true;
	$dom->xmlStandalone = true;
	$xml_file_name=$filename;
		$root = $dom->createElement('CATALOG');

		$user_node=$dom->createElement($Element);

			$child_node_NAME=$dom->createElement('NAME',$c);
			$user_node->appendChild($child_node_NAME);

			$child_node_ANAME=$dom->createElement('ANAME',$d);
			$user_node->appendChild($child_node_ANAME);

			$child_node_INFO=$dom->createElement('INFO',$a);
			$user_node->appendChild($child_node_INFO);

			$child_node_URL=$dom->createElement('URL',$b);
			$user_node->appendChild($child_node_URL);

			$child_node_IMG=$dom->createElement('IMG',$e);
			$user_node->appendChild($child_node_IMG);

		$root->appendChild($user_node);
		$dom->appendChild($root);
		$dom->save($xml_file_name);
		header("Location: $redirect");
?>
