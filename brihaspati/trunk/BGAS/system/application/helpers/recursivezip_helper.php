<?php
/**
 * recursively ZIP / Compress a file / folder
 * 
 */
class recursiveZip
{
    /**
     * Recursively reads a directory and compress files 
     * 
     * @param file $src source directory
     * @param file $zip recursively adds file
     * @param file $path the file path
     */
    private function recursive_zip($src,&$zip,$path) {
        // open file/directory
        $dir = opendir($src);
        // loop through the directory 
        while(false !== ( $file = readdir($dir)) ) {
            // skip parent (..) and root (.) directory
            if (( $file != '.' ) && ( $file != '..' )) {
                    // if directory found again, call recursive_zip() function again
                    if ( is_dir($src . '/' . $file) ) {
                            $this->recursive_zip($src . '/' . $file,$zip,$path);
                    }
                    else {
                            // add files to zip
                            $zip->addFile($src . '/' . $file,substr($src . '/' . $file,$path));
                    }
            }
        }
        closedir($dir);
    }

    /**
     * Perform compression
     * 
     * @param file $source source file/direcctory for compress
     * @param file $dst destination directory where zip file will be created
     * @return zip file / folder
     */        
    public function compress($src,$dst=''){
        
        // check zip extension loaded or not 
        // and
        // check soure file/directory exists or not
        if (!extension_loaded('zip') || !file_exists($src)) {
            return false;
        }        
        
        // remove last slash (/) from source directory / destination directory
        if(substr($src,-1)==='/'){
            $src=substr($src,0,-1);        
        }
        if(substr($dst,-1)==='/'){
            $dst=substr($dst,0,-1);           
        }
        $path=strlen(dirname($src).'/');
        //$filename=substr($src,strrpos($src,'/')+1).'.zip';
        $filename=substr($src,strrpos($src,'/')).  date("dmYHis").'.zip';
        $dst=empty($dst)? $filename : $dst.'/'.$filename;
        @unlink($dst);

        // create zip     
        $zip = new ZipArchive;
        $res = $zip->open($dst, ZipArchive::CREATE);
        if($res !== TRUE){
            echo 'Error: Unable to create zip file';
            exit;
        }
        if(is_file($src)){
                $zip->addFile($src,substr($src,$path));
        }
        else{
            if(!is_dir($src)){
                $zip->close();
                @unlink($dst);
                echo 'Error: File not found';
                exit;
            }
            $this->recursive_zip($src,$zip,$path);
        }
        $zip->close();
        return $dst;
    }
} // end class file
?>
