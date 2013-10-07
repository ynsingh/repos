<?php

        $a= $_POST["UserAddress1"];
        $b= $_POST["UserAddress2"];
        $c= $_POST["UserAddress3"];
        $d= $_POST["UserAddress4"];
        $e= $_POST["UserAddress5"];
        $Element= $_POST["element"];
        $filename= $_POST["filenm"];
        $redirect= $_POST["redirect"];
        $f= $_POST["SERVER"];
        $g= $_POST["SERVER2"];
        echo $Element;
        echo $filename;
                // load the document
                // the root node is <CATALOG/> so we load it into $CATALOG
        if (file_exists($filename)) {
        $CATALOG = simplexml_load_file($filename);

                // update
                if( !empty($c) )
                {
                        $CATALOG->$Element->NAME = $c;
                        }
                if( !empty($a) )
                {
                        $CATALOG->$Element->INFO = $a;
                        }

                if( !empty($d) )
                {
                        $CATALOG->$Element->ANAME = $d;
                        }
                if( !empty($b) )
                {
                        $CATALOG->$Element->URL = $b;
                        }
                if( !empty($e) )
                {
                        $CATALOG->$Element->IMG = $e;
                        }

                // save the updated document
                $CATALOG->asXML($filename);
                if( !empty($f) )
                {
                        //$xml = simplexml_load_file($filename);
                        $newchild = $CATALOG->addChild("SERVER");
                        $newchild->addChild("URL",$f);
                        $CATALOG->asXML($filename);
                }
                        header("Location: $redirect");
			}
			else 
				{
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

}
?>

